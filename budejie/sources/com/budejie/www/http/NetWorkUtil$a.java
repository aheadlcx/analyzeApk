package com.budejie.www.http;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.ali.auth.third.core.model.Constants;
import com.alipay.sdk.util.j;
import com.budejie.www.util.aa;
import com.umeng.onlineconfig.OnlineConfigAgent;
import net.tsz.afinal.a.a;

class NetWorkUtil$a extends a {
    final /* synthetic */ NetWorkUtil a;
    private int b;
    private com.budejie.www.f.a c;
    private Handler d;
    private Bundle e;
    private Message f;
    private String g;
    private Context h;

    private NetWorkUtil$a(NetWorkUtil netWorkUtil, int i, com.budejie.www.f.a aVar, Handler handler, Bundle bundle, String str, Context context) {
        this.a = netWorkUtil;
        this.b = i;
        this.c = aVar;
        this.d = handler;
        this.e = bundle;
        this.g = str;
        this.h = context;
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        try {
            String str2 = "NetWorkUtil";
            if (str == null) {
                str = "";
            }
            aa.a(str2, str);
            if (this.c != null) {
                this.c.a(this.b);
            } else if (this.d != null) {
                if (this.e != null) {
                    this.e.putString(j.c, "");
                    this.e.putInt("notifyId", this.b);
                    this.f = this.d.obtainMessage(this.b, this.e);
                } else {
                    this.f = this.d.obtainMessage(this.b, "");
                }
                this.d.sendMessage(this.f);
            }
            if (!TextUtils.isEmpty(this.g)) {
                if (Constants.SERVICE_SCOPE_FLAG_VALUE.equals(OnlineConfigAgent.getInstance().getConfigParams(this.h, "reportedNetTime_switch"))) {
                    this.a.a(System.currentTimeMillis() - NetWorkUtil.a(this.a), this.g);
                }
            }
            this.c = null;
            this.d = null;
            this.e = null;
            this.h = null;
            this.f = null;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onStart() {
        super.onStart();
        NetWorkUtil.a(this.a, System.currentTimeMillis());
    }

    public void onSuccess(Object obj) {
        super.onSuccess(obj);
        try {
            String obj2 = obj.toString();
            aa.a("NetWorkUtil", "结果数据：" + obj2);
            if (this.c != null) {
                this.c.a(this.b, obj2);
            } else if (this.d != null) {
                if (this.e != null) {
                    this.e.putString(j.c, obj2);
                    this.e.putInt("notifyId", this.b);
                    this.f = this.d.obtainMessage(this.b, this.e);
                } else {
                    this.f = this.d.obtainMessage(this.b, obj2);
                }
                this.d.sendMessage(this.f);
            }
            if (!TextUtils.isEmpty(this.g)) {
                if (Constants.SERVICE_SCOPE_FLAG_VALUE.equals(OnlineConfigAgent.getInstance().getConfigParams(this.h, "reportedNetTime_switch"))) {
                    this.a.a(System.currentTimeMillis() - NetWorkUtil.a(this.a), this.g);
                }
            }
            this.c = null;
            this.d = null;
            this.e = null;
            this.h = null;
            this.f = null;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
