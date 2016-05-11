package RetailManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {

//    private static final String template = "Hello, %s!";
	private static final String dummy="000";
    //private static final String shopName="%s";
    private final AtomicLong counter = new AtomicLong();
    static Shop retShop;
    @RequestMapping("/shops")
    public Shop shopTracker(@RequestParam(value="name", defaultValue="DevWorld") String name, @RequestParam(value="postcode", defaultValue="110011") String postcode, @RequestParam(value="num", defaultValue="1") String num) throws Exception{
    	LatLng latlong=new LatLng();
    	ShopAddress address= new ShopAddress(num,postcode);
    	
    	 String latLongs[] = latlong.getLatLongPositions(postcode);
    	 System.out.println("Latitude: "+latLongs[0]+" and Longitude: "+latLongs[1]);
         String latitude=latLongs[0];
         String longitude=latLongs[1];
        return new Shop(counter.incrementAndGet(),name,address,latitude,longitude);
       
        
    }
    
    @RequestMapping("/shopLocator")
    public Shop shopLocator(@RequestParam(value="latitude") String latitude, @RequestParam(value="longitude") String longitude){
    	ShopDAO dao=new ShopDAO();
    	ArrayList<Shop> shops=dao.initializeShops();
    	int minLat=999;
    	int minLong=999;
    	String retLat;
		String retLng;
		 
    	for(Shop shop:shops){
    		int lat=Integer.parseInt(shop.getLatitude());
    		int lng=Integer.parseInt(shop.getLongitude());
    		int difflat=lat-Integer.parseInt(latitude);
    		if(difflat<0){difflat*=-1;}
    		int difflong=lng-Integer.parseInt(longitude);
    		if(difflong<0){difflong*=-1;}
    		if(minLat>difflat){
    			minLat=difflat;
    			if(minLong>difflong){
    				minLong=difflong;
    				 retLat=""+lat;
    				 retLng=""+lng;
    				 retShop=shop;
    			}
    		}
    	}
    	return retShop;
    	//Shop shop= new shop()
        //return new Shop(counter.incrementAndGet(),name,address,retLat,retLng);
       
        
    }
}
