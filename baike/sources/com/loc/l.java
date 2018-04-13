package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.util.j;
import com.loc.l.a.a;
import com.loc.l.a.b;
import com.loc.l.a.c;
import com.loc.l.a.d;
import com.tencent.stat.DeviceInfo;
import java.net.URLDecoder;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.model.User;

public final class l {
    public static int a = -1;
    public static String b = "";

    public static l$a a(Context context, s sVar, String str) {
        bo a;
        byte[] bArr;
        j e;
        Object obj;
        String a2;
        JSONObject jSONObject;
        int i;
        String str2;
        a aVar;
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        Throwable th;
        JSONObject jSONObject4;
        d dVar;
        Object a3;
        Object a4;
        c cVar;
        String a5;
        b bVar;
        bo boVar = null;
        l$a l_a = new l$a();
        try {
            bi biVar = new bi();
            bn l_b = new l$b(context, sVar, str);
            a = bi.a(l_b, l_b.a());
            if (a != null) {
                try {
                    bArr = a.a;
                } catch (j e2) {
                    e = e2;
                    bArr = boVar;
                    l_a.a = e.a();
                    z.a(sVar, "/v3/iasdkauth", e);
                    obj = boVar;
                    boVar = a;
                    if (bArr != null) {
                        if (TextUtils.isEmpty(a2)) {
                            a2 = t.a(bArr);
                        }
                        try {
                            jSONObject = new JSONObject(a2);
                            if (jSONObject.has("status")) {
                                i = jSONObject.getInt("status");
                                if (i == 1) {
                                    a = 1;
                                } else if (i == 0) {
                                    a2 = "authcsid";
                                    str2 = "authgsid";
                                    if (boVar != null) {
                                        a2 = boVar.c;
                                        str2 = boVar.d;
                                    }
                                    t.a(context, a2, str2, jSONObject);
                                    a = 0;
                                    if (jSONObject.has("info")) {
                                        b = jSONObject.getString("info");
                                    }
                                    a2 = "";
                                    if (jSONObject.has("infocode")) {
                                        a2 = jSONObject.getString("infocode");
                                    }
                                    z.a(sVar, "/v3/iasdkauth", b, str2, a2);
                                    if (a == 0) {
                                        l_a.a = b;
                                    }
                                }
                                if (jSONObject.has(DeviceInfo.TAG_VERSION)) {
                                    l_a.b = jSONObject.getInt(DeviceInfo.TAG_VERSION);
                                }
                                if (t.a(jSONObject, j.c)) {
                                    aVar = new a();
                                    aVar.a = false;
                                    aVar.b = false;
                                    l_a.t = aVar;
                                    jSONObject2 = jSONObject.getJSONObject(j.c);
                                    if (t.a(jSONObject2, "11K")) {
                                        try {
                                            jSONObject3 = jSONObject2.getJSONObject("11K");
                                            aVar.a = a(jSONObject3.getString("able"), false);
                                            if (jSONObject3.has("off")) {
                                                aVar.c = jSONObject3.getJSONObject("off");
                                            }
                                        } catch (Throwable th2) {
                                            w.a(th2, "AuthConfigManager", "loadException");
                                        }
                                    }
                                    if (t.a(jSONObject2, "11B")) {
                                        l_a.h = jSONObject2.getJSONObject("11B");
                                    }
                                    if (t.a(jSONObject2, "11C")) {
                                        l_a.k = jSONObject2.getJSONObject("11C");
                                    }
                                    if (t.a(jSONObject2, "11I")) {
                                        l_a.l = jSONObject2.getJSONObject("11I");
                                    }
                                    if (t.a(jSONObject2, "11H")) {
                                        l_a.m = jSONObject2.getJSONObject("11H");
                                    }
                                    if (t.a(jSONObject2, "11E")) {
                                        l_a.n = jSONObject2.getJSONObject("11E");
                                    }
                                    if (t.a(jSONObject2, "11F")) {
                                        l_a.o = jSONObject2.getJSONObject("11F");
                                    }
                                    if (t.a(jSONObject2, "13A")) {
                                        l_a.q = jSONObject2.getJSONObject("13A");
                                    }
                                    if (t.a(jSONObject2, "13J")) {
                                        l_a.i = jSONObject2.getJSONObject("13J");
                                    }
                                    if (t.a(jSONObject2, "11G")) {
                                        l_a.p = jSONObject2.getJSONObject("11G");
                                    }
                                    if (t.a(jSONObject2, "001")) {
                                        jSONObject4 = jSONObject2.getJSONObject("001");
                                        dVar = new d();
                                        if (jSONObject4 != null) {
                                            try {
                                                a3 = a(jSONObject4, "md5");
                                                a4 = a(jSONObject4, "url");
                                                obj = a(jSONObject4, "sdkversion");
                                                dVar.a = a4;
                                                dVar.b = a3;
                                                dVar.c = obj;
                                            } catch (Throwable th22) {
                                                w.a(th22, "ConfigManager", "parseSDKUpdate");
                                            }
                                        }
                                        l_a.u = dVar;
                                    }
                                    if (t.a(jSONObject2, "002")) {
                                        jSONObject4 = jSONObject2.getJSONObject("002");
                                        cVar = new c();
                                        if (jSONObject4 != null) {
                                            try {
                                                a5 = a(jSONObject4, "md5");
                                                a2 = a(jSONObject4, "url");
                                                cVar.b = a5;
                                                cVar.a = a2;
                                            } catch (Throwable th222) {
                                                w.a(th222, "ConfigManager", "parseSDKCoordinate");
                                            }
                                        }
                                        l_a.v = cVar;
                                    }
                                    if (t.a(jSONObject2, "006")) {
                                        l_a.r = jSONObject2.getJSONObject("006");
                                    }
                                    if (t.a(jSONObject2, "010")) {
                                        l_a.s = jSONObject2.getJSONObject("010");
                                    }
                                    if (t.a(jSONObject2, "11Z")) {
                                        jSONObject4 = jSONObject2.getJSONObject("11Z");
                                        bVar = new b();
                                        a(jSONObject4, bVar);
                                        l_a.w = bVar;
                                    }
                                    if (t.a(jSONObject2, "135")) {
                                        l_a.j = jSONObject2.getJSONObject("135");
                                    }
                                    if (t.a(jSONObject2, "13S")) {
                                        l_a.g = jSONObject2.getJSONObject("13S");
                                    }
                                    if (t.a(jSONObject2, "121")) {
                                        jSONObject4 = jSONObject2.getJSONObject("121");
                                        bVar = new b();
                                        a(jSONObject4, bVar);
                                        l_a.x = bVar;
                                    }
                                    if (t.a(jSONObject2, "122")) {
                                        jSONObject4 = jSONObject2.getJSONObject("122");
                                        bVar = new b();
                                        a(jSONObject4, bVar);
                                        l_a.y = bVar;
                                    }
                                    if (t.a(jSONObject2, "123")) {
                                        jSONObject4 = jSONObject2.getJSONObject("123");
                                        bVar = new b();
                                        a(jSONObject4, bVar);
                                        l_a.z = bVar;
                                    }
                                    if (t.a(jSONObject2, "011")) {
                                        l_a.c = jSONObject2.getJSONObject("011");
                                    }
                                    if (t.a(jSONObject2, "012")) {
                                        l_a.d = jSONObject2.getJSONObject("012");
                                    }
                                    if (t.a(jSONObject2, "013")) {
                                        l_a.e = jSONObject2.getJSONObject("013");
                                    }
                                    if (t.a(jSONObject2, "014")) {
                                        l_a.f = jSONObject2.getJSONObject("014");
                                    }
                                }
                            }
                        } catch (Throwable th2222) {
                            w.a(th2222, "AuthConfigManager", "loadConfig");
                        }
                    }
                    return l_a;
                } catch (IllegalBlockSizeException e3) {
                    bArr = boVar;
                    obj = boVar;
                    boVar = a;
                    if (bArr != null) {
                        if (TextUtils.isEmpty(a2)) {
                            a2 = t.a(bArr);
                        }
                        jSONObject = new JSONObject(a2);
                        if (jSONObject.has("status")) {
                            i = jSONObject.getInt("status");
                            if (i == 1) {
                                a = 1;
                            } else if (i == 0) {
                                a2 = "authcsid";
                                str2 = "authgsid";
                                if (boVar != null) {
                                    a2 = boVar.c;
                                    str2 = boVar.d;
                                }
                                t.a(context, a2, str2, jSONObject);
                                a = 0;
                                if (jSONObject.has("info")) {
                                    b = jSONObject.getString("info");
                                }
                                a2 = "";
                                if (jSONObject.has("infocode")) {
                                    a2 = jSONObject.getString("infocode");
                                }
                                z.a(sVar, "/v3/iasdkauth", b, str2, a2);
                                if (a == 0) {
                                    l_a.a = b;
                                }
                            }
                            if (jSONObject.has(DeviceInfo.TAG_VERSION)) {
                                l_a.b = jSONObject.getInt(DeviceInfo.TAG_VERSION);
                            }
                            if (t.a(jSONObject, j.c)) {
                                aVar = new a();
                                aVar.a = false;
                                aVar.b = false;
                                l_a.t = aVar;
                                jSONObject2 = jSONObject.getJSONObject(j.c);
                                if (t.a(jSONObject2, "11K")) {
                                    jSONObject3 = jSONObject2.getJSONObject("11K");
                                    aVar.a = a(jSONObject3.getString("able"), false);
                                    if (jSONObject3.has("off")) {
                                        aVar.c = jSONObject3.getJSONObject("off");
                                    }
                                }
                                if (t.a(jSONObject2, "11B")) {
                                    l_a.h = jSONObject2.getJSONObject("11B");
                                }
                                if (t.a(jSONObject2, "11C")) {
                                    l_a.k = jSONObject2.getJSONObject("11C");
                                }
                                if (t.a(jSONObject2, "11I")) {
                                    l_a.l = jSONObject2.getJSONObject("11I");
                                }
                                if (t.a(jSONObject2, "11H")) {
                                    l_a.m = jSONObject2.getJSONObject("11H");
                                }
                                if (t.a(jSONObject2, "11E")) {
                                    l_a.n = jSONObject2.getJSONObject("11E");
                                }
                                if (t.a(jSONObject2, "11F")) {
                                    l_a.o = jSONObject2.getJSONObject("11F");
                                }
                                if (t.a(jSONObject2, "13A")) {
                                    l_a.q = jSONObject2.getJSONObject("13A");
                                }
                                if (t.a(jSONObject2, "13J")) {
                                    l_a.i = jSONObject2.getJSONObject("13J");
                                }
                                if (t.a(jSONObject2, "11G")) {
                                    l_a.p = jSONObject2.getJSONObject("11G");
                                }
                                if (t.a(jSONObject2, "001")) {
                                    jSONObject4 = jSONObject2.getJSONObject("001");
                                    dVar = new d();
                                    if (jSONObject4 != null) {
                                        a3 = a(jSONObject4, "md5");
                                        a4 = a(jSONObject4, "url");
                                        obj = a(jSONObject4, "sdkversion");
                                        dVar.a = a4;
                                        dVar.b = a3;
                                        dVar.c = obj;
                                    }
                                    l_a.u = dVar;
                                }
                                if (t.a(jSONObject2, "002")) {
                                    jSONObject4 = jSONObject2.getJSONObject("002");
                                    cVar = new c();
                                    if (jSONObject4 != null) {
                                        a5 = a(jSONObject4, "md5");
                                        a2 = a(jSONObject4, "url");
                                        cVar.b = a5;
                                        cVar.a = a2;
                                    }
                                    l_a.v = cVar;
                                }
                                if (t.a(jSONObject2, "006")) {
                                    l_a.r = jSONObject2.getJSONObject("006");
                                }
                                if (t.a(jSONObject2, "010")) {
                                    l_a.s = jSONObject2.getJSONObject("010");
                                }
                                if (t.a(jSONObject2, "11Z")) {
                                    jSONObject4 = jSONObject2.getJSONObject("11Z");
                                    bVar = new b();
                                    a(jSONObject4, bVar);
                                    l_a.w = bVar;
                                }
                                if (t.a(jSONObject2, "135")) {
                                    l_a.j = jSONObject2.getJSONObject("135");
                                }
                                if (t.a(jSONObject2, "13S")) {
                                    l_a.g = jSONObject2.getJSONObject("13S");
                                }
                                if (t.a(jSONObject2, "121")) {
                                    jSONObject4 = jSONObject2.getJSONObject("121");
                                    bVar = new b();
                                    a(jSONObject4, bVar);
                                    l_a.x = bVar;
                                }
                                if (t.a(jSONObject2, "122")) {
                                    jSONObject4 = jSONObject2.getJSONObject("122");
                                    bVar = new b();
                                    a(jSONObject4, bVar);
                                    l_a.y = bVar;
                                }
                                if (t.a(jSONObject2, "123")) {
                                    jSONObject4 = jSONObject2.getJSONObject("123");
                                    bVar = new b();
                                    a(jSONObject4, bVar);
                                    l_a.z = bVar;
                                }
                                if (t.a(jSONObject2, "011")) {
                                    l_a.c = jSONObject2.getJSONObject("011");
                                }
                                if (t.a(jSONObject2, "012")) {
                                    l_a.d = jSONObject2.getJSONObject("012");
                                }
                                if (t.a(jSONObject2, "013")) {
                                    l_a.e = jSONObject2.getJSONObject("013");
                                }
                                if (t.a(jSONObject2, "014")) {
                                    l_a.f = jSONObject2.getJSONObject("014");
                                }
                            }
                        }
                    }
                    return l_a;
                } catch (Throwable th3) {
                    th2222 = th3;
                    bArr = boVar;
                    w.a(th2222, "ConfigManager", "loadConfig");
                    obj = boVar;
                    boVar = a;
                    if (bArr != null) {
                        if (TextUtils.isEmpty(a2)) {
                            a2 = t.a(bArr);
                        }
                        jSONObject = new JSONObject(a2);
                        if (jSONObject.has("status")) {
                            i = jSONObject.getInt("status");
                            if (i == 1) {
                                a = 1;
                            } else if (i == 0) {
                                a2 = "authcsid";
                                str2 = "authgsid";
                                if (boVar != null) {
                                    a2 = boVar.c;
                                    str2 = boVar.d;
                                }
                                t.a(context, a2, str2, jSONObject);
                                a = 0;
                                if (jSONObject.has("info")) {
                                    b = jSONObject.getString("info");
                                }
                                a2 = "";
                                if (jSONObject.has("infocode")) {
                                    a2 = jSONObject.getString("infocode");
                                }
                                z.a(sVar, "/v3/iasdkauth", b, str2, a2);
                                if (a == 0) {
                                    l_a.a = b;
                                }
                            }
                            if (jSONObject.has(DeviceInfo.TAG_VERSION)) {
                                l_a.b = jSONObject.getInt(DeviceInfo.TAG_VERSION);
                            }
                            if (t.a(jSONObject, j.c)) {
                                aVar = new a();
                                aVar.a = false;
                                aVar.b = false;
                                l_a.t = aVar;
                                jSONObject2 = jSONObject.getJSONObject(j.c);
                                if (t.a(jSONObject2, "11K")) {
                                    jSONObject3 = jSONObject2.getJSONObject("11K");
                                    aVar.a = a(jSONObject3.getString("able"), false);
                                    if (jSONObject3.has("off")) {
                                        aVar.c = jSONObject3.getJSONObject("off");
                                    }
                                }
                                if (t.a(jSONObject2, "11B")) {
                                    l_a.h = jSONObject2.getJSONObject("11B");
                                }
                                if (t.a(jSONObject2, "11C")) {
                                    l_a.k = jSONObject2.getJSONObject("11C");
                                }
                                if (t.a(jSONObject2, "11I")) {
                                    l_a.l = jSONObject2.getJSONObject("11I");
                                }
                                if (t.a(jSONObject2, "11H")) {
                                    l_a.m = jSONObject2.getJSONObject("11H");
                                }
                                if (t.a(jSONObject2, "11E")) {
                                    l_a.n = jSONObject2.getJSONObject("11E");
                                }
                                if (t.a(jSONObject2, "11F")) {
                                    l_a.o = jSONObject2.getJSONObject("11F");
                                }
                                if (t.a(jSONObject2, "13A")) {
                                    l_a.q = jSONObject2.getJSONObject("13A");
                                }
                                if (t.a(jSONObject2, "13J")) {
                                    l_a.i = jSONObject2.getJSONObject("13J");
                                }
                                if (t.a(jSONObject2, "11G")) {
                                    l_a.p = jSONObject2.getJSONObject("11G");
                                }
                                if (t.a(jSONObject2, "001")) {
                                    jSONObject4 = jSONObject2.getJSONObject("001");
                                    dVar = new d();
                                    if (jSONObject4 != null) {
                                        a3 = a(jSONObject4, "md5");
                                        a4 = a(jSONObject4, "url");
                                        obj = a(jSONObject4, "sdkversion");
                                        dVar.a = a4;
                                        dVar.b = a3;
                                        dVar.c = obj;
                                    }
                                    l_a.u = dVar;
                                }
                                if (t.a(jSONObject2, "002")) {
                                    jSONObject4 = jSONObject2.getJSONObject("002");
                                    cVar = new c();
                                    if (jSONObject4 != null) {
                                        a5 = a(jSONObject4, "md5");
                                        a2 = a(jSONObject4, "url");
                                        cVar.b = a5;
                                        cVar.a = a2;
                                    }
                                    l_a.v = cVar;
                                }
                                if (t.a(jSONObject2, "006")) {
                                    l_a.r = jSONObject2.getJSONObject("006");
                                }
                                if (t.a(jSONObject2, "010")) {
                                    l_a.s = jSONObject2.getJSONObject("010");
                                }
                                if (t.a(jSONObject2, "11Z")) {
                                    jSONObject4 = jSONObject2.getJSONObject("11Z");
                                    bVar = new b();
                                    a(jSONObject4, bVar);
                                    l_a.w = bVar;
                                }
                                if (t.a(jSONObject2, "135")) {
                                    l_a.j = jSONObject2.getJSONObject("135");
                                }
                                if (t.a(jSONObject2, "13S")) {
                                    l_a.g = jSONObject2.getJSONObject("13S");
                                }
                                if (t.a(jSONObject2, "121")) {
                                    jSONObject4 = jSONObject2.getJSONObject("121");
                                    bVar = new b();
                                    a(jSONObject4, bVar);
                                    l_a.x = bVar;
                                }
                                if (t.a(jSONObject2, "122")) {
                                    jSONObject4 = jSONObject2.getJSONObject("122");
                                    bVar = new b();
                                    a(jSONObject4, bVar);
                                    l_a.y = bVar;
                                }
                                if (t.a(jSONObject2, "123")) {
                                    jSONObject4 = jSONObject2.getJSONObject("123");
                                    bVar = new b();
                                    a(jSONObject4, bVar);
                                    l_a.z = bVar;
                                }
                                if (t.a(jSONObject2, "011")) {
                                    l_a.c = jSONObject2.getJSONObject("011");
                                }
                                if (t.a(jSONObject2, "012")) {
                                    l_a.d = jSONObject2.getJSONObject("012");
                                }
                                if (t.a(jSONObject2, "013")) {
                                    l_a.e = jSONObject2.getJSONObject("013");
                                }
                                if (t.a(jSONObject2, "014")) {
                                    l_a.f = jSONObject2.getJSONObject("014");
                                }
                            }
                        }
                    }
                    return l_a;
                }
            }
            bArr = boVar;
            try {
                obj = new byte[16];
                a4 = new byte[(bArr.length - 16)];
                System.arraycopy(bArr, 0, obj, 0, 16);
                System.arraycopy(bArr, 16, a4, 0, bArr.length - 16);
                Key secretKeySpec = new SecretKeySpec(obj, "AES");
                Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(2, secretKeySpec, new IvParameterSpec(t.b()));
                a2 = t.a(instance.doFinal(a4));
                boVar = a;
            } catch (j e4) {
                e = e4;
                l_a.a = e.a();
                z.a(sVar, "/v3/iasdkauth", e);
                obj = boVar;
                boVar = a;
                if (bArr != null) {
                    if (TextUtils.isEmpty(a2)) {
                        a2 = t.a(bArr);
                    }
                    jSONObject = new JSONObject(a2);
                    if (jSONObject.has("status")) {
                        i = jSONObject.getInt("status");
                        if (i == 1) {
                            a = 1;
                        } else if (i == 0) {
                            a2 = "authcsid";
                            str2 = "authgsid";
                            if (boVar != null) {
                                a2 = boVar.c;
                                str2 = boVar.d;
                            }
                            t.a(context, a2, str2, jSONObject);
                            a = 0;
                            if (jSONObject.has("info")) {
                                b = jSONObject.getString("info");
                            }
                            a2 = "";
                            if (jSONObject.has("infocode")) {
                                a2 = jSONObject.getString("infocode");
                            }
                            z.a(sVar, "/v3/iasdkauth", b, str2, a2);
                            if (a == 0) {
                                l_a.a = b;
                            }
                        }
                        if (jSONObject.has(DeviceInfo.TAG_VERSION)) {
                            l_a.b = jSONObject.getInt(DeviceInfo.TAG_VERSION);
                        }
                        if (t.a(jSONObject, j.c)) {
                            aVar = new a();
                            aVar.a = false;
                            aVar.b = false;
                            l_a.t = aVar;
                            jSONObject2 = jSONObject.getJSONObject(j.c);
                            if (t.a(jSONObject2, "11K")) {
                                jSONObject3 = jSONObject2.getJSONObject("11K");
                                aVar.a = a(jSONObject3.getString("able"), false);
                                if (jSONObject3.has("off")) {
                                    aVar.c = jSONObject3.getJSONObject("off");
                                }
                            }
                            if (t.a(jSONObject2, "11B")) {
                                l_a.h = jSONObject2.getJSONObject("11B");
                            }
                            if (t.a(jSONObject2, "11C")) {
                                l_a.k = jSONObject2.getJSONObject("11C");
                            }
                            if (t.a(jSONObject2, "11I")) {
                                l_a.l = jSONObject2.getJSONObject("11I");
                            }
                            if (t.a(jSONObject2, "11H")) {
                                l_a.m = jSONObject2.getJSONObject("11H");
                            }
                            if (t.a(jSONObject2, "11E")) {
                                l_a.n = jSONObject2.getJSONObject("11E");
                            }
                            if (t.a(jSONObject2, "11F")) {
                                l_a.o = jSONObject2.getJSONObject("11F");
                            }
                            if (t.a(jSONObject2, "13A")) {
                                l_a.q = jSONObject2.getJSONObject("13A");
                            }
                            if (t.a(jSONObject2, "13J")) {
                                l_a.i = jSONObject2.getJSONObject("13J");
                            }
                            if (t.a(jSONObject2, "11G")) {
                                l_a.p = jSONObject2.getJSONObject("11G");
                            }
                            if (t.a(jSONObject2, "001")) {
                                jSONObject4 = jSONObject2.getJSONObject("001");
                                dVar = new d();
                                if (jSONObject4 != null) {
                                    a3 = a(jSONObject4, "md5");
                                    a4 = a(jSONObject4, "url");
                                    obj = a(jSONObject4, "sdkversion");
                                    dVar.a = a4;
                                    dVar.b = a3;
                                    dVar.c = obj;
                                }
                                l_a.u = dVar;
                            }
                            if (t.a(jSONObject2, "002")) {
                                jSONObject4 = jSONObject2.getJSONObject("002");
                                cVar = new c();
                                if (jSONObject4 != null) {
                                    a5 = a(jSONObject4, "md5");
                                    a2 = a(jSONObject4, "url");
                                    cVar.b = a5;
                                    cVar.a = a2;
                                }
                                l_a.v = cVar;
                            }
                            if (t.a(jSONObject2, "006")) {
                                l_a.r = jSONObject2.getJSONObject("006");
                            }
                            if (t.a(jSONObject2, "010")) {
                                l_a.s = jSONObject2.getJSONObject("010");
                            }
                            if (t.a(jSONObject2, "11Z")) {
                                jSONObject4 = jSONObject2.getJSONObject("11Z");
                                bVar = new b();
                                a(jSONObject4, bVar);
                                l_a.w = bVar;
                            }
                            if (t.a(jSONObject2, "135")) {
                                l_a.j = jSONObject2.getJSONObject("135");
                            }
                            if (t.a(jSONObject2, "13S")) {
                                l_a.g = jSONObject2.getJSONObject("13S");
                            }
                            if (t.a(jSONObject2, "121")) {
                                jSONObject4 = jSONObject2.getJSONObject("121");
                                bVar = new b();
                                a(jSONObject4, bVar);
                                l_a.x = bVar;
                            }
                            if (t.a(jSONObject2, "122")) {
                                jSONObject4 = jSONObject2.getJSONObject("122");
                                bVar = new b();
                                a(jSONObject4, bVar);
                                l_a.y = bVar;
                            }
                            if (t.a(jSONObject2, "123")) {
                                jSONObject4 = jSONObject2.getJSONObject("123");
                                bVar = new b();
                                a(jSONObject4, bVar);
                                l_a.z = bVar;
                            }
                            if (t.a(jSONObject2, "011")) {
                                l_a.c = jSONObject2.getJSONObject("011");
                            }
                            if (t.a(jSONObject2, "012")) {
                                l_a.d = jSONObject2.getJSONObject("012");
                            }
                            if (t.a(jSONObject2, "013")) {
                                l_a.e = jSONObject2.getJSONObject("013");
                            }
                            if (t.a(jSONObject2, "014")) {
                                l_a.f = jSONObject2.getJSONObject("014");
                            }
                        }
                    }
                }
                return l_a;
            } catch (IllegalBlockSizeException e5) {
                obj = boVar;
                boVar = a;
                if (bArr != null) {
                    if (TextUtils.isEmpty(a2)) {
                        a2 = t.a(bArr);
                    }
                    jSONObject = new JSONObject(a2);
                    if (jSONObject.has("status")) {
                        i = jSONObject.getInt("status");
                        if (i == 1) {
                            a = 1;
                        } else if (i == 0) {
                            a2 = "authcsid";
                            str2 = "authgsid";
                            if (boVar != null) {
                                a2 = boVar.c;
                                str2 = boVar.d;
                            }
                            t.a(context, a2, str2, jSONObject);
                            a = 0;
                            if (jSONObject.has("info")) {
                                b = jSONObject.getString("info");
                            }
                            a2 = "";
                            if (jSONObject.has("infocode")) {
                                a2 = jSONObject.getString("infocode");
                            }
                            z.a(sVar, "/v3/iasdkauth", b, str2, a2);
                            if (a == 0) {
                                l_a.a = b;
                            }
                        }
                        if (jSONObject.has(DeviceInfo.TAG_VERSION)) {
                            l_a.b = jSONObject.getInt(DeviceInfo.TAG_VERSION);
                        }
                        if (t.a(jSONObject, j.c)) {
                            aVar = new a();
                            aVar.a = false;
                            aVar.b = false;
                            l_a.t = aVar;
                            jSONObject2 = jSONObject.getJSONObject(j.c);
                            if (t.a(jSONObject2, "11K")) {
                                jSONObject3 = jSONObject2.getJSONObject("11K");
                                aVar.a = a(jSONObject3.getString("able"), false);
                                if (jSONObject3.has("off")) {
                                    aVar.c = jSONObject3.getJSONObject("off");
                                }
                            }
                            if (t.a(jSONObject2, "11B")) {
                                l_a.h = jSONObject2.getJSONObject("11B");
                            }
                            if (t.a(jSONObject2, "11C")) {
                                l_a.k = jSONObject2.getJSONObject("11C");
                            }
                            if (t.a(jSONObject2, "11I")) {
                                l_a.l = jSONObject2.getJSONObject("11I");
                            }
                            if (t.a(jSONObject2, "11H")) {
                                l_a.m = jSONObject2.getJSONObject("11H");
                            }
                            if (t.a(jSONObject2, "11E")) {
                                l_a.n = jSONObject2.getJSONObject("11E");
                            }
                            if (t.a(jSONObject2, "11F")) {
                                l_a.o = jSONObject2.getJSONObject("11F");
                            }
                            if (t.a(jSONObject2, "13A")) {
                                l_a.q = jSONObject2.getJSONObject("13A");
                            }
                            if (t.a(jSONObject2, "13J")) {
                                l_a.i = jSONObject2.getJSONObject("13J");
                            }
                            if (t.a(jSONObject2, "11G")) {
                                l_a.p = jSONObject2.getJSONObject("11G");
                            }
                            if (t.a(jSONObject2, "001")) {
                                jSONObject4 = jSONObject2.getJSONObject("001");
                                dVar = new d();
                                if (jSONObject4 != null) {
                                    a3 = a(jSONObject4, "md5");
                                    a4 = a(jSONObject4, "url");
                                    obj = a(jSONObject4, "sdkversion");
                                    dVar.a = a4;
                                    dVar.b = a3;
                                    dVar.c = obj;
                                }
                                l_a.u = dVar;
                            }
                            if (t.a(jSONObject2, "002")) {
                                jSONObject4 = jSONObject2.getJSONObject("002");
                                cVar = new c();
                                if (jSONObject4 != null) {
                                    a5 = a(jSONObject4, "md5");
                                    a2 = a(jSONObject4, "url");
                                    cVar.b = a5;
                                    cVar.a = a2;
                                }
                                l_a.v = cVar;
                            }
                            if (t.a(jSONObject2, "006")) {
                                l_a.r = jSONObject2.getJSONObject("006");
                            }
                            if (t.a(jSONObject2, "010")) {
                                l_a.s = jSONObject2.getJSONObject("010");
                            }
                            if (t.a(jSONObject2, "11Z")) {
                                jSONObject4 = jSONObject2.getJSONObject("11Z");
                                bVar = new b();
                                a(jSONObject4, bVar);
                                l_a.w = bVar;
                            }
                            if (t.a(jSONObject2, "135")) {
                                l_a.j = jSONObject2.getJSONObject("135");
                            }
                            if (t.a(jSONObject2, "13S")) {
                                l_a.g = jSONObject2.getJSONObject("13S");
                            }
                            if (t.a(jSONObject2, "121")) {
                                jSONObject4 = jSONObject2.getJSONObject("121");
                                bVar = new b();
                                a(jSONObject4, bVar);
                                l_a.x = bVar;
                            }
                            if (t.a(jSONObject2, "122")) {
                                jSONObject4 = jSONObject2.getJSONObject("122");
                                bVar = new b();
                                a(jSONObject4, bVar);
                                l_a.y = bVar;
                            }
                            if (t.a(jSONObject2, "123")) {
                                jSONObject4 = jSONObject2.getJSONObject("123");
                                bVar = new b();
                                a(jSONObject4, bVar);
                                l_a.z = bVar;
                            }
                            if (t.a(jSONObject2, "011")) {
                                l_a.c = jSONObject2.getJSONObject("011");
                            }
                            if (t.a(jSONObject2, "012")) {
                                l_a.d = jSONObject2.getJSONObject("012");
                            }
                            if (t.a(jSONObject2, "013")) {
                                l_a.e = jSONObject2.getJSONObject("013");
                            }
                            if (t.a(jSONObject2, "014")) {
                                l_a.f = jSONObject2.getJSONObject("014");
                            }
                        }
                    }
                }
                return l_a;
            } catch (Throwable th4) {
                th2222 = th4;
                w.a(th2222, "ConfigManager", "loadConfig");
                obj = boVar;
                boVar = a;
                if (bArr != null) {
                    if (TextUtils.isEmpty(a2)) {
                        a2 = t.a(bArr);
                    }
                    jSONObject = new JSONObject(a2);
                    if (jSONObject.has("status")) {
                        i = jSONObject.getInt("status");
                        if (i == 1) {
                            a = 1;
                        } else if (i == 0) {
                            a2 = "authcsid";
                            str2 = "authgsid";
                            if (boVar != null) {
                                a2 = boVar.c;
                                str2 = boVar.d;
                            }
                            t.a(context, a2, str2, jSONObject);
                            a = 0;
                            if (jSONObject.has("info")) {
                                b = jSONObject.getString("info");
                            }
                            a2 = "";
                            if (jSONObject.has("infocode")) {
                                a2 = jSONObject.getString("infocode");
                            }
                            z.a(sVar, "/v3/iasdkauth", b, str2, a2);
                            if (a == 0) {
                                l_a.a = b;
                            }
                        }
                        if (jSONObject.has(DeviceInfo.TAG_VERSION)) {
                            l_a.b = jSONObject.getInt(DeviceInfo.TAG_VERSION);
                        }
                        if (t.a(jSONObject, j.c)) {
                            aVar = new a();
                            aVar.a = false;
                            aVar.b = false;
                            l_a.t = aVar;
                            jSONObject2 = jSONObject.getJSONObject(j.c);
                            if (t.a(jSONObject2, "11K")) {
                                jSONObject3 = jSONObject2.getJSONObject("11K");
                                aVar.a = a(jSONObject3.getString("able"), false);
                                if (jSONObject3.has("off")) {
                                    aVar.c = jSONObject3.getJSONObject("off");
                                }
                            }
                            if (t.a(jSONObject2, "11B")) {
                                l_a.h = jSONObject2.getJSONObject("11B");
                            }
                            if (t.a(jSONObject2, "11C")) {
                                l_a.k = jSONObject2.getJSONObject("11C");
                            }
                            if (t.a(jSONObject2, "11I")) {
                                l_a.l = jSONObject2.getJSONObject("11I");
                            }
                            if (t.a(jSONObject2, "11H")) {
                                l_a.m = jSONObject2.getJSONObject("11H");
                            }
                            if (t.a(jSONObject2, "11E")) {
                                l_a.n = jSONObject2.getJSONObject("11E");
                            }
                            if (t.a(jSONObject2, "11F")) {
                                l_a.o = jSONObject2.getJSONObject("11F");
                            }
                            if (t.a(jSONObject2, "13A")) {
                                l_a.q = jSONObject2.getJSONObject("13A");
                            }
                            if (t.a(jSONObject2, "13J")) {
                                l_a.i = jSONObject2.getJSONObject("13J");
                            }
                            if (t.a(jSONObject2, "11G")) {
                                l_a.p = jSONObject2.getJSONObject("11G");
                            }
                            if (t.a(jSONObject2, "001")) {
                                jSONObject4 = jSONObject2.getJSONObject("001");
                                dVar = new d();
                                if (jSONObject4 != null) {
                                    a3 = a(jSONObject4, "md5");
                                    a4 = a(jSONObject4, "url");
                                    obj = a(jSONObject4, "sdkversion");
                                    dVar.a = a4;
                                    dVar.b = a3;
                                    dVar.c = obj;
                                }
                                l_a.u = dVar;
                            }
                            if (t.a(jSONObject2, "002")) {
                                jSONObject4 = jSONObject2.getJSONObject("002");
                                cVar = new c();
                                if (jSONObject4 != null) {
                                    a5 = a(jSONObject4, "md5");
                                    a2 = a(jSONObject4, "url");
                                    cVar.b = a5;
                                    cVar.a = a2;
                                }
                                l_a.v = cVar;
                            }
                            if (t.a(jSONObject2, "006")) {
                                l_a.r = jSONObject2.getJSONObject("006");
                            }
                            if (t.a(jSONObject2, "010")) {
                                l_a.s = jSONObject2.getJSONObject("010");
                            }
                            if (t.a(jSONObject2, "11Z")) {
                                jSONObject4 = jSONObject2.getJSONObject("11Z");
                                bVar = new b();
                                a(jSONObject4, bVar);
                                l_a.w = bVar;
                            }
                            if (t.a(jSONObject2, "135")) {
                                l_a.j = jSONObject2.getJSONObject("135");
                            }
                            if (t.a(jSONObject2, "13S")) {
                                l_a.g = jSONObject2.getJSONObject("13S");
                            }
                            if (t.a(jSONObject2, "121")) {
                                jSONObject4 = jSONObject2.getJSONObject("121");
                                bVar = new b();
                                a(jSONObject4, bVar);
                                l_a.x = bVar;
                            }
                            if (t.a(jSONObject2, "122")) {
                                jSONObject4 = jSONObject2.getJSONObject("122");
                                bVar = new b();
                                a(jSONObject4, bVar);
                                l_a.y = bVar;
                            }
                            if (t.a(jSONObject2, "123")) {
                                jSONObject4 = jSONObject2.getJSONObject("123");
                                bVar = new b();
                                a(jSONObject4, bVar);
                                l_a.z = bVar;
                            }
                            if (t.a(jSONObject2, "011")) {
                                l_a.c = jSONObject2.getJSONObject("011");
                            }
                            if (t.a(jSONObject2, "012")) {
                                l_a.d = jSONObject2.getJSONObject("012");
                            }
                            if (t.a(jSONObject2, "013")) {
                                l_a.e = jSONObject2.getJSONObject("013");
                            }
                            if (t.a(jSONObject2, "014")) {
                                l_a.f = jSONObject2.getJSONObject("014");
                            }
                        }
                    }
                }
                return l_a;
            }
        } catch (j e6) {
            throw e6;
        } catch (IllegalBlockSizeException e7) {
            a = boVar;
            bArr = boVar;
            obj = boVar;
            boVar = a;
            if (bArr != null) {
                if (TextUtils.isEmpty(a2)) {
                    a2 = t.a(bArr);
                }
                jSONObject = new JSONObject(a2);
                if (jSONObject.has("status")) {
                    i = jSONObject.getInt("status");
                    if (i == 1) {
                        a = 1;
                    } else if (i == 0) {
                        a2 = "authcsid";
                        str2 = "authgsid";
                        if (boVar != null) {
                            a2 = boVar.c;
                            str2 = boVar.d;
                        }
                        t.a(context, a2, str2, jSONObject);
                        a = 0;
                        if (jSONObject.has("info")) {
                            b = jSONObject.getString("info");
                        }
                        a2 = "";
                        if (jSONObject.has("infocode")) {
                            a2 = jSONObject.getString("infocode");
                        }
                        z.a(sVar, "/v3/iasdkauth", b, str2, a2);
                        if (a == 0) {
                            l_a.a = b;
                        }
                    }
                    if (jSONObject.has(DeviceInfo.TAG_VERSION)) {
                        l_a.b = jSONObject.getInt(DeviceInfo.TAG_VERSION);
                    }
                    if (t.a(jSONObject, j.c)) {
                        aVar = new a();
                        aVar.a = false;
                        aVar.b = false;
                        l_a.t = aVar;
                        jSONObject2 = jSONObject.getJSONObject(j.c);
                        if (t.a(jSONObject2, "11K")) {
                            jSONObject3 = jSONObject2.getJSONObject("11K");
                            aVar.a = a(jSONObject3.getString("able"), false);
                            if (jSONObject3.has("off")) {
                                aVar.c = jSONObject3.getJSONObject("off");
                            }
                        }
                        if (t.a(jSONObject2, "11B")) {
                            l_a.h = jSONObject2.getJSONObject("11B");
                        }
                        if (t.a(jSONObject2, "11C")) {
                            l_a.k = jSONObject2.getJSONObject("11C");
                        }
                        if (t.a(jSONObject2, "11I")) {
                            l_a.l = jSONObject2.getJSONObject("11I");
                        }
                        if (t.a(jSONObject2, "11H")) {
                            l_a.m = jSONObject2.getJSONObject("11H");
                        }
                        if (t.a(jSONObject2, "11E")) {
                            l_a.n = jSONObject2.getJSONObject("11E");
                        }
                        if (t.a(jSONObject2, "11F")) {
                            l_a.o = jSONObject2.getJSONObject("11F");
                        }
                        if (t.a(jSONObject2, "13A")) {
                            l_a.q = jSONObject2.getJSONObject("13A");
                        }
                        if (t.a(jSONObject2, "13J")) {
                            l_a.i = jSONObject2.getJSONObject("13J");
                        }
                        if (t.a(jSONObject2, "11G")) {
                            l_a.p = jSONObject2.getJSONObject("11G");
                        }
                        if (t.a(jSONObject2, "001")) {
                            jSONObject4 = jSONObject2.getJSONObject("001");
                            dVar = new d();
                            if (jSONObject4 != null) {
                                a3 = a(jSONObject4, "md5");
                                a4 = a(jSONObject4, "url");
                                obj = a(jSONObject4, "sdkversion");
                                dVar.a = a4;
                                dVar.b = a3;
                                dVar.c = obj;
                            }
                            l_a.u = dVar;
                        }
                        if (t.a(jSONObject2, "002")) {
                            jSONObject4 = jSONObject2.getJSONObject("002");
                            cVar = new c();
                            if (jSONObject4 != null) {
                                a5 = a(jSONObject4, "md5");
                                a2 = a(jSONObject4, "url");
                                cVar.b = a5;
                                cVar.a = a2;
                            }
                            l_a.v = cVar;
                        }
                        if (t.a(jSONObject2, "006")) {
                            l_a.r = jSONObject2.getJSONObject("006");
                        }
                        if (t.a(jSONObject2, "010")) {
                            l_a.s = jSONObject2.getJSONObject("010");
                        }
                        if (t.a(jSONObject2, "11Z")) {
                            jSONObject4 = jSONObject2.getJSONObject("11Z");
                            bVar = new b();
                            a(jSONObject4, bVar);
                            l_a.w = bVar;
                        }
                        if (t.a(jSONObject2, "135")) {
                            l_a.j = jSONObject2.getJSONObject("135");
                        }
                        if (t.a(jSONObject2, "13S")) {
                            l_a.g = jSONObject2.getJSONObject("13S");
                        }
                        if (t.a(jSONObject2, "121")) {
                            jSONObject4 = jSONObject2.getJSONObject("121");
                            bVar = new b();
                            a(jSONObject4, bVar);
                            l_a.x = bVar;
                        }
                        if (t.a(jSONObject2, "122")) {
                            jSONObject4 = jSONObject2.getJSONObject("122");
                            bVar = new b();
                            a(jSONObject4, bVar);
                            l_a.y = bVar;
                        }
                        if (t.a(jSONObject2, "123")) {
                            jSONObject4 = jSONObject2.getJSONObject("123");
                            bVar = new b();
                            a(jSONObject4, bVar);
                            l_a.z = bVar;
                        }
                        if (t.a(jSONObject2, "011")) {
                            l_a.c = jSONObject2.getJSONObject("011");
                        }
                        if (t.a(jSONObject2, "012")) {
                            l_a.d = jSONObject2.getJSONObject("012");
                        }
                        if (t.a(jSONObject2, "013")) {
                            l_a.e = jSONObject2.getJSONObject("013");
                        }
                        if (t.a(jSONObject2, "014")) {
                            l_a.f = jSONObject2.getJSONObject("014");
                        }
                    }
                }
            }
            return l_a;
        } catch (j e8) {
            e6 = e8;
            a = boVar;
            bArr = boVar;
        } catch (Throwable th5) {
            th2222 = th5;
            a = boVar;
            bArr = boVar;
            w.a(th2222, "ConfigManager", "loadConfig");
            obj = boVar;
            boVar = a;
            if (bArr != null) {
                if (TextUtils.isEmpty(a2)) {
                    a2 = t.a(bArr);
                }
                jSONObject = new JSONObject(a2);
                if (jSONObject.has("status")) {
                    i = jSONObject.getInt("status");
                    if (i == 1) {
                        a = 1;
                    } else if (i == 0) {
                        a2 = "authcsid";
                        str2 = "authgsid";
                        if (boVar != null) {
                            a2 = boVar.c;
                            str2 = boVar.d;
                        }
                        t.a(context, a2, str2, jSONObject);
                        a = 0;
                        if (jSONObject.has("info")) {
                            b = jSONObject.getString("info");
                        }
                        a2 = "";
                        if (jSONObject.has("infocode")) {
                            a2 = jSONObject.getString("infocode");
                        }
                        z.a(sVar, "/v3/iasdkauth", b, str2, a2);
                        if (a == 0) {
                            l_a.a = b;
                        }
                    }
                    if (jSONObject.has(DeviceInfo.TAG_VERSION)) {
                        l_a.b = jSONObject.getInt(DeviceInfo.TAG_VERSION);
                    }
                    if (t.a(jSONObject, j.c)) {
                        aVar = new a();
                        aVar.a = false;
                        aVar.b = false;
                        l_a.t = aVar;
                        jSONObject2 = jSONObject.getJSONObject(j.c);
                        if (t.a(jSONObject2, "11K")) {
                            jSONObject3 = jSONObject2.getJSONObject("11K");
                            aVar.a = a(jSONObject3.getString("able"), false);
                            if (jSONObject3.has("off")) {
                                aVar.c = jSONObject3.getJSONObject("off");
                            }
                        }
                        if (t.a(jSONObject2, "11B")) {
                            l_a.h = jSONObject2.getJSONObject("11B");
                        }
                        if (t.a(jSONObject2, "11C")) {
                            l_a.k = jSONObject2.getJSONObject("11C");
                        }
                        if (t.a(jSONObject2, "11I")) {
                            l_a.l = jSONObject2.getJSONObject("11I");
                        }
                        if (t.a(jSONObject2, "11H")) {
                            l_a.m = jSONObject2.getJSONObject("11H");
                        }
                        if (t.a(jSONObject2, "11E")) {
                            l_a.n = jSONObject2.getJSONObject("11E");
                        }
                        if (t.a(jSONObject2, "11F")) {
                            l_a.o = jSONObject2.getJSONObject("11F");
                        }
                        if (t.a(jSONObject2, "13A")) {
                            l_a.q = jSONObject2.getJSONObject("13A");
                        }
                        if (t.a(jSONObject2, "13J")) {
                            l_a.i = jSONObject2.getJSONObject("13J");
                        }
                        if (t.a(jSONObject2, "11G")) {
                            l_a.p = jSONObject2.getJSONObject("11G");
                        }
                        if (t.a(jSONObject2, "001")) {
                            jSONObject4 = jSONObject2.getJSONObject("001");
                            dVar = new d();
                            if (jSONObject4 != null) {
                                a3 = a(jSONObject4, "md5");
                                a4 = a(jSONObject4, "url");
                                obj = a(jSONObject4, "sdkversion");
                                dVar.a = a4;
                                dVar.b = a3;
                                dVar.c = obj;
                            }
                            l_a.u = dVar;
                        }
                        if (t.a(jSONObject2, "002")) {
                            jSONObject4 = jSONObject2.getJSONObject("002");
                            cVar = new c();
                            if (jSONObject4 != null) {
                                a5 = a(jSONObject4, "md5");
                                a2 = a(jSONObject4, "url");
                                cVar.b = a5;
                                cVar.a = a2;
                            }
                            l_a.v = cVar;
                        }
                        if (t.a(jSONObject2, "006")) {
                            l_a.r = jSONObject2.getJSONObject("006");
                        }
                        if (t.a(jSONObject2, "010")) {
                            l_a.s = jSONObject2.getJSONObject("010");
                        }
                        if (t.a(jSONObject2, "11Z")) {
                            jSONObject4 = jSONObject2.getJSONObject("11Z");
                            bVar = new b();
                            a(jSONObject4, bVar);
                            l_a.w = bVar;
                        }
                        if (t.a(jSONObject2, "135")) {
                            l_a.j = jSONObject2.getJSONObject("135");
                        }
                        if (t.a(jSONObject2, "13S")) {
                            l_a.g = jSONObject2.getJSONObject("13S");
                        }
                        if (t.a(jSONObject2, "121")) {
                            jSONObject4 = jSONObject2.getJSONObject("121");
                            bVar = new b();
                            a(jSONObject4, bVar);
                            l_a.x = bVar;
                        }
                        if (t.a(jSONObject2, "122")) {
                            jSONObject4 = jSONObject2.getJSONObject("122");
                            bVar = new b();
                            a(jSONObject4, bVar);
                            l_a.y = bVar;
                        }
                        if (t.a(jSONObject2, "123")) {
                            jSONObject4 = jSONObject2.getJSONObject("123");
                            bVar = new b();
                            a(jSONObject4, bVar);
                            l_a.z = bVar;
                        }
                        if (t.a(jSONObject2, "011")) {
                            l_a.c = jSONObject2.getJSONObject("011");
                        }
                        if (t.a(jSONObject2, "012")) {
                            l_a.d = jSONObject2.getJSONObject("012");
                        }
                        if (t.a(jSONObject2, "013")) {
                            l_a.e = jSONObject2.getJSONObject("013");
                        }
                        if (t.a(jSONObject2, "014")) {
                            l_a.f = jSONObject2.getJSONObject("014");
                        }
                    }
                }
            }
            return l_a;
        }
        if (bArr != null) {
            if (TextUtils.isEmpty(a2)) {
                a2 = t.a(bArr);
            }
            jSONObject = new JSONObject(a2);
            if (jSONObject.has("status")) {
                i = jSONObject.getInt("status");
                if (i == 1) {
                    a = 1;
                } else if (i == 0) {
                    a2 = "authcsid";
                    str2 = "authgsid";
                    if (boVar != null) {
                        a2 = boVar.c;
                        str2 = boVar.d;
                    }
                    t.a(context, a2, str2, jSONObject);
                    a = 0;
                    if (jSONObject.has("info")) {
                        b = jSONObject.getString("info");
                    }
                    a2 = "";
                    if (jSONObject.has("infocode")) {
                        a2 = jSONObject.getString("infocode");
                    }
                    z.a(sVar, "/v3/iasdkauth", b, str2, a2);
                    if (a == 0) {
                        l_a.a = b;
                    }
                }
                if (jSONObject.has(DeviceInfo.TAG_VERSION)) {
                    l_a.b = jSONObject.getInt(DeviceInfo.TAG_VERSION);
                }
                if (t.a(jSONObject, j.c)) {
                    aVar = new a();
                    aVar.a = false;
                    aVar.b = false;
                    l_a.t = aVar;
                    jSONObject2 = jSONObject.getJSONObject(j.c);
                    if (t.a(jSONObject2, "11K")) {
                        jSONObject3 = jSONObject2.getJSONObject("11K");
                        aVar.a = a(jSONObject3.getString("able"), false);
                        if (jSONObject3.has("off")) {
                            aVar.c = jSONObject3.getJSONObject("off");
                        }
                    }
                    if (t.a(jSONObject2, "11B")) {
                        l_a.h = jSONObject2.getJSONObject("11B");
                    }
                    if (t.a(jSONObject2, "11C")) {
                        l_a.k = jSONObject2.getJSONObject("11C");
                    }
                    if (t.a(jSONObject2, "11I")) {
                        l_a.l = jSONObject2.getJSONObject("11I");
                    }
                    if (t.a(jSONObject2, "11H")) {
                        l_a.m = jSONObject2.getJSONObject("11H");
                    }
                    if (t.a(jSONObject2, "11E")) {
                        l_a.n = jSONObject2.getJSONObject("11E");
                    }
                    if (t.a(jSONObject2, "11F")) {
                        l_a.o = jSONObject2.getJSONObject("11F");
                    }
                    if (t.a(jSONObject2, "13A")) {
                        l_a.q = jSONObject2.getJSONObject("13A");
                    }
                    if (t.a(jSONObject2, "13J")) {
                        l_a.i = jSONObject2.getJSONObject("13J");
                    }
                    if (t.a(jSONObject2, "11G")) {
                        l_a.p = jSONObject2.getJSONObject("11G");
                    }
                    if (t.a(jSONObject2, "001")) {
                        jSONObject4 = jSONObject2.getJSONObject("001");
                        dVar = new d();
                        if (jSONObject4 != null) {
                            a3 = a(jSONObject4, "md5");
                            a4 = a(jSONObject4, "url");
                            obj = a(jSONObject4, "sdkversion");
                            if (!(TextUtils.isEmpty(a3) || TextUtils.isEmpty(a4) || TextUtils.isEmpty(obj))) {
                                dVar.a = a4;
                                dVar.b = a3;
                                dVar.c = obj;
                            }
                        }
                        l_a.u = dVar;
                    }
                    if (t.a(jSONObject2, "002")) {
                        jSONObject4 = jSONObject2.getJSONObject("002");
                        cVar = new c();
                        if (jSONObject4 != null) {
                            a5 = a(jSONObject4, "md5");
                            a2 = a(jSONObject4, "url");
                            cVar.b = a5;
                            cVar.a = a2;
                        }
                        l_a.v = cVar;
                    }
                    if (t.a(jSONObject2, "006")) {
                        l_a.r = jSONObject2.getJSONObject("006");
                    }
                    if (t.a(jSONObject2, "010")) {
                        l_a.s = jSONObject2.getJSONObject("010");
                    }
                    if (t.a(jSONObject2, "11Z")) {
                        jSONObject4 = jSONObject2.getJSONObject("11Z");
                        bVar = new b();
                        a(jSONObject4, bVar);
                        l_a.w = bVar;
                    }
                    if (t.a(jSONObject2, "135")) {
                        l_a.j = jSONObject2.getJSONObject("135");
                    }
                    if (t.a(jSONObject2, "13S")) {
                        l_a.g = jSONObject2.getJSONObject("13S");
                    }
                    if (t.a(jSONObject2, "121")) {
                        jSONObject4 = jSONObject2.getJSONObject("121");
                        bVar = new b();
                        a(jSONObject4, bVar);
                        l_a.x = bVar;
                    }
                    if (t.a(jSONObject2, "122")) {
                        jSONObject4 = jSONObject2.getJSONObject("122");
                        bVar = new b();
                        a(jSONObject4, bVar);
                        l_a.y = bVar;
                    }
                    if (t.a(jSONObject2, "123")) {
                        jSONObject4 = jSONObject2.getJSONObject("123");
                        bVar = new b();
                        a(jSONObject4, bVar);
                        l_a.z = bVar;
                    }
                    if (t.a(jSONObject2, "011")) {
                        l_a.c = jSONObject2.getJSONObject("011");
                    }
                    if (t.a(jSONObject2, "012")) {
                        l_a.d = jSONObject2.getJSONObject("012");
                    }
                    if (t.a(jSONObject2, "013")) {
                        l_a.e = jSONObject2.getJSONObject("013");
                    }
                    if (t.a(jSONObject2, "014")) {
                        l_a.f = jSONObject2.getJSONObject("014");
                    }
                }
            }
        }
        return l_a;
    }

    private static String a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null) {
            return "";
        }
        String str2 = "";
        return (!jSONObject.has(str) || jSONObject.getString(str).equals("[]")) ? str2 : jSONObject.optString(str);
    }

    @Deprecated
    public static void a(String str) {
        k.b(str);
    }

    private static void a(JSONObject jSONObject, b bVar) {
        if (bVar != null) {
            try {
                String a = a(jSONObject, "m");
                String a2 = a(jSONObject, User.UNDEFINED);
                String a3 = a(jSONObject, "v");
                String a4 = a(jSONObject, "able");
                bVar.c = a;
                bVar.b = a2;
                bVar.d = a3;
                bVar.a = a(a4, false);
            } catch (Throwable th) {
                w.a(th, "ConfigManager", "parsePluginEntity");
            }
        }
    }

    public static boolean a(String str, boolean z) {
        try {
            String[] split = URLDecoder.decode(str).split(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            return split[split.length + -1].charAt(4) % 2 == 1;
        } catch (Throwable th) {
            return z;
        }
    }
}
