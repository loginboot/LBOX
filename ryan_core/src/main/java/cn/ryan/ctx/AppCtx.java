package cn.ryan.ctx;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author lyodssoft.com
 * 
 * @creator Kevin
 * @version 1.0.0
 * @date 2013-10-12
 * @description 用于存储ApplicationContext常量信息 - 创建
 *
 */
public class AppCtx implements Serializable {
    private static final long serialVersionUID = 1;
    /**
     * 存储系统参数
     */
    private HashMap<String, String> params = new HashMap<String, String>();
    private HashMap<String, Object> objparams = new HashMap<String, Object>();

    private WebApplicationContext webctx;

    @Autowired
    public void setWebAplicationContext(WebApplicationContext ctx) {
        webctx = ctx;
    }

    public WebApplicationContext getWebApplicationContext() {
        return webctx;
    }

    /**
     * get web root path
     * 
     * @return
     */
    public String getWebAppPath() {
        return webctx.getServletContext().getRealPath("/");
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    /**
     * Return all language map
     * 
     * @return Map<String,String> eg: en_US English
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getLangMap() {
        List<String> langs = (List<String>) getObjParam("lang");
        Map<String, String> maps = new LinkedHashMap<String, String>();
        for (String s : langs) {
            String[] scode = s.split("\\|");
            if (scode.length == 2) {
                maps.put(scode[0], scode[1]);
            }
        }
        return maps;
    }

    /**
     * 获取指定key的参数
     * 
     * @param key
     * @return String
     */
    public String getParam(String key) {
        return params.get(key);
    }

    public HashMap<String, Object> getObjparams() {
        return objparams;
    }

    public void setObjparams(HashMap<String, Object> objparams) {
        this.objparams = objparams;
    }

    public Object getObjParam(String key) {
        return objparams.get(key);
    }
}
