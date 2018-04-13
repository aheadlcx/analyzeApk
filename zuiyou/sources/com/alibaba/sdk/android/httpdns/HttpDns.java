package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import com.alibaba.sdk.android.utils.AMSDevReporter;
import com.alibaba.sdk.android.utils.AMSDevReporter.AMSSdkExtInfoKeyEnum;
import com.alibaba.sdk.android.utils.AMSDevReporter.AMSSdkTypeEnum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpDns implements HttpDnsService {
    private static DegradationFilter degradationFilter = null;
    private static a hostManager = a.a();
    static HttpDns instance = null;
    private static boolean isEnabled = true;
    private static ExecutorService pool = Executors.newFixedThreadPool(3, new g());
    private boolean isExpiredIPEnabled = false;

    private HttpDns() {
    }

    private String getIpByHost(String str) {
        if (isEnabled) {
            String[] ipsByHost = getIpsByHost(str);
            return ipsByHost.length > 0 ? ipsByHost[0] : null;
        } else {
            f.e("HttpDns service turned off");
            return null;
        }
    }

    private String[] getIpsByHost(String str) {
        if (!isEnabled) {
            f.e("HttpDns service turned off");
            return c.d;
        } else if (!i.b(str)) {
            return c.d;
        } else {
            if (i.c(str)) {
                return new String[]{str};
            } else if (degradationFilter != null && degradationFilter.shouldDegradeHttpDNS(str)) {
                return c.d;
            } else {
                if (u.c()) {
                    return getIpsByHostAsync(str);
                }
                b a = hostManager.a(str);
                if (a != null && a.a() && this.isExpiredIPEnabled) {
                    if (!hostManager.a(str)) {
                        f.d("refresh host async: " + str);
                        pool.submit(new l(str, p.QUERY_HOST));
                    }
                    return a.a();
                } else if (a != null && !a.a()) {
                    return a.a();
                } else {
                    f.d("refresh host sync: " + str);
                    try {
                        return (String[]) pool.submit(new l(str, p.QUERY_HOST)).get();
                    } catch (Throwable e) {
                        f.a(e);
                        return c.d;
                    }
                }
            }
        }
    }

    public static HttpDnsService getService(Context context, String str) {
        if (instance == null) {
            synchronized (HttpDns.class) {
                if (instance == null) {
                    Map hashMap = new HashMap();
                    hashMap.put(AMSSdkExtInfoKeyEnum.AMS_EXTINFO_KEY_VERSION.toString(), "1.1.1");
                    AMSDevReporter.asyncReport(context, AMSSdkTypeEnum.AMS_HTTPDNS, hashMap);
                    j.setContext(context);
                    l.setContext(context);
                    u.a(context);
                    c.c(str);
                    q.a().a(context);
                    instance = new HttpDns();
                }
            }
        }
        return instance;
    }

    static synchronized void switchDnsService(boolean z) {
        synchronized (HttpDns.class) {
            isEnabled = z;
            if (!isEnabled) {
                f.e("httpdns service disabled");
            }
        }
    }

    public String getIpByHostAsync(String str) {
        if (isEnabled) {
            String[] ipsByHostAsync = getIpsByHostAsync(str);
            return ipsByHostAsync.length > 0 ? ipsByHostAsync[0] : null;
        } else {
            f.e("HttpDns service turned off");
            return null;
        }
    }

    public String[] getIpsByHostAsync(String str) {
        if (!isEnabled) {
            f.e("HttpDns service turned off");
            return c.d;
        } else if (!i.b(str)) {
            return c.d;
        } else {
            if (i.c(str)) {
                return new String[]{str};
            } else if (degradationFilter != null && degradationFilter.shouldDegradeHttpDNS(str)) {
                return c.d;
            } else {
                b a = hostManager.a(str);
                if ((a == null || a.a()) && !hostManager.a(str)) {
                    if (u.c()) {
                        t.a().f(str);
                    } else {
                        f.d("refresh host async: " + str);
                        pool.submit(new l(str, p.QUERY_HOST));
                    }
                }
                return (a == null || (a.a() && !(a.a() && this.isExpiredIPEnabled))) ? c.d : a.a();
            }
        }
    }

    public void setDegradationFilter(DegradationFilter degradationFilter) {
        degradationFilter = degradationFilter;
    }

    public void setExpiredIPEnabled(boolean z) {
        this.isExpiredIPEnabled = z;
    }

    public void setHTTPSRequestEnabled(boolean z) {
        c.setHTTPSRequestEnabled(z);
    }

    public void setLogEnabled(boolean z) {
        f.setLogEnabled(z);
    }

    public void setPreResolveAfterNetworkChanged(boolean z) {
        j.b = z;
    }

    public void setPreResolveHosts(ArrayList arrayList) {
        if (isEnabled) {
            for (int i = 0; i < arrayList.size(); i++) {
                String str = (String) arrayList.get(i);
                if (!hostManager.a(str)) {
                    pool.submit(new l(str, p.QUERY_HOST));
                }
            }
            return;
        }
        f.e("HttpDns service turned off");
    }

    public void setTimeoutInterval(int i) {
        c.setTimeoutInterval(i);
    }
}
