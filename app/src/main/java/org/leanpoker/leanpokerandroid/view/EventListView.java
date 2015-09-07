package org.leanpoker.leanpokerandroid.view;

import org.leanpoker.leanpokerandroid.model.EventModel;

import java.util.List;

/**
 * Created by tbalogh on 06/09/15.
 */
public interface EventListView extends LoadDataView {
	void renderEventList(List<EventModel> eventModelCollection);
}
