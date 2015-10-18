package org.leanpoker.data.datamapper;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.leanpoker.data.model.Address;
import org.leanpoker.data.model.Event;
import org.leanpoker.data.model.Facilitator;
import org.leanpoker.data.model.Photo;
import org.leanpoker.data.response.EventListResponseModel;
import org.leanpoker.data.response.submodel.EventResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tbalogh on 12/09/15.
 */
public class EventDataMapper {
	public List<Event> transform(final EventListResponseModel eventListResponseModel) {
		List<Event> events = new ArrayList<>();
		for (final EventResponseModel eventResponseModel : eventListResponseModel) {
			try {
				events.add(transform(eventResponseModel));
			} catch (final Exception ex) {
				ex.printStackTrace();
			}
		}
		return events;
	}

	public Event transform(final EventResponseModel eventResponseModel) {
		return new Event(eventResponseModel.getId(), eventResponseModel.getHost(),
		                 transformDateTime(eventResponseModel.getDate()), new Facilitator(
				eventResponseModel.getOwner()), new Address(eventResponseModel.getAddress(),
		                                                    eventResponseModel.getCounty(),
		                                                    eventResponseModel.getCity(),
		                                                    eventResponseModel.getLatitude(),
		                                                    eventResponseModel.getLongitude()),
		                 eventResponseModel.getStatus(), eventResponseModel.getTeams().size(),
		                 transformPhotos(eventResponseModel.getImages()));
	}

	private DateTime transformDateTime(final String dateTimeString) {
		//2015-09-27T09:00:00+02:00
		final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");
		try {
			return formatter.parseDateTime(dateTimeString);
		} catch (final IllegalArgumentException ex) {
			// TODO(tb): 17/10/15  Error handling
			return new DateTime();
		}
	}

	private List<Photo> transformPhotos(final List<Map<String, String>> mediaResponseModels) {
		ArrayList<Photo> photos = new ArrayList<>();
		if (mediaResponseModels != null) {
			for (Map<String, String> mediaResponseModel : mediaResponseModels) {
				photos.add(transformPhoto(mediaResponseModel));
			}
		}
		return photos;
	}

	private Photo transformPhoto(final Map<String, String> mediaResponseModel) {
		return new Photo(mediaResponseModel.get("owner"), mediaResponseModel.get("uploaded"),
		                 mediaResponseModel.get("url"));
	}
}
