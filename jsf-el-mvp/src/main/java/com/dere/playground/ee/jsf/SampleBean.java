package com.dere.playground.ee.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("dtColumnsView")
@RequestScoped
public class SampleBean {

	private final static List<String> VALID_COLUMN_KEYS = Arrays.asList("code", "name", "category", "quantity");

	private String columnHeader = "code";
	private String columnExpression = "code";

	private String message = "Hello World JSF with Java EE 8";

	public String getMessage() {
		return message;
	}

	private List<ColumnModel> columns = new ArrayList<ColumnModel>();

	private List<Product> products;

	private List<Product> filteredProducts;

	@Inject
	private ProductService service;

	@PostConstruct
	public void init() {
		products = service.getProducts(10);
		
		columns.add(new ColumnModel("Code", "code"));
		columns.add(new ColumnModel("inventoryStatus", "inventoryStatus.ordinal()"));
		columns.add(new ColumnModel("inventoryStatus", "inventoryStatus.ordinal"));
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

	public String getColumnHeader() {
		return columnHeader;
	}

	public void setColumnHeader(String columnHeader) {
		this.columnHeader = columnHeader;
	}

	public String getColumnExpression() {
		return columnExpression;
	}

	public void setColumnExpression(String columnExpression) {
		this.columnExpression = columnExpression;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	private void createDynamicColumns() {
		columns.add(new ColumnModel(columnHeader, columnExpression));
	}

	public void updateColumns() {
		// reset table state
		UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:products");
		table.setValueExpression("sortBy", null);
		UIComponent table2 = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:columns");
		table2.setValueExpression("sortBy", null);

		// update columns
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
