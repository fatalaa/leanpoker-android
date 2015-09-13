package org.leanpoker.data.model;

/**
 * Created by tbalogh on 06/09/15.
 */
public class Address {
	private final String mCity;

	public Address(final String city) {
		mCity = city;
	}

	public String getCity() {
		return mCity;
	}

	@Override
	public String toString() {
		return mCity;
	}
}
