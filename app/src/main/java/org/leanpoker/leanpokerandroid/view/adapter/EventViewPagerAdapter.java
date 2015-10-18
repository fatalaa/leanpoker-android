package org.leanpoker.leanpokerandroid.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.leanpoker.leanpokerandroid.view.fragment.BaseFragment;
import org.leanpoker.leanpokerandroid.view.fragment.EventDetailsFragment;
import org.leanpoker.leanpokerandroid.view.fragment.EventFragment;
import org.leanpoker.leanpokerandroid.view.fragment.EventPhotoGridFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tbalogh on 19/09/15.
 */
public class EventViewPagerAdapter extends FragmentPagerAdapter {

	private final int M_EVENT_FRAGMENTS_COUNT = 4;
	private final String mEventId;

	private final String[] mPageTitles = {"Gallery", "Wall", "Scores", "Details"};

	private final List<BaseFragment> mEventFragments;

	public EventViewPagerAdapter(final FragmentManager fragmentManager, final String eventId) {
		super(fragmentManager);
		mEventId = eventId;
		mEventFragments = createFragments();
	}

	private List<BaseFragment> createFragments() {
		final List<BaseFragment> eventFragments = new ArrayList<>(M_EVENT_FRAGMENTS_COUNT);
		eventFragments.add(EventPhotoGridFragment.newInstance(mEventId));
		eventFragments.add(EventDetailsFragment.newInstance(mEventId));
		eventFragments.add(EventDetailsFragment.newInstance(mEventId));
		eventFragments.add(EventDetailsFragment.newInstance(mEventId));
		return eventFragments;
	}

	@Override
	public Fragment getItem(final int position) {
		return mEventFragments.get(position);
	}

	@Override
	public int getCount() {
		return M_EVENT_FRAGMENTS_COUNT;
	}

	@Override
	public CharSequence getPageTitle(final int position) {
		return mPageTitles[position];
	}
}
