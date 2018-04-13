package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import org.mozilla.classfile.ClassFileWriter;
import org.mozilla.javascript.ObjToIntMap.Iterator;

public final class JavaAdapter implements IdFunctionCall {
    private static final Object FTAG = "JavaAdapter";
    private static final int Id_JavaAdapter = 1;

    static class JavaAdapterSignature {
        Class<?>[] interfaces;
        ObjToIntMap names;
        Class<?> superClass;

        JavaAdapterSignature(Class<?> cls, Class<?>[] clsArr, ObjToIntMap objToIntMap) {
            this.superClass = cls;
            this.interfaces = clsArr;
            this.names = objToIntMap;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof JavaAdapterSignature)) {
                return false;
            }
            JavaAdapterSignature javaAdapterSignature = (JavaAdapterSignature) obj;
            if (this.superClass != javaAdapterSignature.superClass) {
                return false;
            }
            if (this.interfaces != javaAdapterSignature.interfaces) {
                if (this.interfaces.length != javaAdapterSignature.interfaces.length) {
                    return false;
                }
                for (int i = 0; i < this.interfaces.length; i++) {
                    if (this.interfaces[i] != javaAdapterSignature.interfaces[i]) {
                        return false;
                    }
                }
            }
            if (this.names.size() != javaAdapterSignature.names.size()) {
                return false;
            }
            Iterator iterator = new Iterator(this.names);
            iterator.start();
            while (!iterator.done()) {
                String str = (String) iterator.getKey();
                int value = iterator.getValue();
                if (value != javaAdapterSignature.names.get(str, value + 1)) {
                    return false;
                }
                iterator.next();
            }
            return true;
        }

        public int hashCode() {
            return (this.superClass.hashCode() + Arrays.hashCode(this.interfaces)) ^ this.names.size();
        }
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        IdFunctionObject idFunctionObject = new IdFunctionObject(new JavaAdapter(), FTAG, 1, "JavaAdapter", 1, scriptable);
        idFunctionObject.markAsConstructor(null);
        if (z) {
            idFunctionObject.sealObject();
        }
        idFunctionObject.exportAsScopeProperty();
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (idFunctionObject.hasTag(FTAG) && idFunctionObject.methodId() == 1) {
            return js_createAdapter(context, scriptable, objArr);
        }
        throw idFunctionObject.unknown();
    }

    public static Object convertResult(Object obj, Class<?> cls) {
        if (obj != Undefined.instance || cls == ScriptRuntime.ObjectClass || cls == ScriptRuntime.StringClass) {
            return Context.jsToJava(obj, cls);
        }
        return null;
    }

    public static Scriptable createAdapterWrapper(Scriptable scriptable, Object obj) {
        Scriptable nativeJavaObject = new NativeJavaObject(ScriptableObject.getTopLevelScope(scriptable), obj, null, true);
        nativeJavaObject.setPrototype(scriptable);
        return nativeJavaObject;
    }

    public static Object getAdapterSelf(Class<?> cls, Object obj) throws NoSuchFieldException, IllegalAccessException {
        return cls.getDeclaredField("self").get(obj);
    }

    static Object js_createAdapter(Context context, Scriptable scriptable, Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            throw ScriptRuntime.typeError0("msg.adapter.zero.args");
        }
        Object obj;
        Class classObject;
        int i;
        int i2 = 0;
        while (i2 < length - 1) {
            obj = objArr[i2];
            if (obj instanceof NativeObject) {
                break;
            } else if (obj instanceof NativeJavaClass) {
                i2++;
            } else {
                throw ScriptRuntime.typeError2("msg.not.java.class.arg", String.valueOf(i2), ScriptRuntime.toString(obj));
            }
        }
        Class cls = null;
        Object obj2 = new Class[i2];
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            Class cls2;
            classObject = ((NativeJavaClass) objArr[i3]).getClassObject();
            if (classObject.isInterface()) {
                i = i4 + 1;
                obj2[i4] = classObject;
                cls2 = cls;
            } else if (cls != null) {
                throw ScriptRuntime.typeError2("msg.only.one.super", cls.getName(), classObject.getName());
            } else {
                i = i4;
                cls2 = classObject;
            }
            i3++;
            cls = cls2;
            i4 = i;
        }
        if (cls == null) {
            cls = ScriptRuntime.ObjectClass;
        }
        obj = new Class[i4];
        System.arraycopy(obj2, 0, obj, 0, i4);
        Scriptable ensureScriptable = ScriptableObject.ensureScriptable(objArr[i2]);
        classObject = getAdapterClass(scriptable, cls, obj, ensureScriptable);
        i = (length - i2) - 1;
        if (i > 0) {
            try {
                Object obj3 = new Object[(i + 2)];
                obj3[0] = ensureScriptable;
                obj3[1] = context.getFactory();
                System.arraycopy(objArr, i2 + 1, obj3, 2, i);
                NativeJavaMethod nativeJavaMethod = new NativeJavaClass(scriptable, classObject, true).members.ctors;
                i4 = nativeJavaMethod.findCachedFunction(context, obj3);
                if (i4 < 0) {
                    throw Context.reportRuntimeError2("msg.no.java.ctor", classObject.getName(), NativeJavaMethod.scriptSignature(objArr));
                }
                obj = NativeJavaClass.constructInternal(obj3, nativeJavaMethod.methods[i4]);
            } catch (Throwable e) {
                throw Context.throwAsScriptRuntimeEx(e);
            }
        }
        Object[] objArr2 = new Object[]{ensureScriptable, context.getFactory()};
        obj = classObject.getConstructor(new Class[]{ScriptRuntime.ScriptableClass, ScriptRuntime.ContextFactoryClass}).newInstance(objArr2);
        Object adapterSelf = getAdapterSelf(classObject, obj);
        if (adapterSelf instanceof Wrapper) {
            Object unwrap = ((Wrapper) adapterSelf).unwrap();
            if (unwrap instanceof Scriptable) {
                if (!(unwrap instanceof ScriptableObject)) {
                    return unwrap;
                }
                ScriptRuntime.setObjectProtoAndParent((ScriptableObject) unwrap, scriptable);
                return unwrap;
            }
        }
        return adapterSelf;
    }

    public static void writeAdapterObject(Object obj, ObjectOutputStream objectOutputStream) throws IOException {
        Class cls = obj.getClass();
        objectOutputStream.writeObject(cls.getSuperclass().getName());
        Class[] interfaces = cls.getInterfaces();
        Object obj2 = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            obj2[i] = interfaces[i].getName();
        }
        objectOutputStream.writeObject(obj2);
        try {
            objectOutputStream.writeObject(cls.getField("delegee").get(obj));
        } catch (IllegalAccessException e) {
            throw new IOException();
        } catch (NoSuchFieldException e2) {
            throw new IOException();
        }
    }

    public static Object readAdapterObject(Scriptable scriptable, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Object factory;
        Context currentContext = Context.getCurrentContext();
        if (currentContext != null) {
            factory = currentContext.getFactory();
        } else {
            factory = null;
        }
        Class cls = Class.forName((String) objectInputStream.readObject());
        String[] strArr = (String[]) objectInputStream.readObject();
        Class[] clsArr = new Class[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            clsArr[i] = Class.forName(strArr[i]);
        }
        Class adapterClass = getAdapterClass(scriptable, cls, clsArr, (Scriptable) objectInputStream.readObject());
        try {
            return adapterClass.getConstructor(new Class[]{ScriptRuntime.ContextFactoryClass, ScriptRuntime.ScriptableClass, ScriptRuntime.ScriptableClass}).newInstance(new Object[]{factory, r0, scriptable});
        } catch (InstantiationException e) {
            throw new ClassNotFoundException("adapter");
        } catch (IllegalAccessException e2) {
            throw new ClassNotFoundException("adapter");
        } catch (InvocationTargetException e3) {
            throw new ClassNotFoundException("adapter");
        } catch (NoSuchMethodException e4) {
            throw new ClassNotFoundException("adapter");
        }
    }

    private static ObjToIntMap getObjectFunctionNames(Scriptable scriptable) {
        Object[] propertyIds = ScriptableObject.getPropertyIds(scriptable);
        ObjToIntMap objToIntMap = new ObjToIntMap(propertyIds.length);
        for (int i = 0; i != propertyIds.length; i++) {
            if (propertyIds[i] instanceof String) {
                String str = (String) propertyIds[i];
                Object property = ScriptableObject.getProperty(scriptable, str);
                if (property instanceof Function) {
                    int toInt32 = ScriptRuntime.toInt32(ScriptableObject.getProperty((Function) property, "length"));
                    if (toInt32 < 0) {
                        toInt32 = 0;
                    }
                    objToIntMap.put(str, toInt32);
                }
            }
        }
        return objToIntMap;
    }

    private static Class<?> getAdapterClass(Scriptable scriptable, Class<?> cls, Class<?>[] clsArr, Scriptable scriptable2) {
        ClassCache classCache = ClassCache.get(scriptable);
        Map interfaceAdapterCacheMap = classCache.getInterfaceAdapterCacheMap();
        ObjToIntMap objectFunctionNames = getObjectFunctionNames(scriptable2);
        JavaAdapterSignature javaAdapterSignature = new JavaAdapterSignature(cls, clsArr, objectFunctionNames);
        Class<?> cls2 = (Class) interfaceAdapterCacheMap.get(javaAdapterSignature);
        if (cls2 == null) {
            String str = "adapter" + classCache.newClassSerialNumber();
            cls2 = loadAdapterClass(str, createAdapterCode(objectFunctionNames, str, cls, clsArr, null));
            if (classCache.isCachingEnabled()) {
                interfaceAdapterCacheMap.put(javaAdapterSignature, cls2);
            }
        }
        return cls2;
    }

    public static byte[] createAdapterCode(ObjToIntMap objToIntMap, String str, Class<?> cls, Class<?>[] clsArr, String str2) {
        int i;
        String name;
        Class[] parameterTypes;
        ClassFileWriter classFileWriter = new ClassFileWriter(str, cls.getName(), "<adapter>");
        classFileWriter.addField("factory", "Lorg/mozilla/javascript/ContextFactory;", (short) 17);
        classFileWriter.addField("delegee", "Lorg/mozilla/javascript/Scriptable;", (short) 17);
        classFileWriter.addField("self", "Lorg/mozilla/javascript/Scriptable;", (short) 17);
        int length = clsArr == null ? 0 : clsArr.length;
        for (i = 0; i < length; i++) {
            if (clsArr[i] != null) {
                classFileWriter.addInterface(clsArr[i].getName());
            }
        }
        String replace = cls.getName().replace('.', '/');
        for (Constructor constructor : cls.getDeclaredConstructors()) {
            int modifiers = constructor.getModifiers();
            if (Modifier.isPublic(modifiers) || Modifier.isProtected(modifiers)) {
                generateCtor(classFileWriter, str, replace, constructor);
            }
        }
        generateSerialCtor(classFileWriter, str, replace);
        if (str2 != null) {
            generateEmptyCtor(classFileWriter, str, replace, str2);
        }
        ObjToIntMap objToIntMap2 = new ObjToIntMap();
        ObjToIntMap objToIntMap3 = new ObjToIntMap();
        for (int i2 = 0; i2 < length; i2++) {
            Method[] methods = clsArr[i2].getMethods();
            for (Method method : methods) {
                int modifiers2 = method.getModifiers();
                if (!(Modifier.isStatic(modifiers2) || Modifier.isFinal(modifiers2))) {
                    name = method.getName();
                    parameterTypes = method.getParameterTypes();
                    if (!objToIntMap.has(name)) {
                        try {
                            cls.getMethod(name, parameterTypes);
                        } catch (NoSuchMethodException e) {
                        }
                    }
                    String str3 = name + getMethodSignature(method, parameterTypes);
                    if (!objToIntMap2.has(str3)) {
                        generateMethod(classFileWriter, str, name, parameterTypes, method.getReturnType(), true);
                        objToIntMap2.put(str3, 0);
                        objToIntMap3.put(name, 0);
                    }
                }
            }
        }
        Method[] overridableMethods = getOverridableMethods(cls);
        for (Method method2 : overridableMethods) {
            boolean isAbstract = Modifier.isAbstract(method2.getModifiers());
            name = method2.getName();
            if (isAbstract || objToIntMap.has(name)) {
                parameterTypes = method2.getParameterTypes();
                String methodSignature = getMethodSignature(method2, parameterTypes);
                String str4 = name + methodSignature;
                if (!objToIntMap2.has(str4)) {
                    generateMethod(classFileWriter, str, name, parameterTypes, method2.getReturnType(), true);
                    objToIntMap2.put(str4, 0);
                    objToIntMap3.put(name, 0);
                    if (!isAbstract) {
                        generateSuper(classFileWriter, str, replace, name, methodSignature, parameterTypes, method2.getReturnType());
                    }
                }
            }
        }
        Iterator iterator = new Iterator(objToIntMap);
        iterator.start();
        while (!iterator.done()) {
            name = (String) iterator.getKey();
            if (!objToIntMap3.has(name)) {
                int value = iterator.getValue();
                parameterTypes = new Class[value];
                for (i = 0; i < value; i++) {
                    parameterTypes[i] = ScriptRuntime.ObjectClass;
                }
                generateMethod(classFileWriter, str, name, parameterTypes, ScriptRuntime.ObjectClass, false);
            }
            iterator.next();
        }
        return classFileWriter.toByteArray();
    }

    static Method[] getOverridableMethods(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            appendOverridableMethods(cls2, arrayList, hashSet);
        }
        while (cls != null) {
            for (Class appendOverridableMethods : cls.getInterfaces()) {
                appendOverridableMethods(appendOverridableMethods, arrayList, hashSet);
            }
            cls = cls.getSuperclass();
        }
        return (Method[]) arrayList.toArray(new Method[arrayList.size()]);
    }

    private static void appendOverridableMethods(Class<?> cls, ArrayList<Method> arrayList, HashSet<String> hashSet) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            String str = declaredMethods[i].getName() + getMethodSignature(declaredMethods[i], declaredMethods[i].getParameterTypes());
            if (!hashSet.contains(str)) {
                int modifiers = declaredMethods[i].getModifiers();
                if (!Modifier.isStatic(modifiers)) {
                    if (Modifier.isFinal(modifiers)) {
                        hashSet.add(str);
                    } else if (Modifier.isPublic(modifiers) || Modifier.isProtected(modifiers)) {
                        arrayList.add(declaredMethods[i]);
                        hashSet.add(str);
                    }
                }
            }
        }
    }

    static Class<?> loadAdapterClass(String str, byte[] bArr) {
        Object scriptProtectionDomain;
        Class staticSecurityDomainClass = SecurityController.getStaticSecurityDomainClass();
        if (staticSecurityDomainClass == CodeSource.class || staticSecurityDomainClass == ProtectionDomain.class) {
            scriptProtectionDomain = SecurityUtilities.getScriptProtectionDomain();
            if (scriptProtectionDomain == null) {
                scriptProtectionDomain = JavaAdapter.class.getProtectionDomain();
            }
            if (staticSecurityDomainClass == CodeSource.class) {
                scriptProtectionDomain = scriptProtectionDomain == null ? null : scriptProtectionDomain.getCodeSource();
            }
        } else {
            scriptProtectionDomain = null;
        }
        GeneratedClassLoader createLoader = SecurityController.createLoader(null, scriptProtectionDomain);
        Class<?> defineClass = createLoader.defineClass(str, bArr);
        createLoader.linkClass(defineClass);
        return defineClass;
    }

    public static Function getFunction(Scriptable scriptable, String str) {
        Object property = ScriptableObject.getProperty(scriptable, str);
        if (property == Scriptable.NOT_FOUND) {
            return null;
        }
        if (property instanceof Function) {
            return (Function) property;
        }
        throw ScriptRuntime.notFunctionError(property, str);
    }

    public static Object callMethod(ContextFactory contextFactory, Scriptable scriptable, Function function, Object[] objArr, long j) {
        if (function == null) {
            return null;
        }
        if (contextFactory == null) {
            contextFactory = ContextFactory.getGlobal();
        }
        final Scriptable parentScope = function.getParentScope();
        if (j == 0) {
            return Context.call(contextFactory, function, parentScope, scriptable, objArr);
        }
        Context currentContext = Context.getCurrentContext();
        if (currentContext != null) {
            return doCall(currentContext, parentScope, scriptable, function, objArr, j);
        }
        final Scriptable scriptable2 = scriptable;
        final Function function2 = function;
        final Object[] objArr2 = objArr;
        final long j2 = j;
        return contextFactory.call(new ContextAction() {
            public Object run(Context context) {
                return JavaAdapter.doCall(context, parentScope, scriptable2, function2, objArr2, j2);
            }
        });
    }

    private static Object doCall(Context context, Scriptable scriptable, Scriptable scriptable2, Function function, Object[] objArr, long j) {
        for (int i = 0; i != objArr.length; i++) {
            if (0 != (((long) (1 << i)) & j)) {
                Object obj = objArr[i];
                if (!(obj instanceof Scriptable)) {
                    objArr[i] = context.getWrapFactory().wrap(context, scriptable, obj, null);
                }
            }
        }
        return function.call(context, scriptable, scriptable2, objArr);
    }

    public static Scriptable runScript(final Script script) {
        return (Scriptable) ContextFactory.getGlobal().call(new ContextAction() {
            public Object run(Context context) {
                Scriptable global = ScriptRuntime.getGlobal(context);
                script.exec(context, global);
                return global;
            }
        });
    }

    private static void generateCtor(ClassFileWriter classFileWriter, String str, String str2, Constructor<?> constructor) {
        short s = (short) 3;
        int i = 0;
        Class[] parameterTypes = constructor.getParameterTypes();
        if (parameterTypes.length == 0) {
            classFileWriter.startMethod("<init>", "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/ContextFactory;)V", (short) 1);
            classFileWriter.add(42);
            classFileWriter.addInvoke(183, str2, "<init>", "()V");
        } else {
            StringBuilder stringBuilder = new StringBuilder("(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/ContextFactory;");
            int length = stringBuilder.length();
            for (Class appendTypeString : parameterTypes) {
                appendTypeString(stringBuilder, appendTypeString);
            }
            stringBuilder.append(")V");
            classFileWriter.startMethod("<init>", stringBuilder.toString(), (short) 1);
            classFileWriter.add(42);
            while (i < parameterTypes.length) {
                short generatePushParam = (short) (s + generatePushParam(classFileWriter, s, parameterTypes[i]));
                i++;
                s = generatePushParam;
            }
            stringBuilder.delete(1, length);
            classFileWriter.addInvoke(183, str2, "<init>", stringBuilder.toString());
        }
        classFileWriter.add(42);
        classFileWriter.add(43);
        classFileWriter.add(181, str, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(42);
        classFileWriter.add(44);
        classFileWriter.add(181, str, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        classFileWriter.add(42);
        classFileWriter.add(43);
        classFileWriter.add(42);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "createAdapterWrapper", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(181, str, "self", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(177);
        classFileWriter.stopMethod(s);
    }

    private static void generateSerialCtor(ClassFileWriter classFileWriter, String str, String str2) {
        classFileWriter.startMethod("<init>", "(Lorg/mozilla/javascript/ContextFactory;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;)V", (short) 1);
        classFileWriter.add(42);
        classFileWriter.addInvoke(183, str2, "<init>", "()V");
        classFileWriter.add(42);
        classFileWriter.add(43);
        classFileWriter.add(181, str, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        classFileWriter.add(42);
        classFileWriter.add(44);
        classFileWriter.add(181, str, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(42);
        classFileWriter.add(45);
        classFileWriter.add(181, str, "self", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(177);
        classFileWriter.stopMethod((short) 4);
    }

    private static void generateEmptyCtor(ClassFileWriter classFileWriter, String str, String str2, String str3) {
        classFileWriter.startMethod("<init>", "()V", (short) 1);
        classFileWriter.add(42);
        classFileWriter.addInvoke(183, str2, "<init>", "()V");
        classFileWriter.add(42);
        classFileWriter.add(1);
        classFileWriter.add(181, str, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        classFileWriter.add(187, str3);
        classFileWriter.add(89);
        classFileWriter.addInvoke(183, str3, "<init>", "()V");
        classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "runScript", "(Lorg/mozilla/javascript/Script;)Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(76);
        classFileWriter.add(42);
        classFileWriter.add(43);
        classFileWriter.add(181, str, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(42);
        classFileWriter.add(43);
        classFileWriter.add(42);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "createAdapterWrapper", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(181, str, "self", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(177);
        classFileWriter.stopMethod((short) 2);
    }

    static void generatePushWrappedArgs(ClassFileWriter classFileWriter, Class<?>[] clsArr, int i) {
        classFileWriter.addPush(i);
        classFileWriter.add(189, "java/lang/Object");
        int i2 = 1;
        for (int i3 = 0; i3 != clsArr.length; i3++) {
            classFileWriter.add(89);
            classFileWriter.addPush(i3);
            i2 += generateWrapArg(classFileWriter, i2, clsArr[i3]);
            classFileWriter.add(83);
        }
    }

    private static int generateWrapArg(ClassFileWriter classFileWriter, int i, Class<?> cls) {
        int i2 = 1;
        if (!cls.isPrimitive()) {
            classFileWriter.add(25, i);
        } else if (cls == Boolean.TYPE) {
            classFileWriter.add(187, "java/lang/Boolean");
            classFileWriter.add(89);
            classFileWriter.add(21, i);
            classFileWriter.addInvoke(183, "java/lang/Boolean", "<init>", "(Z)V");
        } else if (cls == Character.TYPE) {
            classFileWriter.add(21, i);
            classFileWriter.addInvoke(184, "java/lang/String", "valueOf", "(C)Ljava/lang/String;");
        } else {
            classFileWriter.add(187, "java/lang/Double");
            classFileWriter.add(89);
            switch (cls.getName().charAt(0)) {
                case 'b':
                case 'i':
                case 's':
                    classFileWriter.add(21, i);
                    classFileWriter.add(135);
                    break;
                case 'd':
                    classFileWriter.add(24, i);
                    i2 = 2;
                    break;
                case 'f':
                    classFileWriter.add(23, i);
                    classFileWriter.add(141);
                    break;
                case 'l':
                    classFileWriter.add(22, i);
                    classFileWriter.add(138);
                    i2 = 2;
                    break;
            }
            classFileWriter.addInvoke(183, "java/lang/Double", "<init>", "(D)V");
        }
        return i2;
    }

    static void generateReturnResult(ClassFileWriter classFileWriter, Class<?> cls, boolean z) {
        if (cls == Void.TYPE) {
            classFileWriter.add(87);
            classFileWriter.add(177);
        } else if (cls == Boolean.TYPE) {
            classFileWriter.addInvoke(184, "org/mozilla/javascript/Context", "toBoolean", "(Ljava/lang/Object;)Z");
            classFileWriter.add(172);
        } else if (cls == Character.TYPE) {
            classFileWriter.addInvoke(184, "org/mozilla/javascript/Context", "toString", "(Ljava/lang/Object;)Ljava/lang/String;");
            classFileWriter.add(3);
            classFileWriter.addInvoke(182, "java/lang/String", "charAt", "(I)C");
            classFileWriter.add(172);
        } else if (cls.isPrimitive()) {
            classFileWriter.addInvoke(184, "org/mozilla/javascript/Context", "toNumber", "(Ljava/lang/Object;)D");
            switch (cls.getName().charAt(0)) {
                case 'b':
                case 'i':
                case 's':
                    classFileWriter.add(142);
                    classFileWriter.add(172);
                    return;
                case 'd':
                    classFileWriter.add(175);
                    return;
                case 'f':
                    classFileWriter.add(144);
                    classFileWriter.add(174);
                    return;
                case 'l':
                    classFileWriter.add(143);
                    classFileWriter.add(173);
                    return;
                default:
                    throw new RuntimeException("Unexpected return type " + cls.toString());
            }
        } else {
            String name = cls.getName();
            if (z) {
                classFileWriter.addLoadConstant(name);
                classFileWriter.addInvoke(184, "java/lang/Class", "forName", "(Ljava/lang/String;)Ljava/lang/Class;");
                classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "convertResult", "(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;");
            }
            classFileWriter.add(192, name);
            classFileWriter.add(176);
        }
    }

    private static void generateMethod(ClassFileWriter classFileWriter, String str, String str2, Class<?>[] clsArr, Class<?> cls, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        int appendMethodSignature = appendMethodSignature(clsArr, cls, stringBuilder);
        classFileWriter.startMethod(str2, stringBuilder.toString(), (short) 1);
        classFileWriter.add(42);
        classFileWriter.add(180, str, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        classFileWriter.add(42);
        classFileWriter.add(180, str, "self", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(42);
        classFileWriter.add(180, str, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.addPush(str2);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "getFunction", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Lorg/mozilla/javascript/Function;");
        generatePushWrappedArgs(classFileWriter, clsArr, clsArr.length);
        if (clsArr.length > 64) {
            throw Context.reportRuntimeError0("JavaAdapter can not subclass methods with more then 64 arguments.");
        }
        long j = 0;
        for (int i = 0; i != clsArr.length; i++) {
            if (!clsArr[i].isPrimitive()) {
                j |= (long) (1 << i);
            }
        }
        classFileWriter.addPush(j);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "callMethod", "(Lorg/mozilla/javascript/ContextFactory;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Function;[Ljava/lang/Object;J)Ljava/lang/Object;");
        generateReturnResult(classFileWriter, cls, z);
        classFileWriter.stopMethod((short) appendMethodSignature);
    }

    private static int generatePushParam(ClassFileWriter classFileWriter, int i, Class<?> cls) {
        if (cls.isPrimitive()) {
            switch (cls.getName().charAt(0)) {
                case 'b':
                case 'c':
                case 'i':
                case 's':
                case 'z':
                    classFileWriter.addILoad(i);
                    return 1;
                case 'd':
                    classFileWriter.addDLoad(i);
                    return 2;
                case 'f':
                    classFileWriter.addFLoad(i);
                    return 1;
                case 'l':
                    classFileWriter.addLLoad(i);
                    return 2;
                default:
                    throw Kit.codeBug();
            }
        }
        classFileWriter.addALoad(i);
        return 1;
    }

    private static void generatePopResult(ClassFileWriter classFileWriter, Class<?> cls) {
        if (cls.isPrimitive()) {
            switch (cls.getName().charAt(0)) {
                case 'b':
                case 'c':
                case 'i':
                case 's':
                case 'z':
                    classFileWriter.add(172);
                    return;
                case 'd':
                    classFileWriter.add(175);
                    return;
                case 'f':
                    classFileWriter.add(174);
                    return;
                case 'l':
                    classFileWriter.add(173);
                    return;
                default:
                    return;
            }
        }
        classFileWriter.add(176);
    }

    private static void generateSuper(ClassFileWriter classFileWriter, String str, String str2, String str3, String str4, Class<?>[] clsArr, Class<?> cls) {
        int i = 1;
        int i2 = 0;
        classFileWriter.startMethod("super$" + str3, str4, (short) 1);
        classFileWriter.add(25, 0);
        while (i2 < clsArr.length) {
            i += generatePushParam(classFileWriter, i, clsArr[i2]);
            i2++;
        }
        classFileWriter.addInvoke(183, str2, str3, str4);
        if (cls.equals(Void.TYPE)) {
            classFileWriter.add(177);
        } else {
            generatePopResult(classFileWriter, cls);
        }
        classFileWriter.stopMethod((short) (i + 1));
    }

    private static String getMethodSignature(Method method, Class<?>[] clsArr) {
        StringBuilder stringBuilder = new StringBuilder();
        appendMethodSignature(clsArr, method.getReturnType(), stringBuilder);
        return stringBuilder.toString();
    }

    static int appendMethodSignature(Class<?>[] clsArr, Class<?> cls, StringBuilder stringBuilder) {
        stringBuilder.append('(');
        int length = clsArr.length + 1;
        int i = length;
        for (Class cls2 : clsArr) {
            appendTypeString(stringBuilder, cls2);
            if (cls2 == Long.TYPE || cls2 == Double.TYPE) {
                i++;
            }
        }
        stringBuilder.append(')');
        appendTypeString(stringBuilder, cls);
        return i;
    }

    private static StringBuilder appendTypeString(StringBuilder stringBuilder, Class<?> cls) {
        while (cls.isArray()) {
            stringBuilder.append('[');
            cls = cls.getComponentType();
        }
        if (cls.isPrimitive()) {
            char c;
            if (cls == Boolean.TYPE) {
                c = 'Z';
            } else if (cls == Long.TYPE) {
                c = 'J';
            } else {
                c = Character.toUpperCase(cls.getName().charAt(0));
            }
            stringBuilder.append(c);
        } else {
            stringBuilder.append('L');
            stringBuilder.append(cls.getName().replace('.', '/'));
            stringBuilder.append(';');
        }
        return stringBuilder;
    }

    static int[] getArgsToConvert(Class<?>[] clsArr) {
        int i;
        int i2 = 0;
        int i3 = 0;
        for (i = 0; i != clsArr.length; i++) {
            if (!clsArr[i].isPrimitive()) {
                i3++;
            }
        }
        if (i3 == 0) {
            return null;
        }
        int[] iArr = new int[i3];
        i = 0;
        while (i2 != clsArr.length) {
            if (!clsArr[i2].isPrimitive()) {
                i3 = i + 1;
                iArr[i] = i2;
                i = i3;
            }
            i2++;
        }
        return iArr;
    }
}
