package org.leanpoker.leanpokerandroid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import org.leanpoker.leanpokerandroid.P;
import org.leanpoker.leanpokerandroid.P.Common;
import org.leanpoker.leanpokerandroid.R;
import org.leanpoker.leanpokerandroid.view.adapter.EventViewPagerAdapter;
import org.leanpoker.leanpokerandroid.view.fragment.BaseFragment;

/**
 * Created by tbalogh on 19/09/15.
 */
public class EventActivity extends BaseActivity {

	private ViewPager             mViewPager;
	private EventViewPagerAdapter mEventViewPagerAdapter;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		setupUI(getIntent());
	}

	private void setupUI(final Intent intent) {
		final String eventId = intent.getStringExtra(P.Common.EVENT_ID_KEY);
		final String eventName = intent.getStringExtra(Common.EVENT_NAME_KEY);
		if (TextUtils.isEmpty(eventId)) {
			throw new RuntimeException("Invalid getEvent id has been given!");
		}
		mEventViewPagerAdapter = new EventViewPagerAdapter(getSupportFragmentManager(), eventId);
		mViewPager = (ViewPager) findViewById(R.id.pager_event_pages);
		mViewPager.setAdapter(mEventViewPagerAdapter);
		TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs_activity_details);
		tabLayout.setupWithViewPager(mViewPager);
		setTitle(eventName);
	}

	@Override
	protected void onActivityResult(final int requestCode, final int resultCode,
	                                final Intent data) {
		BaseFragment currentFragment = (BaseFragment) mEventViewPagerAdapter.getItem(
				mViewPager.getCurrentItem());
		if (currentFragment != null) {
			currentFragment.onActivityResult(requestCode, resultCode, data);
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	public static Intent createIntent(final Context context, final String eventId,
	                                  final String eventName) {
		final Intent intent = new Intent(context, EventActivity.class);
		intent.putExtra(P.Common.EVENT_ID_KEY, eventId);
		intent.putExtra(P.Common.EVENT_NAME_KEY, eventName);
		return intent;
	}
}
