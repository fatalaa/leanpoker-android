package org.leanpoker.leanpokerandroid.presenter;

import org.leanpoker.data.model.Event;
import org.leanpoker.domain.interactor.EventDetailsInteractor;
import org.leanpoker.leanpokerandroid.modelmapper.EventDetailsModelMapper;
import org.leanpoker.leanpokerandroid.view.EventDetailsView;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by tbalogh on 19/09/15.
 */
public class EventDetailsPresenter implements Presenter {

	private final EventDetailsInteractor  mEventDetailsInteractor;
	private       EventDetailsView        mEventDetailsView;
	private       EventDetailsModelMapper mEventDetailsModelMapper;

	public EventDetailsPresenter(final String eventId) {
		mEventDetailsInteractor = new EventDetailsInteractor(eventId);
		mEventDetailsModelMapper = new EventDetailsModelMapper();
	}

	public void setView(final EventDetailsView eventDetailsView) {
		mEventDetailsView = eventDetailsView;
	}

	public void loadEventDetails() {
		mEventDetailsInteractor.execute(new EventDetailsSubscriber());
	}

	final class EventDetailsSubscriber extends DisposableObserver<Event> {

		@Override
		public void onError(final Throwable e) {
			mEventDetailsView.showError("Couldn't load details!");
		}

		@Override
		public void onComplete() {

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
		mEventDetailsInteractor.unsubscribe();
	}
}
