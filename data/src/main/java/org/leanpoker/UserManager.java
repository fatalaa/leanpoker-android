package org.leanpoker;

import android.content.Context;

import org.leanpoker.data.model.AccessToken;
import org.leanpoker.data.store.TokenStore;
import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

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
		Observable<Boolean> userLoggedInObservable = Observable.create(new ObservableOnSubscribe<Boolean>() {
			@Override
			public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
				AccessToken token = TokenStore.getInstance().getAccessToken();
				emitter.onNext(token != null);
			}
		});
		return userLoggedInObservable;
	}
}
