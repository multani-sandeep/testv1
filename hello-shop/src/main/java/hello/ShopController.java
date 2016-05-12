package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import com.vividsolutions.jts.geom.Coordinate;

@Controller
public class ShopController {

	@Autowired
    private ShopFacade shopFacade;
    
    @RequestMapping(path = "/shops/add",method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> addShop(@RequestBody Shop shopData) {
       
    	HttpStatus httpResult = HttpStatus.CREATED;
    	ShopFacade.ADD_SHOP_RESULT result;
		try {
			result = shopFacade.addShop(shopData);
			if(ShopFacade.ADD_SHOP_RESULT.NOT_FOUND == result){
	    		httpResult = HttpStatus.CREATED;
	    	}
		} catch (IOException e) {
			httpResult = HttpStatus.INTERNAL_SERVER_ERROR;
		}
    	
    	
    	return new ResponseEntity<String>(httpResult);
    }
   
    /**
     * Test url : http://localhost:9000/shops?latitude=1&longitude=1
     * @param latitude
     * @param longitude
     * @return
     */
   
    @RequestMapping(path = "/shops",method= RequestMethod.GET )
    public ResponseEntity<List<Shop>> shopLocator(@RequestParam(value="latitude") String latitude, @RequestParam(value="longitude") String longitude){
       List<Shop> shops = shopFacade.findClosestShop(latitude, longitude);
       if(shops.size()==0){
    	   return new ResponseEntity<List<Shop>>(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<List<Shop>>(shops, HttpStatus.OK);
    }

}


