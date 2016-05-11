package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vividsolutions.jts.geom.Coordinate;

@Controller
@RequestMapping("/shops")
public class ShopsController {

    DefaultGeographicCRS default_crs = DefaultGeographicCRS.WGS84;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

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

}