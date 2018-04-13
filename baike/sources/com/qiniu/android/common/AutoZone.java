package com.qiniu.android.common;

import com.baidu.mobstat.Config;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.qiniu.android.common.Zone.QueryHandler;
import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.http.Client;
import com.qiniu.android.http.CompletionHandler;
import com.qiniu.android.storage.UpToken;
import com.qiniu.android.utils.UrlSafeBase64;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AutoZone extends Zone {
    private static Map<a, b> a = new ConcurrentHashMap();
    private static Client b = new Client();
    private final String c;
    private final DnsManager d;
    private final boolean e;

    static class a {
        private final String a;
        private final String b;

        a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public static a getFromToken(String str) {
            String[] split = str.split(Config.TRACE_TODAY_VISIT_SPLIT);
            try {
                return new a(split[0], new JSONObject(new String(UrlSafeBase64.decode(split[2]), "utf-8")).getString("scope").split(Config.TRACE_TODAY_VISIT_SPLIT)[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public int hashCode() {
            return (this.a.hashCode() * 37) + this.b.hashCode();
        }

        public boolean equals(Object obj) {
            return obj == this || (obj != null && (obj instanceof a) && ((a) obj).a.equals(this.a) && ((a) obj).b.equals(this.b));
        }
    }

    static class b {
        final String a;
        final String b;
        final String c;
        final String d;

        private b(String str, String str2, String str3, String str4) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
        }

        static b a(JSONObject jSONObject) throws JSONException {
            JSONArray jSONArray = jSONObject.getJSONObject("http").getJSONArray("up");
            return new b(jSONArray.getString(1), jSONArray.getString(2).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)[2].split("//")[1], jSONArray.getString(0), jSONObject.getJSONObject("https").getJSONArray("up").getString(0));
        }
    }

    public AutoZone(boolean z, DnsManager dnsManager) {
        this("https://uc.qbox.me", z, dnsManager);
    }

    AutoZone(String str, boolean z, DnsManager dnsManager) {
        this.c = str;
        this.e = z;
        this.d = dnsManager;
    }

    private void a(a aVar, CompletionHandler completionHandler) {
        b.asyncGet(this.c + "/v1/query?ak=" + aVar.a + "&bucket=" + aVar.b, null, UpToken.NULL, completionHandler);
    }

    private void a(b bVar) {
        if (this.d != null) {
            try {
                String host = new URI(bVar.a).getHost();
                String host2 = new URI(bVar.d).getHost();
                String host3 = new URI(bVar.c).getHost();
                this.d.putHosts(host, bVar.b);
                this.d.putHosts(host2, bVar.b);
                this.d.putHosts(host3, bVar.b);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    b a(String str, String str2) {
        return (b) a.get(new a(str, str2));
    }

    b a(String str) {
        try {
            String[] split = str.split(Config.TRACE_TODAY_VISIT_SPLIT);
            return a(split[0], new JSONObject(new String(UrlSafeBase64.decode(split[2]), "utf-8")).getString("scope").split(Config.TRACE_TODAY_VISIT_SPLIT)[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ServiceAddress upHost(String str) {
        b a = a(str);
        if (a == null) {
            return null;
        }
        if (this.e) {
            return new ServiceAddress(a.d);
        }
        return new ServiceAddress(a.a, new String[]{a.b});
    }

    public ServiceAddress upHostBackup(String str) {
        b a = a(str);
        if (a == null || this.e) {
            return null;
        }
        return new ServiceAddress(a.c, new String[]{a.b});
    }

    void a(a aVar, QueryHandler queryHandler) {
        if (aVar == null) {
            queryHandler.onFailure(-5);
        } else if (((b) a.get(aVar)) != null) {
            queryHandler.onSuccess();
        } else {
            a(aVar, new a(this, aVar, queryHandler));
        }
    }

    public void preQuery(String str, QueryHandler queryHandler) {
        a(a.getFromToken(str), queryHandler);
    }
}
