package org.leanpoker.leanpokerandroid.presenter;

import org.leanpoker.data.model.Photo;
import org.leanpoker.domain.interactor.EventPhotoGridInteractor;
import org.leanpoker.domain.interactor.IsUserLoggedInInteractor;
import org.leanpoker.leanpokerandroid.modelmapper.EventPhotoGridDataMapper;
import org.leanpoker.leanpokerandroid.navigator.Navigator;
import org.leanpoker.leanpokerandroid.view.EventPhotoGridView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by tmolnar on 21/09/15.
 */
public class EventPhotoGridPresenter implements Presenter {

	private final EventPhotoGridInteractor mEventPhotoGridInteractor;
	private final IsUserLoggedInInteractor mIsUserLoggedInInteractor;
	private final EventPhotoGridDataMapper mEventPhotoGridDataMapper;
	private       EventPhotoGridView       mEventPhotoGridView;

	public EventPhotoGridPresenter(final String eventId) {
		mEventPhotoGridInteractor = new EventPhotoGridInteractor(eventId);
		mEventPhotoGridDataMapper = new EventPhotoGridDataMapper();
		mIsUserLoggedInInteractor = new IsUserLoggedInInteractor();
	}

	public void setEventPhotoGridView(final EventPhotoGridView eventPhotoGridView) {
		mEventPhotoGridView = eventPhotoGridView;
	}

	public void initialize() {

	}

	public void getPhotosGrid() {
		mEventPhotoGridInteractor.execute(new EventPhotoGridSubscriber());
	}

	@Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mEventPhotoGridInteractor.unsubscribe();
    }

	public void uploadPhotos() {
		mIsUserLoggedInInteractor.execute(new IsUserLoggedInSubscriber());
	}

	public void navigateToLoginActivity() {
		Navigator.getInstance().navigateToLoginActivity(mEventPhotoGridView.getContext());
	}

	final class IsUserLoggedInSubscriber extends Subscriber<Boolean> {
		@Override
		public void onCompleted() {

		}

		@Override
		public void onError(final Throwable e) {
			mEventPhotoGridView.showError(e.getMessage());
		}

		@Override
		public void onNext(final Boolean isLoggedIn) {
			if (isLoggedIn) {
				mEventPhotoGridView.showChoosePhotoAppDialog();
			} else {
				mEventPhotoGridView.showLoginDialog();
			}
		}
	}

	final class EventPhotoGridSubscriber extends Subscriber<List<Photo>> {

        @Override
        public void onCompleted() {
            EventPhotoGridPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(final Throwable e) {
            EventPhotoGridPresenter.this.hideViewLoading();
            EventPhotoGridPresenter.this.showError();
        }

        @Override
        public void onNext(final List<Photo> photos) {
            EventPhotoGridPresenter.this.showPhotos(photos);
        }
    }

    private void showPhotos(List<Photo> photos) {
        mEventPhotoGridView.renderPhotoList(mEventPhotoGridDataMapper.transform(photos));
    }

    private void hideViewLoading() {
        mEventPhotoGridView.hideLoading();
    }

    private void showViewLoading() {
        mEventPhotoGridView.showLoading();
    }

    private void showError() {
        mEventPhotoGridView.showError("Photos cannot be loaded");
    }
}
