package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class CollectionCodec implements ObjectDeserializer, ObjectSerializer {
    public static final CollectionCodec instance = new CollectionCodec();

    private CollectionCodec() {
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        int i = 0;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj != null) {
            Type collectionItemType;
            if ((serializeWriter.features & SerializerFeature.WriteClassName.mask) != 0) {
                collectionItemType = TypeUtils.getCollectionItemType(type);
            } else {
                collectionItemType = null;
            }
            Collection collection = (Collection) obj;
            SerialContext serialContext = jSONSerializer.context;
            jSONSerializer.setContext(serialContext, obj, obj2, 0);
            if ((serializeWriter.features & SerializerFeature.WriteClassName.mask) != 0) {
                if (HashSet.class == collection.getClass()) {
                    serializeWriter.append((CharSequence) "Set");
                } else if (TreeSet.class == collection.getClass()) {
                    serializeWriter.append((CharSequence) "TreeSet");
                }
            }
            try {
                serializeWriter.write(91);
                for (Object next : collection) {
                    int i2 = i + 1;
                    if (i != 0) {
                        serializeWriter.write(44);
                    }
                    if (next == null) {
                        serializeWriter.writeNull();
                        i = i2;
                    } else {
                        Class cls = next.getClass();
                        if (cls == Integer.class) {
                            serializeWriter.writeInt(((Integer) next).intValue());
                            i = i2;
                        } else if (cls == Long.class) {
                            serializeWriter.writeLong(((Long) next).longValue());
                            if ((serializeWriter.features & SerializerFeature.WriteClassName.mask) != 0) {
                                serializeWriter.write(76);
                                i = i2;
                            } else {
                                i = i2;
                            }
                        } else {
                            jSONSerializer.config.get(cls).write(jSONSerializer, next, Integer.valueOf(i2 - 1), collectionItemType);
                            i = i2;
                        }
                    }
                }
                serializeWriter.write(93);
            } finally {
                jSONSerializer.context = serialContext;
            }
        } else if ((serializeWriter.features & SerializerFeature.WriteNullListAsEmpty.mask) != 0) {
            serializeWriter.write("[]");
        } else {
            serializeWriter.writeNull();
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (defaultJSONParser.lexer.token() == 8) {
            defaultJSONParser.lexer.nextToken(16);
            return null;
        } else if (type == JSONArray.class) {
            Collection jSONArray = new JSONArray();
            defaultJSONParser.parseArray(jSONArray);
            return jSONArray;
        } else {
            T arrayList;
            Type type2 = type;
            while (!(type2 instanceof Class)) {
                if (type2 instanceof ParameterizedType) {
                    type2 = ((ParameterizedType) type2).getRawType();
                } else {
                    throw new JSONException("TODO");
                }
            }
            Class cls = (Class) type2;
            if (cls == AbstractCollection.class || cls == Collection.class) {
                arrayList = new ArrayList();
            } else if (cls.isAssignableFrom(HashSet.class)) {
                arrayList = new HashSet();
            } else if (cls.isAssignableFrom(LinkedHashSet.class)) {
                arrayList = new LinkedHashSet();
            } else if (cls.isAssignableFrom(TreeSet.class)) {
                arrayList = new TreeSet();
            } else if (cls.isAssignableFrom(ArrayList.class)) {
                arrayList = new ArrayList();
            } else if (cls.isAssignableFrom(EnumSet.class)) {
                if (type instanceof ParameterizedType) {
                    cls = ((ParameterizedType) type).getActualTypeArguments()[0];
                } else {
                    cls = Object.class;
                }
                arrayList = EnumSet.noneOf(cls);
            } else {
                try {
                    arrayList = (Collection) cls.newInstance();
                } catch (Exception e) {
                    throw new JSONException("create instane error, class " + cls.getName());
                }
            }
            defaultJSONParser.parseArray(TypeUtils.getCollectionItemType(type), arrayList, obj);
            return arrayList;
        }
    }
}
