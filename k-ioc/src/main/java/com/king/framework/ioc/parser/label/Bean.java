package com.king.framework.ioc.parser.label;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * ioc.xml中 bean标签
 */

@ToString
@Getter
@Setter
public class Bean {

    public static final String SINGLETON = "singleton";
    public static final String PROTOTYPE = "prototype";

    private String name;
    private String className;

    // bean默认是单例的
    private String scope = SINGLETON;

    private List<Property> properties = new ArrayList<>();

}
