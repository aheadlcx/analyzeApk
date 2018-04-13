package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JavaBeanSerializer implements ObjectSerializer {
    private static final char[] false_chars = new char[]{'f', 'a', 'l', 's', 'e'};
    private static final char[] true_chars = new char[]{'t', 'r', 'u', 'e'};
    protected int features;
    private final FieldSerializer[] getters;
    private final FieldSerializer[] sortedGetters;
    protected final String typeKey;
    protected final String typeName;

    public JavaBeanSerializer(Class<?> cls) {
        this((Class) cls, (PropertyNamingStrategy) null);
    }

    public JavaBeanSerializer(Class<?> cls, PropertyNamingStrategy propertyNamingStrategy) {
        this(cls, cls.getModifiers(), (Map) null, false, true, true, true, propertyNamingStrategy);
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this(cls, cls.getModifiers(), map(strArr), false, true, true, true, null);
    }

    private static Map<String, String> map(String... strArr) {
        Map<String, String> hashMap = new HashMap();
        for (Object obj : strArr) {
            hashMap.put(obj, obj);
        }
        return hashMap;
    }

    public JavaBeanSerializer(Class<?> cls, int i, Map<String, String> map, boolean z, boolean z2, boolean z3, boolean z4, PropertyNamingStrategy propertyNamingStrategy) {
        this.features = 0;
        JSONType jSONType = z2 ? (JSONType) cls.getAnnotation(JSONType.class) : null;
        String str = null;
        String str2 = null;
        if (jSONType != null) {
            this.features = SerializerFeature.of(jSONType.serialzeFeatures());
            String typeName = jSONType.typeName();
            if (typeName.length() == 0) {
                str = null;
            } else {
                JSONType jSONType2;
                Class superclass = cls.getSuperclass();
                str = null;
                while (superclass != null && superclass != Object.class) {
                    jSONType2 = (JSONType) superclass.getAnnotation(JSONType.class);
                    if (jSONType2 != null) {
                        str2 = jSONType2.typeKey();
                        if (str2.length() != 0) {
                            break;
                        }
                        superclass = superclass.getSuperclass();
                        str = str2;
                    } else {
                        str2 = str;
                        break;
                    }
                }
                str2 = str;
                Class[] interfaces = cls.getInterfaces();
                int length = interfaces.length;
                int i2 = 0;
                str = str2;
                while (i2 < length) {
                    jSONType2 = (JSONType) interfaces[i2].getAnnotation(JSONType.class);
                    if (jSONType2 != null) {
                        str2 = jSONType2.typeKey();
                        if (str2.length() != 0) {
                            break;
                        }
                    } else {
                        str2 = str;
                    }
                    i2++;
                    str = str2;
                }
                str2 = str;
                if (str2 == null || str2.length() != 0) {
                    str = typeName;
                } else {
                    str2 = null;
                    str = typeName;
                }
            }
        }
        this.typeName = str;
        this.typeKey = str2;
        List<FieldInfo> computeGetters = TypeUtils.computeGetters(cls, i, z, jSONType, map, false, z3, z4, propertyNamingStrategy);
        List arrayList = new ArrayList();
        for (FieldInfo fieldSerializer : computeGetters) {
            arrayList.add(new FieldSerializer(fieldSerializer));
        }
        this.getters = (FieldSerializer[]) arrayList.toArray(new FieldSerializer[arrayList.size()]);
        String[] strArr = null;
        if (jSONType != null) {
            strArr = jSONType.orders();
        }
        if (strArr == null || strArr.length == 0) {
            Object obj = new FieldSerializer[this.getters.length];
            System.arraycopy(this.getters, 0, obj, 0, this.getters.length);
            Arrays.sort(obj);
            if (Arrays.equals(obj, this.getters)) {
                this.sortedGetters = this.getters;
                return;
            } else {
                this.sortedGetters = obj;
                return;
            }
        }
        computeGetters = TypeUtils.computeGetters(cls, i, z, jSONType, map, true, z3, z4, propertyNamingStrategy);
        arrayList = new ArrayList();
        for (FieldInfo fieldSerializer2 : computeGetters) {
            arrayList.add(new FieldSerializer(fieldSerializer2));
        }
        this.sortedGetters = (FieldSerializer[]) arrayList.toArray(new FieldSerializer[arrayList.size()]);
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        String str;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
        } else if ((jSONSerializer.context == null || (jSONSerializer.context.features & SerializerFeature.DisableCircularReferenceDetect.mask) == 0) && jSONSerializer.references != null && jSONSerializer.references.containsKey(obj)) {
            jSONSerializer.writeReference(obj);
        } else {
            FieldSerializer[] fieldSerializerArr;
            if ((serializeWriter.features & SerializerFeature.SortField.mask) != 0) {
                fieldSerializerArr = this.sortedGetters;
            } else {
                fieldSerializerArr = this.getters;
            }
            SerialContext serialContext = jSONSerializer.context;
            if ((serializeWriter.features & SerializerFeature.DisableCircularReferenceDetect.mask) == 0) {
                jSONSerializer.context = new SerialContext(serialContext, obj, obj2, this.features);
                if (jSONSerializer.references == null) {
                    jSONSerializer.references = new IdentityHashMap();
                }
                jSONSerializer.references.put(obj, jSONSerializer.context);
            }
            Object obj3 = ((this.features & SerializerFeature.BeanToArray.mask) == 0 && (serializeWriter.features & SerializerFeature.BeanToArray.mask) == 0) ? null : 1;
            char c = obj3 != null ? '[' : '{';
            char c2 = obj3 != null ? ']' : '}';
            try {
                int i = serializeWriter.count + 1;
                if (i > serializeWriter.buf.length) {
                    if (serializeWriter.writer == null) {
                        serializeWriter.expandCapacity(i);
                    } else {
                        serializeWriter.flush();
                        i = 1;
                    }
                }
                serializeWriter.buf[serializeWriter.count] = c;
                serializeWriter.count = i;
                if (fieldSerializerArr.length > 0 && (serializeWriter.features & SerializerFeature.PrettyFormat.mask) != 0) {
                    jSONSerializer.incrementIndent();
                    jSONSerializer.println();
                }
                Object obj4 = null;
                Object obj5 = ((this.features & SerializerFeature.WriteClassName.mask) == 0 && ((serializeWriter.features & SerializerFeature.WriteClassName.mask) == 0 || (type == null && (serializeWriter.features & SerializerFeature.NotWriteRootClassName.mask) != 0 && (jSONSerializer.context == null || jSONSerializer.context.parent == null)))) ? null : 1;
                if (!(obj5 == null || obj.getClass() == type)) {
                    serializeWriter.writeFieldName(this.typeKey != null ? this.typeKey : jSONSerializer.config.typeKey, false);
                    String str2 = this.typeName;
                    if (str2 == null) {
                        str2 = obj.getClass().getName();
                    }
                    jSONSerializer.write(str2);
                    obj4 = 1;
                }
                char c3 = obj4 != null ? ',' : '\u0000';
                if (jSONSerializer.beforeFilters != null) {
                    c = c3;
                    for (BeforeFilter writeBefore : jSONSerializer.beforeFilters) {
                        c = writeBefore.writeBefore(jSONSerializer, obj, c);
                    }
                    c3 = c;
                }
                Object obj6 = c3 == ',' ? 1 : null;
                Object obj7 = ((serializeWriter.features & SerializerFeature.QuoteFieldNames.mask) == 0 || (serializeWriter.features & SerializerFeature.UseSingleQuotes.mask) != 0) ? null : 1;
                Object obj8 = (serializeWriter.features & SerializerFeature.UseSingleQuotes.mask) != 0 ? 1 : null;
                Object obj9;
                if ((serializeWriter.features & SerializerFeature.NotWriteDefaultValue.mask) != 0) {
                    obj9 = 1;
                } else {
                    obj9 = null;
                }
                List<PropertyFilter> list = jSONSerializer.propertyFilters;
                List<NameFilter> list2 = jSONSerializer.nameFilters;
                List<ValueFilter> list3 = jSONSerializer.valueFilters;
                List<PropertyPreFilter> list4 = jSONSerializer.propertyPreFilters;
                int i2 = 0;
                while (i2 < fieldSerializerArr.length) {
                    FieldSerializer fieldSerializer = fieldSerializerArr[i2];
                    FieldInfo fieldInfo = fieldSerializer.fieldInfo;
                    Class cls = fieldInfo.fieldClass;
                    String str3 = fieldInfo.name;
                    if ((serializeWriter.features & SerializerFeature.SkipTransientField.mask) != 0 && fieldInfo.field != null && fieldInfo.fieldTransient) {
                        obj4 = obj6;
                    } else if (this.typeKey == null || !this.typeKey.equals(str3)) {
                        if (list4 != null) {
                            for (PropertyPreFilter apply : list4) {
                                if (!apply.apply(jSONSerializer, obj, str3)) {
                                    obj4 = null;
                                    break;
                                }
                            }
                        }
                        i = 1;
                        if (obj4 == null) {
                            obj4 = obj6;
                        } else {
                            boolean z;
                            long j;
                            int i3;
                            Integer propertyValue;
                            Object obj10;
                            Integer valueOf;
                            Object obj11;
                            Number valueOf2;
                            int i4;
                            Object valueOf3;
                            Integer num;
                            if (!fieldInfo.fieldAccess) {
                                z = false;
                                j = 0;
                                i3 = 0;
                                propertyValue = fieldSerializer.getPropertyValue(obj);
                                obj10 = null;
                                i = 1;
                            } else if (cls == Integer.TYPE) {
                                z = false;
                                j = 0;
                                i3 = fieldInfo.field.getInt(obj);
                                propertyValue = null;
                                obj10 = 1;
                                obj4 = null;
                            } else if (cls == Long.TYPE) {
                                z = false;
                                j = fieldInfo.field.getLong(obj);
                                i3 = 0;
                                propertyValue = null;
                                r14 = 1;
                                obj4 = null;
                            } else if (cls == Boolean.TYPE) {
                                z = fieldInfo.field.getBoolean(obj);
                                j = 0;
                                i3 = 0;
                                propertyValue = null;
                                r14 = 1;
                                obj4 = null;
                            } else {
                                z = false;
                                j = 0;
                                i3 = 0;
                                propertyValue = fieldInfo.field.get(obj);
                                obj10 = null;
                                i = 1;
                            }
                            if (list != null) {
                                Object obj12;
                                if (obj10 != null) {
                                    if (cls == Integer.TYPE) {
                                        valueOf = Integer.valueOf(i3);
                                        obj11 = 1;
                                    } else if (cls == Long.TYPE) {
                                        valueOf2 = Long.valueOf(j);
                                        i4 = 1;
                                    } else if (cls == Boolean.TYPE) {
                                        valueOf3 = Boolean.valueOf(z);
                                        i4 = 1;
                                    }
                                    for (PropertyFilter apply2 : list) {
                                        if (!apply2.apply(obj, str3, valueOf)) {
                                            obj5 = obj11;
                                            obj11 = null;
                                            num = valueOf;
                                            break;
                                        }
                                    }
                                    num = valueOf;
                                    obj12 = obj11;
                                    i4 = 1;
                                    obj5 = obj12;
                                }
                                valueOf = propertyValue;
                                obj11 = obj4;
                                while (r12.hasNext()) {
                                    if (apply2.apply(obj, str3, valueOf)) {
                                        obj5 = obj11;
                                        obj11 = null;
                                        num = valueOf;
                                        break;
                                    }
                                }
                                num = valueOf;
                                obj12 = obj11;
                                i4 = 1;
                                obj5 = obj12;
                            } else {
                                obj5 = obj4;
                                num = propertyValue;
                                i4 = 1;
                            }
                            if (obj11 == null) {
                                obj4 = obj6;
                            } else {
                                String str4;
                                if (list2 != null) {
                                    if (obj10 != null && obj5 == null) {
                                        if (cls == Integer.TYPE) {
                                            obj11 = 1;
                                            valueOf = Integer.valueOf(i3);
                                        } else if (cls == Long.TYPE) {
                                            i4 = 1;
                                            valueOf2 = Long.valueOf(j);
                                        } else if (cls == Boolean.TYPE) {
                                            i4 = 1;
                                            valueOf3 = Boolean.valueOf(z);
                                        }
                                        str = str3;
                                        for (NameFilter process : list2) {
                                            str = process.process(obj, str, valueOf);
                                        }
                                        str4 = str;
                                        num = valueOf;
                                        obj5 = obj11;
                                    }
                                    obj11 = obj5;
                                    valueOf = num;
                                    str = str3;
                                    while (r12.hasNext()) {
                                        str = process.process(obj, str, valueOf);
                                    }
                                    str4 = str;
                                    num = valueOf;
                                    obj5 = obj11;
                                } else {
                                    str4 = str3;
                                }
                                Integer num2;
                                if (list3 != null) {
                                    Integer num3;
                                    if (obj10 != null && obj5 == null) {
                                        if (cls == Integer.TYPE) {
                                            num = Integer.valueOf(i3);
                                            obj11 = 1;
                                            valueOf3 = num;
                                            num2 = num;
                                        } else if (cls == Long.TYPE) {
                                            Number valueOf4 = Long.valueOf(j);
                                            i4 = 1;
                                            valueOf2 = valueOf4;
                                            Number number = valueOf4;
                                        } else if (cls == Boolean.TYPE) {
                                            Boolean valueOf5 = Boolean.valueOf(z);
                                            i4 = 1;
                                            Boolean bool = valueOf5;
                                            obj5 = valueOf5;
                                        }
                                        for (ValueFilter process2 : list3) {
                                            valueOf3 = process2.process(obj, str3, valueOf3);
                                        }
                                        num3 = num2;
                                        obj5 = valueOf3;
                                        valueOf3 = obj11;
                                        propertyValue = num3;
                                    }
                                    obj11 = obj5;
                                    valueOf = num;
                                    num2 = num;
                                    while (r32.hasNext()) {
                                        valueOf3 = process2.process(obj, str3, valueOf3);
                                    }
                                    num3 = num2;
                                    obj5 = valueOf3;
                                    valueOf3 = obj11;
                                    propertyValue = num3;
                                } else {
                                    propertyValue = num;
                                    valueOf3 = obj5;
                                    num2 = num;
                                }
                                if (valueOf3 != null && obj5 == null && obj3 == null && !fieldSerializer.writeNull && (serializeWriter.features & SerializerFeature.WriteMapNullValue.mask) == 0) {
                                    obj4 = obj6;
                                } else {
                                    if (!(valueOf3 == null || obj5 == null || r19 == null)) {
                                        if ((cls == Byte.TYPE || cls == Short.TYPE || cls == Integer.TYPE || cls == Long.TYPE || cls == Float.TYPE || cls == Double.TYPE) && (obj5 instanceof Number) && ((Number) obj5).byteValue() == (byte) 0) {
                                            obj4 = obj6;
                                        } else if (cls == Boolean.TYPE && (obj5 instanceof Boolean) && !((Boolean) obj5).booleanValue()) {
                                            obj4 = obj6;
                                        }
                                    }
                                    if (obj6 != null) {
                                        i = serializeWriter.count + 1;
                                        if (i > serializeWriter.buf.length) {
                                            if (serializeWriter.writer == null) {
                                                serializeWriter.expandCapacity(i);
                                            } else {
                                                serializeWriter.flush();
                                                i = 1;
                                            }
                                        }
                                        serializeWriter.buf[serializeWriter.count] = ',';
                                        serializeWriter.count = i;
                                        if ((serializeWriter.features & SerializerFeature.PrettyFormat.mask) != 0) {
                                            jSONSerializer.println();
                                        }
                                    }
                                    if (str4 != str3) {
                                        if (obj3 == null) {
                                            serializeWriter.writeFieldName(str4, true);
                                        }
                                        jSONSerializer.write(obj5);
                                    } else if (propertyValue != obj5) {
                                        if (obj3 == null) {
                                            fieldSerializer.writePrefix(jSONSerializer);
                                        }
                                        jSONSerializer.write(obj5);
                                    } else {
                                        int length;
                                        if (obj3 == null) {
                                            if (obj7 != null) {
                                                Object obj13 = fieldSerializer.name_chars;
                                                i4 = 0;
                                                length = obj13.length;
                                                i = serializeWriter.count + length;
                                                if (i > serializeWriter.buf.length) {
                                                    if (serializeWriter.writer == null) {
                                                        serializeWriter.expandCapacity(i);
                                                    } else {
                                                        while (true) {
                                                            int length2 = serializeWriter.buf.length - serializeWriter.count;
                                                            System.arraycopy(obj13, i4, serializeWriter.buf, serializeWriter.count, length2);
                                                            serializeWriter.count = serializeWriter.buf.length;
                                                            serializeWriter.flush();
                                                            i = length - length2;
                                                            length = i4 + length2;
                                                            if (i <= serializeWriter.buf.length) {
                                                                break;
                                                            }
                                                            i4 = length;
                                                            length = i;
                                                        }
                                                        i4 = length;
                                                        length = i;
                                                    }
                                                }
                                                System.arraycopy(obj13, i4, serializeWriter.buf, serializeWriter.count, length);
                                                serializeWriter.count = i;
                                            } else {
                                                fieldSerializer.writePrefix(jSONSerializer);
                                            }
                                        }
                                        if (obj10 == null || valueOf3 != null) {
                                            if (obj3 != null) {
                                                fieldSerializer.writeValue(jSONSerializer, obj5);
                                            } else if (cls == String.class) {
                                                if (obj5 != null) {
                                                    str = (String) obj5;
                                                    if (obj8 != null) {
                                                        serializeWriter.writeStringWithSingleQuote(str);
                                                    } else {
                                                        serializeWriter.writeStringWithDoubleQuote(str, '\u0000', true);
                                                    }
                                                } else if ((serializeWriter.features & SerializerFeature.WriteNullStringAsEmpty.mask) == 0 && (fieldSerializer.features & SerializerFeature.WriteNullStringAsEmpty.mask) == 0) {
                                                    serializeWriter.writeNull();
                                                } else {
                                                    serializeWriter.writeString("");
                                                }
                                            } else if (!fieldInfo.isEnum) {
                                                fieldSerializer.writeValue(jSONSerializer, obj5);
                                            } else if (obj5 == null) {
                                                serializeWriter.writeNull();
                                            } else if ((serializeWriter.features & SerializerFeature.WriteEnumUsingToString.mask) != 0) {
                                                str = ((Enum) obj5).toString();
                                                if (((serializeWriter.features & SerializerFeature.UseSingleQuotes.mask) != 0 ? 1 : null) != null) {
                                                    serializeWriter.writeStringWithSingleQuote(str);
                                                } else {
                                                    serializeWriter.writeStringWithDoubleQuote(str, '\u0000', false);
                                                }
                                            } else {
                                                serializeWriter.writeInt(((Enum) obj5).ordinal());
                                            }
                                        } else if (cls == Integer.TYPE) {
                                            if (i3 == Integer.MIN_VALUE) {
                                                serializeWriter.write("-2147483648");
                                            } else {
                                                int i5 = 0;
                                                while ((i3 < 0 ? -i3 : i3) > SerializeWriter.sizeTable[i5]) {
                                                    i5++;
                                                }
                                                i = i5 + 1;
                                                if (i3 < 0) {
                                                    i5 = i + 1;
                                                } else {
                                                    i5 = i;
                                                }
                                                obj4 = null;
                                                length = serializeWriter.count + i5;
                                                if (length > serializeWriter.buf.length) {
                                                    if (serializeWriter.writer == null) {
                                                        serializeWriter.expandCapacity(length);
                                                    } else {
                                                        char[] cArr = new char[i5];
                                                        SerializeWriter.getChars((long) i3, i5, cArr);
                                                        serializeWriter.write(cArr, 0, cArr.length);
                                                        obj4 = 1;
                                                    }
                                                }
                                                if (obj4 == null) {
                                                    SerializeWriter.getChars((long) i3, length, serializeWriter.buf);
                                                    serializeWriter.count = length;
                                                }
                                            }
                                        } else if (cls == Long.TYPE) {
                                            jSONSerializer.out.writeLong(j);
                                        } else if (cls == Boolean.TYPE) {
                                            if (z) {
                                                jSONSerializer.out.write(true_chars, 0, true_chars.length);
                                            } else {
                                                jSONSerializer.out.write(false_chars, 0, false_chars.length);
                                            }
                                        }
                                    }
                                    obj4 = 1;
                                }
                            }
                        }
                    } else {
                        obj4 = obj6;
                    }
                    i2++;
                    obj6 = obj4;
                }
                if (jSONSerializer.afterFilters != null) {
                    c3 = obj6 != null ? ',' : '\u0000';
                    c = c3;
                    for (AfterFilter writeAfter : jSONSerializer.afterFilters) {
                        c = writeAfter.writeAfter(jSONSerializer, obj, c);
                    }
                }
                if (fieldSerializerArr.length > 0 && (serializeWriter.features & SerializerFeature.PrettyFormat.mask) != 0) {
                    jSONSerializer.decrementIdent();
                    jSONSerializer.println();
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
                serializeWriter.buf[serializeWriter.count] = c2;
                serializeWriter.count = i;
                jSONSerializer.context = serialContext;
            } catch (Throwable e) {
                str = "write javaBean error, fastjson version 1.1.67";
                if (obj2 != null) {
                    str = str + ", fieldName : " + obj2;
                }
                throw new JSONException(str, e);
            } catch (Throwable th) {
                jSONSerializer.context = serialContext;
            }
        }
    }

    public Map<String, Object> getFieldValuesMap(Object obj) throws Exception {
        Map<String, Object> linkedHashMap = new LinkedHashMap(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
        }
        return linkedHashMap;
    }
}
