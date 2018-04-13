package org.mozilla.javascript;

public class Decompiler {
    public static final int CASE_GAP_PROP = 3;
    private static final int FUNCTION_END = 164;
    public static final int INDENT_GAP_PROP = 2;
    public static final int INITIAL_INDENT_PROP = 1;
    public static final int ONLY_BODY_FLAG = 1;
    public static final int TO_SOURCE_FLAG = 2;
    private static final boolean printSource = false;
    private char[] sourceBuffer = new char[128];
    private int sourceTop;

    String getEncodedSource() {
        return sourceToString(0);
    }

    int getCurrentOffset() {
        return this.sourceTop;
    }

    int markFunctionStart(int i) {
        int currentOffset = getCurrentOffset();
        addToken(109);
        append((char) i);
        return currentOffset;
    }

    int markFunctionEnd(int i) {
        int currentOffset = getCurrentOffset();
        append('¤');
        return currentOffset;
    }

    void addToken(int i) {
        if (i < 0 || i > 163) {
            throw new IllegalArgumentException();
        }
        append((char) i);
    }

    void addEOL(int i) {
        if (i < 0 || i > 163) {
            throw new IllegalArgumentException();
        }
        append((char) i);
        append('\u0001');
    }

    void addName(String str) {
        addToken(39);
        appendString(str);
    }

    void addString(String str) {
        addToken(41);
        appendString(str);
    }

    void addRegexp(String str, String str2) {
        addToken(48);
        appendString('/' + str + '/' + str2);
    }

    void addNumber(double d) {
        addToken(40);
        long j = (long) d;
        if (((double) j) != d) {
            j = Double.doubleToLongBits(d);
            append('D');
            append((char) ((int) (j >> 48)));
            append((char) ((int) (j >> 32)));
            append((char) ((int) (j >> 16)));
            append((char) ((int) j));
            return;
        }
        if (j < 0) {
            Kit.codeBug();
        }
        if (j <= 65535) {
            append('S');
            append((char) ((int) j));
            return;
        }
        append('J');
        append((char) ((int) (j >> 48)));
        append((char) ((int) (j >> 32)));
        append((char) ((int) (j >> 16)));
        append((char) ((int) j));
    }

    private void appendString(String str) {
        int length = str.length();
        int i = 1;
        if (length >= 32768) {
            i = 2;
        }
        i = (i + this.sourceTop) + length;
        if (i > this.sourceBuffer.length) {
            increaseSourceCapacity(i);
        }
        if (length >= 32768) {
            this.sourceBuffer[this.sourceTop] = (char) ((length >>> 16) | 32768);
            this.sourceTop++;
        }
        this.sourceBuffer[this.sourceTop] = (char) length;
        this.sourceTop++;
        str.getChars(0, length, this.sourceBuffer, this.sourceTop);
        this.sourceTop = i;
    }

    private void append(char c) {
        if (this.sourceTop == this.sourceBuffer.length) {
            increaseSourceCapacity(this.sourceTop + 1);
        }
        this.sourceBuffer[this.sourceTop] = c;
        this.sourceTop++;
    }

    private void increaseSourceCapacity(int i) {
        if (i <= this.sourceBuffer.length) {
            Kit.codeBug();
        }
        int length = this.sourceBuffer.length * 2;
        if (length >= i) {
            i = length;
        }
        Object obj = new char[i];
        System.arraycopy(this.sourceBuffer, 0, obj, 0, this.sourceTop);
        this.sourceBuffer = obj;
    }

