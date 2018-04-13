package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.FansListAdapter;
import cn.v6.sixrooms.bean.FansBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.engine.FansListEngine;
import cn.v6.sixrooms.ui.phone.BaseFragmentActivity;
import java.util.ArrayList;
import java.util.List;

public class FansPage extends RelativeLayout implements OnClickListener {
    private final int A = 24;
    private final int B = 25;
    private int C = 23;
    private ImageView D;
    private final String E = "2";
    private BaseFragmentActivity a;
    private WrapRoomInfo b;
    private ListView c;
    private LinearLayout d;
    private LinearLayout e;
    private LinearLayout f;
    private LinearLayout g;
    private LinearLayout h;
    private ImageView i;
    private ImageView j;
    private ImageView k;
    private ImageView l;
    public RelativeLayout ll_fans_page;
    private ImageView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private FansListEngine t;
    private List<FansBean> u;
    private FansListAdapter v;
    private String w = String.valueOf(System.currentTimeMillis());
    private final int x = 21;
    private final int y = 22;
    private final int z = 23;

    public FansPage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FansPage(Context context, WrapRoomInfo wrapRoomInfo) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.phone_room_fans_page, this, true);
        this.a = (BaseFragmentActivity) context;
        this.b = wrapRoomInfo;
        this.ll_fans_page = (RelativeLayout) findViewById(R.id.ll_fans_page);
        this.c = (ListView) findViewById(R.id.lv_follower);
        this.d = (LinearLayout) findViewById(R.id.ll_now_fans);
        this.h = (LinearLayout) findViewById(R.id.ll_red_now_fans);
        this.e = (LinearLayout) findViewById(R.id.ll_month_fans);
        this.f = (LinearLayout) findViewById(R.id.ll_super_fans);
        this.g = (LinearLayout) findViewById(R.id.ll_senven_day_fans);
        this.i = (ImageView) findViewById(R.id.iv_now_fans);
        this.j = (ImageView) findViewById(R.id.iv_red_now_fans);
        this.k = (ImageView) findViewById(R.id.iv_month_fans);
        this.l = (ImageView) findViewById(R.id.iv_super_fans);
        this.m = (ImageView) findViewById(R.id.iv_senven_day_fans);
        this.p = (TextView) findViewById(R.id.tv_now_fans);
        this.r = (TextView) findViewById(R.id.tv_red_now_fans);
        this.o = (TextView) findViewById(R.id.tv_month_fans);
        this.n = (TextView) findViewById(R.id.tv_super_fans);
        this.q = (TextView) findViewById(R.id.tv_senven_day_fans);
        this.D = (ImageView) findViewById(R.id.iv_title_close);
        this.s = (TextView) findViewById(R.id.fanspage_nodata);
        if ("2".equals(this.b.getTplType())) {
            this.p.setText(getResources().getString(R.string.str_contribute_fans_live));
            setRankingShow(-1);
        } else {
            setRankingShow(this.b.getRoomParamInfoBean().getSetranking());
        }
        if (this.b != null) {
            this.t = new FansListEngine(new i(this));
            this.u = new ArrayList();
            this.v = new FansListAdapter(this.a, this.u);
            this.c.setAdapter(this.v);
            if ("2".equals(this.b.getTplType())) {
                this.C = 21;
                a();
                this.t.getNowFansList(this.b.getRoominfoBean().getId(), getTm());
            } else {
                String id = this.b.getRoominfoBean().getId();
                this.C = 21;
                a();
                this.t.getNowFansList(id, getTm());
            }
        }
        this.d.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.D.setOnClickListener(this);
    }

    private void setRankingShow(int i) {
        switch (i) {
            case -1:
                this.C = 21;
                this.f.setVisibility(8);
                this.e.setVisibility(8);
                this.g.setVisibility(8);
                this.h.setVisibility(0);
                break;
            case 0:
                this.C = 21;
                this.e.setVisibility(8);
                this.f.setVisibility(8);
                this.h.setVisibility(8);
                this.g.setVisibility(0);
                break;
            case 1:
                this.C = 21;
                this.f.setVisibility(8);
                this.h.setVisibility(8);
                this.e.setVisibility(0);
                this.g.setVisibility(0);
                break;
            default:
                this.C = 21;
                this.h.setVisibility(8);
                this.f.setVisibility(0);
                this.e.setVisibility(0);
                this.g.setVisibility(0);
                break;
        }
        a();
    }

    private void a() {
        this.i.setBackgroundResource(R.drawable.rooms_third_room_fans_this_normal);
        this.j.setBackgroundResource(R.drawable.rooms_third_room_fans_this_normal);
        this.k.setBackgroundResource(R.drawable.rooms_third_room_fans_ago_normal);
        this.l.setBackgroundResource(R.drawable.rooms_third_room_fans_super_normal);
        this.m.setBackgroundResource(R.drawable.rooms_fourth_room_fans_seven_normal);
        this.p.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_unchecked));
        this.r.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_unchecked));
        this.o.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_unchecked));
        this.n.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_unchecked));
        this.q.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_unchecked));
        switch (this.C) {
            case 21:
                this.i.setBackgroundResource(R.drawable.rooms_third_room_fans_this_down);
                this.p.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_checked));
                return;
            case 22:
                this.k.setBackgroundResource(R.drawable.rooms_third_room_fans_ago_down);
                this.o.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_checked));
                return;
            case 23:
                this.l.setBackgroundResource(R.drawable.rooms_third_room_fans_super_down);
                this.n.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_checked));
                return;
            case 24:
                this.m.setBackgroundResource(R.drawable.rooms_fourth_room_fans_seven_checked);
                this.q.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_checked));
                return;
            case 25:
                this.j.setBackgroundResource(R.drawable.rooms_third_room_fans_this_down);
                this.r.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_checked));
                return;
            default:
                return;
        }
    }

    private String getTm() {
        return this.w;
    }

    public void updateFansTime(String str) {
        this.w = str;
    }

    public void updateNowFans() {
        if (this.b != null && this.C == 21) {
            this.t.getNowFansList(this.b.getRoominfoBean().getId(), getTm());
        }
    }

    public void setFansPageVisible(int i) {
        this.ll_fans_page.setVisibility(i);
    }

    public void setFansPageVisible() {
        this.ll_fans_page.setVisibility(0);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ll_now_fans) {
            if (this.C != 21) {
                this.C = 21;
                a();
                if (this.b != null) {
                    this.t.getNowFansList(this.b.getRoominfoBean().getId(), getTm());
                }
            }
        } else if (id == R.id.ll_red_now_fans) {
            if (this.C != 25) {
                this.C = 25;
                a();
                if (this.b != null) {
                    this.t.getNowFansList(this.b.getRoominfoBean().getId(), getTm());
                }
            }
        } else if (id == R.id.ll_senven_day_fans) {
            if (this.C != 24) {
                this.C = 24;
                a();
                if (this.b != null) {
                    this.t.getSupperSortFansList(this.b.getRoominfoBean().getId());
                }
            }
        } else if (id == R.id.ll_month_fans) {
            if (this.C != 22) {
                this.C = 22;
                a();
                if (this.b != null) {
                    this.t.getSupperSortFansList(this.b.getRoominfoBean().getId());
                }
            }
        } else if (id == R.id.ll_super_fans) {
            if (this.C != 23) {
                this.C = 23;
                a();
                if (this.b != null) {
                    this.t.getSupperSortFansList(this.b.getRoominfoBean().getId());
                }
            }
        } else if (id == R.id.iv_title_close) {
            this.ll_fans_page.setVisibility(8);
        }
    }

    static /* synthetic */ void e(FansPage fansPage) {
        fansPage.u.clear();
        fansPage.v.notifyDataSetChanged();
    }
}
