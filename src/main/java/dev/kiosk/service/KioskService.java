package dev.kiosk.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dev.kiosk.KioskApplication;
import dev.kiosk.customer.Customer;
import dev.kiosk.customer.CustomerProcessor;
import dev.kiosk.parser.CustomerParser;
import dev.kiosk.receipt.Receipt;
import dev.kiosk.restaurant.Restaurant;
import dev.kiosk.restaurant.RestaurantParser;
import dev.kiosk.restaurant.RestaurantProcessor;

public class KioskService {
	private static final String RESOURCES = "/resources/";
//	final Path userPath = Paths.get(RESOURCES + "/userData.txt");
//	final Path restaurantPath = Paths.get(RESOURCES+"/restaurant.txt");
	
	private String name; // 얘를 다른 곳(찾는 애) 한테 넘길 거니까 여기 있어야 함.
	private String restaurant;
	private String menu;
	private Customer customerInfo;
	
	public void service() {
		List<String> userList = new ArrayList<>();
		List<String> restaurantList = new ArrayList<>();
		
		
		try (
		
		// 1. 주어진 파일 읽기	
		InputStream inputStream1 = KioskApplication.class.getResourceAsStream(RESOURCES + "userData.txt");
		InputStream inputStream2 = KioskApplication.class.getResourceAsStream(RESOURCES + "restaurant.txt");

			BufferedReader reader1 = new BufferedReader(new InputStreamReader(inputStream1, "UTF-8"));
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(inputStream2, "UTF-8"))) {
			
			String lines1;
			String lines2;
			
			while((lines1 = reader1.readLine()) != null) {
				userList.add(lines1);
			}
			while((lines2 = reader2.readLine()) != null) {
				restaurantList.add(lines2);
			}
		
		CustomerParser parser = new CustomerParser();
		List<Customer> customers = parser.parseLinesFrom(userList);
		
		RestaurantParser restaurantParser = new RestaurantParser();
		List<Restaurant> diners = restaurantParser.parseLinesFrom(restaurantList); // diners -> 레스토랑 객체 리스트
//		System.out.println(diners);

		
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
//		System.out.println(menus);
		
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
			System.out.println("올바른 값을 입력하세요.");
			System.exit(0);
} 
	}
	
	public void scanner() {
		
		
		
		
	}
	
	
}
