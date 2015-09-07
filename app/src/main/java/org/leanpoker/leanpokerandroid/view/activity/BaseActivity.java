package org.leanpoker.leanpokerandroid.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.leanpoker.leanpokerandroid.navigator.Navigator;

/**
 * Created by tbalogh on 06/09/15.
 */
public class BaseActivity extends AppCompatActivity {

	Navigator navigator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected void addFragment(int containerViewId, Fragment fragment) {
		FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
		fragmentTransaction.add(containerViewId, fragment);
		fragmentTransaction.commit();
	}
}
