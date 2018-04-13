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
import com.budejie.www.bean.FansList;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.budejie.www.widget.XListView;
import com.budejie.www.widget.XListView.a;
import net.tsz.afinal.a.b;

public class FollowListActivity extends BaseTitleActivity implements a {
    private static String a = "FansListActivity";
    private XListView b;
    private a c;
    private RelativeLayout d;
    private ImageView e;
    private TextView i;
    private LinearLayout j;
    private ImageView k;
    private String l = "";
    private String m = "";
    private boolean n = true;
    private boolean o = true;
    private boolean p = false;
    private Activity q;
    private String r;
    private OnItemClickListener s = new OnItemClickListener(this) {
        final /* synthetic */ FollowListActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                Fans fans = (Fans) this.a.b.getItemAtPosition(i);
                Intent intent = new Intent(this.a.q, PersonalProfileActivity.class);
                intent.putExtra(PersonalProfileActivity.c, fans.getId());
                this.a.q.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private net.tsz.afinal.a.a t = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ FollowListActivity a;

        {
            this.a = r1;
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.f();
            this.a.p = false;
            this.a.b.b();
            this.a.b.c();
            Toast.makeText(this.a, str, 0).show();
        }

        public void onSuccess(Object obj) {
            this.a.f();
            this.a.b.setLastRefreshTime(System.currentTimeMillis());
            ai.d(this.a, ai.c);
            this.a.p = false;
            this.a.b.b();
            this.a.b.c();
            Log.e(FollowListActivity.a, "result&&" + obj.toString());
            if (obj == null) {
                Toast.makeText(this.a, "数据为空", 0).show();
                return;
            }
            FansList e = z.e(obj.toString());
            if (e != null) {
                String code = e.getCode();
                if (code == null) {
                    return;
                }
                if ("0".equals(code.trim())) {
                    this.a.m = e.getFollow_id();
                    this.a.n = e.getHas_data();
                    if (!this.a.p) {
                        if (this.a.o) {
                            this.a.o = false;
                            this.a.c.a();
                        }
                        this.a.c.a(e.getData());
                        if (this.a.c.isEmpty()) {
                            this.a.b.setVisibility(8);
                            if (TextUtils.isEmpty(this.a.r) || !this.a.r.equals(this.a.l)) {
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
                        if (!this.a.n) {
                            this.a.b.setPullLoadEnable(false);
                            this.a.b.a("没有更多的数据。", null);
                        }
                    } else {
                        return;
                    }
                }
                Toast.makeText(this.a, e.getMsg(), 0).show();
            }
            super.onSuccess(obj);
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e(R.layout.fans_list_layout);
        this.q = this;
        Intent intent = getIntent();
        if (intent != null) {
            this.l = intent.getStringExtra(HistoryOpenHelper.COLUMN_UID);
        }
        this.r = ai.b(getApplicationContext());
        g();
        h();
        c((int) R.string.suggested_follows);
        b(false);
        f(0);
        i();
        e();
        if (this.r.equals(this.l)) {
            a("我的关注");
        } else {
            a("关注");
        }
    }

    public void onRightClick(View view) {
        super.onRightClick(view);
        startActivity(new Intent(this, SuggestedFollowsActivity.class));
    }

    private void g() {
        this.c = new a(this);
        this.b = (XListView) findViewById(R.id.listview);
        this.b.setXListViewListener(this);
        this.b.setPullLoadEnable(false);
        this.b.setAdapter(this.c);
        this.b.setLastRefreshTime(ai.c((Context) this, ai.c).longValue());
        this.b.setOnItemClickListener(this.s);
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
        this.n = true;
        this.m = "";
        this.o = true;
        i();
    }

    public void b() {
        this.o = false;
        i();
    }

    private synchronized void i() {
        if (an.a((Context) this)) {
            this.p = true;
            BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", j(), this.t);
        } else {
            Toast.makeText(this, getString(R.string.nonet), 0).show();
        }
    }

    private b j() {
        return new j().b(this.q, this.l, this.m);
    }

    protected void onResume() {
        super.onResume();
    }
}
