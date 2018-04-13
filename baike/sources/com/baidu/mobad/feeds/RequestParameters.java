package com.baidu.mobad.feeds;

import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import qsbk.app.core.utils.PictureGetHelper;
import qsbk.app.video.VideoEditActivity;

public final class RequestParameters implements IXAdFeedsRequestParameters {
    public static final int ADS_TYPE_DOWNLOAD = 2;
    public static final int ADS_TYPE_OPENPAGE = 1;
    public static final int DOWNLOAD_APP_CONFIRM_ALWAYS = 2;
    public static final int DOWNLOAD_APP_CONFIRM_CUSTOM_BY_APP = 4;
    public static final int DOWNLOAD_APP_CONFIRM_NEVER = 3;
    public static final int DOWNLOAD_APP_CONFIRM_ONLY_MOBILE = 1;
    public static final int MAX_ASSETS_RESERVED = 15;
    public static final String TAG = "RequestParameters";
    protected String a;
    private final String b;
    private int c;
    private boolean d;
    private Map<String, String> e;
    private int f;
    private int g;
    private int h;

    public static class Builder {
        private String a;
        private Map<String, String> b = new HashMap();
        private int c = 3;
        private boolean d = false;
        private int e = PictureGetHelper.IMAGE_SIZE;
        private int f = VideoEditActivity.MAX_VIDEO_WIDTH;
        private int g = 1;

        public final Builder setWidth(int i) {
            this.e = i;
            return this;
        }

        public final Builder setHeight(int i) {
            this.f = i;
            return this;
        }

        @Deprecated
        public final Builder confirmDownloading(boolean z) {
            if (z) {
                downloadAppConfirmPolicy(2);
            } else {
                downloadAppConfirmPolicy(3);
            }
            return this;
        }

        public final Builder downloadAppConfirmPolicy(int i) {
            this.g = i;
            return this;
        }

        public final Builder addExtra(String str, String str2) {
            this.b.put(str, str2);
            return this;
        }

        public final RequestParameters build() {
            return new RequestParameters();
        }
    }

    private RequestParameters(Builder builder) {
        this.f = 0;
        this.g = 0;
        this.b = builder.a;
        this.c = builder.c;
        this.f = builder.e;
        this.g = builder.f;
        this.d = builder.d;
        this.h = builder.g;
        setExtras(builder.b);
    }

    public final String getKeywords() {
        return this.b;
    }

    public int getWidth() {
        return this.f;
    }

    public int getHeight() {
        return this.g;
    }

    public int getAdsType() {
        return this.c;
    }

    public void setAdsType(int i) {
        this.c = i;
    }

    public boolean isConfirmDownloading() {
        return this.d;
    }

    public Map<String, String> getExtras() {
        return this.e;
    }

    public void setExtras(Map<String, String> map) {
        this.e = map;
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("mKeywords", this.b);
        hashMap.put("adsType", Integer.valueOf(this.c));
        hashMap.put("confirmDownloading", Boolean.valueOf(this.d));
        HashMap hashMap2 = new HashMap();
        if (this.e != null) {
            for (Entry entry : this.e.entrySet()) {
                hashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        hashMap.put("extras", hashMap2);
        return hashMap;
    }

    public String getAdPlacementId() {
        return this.a;
    }

    public int getAPPConfirmPolicy() {
        return this.h;
    }
}
