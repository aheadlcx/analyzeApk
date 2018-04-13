package com.budejie.www.recommendvideo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.video.f$e;
import com.budejie.www.activity.video.k;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.b;
import com.budejie.www.bean.ListItemObject;

public class c extends com.budejie.www.adapter.a {
    protected final ListItemObject a;
    protected final Activity b;
    protected final LayoutInflater c;
    protected final com.budejie.www.adapter.e.a d;
    protected final int e;
    protected final int f;
    private com.budejie.www.activity.video.k.a g;
    private a h;
    private f$e i = new c$1(this);
    private OnClickListener j = new c$2(this);
    private OnClickListener k = new c$3(this);

    public interface a {
        void a(int i);
    }

    public /* synthetic */ Object d() {
        return a();
    }

    public c(Activity activity, com.budejie.www.adapter.e.a aVar, a aVar2, ListItemObject listItemObject, int i, int i2) {
        this.a = listItemObject;
        this.b = activity;
        this.d = aVar;
        this.h = aVar2;
        this.c = LayoutInflater.from(activity);
        this.e = i;
        this.f = i2;
    }

    public void a(b bVar) {
        b bVar2 = (b) bVar;
        int width = this.a.getWidth();
        int height = this.a.getHeight();
        int e = com.budejie.www.adapter.b.a.e(bVar2.b, width, height);
        com.budejie.www.adapter.b.a.c(bVar2.a, width, height);
        width = (com.budejie.www.activity.video.a.b(this.b) - e) / 2;
        if (this.e == 0) {
            bVar2.d.setLayoutParams(new LayoutParams(-1, width));
            bVar2.d.setVisibility(0);
        } else if (this.e == this.f - 1) {
            bVar2.e.setLayoutParams(new LayoutParams(-1, width));
            bVar2.e.setVisibility(0);
        } else {
            bVar2.d.setVisibility(8);
            bVar2.e.setVisibility(8);
        }
        com.budejie.www.activity.video.k.a d = k.a(this.b).d();
        if (k.a(this.b).c && d != null && d.c == bVar2.a && !d.b.equals(this.a.getVideouri())) {
            k.a(this.b).h();
        }
        k a = k.a(this.b);
        a.getClass();
        this.g = new com.budejie.www.activity.video.k.a(a, this.e, bVar2.a);
        bVar2.b.setRecommendVideoImage(this.a.getImgUrl());
        bVar2.f.setOnClickListener(this.j);
        bVar2.g.setOnClickListener(this.k);
        bVar2.f.setTag(R.id.video_play_btn_uri, this.a.getVideouri());
    }

    public View b() {
        b bVar = new b();
        ViewGroup viewGroup = (ViewGroup) this.c.inflate(R.layout.recommend_post_item, null);
        bVar.a = (RelativeLayout) viewGroup.findViewById(R.id.video_container_layout);
        bVar.b = (AsyncImageView) viewGroup.findViewById(R.id.recommend_post_item_image);
        bVar.c = (TextView) viewGroup.findViewById(R.id.recommend_post_item_mask);
        bVar.d = viewGroup.findViewById(R.id.video_container_top);
        bVar.e = viewGroup.findViewById(R.id.video_container_bottom);
        bVar.f = viewGroup.findViewById(R.id.video_play_btn);
        bVar.g = viewGroup.findViewById(R.id.video_stop_btn);
        viewGroup.setTag(bVar);
        return viewGroup;
    }

    public int c() {
        return RowType.RECOMMEND_VIDEO_ROW.ordinal();
    }

    public ListItemObject a() {
        return this.a;
    }
}
