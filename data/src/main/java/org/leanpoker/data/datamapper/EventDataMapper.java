package org.leanpoker.data.datamapper;

import org.leanpoker.data.model.Address;
import org.leanpoker.data.model.Date;
import org.leanpoker.data.model.Event;
import org.leanpoker.data.model.Facilitator;
import org.leanpoker.data.response.EventListResponseModel;
import org.leanpoker.data.response.submodel.EventResponseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tbalogh on 12/09/15.
 */
public class EventDataMapper {
	public List<Event> transformDate(final EventListResponseModel eventListResponseModel) {
		List<Event> events = new ArrayList<>();
		for (final EventResponseModel eventResponseModel : eventListResponseModel) {
			events.add(transformDate(eventResponseModel));
		}
		return events;
	}

	public Event transformDate(final EventResponseModel eventResponseModel) {
		return new Event(eventResponseModel.getId(), eventResponseModel.getHost(), transformDate(
				eventResponseModel.getDate()), new Facilitator(eventResponseModel.getOwner()), new Address(eventResponseModel.getCity()));
	}

	private Date transformDate(final String dateString) {
		//2015-09-27T09:00:00+02:00
		final String year = dateString.substring(0, 4);
		final String month = dateString.substring(6, 7);
		final String day = dateString.substring(9, 10);
		return new Date(day, month, year);
	}
}
