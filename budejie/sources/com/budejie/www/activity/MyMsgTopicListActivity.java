package com.budejie.www.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseTitleActivity;
import com.budejie.www.adapter.a.d;
import com.budejie.www.bean.MyMsgItem;
import com.budejie.www.bean.MyMsgListData;
import com.budejie.www.http.c;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.budejie.www.widget.XListView;
import com.budejie.www.widget.XListView.a;

public class MyMsgTopicListActivity extends BaseTitleActivity implements a {
    private static String a = "MyMsgTopicListActivity";
    private XListView b;
    private d c;
    private boolean d = true;
    private boolean e = false;
    private Activity i;
    private c j;
    private int k = 0;
    private OnItemClickListener l = new OnItemClickListener(this) {
        final /* synthetic */ MyMsgTopicListActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                an.b(this.a.i, ((MyMsgItem) this.a.b.getItemAtPosition(i)).getUserid() + "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private net.tsz.afinal.a.a m = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ MyMsgTopicListActivity a;

        {
            this.a = r1;
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.f();
            this.a.e = false;
            this.a.b.b();
            this.a.b.c();
            Toast.makeText(this.a, str, 0).show();
        }

        public void onSuccess(Object obj) {
            this.a.f();
            this.a.b.setLastRefreshTime(System.currentTimeMillis());
            this.a.e = false;
            this.a.b.b();
            this.a.b.c();
            Log.e(MyMsgTopicListActivity.a, "result&&" + obj.toString());
            if (obj == null) {
                Toast.makeText(this.a, "数据为空", 0).show();
                return;
            }
            MyMsgListData w = z.w(obj.toString());
            if (w != null) {
                this.a.k = w.getMaxtime();
                int count = w.getCount();
                if (!this.a.e) {
                    if (this.a.d) {
                        this.a.d = false;
                        this.a.c.a();
                    }
                    this.a.a("赞过我的");
                    if (count <= 0 || this.a.c.getCount() >= w.getCount()) {
                        this.a.b.setPullLoadEnable(false);
                        this.a.b.a("没有更多的数据。", null);
                    } else {
                        this.a.c.a(w.getLists());
                        this.a.b.setPullLoadEnable(true);
                    }
                } else {
                    return;
                }
            }
            super.onSuccess(obj);
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e(R.layout.fans_list_layout);
        this.i = this;
        this.j = new c(this.i, null);
        getIntent();
        h();
        g();
    }

    private void g() {
        i();
        e();
    }

    private void h() {
        this.c = new d(this);
        this.b = (XListView) findViewById(R.id.listview);
        this.b.setXListViewListener(this);
        this.b.setPullLoadEnable(false);
        this.b.setAdapter(this.c);
        this.b.setOnItemClickListener(this.l);
    }

    public void a() {
        this.d = true;
        this.k = 0;
        i();
    }

    public void b() {
        this.d = false;
        i();
    }

    private synchronized void i() {
        if (!an.a((Context) this)) {
            Toast.makeText(this, getString(R.string.nonet), 0).show();
        } else if (an.a(this.i)) {
            this.e = true;
            this.j.b(an.f((Context) this), this.k, 50, this.m);
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.c != null) {
            this.c.notifyDataSetChanged();
        }
    }
}
