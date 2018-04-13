package com.budejie.www.widget;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.c.d;
import com.budejie.www.c.h;
import com.budejie.www.h.c;
import com.budejie.www.util.an;
import com.budejie.www.widget.curtain.BarrageStatusManager;
import com.budejie.www.widget.curtain.BarrageStatusManager.BarrageState;

public class NewTitleView extends RelativeLayout {
    OnClickListener a = new OnClickListener(this) {
        final /* synthetic */ NewTitleView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.detail_title_back:
                    ((Activity) this.a.c).finish();
                    return;
                case R.id.detail_title_like:
                    if (this.a.f != null && !this.a.i.isSelected() && !this.a.j.isSelected()) {
                        this.a.f.a(view, this.a.e);
                        return;
                    }
                    return;
                case R.id.detail_title_hate:
                    if (this.a.f != null && !this.a.i.isSelected() && !this.a.j.isSelected()) {
                        this.a.j.setSelected(true);
                        this.a.f.b(view, this.a.e);
                        return;
                    }
                    return;
                case R.id.detail_title_share:
                    if (this.a.f != null) {
                        this.a.f.a(view);
                        return;
                    }
                    return;
                case R.id.detail_title_barrage:
                    if (this.a.f != null) {
                        BarrageState a = BarrageStatusManager.a(this.a.d);
                        switch (a) {
                            case CLOSE:
                                a = BarrageState.MULTI;
                                break;
                            case SINGLE:
                                a = BarrageState.CLOSE;
                                break;
                            case MULTI:
                                a = BarrageState.SINGLE;
                                break;
                        }
                        this.a.a(a);
                        BarrageStatusManager.a(this.a.d, a);
                        this.a.f.a(a);
                        return;
                    }
                    return;
                case R.id.detail_title_header:
                    if (this.a.f != null) {
                        this.a.f.c(view, this.a.e);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private final String b = "NewTitleView";
    private Context c;
    private SharedPreferences d;
    private ListItemObject e;
    private a f;
    private RelativeLayout g;
    private ImageView h;
    private ImageView i;
    private ImageView j;
    private ImageView k;
    private ImageView l;
    private AsyncImageView m;
    private View n;
    private boolean o = false;
    private h p;
    private d q;

    public interface a {
        void a(View view);

        void a(View view, ListItemObject listItemObject);

        void a(BarrageState barrageState);

        void b(View view, ListItemObject listItemObject);

        void c(View view, ListItemObject listItemObject);
    }

    public NewTitleView(Context context) {
        super(context);
        a(context);
    }

    public NewTitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public NewTitleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.c = context;
        this.d = this.c.getSharedPreferences("weiboprefer", 0);
        this.p = new h(this.c);
        this.q = new d(this.c);
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.detail_title_layout, null);
        this.g = (RelativeLayout) inflate.findViewById(R.id.detail_title_layout);
        this.h = (ImageView) inflate.findViewById(R.id.detail_title_back);
        this.i = (ImageView) inflate.findViewById(R.id.detail_title_like);
        this.j = (ImageView) inflate.findViewById(R.id.detail_title_hate);
        this.k = (ImageView) inflate.findViewById(R.id.detail_title_share);
        this.l = (ImageView) inflate.findViewById(R.id.detail_title_barrage);
        this.m = (AsyncImageView) inflate.findViewById(R.id.detail_title_header);
        this.n = inflate.findViewById(R.id.detail_title_divider);
        this.h.setOnClickListener(this.a);
        addView(inflate, new LayoutParams(-1, -1));
    }

    public void setmListItemObject(ListItemObject listItemObject) {
        this.e = listItemObject;
        if (listItemObject != null) {
            an.a(listItemObject, this.p, this.q);
            this.i.setSelected("ding".equals(listItemObject.getFlag()));
            this.j.setSelected("cai".equals(listItemObject.getCai_flag()));
            this.m.setPostAvatarImage(listItemObject.getProfile());
        }
        b();
    }

    public void setmTitleLayoutClick(a aVar) {
        this.f = aVar;
    }

    public void a(boolean z) {
        Log.d("NewTitleView", "switchTitleStatus transparent=" + z);
        if (z) {
            if (!this.o) {
                this.o = true;
                this.g.setBackgroundResource(R.drawable.detail_title_layout_bg_tran);
                this.h.setImageResource(R.drawable.detail_title_back_tran_selector);
                this.i.setImageResource(R.drawable.detail_title_like_tran_selector);
                this.j.setImageResource(R.drawable.detail_title_hate_tran_selector);
                this.k.setImageResource(R.drawable.detail_title_share_tran_selector);
                this.n.setVisibility(8);
                a(BarrageStatusManager.a(this.d));
            }
        } else if (this.o) {
            this.o = false;
            this.g.setBackgroundResource(c.a().b(R.attr.new_detail_title_bg));
            this.h.setImageResource(c.a().b(R.attr.new_detail_title_back));
            this.i.setImageResource(c.a().b(R.attr.new_detail_title_like));
            this.j.setImageResource(c.a().b(R.attr.new_detail_title_hate));
            this.k.setImageResource(c.a().b(R.attr.new_detail_title_share));
            this.n.setVisibility(0);
            a(BarrageStatusManager.a(this.d));
        }
    }

    public void a(BarrageState barrageState) {
        if ("41".equals(this.e.getType())) {
            this.l.setVisibility(0);
            if (this.o) {
                switch (barrageState) {
                    case CLOSE:
                        this.l.setImageResource(R.drawable.detail_title_layout_barrage_close_tran);
                        return;
                    case SINGLE:
                        this.l.setImageResource(R.drawable.detail_title_layout_barrage_single_tran);
                        return;
                    case MULTI:
                        this.l.setImageResource(R.drawable.detail_title_layout_barrage_multi_tran);
                        return;
                    default:
                        return;
                }
            }
            switch (barrageState) {
                case CLOSE:
                    this.l.setImageResource(c.a().b(R.attr.new_detail_title_barrage_close));
                    return;
                case SINGLE:
                    this.l.setImageResource(c.a().b(R.attr.new_detail_title_barrage_single));
                    return;
                case MULTI:
                    this.l.setImageResource(c.a().b(R.attr.new_detail_title_barrage_multi));
                    return;
                default:
                    return;
            }
        }
        this.l.setVisibility(8);
    }

    private void b() {
        this.i.setOnClickListener(this.a);
        this.j.setOnClickListener(this.a);
        this.k.setOnClickListener(this.a);
        this.l.setOnClickListener(this.a);
        this.m.setOnClickListener(this.a);
    }

    public boolean a() {
        if (this.i.isSelected() || this.j.isSelected()) {
            return false;
        }
        this.i.setSelected(true);
        return true;
    }
}
