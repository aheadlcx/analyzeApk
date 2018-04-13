package com.tencent.bugly.proguard;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class h {
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

    public h(StringBuilder stringBuilder, int i) {
        this.a = stringBuilder;
        this.b = i;
    }

    public final h a(boolean z, String str) {
        a(str);
        this.a.append(z ? 'T' : 'F').append('\n');
        return this;
    }

    public final h a(byte b, String str) {
        a(str);
        this.a.append(b).append('\n');
        return this;
    }

    public final h a(short s, String str) {
        a(str);
        this.a.append(s).append('\n');
        return this;
    }

    public final h a(int i, String str) {
        a(str);
        this.a.append(i).append('\n');
        return this;
    }

    public final h a(long j, String str) {
        a(str);
        this.a.append(j).append('\n');
        return this;
    }

    public final h a(String str, String str2) {
        a(str2);
        if (str == null) {
            this.a.append("null\n");
        } else {
            this.a.append(str).append('\n');
        }
        return this;
    }

    public final h a(byte[] bArr, String str) {
        a(str);
        if (bArr == null) {
            this.a.append("null\n");
        } else if (bArr.length == 0) {
            this.a.append(bArr.length).append(", []\n");
        } else {
            this.a.append(bArr.length).append(", [\n");
            h hVar = new h(this.a, this.b + 1);
            for (byte b : bArr) {
                hVar.a(null);
                hVar.a.append(b).append('\n');
            }
            a(null);
            this.a.append(']').append('\n');
        }
        return this;
    }

    public final <K, V> h a(Map<K, V> map, String str) {
        a(str);
        if (map == null) {
            this.a.append("null\n");
        } else if (map.isEmpty()) {
            this.a.append(map.size()).append(", {}\n");
        } else {
            this.a.append(map.size()).append(", {\n");
            h hVar = new h(this.a, this.b + 1);
            h hVar2 = new h(this.a, this.b + 2);
            for (Entry entry : map.entrySet()) {
                hVar.a(null);
                hVar.a.append('(').append('\n');
                hVar2.a(entry.getKey(), null);
                hVar2.a(entry.getValue(), null);
                hVar.a(null);
                hVar.a.append(')').append('\n');
            }
            a(null);
            this.a.append('}').append('\n');
        }
        return this;
    }

    private <T> h a(T[] tArr, String str) {
        a(str);
        if (tArr == null) {
            this.a.append("null\n");
        } else if (tArr.length == 0) {
            this.a.append(tArr.length).append(", []\n");
        } else {
            this.a.append(tArr.length).append(", [\n");
            h hVar = new h(this.a, this.b + 1);
            for (Object a : tArr) {
                hVar.a(a, null);
            }
            a(null);
            this.a.append(']').append('\n');
        }
        return this;
    }

    private <T> h a(T t, String str) {
        int i = 0;
        if (t == null) {
            this.a.append("null\n");
        } else if (t instanceof Byte) {
            byte byteValue = ((Byte) t).byteValue();
            a(str);
            this.a.append(byteValue).append('\n');
        } else if (t instanceof Boolean) {
            boolean booleanValue = ((Boolean) t).booleanValue();
            a(str);
            this.a.append(booleanValue ? 'T' : 'F').append('\n');
        } else if (t instanceof Short) {
            short shortValue = ((Short) t).shortValue();
            a(str);
            this.a.append(shortValue).append('\n');
        } else if (t instanceof Integer) {
            i = ((Integer) t).intValue();
            a(str);
            this.a.append(i).append('\n');
        } else if (t instanceof Long) {
            long longValue = ((Long) t).longValue();
            a(str);
            this.a.append(longValue).append('\n');
        } else if (t instanceof Float) {
            float floatValue = ((Float) t).floatValue();
            a(str);
            this.a.append(floatValue).append('\n');
        } else if (t instanceof Double) {
            double doubleValue = ((Double) t).doubleValue();
            a(str);
            this.a.append(doubleValue).append('\n');
        } else if (t instanceof String) {
            a((String) t, str);
        } else if (t instanceof Map) {
            a((Map) t, str);
        } else if (t instanceof List) {
            List list = (List) t;
            if (list == null) {
                a(str);
                this.a.append("null\t");
            } else {
                a(list.toArray(), str);
            }
        } else if (t instanceof k) {
            a((k) t, str);
        } else if (t instanceof byte[]) {
            a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            a((boolean[]) t, str);
        } else if (t instanceof short[]) {
            short[] sArr = (short[]) t;
            a(str);
            if (sArr == null) {
                this.a.append("null\n");
            } else if (sArr.length == 0) {
                this.a.append(sArr.length).append(", []\n");
            } else {
                this.a.append(sArr.length).append(", [\n");
                r1 = new h(this.a, this.b + 1);
                r2 = sArr.length;
                while (i < r2) {
                    short s = sArr[i];
                    r1.a(null);
                    r1.a.append(s).append('\n');
                    i++;
                }
                a(null);
                this.a.append(']').append('\n');
            }
        } else if (t instanceof int[]) {
            int[] iArr = (int[]) t;
            a(str);
            if (iArr == null) {
                this.a.append("null\n");
            } else if (iArr.length == 0) {
                this.a.append(iArr.length).append(", []\n");
            } else {
                this.a.append(iArr.length).append(", [\n");
                r1 = new h(this.a, this.b + 1);
                r2 = iArr.length;
                while (i < r2) {
                    int i2 = iArr[i];
                    r1.a(null);
                    r1.a.append(i2).append('\n');
                    i++;
                }
                a(null);
                this.a.append(']').append('\n');
            }
        } else if (t instanceof long[]) {
            long[] jArr = (long[]) t;
            a(str);
            if (jArr == null) {
                this.a.append("null\n");
            } else if (jArr.length == 0) {
                this.a.append(jArr.length).append(", []\n");
            } else {
                this.a.append(jArr.length).append(", [\n");
                r1 = new h(this.a, this.b + 1);
                r2 = jArr.length;
                while (i < r2) {
                    long j = jArr[i];
                    r1.a(null);
                    r1.a.append(j).append('\n');
                    i++;
                }
                a(null);
                this.a.append(']').append('\n');
            }
        } else if (t instanceof float[]) {
            float[] fArr = (float[]) t;
            a(str);
            if (fArr == null) {
                this.a.append("null\n");
            } else if (fArr.length == 0) {
                this.a.append(fArr.length).append(", []\n");
            } else {
                this.a.append(fArr.length).append(", [\n");
                r1 = new h(this.a, this.b + 1);
                r2 = fArr.length;
                while (i < r2) {
                    float f = fArr[i];
                    r1.a(null);
                    r1.a.append(f).append('\n');
                    i++;
                }
                a(null);
                this.a.append(']').append('\n');
            }
        } else if (t instanceof double[]) {
            double[] dArr = (double[]) t;
            a(str);
            if (dArr == null) {
                this.a.append("null\n");
            } else if (dArr.length == 0) {
                this.a.append(dArr.length).append(", []\n");
            } else {
                this.a.append(dArr.length).append(", [\n");
                r1 = new h(this.a, this.b + 1);
                r2 = dArr.length;
                while (i < r2) {
                    double d = dArr[i];
                    r1.a(null);
                    r1.a.append(d).append('\n');
                    i++;
                }
                a(null);
                this.a.append(']').append('\n');
            }
        } else if (t.getClass().isArray()) {
            a((Object[]) t, str);
        } else {
            throw new b("write object error: unsupport type.");
        }
        return this;
    }

    public final h a(k kVar, String str) {
        a(str);
        this.a.append('{').append('\n');
        if (kVar == null) {
            this.a.append('\t').append("null");
        } else {
            kVar.a(this.a, this.b + 1);
        }
        a(null);
        this.a.append('}').append('\n');
        return this;
    }
}
