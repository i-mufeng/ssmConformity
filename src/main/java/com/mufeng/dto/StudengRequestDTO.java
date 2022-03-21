package com.mufeng.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author MFGN
 * @data 2022/3/21 21:06
 * @description
 */
@ApiModel(description = "学生模块请求内容")
public class StudengRequestDTO implements Serializable {

    private static final long serialVersionUID = -8040311567474169534L;

    /**
     * id
     */
    @ApiModelProperty(value = "学号", required = true, example = "1")
    private Integer id;
    /**
     * 学生姓名
     */
    @ApiModelProperty(value = "学号")
    private String name;
    /**
     * 地址
     */
    @ApiModelProperty(value = "住址")
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
        return "StudengRequestDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
