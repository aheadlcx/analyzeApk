package com.tencent.bugly.proguard;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class i {
    private StringBuilder a;
    private int b = 0;

    private void a(String str) {
        for (int i = 0; i < this.b; i++) {
            this.a.append('\t');
        }
        if (str != null) {
            this.a.append(str).append(": ");
        }
    }

    public i(StringBuilder stringBuilder, int i) {
        this.a = stringBuilder;
        this.b = i;
    }

    public i a(boolean z, String str) {
        a(str);
        this.a.append(z ? 'T' : 'F').append('\n');
        return this;
    }

    public i a(byte b, String str) {
        a(str);
        this.a.append(b).append('\n');
        return this;
    }

    public i a(char c, String str) {
        a(str);
        this.a.append(c).append('\n');
        return this;
    }

    public i a(short s, String str) {
        a(str);
        this.a.append(s).append('\n');
        return this;
    }

    public i a(int i, String str) {
        a(str);
        this.a.append(i).append('\n');
        return this;
    }

    public i a(long j, String str) {
        a(str);
        this.a.append(j).append('\n');
        return this;
    }

    public i a(float f, String str) {
        a(str);
        this.a.append(f).append('\n');
        return this;
    }

    public i a(double d, String str) {
        a(str);
        this.a.append(d).append('\n');
        return this;
    }

    public i a(String str, String str2) {
        a(str2);
        if (str == null) {
            this.a.append("null").append('\n');
        } else {
            this.a.append(str).append('\n');
        }
        return this;
    }

    public i a(byte[] bArr, String str) {
        a(str);
        if (bArr == null) {
            this.a.append("null").append('\n');
        } else if (bArr.length == 0) {
            this.a.append(bArr.length).append(", []").append('\n');
        } else {
            this.a.append(bArr.length).append(", [").append('\n');
            i iVar = new i(this.a, this.b + 1);
            for (byte a : bArr) {
                iVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public i a(short[] sArr, String str) {
        a(str);
        if (sArr == null) {
            this.a.append("null").append('\n');
        } else if (sArr.length == 0) {
            this.a.append(sArr.length).append(", []").append('\n');
        } else {
            this.a.append(sArr.length).append(", [").append('\n');
            i iVar = new i(this.a, this.b + 1);
            for (short a : sArr) {
                iVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public i a(int[] iArr, String str) {
        a(str);
        if (iArr == null) {
            this.a.append("null").append('\n');
        } else if (iArr.length == 0) {
            this.a.append(iArr.length).append(", []").append('\n');
        } else {
            this.a.append(iArr.length).append(", [").append('\n');
            i iVar = new i(this.a, this.b + 1);
            for (int a : iArr) {
                iVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public i a(long[] jArr, String str) {
        a(str);
        if (jArr == null) {
            this.a.append("null").append('\n');
        } else if (jArr.length == 0) {
            this.a.append(jArr.length).append(", []").append('\n');
        } else {
            this.a.append(jArr.length).append(", [").append('\n');
            i iVar = new i(this.a, this.b + 1);
            for (long a : jArr) {
                iVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public i a(float[] fArr, String str) {
        a(str);
        if (fArr == null) {
            this.a.append("null").append('\n');
        } else if (fArr.length == 0) {
            this.a.append(fArr.length).append(", []").append('\n');
        } else {
            this.a.append(fArr.length).append(", [").append('\n');
            i iVar = new i(this.a, this.b + 1);
            for (float a : fArr) {
                iVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public i a(double[] dArr, String str) {
        a(str);
        if (dArr == null) {
            this.a.append("null").append('\n');
        } else if (dArr.length == 0) {
            this.a.append(dArr.length).append(", []").append('\n');
        } else {
            this.a.append(dArr.length).append(", [").append('\n');
            i iVar = new i(this.a, this.b + 1);
            for (double a : dArr) {
                iVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public <K, V> i a(Map<K, V> map, String str) {
        a(str);
        if (map == null) {
            this.a.append("null").append('\n');
        } else if (map.isEmpty()) {
            this.a.append(map.size()).append(", {}").append('\n');
        } else {
            this.a.append(map.size()).append(", {").append('\n');
            i iVar = new i(this.a, this.b + 1);
            i iVar2 = new i(this.a, this.b + 2);
            for (Entry entry : map.entrySet()) {
                iVar.a('(', null);
                iVar2.a(entry.getKey(), null);
                iVar2.a(entry.getValue(), null);
                iVar.a(')', null);
            }
            a('}', null);
        }
        return this;
    }

    public <T> i a(T[] tArr, String str) {
        a(str);
        if (tArr == null) {
            this.a.append("null").append('\n');
        } else if (tArr.length == 0) {
            this.a.append(tArr.length).append(", []").append('\n');
        } else {
            this.a.append(tArr.length).append(", [").append('\n');
            i iVar = new i(this.a, this.b + 1);
            for (Object a : tArr) {
                iVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public <T> i a(Collection<T> collection, String str) {
        if (collection != null) {
            return a(collection.toArray(), str);
        }
        a(str);
        this.a.append("null").append('\t');
        return this;
    }

    public <T> i a(T t, String str) {
        if (t == null) {
            this.a.append("null").append('\n');
        } else if (t instanceof Byte) {
            a(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            a(((Boolean) t).booleanValue(), str);
        } else if (t instanceof Short) {
            a(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            a(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            a(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            a(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            a(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            a((String) t, str);
        } else if (t instanceof Map) {
            a((Map) t, str);
        } else if (t instanceof List) {
            a((List) t, str);
        } else if (t instanceof m) {
            a((m) t, str);
        } else if (t instanceof byte[]) {
            a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            a((Object) (boolean[]) t, str);
        } else if (t instanceof short[]) {
            a((short[]) t, str);
        } else if (t instanceof int[]) {
            a((int[]) t, str);
        } else if (t instanceof long[]) {
            a((long[]) t, str);
        } else if (t instanceof float[]) {
            a((float[]) t, str);
        } else if (t instanceof double[]) {
            a((double[]) t, str);
        } else if (t.getClass().isArray()) {
            a((Object[]) t, str);
        } else {
            throw new j("write object error: unsupport type.");
        }
        return this;
    }

    public i a(m mVar, String str) {
        a('{', str);
        if (mVar == null) {
            this.a.append('\t').append("null");
        } else {
            mVar.a(this.a, this.b + 1);
        }
        a('}', null);
        return this;
    }
}
