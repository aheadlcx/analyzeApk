package com.baidu.mobads.vo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alipay.sdk.app.statistic.c;
import com.baidu.mobads.AdSettings;
import com.baidu.mobads.AdSettings.b;
import com.baidu.mobads.a.a;
import com.baidu.mobads.g.g;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdProdInfo;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.interfaces.utils.IXAdConstants;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobads.interfaces.utils.IXAdURIUitls;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.List;

public abstract class d implements IXAdRequestInfo {
    private String a = AlibcConstants.PF_ANDROID;
    protected String b = "";
    protected String c = "TODO";
    protected Context d;
    protected Activity e;
    protected SlotType f;
    protected IXAdProdInfo g;
    protected IXAdConstants h = XAdSDKFoundationFacade.getInstance().getAdConstants();
    protected IXAdURIUitls i = XAdSDKFoundationFacade.getInstance().getURIUitls();
    private String j = "";
    private int k;
    private int l;
    private int m = XAdSDKFoundationFacade.getInstance().getAdConstants().getAdCreativeTypeImage();
    private String n = "LP,DL";
    private String o = "";
    private int p;
    private int q = 0;
    private int r;
    private boolean s = true;
    private long t = System.currentTimeMillis();

    protected abstract HashMap<String, String> a();

    public d(Context context, Activity activity, SlotType slotType) {
        Activity activity2 = null;
        if (context instanceof Activity) {
            activity2 = (Activity) context;
        }
        this.e = activity2;
        this.d = activity2 == null ? context : activity2.getApplicationContext();
        if (this.e == null && activity != null) {
            this.e = activity;
        }
        this.f = slotType;
        this.g = new b(this, this.f);
        c(this.f.getValue());
        a(context);
    }

    public IXAdProdInfo d() {
        return this.g;
    }

