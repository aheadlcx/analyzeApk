package com.budejie.www.activity.auditpost;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.budejie.www.activity.view.JazzyViewPager;
import com.budejie.www.bean.TouGaoItem;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class b extends FragmentStatePagerAdapter {
    private ArrayList<TouGaoItem> a = new ArrayList();
    private JazzyViewPager b;
    private SparseArray<WeakReference<a>> c = new SparseArray();

    public /* synthetic */ Fragment getItem(int i) {
        return a(i);
    }

    public b(FragmentManager fragmentManager, JazzyViewPager jazzyViewPager) {
        super(fragmentManager);
        this.b = jazzyViewPager;
    }

    public void a(ArrayList<TouGaoItem> arrayList) {
        if (arrayList != null) {
            this.a.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        return this.a.size();
    }

    public a a(int i) {
        return c(i);
    }

    public void destroyItem(View view, int i, Object obj) {
        super.destroyItem(view, i, obj);
        this.c.remove(Integer.valueOf(i).intValue());
    }

    public TouGaoItem b(int i) {
        if (i < 0 || i >= this.a.size()) {
            return null;
        }
        return (TouGaoItem) this.a.get(i);
    }

    public void a() {
        this.a.clear();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        this.c.put(Integer.valueOf(i).intValue(), new WeakReference(a.a((TouGaoItem) this.a.get(i))));
        Object instantiateItem = super.instantiateItem(viewGroup, i);
        this.b.a(instantiateItem, i);
        return instantiateItem;
    }

    public a c(int i) {
        WeakReference weakReference = (WeakReference) this.c.get(i);
        if (weakReference != null) {
            return (a) weakReference.get();
        }
        return null;
    }
}
