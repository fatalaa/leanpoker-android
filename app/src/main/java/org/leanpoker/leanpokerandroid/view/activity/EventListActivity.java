package org.leanpoker.leanpokerandroid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import org.leanpoker.leanpokerandroid.R;
import org.leanpoker.leanpokerandroid.view.adapter.EventListViewPagerAdapter;

/**
 * Created by tbalogh on 06/09/15.
 */
public class EventListActivity extends BaseActivity {

	private ViewPager              mViewPager;
	private EventListViewPagerAdapter mViewPagerAdapter;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_event_list);
		setupUI();
	}

	private void setupUI() {
		mViewPagerAdapter = new EventListViewPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager_events_by_time);
		mViewPager.setAdapter(mViewPagerAdapter);
		TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
		tabLayout.setupWithViewPager(mViewPager);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public static Intent createIntent(final Context context) {
		return new Intent(context, EventListActivity.class);
	}
}
