package cn.ryan.model.rb;

import java.io.Serializable;

import cn.ryan.model.AbstractEntity;

/**
 * 
 * @author cn.ryan
 * @creator xiesw
 * @version 1.0.0
 * @date 2019-11-10
 * @description 目录路径树
 *
 */
public class RbFilePath extends AbstractEntity implements Serializable {

	/**
	 * ID
	 */
	private static final long serialVersionUID = 1L;

	private String fkey;

	private String pkey;

	private String path;

	private int level;

	private String createDate;

	public String getFkey() {
		return fkey;
	}

	public void setFkey(String fkey) {
		this.fkey = fkey;
	}

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
