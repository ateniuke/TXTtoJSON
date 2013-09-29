package jurgita.txttojson;

public class Country{
	private String countryname;
	private String isocode;
	
	public Country (String iso_code, String country_name){
		super();
		this.countryname = country_name;
		this.isocode = iso_code;
	}
	
	public void setCountry_name(String country_name) {
		this.countryname = country_name;
	}
	public String getCountryname() {
		return countryname;
	}
	
	public void setIsocode(String isocode) {
	    this.isocode = isocode;
	}
	public String getIsocode() {
	    return isocode;
	}
}


