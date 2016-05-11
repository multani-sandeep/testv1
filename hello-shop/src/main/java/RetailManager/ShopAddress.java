package RetailManager;

public class ShopAddress {

	private  String shopNum; 
    
    private  String postcode;
    
    public String getShopNum() {
		return shopNum;
	}

//	public void setShopNum(String shopNum) {
//		this.shopNum = shopNum;
//	}

	

	public String getPostcode() {
		return postcode;
	}

//	public void setPostcode(String postcode) {
//		this.postcode = postcode;
//	}

	 public ShopAddress(String num, String postcode){
		 this.shopNum=num;
		 this.postcode=postcode;
	 }

	
	
}
