package com.budejie.www.push;

import android.content.Context;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.aa;
import net.tsz.afinal.a.a;
import net.tsz.afinal.a.b;

public class e {
    private static b b(Context context, String str, String str2, String str3, String str4, String str5) {
        return new j().b(context, str, str2, str3, str4, str5);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", b(context, str, str2, str3, str4, str5), new a<String>() {
            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void a(String str) {
                super.onSuccess(str);
                aa.a("push", "push上报结果-->" + str);
            }

            public void onFailure(Throwable th, int i, String str) {
                super.onFailure(th, i, str);
                aa.a("push", "Throwable:" + th + ",errorNo:" + i + ",strMsg:" + str);
            }
        });
    }
}
