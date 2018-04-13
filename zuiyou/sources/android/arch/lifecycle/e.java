package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.Lifecycle.State;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

class e {
    private static AtomicBoolean a = new AtomicBoolean(false);

    public static class a extends Fragment {
        public void onPause() {
            super.onPause();
            a(Event.ON_PAUSE);
        }

        public void onStop() {
            super.onStop();
            a(Event.ON_STOP);
        }

        public void onDestroy() {
            super.onDestroy();
            a(Event.ON_DESTROY);
        }

        protected void a(Event event) {
            e.b(getParentFragment(), event);
        }
    }

    @VisibleForTesting
    static class b extends b {
        private final c a = new c();

        b() {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (activity instanceof FragmentActivity) {
                ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(this.a, true);
            }
            m.a(activity);
        }

        public void onActivityStopped(Activity activity) {
            if (activity instanceof FragmentActivity) {
                e.b((FragmentActivity) activity, State.CREATED);
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            if (activity instanceof FragmentActivity) {
                e.b((FragmentActivity) activity, State.CREATED);
            }
        }
    }

    @VisibleForTesting
    static class c extends FragmentLifecycleCallbacks {
        c() {
        }

        public void onFragmentCreated(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
            e.b(fragment, Event.ON_CREATE);
            if ((fragment instanceof i) && fragment.getChildFragmentManager().findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
                fragment.getChildFragmentManager().beginTransaction().add(new a(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            }
        }

        public void onFragmentStarted(FragmentManager fragmentManager, Fragment fragment) {
            e.b(fragment, Event.ON_START);
        }

        public void onFragmentResumed(FragmentManager fragmentManager, Fragment fragment) {
            e.b(fragment, Event.ON_RESUME);
        }
    }

    static void a(Context context) {
        if (!a.getAndSet(true)) {
            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new b());
        }
    }

    private static void a(FragmentManager fragmentManager, State state) {
        Collection<Object> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Object obj : fragments) {
                if (obj != null) {
                    a(obj, state);
                    if (obj.isAdded()) {
                        a(obj.getChildFragmentManager(), state);
                    }
                }
            }
        }
    }

    private static void a(Object obj, State state) {
        if (obj instanceof i) {
            ((i) obj).a().a(state);
        }
    }

    private static void b(FragmentActivity fragmentActivity, State state) {
        a((Object) fragmentActivity, state);
        a(fragmentActivity.getSupportFragmentManager(), state);
    }

    private static void b(Fragment fragment, Event event) {
        if (fragment instanceof i) {
            ((i) fragment).a().a(event);
        }
    }
}
