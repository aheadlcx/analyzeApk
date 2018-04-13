package qsbk.app.image;

import android.text.TextUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import okhttp3.Dns;
import qsbk.app.image.OkHttpDnsUtil.DnsInfo;
import qsbk.app.utils.HttpDNSManager;

public class OkHttpDns implements Dns {
    public List<InetAddress> lookup(String str) throws UnknownHostException {
        String httpDnsIp;
        try {
            httpDnsIp = HttpDNSManager.instance().getHttpDnsIp(str);
            if (!TextUtils.isEmpty(httpDnsIp)) {
                OkHttpDnsUtil.getInstance().put(str, new DnsInfo(str, httpDnsIp, 2));
                return Arrays.asList(new InetAddress[]{InetAddress.getByName(httpDnsIp)});
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        List<InetAddress> lookup = SYSTEM.lookup(str);
        httpDnsIp = null;
        if (lookup.size() > 0) {
            httpDnsIp = ((InetAddress) lookup.get(0)).getHostAddress();
        }
        OkHttpDnsUtil.getInstance().put(str, new DnsInfo(str, httpDnsIp, 1));
        return lookup;
    }
}
