package com.alibaba.fastjson.serializer;

public final class ListSerializer implements ObjectSerializer {
    public static final ListSerializer instance = new ListSerializer();

    public final void write(com.alibaba.fastjson.serializer.JSONSerializer r22, java.lang.Object r23, java.lang.Object r24, java.lang.reflect.Type r25, int r26) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r21 = this;
        r0 = r22;
        r4 = r0.out;
        r0 = r4.writeClassName;
        r18 = r0;
        r0 = r22;
        r0 = r0.out;
        r19 = r0;
        r10 = 0;
        if (r18 == 0) goto L_0x0020;
    L_0x0011:
        r0 = r25;
        r4 = r0 instanceof java.lang.reflect.ParameterizedType;
        if (r4 == 0) goto L_0x0020;
    L_0x0017:
        r25 = (java.lang.reflect.ParameterizedType) r25;
        r4 = r25.getActualTypeArguments();
        r5 = 0;
        r10 = r4[r5];
    L_0x0020:
        if (r23 != 0) goto L_0x0038;
    L_0x0022:
        r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty;
        r0 = r19;
        r4 = r0.isEnabled(r4);
        if (r4 == 0) goto L_0x0034;
    L_0x002c:
        r4 = "[]";
        r0 = r19;
        r0.write(r4);
    L_0x0033:
        return;
    L_0x0034:
        r19.writeNull();
        goto L_0x0033;
    L_0x0038:
        r4 = r23;
        r4 = (java.util.List) r4;
        r5 = r4.size();
        if (r5 != 0) goto L_0x004a;
    L_0x0042:
        r4 = "[]";
        r0 = r19;
        r0.append(r4);
        goto L_0x0033;
    L_0x004a:
        r0 = r22;
        r5 = r0.context;
        r6 = 0;
        r0 = r22;
        r1 = r23;
        r2 = r24;
        r0.setContext(r5, r1, r2, r6);
        r0 = r19;	 Catch:{ all -> 0x00bd }
        r6 = r0.prettyFormat;	 Catch:{ all -> 0x00bd }
        if (r6 == 0) goto L_0x00de;	 Catch:{ all -> 0x00bd }
    L_0x005e:
        r6 = 91;	 Catch:{ all -> 0x00bd }
        r0 = r19;	 Catch:{ all -> 0x00bd }
        r0.append(r6);	 Catch:{ all -> 0x00bd }
        r22.incrementIndent();	 Catch:{ all -> 0x00bd }
        r6 = 0;	 Catch:{ all -> 0x00bd }
        r13 = r4.iterator();	 Catch:{ all -> 0x00bd }
        r12 = r6;	 Catch:{ all -> 0x00bd }
    L_0x006e:
        r4 = r13.hasNext();	 Catch:{ all -> 0x00bd }
        if (r4 == 0) goto L_0x00cb;	 Catch:{ all -> 0x00bd }
    L_0x0074:
        r14 = r13.next();	 Catch:{ all -> 0x00bd }
        if (r12 == 0) goto L_0x0081;	 Catch:{ all -> 0x00bd }
    L_0x007a:
        r4 = 44;	 Catch:{ all -> 0x00bd }
        r0 = r19;	 Catch:{ all -> 0x00bd }
        r0.append(r4);	 Catch:{ all -> 0x00bd }
    L_0x0081:
        r22.println();	 Catch:{ all -> 0x00bd }
        if (r14 == 0) goto L_0x00c3;	 Catch:{ all -> 0x00bd }
    L_0x0086:
        r0 = r22;	 Catch:{ all -> 0x00bd }
        r4 = r0.containsReference(r14);	 Catch:{ all -> 0x00bd }
        if (r4 == 0) goto L_0x0097;	 Catch:{ all -> 0x00bd }
    L_0x008e:
        r0 = r22;	 Catch:{ all -> 0x00bd }
        r0.writeReference(r14);	 Catch:{ all -> 0x00bd }
    L_0x0093:
        r4 = r12 + 1;	 Catch:{ all -> 0x00bd }
        r12 = r4;	 Catch:{ all -> 0x00bd }
        goto L_0x006e;	 Catch:{ all -> 0x00bd }
    L_0x0097:
        r4 = r14.getClass();	 Catch:{ all -> 0x00bd }
        r0 = r22;	 Catch:{ all -> 0x00bd }
        r15 = r0.getObjectWriter(r4);	 Catch:{ all -> 0x00bd }
        r4 = new com.alibaba.fastjson.serializer.SerialContext;	 Catch:{ all -> 0x00bd }
        r8 = 0;	 Catch:{ all -> 0x00bd }
        r9 = 0;	 Catch:{ all -> 0x00bd }
        r6 = r23;	 Catch:{ all -> 0x00bd }
        r7 = r24;	 Catch:{ all -> 0x00bd }
        r4.<init>(r5, r6, r7, r8, r9);	 Catch:{ all -> 0x00bd }
        r0 = r22;	 Catch:{ all -> 0x00bd }
        r0.context = r4;	 Catch:{ all -> 0x00bd }
        r9 = java.lang.Integer.valueOf(r12);	 Catch:{ all -> 0x00bd }
        r11 = 0;	 Catch:{ all -> 0x00bd }
        r6 = r15;	 Catch:{ all -> 0x00bd }
        r7 = r22;	 Catch:{ all -> 0x00bd }
        r8 = r14;	 Catch:{ all -> 0x00bd }
        r6.write(r7, r8, r9, r10, r11);	 Catch:{ all -> 0x00bd }
        goto L_0x0093;
    L_0x00bd:
        r4 = move-exception;
        r0 = r22;
        r0.context = r5;
        throw r4;
    L_0x00c3:
        r0 = r22;	 Catch:{ all -> 0x00bd }
        r4 = r0.out;	 Catch:{ all -> 0x00bd }
        r4.writeNull();	 Catch:{ all -> 0x00bd }
        goto L_0x0093;	 Catch:{ all -> 0x00bd }
    L_0x00cb:
        r22.decrementIdent();	 Catch:{ all -> 0x00bd }
        r22.println();	 Catch:{ all -> 0x00bd }
        r4 = 93;	 Catch:{ all -> 0x00bd }
        r0 = r19;	 Catch:{ all -> 0x00bd }
        r0.append(r4);	 Catch:{ all -> 0x00bd }
        r0 = r22;
        r0.context = r5;
        goto L_0x0033;
    L_0x00de:
        r6 = 91;
        r0 = r19;	 Catch:{ all -> 0x00bd }
        r0.append(r6);	 Catch:{ all -> 0x00bd }
        r6 = 0;	 Catch:{ all -> 0x00bd }
        r20 = r4.size();	 Catch:{ all -> 0x00bd }
        r17 = r6;	 Catch:{ all -> 0x00bd }
    L_0x00ec:
        r0 = r17;	 Catch:{ all -> 0x00bd }
        r1 = r20;	 Catch:{ all -> 0x00bd }
        if (r0 >= r1) goto L_0x0177;	 Catch:{ all -> 0x00bd }
    L_0x00f2:
        r0 = r17;	 Catch:{ all -> 0x00bd }
        r8 = r4.get(r0);	 Catch:{ all -> 0x00bd }
        if (r17 == 0) goto L_0x0101;	 Catch:{ all -> 0x00bd }
    L_0x00fa:
        r6 = 44;	 Catch:{ all -> 0x00bd }
        r0 = r19;	 Catch:{ all -> 0x00bd }
        r0.append(r6);	 Catch:{ all -> 0x00bd }
    L_0x0101:
        if (r8 != 0) goto L_0x010f;	 Catch:{ all -> 0x00bd }
    L_0x0103:
        r6 = "null";	 Catch:{ all -> 0x00bd }
        r0 = r19;	 Catch:{ all -> 0x00bd }
        r0.append(r6);	 Catch:{ all -> 0x00bd }
    L_0x010a:
        r6 = r17 + 1;	 Catch:{ all -> 0x00bd }
        r17 = r6;	 Catch:{ all -> 0x00bd }
        goto L_0x00ec;	 Catch:{ all -> 0x00bd }
    L_0x010f:
        r6 = r8.getClass();	 Catch:{ all -> 0x00bd }
        r7 = java.lang.Integer.class;	 Catch:{ all -> 0x00bd }
        if (r6 != r7) goto L_0x0123;	 Catch:{ all -> 0x00bd }
    L_0x0117:
        r8 = (java.lang.Integer) r8;	 Catch:{ all -> 0x00bd }
        r6 = r8.intValue();	 Catch:{ all -> 0x00bd }
        r0 = r19;	 Catch:{ all -> 0x00bd }
        r0.writeInt(r6);	 Catch:{ all -> 0x00bd }
        goto L_0x010a;	 Catch:{ all -> 0x00bd }
    L_0x0123:
        r7 = java.lang.Long.class;	 Catch:{ all -> 0x00bd }
        if (r6 != r7) goto L_0x013d;	 Catch:{ all -> 0x00bd }
    L_0x0127:
        r8 = (java.lang.Long) r8;	 Catch:{ all -> 0x00bd }
        r6 = r8.longValue();	 Catch:{ all -> 0x00bd }
        if (r18 == 0) goto L_0x0137;	 Catch:{ all -> 0x00bd }
    L_0x012f:
        r8 = 76;	 Catch:{ all -> 0x00bd }
        r0 = r19;	 Catch:{ all -> 0x00bd }
        r0.writeLongAndChar(r6, r8);	 Catch:{ all -> 0x00bd }
        goto L_0x010a;	 Catch:{ all -> 0x00bd }
    L_0x0137:
        r0 = r19;	 Catch:{ all -> 0x00bd }
        r0.writeLong(r6);	 Catch:{ all -> 0x00bd }
        goto L_0x010a;	 Catch:{ all -> 0x00bd }
    L_0x013d:
        r0 = r19;	 Catch:{ all -> 0x00bd }
        r6 = r0.disableCircularReferenceDetect;	 Catch:{ all -> 0x00bd }
        if (r6 != 0) goto L_0x0154;	 Catch:{ all -> 0x00bd }
    L_0x0143:
        r11 = new com.alibaba.fastjson.serializer.SerialContext;	 Catch:{ all -> 0x00bd }
        r15 = 0;	 Catch:{ all -> 0x00bd }
        r16 = 0;	 Catch:{ all -> 0x00bd }
        r12 = r5;	 Catch:{ all -> 0x00bd }
        r13 = r23;	 Catch:{ all -> 0x00bd }
        r14 = r24;	 Catch:{ all -> 0x00bd }
        r11.<init>(r12, r13, r14, r15, r16);	 Catch:{ all -> 0x00bd }
        r0 = r22;	 Catch:{ all -> 0x00bd }
        r0.context = r11;	 Catch:{ all -> 0x00bd }
    L_0x0154:
        r0 = r22;	 Catch:{ all -> 0x00bd }
        r6 = r0.containsReference(r8);	 Catch:{ all -> 0x00bd }
        if (r6 == 0) goto L_0x0162;	 Catch:{ all -> 0x00bd }
    L_0x015c:
        r0 = r22;	 Catch:{ all -> 0x00bd }
        r0.writeReference(r8);	 Catch:{ all -> 0x00bd }
        goto L_0x010a;	 Catch:{ all -> 0x00bd }
    L_0x0162:
        r6 = r8.getClass();	 Catch:{ all -> 0x00bd }
        r0 = r22;	 Catch:{ all -> 0x00bd }
        r6 = r0.getObjectWriter(r6);	 Catch:{ all -> 0x00bd }
        r9 = java.lang.Integer.valueOf(r17);	 Catch:{ all -> 0x00bd }
        r11 = 0;	 Catch:{ all -> 0x00bd }
        r7 = r22;	 Catch:{ all -> 0x00bd }
        r6.write(r7, r8, r9, r10, r11);	 Catch:{ all -> 0x00bd }
        goto L_0x010a;	 Catch:{ all -> 0x00bd }
    L_0x0177:
        r4 = 93;	 Catch:{ all -> 0x00bd }
        r0 = r19;	 Catch:{ all -> 0x00bd }
        r0.append(r4);	 Catch:{ all -> 0x00bd }
        r0 = r22;
        r0.context = r5;
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.ListSerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type, int):void");
    }
}
