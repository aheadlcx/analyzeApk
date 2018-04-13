package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

final class ArraySerializer implements ObjectSerializer {
    private final ObjectSerializer compObjectSerializer;
    private final Class<?> componentType;

    ArraySerializer(Class<?> cls, ObjectSerializer objectSerializer) {
        this.componentType = cls;
        this.compObjectSerializer = objectSerializer;
    }

    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        int i = 0;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            if ((serializeWriter.features & SerializerFeature.WriteNullListAsEmpty.mask) != 0) {
                serializeWriter.write("[]");
            } else {
                serializeWriter.writeNull();
            }
        } else if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            serializeWriter.write(91);
            for (r0 = 0; r0 < zArr.length; r0++) {
                if (r0 != 0) {
                    serializeWriter.write(44);
                }
                serializeWriter.write(zArr[r0]);
            }
            serializeWriter.write(93);
        } else if (obj instanceof byte[]) {
            serializeWriter.writeByteArray((byte[]) obj);
        } else if (obj instanceof char[]) {
            serializeWriter.writeString(new String((char[]) obj));
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            r0 = dArr.length - 1;
            if (r0 == -1) {
                serializeWriter.append((CharSequence) "[]");
                return;
            }
            serializeWriter.write(91);
            while (i < r0) {
                double d = dArr[i];
                if (Double.isNaN(d)) {
                    serializeWriter.writeNull();
                } else {
                    serializeWriter.append(Double.toString(d));
                }
                serializeWriter.write(44);
                i++;
            }
            double d2 = dArr[r0];
            if (Double.isNaN(d2)) {
                serializeWriter.writeNull();
            } else {
                serializeWriter.append(Double.toString(d2));
            }
            serializeWriter.write(93);
        } else if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            r0 = fArr.length - 1;
            if (r0 == -1) {
                serializeWriter.append((CharSequence) "[]");
                return;
            }
            serializeWriter.write(91);
            while (i < r0) {
                float f = fArr[i];
                if (Float.isNaN(f)) {
                    serializeWriter.writeNull();
                } else {
                    serializeWriter.append(Float.toString(f));
                }
                serializeWriter.write(44);
                i++;
            }
            float f2 = fArr[r0];
            if (Float.isNaN(f2)) {
                serializeWriter.writeNull();
            } else {
                serializeWriter.append(Float.toString(f2));
            }
            serializeWriter.write(93);
        } else if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            serializeWriter.write(91);
            while (i < iArr.length) {
                if (i != 0) {
                    serializeWriter.write(44);
                }
                serializeWriter.writeInt(iArr[i]);
                i++;
            }
            serializeWriter.write(93);
        } else if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            serializeWriter.write(91);
            while (i < jArr.length) {
                if (i != 0) {
                    serializeWriter.write(44);
                }
                serializeWriter.writeLong(jArr[i]);
                i++;
            }
            serializeWriter.write(93);
        } else if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            serializeWriter.write(91);
            while (i < sArr.length) {
                if (i != 0) {
                    serializeWriter.write(44);
                }
                serializeWriter.writeInt(sArr[i]);
                i++;
            }
            serializeWriter.write(93);
        } else {
            Object[] objArr = (Object[]) obj;
            int length = objArr.length;
            SerialContext serialContext = jSONSerializer.context;
            jSONSerializer.setContext(serialContext, obj, obj2, 0);
            try {
                serializeWriter.write(91);
                while (i < length) {
                    if (i != 0) {
                        serializeWriter.write(44);
                    }
                    Object obj3 = objArr[i];
                    if (obj3 == null) {
                        if (serializeWriter.isEnabled(SerializerFeature.WriteNullStringAsEmpty) && (obj instanceof String[])) {
                            serializeWriter.writeString("");
                        } else {
                            serializeWriter.append((CharSequence) "null");
                        }
                    } else if (obj3.getClass() == this.componentType) {
                        this.compObjectSerializer.write(jSONSerializer, obj3, Integer.valueOf(i), null);
                    } else {
                        jSONSerializer.config.get(obj3.getClass()).write(jSONSerializer, obj3, Integer.valueOf(i), null);
                    }
                    i++;
                }
                serializeWriter.write(93);
            } finally {
                jSONSerializer.context = serialContext;
            }
        }
    }
}
