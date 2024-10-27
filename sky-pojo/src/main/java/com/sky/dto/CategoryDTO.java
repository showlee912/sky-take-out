package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {

    //主键id
    private Long id;

    //类型： 1为菜品分类 2为套餐分类
    private Integer type;

    //分类名称
    private String name;

    //排序
    private Integer sort;

}
