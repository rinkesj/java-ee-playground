package com.dere.el.mvp;

import java.lang.reflect.Method;

import javax.el.ExpressionFactory;
import javax.el.StandardELContext;
import javax.el.ValueExpression;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.TreeValueExpression;
import de.odysseus.el.tree.TreeBuilder;
import de.odysseus.el.tree.impl.Parser;
import de.odysseus.el.tree.impl.Scanner;
import de.odysseus.el.util.SimpleContext;

public class Main {

    public static void main(String[] args) {
        TestStructure object = new TestStructure();
        // ExpressionFactory factory = ExpressionFactory.newInstance();
        // StandardELContext context = new StandardELContext(factory);
        // Object value = null;

        ExpressionFactory factory = new ExpressionFactoryImpl();
        SimpleContext context = new SimpleContext();
        context.setVariable("root", factory.createValueExpression(object, TestStructure.class));

        TreeValueExpression expr = (TreeValueExpression) factory.createValueExpression(context, "${root.level2}", Object.class);
        whisperNext(expr.getValue(context));

        // factory.createValueExpression(context, "${root}", Root.class).setValue(context, object);
        
        // value = factory.createValueExpression(context, "${root}", Object.class).getValue(context);
        // whisperNext(value);
        // value = factory.createValueExpression(context, "${root.level2}", Object.class).getValue(context);
        // whisperNext(value);
        // value = factory.createValueExpression(context, "${empty root.level2a ? 'null' : root.level2.name}", Object.class).getValue(context);
        // whisperNext(value);
        // value = factory.createValueExpression(context, "${root.name}", Object.class).getValue(context);
        // whisperNext(value);
        // value = factory.createValueExpression(context, "${root.level2.name}", Object.class).getValue(context);
        // whisperNext(value);
        // value = factory.createValueExpression(context, "${root.level2.date}", Object.class).getValue(context);
        // whisperNext(value);
        // value = factory.createValueExpression(context, "${root.level2.map['key1']}", Object.class).getValue(context);
        // whisperNext(value);
        // value = factory.createValueExpression(context, "${root.level2.map['key1'].substring(4,5)}", Object.class).getValue(context);
        // whisperNext(value);
    }

    private static void whisperNext(Object value) {
        System.out.println(value);
        Method[] methods = value.getClass().getMethods();
        for (Method method : methods) {
            if(!(method.getDeclaringClass() == Object.class))
                System.out.println(method.getName() + "    " + method.getReturnType().getSimpleName() + ", " + method.getDeclaringClass().getSimpleName());
        }
        System.out.println();
    }
}
