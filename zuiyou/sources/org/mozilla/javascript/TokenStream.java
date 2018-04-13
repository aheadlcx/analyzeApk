package org.mozilla.javascript;

import java.io.IOException;
import java.io.Reader;
import org.mozilla.javascript.Token.CommentType;

class TokenStream {
    static final /* synthetic */ boolean $assertionsDisabled = (!TokenStream.class.desiredAssertionStatus());
    private static final char BYTE_ORDER_MARK = '﻿';
    private static final int EOF_CHAR = -1;
    private ObjToIntMap allStrings = new ObjToIntMap(50);
    private int commentCursor = -1;
    private String commentPrefix = "";
    CommentType commentType;
    int cursor;
    private boolean dirtyLine;
    private boolean hitEOF = false;
    private boolean isHex;
    private boolean isOctal;
    private int lineEndChar = -1;
    private int lineStart = 0;
    int lineno;
    private double number;
    private Parser parser;
    private int quoteChar;
    String regExpFlags;
    private char[] sourceBuffer;
    int sourceCursor;
    private int sourceEnd;
    private Reader sourceReader;
    private String sourceString;
    private String string = "";
    private char[] stringBuffer = new char[128];
    private int stringBufferTop;
    int tokenBeg;
    int tokenEnd;
    private final int[] ungetBuffer = new int[3];
    private int ungetCursor;
    private boolean xmlIsAttribute;
    private boolean xmlIsTagContent;
    private int xmlOpenTagsCount;

    TokenStream(Parser parser, Reader reader, String str, int i) {
        this.parser = parser;
        this.lineno = i;
        if (reader != null) {
            if (str != null) {
                Kit.codeBug();
            }
            this.sourceReader = reader;
            this.sourceBuffer = new char[512];
            this.sourceEnd = 0;
        } else {
            if (str == null) {
                Kit.codeBug();
            }
            this.sourceString = str;
            this.sourceEnd = str.length();
        }
        this.cursor = 0;
        this.sourceCursor = 0;
    }

    String tokenToString(int i) {
        return "";
    }

