package com.dere.el.mvp;

public class TestStructure {

    String name = "Test";
    Level2Structure level2 = new Level2Structure("Level2");
    private Level2Structure level2a;
    
    public String getName() {
        return name;
    }

    public Level2Structure getLevel2() {
        return level2;
    }

    public Level2Structure getLevel2a() {
        return level2a;
    }

}