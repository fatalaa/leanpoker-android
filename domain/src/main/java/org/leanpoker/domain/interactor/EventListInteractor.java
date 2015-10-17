package org.leanpoker.domain.interactor;

import org.leanpoker.api.NetworkManager;

import rx.Observable;
/**
 * Created by tbalogh on 06/09/15.
 */

public class EventListInteractor extends BaseInteractor {

	public EventListInteractor() {
		super();
	}

	@Override
	protected Observable buildInteractorObservable() {
		return NetworkManager.getInstance().getEvents();
	}
}
