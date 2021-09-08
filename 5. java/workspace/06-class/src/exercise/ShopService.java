package exercise;

public class ShopService {

	private static ShopService ss;
	
	private ShopService() {
		
	}
	
	public static ShopService getInstance() {
		if (ss == null) {
			ss = new ShopService();
		}
		return ss;
	}

	
}
