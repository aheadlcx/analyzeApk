package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.mozilla.javascript.TopLevel.Builtins;
import org.mozilla.javascript.TopLevel.NativeErrors;
import org.mozilla.javascript.annotations.JSConstructor;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;
import org.mozilla.javascript.annotations.JSStaticFunction;
import org.mozilla.javascript.debug.DebuggableObject;

public abstract class ScriptableObject implements Serializable, ConstProperties, Scriptable, DebuggableObject {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final int CONST = 13;
    public static final int DONTENUM = 2;
    public static final int EMPTY = 0;
    private static final Method GET_ARRAY_LENGTH;
    private static final int INITIAL_SLOT_SIZE = 4;
    public static final int PERMANENT = 4;
    public static final int READONLY = 1;
    private static final int SLOT_CONVERT_ACCESSOR_TO_DATA = 5;
    private static final int SLOT_MODIFY = 2;
    private static final int SLOT_MODIFY_CONST = 3;
    private static final int SLOT_MODIFY_GETTER_SETTER = 4;
    private static final int SLOT_QUERY = 1;
    public static final int UNINITIALIZED_CONST = 8;
    static final long serialVersionUID = 2829861078851942586L;
    private volatile Map<Object, Object> associatedValues;
    private int count;
    private transient ExternalArrayData externalData;
    private transient ScriptableObject$Slot firstAdded;
    private boolean isExtensible = true;
    private transient ScriptableObject$Slot lastAdded;
    private Scriptable parentScopeObject;
    private Scriptable prototypeObject;
    private transient ScriptableObject$Slot[] slots;

    public abstract String getClassName();

