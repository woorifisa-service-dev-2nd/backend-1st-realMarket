package dev.kiosk.receipt;

public class Receipt {
	// 식당이름, 주문메뉴, 주문금액, 결제금액(주문금액-등급별 할인금액), 주문날짜및시간(현재)
	
	String name;
	String menu;
	int orderedPrice;

	
	public Receipt(String name, String menu, int orderedPrice) {
		this.name = name;
		this.menu = menu;
		this.orderedPrice = orderedPrice;

	}


	@Override
	public String toString() {
		return "Receipt [name=" + name + ", menu=" + menu + ", orderedPrice=" + orderedPrice + "]";
	}


	
	
	
}
