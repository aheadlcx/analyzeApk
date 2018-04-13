package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.List;

public abstract class JavaType extends ResolvedType implements Serializable, Type {
    private static final long serialVersionUID = 1;
    protected final boolean _asStatic;
    protected final Class<?> _class;
    protected final int _hash;
    protected final Object _typeHandler;
    protected final Object _valueHandler;

    @Deprecated
    protected abstract JavaType _narrow(Class<?> cls);

    public abstract JavaType containedType(int i);

    public abstract int containedTypeCount();

    @Deprecated
    public abstract String containedTypeName(int i);

    public abstract boolean equals(Object obj);

    public abstract JavaType findSuperType(Class<?> cls);

    public abstract JavaType[] findTypeParameters(Class<?> cls);

    public abstract TypeBindings getBindings();

    public abstract StringBuilder getErasedSignature(StringBuilder stringBuilder);

    public abstract StringBuilder getGenericSignature(StringBuilder stringBuilder);

    public abstract List<JavaType> getInterfaces();

    public abstract JavaType getSuperClass();

    public abstract boolean isContainerType();

    public abstract JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr);

    public abstract String toString();

    public abstract JavaType withContentType(JavaType javaType);

    public abstract JavaType withContentTypeHandler(Object obj);

    public abstract JavaType withContentValueHandler(Object obj);

    public abstract JavaType withStaticTyping();

    public abstract JavaType withTypeHandler(Object obj);

    public abstract JavaType withValueHandler(Object obj);

    protected JavaType(Class<?> cls, int i, Object obj, Object obj2, boolean z) {
        this._class = cls;
        this._hash = cls.getName().hashCode() + i;
        this._valueHandler = obj;
        this._typeHandler = obj2;
        this._asStatic = z;
    }

    protected JavaType(JavaType javaType) {
        this._class = javaType._class;
        this._hash = javaType._hash;
        this._valueHandler = javaType._valueHandler;
        this._typeHandler = javaType._typeHandler;
        this._asStatic = javaType._asStatic;
    }

    @Deprecated
    public JavaType forcedNarrowBy(Class<?> cls) {
        if (cls == this._class) {
            return this;
        }
        JavaType _narrow = _narrow(cls);
        if (this._valueHandler != _narrow.getValueHandler()) {
            _narrow = _narrow.withValueHandler(this._valueHandler);
        }
        if (this._typeHandler != _narrow.getTypeHandler()) {
            _narrow = _narrow.withTypeHandler(this._typeHandler);
        }
        return _narrow;
    }

    public final Class<?> getRawClass() {
        return this._class;
    }

    public final boolean hasRawClass(Class<?> cls) {
        return this._class == cls;
    }

    public final boolean isTypeOrSubTypeOf(Class<?> cls) {
        return this._class == cls || cls.isAssignableFrom(this._class);
    }

    public boolean isAbstract() {
        return Modifier.isAbstract(this._class.getModifiers());
    }

    public boolean isConcrete() {
        if ((this._class.getModifiers() & 1536) == 0) {
            return true;
        }
        return this._class.isPrimitive();
    }

    public boolean isThrowable() {
        return Throwable.class.isAssignableFrom(this._class);
    }

    public boolean isArrayType() {
        return false;
    }

    public final boolean isEnumType() {
        return this._class.isEnum();
    }

    public final boolean isInterface() {
        return this._class.isInterface();
    }

    public final boolean isPrimitive() {
        return this._class.isPrimitive();
    }

    public final boolean isFinal() {
        return Modifier.isFinal(this._class.getModifiers());
    }

    public boolean isCollectionLikeType() {
        return false;
    }

    public boolean isMapLikeType() {
        return false;
    }

    public final boolean isJavaLangObject() {
        return this._class == Object.class;
    }

    public final boolean useStaticType() {
        return this._asStatic;
    }

    public boolean hasGenericTypes() {
        return containedTypeCount() > 0;
    }

    public JavaType getKeyType() {
        return null;
    }

    public JavaType getContentType() {
        return null;
    }

    public JavaType getReferencedType() {
        return null;
    }

    @Deprecated
    public Class<?> getParameterSource() {
        return null;
    }

    public JavaType containedTypeOrUnknown(int i) {
        JavaType containedType = containedType(i);
        return containedType == null ? TypeFactory.unknownType() : containedType;
    }

    public <T> T getValueHandler() {
        return this._valueHandler;
    }

    public <T> T getTypeHandler() {
        return this._typeHandler;
    }

    public Object getContentValueHandler() {
        return null;
    }

    public Object getContentTypeHandler() {
        return null;
    }

    public boolean hasValueHandler() {
        return this._valueHandler != null;
    }

    public String getGenericSignature() {
        StringBuilder stringBuilder = new StringBuilder(40);
        getGenericSignature(stringBuilder);
        return stringBuilder.toString();
    }

    public String getErasedSignature() {
        StringBuilder stringBuilder = new StringBuilder(40);
        getErasedSignature(stringBuilder);
        return stringBuilder.toString();
    }

    public final int hashCode() {
        return this._hash;
    }
}
