package com.budejie.www.adapter.d;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.phonenumber.PhoneNumBindingActivity;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.a;
import com.budejie.www.adapter.b;
import com.budejie.www.adapter.f.c;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.UserItem;
import com.budejie.www.c.m;
import com.umeng.analytics.MobclickAgent;

public class e extends a {
    protected final ListItemObject a;
    protected final Activity b;
    protected final LayoutInflater c;
    protected final com.budejie.www.adapter.e.a d;
    protected final int e;
    private SharedPreferences f;
    private String g = this.f.getString("id", "");
    private m h;

    public /* synthetic */ Object d() {
        return a();
    }

    public e(Activity activity, com.budejie.www.adapter.e.a aVar, ListItemObject listItemObject, int i) {
        this.a = listItemObject;
        this.b = activity;
        this.d = aVar;
        this.c = LayoutInflater.from(activity);
        this.e = i;
        this.f = activity.getSharedPreferences("weiboprefer", 0);
        this.h = new m(activity);
    }

    public View b() {
        c cVar = new c();
        ViewGroup viewGroup = (ViewGroup) this.c.inflate(R.layout.new_new_list_item_invite, null);
        cVar.a = (TextView) viewGroup.findViewById(R.id.invite);
        viewGroup.setTag(cVar);
        return viewGroup;
    }

    public int c() {
        return RowType.INVITE_ROW.ordinal();
    }

    public void a(b bVar) {
        ((c) bVar).a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                MobclickAgent.onEvent(this.a.b, "E01-A04", "绑定通讯录点击次数");
                Intent intent = new Intent(this.a.b, PhoneNumBindingActivity.class);
                UserItem e = this.a.h.e(this.a.g);
                intent.putExtra("source", "PostInviteRow");
                intent.putExtra("nike_name", e.getName());
                this.a.b.startActivity(intent);
            }
        });
    }

    public ListItemObject a() {
        return this.a;
    }
}
