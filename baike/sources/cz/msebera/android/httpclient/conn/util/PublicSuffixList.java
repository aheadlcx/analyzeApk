package cz.msebera.android.httpclient.conn.util;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.Args;
import java.util.Collections;
import java.util.List;

@Immutable
public final class PublicSuffixList {
    private final List<String> a;
    private final List<String> b;

    public PublicSuffixList(List<String> list, List<String> list2) {
        this.a = Collections.unmodifiableList((List) Args.notNull(list, "Domain suffix rules"));
        this.b = Collections.unmodifiableList((List) Args.notNull(list2, "Domain suffix exceptions"));
    }

    public List<String> getRules() {
        return this.a;
    }

    public List<String> getExceptions() {
        return this.b;
    }
}
