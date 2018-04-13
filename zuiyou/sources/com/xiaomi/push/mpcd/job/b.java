package com.xiaomi.push.mpcd.job;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.text.TextUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.xiaomi.xmpush.thrift.d;

public class b extends f {
    public b(Context context, int i) {
        super(context, i);
    }

    public int a() {
        return 5;
    }

    public String b() {
        Object stringBuilder = new StringBuilder();
        try {
            for (RunningTaskInfo runningTaskInfo : ((ActivityManager) this.d.getSystemService("activity")).getRunningTasks(10)) {
                if (!(runningTaskInfo == null || runningTaskInfo.topActivity == null)) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                    }
                    stringBuilder.append(runningTaskInfo.topActivity.getPackageName());
                }
            }
        } catch (Throwable th) {
        }
        return TextUtils.isEmpty(stringBuilder) ? null : stringBuilder.toString();
    }

    public d d() {
        return d.AppActiveList;
    }
}
