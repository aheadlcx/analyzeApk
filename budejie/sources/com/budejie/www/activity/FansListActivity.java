package com.budejie.www.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseTitleActivity;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.FansList;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.budejie.www.widget.XListView;
import com.budejie.www.widget.XListView.a;

public class FansListActivity extends BaseTitleActivity implements a {
    private static String a = "FansListActivity";
    private XListView b;
    private a c;
    private RelativeLayout d;
    private ImageView e;
    private TextView i;
    private String j = "";
    private String k = "";
    private boolean l = true;
    private boolean m = true;
    private boolean n = false;
    private Activity o;
    private OnItemClickListener p = new OnItemClickListener(this) {
        final /* synthetic */ FansListActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                Fans fans = (Fans) this.a.b.getItemAtPosition(i);
                Intent intent = new Intent(this.a.o, PersonalProfileActivity.class);
                intent.putExtra(PersonalProfileActivity.c, fans.getId());
                this.a.o.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private net.tsz.afinal.a.a q = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ FansListActivity a;

        {
            this.a = r1;
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.f();
            this.a.n = false;
            this.a.b.b();
            this.a.b.c();
            Toast.makeText(this.a, str, 0).show();
        }

        public void onSuccess(Object obj) {
            this.a.f();
            this.a.b.setLastRefreshTime(System.currentTimeMillis());
            ai.d(this.a, ai.b);
            this.a.n = false;
            this.a.b.b();
            this.a.b.c();
            Log.e(FansListActivity.a, "result&&" + obj.toString());
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
                if (code.trim().equals("0")) {
                    this.a.k = e.getFollow_id();
                    this.a.l = e.getHas_data();
                    if (!this.a.n) {
                        if (this.a.m) {
                            this.a.m = false;
                            this.a.c.a();
                        }
                        this.a.c.a(e.getData());
                        if (this.a.c.isEmpty()) {
                            this.a.b.setVisibility(8);
                            this.a.d.setVisibility(0);
                        } else {
                            this.a.b.setPullLoadEnable(true);
                        }
                        if (!this.a.l) {
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
        this.o = this;
        h();
        i();
        a("粉丝");
        g();
    }

    private void g() {
        Intent intent = getIntent();
        if (intent != null) {
            this.j = intent.getStringExtra(HistoryOpenHelper.COLUMN_UID);
            j();
            e();
        }
    }

    private void h() {
        this.c = new a(this);
        this.b = (XListView) findViewById(R.id.listview);
        this.b.setXListViewListener(this);
        this.b.setPullLoadEnable(false);
        this.b.setAdapter(this.c);
        this.b.setLastRefreshTime(ai.c((Context) this, ai.b).longValue());
        this.b.setOnItemClickListener(this.p);
    }

    private void i() {
        this.d = (RelativeLayout) findViewById(R.id.empty_layout);
        this.e = (ImageView) findViewById(R.id.empty_icon);
        this.i = (TextView) findViewById(R.id.empty_text);
        this.e.setImageResource(R.drawable.no_fans_icon);
        this.i.setText(getResources().getString(R.string.fans_none_data));
    }

    public void a() {
        this.l = true;
        this.k = "";
        this.m = true;
        j();
    }

    public void b() {
        this.m = false;
        j();
    }

    private synchronized void j() {
        if (an.a((Context) this)) {
            this.n = true;
            BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new j().a(this.o, this.j, this.k), this.q);
        } else {
            Toast.makeText(this, getString(R.string.nonet), 0).show();
        }
    }

    protected void onResume() {
        super.onResume();
    }
}
