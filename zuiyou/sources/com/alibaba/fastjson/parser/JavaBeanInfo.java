package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

class JavaBeanInfo {
    final Constructor<?> creatorConstructor;
    public final String[] creatorConstructorParameters;
    final Constructor<?> defaultConstructor;
    final int defaultConstructorParameterSize;
    final Method factoryMethod;
    final FieldInfo[] fields;
    final JSONType jsonType;
    boolean ordered = false;
    public final int parserFeatures;
    final FieldInfo[] sortedFields;
    final boolean supportBeanToArray;
    public final String typeKey;
    public final String typeName;

    JavaBeanInfo(Class<?> cls, Constructor<?> constructor, Constructor<?> constructor2, Method method, FieldInfo[] fieldInfoArr, FieldInfo[] fieldInfoArr2, JSONType jSONType, String[] strArr) {
        int i;
        int i2;
        boolean z;
        int i3 = 0;
        this.defaultConstructor = constructor;
        this.creatorConstructor = constructor2;
        this.factoryMethod = method;
        this.fields = fieldInfoArr;
        this.jsonType = jSONType;
        if (strArr == null || strArr.length != fieldInfoArr.length) {
            this.creatorConstructorParameters = strArr;
        } else {
            this.creatorConstructorParameters = null;
        }
        if (jSONType != null) {
            String typeName = jSONType.typeName();
            if (typeName.length() <= 0) {
                typeName = cls.getName();
            }
            this.typeName = typeName;
            typeName = jSONType.typeKey();
            if (typeName.length() <= 0) {
                typeName = null;
            }
            this.typeKey = typeName;
            Feature[] parseFeatures = jSONType.parseFeatures();
            i = 0;
            i2 = 0;
            while (i < parseFeatures.length) {
                int i4 = parseFeatures[i].mask | i2;
                i++;
                i2 = i4;
            }
        } else {
            this.typeName = cls.getName();
            this.typeKey = null;
            i2 = 0;
        }
        this.parserFeatures = i2;
        if (jSONType != null) {
            z = false;
            for (Feature feature : jSONType.parseFeatures()) {
                if (feature == Feature.SupportArrayToBean) {
                    z = true;
                }
            }
        } else {
            z = false;
        }
        this.supportBeanToArray = z;
        FieldInfo[] computeSortedFields = computeSortedFields(fieldInfoArr, fieldInfoArr2);
        if (!Arrays.equals(fieldInfoArr, computeSortedFields)) {
            fieldInfoArr = computeSortedFields;
        }
        this.sortedFields = fieldInfoArr;
        if (constructor != null) {
            i3 = constructor.getParameterTypes().length;
        } else if (method != null) {
            i3 = method.getParameterTypes().length;
        }
        this.defaultConstructorParameterSize = i3;
    }

    private FieldInfo[] computeSortedFields(FieldInfo[] fieldInfoArr, FieldInfo[] fieldInfoArr2) {
        if (this.jsonType == null) {
            return fieldInfoArr2;
        }
        String[] orders = this.jsonType.orders();
        if (orders == null || orders.length == 0) {
            return fieldInfoArr2;
        }
        int i;
        boolean z;
        for (Object equals : orders) {
            int i2;
            boolean z2;
            for (FieldInfo fieldInfo : fieldInfoArr2) {
                if (fieldInfo.name.equals(equals)) {
                    z2 = true;
                    break;
                }
            }
            z2 = false;
            if (!z2) {
                z = false;
                break;
            }
        }
        z = true;
        if (!z) {
            return fieldInfoArr2;
        }
        if (orders.length == fieldInfoArr.length) {
            for (i = 0; i < orders.length; i++) {
                if (!fieldInfoArr2[i].name.equals(orders[i])) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                return fieldInfoArr2;
            }
            FieldInfo[] fieldInfoArr3 = new FieldInfo[fieldInfoArr2.length];
            for (i = 0; i < orders.length; i++) {
                for (i2 = 0; i2 < fieldInfoArr2.length; i2++) {
                    if (fieldInfoArr2[i2].name.equals(orders[i])) {
                        fieldInfoArr3[i] = fieldInfoArr2[i2];
                        break;
                    }
                }
            }
            this.ordered = true;
            return fieldInfoArr3;
        }
        FieldInfo[] fieldInfoArr4 = new FieldInfo[fieldInfoArr2.length];
        for (i = 0; i < orders.length; i++) {
            for (i2 = 0; i2 < fieldInfoArr2.length; i2++) {
                if (fieldInfoArr2[i2].name.equals(orders[i])) {
                    fieldInfoArr4[i] = fieldInfoArr2[i2];
                    break;
                }
            }
        }
        i2 = orders.length;
        for (i = 0; i < fieldInfoArr2.length; i++) {
            boolean z3;
            int i3 = 0;
            while (i3 < fieldInfoArr4.length && i3 < i2) {
                if (fieldInfoArr4[i].equals(fieldInfoArr2[i3])) {
                    z3 = true;
                    break;
                }
                i3++;
            }
            z3 = false;
            if (!z3) {
                fieldInfoArr4[i2] = fieldInfoArr2[i];
                i2++;
            }
        }
        this.ordered = true;
        return fieldInfoArr2;
    }

