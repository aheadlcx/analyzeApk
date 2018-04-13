package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.commonsdk.proguard.b;
import com.umeng.commonsdk.proguard.l;
import com.umeng.commonsdk.proguard.o;
import com.umeng.commonsdk.proguard.u;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback;
import com.umeng.commonsdk.statistics.internal.d;
import com.umeng.commonsdk.statistics.proto.e;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ImprintHandler {
    private static Object a = new Object();
    private static final byte[] b = "pbl0".getBytes();
    private static Map<String, ArrayList<UMImprintChangeCallback>> d = new HashMap();
    private static Object e = new Object();
    private static ImprintHandler h = null;
    private static Context i;
    private d c;
    private a f = new a();
    private com.umeng.commonsdk.statistics.proto.d g = null;

    public static class a {
        private Map<String, String> a = new HashMap();

        a() {
        }

        public synchronized void a(String str) {
            if (this.a != null && this.a.size() > 0 && !TextUtils.isEmpty(str) && this.a.containsKey(str)) {
                this.a.remove(str);
            }
        }

        public void a(com.umeng.commonsdk.statistics.proto.d dVar) {
            if (dVar != null) {
                b(dVar);
            }
        }

        private synchronized void b(com.umeng.commonsdk.statistics.proto.d dVar) {
            if (dVar != null) {
                try {
                    if (dVar.e()) {
                        Map c = dVar.c();
                        for (String str : c.keySet()) {
                            if (!TextUtils.isEmpty(str)) {
                                e eVar = (e) c.get(str);
                                if (eVar != null) {
                                    Object b = eVar.b();
                                    if (!TextUtils.isEmpty(b)) {
                                        this.a.put(str, b);
                                        if (AnalyticsConstants.UM_DEBUG) {
                                            Log.i("ImprintHandler", "imKey is " + str + ", imValue is " + b);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }

        public synchronized String a(String str, String str2) {
            if (!TextUtils.isEmpty(str) && this.a.size() > 0) {
                String str3 = (String) this.a.get(str);
                if (!TextUtils.isEmpty(str3)) {
                    str2 = str3;
                }
            }
            return str2;
        }
    }

    private ImprintHandler(Context context) {
        i = context.getApplicationContext();
    }

    public static synchronized ImprintHandler getImprintService(Context context) {
        ImprintHandler imprintHandler;
        synchronized (ImprintHandler.class) {
            if (h == null) {
                h = new ImprintHandler(context);
                h.e();
            }
            imprintHandler = h;
        }
        return imprintHandler;
    }

    private static void a(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        int i = 0;
        synchronized (e) {
            try {
                if (d.containsKey(str)) {
                    ArrayList arrayList = (ArrayList) d.get(str);
                    int size = arrayList.size();
                    com.umeng.commonsdk.statistics.common.e.c("--->>> addCallback: before add: callbacks size is: " + size);
                    while (i < size) {
                        if (uMImprintChangeCallback == arrayList.get(i)) {
                            com.umeng.commonsdk.statistics.common.e.c("--->>> addCallback: callback has exist, just exit");
                            return;
                        }
                        i++;
                    }
                    arrayList.add(uMImprintChangeCallback);
                    com.umeng.commonsdk.statistics.common.e.c("--->>> addCallback: after add: callbacks size is: " + arrayList.size());
                }
                ArrayList arrayList2 = new ArrayList();
                int size2 = arrayList2.size();
                com.umeng.commonsdk.statistics.common.e.c("--->>> addCallback: before add: callbacks size is: " + size2);
                for (int i2 = 0; i2 < size2; i2++) {
                    if (uMImprintChangeCallback == arrayList2.get(i2)) {
                        com.umeng.commonsdk.statistics.common.e.c("--->>> addCallback: callback has exist, just exit");
                        return;
                    }
                }
                arrayList2.add(uMImprintChangeCallback);
                com.umeng.commonsdk.statistics.common.e.c("--->>> addCallback: after add: callbacks size is: " + arrayList2.size());
                d.put(str, arrayList2);
            } catch (Throwable th) {
                b.a(i, th);
            }
        }
    }

    private static void b(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintChangeCallback != null) {
            synchronized (e) {
                try {
                    if (d.containsKey(str)) {
                        ArrayList arrayList = (ArrayList) d.get(str);
                        if (uMImprintChangeCallback != null && arrayList.size() > 0) {
                            int size = arrayList.size();
                            com.umeng.commonsdk.statistics.common.e.c("--->>> removeCallback: before remove: callbacks size is: " + size);
                            for (int i = 0; i < size; i++) {
                                if (uMImprintChangeCallback == arrayList.get(i)) {
                                    com.umeng.commonsdk.statistics.common.e.c("--->>> removeCallback: remove index " + i);
                                    arrayList.remove(i);
                                    break;
                                }
                            }
                            com.umeng.commonsdk.statistics.common.e.c("--->>> removeCallback: after remove: callbacks size is: " + arrayList.size());
                            if (arrayList.size() == 0) {
                                com.umeng.commonsdk.statistics.common.e.c("--->>> removeCallback: remove key from map: key = " + str);
                                d.remove(str);
                            }
                        }
                    }
                } catch (Throwable th) {
                    b.a(i, th);
                }
            }
        }
    }

    public void registImprintCallback(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintChangeCallback != null) {
            a(str, uMImprintChangeCallback);
        }
    }

    public void unregistImprintCallback(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintChangeCallback != null) {
            b(str, uMImprintChangeCallback);
        }
    }

    public void a(d dVar) {
        this.c = dVar;
    }

    public String a(com.umeng.commonsdk.statistics.proto.d dVar) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : new TreeMap(dVar.c()).entrySet()) {
            stringBuilder.append((String) entry.getKey());
            if (((e) entry.getValue()).d()) {
                stringBuilder.append(((e) entry.getValue()).b());
            }
            stringBuilder.append(((e) entry.getValue()).e());
            stringBuilder.append(((e) entry.getValue()).h());
        }
        stringBuilder.append(dVar.b);
        return HelperUtils.MD5(stringBuilder.toString()).toLowerCase(Locale.US);
    }

    private boolean c(com.umeng.commonsdk.statistics.proto.d dVar) {
        if (!dVar.i().equals(a(dVar))) {
            return false;
        }
        for (e eVar : dVar.c().values()) {
            byte[] reverseHexString = DataHelper.reverseHexString(eVar.h());
            byte[] a = a(eVar);
            for (int i = 0; i < 4; i++) {
                if (reverseHexString[i] != a[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public byte[] a(e eVar) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(null);
        allocate.putLong(eVar.e());
        byte[] array = allocate.array();
        byte[] bArr = b;
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = (byte) (array[i] ^ bArr[i]);
        }
        return bArr2;
    }

    public void b(com.umeng.commonsdk.statistics.proto.d dVar) {
        String str = null;
        if (dVar == null) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.d("Imprint is null");
            }
        } else if (c(dVar)) {
            String i;
            Object obj;
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.d("Imprint is ok");
            }
            Map hashMap = new HashMap();
            synchronized (this) {
                com.umeng.commonsdk.statistics.proto.d d;
                com.umeng.commonsdk.statistics.proto.d dVar2 = this.g;
                i = dVar2 == null ? null : dVar2.i();
                if (dVar2 == null) {
                    d = d(dVar);
                } else {
                    d = a(dVar2, dVar, hashMap);
                }
                this.g = d;
                if (d != null) {
                    str = d.i();
                }
                if (a(i, str)) {
                    obj = null;
                } else {
                    obj = 1;
                }
            }
            if (this.g != null) {
                if (AnalyticsConstants.UM_DEBUG) {
                    if (obj != null) {
                        this.f.a(this.g);
                        if (this.c != null) {
                            this.c.onImprintChanged(this.f);
                        }
                    }
                } else if (obj != null) {
                    this.f.a(this.g);
                    if (this.c != null) {
                        this.c.onImprintChanged(this.f);
                    }
                }
            }
            if (hashMap.size() > 0) {
                synchronized (e) {
                    for (Entry entry : hashMap.entrySet()) {
                        i = (String) entry.getKey();
                        String str2 = (String) entry.getValue();
                        if (!TextUtils.isEmpty(i) && d.containsKey(i)) {
                            com.umeng.commonsdk.statistics.common.e.c("--->>> target imprint key is: " + i + "; value is: " + str2);
                            ArrayList arrayList = (ArrayList) d.get(i);
                            if (arrayList != null) {
                                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                    ((UMImprintChangeCallback) arrayList.get(i2)).onImprintValueChanged(i, str2);
                                }
                            }
                        }
                    }
                }
            }
        } else if (AnalyticsConstants.UM_DEBUG) {
            MLog.e("Imprint is not valid");
        }
    }

    private boolean a(String str, String str2) {
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 != null) {
            return false;
        }
        return true;
    }

    private com.umeng.commonsdk.statistics.proto.d a(com.umeng.commonsdk.statistics.proto.d dVar, com.umeng.commonsdk.statistics.proto.d dVar2, Map<String, String> map) {
        if (dVar2 != null) {
            Map c = dVar.c();
            String str = "";
            str = "";
            for (Entry entry : dVar2.c().entrySet()) {
                if (((e) entry.getValue()).d()) {
                    c.put(entry.getKey(), entry.getValue());
                    synchronized (e) {
                        str = (String) entry.getKey();
                        String str2 = ((e) entry.getValue()).a;
                        if (!(TextUtils.isEmpty(str) || !d.containsKey(str) || ((ArrayList) d.get(str)) == null)) {
                            map.put(str, str2);
                        }
                    }
                } else {
                    String str3 = (String) entry.getKey();
                    c.remove(str3);
                    this.f.a(str3);
                }
            }
            dVar.a(dVar2.f());
            dVar.a(a(dVar));
        }
        return dVar;
    }

    private com.umeng.commonsdk.statistics.proto.d d(com.umeng.commonsdk.statistics.proto.d dVar) {
        Map c = dVar.c();
        List<String> arrayList = new ArrayList(c.size() / 2);
        for (Entry entry : c.entrySet()) {
            if (!((e) entry.getValue()).d()) {
                arrayList.add(entry.getKey());
            }
        }
        for (String remove : arrayList) {
            c.remove(remove);
        }
        return dVar;
    }

    public synchronized com.umeng.commonsdk.statistics.proto.d a() {
        return this.g;
    }

    public a b() {
        return this.f;
    }

    private void e() {
        byte[] readStreamToByteArray;
        Exception e;
        com.umeng.commonsdk.statistics.proto.d dVar;
        InputStream inputStream = null;
        File file = new File(i.getFilesDir(), ".imprint");
        synchronized (a) {
            if (file.exists()) {
                InputStream openFileInput;
                try {
                    openFileInput = i.openFileInput(".imprint");
                    try {
                        readStreamToByteArray = HelperUtils.readStreamToByteArray(openFileInput);
                        HelperUtils.safeClose(openFileInput);
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            e.printStackTrace();
                            HelperUtils.safeClose(openFileInput);
                            if (readStreamToByteArray != null) {
                                try {
                                    dVar = new com.umeng.commonsdk.statistics.proto.d();
                                    new o().a((l) dVar, readStreamToByteArray);
                                    this.g = dVar;
                                    this.f.a(dVar);
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                            return;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            inputStream = openFileInput;
                            HelperUtils.safeClose(inputStream);
                            throw th2;
                        }
                    }
                } catch (Exception e4) {
                    e3 = e4;
                    openFileInput = inputStream;
                    e3.printStackTrace();
                    HelperUtils.safeClose(openFileInput);
                    if (readStreamToByteArray != null) {
                        dVar = new com.umeng.commonsdk.statistics.proto.d();
                        new o().a((l) dVar, readStreamToByteArray);
                        this.g = dVar;
                        this.f.a(dVar);
                    }
                    return;
                } catch (Throwable th3) {
                    th2 = th3;
                    HelperUtils.safeClose(inputStream);
                    throw th2;
                }
                if (readStreamToByteArray != null) {
                    dVar = new com.umeng.commonsdk.statistics.proto.d();
                    new o().a((l) dVar, readStreamToByteArray);
                    this.g = dVar;
                    this.f.a(dVar);
                }
                return;
            }
        }
    }

    public void c() {
        if (this.g != null) {
            OutputStream fileOutputStream;
            try {
                synchronized (a) {
                    byte[] a = new u().a(this.g);
                    fileOutputStream = new FileOutputStream(new File(i.getFilesDir(), ".imprint"));
                    fileOutputStream.write(a);
                    fileOutputStream.flush();
                    HelperUtils.safeClose(fileOutputStream);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                HelperUtils.safeClose(fileOutputStream);
            }
        }
    }

    public boolean d() {
        return new File(i.getFilesDir(), ".imprint").delete();
    }
}
