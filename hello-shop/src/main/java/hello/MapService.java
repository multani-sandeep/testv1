package hello;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.vividsolutions.jts.geom.Coordinate;

@Service
public class MapService {
	
	@Value("${config.gmaps.apikey}")
	protected String gmapsApiKey;

	public Coordinate findNameAndAddressMatch(String name, String addressNumber, String addressPostcode)
			throws IOException {
		GeoApiContext context = new GeoApiContext().setApiKey(gmapsApiKey);
		GeocodingResult[] results;
		try {
			results = GeocodingApi.geocode(context, name + "," + addressNumber + "," + addressPostcode).await();
			if (results == null || results.length < 1) {
				return null;
			}
			return new Coordinate(results[0].geometry.location.lat, results[0].geometry.location.lng);
		} catch (Exception e) {
			throw new IOException("Generic exception raised by geocoding api", e);
		}
	}
}
