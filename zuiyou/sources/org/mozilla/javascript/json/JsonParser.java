package org.mozilla.javascript.json;

import java.util.ArrayList;
import java.util.List;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;

public class JsonParser {
    static final /* synthetic */ boolean $assertionsDisabled = (!JsonParser.class.desiredAssertionStatus());
    private Context cx;
    private int length;
    private int pos;
    private Scriptable scope;
    private String src;

    public static class ParseException extends Exception {
        static final long serialVersionUID = 4804542791749920772L;

        ParseException(String str) {
            super(str);
        }

        ParseException(Exception exception) {
            super(exception);
        }
    }

    public JsonParser(Context context, Scriptable scriptable) {
        this.cx = context;
        this.scope = scriptable;
    }

    public synchronized Object parseValue(String str) throws ParseException {
        Object readValue;
        if (str == null) {
            throw new ParseException("Input string may not be null");
        }
        this.pos = 0;
        this.length = str.length();
        this.src = str;
        readValue = readValue();
        consumeWhitespace();
        if (this.pos < this.length) {
            throw new ParseException("Expected end of stream at char " + this.pos);
        }
        return readValue;
    }

    private Object readValue() throws ParseException {
        consumeWhitespace();
        if (this.pos < this.length) {
            String str = this.src;
            int i = this.pos;
            this.pos = i + 1;
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\"':
                    return readString();
                case '-':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    return readNumber(charAt);
                case '[':
                    return readArray();
                case 'f':
                    return readFalse();
                case 'n':
                    return readNull();
                case 't':
                    return readTrue();
                case '{':
                    return readObject();
                default:
                    throw new ParseException("Unexpected token: " + charAt);
            }
        }
        throw new ParseException("Empty JSON string");
    }

    private Object readObject() throws ParseException {
        consumeWhitespace();
        Scriptable newObject = this.cx.newObject(this.scope);
        if (this.pos >= this.length || this.src.charAt(this.pos) != '}') {
            Object obj = null;
            while (this.pos < this.length) {
                String str = this.src;
                int i = this.pos;
                this.pos = i + 1;
                switch (str.charAt(i)) {
                    case '\"':
                        if (obj == null) {
                            String readString = readString();
                            consume(':');
                            Object readValue = readValue();
                            long indexFromString = ScriptRuntime.indexFromString(readString);
                            if (indexFromString < 0) {
                                newObject.put(readString, newObject, readValue);
                            } else {
                                newObject.put((int) indexFromString, newObject, readValue);
                            }
                            obj = 1;
                            break;
                        }
                        throw new ParseException("Missing comma in object literal");
                    case ',':
                        if (obj != null) {
                            obj = null;
                            break;
                        }
                        throw new ParseException("Unexpected comma in object literal");
                    case '}':
                        if (obj != null) {
                            return newObject;
                        }
                        throw new ParseException("Unexpected comma in object literal");
                    default:
                        throw new ParseException("Unexpected token in object literal");
                }
                consumeWhitespace();
            }
            throw new ParseException("Unterminated object literal");
        }
        this.pos++;
        return newObject;
    }

    private Object readArray() throws ParseException {
        consumeWhitespace();
        if (this.pos >= this.length || this.src.charAt(this.pos) != ']') {
            List arrayList = new ArrayList();
            int i = 0;
            while (this.pos < this.length) {
                switch (this.src.charAt(this.pos)) {
                    case ',':
                        if (i != 0) {
                            this.pos++;
                            i = 0;
                            break;
                        }
                        throw new ParseException("Unexpected comma in array literal");
                    case ']':
                        if (i == 0) {
                            throw new ParseException("Unexpected comma in array literal");
                        }
                        this.pos++;
                        return this.cx.newArray(this.scope, arrayList.toArray());
                    default:
                        if (i == 0) {
                            arrayList.add(readValue());
                            i = 1;
                            break;
                        }
                        throw new ParseException("Missing comma in array literal");
                }
                consumeWhitespace();
            }
            throw new ParseException("Unterminated array literal");
        }
        this.pos++;
        return this.cx.newArray(this.scope, 0);
    }

    private String readString() throws ParseException {
        int i = this.pos;
        while (this.pos < this.length) {
            String str = this.src;
            int i2 = this.pos;
            this.pos = i2 + 1;
            char charAt = str.charAt(i2);
            if (charAt <= '\u001f') {
                throw new ParseException("String contains control character");
            } else if (charAt == '\\') {
                break;
            } else if (charAt == '\"') {
                return this.src.substring(i, this.pos - 1);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (this.pos < this.length) {
            if ($assertionsDisabled || this.src.charAt(this.pos - 1) == '\\') {
                stringBuilder.append(this.src, i, this.pos - 1);
                if (this.pos >= this.length) {
                    throw new ParseException("Unterminated string");
                }
                String str2 = this.src;
                i2 = this.pos;
                this.pos = i2 + 1;
                char charAt2 = str2.charAt(i2);
                switch (charAt2) {
                    case '\"':
                        stringBuilder.append('\"');
                        break;
                    case '/':
                        stringBuilder.append('/');
                        break;
                    case '\\':
                        stringBuilder.append('\\');
                        break;
                    case 'b':
                        stringBuilder.append('\b');
                        break;
                    case 'f':
                        stringBuilder.append('\f');
                        break;
                    case 'n':
                        stringBuilder.append('\n');
                        break;
                    case 'r':
                        stringBuilder.append('\r');
                        break;
                    case 't':
                        stringBuilder.append('\t');
                        break;
                    case 'u':
                        if (this.length - this.pos >= 5) {
                            i = (((fromHex(this.src.charAt(this.pos + 0)) << 12) | (fromHex(this.src.charAt(this.pos + 1)) << 8)) | (fromHex(this.src.charAt(this.pos + 2)) << 4)) | fromHex(this.src.charAt(this.pos + 3));
                            if (i >= 0) {
                                this.pos += 4;
                                stringBuilder.append((char) i);
                                break;
                            }
                            throw new ParseException("Invalid character code: " + this.src.substring(this.pos, this.pos + 4));
                        }
                        throw new ParseException("Invalid character code: \\u" + this.src.substring(this.pos));
                    default:
                        throw new ParseException("Unexpected character in string: '\\" + charAt2 + "'");
                }
                i = this.pos;
                while (this.pos < this.length) {
                    String str3 = this.src;
                    int i3 = this.pos;
                    this.pos = i3 + 1;
                    char charAt3 = str3.charAt(i3);
                    if (charAt3 <= '\u001f') {
                        throw new ParseException("String contains control character");
                    } else if (charAt3 == '\\') {
                        continue;
                    } else if (charAt3 == '\"') {
                        stringBuilder.append(this.src, i, this.pos - 1);
                        return stringBuilder.toString();
                    }
                }
            }
            throw new AssertionError();
        }
        throw new ParseException("Unterminated string literal");
    }

    private int fromHex(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c < 'A' || c > 'F') {
            return (c < 'a' || c > 'f') ? -1 : (c - 97) + 10;
        } else {
            return (c - 65) + 10;
        }
    }

    private Number readNumber(char c) throws ParseException {
        if ($assertionsDisabled || c == '-' || (c >= '0' && c <= '9')) {
            char nextOrNumberError;
            int i = this.pos - 1;
            if (c == '-') {
                c = nextOrNumberError(i);
                if (c < '0' || c > '9') {
                    throw numberError(i, this.pos);
                }
            }
            if (c != '0') {
                readDigits();
            }
            if (this.pos < this.length && this.src.charAt(this.pos) == '.') {
                this.pos++;
                nextOrNumberError = nextOrNumberError(i);
                if (nextOrNumberError < '0' || nextOrNumberError > '9') {
                    throw numberError(i, this.pos);
                }
                readDigits();
            }
            if (this.pos < this.length) {
                nextOrNumberError = this.src.charAt(this.pos);
                if (nextOrNumberError == 'e' || nextOrNumberError == 'E') {
                    this.pos++;
                    nextOrNumberError = nextOrNumberError(i);
                    if (nextOrNumberError == '-' || nextOrNumberError == '+') {
                        nextOrNumberError = nextOrNumberError(i);
                    }
                    if (nextOrNumberError < '0' || nextOrNumberError > '9') {
                        throw numberError(i, this.pos);
                    }
                    readDigits();
                }
            }
            double parseDouble = Double.parseDouble(this.src.substring(i, this.pos));
            int i2 = (int) parseDouble;
            if (((double) i2) == parseDouble) {
                return Integer.valueOf(i2);
            }
            return Double.valueOf(parseDouble);
        }
        throw new AssertionError();
    }

    private ParseException numberError(int i, int i2) {
        return new ParseException("Unsupported number format: " + this.src.substring(i, i2));
    }

    private char nextOrNumberError(int i) throws ParseException {
        if (this.pos >= this.length) {
            throw numberError(i, this.length);
        }
        String str = this.src;
        int i2 = this.pos;
        this.pos = i2 + 1;
        return str.charAt(i2);
    }

    private void readDigits() {
        while (this.pos < this.length) {
            char charAt = this.src.charAt(this.pos);
            if (charAt >= '0' && charAt <= '9') {
                this.pos++;
            } else {
                return;
            }
        }
    }

    private Boolean readTrue() throws ParseException {
        if (this.length - this.pos >= 3 && this.src.charAt(this.pos) == 'r' && this.src.charAt(this.pos + 1) == 'u' && this.src.charAt(this.pos + 2) == 'e') {
            this.pos += 3;
            return Boolean.TRUE;
        }
        throw new ParseException("Unexpected token: t");
    }

    private Boolean readFalse() throws ParseException {
        if (this.length - this.pos >= 4 && this.src.charAt(this.pos) == 'a' && this.src.charAt(this.pos + 1) == 'l' && this.src.charAt(this.pos + 2) == 's' && this.src.charAt(this.pos + 3) == 'e') {
            this.pos += 4;
            return Boolean.FALSE;
        }
        throw new ParseException("Unexpected token: f");
    }

    private Object readNull() throws ParseException {
        if (this.length - this.pos >= 3 && this.src.charAt(this.pos) == 'u' && this.src.charAt(this.pos + 1) == 'l' && this.src.charAt(this.pos + 2) == 'l') {
            this.pos += 3;
            return null;
        }
        throw new ParseException("Unexpected token: n");
    }

    private void consumeWhitespace() {
        while (this.pos < this.length) {
            switch (this.src.charAt(this.pos)) {
                case '\t':
                case '\n':
                case '\r':
                case ' ':
                    this.pos++;
                default:
                    return;
            }
        }
    }

    private void consume(char c) throws ParseException {
        consumeWhitespace();
        if (this.pos >= this.length) {
            throw new ParseException("Expected " + c + " but reached end of stream");
        }
        String str = this.src;
        int i = this.pos;
        this.pos = i + 1;
        char charAt = str.charAt(i);
        if (charAt != c) {
            throw new ParseException("Expected " + c + " found " + charAt);
        }
    }
}
