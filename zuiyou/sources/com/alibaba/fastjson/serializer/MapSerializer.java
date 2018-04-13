package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public final class MapSerializer implements ObjectSerializer {
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        Map treeMap;
        SerialContext serialContext;
        Class cls;
        ObjectSerializer objectSerializer;
        Object obj3;
        Object obj4;
        Object value;
        Object processValue;
        Class cls2;
        ObjectSerializer objectSerializer2;
        Map map = (Map) obj;
        Class cls3 = map.getClass();
        Object obj5 = ((cls3 == JSONObject.class || cls3 == HashMap.class || cls3 == LinkedHashMap.class) && map.containsKey(JSON.DEFAULT_TYPE_KEY)) ? 1 : null;
        if ((serializeWriter.features & SerializerFeature.SortField.mask) != 0) {
            if (map instanceof JSONObject) {
                map = ((JSONObject) map).getInnerMap();
            }
            if (!((map instanceof SortedMap) || (map instanceof LinkedHashMap))) {
                try {
                    treeMap = new TreeMap(map);
                } catch (Exception e) {
                    treeMap = map;
                }
                if (jSONSerializer.references == null && jSONSerializer.references.containsKey(obj)) {
                    jSONSerializer.writeReference(obj);
                    return;
                }
                serialContext = jSONSerializer.context;
                jSONSerializer.setContext(serialContext, obj, obj2, 0);
                serializeWriter.write(123);
                jSONSerializer.incrementIndent();
                cls = null;
                objectSerializer = null;
                obj3 = 1;
                if ((serializeWriter.features & SerializerFeature.WriteClassName.mask) != 0 && obj5 == null) {
                    serializeWriter.writeFieldName(jSONSerializer.config.typeKey, false);
                    serializeWriter.writeString(obj.getClass().getName());
                    obj3 = null;
                }
                obj4 = obj3;
                for (Entry entry : r3.entrySet()) {
                    value = entry.getValue();
                    obj3 = entry.getKey();
                    if (jSONSerializer.applyName(obj, obj3) && jSONSerializer.apply(obj, obj3, value)) {
                        obj5 = jSONSerializer.processKey(obj, obj3, value);
                        processValue = JSONSerializer.processValue(jSONSerializer, obj, obj5, value);
                        if (processValue == null || (serializeWriter.features & SerializerFeature.WriteMapNullValue.mask) != 0) {
                            if (obj5 instanceof String) {
                                if (obj4 == null) {
                                    serializeWriter.write(44);
                                }
                                if ((serializeWriter.features & SerializerFeature.WriteNonStringKeyAsString.mask) != 0 || (obj5 instanceof Enum)) {
                                    jSONSerializer.write(obj5);
                                } else {
                                    jSONSerializer.write(JSON.toJSONString(obj5));
                                }
                                serializeWriter.write(58);
                            } else {
                                String str = (String) obj5;
                                if (obj4 == null) {
                                    serializeWriter.write(44);
                                }
                                if ((serializeWriter.features & SerializerFeature.PrettyFormat.mask) != 0) {
                                    jSONSerializer.println();
                                }
                                serializeWriter.writeFieldName(str, true);
                            }
                            if (processValue != null) {
                                serializeWriter.writeNull();
                                obj4 = null;
                            } else {
                                cls2 = processValue.getClass();
                                if (cls2 != cls) {
                                    objectSerializer.write(jSONSerializer, processValue, obj5, null);
                                    objectSerializer2 = objectSerializer;
                                    cls3 = cls;
                                } else {
                                    objectSerializer2 = jSONSerializer.config.get(cls2);
                                    objectSerializer2.write(jSONSerializer, processValue, obj5, null);
                                    cls3 = cls2;
                                }
                                obj4 = null;
                                objectSerializer = objectSerializer2;
                                cls = cls3;
                            }
                        }
                    }
                }
                jSONSerializer.decrementIdent();
                if ((serializeWriter.features & SerializerFeature.PrettyFormat.mask) != 0 && r3.size() > 0) {
                    jSONSerializer.println();
                }
                serializeWriter.write(125);
            }
        }
        treeMap = map;
        if (jSONSerializer.references == null) {
        }
        serialContext = jSONSerializer.context;
        jSONSerializer.setContext(serialContext, obj, obj2, 0);
        try {
            serializeWriter.write(123);
            jSONSerializer.incrementIndent();
            cls = null;
            objectSerializer = null;
            obj3 = 1;
            serializeWriter.writeFieldName(jSONSerializer.config.typeKey, false);
            serializeWriter.writeString(obj.getClass().getName());
            obj3 = null;
            obj4 = obj3;
            for (Entry entry2 : r3.entrySet()) {
                value = entry2.getValue();
                obj3 = entry2.getKey();
                obj5 = jSONSerializer.processKey(obj, obj3, value);
                processValue = JSONSerializer.processValue(jSONSerializer, obj, obj5, value);
                if (processValue == null) {
                }
                if (obj5 instanceof String) {
                    if (obj4 == null) {
                        serializeWriter.write(44);
                    }
                    if ((serializeWriter.features & SerializerFeature.WriteNonStringKeyAsString.mask) != 0) {
                    }
                    jSONSerializer.write(obj5);
                    serializeWriter.write(58);
                } else {
                    String str2 = (String) obj5;
                    if (obj4 == null) {
                        serializeWriter.write(44);
                    }
                    if ((serializeWriter.features & SerializerFeature.PrettyFormat.mask) != 0) {
                        jSONSerializer.println();
                    }
                    serializeWriter.writeFieldName(str2, true);
                }
                if (processValue != null) {
                    cls2 = processValue.getClass();
                    if (cls2 != cls) {
                        objectSerializer2 = jSONSerializer.config.get(cls2);
                        objectSerializer2.write(jSONSerializer, processValue, obj5, null);
                        cls3 = cls2;
                    } else {
                        objectSerializer.write(jSONSerializer, processValue, obj5, null);
                        objectSerializer2 = objectSerializer;
                        cls3 = cls;
                    }
                    obj4 = null;
                    objectSerializer = objectSerializer2;
                    cls = cls3;
                } else {
                    serializeWriter.writeNull();
                    obj4 = null;
                }
            }
            jSONSerializer.decrementIdent();
            jSONSerializer.println();
            serializeWriter.write(125);
        } finally {
            jSONSerializer.context = serialContext;
        }
    }
}
