package com.alibaba.baichuan.android.trade.c.a.a.a;

import android.text.TextUtils;
import com.ali.auth.third.core.model.Constants;
import com.alibaba.baichuan.android.trade.c.a.a.a.d.a;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.tencent.stat.DeviceInfo;
import com.umeng.update.UpdateConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class f implements b {
    private a a;
    private Pattern b;
    private String c;
    private String d;
    private boolean e;
    private String f;
    private String g;

    public f(a aVar) {
        String str = (String) aVar.c.get("regexp");
        if (str != null) {
            this.b = Pattern.compile(str);
        }
        this.c = (String) aVar.c.get("key");
        this.d = (String) aVar.c.get("value");
        this.g = (String) aVar.c.get("mode");
        this.e = Constants.SERVICE_SCOPE_FLAG_VALUE.equals(aVar.c.get("cacheable"));
        this.a = aVar;
    }

    private Map b(c cVar) {
        Map hashMap = new HashMap();
        if (this.a.c != null) {
            hashMap.putAll(this.a.c);
        }
        hashMap.putAll(cVar.a());
        return hashMap;
    }

    public boolean a(c cVar) {
        if (this.c == null) {
            return false;
        }
        CharSequence a = cVar.a(this.c);
        String a2;
        if ((("addIfAbsent".equals(this.g) || UpdateConfig.a.equals(this.g)) && a == null) || "append".equals(this.g)) {
            if (!"addAllParams".equals(this.c)) {
                if (!this.e || this.f == null || TextUtils.isEmpty(this.f)) {
                    a2 = com.alibaba.baichuan.android.trade.c.a.a.a.a(this.d, b(cVar));
                    if (this.e) {
                        this.f = a2;
                    }
                } else {
                    a2 = this.f;
                }
                if (a2 != null) {
                    cVar.c(this.c, a2);
                }
            } else if (cVar.a() != null) {
                for (String a22 : cVar.a().keySet()) {
                    if (!TextUtils.isEmpty((CharSequence) cVar.a().get(a22))) {
                        cVar.c(a22, (String) cVar.a().get(a22));
                    }
                }
            }
        } else if (("replace".equals(this.g) || UpdateConfig.a.equals(this.g)) && a != null) {
            if (!this.e || this.f == null) {
                Map b = b(cVar);
                if (this.b != null) {
                    Matcher matcher = this.b.matcher(a);
                    if (matcher.matches()) {
                        int groupCount = matcher.groupCount();
                        for (int i = 1; i <= groupCount; i++) {
                            b.put("group_" + i, matcher.group(i));
                        }
                    }
                    b.put("group_0", a);
                }
                a22 = com.alibaba.baichuan.android.trade.c.a.a.a.a(this.d, b);
                if (this.e) {
                    this.f = a22;
                }
            } else {
                a22 = this.f;
            }
            if (a22 != null) {
                cVar.b(this.c, a22);
            }
        } else if ("delete".equals(this.g)) {
            cVar.e(this.c);
        } else {
            AlibcLogger.i(DeviceInfo.TAG_IMEI, "ignore the action " + this.g + " key " + this.c);
        }
        return true;
    }
}
