package com.budejie.www.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alipay.sdk.util.j;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.noticesetting.NotificationSettingsActivity;
import com.budejie.www.activity.view.BadgeView;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.UserItem;
import com.budejie.www.busevent.LoginAction;
import com.budejie.www.c.d;
import com.budejie.www.c.f;
import com.budejie.www.c.g;
import com.budejie.www.c.l;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.n;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.p;
import com.budejie.www.util.z;
import com.elves.update.DownloadServer;
import com.elves.update.c;
import com.ixintui.pushsdk.PushSdkApi;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.CookieManager;
import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.model.Conversation.SyncListener;
import com.umeng.fb.model.DevReply;
import com.umeng.fb.model.Reply;
import com.umeng.onlineconfig.OnlineConfigAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import de.greenrobot.event.EventBus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.client.CookieStore;
import org.json.JSONException;
import org.json.JSONObject;

public class MoreActivity extends QiHooActivity implements OnTouchListener, OnCheckedChangeListener, com.budejie.www.f.a, c {
    private UserItem A;
    private ImageView B;
    private ImageView C;
    private ImageView D;
    private ImageView E;
    private ImageView F;
    private ImageView G;
    private RadioButton H;
    private RadioButton I;
    private RadioButton J;
    private RadioButton K;
    private RadioButton L;
    private RelativeLayout M;
    private RelativeLayout N;
    private TextView O;
    private boolean P = false;
    private String Q;
    private Dialog R = null;
    private View S;
    private View T;
    private TextView U;
    private TextView V;
    private View W;
    private View X;
    private BadgeView Y;
    private RelativeLayout Z;
    String a = "MoreActivity";
    private d aa;
    private com.budejie.www.c.b ab;
    private g ac;
    private TextView ad;
    private com.budejie.www.c.b ae;
    private f af;
    private l ag;
    private int ah = 0;
    private Handler ai = new Handler(this) {
        final /* synthetic */ MoreActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = 0;
            int i2 = message.what;
            String str;
            Map c;
            String str2;
            if (i2 == 923) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.k = an.a(this.a.d, this.a.d.getString(R.string.bind_failed), -1);
                    this.a.k.show();
                    MobclickAgent.onEvent(this.a.d, "E03-A10", "绑定腾讯微博失败");
                } else {
                    try {
                        i = Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                    }
                    if (i < 0) {
                        this.a.k = an.a(this.a.d, this.a.d.getString(R.string.bind_failed), -1);
                        this.a.k.show();
                        MobclickAgent.onEvent(this.a.d, "E03-A10", "绑定腾讯微博失败");
                    } else {
                        c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            MobclickAgent.onEvent(this.a.d, "E03-A10", "绑定腾讯微博失败");
                        } else {
                            str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(j.c))) {
                                MobclickAgent.onEvent(this.a.d, "E03-A10", "绑定腾讯微博成功");
                                this.a.r = (String) c.get("id");
                                this.a.q.a(this.a.r, c);
                                ai.a(this.a.d, this.a.r, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.a();
                                this.a.a(true);
                                this.a.f.setText(R.string.more);
                                this.a.k = an.a(this.a.d, this.a.d.getString(R.string.bind_successed), -1);
                                this.a.k.show();
                            } else {
                                an.a(this.a.d, str2, -1).show();
                            }
                        }
                    }
                }
                this.a.ai.sendEmptyMessage(925);
            } else if (i2 == 924) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.k = an.a(this.a.d, this.a.d.getString(R.string.bind_failed), -1);
                    this.a.k.show();
                    MobclickAgent.onEvent(this.a.d, "E03-A10", "绑定新浪微博失败");
                } else {
                    try {
                        i = Integer.parseInt(str);
                    } catch (NumberFormatException e2) {
                    }
                    if (i < 0) {
                        this.a.k = an.a(this.a.d, this.a.d.getString(R.string.bind_failed), -1);
                        this.a.k.show();
                        MobclickAgent.onEvent(this.a.d, "E03-A10", "绑定新浪微博失败");
                    } else {
                        c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            MobclickAgent.onEvent(this.a.d, "E03-A10", "绑定新浪微博失败");
                        } else {
                            str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(j.c))) {
                                MobclickAgent.onEvent(this.a.d, "E03-A10", "绑定新浪微博成功");
                                this.a.r = (String) c.get("id");
                                this.a.q.a(this.a.r, c);
                                ai.a(this.a.d, this.a.r, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                if (OauthWeiboBaseAct.mAccessToken != null) {
                                    this.a.q.a(this.a.r, OauthWeiboBaseAct.mAccessToken.e());
                                }
                                this.a.a();
                                this.a.a(true);
                                this.a.f.setText(R.string.more);
                                this.a.k = an.a(this.a.d, this.a.d.getString(R.string.bind_successed), -1);
                                this.a.k.show();
                            } else {
                                an.a(this.a.d, str2, -1).show();
                            }
                        }
                    }
                }
                this.a.ai.sendEmptyMessage(925);
            } else if (i2 == 929) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.k = an.a(this.a.d, this.a.d.getString(R.string.bind_failed), -1);
                    this.a.k.show();
                    MobclickAgent.onEvent(this.a.d, "E03-A10", "绑定Qzone失败");
                } else {
                    try {
                        i = Integer.parseInt(str);
                    } catch (NumberFormatException e3) {
                    }
                    if (i < 0) {
                        this.a.k = an.a(this.a.d, this.a.d.getString(R.string.bind_failed), -1);
                        this.a.k.show();
                        MobclickAgent.onEvent(this.a.d, "E03-A10", "绑定Qzone失败");
                    } else {
                        c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            this.a.k = an.a(this.a.d, this.a.d.getString(R.string.bind_failed), -1);
                            this.a.k.show();
                            MobclickAgent.onEvent(this.a.d, "E03-A10", "绑定Qzone失败");
                        } else {
                            str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(j.c))) {
                                MobclickAgent.onEvent(this.a.d, "E03-A10", "绑定Qzone成功");
                                this.a.r = (String) c.get("id");
                                this.a.q.a(this.a.r, c);
                                ai.a(this.a.d, this.a.r, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.a();
                                this.a.a(true);
                                this.a.f.setText(R.string.more);
                                this.a.k = an.a(this.a.d, this.a.d.getString(R.string.bind_successed), -1);
                                this.a.k.show();
                            } else {
                                an.a(this.a.d, str2, -1).show();
                            }
                        }
                    }
                }
                this.a.ai.sendEmptyMessage(925);
            } else if (i2 == 926) {
                this.a.t = i;
                if (this.a.n != null) {
                    this.a.n.cancel();
                }
                try {
                    this.a.p.a(this.a.d, (String) message.obj, (Handler) this);
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            } else if (i2 == 925) {
                if (!this.a.d.isFinishing()) {
                    if (this.a.l.isShowing()) {
                        this.a.l.cancel();
                    } else {
                        this.a.l.show();
                    }
                }
            } else if (i2 == 922) {
                an.a(this.a.d);
            } else if (i2 == 927) {
                this.a.k = an.a(this.a.d, this.a.d.getString(R.string.clear_cache_successed), -1);
                this.a.k.show();
            } else if (i2 == SocketUtil.TYPEID_811) {
                this.a.d.finish();
                System.exit(i);
            } else if (i2 == 980) {
                str = (String) message.obj;
                String str3 = Environment.getExternalStorageDirectory().getPath() + "/elves/apk";
                str2 = "";
                if (!TextUtils.isEmpty(str)) {
                    str2 = str.substring(str.lastIndexOf("/") + 1);
                }
                if (!(TextUtils.isEmpty(str2) || str2.contains(".apk"))) {
                    str2 = str2 + ".apk";
                }
                Intent intent = new Intent(this.a.d, DownloadServer.class);
                intent.putExtra("apkPath", str3);
                intent.putExtra("url", str);
                intent.putExtra("apkName", str2);
                intent.putExtra("type", "360");
                this.a.startService(intent);
            }
        }
    };
    private UmengUpdateListener aj = new UmengUpdateListener(this) {
        final /* synthetic */ MoreActivity a;

        {
            this.a = r1;
        }

        public void onUpdateReturned(int i, UpdateResponse updateResponse) {
            this.a.n.dismiss();
            switch (i) {
                case 0:
                    UmengUpdateAgent.showUpdateDialog(this.a.d, updateResponse);
                    return;
                case 1:
                    Toast.makeText(this.a.d, "没有更新", 0).show();
                    return;
                case 2:
                    Toast.makeText(this.a.d, "没有wifi连接， 只在wifi下更新", 0).show();
                    return;
                case 3:
                    Toast.makeText(this.a.d, "超时", 0).show();
                    return;
                default:
                    return;
            }
        }
    };
    com.budejie.www.http.a b;
    private MoreActivity d;
    private Button e;
    private TextView f;
    private RelativeLayout g;
    private RelativeLayout h;
    private TextView i;
    private ImageView j;
    private Toast k;
    private Dialog l;
    private ScrollView m;
    private ProgressDialog n;
    private n o;
    private p p;
    private m q;
    private String r;
    private boolean s = false;
    private boolean t = false;
    private SharedPreferences u;
    private com.budejie.www.g.b v;
    private IWXAPI w;
    private HashMap<String, String> x;
    private com.elves.update.a y;
    private ListItemObject z;

    private class a implements OnClickListener {
        final /* synthetic */ MoreActivity a;

        private a(MoreActivity moreActivity) {
            this.a = moreActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    private class b extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ MoreActivity a;

        private b(MoreActivity moreActivity) {
            this.a = moreActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Void) obj);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.a.R.show();
        }

        protected Void a(Void... voidArr) {
            AsyncImageView.clearCache();
            an.a(this.a.d.getCacheDir());
            new com.budejie.www.c.b(this.a.d).a(null, null);
            an.a.clear();
            return null;
        }

        protected void onCancelled() {
            super.onCancelled();
            if (this.a.R.isShowing()) {
                this.a.R.cancel();
            }
        }

        protected void a(Void voidR) {
            if (this.a.R.isShowing()) {
                this.a.R.cancel();
            }
            an.a(this.a.d, this.a.getString(R.string.clear_cache_successed), -1).show();
            this.a.O.setText("");
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.more);
        this.d = this;
        this.ae = new com.budejie.www.c.b(this.d);
        this.af = new f(this.d);
        this.ag = new l(this.d);
        this.aa = new d(this);
        this.ac = new g(this);
        this.ab = new com.budejie.www.c.b(this.d);
        this.b = new com.budejie.www.http.a(this, this);
        c();
        o();
        p();
        if (an.a((Context) this)) {
            k();
        }
    }

    private void c() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.u = getSharedPreferences("weiboprefer", 0);
        this.e = (Button) findViewById(R.id.title_left_btn);
        this.e.setVisibility(0);
        this.e.setOnClickListener(new a());
        this.g = (RelativeLayout) findViewById(R.id.collectSettingLayout);
        this.f = (TextView) findViewById(R.id.title_center_txt);
        this.h = (RelativeLayout) findViewById(R.id.recom_friend_Layout);
        this.m = (ScrollView) findViewById(R.id.m_scroll);
        this.i = (TextView) findViewById(R.id.update);
        this.j = (ImageView) findViewById(R.id.repost_on_collect_iv);
        this.B = (ImageView) findViewById(R.id.save_position_switch);
        com.budejie.www.util.c.a(this.B);
        this.C = (ImageView) findViewById(R.id.acceptPush_iv);
        com.budejie.www.util.c.a(this.C);
        this.F = (ImageView) findViewById(R.id.top_navigation_auto_hide_iv);
        com.budejie.www.util.c.a(this.F);
        this.G = (ImageView) findViewById(R.id.bottom_navigation_auto_hide_iv);
        com.budejie.www.util.c.a(this.G);
        this.D = (ImageView) findViewById(R.id.noprofile_in_nonwifi_switch);
        this.E = (ImageView) findViewById(R.id.more_topspeed_swich);
        this.N = (RelativeLayout) findViewById(R.id.more_apply_layout);
        this.M = (RelativeLayout) findViewById(R.id.more_topspeed_setting);
        this.O = (TextView) findViewById(R.id.more_cache_size);
        this.S = findViewById(R.id.more_item_divider);
        this.T = findViewById(R.id.more_item_divider_apply);
        this.U = (TextView) findViewById(R.id.actionsetting_tv);
        this.V = (TextView) findViewById(R.id.actionother_tv);
        this.H = (RadioButton) findViewById(R.id.more_setting_font_small);
        this.I = (RadioButton) findViewById(R.id.more_setting_font_medium);
        this.J = (RadioButton) findViewById(R.id.more_setting_font_large);
        this.H.setOnCheckedChangeListener(this);
        this.I.setOnCheckedChangeListener(this);
        this.J.setOnCheckedChangeListener(this);
        this.W = findViewById(R.id.divider_h_view4);
        this.X = findViewById(R.id.divider_h_view11);
        this.K = (RadioButton) findViewById(R.id.more_setting_theme_light_colour);
        this.L = (RadioButton) findViewById(R.id.more_setting_theme_deep_colour);
        this.K.setOnCheckedChangeListener(this);
        this.L.setOnCheckedChangeListener(this);
        findViewById(R.id.installLayout).setVisibility(8);
        this.X.setVisibility(8);
        this.f.setText(R.string.more_settings_tips);
        String string = getString(R.string.current_version);
        String o = an.o(this.d);
        if ("".equals(o)) {
            this.i.setText(String.format(string, new Object[]{"6.9.2"}));
        } else {
            this.i.setText(String.format(string, new Object[]{"6.9.2"}) + "   (" + o + ")");
        }
        this.l = new Dialog(this, R.style.dialogTheme);
        this.l.setContentView(R.layout.loaddialog);
        this.R = new Dialog(this, R.style.dialogTheme);
        this.R.setContentView(R.layout.loaddialog_wc);
        ((TextView) this.R.findViewById(R.id.dialog_txt)).setText("正在清除……");
        this.m.setOnTouchListener(this);
        this.o = new n(this);
        this.p = new p();
        this.q = new m(this);
        this.y = new com.elves.update.a(this.d);
        if (this.mSsoHandler == null) {
            this.mSsoHandler = new com.sina.weibo.sdk.auth.a.a(this.d);
        }
        this.v = new com.budejie.www.g.b(this.d, this.mSsoHandler, this.mTencent, this);
        this.r = this.u.getString("id", "");
        this.x = this.o.a(this.r);
        this.Z = (RelativeLayout) findViewById(R.id.feedBackLayout);
        this.Y = an.a(this, this.Z, false, 30, 0, 7, 0.0f);
        this.ad = (TextView) findViewById(R.id.account_logout_btn);
        if (an.a(this.u)) {
            this.ad.setVisibility(0);
        }
    }

    private void d() {
        try {
            new AsyncTask<Void, Void, String>(this) {
                final /* synthetic */ MoreActivity a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((Void[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((String) obj);
                }

                protected void a(String str) {
                    super.onPostExecute(str);
                    if (str != null) {
                        this.a.O.setText("(已使用" + str + ")");
                    }
                }

                protected String a(Void... voidArr) {
                    try {
                        return an.b(an.b(HomeGroup.f));
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }.execute(new Void[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onrefreshTheme() {
        if (this.P) {
            this.N.setVisibility(0);
            this.T.setVisibility(0);
        } else {
            this.N.setVisibility(8);
            this.T.setVisibility(8);
        }
        this.f.setTextColor(getResources().getColor(com.budejie.www.util.j.b));
        onRefreshTitleFontTheme(this.e, true);
    }

    private void e() {
        this.r = this.u.getString("id", "");
        this.A = this.q.e(this.r);
        if (this.A == null) {
            return;
        }
        if (TextUtils.isEmpty(this.A.getName()) || TextUtils.isEmpty(this.A.getProfile())) {
            ai.a(this.d, "", "");
        }
    }

    private void a(boolean z) {
        if (z) {
            this.g.setVisibility(0);
            this.W.setVisibility(0);
        } else {
            this.g.setVisibility(8);
            this.W.setVisibility(8);
        }
        this.g.setEnabled(z);
        this.j.setEnabled(z);
        if (an.j(this.d)) {
            this.j.setImageResource(R.drawable.switch_on);
        } else {
            this.j.setImageResource(R.drawable.switch_off);
        }
    }

    public void noticeLayouts$Click(View view) {
        if (an.a((Context) this)) {
            if (an.a(this.u)) {
                startActivity(new Intent(this, NotificationSettingsActivity.class));
            } else {
                an.a(this.d, 1, StatisticCodeTable.MORE, "mycollection", 139);
            }
            MobclickAgent.onEvent((Context) this, "E05-A09", "通知设置");
            return;
        }
        this.k = an.a((Activity) this, getString(R.string.nonet), -1);
        this.k.show();
    }

    public void more_apply_recommend$Click(View view) {
        Intent intent = new Intent(this.d, DetailContentActivity.class);
        intent.putExtra("operator", "apply");
        intent.putExtra("url", this.Q);
        this.d.startActivity(intent);
    }

    public void savePosition$Click(View view) {
        if (getBudejieSettings().a.a().booleanValue()) {
            this.B.setImageResource(R.drawable.switch_off);
            getBudejieSettings().a.a(Boolean.valueOf(false));
            return;
        }
        this.B.setImageResource(R.drawable.switch_on);
        getBudejieSettings().a.a(Boolean.valueOf(true));
    }

    public void acceptPush$Click(View view) {
        if (this.ah == 0) {
            PushSdkApi.suspendPush(this.d);
            this.C.setImageResource(R.drawable.switch_off);
            this.ah = 1;
        } else {
            PushSdkApi.resumePush(this.d);
            this.C.setImageResource(R.drawable.switch_on);
            this.ah = 0;
        }
        PushSdkApi.isSuspended(this.d);
    }

    public void noprofileInNonwifi$Click(View view) {
        if (this.u.getBoolean("noprofileInNonwifi", true)) {
            this.D.setImageResource(R.drawable.switch_off);
            this.u.edit().putBoolean("noprofileInNonwifi", false).commit();
            return;
        }
        this.D.setImageResource(R.drawable.switch_on);
        this.u.edit().putBoolean("noprofileInNonwifi", true).commit();
    }

    public void themeSwitch$Click(View view) {
        changeCurrentTheme();
    }

    public void a() {
        this.r = this.u.getString("id", "");
        this.x = this.o.a(this.r);
        if (this.z == null) {
            this.z = new ListItemObject();
        }
        String configParams = OnlineConfigAgent.getInstance().getConfigParams(this.d, "推荐朋友-SMS-内容");
        if (TextUtils.isEmpty(configParams)) {
            configParams = "发现一个好玩的应用，百思不得姐，瞬间戳中你的笑点，试试看吧。";
        }
        this.z.setContent(configParams);
        this.z.setForwardNoCollect(true);
        this.h.setTag(this.z);
        Bundle bundle = new Bundle();
        bundle.putString(HistoryOpenHelper.COLUMN_UID, this.r);
        bundle.putSerializable("weiboMap", this.x);
        this.h.setOnClickListener(this.v.a(5, bundle, new Handler(), this.w, this.q, this.o, this.y, this.u, this.ai));
    }

    private void f() {
        if (getBudejieSettings().a.a().booleanValue()) {
            this.B.setImageResource(R.drawable.switch_on);
        } else {
            this.B.setImageResource(R.drawable.switch_off);
        }
    }

    private void g() {
        this.ah = ai.v(this);
        if (this.ah == 0) {
            this.C.setImageResource(R.drawable.switch_on);
        } else {
            this.C.setImageResource(R.drawable.switch_off);
        }
    }

    private void h() {
        if (getBudejieSettings().e.a().booleanValue()) {
            CharSequence configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "顶部导航是否自动隐藏");
            if (TextUtils.isEmpty(configParams) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equals(configParams)) {
                this.F.setImageResource(R.drawable.switch_off);
                getBudejieSettings().d.a(Boolean.valueOf(false));
                return;
            }
            this.F.setImageResource(R.drawable.switch_on);
            getBudejieSettings().d.a(Boolean.valueOf(true));
        } else if (getBudejieSettings().d.a().booleanValue()) {
            this.F.setImageResource(R.drawable.switch_on);
        } else {
            this.F.setImageResource(R.drawable.switch_off);
        }
    }

    private void i() {
        if (getBudejieSettings().g.a().booleanValue()) {
            if (Constants.SERVICE_SCOPE_FLAG_VALUE.equals(OnlineConfigAgent.getInstance().getConfigParams(this, "底部导航是否自动隐藏"))) {
                this.G.setImageResource(R.drawable.switch_on);
                getBudejieSettings().f.a(Boolean.valueOf(true));
                HomeGroup.a(this.d, 0);
                return;
            }
            this.G.setImageResource(R.drawable.switch_off);
            getBudejieSettings().f.a(Boolean.valueOf(false));
            HomeGroup.a(this.d, getResources().getDimensionPixelOffset(R.dimen.navigation_height));
        } else if (getBudejieSettings().f.a().booleanValue()) {
            this.G.setImageResource(R.drawable.switch_on);
        } else {
            this.G.setImageResource(R.drawable.switch_off);
        }
    }

    public void topNavigation$Click(View view) {
        getBudejieSettings().e.a(Boolean.valueOf(false));
        if (getBudejieSettings().d.a().booleanValue()) {
            this.F.setImageResource(R.drawable.switch_off);
            getBudejieSettings().d.a(Boolean.valueOf(false));
            return;
        }
        this.F.setImageResource(R.drawable.switch_on);
        getBudejieSettings().d.a(Boolean.valueOf(true));
    }

    public void bottomNavigation$Click(View view) {
        getBudejieSettings().g.a(Boolean.valueOf(false));
        if (getBudejieSettings().f.a().booleanValue()) {
            this.G.setImageResource(R.drawable.switch_off);
            getBudejieSettings().f.a(Boolean.valueOf(false));
            HomeGroup.a(this.d, getResources().getDimensionPixelOffset(R.dimen.navigation_height));
            return;
        }
        this.G.setImageResource(R.drawable.switch_on);
        getBudejieSettings().f.a(Boolean.valueOf(true));
        HomeGroup.a(this.d, 0);
    }

    private void j() {
        if (this.u.getBoolean("noprofileInNonwifi", true)) {
            this.D.setImageResource(R.drawable.switch_on);
        } else {
            this.D.setImageResource(R.drawable.switch_off);
        }
    }

    private void k() {
        this.w = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.w.registerApp("wx592fdc48acfbe290");
    }

    public void clearLayout$Click(View view) {
        MobclickAgent.onEvent(this.d, "E05-A04", "清除缓存");
        Builder builder = new Builder(this);
        builder.setTitle(R.string.system_tip);
        builder.setMessage(R.string.clear_cache_message);
        builder.setPositiveButton(R.string.update_btn_sure, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ MoreActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.l();
            }
        });
        builder.setNegativeButton(R.string.update_btn_cancel, null);
        if (!isFinishing()) {
            builder.show();
        }
    }

    private void l() {
        aa.c(this.a, "deleteCache");
        new b().execute(new Void[0]);
    }

    public void help$Click(View view) {
        String string = getString(R.string.help_page_url);
        Intent intent = new Intent(this.d, DetailContentActivity.class);
        intent.putExtra("operator", "help");
        intent.putExtra("url", string);
        this.d.startActivity(intent);
    }

    public void updateLayout$Click(View view) {
        if (an.a((Context) this)) {
            MobclickAgent.onEvent((Context) this, "E05-A03", "检查更新");
            b();
            return;
        }
        this.k = an.a((Activity) this, getString(R.string.nonet), -1);
        this.k.show();
    }

    public void b() {
        if (an.a((Context) this)) {
            an.a(this.d, this.d);
        }
    }

    public void aboutLayout$Click(View view) {
        Intent intent = new Intent(this, DetailContentActivity.class);
        intent.putExtra("operator", "about");
        intent.putExtra("url", "http://www.budejie.com/budejie/aboutus.html?uid=" + this.r);
        startActivity(intent);
    }

    public synchronized void installLayout$Click(View view) {
        n();
    }

    private void m() {
        if (com.elves.update.d.a()) {
            String string = getString(R.string.yijianzhuang_apk_url);
            String str = Environment.getExternalStorageDirectory().getPath() + "/elves/apk";
            String str2 = "";
            if (!TextUtils.isEmpty(string)) {
                str2 = string.substring(string.lastIndexOf("/") + 1);
            }
            if (!(TextUtils.isEmpty(str2) || str2.contains(".apk"))) {
                str2 = str2 + ".apk";
            }
            Intent intent = new Intent(this, DownloadServer.class);
            intent.putExtra("apkPath", str);
            intent.putExtra("url", string);
            intent.putExtra("apkName", str2);
            startService(intent);
            return;
        }
        this.k = an.a((Activity) this, getString(R.string.sd_message), -1);
        this.k.show();
    }

    private void n() {
        Builder builder = new Builder(this);
        builder.setTitle(R.string.system_tip);
        builder.setMessage(getString(R.string.yijianzhuang) + ",是否下载？");
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ MoreActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.k = an.a(this.a.d, "开始下载", -1);
                this.a.k.show();
                this.a.m();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ MoreActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.k = an.a(this.a.d, "取消下载", -1);
                this.a.k.show();
            }
        });
        builder.show();
    }

    protected void onPause() {
        super.onPause();
        if (this.k != null) {
            this.k.cancel();
        }
    }

    protected void onResume() {
        super.onResume();
        e();
        a();
        f();
        h();
        i();
        g();
        j();
        d();
        q();
        if (this.u.getBoolean("hasNewReply", false)) {
            this.Y.a(an.A(this.d), an.A(this.d));
        } else {
            this.Y.b();
        }
        this.v = new com.budejie.www.g.b(this.d, this.mSsoHandler, this.mTencent, this);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        an.b((Activity) this);
        return false;
    }

    public void a(String str) {
        this.ai.sendMessage(this.ai.obtainMessage(926, str));
    }

    public void a(int i, String str) {
        if (i == 1111121) {
            this.ai.sendMessage(this.ai.obtainMessage(915, str));
        }
    }

    public void a(int i) {
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (!z) {
            return;
        }
        if (compoundButton == this.H) {
            an.k(this.d, "small");
        } else if (compoundButton == this.I) {
            an.k(this.d, "medium");
        } else if (compoundButton == this.J) {
            an.k(this.d, "large");
        } else if (compoundButton == this.L) {
            an.j(this.d, "deep_colour");
            changeThemeStyle();
        } else if (compoundButton == this.K) {
            an.j(this.d, "light_colour");
            changeThemeStyle();
        }
    }

    private void o() {
        String r = an.r(this.d);
        if (r.equals("small")) {
            this.H.performClick();
        } else if (r.equals("medium")) {
            this.I.performClick();
        } else if (r.equals("large")) {
            this.J.performClick();
        } else {
            aa.d(this.a, "checkFontSizeSettingStates, error");
            this.I.performClick();
        }
    }

    private void p() {
        String p = an.p(this.d);
        if (p.equals("deep_colour")) {
            this.L.performClick();
        } else if (p.equals("light_colour")) {
            this.K.performClick();
        }
    }

    public void feedBackLayout$Click(View view) {
        if (this.u.getBoolean("hasNewReply", false)) {
            this.u.edit().putBoolean("hasNewReply", false).commit();
        }
        startActivity(new Intent(this, FeedBackActivity.class));
    }

    private void q() {
        new FeedbackAgent(this).getDefaultConversation().sync(new SyncListener(this) {
            final /* synthetic */ MoreActivity a;

            {
                this.a = r1;
            }

            public void onSendUserReply(List<Reply> list) {
                aa.c(this.a.a, "onSendUserReply");
            }

            public void onReceiveDevReply(List<DevReply> list) {
                aa.c(this.a.a, "onReceiveDevReply");
                if (list != null && list.size() != 0 && list.get(0) != null && !this.a.u.getBoolean("hasNewReply", false)) {
                    this.a.u.edit().putBoolean("hasNewReply", true).commit();
                    this.a.Y.a(an.A(this.a.d), an.A(this.a.d));
                }
            }
        });
    }

    public void btnExit$Click(View view) {
        if (an.a((Context) this)) {
            r();
            this.ab.d("0");
            this.ab.d("1");
            this.ac.a();
            this.aa.b();
            this.af.b();
            this.ag.b();
            ai.a(this.d, "");
            return;
        }
        Toast.makeText(this.d, getResources().getString(R.string.nonet), 0).show();
    }

    private void r() {
        this.b.a(AlibcConstants.PF_ANDROID, new net.tsz.afinal.a.a<String>(this) {
            final /* synthetic */ MoreActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void onStart() {
                super.onStart();
                if (!this.a.d.isFinishing() && this.a.l != null) {
                    this.a.l.show();
                }
            }

            public void a(String str) {
                if (!(this.a.d.isFinishing() || this.a.l == null || !this.a.l.isShowing())) {
                    this.a.l.cancel();
                }
                aa.a("wuzhenlin", str);
                if (TextUtils.isEmpty(str)) {
                    this.a.k = an.a(this.a.d, this.a.getString(R.string.exit_failure), -1);
                    this.a.k.show();
                    return;
                }
                Object obj = "";
                String str2 = "";
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.has(j.c)) {
                        obj = jSONObject.getString(j.c);
                    }
                    if (jSONObject.has("result_desc")) {
                        jSONObject.getString("result_desc");
                    }
                    if (!"0".equals(obj) && !"-2".equals(obj)) {
                        return;
                    }
                    if (this.a.aa.d()) {
                        this.a.t();
                    } else {
                        this.a.s();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    this.a.k = an.a(this.a.d, this.a.getString(R.string.exit_failure), -1);
                    this.a.k.show();
                }
            }

            public void onFailure(Throwable th, int i, String str) {
                if (this.a.l != null && this.a.l.isShowing()) {
                    this.a.l.cancel();
                }
                this.a.k = an.a(this.a.d, this.a.getString(R.string.exit_failure), -1);
                this.a.k.show();
            }
        });
    }

    private void s() {
        ai.a(this.d, "", "");
        if (this.mTencent != null) {
            this.mTencent.logout(this.d);
        }
        CookieStore a = NetWorkUtil.a(this.d);
        if (a != null) {
            a.clear();
        }
        BudejieApplication.a.a("cookie", "");
        CookieManager.getInstance().removeAllCookie();
        this.u.edit().putString("sex", "").commit();
        this.u.edit().putString("collect_version", "").commit();
        finish();
        this.d.sendBroadcast(new Intent("android.hide.sister.news.NOTIFYTIPS"));
        this.d.sendBroadcast(new Intent("android.hide.sister.my.NOTIFYTIPS"));
        this.d.sendBroadcast(new Intent("android.hide.sister.friend.NOTIFYTIPS"));
        sendBroadcast(new Intent("android.hide.sister.my.HANDLER_HIDE_MY_REDPACKET_TIPS"));
        this.d.sendBroadcast(new Intent("android.budejie.more.UPDATE_RED_PACKET_HIDE"));
        ((BudejieApplication) getApplication()).g().ai.a("");
        this.ae.a("subscribe_Label");
        this.ae.b("recommend_Label");
        EventBus.getDefault().post(LoginAction.UNLOGIN);
    }

    private void t() {
        Builder builder = new Builder(this);
        builder.setTitle(R.string.system_tip);
        builder.setMessage(R.string.clear_collect);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ MoreActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.s();
                this.a.aa.e();
                this.a.k = an.a(this.a.d, this.a.d.getString(R.string.clear_collect_successed), -1);
                this.a.k.show();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ MoreActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.aa.c();
                this.a.s();
            }
        });
        builder.show();
    }
}
