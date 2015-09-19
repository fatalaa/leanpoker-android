package org.leanpoker.leanpokerandroid.presenter;

import org.leanpoker.data.model.Event;
import org.leanpoker.domain.interactor.EventWithDetailsInteractor;
import org.leanpoker.leanpokerandroid.modelmapper.EventDetailsModelMapper;
import org.leanpoker.leanpokerandroid.view.EventDetailsView;

import rx.Subscriber;

/**
 * Created by tbalogh on 19/09/15.
 */
public class EventDetailsPresenter implements Presenter {

	private final EventWithDetailsInteractor mEventWithDetailsInteractor;
	private       EventDetailsView           mEventDetailsView;
	private       EventDetailsModelMapper    mEventDetailsModelMapper;

	public EventDetailsPresenter(final String eventId) {
		mEventWithDetailsInteractor = new EventWithDetailsInteractor(eventId);
		mEventDetailsModelMapper = new EventDetailsModelMapper();
	}

	public void setView(final EventDetailsView eventDetailsView) {
		mEventDetailsView = eventDetailsView;
	}

	public void loadEventDetails() {
		mEventWithDetailsInteractor.execute(new EventDetailsSubscriber());
	}

	final class EventDetailsSubscriber extends Subscriber<Event> {

		@Override
		public void onCompleted() {

		}

		@Override
		public void onError(final Throwable e) {
			mEventDetailsView.showError("Couldn't load details!");
		}

		@Override
		public void onNext(final Event event) {
			mEventDetailsView.renderEvent(mEventDetailsModelMapper.transform(event));
		}
	}

	@Override
	public void resume() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void destroy() {
		mEventWithDetailsInteractor.unsubscribe();
	}
}
