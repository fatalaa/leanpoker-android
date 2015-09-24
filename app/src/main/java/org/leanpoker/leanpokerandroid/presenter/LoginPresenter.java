package org.leanpoker.leanpokerandroid.presenter;

import android.util.Log;

import org.leanpoker.data.model.AccessToken;
import org.leanpoker.data.store.TokenStore;
import org.leanpoker.domain.interactor.LoginInteractor;
import org.leanpoker.leanpokerandroid.view.LoginView;

import rx.Subscriber;

/**
 * Created by tmolnar on 18/09/15.
 */
public class LoginPresenter implements Presenter {

    private LoginInteractor mInteractor;
    private LoginView mLoginView;

    public LoginPresenter(LoginView loginView) {
        mInteractor = new LoginInteractor();
        mLoginView = loginView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    final class LoginSubscriber extends Subscriber<AccessToken> {

        @Override
        public void onCompleted() {
            Log.i("ONCOMPLETE", "ASD");
        }

        @Override
        public void onError(final Throwable e) {
            Log.e("ASD", e.getMessage());
        }

        @Override
        public void onNext(final AccessToken accessToken) {
            TokenStore.getInstance().setAccessToken(accessToken);
            mLoginView.navigateToPreviousActivity();
        }
    }

    public void delegateTokenRequest(String accessCode, String state) {
        mInteractor.setUpCredentials(accessCode, state);
        mInteractor.execute(new LoginSubscriber());
    }
}
