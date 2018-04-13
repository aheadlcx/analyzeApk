package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class s {
    @MainThread
    public static r a(@NonNull FragmentActivity fragmentActivity) {
        return d.a(fragmentActivity).a();
    }

    @MainThread
    public static r a(@NonNull Fragment fragment) {
        return d.a(fragment).a();
    }
}
