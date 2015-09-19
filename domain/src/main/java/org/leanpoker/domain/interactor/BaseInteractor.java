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
public abstract class BaseInteractor {

	private Subscription mSubscription;

	protected BaseInteractor() {
		mSubscription = Subscriptions.empty();
	}

	public void execute(final Subscriber useCaseSubscriber) {
		mSubscription = this.buildInteractorObservable()
		                    .subscribeOn(Schedulers.io())
		                    .observeOn(AndroidSchedulers.mainThread())
		                    .subscribe(useCaseSubscriber);
	}

	public void unsubscribe() {
		if (!mSubscription.isUnsubscribed()) {
			mSubscription.unsubscribe();
		}
	}

	protected abstract Observable buildInteractorObservable();
}
