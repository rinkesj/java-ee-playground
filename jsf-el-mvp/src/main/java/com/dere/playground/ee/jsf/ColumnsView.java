package com.dere.playground.ee.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

//@Named("dtColumnsView")
//@ViewScoped
public class ColumnsView implements Serializable {
    
	private static final long serialVersionUID = -6865130041633970271L;

	private final static List<String> VALID_COLUMN_KEYS = Arrays.asList("code", "name", "category", "quantity");
	
    private String columnTemplate = "code name quantity";
    
    private List<ColumnModel> columns;
    
    private List<Product> products;
    
    private List<Product> filteredProducts;
    
    @Inject
    private ProductService service;

    @PostConstruct
    public void init() {
        products = service.getProducts(10);
        
        createDynamicColumns();
    }
    
    public List<Product> getProducts() {
        return products;
    }

    public List<Product> getFilteredProducts() {
        return filteredProducts;
    }

    public void setFilteredProducts(List<Product> filteredProducts) {
        this.filteredProducts = filteredProducts;
    }

    public void setService(ProductService service) {
        this.service = service;
    }

    public String getColumnTemplate() {
        return columnTemplate;
    }

    public void setColumnTemplate(String columnTemplate) {
        this.columnTemplate = columnTemplate;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    private void createDynamicColumns() {
        String[] columnKeys = columnTemplate.split(" ");
        columns = new ArrayList<>();
        
        for(String columnKey : columnKeys) {
            String key = columnKey.trim();
            
            if(VALID_COLUMN_KEYS.contains(key)) {
                columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
            }
        }
    }
    
    public void updateColumns() {
        //reset table state
        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:products");
        table.setValueExpression("sortBy", null);
        
        //update columns
        createDynamicColumns();
    }
    
    static public class ColumnModel implements Serializable {

        private String header;
        private String property;

        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }

        public String getHeader() {
            return header;
        }

        public String getProperty() {
            return property;
        }
    }
}
