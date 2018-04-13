package com.tencent.bugly.proguard;

import cn.xiaochuankeji.tieba.background.AppController;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.mozilla.classfile.ByteCode;

public class l {
    protected String a;
    private ByteBuffer b;

    public l(int i) {
        this.a = AppController.kDataCacheCharset;
        this.b = ByteBuffer.allocate(i);
    }

    public l() {
        this(128);
    }

    public ByteBuffer a() {
        return this.b;
    }

    public byte[] b() {
        Object obj = new byte[this.b.position()];
        System.arraycopy(this.b.array(), 0, obj, 0, this.b.position());
        return obj;
    }

    public void a(int i) {
        if (this.b.remaining() < i) {
            ByteBuffer allocate = ByteBuffer.allocate((this.b.capacity() + i) * 2);
            allocate.put(this.b.array(), 0, this.b.position());
            this.b = allocate;
        }
    }

    public void a(byte b, int i) {
        if (i < 15) {
            this.b.put((byte) ((i << 4) | b));
        } else if (i < 256) {
            this.b.put((byte) (b | 240));
            this.b.put((byte) i);
        } else {
            throw new j("tag is too large: " + i);
        }
    }

    public void a(boolean z, int i) {
        b((byte) (z ? 1 : 0), i);
    }

    public void b(byte b, int i) {
        a(3);
        if (b == (byte) 0) {
            a((byte) 12, i);
            return;
        }
        a((byte) 0, i);
        this.b.put(b);
    }

    public void a(short s, int i) {
        a(4);
        if (s < (short) -128 || s > (short) 127) {
            a((byte) 1, i);
            this.b.putShort(s);
            return;
        }
        b((byte) s, i);
    }

    public void a(int i, int i2) {
        a(6);
        if (i < -32768 || i > 32767) {
            a((byte) 2, i2);
            this.b.putInt(i);
            return;
        }
        a((short) i, i2);
    }

    public void a(long j, int i) {
        a(10);
        if (j < -2147483648L || j > 2147483647L) {
            a((byte) 3, i);
            this.b.putLong(j);
            return;
        }
        a((int) j, i);
    }

    public void a(float f, int i) {
        a(6);
        a((byte) 4, i);
        this.b.putFloat(f);
    }

    public void a(double d, int i) {
        a(10);
        a((byte) 5, i);
        this.b.putDouble(d);
    }

    public void a(String str, int i) {
        byte[] bytes;
        try {
            bytes = str.getBytes(this.a);
        } catch (UnsupportedEncodingException e) {
            bytes = str.getBytes();
        }
        a(bytes.length + 10);
        if (bytes.length > 255) {
            a((byte) 7, i);
            this.b.putInt(bytes.length);
            this.b.put(bytes);
            return;
        }
        a((byte) 6, i);
        this.b.put((byte) bytes.length);
        this.b.put(bytes);
    }

    public <K, V> void a(Map<K, V> map, int i) {
        a(8);
        a((byte) 8, i);
        a(map == null ? 0 : map.size(), 0);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                a(entry.getKey(), 0);
                a(entry.getValue(), 1);
            }
        }
    }

    public void a(boolean[] zArr, int i) {
        a(8);
        a((byte) 9, i);
        a(zArr.length, 0);
        for (boolean a : zArr) {
            a(a, 0);
        }
    }

    public void a(byte[] bArr, int i) {
        a(bArr.length + 8);
        a((byte) 13, i);
        a((byte) 0, 0);
        a(bArr.length, 0);
        this.b.put(bArr);
    }

    public void a(short[] sArr, int i) {
        a(8);
        a((byte) 9, i);
        a(sArr.length, 0);
        for (short a : sArr) {
            a(a, 0);
        }
    }

    public void a(int[] iArr, int i) {
        a(8);
        a((byte) 9, i);
        a(iArr.length, 0);
        for (int a : iArr) {
            a(a, 0);
        }
    }

    public void a(long[] jArr, int i) {
        a(8);
        a((byte) 9, i);
        a(jArr.length, 0);
        for (long a : jArr) {
            a(a, 0);
        }
    }

    public void a(float[] fArr, int i) {
        a(8);
        a((byte) 9, i);
        a(fArr.length, 0);
        for (float a : fArr) {
            a(a, 0);
        }
    }

    public void a(double[] dArr, int i) {
        a(8);
        a((byte) 9, i);
        a(dArr.length, 0);
        for (double a : dArr) {
            a(a, 0);
        }
    }

    private void a(Object[] objArr, int i) {
        a(8);
        a((byte) 9, i);
        a(objArr.length, 0);
        for (Object a : objArr) {
            a(a, 0);
        }
    }

    public <T> void a(Collection<T> collection, int i) {
        a(8);
        a((byte) 9, i);
        a(collection == null ? 0 : collection.size(), 0);
        if (collection != null) {
            for (T a : collection) {
                a((Object) a, 0);
            }
        }
    }

    public void a(m mVar, int i) {
        a(2);
        a((byte) 10, i);
        mVar.a(this);
        a(2);
        a((byte) ByteCode.T_LONG, 0);
    }

    public void a(Object obj, int i) {
        if (obj instanceof Byte) {
            b(((Byte) obj).byteValue(), i);
        } else if (obj instanceof Boolean) {
            a(((Boolean) obj).booleanValue(), i);
        } else if (obj instanceof Short) {
            a(((Short) obj).shortValue(), i);
        } else if (obj instanceof Integer) {
            a(((Integer) obj).intValue(), i);
        } else if (obj instanceof Long) {
            a(((Long) obj).longValue(), i);
        } else if (obj instanceof Float) {
            a(((Float) obj).floatValue(), i);
        } else if (obj instanceof Double) {
            a(((Double) obj).doubleValue(), i);
        } else if (obj instanceof String) {
            a((String) obj, i);
        } else if (obj instanceof Map) {
            a((Map) obj, i);
        } else if (obj instanceof List) {
            a((List) obj, i);
        } else if (obj instanceof m) {
            a((m) obj, i);
        } else if (obj instanceof byte[]) {
            a((byte[]) obj, i);
        } else if (obj instanceof boolean[]) {
            a((boolean[]) obj, i);
        } else if (obj instanceof short[]) {
            a((short[]) obj, i);
        } else if (obj instanceof int[]) {
            a((int[]) obj, i);
        } else if (obj instanceof long[]) {
            a((long[]) obj, i);
        } else if (obj instanceof float[]) {
            a((float[]) obj, i);
        } else if (obj instanceof double[]) {
            a((double[]) obj, i);
        } else if (obj.getClass().isArray()) {
            a((Object[]) obj, i);
        } else if (obj instanceof Collection) {
            a((Collection) obj, i);
        } else {
            throw new j("write object error: unsupport type. " + obj.getClass());
        }
    }

    public int a(String str) {
        this.a = str;
        return 0;
    }
}
