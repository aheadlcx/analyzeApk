package com.alibaba.fastjson.asm;

public class Type {
    public static final Type BOOLEAN_TYPE = new Type(1, null, 1509950721, 1);
    public static final Type BYTE_TYPE = new Type(3, null, 1107297537, 1);
    public static final Type CHAR_TYPE = new Type(2, null, 1124075009, 1);
    public static final Type DOUBLE_TYPE = new Type(8, null, 1141048066, 1);
    public static final Type FLOAT_TYPE = new Type(6, null, 1174536705, 1);
    public static final Type INT_TYPE = new Type(5, null, 1224736769, 1);
    public static final Type LONG_TYPE = new Type(7, null, 1241579778, 1);
    public static final Type SHORT_TYPE = new Type(4, null, 1392510721, 1);
    public static final Type VOID_TYPE = new Type(0, null, 1443168256, 1);
    private final char[] buf;
    private final int len;
    private final int off;
    protected final int sort;

    private Type(int i, char[] cArr, int i2, int i3) {
        this.sort = i;
        this.buf = cArr;
        this.off = i2;
        this.len = i3;
    }

    public static Type getType(String str) {
        return getType(str.toCharArray(), 0);
    }

    public static int getArgumentsAndReturnSizes(String str) {
        int i;
        char charAt;
        int i2 = 1;
        int i3 = 1;
        int i4 = 1;
        while (true) {
            i = i3 + 1;
            charAt = str.charAt(i3);
            if (charAt == ')') {
                break;
            } else if (charAt == 'L') {
                i3 = i;
                while (true) {
                    i = i3 + 1;
                    if (str.charAt(i3) == ';') {
                        break;
                    }
                    i3 = i;
                }
                i4++;
                i3 = i;
            } else if (charAt == 'D' || charAt == 'J') {
                i4 += 2;
                i3 = i;
            } else {
                i4++;
                i3 = i;
            }
        }
        charAt = str.charAt(i);
        i4 <<= 2;
        if (charAt == 'V') {
            i2 = 0;
        } else if (charAt == 'D' || charAt == 'J') {
            i2 = 2;
        }
        return i4 | i2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.alibaba.fastjson.asm.Type getType(char[] r4, int r5) {
        /*
        r3 = 59;
        r0 = 1;
        r1 = r4[r5];
        switch(r1) {
            case 66: goto L_0x001a;
            case 67: goto L_0x0017;
            case 68: goto L_0x0029;
            case 70: goto L_0x0023;
            case 73: goto L_0x0020;
            case 74: goto L_0x0026;
            case 83: goto L_0x001d;
            case 86: goto L_0x0011;
            case 90: goto L_0x0014;
            case 91: goto L_0x002c;
            default: goto L_0x0008;
        };
    L_0x0008:
        r1 = r5 + r0;
        r1 = r4[r1];
        if (r1 == r3) goto L_0x0055;
    L_0x000e:
        r0 = r0 + 1;
        goto L_0x0008;
    L_0x0011:
        r0 = VOID_TYPE;
    L_0x0013:
        return r0;
    L_0x0014:
        r0 = BOOLEAN_TYPE;
        goto L_0x0013;
    L_0x0017:
        r0 = CHAR_TYPE;
        goto L_0x0013;
    L_0x001a:
        r0 = BYTE_TYPE;
        goto L_0x0013;
    L_0x001d:
        r0 = SHORT_TYPE;
        goto L_0x0013;
    L_0x0020:
        r0 = INT_TYPE;
        goto L_0x0013;
    L_0x0023:
        r0 = FLOAT_TYPE;
        goto L_0x0013;
    L_0x0026:
        r0 = LONG_TYPE;
        goto L_0x0013;
    L_0x0029:
        r0 = DOUBLE_TYPE;
        goto L_0x0013;
    L_0x002c:
        r1 = r5 + r0;
        r1 = r4[r1];
        r2 = 91;
        if (r1 != r2) goto L_0x0037;
    L_0x0034:
        r0 = r0 + 1;
        goto L_0x002c;
    L_0x0037:
        r1 = r5 + r0;
        r1 = r4[r1];
        r2 = 76;
        if (r1 != r2) goto L_0x004a;
    L_0x003f:
        r0 = r0 + 1;
    L_0x0041:
        r1 = r5 + r0;
        r1 = r4[r1];
        if (r1 == r3) goto L_0x004a;
    L_0x0047:
        r0 = r0 + 1;
        goto L_0x0041;
    L_0x004a:
        r1 = new com.alibaba.fastjson.asm.Type;
        r2 = 9;
        r0 = r0 + 1;
        r1.<init>(r2, r4, r5, r0);
        r0 = r1;
        goto L_0x0013;
    L_0x0055:
        r1 = new com.alibaba.fastjson.asm.Type;
        r2 = 10;
        r3 = r5 + 1;
        r0 = r0 + -1;
        r1.<init>(r2, r4, r3, r0);
        r0 = r1;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.asm.Type.getType(char[], int):com.alibaba.fastjson.asm.Type");
    }

    public String getInternalName() {
        return new String(this.buf, this.off, this.len);
    }

    String getDescriptor() {
        return new String(this.buf, this.off, this.len);
    }
}
