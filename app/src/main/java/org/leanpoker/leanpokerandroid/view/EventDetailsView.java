package org.leanpoker.leanpokerandroid.view;

import org.leanpoker.leanpokerandroid.model.EventDetailsModel;

/**
 * Created by tbalogh on 19/09/15.
 */
public interface EventDetailsView extends BaseView{
	void renderEvent(final EventDetailsModel eventDetailsModel);
}
