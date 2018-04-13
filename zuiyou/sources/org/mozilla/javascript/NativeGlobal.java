package org.mozilla.javascript;

import java.io.Serializable;
import org.mozilla.javascript.TopLevel.Builtins;
import org.mozilla.javascript.TopLevel.NativeErrors;
import org.mozilla.javascript.xml.XMLLib;

public class NativeGlobal implements Serializable, IdFunctionCall {
    private static final Object FTAG = "Global";
    private static final int INVALID_UTF8 = Integer.MAX_VALUE;
    private static final int Id_decodeURI = 1;
    private static final int Id_decodeURIComponent = 2;
    private static final int Id_encodeURI = 3;
    private static final int Id_encodeURIComponent = 4;
    private static final int Id_escape = 5;
    private static final int Id_eval = 6;
    private static final int Id_isFinite = 7;
    private static final int Id_isNaN = 8;
    private static final int Id_isXMLName = 9;
    private static final int Id_new_CommonError = 14;
    private static final int Id_parseFloat = 10;
    private static final int Id_parseInt = 11;
    private static final int Id_unescape = 12;
    private static final int Id_uneval = 13;
    private static final int LAST_SCOPE_FUNCTION_ID = 13;
    private static final String URI_DECODE_RESERVED = ";/?:@&=+$,#";
    static final long serialVersionUID = 6080442165748707530L;

    public static void init(Context context, Scriptable scriptable, boolean z) {
        IdFunctionCall nativeGlobal = new NativeGlobal();
        for (int i = 1; i <= 13; i++) {
            String str;
            int i2;
            switch (i) {
                case 1:
                    str = "decodeURI";
                    i2 = 1;
                    break;
                case 2:
                    str = "decodeURIComponent";
                    i2 = 1;
                    break;
                case 3:
                    str = "encodeURI";
                    i2 = 1;
                    break;
                case 4:
                    str = "encodeURIComponent";
                    i2 = 1;
                    break;
                case 5:
                    str = "escape";
                    i2 = 1;
                    break;
                case 6:
                    str = "eval";
                    i2 = 1;
                    break;
                case 7:
                    str = "isFinite";
                    i2 = 1;
                    break;
                case 8:
                    str = "isNaN";
                    i2 = 1;
                    break;
                case 9:
                    str = "isXMLName";
                    i2 = 1;
                    break;
                case 10:
                    str = "parseFloat";
                    i2 = 1;
                    break;
                case 11:
                    str = "parseInt";
                    i2 = 2;
                    break;
                case 12:
                    str = "unescape";
                    i2 = 1;
                    break;
                case 13:
                    str = "uneval";
                    i2 = 1;
                    break;
                default:
                    throw Kit.codeBug();
            }
            IdFunctionObject idFunctionObject = new IdFunctionObject(nativeGlobal, FTAG, i, str, i2, scriptable);
            if (z) {
                idFunctionObject.sealObject();
            }
            idFunctionObject.exportAsScopeProperty();
        }
        ScriptableObject.defineProperty(scriptable, "NaN", ScriptRuntime.NaNobj, 7);
        ScriptableObject.defineProperty(scriptable, "Infinity", ScriptRuntime.wrapNumber(Double.POSITIVE_INFINITY), 7);
        ScriptableObject.defineProperty(scriptable, "undefined", Undefined.instance, 7);
        for (NativeErrors nativeErrors : NativeErrors.values()) {
            if (nativeErrors != NativeErrors.Error) {
                Object name = nativeErrors.name();
                Scriptable scriptable2 = (ScriptableObject) ScriptRuntime.newBuiltinObject(context, scriptable, Builtins.Error, ScriptRuntime.emptyArgs);
                scriptable2.put("name", scriptable2, name);
                scriptable2.put("message", scriptable2, (Object) "");
                Object idFunctionObject2 = new IdFunctionObject(nativeGlobal, FTAG, 14, name, 1, scriptable);
                idFunctionObject2.markAsConstructor(scriptable2);
                scriptable2.put("constructor", scriptable2, idFunctionObject2);
                scriptable2.setAttributes("constructor", 2);
                if (z) {
                    scriptable2.sealObject();
                    idFunctionObject2.sealObject();
                }
                idFunctionObject2.exportAsScopeProperty();
            }
        }
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        boolean z = true;
        boolean z2 = false;
        if (idFunctionObject.hasTag(FTAG)) {
            int methodId = idFunctionObject.methodId();
            String scriptRuntime;
            double toNumber;
            switch (methodId) {
                case 1:
                case 2:
                    scriptRuntime = ScriptRuntime.toString(objArr, 0);
                    if (methodId != 1) {
                        z = false;
                    }
                    return decode(scriptRuntime, z);
                case 3:
                case 4:
                    scriptRuntime = ScriptRuntime.toString(objArr, 0);
                    if (methodId != 3) {
                        z = false;
                    }
                    return encode(scriptRuntime, z);
                case 5:
                    return js_escape(objArr);
                case 6:
                    return js_eval(context, scriptable, objArr);
                case 7:
                    if (objArr.length >= 1) {
                        toNumber = ScriptRuntime.toNumber(objArr[0]);
                        if (toNumber != toNumber || toNumber == Double.POSITIVE_INFINITY || toNumber == Double.NEGATIVE_INFINITY) {
                            z = false;
                        }
                        z2 = z;
                    }
                    return ScriptRuntime.wrapBoolean(z2);
                case 8:
                    if (objArr.length >= 1) {
                        toNumber = ScriptRuntime.toNumber(objArr[0]);
                        if (toNumber == toNumber) {
                            z = false;
                        }
                    }
                    return ScriptRuntime.wrapBoolean(z);
                case 9:
                    return ScriptRuntime.wrapBoolean(XMLLib.extractFromScope(scriptable).isXMLName(context, objArr.length == 0 ? Undefined.instance : objArr[0]));
                case 10:
                    return js_parseFloat(objArr);
                case 11:
                    return js_parseInt(objArr);
                case 12:
                    return js_unescape(objArr);
                case 13:
                    return ScriptRuntime.uneval(context, scriptable, objArr.length != 0 ? objArr[0] : Undefined.instance);
                case 14:
                    return NativeError.make(context, scriptable, idFunctionObject, objArr);
            }
        }
        throw idFunctionObject.unknown();
    }

