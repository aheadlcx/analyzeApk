package com.budejie.www.activity.htmlpage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.HuodongBean;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.c.m;
import com.budejie.www.g.b;
import com.budejie.www.http.n;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.p;
import com.budejie.www.util.w;
import com.elves.update.a;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.openapi.IWXAPI;
import java.util.HashMap;

@SuppressLint({"HandlerLeak"})
public class c {
    public static String SHARE_PLATFORM_COPY = "copy";
    public static String SHARE_PLATFORM_QQFRIENDS = "qqFriends";
    public static String SHARE_PLATFORM_QZONE = Constants.SOURCE_QZONE;
    public static String SHARE_PLATFORM_RENREN = "renren";
    public static String SHARE_PLATFORM_SINA = "sina";
    public static String SHARE_PLATFORM_SMS = "sms";
    public static String SHARE_PLATFORM_TENCENT = "qq";
    public static String SHARE_PLATFORM_WXFRIENDS = "wxFriends";
    public static String SHARE_PLATFORM_WXGROUP = "wxGroup";
    Handler a = new Handler();
    private String b = (Environment.getExternalStorageDirectory().toString() + "/budejie/");
    public HuodongBean bean;
    private String c = "";
    private String d = "";
    private Activity e;
    private SharedPreferences f;
    private a g;
    private b h;
    private String i;
    private Handler j;
    private m k;
    private n l;
    private HashMap<String, String> m;
    private IWXAPI n;
    private Toast o;
    private ListItemObject p;
    private Bundle q;
    private String r = "";
    private com.budejie.www.f.a s = new c$1(this);

    public c(Activity activity, Handler handler, b bVar, a aVar, m mVar, n nVar, HashMap<String, String> hashMap, IWXAPI iwxapi) {
        this.e = activity;
        this.j = handler;
        this.h = bVar;
        this.g = aVar;
        this.f = activity.getSharedPreferences("weiboprefer", 0);
        this.k = mVar;
        this.l = nVar;
        this.m = hashMap;
        this.n = iwxapi;
    }

    public HuodongBean getHuodongBean() {
        return this.bean;
    }

    public void setmPreference(SharedPreferences sharedPreferences) {
        this.f = sharedPreferences;
    }

    public void setWeiboDb(m mVar) {
        this.k = mVar;
    }

    public void setWeiboMap(HashMap<String, String> hashMap) {
        this.m = hashMap;
    }

    public void setListItemObject(ListItemObject listItemObject) {
        this.p = listItemObject;
    }

    public ListItemObject getListItemObject() {
        return this.p;
    }

    public void setArgs(Bundle bundle) {
        this.q = bundle;
    }

    public void setApi(IWXAPI iwxapi) {
        this.n = iwxapi;
    }

    @JavascriptInterface
    public void showMoreComment() {
        Log.d("sharePost", "showMoreComment");
        if (this.j != null) {
            this.j.sendEmptyMessage(100);
        }
    }

