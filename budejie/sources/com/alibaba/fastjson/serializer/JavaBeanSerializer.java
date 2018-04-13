package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaBeanSerializer implements ObjectSerializer {
    private static final char[] false_chars = new char[]{'f', 'a', 'l', 's', 'e'};
    private static final char[] true_chars = new char[]{'t', 'r', 'u', 'e'};
    protected final Class<?> beanType;
    protected int features;
    private final FieldSerializer[] getters;
    protected final FieldSerializer[] sortedGetters;

    public JavaBeanSerializer(Class<?> cls) {
        this((Class) cls, (Map) null);
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this((Class) cls, createAliasMap(strArr));
    }

    static Map<String, String> createAliasMap(String... strArr) {
        Map<String, String> hashMap = new HashMap();
        for (Object obj : strArr) {
            hashMap.put(obj, obj);
        }
        return hashMap;
    }

    public JavaBeanSerializer(Class<?> cls, Map<String, String> map) {
        this(cls, map, TypeUtils.getSerializeFeatures(cls));
    }

    public JavaBeanSerializer(Class<?> cls, Map<String, String> map, int i) {
        this.features = 0;
        this.features = i;
        this.beanType = cls;
        JSONType jSONType = (JSONType) cls.getAnnotation(JSONType.class);
        if (jSONType != null) {
            SerializerFeature.of(jSONType.serialzeFeatures());
        }
        List arrayList = new ArrayList();
        for (FieldInfo fieldSerializer : TypeUtils.computeGetters(cls, jSONType, map, false)) {
            arrayList.add(new FieldSerializer(cls, fieldSerializer));
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
        List<FieldInfo> computeGetters = TypeUtils.computeGetters(cls, jSONType, map, true);
        List arrayList2 = new ArrayList();
        for (FieldInfo fieldSerializer2 : computeGetters) {
            arrayList2.add(new FieldSerializer(cls, fieldSerializer2));
        }
        this.sortedGetters = (FieldSerializer[]) arrayList2.toArray(new FieldSerializer[arrayList2.size()]);
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
        } else if (!writeReference(jSONSerializer, obj, i)) {
            FieldSerializer[] fieldSerializerArr;
            char c;
            if (serializeWriter.sortField) {
                fieldSerializerArr = this.sortedGetters;
            } else {
                fieldSerializerArr = this.getters;
            }
            SerialContext serialContext = jSONSerializer.context;
            jSONSerializer.setContext(serialContext, obj, obj2, this.features, i);
            boolean isWriteAsArray = isWriteAsArray(jSONSerializer);
            char c2 = isWriteAsArray ? '[' : '{';
            if (isWriteAsArray) {
                c = ']';
            } else {
                c = '}';
            }
            String str;
            try {
                serializeWriter.append(c2);
                if (fieldSerializerArr.length > 0 && serializeWriter.prettyFormat) {
                    jSONSerializer.incrementIndent();
                    jSONSerializer.println();
                }
                Object obj3 = null;
                if (((this.features & SerializerFeature.WriteClassName.mask) != 0 || jSONSerializer.isWriteClassName(type, obj)) && obj.getClass() != type) {
                    serializeWriter.writeFieldName(JSON.DEFAULT_TYPE_KEY, false);
                    jSONSerializer.write(obj.getClass());
                    obj3 = 1;
                }
                c2 = obj3 != null ? ',' : '\u0000';
                Object obj4 = (!serializeWriter.quoteFieldNames || serializeWriter.useSingleQuotes) ? null : 1;
                Object obj5 = jSONSerializer.writeBefore(obj, c2) == ',' ? 1 : null;
                boolean z = serializeWriter.skipTransientField;
                boolean z2 = serializeWriter.ignoreNonFieldGetter;
                List<LabelFilter> list = jSONSerializer.labelFilters;
                List<PropertyFilter> list2 = jSONSerializer.propertyFilters;
                List<NameFilter> list3 = jSONSerializer.nameFilters;
                List<ValueFilter> list4 = jSONSerializer.valueFilters;
                List<ContextValueFilter> list5 = jSONSerializer.contextValueFilters;
                List<PropertyPreFilter> list6 = jSONSerializer.propertyPreFilters;
                int i2 = 0;
                while (i2 < fieldSerializerArr.length) {
                    FieldSerializer fieldSerializer = fieldSerializerArr[i2];
                    Field field = fieldSerializer.fieldInfo.field;
                    FieldInfo fieldInfo = fieldSerializer.fieldInfo;
                    String str2 = fieldInfo.name;
                    Class cls = fieldInfo.fieldClass;
                    if (z && field != null && fieldInfo.fieldTransient) {
                        obj3 = obj5;
                    } else if (z2 && field == null) {
                        obj3 = obj5;
                    } else {
                        if (list6 != null) {
                            for (PropertyPreFilter apply : list6) {
                                if (!apply.apply(jSONSerializer, obj, fieldInfo.name)) {
                                    obj3 = null;
                                    break;
                                }
                            }
                        }
                        int i3 = 1;
                        if (obj3 == null) {
                            obj3 = obj5;
                        } else {
                            if (list != null) {
                                for (LabelFilter apply2 : list) {
                                    if (!apply2.apply(fieldInfo.label)) {
                                        obj3 = null;
                                        break;
                                    }
                                }
                            }
                            i3 = 1;
                            if (obj3 == null) {
                                obj3 = obj5;
                            } else {
                                Object obj6;
                                long j;
                                boolean z3;
                                int i4;
                                Integer propertyValue;
                                Integer valueOf;
                                Object obj7;
                                Number valueOf2;
                                int i5;
                                Object valueOf3;
                                Object obj8;
                                Integer num;
                                if (!fieldInfo.fieldAccess) {
                                    obj6 = null;
                                    j = 0;
                                    z3 = false;
                                    i3 = 1;
                                    i4 = 0;
                                    propertyValue = fieldSerializer.getPropertyValue(obj);
                                } else if (cls == Integer.TYPE) {
                                    obj6 = 1;
                                    j = 0;
                                    z3 = false;
                                    obj3 = null;
                                    i4 = fieldInfo.field.getInt(obj);
                                    propertyValue = null;
                                } else if (cls == Long.TYPE) {
                                    r13 = 1;
                                    j = fieldInfo.field.getLong(obj);
                                    z3 = false;
                                    obj3 = null;
                                    i4 = 0;
                                    propertyValue = null;
                                } else if (cls == Boolean.TYPE) {
                                    r13 = 1;
                                    j = 0;
                                    z3 = fieldInfo.field.getBoolean(obj);
                                    obj3 = null;
                                    i4 = 0;
                                    propertyValue = null;
                                } else {
                                    obj6 = null;
                                    j = 0;
                                    z3 = false;
                                    i3 = 1;
                                    i4 = 0;
                                    propertyValue = fieldInfo.field.get(obj);
                                }
                                if (list2 != null) {
                                    Object obj9;
                                    if (obj6 != null) {
                                        if (cls == Integer.TYPE) {
                                            valueOf = Integer.valueOf(i4);
                                            obj7 = 1;
                                        } else if (cls == Long.TYPE) {
                                            valueOf2 = Long.valueOf(j);
                                            i5 = 1;
                                        } else if (cls == Boolean.TYPE) {
                                            valueOf3 = Boolean.valueOf(z3);
                                            i5 = 1;
                                        }
                                        for (PropertyFilter apply3 : list2) {
                                            if (!apply3.apply(obj, str2, valueOf)) {
                                                obj8 = obj7;
                                                obj7 = null;
                                                num = valueOf;
                                                break;
                                            }
                                        }
                                        num = valueOf;
                                        obj9 = obj7;
                                        i5 = 1;
                                        obj8 = obj9;
                                    }
                                    valueOf = propertyValue;
                                    obj7 = obj3;
                                    while (r11.hasNext()) {
                                        if (apply3.apply(obj, str2, valueOf)) {
                                            obj8 = obj7;
                                            obj7 = null;
                                            num = valueOf;
                                            break;
                                        }
                                    }
                                    num = valueOf;
                                    obj9 = obj7;
                                    i5 = 1;
                                    obj8 = obj9;
                                } else {
                                    obj8 = obj3;
                                    num = propertyValue;
                                    i5 = 1;
                                }
                                if (obj7 == null) {
                                    obj3 = obj5;
                                } else {
                                    String str3;
                                    Integer num2;
                                    Number valueOf4;
                                    Number number;
                                    Boolean valueOf5;
                                    Boolean bool;
                                    if (list3 != null) {
                                        if (obj6 != null && obj8 == null) {
                                            if (cls == Integer.TYPE) {
                                                obj7 = 1;
                                                valueOf = Integer.valueOf(i4);
                                            } else if (cls == Long.TYPE) {
                                                i5 = 1;
                                                valueOf2 = Long.valueOf(j);
                                            } else if (cls == Boolean.TYPE) {
                                                i5 = 1;
                                                valueOf3 = Boolean.valueOf(z3);
                                            }
                                            str = str2;
                                            for (NameFilter process : list3) {
                                                str = process.process(obj, str, valueOf);
                                            }
                                            str3 = str;
                                            num = valueOf;
                                            obj8 = obj7;
                                        }
                                        obj7 = obj8;
                                        valueOf = num;
                                        str = str2;
                                        while (r11.hasNext()) {
                                            str = process.process(obj, str, valueOf);
                                        }
                                        str3 = str;
                                        num = valueOf;
                                        obj8 = obj7;
                                    } else {
                                        str3 = str2;
                                    }
                                    if (serializeWriter.writeNonStringValueAsString) {
                                        if (cls == Integer.TYPE) {
                                            num = Integer.toString(i4);
                                            obj8 = 1;
                                        } else if (cls == Long.TYPE) {
                                            num = Long.toString(j);
                                            obj8 = 1;
                                        } else if (cls == Boolean.TYPE) {
                                            num = Boolean.toString(z3);
                                            obj8 = 1;
                                        } else if (cls != String.class && ((num instanceof Number) || (num instanceof Boolean))) {
                                            num = num.toString();
                                        }
                                    }
                                    if (list4 != null) {
                                        if (obj6 != null && obj8 == null) {
                                            if (cls == Integer.TYPE) {
                                                num = Integer.valueOf(i4);
                                                obj7 = 1;
                                                valueOf3 = num;
                                                num2 = num;
                                            } else if (cls == Long.TYPE) {
                                                valueOf4 = Long.valueOf(j);
                                                i5 = 1;
                                                valueOf2 = valueOf4;
                                                number = valueOf4;
                                            } else if (cls == Boolean.TYPE) {
                                                valueOf5 = Boolean.valueOf(z3);
                                                i5 = 1;
                                                bool = valueOf5;
                                                obj8 = valueOf5;
                                            }
                                            for (ValueFilter process2 : list4) {
                                                valueOf3 = process2.process(obj, str2, valueOf3);
                                            }
                                            num = num2;
                                            obj8 = obj7;
                                            obj7 = valueOf3;
                                        }
                                        obj7 = obj8;
                                        valueOf = num;
                                        num2 = num;
                                        while (r34.hasNext()) {
                                            valueOf3 = process2.process(obj, str2, valueOf3);
                                        }
                                        num = num2;
                                        obj8 = obj7;
                                        obj7 = valueOf3;
                                    } else {
                                        propertyValue = num;
                                    }
                                    if (list5 != null) {
                                        Integer num3;
                                        if (obj6 != null && obj8 == null) {
                                            if (cls == Integer.TYPE) {
                                                num = Integer.valueOf(i4);
                                                obj7 = 1;
                                                valueOf3 = num;
                                                num2 = num;
                                            } else if (cls == Long.TYPE) {
                                                valueOf4 = Long.valueOf(j);
                                                i5 = 1;
                                                valueOf2 = valueOf4;
                                                number = valueOf4;
                                            } else if (cls == Boolean.TYPE) {
                                                valueOf5 = Boolean.valueOf(z3);
                                                i5 = 1;
                                                bool = valueOf5;
                                                obj8 = valueOf5;
                                            }
                                            for (ContextValueFilter process3 : list5) {
                                                valueOf3 = process3.process(fieldSerializer.fieldContext, obj, str2, valueOf3);
                                            }
                                            num3 = num2;
                                            obj8 = valueOf3;
                                            valueOf3 = obj7;
                                            propertyValue = num3;
                                        }
                                        valueOf3 = obj7;
                                        obj7 = obj8;
                                        num2 = num;
                                        while (r34.hasNext()) {
                                            valueOf3 = process3.process(fieldSerializer.fieldContext, obj, str2, valueOf3);
                                        }
                                        num3 = num2;
                                        obj8 = valueOf3;
                                        valueOf3 = obj7;
                                        propertyValue = num3;
                                    } else {
                                        valueOf3 = obj8;
                                        obj8 = obj7;
                                        propertyValue = num;
                                    }
                                    if (valueOf3 == null || obj8 != null || isWriteAsArray || fieldSerializer.writeNull || serializeWriter.writeMapNullValue) {
                                        if (obj8 != null && serializeWriter.notWriteDefaultValue) {
                                            Class cls2 = fieldInfo.fieldClass;
                                            if (cls2 == Byte.TYPE && (obj8 instanceof Byte) && ((Byte) obj8).byteValue() == (byte) 0) {
                                                obj3 = obj5;
                                            } else if (cls2 == Short.TYPE && (obj8 instanceof Short) && ((Short) obj8).shortValue() == (short) 0) {
                                                obj3 = obj5;
                                            } else if (cls2 == Integer.TYPE && (obj8 instanceof Integer) && ((Integer) obj8).intValue() == 0) {
                                                obj3 = obj5;
                                            } else if (cls2 == Long.TYPE && (obj8 instanceof Long) && ((Long) obj8).longValue() == 0) {
                                                obj3 = obj5;
                                            } else if (cls2 == Float.TYPE && (obj8 instanceof Float) && ((Float) obj8).floatValue() == 0.0f) {
                                                obj3 = obj5;
                                            } else if (cls2 == Double.TYPE && (obj8 instanceof Double) && ((Double) obj8).doubleValue() == 0.0d) {
                                                obj3 = obj5;
                                            } else if (cls2 == Boolean.TYPE && (obj8 instanceof Boolean) && !((Boolean) obj8).booleanValue()) {
                                                obj3 = obj5;
                                            }
                                        }
                                        if (obj5 != null) {
                                            serializeWriter.write(44);
                                            if (serializeWriter.prettyFormat) {
                                                jSONSerializer.println();
                                            }
                                        }
                                        if (str3 != str2) {
                                            if (!isWriteAsArray) {
                                                serializeWriter.writeFieldName(str3, true);
                                            }
                                            jSONSerializer.write(obj8);
                                        } else if (propertyValue != obj8) {
                                            if (!isWriteAsArray) {
                                                fieldSerializer.writePrefix(jSONSerializer);
                                            }
                                            jSONSerializer.write(obj8);
                                        } else {
                                            if (!isWriteAsArray) {
                                                if (obj4 != null) {
                                                    serializeWriter.write(fieldInfo.name_chars, 0, fieldInfo.name_chars.length);
                                                } else {
                                                    fieldSerializer.writePrefix(jSONSerializer);
                                                }
                                            }
                                            if (obj6 == null || valueOf3 != null) {
                                                if (isWriteAsArray) {
                                                    fieldSerializer.writeValue(jSONSerializer, obj8);
                                                } else if (cls != String.class) {
                                                    fieldSerializer.writeValue(jSONSerializer, obj8);
                                                } else if (obj8 != null) {
                                                    str = (String) obj8;
                                                    if (serializeWriter.useSingleQuotes) {
                                                        serializeWriter.writeStringWithSingleQuote(str);
                                                    } else {
                                                        serializeWriter.writeStringWithDoubleQuote(str, '\u0000');
                                                    }
                                                } else if ((serializeWriter.features & SerializerFeature.WriteNullStringAsEmpty.mask) == 0 && (fieldSerializer.features & SerializerFeature.WriteNullStringAsEmpty.mask) == 0) {
                                                    serializeWriter.writeNull();
                                                } else {
                                                    serializeWriter.writeString("");
                                                }
                                            } else if (cls == Integer.TYPE) {
                                                jSONSerializer.out.writeInt(i4);
                                            } else if (cls == Long.TYPE) {
                                                jSONSerializer.out.writeLong(j);
                                            } else if (cls == Boolean.TYPE) {
                                                if (z3) {
                                                    jSONSerializer.out.write(true_chars, 0, true_chars.length);
                                                } else {
                                                    jSONSerializer.out.write(false_chars, 0, false_chars.length);
                                                }
                                            }
                                        }
                                        obj3 = 1;
                                    } else {
                                        obj3 = obj5;
                                    }
                                }
                            }
                        }
                    }
                    i2++;
                    obj5 = obj3;
                }
                jSONSerializer.writeAfter(obj, obj5 != null ? ',' : '\u0000');
                if (fieldSerializerArr.length > 0 && serializeWriter.prettyFormat) {
                    jSONSerializer.decrementIdent();
                    jSONSerializer.println();
                }
                serializeWriter.append(c);
                jSONSerializer.context = serialContext;
            } catch (Throwable e) {
                str = "write javaBean error";
                if (obj != null) {
                    str = str + ", class " + obj.getClass().getName();
                }
                if (obj2 != null) {
                    str = str + ", fieldName : " + obj2;
                }
                if (e.getMessage() != null) {
                    str = str + ", " + e.getMessage();
                }
                throw new JSONException(str, e);
            } catch (Throwable th) {
                jSONSerializer.context = serialContext;
            }
        }
    }

    public boolean writeReference(JSONSerializer jSONSerializer, Object obj, int i) {
        SerialContext serialContext = jSONSerializer.context;
        int i2 = SerializerFeature.DisableCircularReferenceDetect.mask;
        if ((serialContext != null && ((serialContext.features & i2) != 0 || (i & i2) != 0)) || jSONSerializer.references == null || !jSONSerializer.references.containsKey(obj)) {
            return false;
        }
        jSONSerializer.writeReference(obj);
        return true;
    }

    public boolean isWriteAsArray(JSONSerializer jSONSerializer) {
        return (this.features & SerializerFeature.BeanToArray.mask) != 0 || jSONSerializer.out.beanToArray;
    }

    public FieldSerializer getFieldSerializer(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        int length = this.sortedGetters.length - 1;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int compareTo = this.sortedGetters[i2].fieldInfo.name.compareTo(str);
            if (compareTo < 0) {
                i = i2 + 1;
            } else if (compareTo <= 0) {
                return this.sortedGetters[i2];
            } else {
                length = i2 - 1;
            }
        }
        return null;
    }

    public List<Object> getFieldValues(Object obj) throws Exception {
        List<Object> arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer propertyValue : this.sortedGetters) {
            arrayList.add(propertyValue.getPropertyValue(obj));
        }
        return arrayList;
    }
}
