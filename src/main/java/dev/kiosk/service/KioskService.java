package dev.kiosk.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import dev.kiosk.customer.Customer;
import dev.kiosk.customer.CustomerProcessor;
import dev.kiosk.parser.CustomerParser;
import dev.kiosk.receipt.Receipt;
import dev.kiosk.restaurant.Restaurant;
import dev.kiosk.restaurant.RestaurantParser;
import dev.kiosk.restaurant.RestaurantProcessor;

public class KioskService {
	private static final String RESOURCES = "src/main/resources";
	final Path userPath = Paths.get(RESOURCES + "/userData.txt");
	final Path restaurantPath = Paths.get(RESOURCES+"/restaurant.txt");
	
	private String name; // 얘를 다른 곳(찾는 애) 한테 넘길 거니까 여기 있어야 함.
	private String restaurant;
	private String menu;
	private Customer customerInfo;
	
	public void service() {
		
		try {
		// 1. 주어진 파일 읽기
		List<String> users = Files.readAllLines(userPath);
		List<String> reataurants = Files.readAllLines(restaurantPath);
		
		CustomerParser parser = new CustomerParser();
		List<Customer> customers = parser.parseLinesFrom(users);
		
		RestaurantParser restaurantParser = new RestaurantParser();
		List<Restaurant> diners = restaurantParser.parseLinesFrom(reataurants); // diners -> 레스토랑 객체 리스트
		
		// 2-1. 이름 입력 받기
		Scanner sc = new Scanner(System.in);
		System.out.println("이름을 입력하세요");
		this.name = sc.nextLine();

		CustomerProcessor customerProcessor = new CustomerProcessor();
		this.customerInfo = customerProcessor.findCustomer(customers, name);
		
		// 2-2. 식당 입력 받기
		System.out.println("식당을 선택하세요 \n 1.김둘레순대국 2.롯데리아");
		this.restaurant = sc.nextLine();
		
		RestaurantProcessor restaurantProcessor = new RestaurantProcessor();
		// 여기에 식당 객체를 넘긴 이유는 식당 주인을 만들기 위해서(restaurantProcessor를 우리가 선택한 식당의 주인으로 만들자)
		List<String> menus = restaurantProcessor.findMenu(diners, restaurant);
		
		// 2-3. 메뉴 입력 받기
		System.out.println("메뉴를 선택해주세요" + menus);
		this.menu = sc.nextLine();
		
		// 3. 주문이 가능한지 확인 -> purchasableMenu
		// 3-1. 선택한 메뉴 (selectedMenu) 의 가격 조회
		// 3-1. 고객 의 잔액 조회
		
		int menuPrice = restaurantProcessor.findPrice(diners, restaurant, menu, customerInfo);
		boolean isPurchasable = restaurantProcessor.purchasableMenu(customerInfo, menuPrice);
		
		if(isPurchasable) {
			Receipt receipt = new Receipt(restaurant, menu, menuPrice);
			
			System.out.println(receipt);
		}
	
		} catch(IOException e) {
			
		} 
	}
	
	public void scanner() {
		
		
		
		
	}
	
	
}
