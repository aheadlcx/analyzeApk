package com.budejie.www.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.internal.view.SupportMenu;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.login.LoginConstants;
import com.alipay.sdk.util.h;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.c.e;
import com.budejie.www.c.m;
import com.budejie.www.http.n;
import com.budejie.www.util.an;
import com.budejie.www.util.j;
import com.budejie.www.util.z;
import com.elves.update.a;
import com.sina.weibo.sdk.auth.d;
import com.tencent.open.SocialConstants;
import com.tencent.tauth.IUiListener;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class sendCommendActivity extends BaseActvityWithLoadDailog implements OnClickListener {
    private m A;
    private int B = R$styleable.Theme_Custom_send_btn_text_color;
    private TextView C;
    private Handler D = new Handler(this) {
        final /* synthetic */ sendCommendActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            try {
                int i = message.what;
                if (i == 12) {
                    Map u = z.u((String) message.obj);
                    if (u == null) {
                        this.a.b = an.a(this.a.f, this.a.f.getString(R.string.operate_fail), -1);
                        this.a.b.show();
                        return;
                    }
                    if ("1".equals((String) u.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY))) {
                        this.a.m.a(this.a.l, true, (int) R.string.sendsuccess);
                        if (!(this.a.n == null || this.a.n.getRichObject() == null)) {
                            RichPostDetail.a++;
                        }
                        this.a.a(true);
                        this.a.f.finish();
                        return;
                    }
                    this.a.b = an.a(this.a.f, (String) u.get(SocialConstants.PARAM_APP_DESC), -1);
                    this.a.b.show();
                } else if (i == 931) {
                    this.a.m.a(this.a.l);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    TextWatcher a = new TextWatcher(this) {
        final /* synthetic */ sendCommendActivity a;

        {
            this.a = r1;
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(this.a.t.getText())) {
                this.a.C.setTextColor(-1);
                this.a.C.setText("" + this.a.B);
                return;
            }
            int h = this.a.B - this.a.t.getText().toString().trim().length();
            if (h < 0) {
                this.a.C.setTextColor(SupportMenu.CATEGORY_MASK);
            } else {
                this.a.C.setTextColor(-16777216);
            }
            this.a.C.setText("" + h);
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    };
    private Toast b;
    private SharedPreferences c;
    private String d;
    private String e;
    private sendCommendActivity f;
    private n h;
    private boolean i = false;
    private boolean j = false;
    private e k;
    private int l;
    private a m;
    private ListItemObject n;
    private com.budejie.www.util.n o;
    private RelativeLayout p;
    private TextView q;
    private TextView r;
    private TextView s;
    private EditText t;
    private String u;
    private HashMap<String, String> v;
    private Button w;
    private Button x;
    private LinearLayout y;
    private String z;

    private void a(final boolean z) {
        new Thread(this) {
            final /* synthetic */ sendCommendActivity b;

            public void run() {
                try {
                    Thread.sleep(3000);
                    this.b.D.sendMessage(this.b.D.obtainMessage(931, Boolean.valueOf(z)));
                } catch (InterruptedException e) {
                }
            }
        }.start();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.writecommend);
        a();
        b();
        g();
    }

    private void a() {
        this.f = this;
        this.c = getSharedPreferences("weiboprefer", 0);
        this.h = new n(this);
        this.o = new com.budejie.www.util.n();
        this.k = new e(this);
        this.A = new m(this);
        this.n = (ListItemObject) getIntent().getSerializableExtra("post_key");
        this.e = this.n.getWid() + "";
    }

    private void b() {
        this.p = (RelativeLayout) findViewById(R.id.title);
        this.q = (TextView) findViewById(R.id.write_left_btn);
        this.r = (TextView) findViewById(R.id.write_comment_txt);
        this.s = (TextView) findViewById(R.id.comment_send);
        this.q.setText(R.string.cancel);
        this.r.setTextColor(getResources().getColor(j.b));
        this.q.setOnClickListener(this);
        this.s.setOnClickListener(this);
    }

    private void g() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.m = new a(this);
        this.t = (EditText) findViewById(R.id.commend);
        this.y = (LinearLayout) findViewById(R.id.write_weibo_layout);
        this.w = this.o.a(this.f);
        this.x = this.o.a(this.f);
        this.w.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.C = (TextView) findViewById(R.id.textview);
        this.C.setTextColor(-16777216);
        this.t.addTextChangedListener(this.a);
        this.t.setFocusableInTouchMode(true);
        this.t.requestFocus();
        this.t.setText("");
        h();
    }

    private void h() {
        String string = this.c.getString("locallogin", "");
        this.v = new HashMap();
        if (Constants.SERVICE_SCOPE_FLAG_VALUE.equals(string)) {
            j();
            this.d = this.c.getString("id", "");
            a(this.d);
        }
        if (!this.h.a(this.f)) {
            this.w.setBackgroundResource(R.drawable.comment_sina_normal);
        } else if (this.i) {
            this.w.setBackgroundResource(R.drawable.comment_sina_forward);
        } else {
            this.w.setBackgroundResource(R.drawable.comment_sina_light);
        }
        if (!this.h.b(this.f)) {
            this.x.setBackgroundResource(R.drawable.comment_tenct_normal);
        } else if (this.j) {
            this.x.setBackgroundResource(R.drawable.comment_tenct_forward);
        } else {
            this.x.setBackgroundResource(R.drawable.comment_tenct_light);
        }
        i();
    }

    private void i() {
        this.y.removeAllViews();
        List<Button> arrayList = new ArrayList();
        List<Button> arrayList2 = new ArrayList();
        if (this.h.a(this.f)) {
            arrayList.add(this.w);
        } else {
            arrayList2.add(this.w);
        }
        if (this.h.b(this.f)) {
            arrayList.add(this.x);
        } else {
            arrayList2.add(this.x);
        }
        for (Button addView : arrayList) {
            this.y.addView(addView);
        }
        for (Button addView2 : arrayList2) {
            this.y.addView(addView2);
        }
    }

    private void j() {
        this.i = this.c.getBoolean("sharesina", true);
        this.j = this.c.getBoolean("sharetenct", true);
    }

    private void a(String str) {
        this.z = this.A.a(str);
        String[] split = this.z.split(h.b);
        if (!TextUtils.isEmpty(this.z) && split.length != 0 && !"null".equals(this.z)) {
            for (int i = 0; i < split.length; i++) {
                CharSequence charSequence = split[i];
                if (!(TextUtils.isEmpty(charSequence) || "null".equals(charSequence))) {
                    String[] split2 = split[i].split(LoginConstants.EQUAL);
                    this.v.put(split2[0], split2[1]);
                }
            }
        }
    }

    public void onrefreshTheme() {
        super.onrefreshTheme();
        this.p.setBackgroundResource(j.a);
    }

    public void onClick(View view) {
        if (view == this.q) {
            this.f.finish();
        } else if (view == this.s) {
            if (an.a((Context) this)) {
                this.u = this.t.getText().toString().trim();
                int length = this.u.length();
                Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "comment_size");
                int i = 2;
                if (!TextUtils.isEmpty(configParams)) {
                    i = Integer.parseInt(configParams);
                }
                if (length < i) {
                    this.b = an.a((Activity) this, getString(R.string.commend_limit, new Object[]{Integer.valueOf(i)}), -1);
                    this.b.show();
                    return;
                } else if (length > R$styleable.Theme_Custom_send_btn_text_color) {
                    this.b = an.a((Activity) this, getString(R.string.text_beyond) + (length - 140) + getString(R.string.again_input), -1);
                    this.b.show();
                    return;
                } else {
                    this.d = this.c.getString("id", "");
                    if (TextUtils.isEmpty(this.d)) {
                        an.a(this.f, 0, null, null, 0);
                        return;
                    } else if (this.k.b(this.e, this.u)) {
                        this.b = an.a((Activity) this, getString(R.string.commentExist), -1);
                        this.b.show();
                        return;
                    } else {
                        j();
                        StringBuffer stringBuffer = new StringBuffer();
                        if (this.h.a(this.f) && this.i) {
                            stringBuffer.append("sina,");
                            this.c.edit().putBoolean("sharesina", true).commit();
                        } else {
                            this.c.edit().putBoolean("sharesina", false).commit();
                        }
                        if (this.h.b(this.f) && this.j) {
                            stringBuffer.append("qq,");
                            this.c.edit().putBoolean("sharetenct", true).commit();
                        } else {
                            this.c.edit().putBoolean("sharetenct", false).commit();
                        }
                        String stringBuffer2 = stringBuffer.toString();
                        if (stringBuffer2.endsWith(",")) {
                            stringBuffer2 = stringBuffer2.substring(0, stringBuffer.length() - 1);
                        }
                        if (TextUtils.isEmpty(stringBuffer2)) {
                            stringBuffer2 = "";
                        }
                        a(stringBuffer2, this.u);
                        return;
                    }
                }
            }
            Toast.makeText(this, R.string.nonet, 0).show();
        } else if (view == this.w) {
            if (this.h.a(this.f)) {
                j();
                if (this.i) {
                    this.w.setBackgroundResource(R.drawable.comment_sina_light);
                    this.c.edit().putBoolean("sharesina", false).commit();
                    return;
                }
                this.w.setBackgroundResource(R.drawable.comment_sina_forward);
                this.c.edit().putBoolean("sharesina", true).commit();
                return;
            }
            b("sina");
        } else if (view != this.x) {
        } else {
            if (this.h.b(this.f)) {
                j();
                if (this.j) {
                    this.x.setBackgroundResource(R.drawable.comment_tenct_light);
                    this.c.edit().putBoolean("sharetenct", false).commit();
                    return;
                }
                this.x.setBackgroundResource(R.drawable.comment_tenct_forward);
                this.c.edit().putBoolean("sharetenct", true).commit();
                return;
            }
            b("tenct");
        }
    }

    private void b(String str) {
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
            this.mTencent.login(this.f, "get_simple_userinfo,get_user_profile,add_share,add_topic,list_album,upload_pic,add_album", (IUiListener) this);
        }
    }

    private void a(String str, String str2) {
        this.l = ((int) System.currentTimeMillis()) / 100;
        this.m.a(this.l, getString(R.string.commend_sending));
        String str3 = str2;
        String str4 = str;
        this.h.a(this.e, str3, str4, this.d, this.D, 12, "", false, a(this.n));
    }

    public static String a(ListItemObject listItemObject) {
        String str = "";
        if (listItemObject == null) {
            return str;
        }
        str = listItemObject.getType();
        if ("29".equals(str)) {
            return "text";
        }
        if (com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(str)) {
            return CheckCodeDO.CHECKCODE_IMAGE_URL_KEY;
        }
        if ("31".equals(str)) {
            return "voice";
        }
        if ("41".equals(str)) {
            return "video";
        }
        return "html";
    }
}
