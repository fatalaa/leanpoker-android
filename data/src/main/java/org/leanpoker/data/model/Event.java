package org.leanpoker.data.model;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by tbalogh on 06/09/15.
 */
public class Event {
	private final String      mEventId;
	private final String      mName;
	private final DateTime    mDateTime;
	private final Facilitator mFacilitator;
	private final Address     mAddress;
	private final EventStatus mEventStatus;
	private final int         mTeamCount;
	private final List<Photo> mPhotos;

	public Event(final String eventId, final String name, final DateTime dateTime,
	             final Facilitator facilitator, final Address address, final String eventStatus,
	             final int teamCount, final List<Photo> photos) {
		mEventId = eventId;
		mName = name;
		mDateTime = dateTime;
		mFacilitator = facilitator;
		mAddress = address;
		mEventStatus = EventStatus.valueOf(eventStatus.toUpperCase());
		mTeamCount = teamCount;
		mPhotos = photos;
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

	public Facilitator getFacilitator() {
		return mFacilitator;
	}

	public Address getAddress() {
		return mAddress;
	}

	public EventStatus getEventStatus() {
		return mEventStatus;
	}

	public List<Photo> getPhotos() {
		return mPhotos;
	}

	public int getTeamCount() {
		return mTeamCount;
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
