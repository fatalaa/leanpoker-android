package org.leanpoker.api;

import org.leanpoker.data.model.Event;
import org.leanpoker.data.model.Photo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tmolnar on 12/09/15.
 */
public interface LeanPokerApi {
	Observable<List<Event>> getEvents();
	Observable<Event> getEvent(final String eventId);
	Observable<List<Photo>> getPhotos(final String eventId);
	Boolean uploadPhotoToLeanPoker(
			final String tournamentId,
			final String login,
			final String accessToken,
			final String uploadedImageUrl
	);
}
