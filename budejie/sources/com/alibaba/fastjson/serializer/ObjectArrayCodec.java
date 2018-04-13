package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class ObjectArrayCodec implements ObjectDeserializer, ObjectSerializer {
    public static final ObjectArrayCodec instance = new ObjectArrayCodec();

    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        Object[] objArr = (Object[]) obj;
        if (obj != null) {
            int length = objArr.length;
            int i2 = length - 1;
            if (i2 == -1) {
                serializeWriter.append((CharSequence) "[]");
                return;
            }
            SerialContext serialContext = jSONSerializer.context;
            jSONSerializer.setContext(serialContext, obj, obj2, 0);
            Class cls = null;
            serializeWriter.append('[');
            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                jSONSerializer.incrementIndent();
                jSONSerializer.println();
                for (int i3 = 0; i3 < length; i3++) {
                    if (i3 != 0) {
                        serializeWriter.write(44);
                        jSONSerializer.println();
                    }
                    jSONSerializer.write(objArr[i3]);
                }
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
                serializeWriter.write(93);
                return;
            }
            int i4 = 0;
            ObjectSerializer objectSerializer = null;
            while (i4 < i2) {
                Class cls2;
                Object obj3 = objArr[i4];
                if (obj3 == null) {
                    serializeWriter.append((CharSequence) "null,");
                    cls2 = cls;
                } else {
                    if (jSONSerializer.containsReference(obj3)) {
                        jSONSerializer.writeReference(obj3);
                        cls2 = cls;
                    } else {
                        try {
                            Class cls3 = obj3.getClass();
                            if (cls3 == cls) {
                                objectSerializer.write(jSONSerializer, obj3, null, null, 0);
                                cls2 = cls;
                            } else {
                                objectSerializer = jSONSerializer.getObjectWriter(cls3);
                                objectSerializer.write(jSONSerializer, obj3, null, null, 0);
                                cls2 = cls3;
                            }
                        } finally {
                            jSONSerializer.context = serialContext;
                        }
                    }
                    serializeWriter.append(',');
                }
                i4++;
                cls = cls2;
            }
            Object obj4 = objArr[i2];
            if (obj4 == null) {
                serializeWriter.append((CharSequence) "null]");
            } else {
                if (jSONSerializer.containsReference(obj4)) {
                    jSONSerializer.writeReference(obj4);
                } else {
                    jSONSerializer.writeWithFieldName(obj4, Integer.valueOf(i2));
                }
                serializeWriter.append(']');
            }
            jSONSerializer.context = serialContext;
        } else if (serializeWriter.isEnabled(SerializerFeature.WriteNullListAsEmpty)) {
            serializeWriter.write("[]");
        } else {
            serializeWriter.writeNull();
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        int i = 0;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken(16);
            return null;
        } else if (jSONLexer.token() == 4) {
            T bytesValue = jSONLexer.bytesValue();
            jSONLexer.nextToken(16);
            return bytesValue;
        } else {
            Object obj2;
            if (type instanceof GenericArrayType) {
                Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
                if (genericComponentType instanceof TypeVariable) {
                    TypeVariable typeVariable = (TypeVariable) genericComponentType;
                    Type type2 = defaultJSONParser.getContext().type;
                    if (type2 instanceof ParameterizedType) {
                        T t;
                        ParameterizedType parameterizedType = (ParameterizedType) type2;
                        Type rawType = parameterizedType.getRawType();
                        if (rawType instanceof Class) {
                            TypeVariable[] typeParameters = ((Class) rawType).getTypeParameters();
                            T t2 = null;
                            while (i < typeParameters.length) {
                                if (typeParameters[i].getName().equals(typeVariable.getName())) {
                                    t2 = parameterizedType.getActualTypeArguments()[i];
                                }
                                i++;
                            }
                            t = t2;
                        } else {
                            t = null;
                        }
                        if (t instanceof Class) {
                            obj2 = (Class) t;
                        } else {
                            obj2 = Object.class;
                        }
                    } else {
                        obj2 = TypeUtils.getClass(typeVariable.getBounds()[0]);
                    }
                } else {
                    obj2 = TypeUtils.getClass(genericComponentType);
                }
            } else {
                obj2 = ((Class) type).getComponentType();
            }
            Object jSONArray = new JSONArray();
            defaultJSONParser.parseArray(obj2, jSONArray, obj);
            return toObjectArray(defaultJSONParser, obj2, jSONArray);
        }
    }

    private <T> T toObjectArray(DefaultJSONParser defaultJSONParser, Class<?> cls, JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        int size = jSONArray.size();
        T newInstance = Array.newInstance(cls, size);
        for (int i = 0; i < size; i++) {
            Object obj = jSONArray.get(i);
            if (obj == jSONArray) {
                Array.set(newInstance, i, newInstance);
            } else if (cls.isArray()) {
                if (!cls.isInstance(obj)) {
                    obj = toObjectArray(defaultJSONParser, cls, (JSONArray) obj);
                }
                Array.set(newInstance, i, obj);
            } else {
                Object toArray;
                if (obj instanceof JSONArray) {
                    JSONArray jSONArray2 = (JSONArray) obj;
                    int size2 = jSONArray2.size();
                    Object obj2 = null;
                    for (int i2 = 0; i2 < size2; i2++) {
                        if (jSONArray2.get(i2) == jSONArray) {
                            jSONArray2.set(i, newInstance);
                            obj2 = 1;
                        }
                    }
                    if (obj2 != null) {
                        toArray = jSONArray2.toArray();
                        if (toArray != null) {
                            obj = TypeUtils.cast(obj, (Class) cls, defaultJSONParser.getConfig());
                        } else {
                            obj = toArray;
                        }
                        Array.set(newInstance, i, obj);
                    }
                }
                toArray = null;
                if (toArray != null) {
                    obj = toArray;
                } else {
                    obj = TypeUtils.cast(obj, (Class) cls, defaultJSONParser.getConfig());
                }
                Array.set(newInstance, i, obj);
            }
        }
        jSONArray.setRelatedArray(newInstance);
        jSONArray.setComponentType(cls);
        return newInstance;
    }

    public int getFastMatchToken() {
        return 14;
    }
}
