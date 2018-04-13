package com.budejie.www.adapter.d;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.budejie.www.R;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.a;
import com.budejie.www.adapter.b;
import com.budejie.www.bean.ListItemObject;
import com.umeng.analytics.MobclickAgent;

public class k extends a {
    protected final ListItemObject a;
    protected final Activity b;
    protected final LayoutInflater c;
    protected final com.budejie.www.adapter.e.a d;
    protected final int e;

    public /* synthetic */ Object d() {
        return a();
    }

    public k(Activity activity, com.budejie.www.adapter.e.a aVar, ListItemObject listItemObject, int i) {
        this.a = listItemObject;
        this.b = activity;
        this.d = aVar;
        this.c = LayoutInflater.from(activity);
        this.e = i;
    }

    public View b() {
        com.budejie.www.adapter.f.a aVar = new com.budejie.www.adapter.f.a();
        ViewGroup viewGroup = (ViewGroup) this.c.inflate(R.layout.new_new_list_item_last_refresh, null);
        aVar.a = (RelativeLayout) viewGroup.findViewById(R.id.last_refresh_layout);
        viewGroup.setTag(aVar);
        return viewGroup;
    }

    public int c() {
        return RowType.SHENHE_OR_SUIJI_ROW.ordinal();
    }

    public void a(b bVar) {
        ((com.budejie.www.adapter.f.a) bVar).a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    this.a.d.a();
                } catch (Exception e) {
                    MobclickAgent.onEvent(this.a.b, "cacheException", "PostShenheOrSuijiRow mind_into_shenhe onClick:" + e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    public ListItemObject a() {
        return this.a;
    }
}