    static boolean isKeyword(String str) {
        return stringToKeyword(str) != 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int stringToKeyword(java.lang.String r9) {
        /*
        r3 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        r7 = 2;
        r0 = 0;
        r6 = 1;
        r1 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r2 = 0;
        r4 = r9.length();
        switch(r4) {
            case 2: goto L_0x001e;
            case 3: goto L_0x004f;
            case 4: goto L_0x00c5;
            case 5: goto L_0x0193;
            case 6: goto L_0x0220;
            case 7: goto L_0x027b;
            case 8: goto L_0x02ad;
            case 9: goto L_0x02df;
            case 10: goto L_0x02fe;
            case 11: goto L_0x000f;
            case 12: goto L_0x0319;
            default: goto L_0x000f;
        };
    L_0x000f:
        r1 = r0;
    L_0x0010:
        if (r2 == 0) goto L_0x001b;
    L_0x0012:
        if (r2 == r9) goto L_0x001b;
    L_0x0014:
        r2 = r2.equals(r9);
        if (r2 != 0) goto L_0x001b;
    L_0x001a:
        r1 = r0;
    L_0x001b:
        if (r1 != 0) goto L_0x031e;
    L_0x001d:
        return r0;
    L_0x001e:
        r1 = r9.charAt(r6);
        r3 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r1 != r3) goto L_0x0031;
    L_0x0026:
        r1 = r9.charAt(r0);
        r3 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        if (r1 != r3) goto L_0x000f;
    L_0x002e:
        r1 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        goto L_0x001b;
    L_0x0031:
        r3 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        if (r1 != r3) goto L_0x0040;
    L_0x0035:
        r1 = r9.charAt(r0);
        r3 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        if (r1 != r3) goto L_0x000f;
    L_0x003d:
        r1 = 52;
        goto L_0x001b;
    L_0x0040:
        r3 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        if (r1 != r3) goto L_0x000f;
    L_0x0044:
        r1 = r9.charAt(r0);
        r3 = 100;
        if (r1 != r3) goto L_0x000f;
    L_0x004c:
        r1 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
        goto L_0x001b;
    L_0x004f:
        r4 = r9.charAt(r0);
        switch(r4) {
            case 102: goto L_0x0058;
            case 105: goto L_0x0069;
            case 108: goto L_0x007a;
            case 110: goto L_0x008d;
            case 116: goto L_0x00a1;
            case 118: goto L_0x00b3;
            default: goto L_0x0056;
        };
    L_0x0056:
        r1 = r0;
        goto L_0x0010;
    L_0x0058:
        r1 = r9.charAt(r7);
        if (r1 != r3) goto L_0x000f;
    L_0x005e:
        r1 = r9.charAt(r6);
        r3 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        if (r1 != r3) goto L_0x000f;
    L_0x0066:
        r1 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        goto L_0x001b;
    L_0x0069:
        r3 = r9.charAt(r7);
        r4 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        if (r3 != r4) goto L_0x000f;
    L_0x0071:
        r3 = r9.charAt(r6);
        r4 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        if (r3 != r4) goto L_0x000f;
    L_0x0079:
        goto L_0x001b;
    L_0x007a:
        r1 = r9.charAt(r7);
        r3 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        if (r1 != r3) goto L_0x000f;
    L_0x0082:
        r1 = r9.charAt(r6);
        r3 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r1 != r3) goto L_0x000f;
    L_0x008a:
        r1 = 153; // 0x99 float:2.14E-43 double:7.56E-322;
        goto L_0x001b;
    L_0x008d:
        r1 = r9.charAt(r7);
        r3 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        if (r1 != r3) goto L_0x000f;
    L_0x0095:
        r1 = r9.charAt(r6);
        r3 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r1 != r3) goto L_0x000f;
    L_0x009d:
        r1 = 30;
        goto L_0x001b;
    L_0x00a1:
        r1 = r9.charAt(r7);
        r4 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        if (r1 != r4) goto L_0x000f;
    L_0x00a9:
        r1 = r9.charAt(r6);
        if (r1 != r3) goto L_0x000f;
    L_0x00af:
        r1 = 81;
        goto L_0x001b;
    L_0x00b3:
        r1 = r9.charAt(r7);
        if (r1 != r3) goto L_0x000f;
    L_0x00b9:
        r1 = r9.charAt(r6);
        r3 = 97;
        if (r1 != r3) goto L_0x000f;
    L_0x00c1:
        r1 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        goto L_0x001b;
    L_0x00c5:
        r4 = r9.charAt(r0);
        switch(r4) {
            case 98: goto L_0x00cf;
            case 99: goto L_0x00d4;
            case 101: goto L_0x0105;
            case 103: goto L_0x0138;
            case 108: goto L_0x013d;
            case 110: goto L_0x0142;
            case 116: goto L_0x014c;
            case 118: goto L_0x017f;
            case 119: goto L_0x0189;
            default: goto L_0x00cc;
        };
    L_0x00cc:
        r1 = r0;
        goto L_0x0010;
    L_0x00cf:
        r2 = "byte";
        goto L_0x0010;
    L_0x00d4:
        r4 = 3;
        r4 = r9.charAt(r4);
        r5 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r4 != r5) goto L_0x00f1;
    L_0x00dd:
        r1 = r9.charAt(r7);
        r3 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r1 != r3) goto L_0x000f;
    L_0x00e5:
        r1 = r9.charAt(r6);
        r3 = 97;
        if (r1 != r3) goto L_0x000f;
    L_0x00ed:
        r1 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        goto L_0x001b;
    L_0x00f1:
        if (r4 != r3) goto L_0x000f;
    L_0x00f3:
        r3 = r9.charAt(r7);
        r4 = 97;
        if (r3 != r4) goto L_0x000f;
    L_0x00fb:
        r3 = r9.charAt(r6);
        r4 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        if (r3 != r4) goto L_0x000f;
    L_0x0103:
        goto L_0x001b;
    L_0x0105:
        r3 = 3;
        r3 = r9.charAt(r3);
        r4 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r3 != r4) goto L_0x0122;
    L_0x010e:
        r1 = r9.charAt(r7);
        r3 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r1 != r3) goto L_0x000f;
    L_0x0116:
        r1 = r9.charAt(r6);
        r3 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        if (r1 != r3) goto L_0x000f;
    L_0x011e:
        r1 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
        goto L_0x001b;
    L_0x0122:
        r4 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        if (r3 != r4) goto L_0x000f;
    L_0x0126:
        r3 = r9.charAt(r7);
        r4 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        if (r3 != r4) goto L_0x000f;
    L_0x012e:
        r3 = r9.charAt(r6);
        r4 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        if (r3 != r4) goto L_0x000f;
    L_0x0136:
        goto L_0x001b;
    L_0x0138:
        r2 = "goto";
        goto L_0x0010;
    L_0x013d:
        r2 = "long";
        goto L_0x0010;
    L_0x0142:
        r1 = "null";
        r2 = 42;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x014c:
        r1 = 3;
        r1 = r9.charAt(r1);
        r4 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r1 != r4) goto L_0x0167;
    L_0x0155:
        r1 = r9.charAt(r7);
        r4 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        if (r1 != r4) goto L_0x000f;
    L_0x015d:
        r1 = r9.charAt(r6);
        if (r1 != r3) goto L_0x000f;
    L_0x0163:
        r1 = 45;
        goto L_0x001b;
    L_0x0167:
        r3 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r1 != r3) goto L_0x000f;
    L_0x016b:
        r1 = r9.charAt(r7);
        r3 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        if (r1 != r3) goto L_0x000f;
    L_0x0173:
        r1 = r9.charAt(r6);
        r3 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        if (r1 != r3) goto L_0x000f;
    L_0x017b:
        r1 = 43;
        goto L_0x001b;
    L_0x017f:
        r1 = "void";
        r2 = 126; // 0x7e float:1.77E-43 double:6.23E-322;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x0189:
        r1 = "with";
        r2 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x0193:
        r3 = r9.charAt(r7);
        switch(r3) {
            case 97: goto L_0x019d;
            case 98: goto L_0x019a;
            case 99: goto L_0x019a;
            case 100: goto L_0x019a;
            case 101: goto L_0x01a2;
            case 102: goto L_0x019a;
            case 103: goto L_0x019a;
            case 104: goto L_0x019a;
            case 105: goto L_0x01c2;
            case 106: goto L_0x019a;
            case 107: goto L_0x019a;
            case 108: goto L_0x01cc;
            case 109: goto L_0x019a;
            case 110: goto L_0x01d6;
            case 111: goto L_0x01f1;
            case 112: goto L_0x0207;
            case 113: goto L_0x019a;
            case 114: goto L_0x020c;
            case 115: goto L_0x019a;
            case 116: goto L_0x0216;
            default: goto L_0x019a;
        };
    L_0x019a:
        r1 = r0;
        goto L_0x0010;
    L_0x019d:
        r2 = "class";
        goto L_0x0010;
    L_0x01a2:
        r1 = r9.charAt(r0);
        r3 = 98;
        if (r1 != r3) goto L_0x01b4;
    L_0x01aa:
        r1 = "break";
        r2 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x01b4:
        r3 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        if (r1 != r3) goto L_0x000f;
    L_0x01b8:
        r1 = "yield";
        r2 = 72;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x01c2:
        r1 = "while";
        r2 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x01cc:
        r1 = "false";
        r2 = 44;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x01d6:
        r3 = r9.charAt(r0);
        r4 = 99;
        if (r3 != r4) goto L_0x01e8;
    L_0x01de:
        r1 = "const";
        r2 = 154; // 0x9a float:2.16E-43 double:7.6E-322;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x01e8:
        r4 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r3 != r4) goto L_0x000f;
    L_0x01ec:
        r2 = "final";
        goto L_0x0010;
    L_0x01f1:
        r3 = r9.charAt(r0);
        r4 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r3 != r4) goto L_0x01fe;
    L_0x01f9:
        r2 = "float";
        goto L_0x0010;
    L_0x01fe:
        r4 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r3 != r4) goto L_0x000f;
    L_0x0202:
        r2 = "short";
        goto L_0x0010;
    L_0x0207:
        r2 = "super";
        goto L_0x0010;
    L_0x020c:
        r1 = "throw";
        r2 = 50;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x0216:
        r1 = "catch";
        r2 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x0220:
        r4 = r9.charAt(r6);
        switch(r4) {
            case 97: goto L_0x022a;
            case 101: goto L_0x022f;
            case 104: goto L_0x024c;
            case 109: goto L_0x0251;
            case 111: goto L_0x0256;
            case 116: goto L_0x025b;
            case 117: goto L_0x0260;
            case 119: goto L_0x0265;
            case 120: goto L_0x026c;
            case 121: goto L_0x0271;
            default: goto L_0x0227;
        };
    L_0x0227:
        r1 = r0;
        goto L_0x0010;
    L_0x022a:
        r2 = "native";
        goto L_0x0010;
    L_0x022f:
        r1 = r9.charAt(r0);
        r4 = 100;
        if (r1 != r4) goto L_0x0241;
    L_0x0237:
        r1 = "delete";
        r2 = 31;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x0241:
        if (r1 != r3) goto L_0x000f;
    L_0x0243:
        r1 = "return";
        r2 = 4;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x024c:
        r2 = "throws";
        goto L_0x0010;
    L_0x0251:
        r2 = "import";
        goto L_0x0010;
    L_0x0256:
        r2 = "double";
        goto L_0x0010;
    L_0x025b:
        r2 = "static";
        goto L_0x0010;
    L_0x0260:
        r2 = "public";
        goto L_0x0010;
    L_0x0265:
        r1 = "switch";
        r2 = r1;
        r1 = r3;
        goto L_0x0010;
    L_0x026c:
        r2 = "export";
        goto L_0x0010;
    L_0x0271:
        r1 = "typeof";
        r2 = 32;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x027b:
        r3 = r9.charAt(r6);
        switch(r3) {
            case 97: goto L_0x0285;
            case 101: goto L_0x028a;
            case 105: goto L_0x0294;
            case 111: goto L_0x029e;
            case 114: goto L_0x02a3;
            case 120: goto L_0x02a8;
            default: goto L_0x0282;
        };
    L_0x0282:
        r1 = r0;
        goto L_0x0010;
    L_0x0285:
        r2 = "package";
        goto L_0x0010;
    L_0x028a:
        r1 = "default";
        r2 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x0294:
        r1 = "finally";
        r2 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x029e:
        r2 = "boolean";
        goto L_0x0010;
    L_0x02a3:
        r2 = "private";
        goto L_0x0010;
    L_0x02a8:
        r2 = "extends";
        goto L_0x0010;
    L_0x02ad:
        r3 = r9.charAt(r0);
        switch(r3) {
            case 97: goto L_0x02b7;
            case 99: goto L_0x02bc;
            case 100: goto L_0x02c6;
            case 102: goto L_0x02d0;
            case 118: goto L_0x02da;
            default: goto L_0x02b4;
        };
    L_0x02b4:
        r1 = r0;
        goto L_0x0010;
    L_0x02b7:
        r2 = "abstract";
        goto L_0x0010;
    L_0x02bc:
        r1 = "continue";
        r2 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x02c6:
        r1 = "debugger";
        r2 = 160; // 0xa0 float:2.24E-43 double:7.9E-322;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x02d0:
        r1 = "function";
        r2 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x02da:
        r2 = "volatile";
        goto L_0x0010;
    L_0x02df:
        r3 = r9.charAt(r0);
        r4 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        if (r3 != r4) goto L_0x02ec;
    L_0x02e7:
        r2 = "interface";
        goto L_0x0010;
    L_0x02ec:
        r4 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        if (r3 != r4) goto L_0x02f5;
    L_0x02f0:
        r2 = "protected";
        goto L_0x0010;
    L_0x02f5:
        r4 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        if (r3 != r4) goto L_0x000f;
    L_0x02f9:
        r2 = "transient";
        goto L_0x0010;
    L_0x02fe:
        r3 = r9.charAt(r6);
        r4 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        if (r3 != r4) goto L_0x030b;
    L_0x0306:
        r2 = "implements";
        goto L_0x0010;
    L_0x030b:
        r1 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        if (r3 != r1) goto L_0x000f;
    L_0x030f:
        r1 = "instanceof";
        r2 = 53;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0010;
    L_0x0319:
        r2 = "synchronized";
        goto L_0x0010;
    L_0x031e:
        r0 = r1 & 255;
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.TokenStream.stringToKeyword(java.lang.String):int");
    }

    final String getSourceString() {
        return this.sourceString;
    }

    final int getLineno() {
        return this.lineno;
    }

    final String getString() {
        return this.string;
    }

    final char getQuoteChar() {
        return (char) this.quoteChar;
    }

    final double getNumber() {
        return this.number;
    }

    final boolean isNumberOctal() {
        return this.isOctal;
    }

    final boolean isNumberHex() {
        return this.isHex;
    }

    final boolean eof() {
        return this.hitEOF;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final int getToken() throws java.io.IOException {
        /*
        r9 = this;
        r1 = 10;
        r5 = -1;
        r2 = 1;
        r7 = 61;
        r3 = 0;
    L_0x0007:
        r4 = r9.getChar();
        if (r4 != r5) goto L_0x0019;
    L_0x000d:
        r0 = r9.cursor;
        r0 = r0 + -1;
        r9.tokenBeg = r0;
        r0 = r9.cursor;
        r9.tokenEnd = r0;
        r1 = r3;
    L_0x0018:
        return r1;
    L_0x0019:
        if (r4 != r1) goto L_0x0029;
    L_0x001b:
        r9.dirtyLine = r3;
        r0 = r9.cursor;
        r0 = r0 + -1;
        r9.tokenBeg = r0;
        r0 = r9.cursor;
        r9.tokenEnd = r0;
        r1 = r2;
        goto L_0x0018;
    L_0x0029:
        r0 = isJSSpace(r4);
        if (r0 != 0) goto L_0x0007;
    L_0x002f:
        r0 = 45;
        if (r4 == r0) goto L_0x0035;
    L_0x0033:
        r9.dirtyLine = r2;
    L_0x0035:
        r0 = r9.cursor;
        r0 = r0 + -1;
        r9.tokenBeg = r0;
        r0 = r9.cursor;
        r9.tokenEnd = r0;
        r0 = 64;
        if (r4 != r0) goto L_0x0046;
    L_0x0043:
        r1 = 147; // 0x93 float:2.06E-43 double:7.26E-322;
        goto L_0x0018;
    L_0x0046:
        r0 = 92;
        if (r4 != r0) goto L_0x007e;
    L_0x004a:
        r0 = r9.getChar();
        r4 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        if (r0 != r4) goto L_0x0076;
    L_0x0052:
        r9.stringBufferTop = r3;
        r4 = r2;
        r6 = r2;
    L_0x0056:
        if (r6 == 0) goto L_0x012c;
    L_0x0058:
        r0 = r4;
    L_0x0059:
        if (r0 == 0) goto L_0x0096;
    L_0x005b:
        r1 = r3;
        r0 = r3;
    L_0x005d:
        r6 = 4;
        if (r1 == r6) goto L_0x006a;
    L_0x0060:
        r6 = r9.getChar();
        r0 = org.mozilla.javascript.Kit.xDigitToInt(r6, r0);
        if (r0 >= 0) goto L_0x008e;
    L_0x006a:
        if (r0 >= 0) goto L_0x0091;
    L_0x006c:
        r0 = r9.parser;
        r1 = "msg.invalid.escape";
        r0.addError(r1);
        r1 = r5;
        goto L_0x0018;
    L_0x0076:
        r9.ungetChar(r0);
        r0 = 92;
        r4 = r3;
        r6 = r3;
        goto L_0x0056;
    L_0x007e:
        r0 = (char) r4;
        r0 = java.lang.Character.isJavaIdentifierStart(r0);
        if (r0 == 0) goto L_0x008a;
    L_0x0085:
        r9.stringBufferTop = r3;
        r9.addToString(r4);
    L_0x008a:
        r6 = r0;
        r0 = r4;
        r4 = r3;
        goto L_0x0056;
    L_0x008e:
        r1 = r1 + 1;
        goto L_0x005d;
    L_0x0091:
        r9.addToString(r0);
        r0 = r3;
        goto L_0x0059;
    L_0x0096:
        r1 = r9.getChar();
        r6 = 92;
        if (r1 != r6) goto L_0x00b4;
    L_0x009e:
        r0 = r9.getChar();
        r1 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        if (r0 != r1) goto L_0x00a9;
    L_0x00a6:
        r4 = r2;
        r0 = r2;
        goto L_0x0059;
    L_0x00a9:
        r0 = r9.parser;
        r1 = "msg.illegal.character";
        r0.addError(r1);
        r1 = r5;
        goto L_0x0018;
    L_0x00b4:
        if (r1 == r5) goto L_0x00c2;
    L_0x00b6:
        r6 = 65279; // 0xfeff float:9.1475E-41 double:3.2252E-319;
        if (r1 == r6) goto L_0x00c2;
    L_0x00bb:
        r6 = (char) r1;
        r6 = java.lang.Character.isJavaIdentifierPart(r6);
        if (r6 != 0) goto L_0x0118;
    L_0x00c2:
        r9.ungetChar(r1);
        r2 = r9.getStringFromBuffer();
        if (r4 != 0) goto L_0x0121;
    L_0x00cb:
        r0 = stringToKeyword(r2);
        if (r0 == 0) goto L_0x0109;
    L_0x00d1:
        r1 = 153; // 0x99 float:2.14E-43 double:7.56E-322;
        if (r0 == r1) goto L_0x00d9;
    L_0x00d5:
        r1 = 72;
        if (r0 != r1) goto L_0x0577;
    L_0x00d9:
        r1 = r9.parser;
        r1 = r1.compilerEnv;
        r1 = r1.getLanguageVersion();
        r3 = 170; // 0xaa float:2.38E-43 double:8.4E-322;
        if (r1 >= r3) goto L_0x0577;
    L_0x00e5:
        r1 = 153; // 0x99 float:2.14E-43 double:7.56E-322;
        if (r0 != r1) goto L_0x011d;
    L_0x00e9:
        r0 = "let";
    L_0x00ec:
        r9.string = r0;
        r0 = 39;
        r1 = r0;
    L_0x00f1:
        r0 = r9.allStrings;
        r0 = r0.intern(r2);
        r0 = (java.lang.String) r0;
        r9.string = r0;
        r0 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        if (r1 != r0) goto L_0x0018;
    L_0x00ff:
        r0 = r9.parser;
        r0 = r0.compilerEnv;
        r0 = r0.isReservedKeywordAsIdentifier();
        if (r0 == 0) goto L_0x0018;
    L_0x0109:
        r0 = r2;
    L_0x010a:
        r1 = r9.allStrings;
        r0 = r1.intern(r0);
        r0 = (java.lang.String) r0;
        r9.string = r0;
        r1 = 39;
        goto L_0x0018;
    L_0x0118:
        r9.addToString(r1);
        goto L_0x0059;
    L_0x011d:
        r0 = "yield";
        goto L_0x00ec;
    L_0x0121:
        r0 = isKeyword(r2);
        if (r0 == 0) goto L_0x0574;
    L_0x0127:
        r0 = r9.convertLastCharToHex(r2);
        goto L_0x010a;
    L_0x012c:
        r4 = isDigit(r0);
        if (r4 != 0) goto L_0x0140;
    L_0x0132:
        r4 = 46;
        if (r0 != r4) goto L_0x0238;
    L_0x0136:
        r4 = r9.peekChar();
        r4 = isDigit(r4);
        if (r4 == 0) goto L_0x0238;
    L_0x0140:
        r9.isOctal = r3;
        r9.stringBufferTop = r3;
        r9.isOctal = r3;
        r9.isHex = r3;
        r4 = 48;
        if (r0 != r4) goto L_0x0182;
    L_0x014c:
        r0 = r9.getChar();
        r4 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        if (r0 == r4) goto L_0x0158;
    L_0x0154:
        r4 = 88;
        if (r0 != r4) goto L_0x0172;
    L_0x0158:
        r4 = 16;
        r9.isHex = r2;
        r0 = r9.getChar();
    L_0x0160:
        r6 = 16;
        if (r4 != r6) goto L_0x056f;
    L_0x0164:
        r6 = org.mozilla.javascript.Kit.xDigitToInt(r0, r3);
        if (r6 < 0) goto L_0x01b3;
    L_0x016a:
        r9.addToString(r0);
        r0 = r9.getChar();
        goto L_0x0164;
    L_0x0172:
        r4 = isDigit(r0);
        if (r4 == 0) goto L_0x017d;
    L_0x0178:
        r4 = 8;
        r9.isOctal = r2;
        goto L_0x0160;
    L_0x017d:
        r4 = 48;
        r9.addToString(r4);
    L_0x0182:
        r4 = r1;
        goto L_0x0160;
    L_0x0184:
        r6 = 48;
        if (r6 > r4) goto L_0x01b0;
    L_0x0188:
        r6 = 57;
        if (r4 > r6) goto L_0x01b0;
    L_0x018c:
        r6 = 8;
        if (r0 != r6) goto L_0x01a4;
    L_0x0190:
        r6 = 56;
        if (r4 < r6) goto L_0x01a4;
    L_0x0194:
        r6 = r9.parser;
        r7 = "msg.bad.octal.literal";
        r0 = 56;
        if (r4 != r0) goto L_0x01ac;
    L_0x019d:
        r0 = "8";
    L_0x01a0:
        r6.addWarning(r7, r0);
        r0 = r1;
    L_0x01a4:
        r9.addToString(r4);
        r4 = r9.getChar();
        goto L_0x0184;
    L_0x01ac:
        r0 = "9";
        goto L_0x01a0;
    L_0x01b0:
        r8 = r0;
        r0 = r4;
        r4 = r8;
    L_0x01b3:
        if (r4 != r1) goto L_0x056a;
    L_0x01b5:
        r6 = 46;
        if (r0 == r6) goto L_0x01c1;
    L_0x01b9:
        r6 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r0 == r6) goto L_0x01c1;
    L_0x01bd:
        r6 = 69;
        if (r0 != r6) goto L_0x056a;
    L_0x01c1:
        r2 = 46;
        if (r0 != r2) goto L_0x01d2;
    L_0x01c5:
        r9.addToString(r0);
        r0 = r9.getChar();
        r2 = isDigit(r0);
        if (r2 != 0) goto L_0x01c5;
    L_0x01d2:
        r2 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r0 == r2) goto L_0x01da;
    L_0x01d6:
        r2 = 69;
        if (r0 != r2) goto L_0x020e;
    L_0x01da:
        r9.addToString(r0);
        r0 = r9.getChar();
        r2 = 43;
        if (r0 == r2) goto L_0x01e9;
    L_0x01e5:
        r2 = 45;
        if (r0 != r2) goto L_0x01f0;
    L_0x01e9:
        r9.addToString(r0);
        r0 = r9.getChar();
    L_0x01f0:
        r2 = isDigit(r0);
        if (r2 != 0) goto L_0x0201;
    L_0x01f6:
        r0 = r9.parser;
        r1 = "msg.missing.exponent";
        r0.addError(r1);
        r1 = r5;
        goto L_0x0018;
    L_0x0201:
        r9.addToString(r0);
        r0 = r9.getChar();
        r2 = isDigit(r0);
        if (r2 != 0) goto L_0x0201;
    L_0x020e:
        r2 = r0;
        r0 = r3;
    L_0x0210:
        r9.ungetChar(r2);
        r2 = r9.getStringFromBuffer();
        r9.string = r2;
        if (r4 != r1) goto L_0x0233;
    L_0x021b:
        if (r0 != 0) goto L_0x0233;
    L_0x021d:
        r0 = java.lang.Double.parseDouble(r2);	 Catch:{ NumberFormatException -> 0x0227 }
    L_0x0221:
        r9.number = r0;
        r1 = 40;
        goto L_0x0018;
    L_0x0227:
        r0 = move-exception;
        r0 = r9.parser;
        r1 = "msg.caught.nfe";
        r0.addError(r1);
        r1 = r5;
        goto L_0x0018;
    L_0x0233:
        r0 = org.mozilla.javascript.ScriptRuntime.stringToNumber(r2, r3, r4);
        goto L_0x0221;
    L_0x0238:
        r4 = 34;
        if (r0 == r4) goto L_0x0240;
    L_0x023c:
        r4 = 39;
        if (r0 != r4) goto L_0x031e;
    L_0x0240:
        r9.quoteChar = r0;
        r9.stringBufferTop = r3;
        r0 = r9.getChar(r3);
    L_0x0248:
        r2 = r9.quoteChar;
        if (r0 == r2) goto L_0x030c;
    L_0x024c:
        if (r0 == r1) goto L_0x0250;
    L_0x024e:
        if (r0 != r5) goto L_0x0262;
    L_0x0250:
        r9.ungetChar(r0);
        r0 = r9.cursor;
        r9.tokenEnd = r0;
        r0 = r9.parser;
        r1 = "msg.unterminated.string.lit";
        r0.addError(r1);
        r1 = r5;
        goto L_0x0018;
    L_0x0262:
        r2 = 92;
        if (r0 != r2) goto L_0x02a4;
    L_0x0266:
        r0 = r9.getChar();
        switch(r0) {
            case 10: goto L_0x0306;
            case 98: goto L_0x02ac;
            case 102: goto L_0x02af;
            case 110: goto L_0x02b2;
            case 114: goto L_0x02b4;
            case 116: goto L_0x02b7;
            case 117: goto L_0x02bd;
            case 118: goto L_0x02ba;
            case 120: goto L_0x02de;
            default: goto L_0x026d;
        };
    L_0x026d:
        r2 = 48;
        if (r2 > r0) goto L_0x02a4;
    L_0x0271:
        r2 = 56;
        if (r0 >= r2) goto L_0x02a4;
    L_0x0275:
        r0 = r0 + -48;
        r2 = r9.getChar();
        r4 = 48;
        if (r4 > r2) goto L_0x02a1;
    L_0x027f:
        r4 = 56;
        if (r2 >= r4) goto L_0x02a1;
    L_0x0283:
        r0 = r0 * 8;
        r0 = r0 + r2;
        r0 = r0 + -48;
        r2 = r9.getChar();
        r4 = 48;
        if (r4 > r2) goto L_0x02a1;
    L_0x0290:
        r4 = 56;
        if (r2 >= r4) goto L_0x02a1;
    L_0x0294:
        r4 = 31;
        if (r0 > r4) goto L_0x02a1;
    L_0x0298:
        r0 = r0 * 8;
        r0 = r0 + r2;
        r0 = r0 + -48;
        r2 = r9.getChar();
    L_0x02a1:
        r9.ungetChar(r2);
    L_0x02a4:
        r9.addToString(r0);
        r0 = r9.getChar(r3);
        goto L_0x0248;
    L_0x02ac:
        r0 = 8;
        goto L_0x02a4;
    L_0x02af:
        r0 = 12;
        goto L_0x02a4;
    L_0x02b2:
        r0 = r1;
        goto L_0x02a4;
    L_0x02b4:
        r0 = 13;
        goto L_0x02a4;
    L_0x02b7:
        r0 = 9;
        goto L_0x02a4;
    L_0x02ba:
        r0 = 11;
        goto L_0x02a4;
    L_0x02bd:
        r6 = r9.stringBufferTop;
        r0 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        r9.addToString(r0);
        r4 = r3;
        r2 = r3;
    L_0x02c6:
        r0 = 4;
        if (r4 == r0) goto L_0x02da;
    L_0x02c9:
        r0 = r9.getChar();
        r2 = org.mozilla.javascript.Kit.xDigitToInt(r0, r2);
        if (r2 < 0) goto L_0x0248;
    L_0x02d3:
        r9.addToString(r0);
        r0 = r4 + 1;
        r4 = r0;
        goto L_0x02c6;
    L_0x02da:
        r9.stringBufferTop = r6;
        r0 = r2;
        goto L_0x02a4;
    L_0x02de:
        r0 = r9.getChar();
        r4 = org.mozilla.javascript.Kit.xDigitToInt(r0, r3);
        if (r4 >= 0) goto L_0x02ef;
    L_0x02e8:
        r2 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        r9.addToString(r2);
        goto L_0x0248;
    L_0x02ef:
        r2 = r9.getChar();
        r4 = org.mozilla.javascript.Kit.xDigitToInt(r2, r4);
        if (r4 >= 0) goto L_0x0304;
    L_0x02f9:
        r4 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        r9.addToString(r4);
        r9.addToString(r0);
        r0 = r2;
        goto L_0x0248;
    L_0x0304:
        r0 = r4;
        goto L_0x02a4;
    L_0x0306:
        r0 = r9.getChar();
        goto L_0x0248;
    L_0x030c:
        r0 = r9.getStringFromBuffer();
        r1 = r9.allStrings;
        r0 = r1.intern(r0);
        r0 = (java.lang.String) r0;
        r9.string = r0;
        r1 = 41;
        goto L_0x0018;
    L_0x031e:
        switch(r0) {
            case 33: goto L_0x03d2;
            case 37: goto L_0x0506;
            case 38: goto L_0x03a0;
            case 40: goto L_0x0340;
            case 41: goto L_0x0344;
            case 42: goto L_0x047b;
            case 43: goto L_0x0518;
            case 44: goto L_0x0348;
            case 45: goto L_0x0532;
            case 46: goto L_0x0360;
            case 47: goto L_0x0489;
            case 58: goto L_0x0350;
            case 59: goto L_0x032c;
            case 60: goto L_0x03ea;
            case 61: goto L_0x03ba;
            case 62: goto L_0x0441;
            case 63: goto L_0x034c;
            case 91: goto L_0x0330;
            case 93: goto L_0x0334;
            case 94: goto L_0x0396;
            case 123: goto L_0x0338;
            case 124: goto L_0x037c;
            case 125: goto L_0x033c;
            case 126: goto L_0x0514;
            default: goto L_0x0321;
        };
    L_0x0321:
        r0 = r9.parser;
        r1 = "msg.illegal.character";
        r0.addError(r1);
        r1 = r5;
        goto L_0x0018;
    L_0x032c:
        r1 = 82;
        goto L_0x0018;
    L_0x0330:
        r1 = 83;
        goto L_0x0018;
    L_0x0334:
        r1 = 84;
        goto L_0x0018;
    L_0x0338:
        r1 = 85;
        goto L_0x0018;
    L_0x033c:
        r1 = 86;
        goto L_0x0018;
    L_0x0340:
        r1 = 87;
        goto L_0x0018;
    L_0x0344:
        r1 = 88;
        goto L_0x0018;
    L_0x0348:
        r1 = 89;
        goto L_0x0018;
    L_0x034c:
        r1 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        goto L_0x0018;
    L_0x0350:
        r0 = 58;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x035c;
    L_0x0358:
        r1 = 144; // 0x90 float:2.02E-43 double:7.1E-322;
        goto L_0x0018;
    L_0x035c:
        r1 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        goto L_0x0018;
    L_0x0360:
        r0 = 46;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x036c;
    L_0x0368:
        r1 = 143; // 0x8f float:2.0E-43 double:7.07E-322;
        goto L_0x0018;
    L_0x036c:
        r0 = 40;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x0378;
    L_0x0374:
        r1 = 146; // 0x92 float:2.05E-43 double:7.2E-322;
        goto L_0x0018;
    L_0x0378:
        r1 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        goto L_0x0018;
    L_0x037c:
        r0 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x0388;
    L_0x0384:
        r1 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        goto L_0x0018;
    L_0x0388:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x0392;
    L_0x038e:
        r1 = 91;
        goto L_0x0018;
    L_0x0392:
        r1 = 9;
        goto L_0x0018;
    L_0x0396:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x0018;
    L_0x039c:
        r1 = 92;
        goto L_0x0018;
    L_0x03a0:
        r0 = 38;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x03ac;
    L_0x03a8:
        r1 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        goto L_0x0018;
    L_0x03ac:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x03b6;
    L_0x03b2:
        r1 = 93;
        goto L_0x0018;
    L_0x03b6:
        r1 = 11;
        goto L_0x0018;
    L_0x03ba:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x03ce;
    L_0x03c0:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x03ca;
    L_0x03c6:
        r1 = 46;
        goto L_0x0018;
    L_0x03ca:
        r1 = 12;
        goto L_0x0018;
    L_0x03ce:
        r1 = 90;
        goto L_0x0018;
    L_0x03d2:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x03e6;
    L_0x03d8:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x03e2;
    L_0x03de:
        r1 = 47;
        goto L_0x0018;
    L_0x03e2:
        r1 = 13;
        goto L_0x0018;
    L_0x03e6:
        r1 = 26;
        goto L_0x0018;
    L_0x03ea:
        r0 = 33;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x041d;
    L_0x03f2:
        r0 = 45;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x0418;
    L_0x03fa:
        r0 = 45;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x0413;
    L_0x0402:
        r0 = r9.cursor;
        r0 = r0 + -4;
        r9.tokenBeg = r0;
        r9.skipLine();
        r0 = org.mozilla.javascript.Token.CommentType.HTML;
        r9.commentType = r0;
        r1 = 161; // 0xa1 float:2.26E-43 double:7.95E-322;
        goto L_0x0018;
    L_0x0413:
        r0 = 45;
        r9.ungetCharIgnoreLineEnd(r0);
    L_0x0418:
        r0 = 33;
        r9.ungetCharIgnoreLineEnd(r0);
    L_0x041d:
        r0 = 60;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x0433;
    L_0x0425:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x042f;
    L_0x042b:
        r1 = 94;
        goto L_0x0018;
    L_0x042f:
        r1 = 18;
        goto L_0x0018;
    L_0x0433:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x043d;
    L_0x0439:
        r1 = 15;
        goto L_0x0018;
    L_0x043d:
        r1 = 14;
        goto L_0x0018;
    L_0x0441:
        r0 = 62;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x046d;
    L_0x0449:
        r0 = 62;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x045f;
    L_0x0451:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x045b;
    L_0x0457:
        r1 = 96;
        goto L_0x0018;
    L_0x045b:
        r1 = 20;
        goto L_0x0018;
    L_0x045f:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x0469;
    L_0x0465:
        r1 = 95;
        goto L_0x0018;
    L_0x0469:
        r1 = 19;
        goto L_0x0018;
    L_0x046d:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x0477;
    L_0x0473:
        r1 = 17;
        goto L_0x0018;
    L_0x0477:
        r1 = 16;
        goto L_0x0018;
    L_0x047b:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x0485;
    L_0x0481:
        r1 = 99;
        goto L_0x0018;
    L_0x0485:
        r1 = 23;
        goto L_0x0018;
    L_0x0489:
        r9.markCommentStart();
        r0 = 47;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x04a5;
    L_0x0494:
        r0 = r9.cursor;
        r0 = r0 + -2;
        r9.tokenBeg = r0;
        r9.skipLine();
        r0 = org.mozilla.javascript.Token.CommentType.LINE;
        r9.commentType = r0;
        r1 = 161; // 0xa1 float:2.26E-43 double:7.95E-322;
        goto L_0x0018;
    L_0x04a5:
        r0 = 42;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x04f8;
    L_0x04ad:
        r0 = r9.cursor;
        r0 = r0 + -2;
        r9.tokenBeg = r0;
        r0 = 42;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x04d8;
    L_0x04bb:
        r0 = org.mozilla.javascript.Token.CommentType.JSDOC;
        r9.commentType = r0;
        r0 = r2;
    L_0x04c0:
        r1 = r9.getChar();
        if (r1 != r5) goto L_0x04de;
    L_0x04c6:
        r0 = r9.cursor;
        r0 = r0 + -1;
        r9.tokenEnd = r0;
        r0 = r9.parser;
        r1 = "msg.unterminated.comment";
        r0.addError(r1);
        r1 = 161; // 0xa1 float:2.26E-43 double:7.95E-322;
        goto L_0x0018;
    L_0x04d8:
        r0 = org.mozilla.javascript.Token.CommentType.BLOCK_COMMENT;
        r9.commentType = r0;
        r0 = r3;
        goto L_0x04c0;
    L_0x04de:
        r4 = 42;
        if (r1 != r4) goto L_0x04e4;
    L_0x04e2:
        r0 = r2;
        goto L_0x04c0;
    L_0x04e4:
        r4 = 47;
        if (r1 != r4) goto L_0x04f2;
    L_0x04e8:
        if (r0 == 0) goto L_0x04c0;
    L_0x04ea:
        r0 = r9.cursor;
        r9.tokenEnd = r0;
        r1 = 161; // 0xa1 float:2.26E-43 double:7.95E-322;
        goto L_0x0018;
    L_0x04f2:
        r0 = r9.cursor;
        r9.tokenEnd = r0;
        r0 = r3;
        goto L_0x04c0;
    L_0x04f8:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x0502;
    L_0x04fe:
        r1 = 100;
        goto L_0x0018;
    L_0x0502:
        r1 = 24;
        goto L_0x0018;
    L_0x0506:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x0510;
    L_0x050c:
        r1 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0018;
    L_0x0510:
        r1 = 25;
        goto L_0x0018;
    L_0x0514:
        r1 = 27;
        goto L_0x0018;
    L_0x0518:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x0522;
    L_0x051e:
        r1 = 97;
        goto L_0x0018;
    L_0x0522:
        r0 = 43;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x052e;
    L_0x052a:
        r1 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        goto L_0x0018;
    L_0x052e:
        r1 = 21;
        goto L_0x0018;
    L_0x0532:
        r0 = r9.matchChar(r7);
        if (r0 == 0) goto L_0x053f;
    L_0x0538:
        r0 = 98;
    L_0x053a:
        r9.dirtyLine = r2;
        r1 = r0;
        goto L_0x0018;
    L_0x053f:
        r0 = 45;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x0567;
    L_0x0547:
        r0 = r9.dirtyLine;
        if (r0 != 0) goto L_0x0564;
    L_0x054b:
        r0 = 62;
        r0 = r9.matchChar(r0);
        if (r0 == 0) goto L_0x0564;
    L_0x0553:
        r0 = "--";
        r9.markCommentStart(r0);
        r9.skipLine();
        r0 = org.mozilla.javascript.Token.CommentType.HTML;
        r9.commentType = r0;
        r1 = 161; // 0xa1 float:2.26E-43 double:7.95E-322;
        goto L_0x0018;
    L_0x0564:
        r0 = 107; // 0x6b float:1.5E-43 double:5.3E-322;
        goto L_0x053a;
    L_0x0567:
        r0 = 22;
        goto L_0x053a;
    L_0x056a:
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x0210;
    L_0x056f:
        r8 = r4;
        r4 = r0;
        r0 = r8;
        goto L_0x0184;
    L_0x0574:
        r0 = r2;
        goto L_0x010a;
    L_0x0577:
        r1 = r0;
        goto L_0x00f1;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.TokenStream.getToken():int");
    }

    private static boolean isAlpha(int i) {
        if (i <= 90) {
            if (65 <= i) {
                return true;
            }
            return false;
        } else if (97 > i || i > 122) {
            return false;
        } else {
            return true;
        }
    }

    static boolean isDigit(int i) {
        return 48 <= i && i <= 57;
    }

    static boolean isJSSpace(int i) {
        if (i <= 127) {
            if (i == 32 || i == 9 || i == 12 || i == 11) {
                return true;
            }
            return false;
        } else if (i == 160 || i == 65279 || Character.getType((char) i) == 12) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isJSFormatChar(int i) {
        return i > 127 && Character.getType((char) i) == 16;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void readRegExp(int r10) throws java.io.IOException {
        /*
        r9 = this;
        r8 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        r7 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        r6 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        r5 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        r1 = 0;
        r3 = r9.tokenBeg;
        r9.stringBufferTop = r1;
        r0 = 100;
        if (r10 != r0) goto L_0x0045;
    L_0x0011:
        r0 = 61;
        r9.addToString(r0);
    L_0x0016:
        r0 = r1;
    L_0x0017:
        r2 = r9.getChar();
        r4 = 47;
        if (r2 != r4) goto L_0x0021;
    L_0x001f:
        if (r0 == 0) goto L_0x0068;
    L_0x0021:
        r4 = 10;
        if (r2 == r4) goto L_0x0028;
    L_0x0025:
        r4 = -1;
        if (r2 != r4) goto L_0x004d;
    L_0x0028:
        r9.ungetChar(r2);
        r0 = r9.cursor;
        r0 = r0 + -1;
        r9.tokenEnd = r0;
        r0 = new java.lang.String;
        r2 = r9.stringBuffer;
        r3 = r9.stringBufferTop;
        r0.<init>(r2, r1, r3);
        r9.string = r0;
        r0 = r9.parser;
        r1 = "msg.unterminated.re.lit";
        r0.reportError(r1);
    L_0x0044:
        return;
    L_0x0045:
        r0 = 24;
        if (r10 == r0) goto L_0x0016;
    L_0x0049:
        org.mozilla.javascript.Kit.codeBug();
        goto L_0x0016;
    L_0x004d:
        r4 = 92;
        if (r2 != r4) goto L_0x005c;
    L_0x0051:
        r9.addToString(r2);
        r2 = r9.getChar();
    L_0x0058:
        r9.addToString(r2);
        goto L_0x0017;
    L_0x005c:
        r4 = 91;
        if (r2 != r4) goto L_0x0062;
    L_0x0060:
        r0 = 1;
        goto L_0x0058;
    L_0x0062:
        r4 = 93;
        if (r2 != r4) goto L_0x0058;
    L_0x0066:
        r0 = r1;
        goto L_0x0058;
    L_0x0068:
        r0 = r9.stringBufferTop;
    L_0x006a:
        r2 = r9.matchChar(r5);
        if (r2 == 0) goto L_0x0074;
    L_0x0070:
        r9.addToString(r5);
        goto L_0x006a;
    L_0x0074:
        r2 = r9.matchChar(r6);
        if (r2 == 0) goto L_0x007e;
    L_0x007a:
        r9.addToString(r6);
        goto L_0x006a;
    L_0x007e:
        r2 = r9.matchChar(r7);
        if (r2 == 0) goto L_0x0088;
    L_0x0084:
        r9.addToString(r7);
        goto L_0x006a;
    L_0x0088:
        r2 = r9.matchChar(r8);
        if (r2 == 0) goto L_0x0092;
    L_0x008e:
        r9.addToString(r8);
        goto L_0x006a;
    L_0x0092:
        r2 = r9.stringBufferTop;
        r2 = r2 + r3;
        r2 = r2 + 2;
        r9.tokenEnd = r2;
        r2 = r9.peekChar();
        r2 = isAlpha(r2);
        if (r2 == 0) goto L_0x00ab;
    L_0x00a3:
        r2 = r9.parser;
        r3 = "msg.invalid.re.flag";
        r2.reportError(r3);
    L_0x00ab:
        r2 = new java.lang.String;
        r3 = r9.stringBuffer;
        r2.<init>(r3, r1, r0);
        r9.string = r2;
        r1 = new java.lang.String;
        r2 = r9.stringBuffer;
        r3 = r9.stringBufferTop;
        r3 = r3 - r0;
        r1.<init>(r2, r0, r3);
        r9.regExpFlags = r1;
        goto L_0x0044;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.TokenStream.readRegExp(int):void");
    }

    String readAndClearRegExpFlags() {
        String str = this.regExpFlags;
        this.regExpFlags = null;
        return str;
    }

    boolean isXMLAttribute() {
        return this.xmlIsAttribute;
    }

    int getFirstXMLToken() throws IOException {
        this.xmlOpenTagsCount = 0;
        this.xmlIsAttribute = false;
        this.xmlIsTagContent = false;
        if (!canUngetChar()) {
            return -1;
        }
        ungetChar(60);
        return getNextXMLToken();
    }

    int getNextXMLToken() throws IOException {
        this.tokenBeg = this.cursor;
        this.stringBufferTop = 0;
        int i = getChar();
        while (i != -1) {
            if (!this.xmlIsTagContent) {
                switch (i) {
                    case 60:
                        addToString(i);
                        switch (peekChar()) {
                            case 33:
                                addToString(getChar());
                                switch (peekChar()) {
                                    case 45:
                                        addToString(getChar());
                                        i = getChar();
                                        if (i == 45) {
                                            addToString(i);
                                            if (readXmlComment()) {
                                                break;
                                            }
                                            return -1;
                                        }
                                        this.stringBufferTop = 0;
                                        this.string = null;
                                        this.parser.addError("msg.XML.bad.form");
                                        return -1;
                                    case 91:
                                        addToString(getChar());
                                        if (getChar() == 67 && getChar() == 68 && getChar() == 65 && getChar() == 84 && getChar() == 65 && getChar() == 91) {
                                            addToString(67);
                                            addToString(68);
                                            addToString(65);
                                            addToString(84);
                                            addToString(65);
                                            addToString(91);
                                            if (readCDATA()) {
                                                break;
                                            }
                                            return -1;
                                        }
                                        this.stringBufferTop = 0;
                                        this.string = null;
                                        this.parser.addError("msg.XML.bad.form");
                                        return -1;
                                    default:
                                        if (readEntity()) {
                                            break;
                                        }
                                        return -1;
                                }
                            case 47:
                                addToString(getChar());
                                if (this.xmlOpenTagsCount != 0) {
                                    this.xmlIsTagContent = true;
                                    this.xmlOpenTagsCount--;
                                    break;
                                }
                                this.stringBufferTop = 0;
                                this.string = null;
                                this.parser.addError("msg.XML.bad.form");
                                return -1;
                            case 63:
                                addToString(getChar());
                                if (readPI()) {
                                    break;
                                }
                                return -1;
                            default:
                                this.xmlIsTagContent = true;
                                this.xmlOpenTagsCount++;
                                break;
                        }
                    case 123:
                        ungetChar(i);
                        this.string = getStringFromBuffer();
                        return 145;
                    default:
                        addToString(i);
                        break;
                }
            }
            switch (i) {
                case 9:
                case 10:
                case 13:
                case 32:
                    addToString(i);
                    break;
                case 34:
                case 39:
                    addToString(i);
                    if (!readQuotedString(i)) {
                        return -1;
                    }
                    break;
                case 47:
                    addToString(i);
                    if (peekChar() == 62) {
                        addToString(getChar());
                        this.xmlIsTagContent = false;
                        this.xmlOpenTagsCount--;
                        break;
                    }
                    break;
                case 61:
                    addToString(i);
                    this.xmlIsAttribute = true;
                    break;
                case 62:
                    addToString(i);
                    this.xmlIsTagContent = false;
                    this.xmlIsAttribute = false;
                    break;
                case 123:
                    ungetChar(i);
                    this.string = getStringFromBuffer();
                    return 145;
                default:
                    addToString(i);
                    this.xmlIsAttribute = false;
                    break;
            }
            if (!this.xmlIsTagContent && this.xmlOpenTagsCount == 0) {
                this.string = getStringFromBuffer();
                return 148;
            }
            i = getChar();
        }
        this.tokenEnd = this.cursor;
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return -1;
    }

    private boolean readQuotedString(int i) throws IOException {
        int i2 = getChar();
        while (i2 != -1) {
            addToString(i2);
            if (i2 == i) {
                return true;
            }
            i2 = getChar();
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readXmlComment() throws IOException {
        int i = getChar();
        while (i != -1) {
            addToString(i);
            if (i == 45 && peekChar() == 45) {
                i = getChar();
                addToString(i);
                if (peekChar() == 62) {
                    addToString(getChar());
                    return true;
                }
            } else {
                i = getChar();
            }
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readCDATA() throws IOException {
        int i = getChar();
        while (i != -1) {
            addToString(i);
            if (i == 93 && peekChar() == 93) {
                i = getChar();
                addToString(i);
                if (peekChar() == 62) {
                    addToString(getChar());
                    return true;
                }
            } else {
                i = getChar();
            }
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readEntity() throws IOException {
        int i = getChar();
        int i2 = 1;
        while (i != -1) {
            addToString(i);
            switch (i) {
                case 60:
                    i2++;
                    break;
                case 62:
                    i2--;
                    if (i2 != 0) {
                        break;
                    }
                    return true;
                default:
                    break;
            }
            i = getChar();
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readPI() throws IOException {
        int i = getChar();
        while (i != -1) {
            addToString(i);
            if (i == 63 && peekChar() == 62) {
                addToString(getChar());
                return true;
            }
            i = getChar();
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private String getStringFromBuffer() {
        this.tokenEnd = this.cursor;
        return new String(this.stringBuffer, 0, this.stringBufferTop);
    }

    private void addToString(int i) {
        int i2 = this.stringBufferTop;
        if (i2 == this.stringBuffer.length) {
            Object obj = new char[(this.stringBuffer.length * 2)];
            System.arraycopy(this.stringBuffer, 0, obj, 0, i2);
            this.stringBuffer = obj;
        }
        this.stringBuffer[i2] = (char) i;
        this.stringBufferTop = i2 + 1;
    }

    private boolean canUngetChar() {
        return this.ungetCursor == 0 || this.ungetBuffer[this.ungetCursor - 1] != 10;
    }

    private void ungetChar(int i) {
        if (this.ungetCursor != 0 && this.ungetBuffer[this.ungetCursor - 1] == 10) {
            Kit.codeBug();
        }
        int[] iArr = this.ungetBuffer;
        int i2 = this.ungetCursor;
        this.ungetCursor = i2 + 1;
        iArr[i2] = i;
        this.cursor--;
    }

    private boolean matchChar(int i) throws IOException {
        int charIgnoreLineEnd = getCharIgnoreLineEnd();
        if (charIgnoreLineEnd == i) {
            this.tokenEnd = this.cursor;
            return true;
        }
        ungetCharIgnoreLineEnd(charIgnoreLineEnd);
        return false;
    }

    private int peekChar() throws IOException {
        int i = getChar();
        ungetChar(i);
        return i;
    }

    private int getChar() throws IOException {
        return getChar(true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getChar(boolean r8) throws java.io.IOException {
        /*
        r7 = this;
        r6 = 13;
        r5 = 1;
        r2 = -1;
        r1 = 10;
        r0 = r7.ungetCursor;
        if (r0 == 0) goto L_0x0039;
    L_0x000a:
        r0 = r7.cursor;
        r0 = r0 + 1;
        r7.cursor = r0;
        r0 = r7.ungetBuffer;
        r1 = r7.ungetCursor;
        r1 = r1 + -1;
        r7.ungetCursor = r1;
        r0 = r0[r1];
    L_0x001a:
        return r0;
    L_0x001b:
        r0 = r7.cursor;
        r0 = r0 + 1;
        r7.cursor = r0;
        r0 = r7.sourceString;
        r3 = r7.sourceCursor;
        r4 = r3 + 1;
        r7.sourceCursor = r4;
        r0 = r0.charAt(r3);
    L_0x002d:
        r3 = r7.lineEndChar;
        if (r3 < 0) goto L_0x0076;
    L_0x0031:
        r3 = r7.lineEndChar;
        if (r3 != r6) goto L_0x0068;
    L_0x0035:
        if (r0 != r1) goto L_0x0068;
    L_0x0037:
        r7.lineEndChar = r1;
    L_0x0039:
        r0 = r7.sourceString;
        if (r0 == 0) goto L_0x0047;
    L_0x003d:
        r0 = r7.sourceCursor;
        r3 = r7.sourceEnd;
        if (r0 != r3) goto L_0x001b;
    L_0x0043:
        r7.hitEOF = r5;
        r0 = r2;
        goto L_0x001a;
    L_0x0047:
        r0 = r7.sourceCursor;
        r3 = r7.sourceEnd;
        if (r0 != r3) goto L_0x0057;
    L_0x004d:
        r0 = r7.fillSourceBuffer();
        if (r0 != 0) goto L_0x0057;
    L_0x0053:
        r7.hitEOF = r5;
        r0 = r2;
        goto L_0x001a;
    L_0x0057:
        r0 = r7.cursor;
        r0 = r0 + 1;
        r7.cursor = r0;
        r0 = r7.sourceBuffer;
        r3 = r7.sourceCursor;
        r4 = r3 + 1;
        r7.sourceCursor = r4;
        r0 = r0[r3];
        goto L_0x002d;
    L_0x0068:
        r7.lineEndChar = r2;
        r3 = r7.sourceCursor;
        r3 = r3 + -1;
        r7.lineStart = r3;
        r3 = r7.lineno;
        r3 = r3 + 1;
        r7.lineno = r3;
    L_0x0076:
        r3 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        if (r0 > r3) goto L_0x0082;
    L_0x007a:
        if (r0 == r1) goto L_0x007e;
    L_0x007c:
        if (r0 != r6) goto L_0x001a;
    L_0x007e:
        r7.lineEndChar = r0;
        r0 = r1;
        goto L_0x001a;
    L_0x0082:
        r3 = 65279; // 0xfeff float:9.1475E-41 double:3.2252E-319;
        if (r0 == r3) goto L_0x001a;
    L_0x0087:
        if (r8 == 0) goto L_0x008f;
    L_0x0089:
        r3 = isJSFormatChar(r0);
        if (r3 != 0) goto L_0x0039;
    L_0x008f:
        r2 = org.mozilla.javascript.ScriptRuntime.isJSLineTerminator(r0);
        if (r2 == 0) goto L_0x001a;
    L_0x0095:
        r7.lineEndChar = r0;
        r0 = r1;
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.TokenStream.getChar(boolean):int");
    }

    private int getCharIgnoreLineEnd() throws IOException {
        if (this.ungetCursor != 0) {
            this.cursor++;
            int[] iArr = this.ungetBuffer;
            int i = this.ungetCursor - 1;
            this.ungetCursor = i;
            return iArr[i];
        }
        int charAt;
        do {
            int i2;
            if (this.sourceString != null) {
                if (this.sourceCursor == this.sourceEnd) {
                    this.hitEOF = true;
                    return -1;
                }
                this.cursor++;
                String str = this.sourceString;
                i2 = this.sourceCursor;
                this.sourceCursor = i2 + 1;
                charAt = str.charAt(i2);
            } else if (this.sourceCursor != this.sourceEnd || fillSourceBuffer()) {
                this.cursor++;
                char[] cArr = this.sourceBuffer;
                i2 = this.sourceCursor;
                this.sourceCursor = i2 + 1;
                charAt = cArr[i2];
            } else {
                this.hitEOF = true;
                return -1;
            }
            if (charAt <= 127) {
                if (charAt != 10 && charAt != 13) {
                    return charAt;
                }
                this.lineEndChar = charAt;
                return 10;
            } else if (charAt == 65279) {
                return charAt;
            }
        } while (isJSFormatChar(charAt));
        if (!ScriptRuntime.isJSLineTerminator(charAt)) {
            return charAt;
        }
        this.lineEndChar = charAt;
        return 10;
    }

    private void ungetCharIgnoreLineEnd(int i) {
        int[] iArr = this.ungetBuffer;
        int i2 = this.ungetCursor;
        this.ungetCursor = i2 + 1;
        iArr[i2] = i;
        this.cursor--;
    }

    private void skipLine() throws IOException {
        int i;
        do {
            i = getChar();
            if (i == -1) {
                break;
            }
        } while (i != 10);
        ungetChar(i);
        this.tokenEnd = this.cursor;
    }

    final int getOffset() {
        int i = this.sourceCursor - this.lineStart;
        if (this.lineEndChar >= 0) {
            return i - 1;
        }
        return i;
    }

    private final int charAt(int i) {
        if (i < 0) {
            return -1;
        }
        if (this.sourceString == null) {
            if (i >= this.sourceEnd) {
                int i2 = this.sourceCursor;
                try {
                    if (!fillSourceBuffer()) {
                        return -1;
                    }
                    i -= i2 - this.sourceCursor;
                } catch (IOException e) {
                    return -1;
                }
            }
            return this.sourceBuffer[i];
        } else if (i < this.sourceEnd) {
            return this.sourceString.charAt(i);
        } else {
            return -1;
        }
    }

    private final String substring(int i, int i2) {
        if (this.sourceString != null) {
            return this.sourceString.substring(i, i2);
        }
        return new String(this.sourceBuffer, i, i2 - i);
    }

    final String getLine() {
        int i = this.sourceCursor;
        if (this.lineEndChar >= 0) {
            i--;
            if (this.lineEndChar == 10 && charAt(i - 1) == 13) {
                i--;
            }
        } else {
            i -= this.lineStart;
            while (true) {
                int charAt = charAt(this.lineStart + i);
                i = (charAt == -1 || ScriptRuntime.isJSLineTerminator(charAt)) ? i + this.lineStart : i + 1;
            }
        }
        return substring(this.lineStart, i);
    }

    final String getLine(int i, int[] iArr) {
        if (!$assertionsDisabled && (i < 0 || i > this.cursor)) {
            throw new AssertionError();
        } else if ($assertionsDisabled || iArr.length == 2) {
            int i2 = (this.cursor + this.ungetCursor) - i;
            int i3 = this.sourceCursor;
            if (i2 > i3) {
                return null;
            }
            int charAt;
            int i4 = 0;
            int i5 = 0;
            while (i2 > 0) {
                if ($assertionsDisabled || i3 > 0) {
                    int i6;
                    int i7;
                    charAt = charAt(i3 - 1);
                    if (ScriptRuntime.isJSLineTerminator(charAt)) {
                        if (charAt == 10 && charAt(i3 - 2) == 13) {
                            i2--;
                            i3--;
                        }
                        i5 = i3 - 1;
                        i6 = i4 + 1;
                        i4 = i3;
                        i3 = i6;
                        i7 = i5;
                        i5 = i2;
                        i2 = i7;
                    } else {
                        i6 = i4;
                        i4 = i3;
                        i3 = i6;
                        i7 = i5;
                        i5 = i2;
                        i2 = i7;
                    }
                    i6 = i3;
                    i3 = i4 - 1;
                    i4 = i6;
                    i7 = i2;
                    i2 = i5 - 1;
                    i5 = i7;
                } else {
                    throw new AssertionError();
                }
            }
            charAt = 0;
            while (i3 > 0) {
                if (ScriptRuntime.isJSLineTerminator(charAt(i3 - 1))) {
                    break;
                }
                charAt++;
                i3--;
            }
            i3 = 0;
            int i8 = this.lineno - i4;
            if (this.lineEndChar >= 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            iArr[0] = i2 + i8;
            iArr[1] = charAt;
            if (i4 == 0) {
                return getLine();
            }
            return substring(i3, i5);
        } else {
            throw new AssertionError();
        }
    }

    private boolean fillSourceBuffer() throws IOException {
        if (this.sourceString != null) {
            Kit.codeBug();
        }
        if (this.sourceEnd == this.sourceBuffer.length) {
            if (this.lineStart == 0 || isMarkingComment()) {
                Object obj = new char[(this.sourceBuffer.length * 2)];
                System.arraycopy(this.sourceBuffer, 0, obj, 0, this.sourceEnd);
                this.sourceBuffer = obj;
            } else {
                System.arraycopy(this.sourceBuffer, this.lineStart, this.sourceBuffer, 0, this.sourceEnd - this.lineStart);
                this.sourceEnd -= this.lineStart;
                this.sourceCursor -= this.lineStart;
                this.lineStart = 0;
            }
        }
        int read = this.sourceReader.read(this.sourceBuffer, this.sourceEnd, this.sourceBuffer.length - this.sourceEnd);
        if (read < 0) {
            return false;
        }
        this.sourceEnd += read;
        return true;
    }

    public int getCursor() {
        return this.cursor;
    }

    public int getTokenBeg() {
        return this.tokenBeg;
    }

    public int getTokenEnd() {
        return this.tokenEnd;
    }

    public int getTokenLength() {
        return this.tokenEnd - this.tokenBeg;
    }

    public CommentType getCommentType() {
        return this.commentType;
    }

    private void markCommentStart() {
        markCommentStart("");
    }

    private void markCommentStart(String str) {
        if (this.parser.compilerEnv.isRecordingComments() && this.sourceReader != null) {
            this.commentPrefix = str;
            this.commentCursor = this.sourceCursor - 1;
        }
    }

    private boolean isMarkingComment() {
        return this.commentCursor != -1;
    }

    final String getAndResetCurrentComment() {
        if (this.sourceString != null) {
            if (isMarkingComment()) {
                Kit.codeBug();
            }
            return this.sourceString.substring(this.tokenBeg, this.tokenEnd);
        }
        if (!isMarkingComment()) {
            Kit.codeBug();
        }
        StringBuilder stringBuilder = new StringBuilder(this.commentPrefix);
        stringBuilder.append(this.sourceBuffer, this.commentCursor, getTokenLength() - this.commentPrefix.length());
        this.commentCursor = -1;
        return stringBuilder.toString();
    }

    private String convertLastCharToHex(String str) {
        int i = 0;
        int length = str.length() - 1;
        StringBuffer stringBuffer = new StringBuffer(str.substring(0, length));
        stringBuffer.append("\\u");
        String toHexString = Integer.toHexString(str.charAt(length));
        while (i < 4 - toHexString.length()) {
            stringBuffer.append('0');
            i++;
        }
        stringBuffer.append(toHexString);
        return stringBuffer.toString();
    }
}
