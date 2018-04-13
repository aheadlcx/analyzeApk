package com.alibaba.baichuan.android.trade.c.a.a.b;

import com.alibaba.baichuan.android.trade.c.a.a.c;
import com.facebook.common.util.UriUtil;
import java.io.Serializable;
import java.util.regex.Pattern;

public class f implements Serializable {
    public static final String[] a = new String[]{UriUtil.HTTP_SCHEME, "https"};
    public static final String[] b = new String[]{"tbopen"};
    public String c;
    public String d;
    public String[] e;
    public String[] f;
    private transient Pattern[] g;

    public boolean a(c cVar) {
        String d;
        int length;
        if (this.e == null || this.e.length <= 0) {
            d = cVar.d();
        } else {
            d = cVar.c();
            String f = cVar.f();
            if (f == null) {
                return false;
            }
            boolean z;
            for (Object equals : this.e) {
                if (f.equals(equals)) {
                    z = true;
                    break;
                }
            }
            z = false;
            if (!z) {
                return false;
            }
        }
        if ("all".equals(this.d)) {
            return true;
        }
        if (((this.f == null ? 1 : 0) | (this.f.length == 0 ? 1 : 0)) != 0) {
            return false;
        }
        if ("pattern".equals(this.d)) {
            int i;
            if (this.g == null) {
                this.g = new Pattern[this.f.length];
                length = this.g.length;
                for (i = 0; i < length; i++) {
                    this.g[i] = Pattern.compile(this.f[i]);
                }
            }
            for (Pattern matcher : this.g) {
                if (matcher.matcher(cVar.d()).matches()) {
                    return true;
                }
            }
            return false;
        } else if (!"start".equals(this.d)) {
            return false;
        } else {
            for (String startsWith : this.f) {
                if (d.startsWith(startsWith)) {
                    return true;
                }
            }
            return false;
        }
    }
}