    private String sourceToString(int i) {
        if (i < 0 || this.sourceTop < i) {
            Kit.codeBug();
        }
        return new String(this.sourceBuffer, i, this.sourceTop - i);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String decompile(java.lang.String r17, int r18, org.mozilla.javascript.UintMap r19) {
        /*
        r10 = r17.length();
        if (r10 != 0) goto L_0x000a;
    L_0x0006:
        r1 = "";
    L_0x0009:
        return r1;
    L_0x000a:
        r1 = 1;
        r2 = 0;
        r0 = r19;
        r3 = r0.getInt(r1, r2);
        if (r3 >= 0) goto L_0x001a;
    L_0x0014:
        r1 = new java.lang.IllegalArgumentException;
        r1.<init>();
        throw r1;
    L_0x001a:
        r1 = 2;
        r2 = 4;
        r0 = r19;
        r7 = r0.getInt(r1, r2);
        if (r7 >= 0) goto L_0x002a;
    L_0x0024:
        r1 = new java.lang.IllegalArgumentException;
        r1.<init>();
        throw r1;
    L_0x002a:
        r1 = 3;
        r2 = 2;
        r0 = r19;
        r11 = r0.getInt(r1, r2);
        if (r11 >= 0) goto L_0x003a;
    L_0x0034:
        r1 = new java.lang.IllegalArgumentException;
        r1.<init>();
        throw r1;
    L_0x003a:
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r1 = r18 & 1;
        if (r1 == 0) goto L_0x006c;
    L_0x0043:
        r1 = 1;
        r9 = r1;
    L_0x0045:
        r1 = r18 & 2;
        if (r1 == 0) goto L_0x006f;
    L_0x0049:
        r1 = 1;
    L_0x004a:
        r6 = 0;
        r5 = 0;
        r4 = 0;
        r0 = r17;
        r2 = r0.charAt(r4);
        r8 = 136; // 0x88 float:1.9E-43 double:6.7E-322;
        if (r2 != r8) goto L_0x0071;
    L_0x0057:
        r4 = 1;
        r2 = -1;
        r8 = r2;
    L_0x005a:
        if (r1 != 0) goto L_0x007a;
    L_0x005c:
        r2 = 10;
        r12.append(r2);
        r2 = 0;
    L_0x0062:
        if (r2 >= r3) goto L_0x0082;
    L_0x0064:
        r13 = 32;
        r12.append(r13);
        r2 = r2 + 1;
        goto L_0x0062;
    L_0x006c:
        r1 = 0;
        r9 = r1;
        goto L_0x0045;
    L_0x006f:
        r1 = 0;
        goto L_0x004a;
    L_0x0071:
        r2 = 1;
        r0 = r17;
        r2 = r0.charAt(r2);
        r8 = r2;
        goto L_0x005a;
    L_0x007a:
        r2 = 2;
        if (r8 != r2) goto L_0x0082;
    L_0x007d:
        r2 = 40;
        r12.append(r2);
    L_0x0082:
        if (r4 >= r10) goto L_0x064e;
    L_0x0084:
        r0 = r17;
        r2 = r0.charAt(r4);
        switch(r2) {
            case 1: goto L_0x01d2;
            case 2: goto L_0x008d;
            case 3: goto L_0x008d;
            case 4: goto L_0x034a;
            case 5: goto L_0x008d;
            case 6: goto L_0x008d;
            case 7: goto L_0x008d;
            case 8: goto L_0x008d;
            case 9: goto L_0x048c;
            case 10: goto L_0x0499;
            case 11: goto L_0x04a6;
            case 12: goto L_0x04cd;
            case 13: goto L_0x04da;
            case 14: goto L_0x04f4;
            case 15: goto L_0x04e7;
            case 16: goto L_0x050e;
            case 17: goto L_0x0501;
            case 18: goto L_0x0528;
            case 19: goto L_0x0535;
            case 20: goto L_0x0542;
            case 21: goto L_0x05cd;
            case 22: goto L_0x05da;
            case 23: goto L_0x05e7;
            case 24: goto L_0x05f4;
            case 25: goto L_0x0601;
            case 26: goto L_0x0583;
            case 27: goto L_0x058f;
            case 28: goto L_0x059b;
            case 29: goto L_0x05a7;
            case 30: goto L_0x0242;
            case 31: goto L_0x024f;
            case 32: goto L_0x054f;
            case 33: goto L_0x008d;
            case 34: goto L_0x008d;
            case 35: goto L_0x008d;
            case 36: goto L_0x008d;
            case 37: goto L_0x008d;
            case 38: goto L_0x008d;
            case 39: goto L_0x00de;
            case 40: goto L_0x00f2;
            case 41: goto L_0x00e8;
            case 42: goto L_0x0113;
            case 43: goto L_0x011f;
            case 44: goto L_0x0107;
            case 45: goto L_0x00fb;
            case 46: goto L_0x04b3;
            case 47: goto L_0x04c0;
            case 48: goto L_0x00de;
            case 49: goto L_0x008d;
            case 50: goto L_0x02de;
            case 51: goto L_0x008d;
            case 52: goto L_0x0283;
            case 53: goto L_0x051b;
            case 54: goto L_0x008d;
            case 55: goto L_0x008d;
            case 56: goto L_0x008d;
            case 57: goto L_0x008d;
            case 58: goto L_0x008d;
            case 59: goto L_0x008d;
            case 60: goto L_0x008d;
            case 61: goto L_0x008d;
            case 62: goto L_0x008d;
            case 63: goto L_0x008d;
            case 64: goto L_0x008d;
            case 65: goto L_0x008d;
            case 66: goto L_0x0443;
            case 67: goto L_0x008d;
            case 68: goto L_0x008d;
            case 69: goto L_0x008d;
            case 70: goto L_0x008d;
            case 71: goto L_0x008d;
            case 72: goto L_0x0576;
            case 73: goto L_0x008d;
            case 74: goto L_0x008d;
            case 75: goto L_0x008d;
            case 76: goto L_0x008d;
            case 77: goto L_0x008d;
            case 78: goto L_0x008d;
            case 79: goto L_0x008d;
            case 80: goto L_0x008d;
            case 81: goto L_0x02b7;
            case 82: goto L_0x0380;
            case 83: goto L_0x01ba;
            case 84: goto L_0x01c6;
            case 85: goto L_0x014a;
            case 86: goto L_0x0162;
            case 87: goto L_0x0193;
            case 88: goto L_0x019f;
            case 89: goto L_0x013e;
            case 90: goto L_0x039a;
            case 91: goto L_0x03e8;
            case 92: goto L_0x03f5;
            case 93: goto L_0x0402;
            case 94: goto L_0x040f;
            case 95: goto L_0x041c;
            case 96: goto L_0x0429;
            case 97: goto L_0x03a7;
            case 98: goto L_0x03b4;
            case 99: goto L_0x03c1;
            case 100: goto L_0x03ce;
            case 101: goto L_0x03db;
            case 102: goto L_0x0436;
            case 103: goto L_0x0450;
            case 104: goto L_0x0472;
            case 105: goto L_0x047f;
            case 106: goto L_0x05b3;
            case 107: goto L_0x05c0;
            case 108: goto L_0x0236;
            case 109: goto L_0x012b;
            case 110: goto L_0x008d;
            case 111: goto L_0x008d;
            case 112: goto L_0x025c;
            case 113: goto L_0x0269;
            case 114: goto L_0x02eb;
            case 115: goto L_0x0330;
            case 116: goto L_0x033d;
            case 117: goto L_0x029d;
            case 118: goto L_0x02aa;
            case 119: goto L_0x0276;
            case 120: goto L_0x02f8;
            case 121: goto L_0x0314;
            case 122: goto L_0x0366;
            case 123: goto L_0x0290;
            case 124: goto L_0x02c4;
            case 125: goto L_0x02d1;
            case 126: goto L_0x055c;
            case 127: goto L_0x008d;
            case 128: goto L_0x008d;
            case 129: goto L_0x008d;
            case 130: goto L_0x008d;
            case 131: goto L_0x008d;
            case 132: goto L_0x008d;
            case 133: goto L_0x008d;
            case 134: goto L_0x008d;
            case 135: goto L_0x008d;
            case 136: goto L_0x008d;
            case 137: goto L_0x008d;
            case 138: goto L_0x008d;
            case 139: goto L_0x008d;
            case 140: goto L_0x008d;
            case 141: goto L_0x008d;
            case 142: goto L_0x008d;
            case 143: goto L_0x061b;
            case 144: goto L_0x060e;
            case 145: goto L_0x008d;
            case 146: goto L_0x0628;
            case 147: goto L_0x0635;
            case 148: goto L_0x008d;
            case 149: goto L_0x008d;
            case 150: goto L_0x008d;
            case 151: goto L_0x00b1;
            case 152: goto L_0x00b1;
            case 153: goto L_0x0373;
            case 154: goto L_0x0569;
            case 155: goto L_0x008d;
            case 156: goto L_0x008d;
            case 157: goto L_0x008d;
            case 158: goto L_0x008d;
            case 159: goto L_0x008d;
            case 160: goto L_0x0641;
            case 161: goto L_0x008d;
            case 162: goto L_0x008d;
            case 163: goto L_0x008d;
            case 164: goto L_0x0138;
            default: goto L_0x008d;
        };
    L_0x008d:
        r1 = new java.lang.RuntimeException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Token: ";
        r2 = r2.append(r3);
        r0 = r17;
        r3 = r0.charAt(r4);
        r3 = org.mozilla.javascript.Token.name(r3);
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2);
        throw r1;
    L_0x00b1:
        r0 = r17;
        r2 = r0.charAt(r4);
        r13 = 151; // 0x97 float:2.12E-43 double:7.46E-322;
        if (r2 != r13) goto L_0x00da;
    L_0x00bb:
        r2 = "get ";
    L_0x00be:
        r12.append(r2);
        r2 = r4 + 1;
        r2 = r2 + 1;
        r4 = 0;
        r0 = r17;
        r2 = printSourceString(r0, r2, r4, r12);
        r2 = r2 + 1;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
    L_0x00d2:
        r2 = r2 + 1;
        r6 = r4;
        r4 = r2;
        r15 = r3;
        r3 = r5;
        r5 = r15;
        goto L_0x0082;
    L_0x00da:
        r2 = "set ";
        goto L_0x00be;
    L_0x00de:
        r2 = r4 + 1;
        r4 = 0;
        r0 = r17;
        r4 = printSourceString(r0, r2, r4, r12);
        goto L_0x0082;
    L_0x00e8:
        r2 = r4 + 1;
        r4 = 1;
        r0 = r17;
        r4 = printSourceString(r0, r2, r4, r12);
        goto L_0x0082;
    L_0x00f2:
        r2 = r4 + 1;
        r0 = r17;
        r4 = printSourceNumber(r0, r2, r12);
        goto L_0x0082;
    L_0x00fb:
        r2 = "true";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0107:
        r2 = "false";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0113:
        r2 = "null";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x011f:
        r2 = "this";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x012b:
        r2 = r4 + 1;
        r4 = "function ";
        r12.append(r4);
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0138:
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x013e:
        r2 = ", ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x014a:
        r6 = r6 + 1;
        r2 = 1;
        r0 = r17;
        r13 = getNext(r0, r10, r4);
        if (r2 != r13) goto L_0x0678;
    L_0x0155:
        r2 = r3 + r7;
    L_0x0157:
        r3 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        r12.append(r3);
        r3 = r5;
        r5 = r2;
        r2 = r4;
        r4 = r6;
        goto L_0x00d2;
    L_0x0162:
        r2 = r6 + -1;
        if (r9 == 0) goto L_0x0172;
    L_0x0166:
        if (r2 != 0) goto L_0x0172;
    L_0x0168:
        r15 = r4;
        r4 = r2;
        r2 = r15;
        r16 = r5;
        r5 = r3;
        r3 = r16;
        goto L_0x00d2;
    L_0x0172:
        r6 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        r12.append(r6);
        r0 = r17;
        r6 = getNext(r0, r10, r4);
        switch(r6) {
            case 1: goto L_0x018a;
            case 113: goto L_0x018c;
            case 117: goto L_0x018c;
            case 164: goto L_0x018a;
            default: goto L_0x0180;
        };
    L_0x0180:
        r15 = r4;
        r4 = r2;
        r2 = r15;
        r16 = r5;
        r5 = r3;
        r3 = r16;
        goto L_0x00d2;
    L_0x018a:
        r3 = r3 - r7;
        goto L_0x0180;
    L_0x018c:
        r3 = r3 - r7;
        r6 = 32;
        r12.append(r6);
        goto L_0x0180;
    L_0x0193:
        r2 = 40;
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x019f:
        r2 = 41;
        r12.append(r2);
        r2 = 85;
        r0 = r17;
        r13 = getNext(r0, r10, r4);
        if (r2 != r13) goto L_0x0666;
    L_0x01ae:
        r2 = 32;
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x01ba:
        r2 = 91;
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x01c6:
        r2 = 93;
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x01d2:
        if (r1 == 0) goto L_0x01db;
    L_0x01d4:
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x01db:
        r2 = 1;
        if (r5 != 0) goto L_0x0673;
    L_0x01de:
        r5 = 1;
        if (r9 == 0) goto L_0x0673;
    L_0x01e1:
        r2 = 0;
        r12.setLength(r2);
        r3 = r3 - r7;
        r2 = 0;
        r15 = r2;
        r2 = r5;
        r5 = r15;
    L_0x01ea:
        if (r5 == 0) goto L_0x01f1;
    L_0x01ec:
        r5 = 10;
        r12.append(r5);
    L_0x01f1:
        r5 = r4 + 1;
        if (r5 >= r10) goto L_0x066d;
    L_0x01f5:
        r5 = 0;
        r13 = r4 + 1;
        r0 = r17;
        r13 = r0.charAt(r13);
        r14 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r13 == r14) goto L_0x0206;
    L_0x0202:
        r14 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        if (r13 != r14) goto L_0x0212;
    L_0x0206:
        r5 = r7 - r11;
    L_0x0208:
        if (r5 >= r3) goto L_0x0230;
    L_0x020a:
        r13 = 32;
        r12.append(r13);
        r5 = r5 + 1;
        goto L_0x0208;
    L_0x0212:
        r14 = 86;
        if (r13 != r14) goto L_0x0218;
    L_0x0216:
        r5 = r7;
        goto L_0x0208;
    L_0x0218:
        r14 = 39;
        if (r13 != r14) goto L_0x0208;
    L_0x021c:
        r13 = r4 + 2;
        r0 = r17;
        r13 = getSourceStringEnd(r0, r13);
        r0 = r17;
        r13 = r0.charAt(r13);
        r14 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        if (r13 != r14) goto L_0x0208;
    L_0x022e:
        r5 = r7;
        goto L_0x0208;
    L_0x0230:
        r5 = r3;
        r3 = r2;
        r2 = r4;
        r4 = r6;
        goto L_0x00d2;
    L_0x0236:
        r2 = 46;
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0242:
        r2 = "new ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x024f:
        r2 = "delete ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x025c:
        r2 = "if ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0269:
        r2 = "else ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0276:
        r2 = "for ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0283:
        r2 = " in ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0290:
        r2 = "with ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x029d:
        r2 = "while ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x02aa:
        r2 = "do ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x02b7:
        r2 = "try ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x02c4:
        r2 = "catch ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x02d1:
        r2 = "finally ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x02de:
        r2 = "throw ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x02eb:
        r2 = "switch ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x02f8:
        r2 = "break";
        r12.append(r2);
        r2 = 39;
        r0 = r17;
        r13 = getNext(r0, r10, r4);
        if (r2 != r13) goto L_0x0666;
    L_0x0308:
        r2 = 32;
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0314:
        r2 = "continue";
        r12.append(r2);
        r2 = 39;
        r0 = r17;
        r13 = getNext(r0, r10, r4);
        if (r2 != r13) goto L_0x0666;
    L_0x0324:
        r2 = 32;
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0330:
        r2 = "case ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x033d:
        r2 = "default";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x034a:
        r2 = "return";
        r12.append(r2);
        r2 = 82;
        r0 = r17;
        r13 = getNext(r0, r10, r4);
        if (r2 == r13) goto L_0x0666;
    L_0x035a:
        r2 = 32;
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0366:
        r2 = "var ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0373:
        r2 = "let ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0380:
        r2 = 59;
        r12.append(r2);
        r2 = 1;
        r0 = r17;
        r13 = getNext(r0, r10, r4);
        if (r2 == r13) goto L_0x0666;
    L_0x038e:
        r2 = 32;
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x039a:
        r2 = " = ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x03a7:
        r2 = " += ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x03b4:
        r2 = " -= ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x03c1:
        r2 = " *= ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x03ce:
        r2 = " /= ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x03db:
        r2 = " %= ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x03e8:
        r2 = " |= ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x03f5:
        r2 = " ^= ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0402:
        r2 = " &= ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x040f:
        r2 = " <<= ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x041c:
        r2 = " >>= ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0429:
        r2 = " >>>= ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0436:
        r2 = " ? ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0443:
        r2 = ": ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0450:
        r2 = 1;
        r0 = r17;
        r13 = getNext(r0, r10, r4);
        if (r2 != r13) goto L_0x0465;
    L_0x0459:
        r2 = 58;
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0465:
        r2 = " : ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0472:
        r2 = " || ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x047f:
        r2 = " && ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x048c:
        r2 = " | ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0499:
        r2 = " ^ ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x04a6:
        r2 = " & ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x04b3:
        r2 = " === ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x04c0:
        r2 = " !== ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x04cd:
        r2 = " == ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x04da:
        r2 = " != ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x04e7:
        r2 = " <= ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x04f4:
        r2 = " < ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0501:
        r2 = " >= ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x050e:
        r2 = " > ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x051b:
        r2 = " instanceof ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0528:
        r2 = " << ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0535:
        r2 = " >> ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0542:
        r2 = " >>> ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x054f:
        r2 = "typeof ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x055c:
        r2 = "void ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0569:
        r2 = "const ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0576:
        r2 = "yield ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0583:
        r2 = 33;
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x058f:
        r2 = 126; // 0x7e float:1.77E-43 double:6.23E-322;
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x059b:
        r2 = 43;
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x05a7:
        r2 = 45;
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x05b3:
        r2 = "++";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x05c0:
        r2 = "--";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x05cd:
        r2 = " + ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x05da:
        r2 = " - ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x05e7:
        r2 = " * ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x05f4:
        r2 = " / ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0601:
        r2 = " % ";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x060e:
        r2 = "::";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x061b:
        r2 = "..";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0628:
        r2 = ".(";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0635:
        r2 = 64;
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0641:
        r2 = "debugger;\n";
        r12.append(r2);
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x064e:
        if (r1 != 0) goto L_0x065d;
    L_0x0650:
        if (r9 != 0) goto L_0x0657;
    L_0x0652:
        r1 = 10;
        r12.append(r1);
    L_0x0657:
        r1 = r12.toString();
        goto L_0x0009;
    L_0x065d:
        r1 = 2;
        if (r8 != r1) goto L_0x0657;
    L_0x0660:
        r1 = 41;
        r12.append(r1);
        goto L_0x0657;
    L_0x0666:
        r2 = r4;
        r4 = r6;
        r15 = r5;
        r5 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x066d:
        r5 = r3;
        r3 = r2;
        r2 = r4;
        r4 = r6;
        goto L_0x00d2;
    L_0x0673:
        r15 = r2;
        r2 = r5;
        r5 = r15;
        goto L_0x01ea;
    L_0x0678:
        r2 = r3;
        goto L_0x0157;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Decompiler.decompile(java.lang.String, int, org.mozilla.javascript.UintMap):java.lang.String");
    }

    private static int getNext(String str, int i, int i2) {
        return i2 + 1 < i ? str.charAt(i2 + 1) : 0;
    }

    private static int getSourceStringEnd(String str, int i) {
        return printSourceString(str, i, false, null);
    }

    private static int printSourceString(String str, int i, boolean z, StringBuilder stringBuilder) {
        int charAt = str.charAt(i);
        int i2 = i + 1;
        if ((32768 & charAt) != 0) {
            charAt = ((charAt & 32767) << 16) | str.charAt(i2);
            i2++;
        }
        if (stringBuilder != null) {
            String substring = str.substring(i2, i2 + charAt);
            if (z) {
                stringBuilder.append('\"');
                stringBuilder.append(ScriptRuntime.escapeString(substring));
                stringBuilder.append('\"');
            } else {
                stringBuilder.append(substring);
            }
        }
        return charAt + i2;
    }

    private static int printSourceNumber(String str, int i, StringBuilder stringBuilder) {
        int i2;
        double d = 0.0d;
        char charAt = str.charAt(i);
        int i3 = i + 1;
        if (charAt == 'S') {
            if (stringBuilder != null) {
                d = (double) str.charAt(i3);
            }
            i2 = i3 + 1;
        } else if (charAt == 'J' || charAt == 'D') {
            if (stringBuilder != null) {
                long charAt2 = (((((long) str.charAt(i3)) << 48) | (((long) str.charAt(i3 + 1)) << 32)) | (((long) str.charAt(i3 + 2)) << 16)) | ((long) str.charAt(i3 + 3));
                if (charAt == 'J') {
                    d = (double) charAt2;
                } else {
                    d = Double.longBitsToDouble(charAt2);
                }
            }
            i2 = i3 + 4;
        } else {
            throw new RuntimeException();
        }
        if (stringBuilder != null) {
            stringBuilder.append(ScriptRuntime.numberToString(d, 10));
        }
        return i2;
    }
}
