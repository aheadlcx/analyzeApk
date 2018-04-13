package com.tencent.bugly.proguard;

import cn.xiaochuankeji.tieba.background.AppController;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.mozilla.classfile.ByteCode;

public final class k {
    protected String a = AppController.kDataCacheCharset;
    private ByteBuffer b;

    public static class a {
        public byte a;
        public int b;
    }

    public k(byte[] bArr) {
        this.b = ByteBuffer.wrap(bArr);
    }

    public k(byte[] bArr, int i) {
        this.b = ByteBuffer.wrap(bArr);
        this.b.position(i);
    }

    public void a(byte[] bArr) {
        if (this.b != null) {
            this.b.clear();
        }
        this.b = ByteBuffer.wrap(bArr);
    }

    public static int a(a aVar, ByteBuffer byteBuffer) {
        byte b = byteBuffer.get();
        aVar.a = (byte) (b & 15);
        aVar.b = (b & 240) >> 4;
        if (aVar.b != 15) {
            return 1;
        }
        aVar.b = byteBuffer.get();
        return 2;
    }

    public void a(a aVar) {
        a(aVar, this.b);
    }

    private int b(a aVar) {
        return a(aVar, this.b.duplicate());
    }

    private void b(int i) {
        this.b.position(this.b.position() + i);
    }

    public boolean a(int i) {
        try {
            a aVar = new a();
            while (true) {
                int b = b(aVar);
                if (i > aVar.b && aVar.a != ByteCode.T_LONG) {
                    b(b);
                    a(aVar.a);
                }
            }
            if (i == aVar.b) {
                return true;
            }
            return false;
        } catch (h e) {
            return false;
        } catch (BufferUnderflowException e2) {
            return false;
        }
    }

    public void a() {
        a aVar = new a();
        do {
            a(aVar);
            a(aVar.a);
        } while (aVar.a != ByteCode.T_LONG);
    }

    private void b() {
        a aVar = new a();
        a(aVar);
        a(aVar.a);
    }

    private void a(byte b) {
        int i = 0;
        int a;
        switch (b) {
            case (byte) 0:
                b(1);
                return;
            case (byte) 1:
                b(2);
                return;
            case (byte) 2:
                b(4);
                return;
            case (byte) 3:
                b(8);
                return;
            case (byte) 4:
                b(4);
                return;
            case (byte) 5:
                b(8);
                return;
            case (byte) 6:
                i = this.b.get();
                if (i < 0) {
                    i += 256;
                }
                b(i);
                return;
            case (byte) 7:
                b(this.b.getInt());
                return;
            case (byte) 8:
                a = a(0, 0, true);
                while (i < a * 2) {
                    b();
                    i++;
                }
                return;
            case (byte) 9:
                a = a(0, 0, true);
                while (i < a) {
                    b();
                    i++;
                }
                return;
            case (byte) 10:
                a();
                return;
            case (byte) 11:
            case (byte) 12:
                return;
            case (byte) 13:
                a aVar = new a();
                a(aVar);
                if (aVar.a != (byte) 0) {
                    throw new h("skipField with invalid type, type value: " + b + ", " + aVar.a);
                }
                b(a(0, 0, true));
                return;
            default:
                throw new h("invalid type.");
        }
    }

    public boolean a(boolean z, int i, boolean z2) {
        if (a((byte) 0, i, z2) != (byte) 0) {
            return true;
        }
        return false;
    }

