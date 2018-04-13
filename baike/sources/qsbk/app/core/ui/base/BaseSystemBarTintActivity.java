package qsbk.app.core.ui.base;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import qsbk.app.core.R;
import qsbk.app.core.utils.WindowUtils;

public class BaseSystemBarTintActivity extends FragmentActivity {
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        Window window = getWindow();
        window.setBackgroundDrawable(null);
        if (isNeedHideStatusBar()) {
            hideStatusBar(window);
        } else if (isNeedImmersiveStatusBar()) {
            setImmersiveStatusBar(window);
        }
        if (isNeedHideNavigationBar()) {
            hideNavigationBar(window);
        } else if (isNeedImmersiveNavigationBar()) {
            setImmersiveNavigationBar(window);
        } else {
            WindowUtils.setNavigationBarColor(window, getResources().getColor(getNavigationBarColor()));
        }
    }

    protected void setImmersiveStatusBar(Window window) {
        if (VERSION.SDK_INT >= 19) {
            window.getDecorView().setSystemUiVisibility(1280);
        }
        WindowUtils.setStatusBarColor(this, window, getResources().getColor(getImmersiveStatusBarColor()), isNotBlurActivity());
    }

    protected boolean isNotBlurActivity() {
        return true;
    }

    protected boolean isNeedImmersiveStatusBar() {
        return false;
    }

    protected boolean isNeedHideStatusBar() {
        return false;
    }

    protected boolean isNeedImmersiveNavigationBar() {
        return false;
    }

    protected void setImmersiveNavigationBar(Window window) {
        if (VERSION.SDK_INT >= 19) {
            window.getDecorView().setSystemUiVisibility(768);
        }
        WindowUtils.setNavigationBarColor(window, getResources().getColor(getImmersiveNavigationBarColor()), isNotBlurActivity());
    }

    protected boolean isNeedHideNavigationBar() {
        return false;
    }

    protected void hideStatusBar(Window window) {
        int i = 0;
        window.addFlags(1024);
        if (VERSION.SDK_INT >= 16) {
            i = 4;
        }
        if (i != 0) {
            window.getDecorView().setSystemUiVisibility(i);
        }
    }

    protected void hideNavigationBar(Window window) {
        int i = 0;
        if (VERSION.SDK_INT >= 16) {
            i = 770;
            if (VERSION.SDK_INT >= 19) {
                i = 2818;
            }
        }
        if (i != 0) {
            window.getDecorView().setSystemUiVisibility(i);
        }
    }

    protected int getImmersiveNavigationBarColor() {
        if (WindowUtils.isXiaoMiMix()) {
            return R.color.black_30_percent_transparent;
        }
        return R.color.transparent;
    }

    protected int getNavigationBarColor() {
        return R.color.black;
    }

    protected int getImmersiveStatusBarColor() {
        return R.color.transparent;
    }

    protected boolean isNeedSystemBarTintEnable() {
        return true;
    }
}
