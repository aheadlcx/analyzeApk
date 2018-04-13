package com.flurry.android;

import android.content.Context;
import com.alipay.sdk.util.h;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class z {
    private Context a;
    private u b;
    private a c;
    private volatile long d;
    private af e = new af(50);
    private af f = new af(50);
    private Map g = new HashMap();
    private Map h = new HashMap();
    private Map i = new HashMap();
    private Map j = new HashMap();
    private volatile boolean k;

    z() {
    }

    final void a(Context context, u uVar, a aVar) {
        this.a = context;
        this.b = uVar;
        this.c = aVar;
    }

    final synchronized v[] a(String str) {
        v[] vVarArr;
        vVarArr = (v[]) this.g.get(str);
        if (vVarArr == null) {
            vVarArr = (v[]) this.g.get("");
        }
        return vVarArr;
    }

    final synchronized al a(long j) {
        return (al) this.f.a(Long.valueOf(j));
    }

    final synchronized Set a() {
        return this.e.c();
    }

    final synchronized AdImage b(long j) {
        return (AdImage) this.e.a(Long.valueOf(j));
    }

    final synchronized AdImage a(short s) {
        Long l;
        l = (Long) this.j.get(Short.valueOf((short) 1));
        return l == null ? null : b(l.longValue());
    }

    final synchronized e b(String str) {
        e eVar;
        eVar = (e) this.h.get(str);
        if (eVar == null) {
            eVar = (e) this.h.get("");
        }
        return eVar;
    }

    final boolean b() {
        return this.k;
    }

    private synchronized c a(byte b) {
        return (c) this.i.get(Byte.valueOf(b));
    }

    final synchronized void a(Map map, Map map2, Map map3, Map map4, Map map5, Map map6) {
        this.d = System.currentTimeMillis();
        for (Entry entry : map4.entrySet()) {
            if (entry.getValue() != null) {
                this.e.a(entry.getKey(), entry.getValue());
            }
        }
        for (Entry entry2 : map5.entrySet()) {
            if (entry2.getValue() != null) {
                this.f.a(entry2.getKey(), entry2.getValue());
            }
        }
        if (!(map2 == null || map2.isEmpty())) {
            this.h = map2;
        }
        if (!(map3 == null || map3.isEmpty())) {
            this.i = map3;
        }
        if (!(map6 == null || map6.isEmpty())) {
            this.j = map6;
        }
        this.g = new HashMap();
        for (Entry entry22 : map2.entrySet()) {
            e eVar = (e) entry22.getValue();
            v[] vVarArr = (v[]) map.get(Byte.valueOf(eVar.b));
            if (vVarArr != null) {
                this.g.put(entry22.getKey(), vVarArr);
            }
            c cVar = (c) map3.get(Byte.valueOf(eVar.c));
            if (cVar != null) {
                eVar.d = cVar;
            }
        }
        f();
        a(202);
    }

    final long c() {
        return this.d;
    }

    final synchronized void d() {
        Closeable dataInputStream;
        Throwable th;
        File fileStreamPath = this.a.getFileStreamPath(g());
        if (fileStreamPath.exists()) {
            try {
                dataInputStream = new DataInputStream(new FileInputStream(fileStreamPath));
                try {
                    if (dataInputStream.readUnsignedShort() == 46587) {
                        a((DataInputStream) dataInputStream);
                        a(201);
                    } else {
                        a(fileStreamPath);
                    }
                    r.a(dataInputStream);
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        ah.a("FlurryAgent", "Discarding cache", th);
                        a(fileStreamPath);
                        r.a(dataInputStream);
                    } catch (Throwable th3) {
                        th = th3;
                        r.a(dataInputStream);
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                dataInputStream = null;
                r.a(dataInputStream);
                throw th;
            }
        }
        ah.c("FlurryAgent", "cache file does not exist, path=" + fileStreamPath.getAbsolutePath());
    }

    private static void a(File file) {
        if (!file.delete()) {
            ah.b("FlurryAgent", "Cannot delete cached ads");
        }
    }

    private void f() {
        Iterator it = this.i.values().iterator();
        while (it.hasNext()) {
            it.next();
        }
        for (v[] vVarArr : this.g.values()) {
            if (vVarArr != null) {
                for (v vVar : vVarArr) {
                    vVar.h = b(vVar.f.longValue());
                    if (vVar.h == null) {
                        ah.b("FlurryAgent", "Ad " + vVar.d + " has no image");
                    }
                    if (a(vVar.a) == null) {
                        ah.b("FlurryAgent", "Ad " + vVar.d + " has no pricing");
                    }
                }
            }
        }
        for (e eVar : this.h.values()) {
            eVar.d = a(eVar.c);
            if (eVar.d == null) {
                ah.d("FlurryAgent", "No ad theme found for " + eVar.c);
            }
        }
    }

    final synchronized void e() {
        Throwable th;
        Closeable closeable = null;
        synchronized (this) {
            try {
                File fileStreamPath = this.a.getFileStreamPath(g());
                File parentFile = fileStreamPath.getParentFile();
                if (parentFile.mkdirs() || parentFile.exists()) {
                    Closeable dataOutputStream = new DataOutputStream(new FileOutputStream(fileStreamPath));
                    try {
                        dataOutputStream.writeShort(46587);
                        a((DataOutputStream) dataOutputStream);
                        r.a(dataOutputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        closeable = dataOutputStream;
                        r.a(closeable);
                        throw th;
                    }
                } else {
                    ah.b("FlurryAgent", "Unable to create persistent dir: " + parentFile);
                    r.a(null);
                }
            } catch (Throwable th3) {
                th = th3;
                ah.b("FlurryAgent", "", th);
                r.a(closeable);
            }
        }
    }

    private void a(DataInputStream dataInputStream) {
        int i = 0;
        ah.a("FlurryAgent", "Reading cache");
        if (dataInputStream.readUnsignedShort() == 2) {
            int i2;
            long readLong;
            this.d = dataInputStream.readLong();
            int readUnsignedShort = dataInputStream.readUnsignedShort();
            this.e = new af(50);
            for (i2 = 0; i2 < readUnsignedShort; i2++) {
                readLong = dataInputStream.readLong();
                AdImage adImage = new AdImage();
                adImage.a(dataInputStream);
                this.e.a(Long.valueOf(readLong), adImage);
            }
            readUnsignedShort = dataInputStream.readUnsignedShort();
            this.f = new af(50);
            for (i2 = 0; i2 < readUnsignedShort; i2++) {
                readLong = dataInputStream.readLong();
                al alVar = new al();
                if (dataInputStream.readBoolean()) {
                    alVar.a = dataInputStream.readUTF();
                }
                if (dataInputStream.readBoolean()) {
                    alVar.b = dataInputStream.readUTF();
                }
                alVar.c = dataInputStream.readInt();
                this.f.a(Long.valueOf(readLong), alVar);
            }
            readUnsignedShort = dataInputStream.readUnsignedShort();
            this.h = new HashMap(readUnsignedShort);
            for (i2 = 0; i2 < readUnsignedShort; i2++) {
                this.h.put(dataInputStream.readUTF(), new e(dataInputStream));
            }
            int readUnsignedShort2 = dataInputStream.readUnsignedShort();
            this.g = new HashMap(readUnsignedShort2);
            for (readUnsignedShort = 0; readUnsignedShort < readUnsignedShort2; readUnsignedShort++) {
                String readUTF = dataInputStream.readUTF();
                int readUnsignedShort3 = dataInputStream.readUnsignedShort();
                Object obj = new v[readUnsignedShort3];
                for (i2 = 0; i2 < readUnsignedShort3; i2++) {
                    v vVar = new v();
                    vVar.a((DataInput) dataInputStream);
                    obj[i2] = vVar;
                }
                this.g.put(readUTF, obj);
            }
            readUnsignedShort = dataInputStream.readUnsignedShort();
            this.i = new HashMap();
            for (i2 = 0; i2 < readUnsignedShort; i2++) {
                byte readByte = dataInputStream.readByte();
                c cVar = new c();
                cVar.b(dataInputStream);
                this.i.put(Byte.valueOf(readByte), cVar);
            }
            i2 = dataInputStream.readUnsignedShort();
            this.j = new HashMap(i2);
            while (i < i2) {
                this.j.put(Short.valueOf(dataInputStream.readShort()), Long.valueOf(dataInputStream.readLong()));
                i++;
            }
            f();
            ah.a("FlurryAgent", "Cache read, num images: " + this.e.a());
        }
    }

    private void a(DataOutputStream dataOutputStream) {
        dataOutputStream.writeShort(2);
        dataOutputStream.writeLong(this.d);
        List<Entry> b = this.e.b();
        dataOutputStream.writeShort(b.size());
        for (Entry entry : b) {
            dataOutputStream.writeLong(((Long) entry.getKey()).longValue());
            AdImage adImage = (AdImage) entry.getValue();
            dataOutputStream.writeLong(adImage.a);
            dataOutputStream.writeInt(adImage.b);
            dataOutputStream.writeInt(adImage.c);
            dataOutputStream.writeUTF(adImage.d);
            dataOutputStream.writeInt(adImage.e.length);
            dataOutputStream.write(adImage.e);
        }
        b = this.f.b();
        dataOutputStream.writeShort(b.size());
        for (Entry entry2 : b) {
            dataOutputStream.writeLong(((Long) entry2.getKey()).longValue());
            al alVar = (al) entry2.getValue();
            boolean z = alVar.a != null;
            dataOutputStream.writeBoolean(z);
            if (z) {
                dataOutputStream.writeUTF(alVar.a);
            }
            z = alVar.b != null;
            dataOutputStream.writeBoolean(z);
            if (z) {
                dataOutputStream.writeUTF(alVar.b);
            }
            dataOutputStream.writeInt(alVar.c);
        }
        dataOutputStream.writeShort(this.h.size());
        for (Entry entry22 : this.h.entrySet()) {
            dataOutputStream.writeUTF((String) entry22.getKey());
            e eVar = (e) entry22.getValue();
            dataOutputStream.writeUTF(eVar.a);
            dataOutputStream.writeByte(eVar.b);
            dataOutputStream.writeByte(eVar.c);
        }
        dataOutputStream.writeShort(this.g.size());
        for (Entry entry3 : this.g.entrySet()) {
            dataOutputStream.writeUTF((String) entry3.getKey());
            v[] vVarArr = (v[]) entry3.getValue();
            int length = vVarArr == null ? 0 : vVarArr.length;
            dataOutputStream.writeShort(length);
            for (int i = 0; i < length; i++) {
                v vVar = vVarArr[i];
                dataOutputStream.writeLong(vVar.a);
                dataOutputStream.writeLong(vVar.b);
                dataOutputStream.writeUTF(vVar.d);
                dataOutputStream.writeUTF(vVar.c);
                dataOutputStream.writeLong(vVar.e);
                dataOutputStream.writeLong(vVar.f.longValue());
                dataOutputStream.writeByte(vVar.g.length);
                dataOutputStream.write(vVar.g);
            }
        }
        dataOutputStream.writeShort(this.i.size());
        for (Entry entry32 : this.i.entrySet()) {
            dataOutputStream.writeByte(((Byte) entry32.getKey()).byteValue());
            ((c) entry32.getValue()).a((DataOutput) dataOutputStream);
        }
        dataOutputStream.writeShort(this.j.size());
        for (Entry entry222 : this.j.entrySet()) {
            dataOutputStream.writeShort(((Short) entry222.getKey()).shortValue());
            dataOutputStream.writeLong(((Long) entry222.getValue()).longValue());
        }
    }

    private String g() {
        return ".flurryappcircle." + Integer.toString(this.c.a.hashCode(), 16);
    }

    private void a(int i) {
        this.k = !this.g.isEmpty();
        if (this.k) {
            this.b.a(i);
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append("adImages (" + this.e.b().size() + "),\n");
        stringBuilder.append("adBlock (" + this.g.size() + "):").append(",\n");
        for (Entry entry : this.g.entrySet()) {
            stringBuilder.append("\t" + ((String) entry.getKey()) + ": " + Arrays.toString((Object[]) entry.getValue()));
        }
        stringBuilder.append("adHooks (" + this.h.size() + "):" + this.h).append(",\n");
        stringBuilder.append("adThemes (" + this.i.size() + "):" + this.i).append(",\n");
        stringBuilder.append("auxMap (" + this.j.size() + "):" + this.j).append(",\n");
        stringBuilder.append(h.d);
        return stringBuilder.toString();
    }
}
