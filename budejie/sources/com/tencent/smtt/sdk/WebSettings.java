package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings.LayoutAlgorithm;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings.TextSize;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings.ZoomDensity;
import com.tencent.smtt.utils.v;

public class WebSettings {
    public static final int LOAD_CACHE_ELSE_NETWORK = 1;
    public static final int LOAD_CACHE_ONLY = 3;
    public static final int LOAD_DEFAULT = -1;
    public static final int LOAD_NORMAL = 0;
    public static final int LOAD_NO_CACHE = 2;
    private IX5WebSettings a;
    private android.webkit.WebSettings b;
    private boolean c;

    public enum PluginState {
        ON,
        ON_DEMAND,
        OFF
    }

    public enum RenderPriority {
        NORMAL,
        HIGH,
        LOW
    }

    WebSettings(android.webkit.WebSettings webSettings) {
        this.a = null;
        this.b = null;
        this.c = false;
        this.a = null;
        this.b = webSettings;
        this.c = false;
    }

    WebSettings(IX5WebSettings iX5WebSettings) {
        this.a = null;
        this.b = null;
        this.c = false;
        this.a = iX5WebSettings;
        this.b = null;
        this.c = true;
    }

    @TargetApi(17)
    public static String getDefaultUserAgent(Context context) {
        if (bi.b().c() || VERSION.SDK_INT < 17) {
            return null;
        }
        Object a = v.a(android.webkit.WebSettings.class, "getDefaultUserAgent", new Class[]{Context.class}, new Object[]{context});
        return a == null ? null : (String) a;
    }

    @Deprecated
    public boolean enableSmoothTransition() {
        if (this.c && this.a != null) {
            return this.a.enableSmoothTransition();
        }
        if (this.c || this.b == null || VERSION.SDK_INT < 11) {
            return false;
        }
        Object a = v.a(this.b, "enableSmoothTransition");
        return a == null ? false : ((Boolean) a).booleanValue();
    }

    @TargetApi(11)
    public boolean getAllowContentAccess() {
        if (this.c && this.a != null) {
            return this.a.getAllowContentAccess();
        }
        if (this.c || this.b == null || VERSION.SDK_INT < 11) {
            return false;
        }
        Object a = v.a(this.b, "getAllowContentAccess");
        return a == null ? false : ((Boolean) a).booleanValue();
    }

    @TargetApi(3)
    public boolean getAllowFileAccess() {
        return (!this.c || this.a == null) ? (this.c || this.b == null) ? false : this.b.getAllowFileAccess() : this.a.getAllowFileAccess();
    }

    public synchronized boolean getBlockNetworkImage() {
        boolean blockNetworkImage;
        blockNetworkImage = (!this.c || this.a == null) ? (this.c || this.b == null) ? false : this.b.getBlockNetworkImage() : this.a.getBlockNetworkImage();
        return blockNetworkImage;
    }

    @TargetApi(8)
    public synchronized boolean getBlockNetworkLoads() {
        boolean z = false;
        synchronized (this) {
            if (this.c && this.a != null) {
                z = this.a.getBlockNetworkLoads();
            } else if (!(this.c || this.b == null || VERSION.SDK_INT < 8)) {
                z = this.b.getBlockNetworkLoads();
            }
        }
        return z;
    }

    @TargetApi(3)
    public boolean getBuiltInZoomControls() {
        return (!this.c || this.a == null) ? (this.c || this.b == null) ? false : this.b.getBuiltInZoomControls() : this.a.getBuiltInZoomControls();
    }

    public int getCacheMode() {
        return (!this.c || this.a == null) ? (this.c || this.b == null) ? 0 : this.b.getCacheMode() : this.a.getCacheMode();
    }

    public synchronized String getCursiveFontFamily() {
        String cursiveFontFamily;
        cursiveFontFamily = (!this.c || this.a == null) ? (this.c || this.b == null) ? "" : this.b.getCursiveFontFamily() : this.a.getCursiveFontFamily();
        return cursiveFontFamily;
    }

