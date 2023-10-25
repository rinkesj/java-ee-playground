package com.dere.playground.ee.jsf.layout;

public class Level2 {
    
    public enum Level2Enum {
        L2ENUM1,
        L2ENUM2,
        L2ENUM3,
        L2ENUM4,
        L2ENUM5,
    }
    
    String name;
    String description;

    Level2Enum valtype2;
    String value2;
    String op2;

    Level2Enum ruletype2;
    String rule2;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOp2() {
        return op2;
    }

    public Level2Enum getValtype2() {
        return valtype2;
    }

    public String getRule2() {
        return rule2;
    }

    public Level2Enum getRuletype2() {
        return ruletype2;
    }

    public String getValue2() {
        return value2;
    }

}
