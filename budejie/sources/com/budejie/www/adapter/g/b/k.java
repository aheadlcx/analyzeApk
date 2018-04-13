package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.video.f.b;
import com.budejie.www.activity.video.k.a;
import com.budejie.www.activity.view.MediaPlayView;
import com.budejie.www.activity.view.MediaPlayView$a;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.g.c;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.budejie.www.util.ac;
import com.budejie.www.util.an;
import com.sprite.ads.DataSourceType;
import com.sprite.ads.nati.NativeAdRef;
import com.umeng.analytics.MobclickAgent;
import java.util.HashMap;
import java.util.Map;

public class k extends j implements b, MediaPlayView$a {
    private TextView h;
    private TextView i;
    private RelativeLayout j;
    private ImageView k;
    private MediaPlayView l;
    private int m;
    private View n;
    private a o;
    private int p;

    public k(Context context, com.budejie.www.adapter.g.b<ListItemObject> bVar) {
        super(context, bVar);
        this.m = ((ListItemObject) bVar.b).isIs_ad() ? 2 : 0;
    }

    public View a(ViewGroup viewGroup) {
        View a = super.a(viewGroup);
        this.h = (TextView) a.findViewById(R.id.itemPlayCount);
        this.i = (TextView) a.findViewById(R.id.playTimeLength);
        this.l = (MediaPlayView) a.findViewById(R.id.mMPview);
        this.j = (RelativeLayout) a.findViewById(R.id.video_container_layout);
        this.k = (ImageView) a.findViewById(R.id.video_play_btn);
        this.n = a.findViewById(R.id.ad_gdt_layout);
        return a;
    }

    protected int e() {
        return R.layout.post_image_voice_video;
    }

    public void c() {
        this.k.setVisibility(8);
        super.c();
        if ("31".equals(((ListItemObject) this.c).getType())) {
            this.j.setVisibility(8);
            this.l.setVisibility(0);
            this.h.setVisibility(0);
            this.i.setVisibility(0);
            h();
        } else if ("41".equals(((ListItemObject) this.c).getType())) {
            this.j.setVisibility(0);
            this.l.setVisibility(8);
            this.h.setVisibility(0);
            this.i.setVisibility(0);
            i();
        } else {
            this.j.setVisibility(8);
            this.l.setVisibility(8);
            this.h.setVisibility(8);
            this.i.setVisibility(8);
        }
        NativeAdRef adItem = ((ListItemObject) this.c).getAdItem();
        if (adItem != null) {
            if (DataSourceType.SDK_GDT == adItem.getDataSourceType()) {
                this.n.setVisibility(0);
            } else {
                this.n.setVisibility(8);
            }
        }
    }

    private void h() {
        this.l.setOnMediaPlayerStateListener(new c(this.l));
        this.l.setPlayPath(((ListItemObject) this.c).getVoiceUri());
        this.l.setServerTime(Integer.parseInt(((ListItemObject) this.c).getVoicetime()) * 1000);
        this.l.setDataId(((ListItemObject) this.c).getWid());
        Object playcount = ((ListItemObject) this.c).getPlaycount();
        if (TextUtils.isEmpty(playcount)) {
            this.p = 0;
        } else {
            this.p = Integer.parseInt(playcount);
        }
        this.l.setPlayingListener(this);
        ac a = ac.a(this.a);
        Object m = a.m();
        if (a.c() || a.a()) {
            if (TextUtils.isEmpty(m) || !m.equals(((ListItemObject) this.c).getVoiceUri())) {
                this.l.e();
            } else {
                this.l.a();
            }
        } else if (TextUtils.isEmpty(m) || !m.equals(((ListItemObject) this.c).getVoiceUri()) || a.b()) {
            this.l.e();
        } else {
            this.l.b();
        }
        this.i.setVisibility(0);
        this.i.setText(ac.a((long) (Integer.parseInt(((ListItemObject) this.c).getVoicetime()) * 1000)));
        if (this.p != 0) {
            this.h.setText(this.p + "次播放");
        } else {
            this.h.setText("");
        }
    }

    private void i() {
        int parseInt;
        this.k.setOnClickListener(this);
        this.k.setTag(R.id.video_play_btn_uri, ((ListItemObject) this.c).getVideouri());
        com.budejie.www.adapter.b.a.a(this.j, ((ListItemObject) this.c).getWidth(), ((ListItemObject) this.c).getHeight());
        a d = com.budejie.www.activity.video.k.a(this.a).d();
        if (com.budejie.www.activity.video.k.a(this.a).c && d != null && d.c == this.j && !d.b.equals(((ListItemObject) this.c).getVideouri())) {
            com.budejie.www.activity.video.k.a(this.a).h();
        }
        com.budejie.www.activity.video.k a = com.budejie.www.activity.video.k.a(this.a);
        a.getClass();
        this.o = new a(a, this.d, this.j);
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
        super.f();
        if ("41".equals(((ListItemObject) this.c).getType())) {
            this.k.setVisibility(0);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.video_play_btn:
                g();
                return;
            default:
                super.onClick(view);
                return;
        }
    }

    public void b_() {
        if (this.b.f.a != null) {
            this.b.f.a.performClick();
        }
    }

    protected void g() {
        try {
            if ("41".equals(((ListItemObject) this.c).getType())) {
                Map hashMap = new HashMap();
                hashMap.put("label", ((ListItemObject) this.c).getPlateNames());
                hashMap.put("type", an.g(((ListItemObject) this.c).getType()));
                an.a(this.a, hashMap, "E01_A01");
                an.E(this.a);
                if (((ListItemObject) this.c).getImgUrl().endsWith(".gif")) {
                    this.e.setVisibility(8);
                }
                if (this.m == 2) {
                    com.budejie.www.activity.video.k.a(this.a).a((ListItemObject) this.c, this.o, this.m);
                    return;
                } else {
                    this.b.c.e(this.k, (ListItemObject) this.c);
                    return;
                }
            }
            super.g();
        } catch (Exception e) {
            MobclickAgent.onEvent(this.a, "cacheException", "PostImageVoiceVideoView clickImage:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public void a() {
        this.i.setVisibility(8);
        this.p++;
        this.h.setText(this.p + "次播放");
        ((ListItemObject) this.c).setPlaycount(String.valueOf(this.p));
        ((BudejieApplication) this.a.getApplicationContext()).a((ListItemObject) this.c);
        i.a(this.a.getString(R.string.track_event_play_voice), j.a((ListItemObject) this.c), j.b(this.a, (ListItemObject) this.c));
    }

    public void b() {
        this.i.setVisibility(0);
        ((BudejieApplication) this.a.getApplicationContext()).a(Status.end);
    }
}
