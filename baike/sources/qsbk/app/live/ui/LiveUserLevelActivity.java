package qsbk.app.live.ui;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.r0adkll.slidr.Slidr;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;
import qsbk.app.live.widget.ExpPrgressView;

public class LiveUserLevelActivity extends BaseActivity implements OnTouchListener {
    private TextView a;
    private TextView b;
    private TextView c;
    private ExpPrgressView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private ImageView k;
    private ImageView l;
    private ImageView m;
    private ImageView n;
    private ImageView o;
    private ImageView p;
    private ImageView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private GestureDetectorCompat u;
    private LinearLayout v;

    protected class SingleTapGestureListener extends SimpleOnGestureListener {
        final /* synthetic */ LiveUserLevelActivity a;

        public SingleTapGestureListener(LiveUserLevelActivity liveUserLevelActivity) {
            this.a = liveUserLevelActivity;
        }

        public boolean onDown(MotionEvent motionEvent) {
            if (this.a.t == null) {
                return false;
            }
            this.a.t.setVisibility(4);
            this.a.t = null;
            return true;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        Slidr.attach(this, AppUtils.getEdgeSlidrConfig());
    }

    protected void initView() {
        setUp();
        setTitle(getString(R.string.user_my_exp));
        this.a = (TextView) findViewById(R.id.tv_mark_lv);
        this.b = (TextView) findViewById(R.id.tv_cur_lv);
        this.c = (TextView) findViewById(R.id.tv_next_lv);
        this.d = (ExpPrgressView) findViewById(R.id.expv_user_exp);
        this.e = (TextView) $(R.id.tv_rank_intro);
        this.f = (TextView) $(R.id.tv_mic_intro);
        this.g = (TextView) $(R.id.tv_joinfamily_intro);
        this.h = (TextView) $(R.id.tv_charge_intro);
        this.i = (TextView) $(R.id.tv_createfamily_intro);
        this.j = (TextView) $(R.id.tv_enter_intro);
        this.k = (ImageView) $(R.id.iv_rank);
        this.l = (ImageView) $(R.id.iv_mic);
        this.m = (ImageView) $(R.id.iv_join_family);
        this.n = (ImageView) $(R.id.iv_charge);
        this.o = (ImageView) $(R.id.iv_create_family);
        this.p = (ImageView) $(R.id.iv_enter);
        this.q = (ImageView) $(R.id.iv_question);
        this.r = (TextView) $(R.id.tv_question_info);
        this.v = (LinearLayout) findViewById(R.id.touch_view);
        this.v.setClickable(true);
        this.v.setOnTouchListener(this);
        this.s = (TextView) $(R.id.tv_level);
    }

    protected void initData() {
        if (AppUtils.getInstance().getUserInfoProvider().getUser() != null) {
            NetRequest.getInstance().get(UrlConstants.USER_EXP, new ff(this));
        }
        this.u = new GestureDetectorCompat(this, new SingleTapGestureListener(this));
        a(this.k, this.e);
        a(this.l, this.f);
        a(this.m, this.g);
        a(this.n, this.h);
        a(this.o, this.i);
        a(this.p, this.j);
        a(this.q, this.r);
    }

    private void a(ImageView imageView, TextView textView) {
        imageView.setOnClickListener(new fg(this, textView));
    }

    protected int getLayoutId() {
        return R.layout.live_user_exp_activity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.u.onTouchEvent(motionEvent);
    }
}
