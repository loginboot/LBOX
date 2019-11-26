package cn.ryan.model.rb;

import java.io.Serializable;

import cn.ryan.model.AbstractEntity;

/**
 * 
 * @author cn.ryan
 * @creator xiesw
 * @version 1.0.0
 * @date 2019-11-10
 * @description 文件类型
 *
 */
public class RbFileType extends AbstractEntity implements Serializable {

	/**
	 * ID
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private String pattern;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
