package com.microquation.linkedme.android;

import android.app.Activity;
import android.content.Intent;
import com.microquation.linkedme.android.f.b;
import com.microquation.linkedme.android.util.LinkProperties;
import org.json.JSONException;
import org.json.JSONObject;

class a$4 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ JSONObject b;
    final /* synthetic */ LinkProperties c;
    final /* synthetic */ int d;
    final /* synthetic */ a e;

    a$4(a aVar, String str, JSONObject jSONObject, LinkProperties linkProperties, int i) {
        this.e = aVar;
        this.a = str;
        this.b = jSONObject;
        this.c = linkProperties;
        this.d = i;
    }

    public void run() {
        try {
            Activity activity = (Activity) a.a(this.e).get();
            if (activity != null) {
                try {
                    Intent intent = new Intent(activity, Class.forName(this.a));
                    a.a(this.e, intent, this.b, this.c);
                    b.a(a.a, "开始跳转到中间页面！");
                    activity.startActivityForResult(intent, this.d);
                    a.b(this.e, true);
                    a.c(this.e, false);
                    return;
                } catch (ClassNotFoundException e) {
                    a.b(this.e).c("LinkedME Warning: 请确保自动深度链接Activity正确配置！并没有找到该Activity" + this.d);
                    return;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    a.b(this.e).c("LinkedME Warning: 数据解析错误！");
                    return;
                }
            }
            a.b(this.e).c(a.a, "页面已被销毁，无法跳转，请将URI Scheme配置到不会短时间内被销毁的页面，如：首页。");
        } catch (Exception e3) {
            if (b.a()) {
                e3.printStackTrace();
            }
        }
    }
}
