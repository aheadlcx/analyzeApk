package org.mozilla.javascript;

import android.support.media.ExifInterface;

final class NativeMath extends IdScriptableObject {
    private static final int Id_E = 20;
    private static final int Id_LN10 = 22;
    private static final int Id_LN2 = 23;
    private static final int Id_LOG10E = 25;
    private static final int Id_LOG2E = 24;
    private static final int Id_PI = 21;
    private static final int Id_SQRT1_2 = 26;
    private static final int Id_SQRT2 = 27;
    private static final int Id_abs = 2;
    private static final int Id_acos = 3;
    private static final int Id_asin = 4;
    private static final int Id_atan = 5;
    private static final int Id_atan2 = 6;
    private static final int Id_ceil = 7;
    private static final int Id_cos = 8;
    private static final int Id_exp = 9;
    private static final int Id_floor = 10;
    private static final int Id_log = 11;
    private static final int Id_max = 12;
    private static final int Id_min = 13;
    private static final int Id_pow = 14;
    private static final int Id_random = 15;
    private static final int Id_round = 16;
    private static final int Id_sin = 17;
    private static final int Id_sqrt = 18;
    private static final int Id_tan = 19;
    private static final int Id_toSource = 1;
    private static final int LAST_METHOD_ID = 19;
    private static final Object MATH_TAG = "Math";
    private static final int MAX_ID = 27;
    static final long serialVersionUID = -8838847185801131569L;

    static void init(Scriptable scriptable, boolean z) {
        NativeMath nativeMath = new NativeMath();
        nativeMath.activatePrototypeMap(27);
        nativeMath.setPrototype(getObjectPrototype(scriptable));
        nativeMath.setParentScope(scriptable);
        if (z) {
            nativeMath.sealObject();
        }
        ScriptableObject.defineProperty(scriptable, "Math", nativeMath, 2);
    }

    private NativeMath() {
    }

    public String getClassName() {
        return "Math";
    }

