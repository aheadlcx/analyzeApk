package com.qiniu.android.dns.local;

import com.qiniu.android.dns.Domain;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.Record;
import java.io.IOException;

public final class HijackingDetectWrapper implements IResolver {
    private final Resolver a;

    public HijackingDetectWrapper(Resolver resolver) {
        this.a = resolver;
    }

    public Record[] resolve(Domain domain, NetworkInfo networkInfo) throws IOException {
        int i;
        int i2 = 0;
        Record[] resolve = this.a.resolve(domain, networkInfo);
        if (domain.hasCname) {
            for (Record isCname : resolve) {
                if (isCname.isCname()) {
                    i = 1;
                    break;
                }
            }
            i = 0;
            if (i == 0) {
                throw new DnshijackingException(domain.domain, this.a.a.getHostAddress());
            }
        }
        if (domain.maxTtl != 0) {
            i = resolve.length;
            while (i2 < i) {
                Record record = resolve[i2];
                if (record.isCname() || record.ttl <= domain.maxTtl) {
                    i2++;
                } else {
                    throw new DnshijackingException(domain.domain, this.a.a.getHostAddress(), record.ttl);
                }
            }
        }
        return resolve;
    }
}
