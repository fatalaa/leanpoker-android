package org.leanpoker.leanpokerandroid.view.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.leanpoker.leanpokerandroid.R;
import org.leanpoker.leanpokerandroid.model.PhotoModel;
import org.leanpoker.leanpokerandroid.presenter.EventPhotoGridPresenter;
import org.leanpoker.leanpokerandroid.view.EventPhotoGridView;
import org.leanpoker.leanpokerandroid.view.adapter.EventPhotoGridAdapter;
import org.leanpoker.leanpokerandroid.view.adapter.EventPhotoGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tmolnar on 21/09/15.
 */
public class EventPhotoGridFragment extends BaseFragment implements EventPhotoGridView {

    private EventPhotoGridLayoutManager mEventPhotoGridLayoutManager;
    private EventPhotoGridPresenter     mEventPhotoGridPresenter;
    private EventPhotoGridAdapter       mEventPhotoGridAdapter;
    private String                      mEventId;

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

    private void initialize() {
        mEventPhotoGridPresenter = new EventPhotoGridPresenter(mEventId);
        mEventPhotoGridPresenter.setEventPhotoGridView(this);
    }

    private void setupUI() {
        mEventPhotoGridLayoutManager = new EventPhotoGridLayoutManager(getActivity(), 1);
        mPhotoGridRecyclerView.setLayoutManager(mEventPhotoGridLayoutManager);

        mEventPhotoGridAdapter = new EventPhotoGridAdapter(
                getActivity(),
                new ArrayList<PhotoModel>()
        );
        mPhotoGridRecyclerView.setAdapter(mEventPhotoGridAdapter);
        mPhotoGridRecyclerView.addItemDecoration(new ZeroSpaceItemDecoration());
    }

    private void loadPhotos() {
        mEventPhotoGridPresenter.getPhotosGrid();
    }

    @Override
    public void renderPhotoList(final List<PhotoModel> photoModelList) {
        mEventPhotoGridAdapter.setPhotoModels(photoModelList);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(final String message) {

    }

    class ZeroSpaceItemDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(final Rect outRect, final View view, final RecyclerView parent, final RecyclerView.State state) {
            outRect.left = 0;
            outRect.right = 0;
            outRect.bottom = 0;

            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = 0;
            }
        }
    }

    public static EventPhotoGridFragment newInstance(final String eventId) {
        EventPhotoGridFragment eventPhotoGridFragment = new EventPhotoGridFragment();
        eventPhotoGridFragment.setEventId(eventId);
        return eventPhotoGridFragment;
    }
}
