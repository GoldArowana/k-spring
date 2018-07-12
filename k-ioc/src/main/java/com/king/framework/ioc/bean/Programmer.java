package com.king.framework.ioc.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
// 程序员
public class Programmer {
    // 程序员的名字
    private String name;

    //程序员的电脑
    private Computer computer;
}