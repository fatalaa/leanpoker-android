package org.leanpoker.domain.interactor;

import org.leanpoker.api.NetworkManager;

import rx.Observable;

/**
 * Created by tbalogh on 19/09/15.
 */
public class EventWithDetailsInteractor extends BaseInteractor {

	private final String mEventId;

	public EventWithDetailsInteractor(final String eventId) {
		super();
		mEventId = eventId;
	}

	@Override
	protected Observable buildInteractorObservable() {
		return NetworkManager.getInstance().event(mEventId);
	}
}
