package org.leanpoker.domain.interactor;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tbalogh on 06/09/15.
 */

/*
Abstract base class of Interactors. Interactors are not generalized yet but they could be later.
 */
public abstract class BaseInteractor {

	private CompositeDisposable mSubscription;

	protected BaseInteractor() {
		mSubscription = new CompositeDisposable();
	}

	public void execute(final Observer useCaseSubscriber) {

		Observable observable = this.buildInteractorObservable()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());

		mSubscription.add((Disposable) observable.subscribeWith(useCaseSubscriber));
	}

	public void unsubscribe() {
		if (!mSubscription.isDisposed()) {
			mSubscription.dispose();
		}
	}

	protected abstract Observable buildInteractorObservable();
}
