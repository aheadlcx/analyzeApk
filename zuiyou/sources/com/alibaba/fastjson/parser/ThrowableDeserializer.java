package com.alibaba.fastjson.parser;

public class ThrowableDeserializer extends JavaBeanDeserializer {
    public ThrowableDeserializer(ParserConfig parserConfig, Class<?> cls) {
        super(parserConfig, cls, cls);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r19, java.lang.reflect.Type r20, java.lang.Object r21) {
        /*
        r18 = this;
        r0 = r19;
        r8 = r0.lexer;
        r2 = r8.token;
        r3 = 8;
        if (r2 != r3) goto L_0x000f;
    L_0x000a:
        r8.nextToken();
        r2 = 0;
    L_0x000e:
        return r2;
    L_0x000f:
        r0 = r19;
        r2 = r0.resolveStatus;
        r3 = 2;
        if (r2 != r3) goto L_0x0092;
    L_0x0016:
        r2 = 0;
        r0 = r19;
        r0.resolveStatus = r2;
    L_0x001b:
        r7 = 0;
        r2 = 0;
        if (r20 == 0) goto L_0x0240;
    L_0x001f:
        r0 = r20;
        r3 = r0 instanceof java.lang.Class;
        if (r3 == 0) goto L_0x0240;
    L_0x0025:
        r20 = (java.lang.Class) r20;
        r3 = java.lang.Throwable.class;
        r0 = r20;
        r3 = r3.isAssignableFrom(r0);
        if (r3 == 0) goto L_0x0240;
    L_0x0031:
        r5 = 0;
        r4 = 0;
        r3 = 0;
        r6 = r20;
    L_0x0036:
        r0 = r19;
        r2 = r0.symbolTable;
        r9 = r8.scanSymbol(r2);
        if (r9 != 0) goto L_0x00a7;
    L_0x0040:
        r2 = r8.token;
        r10 = 13;
        if (r2 != r10) goto L_0x00a1;
    L_0x0046:
        r2 = 16;
        r8.nextToken(r2);
        r8 = r3;
        r9 = r4;
        r10 = r5;
        r11 = r6;
        r12 = r7;
    L_0x0050:
        r6 = 0;
        if (r11 != 0) goto L_0x0177;
    L_0x0053:
        r4 = new java.lang.Exception;
        r4.<init>(r10, r12);
    L_0x0058:
        if (r9 == 0) goto L_0x005d;
    L_0x005a:
        r4.setStackTrace(r9);
    L_0x005d:
        if (r8 == 0) goto L_0x0230;
    L_0x005f:
        r3 = 0;
        if (r11 == 0) goto L_0x0233;
    L_0x0062:
        r0 = r18;
        r2 = r0.clazz;
        if (r11 != r2) goto L_0x021e;
    L_0x0068:
        r2 = r8.entrySet();
        r5 = r2.iterator();
    L_0x0070:
        r2 = r5.hasNext();
        if (r2 == 0) goto L_0x0230;
    L_0x0076:
        r2 = r5.next();
        r2 = (java.util.Map.Entry) r2;
        r3 = r2.getKey();
        r3 = (java.lang.String) r3;
        r2 = r2.getValue();
        r0 = r18;
        r3 = r0.getFieldDeserializer(r3);
        if (r3 == 0) goto L_0x0070;
    L_0x008e:
        r3.setValue(r4, r2);
        goto L_0x0070;
    L_0x0092:
        r2 = r8.token;
        r3 = 12;
        if (r2 == r3) goto L_0x001b;
    L_0x0098:
        r2 = new com.alibaba.fastjson.JSONException;
        r3 = "syntax error";
        r2.<init>(r3);
        throw r2;
    L_0x00a1:
        r2 = r8.token;
        r10 = 16;
        if (r2 == r10) goto L_0x0036;
    L_0x00a7:
        r2 = 58;
        r8.nextTokenWithChar(r2);
        r2 = "@type";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x00f0;
    L_0x00b5:
        r2 = r8.token;
        r6 = 4;
        if (r2 != r6) goto L_0x00e7;
    L_0x00ba:
        r2 = r8.stringVal();
        r0 = r19;
        r6 = r0.config;
        r6 = r6.defaultClassLoader;
        r2 = com.alibaba.fastjson.util.TypeUtils.loadClass(r2, r6);
        r6 = 16;
        r8.nextToken(r6);
        r6 = r7;
        r17 = r4;
        r4 = r5;
        r5 = r2;
        r2 = r3;
        r3 = r17;
    L_0x00d5:
        r7 = r8.token;
        r9 = 13;
        if (r7 != r9) goto L_0x0170;
    L_0x00db:
        r7 = 16;
        r8.nextToken(r7);
        r8 = r2;
        r9 = r3;
        r10 = r4;
        r11 = r5;
        r12 = r6;
        goto L_0x0050;
    L_0x00e7:
        r2 = new com.alibaba.fastjson.JSONException;
        r3 = "syntax error";
        r2.<init>(r3);
        throw r2;
    L_0x00f0:
        r2 = "message";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x011f;
    L_0x00f9:
        r2 = r8.token;
        r5 = 8;
        if (r2 != r5) goto L_0x010c;
    L_0x00ff:
        r2 = 0;
    L_0x0100:
        r8.nextToken();
        r5 = r6;
        r6 = r7;
        r17 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r17;
        goto L_0x00d5;
    L_0x010c:
        r2 = r8.token;
        r5 = 4;
        if (r2 != r5) goto L_0x0116;
    L_0x0111:
        r2 = r8.stringVal();
        goto L_0x0100;
    L_0x0116:
        r2 = new com.alibaba.fastjson.JSONException;
        r3 = "syntax error";
        r2.<init>(r3);
        throw r2;
    L_0x011f:
        r2 = "cause";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x013f;
    L_0x0128:
        r2 = 0;
        r7 = "cause";
        r0 = r18;
        r1 = r19;
        r2 = r0.deserialze(r1, r2, r7);
        r2 = (java.lang.Throwable) r2;
        r17 = r3;
        r3 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r2;
        r2 = r17;
        goto L_0x00d5;
    L_0x013f:
        r2 = "stackTrace";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x015c;
    L_0x0148:
        r2 = java.lang.StackTraceElement[].class;
        r0 = r19;
        r2 = r0.parseObject(r2);
        r2 = (java.lang.StackTraceElement[]) r2;
        r4 = r5;
        r5 = r6;
        r6 = r7;
        r17 = r2;
        r2 = r3;
        r3 = r17;
        goto L_0x00d5;
    L_0x015c:
        if (r3 != 0) goto L_0x023d;
    L_0x015e:
        r2 = new java.util.HashMap;
        r2.<init>();
    L_0x0163:
        r3 = r19.parse();
        r2.put(r9, r3);
        r3 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r7;
        goto L_0x00d5;
    L_0x0170:
        r7 = r6;
        r6 = r5;
        r5 = r4;
        r4 = r3;
        r3 = r2;
        goto L_0x0036;
    L_0x0177:
        r5 = 0;
        r4 = 0;
        r3 = 0;
        r13 = r11.getConstructors();	 Catch:{ Exception -> 0x0214 }
        r14 = r13.length;	 Catch:{ Exception -> 0x0214 }
        r2 = 0;
        r7 = r2;
        r2 = r3;
    L_0x0182:
        if (r7 >= r14) goto L_0x01de;
    L_0x0184:
        r3 = r13[r7];	 Catch:{ Exception -> 0x0214 }
        r15 = r3.getParameterTypes();	 Catch:{ Exception -> 0x0214 }
        r15 = r15.length;	 Catch:{ Exception -> 0x0214 }
        if (r15 != 0) goto L_0x0198;
    L_0x018d:
        r17 = r4;
        r4 = r3;
        r3 = r17;
    L_0x0192:
        r5 = r7 + 1;
        r7 = r5;
        r5 = r4;
        r4 = r3;
        goto L_0x0182;
    L_0x0198:
        r15 = r3.getParameterTypes();	 Catch:{ Exception -> 0x0214 }
        r15 = r15.length;	 Catch:{ Exception -> 0x0214 }
        r16 = 1;
        r0 = r16;
        if (r15 != r0) goto L_0x01b3;
    L_0x01a3:
        r15 = r3.getParameterTypes();	 Catch:{ Exception -> 0x0214 }
        r16 = 0;
        r15 = r15[r16];	 Catch:{ Exception -> 0x0214 }
        r16 = java.lang.String.class;
        r0 = r16;
        if (r15 != r0) goto L_0x01b3;
    L_0x01b1:
        r4 = r5;
        goto L_0x0192;
    L_0x01b3:
        r15 = r3.getParameterTypes();	 Catch:{ Exception -> 0x0214 }
        r15 = r15.length;	 Catch:{ Exception -> 0x0214 }
        r16 = 2;
        r0 = r16;
        if (r15 != r0) goto L_0x0239;
    L_0x01be:
        r15 = r3.getParameterTypes();	 Catch:{ Exception -> 0x0214 }
        r16 = 0;
        r15 = r15[r16];	 Catch:{ Exception -> 0x0214 }
        r16 = java.lang.String.class;
        r0 = r16;
        if (r15 != r0) goto L_0x0239;
    L_0x01cc:
        r15 = r3.getParameterTypes();	 Catch:{ Exception -> 0x0214 }
        r16 = 1;
        r15 = r15[r16];	 Catch:{ Exception -> 0x0214 }
        r16 = java.lang.Throwable.class;
        r0 = r16;
        if (r15 != r0) goto L_0x0239;
    L_0x01da:
        r2 = r3;
        r3 = r4;
        r4 = r5;
        goto L_0x0192;
    L_0x01de:
        if (r2 == 0) goto L_0x01f9;
    L_0x01e0:
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x0214 }
        r4 = 0;
        r3[r4] = r10;	 Catch:{ Exception -> 0x0214 }
        r4 = 1;
        r3[r4] = r12;	 Catch:{ Exception -> 0x0214 }
        r2 = r2.newInstance(r3);	 Catch:{ Exception -> 0x0214 }
        r2 = (java.lang.Throwable) r2;	 Catch:{ Exception -> 0x0214 }
    L_0x01ef:
        if (r2 != 0) goto L_0x01f6;
    L_0x01f1:
        r2 = new java.lang.Exception;	 Catch:{ Exception -> 0x0214 }
        r2.<init>(r10, r12);	 Catch:{ Exception -> 0x0214 }
    L_0x01f6:
        r4 = r2;
        goto L_0x0058;
    L_0x01f9:
        if (r4 == 0) goto L_0x0208;
    L_0x01fb:
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0214 }
        r3 = 0;
        r2[r3] = r10;	 Catch:{ Exception -> 0x0214 }
        r2 = r4.newInstance(r2);	 Catch:{ Exception -> 0x0214 }
        r2 = (java.lang.Throwable) r2;	 Catch:{ Exception -> 0x0214 }
        goto L_0x01ef;
    L_0x0208:
        if (r5 == 0) goto L_0x0237;
    L_0x020a:
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0214 }
        r2 = r5.newInstance(r2);	 Catch:{ Exception -> 0x0214 }
        r2 = (java.lang.Throwable) r2;	 Catch:{ Exception -> 0x0214 }
        goto L_0x01ef;
    L_0x0214:
        r2 = move-exception;
        r3 = new com.alibaba.fastjson.JSONException;
        r4 = "create instance error";
        r3.<init>(r4, r2);
        throw r3;
    L_0x021e:
        r0 = r19;
        r2 = r0.config;
        r2 = r2.getDeserializer(r11);
        r5 = r2 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer;
        if (r5 == 0) goto L_0x0233;
    L_0x022a:
        r2 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r2;
        r18 = r2;
        goto L_0x0068;
    L_0x0230:
        r2 = r4;
        goto L_0x000e;
    L_0x0233:
        r18 = r3;
        goto L_0x0068;
    L_0x0237:
        r2 = r6;
        goto L_0x01ef;
    L_0x0239:
        r3 = r4;
        r4 = r5;
        goto L_0x0192;
    L_0x023d:
        r2 = r3;
        goto L_0x0163;
    L_0x0240:
        r20 = r2;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.ThrowableDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object):T");
    }
}
