package org.leanpoker.leanpokerandroid.presenter;

import org.leanpoker.data.model.Event;
import org.leanpoker.domain.interactor.EventListInteractor;
import org.leanpoker.leanpokerandroid.modelmapper.EventModelDataMapper;
import org.leanpoker.leanpokerandroid.view.EventListView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by tbalogh on 06/09/15.
 */
public class EventListPresenter implements Presenter {

	private static final int M_MOCK_EVENT_COUNT = 50;
	private final EventListInteractor mEventListInteractor;
	private final EventModelDataMapper mEventModelDataMapper;


	private EventListView mEventListView;

	public EventListPresenter() {
		mEventListInteractor = new EventListInteractor();
		mEventModelDataMapper = new EventModelDataMapper();
	}

	public void setView(final EventListView eventListView) {
		mEventListView = eventListView;
	}

	public void initialize() {
		showViewLoading();
		getEventList();
	}

	public void getEventList() {
		mEventListInteractor.execute(new EventListSubscriber());
	}

	@Override
	public void resume() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void destroy() {
		mEventListInteractor.unsubscribe();
	}

	final class EventListSubscriber extends Subscriber<List<Event>> {

		@Override
		public void onCompleted() {
			EventListPresenter.this.hideViewLoading();
		}

		@Override
		public void onError(final Throwable e) {
			EventListPresenter.this.hideViewLoading();
			EventListPresenter.this.showError();
		}

		@Override
		public void onNext(final List<Event> events) {
			EventListPresenter.this.showEvents(events);
		}
	}

	private void showEvents(final List<Event> events) {
		mEventListView.renderEventList(mEventModelDataMapper.transform(events));
	}

	private void showViewLoading() {
		mEventListView.showLoading();
	}

	private void hideViewLoading() {
		mEventListView.hideLoading();
	}

	private void showError() {
		mEventListView.showError("Events can't be loaded!");
	}
}
