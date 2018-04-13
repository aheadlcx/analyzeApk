package android.arch.lifecycle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.p.b;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class q {
    @SuppressLint({"StaticFieldLeak"})
    private static a a;

    public static class a extends b {
        private Application a;

        public a(@NonNull Application application) {
            this.a = application;
        }

        @NonNull
        public <T extends o> T a(@NonNull Class<T> cls) {
            if (!AndroidViewModel.class.isAssignableFrom(cls)) {
                return super.a(cls);
            }
            try {
                return (o) cls.getConstructor(new Class[]{Application.class}).newInstance(new Object[]{this.a});
            } catch (Throwable e) {
                throw new RuntimeException("Cannot create an instance of " + cls, e);
            } catch (Throwable e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            } catch (Throwable e22) {
                throw new RuntimeException("Cannot create an instance of " + cls, e22);
            } catch (Throwable e222) {
                throw new RuntimeException("Cannot create an instance of " + cls, e222);
            }
        }
    }

    private static void a(Application application) {
        if (a == null) {
            a = new a(application);
        }
    }

    private static Application a(Activity activity) {
        Application application = activity.getApplication();
        if (application != null) {
            return application;
        }
        throw new IllegalStateException("Your activity/fragment is not yet attached to Application. You can't request ViewModel before onCreate call.");
    }

    private static Activity b(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException("Can't create ViewModelProvider for detached fragment");
    }

    @MainThread
    public static p a(@NonNull Fragment fragment) {
        a(a(b(fragment)));
        return new p(s.a(fragment), a);
    }

    @MainThread
    public static p a(@NonNull FragmentActivity fragmentActivity) {
        a(a((Activity) fragmentActivity));
        return new p(s.a(fragmentActivity), a);
    }
}
