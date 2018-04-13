package com.umeng.commonsdk.internal.utils;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.a;
import com.umeng.commonsdk.internal.b;
import com.umeng.commonsdk.statistics.common.e;

class m extends PhoneStateListener {
    final /* synthetic */ b a;

    m(b bVar) {
        this.a = bVar;
    }

    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        e.c("BaseStationUtils", "base station onSignalStrengthsChanged");
        try {
            this.a.d = (TelephonyManager) b.c.getSystemService("phone");
            String[] split = signalStrength.toString().split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            CharSequence charSequence = null;
            if (this.a.d.getNetworkType() == 13) {
                charSequence = "" + Integer.parseInt(split[9]);
            } else if (this.a.d.getNetworkType() == 8 || this.a.d.getNetworkType() == 10 || this.a.d.getNetworkType() == 9 || this.a.d.getNetworkType() == 3) {
                Object b = this.a.e();
                if (!TextUtils.isEmpty(b) && b.equals("中国移动")) {
                    charSequence = "0";
                } else if (!TextUtils.isEmpty(b) && b.equals("中国联通")) {
                    charSequence = signalStrength.getCdmaDbm() + "";
                } else if (!TextUtils.isEmpty(b) && b.equals("中国电信")) {
                    charSequence = signalStrength.getEvdoDbm() + "";
                }
            } else {
                charSequence = ((signalStrength.getGsmSignalStrength() * 2) - 113) + "";
            }
            e.c("BaseStationUtils", "stationStrength is " + charSequence);
            if (!TextUtils.isEmpty(charSequence)) {
                try {
                    UMWorkDispatch.sendEvent(b.c, a.h, b.a(b.c).a(), charSequence);
                } catch (Throwable th) {
                    com.umeng.commonsdk.proguard.b.a(b.c, th);
                }
            }
            this.a.c();
        } catch (Throwable th2) {
            com.umeng.commonsdk.proguard.b.a(b.c, th2);
        }
    }
}
