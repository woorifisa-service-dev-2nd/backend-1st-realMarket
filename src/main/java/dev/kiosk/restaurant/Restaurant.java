package dev.kiosk.restaurant;

import java.util.List;

public class Restaurant {
	private String name;
	private String menu;
	private int price;
	
	public Restaurant(String name, String menu, int price) {
		this.name = name;
		this.menu = menu;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Restaurant [name=" + name + ", menu=" + menu + ", price=" + price + "]";
	}

	public String getName() {
		return name;
	}

	public String getMenu() {
		return menu;
	}

	public int getPrice() {
		return price;
	}
	
	
	
	
	
	
	
}
