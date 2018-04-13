package qsbk.app.core.utils;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import qsbk.app.core.ui.base.BaseActivity;

public class KeyBoardUtils {
    private boolean a = true;
    private int b;
    private int c;
    private int d;
    private OnKeyboardHiddenChangedListener e;

    public static abstract class OnKeyboardHiddenChangedListener {
        private KeyBoardUtils a;

        public abstract void onNavigationBarChanged(int i);

        public abstract void onSoftKeyboardHiddenChanged(int i, boolean z);

        public void onKeyboardHiddenChanged(int i, boolean z) {
            if (isVirutalNavigationChanged(i - getPreviousKeyboardHeight())) {
                onNavigationBarChanged(i);
            } else {
                onSoftKeyboardHiddenChanged(i, z);
            }
        }

        public void attach(KeyBoardUtils keyBoardUtils) {
            this.a = keyBoardUtils;
        }

        public boolean isVirutalNavigationChanged(int i) {
            return this.a.isVirutalNavigationChanged(i);
        }

        public int getNavigationHideHeight() {
            return this.a.getNavigationHideHeight();
        }

        public int getPreviousKeyboardHeight() {
            return this.a.getPreviousKeyboardHeight();
        }

        public boolean isKeyboardHide() {
            return this.a.isKeyboardHide();
        }
    }

    public static void hideSoftInput(Context context) {
        if (context instanceof Activity) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
            View currentFocus = ((Activity) context).getCurrentFocus();
            if (currentFocus != null) {
                IBinder windowToken = currentFocus.getWindowToken();
                if (inputMethodManager != null && windowToken != null) {
                    try {
                        inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void toggleSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(0, 2);
        }
    }

    public static void showSoftInput(Context context) {
        if (context instanceof Activity) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
            View currentFocus = ((Activity) context).getCurrentFocus();
            if (inputMethodManager != null && currentFocus != null) {
                try {
                    inputMethodManager.showSoftInput(currentFocus, 0);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setOnKeyboadHiddenChangedListener(BaseActivity baseActivity, OnKeyboardHiddenChangedListener onKeyboardHiddenChangedListener) {
        this.e = onKeyboardHiddenChangedListener;
        if (baseActivity != null && onKeyboardHiddenChangedListener != null && DeviceUtils.getSystemSDKInt() > 18) {
            this.e.attach(this);
            this.b = WindowUtils.getNavigationBarHeight();
            View decorView = baseActivity.getWindow().getDecorView();
            decorView.getViewTreeObserver().addOnGlobalLayoutListener(new o(this, baseActivity, decorView));
        }
    }

    public boolean isVirutalNavigationChanged(int i) {
        return Math.abs(i) == this.b;
    }

    public int getNavigationHideHeight() {
        return this.d;
    }

    public int getPreviousKeyboardHeight() {
        return this.c;
    }

    public boolean isKeyboardHide() {
        return this.a;
    }

    public static KeyBoardUtils build() {
        return new KeyBoardUtils();
    }
}
