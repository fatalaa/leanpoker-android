package org.leanpoker.leanpokerandroid.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.leanpoker.leanpokerandroid.R;
import org.leanpoker.leanpokerandroid.model.EventModel;
import org.leanpoker.leanpokerandroid.navigator.Navigator;
import org.leanpoker.leanpokerandroid.presenter.EventListPresenter;
import org.leanpoker.leanpokerandroid.view.EventListView;
import org.leanpoker.leanpokerandroid.view.adapter.EventListAdapter;
import org.leanpoker.leanpokerandroid.view.adapter.EventListAdapter.OnEventClickListener;
import org.leanpoker.leanpokerandroid.view.adapter.EventListLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tbalogh on 06/09/15.
 */
public class EventListFragment extends BaseFragment implements EventListView,
		OnEventClickListener, SwipeRefreshLayout.OnRefreshListener {

	private   EventListLayoutManager mEventListLayoutManager;
	private   EventListPresenter     mEventListPresenter;
	protected EventListAdapter       mEventListAdapter;

	@Bind(R.id.pull_to_refresh)
	SwipeRefreshLayout mSwipeRefreshLayout;

	@Bind(R.id.recyclerview_events)
	RecyclerView mEventsRecyclerView;

	@Override
	public void renderEventList(final List<EventModel> eventModelList) {
		mEventListAdapter.setItemList(eventModelList);
	}

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
	public void onRefresh() {
		loadEvents();
	}

	@Override
	public void showLoading() {
		mSwipeRefreshLayout.setRefreshing(true);
	}

	@Override
	public void hideLoading() {
		mSwipeRefreshLayout.setRefreshing(false);
	}

	@Override
	public void showLoadingError(final String message) {
		showToastMessage(message);
	}

	@Override
	public void onEventClick(final String eventId, final String eventName) {
		Navigator.getInstance().navigateToEventActivity(getActivity(), eventId, eventName);
	}

	private void initialize() {
		mEventListPresenter = new EventListPresenter();
		mEventListPresenter.setView(this);
	}

	protected void loadEvents() {
		mEventListPresenter.getEventList();
	}

	private void setupUI() {
		mEventListLayoutManager = new EventListLayoutManager(getActivity());
		mEventsRecyclerView.setLayoutManager(mEventListLayoutManager);

		mEventListAdapter = new EventListAdapter(getActivity(), new ArrayList<EventModel>());
		mEventListAdapter.setOnEventClickListener(this);
		mEventsRecyclerView.setAdapter(mEventListAdapter);
		mSwipeRefreshLayout.setOnRefreshListener(this);
	}

	public static EventListFragment newInstance() {
		return new EventListFragment();
	}
}
