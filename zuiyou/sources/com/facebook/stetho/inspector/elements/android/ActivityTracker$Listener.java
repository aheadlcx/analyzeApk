package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;

public interface ActivityTracker$Listener {
    void onActivityAdded(Activity activity);

    void onActivityRemoved(Activity activity);
}
