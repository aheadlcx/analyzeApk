package com.budejie.www.activity.detail;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.budejie.www.R;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.activity.TipPopUp;
import com.budejie.www.activity.TipPopUp.TypeControl;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.activity.video.k;
import com.budejie.www.adapter.e.a;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.c.m;
import com.budejie.www.http.f;
import com.budejie.www.http.n;
import com.budejie.www.util.ai;
import com.budejie.www.util.q;
import com.budejie.www.widget.NewTitleView;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import java.util.HashMap;

public class b implements a {
    private Activity a;
    private NewTitleView b;
    private com.budejie.www.g.b c;
    private f d;
    private n e;
    private m f;
    private HashMap<String, String> g = this.e.a(this.h.getString("id", ""));
    private SharedPreferences h;
    private com.elves.update.a i;
    private IWXAPI j;
    private Handler k;
    private Handler l;

    public b(Activity activity, com.budejie.www.g.b bVar, Handler handler, Handler handler2) {
        this.a = activity;
        this.c = bVar;
        this.k = handler;
        this.l = handler2;
        this.e = new n(activity);
        this.f = new m(activity);
        this.h = activity.getSharedPreferences("weiboprefer", 0);
        this.i = new com.elves.update.a(activity);
        c();
        this.d = new f(activity);
    }

    private void c() {
        this.j = WXAPIFactory.createWXAPI(this.a, "wx592fdc48acfbe290", true);
        this.j.registerApp("wx592fdc48acfbe290");
    }

    public void a(NewTitleView newTitleView) {
        this.b = newTitleView;
    }

    public void e(View view, ListItemObject listItemObject) {
    }

    public void a(View view, ListItemObject listItemObject, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(PersonalProfileActivity.c, listItemObject.getUid());
        bundle.putString(PersonalProfileActivity.d, str);
        this.c.a(7, bundle).onClick(view);
    }

    public void c(View view, ListItemObject listItemObject) {
    }

    public void a(View view, ListItemObject listItemObject) {
        if (this.b != null && this.b.a()) {
            TipPopUp.a(this.a, TypeControl.dingtie);
            this.d.a("ding", this.k, listItemObject);
            this.d.a(listItemObject, this.k, "ding");
            Object tag = view.getTag(R.id.DOUBLE_CLICK_KEY);
            if (view != null && tag != null && ((Boolean) tag).booleanValue()) {
                try {
                    q.a(k.a(this.a).b.b);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void b(View view, ListItemObject listItemObject) {
        this.d.a("cai", this.k, listItemObject);
        this.d.a(listItemObject, this.k, "cai");
    }

    public void a(View view, ListItemObject listItemObject, int i) {
        listItemObject.setForwardNoCollect(false);
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this.a));
        bundle.putSerializable("weiboMap", this.g);
        bundle.putSerializable("data", listItemObject);
        view.setTag(listItemObject);
        this.c.a(5, bundle, this.k, this.j, this.f, this.e, this.i, this.h, this.l).onClick(view);
    }

    public void d(View view, ListItemObject listItemObject) {
    }

    public void a() {
    }

    public void a_(String str) {
    }

    public OnClickListener b() {
        return null;
    }

    public void a(SuggestedFollowsListItem suggestedFollowsListItem) {
    }

    public void a(ListItemObject listItemObject, int i) {
        Intent intent = new Intent(this.a, CommonLabelActivity.class);
        PlateBean plateBean = listItemObject.getPlateBean(i);
        if (plateBean != null) {
            intent.putExtra("theme_name", plateBean.theme_name);
            intent.putExtra("theme_id", plateBean.theme_id);
            this.a.startActivity(intent);
        }
    }
}
