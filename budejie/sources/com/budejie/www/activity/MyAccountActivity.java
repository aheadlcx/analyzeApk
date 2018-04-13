package com.budejie.www.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.core.model.SystemMessageConstants;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alipay.sdk.util.j;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.phonenumber.PhoneNumBindingActivity;
import com.budejie.www.activity.phonenumber.PhoneNumChangeActivity;
import com.budejie.www.activity.phonenumber.PhoneRetrievePasswordActivity;
import com.budejie.www.activity.video.c;
import com.budejie.www.bean.UserItem;
import com.budejie.www.c.b;
import com.budejie.www.c.d;
import com.budejie.www.c.f;
import com.budejie.www.c.g;
import com.budejie.www.c.l;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.n;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.au;
import com.budejie.www.util.p;
import com.budejie.www.util.z;
import com.budejie.www.wxapi.WXEntryActivity;
import com.facebook.common.util.UriUtil;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.open.SocialConstants;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.CookieStore;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class MyAccountActivity extends OauthWeiboBaseAct implements com.budejie.www.f.a {
    TextView A;
    TextView B;
    RelativeLayout C;
    TextView D;
    TextView E;
    OnClickListener F = new OnClickListener(this) {
        final /* synthetic */ MyAccountActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view == this.a.K || view == this.a.M) {
                this.a.J.setResult(719);
                this.a.J.finish();
            }
        }
    };
    OnDateSetListener G = new OnDateSetListener(this) {
        final /* synthetic */ MyAccountActivity a;

        {
            this.a = r1;
        }

        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            if (this.a.W) {
                String str;
                String str2;
                String str3 = (i2 + 1) + "";
                String str4 = i3 + "";
                if (i2 < 9) {
                    str = "0" + (i2 + 1);
                } else {
                    str = str3;
                }
                if (i3 < 10) {
                    str2 = "0" + i3;
                } else {
                    str2 = str4;
                }
                this.a.s = i + "/" + str + "/" + str2;
                this.a.H.sendEmptyMessage(925);
                this.a.u.a(this.a.m, "", "", "", "", i + "-" + str + "-" + str2, "", 2959);
                this.a.W = false;
            }
        }
    };
    Handler H = new Handler(this) {
        final /* synthetic */ MyAccountActivity a;

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
                    this.a.j = an.a(this.a.J, this.a.J.getString(R.string.bind_failed), -1);
                    this.a.j.show();
                    MobclickAgent.onEvent(this.a.J, "weibo_bind", "tencent_faild");
                } else {
                    try {
                        i = Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                    }
                    if (i < 0) {
                        this.a.j = an.a(this.a.J, this.a.J.getString(R.string.bind_failed), -1);
                        this.a.j.show();
                        MobclickAgent.onEvent(this.a.J, "weibo_bind", "tencent_faild");
                    } else {
                        c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            MobclickAgent.onEvent(this.a.J, "weibo_bind", "tencent_faild");
                        } else {
                            str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(j.c))) {
                                MobclickAgent.onEvent(this.a.J, "weibo_bind", "tencent_success");
                                this.a.m = (String) c.get("id");
                                this.a.p.a(this.a.m, c);
                                ai.a(this.a.J, this.a.m, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.d();
                                this.a.j = an.a(this.a.J, this.a.J.getString(R.string.bind_successed), -1);
                                this.a.j.show();
                            } else {
                                this.a.j = an.a(this.a.J, str2, -1);
                                this.a.j.show();
                            }
                        }
                    }
                }
                this.a.H.sendEmptyMessage(925);
            } else if (i2 == 924) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.j = an.a(this.a.J, this.a.J.getString(R.string.bind_failed), -1);
                    this.a.j.show();
                    MobclickAgent.onEvent(this.a.J, "weibo_bind", "sina_faild");
                } else {
                    try {
                        i = Integer.parseInt(str);
                    } catch (NumberFormatException e2) {
                    }
                    if (i < 0) {
                        this.a.j = an.a(this.a.J, this.a.J.getString(R.string.bind_failed), -1);
                        this.a.j.show();
                        MobclickAgent.onEvent(this.a.J, "weibo_bind", "sina_faild");
                    } else {
                        c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            MobclickAgent.onEvent(this.a.J, "weibo_bind", "sina_success");
                        } else {
                            str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(j.c))) {
                                MobclickAgent.onEvent(this.a.J, "weibo_bind", "sina_success");
                                this.a.m = (String) c.get("id");
                                this.a.p.a(this.a.m, c);
                                ai.a(this.a.J, this.a.m, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                if (OauthWeiboBaseAct.mAccessToken != null) {
                                    this.a.p.a(this.a.m, OauthWeiboBaseAct.mAccessToken.e());
                                }
                                this.a.d();
                                this.a.j = an.a(this.a.J, this.a.J.getString(R.string.bind_successed), -1);
                                this.a.j.show();
                            } else {
                                this.a.j = an.a(this.a.J, str2, -1);
                                this.a.j.show();
                            }
                        }
                    }
                }
                this.a.H.sendEmptyMessage(925);
            } else if (i2 == 929) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.j = an.a(this.a.J, this.a.J.getString(R.string.bind_failed), -1);
                    this.a.j.show();
                    MobclickAgent.onEvent(this.a.J, "weibo_bind", "qzone_faild");
                } else {
                    try {
                        i = Integer.parseInt(str);
                    } catch (NumberFormatException e3) {
                    }
                    if (i < 0) {
                        this.a.j = an.a(this.a.J, this.a.J.getString(R.string.bind_failed), -1);
                        this.a.j.show();
                        MobclickAgent.onEvent(this.a.J, "weibo_bind", "qzone_faild");
                    } else {
                        c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            this.a.j = an.a(this.a.J, this.a.J.getString(R.string.bind_failed), -1);
                            this.a.j.show();
                            MobclickAgent.onEvent(this.a.J, "weibo_bind", "qzone_faild");
                        } else {
                            str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(j.c))) {
                                MobclickAgent.onEvent(this.a.J, "weibo_bind", "qzone_success");
                                this.a.m = (String) c.get("id");
                                this.a.p.a(this.a.m, c);
                                ai.a(this.a.J, this.a.m, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.d();
                                this.a.j = an.a(this.a.J, this.a.J.getString(R.string.bind_successed), -1);
                                this.a.j.show();
                            } else {
                                this.a.j = an.a(this.a.J, str2, -1);
                                this.a.j.show();
                            }
                        }
                    }
                }
                this.a.H.sendEmptyMessage(925);
            } else if (i2 == SystemMessageConstants.JS_BRIDGE_ANNOTATION_NOT_PRESENT) {
                r1 = z.k((String) message.obj);
                if (r1.get("result_desc") == null || TextUtils.isEmpty((CharSequence) r1.get("result_desc"))) {
                    this.a.j = an.a(this.a.J, this.a.J.getString(R.string.person_edit_data_faild), -1);
                    this.a.j.show();
                } else if ("0".equals(r1.get(j.c))) {
                    this.a.p.a("portrait", (String) r1.get("url"), this.a.m);
                    UserItem g = an.g(this.a.J);
                    if (g != null) {
                        com.d.a.a(g.getProfile(), g.getName());
                    }
                    this.a.b();
                    this.a.c();
                    if (this.a.k.isShowing()) {
                        this.a.k.cancel();
                    }
                } else {
                    this.a.j = an.a(this.a.J, (String) r1.get("result_desc"), -1);
                    this.a.j.show();
                }
            } else if (i2 == 954) {
                this.a.a(com.tencent.connect.common.Constants.SOURCE_QZONE, (String) message.obj);
            } else if (i2 == 953) {
                this.a.a("sina", (String) message.obj);
            } else if (i2 == 955) {
                this.a.a("tencent", (String) message.obj);
            } else if (i2 == 2960) {
                this.a.H.sendEmptyMessage(925);
                r1 = z.l((String) message.obj);
                if (r1.isEmpty()) {
                    this.a.j = an.a(this.a.J, this.a.J.getString(R.string.person_edit_data_faild), -1);
                    this.a.j.show();
                } else if (r1.get("msg") == null || TextUtils.isEmpty((CharSequence) r1.get("msg"))) {
                    this.a.j = an.a(this.a.J, this.a.J.getString(R.string.person_edit_data_faild), -1);
                    this.a.j.show();
                } else if ("0".equals(r1.get(j.c))) {
                    this.a.j = an.a(this.a.J, this.a.J.getString(R.string.person_edit_data_success), -1);
                    this.a.j.show();
                    this.a.p.a("birthday", this.a.s, this.a.m);
                    this.a.y.edit().putString("birthday", this.a.s).commit();
                    this.a.b();
                } else {
                    this.a.j = an.a(this.a.J, (String) r1.get("msg"), -1);
                    this.a.j.show();
                }
            } else if (i2 == 1960) {
                this.a.H.sendEmptyMessage(925);
                r1 = z.l((String) message.obj);
                if (r1.isEmpty()) {
                    this.a.j = an.a(this.a.J, this.a.J.getString(R.string.person_edit_data_faild), -1);
                    this.a.j.show();
                } else if (r1.get("msg") == null || TextUtils.isEmpty((CharSequence) r1.get("msg"))) {
                    this.a.j = an.a(this.a.J, this.a.J.getString(R.string.person_edit_data_faild), -1);
                    this.a.j.show();
                } else if ("0".equals(r1.get(j.c))) {
                    this.a.j = an.a(this.a.J, this.a.J.getString(R.string.person_edit_data_success), -1);
                    this.a.j.show();
                    this.a.p.a("degree", this.a.r, this.a.m);
                    this.a.y.edit().putString("degree", this.a.r).commit();
                    this.a.b();
                } else {
                    this.a.j = an.a(this.a.J, (String) r1.get("msg"), -1);
                    this.a.j.show();
                }
            } else if (i2 == 960) {
                this.a.H.sendEmptyMessage(925);
                r1 = z.l((String) message.obj);
                if (r1.isEmpty()) {
                    this.a.j = an.a(this.a.J, this.a.J.getString(R.string.person_edit_data_faild), -1);
                    this.a.j.show();
                } else if (r1.get("msg") == null || TextUtils.isEmpty((CharSequence) r1.get("msg"))) {
                    this.a.j = an.a(this.a.J, this.a.J.getString(R.string.person_edit_data_faild), -1);
                    this.a.j.show();
                } else if ("0".equals(r1.get(j.c))) {
                    this.a.j = an.a(this.a.J, this.a.J.getString(R.string.person_edit_data_success), -1);
                    this.a.j.show();
                    this.a.p.a("sex", this.a.n, this.a.m);
                    this.a.y.edit().putString("sex", this.a.n).commit();
                    this.a.b();
                } else {
                    this.a.j = an.a(this.a.J, (String) r1.get("msg"), -1);
                    this.a.j.show();
                }
            } else if (i2 == 925) {
                if (!this.a.J.isFinishing()) {
                    if (this.a.k.isShowing()) {
                        this.a.k.cancel();
                    } else {
                        this.a.k.show();
                    }
                }
            } else if (i2 == 1003) {
                this.a.a("phone", (String) message.obj);
            }
        }
    };
    private String I = "MyAccountActivity";
    private MyAccountActivity J;
    private Button K;
    private TextView L;
    private LinearLayout M;
    private RelativeLayout N;
    private d O;
    private b P;
    private View Q;
    private TextView R;
    private TextView S;
    private TextView T;
    private TextView U;
    private RelativeLayout V;
    private boolean W;
    private Calendar X;
    private String[] Y = new String[]{"在读学生", "高中毕业", "专科毕业", "本科毕业"};
    private f Z;
    TextView a;
    private l aa;
    private IWXAPI ab;
    private net.tsz.afinal.a.a<String> ac = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ MyAccountActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            aa.e("wuzhenlin", "onFailure " + str);
            if (!this.a.J.isFinishing()) {
                this.a.k.cancel();
                this.a.j = an.a(this.a.J, "解除失败", -1);
                this.a.j.show();
            }
        }

        public void a(String str) {
            try {
                if (TextUtils.isEmpty(str)) {
                    this.a.j = an.a(this.a.J, "解除失败", -1);
                    this.a.j.show();
                } else {
                    int i = 0;
                    try {
                        i = Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                    }
                    if (i < 0) {
                        this.a.j = an.a(this.a.J, "解除失败", -1);
                        this.a.j.show();
                    } else {
                        HashMap c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            this.a.j = an.a(this.a.J, "解除失败", -1);
                            this.a.j.show();
                        } else {
                            String str2 = (String) c.get(j.c);
                            String str3 = (String) c.get("result_msg");
                            if ("0".equals(str2)) {
                                ai.a(this.a.J, "");
                                this.a.d();
                                this.a.j = an.a(this.a.J, str3, -1);
                                this.a.j.show();
                            } else {
                                this.a.j = an.a(this.a.J, str3, -1);
                                this.a.j.show();
                            }
                        }
                    }
                }
                this.a.k.cancel();
            } catch (Exception e2) {
                e2.printStackTrace();
                aa.e("", "ljj-->" + e2.toString());
                if (!this.a.J.isFinishing()) {
                    this.a.j = an.a(this.a.J, this.a.getString(R.string.load_failed), -1);
                    this.a.j.show();
                }
            }
        }
    };
    private net.tsz.afinal.a.a<String> ad = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ MyAccountActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            if (!this.a.J.isFinishing()) {
                this.a.k.cancel();
                this.a.j = an.a(this.a.J, this.a.getString(R.string.load_failed), -1);
                this.a.j.show();
            }
        }

        public void a(String str) {
            try {
                if (TextUtils.isEmpty(str)) {
                    this.a.j = an.a(this.a.J, this.a.J.getString(R.string.bind_failed), -1);
                    this.a.j.show();
                } else {
                    int i = 0;
                    try {
                        i = Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                    }
                    if (i < 0) {
                        this.a.j = an.a(this.a.J, this.a.J.getString(R.string.bind_failed), -1);
                        this.a.j.show();
                    } else {
                        Map c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            this.a.j = an.a(this.a.J, this.a.J.getString(R.string.bind_failed), -1);
                            this.a.j.show();
                        } else {
                            String str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(j.c))) {
                                MobclickAgent.onEvent(this.a.J, "weibo_bind", "tencent_success");
                                this.a.m = (String) c.get("id");
                                this.a.p.a(this.a.m, c);
                                ai.a(this.a.J, this.a.m, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                ai.a(this.a.J, (String) c.get("weixin_uid"));
                                this.a.d();
                                this.a.j = an.a(this.a.J, this.a.J.getString(R.string.bind_successed), -1);
                                this.a.j.show();
                            } else {
                                this.a.j = an.a(this.a.J, str2, -1);
                                this.a.j.show();
                            }
                        }
                    }
                }
                this.a.k.cancel();
            } catch (Exception e2) {
                e2.printStackTrace();
                aa.e("", "ljj-->" + e2.toString());
                if (!this.a.J.isFinishing()) {
                    this.a.j = an.a(this.a.J, this.a.getString(R.string.load_failed), -1);
                    this.a.j.show();
                }
            }
        }
    };
    TextView b;
    ImageView c;
    TextView d;
    TextView e;
    TextView f;
    TextView g;
    TextView h;
    TextView i;
    Toast j;
    Dialog k;
    int l;
    String m;
    String n;
    boolean o = false;
    m p;
    UserItem q;
    String r;
    String s;
    n t;
    com.budejie.www.http.a u;
    com.budejie.www.http.m v;
    HashMap<String, String> w;
    com.budejie.www.util.f x;
    SharedPreferences y;
    RelativeLayout z;

    class a extends DatePickerDialog {
        final /* synthetic */ MyAccountActivity a;

        public a(MyAccountActivity myAccountActivity, Context context, int i, OnDateSetListener onDateSetListener, int i2, int i3, int i4) {
            this.a = myAccountActivity;
            super(context, i, onDateSetListener, i2, i3, i4);
        }

        protected void onStop() {
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_myaccount);
        this.J = this;
        this.P = new b(this.J);
        this.Z = new f(this.J);
        this.aa = new l(this.J);
        g();
        a();
        b();
        c();
    }

    protected void onResume() {
        super.onResume();
        d();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 4) {
            return super.dispatchKeyEvent(keyEvent);
        }
        this.J.setResult(719);
        this.J.finish();
        return true;
    }

    private void g() {
        this.ab = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.ab.registerApp("wx592fdc48acfbe290");
    }

    public void a() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.K = (Button) findViewById(R.id.title_left_btn);
        this.M = (LinearLayout) findViewById(R.id.left_layout);
        this.N = (RelativeLayout) findViewById(R.id.qqOauthLayout);
        this.L = (TextView) findViewById(R.id.title_center_txt);
        this.a = (TextView) findViewById(R.id.userNameTv);
        this.b = (TextView) findViewById(R.id.user_sexTv);
        this.c = (ImageView) findViewById(R.id.userProfile);
        this.d = (TextView) findViewById(R.id.sina_tips);
        this.e = (TextView) findViewById(R.id.tencent_tips);
        this.f = (TextView) findViewById(R.id.qzone_tips);
        this.g = (TextView) findViewById(R.id.sinaBind);
        this.h = (TextView) findViewById(R.id.tenctBind);
        this.i = (TextView) findViewById(R.id.qzoneBind);
        this.Q = findViewById(R.id.person_ready);
        this.z = (RelativeLayout) findViewById(R.id.account_phone_layout);
        this.A = (TextView) findViewById(R.id.phone_tips);
        this.B = (TextView) findViewById(R.id.phoneBind);
        this.C = (RelativeLayout) findViewById(R.id.account_weixin_layout);
        this.D = (TextView) findViewById(R.id.weixin_tips);
        this.E = (TextView) findViewById(R.id.weixinBind);
        this.R = (TextView) findViewById(R.id.user_proTv);
        this.S = (TextView) findViewById(R.id.birthdayTv);
        this.T = (TextView) findViewById(R.id.educationTv);
        this.U = (TextView) findViewById(R.id.qqTv);
        this.V = (RelativeLayout) findViewById(R.id.account_alter_layout);
        this.K.setVisibility(0);
        this.K.setOnClickListener(this.F);
        this.M.setOnClickListener(this.F);
        this.M.setVisibility(0);
        this.L.setText(R.string.person_title_content);
        this.y = getSharedPreferences("weiboprefer", 0);
        this.p = new m(this);
        this.O = new d(this);
        this.t = new n(this);
        this.u = new com.budejie.www.http.a(this, this);
        this.v = new com.budejie.www.http.m();
        this.x = new com.budejie.www.util.f();
        this.k = new Dialog(this, R.style.dialogTheme);
        this.k.setContentView(R.layout.loaddialog);
        CharSequence configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "cmt_share_qzone_new");
        if (!TextUtils.isEmpty(configParams) && Constants.SERVICE_SCOPE_FLAG_VALUE.equals(configParams)) {
            this.N.setVisibility(0);
        }
    }

    public void alterPsw$Click(View view) {
        Intent intent = new Intent(this.J, PhoneRetrievePasswordActivity.class);
        intent.putExtra("source", "MyAccountActivity");
        startActivityForResult(intent, 8373);
    }

    public void b() {
        this.m = this.y.getString("id", "");
        this.q = this.p.e(this.m);
        if (this.q != null) {
            this.a.setText(this.q.getName());
            String sex = this.q.getSex();
            if ("m".equals(sex)) {
                this.b.setText(R.string.man);
            } else if ("f".equals(sex)) {
                this.b.setText(R.string.women);
            }
            CharSequence instroduce = this.q.getInstroduce();
            if (TextUtils.isEmpty(instroduce)) {
                this.R.setText("这家伙很懒，神马都木有写有木有！！！");
            } else {
                this.R.setText(instroduce);
            }
            if (!TextUtils.isEmpty(this.q.getBirthday())) {
                this.S.setText(this.q.getBirthday());
            }
            if (TextUtils.isEmpty(this.q.getDegree())) {
                this.T.setText("");
            } else {
                instroduce = "";
                int i = 0;
                try {
                    i = Integer.parseInt(this.q.getDegree());
                } catch (Exception e) {
                }
                switch (i) {
                    case 1:
                        instroduce = "在读学生";
                        break;
                    case 2:
                        instroduce = "高中毕业";
                        break;
                    case 3:
                        instroduce = "专科毕业";
                        break;
                    case 4:
                        instroduce = "本科毕业";
                        break;
                }
                this.T.setText(instroduce);
            }
            if (!TextUtils.isEmpty(this.q.getQq())) {
                this.U.setText(this.q.getQq());
            }
        }
    }

    public void c() {
        if (this.q != null) {
            String profile = this.q.getProfile();
            if (TextUtils.isEmpty(profile)) {
                this.c.setImageResource(R.drawable.head_portrait);
            } else {
                this.x.a(this.J, profile, new com.budejie.www.util.f.a(this) {
                    final /* synthetic */ MyAccountActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(Drawable drawable, String str) {
                        this.a.c.setImageDrawable(drawable);
                    }
                });
            }
        }
    }

    public void d() {
        this.l = 0;
        this.m = this.y.getString("id", "");
        this.w = this.t.a(this.m);
        if ("null".equals(this.w.get("weibo_uid")) || TextUtils.isEmpty((CharSequence) this.w.get("weibo_uid"))) {
            this.d.setText(R.string.sina_unbind);
            this.g.setText(R.string.person_at_once_bind);
            this.g.setEnabled(true);
        } else if (an.a(Long.parseLong(this.p.c(this.m)))) {
            this.d.setText(R.string.person_sina_bind);
            this.g.setText(R.string.person_cancel_bind);
            this.g.setEnabled(false);
            this.l++;
        } else {
            this.d.setText(R.string.sina_unbind);
            this.g.setText(R.string.person_at_once_bind);
            this.g.setEnabled(true);
        }
        if ("null".equals(this.w.get("qq_uid")) || TextUtils.isEmpty((CharSequence) this.w.get("qq_uid"))) {
            this.e.setText(R.string.tecent_unbind);
            this.h.setText(R.string.person_at_once_bind);
            this.h.setEnabled(true);
        } else {
            this.e.setText(R.string.person_tecent_bind);
            this.h.setText(R.string.person_cancel_bind);
            this.h.setEnabled(false);
            this.l++;
        }
        if (!this.w.containsKey("qzone_uid") || TextUtils.isEmpty((CharSequence) this.w.get("qzone_uid"))) {
            this.f.setText(R.string.qzone_unbind);
            this.i.setText(R.string.person_at_once_bind);
            this.i.setEnabled(true);
        } else {
            this.f.setText(R.string.person_qzone_bind);
            this.i.setText(R.string.person_cancel_bind);
            this.i.setEnabled(false);
            this.l++;
        }
        if (this.t.d(this.J)) {
            this.z.setVisibility(8);
            this.A.setText(R.string.phone_bind);
            this.B.setText(R.string.person_cancel_bind);
            this.B.setEnabled(false);
            this.V.setVisibility(0);
            this.l++;
        } else {
            this.z.setVisibility(0);
            this.A.setText(R.string.phone_unbind);
            this.B.setText(R.string.person_at_once_bind);
            this.V.setVisibility(8);
            this.B.setEnabled(true);
        }
        if (ai.d(this.J)) {
            this.D.setText(R.string.weixin_bind);
            this.E.setText(R.string.person_cancel_bind);
            this.E.setEnabled(false);
            this.l++;
            return;
        }
        this.D.setText(R.string.weixin_unbind);
        this.E.setText(R.string.person_at_once_bind);
        this.E.setEnabled(true);
    }

    public void btnExit$Click(View view) {
        if (an.a((Context) this)) {
            h();
            this.P.d("0");
            this.P.d("1");
            new g(this).a();
            this.O.b();
            this.Z.b();
            this.aa.b();
            ai.a(this.J, "");
            return;
        }
        Toast.makeText(this.J, getResources().getString(R.string.nonet), 0).show();
    }

    private void h() {
        this.u.a(AlibcConstants.PF_ANDROID, new net.tsz.afinal.a.a<String>(this) {
            final /* synthetic */ MyAccountActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void onStart() {
                super.onStart();
                if (this.a.k != null && !this.a.k.isShowing()) {
                    this.a.k.show();
                }
            }

            public void a(String str) {
                if (this.a.k != null && this.a.k.isShowing()) {
                    this.a.k.cancel();
                }
                aa.a("wuzhenlin", str);
                if (TextUtils.isEmpty(str)) {
                    this.a.j = an.a(this.a.J, this.a.getString(R.string.exit_failure), -1);
                    this.a.j.show();
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
                    if (this.a.O.d()) {
                        this.a.j();
                    } else {
                        this.a.k();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    this.a.j = an.a(this.a.J, this.a.getString(R.string.exit_failure), -1);
                    this.a.j.show();
                }
            }

            public void onFailure(Throwable th, int i, String str) {
                if (this.a.k != null && this.a.k.isShowing()) {
                    this.a.k.cancel();
                }
                this.a.j = an.a(this.a.J, this.a.getString(R.string.exit_failure), -1);
                this.a.j.show();
            }
        });
    }

    public void btnPhoneBind$Click(View view) {
        if (this.t.d(this.J)) {
            Intent intent = new Intent(this.J, PhoneNumChangeActivity.class);
            intent.putExtra("phone", this.q.getPhone());
            intent.putExtra("isChangePhone", true);
            intent.putExtra("source", "MyAccountActivity");
            this.J.startActivityForResult(intent, 8734);
            return;
        }
        intent = new Intent(this.J, PhoneNumBindingActivity.class);
        intent.putExtra("nike_name", this.q.getName());
        intent.putExtra("source", "MyAccountActivity");
        intent.putExtra("isChangePhone", false);
        startActivityForResult(intent, 4257);
    }

    public void btnQzoneBind$Click(View view) {
        if (!this.t.c(this.J)) {
            a(com.tencent.connect.common.Constants.SOURCE_QZONE);
        } else if (this.l == 1) {
            Toast.makeText(this, "当前账户只绑定了一个QQ空间，暂不能解除绑定", 0).show();
        } else {
            b(com.tencent.connect.common.Constants.SOURCE_QZONE);
        }
    }

    public void btnWeixinBind$Click(View view) {
        if (!ai.d(this.J)) {
            a("weixin");
        } else if (this.l == 1) {
            Toast.makeText(this, "当前账户只绑定了一个微博，暂不能解除绑定", 0).show();
        } else {
            b("weixin");
        }
    }

    public void btnTencentBind$Click(View view) {
        if (!this.t.b(this.J)) {
            a("tenct");
        } else if (this.l == 1) {
            Toast.makeText(this, "当前账户只绑定了一个微博，暂不能解除绑定", 0).show();
        } else {
            b("tencent");
        }
    }

    public void btnSinaBind$Click(View view) {
        if (!this.t.a(this.J)) {
            a("sina");
        } else if (this.l == 1) {
            Toast.makeText(this, "当前账户只绑定了一个微博，暂不能解除绑定", 0).show();
        } else {
            b("sina");
        }
    }

    public void btnSex$Click(View view) {
        d("sex");
    }

    public void btnName$Click(View view) {
        Intent intent = new Intent(this, EditUserNameAct.class);
        if (this.q != null) {
            intent.putExtra("name", this.q.getName());
        }
        startActivityForResult(intent, 718);
    }

    public void btnProfile$Click(View view) {
        d("photo");
    }

    public void btnPro$Click(View view) {
        Intent intent = new Intent(this, EditUserIntroduceAct.class);
        intent.putExtra(SocialConstants.PARAM_APP_DESC, this.q.getInstroduce());
        startActivityForResult(intent, 726);
    }

    public void btnBirthday$Click(View view) {
        this.W = true;
        this.X = Calendar.getInstance();
        showDialog(1);
    }

    @TargetApi(11)
    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case 1:
                Dialog aVar = new a(this, this, 3, this.G, this.X.get(1), this.X.get(2), this.X.get(5));
                if (VERSION.SDK_INT < 11) {
                    return aVar;
                }
                aVar.getDatePicker().setMinDate(c.a("1970-01-01 00:00:00"));
                aVar.getDatePicker().setMaxDate(System.currentTimeMillis());
                return aVar;
            default:
                return super.onCreateDialog(i);
        }
    }

    public void btnEducation$Click(View view) {
        final Dialog dialog = new Dialog(this.J, R.style.DialogTheme_CreateUgc);
        LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) this.J.getSystemService("layout_inflater")).inflate(R.layout.alert_item_latout, null);
        linearLayout.setMinimumWidth(10000);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.content_list);
        linearLayout2.setBackgroundResource(com.budejie.www.util.j.aC);
        Button button = (Button) linearLayout.findViewById(R.id.btn_cancel);
        button.setBackgroundResource(com.budejie.www.util.j.aC);
        button.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyAccountActivity b;

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelOffset(R.dimen.list_item_writer_profile));
        String[] strArr = new String[]{getResources().getString(R.string.education_1), getResources().getString(R.string.education_2), getResources().getString(R.string.education_3), getResources().getString(R.string.education_4)};
        for (int i = 0; i < strArr.length; i++) {
            CharSequence charSequence = strArr[i];
            View textView = new TextView(this.J);
            textView.setGravity(17);
            textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.item_title_name_color));
            textView.setText(charSequence);
            textView.setTextSize(2, 18.0f);
            textView.setBackgroundResource(com.budejie.www.util.j.aC);
            textView.setTag(Integer.valueOf(i));
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ MyAccountActivity b;

                public void onClick(View view) {
                    dialog.dismiss();
                    this.b.H.sendEmptyMessage(925);
                    this.b.r = (((Integer) view.getTag()).intValue() + 1) + "";
                    this.b.u.a(this.b.m, "", "", "", this.b.r, "", "", 1959);
                }
            });
            textView.setLayoutParams(layoutParams);
            if (i == 0) {
                linearLayout2.addView(textView);
            } else {
                View imageView = new ImageView(this.J);
                imageView.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.divider_horizontal_bg));
                imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelOffset(R.dimen.divide_line_height)));
                linearLayout2.addView(imageView);
                linearLayout2.addView(textView);
            }
        }
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.x = 0;
        attributes.y = -1000;
        attributes.gravity = 80;
        dialog.onWindowAttributesChanged(attributes);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(linearLayout);
        dialog.show();
    }

    public void btnQq$Click(View view) {
        Intent intent = new Intent(this, EditUserNameAct.class);
        intent.putExtra("name", this.q.getQq());
        intent.putExtra("page_type", com.tencent.connect.common.Constants.SOURCE_QQ);
        startActivityForResult(intent, 55);
    }

    private void d(String str) {
        p.a(this.J, str, new p.c(this) {
            final /* synthetic */ MyAccountActivity a;

            {
                this.a = r1;
            }

            public void a(String str) {
                if (!TextUtils.isEmpty(str)) {
                    if ("男".equals(str)) {
                        this.a.n = "m";
                        this.a.H.sendEmptyMessage(925);
                        this.a.u.a(this.a.m, this.a.n, "", "", "", "", "", 959);
                    } else if ("女".equals(str)) {
                        this.a.n = "f";
                        this.a.H.sendEmptyMessage(925);
                        this.a.u.a(this.a.m, this.a.n, "", "", "", "", "", 959);
                    } else if ("拍照".equals(str)) {
                        this.a.i();
                        this.a.f();
                    } else if ("从相册中选择".equals(str)) {
                        this.a.i();
                        this.a.e();
                    }
                }
            }
        });
    }

    private void i() {
        this.j = an.a(this.J, this.J.getString(R.string.upload_portrait_hint), -1);
        this.j.show();
    }

    public void a(String str) {
        if (com.tencent.connect.common.Constants.SOURCE_QZONE.equals(str)) {
            MobclickAgent.onEvent((Context) this, "weibo_bind", "qzone_start");
            if (this.mTencent == null) {
                this.mTencent = Tencent.createInstance("100336987", this);
            }
            this.mTencent.login(this.J, "get_simple_userinfo,get_user_profile,add_share,add_topic,list_album,upload_pic,add_album", (IUiListener) this);
        } else if ("sina".equals(str)) {
            MobclickAgent.onEvent((Context) this, "weibo_bind", "sina_start");
            if (this.mSsoHandler == null) {
                this.mSsoHandler = new com.sina.weibo.sdk.auth.a.a(this);
            }
            this.mSsoHandler.a((com.sina.weibo.sdk.auth.d) this);
        } else if ("tenct".equals(str)) {
            MobclickAgent.onEvent((Context) this, "weibo_bind", "tencent_start");
            auth(Long.valueOf(Util.getConfig().getProperty("APP_KEY")).longValue(), Util.getConfig().getProperty("APP_KEY_SEC"));
        } else if ("weixin".equals(str)) {
            MobclickAgent.onEvent((Context) this, "weixin_bind", "weixin_start");
            WXEntryActivity.a(this.J, this.ab);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Tencent.onActivityResultData(i, i2, intent, this);
        if (i2 == 711) {
            this.H.sendEmptyMessage(925);
            bindTencent();
        } else if (i2 == -1) {
            if (i == 32973) {
                if (this.mSsoHandler != null) {
                    this.mSsoHandler.a(i, i2, intent);
                }
            } else if (i == 714) {
                if (intent != null) {
                    Uri data = intent.getData();
                    if (data.toString().startsWith(UriUtil.LOCAL_FILE_SCHEME)) {
                        p.a(this.J, data.getPath(), (int) R$styleable.Theme_Custom_last_refresh_item_text_theme, (int) R$styleable.Theme_Custom_last_refresh_item_text_theme);
                        return;
                    }
                    Cursor query = getContentResolver().query(data, null, null, null, null);
                    if (query != null) {
                        query.moveToFirst();
                        p.a(this.J, query.getString(query.getColumnIndex("_data")), (int) R$styleable.Theme_Custom_last_refresh_item_text_theme, (int) R$styleable.Theme_Custom_last_refresh_item_text_theme);
                    }
                }
            } else if (i == 716) {
                p.a(this.J, Environment.getExternalStorageDirectory() + "/camera.jpg", (int) R$styleable.Theme_Custom_last_refresh_item_text_theme, (int) R$styleable.Theme_Custom_last_refresh_item_text_theme);
            } else if (i == 715) {
                if (intent != null) {
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        try {
                            r4 = this.u.a(new BitmapDrawable((Bitmap) extras.getParcelable("data")));
                            r3 = "http://api.budejie.com/api/api_open.php?c=user&a=modifyheader";
                            r6 = new ArrayList();
                            r6.add(new BasicNameValuePair("id", this.m));
                            an.a(r3, r6, this.J);
                            this.v.a(this.J, true, r3, r4, "jpg", r6, this.H, SystemMessageConstants.JS_BRIDGE_ANNOTATION_NOT_PRESENT);
                        } catch (OutOfMemoryError e) {
                            aa.e(this.I, "OutOfMemoryError , " + e.toString());
                        }
                    }
                }
            } else if (i == 717) {
                File file = new File(Environment.getExternalStorageDirectory() + "/camera.jpg");
                if (file.exists()) {
                    try {
                        r4 = this.u.a(Drawable.createFromPath(file.getPath()));
                        r3 = "http://api.budejie.com/api/api_open.php?c=user&a=modifyheader";
                        r6 = new ArrayList();
                        r6.add(new BasicNameValuePair("id", this.m));
                        an.a(r3, r6, this.J);
                        this.v.a(this.J, true, r3, r4, "jpg", r6, this.H, SystemMessageConstants.JS_BRIDGE_ANNOTATION_NOT_PRESENT);
                    } catch (OutOfMemoryError e2) {
                        aa.e(this.I, "OutOfMemoryError , " + e2.toString());
                    }
                }
            } else if (i == 728) {
                r4 = intent.getStringExtra("image-path");
                if (r4 != null) {
                    r6 = new ArrayList();
                    r6.add(new BasicNameValuePair("id", this.m));
                    an.a(r6, this.J);
                    i();
                    this.v.a(this.J, true, "http://api.budejie.com/api/api_open.php?c=user&a=modifyheader", r4, "jpg", r6, this.H, SystemMessageConstants.JS_BRIDGE_ANNOTATION_NOT_PRESENT);
                }
            } else if (i != 8373 && i == 8734) {
                this.j = an.a(this.J, this.J.getString(R.string.change_phone_success), -1);
                this.j.show();
                String stringExtra = intent.getStringExtra("newPhone");
                this.p.a("phone", stringExtra, this.m);
                this.q.setPhone(stringExtra);
            }
        } else if (i2 == 718) {
            b();
        } else if (i2 == 726) {
            b();
        } else if (i2 == 55) {
            b();
        } else if (i2 == 6383 && i == 4257) {
            this.j = an.a(this.J, this.J.getString(R.string.bind_successed), -1);
            this.j.show();
            this.V.setVisibility(0);
        }
    }

    public void a(String str, String str2) {
        this.H.sendEmptyMessage(925);
        if (TextUtils.isEmpty(str2)) {
            this.j = an.a(this.J, "解除失败", -1);
            this.j.show();
            return;
        }
        HashMap l = z.l(str2);
        if (l.get(j.c) == null || TextUtils.isEmpty((CharSequence) l.get(j.c))) {
            this.j = an.a(this.J, this.J.getString(R.string.person_edit_data_faild), -1);
            this.j.show();
        } else if ("0".equals(l.get(j.c))) {
            this.j = an.a(this.J, getString(R.string.person_unbind_successed), -1);
            this.j.show();
            this.p.a(str, "", this.m);
            d();
        } else {
            this.j = an.a(this.J, (String) l.get("msg"), -1);
            this.j.show();
        }
    }

    public void e() {
        try {
            Intent intent = new Intent("android.intent.action.PICK");
            intent.setDataAndType(Media.INTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, 714);
        } catch (Exception e) {
            an.a(this.J, getString(R.string.no_available_album), -1).show();
        }
    }

    public void f() {
        if (com.budejie.www.activity.video.a.a()) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "camera.jpg")));
            try {
                startActivityForResult(intent, 716);
                return;
            } catch (Exception e) {
                this.j = an.a(this.J, this.J.getString(R.string.no_camera), -1);
                this.j.show();
                return;
            }
        }
        this.j = an.a(this.J, this.J.getString(R.string.no_sdcard), -1);
        this.j.show();
    }

    public void a(int i, String str) {
        if (i == 959) {
            this.H.sendMessage(this.H.obtainMessage(960, str));
        } else if (i == 1959) {
            this.H.sendMessage(this.H.obtainMessage(1960, str));
        } else if (i == 2959) {
            this.H.sendMessage(this.H.obtainMessage(2960, str));
        }
    }

    public void a(int i) {
        if (i == 959) {
            this.H.sendEmptyMessage(961);
        } else if (i == 1959) {
            this.H.sendEmptyMessage(1961);
        } else if (i == 2959) {
            this.H.sendEmptyMessage(2961);
        }
    }

    public void b(final String str) {
        Builder builder = new Builder(this);
        builder.setTitle(R.string.system_tip);
        builder.setMessage(R.string.person_dialog_text);
        builder.setPositiveButton(R.string.update_btn_sure, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ MyAccountActivity b;

            public void onClick(DialogInterface dialogInterface, int i) {
                if ("sina".equals(str)) {
                    this.b.H.sendEmptyMessage(925);
                    this.b.t.a("sina", this.b.m, this.b.H, 953);
                }
                if ("tencent".equals(str)) {
                    this.b.H.sendEmptyMessage(925);
                    this.b.t.a("qq", this.b.m, this.b.H, 955);
                }
                if (com.tencent.connect.common.Constants.SOURCE_QZONE.equals(str)) {
                    this.b.mTencent.logout(this.b.J);
                    this.b.H.sendEmptyMessage(925);
                    this.b.t.a(com.tencent.connect.common.Constants.SOURCE_QZONE, this.b.m, this.b.H, 954);
                }
                if ("phone".equals(str)) {
                    this.b.H.sendEmptyMessage(925);
                    this.b.t.a("phone", this.b.m, this.b.H, 1003);
                }
                if ("weixin".equals(str)) {
                    NetWorkUtil netWorkUtil = BudejieApplication.a;
                    RequstMethod requstMethod = RequstMethod.GET;
                    String p = com.budejie.www.http.j.p();
                    com.budejie.www.http.j jVar = new com.budejie.www.http.j();
                    netWorkUtil.a(requstMethod, p, com.budejie.www.http.j.t(this.b.J, "weixin"), this.b.ac);
                }
            }
        });
        builder.setNegativeButton(R.string.update_btn_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ MyAccountActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        super.onComplete(jSONObject);
        if (!this.k.isShowing()) {
            this.H.sendEmptyMessage(925);
        }
        HashMap a = z.a(jSONObject);
        if (a != null && a.size() != 0) {
            this.y.edit().putString("openid", (String) a.get("qzone_uid")).putString("qzone_token", (String) a.get("qzone_token")).putString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN, (System.currentTimeMillis() + (Long.parseLong((String) a.get(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN)) * 1000)) + "").commit();
            this.m = this.y.getString("id", "");
            this.t.a((String) a.get("qzone_uid"), this.m, (String) a.get("qzone_token"), 929, this.H);
        }
    }

    public void onError(UiError uiError) {
        super.onError(uiError);
        if (!this.k.isShowing()) {
            this.H.sendEmptyMessage(925);
        }
        Toast.makeText(this.J, "code:" + uiError.errorCode + ", msg:" + uiError.errorMessage + ", detail:" + uiError.errorDetail, 0).show();
        this.H.sendEmptyMessage(925);
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        if (!this.k.isShowing()) {
            this.H.sendEmptyMessage(925);
        }
        try {
            this.m = this.y.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this.J, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.t.a(mAccessToken, this.m, 924, this.H);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }

    public void onrefreshTheme() {
        this.L.setTextColor(getResources().getColor(com.budejie.www.util.j.b));
        onRefreshTitleFontTheme(this.K, true);
    }

    private void j() {
        Builder builder = new Builder(this);
        builder.setTitle(R.string.system_tip);
        builder.setMessage(R.string.clear_collect);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ MyAccountActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.k();
                this.a.O.e();
                this.a.j = an.a(this.a.J, this.a.J.getString(R.string.clear_collect_successed), -1);
                this.a.j.show();
            }
        });
        builder.setNegativeButton(R.string.goback, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ MyAccountActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.O.c();
                this.a.k();
            }
        });
        builder.show();
    }

    private void k() {
        ai.a(this.J, "", "");
        if (this.mTencent != null) {
            this.mTencent.logout(this.J);
        }
        CookieStore a = NetWorkUtil.a(this.J);
        if (a != null) {
            a.clear();
        }
        CookieManager.getInstance().removeAllCookie();
        BudejieApplication.a.a("cookie", "");
        this.y.edit().putString("sex", "").commit();
        this.y.edit().putString("collect_version", "").commit();
        this.y.edit().putString("education", "").commit();
        finish();
        if (PersonalProfileActivity.h != null) {
            PersonalProfileActivity.h.finish();
        }
        this.J.sendBroadcast(new Intent("android.hide.sister.news.NOTIFYTIPS"));
        this.J.sendBroadcast(new Intent("android.hide.sister.my.NOTIFYTIPS"));
        sendBroadcast(new Intent("android.hide.sister.my.HANDLER_HIDE_MY_REDPACKET_TIPS"));
        this.J.sendBroadcast(new Intent("android.budejie.more.UPDATE_RED_PACKET_HIDE"));
        ((BudejieApplication) getApplication()).g().ai.a("");
        this.P.a("subscribe_Label");
        this.P.b("recommend_Label");
    }

    public void bindTencent() {
        this.m = this.y.getString("id", "");
        String sharePersistent = Util.getSharePersistent(this.J, "ACCESS_TOKEN");
        this.t.a(Util.getSharePersistent(this.J, "NAME"), sharePersistent, Util.getSharePersistent(this.J, "OPEN_ID"), this.m, 923, this.H);
    }

    public void c(String str) {
        if (!this.J.isFinishing()) {
            this.k.show();
        }
        if (str != null) {
            try {
                this.m = this.y.getString("id", "");
                this.t.a(this.J, str, "weixin", this.ad);
            } catch (Exception e) {
                this.j = an.a(this.J, this.J.getString(R.string.weixin_shouquan_failed), -1);
                this.j.show();
            }
        }
    }
}
