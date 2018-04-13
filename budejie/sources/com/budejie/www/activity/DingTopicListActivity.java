package com.budejie.www.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseTitleActivity;
import com.budejie.www.adapter.a.b;
import com.budejie.www.bean.HeadPortraitData;
import com.budejie.www.bean.HeadPortraitItem;
import com.budejie.www.http.c;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.budejie.www.widget.XListView;
import com.budejie.www.widget.XListView.a;

public class DingTopicListActivity extends BaseTitleActivity implements a {
    private static String a = "DingTopicListActivity";
    private XListView b;
    private b c;
    private boolean d = true;
    private boolean e = false;
    private Activity i;
    private c j;
    private String k;
    private int l = 0;
    private OnItemClickListener m = new OnItemClickListener(this) {
        final /* synthetic */ DingTopicListActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                an.b(this.a.i, ((HeadPortraitItem) this.a.b.getItemAtPosition(i)).getUserid());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private net.tsz.afinal.a.a n = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ DingTopicListActivity a;

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
            Log.e(DingTopicListActivity.a, "result&&" + obj.toString());
            if (obj == null) {
                Toast.makeText(this.a, "数据为空", 0).show();
                return;
            }
            HeadPortraitData v = z.v(obj.toString());
            if (v != null) {
                int count = v.getCount();
                if (!this.a.e) {
                    if (this.a.d) {
                        this.a.d = false;
                        this.a.c.a();
                    }
                    if (count <= 0 || v.getLists().size() <= 0) {
                        this.a.b.setPullLoadEnable(false);
                        this.a.b.a("没有更多的数据。", null);
                    } else {
                        this.a.c.a(v.getLists());
                        this.a.b.setPullLoadEnable(false);
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
        Intent intent = getIntent();
        if (intent != null) {
            this.k = intent.getStringExtra("dataid");
        }
        a("最近点赞的人");
        h();
        g();
    }

    private void g() {
        i();
        e();
    }

    private void h() {
        this.c = new b(this);
        this.b = (XListView) findViewById(R.id.listview);
        this.b.setXListViewListener(this);
        this.b.setPullLoadEnable(false);
        this.b.setAdapter(this.c);
        this.b.setOnItemClickListener(this.m);
    }

    public void a() {
        this.d = true;
        this.l = 0;
        i();
    }

    public void b() {
        this.d = false;
        this.l++;
        i();
    }

    private synchronized void i() {
        if (!an.a((Context) this)) {
            Toast.makeText(this, getString(R.string.nonet), 0).show();
        } else if (an.a(this.i) && !TextUtils.isEmpty(this.k)) {
            this.e = true;
            this.j.a(this.k, this.l, 30, this.n);
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.c != null) {
            this.c.notifyDataSetChanged();
        }
    }
}
