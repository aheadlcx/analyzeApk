package com.budejie.www.activity;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.alipay.sdk.util.h;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.phonenumber.PhoneNumRegisterActivity;
import com.budejie.www.activity.phonenumber.PhoneNumberLoginActivity;
import com.budejie.www.activity.phonenumber.PhoneRetrievePasswordActivity;
import com.budejie.www.bean.SyncCollectItem;
import com.budejie.www.c.b;
import com.budejie.www.c.d;
import com.budejie.www.c.m;
import com.budejie.www.f.a;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.e;
import com.budejie.www.http.j;
import com.budejie.www.http.n;
import com.budejie.www.type.SendSMSVerifyResult;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.au;
import com.budejie.www.util.i;
import com.budejie.www.util.z;
import com.budejie.www.widget.erroredittext.SetErrorAbleEditText;
import com.budejie.www.widget.erroredittext.c;
import com.budejie.www.widget.erroredittext.g;
import com.budejie.www.wxapi.WXEntryActivity;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.stat.StatService;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.pro.x;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONObject;

public class OAuthWeiboActivity extends OauthWeiboBaseAct implements OnClickListener, a {
    private int A = 0;
    private final int B = 2;
    private StringBuffer C = new StringBuffer();
    private String D;
    private boolean E;
    private b F;
    private final String G = "86";
    private SetErrorAbleEditText H;
    private SetErrorAbleEditText I;
    private SetErrorAbleEditText J;
    private SetErrorAbleEditText K;
    private String L;
    private String M;
    private TextView N;
    private TextView O;
    private com.budejie.www.widget.erroredittext.b P;
    private com.budejie.www.widget.erroredittext.b Q;
    private com.budejie.www.widget.erroredittext.b R;
    private com.budejie.www.widget.erroredittext.b S;
    private TextView T;
    private TextView U;
    private Dialog V;
    private ImageView W;
    private TextView X;
    private TextView Y;
    private LinearLayout Z;
    OAuthWeiboActivity a;
    private LinearLayout aa;
    private int ab;
    private Dialog ac;
    private TextView ad;
    private TextView ae;
    private String af;
    private String ag = "";
    private Toast ah;
    private InputMethodManager ai;
    private j aj;
    private RelativeLayout ak;
    private RelativeLayout al;
    private TextView am;
    private TextView an;
    private IWXAPI ao;
    private OnClickListener ap = new OnClickListener(this) {
        final /* synthetic */ OAuthWeiboActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view.getId() == R.id.mycomment_delete_cancelBtn) {
                this.a.v.dismiss();
                this.a.w.b();
                this.a.l();
            } else if (view.getId() == R.id.mycomment_delete_sureBtn) {
                this.a.v.dismiss();
                ArrayList a = this.a.w.a();
                if (!(a == null || a.isEmpty())) {
                    for (int i = 0; i < a.size(); i++) {
                        if (i == a.size() - 1) {
                            this.a.C.append((String) a.get(i));
                        } else {
                            this.a.C.append((String) a.get(i)).append(",");
                        }
                    }
                    this.a.x.a("add", this.a.j, this.a.C.toString(), 971);
                }
                this.a.l();
            } else if (view == this.a.N) {
                if (this.a.E) {
                    MobclickAgent.onEvent(this.a.a, "E04-A01", "首次启动登陆引导页点击_手机号登录");
                }
                MobclickAgent.onEvent(this.a.a, "E04-A01", "手机号登录");
                if (this.a.P.a() && this.a.Q.a()) {
                    this.a.b(this.a.H.getText().toString(), this.a.I.getText().toString());
                }
            } else if (view == this.a.O) {
                if (this.a.E) {
                    MobclickAgent.onEvent(this.a.a, "首次启动登陆引导页点击", "手机号注册");
                    MobclickAgent.onEvent(this.a.a, "E04-A01", "首次启动登陆引导页点击_手机号注册");
                }
                MobclickAgent.onEvent(this.a.a, "E04-A01", "手机号注册");
                if (!this.a.S.a() || !this.a.R.a()) {
                    return;
                }
                if (an.a(this.a.a)) {
                    this.a.L = this.a.J.getText().toString();
                    this.a.M = this.a.K.getText().toString();
                    this.a.a("我们将发送", "验证码到这个号码", "+86" + this.a.L, "确定", "取消", this.a.t);
                    return;
                }
                an.a(this.a.a, this.a.a.getString(R.string.nonet), -1).show();
            } else if (view == this.a.T) {
                if (an.a(this.a.a)) {
                    this.a.startActivity(new Intent(this.a.a, PhoneRetrievePasswordActivity.class));
                    return;
                }
                an.a(this.a.a, this.a.a.getString(R.string.nonet), -1).show();
            } else if (view != this.a.U) {
            } else {
                if ("注册账号".equals(this.a.U.getText().toString())) {
                    this.a.b(this.a.Z, 0, -this.a.ab);
                    this.a.a(this.a.aa, this.a.ab, 0);
                    this.a.U.setText("已有账号?");
                } else if ("已有账号?".equals(this.a.U.getText().toString())) {
                    this.a.b(this.a.aa, 0, this.a.ab);
                    this.a.a(this.a.Z, -this.a.ab, 0);
                    this.a.U.setText("注册账号");
                }
            }
        }
    };
    private net.tsz.afinal.a.a<String> aq = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ OAuthWeiboActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
            this.a.c();
        }

        public void a(String str) {
            this.a.d();
            if (TextUtils.isEmpty(str)) {
                an.a(this.a.a, "注册失败", -1).show();
                return;
            }
            Map d = z.d(str);
            if (d != null && !d.isEmpty()) {
                String str2 = (String) d.get("msg");
                if (Constants.DEFAULT_UIN.equals((String) d.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY))) {
                    String str3 = (String) d.get("id");
                    this.a.k.a(str3, d);
                    ai.a(this.a.a, str3, com.ali.auth.third.core.model.Constants.SERVICE_SCOPE_FLAG_VALUE);
                    ai.a(this.a.a, (String) d.get("weixin_uid"));
                    com.budejie.www.util.b.a(this.a.a, str3);
                    com.budejie.www.util.b.a(this.a.a);
                    this.a.a(true);
                    this.a.b();
                    return;
                }
                an.a(this.a.a, str2, -1).show();
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.d();
        }
    };
    private net.tsz.afinal.a.a<String> ar = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ OAuthWeiboActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            aa.e("wuzhenlin", "onFailure " + str);
            if (!this.a.a.isFinishing()) {
                this.a.g.cancel();
                this.a.f = an.a(this.a.a, this.a.getString(R.string.load_failed), -1);
                this.a.f.show();
            }
        }

        public void a(String str) {
            boolean z = false;
            aa.e("wuzhenlin", "onSuccess json " + str);
            try {
                if (TextUtils.isEmpty(str)) {
                    this.a.f = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                    this.a.f.show();
                } else {
                    int parseInt;
                    try {
                        parseInt = Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                        parseInt = 0;
                    }
                    if (parseInt < 0) {
                        this.a.f = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                        this.a.f.show();
                    } else {
                        Map c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            this.a.f = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                            this.a.f.show();
                            z = true;
                        } else {
                            String str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                this.a.j = (String) c.get("id");
                                this.a.k.a(this.a.j, c);
                                ai.a(this.a.a, this.a.j, com.ali.auth.third.core.model.Constants.SERVICE_SCOPE_FLAG_VALUE);
                                ai.a(this.a.a, (String) c.get("weixin_uid"));
                                this.a.f = an.a(this.a.a, this.a.a.getString(R.string.bind_successed), -1);
                                this.a.f.show();
                                if ("1".equals((String) c.get("new_user"))) {
                                    this.a.g.cancel();
                                    BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.m(), this.a.at);
                                    BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.n(), this.a.au);
                                    Intent intent = new Intent(this.a.a, CreatePersonDataActivity.class);
                                    intent.putExtra("source", "third_party");
                                    intent.putExtra("user_info", c);
                                    this.a.a.startActivityForResult(intent, 5051);
                                    return;
                                }
                                com.budejie.www.util.b.a(this.a.a, this.a.j);
                                com.budejie.www.util.b.a(this.a.a);
                            } else {
                                an.a(this.a.a, str2, -1).show();
                            }
                            z = true;
                        }
                    }
                }
                this.a.g.cancel();
                this.a.a(z);
            } catch (Exception e2) {
                e2.printStackTrace();
                aa.e("", "ljj-->" + e2.toString());
                if (!this.a.a.isFinishing()) {
                    this.a.f = an.a(this.a.a, this.a.getString(R.string.load_failed), -1);
                    this.a.f.show();
                }
            }
        }
    };
    private net.tsz.afinal.a.a<String> as = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ OAuthWeiboActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.d();
        }

        public void a(String str) {
            super.onSuccess(str);
            this.a.d();
            SendSMSVerifyResult sendSMSVerifyResult = (SendSMSVerifyResult) z.a(str, SendSMSVerifyResult.class);
            if (sendSMSVerifyResult == null) {
                this.a.ah = an.a(this.a.a, "获取验证码失败", -1);
                this.a.ah.show();
            } else if (Constants.DEFAULT_UIN.equals(sendSMSVerifyResult.getCode())) {
                this.a.af = sendSMSVerifyResult.getSeq();
                this.a.ag = sendSMSVerifyResult.getExpirTime();
                i.a().a(this.a.af);
                i.a().b(this.a.L);
                i.a().c(this.a.M);
                BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.m(), this.a.at);
                BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.n(), this.a.au);
                Intent intent = new Intent(this.a.a, PhoneNumRegisterActivity.class);
                intent.putExtra("source", "phone_register");
                this.a.a.startActivityForResult(intent, 5050);
            } else if ("1004".equals(sendSMSVerifyResult.getCode())) {
                this.a.a(this.a.L, "", "", "", "", this.a.r);
            } else {
                String msg = sendSMSVerifyResult.getMsg();
                if (TextUtils.isEmpty(msg)) {
                    msg = "获取验证码失败";
                }
                this.a.ah = an.a(this.a.a, msg, -1);
                this.a.ah.show();
            }
        }
    };
    private net.tsz.afinal.a.a at = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ OAuthWeiboActivity a;

        {
            this.a = r1;
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
        }

        public void onSuccess(Object obj) {
            List h = z.h(obj.toString());
            if (h != null && h.size() > 0) {
                this.a.F.b("recommend_Label");
                this.a.F.b(h);
            }
        }
    };
    private net.tsz.afinal.a.a au = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ OAuthWeiboActivity a;

        {
            this.a = r1;
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
        }

        public void onSuccess(Object obj) {
            List i = z.i(obj.toString());
            if (i != null && i.size() > 0) {
                this.a.F.b("recommend_user");
                this.a.F.c(i);
            }
        }
    };
    LinearLayout b;
    Button c;
    RadioButton d;
    RadioButton e;
    Toast f;
    Dialog g;
    String h;
    String i;
    String j;
    m k;
    n l;
    SharedPreferences m;
    protected int n;
    protected int o;
    OnPageChangeListener p = new OnPageChangeListener(this) {
        final /* synthetic */ OAuthWeiboActivity a;

        {
            this.a = r1;
        }

        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            if (i == 0) {
                this.a.d.setChecked(true);
            } else if (i == 1) {
                this.a.e.setChecked(true);
            }
        }
    };
    Handler q = new Handler(this) {
        final /* synthetic */ OAuthWeiboActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = 0;
            int i2 = message.what;
            String str;
            int parseInt;
            Map c;
            String str2;
            Intent intent;
            if (i2 == 923) {
                boolean z;
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.f = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                    this.a.f.show();
                    MobclickAgent.onEvent(this.a.a, "E04-A01", "weibo_bind_tencent_faild");
                    z = false;
                } else {
                    try {
                        parseInt = Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                        parseInt = 0;
                    }
                    if (parseInt < 0) {
                        this.a.f = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                        this.a.f.show();
                        MobclickAgent.onEvent(this.a.a, "E04-A01", "weibo_bind_tencent_faild");
                        z = false;
                    } else {
                        MobclickAgent.onEvent(this.a.a, "E04-A01", "weibo_bind_tencent_success");
                        c = z.c(str);
                        if (!(c == null || c.isEmpty())) {
                            str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                this.a.j = (String) c.get("id");
                                this.a.k.a(this.a.j, c);
                                ai.a(this.a.a, this.a.j, com.ali.auth.third.core.model.Constants.SERVICE_SCOPE_FLAG_VALUE);
                                ai.a(this.a.a, (String) c.get("weixin_uid"));
                                if ("1".equals((String) c.get("new_user"))) {
                                    this.a.g.cancel();
                                    BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.m(), this.a.at);
                                    BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.n(), this.a.au);
                                    intent = new Intent(this.a.a, CreatePersonDataActivity.class);
                                    intent.putExtra("source", "third_party");
                                    intent.putExtra("user_info", c);
                                    this.a.a.startActivityForResult(intent, 5051);
                                    return;
                                }
                                com.budejie.www.util.b.a(this.a.a, this.a.j);
                                com.budejie.www.util.b.a(this.a.a);
                                z = true;
                            } else {
                                an.a(this.a.a, str2, -1).show();
                            }
                        }
                        z = true;
                    }
                }
                this.a.g.cancel();
                this.a.a(z);
            } else if (i2 == 924) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.f = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                    this.a.f.show();
                    MobclickAgent.onEvent(this.a.a, "E04-A01", "weibo_bind_sina_faild_resultnull");
                } else {
                    try {
                        parseInt = Integer.parseInt(str);
                    } catch (NumberFormatException e2) {
                        parseInt = 0;
                    }
                    if (parseInt < 0) {
                        this.a.f = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                        this.a.f.show();
                        MobclickAgent.onEvent(this.a.a, "E04-A01", "weibo_bind_sina_faild_resultminus:" + parseInt);
                    } else {
                        c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            MobclickAgent.onEvent(this.a.a, "E04-A01", "weibo_bind_sina_faild_mapnull");
                            r1 = true;
                        } else {
                            str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                MobclickAgent.onEvent(this.a.a, "E04-A01", "weibo_bind_sina_success");
                                this.a.j = (String) c.get("id");
                                this.a.k.a(this.a.j, c);
                                ai.a(this.a.a, this.a.j, com.ali.auth.third.core.model.Constants.SERVICE_SCOPE_FLAG_VALUE);
                                ai.a(this.a.a, (String) c.get("weixin_uid"));
                                if (OauthWeiboBaseAct.mAccessToken != null) {
                                    this.a.k.a(this.a.j, OauthWeiboBaseAct.mAccessToken.e());
                                }
                                if ("1".equals((String) c.get("new_user"))) {
                                    this.a.g.cancel();
                                    BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.m(), this.a.at);
                                    BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.n(), this.a.au);
                                    intent = new Intent(this.a.a, CreatePersonDataActivity.class);
                                    intent.putExtra("source", "third_party");
                                    intent.putExtra("user_info", c);
                                    this.a.a.startActivityForResult(intent, 5051);
                                    return;
                                }
                                com.budejie.www.util.b.a(this.a.a, this.a.j);
                                com.budejie.www.util.b.a(this.a.a);
                            } else {
                                an.a(this.a.a, str2, -1).show();
                            }
                            r1 = true;
                        }
                    }
                }
                this.a.g.cancel();
                this.a.a(r1);
            } else if (i2 == 929) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.f = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                    MobclickAgent.onEvent(this.a.a, "E04-A01", "weibo_bind_qzone_faild");
                    this.a.f.show();
                } else {
                    try {
                        parseInt = Integer.parseInt(str);
                    } catch (NumberFormatException e3) {
                        parseInt = 0;
                    }
                    if (parseInt < 0) {
                        MobclickAgent.onEvent(this.a.a, "E04-A01", "weibo_bind_qzone_faild");
                        this.a.f = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                        this.a.f.show();
                    } else {
                        c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            this.a.f = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                            this.a.f.show();
                            r1 = true;
                        } else {
                            str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                MobclickAgent.onEvent(this.a.a, "E04-A01", "weibo_bind_qzone_success");
                                this.a.j = (String) c.get("id");
                                this.a.k.a(this.a.j, c);
                                ai.a(this.a.a, this.a.j, com.ali.auth.third.core.model.Constants.SERVICE_SCOPE_FLAG_VALUE);
                                ai.a(this.a.a, (String) c.get("weixin_uid"));
                                this.a.f = an.a(this.a.a, this.a.a.getString(R.string.bind_successed), -1);
                                this.a.f.show();
                                if ("1".equals((String) c.get("new_user"))) {
                                    this.a.g.cancel();
                                    BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.m(), this.a.at);
                                    BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.n(), this.a.au);
                                    intent = new Intent(this.a.a, CreatePersonDataActivity.class);
                                    intent.putExtra("source", "third_party");
                                    intent.putExtra("user_info", c);
                                    this.a.a.startActivityForResult(intent, 5051);
                                    return;
                                }
                                com.budejie.www.util.b.a(this.a.a, this.a.j);
                                com.budejie.www.util.b.a(this.a.a);
                            } else {
                                an.a(this.a.a, str2, -1).show();
                            }
                            r1 = true;
                        }
                    }
                }
                this.a.g.cancel();
                this.a.a(r1);
            } else if (i2 == 925) {
                if (this.a.g.isShowing()) {
                    this.a.g.cancel();
                } else if (!this.a.a.isFinishing()) {
                    this.a.g.show();
                }
            } else if (i2 == 972) {
                SyncCollectItem n = z.n((String) message.obj);
                if (this.a.y.equals(n.getResult())) {
                    ArrayList successIds = n.getSuccessIds();
                    if (!successIds.isEmpty()) {
                        while (i < successIds.size()) {
                            this.a.w.b(n.getUid(), (String) successIds.get(i));
                            i++;
                        }
                    }
                } else if (this.a.z.equals(n.getResult())) {
                    this.a.A = this.a.A + 1;
                    if (this.a.A < 2 && !n.getSuccessIds().isEmpty()) {
                        ArrayList a = this.a.w.a();
                        if (a != null && !a.isEmpty()) {
                            this.a.C.delete(0, this.a.C.length() - 1);
                            while (i < a.size()) {
                                if (i == a.size() - 1) {
                                    this.a.C.append((String) a.get(i));
                                } else {
                                    this.a.C.append((String) a.get(i)).append(",");
                                }
                                i++;
                            }
                            this.a.x.a("add", this.a.j, this.a.C.toString(), 971);
                        }
                    }
                }
            }
        }
    };
    OnClickListener r = new OnClickListener(this) {
        final /* synthetic */ OAuthWeiboActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.ac.dismiss();
        }
    };
    OnClickListener s = new OnClickListener(this) {
        final /* synthetic */ OAuthWeiboActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
        }
    };
    OnClickListener t = new OnClickListener(this) {
        final /* synthetic */ OAuthWeiboActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (R.id.change == view.getId()) {
                this.a.c();
                this.a.ac.dismiss();
                this.a.d(this.a.L, "86");
            } else if (R.id.confirm == view.getId()) {
                this.a.ac.dismiss();
            }
        }
    };
    private RelativeLayout u;
    private Dialog v;
    private d w;
    private com.budejie.www.http.b x;
    private String y = "1";
    private String z = "0";

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.aj = new j();
        this.E = getIntent().getBooleanExtra("is_guide_login", false);
        setContentView(R.layout.new_login_register_layout);
        TypedArray obtainStyledAttributes = getTheme().obtainStyledAttributes(new int[]{16842926});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        obtainStyledAttributes = getTheme().obtainStyledAttributes(resourceId, new int[]{16842938, 16842939});
        this.n = obtainStyledAttributes.getResourceId(0, 0);
        this.o = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
        this.a = this;
        this.F = new b(this.a);
        this.x = com.budejie.www.http.b.a(this, this);
        f();
        j();
        if ("friends_recommend".equals(this.i)) {
            if ("0".equals(this.h)) {
                oauthSina$Click(null);
            } else if ("1".equals(this.h)) {
                oauthTenct$Click(null);
            }
        }
        e();
        com.budejie.www.http.i.a(getString(R.string.track_event_login_flow), getString(R.string.track_event_login_screen));
    }

    private void e() {
        this.ao = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.ao.registerApp("wx592fdc48acfbe290");
    }

    public void finish() {
        super.finish();
        overridePendingTransition(this.n, this.o);
    }

    private void f() {
        this.ak = (RelativeLayout) findViewById(R.id.phoneLayout);
        this.al = (RelativeLayout) findViewById(R.id.weixinLoginLayout);
        this.am = (TextView) findViewById(R.id.login_weixin_btn);
        this.an = (TextView) findViewById(R.id.login_qq_btn);
        this.am.setOnClickListener(this);
        this.an.setOnClickListener(this);
        this.u = (RelativeLayout) findViewById(R.id.oauthweibo_layout);
        this.u.setOnClickListener(this);
        this.W = (ImageView) findViewById(R.id.close_view);
        this.W.setOnClickListener(this);
        this.X = (TextView) findViewById(R.id.LoginBackTextView);
        this.X.setOnClickListener(this);
        this.Y = (TextView) findViewById(R.id.skip_text);
        if (this.E) {
            this.W.setVisibility(8);
            this.Y.setVisibility(0);
        } else {
            this.W.setVisibility(0);
            this.Y.setVisibility(8);
        }
        this.m = getSharedPreferences("weiboprefer", 0);
        this.l = new n(this);
        this.k = new m(this);
        this.w = new d(this);
        this.g = new Dialog(this, R.style.dialogTheme);
        this.g.setContentView(R.layout.loaddialog);
        this.i = getIntent().getStringExtra("source");
        this.h = getIntent().getStringExtra("type");
        this.Z = (LinearLayout) findViewById(R.id.phone_login);
        this.N = (TextView) findViewById(R.id.login_btn);
        this.N.setOnClickListener(this.ap);
        this.H = (SetErrorAbleEditText) findViewById(R.id.login_phonenumber);
        this.H.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ OAuthWeiboActivity a;

            {
                this.a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (z) {
                    this.a.H.setHintTextColor(this.a.getResources().getColor(R.color.login_input_text_color));
                } else {
                    this.a.H.setHintTextColor(this.a.getResources().getColor(R.color.login_input_text_color_no_focus));
                }
            }
        });
        this.I = (SetErrorAbleEditText) findViewById(R.id.login_password);
        this.I.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ OAuthWeiboActivity a;

            {
                this.a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (z) {
                    this.a.I.setHintTextColor(this.a.getResources().getColor(R.color.login_input_text_color));
                } else {
                    this.a.I.setHintTextColor(this.a.getResources().getColor(R.color.login_input_text_color_no_focus));
                }
            }
        });
        this.P = new com.budejie.www.widget.erroredittext.b(this.H);
        this.P.a(new c("手机号码不能为空"));
        this.P.a(new com.budejie.www.widget.erroredittext.i("手机号码不正确"));
        this.Q = new com.budejie.www.widget.erroredittext.b(this.I);
        this.Q.a(new c("密码不能为空"));
        this.Q.a(new com.budejie.www.widget.erroredittext.d("密码格式不正确"));
        this.aa = (LinearLayout) findViewById(R.id.phone_register);
        this.O = (TextView) findViewById(R.id.register_btn);
        this.O.setOnClickListener(this.ap);
        this.J = (SetErrorAbleEditText) findViewById(R.id.register_phonenumber);
        this.J.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ OAuthWeiboActivity a;

            {
                this.a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (z) {
                    this.a.J.setHintTextColor(this.a.getResources().getColor(R.color.login_input_text_color));
                } else {
                    this.a.J.setHintTextColor(this.a.getResources().getColor(R.color.login_input_text_color_no_focus));
                }
            }
        });
        this.K = (SetErrorAbleEditText) findViewById(R.id.register_password);
        this.K.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ OAuthWeiboActivity a;

            {
                this.a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (z) {
                    this.a.K.setHintTextColor(this.a.getResources().getColor(R.color.login_input_text_color));
                } else {
                    this.a.K.setHintTextColor(this.a.getResources().getColor(R.color.login_input_text_color_no_focus));
                }
            }
        });
        this.R = new com.budejie.www.widget.erroredittext.b(this.J);
        this.R.a(new c("手机号码不能为空"));
        this.R.a(new com.budejie.www.widget.erroredittext.i("手机号码不正确"));
        this.S = new com.budejie.www.widget.erroredittext.b(this.K);
        this.S.a(new c("密码不能为空"));
        this.S.a(new g("密码格式不正确"));
        this.T = (TextView) findViewById(R.id.loginToRetrievepsw);
        this.T.setOnClickListener(this.ap);
        this.U = (TextView) findViewById(R.id.loginToRegister);
        this.U.setOnClickListener(this.ap);
        this.ab = i.a().a(this.a);
        if (getIntent().getBooleanExtra("regist", false)) {
            this.U.setText("已有账号?");
            this.aa.setVisibility(0);
            this.Z.setVisibility(4);
        }
    }

    public void a(final View view, int i, int i2) {
        view.setVisibility(0);
        Animation translateAnimation = new TranslateAnimation((float) i, (float) i2, 0.0f, 0.0f);
        translateAnimation.setInterpolator(new OvershootInterpolator());
        translateAnimation.setDuration(350);
        translateAnimation.setStartOffset(150);
        translateAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ OAuthWeiboActivity b;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
            }
        });
        view.startAnimation(translateAnimation);
    }

    public void b(final View view, int i, int i2) {
        Animation translateAnimation = new TranslateAnimation((float) i, (float) i2, 0.0f, 0.0f);
        translateAnimation.setInterpolator(new OvershootInterpolator());
        translateAnimation.setDuration(350);
        translateAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ OAuthWeiboActivity b;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                view.setVisibility(4);
            }
        });
        view.startAnimation(translateAnimation);
    }

    public void OtherLoginLayout$Click(View view) {
        final Dialog dialog = new Dialog(this.a, R.style.DialogTheme_CreateUgc);
        LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(R.layout.alert_item_latout, null);
        linearLayout.setMinimumWidth(10000);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.content_list);
        linearLayout2.setBackgroundResource(com.budejie.www.util.j.aC);
        Button button = (Button) linearLayout.findViewById(R.id.btn_cancel);
        button.setBackgroundResource(com.budejie.www.util.j.aC);
        button.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ OAuthWeiboActivity b;

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelOffset(R.dimen.list_item_writer_profile));
        String[] strArr = new String[]{getResources().getString(R.string.login_tip_1), getResources().getString(R.string.login_tip_2), getResources().getString(R.string.login_tip_3)};
        for (int i = 0; i < strArr.length; i++) {
            CharSequence charSequence = strArr[i];
            View textView = new TextView(this.a);
            textView.setGravity(17);
            textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.item_title_name_color));
            textView.setText(charSequence);
            textView.setTextSize(2, 18.0f);
            textView.setBackgroundResource(com.budejie.www.util.j.aC);
            textView.setTag(Integer.valueOf(i));
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ OAuthWeiboActivity b;

                public void onClick(View view) {
                    dialog.dismiss();
                    switch (((Integer) view.getTag()).intValue()) {
                        case 0:
                            this.b.ak.setVisibility(0);
                            this.b.al.setVisibility(8);
                            return;
                        case 1:
                            this.b.i();
                            return;
                        case 2:
                            this.b.h();
                            return;
                        default:
                            return;
                    }
                }
            });
            textView.setLayoutParams(layoutParams);
            if (i == 0) {
                linearLayout2.addView(textView);
            } else {
                View imageView = new ImageView(this.a);
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

    private void g() {
        if (an.a(this.a)) {
            if (this.E) {
                aa.a("OAuthWeiboActivity", "首次启动登陆引导页点击-QQ");
                MobclickAgent.onEvent((Context) this, "E04-A01", "首次启动登陆引导页点击_QQ");
            } else if ("new".equals(this.D)) {
                MobclickAgent.onEvent(this, "newLoginUI_qzone_start");
            } else {
                MobclickAgent.onEvent((Context) this, "weibo_bind", "qzone_start");
            }
            if (this.mTencent == null) {
                this.mTencent = Tencent.createInstance("100336987", this);
            }
            this.mTencent.login(this.a, "get_simple_userinfo,get_user_profile,add_share,add_topic,list_album,upload_pic,add_album", (IUiListener) this);
            return;
        }
        an.a(this.a, this.a.getString(R.string.nonet), -1).show();
    }

    public void oauthTenct$Click(View view) {
        h();
    }

    public void oauthSina$Click(View view) {
        i();
    }

    private void h() {
        if (an.a(this.a)) {
            if (this.E) {
                aa.a("OAuthWeiboActivity", "首次启动登陆引导页点击-腾讯微博");
                MobclickAgent.onEvent((Context) this, "E04-A01", "首次启动登陆引导页点击_腾讯微博");
            } else if ("new".equals(this.D)) {
                MobclickAgent.onEvent(this, "newLoginUI_tencent_start");
            } else {
                MobclickAgent.onEvent((Context) this, "weibo_bind", "tencent_start");
            }
            auth(Long.valueOf(Util.getConfig().getProperty("APP_KEY")).longValue(), Util.getConfig().getProperty("APP_KEY_SEC"));
            return;
        }
        an.a(this.a, this.a.getString(R.string.nonet), -1).show();
    }

    private void i() {
        if (an.a(this.a)) {
            if (this.E) {
                aa.a("OAuthWeiboActivity", "首次启动登陆引导页点击-新浪");
                MobclickAgent.onEvent((Context) this, "E04-A01", "首次启动登陆引导页点击_新浪微博");
            } else if ("new".equals(this.D)) {
                MobclickAgent.onEvent(this, "newLoginUI_sina_start");
            } else {
                MobclickAgent.onEvent((Context) this, "weibo_bind", "sina_start");
            }
            if (this.mSsoHandler == null) {
                this.mSsoHandler = new com.sina.weibo.sdk.auth.a.a(this);
            }
            this.mSsoHandler.a((com.sina.weibo.sdk.auth.d) this);
            return;
        }
        an.a(this.a, this.a.getString(R.string.nonet), -1).show();
    }

    public void oauthPhone$Click(View view) {
        if (an.a(this.a)) {
            startActivityForResult(new Intent(this, PhoneNumberLoginActivity.class), 5050);
        } else {
            an.a(this.a, this.a.getString(R.string.nonet), -1).show();
        }
    }

    public void skip$Click(View view) {
        l();
        aa.a("OAuthWeiboActivity", "首次启动登陆引导页点击-跳过");
        MobclickAgent.onEvent((Context) this, "E04-A01", "首次启动登陆引导页点击_跳过");
    }

    public void a() {
        startActivity(new Intent(this, HomeGroup.class));
    }

    public void onClick(View view) {
        if (view == this.c || view == this.b) {
            finish();
            if ("new".equals(this.D)) {
                MobclickAgent.onEvent(this, "newLoginUI_return_count");
            }
        } else if (view == this.u) {
            if (this.ai != null) {
                this.ai.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return;
            }
            this.ai = (InputMethodManager) getSystemService("input_method");
            this.ai.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } else if (view == this.W) {
            this.ak.setVisibility(8);
            this.al.setVisibility(0);
        } else if (view == this.X) {
            finish();
        } else if (view == this.am) {
            WXEntryActivity.a(this.a, this.ao);
        } else if (view == this.an) {
            g();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        HomeGroup.a(this.a);
        if (i2 == 711) {
            this.q.sendEmptyMessage(925);
            bindTencent();
        }
        if ("1".equals(this.h)) {
            finish();
        }
        if (i2 == 5051) {
            a(true);
            com.budejie.www.util.b.a(this.a, this.j);
            com.budejie.www.util.b.a(this.a);
        }
    }

    public void onCancel() {
        if ("0".equals(this.h)) {
            finish();
        }
    }

    private void a(boolean z) {
        if (z) {
            b();
            com.budejie.www.http.i.a(getString(R.string.track_event_login_flow), getString(R.string.track_event_login_sccess));
        }
        Intent intent = new Intent();
        intent.putExtra("success", z);
        if ("tougao".equals(this.h) && StatisticCodeTable.MORE.equals(this.i)) {
            if (z) {
                this.a.setResult(123, intent);
                k();
            }
        } else if ("mytougao".equals(this.h) && StatisticCodeTable.MORE.equals(this.i)) {
            if (z) {
                this.a.setResult(124, intent);
                k();
            }
        } else if ("shenhe".equals(this.h) && StatisticCodeTable.MORE.equals(this.i)) {
            if (z) {
                this.a.setResult(125, intent);
                k();
            }
        } else if ("newsfeed".equals(this.h) && StatisticCodeTable.MORE.equals(this.i)) {
            if (z) {
                this.a.setResult(R$styleable.Theme_Custom_new_item_login_phone_bg, intent);
                k();
            }
        } else if ("personalprofile".equals(this.h) && StatisticCodeTable.MORE.equals(this.i)) {
            if (z) {
                this.a.setResult(R$styleable.Theme_Custom_new_item_login_phone_bg, intent);
                k();
            }
        } else if ("suggestedfollows".equals(this.h) && StatisticCodeTable.MORE.equals(this.i)) {
            if (z) {
                this.a.setResult(135, intent);
                k();
            }
        } else if ("login".equals(this.h) && StatisticCodeTable.MORE.equals(this.i)) {
            if (z) {
                this.a.setResult(R$styleable.Theme_Custom_new_item_shareFriend_text_color, intent);
                k();
            }
        } else if ("nearby".equals(this.h) && StatisticCodeTable.MORE.equals(this.i)) {
            if (z) {
                this.a.setResult(R$styleable.Theme_Custom_divider_horizontal_bg, intent);
                k();
            }
        } else if ("shenhe".equals(this.h) && "new".equals(this.i)) {
            if (z) {
                this.a.setResult(125, intent);
                k();
            }
        } else if ("mytougao".equals(this.h) && "mytougao".equals(this.i)) {
            if (z) {
                this.a.setResult(124, intent);
                k();
            }
        } else if ("person".equals(this.h) && StatisticCodeTable.MORE.equals(this.i)) {
            if (z) {
                this.a.setResult(126, intent);
                k();
            }
        } else if ("shenhe".equals(this.h) && "duanzi".equals(this.i)) {
            if (z) {
                this.a.setResult(127, intent);
                k();
            }
        } else if ("shenhe".equals(this.h) && "tiezi".equals(this.i)) {
            if (z) {
                this.a.setResult(128, intent);
                k();
            }
        } else if ("mycomment".equals(this.h) && StatisticCodeTable.MORE.equals(this.i)) {
            if (z) {
                this.a.setResult(129, intent);
                k();
            }
        } else if ("mynews".equals(this.h) && StatisticCodeTable.MORE.equals(this.i)) {
            if (z) {
                this.a.setResult(130, intent);
                k();
            }
        } else if ("publish".equals(this.h) && "voice".equals(this.i)) {
            if (z) {
                this.a.setResult(R$styleable.Theme_Custom_new_item_login_qq_bg, intent);
                k();
            }
        } else if ("publish".equals(this.h) && "tougao".equals(this.i)) {
            if (z) {
                this.a.setResult(R$styleable.Theme_Custom_new_item_login_tencent_bg, intent);
                k();
            }
        } else if (z) {
            k();
        }
    }

    private void j() {
        this.v = new Dialog(this.a, R.style.dialogTheme);
        View inflate = this.a.getLayoutInflater().inflate(R.layout.mycomment_delete_dialog, null);
        ((TextView) inflate.findViewById(R.id.mycomment_delete_text)).setText(getString(R.string.mycollect_sync_text));
        Button button = (Button) inflate.findViewById(R.id.mycomment_delete_cancelBtn);
        Button button2 = (Button) inflate.findViewById(R.id.mycomment_delete_sureBtn);
        button.setText(R.string.cancel);
        button.setOnClickListener(this.ap);
        button2.setOnClickListener(this.ap);
        this.v.setContentView(inflate);
        Window window = this.v.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = an.a(this.a, 300);
        window.setAttributes(attributes);
    }

    private void b(String str, String str2) {
        if (an.a(this.a)) {
            BudejieApplication.a.a(RequstMethod.GET, j.j(), c(str, str2), this.aq);
        } else {
            an.a(this.a, this.a.getString(R.string.nonet), -1).show();
        }
    }

    private net.tsz.afinal.a.b c(String str, String str2) {
        Map a = this.aj.a(this.a, new HashMap());
        a.put("phonenum", str);
        a.put("password", com.budejie.www.activity.phonenumber.a.a(str2));
        a.put("countrycode", "86");
        return e.a("/user/api/login", a);
    }

    public void b() {
        String str = "http://d.api.budejie.com/";
        String str2 = "http://api.budejie.com/";
        CookieSyncManager.createInstance(this.a);
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        Object b = NetWorkUtil.b(this.a);
        if (!TextUtils.isEmpty(b)) {
            String[] split = b.split(h.b);
            for (int i = 0; i < split.length; i++) {
                instance.setCookie(str, split[i]);
                instance.setCookie(str2, split[i]);
            }
        }
        CookieSyncManager.getInstance().sync();
    }

    public void c() {
        try {
            showDialog(1);
        } catch (RuntimeException e) {
        }
    }

    public void d() {
        if (this.V != null && this.V.isShowing()) {
            dismissDialog(1);
        }
    }

    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case 1:
                this.V = new Dialog(this, R.style.dialogTheme);
                this.V.setContentView(R.layout.loaddialog);
                this.V.setCanceledOnTouchOutside(true);
                this.V.setCancelable(true);
                return this.V;
            default:
                return super.onCreateDialog(i);
        }
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        super.onComplete(jSONObject);
        HashMap a = z.a(jSONObject);
        if (a != null && a.size() != 0) {
            if (!this.a.isFinishing()) {
                this.g.show();
            }
            this.m.edit().putString("openid", (String) a.get("qzone_uid")).putString("qzone_token", (String) a.get("qzone_token")).putString(Constants.PARAM_EXPIRES_IN, (System.currentTimeMillis() + (Long.parseLong((String) a.get(Constants.PARAM_EXPIRES_IN)) * 1000)) + "").commit();
            this.j = this.m.getString("id", "");
            this.l.a((String) a.get("qzone_uid"), this.j, (String) a.get("qzone_token"), 929, this.q);
        }
    }

    public void onError(UiError uiError) {
        super.onError(uiError);
        Toast.makeText(this.a, "code:" + uiError.errorCode + ", msg:" + uiError.errorMessage + ", detail:" + uiError.errorDetail, 0).show();
        this.q.sendEmptyMessage(925);
        MobclickAgent.onEvent(this.a, "E04-A01", "weibo_bind_qzone_faild");
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        if (!this.a.isFinishing()) {
            this.g.show();
        }
        try {
            this.j = this.m.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this.a, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.l.a(mAccessToken, this.j, 924, this.q);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }

    public void a(String str) {
        if (!this.a.isFinishing()) {
            this.g.show();
        }
        if (str != null) {
            try {
                this.j = this.m.getString("id", "");
                this.l.a(this.a, str, "weixin", this.ar);
            } catch (Exception e) {
                this.f = an.a(this.a, this.a.getString(R.string.weixin_shouquan_failed), -1);
                this.f.show();
            }
        }
    }

    public void onrefreshTheme() {
        super.onrefreshTheme();
    }

    public void a(int i, String str) {
        if (i == 971) {
            this.q.sendMessage(this.q.obtainMessage(972, str));
        }
    }

    public void a(int i) {
        if (i == 971) {
            this.A++;
            if (this.A < 2 && !TextUtils.isEmpty(this.C.toString())) {
                this.x.a("add", this.j, this.C.toString(), 971);
            }
        }
    }

    private void k() {
        l();
    }

    private void l() {
        if (this.E) {
            a();
        }
        PendingIntent pendingIntent = (PendingIntent) getIntent().getParcelableExtra("ok_taget_pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send(this, -1, getIntent());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        this.a.finish();
    }

    public void bindTencent() {
        if (!this.a.isFinishing()) {
            this.g.show();
        }
        this.j = this.m.getString("id", "");
        String sharePersistent = Util.getSharePersistent(this.a, "ACCESS_TOKEN");
        this.l.a(Util.getSharePersistent(this.a, "NAME"), sharePersistent, Util.getSharePersistent(this.a, "OPEN_ID"), this.j, 923, this.q);
    }

    protected void onStart() {
        super.onStart();
        if (!this.E) {
        }
    }

    protected void onStop() {
        super.onStop();
        if (!this.E) {
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.E) {
            StatService.onResume(this);
            MobclickAgent.onResume(this);
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.E) {
            StatService.onPause(this);
            MobclickAgent.onPause(this);
        }
    }

    private void a(String str, String str2, String str3, String str4, String str5, OnClickListener onClickListener) {
        View inflate = View.inflate(this.a, R.layout.phone_register_dialog, null);
        TextView textView = (TextView) inflate.findViewById(R.id.phone_number);
        TextView textView2 = (TextView) inflate.findViewById(R.id.rightText);
        TextView textView3 = (TextView) inflate.findViewById(R.id.belowText);
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            textView2.setText(str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            textView3.setText(str3);
        }
        this.ac = new Dialog(this.a, R.style.theme_my_dialog);
        this.ac.setContentView(inflate);
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams attributes = this.ac.getWindow().getAttributes();
        attributes.width = (int) (((double) defaultDisplay.getWidth()) * 0.8d);
        this.ac.getWindow().setAttributes(attributes);
        this.ac.show();
        this.ad = (TextView) inflate.findViewById(R.id.change);
        if (!TextUtils.isEmpty(str4)) {
            this.ad.setText(str4);
        }
        this.ae = (TextView) inflate.findViewById(R.id.confirm);
        if (!TextUtils.isEmpty(str5)) {
            this.ae.setText(str5);
        }
        this.ad.setOnClickListener(onClickListener);
        this.ae.setOnClickListener(onClickListener);
    }

    private void d(String str, String str2) {
        BudejieApplication.a.a(RequstMethod.GET, j.l(), a(str, str2), this.as);
    }

    public net.tsz.afinal.a.b a(String str, String str2) {
        Map a = this.aj.a(this.a, new HashMap());
        a.put("phonenum", str);
        a.put("countrycode", str2);
        a.put("verifytype", "1");
        String e = an.e(this.a);
        a.put(x.u, e);
        long currentTimeMillis = System.currentTimeMillis();
        a.put(AppLinkConstants.TIME, Long.toString(currentTimeMillis));
        a.put("sec", com.budejie.www.activity.phonenumber.a.a(e, str, currentTimeMillis));
        return e.a("/user/api/get_verify", a);
    }

    private net.tsz.afinal.a.b m() {
        return new j().b(this.a);
    }

    private net.tsz.afinal.a.b n() {
        return new j().c(this.a);
    }
}
