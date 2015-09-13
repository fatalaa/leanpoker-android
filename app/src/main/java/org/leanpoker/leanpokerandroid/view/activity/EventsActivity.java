package org.leanpoker.leanpokerandroid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.crashlytics.android.Crashlytics;

import org.leanpoker.leanpokerandroid.R;
import org.leanpoker.leanpokerandroid.view.adapter.EventsViewPagerAdapter;

import io.fabric.sdk.android.Fabric;

/**
 * Created by tbalogh on 06/09/15.
 */
public class EventsActivity extends BaseActivity {

	private ViewPager              mViewPager;
	private EventsViewPagerAdapter mViewPagerAdapter;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Fabric.with(this, new Crashlytics());
		setContentView(R.layout.actvity_event_list);
		mViewPager = (ViewPager) findViewById(R.id.pager_events_by_time);
		mViewPagerAdapter = new EventsViewPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mViewPagerAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public static Intent createIntent(final Context context) {
		return new Intent(context, EventsActivity.class);
	}
}
