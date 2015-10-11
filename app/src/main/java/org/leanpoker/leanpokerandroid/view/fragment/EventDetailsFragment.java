package org.leanpoker.leanpokerandroid.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.leanpoker.leanpokerandroid.R;
import org.leanpoker.leanpokerandroid.model.EventDetailsModel;
import org.leanpoker.leanpokerandroid.presenter.EventDetailsPresenter;
import org.leanpoker.leanpokerandroid.view.EventDetailsView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tbalogh on 19/09/15.
 */
public class EventDetailsFragment extends EventFragment implements EventDetailsView {

	@Bind(R.id.event_detail_id)
	TextView mEventDetailIdTextView;

	private String                mEventId;
	private EventDetailsPresenter mEventDetailsPresenter;

	public void setEventId(final String eventId) {
		mEventId = eventId;
	}

	@Override
	public void renderEvent(final EventDetailsModel eventDetailsModel) {
		mEventDetailIdTextView.setText(eventDetailsModel.getEventName());
	}

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
	                         final Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_event_details, container, false);
		ButterKnife.bind(this, view);

		return view;
	}

	@Override
	public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initialize();
		loadDetails();
	}

	@Override
	public void onResume() {
		super.onResume();
		mEventDetailsPresenter.resume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mEventDetailsPresenter.pause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mEventDetailsPresenter.destroy();
	}

	private void initialize() {
		mEventDetailsPresenter = new EventDetailsPresenter(mEventId);
		mEventDetailsPresenter.setView(this);
	}

	private void loadDetails() {
		mEventDetailsPresenter.loadEventDetails();
	}

	public static EventDetailsFragment newInstance(final String eventId) {
		final EventDetailsFragment eventDetailsFragment = new EventDetailsFragment();
		eventDetailsFragment.setEventId(eventId);
		return eventDetailsFragment;
	}
}