    private Object js_parseInt(Object[] objArr) {
        int i = 0;
        String scriptRuntime = ScriptRuntime.toString(objArr, 0);
        int toInt32 = ScriptRuntime.toInt32(objArr, 1);
        int length = scriptRuntime.length();
        if (length == 0) {
            return ScriptRuntime.NaNobj;
        }
        int i2;
        char charAt;
        double stringToNumber;
        int i3 = 0;
        do {
            char charAt2 = scriptRuntime.charAt(i3);
            if (!ScriptRuntime.isStrWhiteSpaceChar(charAt2)) {
                break;
            }
            i3++;
        } while (i3 < length);
        if (charAt2 != '+') {
            if (charAt2 == '-') {
                i = 1;
            }
            if (i == 0) {
                i2 = i;
                if (toInt32 != 0) {
                    i = -1;
                } else if (toInt32 >= 2 || toInt32 > 36) {
                    return ScriptRuntime.NaNobj;
                } else {
                    if (toInt32 == 16 && length - i3 > 1 && scriptRuntime.charAt(i3) == '0') {
                        char charAt3 = scriptRuntime.charAt(i3 + 1);
                        if (charAt3 == 'x' || charAt3 == 'X') {
                            i3 += 2;
                            i = toInt32;
                        }
                    }
                    i = toInt32;
                }
                if (i == -1) {
                    i = 10;
                    if (length - i3 > 1 && scriptRuntime.charAt(i3) == '0') {
                        charAt = scriptRuntime.charAt(i3 + 1);
                        if (charAt != 'x' || charAt == 'X') {
                            i3 += 2;
                            i = 16;
                        } else if ('0' <= charAt && charAt <= '9') {
                            i = 8;
                            i3++;
                        }
                    }
                }
                stringToNumber = ScriptRuntime.stringToNumber(scriptRuntime, i3, i);
                if (i2 != 0) {
                    stringToNumber = -stringToNumber;
                }
                return ScriptRuntime.wrapNumber(stringToNumber);
            }
        }
        i3++;
        i2 = i;
        if (toInt32 != 0) {
            if (toInt32 >= 2) {
            }
            return ScriptRuntime.NaNobj;
        }
        i = -1;
        if (i == -1) {
            i = 10;
            charAt = scriptRuntime.charAt(i3 + 1);
            if (charAt != 'x') {
            }
            i3 += 2;
            i = 16;
        }
        stringToNumber = ScriptRuntime.stringToNumber(scriptRuntime, i3, i);
        if (i2 != 0) {
            stringToNumber = -stringToNumber;
        }
        return ScriptRuntime.wrapNumber(stringToNumber);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object js_parseFloat(java.lang.Object[] r11) {
        /*
        r10 = this;
        r9 = 45;
        r1 = 1;
        r0 = 0;
        r5 = -1;
        r2 = r11.length;
        if (r2 >= r1) goto L_0x000b;
    L_0x0008:
        r0 = org.mozilla.javascript.ScriptRuntime.NaNobj;
    L_0x000a:
        return r0;
    L_0x000b:
        r2 = r11[r0];
        r7 = org.mozilla.javascript.ScriptRuntime.toString(r2);
        r8 = r7.length();
        r6 = r0;
    L_0x0016:
        if (r6 != r8) goto L_0x001b;
    L_0x0018:
        r0 = org.mozilla.javascript.ScriptRuntime.NaNobj;
        goto L_0x000a;
    L_0x001b:
        r2 = r7.charAt(r6);
        r3 = org.mozilla.javascript.ScriptRuntime.isStrWhiteSpaceChar(r2);
        if (r3 != 0) goto L_0x0032;
    L_0x0025:
        r3 = 43;
        if (r2 == r3) goto L_0x002b;
    L_0x0029:
        if (r2 != r9) goto L_0x00a7;
    L_0x002b:
        r3 = r6 + 1;
        if (r3 != r8) goto L_0x0035;
    L_0x002f:
        r0 = org.mozilla.javascript.ScriptRuntime.NaNobj;
        goto L_0x000a;
    L_0x0032:
        r6 = r6 + 1;
        goto L_0x0016;
    L_0x0035:
        r2 = r7.charAt(r3);
    L_0x0039:
        r4 = 73;
        if (r2 != r4) goto L_0x005f;
    L_0x003d:
        r1 = r3 + 8;
        if (r1 > r8) goto L_0x005c;
    L_0x0041:
        r1 = "Infinity";
        r2 = 8;
        r0 = r7.regionMatches(r3, r1, r0, r2);
        if (r0 == 0) goto L_0x005c;
    L_0x004c:
        r0 = r7.charAt(r6);
        if (r0 != r9) goto L_0x0059;
    L_0x0052:
        r0 = -4503599627370496; // 0xfff0000000000000 float:0.0 double:-Infinity;
    L_0x0054:
        r0 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r0);
        goto L_0x000a;
    L_0x0059:
        r0 = 9218868437227405312; // 0x7ff0000000000000 float:0.0 double:Infinity;
        goto L_0x0054;
    L_0x005c:
        r0 = org.mozilla.javascript.ScriptRuntime.NaNobj;
        goto L_0x000a;
    L_0x005f:
        r2 = r5;
        r4 = r5;
    L_0x0061:
        if (r3 >= r8) goto L_0x00a5;
    L_0x0063:
        r9 = r7.charAt(r3);
        switch(r9) {
            case 43: goto L_0x008c;
            case 45: goto L_0x008c;
            case 46: goto L_0x0078;
            case 48: goto L_0x009a;
            case 49: goto L_0x009a;
            case 50: goto L_0x009a;
            case 51: goto L_0x009a;
            case 52: goto L_0x009a;
            case 53: goto L_0x009a;
            case 54: goto L_0x009a;
            case 55: goto L_0x009a;
            case 56: goto L_0x009a;
            case 57: goto L_0x009a;
            case 69: goto L_0x0080;
            case 101: goto L_0x0080;
            default: goto L_0x006a;
        };
    L_0x006a:
        r1 = r3;
    L_0x006b:
        if (r2 == r5) goto L_0x00a3;
    L_0x006d:
        if (r0 != 0) goto L_0x00a3;
    L_0x006f:
        r0 = r7.substring(r6, r2);
        r0 = java.lang.Double.valueOf(r0);	 Catch:{ NumberFormatException -> 0x009e }
        goto L_0x000a;
    L_0x0078:
        if (r4 == r5) goto L_0x007c;
    L_0x007a:
        r1 = r3;
        goto L_0x006b;
    L_0x007c:
        r4 = r3;
    L_0x007d:
        r3 = r3 + 1;
        goto L_0x0061;
    L_0x0080:
        if (r2 == r5) goto L_0x0084;
    L_0x0082:
        r1 = r3;
        goto L_0x006b;
    L_0x0084:
        r9 = r8 + -1;
        if (r3 != r9) goto L_0x008a;
    L_0x0088:
        r1 = r3;
        goto L_0x006b;
    L_0x008a:
        r2 = r3;
        goto L_0x007d;
    L_0x008c:
        r9 = r3 + -1;
        if (r2 == r9) goto L_0x0092;
    L_0x0090:
        r1 = r3;
        goto L_0x006b;
    L_0x0092:
        r9 = r8 + -1;
        if (r3 != r9) goto L_0x007d;
    L_0x0096:
        r3 = r3 + -1;
        r1 = r3;
        goto L_0x006b;
    L_0x009a:
        if (r2 == r5) goto L_0x007d;
    L_0x009c:
        r0 = r1;
        goto L_0x007d;
    L_0x009e:
        r0 = move-exception;
        r0 = org.mozilla.javascript.ScriptRuntime.NaNobj;
        goto L_0x000a;
    L_0x00a3:
        r2 = r1;
        goto L_0x006f;
    L_0x00a5:
        r1 = r3;
        goto L_0x006b;
    L_0x00a7:
        r3 = r6;
        goto L_0x0039;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeGlobal.js_parseFloat(java.lang.Object[]):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object js_escape(java.lang.Object[] r13) {
        /*
        r12 = this;
        r11 = 43;
        r10 = 37;
        r2 = 2;
        r5 = 1;
        r1 = 0;
        r4 = org.mozilla.javascript.ScriptRuntime.toString(r13, r1);
        r0 = 7;
        r3 = r13.length;
        if (r3 <= r5) goto L_0x002b;
    L_0x000f:
        r0 = r13[r5];
        r6 = org.mozilla.javascript.ScriptRuntime.toNumber(r0);
        r0 = (r6 > r6 ? 1 : (r6 == r6 ? 0 : -1));
        if (r0 != 0) goto L_0x0023;
    L_0x0019:
        r0 = (int) r6;
        r8 = (double) r0;
        r3 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r3 != 0) goto L_0x0023;
    L_0x001f:
        r3 = r0 & -8;
        if (r3 == 0) goto L_0x002b;
    L_0x0023:
        r0 = "msg.bad.esc.mask";
        r0 = org.mozilla.javascript.Context.reportRuntimeError0(r0);
        throw r0;
    L_0x002b:
        r3 = 0;
        r7 = r4.length();
        r5 = r1;
        r1 = r3;
    L_0x0032:
        if (r5 == r7) goto L_0x00c2;
    L_0x0034:
        r8 = r4.charAt(r5);
        if (r0 == 0) goto L_0x007a;
    L_0x003a:
        r3 = 48;
        if (r8 < r3) goto L_0x0042;
    L_0x003e:
        r3 = 57;
        if (r8 <= r3) goto L_0x0070;
    L_0x0042:
        r3 = 65;
        if (r8 < r3) goto L_0x004a;
    L_0x0046:
        r3 = 90;
        if (r8 <= r3) goto L_0x0070;
    L_0x004a:
        r3 = 97;
        if (r8 < r3) goto L_0x0052;
    L_0x004e:
        r3 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        if (r8 <= r3) goto L_0x0070;
    L_0x0052:
        r3 = 64;
        if (r8 == r3) goto L_0x0070;
    L_0x0056:
        r3 = 42;
        if (r8 == r3) goto L_0x0070;
    L_0x005a:
        r3 = 95;
        if (r8 == r3) goto L_0x0070;
    L_0x005e:
        r3 = 45;
        if (r8 == r3) goto L_0x0070;
    L_0x0062:
        r3 = 46;
        if (r8 == r3) goto L_0x0070;
    L_0x0066:
        r3 = r0 & 4;
        if (r3 == 0) goto L_0x007a;
    L_0x006a:
        r3 = 47;
        if (r8 == r3) goto L_0x0070;
    L_0x006e:
        if (r8 != r11) goto L_0x007a;
    L_0x0070:
        if (r1 == 0) goto L_0x0076;
    L_0x0072:
        r3 = (char) r8;
        r1.append(r3);
    L_0x0076:
        r3 = r5 + 1;
        r5 = r3;
        goto L_0x0032;
    L_0x007a:
        if (r1 != 0) goto L_0x00cb;
    L_0x007c:
        r3 = new java.lang.StringBuilder;
        r1 = r7 + 3;
        r3.<init>(r1);
        r3.append(r4);
        r3.setLength(r5);
    L_0x0089:
        r1 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        if (r8 >= r1) goto L_0x00b5;
    L_0x008d:
        r1 = 32;
        if (r8 != r1) goto L_0x0098;
    L_0x0091:
        if (r0 != r2) goto L_0x0098;
    L_0x0093:
        r3.append(r11);
        r1 = r3;
        goto L_0x0076;
    L_0x0098:
        r3.append(r10);
        r1 = r2;
    L_0x009c:
        r1 = r1 + -1;
        r1 = r1 * 4;
        r6 = r1;
    L_0x00a1:
        if (r6 < 0) goto L_0x00cd;
    L_0x00a3:
        r1 = r8 >> r6;
        r1 = r1 & 15;
        r9 = 10;
        if (r1 >= r9) goto L_0x00bf;
    L_0x00ab:
        r1 = r1 + 48;
    L_0x00ad:
        r1 = (char) r1;
        r3.append(r1);
        r1 = r6 + -4;
        r6 = r1;
        goto L_0x00a1;
    L_0x00b5:
        r3.append(r10);
        r1 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        r3.append(r1);
        r1 = 4;
        goto L_0x009c;
    L_0x00bf:
        r1 = r1 + 55;
        goto L_0x00ad;
    L_0x00c2:
        if (r1 != 0) goto L_0x00c6;
    L_0x00c4:
        r0 = r4;
    L_0x00c5:
        return r0;
    L_0x00c6:
        r0 = r1.toString();
        goto L_0x00c5;
    L_0x00cb:
        r3 = r1;
        goto L_0x0089;
    L_0x00cd:
        r1 = r3;
        goto L_0x0076;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeGlobal.js_escape(java.lang.Object[]):java.lang.Object");
    }

    private Object js_unescape(Object[] objArr) {
        Object scriptRuntime = ScriptRuntime.toString(objArr, 0);
        int indexOf = scriptRuntime.indexOf(37);
        if (indexOf < 0) {
            return scriptRuntime;
        }
        int length = scriptRuntime.length();
        char[] toCharArray = scriptRuntime.toCharArray();
        int i = indexOf;
        while (indexOf != length) {
            char c = toCharArray[indexOf];
            int i2 = indexOf + 1;
            if (c == '%' && i2 != length) {
                int i3;
                if (toCharArray[i2] == 'u') {
                    i3 = i2 + 1;
                    indexOf = i2 + 5;
                } else {
                    indexOf = i2 + 2;
                    i3 = i2;
                }
                if (indexOf <= length) {
                    int i4 = 0;
                    for (i3 = 
/*
Method generation error in method: org.mozilla.javascript.NativeGlobal.js_unescape(java.lang.Object[]):java.lang.Object, dex: classes.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r3_2 'i3' int) = (r3_1 'i3' int), (r3_5 'i3' int) binds: {(r3_1 'i3' int)=B:9:0x0026, (r3_5 'i3' int)=B:14:0x0038} in method: org.mozilla.javascript.NativeGlobal.js_unescape(java.lang.Object[]):java.lang.Object, dex: classes.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:118)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:118)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:219)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:187)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:320)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
Caused by: jadx.core.utils.exceptions.CodegenException: PHI can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:537)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:509)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 34 more

*/

                    private Object js_eval(Context context, Scriptable scriptable, Object[] objArr) {
                        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
                        return ScriptRuntime.evalSpecial(context, topLevelScope, topLevelScope, objArr, "eval code", 1);
                    }

                    static boolean isEvalFunction(Object obj) {
                        if (obj instanceof IdFunctionObject) {
                            IdFunctionObject idFunctionObject = (IdFunctionObject) obj;
                            if (idFunctionObject.hasTag(FTAG) && idFunctionObject.methodId() == 6) {
                                return true;
                            }
                        }
                        return false;
                    }

                    @Deprecated
                    public static EcmaError constructError(Context context, String str, String str2, Scriptable scriptable) {
                        return ScriptRuntime.constructError(str, str2);
                    }

                    @Deprecated
                    public static EcmaError constructError(Context context, String str, String str2, Scriptable scriptable, String str3, int i, int i2, String str4) {
                        return ScriptRuntime.constructError(str, str2, str3, i, str4, i2);
                    }

                    private static String encode(String str, boolean z) {
                        StringBuilder stringBuilder = null;
                        int length = str.length();
                        int i = 0;
                        byte[] bArr = null;
                        while (i != length) {
                            StringBuilder stringBuilder2;
                            byte[] bArr2;
                            int charAt = str.charAt(i);
                            if (encodeUnescaped(charAt, z)) {
                                if (stringBuilder != null) {
                                    stringBuilder.append(charAt);
                                    stringBuilder2 = stringBuilder;
                                    bArr2 = bArr;
                                }
                                stringBuilder2 = stringBuilder;
                                bArr2 = bArr;
                            } else {
                                if (stringBuilder == null) {
                                    stringBuilder = new StringBuilder(length + 3);
                                    stringBuilder.append(str);
                                    stringBuilder.setLength(i);
                                    bArr = new byte[6];
                                }
                                if (56320 > charAt || charAt > 57343) {
                                    if (charAt >= 55296 && 56319 >= charAt) {
                                        i++;
                                        if (i == length) {
                                            throw uriError();
                                        }
                                        char charAt2 = str.charAt(i);
                                        if ('?' > charAt2 || charAt2 > '?') {
                                            throw uriError();
                                        }
                                        charAt = (((charAt - 55296) << 10) + (charAt2 - 56320)) + 65536;
                                    }
                                    int oneUcs4ToUtf8Char = oneUcs4ToUtf8Char(bArr, charAt);
                                    for (charAt = 0; charAt < oneUcs4ToUtf8Char; charAt++) {
                                        int i2 = bArr[charAt] & 255;
                                        stringBuilder.append('%');
                                        stringBuilder.append(toHexChar(i2 >>> 4));
                                        stringBuilder.append(toHexChar(i2 & 15));
                                    }
                                    stringBuilder2 = stringBuilder;
                                    bArr2 = bArr;
                                } else {
                                    throw uriError();
                                }
                            }
                            i++;
                            bArr = bArr2;
                            stringBuilder = stringBuilder2;
                        }
                        return stringBuilder == null ? str : stringBuilder.toString();
                    }

                    private static char toHexChar(int i) {
                        if ((i >> 4) != 0) {
                            Kit.codeBug();
                        }
                        return (char) (i < 10 ? i + 48 : (i - 10) + 65);
                    }

                    private static int unHex(char c) {
                        if ('A' <= c && c <= 'F') {
                            return (c - 65) + 10;
                        }
                        if ('a' <= c && c <= 'f') {
                            return (c - 97) + 10;
                        }
                        if ('0' > c || c > '9') {
                            return -1;
                        }
                        return c - 48;
                    }

                    private static int unHex(char c, char c2) {
                        int unHex = unHex(c);
                        int unHex2 = unHex(c2);
                        if (unHex < 0 || unHex2 < 0) {
                            return -1;
                        }
                        return (unHex << 4) | unHex2;
                    }

                    private static String decode(String str, boolean z) {
                        char[] cArr = null;
                        int length = str.length();
                        int i = 0;
                        int i2 = 0;
                        while (i != length) {
                            int i3;
                            char charAt = str.charAt(i);
                            if (charAt != '%') {
                                if (cArr != null) {
                                    i3 = i2 + 1;
                                    cArr[i2] = charAt;
                                } else {
                                    i3 = i2;
                                }
                                i2 = i + 1;
                            } else {
                                int i4;
                                if (cArr == null) {
                                    cArr = new char[length];
                                    str.getChars(0, i, cArr, 0);
                                    i4 = i;
                                } else {
                                    i4 = i2;
                                }
                                if (i + 3 > length) {
                                    throw uriError();
                                }
                                i2 = unHex(str.charAt(i + 1), str.charAt(i + 2));
                                if (i2 < 0) {
                                    throw uriError();
                                }
                                char c;
                                i3 = i + 3;
                                if ((i2 & 128) == 0) {
                                    c = (char) i2;
                                    i2 = i3;
                                } else if ((i2 & 192) == 128) {
                                    throw uriError();
                                } else {
                                    int i5;
                                    int i6;
                                    int i7;
                                    if ((i2 & 32) == 0) {
                                        i5 = i2 & 31;
                                        i6 = 128;
                                        i7 = 1;
                                    } else if ((i2 & 16) == 0) {
                                        i5 = i2 & 15;
                                        i6 = 2048;
                                        i7 = 2;
                                    } else if ((i2 & 8) == 0) {
                                        i5 = i2 & 7;
                                        i6 = 65536;
                                        i7 = 3;
                                    } else if ((i2 & 4) == 0) {
                                        i5 = i2 & 3;
                                        i6 = 2097152;
                                        i7 = 4;
                                    } else if ((i2 & 2) == 0) {
                                        i5 = i2 & 1;
                                        i6 = 67108864;
                                        i7 = 5;
                                    } else {
                                        throw uriError();
                                    }
                                    if ((i7 * 3) + i3 > length) {
                                        throw uriError();
                                    }
                                    i2 = i5;
                                    i5 = 0;
                                    while (i5 != i7) {
                                        if (str.charAt(i3) != '%') {
                                            throw uriError();
                                        }
                                        int unHex = unHex(str.charAt(i3 + 1), str.charAt(i3 + 2));
                                        if (unHex < 0 || (unHex & 192) != 128) {
                                            throw uriError();
                                        }
                                        i3 += 3;
                                        i5++;
                                        i2 = (unHex & 63) | (i2 << 6);
                                    }
                                    if (i2 < i6 || (i2 >= 55296 && i2 <= 57343)) {
                                        i2 = Integer.MAX_VALUE;
                                    } else if (i2 == 65534 || i2 == 65535) {
                                        i2 = 65533;
                                    }
                                    if (i2 >= 65536) {
                                        i2 -= 65536;
                                        if (i2 > 1048575) {
                                            throw uriError();
                                        }
                                        char c2 = (char) ((i2 >>> 10) + 55296);
                                        char c3 = (char) ((i2 & 1023) + 56320);
                                        i5 = i4 + 1;
                                        cArr[i4] = c2;
                                        i4 = i5;
                                        c = c3;
                                        i2 = i3;
                                    } else {
                                        c = (char) i2;
                                        i2 = i3;
                                    }
                                }
                                if (!z || URI_DECODE_RESERVED.indexOf(c) < 0) {
                                    i3 = i4 + 1;
                                    cArr[i4] = c;
                                } else {
                                    i3 = i4;
                                    while (i != i2) {
                                        i4 = i3 + 1;
                                        cArr[i3] = str.charAt(i);
                                        i++;
                                        i3 = i4;
                                    }
                                }
                            }
                            i = i2;
                            i2 = i3;
                        }
                        return cArr == null ? str : new String(cArr, 0, i2);
                    }

                    private static boolean encodeUnescaped(char c, boolean z) {
                        if ('A' <= c && c <= 'Z') {
                            return true;
                        }
                        if ('a' <= c && c <= 'z') {
                            return true;
                        }
                        if (('0' <= c && c <= '9') || "-_.!~*'()".indexOf(c) >= 0) {
                            return true;
                        }
                        if (!z) {
                            return false;
                        }
                        if (URI_DECODE_RESERVED.indexOf(c) < 0) {
                            return false;
                        }
                        return true;
                    }

                    private static EcmaError uriError() {
                        return ScriptRuntime.constructError("URIError", ScriptRuntime.getMessage0("msg.bad.uri"));
                    }

                    private static int oneUcs4ToUtf8Char(byte[] bArr, int i) {
                        if ((i & -128) == 0) {
                            bArr[0] = (byte) i;
                            return 1;
                        }
                        int i2 = 2;
                        int i3 = i >>> 11;
                        while (i3 != 0) {
                            i3 >>>= 5;
                            i2++;
                        }
                        i3 = i2;
                        while (true) {
                            i3--;
                            if (i3 > 0) {
                                bArr[i3] = (byte) ((i & 63) | 128);
                                i >>>= 6;
                            } else {
                                bArr[0] = (byte) ((256 - (1 << (8 - i2))) + i);
                                return i2;
                            }
                        }
                    }
                }
