package dev.kiosk;

import dev.kiosk.service.KioskService;

public class KioskApplication {

	public static void main(String[] args) {
//		System.setProperty("file.encoding","UTF-8");
		
		KioskService kioskService = new KioskService();
		
		kioskService.service();

	}

}
