package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.IOUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.TimeZone;

public final class JSONScanner extends JSONLexerBase {
    public final int ISO8601_LEN_0;
    public final int ISO8601_LEN_1;
    public final int ISO8601_LEN_2;
    private final int len;
    private final String text;

    public JSONScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(String str, int i) {
        super(i);
        this.ISO8601_LEN_0 = "0000-00-00".length();
        this.ISO8601_LEN_1 = "0000-00-00T00:00:00".length();
        this.ISO8601_LEN_2 = "0000-00-00T00:00:00.000".length();
        this.text = str;
        this.len = this.text.length();
        this.bp = -1;
        next();
        if (this.ch == '﻿') {
            next();
        }
    }

    public final char charAt(int i) {
        if (i >= this.len) {
            return '\u001a';
        }
        return this.text.charAt(i);
    }

    public final char next() {
        int i = this.bp + 1;
        this.bp = i;
        char charAt = charAt(i);
        this.ch = charAt;
        return charAt;
    }

    public JSONScanner(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(char[] cArr, int i, int i2) {
        this(new String(cArr, 0, i), i2);
    }

    protected final void copyTo(int i, int i2, char[] cArr) {
        this.text.getChars(i, i + i2, cArr, 0);
    }

    static boolean charArrayCompare(String str, int i, char[] cArr) {
        int length = cArr.length;
        if (length + i > str.length()) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (cArr[i2] != str.charAt(i + i2)) {
                return false;
            }
        }
        return true;
    }

    public final boolean charArrayCompare(char[] cArr) {
        return charArrayCompare(this.text, this.bp, cArr);
    }

    public final int indexOf(char c, int i) {
        return this.text.indexOf(c, i);
    }

