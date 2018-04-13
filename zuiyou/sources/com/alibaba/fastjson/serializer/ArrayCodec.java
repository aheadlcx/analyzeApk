package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;

public final class ArrayCodec implements ObjectDeserializer, ObjectSerializer {
    public static final ArrayCodec instance = new ArrayCodec();

    private ArrayCodec() {
    }

    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        int i = 0;
        Class cls = null;
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
            serializeWriter.write(91);
            if ((serializeWriter.features & SerializerFeature.PrettyFormat.mask) != 0) {
                jSONSerializer.incrementIndent();
                jSONSerializer.println();
                while (i < length) {
                    if (i != 0) {
                        serializeWriter.write(44);
                        jSONSerializer.println();
                    }
                    jSONSerializer.write(objArr[i]);
                    i++;
                }
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
                serializeWriter.write(93);
                return;
            }
            ObjectSerializer objectSerializer = null;
            for (length = 0; length < i2; length++) {
                Object obj3 = objArr[length];
                if (obj3 == null) {
                    serializeWriter.append((CharSequence) "null,");
                } else {
                    if (jSONSerializer.references == null || !jSONSerializer.references.containsKey(obj3)) {
                        try {
                            Class cls2 = obj3.getClass();
                            if (cls2 == cls) {
                                objectSerializer.write(jSONSerializer, obj3, null, null);
                            } else {
                                objectSerializer = jSONSerializer.config.get(cls2);
                                objectSerializer.write(jSONSerializer, obj3, null, null);
                                cls = cls2;
                            }
                        } finally {
                            jSONSerializer.context = serialContext;
                        }
                    } else {
                        jSONSerializer.writeReference(obj3);
                    }
                    serializeWriter.write(44);
                }
            }
            Object obj4 = objArr[i2];
            if (obj4 == null) {
                serializeWriter.append((CharSequence) "null]");
            } else {
                if (jSONSerializer.references == null || !jSONSerializer.references.containsKey(obj4)) {
                    jSONSerializer.writeWithFieldName(obj4, Integer.valueOf(i2));
                } else {
                    jSONSerializer.writeReference(obj4);
                }
                serializeWriter.write(93);
            }
            jSONSerializer.context = serialContext;
        } else if ((serializeWriter.features & SerializerFeature.WriteNullListAsEmpty.mask) != 0) {
            serializeWriter.write("[]");
        } else {
            serializeWriter.writeNull();
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int token = jSONLexer.token();
        if (token == 8) {
            jSONLexer.nextToken(16);
            return null;
        } else if (type == char[].class) {
            if (token == 4) {
                String stringVal = jSONLexer.stringVal();
                jSONLexer.nextToken(16);
                return stringVal.toCharArray();
            } else if (token != 2) {
                return JSON.toJSONString(defaultJSONParser.parse()).toCharArray();
            } else {
                Number integerValue = jSONLexer.integerValue();
                jSONLexer.nextToken(16);
                return integerValue.toString().toCharArray();
            }
        } else if (token == 4) {
            T bytesValue = jSONLexer.bytesValue();
            jSONLexer.nextToken(16);
            return bytesValue;
        } else {
            Object componentType = ((Class) type).getComponentType();
            Object jSONArray = new JSONArray();
            defaultJSONParser.parseArray(componentType, jSONArray, obj);
            return toObjectArray(defaultJSONParser, componentType, jSONArray);
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
            } else {
                if (!cls.isArray()) {
                    obj = TypeUtils.cast(obj, (Class) cls, defaultJSONParser.config);
                } else if (!cls.isInstance(obj)) {
                    obj = toObjectArray(defaultJSONParser, cls, (JSONArray) obj);
                }
                Array.set(newInstance, i, obj);
            }
        }
        jSONArray.setRelatedArray(newInstance);
        jSONArray.setComponentType(cls);
        return newInstance;
    }
}
