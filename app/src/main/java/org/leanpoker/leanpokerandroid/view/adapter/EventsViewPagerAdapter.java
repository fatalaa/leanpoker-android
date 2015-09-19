package org.leanpoker.leanpokerandroid.view.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.leanpoker.leanpokerandroid.view.fragment.EventListFragment;
import org.leanpoker.leanpokerandroid.view.fragment.EventListLiveFragment;
import org.leanpoker.leanpokerandroid.view.fragment.EventListPastFragment;
import org.leanpoker.leanpokerandroid.view.fragment.EventListUpcomingsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tbalogh on 12/09/15.
 */
public class EventsViewPagerAdapter extends FragmentPagerAdapter {

	private static final int M_EVENTS_FRAGMENT_COUNT = 3;
	private final List<EventListFragment> mEventListFragments;
	private String tabTitles[] = new String[]{"UPCOMING", "LIVE", "PAST"};

	public EventsViewPagerAdapter(final FragmentManager fragmentManager) {
		super(fragmentManager);
		mEventListFragments = createEventFragments();
	}

	@NonNull
	private List<EventListFragment> createEventFragments() {
		final List<EventListFragment> eventListFragments = new ArrayList<>();
		eventListFragments.add(EventListUpcomingsFragment.newInstance());
		eventListFragments.add(EventListLiveFragment.newInstance());
		eventListFragments.add(EventListPastFragment.newInstance());
		return eventListFragments;
	}

	@Override
	public Fragment getItem(final int position) {
		return mEventListFragments.get(position);
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
