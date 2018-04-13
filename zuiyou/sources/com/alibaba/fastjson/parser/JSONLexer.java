package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class JSONLexer {
    public static final char[] CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    public static final int END = 4;
    public static final char EOI = '\u001a';
    static final int[] IA = new int[256];
    public static final int NOT_MATCH = -1;
    public static final int NOT_MATCH_NAME = -2;
    public static final int UNKNOWN = 0;
    private static boolean V6 = false;
    public static final int VALUE = 3;
    protected static final int[] digits = new int[103];
    public static final boolean[] firstIdentifierFlags = new boolean[256];
    public static final boolean[] identifierFlags = new boolean[256];
    private static final ThreadLocal<char[]> sbufLocal = new ThreadLocal();
    protected int bp;
    public Calendar calendar;
    protected char ch;
    public boolean disableCircularReferenceDetect;
    protected int eofPos;
    protected boolean exp;
    public int features;
    protected long fieldHash;
    protected boolean hasSpecial;
    protected boolean isDouble;
    protected final int len;
    public Locale locale;
    public int matchStat;
    protected int np;
    protected int pos;
    protected char[] sbuf;
    protected int sp;
    protected String stringDefaultValue;
    protected final String text;
    public TimeZone timeZone;
    protected int token;

    static {
        boolean z;
        int i = 0;
        int i2 = -1;
        try {
            i2 = Class.forName("android.os.Build$VERSION").getField("SDK_INT").getInt(null);
        } catch (Exception e) {
        }
        if (i2 >= 23) {
            z = true;
        } else {
            z = false;
        }
        V6 = z;
        for (i2 = 48; i2 <= 57; i2++) {
            digits[i2] = i2 - 48;
        }
        for (i2 = 97; i2 <= 102; i2++) {
            digits[i2] = (i2 - 97) + 10;
        }
        for (i2 = 65; i2 <= 70; i2++) {
            digits[i2] = (i2 - 65) + 10;
        }
        Arrays.fill(IA, -1);
        int length = CA.length;
        for (i2 = 0; i2 < length; i2++) {
            IA[CA[i2]] = i2;
        }
        IA[61] = 0;
        i2 = 0;
        while (i2 < firstIdentifierFlags.length) {
            if (i2 >= 65 && i2 <= 90) {
                firstIdentifierFlags[i2] = true;
            } else if (i2 >= 97 && i2 <= 122) {
                firstIdentifierFlags[i2] = true;
            } else if (i2 == 95) {
                firstIdentifierFlags[i2] = true;
            }
            i2 = (char) (i2 + 1);
        }
        while (i < identifierFlags.length) {
            if (i >= 65 && i <= 90) {
                identifierFlags[i] = true;
            } else if (i >= 97 && i <= 122) {
                identifierFlags[i] = true;
            } else if (i == 95) {
                identifierFlags[i] = true;
            } else if (i >= 48 && i <= 57) {
                identifierFlags[i] = true;
            }
            i = (char) (i + 1);
        }
    }

    public JSONLexer(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONLexer(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONLexer(char[] cArr, int i, int i2) {
        this(new String(cArr, 0, i), i2);
    }

    public JSONLexer(String str, int i) {
        char c;
        String str2;
        boolean z;
        this.features = JSON.DEFAULT_PARSER_FEATURE;
        this.exp = false;
        this.isDouble = false;
        this.timeZone = JSON.defaultTimeZone;
        this.locale = JSON.defaultLocale;
        this.calendar = null;
        this.matchStat = 0;
        this.sbuf = (char[]) sbufLocal.get();
        if (this.sbuf == null) {
            this.sbuf = new char[512];
        }
        this.features = i;
        this.text = str;
        this.len = this.text.length();
        this.bp = -1;
        int i2 = this.bp + 1;
        this.bp = i2;
        if (i2 >= this.len) {
            c = EOI;
        } else {
            c = this.text.charAt(i2);
        }
        this.ch = c;
        if (this.ch == '﻿') {
            next();
        }
        if ((Feature.InitStringFieldAsEmpty.mask & i) != 0) {
            str2 = "";
        } else {
            str2 = null;
        }
        this.stringDefaultValue = str2;
        if ((Feature.DisableCircularReferenceDetect.mask & i) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.disableCircularReferenceDetect = z;
    }

    public final int token() {
        return this.token;
    }

    public void close() {
        if (this.sbuf.length <= 8196) {
            sbufLocal.set(this.sbuf);
        }
        this.sbuf = null;
    }

    public char next() {
        char c;
        int i = this.bp + 1;
        this.bp = i;
        if (i >= this.len) {
            c = EOI;
        } else {
            c = this.text.charAt(i);
        }
        this.ch = c;
        return c;
    }

    public final void config(Feature feature, boolean z) {
        if (z) {
            this.features |= feature.mask;
        } else {
            this.features &= feature.mask ^ -1;
        }
        if (feature == Feature.InitStringFieldAsEmpty) {
            this.stringDefaultValue = z ? "" : null;
        }
        this.disableCircularReferenceDetect = (this.features & Feature.DisableCircularReferenceDetect.mask) != 0;
    }

    public final boolean isEnabled(Feature feature) {
        return (this.features & feature.mask) != 0;
    }

    public final void nextTokenWithChar(char c) {
        char c2;
        this.sp = 0;
        while (this.ch != c) {
            if (this.ch == ' ' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == '\f' || this.ch == '\b') {
                next();
            } else {
                throw new JSONException("not match " + c + " - " + this.ch);
            }
        }
        int i = this.bp + 1;
        this.bp = i;
        if (i >= this.len) {
            c2 = EOI;
        } else {
            c2 = this.text.charAt(i);
        }
        this.ch = c2;
        nextToken();
    }

    public final String numberString() {
        char charAt = this.text.charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i--;
        }
        return subString(this.np, i);
    }

    protected char charAt(int i) {
        if (i >= this.len) {
            return EOI;
        }
        return this.text.charAt(i);
    }

    public final void nextToken() {
        char c = EOI;
        this.sp = 0;
        while (true) {
            this.pos = this.bp;
            if (this.ch == '/') {
                skipComment();
            } else if (this.ch == '\"') {
                scanString();
                return;
            } else if ((this.ch >= '0' && this.ch <= '9') || this.ch == '-') {
                scanNumber();
                return;
            } else if (this.ch == ',') {
                next();
                this.token = 16;
                return;
            } else {
                int i;
                switch (this.ch) {
                    case '\b':
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                        next();
                        break;
                    case '\'':
                        scanString();
                        return;
                    case '(':
                        next();
                        this.token = 10;
                        return;
                    case ')':
                        next();
                        this.token = 11;
                        return;
                    case ':':
                        next();
                        this.token = 17;
                        return;
                    case 'S':
                    case 'T':
                    case 'u':
                        scanIdent();
                        return;
                    case '[':
                        i = this.bp + 1;
                        this.bp = i;
                        if (i < this.len) {
                            c = this.text.charAt(i);
                        }
                        this.ch = c;
                        this.token = 14;
                        return;
                    case ']':
                        next();
                        this.token = 15;
                        return;
                    case 'f':
                        if (this.text.startsWith("false", this.bp)) {
                            this.bp += 5;
                            this.ch = charAt(this.bp);
                            if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == EOI || this.ch == '\f' || this.ch == '\b' || this.ch == ':') {
                                this.token = 7;
                                return;
                            }
                        }
                        throw new JSONException("scan false error");
                    case 'n':
                        i = 0;
                        if (this.text.startsWith("null", this.bp)) {
                            this.bp += 4;
                            i = 8;
                        } else if (this.text.startsWith("new", this.bp)) {
                            this.bp += 3;
                            i = 9;
                        }
                        if (i != 0) {
                            this.ch = charAt(this.bp);
                            if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == EOI || this.ch == '\f' || this.ch == '\b') {
                                this.token = i;
                                return;
                            }
                        }
                        throw new JSONException("scan null/new error");
                    case 't':
                        if (this.text.startsWith("true", this.bp)) {
                            this.bp += 4;
                            this.ch = charAt(this.bp);
                            if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == EOI || this.ch == '\f' || this.ch == '\b' || this.ch == ':') {
                                this.token = 6;
                                return;
                            }
                        }
                        throw new JSONException("scan true error");
                    case '{':
                        i = this.bp + 1;
                        this.bp = i;
                        if (i < this.len) {
                            c = this.text.charAt(i);
                        }
                        this.ch = c;
                        this.token = 12;
                        return;
                    case '}':
                        i = this.bp + 1;
                        this.bp = i;
                        if (i < this.len) {
                            c = this.text.charAt(i);
                        }
                        this.ch = c;
                        this.token = 13;
                        return;
                    default:
                        Object obj = (this.bp == this.len || (this.ch == EOI && this.bp + 1 == this.len)) ? 1 : null;
                        if (obj != null) {
                            if (this.token == 20) {
                                throw new JSONException("EOF error");
                            }
                            this.token = 20;
                            int i2 = this.eofPos;
                            this.bp = i2;
                            this.pos = i2;
                            return;
                        } else if (this.ch <= '\u001f' || this.ch == '') {
                            next();
                            break;
                        } else {
                            this.token = 1;
                            next();
                            return;
                        }
                }
            }
        }
    }

    public final void nextToken(int i) {
        char c = EOI;
        this.sp = 0;
        while (true) {
            int i2;
            switch (i) {
                case 2:
                    if (this.ch >= '0' && this.ch <= '9') {
                        this.pos = this.bp;
                        scanNumber();
                        return;
                    } else if (this.ch == '\"') {
                        this.pos = this.bp;
                        scanString();
                        return;
                    } else if (this.ch == '[') {
                        this.token = 14;
                        next();
                        return;
                    } else if (this.ch == '{') {
                        this.token = 12;
                        next();
                        return;
                    }
                    break;
                case 4:
                    if (this.ch == '\"') {
                        this.pos = this.bp;
                        scanString();
                        return;
                    } else if (this.ch >= '0' && this.ch <= '9') {
                        this.pos = this.bp;
                        scanNumber();
                        return;
                    } else if (this.ch == '{') {
                        this.token = 12;
                        i2 = this.bp + 1;
                        this.bp = i2;
                        if (i2 < this.len) {
                            c = this.text.charAt(i2);
                        }
                        this.ch = c;
                        return;
                    }
                    break;
                case 12:
                    if (this.ch == '{') {
                        this.token = 12;
                        i2 = this.bp + 1;
                        this.bp = i2;
                        if (i2 < this.len) {
                            c = this.text.charAt(i2);
                        }
                        this.ch = c;
                        return;
                    } else if (this.ch == '[') {
                        this.token = 14;
                        i2 = this.bp + 1;
                        this.bp = i2;
                        if (i2 < this.len) {
                            c = this.text.charAt(i2);
                        }
                        this.ch = c;
                        return;
                    }
                    break;
                case 14:
                    if (this.ch == '[') {
                        this.token = 14;
                        next();
                        return;
                    } else if (this.ch == '{') {
                        this.token = 12;
                        next();
                        return;
                    }
                    break;
                case 15:
                    if (this.ch == ']') {
                        this.token = 15;
                        next();
                        return;
                    }
                    break;
                case 16:
                    if (this.ch == ',') {
                        this.token = 16;
                        i2 = this.bp + 1;
                        this.bp = i2;
                        if (i2 < this.len) {
                            c = this.text.charAt(i2);
                        }
                        this.ch = c;
                        return;
                    } else if (this.ch == '}') {
                        this.token = 13;
                        i2 = this.bp + 1;
                        this.bp = i2;
                        if (i2 < this.len) {
                            c = this.text.charAt(i2);
                        }
                        this.ch = c;
                        return;
                    } else if (this.ch == ']') {
                        this.token = 15;
                        i2 = this.bp + 1;
                        this.bp = i2;
                        if (i2 < this.len) {
                            c = this.text.charAt(i2);
                        }
                        this.ch = c;
                        return;
                    } else if (this.ch == EOI) {
                        this.token = 20;
                        return;
                    }
                    break;
                case 18:
                    nextIdent();
                    return;
                case 20:
                    break;
            }
            if (this.ch == EOI) {
                this.token = 20;
                return;
            }
            if (this.ch == ' ' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == '\f' || this.ch == '\b') {
                next();
            } else {
                nextToken();
                return;
            }
        }
    }

    public final void nextIdent() {
        while (true) {
            Object obj = (this.ch > ' ' || !(this.ch == ' ' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == '\f' || this.ch == '\b')) ? null : 1;
            if (obj == null) {
                break;
            }
            next();
        }
        if (this.ch == '_' || Character.isLetter(this.ch)) {
            scanIdent();
        } else {
            nextToken();
        }
    }

    public final Number integerValue() throws NumberFormatException {
        char c;
        Object obj;
        long j;
        long j2 = 0;
        int i = this.np;
        int i2 = this.sp + this.np;
        int i3 = 32;
        int i4 = i2 - 1;
        if (i4 >= this.len) {
            c = EOI;
        } else {
            c = this.text.charAt(i4);
        }
        switch (c) {
            case 'B':
                i2--;
                i3 = 66;
                break;
            case 'L':
                i2--;
                i3 = 76;
                break;
            case 'S':
                i2--;
                i3 = 83;
                break;
        }
        if (this.np >= this.len) {
            c = EOI;
        } else {
            c = this.text.charAt(this.np);
        }
        int i5;
        if (c == '-') {
            obj = 1;
            i5 = i + 1;
            j = Long.MIN_VALUE;
            i4 = i5;
        } else {
            obj = null;
            i5 = i;
            j = -9223372036854775807L;
            i4 = i5;
        }
        if (i4 < i2) {
            int i6 = i4 + 1;
            if (i4 >= this.len) {
                i4 = 26;
            } else {
                i4 = this.text.charAt(i4);
            }
            j2 = (long) (-(i4 - 48));
            i4 = i6;
        }
        while (i4 < i2) {
            i6 = i4 + 1;
            if (i4 >= this.len) {
                i4 = 26;
            } else {
                i4 = this.text.charAt(i4);
            }
            i4 -= 48;
            if (j2 < -922337203685477580L) {
                return new BigInteger(numberString());
            }
            j2 *= 10;
            if (j2 < ((long) i4) + j) {
                return new BigInteger(numberString());
            }
            j2 -= (long) i4;
            i4 = i6;
        }
        if (obj == null) {
            long j3 = -j2;
            if (j3 > 2147483647L || i3 == 76) {
                return Long.valueOf(j3);
            }
            if (i3 == 83) {
                return Short.valueOf((short) ((int) j3));
            }
            if (i3 == 66) {
                return Byte.valueOf((byte) ((int) j3));
            }
            return Integer.valueOf((int) j3);
        } else if (i4 <= this.np + 1) {
            throw new NumberFormatException(numberString());
        } else if (j2 < -2147483648L || i3 == 76) {
            return Long.valueOf(j2);
        } else {
            if (i3 == 83) {
                return Short.valueOf((short) ((int) j2));
            }
            if (i3 == 66) {
                return Byte.valueOf((byte) ((int) j2));
            }
            return Integer.valueOf((int) j2);
        }
    }

    public final String scanSymbol(SymbolTable symbolTable) {
        while (true) {
            if (this.ch != ' ' && this.ch != '\n' && this.ch != '\r' && this.ch != '\t' && this.ch != '\f' && this.ch != '\b') {
                break;
            }
            next();
        }
        if (this.ch == '\"') {
            return scanSymbol(symbolTable, '\"');
        }
        if (this.ch == '\'') {
            return scanSymbol(symbolTable, '\'');
        }
        if (this.ch == '}') {
            next();
            this.token = 13;
            return null;
        } else if (this.ch == ',') {
            next();
            this.token = 16;
            return null;
        } else if (this.ch != EOI) {
            return scanSymbolUnQuoted(symbolTable);
        } else {
            this.token = 20;
            return null;
        }
    }

    public String scanSymbol(SymbolTable symbolTable, char c) {
        int i = this.bp + 1;
        int indexOf = this.text.indexOf(c, i);
        if (indexOf == -1) {
            throw new JSONException("unclosed str, " + info());
        }
        String readString;
        char c2;
        i = indexOf - i;
        char[] sub_chars = sub_chars(this.bp + 1, i);
        int i2 = i;
        int i3 = indexOf;
        indexOf = 0;
        while (i2 > 0 && sub_chars[i2 - 1] == '\\') {
            i = i2 - 2;
            int i4 = 1;
            while (i >= 0 && sub_chars[i] == '\\') {
                i4++;
                i--;
            }
            if (i4 % 2 == 0) {
                break;
            }
            char[] cArr;
            i4 = this.text.indexOf(c, i3 + 1);
            i = i2 + (i4 - i3);
            if (i >= sub_chars.length) {
                indexOf = (sub_chars.length * 3) / 2;
                if (indexOf < i) {
                    indexOf = i;
                }
                cArr = new char[indexOf];
                System.arraycopy(sub_chars, 0, cArr, 0, sub_chars.length);
            } else {
                cArr = sub_chars;
            }
            this.text.getChars(i3, i4, cArr, i2);
            sub_chars = cArr;
            i2 = i;
            i3 = i4;
            indexOf = 1;
        }
        if (indexOf == 0) {
            i4 = 0;
            for (i = 0; i < i2; i++) {
                char c3 = sub_chars[i];
                i4 = (i4 * 31) + c3;
                if (c3 == '\\') {
                    indexOf = 1;
                }
            }
            readString = indexOf != 0 ? readString(sub_chars, i2) : i2 < 20 ? symbolTable.addSymbol(sub_chars, 0, i2, i4) : new String(sub_chars, 0, i2);
        } else {
            readString = readString(sub_chars, i2);
        }
        this.bp = i3 + 1;
        i = this.bp;
        if (i >= this.len) {
            c2 = EOI;
        } else {
            c2 = this.text.charAt(i);
        }
        this.ch = c2;
        return readString;
    }

    private static String readString(char[] cArr, int i) {
        char[] cArr2 = new char[i];
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4;
            char c = cArr[i2];
            if (c != '\\') {
                i4 = i3 + 1;
                cArr2[i3] = c;
            } else {
                i2++;
                switch (cArr[i2]) {
                    case '\"':
                        i4 = i3 + 1;
                        cArr2[i3] = '\"';
                        break;
                    case '\'':
                        i4 = i3 + 1;
                        cArr2[i3] = '\'';
                        break;
                    case '/':
                        i4 = i3 + 1;
                        cArr2[i3] = '/';
                        break;
                    case '0':
                        i4 = i3 + 1;
                        cArr2[i3] = '\u0000';
                        break;
                    case '1':
                        i4 = i3 + 1;
                        cArr2[i3] = '\u0001';
                        break;
                    case '2':
                        i4 = i3 + 1;
                        cArr2[i3] = '\u0002';
                        break;
                    case '3':
                        i4 = i3 + 1;
                        cArr2[i3] = '\u0003';
                        break;
                    case '4':
                        i4 = i3 + 1;
                        cArr2[i3] = '\u0004';
                        break;
                    case '5':
                        i4 = i3 + 1;
                        cArr2[i3] = '\u0005';
                        break;
                    case '6':
                        i4 = i3 + 1;
                        cArr2[i3] = '\u0006';
                        break;
                    case '7':
                        i4 = i3 + 1;
                        cArr2[i3] = '\u0007';
                        break;
                    case 'F':
                    case 'f':
                        i4 = i3 + 1;
                        cArr2[i3] = '\f';
                        break;
                    case '\\':
                        i4 = i3 + 1;
                        cArr2[i3] = '\\';
                        break;
                    case 'b':
                        i4 = i3 + 1;
                        cArr2[i3] = '\b';
                        break;
                    case 'n':
                        i4 = i3 + 1;
                        cArr2[i3] = '\n';
                        break;
                    case 'r':
                        i4 = i3 + 1;
                        cArr2[i3] = '\r';
                        break;
                    case 't':
                        i4 = i3 + 1;
                        cArr2[i3] = '\t';
                        break;
                    case 'u':
                        i4 = i3 + 1;
                        r6 = new char[4];
                        i2++;
                        r6[0] = cArr[i2];
                        i2++;
                        r6[1] = cArr[i2];
                        i2++;
                        r6[2] = cArr[i2];
                        i2++;
                        r6[3] = cArr[i2];
                        cArr2[i3] = (char) Integer.parseInt(new String(r6), 16);
                        break;
                    case 'v':
                        i4 = i3 + 1;
                        cArr2[i3] = '\u000b';
                        break;
                    case 'x':
                        i4 = i3 + 1;
                        i2++;
                        i2++;
                        cArr2[i3] = (char) ((digits[cArr[i2]] * 16) + digits[cArr[i2]]);
                        break;
                    default:
                        throw new JSONException("unclosed.str.lit");
                }
            }
            i2++;
            i3 = i4;
        }
        return new String(cArr2, 0, i3);
    }

    public String info() {
        String str;
        StringBuilder append = new StringBuilder().append("pos ").append(this.bp).append(", json : ");
        if (this.len < 65536) {
            str = this.text;
        } else {
            str = this.text.substring(0, 65536);
        }
        return append.append(str).toString();
    }

    protected void skipComment() {
        next();
        if (this.ch == '/') {
            do {
                next();
            } while (this.ch != '\n');
            next();
        } else if (this.ch == '*') {
            next();
            while (this.ch != EOI) {
                if (this.ch == '*') {
                    next();
                    if (this.ch == '/') {
                        next();
                        return;
                    }
                } else {
                    next();
                }
            }
        } else {
            throw new JSONException("invalid comment");
        }
    }

    public final String scanSymbolUnQuoted(SymbolTable symbolTable) {
        int i = this.ch;
        int i2 = (this.ch >= firstIdentifierFlags.length || firstIdentifierFlags[i]) ? 1 : 0;
        if (i2 == 0) {
            throw new JSONException("illegal identifier : " + this.ch + ", " + info());
        }
        this.np = this.bp;
        this.sp = 1;
        while (true) {
            char next = next();
            if (next < identifierFlags.length && !identifierFlags[next]) {
                break;
            }
            i = (i * 31) + next;
            this.sp++;
        }
        this.ch = charAt(this.bp);
        this.token = 18;
        if (this.sp == 4 && this.text.startsWith("null", this.np)) {
            return null;
        }
        return symbolTable.addSymbol(this.text, this.np, this.sp, i);
    }

    public final void scanString() {
        char c = this.ch;
        int i = this.bp + 1;
        int indexOf = this.text.indexOf(c, i);
        if (indexOf == -1) {
            throw new JSONException("unclosed str, " + info());
        }
        char c2;
        i = indexOf - i;
        Object sub_chars = sub_chars(this.bp + 1, i);
        int i2 = i;
        int i3 = indexOf;
        boolean z = false;
        while (i2 > 0 && sub_chars[i2 - 1] == '\\') {
            i = i2 - 2;
            int i4 = 1;
            while (i >= 0 && sub_chars[i] == '\\') {
                i4++;
                i--;
            }
            if (i4 % 2 == 0) {
                break;
            }
            Object obj;
            i4 = this.text.indexOf(c, i3 + 1);
            i = i2 + (i4 - i3);
            if (i >= sub_chars.length) {
                indexOf = (sub_chars.length * 3) / 2;
                if (indexOf < i) {
                    indexOf = i;
                }
                obj = new char[indexOf];
                System.arraycopy(sub_chars, 0, obj, 0, sub_chars.length);
            } else {
                obj = sub_chars;
            }
            this.text.getChars(i3, i4, obj, i2);
            sub_chars = obj;
            i2 = i;
            i3 = i4;
            z = true;
        }
        if (!z) {
            for (i = 0; i < i2; i++) {
                if (sub_chars[i] == '\\') {
                    z = true;
                }
            }
        }
        this.sbuf = sub_chars;
        this.sp = i2;
        this.np = this.bp;
        this.hasSpecial = z;
        this.bp = i3 + 1;
        indexOf = this.bp;
        if (indexOf >= this.len) {
            c2 = EOI;
        } else {
            c2 = this.text.charAt(indexOf);
        }
        this.ch = c2;
        this.token = 4;
    }

    public String scanStringValue(char c) {
        int i = this.bp + 1;
        int indexOf = this.text.indexOf(c, i);
        if (indexOf == -1) {
            throw new JSONException("unclosed str, " + info());
        }
        String substring;
        char c2;
        if (V6) {
            substring = this.text.substring(i, indexOf);
        } else {
            int i2 = indexOf - i;
            substring = new String(sub_chars(this.bp + 1, i2), 0, i2);
        }
        if (substring.indexOf(92) != -1) {
            int i3 = indexOf;
            while (true) {
                indexOf = i3 - 1;
                i2 = 0;
                while (indexOf >= 0 && this.text.charAt(indexOf) == '\\') {
                    i2++;
                    indexOf--;
                }
                if (i2 % 2 == 0) {
                    break;
                }
                i3 = this.text.indexOf(c, i3 + 1);
            }
            indexOf = i3 - i;
            String readString = readString(sub_chars(this.bp + 1, indexOf), indexOf);
            indexOf = i3;
            substring = readString;
        }
        this.bp = indexOf + 1;
        indexOf = this.bp;
        if (indexOf >= this.len) {
            c2 = EOI;
        } else {
            c2 = this.text.charAt(indexOf);
        }
        this.ch = c2;
        return substring;
    }

    public final int intValue() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        int i6 = this.np;
        int i7 = this.np + this.sp;
        if ((this.np >= this.len ? EOI : this.text.charAt(this.np)) == '-') {
            i = 1;
            i2 = Integer.MIN_VALUE;
            i3 = i6 + 1;
        } else {
            i2 = -2147483647;
            i = 0;
            i3 = i6;
        }
        if (i3 < i7) {
            i6 = i3 + 1;
            if (i3 >= this.len) {
                i5 = 26;
            } else {
                i5 = this.text.charAt(i3);
            }
            i5 = -(i5 - 48);
        } else {
            i6 = i3;
        }
        while (i6 < i7) {
            i3 = i6 + 1;
            if (i6 >= this.len) {
                i6 = 26;
            } else {
                i6 = this.text.charAt(i6);
            }
            if (i6 == 76 || i6 == 83) {
                i4 = i3;
                break;
            } else if (i6 == 66) {
                i4 = i3;
                break;
            } else {
                i6 -= 48;
                if (i5 < -214748364) {
                    throw new NumberFormatException(numberString());
                }
                i5 *= 10;
                if (i5 < i2 + i6) {
                    throw new NumberFormatException(numberString());
                }
                i5 -= i6;
                i6 = i3;
            }
        }
        i4 = i6;
        if (i == 0) {
            return -i5;
        }
        if (i4 > this.np + 1) {
            return i5;
        }
        throw new NumberFormatException(numberString());
    }

    public byte[] bytesValue() {
        return decodeFast(this.text, this.np + 1, this.sp);
    }

    private void scanIdent() {
        this.np = this.bp - 1;
        this.hasSpecial = false;
        do {
            this.sp++;
            next();
        } while (Character.isLetterOrDigit(this.ch));
        String stringVal = stringVal();
        if (stringVal.equals("null")) {
            this.token = 8;
        } else if (stringVal.equals("true")) {
            this.token = 6;
        } else if (stringVal.equals("false")) {
            this.token = 7;
        } else if (stringVal.equals("new")) {
            this.token = 9;
        } else if (stringVal.equals("undefined")) {
            this.token = 23;
        } else if (stringVal.equals("Set")) {
            this.token = 21;
        } else if (stringVal.equals("TreeSet")) {
            this.token = 22;
        } else {
            this.token = 18;
        }
    }

    public final String stringVal() {
        if (this.hasSpecial) {
            return readString(this.sbuf, this.sp);
        }
        return subString(this.np + 1, this.sp);
    }

    private final String subString(int i, int i2) {
        if (i2 < this.sbuf.length) {
            this.text.getChars(i, i + i2, this.sbuf, 0);
            return new String(this.sbuf, 0, i2);
        }
        char[] cArr = new char[i2];
        this.text.getChars(i, i + i2, cArr, 0);
        return new String(cArr);
    }

    final char[] sub_chars(int i, int i2) {
        if (i2 < this.sbuf.length) {
            this.text.getChars(i, i + i2, this.sbuf, 0);
            return this.sbuf;
        }
        char[] cArr = new char[i2];
        this.sbuf = cArr;
        this.text.getChars(i, i + i2, cArr, 0);
        return cArr;
    }

    public final boolean isBlankInput() {
        int i = 0;
        while (true) {
            char charAt = charAt(i);
            if (charAt == EOI) {
                return true;
            }
            boolean z;
            if (charAt > ' ' || !(charAt == ' ' || charAt == '\n' || charAt == '\r' || charAt == '\t' || charAt == '\f' || charAt == '\b')) {
                z = false;
            } else {
                z = true;
            }
            if (!z) {
                return false;
            }
            i++;
        }
    }

    final void skipWhitespace() {
        while (this.ch <= '/') {
            if (this.ch == ' ' || this.ch == '\r' || this.ch == '\n' || this.ch == '\t' || this.ch == '\f' || this.ch == '\b') {
                next();
            } else if (this.ch == '/') {
                skipComment();
            } else {
                return;
            }
        }
    }

    public final void scanNumber() {
        this.np = this.bp;
        this.exp = false;
        if (this.ch == '-') {
            this.sp++;
            int i = this.bp + 1;
            this.bp = i;
            this.ch = i >= this.len ? EOI : this.text.charAt(i);
        }
        while (this.ch >= '0' && this.ch <= '9') {
            this.sp++;
            i = this.bp + 1;
            this.bp = i;
            this.ch = i >= this.len ? EOI : this.text.charAt(i);
        }
        this.isDouble = false;
        if (this.ch == '.') {
            this.sp++;
            i = this.bp + 1;
            this.bp = i;
            this.ch = i >= this.len ? EOI : this.text.charAt(i);
            this.isDouble = true;
            while (this.ch >= '0' && this.ch <= '9') {
                this.sp++;
                i = this.bp + 1;
                this.bp = i;
                this.ch = i >= this.len ? EOI : this.text.charAt(i);
            }
        }
        if (this.ch == 'L') {
            this.sp++;
            next();
        } else if (this.ch == 'S') {
            this.sp++;
            next();
        } else if (this.ch == 'B') {
            this.sp++;
            next();
        } else if (this.ch == 'F') {
            this.sp++;
            next();
            this.isDouble = true;
        } else if (this.ch == 'D') {
            this.sp++;
            next();
            this.isDouble = true;
        } else if (this.ch == 'e' || this.ch == 'E') {
            this.sp++;
            i = this.bp + 1;
            this.bp = i;
            this.ch = i >= this.len ? EOI : this.text.charAt(i);
            if (this.ch == '+' || this.ch == '-') {
                this.sp++;
                i = this.bp + 1;
                this.bp = i;
                this.ch = i >= this.len ? EOI : this.text.charAt(i);
            }
            while (this.ch >= '0' && this.ch <= '9') {
                this.sp++;
                i = this.bp + 1;
                this.bp = i;
                this.ch = i >= this.len ? EOI : this.text.charAt(i);
            }
            if (this.ch == 'D' || this.ch == 'F') {
                this.sp++;
                next();
            }
            this.exp = true;
            this.isDouble = true;
        }
        if (this.isDouble) {
            this.token = 3;
        } else {
            this.token = 2;
        }
    }

    public boolean scanBoolean() {
        boolean z = false;
        int i = 1;
        if (this.text.startsWith("false", this.bp)) {
            i = 5;
        } else if (this.text.startsWith("true", this.bp)) {
            i = 4;
            z = true;
        } else if (this.ch == '1') {
            z = true;
        } else if (this.ch != '0') {
            this.matchStat = -1;
            return z;
        }
        this.bp = i + this.bp;
        this.ch = charAt(this.bp);
        return z;
    }

    public final Number scanNumberValue() {
        int i;
        Object obj;
        long j;
        char c;
        Number valueOf;
        int i2;
        Object obj2;
        int i3 = this.bp;
        this.np = 0;
        if (this.ch == '-') {
            char c2;
            this.np++;
            i = this.bp + 1;
            this.bp = i;
            if (i >= this.len) {
                c2 = EOI;
            } else {
                c2 = this.text.charAt(i);
            }
            this.ch = c2;
            obj = 1;
            j = Long.MIN_VALUE;
        } else {
            obj = null;
            j = -9223372036854775807L;
        }
        long j2 = 0;
        Object obj3 = null;
        while (this.ch >= '0' && this.ch <= '9') {
            int i4 = this.ch - 48;
            if (j2 < -922337203685477580L) {
                obj3 = 1;
            }
            j2 *= 10;
            if (j2 < ((long) i4) + j) {
                obj3 = 1;
            }
            j2 -= (long) i4;
            this.np++;
            i4 = this.bp + 1;
            this.bp = i4;
            if (i4 >= this.len) {
                c = EOI;
            } else {
                c = this.text.charAt(i4);
            }
            this.ch = c;
        }
        if (obj == null) {
            j2 = -j2;
        }
        if (this.ch == 'L') {
            this.np++;
            next();
            valueOf = Long.valueOf(j2);
        } else if (this.ch == 'S') {
            this.np++;
            next();
            valueOf = Short.valueOf((short) ((int) j2));
        } else if (this.ch == 'B') {
            this.np++;
            next();
            valueOf = Byte.valueOf((byte) ((int) j2));
        } else if (this.ch == 'F') {
            this.np++;
            next();
            valueOf = Float.valueOf((float) j2);
        } else if (this.ch == 'D') {
            this.np++;
            next();
            valueOf = Double.valueOf((double) j2);
        } else {
            valueOf = null;
        }
        Object obj4 = null;
        if (this.ch == '.') {
            char c3;
            this.np++;
            i2 = this.bp + 1;
            this.bp = i2;
            if (i2 >= this.len) {
                c3 = EOI;
            } else {
                c3 = this.text.charAt(i2);
            }
            this.ch = c3;
            while (this.ch >= '0' && this.ch <= '9') {
                this.np++;
                i2 = this.bp + 1;
                this.bp = i2;
                if (i2 >= this.len) {
                    c3 = EOI;
                } else {
                    c3 = this.text.charAt(i2);
                }
                this.ch = c3;
            }
            obj4 = 1;
        }
        char c4 = '\u0000';
        if (this.ch == 'e' || this.ch == 'E') {
            this.np++;
            i4 = this.bp + 1;
            this.bp = i4;
            if (i4 >= this.len) {
                c = EOI;
            } else {
                c = this.text.charAt(i4);
            }
            this.ch = c;
            if (this.ch == '+' || this.ch == '-') {
                this.np++;
                i4 = this.bp + 1;
                this.bp = i4;
                if (i4 >= this.len) {
                    c = EOI;
                } else {
                    c = this.text.charAt(i4);
                }
                this.ch = c;
            }
            while (this.ch >= '0' && this.ch <= '9') {
                this.np++;
                i4 = this.bp + 1;
                this.bp = i4;
                if (i4 >= this.len) {
                    c = EOI;
                } else {
                    c = this.text.charAt(i4);
                }
                this.ch = c;
            }
            if (this.ch == 'D' || this.ch == 'F') {
                this.np++;
                c = this.ch;
                next();
            } else {
                c = '\u0000';
            }
            char c5 = c;
            obj2 = 1;
            c4 = c5;
        } else {
            obj2 = null;
        }
        if (obj4 == null && obj2 == null) {
            if (obj3 != null) {
                char[] cArr = new char[(this.bp - i3)];
                this.text.getChars(i3, this.bp, cArr, 0);
                valueOf = new BigInteger(new String(cArr));
            }
            if (valueOf != null) {
                return valueOf;
            }
            if (j2 <= -2147483648L || j2 >= 2147483647L) {
                return Long.valueOf(j2);
            }
            return Integer.valueOf((int) j2);
        }
        char[] cArr2;
        i = this.bp - i3;
        if (c4 != '\u0000') {
            i--;
        }
        if (i < this.sbuf.length) {
            this.text.getChars(i3, i3 + i, this.sbuf, 0);
            cArr2 = this.sbuf;
        } else {
            char[] cArr3 = new char[i];
            this.text.getChars(i3, i3 + i, cArr3, 0);
            cArr2 = cArr3;
        }
        if (obj2 == null && (this.features & Feature.UseBigDecimal.mask) != 0) {
            return new BigDecimal(cArr2, 0, i);
        }
        if (i > 9 || obj2 != null) {
            String str = new String(cArr2, 0, i);
            if (c4 == 'F') {
                return Float.valueOf(str);
            }
            return Double.valueOf(Double.parseDouble(str));
        }
        int i5 = 1;
        try {
            i2 = cArr2[0];
            if (i2 == 45 || i2 == 43) {
                i2 = cArr2[1];
                i5 = 2;
            }
            int i6 = i2 - 48;
            i2 = 0;
            int i7 = i6;
            i5 = i7;
            for (i6 = i5; i6 < i; i6++) {
                c = cArr2[i6];
                if (c == '.') {
                    i2 = 1;
                } else {
                    i5 = (i5 * 10) + (c - 48);
                    if (i2 != 0) {
                        i2 *= 10;
                    }
                }
            }
            if (c4 == 'F') {
                float f = ((float) i5) / ((float) i2);
                if (obj != null) {
                    f = -f;
                }
                return Float.valueOf(f);
            }
            double d = ((double) i5) / ((double) i2);
            if (obj != null) {
                d = -d;
            }
            return Double.valueOf(d);
        } catch (Throwable e) {
            throw new JSONException(e.getMessage() + ", " + info(), e);
        }
    }

    public final long scanLongValue() {
        long j;
        int i;
        int i2 = 0;
        this.np = 0;
        if (this.ch == '-') {
            i2 = 1;
            j = Long.MIN_VALUE;
            this.np++;
            i = this.bp + 1;
            this.bp = i;
            if (i >= this.len) {
                throw new JSONException("syntax error, " + info());
            }
            this.ch = this.text.charAt(i);
        } else {
            j = -9223372036854775807L;
        }
        long j2 = 0;
        while (this.ch >= '0' && this.ch <= '9') {
            i = this.ch - 48;
            if (j2 < -922337203685477580L) {
                throw new JSONException("error long value, " + j2 + ", " + info());
            }
            j2 *= 10;
            if (j2 < ((long) i) + j) {
                throw new JSONException("error long value, " + j2 + ", " + info());
            }
            char c;
            j2 -= (long) i;
            this.np++;
            i = this.bp + 1;
            this.bp = i;
            if (i >= this.len) {
                c = EOI;
            } else {
                c = this.text.charAt(i);
            }
            this.ch = c;
        }
        if (i2 == 0) {
            return -j2;
        }
        return j2;
    }

    public final long longValue() throws NumberFormatException {
        Object obj;
        long j;
        int i;
        int i2;
        long j2 = 0;
        int i3 = this.np;
        int i4 = this.np + this.sp;
        int i5;
        if (charAt(this.np) == '-') {
            obj = 1;
            i5 = i3 + 1;
            j = Long.MIN_VALUE;
            i = i5;
        } else {
            obj = null;
            i5 = i3;
            j = -9223372036854775807L;
            i = i5;
        }
        if (i < i4) {
            i2 = i + 1;
            j2 = (long) (-(charAt(i) - 48));
        } else {
            i2 = i;
        }
        while (i2 < i4) {
            i = i2 + 1;
            if (i2 >= this.len) {
                i2 = 26;
            } else {
                i2 = this.text.charAt(i2);
            }
            if (i2 == 76 || i2 == 83 || i2 == 66) {
                break;
            }
            i2 -= 48;
            if (j2 < -922337203685477580L) {
                throw new NumberFormatException(numberString());
            }
            j2 *= 10;
            if (j2 < ((long) i2) + j) {
                throw new NumberFormatException(numberString());
            }
            j2 -= (long) i2;
            i2 = i;
        }
        i = i2;
        if (obj == null) {
            return -j2;
        }
        if (i > this.np + 1) {
            return j2;
        }
        throw new NumberFormatException(numberString());
    }

    public final Number decimalValue(boolean z) {
        char c;
        int i = 2;
        int i2 = (this.np + this.sp) - 1;
        if (i2 >= this.len) {
            c = EOI;
        } else {
            c = this.text.charAt(i2);
        }
        if (c == 'F') {
            try {
                return Float.valueOf(Float.parseFloat(numberString()));
            } catch (NumberFormatException e) {
                throw new JSONException(e.getMessage() + ", " + info());
            }
        } else if (c == 'D') {
            return Double.valueOf(Double.parseDouble(numberString()));
        } else {
            if (z) {
                return decimalValue();
            }
            int i3;
            char[] cArr;
            char charAt = this.text.charAt((this.np + this.sp) - 1);
            i2 = this.sp;
            if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
                i3 = i2 - 1;
            } else {
                i3 = i2;
            }
            int i4 = this.np;
            if (i3 < this.sbuf.length) {
                this.text.getChars(i4, i4 + i3, this.sbuf, 0);
                cArr = this.sbuf;
            } else {
                char[] cArr2 = new char[i3];
                this.text.getChars(i4, i4 + i3, cArr2, 0);
                cArr = cArr2;
            }
            if (i3 > 9 || this.exp) {
                return Double.valueOf(Double.parseDouble(new String(cArr, 0, i3)));
            }
            Object obj;
            i2 = cArr[0];
            if (i2 == 45) {
                i2 = cArr[1];
                obj = 1;
            } else if (i2 == 43) {
                i2 = cArr[1];
                obj = null;
            } else {
                i = 1;
                obj = null;
            }
            i = i2 - 48;
            i2 = 0;
            for (int i5 = i; i5 < i3; i5++) {
                char c2 = cArr[i5];
                if (c2 == '.') {
                    i2 = 1;
                } else {
                    i = (i * 10) + (c2 - 48);
                    if (i2 != 0) {
                        i2 *= 10;
                    }
                }
            }
            double d = ((double) i) / ((double) i2);
            if (obj != null) {
                d = -d;
            }
            return Double.valueOf(d);
        }
    }

    public final BigDecimal decimalValue() {
        char charAt = this.text.charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i--;
        }
        int i2 = this.np;
        if (i < this.sbuf.length) {
            this.text.getChars(i2, i2 + i, this.sbuf, 0);
            return new BigDecimal(this.sbuf, 0, i);
        }
        char[] cArr = new char[i];
        this.text.getChars(i2, i + i2, cArr, 0);
        return new BigDecimal(cArr);
    }

    public boolean matchField(long j) {
        int i;
        char c = EOI;
        int i2 = 1;
        int i3 = this.bp + 1;
        char c2 = this.ch;
        while (c2 != '\"' && c2 != '\'') {
            if (c2 > ' ' || !(c2 == ' ' || c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == '\f' || c2 == '\b')) {
                this.fieldHash = 0;
                this.matchStat = -2;
                return false;
            }
            i = this.bp + i2;
            c2 = i >= this.len ? EOI : this.text.charAt(i);
            i2++;
        }
        long j2 = -3750763034362895579L;
        for (i = i3; i < this.len; i++) {
            char charAt = this.text.charAt(i);
            if (charAt == c2) {
                i = ((i - i3) + 1) + i2;
                break;
            }
            j2 = (j2 ^ ((long) charAt)) * 1099511628211L;
        }
        i = i2;
        if (j2 != j) {
            this.matchStat = -2;
            this.fieldHash = j2;
            return false;
        }
        int i4;
        i3 = i + 1;
        i += this.bp;
        char charAt2 = i >= this.len ? EOI : this.text.charAt(i);
        while (charAt2 != ':') {
            if (charAt2 > ' ' || !(charAt2 == ' ' || charAt2 == '\n' || charAt2 == '\r' || charAt2 == '\t' || charAt2 == '\f' || charAt2 == '\b')) {
                throw new JSONException("match feild error expect ':'");
            }
            i4 = i3 + 1;
            i = this.bp + i3;
            charAt2 = i >= this.len ? EOI : this.text.charAt(i);
            i3 = i4;
        }
        i4 = i3 + 1;
        i3 += this.bp;
        charAt2 = i3 >= this.len ? EOI : this.text.charAt(i3);
        if (charAt2 == '{') {
            this.bp = i3 + 1;
            if (this.bp < this.len) {
                c = this.text.charAt(this.bp);
            }
            this.ch = c;
            this.token = 12;
        } else if (charAt2 == '[') {
            this.bp = i3 + 1;
            if (this.bp < this.len) {
                c = this.text.charAt(this.bp);
            }
            this.ch = c;
            this.token = 14;
        } else {
            this.bp = i3;
            if (this.bp < this.len) {
                c = this.text.charAt(this.bp);
            }
            this.ch = c;
            nextToken();
        }
        return true;
    }

    private int matchFieldHash(long j) {
        int i;
        int i2 = 1;
        int i3 = this.bp + 1;
        char c = this.ch;
        while (c != '\"' && c != '\'') {
            if (c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == '\f' || c == '\b') {
                i = this.bp + i2;
                c = i >= this.len ? EOI : this.text.charAt(i);
                i2++;
            } else {
                this.fieldHash = 0;
                this.matchStat = -2;
                return 0;
            }
        }
        long j2 = -3750763034362895579L;
        for (i = this.bp + i2; i < this.len; i++) {
            char charAt = this.text.charAt(i);
            if (charAt == c) {
                i = ((i - this.bp) - i2) + i2;
                break;
            }
            j2 = (j2 ^ ((long) charAt)) * 1099511628211L;
        }
        i = i2;
        if (j2 != j) {
            this.fieldHash = j2;
            this.matchStat = -2;
            return 0;
        }
        i3 = i + 1;
        i = this.bp + i3;
        char charAt2 = i >= this.len ? EOI : this.text.charAt(i);
        while (charAt2 != ':') {
            if (charAt2 > ' ' || !(charAt2 == ' ' || charAt2 == '\n' || charAt2 == '\r' || charAt2 == '\t' || charAt2 == '\f' || charAt2 == '\b')) {
                throw new JSONException("match feild error expect ':'");
            }
            int i4 = i3 + 1;
            i = this.bp + i3;
            charAt2 = i >= this.len ? EOI : this.text.charAt(i);
            i3 = i4;
        }
        return i3 + 1;
    }

    public int scanFieldInt(long j) {
        char c = EOI;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j);
        if (matchFieldHash == 0) {
            return 0;
        }
        int i;
        int i2;
        int i3;
        int i4 = matchFieldHash + 1;
        matchFieldHash += this.bp;
        char charAt = matchFieldHash >= this.len ? EOI : this.text.charAt(matchFieldHash);
        if (charAt == '\"') {
            matchFieldHash = 1;
        } else {
            matchFieldHash = 0;
        }
        if (matchFieldHash != 0) {
            i = i4 + 1;
            matchFieldHash = this.bp + i4;
            matchFieldHash = matchFieldHash >= this.len ? EOI : this.text.charAt(matchFieldHash);
            i4 = 1;
        } else {
            int i5 = matchFieldHash;
            char c2 = charAt;
            i = i4;
            i4 = i5;
        }
        if (matchFieldHash == 45) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (i2 != 0) {
            i3 = i + 1;
            matchFieldHash = this.bp + i;
            if (matchFieldHash >= this.len) {
                matchFieldHash = 26;
            } else {
                matchFieldHash = this.text.charAt(matchFieldHash);
            }
        } else {
            i3 = i;
        }
        if (matchFieldHash < 48 || matchFieldHash > 57) {
            this.matchStat = -1;
            return 0;
        }
        matchFieldHash -= 48;
        while (true) {
            i = i3 + 1;
            i3 += this.bp;
            if (i3 >= this.len) {
                i3 = 26;
            } else {
                i3 = this.text.charAt(i3);
            }
            if (i3 >= 48 && i3 <= 57) {
                matchFieldHash = (matchFieldHash * 10) + (i3 - 48);
                i3 = i;
            }
        }
        if (i3 == 46) {
            this.matchStat = -1;
            return 0;
        }
        if (i3 == 34) {
            if (i4 == 0) {
                this.matchStat = -1;
                return 0;
            }
            i4 = i + 1;
            i3 = this.bp + i;
            if (i3 >= this.len) {
                i3 = 26;
            } else {
                i3 = this.text.charAt(i3);
            }
            i = i4;
        }
        if (matchFieldHash < 0) {
            this.matchStat = -1;
            return 0;
        }
        while (i3 != 44) {
            if (i3 <= 32 && (i3 == 32 || i3 == 10 || i3 == 13 || i3 == 9 || i3 == 12 || i3 == 8)) {
                i4 = i + 1;
                i3 = this.bp + i;
                if (i3 >= this.len) {
                    i3 = 26;
                } else {
                    i3 = this.text.charAt(i3);
                }
                i = i4;
            } else if (i3 == 125) {
                i4 = i + 1;
                char charAt2 = charAt(this.bp + i);
                if (charAt2 == ',') {
                    this.token = 16;
                    this.bp += i4 - 1;
                    i3 = this.bp + 1;
                    this.bp = i3;
                    if (i3 < this.len) {
                        c = this.text.charAt(i3);
                    }
                    this.ch = c;
                } else if (charAt2 == ']') {
                    this.token = 15;
                    this.bp += i4 - 1;
                    i3 = this.bp + 1;
                    this.bp = i3;
                    if (i3 < this.len) {
                        c = this.text.charAt(i3);
                    }
                    this.ch = c;
                } else if (charAt2 == '}') {
                    this.token = 13;
                    this.bp += i4 - 1;
                    i3 = this.bp + 1;
                    this.bp = i3;
                    if (i3 < this.len) {
                        c = this.text.charAt(i3);
                    }
                    this.ch = c;
                } else if (charAt2 == EOI) {
                    this.token = 20;
                    this.bp += i4 - 1;
                    this.ch = EOI;
                } else {
                    this.matchStat = -1;
                    return 0;
                }
                this.matchStat = 4;
                if (i2 != 0) {
                    return -matchFieldHash;
                }
                return matchFieldHash;
            } else {
                this.matchStat = -1;
                return 0;
            }
        }
        this.bp += i - 1;
        i3 = this.bp + 1;
        this.bp = i3;
        if (i3 < this.len) {
            c = this.text.charAt(i3);
        }
        this.ch = c;
        this.matchStat = 3;
        this.token = 16;
        if (i2 != 0) {
            return -matchFieldHash;
        }
        return matchFieldHash;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int[] scanFieldIntArray(long r14) {
        /*
        r13 = this;
        r11 = 16;
        r10 = -1;
        r7 = 0;
        r4 = 0;
        r1 = 26;
        r13.matchStat = r4;
        r0 = r13.matchFieldHash(r14);
        if (r0 != 0) goto L_0x0011;
    L_0x000f:
        r0 = r7;
    L_0x0010:
        return r0;
    L_0x0011:
        r2 = r13.bp;
        r3 = r0 + 1;
        r0 = r0 + r2;
        r2 = r13.len;
        if (r0 < r2) goto L_0x0023;
    L_0x001a:
        r0 = r1;
    L_0x001b:
        r2 = 91;
        if (r0 == r2) goto L_0x002a;
    L_0x001f:
        r13.matchStat = r10;
        r0 = r7;
        goto L_0x0010;
    L_0x0023:
        r2 = r13.text;
        r0 = r2.charAt(r0);
        goto L_0x001b;
    L_0x002a:
        r0 = r13.bp;
        r5 = r3 + 1;
        r0 = r0 + r3;
        r2 = r13.len;
        if (r0 < r2) goto L_0x0064;
    L_0x0033:
        r0 = r1;
    L_0x0034:
        r2 = new int[r11];
        r3 = 93;
        if (r0 != r3) goto L_0x016f;
    L_0x003a:
        r0 = r13.bp;
        r3 = r5 + 1;
        r0 = r0 + r5;
        r5 = r13.len;
        if (r0 < r5) goto L_0x006b;
    L_0x0043:
        r0 = r1;
    L_0x0044:
        r5 = r3;
        r3 = r0;
        r0 = r4;
    L_0x0047:
        r6 = r2.length;
        if (r0 == r6) goto L_0x0163;
    L_0x004a:
        r6 = new int[r0];
        java.lang.System.arraycopy(r2, r4, r6, r4, r0);
        r0 = r6;
    L_0x0050:
        r2 = 44;
        if (r3 != r2) goto L_0x0101;
    L_0x0054:
        r1 = r13.bp;
        r2 = r5 + -1;
        r1 = r1 + r2;
        r13.bp = r1;
        r13.next();
        r1 = 3;
        r13.matchStat = r1;
        r13.token = r11;
        goto L_0x0010;
    L_0x0064:
        r2 = r13.text;
        r0 = r2.charAt(r0);
        goto L_0x0034;
    L_0x006b:
        r5 = r13.text;
        r0 = r5.charAt(r0);
        goto L_0x0044;
    L_0x0072:
        r6 = r5.length;
        if (r9 < r6) goto L_0x0169;
    L_0x0075:
        r6 = r5.length;
        r6 = r6 * 3;
        r6 = r6 / 2;
        r6 = new int[r6];
        java.lang.System.arraycopy(r5, r4, r6, r4, r9);
    L_0x007f:
        r5 = r9 + 1;
        if (r8 == 0) goto L_0x0084;
    L_0x0083:
        r0 = -r0;
    L_0x0084:
        r6[r9] = r0;
        r0 = 44;
        if (r2 != r0) goto L_0x00e1;
    L_0x008a:
        r0 = r13.bp;
        r2 = r3 + 1;
        r0 = r0 + r3;
        r3 = r13.len;
        if (r0 < r3) goto L_0x00da;
    L_0x0093:
        r0 = r1;
    L_0x0094:
        r3 = r2;
    L_0x0095:
        r9 = r5;
        r2 = r3;
        r5 = r6;
    L_0x0098:
        r3 = 45;
        if (r0 != r3) goto L_0x016c;
    L_0x009c:
        r0 = r13.bp;
        r3 = r2 + 1;
        r0 = r0 + r2;
        r2 = r13.len;
        if (r0 < r2) goto L_0x00cc;
    L_0x00a5:
        r0 = r1;
    L_0x00a6:
        r2 = 1;
        r8 = r2;
        r2 = r3;
    L_0x00a9:
        r3 = 48;
        if (r0 < r3) goto L_0x00fc;
    L_0x00ad:
        r3 = 57;
        if (r0 > r3) goto L_0x00fc;
    L_0x00b1:
        r0 = r0 + -48;
    L_0x00b3:
        r6 = r13.bp;
        r3 = r2 + 1;
        r2 = r2 + r6;
        r6 = r13.len;
        if (r2 < r6) goto L_0x00d3;
    L_0x00bc:
        r2 = r1;
    L_0x00bd:
        r6 = 48;
        if (r2 < r6) goto L_0x0072;
    L_0x00c1:
        r6 = 57;
        if (r2 > r6) goto L_0x0072;
    L_0x00c5:
        r0 = r0 * 10;
        r2 = r2 + -48;
        r0 = r0 + r2;
        r2 = r3;
        goto L_0x00b3;
    L_0x00cc:
        r2 = r13.text;
        r0 = r2.charAt(r0);
        goto L_0x00a6;
    L_0x00d3:
        r6 = r13.text;
        r2 = r6.charAt(r2);
        goto L_0x00bd;
    L_0x00da:
        r3 = r13.text;
        r0 = r3.charAt(r0);
        goto L_0x0094;
    L_0x00e1:
        r0 = 93;
        if (r2 != r0) goto L_0x0166;
    L_0x00e5:
        r0 = r13.bp;
        r2 = r3 + 1;
        r0 = r0 + r3;
        r3 = r13.len;
        if (r0 < r3) goto L_0x00f5;
    L_0x00ee:
        r0 = r1;
    L_0x00ef:
        r3 = r0;
        r0 = r5;
        r5 = r2;
        r2 = r6;
        goto L_0x0047;
    L_0x00f5:
        r3 = r13.text;
        r0 = r3.charAt(r0);
        goto L_0x00ef;
    L_0x00fc:
        r13.matchStat = r10;
        r0 = r7;
        goto L_0x0010;
    L_0x0101:
        r2 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r3 != r2) goto L_0x015e;
    L_0x0105:
        r2 = r13.bp;
        r3 = r5 + 1;
        r2 = r2 + r5;
        r2 = r13.charAt(r2);
        r4 = 44;
        if (r2 != r4) goto L_0x0123;
    L_0x0112:
        r13.token = r11;
        r1 = r13.bp;
        r2 = r3 + -1;
        r1 = r1 + r2;
        r13.bp = r1;
        r13.next();
    L_0x011e:
        r1 = 4;
        r13.matchStat = r1;
        goto L_0x0010;
    L_0x0123:
        r4 = 93;
        if (r2 != r4) goto L_0x0136;
    L_0x0127:
        r1 = 15;
        r13.token = r1;
        r1 = r13.bp;
        r2 = r3 + -1;
        r1 = r1 + r2;
        r13.bp = r1;
        r13.next();
        goto L_0x011e;
    L_0x0136:
        r4 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r2 != r4) goto L_0x0149;
    L_0x013a:
        r1 = 13;
        r13.token = r1;
        r1 = r13.bp;
        r2 = r3 + -1;
        r1 = r1 + r2;
        r13.bp = r1;
        r13.next();
        goto L_0x011e;
    L_0x0149:
        if (r2 != r1) goto L_0x0159;
    L_0x014b:
        r2 = r13.bp;
        r3 = r3 + -1;
        r2 = r2 + r3;
        r13.bp = r2;
        r2 = 20;
        r13.token = r2;
        r13.ch = r1;
        goto L_0x011e;
    L_0x0159:
        r13.matchStat = r10;
        r0 = r7;
        goto L_0x0010;
    L_0x015e:
        r13.matchStat = r10;
        r0 = r7;
        goto L_0x0010;
    L_0x0163:
        r0 = r2;
        goto L_0x0050;
    L_0x0166:
        r0 = r2;
        goto L_0x0095;
    L_0x0169:
        r6 = r5;
        goto L_0x007f;
    L_0x016c:
        r8 = r4;
        goto L_0x00a9;
    L_0x016f:
        r9 = r4;
        r12 = r2;
        r2 = r5;
        r5 = r12;
        goto L_0x0098;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldIntArray(long):int[]");
    }

    public long scanFieldLong(long j) {
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j);
        if (matchFieldHash == 0) {
            return 0;
        }
        int i;
        int i2 = matchFieldHash + 1;
        matchFieldHash += this.bp;
        if (matchFieldHash >= this.len) {
            matchFieldHash = EOI;
        } else {
            matchFieldHash = this.text.charAt(matchFieldHash);
        }
        Object obj = matchFieldHash == '\"' ? 1 : null;
        if (obj != null) {
            i = i2 + 1;
            matchFieldHash = this.bp + i2;
            if (matchFieldHash >= this.len) {
                matchFieldHash = EOI;
            } else {
                matchFieldHash = this.text.charAt(matchFieldHash);
            }
        } else {
            i = i2;
        }
        Object obj2 = matchFieldHash == 45 ? 1 : null;
        if (obj2 != null) {
            i2 = i + 1;
            matchFieldHash = this.bp + i;
            if (matchFieldHash >= this.len) {
                matchFieldHash = 26;
            } else {
                matchFieldHash = this.text.charAt(matchFieldHash);
            }
        } else {
            i2 = i;
        }
        if (matchFieldHash < 48 || matchFieldHash > 57) {
            this.matchStat = -1;
            return 0;
        }
        long j2 = (long) (matchFieldHash - 48);
        while (true) {
            int i3 = i2 + 1;
            i2 += this.bp;
            if (i2 >= this.len) {
                i2 = 26;
            } else {
                i2 = this.text.charAt(i2);
            }
            if (i2 >= 48 && i2 <= 57) {
                j2 = (j2 * 10) + ((long) (i2 - 48));
                i2 = i3;
            }
        }
        if (i2 == 46) {
            this.matchStat = -1;
            return 0;
        }
        if (i2 == 34) {
            if (obj == null) {
                this.matchStat = -1;
                return 0;
            }
            int i4 = i3 + 1;
            i2 = this.bp + i3;
            if (i2 >= this.len) {
                i2 = 26;
            } else {
                i2 = this.text.charAt(i2);
            }
            i3 = i4;
        }
        if (j2 < 0) {
            this.matchStat = -1;
            return 0;
        } else if (i2 == 44) {
            this.bp += i3 - 1;
            i2 = this.bp + 1;
            this.bp = i2;
            if (i2 >= this.len) {
                r2 = EOI;
            } else {
                r2 = this.text.charAt(i2);
            }
            this.ch = r2;
            this.matchStat = 3;
            this.token = 16;
            if (obj2 != null) {
                return -j2;
            }
            return j2;
        } else if (i2 == 125) {
            i4 = i3 + 1;
            r2 = charAt(this.bp + i3);
            if (r2 == ',') {
                this.token = 16;
                this.bp += i4 - 1;
                i2 = this.bp + 1;
                this.bp = i2;
                if (i2 >= this.len) {
                    r2 = EOI;
                } else {
                    r2 = this.text.charAt(i2);
                }
                this.ch = r2;
            } else if (r2 == ']') {
                this.token = 15;
                this.bp += i4 - 1;
                i2 = this.bp + 1;
                this.bp = i2;
                if (i2 >= this.len) {
                    r2 = EOI;
                } else {
                    r2 = this.text.charAt(i2);
                }
                this.ch = r2;
            } else if (r2 == '}') {
                this.token = 13;
                this.bp += i4 - 1;
                i2 = this.bp + 1;
                this.bp = i2;
                if (i2 >= this.len) {
                    r2 = EOI;
                } else {
                    r2 = this.text.charAt(i2);
                }
                this.ch = r2;
            } else if (r2 == EOI) {
                this.token = 20;
                this.bp += i4 - 1;
                this.ch = EOI;
            } else {
                this.matchStat = -1;
                return 0;
            }
            this.matchStat = 4;
            if (obj2 != null) {
                return -j2;
            }
            return j2;
        } else {
            this.matchStat = -1;
            return 0;
        }
    }

    public String scanFieldString(long j) {
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j);
        if (matchFieldHash == 0) {
            return null;
        }
        int i = matchFieldHash + 1;
        matchFieldHash += this.bp;
        if (matchFieldHash >= this.len) {
            throw new JSONException("unclosed str, " + info());
        } else if (this.text.charAt(matchFieldHash) != '\"') {
            this.matchStat = -1;
            return this.stringDefaultValue;
        } else {
            int i2 = this.bp + i;
            int indexOf = this.text.indexOf(34, i2);
            if (indexOf == -1) {
                throw new JSONException("unclosed str, " + info());
            }
            String substring;
            int i3;
            if (V6) {
                substring = this.text.substring(i2, indexOf);
            } else {
                i3 = indexOf - i2;
                substring = new String(sub_chars(this.bp + i, i3), 0, i3);
            }
            if (substring.indexOf(92) != -1) {
                matchFieldHash = indexOf;
                indexOf = 0;
                while (true) {
                    i3 = matchFieldHash - 1;
                    int i4 = 0;
                    while (i3 >= 0 && this.text.charAt(i3) == '\\') {
                        i4++;
                        i3--;
                        indexOf = 1;
                    }
                    if (i4 % 2 == 0) {
                        break;
                    }
                    matchFieldHash = this.text.indexOf(34, matchFieldHash + 1);
                }
                i3 = matchFieldHash - i2;
                char[] sub_chars = sub_chars(this.bp + i, i3);
                if (indexOf != 0) {
                    indexOf = matchFieldHash;
                    substring = readString(sub_chars, i3);
                } else {
                    String str = new String(sub_chars, 0, i3);
                    if (str.indexOf(92) != -1) {
                        indexOf = matchFieldHash;
                        substring = readString(sub_chars, i3);
                    } else {
                        String str2 = str;
                        indexOf = matchFieldHash;
                        substring = str2;
                    }
                }
            }
            int i5 = indexOf + 1;
            char charAt = i5 >= this.len ? EOI : this.text.charAt(i5);
            if (charAt == ',') {
                this.bp = i5;
                indexOf = this.bp + 1;
                this.bp = indexOf;
                this.ch = indexOf >= this.len ? EOI : this.text.charAt(indexOf);
                this.matchStat = 3;
                this.token = 16;
                return substring;
            } else if (charAt == '}') {
                i5++;
                charAt = i5 >= this.len ? EOI : this.text.charAt(i5);
                if (charAt == ',') {
                    this.token = 16;
                    this.bp = i5;
                    next();
                } else if (charAt == ']') {
                    this.token = 15;
                    this.bp = i5;
                    next();
                } else if (charAt == '}') {
                    this.token = 13;
                    this.bp = i5;
                    next();
                } else if (charAt == EOI) {
                    this.token = 20;
                    this.bp = i5;
                    this.ch = EOI;
                } else {
                    this.matchStat = -1;
                    return this.stringDefaultValue;
                }
                this.matchStat = 4;
                return substring;
            } else {
                this.matchStat = -1;
                return this.stringDefaultValue;
            }
        }
    }

    public Date scanFieldDate(long j) {
        char c = EOI;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j);
        if (matchFieldHash == 0) {
            return null;
        }
        int i;
        Date time;
        int i2 = this.bp;
        char c2 = this.ch;
        int i3 = matchFieldHash + 1;
        matchFieldHash += this.bp;
        if (matchFieldHash >= this.len) {
            matchFieldHash = 26;
        } else {
            matchFieldHash = this.text.charAt(matchFieldHash);
        }
        int i4;
        if (matchFieldHash == 34) {
            matchFieldHash = this.bp + i3;
            i4 = i3 + 1;
            i = this.bp + i3;
            if (i < this.len) {
                this.text.charAt(i);
            }
            i = this.text.indexOf(34, this.bp + i4);
            if (i == -1) {
                throw new JSONException("unclosed str");
            }
            i -= matchFieldHash;
            this.bp = matchFieldHash;
            if (scanISO8601DateIfMatch(false, i)) {
                time = this.calendar.getTime();
                i += i4;
                i3 = i + 1;
                c2 = charAt(i + i2);
                this.bp = i2;
            } else {
                this.bp = i2;
                this.matchStat = -1;
                return null;
            }
        } else if (matchFieldHash < 48 || matchFieldHash > 57) {
            this.matchStat = -1;
            return null;
        } else {
            char c3;
            long j2 = (long) (matchFieldHash - 48);
            while (true) {
                i2 = i3 + 1;
                i3 += this.bp;
                if (i3 >= this.len) {
                    c3 = '\u001a';
                } else {
                    c3 = this.text.charAt(i3);
                }
                if (c3 >= '0' && c3 <= '9') {
                    j2 = (j2 * 10) + ((long) (c3 - 48));
                    i3 = i2;
                }
            }
            if (c3 == '.') {
                this.matchStat = -1;
                return null;
            }
            char charAt;
            if (c3 == '\"') {
                i4 = i2 + 1;
                i3 = this.bp + i2;
                charAt = i3 >= this.len ? EOI : this.text.charAt(i3);
            } else {
                i4 = i2;
                charAt = c3;
            }
            if (j2 < 0) {
                this.matchStat = -1;
                return null;
            }
            time = new Date(j2);
            c2 = charAt;
            i3 = i4;
        }
        if (c2 == ',') {
            this.bp += i3 - 1;
            i = this.bp + 1;
            this.bp = i;
            if (i < this.len) {
                c = this.text.charAt(i);
            }
            this.ch = c;
            this.matchStat = 3;
            this.token = 16;
            return time;
        } else if (c2 == '}') {
            i2 = i3 + 1;
            c2 = charAt(this.bp + i3);
            if (c2 == ',') {
                this.token = 16;
                this.bp += i2 - 1;
                i = this.bp + 1;
                this.bp = i;
                if (i < this.len) {
                    c = this.text.charAt(i);
                }
                this.ch = c;
            } else if (c2 == ']') {
                this.token = 15;
                this.bp += i2 - 1;
                i = this.bp + 1;
                this.bp = i;
                if (i < this.len) {
                    c = this.text.charAt(i);
                }
                this.ch = c;
            } else if (c2 == '}') {
                this.token = 13;
                this.bp += i2 - 1;
                i = this.bp + 1;
                this.bp = i;
                if (i < this.len) {
                    c = this.text.charAt(i);
                }
                this.ch = c;
            } else if (c2 == EOI) {
                this.token = 20;
                this.bp += i2 - 1;
                this.ch = EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return time;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    public boolean scanFieldBoolean(long j) {
        boolean z = true;
        char c = EOI;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j);
        if (matchFieldHash == 0) {
            return false;
        }
        int i;
        if (this.text.startsWith("false", this.bp + matchFieldHash)) {
            matchFieldHash += 5;
            z = false;
        } else if (this.text.startsWith("true", this.bp + matchFieldHash)) {
            matchFieldHash += 4;
        } else if (this.text.startsWith("\"false\"", this.bp + matchFieldHash)) {
            matchFieldHash += 7;
            z = false;
        } else if (this.text.startsWith("\"true\"", this.bp + matchFieldHash)) {
            matchFieldHash += 6;
        } else if (this.text.charAt(this.bp + matchFieldHash) == '1') {
            matchFieldHash++;
        } else if (this.text.charAt(this.bp + matchFieldHash) == '0') {
            matchFieldHash++;
            z = false;
        } else if (this.text.startsWith("\"1\"", this.bp + matchFieldHash)) {
            matchFieldHash += 3;
        } else if (this.text.startsWith("\"0\"", this.bp + matchFieldHash)) {
            matchFieldHash += 3;
            z = false;
        } else {
            this.matchStat = -1;
            return false;
        }
        int i2 = matchFieldHash + 1;
        matchFieldHash += this.bp;
        char charAt = matchFieldHash >= this.len ? EOI : this.text.charAt(matchFieldHash);
        while (charAt != ',') {
            int i3;
            if (charAt != '}' && (charAt == ' ' || charAt == '\n' || charAt == '\r' || charAt == '\t' || charAt == '\f' || charAt == '\b')) {
                i3 = i2 + 1;
                matchFieldHash = this.bp + i2;
                charAt = matchFieldHash >= this.len ? EOI : this.text.charAt(matchFieldHash);
                i2 = i3;
            } else if (charAt == '}') {
                i3 = i2 + 1;
                charAt = charAt(this.bp + i2);
                if (charAt == ',') {
                    this.token = 16;
                    this.bp += i3 - 1;
                    i = this.bp + 1;
                    this.bp = i;
                    if (i < this.len) {
                        c = this.text.charAt(i);
                    }
                    this.ch = c;
                } else if (charAt == ']') {
                    this.token = 15;
                    this.bp += i3 - 1;
                    i = this.bp + 1;
                    this.bp = i;
                    if (i < this.len) {
                        c = this.text.charAt(i);
                    }
                    this.ch = c;
                } else if (charAt == '}') {
                    this.token = 13;
                    this.bp += i3 - 1;
                    i = this.bp + 1;
                    this.bp = i;
                    if (i < this.len) {
                        c = this.text.charAt(i);
                    }
                    this.ch = c;
                } else if (charAt == EOI) {
                    this.token = 20;
                    this.bp += i3 - 1;
                    this.ch = EOI;
                } else {
                    this.matchStat = -1;
                    return false;
                }
                this.matchStat = 4;
                return z;
            } else {
                this.matchStat = -1;
                return false;
            }
        }
        this.bp += i2 - 1;
        i = this.bp + 1;
        this.bp = i;
        if (i < this.len) {
            c = this.text.charAt(i);
        }
        this.ch = c;
        this.matchStat = 3;
        this.token = 16;
        return z;
    }

    public final float scanFieldFloat(long j) {
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j);
        if (matchFieldHash == 0) {
            return 0.0f;
        }
        int i;
        int i2 = matchFieldHash + 1;
        matchFieldHash = charAt(matchFieldHash + this.bp);
        int i3 = (this.bp + i2) - 1;
        int i4 = matchFieldHash == 45 ? 1 : 0;
        if (i4 != 0) {
            i = i2 + 1;
            matchFieldHash = charAt(this.bp + i2);
        } else {
            i = i2;
        }
        if (matchFieldHash < 48 || matchFieldHash > 57) {
            this.matchStat = -1;
            return 0.0f;
        }
        int i5;
        int i6;
        int i7;
        char c;
        float parseFloat;
        matchFieldHash -= 48;
        while (true) {
            i2 = i + 1;
            char charAt = charAt(i + this.bp);
            if (charAt >= '0' && charAt <= '9') {
                matchFieldHash = (matchFieldHash * 10) + (charAt - 48);
                i = i2;
            }
        }
        if ((charAt == '.' ? 1 : 0) != 0) {
            i5 = i2 + 1;
            char charAt2 = charAt(this.bp + i2);
            if (charAt2 < '0' || charAt2 > '9') {
                this.matchStat = -1;
                return 0.0f;
            }
            char charAt3;
            i = (charAt2 - 48) + (matchFieldHash * 10);
            matchFieldHash = 10;
            i2 = i5;
            while (true) {
                i5 = i2 + 1;
                charAt3 = charAt(i2 + this.bp);
                if (charAt3 < '0' || charAt3 > '9') {
                    i6 = matchFieldHash;
                    i7 = i;
                    c = charAt3;
                    i = i5;
                } else {
                    i = (i * 10) + (charAt3 - 48);
                    matchFieldHash *= 10;
                    i2 = i5;
                }
            }
            i6 = matchFieldHash;
            i7 = i;
            c = charAt3;
            i = i5;
        } else {
            i6 = 1;
            i7 = matchFieldHash;
            c = charAt;
            i = i2;
        }
        i5 = (c == 'e' || c == 'E') ? 1 : 0;
        if (i5 != 0) {
            i2 = i + 1;
            c = charAt(this.bp + i);
            if (c == '+' || c == '-') {
                i = i2 + 1;
                c = charAt(this.bp + i2);
            } else {
                i = i2;
            }
            while (c >= '0' && c <= '9') {
                i2 = i + 1;
                c = charAt(this.bp + i);
                i = i2;
            }
        }
        i2 = ((this.bp + i) - i3) - 1;
        if (i5 != 0 || i2 >= 10) {
            parseFloat = Float.parseFloat(subString(i3, i2));
        } else {
            parseFloat = ((float) i7) / ((float) i6);
            if (i4 != 0) {
                parseFloat = -parseFloat;
            }
        }
        if (c == ',') {
            this.bp += i - 1;
            next();
            this.matchStat = 3;
            this.token = 16;
            return parseFloat;
        } else if (c == '}') {
            i5 = i + 1;
            c = charAt(this.bp + i);
            if (c == ',') {
                this.token = 16;
                this.bp += i5 - 1;
                next();
            } else if (c == ']') {
                this.token = 15;
                this.bp += i5 - 1;
                next();
            } else if (c == '}') {
                this.token = 13;
                this.bp += i5 - 1;
                next();
            } else if (c == EOI) {
                this.bp += i5 - 1;
                this.token = 20;
                this.ch = EOI;
            } else {
                this.matchStat = -1;
                return 0.0f;
            }
            this.matchStat = 4;
            return parseFloat;
        } else {
            this.matchStat = -1;
            return 0.0f;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final float[] scanFieldFloatArray(long r12) {
        /*
        r11 = this;
        r0 = 0;
        r11.matchStat = r0;
        r0 = r11.matchFieldHash(r12);
        if (r0 != 0) goto L_0x000b;
    L_0x0009:
        r0 = 0;
    L_0x000a:
        return r0;
    L_0x000b:
        r1 = r11.bp;
        r2 = r0 + 1;
        r0 = r0 + r1;
        r1 = r11.len;
        if (r0 < r1) goto L_0x001f;
    L_0x0014:
        r0 = 26;
    L_0x0016:
        r1 = 91;
        if (r0 == r1) goto L_0x0026;
    L_0x001a:
        r0 = -1;
        r11.matchStat = r0;
        r0 = 0;
        goto L_0x000a;
    L_0x001f:
        r1 = r11.text;
        r0 = r1.charAt(r0);
        goto L_0x0016;
    L_0x0026:
        r0 = r11.bp;
        r3 = r2 + 1;
        r0 = r0 + r2;
        r1 = r11.len;
        if (r0 < r1) goto L_0x0077;
    L_0x002f:
        r0 = 26;
    L_0x0031:
        r1 = 16;
        r2 = new float[r1];
        r1 = 0;
        r10 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r10;
    L_0x003a:
        r4 = r11.bp;
        r4 = r4 + r3;
        r9 = r4 + -1;
        r4 = 45;
        if (r2 != r4) goto L_0x007e;
    L_0x0043:
        r4 = 1;
        r8 = r4;
    L_0x0045:
        if (r8 == 0) goto L_0x0053;
    L_0x0047:
        r2 = r11.bp;
        r4 = r3 + 1;
        r2 = r2 + r3;
        r3 = r11.len;
        if (r2 < r3) goto L_0x0081;
    L_0x0050:
        r2 = 26;
    L_0x0052:
        r3 = r4;
    L_0x0053:
        r4 = 48;
        if (r2 < r4) goto L_0x01cb;
    L_0x0057:
        r4 = 57;
        if (r2 > r4) goto L_0x01cb;
    L_0x005b:
        r2 = r2 + -48;
    L_0x005d:
        r5 = r11.bp;
        r4 = r3 + 1;
        r3 = r3 + r5;
        r5 = r11.len;
        if (r3 < r5) goto L_0x0088;
    L_0x0066:
        r5 = 26;
    L_0x0068:
        r3 = 48;
        if (r5 < r3) goto L_0x008f;
    L_0x006c:
        r3 = 57;
        if (r5 > r3) goto L_0x008f;
    L_0x0070:
        r2 = r2 * 10;
        r3 = r5 + -48;
        r2 = r2 + r3;
        r3 = r4;
        goto L_0x005d;
    L_0x0077:
        r1 = r11.text;
        r0 = r1.charAt(r0);
        goto L_0x0031;
    L_0x007e:
        r4 = 0;
        r8 = r4;
        goto L_0x0045;
    L_0x0081:
        r3 = r11.text;
        r2 = r3.charAt(r2);
        goto L_0x0052;
    L_0x0088:
        r5 = r11.text;
        r5 = r5.charAt(r3);
        goto L_0x0068;
    L_0x008f:
        r3 = 1;
        r6 = 46;
        if (r5 != r6) goto L_0x00d2;
    L_0x0094:
        r6 = 1;
    L_0x0095:
        if (r6 == 0) goto L_0x0255;
    L_0x0097:
        r3 = r11.bp;
        r5 = r4 + 1;
        r3 = r3 + r4;
        r4 = r11.len;
        if (r3 < r4) goto L_0x00d4;
    L_0x00a0:
        r3 = 26;
        r4 = r3;
    L_0x00a3:
        r3 = 10;
        r6 = 48;
        if (r4 < r6) goto L_0x00e3;
    L_0x00a9:
        r6 = 57;
        if (r4 > r6) goto L_0x00e3;
    L_0x00ad:
        r2 = r2 * 10;
        r4 = r4 + -48;
        r2 = r2 + r4;
        r4 = r5;
        r10 = r2;
        r2 = r3;
        r3 = r10;
    L_0x00b6:
        r6 = r11.bp;
        r5 = r4 + 1;
        r4 = r4 + r6;
        r6 = r11.len;
        if (r4 < r6) goto L_0x00dc;
    L_0x00bf:
        r4 = 26;
    L_0x00c1:
        r6 = 48;
        if (r4 < r6) goto L_0x00e9;
    L_0x00c5:
        r6 = 57;
        if (r4 > r6) goto L_0x00e9;
    L_0x00c9:
        r3 = r3 * 10;
        r4 = r4 + -48;
        r3 = r3 + r4;
        r2 = r2 * 10;
        r4 = r5;
        goto L_0x00b6;
    L_0x00d2:
        r6 = 0;
        goto L_0x0095;
    L_0x00d4:
        r4 = r11.text;
        r3 = r4.charAt(r3);
        r4 = r3;
        goto L_0x00a3;
    L_0x00dc:
        r6 = r11.text;
        r4 = r6.charAt(r4);
        goto L_0x00c1;
    L_0x00e3:
        r0 = -1;
        r11.matchStat = r0;
        r0 = 0;
        goto L_0x000a;
    L_0x00e9:
        r6 = r2;
        r7 = r3;
        r2 = r4;
        r3 = r5;
    L_0x00ed:
        r4 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r2 == r4) goto L_0x00f5;
    L_0x00f1:
        r4 = 69;
        if (r2 != r4) goto L_0x012c;
    L_0x00f5:
        r4 = 1;
        r5 = r4;
    L_0x00f7:
        if (r5 == 0) goto L_0x0144;
    L_0x00f9:
        r2 = r11.bp;
        r4 = r3 + 1;
        r2 = r2 + r3;
        r3 = r11.len;
        if (r2 < r3) goto L_0x012f;
    L_0x0102:
        r2 = 26;
    L_0x0104:
        r3 = 43;
        if (r2 == r3) goto L_0x010c;
    L_0x0108:
        r3 = 45;
        if (r2 != r3) goto L_0x0252;
    L_0x010c:
        r2 = r11.bp;
        r3 = r4 + 1;
        r2 = r2 + r4;
        r4 = r11.len;
        if (r2 < r4) goto L_0x0136;
    L_0x0115:
        r2 = 26;
    L_0x0117:
        r4 = 48;
        if (r2 < r4) goto L_0x0144;
    L_0x011b:
        r4 = 57;
        if (r2 > r4) goto L_0x0144;
    L_0x011f:
        r2 = r11.bp;
        r4 = r3 + 1;
        r2 = r2 + r3;
        r3 = r11.len;
        if (r2 < r3) goto L_0x013d;
    L_0x0128:
        r2 = 26;
    L_0x012a:
        r3 = r4;
        goto L_0x0117;
    L_0x012c:
        r4 = 0;
        r5 = r4;
        goto L_0x00f7;
    L_0x012f:
        r3 = r11.text;
        r2 = r3.charAt(r2);
        goto L_0x0104;
    L_0x0136:
        r4 = r11.text;
        r2 = r4.charAt(r2);
        goto L_0x0117;
    L_0x013d:
        r3 = r11.text;
        r2 = r3.charAt(r2);
        goto L_0x012a;
    L_0x0144:
        r4 = r3;
        r3 = r2;
        r2 = r11.bp;
        r2 = r2 + r4;
        r2 = r2 - r9;
        r2 = r2 + -1;
        if (r5 != 0) goto L_0x0181;
    L_0x014e:
        r5 = 10;
        if (r2 >= r5) goto L_0x0181;
    L_0x0152:
        r2 = (float) r7;
        r5 = (float) r6;
        r2 = r2 / r5;
        if (r8 == 0) goto L_0x024f;
    L_0x0157:
        r2 = -r2;
        r5 = r2;
    L_0x0159:
        r2 = r1.length;
        if (r0 < r2) goto L_0x024c;
    L_0x015c:
        r2 = r1.length;
        r2 = r2 * 3;
        r2 = r2 / 2;
        r2 = new float[r2];
        r6 = 0;
        r7 = 0;
        java.lang.System.arraycopy(r1, r6, r2, r7, r0);
    L_0x0168:
        r1 = r0 + 1;
        r2[r0] = r5;
        r0 = 44;
        if (r3 != r0) goto L_0x0192;
    L_0x0170:
        r0 = r11.bp;
        r3 = r4 + 1;
        r0 = r0 + r4;
        r4 = r11.len;
        if (r0 < r4) goto L_0x018b;
    L_0x0179:
        r0 = 26;
    L_0x017b:
        r10 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r10;
        goto L_0x003a;
    L_0x0181:
        r2 = r11.subString(r9, r2);
        r2 = java.lang.Float.parseFloat(r2);
        r5 = r2;
        goto L_0x0159;
    L_0x018b:
        r4 = r11.text;
        r0 = r4.charAt(r0);
        goto L_0x017b;
    L_0x0192:
        r0 = 93;
        if (r3 != r0) goto L_0x0248;
    L_0x0196:
        r0 = r11.bp;
        r5 = r4 + 1;
        r0 = r0 + r4;
        r3 = r11.len;
        if (r0 < r3) goto L_0x01c4;
    L_0x019f:
        r0 = 26;
    L_0x01a1:
        r3 = r2.length;
        if (r1 == r3) goto L_0x0245;
    L_0x01a4:
        r3 = new float[r1];
        r4 = 0;
        r6 = 0;
        java.lang.System.arraycopy(r2, r4, r3, r6, r1);
        r1 = r3;
    L_0x01ac:
        r2 = 44;
        if (r0 != r2) goto L_0x01d1;
    L_0x01b0:
        r0 = r11.bp;
        r2 = r5 + -1;
        r0 = r0 + r2;
        r11.bp = r0;
        r11.next();
        r0 = 3;
        r11.matchStat = r0;
        r0 = 16;
        r11.token = r0;
        r0 = r1;
        goto L_0x000a;
    L_0x01c4:
        r3 = r11.text;
        r0 = r3.charAt(r0);
        goto L_0x01a1;
    L_0x01cb:
        r0 = -1;
        r11.matchStat = r0;
        r0 = 0;
        goto L_0x000a;
    L_0x01d1:
        r2 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r0 != r2) goto L_0x023f;
    L_0x01d5:
        r0 = r11.bp;
        r2 = r5 + 1;
        r0 = r0 + r5;
        r3 = r11.len;
        if (r0 < r3) goto L_0x01f8;
    L_0x01de:
        r0 = 26;
    L_0x01e0:
        r3 = 44;
        if (r0 != r3) goto L_0x01ff;
    L_0x01e4:
        r0 = 16;
        r11.token = r0;
        r0 = r11.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r11.bp = r0;
        r11.next();
    L_0x01f2:
        r0 = 4;
        r11.matchStat = r0;
        r0 = r1;
        goto L_0x000a;
    L_0x01f8:
        r3 = r11.text;
        r0 = r3.charAt(r0);
        goto L_0x01e0;
    L_0x01ff:
        r3 = 93;
        if (r0 != r3) goto L_0x0212;
    L_0x0203:
        r0 = 15;
        r11.token = r0;
        r0 = r11.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r11.bp = r0;
        r11.next();
        goto L_0x01f2;
    L_0x0212:
        r3 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r0 != r3) goto L_0x0225;
    L_0x0216:
        r0 = 13;
        r11.token = r0;
        r0 = r11.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r11.bp = r0;
        r11.next();
        goto L_0x01f2;
    L_0x0225:
        r3 = 26;
        if (r0 != r3) goto L_0x0239;
    L_0x0229:
        r0 = r11.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r11.bp = r0;
        r0 = 20;
        r11.token = r0;
        r0 = 26;
        r11.ch = r0;
        goto L_0x01f2;
    L_0x0239:
        r0 = -1;
        r11.matchStat = r0;
        r0 = 0;
        goto L_0x000a;
    L_0x023f:
        r0 = -1;
        r11.matchStat = r0;
        r0 = 0;
        goto L_0x000a;
    L_0x0245:
        r1 = r2;
        goto L_0x01ac;
    L_0x0248:
        r0 = r3;
        r3 = r4;
        goto L_0x017b;
    L_0x024c:
        r2 = r1;
        goto L_0x0168;
    L_0x024f:
        r5 = r2;
        goto L_0x0159;
    L_0x0252:
        r3 = r4;
        goto L_0x0117;
    L_0x0255:
        r6 = r3;
        r7 = r2;
        r2 = r5;
        r3 = r4;
        goto L_0x00ed;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldFloatArray(long):float[]");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final float[][] scanFieldFloatArray2(long r14) {
        /*
        r13 = this;
        r0 = 0;
        r13.matchStat = r0;
        r0 = r13.matchFieldHash(r14);
        if (r0 != 0) goto L_0x000d;
    L_0x0009:
        r0 = 0;
        r0 = (float[][]) r0;
    L_0x000c:
        return r0;
    L_0x000d:
        r1 = r13.bp;
        r3 = r0 + 1;
        r0 = r0 + r1;
        r1 = r13.len;
        if (r0 < r1) goto L_0x0023;
    L_0x0016:
        r0 = 26;
    L_0x0018:
        r1 = 91;
        if (r0 == r1) goto L_0x002a;
    L_0x001c:
        r0 = -1;
        r13.matchStat = r0;
        r0 = 0;
        r0 = (float[][]) r0;
        goto L_0x000c;
    L_0x0023:
        r1 = r13.text;
        r0 = r1.charAt(r0);
        goto L_0x0018;
    L_0x002a:
        r0 = r13.bp;
        r2 = r3 + 1;
        r0 = r0 + r3;
        r1 = r13.len;
        if (r0 < r1) goto L_0x0090;
    L_0x0033:
        r0 = 26;
    L_0x0035:
        r1 = 16;
        r6 = new float[r1][];
        r1 = 0;
        r10 = r1;
    L_0x003b:
        r1 = 91;
        if (r0 != r1) goto L_0x003b;
    L_0x003f:
        r0 = r13.bp;
        r3 = r2 + 1;
        r0 = r0 + r2;
        r1 = r13.len;
        if (r0 < r1) goto L_0x0097;
    L_0x0048:
        r0 = 26;
    L_0x004a:
        r1 = 16;
        r2 = new float[r1];
        r1 = 0;
        r12 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r12;
    L_0x0053:
        r4 = r13.bp;
        r4 = r4 + r3;
        r11 = r4 + -1;
        r4 = 45;
        if (r2 != r4) goto L_0x009e;
    L_0x005c:
        r4 = 1;
        r9 = r4;
    L_0x005e:
        if (r9 == 0) goto L_0x006c;
    L_0x0060:
        r2 = r13.bp;
        r4 = r3 + 1;
        r2 = r2 + r3;
        r3 = r13.len;
        if (r2 < r3) goto L_0x00a1;
    L_0x0069:
        r2 = 26;
    L_0x006b:
        r3 = r4;
    L_0x006c:
        r4 = 48;
        if (r2 < r4) goto L_0x01f2;
    L_0x0070:
        r4 = 57;
        if (r2 > r4) goto L_0x01f2;
    L_0x0074:
        r2 = r2 + -48;
    L_0x0076:
        r5 = r13.bp;
        r4 = r3 + 1;
        r3 = r3 + r5;
        r5 = r13.len;
        if (r3 < r5) goto L_0x00a8;
    L_0x007f:
        r5 = 26;
    L_0x0081:
        r3 = 48;
        if (r5 < r3) goto L_0x00af;
    L_0x0085:
        r3 = 57;
        if (r5 > r3) goto L_0x00af;
    L_0x0089:
        r2 = r2 * 10;
        r3 = r5 + -48;
        r2 = r2 + r3;
        r3 = r4;
        goto L_0x0076;
    L_0x0090:
        r1 = r13.text;
        r0 = r1.charAt(r0);
        goto L_0x0035;
    L_0x0097:
        r1 = r13.text;
        r0 = r1.charAt(r0);
        goto L_0x004a;
    L_0x009e:
        r4 = 0;
        r9 = r4;
        goto L_0x005e;
    L_0x00a1:
        r3 = r13.text;
        r2 = r3.charAt(r2);
        goto L_0x006b;
    L_0x00a8:
        r5 = r13.text;
        r5 = r5.charAt(r3);
        goto L_0x0081;
    L_0x00af:
        r3 = 1;
        r7 = 46;
        if (r5 != r7) goto L_0x02bc;
    L_0x00b4:
        r3 = r13.bp;
        r5 = r4 + 1;
        r3 = r3 + r4;
        r4 = r13.len;
        if (r3 < r4) goto L_0x00eb;
    L_0x00bd:
        r3 = 26;
    L_0x00bf:
        r4 = 48;
        if (r3 < r4) goto L_0x00f9;
    L_0x00c3:
        r4 = 57;
        if (r3 > r4) goto L_0x00f9;
    L_0x00c7:
        r2 = r2 * 10;
        r3 = r3 + -48;
        r3 = r3 + r2;
        r2 = 10;
        r4 = r5;
    L_0x00cf:
        r7 = r13.bp;
        r5 = r4 + 1;
        r4 = r4 + r7;
        r7 = r13.len;
        if (r4 < r7) goto L_0x00f2;
    L_0x00d8:
        r4 = 26;
    L_0x00da:
        r7 = 48;
        if (r4 < r7) goto L_0x0101;
    L_0x00de:
        r7 = 57;
        if (r4 > r7) goto L_0x0101;
    L_0x00e2:
        r3 = r3 * 10;
        r4 = r4 + -48;
        r3 = r3 + r4;
        r2 = r2 * 10;
        r4 = r5;
        goto L_0x00cf;
    L_0x00eb:
        r4 = r13.text;
        r3 = r4.charAt(r3);
        goto L_0x00bf;
    L_0x00f2:
        r7 = r13.text;
        r4 = r7.charAt(r4);
        goto L_0x00da;
    L_0x00f9:
        r0 = -1;
        r13.matchStat = r0;
        r0 = 0;
        r0 = (float[][]) r0;
        goto L_0x000c;
    L_0x0101:
        r7 = r2;
        r8 = r3;
        r2 = r4;
        r3 = r5;
    L_0x0105:
        r4 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r2 == r4) goto L_0x010d;
    L_0x0109:
        r4 = 69;
        if (r2 != r4) goto L_0x0144;
    L_0x010d:
        r4 = 1;
        r5 = r4;
    L_0x010f:
        if (r5 == 0) goto L_0x015c;
    L_0x0111:
        r2 = r13.bp;
        r4 = r3 + 1;
        r2 = r2 + r3;
        r3 = r13.len;
        if (r2 < r3) goto L_0x0147;
    L_0x011a:
        r2 = 26;
    L_0x011c:
        r3 = 43;
        if (r2 == r3) goto L_0x0124;
    L_0x0120:
        r3 = 45;
        if (r2 != r3) goto L_0x02b9;
    L_0x0124:
        r2 = r13.bp;
        r3 = r4 + 1;
        r2 = r2 + r4;
        r4 = r13.len;
        if (r2 < r4) goto L_0x014e;
    L_0x012d:
        r2 = 26;
    L_0x012f:
        r4 = 48;
        if (r2 < r4) goto L_0x015c;
    L_0x0133:
        r4 = 57;
        if (r2 > r4) goto L_0x015c;
    L_0x0137:
        r2 = r13.bp;
        r4 = r3 + 1;
        r2 = r2 + r3;
        r3 = r13.len;
        if (r2 < r3) goto L_0x0155;
    L_0x0140:
        r2 = 26;
    L_0x0142:
        r3 = r4;
        goto L_0x012f;
    L_0x0144:
        r4 = 0;
        r5 = r4;
        goto L_0x010f;
    L_0x0147:
        r3 = r13.text;
        r2 = r3.charAt(r2);
        goto L_0x011c;
    L_0x014e:
        r4 = r13.text;
        r2 = r4.charAt(r2);
        goto L_0x012f;
    L_0x0155:
        r3 = r13.text;
        r2 = r3.charAt(r2);
        goto L_0x0142;
    L_0x015c:
        r4 = r3;
        r3 = r2;
        r2 = r13.bp;
        r2 = r2 + r4;
        r2 = r2 - r11;
        r2 = r2 + -1;
        if (r5 != 0) goto L_0x0199;
    L_0x0166:
        r5 = 10;
        if (r2 >= r5) goto L_0x0199;
    L_0x016a:
        r2 = (float) r8;
        r5 = (float) r7;
        r2 = r2 / r5;
        if (r9 == 0) goto L_0x02b6;
    L_0x016f:
        r2 = -r2;
        r5 = r2;
    L_0x0171:
        r2 = r1.length;
        if (r0 < r2) goto L_0x02b3;
    L_0x0174:
        r2 = r1.length;
        r2 = r2 * 3;
        r2 = r2 / 2;
        r2 = new float[r2];
        r7 = 0;
        r8 = 0;
        java.lang.System.arraycopy(r1, r7, r2, r8, r0);
    L_0x0180:
        r1 = r0 + 1;
        r2[r0] = r5;
        r0 = 44;
        if (r3 != r0) goto L_0x01aa;
    L_0x0188:
        r0 = r13.bp;
        r3 = r4 + 1;
        r0 = r0 + r4;
        r4 = r13.len;
        if (r0 < r4) goto L_0x01a3;
    L_0x0191:
        r0 = 26;
    L_0x0193:
        r12 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r12;
        goto L_0x0053;
    L_0x0199:
        r2 = r13.subString(r11, r2);
        r2 = java.lang.Float.parseFloat(r2);
        r5 = r2;
        goto L_0x0171;
    L_0x01a3:
        r4 = r13.text;
        r0 = r4.charAt(r0);
        goto L_0x0193;
    L_0x01aa:
        r0 = 93;
        if (r3 != r0) goto L_0x02af;
    L_0x01ae:
        r0 = r13.bp;
        r5 = r4 + 1;
        r0 = r0 + r4;
        r3 = r13.len;
        if (r0 < r3) goto L_0x01eb;
    L_0x01b7:
        r0 = 26;
    L_0x01b9:
        r3 = r2.length;
        if (r1 == r3) goto L_0x01c4;
    L_0x01bc:
        r3 = new float[r1];
        r4 = 0;
        r7 = 0;
        java.lang.System.arraycopy(r2, r4, r3, r7, r1);
        r2 = r3;
    L_0x01c4:
        r3 = r6.length;
        if (r10 < r3) goto L_0x02ac;
    L_0x01c7:
        r3 = r6.length;
        r3 = r3 * 3;
        r3 = r3 / 2;
        r3 = new float[r3][];
        r4 = 0;
        r6 = 0;
        java.lang.System.arraycopy(r2, r4, r3, r6, r1);
        r1 = r3;
    L_0x01d4:
        r3 = r10 + 1;
        r1[r10] = r2;
        r2 = 44;
        if (r0 != r2) goto L_0x0201;
    L_0x01dc:
        r0 = r13.bp;
        r2 = r5 + 1;
        r0 = r0 + r5;
        r4 = r13.len;
        if (r0 < r4) goto L_0x01fa;
    L_0x01e5:
        r0 = 26;
    L_0x01e7:
        r10 = r3;
        r6 = r1;
        goto L_0x003b;
    L_0x01eb:
        r3 = r13.text;
        r0 = r3.charAt(r0);
        goto L_0x01b9;
    L_0x01f2:
        r0 = -1;
        r13.matchStat = r0;
        r0 = 0;
        r0 = (float[][]) r0;
        goto L_0x000c;
    L_0x01fa:
        r4 = r13.text;
        r0 = r4.charAt(r0);
        goto L_0x01e7;
    L_0x0201:
        r2 = 93;
        if (r0 != r2) goto L_0x02a9;
    L_0x0205:
        r0 = r13.bp;
        r4 = r5 + 1;
        r0 = r0 + r5;
        r2 = r13.len;
        if (r0 < r2) goto L_0x0233;
    L_0x020e:
        r0 = 26;
    L_0x0210:
        r2 = r1.length;
        if (r3 == r2) goto L_0x021b;
    L_0x0213:
        r2 = new float[r3][];
        r5 = 0;
        r6 = 0;
        java.lang.System.arraycopy(r1, r5, r2, r6, r3);
        r1 = r2;
    L_0x021b:
        r2 = 44;
        if (r0 != r2) goto L_0x023a;
    L_0x021f:
        r0 = r13.bp;
        r2 = r4 + -1;
        r0 = r0 + r2;
        r13.bp = r0;
        r13.next();
        r0 = 3;
        r13.matchStat = r0;
        r0 = 16;
        r13.token = r0;
        r0 = r1;
        goto L_0x000c;
    L_0x0233:
        r2 = r13.text;
        r0 = r2.charAt(r0);
        goto L_0x0210;
    L_0x023a:
        r2 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r0 != r2) goto L_0x02a1;
    L_0x023e:
        r0 = r13.bp;
        r2 = r4 + 1;
        r0 = r0 + r4;
        r0 = r13.charAt(r0);
        r3 = 44;
        if (r0 != r3) goto L_0x025f;
    L_0x024b:
        r0 = 16;
        r13.token = r0;
        r0 = r13.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r13.bp = r0;
        r13.next();
    L_0x0259:
        r0 = 4;
        r13.matchStat = r0;
        r0 = r1;
        goto L_0x000c;
    L_0x025f:
        r3 = 93;
        if (r0 != r3) goto L_0x0272;
    L_0x0263:
        r0 = 15;
        r13.token = r0;
        r0 = r13.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r13.bp = r0;
        r13.next();
        goto L_0x0259;
    L_0x0272:
        r3 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r0 != r3) goto L_0x0285;
    L_0x0276:
        r0 = 13;
        r13.token = r0;
        r0 = r13.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r13.bp = r0;
        r13.next();
        goto L_0x0259;
    L_0x0285:
        r3 = 26;
        if (r0 != r3) goto L_0x0299;
    L_0x0289:
        r0 = r13.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r13.bp = r0;
        r0 = 20;
        r13.token = r0;
        r0 = 26;
        r13.ch = r0;
        goto L_0x0259;
    L_0x0299:
        r0 = -1;
        r13.matchStat = r0;
        r0 = 0;
        r0 = (float[][]) r0;
        goto L_0x000c;
    L_0x02a1:
        r0 = -1;
        r13.matchStat = r0;
        r0 = 0;
        r0 = (float[][]) r0;
        goto L_0x000c;
    L_0x02a9:
        r2 = r5;
        goto L_0x01e7;
    L_0x02ac:
        r1 = r6;
        goto L_0x01d4;
    L_0x02af:
        r0 = r3;
        r3 = r4;
        goto L_0x0193;
    L_0x02b3:
        r2 = r1;
        goto L_0x0180;
    L_0x02b6:
        r5 = r2;
        goto L_0x0171;
    L_0x02b9:
        r3 = r4;
        goto L_0x012f;
    L_0x02bc:
        r7 = r3;
        r8 = r2;
        r2 = r5;
        r3 = r4;
        goto L_0x0105;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldFloatArray2(long):float[][]");
    }

    public final double scanFieldDouble(long j) {
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j);
        if (matchFieldHash == 0) {
            return 0.0d;
        }
        Object obj;
        int i;
        int i2 = matchFieldHash + 1;
        matchFieldHash = charAt(matchFieldHash + this.bp);
        int i3 = (this.bp + i2) - 1;
        if (matchFieldHash == 45) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            i = i2 + 1;
            matchFieldHash = charAt(this.bp + i2);
        } else {
            i = i2;
        }
        if (matchFieldHash < 48 || matchFieldHash > 57) {
            this.matchStat = -1;
            return 0.0d;
        }
        int i4;
        int i5;
        char c;
        Object obj2;
        double parseDouble;
        matchFieldHash -= 48;
        while (true) {
            i2 = i + 1;
            char charAt = charAt(i + this.bp);
            if (charAt >= '0' && charAt <= '9') {
                matchFieldHash = (matchFieldHash * 10) + (charAt - 48);
                i = i2;
            }
        }
        if ((charAt == '.' ? 1 : null) != null) {
            int i6 = i2 + 1;
            char charAt2 = charAt(this.bp + i2);
            if (charAt2 < '0' || charAt2 > '9') {
                this.matchStat = -1;
                return 0.0d;
            }
            char charAt3;
            i = (charAt2 - 48) + (matchFieldHash * 10);
            matchFieldHash = 10;
            i2 = i6;
            while (true) {
                i6 = i2 + 1;
                charAt3 = charAt(i2 + this.bp);
                if (charAt3 < '0' || charAt3 > '9') {
                    i4 = matchFieldHash;
                    i5 = i;
                    c = charAt3;
                    i = i6;
                } else {
                    i = (i * 10) + (charAt3 - 48);
                    matchFieldHash *= 10;
                    i2 = i6;
                }
            }
            i4 = matchFieldHash;
            i5 = i;
            c = charAt3;
            i = i6;
        } else {
            i4 = 1;
            i5 = matchFieldHash;
            c = charAt;
            i = i2;
        }
        if (c == 'e' || c == 'E') {
            obj2 = 1;
        } else {
            obj2 = null;
        }
        if (obj2 != null) {
            i2 = i + 1;
            c = charAt(this.bp + i);
            if (c == '+' || c == '-') {
                i = i2 + 1;
                c = charAt(this.bp + i2);
            } else {
                i = i2;
            }
            while (c >= '0' && c <= '9') {
                i2 = i + 1;
                c = charAt(this.bp + i);
                i = i2;
            }
        }
        i2 = ((this.bp + i) - i3) - 1;
        if (obj2 != null || i2 >= 10) {
            parseDouble = Double.parseDouble(subString(i3, i2));
        } else {
            parseDouble = ((double) i5) / ((double) i4);
            if (obj != null) {
                parseDouble = -parseDouble;
            }
        }
        if (c == ',') {
            this.bp += i - 1;
            next();
            this.matchStat = 3;
            this.token = 16;
            return parseDouble;
        } else if (c == '}') {
            i4 = i + 1;
            c = charAt(this.bp + i);
            if (c == ',') {
                this.token = 16;
                this.bp += i4 - 1;
                next();
            } else if (c == ']') {
                this.token = 15;
                this.bp += i4 - 1;
                next();
            } else if (c == '}') {
                this.token = 13;
                this.bp += i4 - 1;
                next();
            } else if (c == EOI) {
                this.bp += i4 - 1;
                this.token = 20;
                this.ch = EOI;
            } else {
                this.matchStat = -1;
                return 0.0d;
            }
            this.matchStat = 4;
            return parseDouble;
        } else {
            this.matchStat = -1;
            return 0.0d;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final double[] scanFieldDoubleArray(long r14) {
        /*
        r13 = this;
        r0 = 0;
        r13.matchStat = r0;
        r0 = r13.matchFieldHash(r14);
        if (r0 != 0) goto L_0x000b;
    L_0x0009:
        r0 = 0;
    L_0x000a:
        return r0;
    L_0x000b:
        r1 = r13.bp;
        r2 = r0 + 1;
        r0 = r0 + r1;
        r1 = r13.len;
        if (r0 < r1) goto L_0x001f;
    L_0x0014:
        r0 = 26;
    L_0x0016:
        r1 = 91;
        if (r0 == r1) goto L_0x0026;
    L_0x001a:
        r0 = -1;
        r13.matchStat = r0;
        r0 = 0;
        goto L_0x000a;
    L_0x001f:
        r1 = r13.text;
        r0 = r1.charAt(r0);
        goto L_0x0016;
    L_0x0026:
        r0 = r13.bp;
        r3 = r2 + 1;
        r0 = r0 + r2;
        r1 = r13.len;
        if (r0 < r1) goto L_0x0077;
    L_0x002f:
        r0 = 26;
    L_0x0031:
        r1 = 16;
        r2 = new double[r1];
        r1 = 0;
        r12 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r12;
    L_0x003a:
        r4 = r13.bp;
        r4 = r4 + r3;
        r9 = r4 + -1;
        r4 = 45;
        if (r2 != r4) goto L_0x007e;
    L_0x0043:
        r4 = 1;
        r8 = r4;
    L_0x0045:
        if (r8 == 0) goto L_0x0053;
    L_0x0047:
        r2 = r13.bp;
        r4 = r3 + 1;
        r2 = r2 + r3;
        r3 = r13.len;
        if (r2 < r3) goto L_0x0081;
    L_0x0050:
        r2 = 26;
    L_0x0052:
        r3 = r4;
    L_0x0053:
        r4 = 48;
        if (r2 < r4) goto L_0x01ca;
    L_0x0057:
        r4 = 57;
        if (r2 > r4) goto L_0x01ca;
    L_0x005b:
        r2 = r2 + -48;
    L_0x005d:
        r5 = r13.bp;
        r4 = r3 + 1;
        r3 = r3 + r5;
        r5 = r13.len;
        if (r3 < r5) goto L_0x0088;
    L_0x0066:
        r5 = 26;
    L_0x0068:
        r3 = 48;
        if (r5 < r3) goto L_0x008f;
    L_0x006c:
        r3 = 57;
        if (r5 > r3) goto L_0x008f;
    L_0x0070:
        r2 = r2 * 10;
        r3 = r5 + -48;
        r2 = r2 + r3;
        r3 = r4;
        goto L_0x005d;
    L_0x0077:
        r1 = r13.text;
        r0 = r1.charAt(r0);
        goto L_0x0031;
    L_0x007e:
        r4 = 0;
        r8 = r4;
        goto L_0x0045;
    L_0x0081:
        r3 = r13.text;
        r2 = r3.charAt(r2);
        goto L_0x0052;
    L_0x0088:
        r5 = r13.text;
        r5 = r5.charAt(r3);
        goto L_0x0068;
    L_0x008f:
        r3 = 1;
        r6 = 46;
        if (r5 != r6) goto L_0x00d2;
    L_0x0094:
        r6 = 1;
    L_0x0095:
        if (r6 == 0) goto L_0x0251;
    L_0x0097:
        r3 = r13.bp;
        r5 = r4 + 1;
        r3 = r3 + r4;
        r4 = r13.len;
        if (r3 < r4) goto L_0x00d4;
    L_0x00a0:
        r3 = 26;
        r4 = r3;
    L_0x00a3:
        r3 = 10;
        r6 = 48;
        if (r4 < r6) goto L_0x00e3;
    L_0x00a9:
        r6 = 57;
        if (r4 > r6) goto L_0x00e3;
    L_0x00ad:
        r2 = r2 * 10;
        r4 = r4 + -48;
        r2 = r2 + r4;
        r4 = r5;
        r12 = r2;
        r2 = r3;
        r3 = r12;
    L_0x00b6:
        r6 = r13.bp;
        r5 = r4 + 1;
        r4 = r4 + r6;
        r6 = r13.len;
        if (r4 < r6) goto L_0x00dc;
    L_0x00bf:
        r4 = 26;
    L_0x00c1:
        r6 = 48;
        if (r4 < r6) goto L_0x00e9;
    L_0x00c5:
        r6 = 57;
        if (r4 > r6) goto L_0x00e9;
    L_0x00c9:
        r3 = r3 * 10;
        r4 = r4 + -48;
        r3 = r3 + r4;
        r2 = r2 * 10;
        r4 = r5;
        goto L_0x00b6;
    L_0x00d2:
        r6 = 0;
        goto L_0x0095;
    L_0x00d4:
        r4 = r13.text;
        r3 = r4.charAt(r3);
        r4 = r3;
        goto L_0x00a3;
    L_0x00dc:
        r6 = r13.text;
        r4 = r6.charAt(r4);
        goto L_0x00c1;
    L_0x00e3:
        r0 = -1;
        r13.matchStat = r0;
        r0 = 0;
        goto L_0x000a;
    L_0x00e9:
        r6 = r2;
        r7 = r3;
        r2 = r4;
        r3 = r5;
    L_0x00ed:
        r4 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r2 == r4) goto L_0x00f5;
    L_0x00f1:
        r4 = 69;
        if (r2 != r4) goto L_0x012c;
    L_0x00f5:
        r4 = 1;
        r5 = r4;
    L_0x00f7:
        if (r5 == 0) goto L_0x0144;
    L_0x00f9:
        r2 = r13.bp;
        r4 = r3 + 1;
        r2 = r2 + r3;
        r3 = r13.len;
        if (r2 < r3) goto L_0x012f;
    L_0x0102:
        r2 = 26;
    L_0x0104:
        r3 = 43;
        if (r2 == r3) goto L_0x010c;
    L_0x0108:
        r3 = 45;
        if (r2 != r3) goto L_0x024e;
    L_0x010c:
        r2 = r13.bp;
        r3 = r4 + 1;
        r2 = r2 + r4;
        r4 = r13.len;
        if (r2 < r4) goto L_0x0136;
    L_0x0115:
        r2 = 26;
    L_0x0117:
        r4 = 48;
        if (r2 < r4) goto L_0x0144;
    L_0x011b:
        r4 = 57;
        if (r2 > r4) goto L_0x0144;
    L_0x011f:
        r2 = r13.bp;
        r4 = r3 + 1;
        r2 = r2 + r3;
        r3 = r13.len;
        if (r2 < r3) goto L_0x013d;
    L_0x0128:
        r2 = 26;
    L_0x012a:
        r3 = r4;
        goto L_0x0117;
    L_0x012c:
        r4 = 0;
        r5 = r4;
        goto L_0x00f7;
    L_0x012f:
        r3 = r13.text;
        r2 = r3.charAt(r2);
        goto L_0x0104;
    L_0x0136:
        r4 = r13.text;
        r2 = r4.charAt(r2);
        goto L_0x0117;
    L_0x013d:
        r3 = r13.text;
        r2 = r3.charAt(r2);
        goto L_0x012a;
    L_0x0144:
        r4 = r3;
        r3 = r2;
        r2 = r13.bp;
        r2 = r2 + r4;
        r2 = r2 - r9;
        r2 = r2 + -1;
        if (r5 != 0) goto L_0x0181;
    L_0x014e:
        r5 = 10;
        if (r2 >= r5) goto L_0x0181;
    L_0x0152:
        r10 = (double) r7;
        r6 = (double) r6;
        r6 = r10 / r6;
        if (r8 == 0) goto L_0x0159;
    L_0x0158:
        r6 = -r6;
    L_0x0159:
        r2 = r1.length;
        if (r0 < r2) goto L_0x024b;
    L_0x015c:
        r2 = r1.length;
        r2 = r2 * 3;
        r2 = r2 / 2;
        r2 = new double[r2];
        r5 = 0;
        r8 = 0;
        java.lang.System.arraycopy(r1, r5, r2, r8, r0);
    L_0x0168:
        r1 = r0 + 1;
        r2[r0] = r6;
        r0 = 44;
        if (r3 != r0) goto L_0x0191;
    L_0x0170:
        r0 = r13.bp;
        r3 = r4 + 1;
        r0 = r0 + r4;
        r4 = r13.len;
        if (r0 < r4) goto L_0x018a;
    L_0x0179:
        r0 = 26;
    L_0x017b:
        r12 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r12;
        goto L_0x003a;
    L_0x0181:
        r2 = r13.subString(r9, r2);
        r6 = java.lang.Double.parseDouble(r2);
        goto L_0x0159;
    L_0x018a:
        r4 = r13.text;
        r0 = r4.charAt(r0);
        goto L_0x017b;
    L_0x0191:
        r0 = 93;
        if (r3 != r0) goto L_0x0247;
    L_0x0195:
        r0 = r13.bp;
        r5 = r4 + 1;
        r0 = r0 + r4;
        r3 = r13.len;
        if (r0 < r3) goto L_0x01c3;
    L_0x019e:
        r0 = 26;
    L_0x01a0:
        r3 = r2.length;
        if (r1 == r3) goto L_0x0244;
    L_0x01a3:
        r3 = new double[r1];
        r4 = 0;
        r6 = 0;
        java.lang.System.arraycopy(r2, r4, r3, r6, r1);
        r1 = r3;
    L_0x01ab:
        r2 = 44;
        if (r0 != r2) goto L_0x01d0;
    L_0x01af:
        r0 = r13.bp;
        r2 = r5 + -1;
        r0 = r0 + r2;
        r13.bp = r0;
        r13.next();
        r0 = 3;
        r13.matchStat = r0;
        r0 = 16;
        r13.token = r0;
        r0 = r1;
        goto L_0x000a;
    L_0x01c3:
        r3 = r13.text;
        r0 = r3.charAt(r0);
        goto L_0x01a0;
    L_0x01ca:
        r0 = -1;
        r13.matchStat = r0;
        r0 = 0;
        goto L_0x000a;
    L_0x01d0:
        r2 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r0 != r2) goto L_0x023e;
    L_0x01d4:
        r0 = r13.bp;
        r2 = r5 + 1;
        r0 = r0 + r5;
        r3 = r13.len;
        if (r0 < r3) goto L_0x01f7;
    L_0x01dd:
        r0 = 26;
    L_0x01df:
        r3 = 44;
        if (r0 != r3) goto L_0x01fe;
    L_0x01e3:
        r0 = 16;
        r13.token = r0;
        r0 = r13.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r13.bp = r0;
        r13.next();
    L_0x01f1:
        r0 = 4;
        r13.matchStat = r0;
        r0 = r1;
        goto L_0x000a;
    L_0x01f7:
        r3 = r13.text;
        r0 = r3.charAt(r0);
        goto L_0x01df;
    L_0x01fe:
        r3 = 93;
        if (r0 != r3) goto L_0x0211;
    L_0x0202:
        r0 = 15;
        r13.token = r0;
        r0 = r13.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r13.bp = r0;
        r13.next();
        goto L_0x01f1;
    L_0x0211:
        r3 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r0 != r3) goto L_0x0224;
    L_0x0215:
        r0 = 13;
        r13.token = r0;
        r0 = r13.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r13.bp = r0;
        r13.next();
        goto L_0x01f1;
    L_0x0224:
        r3 = 26;
        if (r0 != r3) goto L_0x0238;
    L_0x0228:
        r0 = r13.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r13.bp = r0;
        r0 = 20;
        r13.token = r0;
        r0 = 26;
        r13.ch = r0;
        goto L_0x01f1;
    L_0x0238:
        r0 = -1;
        r13.matchStat = r0;
        r0 = 0;
        goto L_0x000a;
    L_0x023e:
        r0 = -1;
        r13.matchStat = r0;
        r0 = 0;
        goto L_0x000a;
    L_0x0244:
        r1 = r2;
        goto L_0x01ab;
    L_0x0247:
        r0 = r3;
        r3 = r4;
        goto L_0x017b;
    L_0x024b:
        r2 = r1;
        goto L_0x0168;
    L_0x024e:
        r3 = r4;
        goto L_0x0117;
    L_0x0251:
        r6 = r3;
        r7 = r2;
        r2 = r5;
        r3 = r4;
        goto L_0x00ed;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldDoubleArray(long):double[]");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final double[][] scanFieldDoubleArray2(long r16) {
        /*
        r15 = this;
        r0 = 0;
        r15.matchStat = r0;
        r0 = r15.matchFieldHash(r16);
        if (r0 != 0) goto L_0x000d;
    L_0x0009:
        r0 = 0;
        r0 = (double[][]) r0;
    L_0x000c:
        return r0;
    L_0x000d:
        r1 = r15.bp;
        r3 = r0 + 1;
        r0 = r0 + r1;
        r1 = r15.len;
        if (r0 < r1) goto L_0x0023;
    L_0x0016:
        r0 = 26;
    L_0x0018:
        r1 = 91;
        if (r0 == r1) goto L_0x002a;
    L_0x001c:
        r0 = -1;
        r15.matchStat = r0;
        r0 = 0;
        r0 = (double[][]) r0;
        goto L_0x000c;
    L_0x0023:
        r1 = r15.text;
        r0 = r1.charAt(r0);
        goto L_0x0018;
    L_0x002a:
        r0 = r15.bp;
        r2 = r3 + 1;
        r0 = r0 + r3;
        r1 = r15.len;
        if (r0 < r1) goto L_0x0090;
    L_0x0033:
        r0 = 26;
    L_0x0035:
        r1 = 16;
        r8 = new double[r1][];
        r1 = 0;
        r10 = r1;
    L_0x003b:
        r1 = 91;
        if (r0 != r1) goto L_0x003b;
    L_0x003f:
        r0 = r15.bp;
        r3 = r2 + 1;
        r0 = r0 + r2;
        r1 = r15.len;
        if (r0 < r1) goto L_0x0097;
    L_0x0048:
        r0 = 26;
    L_0x004a:
        r1 = 16;
        r2 = new double[r1];
        r1 = 0;
        r14 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r14;
    L_0x0053:
        r4 = r15.bp;
        r4 = r4 + r3;
        r11 = r4 + -1;
        r4 = 45;
        if (r2 != r4) goto L_0x009e;
    L_0x005c:
        r4 = 1;
        r9 = r4;
    L_0x005e:
        if (r9 == 0) goto L_0x006c;
    L_0x0060:
        r2 = r15.bp;
        r4 = r3 + 1;
        r2 = r2 + r3;
        r3 = r15.len;
        if (r2 < r3) goto L_0x00a1;
    L_0x0069:
        r2 = 26;
    L_0x006b:
        r3 = r4;
    L_0x006c:
        r4 = 48;
        if (r2 < r4) goto L_0x01f1;
    L_0x0070:
        r4 = 57;
        if (r2 > r4) goto L_0x01f1;
    L_0x0074:
        r2 = r2 + -48;
    L_0x0076:
        r5 = r15.bp;
        r4 = r3 + 1;
        r3 = r3 + r5;
        r5 = r15.len;
        if (r3 < r5) goto L_0x00a8;
    L_0x007f:
        r5 = 26;
    L_0x0081:
        r3 = 48;
        if (r5 < r3) goto L_0x00af;
    L_0x0085:
        r3 = 57;
        if (r5 > r3) goto L_0x00af;
    L_0x0089:
        r2 = r2 * 10;
        r3 = r5 + -48;
        r2 = r2 + r3;
        r3 = r4;
        goto L_0x0076;
    L_0x0090:
        r1 = r15.text;
        r0 = r1.charAt(r0);
        goto L_0x0035;
    L_0x0097:
        r1 = r15.text;
        r0 = r1.charAt(r0);
        goto L_0x004a;
    L_0x009e:
        r4 = 0;
        r9 = r4;
        goto L_0x005e;
    L_0x00a1:
        r3 = r15.text;
        r2 = r3.charAt(r2);
        goto L_0x006b;
    L_0x00a8:
        r5 = r15.text;
        r5 = r5.charAt(r3);
        goto L_0x0081;
    L_0x00af:
        r3 = 1;
        r6 = 46;
        if (r5 != r6) goto L_0x02b8;
    L_0x00b4:
        r3 = r15.bp;
        r5 = r4 + 1;
        r3 = r3 + r4;
        r4 = r15.len;
        if (r3 < r4) goto L_0x00eb;
    L_0x00bd:
        r3 = 26;
    L_0x00bf:
        r4 = 48;
        if (r3 < r4) goto L_0x00f9;
    L_0x00c3:
        r4 = 57;
        if (r3 > r4) goto L_0x00f9;
    L_0x00c7:
        r2 = r2 * 10;
        r3 = r3 + -48;
        r3 = r3 + r2;
        r2 = 10;
        r4 = r5;
    L_0x00cf:
        r6 = r15.bp;
        r5 = r4 + 1;
        r4 = r4 + r6;
        r6 = r15.len;
        if (r4 < r6) goto L_0x00f2;
    L_0x00d8:
        r4 = 26;
    L_0x00da:
        r6 = 48;
        if (r4 < r6) goto L_0x0101;
    L_0x00de:
        r6 = 57;
        if (r4 > r6) goto L_0x0101;
    L_0x00e2:
        r3 = r3 * 10;
        r4 = r4 + -48;
        r3 = r3 + r4;
        r2 = r2 * 10;
        r4 = r5;
        goto L_0x00cf;
    L_0x00eb:
        r4 = r15.text;
        r3 = r4.charAt(r3);
        goto L_0x00bf;
    L_0x00f2:
        r6 = r15.text;
        r4 = r6.charAt(r4);
        goto L_0x00da;
    L_0x00f9:
        r0 = -1;
        r15.matchStat = r0;
        r0 = 0;
        r0 = (double[][]) r0;
        goto L_0x000c;
    L_0x0101:
        r6 = r2;
        r7 = r3;
        r2 = r4;
        r3 = r5;
    L_0x0105:
        r4 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r2 == r4) goto L_0x010d;
    L_0x0109:
        r4 = 69;
        if (r2 != r4) goto L_0x0144;
    L_0x010d:
        r4 = 1;
        r5 = r4;
    L_0x010f:
        if (r5 == 0) goto L_0x015c;
    L_0x0111:
        r2 = r15.bp;
        r4 = r3 + 1;
        r2 = r2 + r3;
        r3 = r15.len;
        if (r2 < r3) goto L_0x0147;
    L_0x011a:
        r2 = 26;
    L_0x011c:
        r3 = 43;
        if (r2 == r3) goto L_0x0124;
    L_0x0120:
        r3 = 45;
        if (r2 != r3) goto L_0x02b5;
    L_0x0124:
        r2 = r15.bp;
        r3 = r4 + 1;
        r2 = r2 + r4;
        r4 = r15.len;
        if (r2 < r4) goto L_0x014e;
    L_0x012d:
        r2 = 26;
    L_0x012f:
        r4 = 48;
        if (r2 < r4) goto L_0x015c;
    L_0x0133:
        r4 = 57;
        if (r2 > r4) goto L_0x015c;
    L_0x0137:
        r2 = r15.bp;
        r4 = r3 + 1;
        r2 = r2 + r3;
        r3 = r15.len;
        if (r2 < r3) goto L_0x0155;
    L_0x0140:
        r2 = 26;
    L_0x0142:
        r3 = r4;
        goto L_0x012f;
    L_0x0144:
        r4 = 0;
        r5 = r4;
        goto L_0x010f;
    L_0x0147:
        r3 = r15.text;
        r2 = r3.charAt(r2);
        goto L_0x011c;
    L_0x014e:
        r4 = r15.text;
        r2 = r4.charAt(r2);
        goto L_0x012f;
    L_0x0155:
        r3 = r15.text;
        r2 = r3.charAt(r2);
        goto L_0x0142;
    L_0x015c:
        r4 = r3;
        r3 = r2;
        r2 = r15.bp;
        r2 = r2 + r4;
        r2 = r2 - r11;
        r2 = r2 + -1;
        if (r5 != 0) goto L_0x0199;
    L_0x0166:
        r5 = 10;
        if (r2 >= r5) goto L_0x0199;
    L_0x016a:
        r12 = (double) r7;
        r6 = (double) r6;
        r6 = r12 / r6;
        if (r9 == 0) goto L_0x0171;
    L_0x0170:
        r6 = -r6;
    L_0x0171:
        r2 = r1.length;
        if (r0 < r2) goto L_0x02b2;
    L_0x0174:
        r2 = r1.length;
        r2 = r2 * 3;
        r2 = r2 / 2;
        r2 = new double[r2];
        r5 = 0;
        r9 = 0;
        java.lang.System.arraycopy(r1, r5, r2, r9, r0);
    L_0x0180:
        r1 = r0 + 1;
        r2[r0] = r6;
        r0 = 44;
        if (r3 != r0) goto L_0x01a9;
    L_0x0188:
        r0 = r15.bp;
        r3 = r4 + 1;
        r0 = r0 + r4;
        r4 = r15.len;
        if (r0 < r4) goto L_0x01a2;
    L_0x0191:
        r0 = 26;
    L_0x0193:
        r14 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r14;
        goto L_0x0053;
    L_0x0199:
        r2 = r15.subString(r11, r2);
        r6 = java.lang.Double.parseDouble(r2);
        goto L_0x0171;
    L_0x01a2:
        r4 = r15.text;
        r0 = r4.charAt(r0);
        goto L_0x0193;
    L_0x01a9:
        r0 = 93;
        if (r3 != r0) goto L_0x02ae;
    L_0x01ad:
        r0 = r15.bp;
        r5 = r4 + 1;
        r0 = r0 + r4;
        r3 = r15.len;
        if (r0 < r3) goto L_0x01ea;
    L_0x01b6:
        r0 = 26;
    L_0x01b8:
        r3 = r2.length;
        if (r1 == r3) goto L_0x01c3;
    L_0x01bb:
        r3 = new double[r1];
        r4 = 0;
        r6 = 0;
        java.lang.System.arraycopy(r2, r4, r3, r6, r1);
        r2 = r3;
    L_0x01c3:
        r3 = r8.length;
        if (r10 < r3) goto L_0x02ab;
    L_0x01c6:
        r3 = r8.length;
        r3 = r3 * 3;
        r3 = r3 / 2;
        r3 = new double[r3][];
        r4 = 0;
        r6 = 0;
        java.lang.System.arraycopy(r2, r4, r3, r6, r1);
        r1 = r3;
    L_0x01d3:
        r3 = r10 + 1;
        r1[r10] = r2;
        r2 = 44;
        if (r0 != r2) goto L_0x0200;
    L_0x01db:
        r0 = r15.bp;
        r2 = r5 + 1;
        r0 = r0 + r5;
        r4 = r15.len;
        if (r0 < r4) goto L_0x01f9;
    L_0x01e4:
        r0 = 26;
    L_0x01e6:
        r10 = r3;
        r8 = r1;
        goto L_0x003b;
    L_0x01ea:
        r3 = r15.text;
        r0 = r3.charAt(r0);
        goto L_0x01b8;
    L_0x01f1:
        r0 = -1;
        r15.matchStat = r0;
        r0 = 0;
        r0 = (double[][]) r0;
        goto L_0x000c;
    L_0x01f9:
        r4 = r15.text;
        r0 = r4.charAt(r0);
        goto L_0x01e6;
    L_0x0200:
        r2 = 93;
        if (r0 != r2) goto L_0x02a8;
    L_0x0204:
        r0 = r15.bp;
        r4 = r5 + 1;
        r0 = r0 + r5;
        r2 = r15.len;
        if (r0 < r2) goto L_0x0232;
    L_0x020d:
        r0 = 26;
    L_0x020f:
        r2 = r1.length;
        if (r3 == r2) goto L_0x021a;
    L_0x0212:
        r2 = new double[r3][];
        r5 = 0;
        r6 = 0;
        java.lang.System.arraycopy(r1, r5, r2, r6, r3);
        r1 = r2;
    L_0x021a:
        r2 = 44;
        if (r0 != r2) goto L_0x0239;
    L_0x021e:
        r0 = r15.bp;
        r2 = r4 + -1;
        r0 = r0 + r2;
        r15.bp = r0;
        r15.next();
        r0 = 3;
        r15.matchStat = r0;
        r0 = 16;
        r15.token = r0;
        r0 = r1;
        goto L_0x000c;
    L_0x0232:
        r2 = r15.text;
        r0 = r2.charAt(r0);
        goto L_0x020f;
    L_0x0239:
        r2 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r0 != r2) goto L_0x02a0;
    L_0x023d:
        r0 = r15.bp;
        r2 = r4 + 1;
        r0 = r0 + r4;
        r0 = r15.charAt(r0);
        r3 = 44;
        if (r0 != r3) goto L_0x025e;
    L_0x024a:
        r0 = 16;
        r15.token = r0;
        r0 = r15.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r15.bp = r0;
        r15.next();
    L_0x0258:
        r0 = 4;
        r15.matchStat = r0;
        r0 = r1;
        goto L_0x000c;
    L_0x025e:
        r3 = 93;
        if (r0 != r3) goto L_0x0271;
    L_0x0262:
        r0 = 15;
        r15.token = r0;
        r0 = r15.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r15.bp = r0;
        r15.next();
        goto L_0x0258;
    L_0x0271:
        r3 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r0 != r3) goto L_0x0284;
    L_0x0275:
        r0 = 13;
        r15.token = r0;
        r0 = r15.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r15.bp = r0;
        r15.next();
        goto L_0x0258;
    L_0x0284:
        r3 = 26;
        if (r0 != r3) goto L_0x0298;
    L_0x0288:
        r0 = r15.bp;
        r2 = r2 + -1;
        r0 = r0 + r2;
        r15.bp = r0;
        r0 = 20;
        r15.token = r0;
        r0 = 26;
        r15.ch = r0;
        goto L_0x0258;
    L_0x0298:
        r0 = -1;
        r15.matchStat = r0;
        r0 = 0;
        r0 = (double[][]) r0;
        goto L_0x000c;
    L_0x02a0:
        r0 = -1;
        r15.matchStat = r0;
        r0 = 0;
        r0 = (double[][]) r0;
        goto L_0x000c;
    L_0x02a8:
        r2 = r5;
        goto L_0x01e6;
    L_0x02ab:
        r1 = r8;
        goto L_0x01d3;
    L_0x02ae:
        r0 = r3;
        r3 = r4;
        goto L_0x0193;
    L_0x02b2:
        r2 = r1;
        goto L_0x0180;
    L_0x02b5:
        r3 = r4;
        goto L_0x012f;
    L_0x02b8:
        r6 = r3;
        r7 = r2;
        r2 = r5;
        r3 = r4;
        goto L_0x0105;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldDoubleArray2(long):double[][]");
    }

    public long scanFieldSymbol(long j) {
        char c = EOI;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j);
        if (matchFieldHash == 0) {
            return 0;
        }
        int i = matchFieldHash + 1;
        matchFieldHash += this.bp;
        if ((matchFieldHash >= this.len ? EOI : this.text.charAt(matchFieldHash)) != '\"') {
            this.matchStat = -1;
            return 0;
        }
        long j2 = -3750763034362895579L;
        int i2 = this.bp + i;
        while (true) {
            i2 = i + 1;
            i += this.bp;
            if (i >= this.len) {
                i = 26;
            } else {
                i = this.text.charAt(i);
            }
            if (i == 34) {
                break;
            }
            j2 = (j2 ^ ((long) i)) * 1099511628211L;
            if (i == 92) {
                this.matchStat = -1;
                return 0;
            }
            i = i2;
        }
        int i3 = i2 + 1;
        i = this.bp + i2;
        char charAt = i >= this.len ? EOI : this.text.charAt(i);
        if (charAt == ',') {
            this.bp += i3 - 1;
            i = this.bp + 1;
            this.bp = i;
            if (i < this.len) {
                c = this.text.charAt(i);
            }
            this.ch = c;
            this.matchStat = 3;
            return j2;
        } else if (charAt == '}') {
            i2 = i3 + 1;
            i = this.bp + i3;
            charAt = i >= this.len ? EOI : this.text.charAt(i);
            if (charAt == ',') {
                this.token = 16;
                this.bp += i2 - 1;
                next();
            } else if (charAt == ']') {
                this.token = 15;
                this.bp += i2 - 1;
                next();
            } else if (charAt == '}') {
                this.token = 13;
                this.bp += i2 - 1;
                next();
            } else if (charAt == EOI) {
                this.token = 20;
                this.bp += i2 - 1;
                this.ch = EOI;
            } else {
                this.matchStat = -1;
                return 0;
            }
            this.matchStat = 4;
            return j2;
        } else {
            this.matchStat = -1;
            return 0;
        }
    }

    public boolean scanISO8601DateIfMatch(boolean z) {
        return scanISO8601DateIfMatch(z, this.len - this.bp);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean scanISO8601DateIfMatch(boolean r24, int r25) {
        /*
        r23 = this;
        if (r24 != 0) goto L_0x00df;
    L_0x0002:
        r2 = 13;
        r0 = r25;
        if (r0 <= r2) goto L_0x00df;
    L_0x0008:
        r0 = r23;
        r2 = r0.bp;
        r0 = r23;
        r2 = r0.charAt(r2);
        r0 = r23;
        r3 = r0.bp;
        r3 = r3 + 1;
        r0 = r23;
        r3 = r0.charAt(r3);
        r0 = r23;
        r4 = r0.bp;
        r4 = r4 + 2;
        r0 = r23;
        r4 = r0.charAt(r4);
        r0 = r23;
        r5 = r0.bp;
        r5 = r5 + 3;
        r0 = r23;
        r5 = r0.charAt(r5);
        r0 = r23;
        r6 = r0.bp;
        r6 = r6 + 4;
        r0 = r23;
        r6 = r0.charAt(r6);
        r0 = r23;
        r7 = r0.bp;
        r7 = r7 + 5;
        r0 = r23;
        r7 = r0.charAt(r7);
        r0 = r23;
        r8 = r0.bp;
        r8 = r8 + r25;
        r8 = r8 + -1;
        r0 = r23;
        r8 = r0.charAt(r8);
        r0 = r23;
        r9 = r0.bp;
        r9 = r9 + r25;
        r9 = r9 + -2;
        r0 = r23;
        r9 = r0.charAt(r9);
        r10 = 47;
        if (r2 != r10) goto L_0x00df;
    L_0x006e:
        r2 = 68;
        if (r3 != r2) goto L_0x00df;
    L_0x0072:
        r2 = 97;
        if (r4 != r2) goto L_0x00df;
    L_0x0076:
        r2 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        if (r5 != r2) goto L_0x00df;
    L_0x007a:
        r2 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r6 != r2) goto L_0x00df;
    L_0x007e:
        r2 = 40;
        if (r7 != r2) goto L_0x00df;
    L_0x0082:
        r2 = 47;
        if (r8 != r2) goto L_0x00df;
    L_0x0086:
        r2 = 41;
        if (r9 != r2) goto L_0x00df;
    L_0x008a:
        r2 = -1;
        r3 = 6;
    L_0x008c:
        r0 = r25;
        if (r3 >= r0) goto L_0x00ab;
    L_0x0090:
        r0 = r23;
        r4 = r0.bp;
        r4 = r4 + r3;
        r0 = r23;
        r4 = r0.charAt(r4);
        r5 = 43;
        if (r4 != r5) goto L_0x00a3;
    L_0x009f:
        r2 = r3;
    L_0x00a0:
        r3 = r3 + 1;
        goto L_0x008c;
    L_0x00a3:
        r5 = 48;
        if (r4 < r5) goto L_0x00ab;
    L_0x00a7:
        r5 = 57;
        if (r4 <= r5) goto L_0x00a0;
    L_0x00ab:
        r3 = -1;
        if (r2 != r3) goto L_0x00b0;
    L_0x00ae:
        r2 = 0;
    L_0x00af:
        return r2;
    L_0x00b0:
        r0 = r23;
        r3 = r0.bp;
        r3 = r3 + 6;
        r2 = r2 - r3;
        r0 = r23;
        r2 = r0.subString(r3, r2);
        r2 = java.lang.Long.parseLong(r2);
        r0 = r23;
        r4 = r0.timeZone;
        r0 = r23;
        r5 = r0.locale;
        r4 = java.util.Calendar.getInstance(r4, r5);
        r0 = r23;
        r0.calendar = r4;
        r0 = r23;
        r4 = r0.calendar;
        r4.setTimeInMillis(r2);
        r2 = 5;
        r0 = r23;
        r0.token = r2;
        r2 = 1;
        goto L_0x00af;
    L_0x00df:
        r2 = 8;
        r0 = r25;
        if (r0 == r2) goto L_0x011b;
    L_0x00e5:
        r2 = 14;
        r0 = r25;
        if (r0 == r2) goto L_0x011b;
    L_0x00eb:
        r2 = 16;
        r0 = r25;
        if (r0 != r2) goto L_0x0105;
    L_0x00f1:
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + 10;
        r0 = r23;
        r2 = r0.charAt(r2);
        r3 = 84;
        if (r2 == r3) goto L_0x011b;
    L_0x0101:
        r3 = 32;
        if (r2 == r3) goto L_0x011b;
    L_0x0105:
        r2 = 17;
        r0 = r25;
        if (r0 != r2) goto L_0x0321;
    L_0x010b:
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + 6;
        r0 = r23;
        r2 = r0.charAt(r2);
        r3 = 45;
        if (r2 == r3) goto L_0x0321;
    L_0x011b:
        if (r24 == 0) goto L_0x011f;
    L_0x011d:
        r2 = 0;
        goto L_0x00af;
    L_0x011f:
        r0 = r23;
        r2 = r0.bp;
        r0 = r23;
        r2 = r0.charAt(r2);
        r0 = r23;
        r3 = r0.bp;
        r3 = r3 + 1;
        r0 = r23;
        r3 = r0.charAt(r3);
        r0 = r23;
        r4 = r0.bp;
        r4 = r4 + 2;
        r0 = r23;
        r4 = r0.charAt(r4);
        r0 = r23;
        r5 = r0.bp;
        r5 = r5 + 3;
        r0 = r23;
        r5 = r0.charAt(r5);
        r0 = r23;
        r6 = r0.bp;
        r6 = r6 + 4;
        r0 = r23;
        r6 = r0.charAt(r6);
        r0 = r23;
        r7 = r0.bp;
        r7 = r7 + 5;
        r0 = r23;
        r7 = r0.charAt(r7);
        r0 = r23;
        r8 = r0.bp;
        r8 = r8 + 6;
        r0 = r23;
        r8 = r0.charAt(r8);
        r0 = r23;
        r9 = r0.bp;
        r9 = r9 + 7;
        r0 = r23;
        r9 = r0.charAt(r9);
        r0 = r23;
        r10 = r0.bp;
        r10 = r10 + 8;
        r0 = r23;
        r19 = r0.charAt(r10);
        r10 = 45;
        if (r6 != r10) goto L_0x01c6;
    L_0x018d:
        r10 = 45;
        if (r9 != r10) goto L_0x01c6;
    L_0x0191:
        r10 = 1;
        r11 = r10;
    L_0x0193:
        if (r11 == 0) goto L_0x01c9;
    L_0x0195:
        r10 = 16;
        r0 = r25;
        if (r0 != r10) goto L_0x01c9;
    L_0x019b:
        r10 = 1;
        r21 = r10;
    L_0x019e:
        if (r11 == 0) goto L_0x01cd;
    L_0x01a0:
        r10 = 17;
        r0 = r25;
        if (r0 != r10) goto L_0x01cd;
    L_0x01a6:
        r10 = 1;
        r20 = r10;
    L_0x01a9:
        if (r20 != 0) goto L_0x01ad;
    L_0x01ab:
        if (r21 == 0) goto L_0x01bd;
    L_0x01ad:
        r0 = r23;
        r6 = r0.bp;
        r6 = r6 + 9;
        r0 = r23;
        r9 = r0.charAt(r6);
        r6 = r7;
        r7 = r8;
        r8 = r19;
    L_0x01bd:
        r10 = checkDate(r2, r3, r4, r5, r6, r7, r8, r9);
        if (r10 != 0) goto L_0x01d1;
    L_0x01c3:
        r2 = 0;
        goto L_0x00af;
    L_0x01c6:
        r10 = 0;
        r11 = r10;
        goto L_0x0193;
    L_0x01c9:
        r10 = 0;
        r21 = r10;
        goto L_0x019e;
    L_0x01cd:
        r10 = 0;
        r20 = r10;
        goto L_0x01a9;
    L_0x01d1:
        r10 = r23;
        r11 = r2;
        r12 = r3;
        r13 = r4;
        r14 = r5;
        r15 = r6;
        r16 = r7;
        r17 = r8;
        r18 = r9;
        r10.setCalendar(r11, r12, r13, r14, r15, r16, r17, r18);
        r2 = 8;
        r0 = r25;
        if (r0 == r2) goto L_0x031c;
    L_0x01e7:
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + 9;
        r0 = r23;
        r5 = r0.charAt(r2);
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + 10;
        r0 = r23;
        r4 = r0.charAt(r2);
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + 11;
        r0 = r23;
        r3 = r0.charAt(r2);
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + 12;
        r0 = r23;
        r2 = r0.charAt(r2);
        r0 = r23;
        r6 = r0.bp;
        r6 = r6 + 13;
        r0 = r23;
        r7 = r0.charAt(r6);
        if (r20 == 0) goto L_0x023d;
    L_0x0225:
        r6 = 84;
        if (r4 != r6) goto L_0x023d;
    L_0x0229:
        r6 = 58;
        if (r7 != r6) goto L_0x023d;
    L_0x022d:
        r0 = r23;
        r6 = r0.bp;
        r6 = r6 + 16;
        r0 = r23;
        r6 = r0.charAt(r6);
        r8 = 90;
        if (r6 == r8) goto L_0x024b;
    L_0x023d:
        if (r21 == 0) goto L_0x0275;
    L_0x023f:
        r6 = 32;
        if (r4 == r6) goto L_0x0247;
    L_0x0243:
        r6 = 84;
        if (r4 != r6) goto L_0x0275;
    L_0x0247:
        r6 = 58;
        if (r7 != r6) goto L_0x0275;
    L_0x024b:
        r0 = r23;
        r4 = r0.bp;
        r4 = r4 + 14;
        r0 = r23;
        r4 = r0.charAt(r4);
        r0 = r23;
        r5 = r0.bp;
        r5 = r5 + 15;
        r0 = r23;
        r5 = r0.charAt(r5);
        r6 = 48;
        r7 = 48;
        r22 = r2;
        r2 = r3;
        r3 = r22;
    L_0x026c:
        r8 = checkTime(r2, r3, r4, r5, r6, r7);
        if (r8 != 0) goto L_0x027e;
    L_0x0272:
        r2 = 0;
        goto L_0x00af;
    L_0x0275:
        r6 = r2;
        r2 = r19;
        r22 = r5;
        r5 = r3;
        r3 = r22;
        goto L_0x026c;
    L_0x027e:
        r8 = 17;
        r0 = r25;
        if (r0 != r8) goto L_0x031a;
    L_0x0284:
        if (r20 != 0) goto L_0x031a;
    L_0x0286:
        r0 = r23;
        r8 = r0.bp;
        r8 = r8 + 14;
        r0 = r23;
        r8 = r0.charAt(r8);
        r0 = r23;
        r9 = r0.bp;
        r9 = r9 + 15;
        r0 = r23;
        r9 = r0.charAt(r9);
        r0 = r23;
        r10 = r0.bp;
        r10 = r10 + 16;
        r0 = r23;
        r10 = r0.charAt(r10);
        r11 = 48;
        if (r8 < r11) goto L_0x02b2;
    L_0x02ae:
        r11 = 57;
        if (r8 <= r11) goto L_0x02b5;
    L_0x02b2:
        r2 = 0;
        goto L_0x00af;
    L_0x02b5:
        r11 = 48;
        if (r9 < r11) goto L_0x02bd;
    L_0x02b9:
        r11 = 57;
        if (r9 <= r11) goto L_0x02c0;
    L_0x02bd:
        r2 = 0;
        goto L_0x00af;
    L_0x02c0:
        r11 = 48;
        if (r10 < r11) goto L_0x02c8;
    L_0x02c4:
        r11 = 57;
        if (r10 <= r11) goto L_0x02cb;
    L_0x02c8:
        r2 = 0;
        goto L_0x00af;
    L_0x02cb:
        r8 = r8 + -48;
        r8 = r8 * 100;
        r9 = r9 + -48;
        r9 = r9 * 10;
        r8 = r8 + r9;
        r9 = r10 + -48;
        r8 = r8 + r9;
    L_0x02d7:
        r2 = r2 + -48;
        r2 = r2 * 10;
        r3 = r3 + -48;
        r9 = r2 + r3;
        r2 = r4 + -48;
        r2 = r2 * 10;
        r3 = r5 + -48;
        r3 = r3 + r2;
        r2 = r6 + -48;
        r2 = r2 * 10;
        r4 = r7 + -48;
        r2 = r2 + r4;
        r4 = r9;
    L_0x02ee:
        r0 = r23;
        r5 = r0.calendar;
        r6 = 11;
        r5.set(r6, r4);
        r0 = r23;
        r4 = r0.calendar;
        r5 = 12;
        r4.set(r5, r3);
        r0 = r23;
        r3 = r0.calendar;
        r4 = 13;
        r3.set(r4, r2);
        r0 = r23;
        r2 = r0.calendar;
        r3 = 14;
        r2.set(r3, r8);
        r2 = 5;
        r0 = r23;
        r0.token = r2;
        r2 = 1;
        goto L_0x00af;
    L_0x031a:
        r8 = 0;
        goto L_0x02d7;
    L_0x031c:
        r4 = 0;
        r3 = 0;
        r2 = 0;
        r8 = 0;
        goto L_0x02ee;
    L_0x0321:
        r2 = 9;
        r0 = r25;
        if (r0 >= r2) goto L_0x032a;
    L_0x0327:
        r2 = 0;
        goto L_0x00af;
    L_0x032a:
        r0 = r23;
        r2 = r0.bp;
        r0 = r23;
        r8 = r0.charAt(r2);
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + 1;
        r0 = r23;
        r4 = r0.charAt(r2);
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + 2;
        r0 = r23;
        r11 = r0.charAt(r2);
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + 3;
        r0 = r23;
        r10 = r0.charAt(r2);
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + 4;
        r0 = r23;
        r6 = r0.charAt(r2);
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + 5;
        r0 = r23;
        r5 = r0.charAt(r2);
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + 6;
        r0 = r23;
        r7 = r0.charAt(r2);
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + 7;
        r0 = r23;
        r2 = r0.charAt(r2);
        r0 = r23;
        r3 = r0.bp;
        r3 = r3 + 8;
        r0 = r23;
        r3 = r0.charAt(r3);
        r0 = r23;
        r9 = r0.bp;
        r9 = r9 + 9;
        r0 = r23;
        r9 = r0.charAt(r9);
        r12 = 10;
        r13 = 45;
        if (r6 != r13) goto L_0x03aa;
    L_0x03a6:
        r13 = 45;
        if (r2 == r13) goto L_0x03b2;
    L_0x03aa:
        r13 = 47;
        if (r6 != r13) goto L_0x03c3;
    L_0x03ae:
        r13 = 47;
        if (r2 != r13) goto L_0x03c3;
    L_0x03b2:
        r6 = r5;
        r2 = r8;
        r19 = r12;
        r8 = r3;
        r5 = r10;
        r3 = r4;
        r4 = r11;
    L_0x03ba:
        r10 = checkDate(r2, r3, r4, r5, r6, r7, r8, r9);
        if (r10 != 0) goto L_0x04a3;
    L_0x03c0:
        r2 = 0;
        goto L_0x00af;
    L_0x03c3:
        r13 = 45;
        if (r6 != r13) goto L_0x03ee;
    L_0x03c7:
        r13 = 45;
        if (r7 != r13) goto L_0x03ee;
    L_0x03cb:
        r6 = 48;
        r7 = 32;
        if (r3 != r7) goto L_0x03df;
    L_0x03d1:
        r3 = 48;
        r7 = 8;
        r9 = r2;
        r19 = r7;
        r7 = r5;
        r2 = r8;
        r5 = r10;
        r8 = r3;
        r3 = r4;
        r4 = r11;
        goto L_0x03ba;
    L_0x03df:
        r7 = 9;
        r9 = r3;
        r19 = r7;
        r7 = r5;
        r3 = r4;
        r4 = r11;
        r5 = r10;
        r22 = r8;
        r8 = r2;
        r2 = r22;
        goto L_0x03ba;
    L_0x03ee:
        r13 = 46;
        if (r11 != r13) goto L_0x03f6;
    L_0x03f2:
        r13 = 46;
        if (r5 == r13) goto L_0x03fe;
    L_0x03f6:
        r13 = 45;
        if (r11 != r13) goto L_0x0408;
    L_0x03fa:
        r13 = 45;
        if (r5 != r13) goto L_0x0408;
    L_0x03fe:
        r5 = r9;
        r19 = r12;
        r9 = r4;
        r4 = r3;
        r3 = r2;
        r2 = r7;
        r7 = r6;
        r6 = r10;
        goto L_0x03ba;
    L_0x0408:
        r13 = 24180; // 0x5e74 float:3.3883E-41 double:1.19465E-319;
        if (r6 == r13) goto L_0x0411;
    L_0x040c:
        r13 = 45380; // 0xb144 float:6.3591E-41 double:2.24207E-319;
        if (r6 != r13) goto L_0x04a0;
    L_0x0411:
        r6 = 26376; // 0x6708 float:3.696E-41 double:1.30315E-319;
        if (r2 == r6) goto L_0x041a;
    L_0x0415:
        r6 = 50900; // 0xc6d4 float:7.1326E-41 double:2.5148E-319;
        if (r2 != r6) goto L_0x0462;
    L_0x041a:
        r2 = 26085; // 0x65e5 float:3.6553E-41 double:1.28877E-319;
        if (r9 == r2) goto L_0x0423;
    L_0x041e:
        r2 = 51068; // 0xc77c float:7.1562E-41 double:2.5231E-319;
        if (r9 != r2) goto L_0x0432;
    L_0x0423:
        r2 = 48;
        r9 = r3;
        r6 = r5;
        r19 = r12;
        r5 = r10;
        r3 = r4;
        r4 = r11;
        r22 = r8;
        r8 = r2;
        r2 = r22;
        goto L_0x03ba;
    L_0x0432:
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + 10;
        r0 = r23;
        r2 = r0.charAt(r2);
        r6 = 26085; // 0x65e5 float:3.6553E-41 double:1.28877E-319;
        if (r2 == r6) goto L_0x0453;
    L_0x0442:
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + 10;
        r0 = r23;
        r2 = r0.charAt(r2);
        r6 = 51068; // 0xc77c float:7.1562E-41 double:2.5231E-319;
        if (r2 != r6) goto L_0x045f;
    L_0x0453:
        r2 = 11;
        r6 = r5;
        r19 = r2;
        r5 = r10;
        r2 = r8;
        r8 = r3;
        r3 = r4;
        r4 = r11;
        goto L_0x03ba;
    L_0x045f:
        r2 = 0;
        goto L_0x00af;
    L_0x0462:
        r6 = 26376; // 0x6708 float:3.696E-41 double:1.30315E-319;
        if (r7 == r6) goto L_0x046b;
    L_0x0466:
        r6 = 50900; // 0xc6d4 float:7.1326E-41 double:2.5148E-319;
        if (r7 != r6) goto L_0x049d;
    L_0x046b:
        r6 = 48;
        r7 = 26085; // 0x65e5 float:3.6553E-41 double:1.28877E-319;
        if (r3 == r7) goto L_0x0476;
    L_0x0471:
        r7 = 51068; // 0xc77c float:7.1562E-41 double:2.5231E-319;
        if (r3 != r7) goto L_0x0483;
    L_0x0476:
        r3 = 48;
        r9 = r2;
        r7 = r5;
        r19 = r12;
        r5 = r10;
        r2 = r8;
        r8 = r3;
        r3 = r4;
        r4 = r11;
        goto L_0x03ba;
    L_0x0483:
        r7 = 26085; // 0x65e5 float:3.6553E-41 double:1.28877E-319;
        if (r9 == r7) goto L_0x048c;
    L_0x0487:
        r7 = 51068; // 0xc77c float:7.1562E-41 double:2.5231E-319;
        if (r9 != r7) goto L_0x049a;
    L_0x048c:
        r9 = r3;
        r7 = r5;
        r19 = r12;
        r5 = r10;
        r3 = r4;
        r4 = r11;
        r22 = r8;
        r8 = r2;
        r2 = r22;
        goto L_0x03ba;
    L_0x049a:
        r2 = 0;
        goto L_0x00af;
    L_0x049d:
        r2 = 0;
        goto L_0x00af;
    L_0x04a0:
        r2 = 0;
        goto L_0x00af;
    L_0x04a3:
        r10 = r23;
        r11 = r2;
        r12 = r3;
        r13 = r4;
        r14 = r5;
        r15 = r6;
        r16 = r7;
        r17 = r8;
        r18 = r9;
        r10.setCalendar(r11, r12, r13, r14, r15, r16, r17, r18);
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + r19;
        r0 = r23;
        r9 = r0.charAt(r2);
        r2 = 84;
        if (r9 == r2) goto L_0x04c9;
    L_0x04c3:
        r2 = 32;
        if (r9 != r2) goto L_0x04d2;
    L_0x04c7:
        if (r24 != 0) goto L_0x04d2;
    L_0x04c9:
        r2 = r19 + 9;
        r0 = r25;
        if (r0 >= r2) goto L_0x05b5;
    L_0x04cf:
        r2 = 0;
        goto L_0x00af;
    L_0x04d2:
        r2 = 34;
        if (r9 == r2) goto L_0x04e3;
    L_0x04d6:
        r2 = 26;
        if (r9 == r2) goto L_0x04e3;
    L_0x04da:
        r2 = 26085; // 0x65e5 float:3.6553E-41 double:1.28877E-319;
        if (r9 == r2) goto L_0x04e3;
    L_0x04de:
        r2 = 51068; // 0xc77c float:7.1562E-41 double:2.5231E-319;
        if (r9 != r2) goto L_0x0527;
    L_0x04e3:
        r0 = r23;
        r2 = r0.calendar;
        r3 = 11;
        r4 = 0;
        r2.set(r3, r4);
        r0 = r23;
        r2 = r0.calendar;
        r3 = 12;
        r4 = 0;
        r2.set(r3, r4);
        r0 = r23;
        r2 = r0.calendar;
        r3 = 13;
        r4 = 0;
        r2.set(r3, r4);
        r0 = r23;
        r2 = r0.calendar;
        r3 = 14;
        r4 = 0;
        r2.set(r3, r4);
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + r19;
        r0 = r23;
        r0.bp = r2;
        r0 = r23;
        r2 = r0.charAt(r2);
        r0 = r23;
        r0.ch = r2;
        r2 = 5;
        r0 = r23;
        r0.token = r2;
        r2 = 1;
        goto L_0x00af;
    L_0x0527:
        r2 = 43;
        if (r9 == r2) goto L_0x052f;
    L_0x052b:
        r2 = 45;
        if (r9 != r2) goto L_0x05b2;
    L_0x052f:
        r0 = r23;
        r2 = r0.len;
        r3 = r19 + 6;
        if (r2 != r3) goto L_0x05af;
    L_0x0537:
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + r19;
        r2 = r2 + 3;
        r0 = r23;
        r2 = r0.charAt(r2);
        r3 = 58;
        if (r2 != r3) goto L_0x056d;
    L_0x0549:
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + r19;
        r2 = r2 + 4;
        r0 = r23;
        r2 = r0.charAt(r2);
        r3 = 48;
        if (r2 != r3) goto L_0x056d;
    L_0x055b:
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + r19;
        r2 = r2 + 5;
        r0 = r23;
        r2 = r0.charAt(r2);
        r3 = 48;
        if (r2 == r3) goto L_0x0570;
    L_0x056d:
        r2 = 0;
        goto L_0x00af;
    L_0x0570:
        r3 = 48;
        r4 = 48;
        r5 = 48;
        r6 = 48;
        r7 = 48;
        r8 = 48;
        r2 = r23;
        r2.setTime(r3, r4, r5, r6, r7, r8);
        r0 = r23;
        r2 = r0.calendar;
        r3 = 14;
        r4 = 0;
        r2.set(r3, r4);
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + r19;
        r2 = r2 + 1;
        r0 = r23;
        r2 = r0.charAt(r2);
        r0 = r23;
        r3 = r0.bp;
        r3 = r3 + r19;
        r3 = r3 + 2;
        r0 = r23;
        r3 = r0.charAt(r3);
        r0 = r23;
        r0.setTimeZone(r9, r2, r3);
        r2 = 1;
        goto L_0x00af;
    L_0x05af:
        r2 = 0;
        goto L_0x00af;
    L_0x05b2:
        r2 = 0;
        goto L_0x00af;
    L_0x05b5:
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + r19;
        r2 = r2 + 3;
        r0 = r23;
        r2 = r0.charAt(r2);
        r3 = 58;
        if (r2 == r3) goto L_0x05ca;
    L_0x05c7:
        r2 = 0;
        goto L_0x00af;
    L_0x05ca:
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + r19;
        r2 = r2 + 6;
        r0 = r23;
        r2 = r0.charAt(r2);
        r3 = 58;
        if (r2 == r3) goto L_0x05df;
    L_0x05dc:
        r2 = 0;
        goto L_0x00af;
    L_0x05df:
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + r19;
        r2 = r2 + 1;
        r0 = r23;
        r2 = r0.charAt(r2);
        r0 = r23;
        r3 = r0.bp;
        r3 = r3 + r19;
        r3 = r3 + 2;
        r0 = r23;
        r3 = r0.charAt(r3);
        r0 = r23;
        r4 = r0.bp;
        r4 = r4 + r19;
        r4 = r4 + 4;
        r0 = r23;
        r4 = r0.charAt(r4);
        r0 = r23;
        r5 = r0.bp;
        r5 = r5 + r19;
        r5 = r5 + 5;
        r0 = r23;
        r5 = r0.charAt(r5);
        r0 = r23;
        r6 = r0.bp;
        r6 = r6 + r19;
        r6 = r6 + 7;
        r0 = r23;
        r6 = r0.charAt(r6);
        r0 = r23;
        r7 = r0.bp;
        r7 = r7 + r19;
        r7 = r7 + 8;
        r0 = r23;
        r7 = r0.charAt(r7);
        r8 = checkTime(r2, r3, r4, r5, r6, r7);
        if (r8 != 0) goto L_0x063c;
    L_0x0639:
        r2 = 0;
        goto L_0x00af;
    L_0x063c:
        r8 = r23;
        r9 = r2;
        r10 = r3;
        r11 = r4;
        r12 = r5;
        r13 = r6;
        r14 = r7;
        r8.setTime(r9, r10, r11, r12, r13, r14);
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + r19;
        r2 = r2 + 9;
        r0 = r23;
        r2 = r0.charAt(r2);
        r3 = 46;
        if (r2 != r3) goto L_0x0662;
    L_0x0659:
        r2 = r19 + 11;
        r0 = r25;
        if (r0 >= r2) goto L_0x06b1;
    L_0x065f:
        r2 = 0;
        goto L_0x00af;
    L_0x0662:
        r0 = r23;
        r3 = r0.calendar;
        r4 = 14;
        r5 = 0;
        r3.set(r4, r5);
        r0 = r23;
        r3 = r0.bp;
        r4 = r19 + 9;
        r3 = r3 + r4;
        r0 = r23;
        r0.bp = r3;
        r0 = r23;
        r3 = r0.charAt(r3);
        r0 = r23;
        r0.ch = r3;
        r3 = 5;
        r0 = r23;
        r0.token = r3;
        r3 = 90;
        if (r2 != r3) goto L_0x06ae;
    L_0x068a:
        r0 = r23;
        r2 = r0.calendar;
        r2 = r2.getTimeZone();
        r2 = r2.getRawOffset();
        if (r2 == 0) goto L_0x06ae;
    L_0x0698:
        r2 = 0;
        r2 = java.util.TimeZone.getAvailableIDs(r2);
        r3 = r2.length;
        if (r3 <= 0) goto L_0x06ae;
    L_0x06a0:
        r3 = 0;
        r2 = r2[r3];
        r2 = java.util.TimeZone.getTimeZone(r2);
        r0 = r23;
        r3 = r0.calendar;
        r3.setTimeZone(r2);
    L_0x06ae:
        r2 = 1;
        goto L_0x00af;
    L_0x06b1:
        r0 = r23;
        r2 = r0.bp;
        r2 = r2 + r19;
        r2 = r2 + 10;
        r0 = r23;
        r2 = r0.charAt(r2);
        r3 = 48;
        if (r2 < r3) goto L_0x06c7;
    L_0x06c3:
        r3 = 57;
        if (r2 <= r3) goto L_0x06ca;
    L_0x06c7:
        r2 = 0;
        goto L_0x00af;
    L_0x06ca:
        r3 = r2 + -48;
        r2 = 1;
        r4 = r19 + 11;
        r0 = r25;
        if (r0 <= r4) goto L_0x06ef;
    L_0x06d3:
        r0 = r23;
        r4 = r0.bp;
        r4 = r4 + r19;
        r4 = r4 + 11;
        r0 = r23;
        r4 = r0.charAt(r4);
        r5 = 48;
        if (r4 < r5) goto L_0x06ef;
    L_0x06e5:
        r5 = 57;
        if (r4 > r5) goto L_0x06ef;
    L_0x06e9:
        r2 = r3 * 10;
        r3 = r4 + -48;
        r3 = r3 + r2;
        r2 = 2;
    L_0x06ef:
        r4 = 2;
        if (r2 != r4) goto L_0x070e;
    L_0x06f2:
        r0 = r23;
        r4 = r0.bp;
        r4 = r4 + r19;
        r4 = r4 + 12;
        r0 = r23;
        r4 = r0.charAt(r4);
        r5 = 48;
        if (r4 < r5) goto L_0x070e;
    L_0x0704:
        r5 = 57;
        if (r4 > r5) goto L_0x070e;
    L_0x0708:
        r2 = r3 * 10;
        r3 = r4 + -48;
        r3 = r3 + r2;
        r2 = 3;
    L_0x070e:
        r0 = r23;
        r4 = r0.calendar;
        r5 = 14;
        r4.set(r5, r3);
        r3 = 0;
        r0 = r23;
        r4 = r0.bp;
        r4 = r4 + r19;
        r4 = r4 + 10;
        r4 = r4 + r2;
        r0 = r23;
        r4 = r0.charAt(r4);
        r5 = 43;
        if (r4 == r5) goto L_0x072f;
    L_0x072b:
        r5 = 45;
        if (r4 != r5) goto L_0x07ec;
    L_0x072f:
        r0 = r23;
        r3 = r0.bp;
        r3 = r3 + r19;
        r3 = r3 + 10;
        r3 = r3 + r2;
        r3 = r3 + 1;
        r0 = r23;
        r5 = r0.charAt(r3);
        r3 = 48;
        if (r5 < r3) goto L_0x0748;
    L_0x0744:
        r3 = 49;
        if (r5 <= r3) goto L_0x074b;
    L_0x0748:
        r2 = 0;
        goto L_0x00af;
    L_0x074b:
        r0 = r23;
        r3 = r0.bp;
        r3 = r3 + r19;
        r3 = r3 + 10;
        r3 = r3 + r2;
        r3 = r3 + 2;
        r0 = r23;
        r6 = r0.charAt(r3);
        r3 = 48;
        if (r6 < r3) goto L_0x0764;
    L_0x0760:
        r3 = 57;
        if (r6 <= r3) goto L_0x0767;
    L_0x0764:
        r2 = 0;
        goto L_0x00af;
    L_0x0767:
        r0 = r23;
        r3 = r0.bp;
        r3 = r3 + r19;
        r3 = r3 + 10;
        r3 = r3 + r2;
        r3 = r3 + 3;
        r0 = r23;
        r3 = r0.charAt(r3);
        r7 = 58;
        if (r3 != r7) goto L_0x07cc;
    L_0x077c:
        r0 = r23;
        r3 = r0.bp;
        r3 = r3 + r19;
        r3 = r3 + 10;
        r3 = r3 + r2;
        r3 = r3 + 4;
        r0 = r23;
        r3 = r0.charAt(r3);
        r7 = 48;
        if (r3 == r7) goto L_0x0794;
    L_0x0791:
        r2 = 0;
        goto L_0x00af;
    L_0x0794:
        r0 = r23;
        r3 = r0.bp;
        r3 = r3 + r19;
        r3 = r3 + 10;
        r3 = r3 + r2;
        r3 = r3 + 5;
        r0 = r23;
        r3 = r0.charAt(r3);
        r7 = 48;
        if (r3 == r7) goto L_0x07ac;
    L_0x07a9:
        r2 = 0;
        goto L_0x00af;
    L_0x07ac:
        r3 = 6;
    L_0x07ad:
        r0 = r23;
        r0.setTimeZone(r4, r5, r6);
    L_0x07b2:
        r0 = r23;
        r4 = r0.bp;
        r5 = r19 + 10;
        r5 = r5 + r2;
        r5 = r5 + r3;
        r4 = r4 + r5;
        r0 = r23;
        r4 = r0.charAt(r4);
        r5 = 26;
        if (r4 == r5) goto L_0x0816;
    L_0x07c5:
        r5 = 34;
        if (r4 == r5) goto L_0x0816;
    L_0x07c9:
        r2 = 0;
        goto L_0x00af;
    L_0x07cc:
        r7 = 48;
        if (r3 != r7) goto L_0x07ea;
    L_0x07d0:
        r0 = r23;
        r3 = r0.bp;
        r3 = r3 + r19;
        r3 = r3 + 10;
        r3 = r3 + r2;
        r3 = r3 + 4;
        r0 = r23;
        r3 = r0.charAt(r3);
        r7 = 48;
        if (r3 == r7) goto L_0x07e8;
    L_0x07e5:
        r2 = 0;
        goto L_0x00af;
    L_0x07e8:
        r3 = 5;
        goto L_0x07ad;
    L_0x07ea:
        r3 = 3;
        goto L_0x07ad;
    L_0x07ec:
        r5 = 90;
        if (r4 != r5) goto L_0x07b2;
    L_0x07f0:
        r3 = 1;
        r0 = r23;
        r4 = r0.calendar;
        r4 = r4.getTimeZone();
        r4 = r4.getRawOffset();
        if (r4 == 0) goto L_0x07b2;
    L_0x07ff:
        r4 = 0;
        r4 = java.util.TimeZone.getAvailableIDs(r4);
        r5 = r4.length;
        if (r5 <= 0) goto L_0x07b2;
    L_0x0807:
        r5 = 0;
        r4 = r4[r5];
        r4 = java.util.TimeZone.getTimeZone(r4);
        r0 = r23;
        r5 = r0.calendar;
        r5.setTimeZone(r4);
        goto L_0x07b2;
    L_0x0816:
        r0 = r23;
        r4 = r0.bp;
        r5 = r19 + 10;
        r2 = r2 + r5;
        r2 = r2 + r3;
        r2 = r2 + r4;
        r0 = r23;
        r0.bp = r2;
        r0 = r23;
        r2 = r0.charAt(r2);
        r0 = r23;
        r0.ch = r2;
        r2 = 5;
        r0 = r23;
        r0.token = r2;
        r2 = 1;
        goto L_0x00af;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanISO8601DateIfMatch(boolean, int):boolean");
    }

    protected void setTime(char c, char c2, char c3, char c4, char c5, char c6) {
        int i = ((c3 - 48) * 10) + (c4 - 48);
        int i2 = ((c5 - 48) * 10) + (c6 - 48);
        this.calendar.set(11, ((c - 48) * 10) + (c2 - 48));
        this.calendar.set(12, i);
        this.calendar.set(13, i2);
    }

    protected void setTimeZone(char c, char c2, char c3) {
        int i = ((((c2 - 48) * 10) + (c3 - 48)) * 3600) * 1000;
        if (c == '-') {
            i = -i;
        }
        if (this.calendar.getTimeZone().getRawOffset() != i) {
            String[] availableIDs = TimeZone.getAvailableIDs(i);
            if (availableIDs.length > 0) {
                this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
            }
        }
    }

    static boolean checkTime(char c, char c2, char c3, char c4, char c5, char c6) {
        if (c == '0') {
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        } else if (c == '1') {
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        } else if (c != '2' || c2 < '0') {
            return false;
        } else {
            if (c2 > '4') {
                return false;
            }
        }
        if (c3 < '0' || c3 > '5') {
            if (c3 != '6') {
                return false;
            }
            if (c4 != '0') {
                return false;
            }
        } else if (c4 < '0' || c4 > '9') {
            return false;
        }
        if (c5 < '0' || c5 > '5') {
            if (c5 != '6') {
                return false;
            }
            if (c6 != '0') {
                return false;
            }
        } else if (c6 < '0' || c6 > '9') {
            return false;
        }
        return true;
    }

    private void setCalendar(char c, char c2, char c3, char c4, char c5, char c6, char c7, char c8) {
        this.calendar = Calendar.getInstance(this.timeZone, this.locale);
        int i = (((c5 - 48) * 10) + (c6 - 48)) - 1;
        int i2 = ((c7 - 48) * 10) + (c8 - 48);
        this.calendar.set(1, ((((c - 48) * 1000) + ((c2 - 48) * 100)) + ((c3 - 48) * 10)) + (c4 - 48));
        this.calendar.set(2, i);
        this.calendar.set(5, i2);
    }

    static boolean checkDate(char c, char c2, char c3, char c4, char c5, char c6, int i, int i2) {
        if (c < '1' || c > '3' || c2 < '0' || c2 > '9' || c3 < '0' || c3 > '9' || c4 < '0' || c4 > '9') {
            return false;
        }
        if (c5 == '0') {
            if (c6 < '1' || c6 > '9') {
                return false;
            }
        } else if (c5 != '1') {
            return false;
        } else {
            if (!(c6 == '0' || c6 == '1' || c6 == '2')) {
                return false;
            }
        }
        if (i == 48) {
            if (i2 < 49 || i2 > 57) {
                return false;
            }
        } else if (i == 49 || i == 50) {
            if (i2 < 48) {
                return false;
            }
            if (i2 > 57) {
                return false;
            }
        } else if (i != 51) {
            return false;
        } else {
            if (!(i2 == 48 || i2 == 49)) {
                return false;
            }
        }
        return true;
    }

    public static final byte[] decodeFast(String str, int i, int i2) {
        int i3 = 0;
        if (i2 == 0) {
            return new byte[0];
        }
        int i4 = (i + i2) - 1;
        int i5 = i;
        while (i5 < i4 && IA[str.charAt(i5)] < 0) {
            i5++;
        }
        int i6 = i4;
        while (i6 > 0 && IA[str.charAt(i6)] < 0) {
            i6--;
        }
        int i7 = str.charAt(i6) == '=' ? str.charAt(i6 + -1) == '=' ? 2 : 1 : 0;
        int i8 = (i6 - i5) + 1;
        if (i2 > 76) {
            if (str.charAt(76) == '\r') {
                i4 = i8 / 78;
            } else {
                i4 = 0;
            }
            i4 <<= 1;
        } else {
            i4 = 0;
        }
        int i9 = (((i8 - i4) * 6) >> 3) - i7;
        byte[] bArr = new byte[i9];
        int i10 = (i9 / 3) * 3;
        i8 = 0;
        int i11 = 0;
        while (i11 < i10) {
            int i12 = i5 + 1;
            int i13 = i12 + 1;
            i5 = (IA[str.charAt(i5)] << 18) | (IA[str.charAt(i12)] << 12);
            i12 = i13 + 1;
            int i14 = (IA[str.charAt(i13)] << 6) | i5;
            i5 = i12 + 1;
            i14 |= IA[str.charAt(i12)];
            i12 = i11 + 1;
            bArr[i11] = (byte) (i14 >> 16);
            i13 = i12 + 1;
            bArr[i12] = (byte) (i14 >> 8);
            i11 = i13 + 1;
            bArr[i13] = (byte) i14;
            if (i4 > 0) {
                i8++;
                if (i8 == 19) {
                    i5 += 2;
                    i8 = 0;
                }
            }
        }
        if (i11 < i9) {
            i5 = 0;
            for (i4 = i5; i4 <= i6 - i7; i4++) {
                i3++;
                i5 = (IA[str.charAt(i4)] << (18 - (i3 * 6))) | i5;
            }
            i4 = 16;
            i3 = i11;
            while (i3 < i9) {
                i8 = i3 + 1;
                bArr[i3] = (byte) (i5 >> i4);
                i4 -= 8;
                i3 = i8;
            }
        }
        return bArr;
    }
}
