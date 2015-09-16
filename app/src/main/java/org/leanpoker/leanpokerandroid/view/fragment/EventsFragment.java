package org.leanpoker.leanpokerandroid.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.leanpoker.leanpokerandroid.R;
import org.leanpoker.leanpokerandroid.model.EventModel;
import org.leanpoker.leanpokerandroid.presenter.EventListPresenter;
import org.leanpoker.leanpokerandroid.view.EventListView;
import org.leanpoker.leanpokerandroid.view.adapter.EventsAdapter;
import org.leanpoker.leanpokerandroid.view.adapter.EventsLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tbalogh on 06/09/15.
 */
public abstract class EventsFragment extends BaseFragment implements EventListView {

	private EventsLayoutManager mEventsLayoutManager;
	private EventListPresenter  mEventListPresenter;

	protected EventsAdapter mEventsAdapter;

	@Bind(R.id.recyclerview_events)
	RecyclerView mEventsRecyclerView;



	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
	                         final Bundle savedInstanceState) {
		final View fragmentView = inflater.inflate(R.layout.fragment_event_list, container, false);
		ButterKnife.bind(this, fragmentView);
		setupUI();

		return fragmentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.initialize();
		this.loadEvents();
	}

	@Override
	public void onResume() {
		super.onResume();
		mEventListPresenter.resume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mEventListPresenter.pause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mEventListPresenter.destroy();
	}

	@Override
	public abstract void renderEventList(final List<EventModel> eventModelList);

	@Override
	public void showLoading() {

	}

	@Override
	public void hideLoading() {

	}

	@Override
	public void showError(final String message) {
		showToastMessage(message);
	}

	private void initialize() {
		mEventListPresenter = new EventListPresenter();
		mEventListPresenter.setView(this);
	}

	protected void loadEvents() {
		mEventListPresenter.getEventList();
	}

	private void setupUI() {
		mEventsLayoutManager = new EventsLayoutManager(getActivity());
		mEventsRecyclerView.setLayoutManager(mEventsLayoutManager);

		mEventsAdapter = new EventsAdapter(getActivity(), new ArrayList<EventModel>());
		mEventsRecyclerView.setAdapter(mEventsAdapter);
	}
}
