package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.ParserConfig;
import java.lang.reflect.Constructor;

public class ThrowableDeserializer extends JavaBeanDeserializer {
    public ThrowableDeserializer(ParserConfig parserConfig, Class<?> cls) {
        super(parserConfig, cls, cls);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r11, java.lang.reflect.Type r12, java.lang.Object r13) {
        /*
        r10 = this;
        r5 = r11.lexer;
        r0 = r5.token();
        r1 = 8;
        if (r0 != r1) goto L_0x000f;
    L_0x000a:
        r5.nextToken();
        r0 = 0;
    L_0x000e:
        return r0;
    L_0x000f:
        r0 = r11.getResolveStatus();
        r1 = 2;
        if (r0 != r1) goto L_0x0058;
    L_0x0016:
        r0 = 0;
        r11.setResolveStatus(r0);
    L_0x001a:
        r4 = 0;
        r0 = 0;
        if (r12 == 0) goto L_0x0140;
    L_0x001e:
        r1 = r12 instanceof java.lang.Class;
        if (r1 == 0) goto L_0x0140;
    L_0x0022:
        r12 = (java.lang.Class) r12;
        r1 = java.lang.Throwable.class;
        r1 = r1.isAssignableFrom(r12);
        if (r1 == 0) goto L_0x0140;
    L_0x002c:
        r2 = 0;
        r1 = 0;
        r6 = new java.util.HashMap;
        r6.<init>();
        r3 = r12;
    L_0x0034:
        r0 = r11.getSymbolTable();
        r0 = r5.scanSymbol(r0);
        if (r0 != 0) goto L_0x0078;
    L_0x003e:
        r7 = r5.token();
        r8 = 13;
        if (r7 != r8) goto L_0x0068;
    L_0x0046:
        r0 = 16;
        r5.nextToken(r0);
    L_0x004b:
        if (r3 != 0) goto L_0x012a;
    L_0x004d:
        r0 = new java.lang.Exception;
        r0.<init>(r2, r4);
    L_0x0052:
        if (r1 == 0) goto L_0x000e;
    L_0x0054:
        r0.setStackTrace(r1);
        goto L_0x000e;
    L_0x0058:
        r0 = r5.token();
        r1 = 12;
        if (r0 == r1) goto L_0x001a;
    L_0x0060:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x0068:
        r7 = r5.token();
        r8 = 16;
        if (r7 != r8) goto L_0x0078;
    L_0x0070:
        r7 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas;
        r7 = r5.isEnabled(r7);
        if (r7 != 0) goto L_0x0034;
    L_0x0078:
        r7 = 4;
        r5.nextTokenWithColon(r7);
        r7 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY;
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x00bf;
    L_0x0084:
        r0 = r5.token();
        r3 = 4;
        if (r0 != r3) goto L_0x00b7;
    L_0x008b:
        r0 = r5.stringVal();
        r3 = r11.getConfig();
        r3 = r3.getDefaultClassLoader();
        r0 = com.alibaba.fastjson.util.TypeUtils.loadClass(r0, r3);
        r3 = 16;
        r5.nextToken(r3);
        r3 = r4;
        r9 = r2;
        r2 = r0;
        r0 = r1;
        r1 = r9;
    L_0x00a5:
        r4 = r5.token();
        r7 = 13;
        if (r4 != r7) goto L_0x0124;
    L_0x00ad:
        r4 = 16;
        r5.nextToken(r4);
        r4 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r0;
        goto L_0x004b;
    L_0x00b7:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x00bf:
        r7 = "message";
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x00ed;
    L_0x00c7:
        r0 = r5.token();
        r2 = 8;
        if (r0 != r2) goto L_0x00d9;
    L_0x00cf:
        r0 = 0;
    L_0x00d0:
        r5.nextToken();
        r2 = r3;
        r3 = r4;
        r9 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x00a5;
    L_0x00d9:
        r0 = r5.token();
        r2 = 4;
        if (r0 != r2) goto L_0x00e5;
    L_0x00e0:
        r0 = r5.stringVal();
        goto L_0x00d0;
    L_0x00e5:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x00ed:
        r7 = "cause";
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x0104;
    L_0x00f5:
        r0 = 0;
        r4 = "cause";
        r0 = r10.deserialze(r11, r0, r4);
        r0 = (java.lang.Throwable) r0;
        r9 = r1;
        r1 = r2;
        r2 = r3;
        r3 = r0;
        r0 = r9;
        goto L_0x00a5;
    L_0x0104:
        r7 = "stackTrace";
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x0118;
    L_0x010c:
        r0 = java.lang.StackTraceElement[].class;
        r0 = r11.parseObject(r0);
        r0 = (java.lang.StackTraceElement[]) r0;
        r1 = r2;
        r2 = r3;
        r3 = r4;
        goto L_0x00a5;
    L_0x0118:
        r7 = r11.parse();
        r6.put(r0, r7);
        r0 = r1;
        r1 = r2;
        r2 = r3;
        r3 = r4;
        goto L_0x00a5;
    L_0x0124:
        r4 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r0;
        goto L_0x0034;
    L_0x012a:
        r0 = r10.createException(r2, r4, r3);	 Catch:{ Exception -> 0x0137 }
        if (r0 != 0) goto L_0x0052;
    L_0x0130:
        r0 = new java.lang.Exception;	 Catch:{ Exception -> 0x0137 }
        r0.<init>(r2, r4);	 Catch:{ Exception -> 0x0137 }
        goto L_0x0052;
    L_0x0137:
        r0 = move-exception;
        r1 = new com.alibaba.fastjson.JSONException;
        r2 = "create instance error";
        r1.<init>(r2, r0);
        throw r1;
    L_0x0140:
        r12 = r0;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object):T");
    }

    private Throwable createException(String str, Throwable th, Class<?> cls) throws Exception {
        Constructor constructor = null;
        Constructor constructor2 = null;
        Constructor[] constructors = cls.getConstructors();
        int length = constructors.length;
        int i = 0;
        Constructor constructor3 = null;
        while (i < length) {
            Constructor constructor4 = constructors[i];
            Class[] parameterTypes = constructor4.getParameterTypes();
            if (parameterTypes.length == 0) {
                Constructor constructor5 = constructor2;
                constructor2 = constructor4;
                constructor4 = constructor5;
            } else if (parameterTypes.length == 1 && parameterTypes[0] == String.class) {
                constructor2 = constructor;
            } else if (parameterTypes.length == 2 && parameterTypes[0] == String.class && parameterTypes[1] == Throwable.class) {
                constructor3 = constructor4;
                constructor4 = constructor2;
                constructor2 = constructor;
            } else {
                constructor4 = constructor2;
                constructor2 = constructor;
            }
            i++;
            constructor = constructor2;
            constructor2 = constructor4;
        }
        if (constructor3 != null) {
            return (Throwable) constructor3.newInstance(new Object[]{str, th});
        } else if (constructor2 != null) {
            return (Throwable) constructor2.newInstance(new Object[]{str});
        } else if (constructor != null) {
            return (Throwable) constructor.newInstance(new Object[0]);
        } else {
            return null;
        }
    }

    public int getFastMatchToken() {
        return 12;
    }
}
