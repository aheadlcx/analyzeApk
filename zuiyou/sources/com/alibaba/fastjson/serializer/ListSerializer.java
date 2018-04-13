package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.IdentityHashMap;
import java.util.List;

public final class ListSerializer implements ObjectSerializer {
    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        Object obj3;
        Type collectionItemType;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if ((serializeWriter.features & SerializerFeature.WriteClassName.mask) != 0) {
            obj3 = 1;
        } else {
            obj3 = null;
        }
        if (obj3 != null) {
            collectionItemType = TypeUtils.getCollectionItemType(type);
        } else {
            collectionItemType = null;
        }
        if (obj != null) {
            List list = (List) obj;
            int size = list.size();
            if (size == 0) {
                serializeWriter.append((CharSequence) "[]");
                return;
            }
            SerialContext serialContext = jSONSerializer.context;
            if ((serializeWriter.features & SerializerFeature.DisableCircularReferenceDetect.mask) == 0) {
                jSONSerializer.context = new SerialContext(serialContext, obj, obj2, 0);
                if (jSONSerializer.references == null) {
                    jSONSerializer.references = new IdentityHashMap();
                }
                jSONSerializer.references.put(obj, jSONSerializer.context);
            }
            try {
                int i;
                if ((serializeWriter.features & SerializerFeature.PrettyFormat.mask) != 0) {
                    serializeWriter.write(91);
                    jSONSerializer.incrementIndent();
                    for (i = 0; i < size; i++) {
                        Object obj4 = list.get(i);
                        if (i != 0) {
                            serializeWriter.write(44);
                        }
                        jSONSerializer.println();
                        if (obj4 == null) {
                            jSONSerializer.out.writeNull();
                        } else if (jSONSerializer.references == null || !jSONSerializer.references.containsKey(obj4)) {
                            ObjectSerializer objectSerializer = jSONSerializer.config.get(obj4.getClass());
                            jSONSerializer.context = new SerialContext(serialContext, obj, obj2, 0);
                            objectSerializer.write(jSONSerializer, obj4, Integer.valueOf(i), collectionItemType);
                        } else {
                            jSONSerializer.writeReference(obj4);
                        }
                    }
                    jSONSerializer.decrementIdent();
                    jSONSerializer.println();
                    serializeWriter.write(93);
                    jSONSerializer.context = serialContext;
                    return;
                }
                i = serializeWriter.count + 1;
                if (i > serializeWriter.buf.length) {
                    if (serializeWriter.writer == null) {
                        serializeWriter.expandCapacity(i);
                    } else {
                        serializeWriter.flush();
                        i = 1;
                    }
                }
                serializeWriter.buf[serializeWriter.count] = '[';
                serializeWriter.count = i;
                for (int i2 = 0; i2 < list.size(); i2++) {
                    Object obj5 = list.get(i2);
                    if (i2 != 0) {
                        size = serializeWriter.count + 1;
                        if (size > serializeWriter.buf.length) {
                            if (serializeWriter.writer == null) {
                                serializeWriter.expandCapacity(size);
                            } else {
                                serializeWriter.flush();
                                size = 1;
                            }
                        }
                        serializeWriter.buf[serializeWriter.count] = ',';
                        serializeWriter.count = size;
                    }
                    if (obj5 == null) {
                        serializeWriter.append((CharSequence) "null");
                    } else {
                        Class cls = obj5.getClass();
                        if (cls == Integer.class) {
                            serializeWriter.writeInt(((Integer) obj5).intValue());
                        } else if (cls == Long.class) {
                            long longValue = ((Long) obj5).longValue();
                            if (obj3 != null) {
                                serializeWriter.writeLong(longValue);
                                serializeWriter.write(76);
                            } else {
                                serializeWriter.writeLong(longValue);
                            }
                        } else if (cls == String.class) {
                            String str = (String) obj5;
                            if ((serializeWriter.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
                                serializeWriter.writeStringWithSingleQuote(str);
                            } else {
                                serializeWriter.writeStringWithDoubleQuote(str, '\u0000', true);
                            }
                        } else {
                            if ((serializeWriter.features & SerializerFeature.DisableCircularReferenceDetect.mask) == 0) {
                                jSONSerializer.context = new SerialContext(serialContext, obj, obj2, 0);
                            }
                            if (jSONSerializer.references == null || !jSONSerializer.references.containsKey(obj5)) {
                                jSONSerializer.config.get(obj5.getClass()).write(jSONSerializer, obj5, Integer.valueOf(i2), collectionItemType);
                            } else {
                                jSONSerializer.writeReference(obj5);
                            }
                        }
                    }
                }
                int i3 = serializeWriter.count + 1;
                if (i3 > serializeWriter.buf.length) {
                    if (serializeWriter.writer == null) {
                        serializeWriter.expandCapacity(i3);
                    } else {
                        serializeWriter.flush();
                        i3 = 1;
                    }
                }
                serializeWriter.buf[serializeWriter.count] = ']';
                serializeWriter.count = i3;
                jSONSerializer.context = serialContext;
            } catch (Throwable th) {
                jSONSerializer.context = serialContext;
            }
        } else if ((serializeWriter.features & SerializerFeature.WriteNullListAsEmpty.mask) != 0) {
            serializeWriter.write("[]");
        } else {
            serializeWriter.writeNull();
        }
    }
}
