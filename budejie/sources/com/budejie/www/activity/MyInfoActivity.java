package com.budejie.www.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import cn.tatagou.sdk.android.TtgInterface;
import cn.v6.sixrooms.room.gift.GiftConfigUtil;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.auditpost.AuditPostsActivity;
import com.budejie.www.activity.htmlpage.HtmlFeatureActivity;
import com.budejie.www.activity.htmlpage.HtmlSkipParams.HtmlTypeTougao;
import com.budejie.www.activity.htmlpage.b;
import com.budejie.www.activity.image.CaptureActivity;
import com.budejie.www.activity.label.ActivitiesTopicActivity;
import com.budejie.www.activity.label.LabelBean;
import com.budejie.www.activity.label.f;
import com.budejie.www.activity.mycomment.MyCommentActivity;
import com.budejie.www.activity.recommend.SuggestedFollowsActivity;
import com.budejie.www.activity.view.BadgeView;
import com.budejie.www.activity.view.LinearLayoutForListView;
import com.budejie.www.bean.NotificationSettingItem;
import com.budejie.www.bean.UserItem;
import com.budejie.www.c.m;
import com.budejie.www.h.c;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.h;
import com.budejie.www.http.n;
import com.budejie.www.type.MySquareIcon;
import com.budejie.www.type.MySquareItem;
import com.budejie.www.type.UpdateUserInfo;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.av;
import com.budejie.www.util.g;
import com.budejie.www.util.i;
import com.budejie.www.util.j;
import com.budejie.www.util.p;
import com.budejie.www.util.u;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;
import com.zxt.download2.DownloadListActivity;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;

