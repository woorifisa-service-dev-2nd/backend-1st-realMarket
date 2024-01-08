package dev.kiosk;

import dev.kiosk.service.KioskService;

public class KioskApplication {

	public static void main(String[] args) {
		
		
		KioskService kioskService = new KioskService();
		
		kioskService.service();

	}

}
