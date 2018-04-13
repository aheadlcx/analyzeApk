package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class ArrayListTypeFieldDeserializer extends FieldDeserializer {
    private ObjectDeserializer deserializer;
    private int itemFastMatchToken;
    private final Type itemType;

    public ArrayListTypeFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
        if (fieldInfo.fieldType instanceof ParameterizedType) {
            this.itemType = ((ParameterizedType) fieldInfo.fieldType).getActualTypeArguments()[0];
        } else {
            this.itemType = Object.class;
        }
    }

    public int getFastMatchToken() {
        return 14;
    }

    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        if (defaultJSONParser.lexer.token() == 8) {
            setValue(obj, null);
            return;
        }
        Collection arrayList = new ArrayList();
        ParseContext context = defaultJSONParser.getContext();
        defaultJSONParser.setContext(context, obj, this.fieldInfo.name);
        parseArray(defaultJSONParser, type, arrayList);
        defaultJSONParser.setContext(context);
        if (obj == null) {
            map.put(this.fieldInfo.name, arrayList);
        } else {
            setValue(obj, (Object) arrayList);
        }
    }

    public final void parseArray(DefaultJSONParser defaultJSONParser, Type type, Collection collection) {
        ObjectDeserializer deserializer;
        JSONLexer jSONLexer;
        String str;
        int i;
        Type type2 = this.itemType;
        ObjectDeserializer objectDeserializer = this.deserializer;
        if ((type2 instanceof TypeVariable) && (type instanceof ParameterizedType)) {
            Class cls;
            int i2;
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
                        i2 = i3;
                        break;
                    }
                }
            }
            i2 = -1;
            if (i2 != -1) {
                type2 = parameterizedType.getActualTypeArguments()[i2];
                if (!type2.equals(this.itemType)) {
                    deserializer = defaultJSONParser.getConfig().getDeserializer(type2);
                    jSONLexer = defaultJSONParser.lexer;
                    if (jSONLexer.token() == 14) {
                        str = "exepct '[', but " + JSONToken.name(jSONLexer.token());
                        if (type != null) {
                            str = str + ", type : " + type;
                        }
                        throw new JSONException(str);
                    }
                    if (deserializer == null) {
                        deserializer = defaultJSONParser.getConfig().getDeserializer(type2);
                        this.deserializer = deserializer;
                        this.itemFastMatchToken = this.deserializer.getFastMatchToken();
                    }
                    jSONLexer.nextToken(this.itemFastMatchToken);
                    i = 0;
                    while (true) {
                        if (jSONLexer.isEnabled(Feature.AllowArbitraryCommas)) {
                            while (jSONLexer.token() == 16) {
                                jSONLexer.nextToken();
                            }
                        }
                        if (jSONLexer.token() != 15) {
                            jSONLexer.nextToken(16);
                            return;
                        }
                        collection.add(deserializer.deserialze(defaultJSONParser, type2, Integer.valueOf(i)));
                        defaultJSONParser.checkListResolve(collection);
                        if (jSONLexer.token() == 16) {
                            jSONLexer.nextToken(this.itemFastMatchToken);
                        }
                        i++;
                    }
                }
            }
        }
        deserializer = objectDeserializer;
        jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 14) {
            if (deserializer == null) {
                deserializer = defaultJSONParser.getConfig().getDeserializer(type2);
                this.deserializer = deserializer;
                this.itemFastMatchToken = this.deserializer.getFastMatchToken();
            }
            jSONLexer.nextToken(this.itemFastMatchToken);
            i = 0;
            while (true) {
                if (jSONLexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (jSONLexer.token() == 16) {
                        jSONLexer.nextToken();
                    }
                }
                if (jSONLexer.token() != 15) {
                    collection.add(deserializer.deserialze(defaultJSONParser, type2, Integer.valueOf(i)));
                    defaultJSONParser.checkListResolve(collection);
                    if (jSONLexer.token() == 16) {
                        jSONLexer.nextToken(this.itemFastMatchToken);
                    }
                    i++;
                } else {
                    jSONLexer.nextToken(16);
                    return;
                }
            }
        }
        str = "exepct '[', but " + JSONToken.name(jSONLexer.token());
        if (type != null) {
            str = str + ", type : " + type;
        }
        throw new JSONException(str);
    }
}
