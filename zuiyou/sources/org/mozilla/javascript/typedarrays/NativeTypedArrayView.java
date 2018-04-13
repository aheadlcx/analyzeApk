package org.mozilla.javascript.typedarrays;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ExternalArrayData;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;

public abstract class NativeTypedArrayView<T> extends NativeArrayBufferView implements List<T>, RandomAccess, ExternalArrayData {
    private static final int Id_BYTES_PER_ELEMENT = 11;
    private static final int Id_constructor = 1;
    private static final int Id_get = 2;
    private static final int Id_length = 10;
    private static final int Id_set = 3;
    private static final int Id_subarray = 4;
    private static final int MAX_INSTANCE_ID = 11;
    protected static final int MAX_PROTOTYPE_ID = 4;
    protected final int length;

    protected abstract NativeTypedArrayView construct(NativeArrayBuffer nativeArrayBuffer, int i, int i2);

    public abstract int getBytesPerElement();

    protected abstract Object js_get(int i);

    protected abstract Object js_set(int i, Object obj);

    protected abstract NativeTypedArrayView realThis(Scriptable scriptable, IdFunctionObject idFunctionObject);

    protected NativeTypedArrayView() {
        this.length = 0;
    }

    protected NativeTypedArrayView(NativeArrayBuffer nativeArrayBuffer, int i, int i2, int i3) {
        super(nativeArrayBuffer, i, i3);
        this.length = i2;
    }

    public Object get(int i, Scriptable scriptable) {
        return js_get(i);
    }

    public boolean has(int i, Scriptable scriptable) {
        return i > 0 && i < this.length;
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        js_set(i, obj);
    }

    public void delete(int i) {
    }

    public Object[] getIds() {
        Object[] objArr = new Object[this.length];
        for (int i = 0; i < this.length; i++) {
            objArr[i] = Integer.valueOf(i);
        }
        return objArr;
    }

    protected boolean checkIndex(int i) {
        return i < 0 || i >= this.length;
    }

    private NativeArrayBuffer makeArrayBuffer(Context context, Scriptable scriptable, int i) {
        return (NativeArrayBuffer) context.newObject(scriptable, NativeArrayBuffer.CLASS_NAME, new Object[]{Integer.valueOf(i)});
    }

    private NativeTypedArrayView js_constructor(Context context, Scriptable scriptable, Object[] objArr) {
        int i = 0;
        if (!NativeArrayBufferView.isArg(objArr, 0)) {
            return construct(NativeArrayBuffer.EMPTY_BUFFER, 0, 0);
        }
        if ((objArr[0] instanceof Number) || (objArr[0] instanceof String)) {
            int toInt32 = ScriptRuntime.toInt32(objArr[0]);
            return construct(makeArrayBuffer(context, scriptable, getBytesPerElement() * toInt32), 0, toInt32);
        } else if (objArr[0] instanceof NativeTypedArrayView) {
            NativeTypedArrayView nativeTypedArrayView = (NativeTypedArrayView) objArr[0];
            r2 = construct(makeArrayBuffer(context, scriptable, nativeTypedArrayView.length * getBytesPerElement()), 0, nativeTypedArrayView.length);
            while (i < nativeTypedArrayView.length) {
                r2.js_set(i, nativeTypedArrayView.js_get(i));
                i++;
            }
            return r2;
        } else if (objArr[0] instanceof NativeArrayBuffer) {
            int toInt322;
            NativeArrayBuffer nativeArrayBuffer = (NativeArrayBuffer) objArr[0];
            if (NativeArrayBufferView.isArg(objArr, 1)) {
                i = ScriptRuntime.toInt32(objArr[1]);
            }
            if (NativeArrayBufferView.isArg(objArr, 2)) {
                toInt322 = ScriptRuntime.toInt32(objArr[2]) * getBytesPerElement();
            } else {
                toInt322 = nativeArrayBuffer.getLength() - i;
            }
            if (i < 0 || i > nativeArrayBuffer.buffer.length) {
                throw ScriptRuntime.constructError("RangeError", "offset out of range");
            } else if (toInt322 < 0 || i + toInt322 > nativeArrayBuffer.buffer.length) {
                throw ScriptRuntime.constructError("RangeError", "length out of range");
            } else if (i % getBytesPerElement() != 0) {
                throw ScriptRuntime.constructError("RangeError", "offset must be a multiple of the byte size");
            } else if (toInt322 % getBytesPerElement() == 0) {
                return construct(nativeArrayBuffer, i, toInt322 / getBytesPerElement());
            } else {
                throw ScriptRuntime.constructError("RangeError", "offset and buffer must be a multiple of the byte size");
            }
        } else if (objArr[0] instanceof NativeArray) {
            List<Object> list = (List) objArr[0];
            r2 = construct(makeArrayBuffer(context, scriptable, list.size() * getBytesPerElement()), 0, list.size());
            for (Object js_set : list) {
                r2.js_set(i, js_set);
                i++;
            }
            return r2;
        } else {
            throw ScriptRuntime.constructError(MNSConstants.ERROR_TAG, "invalid argument");
        }
    }

