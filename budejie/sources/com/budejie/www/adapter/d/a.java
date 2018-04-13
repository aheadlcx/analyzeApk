package com.budejie.www.adapter.d;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.f;

public class a extends com.budejie.www.adapter.a {
    protected final ListItemObject a;
    protected final Activity b;
    protected final LayoutInflater c;
    protected final int d;
    f e;
    private a f;
    private int g;
    private float h = ((1.25f * ((float) this.g)) * 0.4875f);

    public interface a {
    }

    public /* synthetic */ Object d() {
        return a();
    }

    public a(Activity activity, a aVar, ListItemObject listItemObject, int i) {
        this.a = listItemObject;
        this.b = activity;
        this.f = aVar;
        this.c = LayoutInflater.from(activity);
        this.d = i;
        this.g = activity.getWindowManager().getDefaultDisplay().getWidth();
        this.e = new f(activity);
    }

    public ListItemObject a() {
        return this.a;
    }

    public void a(b bVar) {
        if (this.a != null && !TextUtils.isEmpty(this.a.getContent())) {
            ((com.budejie.www.adapter.f.b) bVar).a.setText(this.a.getContent());
        }
    }

    public View b() {
        com.budejie.www.adapter.f.b bVar = new com.budejie.www.adapter.f.b();
        View inflate = this.c.inflate(R.layout.personal_profile_info, null);
        bVar.a = (TextView) inflate.findViewById(R.id.UserCreditInfoTextView);
        ((AsyncImageView) inflate.findViewById(R.id.aiv_pp_bg)).setLayoutParams(new LayoutParams(-1, (int) this.h));
        inflate.setVisibility(4);
        inflate.setTag(bVar);
        return inflate;
    }

    public int c() {
        return RowType.PINNERHEAD_ROW.ordinal();
    }
}