    static boolean addField(List<FieldInfo> list, FieldInfo fieldInfo, boolean z) {
        if (!z) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                FieldInfo fieldInfo2 = (FieldInfo) list.get(i);
                if (fieldInfo2.name.equals(fieldInfo.name) && (!fieldInfo2.getOnly || fieldInfo.getOnly)) {
                    return false;
                }
            }
        }
        list.add(fieldInfo);
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.alibaba.fastjson.parser.JavaBeanInfo build(java.lang.Class<?> r24, int r25, java.lang.reflect.Type r26, boolean r27, boolean r28, boolean r29, boolean r30, com.alibaba.fastjson.PropertyNamingStrategy r31) {
        /*
        r16 = 0;
        r20 = new java.util.ArrayList;
        r20.<init>();
        r21 = new java.util.HashMap;
        r21.<init>();
        r8 = r24.getDeclaredConstructors();
        r9 = com.alibaba.fastjson.util.TypeUtils.isKotlin(r24);
        r3 = 0;
        r0 = r25;
        r4 = r0 & 1024;
        if (r4 != 0) goto L_0x0767;
    L_0x001b:
        r4 = r8.length;
        r5 = 1;
        if (r4 == r5) goto L_0x0021;
    L_0x001f:
        if (r9 != 0) goto L_0x0767;
    L_0x0021:
        r4 = 0;
        r4 = new java.lang.Class[r4];	 Catch:{ Exception -> 0x0091 }
        r0 = r24;
        r4 = r0.getDeclaredConstructor(r4);	 Catch:{ Exception -> 0x0091 }
    L_0x002a:
        if (r4 != 0) goto L_0x0763;
    L_0x002c:
        r3 = r24.isMemberClass();
        if (r3 == 0) goto L_0x0763;
    L_0x0032:
        r3 = r25 & 8;
        if (r3 != 0) goto L_0x0763;
    L_0x0036:
        r6 = r8.length;
        r3 = 0;
        r5 = r3;
    L_0x0039:
        if (r5 >= r6) goto L_0x0763;
    L_0x003b:
        r3 = r8[r5];
        r7 = r3.getParameterTypes();
        r10 = r7.length;
        r11 = 1;
        if (r10 != r11) goto L_0x0094;
    L_0x0045:
        r10 = 0;
        r7 = r7[r10];
        r10 = r24.getDeclaringClass();
        r7 = r7.equals(r10);
        if (r7 == 0) goto L_0x0094;
    L_0x0052:
        r19 = r3;
    L_0x0054:
        r5 = 0;
        r11 = 0;
        r4 = 0;
        if (r27 == 0) goto L_0x0098;
    L_0x0059:
        r3 = 0;
        r14 = r3;
        r15 = r4;
    L_0x005c:
        r22 = r24.getDeclaredFields();
        r3 = r24.isInterface();
        if (r3 != 0) goto L_0x006c;
    L_0x0066:
        r0 = r25;
        r3 = r0 & 1024;
        if (r3 == 0) goto L_0x00f5;
    L_0x006c:
        r3 = 1;
        r6 = r3;
    L_0x006e:
        if (r19 == 0) goto L_0x0072;
    L_0x0070:
        if (r6 == 0) goto L_0x075d;
    L_0x0072:
        r4 = 0;
        r7 = r8.length;
        r3 = 0;
        r5 = r3;
    L_0x0076:
        if (r5 >= r7) goto L_0x00fe;
    L_0x0078:
        r17 = r8[r5];
        r3 = com.alibaba.fastjson.annotation.JSONCreator.class;
        r0 = r17;
        r3 = r0.getAnnotation(r3);
        r3 = (com.alibaba.fastjson.annotation.JSONCreator) r3;
        if (r3 == 0) goto L_0x00f9;
    L_0x0086:
        if (r4 == 0) goto L_0x0100;
    L_0x0088:
        r3 = new com.alibaba.fastjson.JSONException;
        r4 = "multi-json creator";
        r3.<init>(r4);
        throw r3;
    L_0x0091:
        r4 = move-exception;
        r4 = r3;
        goto L_0x002a;
    L_0x0094:
        r3 = r5 + 1;
        r5 = r3;
        goto L_0x0039;
    L_0x0098:
        r10 = new java.util.ArrayList;
        r10.<init>();
        r6 = r24;
    L_0x009f:
        if (r6 == 0) goto L_0x00e8;
    L_0x00a1:
        r3 = java.lang.Object.class;
        if (r6 == r3) goto L_0x00e8;
    L_0x00a5:
        r12 = r6.getDeclaredMethods();
        r13 = r12.length;
        r3 = 0;
        r7 = r3;
    L_0x00ac:
        if (r7 >= r13) goto L_0x00e2;
    L_0x00ae:
        r3 = r12[r7];
        r14 = r3.getModifiers();
        r15 = r14 & 8;
        if (r15 == 0) goto L_0x00cb;
    L_0x00b8:
        r14 = com.alibaba.fastjson.annotation.JSONCreator.class;
        r14 = r3.isAnnotationPresent(r14);
        if (r14 == 0) goto L_0x00e0;
    L_0x00c0:
        if (r4 == 0) goto L_0x00d8;
    L_0x00c2:
        r3 = new com.alibaba.fastjson.JSONException;
        r4 = "multi-json creator";
        r3.<init>(r4);
        throw r3;
    L_0x00cb:
        r15 = r14 & 2;
        if (r15 != 0) goto L_0x00e0;
    L_0x00cf:
        r15 = r14 & 256;
        if (r15 != 0) goto L_0x00e0;
    L_0x00d3:
        r14 = r14 & 4;
        if (r14 == 0) goto L_0x00dd;
    L_0x00d7:
        r3 = r4;
    L_0x00d8:
        r4 = r7 + 1;
        r7 = r4;
        r4 = r3;
        goto L_0x00ac;
    L_0x00dd:
        r10.add(r3);
    L_0x00e0:
        r3 = r4;
        goto L_0x00d8;
    L_0x00e2:
        r3 = r6.getSuperclass();
        r6 = r3;
        goto L_0x009f;
    L_0x00e8:
        r3 = r10.size();
        r3 = new java.lang.reflect.Method[r3];
        r10.toArray(r3);
        r14 = r3;
        r15 = r4;
        goto L_0x005c;
    L_0x00f5:
        r3 = 0;
        r6 = r3;
        goto L_0x006e;
    L_0x00f9:
        r3 = r5 + 1;
        r5 = r3;
        goto L_0x0076;
    L_0x00fe:
        r17 = r4;
    L_0x0100:
        if (r17 == 0) goto L_0x01e2;
    L_0x0102:
        r0 = r24;
        r1 = r17;
        r2 = r25;
        com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r1, r2);
        r12 = r17.getParameterTypes();
        if (r30 == 0) goto L_0x013b;
    L_0x0111:
        r3 = r17.getGenericParameterTypes();
        r11 = r3;
    L_0x0116:
        r16 = r17.getParameterAnnotations();
        r3 = 0;
        r13 = r3;
    L_0x011c:
        r3 = r12.length;
        if (r13 >= r3) goto L_0x017e;
    L_0x011f:
        r6 = r16[r13];
        r4 = 0;
        r7 = r6.length;
        r3 = 0;
        r5 = r3;
    L_0x0125:
        if (r5 >= r7) goto L_0x0130;
    L_0x0127:
        r3 = r6[r5];
        r8 = r3 instanceof com.alibaba.fastjson.annotation.JSONField;
        if (r8 == 0) goto L_0x013d;
    L_0x012d:
        r3 = (com.alibaba.fastjson.annotation.JSONField) r3;
        r4 = r3;
    L_0x0130:
        if (r4 != 0) goto L_0x0141;
    L_0x0132:
        r3 = new com.alibaba.fastjson.JSONException;
        r4 = "illegal json creator";
        r3.<init>(r4);
        throw r3;
    L_0x013b:
        r11 = r12;
        goto L_0x0116;
    L_0x013d:
        r3 = r5 + 1;
        r5 = r3;
        goto L_0x0125;
    L_0x0141:
        r6 = r12[r13];
        r7 = r11[r13];
        r3 = r4.name();
        r0 = r24;
        r1 = r22;
        r2 = r21;
        r8 = com.alibaba.fastjson.util.TypeUtils.getField(r0, r3, r1, r2);
        if (r8 == 0) goto L_0x015c;
    L_0x0155:
        r0 = r24;
        r1 = r25;
        com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r8, r1);
    L_0x015c:
        r9 = r4.ordinal();
        r3 = r4.serialzeFeatures();
        r10 = com.alibaba.fastjson.serializer.SerializerFeature.of(r3);
        r3 = new com.alibaba.fastjson.util.FieldInfo;
        r4 = r4.name();
        r5 = r24;
        r3.<init>(r4, r5, r6, r7, r8, r9, r10);
        r0 = r20;
        r1 = r27;
        addField(r0, r3, r1);
        r3 = r13 + 1;
        r13 = r3;
        goto L_0x011c;
    L_0x017e:
        r3 = r20.size();
        r4 = new com.alibaba.fastjson.util.FieldInfo[r3];
        r0 = r20;
        r0.toArray(r4);
        r3 = r4.length;
        r3 = new com.alibaba.fastjson.util.FieldInfo[r3];
        r5 = 0;
        r6 = 0;
        r7 = r4.length;
        java.lang.System.arraycopy(r4, r5, r3, r6, r7);
        java.util.Arrays.sort(r3);
        if (r28 == 0) goto L_0x01a1;
    L_0x0197:
        r3 = com.alibaba.fastjson.annotation.JSONType.class;
        r0 = r24;
        r3 = r0.getAnnotation(r3);
        r3 = (com.alibaba.fastjson.annotation.JSONType) r3;
    L_0x01a1:
        r3 = r4.length;
        r11 = new java.lang.String[r3];
        r3 = 0;
    L_0x01a5:
        r5 = r4.length;
        if (r3 >= r5) goto L_0x01b1;
    L_0x01a8:
        r5 = r4[r3];
        r5 = r5.name;
        r11[r3] = r5;
        r3 = r3 + 1;
        goto L_0x01a5;
    L_0x01b1:
        r18 = r17;
        r17 = r11;
    L_0x01b5:
        if (r19 == 0) goto L_0x01c0;
    L_0x01b7:
        r0 = r24;
        r1 = r19;
        r2 = r25;
        com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r1, r2);
    L_0x01c0:
        if (r27 != 0) goto L_0x0556;
    L_0x01c2:
        r0 = r14.length;
        r23 = r0;
        r3 = 0;
        r16 = r3;
    L_0x01c8:
        r0 = r16;
        r1 = r23;
        if (r0 >= r1) goto L_0x0556;
    L_0x01ce:
        r5 = r14[r16];
        r9 = 0;
        r10 = 0;
        r4 = r5.getName();
        r3 = r4.length();
        r6 = 4;
        if (r3 >= r6) goto L_0x03c1;
    L_0x01dd:
        r3 = r16 + 1;
        r16 = r3;
        goto L_0x01c8;
    L_0x01e2:
        if (r15 == 0) goto L_0x0298;
    L_0x01e4:
        r0 = r24;
        r1 = r25;
        com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r15, r1);
        r13 = r15.getParameterTypes();
        r3 = r13.length;
        if (r3 <= 0) goto L_0x0292;
    L_0x01f2:
        if (r30 == 0) goto L_0x021e;
    L_0x01f4:
        r3 = r15.getGenericParameterTypes();
        r12 = r3;
    L_0x01f9:
        r17 = r15.getParameterAnnotations();
        r3 = 0;
        r14 = r3;
    L_0x01ff:
        r3 = r13.length;
        if (r14 >= r3) goto L_0x0258;
    L_0x0202:
        r6 = r17[r14];
        r4 = 0;
        r7 = r6.length;
        r3 = 0;
        r5 = r3;
    L_0x0208:
        if (r5 >= r7) goto L_0x0213;
    L_0x020a:
        r3 = r6[r5];
        r8 = r3 instanceof com.alibaba.fastjson.annotation.JSONField;
        if (r8 == 0) goto L_0x0220;
    L_0x0210:
        r3 = (com.alibaba.fastjson.annotation.JSONField) r3;
        r4 = r3;
    L_0x0213:
        if (r4 != 0) goto L_0x0224;
    L_0x0215:
        r3 = new com.alibaba.fastjson.JSONException;
        r4 = "illegal json creator";
        r3.<init>(r4);
        throw r3;
    L_0x021e:
        r12 = r13;
        goto L_0x01f9;
    L_0x0220:
        r3 = r5 + 1;
        r5 = r3;
        goto L_0x0208;
    L_0x0224:
        r6 = r13[r14];
        r7 = r12[r14];
        r3 = r4.name();
        r0 = r24;
        r1 = r22;
        r2 = r21;
        r8 = com.alibaba.fastjson.util.TypeUtils.getField(r0, r3, r1, r2);
        r9 = r4.ordinal();
        r3 = r4.serialzeFeatures();
        r10 = com.alibaba.fastjson.serializer.SerializerFeature.of(r3);
        r3 = new com.alibaba.fastjson.util.FieldInfo;
        r4 = r4.name();
        r5 = r24;
        r3.<init>(r4, r5, r6, r7, r8, r9, r10);
        r0 = r20;
        r1 = r27;
        addField(r0, r3, r1);
        r3 = r14 + 1;
        r14 = r3;
        goto L_0x01ff;
    L_0x0258:
        r3 = r20.size();
        r8 = new com.alibaba.fastjson.util.FieldInfo[r3];
        r0 = r20;
        r0.toArray(r8);
        r3 = r8.length;
        r9 = new com.alibaba.fastjson.util.FieldInfo[r3];
        r3 = 0;
        r4 = 0;
        r5 = r8.length;
        java.lang.System.arraycopy(r8, r3, r9, r4, r5);
        java.util.Arrays.sort(r9);
        r3 = java.util.Arrays.equals(r8, r9);
        if (r3 == 0) goto L_0x0276;
    L_0x0275:
        r9 = r8;
    L_0x0276:
        if (r16 != 0) goto L_0x0759;
    L_0x0278:
        if (r28 == 0) goto L_0x0290;
    L_0x027a:
        r3 = com.alibaba.fastjson.annotation.JSONType.class;
        r0 = r24;
        r3 = r0.getAnnotation(r3);
        r3 = (com.alibaba.fastjson.annotation.JSONType) r3;
    L_0x0284:
        r10 = r3;
    L_0x0285:
        r3 = new com.alibaba.fastjson.parser.JavaBeanInfo;
        r5 = 0;
        r6 = 0;
        r4 = r24;
        r7 = r15;
        r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11);
    L_0x028f:
        return r3;
    L_0x0290:
        r3 = 0;
        goto L_0x0284;
    L_0x0292:
        r18 = r17;
        r17 = r11;
        goto L_0x01b5;
    L_0x0298:
        if (r6 != 0) goto L_0x0753;
    L_0x029a:
        if (r9 == 0) goto L_0x03a5;
    L_0x029c:
        r3 = r8.length;
        if (r3 <= 0) goto L_0x03a5;
    L_0x029f:
        r18 = com.alibaba.fastjson.util.TypeUtils.getKoltinConstructorParameters(r24);
        if (r18 == 0) goto L_0x0383;
    L_0x02a5:
        r5 = r8.length;
        r3 = 0;
        r4 = r3;
        r11 = r17;
    L_0x02aa:
        if (r4 >= r5) goto L_0x02d8;
    L_0x02ac:
        r3 = r8[r4];
        r6 = r3.getParameterTypes();
        r7 = r6.length;
        if (r7 <= 0) goto L_0x02cc;
    L_0x02b5:
        r7 = r6.length;
        r7 = r7 + -1;
        r7 = r6[r7];
        r7 = r7.getName();
        r9 = "kotlin.jvm.internal.DefaultConstructorMarker";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x02cc;
    L_0x02c7:
        r3 = r11;
    L_0x02c8:
        r4 = r4 + 1;
        r11 = r3;
        goto L_0x02aa;
    L_0x02cc:
        if (r11 == 0) goto L_0x02c8;
    L_0x02ce:
        r7 = r11.getParameterTypes();
        r7 = r7.length;
        r6 = r6.length;
        if (r7 < r6) goto L_0x02c8;
    L_0x02d6:
        r3 = r11;
        goto L_0x02c8;
    L_0x02d8:
        r3 = 1;
        r11.setAccessible(r3);
        r0 = r24;
        r1 = r25;
        com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r11, r1);
        r13 = r11.getParameterTypes();
        if (r30 == 0) goto L_0x0352;
    L_0x02e9:
        r3 = r11.getGenericParameterTypes();
        r12 = r3;
    L_0x02ee:
        r17 = r11.getParameterAnnotations();
        r3 = 0;
        r16 = r3;
    L_0x02f5:
        r3 = r13.length;
        r0 = r16;
        if (r0 >= r3) goto L_0x035c;
    L_0x02fa:
        r5 = r18[r16];
        r7 = r17[r16];
        r4 = 0;
        r8 = r7.length;
        r3 = 0;
        r6 = r3;
    L_0x0302:
        if (r6 >= r8) goto L_0x0750;
    L_0x0304:
        r3 = r7[r6];
        r9 = r3 instanceof com.alibaba.fastjson.annotation.JSONField;
        if (r9 == 0) goto L_0x0354;
    L_0x030a:
        r3 = (com.alibaba.fastjson.annotation.JSONField) r3;
    L_0x030c:
        r6 = r13[r16];
        r7 = r12[r16];
        r0 = r24;
        r1 = r22;
        r2 = r21;
        r8 = com.alibaba.fastjson.util.TypeUtils.getField(r0, r5, r1, r2);
        if (r8 == 0) goto L_0x0326;
    L_0x031c:
        if (r3 != 0) goto L_0x0326;
    L_0x031e:
        r3 = com.alibaba.fastjson.annotation.JSONField.class;
        r3 = r8.getAnnotation(r3);
        r3 = (com.alibaba.fastjson.annotation.JSONField) r3;
    L_0x0326:
        if (r3 == 0) goto L_0x0358;
    L_0x0328:
        r9 = r3.ordinal();
        r4 = r3.serialzeFeatures();
        r10 = com.alibaba.fastjson.serializer.SerializerFeature.of(r4);
        r3 = r3.name();
        r4 = r3.length();
        if (r4 == 0) goto L_0x074d;
    L_0x033e:
        r4 = r3;
    L_0x033f:
        r3 = new com.alibaba.fastjson.util.FieldInfo;
        r5 = r24;
        r3.<init>(r4, r5, r6, r7, r8, r9, r10);
        r0 = r20;
        r1 = r27;
        addField(r0, r3, r1);
        r3 = r16 + 1;
        r16 = r3;
        goto L_0x02f5;
    L_0x0352:
        r12 = r13;
        goto L_0x02ee;
    L_0x0354:
        r3 = r6 + 1;
        r6 = r3;
        goto L_0x0302;
    L_0x0358:
        r9 = 0;
        r10 = 0;
        r4 = r5;
        goto L_0x033f;
    L_0x035c:
        r3 = r20.size();
        r5 = new com.alibaba.fastjson.util.FieldInfo[r3];
        r0 = r20;
        r0.toArray(r5);
        r3 = r5.length;
        r3 = new com.alibaba.fastjson.util.FieldInfo[r3];
        r4 = 0;
        r6 = 0;
        r7 = r5.length;
        java.lang.System.arraycopy(r5, r4, r3, r6, r7);
        java.util.Arrays.sort(r3);
        r3 = r5.length;
        r4 = new java.lang.String[r3];
        r3 = 0;
    L_0x0377:
        r6 = r5.length;
        if (r3 >= r6) goto L_0x039f;
    L_0x037a:
        r6 = r5[r3];
        r6 = r6.name;
        r4[r3] = r6;
        r3 = r3 + 1;
        goto L_0x0377;
    L_0x0383:
        r3 = new com.alibaba.fastjson.JSONException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "default constructor not found. ";
        r4 = r4.append(r5);
        r0 = r24;
        r4 = r4.append(r0);
        r4 = r4.toString();
        r3.<init>(r4);
        throw r3;
    L_0x039f:
        r17 = r4;
        r18 = r11;
        goto L_0x01b5;
    L_0x03a5:
        r3 = new com.alibaba.fastjson.JSONException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "default constructor not found. ";
        r4 = r4.append(r5);
        r0 = r24;
        r4 = r4.append(r0);
        r4 = r4.toString();
        r3.<init>(r4);
        throw r3;
    L_0x03c1:
        r3 = r5.getReturnType();
        r6 = java.lang.Void.TYPE;
        if (r3 == r6) goto L_0x03cf;
    L_0x03c9:
        r6 = r5.getDeclaringClass();
        if (r3 != r6) goto L_0x01dd;
    L_0x03cf:
        r3 = r5.getParameterTypes();
        r3 = r3.length;
        r6 = 1;
        if (r3 != r6) goto L_0x01dd;
    L_0x03d7:
        if (r29 == 0) goto L_0x042b;
    L_0x03d9:
        r3 = com.alibaba.fastjson.annotation.JSONField.class;
        r3 = r5.getAnnotation(r3);
        r3 = (com.alibaba.fastjson.annotation.JSONField) r3;
        r11 = r3;
    L_0x03e2:
        if (r11 != 0) goto L_0x03ec;
    L_0x03e4:
        if (r29 == 0) goto L_0x03ec;
    L_0x03e6:
        r0 = r24;
        r11 = com.alibaba.fastjson.util.TypeUtils.getSupperMethodAnnotation(r0, r5);
    L_0x03ec:
        if (r11 == 0) goto L_0x042d;
    L_0x03ee:
        r3 = r11.deserialize();
        if (r3 == 0) goto L_0x01dd;
    L_0x03f4:
        r9 = r11.ordinal();
        r3 = r11.serialzeFeatures();
        r10 = com.alibaba.fastjson.serializer.SerializerFeature.of(r3);
        r3 = r11.name();
        r3 = r3.length();
        if (r3 == 0) goto L_0x042d;
    L_0x040a:
        r4 = r11.name();
        r3 = new com.alibaba.fastjson.util.FieldInfo;
        r6 = 0;
        r12 = 0;
        r7 = r24;
        r8 = r26;
        r13 = r30;
        r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
        r0 = r20;
        r1 = r27;
        addField(r0, r3, r1);
        r0 = r24;
        r1 = r25;
        com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r5, r1);
        goto L_0x01dd;
    L_0x042b:
        r11 = 0;
        goto L_0x03e2;
    L_0x042d:
        r3 = "set";
        r3 = r4.startsWith(r3);
        if (r3 == 0) goto L_0x01dd;
    L_0x0436:
        r3 = 3;
        r3 = r4.charAt(r3);
        r6 = java.lang.Character.isUpperCase(r3);
        if (r6 == 0) goto L_0x04f5;
    L_0x0441:
        r3 = com.alibaba.fastjson.util.TypeUtils.compatibleWithJavaBean;
        if (r3 == 0) goto L_0x04d3;
    L_0x0445:
        r3 = 3;
        r3 = r4.substring(r3);
        r3 = com.alibaba.fastjson.util.TypeUtils.decapitalize(r3);
        r4 = r3;
    L_0x044f:
        r0 = r24;
        r1 = r22;
        r2 = r21;
        r6 = com.alibaba.fastjson.util.TypeUtils.getField(r0, r4, r1, r2);
        if (r6 != 0) goto L_0x0496;
    L_0x045b:
        r3 = r5.getParameterTypes();
        r7 = 0;
        r3 = r3[r7];
        r7 = java.lang.Boolean.TYPE;
        if (r3 != r7) goto L_0x0496;
    L_0x0466:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r6 = "is";
        r3 = r3.append(r6);
        r6 = 0;
        r6 = r4.charAt(r6);
        r6 = java.lang.Character.toUpperCase(r6);
        r3 = r3.append(r6);
        r6 = 1;
        r6 = r4.substring(r6);
        r3 = r3.append(r6);
        r3 = r3.toString();
        r0 = r24;
        r1 = r22;
        r2 = r21;
        r6 = com.alibaba.fastjson.util.TypeUtils.getField(r0, r3, r1, r2);
    L_0x0496:
        if (r6 == 0) goto L_0x0531;
    L_0x0498:
        if (r29 == 0) goto L_0x052b;
    L_0x049a:
        r3 = com.alibaba.fastjson.annotation.JSONField.class;
        r3 = r6.getAnnotation(r3);
        r3 = (com.alibaba.fastjson.annotation.JSONField) r3;
        r12 = r3;
    L_0x04a3:
        if (r12 == 0) goto L_0x0531;
    L_0x04a5:
        r9 = r12.ordinal();
        r3 = r12.serialzeFeatures();
        r10 = com.alibaba.fastjson.serializer.SerializerFeature.of(r3);
        r3 = r12.name();
        r3 = r3.length();
        if (r3 == 0) goto L_0x052e;
    L_0x04bb:
        r4 = r12.name();
        r3 = new com.alibaba.fastjson.util.FieldInfo;
        r7 = r24;
        r8 = r26;
        r13 = r30;
        r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
        r0 = r20;
        r1 = r27;
        addField(r0, r3, r1);
        goto L_0x01dd;
    L_0x04d3:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r6 = 3;
        r6 = r4.charAt(r6);
        r6 = java.lang.Character.toLowerCase(r6);
        r3 = r3.append(r6);
        r6 = 4;
        r4 = r4.substring(r6);
        r3 = r3.append(r4);
        r3 = r3.toString();
        r4 = r3;
        goto L_0x044f;
    L_0x04f5:
        r6 = 95;
        if (r3 != r6) goto L_0x0501;
    L_0x04f9:
        r3 = 4;
        r3 = r4.substring(r3);
        r4 = r3;
        goto L_0x044f;
    L_0x0501:
        r6 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r3 != r6) goto L_0x050d;
    L_0x0505:
        r3 = 3;
        r3 = r4.substring(r3);
        r4 = r3;
        goto L_0x044f;
    L_0x050d:
        r3 = r4.length();
        r6 = 5;
        if (r3 < r6) goto L_0x01dd;
    L_0x0514:
        r3 = 4;
        r3 = r4.charAt(r3);
        r3 = java.lang.Character.isUpperCase(r3);
        if (r3 == 0) goto L_0x01dd;
    L_0x051f:
        r3 = 3;
        r3 = r4.substring(r3);
        r3 = com.alibaba.fastjson.util.TypeUtils.decapitalize(r3);
        r4 = r3;
        goto L_0x044f;
    L_0x052b:
        r12 = 0;
        goto L_0x04a3;
    L_0x052e:
        if (r11 != 0) goto L_0x0531;
    L_0x0530:
        r11 = r12;
    L_0x0531:
        if (r31 == 0) goto L_0x0539;
    L_0x0533:
        r0 = r31;
        r4 = r0.translate(r4);
    L_0x0539:
        r3 = new com.alibaba.fastjson.util.FieldInfo;
        r6 = 0;
        r12 = 0;
        r7 = r24;
        r8 = r26;
        r13 = r30;
        r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
        r0 = r20;
        r1 = r27;
        addField(r0, r3, r1);
        r0 = r24;
        r1 = r25;
        com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r5, r1);
        goto L_0x01dd;
    L_0x0556:
        r6 = new java.util.ArrayList;
        r0 = r22;
        r3 = r0.length;
        r6.<init>(r3);
        r0 = r22;
        r5 = r0.length;
        r3 = 0;
        r4 = r3;
    L_0x0563:
        if (r4 >= r5) goto L_0x059c;
    L_0x0565:
        r7 = r22[r4];
        r3 = r7.getModifiers();
        r8 = r3 & 8;
        if (r8 == 0) goto L_0x0573;
    L_0x056f:
        r3 = r4 + 1;
        r4 = r3;
        goto L_0x0563;
    L_0x0573:
        r3 = r3 & 16;
        if (r3 == 0) goto L_0x058e;
    L_0x0577:
        r3 = r7.getType();
        r8 = java.util.Map.class;
        r8 = r8.isAssignableFrom(r3);
        if (r8 != 0) goto L_0x058b;
    L_0x0583:
        r8 = java.util.Collection.class;
        r3 = r8.isAssignableFrom(r3);
        if (r3 == 0) goto L_0x059a;
    L_0x058b:
        r3 = 1;
    L_0x058c:
        if (r3 == 0) goto L_0x056f;
    L_0x058e:
        r3 = r7.getModifiers();
        r3 = r3 & 1;
        if (r3 == 0) goto L_0x056f;
    L_0x0596:
        r6.add(r7);
        goto L_0x056f;
    L_0x059a:
        r3 = 0;
        goto L_0x058c;
    L_0x059c:
        r3 = r24.getSuperclass();
    L_0x05a0:
        if (r3 == 0) goto L_0x05e7;
    L_0x05a2:
        r4 = java.lang.Object.class;
        if (r3 == r4) goto L_0x05e7;
    L_0x05a6:
        r7 = r3.getDeclaredFields();
        r8 = r7.length;
        r4 = 0;
        r5 = r4;
    L_0x05ad:
        if (r5 >= r8) goto L_0x05e2;
    L_0x05af:
        r9 = r7[r5];
        r10 = r9.getModifiers();
        r4 = r10 & 8;
        if (r4 == 0) goto L_0x05bd;
    L_0x05b9:
        r4 = r5 + 1;
        r5 = r4;
        goto L_0x05ad;
    L_0x05bd:
        r4 = r10 & 16;
        if (r4 == 0) goto L_0x05d8;
    L_0x05c1:
        r4 = r9.getType();
        r11 = java.util.Map.class;
        r11 = r11.isAssignableFrom(r4);
        if (r11 != 0) goto L_0x05d5;
    L_0x05cd:
        r11 = java.util.Collection.class;
        r4 = r11.isAssignableFrom(r4);
        if (r4 == 0) goto L_0x05e0;
    L_0x05d5:
        r4 = 1;
    L_0x05d6:
        if (r4 == 0) goto L_0x05b9;
    L_0x05d8:
        r4 = r10 & 1;
        if (r4 == 0) goto L_0x05b9;
    L_0x05dc:
        r6.add(r9);
        goto L_0x05b9;
    L_0x05e0:
        r4 = 0;
        goto L_0x05d6;
    L_0x05e2:
        r3 = r3.getSuperclass();
        goto L_0x05a0;
    L_0x05e7:
        r16 = r6.iterator();
    L_0x05eb:
        r3 = r16.hasNext();
        if (r3 == 0) goto L_0x066b;
    L_0x05f1:
        r6 = r16.next();
        r6 = (java.lang.reflect.Field) r6;
        r5 = r6.getName();
        r4 = 0;
        r3 = 0;
        r8 = r20.size();
        r7 = r3;
    L_0x0602:
        if (r7 >= r8) goto L_0x061a;
    L_0x0604:
        r0 = r20;
        r3 = r0.get(r7);
        r3 = (com.alibaba.fastjson.util.FieldInfo) r3;
        r3 = r3.name;
        r3 = r3.equals(r5);
        if (r3 == 0) goto L_0x074a;
    L_0x0614:
        r3 = 1;
    L_0x0615:
        r4 = r7 + 1;
        r7 = r4;
        r4 = r3;
        goto L_0x0602;
    L_0x061a:
        if (r4 != 0) goto L_0x05eb;
    L_0x061c:
        r9 = 0;
        r10 = 0;
        if (r29 == 0) goto L_0x0669;
    L_0x0620:
        r3 = com.alibaba.fastjson.annotation.JSONField.class;
        r3 = r6.getAnnotation(r3);
        r3 = (com.alibaba.fastjson.annotation.JSONField) r3;
        r12 = r3;
    L_0x0629:
        if (r12 == 0) goto L_0x0747;
    L_0x062b:
        r9 = r12.ordinal();
        r3 = r12.serialzeFeatures();
        r10 = com.alibaba.fastjson.serializer.SerializerFeature.of(r3);
        r3 = r12.name();
        r3 = r3.length();
        if (r3 == 0) goto L_0x0747;
    L_0x0641:
        r4 = r12.name();
    L_0x0645:
        if (r31 == 0) goto L_0x064d;
    L_0x0647:
        r0 = r31;
        r4 = r0.translate(r4);
    L_0x064d:
        r0 = r24;
        r1 = r25;
        com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r6, r1);
        r3 = new com.alibaba.fastjson.util.FieldInfo;
        r5 = 0;
        r11 = 0;
        r7 = r24;
        r8 = r26;
        r13 = r30;
        r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
        r0 = r20;
        r1 = r27;
        addField(r0, r3, r1);
        goto L_0x05eb;
    L_0x0669:
        r12 = 0;
        goto L_0x0629;
    L_0x066b:
        if (r27 != 0) goto L_0x0711;
    L_0x066d:
        r0 = r14.length;
        r21 = r0;
        r3 = 0;
        r16 = r3;
    L_0x0673:
        r0 = r16;
        r1 = r21;
        if (r0 >= r1) goto L_0x0711;
    L_0x0679:
        r5 = r14[r16];
        r6 = r5.getName();
        r3 = r6.length();
        r4 = 4;
        if (r3 >= r4) goto L_0x068b;
    L_0x0686:
        r3 = r16 + 1;
        r16 = r3;
        goto L_0x0673;
    L_0x068b:
        r3 = "get";
        r3 = r6.startsWith(r3);
        if (r3 == 0) goto L_0x0686;
    L_0x0694:
        r3 = 3;
        r3 = r6.charAt(r3);
        r3 = java.lang.Character.isUpperCase(r3);
        if (r3 == 0) goto L_0x0686;
    L_0x069f:
        r3 = r5.getParameterTypes();
        r3 = r3.length;
        if (r3 != 0) goto L_0x0686;
    L_0x06a6:
        r3 = r5.getReturnType();
        r4 = java.util.Collection.class;
        r4 = r4.isAssignableFrom(r3);
        if (r4 != 0) goto L_0x06ba;
    L_0x06b2:
        r4 = java.util.Map.class;
        r3 = r4.isAssignableFrom(r3);
        if (r3 == 0) goto L_0x0686;
    L_0x06ba:
        if (r29 == 0) goto L_0x06ef;
    L_0x06bc:
        r3 = com.alibaba.fastjson.annotation.JSONField.class;
        r3 = r5.getAnnotation(r3);
        r3 = (com.alibaba.fastjson.annotation.JSONField) r3;
        r11 = r3;
    L_0x06c5:
        if (r11 == 0) goto L_0x06f1;
    L_0x06c7:
        r4 = r11.name();
        r3 = r4.length();
        if (r3 <= 0) goto L_0x06f1;
    L_0x06d1:
        r3 = new com.alibaba.fastjson.util.FieldInfo;
        r6 = 0;
        r9 = 0;
        r10 = 0;
        r12 = 0;
        r7 = r24;
        r8 = r26;
        r13 = r30;
        r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
        r0 = r20;
        r1 = r27;
        addField(r0, r3, r1);
        r0 = r24;
        r1 = r25;
        com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r5, r1);
        goto L_0x0686;
    L_0x06ef:
        r11 = 0;
        goto L_0x06c5;
    L_0x06f1:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = 3;
        r4 = r6.charAt(r4);
        r4 = java.lang.Character.toLowerCase(r4);
        r3 = r3.append(r4);
        r4 = 4;
        r4 = r6.substring(r4);
        r3 = r3.append(r4);
        r4 = r3.toString();
        goto L_0x06d1;
    L_0x0711:
        r3 = r20.size();
        r8 = new com.alibaba.fastjson.util.FieldInfo[r3];
        r0 = r20;
        r0.toArray(r8);
        r3 = r8.length;
        r9 = new com.alibaba.fastjson.util.FieldInfo[r3];
        r3 = 0;
        r4 = 0;
        r5 = r8.length;
        java.lang.System.arraycopy(r8, r3, r9, r4, r5);
        java.util.Arrays.sort(r9);
        if (r28 == 0) goto L_0x0745;
    L_0x072a:
        r3 = com.alibaba.fastjson.annotation.JSONType.class;
        r0 = r24;
        r3 = r0.getAnnotation(r3);
        r3 = (com.alibaba.fastjson.annotation.JSONType) r3;
        r10 = r3;
    L_0x0735:
        r3 = new com.alibaba.fastjson.parser.JavaBeanInfo;
        r4 = r24;
        r5 = r19;
        r6 = r18;
        r7 = r15;
        r11 = r17;
        r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11);
        goto L_0x028f;
    L_0x0745:
        r10 = 0;
        goto L_0x0735;
    L_0x0747:
        r4 = r5;
        goto L_0x0645;
    L_0x074a:
        r3 = r4;
        goto L_0x0615;
    L_0x074d:
        r3 = r5;
        goto L_0x033e;
    L_0x0750:
        r3 = r4;
        goto L_0x030c;
    L_0x0753:
        r18 = r17;
        r17 = r11;
        goto L_0x01b5;
    L_0x0759:
        r10 = r16;
        goto L_0x0285;
    L_0x075d:
        r17 = r11;
        r18 = r5;
        goto L_0x01b5;
    L_0x0763:
        r19 = r4;
        goto L_0x0054;
    L_0x0767:
        r19 = r3;
        goto L_0x0054;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JavaBeanInfo.build(java.lang.Class, int, java.lang.reflect.Type, boolean, boolean, boolean, boolean, com.alibaba.fastjson.PropertyNamingStrategy):com.alibaba.fastjson.parser.JavaBeanInfo");
    }
}
