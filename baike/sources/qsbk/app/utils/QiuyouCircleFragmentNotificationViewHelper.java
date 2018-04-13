package qsbk.app.utils;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import qsbk.app.fragments.QiuyouCircleFragment;
import qsbk.app.widget.PtrLayout;

public class QiuyouCircleFragmentNotificationViewHelper {
    private static final String a = QiuyouCircleFragmentNotificationViewHelper.class.getSimpleName();
    private PtrLayout b;
    private QiuyouCircleFragment c;
    private Handler d = new Handler();
    private Animation e = new AlphaAnimation(0.0f, 1.0f);
    private Animation f = new AlphaAnimation(1.0f, 0.0f);
    private View g;
    private Runnable h;
    private Runnable i;

    public QiuyouCircleFragmentNotificationViewHelper(PtrLayout ptrLayout, QiuyouCircleFragment qiuyouCircleFragment) {
        this.b = ptrLayout;
        this.c = qiuyouCircleFragment;
        this.g = qiuyouCircleFragment.getQiuyouquanNotificationView();
        this.i = new ap(this);
        this.h = new aq(this);
        this.b.setOnScrollOffsetListener(new ar(this));
    }

    public static QiuyouCircleFragmentNotificationViewHelper newInstance4ChildFragment(PtrLayout ptrLayout, Fragment fragment) {
        if (!isValidChildFragment(fragment)) {
            Log.e(a, "fragment is not valid " + fragment);
            return null;
        } else if (ptrLayout != null && fragment != null) {
            return new QiuyouCircleFragmentNotificationViewHelper(ptrLayout, (QiuyouCircleFragment) fragment.getParentFragment());
        } else {
            Log.e(a, "fragment is not valid " + fragment);
            return null;
        }
    }

    public static boolean isValidChildFragment(Fragment fragment) {
        if (fragment == null) {
            return false;
        }
        Fragment parentFragment = fragment.getParentFragment();
        if (parentFragment == null || !(parentFragment instanceof QiuyouCircleFragment)) {
            return false;
        }
        return true;
    }

    public void release() {
        this.b = null;
        this.c = null;
        this.g.clearAnimation();
        this.g = null;
        this.f.cancel();
        this.f = null;
        this.e.cancel();
        this.e = null;
        this.d = null;
        this.h = null;
        this.i = null;
    }
}
