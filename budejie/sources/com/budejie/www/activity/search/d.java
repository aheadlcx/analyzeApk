package com.budejie.www.activity.search;

import android.os.Bundle;
import android.text.TextUtils;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.adapter.a.o;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.widget.XListView.a;

public class d extends a {
    private o h;
    private j i;
    private int j = 1;
    private a k = new d$1(this);

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        d();
    }

    private void d() {
        this.d.setVisibility(8);
        this.a = 2;
        this.h = new o(getActivity());
        this.c.setAdapter(this.h);
        this.i = new j();
        this.c.setPullRefreshEnable(false);
        this.c.setPullLoadEnable(false);
        this.c.setXListViewListener(this.k);
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
        if (this.i != null) {
            b();
            BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.i.e(getActivity(), str, "1"), new d$a(this, 1, null));
        }
    }
}
