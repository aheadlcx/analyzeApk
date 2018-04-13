package com.zhihu.matisse;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.zhihu.matisse.ui.MatisseActivity;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Set;

public final class Matisse {
    private final WeakReference<Activity> mContext;
    private final WeakReference<Fragment> mFragment;

    private Matisse(Activity activity) {
        this(activity, null);
    }

    private Matisse(Fragment fragment) {
        this(fragment.getActivity(), fragment);
    }

    private Matisse(Activity activity, Fragment fragment) {
        this.mContext = new WeakReference(activity);
        this.mFragment = new WeakReference(fragment);
    }

    public static Matisse from(Activity activity) {
        return new Matisse(activity);
    }

    public static Matisse from(Fragment fragment) {
        return new Matisse(fragment);
    }

    public static List<ResultItem> obtainResult(Intent intent) {
        return intent.getParcelableArrayListExtra(MatisseActivity.EXTRA_RESULT_SELECTION);
    }

    public SelectionCreator choose(Set<MimeType> set) {
        return choose(set, true);
    }

    public SelectionCreator choose(Set<MimeType> set, boolean z) {
        return new SelectionCreator(this, set, z);
    }

    @Nullable
    Activity getActivity() {
        return (Activity) this.mContext.get();
    }

    @Nullable
    Fragment getFragment() {
        return this.mFragment != null ? (Fragment) this.mFragment.get() : null;
    }
}
