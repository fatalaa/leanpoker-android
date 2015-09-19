package org.leanpoker;

import org.leanpoker.data.model.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tbalogh on 16/09/15.
 */
public class EventsCache {
	private static EventsCache mInstance = new EventsCache();
	private HashMap<String, Event> mEventHashMap;
	private ValidationStrategy     mValidationStrategy;
	private boolean                mIsValid;

	public static EventsCache getInstance() {
		return mInstance;
	}

	private EventsCache() {
		mEventHashMap = new HashMap<>();
		mIsValid = false;
	}

	public void cacheEvents(final List<Event> events, final ValidationStrategy validationStrategy) {
		mEventHashMap = cacheAsMapWithIdKey(events);
		mValidationStrategy = validationStrategy;
		mIsValid = true;
	}

	private HashMap<String, Event> cacheAsMapWithIdKey(final List<Event> events) {
		final HashMap<String, Event> eventHashMap = new HashMap<>(events.size());
		for(final Event event : events) {
			if (event != null) {
				eventHashMap.put(event.getEventId(), event);
			}
		}
		return eventHashMap;
	}

	public boolean isValid() {
		return mIsValid;
	}

	public List<Event> getEvents() {
		if (ValidationStrategy.ALWAYS_INVALIDATE.equals(mValidationStrategy)) {
			mIsValid = false;
		}
		return new ArrayList<>(mEventHashMap.values());
	}

	public Event getEvent(final String eventId) {
		if (eventId == null || eventId.isEmpty()) {
			throw new RuntimeException("Trying to retrieve item with null id!");
		}
		return mEventHashMap.get(eventId);
	}

	public enum ValidationStrategy {
		NEVER_INVALIDATE,
		ALWAYS_INVALIDATE
	}
}