    private void setRange(NativeTypedArrayView nativeTypedArrayView, int i) {
        int i2 = 0;
        if (i >= this.length) {
            throw ScriptRuntime.constructError("RangeError", "offset out of range");
        } else if (nativeTypedArrayView.length > this.length - i) {
            throw ScriptRuntime.constructError("RangeError", "source array too long");
        } else if (nativeTypedArrayView.arrayBuffer == this.arrayBuffer) {
            Object[] objArr = new Object[nativeTypedArrayView.length];
            for (int i3 = 0; i3 < nativeTypedArrayView.length; i3++) {
                objArr[i3] = nativeTypedArrayView.js_get(i3);
            }
            while (i2 < nativeTypedArrayView.length) {
                js_set(i2 + i, objArr[i2]);
                i2++;
            }
        } else {
            while (i2 < nativeTypedArrayView.length) {
                js_set(i2 + i, nativeTypedArrayView.js_get(i2));
                i2++;
            }
        }
    }

    private void setRange(NativeArray nativeArray, int i) {
        if (i > this.length) {
            throw ScriptRuntime.constructError("RangeError", "offset out of range");
        } else if (nativeArray.size() + i > this.length) {
            throw ScriptRuntime.constructError("RangeError", "offset + length out of range");
        } else {
            Iterator it = nativeArray.iterator();
            while (it.hasNext()) {
                js_set(i, it.next());
                i++;
            }
        }
    }

    private Object js_subarray(Context context, Scriptable scriptable, int i, int i2) {
        if (i < 0) {
            i += this.length;
        }
        if (i2 < 0) {
            i2 += this.length;
        }
        int max = Math.max(0, i);
        int max2 = Math.max(0, Math.min(this.length, i2) - max);
        max = Math.min(max * getBytesPerElement(), this.arrayBuffer.getLength());
        return context.newObject(scriptable, getClassName(), new Object[]{this.arrayBuffer, Integer.valueOf(max), Integer.valueOf(max2)});
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(getClassName())) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        switch (methodId) {
            case 1:
                return js_constructor(context, scriptable, objArr);
            case 2:
                if (objArr.length > 0) {
                    return realThis(scriptable2, idFunctionObject).js_get(ScriptRuntime.toInt32(objArr[0]));
                }
                throw ScriptRuntime.constructError(MNSConstants.ERROR_TAG, "invalid arguments");
            case 3:
                if (objArr.length > 0) {
                    NativeTypedArrayView realThis = realThis(scriptable2, idFunctionObject);
                    int toInt32;
                    if (objArr[0] instanceof NativeTypedArrayView) {
                        if (NativeArrayBufferView.isArg(objArr, 1)) {
                            toInt32 = ScriptRuntime.toInt32(objArr[1]);
                        } else {
                            toInt32 = 0;
                        }
                        realThis.setRange((NativeTypedArrayView) objArr[0], toInt32);
                        return Undefined.instance;
                    } else if (objArr[0] instanceof NativeArray) {
                        if (NativeArrayBufferView.isArg(objArr, 1)) {
                            toInt32 = ScriptRuntime.toInt32(objArr[1]);
                        } else {
                            toInt32 = 0;
                        }
                        realThis.setRange((NativeArray) objArr[0], toInt32);
                        return Undefined.instance;
                    } else if (objArr[0] instanceof Scriptable) {
                        return Undefined.instance;
                    } else {
                        if (NativeArrayBufferView.isArg(objArr, 2)) {
                            return realThis.js_set(ScriptRuntime.toInt32(objArr[0]), objArr[1]);
                        }
                    }
                }
                throw ScriptRuntime.constructError(MNSConstants.ERROR_TAG, "invalid arguments");
            case 4:
                if (objArr.length > 0) {
                    NativeTypedArrayView realThis2 = realThis(scriptable2, idFunctionObject);
                    return realThis2.js_subarray(context, scriptable, ScriptRuntime.toInt32(objArr[0]), NativeArrayBufferView.isArg(objArr, 1) ? ScriptRuntime.toInt32(objArr[1]) : realThis2.length);
                }
                throw ScriptRuntime.constructError(MNSConstants.ERROR_TAG, "invalid arguments");
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
                str = "get";
                break;
            case 3:
                str = "set";
                i2 = 2;
                break;
            case 4:
                str = "subarray";
                i2 = 2;
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(getClassName(), i, str, i2);
    }

