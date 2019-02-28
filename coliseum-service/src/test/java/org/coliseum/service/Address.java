package org.coliseum.service;

public class Address {

	private Long id;

	private String street;

	private String city;

	private String district;

	private String state;

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", district=" + district + ", state="
				+ state + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}