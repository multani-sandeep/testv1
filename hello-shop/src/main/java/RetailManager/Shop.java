package RetailManager;



public class Shop {
	 private final long id;
	    //private final String content;
	 	private  String shopName;
	    private  ShopAddress address; 
	    private String latitude;
	    private String longitude;
	    
	    
	    public ShopAddress getAddress() {
			return address;
		}

		public void setAddress(ShopAddress address) {
			this.address = address;
		}

		public String getShopName() {
			return shopName;
		}

		public void setShopName(String shopName) {
			this.shopName = shopName;
		}

	    
	    public Shop(long id, String shopName, ShopAddress address, String latitude, String longitude) {
	        this.id = id;
	        this.address=address;
	        this.shopName=shopName;
	        this.latitude=latitude;
	        this.longitude=longitude;

	    }

	    public String getLatitude() {
			return latitude;
		}

		public String getLongitude() {
			return longitude;
		}

		public long getId() {
	        return id;
	    }

	    
	
}
