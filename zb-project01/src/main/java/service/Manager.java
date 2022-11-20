package service;


public class Manager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WifiInfoService ws = new WifiInfoService();
		HttpService hs = new HttpService();
		try {
			hs.loadWifi();
			System.out.println("load 성공");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
