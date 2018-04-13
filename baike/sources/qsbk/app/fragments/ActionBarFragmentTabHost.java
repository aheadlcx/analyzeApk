package qsbk.app.fragments;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.util.AttributeSet;
import qsbk.app.widget.ActionBarTabPanel;
import qsbk.app.widget.TabPanel;

public class ActionBarFragmentTabHost extends HintFragmentTabHost {
    private ActionBar b;

    public ActionBarFragmentTabHost(Context context) {
        super(context);
    }

    public ActionBarFragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActionBarFragmentTabHost(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setup(ActionBar actionBar, FragmentManager fragmentManager, int i) {
        if (actionBar == null || fragmentManager == null) {
            throw new IllegalStateException("Wrong params. ActionBar: " + actionBar + " , FragmentManager: " + fragmentManager);
        }
        super.setup(fragmentManager, i);
        this.b = actionBar;
        a();
    }

    public void setup(ActionBar actionBar, FragmentManager fragmentManager, ActionBarTabPanel actionBarTabPanel) {
        if (actionBar == null || fragmentManager == null) {
            throw new IllegalStateException("Wrong params. ActionBar: " + actionBar + " , FragmentManager: " + fragmentManager);
        }
        super.setup(fragmentManager, (TabPanel) actionBarTabPanel);
        this.b = actionBar;
        a();
    }

    private void a() {
        this.b.setDisplayShowCustomEnabled(true);
        this.b.setDisplayShowHomeEnabled(false);
        this.b.setDisplayShowTitleEnabled(false);
        this.b.setDisplayUseLogoEnabled(false);
        this.b.setDisplayHomeAsUpEnabled(false);
        this.b.setCustomView(this.a, new LayoutParams(-1, -1, 3));
    }
}
