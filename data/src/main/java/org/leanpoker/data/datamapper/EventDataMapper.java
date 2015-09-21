package org.leanpoker.data.datamapper;

import org.leanpoker.data.model.Address;
import org.leanpoker.data.model.Date;
import org.leanpoker.data.model.Event;
import org.leanpoker.data.model.Facilitator;
import org.leanpoker.data.model.Photo;
import org.leanpoker.data.response.EventListResponseModel;
import org.leanpoker.data.response.submodel.EventResponseModel;
import org.leanpoker.data.response.submodel.MediaResponseModel;

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
		return new Event(
				eventResponseModel.getId(),
				eventResponseModel.getHost(),
				transformDate(eventResponseModel.getDate()),
				new Facilitator(eventResponseModel.getOwner()),
				new Address(eventResponseModel.getCity()),
				eventResponseModel.getStatus(),
				transformPhotos(eventResponseModel.getImages())
		);
	}

	private Date transformDate(final String dateString) {
		//2015-09-27T09:00:00+02:00
		final String year = dateString.substring(0, 4);
		final String month = dateString.substring(6, 7);
		final String day = dateString.substring(9, 10);
		return new Date(day, month, year);
	}

	private List<Photo> transformPhotos(final List<Map<String,String>> mediaResponseModels) {
		ArrayList<Photo> photos = new ArrayList<>();
		if (mediaResponseModels != null) {
			for (Map<String,String> mediaResponseModel : mediaResponseModels) {
				photos.add(transformPhoto(mediaResponseModel));
			}
		}
		return photos;
	}

	private Photo transformPhoto(final Map<String,String> mediaResponseModel) {
		return new Photo(
				mediaResponseModel.get("owner"),
				mediaResponseModel.get("uploaded"),
				mediaResponseModel.get("url")
		);
	}
}
