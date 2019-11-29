package cn.ryan.mybatis.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.ryan.constant.RConstant;
import cn.ryan.utils.RyanUtil;

/**
 * 
 * @author lyodssoft.com
 * 
 * @creator xiesw
 * @version 1.0.0
 * @date 2019-01-01
 * @description MyBaties操作數據庫分頁插件
 * 
 */

@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PagePlugin implements Interceptor {

	/**
	 * 日 志
	 */
	private static Logger log = LogManager.getLogger(PagePlugin.class);

	private static String dialect = ""; // 数据库方言
	private static String pageSqlId = ""; // mapper.xml中需要拦截的ID(正则匹配)

	@SuppressWarnings("unchecked")
	public Object intercept(Invocation ivk) throws Throwable {
		// TODO Auto-generated method stub
		if (ivk.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler,
					"delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate,
					"mappedStatement");
			// 拦截需要分页的SQL
			if (mappedStatement.getId().matches(pageSqlId)) {
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();// 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
				if (parameterObject == null) {
					throw new NullPointerException("parameterObject尚未实例化！");
				} else {
					// 查詢條件
					Map<String, Object> search = null;
					if (parameterObject instanceof Map) { // 参数就是Search Map
						search = (Map<String, Object>) parameterObject;
					} else { // 不存在page属性
						return ivk.proceed();
					}
					Connection connection = (Connection) ivk.getArgs()[0];
					String sql = boundSql.getSql();
					int idx = RyanUtil.trim(sql).toUpperCase().indexOf("ORDER BY");
					// 總記錄數
					String totalSql = sql;
					if (idx != -1) {
						totalSql = RyanUtil.trim(sql).substring(0, idx);
					}
					String countSql = "select count(0) from (" + totalSql + ") tmp_count"; // 记录统计
					PreparedStatement countStmt = connection.prepareStatement(countSql);
					ReflectHelper.setValueByFieldName(boundSql, "sql", countSql);
					DefaultParameterHandler ParameterHandler = new DefaultParameterHandler(mappedStatement,
							parameterObject, boundSql);
					ParameterHandler.setParameters(countStmt);
					ResultSet rs = countStmt.executeQuery();
					int count = 0;
					if (rs.next()) {
						count = rs.getInt(1);
					}
					rs.close();
					countStmt.close();
					search.put("TOTAL_SIZE", count);
					String pageSql = generatePagesSql(sql, search);
					log.debug("Find Page SQL:" + pageSql);
					ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql); // 将分页sql语句反射回BoundSql.
				}
			}
		}
		return ivk.proceed();
	}

	/**
	 * 需要先注入SqlUtil对象后调用。 兼容数据库分页。
	 * Oracle,SQLServer,DB2時，別名ta,tb已經使用，sql不能再使用ta,tb這兩作為別名了。
	 * 
	 * @param jdbcType
	 * @param sql
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public String generatePagesSql(String sql, Map<String, Object> searchMap) {
		// 分頁
		Integer page = (Integer) searchMap.get("page");
		Integer pageSize = (Integer) searchMap.get("pageSize");
		// 如果page size 為NULL時，則直接使用默認值
		if (pageSize == null) {
			pageSize = RConstant.DEFAULT_PAGE_SIZE;
		}
		// 不能分頁
		if (page == null) {
			return sql;
		}
		// 分頁運算參數
		int start = (page - 1) * pageSize;
		int end = page * pageSize;
		StringBuffer buf = new StringBuffer();
		// oracle
		if ("oracle".equals(dialect)) {
			if (page == 1) {
				buf.append("SELECT ta.*,ROWNUM RN FROM(");
				buf.append(sql);
				buf.append(") ta WHERE ROWNUM <=" + end);
			} else {
				buf.append("SELECT tb.* FROM (SELECT ta.*,ROWNUM RN FROM (");
				buf.append(sql);
				buf.append(") ta WHERE ROWNUM<=" + end + ")tb where tb.RN>" + start);
			}
			// mysql
		} else if ("mysql".equals(dialect)) {
			if (start > 0) {
				start = start - 1;
			}
			buf.append(sql);
			buf.append(" limit " + start + "," + pageSize);
			// sqlserver or db2
		} else if ("sqlserver".equals(dialect)) {
			int idx = RyanUtil.trim(sql).toUpperCase().indexOf("ORDER BY");
			String order = RyanUtil.trim(sql).substring(idx);
			order = order.toUpperCase();
			order = order.replace("ORDER", "").replace("BY", "");
			String[] arr = RyanUtil.trim(order).split(",");
			String tmp = "";
			for (String s : arr) {
				String str = s;
				if (s.indexOf(".") != -1) {
					str = s.split("\\.")[1];
				}
				if (!RyanUtil.isEmpty(tmp)) {
					tmp += ",";
				}
				tmp += "ta." + RyanUtil.trim(str);
			}
			order = "ORDER BY " + tmp;

			String search = RyanUtil.trim(sql).substring(0, idx);
			buf.append("SELECT tb.* FROM (SELECT ta.*,ROW_NUMBER() over(" + order + ") RN");
			buf.append(" FROM (" + search + ") ta )tb ");
			buf.append("WHERE tb.RN>" + start + " AND tb.RN<=" + end);
		} else if ("db2".equals(dialect)) {
			buf.append("SELECT * FROM (SELECT ta.*, ROWNUMBER() OVER(ORDER BY ORDER OF ta) as RN FROM( ");
			buf.append(sql);
			buf.append(" FETCH FIRST " + end + " ROWS ONLY) AS ta) WHERE RN>" + start + " ORDER BY RN");
		} else if ("derby".equals(dialect)) {
			buf.append(sql);
			buf.append("OFFSET " + start + " ROWS FETCH NEXT " + pageSize + " ROWS ONLY");
		} else {
			buf.append(sql);
		}
		log.info("Conver sql for page:[" + buf.toString() + "]...");
		return buf.toString();
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	/**
	 * 設置分頁參數
	 */
	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		if (StringUtils.isEmpty(dialect)) {
			log.warn("dialect property is not found!");
		} else {
			dialect = dialect.toLowerCase(); // 統一轉換為小寫
		}
		pageSqlId = p.getProperty("pageSqlId");
		if (StringUtils.isEmpty(pageSqlId)) {
			log.warn("pageSqlId property is not found!");
		}
	}

}
