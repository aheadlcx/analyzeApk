package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({Scope.LIBRARY_GROUP})
public class d extends Fragment {
    private static final a a = new a();
    private r b = new r();

    static class a {
        private Map<Activity, d> a = new HashMap();
        private Map<Fragment, d> b = new HashMap();
        private ActivityLifecycleCallbacks c = new b(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onActivityDestroyed(Activity activity) {
                if (((d) this.a.a.remove(activity)) != null) {
                    Log.e("ViewModelStores", "Failed to save a ViewModel for " + activity);
                }
            }
        };
        private boolean d = false;
        private FragmentLifecycleCallbacks e = new FragmentLifecycleCallbacks(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onFragmentDestroyed(FragmentManager fragmentManager, Fragment fragment) {
                super.onFragmentDestroyed(fragmentManager, fragment);
                if (((d) this.a.b.remove(fragment)) != null) {
                    Log.e("ViewModelStores", "Failed to save a ViewModel for " + fragment);
                }
            }
        };

        a() {
        }

        void a(Fragment fragment) {
            Fragment parentFragment = fragment.getParentFragment();
            if (parentFragment != null) {
                this.b.remove(parentFragment);
                parentFragment.getFragmentManager().unregisterFragmentLifecycleCallbacks(this.e);
                return;
            }
            this.a.remove(fragment.getActivity());
        }

        private static d a(FragmentManager fragmentManager) {
            if (fragmentManager.isDestroyed()) {
                throw new IllegalStateException("Can't access ViewModels from onDestroy");
            }
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("android.arch.lifecycle.state.StateProviderHolderFragment");
            if (findFragmentByTag == null || (findFragmentByTag instanceof d)) {
                return (d) findFragmentByTag;
            }
            throw new IllegalStateException("Unexpected fragment instance was returned by HOLDER_TAG");
        }

        private static d b(FragmentManager fragmentManager) {
            Fragment dVar = new d();
            fragmentManager.beginTransaction().add(dVar, "android.arch.lifecycle.state.StateProviderHolderFragment").commitAllowingStateLoss();
            return dVar;
        }

        d a(FragmentActivity fragmentActivity) {
            FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
            d a = a(supportFragmentManager);
            if (a != null) {
                return a;
            }
            a = (d) this.a.get(fragmentActivity);
            if (a != null) {
                return a;
            }
            if (!this.d) {
                this.d = true;
                fragmentActivity.getApplication().registerActivityLifecycleCallbacks(this.c);
            }
            a = b(supportFragmentManager);
            this.a.put(fragmentActivity, a);
            return a;
        }

        d b(Fragment fragment) {
            FragmentManager childFragmentManager = fragment.getChildFragmentManager();
            d a = a(childFragmentManager);
            if (a != null) {
                return a;
            }
            a = (d) this.b.get(fragment);
            if (a != null) {
                return a;
            }
            fragment.getFragmentManager().registerFragmentLifecycleCallbacks(this.e, false);
            a = b(childFragmentManager);
            this.b.put(fragment, a);
            return a;
        }
    }

    public d() {
        setRetainInstance(true);
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        a.a((Fragment) this);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        this.b.a();
    }

    public r a() {
        return this.b;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static d a(FragmentActivity fragmentActivity) {
        return a.a(fragmentActivity);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static d a(Fragment fragment) {
        return a.b(fragment);
    }
}
