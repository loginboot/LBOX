package cn.ryan.model;

import cn.ryan.utils.JsonMapper;

/**
 * 
 * @author cn.ryan
 * 
 * @creator xiesw
 * @version 1.0.0
 * @date 2019-11-16
 * @description Json Format - Create
 *
 */

public abstract class AbstractEntity {

	@Override
	public String toString() {
		JsonMapper mapper = new JsonMapper();
		return mapper.toJson(this);
	}
}
