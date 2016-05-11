package hello;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

<<<<<<< HEAD
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
>>>>>>> branch 'master' of https://github.com/multani-sandeep/testv1
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import com.vividsolutions.jts.geom.Coordinate;

@Controller
@RequestMapping("/shops")
public class ShopsController {

<<<<<<< HEAD
    DefaultGeographicCRS default_crs = DefaultGeographicCRS.WGS84;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
=======
	private static List<Shop> shops = (List<Shop>) Collections.synchronizedList(new ArrayList<Shop>());
>>>>>>> branch 'master' of https://github.com/multani-sandeep/testv1

<<<<<<< HEAD
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Greeting sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
    	//Read https://en.wikipedia.org/wiki/Great-circle_distance
    	//for orthodromic distance
    	//JTS.orthodromicDistance(p1, p2, crs)
    	//Coordinate p1=new Coordinate(x, y);
    	CoordinateReferenceSystem crs;
    	if(crs==null){
    		crs=default_crs;
    	}
		JTS.orthodromicDistance(p1, p2, crs)
    	
    	
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
=======
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> addShop(@RequestBody Shop shopData) {
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyDIsygvV5fcTO1lLdiqbsNRC0IJKC7BV6c");
		GeocodingResult[] results;
		try {
			results = GeocodingApi.geocode(context,
			    shopData.getName()+","+shopData.getNumber()+","+shopData.getPostcode()).await();
			if(results ==null || results.length<1){
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
			shopData.setLatitude(String.valueOf(results[0].geometry.location.lat));
			shopData.setLongitude(String.valueOf(results[0].geometry.location.lng));
			System.out.println(shopData.getName() +" "+shopData.getLatitude()+">"+shopData.getLongitude());
			shops.add(shopData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
>>>>>>> branch 'master' of https://github.com/multani-sandeep/testv1

}