package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.annotation.NoClass;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class ClassUtil {
    private static final Class<?> CLS_OBJECT = Object.class;
    private static final LRUMap<Class<?>, ClassMetadata> sCached = new LRUMap(48, 48);

    private static final class ClassMetadata {
        private static final Annotation[] NO_ANNOTATIONS = new Annotation[0];
        private static final Ctor[] NO_CTORS = new Ctor[0];
        private Annotation[] _annotations;
        private Ctor[] _constructors;
        private Field[] _fields;
        private final Class<?> _forClass;
        private Type[] _genericInterfaces;
        private Boolean _hasEnclosingMethod;
        private Class<?>[] _interfaces;
        private Method[] _methods;
        private String _packageName;

        public ClassMetadata(Class<?> cls) {
            this._forClass = cls;
        }

        public String getPackageName() {
            String str = this._packageName;
            if (str == null) {
                Package packageR = this._forClass.getPackage();
                str = packageR == null ? null : packageR.getName();
                if (str == null) {
                    str = "";
                }
                this._packageName = str;
            }
            return str == "" ? null : str;
        }

        public Class<?>[] getInterfaces() {
            Class<?>[] clsArr = this._interfaces;
            if (clsArr != null) {
                return clsArr;
            }
            clsArr = this._forClass.getInterfaces();
            this._interfaces = clsArr;
            return clsArr;
        }

        public Type[] getGenericInterfaces() {
            Type[] typeArr = this._genericInterfaces;
            if (typeArr != null) {
                return typeArr;
            }
            typeArr = this._forClass.getGenericInterfaces();
            this._genericInterfaces = typeArr;
            return typeArr;
        }

        public Annotation[] getDeclaredAnnotations() {
            Annotation[] annotationArr = this._annotations;
            if (annotationArr == null) {
                annotationArr = isObjectOrPrimitive() ? NO_ANNOTATIONS : this._forClass.getDeclaredAnnotations();
                this._annotations = annotationArr;
            }
            return annotationArr;
        }

        public Ctor[] getConstructors() {
            Ctor[] ctorArr = this._constructors;
            if (ctorArr == null) {
                if (this._forClass.isInterface() || isObjectOrPrimitive()) {
                    ctorArr = NO_CTORS;
                } else {
                    Constructor[] declaredConstructors = this._forClass.getDeclaredConstructors();
                    int length = declaredConstructors.length;
                    ctorArr = new Ctor[length];
                    for (int i = 0; i < length; i++) {
                        ctorArr[i] = new Ctor(declaredConstructors[i]);
                    }
                }
                this._constructors = ctorArr;
            }
            return ctorArr;
        }

        public Field[] getDeclaredFields() {
            Field[] fieldArr = this._fields;
            if (fieldArr != null) {
                return fieldArr;
            }
            fieldArr = this._forClass.getDeclaredFields();
            this._fields = fieldArr;
            return fieldArr;
        }

        public Method[] getDeclaredMethods() {
            Method[] methodArr = this._methods;
            if (methodArr != null) {
                return methodArr;
            }
            methodArr = this._forClass.getDeclaredMethods();
            this._methods = methodArr;
            return methodArr;
        }

        public boolean hasEnclosingMethod() {
            Boolean bool = this._hasEnclosingMethod;
            if (bool == null) {
                if (isObjectOrPrimitive()) {
                    bool = Boolean.FALSE;
                } else {
                    bool = Boolean.valueOf(this._forClass.getEnclosingMethod() != null);
                }
                this._hasEnclosingMethod = bool;
            }
            return bool.booleanValue();
        }

        private boolean isObjectOrPrimitive() {
            return this._forClass == ClassUtil.CLS_OBJECT || this._forClass.isPrimitive();
        }
    }

    public static final class Ctor {
        private Annotation[] _annotations;
        public final Constructor<?> _ctor;
        private Annotation[][] _paramAnnotations;
        private int _paramCount = -1;

        public Ctor(Constructor<?> constructor) {
            this._ctor = constructor;
        }

        public Constructor<?> getConstructor() {
            return this._ctor;
        }

        public int getParamCount() {
            int i = this._paramCount;
            if (i >= 0) {
                return i;
            }
            i = this._ctor.getParameterTypes().length;
            this._paramCount = i;
            return i;
        }

        public Class<?> getDeclaringClass() {
            return this._ctor.getDeclaringClass();
        }

        public Annotation[] getDeclaredAnnotations() {
            Annotation[] annotationArr = this._annotations;
            if (annotationArr != null) {
                return annotationArr;
            }
            annotationArr = this._ctor.getDeclaredAnnotations();
            this._annotations = annotationArr;
            return annotationArr;
        }

        public Annotation[][] getParameterAnnotations() {
            Annotation[][] annotationArr = this._paramAnnotations;
            if (annotationArr != null) {
                return annotationArr;
            }
            annotationArr = this._ctor.getParameterAnnotations();
            this._paramAnnotations = annotationArr;
            return annotationArr;
        }
    }

    private static class EnumTypeLocator {
        static final EnumTypeLocator instance = new EnumTypeLocator();
        private final Field enumMapTypeField = locateField(EnumMap.class, "elementType", Class.class);
        private final Field enumSetTypeField = locateField(EnumSet.class, "elementType", Class.class);

        private EnumTypeLocator() {
        }

        public Class<? extends Enum<?>> enumTypeFor(EnumSet<?> enumSet) {
            if (this.enumSetTypeField != null) {
                return (Class) get(enumSet, this.enumSetTypeField);
            }
            throw new IllegalStateException("Can not figure out type for EnumSet (odd JDK platform?)");
        }

        public Class<? extends Enum<?>> enumTypeFor(EnumMap<?, ?> enumMap) {
            if (this.enumMapTypeField != null) {
                return (Class) get(enumMap, this.enumMapTypeField);
            }
            throw new IllegalStateException("Can not figure out type for EnumMap (odd JDK platform?)");
        }

        private Object get(Object obj, Field field) {
            try {
                return field.get(obj);
            } catch (Throwable e) {
                throw new IllegalArgumentException(e);
            }
        }

        private static Field locateField(Class<?> cls, String str, Class<?> cls2) {
            int i;
            int length;
            Field[] declaredFields = ClassUtil.getDeclaredFields(cls);
            for (Field field : declaredFields) {
                if (str.equals(field.getName()) && field.getType() == cls2) {
                    break;
                }
            }
            Field field2 = null;
            if (field2 == null) {
                length = declaredFields.length;
                i = 0;
                Field field3 = field2;
                while (i < length) {
                    field2 = declaredFields[i];
                    if (field2.getType() != cls2) {
                        field2 = field3;
                    } else if (field3 != null) {
                        return null;
                    }
                    i++;
                    field3 = field2;
                }
                field2 = field3;
            }
            if (field2 == null) {
                return field2;
            }
            try {
                field2.setAccessible(true);
                return field2;
            } catch (Throwable th) {
                return field2;
            }
        }
    }

    public static <T> Iterator<T> emptyIterator() {
        return Collections.emptyIterator();
    }

    public static List<JavaType> findSuperTypes(JavaType javaType, Class<?> cls, boolean z) {
        if (javaType == null || javaType.hasRawClass(cls) || javaType.hasRawClass(Object.class)) {
            return Collections.emptyList();
        }
        List<JavaType> arrayList = new ArrayList(8);
        _addSuperTypes(javaType, cls, arrayList, z);
        return arrayList;
    }

    public static List<Class<?>> findRawSuperTypes(Class<?> cls, Class<?> cls2, boolean z) {
        if (cls == null || cls == cls2 || cls == Object.class) {
            return Collections.emptyList();
        }
        List<Class<?>> arrayList = new ArrayList(8);
        _addRawSuperTypes(cls, cls2, arrayList, z);
        return arrayList;
    }

    public static List<Class<?>> findSuperClasses(Class<?> cls, Class<?> cls2, boolean z) {
        List<Class<?>> linkedList = new LinkedList();
        if (cls != null && cls != cls2) {
            if (z) {
                linkedList.add(cls);
            }
            while (true) {
                cls = cls.getSuperclass();
                if (cls == null || cls == cls2) {
                    break;
                }
                linkedList.add(cls);
            }
        }
        return linkedList;
    }

    @Deprecated
    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2) {
        return findSuperTypes((Class) cls, (Class) cls2, new ArrayList(8));
    }

    @Deprecated
    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2, List<Class<?>> list) {
        _addRawSuperTypes(cls, cls2, list, false);
        return list;
    }

    private static void _addSuperTypes(JavaType javaType, Class<?> cls, Collection<JavaType> collection, boolean z) {
        if (javaType != null) {
            Class<?> rawClass = javaType.getRawClass();
            if (rawClass != cls && rawClass != Object.class) {
                if (z) {
                    if (!collection.contains(javaType)) {
                        collection.add(javaType);
                    } else {
                        return;
                    }
                }
                for (JavaType _addSuperTypes : javaType.getInterfaces()) {
                    _addSuperTypes(_addSuperTypes, cls, collection, true);
                }
                _addSuperTypes(javaType.getSuperClass(), cls, collection, true);
            }
        }
    }

    private static void _addRawSuperTypes(Class<?> cls, Class<?> cls2, Collection<Class<?>> collection, boolean z) {
        if (cls != cls2 && cls != null && cls != Object.class) {
            if (z) {
                if (!collection.contains(cls)) {
                    collection.add(cls);
                } else {
                    return;
                }
            }
            for (Class _addRawSuperTypes : _interfaces(cls)) {
                _addRawSuperTypes(_addRawSuperTypes, cls2, collection, true);
            }
            _addRawSuperTypes(cls.getSuperclass(), cls2, collection, true);
        }
    }

    public static String canBeABeanType(Class<?> cls) {
        if (cls.isAnnotation()) {
            return "annotation";
        }
        if (cls.isArray()) {
            return "array";
        }
        if (cls.isEnum()) {
            return "enum";
        }
        if (cls.isPrimitive()) {
            return "primitive";
        }
        return null;
    }

    public static String isLocalType(Class<?> cls, boolean z) {
        try {
            if (hasEnclosingMethod(cls)) {
                return "local/anonymous";
            }
            if (!(z || Modifier.isStatic(cls.getModifiers()) || getEnclosingClass(cls) == null)) {
                return "non-static member class";
            }
            return null;
        } catch (SecurityException e) {
        } catch (NullPointerException e2) {
        }
    }

    public static Class<?> getOuterClass(Class<?> cls) {
        Class<?> cls2 = null;
        try {
            if (!(hasEnclosingMethod(cls) || Modifier.isStatic(cls.getModifiers()))) {
                cls2 = getEnclosingClass(cls);
            }
        } catch (SecurityException e) {
        }
        return cls2;
    }

    public static boolean isProxyType(Class<?> cls) {
        String name = cls.getName();
        if (name.startsWith("net.sf.cglib.proxy.") || name.startsWith("org.hibernate.proxy.")) {
            return true;
        }
        return false;
    }

    public static boolean isConcrete(Class<?> cls) {
        return (cls.getModifiers() & 1536) == 0;
    }

    public static boolean isConcrete(Member member) {
        return (member.getModifiers() & 1536) == 0;
    }

    public static boolean isCollectionMapOrArray(Class<?> cls) {
        if (cls.isArray() || Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    public static String getClassDescription(Object obj) {
        if (obj == null) {
            return "unknown";
        }
        return (obj instanceof Class ? (Class) obj : obj.getClass()).getName();
    }

    @Deprecated
    public static Class<?> findClass(String str) throws ClassNotFoundException {
        if (str.indexOf(46) < 0) {
            if ("int".equals(str)) {
                return Integer.TYPE;
            }
            if ("long".equals(str)) {
                return Long.TYPE;
            }
            if ("float".equals(str)) {
                return Float.TYPE;
            }
            if ("double".equals(str)) {
                return Double.TYPE;
            }
            if ("boolean".equals(str)) {
                return Boolean.TYPE;
            }
            if ("byte".equals(str)) {
                return Byte.TYPE;
            }
            if ("char".equals(str)) {
                return Character.TYPE;
            }
            if ("short".equals(str)) {
                return Short.TYPE;
            }
            if ("void".equals(str)) {
                return Void.TYPE;
            }
        }
        Throwable th = null;
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            try {
                return Class.forName(str, true, contextClassLoader);
            } catch (Throwable th2) {
                th2 = getRootCause(th2);
            }
        }
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            if (th2 == null) {
                th2 = getRootCause(e);
            }
            if (th2 instanceof RuntimeException) {
                throw ((RuntimeException) th2);
            }
            throw new ClassNotFoundException(th2.getMessage(), th2);
        }
    }

    public static String getPackageName(Class<?> cls) {
        return _getMetadata(cls).getPackageName();
    }

    public static boolean hasEnclosingMethod(Class<?> cls) {
        return _getMetadata(cls).hasEnclosingMethod();
    }

    public static Field[] getDeclaredFields(Class<?> cls) {
        return _getMetadata(cls).getDeclaredFields();
    }

    public static Method[] getDeclaredMethods(Class<?> cls) {
        return _getMetadata(cls).getDeclaredMethods();
    }

    public static Annotation[] findClassAnnotations(Class<?> cls) {
        return _getMetadata(cls).getDeclaredAnnotations();
    }

    public static Ctor[] getConstructors(Class<?> cls) {
        return _getMetadata(cls).getConstructors();
    }

    public static Class<?> getDeclaringClass(Class<?> cls) {
        return isObjectOrPrimitive(cls) ? null : cls.getDeclaringClass();
    }

    public static Type getGenericSuperclass(Class<?> cls) {
        return cls.getGenericSuperclass();
    }

    public static Type[] getGenericInterfaces(Class<?> cls) {
        return _getMetadata(cls).getGenericInterfaces();
    }

    public static Class<?> getEnclosingClass(Class<?> cls) {
        return isObjectOrPrimitive(cls) ? null : cls.getEnclosingClass();
    }

    private static Class<?>[] _interfaces(Class<?> cls) {
        return _getMetadata(cls).getInterfaces();
    }

    private static ClassMetadata _getMetadata(Class<?> cls) {
        ClassMetadata classMetadata = (ClassMetadata) sCached.get(cls);
        if (classMetadata != null) {
            return classMetadata;
        }
        ClassMetadata classMetadata2 = new ClassMetadata(cls);
        classMetadata = (ClassMetadata) sCached.putIfAbsent(cls, classMetadata2);
        return classMetadata != null ? classMetadata : classMetadata2;
    }

    @Deprecated
    public static boolean hasGetterSignature(Method method) {
        if (Modifier.isStatic(method.getModifiers())) {
            return false;
        }
        Class[] parameterTypes = method.getParameterTypes();
        if ((parameterTypes == null || parameterTypes.length == 0) && Void.TYPE != method.getReturnType()) {
            return true;
        }
        return false;
    }

    public static Throwable getRootCause(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    public static void throwRootCause(Throwable th) throws Exception {
        Throwable rootCause = getRootCause(th);
        if (rootCause instanceof Exception) {
            throw ((Exception) rootCause);
        }
        throw ((Error) rootCause);
    }

    public static void throwAsIAE(Throwable th) {
        throwAsIAE(th, th.getMessage());
    }

    public static void throwAsIAE(Throwable th, String str) {
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        } else {
            throw new IllegalArgumentException(str, th);
        }
    }

    public static void unwrapAndThrowAsIAE(Throwable th) {
        throwAsIAE(getRootCause(th));
    }

    public static void unwrapAndThrowAsIAE(Throwable th, String str) {
        throwAsIAE(getRootCause(th), str);
    }

    public static <T> T createInstance(Class<T> cls, boolean z) throws IllegalArgumentException {
        Constructor findConstructor = findConstructor(cls, z);
        if (findConstructor == null) {
            throw new IllegalArgumentException("Class " + cls.getName() + " has no default (no arg) constructor");
        }
        try {
            return findConstructor.newInstance(new Object[0]);
        } catch (Throwable e) {
            unwrapAndThrowAsIAE(e, "Failed to instantiate class " + cls.getName() + ", problem: " + e.getMessage());
            return null;
        }
    }

    public static <T> Constructor<T> findConstructor(Class<T> cls, boolean z) throws IllegalArgumentException {
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (z) {
                checkAndFixAccess(declaredConstructor);
                return declaredConstructor;
            } else if (Modifier.isPublic(declaredConstructor.getModifiers())) {
                return declaredConstructor;
            } else {
                throw new IllegalArgumentException("Default constructor for " + cls.getName() + " is not accessible (non-public?): not allowed to try modify access via Reflection: can not instantiate type");
            }
        } catch (NoSuchMethodException e) {
            return null;
        } catch (Throwable e2) {
            unwrapAndThrowAsIAE(e2, "Failed to find default constructor of class " + cls.getName() + ", problem: " + e2.getMessage());
            return null;
        }
    }

    public static Object defaultValue(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return Integer.valueOf(0);
        }
        if (cls == Long.TYPE) {
            return Long.valueOf(0);
        }
        if (cls == Boolean.TYPE) {
            return Boolean.FALSE;
        }
        if (cls == Double.TYPE) {
            return Double.valueOf(0.0d);
        }
        if (cls == Float.TYPE) {
            return Float.valueOf(0.0f);
        }
        if (cls == Byte.TYPE) {
            return Byte.valueOf((byte) 0);
        }
        if (cls == Short.TYPE) {
            return Short.valueOf((short) 0);
        }
        if (cls == Character.TYPE) {
            return Character.valueOf('\u0000');
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " is not a primitive type");
    }

    public static Class<?> wrapperType(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return Integer.class;
        }
        if (cls == Long.TYPE) {
            return Long.class;
        }
        if (cls == Boolean.TYPE) {
            return Boolean.class;
        }
        if (cls == Double.TYPE) {
            return Double.class;
        }
        if (cls == Float.TYPE) {
            return Float.class;
        }
        if (cls == Byte.TYPE) {
            return Byte.class;
        }
        if (cls == Short.TYPE) {
            return Short.class;
        }
        if (cls == Character.TYPE) {
            return Character.class;
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " is not a primitive type");
    }

    public static Class<?> primitiveType(Class<?> cls) {
        if (cls.isPrimitive()) {
            return cls;
        }
        if (cls == Integer.class) {
            return Integer.TYPE;
        }
        if (cls == Long.class) {
            return Long.TYPE;
        }
        if (cls == Boolean.class) {
            return Boolean.TYPE;
        }
        if (cls == Double.class) {
            return Double.TYPE;
        }
        if (cls == Float.class) {
            return Float.TYPE;
        }
        if (cls == Byte.class) {
            return Byte.TYPE;
        }
        if (cls == Short.class) {
            return Short.TYPE;
        }
        if (cls == Character.class) {
            return Character.TYPE;
        }
        return null;
    }

    @Deprecated
    public static void checkAndFixAccess(Member member) {
        checkAndFixAccess(member, false);
    }

    public static void checkAndFixAccess(Member member, boolean z) {
        AccessibleObject accessibleObject = (AccessibleObject) member;
        if (!z) {
            try {
                if (Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(member.getDeclaringClass().getModifiers())) {
                    return;
                }
            } catch (SecurityException e) {
                if (!accessibleObject.isAccessible()) {
                    throw new IllegalArgumentException("Can not access " + member + " (from class " + member.getDeclaringClass().getName() + "; failed to set access: " + e.getMessage());
                }
                return;
            }
        }
        accessibleObject.setAccessible(true);
    }

    public static Class<? extends Enum<?>> findEnumType(EnumSet<?> enumSet) {
        if (enumSet.isEmpty()) {
            return EnumTypeLocator.instance.enumTypeFor((EnumSet) enumSet);
        }
        return findEnumType((Enum) enumSet.iterator().next());
    }

    public static Class<? extends Enum<?>> findEnumType(EnumMap<?, ?> enumMap) {
        if (enumMap.isEmpty()) {
            return EnumTypeLocator.instance.enumTypeFor((EnumMap) enumMap);
        }
        return findEnumType((Enum) enumMap.keySet().iterator().next());
    }

    public static Class<? extends Enum<?>> findEnumType(Enum<?> enumR) {
        Class<? extends Enum<?>> cls = enumR.getClass();
        if (cls.getSuperclass() != Enum.class) {
            return cls.getSuperclass();
        }
        return cls;
    }

    public static Class<? extends Enum<?>> findEnumType(Class<?> cls) {
        if (cls.getSuperclass() != Enum.class) {
            return cls.getSuperclass();
        }
        return cls;
    }

    public static boolean isJacksonStdImpl(Object obj) {
        return obj != null && isJacksonStdImpl(obj.getClass());
    }

    public static boolean isJacksonStdImpl(Class<?> cls) {
        return cls.getAnnotation(JacksonStdImpl.class) != null;
    }

    public static boolean isBogusClass(Class<?> cls) {
        return cls == Void.class || cls == Void.TYPE || cls == NoClass.class;
    }

    public static boolean isNonStaticInnerClass(Class<?> cls) {
        return (Modifier.isStatic(cls.getModifiers()) || getEnclosingClass(cls) == null) ? false : true;
    }

    public static boolean isObjectOrPrimitive(Class<?> cls) {
        return cls == CLS_OBJECT || cls.isPrimitive();
    }
}
