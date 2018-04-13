package com.budejie.www.activity.recommend;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.budejie.www.bean.SubscibeBean;
import java.util.HashMap;
import java.util.List;

public class d {
    private FragmentActivity a;
    private int b;
    private HashMap<String, d$a> c = new HashMap();
    private d$a d;

    public d(FragmentActivity fragmentActivity, int i) {
        this.a = fragmentActivity;
        this.b = i;
    }

    public void a(List<SubscibeBean> list) {
        if (list != null) {
            for (SubscibeBean subscibeBean : list) {
                Bundle bundle = new Bundle();
                bundle.putString("category_id", subscibeBean.id);
                a(subscibeBean.name, b.class, bundle);
            }
        }
    }

    public void a(String str, Class<?> cls, Bundle bundle) {
        d$a d_a = new d$a(this, str, cls, bundle);
        d_a.d = this.a.getSupportFragmentManager().findFragmentByTag(str);
        if (!(d_a.d == null || d_a.d.isDetached())) {
            this.a.getSupportFragmentManager().beginTransaction().detach(d_a.d).commitAllowingStateLoss();
        }
        this.c.put(str, d_a);
    }

    public void a(String str) {
        d$a d_a = (d$a) this.c.get(str);
        if (this.d != d_a) {
            FragmentTransaction beginTransaction = this.a.getSupportFragmentManager().beginTransaction();
            if (!(this.d == null || this.d.d == null)) {
                beginTransaction.detach(this.d.d);
            }
            if (d_a != null) {
                if (d_a.d == null) {
                    d_a.d = Fragment.instantiate(this.a, d_a.b.getName(), d_a.c);
                    beginTransaction.add(this.b, d_a.d, d_a.a);
                } else {
                    beginTransaction.attach(d_a.d);
                }
            }
            this.d = d_a;
            beginTransaction.commitAllowingStateLoss();
            this.a.getSupportFragmentManager().executePendingTransactions();
        }
    }
}
