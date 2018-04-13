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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseTitleActivity;
import com.budejie.www.activity.recommend.SuggestedFollowsActivity;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.MyFollowResultInfo;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.widget.XListView;
import com.budejie.www.widget.XListView.a;
import com.google.gson.Gson;

public class MyFollowListActivity extends BaseTitleActivity implements a {
    private static String a = "MyFollowListActivity";
    private XListView b;
    private a c;
    private RelativeLayout d;
    private ImageView e;
    private TextView i;
    private LinearLayout j;
    private ImageView k;
    private String l = "";
    private String m = "";
    private long n = 0;
    private boolean o = true;
    private boolean p = true;
    private boolean q = false;
    private Activity r;
    private String s;
    private OnItemClickListener t = new OnItemClickListener(this) {
        final /* synthetic */ MyFollowListActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                view.findViewById(R.id.unread_num).setVisibility(8);
                Fans fans = (Fans) this.a.b.getItemAtPosition(i);
                fans.unread = 0;
                Intent intent = new Intent(this.a.r, PersonalProfileActivity.class);
                intent.putExtra(PersonalProfileActivity.c, fans.getId());
                BudejieApplication.a.a(RequstMethod.GET, j.a(), new j(this.a.r).f(this.a.r, fans.getId()), null);
                this.a.r.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private net.tsz.afinal.a.a u = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ MyFollowListActivity a;

        {
            this.a = r1;
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.f();
            this.a.q = false;
            this.a.b.b();
            this.a.b.c();
            Toast.makeText(this.a, str, 0).show();
        }

        public void onSuccess(Object obj) {
            boolean z = false;
            this.a.f();
            this.a.q = false;
            this.a.b.setLastRefreshTime(System.currentTimeMillis());
            ai.d(this.a, ai.c);
            this.a.b.b();
            this.a.b.c();
            Log.e(MyFollowListActivity.a, "result&&" + obj.toString());
            if (obj == null) {
                Toast.makeText(this.a, "数据为空", 0).show();
                return;
            }
            String obj2 = obj.toString();
            if (TextUtils.isEmpty(obj2)) {
                Toast.makeText(this.a, "数据为空", 0).show();
                return;
            }
            try {
                MyFollowResultInfo myFollowResultInfo = (MyFollowResultInfo) new Gson().fromJson(obj2, MyFollowResultInfo.class);
                if (myFollowResultInfo != null) {
                    this.a.n = myFollowResultInfo.info.np;
                    MyFollowListActivity myFollowListActivity = this.a;
                    if (this.a.n > 0) {
                        z = true;
                    }
                    myFollowListActivity.o = z;
                    if (!this.a.q) {
                        if (this.a.p) {
                            this.a.p = false;
                            this.a.c.a();
                        }
                        this.a.c.a(myFollowResultInfo.getFans());
                        if (this.a.c.isEmpty()) {
                            this.a.b.setVisibility(8);
                            if (TextUtils.isEmpty(this.a.s) || !this.a.s.equals(this.a.l)) {
                                if (BaseTitleActivity.g == 1) {
                                    this.a.k.setImageResource(R.drawable.ic_nofollows_title_night);
                                } else {
                                    this.a.k.setImageResource(R.drawable.ic_nofollows_title);
                                }
                                this.a.j.setVisibility(0);
                            } else {
                                if (BaseTitleActivity.g == 1) {
                                    this.a.k.setImageResource(R.drawable.ic_nofollows_self_title_night);
                                } else {
                                    this.a.k.setImageResource(R.drawable.ic_nofollows_self_title);
                                }
                                this.a.j.setVisibility(0);
                            }
                        } else {
                            this.a.b.setPullLoadEnable(true);
                        }
                        if (!this.a.o) {
                            this.a.b.setPullLoadEnable(false);
                            this.a.b.a("没有更多的数据。", null);
                        }
                    } else {
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.onSuccess(obj);
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e(R.layout.fans_list_layout);
        this.r = this;
        Intent intent = getIntent();
        if (intent != null) {
            this.l = intent.getStringExtra(HistoryOpenHelper.COLUMN_UID);
        }
        this.s = ai.b(getApplicationContext());
        g();
        h();
        c((int) R.string.suggested_follows);
        b(false);
        f(0);
        i();
        e();
        a("我的关注");
    }

    public void onRightClick(View view) {
        super.onRightClick(view);
        startActivity(new Intent(this, SuggestedFollowsActivity.class));
    }

    private void g() {
        this.c = new a(this);
        this.c.a(true);
        this.b = (XListView) findViewById(R.id.listview);
        this.b.setXListViewListener(this);
        this.b.setPullLoadEnable(false);
        this.b.setAdapter(this.c);
        this.b.setLastRefreshTime(ai.c((Context) this, ai.c).longValue());
        this.b.setOnItemClickListener(this.t);
    }

    private void h() {
        this.d = (RelativeLayout) findViewById(R.id.empty_layout);
        this.e = (ImageView) findViewById(R.id.empty_icon);
        this.i = (TextView) findViewById(R.id.empty_text);
        this.e.setImageResource(R.drawable.no_follow_icon);
        this.i.setText("一个人都木有关注呀！");
        this.j = (LinearLayout) findViewById(R.id.hintEmptyLayout);
        this.k = (ImageView) findViewById(R.id.hintEmptyIV);
    }

    public void a() {
        this.o = true;
        this.p = true;
        this.n = 0;
        i();
    }

    public void b() {
        this.p = false;
        i();
    }

    private synchronized void i() {
        if (!an.a((Context) this)) {
            Toast.makeText(this, getString(R.string.nonet), 0).show();
        } else if (!this.q) {
            this.q = true;
            BudejieApplication.a.a(RequstMethod.GET, j.a(this.n + ""), new j(this.r), this.u);
        }
    }
}
