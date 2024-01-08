package dev.kiosk.restaurant;

import java.util.ArrayList;
import java.util.List;

import dev.kiosk.customer.Customer;

public class RestaurantProcessor {
	// 선택한 식당의 메뉴 리스트 리턴
	public List<String> findMenu(List<Restaurant> restaurants, String name){

		List<String> menus = new ArrayList<String>();
		
		for(Restaurant restaurant: restaurants) {
			if(restaurant.getName().equals(name)) {
				menus.add(restaurant.getMenu());
			}
		}
//		System.out.println("menus = " + menus);
//		System.out.println("resutaurants = " + restaurants);
		System.out.println(name);
		System.out.println("menus = " + menus);
		return menus;
	}
	
	// 등급에 따라 할인된 가격을 계산해주는 메서드
	public int discount(int price, String grade) {
		int discountedPrice = 0;
		
		if(grade.equals("VIP")) {
			discountedPrice = (int)(price * 0.88);
		} else if (grade.equals("GOLD")) {
			discountedPrice = (int)(price * 0.9);
		} else if(grade.equals("SILVER")) {
			discountedPrice = (int)(price * 0.96);
		} else if (grade.equals("BRONZE")) {
			discountedPrice = (int)(price * 0.985);
		} else {
			discountedPrice = price;
		}
	
//		switch(grade){
//		case "VIP":
//			discountedPrice = (int)(price * 0.88);
//			break;
//		case "GOLD":
//			discountedPrice = (int)(price * 0.9);
//			break;
//		case "SILVER":
//			discountedPrice = (int)(price * 0.96);
//			break;
//		case "BRONZE":
//			discountedPrice = (int)(price * 0.985);
//		default:
//			discountedPrice = price; 
//		}
		
		
		return discountedPrice; 
		
	}
	
	// 구매가능한 가격인지 - 잔액과 메뉴 가격 비교
	public boolean purchasableMenu(Customer customerInfo, int price) {
		boolean purchasable = false;
		if (customerInfo.getBalance() >= price) {
			purchasable = true;
		}
		return purchasable;
	}
	
	// 선택한 식당의 메뉴의 가격 찾기
	public int findPrice (List<Restaurant> restaurants, String name, String menu, Customer customerInfo) {
		int price = 0;
		
		for(Restaurant restaurant: restaurants) {
			if(restaurant.getName().equals(name) && restaurant.getMenu().equals(menu)) {
				 price = this.discount(restaurant.getPrice(), customerInfo.getGrade());
				 return price;
			}
		}
		
		return price;
	}
}
