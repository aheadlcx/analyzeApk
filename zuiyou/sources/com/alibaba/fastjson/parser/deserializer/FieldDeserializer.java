package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public abstract class FieldDeserializer {
    public final Class<?> clazz;
    protected long[] enumNameHashCodes;
    protected Enum[] enums;
    public final FieldInfo fieldInfo;

    public abstract void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map);

    public FieldDeserializer(Class<?> cls, FieldInfo fieldInfo, int i) {
        this.clazz = cls;
        this.fieldInfo = fieldInfo;
        if (fieldInfo != null) {
            Class cls2 = fieldInfo.fieldClass;
            if (cls2.isEnum()) {
                int i2;
                int i3;
                Enum[] enumArr = (Enum[]) cls2.getEnumConstants();
                long[] jArr = new long[enumArr.length];
                this.enumNameHashCodes = new long[enumArr.length];
                for (i2 = 0; i2 < enumArr.length; i2++) {
                    String name = enumArr[i2].name();
                    long j = -3750763034362895579L;
                    for (i3 = 0; i3 < name.length(); i3++) {
                        j = (j ^ ((long) name.charAt(i3))) * 1099511628211L;
                    }
                    jArr[i2] = j;
                    this.enumNameHashCodes[i2] = j;
                }
                Arrays.sort(this.enumNameHashCodes);
                this.enums = new Enum[enumArr.length];
                for (i2 = 0; i2 < this.enumNameHashCodes.length; i2++) {
                    for (i3 = 0; i3 < jArr.length; i3++) {
                        if (this.enumNameHashCodes[i2] == jArr[i3]) {
                            this.enums[i2] = enumArr[i3];
                            break;
                        }
                    }
                }
            }
        }
    }

    public Enum getEnumByHashCode(long j) {
        if (this.enums == null) {
            return null;
        }
        int binarySearch = Arrays.binarySearch(this.enumNameHashCodes, j);
        if (binarySearch >= 0) {
            return this.enums[binarySearch];
        }
        return null;
    }

    public void setValue(Object obj, int i) throws IllegalAccessException {
        this.fieldInfo.field.setInt(obj, i);
    }

    public void setValue(Object obj, long j) throws IllegalAccessException {
        this.fieldInfo.field.setLong(obj, j);
    }

    public void setValue(Object obj, float f) throws IllegalAccessException {
        this.fieldInfo.field.setFloat(obj, f);
    }

    public void setValue(Object obj, double d) throws IllegalAccessException {
        this.fieldInfo.field.setDouble(obj, d);
    }

    public void setValue(Object obj, Object obj2) {
        if (obj2 != null || !this.fieldInfo.fieldClass.isPrimitive()) {
            Field field = this.fieldInfo.field;
            Method method = this.fieldInfo.method;
            try {
                Map map;
                Collection collection;
                if (this.fieldInfo.fieldAccess) {
                    if (!this.fieldInfo.getOnly) {
                        field.set(obj, obj2);
                    } else if (Map.class.isAssignableFrom(this.fieldInfo.fieldClass)) {
                        map = (Map) field.get(obj);
                        if (map != null) {
                            map.putAll((Map) obj2);
                        }
                    } else {
                        collection = (Collection) field.get(obj);
                        if (collection != null) {
                            collection.addAll((Collection) obj2);
                        }
                    }
                } else if (!this.fieldInfo.getOnly) {
                    method.invoke(obj, new Object[]{obj2});
                } else if (Map.class.isAssignableFrom(this.fieldInfo.fieldClass)) {
                    map = (Map) method.invoke(obj, new Object[0]);
                    if (map != null) {
                        map.putAll((Map) obj2);
                    }
                } else {
                    collection = (Collection) method.invoke(obj, new Object[0]);
                    if (collection != null) {
                        collection.addAll((Collection) obj2);
                    }
                }
            } catch (Throwable e) {
                throw new JSONException("set property error, " + this.fieldInfo.name, e);
            }
        }
    }
}
