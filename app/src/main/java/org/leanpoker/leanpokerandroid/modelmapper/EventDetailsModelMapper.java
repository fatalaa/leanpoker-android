package org.leanpoker.leanpokerandroid.modelmapper;

import org.leanpoker.data.model.Event;
import org.leanpoker.leanpokerandroid.model.EventDetailsModel;

/**
 * Created by tbalogh on 19/09/15.
 */
public class EventDetailsModelMapper {
	public EventDetailsModel transform(final Event event) {
		return new EventDetailsModel(event.getName(), event.getAddress(), event.getDateTime(),
		                             event.getEventStatus(), event.getFacilitator());
	}
}
