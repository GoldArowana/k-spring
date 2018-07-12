package com.king.framework.ioc.bean;

import org.junit.Test;

public class ComputerTest {
    @Test
    public void t() {
        Computer computer = new Computer();
        computer.setBrand("mac");
        computer.setPrice(11233);
        System.out.println(computer.toString());
    }
}
