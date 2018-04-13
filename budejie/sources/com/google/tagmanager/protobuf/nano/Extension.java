package com.google.tagmanager.protobuf.nano;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class Extension<T> {
    public final int fieldNumber;
    public Class<T> fieldType;
    public boolean isRepeatedField;
    public Class<T> listType;

    public static abstract class TypeLiteral<T> {
        private final Type type;

        protected TypeLiteral() {
            Type genericSuperclass = getClass().getGenericSuperclass();
            if (genericSuperclass instanceof Class) {
                throw new RuntimeException("Missing type parameter");
            }
            this.type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        }

        private boolean isList() {
            return this.type instanceof ParameterizedType;
        }

        private Class<T> getListType() {
            return (Class) ((ParameterizedType) this.type).getRawType();
        }

        private Class<T> getTargetClass() {
            if (isList()) {
                return (Class) ((ParameterizedType) this.type).getActualTypeArguments()[0];
            }
            return (Class) this.type;
        }
    }

    private Extension(int i, TypeLiteral<T> typeLiteral) {
        this.fieldNumber = i;
        this.isRepeatedField = typeLiteral.isList();
        this.fieldType = typeLiteral.getTargetClass();
        this.listType = this.isRepeatedField ? typeLiteral.getListType() : null;
    }

    public static <T> Extension<T> create(int i, TypeLiteral<T> typeLiteral) {
        return new Extension(i, typeLiteral);
    }

    public static <T> Extension<List<T>> createRepeated(int i, TypeLiteral<List<T>> typeLiteral) {
        return new Extension(i, typeLiteral);
    }
}