    @TargetApi(5)
    public synchronized boolean getDatabaseEnabled() {
        boolean databaseEnabled;
        databaseEnabled = (!this.c || this.a == null) ? (this.c || this.b == null) ? false : this.b.getDatabaseEnabled() : this.a.getDatabaseEnabled();
        return databaseEnabled;
    }

    @TargetApi(5)
    public synchronized String getDatabasePath() {
        String databasePath;
        databasePath = (!this.c || this.a == null) ? (this.c || this.b == null) ? "" : this.b.getDatabasePath() : this.a.getDatabasePath();
        return databasePath;
    }

    public synchronized int getDefaultFixedFontSize() {
        int defaultFixedFontSize;
        defaultFixedFontSize = (!this.c || this.a == null) ? (this.c || this.b == null) ? 0 : this.b.getDefaultFixedFontSize() : this.a.getDefaultFixedFontSize();
        return defaultFixedFontSize;
    }

    public synchronized int getDefaultFontSize() {
        int defaultFontSize;
        defaultFontSize = (!this.c || this.a == null) ? (this.c || this.b == null) ? 0 : this.b.getDefaultFontSize() : this.a.getDefaultFontSize();
        return defaultFontSize;
    }

    public synchronized String getDefaultTextEncodingName() {
        String defaultTextEncodingName;
        defaultTextEncodingName = (!this.c || this.a == null) ? (this.c || this.b == null) ? "" : this.b.getDefaultTextEncodingName() : this.a.getDefaultTextEncodingName();
        return defaultTextEncodingName;
    }

    @TargetApi(7)
    public WebSettings$ZoomDensity getDefaultZoom() {
        return (!this.c || this.a == null) ? (this.c || this.b == null) ? null : WebSettings$ZoomDensity.valueOf(this.b.getDefaultZoom().name()) : WebSettings$ZoomDensity.valueOf(this.a.getDefaultZoom().name());
    }

    @TargetApi(11)
    public boolean getDisplayZoomControls() {
        if (this.c && this.a != null) {
            return this.a.getDisplayZoomControls();
        }
        if (this.c || this.b == null || VERSION.SDK_INT < 11) {
            return false;
        }
        Object a = v.a(this.b, "getDisplayZoomControls");
        return a == null ? false : ((Boolean) a).booleanValue();
    }

    @TargetApi(7)
    public synchronized boolean getDomStorageEnabled() {
        boolean domStorageEnabled;
        domStorageEnabled = (!this.c || this.a == null) ? (this.c || this.b == null) ? false : this.b.getDomStorageEnabled() : this.a.getDomStorageEnabled();
        return domStorageEnabled;
    }

    public synchronized String getFantasyFontFamily() {
        String fantasyFontFamily;
        fantasyFontFamily = (!this.c || this.a == null) ? (this.c || this.b == null) ? "" : this.b.getFantasyFontFamily() : this.a.getFantasyFontFamily();
        return fantasyFontFamily;
    }

    public synchronized String getFixedFontFamily() {
        String fixedFontFamily;
        fixedFontFamily = (!this.c || this.a == null) ? (this.c || this.b == null) ? "" : this.b.getFixedFontFamily() : this.a.getFixedFontFamily();
        return fixedFontFamily;
    }

    public synchronized boolean getJavaScriptCanOpenWindowsAutomatically() {
        boolean javaScriptCanOpenWindowsAutomatically;
        javaScriptCanOpenWindowsAutomatically = (!this.c || this.a == null) ? (this.c || this.b == null) ? false : this.b.getJavaScriptCanOpenWindowsAutomatically() : this.a.getJavaScriptCanOpenWindowsAutomatically();
        return javaScriptCanOpenWindowsAutomatically;
    }

    public synchronized boolean getJavaScriptEnabled() {
        boolean javaScriptEnabled;
        javaScriptEnabled = (!this.c || this.a == null) ? (this.c || this.b == null) ? false : this.b.getJavaScriptEnabled() : this.a.getJavaScriptEnabled();
        return javaScriptEnabled;
    }

