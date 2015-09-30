package org.leanpoker;

import android.content.Context;

import org.leanpoker.data.model.AccessToken;
import org.leanpoker.data.store.TokenStore;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

/**
 * Created by tbalogh on 23/09/15.
 */
public class UserManager {
	private static UserManager ourInstance = new UserManager();
	private Context mContext;

	public static UserManager getInstance() {
		return ourInstance;
	}

	private UserManager() {
	}

	public void init(final Context context) {
		mContext = context;
	}

	public Observable<Boolean> isUserLoggedIn() {
		Observable<Boolean> userLoggedInObservable = Observable.create(new OnSubscribe<Boolean>() {
			@Override
			public void call(final Subscriber<? super Boolean> subscriber) {
				AccessToken token = TokenStore.getInstance().getAccessToken();
				subscriber.onNext(token != null);
			}
		});
		return userLoggedInObservable;
	}
}
