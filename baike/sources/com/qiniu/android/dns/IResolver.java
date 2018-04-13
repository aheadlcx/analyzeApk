package com.qiniu.android.dns;

import java.io.IOException;

public interface IResolver {
    public static final int DNS_DEFAULT_TIMEOUT = 10;

    Record[] resolve(Domain domain, NetworkInfo networkInfo) throws IOException;
}
