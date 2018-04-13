package org.greenrobot.eventbus.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import org.greenrobot.eventbus.EventBus;

public class ErrorDialogManager {
    public static final String KEY_EVENT_TYPE_ON_CLOSE = "de.greenrobot.eventbus.errordialog.event_type_on_close";
    public static final String KEY_FINISH_AFTER_DIALOG = "de.greenrobot.eventbus.errordialog.finish_after_dialog";
    public static final String KEY_ICON_ID = "de.greenrobot.eventbus.errordialog.icon_id";
    public static final String KEY_MESSAGE = "de.greenrobot.eventbus.errordialog.message";
    public static final String KEY_TITLE = "de.greenrobot.eventbus.errordialog.title";
    public static ErrorDialogFragmentFactory<?> factory;

    @TargetApi(11)
    public static class HoneycombManagerFragment extends Fragment {
        protected boolean a;
        protected Bundle b;
        private EventBus c;
        private Object d;

        public void onResume() {
            super.onResume();
            this.c = ErrorDialogManager.factory.a.a();
            this.c.register(this);
        }

        public void onPause() {
            this.c.unregister(this);
            super.onPause();
        }

        public void onEventMainThread(ThrowableFailureEvent throwableFailureEvent) {
            if (ErrorDialogManager.b(this.d, throwableFailureEvent)) {
                ErrorDialogManager.a(throwableFailureEvent);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.executePendingTransactions();
                DialogFragment dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag("de.greenrobot.eventbus.error_dialog");
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                }
                dialogFragment = (DialogFragment) ErrorDialogManager.factory.a(throwableFailureEvent, this.a, this.b);
                if (dialogFragment != null) {
                    dialogFragment.show(fragmentManager, "de.greenrobot.eventbus.error_dialog");
                }
            }
        }

        public static void attachTo(Activity activity, Object obj, boolean z, Bundle bundle) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            HoneycombManagerFragment honeycombManagerFragment = (HoneycombManagerFragment) fragmentManager.findFragmentByTag("de.greenrobot.eventbus.error_dialog_manager");
            if (honeycombManagerFragment == null) {
                honeycombManagerFragment = new HoneycombManagerFragment();
                fragmentManager.beginTransaction().add(honeycombManagerFragment, "de.greenrobot.eventbus.error_dialog_manager").commit();
                fragmentManager.executePendingTransactions();
            }
            honeycombManagerFragment.a = z;
            honeycombManagerFragment.b = bundle;
            honeycombManagerFragment.d = obj;
        }
    }

    public static class SupportManagerFragment extends android.support.v4.app.Fragment {
        protected boolean a;
        protected Bundle b;
        private EventBus c;
        private boolean d;
        private Object e;

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.c = ErrorDialogManager.factory.a.a();
            this.c.register(this);
            this.d = true;
        }

        public void onResume() {
            super.onResume();
            if (this.d) {
                this.d = false;
                return;
            }
            this.c = ErrorDialogManager.factory.a.a();
            this.c.register(this);
        }

        public void onPause() {
            this.c.unregister(this);
            super.onPause();
        }

        public void onEventMainThread(ThrowableFailureEvent throwableFailureEvent) {
            if (ErrorDialogManager.b(this.e, throwableFailureEvent)) {
                ErrorDialogManager.a(throwableFailureEvent);
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.executePendingTransactions();
                android.support.v4.app.DialogFragment dialogFragment = (android.support.v4.app.DialogFragment) fragmentManager.findFragmentByTag("de.greenrobot.eventbus.error_dialog");
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                }
                dialogFragment = (android.support.v4.app.DialogFragment) ErrorDialogManager.factory.a(throwableFailureEvent, this.a, this.b);
                if (dialogFragment != null) {
                    dialogFragment.show(fragmentManager, "de.greenrobot.eventbus.error_dialog");
                }
            }
        }

        public static void attachTo(Activity activity, Object obj, boolean z, Bundle bundle) {
            android.support.v4.app.FragmentManager supportFragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            SupportManagerFragment supportManagerFragment = (SupportManagerFragment) supportFragmentManager.findFragmentByTag("de.greenrobot.eventbus.error_dialog_manager");
            if (supportManagerFragment == null) {
                supportManagerFragment = new SupportManagerFragment();
                supportFragmentManager.beginTransaction().add(supportManagerFragment, "de.greenrobot.eventbus.error_dialog_manager").commit();
                supportFragmentManager.executePendingTransactions();
            }
            supportManagerFragment.a = z;
            supportManagerFragment.b = bundle;
            supportManagerFragment.e = obj;
        }
    }

    public static void attachTo(Activity activity) {
        attachTo(activity, false, null);
    }

    public static void attachTo(Activity activity, boolean z) {
        attachTo(activity, z, null);
    }

    public static void attachTo(Activity activity, boolean z, Bundle bundle) {
        attachTo(activity, activity.getClass(), z, bundle);
    }

    public static void attachTo(Activity activity, Object obj, boolean z, Bundle bundle) {
        if (factory == null) {
            throw new RuntimeException("You must set the static factory field to configure error dialogs for your app.");
        } else if (a(activity)) {
            SupportManagerFragment.attachTo(activity, obj, z, bundle);
        } else {
            HoneycombManagerFragment.attachTo(activity, obj, z, bundle);
        }
    }

    private static boolean a(Activity activity) {
        Class superclass = activity.getClass().getSuperclass();
        while (superclass != null) {
            String name = superclass.getName();
            if (name.equals("android.support.v4.app.FragmentActivity")) {
                return true;
            }
            if (name.startsWith("com.actionbarsherlock.app") && (name.endsWith(".SherlockActivity") || name.endsWith(".SherlockListActivity") || name.endsWith(".SherlockPreferenceActivity"))) {
                throw new RuntimeException("Please use SherlockFragmentActivity. Illegal activity: " + name);
            } else if (!name.equals("android.app.Activity")) {
                superclass = superclass.getSuperclass();
            } else if (VERSION.SDK_INT >= 11) {
                return false;
            } else {
                throw new RuntimeException("Illegal activity without fragment support. Either use Android 3.0+ or android.support.v4.app.FragmentActivity.");
            }
        }
        throw new RuntimeException("Illegal activity type: " + activity.getClass());
    }

    protected static void a(ThrowableFailureEvent throwableFailureEvent) {
        if (factory.a.f) {
            String str = factory.a.g;
            if (str == null) {
                str = EventBus.TAG;
            }
            Log.i(str, "Error dialog manager received exception", throwableFailureEvent.a);
        }
    }

    private static boolean b(Object obj, ThrowableFailureEvent throwableFailureEvent) {
        if (throwableFailureEvent != null) {
            Object executionScope = throwableFailureEvent.getExecutionScope();
            if (!(executionScope == null || executionScope.equals(obj))) {
                return false;
            }
        }
        return true;
    }
}
