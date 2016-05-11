package RetailManager;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ShopDAO {
	public ArrayList<Shop> shops=new ArrayList<Shop>();
	public ArrayList<Shop> initializeShops(){
	ShopAddress addr=new ShopAddress("419","80992");
	Shop shop = new Shop(1,"Dachauer Str",addr,"48.1801361","11.5125280");
	shops.add(shop);
	return shops;
	}
}