    @JavascriptInterface
    public void shareOrSavePic(String str, String str2) {
        this.c = "";
        this.d = str;
        if (!TextUtils.isEmpty(this.d)) {
            if ("1".equals(this.d) || "0".equals(this.d)) {
                a(str2);
            }
        }
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            this.j.sendMessage(this.j.obtainMessage(HtmlFeatureActivity.d));
            return;
        }
        this.j.sendMessage(this.j.obtainMessage(HtmlFeatureActivity.a));
        this.c = this.b + str.substring(7).replace("/", "-");
        BudejieApplication.a.a(this.e, str, this.s, 100);
    }

    private void b(String str) {
        p.a(null, this.e, this.n.isWXAppInstalled(), this.n.getWXAppSupportAPI() >= Build.TIMELINE_SUPPORTED_SDK_INT, this.f, new c$2(this, new ListItemObject(), str), false, true);
    }

    @JavascriptInterface
    public void sharePost(String str) {
        Log.d("sharePost", "snsName=" + str);
        if (this.p != null) {
            if (TextUtils.isEmpty(str)) {
                if (an.a(this.e)) {
                    p.a(this.q, this.e, this.n.isWXAppInstalled(), this.n.getWXAppSupportAPI() >= Build.TIMELINE_SUPPORTED_SDK_INT, this.f, new c$3(this), true);
                    return;
                }
                this.o = an.a(this.e, this.e.getString(R.string.nonet), -1);
                this.o.show();
            } else if (SHARE_PLATFORM_QQFRIENDS.equals(str)) {
                this.h.b(this.p, this.j);
            } else if (SHARE_PLATFORM_WXFRIENDS.equals(str)) {
                this.h.b(this.p, this.n, this.j);
            } else if (SHARE_PLATFORM_WXGROUP.equals(str)) {
                this.h.a(this.p, this.n, this.j);
            } else if (SHARE_PLATFORM_QZONE.equals(str)) {
                this.h.a(this.p, this.j);
            }
        }
    }

    @JavascriptInterface
    public void shareSNS(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        shareSNS(str, str2, str3, str4, str5, str6, str7, str8, "");
    }

    @JavascriptInterface
    public void shareSNS(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        Log.e("wuzhenlin", "shareSNS  ::  snsName = " + str + " title = " + str2 + " imageURL = " + str3 + " desc = " + str4 + " landURL = " + str5 + " audioURL = " + str6 + " vedioURL = " + str7 + " reserve = " + str8 + " huodongId = " + str9);
        this.bean = new HuodongBean();
        this.i = ai.b(this.e);
        this.bean.setUid(this.i);
        this.bean.setContent(str4);
        this.bean.setPicUrl(str3);
        this.bean.setShareUrl(str5);
        this.bean.setVoiceUrl(str6);
        this.bean.setVideoUrl(str7);
        this.bean.setTitle(str2);
        this.bean.setType(str);
        this.bean.setReserve(str8);
        this.bean.setHuodongId(str9);
        if (TextUtils.isEmpty(str)) {
            if (an.a(this.e)) {
                if (this.j == null) {
                    this.f.edit().putBoolean("isRecommend", true).commit();
                }
                p.a(null, this.e, this.n.isWXAppInstalled(), this.n.getWXAppSupportAPI() >= Build.TIMELINE_SUPPORTED_SDK_INT, this.f, new c$4(this), false, false);
                return;
            }
            this.o = an.a(this.e, this.e.getString(R.string.nonet), -1);
            this.o.show();
        } else if (SHARE_PLATFORM_QQFRIENDS.equals(str)) {
            this.h.a(this.bean, this.j);
        } else if (SHARE_PLATFORM_TENCENT.equals(str)) {
            this.h.a(this.f, this.bean, this.g, this.j, this.l, this.m);
        } else if (SHARE_PLATFORM_SINA.equals(str)) {
            n.a(this.e, this.bean);
        } else if (SHARE_PLATFORM_WXFRIENDS.equals(str)) {
            this.h.a(this.f, this.bean, this.n);
        } else if (SHARE_PLATFORM_WXGROUP.equals(str)) {
            this.h.b(this.f, this.bean, this.n);
        } else if (SHARE_PLATFORM_QZONE.equals(str)) {
            this.h.b(this.bean, this.j);
        } else if (SHARE_PLATFORM_SMS.equals(str)) {
            this.h.a(this.bean, this.f);
        } else if (SHARE_PLATFORM_COPY.equals(str)) {
            this.h.b(this.bean, this.f);
        }
    }

    @JavascriptInterface
    public void showInterstitialAd() {
        Log.d("admoblog", "showInterstitialAd");
        this.j.post(new c$5(this));
    }

    @JavascriptInterface
    public void initInterstitialAd(String str) {
        this.r = str;
    }

    @JavascriptInterface
    public void launchPage(String str) {
        w.a(this.e, false).a(str);
    }

    @JavascriptInterface
    private boolean netWorkAvailable() {
        if (an.a(this.e)) {
            return true;
        }
        this.o = an.a(this.e, this.e.getString(R.string.nonet), -1);
        this.o.show();
        return false;
    }
}