    protected void initPrototypeId(int i) {
        int i2 = 0;
        if (i <= 19) {
            String str;
            switch (i) {
                case 1:
                    str = "toSource";
                    break;
                case 2:
                    str = "abs";
                    i2 = 1;
                    break;
                case 3:
                    str = "acos";
                    i2 = 1;
                    break;
                case 4:
                    str = "asin";
                    i2 = 1;
                    break;
                case 5:
                    str = "atan";
                    i2 = 1;
                    break;
                case 6:
                    str = "atan2";
                    i2 = 2;
                    break;
                case 7:
                    str = "ceil";
                    i2 = 1;
                    break;
                case 8:
                    str = "cos";
                    i2 = 1;
                    break;
                case 9:
                    str = "exp";
                    i2 = 1;
                    break;
                case 10:
                    str = "floor";
                    i2 = 1;
                    break;
                case 11:
                    str = "log";
                    i2 = 1;
                    break;
                case 12:
                    str = "max";
                    i2 = 2;
                    break;
                case 13:
                    str = "min";
                    i2 = 2;
                    break;
                case 14:
                    str = "pow";
                    i2 = 2;
                    break;
                case 15:
                    str = "random";
                    break;
                case 16:
                    str = "round";
                    i2 = 1;
                    break;
                case 17:
                    str = "sin";
                    i2 = 1;
                    break;
                case 18:
                    str = "sqrt";
                    i2 = 1;
                    break;
                case 19:
                    str = "tan";
                    i2 = 1;
                    break;
                default:
                    throw new IllegalStateException(String.valueOf(i));
            }
            initPrototypeMethod(MATH_TAG, i, str, i2);
            return;
        }
        double d;
        String str2;
        switch (i) {
            case 20:
                d = 2.718281828459045d;
                str2 = ExifInterface.LONGITUDE_EAST;
                break;
            case 21:
                d = 3.141592653589793d;
                str2 = "PI";
                break;
            case 22:
                d = 2.302585092994046d;
                str2 = "LN10";
                break;
            case 23:
                d = 0.6931471805599453d;
                str2 = "LN2";
                break;
            case 24:
                d = 1.4426950408889634d;
                str2 = "LOG2E";
                break;
            case 25:
                d = 0.4342944819032518d;
                str2 = "LOG10E";
                break;
            case 26:
                d = 0.7071067811865476d;
                str2 = "SQRT1_2";
                break;
            case 27:
                d = 1.4142135623730951d;
                str2 = "SQRT2";
                break;
            default:
                throw new IllegalStateException(String.valueOf(i));
        }
        initPrototypeValue(i, str2, ScriptRuntime.wrapNumber(d), 7);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double d = Double.NaN;
        double d2 = 0.0d;
        if (!idFunctionObject.hasTag(MATH_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        double toNumber;
        switch (methodId) {
            case 1:
                return "Math";
            case 2:
                d = ScriptRuntime.toNumber(objArr, 0);
                if (d != 0.0d) {
                    d2 = d < 0.0d ? -d : d;
                }
                d = d2;
                break;
            case 3:
            case 4:
                d2 = ScriptRuntime.toNumber(objArr, 0);
                if (d2 == d2 && -1.0d <= d2 && d2 <= 1.0d) {
                    d = methodId == 3 ? Math.acos(d2) : Math.asin(d2);
                    break;
                }
            case 5:
                d = Math.atan(ScriptRuntime.toNumber(objArr, 0));
                break;
            case 6:
                d = Math.atan2(ScriptRuntime.toNumber(objArr, 0), ScriptRuntime.toNumber(objArr, 1));
                break;
            case 7:
                d = Math.ceil(ScriptRuntime.toNumber(objArr, 0));
                break;
            case 8:
                d2 = ScriptRuntime.toNumber(objArr, 0);
                d2 = (d2 == Double.POSITIVE_INFINITY || d2 == Double.NEGATIVE_INFINITY) ? Double.NaN : Math.cos(d2);
                d = d2;
                break;
            case 9:
                d = ScriptRuntime.toNumber(objArr, 0);
                if (d == Double.POSITIVE_INFINITY) {
                    d2 = d;
                } else if (d != Double.NEGATIVE_INFINITY) {
                    d2 = Math.exp(d);
                }
                d = d2;
                break;
            case 10:
                d = Math.floor(ScriptRuntime.toNumber(objArr, 0));
                break;
            case 11:
                toNumber = ScriptRuntime.toNumber(objArr, 0);
                if (toNumber >= 0.0d) {
                    d = Math.log(toNumber);
                    break;
                }
                break;
            case 12:
            case 13:
                d = methodId == 12 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
                for (int i = 0; i != objArr.length; i++) {
                    toNumber = ScriptRuntime.toNumber(objArr[i]);
                    if (toNumber != toNumber) {
                        d = toNumber;
                        break;
                    }
                    if (methodId == 12) {
                        d = Math.max(d, toNumber);
                    } else {
                        d = Math.min(d, toNumber);
                    }
                }
                break;
            case 14:
                d = js_pow(ScriptRuntime.toNumber(objArr, 0), ScriptRuntime.toNumber(objArr, 1));
                break;
            case 15:
                d = Math.random();
                break;
            case 16:
                d = ScriptRuntime.toNumber(objArr, 0);
                if (!(d != d || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY)) {
                    long round = Math.round(d);
                    if (round != 0) {
                        d2 = (double) round;
                    } else if (d < 0.0d) {
                        d2 = ScriptRuntime.negativeZero;
                    } else if (d == 0.0d) {
                        d2 = d;
                    }
                    d = d2;
                    break;
                }
            case 17:
                d2 = ScriptRuntime.toNumber(objArr, 0);
                if (!(d2 == Double.POSITIVE_INFINITY || d2 == Double.NEGATIVE_INFINITY)) {
                    d = Math.sin(d2);
                    break;
                }
            case 18:
                d = Math.sqrt(ScriptRuntime.toNumber(objArr, 0));
                break;
            case 19:
                d = Math.tan(ScriptRuntime.toNumber(objArr, 0));
                break;
            default:
                throw new IllegalStateException(String.valueOf(methodId));
        }
        return ScriptRuntime.wrapNumber(d);
    }

    private double js_pow(double d, double d2) {
        if (d2 != d2) {
            return d2;
        }
        if (d2 == 0.0d) {
            return 1.0d;
        }
        long j;
        if (d != 0.0d) {
            double pow = Math.pow(d, d2);
            if (pow == pow) {
                return pow;
            }
            if (d2 == Double.POSITIVE_INFINITY) {
                if (d < -1.0d || 1.0d < d) {
                    return Double.POSITIVE_INFINITY;
                }
                if (-1.0d >= d || d >= 1.0d) {
                    return pow;
                }
                return 0.0d;
            } else if (d2 == Double.NEGATIVE_INFINITY) {
                if (d < -1.0d || 1.0d < d) {
                    return 0.0d;
                }
                if (-1.0d >= d || d >= 1.0d) {
                    return pow;
                }
                return Double.POSITIVE_INFINITY;
            } else if (d == Double.POSITIVE_INFINITY) {
                return d2 > 0.0d ? Double.POSITIVE_INFINITY : 0.0d;
            } else {
                if (d != Double.NEGATIVE_INFINITY) {
                    return pow;
                }
                j = (long) d2;
                return (((double) j) != d2 || (j & 1) == 0) ? d2 > 0.0d ? Double.POSITIVE_INFINITY : 0.0d : d2 > 0.0d ? Double.NEGATIVE_INFINITY : -0.0d;
            }
        } else if (1.0d / d > 0.0d) {
            return d2 > 0.0d ? 0.0d : Double.POSITIVE_INFINITY;
        } else {
            j = (long) d2;
            return (((double) j) != d2 || (j & 1) == 0) ? d2 > 0.0d ? 0.0d : Double.POSITIVE_INFINITY : d2 > 0.0d ? -0.0d : Double.NEGATIVE_INFINITY;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int findPrototypeId(java.lang.String r9) {
        /*
        r8 = this;
        r6 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        r5 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        r1 = 0;
        r0 = 2;
        r3 = 1;
        r2 = 0;
        r4 = r9.length();
        switch(r4) {
            case 1: goto L_0x001c;
            case 2: goto L_0x0027;
            case 3: goto L_0x003a;
            case 4: goto L_0x00f4;
            case 5: goto L_0x0136;
            case 6: goto L_0x0171;
            case 7: goto L_0x0191;
            case 8: goto L_0x019b;
            default: goto L_0x000f;
        };
    L_0x000f:
        r0 = r1;
    L_0x0010:
        if (r2 == 0) goto L_0x001b;
    L_0x0012:
        if (r2 == r9) goto L_0x001b;
    L_0x0014:
        r2 = r2.equals(r9);
        if (r2 != 0) goto L_0x001b;
    L_0x001a:
        r0 = r1;
    L_0x001b:
        return r0;
    L_0x001c:
        r0 = r9.charAt(r1);
        r3 = 69;
        if (r0 != r3) goto L_0x000f;
    L_0x0024:
        r0 = 20;
        goto L_0x001b;
    L_0x0027:
        r0 = r9.charAt(r1);
        r4 = 80;
        if (r0 != r4) goto L_0x000f;
    L_0x002f:
        r0 = r9.charAt(r3);
        r3 = 73;
        if (r0 != r3) goto L_0x000f;
    L_0x0037:
        r0 = 21;
        goto L_0x001b;
    L_0x003a:
        r4 = r9.charAt(r1);
        switch(r4) {
            case 76: goto L_0x0043;
            case 97: goto L_0x0056;
            case 99: goto L_0x0067;
            case 101: goto L_0x0078;
            case 108: goto L_0x008b;
            case 109: goto L_0x009c;
            case 112: goto L_0x00be;
            case 115: goto L_0x00d0;
            case 116: goto L_0x00e2;
            default: goto L_0x0041;
        };
    L_0x0041:
        r0 = r1;
        goto L_0x0010;
    L_0x0043:
        r0 = r9.charAt(r0);
        r4 = 50;
        if (r0 != r4) goto L_0x000f;
    L_0x004b:
        r0 = r9.charAt(r3);
        r3 = 78;
        if (r0 != r3) goto L_0x000f;
    L_0x0053:
        r0 = 23;
        goto L_0x001b;
    L_0x0056:
        r4 = r9.charAt(r0);
        r5 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r4 != r5) goto L_0x000f;
    L_0x005e:
        r3 = r9.charAt(r3);
        r4 = 98;
        if (r3 != r4) goto L_0x000f;
    L_0x0066:
        goto L_0x001b;
    L_0x0067:
        r0 = r9.charAt(r0);
        r4 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r0 != r4) goto L_0x000f;
    L_0x006f:
        r0 = r9.charAt(r3);
        if (r0 != r6) goto L_0x000f;
    L_0x0075:
        r0 = 8;
        goto L_0x001b;
    L_0x0078:
        r0 = r9.charAt(r0);
        r4 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        if (r0 != r4) goto L_0x000f;
    L_0x0080:
        r0 = r9.charAt(r3);
        r3 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        if (r0 != r3) goto L_0x000f;
    L_0x0088:
        r0 = 9;
        goto L_0x001b;
    L_0x008b:
        r0 = r9.charAt(r0);
        r4 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        if (r0 != r4) goto L_0x000f;
    L_0x0093:
        r0 = r9.charAt(r3);
        if (r0 != r6) goto L_0x000f;
    L_0x0099:
        r0 = 11;
        goto L_0x001b;
    L_0x009c:
        r0 = r9.charAt(r0);
        if (r0 != r5) goto L_0x00ae;
    L_0x00a2:
        r0 = r9.charAt(r3);
        r3 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        if (r0 != r3) goto L_0x000f;
    L_0x00aa:
        r0 = 13;
        goto L_0x001b;
    L_0x00ae:
        r4 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        if (r0 != r4) goto L_0x000f;
    L_0x00b2:
        r0 = r9.charAt(r3);
        r3 = 97;
        if (r0 != r3) goto L_0x000f;
    L_0x00ba:
        r0 = 12;
        goto L_0x001b;
    L_0x00be:
        r0 = r9.charAt(r0);
        r4 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        if (r0 != r4) goto L_0x000f;
    L_0x00c6:
        r0 = r9.charAt(r3);
        if (r0 != r6) goto L_0x000f;
    L_0x00cc:
        r0 = 14;
        goto L_0x001b;
    L_0x00d0:
        r0 = r9.charAt(r0);
        if (r0 != r5) goto L_0x000f;
    L_0x00d6:
        r0 = r9.charAt(r3);
        r3 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        if (r0 != r3) goto L_0x000f;
    L_0x00de:
        r0 = 17;
        goto L_0x001b;
    L_0x00e2:
        r0 = r9.charAt(r0);
        if (r0 != r5) goto L_0x000f;
    L_0x00e8:
        r0 = r9.charAt(r3);
        r3 = 97;
        if (r0 != r3) goto L_0x000f;
    L_0x00f0:
        r0 = 19;
        goto L_0x001b;
    L_0x00f4:
        r0 = r9.charAt(r3);
        switch(r0) {
            case 78: goto L_0x00fe;
            case 99: goto L_0x0108;
            case 101: goto L_0x0111;
            case 113: goto L_0x011a;
            case 115: goto L_0x0124;
            case 116: goto L_0x012d;
            default: goto L_0x00fb;
        };
    L_0x00fb:
        r0 = r1;
        goto L_0x0010;
    L_0x00fe:
        r0 = "LN10";
        r2 = 22;
        r7 = r0;
        r0 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0108:
        r0 = "acos";
        r2 = 3;
        r7 = r0;
        r0 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0111:
        r0 = "ceil";
        r2 = 7;
        r7 = r0;
        r0 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x011a:
        r0 = "sqrt";
        r2 = 18;
        r7 = r0;
        r0 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0124:
        r0 = "asin";
        r2 = 4;
        r7 = r0;
        r0 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x012d:
        r0 = "atan";
        r2 = 5;
        r7 = r0;
        r0 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0136:
        r0 = r9.charAt(r1);
        switch(r0) {
            case 76: goto L_0x0140;
            case 83: goto L_0x014a;
            case 97: goto L_0x0154;
            case 102: goto L_0x015d;
            case 114: goto L_0x0167;
            default: goto L_0x013d;
        };
    L_0x013d:
        r0 = r1;
        goto L_0x0010;
    L_0x0140:
        r0 = "LOG2E";
        r2 = 24;
        r7 = r0;
        r0 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x014a:
        r0 = "SQRT2";
        r2 = 27;
        r7 = r0;
        r0 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0154:
        r0 = "atan2";
        r2 = 6;
        r7 = r0;
        r0 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x015d:
        r0 = "floor";
        r2 = 10;
        r7 = r0;
        r0 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0167:
        r0 = "round";
        r2 = 16;
        r7 = r0;
        r0 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0171:
        r0 = r9.charAt(r1);
        r3 = 76;
        if (r0 != r3) goto L_0x0183;
    L_0x0179:
        r0 = "LOG10E";
        r2 = 25;
        r7 = r0;
        r0 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0183:
        r3 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        if (r0 != r3) goto L_0x000f;
    L_0x0187:
        r0 = "random";
        r2 = 15;
        r7 = r0;
        r0 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0191:
        r0 = "SQRT1_2";
        r2 = 26;
        r7 = r0;
        r0 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x019b:
        r0 = "toSource";
        r2 = r0;
        r0 = r3;
        goto L_0x0010;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeMath.findPrototypeId(java.lang.String):int");
    }
}
