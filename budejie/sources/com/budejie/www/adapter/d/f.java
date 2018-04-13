package com.budejie.www.adapter.d;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.budejie.www.R;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.a;
import com.budejie.www.adapter.b;
import com.budejie.www.adapter.f.d;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.c;
import com.umeng.analytics.MobclickAgent;

public class f extends a {
    protected final ListItemObject a;
    protected final Activity b;
    protected final LayoutInflater c;
    protected final com.budejie.www.adapter.e.a d;
    protected final int e;

    public /* synthetic */ Object d() {
        return a();
    }

    public f(Activity activity, com.budejie.www.adapter.e.a aVar, ListItemObject listItemObject, int i) {
        this.a = listItemObject;
        this.b = activity;
        this.d = aVar;
        this.c = LayoutInflater.from(activity);
        this.e = i;
    }

    public View b() {
        d dVar = new d();
        ViewGroup viewGroup = (ViewGroup) this.c.inflate(R.layout.new_new_list_item_login, null);
        dVar.a = (LinearLayout) viewGroup.findViewById(R.id.item_layout);
        dVar.b = (ImageButton) viewGroup.findViewById(R.id.sina_login);
        c.a(dVar.b);
        dVar.c = (ImageButton) viewGroup.findViewById(R.id.qq_login);
        c.a(dVar.c);
        dVar.d = (ImageButton) viewGroup.findViewById(R.id.tencent_login);
        c.a(dVar.d);
        dVar.e = (ImageButton) viewGroup.findViewById(R.id.phone_login);
        c.a(dVar.e);
        viewGroup.setTag(dVar);
        return viewGroup;
    }

    public int c() {
        return RowType.LOGIN_ROW.ordinal();
    }

    public void a(b bVar) {
        d dVar = (d) bVar;
        dVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                MobclickAgent.onEvent(this.a.b, "E01-A04", "登录点击次数");
                this.a.d.a_("sina");
            }
        });
        dVar.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                MobclickAgent.onEvent(this.a.b, "E01-A04", "登录点击次数");
                this.a.d.a_("qq");
            }
        });
        dVar.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                MobclickAgent.onEvent(this.a.b, "E01-A04", "登录点击次数");
                this.a.d.a_("tencent");
            }
        });
        dVar.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                MobclickAgent.onEvent(this.a.b, "E01-A04", "登录点击次数");
                this.a.d.a_("phone");
            }
        });
        dVar.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                MobclickAgent.onEvent(this.a.b, "E01-A04", "登录点击次数");
                this.a.d.a_("login");
            }
        });
    }

    public ListItemObject a() {
        return this.a;
    }
}
