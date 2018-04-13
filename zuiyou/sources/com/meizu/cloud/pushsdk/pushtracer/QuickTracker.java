package com.meizu.cloud.pushsdk.pushtracer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.pushtracer.emitter.BufferOption;
import com.meizu.cloud.pushsdk.pushtracer.emitter.Emitter;
import com.meizu.cloud.pushsdk.pushtracer.emitter.Emitter.EmitterBuilder;
import com.meizu.cloud.pushsdk.pushtracer.emitter.RequestCallback;
import com.meizu.cloud.pushsdk.pushtracer.tracker.Subject;
import com.meizu.cloud.pushsdk.pushtracer.tracker.Subject.SubjectBuilder;
import com.meizu.cloud.pushsdk.pushtracer.tracker.Tracker;
import com.meizu.cloud.pushsdk.pushtracer.tracker.Tracker.TrackerBuilder;
import com.meizu.cloud.pushsdk.pushtracer.utils.LogLevel;
import com.meizu.cloud.pushsdk.pushtracer.utils.Logger;
import com.meizu.cloud.pushsdk.pushtracer.utils.Util;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import java.util.concurrent.atomic.AtomicBoolean;

public class QuickTracker {
    private static AtomicBoolean isRegisterNetReceiver = new AtomicBoolean(false);
    public static final String namespace = "PushAndroidTracker";
    private static Tracker tracker;

    public static Tracker init(Context context, boolean z) {
        if (tracker == null) {
            synchronized (QuickTracker.class) {
                if (tracker == null) {
                    tracker = getTrackerClassic(getEmitterClassic(context, null), null, context);
                }
            }
        }
        a.a(namespace, "can upload subject " + z);
        if (z) {
            tracker.setSubject(getSubject(context));
        }
        return tracker;
    }

    public static Tracker getAndroidTrackerClassic(Context context, RequestCallback requestCallback) {
        if (tracker == null) {
            synchronized (QuickTracker.class) {
                if (tracker == null) {
                    tracker = getTrackerClassic(getEmitterClassic(context, requestCallback), null, context);
                }
            }
        }
        if (isRegisterNetReceiver.compareAndSet(false, true)) {
            registerNetworkReceiver(context, tracker);
        }
        return tracker;
    }

    private static Tracker getTrackerClassic(Emitter emitter, Subject subject, Context context) {
        return new com.meizu.cloud.pushsdk.pushtracer.tracker.classic.Tracker(new TrackerBuilder(emitter, namespace, context.getPackageCodePath(), context, com.meizu.cloud.pushsdk.pushtracer.tracker.classic.Tracker.class).level(LogLevel.VERBOSE).base64(Boolean.valueOf(false)).subject(subject).threadCount(4));
    }

    private static Emitter getEmitterClassic(Context context, RequestCallback requestCallback) {
        return new com.meizu.cloud.pushsdk.pushtracer.emitter.classic.Emitter(new EmitterBuilder(getStaticsDomain(), context, com.meizu.cloud.pushsdk.pushtracer.emitter.classic.Emitter.class).callback(requestCallback).tick(1).option(BufferOption.DefaultGroup).sendLimit(BufferOption.DefaultGroup.getCode()).emptyLimit(2));
    }

    private static Subject getSubject(Context context) {
        return new SubjectBuilder().context(context).build();
    }

    private static void registerNetworkReceiver(Context context, final Tracker tracker) {
        context.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (Util.isOnline(context)) {
                    Logger.e("QuickTracker", "restart track event: %s", "online true");
                    tracker.restartEventTracking();
                }
            }
        }, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    private static String getStaticsDomain() {
        String str = "push-statics.meizu.com";
        if (MzSystemUtils.isInternational() || MzSystemUtils.isIndiaLocal()) {
            return "push-statics.in.meizu.com";
        }
        a.d("QuickTracker", "current statics domain is " + str);
        return str;
    }
}
