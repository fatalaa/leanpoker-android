package org.leanpoker.leanpokerandroid.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.leanpoker.data.store.MiscStorage;
import org.leanpoker.leanpokerandroid.P;
import org.leanpoker.leanpokerandroid.R;
import org.leanpoker.leanpokerandroid.model.PhotoModel;
import org.leanpoker.leanpokerandroid.presenter.EventPhotoGridPresenter;
import org.leanpoker.leanpokerandroid.view.EventPhotoGridView;
import org.leanpoker.leanpokerandroid.view.adapter.EventPhotoGridAdapter;
import org.leanpoker.leanpokerandroid.view.adapter.EventPhotoGridLayoutManager;
import org.leanpoker.leanpokerandroid.view.dialog.ChoosePhotoAppDialog;
import org.leanpoker.leanpokerandroid.view.dialog.ChoosePhotoAppDialog.ChoosePhotoAppDialogListener;
import org.leanpoker.leanpokerandroid.view.dialog.LoginDialog;
import org.leanpoker.leanpokerandroid.view.dialog.LoginDialog.LoginDialogListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tmolnar on 21/09/15.
 */
public class EventPhotoGridFragment extends BaseFragment implements EventPhotoGridView,
        FloatingActionButton.OnClickListener,
		LoginDialogListener,
        ChoosePhotoAppDialogListener,
        EventPhotoGridAdapter.OnPhotoClickListener {

    private static final int ADAPTER_COLUMN_COUNT = 2;
    private static final String TAG = EventPhotoGridFragment.class.getSimpleName();

    private EventPhotoGridLayoutManager mEventPhotoGridLayoutManager;
    private EventPhotoGridPresenter     mEventPhotoGridPresenter;
    private EventPhotoGridAdapter       mEventPhotoGridAdapter;
    private String                      mEventId;

    public static final int REQUEST_IMAGE_CAPTURE   = 1;
    public static final int REQUEST_GALLERY_CAPTURE = 2;

    @Bind(R.id.recyclerview_photos)
    RecyclerView mPhotoGridRecyclerView;

    @Bind(R.id.add_photos_fab)
    FloatingActionButton mUploadPhotosButton;

    public EventPhotoGridFragment() {
    }

    public void setEventId(final String eventId) {
        this.mEventId = eventId;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_photo_grid, container, false);
        ButterKnife.bind(this, fragmentView);
        this.setupUI();
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
        this.loadPhotos();
    }

    @Override
    public void onResume() {
        super.onResume();
        mEventPhotoGridPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mEventPhotoGridPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mEventPhotoGridPresenter.destroy();
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_GALLERY_CAPTURE:
                    mEventPhotoGridPresenter.delegatePhotoUpload(data.getData());
                    break;
                case REQUEST_IMAGE_CAPTURE:
                    mEventPhotoGridPresenter.delegateCameraBackedPhotoUpload(
                            (Uri) MiscStorage.getInstance().get(P.Common.CAMERA_IMAGE_URI_KEY)
                    );
                    break;
                default:
                    mEventPhotoGridPresenter.delegateGithubUserFetch();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initialize() {
        mEventPhotoGridPresenter = new EventPhotoGridPresenter(mEventId);
        mEventPhotoGridPresenter.setEventPhotoGridView(this);
	    mUploadPhotosButton.setOnClickListener(this);
        mEventPhotoGridAdapter.setOnPhotoClickListener(this);
    }

    private void setupUI() {
        mEventPhotoGridLayoutManager = new EventPhotoGridLayoutManager(
                getActivity(),
                ADAPTER_COLUMN_COUNT
        );
        mPhotoGridRecyclerView.setLayoutManager(mEventPhotoGridLayoutManager);

        mEventPhotoGridAdapter = new EventPhotoGridAdapter(
                getActivity(),
                new ArrayList<PhotoModel>()
        );
        mPhotoGridRecyclerView.setAdapter(mEventPhotoGridAdapter);
    }

    private void loadPhotos() {
        mEventPhotoGridPresenter.getPhotosGrid();
    }

    @Override
    public void renderPhotoList(final List<PhotoModel> photoModelList) {
        mEventPhotoGridAdapter.setPhotoModels(photoModelList);
    }

    @Override
    public void showChoosePhotoAppDialog() {
	    // Todo
        final ChoosePhotoAppDialog choosePhotoAppDialog = new ChoosePhotoAppDialog();
        choosePhotoAppDialog.setListener(this);
        choosePhotoAppDialog.show(getActivity().getSupportFragmentManager(), "Lofasz");
    }

    @Override
    public void showLoginDialog() {
	    final LoginDialog loginDialog = new LoginDialog();
	    loginDialog.setLoginDialogListener(this);
	    loginDialog.show(getActivity().getFragmentManager(), "Lofasz");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoadingError(final String message) {
        super.showToastMessage(message);
    }

    @Override
    public void onClick(final View v) {
        mEventPhotoGridPresenter.uploadPhotos();
    }

	@Override
	public void onSignUpClicked() {
		mEventPhotoGridPresenter.navigateToLoginActivity();
	}

    public static EventPhotoGridFragment newInstance(final String eventId) {
        EventPhotoGridFragment eventPhotoGridFragment = new EventPhotoGridFragment();
        eventPhotoGridFragment.setEventId(eventId);
        return eventPhotoGridFragment;
    }

    @Override
    public void onClick(final ChoosePhotoAppDialog.PhotoAppType appType) {
        mEventPhotoGridPresenter.navigateToApp(getActivity(), appType);
    }

    @Override
    public void onPhotoClick(final int clickedPhotoIndex) {
        mEventPhotoGridPresenter.navigateToFullScreenPhotoActivity(clickedPhotoIndex);
    }
}
