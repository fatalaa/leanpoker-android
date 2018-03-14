package org.leanpoker.domain.interactor;

import org.leanpoker.api.NetworkManager;

import io.reactivex.Observable;

/**
 * Created by tbalogh on 19/09/15.
 */
public class EventDetailsInteractor extends BaseInteractor {

	private final String mEventId;

	public EventDetailsInteractor(final String eventId) {
		super();
		mEventId = eventId;
	}

	@Override
	protected Observable buildInteractorObservable() {
		return NetworkManager.getInstance().getEvent(mEventId);
	}
}
