package com.iflytek.cloud;

import android.text.TextUtils;
import com.iflytek.cloud.thirdparty.bh.a;
import com.iflytek.cloud.thirdparty.cb;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import com.tencent.connect.common.Constants;

public class Version {
    private static String a = "";
    private static String b = "0";

    private static String getModeVersion() {
        return a.MSC == SpeechUtility.DEF_ENGINE_MODE ? Constants.VIA_SHARE_TYPE_INFO : "5";
    }

    private static String getMscVersion() {
        if ("0".equalsIgnoreCase(b)) {
            try {
                if (MSC.isLoaded()) {
                    MSCSessionInfo mSCSessionInfo = new MSCSessionInfo();
                    String str = "ver_msc";
                    byte[] QMSPGetVersion = MSC.QMSPGetVersion("ver_msc".getBytes("gb2312"), mSCSessionInfo);
                    if (mSCSessionInfo.errorcode == 0) {
                        String str2 = new String(QMSPGetVersion, "gb2312");
                        cb.a("get msc full version name: " + str2);
                        int lastIndexOf = str2.lastIndexOf(".");
                        if (lastIndexOf >= 0 && str2.length() > lastIndexOf + 1) {
                            b = str2.substring(lastIndexOf + 1);
                        }
                    } else {
                        cb.a("get msc version error: " + mSCSessionInfo.errorcode);
                    }
                } else {
                    cb.c("get msc version msc is not load.");
                }
            } catch (Throwable th) {
                cb.c("get msc version exception:");
                cb.a(th);
            }
        }
        return b;
    }

    public static String getVersion() {
        if (TextUtils.isEmpty(a) || "0".equalsIgnoreCase(b)) {
            a = "4." + getModeVersion() + "." + "1127" + "." + getMscVersion();
        }
        return a;
    }
}
