package com.qiniu.android.common;

import com.qiniu.android.dns.DnsManager;
import java.net.URI;
import java.net.URISyntaxException;

public final class ServiceAddress {
    public final URI address;
    public final String[] backupIps;

    public ServiceAddress(String str, String[] strArr) {
        this.address = a(str);
        if (strArr == null) {
            strArr = new String[0];
        }
        this.backupIps = strArr;
    }

    public ServiceAddress(String str) {
        this(str, null);
    }

    private static URI a(String str) {
        try {
            return new URI(str);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addIpToDns(DnsManager dnsManager) {
        for (String putHosts : this.backupIps) {
            dnsManager.putHosts(this.address.getHost(), putHosts);
        }
    }
}
