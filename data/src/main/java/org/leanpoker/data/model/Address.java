package org.leanpoker.data.model;

/**
 * Created by tbalogh on 06/09/15.
 */
public class Address {
	private final String mAddress;
	private final String mCountry;
	private final String mCity;
	private final String mLatitude;
	private final String mLongitude;

	public Address(final String address, final String country, final String city, final String latitude, final String longitude) {
		mAddress = address;
		mCountry = country;
		mCity = city;
		mLatitude = latitude;
		mLongitude = longitude;
	}

	public String getAddress() {
		return mAddress;
	}

	public String getCountry() {
		return mCountry;
	}

	public String getCity() {
		return mCity;
	}

	public String getLatitude() {
		return mLatitude;
	}

	public String getLongitude() {
		return mLongitude;
	}
}
