package dev.kiosk.restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantParser {

	// 1. 한 줄 파싱하는 기능
	public Restaurant parseFrom(final String restaurant) {
		String[] columns = restaurant.split(",");
		
		String name = columns[0];
		String menu = columns[1];
		int price = Integer.parseInt(columns[2]);
		
		
		Restaurant diner = new Restaurant(name, menu, price);
		
		return diner;
	}
	
	// 2. 한 줄씩 파싱된 데이터를 리스트에 추가하는 기능
	public List<Restaurant> parseLinesFrom(List<String> lists) {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		
		for (String list : lists) {
			Restaurant restaurant = parseFrom(list);
			restaurants.add(restaurant);
		}
		
		return restaurants;
		
	}
	
}
