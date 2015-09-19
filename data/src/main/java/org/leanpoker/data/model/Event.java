package org.leanpoker.data.model;

/**
 * Created by tbalogh on 06/09/15.
 */
public class Event {
	private final String      mEventId;
	private final String      mName;
	private final Date        mDate;
	private final Facilitator mFacilitator;
	private final Address     mAddress;
	private final EventStatus mEventStatus;

	public Event(final String eventId, final String name, final Date date,
	             final Facilitator facilitator, final Address address, final String eventStatus) {
		mEventId = eventId;
		mName = name;
		mDate = date;
		mFacilitator = facilitator;
		mAddress = address;
		mEventStatus = EventStatus.valueOf(eventStatus.toUpperCase());
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

	public enum EventStatus {
		FUTURE("future"),
		LIVE("live"),
		ENDED("ended"),
		STAND_BY("stand_by"),
		RUNNING("running");

		private final String mValue;

		EventStatus(final String value) {
			mValue = value;
		}
	}
}
