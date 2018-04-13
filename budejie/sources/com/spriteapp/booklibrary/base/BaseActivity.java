package com.spriteapp.booklibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import com.spriteapp.booklibrary.a.a;
import com.spriteapp.booklibrary.config.HuaXiSDK;
import com.spriteapp.booklibrary.util.StatusBarUtil;
import com.spriteapp.booklibrary.widget.loading.CustomDialog;

public abstract class BaseActivity extends AppCompatActivity {
    private CustomDialog dialog;

    public abstract void configViews();

    public abstract void findViewId();

    public abstract int getLayoutResId();

    public abstract void initData();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutResId());
        setStatusBarColor();
        findViewId();
        initData();
        configViews();
    }

    private void setStatusBarColor() {
        int statusBarColor = HuaXiSDK.getInstance().getConfig().getStatusBarColor();
        if (statusBarColor == 0) {
            statusBarColor = getResources().getColor(a.book_reader_bdj_main_color);
        }
        StatusBarUtil.setWindowStatusBarColor(this, statusBarColor);
    }

    protected void gone(View... viewArr) {
        if (viewArr != null && viewArr.length > 0) {
            for (View view : viewArr) {
                if (view != null) {
                    view.setVisibility(8);
                }
            }
        }
    }

    protected void visible(View... viewArr) {
        if (viewArr != null && viewArr.length > 0) {
            for (View view : viewArr) {
                if (view != null) {
                    view.setVisibility(0);
                }
            }
        }
    }

    protected void setSelectFalse(View... viewArr) {
        if (viewArr != null && viewArr.length > 0) {
            for (View view : viewArr) {
                if (view != null) {
                    view.setSelected(false);
                }
            }
        }
    }

    protected void hideStatusBar() {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.flags |= 1024;
        getWindow().setAttributes(attributes);
    }

    protected void showStatusBar() {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.flags &= -1025;
        getWindow().setAttributes(attributes);
    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == 0;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        HuaXiSDK.getInstance().onActivityResult(i, i2, intent);
    }

    public void showDialog() {
        if (this.dialog == null) {
            this.dialog = CustomDialog.instance(this);
            this.dialog.setCancelable(true);
        }
        this.dialog.show();
    }

    public void dismissDialog() {
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
            this.dialog = null;
        }
    }
}
