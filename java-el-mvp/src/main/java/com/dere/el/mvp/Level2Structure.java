package com.dere.el.mvp;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Level2Structure {
    
    private String name = "";
    private LocalDate date;

    Map<String, String> map = new HashMap<String, String>();

    public Level2Structure(String string) {
        name = string;

        date = LocalDate.now();

        map.put("key1", name + "_value1");
        map.put("key2", name + "_value2");
        map.put("key3", name + "_value3");
        map.put("key4", name + "_value4");
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public LocalDate getDate() {
        return date;
    }
}
