package org.leanpoker.leanpokerandroid.model;


import org.leanpoker.data.Address;
import org.leanpoker.data.Date;
import org.leanpoker.data.Event;
import org.leanpoker.data.Facilitator;

/**
 * Created by tbalogh on 06/09/15.
 */
public class EventModel {
	private final String      mName;
	private final Date mDate;
	private final Facilitator mFacilitator;
	private final Address mAddress;

	public EventModel(final Event event) {
		mName = event.getName();
		mDate = event.getDate();
		mFacilitator = event.getFacilitator();
		mAddress = event.getAddress();
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
