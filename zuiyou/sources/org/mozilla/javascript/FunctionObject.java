package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class FunctionObject extends BaseFunction {
    public static final int JAVA_BOOLEAN_TYPE = 3;
    public static final int JAVA_DOUBLE_TYPE = 4;
    public static final int JAVA_INT_TYPE = 2;
    public static final int JAVA_OBJECT_TYPE = 6;
    public static final int JAVA_SCRIPTABLE_TYPE = 5;
    public static final int JAVA_STRING_TYPE = 1;
    public static final int JAVA_UNSUPPORTED_TYPE = 0;
    private static final short VARARGS_CTOR = (short) -2;
    private static final short VARARGS_METHOD = (short) -1;
    private static boolean sawSecurityException = false;
    static final long serialVersionUID = -5332312783643935019L;
    private String functionName;
    private transient boolean hasVoidReturn;
    private boolean isStatic;
    MemberBox member;
    private int parmsLength;
    private transient int returnTypeTag;
    private transient byte[] typeTags;

    public FunctionObject(String str, Member member, Scriptable scriptable) {
        int i = 0;
        if (member instanceof Constructor) {
            this.member = new MemberBox((Constructor) member);
            this.isStatic = true;
        } else {
            this.member = new MemberBox((Method) member);
            this.isStatic = this.member.isStatic();
        }
        String name = this.member.getName();
        this.functionName = str;
        Class[] clsArr = this.member.argTypes;
        int length = clsArr.length;
        if (length != 4 || (!clsArr[1].isArray() && !clsArr[2].isArray())) {
            this.parmsLength = length;
            if (length > 0) {
                this.typeTags = new byte[length];
                while (i != length) {
                    int typeTag = getTypeTag(clsArr[i]);
                    if (typeTag == 0) {
                        throw Context.reportRuntimeError2("msg.bad.parms", clsArr[i].getName(), name);
                    }
                    this.typeTags[i] = (byte) typeTag;
                    i++;
                }
            }
        } else if (clsArr[1].isArray()) {
            if (this.isStatic && clsArr[0] == ScriptRuntime.ContextClass && clsArr[1].getComponentType() == ScriptRuntime.ObjectClass && clsArr[2] == ScriptRuntime.FunctionClass && clsArr[3] == Boolean.TYPE) {
                this.parmsLength = -2;
            } else {
                throw Context.reportRuntimeError1("msg.varargs.ctor", name);
            }
        } else if (this.isStatic && clsArr[0] == ScriptRuntime.ContextClass && clsArr[1] == ScriptRuntime.ScriptableClass && clsArr[2].getComponentType() == ScriptRuntime.ObjectClass && clsArr[3] == ScriptRuntime.FunctionClass) {
            this.parmsLength = -1;
        } else {
            throw Context.reportRuntimeError1("msg.varargs.fun", name);
        }
        Class returnType;
        if (this.member.isMethod()) {
            returnType = this.member.method().getReturnType();
            if (returnType == Void.TYPE) {
                this.hasVoidReturn = true;
            } else {
                this.returnTypeTag = getTypeTag(returnType);
            }
        } else {
            returnType = this.member.getDeclaringClass();
            if (!ScriptRuntime.ScriptableClass.isAssignableFrom(returnType)) {
                throw Context.reportRuntimeError1("msg.bad.ctor.return", returnType.getName());
            }
        }
        ScriptRuntime.setFunctionProtoAndParent(this, scriptable);
    }

    public static int getTypeTag(Class<?> cls) {
        if (cls == ScriptRuntime.StringClass) {
            return 1;
        }
        if (cls == ScriptRuntime.IntegerClass || cls == Integer.TYPE) {
            return 2;
        }
        if (cls == ScriptRuntime.BooleanClass || cls == Boolean.TYPE) {
            return 3;
        }
        if (cls == ScriptRuntime.DoubleClass || cls == Double.TYPE) {
            return 4;
        }
        if (ScriptRuntime.ScriptableClass.isAssignableFrom(cls)) {
            return 5;
        }
        if (cls == ScriptRuntime.ObjectClass) {
            return 6;
        }
        return 0;
    }

    public static Object convertArg(Context context, Scriptable scriptable, Object obj, int i) {
        switch (i) {
            case 1:
                if (obj instanceof String) {
                    return obj;
                }
                return ScriptRuntime.toString(obj);
            case 2:
                if (obj instanceof Integer) {
                    return obj;
                }
                return Integer.valueOf(ScriptRuntime.toInt32(obj));
            case 3:
                if (obj instanceof Boolean) {
                    return obj;
                }
                return ScriptRuntime.toBoolean(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                if (obj instanceof Double) {
                    return obj;
                }
                return new Double(ScriptRuntime.toNumber(obj));
            case 5:
                return ScriptRuntime.toObjectOrNull(context, obj, scriptable);
            case 6:
                return obj;
            default:
                throw new IllegalArgumentException();
        }
    }

    public int getArity() {
        return this.parmsLength < 0 ? 1 : this.parmsLength;
    }

    public int getLength() {
        return getArity();
    }

    public String getFunctionName() {
        return this.functionName == null ? "" : this.functionName;
    }

    public Member getMethodOrConstructor() {
        if (this.member.isMethod()) {
            return this.member.method();
        }
        return this.member.ctor();
    }

    static Method findSingleMethod(Method[] methodArr, String str) {
        Method method = null;
        int length = methodArr.length;
        int i = 0;
        while (i != length) {
            Method method2 = methodArr[i];
            if (method2 == null || !str.equals(method2.getName())) {
                method2 = method;
            } else if (method != null) {
                throw Context.reportRuntimeError2("msg.no.overload", str, method2.getDeclaringClass().getName());
            }
            i++;
            method = method2;
        }
        return method;
    }

    static Method[] getMethodList(Class<?> cls) {
        Method[] declaredMethods;
        int i;
        int i2;
        Method[] methodArr;
        int i3;
        int i4 = 0;
        try {
            if (!sawSecurityException) {
                declaredMethods = cls.getDeclaredMethods();
                if (declaredMethods == null) {
                    declaredMethods = cls.getMethods();
                }
                i = 0;
                i2 = 0;
                while (i < declaredMethods.length) {
                    if (sawSecurityException ? !Modifier.isPublic(declaredMethods[i].getModifiers()) : declaredMethods[i].getDeclaringClass() != cls) {
                        declaredMethods[i] = null;
                    } else {
                        i2++;
                    }
                    i++;
                }
                methodArr = new Method[i2];
                i3 = 0;
                while (i4 < declaredMethods.length) {
                    if (declaredMethods[i4] != null) {
                        i = i3 + 1;
                        methodArr[i3] = declaredMethods[i4];
                        i3 = i;
                    }
                    i4++;
                }
                return methodArr;
            }
        } catch (SecurityException e) {
            sawSecurityException = true;
        }
        declaredMethods = null;
        if (declaredMethods == null) {
            declaredMethods = cls.getMethods();
        }
        i = 0;
        i2 = 0;
        while (i < declaredMethods.length) {
            if (sawSecurityException) {
            }
            declaredMethods[i] = null;
            i++;
        }
        methodArr = new Method[i2];
        i3 = 0;
        while (i4 < declaredMethods.length) {
            if (declaredMethods[i4] != null) {
                i = i3 + 1;
                methodArr[i3] = declaredMethods[i4];
                i3 = i;
            }
            i4++;
        }
        return methodArr;
    }

    public void addAsConstructor(Scriptable scriptable, Scriptable scriptable2) {
        initAsConstructor(scriptable, scriptable2);
        defineProperty(scriptable, scriptable2.getClassName(), this, 2);
    }

    void initAsConstructor(Scriptable scriptable, Scriptable scriptable2) {
        ScriptRuntime.setFunctionProtoAndParent(this, scriptable);
        setImmunePrototypeProperty(scriptable2);
        scriptable2.setParentScope(this);
        defineProperty(scriptable2, "constructor", this, 7);
        setParentScope(scriptable);
    }

    @Deprecated
    public static Object convertArg(Context context, Scriptable scriptable, Object obj, Class<?> cls) {
        int typeTag = getTypeTag(cls);
        if (typeTag != 0) {
            return convertArg(context, scriptable, obj, typeTag);
        }
        throw Context.reportRuntimeError1("msg.cant.convert", cls.getName());
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        int i;
        Object invoke;
        int i2 = 0;
        int length = objArr.length;
        for (i = 0; i < length; i++) {
            if (objArr[i] instanceof ConsString) {
                objArr[i] = objArr[i].toString();
            }
        }
        if (this.parmsLength >= 0) {
            Object[] objArr2;
            if (!this.isStatic) {
                Class declaringClass = this.member.getDeclaringClass();
                if (!declaringClass.isInstance(scriptable2)) {
                    boolean isInstance;
                    if (scriptable2 == scriptable) {
                        Scriptable parentScope = getParentScope();
                        if (scriptable != parentScope) {
                            isInstance = declaringClass.isInstance(parentScope);
                            if (isInstance) {
                                scriptable2 = parentScope;
                            }
                            if (!isInstance) {
                                throw ScriptRuntime.typeError1("msg.incompat.call", this.functionName);
                            }
                        }
                    }
                    isInstance = false;
                    if (isInstance) {
                        throw ScriptRuntime.typeError1("msg.incompat.call", this.functionName);
                    }
                }
            }
            if (this.parmsLength == length) {
                int i3 = 0;
                objArr2 = objArr;
                while (i3 != this.parmsLength) {
                    Object obj = objArr[i3];
                    Object convertArg = convertArg(context, scriptable, obj, this.typeTags[i3]);
                    if (obj != convertArg) {
                        if (objArr2 == objArr) {
                            objArr2 = (Object[]) objArr.clone();
                        }
                        objArr2[i3] = convertArg;
                    }
                    i3++;
                    objArr2 = objArr2;
                }
            } else if (this.parmsLength == 0) {
                objArr2 = ScriptRuntime.emptyArgs;
            } else {
                Object[] objArr3 = new Object[this.parmsLength];
                i = 0;
                while (i != this.parmsLength) {
                    objArr3[i] = convertArg(context, scriptable, i < length ? objArr[i] : Undefined.instance, this.typeTags[i]);
                    i++;
                }
                objArr2 = objArr3;
            }
            if (this.member.isMethod()) {
                invoke = this.member.invoke(scriptable2, objArr2);
                i2 = 1;
            } else {
                invoke = this.member.newInstance(objArr2);
            }
        } else if (this.parmsLength == -1) {
            invoke = this.member.invoke(null, new Object[]{context, scriptable2, objArr, this});
            i2 = 1;
        } else {
            if (scriptable2 == null) {
                i = 1;
            } else {
                i = 0;
            }
            Boolean bool = i != 0 ? Boolean.TRUE : Boolean.FALSE;
            Object[] objArr4 = new Object[]{context, objArr, this, bool};
            invoke = this.member.isCtor() ? this.member.newInstance(objArr4) : this.member.invoke(null, objArr4);
        }
        if (i2 == 0) {
            return invoke;
        }
        if (this.hasVoidReturn) {
            return Undefined.instance;
        }
        if (this.returnTypeTag == 0) {
            return context.getWrapFactory().wrap(context, scriptable, invoke, null);
        }
        return invoke;
    }

    public Scriptable createObject(Context context, Scriptable scriptable) {
        if (this.member.isCtor() || this.parmsLength == -2) {
            return null;
        }
        try {
            Scriptable scriptable2 = (Scriptable) this.member.getDeclaringClass().newInstance();
            scriptable2.setPrototype(getClassPrototype());
            scriptable2.setParentScope(getParentScope());
            return scriptable2;
        } catch (Throwable e) {
            throw Context.throwAsScriptRuntimeEx(e);
        }
    }

    boolean isVarArgsMethod() {
        return this.parmsLength == -1;
    }

    boolean isVarArgsConstructor() {
        return this.parmsLength == -2;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        if (this.parmsLength > 0) {
            Class[] clsArr = this.member.argTypes;
            this.typeTags = new byte[this.parmsLength];
            for (int i = 0; i != this.parmsLength; i++) {
                this.typeTags[i] = (byte) getTypeTag(clsArr[i]);
            }
        }
        if (this.member.isMethod()) {
            Class returnType = this.member.method().getReturnType();
            if (returnType == Void.TYPE) {
                this.hasVoidReturn = true;
            } else {
                this.returnTypeTag = getTypeTag(returnType);
            }
        }
    }
}
