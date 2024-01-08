package dev.kiosk.parser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import dev.kiosk.customer.Customer;

public class CustomerParser {
	
	
	// 1. 한 줄 파싱하는 기능
	public Customer parseFrom(final String user) {
		String[] columns = user.split(",");
		
		String name = columns[0];
		String grade = columns[1];
		int balance = Integer.parseInt(columns[2]);
	
		Customer customer = new Customer(name, grade, balance);
		
		return customer;
		
		
	}

	
	// 2. 한줄씩 파싱된 데이터를 리스트에 추가하는 기능
	public List<Customer> parseLinesFrom(List<String> users) {
		List<Customer> customers = new ArrayList<Customer>(); // <>안에 값 없으면 왜 에러?
	
		for (String user : users) {
			Customer customer = parseFrom(user);
			customers.add(customer); // ArrayList 배열의 요소로 추가
		}
		
		return customers;
		
		
	
	}
	
	

	
}
