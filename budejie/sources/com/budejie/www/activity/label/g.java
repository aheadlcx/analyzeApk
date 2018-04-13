package com.budejie.www.activity.label;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.htmlpage.c;
import com.budejie.www.bean.HuodongBean;
import com.budejie.www.c.m;
import com.budejie.www.g.b;
import com.budejie.www.http.n;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.p;
import com.elves.update.a;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.openapi.IWXAPI;
import java.util.HashMap;

public class g extends c {
    public HuodongBean b;
    private Activity c;
    private SharedPreferences d;
    private a e;
    private b f;
    private String g;
    private Handler h;
    private m i;
    private n j;
    private HashMap<String, String> k;
    private IWXAPI l;
    private Toast m;

    public g(Activity activity, Handler handler, b bVar, a aVar, m mVar, n nVar, HashMap<String, String> hashMap, IWXAPI iwxapi) {
        super(activity, handler, bVar, aVar, mVar, nVar, hashMap, iwxapi);
        this.c = activity;
        this.h = handler;
        this.f = bVar;
        this.e = aVar;
        this.d = activity.getSharedPreferences("weiboprefer", 0);
        this.i = mVar;
        this.j = nVar;
        this.k = hashMap;
        this.l = iwxapi;
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i) {
        this.b = new HuodongBean();
        this.g = ai.b(this.c);
        this.b.setUid(this.g);
        this.b.setContent(str4);
        if (TextUtils.isEmpty(str3)) {
            this.b.setPicUrl("http://img.spriteapp.cn/ws/img/tag_logo.png");
        } else {
            this.b.setPicUrl(str3);
        }
        this.b.setShareUrl(str5);
        this.b.setVoiceUrl(str6);
        this.b.setVideoUrl(str7);
        this.b.setTitle(str2);
        this.b.setType(str);
        this.b.setReserve(str8);
        this.b.setHuodongId(str9);
        this.b.setTheme_id(i);
        if (TextUtils.isEmpty(str)) {
            if (an.a(this.c)) {
                if (this.h == null) {
                    this.d.edit().putBoolean("isRecommend", true).commit();
                }
                p.a(null, this.c, this.l.isWXAppInstalled(), this.l.getWXAppSupportAPI() >= Build.TIMELINE_SUPPORTED_SDK_INT, this.d, new g$1(this), false, false);
                return;
            }
            this.m = an.a(this.c, this.c.getString(R.string.nonet), -1);
            this.m.show();
        } else if (SHARE_PLATFORM_QQFRIENDS.equals(str)) {
            this.f.d(this.b, this.h);
        } else if (SHARE_PLATFORM_TENCENT.equals(str)) {
            this.f.b(this.d, this.b, this.e, this.h, this.j, this.k);
        } else if (SHARE_PLATFORM_SINA.equals(str)) {
            this.f.a(this.d, this.b, this.e, this.h, this.i, this.j, this.k);
        } else if (SHARE_PLATFORM_WXFRIENDS.equals(str)) {
            this.f.c(this.d, this.b, this.l);
        } else if (SHARE_PLATFORM_WXGROUP.equals(str)) {
            this.f.d(this.d, this.b, this.l);
        } else if (SHARE_PLATFORM_QZONE.equals(str)) {
            this.f.c(this.b, this.h);
        } else if (SHARE_PLATFORM_SMS.equals(str)) {
            this.f.c(this.b, this.d);
        }
    }
}