public class MyInfoActivity extends OauthWeiboBaseAct implements b {
    private static final String am = Environment.getExternalStorageDirectory().getPath();
    public static TextView b;
    public static BadgeView c;
    public static List<NotificationSettingItem> d;
    private RelativeLayout A;
    private LinearLayout B;
    private LinearLayout C;
    private LinearLayout D;
    private LinearLayout E;
    private Toast F;
    private LinearLayout G;
    private TextView H;
    private TextView I;
    private BadgeView J;
    private BadgeView K;
    private BadgeView L;
    private BadgeView M;
    private BadgeView N;
    private String O = "";
    private boolean P = false;
    private TextView Q;
    private TextView R;
    private IntentFilter S = new IntentFilter("android.budejie.more.NEWS_UPDATE");
    private List<MySquareIcon> T;
    private List<MySquareIcon> U;
    private AsyncImageView V;
    private LinearLayout W;
    private LinearLayoutForListView X;
    private List<LabelBean> Y;
    private BudejieApplication Z;
    String a = MyInfoActivity.class.getSimpleName();
    private SharedPreferences aa;
    private a ab;
    private View ac;
    private View ad;
    private n ae;
    private TextView af;
    private TextView ag;
    private TextView ah;
    private UserItem ai;
    private int aj;
    private LinearLayout ak;
    private Handler al = new Handler(this) {
        final /* synthetic */ MyInfoActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    aa.a("MyInfoActivity", "获取缓存后刷新列表");
                    break;
            }
            super.handleMessage(message);
        }
    };
    private net.tsz.afinal.a.a<String> an = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ MyInfoActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            aa.e("wuzhenlin", "onFailure " + str);
            if (!this.a.i.isFinishing()) {
                this.a.F = an.a(this.a.i, this.a.getString(R.string.load_failed), -1);
                this.a.F.show();
            }
        }

        public void a(String str) {
            aa.e("wuzhenlin", "onSuccess json " + str);
            try {
                UpdateUserInfo updateUserInfo = (UpdateUserInfo) new Gson().fromJson(str, UpdateUserInfo.class);
                this.a.o.a(updateUserInfo, this.a.p);
                this.a.h();
                MyInfoActivity.d = updateUserInfo.getSwitchsList();
            } catch (Exception e) {
                e.printStackTrace();
                aa.e("", "ljj-->" + e.toString());
                if (!this.a.i.isFinishing()) {
                    this.a.F = an.a(this.a.i, this.a.getString(R.string.load_failed), -1);
                    this.a.F.show();
                }
            }
        }
    };
    private OnClickListener ao = new OnClickListener(this) {
        final /* synthetic */ MyInfoActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            MobclickAgent.onEvent(this.a, "E02-A08", "设置");
            this.a.startActivity(new Intent(this.a.i, MoreActivity.class));
        }
    };
    private OnClickListener ap = new OnClickListener(this) {
        final /* synthetic */ MyInfoActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (!an.a(this.a.q) || this.a.ai == null) {
                an.a(this.a.i, null);
                return;
            }
            this.a.N.b();
            MobclickAgent.onEvent(this.a, "E02-A08", "我的积分");
            Intent intent = new Intent(this.a.i, DetailContentActivity.class);
            intent.putExtra("operator", "my_coins");
            intent.putExtra("url", this.a.ai.getTrade_ruler());
            intent.putExtra("trade_history", this.a.ai.getTrade_history());
            aa.b(this.a.a, "userItem.getTrade_history()=" + this.a.ai.getTrade_history());
            this.a.i.startActivity(intent);
        }
    };
    private OnClickListener aq = new OnClickListener(this) {
        final /* synthetic */ MyInfoActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (!an.b()) {
                ((BudejieApplication) this.a.getApplication()).g().al.a(Boolean.valueOf(true));
                MobclickAgent.onEvent(this.a, "E02-A08", "切换夜间模式");
                SwitchThemeMiddleActivity.a = c.a(this.a.i.getParent().findViewById(R.id.curtain_root_layout));
                this.a.changeCurrentTheme();
                com.b.a.a(ai.a(this.a.i) != 0);
                HomeGroup homeGroup = (HomeGroup) this.a.getParent();
                Intent intent = new Intent(this.a.i, SwitchThemeMiddleActivity.class);
                if (SwitchThemeMiddleActivity.a == null) {
                    intent = new Intent(this.a.i, HomeGroup.class);
                }
                intent.putExtra("tag_all", "myinfo_type");
                if (SwitchThemeMiddleActivity.a == null) {
                    aa.b(this.a.a, "changeTheme");
                    i.a().a(homeGroup, intent);
                    return;
                }
                aa.b(this.a.a, "startActivity(intent)");
                this.a.startActivity(intent);
                this.a.getParent().overridePendingTransition(0, 0);
                this.a.finish();
            }
        }
    };
    com.budejie.www.adapter.g.b.b e;
    int f;
    net.tsz.afinal.a.a<String> g = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ MyInfoActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
        }

        public void a(String str) {
            aa.a("MyInfoActivity", "结果：" + str);
            try {
                MySquareItem mySquareItem = (MySquareItem) new Gson().fromJson(str, MySquareItem.class);
                if (mySquareItem != null && mySquareItem.getMySquares() != null && mySquareItem.getMySquares().size() > 0) {
                    this.a.T = mySquareItem.getMySquares();
                    this.a.e.a(this.a.T);
                    this.a.c();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            aa.a("MyInfoActivity", "加载广场数据失败：" + str);
        }
    };
    net.tsz.afinal.a.a<String> h = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ MyInfoActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
        }

        public void a(String str) {
            com.budejie.www.activity.label.b b = f.b(str);
            if (b == null) {
                this.a.F = an.a(this.a, this.a.getString(R.string.load_failed), -1);
                this.a.F.show();
                return;
            }
            this.a.Y = b.a;
            if (this.a.Y == null) {
                this.a.F = an.a(this.a, this.a.getString(R.string.load_failed), -1);
                this.a.F.show();
                return;
            }
            this.a.X.a(this.a.i, new com.budejie.www.adapter.a.c(this.a.Y, this.a.i));
        }

        public void onFailure(Throwable th, int i, String str) {
            aa.a("MyInfoActivity", "加载活动数据失败：" + str);
        }
    };
    private MyInfoActivity i;
    private TextView j;
    private LinearLayout k;
    private LinearLayout l;
    private ImageButton m;
    private TextView n;
    private m o;
    private String p;
    private SharedPreferences q;
    private RelativeLayout r;
    private ScrollView s;
    private AsyncImageView t;
    private TextView u;
    private TextView v;
    private TextView w;
    private TextView x;
    private TextView y;
    private TextView z;

    private class a extends BroadcastReceiver {
        final /* synthetic */ MyInfoActivity a;

        private a(MyInfoActivity myInfoActivity) {
            this.a = myInfoActivity;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.budejie.more.NEWS_UPDATE")) {
                if (HomeGroup.h > 0) {
                    CharSequence valueOf = String.valueOf(HomeGroup.h);
                    if (HomeGroup.h > 99) {
                        valueOf = "99+";
                    }
                    this.a.J.setText(valueOf);
                    this.a.J.a();
                } else if (HomeGroup.o != 1) {
                    this.a.J.b();
                }
            } else if (intent.getAction().equals("android.budejie.more.FANS_ADD_UPDATE")) {
                if (HomeGroup.i > 0) {
                    this.a.L.setText(String.valueOf(HomeGroup.i));
                    this.a.L.a();
                    return;
                }
                this.a.L.b();
            } else if (intent.getAction().equals("android.budejie.more.FRIEND_UPDATE")) {
                if (HomeGroup.j > 0) {
                    this.a.K.a(this.a.f, this.a.f);
                } else {
                    this.a.K.b();
                }
            } else if (intent.getAction().equals("android.budejie.more.MYFRIEND_UPDATE")) {
                if (HomeGroup.j > 0) {
                    this.a.K.a(this.a.f, this.a.f);
                    MyInfoActivity.c.a(this.a.f, this.a.f);
                    if (TextUtils.isEmpty(HomeGroup.k)) {
                        this.a.V.setVisibility(4);
                        return;
                    } else {
                        this.a.V.setVisibility(4);
                        return;
                    }
                }
                this.a.K.b();
                MyInfoActivity.c.b();
                if (!(this.a.J.isShown() || this.a.L.isShown() || this.a.M.isShown())) {
                    this.a.i.sendBroadcast(new Intent("android.hide.sister.news.NOTIFYTIPS"));
                }
                this.a.V.setVisibility(4);
            } else if (intent.getAction().equals("android.budejie.more.RECOMMEND_UPDATE")) {
                if (HomeGroup.l > 0) {
                    this.a.M.setText(String.valueOf(HomeGroup.l));
                    this.a.M.a();
                    return;
                }
                this.a.M.b();
                if (!this.a.J.isShown() && !this.a.L.isShown() && !MyInfoActivity.c.isShown()) {
                    this.a.i.sendBroadcast(new Intent("android.hide.sister.news.NOTIFYTIPS"));
                }
            } else if (intent.getAction().equals("android.budejie.more.UPDATE_RED_PACKET")) {
                if (HomeGroup.o == 1) {
                    this.a.N.a(this.a.f, this.a.f);
                } else {
                    this.a.N.b();
                }
            } else if (intent.getAction().equals("android.budejie.more.UPDATE_RED_PACKET_HIDE")) {
                this.a.N.b();
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.my_info);
        this.i = this;
        f();
        a(getIntent(), true);
        e();
        this.Z = (BudejieApplication) getApplication();
        d();
        a();
        if (an.a(this.q)) {
            HomeGroup.a(getBaseContext());
        }
        a(getIntent().getStringExtra("jump_type"));
    }

    public void a(String str) {
        if ("jump_type_follow".equals(str) && an.a(this.q)) {
            i();
        }
    }

    private void a() {
        this.aa = getSharedPreferences("mySquareIconCache", 2);
        k();
        aa.a("MyInfoActivity", "读缓存");
        b();
    }

    private void b() {
        final Object string = this.aa.getString("mySquareIconCache", "");
        if (TextUtils.isEmpty(string)) {
            k();
        } else {
            new Thread(this) {
                final /* synthetic */ MyInfoActivity b;

                public void run() {
                    try {
                        this.b.T = (List) new ObjectInputStream(new ByteArrayInputStream(Base64.decodeBase64(string.getBytes()))).readObject();
                        this.b.e.a(this.b.T);
                        this.b.al.sendEmptyMessage(1);
                        aa.a("MyInfoActivity", "从缓存中获取:" + this.b.T.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    private void c() {
        new Thread(this) {
            final /* synthetic */ MyInfoActivity a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.a.T != null && this.a.T.size() > 0) {
                    try {
                        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        new ObjectOutputStream(byteArrayOutputStream).writeObject(this.a.T);
                        String str = new String(Base64.encodeBase64(byteArrayOutputStream.toByteArray()));
                        Editor edit = this.a.aa.edit();
                        edit.putString("mySquareIconCache", str);
                        edit.commit();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void d() {
        this.U = new ArrayList();
        this.U.add(new MySquareIcon(1, "审帖", "mod://BDJ_To_Check", (int) R.drawable.myinfo_icon_st_selector));
        this.U.add(new MySquareIcon(2, "我的收藏", "mod://BDJ_To_Mine@dest=2", (int) R.drawable.myinfo_icon_collect_normal));
        this.U.add(new MySquareIcon(3, "搜索", "mod://App_To_SearchUser", (int) R.drawable.myinfo_icon_search_selector));
        this.U.add(new MySquareIcon(4, "我的视频", "mod://App_To_MyVideo", (int) R.drawable.down_video));
        View inflate = this.i.getLayoutInflater().inflate(R.layout.my_square_layout, null);
        this.e = new com.budejie.www.adapter.g.b.b(this, this.U, inflate);
        ((LinearLayout) findViewById(R.id.mysquare_icon_layout)).addView(inflate);
    }

    private void e() {
        try {
            this.ab = new a();
            this.S.addAction("android.budejie.more.FRIEND_UPDATE");
            this.S.addAction("android.budejie.more.FANS_ADD_UPDATE");
            this.S.addAction("android.budejie.more.MYFRIEND_UPDATE");
            this.S.addAction("android.budejie.more.RECOMMEND_UPDATE");
            this.S.addAction("android.budejie.more.UPDATE_RED_PACKET");
        } catch (Exception e) {
        }
    }

    private void f() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.o = new m(this);
        this.ae = new n(this.i);
        this.q = getSharedPreferences("weiboprefer", 0);
        this.p = this.q.getString("id", "");
        this.r = (RelativeLayout) findViewById(R.id.my_layout);
        this.s = (ScrollView) findViewById(R.id.m_scroll);
        b = (TextView) findViewById(R.id.title_center_txt);
        this.j = (TextView) findViewById(R.id.title_left_btn);
        this.k = (LinearLayout) findViewById(R.id.left_layout);
        this.k.setVisibility(0);
        this.l = (LinearLayout) findViewById(R.id.right2_layout);
        this.m = (ImageButton) findViewById(R.id.night_model);
        this.ag = (TextView) findViewById(R.id.user_level);
        this.af = (TextView) findViewById(R.id.user_v);
        this.ah = (TextView) findViewById(R.id.user_signature);
        this.l.setVisibility(0);
        this.l.setOnClickListener(this.aq);
        this.m.setOnClickListener(this.aq);
        this.k.setOnClickListener(this.ap);
        b.setText("我的");
        View findViewById = findViewById(R.id.right_layout);
        findViewById.setVisibility(0);
        this.n = (TextView) findViewById(R.id.title_right_btn);
        this.n.setBackgroundResource(R.drawable.myinfo_setting_selector);
        this.n.setText("");
        findViewById.setOnClickListener(this.ao);
        this.t = (AsyncImageView) findViewById(R.id.user_profile_img);
        this.u = (TextView) findViewById(R.id.user_name);
        this.v = (TextView) findViewById(R.id.follower_count);
        this.w = (TextView) findViewById(R.id.follower_tv);
        this.x = (TextView) findViewById(R.id.fans_count);
        this.y = (TextView) findViewById(R.id.fans_tv);
        this.C = (LinearLayout) findViewById(R.id.fans_follower_layout);
        this.D = (LinearLayout) findViewById(R.id.follower_layout);
        this.E = (LinearLayout) findViewById(R.id.fans_layout);
        this.R = (TextView) findViewById(R.id.myinfo_collect);
        this.Q = (TextView) findViewById(R.id.myinfo_collect_num);
        this.G = (LinearLayout) findViewById(R.id.myinfo_msg_notification);
        this.H = (TextView) findViewById(R.id.myinfo_news_notification_text);
        this.z = (TextView) findViewById(R.id.follower_dynamic_state);
        this.A = (RelativeLayout) findViewById(R.id.follower_dynamic_state_rl);
        this.B = (LinearLayout) findViewById(R.id.follower_dynamic_state_layout);
        this.I = (TextView) findViewById(R.id.recommendFriend);
        this.V = (AsyncImageView) findViewById(R.id.newestFriendHeader);
        this.W = (LinearLayout) findViewById(R.id.newestFriendHeader_layout);
        this.X = (LinearLayoutForListView) findViewById(R.id.activityCell);
        this.ac = findViewById(R.id.divider1);
        this.ad = findViewById(R.id.divider2);
        this.ak = (LinearLayout) findViewById(R.id.MyPayItem);
        g();
        if (HomeGroup.h > 0) {
            CharSequence valueOf = String.valueOf(HomeGroup.h);
            if (HomeGroup.h > 99) {
                valueOf = "99+";
            }
            this.J.setText(valueOf);
            this.J.a();
        } else {
            this.J.b();
        }
        if (HomeGroup.i > 0) {
            this.L.setText(String.valueOf(HomeGroup.i));
            this.L.a();
        } else {
            this.L.b();
        }
        if (HomeGroup.j > 0) {
            c.a(this.f, this.f);
            if (TextUtils.isEmpty(HomeGroup.k)) {
                this.V.setVisibility(4);
            } else {
                this.V.setVisibility(4);
            }
        } else {
            c.b();
            this.V.setVisibility(4);
        }
        if (HomeGroup.l > 0) {
            this.M.setText(String.valueOf(HomeGroup.l));
            this.M.a();
        } else {
            this.M.b();
        }
        if (HomeGroup.o == 1) {
            this.N.a(this.f, this.f);
        } else {
            this.N.b();
        }
    }

    private void g() {
        this.f = an.a((Context) this, 8);
        this.M = an.a(this.i, this.I, true, 30, 0, 7, 10.0f);
        c = an.a(this.i, this.B, false, 30, 0, 7, 10.0f);
        this.K = an.a(this.i, this.D, false, 20, 0, 2, 10.0f);
        this.L = an.a(this.i, this.E, true, 10, 0, 2, 10.0f);
        this.J = an.a(this.i, this.G, true, 15, 0, 2, 10.0f);
        this.N = an.a(this.i, this.k, false, 17, 8, 2, 0.0f, true);
    }

    public void a(Intent intent, boolean z) {
        Bundle bundleExtra = intent.getBundleExtra("bundle");
        if (bundleExtra == null) {
            aa.a("zhangxitao", "bundle is null");
            return;
        }
        aa.a("zhangxitao", "bundle-->" + bundleExtra);
        HtmlTypeTougao htmlTypeTougao = (HtmlTypeTougao) bundleExtra.getSerializable("tougao");
        String string = bundleExtra.getString("param");
        if (string == null) {
            string = "";
        }
        if (htmlTypeTougao != null) {
            Intent intent2;
            switch (htmlTypeTougao) {
                case SEND_PICTURE:
                    intent2 = new Intent(this.i, TougaoActivity.class);
                    intent2.putExtra("TOUGAO_TYPE", 10);
                    intent2.putExtra("h5_reserve", string);
                    this.i.startActivity(intent2);
                    break;
                case SEND_SHORTJOKE:
                    intent2 = new Intent(this.i, TougaoActivity.class);
                    intent2.putExtra("TOUGAO_TYPE", 29);
                    intent2.putExtra("h5_reserve", string);
                    this.i.startActivity(intent2);
                    break;
                case SEND_VOICE:
                    this.O = string;
                    intent2 = new Intent(this, CaptureActivity.class);
                    intent2.putExtra("output", new File(Environment.getExternalStorageDirectory(), i.a().d() + ".jpg").getAbsolutePath());
                    intent2.putExtra("type", "voice");
                    if (!this.P) {
                        intent2.putExtra("reserve", string);
                    }
                    startActivity(intent2);
                    this.P = true;
                    g.a(this.i).d();
                    break;
            }
        }
        String string2 = bundleExtra.getString("htmlUrl");
        aa.a(this.a, "htmlUrl-->" + string2);
        if (string2 != null) {
            Intent intent3 = new Intent(this.i, HtmlFeatureActivity.class);
            intent3.setData(Uri.parse(string2));
            this.i.startActivity(intent3);
        }
    }

    public void onrefreshTheme() {
        b.setTextColor(getResources().getColor(j.b));
        this.r.setBackgroundResource(j.m);
        if (!ai.c(this.i) || this.ai == null || TextUtils.isEmpty(this.ai.getName())) {
            this.u.setTextColor(this.i.getResources().getColor(R.color.comment_item_content_color));
        } else {
            this.u.setTextColor(this.i.getResources().getColor(j.H));
        }
        this.z.setTextColor(getResources().getColor(j.M));
        this.I.setTextColor(getResources().getColor(j.M));
        this.n.setBackgroundResource(j.aZ);
        this.m.setBackgroundResource(j.ba);
        this.j.setBackgroundResource(j.p);
        this.af.setBackgroundResource(j.bo);
    }

    protected void onResume() {
        super.onResume();
        this.al.postDelayed(new Runnable(this) {
            final /* synthetic */ MyInfoActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (HomeGroup.w != null && HomeGroup.w.getVisibility() == 8) {
                    HomeGroup.w.a();
                }
            }
        }, 500);
        registerReceiver(this.ab, this.S);
        h();
        j();
        c.a().e();
        com.budejie.www.http.i.a((int) R.string.track_screen_me);
        this.s.smoothScrollTo(0, this.aj);
    }

    @SuppressLint({"NewApi"})
    protected void onPause() {
        super.onPause();
        if (VERSION.SDK_INT >= 14) {
            this.aj = this.s.getScrollY();
        }
    }

    private void h() {
        this.p = this.q.getString("id", "");
        this.ai = this.o.e(this.p);
        if (this.ai == null) {
            this.u.setText(R.string.person_no_bind);
            this.t.setPostAvatarImage("");
            this.B.setVisibility(8);
            this.A.setVisibility(8);
            this.C.setVisibility(8);
            this.ac.setVisibility(8);
            this.ad.setVisibility(8);
            this.ag.setVisibility(8);
            this.af.setVisibility(8);
            this.ah.setVisibility(8);
            this.J.b();
            c.b();
            this.K.b();
            this.L.b();
            this.M.b();
            this.N.b();
        } else if (TextUtils.isEmpty(this.ai.getName()) || TextUtils.isEmpty(this.ai.getProfile())) {
            ai.a(this.i, "", "");
        } else {
            this.u.setText(this.ai.getName());
            if (!ai.c(this.i) || TextUtils.isEmpty(this.ai.getName())) {
                this.u.setTextColor(this.i.getResources().getColor(R.color.comment_item_content_color));
            } else {
                this.u.setTextColor(this.i.getResources().getColor(j.H));
            }
            this.t.setPostAvatarImage(this.ai.getProfile());
            this.C.setVisibility(0);
            this.v.setText(this.ai.getFollowCount());
            this.x.setText(this.ai.getFansCount());
            this.Q.setText(this.ai.getTiezi_count());
            b.setText("我的");
            this.B.setVisibility(0);
            this.A.setVisibility(0);
            this.ac.setVisibility(8);
            this.ad.setVisibility(0);
            this.ag.setVisibility(0);
            this.ag.setBackgroundResource(j.bp);
            if (TextUtils.isEmpty(this.ai.getLevel())) {
                this.ag.setText("LV 1");
            } else {
                this.ag.setText("LV " + this.ai.getLevel());
            }
            if ((TextUtils.isEmpty(this.ai.getJie_v()) || "0".equalsIgnoreCase(this.ai.getJie_v())) && (TextUtils.isEmpty(this.ai.getSina_v()) || "0".equalsIgnoreCase(this.ai.getSina_v()))) {
                this.af.setVisibility(8);
            } else {
                this.af.setVisibility(0);
            }
            this.ah.setText(this.ai.getInstroduce());
            this.ah.setVisibility(8);
        }
    }

    public void follower$click(View view) {
        if (an.a(this.i)) {
            i();
            return;
        }
        this.F = an.a(this.i, getString(R.string.nonet), -1);
        this.F.show();
    }

    public void ttgLayout$Click(View view) {
        TtgInterface.openTabTtgMain(this, "ttg://home", 67620777);
    }

    public void recentReadBook$Click(View view) {
        com.b.a.c(this);
    }

    private void i() {
        MobclickAgent.onEvent(this.i, "E03-A01", "进入关注列表");
        u.b(this, this.p);
        this.K.b();
        c.b();
        HomeGroup.j = 0;
        this.i.sendBroadcast(new Intent("android.hide.sister.news.NOTIFYTIPS"));
    }

    public void fans$click(View view) {
        if (an.a(this.i)) {
            MobclickAgent.onEvent(this.i, "E03-A01", "粉丝");
            u.a(this, this.p);
            this.L.b();
            HomeGroup.p -= HomeGroup.i;
            HomeGroup.i = 0;
            this.i.sendBroadcast(new Intent("android.hide.sister.news.NOTIFYTIPS"));
            return;
        }
        this.F = an.a(this.i, getString(R.string.nonet), -1);
        this.F.show();
    }

    public void newsFeed$click(View view) {
        if (an.a(this.q)) {
            c.b();
            this.K.b();
            HomeGroup.p -= HomeGroup.j;
            HomeGroup.k = "";
            this.V.setVisibility(4);
            Intent intent = new Intent(this, NewsFeedActivity.class);
            if (HomeGroup.j > 0) {
                intent.putExtra("isLoadCache", false);
                HomeGroup.j = 0;
            }
            startActivity(intent);
            if (!this.J.isShown() && !this.L.isShown() && !this.M.isShown()) {
                this.i.sendBroadcast(new Intent("android.hide.sister.news.NOTIFYTIPS"));
                return;
            }
            return;
        }
        this.F = an.a(this.i, getString(R.string.bindUserFirst), -1);
        this.F.show();
    }

    public void tougaoLayout$Click(View view) {
        this.ai = this.o.e(this.p);
        av.a().a(this.i, this.ai);
    }

    public void myTougaoLayout$Click(View view) {
        if (an.a((Context) this)) {
            if (an.a(this.q)) {
                startActivity(new Intent(this, MyPostsActivity.class));
            } else {
                an.a(this.i, 1, StatisticCodeTable.MORE, "mytougao", 124);
            }
            if (!this.q.getBoolean("mytougaoUpdate", false)) {
                this.q.edit().putBoolean("mytougaoUpdate", true).commit();
            }
            MobclickAgent.onEvent(this, "mytougao");
            return;
        }
        this.F = an.a((Activity) this, getString(R.string.nonet), -1);
        this.F.show();
    }

    public void myCommend$Click(View view) {
        if (an.a((Context) this)) {
            if (an.a(this.q)) {
                startActivity(new Intent(this, MyCommentActivity.class));
            } else {
                an.a(this.i, 1, StatisticCodeTable.MORE, "mycomment", 129);
            }
            MobclickAgent.onEvent(this, "我的评论");
            return;
        }
        this.F = an.a((Activity) this, getString(R.string.nonet), -1);
        this.F.show();
    }

    public void myNewsBtn$Click(View view) {
        if (!an.a((Context) this)) {
            this.F = an.a((Activity) this, getString(R.string.nonet), -1);
            this.F.show();
        } else if (an.a(this.q)) {
            MobclickAgent.onEvent(this.i, "E03-A01", "进入消息通知");
            this.J.b();
            if (HomeGroup.i <= HomeGroup.p) {
                HomeGroup.p -= HomeGroup.h;
            }
            ai.a(this.i, ai.b(this.i), 0);
            this.i.sendBroadcast(new Intent("android.hide.sister.news.NOTIFYTIPS"));
            startActivity(new Intent(this, MessageCenterActivity.class));
        } else {
            an.a(this.i, 1, StatisticCodeTable.MORE, "mynews", 130);
        }
    }

    public void shenheLayout$Click(View view) {
        if (an.a((Context) this)) {
            if (an.a(this.q)) {
                startActivity(new Intent(this, AuditPostsActivity.class));
            } else {
                an.a(this.i, 1, StatisticCodeTable.MORE, "shenhe", 125);
            }
            if (!this.q.getBoolean("shenheUpdate", false)) {
                this.q.edit().putBoolean("shenheUpdate", true).commit();
            }
            sendBroadcast(new Intent("android.hide.sister.my.NOTIFYTIPS"));
            MobclickAgent.onEvent(this, "sheheTiezi");
            return;
        }
        this.F = an.a((Activity) this, getString(R.string.nonet), -1);
        this.F.show();
    }

    public void personLayout$Click(View view) {
        if (!an.a(this.i)) {
            this.F = an.a(this.i, getString(R.string.nonet), -1);
            this.F.show();
        } else if (an.a(this.q)) {
            MobclickAgent.onEvent(this.i, "E03-A01", "进入个人主页");
            aa.a("MyInfoActivity", "进入个人Profile");
            Intent intent = new Intent();
            intent.putExtra(PersonalProfileActivity.c, ai.b(this.i));
            intent.setClass(this, PersonalProfileActivity.class);
            startActivity(intent);
        } else {
            an.a(this.i, 1, StatisticCodeTable.MORE, "person", 126);
        }
    }

    public void collectLayout$Click(View view) {
        MobclickAgent.onEvent(this.i, "E03-A01", "进入我的帖子");
        startActivity(new Intent(this, MyPostsActivity.class));
    }

    public void recommendFriend$Click(View view) {
        if (an.a(this.i)) {
            Intent intent = new Intent(this, SuggestedFollowsActivity.class);
            this.M.b();
            HomeGroup.p -= HomeGroup.l;
            startActivity(intent);
            if (this.q.getBoolean("shenheUpdate", false)) {
                sendBroadcast(new Intent("android.hide.sister.my.NOTIFYTIPS"));
                return;
            }
            return;
        }
        this.F = an.a(this.i, getString(R.string.nonet), -1);
        this.F.show();
    }

    public void offline_read$Click(View view) {
        MobclickAgent.onEvent((Context) this, getString(R.string.offline_umeng_event_download_key), getString(R.string.offline_umeng_event_download_myinfo));
    }

    public void my_video$Click(View view) {
        Intent intent = new Intent(this, DownloadListActivity.class);
        intent.putExtra("isDownloaded", true);
        startActivity(intent);
    }

    public void more_activity$Click(View view) {
        this.i.startActivity(new Intent(this.i, ActivitiesTopicActivity.class));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == 123) {
            if (intent.getBooleanExtra("success", false)) {
                startActivity(new Intent(this, TougaoActivity.class));
            }
        } else if (i2 == 124) {
            if (intent.getBooleanExtra("success", false)) {
                startActivity(new Intent(this, MyPostsActivity.class));
            }
        } else if (i2 == 125) {
            if (intent.getBooleanExtra("success", false)) {
                startActivity(new Intent(this, AuditPostsActivity.class));
            }
        } else if (i2 == 126) {
            if (intent.getBooleanExtra("success", false)) {
                h();
            }
        } else if (i2 == 129) {
            if (intent.getBooleanExtra("success", false)) {
                startActivity(new Intent(this, MyCommentActivity.class));
            }
        } else if (i == 716 && i2 == -1) {
            p.a(this.i, p.a, 640, 640);
        } else if (i == 728 && i2 == -1) {
            if (intent != null) {
                String str = this.O;
                this.O = "";
                p.a(intent, this.i, RecordActivity.class, str);
            }
        } else if (i == 714 && i2 == -1) {
            if (intent != null) {
                p.a(this.i, intent, 640, 640);
            }
        } else if (i == R$styleable.Theme_Custom_myinfo_night_model_bg && i2 == -1) {
            if (intent != null) {
                Uri data = intent.getData();
                if (data.toString().endsWith(".mp4")) {
                    Intent intent2 = new Intent();
                    intent2.setAction("android.intent.action.VIEW");
                    intent2.setDataAndType(data, "video/*");
                    startActivity(intent2);
                }
            }
        } else if (i2 == -1) {
            aa.a(AppLinkConstants.REQUESTCODE, i + "");
            if (i == 32973 && this.mSsoHandler != null) {
                this.mSsoHandler.a(i, i2, intent);
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.ab);
        } catch (Exception e) {
        }
    }

    private void j() {
        this.p = this.q.getString("id", "");
        if (!TextUtils.isEmpty(this.p)) {
            if (an.a((Context) this)) {
                BudejieApplication.a.a(RequstMethod.GET, com.budejie.www.http.j.e(), b(this.p), this.an);
            } else {
                an.a((Activity) this, getString(R.string.nonet), -1).show();
            }
        }
    }

    private net.tsz.afinal.a.b b(String str) {
        com.budejie.www.http.j jVar = new com.budejie.www.http.j();
        return com.budejie.www.http.j.s(this.i, str);
    }

    private void k() {
        if (an.a((Context) this)) {
            h hVar = new h("http://s.budejie.com/op/square2");
            hVar.b().c().c("0", GiftConfigUtil.SUPER_GIRL_GIFT_TAG).a();
            BudejieApplication.a.a(RequstMethod.GET, hVar.toString(), new com.budejie.www.http.j(this), this.g);
            return;
        }
        an.a((Activity) this, getString(R.string.nonet), -1).show();
    }
}
