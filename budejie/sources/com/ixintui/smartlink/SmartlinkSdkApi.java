package com.ixintui.smartlink;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.ixintui.smartlink.a.a;
import org.json.JSONException;
import org.json.JSONObject;

public class SmartlinkSdkApi {

    /* renamed from: com.ixintui.smartlink.SmartlinkSdkApi$1 */
    final class AnonymousClass1 extends Thread {
        final /* synthetic */ SmartlinkListener val$listener;
        final /* synthetic */ String val$smartKey;

        AnonymousClass1(String str, SmartlinkListener smartlinkListener) {
            this.val$smartKey = str;
            this.val$listener = smartlinkListener;
        }

        public final void run() {
            Object a = a.a("http://www.iappease.com.cn/smapi/getInfo", "si=" + this.val$smartKey);
            if (!TextUtils.isEmpty(a)) {
                try {
                    ParamInfo a2 = a.a(Uri.parse("http://www.iappease.com.cn?" + new JSONObject(a).optString("info", "")));
                    if (a2 != null) {
                        this.val$listener.onParamsReturn(a2);
                        this.val$listener.onError(a2.getError());
                        SmartlinkSdkApi.responseLink(a2.getLinkKey());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public static void init(Intent intent, SmartlinkListener smartlinkListener) {
        if (intent != null && smartlinkListener != null) {
            Uri data = intent.getData();
            if (data != null) {
                try {
                    ParamInfo a = a.a(data);
                    smartlinkListener.onParamsReturn(a);
                    smartlinkListener.onError(a.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void registerLink(Context context, String str, SmartlinkListener smartlinkListener) {
        new AnonymousClass1(str, smartlinkListener).start();
    }

    private static void responseLink(String str) {
        if (!TextUtils.isEmpty(str)) {
            a.a("http://www.iappease.com.cn/smapi/client_update", "lk=" + str);
        }
    }
}
