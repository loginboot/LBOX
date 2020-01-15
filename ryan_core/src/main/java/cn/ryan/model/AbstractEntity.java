package cn.ryan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    private long csize;

    @Override
    public String toString() {
        JsonMapper mapper = new JsonMapper();
        return mapper.toJson(this);
    }

    @JsonIgnore
    public long getCsize() {
        return csize;
    }

    public void setCsize(long csize) {
        this.csize = csize;
    }
}
