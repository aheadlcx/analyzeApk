package com.budejie.www.activity.label;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.a;
import com.budejie.www.adapter.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.f;
import com.budejie.www.util.i;

public class j extends a {
    protected final ListItemObject a;
    protected final Activity b;
    protected final LayoutInflater c;
    protected final int d;
    f e;
    private com.budejie.www.adapter.e.a f;

    public /* synthetic */ Object d() {
        return a();
    }

    public j(Activity activity, com.budejie.www.adapter.e.a aVar, ListItemObject listItemObject, int i) {
        this.a = listItemObject;
        this.b = activity;
        this.f = aVar;
        this.c = LayoutInflater.from(activity);
        this.d = i;
        activity.getWindowManager().getDefaultDisplay();
        this.e = new f(activity);
    }

    public ListItemObject a() {
        return this.a;
    }

    public void a(b bVar) {
    }

    public View b() {
        View view = null;
        if (this.a.getPlaceholder() == 1) {
            View inflate = this.c.inflate(R.layout.label_details_head, null);
            ((AsyncImageView) inflate.findViewById(R.id.labelDetailsImg)).setLayoutParams(i.a().c(this.b));
            view = inflate;
        } else if (this.a.getPlaceholder() == 2) {
            view = this.c.inflate(R.layout.label_details_head_pinner, null);
        }
        view.setVisibility(8);
        return view;
    }

    public int c() {
        return RowType.PINNERHEAD_ROW.ordinal();
    }
}