    public final String addSymbol(int i, int i2, int i3, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.text, i, i2, i3);
    }

    public byte[] bytesValue() {
        return IOUtils.decodeFast(this.text, this.np + 1, this.sp);
    }

    public final String stringVal() {
        if (this.hasSpecial) {
            return new String(this.sbuf, 0, this.sp);
        }
        return subString(this.np + 1, this.sp);
    }

    public final String subString(int i, int i2) {
        if (!ASMUtils.IS_ANDROID) {
            return this.text.substring(i, i + i2);
        }
        if (i2 < this.sbuf.length) {
            this.text.getChars(i, i + i2, this.sbuf, 0);
            return new String(this.sbuf, 0, i2);
        }
        char[] cArr = new char[i2];
        this.text.getChars(i, i + i2, cArr, 0);
        return new String(cArr);
    }

    public final String numberString() {
        char charAt = charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i--;
        }
        return subString(this.np, i);
    }

    public boolean scanISO8601DateIfMatch() {
        return scanISO8601DateIfMatch(true);
    }

    public boolean scanISO8601DateIfMatch(boolean z) {
        char charAt;
        char charAt2;
        int i;
        int i2;
        int i3 = this.len - this.bp;
        if (!z && i3 > 13) {
            charAt = charAt(this.bp);
            char charAt3 = charAt(this.bp + 1);
            charAt2 = charAt(this.bp + 2);
            char charAt4 = charAt(this.bp + 3);
            char charAt5 = charAt(this.bp + 4);
            char charAt6 = charAt(this.bp + 5);
            char charAt7 = charAt((this.bp + i3) - 1);
            char charAt8 = charAt((this.bp + i3) - 2);
            if (charAt == '/' && charAt3 == 'D' && charAt2 == 'a' && charAt4 == 't' && charAt5 == 'e' && charAt6 == '(' && charAt7 == '/' && charAt8 == ')') {
                i = -1;
                for (i2 = 6; i2 < i3; i2++) {
                    charAt2 = charAt(this.bp + i2);
                    if (charAt2 != '+') {
                        if (charAt2 < '0' || charAt2 > '9') {
                            break;
                        }
                    } else {
                        i = i2;
                    }
                }
                if (i == -1) {
                    return false;
                }
                i2 = this.bp + 6;
                long parseLong = Long.parseLong(subString(i2, i - i2));
                this.calendar = Calendar.getInstance(this.timeZone, this.locale);
                this.calendar.setTimeInMillis(parseLong);
                this.token = 5;
                return true;
            }
        }
        if (i3 == 8 || i3 == 14 || i3 == 17) {
            if (z) {
                return false;
            }
            charAt = charAt(this.bp);
            charAt3 = charAt(this.bp + 1);
            charAt2 = charAt(this.bp + 2);
            charAt4 = charAt(this.bp + 3);
            charAt5 = charAt(this.bp + 4);
            charAt6 = charAt(this.bp + 5);
            charAt7 = charAt(this.bp + 6);
            charAt8 = charAt(this.bp + 7);
            if (!checkDate(charAt, charAt3, charAt2, charAt4, charAt5, charAt6, charAt7, charAt8)) {
                return false;
            }
            int i4;
            int i5;
            setCalendar(charAt, charAt3, charAt2, charAt4, charAt5, charAt6, charAt7, charAt8);
            if (i3 != 8) {
                charAt3 = charAt(this.bp + 8);
                charAt2 = charAt(this.bp + 9);
                charAt4 = charAt(this.bp + 10);
                charAt5 = charAt(this.bp + 11);
                charAt6 = charAt(this.bp + 12);
                charAt7 = charAt(this.bp + 13);
                if (!checkTime(charAt3, charAt2, charAt4, charAt5, charAt6, charAt7)) {
                    return false;
                }
                if (i3 == 17) {
                    charAt = charAt(this.bp + 14);
                    charAt8 = charAt(this.bp + 15);
                    char charAt9 = charAt(this.bp + 16);
                    if (charAt < '0' || charAt > '9') {
                        return false;
                    }
                    if (charAt8 < '0' || charAt8 > '9') {
                        return false;
                    }
                    if (charAt9 < '0' || charAt9 > '9') {
                        return false;
                    }
                    i = ((digits[charAt] * 100) + (digits[charAt8] * 10)) + digits[charAt9];
                } else {
                    i = 0;
                }
                int i6 = (digits[charAt3] * 10) + digits[charAt2];
                i4 = digits[charAt5] + (digits[charAt4] * 10);
                i2 = (digits[charAt6] * 10) + digits[charAt7];
                i5 = i6;
            } else {
                i5 = 0;
                i4 = 0;
                i2 = 0;
                i = 0;
            }
            this.calendar.set(11, i5);
            this.calendar.set(12, i4);
            this.calendar.set(13, i2);
            this.calendar.set(14, i);
            this.token = 5;
            return true;
        } else if (i3 < this.ISO8601_LEN_0) {
            return false;
        } else {
            if (charAt(this.bp + 4) != '-') {
                return false;
            }
            if (charAt(this.bp + 7) != '-') {
                return false;
            }
            charAt = charAt(this.bp);
            charAt3 = charAt(this.bp + 1);
            charAt2 = charAt(this.bp + 2);
            charAt4 = charAt(this.bp + 3);
            charAt5 = charAt(this.bp + 5);
            charAt6 = charAt(this.bp + 6);
            charAt7 = charAt(this.bp + 8);
            charAt8 = charAt(this.bp + 9);
            if (!checkDate(charAt, charAt3, charAt2, charAt4, charAt5, charAt6, charAt7, charAt8)) {
                return false;
            }
            setCalendar(charAt, charAt3, charAt2, charAt4, charAt5, charAt6, charAt7, charAt8);
            charAt8 = charAt(this.bp + 10);
            if (charAt8 == 'T' || (charAt8 == ' ' && !z)) {
                if (i3 < this.ISO8601_LEN_1) {
                    return false;
                }
                if (charAt(this.bp + 13) != ':') {
                    return false;
                }
                if (charAt(this.bp + 16) != ':') {
                    return false;
                }
                charAt3 = charAt(this.bp + 11);
                charAt2 = charAt(this.bp + 12);
                charAt4 = charAt(this.bp + 14);
                charAt5 = charAt(this.bp + 15);
                charAt6 = charAt(this.bp + 17);
                charAt7 = charAt(this.bp + 18);
                if (!checkTime(charAt3, charAt2, charAt4, charAt5, charAt6, charAt7)) {
                    return false;
                }
                setTime(charAt3, charAt2, charAt4, charAt5, charAt6, charAt7);
                charAt = charAt(this.bp + 19);
                if (charAt != '.') {
                    this.calendar.set(14, 0);
                    i2 = this.bp + 19;
                    this.bp = i2;
                    this.ch = charAt(i2);
                    this.token = 5;
                    if (charAt == 'Z' && this.calendar.getTimeZone().getRawOffset() != 0) {
                        String[] availableIDs = TimeZone.getAvailableIDs(0);
                        if (availableIDs.length > 0) {
                            this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
                        }
                    }
                    return true;
                } else if (i3 < this.ISO8601_LEN_2) {
                    return false;
                } else {
                    charAt = charAt(this.bp + 20);
                    if (charAt < '0' || charAt > '9') {
                        return false;
                    }
                    i2 = digits[charAt];
                    i = 1;
                    charAt2 = charAt(this.bp + 21);
                    if (charAt2 >= '0' && charAt2 <= '9') {
                        i2 = digits[charAt2] + (i2 * 10);
                        i = 2;
                    }
                    if (i == 2) {
                        charAt2 = charAt(this.bp + 22);
                        if (charAt2 >= '0' && charAt2 <= '9') {
                            i2 = digits[charAt2] + (i2 * 10);
                            i = 3;
                        }
                    }
                    this.calendar.set(14, i2);
                    i2 = 0;
                    charAt2 = charAt((this.bp + 20) + i);
                    if (charAt2 == '+' || charAt2 == '-') {
                        charAt4 = charAt(((this.bp + 20) + i) + 1);
                        if (charAt4 < '0' || charAt4 > '1') {
                            return false;
                        }
                        charAt5 = charAt(((this.bp + 20) + i) + 2);
                        if (charAt5 < '0' || charAt5 > '9') {
                            return false;
                        }
                        charAt3 = charAt(((this.bp + 20) + i) + 3);
                        if (charAt3 == ':') {
                            if (charAt(((this.bp + 20) + i) + 4) != '0') {
                                return false;
                            }
                            if (charAt(((this.bp + 20) + i) + 5) != '0') {
                                return false;
                            }
                            i2 = 6;
                        } else if (charAt3 != '0') {
                            i2 = 3;
                        } else if (charAt(((this.bp + 20) + i) + 4) != '0') {
                            return false;
                        } else {
                            i2 = 5;
                        }
                        setTimeZone(charAt2, charAt4, charAt5);
                    } else if (charAt2 == 'Z') {
                        i2 = 1;
                        if (this.calendar.getTimeZone().getRawOffset() != 0) {
                            String[] availableIDs2 = TimeZone.getAvailableIDs(0);
                            if (availableIDs2.length > 0) {
                                this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs2[0]));
                            }
                        }
                    }
                    charAt2 = charAt(this.bp + ((i + 20) + i2));
                    if (charAt2 != '\u001a' && charAt2 != '\"') {
                        return false;
                    }
                    i = ((i + 20) + i2) + this.bp;
                    this.bp = i;
                    this.ch = charAt(i);
                    this.token = 5;
                    return true;
                }
            } else if (charAt8 == '\"' || charAt8 == '\u001a') {
                this.calendar.set(11, 0);
                this.calendar.set(12, 0);
                this.calendar.set(13, 0);
                this.calendar.set(14, 0);
                i = this.bp + 10;
                this.bp = i;
                this.ch = charAt(i);
                this.token = 5;
                return true;
            } else if (charAt8 != '+' && charAt8 != '-') {
                return false;
            } else {
                if (this.len != 16) {
                    return false;
                }
                if (charAt(this.bp + 13) != ':' || charAt(this.bp + 14) != '0' || charAt(this.bp + 15) != '0') {
                    return false;
                }
                setTime('0', '0', '0', '0', '0', '0');
                this.calendar.set(14, 0);
                setTimeZone(charAt8, charAt(this.bp + 11), charAt(this.bp + 12));
                return true;
            }
        }
    }

    protected void setTime(char c, char c2, char c3, char c4, char c5, char c6) {
        int i = (digits[c3] * 10) + digits[c4];
        int i2 = (digits[c5] * 10) + digits[c6];
        this.calendar.set(11, (digits[c] * 10) + digits[c2]);
        this.calendar.set(12, i);
        this.calendar.set(13, i2);
    }

    protected void setTimeZone(char c, char c2, char c3) {
        int i = (((digits[c2] * 10) + digits[c3]) * 3600) * 1000;
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

    private boolean checkTime(char c, char c2, char c3, char c4, char c5, char c6) {
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
        int i = ((digits[c5] * 10) + digits[c6]) - 1;
        int i2 = (digits[c7] * 10) + digits[c8];
        this.calendar.set(1, (((digits[c] * 1000) + (digits[c2] * 100)) + (digits[c3] * 10)) + digits[c4]);
        this.calendar.set(2, i);
        this.calendar.set(5, i2);
    }

    static boolean checkDate(char c, char c2, char c3, char c4, char c5, char c6, int i, int i2) {
        if ((c != '1' && c != '2') || c2 < '0' || c2 > '9' || c3 < '0' || c3 > '9' || c4 < '0' || c4 > '9') {
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

    public boolean isEOF() {
        return this.bp == this.len || (this.ch == '\u001a' && this.bp + 1 == this.len);
    }

    public int scanFieldInt(char[] cArr) {
        this.matchStat = 0;
        int i = this.bp;
        char c = this.ch;
        if (charArrayCompare(this.text, this.bp, cArr)) {
            int length = this.bp + cArr.length;
            int i2 = length + 1;
            char charAt = charAt(length);
            if (charAt < '0' || charAt > '9') {
                this.matchStat = -1;
                return 0;
            }
            int i3;
            char charAt2;
            length = digits[charAt];
            while (true) {
                i3 = i2 + 1;
                charAt2 = charAt(i2);
                if (charAt2 >= '0' && charAt2 <= '9') {
                    length = (length * 10) + digits[charAt2];
                    i2 = i3;
                }
            }
            if (charAt2 == '.') {
                this.matchStat = -1;
                return 0;
            } else if (length < 0) {
                this.matchStat = -1;
                return 0;
            } else {
                if (charAt2 == ',' || charAt2 == '}') {
                    this.bp = i3 - 1;
                }
                if (charAt2 == ',') {
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                    this.matchStat = 3;
                    this.token = 16;
                    return length;
                } else if (charAt2 != '}') {
                    return length;
                } else {
                    i2 = this.bp + 1;
                    this.bp = i2;
                    charAt2 = charAt(i2);
                    if (charAt2 == ',') {
                        this.token = 16;
                        i2 = this.bp + 1;
                        this.bp = i2;
                        this.ch = charAt(i2);
                    } else if (charAt2 == ']') {
                        this.token = 15;
                        i2 = this.bp + 1;
                        this.bp = i2;
                        this.ch = charAt(i2);
                    } else if (charAt2 == '}') {
                        this.token = 13;
                        i2 = this.bp + 1;
                        this.bp = i2;
                        this.ch = charAt(i2);
                    } else if (charAt2 == '\u001a') {
                        this.token = 20;
                    } else {
                        this.bp = i;
                        this.ch = c;
                        this.matchStat = -1;
                        return 0;
                    }
                    this.matchStat = 4;
                    return length;
                }
            }
        }
        this.matchStat = -2;
        return 0;
    }

    public String scanFieldString(char[] cArr) {
        int i = 0;
        this.matchStat = 0;
        int i2 = this.bp;
        char c = this.ch;
        if (charArrayCompare(this.text, this.bp, cArr)) {
            int length = this.bp + cArr.length;
            int i3 = length + 1;
            if (charAt(length) != '\"') {
                this.matchStat = -1;
                return stringDefaultValue();
            }
            int indexOf = this.text.indexOf(34, i3);
            if (indexOf == -1) {
                throw new JSONException("unclosed str");
            }
            String subString = subString(i3, indexOf - i3);
            for (length = 0; length < subString.length(); length++) {
                if (subString.charAt(length) == '\\') {
                    i = 1;
                    break;
                }
            }
            if (i != 0) {
                this.matchStat = -1;
                return stringDefaultValue();
            }
            char charAt = charAt(indexOf + 1);
            if (charAt == ',' || charAt == '}') {
                this.bp = indexOf + 1;
                this.ch = charAt;
                if (charAt == ',') {
                    length = this.bp + 1;
                    this.bp = length;
                    this.ch = charAt(length);
                    this.matchStat = 3;
                    return subString;
                } else if (charAt == '}') {
                    length = this.bp + 1;
                    this.bp = length;
                    charAt = charAt(length);
                    if (charAt == ',') {
                        this.token = 16;
                        length = this.bp + 1;
                        this.bp = length;
                        this.ch = charAt(length);
                    } else if (charAt == ']') {
                        this.token = 15;
                        length = this.bp + 1;
                        this.bp = length;
                        this.ch = charAt(length);
                    } else if (charAt == '}') {
                        this.token = 13;
                        length = this.bp + 1;
                        this.bp = length;
                        this.ch = charAt(length);
                    } else if (charAt == '\u001a') {
                        this.token = 20;
                    } else {
                        this.bp = i2;
                        this.ch = c;
                        this.matchStat = -1;
                        return stringDefaultValue();
                    }
                    this.matchStat = 4;
                    return subString;
                } else {
                    this.matchStat = -1;
                    return stringDefaultValue();
                }
            }
            this.matchStat = -1;
            return stringDefaultValue();
        }
        this.matchStat = -2;
        return stringDefaultValue();
    }

    public String scanFieldSymbol(char[] cArr, SymbolTable symbolTable) {
        int i = 0;
        this.matchStat = 0;
        if (charArrayCompare(this.text, this.bp, cArr)) {
            int length = this.bp + cArr.length;
            int i2 = length + 1;
            if (charAt(length) != '\"') {
                this.matchStat = -1;
                return null;
            }
            int i3;
            char charAt;
            length = i2;
            while (true) {
                i3 = length + 1;
                charAt = charAt(length);
                if (charAt == '\"') {
                    break;
                }
                i = (i * 31) + charAt;
                if (charAt == '\\') {
                    this.matchStat = -1;
                    return null;
                }
                length = i3;
            }
            this.bp = i3;
            charAt = charAt(this.bp);
            this.ch = charAt;
            String addSymbol = symbolTable.addSymbol(this.text, i2, (i3 - i2) - 1, i);
            if (charAt == ',') {
                length = this.bp + 1;
                this.bp = length;
                this.ch = charAt(length);
                this.matchStat = 3;
                return addSymbol;
            } else if (charAt == '}') {
                length = this.bp + 1;
                this.bp = length;
                charAt = charAt(length);
                if (charAt == ',') {
                    this.token = 16;
                    length = this.bp + 1;
                    this.bp = length;
                    this.ch = charAt(length);
                } else if (charAt == ']') {
                    this.token = 15;
                    length = this.bp + 1;
                    this.bp = length;
                    this.ch = charAt(length);
                } else if (charAt == '}') {
                    this.token = 13;
                    length = this.bp + 1;
                    this.bp = length;
                    this.ch = charAt(length);
                } else if (charAt == '\u001a') {
                    this.token = 20;
                } else {
                    this.matchStat = -1;
                    return null;
                }
                this.matchStat = 4;
                return addSymbol;
            } else {
                this.matchStat = -1;
                return null;
            }
        }
        this.matchStat = -2;
        return null;
    }

    public Collection<String> scanFieldStringArray(char[] cArr, Class<?> cls) {
        this.matchStat = 0;
        if (charArrayCompare(this.text, this.bp, cArr)) {
            Collection<String> hashSet;
            if (cls.isAssignableFrom(HashSet.class)) {
                hashSet = new HashSet();
            } else if (cls.isAssignableFrom(ArrayList.class)) {
                hashSet = new ArrayList();
            } else {
                try {
                    hashSet = (Collection) cls.newInstance();
                } catch (Throwable e) {
                    throw new JSONException(e.getMessage(), e);
                }
            }
            int length = this.bp + cArr.length;
            int i = length + 1;
            if (charAt(length) != '[') {
                this.matchStat = -1;
                return null;
            }
            int i2 = i + 1;
            char charAt = charAt(i);
            while (charAt == '\"') {
                length = i2;
                while (true) {
                    i = length + 1;
                    charAt = charAt(length);
                    if (charAt == '\"') {
                        break;
                    } else if (charAt == '\\') {
                        this.matchStat = -1;
                        return null;
                    } else {
                        length = i;
                    }
                }
                hashSet.add(subString(i2, (i - i2) - 1));
                length = i + 1;
                char charAt2 = charAt(i);
                if (charAt2 == ',') {
                    i2 = length + 1;
                    charAt = charAt(length);
                } else if (charAt2 == ']') {
                    i2 = length + 1;
                    charAt = charAt(length);
                    while (JSONLexerBase.isWhitespace(charAt)) {
                        i = i2 + 1;
                        charAt = charAt(i2);
                        i2 = i;
                    }
                    this.bp = i2;
                    if (charAt == ',') {
                        this.ch = charAt(this.bp);
                        this.matchStat = 3;
                        return hashSet;
                    } else if (charAt == '}') {
                        charAt = charAt(this.bp);
                        while (JSONLexerBase.isWhitespace(charAt)) {
                            i = i2 + 1;
                            charAt = charAt(i2);
                            this.bp = i;
                            i2 = i;
                        }
                        if (charAt == ',') {
                            this.token = 16;
                            length = this.bp + 1;
                            this.bp = length;
                            this.ch = charAt(length);
                        } else if (charAt == ']') {
                            this.token = 15;
                            length = this.bp + 1;
                            this.bp = length;
                            this.ch = charAt(length);
                        } else if (charAt == '}') {
                            this.token = 13;
                            length = this.bp + 1;
                            this.bp = length;
                            this.ch = charAt(length);
                        } else if (charAt == '\u001a') {
                            this.token = 20;
                            this.ch = charAt;
                        } else {
                            this.matchStat = -1;
                            return null;
                        }
                        this.matchStat = 4;
                        return hashSet;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
            this.matchStat = -1;
            return null;
        }
        this.matchStat = -2;
        return null;
    }

    public long scanFieldLong(char[] cArr) {
        this.matchStat = 0;
        int i = this.bp;
        char c = this.ch;
        if (charArrayCompare(this.text, this.bp, cArr)) {
            int length = this.bp + cArr.length;
            int i2 = length + 1;
            char charAt = charAt(length);
            if (charAt < '0' || charAt > '9') {
                this.bp = i;
                this.ch = c;
                this.matchStat = -1;
                return 0;
            }
            int i3;
            char charAt2;
            long j = (long) digits[charAt];
            while (true) {
                i3 = i2 + 1;
                charAt2 = charAt(i2);
                if (charAt2 >= '0' && charAt2 <= '9') {
                    j = (j * 10) + ((long) digits[charAt2]);
                    i2 = i3;
                }
            }
            if (charAt2 == '.') {
                this.matchStat = -1;
                return 0;
            }
            if (charAt2 == ',' || charAt2 == '}') {
                this.bp = i3 - 1;
            }
            if (j < 0) {
                this.bp = i;
                this.ch = c;
                this.matchStat = -1;
                return 0;
            } else if (charAt2 == ',') {
                i2 = this.bp + 1;
                this.bp = i2;
                this.ch = charAt(i2);
                this.matchStat = 3;
                this.token = 16;
                return j;
            } else if (charAt2 == '}') {
                i2 = this.bp + 1;
                this.bp = i2;
                charAt2 = charAt(i2);
                if (charAt2 == ',') {
                    this.token = 16;
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                } else if (charAt2 == ']') {
                    this.token = 15;
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                } else if (charAt2 == '}') {
                    this.token = 13;
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                } else if (charAt2 == '\u001a') {
                    this.token = 20;
                } else {
                    this.bp = i;
                    this.ch = c;
                    this.matchStat = -1;
                    return 0;
                }
                this.matchStat = 4;
                return j;
            } else {
                this.matchStat = -1;
                return 0;
            }
        }
        this.matchStat = -2;
        return 0;
    }

    public boolean scanFieldBoolean(char[] cArr) {
        this.matchStat = 0;
        if (charArrayCompare(this.text, this.bp, cArr)) {
            char charAt;
            boolean z;
            int length = this.bp + cArr.length;
            int i = length + 1;
            char charAt2 = charAt(length);
            if (charAt2 == 't') {
                length = i + 1;
                if (charAt(i) != 'r') {
                    this.matchStat = -1;
                    return false;
                }
                i = length + 1;
                if (charAt(length) != 'u') {
                    this.matchStat = -1;
                    return false;
                }
                length = i + 1;
                if (charAt(i) != 'e') {
                    this.matchStat = -1;
                    return false;
                }
                this.bp = length;
                charAt = charAt(this.bp);
                z = true;
            } else if (charAt2 == 'f') {
                length = i + 1;
                if (charAt(i) != 'a') {
                    this.matchStat = -1;
                    return false;
                }
                i = length + 1;
                if (charAt(length) != 'l') {
                    this.matchStat = -1;
                    return false;
                }
                length = i + 1;
                if (charAt(i) != 's') {
                    this.matchStat = -1;
                    return false;
                }
                i = length + 1;
                if (charAt(length) != 'e') {
                    this.matchStat = -1;
                    return false;
                }
                this.bp = i;
                charAt = charAt(this.bp);
                z = false;
            } else {
                this.matchStat = -1;
                return false;
            }
            int i2;
            if (charAt == ',') {
                i2 = this.bp + 1;
                this.bp = i2;
                this.ch = charAt(i2);
                this.matchStat = 3;
                this.token = 16;
            } else if (charAt == '}') {
                i = this.bp + 1;
                this.bp = i;
                charAt = charAt(i);
                if (charAt == ',') {
                    this.token = 16;
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                } else if (charAt == ']') {
                    this.token = 15;
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                } else if (charAt == '}') {
                    this.token = 13;
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                } else if (charAt == '\u001a') {
                    this.token = 20;
                } else {
                    this.matchStat = -1;
                    return false;
                }
                this.matchStat = 4;
            } else {
                this.matchStat = -1;
                return false;
            }
            return z;
        }
        this.matchStat = -2;
        return false;
    }

    protected final void arrayCopy(int i, char[] cArr, int i2, int i3) {
        this.text.getChars(i, i + i3, cArr, i2);
    }

    public String info() {
        String str;
        StringBuilder append = new StringBuilder().append("pos ").append(this.bp).append(", json : ");
        if (this.text.length() < 65536) {
            str = this.text;
        } else {
            str = this.text.substring(0, 65536);
        }
        return append.append(str).toString();
    }
}
