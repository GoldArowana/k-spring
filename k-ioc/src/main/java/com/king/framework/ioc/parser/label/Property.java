package com.king.framework.ioc.parser.label;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ioc.xml中 bean标签里的 property标签
 */

@Getter
@Setter
@ToString
public class Property {
    private String name;
    private String value;
    private String ref;
}
