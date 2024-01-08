package dev.kiosk.customer;

import java.util.List;

public class CustomerProcessor {
	
	
	
	public Customer findCustomer(List<Customer> customers, String name) {
		
		Customer customerInfo = null; // @@@@@@@@@@@@@ 없는 이름이 입력됐을 경우 null이 반환되지 않도록
		// 처리 필요
		
		for(Customer customer: customers) {
			
			if (customer.getName().equals(name)) {
				customerInfo = customer;
				
			}
		} 
		return customerInfo;
	}
	
	
}
