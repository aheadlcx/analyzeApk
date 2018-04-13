package org.mozilla.javascript.typedarrays;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;

public class NativeDataView extends NativeArrayBufferView {
    public static final String CLASS_NAME = "DataView";
    private static final int Id_constructor = 1;
    private static final int Id_getFloat32 = 8;
    private static final int Id_getFloat64 = 9;
    private static final int Id_getInt16 = 4;
    private static final int Id_getInt32 = 6;
    private static final int Id_getInt8 = 2;
    private static final int Id_getUint16 = 5;
    private static final int Id_getUint32 = 7;
    private static final int Id_getUint8 = 3;
    private static final int Id_setFloat32 = 16;
    private static final int Id_setFloat64 = 17;
    private static final int Id_setInt16 = 12;
    private static final int Id_setInt32 = 14;
    private static final int Id_setInt8 = 10;
    private static final int Id_setUint16 = 13;
    private static final int Id_setUint32 = 15;
    private static final int Id_setUint8 = 11;
    private static final int MAX_PROTOTYPE_ID = 17;
    private static final long serialVersionUID = 1427967607557438968L;

    public NativeDataView(NativeArrayBuffer nativeArrayBuffer, int i, int i2) {
        super(nativeArrayBuffer, i, i2);
    }

    public String getClassName() {
        return CLASS_NAME;
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        new NativeDataView().exportAsJSClass(17, scriptable, z);
    }

    private void rangeCheck(int i, int i2) {
        if (i < 0 || i + i2 > this.byteLength) {
            throw ScriptRuntime.constructError("RangeError", "offset out of range");
        }
    }

    private void checkOffset(Object[] objArr, int i) {
        if (objArr.length <= i) {
            throw ScriptRuntime.constructError("TypeError", "missing required offset parameter");
        } else if (Undefined.instance.equals(objArr[i])) {
            throw ScriptRuntime.constructError("RangeError", "invalid offset");
        }
    }

    private void checkValue(Object[] objArr, int i) {
        if (objArr.length <= i) {
            throw ScriptRuntime.constructError("TypeError", "missing required value parameter");
        } else if (Undefined.instance.equals(objArr[i])) {
            throw ScriptRuntime.constructError("RangeError", "invalid value parameter");
        }
    }