    public synchronized WebSettings$LayoutAlgorithm getLayoutAlgorithm() {
        WebSettings$LayoutAlgorithm valueOf;
        valueOf = (!this.c || this.a == null) ? (this.c || this.b == null) ? null : WebSettings$LayoutAlgorithm.valueOf(this.b.getLayoutAlgorithm().name()) : WebSettings$LayoutAlgorithm.valueOf(this.a.getLayoutAlgorithm().name());
        return valueOf;
    }

    public boolean getLightTouchEnabled() {
        return (!this.c || this.a == null) ? (this.c || this.b == null) ? false : this.b.getLightTouchEnabled() : this.a.getLightTouchEnabled();
    }

    @TargetApi(7)
    public boolean getLoadWithOverviewMode() {
        return (!this.c || this.a == null) ? (this.c || this.b == null) ? false : this.b.getLoadWithOverviewMode() : this.a.getLoadWithOverviewMode();
    }

    public synchronized boolean getLoadsImagesAutomatically() {
        boolean loadsImagesAutomatically;
        loadsImagesAutomatically = (!this.c || this.a == null) ? (this.c || this.b == null) ? false : this.b.getLoadsImagesAutomatically() : this.a.getLoadsImagesAutomatically();
        return loadsImagesAutomatically;
    }

    @TargetApi(17)
    public boolean getMediaPlaybackRequiresUserGesture() {
        if (this.c && this.a != null) {
            return this.a.getMediaPlaybackRequiresUserGesture();
        }
        if (this.c || this.b == null || VERSION.SDK_INT < 17) {
            return false;
        }
        Object a = v.a(this.b, "getMediaPlaybackRequiresUserGesture");
        return a == null ? false : ((Boolean) a).booleanValue();
    }

    public synchronized int getMinimumFontSize() {
        int minimumFontSize;
        minimumFontSize = (!this.c || this.a == null) ? (this.c || this.b == null) ? 0 : this.b.getMinimumFontSize() : this.a.getMinimumFontSize();
        return minimumFontSize;
    }

    public synchronized int getMinimumLogicalFontSize() {
        int minimumLogicalFontSize;
        minimumLogicalFontSize = (!this.c || this.a == null) ? (this.c || this.b == null) ? 0 : this.b.getMinimumLogicalFontSize() : this.a.getMinimumLogicalFontSize();
        return minimumLogicalFontSize;
    }

