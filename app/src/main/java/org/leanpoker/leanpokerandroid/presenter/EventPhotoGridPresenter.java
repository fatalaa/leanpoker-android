package org.leanpoker.leanpokerandroid.presenter;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;

import org.leanpoker.data.model.GithubUser;
import org.leanpoker.data.model.Photo;
import org.leanpoker.data.store.MiscStorage;
import org.leanpoker.data.store.UserStore;
import org.leanpoker.domain.interactor.EventPhotoGridInteractor;
import org.leanpoker.domain.interactor.GithubUserEmailsInteractor;
import org.leanpoker.domain.interactor.GithubUserInteractor;
import org.leanpoker.domain.interactor.ImageUploadInteractor;
import org.leanpoker.domain.interactor.IsUserLoggedInInteractor;
import org.leanpoker.leanpokerandroid.P;
import org.leanpoker.leanpokerandroid.model.PhotoModel;
import org.leanpoker.leanpokerandroid.modelmapper.EventPhotoGridDataMapper;
import org.leanpoker.leanpokerandroid.navigator.Navigator;
import org.leanpoker.leanpokerandroid.util.GraphicsUtil;
import org.leanpoker.leanpokerandroid.view.EventPhotoGridView;
import org.leanpoker.leanpokerandroid.view.dialog.ChoosePhotoAppDialog;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by tmolnar on 21/09/15.
 */
public class EventPhotoGridPresenter implements Presenter {

	private final EventPhotoGridInteractor   mEventPhotoGridInteractor;
	private final IsUserLoggedInInteractor   mIsUserLoggedInInteractor;
	private final GithubUserInteractor       mGithubUserInteractor;
	private final GithubUserEmailsInteractor mGithubUserEmailsInteractor;
	private final EventPhotoGridDataMapper   mEventPhotoGridDataMapper;
	private       EventPhotoGridView         mEventPhotoGridView;
	private       ArrayList<PhotoModel>      mPhotoModels;
	private final String                     mEventId;

	private Uri mPhotoUri;

	public EventPhotoGridPresenter(final String eventId) {
		mEventId = eventId;
		mEventPhotoGridInteractor = new EventPhotoGridInteractor(mEventId);
		mEventPhotoGridDataMapper = new EventPhotoGridDataMapper();
		mIsUserLoggedInInteractor = new IsUserLoggedInInteractor();
		mGithubUserInteractor = new GithubUserInteractor();
		mGithubUserEmailsInteractor = new GithubUserEmailsInteractor();
	}

	public void setEventPhotoGridView(final EventPhotoGridView eventPhotoGridView) {
		mEventPhotoGridView = eventPhotoGridView;
	}

	public Uri getPhotoUri() {
		return mPhotoUri;
	}

	public void uploadPhotos() {
		mIsUserLoggedInInteractor.execute(new IsUserLoggedInSubscriber());
	}

	public void navigateToLoginActivity() {
		Navigator.getInstance().navigateToLoginActivity(mEventPhotoGridView.getContext());
	}

	public void navigateToFullScreenPhotoActivity(final int clickedPhotoIndex) {
		Navigator.getInstance().navigateToFullScreenPhotoActivity(mEventPhotoGridView.getContext(),
		                                                          mPhotoModels, clickedPhotoIndex);
	}

	public void navigateToApp(final Activity activity,
	                          final ChoosePhotoAppDialog.PhotoAppType appType) {
		if (appType == ChoosePhotoAppDialog.PhotoAppType.GALLERY) {
			Navigator.getInstance().navigateToGalleryApp(activity);
		} else {
			//TODO refactor this to somewhere else
			// Temporary change: Save the uri always at the presenter and start the camera app with this uri.
			// Expected: There is no chance the user can interact with the fragment again before the fragments onActivityResult method will call.
			//           So the mPhotoUri value will be always valid.
			// The problem with the MiscStore is the following:
			// 1. One photo taken requested -> put a uri in the MiscStore
			// 2. Photo has been taken and we start uploading.
			// 3. Another photo request taken before the photo upload request served -> put another uri in the MiscStore
			// 4. While we are taking our second picture the first photo upload request served -> remove uri from MiscStore
			// 5. We taken the second photo, activity is trying to access the uri but it has been removed in the 4. step
			mPhotoUri = GraphicsUtil.createImageUri();
			//MiscStorage.getInstance().put(P.Common.CAMERA_IMAGE_URI_KEY, uri);
			Navigator.getInstance().navigateToCameraApp(activity, mPhotoUri);
		}
	}

	public void delegateGithubUserFetch() {
		mGithubUserInteractor.execute(new GithubUserSubscriber());
	}

	public void delegatePhotoUpload(final Uri uri) {
		ImageUploadInteractor uploadInteractor = new ImageUploadInteractor(
				mEventPhotoGridView.getContext(), uri, mEventId, false);
		uploadInteractor.execute(new ImageUploadSubscriber());
	}

	public void delegateCameraBackedPhotoUpload(final Uri uri) {
		ImageUploadInteractor uploadInteractor = new ImageUploadInteractor(
				mEventPhotoGridView.getContext(), uri, mEventId, true);
		uploadInteractor.execute(new ImageUploadSubscriber());
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
		mGithubUserEmailsInteractor.unsubscribe();
	}

	private void showPhotos(List<Photo> photos) {
		mPhotoModels = (ArrayList<PhotoModel>) mEventPhotoGridDataMapper.transform(photos);
		mEventPhotoGridView.renderPhotoList(mPhotoModels);
	}

	private void hideViewLoading() {
		mEventPhotoGridView.hideLoading();
	}

	private void showViewLoading() {
		mEventPhotoGridView.showLoading();
	}

	private void showError() {
		mEventPhotoGridView.showLoadingError("Photos cannot be loaded");
	}

	final class ImageUploadSubscriber extends Subscriber<Boolean> {

		private final String TAG = ImageUploadSubscriber.class.getSimpleName();

		@Override
		public void onCompleted() {
			Log.d(TAG, "COMPLETED");
		}

		@Override
		public void onError(final Throwable e) {
			Log.e(TAG, "exception", e);
			throw new RuntimeException(e);
		}

		@Override
		public void onNext(final Boolean success) {
			if (success) {
				mEventPhotoGridView.showLoadingError("Sucessfully uploaded image");
			} else {
				mEventPhotoGridView.showLoadingError("Failed to upload image");
			}
			MiscStorage.getInstance().remove(P.Common.CAMERA_IMAGE_URI_KEY);
		}
	}

	final class GithubUserSubscriber extends Subscriber<GithubUser> {

		@Override
		public void onCompleted() {

		}

		@Override
		public void onError(final Throwable e) {
			mEventPhotoGridView.showLoadingError(e.getMessage());
		}

		@Override
		public void onNext(final GithubUser githubUser) {
			UserStore.getInstance().setUser(githubUser);
			mEventPhotoGridView.showChoosePhotoAppDialog();
		}
	}

	final class IsUserLoggedInSubscriber extends Subscriber<Boolean> {
		@Override
		public void onCompleted() {

		}

		@Override
		public void onError(final Throwable e) {
			mEventPhotoGridView.showLoadingError(e.getMessage());
		}

		@Override
		public void onNext(final Boolean isLoggedIn) {
			if (isLoggedIn) {
				GithubUser loggedInUser = UserStore.getInstance().getUser();
				if (loggedInUser == null) {
					EventPhotoGridPresenter.this.mGithubUserInteractor.execute(
							new GithubUserSubscriber());
				} else {
					mEventPhotoGridView.showChoosePhotoAppDialog();
				}
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

}
