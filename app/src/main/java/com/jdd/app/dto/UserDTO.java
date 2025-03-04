package com.jdd.app.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangxuegang@gmail.com
 */
@Data
public class UserDTO implements Serializable {
    /**
     * 主键
     */
    public Long id;

    /**
     * 姓名
     */
    public String name;

    /**
     * 年龄
     */
    public Integer age;

    /**
     * 地址
     */
    public String addr;
}
