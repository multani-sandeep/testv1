package hello;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

@Controller
@RequestMapping("/shops")
public class ShopsController {

	private static List<Shop> shops = (List<Shop>) Collections.synchronizedList(new ArrayList<Shop>());

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> addShop(@RequestBody Shop shopData) {
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyDIsygvV5fcTO1lLdiqbsNRC0IJKC7BV6c");
		GeocodingResult[] results;
		try {
			results = GeocodingApi.geocode(context,
			    shopData.getName()+","+shopData.getNumber()+","+shopData.getPostcode()).await();
			shopData.setLatitude(String.valueOf(results[0].geometry.location.lat));
			shopData.setLongitude(String.valueOf(results[0].geometry.location.lng));
			shops.add(shopData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}