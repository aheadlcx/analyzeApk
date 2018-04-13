package org.apache.commons.httpclient.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;

public class ParameterParser {
    private char[] chars = null;
    private int i1 = 0;
    private int i2 = 0;
    private int len = 0;
    private int pos = 0;

    private boolean hasChar() {
        return this.pos < this.len;
    }

    private String getToken(boolean z) {
        while (this.i1 < this.i2 && Character.isWhitespace(this.chars[this.i1])) {
            this.i1++;
        }
        while (this.i2 > this.i1 && Character.isWhitespace(this.chars[this.i2 - 1])) {
            this.i2--;
        }
        if (z && this.i2 - this.i1 >= 2 && this.chars[this.i1] == '\"' && this.chars[this.i2 - 1] == '\"') {
            this.i1++;
            this.i2--;
        }
        if (this.i2 >= this.i1) {
            return new String(this.chars, this.i1, this.i2 - this.i1);
        }
        return null;
    }

    private boolean isOneOf(char c, char[] cArr) {
        for (char c2 : cArr) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    private String parseToken(char[] cArr) {
        this.i1 = this.pos;
        this.i2 = this.pos;
        while (hasChar() && !isOneOf(this.chars[this.pos], cArr)) {
            this.i2++;
            this.pos++;
        }
        return getToken(false);
    }

    private String parseQuotedToken(char[] cArr) {
        this.i1 = this.pos;
        this.i2 = this.pos;
        boolean z = false;
        boolean z2 = false;
        while (hasChar()) {
            char c = this.chars[this.pos];
            if (!z2 && isOneOf(c, cArr)) {
                break;
            }
            if (!z && c == '\"') {
                if (z2) {
                    z2 = false;
                } else {
                    z2 = true;
                }
            }
            if (z || c != '\\') {
                z = false;
            } else {
                z = true;
            }
            this.i2++;
            this.pos++;
        }
        return getToken(true);
    }

    public List parse(String str, char c) {
        if (str == null) {
            return new ArrayList();
        }
        return parse(str.toCharArray(), c);
    }

    public List parse(char[] cArr, char c) {
        if (cArr == null) {
            return new ArrayList();
        }
        return parse(cArr, 0, cArr.length, c);
    }

    public List parse(char[] cArr, int i, int i2, char c) {
        if (cArr == null) {
            return new ArrayList();
        }
        List arrayList = new ArrayList();
        this.chars = cArr;
        this.pos = i;
        this.len = i2;
        while (hasChar()) {
            String parseToken = parseToken(new char[]{'=', c});
            String str = null;
            if (hasChar() && cArr[this.pos] == '=') {
                this.pos++;
                str = parseQuotedToken(new char[]{c});
            }
            if (hasChar() && cArr[this.pos] == c) {
                this.pos++;
            }
            if (!(parseToken == null || (parseToken.equals("") && str == null))) {
                arrayList.add(new NameValuePair(parseToken, str));
            }
        }
        return arrayList;
    }
}
