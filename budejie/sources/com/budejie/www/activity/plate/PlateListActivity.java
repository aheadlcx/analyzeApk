package com.budejie.www.activity.plate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.activity.plate.bean.PlateResponseData;
import com.budejie.www.activity.search.SearchMainActivity;
import com.budejie.www.activity.view.CircleLoadLayout;
import com.budejie.www.c.j;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.util.ah;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.budejie.www.widget.XListView;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class PlateListActivity extends BaseActvityWithLoadDailog implements OnClickListener {
    private PlateListActivity a;
    private j b;
    private LinearLayout c;
    private XListView d;
    private a e;
    private ArrayList<PlateBean> f;
    private CircleLoadLayout h;
    private OnItemClickListener i = new OnItemClickListener(this) {
        final /* synthetic */ PlateListActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                PlateBean plateBean = (PlateBean) this.a.f.get((int) j);
                if (plateBean != null) {
                    MobclickAgent.onEvent(this.a.a, "E01_A11", plateBean.theme_name);
                    plateBean.read_time = (int) (System.currentTimeMillis() / 1000);
                    plateBean.read_count++;
                    this.a.b.a(plateBean.theme_id, plateBean.read_time, plateBean.read_count);
                    Intent intent = new Intent(this.a.a, CommonLabelActivity.class);
                    intent.putExtra("theme_id", plateBean.theme_id);
                    intent.putExtra("theme_name", plateBean.theme_name);
                    intent.putExtra("colum_set", plateBean.colum_set);
                    this.a.a.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private net.tsz.afinal.a.a j = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ PlateListActivity a;

        {
            this.a = r1;
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.h.b();
        }

        public void onSuccess(Object obj) {
            if (obj != null) {
                String obj2 = obj.toString();
                if (!TextUtils.isEmpty(obj2)) {
                    PlateResponseData plateResponseData = (PlateResponseData) z.a(obj2, PlateResponseData.class);
                    if (!(plateResponseData == null || plateResponseData.list == null || plateResponseData.list.isEmpty())) {
                        this.a.f = plateResponseData.list;
                        ah.a(this.a.getApplicationContext(), this.a.f);
                        ArrayList a = this.a.b.a();
                        if (!(a == null || a.isEmpty())) {
                            Iterator it = this.a.f.iterator();
                            while (it.hasNext()) {
                                PlateBean plateBean = (PlateBean) it.next();
                                Iterator it2 = a.iterator();
                                while (it2.hasNext()) {
                                    PlateBean plateBean2 = (PlateBean) it2.next();
                                    if (plateBean.theme_id.equals(plateBean2.theme_id)) {
                                        plateBean.read_time = plateBean2.read_time;
                                        plateBean.read_count = plateBean2.read_count;
                                    }
                                }
                            }
                        }
                        Collections.sort(this.a.f, new a(this.a));
                        this.a.e.a(this.a.f);
                    }
                    this.a.h.b();
                }
            }
        }
    };

    class a implements Comparator {
        final /* synthetic */ PlateListActivity a;

        a(PlateListActivity plateListActivity) {
            this.a = plateListActivity;
        }

        public int compare(Object obj, Object obj2) {
            PlateBean plateBean = (PlateBean) obj;
            PlateBean plateBean2 = (PlateBean) obj2;
            if (plateBean.read_count == plateBean2.read_count) {
                if (plateBean.read_time >= plateBean2.read_time) {
                    return -1;
                }
                return 1;
            } else if (plateBean.read_count <= plateBean2.read_count) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_plate_list);
        this.a = this;
        this.b = new j(this.a);
        a();
    }

    protected void onResume() {
        super.onResume();
        b();
    }

    public void a() {
        this.c = (LinearLayout) findViewById(R.id.plate_search_layout);
        this.c.setOnClickListener(this);
        this.d = (XListView) findViewById(R.id.plate_list_view);
        this.d.setPullRefreshEnable(false);
        this.d.setPullLoadEnable(false);
        this.d.setOnItemClickListener(this.i);
        this.e = new a(this.a);
        this.d.setAdapter(this.e);
        this.h = (CircleLoadLayout) findViewById(R.id.circle_load_layout);
    }

    public void b() {
        g();
        if (com.budejie.www.goddubbing.c.a.a(this.f)) {
            this.h.a();
        }
    }

    private void g() {
        if (an.a((Context) this)) {
            BudejieApplication.a.a(RequstMethod.GET, com.budejie.www.http.j.s(), new com.budejie.www.http.j(this.a), this.j);
        } else {
            Toast.makeText(this, getString(R.string.nonet), 0).show();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.plate_search_layout:
                startActivity(new Intent(this, SearchMainActivity.class));
                return;
            default:
                return;
        }
    }
}
