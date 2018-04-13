package com.alibaba.baichuan.android.trade.c.a.a;

import android.net.Uri;
import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.tencent.stat.DeviceInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class c {
    protected Uri a;
    protected Map b = new LinkedHashMap();
    protected String c;
    private String d;
    private String e;
    private int f;
    private String g;
    private Map h;
    private Map i;

    public c(int i, String str, Map map) {
        this.e = str;
        this.f = i;
        if (map == null) {
            map = new HashMap();
        }
        this.i = map;
        Map map2 = (Map) this.i.get("ui_contextParams");
        if (map2 == null) {
            map2 = new HashMap();
        } else {
            Object hashMap = new HashMap(map2);
        }
        this.h = map2;
        g();
    }

    private void g() {
        try {
            String d = d();
            int indexOf = d.indexOf("#");
            if (indexOf == -1) {
                this.c = null;
            } else if (indexOf != d.length() - 1) {
                this.c = d.substring(indexOf + 1);
            }
            int indexOf2 = d.indexOf("?");
            this.b.clear();
            if (indexOf2 != -1 && indexOf2 != d.length() - 1) {
                if (indexOf == -1 || indexOf2 < indexOf) {
                    for (String str : (indexOf == -1 ? d.substring(indexOf2 + 1) : d.substring(indexOf2 + 1, indexOf)).split("[\\&]")) {
                        if (!TextUtils.isEmpty(str)) {
                            int indexOf3 = str.indexOf(61);
                            if (!(indexOf3 == -1 || indexOf3 == str.length() - 1)) {
                                String decode = Uri.decode(str.substring(0, indexOf3));
                                String decode2 = Uri.decode(str.substring(indexOf3 + 1));
                                List list = (List) this.b.get(decode);
                                if (list == null) {
                                    list = new ArrayList(2);
                                    list.add(decode2);
                                    this.b.put(decode, list);
                                } else {
                                    list.add(decode2);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable e) {
            AlibcLogger.e(DeviceInfo.TAG_IMEI, "fail to parse the uri " + d(), e);
        }
    }

    public String a(String str) {
        List list = (List) this.b.get(str);
        return (list == null || list.size() == 0) ? null : (String) list.get(0);
    }

    public Map a() {
        return this.h;
    }

    public void a(String str, String str2) {
        this.h.put(str, str2);
    }

    public int b() {
        return this.f;
    }

    public String b(String str) {
        return (String) this.h.get(str);
    }

    public String c() {
        if (this.d != null) {
            return this.d;
        }
        if (this.a == null) {
            this.a = Uri.parse(this.e);
        }
        String schemeSpecificPart = this.a.getSchemeSpecificPart();
        if (schemeSpecificPart == null || schemeSpecificPart.length() <= "//".length()) {
            this.d = "";
        } else {
            this.d = schemeSpecificPart.substring(2);
            if (this.a.getFragment() != null) {
                this.d += "#" + this.a.getFragment();
            }
        }
        return this.d;
    }

    public void c(String str) {
        this.g = str;
    }

    public String d() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
        this.a = null;
        this.c = null;
        this.d = null;
        g();
    }

    public String e() {
        return this.g;
    }

    public String f() {
        if (this.a == null) {
            this.a = Uri.parse(this.e);
        }
        return this.a.getScheme().trim();
    }
}
