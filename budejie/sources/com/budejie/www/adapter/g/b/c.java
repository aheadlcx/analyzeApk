package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.budejie.www.R;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.i;
import java.util.HashMap;
import java.util.Map;

public class c extends a<ListItemObject> {
    private static final Map<Integer, Long> e = new HashMap();
    private View f;

    public c(Context context, b<ListItemObject> bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        this.f = View.inflate(this.a, R.layout.post_ad_download_bar, viewGroup);
        return this.f;
    }

    public void c() {
        this.f.setOnClickListener(this);
        int ad_id = ((ListItemObject) this.c).getAd_id();
        if (!e.containsKey(Integer.valueOf(ad_id)) || System.currentTimeMillis() - ((Long) e.get(Integer.valueOf(ad_id))).longValue() > 5000) {
            e.put(Integer.valueOf(ad_id), Long.valueOf(System.currentTimeMillis()));
            i.a(this.a, ad_id);
        }
    }

    public void onClick(View view) {
        this.b.c.e(view, (ListItemObject) this.c);
    }
}
