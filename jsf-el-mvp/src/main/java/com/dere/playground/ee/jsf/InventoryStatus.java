package com.dere.playground.ee.jsf;

public enum InventoryStatus {
	INSTOCK, LOWSTOCK, OUTOFSTOCK;
	
	public int getOrdinal() {
		return ordinal();
	}
	
	

}
