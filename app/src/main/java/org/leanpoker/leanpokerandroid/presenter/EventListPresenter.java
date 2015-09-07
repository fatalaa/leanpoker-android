package org.leanpoker.leanpokerandroid.presenter;

import org.leanpoker.domain.interactor.EventListInteractor;
import org.leanpoker.domain.mock.EventListProviderMock;
import org.leanpoker.leanpokerandroid.model.EventModel;
import org.leanpoker.leanpokerandroid.modelmapper.EventModelDataMapper;
import org.leanpoker.leanpokerandroid.view.EventListView;

import java.util.List;

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
		loadEvents();
	}

	public void loadEvents() {
		final List<EventModel> eventModels = mEventModelDataMapper.transform(
				EventListProviderMock.getMockEvents(M_MOCK_EVENT_COUNT));
		mEventListView.renderEventList(eventModels);
	}

	@Override
	public void resume() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void destroy() {
	}
}
