package com.dere.playground.ee.jsf.layout;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("LayoutBean")
@RequestScoped
public class LayoutBean {

    private Root root = Root.createRoot();

    public Root getRoot() {
        return root;
    }
    
}
