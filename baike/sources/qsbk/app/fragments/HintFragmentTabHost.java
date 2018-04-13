package qsbk.app.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View.BaseSavedState;
import android.widget.FrameLayout;
import com.alipay.sdk.util.h;
import java.util.ArrayList;
import java.util.HashMap;
import qsbk.app.utils.LogUtil;
import qsbk.app.widget.TabPanel;
import qsbk.app.widget.TabPanel.TabBarItem;

public class HintFragmentTabHost extends FrameLayout implements qsbk.app.widget.TabPanel.OnTabClickListener {
    public static HashMap<String, Boolean> showTipsMap = new HashMap();
    protected TabPanel a;
    private final ArrayList<a> b = new ArrayList();
    private FragmentManager c;
    private a d;
    private boolean e;
    private OnTabClickListener f;
    private String g;
    private int h;

    public interface OnTabClickListener {
        void OnTabClick(String str, Fragment fragment);
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new cr();
        String a;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.a);
        }

        public String toString() {
            return "HintFragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.a + h.d;
        }
    }

    static final class a {
        private String a;
        private Class<?> b;
        private Bundle c;
        private Fragment d;

        a(String str, Class<?> cls, Bundle bundle) {
            this.a = str;
            this.b = cls;
            this.c = bundle;
        }
    }

    public HintFragmentTabHost(Context context) {
        super(context);
    }

    public HintFragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HintFragmentTabHost(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setup(FragmentManager fragmentManager, int i) {
        this.h = i;
        setup(fragmentManager, (TabPanel) ((Activity) getContext()).findViewById(i));
    }

    public void setup(FragmentManager fragmentManager, TabPanel tabPanel) {
        if (getId() <= 0) {
            setId(16908290);
        }
        this.c = fragmentManager;
        this.a = tabPanel;
        a();
    }

    private void a() {
        if (this.a == null) {
            throw new IllegalStateException("No TabPanel found.");
        }
        this.a.setOnTabClickListener(this);
    }

    public void addTab(TabBarItem tabBarItem, Class<?> cls, Bundle bundle) {
        if (this.a == null || this.c == null) {
            throw new IllegalStateException("Did you forget to call 'public void setup(ActionBar ab, FragmentManager fm).");
        }
        String id = tabBarItem.getId();
        a aVar = new a(id, cls, bundle);
        if (this.e) {
            aVar.d = this.c.findFragmentByTag(id);
            if (!(aVar.d == null || aVar.d.isDetached())) {
                FragmentTransaction beginTransaction = this.c.beginTransaction();
                beginTransaction.detach(aVar.d);
                beginTransaction.commit();
            }
        }
        this.b.add(aVar);
        this.a.addTab(tabBarItem);
    }

    public void updateTab(String str, Class<?> cls, Bundle bundle) {
        int size = this.b.size();
        int i = 0;
        a aVar = null;
        while (i < size) {
            a aVar2 = (a) this.b.get(i);
            if (!aVar2.a.equals(str)) {
                aVar2 = aVar;
            }
            i++;
            aVar = aVar2;
        }
        if (aVar == null) {
            throw new IllegalStateException("No tab known for tag " + str);
        }
        aVar.c = bundle;
        aVar.b = cls;
        if (aVar == this.d) {
            FragmentTransaction beginTransaction = this.c.beginTransaction();
            if (!(this.d == null || this.d.d == null)) {
                beginTransaction.detach(this.d.d);
            }
            if (aVar != null) {
                aVar.d = Fragment.instantiate(getContext(), cls.getName(), aVar.c);
                beginTransaction.add(getId(), aVar.d, aVar.a);
            }
            beginTransaction.commit();
            return;
        }
        aVar.d = null;
    }

    public void setTips(String str, String str2) {
        showTipsMap.put(str, Boolean.valueOf(true));
        this.a.setTips(str, str2);
    }

    public void setSmallTips(String str) {
        if (!showTipsMap.containsKey(str)) {
            this.a.setSmallTips(str);
        }
    }

    public void hideSmallTips(String str) {
        this.a.hideSmallTips(str);
    }

    public void hideTips(String str) {
        if (showTipsMap.containsKey(str)) {
            showTipsMap.remove(str);
        }
        this.a.hideTips(str);
    }

    public final void setWillAutoCancelTipsWhenSelected(boolean z) {
        this.a.setWillAutoCancelTipsWhenSelected(z);
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.f = onTabClickListener;
    }

    public Fragment getTabById(String str) {
        if (this.d == null || this.d.d == null) {
            LogUtil.d("on get tab by id:" + this.e);
            if (!this.e) {
                return null;
            }
            FragmentTransaction a = a(str, null);
            if (a != null) {
                a.commit();
            }
        }
        return this.d.d;
    }

    public void setHighlightedTab(String str, boolean z) {
        if (this.a != null) {
            this.a.setHighlightedTab(str, z);
        }
    }

    private FragmentTransaction a(String str, FragmentTransaction fragmentTransaction) {
        LogUtil.d("doTabChange:" + str + "ft:" + fragmentTransaction);
        a aVar = null;
        if (this.a != null) {
            this.a.setSelectedTab(str);
        }
        int size = this.b.size();
        int i = 0;
        while (i < size) {
            a aVar2 = (a) this.b.get(i);
            LogUtil.d("tab:" + aVar2.a + " tab class:" + aVar2.b);
            if (!aVar2.a.equals(str)) {
                aVar2 = aVar;
            }
            i++;
            aVar = aVar2;
        }
        if (aVar == null) {
            throw new IllegalStateException("No tab known for tag " + str);
        }
        LogUtil.d("mLastTab:" + this.d + " new Tab:" + aVar);
        if (this.d != aVar) {
            LogUtil.d("mLastTab not same with new tab");
            if (fragmentTransaction == null) {
                LogUtil.d("ft null and new transaction");
                fragmentTransaction = this.c.beginTransaction();
            }
            if (this.d != null) {
                LogUtil.d("mLastTab is not nll");
                if (this.d.d != null) {
                    fragmentTransaction.detach(this.d.d);
                }
            }
            if (aVar != null) {
                LogUtil.d("newTab is not nll");
                if (aVar.d == null) {
                    aVar.d = Fragment.instantiate(getContext(), aVar.b.getName(), aVar.c);
                    fragmentTransaction.add(getId(), aVar.d, aVar.a);
                } else {
                    fragmentTransaction.attach(aVar.d);
                }
            }
            this.d = aVar;
        }
        return fragmentTransaction;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTab = getCurrentTab();
        LogUtil.d("on attach to window:" + currentTab);
        FragmentTransaction fragmentTransaction = null;
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            a aVar = (a) this.b.get(i);
            aVar.d = this.c.findFragmentByTag(aVar.a);
            if (!(aVar.d == null || aVar.d.isDetached())) {
                if (aVar.a.equals(currentTab)) {
                    LogUtil.d("mLast tab = " + aVar.a);
                    this.d = aVar;
                } else {
                    if (fragmentTransaction == null) {
                        fragmentTransaction = this.c.beginTransaction();
                    }
                    fragmentTransaction.detach(aVar.d);
                }
            }
        }
        this.e = true;
        LogUtil.d("on attach:");
        try {
            fragmentTransaction = a(currentTab, fragmentTransaction);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        if (fragmentTransaction != null) {
            try {
                fragmentTransaction.commitAllowingStateLoss();
            } catch (Exception e2) {
                LogUtil.d("commit fail");
                e2.printStackTrace();
            }
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.e = false;
        if (!(this.d == null || this.d.d == null)) {
            FragmentTransaction beginTransaction = this.c.beginTransaction();
            beginTransaction.detach(this.d.d);
            try {
                beginTransaction.commitAllowingStateLoss();
            } catch (IllegalStateException e) {
            }
        }
        this.d = null;
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = getCurrentTab();
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        LogUtil.d("on restore:" + savedState.a);
        setCurrentTab(savedState.a);
    }

    public String getCurrentTab() {
        return this.g;
    }

    public void setCurrentTab(String str) {
        this.g = str;
        if (this.e) {
            FragmentTransaction a = a(str, null);
            if (a != null) {
                a.commit();
            }
        }
    }

    public void onTabClick(String str) {
        this.g = str;
        if (this.e) {
            LogUtil.d("on tab click");
            FragmentTransaction a = a(str, null);
            if (a != null) {
                a.commitAllowingStateLoss();
            }
        }
        if (this.f != null) {
            this.f.OnTabClick(str, getTabById(str));
        }
    }
}
