package org.leanpoker.leanpokerandroid.view.fragment;

import org.leanpoker.data.model.Event.EventStatus;
import org.leanpoker.leanpokerandroid.model.EventModel;

import java.util.List;

/**
 * Created by tbalogh on 15/09/15.
 */
public class EventListLiveFragment extends EventListFragment {

	protected List<EventModel> filter(final List<EventModel> eventModelList) {
		return filter(eventModelList, EventStatus.RUNNING);
	}

	public static EventListLiveFragment newInstance() {
		return new EventListLiveFragment();
	}
}
