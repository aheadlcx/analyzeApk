package com.ut.mini;

import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public class UTPageHitHelper$UTPageEventObject {
    private long A = 0;
    private boolean L = false;
    private boolean M = false;
    private boolean N = false;
    private Uri a = null;
    /* renamed from: a */
    private UTPageStatus f76a = null;
    private String ai = null;
    private String aj = null;
    private String ak = null;
    private Map<String, String> w = new HashMap();

    public void setCacheKey(String str) {
        this.ak = str;
    }

    public String getCacheKey() {
        return this.ak;
    }

    public void resetPropertiesWithoutSkipFlagAndH5Flag() {
        this.w = new HashMap();
        this.A = 0;
        this.a = null;
        this.ai = null;
        this.aj = null;
        if (this.f76a == null || this.f76a != UTPageStatus.UT_H5_IN_WebView) {
            this.f76a = null;
        }
        this.L = false;
        this.N = false;
    }

    public boolean isH5Called() {
        return this.N;
    }

    public void setH5Called() {
        this.N = true;
    }

    public void setToSkipPage() {
        this.M = true;
    }

    public boolean isSkipPage() {
        return this.M;
    }

    public void setPageAppearCalled() {
        this.L = true;
    }

    public boolean isPageAppearCalled() {
        return this.L;
    }

    public void setPageStatus(UTPageStatus uTPageStatus) {
        this.f76a = uTPageStatus;
    }

    public UTPageStatus getPageStatus() {
        return this.f76a;
    }

    public Map<String, String> getPageProperties() {
        return this.w;
    }

    public void setPageProperties(Map<String, String> map) {
        this.w = map;
    }

    public long getPageStayTimstamp() {
        return this.A;
    }

    public void setPageStayTimstamp(long j) {
        this.A = j;
    }

    public Uri getPageUrl() {
        return this.a;
    }

    public void setPageUrl(Uri uri) {
        this.a = uri;
    }

    public void setPageName(String str) {
        this.ai = str;
    }

    public String getPageName() {
        return this.ai;
    }

    public void setRefPage(String str) {
        this.aj = str;
    }

    public String getRefPage() {
        return this.aj;
    }
}
