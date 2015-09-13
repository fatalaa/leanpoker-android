package org.leanpoker.domain.interactor;

import org.leanpoker.api.NetworkManager;
import org.leanpoker.data.model.Event;
import org.leanpoker.domain.mock.EventListProviderMock;

import java.util.List;

import rx.Observable;
/**
 * Created by tbalogh on 06/09/15.
 */

public class EventListInteractor extends Interactor {

	public List<Event> loadMockedEvents(final int eventCount) {
		return EventListProviderMock.getMockEvents(eventCount);
	}

	@Override
	protected Observable buildInteractorObservable() {
		return NetworkManager.getInstance().events();
	}
}