    public byte a(byte b, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar);
            switch (aVar.a) {
                case (byte) 0:
                    return this.b.get();
                case (byte) 12:
                    return (byte) 0;
                default:
                    throw new h("type mismatch.");
            }
        } else if (!z) {
            return b;
        } else {
            throw new h("require field not exist.");
        }
    }

    public short a(short s, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar);
            switch (aVar.a) {
                case (byte) 0:
                    return (short) this.b.get();
                case (byte) 1:
                    return this.b.getShort();
                case (byte) 12:
                    return (short) 0;
                default:
                    throw new h("type mismatch.");
            }
        } else if (!z) {
            return s;
        } else {
            throw new h("require field not exist.");
        }
    }

    public int a(int i, int i2, boolean z) {
        if (a(i2)) {
            a aVar = new a();
            a(aVar);
            switch (aVar.a) {
                case (byte) 0:
                    return this.b.get();
                case (byte) 1:
                    return this.b.getShort();
                case (byte) 2:
                    return this.b.getInt();
                case (byte) 12:
                    return 0;
                default:
                    throw new h("type mismatch.");
            }
        } else if (!z) {
            return i;
        } else {
            throw new h("require field not exist.");
        }
    }

    public long a(long j, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar);
            switch (aVar.a) {
                case (byte) 0:
                    return (long) this.b.get();
                case (byte) 1:
                    return (long) this.b.getShort();
                case (byte) 2:
                    return (long) this.b.getInt();
                case (byte) 3:
                    return this.b.getLong();
                case (byte) 12:
                    return 0;
                default:
                    throw new h("type mismatch.");
            }
        } else if (!z) {
            return j;
        } else {
            throw new h("require field not exist.");
        }
    }

    public float a(float f, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar);
            switch (aVar.a) {
                case (byte) 4:
                    return this.b.getFloat();
                case (byte) 12:
                    return 0.0f;
                default:
                    throw new h("type mismatch.");
            }
        } else if (!z) {
            return f;
        } else {
            throw new h("require field not exist.");
        }
    }

    public double a(double d, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar);
            switch (aVar.a) {
                case (byte) 4:
                    return (double) this.b.getFloat();
                case (byte) 5:
                    return this.b.getDouble();
                case (byte) 12:
                    return 0.0d;
                default:
                    throw new h("type mismatch.");
            }
        } else if (!z) {
            return d;
        } else {
            throw new h("require field not exist.");
        }
    }

    public String a(int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar);
            int i2;
            byte[] bArr;
            switch (aVar.a) {
                case (byte) 6:
                    i2 = this.b.get();
                    if (i2 < 0) {
                        i2 += 256;
                    }
                    bArr = new byte[i2];
                    this.b.get(bArr);
                    try {
                        return new String(bArr, this.a);
                    } catch (UnsupportedEncodingException e) {
                        return new String(bArr);
                    }
                case (byte) 7:
                    i2 = this.b.getInt();
                    if (i2 > 104857600 || i2 < 0) {
                        throw new h("String too long: " + i2);
                    }
                    bArr = new byte[i2];
                    this.b.get(bArr);
                    try {
                        return new String(bArr, this.a);
                    } catch (UnsupportedEncodingException e2) {
                        return new String(bArr);
                    }
                default:
                    throw new h("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new h("require field not exist.");
        }
    }

    public <K, V> HashMap<K, V> a(Map<K, V> map, int i, boolean z) {
        return (HashMap) a(new HashMap(), map, i, z);
    }

    private <K, V> Map<K, V> a(Map<K, V> map, Map<K, V> map2, int i, boolean z) {
        if (map2 == null || map2.isEmpty()) {
            return new HashMap();
        }
        Entry entry = (Entry) map2.entrySet().iterator().next();
        Object key = entry.getKey();
        Object value = entry.getValue();
        if (a(i)) {
            a aVar = new a();
            a(aVar);
            switch (aVar.a) {
                case (byte) 8:
                    int a = a(0, 0, true);
                    if (a < 0) {
                        throw new h("size invalid: " + a);
                    }
                    for (int i2 = 0; i2 < a; i2++) {
                        map.put(a(key, 0, true), a(value, 1, true));
                    }
                    return map;
                default:
                    throw new h("type mismatch.");
            }
        } else if (!z) {
            return map;
        } else {
            throw new h("require field not exist.");
        }
    }

    public boolean[] a(boolean[] zArr, int i, boolean z) {
        boolean[] zArr2 = null;
        if (a(i)) {
            a aVar = new a();
            a(aVar);
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        zArr2 = new boolean[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            zArr2[i2] = a(zArr2[0], 0, true);
                        }
                        break;
                    }
                    throw new h("size invalid: " + a);
                default:
                    throw new h("type mismatch.");
            }
        } else if (z) {
            throw new h("require field not exist.");
        }
        return zArr2;
    }

    public byte[] a(byte[] bArr, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar);
            byte[] bArr2;
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a < 0) {
                        throw new h("size invalid: " + a);
                    }
                    bArr2 = new byte[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        bArr2[i2] = a(bArr2[0], 0, true);
                    }
                    return bArr2;
                case (byte) 13:
                    a aVar2 = new a();
                    a(aVar2);
                    if (aVar2.a != (byte) 0) {
                        throw new h("type mismatch, tag: " + i + ", type: " + aVar.a + ", " + aVar2.a);
                    }
                    int a2 = a(0, 0, true);
                    if (a2 < 0) {
                        throw new h("invalid size, tag: " + i + ", type: " + aVar.a + ", " + aVar2.a + ", size: " + a2);
                    }
                    bArr2 = new byte[a2];
                    this.b.get(bArr2);
                    return bArr2;
                default:
                    throw new h("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new h("require field not exist.");
        }
    }

    public short[] a(short[] sArr, int i, boolean z) {
        short[] sArr2 = null;
        if (a(i)) {
            a aVar = new a();
            a(aVar);
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        sArr2 = new short[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            sArr2[i2] = a(sArr2[0], 0, true);
                        }
                        break;
                    }
                    throw new h("size invalid: " + a);
                default:
                    throw new h("type mismatch.");
            }
        } else if (z) {
            throw new h("require field not exist.");
        }
        return sArr2;
    }

    public int[] a(int[] iArr, int i, boolean z) {
        int[] iArr2 = null;
        if (a(i)) {
            a aVar = new a();
            a(aVar);
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        iArr2 = new int[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            iArr2[i2] = a(iArr2[0], 0, true);
                        }
                        break;
                    }
                    throw new h("size invalid: " + a);
                default:
                    throw new h("type mismatch.");
            }
        } else if (z) {
            throw new h("require field not exist.");
        }
        return iArr2;
    }

    public long[] a(long[] jArr, int i, boolean z) {
        long[] jArr2 = null;
        if (a(i)) {
            a aVar = new a();
            a(aVar);
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        jArr2 = new long[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            jArr2[i2] = a(jArr2[0], 0, true);
                        }
                        break;
                    }
                    throw new h("size invalid: " + a);
                default:
                    throw new h("type mismatch.");
            }
        } else if (z) {
            throw new h("require field not exist.");
        }
        return jArr2;
    }

    public float[] a(float[] fArr, int i, boolean z) {
        float[] fArr2 = null;
        if (a(i)) {
            a aVar = new a();
            a(aVar);
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        fArr2 = new float[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            fArr2[i2] = a(fArr2[0], 0, true);
                        }
                        break;
                    }
                    throw new h("size invalid: " + a);
                default:
                    throw new h("type mismatch.");
            }
        } else if (z) {
            throw new h("require field not exist.");
        }
        return fArr2;
    }

    public double[] a(double[] dArr, int i, boolean z) {
        double[] dArr2 = null;
        if (a(i)) {
            a aVar = new a();
            a(aVar);
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        dArr2 = new double[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            dArr2[i2] = a(dArr2[0], 0, true);
                        }
                        break;
                    }
                    throw new h("size invalid: " + a);
                default:
                    throw new h("type mismatch.");
            }
        } else if (z) {
            throw new h("require field not exist.");
        }
        return dArr2;
    }

    public <T> T[] a(T[] tArr, int i, boolean z) {
        if (tArr != null && tArr.length != 0) {
            return b(tArr[0], i, z);
        }
        throw new h("unable to get type of key and value.");
    }

    public <T> List<T> a(List<T> list, int i, boolean z) {
        int i2 = 0;
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        Object[] b = b(list.get(0), i, z);
        if (b == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (i2 < b.length) {
            arrayList.add(b[i2]);
            i2++;
        }
        return arrayList;
    }

    private <T> T[] b(T t, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar);
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a < 0) {
                        throw new h("size invalid: " + a);
                    }
                    Object[] objArr = (Object[]) Array.newInstance(t.getClass(), a);
                    for (int i2 = 0; i2 < a; i2++) {
                        objArr[i2] = a((Object) t, 0, true);
                    }
                    return objArr;
                default:
                    throw new h("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new h("require field not exist.");
        }
    }

    public m a(m mVar, int i, boolean z) {
        m mVar2 = null;
        if (a(i)) {
            try {
                mVar2 = (m) mVar.getClass().newInstance();
                a aVar = new a();
                a(aVar);
                if (aVar.a != (byte) 10) {
                    throw new h("type mismatch.");
                }
                mVar2.a(this);
                a();
            } catch (Exception e) {
                throw new h(e.getMessage());
            }
        } else if (z) {
            throw new h("require field not exist.");
        }
        return mVar2;
    }

    public <T> Object a(T t, int i, boolean z) {
        if (t instanceof Byte) {
            return Byte.valueOf(a((byte) 0, i, z));
        }
        if (t instanceof Boolean) {
            return Boolean.valueOf(a(false, i, z));
        }
        if (t instanceof Short) {
            return Short.valueOf(a((short) 0, i, z));
        }
        if (t instanceof Integer) {
            return Integer.valueOf(a(0, i, z));
        }
        if (t instanceof Long) {
            return Long.valueOf(a(0, i, z));
        }
        if (t instanceof Float) {
            return Float.valueOf(a(0.0f, i, z));
        }
        if (t instanceof Double) {
            return Double.valueOf(a(0.0d, i, z));
        }
        if (t instanceof String) {
            return String.valueOf(a(i, z));
        }
        if (t instanceof Map) {
            return a((Map) t, i, z);
        }
        if (t instanceof List) {
            return a((List) t, i, z);
        }
        if (t instanceof m) {
            return a((m) t, i, z);
        }
        if (!t.getClass().isArray()) {
            throw new h("read object error: unsupport type.");
        } else if ((t instanceof byte[]) || (t instanceof Byte[])) {
            return a((byte[]) null, i, z);
        } else {
            if (t instanceof boolean[]) {
                return a((boolean[]) null, i, z);
            }
            if (t instanceof short[]) {
                return a((short[]) null, i, z);
            }
            if (t instanceof int[]) {
                return a((int[]) null, i, z);
            }
            if (t instanceof long[]) {
                return a((long[]) null, i, z);
            }
            if (t instanceof float[]) {
                return a((float[]) null, i, z);
            }
            if (t instanceof double[]) {
                return a((double[]) null, i, z);
            }
            return a((Object[]) t, i, z);
        }
    }

    public int a(String str) {
        this.a = str;
        return 0;
    }
}