    private static NativeDataView realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (scriptable instanceof NativeDataView) {
            return (NativeDataView) scriptable;
        }
        throw IdScriptableObject.incompatibleCallError(idFunctionObject);
    }

    private NativeDataView js_constructor(NativeArrayBuffer nativeArrayBuffer, int i, int i2) {
        if (i2 < 0) {
            throw ScriptRuntime.constructError("RangeError", "length out of range");
        } else if (i >= 0 && i + i2 <= nativeArrayBuffer.getLength()) {
            return new NativeDataView(nativeArrayBuffer, i, i2);
        } else {
            throw ScriptRuntime.constructError("RangeError", "offset out of range");
        }
    }

    private Object js_getInt(int i, boolean z, Object[] objArr) {
        boolean z2 = true;
        checkOffset(objArr, 0);
        int toInt32 = ScriptRuntime.toInt32(objArr[0]);
        rangeCheck(toInt32, i);
        if (!(NativeArrayBufferView.isArg(objArr, 1) && i > 1 && ScriptRuntime.toBoolean(objArr[1]))) {
            z2 = false;
        }
        switch (i) {
            case 1:
                return z ? ByteIo.readInt8(this.arrayBuffer.buffer, toInt32) : ByteIo.readUint8(this.arrayBuffer.buffer, toInt32);
            case 2:
                return z ? ByteIo.readInt16(this.arrayBuffer.buffer, toInt32, z2) : ByteIo.readUint16(this.arrayBuffer.buffer, toInt32, z2);
            case 4:
                return z ? ByteIo.readInt32(this.arrayBuffer.buffer, toInt32, z2) : ByteIo.readUint32(this.arrayBuffer.buffer, toInt32, z2);
            default:
                throw new AssertionError();
        }
    }

    private Object js_getFloat(int i, Object[] objArr) {
        boolean z = true;
        checkOffset(objArr, 0);
        int toInt32 = ScriptRuntime.toInt32(objArr[0]);
        rangeCheck(toInt32, i);
        if (!(NativeArrayBufferView.isArg(objArr, 1) && i > 1 && ScriptRuntime.toBoolean(objArr[1]))) {
            z = false;
        }
        switch (i) {
            case 4:
                return ByteIo.readFloat32(this.arrayBuffer.buffer, toInt32, z);
            case 8:
                return ByteIo.readFloat64(this.arrayBuffer.buffer, toInt32, z);
            default:
                throw new AssertionError();
        }
    }

    private void js_setInt(int i, boolean z, Object[] objArr) {
        boolean z2 = false;
        checkOffset(objArr, 0);
        checkValue(objArr, 1);
        int toInt32 = ScriptRuntime.toInt32(objArr[0]);
        rangeCheck(toInt32, i);
        if (NativeArrayBufferView.isArg(objArr, 2) && i > 1 && ScriptRuntime.toBoolean(objArr[2])) {
            z2 = true;
        }
        switch (i) {
            case 1:
                if (z) {
                    ByteIo.writeInt8(this.arrayBuffer.buffer, toInt32, Conversions.toInt8(objArr[1]));
                    return;
                } else {
                    ByteIo.writeUint8(this.arrayBuffer.buffer, toInt32, Conversions.toUint8(objArr[1]));
                    return;
                }
            case 2:
                if (z) {
                    ByteIo.writeInt16(this.arrayBuffer.buffer, toInt32, Conversions.toInt16(objArr[1]), z2);
                    return;
                } else {
                    ByteIo.writeUint16(this.arrayBuffer.buffer, toInt32, Conversions.toUint16(objArr[1]), z2);
                    return;
                }
            case 4:
                if (z) {
                    ByteIo.writeInt32(this.arrayBuffer.buffer, toInt32, Conversions.toInt32(objArr[1]), z2);
                    return;
                } else {
                    ByteIo.writeUint32(this.arrayBuffer.buffer, toInt32, Conversions.toUint32(objArr[1]), z2);
                    return;
                }
            default:
                throw new AssertionError();
        }
    }

    private void js_setFloat(int i, Object[] objArr) {
        boolean z = false;
        checkOffset(objArr, 0);
        checkValue(objArr, 1);
        int toInt32 = ScriptRuntime.toInt32(objArr[0]);
        rangeCheck(toInt32, i);
        if (NativeArrayBufferView.isArg(objArr, 2) && i > 1 && ScriptRuntime.toBoolean(objArr[2])) {
            z = true;
        }
        double toNumber = ScriptRuntime.toNumber(objArr[1]);
        switch (i) {
            case 4:
                ByteIo.writeFloat32(this.arrayBuffer.buffer, toInt32, toNumber, z);
                return;
            case 8:
                ByteIo.writeFloat64(this.arrayBuffer.buffer, toInt32, toNumber, z);
                return;
            default:
                throw new AssertionError();
        }
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        int i = 0;
        if (!idFunctionObject.hasTag(getClassName())) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        switch (methodId) {
            case 1:
                if (NativeArrayBufferView.isArg(objArr, 0) && (objArr[0] instanceof NativeArrayBuffer)) {
                    NativeArrayBuffer nativeArrayBuffer = (NativeArrayBuffer) objArr[0];
                    if (NativeArrayBufferView.isArg(objArr, 1)) {
                        i = ScriptRuntime.toInt32(objArr[1]);
                    }
                    return js_constructor(nativeArrayBuffer, i, NativeArrayBufferView.isArg(objArr, 2) ? ScriptRuntime.toInt32(objArr[2]) : nativeArrayBuffer.getLength() - i);
                }
                throw ScriptRuntime.constructError("TypeError", "Missing parameters");
            case 2:
                return realThis(scriptable2, idFunctionObject).js_getInt(1, true, objArr);
            case 3:
                return realThis(scriptable2, idFunctionObject).js_getInt(1, false, objArr);
            case 4:
                return realThis(scriptable2, idFunctionObject).js_getInt(2, true, objArr);
            case 5:
                return realThis(scriptable2, idFunctionObject).js_getInt(2, false, objArr);
            case 6:
                return realThis(scriptable2, idFunctionObject).js_getInt(4, true, objArr);
            case 7:
                return realThis(scriptable2, idFunctionObject).js_getInt(4, false, objArr);
            case 8:
                return realThis(scriptable2, idFunctionObject).js_getFloat(4, objArr);
            case 9:
                return realThis(scriptable2, idFunctionObject).js_getFloat(8, objArr);
            case 10:
                realThis(scriptable2, idFunctionObject).js_setInt(1, true, objArr);
                return Undefined.instance;
            case 11:
                realThis(scriptable2, idFunctionObject).js_setInt(1, false, objArr);
                return Undefined.instance;
            case 12:
                realThis(scriptable2, idFunctionObject).js_setInt(2, true, objArr);
                return Undefined.instance;
            case 13:
                realThis(scriptable2, idFunctionObject).js_setInt(2, false, objArr);
                return Undefined.instance;
            case 14:
                realThis(scriptable2, idFunctionObject).js_setInt(4, true, objArr);
                return Undefined.instance;
            case 15:
                realThis(scriptable2, idFunctionObject).js_setInt(4, false, objArr);
                return Undefined.instance;
            case 16:
                realThis(scriptable2, idFunctionObject).js_setFloat(4, objArr);
                return Undefined.instance;
            case 17:
                realThis(scriptable2, idFunctionObject).js_setFloat(8, objArr);
                return Undefined.instance;
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    protected void initPrototypeId(int i) {
        String str;
        int i2 = 1;
        switch (i) {
            case 1:
                str = "constructor";
                break;
            case 2:
                str = "getInt8";
                break;
            case 3:
                str = "getUint8";
                break;
            case 4:
                str = "getInt16";
                break;
            case 5:
                str = "getUint16";
                break;
            case 6:
                str = "getInt32";
                break;
            case 7:
                str = "getUint32";
                break;
            case 8:
                str = "getFloat32";
                break;
            case 9:
                str = "getFloat64";
                break;
            case 10:
                str = "setInt8";
                i2 = 2;
                break;
            case 11:
                str = "setUint8";
                i2 = 2;
                break;
            case 12:
                str = "setInt16";
                i2 = 2;
                break;
            case 13:
                str = "setUint16";
                i2 = 2;
                break;
            case 14:
                str = "setInt32";
                i2 = 2;
                break;
            case 15:
                str = "setUint32";
                i2 = 2;
                break;
            case 16:
                str = "setFloat32";
                i2 = 2;
                break;
            case 17:
                str = "setFloat64";
                i2 = 2;
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(getClassName(), i, str, i2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int findPrototypeId(java.lang.String r9) {
        /*
        r8 = this;
        r2 = 8;
        r6 = 50;
        r5 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        r4 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        r0 = 0;
        r1 = 0;
        r3 = r9.length();
        switch(r3) {
            case 7: goto L_0x001e;
            case 8: goto L_0x0037;
            case 9: goto L_0x0093;
            case 10: goto L_0x00d5;
            case 11: goto L_0x011b;
            default: goto L_0x0011;
        };
    L_0x0011:
        r2 = r1;
        r1 = r0;
    L_0x0013:
        if (r2 == 0) goto L_0x0124;
    L_0x0015:
        if (r2 == r9) goto L_0x0124;
    L_0x0017:
        r2 = r2.equals(r9);
        if (r2 != 0) goto L_0x0124;
    L_0x001d:
        return r0;
    L_0x001e:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x002c;
    L_0x0024:
        r1 = "getInt8";
        r2 = 2;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x002c:
        if (r2 != r5) goto L_0x0011;
    L_0x002e:
        r1 = "setInt8";
        r2 = 10;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x0037:
        r2 = 6;
        r2 = r9.charAt(r2);
        r3 = 49;
        if (r2 != r3) goto L_0x0059;
    L_0x0040:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x004e;
    L_0x0046:
        r1 = "getInt16";
        r2 = 4;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x004e:
        if (r2 != r5) goto L_0x0011;
    L_0x0050:
        r1 = "setInt16";
        r2 = 12;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x0059:
        r3 = 51;
        if (r2 != r3) goto L_0x0076;
    L_0x005d:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x006b;
    L_0x0063:
        r1 = "getInt32";
        r2 = 6;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x006b:
        if (r2 != r5) goto L_0x0011;
    L_0x006d:
        r1 = "setInt32";
        r2 = 14;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x0076:
        r3 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        if (r2 != r3) goto L_0x0011;
    L_0x007a:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x0088;
    L_0x0080:
        r1 = "getUint8";
        r2 = 3;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x0088:
        if (r2 != r5) goto L_0x0011;
    L_0x008a:
        r1 = "setUint8";
        r2 = 11;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x0093:
        r3 = r9.charAt(r0);
        if (r3 != r4) goto L_0x00b5;
    L_0x0099:
        r2 = r9.charAt(r2);
        if (r2 != r6) goto L_0x00a8;
    L_0x009f:
        r1 = "getUint32";
        r2 = 7;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x00a8:
        r3 = 54;
        if (r2 != r3) goto L_0x0011;
    L_0x00ac:
        r1 = "getUint16";
        r2 = 5;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x00b5:
        if (r3 != r5) goto L_0x0011;
    L_0x00b7:
        r2 = r9.charAt(r2);
        if (r2 != r6) goto L_0x00c7;
    L_0x00bd:
        r1 = "setUint32";
        r2 = 15;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x00c7:
        r3 = 54;
        if (r2 != r3) goto L_0x0011;
    L_0x00cb:
        r1 = "setUint16";
        r2 = 13;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x00d5:
        r3 = r9.charAt(r0);
        if (r3 != r4) goto L_0x00f9;
    L_0x00db:
        r3 = 9;
        r3 = r9.charAt(r3);
        if (r3 != r6) goto L_0x00eb;
    L_0x00e3:
        r1 = "getFloat32";
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x00eb:
        r2 = 52;
        if (r3 != r2) goto L_0x0011;
    L_0x00ef:
        r1 = "getFloat64";
        r2 = 9;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x00f9:
        if (r3 != r5) goto L_0x0011;
    L_0x00fb:
        r2 = 9;
        r2 = r9.charAt(r2);
        if (r2 != r6) goto L_0x010d;
    L_0x0103:
        r1 = "setFloat32";
        r2 = 16;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x010d:
        r3 = 52;
        if (r2 != r3) goto L_0x0011;
    L_0x0111:
        r1 = "setFloat64";
        r2 = 17;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x011b:
        r1 = "constructor";
        r2 = 1;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0013;
    L_0x0124:
        r0 = r1;
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.typedarrays.NativeDataView.findPrototypeId(java.lang.String):int");
    }
}
