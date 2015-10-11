package org.leanpoker.leanpokerandroid.view.fragment;

import org.leanpoker.data.model.Event.EventStatus;
import org.leanpoker.leanpokerandroid.model.EventModel;

import java.util.List;

/**
 * Created by tbalogh on 15/09/15.
 */
public class EventListUpcomingsFragment extends EventListFragment {

	protected List<EventModel> filter(final List<EventModel> eventModelList) {
		return filter(eventModelList, EventStatus.FUTURE);
	}

	public static EventListUpcomingsFragment newInstance() {
		return new EventListUpcomingsFragment();
	}
}