    protected int findPrototypeId(String str) {
        String str2;
        int i;
        int length = str.length();
        if (length == 3) {
            char charAt = str.charAt(0);
            if (charAt == 'g') {
                if (str.charAt(2) == 't' && str.charAt(1) == 'e') {
                    return 2;
                }
            } else if (charAt == 's' && str.charAt(2) == 't' && str.charAt(1) == 'e') {
                return 3;
            }
            str2 = null;
            i = 0;
        } else if (length == 8) {
            i = 4;
            str2 = "subarray";
        } else {
            if (length == 11) {
                str2 = "constructor";
                i = 1;
            }
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }

    protected void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        idFunctionObject.put("BYTES_PER_ELEMENT", idFunctionObject, ScriptRuntime.wrapInt(getBytesPerElement()));
    }

    protected int getMaxInstanceId() {
        return 11;
    }

    protected String getInstanceIdName(int i) {
        switch (i) {
            case 10:
                return "length";
            case 11:
                return "BYTES_PER_ELEMENT";
            default:
                return super.getInstanceIdName(i);
        }
    }

    protected Object getInstanceIdValue(int i) {
        switch (i) {
            case 10:
                return ScriptRuntime.wrapInt(this.length);
            case 11:
                return ScriptRuntime.wrapInt(getBytesPerElement());
            default:
                return super.getInstanceIdValue(i);
        }
    }

    protected int findInstanceIdInfo(String str) {
        int i;
        String str2;
        int i2 = 0;
        int length = str.length();
        if (length == 6) {
            i = 10;
            str2 = "length";
        } else if (length == 17) {
            i = 11;
            str2 = "BYTES_PER_ELEMENT";
        } else {
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            i2 = i;
        }
        if (i2 == 0) {
            return super.findInstanceIdInfo(str);
        }
        return IdScriptableObject.instanceIdInfo(5, i2);
    }

    public Object getArrayElement(int i) {
        return js_get(i);
    }

    public void setArrayElement(int i, Object obj) {
        js_set(i, obj);
    }

    public int getArrayLength() {
        return this.length;
    }

    public int size() {
        return this.length;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    public boolean containsAll(Collection<?> collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < this.length; i++) {
            if (obj.equals(js_get(i))) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        for (int i = this.length - 1; i >= 0; i--) {
            if (obj.equals(js_get(i))) {
                return i;
            }
        }
        return -1;
    }

    public Object[] toArray() {
        Object[] objArr = new Object[this.length];
        for (int i = 0; i < this.length; i++) {
            objArr[i] = js_get(i);
        }
        return objArr;
    }

    public <U> U[] toArray(U[] uArr) {
        if (uArr.length < this.length) {
            uArr = (Object[]) Array.newInstance(uArr.getClass().getComponentType(), this.length);
        }
        int i = 0;
        while (i < this.length) {
            try {
                uArr[i] = js_get(i);
                i++;
            } catch (ClassCastException e) {
                throw new ArrayStoreException();
            }
        }
        return uArr;
    }

    public boolean equals(Object obj) {
        try {
            NativeTypedArrayView nativeTypedArrayView = (NativeTypedArrayView) obj;
            if (this.length != nativeTypedArrayView.length) {
                return false;
            }
            for (int i = 0; i < this.length; i++) {
                if (!js_get(i).equals(nativeTypedArrayView.js_get(i))) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < this.length; i2++) {
            i += js_get(i2).hashCode();
        }
        return 0;
    }

    public Iterator<T> iterator() {
        return new NativeTypedArrayIterator(this, 0);
    }

    public ListIterator<T> listIterator() {
        return new NativeTypedArrayIterator(this, 0);
    }

    public ListIterator<T> listIterator(int i) {
        if (!checkIndex(i)) {
            return new NativeTypedArrayIterator(this, i);
        }
        throw new IndexOutOfBoundsException();
    }

    public List<T> subList(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    public void add(int i, T t) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public T remove(int i) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }
}
