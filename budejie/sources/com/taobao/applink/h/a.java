package com.taobao.applink.h;

import android.text.TextUtils;
import android.util.Log;
import com.taobao.applink.util.TBAppLinkUtil;
import com.taobao.applink.util.e;
import java.util.HashMap;
import java.util.Map;

public class a {
    private Map a = new HashMap();
    private String b;

    private a() {
    }

    public static a a() {
        return new a();
    }

    public a a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.b = str;
        }
        return this;
    }

    public a a(Map map) {
        if (map != null) {
            this.a.putAll(map);
        }
        return this;
    }

    public void b() {
        if (TextUtils.isEmpty(this.b)) {
            throw new IllegalArgumentException("spm is null");
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.format("https://wgo.mmstat.com%s?", new Object[]{this.b})).append(e.a(this.a));
        if (stringBuffer.toString().length() > 8000) {
            if (this.a.containsKey("param")) {
                this.a.remove("param");
            }
            stringBuffer = new StringBuffer();
            stringBuffer.append(String.format("https://wgo.mmstat.com%s?", new Object[]{this.b})).append(e.a(this.a));
        }
        String stringBuffer2 = stringBuffer.toString();
        Log.d(TBAppLinkUtil.TAG, "the url TBAplus send is " + stringBuffer2);
        new b(this, stringBuffer2).execute(new Void[0]);
    }
}
