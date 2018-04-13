package com.lt.a.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.budejie.www.util.aa;
import com.lt.a.b.b;
import com.lt.a.b.c;
import com.lt.a.b.d;
import com.lt.a.b.e;
import com.lt.a.b.f;
import com.lt.payactivity.WebViewActivity;
import java.util.ArrayList;
import java.util.List;

public class a {
    static String a = "http://bdj.p10155.cn/";
    static String b = "http://bdj.p10155.cn/";
    static String c = (a + "/get_mobile_number/?");
    static String d = (a + "/phone_number_segment");
    static String e = (a + "/config/?");
    static String f = (a + "/proxy_address/?");
    static String g = (a + "/proxy_address/multiple/?");
    public static String h = (a + "/order_state/?");
    public static String i = (b + "/?");

    public static String a(Context context) {
        return c + "AppID=" + com.lt.b.a.a.a + "&&type=2";
    }

    public static String a(Context context, String str) {
        String str2 = com.lt.b.a.a.a;
        String str3 = com.lt.b.a.a.b;
        long parseLong = Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
        String str4 = "" + ((parseLong % 3) + parseLong);
        int b = b.b(context);
        String a = c.a(context);
        String str5 = "" + parseLong;
        String str6 = "" + b.c(context);
        String str7 = "";
        List arrayList = new ArrayList();
        arrayList.add("AppID=" + str2 + "&");
        arrayList.add("operator=cu&");
        arrayList.add("secretKey=" + str3 + "&");
        arrayList.add("networkType=" + b + "&");
        arrayList.add("sdkVersion=" + "1.0.2.A" + "&");
        arrayList.add("version=" + a + "&");
        arrayList.add("deviceType=" + "1" + "&");
        arrayList.add("timeStamp=" + str5 + "&");
        arrayList.add("phonenum=" + str + "&");
        arrayList.add("imsi=" + str6 + "&");
        arrayList.add("channelId=" + "GZFS" + "&");
        arrayList.add("ip=" + "" + "&");
        str2 = f.a(f.a(arrayList), "&");
        arrayList.add("appkey=" + str4 + "&");
        str3 = f.a(e.a(arrayList), "&");
        aa.b("test", "newMsg=" + str3);
        str3 = com.lt.a.b.a.a(str3);
        aa.b("test", "bsse64msg=" + str3);
        str3 = d.a(str3);
        aa.b("test", "md5Msg=" + str3);
        str3 = str3.toUpperCase();
        aa.b("test", "Sign=" + str3);
        return g + "sign=" + str3 + "&" + str2;
    }

    public static String b(Context context, String str) {
        String str2 = com.lt.b.a.a.a;
        String str3 = com.lt.b.a.a.b;
        long parseLong = Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
        String str4 = "" + ((parseLong % 3) + parseLong);
        int b = b.b(context);
        String a = c.a(context);
        String str5 = "" + parseLong;
        String str6 = "" + b.c(context);
        String str7 = "";
        str7 = "";
        List arrayList = new ArrayList();
        arrayList.add("AppID=" + str2 + "&");
        arrayList.add("operator=cu&");
        arrayList.add("secretKey=" + str3 + "&");
        arrayList.add("networkType=" + b + "&");
        arrayList.add("sdkVersion=" + "1.0.2.A" + "&");
        arrayList.add("version=" + a + "&");
        arrayList.add("deviceType=" + "1" + "&");
        arrayList.add("timeStamp=" + str5 + "&");
        arrayList.add("phonenum=" + str + "&");
        arrayList.add("imsi=" + str6 + "&");
        arrayList.add("channelId=" + "GZFS" + "&");
        arrayList.add("ip=" + "" + "&");
        str2 = f.a(f.a(arrayList), "&");
        arrayList.add("appkey=" + str4 + "&");
        return h + "sign=" + d.a(com.lt.a.b.a.a(f.a(e.a(arrayList), "&"))).toUpperCase() + "&" + str2;
    }

    public static void a(Activity activity, String str) {
        String str2 = com.lt.b.a.a.a;
        int b = b.b(activity);
        String a = c.a(activity);
        String str3 = "" + b.c(activity);
        String str4 = "";
        List arrayList = new ArrayList();
        arrayList.clear();
        arrayList.add("AppID=" + str2 + "&");
        arrayList.add("networkType=" + b + "&");
        arrayList.add("sdkVersion=" + "1.0.2.A" + "&");
        arrayList.add("version=" + a + "&");
        arrayList.add("deviceType=" + "1" + "&");
        arrayList.add("phonenum=" + str + "&");
        arrayList.add("channelId=" + "GZFS" + "&");
        arrayList.add("imsi=" + str3 + "&");
        arrayList.add("ip=" + "");
        str2 = i + f.a(arrayList);
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("url", str2);
        activity.startActivityForResult(intent, 10001);
    }
}