    @TargetApi(4)
    protected HashMap<String, String> e() {
        Object obj;
        String str = "";
        IXAdSystemUtils systemUtils = XAdSDKFoundationFacade.getInstance().getSystemUtils();
        IXAdCommonUtils commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put(c.a, "" + systemUtils.getNetworkCatagory(this.d));
        hashMap.put(IXAdRequestInfo.AD_COUNT, "" + getN());
        hashMap.put(IXAdRequestInfo.AD_TYPE, "" + getAt());
        hashMap.put(IXAdRequestInfo.V, f() + "_" + a.c + "_" + "4.1.30");
        hashMap.put(IXAdRequestInfo.CS, "");
        hashMap.put(IXAdRequestInfo.PACKAGE, commonUtils.getAppPackage(this.d));
        hashMap.put(IXAdRequestInfo.SDK_VALID, "sdk_8.7018");
        str = commonUtils.getAppId(this.d);
        hashMap.put(IXAdRequestInfo.COST_NAME, str + "_cpr");
        hashMap.put("appid", str);
        hashMap.put(IXAdRequestInfo.PHONE_TYPE, Build.MODEL);
        hashMap.put(IXAdRequestInfo.BRAND, systemUtils.getPhoneOSBrand());
        DisplayMetrics displayMetrics = commonUtils.getDisplayMetrics(this.d);
        hashMap.put(IXAdRequestInfo.DENSITY, "" + displayMetrics.density);
        hashMap.put(IXAdRequestInfo.WIDTH, "" + getW());
        hashMap.put(IXAdRequestInfo.HEIGHT, "" + getH());
        Rect screenRect = commonUtils.getScreenRect(this.d);
        hashMap.put(IXAdRequestInfo.SCREEN_WIDTH, "" + screenRect.width());
        hashMap.put(IXAdRequestInfo.SCREEN_HEIGHT, "" + screenRect.height());
        hashMap.put(IXAdRequestInfo.QUERY_WIDTH, String.valueOf(Math.round(((float) getW()) / displayMetrics.density)));
        hashMap.put(IXAdRequestInfo.QUERY_HEIGHT, String.valueOf(Math.round(((float) getH()) / displayMetrics.density)));
        hashMap.put(IXAdRequestInfo.SN, systemUtils.getSn(this.d));
        try {
            obj = "";
            List cell = systemUtils.getCell(this.d);
            if (cell.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < cell.size(); i++) {
                    String[] strArr = (String[]) cell.get(i);
                    stringBuilder.append(String.format("%s_%s_%s|", new Object[]{strArr[0], strArr[1], strArr[2]}));
                }
                obj = stringBuilder.substring(0, stringBuilder.length() - 1);
            }
        } catch (Exception e) {
            obj = "";
        }
        try {
            Object obj2;
            hashMap.put(IXAdRequestInfo.CELL_ID, obj);
            hashMap.put(IXAdRequestInfo.NETWORK_OPERATOR, systemUtils.getNetworkOperator(this.d));
            hashMap.put(IXAdRequestInfo.IMSI, commonUtils.getSubscriberId(this.d));
            try {
                str = "";
                if (systemUtils.getGPS(this.d) != null) {
                    System.currentTimeMillis();
                    str = String.format("%s_%s_%s", new Object[]{Double.valueOf(r1[0]), Double.valueOf(r1[1]), Double.valueOf(r1[2])});
                }
                obj2 = str;
            } catch (Exception e2) {
                obj2 = "";
            }
            hashMap.put(IXAdRequestInfo.GPS, obj2);
            try {
                obj2 = "";
                List wifi = systemUtils.getWIFI(this.d);
                if (wifi.size() > 0) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    for (int i2 = 0; i2 < wifi.size(); i2++) {
                        strArr = (String[]) wifi.get(i2);
                        stringBuilder2.append(String.format("%s_%s|", new Object[]{strArr[0], strArr[1]}));
                    }
                    obj = stringBuilder2.substring(0, stringBuilder2.length() - 1);
                } else {
                    obj = obj2;
                }
            } catch (Exception e3) {
                obj = obj2;
            }
            hashMap.put(IXAdRequestInfo.WIFI, obj);
            hashMap.put("swi", "" + (IXAdSystemUtils.NT_WIFI.equals(systemUtils.getNetworkType(this.d)) ? 1 : 0));
            hashMap.put("tab", systemUtils.isTablet(this.d) ? "1" : "0");
            hashMap.put("sdc", systemUtils.getAppSDC() + "," + systemUtils.getMem());
            hashMap.put(SocialConstants.PARAM_ACT, getAct());
            hashMap.put("prod", getProd());
            hashMap.put("os", AlibcConstants.PF_ANDROID);
            hashMap.put(IXAdRequestInfo.OSV, VERSION.RELEASE);
            hashMap.put(IXAdRequestInfo.BDR, "" + VERSION.SDK_INT);
            hashMap.put("apinfo", commonUtils.getBaiduMapsInfo(this.d));
            hashMap.put("apid", getApid());
            hashMap.put("chid", commonUtils.getChannelId());
            hashMap.put("apt", "0");
            hashMap.put("ap", "" + getAp());
            hashMap.put("nt", systemUtils.getNetType(this.d));
            hashMap.put("udid", "");
            hashMap.put("ses", "" + getSes());
            hashMap.put("android_id", systemUtils.getAndroidId(this.d));
            hashMap.put("imei", systemUtils.getIMEI(this.d));
            hashMap.put("mac", systemUtils.getMacAddress(this.d));
            hashMap.put("cuid", systemUtils.getCUID(this.d));
            hashMap.put("snfrom", systemUtils.getSnFrom(this.d));
            hashMap.put(IXAdRequestInfo.P_VER, "8.7018");
            hashMap.put("req_id", commonUtils.createRequestId(this.d, getApid()));
            hashMap.put("cssid", systemUtils.isWifiConnected(this.d).booleanValue() ? systemUtils.getWifiConnected(this.d) : "");
            if (AdSettings.getSupportHttps().equals(b.HTTPS_PROTOCOL_TYPE.a())) {
                hashMap.put("rpt", String.valueOf(b.HTTPS_PROTOCOL_TYPE.a()));
            }
        } catch (Exception e4) {
        }
        return hashMap;
    }

    public String b() {
        HashMap e = e();
        e.putAll(a());
        return XAdSDKFoundationFacade.getInstance().getURIUitls().getRequestAdUrl(this.b, e);
    }

    public boolean isCanClick() {
        return this.s;
    }

    public void a(boolean z) {
        this.s = z;
    }

    public String f() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public int getW() {
        return this.k;
    }

    public void d(int i) {
        this.k = i;
    }

    public int getH() {
        return this.l;
    }

    public void e(int i) {
        this.l = i;
    }

    public String getAct() {
        return this.n;
    }

    public void b(String str) {
        this.n = str;
    }

    public String getProd() {
        return this.o;
    }

    public void c(String str) {
        this.o = str;
    }

    public int getApt() {
        return this.p;
    }

    public void f(int i) {
        this.p = i;
    }

    public int getN() {
        return this.r;
    }

    public void g(int i) {
        this.r = i;
    }

    public String getUk() {
        return "";
    }

    public String getSex() {
        return "";
    }

    public String getZip() {
        return "";
    }

    public long getSes() {
        return this.t;
    }

    public int getAp() {
        return this.q;
    }

    public void h(int i) {
        this.q = i;
    }

    public String getApid() {
        return this.j;
    }

    public void d(String str) {
        this.j = str;
    }

    public int getAt() {
        return this.m;
    }

    public void i(int i) {
        this.m = i;
    }

    private void a(Context context) {
        if ("0.0".equals(a.c)) {
            double b = g.b(context);
            if (b > 0.0d) {
                a.c = String.valueOf(b);
            }
        }
    }
}