    public synchronized int getMixedContentMode() {
        int i = -1;
        synchronized (this) {
            if (this.c && this.a != null) {
                try {
                    i = this.a.getMixedContentMode();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            } else if (VERSION.SDK_INT >= 21) {
                Object a = v.a(this.b, "getMixedContentMode", new Class[0], new Object[0]);
                i = a == null ? i : ((Integer) a).intValue();
            }
        }
        return i;
    }

    public boolean getNavDump() {
        if (this.c && this.a != null) {
            return this.a.getNavDump();
        }
        if (this.c || this.b == null) {
            return false;
        }
        Object a = v.a(this.b, "getNavDump");
        return a == null ? false : ((Boolean) a).booleanValue();
    }

    @TargetApi(8)
    @Deprecated
    public synchronized PluginState getPluginState() {
        PluginState valueOf;
        if (this.c && this.a != null) {
            valueOf = PluginState.valueOf(this.a.getPluginState().name());
        } else if (this.c || this.b == null) {
            valueOf = null;
        } else if (VERSION.SDK_INT >= 8) {
            Object a = v.a(this.b, "getPluginState");
            valueOf = a == null ? null : PluginState.valueOf(((android.webkit.WebSettings.PluginState) a).name());
        } else {
            valueOf = null;
        }
        return valueOf;
    }

    @TargetApi(8)
    @Deprecated
    public synchronized boolean getPluginsEnabled() {
        boolean z = false;
        synchronized (this) {
            if (this.c && this.a != null) {
                z = this.a.getPluginsEnabled();
            } else if (!(this.c || this.b == null)) {
                if (VERSION.SDK_INT <= 17) {
                    Object a = v.a(this.b, "getPluginsEnabled");
                    z = a == null ? false : ((Boolean) a).booleanValue();
                } else if (VERSION.SDK_INT == 18 && android.webkit.WebSettings.PluginState.ON == this.b.getPluginState()) {
                    z = true;
                }
            }
        }
        return z;
    }

    @Deprecated
    public synchronized String getPluginsPath() {
        String pluginsPath;
        if (this.c && this.a != null) {
            pluginsPath = this.a.getPluginsPath();
        } else if (this.c || this.b == null) {
            pluginsPath = "";
        } else if (VERSION.SDK_INT <= 17) {
            Object a = v.a(this.b, "getPluginsPath");
            pluginsPath = a == null ? null : (String) a;
        } else {
            pluginsPath = "";
        }
        return pluginsPath;
    }

    public synchronized String getSansSerifFontFamily() {
        String sansSerifFontFamily;
        sansSerifFontFamily = (!this.c || this.a == null) ? (this.c || this.b == null) ? "" : this.b.getSansSerifFontFamily() : this.a.getSansSerifFontFamily();
        return sansSerifFontFamily;
    }

    public boolean getSaveFormData() {
        return (!this.c || this.a == null) ? (this.c || this.b == null) ? false : this.b.getSaveFormData() : this.a.getSaveFormData();
    }

    public boolean getSavePassword() {
        return (!this.c || this.a == null) ? (this.c || this.b == null) ? false : this.b.getSavePassword() : this.a.getSavePassword();
    }

    public synchronized String getSerifFontFamily() {
        String serifFontFamily;
        serifFontFamily = (!this.c || this.a == null) ? (this.c || this.b == null) ? "" : this.b.getSerifFontFamily() : this.a.getSerifFontFamily();
        return serifFontFamily;
    }

    public synchronized String getStandardFontFamily() {
        String standardFontFamily;
        standardFontFamily = (!this.c || this.a == null) ? (this.c || this.b == null) ? "" : this.b.getStandardFontFamily() : this.a.getStandardFontFamily();
        return standardFontFamily;
    }

    public WebSettings$TextSize getTextSize() {
        return (!this.c || this.a == null) ? (this.c || this.b == null) ? null : WebSettings$TextSize.valueOf(this.b.getTextSize().name()) : WebSettings$TextSize.valueOf(this.a.getTextSize().name());
    }

    @TargetApi(14)
    public synchronized int getTextZoom() {
        int i = 0;
        synchronized (this) {
            if (this.c && this.a != null) {
                i = this.a.getTextZoom();
            } else if (!(this.c || this.b == null || VERSION.SDK_INT < 14)) {
                Object a = v.a(this.b, "getTextZoom");
                i = a == null ? 0 : ((Integer) a).intValue();
            }
        }
        return i;
    }

    @Deprecated
    public boolean getUseWebViewBackgroundForOverscrollBackground() {
        if (this.c && this.a != null) {
            return this.a.getUseWebViewBackgroundForOverscrollBackground();
        }
        if (this.c || this.b == null) {
            return false;
        }
        Object a = v.a(this.b, "getUseWebViewBackgroundForOverscrollBackground");
        return a == null ? false : ((Boolean) a).booleanValue();
    }

    public synchronized boolean getUseWideViewPort() {
        boolean useWideViewPort;
        useWideViewPort = (!this.c || this.a == null) ? (this.c || this.b == null) ? false : this.b.getUseWideViewPort() : this.a.getUseWideViewPort();
        return useWideViewPort;
    }

    @TargetApi(3)
    public String getUserAgentString() {
        return (!this.c || this.a == null) ? (this.c || this.b == null) ? "" : this.b.getUserAgentString() : this.a.getUserAgentString();
    }

    @TargetApi(11)
    public void setAllowContentAccess(boolean z) {
        if (this.c && this.a != null) {
            this.a.setAllowContentAccess(z);
        } else if (!this.c && this.b != null && VERSION.SDK_INT >= 11) {
            v.a(this.b, "setAllowContentAccess", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
        }
    }

    @TargetApi(3)
    public void setAllowFileAccess(boolean z) {
        if (this.c && this.a != null) {
            this.a.setAllowFileAccess(z);
        } else if (!this.c && this.b != null) {
            this.b.setAllowFileAccess(z);
        }
    }

    @TargetApi(16)
    public void setAllowFileAccessFromFileURLs(boolean z) {
        if (this.c && this.a != null) {
            this.a.setAllowFileAccessFromFileURLs(z);
        } else if (!this.c && this.b != null) {
            v.a(this.b, "setAllowFileAccessFromFileURLs", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
        }
    }

    @TargetApi(16)
    public void setAllowUniversalAccessFromFileURLs(boolean z) {
        if (this.c && this.a != null) {
            this.a.setAllowUniversalAccessFromFileURLs(z);
        } else if (!this.c && this.b != null) {
            v.a(this.b, "setAllowUniversalAccessFromFileURLs", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
        }
    }

    @TargetApi(7)
    public void setAppCacheEnabled(boolean z) {
        if (this.c && this.a != null) {
            this.a.setAppCacheEnabled(z);
        } else if (!this.c && this.b != null) {
            this.b.setAppCacheEnabled(z);
        }
    }

    @TargetApi(7)
    public void setAppCacheMaxSize(long j) {
        if (this.c && this.a != null) {
            this.a.setAppCacheMaxSize(j);
        } else if (!this.c && this.b != null) {
            this.b.setAppCacheMaxSize(j);
        }
    }

    @TargetApi(7)
    public void setAppCachePath(String str) {
        if (this.c && this.a != null) {
            this.a.setAppCachePath(str);
        } else if (!this.c && this.b != null) {
            this.b.setAppCachePath(str);
        }
    }

    public void setBlockNetworkImage(boolean z) {
        if (this.c && this.a != null) {
            this.a.setBlockNetworkImage(z);
        } else if (!this.c && this.b != null) {
            this.b.setBlockNetworkImage(z);
        }
    }

    @TargetApi(8)
    public synchronized void setBlockNetworkLoads(boolean z) {
        if (this.c && this.a != null) {
            this.a.setBlockNetworkLoads(z);
        } else if (!(this.c || this.b == null || VERSION.SDK_INT < 8)) {
            this.b.setBlockNetworkLoads(z);
        }
    }

    @TargetApi(3)
    public void setBuiltInZoomControls(boolean z) {
        if (this.c && this.a != null) {
            this.a.setBuiltInZoomControls(z);
        } else if (!this.c && this.b != null) {
            this.b.setBuiltInZoomControls(z);
        }
    }

    public void setCacheMode(int i) {
        if (this.c && this.a != null) {
            this.a.setCacheMode(i);
        } else if (!this.c && this.b != null) {
            this.b.setCacheMode(i);
        }
    }

    public synchronized void setCursiveFontFamily(String str) {
        if (this.c && this.a != null) {
            this.a.setCursiveFontFamily(str);
        } else if (!(this.c || this.b == null)) {
            this.b.setCursiveFontFamily(str);
        }
    }

    @TargetApi(5)
    public void setDatabaseEnabled(boolean z) {
        if (this.c && this.a != null) {
            this.a.setDatabaseEnabled(z);
        } else if (!this.c && this.b != null) {
            this.b.setDatabaseEnabled(z);
        }
    }

    @TargetApi(5)
    @Deprecated
    public void setDatabasePath(String str) {
        if (this.c && this.a != null) {
            this.a.setDatabasePath(str);
        } else if (!this.c && this.b != null) {
            v.a(this.b, "setDatabasePath", new Class[]{String.class}, new Object[]{str});
        }
    }

    public synchronized void setDefaultFixedFontSize(int i) {
        if (this.c && this.a != null) {
            this.a.setDefaultFixedFontSize(i);
        } else if (!(this.c || this.b == null)) {
            this.b.setDefaultFixedFontSize(i);
        }
    }

    public synchronized void setDefaultFontSize(int i) {
        if (this.c && this.a != null) {
            this.a.setDefaultFontSize(i);
        } else if (!(this.c || this.b == null)) {
            this.b.setDefaultFontSize(i);
        }
    }

    public synchronized void setDefaultTextEncodingName(String str) {
        if (this.c && this.a != null) {
            this.a.setDefaultTextEncodingName(str);
        } else if (!(this.c || this.b == null)) {
            this.b.setDefaultTextEncodingName(str);
        }
    }

    @TargetApi(7)
    public void setDefaultZoom(WebSettings$ZoomDensity webSettings$ZoomDensity) {
        if (this.c && this.a != null) {
            this.a.setDefaultZoom(ZoomDensity.valueOf(webSettings$ZoomDensity.name()));
        } else if (!this.c && this.b != null) {
            this.b.setDefaultZoom(android.webkit.WebSettings.ZoomDensity.valueOf(webSettings$ZoomDensity.name()));
        }
    }

    @TargetApi(11)
    public void setDisplayZoomControls(boolean z) {
        if (this.c && this.a != null) {
            this.a.setDisplayZoomControls(z);
        } else if (!this.c && this.b != null && VERSION.SDK_INT >= 11) {
            v.a(this.b, "setDisplayZoomControls", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
        }
    }

    @TargetApi(7)
    public void setDomStorageEnabled(boolean z) {
        if (this.c && this.a != null) {
            this.a.setDomStorageEnabled(z);
        } else if (!this.c && this.b != null) {
            this.b.setDomStorageEnabled(z);
        }
    }

    @TargetApi(11)
    @Deprecated
    public void setEnableSmoothTransition(boolean z) {
        if (this.c && this.a != null) {
            this.a.setEnableSmoothTransition(z);
        } else if (!this.c && this.b != null && VERSION.SDK_INT >= 11) {
            v.a(this.b, "setEnableSmoothTransition", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
        }
    }

    public synchronized void setFantasyFontFamily(String str) {
        if (this.c && this.a != null) {
            this.a.setFantasyFontFamily(str);
        } else if (!(this.c || this.b == null)) {
            this.b.setFantasyFontFamily(str);
        }
    }

    public synchronized void setFixedFontFamily(String str) {
        if (this.c && this.a != null) {
            this.a.setFixedFontFamily(str);
        } else if (!(this.c || this.b == null)) {
            this.b.setFixedFontFamily(str);
        }
    }

    @TargetApi(5)
    public void setGeolocationDatabasePath(String str) {
        if (this.c && this.a != null) {
            this.a.setGeolocationDatabasePath(str);
        } else if (!this.c && this.b != null) {
            this.b.setGeolocationDatabasePath(str);
        }
    }

    @TargetApi(5)
    public void setGeolocationEnabled(boolean z) {
        if (this.c && this.a != null) {
            this.a.setGeolocationEnabled(z);
        } else if (!this.c && this.b != null) {
            this.b.setGeolocationEnabled(z);
        }
    }

    public synchronized void setJavaScriptCanOpenWindowsAutomatically(boolean z) {
        if (this.c && this.a != null) {
            this.a.setJavaScriptCanOpenWindowsAutomatically(z);
        } else if (!(this.c || this.b == null)) {
            this.b.setJavaScriptCanOpenWindowsAutomatically(z);
        }
    }

    @Deprecated
    public void setJavaScriptEnabled(boolean z) {
        try {
            if (this.c && this.a != null) {
                this.a.setJavaScriptEnabled(z);
            } else if (!this.c && this.b != null) {
                this.b.setJavaScriptEnabled(z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setLayoutAlgorithm(WebSettings$LayoutAlgorithm webSettings$LayoutAlgorithm) {
        if (this.c && this.a != null) {
            this.a.setLayoutAlgorithm(LayoutAlgorithm.valueOf(webSettings$LayoutAlgorithm.name()));
        } else if (!this.c && this.b != null) {
            this.b.setLayoutAlgorithm(android.webkit.WebSettings.LayoutAlgorithm.valueOf(webSettings$LayoutAlgorithm.name()));
        }
    }

    public void setLightTouchEnabled(boolean z) {
        if (this.c && this.a != null) {
            this.a.setLightTouchEnabled(z);
        } else if (!this.c && this.b != null) {
            this.b.setLightTouchEnabled(z);
        }
    }

    @TargetApi(7)
    public void setLoadWithOverviewMode(boolean z) {
        if (this.c && this.a != null) {
            this.a.setLoadWithOverviewMode(z);
        } else if (!this.c && this.b != null) {
            this.b.setLoadWithOverviewMode(z);
        }
    }

    public void setLoadsImagesAutomatically(boolean z) {
        if (this.c && this.a != null) {
            this.a.setLoadsImagesAutomatically(z);
        } else if (!this.c && this.b != null) {
            this.b.setLoadsImagesAutomatically(z);
        }
    }

    @TargetApi(17)
    public void setMediaPlaybackRequiresUserGesture(boolean z) {
        if (this.c && this.a != null) {
            this.a.setMediaPlaybackRequiresUserGesture(z);
        } else if (!this.c && this.b != null && VERSION.SDK_INT >= 17) {
            v.a(this.b, "setMediaPlaybackRequiresUserGesture", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
        }
    }

    public synchronized void setMinimumFontSize(int i) {
        if (this.c && this.a != null) {
            this.a.setMinimumFontSize(i);
        } else if (!(this.c || this.b == null)) {
            this.b.setMinimumFontSize(i);
        }
    }

    public synchronized void setMinimumLogicalFontSize(int i) {
        if (this.c && this.a != null) {
            this.a.setMinimumLogicalFontSize(i);
        } else if (!(this.c || this.b == null)) {
            this.b.setMinimumLogicalFontSize(i);
        }
    }

    @TargetApi(21)
    public void setMixedContentMode(int i) {
        if ((!this.c || this.a == null) && !this.c && this.b != null && VERSION.SDK_INT >= 21) {
            v.a(this.b, "setMixedContentMode", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(i)});
        }
    }

    public void setNavDump(boolean z) {
        if (this.c && this.a != null) {
            this.a.setNavDump(z);
        } else if (!this.c && this.b != null) {
            v.a(this.b, "setNavDump", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
        }
    }

    public void setNeedInitialFocus(boolean z) {
        if (this.c && this.a != null) {
            this.a.setNeedInitialFocus(z);
        } else if (!this.c && this.b != null) {
            this.b.setNeedInitialFocus(z);
        }
    }

    @TargetApi(8)
    @Deprecated
    public synchronized void setPluginState(PluginState pluginState) {
        if (this.c && this.a != null) {
            this.a.setPluginState(com.tencent.smtt.export.external.interfaces.IX5WebSettings.PluginState.valueOf(pluginState.name()));
        } else if (!(this.c || this.b == null || VERSION.SDK_INT < 8)) {
            android.webkit.WebSettings.PluginState valueOf = android.webkit.WebSettings.PluginState.valueOf(pluginState.name());
            v.a(this.b, "setPluginState", new Class[]{android.webkit.WebSettings.PluginState.class}, new Object[]{valueOf});
        }
    }

    @Deprecated
    public void setPluginsEnabled(boolean z) {
        if (this.c && this.a != null) {
            this.a.setPluginsEnabled(z);
        } else if (!this.c && this.b != null) {
            v.a(this.b, "setPluginsEnabled", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
        }
    }

    @Deprecated
    public synchronized void setPluginsPath(String str) {
        if (this.c && this.a != null) {
            this.a.setPluginsPath(str);
        } else if (!(this.c || this.b == null)) {
            v.a(this.b, "setPluginsPath", new Class[]{String.class}, new Object[]{str});
        }
    }

    public void setRenderPriority(RenderPriority renderPriority) {
        if (this.c && this.a != null) {
            this.a.setRenderPriority(com.tencent.smtt.export.external.interfaces.IX5WebSettings.RenderPriority.valueOf(renderPriority.name()));
        } else if (!this.c && this.b != null) {
            this.b.setRenderPriority(android.webkit.WebSettings.RenderPriority.valueOf(renderPriority.name()));
        }
    }

    public synchronized void setSansSerifFontFamily(String str) {
        if (this.c && this.a != null) {
            this.a.setSansSerifFontFamily(str);
        } else if (!(this.c || this.b == null)) {
            this.b.setSansSerifFontFamily(str);
        }
    }

    public void setSaveFormData(boolean z) {
        if (this.c && this.a != null) {
            this.a.setSaveFormData(z);
        } else if (!this.c && this.b != null) {
            this.b.setSaveFormData(z);
        }
    }

    public void setSavePassword(boolean z) {
        if (this.c && this.a != null) {
            this.a.setSavePassword(z);
        } else if (!this.c && this.b != null) {
            this.b.setSavePassword(z);
        }
    }

    public synchronized void setSerifFontFamily(String str) {
        if (this.c && this.a != null) {
            this.a.setSerifFontFamily(str);
        } else if (!(this.c || this.b == null)) {
            this.b.setSerifFontFamily(str);
        }
    }

    public synchronized void setStandardFontFamily(String str) {
        if (this.c && this.a != null) {
            this.a.setStandardFontFamily(str);
        } else if (!(this.c || this.b == null)) {
            this.b.setStandardFontFamily(str);
        }
    }

    public void setSupportMultipleWindows(boolean z) {
        if (this.c && this.a != null) {
            this.a.setSupportMultipleWindows(z);
        } else if (!this.c && this.b != null) {
            this.b.setSupportMultipleWindows(z);
        }
    }

    public void setSupportZoom(boolean z) {
        if (this.c && this.a != null) {
            this.a.setSupportZoom(z);
        } else if (!this.c && this.b != null) {
            this.b.setSupportZoom(z);
        }
    }

    public void setTextSize(WebSettings$TextSize webSettings$TextSize) {
        if (this.c && this.a != null) {
            this.a.setTextSize(TextSize.valueOf(webSettings$TextSize.name()));
        } else if (!this.c && this.b != null) {
            this.b.setTextSize(android.webkit.WebSettings.TextSize.valueOf(webSettings$TextSize.name()));
        }
    }

    @TargetApi(14)
    public synchronized void setTextZoom(int i) {
        if (this.c && this.a != null) {
            this.a.setTextZoom(i);
        } else if (!(this.c || this.b == null || VERSION.SDK_INT < 14)) {
            v.a(this.b, "setTextZoom", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(i)});
        }
    }

    @Deprecated
    public void setUseWebViewBackgroundForOverscrollBackground(boolean z) {
        if (this.c && this.a != null) {
            this.a.setUseWebViewBackgroundForOverscrollBackground(z);
        } else if (!this.c && this.b != null) {
            v.a(this.b, "setUseWebViewBackgroundForOverscrollBackground", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
        }
    }

    public void setUseWideViewPort(boolean z) {
        if (this.c && this.a != null) {
            this.a.setUseWideViewPort(z);
        } else if (!this.c && this.b != null) {
            this.b.setUseWideViewPort(z);
        }
    }

    public void setUserAgent(String str) {
        if (this.c && this.a != null) {
            this.a.setUserAgent(str);
        } else if (!this.c && this.b != null) {
            this.b.setUserAgentString(str);
        }
    }

    @TargetApi(3)
    public void setUserAgentString(String str) {
        if (this.c && this.a != null) {
            this.a.setUserAgentString(str);
        } else if (!this.c && this.b != null) {
            this.b.setUserAgentString(str);
        }
    }

    public synchronized boolean supportMultipleWindows() {
        boolean supportMultipleWindows;
        supportMultipleWindows = (!this.c || this.a == null) ? (this.c || this.b == null) ? false : this.b.supportMultipleWindows() : this.a.supportMultipleWindows();
        return supportMultipleWindows;
    }

    public boolean supportZoom() {
        return (!this.c || this.a == null) ? (this.c || this.b == null) ? false : this.b.supportZoom() : this.a.supportZoom();
    }
}
