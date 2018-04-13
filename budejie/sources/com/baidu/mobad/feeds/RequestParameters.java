package com.baidu.mobad.feeds;

import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class RequestParameters implements IXAdFeedsRequestParameters {
    public static final int ADS_TYPE_DOWNLOAD = 2;
    public static final int ADS_TYPE_OPENPAGE = 1;
    public static final int DOWNLOAD_APP_CONFIRM_ALWAYS = 2;
    public static final int DOWNLOAD_APP_CONFIRM_CUSTOM_BY_APP = 4;
    public static final int DOWNLOAD_APP_CONFIRM_NEVER = 3;
    public static final int DOWNLOAD_APP_CONFIRM_ONLY_MOBILE = 1;
    public static final int MAX_ASSETS_RESERVED = 15;
    public static final String TAG = "RequestParameters";
    private final String a;
    private int b;
    private boolean c;
    private Map<String, String> d;
    private int e;
    private int f;
    private int g;
    protected String mPlacementId;

    private RequestParameters(RequestParameters$Builder requestParameters$Builder) {
        this.e = 0;
        this.f = 0;
        this.a = RequestParameters$Builder.a(requestParameters$Builder);
        this.b = RequestParameters$Builder.b(requestParameters$Builder);
        this.e = RequestParameters$Builder.c(requestParameters$Builder);
        this.f = RequestParameters$Builder.d(requestParameters$Builder);
        this.c = RequestParameters$Builder.e(requestParameters$Builder);
        this.g = RequestParameters$Builder.f(requestParameters$Builder);
        setExtras(RequestParameters$Builder.g(requestParameters$Builder));
    }

    public final String getKeywords() {
        return this.a;
    }

    public int getWidth() {
        return this.e;
    }

    public int getHeight() {
        return this.f;
    }

    public int getAdsType() {
        return this.b;
    }

    public void setAdsType(int i) {
        this.b = i;
    }

    public boolean isConfirmDownloading() {
        return this.c;
    }

    public Map<String, String> getExtras() {
        return this.d;
    }

    public void setExtras(Map<String, String> map) {
        this.d = map;
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("mKeywords", this.a);
        hashMap.put("adsType", Integer.valueOf(this.b));
        hashMap.put("confirmDownloading", Boolean.valueOf(this.c));
        HashMap hashMap2 = new HashMap();
        if (this.d != null) {
            for (Entry entry : this.d.entrySet()) {
                hashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        hashMap.put("extras", hashMap2);
        return hashMap;
    }

    public String getAdPlacementId() {
        return this.mPlacementId;
    }

    public int getAPPConfirmPolicy() {
        return this.g;
    }
}
