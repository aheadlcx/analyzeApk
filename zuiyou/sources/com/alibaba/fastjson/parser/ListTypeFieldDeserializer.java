package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

class ListTypeFieldDeserializer extends FieldDeserializer {
    private final boolean array;
    private ObjectDeserializer deserializer;
    private final Type itemType;

    public ListTypeFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo, 14);
        Type type = fieldInfo.fieldType;
        Class cls2 = fieldInfo.fieldClass;
        if (cls2.isArray()) {
            this.itemType = cls2.getComponentType();
            this.array = true;
            return;
        }
        this.itemType = TypeUtils.getCollectionItemType(type);
        this.array = false;
    }

    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int token = jSONLexer.token();
        if (token == 8 || (token == 4 && jSONLexer.stringVal().length() == 0)) {
            setValue(obj, null);
            defaultJSONParser.lexer.nextToken();
            return;
        }
        Object obj2;
        JSONArray jSONArray;
        if (this.array) {
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.setComponentType(this.itemType);
            obj2 = jSONArray2;
            jSONArray = jSONArray2;
        } else {
            jSONArray = null;
            ArrayList arrayList = new ArrayList();
        }
        ParseContext parseContext = defaultJSONParser.contex;
        defaultJSONParser.setContext(parseContext, obj, this.fieldInfo.name);
        parseArray(defaultJSONParser, type, obj2);
        defaultJSONParser.setContext(parseContext);
        if (this.array) {
            obj2 = obj2.toArray((Object[]) Array.newInstance((Class) this.itemType, obj2.size()));
            jSONArray.setRelatedArray(obj2);
        }
        if (obj == null) {
            map.put(this.fieldInfo.name, obj2);
        } else {
            setValue(obj, obj2);
        }
    }

    final void parseArray(DefaultJSONParser defaultJSONParser, Type type, Collection collection) {
        int i;
        int i2;
        Type type2 = this.itemType;
        ObjectDeserializer objectDeserializer = this.deserializer;
        if (type instanceof ParameterizedType) {
            if (type2 instanceof TypeVariable) {
                Class cls;
                ObjectDeserializer deserializer;
                TypeVariable typeVariable = (TypeVariable) type2;
                ParameterizedType parameterizedType = (ParameterizedType) type;
                if (parameterizedType.getRawType() instanceof Class) {
                    cls = (Class) parameterizedType.getRawType();
                } else {
                    cls = null;
                }
                if (cls != null) {
                    int length = cls.getTypeParameters().length;
                    for (int i3 = 0; i3 < length; i3++) {
                        if (cls.getTypeParameters()[i3].getName().equals(typeVariable.getName())) {
                            i = i3;
                            break;
                        }
                    }
                }
                i = -1;
                if (i != -1) {
                    type2 = parameterizedType.getActualTypeArguments()[i];
                    if (!type2.equals(this.itemType)) {
                        deserializer = defaultJSONParser.config.getDeserializer(type2);
                        objectDeserializer = deserializer;
                    }
                }
                deserializer = objectDeserializer;
                objectDeserializer = deserializer;
            } else if (type2 instanceof ParameterizedType) {
                ParameterizedType parameterizedType2 = (ParameterizedType) type2;
                Type[] actualTypeArguments = parameterizedType2.getActualTypeArguments();
                if (actualTypeArguments.length == 1 && (actualTypeArguments[0] instanceof TypeVariable)) {
                    Class cls2;
                    TypeVariable typeVariable2 = (TypeVariable) actualTypeArguments[0];
                    ParameterizedType parameterizedType3 = (ParameterizedType) type;
                    if (parameterizedType3.getRawType() instanceof Class) {
                        cls2 = (Class) parameterizedType3.getRawType();
                    } else {
                        cls2 = null;
                    }
                    if (cls2 != null) {
                        int length2 = cls2.getTypeParameters().length;
                        for (int i4 = 0; i4 < length2; i4++) {
                            if (cls2.getTypeParameters()[i4].getName().equals(typeVariable2.getName())) {
                                i2 = i4;
                                break;
                            }
                        }
                    }
                    i2 = -1;
                    if (i2 != -1) {
                        actualTypeArguments[0] = parameterizedType3.getActualTypeArguments()[i2];
                        type2 = new ParameterizedTypeImpl(actualTypeArguments, parameterizedType2.getOwnerType(), parameterizedType2.getRawType());
                    }
                }
            }
        }
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (objectDeserializer == null) {
            objectDeserializer = defaultJSONParser.config.getDeserializer(type2);
            this.deserializer = objectDeserializer;
        }
        if (jSONLexer.token == 14) {
            char c = jSONLexer.ch;
            if (c == '[') {
                i = jSONLexer.bp + 1;
                jSONLexer.bp = i;
                if (i >= jSONLexer.len) {
                    c = JSONLexer.EOI;
                } else {
                    c = jSONLexer.text.charAt(i);
                }
                jSONLexer.ch = c;
                jSONLexer.token = 14;
            } else if (c == '{') {
                i = jSONLexer.bp + 1;
                jSONLexer.bp = i;
                if (i >= jSONLexer.len) {
                    c = JSONLexer.EOI;
                } else {
                    c = jSONLexer.text.charAt(i);
                }
                jSONLexer.ch = c;
                jSONLexer.token = 12;
            } else if (c == '\"') {
                jSONLexer.scanString();
            } else if (c == ']') {
                i = jSONLexer.bp + 1;
                jSONLexer.bp = i;
                if (i >= jSONLexer.len) {
                    c = JSONLexer.EOI;
                } else {
                    c = jSONLexer.text.charAt(i);
                }
                jSONLexer.ch = c;
                jSONLexer.token = 15;
            } else {
                jSONLexer.nextToken();
            }
            i = 0;
            while (true) {
                if (jSONLexer.token == 16) {
                    jSONLexer.nextToken();
                } else if (jSONLexer.token == 15) {
                    break;
                } else {
                    collection.add(objectDeserializer.deserialze(defaultJSONParser, type2, Integer.valueOf(i)));
                    if (defaultJSONParser.resolveStatus == 1) {
                        defaultJSONParser.checkListResolve(collection);
                    }
                    if (jSONLexer.token == 16) {
                        char c2 = jSONLexer.ch;
                        if (c2 == '[') {
                            i2 = jSONLexer.bp + 1;
                            jSONLexer.bp = i2;
                            if (i2 >= jSONLexer.len) {
                                c2 = JSONLexer.EOI;
                            } else {
                                c2 = jSONLexer.text.charAt(i2);
                            }
                            jSONLexer.ch = c2;
                            jSONLexer.token = 14;
                        } else if (c2 == '{') {
                            i2 = jSONLexer.bp + 1;
                            jSONLexer.bp = i2;
                            if (i2 >= jSONLexer.len) {
                                c2 = JSONLexer.EOI;
                            } else {
                                c2 = jSONLexer.text.charAt(i2);
                            }
                            jSONLexer.ch = c2;
                            jSONLexer.token = 12;
                        } else if (c2 == '\"') {
                            jSONLexer.scanString();
                        } else {
                            jSONLexer.nextToken();
                        }
                    }
                    i++;
                }
            }
            if (jSONLexer.ch == ',') {
                i = jSONLexer.bp + 1;
                jSONLexer.bp = i;
                if (i >= jSONLexer.len) {
                    c = JSONLexer.EOI;
                } else {
                    c = jSONLexer.text.charAt(i);
                }
                jSONLexer.ch = c;
                jSONLexer.token = 16;
                return;
            }
            jSONLexer.nextToken();
        } else if (jSONLexer.token == 12) {
            collection.add(objectDeserializer.deserialze(defaultJSONParser, type2, Integer.valueOf(0)));
        } else {
            String str = "exepct '[', but " + JSONToken.name(jSONLexer.token);
            if (type != null) {
                str = str + ", type : " + type;
            }
            throw new JSONException(str);
        }
    }
}
