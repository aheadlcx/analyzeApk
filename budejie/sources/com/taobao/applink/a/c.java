package com.taobao.applink.a;

import android.util.Log;
import com.taobao.applink.auth.TBAppLinkAuthListener;
import com.taobao.applink.auth.userinfo.TBAppLinkUserInfo;
import com.taobao.applink.f.a.b;
import com.taobao.applink.util.TBAppLinkUtil;

class c implements TBAppLinkAuthListener {
    final /* synthetic */ b a;
    final /* synthetic */ b b;

    c(b bVar, b bVar2) {
        this.b = bVar;
        this.a = bVar2;
    }

    public void onFailure() {
        if (this.a != null) {
            try {
                this.a.a("{\"result\":false}");
            } catch (Exception e) {
                Log.d(TBAppLinkUtil.TAG, e.toString());
            }
        }
    }

    public void onSuccess(TBAppLinkUserInfo tBAppLinkUserInfo) {
        if (this.a != null) {
            try {
                this.a.a(String.format("{\"result\":true,\"mixedNick\":\"%s\",\"icon\":\"%s\"}", new Object[]{tBAppLinkUserInfo.mixedNick, tBAppLinkUserInfo.icon}));
            } catch (Exception e) {
                Log.d(TBAppLinkUtil.TAG, e.toString());
            }
        }
    }
}
