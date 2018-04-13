package org.mozilla.javascript.typedarrays;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;

public class NativeArrayBuffer extends IdScriptableObject {
    public static final String CLASS_NAME = "ArrayBuffer";
    private static final int ConstructorId_isView = -3;
    private static final byte[] EMPTY_BUF = new byte[0];
    public static final NativeArrayBuffer EMPTY_BUFFER = new NativeArrayBuffer();
    private static final int Id_byteLength = 1;
    private static final int Id_constructor = 1;
    private static final int Id_isView = 3;
    private static final int Id_slice = 2;
    private static final int MAX_INSTANCE_ID = 1;
    private static final int MAX_PROTOTYPE_ID = 3;
    private static final long serialVersionUID = 3110411773054879549L;
    final byte[] buffer;

    public String getClassName() {
        return CLASS_NAME;
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        new NativeArrayBuffer().exportAsJSClass(3, scriptable, z);
    }

    public NativeArrayBuffer() {
        this.buffer = EMPTY_BUF;
    }

    public NativeArrayBuffer(int i) {
        if (i < 0) {
            throw ScriptRuntime.constructError("RangeError", "Negative array length " + i);
        } else if (i == 0) {
            this.buffer = EMPTY_BUF;
        } else {
            this.buffer = new byte[i];
        }
    }

    public int getLength() {
        return this.buffer.length;
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    public NativeArrayBuffer slice(int i, int i2) {
        int length = this.buffer.length;
        if (i2 < 0) {
            i2 += this.buffer.length;
        }
        length = Math.max(0, Math.min(length, i2));
        if (i < 0) {
            i += this.buffer.length;
        }
        int min = Math.min(length, Math.max(0, i));
        length -= min;
        NativeArrayBuffer nativeArrayBuffer = new NativeArrayBuffer(length);
        System.arraycopy(this.buffer, min, nativeArrayBuffer.buffer, 0, length);
        return nativeArrayBuffer;
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        boolean z = true;
        int i = 0;
        if (!idFunctionObject.hasTag(CLASS_NAME)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        switch (methodId) {
            case -3:
                if (!(isArg(objArr, 0) && (objArr[0] instanceof NativeArrayBufferView))) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 1:
                if (isArg(objArr, 0)) {
                    i = ScriptRuntime.toInt32(objArr[0]);
                }
                return new NativeArrayBuffer(i);
            case 2:
                int toInt32;
                NativeArrayBuffer realThis = realThis(scriptable2, idFunctionObject);
                if (isArg(objArr, 0)) {
                    i = ScriptRuntime.toInt32(objArr[0]);
                }
                if (isArg(objArr, 1)) {
                    toInt32 = ScriptRuntime.toInt32(objArr[1]);
                } else {
                    toInt32 = realThis.buffer.length;
                }
                return realThis.slice(i, toInt32);
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    private static NativeArrayBuffer realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (scriptable instanceof NativeArrayBuffer) {
            return (NativeArrayBuffer) scriptable;
        }
        throw IdScriptableObject.incompatibleCallError(idFunctionObject);
    }

    private static boolean isArg(Object[] objArr, int i) {
        return objArr.length > i && !Undefined.instance.equals(objArr[i]);
    }

    protected void initPrototypeId(int i) {
        String str;
        switch (i) {
            case 1:
                str = "constructor";
                break;
            case 2:
                str = "slice";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(CLASS_NAME, i, str, 1);
    }

    protected int findPrototypeId(String str) {
        int i;
        String str2;
        int length = str.length();
        if (length == 5) {
            i = 2;
            str2 = "slice";
        } else if (length == 6) {
            i = 3;
            str2 = "isView";
        } else if (length == 11) {
            i = 1;
            str2 = "constructor";
        } else {
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }

    protected void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        addIdFunctionProperty(idFunctionObject, CLASS_NAME, -3, "isView", 1);
    }

    protected int getMaxInstanceId() {
        return 1;
    }

    protected String getInstanceIdName(int i) {
        if (i == 1) {
            return "byteLength";
        }
        return super.getInstanceIdName(i);
    }

    protected Object getInstanceIdValue(int i) {
        if (i == 1) {
            return ScriptRuntime.wrapInt(this.buffer.length);
        }
        return super.getInstanceIdValue(i);
    }

    protected int findInstanceIdInfo(String str) {
        if ("byteLength".equals(str)) {
            return IdScriptableObject.instanceIdInfo(5, 1);
        }
        return super.findInstanceIdInfo(str);
    }
}
