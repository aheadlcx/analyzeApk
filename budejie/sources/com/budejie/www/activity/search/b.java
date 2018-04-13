package com.budejie.www.activity.search;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.AdapterView.OnItemClickListener;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.a.a;

public class b extends a {
    private b$a h;
    private List<PlateBean> i;
    private a<String> j = new b$1(this);
    private OnItemClickListener k = new b$2(this);

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        d();
    }

    private void d() {
        this.d.setVisibility(8);
        this.a = 1;
        this.i = new ArrayList();
        this.h = new b$a(this, null);
        this.c.setOnItemClickListener(this.k);
        this.c.setPullRefreshEnable(false);
        this.c.setPullLoadEnable(false);
        this.c.setAdapter(this.h);
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(this.f)) {
            this.f = str;
            c(str);
            if (this.g != null) {
                this.g.a(1, str, false);
            }
        }
    }

    private synchronized void c(String str) {
        BudejieApplication.a.a(RequstMethod.GET, j.l(str), new j(getActivity()), this.j);
    }
}
