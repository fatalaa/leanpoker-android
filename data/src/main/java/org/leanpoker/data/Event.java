package org.leanpoker.data;

/**
 * Created by tbalogh on 06/09/15.
 */
public class Event {
	private final String mName;
	private final Date mDate;
	private final Facilitator mFacilitator;
	private final Address mAddress;

	public Event(final String name, final Date date, final Facilitator facilitator,
	             final Address address) {
		mName = name;
		mDate = date;
		mFacilitator = facilitator;
		mAddress = address;
	}

	public String getName() {
		return mName;
	}

	public Date getDate() {
		return mDate;
	}

	public Facilitator getFacilitator() {
		return mFacilitator;
	}

	public Address getAddress() {
		return mAddress;
	}
}
