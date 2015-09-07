package org.leanpoker.domain.interactor;

import org.leanpoker.data.Event;
import org.leanpoker.domain.mock.EventListProviderMock;

import java.util.List;

/**
 * Created by tbalogh on 06/09/15.
 */
public class EventListInteractor extends Interactor {
	public List<Event> loadEvents() {
		throw new RuntimeException("Not implemeneted yet");
	}

	public List<Event> loadMockedEvents(final int eventCount) {
		return EventListProviderMock.getMockEvents(eventCount);
	}
}
