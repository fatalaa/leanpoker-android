package org.leanpoker.leanpokerandroid.view;

import android.app.Activity;
import android.content.Context;

/**
 * Created by tmolnar on 19/09/15.
 */
public interface LoginView {
    void navigateToEventListAcitivity();
    void navigateToPreviousActivity();
    Context getContext();
    Activity getActivity();
}
