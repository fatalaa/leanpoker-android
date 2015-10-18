package org.leanpoker.leanpokerandroid.model;

import java.util.Comparator;

/**
 * Created by tbalogh on 18/10/15.
 */
public class EventModelComparator implements Comparator<EventModel> {
	@Override
	public int compare(final EventModel lhs, final EventModel rhs) {
		return rhs.getDateTime().compareTo(lhs.getDateTime());
	}
}
