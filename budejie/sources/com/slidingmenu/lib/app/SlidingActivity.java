package com.slidingmenu.lib.app;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import cn.v6.sixrooms.ui.phone.BaseFragmentActivity;
import com.slidingmenu.lib.SlidingMenu;

public class SlidingActivity extends BaseFragmentActivity {
    private a mHelper;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mHelper = new a(this);
        this.mHelper.a(bundle);
    }

    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.mHelper.b(bundle);
    }

    public View findViewById(int i) {
        View findViewById = super.findViewById(i);
        return findViewById != null ? findViewById : this.mHelper.a(i);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mHelper.c(bundle);
    }

    public void setContentView(int i) {
        setContentView(getLayoutInflater().inflate(i, null));
    }

    public void setContentView(View view) {
        setContentView(view, new LayoutParams(-1, -1));
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.mHelper.a(view, layoutParams);
    }

    public void setBehindContentView(int i) {
        setBehindContentView(getLayoutInflater().inflate(i, null));
    }

    public void setBehindContentView(View view) {
        setBehindContentView(view, new LayoutParams(-1, -1));
    }

    public void setBehindContentView(View view, LayoutParams layoutParams) {
        this.mHelper.b(view, layoutParams);
    }

    public SlidingMenu getSlidingMenu() {
        return this.mHelper.a();
    }

    public void toggle() {
        this.mHelper.b();
    }

    public void showContent() {
        this.mHelper.c();
    }

    public void showMenu() {
        this.mHelper.d();
    }

    public void showSecondaryMenu() {
        this.mHelper.e();
    }

    public void setSlidingActionBarEnabled(boolean z) {
        this.mHelper.a(z);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        boolean a = this.mHelper.a(i, keyEvent);
        return a ? a : super.onKeyUp(i, keyEvent);
    }
}
