package org.mozilla.javascript;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.Map;

public class NativeJavaClass extends NativeJavaObject implements Function {
    static final String javaClassPropertyName = "__javaObject__";
    static final long serialVersionUID = -6460763940409461664L;
    private Map<String, FieldAndMethods> staticFieldAndMethods;

    public NativeJavaClass(Scriptable scriptable, Class<?> cls) {
        this(scriptable, cls, false);
    }

    public NativeJavaClass(Scriptable scriptable, Class<?> cls, boolean z) {
        super(scriptable, cls, null, z);
    }

    protected void initMembers() {
        Class cls = (Class) this.javaObject;
        this.members = JavaMembers.lookupClass(this.parent, cls, cls, this.isAdapter);
        this.staticFieldAndMethods = this.members.getFieldAndMethodsObjects(this, cls, true);
    }

    public String getClassName() {
        return "JavaClass";
    }

    public boolean has(String str, Scriptable scriptable) {
        return this.members.has(str, true) || javaClassPropertyName.equals(str);
    }

    public Object get(String str, Scriptable scriptable) {
        if (str.equals("prototype")) {
            return null;
        }
        Object obj;
        if (this.staticFieldAndMethods != null) {
            obj = this.staticFieldAndMethods.get(str);
            if (obj != null) {
                return obj;
            }
        }
        if (this.members.has(str, true)) {
            return this.members.get(this, str, this.javaObject, true);
        }
        Context context = Context.getContext();
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        WrapFactory wrapFactory = context.getWrapFactory();
        if (javaClassPropertyName.equals(str)) {
            return wrapFactory.wrap(context, topLevelScope, this.javaObject, ScriptRuntime.ClassClass);
        }
        Class findNestedClass = findNestedClass(getClassObject(), str);
        if (findNestedClass != null) {
            obj = wrapFactory.wrapJavaClass(context, topLevelScope, findNestedClass);
            obj.setParentScope(this);
            return obj;
        }
        throw this.members.reportMemberNotFound(str);
    }

    public void put(String str, Scriptable scriptable, Object obj) {
        this.members.put(this, str, this.javaObject, obj, true);
    }

    public Object[] getIds() {
        return this.members.getIds(true);
    }

    public Class<?> getClassObject() {
        return (Class) super.unwrap();
    }

    public Object getDefaultValue(Class<?> cls) {
        if (cls == null || cls == ScriptRuntime.StringClass) {
            return toString();
        }
        if (cls == ScriptRuntime.BooleanClass) {
            return Boolean.TRUE;
        }
        if (cls == ScriptRuntime.NumberClass) {
            return ScriptRuntime.NaNobj;
        }
        return this;
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (objArr.length != 1 || !(objArr[0] instanceof Scriptable)) {
            return construct(context, scriptable, objArr);
        }
        Class classObject = getClassObject();
        Object obj = (Scriptable) objArr[0];
        do {
            if ((obj instanceof Wrapper) && classObject.isInstance(((Wrapper) obj).unwrap())) {
                return obj;
            }
            obj = obj.getPrototype();
        } while (obj != null);
        return construct(context, scriptable, objArr);
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        Class classObject = getClassObject();
        int modifiers = classObject.getModifiers();
        if (!Modifier.isInterface(modifiers) && !Modifier.isAbstract(modifiers)) {
            NativeJavaMethod nativeJavaMethod = this.members.ctors;
            int findCachedFunction = nativeJavaMethod.findCachedFunction(context, objArr);
            if (findCachedFunction >= 0) {
                return constructSpecific(context, scriptable, objArr, nativeJavaMethod.methods[findCachedFunction]);
            }
            throw Context.reportRuntimeError2("msg.no.java.ctor", classObject.getName(), NativeJavaMethod.scriptSignature(objArr));
        } else if (objArr.length == 0) {
            throw Context.reportRuntimeError0("msg.adapter.zero.args");
        } else {
            Scriptable topLevelScope = ScriptableObject.getTopLevelScope(this);
            String str = "";
            Object obj;
            try {
                if ("Dalvik".equals(System.getProperty("java.vm.name")) && classObject.isInterface()) {
                    return context.getWrapFactory().wrapAsJavaObject(context, scriptable, createInterfaceAdapter(classObject, ScriptableObject.ensureScriptableObject(objArr[0])), null);
                }
                obj = topLevelScope.get("JavaAdapter", topLevelScope);
                if (obj != NOT_FOUND) {
                    return ((Function) obj).construct(context, topLevelScope, new Object[]{this, objArr[0]});
                }
                obj = str;
                throw Context.reportRuntimeError2("msg.cant.instantiate", obj, classObject.getName());
            } catch (Exception e) {
                obj = e.getMessage();
                if (obj == null) {
                    String str2 = str;
                }
            }
        }
    }

    static Scriptable constructSpecific(Context context, Scriptable scriptable, Object[] objArr, MemberBox memberBox) {
        Object constructInternal = constructInternal(objArr, memberBox);
        return context.getWrapFactory().wrapNewObject(context, ScriptableObject.getTopLevelScope(scriptable), constructInternal);
    }

    static Object constructInternal(Object[] objArr, MemberBox memberBox) {
        Object[] objArr2;
        int i = 0;
        Class[] clsArr = memberBox.argTypes;
        if (memberBox.vararg) {
            Object jsToJava;
            Object[] objArr3 = new Object[clsArr.length];
            for (int i2 = 0; i2 < clsArr.length - 1; i2++) {
                objArr3[i2] = Context.jsToJava(objArr[i2], clsArr[i2]);
            }
            if (objArr.length == clsArr.length && (objArr[objArr.length - 1] == null || (objArr[objArr.length - 1] instanceof NativeArray) || (objArr[objArr.length - 1] instanceof NativeJavaArray))) {
                jsToJava = Context.jsToJava(objArr[objArr.length - 1], clsArr[clsArr.length - 1]);
            } else {
                Class componentType = clsArr[clsArr.length - 1].getComponentType();
                jsToJava = Array.newInstance(componentType, (objArr.length - clsArr.length) + 1);
                while (i < Array.getLength(jsToJava)) {
                    Array.set(jsToJava, i, Context.jsToJava(objArr[(clsArr.length - 1) + i], componentType));
                    i++;
                }
            }
            objArr3[clsArr.length - 1] = jsToJava;
            objArr2 = objArr3;
        } else {
            objArr2 = objArr;
            while (i < objArr2.length) {
                Object obj = objArr2[i];
                Object jsToJava2 = Context.jsToJava(obj, clsArr[i]);
                if (jsToJava2 != obj) {
                    if (objArr2 == objArr) {
                        objArr2 = (Object[]) objArr.clone();
                    }
                    objArr2[i] = jsToJava2;
                }
                i++;
            }
        }
        return memberBox.newInstance(objArr2);
    }

    public String toString() {
        return "[JavaClass " + getClassObject().getName() + "]";
    }

    public boolean hasInstance(Scriptable scriptable) {
        if (!(scriptable instanceof Wrapper) || (scriptable instanceof NativeJavaClass)) {
            return false;
        }
        return getClassObject().isInstance(((Wrapper) scriptable).unwrap());
    }

    private static Class<?> findNestedClass(Class<?> cls, String str) {
        String str2 = cls.getName() + '$' + str;
        ClassLoader classLoader = cls.getClassLoader();
        if (classLoader == null) {
            return Kit.classOrNull(str2);
        }
        return Kit.classOrNull(classLoader, str2);
    }
}
