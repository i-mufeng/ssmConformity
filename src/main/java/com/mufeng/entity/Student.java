package com.mufeng.entity;

import java.io.Serializable;

/**
 * (Student)实体类
 *
 * @author makejava
 * @since 2022-03-21 16:39:28
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 775574806239651301L;
    /**
     * id
     */
    private Integer id;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 地址
     */
    private String address;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

