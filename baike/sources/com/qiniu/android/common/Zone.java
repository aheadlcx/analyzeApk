package com.qiniu.android.common;

import com.qiniu.android.dns.DnsManager;

public abstract class Zone {
    public static final AutoZone httpAutoZone = new AutoZone(false, null);
    public static final AutoZone httpsAutoZone = new AutoZone(true, null);
    public static final Zone zone0 = a("upload.qiniu.com", "up.qiniu.com", "183.136.139.10", "115.231.182.136");
    public static final Zone zone1 = a("upload-z1.qiniu.com", "up-z1.qiniu.com", "106.38.227.27", "106.38.227.28");
    public static final Zone zone2 = a("upload-z2.qiniu.com", "up-z2.qiniu.com", "183.60.214.197", "14.152.37.7");
    public static final Zone zoneNa0 = a("upload-na0.qiniu.com", "up-na0.qiniu.com", "23.236.102.3", "23.236.102.2");

    public interface QueryHandler {
        void onFailure(int i);

        void onSuccess();
    }

    public abstract void preQuery(String str, QueryHandler queryHandler);

    public abstract ServiceAddress upHost(String str);

    public abstract ServiceAddress upHostBackup(String str);

    private static Zone a(String str, String str2, String str3, String str4) {
        String[] strArr = new String[]{str3, str4};
        return new FixedZone(new ServiceAddress("http://" + str, strArr), new ServiceAddress("http://" + str2, strArr));
    }

    public static void addDnsIp(DnsManager dnsManager) {
        zone0.upHost("").addIpToDns(dnsManager);
        zone0.upHostBackup("").addIpToDns(dnsManager);
        zone1.upHost("").addIpToDns(dnsManager);
        zone1.upHostBackup("").addIpToDns(dnsManager);
        zone2.upHost("").addIpToDns(dnsManager);
        zone2.upHostBackup("").addIpToDns(dnsManager);
    }
}
