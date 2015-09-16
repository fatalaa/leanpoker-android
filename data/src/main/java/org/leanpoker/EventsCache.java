package org.leanpoker;

import org.leanpoker.data.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tbalogh on 16/09/15.
 */
public class EventsCache {
	private static EventsCache mInstance = new EventsCache();
	private List<Event>        mEvents;
	private ValidationStrategy mValidationStrategy;
	private boolean            mIsValid;

	public static EventsCache getInstance() {
		return mInstance;
	}

	private EventsCache() {
		mEvents = new ArrayList<>();
		mIsValid = false;
	}

	public void cacheEvents(final List<Event> events, final ValidationStrategy validationStrategy) {
		mEvents = events;
		mValidationStrategy = validationStrategy;
		mIsValid = true;
	}

	public boolean isValid() {
		return mIsValid;
	}

	public List<Event> getEvents() {
		if (ValidationStrategy.ALWAYS_INVALIDATE.equals(mValidationStrategy)) {
			mIsValid = false;
		}
		return mEvents;
	}

	public enum ValidationStrategy {
		NEVER_INVALIDATE,
		ALWAYS_INVALIDATE
	}
}
