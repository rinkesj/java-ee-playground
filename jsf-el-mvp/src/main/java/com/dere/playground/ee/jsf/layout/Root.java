package com.dere.playground.ee.jsf.layout;

import java.util.ArrayList;
import java.util.List;

import com.dere.playground.ee.jsf.layout.Level2.Level2Enum;

public class Root {

    String name;
    String description;

    List<Level1> items = new ArrayList<Level1>();

    public static Root createRoot() {
        Root root = new Root();
        root.name = "root";
        root.description = "root description";

        for (int i = 0; i < 6; i++) {
            Level1 l1a = new Level1();
            l1a.name = "Level1 " + i;
            l1a.description = "Level1 A " + i + " description";

            for (int j = 0; j < 3; j++) {
                Level2 l2 = new Level2();
                l2.name = "Level2 " + i + " " + j;
                l2.description = "Level2 description";
                l2.ruletype2 = Level2Enum.L2ENUM1;
                l2.valtype2 = Level2Enum.L2ENUM4;
                l2.rule2 = "rule2";
                l2.op2 = "op2";
                l2.value2 = "value2";

                l1a.sublevels.add(l2);
            }

            root.items.add(l1a);
        }

        return root;
    }

    public void print() {
        System.out.println(name);
        System.out.println(description);

        for (Level1 level1 : items) {
            System.out.println(level1.name);
            System.out.println(level1.description);

            for (Level2 level2 : level1.sublevels) {
                System.out.println(level2.name);
                System.out.println(level2.description);
            }
        }
    }

    public static void main(String[] args) {
        Root.createRoot().print();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Level1> getItems() {
        return items;
    }
}
