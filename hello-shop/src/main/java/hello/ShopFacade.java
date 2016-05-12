package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vividsolutions.jts.geom.Coordinate;

@Service
public class ShopFacade {

	public static enum ADD_SHOP_RESULT {
		NOT_FOUND, ADDED, ERROR
	};
	
	private static List<Shop> shops = (List<Shop>) Collections.synchronizedList(new ArrayList<Shop>());

	@Autowired
	protected MapService mapService;

	public ADD_SHOP_RESULT addShop(Shop input) throws IOException {
		Coordinate result = mapService.findNameAndAddressMatch(input.getName(), input.getNumber(), input.getPostcode());
		if (result == null) {
			return ADD_SHOP_RESULT.NOT_FOUND;
		}
		input.setLatitude(String.valueOf(result.getOrdinate(Coordinate.X)));
		input.setLongitude(String.valueOf(result.getOrdinate(Coordinate.Y)));
		shops.add(input);
		return ADD_SHOP_RESULT.ADDED;
	}

	public List<Shop> findClosestShop(String latitude, String longitude) {
		List<Shop> results = new ArrayList<Shop>();
		Coordinate shopcoord, custcoord;
		double custLatitude = Double.parseDouble(latitude);
		double custLongitude = Double.parseDouble(longitude);
		custcoord = new Coordinate(custLatitude, custLongitude);
		Shop retShop = null;
		double mindist = -1;

		for (Shop shop : shops) {
			double lat = Double.parseDouble(shop.getLatitude());
			double lng = Double.parseDouble(shop.getLongitude());
			shopcoord = new Coordinate(lat, lng);

			double distance = custcoord.distance(shopcoord);
			if (mindist==-1 || mindist > distance) {
				mindist = distance;
				retShop = shop;
			}
		}
		if (retShop != null) {
			results.add(retShop);
			System.out.println(retShop.getNumber() + ", " + retShop.getName() + ", " + retShop.getPostcode());
		}
		
		return results;
	}

}
