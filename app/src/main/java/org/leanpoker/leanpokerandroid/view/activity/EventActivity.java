package org.leanpoker.leanpokerandroid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import org.leanpoker.leanpokerandroid.R;
import org.leanpoker.leanpokerandroid.view.adapter.EventViewPagerAdapter;

/**
 * Created by tbalogh on 19/09/15.
 */
public class EventActivity extends BaseActivity {

	private static final String M_EVENT_ID_KEY = "event_id_key";
	private ViewPager             mViewPager;
	private EventViewPagerAdapter mEventViewPagerAdapter;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		setupUI(getIntent().getStringExtra(M_EVENT_ID_KEY));
	}

	private void setupUI(final String eventId) {
		if (eventId == null || eventId.isEmpty()) {
			throw new RuntimeException("Invalid event id has been given!");
		}
		mEventViewPagerAdapter = new EventViewPagerAdapter(getSupportFragmentManager(), eventId);
		mViewPager = (ViewPager) findViewById(R.id.pager_event_pages);
		mViewPager.setAdapter(mEventViewPagerAdapter);
		TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs_activity_details);
		tabLayout.setTabsFromPagerAdapter(mEventViewPagerAdapter);
	}

	public static Intent createIntent(final Context context, final String eventId) {
		final Intent intent = new Intent(context, EventActivity.class);
		intent.putExtra(M_EVENT_ID_KEY, eventId);
		return intent;
	}
}
