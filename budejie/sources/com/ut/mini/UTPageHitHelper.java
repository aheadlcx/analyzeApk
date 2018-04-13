package com.ut.mini;

import android.app.Activity;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.mtl.log.c;
import com.alibaba.mtl.log.e.i;
import com.ut.mini.UTHitBuilders.UTPageHitBuilder;
import com.ut.mini.base.UTMIVariables;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class UTPageHitHelper {
    private static UTPageHitHelper a = new UTPageHitHelper();
    private boolean K = false;
    /* renamed from: a */
    private Queue<UTPageHitHelper$UTPageEventObject> f12a = new LinkedList();
    private String ag = null;
    private String ah = null;
    private Map<String, String> w = new HashMap();
    private Map<String, UTPageHitHelper$UTPageEventObject> x = new HashMap();
    private Map<String, String> y = new HashMap();
    private Map<Object, String> z = new HashMap();

    public static UTPageHitHelper getInstance() {
        return a;
    }

    synchronized Map<String, String> c() {
        Map<String, String> map;
        if (this.y == null || this.y.size() <= 0) {
            map = null;
        } else {
            map = new HashMap();
            map.putAll(this.y);
            this.y.clear();
        }
        return map;
    }

    synchronized void a(UTPageHitHelper$UTPageEventObject uTPageHitHelper$UTPageEventObject) {
        uTPageHitHelper$UTPageEventObject.resetPropertiesWithoutSkipFlagAndH5Flag();
        if (!this.f12a.contains(uTPageHitHelper$UTPageEventObject)) {
            this.f12a.add(uTPageHitHelper$UTPageEventObject);
        }
        if (this.f12a.size() > 200) {
            for (int i = 0; i < 100; i++) {
                UTPageHitHelper$UTPageEventObject uTPageHitHelper$UTPageEventObject2 = (UTPageHitHelper$UTPageEventObject) this.f12a.poll();
                if (uTPageHitHelper$UTPageEventObject2 != null && this.x.containsKey(uTPageHitHelper$UTPageEventObject2.getCacheKey())) {
                    this.x.remove(uTPageHitHelper$UTPageEventObject2.getCacheKey());
                }
            }
        }
    }

    @Deprecated
    public synchronized void turnOffAutoPageTrack() {
        this.K = true;
    }

    public String getCurrentPageName() {
        return this.ah;
    }

    void pageAppearByAuto(Activity activity) {
        if (!this.K) {
            pageAppear(activity);
        }
    }

    /* renamed from: a */
    private String m4a(Object obj) {
        String str;
        if (obj instanceof String) {
            str = (String) obj;
        } else {
            str = obj.getClass().getSimpleName();
        }
        return str + obj.hashCode();
    }

    /* renamed from: a */
    synchronized boolean m7a(Object obj) {
        boolean z;
        if (obj != null) {
            UTPageHitHelper$UTPageEventObject a = a(obj);
            if (a.getPageStatus() != null && a.getPageStatus() == UTPageStatus.UT_H5_IN_WebView) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    /* renamed from: a */
    synchronized void m6a(Object obj) {
        if (obj != null) {
            UTPageHitHelper$UTPageEventObject a = a(obj);
            if (a.getPageStatus() != null) {
                a.setH5Called();
            }
        }
    }

    private synchronized UTPageHitHelper$UTPageEventObject a(Object obj) {
        UTPageHitHelper$UTPageEventObject uTPageHitHelper$UTPageEventObject;
        String a = a(obj);
        if (this.x.containsKey(a)) {
            uTPageHitHelper$UTPageEventObject = (UTPageHitHelper$UTPageEventObject) this.x.get(a);
        } else {
            uTPageHitHelper$UTPageEventObject = new UTPageHitHelper$UTPageEventObject();
            this.x.put(a, uTPageHitHelper$UTPageEventObject);
            uTPageHitHelper$UTPageEventObject.setCacheKey(a);
        }
        return uTPageHitHelper$UTPageEventObject;
    }

    private synchronized void a(String str, UTPageHitHelper$UTPageEventObject uTPageHitHelper$UTPageEventObject) {
        this.x.put(str, uTPageHitHelper$UTPageEventObject);
    }

    private synchronized void b(UTPageHitHelper$UTPageEventObject uTPageHitHelper$UTPageEventObject) {
        if (this.x.containsKey(uTPageHitHelper$UTPageEventObject.getCacheKey())) {
            this.x.remove(uTPageHitHelper$UTPageEventObject.getCacheKey());
        }
    }

    /* renamed from: b */
    private synchronized void m5b(Object obj) {
        String a = a(obj);
        if (this.x.containsKey(a)) {
            this.x.remove(a);
        }
    }

    @Deprecated
    public synchronized void pageAppear(Object obj) {
        a(obj, null, false);
    }

    synchronized void a(Object obj, String str, boolean z) {
        if (obj != null) {
            String a = a(obj);
            if (a == null || !a.equals(this.ag)) {
                if (this.ag != null) {
                    i.a("lost 2001", "Last page requires leave(" + this.ag + ").");
                }
                UTPageHitHelper$UTPageEventObject a2 = a(obj);
                if (z || !a2.isSkipPage()) {
                    a = UTMIVariables.getInstance().getH5Url();
                    if (a != null) {
                        try {
                            this.w.put("spm", Uri.parse(a).getQueryParameter("spm"));
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                        UTMIVariables.getInstance().setH5Url(null);
                    }
                    a = b(obj);
                    if (!TextUtils.isEmpty(str)) {
                        a = str;
                    }
                    if (!TextUtils.isEmpty(a2.getPageName())) {
                        a = a2.getPageName();
                    }
                    this.ah = a;
                    a2.setPageName(a);
                    a2.setPageStayTimstamp(SystemClock.elapsedRealtime());
                    a2.setRefPage(UTMIVariables.getInstance().getRefPage());
                    a2.setPageAppearCalled();
                    if (this.y != null) {
                        Map pageProperties = a2.getPageProperties();
                        if (pageProperties == null) {
                            a2.setPageProperties(this.y);
                        } else {
                            Map hashMap = new HashMap();
                            hashMap.putAll(pageProperties);
                            hashMap.putAll(this.y);
                            a2.setPageProperties(hashMap);
                        }
                    }
                    this.y = null;
                    this.ag = a(obj);
                    b(a2);
                    a(a(obj), a2);
                } else {
                    i.a("skip page[pageAppear]", new String[]{"page name:" + obj.getClass().getSimpleName()});
                }
            }
        } else {
            i.a("pageAppear", "The page object should not be null");
        }
    }

    synchronized void pageAppear(Object obj, String str) {
        a(obj, str, false);
    }

    @Deprecated
    public synchronized void updatePageProperties(Map<String, String> map) {
        if (map != null) {
            this.w.putAll(map);
        }
    }

    synchronized void updatePageProperties(Object obj, Map<String, String> map) {
        if (!(obj == null || map == null)) {
            if (map.size() != 0) {
                Map hashMap = new HashMap();
                hashMap.putAll(map);
                UTPageHitHelper$UTPageEventObject a = a(obj);
                Map pageProperties = a.getPageProperties();
                if (pageProperties == null) {
                    a.setPageProperties(hashMap);
                } else {
                    Map hashMap2 = new HashMap();
                    hashMap2.putAll(pageProperties);
                    hashMap2.putAll(hashMap);
                    a.setPageProperties(hashMap2);
                }
            }
        }
        i.a("updatePageProperties", "failed to update project, parameters should not be null and the map should not be empty");
    }

    synchronized void updatePageName(Object obj, String str) {
        if (obj != null) {
            if (!TextUtils.isEmpty(str)) {
                a(obj).setPageName(str);
                this.ah = str;
            }
        }
    }

    synchronized void updatePageUrl(Object obj, Uri uri) {
        if (!(obj == null || uri == null)) {
            Log.i("url", "url" + uri.toString());
            a(obj).setPageUrl(uri);
        }
    }

    synchronized void updatePageStatus(Object obj, UTPageStatus uTPageStatus) {
        if (!(obj == null || uTPageStatus == null)) {
            a(obj).setPageStatus(uTPageStatus);
        }
    }

    synchronized void updateNextPageProperties(Map<String, String> map) {
        if (map != null) {
            Map hashMap = new HashMap();
            hashMap.putAll(map);
            this.y = hashMap;
        }
    }

    void pageDisAppearByAuto(Activity activity) {
        if (!this.K) {
            pageDisAppear(activity);
        }
    }

    synchronized void skipPage(Object obj) {
        if (obj != null) {
            a(obj).setToSkipPage();
        }
    }

    @Deprecated
    public synchronized void pageDisAppear(Object obj) {
        if (obj == null) {
            i.a("pageDisAppear", "The page object should not be null");
        } else if (this.ag != null) {
            UTPageHitHelper$UTPageEventObject a = a(obj);
            if (!a.isPageAppearCalled()) {
                i.a("UT", "Please call pageAppear first(" + b(obj) + ").");
            } else if (a.getPageStatus() != null && UTPageStatus.UT_H5_IN_WebView == a.getPageStatus() && a.isH5Called()) {
                a(a);
            } else {
                CharSequence pageName;
                Map map;
                String str;
                String str2;
                Uri pageUrl;
                Map hashMap;
                CharSequence queryParameter;
                Uri uri;
                Object queryParameter2;
                Object obj2;
                CharSequence charSequence;
                CharSequence charSequence2;
                UTPageHitBuilder uTPageHitBuilder;
                UTTracker defaultTracker;
                long elapsedRealtime = SystemClock.elapsedRealtime() - a.getPageStayTimstamp();
                if (a.getPageUrl() == null && (obj instanceof Activity)) {
                    a.setPageUrl(((Activity) obj).getIntent().getData());
                }
                String pageName2 = a.getPageName();
                String refPage = a.getRefPage();
                if (refPage == null || refPage.length() == 0) {
                    refPage = "-";
                }
                Map map2 = this.w;
                if (map2 == null) {
                    map2 = new HashMap();
                }
                if (a.getPageProperties() != null) {
                    map2.putAll(a.getPageProperties());
                }
                if (obj instanceof IUTPageTrack) {
                    IUTPageTrack iUTPageTrack = (IUTPageTrack) obj;
                    CharSequence referPage = iUTPageTrack.getReferPage();
                    if (!TextUtils.isEmpty(referPage)) {
                        refPage = referPage;
                    }
                    Map pageProperties = iUTPageTrack.getPageProperties();
                    if (pageProperties != null && pageProperties.size() > 0) {
                        this.w.putAll(pageProperties);
                        map2 = this.w;
                    }
                    pageName = iUTPageTrack.getPageName();
                    if (!TextUtils.isEmpty(pageName)) {
                        map = map2;
                        str = refPage;
                        str2 = pageName;
                        pageUrl = a.getPageUrl();
                        if (pageUrl != null) {
                            try {
                                hashMap = new HashMap();
                                queryParameter = pageUrl.getQueryParameter("spm");
                                if (TextUtils.isEmpty(queryParameter)) {
                                    pageUrl = Uri.parse(URLDecoder.decode(pageUrl.toString(), "UTF-8"));
                                    uri = pageUrl;
                                    queryParameter2 = pageUrl.getQueryParameter("spm");
                                    if (!TextUtils.isEmpty(queryParameter2)) {
                                        obj2 = null;
                                        if (this.z.containsKey(obj) && queryParameter2.equals(this.z.get(obj))) {
                                            obj2 = 1;
                                        }
                                        if (obj2 == null) {
                                            hashMap.put("spm", queryParameter2);
                                            this.z.put(obj, queryParameter2);
                                        }
                                    }
                                    pageName = uri.getQueryParameter(AlibcConstants.SCM);
                                    if (!TextUtils.isEmpty(pageName)) {
                                        hashMap.put(AlibcConstants.SCM, pageName);
                                    }
                                    obj2 = a(uri);
                                    if (!TextUtils.isEmpty(obj2)) {
                                        c.a().e(obj2);
                                    }
                                    if (hashMap.size() > 0) {
                                        map.putAll(hashMap);
                                    }
                                }
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                            charSequence = queryParameter;
                            uri = pageUrl;
                            charSequence2 = charSequence;
                            if (TextUtils.isEmpty(queryParameter2)) {
                                obj2 = null;
                                obj2 = 1;
                                if (obj2 == null) {
                                    hashMap.put("spm", queryParameter2);
                                    this.z.put(obj, queryParameter2);
                                }
                            }
                            pageName = uri.getQueryParameter(AlibcConstants.SCM);
                            if (TextUtils.isEmpty(pageName)) {
                                hashMap.put(AlibcConstants.SCM, pageName);
                            }
                            obj2 = a(uri);
                            if (TextUtils.isEmpty(obj2)) {
                                c.a().e(obj2);
                            }
                            if (hashMap.size() > 0) {
                                map.putAll(hashMap);
                            }
                        }
                        uTPageHitBuilder = new UTPageHitBuilder(str2);
                        uTPageHitBuilder.setReferPage(str).setDurationOnPage(elapsedRealtime).setProperties(map);
                        UTMIVariables.getInstance().setRefPage(str2);
                        defaultTracker = UTAnalytics.getInstance().getDefaultTracker();
                        if (defaultTracker == null) {
                            defaultTracker.send(uTPageHitBuilder.build());
                        } else {
                            i.a("Record page event error", "Fatal Error,must call setRequestAuthentication method first.");
                        }
                    }
                }
                str = refPage;
                str2 = pageName2;
                map = map2;
                pageUrl = a.getPageUrl();
                if (pageUrl != null) {
                    hashMap = new HashMap();
                    queryParameter = pageUrl.getQueryParameter("spm");
                    if (TextUtils.isEmpty(queryParameter)) {
                        pageUrl = Uri.parse(URLDecoder.decode(pageUrl.toString(), "UTF-8"));
                        uri = pageUrl;
                        queryParameter2 = pageUrl.getQueryParameter("spm");
                        if (TextUtils.isEmpty(queryParameter2)) {
                            obj2 = null;
                            obj2 = 1;
                            if (obj2 == null) {
                                hashMap.put("spm", queryParameter2);
                                this.z.put(obj, queryParameter2);
                            }
                        }
                        pageName = uri.getQueryParameter(AlibcConstants.SCM);
                        if (TextUtils.isEmpty(pageName)) {
                            hashMap.put(AlibcConstants.SCM, pageName);
                        }
                        obj2 = a(uri);
                        if (TextUtils.isEmpty(obj2)) {
                            c.a().e(obj2);
                        }
                        if (hashMap.size() > 0) {
                            map.putAll(hashMap);
                        }
                    }
                    charSequence = queryParameter;
                    uri = pageUrl;
                    charSequence2 = charSequence;
                    if (TextUtils.isEmpty(queryParameter2)) {
                        obj2 = null;
                        obj2 = 1;
                        if (obj2 == null) {
                            hashMap.put("spm", queryParameter2);
                            this.z.put(obj, queryParameter2);
                        }
                    }
                    pageName = uri.getQueryParameter(AlibcConstants.SCM);
                    if (TextUtils.isEmpty(pageName)) {
                        hashMap.put(AlibcConstants.SCM, pageName);
                    }
                    obj2 = a(uri);
                    if (TextUtils.isEmpty(obj2)) {
                        c.a().e(obj2);
                    }
                    if (hashMap.size() > 0) {
                        map.putAll(hashMap);
                    }
                }
                uTPageHitBuilder = new UTPageHitBuilder(str2);
                uTPageHitBuilder.setReferPage(str).setDurationOnPage(elapsedRealtime).setProperties(map);
                UTMIVariables.getInstance().setRefPage(str2);
                defaultTracker = UTAnalytics.getInstance().getDefaultTracker();
                if (defaultTracker == null) {
                    i.a("Record page event error", "Fatal Error,must call setRequestAuthentication method first.");
                } else {
                    defaultTracker.send(uTPageHitBuilder.build());
                }
            }
            this.w = new HashMap();
            if (a.isSkipPage()) {
                a(a);
            } else if (a.getPageStatus() == null || UTPageStatus.UT_H5_IN_WebView != a.getPageStatus()) {
                b(obj);
            } else {
                a(a);
            }
            this.ag = null;
            this.ah = null;
        }
    }

    private static String a(Uri uri) {
        if (uri != null) {
            List<String> queryParameters = uri.getQueryParameters(AlibcConstants.TTID);
            if (queryParameters != null) {
                for (String str : queryParameters) {
                    if (!str.contains("@") && !str.contains("%40")) {
                        return str;
                    }
                }
            }
        }
        return null;
    }

    private static String b(Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        if (simpleName == null || !simpleName.toLowerCase().endsWith("activity")) {
            return simpleName;
        }
        return simpleName.substring(0, simpleName.length() - 8);
    }
}
