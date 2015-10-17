package org.leanpoker.leanpokerandroid.model;


import org.joda.time.DateTime;
import org.leanpoker.data.model.Address;
import org.leanpoker.data.model.Event;
import org.leanpoker.data.model.Event.EventStatus;
import org.leanpoker.data.model.Facilitator;

/**
 * Created by tbalogh on 06/09/15.
 */
public class EventModel {
	private final String      mEventId;
	private final String      mName;
	private final DateTime    mDateTime;
	private final Facilitator mFacilitator;
	private final Address     mAddress;
	private final EventStatus mEventStatus;

	public EventModel(final Event event) {
		mEventId = event.getEventId();
		mName = event.getName();
		mDateTime = event.getDateTime();
		mFacilitator = event.getFacilitator();
		mAddress = event.getAddress();
		mEventStatus = event.getEventStatus();
	}

	public String getEventId() {
		return mEventId;
	}

	public String getName() {
		return mName;
	}

	public DateTime getDateTime() {
		return mDateTime;
	}

	public String getDay() {
		return String.valueOf(mDateTime.getDayOfMonth());
	}

	public String getMonth() {
		return String.valueOf(mDateTime.getMonthOfYear());
	}

	public String getYear() {
		return String.valueOf(mDateTime.getYear());
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
