package org.leanpoker.domain.interactor;

import org.leanpoker.UserManager;

import rx.Observable;

/**
 * Created by tbalogh on 23/09/15.
 */
public class IsUserLoggedInInteractor extends BaseInteractor {
	public IsUserLoggedInInteractor() {
		super();
	}

	@Override
	protected Observable buildInteractorObservable() {
		return UserManager.getInstance().isUserLoggedIn();
	}
}
