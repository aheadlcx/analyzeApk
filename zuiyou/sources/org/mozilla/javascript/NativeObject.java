package org.mozilla.javascript;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public class NativeObject extends IdScriptableObject implements Map {
    private static final int ConstructorId_create = -9;
    private static final int ConstructorId_defineProperties = -8;
    private static final int ConstructorId_defineProperty = -5;
    private static final int ConstructorId_freeze = -13;
    private static final int ConstructorId_getOwnPropertyDescriptor = -4;
    private static final int ConstructorId_getOwnPropertyNames = -3;
    private static final int ConstructorId_getPrototypeOf = -1;
    private static final int ConstructorId_isExtensible = -6;
    private static final int ConstructorId_isFrozen = -11;
    private static final int ConstructorId_isSealed = -10;
    private static final int ConstructorId_keys = -2;
    private static final int ConstructorId_preventExtensions = -7;
    private static final int ConstructorId_seal = -12;
    private static final int Id___defineGetter__ = 9;
    private static final int Id___defineSetter__ = 10;
    private static final int Id___lookupGetter__ = 11;
    private static final int Id___lookupSetter__ = 12;
    private static final int Id_constructor = 1;
    private static final int Id_hasOwnProperty = 5;
    private static final int Id_isPrototypeOf = 7;
    private static final int Id_propertyIsEnumerable = 6;
    private static final int Id_toLocaleString = 3;
    private static final int Id_toSource = 8;
    private static final int Id_toString = 2;
    private static final int Id_valueOf = 4;
    private static final int MAX_PROTOTYPE_ID = 12;
    private static final Object OBJECT_TAG = "Object";
    static final long serialVersionUID = -6345305608474346996L;

    class EntrySet extends AbstractSet<Entry<Object, Object>> {
        EntrySet() {
        }

        public Iterator<Entry<Object, Object>> iterator() {
            return new Iterator<Entry<Object, Object>>() {
                Object[] ids = NativeObject.this.getIds();
                int index = 0;
                Object key = null;

                public boolean hasNext() {
                    return this.index < this.ids.length;
                }

                public Entry<Object, Object> next() {
                    Object[] objArr = this.ids;
                    int i = this.index;
                    this.index = i + 1;
                    final Object obj = objArr[i];
                    this.key = obj;
                    final Object obj2 = NativeObject.this.get(this.key);
                    return new Entry<Object, Object>() {
                        public Object getKey() {
                            return obj;
                        }

                        public Object getValue() {
                            return obj2;
                        }

                        public Object setValue(Object obj) {
                            throw new UnsupportedOperationException();
                        }

                        public boolean equals(Object obj) {
                            if (!(obj instanceof Entry)) {
                                return false;
                            }
                            Entry entry = (Entry) obj;
                            if (obj == null) {
                                if (entry.getKey() != null) {
                                    return false;
                                }
                            } else if (!obj.equals(entry.getKey())) {
                                return false;
                            }
                            if (obj2 == null) {
                                if (entry.getValue() != null) {
                                    return false;
                                }
                            } else if (!obj2.equals(entry.getValue())) {
                                return false;
                            }
                            return true;
                        }

                        public int hashCode() {
                            int i = 0;
                            int hashCode = obj == null ? 0 : obj.hashCode();
                            if (obj2 != null) {
                                i = obj2.hashCode();
                            }
                            return hashCode ^ i;
                        }

                        public String toString() {
                            return obj + "=" + obj2;
                        }
                    };
                }

                public void remove() {
                    if (this.key == null) {
                        throw new IllegalStateException();
                    }
                    NativeObject.this.remove(this.key);
                    this.key = null;
                }
            };
        }

        public int size() {
            return NativeObject.this.size();
        }
    }

    class KeySet extends AbstractSet<Object> {
        KeySet() {
        }

        public boolean contains(Object obj) {
            return NativeObject.this.containsKey(obj);
        }

        public Iterator<Object> iterator() {
            return new Iterator<Object>() {
                Object[] ids = NativeObject.this.getIds();
                int index = 0;
                Object key;

                public boolean hasNext() {
                    return this.index < this.ids.length;
                }

                public Object next() {
                    try {
                        Object[] objArr = this.ids;
                        int i = this.index;
                        this.index = i + 1;
                        Object obj = objArr[i];
                        this.key = obj;
                        return obj;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        this.key = null;
                        throw new NoSuchElementException();
                    }
                }

                public void remove() {
                    if (this.key == null) {
                        throw new IllegalStateException();
                    }
                    NativeObject.this.remove(this.key);
                    this.key = null;
                }
            };
        }

        public int size() {
            return NativeObject.this.size();
        }
    }

    class ValueCollection extends AbstractCollection<Object> {
        ValueCollection() {
        }

        public Iterator<Object> iterator() {
            return new Iterator<Object>() {
                Object[] ids = NativeObject.this.getIds();
                int index = 0;
                Object key;

                public boolean hasNext() {
                    return this.index < this.ids.length;
                }

                public Object next() {
                    NativeObject nativeObject = NativeObject.this;
                    Object[] objArr = this.ids;
                    int i = this.index;
                    this.index = i + 1;
                    Object obj = objArr[i];
                    this.key = obj;
                    return nativeObject.get(obj);
                }

                public void remove() {
                    if (this.key == null) {
                        throw new IllegalStateException();
                    }
                    NativeObject.this.remove(this.key);
                    this.key = null;
                }
            };
        }

        public int size() {
            return NativeObject.this.size();
        }
    }

    static void init(Scriptable scriptable, boolean z) {
        new NativeObject().exportAsJSClass(12, scriptable, z);
    }

    public String getClassName() {
        return "Object";
    }

    public String toString() {
        return ScriptRuntime.defaultObjectToString(this);
    }

    protected void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -1, "getPrototypeOf", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -2, "keys", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -3, "getOwnPropertyNames", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -4, "getOwnPropertyDescriptor", 2);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -5, "defineProperty", 3);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -6, "isExtensible", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -7, "preventExtensions", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -8, "defineProperties", 2);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -9, "create", 2);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -10, "isSealed", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -11, "isFrozen", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -12, "seal", 1);
        addIdFunctionProperty(idFunctionObject, OBJECT_TAG, -13, "freeze", 1);
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
                i2 = 0;
                break;
            case 3:
                str = "toLocaleString";
                i2 = 0;
                break;
            case 4:
                str = "valueOf";
                i2 = 0;
                break;
            case 5:
                str = "hasOwnProperty";
                break;
            case 6:
                str = "propertyIsEnumerable";
                break;
            case 7:
                str = "isPrototypeOf";
                break;
            case 8:
                str = "toSource";
                i2 = 0;
                break;
            case 9:
                str = "__defineGetter__";
                i2 = 2;
                break;
            case 10:
                str = "__defineSetter__";
                i2 = 2;
                break;
            case 11:
                str = "__lookupGetter__";
                break;
            case 12:
                str = "__lookupSetter__";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(OBJECT_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        boolean z = true;
        int i = 0;
        if (!idFunctionObject.hasTag(OBJECT_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        Object ensureScriptableObject;
        Object ownPropertyDescriptor;
        ScriptableObject ensureScriptableObject2;
        Object[] allIds;
        int length;
        Scriptable ensureScriptable;
        Object[] allIds2;
        boolean has;
        String toStringIdOrIndex;
        switch (methodId) {
            case -13:
                ensureScriptableObject = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]);
                for (Object obj : ensureScriptableObject.getAllIds()) {
                    ownPropertyDescriptor = ensureScriptableObject.getOwnPropertyDescriptor(context, obj);
                    if (isDataDescriptor(ownPropertyDescriptor) && Boolean.TRUE.equals(ownPropertyDescriptor.get("writable"))) {
                        ownPropertyDescriptor.put("writable", ownPropertyDescriptor, Boolean.FALSE);
                    }
                    if (Boolean.TRUE.equals(ownPropertyDescriptor.get("configurable"))) {
                        ownPropertyDescriptor.put("configurable", ownPropertyDescriptor, Boolean.FALSE);
                    }
                    ensureScriptableObject.defineOwnProperty(context, obj, ownPropertyDescriptor, false);
                }
                ensureScriptableObject.preventExtensions();
                return ensureScriptableObject;
            case -12:
                ensureScriptableObject = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]);
                for (Object obj2 : ensureScriptableObject.getAllIds()) {
                    ownPropertyDescriptor = ensureScriptableObject.getOwnPropertyDescriptor(context, obj2);
                    if (Boolean.TRUE.equals(ownPropertyDescriptor.get("configurable"))) {
                        ownPropertyDescriptor.put("configurable", ownPropertyDescriptor, Boolean.FALSE);
                        ensureScriptableObject.defineOwnProperty(context, obj2, ownPropertyDescriptor, false);
                    }
                }
                ensureScriptableObject.preventExtensions();
                return ensureScriptableObject;
            case -11:
                ensureScriptableObject2 = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]);
                if (ensureScriptableObject2.isExtensible()) {
                    return Boolean.FALSE;
                }
                allIds = ensureScriptableObject2.getAllIds();
                length = allIds.length;
                while (i < length) {
                    ScriptableObject ownPropertyDescriptor2 = ensureScriptableObject2.getOwnPropertyDescriptor(context, allIds[i]);
                    if (Boolean.TRUE.equals(ownPropertyDescriptor2.get("configurable"))) {
                        return Boolean.FALSE;
                    }
                    if (isDataDescriptor(ownPropertyDescriptor2) && Boolean.TRUE.equals(ownPropertyDescriptor2.get("writable"))) {
                        return Boolean.FALSE;
                    }
                    i++;
                }
                return Boolean.TRUE;
            case -10:
                ensureScriptableObject2 = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]);
                if (ensureScriptableObject2.isExtensible()) {
                    return Boolean.FALSE;
                }
                allIds = ensureScriptableObject2.getAllIds();
                length = allIds.length;
                while (i < length) {
                    if (Boolean.TRUE.equals(ensureScriptableObject2.getOwnPropertyDescriptor(context, allIds[i]).get("configurable"))) {
                        return Boolean.FALSE;
                    }
                    i++;
                }
                return Boolean.TRUE;
            case -9:
                ensureScriptableObject = objArr.length < 1 ? Undefined.instance : objArr[0];
                ensureScriptable = ensureScriptableObject == null ? null : ensureScriptable(ensureScriptableObject);
                scriptable2 = new NativeObject();
                scriptable2.setParentScope(getParentScope());
                scriptable2.setPrototype(ensureScriptable);
                if (objArr.length > 1 && objArr[1] != Undefined.instance) {
                    scriptable2.defineOwnProperties(context, ensureScriptableObject(Context.toObject(objArr[1], getParentScope())));
                }
                return scriptable2;
            case -8:
                scriptable2 = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]);
                scriptable2.defineOwnProperties(context, ensureScriptableObject(Context.toObject(objArr.length < 2 ? Undefined.instance : objArr[1], getParentScope())));
                return scriptable2;
            case -7:
                ensureScriptableObject = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]);
                ensureScriptableObject.preventExtensions();
                return ensureScriptableObject;
            case -6:
                return Boolean.valueOf(ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]).isExtensible());
            case -5:
                scriptable2 = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]);
                scriptable2.defineOwnProperty(context, objArr.length < 2 ? Undefined.instance : objArr[1], ensureScriptableObject(objArr.length < 3 ? Undefined.instance : objArr[2]));
                return scriptable2;
            case -4:
                ensureScriptableObject = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]).getOwnPropertyDescriptor(context, ScriptRuntime.toString(objArr.length < 2 ? Undefined.instance : objArr[1]));
                if (ensureScriptableObject == null) {
                    return Undefined.instance;
                }
                return ensureScriptableObject;
            case -3:
                allIds2 = ensureScriptableObject(objArr.length < 1 ? Undefined.instance : objArr[0]).getAllIds();
                while (i < allIds2.length) {
                    allIds2[i] = ScriptRuntime.toString(allIds2[i]);
                    i++;
                }
                return context.newArray(scriptable, allIds2);
            case -2:
                allIds2 = ensureScriptable(objArr.length < 1 ? Undefined.instance : objArr[0]).getIds();
                while (i < allIds2.length) {
                    allIds2[i] = ScriptRuntime.toString(allIds2[i]);
                    i++;
                }
                return context.newArray(scriptable, allIds2);
            case -1:
                return ensureScriptable(objArr.length < 1 ? Undefined.instance : objArr[0]).getPrototype();
            case 1:
                if (scriptable2 != null) {
                    return idFunctionObject.construct(context, scriptable, objArr);
                }
                if (objArr.length == 0 || objArr[0] == null || objArr[0] == Undefined.instance) {
                    return new NativeObject();
                }
                return ScriptRuntime.toObject(context, scriptable, objArr[0]);
            case 2:
                if (!context.hasFeature(4)) {
                    return ScriptRuntime.defaultObjectToString(scriptable2);
                }
                ensureScriptableObject = ScriptRuntime.defaultObjectToSource(context, scriptable, scriptable2, objArr);
                length = ensureScriptableObject.length();
                if (length != 0 && ensureScriptableObject.charAt(0) == '(' && ensureScriptableObject.charAt(length - 1) == ')') {
                    return ensureScriptableObject.substring(1, length - 1);
                }
                return ensureScriptableObject;
            case 3:
                ensureScriptableObject = ScriptableObject.getProperty(scriptable2, "toString");
                if (ensureScriptableObject instanceof Callable) {
                    return ((Callable) ensureScriptableObject).call(context, scriptable, scriptable2, ScriptRuntime.emptyArgs);
                }
                throw ScriptRuntime.notFunctionError(ensureScriptableObject);
            case 4:
                return scriptable2;
            case 5:
                String toStringIdOrIndex2 = ScriptRuntime.toStringIdOrIndex(context, objArr.length < 1 ? Undefined.instance : objArr[0]);
                if (toStringIdOrIndex2 == null) {
                    has = scriptable2.has(ScriptRuntime.lastIndexResult(context), scriptable2);
                } else {
                    has = scriptable2.has(toStringIdOrIndex2, scriptable2);
                }
                return ScriptRuntime.wrapBoolean(has);
            case 6:
                String toStringIdOrIndex3 = ScriptRuntime.toStringIdOrIndex(context, objArr.length < 1 ? Undefined.instance : objArr[0]);
                if (toStringIdOrIndex3 == null) {
                    length = ScriptRuntime.lastIndexResult(context);
                    has = scriptable2.has(length, scriptable2);
                    if (has && (scriptable2 instanceof ScriptableObject)) {
                        has = (((ScriptableObject) scriptable2).getAttributes(length) & 2) == 0;
                    }
                } else {
                    has = scriptable2.has(toStringIdOrIndex3, scriptable2);
                    if (has && (scriptable2 instanceof ScriptableObject)) {
                        if ((((ScriptableObject) scriptable2).getAttributes(toStringIdOrIndex3) & 2) != 0) {
                            z = false;
                        }
                        has = z;
                    }
                }
                return ScriptRuntime.wrapBoolean(has);
            case 7:
                boolean z2;
                if (objArr.length != 0 && (objArr[0] instanceof Scriptable)) {
                    ensureScriptable = (Scriptable) objArr[0];
                    do {
                        ensureScriptable = ensureScriptable.getPrototype();
                        if (ensureScriptable == scriptable2) {
                            z2 = true;
                        }
                    } while (ensureScriptable != null);
                }
                return ScriptRuntime.wrapBoolean(z2);
            case 8:
                return ScriptRuntime.defaultObjectToSource(context, scriptable, scriptable2, objArr);
            case 9:
            case 10:
                if (objArr.length < 2 || !(objArr[1] instanceof Callable)) {
                    throw ScriptRuntime.notFunctionError(objArr.length >= 2 ? objArr[1] : Undefined.instance);
                } else if (scriptable2 instanceof ScriptableObject) {
                    ScriptableObject scriptableObject = (ScriptableObject) scriptable2;
                    toStringIdOrIndex = ScriptRuntime.toStringIdOrIndex(context, objArr[0]);
                    length = toStringIdOrIndex != null ? 0 : ScriptRuntime.lastIndexResult(context);
                    Callable callable = (Callable) objArr[1];
                    if (methodId != 10) {
                        z = false;
                    }
                    scriptableObject.setGetterOrSetter(toStringIdOrIndex, length, callable, z);
                    if (scriptableObject instanceof NativeArray) {
                        ((NativeArray) scriptableObject).setDenseOnly(false);
                    }
                    return Undefined.instance;
                } else {
                    throw Context.reportRuntimeError2("msg.extend.scriptable", scriptable2.getClass().getName(), String.valueOf(objArr[0]));
                }
            case 11:
            case 12:
                if (objArr.length < 1 || !(scriptable2 instanceof ScriptableObject)) {
                    return Undefined.instance;
                }
                scriptable2 = (ScriptableObject) scriptable2;
                toStringIdOrIndex = ScriptRuntime.toStringIdOrIndex(context, objArr[0]);
                length = toStringIdOrIndex != null ? 0 : ScriptRuntime.lastIndexResult(context);
                if (methodId != 12) {
                    z = false;
                }
                while (true) {
                    Object getterOrSetter = scriptable2.getGetterOrSetter(toStringIdOrIndex, length, z);
                    if (getterOrSetter == null) {
                        ensureScriptable = scriptable2.getPrototype();
                        if (ensureScriptable != null && (ensureScriptable instanceof ScriptableObject)) {
                            Object obj3 = (ScriptableObject) ensureScriptable;
                        }
                    }
                    if (getterOrSetter != null) {
                        return getterOrSetter;
                    }
                    return Undefined.instance;
                }
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    public boolean containsKey(Object obj) {
        if (obj instanceof String) {
            return has((String) obj, this);
        }
        if (obj instanceof Number) {
            return has(((Number) obj).intValue(), this);
        }
        return false;
    }

    public boolean containsValue(Object obj) {
        for (Object next : values()) {
            if (obj == next || (obj != null && obj.equals(next))) {
                return true;
            }
        }
        return false;
    }

    public Object remove(Object obj) {
        Object obj2 = get(obj);
        if (obj instanceof String) {
            delete((String) obj);
        } else if (obj instanceof Number) {
            delete(((Number) obj).intValue());
        }
        return obj2;
    }

    public Set<Object> keySet() {
        return new KeySet();
    }

    public Collection<Object> values() {
        return new ValueCollection();
    }

    public Set<Entry<Object, Object>> entrySet() {
        return new EntrySet();
    }

    public Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    public void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int findPrototypeId(java.lang.String r9) {
        /*
        r8 = this;
        r6 = 71;
        r4 = 3;
        r3 = 2;
        r2 = 8;
        r0 = 0;
        r1 = 0;
        r5 = r9.length();
        switch(r5) {
            case 7: goto L_0x001c;
            case 8: goto L_0x0024;
            case 9: goto L_0x000f;
            case 10: goto L_0x000f;
            case 11: goto L_0x003d;
            case 12: goto L_0x000f;
            case 13: goto L_0x0045;
            case 14: goto L_0x004d;
            case 15: goto L_0x000f;
            case 16: goto L_0x0067;
            case 17: goto L_0x000f;
            case 18: goto L_0x000f;
            case 19: goto L_0x000f;
            case 20: goto L_0x00ad;
            default: goto L_0x000f;
        };
    L_0x000f:
        r2 = r1;
        r1 = r0;
    L_0x0011:
        if (r2 == 0) goto L_0x00b6;
    L_0x0013:
        if (r2 == r9) goto L_0x00b6;
    L_0x0015:
        r2 = r2.equals(r9);
        if (r2 != 0) goto L_0x00b6;
    L_0x001b:
        return r0;
    L_0x001c:
        r1 = "valueOf";
        r2 = 4;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0011;
    L_0x0024:
        r4 = r9.charAt(r4);
        r5 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        if (r4 != r5) goto L_0x0033;
    L_0x002c:
        r1 = "toSource";
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0011;
    L_0x0033:
        r2 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        if (r4 != r2) goto L_0x000f;
    L_0x0037:
        r1 = "toString";
        r2 = r1;
        r1 = r3;
        goto L_0x0011;
    L_0x003d:
        r1 = "constructor";
        r2 = 1;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0011;
    L_0x0045:
        r1 = "isPrototypeOf";
        r2 = 7;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0011;
    L_0x004d:
        r2 = r9.charAt(r0);
        r3 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        if (r2 != r3) goto L_0x005d;
    L_0x0055:
        r1 = "hasOwnProperty";
        r2 = 5;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0011;
    L_0x005d:
        r3 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        if (r2 != r3) goto L_0x000f;
    L_0x0061:
        r1 = "toLocaleString";
        r2 = r1;
        r1 = r4;
        goto L_0x0011;
    L_0x0067:
        r3 = r9.charAt(r3);
        r4 = 100;
        if (r3 != r4) goto L_0x008b;
    L_0x006f:
        r2 = r9.charAt(r2);
        if (r2 != r6) goto L_0x007e;
    L_0x0075:
        r1 = "__defineGetter__";
        r2 = 9;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0011;
    L_0x007e:
        r3 = 83;
        if (r2 != r3) goto L_0x000f;
    L_0x0082:
        r1 = "__defineSetter__";
        r2 = 10;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0011;
    L_0x008b:
        r4 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        if (r3 != r4) goto L_0x000f;
    L_0x008f:
        r2 = r9.charAt(r2);
        if (r2 != r6) goto L_0x009f;
    L_0x0095:
        r1 = "__lookupGetter__";
        r2 = 11;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0011;
    L_0x009f:
        r3 = 83;
        if (r2 != r3) goto L_0x000f;
    L_0x00a3:
        r1 = "__lookupSetter__";
        r2 = 12;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0011;
    L_0x00ad:
        r1 = "propertyIsEnumerable";
        r2 = 6;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0011;
    L_0x00b6:
        r0 = r1;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeObject.findPrototypeId(java.lang.String):int");
    }
}
