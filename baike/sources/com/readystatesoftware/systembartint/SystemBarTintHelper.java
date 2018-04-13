package com.readystatesoftware.systembartint;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class SystemBarTintHelper {
    private View a;
    private Activity b;

    public SystemBarTintHelper(Activity activity) {
        this.b = activity;
    }

    public SystemBarTintHelper setView(int i) {
        this.a = LayoutInflater.from(this.b).inflate(i, null);
        this.b.setContentView(this.a);
        return this;
    }

    @TargetApi(19)
    private void a(boolean z) {
        Window window = this.b.getWindow();
        LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags |= 67108864;
        } else {
            attributes.flags &= -67108865;
        }
        window.setAttributes(attributes);
    }

    public void enableSystembarTint() {
        enableSystembarTint(0);
    }

    @SuppressLint({"NewApi"})
    public void enableSystembarTint(int i) {
        if (VERSION.SDK_INT >= 19) {
            if (SystemBarTintManager.sPostLollipop) {
                a(false);
            } else {
                a(true);
            }
            if (this.a != null && VERSION.SDK_INT >= 14) {
                this.a.setFitsSystemWindows(true);
            }
            SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this.b);
            systemBarTintManager.setStatusBarTintEnabled(true);
            systemBarTintManager.setStatusBarDarkMode(UIHelper.isNightTheme(), this.b);
            if (i == 0) {
                try {
                    TypedArray obtainStyledAttributes = this.b.obtainStyledAttributes(new int[]{16843470});
                    TypedValue typedValue = new TypedValue();
                    if (obtainStyledAttributes.length() > 0) {
                        obtainStyledAttributes.getValue(0, typedValue);
                        obtainStyledAttributes = this.b.obtainStyledAttributes(typedValue.data, new int[]{16842964});
                        i = obtainStyledAttributes.getColor(0, 0);
                    }
                    obtainStyledAttributes.recycle();
                } catch (Exception e) {
                }
                if (i == 0) {
                    i = this.b.getResources().getColor(UIHelper.isNightTheme() ? R.color.actionbar_dark : R.color.actionbar_day);
                }
            }
            Log.e("systembar", "set color " + i);
            systemBarTintManager.setStatusBarTintColor(i);
        }
    }
}
