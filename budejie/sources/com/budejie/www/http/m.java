package com.budejie.www.http;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.util.ai;
import java.net.HttpURLConnection;
import java.util.List;
import net.tsz.afinal.a.a;
import org.apache.http.NameValuePair;

public class m {
    private j a = new j();

    private void a(Context context, HttpURLConnection httpURLConnection) {
        Object b = NetWorkUtil.b(context);
        if (!TextUtils.isEmpty(b)) {
            httpURLConnection.setRequestProperty("Cookie", b);
        }
    }

    public void a(String str, a<String> aVar) {
        BudejieApplication.a.a(RequstMethod.GET, new h("http://d.api.budejie.com/topic/my-topic").b(str).a().toString(), new j(), (a) aVar);
    }

    public void a(Activity activity, String str, String str2, com.budejie.www.f.a aVar, int i) {
        h hVar = new h("http://d.api.budejie.com/topic/audit-topic");
        hVar.d(str).b(str2).a();
        BudejieApplication.a.a(activity, hVar.toString(), "get", new j(activity), aVar, null, i);
    }

    public void a(Activity activity, com.budejie.www.f.a aVar, int i) {
        BudejieApplication.a.a(activity, j.h(), "get", new j(activity), aVar, null, i);
    }

    public void a(Activity activity, String str, String str2, String str3, com.budejie.www.f.a aVar) {
        Context context = activity;
        BudejieApplication.a.a(context, "http://api.budejie.com/api/api_open.php", "get", this.a.a((Context) activity, str, str3, str2), aVar, null, -1);
    }

    public void a(Activity activity, String str, String str2, String str3) {
        Context context = activity;
        BudejieApplication.a.a(context, "http://api.budejie.com/api/api_open.php", "post", this.a.a(activity, str, str2, str3), null, null, -1);
    }

    public void a(Activity activity, String str, int i, com.budejie.www.f.a aVar) {
        Context context = activity;
        BudejieApplication.a.a(context, "http://api.budejie.com/api/api_open.php", "post", this.a.a(activity, str, ai.b(activity)), aVar, null, i);
    }

    public void a(Context context, boolean z, String str, String str2, String str3, List<NameValuePair> list, Handler handler, int i) {
        new Thread(new m$1(this, str, context, list, z, str2, str3, handler, i)).start();
    }
}
