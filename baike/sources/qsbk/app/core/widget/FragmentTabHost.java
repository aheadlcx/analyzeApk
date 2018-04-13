package qsbk.app.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import java.util.ArrayList;

public class FragmentTabHost extends TabHost implements OnTabChangeListener {
    private final ArrayList<FragmentTabHost$b> a = new ArrayList();
    private FrameLayout b;
    private Context c;
    private FragmentManager d;
    private int e;
    private OnTabChangeListener f;
    private FragmentTabHost$b g;
    private boolean h;

    public FragmentTabHost(Context context) {
        super(context, null);
        a(context, null);
    }

    public FragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16842995}, 0, 0);
        this.e = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    private void a(Context context) {
        if (findViewById(16908307) == null) {
            View linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            addView(linearLayout, new LayoutParams(-1, -1));
            View tabWidget = new TabWidget(context);
            tabWidget.setId(16908307);
            tabWidget.setOrientation(0);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(-1, -2, 0.0f));
            tabWidget = new FrameLayout(context);
            tabWidget.setId(16908305);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(0, 0, 0.0f));
            tabWidget = new FrameLayout(context);
            this.b = tabWidget;
            this.b.setId(this.e);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        }
    }

    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    public void setup(Context context, FragmentManager fragmentManager) {
        a(context);
        super.setup();
        this.c = context;
        this.d = fragmentManager;
        a();
    }

    public void setup(Context context, FragmentManager fragmentManager, int i) {
        a(context);
        super.setup();
        this.c = context;
        this.d = fragmentManager;
        this.e = i;
        a();
        this.b.setId(i);
        if (getId() == -1) {
            setId(16908306);
        }
    }

    private void a() {
        if (this.b == null) {
            this.b = (FrameLayout) findViewById(this.e);
            if (this.b == null) {
                throw new IllegalStateException("No tab content FrameLayout found for id " + this.e);
            }
        }
    }

    public void setOnTabChangedListener(OnTabChangeListener onTabChangeListener) {
        this.f = onTabChangeListener;
    }

    public void addTab(TabSpec tabSpec, Class<?> cls, Bundle bundle) {
        tabSpec.setContent(new FragmentTabHost$a(this.c));
        String tag = tabSpec.getTag();
        FragmentTabHost$b fragmentTabHost$b = new FragmentTabHost$b(tag, cls, bundle);
        if (this.h) {
            FragmentTabHost$b.a(fragmentTabHost$b, this.d.findFragmentByTag(tag));
            if (!(FragmentTabHost$b.a(fragmentTabHost$b) == null || FragmentTabHost$b.a(fragmentTabHost$b).isDetached())) {
                FragmentTransaction beginTransaction = this.d.beginTransaction();
                beginTransaction.hide(FragmentTabHost$b.a(fragmentTabHost$b));
                beginTransaction.commitAllowingStateLoss();
            }
        }
        this.a.add(fragmentTabHost$b);
        addTab(tabSpec);
    }

    public void replaceTab(int i, Class<?> cls) {
        FragmentTabHost$b fragmentTabHost$b = (FragmentTabHost$b) this.a.get(i);
        if (fragmentTabHost$b != null && !FragmentTabHost$b.b(fragmentTabHost$b).getSimpleName().equals(cls.getSimpleName())) {
            FragmentTabHost$b.a(fragmentTabHost$b, cls);
            FragmentTransaction beginTransaction = this.d.beginTransaction();
            if (FragmentTabHost$b.a(fragmentTabHost$b) != null) {
                beginTransaction.remove(FragmentTabHost$b.a(fragmentTabHost$b));
            }
            beginTransaction.commit();
            FragmentTabHost$b.a(fragmentTabHost$b, null);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        FragmentTransaction fragmentTransaction = null;
        for (int i = 0; i < this.a.size(); i++) {
            FragmentTabHost$b fragmentTabHost$b = (FragmentTabHost$b) this.a.get(i);
            FragmentTabHost$b.a(fragmentTabHost$b, this.d.findFragmentByTag(FragmentTabHost$b.c(fragmentTabHost$b)));
            if (FragmentTabHost$b.a(fragmentTabHost$b) != null) {
                if (FragmentTabHost$b.c(fragmentTabHost$b).equals(currentTabTag)) {
                    this.g = fragmentTabHost$b;
                } else {
                    if (fragmentTransaction == null) {
                        fragmentTransaction = this.d.beginTransaction();
                    }
                    fragmentTransaction.hide(FragmentTabHost$b.a(fragmentTabHost$b));
                }
            }
        }
        this.h = true;
        FragmentTransaction a = a(currentTabTag, fragmentTransaction);
        if (a != null) {
            a.commitAllowingStateLoss();
            this.d.executePendingTransactions();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.h = false;
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable fragmentTabHost$SavedState = new FragmentTabHost$SavedState(super.onSaveInstanceState());
        fragmentTabHost$SavedState.a = getCurrentTabTag();
        return fragmentTabHost$SavedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        FragmentTabHost$SavedState fragmentTabHost$SavedState = (FragmentTabHost$SavedState) parcelable;
        super.onRestoreInstanceState(fragmentTabHost$SavedState.getSuperState());
        setCurrentTabByTag(fragmentTabHost$SavedState.a);
    }

    public void onTabChanged(String str) {
        if (this.h) {
            FragmentTransaction a = a(str, null);
            if (a != null) {
                a.commitAllowingStateLoss();
            }
        }
        if (this.f != null) {
            this.f.onTabChanged(str);
        }
    }

    private FragmentTransaction a(String str, FragmentTransaction fragmentTransaction) {
        FragmentTabHost$b fragmentTabHost$b = null;
        int i = 0;
        while (i < this.a.size()) {
            FragmentTabHost$b fragmentTabHost$b2 = (FragmentTabHost$b) this.a.get(i);
            if (!FragmentTabHost$b.c(fragmentTabHost$b2).equals(str)) {
                fragmentTabHost$b2 = fragmentTabHost$b;
            }
            i++;
            fragmentTabHost$b = fragmentTabHost$b2;
        }
        if (fragmentTabHost$b == null) {
            throw new IllegalStateException("No tab known for tag " + str);
        }
        if (this.g != fragmentTabHost$b) {
            if (fragmentTransaction == null) {
                fragmentTransaction = this.d.beginTransaction();
            }
            if (!(this.g == null || FragmentTabHost$b.a(this.g) == null)) {
                fragmentTransaction.hide(FragmentTabHost$b.a(this.g));
            }
            if (fragmentTabHost$b != null) {
                if (FragmentTabHost$b.a(fragmentTabHost$b) == null) {
                    FragmentTabHost$b.a(fragmentTabHost$b, Fragment.instantiate(this.c, FragmentTabHost$b.b(fragmentTabHost$b).getName(), FragmentTabHost$b.d(fragmentTabHost$b)));
                    fragmentTransaction.add(this.e, FragmentTabHost$b.a(fragmentTabHost$b), FragmentTabHost$b.c(fragmentTabHost$b));
                } else {
                    fragmentTransaction.show(FragmentTabHost$b.a(fragmentTabHost$b));
                }
            }
            this.g = fragmentTabHost$b;
        }
        return fragmentTransaction;
    }
}
