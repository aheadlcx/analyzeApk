package com.alibaba.fastjson.parser.deserializer;

public class StackTraceElementDeserializer implements ObjectDeserializer {
    public static final StackTraceElementDeserializer instance = new StackTraceElementDeserializer();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r9, java.lang.reflect.Type r10, java.lang.Object r11) {
        /*
        r8 = this;
        r4 = r9.lexer;
        r0 = r4.token();
        r1 = 8;
        if (r0 != r1) goto L_0x000f;
    L_0x000a:
        r4.nextToken();
        r0 = 0;
    L_0x000e:
        return r0;
    L_0x000f:
        r0 = r4.token();
        r1 = 12;
        if (r0 == r1) goto L_0x0040;
    L_0x0017:
        r0 = r4.token();
        r1 = 16;
        if (r0 == r1) goto L_0x0040;
    L_0x001f:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "syntax error: ";
        r1 = r1.append(r2);
        r2 = r4.token();
        r2 = com.alibaba.fastjson.parser.JSONToken.name(r2);
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0040:
        r3 = 0;
        r2 = 0;
        r1 = 0;
        r0 = 0;
    L_0x0044:
        r5 = r9.getSymbolTable();
        r5 = r4.scanSymbol(r5);
        if (r5 != 0) goto L_0x0072;
    L_0x004e:
        r6 = r4.token();
        r7 = 13;
        if (r6 != r7) goto L_0x0062;
    L_0x0056:
        r5 = 16;
        r4.nextToken(r5);
    L_0x005b:
        r4 = new java.lang.StackTraceElement;
        r4.<init>(r3, r2, r1, r0);
        r0 = r4;
        goto L_0x000e;
    L_0x0062:
        r6 = r4.token();
        r7 = 16;
        if (r6 != r7) goto L_0x0072;
    L_0x006a:
        r6 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas;
        r6 = r4.isEnabled(r6);
        if (r6 != 0) goto L_0x0044;
    L_0x0072:
        r6 = 4;
        r4.nextTokenWithColon(r6);
        r6 = "className";
        r6 = r6.equals(r5);
        if (r6 == 0) goto L_0x00a9;
    L_0x007e:
        r3 = r4.token();
        r5 = 8;
        if (r3 != r5) goto L_0x0095;
    L_0x0086:
        r3 = 0;
    L_0x0087:
        r5 = r4.token();
        r6 = 13;
        if (r5 != r6) goto L_0x0044;
    L_0x008f:
        r5 = 16;
        r4.nextToken(r5);
        goto L_0x005b;
    L_0x0095:
        r3 = r4.token();
        r5 = 4;
        if (r3 != r5) goto L_0x00a1;
    L_0x009c:
        r3 = r4.stringVal();
        goto L_0x0087;
    L_0x00a1:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x00a9:
        r6 = "methodName";
        r6 = r6.equals(r5);
        if (r6 == 0) goto L_0x00cf;
    L_0x00b1:
        r2 = r4.token();
        r5 = 8;
        if (r2 != r5) goto L_0x00bb;
    L_0x00b9:
        r2 = 0;
        goto L_0x0087;
    L_0x00bb:
        r2 = r4.token();
        r5 = 4;
        if (r2 != r5) goto L_0x00c7;
    L_0x00c2:
        r2 = r4.stringVal();
        goto L_0x0087;
    L_0x00c7:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x00cf:
        r6 = "fileName";
        r6 = r6.equals(r5);
        if (r6 == 0) goto L_0x00f5;
    L_0x00d7:
        r1 = r4.token();
        r5 = 8;
        if (r1 != r5) goto L_0x00e1;
    L_0x00df:
        r1 = 0;
        goto L_0x0087;
    L_0x00e1:
        r1 = r4.token();
        r5 = 4;
        if (r1 != r5) goto L_0x00ed;
    L_0x00e8:
        r1 = r4.stringVal();
        goto L_0x0087;
    L_0x00ed:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x00f5:
        r6 = "lineNumber";
        r6 = r6.equals(r5);
        if (r6 == 0) goto L_0x011c;
    L_0x00fd:
        r0 = r4.token();
        r5 = 8;
        if (r0 != r5) goto L_0x0107;
    L_0x0105:
        r0 = 0;
        goto L_0x0087;
    L_0x0107:
        r0 = r4.token();
        r5 = 2;
        if (r0 != r5) goto L_0x0114;
    L_0x010e:
        r0 = r4.intValue();
        goto L_0x0087;
    L_0x0114:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x011c:
        r6 = "nativeMethod";
        r6 = r6.equals(r5);
        if (r6 == 0) goto L_0x0157;
    L_0x0124:
        r5 = r4.token();
        r6 = 8;
        if (r5 != r6) goto L_0x0133;
    L_0x012c:
        r5 = 16;
        r4.nextToken(r5);
        goto L_0x0087;
    L_0x0133:
        r5 = r4.token();
        r6 = 6;
        if (r5 != r6) goto L_0x0141;
    L_0x013a:
        r5 = 16;
        r4.nextToken(r5);
        goto L_0x0087;
    L_0x0141:
        r5 = r4.token();
        r6 = 7;
        if (r5 != r6) goto L_0x014f;
    L_0x0148:
        r5 = 16;
        r4.nextToken(r5);
        goto L_0x0087;
    L_0x014f:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x0157:
        r6 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY;
        if (r5 != r6) goto L_0x0197;
    L_0x015b:
        r5 = r4.token();
        r6 = 4;
        if (r5 != r6) goto L_0x0187;
    L_0x0162:
        r5 = r4.stringVal();
        r6 = "java.lang.StackTraceElement";
        r6 = r5.equals(r6);
        if (r6 != 0) goto L_0x0087;
    L_0x016e:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "syntax error : ";
        r1 = r1.append(r2);
        r1 = r1.append(r5);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0187:
        r5 = r4.token();
        r6 = 8;
        if (r5 == r6) goto L_0x0087;
    L_0x018f:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x0197:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "syntax error : ";
        r1 = r1.append(r2);
        r1 = r1.append(r5);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.StackTraceElementDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object):T");
    }

    public int getFastMatchToken() {
        return 12;
    }
}
