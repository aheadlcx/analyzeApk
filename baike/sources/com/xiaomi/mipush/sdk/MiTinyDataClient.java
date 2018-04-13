package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.aw;

public class MiTinyDataClient {
    public static void init(Context context, String str) {
        if (context == null) {
            b.d("MiTinyDataClient.init(Context, String). Context can not be null");
        } else if (str == null) {
            b.d("MiTinyDataClient.init(Context, String). Channel can not be null.");
        } else {
            aw.a().a(str);
        }
    }

    public static boolean upload(int i, String str, String str2, long j, String str3) {
        return aw.a().a(i, str, str2, j, str3, false);
    }

    public static boolean upload(String str, String str2, long j, String str3) {
        return upload(0, str, str2, j, str3);
    }
}
