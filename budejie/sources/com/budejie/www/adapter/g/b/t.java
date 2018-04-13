package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.video.f.b;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.video.k.a;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.ac;
import com.budejie.www.util.an;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.umeng.analytics.MobclickAgent;
import java.util.HashMap;
import java.util.Map;

public class t extends j implements b {
    private TextView h;
    private TextView i;
    private RelativeLayout j;
    private ImageView k;
    private a l;
    private int m;

    public t(Context context, com.budejie.www.adapter.g.b<ListItemObject> bVar) {
        super(context, bVar);
        this.m = ((ListItemObject) bVar.b).isIs_ad() ? 2 : 0;
    }

    public t(Context context, com.budejie.www.adapter.g.b<ListItemObject> bVar, boolean z) {
        super(context, bVar);
        this.m = z ? 1 : 0;
    }

    public View a(ViewGroup viewGroup) {
        View a = super.a(viewGroup);
        this.h = (TextView) a.findViewById(R.id.itemPlayCount);
        this.i = (TextView) a.findViewById(R.id.playTimeLength);
        this.j = (RelativeLayout) a.findViewById(R.id.video_container_layout);
        this.k = (ImageView) a.findViewById(R.id.video_play_btn);
        return a;
    }

    protected int e() {
        return R.layout.post_video;
    }

    public void c() {
        int parseInt;
        this.k.setVisibility(8);
        super.c();
        this.k.setOnClickListener(this);
        this.k.setTag(R.id.video_play_btn_uri, ((ListItemObject) this.c).getVideouri());
        com.budejie.www.adapter.b.a.a(this.j, ((ListItemObject) this.c).getWidth(), ((ListItemObject) this.c).getHeight());
        a d = k.a(this.a).d();
        if (k.a(this.a).c && d != null && d.c == this.j && !d.b.equals(((ListItemObject) this.c).getVideouri())) {
            k.a(this.a).h();
        }
        k a = k.a(this.a);
        a.getClass();
        this.l = new a(a, this.d, this.j);
        try {
            parseInt = Integer.parseInt(((ListItemObject) this.c).getVideotime());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            parseInt = 0;
        }
        this.i.setText(ac.a((long) (parseInt * 1000)));
        if (TextUtils.isEmpty(((ListItemObject) this.c).getPlaycount())) {
            this.h.setText("");
        } else {
            this.h.setText(((ListItemObject) this.c).getPlaycount() + "次播放");
        }
    }

    protected void f() {
        this.k.setVisibility(0);
    }

    public void onLoadingFailed(String str, View view, FailReason failReason) {
        super.onLoadingFailed(str, view, failReason);
        this.f.setVisibility(8);
        this.k.setVisibility(0);
    }

    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.video_play_btn:
                    g();
                    return;
                default:
                    super.onClick(view);
                    return;
            }
        } catch (Exception e) {
            MobclickAgent.onEvent(this.a, "cacheException", "PostVideoView onClick:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public void b_() {
        if (this.b.f.a != null) {
            this.b.f.a.performClick();
            MobclickAgent.onEvent(this.a, this.a.getString(R.string.double_click_praise), this.a.getString(R.string.double_click_praise_describe));
        }
    }

    public void a() {
        g();
    }

    protected void g() {
        try {
            if (this.m == 1) {
                Intent intent = new Intent();
                intent.setAction("play.video.action");
                intent.putExtra("posts_id", ((ListItemObject) this.c).getWid());
                this.a.sendBroadcast(intent);
            }
            Map hashMap = new HashMap();
            hashMap.put("label", ((ListItemObject) this.c).getPlateNames());
            hashMap.put("type", an.g(((ListItemObject) this.c).getType()));
            an.a(this.a, hashMap, "E01_A01");
            an.E(this.a);
            if (((ListItemObject) this.c).getImgUrl().endsWith(".gif")) {
                this.e.setVisibility(8);
            }
            if (this.m == 1) {
                k.a(this.a).a((ListItemObject) this.c, this.l, this, this.m);
            } else {
                this.b.c.e(this.k, (ListItemObject) this.c);
            }
        } catch (Exception e) {
            MobclickAgent.onEvent(this.a, "cacheException", "PostVideoView clickImage:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
