package hello;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="shop")
public class Shop {

	@JsonProperty(value="shopName")
	protected String name;
	@JsonProperty(value="shopAddress.number")
	protected String number;
	@JsonProperty(value="shopAddress.postcode")
	protected String postcode;
	@JsonProperty(value="latitude")
	protected String latitude;
	@JsonProperty(value="longitude")
	protected String longitude;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
