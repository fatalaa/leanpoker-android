package org.leanpoker.api;

import org.leanpoker.data.model.Event;

import java.util.List;

import rx.Observable;

/**
 * Created by tmolnar on 12/09/15.
 */
public interface LeanPokerApi {
	Observable<List<Event>> events();
	Observable<Event> event(final long id);
}
