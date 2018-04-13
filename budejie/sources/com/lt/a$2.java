package com.lt;

import android.text.TextUtils;
import com.baidu.mobads.openad.c.b;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import java.text.SimpleDateFormat;
import java.util.Date;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import net.tsz.afinal.a.a;
import org.json.JSONObject;

class a$2 extends a<String> {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        boolean z = true;
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                aa.b("test", "requestPhoneNumUrl=" + str);
                if (jSONObject.has(CheckCodeDO.CHECKCODE_USER_INPUT_KEY) && "0000".equals(jSONObject.getString(CheckCodeDO.CHECKCODE_USER_INPUT_KEY)) && jSONObject.has(b.EVENT_MESSAGE)) {
                    a.b(this.a, jSONObject.getString(b.EVENT_MESSAGE));
                    a.a(this.a, true);
                } else {
                    aa.b("test", "使用缓存号码");
                    a.b(this.a, an.m(a.a(this.a)));
                    a.a(this.a, true);
                }
                aa.b("test", "mPhoneNum =" + a.b(this.a));
                if (!TextUtils.isEmpty(a.b(this.a))) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if (a.c(this.a) || !simpleDateFormat.format(new Date()).equals(an.f(a.a(this.a), a.b(this.a)))) {
                        this.a.d();
                        a.b(this.a, false);
                        return;
                    }
                    a.c(this.a, an.e(a.a(this.a), a.b(this.a)));
                    Object h = an.h(a.a(this.a), a.b(this.a));
                    if (!TextUtils.isEmpty(h)) {
                        a.d(this.a, h);
                    }
                } else if (2 == com.lt.a.b.b.a(a.a(this.a))) {
                    String str2 = "test";
                    StringBuilder append = new StringBuilder().append("showDialogLinstener!=null =");
                    if (a.h() == null) {
                        z = false;
                    }
                    aa.b(str2, append.append(z).toString());
                    if (a.h() != null) {
                        a.h().a(true);
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
