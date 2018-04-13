package com.baidu.mobstat;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class CooperService implements ICooperService {
    private static CooperService a;
    private bu b = new bu();

    static synchronized CooperService a() {
        CooperService cooperService;
        synchronized (CooperService.class) {
            if (a == null) {
                a = new CooperService();
            }
            cooperService = a;
        }
        return cooperService;
    }

    public bu getHeadObject() {
        return this.b;
    }

    public String getHost() {
        return Config.LOG_SEND_URL;
    }

    public void installHeader(Context context, JSONObject jSONObject) {
        this.b.a(context, jSONObject);
    }

    public JSONObject getHeaderExt(Context context) {
        Object l = bj.a().l(context);
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(l)) {
            return jSONObject;
        }
        try {
            return new JSONObject(l);
        } catch (JSONException e) {
            return jSONObject;
        }
    }

    public void setHeaderExt(Context context, ExtraInfo extraInfo) {
        JSONObject jSONObject = new JSONObject();
        if (extraInfo != null) {
            jSONObject = extraInfo.dumpToJson();
        }
        this.b.a(jSONObject);
        bj.a().f(context, jSONObject.toString());
    }

    private static String a(Context context) {
        String j = de.j(context);
        if (TextUtils.isEmpty(j)) {
            return j;
        }
        return j.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
    }

    private static String b(Context context) {
        String i = de.i(context);
        if (TextUtils.isEmpty(i)) {
            return i;
        }
        return i.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
    }

    private static String c(Context context) {
        String k = de.k(context);
        if (TextUtils.isEmpty(k)) {
            return k;
        }
        return k.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
    }

    public String getMacAddress(Context context, boolean z) {
        String replace = Config.DEF_MAC_ID.replace(Config.TRACE_TODAY_VISIT_SPLIT, "");
        if (!z && VERSION.SDK_INT >= 23) {
            return getSecretValue(replace);
        }
        if (!TextUtils.isEmpty(this.b.t)) {
            return this.b.t;
        }
        Object i = bj.a().i(context);
        if (TextUtils.isEmpty(i)) {
            i = a(context, z);
            if (TextUtils.isEmpty(i) || replace.equals(i)) {
                this.b.t = "";
                return this.b.t;
            }
            this.b.t = getSecretValue(i);
            bj.a().d(context, this.b.t);
            return this.b.t;
        }
        this.b.t = i;
        return this.b.t;
    }

    private String a(Context context, boolean z) {
        String b;
        if (z) {
            b = b(context);
        } else {
            b = a(context);
        }
        if (TextUtils.isEmpty(b)) {
            return "";
        }
        return b;
    }

    public String getMacIdForTv(Context context) {
        if (!TextUtils.isEmpty(this.b.u)) {
            return this.b.u;
        }
        Object k = bj.a().k(context);
        if (TextUtils.isEmpty(k)) {
            String c = de.c(1, context);
            if (TextUtils.isEmpty(c)) {
                this.b.u = "";
                return this.b.u;
            }
            this.b.u = c;
            bj.a().e(context, c);
            return this.b.u;
        }
        this.b.u = k;
        return this.b.u;
    }

    public String getCUID(Context context, boolean z) {
        if (this.b.g == null) {
            this.b.g = bj.a().f(context);
            if (this.b.g == null || "".equalsIgnoreCase(this.b.g)) {
                try {
                    this.b.g = dg.a(context);
                    Matcher matcher = Pattern.compile("\\s*|\t|\r|\n").matcher(this.b.g);
                    this.b.g = matcher.replaceAll("");
                    this.b.g = getSecretValue(this.b.g);
                    bj.a().b(context, this.b.g);
                } catch (Exception e) {
                    db.c(e.getMessage());
                }
            }
        }
        if (z) {
            return this.b.g;
        }
        try {
            Object obj = this.b.g;
            if (!TextUtils.isEmpty(obj)) {
                return new String(ct.b(1, cv.a(obj.getBytes())));
            }
        } catch (Throwable e2) {
            db.a(e2);
        }
        return null;
    }

    public int getTagValue() {
        return 1;
    }

    public String getDeviceId(TelephonyManager telephonyManager, Context context) {
        String str = this.b.j;
        if (!TextUtils.isEmpty(str)) {
            return this.b.j;
        }
        if (bj.a().j(context)) {
            this.b.j = getMacIdForTv(context);
            return this.b.j;
        } else if (telephonyManager == null) {
            return this.b.j;
        } else {
            Pattern compile = Pattern.compile("\\s*|\t|\r|\n");
            try {
                CharSequence deviceId = telephonyManager.getDeviceId();
                if (deviceId != null) {
                    str = compile.matcher(deviceId).replaceAll("");
                }
            } catch (Throwable e) {
                db.a(e);
            }
            if (str == null || str.equals(Config.NULL_DEVICE_ID)) {
                str = a(context);
            }
            if (de.s(context) && (TextUtils.isEmpty(r0) || r0.equals(Config.NULL_DEVICE_ID))) {
                try {
                    str = c(context);
                } catch (Throwable e2) {
                    db.a(e2);
                }
            }
            if (TextUtils.isEmpty(str) || str.equals(Config.NULL_DEVICE_ID)) {
                str = bj.a().e(context);
            }
            if (TextUtils.isEmpty(str) || str.equals(Config.NULL_DEVICE_ID)) {
                str = "hol" + (new Date().getTime() + "").hashCode() + "mes";
                bj.a().a(context, str);
            }
            this.b.j = str;
            this.b.j = getSecretValue(this.b.j);
            return this.b.j;
        }
    }

    public String getAppChannel(Context context) {
        return d(context);
    }

    private String d(Context context) {
        try {
            if (this.b.m == null || this.b.m.equals("")) {
                boolean h = bj.a().h(context);
                if (h) {
                    this.b.m = bj.a().g(context);
                }
                if (!h || this.b.m == null || this.b.m.equals("")) {
                    this.b.m = de.a(context, Config.CHANNEL_META_NAME);
                }
            }
        } catch (Throwable e) {
            db.a(e);
        }
        return this.b.m;
    }

    public String getAppKey(Context context) {
        if (this.b.f == null) {
            this.b.f = de.a(context, Config.APPKEY_META_NAME);
        }
        return this.b.f;
    }

    public String getMTJSDKVersion() {
        return "3.7.6.1";
    }

    public int getAppVersionCode(Context context) {
        if (this.b.h == -1) {
            this.b.h = de.e(context);
        }
        return this.b.h;
    }

    public String getAppVersionName(Context context) {
        if (TextUtils.isEmpty(this.b.i)) {
            this.b.i = de.f(context);
        }
        return this.b.i;
    }

    public String getOperator(TelephonyManager telephonyManager) {
        if (TextUtils.isEmpty(this.b.n)) {
            this.b.n = telephonyManager.getNetworkOperator();
        }
        return this.b.n;
    }

    public String getLinkedWay(Context context) {
        if (TextUtils.isEmpty(this.b.s)) {
            this.b.s = de.o(context);
        }
        return this.b.s;
    }

    public String getOSVersion() {
        if (TextUtils.isEmpty(this.b.c)) {
            this.b.c = Integer.toString(VERSION.SDK_INT);
        }
        return this.b.c;
    }

    public String getOSSysVersion() {
        if (TextUtils.isEmpty(this.b.d)) {
            this.b.d = VERSION.RELEASE;
        }
        return this.b.d;
    }

    public String getPhoneModel() {
        if (TextUtils.isEmpty(this.b.o)) {
            this.b.o = Build.MODEL;
        }
        return this.b.o;
    }

    public String getManufacturer() {
        if (TextUtils.isEmpty(this.b.p)) {
            this.b.p = Build.MANUFACTURER;
        }
        return this.b.p;
    }

    public boolean checkWifiLocationSetting(Context context) {
        return "true".equalsIgnoreCase(de.a(context, Config.GET_WIFI_LOCATION));
    }

    public boolean checkGPSLocationSetting(Context context) {
        return "true".equals(de.a(context, Config.GET_GPS_LOCATION));
    }

    public boolean checkCellLocationSetting(Context context) {
        return "true".equalsIgnoreCase(de.a(context, Config.GET_CELL_LOCATION));
    }

    public String getSecretValue(String str) {
        return ct.c(1, str.getBytes());
    }

    public String getUUID() {
        return UUID.randomUUID().toString().replace(Constants.ACCEPT_TIME_SEPARATOR_SERVER, "");
    }

    public void resetHeadSign() {
        this.b.y = a().getUUID();
    }

    public void enableDeviceMac(Context context, boolean z) {
        bj.a().d(context, z);
    }

    public boolean isDeviceMacEnabled(Context context) {
        return bj.a().m(context);
    }
}
