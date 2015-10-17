package org.leanpoker.data;

import org.joda.time.DateTime;
import org.leanpoker.data.model.Address;
import org.leanpoker.data.model.Facilitator;

/**
 * Created by tbalogh on 06/09/15.
 */
public class Event {
	private final long        mEventId;
	private final String      mName;
	private final DateTime    mDateTime;
	private final Facilitator mFacilitator;
	private final Address     mAddress;

	public Event(final long eventId, final String name, final DateTime dateTime,
	             final Facilitator facilitator, final Address address) {
		mEventId = eventId;
		mName = name;
		mDateTime = dateTime;
		mFacilitator = facilitator;
		mAddress = address;
	}

	public String getName() {
		return mName;
	}

	public DateTime getDateTime() {
		return mDateTime;
	}

	public Facilitator getFacilitator() {
		return mFacilitator;
	}

	public Address getAddress() {
		return mAddress;
	}
}