    static {
        boolean z = false;
        if (!ScriptableObject.class.desiredAssertionStatus()) {
            z = true;
        }
        $assertionsDisabled = z;
        try {
            GET_ARRAY_LENGTH = ScriptableObject.class.getMethod("getExternalArrayLength", new Class[0]);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    protected static ScriptableObject buildDataDescriptor(Scriptable scriptable, Object obj, int i) {
        boolean z;
        boolean z2 = true;
        ScriptableObject nativeObject = new NativeObject();
        ScriptRuntime.setBuiltinProtoAndParent(nativeObject, scriptable, Builtins.Object);
        nativeObject.defineProperty("value", obj, 0);
        String str = "writable";
        if ((i & 1) == 0) {
            z = true;
        } else {
            z = false;
        }
        nativeObject.defineProperty(str, Boolean.valueOf(z), 0);
        str = "enumerable";
        if ((i & 2) == 0) {
            z = true;
        } else {
            z = false;
        }
        nativeObject.defineProperty(str, Boolean.valueOf(z), 0);
        String str2 = "configurable";
        if ((i & 4) != 0) {
            z2 = false;
        }
        nativeObject.defineProperty(str2, Boolean.valueOf(z2), 0);
        return nativeObject;
    }

    static void checkValidAttributes(int i) {
        if ((i & -16) != 0) {
            throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    public ScriptableObject(Scriptable scriptable, Scriptable scriptable2) {
        if (scriptable == null) {
            throw new IllegalArgumentException();
        }
        this.parentScopeObject = scriptable;
        this.prototypeObject = scriptable2;
    }

    public String getTypeOf() {
        return avoidObjectDetection() ? "undefined" : "object";
    }

    public boolean has(String str, Scriptable scriptable) {
        return getSlot(str, 0, 1) != null;
    }

    public boolean has(int i, Scriptable scriptable) {
        if (this.externalData != null) {
            if (i < this.externalData.getArrayLength()) {
                return true;
            }
            return false;
        } else if (getSlot(null, i, 1) == null) {
            return false;
        } else {
            return true;
        }
    }

    public Object get(String str, Scriptable scriptable) {
        ScriptableObject$Slot slot = getSlot(str, 0, 1);
        if (slot == null) {
            return Scriptable.NOT_FOUND;
        }
        return slot.getValue(scriptable);
    }

    public Object get(int i, Scriptable scriptable) {
        if (this.externalData == null) {
            ScriptableObject$Slot slot = getSlot(null, i, 1);
            if (slot == null) {
                return Scriptable.NOT_FOUND;
            }
            return slot.getValue(scriptable);
        } else if (i < this.externalData.getArrayLength()) {
            return this.externalData.getArrayElement(i);
        } else {
            return Scriptable.NOT_FOUND;
        }
    }

    public void put(String str, Scriptable scriptable, Object obj) {
        if (!putImpl(str, 0, scriptable, obj)) {
            if (scriptable == this) {
                throw Kit.codeBug();
            }
            scriptable.put(str, scriptable, obj);
        }
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        if (this.externalData != null) {
            if (i < this.externalData.getArrayLength()) {
                this.externalData.setArrayElement(i, obj);
                return;
            }
            throw new JavaScriptException(ScriptRuntime.newNativeError(Context.getCurrentContext(), this, NativeErrors.RangeError, new Object[]{"External array index out of bounds "}), null, 0);
        } else if (!putImpl(null, i, scriptable, obj)) {
            if (scriptable == this) {
                throw Kit.codeBug();
            }
            scriptable.put(i, scriptable, obj);
        }
    }

    public void delete(String str) {
        checkNotSealed(str, 0);
        removeSlot(str, 0);
    }

    public void delete(int i) {
        checkNotSealed(null, i);
        removeSlot(null, i);
    }

    public void putConst(String str, Scriptable scriptable, Object obj) {
        if (!putConstImpl(str, 0, scriptable, obj, 1)) {
            if (scriptable == this) {
                throw Kit.codeBug();
            } else if (scriptable instanceof ConstProperties) {
                ((ConstProperties) scriptable).putConst(str, scriptable, obj);
            } else {
                scriptable.put(str, scriptable, obj);
            }
        }
    }

    public void defineConst(String str, Scriptable scriptable) {
        if (!putConstImpl(str, 0, scriptable, Undefined.instance, 8)) {
            if (scriptable == this) {
                throw Kit.codeBug();
            } else if (scriptable instanceof ConstProperties) {
                ((ConstProperties) scriptable).defineConst(str, scriptable);
            }
        }
    }

    public boolean isConst(String str) {
        boolean z = true;
        ScriptableObject$Slot slot = getSlot(str, 0, 1);
        if (slot == null) {
            return false;
        }
        if ((slot.getAttributes() & 5) != 5) {
            z = false;
        }
        return z;
    }

    @Deprecated
    public final int getAttributes(String str, Scriptable scriptable) {
        return getAttributes(str);
    }

    @Deprecated
    public final int getAttributes(int i, Scriptable scriptable) {
        return getAttributes(i);
    }

    @Deprecated
    public final void setAttributes(String str, Scriptable scriptable, int i) {
        setAttributes(str, i);
    }

    @Deprecated
    public void setAttributes(int i, Scriptable scriptable, int i2) {
        setAttributes(i, i2);
    }

    public int getAttributes(String str) {
        return findAttributeSlot(str, 0, 1).getAttributes();
    }

    public int getAttributes(int i) {
        return findAttributeSlot(null, i, 1).getAttributes();
    }

    public void setAttributes(String str, int i) {
        checkNotSealed(str, 0);
        findAttributeSlot(str, 0, 2).setAttributes(i);
    }

    public void setAttributes(int i, int i2) {
        checkNotSealed(null, i);
        findAttributeSlot(null, i, 2).setAttributes(i2);
    }

    public void setGetterOrSetter(String str, int i, Callable callable, boolean z) {
        setGetterOrSetter(str, i, callable, z, false);
    }

    private void setGetterOrSetter(String str, int i, Callable callable, boolean z, boolean z2) {
        if (str == null || i == 0) {
            ScriptableObject$GetterSlot scriptableObject$GetterSlot;
            if (!z2) {
                checkNotSealed(str, i);
            }
            if (isExtensible()) {
                scriptableObject$GetterSlot = (ScriptableObject$GetterSlot) getSlot(str, i, 4);
            } else {
                ScriptableObject$Slot unwrapSlot = unwrapSlot(getSlot(str, i, 1));
                if (unwrapSlot instanceof ScriptableObject$GetterSlot) {
                    scriptableObject$GetterSlot = (ScriptableObject$GetterSlot) unwrapSlot;
                } else {
                    return;
                }
            }
            if (z2 || (scriptableObject$GetterSlot.getAttributes() & 1) == 0) {
                if (z) {
                    scriptableObject$GetterSlot.setter = callable;
                } else {
                    scriptableObject$GetterSlot.getter = callable;
                }
                scriptableObject$GetterSlot.value = Undefined.instance;
                return;
            }
            throw Context.reportRuntimeError1("msg.modify.readonly", str);
        }
        throw new IllegalArgumentException(str);
    }

    public Object getGetterOrSetter(String str, int i, boolean z) {
        if (str == null || i == 0) {
            ScriptableObject$Slot unwrapSlot = unwrapSlot(getSlot(str, i, 1));
            if (unwrapSlot == null) {
                return null;
            }
            if (!(unwrapSlot instanceof ScriptableObject$GetterSlot)) {
                return Undefined.instance;
            }
            ScriptableObject$GetterSlot scriptableObject$GetterSlot = (ScriptableObject$GetterSlot) unwrapSlot;
            Object obj = z ? scriptableObject$GetterSlot.setter : scriptableObject$GetterSlot.getter;
            if (obj == null) {
                return Undefined.instance;
            }
            return obj;
        }
        throw new IllegalArgumentException(str);
    }

    protected boolean isGetterOrSetter(String str, int i, boolean z) {
        ScriptableObject$Slot unwrapSlot = unwrapSlot(getSlot(str, i, 1));
        if (unwrapSlot instanceof ScriptableObject$GetterSlot) {
            if (z && ((ScriptableObject$GetterSlot) unwrapSlot).setter != null) {
                return true;
            }
            if (!(z || ((ScriptableObject$GetterSlot) unwrapSlot).getter == null)) {
                return true;
            }
        }
        return false;
    }

    void addLazilyInitializedValue(String str, int i, LazilyLoadedCtor lazilyLoadedCtor, int i2) {
        if (str == null || i == 0) {
            checkNotSealed(str, i);
            ScriptableObject$GetterSlot scriptableObject$GetterSlot = (ScriptableObject$GetterSlot) getSlot(str, i, 4);
            scriptableObject$GetterSlot.setAttributes(i2);
            scriptableObject$GetterSlot.getter = null;
            scriptableObject$GetterSlot.setter = null;
            scriptableObject$GetterSlot.value = lazilyLoadedCtor;
            return;
        }
        throw new IllegalArgumentException(str);
    }

    public void setExternalArrayData(ExternalArrayData externalArrayData) {
        this.externalData = externalArrayData;
        if (externalArrayData == null) {
            delete("length");
        } else {
            defineProperty("length", null, GET_ARRAY_LENGTH, null, 3);
        }
    }

    public ExternalArrayData getExternalArrayData() {
        return this.externalData;
    }

    public Object getExternalArrayLength() {
        return Integer.valueOf(this.externalData == null ? 0 : this.externalData.getArrayLength());
    }

    public Scriptable getPrototype() {
        return this.prototypeObject;
    }

    public void setPrototype(Scriptable scriptable) {
        this.prototypeObject = scriptable;
    }

    public Scriptable getParentScope() {
        return this.parentScopeObject;
    }

    public void setParentScope(Scriptable scriptable) {
        this.parentScopeObject = scriptable;
    }

    public Object[] getIds() {
        return getIds(false);
    }

    public Object[] getAllIds() {
        return getIds(true);
    }

    public Object getDefaultValue(Class<?> cls) {
        return getDefaultValue(this, cls);
    }

    public static Object getDefaultValue(Scriptable scriptable, Class<?> cls) {
        Context context = null;
        int i = 0;
        while (i < 2) {
            int i2;
            String str;
            Object[] objArr;
            if (cls == ScriptRuntime.StringClass) {
                int i3;
                if (i == 0) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                i2 = i3;
            } else {
                i2 = i == 1 ? 1 : 0;
            }
            if (i2 != 0) {
                str = "toString";
                objArr = ScriptRuntime.emptyArgs;
            } else {
                String str2 = "valueOf";
                objArr = new Object[1];
                if (cls == null) {
                    str = "undefined";
                } else if (cls == ScriptRuntime.StringClass) {
                    str = "string";
                } else if (cls == ScriptRuntime.ScriptableClass) {
                    str = "object";
                } else if (cls == ScriptRuntime.FunctionClass) {
                    str = "function";
                } else if (cls == ScriptRuntime.BooleanClass || cls == Boolean.TYPE) {
                    str = "boolean";
                } else if (cls == ScriptRuntime.NumberClass || cls == ScriptRuntime.ByteClass || cls == Byte.TYPE || cls == ScriptRuntime.ShortClass || cls == Short.TYPE || cls == ScriptRuntime.IntegerClass || cls == Integer.TYPE || cls == ScriptRuntime.FloatClass || cls == Float.TYPE || cls == ScriptRuntime.DoubleClass || cls == Double.TYPE) {
                    str = "number";
                } else {
                    throw Context.reportRuntimeError1("msg.invalid.type", cls.toString());
                }
                objArr[0] = str;
                str = str2;
            }
            Object property = getProperty(scriptable, str);
            if (property instanceof Function) {
                Function function = (Function) property;
                if (context == null) {
                    context = Context.getContext();
                }
                property = function.call(context, function.getParentScope(), scriptable, objArr);
                if (property == null) {
                    continue;
                } else {
                    if (!(!(property instanceof Scriptable) || cls == ScriptRuntime.ScriptableClass || cls == ScriptRuntime.FunctionClass)) {
                        if (i2 != 0 && (property instanceof Wrapper)) {
                            property = ((Wrapper) property).unwrap();
                            if (property instanceof String) {
                            }
                        }
                    }
                    return property;
                }
            }
            i++;
        }
        throw ScriptRuntime.typeError1("msg.default.value", cls == null ? "undefined" : cls.getName());
    }

    public boolean hasInstance(Scriptable scriptable) {
        return ScriptRuntime.jsDelegatesTo(scriptable, this);
    }

    public boolean avoidObjectDetection() {
        return false;
    }

    protected Object equivalentValues(Object obj) {
        return this == obj ? Boolean.TRUE : Scriptable.NOT_FOUND;
    }

    public static <T extends Scriptable> void defineClass(Scriptable scriptable, Class<T> cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        defineClass(scriptable, cls, false, false);
    }

    public static <T extends Scriptable> void defineClass(Scriptable scriptable, Class<T> cls, boolean z) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        defineClass(scriptable, cls, z, false);
    }

    public static <T extends Scriptable> String defineClass(Scriptable scriptable, Class<T> cls, boolean z, boolean z2) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        BaseFunction buildClassCtor = buildClassCtor(scriptable, cls, z, z2);
        if (buildClassCtor == null) {
            return null;
        }
        String className = buildClassCtor.getClassPrototype().getClassName();
        defineProperty(scriptable, className, buildClassCtor, 2);
        return className;
    }

    static <T extends Scriptable> BaseFunction buildClassCtor(Scriptable scriptable, Class<T> cls, boolean z, boolean z2) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        int i;
        Constructor constructor;
        AccessibleObject[] methodList = FunctionObject.getMethodList(cls);
        for (Method method : methodList) {
            if (method.getName().equals("init")) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 3 && parameterTypes[0] == ScriptRuntime.ContextClass && parameterTypes[1] == ScriptRuntime.ScriptableClass && parameterTypes[2] == Boolean.TYPE && Modifier.isStatic(method.getModifiers())) {
                    Object[] objArr = new Object[3];
                    objArr[0] = Context.getContext();
                    objArr[1] = scriptable;
                    objArr[2] = z ? Boolean.TRUE : Boolean.FALSE;
                    method.invoke(null, objArr);
                    return null;
                } else if (parameterTypes.length == 1 && parameterTypes[0] == ScriptRuntime.ScriptableClass && Modifier.isStatic(method.getModifiers())) {
                    method.invoke(null, new Object[]{scriptable});
                    return null;
                }
            }
        }
        AccessibleObject[] constructors = cls.getConstructors();
        for (i = 0; i < constructors.length; i++) {
            if (constructors[i].getParameterTypes().length == 0) {
                constructor = constructors[i];
                break;
            }
        }
        constructor = null;
        if (constructor == null) {
            throw Context.reportRuntimeError1("msg.zero.arg.ctor", cls.getName());
        }
        String defineClass;
        Scriptable scriptable2 = (Scriptable) constructor.newInstance(ScriptRuntime.emptyArgs);
        String className = scriptable2.getClassName();
        Object property = getProperty(getTopLevelScope(scriptable), className);
        if (property instanceof BaseFunction) {
            Object prototypeProperty = ((BaseFunction) property).getPrototypeProperty();
            if (prototypeProperty != null) {
                if (cls.equals(prototypeProperty.getClass())) {
                    return (BaseFunction) property;
                }
            }
        }
        Scriptable scriptable3 = null;
        if (z2) {
            Class superclass = cls.getSuperclass();
            if (ScriptRuntime.ScriptableClass.isAssignableFrom(superclass) && !Modifier.isAbstract(superclass.getModifiers())) {
                defineClass = defineClass(scriptable, extendsScriptable(superclass), z, z2);
                if (defineClass != null) {
                    scriptable3 = getClassPrototype(scriptable, defineClass);
                }
            }
        }
        if (scriptable3 == null) {
            scriptable3 = getObjectPrototype(scriptable);
        }
        scriptable2.setPrototype(scriptable3);
        String str = "jsFunction_";
        str = "jsStaticFunction_";
        str = "jsGet_";
        str = "jsSet_";
        str = "jsConstructor";
        Member findAnnotatedMember = findAnnotatedMember(methodList, JSConstructor.class);
        if (findAnnotatedMember == null) {
            findAnnotatedMember = findAnnotatedMember(constructors, JSConstructor.class);
        }
        if (findAnnotatedMember == null) {
            findAnnotatedMember = FunctionObject.findSingleMethod(methodList, "jsConstructor");
        }
        if (findAnnotatedMember == null) {
            if (constructors.length == 1) {
                findAnnotatedMember = constructors[0];
            } else if (constructors.length == 2) {
                if (constructors[0].getParameterTypes().length == 0) {
                    findAnnotatedMember = constructors[1];
                } else if (constructors[1].getParameterTypes().length == 0) {
                    findAnnotatedMember = constructors[0];
                }
            }
            if (findAnnotatedMember == null) {
                throw Context.reportRuntimeError1("msg.ctor.multiple.parms", cls.getName());
            }
        }
        Object obj = findAnnotatedMember;
        BaseFunction functionObject = new FunctionObject(className, obj, scriptable);
        if (functionObject.isVarArgsMethod()) {
            throw Context.reportRuntimeError1("msg.varargs.ctor", obj.getName());
        }
        functionObject.initAsConstructor(scriptable, scriptable2);
        Method method2 = null;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        int length = methodList.length;
        int i2 = 0;
        while (i2 < length) {
            Method method3 = methodList[i2];
            if (method3 == obj) {
                method3 = method2;
            } else {
                String name = method3.getName();
                if (name.equals("finishInit")) {
                    Class[] parameterTypes2 = method3.getParameterTypes();
                    if (parameterTypes2.length == 3 && parameterTypes2[0] == ScriptRuntime.ScriptableClass && parameterTypes2[1] == FunctionObject.class && parameterTypes2[2] == ScriptRuntime.ScriptableClass && Modifier.isStatic(method3.getModifiers())) {
                    }
                }
                if (name.indexOf(36) != -1) {
                    method3 = method2;
                } else {
                    if (name.equals("jsConstructor")) {
                        method3 = method2;
                    } else {
                        Annotation annotation;
                        HashSet hashSet3;
                        str = null;
                        if (method3.isAnnotationPresent(JSFunction.class)) {
                            annotation = method3.getAnnotation(JSFunction.class);
                        } else if (method3.isAnnotationPresent(JSStaticFunction.class)) {
                            annotation = method3.getAnnotation(JSStaticFunction.class);
                        } else if (method3.isAnnotationPresent(JSGetter.class)) {
                            annotation = method3.getAnnotation(JSGetter.class);
                        } else if (method3.isAnnotationPresent(JSSetter.class)) {
                            method3 = method2;
                        } else {
                            annotation = null;
                        }
                        if (annotation == null) {
                            if (name.startsWith("jsFunction_")) {
                                str = "jsFunction_";
                            } else {
                                if (name.startsWith("jsStaticFunction_")) {
                                    str = "jsStaticFunction_";
                                } else {
                                    if (name.startsWith("jsGet_")) {
                                        str = "jsGet_";
                                    } else if (annotation == null) {
                                        method3 = method2;
                                    }
                                }
                            }
                        }
                        Object obj2 = ((annotation instanceof JSStaticFunction) || str == "jsStaticFunction_") ? 1 : null;
                        if (obj2 != null) {
                            hashSet3 = hashSet;
                        } else {
                            hashSet3 = hashSet2;
                        }
                        defineClass = getPropertyName(name, str, annotation);
                        if (hashSet3.contains(defineClass)) {
                            throw Context.reportRuntimeError2("duplicate.defineClass.name", name, defineClass);
                        }
                        hashSet3.add(defineClass);
                        if ((annotation instanceof JSGetter) || str == "jsGet_") {
                            if (scriptable2 instanceof ScriptableObject) {
                                Method findSetterMethod = findSetterMethod(methodList, defineClass, "jsSet_");
                                ((ScriptableObject) scriptable2).defineProperty(defineClass, null, method3, findSetterMethod, (findSetterMethod != null ? 0 : 1) | 6);
                                method3 = method2;
                            } else {
                                throw Context.reportRuntimeError2("msg.extend.scriptable", scriptable2.getClass().toString(), defineClass);
                            }
                        } else if (obj2 == null || Modifier.isStatic(method3.getModifiers())) {
                            FunctionObject functionObject2 = new FunctionObject(defineClass, method3, scriptable2);
                            if (functionObject2.isVarArgsConstructor()) {
                                throw Context.reportRuntimeError1("msg.varargs.fun", obj.getName());
                            }
                            if (obj2 != null) {
                                scriptable3 = functionObject;
                            } else {
                                scriptable3 = scriptable2;
                            }
                            defineProperty(scriptable3, defineClass, functionObject2, 2);
                            if (z) {
                                functionObject2.sealObject();
                            }
                            method3 = method2;
                        } else {
                            throw Context.reportRuntimeError("jsStaticFunction must be used with static method.");
                        }
                    }
                }
            }
            i2++;
            method2 = method3;
        }
        if (method2 != null) {
            method2.invoke(null, new Object[]{scriptable, functionObject, scriptable2});
        }
        if (z) {
            functionObject.sealObject();
            if (scriptable2 instanceof ScriptableObject) {
                ((ScriptableObject) scriptable2).sealObject();
            }
        }
        return functionObject;
    }

    private static Member findAnnotatedMember(AccessibleObject[] accessibleObjectArr, Class<? extends Annotation> cls) {
        for (AccessibleObject accessibleObject : accessibleObjectArr) {
            if (accessibleObject.isAnnotationPresent(cls)) {
                return (Member) accessibleObject;
            }
        }
        return null;
    }

    private static Method findSetterMethod(Method[] methodArr, String str, String str2) {
        String str3 = "set" + Character.toUpperCase(str.charAt(0)) + str.substring(1);
        for (Method method : methodArr) {
            JSSetter jSSetter = (JSSetter) method.getAnnotation(JSSetter.class);
            if (jSSetter != null && (str.equals(jSSetter.value()) || ("".equals(jSSetter.value()) && str3.equals(method.getName())))) {
                return method;
            }
        }
        String str4 = str2 + str;
        for (Method method2 : methodArr) {
            if (str4.equals(method2.getName())) {
                return method2;
            }
        }
        return null;
    }

    private static String getPropertyName(String str, String str2, Annotation annotation) {
        if (str2 != null) {
            return str.substring(str2.length());
        }
        String str3 = null;
        if (annotation instanceof JSGetter) {
            str3 = ((JSGetter) annotation).value();
            if ((str3 == null || str3.length() == 0) && str.length() > 3 && str.startsWith("get")) {
                str3 = str.substring(3);
                if (Character.isUpperCase(str3.charAt(0))) {
                    if (str3.length() == 1) {
                        str3 = str3.toLowerCase();
                    } else if (!Character.isUpperCase(str3.charAt(1))) {
                        str3 = Character.toLowerCase(str3.charAt(0)) + str3.substring(1);
                    }
                }
            }
        } else if (annotation instanceof JSFunction) {
            str3 = ((JSFunction) annotation).value();
        } else if (annotation instanceof JSStaticFunction) {
            str3 = ((JSStaticFunction) annotation).value();
        }
        if (str3 == null || str3.length() == 0) {
            return str;
        }
        return str3;
    }

    private static <T extends Scriptable> Class<T> extendsScriptable(Class<?> cls) {
        return ScriptRuntime.ScriptableClass.isAssignableFrom(cls) ? cls : null;
    }

    public void defineProperty(String str, Object obj, int i) {
        checkNotSealed(str, 0);
        put(str, (Scriptable) this, obj);
        setAttributes(str, i);
    }

    public static void defineProperty(Scriptable scriptable, String str, Object obj, int i) {
        if (scriptable instanceof ScriptableObject) {
            ((ScriptableObject) scriptable).defineProperty(str, obj, i);
        } else {
            scriptable.put(str, scriptable, obj);
        }
    }

    public static void defineConstProperty(Scriptable scriptable, String str) {
        if (scriptable instanceof ConstProperties) {
            ((ConstProperties) scriptable).defineConst(str, scriptable);
        } else {
            defineProperty(scriptable, str, Undefined.instance, 13);
        }
    }

    public void defineProperty(String str, Class<?> cls, int i) {
        int length = str.length();
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        int i2;
        char[] cArr = new char[(length + 3)];
        str.getChars(0, length, cArr, 3);
        cArr[3] = Character.toUpperCase(cArr[3]);
        cArr[0] = 'g';
        cArr[1] = 'e';
        cArr[2] = 't';
        String str2 = new String(cArr);
        cArr[0] = 's';
        String str3 = new String(cArr);
        Method[] methodList = FunctionObject.getMethodList(cls);
        Method findSingleMethod = FunctionObject.findSingleMethod(methodList, str2);
        Method findSingleMethod2 = FunctionObject.findSingleMethod(methodList, str3);
        if (findSingleMethod2 == null) {
            i2 = i | 1;
        } else {
            i2 = i;
        }
        if (findSingleMethod2 == null) {
            findSingleMethod2 = null;
        }
        defineProperty(str, null, findSingleMethod, findSingleMethod2, i2);
    }

    public void defineProperty(String str, Object obj, Method method, Method method2, int i) {
        MemberBox memberBox;
        int i2;
        Object obj2;
        Object obj3 = null;
        if (method != null) {
            String str2;
            memberBox = new MemberBox(method);
            if (Modifier.isStatic(method.getModifiers())) {
                memberBox.delegateTo = Void.TYPE;
                i2 = 1;
            } else {
                i2 = obj != null ? 1 : 0;
                memberBox.delegateTo = obj;
            }
            Class[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 0) {
                if (i2 != 0) {
                    str2 = "msg.obj.getter.parms";
                }
                str2 = null;
            } else if (parameterTypes.length == 1) {
                Class cls = parameterTypes[0];
                if (cls == ScriptRuntime.ScriptableClass || cls == ScriptRuntime.ScriptableObjectClass) {
                    if (i2 == 0) {
                        str2 = "msg.bad.getter.parms";
                    }
                    str2 = null;
                } else {
                    str2 = "msg.bad.getter.parms";
                }
            } else {
                str2 = "msg.bad.getter.parms";
            }
            if (str2 != null) {
                throw Context.reportRuntimeError1(str2, method.toString());
            }
            obj2 = memberBox;
        } else {
            obj2 = null;
        }
        if (method2 != null) {
            if (method2.getReturnType() != Void.TYPE) {
                throw Context.reportRuntimeError1("msg.setter.return", method2.toString());
            }
            String str3;
            memberBox = new MemberBox(method2);
            if (Modifier.isStatic(method2.getModifiers())) {
                memberBox.delegateTo = Void.TYPE;
                i2 = 1;
            } else {
                i2 = obj != null ? 1 : 0;
                memberBox.delegateTo = obj;
            }
            Class[] parameterTypes2 = method2.getParameterTypes();
            if (parameterTypes2.length == 1) {
                if (i2 != 0) {
                    str3 = "msg.setter2.expected";
                }
            } else if (parameterTypes2.length == 2) {
                Class cls2 = parameterTypes2[0];
                if (cls2 != ScriptRuntime.ScriptableClass && cls2 != ScriptRuntime.ScriptableObjectClass) {
                    str3 = "msg.setter2.parms";
                } else if (i2 == 0) {
                    str3 = "msg.setter1.parms";
                }
            } else {
                str3 = "msg.setter.parms";
            }
            if (str3 != null) {
                throw Context.reportRuntimeError1(str3, method2.toString());
            }
            obj3 = memberBox;
        }
        ScriptableObject$GetterSlot scriptableObject$GetterSlot = (ScriptableObject$GetterSlot) getSlot(str, 0, 4);
        scriptableObject$GetterSlot.setAttributes(i);
        scriptableObject$GetterSlot.getter = obj2;
        scriptableObject$GetterSlot.setter = obj3;
    }

    public void defineOwnProperties(Context context, ScriptableObject scriptableObject) {
        int i;
        int i2 = 0;
        Object[] ids = scriptableObject.getIds();
        ScriptableObject[] scriptableObjectArr = new ScriptableObject[ids.length];
        int length = ids.length;
        for (i = 0; i < length; i++) {
            ScriptableObject ensureScriptableObject = ensureScriptableObject(ScriptRuntime.getObjectElem((Scriptable) scriptableObject, ids[i], context));
            checkPropertyDefinition(ensureScriptableObject);
            scriptableObjectArr[i] = ensureScriptableObject;
        }
        i = ids.length;
        while (i2 < i) {
            defineOwnProperty(context, ids[i2], scriptableObjectArr[i2]);
            i2++;
        }
    }

    public void defineOwnProperty(Context context, Object obj, ScriptableObject scriptableObject) {
        checkPropertyDefinition(scriptableObject);
        defineOwnProperty(context, obj, scriptableObject, true);
    }

    protected void defineOwnProperty(Context context, Object obj, ScriptableObject scriptableObject, boolean z) {
        ScriptableObject$Slot slot;
        int applyDescriptorToAttributeBitset;
        ScriptableObject$Slot slot2 = getSlot(context, obj, 1);
        int i = slot2 == null ? 1 : 0;
        if (z) {
            checkPropertyChange(ScriptRuntime.toString(obj), slot2 == null ? null : slot2.getPropertyDescriptor(context, this), scriptableObject);
        }
        boolean isAccessorDescriptor = isAccessorDescriptor(scriptableObject);
        if (slot2 == null) {
            slot = getSlot(context, obj, isAccessorDescriptor ? 4 : 2);
            applyDescriptorToAttributeBitset = applyDescriptorToAttributeBitset(7, scriptableObject);
        } else {
            slot = slot2;
            applyDescriptorToAttributeBitset = applyDescriptorToAttributeBitset(slot2.getAttributes(), scriptableObject);
        }
        slot = unwrapSlot(slot);
        Object property;
        if (isAccessorDescriptor) {
            if (!(slot instanceof ScriptableObject$GetterSlot)) {
                slot = getSlot(context, obj, 4);
            }
            ScriptableObject$GetterSlot scriptableObject$GetterSlot = (ScriptableObject$GetterSlot) slot;
            property = getProperty((Scriptable) scriptableObject, "get");
            if (property != NOT_FOUND) {
                scriptableObject$GetterSlot.getter = property;
            }
            property = getProperty((Scriptable) scriptableObject, "set");
            if (property != NOT_FOUND) {
                scriptableObject$GetterSlot.setter = property;
            }
            scriptableObject$GetterSlot.value = Undefined.instance;
            scriptableObject$GetterSlot.setAttributes(applyDescriptorToAttributeBitset);
            return;
        }
        if ((slot instanceof ScriptableObject$GetterSlot) && isDataDescriptor(scriptableObject)) {
            slot = getSlot(context, obj, 5);
        }
        property = getProperty((Scriptable) scriptableObject, "value");
        if (property != NOT_FOUND) {
            slot.value = property;
        } else if (i != 0) {
            slot.value = Undefined.instance;
        }
        slot.setAttributes(applyDescriptorToAttributeBitset);
    }

    protected void checkPropertyDefinition(ScriptableObject scriptableObject) {
        Object property = getProperty((Scriptable) scriptableObject, "get");
        if (property == NOT_FOUND || property == Undefined.instance || (property instanceof Callable)) {
            property = getProperty((Scriptable) scriptableObject, "set");
            if (property != NOT_FOUND && property != Undefined.instance && !(property instanceof Callable)) {
                throw ScriptRuntime.notFunctionError(property);
            } else if (isDataDescriptor(scriptableObject) && isAccessorDescriptor(scriptableObject)) {
                throw ScriptRuntime.typeError0("msg.both.data.and.accessor.desc");
            } else {
                return;
            }
        }
        throw ScriptRuntime.notFunctionError(property);
    }

    protected void checkPropertyChange(String str, ScriptableObject scriptableObject, ScriptableObject scriptableObject2) {
        if (scriptableObject == null) {
            if (!isExtensible()) {
                throw ScriptRuntime.typeError0("msg.not.extensible");
            }
        } else if (!isFalse(scriptableObject.get("configurable", (Scriptable) scriptableObject))) {
        } else {
            if (isTrue(getProperty((Scriptable) scriptableObject2, "configurable"))) {
                throw ScriptRuntime.typeError1("msg.change.configurable.false.to.true", str);
            } else if (isTrue(scriptableObject.get("enumerable", (Scriptable) scriptableObject)) != isTrue(getProperty((Scriptable) scriptableObject2, "enumerable"))) {
                throw ScriptRuntime.typeError1("msg.change.enumerable.with.configurable.false", str);
            } else {
                boolean isDataDescriptor = isDataDescriptor(scriptableObject2);
                boolean isAccessorDescriptor = isAccessorDescriptor(scriptableObject2);
                if (!isDataDescriptor && !isAccessorDescriptor) {
                    return;
                }
                if (isDataDescriptor && isDataDescriptor(scriptableObject)) {
                    if (!isFalse(scriptableObject.get("writable", (Scriptable) scriptableObject))) {
                        return;
                    }
                    if (isTrue(getProperty((Scriptable) scriptableObject2, "writable"))) {
                        throw ScriptRuntime.typeError1("msg.change.writable.false.to.true.with.configurable.false", str);
                    } else if (!sameValue(getProperty((Scriptable) scriptableObject2, "value"), scriptableObject.get("value", (Scriptable) scriptableObject))) {
                        throw ScriptRuntime.typeError1("msg.change.value.with.writable.false", str);
                    }
                } else if (isAccessorDescriptor && isAccessorDescriptor(scriptableObject)) {
                    if (!sameValue(getProperty((Scriptable) scriptableObject2, "set"), scriptableObject.get("set", (Scriptable) scriptableObject))) {
                        throw ScriptRuntime.typeError1("msg.change.setter.with.configurable.false", str);
                    } else if (!sameValue(getProperty((Scriptable) scriptableObject2, "get"), scriptableObject.get("get", (Scriptable) scriptableObject))) {
                        throw ScriptRuntime.typeError1("msg.change.getter.with.configurable.false", str);
                    }
                } else if (isDataDescriptor(scriptableObject)) {
                    throw ScriptRuntime.typeError1("msg.change.property.data.to.accessor.with.configurable.false", str);
                } else {
                    throw ScriptRuntime.typeError1("msg.change.property.accessor.to.data.with.configurable.false", str);
                }
            }
        }
    }

    protected static boolean isTrue(Object obj) {
        return obj != NOT_FOUND && ScriptRuntime.toBoolean(obj);
    }

    protected static boolean isFalse(Object obj) {
        return !isTrue(obj);
    }

    protected boolean sameValue(Object obj, Object obj2) {
        if (obj == NOT_FOUND) {
            return true;
        }
        Object obj3;
        if (obj2 == NOT_FOUND) {
            obj3 = Undefined.instance;
        } else {
            obj3 = obj2;
        }
        if ((obj3 instanceof Number) && (obj instanceof Number)) {
            double doubleValue = ((Number) obj3).doubleValue();
            double doubleValue2 = ((Number) obj).doubleValue();
            if (Double.isNaN(doubleValue) && Double.isNaN(doubleValue2)) {
                return true;
            }
            if (doubleValue == 0.0d && Double.doubleToLongBits(doubleValue) != Double.doubleToLongBits(doubleValue2)) {
                return false;
            }
        }
        return ScriptRuntime.shallowEq(obj3, obj);
    }

    protected int applyDescriptorToAttributeBitset(int i, ScriptableObject scriptableObject) {
        Object property = getProperty((Scriptable) scriptableObject, "enumerable");
        int i2 = property != NOT_FOUND ? ScriptRuntime.toBoolean(property) ? i & -3 : i | 2 : i;
        Object property2 = getProperty((Scriptable) scriptableObject, "writable");
        if (property2 != NOT_FOUND) {
            i2 = ScriptRuntime.toBoolean(property2) ? i2 & -2 : i2 | 1;
        }
        property2 = getProperty((Scriptable) scriptableObject, "configurable");
        if (property2 != NOT_FOUND) {
            return ScriptRuntime.toBoolean(property2) ? i2 & -5 : i2 | 4;
        } else {
            return i2;
        }
    }

    protected boolean isDataDescriptor(ScriptableObject scriptableObject) {
        return hasProperty((Scriptable) scriptableObject, "value") || hasProperty((Scriptable) scriptableObject, "writable");
    }

    protected boolean isAccessorDescriptor(ScriptableObject scriptableObject) {
        return hasProperty((Scriptable) scriptableObject, "get") || hasProperty((Scriptable) scriptableObject, "set");
    }

    protected boolean isGenericDescriptor(ScriptableObject scriptableObject) {
        return (isDataDescriptor(scriptableObject) || isAccessorDescriptor(scriptableObject)) ? false : true;
    }

    protected static Scriptable ensureScriptable(Object obj) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        throw ScriptRuntime.typeError1("msg.arg.not.object", ScriptRuntime.typeof(obj));
    }

    protected static ScriptableObject ensureScriptableObject(Object obj) {
        if (obj instanceof ScriptableObject) {
            return (ScriptableObject) obj;
        }
        throw ScriptRuntime.typeError1("msg.arg.not.object", ScriptRuntime.typeof(obj));
    }

    public void defineFunctionProperties(String[] strArr, Class<?> cls, int i) {
        Method[] methodList = FunctionObject.getMethodList(cls);
        for (String str : strArr) {
            Member findSingleMethod = FunctionObject.findSingleMethod(methodList, str);
            if (findSingleMethod == null) {
                throw Context.reportRuntimeError2("msg.method.not.found", str, cls.getName());
            }
            defineProperty(str, new FunctionObject(str, findSingleMethod, this), i);
        }
    }

    public static Scriptable getObjectPrototype(Scriptable scriptable) {
        return TopLevel.getBuiltinPrototype(getTopLevelScope(scriptable), Builtins.Object);
    }

    public static Scriptable getFunctionPrototype(Scriptable scriptable) {
        return TopLevel.getBuiltinPrototype(getTopLevelScope(scriptable), Builtins.Function);
    }

    public static Scriptable getArrayPrototype(Scriptable scriptable) {
        return TopLevel.getBuiltinPrototype(getTopLevelScope(scriptable), Builtins.Array);
    }

    public static Scriptable getClassPrototype(Scriptable scriptable, String str) {
        Object property = getProperty(getTopLevelScope(scriptable), str);
        if (property instanceof BaseFunction) {
            property = ((BaseFunction) property).getPrototypeProperty();
        } else if (!(property instanceof Scriptable)) {
            return null;
        } else {
            Scriptable scriptable2 = (Scriptable) property;
            property = scriptable2.get("prototype", scriptable2);
        }
        if (property instanceof Scriptable) {
            return (Scriptable) property;
        }
        return null;
    }

    public static Scriptable getTopLevelScope(Scriptable scriptable) {
        while (true) {
            Scriptable parentScope = scriptable.getParentScope();
            if (parentScope == null) {
                return scriptable;
            }
            scriptable = parentScope;
        }
    }

    public boolean isExtensible() {
        return this.isExtensible;
    }

    public void preventExtensions() {
        this.isExtensible = false;
    }

    public synchronized void sealObject() {
        if (this.count >= 0) {
            for (ScriptableObject$Slot scriptableObject$Slot = this.firstAdded; scriptableObject$Slot != null; scriptableObject$Slot = scriptableObject$Slot.orderedNext) {
                Object obj = scriptableObject$Slot.value;
                if (obj instanceof LazilyLoadedCtor) {
                    LazilyLoadedCtor lazilyLoadedCtor = (LazilyLoadedCtor) obj;
                    try {
                        lazilyLoadedCtor.init();
                        scriptableObject$Slot.value = lazilyLoadedCtor.getValue();
                    } catch (Throwable th) {
                        scriptableObject$Slot.value = lazilyLoadedCtor.getValue();
                    }
                }
            }
            this.count ^= -1;
        }
    }

    public final boolean isSealed() {
        return this.count < 0;
    }

    private void checkNotSealed(String str, int i) {
        if (isSealed()) {
            if (str == null) {
                str = Integer.toString(i);
            }
            throw Context.reportRuntimeError1("msg.modify.sealed", str);
        }
    }

    public static Object getProperty(Scriptable scriptable, String str) {
        Object obj;
        Scriptable scriptable2 = scriptable;
        do {
            obj = scriptable2.get(str, scriptable);
            if (obj != Scriptable.NOT_FOUND) {
                break;
            }
            scriptable2 = scriptable2.getPrototype();
        } while (scriptable2 != null);
        return obj;
    }

    public static <T> T getTypedProperty(Scriptable scriptable, int i, Class<T> cls) {
        Object property = getProperty(scriptable, i);
        if (property == Scriptable.NOT_FOUND) {
            property = null;
        }
        return cls.cast(Context.jsToJava(property, cls));
    }

    public static Object getProperty(Scriptable scriptable, int i) {
        Object obj;
        Scriptable scriptable2 = scriptable;
        do {
            obj = scriptable2.get(i, scriptable);
            if (obj != Scriptable.NOT_FOUND) {
                break;
            }
            scriptable2 = scriptable2.getPrototype();
        } while (scriptable2 != null);
        return obj;
    }

    public static <T> T getTypedProperty(Scriptable scriptable, String str, Class<T> cls) {
        Object property = getProperty(scriptable, str);
        if (property == Scriptable.NOT_FOUND) {
            property = null;
        }
        return cls.cast(Context.jsToJava(property, cls));
    }

    public static boolean hasProperty(Scriptable scriptable, String str) {
        return getBase(scriptable, str) != null;
    }

    public static void redefineProperty(Scriptable scriptable, String str, boolean z) {
        Scriptable base = getBase(scriptable, str);
        if (base != null) {
            if ((base instanceof ConstProperties) && ((ConstProperties) base).isConst(str)) {
                throw ScriptRuntime.typeError1("msg.const.redecl", str);
            } else if (z) {
                throw ScriptRuntime.typeError1("msg.var.redecl", str);
            }
        }
    }

    public static boolean hasProperty(Scriptable scriptable, int i) {
        return getBase(scriptable, i) != null;
    }

    public static void putProperty(Scriptable scriptable, String str, Object obj) {
        Scriptable base = getBase(scriptable, str);
        if (base == null) {
            base = scriptable;
        }
        base.put(str, scriptable, obj);
    }

    public static void putConstProperty(Scriptable scriptable, String str, Object obj) {
        Scriptable base = getBase(scriptable, str);
        if (base == null) {
            base = scriptable;
        }
        if (base instanceof ConstProperties) {
            ((ConstProperties) base).putConst(str, scriptable, obj);
        }
    }

    public static void putProperty(Scriptable scriptable, int i, Object obj) {
        Scriptable base = getBase(scriptable, i);
        if (base == null) {
            base = scriptable;
        }
        base.put(i, scriptable, obj);
    }

    public static boolean deleteProperty(Scriptable scriptable, String str) {
        Scriptable base = getBase(scriptable, str);
        if (base == null) {
            return true;
        }
        base.delete(str);
        if (base.has(str, scriptable)) {
            return false;
        }
        return true;
    }

    public static boolean deleteProperty(Scriptable scriptable, int i) {
        Scriptable base = getBase(scriptable, i);
        if (base == null) {
            return true;
        }
        base.delete(i);
        if (base.has(i, scriptable)) {
            return false;
        }
        return true;
    }

    public static Object[] getPropertyIds(Scriptable scriptable) {
        if (scriptable == null) {
            return ScriptRuntime.emptyArgs;
        }
        Object[] ids = scriptable.getIds();
        ObjToIntMap objToIntMap = null;
        while (true) {
            scriptable = scriptable.getPrototype();
            if (scriptable == null) {
                break;
            }
            Object[] ids2 = scriptable.getIds();
            if (ids2.length != 0) {
                Object[] objArr;
                if (objToIntMap != null) {
                    objArr = ids;
                } else if (ids.length == 0) {
                    ids = ids2;
                } else {
                    ObjToIntMap objToIntMap2 = new ObjToIntMap(ids.length + ids2.length);
                    for (int i = 0; i != ids.length; i++) {
                        objToIntMap2.intern(ids[i]);
                    }
                    objToIntMap = objToIntMap2;
                    objArr = null;
                }
                for (int i2 = 0; i2 != ids2.length; i2++) {
                    objToIntMap.intern(ids2[i2]);
                }
                ids = objArr;
            }
        }
        if (objToIntMap != null) {
            return objToIntMap.getKeys();
        }
        return ids;
    }

    public static Object callMethod(Scriptable scriptable, String str, Object[] objArr) {
        return callMethod(null, scriptable, str, objArr);
    }

    public static Object callMethod(Context context, Scriptable scriptable, String str, Object[] objArr) {
        Object property = getProperty(scriptable, str);
        if (property instanceof Function) {
            Function function = (Function) property;
            Scriptable topLevelScope = getTopLevelScope(scriptable);
            if (context != null) {
                return function.call(context, topLevelScope, scriptable, objArr);
            }
            return Context.call(null, function, topLevelScope, scriptable, objArr);
        }
        throw ScriptRuntime.notFunctionError(scriptable, str);
    }

    private static Scriptable getBase(Scriptable scriptable, String str) {
        while (!scriptable.has(str, scriptable)) {
            scriptable = scriptable.getPrototype();
            if (scriptable == null) {
                break;
            }
        }
        return scriptable;
    }

    private static Scriptable getBase(Scriptable scriptable, int i) {
        while (!scriptable.has(i, scriptable)) {
            scriptable = scriptable.getPrototype();
            if (scriptable == null) {
                break;
            }
        }
        return scriptable;
    }

    public final Object getAssociatedValue(Object obj) {
        Map map = this.associatedValues;
        if (map == null) {
            return null;
        }
        return map.get(obj);
    }

    public static Object getTopScopeValue(Scriptable scriptable, Object obj) {
        Scriptable topLevelScope = getTopLevelScope(scriptable);
        do {
            if (topLevelScope instanceof ScriptableObject) {
                Object associatedValue = ((ScriptableObject) topLevelScope).getAssociatedValue(obj);
                if (associatedValue != null) {
                    return associatedValue;
                }
            }
            topLevelScope = topLevelScope.getPrototype();
        } while (topLevelScope != null);
        return null;
    }

    public final synchronized Object associateValue(Object obj, Object obj2) {
        Map map;
        if (obj2 == null) {
            throw new IllegalArgumentException();
        }
        map = this.associatedValues;
        if (map == null) {
            map = new HashMap();
            this.associatedValues = map;
        }
        return Kit.initHash(map, obj, obj2);
    }

    private boolean putImpl(String str, int i, Scriptable scriptable, Object obj) {
        ScriptableObject$Slot slot;
        if (this != scriptable) {
            slot = getSlot(str, i, 1);
            if (slot == null) {
                return false;
            }
        } else if (this.isExtensible) {
            if (this.count < 0) {
                checkNotSealed(str, i);
            }
            slot = getSlot(str, i, 2);
        } else {
            slot = getSlot(str, i, 1);
            if (slot == null) {
                return true;
            }
        }
        return slot.setValue(obj, this, scriptable);
    }

    private boolean putConstImpl(String str, int i, Scriptable scriptable, Object obj, int i2) {
        if ($assertionsDisabled || i2 != 0) {
            ScriptableObject$Slot slot;
            if (this != scriptable) {
                slot = getSlot(str, i, 1);
                if (slot == null) {
                    return false;
                }
            } else if (isExtensible()) {
                checkNotSealed(str, i);
                slot = unwrapSlot(getSlot(str, i, 3));
                int attributes = slot.getAttributes();
                if ((attributes & 1) == 0) {
                    throw Context.reportRuntimeError1("msg.var.redecl", str);
                }
                if ((attributes & 8) != 0) {
                    slot.value = obj;
                    if (i2 != 8) {
                        slot.setAttributes(attributes & -9);
                    }
                }
                return true;
            } else {
                slot = getSlot(str, i, 1);
                if (slot == null) {
                    return true;
                }
            }
            return slot.setValue(obj, this, scriptable);
        }
        throw new AssertionError();
    }

    private ScriptableObject$Slot findAttributeSlot(String str, int i, int i2) {
        ScriptableObject$Slot slot = getSlot(str, i, i2);
        if (slot != null) {
            return slot;
        }
        if (str == null) {
            str = Integer.toString(i);
        }
        throw Context.reportRuntimeError1("msg.prop.not.found", str);
    }

    private static ScriptableObject$Slot unwrapSlot(ScriptableObject$Slot scriptableObject$Slot) {
        return scriptableObject$Slot instanceof ScriptableObject$RelinkedSlot ? ((ScriptableObject$RelinkedSlot) scriptableObject$Slot).slot : scriptableObject$Slot;
    }

    private ScriptableObject$Slot getSlot(String str, int i, int i2) {
        ScriptableObject$Slot[] scriptableObject$SlotArr = this.slots;
        if (scriptableObject$SlotArr == null && i2 == 1) {
            return null;
        }
        if (str != null) {
            i = str.hashCode();
        }
        if (scriptableObject$SlotArr != null) {
            ScriptableObject$Slot scriptableObject$Slot = scriptableObject$SlotArr[getSlotIndex(scriptableObject$SlotArr.length, i)];
            while (scriptableObject$Slot != null) {
                String str2 = scriptableObject$Slot.name;
                if (i == scriptableObject$Slot.indexOrHash && (str2 == str || (str != null && str.equals(str2)))) {
                    break;
                }
                scriptableObject$Slot = scriptableObject$Slot.next;
            }
            switch (i2) {
                case 1:
                    return scriptableObject$Slot;
                case 2:
                case 3:
                    if (scriptableObject$Slot != null) {
                        return scriptableObject$Slot;
                    }
                    break;
                case 4:
                    scriptableObject$Slot = unwrapSlot(scriptableObject$Slot);
                    if (scriptableObject$Slot instanceof ScriptableObject$GetterSlot) {
                        return scriptableObject$Slot;
                    }
                    break;
                case 5:
                    scriptableObject$Slot = unwrapSlot(scriptableObject$Slot);
                    if (!(scriptableObject$Slot instanceof ScriptableObject$GetterSlot)) {
                        return scriptableObject$Slot;
                    }
                    break;
            }
        }
        return createSlot(str, i, i2);
    }

    private synchronized ScriptableObject$Slot createSlot(String str, int i, int i2) {
        ScriptableObject$Slot scriptableObject$Slot;
        ScriptableObject$Slot[] scriptableObject$SlotArr;
        int slotIndex;
        ScriptableObject$Slot[] scriptableObject$SlotArr2 = this.slots;
        ScriptableObject$Slot[] scriptableObject$SlotArr3;
        if (this.count == 0) {
            scriptableObject$SlotArr3 = new ScriptableObject$Slot[4];
            this.slots = scriptableObject$SlotArr3;
            scriptableObject$SlotArr = scriptableObject$SlotArr3;
            slotIndex = getSlotIndex(scriptableObject$SlotArr3.length, i);
        } else {
            int slotIndex2 = getSlotIndex(scriptableObject$SlotArr2.length, i);
            scriptableObject$Slot = scriptableObject$SlotArr2[slotIndex2];
            ScriptableObject$Slot scriptableObject$Slot2 = scriptableObject$Slot;
            ScriptableObject$Slot scriptableObject$Slot3 = scriptableObject$Slot;
            while (scriptableObject$Slot2 != null && (scriptableObject$Slot2.indexOrHash != i || (scriptableObject$Slot2.name != str && (str == null || !str.equals(scriptableObject$Slot2.name))))) {
                scriptableObject$Slot3 = scriptableObject$Slot2;
                scriptableObject$Slot2 = scriptableObject$Slot2.next;
            }
            if (scriptableObject$Slot2 != null) {
                ScriptableObject$Slot unwrapSlot = unwrapSlot(scriptableObject$Slot2);
                if (i2 == 4 && !(unwrapSlot instanceof ScriptableObject$GetterSlot)) {
                    scriptableObject$Slot = new ScriptableObject$GetterSlot(str, i, unwrapSlot.getAttributes());
                } else if (i2 == 5 && (unwrapSlot instanceof ScriptableObject$GetterSlot)) {
                    scriptableObject$Slot = new ScriptableObject$Slot(str, i, unwrapSlot.getAttributes());
                } else {
                    scriptableObject$Slot = i2 == 3 ? null : unwrapSlot;
                }
                scriptableObject$Slot.value = unwrapSlot.value;
                scriptableObject$Slot.next = scriptableObject$Slot2.next;
                if (this.lastAdded != null) {
                    this.lastAdded.orderedNext = scriptableObject$Slot;
                }
                if (this.firstAdded == null) {
                    this.firstAdded = scriptableObject$Slot;
                }
                this.lastAdded = scriptableObject$Slot;
                if (scriptableObject$Slot3 == scriptableObject$Slot2) {
                    scriptableObject$SlotArr2[slotIndex2] = scriptableObject$Slot;
                } else {
                    scriptableObject$Slot3.next = scriptableObject$Slot;
                }
                scriptableObject$Slot2.markDeleted();
            } else if ((this.count + 1) * 4 > scriptableObject$SlotArr2.length * 3) {
                scriptableObject$SlotArr3 = new ScriptableObject$Slot[(scriptableObject$SlotArr2.length * 2)];
                copyTable(this.slots, scriptableObject$SlotArr3, this.count);
                this.slots = scriptableObject$SlotArr3;
                scriptableObject$SlotArr = scriptableObject$SlotArr3;
                slotIndex = getSlotIndex(scriptableObject$SlotArr3.length, i);
            } else {
                slotIndex = slotIndex2;
                scriptableObject$SlotArr = scriptableObject$SlotArr2;
            }
        }
        scriptableObject$Slot = i2 == 4 ? new ScriptableObject$GetterSlot(str, i, 0) : new ScriptableObject$Slot(str, i, 0);
        if (i2 == 3) {
            scriptableObject$Slot.setAttributes(13);
        }
        this.count++;
        if (this.lastAdded != null) {
            this.lastAdded.orderedNext = scriptableObject$Slot;
        }
        if (this.firstAdded == null) {
            this.firstAdded = scriptableObject$Slot;
        }
        this.lastAdded = scriptableObject$Slot;
        addKnownAbsentSlot(scriptableObject$SlotArr, scriptableObject$Slot, slotIndex);
        return scriptableObject$Slot;
    }

    private synchronized void removeSlot(String str, int i) {
        if (str != null) {
            i = str.hashCode();
        }
        ScriptableObject$Slot[] scriptableObject$SlotArr = this.slots;
        if (this.count != 0) {
            int slotIndex = getSlotIndex(scriptableObject$SlotArr.length, i);
            ScriptableObject$Slot scriptableObject$Slot = scriptableObject$SlotArr[slotIndex];
            ScriptableObject$Slot scriptableObject$Slot2 = scriptableObject$Slot;
            while (scriptableObject$Slot2 != null && (scriptableObject$Slot2.indexOrHash != i || (scriptableObject$Slot2.name != str && (str == null || !str.equals(scriptableObject$Slot2.name))))) {
                scriptableObject$Slot = scriptableObject$Slot2;
                scriptableObject$Slot2 = scriptableObject$Slot2.next;
            }
            if (scriptableObject$Slot2 != null && (scriptableObject$Slot2.getAttributes() & 4) == 0) {
                this.count--;
                if (scriptableObject$Slot == scriptableObject$Slot2) {
                    scriptableObject$SlotArr[slotIndex] = scriptableObject$Slot2.next;
                } else {
                    scriptableObject$Slot.next = scriptableObject$Slot2.next;
                }
                ScriptableObject$Slot unwrapSlot = unwrapSlot(scriptableObject$Slot2);
                if (unwrapSlot == this.firstAdded) {
                    scriptableObject$Slot = null;
                    this.firstAdded = unwrapSlot.orderedNext;
                } else {
                    scriptableObject$Slot = this.firstAdded;
                    while (scriptableObject$Slot.orderedNext != unwrapSlot) {
                        scriptableObject$Slot = scriptableObject$Slot.orderedNext;
                    }
                    scriptableObject$Slot.orderedNext = unwrapSlot.orderedNext;
                }
                if (unwrapSlot == this.lastAdded) {
                    this.lastAdded = scriptableObject$Slot;
                }
                scriptableObject$Slot2.markDeleted();
            }
        }
    }

    private static int getSlotIndex(int i, int i2) {
        return (i - 1) & i2;
    }

    private static void copyTable(ScriptableObject$Slot[] scriptableObject$SlotArr, ScriptableObject$Slot[] scriptableObject$SlotArr2, int i) {
        if (i == 0) {
            throw Kit.codeBug();
        }
        int length = scriptableObject$SlotArr2.length;
        int length2 = scriptableObject$SlotArr.length;
        while (true) {
            length2--;
            ScriptableObject$Slot scriptableObject$Slot = scriptableObject$SlotArr[length2];
            while (scriptableObject$Slot != null) {
                addKnownAbsentSlot(scriptableObject$SlotArr2, scriptableObject$Slot.next == null ? scriptableObject$Slot : new ScriptableObject$RelinkedSlot(scriptableObject$Slot), getSlotIndex(length, scriptableObject$Slot.indexOrHash));
                scriptableObject$Slot = scriptableObject$Slot.next;
                i--;
                if (i == 0) {
                    return;
                }
            }
        }
    }

    private static void addKnownAbsentSlot(ScriptableObject$Slot[] scriptableObject$SlotArr, ScriptableObject$Slot scriptableObject$Slot, int i) {
        if (scriptableObject$SlotArr[i] == null) {
            scriptableObject$SlotArr[i] = scriptableObject$Slot;
            return;
        }
        ScriptableObject$Slot scriptableObject$Slot2 = scriptableObject$SlotArr[i];
        for (ScriptableObject$Slot scriptableObject$Slot3 = scriptableObject$Slot2.next; scriptableObject$Slot3 != null; scriptableObject$Slot3 = scriptableObject$Slot3.next) {
            scriptableObject$Slot2 = scriptableObject$Slot3;
        }
        scriptableObject$Slot2.next = scriptableObject$Slot;
    }

    Object[] getIds(boolean z) {
        Object obj;
        int i;
        ScriptableObject$Slot[] scriptableObject$SlotArr = this.slots;
        int arrayLength = this.externalData == null ? 0 : this.externalData.getArrayLength();
        if (arrayLength == 0) {
            obj = ScriptRuntime.emptyArgs;
        } else {
            obj = new Object[arrayLength];
            for (i = 0; i < arrayLength; i++) {
                obj[i] = Integer.valueOf(i);
            }
        }
        if (scriptableObject$SlotArr == null) {
            return obj;
        }
        ScriptableObject$Slot scriptableObject$Slot = this.firstAdded;
        while (scriptableObject$Slot != null && scriptableObject$Slot.wasDeleted) {
            scriptableObject$Slot = scriptableObject$Slot.orderedNext;
        }
        ScriptableObject$Slot scriptableObject$Slot2 = scriptableObject$Slot;
        int i2 = arrayLength;
        while (scriptableObject$Slot2 != null) {
            Object obj2;
            if (z || (scriptableObject$Slot2.getAttributes() & 2) == 0) {
                if (i2 == arrayLength) {
                    obj2 = new Object[(scriptableObject$SlotArr.length + arrayLength)];
                    if (obj != null) {
                        System.arraycopy(obj, 0, obj2, 0, arrayLength);
                    }
                } else {
                    obj2 = obj;
                }
                int i3 = i2 + 1;
                obj2[i2] = scriptableObject$Slot2.name != null ? scriptableObject$Slot2.name : Integer.valueOf(scriptableObject$Slot2.indexOrHash);
                obj = obj2;
                i = i3;
            } else {
                i = i2;
            }
            scriptableObject$Slot = scriptableObject$Slot2.orderedNext;
            while (scriptableObject$Slot != null && scriptableObject$Slot.wasDeleted) {
                scriptableObject$Slot = scriptableObject$Slot.orderedNext;
            }
            scriptableObject$Slot2 = scriptableObject$Slot;
            i2 = i;
        }
        if (i2 == obj.length + arrayLength) {
            return obj;
        }
        obj2 = new Object[i2];
        System.arraycopy(obj, 0, obj2, 0, i2);
        return obj2;
    }

    private synchronized void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        int i = this.count;
        if (i < 0) {
            i ^= -1;
        }
        if (i == 0) {
            objectOutputStream.writeInt(0);
        } else {
            objectOutputStream.writeInt(this.slots.length);
            ScriptableObject$Slot scriptableObject$Slot = this.firstAdded;
            while (scriptableObject$Slot != null && scriptableObject$Slot.wasDeleted) {
                scriptableObject$Slot = scriptableObject$Slot.orderedNext;
            }
            this.firstAdded = scriptableObject$Slot;
            ScriptableObject$Slot scriptableObject$Slot2 = scriptableObject$Slot;
            while (scriptableObject$Slot2 != null) {
                objectOutputStream.writeObject(scriptableObject$Slot2);
                scriptableObject$Slot = scriptableObject$Slot2.orderedNext;
                while (scriptableObject$Slot != null && scriptableObject$Slot.wasDeleted) {
                    scriptableObject$Slot = scriptableObject$Slot.orderedNext;
                }
                scriptableObject$Slot2.orderedNext = scriptableObject$Slot;
                scriptableObject$Slot2 = scriptableObject$Slot;
            }
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt != 0) {
            int i;
            int i2;
            if (((readInt - 1) & readInt) != 0) {
                if (readInt > 1073741824) {
                    throw new RuntimeException("Property table overflow");
                }
                i = 4;
                while (i < readInt) {
                    i <<= 1;
                }
                readInt = i;
            }
            this.slots = new ScriptableObject$Slot[readInt];
            i = this.count;
            if (i < 0) {
                i2 = i ^ -1;
            } else {
                i2 = i;
            }
            ScriptableObject$Slot scriptableObject$Slot = null;
            for (int i3 = 0; i3 != i2; i3++) {
                this.lastAdded = (ScriptableObject$Slot) objectInputStream.readObject();
                if (i3 == 0) {
                    this.firstAdded = this.lastAdded;
                } else {
                    scriptableObject$Slot.orderedNext = this.lastAdded;
                }
                addKnownAbsentSlot(this.slots, this.lastAdded, getSlotIndex(readInt, this.lastAdded.indexOrHash));
                scriptableObject$Slot = this.lastAdded;
            }
        }
    }

    protected ScriptableObject getOwnPropertyDescriptor(Context context, Object obj) {
        ScriptableObject$Slot slot = getSlot(context, obj, 1);
        if (slot == null) {
            return null;
        }
        Scriptable scriptable;
        Scriptable parentScope = getParentScope();
        if (parentScope != null) {
            scriptable = parentScope;
        }
        return slot.getPropertyDescriptor(context, scriptable);
    }

    protected ScriptableObject$Slot getSlot(Context context, Object obj, int i) {
        String toStringIdOrIndex = ScriptRuntime.toStringIdOrIndex(context, obj);
        if (toStringIdOrIndex == null) {
            return getSlot(null, ScriptRuntime.lastIndexResult(context), i);
        }
        return getSlot(toStringIdOrIndex, 0, i);
    }

    public int size() {
        return this.count < 0 ? this.count ^ -1 : this.count;
    }

    public boolean isEmpty() {
        return this.count == 0 || this.count == -1;
    }

    public Object get(Object obj) {
        Object obj2;
        if (obj instanceof String) {
            obj2 = get((String) obj, (Scriptable) this);
        } else if (obj instanceof Number) {
            obj2 = get(((Number) obj).intValue(), (Scriptable) this);
        } else {
            obj2 = null;
        }
        if (obj2 == Scriptable.NOT_FOUND || obj2 == Undefined.instance) {
            return null;
        }
        if (obj2 instanceof Wrapper) {
            return ((Wrapper) obj2).unwrap();
        }
        return obj2;
    }
}
