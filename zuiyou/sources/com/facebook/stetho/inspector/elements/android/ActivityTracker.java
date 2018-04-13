package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.app.Application;
import android.os.Build.VERSION;
import android.os.Looper;
import com.facebook.stetho.common.Util;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class ActivityTracker {
    private static final ActivityTracker sInstance = new ActivityTracker();
    @GuardedBy
    private final ArrayList<WeakReference<Activity>> mActivities = new ArrayList();
    private final List<WeakReference<Activity>> mActivitiesUnmodifiable = Collections.unmodifiableList(this.mActivities);
    @Nullable
    private AutomaticTracker mAutomaticTracker;
    private final List<ActivityTracker$Listener> mListeners = new CopyOnWriteArrayList();

    private static abstract class AutomaticTracker {
        public abstract void register();

        public abstract void unregister();

        private AutomaticTracker() {
        }

        @Nullable
        public static AutomaticTracker newInstanceIfPossible(Application application, ActivityTracker activityTracker) {
            if (VERSION.SDK_INT >= 14) {
                return new ActivityTracker$AutomaticTracker$AutomaticTrackerICSAndBeyond(application, activityTracker);
            }
            return null;
        }
    }

    public static ActivityTracker get() {
        return sInstance;
    }

    public void registerListener(ActivityTracker$Listener activityTracker$Listener) {
        this.mListeners.add(activityTracker$Listener);
    }

    public void unregisterListener(ActivityTracker$Listener activityTracker$Listener) {
        this.mListeners.remove(activityTracker$Listener);
    }

    public boolean beginTrackingIfPossible(Application application) {
        if (this.mAutomaticTracker == null) {
            AutomaticTracker newInstanceIfPossible = AutomaticTracker.newInstanceIfPossible(application, this);
            if (newInstanceIfPossible != null) {
                newInstanceIfPossible.register();
                this.mAutomaticTracker = newInstanceIfPossible;
                return true;
            }
        }
        return false;
    }

    public boolean endTracking() {
        if (this.mAutomaticTracker == null) {
            return false;
        }
        this.mAutomaticTracker.unregister();
        this.mAutomaticTracker = null;
        return true;
    }

    public void add(Activity activity) {
        Util.throwIfNull(activity);
        Util.throwIfNot(Looper.myLooper() == Looper.getMainLooper());
        this.mActivities.add(new WeakReference(activity));
        for (ActivityTracker$Listener onActivityAdded : this.mListeners) {
            onActivityAdded.onActivityAdded(activity);
        }
    }

    public void remove(Activity activity) {
        Util.throwIfNull(activity);
        Util.throwIfNot(Looper.myLooper() == Looper.getMainLooper());
        if (removeFromWeakList(this.mActivities, activity)) {
            for (ActivityTracker$Listener onActivityRemoved : this.mListeners) {
                onActivityRemoved.onActivityRemoved(activity);
            }
        }
    }

    private static <T> boolean removeFromWeakList(ArrayList<WeakReference<T>> arrayList, T t) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (((WeakReference) arrayList.get(i)).get() == t) {
                arrayList.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<WeakReference<Activity>> getActivitiesView() {
        return this.mActivitiesUnmodifiable;
    }

    @Nullable
    public Activity tryGetTopActivity() {
        if (this.mActivitiesUnmodifiable.isEmpty()) {
            return null;
        }
        for (int size = this.mActivitiesUnmodifiable.size() - 1; size >= 0; size--) {
            Activity activity = (Activity) ((WeakReference) this.mActivitiesUnmodifiable.get(size)).get();
            if (activity != null) {
                return activity;
            }
        }
        return null;
    }
}
