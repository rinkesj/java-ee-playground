package com.dere.playground.ee.jsf.layout;

import java.util.ArrayList;
import java.util.List;

public class Level1 {

    String name;
    String description;

    List<Level2> sublevels = new ArrayList<Level2>();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Level2> getSublevels() {
        return sublevels;
    }
}
