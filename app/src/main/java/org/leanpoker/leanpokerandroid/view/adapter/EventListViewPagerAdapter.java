package org.leanpoker.leanpokerandroid.view.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.leanpoker.leanpokerandroid.view.fragment.EventListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tbalogh on 12/09/15.
 */
public class EventListViewPagerAdapter extends FragmentPagerAdapter {

	private static final int M_EVENTS_FRAGMENT_COUNT = 1;
	private final List<EventListFragment> mEventListFragments;
	private String tabTitles[] = new String[]{"EVENTS"};

	public EventListViewPagerAdapter(final FragmentManager fragmentManager) {
		super(fragmentManager);
		mEventListFragments = createEventFragments();
	}

	@NonNull
	private List<EventListFragment> createEventFragments() {
		final List<EventListFragment> eventListFragments = new ArrayList<>();
		eventListFragments.add(EventListFragment.newInstance());
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
