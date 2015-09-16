package org.leanpoker.domain.mock;

import org.leanpoker.data.model.Address;
import org.leanpoker.data.model.Date;
import org.leanpoker.data.model.Event;
import org.leanpoker.data.model.Facilitator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tbalogh on 06/09/15.
 */
public final class EventListProviderMock {
	private EventListProviderMock() {
		// Util class
	}

	public static List<Event> getMockEvents(final int eventCount) {
		final List<Event> events = new ArrayList<>();
		for (int i = 0; i < eventCount; i++) {
			final Event event = new Event(getMockId(i), getMockName(i), getMockDate(i), getMockFacilitator(i),
			                              getMockAddress(i), getMockEventStatus(i));
			events.add(event);
		}
		return events;
	}

	private static String getMockEventStatus(final int i) {
		return "future";
	}

	private static String getMockId(final int i) {
		return String.valueOf(i);
	}

	private static String getMockName(final int i) {
		return "Geeks & Greeks";
	}

	private static Date getMockDate(final int i) {
		return new Date(getMockDay(), getMockMonth(), getMockYear());
	}

	private static String getMockDay() {
		return "27";
	}

	private static String getMockMonth() {
		return "August";
	}

	private static String getMockYear() {
		return "2015";
	}

	private static Facilitator getMockFacilitator(final int i) {
		return new Facilitator("DevIll");
	}

	private static Address getMockAddress(final int i) {
		return new Address("Budapest");
	}
}
