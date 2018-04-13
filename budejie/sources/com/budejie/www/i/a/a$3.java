package com.budejie.www.i.a;

import android.os.Build;
import android.text.TextUtils;
import com.budejie.www.a.a.a;
import com.budejie.www.activity.label.response.UserBanResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.umeng.analytics.MobclickAgent;
import java.io.IOException;
import okhttp3.e;

class a$3 implements a {
    final /* synthetic */ a a;

    a$3(a aVar) {
        this.a = aVar;
    }

    public void a(e eVar, IOException iOException) {
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            UserBanResponse userBanResponse;
            try {
                userBanResponse = (UserBanResponse) new Gson().fromJson(str, UserBanResponse.class);
            } catch (JsonSyntaxException e) {
                if (!(a.a(this.a) == null || a.a(this.a).h() == null)) {
                    MobclickAgent.onEvent(a.a(this.a).h(), "E03-A07", "版块禁言用户解析异常json: " + str + " 机型:" + Build.MODEL);
                }
                userBanResponse = null;
            }
            if (a.a(this.a) != null && userBanResponse != null) {
                a.a(this.a).a(userBanResponse);
            }
        }
    }
}
