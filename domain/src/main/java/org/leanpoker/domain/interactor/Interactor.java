package org.leanpoker.domain.interactor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by tbalogh on 06/09/15.
 */

/*
Abstract base class of Interactors. Interactors are not generalized yet but they could be later.
 */
public abstract class Interactor {

	private Subscription mSubscription;

	protected Interactor() {
		mSubscription = Subscriptions.empty();
	}

	public void execute(final Subscriber UseCaseSubscriber) {
		mSubscription = this.buildInteractorObservable().subscribeOn(Schedulers.io()).observeOn(
				AndroidSchedulers.mainThread()).subscribe(UseCaseSubscriber);
	}

	public void unsubscribe() {
		if (!mSubscription.isUnsubscribed()) {
			mSubscription.unsubscribe();
		}
	}

	protected abstract Observable buildInteractorObservable();
}
