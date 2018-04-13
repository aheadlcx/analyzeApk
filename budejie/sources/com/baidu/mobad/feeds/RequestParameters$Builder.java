package com.baidu.mobad.feeds;

import java.util.HashMap;
import java.util.Map;

public class RequestParameters$Builder {
    private String a;
    private Map<String, String> b = new HashMap();
    private int c = 3;
    private boolean d = false;
    private int e = 640;
    private int f = 480;
    private int g = 1;

    public final RequestParameters$Builder setWidth(int i) {
        this.e = i;
        return this;
    }

    public final RequestParameters$Builder setHeight(int i) {
        this.f = i;
        return this;
    }

    @Deprecated
    public final RequestParameters$Builder confirmDownloading(boolean z) {
        if (z) {
            downloadAppConfirmPolicy(2);
        } else {
            downloadAppConfirmPolicy(3);
        }
        return this;
    }

    public final RequestParameters$Builder downloadAppConfirmPolicy(int i) {
        this.g = i;
        return this;
    }

    public final RequestParameters$Builder addExtra(String str, String str2) {
        this.b.put(str, str2);
        return this;
    }

    public final RequestParameters build() {
        return new RequestParameters(this, null);
    }
}
