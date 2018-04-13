package com.qiniu.android.dns.local;

import com.qiniu.android.dns.Domain;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.Record;
import java.io.IOException;
import java.net.InetAddress;

final class a implements IResolver {
    a() {
    }

    public Record[] resolve(Domain domain, NetworkInfo networkInfo) throws IOException {
        InetAddress[] byCommand;
        InetAddress[] byReflection = AndroidDnsServer.getByReflection();
        if (byReflection == null) {
            byCommand = AndroidDnsServer.getByCommand();
        } else {
            byCommand = byReflection;
        }
        if (byCommand == null) {
            throw new IOException("cant get local dns server");
        }
        int i;
        int length;
        Record record;
        Record[] resolve = new HijackingDetectWrapper(new Resolver(byCommand[0])).resolve(domain, networkInfo);
        if (domain.hasCname) {
            for (Record record2 : resolve) {
                if (record2.isCname()) {
                    i = 1;
                    break;
                }
            }
            i = 0;
            if (i == 0) {
                throw new DnshijackingException(domain.domain, byCommand[0].getHostAddress());
            }
        }
        if (domain.maxTtl != 0) {
            length = resolve.length;
            i = 0;
            while (i < length) {
                record2 = resolve[i];
                if (record2.isCname() || record2.ttl <= domain.maxTtl) {
                    i++;
                } else {
                    throw new DnshijackingException(domain.domain, byCommand[0].getHostAddress(), record2.ttl);
                }
            }
        }
        return resolve;
    }
}
