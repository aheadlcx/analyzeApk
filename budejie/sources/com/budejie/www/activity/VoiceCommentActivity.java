package com.budejie.www.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.labelsearch.SearchLabelActivity;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.activity.view.ShowStepView;
import com.budejie.www.bean.DraftBean;
import com.budejie.www.bean.Vote;
import com.budejie.www.bean.VoteData;
import com.budejie.www.c.f;
import com.budejie.www.c.m;
import com.budejie.www.http.e;
import com.budejie.www.http.j;
import com.budejie.www.http.n;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.i;
import com.budejie.www.util.s;
import com.budejie.www.util.z;
import com.budejie.www.widget.SelectLabelLayout;
import com.budejie.www.widget.VoteView;
import com.budejie.www.widget.VoteView.b;
import com.budejie.www.widget.parsetagview.ParseTagEditText;
import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.d;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

public class VoiceCommentActivity extends OauthWeiboBaseAct implements OnClickListener {
    static final /* synthetic */ boolean d;
    private static boolean o = false;
    private f A;
    private String B;
    private String C;
    private String D = "0";
    private String E;
    private j F;
    private VoteView G;
    private String H;
    private SelectLabelLayout I;
    private String J;
    private String K;
    protected a a;
    Handler b = new Handler(this) {
        final /* synthetic */ VoiceCommentActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = 0;
            int i2 = message.what;
            String str;
            Map c;
            String str2;
            if (i2 == 4) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.y = an.a(this.a, this.a.getString(R.string.bind_failed), -1);
                    this.a.y.show();
                    MobclickAgent.onEvent(this.a, "weibo_bind", "sina_faild");
                } else {
                    try {
                        i = Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                    }
                    if (i < 0) {
                        this.a.y = an.a(this.a, this.a.getString(R.string.bind_failed), -1);
                        this.a.y.show();
                        MobclickAgent.onEvent(this.a, "weibo_bind", "sina_faild");
                    } else {
                        c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            MobclickAgent.onEvent(this.a, "weibo_bind", "sina_faild");
                        } else {
                            str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                MobclickAgent.onEvent(this.a, "weibo_bind", "sina_success");
                                str = (String) c.get("id");
                                this.a.z.a(str, c);
                                ai.a(this.a, str, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                if (OauthWeiboBaseAct.mAccessToken != null) {
                                    this.a.z.a(str, OauthWeiboBaseAct.mAccessToken.e());
                                }
                            } else {
                                an.a(this.a, str2, -1).show();
                            }
                        }
                    }
                }
                this.a.a.n.dismiss();
            } else if (i2 == 5) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.y = an.a(this.a, this.a.getString(R.string.bind_failed), -1);
                    this.a.y.show();
                    MobclickAgent.onEvent(this.a, "E03-A10", "绑定腾讯微博失败");
                } else {
                    try {
                        i = Integer.parseInt(str);
                    } catch (NumberFormatException e2) {
                    }
                    if (i < 0) {
                        this.a.y = an.a(this.a, this.a.getString(R.string.bind_failed), -1);
                        this.a.y.show();
                        MobclickAgent.onEvent(this.a, "E03-A10", "绑定腾讯微博失败");
                    } else {
                        c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            MobclickAgent.onEvent(this.a, "E03-A10", "绑定腾讯微博失败");
                        } else {
                            str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                MobclickAgent.onEvent(this.a, "E03-A10", "绑定腾讯微博成功");
                                str = (String) c.get("id");
                                this.a.z.a(str, c);
                                ai.a(this.a, str, Constants.SERVICE_SCOPE_FLAG_VALUE);
                            } else {
                                an.a(this.a, str2, -1).show();
                            }
                        }
                    }
                }
                this.a.a.n.dismiss();
            } else if (i2 == 10) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.y = an.a(this.a, this.a.getString(R.string.bind_failed), -1);
                    this.a.y.show();
                    MobclickAgent.onEvent(this.a, "E03-A10", "绑定Qzone失败");
                } else {
                    try {
                        i = Integer.parseInt(str);
                    } catch (NumberFormatException e3) {
                    }
                    if (i < 0) {
                        this.a.y = an.a(this.a, this.a.getString(R.string.bind_failed), -1);
                        this.a.y.show();
                        MobclickAgent.onEvent(this.a, "E03-A10", "绑定Qzone失败");
                    } else {
                        c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            MobclickAgent.onEvent(this.a, "E03-A10", "绑定Qzone失败");
                        } else {
                            str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                MobclickAgent.onEvent(this.a, "E03-A10", "绑定Qzone成功");
                                str = (String) c.get("id");
                                this.a.z.a(str, c);
                                ai.a(this.a, str, Constants.SERVICE_SCOPE_FLAG_VALUE);
                            } else {
                                an.a(this.a, str2, -1).show();
                            }
                        }
                    }
                }
                this.a.a.n.dismiss();
            }
        }
    };
    TextWatcher c = new TextWatcher(this) {
        final /* synthetic */ VoiceCommentActivity a;

        {
            this.a = r1;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
        }
    };
    private final int e = 435;
    private final int f = 475;
    private TextView g;
    private BudejieApplication h;
    private boolean i;
    private n j;
    private SharedPreferences k;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    private int p = R$styleable.Theme_Custom_send_btn_text_color;
    private String q = com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
    private String r = "这里添加文字，请勿发布色情、政治等违反国家法律的内容，违者封号处理。";
    private String s;
    private int t;
    private File u;
    private File v;
    private int w;
    private int x;
    private Toast y;
    private m z;

    protected class a {
        RelativeLayout a;
        RelativeLayout b;
        LinearLayout c;
        Button d;
        TextView e;
        RelativeLayout f;
        Button g;
        TextView h;
        ParseTagEditText i;
        LinearLayout j;
        Button k;
        Button l;
        Button m;
        Dialog n;
        TextView o;
        ShowStepView p;
        final /* synthetic */ VoiceCommentActivity q;

        protected a(VoiceCommentActivity voiceCommentActivity) {
            this.q = voiceCommentActivity;
        }
    }

    static {
        boolean z;
        if (VoiceCommentActivity.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        d = z;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        i.a().a((Activity) this);
        setContentView(R.layout.voice_comment);
        com.budejie.www.widget.a.a((Activity) this);
        this.h = (BudejieApplication) getApplication();
        this.F = new j();
        Intent intent = getIntent();
        this.u = new File(intent.getStringExtra("img_path"));
        this.v = new File(intent.getStringExtra("record_path"));
        this.w = intent.getIntExtra("bg_music_index", 0);
        this.x = intent.getIntExtra("bg_voice_len", 0);
        this.C = intent.getStringExtra("h5_reserve");
        Bundle bundleExtra = intent.getBundleExtra("theme_data");
        if (bundleExtra != null) {
            this.J = bundleExtra.getString("label_name");
            this.K = String.valueOf(bundleExtra.getInt("label_id"));
        }
        this.z = new m(this);
        this.A = new f(this);
        a();
        g();
        if (!d && this.a == null) {
            throw new AssertionError("mViewHelper can't be null");
        }
    }

    protected void onDestroy() {
        this.a.n.dismiss();
        i.a().b((Activity) this);
        super.onDestroy();
    }

    protected void a() {
        this.a = new a(this);
        this.a.a = (RelativeLayout) findViewById(R.id.tougao_layout);
        this.a.b = (RelativeLayout) findViewById(R.id.titleLayout);
        this.a.d = (Button) findViewById(R.id.title_left_btn);
        this.a.d.setVisibility(0);
        this.a.c = (LinearLayout) findViewById(R.id.left_layout);
        this.a.f = (RelativeLayout) findViewById(R.id.title_refresh_layout);
        this.a.e = (TextView) findViewById(R.id.title_center_txt);
        this.a.g = (Button) findViewById(R.id.refresh_btn);
        this.a.g.setVisibility(8);
        this.a.p = (ShowStepView) findViewById(R.id.show_step);
        this.a.p.setCurrentLine(4);
        this.a.h = (TextView) findViewById(R.id.title_right_btn);
        this.a.h.setVisibility(0);
        findViewById(R.id.selector_contacts).setOnClickListener(this);
        findViewById(R.id.selector_label).setOnClickListener(this);
        findViewById(R.id.add_vote).setOnClickListener(this);
        findViewById(R.id.add_vote_name).setOnClickListener(this);
        this.g = (TextView) findViewById(R.id.selector_label_name);
        this.g.setOnClickListener(this);
        Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "tougao_size");
        if (!TextUtils.isEmpty(configParams)) {
            this.q = configParams;
        }
        this.a.i = (ParseTagEditText) findViewById(R.id.editTougao);
        this.a.i.setTextChangedListener(this.c);
        this.a.i.setListener(new com.budejie.www.widget.parsetagview.a(this));
        this.a.i.setHint(this.r);
        this.a.i.setFilters(new InputFilter[]{new s()});
        this.a.j = (LinearLayout) findViewById(R.id.write_weibo_layout);
        this.a.k = a((Context) this);
        this.a.l = a((Context) this);
        this.a.m = a((Context) this);
        this.j = new n(this);
        this.k = getSharedPreferences("weiboprefer", 0);
        f();
        this.a.n = new Dialog(this, R.style.dialogTheme);
        this.a.n.setContentView(R.layout.loaddialog);
        this.a.n.setCanceledOnTouchOutside(true);
        this.a.o = (TextView) this.a.n.findViewById(R.id.dialog_txt);
        this.a.d.setOnClickListener(this);
        this.a.h.setOnClickListener(this);
        this.a.c.setOnClickListener(this);
        this.a.f.setOnClickListener(this);
        this.a.c.setVisibility(0);
        this.a.f.setVisibility(0);
        this.a.e.setText("声音");
        a(o);
        e();
        this.G = (VoteView) findViewById(R.id.vote_view);
        this.G.setCancelListener(new b(this) {
            final /* synthetic */ VoiceCommentActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.H = "";
            }
        });
        this.G.setVisibility(8);
        c();
    }

    private void c() {
        this.I = (SelectLabelLayout) findViewById(R.id.label_layout);
        this.I.setListener(new OnClickListener(this) {
            final /* synthetic */ VoiceCommentActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d();
            }
        });
        this.i = !TextUtils.isEmpty(this.J);
        this.I.setLabelName(this.J);
    }

    private void d() {
        Intent intent = new Intent(this, SearchLabelActivity.class);
        intent.putExtra("topicType", "31");
        startActivityForResult(intent, 10);
    }

    private void e() {
        String str = "0";
        Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "tougao_voice_text_limit");
        if (!TextUtils.isEmpty(configParams) && !"0".equals(configParams)) {
            this.p = Integer.parseInt(configParams);
        }
    }

    private void f() {
        Editor edit = this.k.edit();
        edit.putString("voice_comment_cache", this.s);
        edit.putString("voice_image_cache", this.u.toString());
        edit.putString("voice_file_cache", this.v.toString());
        edit.putInt("bgmusic_file_cache", this.w);
        edit.commit();
    }

    private void g() {
        this.s = this.k.getString("voice_comment_cache", "");
        if (!TextUtils.isEmpty(this.s)) {
            this.a.i.setText(this.s);
        }
    }

    private void h() {
        Editor edit = this.k.edit();
        edit.remove("voice_comment_cache");
        edit.remove("voice_image_cache");
        ai.b((Context) this, 0);
        edit.remove("voice_file_cache");
        edit.remove("bgmusic_file_cache");
        edit.commit();
    }

    private void i() {
        this.l = this.k.getBoolean("sharesina", true);
        this.m = this.k.getBoolean("sharetenct", true);
        this.n = this.k.getBoolean("shareqzone", true);
    }

    public Button a(Context context) {
        Button button = new Button(context);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(an.a(context, 43), an.a(context, 43));
        layoutParams.setMargins(10, 0, 10, 0);
        button.setLayoutParams(layoutParams);
        button.setOnClickListener(this);
        return button;
    }

    public void onClick(View view) {
        int i = 0;
        switch (view.getId()) {
            case R.id.add_vote:
            case R.id.add_vote_name:
                Intent intent = new Intent(this, AddVoteActivity.class);
                intent.putExtra("vote_data_key", this.H);
                startActivityForResult(intent, 10);
                break;
            case R.id.selector_contacts:
                startActivityForResult(new Intent(this, SelectorContactsActivity.class), 435);
                break;
        }
        if (view == this.a.d || view == this.a.c) {
            this.a.n.dismiss();
            if (o) {
                a(2);
                b();
                return;
            }
            finish();
        } else if (view == this.a.h || view == this.a.f) {
            if (an.a(this.k)) {
                this.s = this.a.i.getText().toString();
                this.E = this.s.trim();
                try {
                    i = Integer.parseInt(this.q);
                } catch (Exception e) {
                    aa.e("VoiceCommentActivity", "parse tougao size error");
                }
                if (this.E.length() < i) {
                    an.a((Activity) this, "投稿内容不能少于" + i + "个字", -1).show();
                    return;
                } else if (this.i) {
                    a(2);
                    try {
                        this.t = Integer.parseInt(ai.b(this));
                    } catch (Exception e2) {
                    }
                    j();
                    return;
                } else {
                    d();
                    return;
                }
            }
            an.a((Activity) this, 1, "voice", "publish", (int) R$styleable.Theme_Custom_new_item_login_qq_bg);
        } else if (view == this.a.k) {
            if (this.j.a((Context) this)) {
                i();
                if (this.l) {
                    this.a.k.setBackgroundResource(R.drawable.comment_sina_light);
                    this.k.edit().putBoolean("sharesina", i).commit();
                    return;
                }
                this.a.k.setBackgroundResource(R.drawable.comment_sina_forward);
                this.k.edit().putBoolean("sharesina", true).commit();
                return;
            }
            a("sina");
        } else if (view == this.a.l) {
            if (this.j.b((Context) this)) {
                i();
                if (this.m) {
                    this.a.l.setBackgroundResource(R.drawable.comment_tenct_light);
                    this.k.edit().putBoolean("sharetenct", i).commit();
                    return;
                }
                this.a.l.setBackgroundResource(R.drawable.comment_tenct_forward);
                this.k.edit().putBoolean("sharetenct", true).commit();
                return;
            }
            a("tenct");
        } else if (view != this.a.m) {
        } else {
            if (this.j.c(this)) {
                i();
                if (this.n) {
                    this.a.m.setBackgroundResource(R.drawable.comment_qzone_light);
                    this.k.edit().putBoolean("shareqzone", i).commit();
                    return;
                }
                this.a.m.setBackgroundResource(R.drawable.comment_qzone_forward);
                this.k.edit().putBoolean("shareqzone", true).commit();
                return;
            }
            a(com.tencent.connect.common.Constants.SOURCE_QZONE);
        }
    }

    private void j() {
        com.budejie.www.util.b.a(this, a(this.t, this.u, this.v, this.w, this.x, this.E, this.C, this.H), this.B, 0);
        Intent intent = new Intent(this, MyPostsActivity.class);
        intent.putExtra("from_tougao_tag", true);
        startActivity(intent);
        this.a.n.dismiss();
        h();
        i.a().b();
    }

    protected void a(int i) {
        switch (i) {
            case 1:
                this.a.o.setText(getResources().getString(R.string.downloading));
                break;
            case 2:
                this.a.o.setText(getResources().getString(R.string.sending));
                break;
        }
        this.a.n.show();
    }

    public void b() {
        Intent intent = new Intent(this, MyPostsActivity.class);
        intent.putExtra("from_tougao_tag", true);
        intent.setFlags(67108864);
        intent.addFlags(536870912);
        startActivity(intent);
    }

    private void a(String str) {
        a(1);
        if ("sina".equals(str)) {
            MobclickAgent.onEvent((Context) this, "weibo_bind", "sina_start");
            if (this.mSsoHandler == null) {
                this.mSsoHandler = new com.sina.weibo.sdk.auth.a.a(this);
            }
            this.mSsoHandler.a((d) this);
        } else if ("tenct".equals(str)) {
            MobclickAgent.onEvent((Context) this, "weibo_bind", "tencent_start");
            auth(Long.valueOf(Util.getConfig().getProperty("APP_KEY")).longValue(), Util.getConfig().getProperty("APP_KEY_SEC"));
        } else if (com.tencent.connect.common.Constants.SOURCE_QZONE.equals(str)) {
            MobclickAgent.onEvent((Context) this, "weibo_bind", "qzone_start");
            this.mTencent.login((Activity) this, "get_simple_userinfo,get_user_profile,add_share,add_topic,list_album,upload_pic,add_album", (IUiListener) this);
        }
    }

    public net.tsz.afinal.a.b a(int i, File file, File file2, int i2, int i3, String str, String str2, String str3) {
        Map a = this.F.a((Context) this, new HashMap());
        a.put("format", "json");
        a.put(HistoryOpenHelper.COLUMN_UID, String.valueOf(i));
        a.put("bvoiceid", String.valueOf(i2));
        a.put("voicetime", String.valueOf(i3));
        a.put("content", String.valueOf(str));
        if (((BudejieApplication) getApplication()).b != null) {
            a.put("longitude", ((BudejieApplication) getApplication()).b[0]);
            a.put("latitude", ((BudejieApplication) getApplication()).b[1]);
        }
        if (!TextUtils.isEmpty(str2)) {
            a.put("reserve", str2);
        }
        Map map;
        if (!TextUtils.isEmpty(this.K)) {
            map = a;
            map.put("theme_id", this.K);
        } else if (!TextUtils.isEmpty(this.J)) {
            map = a;
            map.put("theme_name", this.J);
        }
        a.put("type", "31");
        this.B = i.a().d();
        DraftBean draftBean = new DraftBean(0, i, 0, file.getAbsolutePath(), file2.getAbsolutePath(), str, i2, i3, this.B, this.C, null, 0, false);
        draftBean.voteDataStr = this.H;
        Object arrayList = new ArrayList();
        PlateBean plateBean = new PlateBean();
        plateBean.theme_id = this.K;
        plateBean.theme_name = this.J;
        arrayList.add(plateBean);
        draftBean.plateDataStr = new Gson().toJson(arrayList);
        this.A.a(draftBean);
        a.put("a", "createugc");
        a.put("c", "topic");
        a.put("vote", str3);
        return e.a("/api/api_open.php", a, file, file2);
    }

    public void a(boolean z) {
        o = z;
        if (z) {
            this.a.d.setEnabled(false);
            return;
        }
        this.a.d.setEnabled(true);
        this.a.n.dismiss();
    }

    protected void onResume() {
        super.onResume();
        if (!(this.a == null || this.a.n == null)) {
            this.a.n.dismiss();
        }
        new Timer().schedule(new TimerTask(this) {
            final /* synthetic */ VoiceCommentActivity a;

            {
                this.a = r1;
            }

            public void run() {
                an.a(this.a, this.a.a.i);
            }
        }, 300);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == R$styleable.Theme_Custom_new_item_login_qq_bg || i2 == R$styleable.Theme_Custom_new_item_login_qq_bg) {
        }
        if (i2 == 711) {
            bindTencent();
        } else if (i2 == 0) {
            if (this.a.n.isShowing()) {
                this.a.n.dismiss();
            }
        } else if (i2 == -1) {
            if (i == 435 || i == 436) {
                String stringExtra = intent.getStringExtra(getString(R.string.RESPONE_RESULT_CONTACT_NAME));
                if (!TextUtils.isEmpty(stringExtra)) {
                    CharSequence append;
                    if (i == 436) {
                        append = new StringBuilder("").append(stringExtra).append(" ");
                    } else {
                        append = new StringBuilder("@").append(stringExtra).append(" ");
                    }
                    this.a.i.getEditableText().insert(this.a.i.getSelectionStart(), append);
                }
            }
        } else if (i2 == 10) {
            this.H = intent.getStringExtra("vote_data_key");
            if (!TextUtils.isEmpty(this.H)) {
                k();
            }
        } else if (i == 10 && i2 == 11) {
            this.J = intent.getStringExtra("selectLabelNameTag");
            this.K = intent.getStringExtra("selectLabelThemeIdTag");
            this.I.setLabelName(this.J);
            this.i = true;
        }
    }

    private void k() {
        this.G.a();
        String[] split = this.H.split(",");
        ArrayList arrayList = new ArrayList();
        for (String str : split) {
            Vote vote = new Vote();
            vote.name = str;
            arrayList.add(vote);
        }
        VoteData voteData = new VoteData();
        voteData.votes = arrayList;
        this.G.a(voteData, true);
        this.G.setVisibility(0);
    }

    public void bindTencent() {
        String string = this.k.getString("id", "");
        String sharePersistent = Util.getSharePersistent(this, "ACCESS_TOKEN");
        this.j.a(Util.getSharePersistent(this, "NAME"), sharePersistent, Util.getSharePersistent(this, "OPEN_ID"), string, 5, this.b);
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        super.onComplete(jSONObject);
        HashMap a = z.a(jSONObject);
        if (a != null && a.size() != 0) {
            this.k.edit().putString("openid", (String) a.get("qzone_uid")).putString("qzone_token", (String) a.get("qzone_token")).putString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN, (System.currentTimeMillis() + (Long.parseLong((String) a.get(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN)) * 1000)) + "").commit();
            this.j.a((String) a.get("qzone_uid"), this.k.getString("id", ""), (String) a.get("qzone_token"), 10, this.b);
        }
    }

    public void onError(UiError uiError) {
        super.onError(uiError);
        Toast.makeText(this, "code:" + uiError.errorCode + ", msg:" + uiError.errorMessage + ", detail:" + uiError.errorDetail, 0).show();
        this.a.n.dismiss();
    }

    public void onCancel() {
        super.onCancel();
        this.a.n.dismiss();
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        try {
            String string = this.k.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this, mAccessToken);
                Toast.makeText(this, "认证成功", 0).show();
                this.j.a(mAccessToken, string, 4, this.b);
                return;
            }
        } catch (Exception e) {
        }
        this.a.n.dismiss();
        this.y = an.a((Activity) this, getString(R.string.sina_shouquan_failed), -1);
        this.y.show();
    }

    public void onrefreshTheme() {
        super.onrefreshTheme();
        this.a.b.setBackgroundResource(com.budejie.www.util.j.a);
        this.a.e.setTextColor(getResources().getColor(com.budejie.www.util.j.b));
        this.a.p.setBackgroundResource(com.budejie.www.util.j.u);
        this.a.a.setBackgroundResource(com.budejie.www.util.j.u);
        onRefreshTitleFontTheme(this.a.d, true);
        onRefreshTitleFontTheme(this.a.h, false);
    }
}
