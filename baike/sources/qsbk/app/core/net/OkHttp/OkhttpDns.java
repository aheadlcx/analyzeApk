package qsbk.app.core.net.OkHttp;

import android.text.TextUtils;
import com.qiushibaike.httpdns.lib.HttpDNSManager;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import okhttp3.Dns;

public class OkhttpDns implements Dns {
    private static int a = 80;
    private static LinkedHashMap<String, String> b = new a();

    public List<InetAddress> lookup(String str) throws UnknownHostException {
        Object httpDnsIp = HttpDNSManager.instance().getHttpDnsIp(str);
        if (TextUtils.isEmpty(httpDnsIp)) {
            return SYSTEM.lookup(str);
        }
        b.put(str, httpDnsIp);
        return Arrays.asList(InetAddress.getAllByName(httpDnsIp));
    }

    public static String getIpByHost(String str) {
        return (String) b.get(str);
    }
}
