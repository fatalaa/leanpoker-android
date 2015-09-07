package org.leanpoker.leanpokerandroid.modelmapper;

import org.leanpoker.data.Event;
import org.leanpoker.leanpokerandroid.model.EventModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tbalogh on 06/09/15.
 */
public class EventModelDataMapper {
	public EventModelDataMapper() {
	}

	public EventModel transform(final Event event) {
		return new EventModel(event);
	}

	public List<EventModel> transform(final List<Event> events) {
		List<EventModel> eventModels = new ArrayList<>();
		for(final Event event : events) {
			eventModels.add(transform(event));
		}
		return eventModels;
	}
}
