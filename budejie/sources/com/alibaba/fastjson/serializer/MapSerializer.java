package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapSerializer implements ObjectSerializer {
    public static MapSerializer instance = new MapSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        Map map = (Map) obj;
        if (jSONSerializer.containsReference(obj)) {
            jSONSerializer.writeReference(obj);
            return;
        }
        SerialContext serialContext = jSONSerializer.context;
        jSONSerializer.setContext(serialContext, obj, obj2, 0);
        try {
            Object obj3;
            serializeWriter.write(123);
            jSONSerializer.incrementIndent();
            Class cls = null;
            Object obj4 = 1;
            if (serializeWriter.isEnabled(SerializerFeature.WriteClassName)) {
                Class cls2 = map.getClass();
                obj3 = ((cls2 == JSONObject.class || cls2 == HashMap.class || cls2 == LinkedHashMap.class) && map.containsKey(JSON.DEFAULT_TYPE_KEY)) ? 1 : null;
                if (obj3 == null) {
                    serializeWriter.writeFieldName(JSON.DEFAULT_TYPE_KEY);
                    serializeWriter.writeString(obj.getClass().getName());
                    obj4 = null;
                }
            }
            Object obj5 = obj4;
            ObjectSerializer objectSerializer = null;
            for (Entry entry : map.entrySet()) {
                Object processKey;
                List list;
                String str;
                Class cls3;
                Class cls4;
                Object value = entry.getValue();
                obj3 = entry.getKey();
                List list2 = jSONSerializer.propertyPreFilters;
                if (list2 != null && list2.size() > 0) {
                    if (obj3 == null || (obj3 instanceof String)) {
                        if (!jSONSerializer.applyName(obj, (String) obj3)) {
                            continue;
                        }
                    } else if (obj3.getClass().isPrimitive() || (obj3 instanceof Number)) {
                        if (!jSONSerializer.applyName(obj, JSON.toJSONString(obj3))) {
                        }
                    }
                }
                list2 = jSONSerializer.propertyFilters;
                if (list2 != null && list2.size() > 0) {
                    if (obj3 == null || (obj3 instanceof String)) {
                        if (!jSONSerializer.apply(obj, (String) obj3, value)) {
                            continue;
                        }
                    } else if (obj3.getClass().isPrimitive() || (obj3 instanceof Number)) {
                        if (!jSONSerializer.apply(obj, JSON.toJSONString(obj3), value)) {
                        }
                    }
                }
                list2 = jSONSerializer.nameFilters;
                if (list2 != null && list2.size() > 0) {
                    if (obj3 == null || (obj3 instanceof String)) {
                        processKey = jSONSerializer.processKey(obj, (String) obj3, value);
                        list2 = jSONSerializer.valueFilters;
                        list = jSONSerializer.contextValueFilters;
                        if ((list2 != null && list2.size() > 0) || (list != null && list.size() > 0)) {
                            if (processKey != null || (processKey instanceof String)) {
                                obj3 = jSONSerializer.processValue(null, obj, (String) processKey, value);
                                if (obj3 == null || serializeWriter.isEnabled(SerializerFeature.WriteMapNullValue)) {
                                    if (processKey instanceof String) {
                                        str = (String) processKey;
                                        if (obj5 == null) {
                                            serializeWriter.write(44);
                                        }
                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                            jSONSerializer.println();
                                        }
                                        serializeWriter.writeFieldName(str, true);
                                    } else {
                                        if (obj5 == null) {
                                            serializeWriter.write(44);
                                        }
                                        if (!serializeWriter.isEnabled(SerializerFeature.BrowserCompatible) || serializeWriter.isEnabled(SerializerFeature.WriteNonStringKeyAsString) || serializeWriter.isEnabled(SerializerFeature.BrowserSecure)) {
                                            jSONSerializer.write(JSON.toJSONString(processKey));
                                        } else {
                                            jSONSerializer.write(processKey);
                                        }
                                        serializeWriter.write(58);
                                    }
                                    if (obj3 == null) {
                                        serializeWriter.writeNull();
                                        obj5 = null;
                                    } else {
                                        cls3 = obj3.getClass();
                                        if (cls3 == cls) {
                                            objectSerializer.write(jSONSerializer, obj3, processKey, null, 0);
                                            cls4 = cls;
                                        } else {
                                            objectSerializer = jSONSerializer.getObjectWriter(cls3);
                                            objectSerializer.write(jSONSerializer, obj3, processKey, null, 0);
                                            cls4 = cls3;
                                        }
                                        obj5 = null;
                                        cls = cls4;
                                    }
                                }
                            } else if (processKey.getClass().isPrimitive() || (processKey instanceof Number)) {
                                obj3 = jSONSerializer.processValue(null, obj, JSON.toJSONString(processKey), value);
                                if (obj3 == null) {
                                }
                                if (processKey instanceof String) {
                                    if (obj5 == null) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.BrowserCompatible)) {
                                    }
                                    jSONSerializer.write(JSON.toJSONString(processKey));
                                    serializeWriter.write(58);
                                } else {
                                    str = (String) processKey;
                                    if (obj5 == null) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    serializeWriter.writeFieldName(str, true);
                                }
                                if (obj3 == null) {
                                    cls3 = obj3.getClass();
                                    if (cls3 == cls) {
                                        objectSerializer = jSONSerializer.getObjectWriter(cls3);
                                        objectSerializer.write(jSONSerializer, obj3, processKey, null, 0);
                                        cls4 = cls3;
                                    } else {
                                        objectSerializer.write(jSONSerializer, obj3, processKey, null, 0);
                                        cls4 = cls;
                                    }
                                    obj5 = null;
                                    cls = cls4;
                                } else {
                                    serializeWriter.writeNull();
                                    obj5 = null;
                                }
                            }
                        }
                        obj3 = value;
                        if (obj3 == null) {
                        }
                        if (processKey instanceof String) {
                            str = (String) processKey;
                            if (obj5 == null) {
                                serializeWriter.write(44);
                            }
                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                jSONSerializer.println();
                            }
                            serializeWriter.writeFieldName(str, true);
                        } else {
                            if (obj5 == null) {
                                serializeWriter.write(44);
                            }
                            if (serializeWriter.isEnabled(SerializerFeature.BrowserCompatible)) {
                            }
                            jSONSerializer.write(JSON.toJSONString(processKey));
                            serializeWriter.write(58);
                        }
                        if (obj3 == null) {
                            serializeWriter.writeNull();
                            obj5 = null;
                        } else {
                            cls3 = obj3.getClass();
                            if (cls3 == cls) {
                                objectSerializer.write(jSONSerializer, obj3, processKey, null, 0);
                                cls4 = cls;
                            } else {
                                objectSerializer = jSONSerializer.getObjectWriter(cls3);
                                objectSerializer.write(jSONSerializer, obj3, processKey, null, 0);
                                cls4 = cls3;
                            }
                            obj5 = null;
                            cls = cls4;
                        }
                    } else if (obj3.getClass().isPrimitive() || (obj3 instanceof Number)) {
                        String processKey2 = jSONSerializer.processKey(obj, JSON.toJSONString(obj3), value);
                        list2 = jSONSerializer.valueFilters;
                        list = jSONSerializer.contextValueFilters;
                        if (processKey != null) {
                        }
                        obj3 = jSONSerializer.processValue(null, obj, (String) processKey, value);
                        if (obj3 == null) {
                        }
                        if (processKey instanceof String) {
                            if (obj5 == null) {
                                serializeWriter.write(44);
                            }
                            if (serializeWriter.isEnabled(SerializerFeature.BrowserCompatible)) {
                            }
                            jSONSerializer.write(JSON.toJSONString(processKey));
                            serializeWriter.write(58);
                        } else {
                            str = (String) processKey;
                            if (obj5 == null) {
                                serializeWriter.write(44);
                            }
                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                jSONSerializer.println();
                            }
                            serializeWriter.writeFieldName(str, true);
                        }
                        if (obj3 == null) {
                            cls3 = obj3.getClass();
                            if (cls3 == cls) {
                                objectSerializer = jSONSerializer.getObjectWriter(cls3);
                                objectSerializer.write(jSONSerializer, obj3, processKey, null, 0);
                                cls4 = cls3;
                            } else {
                                objectSerializer.write(jSONSerializer, obj3, processKey, null, 0);
                                cls4 = cls;
                            }
                            obj5 = null;
                            cls = cls4;
                        } else {
                            serializeWriter.writeNull();
                            obj5 = null;
                        }
                    }
                }
                processKey = obj3;
                list2 = jSONSerializer.valueFilters;
                list = jSONSerializer.contextValueFilters;
                if (processKey != null) {
                }
                obj3 = jSONSerializer.processValue(null, obj, (String) processKey, value);
                if (obj3 == null) {
                }
                if (processKey instanceof String) {
                    str = (String) processKey;
                    if (obj5 == null) {
                        serializeWriter.write(44);
                    }
                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                        jSONSerializer.println();
                    }
                    serializeWriter.writeFieldName(str, true);
                } else {
                    if (obj5 == null) {
                        serializeWriter.write(44);
                    }
                    if (serializeWriter.isEnabled(SerializerFeature.BrowserCompatible)) {
                    }
                    jSONSerializer.write(JSON.toJSONString(processKey));
                    serializeWriter.write(58);
                }
                if (obj3 == null) {
                    serializeWriter.writeNull();
                    obj5 = null;
                } else {
                    cls3 = obj3.getClass();
                    if (cls3 == cls) {
                        objectSerializer.write(jSONSerializer, obj3, processKey, null, 0);
                        cls4 = cls;
                    } else {
                        objectSerializer = jSONSerializer.getObjectWriter(cls3);
                        objectSerializer.write(jSONSerializer, obj3, processKey, null, 0);
                        cls4 = cls3;
                    }
                    obj5 = null;
                    cls = cls4;
                }
            }
            jSONSerializer.decrementIdent();
            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat) && map.size() > 0) {
                jSONSerializer.println();
            }
            serializeWriter.write(125);
        } finally {
            jSONSerializer.context = serialContext;
        }
    }
}
