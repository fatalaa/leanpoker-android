package org.leanpoker.api;

import org.leanpoker.data.Event;

import java.util.List;

/**
 * Created by tmolnar on 12/09/15.
 */
public interface LeanPokerApiListener {
    void notifyEventsLoaded(final List<Event> events);
    void notifyEventsLoadingFailure(final Throwable throwable);
}
