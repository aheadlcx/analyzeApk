package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tencent.bugly.Bugly;
import cz.msebera.android.httpclient.message.TokenParser;
import java.io.IOException;

public class TextNode extends ValueNode {
    static final TextNode EMPTY_STRING_NODE = new TextNode("");
    protected final String _value;

    public TextNode(String str) {
        this._value = str;
    }

    public static TextNode valueOf(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return EMPTY_STRING_NODE;
        }
        return new TextNode(str);
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.STRING;
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_STRING;
    }

    public String textValue() {
        return this._value;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getBinaryValue(com.fasterxml.jackson.core.Base64Variant r12) throws java.io.IOException {
        /*
        r11 = this;
        r10 = 3;
        r2 = 0;
        r9 = -2;
        r3 = new com.fasterxml.jackson.core.util.ByteArrayBuilder;
        r0 = 100;
        r3.<init>(r0);
        r4 = r11._value;
        r5 = r4.length();
        r0 = r2;
    L_0x0011:
        if (r0 >= r5) goto L_0x001b;
    L_0x0013:
        r1 = r0 + 1;
        r0 = r4.charAt(r0);
        if (r1 < r5) goto L_0x0020;
    L_0x001b:
        r0 = r3.toByteArray();
        return r0;
    L_0x0020:
        r6 = 32;
        if (r0 <= r6) goto L_0x00d6;
    L_0x0024:
        r6 = r12.decodeBase64Char(r0);
        if (r6 >= 0) goto L_0x002d;
    L_0x002a:
        r11._reportInvalidBase64(r12, r0, r2);
    L_0x002d:
        if (r1 < r5) goto L_0x0032;
    L_0x002f:
        r11._reportBase64EOF();
    L_0x0032:
        r0 = r1 + 1;
        r1 = r4.charAt(r1);
        r7 = r12.decodeBase64Char(r1);
        if (r7 >= 0) goto L_0x0042;
    L_0x003e:
        r8 = 1;
        r11._reportInvalidBase64(r12, r1, r8);
    L_0x0042:
        r1 = r6 << 6;
        r1 = r1 | r7;
        if (r0 < r5) goto L_0x0056;
    L_0x0047:
        r6 = r12.usesPadding();
        if (r6 != 0) goto L_0x0053;
    L_0x004d:
        r0 = r1 >> 4;
        r3.append(r0);
        goto L_0x001b;
    L_0x0053:
        r11._reportBase64EOF();
    L_0x0056:
        r6 = r0 + 1;
        r0 = r4.charAt(r0);
        r7 = r12.decodeBase64Char(r0);
        if (r7 >= 0) goto L_0x00a0;
    L_0x0062:
        if (r7 == r9) goto L_0x0068;
    L_0x0064:
        r7 = 2;
        r11._reportInvalidBase64(r12, r0, r7);
    L_0x0068:
        if (r6 < r5) goto L_0x006d;
    L_0x006a:
        r11._reportBase64EOF();
    L_0x006d:
        r0 = r6 + 1;
        r6 = r4.charAt(r6);
        r7 = r12.usesPaddingChar(r6);
        if (r7 != 0) goto L_0x0099;
    L_0x0079:
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = "expected padding character '";
        r7 = r7.append(r8);
        r8 = r12.getPaddingChar();
        r7 = r7.append(r8);
        r8 = "'";
        r7 = r7.append(r8);
        r7 = r7.toString();
        r11._reportInvalidBase64(r12, r6, r10, r7);
    L_0x0099:
        r1 = r1 >> 4;
        r3.append(r1);
        goto L_0x0011;
    L_0x00a0:
        r0 = r1 << 6;
        r1 = r0 | r7;
        if (r6 < r5) goto L_0x00b6;
    L_0x00a6:
        r0 = r12.usesPadding();
        if (r0 != 0) goto L_0x00b3;
    L_0x00ac:
        r0 = r1 >> 2;
        r3.appendTwoBytes(r0);
        goto L_0x001b;
    L_0x00b3:
        r11._reportBase64EOF();
    L_0x00b6:
        r0 = r6 + 1;
        r6 = r4.charAt(r6);
        r7 = r12.decodeBase64Char(r6);
        if (r7 >= 0) goto L_0x00ce;
    L_0x00c2:
        if (r7 == r9) goto L_0x00c7;
    L_0x00c4:
        r11._reportInvalidBase64(r12, r6, r10);
    L_0x00c7:
        r1 = r1 >> 2;
        r3.appendTwoBytes(r1);
        goto L_0x0011;
    L_0x00ce:
        r1 = r1 << 6;
        r1 = r1 | r7;
        r3.appendThreeBytes(r1);
        goto L_0x0011;
    L_0x00d6:
        r0 = r1;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.node.TextNode.getBinaryValue(com.fasterxml.jackson.core.Base64Variant):byte[]");
    }

    public byte[] binaryValue() throws IOException {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    public String asText() {
        return this._value;
    }

    public String asText(String str) {
        return this._value == null ? str : this._value;
    }

    public boolean asBoolean(boolean z) {
        if (this._value == null) {
            return z;
        }
        String trim = this._value.trim();
        if ("true".equals(trim)) {
            return true;
        }
        if (Bugly.SDK_IS_DEV.equals(trim)) {
            return false;
        }
        return z;
    }

    public int asInt(int i) {
        return NumberInput.parseAsInt(this._value, i);
    }

    public long asLong(long j) {
        return NumberInput.parseAsLong(this._value, j);
    }

    public double asDouble(double d) {
        return NumberInput.parseAsDouble(this._value, d);
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (this._value == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(this._value);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof TextNode)) {
            return false;
        }
        return ((TextNode) obj)._value.equals(this._value);
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    public String toString() {
        int length = this._value.length();
        StringBuilder stringBuilder = new StringBuilder((length >> 4) + (length + 2));
        appendQuoted(stringBuilder, this._value);
        return stringBuilder.toString();
    }

    protected static void appendQuoted(StringBuilder stringBuilder, String str) {
        stringBuilder.append('\"');
        CharTypes.appendQuoted(stringBuilder, str);
        stringBuilder.append('\"');
    }

    protected void _reportInvalidBase64(Base64Variant base64Variant, char c, int i) throws JsonParseException {
        _reportInvalidBase64(base64Variant, c, i, null);
    }

    protected void _reportInvalidBase64(Base64Variant base64Variant, char c, int i, String str) throws JsonParseException {
        String str2;
        if (c <= TokenParser.SP) {
            str2 = "Illegal white space character (code 0x" + Integer.toHexString(c) + ") as character #" + (i + 1) + " of 4-char base64 unit: can only used between units";
        } else if (base64Variant.usesPaddingChar(c)) {
            str2 = "Unexpected padding character ('" + base64Variant.getPaddingChar() + "') as character #" + (i + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (!Character.isDefined(c) || Character.isISOControl(c)) {
            str2 = "Illegal character (code 0x" + Integer.toHexString(c) + ") in base64 content";
        } else {
            str2 = "Illegal character '" + c + "' (code 0x" + Integer.toHexString(c) + ") in base64 content";
        }
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        throw new JsonParseException(null, str2, JsonLocation.NA);
    }

    protected void _reportBase64EOF() throws JsonParseException {
        throw new JsonParseException(null, "Unexpected end-of-String when base64 content");
    }
}
