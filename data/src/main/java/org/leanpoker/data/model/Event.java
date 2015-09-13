package org.leanpoker.data.model;

/**
 * Created by tbalogh on 06/09/15.
 */
public class Event {
	private final String                           mEventId;
	private final String                           mName;
	private final Date                             mDate;
	private final Facilitator                      mFacilitator;
	private final org.leanpoker.data.model.Address mAddress;

	public Event(final String eventId, final String name, final Date date,
	             final Facilitator facilitator, final org.leanpoker.data.model.Address address) {
		mEventId = eventId;
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

	public org.leanpoker.data.model.Address getAddress() {
		return mAddress;
	}
}
