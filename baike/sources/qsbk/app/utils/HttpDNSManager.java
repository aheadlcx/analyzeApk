package qsbk.app.utils;

import android.content.Context;
import com.qiushibaike.httpdns.lib.AliFetch;
import com.qiushibaike.httpdns.lib.AppContext;
import com.qiushibaike.httpdns.lib.Fetch;
import com.qiushibaike.httpdns.lib.QCloudFetcher;
import org.json.JSONArray;
import qsbk.app.QsbkApp;

public class HttpDNSManager {
    private static HttpDNSManager a;

    public static synchronized HttpDNSManager instance() {
        HttpDNSManager httpDNSManager;
        synchronized (HttpDNSManager.class) {
            if (a == null) {
                a = new HttpDNSManager();
            }
            httpDNSManager = a;
        }
        return httpDNSManager;
    }

    public void reportError(String str, String str2) {
        com.qiushibaike.httpdns.lib.HttpDNSManager.instance().reportError(str, str2);
    }

    public void reportOK(String str, String str2) {
        com.qiushibaike.httpdns.lib.HttpDNSManager.instance().reportOK(str, str2);
    }

    public String getHttpDnsIp(String str) {
        return com.qiushibaike.httpdns.lib.HttpDNSManager.instance().getHttpDnsIp(str);
    }

    public void onCreate(Context context) {
        com.qiushibaike.httpdns.lib.HttpDNSManager.instance().onCreate(context);
        JSONArray optJSONArray = QsbkApp.indexConfig.optJSONArray("white_list");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                String optString = optJSONArray.optString(i);
                if (optString != null) {
                    AppContext.addWhite(optString);
                }
            }
        }
        boolean isInConfigRatio = QsbkApp.isInConfigRatio("httpdns_ali_ratio", 50);
        Fetch aliFetch = isInConfigRatio ? new AliFetch() : new QCloudFetcher();
        aliFetch.setResultListener(new ai(this, isInConfigRatio ? "ali" : "qc"));
        com.qiushibaike.httpdns.lib.HttpDNSManager.instance().setFetcher(aliFetch);
    }

    public void onDestroy(Context context) {
        com.qiushibaike.httpdns.lib.HttpDNSManager.instance().onDestroy(context);
    }
}
