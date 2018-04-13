package org.mozilla.javascript;

final class NativeNumber extends IdScriptableObject {
    private static final int Id_constructor = 1;
    private static final int Id_toExponential = 7;
    private static final int Id_toFixed = 6;
    private static final int Id_toLocaleString = 3;
    private static final int Id_toPrecision = 8;
    private static final int Id_toSource = 4;
    private static final int Id_toString = 2;
    private static final int Id_valueOf = 5;
    private static final int MAX_PRECISION = 100;
    private static final int MAX_PROTOTYPE_ID = 8;
    private static final Object NUMBER_TAG = "Number";
    static final long serialVersionUID = 3504516769741512101L;
    private double doubleValue;

    static void init(Scriptable scriptable, boolean z) {
        new NativeNumber(0.0d).exportAsJSClass(8, scriptable, z);
    }

    NativeNumber(double d) {
        this.doubleValue = d;
    }

    public String getClassName() {
        return "Number";
    }

    protected void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        idFunctionObject.defineProperty("NaN", ScriptRuntime.NaNobj, 7);
        idFunctionObject.defineProperty("POSITIVE_INFINITY", ScriptRuntime.wrapNumber(Double.POSITIVE_INFINITY), 7);
        idFunctionObject.defineProperty("NEGATIVE_INFINITY", ScriptRuntime.wrapNumber(Double.NEGATIVE_INFINITY), 7);
        idFunctionObject.defineProperty("MAX_VALUE", ScriptRuntime.wrapNumber(Double.MAX_VALUE), 7);
        idFunctionObject.defineProperty("MIN_VALUE", ScriptRuntime.wrapNumber(Double.MIN_VALUE), 7);
        super.fillConstructorProperties(idFunctionObject);
    }

    protected void initPrototypeId(int i) {
        String str;
        int i2 = 1;
        switch (i) {
            case 1:
                str = "constructor";
                break;
            case 2:
                str = "toString";
                break;
            case 3:
                str = "toLocaleString";
                break;
            case 4:
                str = "toSource";
                i2 = 0;
                break;
            case 5:
                str = "valueOf";
                i2 = 0;
                break;
            case 6:
                str = "toFixed";
                break;
            case 7:
                str = "toExponential";
                break;
            case 8:
                str = "toPrecision";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(NUMBER_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(NUMBER_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId == 1) {
            double toNumber = objArr.length >= 1 ? ScriptRuntime.toNumber(objArr[0]) : 0.0d;
            if (scriptable2 == null) {
                return new NativeNumber(toNumber);
            }
            return ScriptRuntime.wrapNumber(toNumber);
        } else if (scriptable2 instanceof NativeNumber) {
            double d = ((NativeNumber) scriptable2).doubleValue;
            switch (methodId) {
                case 2:
                case 3:
                    methodId = (objArr.length == 0 || objArr[0] == Undefined.instance) ? 10 : ScriptRuntime.toInt32(objArr[0]);
                    return ScriptRuntime.numberToString(d, methodId);
                case 4:
                    return "(new Number(" + ScriptRuntime.toString(d) + "))";
                case 5:
                    return ScriptRuntime.wrapNumber(d);
                case 6:
                    return num_to(d, objArr, 2, 2, -20, 0);
                case 7:
                    if (Double.isNaN(d)) {
                        return "NaN";
                    }
                    if (!Double.isInfinite(d)) {
                        return num_to(d, objArr, 1, 3, 0, 1);
                    }
                    if (d >= 0.0d) {
                        return "Infinity";
                    }
                    return "-Infinity";
                case 8:
                    if (objArr.length == 0 || objArr[0] == Undefined.instance) {
                        return ScriptRuntime.numberToString(d, 10);
                    }
                    if (Double.isNaN(d)) {
                        return "NaN";
                    }
                    if (!Double.isInfinite(d)) {
                        return num_to(d, objArr, 0, 4, 1, 0);
                    }
                    if (d >= 0.0d) {
                        return "Infinity";
                    }
                    return "-Infinity";
                default:
                    throw new IllegalArgumentException(String.valueOf(methodId));
            }
        } else {
            throw IdScriptableObject.incompatibleCallError(idFunctionObject);
        }
    }

    public String toString() {
        return ScriptRuntime.numberToString(this.doubleValue, 10);
    }

    private static String num_to(double d, Object[] objArr, int i, int i2, int i3, int i4) {
        int i5 = 0;
        if (objArr.length != 0) {
            double toInteger = ScriptRuntime.toInteger(objArr[0]);
            if (toInteger < ((double) i3) || toInteger > 100.0d) {
                throw ScriptRuntime.constructError("RangeError", ScriptRuntime.getMessage1("msg.bad.precision", ScriptRuntime.toString(objArr[0])));
            }
            i5 = ScriptRuntime.toInt32(toInteger);
            i = i2;
        }
        StringBuilder stringBuilder = new StringBuilder();
        DToA.JS_dtostr(stringBuilder, i, i5 + i4, d);
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int findPrototypeId(java.lang.String r7) {
        /*
        r6 = this;
        r2 = 3;
        r4 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        r0 = 0;
        r1 = 0;
        r3 = r7.length();
        switch(r3) {
            case 7: goto L_0x0019;
            case 8: goto L_0x0033;
            case 9: goto L_0x000c;
            case 10: goto L_0x000c;
            case 11: goto L_0x004d;
            case 12: goto L_0x000c;
            case 13: goto L_0x0068;
            case 14: goto L_0x0070;
            default: goto L_0x000c;
        };
    L_0x000c:
        r2 = r1;
        r1 = r0;
    L_0x000e:
        if (r2 == 0) goto L_0x0077;
    L_0x0010:
        if (r2 == r7) goto L_0x0077;
    L_0x0012:
        r2 = r2.equals(r7);
        if (r2 != 0) goto L_0x0077;
    L_0x0018:
        return r0;
    L_0x0019:
        r2 = r7.charAt(r0);
        if (r2 != r4) goto L_0x0027;
    L_0x001f:
        r1 = "toFixed";
        r2 = 6;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000e;
    L_0x0027:
        r3 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
        if (r2 != r3) goto L_0x000c;
    L_0x002b:
        r1 = "valueOf";
        r2 = 5;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000e;
    L_0x0033:
        r2 = r7.charAt(r2);
        r3 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        if (r2 != r3) goto L_0x0043;
    L_0x003b:
        r1 = "toSource";
        r2 = 4;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000e;
    L_0x0043:
        if (r2 != r4) goto L_0x000c;
    L_0x0045:
        r1 = "toString";
        r2 = 2;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000e;
    L_0x004d:
        r2 = r7.charAt(r0);
        r3 = 99;
        if (r2 != r3) goto L_0x005d;
    L_0x0055:
        r1 = "constructor";
        r2 = 1;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000e;
    L_0x005d:
        if (r2 != r4) goto L_0x000c;
    L_0x005f:
        r1 = "toPrecision";
        r2 = 8;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000e;
    L_0x0068:
        r1 = "toExponential";
        r2 = 7;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000e;
    L_0x0070:
        r1 = "toLocaleString";
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000e;
    L_0x0077:
        r0 = r1;
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeNumber.findPrototypeId(java.lang.String):int");
    }
}
