package org.leanpoker.leanpokerandroid.model;


import org.leanpoker.data.model.Address;
import org.leanpoker.data.model.Date;
import org.leanpoker.data.model.Event;
import org.leanpoker.data.model.Event.EventStatus;
import org.leanpoker.data.model.Facilitator;

/**
 * Created by tbalogh on 06/09/15.
 */
public class EventModel {
	private final String      mName;
	private final Date        mDate;
	private final Facilitator mFacilitator;
	private final Address     mAddress;
	private final EventStatus mEventStatus;

	public EventModel(final Event event) {
		mName = event.getName();
		mDate = event.getDate();
		mFacilitator = event.getFacilitator();
		mAddress = event.getAddress();
		mEventStatus = event.getEventStatus();
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

	public EventStatus getEventStatus() {
		return mEventStatus;
	}
}
