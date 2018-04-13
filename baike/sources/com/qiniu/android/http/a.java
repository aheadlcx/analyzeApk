package com.qiniu.android.http;

import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.Domain;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.Dns;

class a implements Dns {
    final /* synthetic */ DnsManager a;
    final /* synthetic */ Client b;

    a(Client client, DnsManager dnsManager) {
        this.b = client;
        this.a = dnsManager;
    }

    public List<InetAddress> lookup(String str) throws UnknownHostException {
        try {
            InetAddress[] queryInetAdress = this.a.queryInetAdress(new Domain(str));
            if (queryInetAdress == null) {
                throw new UnknownHostException(str + " resolve failed");
            }
            Object arrayList = new ArrayList();
            Collections.addAll(arrayList, queryInetAdress);
            return arrayList;
        } catch (IOException e) {
            e.printStackTrace();
            throw new UnknownHostException(e.getMessage());
        }
    }
}
