package org.mozilla.javascript;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class JavaMembers {
    private Class<?> cl;
    NativeJavaMethod ctors;
    private Map<String, FieldAndMethods> fieldAndMethods;
    private Map<String, Object> members;
    private Map<String, FieldAndMethods> staticFieldAndMethods;
    private Map<String, Object> staticMembers;

    private static final class MethodSignature {
        private final Class<?>[] args;
        private final String name;

        private MethodSignature(String str, Class<?>[] clsArr) {
            this.name = str;
            this.args = clsArr;
        }

        MethodSignature(Method method) {
            this(method.getName(), method.getParameterTypes());
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof MethodSignature)) {
                return false;
            }
            MethodSignature methodSignature = (MethodSignature) obj;
            if (methodSignature.name.equals(this.name) && Arrays.equals(this.args, methodSignature.args)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.name.hashCode() ^ this.args.length;
        }
    }

    JavaMembers(Scriptable scriptable, Class<?> cls) {
        this(scriptable, cls, false);
    }

    JavaMembers(Scriptable scriptable, Class<?> cls, boolean z) {
        try {
            Context enterContext = ContextFactory.getGlobal().enterContext();
            ClassShutter classShutter = enterContext.getClassShutter();
            if (classShutter == null || classShutter.visibleToScripts(cls.getName())) {
                this.members = new HashMap();
                this.staticMembers = new HashMap();
                this.cl = cls;
                reflect(scriptable, z, enterContext.hasFeature(13));
                return;
            }
            throw Context.reportRuntimeError1("msg.access.prohibited", cls.getName());
        } finally {
            Context.exit();
        }
    }

    boolean has(String str, boolean z) {
        if ((z ? this.staticMembers : this.members).get(str) != null) {
            return true;
        }
        return findExplicitFunction(str, z) != null;
    }

    Object get(Scriptable scriptable, String str, Object obj, boolean z) {
        Object obj2 = (z ? this.staticMembers : this.members).get(str);
        if (!z && obj2 == null) {
            obj2 = this.staticMembers.get(str);
        }
        if (obj2 == null) {
            obj2 = getExplicitFunction(scriptable, str, obj, z);
            if (obj2 == null) {
                return Scriptable.NOT_FOUND;
            }
        }
        if (obj2 instanceof Scriptable) {
            return obj2;
        }
        Context context = Context.getContext();
        try {
            Object invoke;
            Class returnType;
            if (obj2 instanceof BeanProperty) {
                BeanProperty beanProperty = (BeanProperty) obj2;
                if (beanProperty.getter == null) {
                    return Scriptable.NOT_FOUND;
                }
                invoke = beanProperty.getter.invoke(obj, Context.emptyArgs);
                returnType = beanProperty.getter.method().getReturnType();
            } else {
                Field field = (Field) obj2;
                if (z) {
                    obj = null;
                }
                invoke = field.get(obj);
                returnType = field.getType();
            }
            return context.getWrapFactory().wrap(context, ScriptableObject.getTopLevelScope(scriptable), invoke, returnType);
        } catch (Throwable e) {
            throw Context.throwAsScriptRuntimeEx(e);
        }
    }

    void put(Scriptable scriptable, String str, Object obj, Object obj2, boolean z) {
        Map map = z ? this.staticMembers : this.members;
        Field field = map.get(str);
        if (!z && field == null) {
            field = this.staticMembers.get(str);
        }
        if (field == null) {
            throw reportMemberNotFound(str);
        }
        Field field2;
        if (field instanceof FieldAndMethods) {
            field2 = ((FieldAndMethods) map.get(str)).field;
        } else {
            field2 = field;
        }
        if (field2 instanceof BeanProperty) {
            BeanProperty beanProperty = (BeanProperty) field2;
            if (beanProperty.setter == null) {
                throw reportMemberNotFound(str);
            } else if (beanProperty.setters == null || obj2 == null) {
                try {
                    beanProperty.setter.invoke(obj, new Object[]{Context.jsToJava(obj2, beanProperty.setter.argTypes[0])});
                } catch (Throwable e) {
                    throw Context.throwAsScriptRuntimeEx(e);
                }
            } else {
                beanProperty.setters.call(Context.getContext(), ScriptableObject.getTopLevelScope(scriptable), scriptable, new Object[]{obj2});
            }
        } else if (field2 instanceof Field) {
            field2 = field2;
            try {
                field2.set(obj, Context.jsToJava(obj2, field2.getType()));
            } catch (Throwable e2) {
                if ((field2.getModifiers() & 16) == 0) {
                    throw Context.throwAsScriptRuntimeEx(e2);
                }
            } catch (IllegalArgumentException e3) {
                throw Context.reportRuntimeError3("msg.java.internal.field.type", obj2.getClass().getName(), field2, obj.getClass().getName());
            }
        } else {
            throw Context.reportRuntimeError1(field2 == null ? "msg.java.internal.private" : "msg.java.method.assign", str);
        }
    }

    Object[] getIds(boolean z) {
        Map map = z ? this.staticMembers : this.members;
        return map.keySet().toArray(new Object[map.size()]);
    }

    static String javaSignature(Class<?> cls) {
        if (!cls.isArray()) {
            return cls.getName();
        }
        int i = 0;
        do {
            i++;
            cls = cls.getComponentType();
        } while (cls.isArray());
        String name = cls.getName();
        String str = "[]";
        if (i == 1) {
            return name.concat(str);
        }
        StringBuilder stringBuilder = new StringBuilder(name.length() + (str.length() * i));
        stringBuilder.append(name);
        while (i != 0) {
            i--;
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    static String liveConnectSignature(Class<?>[] clsArr) {
        int length = clsArr.length;
        if (length == 0) {
            return "()";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        for (int i = 0; i != length; i++) {
            if (i != 0) {
                stringBuilder.append(',');
            }
            stringBuilder.append(javaSignature(clsArr[i]));
        }
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    private MemberBox findExplicitFunction(String str, boolean z) {
        int indexOf = str.indexOf(40);
        if (indexOf < 0) {
            return null;
        }
        int i;
        MemberBox[] memberBoxArr;
        Map map = z ? this.staticMembers : this.members;
        if (z && indexOf == 0) {
            i = 1;
        } else {
            i = 0;
        }
        if (i != 0) {
            memberBoxArr = this.ctors.methods;
        } else {
            String substring = str.substring(0, indexOf);
            Object obj = map.get(substring);
            if (!z && obj == null) {
                obj = this.staticMembers.get(substring);
            }
            if (obj instanceof NativeJavaMethod) {
                memberBoxArr = ((NativeJavaMethod) obj).methods;
            } else {
                memberBoxArr = null;
            }
        }
        if (memberBoxArr != null) {
            for (MemberBox memberBox : memberBoxArr) {
                String liveConnectSignature = liveConnectSignature(memberBox.argTypes);
                if (liveConnectSignature.length() + indexOf == str.length() && str.regionMatches(indexOf, liveConnectSignature, 0, liveConnectSignature.length())) {
                    return memberBox;
                }
            }
        }
        return null;
    }

    private Object getExplicitFunction(Scriptable scriptable, String str, Object obj, boolean z) {
        Map map = z ? this.staticMembers : this.members;
        MemberBox findExplicitFunction = findExplicitFunction(str, z);
        if (findExplicitFunction == null) {
            return null;
        }
        Scriptable functionPrototype = ScriptableObject.getFunctionPrototype(scriptable);
        if (findExplicitFunction.isCtor()) {
            NativeJavaConstructor nativeJavaConstructor = new NativeJavaConstructor(findExplicitFunction);
            nativeJavaConstructor.setPrototype(functionPrototype);
            map.put(str, nativeJavaConstructor);
            return nativeJavaConstructor;
        }
        Object obj2 = map.get(findExplicitFunction.getName());
        if (!(obj2 instanceof NativeJavaMethod) || ((NativeJavaMethod) obj2).methods.length <= 1) {
            return obj2;
        }
        obj2 = new NativeJavaMethod(findExplicitFunction, str);
        obj2.setPrototype(functionPrototype);
        map.put(str, obj2);
        return obj2;
    }

    private static Method[] discoverAccessibleMethods(Class<?> cls, boolean z, boolean z2) {
        Map hashMap = new HashMap();
        discoverAccessibleMethods(cls, hashMap, z, z2);
        return (Method[]) hashMap.values().toArray(new Method[hashMap.size()]);
    }

    private static void discoverAccessibleMethods(Class<?> cls, Map<MethodSignature, Method> map, boolean z, boolean z2) {
        int i = 0;
        if (Modifier.isPublic(cls.getModifiers()) || z2) {
            MethodSignature methodSignature;
            if (z || z2) {
                while (cls != null) {
                    try {
                        for (Method method : cls.getDeclaredMethods()) {
                            int modifiers = method.getModifiers();
                            if (Modifier.isPublic(modifiers) || Modifier.isProtected(modifiers) || z2) {
                                methodSignature = new MethodSignature(method);
                                if (!map.containsKey(methodSignature)) {
                                    if (z2 && !method.isAccessible()) {
                                        method.setAccessible(true);
                                    }
                                    map.put(methodSignature, method);
                                }
                            }
                        }
                        cls = cls.getSuperclass();
                    } catch (SecurityException e) {
                        try {
                            for (Method method2 : cls.getMethods()) {
                                methodSignature = new MethodSignature(method2);
                                if (!map.containsKey(methodSignature)) {
                                    map.put(methodSignature, method2);
                                }
                            }
                            return;
                        } catch (SecurityException e2) {
                            Context.reportWarning("Could not discover accessible methods of class " + cls.getName() + " due to lack of privileges, " + "attemping superclasses/interfaces.");
                        }
                    }
                }
                return;
            }
            for (Method method22 : cls.getMethods()) {
                methodSignature = new MethodSignature(method22);
                if (!map.containsKey(methodSignature)) {
                    map.put(methodSignature, method22);
                }
            }
            return;
        }
        Class[] interfaces = cls.getInterfaces();
        int length = interfaces.length;
        while (i < length) {
            discoverAccessibleMethods(interfaces[i], map, z, z2);
            i++;
        }
        Class superclass = cls.getSuperclass();
        if (superclass != null) {
            discoverAccessibleMethods(superclass, map, z, z2);
        }
    }

    private void reflect(Scriptable scriptable, boolean z, boolean z2) {
        int size;
        for (Method method : discoverAccessibleMethods(this.cl, z, z2)) {
            Map map = Modifier.isStatic(method.getModifiers()) ? this.staticMembers : this.members;
            String name = method.getName();
            Object obj = map.get(name);
            if (obj == null) {
                map.put(name, method);
            } else {
                ObjArray objArray;
                if (obj instanceof ObjArray) {
                    objArray = (ObjArray) obj;
                } else {
                    if (!(obj instanceof Method)) {
                        Kit.codeBug();
                    }
                    ObjArray objArray2 = new ObjArray();
                    objArray2.add(obj);
                    map.put(name, objArray2);
                    objArray = objArray2;
                }
                objArray.add(method);
            }
        }
        int i = 0;
        while (i != 2) {
            Map map2 = (i == 0 ? 1 : null) != null ? this.staticMembers : this.members;
            for (Entry entry : map2.entrySet()) {
                MemberBox[] memberBoxArr;
                Object value = entry.getValue();
                if (value instanceof Method) {
                    memberBoxArr = new MemberBox[]{new MemberBox((Method) value)};
                } else {
                    ObjArray objArray3 = (ObjArray) value;
                    size = objArray3.size();
                    if (size < 2) {
                        Kit.codeBug();
                    }
                    MemberBox[] memberBoxArr2 = new MemberBox[size];
                    for (int i2 = 0; i2 != size; i2++) {
                        memberBoxArr2[i2] = new MemberBox((Method) objArray3.get(i2));
                    }
                    memberBoxArr = memberBoxArr2;
                }
                BaseFunction nativeJavaMethod = new NativeJavaMethod(memberBoxArr);
                if (scriptable != null) {
                    ScriptRuntime.setFunctionProtoAndParent(nativeJavaMethod, scriptable);
                }
                map2.put(entry.getKey(), nativeJavaMethod);
            }
            i++;
        }
        for (Field field : getAccessibleFields(z, z2)) {
            boolean isStatic;
            String name2 = field.getName();
            try {
                isStatic = Modifier.isStatic(field.getModifiers());
                map = isStatic ? this.staticMembers : this.members;
                obj = map.get(name2);
                if (obj == null) {
                    map.put(name2, field);
                } else if (obj instanceof NativeJavaMethod) {
                    FieldAndMethods fieldAndMethods = new FieldAndMethods(scriptable, ((NativeJavaMethod) obj).methods, field);
                    Map map3 = isStatic ? this.staticFieldAndMethods : this.fieldAndMethods;
                    if (map3 == null) {
                        map3 = new HashMap();
                        if (isStatic) {
                            this.staticFieldAndMethods = map3;
                        } else {
                            this.fieldAndMethods = map3;
                        }
                    }
                    map3.put(name2, fieldAndMethods);
                    map.put(name2, fieldAndMethods);
                } else if (!(obj instanceof Field)) {
                    Kit.codeBug();
                } else if (((Field) obj).getDeclaringClass().isAssignableFrom(field.getDeclaringClass())) {
                    map.put(name2, field);
                }
            } catch (SecurityException e) {
                Context.reportWarning("Could not access field " + name2 + " of class " + this.cl.getName() + " due to lack of privileges.");
            }
        }
        size = 0;
        while (size != 2) {
            isStatic = size == 0;
            Map map4 = isStatic ? this.staticMembers : this.members;
            Map hashMap = new HashMap();
            for (String str : map4.keySet()) {
                String str2;
                boolean startsWith = str2.startsWith("get");
                boolean startsWith2 = str2.startsWith("set");
                boolean startsWith3 = str2.startsWith("is");
                if (startsWith || startsWith3 || startsWith2) {
                    String substring = str2.substring(startsWith3 ? 2 : 3);
                    if (substring.length() != 0) {
                        String str3;
                        MemberBox findGetter;
                        MemberBox findGetter2;
                        NativeJavaMethod nativeJavaMethod2;
                        MemberBox extractSetMethod;
                        char charAt = substring.charAt(0);
                        if (Character.isUpperCase(charAt)) {
                            if (substring.length() == 1) {
                                value = substring.toLowerCase();
                            } else if (!Character.isUpperCase(substring.charAt(1))) {
                                str3 = Character.toLowerCase(charAt) + substring.substring(1);
                            }
                            if (!hashMap.containsKey(value)) {
                                obj = map4.get(value);
                                if (obj == null || (z2 && (obj instanceof Member) && Modifier.isPrivate(((Member) obj).getModifiers()))) {
                                    findGetter = findGetter(isStatic, map4, "get", substring);
                                    if (findGetter != null) {
                                        findGetter2 = findGetter(isStatic, map4, "is", substring);
                                    } else {
                                        findGetter2 = findGetter;
                                    }
                                    str2 = "set".concat(substring);
                                    if (map4.containsKey(str2)) {
                                        obj = map4.get(str2);
                                        if (obj instanceof NativeJavaMethod) {
                                            nativeJavaMethod2 = (NativeJavaMethod) obj;
                                            if (findGetter2 == null) {
                                                extractSetMethod = extractSetMethod(findGetter2.method().getReturnType(), nativeJavaMethod2.methods, isStatic);
                                            } else {
                                                extractSetMethod = extractSetMethod(nativeJavaMethod2.methods, isStatic);
                                            }
                                            if (nativeJavaMethod2.methods.length <= 1) {
                                                nativeJavaMethod2 = null;
                                            }
                                            hashMap.put(value, new BeanProperty(findGetter2, extractSetMethod, nativeJavaMethod2));
                                        }
                                    }
                                    nativeJavaMethod2 = null;
                                    extractSetMethod = null;
                                    hashMap.put(value, new BeanProperty(findGetter2, extractSetMethod, nativeJavaMethod2));
                                }
                            }
                        }
                        str3 = substring;
                        if (!hashMap.containsKey(value)) {
                            obj = map4.get(value);
                            findGetter = findGetter(isStatic, map4, "get", substring);
                            if (findGetter != null) {
                                findGetter2 = findGetter;
                            } else {
                                findGetter2 = findGetter(isStatic, map4, "is", substring);
                            }
                            str2 = "set".concat(substring);
                            if (map4.containsKey(str2)) {
                                obj = map4.get(str2);
                                if (obj instanceof NativeJavaMethod) {
                                    nativeJavaMethod2 = (NativeJavaMethod) obj;
                                    if (findGetter2 == null) {
                                        extractSetMethod = extractSetMethod(nativeJavaMethod2.methods, isStatic);
                                    } else {
                                        extractSetMethod = extractSetMethod(findGetter2.method().getReturnType(), nativeJavaMethod2.methods, isStatic);
                                    }
                                    if (nativeJavaMethod2.methods.length <= 1) {
                                        nativeJavaMethod2 = null;
                                    }
                                    hashMap.put(value, new BeanProperty(findGetter2, extractSetMethod, nativeJavaMethod2));
                                }
                            }
                            nativeJavaMethod2 = null;
                            extractSetMethod = null;
                            hashMap.put(value, new BeanProperty(findGetter2, extractSetMethod, nativeJavaMethod2));
                        }
                    }
                }
            }
            for (String str22 : hashMap.keySet()) {
                map4.put(str22, hashMap.get(str22));
            }
            size++;
        }
        Constructor[] accessibleConstructors = getAccessibleConstructors(z2);
        MemberBox[] memberBoxArr3 = new MemberBox[accessibleConstructors.length];
        for (int i3 = 0; i3 != accessibleConstructors.length; i3++) {
            memberBoxArr3[i3] = new MemberBox(accessibleConstructors[i3]);
        }
        this.ctors = new NativeJavaMethod(memberBoxArr3, this.cl.getSimpleName());
    }

    private Constructor<?>[] getAccessibleConstructors(boolean z) {
        if (z && this.cl != ScriptRuntime.ClassClass) {
            try {
                AccessibleObject[] declaredConstructors = this.cl.getDeclaredConstructors();
                AccessibleObject.setAccessible(declaredConstructors, true);
                return declaredConstructors;
            } catch (SecurityException e) {
                Context.reportWarning("Could not access constructor  of class " + this.cl.getName() + " due to lack of privileges.");
            }
        }
        return this.cl.getConstructors();
    }

    private Field[] getAccessibleFields(boolean z, boolean z2) {
        if (z2 || z) {
            try {
                List arrayList = new ArrayList();
                for (Class cls = this.cl; cls != null; cls = cls.getSuperclass()) {
                    for (Field field : cls.getDeclaredFields()) {
                        int modifiers = field.getModifiers();
                        if (z2 || Modifier.isPublic(modifiers) || Modifier.isProtected(modifiers)) {
                            if (!field.isAccessible()) {
                                field.setAccessible(true);
                            }
                            arrayList.add(field);
                        }
                    }
                }
                return (Field[]) arrayList.toArray(new Field[arrayList.size()]);
            } catch (SecurityException e) {
            }
        }
        return this.cl.getFields();
    }

    private MemberBox findGetter(boolean z, Map<String, Object> map, String str, String str2) {
        String concat = str.concat(str2);
        if (map.containsKey(concat)) {
            Object obj = map.get(concat);
            if (obj instanceof NativeJavaMethod) {
                return extractGetMethod(((NativeJavaMethod) obj).methods, z);
            }
        }
        return null;
    }

    private static MemberBox extractGetMethod(MemberBox[] memberBoxArr, boolean z) {
        int length = memberBoxArr.length;
        int i = 0;
        while (i < length) {
            MemberBox memberBox = memberBoxArr[i];
            if (memberBox.argTypes.length != 0 || (z && !memberBox.isStatic())) {
                i++;
            } else {
                if (memberBox.method().getReturnType() != Void.TYPE) {
                    return memberBox;
                }
                return null;
            }
        }
        return null;
    }

    private static MemberBox extractSetMethod(Class<?> cls, MemberBox[] memberBoxArr, boolean z) {
        for (int i = 1; i <= 2; i++) {
            for (MemberBox memberBox : memberBoxArr) {
                if (!z || memberBox.isStatic()) {
                    Class[] clsArr = memberBox.argTypes;
                    if (clsArr.length != 1) {
                        continue;
                    } else if (i != 1) {
                        if (i != 2) {
                            Kit.codeBug();
                        }
                        if (clsArr[0].isAssignableFrom(cls)) {
                            return memberBox;
                        }
                    } else if (clsArr[0] == cls) {
                        return memberBox;
                    }
                }
            }
        }
        return null;
    }

    private static MemberBox extractSetMethod(MemberBox[] memberBoxArr, boolean z) {
        for (MemberBox memberBox : memberBoxArr) {
            if ((!z || memberBox.isStatic()) && memberBox.method().getReturnType() == Void.TYPE && memberBox.argTypes.length == 1) {
                return memberBox;
            }
        }
        return null;
    }

    Map<String, FieldAndMethods> getFieldAndMethodsObjects(Scriptable scriptable, Object obj, boolean z) {
        Map map = z ? this.staticFieldAndMethods : this.fieldAndMethods;
        if (map == null) {
            return null;
        }
        Map<String, FieldAndMethods> hashMap = new HashMap(map.size());
        for (FieldAndMethods fieldAndMethods : map.values()) {
            FieldAndMethods fieldAndMethods2 = new FieldAndMethods(scriptable, fieldAndMethods.methods, fieldAndMethods.field);
            fieldAndMethods2.javaObject = obj;
            hashMap.put(fieldAndMethods.field.getName(), fieldAndMethods2);
        }
        return hashMap;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static org.mozilla.javascript.JavaMembers lookupClass(org.mozilla.javascript.Scriptable r6, java.lang.Class<?> r7, java.lang.Class<?> r8, boolean r9) {
        /*
        r3 = org.mozilla.javascript.ClassCache.get(r6);
        r4 = r3.getClassCacheMap();
        r1 = r7;
    L_0x0009:
        r0 = r4.get(r1);
        r0 = (org.mozilla.javascript.JavaMembers) r0;
        if (r0 == 0) goto L_0x0017;
    L_0x0011:
        if (r1 == r7) goto L_0x0016;
    L_0x0013:
        r4.put(r7, r0);
    L_0x0016:
        return r0;
    L_0x0017:
        r0 = new org.mozilla.javascript.JavaMembers;	 Catch:{ SecurityException -> 0x002f }
        r2 = r3.getAssociatedScope();	 Catch:{ SecurityException -> 0x002f }
        r0.<init>(r2, r1, r9);	 Catch:{ SecurityException -> 0x002f }
        r2 = r3.isCachingEnabled();
        if (r2 == 0) goto L_0x0016;
    L_0x0026:
        r4.put(r1, r0);
        if (r1 == r7) goto L_0x0016;
    L_0x002b:
        r4.put(r7, r0);
        goto L_0x0016;
    L_0x002f:
        r0 = move-exception;
        r2 = r0;
        if (r8 == 0) goto L_0x003f;
    L_0x0033:
        r0 = r8.isInterface();
        if (r0 == 0) goto L_0x003f;
    L_0x0039:
        r0 = 0;
        r5 = r8;
        r8 = r0;
        r0 = r5;
    L_0x003d:
        r1 = r0;
        goto L_0x0009;
    L_0x003f:
        r0 = r1.getSuperclass();
        if (r0 != 0) goto L_0x003d;
    L_0x0045:
        r0 = r1.isInterface();
        if (r0 == 0) goto L_0x004e;
    L_0x004b:
        r0 = org.mozilla.javascript.ScriptRuntime.ObjectClass;
        goto L_0x003d;
    L_0x004e:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.JavaMembers.lookupClass(org.mozilla.javascript.Scriptable, java.lang.Class, java.lang.Class, boolean):org.mozilla.javascript.JavaMembers");
    }

    RuntimeException reportMemberNotFound(String str) {
        return Context.reportRuntimeError2("msg.java.member.not.found", this.cl.getName(), str);
    }
}
