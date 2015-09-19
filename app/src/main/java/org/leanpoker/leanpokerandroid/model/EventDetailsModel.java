package org.leanpoker.leanpokerandroid.model;

import org.leanpoker.data.model.Address;
import org.leanpoker.data.model.Date;
import org.leanpoker.data.model.Event.EventStatus;
import org.leanpoker.data.model.Facilitator;

/**
 * Created by tbalogh on 19/09/15.
 */
public class EventDetailsModel {

	private final String      mEventName;
	private final Address     mAddress;
	private final Date        mDate;
	private final EventStatus mEventStatus;
	private final Facilitator mFacilitator;

	public EventDetailsModel(final String eventName, final Address address, final Date date,
	                         final EventStatus eventStatus, final Facilitator facilitator) {
		mEventName = eventName;
		mAddress = address;
		mDate = date;
		mEventStatus = eventStatus;
		mFacilitator = facilitator;
	}

	public String getEventName() {
		return mEventName;
	}

	public Address getAddress() {
		return mAddress;
	}

	public Date getDate() {
		return mDate;
	}

	public EventStatus getEventStatus() {
		return mEventStatus;
	}

	public Facilitator getFacilitator() {
		return mFacilitator;
	}
}
