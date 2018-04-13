package com.xiaomi.network;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.tencent.stat.DeviceInfo;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.network.a;
import com.xiaomi.channel.commonutils.network.c;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.network.HostManager.HttpGet;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.utils.HttpUtils;

public class HostManagerV2 extends HostManager {
    private final int f;
    private final int g;
    private int h;

    protected HostManagerV2(Context context, HostFilter hostFilter, HttpGet httpGet, String str) {
        this(context, hostFilter, httpGet, str, null, null);
    }

    protected HostManagerV2(Context context, HostFilter hostFilter, HttpGet httpGet, String str, String str2, String str3) {
        super(context, hostFilter, httpGet, str, str2, str3);
        this.f = 80;
        this.g = 5222;
        this.h = 80;
        HostManager.addReservedHost("resolver.msg.xiaomi.net", "resolver.msg.xiaomi.net:5222");
    }

    static String d(String str) {
        try {
            int length = str.length();
            byte[] bytes = str.getBytes("UTF-8");
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                if ((b & 240) != 240) {
                    bytes[i] = (byte) (((b & 15) ^ ((byte) (((b >> 4) + length) & 15))) | (b & 240));
                }
            }
            return new String(bytes);
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    protected String a() {
        return "resolver.msg.xiaomi.net";
    }

    protected String a(ArrayList<String> arrayList, String str, String str2) {
        Iterator it;
        ArrayList arrayList2;
        ArrayList arrayList3 = new ArrayList();
        List<c> arrayList4 = new ArrayList();
        arrayList4.add(new a("type", str));
        if (str.equals(HttpUtils.WAP)) {
            arrayList4.add(new a("conpt", d(d.k(this.c))));
        }
        arrayList4.add(new a("uuid", str2));
        arrayList4.add(new a("list", HostManager.join((Collection) arrayList, Constants.ACCEPT_TIME_SEPARATOR_SP)));
        Fallback a = a("resolver.msg.xiaomi.net");
        String format = String.format(Locale.US, "http://%1$s/gslb/?ver=3.0", new Object[]{"resolver.msg.xiaomi.net:" + this.h});
        if (a == null) {
            arrayList3.add(format);
            synchronized (b) {
                it = ((ArrayList) b.get("resolver.msg.xiaomi.net")).iterator();
                while (it.hasNext()) {
                    String str3 = (String) it.next();
                    arrayList3.add(String.format(Locale.US, "http://%1$s/gslb/?ver=3.0", new Object[]{str3}));
                }
            }
            arrayList2 = arrayList3;
        } else {
            arrayList2 = a.a(format);
        }
        Iterator it2 = arrayList2.iterator();
        IOException iOException = null;
        while (it2.hasNext()) {
            Builder buildUpon = Uri.parse((String) it2.next()).buildUpon();
            for (c cVar : arrayList4) {
                buildUpon.appendQueryParameter(cVar.a(), cVar.b());
            }
            try {
                return this.d == null ? d.a(this.c, new URL(buildUpon.toString())) : this.d.a(buildUpon.toString());
            } catch (IOException e) {
                iOException = e;
            }
        }
        return iOException != null ? super.a(arrayList, str, str2) : null;
    }

    protected boolean c() {
        synchronized (this.a) {
            if (e) {
                return true;
            }
            e = true;
            this.a.clear();
            try {
                Object b = b();
                if (!TextUtils.isEmpty(b)) {
                    e(b);
                    b.b("loading the new hosts succeed");
                    return true;
                }
            } catch (Throwable th) {
                b.a("load bucket failure: " + th.getMessage());
            }
        }
        return false;
    }

    protected void e(String str) {
        synchronized (this.a) {
            this.a.clear();
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(DeviceInfo.TAG_VERSION) != 2) {
                throw new JSONException("Bad version");
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            for (int i = 0; i < optJSONArray.length(); i++) {
                a fromJSON = new a().fromJSON(optJSONArray.getJSONObject(i));
                this.a.put(fromJSON.getHost(), fromJSON);
            }
        }
    }

    protected JSONObject f() {
        JSONObject jSONObject;
        synchronized (this.a) {
            jSONObject = new JSONObject();
            jSONObject.put(DeviceInfo.TAG_VERSION, 2);
            jSONObject.put("data", e());
        }
        return jSONObject;
    }

    public void persist() {
        synchronized (this.a) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.c.openFileOutput(d(), 0)));
                Object jSONObject = f().toString();
                if (!TextUtils.isEmpty(jSONObject)) {
                    bufferedWriter.write(jSONObject);
                }
                bufferedWriter.close();
            } catch (Exception e) {
                b.a("persist bucket failure: " + e.getMessage());
            }
        }
    }

    public void purge() {
        synchronized (this.a) {
            for (a purge : this.a.values()) {
                purge.purge(true);
            }
            Object obj = null;
            while (obj == null) {
                for (String str : this.a.keySet()) {
                    if (((a) this.a.get(str)).getFallbacks().isEmpty()) {
                        this.a.remove(str);
                        obj = null;
                        break;
                    }
                }
                obj = 1;
            }
        }
    }
}
