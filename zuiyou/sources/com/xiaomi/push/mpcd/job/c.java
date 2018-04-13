package com.xiaomi.push.mpcd.job;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.xiaomi.channel.commonutils.android.a;
import com.xiaomi.xmpush.thrift.d;

public class c extends f {
    public c(Context context, int i) {
        super(context, i);
    }

    public int a() {
        return 4;
    }

    public String b() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            PackageManager packageManager = this.d.getPackageManager();
            int i = 0;
            for (PackageInfo packageInfo : packageManager.getInstalledPackages(128)) {
                if ((packageInfo.applicationInfo.flags & 1) == 0) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                    }
                    String charSequence = packageInfo.applicationInfo.loadLabel(packageManager).toString();
                    stringBuilder.append(charSequence).append(",").append(packageInfo.packageName).append(",").append(packageInfo.versionName).append(",").append(packageInfo.versionCode).append(",").append(a.f(this.d, packageInfo.packageName));
                    int i2 = i + 1;
                    if (i2 >= 200) {
                        break;
                    }
                    i = i2;
                }
            }
        } catch (Throwable th) {
        }
        return stringBuilder.toString();
    }

    public d d() {
        return d.AppInstallList;
    }
}
