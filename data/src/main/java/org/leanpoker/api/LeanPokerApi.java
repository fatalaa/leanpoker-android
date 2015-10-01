package org.leanpoker.api;

import org.leanpoker.data.model.Event;
import org.leanpoker.data.model.Photo;

import java.util.List;

import rx.Observable;

/**
 * Created by tmolnar on 12/09/15.
 */
public interface LeanPokerApi {
	Observable<List<Event>> events();
	Observable<Event> event(final String eventId);
	Observable<List<Photo>> photos(final String eventId);
	Boolean uploadPhotoToLeanPoker(
			final String tournamentId,
			final String email,
			final String accessToken,
			final String uploadedImageUrl
	);
}
