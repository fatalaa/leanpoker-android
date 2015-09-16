package org.leanpoker.leanpokerandroid.view.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.leanpoker.leanpokerandroid.view.fragment.EventsFragment;
import org.leanpoker.leanpokerandroid.view.fragment.EventsLiveFragment;
import org.leanpoker.leanpokerandroid.view.fragment.EventsPastFragment;
import org.leanpoker.leanpokerandroid.view.fragment.EventsUpcomingsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tbalogh on 12/09/15.
 */
public class EventsViewPagerAdapter extends FragmentPagerAdapter {

	private static final int M_EVENTS_FRAGMENT_COUNT = 3;
	private final List<EventsFragment> mEventsFragments;
	private String tabTitles[] = new String[]{"UPCOMING", "LIVE", "PAST"};

	public EventsViewPagerAdapter(final FragmentManager fragmentManager) {
		super(fragmentManager);
		mEventsFragments = createEventFragments();
	}

	@NonNull
	private List<EventsFragment> createEventFragments() {
		final List<EventsFragment> eventsFragments = new ArrayList<>(M_EVENTS_FRAGMENT_COUNT);
		eventsFragments.add(new EventsUpcomingsFragment());
		eventsFragments.add(new EventsLiveFragment());
		eventsFragments.add(new EventsPastFragment());
		return eventsFragments;
	}

	@Override
	public Fragment getItem(final int position) {
		return mEventsFragments.get(position);
	}

	@Override
	public int getCount() {
		return M_EVENTS_FRAGMENT_COUNT;
	}

	@Override
	public CharSequence getPageTitle(final int position) {
		return tabTitles[position];
	}
}
