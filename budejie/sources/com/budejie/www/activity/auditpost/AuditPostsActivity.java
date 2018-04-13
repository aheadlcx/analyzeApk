package com.budejie.www.activity.auditpost;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.util.j;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.TipPopUp;
import com.budejie.www.activity.TipPopUp.TypeControl;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.view.JazzyViewPager;
import com.budejie.www.bean.ListInfo;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.ReportItem;
import com.budejie.www.bean.ShenHeItem;
import com.budejie.www.bean.TouGaoItem;
import com.budejie.www.g.d;
import com.budejie.www.h.c;
import com.budejie.www.http.i;
import com.budejie.www.http.m;
import com.budejie.www.util.aa;
import com.budejie.www.util.ac;
import com.budejie.www.util.an;
import com.budejie.www.util.p;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class AuditPostsActivity extends BaseActvityWithLoadDailog implements com.budejie.www.f.a {
    private TextView A;
    private TextView B;
    private TextView C;
    private TextView D;
    private TextView E;
    private TextView F;
    private LinearLayout G;
    private LinearLayout H;
    private LinearLayout I;
    private TextView J;
    private TextView K;
    private ImageView L;
    private ImageView M;
    private JazzyViewPager N;
    private b O;
    private TouGaoItem P;
    private ac Q;
    private PopupWindow R;
    private String S;
    private TextView T;
    private String U = "0";
    private a V;
    private boolean W;
    private int X = -1;
    private BroadcastReceiver Y = new BroadcastReceiver(this) {
        final /* synthetic */ AuditPostsActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.budejie.www.publishcomments.action")) {
                this.a.e(intent.getIntExtra(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, 1));
            }
        }
    };
    String a = "ShenHeActivity";
    int b = 1;
    com.budejie.www.activity.view.JazzyViewPager.a c = new com.budejie.www.activity.view.JazzyViewPager.a(this) {
        final /* synthetic */ AuditPostsActivity a;

        {
            this.a = r1;
        }

        public void a() {
            if (this.a.P != null && "41".equals(this.a.P.getType()) && this.a.N.getCurrentItem() != this.a.X) {
                a a = this.a.O.a(this.a.N.getCurrentItem());
                if (a != null) {
                    a.b();
                    this.a.X = this.a.N.getCurrentItem();
                    aa.a("tangjian", "onOver");
                }
            }
        }
    };
    OnClickListener d = new OnClickListener(this) {
        final /* synthetic */ AuditPostsActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (this.a.P != null) {
                if (view == this.a.H) {
                    this.a.o();
                    i.a(this.a.getString(R.string.track_event_audit_post_operation), "顶", this.a.s());
                } else if (view == this.a.I) {
                    this.a.p();
                    i.a(this.a.getString(R.string.track_event_audit_post_operation), "踩", this.a.s());
                } else if (view == this.a.J) {
                    this.a.q();
                    return;
                } else if (view == this.a.K) {
                    this.a.r();
                    i.a(this.a.getString(R.string.track_event_audit_post_operation), "跳过", this.a.s());
                }
                TipPopUp.a(this.a.h, TypeControl.shenhe);
                this.a.a(false);
            }
        }
    };
    Handler e = new Handler(this) {
        final /* synthetic */ AuditPostsActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            try {
                this.a.e();
                switch (message.what) {
                    case 950:
                        this.a.s.edit().putString("reportdata", (String) message.obj).commit();
                        return;
                    case 555550:
                        this.a.a(true);
                        int currentItem = this.a.N.getCurrentItem();
                        if (currentItem < this.a.O.getCount() - 1) {
                            this.a.N.setCurrentItem(currentItem + 1, true);
                            return;
                        }
                        return;
                    case 555552:
                        this.a.v.setVisibility(0);
                        return;
                    case 555553:
                        String str = (String) message.obj;
                        aa.e(j.c, str);
                        ListInfo a = com.budejie.www.j.a.a(str);
                        this.a.U = String.valueOf(a.np);
                        this.a.o = com.budejie.www.j.a.g(this.a.h, str);
                        if (this.a.o != null) {
                            ArrayList dataList = this.a.o.getDataList();
                            if (a.np == 0 || dataList == null || dataList.isEmpty()) {
                                this.a.p = an.a(this.a.h, this.a.h.getString(R.string.no_shenhe_data), -1);
                                this.a.p.show();
                                return;
                            }
                            if (this.a.n) {
                                this.a.N.setVisibility(0);
                                if (!this.a.l) {
                                    this.a.n = false;
                                    this.a.O.a();
                                    this.a.N.removeAllViews();
                                    this.a.N.setAdapter(this.a.O);
                                } else {
                                    return;
                                }
                            }
                            this.a.O.a(dataList);
                            if (!this.a.k) {
                                this.a.P = this.a.O.b(this.a.N.getCurrentItem());
                                this.a.k = true;
                                this.a.N.postDelayed(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass10 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        this.a.a.X = -1;
                                        this.a.a.c.a();
                                    }
                                }, 300);
                                return;
                            }
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    };
    OnClickListener f = new OnClickListener(this) {
        final /* synthetic */ AuditPostsActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            k.a(this.a.h).p();
            this.a.N.setVisibility(8);
            this.a.k = false;
            this.a.n = true;
            this.a.R.dismiss();
            this.a.i = (String) view.getTag();
            this.a.c(null, this.a.a(this.a.i));
            this.a.U = "0";
            this.a.n();
        }
    };
    private AuditPostsActivity h;
    private String i = "1";
    private String j;
    private boolean k = false;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    private ShenHeItem o;
    private Toast p;
    private Animation q;
    private m r;
    private SharedPreferences s;
    private d t;
    private LinearLayout u;
    private Button v;
    private LayoutParams w = new LayoutParams(-1, -2);
    private TextView x;
    private TextView y;
    private TextView z;

    class a extends BroadcastReceiver {
        final /* synthetic */ AuditPostsActivity a;

        a(AuditPostsActivity auditPostsActivity) {
            this.a = auditPostsActivity;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                if (this.a.P.getDataId().equals(intent.getStringExtra("posts_id"))) {
                    this.a.W = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public TouGaoItem a() {
        return this.P;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.Q = ac.a((Context) this);
        this.V = new a(this);
        registerReceiver(this.V, new IntentFilter("play.video.action"));
        setContentView(R.layout.audit_posts_layout);
        d(R.id.navigation_bar);
        c().setBackgroundResource(c.a().b(R.attr.title_background));
        this.h = this;
        t();
        a(null);
        b();
        l();
        k();
        j();
        m();
        i();
        registerReceiver(this.Y, new IntentFilter("com.budejie.www.publishcomments.action"));
        i.a(getString(R.string.track_event_start_audit_posts), getString(R.string.track_event_start_audit_posts));
        if (VERSION.SDK_INT <= 18) {
            k.a(this.h).h();
        }
    }

    private void c(final OnClickListener onClickListener, String str) {
        if (this.T == null) {
            this.T = (TextView) getLayoutInflater().inflate(R.layout.navigation_bar_center_spinner, null);
            if (this.g != null) {
                this.g.setMiddleView(this.T);
            }
        }
        this.T.setText(str);
        this.T.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AuditPostsActivity b;

            public void onClick(View view) {
                aa.b(this.b.a, "v=" + view.getId());
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                } else {
                    this.b.onCenterSpinnerClick(view);
                }
            }
        });
    }

    public void b(int i) {
        if (this.T != null) {
            this.T.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
        }
    }

    public void c(int i) {
        if (this.T != null) {
            this.T.setTextColor(i);
        }
    }

    private void d(final OnClickListener onClickListener, String str) {
        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.navigation_bar_title, null);
        textView.setText(str);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AuditPostsActivity b;

            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                } else {
                    this.b.g();
                }
            }
        });
        if (this.g != null) {
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            textView.setLayoutParams(layoutParams);
            this.g.setRightView(textView);
        }
    }

    private void g() {
        this.j = this.s.getString("id", "");
        if (TextUtils.isEmpty(this.j)) {
            an.a(this.h, 0, null, null, 0);
        } else if (this.P != null) {
            com.budejie.www.util.a.a(this.h, h());
        }
    }

    private ListItemObject h() {
        int parseInt;
        int i = 0;
        ListItemObject listItemObject = new ListItemObject();
        listItemObject.setWid(this.P.getDataId());
        listItemObject.setType(this.P.getType());
        try {
            parseInt = Integer.parseInt(this.P.getWidth());
        } catch (Exception e) {
            parseInt = i;
        }
        try {
            i = Integer.parseInt(this.P.getHeight());
        } catch (Exception e2) {
        }
        listItemObject.setWidth(parseInt);
        listItemObject.setHeight(i);
        listItemObject.setIs_gif(this.P.getIsGif() ? "1" : "0");
        listItemObject.setDownloadVideoUris(this.P.getDownloadVideoUris());
        listItemObject.setImgUrl(this.P.getImgUrl());
        listItemObject.setGifFistFrame(this.P.getGifFistFrame());
        listItemObject.setVideotime(this.P.getVideotime());
        return listItemObject;
    }

    private void i() {
        if (this.s.getBoolean("first_shenhe", true)) {
            this.u.setVisibility(0);
            com.budejie.www.util.c.a(findViewById(R.id.shenhe_prompt_image));
            com.budejie.www.util.c.a(findViewById(R.id.start_shenheBtn));
            this.G.setVisibility(8);
            this.N.setVisibility(8);
            return;
        }
        this.N.setVisibility(0);
        this.G.setVisibility(0);
        this.u.setVisibility(8);
        c(null, a(this.i));
        d(null, getResources().getString(R.string.write_commend));
        n();
    }

    private void j() {
        try {
            this.N = (JazzyViewPager) findViewById(R.id.mypager);
            this.N.setPageMargin(30);
            this.N.setScrollDurationFactor(6.0d);
            this.O = new b(getSupportFragmentManager(), this.N);
            this.N.setAdapter(this.O);
            this.N.setOnPageChangeListener(new SimpleOnPageChangeListener(this) {
                final /* synthetic */ AuditPostsActivity a;

                {
                    this.a = r1;
                }

                public void onPageSelected(int i) {
                    super.onPageSelected(i);
                    if (this.a.Q != null && this.a.Q.c()) {
                        this.a.Q.g();
                    }
                    this.a.P = this.a.O.b(this.a.N.getCurrentItem());
                    if (i == this.a.N.getAdapter().getCount() - 1) {
                        this.a.n();
                    }
                    k.a(this.a.h).p();
                    aa.a("tangjian", "setOnPageChangeListener");
                }
            });
            this.N.setOnScrollExecutOverListener(this.c);
        } catch (Exception e) {
        }
    }

    private void k() {
        this.q = AnimationUtils.loadAnimation(this, R.anim.ding_scale);
        this.r = new m();
        this.j = this.s.getString("id", "");
        this.S = getIntent().getStringExtra("post_type");
        if (!TextUtils.isEmpty(this.S)) {
            this.i = b(this.S);
            c(null, a(this.i));
            c(-1);
        }
        sendBroadcast(new Intent("android.hide.sister.my.NOTIFYTIPS"));
    }

    private void l() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.u = (LinearLayout) findViewById(R.id.first_shenhe_page);
        this.v = (Button) findViewById(R.id.click_repeat);
        this.x = (TextView) findViewById(R.id.shenhe_remind_text);
        this.y = (TextView) findViewById(R.id.shenhe_1);
        this.z = (TextView) findViewById(R.id.shenhe_2);
        this.E = (TextView) findViewById(R.id.shenhe_3);
        this.F = (TextView) findViewById(R.id.shenhe_4);
        this.A = (TextView) findViewById(R.id.shenhe_ding_tv);
        this.B = (TextView) findViewById(R.id.shenhe_cai_tv);
        this.C = (TextView) findViewById(R.id.shenhe_ding_text);
        this.D = (TextView) findViewById(R.id.shenhe_cai_text);
        this.H = (LinearLayout) findViewById(R.id.shenhe_dingLayout);
        this.I = (LinearLayout) findViewById(R.id.shenhe_caiLayout);
        this.J = (TextView) findViewById(R.id.shenhe_report);
        this.K = (TextView) findViewById(R.id.shenhe_skip);
        this.L = (ImageView) findViewById(R.id.shenhe_ding_img);
        this.M = (ImageView) findViewById(R.id.shenhe_cai_img);
        this.G = (LinearLayout) findViewById(R.id.dcLayout);
        this.J.setOnClickListener(this.d);
        this.K.setOnClickListener(this.d);
        this.H.setOnClickListener(this.d);
        this.I.setOnClickListener(this.d);
        this.s = getSharedPreferences("weiboprefer", 0);
        if (!this.s.getBoolean("shenheUpdate", false)) {
            this.s.edit().putBoolean("shenheUpdate", true).commit();
        }
    }

    private void m() {
        if (TextUtils.isEmpty(this.s.getString("reportdata", ""))) {
            this.r.a((Activity) this, (com.budejie.www.f.a) this, 1111124);
        }
    }

    private void n() {
        if (!isFinishing()) {
            f();
        }
        this.l = true;
        this.r.a((Activity) this, this.i, this.U, (com.budejie.www.f.a) this, 555551);
    }

    public void start_shenhe$Click(View view) {
        if (!this.l) {
            n();
        }
        this.N.setVisibility(0);
        this.G.setVisibility(0);
        this.u.setVisibility(8);
        c(null, a(this.i));
        d(null, getResources().getString(R.string.write_commend));
        this.s.edit().putBoolean("first_shenhe", false).commit();
    }

    public void clickRepeat$Click(View view) {
        if (an.a((Context) this)) {
            this.m = true;
            this.v.setVisibility(8);
            n();
            return;
        }
        this.p = an.a((Activity) this, getString(R.string.nonet), -1);
        this.p.show();
    }

    private void a(boolean z) {
        if (z) {
            this.J.setClickable(true);
            this.K.setClickable(true);
            this.I.setClickable(true);
            this.H.setClickable(true);
            this.I.setSelected(false);
            this.H.setSelected(false);
            this.C.setVisibility(0);
            this.D.setVisibility(0);
            this.A.setVisibility(8);
            this.B.setVisibility(8);
            return;
        }
        this.C.setVisibility(8);
        this.D.setVisibility(8);
        this.A.setVisibility(0);
        this.B.setVisibility(0);
        this.J.setClickable(false);
        this.K.setClickable(false);
        this.I.setClickable(false);
        this.H.setClickable(false);
    }

    private void o() {
        int parseInt;
        a a = this.O.a(this.N.getCurrentItem());
        if (a != null) {
            a.c();
        }
        this.m = false;
        this.A.setText(this.P.getDingCount());
        this.B.setText(this.P.getCaiCount());
        try {
            parseInt = Integer.parseInt(this.A.getText().toString()) + 1;
        } catch (NumberFormatException e) {
            aa.e(this.a, "handleDing , NumberFormatException, currentPage = 1");
            parseInt = 0;
        }
        this.A.setText(parseInt + "");
        this.H.setSelected(true);
        this.t = new d(this.y, this.e, 555550);
        this.q.setAnimationListener(this.t);
        this.y.startAnimation(this.q);
        if (!"41".equals(this.P.getType()) || this.W) {
            this.W = false;
            this.r.a((Activity) this, this.j, this.P.getDataId(), "1");
            return;
        }
        this.r.a((Activity) this, this.j, this.P.getDataId(), "3");
    }

    private void p() {
        int parseInt;
        this.m = false;
        a a = this.O.a(this.N.getCurrentItem());
        if (a != null) {
            a.d();
        }
        this.A.setText(this.P.getDingCount());
        this.B.setText(this.P.getCaiCount());
        try {
            parseInt = Integer.parseInt(this.B.getText().toString()) + 1;
        } catch (NumberFormatException e) {
            aa.e(this.a, "handleCai , NumberFormatException, currentPage = 1");
            parseInt = 0;
        }
        this.B.setText(parseInt + "");
        this.I.setSelected(true);
        this.t = new d(this.z, this.e, 555550);
        this.q.setAnimationListener(this.t);
        this.z.startAnimation(this.q);
        if (!"41".equals(this.P.getType()) || this.W) {
            this.W = false;
            this.r.a((Activity) this, this.j, this.P.getDataId(), "2");
            return;
        }
        this.r.a((Activity) this, this.j, this.P.getDataId(), "3");
    }

    private void q() {
        p.a(this.h, this.j, this.P.getDataId(), new OnClickListener(this) {
            final /* synthetic */ AuditPostsActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.A.setText(this.a.P.getDingCount());
                this.a.B.setText(this.a.P.getCaiCount());
                this.a.t = new d(this.a.E, this.a.e, 555550);
                this.a.q.setAnimationListener(this.a.t);
                this.a.E.startAnimation(this.a.q);
                this.a.a(false);
                ReportItem reportItem = (ReportItem) (view.getTag() == null ? null : view.getTag());
                if (reportItem != null) {
                    if (!"举报".equals(reportItem.getReportContent())) {
                        i.a(this.a.getString(R.string.track_event_audit_post_operation), "举报-" + reportItem.getReportContent(), this.a.s());
                    }
                }
            }
        });
    }

    private void r() {
        this.A.setText(this.P.getDingCount());
        this.B.setText(this.P.getCaiCount());
        this.m = false;
        this.F.postDelayed(new Runnable(this) {
            final /* synthetic */ AuditPostsActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.e.sendEmptyMessage(555550);
            }
        }, 0);
        this.r.a((Activity) this, this.j, this.P.getDataId(), "3");
    }

    public void a(int i, String str) {
        if (i == 555551) {
            this.b = 1;
            this.l = false;
            this.e.sendMessage(this.e.obtainMessage(555553, str));
        } else if (i == 1111124) {
            this.e.sendMessage(this.e.obtainMessage(950, str));
        }
    }

    public void a(int i) {
        if (i == 555551) {
            this.b++;
            if (this.b <= 2) {
                n();
                return;
            }
            this.l = false;
            this.e.sendEmptyMessage(555552);
        }
    }

    private String s() {
        return new StringBuilder(this.P.getContent()).append("|").append(this.P.getDataId()).append("|").append(com.budejie.www.http.j.b((Context) this, an.a(this.P))).toString();
    }

    public void onCenterSpinnerClick(View view) {
        aa.b(this.a, "filterwindow.isShowing()=" + this.R.isShowing());
        if (!this.u.isShown() && !this.R.isShowing()) {
            a(view);
            b((int) R.drawable.arrow_up_press);
            ((TextView) view).setTextColor(-1);
        }
    }

    private void e(int i) {
        try {
            a c = this.O.c(this.N.getCurrentItem());
            if (c != null) {
                switch (i) {
                    case 1:
                        c.a(true);
                        return;
                    case 2:
                        c.a(false);
                        return;
                    case 3:
                        c.a();
                        return;
                    default:
                        return;
                }
            }
        } catch (Exception e) {
        }
    }

    public void b() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.filter_content_layout, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv1);
        textView.setTag(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        textView.setOnClickListener(this.f);
        textView = (TextView) inflate.findViewById(R.id.tv2);
        textView.setTag("31");
        textView.setOnClickListener(this.f);
        textView = (TextView) inflate.findViewById(R.id.tv3);
        textView.setTag("1");
        textView.setOnClickListener(this.f);
        textView = (TextView) inflate.findViewById(R.id.tv4);
        textView.setTag("29");
        textView.setOnClickListener(this.f);
        textView = (TextView) inflate.findViewById(R.id.tv5);
        textView.setTag("41");
        textView.setOnClickListener(this.f);
        textView = (TextView) inflate.findViewById(R.id.rich_text);
        textView.setTag("51");
        textView.setOnClickListener(this.f);
        this.R = new PopupWindow(inflate);
        this.R.setWindowLayoutMode(-2, -2);
        this.R.setBackgroundDrawable(getResources().getDrawable(R.drawable.filter_popwindow_bg));
        this.R.setOutsideTouchable(true);
        this.R.setFocusable(true);
        this.R.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ AuditPostsActivity a;

            {
                this.a = r1;
            }

            public void onDismiss() {
                this.a.b((int) R.drawable.arrow_down_selector);
            }
        });
    }

    private void a(View view) {
        int b = (int) (((float) R$styleable.Theme_Custom_send_btn_text_color) * com.budejie.www.util.i.a().b((Context) this));
        int width = (view.getWidth() - b) / 2;
        aa.b(this.a, "xOff=" + width);
        aa.b(this.a, "view.getWidth()=" + view.getWidth());
        aa.b(this.a, "width=" + b);
        aa.b(this.a, "filterwindow.getWidth()=" + this.R.getWidth());
        this.R.showAsDropDown(view, width, 0);
    }

    protected void onDestroy() {
        unregisterReceiver(this.Y);
        unregisterReceiver(this.V);
        if (this.R != null && this.R.isShowing()) {
            this.R.dismiss();
        }
        super.onDestroy();
        k.a((Context) this).p();
    }

    private String t() {
        if (an.b((Context) this).equals("1")) {
            this.i = "1";
        } else {
            this.i = "29";
        }
        return this.i;
    }

    private String a(String str) {
        String str2 = "筛选";
        if (str.equals("1")) {
            return "全部";
        }
        if (str.equals("29")) {
            return "段子";
        }
        if (str.equals(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ)) {
            return "图文";
        }
        if (str.equals("31")) {
            return "声音";
        }
        if (str.equals("41")) {
            return "视频";
        }
        if (str.equals("51")) {
            return "文章";
        }
        return str2;
    }

    private String b(String str) {
        return "1";
    }

    protected void onPause() {
        if (this.Q != null && this.Q.c()) {
            this.Q.g();
            BudejieApplication.b().a(Status.end);
        }
        super.onPause();
    }
}
