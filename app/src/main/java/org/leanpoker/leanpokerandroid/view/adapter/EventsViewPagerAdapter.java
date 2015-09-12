package org.leanpoker.leanpokerandroid.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.leanpoker.leanpokerandroid.view.fragment.EventsFragment;

/**
 * Created by tbalogh on 12/09/15.
 */
public class EventsViewPagerAdapter extends FragmentStatePagerAdapter {

	private static final int M_EVENTS_FRAGMENT_COUNT = 3;

	public EventsViewPagerAdapter(final FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(final int position) {
		return new EventsFragment();
	}

	@Override
	public int getCount() {
		return M_EVENTS_FRAGMENT_COUNT;
	}
}
