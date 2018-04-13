package org.mozilla.javascript.regexp;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.TopLevel.Builtins;
import org.mozilla.javascript.Undefined;

public class NativeRegExp extends IdScriptableObject implements Function {
    static final /* synthetic */ boolean $assertionsDisabled = (!NativeRegExp.class.desiredAssertionStatus());
    private static final int ANCHOR_BOL = -2;
    private static final int INDEX_LEN = 2;
    private static final int Id_compile = 1;
    private static final int Id_exec = 4;
    private static final int Id_global = 3;
    private static final int Id_ignoreCase = 4;
    private static final int Id_lastIndex = 1;
    private static final int Id_multiline = 5;
    private static final int Id_prefix = 6;
    private static final int Id_source = 2;
    private static final int Id_test = 5;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    public static final int JSREG_FOLD = 2;
    public static final int JSREG_GLOB = 1;
    public static final int JSREG_MULTILINE = 4;
    public static final int MATCH = 1;
    private static final int MAX_INSTANCE_ID = 5;
    private static final int MAX_PROTOTYPE_ID = 6;
    public static final int PREFIX = 2;
    private static final Object REGEXP_TAG = new Object();
    private static final byte REOP_ALNUM = (byte) 9;
    private static final byte REOP_ALT = (byte) 31;
    private static final byte REOP_ALTPREREQ = (byte) 53;
    private static final byte REOP_ALTPREREQ2 = (byte) 55;
    private static final byte REOP_ALTPREREQi = (byte) 54;
    private static final byte REOP_ASSERT = (byte) 41;
    private static final byte REOP_ASSERTNOTTEST = (byte) 44;
    private static final byte REOP_ASSERTTEST = (byte) 43;
    private static final byte REOP_ASSERT_NOT = (byte) 42;
    private static final byte REOP_BACKREF = (byte) 13;
    private static final byte REOP_BOL = (byte) 2;
    private static final byte REOP_CLASS = (byte) 22;
    private static final byte REOP_DIGIT = (byte) 7;
    private static final byte REOP_DOT = (byte) 6;
    private static final byte REOP_EMPTY = (byte) 1;
    private static final byte REOP_END = (byte) 57;
    private static final byte REOP_ENDCHILD = (byte) 49;
    private static final byte REOP_EOL = (byte) 3;
    private static final byte REOP_FLAT = (byte) 14;
    private static final byte REOP_FLAT1 = (byte) 15;
    private static final byte REOP_FLAT1i = (byte) 17;
    private static final byte REOP_FLATi = (byte) 16;
    private static final byte REOP_JUMP = (byte) 32;
    private static final byte REOP_LPAREN = (byte) 29;
    private static final byte REOP_MINIMALOPT = (byte) 47;
    private static final byte REOP_MINIMALPLUS = (byte) 46;
    private static final byte REOP_MINIMALQUANT = (byte) 48;
    private static final byte REOP_MINIMALREPEAT = (byte) 52;
    private static final byte REOP_MINIMALSTAR = (byte) 45;
    private static final byte REOP_NCLASS = (byte) 23;
    private static final byte REOP_NONALNUM = (byte) 10;
    private static final byte REOP_NONDIGIT = (byte) 8;
    private static final byte REOP_NONSPACE = (byte) 12;
    private static final byte REOP_OPT = (byte) 28;
    private static final byte REOP_PLUS = (byte) 27;
    private static final byte REOP_QUANT = (byte) 25;
    private static final byte REOP_REPEAT = (byte) 51;
    private static final byte REOP_RPAREN = (byte) 30;
    private static final byte REOP_SIMPLE_END = (byte) 23;
    private static final byte REOP_SIMPLE_START = (byte) 1;
    private static final byte REOP_SPACE = (byte) 11;
    private static final byte REOP_STAR = (byte) 26;
    private static final byte REOP_UCFLAT1 = (byte) 18;
    private static final byte REOP_UCFLAT1i = (byte) 19;
    private static final byte REOP_WBDRY = (byte) 4;
    private static final byte REOP_WNONBDRY = (byte) 5;
    public static final int TEST = 0;
    private static final boolean debug = false;
    static final long serialVersionUID = 4965263491464903264L;
    Object lastIndex = Double.valueOf(0.0d);
    private int lastIndexAttr = 6;
    private RECompiled re;

    public static void init(Context context, Scriptable scriptable, boolean z) {
        NativeRegExp nativeRegExp = new NativeRegExp();
        nativeRegExp.re = compileRE(context, "", null, false);
        nativeRegExp.activatePrototypeMap(6);
        nativeRegExp.setParentScope(scriptable);
        nativeRegExp.setPrototype(getObjectPrototype(scriptable));
        BaseFunction nativeRegExpCtor = new NativeRegExpCtor();
        nativeRegExp.defineProperty("constructor", nativeRegExpCtor, 2);
        ScriptRuntime.setFunctionProtoAndParent(nativeRegExpCtor, scriptable);
        nativeRegExpCtor.setImmunePrototypeProperty(nativeRegExp);
        if (z) {
            nativeRegExp.sealObject();
            nativeRegExpCtor.sealObject();
        }
        defineProperty(scriptable, "RegExp", nativeRegExpCtor, 2);
    }

    NativeRegExp(Scriptable scriptable, RECompiled rECompiled) {
        this.re = rECompiled;
        this.lastIndex = Double.valueOf(0.0d);
        ScriptRuntime.setBuiltinProtoAndParent(this, scriptable, Builtins.RegExp);
    }

    public String getClassName() {
        return "RegExp";
    }

    public String getTypeOf() {
        return "object";
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return execSub(context, scriptable, objArr, 1);
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        return (Scriptable) execSub(context, scriptable, objArr, 1);
    }

    Scriptable compile(Context context, Scriptable scriptable, Object[] objArr) {
        if (objArr.length <= 0 || !(objArr[0] instanceof NativeRegExp)) {
            String escapeRegExp = (objArr.length == 0 || (objArr[0] instanceof Undefined)) ? "" : escapeRegExp(objArr[0]);
            String scriptRuntime = (objArr.length <= 1 || objArr[1] == Undefined.instance) ? null : ScriptRuntime.toString(objArr[1]);
            this.re = compileRE(context, escapeRegExp, scriptRuntime, false);
            this.lastIndex = Double.valueOf(0.0d);
        } else if (objArr.length <= 1 || objArr[1] == Undefined.instance) {
            NativeRegExp nativeRegExp = (NativeRegExp) objArr[0];
            this.re = nativeRegExp.re;
            this.lastIndex = nativeRegExp.lastIndex;
        } else {
            throw ScriptRuntime.typeError0("msg.bad.regexp.compile");
        }
        return this;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('/');
        if (this.re.source.length != 0) {
            stringBuilder.append(this.re.source);
        } else {
            stringBuilder.append("(?:)");
        }
        stringBuilder.append('/');
        if ((this.re.flags & 1) != 0) {
            stringBuilder.append('g');
        }
        if ((this.re.flags & 2) != 0) {
            stringBuilder.append('i');
        }
        if ((this.re.flags & 4) != 0) {
            stringBuilder.append('m');
        }
        return stringBuilder.toString();
    }

    NativeRegExp() {
    }

    private static RegExpImpl getImpl(Context context) {
        return (RegExpImpl) ScriptRuntime.getRegExpProxy(context);
    }

    private static String escapeRegExp(Object obj) {
        CharSequence scriptRuntime = ScriptRuntime.toString(obj);
        int i = 0;
        StringBuilder stringBuilder = null;
        int indexOf = scriptRuntime.indexOf(47);
        while (indexOf > -1) {
            StringBuilder stringBuilder2;
            int i2;
            int i3;
            if (indexOf == i || scriptRuntime.charAt(indexOf - 1) != '\\') {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder();
                }
                stringBuilder.append(scriptRuntime, i, indexOf);
                stringBuilder.append("\\/");
                stringBuilder2 = stringBuilder;
                i2 = indexOf + 1;
            } else {
                i3 = i;
                stringBuilder2 = stringBuilder;
                i2 = i3;
            }
            indexOf = scriptRuntime.indexOf(47, indexOf + 1);
            i3 = i2;
            stringBuilder = stringBuilder2;
            i = i3;
        }
        if (stringBuilder == null) {
            return scriptRuntime;
        }
        stringBuilder.append(scriptRuntime, i, scriptRuntime.length());
        return stringBuilder.toString();
    }

    private Object execSub(Context context, Scriptable scriptable, Object[] objArr, int i) {
        String str;
        double toInteger;
        RegExpImpl impl = getImpl(context);
        if (objArr.length == 0) {
            str = impl.input;
            if (str == null) {
                str = ScriptRuntime.toString(Undefined.instance);
            }
        } else {
            str = ScriptRuntime.toString(objArr[0]);
        }
        if ((this.re.flags & 1) != 0) {
            toInteger = ScriptRuntime.toInteger(this.lastIndex);
        } else {
            toInteger = 0.0d;
        }
        if (toInteger < 0.0d || ((double) str.length()) < toInteger) {
            this.lastIndex = Double.valueOf(0.0d);
            return null;
        }
        int[] iArr = new int[]{(int) toInteger};
        Object executeRegExp = executeRegExp(context, scriptable, impl, str, iArr, i);
        if ((this.re.flags & 1) != 0) {
            toInteger = (executeRegExp == null || executeRegExp == Undefined.instance) ? 0.0d : (double) iArr[0];
            this.lastIndex = Double.valueOf(toInteger);
        }
        return executeRegExp;
    }

    static RECompiled compileRE(Context context, String str, String str2, boolean z) {
        int i;
        int i2;
        RECompiled rECompiled = new RECompiled(str);
        int length = str.length();
        if (str2 != null) {
            i = 0;
            for (int i3 = 0; i3 < str2.length(); i3++) {
                char charAt = str2.charAt(i3);
                if (charAt == 'g') {
                    i2 = 1;
                } else if (charAt == 'i') {
                    i2 = 2;
                } else if (charAt == 'm') {
                    i2 = 4;
                } else {
                    reportError("msg.invalid.re.flag", String.valueOf(charAt));
                    i2 = 0;
                }
                if ((i & i2) != 0) {
                    reportError("msg.invalid.re.flag", String.valueOf(charAt));
                }
                i |= i2;
            }
        } else {
            i = 0;
        }
        rECompiled.flags = i;
        CompilerState compilerState = new CompilerState(context, rECompiled.source, length, i);
        if (z && length > 0) {
            compilerState.result = new RENode(REOP_FLAT);
            compilerState.result.chr = compilerState.cpbegin[0];
            compilerState.result.length = length;
            compilerState.result.flatIndex = 0;
            compilerState.progLength += 5;
        } else if (!parseDisjunction(compilerState)) {
            return null;
        } else {
            if (compilerState.maxBackReference > compilerState.parenCount) {
                compilerState = new CompilerState(context, rECompiled.source, length, i);
                compilerState.backReferenceLimit = compilerState.parenCount;
                if (!parseDisjunction(compilerState)) {
                    return null;
                }
            }
        }
        rECompiled.program = new byte[(compilerState.progLength + 1)];
        if (compilerState.classCount != 0) {
            rECompiled.classList = new RECharSet[compilerState.classCount];
            rECompiled.classCount = compilerState.classCount;
        }
        i = emitREBytecode(compilerState, rECompiled, 0, compilerState.result);
        i2 = i + 1;
        rECompiled.program[i] = REOP_END;
        rECompiled.parenCount = compilerState.parenCount;
        switch (rECompiled.program[0]) {
            case (byte) 2:
                rECompiled.anchorCh = -2;
                break;
            case (byte) 14:
            case (byte) 16:
                rECompiled.anchorCh = rECompiled.source[getIndex(rECompiled.program, 1)];
                break;
            case (byte) 15:
            case (byte) 17:
                rECompiled.anchorCh = (char) (rECompiled.program[1] & 255);
                break;
            case (byte) 18:
            case (byte) 19:
                rECompiled.anchorCh = (char) getIndex(rECompiled.program, 1);
                break;
            case (byte) 31:
                RENode rENode = compilerState.result;
                if (rENode.kid.op == (byte) 2 && rENode.kid2.op == (byte) 2) {
                    rECompiled.anchorCh = -2;
                    break;
                }
        }
        return rECompiled;
    }

    static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private static boolean isWord(char c) {
        return ('a' <= c && c <= 'z') || (('A' <= c && c <= 'Z') || isDigit(c) || c == '_');
    }

    private static boolean isControlLetter(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }

    private static boolean isLineTerm(char c) {
        return ScriptRuntime.isJSLineTerminator(c);
    }

    private static boolean isREWhiteSpace(int i) {
        return ScriptRuntime.isJSWhitespaceOrLineTerminator(i);
    }

    private static char upcase(char c) {
        if (c >= '') {
            char toUpperCase = Character.toUpperCase(c);
            return toUpperCase >= '' ? toUpperCase : c;
        } else if ('a' > c || c > 'z') {
            return c;
        } else {
            return (char) (c - 32);
        }
    }

    private static char downcase(char c) {
        if (c >= '') {
            char toLowerCase = Character.toLowerCase(c);
            return toLowerCase >= '' ? toLowerCase : c;
        } else if ('A' > c || c > 'Z') {
            return c;
        } else {
            return (char) (c + 32);
        }
    }

    private static int toASCIIHexDigit(int i) {
        if (i < 48) {
            return -1;
        }
        if (i <= 57) {
            return i - 48;
        }
        int i2 = i | 32;
        if (97 > i2 || i2 > 102) {
            return -1;
        }
        return (i2 - 97) + 10;
    }

    private static boolean parseDisjunction(CompilerState compilerState) {
        if (!parseAlternative(compilerState)) {
            return false;
        }
        char[] cArr = compilerState.cpbegin;
        int i = compilerState.cp;
        if (i != cArr.length && cArr[i] == '|') {
            compilerState.cp++;
            RENode rENode = new RENode(REOP_ALT);
            rENode.kid = compilerState.result;
            if (!parseDisjunction(compilerState)) {
                return false;
            }
            rENode.kid2 = compilerState.result;
            compilerState.result = rENode;
            if (rENode.kid.op == REOP_FLAT && rENode.kid2.op == REOP_FLAT) {
                rENode.op = (compilerState.flags & 2) == 0 ? REOP_ALTPREREQ : REOP_ALTPREREQi;
                rENode.chr = rENode.kid.chr;
                rENode.index = rENode.kid2.chr;
                compilerState.progLength += 13;
            } else if (rENode.kid.op == REOP_CLASS && rENode.kid.index < 256 && rENode.kid2.op == REOP_FLAT && (compilerState.flags & 2) == 0) {
                rENode.op = REOP_ALTPREREQ2;
                rENode.chr = rENode.kid2.chr;
                rENode.index = rENode.kid.index;
                compilerState.progLength += 13;
            } else if (rENode.kid.op == REOP_FLAT && rENode.kid2.op == REOP_CLASS && rENode.kid2.index < 256 && (compilerState.flags & 2) == 0) {
                rENode.op = REOP_ALTPREREQ2;
                rENode.chr = rENode.kid.chr;
                rENode.index = rENode.kid2.index;
                compilerState.progLength += 13;
            } else {
                compilerState.progLength += 9;
            }
        }
        return true;
    }

    private static boolean parseAlternative(CompilerState compilerState) {
        RENode rENode = null;
        char[] cArr = compilerState.cpbegin;
        RENode rENode2 = null;
        while (compilerState.cp != compilerState.cpend && cArr[compilerState.cp] != '|' && (compilerState.parenNesting == 0 || cArr[compilerState.cp] != ')')) {
            if (!parseTerm(compilerState)) {
                return false;
            }
            if (rENode2 == null) {
                rENode = compilerState.result;
                rENode2 = rENode;
            } else {
                rENode.next = compilerState.result;
            }
            while (rENode.next != null) {
                rENode = rENode.next;
            }
        }
        if (rENode2 == null) {
            compilerState.result = new RENode((byte) 1);
        } else {
            compilerState.result = rENode2;
        }
        return true;
    }

    private static boolean calculateBitmapSize(CompilerState compilerState, RENode rENode, char[] cArr, int i, int i2) {
        int i3 = 0;
        Object obj = null;
        rENode.bmsize = 0;
        rENode.sense = true;
        if (i == i2) {
            return true;
        }
        int i4;
        int i5;
        if (cArr[i] == '^') {
            i4 = i + 1;
            rENode.sense = false;
            i5 = 0;
        } else {
            i5 = 0;
            i4 = i;
        }
        while (i4 != i2) {
            char c;
            char c2;
            Object obj2;
            int i6 = 2;
            int i7;
            switch (cArr[i4]) {
                case '\\':
                    char c3;
                    int i8 = i4 + 1;
                    i7 = i8 + 1;
                    c = cArr[i8];
                    switch (c) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                            c3 = c - 48;
                            char c4 = cArr[i7];
                            if ('0' <= c4 && c4 <= '7') {
                                i8 = i7 + 1;
                                c2 = (c4 - 48) + (c3 * 8);
                                c3 = cArr[i8];
                                if ('0' > c3 || c3 > '7') {
                                    c3 = c2;
                                    i7 = i8;
                                } else {
                                    i8++;
                                    c3 = (c3 - 48) + (c2 * 8);
                                    if (c3 <= 'ÿ') {
                                        i7 = i8;
                                    } else {
                                        char c5 = c2;
                                        i7 = i8 - 1;
                                        c3 = c5;
                                    }
                                }
                            }
                            c = c3;
                            i4 = i7;
                            break;
                        case 'D':
                        case 'S':
                        case 'W':
                        case 's':
                        case 'w':
                            if (obj != null) {
                                reportError("msg.bad.range", "");
                                return false;
                            }
                            rENode.bmsize = 65536;
                            return true;
                        case 'b':
                            c = '\b';
                            i4 = i7;
                            break;
                        case 'c':
                            if (i7 >= i2 || !isControlLetter(cArr[i7])) {
                                i6 = i7 - 1;
                            } else {
                                i6 = i7 + 1;
                                c2 = (char) (cArr[i7] & 31);
                            }
                            c = '\\';
                            i4 = i6;
                            break;
                        case 'd':
                            if (obj == null) {
                                c = '9';
                                i4 = i7;
                                break;
                            }
                            reportError("msg.bad.range", "");
                            return false;
                        case 'f':
                            c = '\f';
                            i4 = i7;
                            break;
                        case 'n':
                            c = '\n';
                            i4 = i7;
                            break;
                        case 'r':
                            c = '\r';
                            i4 = i7;
                            break;
                        case 't':
                            c = '\t';
                            i4 = i7;
                            break;
                        case 'u':
                            i6 = 4;
                            break;
                        case 'v':
                            c = '\u000b';
                            i4 = i7;
                            break;
                        case 'x':
                            break;
                        default:
                            i4 = i7;
                            break;
                    }
                    i8 = i7;
                    c2 = '\u0000';
                    i4 = 0;
                    while (i4 < i6 && i8 < i2) {
                        int i9 = i8 + 1;
                        c = Kit.xDigitToInt(cArr[i8], c2);
                        if (c < '\u0000') {
                            i7 = i9 - (i4 + 1);
                            c3 = '\\';
                            c = c3;
                            i4 = i7;
                            break;
                        }
                        i4++;
                        c2 = c;
                        i8 = i9;
                    }
                    c3 = c2;
                    i7 = i8;
                    c = c3;
                    i4 = i7;
                    break;
                default:
                    i7 = i4 + 1;
                    c = cArr[i4];
                    i4 = i7;
                    break;
            }
            if (obj != null) {
                if (i5 > c) {
                    reportError("msg.bad.range", "");
                    return false;
                }
                obj2 = null;
            } else if (i4 >= i2 - 1 || cArr[i4] != '-') {
                obj2 = obj;
            } else {
                i4++;
                obj = 1;
                i5 = (char) c;
            }
            if ((compilerState.flags & 2) != 0) {
                c2 = upcase((char) c);
                c = downcase((char) c);
                if (c2 < c) {
                    c2 = c;
                }
            } else {
                c2 = c;
            }
            if (c2 <= i3) {
                c2 = i3;
            }
            obj = obj2;
            i3 = c2;
        }
        rENode.bmsize = i3 + 1;
        return true;
    }

    private static void doFlat(CompilerState compilerState, char c) {
        compilerState.result = new RENode(REOP_FLAT);
        compilerState.result.chr = c;
        compilerState.result.length = 1;
        compilerState.result.flatIndex = -1;
        compilerState.progLength += 3;
    }

    private static int getDecimalValue(char c, CompilerState compilerState, int i, String str) {
        Object obj = null;
        int i2 = compilerState.cp;
        char[] cArr = compilerState.cpbegin;
        int i3 = c - 48;
        while (compilerState.cp != compilerState.cpend) {
            char c2 = cArr[compilerState.cp];
            if (!isDigit(c2)) {
                break;
            }
            if (obj == null) {
                i3 = (i3 * 10) + (c2 - 48);
                if (i3 >= i) {
                    obj = 1;
                    i3 = i;
                }
            }
            compilerState.cp++;
        }
        if (obj != null) {
            reportError(str, String.valueOf(cArr, i2, compilerState.cp - i2));
        }
        return i3;
    }

    private static boolean parseTerm(CompilerState compilerState) {
        int i;
        int decimalValue;
        char c = '\\';
        char[] cArr = compilerState.cpbegin;
        int i2 = compilerState.cp;
        compilerState.cp = i2 + 1;
        char c2 = cArr[i2];
        int i3 = compilerState.parenCount;
        RENode rENode;
        char c3;
        int i4;
        switch (c2) {
            case '$':
                compilerState.result = new RENode((byte) 3);
                compilerState.progLength++;
                return true;
            case '(':
                rENode = null;
                i2 = compilerState.cp;
                if (compilerState.cp + 1 < compilerState.cpend && cArr[compilerState.cp] == '?') {
                    c3 = cArr[compilerState.cp + 1];
                    if (c3 == '=' || c3 == '!' || c3 == ':') {
                        compilerState.cp += 2;
                        if (c3 == '=') {
                            rENode = new RENode(REOP_ASSERT);
                            compilerState.progLength += 4;
                        } else if (c3 == '!') {
                            rENode = new RENode(REOP_ASSERT_NOT);
                            compilerState.progLength += 4;
                        }
                        compilerState.parenNesting++;
                        if (parseDisjunction(compilerState)) {
                            if (compilerState.cp == compilerState.cpend && cArr[compilerState.cp] == ')') {
                                compilerState.cp++;
                                compilerState.parenNesting--;
                                if (rENode != null) {
                                    rENode.kid = compilerState.result;
                                    compilerState.result = rENode;
                                    break;
                                }
                            }
                            reportError("msg.unterm.paren", "");
                            return false;
                        }
                        return false;
                    }
                }
                rENode = new RENode(REOP_LPAREN);
                compilerState.progLength += 6;
                i2 = compilerState.parenCount;
                compilerState.parenCount = i2 + 1;
                rENode.parenIndex = i2;
                compilerState.parenNesting++;
                if (parseDisjunction(compilerState)) {
                    return false;
                }
                if (compilerState.cp == compilerState.cpend) {
                    break;
                }
                reportError("msg.unterm.paren", "");
                return false;
            case ')':
                reportError("msg.re.unmatched.right.paren", "");
                return false;
            case '*':
            case '+':
            case '?':
                reportError("msg.bad.quant", String.valueOf(cArr[compilerState.cp - 1]));
                return false;
            case '.':
                compilerState.result = new RENode((byte) 6);
                compilerState.progLength++;
                break;
            case '[':
                compilerState.result = new RENode(REOP_CLASS);
                i2 = compilerState.cp;
                compilerState.result.startIndex = i2;
                while (compilerState.cp != compilerState.cpend) {
                    if (cArr[compilerState.cp] == '\\') {
                        compilerState.cp++;
                    } else if (cArr[compilerState.cp] == ']') {
                        compilerState.result.kidlen = compilerState.cp - i2;
                        rENode = compilerState.result;
                        i4 = compilerState.classCount;
                        compilerState.classCount = i4 + 1;
                        rENode.index = i4;
                        rENode = compilerState.result;
                        i4 = compilerState.cp;
                        compilerState.cp = i4 + 1;
                        if (calculateBitmapSize(compilerState, rENode, cArr, i2, i4)) {
                            compilerState.progLength += 3;
                            break;
                        }
                        return false;
                    }
                    compilerState.cp++;
                }
                reportError("msg.unterm.class", "");
                return false;
            case '\\':
                if (compilerState.cp < compilerState.cpend) {
                    i4 = compilerState.cp;
                    compilerState.cp = i4 + 1;
                    c2 = cArr[i4];
                    switch (c2) {
                        case '0':
                            reportWarning(compilerState.cx, "msg.bad.backref", "");
                            i = 0;
                            while (i < 32 && compilerState.cp < compilerState.cpend) {
                                c3 = cArr[compilerState.cp];
                                if (c3 >= '0' && c3 <= '7') {
                                    compilerState.cp++;
                                    i = (i * 8) + (c3 - 48);
                                }
                            }
                            doFlat(compilerState, (char) i);
                            break;
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            i2 = compilerState.cp - 1;
                            decimalValue = getDecimalValue(c2, compilerState, 65535, "msg.overlarge.backref");
                            if (decimalValue > compilerState.backReferenceLimit) {
                                reportWarning(compilerState.cx, "msg.bad.backref", "");
                            }
                            if (decimalValue <= compilerState.backReferenceLimit) {
                                compilerState.result = new RENode(REOP_BACKREF);
                                compilerState.result.parenIndex = decimalValue - 1;
                                compilerState.progLength += 3;
                                if (compilerState.maxBackReference < decimalValue) {
                                    compilerState.maxBackReference = decimalValue;
                                    break;
                                }
                            }
                            compilerState.cp = i2;
                            if (c2 < '8') {
                                compilerState.cp++;
                                i = c2 - 48;
                                while (i < 32 && compilerState.cp < compilerState.cpend) {
                                    c3 = cArr[compilerState.cp];
                                    if (c3 >= '0' && c3 <= '7') {
                                        compilerState.cp++;
                                        i = (i * 8) + (c3 - 48);
                                    }
                                }
                                doFlat(compilerState, (char) i);
                                break;
                            }
                            doFlat(compilerState, '\\');
                            break;
                            break;
                        case 'B':
                            compilerState.result = new RENode((byte) 5);
                            compilerState.progLength++;
                            return true;
                        case 'D':
                            compilerState.result = new RENode((byte) 8);
                            compilerState.progLength++;
                            break;
                        case 'S':
                            compilerState.result = new RENode(REOP_NONSPACE);
                            compilerState.progLength++;
                            break;
                        case 'W':
                            compilerState.result = new RENode((byte) 10);
                            compilerState.progLength++;
                            break;
                        case 'b':
                            compilerState.result = new RENode((byte) 4);
                            compilerState.progLength++;
                            return true;
                        case 'c':
                            if (compilerState.cp >= compilerState.cpend || !isControlLetter(cArr[compilerState.cp])) {
                                compilerState.cp--;
                            } else {
                                i = compilerState.cp;
                                compilerState.cp = i + 1;
                                c = (char) (cArr[i] & 31);
                            }
                            doFlat(compilerState, c);
                            break;
                        case 'd':
                            compilerState.result = new RENode((byte) 7);
                            compilerState.progLength++;
                            break;
                        case 'f':
                            doFlat(compilerState, '\f');
                            break;
                        case 'n':
                            doFlat(compilerState, '\n');
                            break;
                        case 'r':
                            doFlat(compilerState, '\r');
                            break;
                        case 's':
                            compilerState.result = new RENode((byte) 11);
                            compilerState.progLength++;
                            break;
                        case 't':
                            doFlat(compilerState, '\t');
                            break;
                        case 'u':
                            i = 4;
                            break;
                        case 'v':
                            doFlat(compilerState, '\u000b');
                            break;
                        case 'w':
                            compilerState.result = new RENode((byte) 9);
                            compilerState.progLength++;
                            break;
                        case 'x':
                            i = 2;
                            break;
                        default:
                            compilerState.result = new RENode(REOP_FLAT);
                            compilerState.result.chr = c2;
                            compilerState.result.length = 1;
                            compilerState.result.flatIndex = compilerState.cp - 1;
                            compilerState.progLength += 3;
                            break;
                    }
                    i4 = 0;
                    i2 = 0;
                    while (i4 < i && compilerState.cp < compilerState.cpend) {
                        decimalValue = compilerState.cp;
                        compilerState.cp = decimalValue + 1;
                        decimalValue = Kit.xDigitToInt(cArr[decimalValue], i2);
                        if (decimalValue < 0) {
                            compilerState.cp -= i4 + 2;
                            i = compilerState.cp;
                            compilerState.cp = i + 1;
                            i = cArr[i];
                            doFlat(compilerState, (char) i);
                            break;
                        }
                        i4++;
                        i2 = decimalValue;
                    }
                    i = i2;
                    doFlat(compilerState, (char) i);
                } else {
                    reportError("msg.trail.backslash", "");
                    return false;
                }
                break;
            case '^':
                compilerState.result = new RENode((byte) 2);
                compilerState.progLength++;
                return true;
            default:
                compilerState.result = new RENode(REOP_FLAT);
                compilerState.result.chr = c2;
                compilerState.result.length = 1;
                compilerState.result.flatIndex = compilerState.cp - 1;
                compilerState.progLength += 3;
                break;
        }
        RENode rENode2 = compilerState.result;
        if (compilerState.cp == compilerState.cpend) {
            return true;
        }
        boolean z;
        switch (cArr[compilerState.cp]) {
            case '*':
                compilerState.result = new RENode(REOP_QUANT);
                compilerState.result.min = 0;
                compilerState.result.max = -1;
                compilerState.progLength += 8;
                z = true;
                break;
            case '+':
                compilerState.result = new RENode(REOP_QUANT);
                compilerState.result.min = 1;
                compilerState.result.max = -1;
                compilerState.progLength += 8;
                z = true;
                break;
            case '?':
                compilerState.result = new RENode(REOP_QUANT);
                compilerState.result.min = 0;
                compilerState.result.max = 1;
                compilerState.progLength += 8;
                z = true;
                break;
            case '{':
                decimalValue = compilerState.cp;
                i = compilerState.cp + 1;
                compilerState.cp = i;
                if (i < cArr.length) {
                    c = cArr[compilerState.cp];
                    if (isDigit(c)) {
                        char c4;
                        compilerState.cp++;
                        i2 = getDecimalValue(c, compilerState, 65535, "msg.overlarge.min");
                        c = cArr[compilerState.cp];
                        if (c == ',') {
                            i = compilerState.cp + 1;
                            compilerState.cp = i;
                            c = cArr[i];
                            if (isDigit(c)) {
                                compilerState.cp++;
                                i = getDecimalValue(c, compilerState, 65535, "msg.overlarge.max");
                                c4 = cArr[compilerState.cp];
                                if (i2 > i) {
                                    reportError("msg.max.lt.min", String.valueOf(cArr[compilerState.cp]));
                                    return false;
                                }
                            }
                            c4 = c;
                            i = -1;
                        } else {
                            c4 = c;
                            i = i2;
                        }
                        if (c4 == '}') {
                            compilerState.result = new RENode(REOP_QUANT);
                            compilerState.result.min = i2;
                            compilerState.result.max = i;
                            compilerState.progLength += 12;
                            z = true;
                            if (!z) {
                                compilerState.cp = decimalValue;
                                break;
                            }
                        }
                    }
                }
                z = false;
                if (z) {
                    compilerState.cp = decimalValue;
                }
                break;
            default:
                z = false;
                break;
        }
        if (!z) {
            return true;
        }
        compilerState.cp++;
        compilerState.result.kid = rENode2;
        compilerState.result.parenIndex = i3;
        compilerState.result.parenCount = compilerState.parenCount - i3;
        if (compilerState.cp >= compilerState.cpend || cArr[compilerState.cp] != '?') {
            compilerState.result.greedy = true;
        } else {
            compilerState.cp++;
            compilerState.result.greedy = false;
        }
        return true;
    }

    private static void resolveForwardJump(byte[] bArr, int i, int i2) {
        if (i > i2) {
            throw Kit.codeBug();
        }
        addIndex(bArr, i, i2 - i);
    }

    private static int getOffset(byte[] bArr, int i) {
        return getIndex(bArr, i);
    }

    private static int addIndex(byte[] bArr, int i, int i2) {
        if (i2 < 0) {
            throw Kit.codeBug();
        } else if (i2 > 65535) {
            throw Context.reportRuntimeError("Too complex regexp");
        } else {
            bArr[i] = (byte) (i2 >> 8);
            bArr[i + 1] = (byte) i2;
            return i + 2;
        }
    }

    private static int getIndex(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 8) | (bArr[i + 1] & 255);
    }

    private static int emitREBytecode(CompilerState compilerState, RECompiled rECompiled, int i, RENode rENode) {
        byte[] bArr = rECompiled.program;
        while (rENode != null) {
            int i2;
            int i3;
            int i4 = i + 1;
            bArr[i] = rENode.op;
            switch (rENode.op) {
                case (byte) 1:
                    i4--;
                    continue;
                case (byte) 13:
                    i4 = addIndex(bArr, i4, rENode.parenIndex);
                    continue;
                case (byte) 14:
                    if (rENode.flatIndex != -1) {
                        while (rENode.next != null && rENode.next.op == REOP_FLAT && rENode.flatIndex + rENode.length == rENode.next.flatIndex) {
                            rENode.length += rENode.next.length;
                            rENode.next = rENode.next.next;
                        }
                    }
                    if (rENode.flatIndex == -1 || rENode.length <= 1) {
                        if (rENode.chr >= 'Ā') {
                            if ((compilerState.flags & 2) != 0) {
                                bArr[i4 - 1] = REOP_UCFLAT1i;
                            } else {
                                bArr[i4 - 1] = REOP_UCFLAT1;
                            }
                            i4 = addIndex(bArr, i4, rENode.chr);
                            break;
                        }
                        if ((compilerState.flags & 2) != 0) {
                            bArr[i4 - 1] = REOP_FLAT1i;
                        } else {
                            bArr[i4 - 1] = REOP_FLAT1;
                        }
                        i2 = i4 + 1;
                        bArr[i4] = (byte) rENode.chr;
                        i4 = i2;
                        break;
                    }
                    if ((compilerState.flags & 2) != 0) {
                        bArr[i4 - 1] = REOP_FLATi;
                    } else {
                        bArr[i4 - 1] = REOP_FLAT;
                    }
                    i4 = addIndex(bArr, addIndex(bArr, i4, rENode.flatIndex), rENode.length);
                    continue;
                case (byte) 22:
                    if (!rENode.sense) {
                        bArr[i4 - 1] = (byte) 23;
                    }
                    i4 = addIndex(bArr, i4, rENode.index);
                    rECompiled.classList[rENode.index] = new RECharSet(rENode.bmsize, rENode.startIndex, rENode.kidlen, rENode.sense);
                    continue;
                case (byte) 25:
                    if (rENode.min == 0 && rENode.max == -1) {
                        byte b;
                        i3 = i4 - 1;
                        if (rENode.greedy) {
                            b = REOP_STAR;
                        } else {
                            b = (byte) 45;
                        }
                        bArr[i3] = b;
                    } else if (rENode.min == 0 && rENode.max == 1) {
                        bArr[i4 - 1] = rENode.greedy ? REOP_OPT : REOP_MINIMALOPT;
                    } else if (rENode.min == 1 && rENode.max == -1) {
                        bArr[i4 - 1] = rENode.greedy ? REOP_PLUS : REOP_MINIMALPLUS;
                    } else {
                        if (!rENode.greedy) {
                            bArr[i4 - 1] = REOP_MINIMALQUANT;
                        }
                        i4 = addIndex(bArr, addIndex(bArr, i4, rENode.min), rENode.max + 1);
                    }
                    i2 = addIndex(bArr, addIndex(bArr, i4, rENode.parenCount), rENode.parenIndex);
                    i3 = emitREBytecode(compilerState, rECompiled, i2 + 2, rENode.kid);
                    i4 = i3 + 1;
                    bArr[i3] = (byte) 49;
                    resolveForwardJump(bArr, i2, i4);
                    continue;
                case (byte) 29:
                    i2 = emitREBytecode(compilerState, rECompiled, addIndex(bArr, i4, rENode.parenIndex), rENode.kid);
                    i4 = i2 + 1;
                    bArr[i2] = REOP_RPAREN;
                    i4 = addIndex(bArr, i4, rENode.parenIndex);
                    continue;
                case (byte) 31:
                    i2 = i4;
                    break;
                case (byte) 41:
                    i3 = emitREBytecode(compilerState, rECompiled, i4 + 2, rENode.kid);
                    i2 = i3 + 1;
                    bArr[i3] = REOP_ASSERTTEST;
                    resolveForwardJump(bArr, i4, i2);
                    i4 = i2;
                    continue;
                case (byte) 42:
                    i3 = emitREBytecode(compilerState, rECompiled, i4 + 2, rENode.kid);
                    i2 = i3 + 1;
                    bArr[i3] = REOP_ASSERTNOTTEST;
                    resolveForwardJump(bArr, i4, i2);
                    i4 = i2;
                    continue;
                case (byte) 53:
                case (byte) 54:
                case (byte) 55:
                    Object obj = rENode.op == REOP_ALTPREREQi ? 1 : null;
                    addIndex(bArr, i4, obj != null ? upcase(rENode.chr) : rENode.chr);
                    i4 += 2;
                    addIndex(bArr, i4, obj != null ? upcase((char) rENode.index) : rENode.index);
                    i2 = i4 + 2;
                    break;
                default:
                    break;
            }
            RENode rENode2 = rENode.kid2;
            i3 = emitREBytecode(compilerState, rECompiled, i2 + 2, rENode.kid);
            int i5 = i3 + 1;
            bArr[i3] = REOP_JUMP;
            i3 = i5 + 2;
            resolveForwardJump(bArr, i2, i3);
            i2 = emitREBytecode(compilerState, rECompiled, i3, rENode2);
            i3 = i2 + 1;
            bArr[i2] = REOP_JUMP;
            i4 = i3 + 2;
            resolveForwardJump(bArr, i5, i4);
            resolveForwardJump(bArr, i3, i4);
            rENode = rENode.next;
            i = i4;
        }
        return i;
    }

    private static void pushProgState(REGlobalData rEGlobalData, int i, int i2, int i3, REBackTrackData rEBackTrackData, int i4, int i5) {
        rEGlobalData.stateStackTop = new REProgState(rEGlobalData.stateStackTop, i, i2, i3, rEBackTrackData, i4, i5);
    }

    private static REProgState popProgState(REGlobalData rEGlobalData) {
        REProgState rEProgState = rEGlobalData.stateStackTop;
        rEGlobalData.stateStackTop = rEProgState.previous;
        return rEProgState;
    }

    private static void pushBackTrackState(REGlobalData rEGlobalData, byte b, int i) {
        REProgState rEProgState = rEGlobalData.stateStackTop;
        rEGlobalData.backTrackStackTop = new REBackTrackData(rEGlobalData, b, i, rEGlobalData.cp, rEProgState.continuationOp, rEProgState.continuationPc);
    }

    private static void pushBackTrackState(REGlobalData rEGlobalData, byte b, int i, int i2, int i3, int i4) {
        rEGlobalData.backTrackStackTop = new REBackTrackData(rEGlobalData, b, i, i2, i3, i4);
    }

    private static boolean flatNMatcher(REGlobalData rEGlobalData, int i, int i2, String str, int i3) {
        if (rEGlobalData.cp + i2 > i3) {
            return false;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            if (rEGlobalData.regexp.source[i + i4] != str.charAt(rEGlobalData.cp + i4)) {
                return false;
            }
        }
        rEGlobalData.cp += i2;
        return true;
    }

    private static boolean flatNIMatcher(REGlobalData rEGlobalData, int i, int i2, String str, int i3) {
        if (rEGlobalData.cp + i2 > i3) {
            return false;
        }
        char[] cArr = rEGlobalData.regexp.source;
        for (int i4 = 0; i4 < i2; i4++) {
            char c = cArr[i + i4];
            char charAt = str.charAt(rEGlobalData.cp + i4);
            if (c != charAt && upcase(c) != upcase(charAt)) {
                return false;
            }
        }
        rEGlobalData.cp += i2;
        return true;
    }

    private static boolean backrefMatcher(REGlobalData rEGlobalData, int i, String str, int i2) {
        if (rEGlobalData.parens == null || i >= rEGlobalData.parens.length) {
            return false;
        }
        int parensIndex = rEGlobalData.parensIndex(i);
        if (parensIndex == -1) {
            return true;
        }
        int parensLength = rEGlobalData.parensLength(i);
        if (rEGlobalData.cp + parensLength > i2) {
            return false;
        }
        if ((rEGlobalData.regexp.flags & 2) != 0) {
            for (int i3 = 0; i3 < parensLength; i3++) {
                char charAt = str.charAt(parensIndex + i3);
                char charAt2 = str.charAt(rEGlobalData.cp + i3);
                if (charAt != charAt2 && upcase(charAt) != upcase(charAt2)) {
                    return false;
                }
            }
        } else if (!str.regionMatches(parensIndex, str, rEGlobalData.cp, parensLength)) {
            return false;
        }
        rEGlobalData.cp += parensLength;
        return true;
    }

    private static void addCharacterToCharSet(RECharSet rECharSet, char c) {
        int i = c / 8;
        if (c >= rECharSet.length) {
            throw ScriptRuntime.constructError("SyntaxError", "invalid range in character class");
        }
        byte[] bArr = rECharSet.bits;
        bArr[i] = (byte) (bArr[i] | (1 << (c & 7)));
    }

    private static void addCharacterRangeToCharSet(RECharSet rECharSet, char c, char c2) {
        int i = c / 8;
        int i2 = c2 / 8;
        if (c2 >= rECharSet.length || c > c2) {
            throw ScriptRuntime.constructError("SyntaxError", "invalid range in character class");
        }
        char c3 = (char) (c & 7);
        char c4 = (char) (c2 & 7);
        if (i == i2) {
            byte[] bArr = rECharSet.bits;
            bArr[i] = (byte) (((255 >> (7 - (c4 - c3))) << c3) | bArr[i]);
            return;
        }
        byte[] bArr2 = rECharSet.bits;
        bArr2[i] = (byte) ((255 << c3) | bArr2[i]);
        for (i++; i < i2; i++) {
            rECharSet.bits[i] = (byte) -1;
        }
        byte[] bArr3 = rECharSet.bits;
        bArr3[i2] = (byte) (bArr3[i2] | (255 >> (7 - c4)));
    }

    private static void processCharSet(REGlobalData rEGlobalData, RECharSet rECharSet) {
        synchronized (rECharSet) {
            if (!rECharSet.converted) {
                processCharSetImpl(rEGlobalData, rECharSet);
                rECharSet.converted = true;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void processCharSetImpl(org.mozilla.javascript.regexp.REGlobalData r14, org.mozilla.javascript.regexp.RECharSet r15) {
        /*
        r1 = 92;
        r12 = 55;
        r11 = 48;
        r6 = 0;
        r2 = r15.startIndex;
        r0 = r15.strlength;
        r9 = r2 + r0;
        r0 = r15.length;
        r0 = r0 + 7;
        r0 = r0 / 8;
        r0 = new byte[r0];
        r15.bits = r0;
        if (r2 != r9) goto L_0x001a;
    L_0x0019:
        return;
    L_0x001a:
        r0 = r14.regexp;
        r0 = r0.source;
        r0 = r0[r2];
        r3 = 94;
        if (r0 != r3) goto L_0x0063;
    L_0x0024:
        r0 = $assertionsDisabled;
        if (r0 != 0) goto L_0x0032;
    L_0x0028:
        r0 = r15.sense;
        if (r0 == 0) goto L_0x0032;
    L_0x002c:
        r0 = new java.lang.AssertionError;
        r0.<init>();
        throw r0;
    L_0x0032:
        r2 = r2 + 1;
        r8 = r6;
        r4 = r6;
        r3 = r2;
    L_0x0037:
        if (r3 == r9) goto L_0x0019;
    L_0x0039:
        r0 = 2;
        r2 = r14.regexp;
        r2 = r2.source;
        r2 = r2[r3];
        switch(r2) {
            case 92: goto L_0x0071;
            default: goto L_0x0043;
        };
    L_0x0043:
        r0 = r14.regexp;
        r0 = r0.source;
        r2 = r3 + 1;
        r0 = r0[r3];
        r3 = r2;
        r2 = r0;
    L_0x004d:
        if (r8 == 0) goto L_0x01aa;
    L_0x004f:
        r0 = r14.regexp;
        r0 = r0.flags;
        r0 = r0 & 2;
        if (r0 == 0) goto L_0x01a6;
    L_0x0057:
        r0 = $assertionsDisabled;
        if (r0 != 0) goto L_0x0186;
    L_0x005b:
        if (r4 <= r2) goto L_0x0186;
    L_0x005d:
        r0 = new java.lang.AssertionError;
        r0.<init>();
        throw r0;
    L_0x0063:
        r0 = $assertionsDisabled;
        if (r0 != 0) goto L_0x01e4;
    L_0x0067:
        r0 = r15.sense;
        if (r0 != 0) goto L_0x01e4;
    L_0x006b:
        r0 = new java.lang.AssertionError;
        r0.<init>();
        throw r0;
    L_0x0071:
        r3 = r3 + 1;
        r2 = r14.regexp;
        r5 = r2.source;
        r2 = r3 + 1;
        r3 = r5[r3];
        switch(r3) {
            case 48: goto L_0x00ea;
            case 49: goto L_0x00ea;
            case 50: goto L_0x00ea;
            case 51: goto L_0x00ea;
            case 52: goto L_0x00ea;
            case 53: goto L_0x00ea;
            case 54: goto L_0x00ea;
            case 55: goto L_0x00ea;
            case 68: goto L_0x0126;
            case 83: goto L_0x014b;
            case 87: goto L_0x0172;
            case 98: goto L_0x0082;
            case 99: goto L_0x00a0;
            case 100: goto L_0x011e;
            case 102: goto L_0x0087;
            case 110: goto L_0x008c;
            case 114: goto L_0x0091;
            case 115: goto L_0x0138;
            case 116: goto L_0x0096;
            case 117: goto L_0x00c0;
            case 118: goto L_0x009b;
            case 119: goto L_0x015e;
            case 120: goto L_0x00c1;
            default: goto L_0x007e;
        };
    L_0x007e:
        r13 = r3;
        r3 = r2;
        r2 = r13;
        goto L_0x004d;
    L_0x0082:
        r0 = 8;
        r3 = r2;
        r2 = r0;
        goto L_0x004d;
    L_0x0087:
        r0 = 12;
        r3 = r2;
        r2 = r0;
        goto L_0x004d;
    L_0x008c:
        r0 = 10;
        r3 = r2;
        r2 = r0;
        goto L_0x004d;
    L_0x0091:
        r0 = 13;
        r3 = r2;
        r2 = r0;
        goto L_0x004d;
    L_0x0096:
        r0 = 9;
        r3 = r2;
        r2 = r0;
        goto L_0x004d;
    L_0x009b:
        r0 = 11;
        r3 = r2;
        r2 = r0;
        goto L_0x004d;
    L_0x00a0:
        if (r2 >= r9) goto L_0x00bb;
    L_0x00a2:
        r0 = r14.regexp;
        r0 = r0.source;
        r0 = r0[r2];
        r0 = isControlLetter(r0);
        if (r0 == 0) goto L_0x00bb;
    L_0x00ae:
        r0 = r14.regexp;
        r0 = r0.source;
        r3 = r2 + 1;
        r0 = r0[r2];
        r0 = r0 & 31;
        r0 = (char) r0;
        r2 = r0;
        goto L_0x004d;
    L_0x00bb:
        r2 = r2 + -1;
        r3 = r2;
        r2 = r1;
        goto L_0x004d;
    L_0x00c0:
        r0 = 4;
    L_0x00c1:
        r5 = r6;
        r3 = r2;
        r2 = r6;
    L_0x00c4:
        if (r5 >= r0) goto L_0x01e0;
    L_0x00c6:
        if (r3 >= r9) goto L_0x01e0;
    L_0x00c8:
        r7 = r14.regexp;
        r10 = r7.source;
        r7 = r3 + 1;
        r3 = r10[r3];
        r3 = toASCIIHexDigit(r3);
        if (r3 >= 0) goto L_0x00e1;
    L_0x00d6:
        r0 = r5 + 1;
        r0 = r7 - r0;
        r2 = r0;
        r0 = r1;
    L_0x00dc:
        r0 = (char) r0;
        r3 = r2;
        r2 = r0;
        goto L_0x004d;
    L_0x00e1:
        r2 = r2 << 4;
        r3 = r3 | r2;
        r2 = r5 + 1;
        r5 = r2;
        r2 = r3;
        r3 = r7;
        goto L_0x00c4;
    L_0x00ea:
        r0 = r3 + -48;
        r3 = r14.regexp;
        r3 = r3.source;
        r5 = r3[r2];
        if (r11 > r5) goto L_0x0113;
    L_0x00f4:
        if (r5 > r12) goto L_0x0113;
    L_0x00f6:
        r3 = r2 + 1;
        r0 = r0 * 8;
        r2 = r5 + -48;
        r2 = r2 + r0;
        r0 = r14.regexp;
        r0 = r0.source;
        r0 = r0[r3];
        if (r11 > r0) goto L_0x01dc;
    L_0x0105:
        if (r0 > r12) goto L_0x01dc;
    L_0x0107:
        r3 = r3 + 1;
        r5 = r2 * 8;
        r0 = r0 + -48;
        r0 = r0 + r5;
        r5 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        if (r0 > r5) goto L_0x0118;
    L_0x0112:
        r2 = r3;
    L_0x0113:
        r0 = (char) r0;
        r3 = r2;
        r2 = r0;
        goto L_0x004d;
    L_0x0118:
        r0 = r3 + -1;
        r13 = r2;
        r2 = r0;
        r0 = r13;
        goto L_0x0113;
    L_0x011e:
        r0 = 57;
        addCharacterRangeToCharSet(r15, r11, r0);
        r3 = r2;
        goto L_0x0037;
    L_0x0126:
        r0 = 47;
        addCharacterRangeToCharSet(r15, r6, r0);
        r0 = 58;
        r3 = r15.length;
        r3 = r3 + -1;
        r3 = (char) r3;
        addCharacterRangeToCharSet(r15, r0, r3);
        r3 = r2;
        goto L_0x0037;
    L_0x0138:
        r0 = r15.length;
        r0 = r0 + -1;
    L_0x013c:
        if (r0 < 0) goto L_0x01d9;
    L_0x013e:
        r3 = isREWhiteSpace(r0);
        if (r3 == 0) goto L_0x0148;
    L_0x0144:
        r3 = (char) r0;
        addCharacterToCharSet(r15, r3);
    L_0x0148:
        r0 = r0 + -1;
        goto L_0x013c;
    L_0x014b:
        r0 = r15.length;
        r0 = r0 + -1;
    L_0x014f:
        if (r0 < 0) goto L_0x01d9;
    L_0x0151:
        r3 = isREWhiteSpace(r0);
        if (r3 != 0) goto L_0x015b;
    L_0x0157:
        r3 = (char) r0;
        addCharacterToCharSet(r15, r3);
    L_0x015b:
        r0 = r0 + -1;
        goto L_0x014f;
    L_0x015e:
        r0 = r15.length;
        r0 = r0 + -1;
    L_0x0162:
        if (r0 < 0) goto L_0x01d9;
    L_0x0164:
        r3 = (char) r0;
        r3 = isWord(r3);
        if (r3 == 0) goto L_0x016f;
    L_0x016b:
        r3 = (char) r0;
        addCharacterToCharSet(r15, r3);
    L_0x016f:
        r0 = r0 + -1;
        goto L_0x0162;
    L_0x0172:
        r0 = r15.length;
        r0 = r0 + -1;
    L_0x0176:
        if (r0 < 0) goto L_0x01d9;
    L_0x0178:
        r3 = (char) r0;
        r3 = isWord(r3);
        if (r3 != 0) goto L_0x0183;
    L_0x017f:
        r3 = (char) r0;
        addCharacterToCharSet(r15, r3);
    L_0x0183:
        r0 = r0 + -1;
        goto L_0x0176;
    L_0x0186:
        r0 = r4;
    L_0x0187:
        if (r0 > r2) goto L_0x01a3;
    L_0x0189:
        addCharacterToCharSet(r15, r0);
        r5 = upcase(r0);
        r7 = downcase(r0);
        if (r0 == r5) goto L_0x0199;
    L_0x0196:
        addCharacterToCharSet(r15, r5);
    L_0x0199:
        if (r0 == r7) goto L_0x019e;
    L_0x019b:
        addCharacterToCharSet(r15, r7);
    L_0x019e:
        r0 = r0 + 1;
        r0 = (char) r0;
        if (r0 != 0) goto L_0x0187;
    L_0x01a3:
        r8 = r6;
        goto L_0x0037;
    L_0x01a6:
        addCharacterRangeToCharSet(r15, r4, r2);
        goto L_0x01a3;
    L_0x01aa:
        r0 = r14.regexp;
        r0 = r0.flags;
        r0 = r0 & 2;
        if (r0 == 0) goto L_0x01d5;
    L_0x01b2:
        r0 = upcase(r2);
        addCharacterToCharSet(r15, r0);
        r0 = downcase(r2);
        addCharacterToCharSet(r15, r0);
    L_0x01c0:
        r0 = r9 + -1;
        if (r3 >= r0) goto L_0x0037;
    L_0x01c4:
        r0 = r14.regexp;
        r0 = r0.source;
        r0 = r0[r3];
        r5 = 45;
        if (r0 != r5) goto L_0x0037;
    L_0x01ce:
        r3 = r3 + 1;
        r0 = 1;
        r8 = r0;
        r4 = r2;
        goto L_0x0037;
    L_0x01d5:
        addCharacterToCharSet(r15, r2);
        goto L_0x01c0;
    L_0x01d9:
        r3 = r2;
        goto L_0x0037;
    L_0x01dc:
        r0 = r2;
        r2 = r3;
        goto L_0x0113;
    L_0x01e0:
        r0 = r2;
        r2 = r3;
        goto L_0x00dc;
    L_0x01e4:
        r8 = r6;
        r4 = r6;
        r3 = r2;
        goto L_0x0037;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.processCharSetImpl(org.mozilla.javascript.regexp.REGlobalData, org.mozilla.javascript.regexp.RECharSet):void");
    }

    private static boolean classMatcher(REGlobalData rEGlobalData, RECharSet rECharSet, char c) {
        int i = 1;
        if (!rECharSet.converted) {
            processCharSet(rEGlobalData, rECharSet);
        }
        int i2 = c >> 3;
        if (!(rECharSet.length == 0 || c >= rECharSet.length || (rECharSet.bits[i2] & (1 << (c & 7))) == 0)) {
            i = 0;
        }
        return i ^ rECharSet.sense;
    }

    private static boolean reopIsSimple(int i) {
        return i >= 1 && i <= 23;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int simpleMatch(org.mozilla.javascript.regexp.REGlobalData r6, java.lang.String r7, int r8, byte[] r9, int r10, int r11, boolean r12) {
        /*
        r1 = 0;
        r2 = 1;
        r3 = r6.cp;
        switch(r8) {
            case 1: goto L_0x000c;
            case 2: goto L_0x0015;
            case 3: goto L_0x0030;
            case 4: goto L_0x0049;
            case 5: goto L_0x0072;
            case 6: goto L_0x009e;
            case 7: goto L_0x00b8;
            case 8: goto L_0x00d2;
            case 9: goto L_0x00ec;
            case 10: goto L_0x0106;
            case 11: goto L_0x0120;
            case 12: goto L_0x013a;
            case 13: goto L_0x0154;
            case 14: goto L_0x0161;
            case 15: goto L_0x0174;
            case 16: goto L_0x0190;
            case 17: goto L_0x01a3;
            case 18: goto L_0x01c9;
            case 19: goto L_0x01e6;
            case 20: goto L_0x0007;
            case 21: goto L_0x0007;
            case 22: goto L_0x020d;
            case 23: goto L_0x020d;
            default: goto L_0x0007;
        };
    L_0x0007:
        r0 = org.mozilla.javascript.Kit.codeBug();
        throw r0;
    L_0x000c:
        r1 = r2;
        r0 = r10;
    L_0x000e:
        if (r1 == 0) goto L_0x0233;
    L_0x0010:
        if (r12 != 0) goto L_0x0014;
    L_0x0012:
        r6.cp = r3;
    L_0x0014:
        return r0;
    L_0x0015:
        r0 = r6.cp;
        if (r0 == 0) goto L_0x002d;
    L_0x0019:
        r0 = r6.multiline;
        if (r0 == 0) goto L_0x0238;
    L_0x001d:
        r0 = r6.cp;
        r0 = r0 + -1;
        r0 = r7.charAt(r0);
        r0 = isLineTerm(r0);
        if (r0 != 0) goto L_0x002d;
    L_0x002b:
        r0 = r10;
        goto L_0x000e;
    L_0x002d:
        r1 = r2;
        r0 = r10;
        goto L_0x000e;
    L_0x0030:
        r0 = r6.cp;
        if (r0 == r11) goto L_0x0046;
    L_0x0034:
        r0 = r6.multiline;
        if (r0 == 0) goto L_0x0238;
    L_0x0038:
        r0 = r6.cp;
        r0 = r7.charAt(r0);
        r0 = isLineTerm(r0);
        if (r0 != 0) goto L_0x0046;
    L_0x0044:
        r0 = r10;
        goto L_0x000e;
    L_0x0046:
        r1 = r2;
        r0 = r10;
        goto L_0x000e;
    L_0x0049:
        r0 = r6.cp;
        if (r0 == 0) goto L_0x005b;
    L_0x004d:
        r0 = r6.cp;
        r0 = r0 + -1;
        r0 = r7.charAt(r0);
        r0 = isWord(r0);
        if (r0 != 0) goto L_0x0070;
    L_0x005b:
        r0 = r2;
    L_0x005c:
        r4 = r6.cp;
        if (r4 >= r11) goto L_0x006c;
    L_0x0060:
        r4 = r6.cp;
        r4 = r7.charAt(r4);
        r4 = isWord(r4);
        if (r4 != 0) goto L_0x006d;
    L_0x006c:
        r1 = r2;
    L_0x006d:
        r1 = r1 ^ r0;
        r0 = r10;
        goto L_0x000e;
    L_0x0070:
        r0 = r1;
        goto L_0x005c;
    L_0x0072:
        r0 = r6.cp;
        if (r0 == 0) goto L_0x0084;
    L_0x0076:
        r0 = r6.cp;
        r0 = r0 + -1;
        r0 = r7.charAt(r0);
        r0 = isWord(r0);
        if (r0 != 0) goto L_0x009a;
    L_0x0084:
        r0 = r2;
    L_0x0085:
        r4 = r6.cp;
        if (r4 >= r11) goto L_0x009c;
    L_0x0089:
        r4 = r6.cp;
        r4 = r7.charAt(r4);
        r4 = isWord(r4);
        if (r4 == 0) goto L_0x009c;
    L_0x0095:
        r1 = r0 ^ r2;
        r0 = r10;
        goto L_0x000e;
    L_0x009a:
        r0 = r1;
        goto L_0x0085;
    L_0x009c:
        r2 = r1;
        goto L_0x0095;
    L_0x009e:
        r0 = r6.cp;
        if (r0 == r11) goto L_0x0238;
    L_0x00a2:
        r0 = r6.cp;
        r0 = r7.charAt(r0);
        r0 = isLineTerm(r0);
        if (r0 != 0) goto L_0x0238;
    L_0x00ae:
        r0 = r6.cp;
        r0 = r0 + 1;
        r6.cp = r0;
        r1 = r2;
        r0 = r10;
        goto L_0x000e;
    L_0x00b8:
        r0 = r6.cp;
        if (r0 == r11) goto L_0x0238;
    L_0x00bc:
        r0 = r6.cp;
        r0 = r7.charAt(r0);
        r0 = isDigit(r0);
        if (r0 == 0) goto L_0x0238;
    L_0x00c8:
        r0 = r6.cp;
        r0 = r0 + 1;
        r6.cp = r0;
        r1 = r2;
        r0 = r10;
        goto L_0x000e;
    L_0x00d2:
        r0 = r6.cp;
        if (r0 == r11) goto L_0x0238;
    L_0x00d6:
        r0 = r6.cp;
        r0 = r7.charAt(r0);
        r0 = isDigit(r0);
        if (r0 != 0) goto L_0x0238;
    L_0x00e2:
        r0 = r6.cp;
        r0 = r0 + 1;
        r6.cp = r0;
        r1 = r2;
        r0 = r10;
        goto L_0x000e;
    L_0x00ec:
        r0 = r6.cp;
        if (r0 == r11) goto L_0x0238;
    L_0x00f0:
        r0 = r6.cp;
        r0 = r7.charAt(r0);
        r0 = isWord(r0);
        if (r0 == 0) goto L_0x0238;
    L_0x00fc:
        r0 = r6.cp;
        r0 = r0 + 1;
        r6.cp = r0;
        r1 = r2;
        r0 = r10;
        goto L_0x000e;
    L_0x0106:
        r0 = r6.cp;
        if (r0 == r11) goto L_0x0238;
    L_0x010a:
        r0 = r6.cp;
        r0 = r7.charAt(r0);
        r0 = isWord(r0);
        if (r0 != 0) goto L_0x0238;
    L_0x0116:
        r0 = r6.cp;
        r0 = r0 + 1;
        r6.cp = r0;
        r1 = r2;
        r0 = r10;
        goto L_0x000e;
    L_0x0120:
        r0 = r6.cp;
        if (r0 == r11) goto L_0x0238;
    L_0x0124:
        r0 = r6.cp;
        r0 = r7.charAt(r0);
        r0 = isREWhiteSpace(r0);
        if (r0 == 0) goto L_0x0238;
    L_0x0130:
        r0 = r6.cp;
        r0 = r0 + 1;
        r6.cp = r0;
        r1 = r2;
        r0 = r10;
        goto L_0x000e;
    L_0x013a:
        r0 = r6.cp;
        if (r0 == r11) goto L_0x0238;
    L_0x013e:
        r0 = r6.cp;
        r0 = r7.charAt(r0);
        r0 = isREWhiteSpace(r0);
        if (r0 != 0) goto L_0x0238;
    L_0x014a:
        r0 = r6.cp;
        r0 = r0 + 1;
        r6.cp = r0;
        r1 = r2;
        r0 = r10;
        goto L_0x000e;
    L_0x0154:
        r0 = getIndex(r9, r10);
        r10 = r10 + 2;
        r1 = backrefMatcher(r6, r0, r7, r11);
        r0 = r10;
        goto L_0x000e;
    L_0x0161:
        r0 = getIndex(r9, r10);
        r1 = r10 + 2;
        r2 = getIndex(r9, r1);
        r10 = r1 + 2;
        r1 = flatNMatcher(r6, r0, r2, r7, r11);
        r0 = r10;
        goto L_0x000e;
    L_0x0174:
        r0 = r10 + 1;
        r4 = r9[r10];
        r4 = r4 & 255;
        r4 = (char) r4;
        r5 = r6.cp;
        if (r5 == r11) goto L_0x000e;
    L_0x017f:
        r5 = r6.cp;
        r5 = r7.charAt(r5);
        if (r5 != r4) goto L_0x000e;
    L_0x0187:
        r1 = r6.cp;
        r1 = r1 + 1;
        r6.cp = r1;
        r1 = r2;
        goto L_0x000e;
    L_0x0190:
        r0 = getIndex(r9, r10);
        r1 = r10 + 2;
        r2 = getIndex(r9, r1);
        r10 = r1 + 2;
        r1 = flatNIMatcher(r6, r0, r2, r7, r11);
        r0 = r10;
        goto L_0x000e;
    L_0x01a3:
        r0 = r10 + 1;
        r4 = r9[r10];
        r4 = r4 & 255;
        r4 = (char) r4;
        r5 = r6.cp;
        if (r5 == r11) goto L_0x000e;
    L_0x01ae:
        r5 = r6.cp;
        r5 = r7.charAt(r5);
        if (r4 == r5) goto L_0x01c0;
    L_0x01b6:
        r4 = upcase(r4);
        r5 = upcase(r5);
        if (r4 != r5) goto L_0x000e;
    L_0x01c0:
        r1 = r6.cp;
        r1 = r1 + 1;
        r6.cp = r1;
        r1 = r2;
        goto L_0x000e;
    L_0x01c9:
        r0 = getIndex(r9, r10);
        r0 = (char) r0;
        r10 = r10 + 2;
        r4 = r6.cp;
        if (r4 == r11) goto L_0x0238;
    L_0x01d4:
        r4 = r6.cp;
        r4 = r7.charAt(r4);
        if (r4 != r0) goto L_0x0238;
    L_0x01dc:
        r0 = r6.cp;
        r0 = r0 + 1;
        r6.cp = r0;
        r1 = r2;
        r0 = r10;
        goto L_0x000e;
    L_0x01e6:
        r0 = getIndex(r9, r10);
        r0 = (char) r0;
        r10 = r10 + 2;
        r4 = r6.cp;
        if (r4 == r11) goto L_0x0238;
    L_0x01f1:
        r4 = r6.cp;
        r4 = r7.charAt(r4);
        if (r0 == r4) goto L_0x0203;
    L_0x01f9:
        r0 = upcase(r0);
        r4 = upcase(r4);
        if (r0 != r4) goto L_0x020a;
    L_0x0203:
        r0 = r6.cp;
        r0 = r0 + 1;
        r6.cp = r0;
        r1 = r2;
    L_0x020a:
        r0 = r10;
        goto L_0x000e;
    L_0x020d:
        r0 = getIndex(r9, r10);
        r10 = r10 + 2;
        r4 = r6.cp;
        if (r4 == r11) goto L_0x0238;
    L_0x0217:
        r4 = r6.regexp;
        r4 = r4.classList;
        r0 = r4[r0];
        r4 = r6.cp;
        r4 = r7.charAt(r4);
        r0 = classMatcher(r6, r0, r4);
        if (r0 == 0) goto L_0x0238;
    L_0x0229:
        r0 = r6.cp;
        r0 = r0 + 1;
        r6.cp = r0;
        r1 = r2;
        r0 = r10;
        goto L_0x000e;
    L_0x0233:
        r6.cp = r3;
        r0 = -1;
        goto L_0x0014;
    L_0x0238:
        r0 = r10;
        goto L_0x000e;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.simpleMatch(org.mozilla.javascript.regexp.REGlobalData, java.lang.String, int, byte[], int, int, boolean):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean executeREBytecode(org.mozilla.javascript.regexp.REGlobalData r20, java.lang.String r21, int r22) {
        /*
        r1 = 0;
        r0 = r20;
        r2 = r0.regexp;
        r4 = r2.program;
        r9 = 57;
        r10 = 0;
        r8 = 0;
        r5 = 1;
        r3 = r4[r1];
        r0 = r20;
        r1 = r0.regexp;
        r1 = r1.anchorCh;
        if (r1 >= 0) goto L_0x0448;
    L_0x0016:
        r1 = reopIsSimple(r3);
        if (r1 == 0) goto L_0x0448;
    L_0x001c:
        r11 = 0;
    L_0x001d:
        r0 = r20;
        r1 = r0.cp;
        r0 = r22;
        if (r1 > r0) goto L_0x044c;
    L_0x0025:
        r7 = 1;
        r1 = r20;
        r2 = r21;
        r6 = r22;
        r2 = simpleMatch(r1, r2, r3, r4, r5, r6, r7);
        if (r2 < 0) goto L_0x003b;
    L_0x0032:
        r1 = 1;
        r5 = r2 + 1;
        r3 = r4[r2];
    L_0x0037:
        if (r1 != 0) goto L_0x0448;
    L_0x0039:
        r1 = 0;
    L_0x003a:
        return r1;
    L_0x003b:
        r0 = r20;
        r1 = r0.skipped;
        r1 = r1 + 1;
        r0 = r20;
        r0.skipped = r1;
        r0 = r20;
        r1 = r0.cp;
        r1 = r1 + 1;
        r0 = r20;
        r0.cp = r1;
        goto L_0x001d;
    L_0x0050:
        r1 = 1;
        r2 = r3 + 1;
        r3 = r4[r3];
    L_0x0055:
        r7 = r11 + 1;
        r6 = r4[r11];
        r5 = r20;
        pushBackTrackState(r5, r6, r7, r8, r9, r10);
        r18 = r1;
        r5 = r2;
    L_0x0061:
        r1 = reopIsSimple(r3);
        if (r1 == 0) goto L_0x00aa;
    L_0x0067:
        r7 = 1;
        r1 = r20;
        r2 = r21;
        r6 = r22;
        r1 = simpleMatch(r1, r2, r3, r4, r5, r6, r7);
        if (r1 < 0) goto L_0x00a7;
    L_0x0074:
        r18 = 1;
    L_0x0076:
        if (r18 == 0) goto L_0x0079;
    L_0x0078:
        r5 = r1;
    L_0x0079:
        r1 = r18;
        r2 = r5;
    L_0x007c:
        if (r1 != 0) goto L_0x042c;
    L_0x007e:
        r0 = r20;
        r2 = r0.backTrackStackTop;
        if (r2 == 0) goto L_0x0429;
    L_0x0084:
        r3 = r2.previous;
        r0 = r20;
        r0.backTrackStackTop = r3;
        r3 = r2.parens;
        r0 = r20;
        r0.parens = r3;
        r3 = r2.cp;
        r0 = r20;
        r0.cp = r3;
        r3 = r2.stateStackTop;
        r0 = r20;
        r0.stateStackTop = r3;
        r9 = r2.continuationOp;
        r10 = r2.continuationPc;
        r5 = r2.pc;
        r3 = r2.op;
        r18 = r1;
        goto L_0x0061;
    L_0x00a7:
        r18 = 0;
        goto L_0x0076;
    L_0x00aa:
        switch(r3) {
            case 25: goto L_0x020b;
            case 26: goto L_0x020b;
            case 27: goto L_0x020b;
            case 28: goto L_0x020b;
            case 29: goto L_0x0135;
            case 30: goto L_0x014b;
            case 31: goto L_0x0101;
            case 32: goto L_0x012a;
            case 33: goto L_0x00ad;
            case 34: goto L_0x00ad;
            case 35: goto L_0x00ad;
            case 36: goto L_0x00ad;
            case 37: goto L_0x00ad;
            case 38: goto L_0x00ad;
            case 39: goto L_0x00ad;
            case 40: goto L_0x00ad;
            case 41: goto L_0x0167;
            case 42: goto L_0x01a6;
            case 43: goto L_0x01eb;
            case 44: goto L_0x01eb;
            case 45: goto L_0x020b;
            case 46: goto L_0x020b;
            case 47: goto L_0x020b;
            case 48: goto L_0x020b;
            case 49: goto L_0x0277;
            case 50: goto L_0x00ad;
            case 51: goto L_0x0280;
            case 52: goto L_0x0360;
            case 53: goto L_0x00b5;
            case 54: goto L_0x00b5;
            case 55: goto L_0x00b5;
            case 56: goto L_0x00ad;
            case 57: goto L_0x0426;
            default: goto L_0x00ad;
        };
    L_0x00ad:
        r1 = "invalid bytecode";
        r1 = org.mozilla.javascript.Kit.codeBug(r1);
        throw r1;
    L_0x00b5:
        r1 = getIndex(r4, r5);
        r2 = (char) r1;
        r1 = r5 + 2;
        r5 = getIndex(r4, r1);
        r6 = (char) r5;
        r5 = r1 + 2;
        r0 = r20;
        r1 = r0.cp;
        r0 = r22;
        if (r1 != r0) goto L_0x00ce;
    L_0x00cb:
        r1 = 0;
        r2 = r5;
        goto L_0x007c;
    L_0x00ce:
        r0 = r20;
        r1 = r0.cp;
        r0 = r21;
        r1 = r0.charAt(r1);
        r7 = 55;
        if (r3 != r7) goto L_0x00f1;
    L_0x00dc:
        if (r1 == r2) goto L_0x0101;
    L_0x00de:
        r0 = r20;
        r2 = r0.regexp;
        r2 = r2.classList;
        r2 = r2[r6];
        r0 = r20;
        r1 = classMatcher(r0, r2, r1);
        if (r1 != 0) goto L_0x0101;
    L_0x00ee:
        r1 = 0;
        r2 = r5;
        goto L_0x007c;
    L_0x00f1:
        r7 = 54;
        if (r3 != r7) goto L_0x00f9;
    L_0x00f5:
        r1 = upcase(r1);
    L_0x00f9:
        if (r1 == r2) goto L_0x0101;
    L_0x00fb:
        if (r1 == r6) goto L_0x0101;
    L_0x00fd:
        r1 = 0;
        r2 = r5;
        goto L_0x007c;
    L_0x0101:
        r1 = getOffset(r4, r5);
        r11 = r5 + r1;
        r1 = r5 + 2;
        r5 = r1 + 1;
        r3 = r4[r1];
        r0 = r20;
        r8 = r0.cp;
        r1 = reopIsSimple(r3);
        if (r1 == 0) goto L_0x0443;
    L_0x0117:
        r7 = 1;
        r1 = r20;
        r2 = r21;
        r6 = r22;
        r3 = simpleMatch(r1, r2, r3, r4, r5, r6, r7);
        if (r3 >= 0) goto L_0x0050;
    L_0x0124:
        r5 = r11 + 1;
        r3 = r4[r11];
        goto L_0x0061;
    L_0x012a:
        r1 = getOffset(r4, r5);
        r1 = r1 + r5;
        r5 = r1 + 1;
        r3 = r4[r1];
        goto L_0x0061;
    L_0x0135:
        r1 = getIndex(r4, r5);
        r2 = r5 + 2;
        r0 = r20;
        r3 = r0.cp;
        r5 = 0;
        r0 = r20;
        r0.setParens(r1, r3, r5);
        r5 = r2 + 1;
        r3 = r4[r2];
        goto L_0x0061;
    L_0x014b:
        r1 = getIndex(r4, r5);
        r2 = r5 + 2;
        r0 = r20;
        r3 = r0.parensIndex(r1);
        r0 = r20;
        r5 = r0.cp;
        r5 = r5 - r3;
        r0 = r20;
        r0.setParens(r1, r3, r5);
        r5 = r2 + 1;
        r3 = r4[r2];
        goto L_0x0061;
    L_0x0167:
        r1 = getIndex(r4, r5);
        r8 = r5 + r1;
        r1 = r5 + 2;
        r5 = r1 + 1;
        r3 = r4[r1];
        r1 = reopIsSimple(r3);
        if (r1 == 0) goto L_0x018a;
    L_0x0179:
        r7 = 0;
        r1 = r20;
        r2 = r21;
        r6 = r22;
        r1 = simpleMatch(r1, r2, r3, r4, r5, r6, r7);
        if (r1 >= 0) goto L_0x018a;
    L_0x0186:
        r1 = 0;
        r2 = r5;
        goto L_0x007c;
    L_0x018a:
        r12 = 0;
        r13 = 0;
        r0 = r20;
        r14 = r0.cp;
        r0 = r20;
        r15 = r0.backTrackStackTop;
        r11 = r20;
        r16 = r9;
        r17 = r10;
        pushProgState(r11, r12, r13, r14, r15, r16, r17);
        r1 = 43;
        r0 = r20;
        pushBackTrackState(r0, r1, r8);
        goto L_0x0061;
    L_0x01a6:
        r1 = getIndex(r4, r5);
        r8 = r5 + r1;
        r1 = r5 + 2;
        r5 = r1 + 1;
        r3 = r4[r1];
        r1 = reopIsSimple(r3);
        if (r1 == 0) goto L_0x01cf;
    L_0x01b8:
        r7 = 0;
        r1 = r20;
        r2 = r21;
        r6 = r22;
        r1 = simpleMatch(r1, r2, r3, r4, r5, r6, r7);
        if (r1 < 0) goto L_0x01cf;
    L_0x01c5:
        r1 = r4[r1];
        r2 = 44;
        if (r1 != r2) goto L_0x01cf;
    L_0x01cb:
        r1 = 0;
        r2 = r5;
        goto L_0x007c;
    L_0x01cf:
        r12 = 0;
        r13 = 0;
        r0 = r20;
        r14 = r0.cp;
        r0 = r20;
        r15 = r0.backTrackStackTop;
        r11 = r20;
        r16 = r9;
        r17 = r10;
        pushProgState(r11, r12, r13, r14, r15, r16, r17);
        r1 = 44;
        r0 = r20;
        pushBackTrackState(r0, r1, r8);
        goto L_0x0061;
    L_0x01eb:
        r1 = popProgState(r20);
        r2 = r1.index;
        r0 = r20;
        r0.cp = r2;
        r2 = r1.backTrack;
        r0 = r20;
        r0.backTrackStackTop = r2;
        r10 = r1.continuationPc;
        r9 = r1.continuationOp;
        r1 = 44;
        if (r3 != r1) goto L_0x043f;
    L_0x0203:
        if (r18 != 0) goto L_0x0209;
    L_0x0205:
        r1 = 1;
    L_0x0206:
        r2 = r5;
        goto L_0x007c;
    L_0x0209:
        r1 = 0;
        goto L_0x0206;
    L_0x020b:
        r1 = 0;
        switch(r3) {
            case 25: goto L_0x0242;
            case 26: goto L_0x0214;
            case 27: goto L_0x023a;
            case 28: goto L_0x023e;
            case 45: goto L_0x0215;
            case 46: goto L_0x023b;
            case 47: goto L_0x023f;
            case 48: goto L_0x0243;
            default: goto L_0x020f;
        };
    L_0x020f:
        r1 = org.mozilla.javascript.Kit.codeBug();
        throw r1;
    L_0x0214:
        r1 = 1;
    L_0x0215:
        r12 = 0;
        r13 = -1;
    L_0x0217:
        r0 = r20;
        r14 = r0.cp;
        r15 = 0;
        r11 = r20;
        r16 = r9;
        r17 = r10;
        pushProgState(r11, r12, r13, r14, r15, r16, r17);
        if (r1 == 0) goto L_0x0252;
    L_0x0227:
        r1 = 51;
        r0 = r20;
        pushBackTrackState(r0, r1, r5);
        r9 = 51;
        r2 = r5 + 6;
        r1 = r2 + 1;
        r3 = r4[r2];
        r10 = r5;
        r5 = r1;
        goto L_0x0061;
    L_0x023a:
        r1 = 1;
    L_0x023b:
        r12 = 1;
        r13 = -1;
        goto L_0x0217;
    L_0x023e:
        r1 = 1;
    L_0x023f:
        r12 = 0;
        r13 = 1;
        goto L_0x0217;
    L_0x0242:
        r1 = 1;
    L_0x0243:
        r12 = getOffset(r4, r5);
        r2 = r5 + 2;
        r3 = getOffset(r4, r2);
        r13 = r3 + -1;
        r5 = r2 + 2;
        goto L_0x0217;
    L_0x0252:
        if (r12 == 0) goto L_0x0260;
    L_0x0254:
        r9 = 52;
        r2 = r5 + 6;
        r1 = r2 + 1;
        r3 = r4[r2];
        r10 = r5;
        r5 = r1;
        goto L_0x0061;
    L_0x0260:
        r1 = 52;
        r0 = r20;
        pushBackTrackState(r0, r1, r5);
        popProgState(r20);
        r1 = r5 + 4;
        r2 = getOffset(r4, r1);
        r1 = r1 + r2;
        r5 = r1 + 1;
        r3 = r4[r1];
        goto L_0x0061;
    L_0x0277:
        r1 = 1;
        r3 = r9;
        r18 = r1;
        r5 = r10;
        goto L_0x0061;
    L_0x027e:
        r18 = r1;
    L_0x0280:
        r15 = popProgState(r20);
        if (r18 != 0) goto L_0x029d;
    L_0x0286:
        r1 = r15.min;
        if (r1 != 0) goto L_0x028c;
    L_0x028a:
        r18 = 1;
    L_0x028c:
        r10 = r15.continuationPc;
        r9 = r15.continuationOp;
        r1 = r5 + 4;
        r2 = getOffset(r4, r1);
        r5 = r1 + r2;
        r1 = r18;
        r2 = r5;
        goto L_0x007c;
    L_0x029d:
        r1 = r15.min;
        if (r1 != 0) goto L_0x02b9;
    L_0x02a1:
        r0 = r20;
        r1 = r0.cp;
        r2 = r15.index;
        if (r1 != r2) goto L_0x02b9;
    L_0x02a9:
        r1 = 0;
        r10 = r15.continuationPc;
        r9 = r15.continuationOp;
        r2 = r5 + 4;
        r3 = getOffset(r4, r2);
        r5 = r2 + r3;
        r2 = r5;
        goto L_0x007c;
    L_0x02b9:
        r2 = r15.min;
        r1 = r15.max;
        if (r2 == 0) goto L_0x043c;
    L_0x02bf:
        r2 = r2 + -1;
        r14 = r2;
    L_0x02c2:
        r2 = -1;
        if (r1 == r2) goto L_0x0439;
    L_0x02c5:
        r1 = r1 + -1;
        r13 = r1;
    L_0x02c8:
        if (r13 != 0) goto L_0x02da;
    L_0x02ca:
        r1 = 1;
        r10 = r15.continuationPc;
        r9 = r15.continuationOp;
        r2 = r5 + 4;
        r3 = getOffset(r4, r2);
        r5 = r2 + r3;
        r2 = r5;
        goto L_0x007c;
    L_0x02da:
        r1 = r5 + 6;
        r8 = r4[r1];
        r0 = r20;
        r0 = r0.cp;
        r16 = r0;
        r2 = reopIsSimple(r8);
        if (r2 == 0) goto L_0x0434;
    L_0x02ea:
        r10 = r1 + 1;
        r12 = 1;
        r6 = r20;
        r7 = r21;
        r9 = r4;
        r11 = r22;
        r1 = simpleMatch(r6, r7, r8, r9, r10, r11, r12);
        if (r1 >= 0) goto L_0x030e;
    L_0x02fa:
        if (r14 != 0) goto L_0x030c;
    L_0x02fc:
        r1 = 1;
    L_0x02fd:
        r10 = r15.continuationPc;
        r9 = r15.continuationOp;
        r2 = r5 + 4;
        r3 = getOffset(r4, r2);
        r5 = r2 + r3;
        r2 = r5;
        goto L_0x007c;
    L_0x030c:
        r1 = 0;
        goto L_0x02fd;
    L_0x030e:
        r18 = 1;
        r3 = r1;
        r1 = r18;
    L_0x0313:
        r2 = 51;
        r10 = 0;
        r11 = r15.continuationOp;
        r12 = r15.continuationPc;
        r6 = r20;
        r7 = r14;
        r8 = r13;
        r9 = r16;
        pushProgState(r6, r7, r8, r9, r10, r11, r12);
        if (r14 != 0) goto L_0x034c;
    L_0x0325:
        r7 = 51;
        r10 = r15.continuationOp;
        r11 = r15.continuationPc;
        r6 = r20;
        r8 = r5;
        r9 = r16;
        pushBackTrackState(r6, r7, r8, r9, r10, r11);
        r7 = getIndex(r4, r5);
        r6 = r5 + 2;
        r8 = getIndex(r4, r6);
        r6 = 0;
    L_0x033e:
        if (r6 >= r7) goto L_0x034c;
    L_0x0340:
        r9 = r8 + r6;
        r10 = -1;
        r11 = 0;
        r0 = r20;
        r0.setParens(r9, r10, r11);
        r6 = r6 + 1;
        goto L_0x033e;
    L_0x034c:
        r6 = r4[r3];
        r7 = 49;
        if (r6 == r7) goto L_0x027e;
    L_0x0352:
        r10 = r3 + 1;
        r3 = r4[r3];
        r18 = r1;
        r9 = r2;
        r19 = r5;
        r5 = r10;
        r10 = r19;
        goto L_0x0061;
    L_0x0360:
        r2 = popProgState(r20);
        if (r18 != 0) goto L_0x03b2;
    L_0x0366:
        r1 = r2.max;
        r3 = -1;
        if (r1 == r3) goto L_0x036f;
    L_0x036b:
        r1 = r2.max;
        if (r1 <= 0) goto L_0x03a9;
    L_0x036f:
        r7 = r2.min;
        r8 = r2.max;
        r0 = r20;
        r9 = r0.cp;
        r10 = 0;
        r11 = r2.continuationOp;
        r12 = r2.continuationPc;
        r6 = r20;
        pushProgState(r6, r7, r8, r9, r10, r11, r12);
        r9 = 52;
        r2 = getIndex(r4, r5);
        r1 = r5 + 2;
        r3 = getIndex(r4, r1);
        r6 = r1 + 4;
        r1 = 0;
    L_0x0390:
        if (r1 >= r2) goto L_0x039e;
    L_0x0392:
        r7 = r3 + r1;
        r8 = -1;
        r10 = 0;
        r0 = r20;
        r0.setParens(r7, r8, r10);
        r1 = r1 + 1;
        goto L_0x0390;
    L_0x039e:
        r10 = r6 + 1;
        r3 = r4[r6];
        r19 = r5;
        r5 = r10;
        r10 = r19;
        goto L_0x0061;
    L_0x03a9:
        r10 = r2.continuationPc;
        r9 = r2.continuationOp;
        r1 = r18;
        r2 = r5;
        goto L_0x007c;
    L_0x03b2:
        r1 = r2.min;
        if (r1 != 0) goto L_0x03c6;
    L_0x03b6:
        r0 = r20;
        r1 = r0.cp;
        r3 = r2.index;
        if (r1 != r3) goto L_0x03c6;
    L_0x03be:
        r1 = 0;
        r10 = r2.continuationPc;
        r9 = r2.continuationOp;
        r2 = r5;
        goto L_0x007c;
    L_0x03c6:
        r7 = r2.min;
        r8 = r2.max;
        if (r7 == 0) goto L_0x03ce;
    L_0x03cc:
        r7 = r7 + -1;
    L_0x03ce:
        r1 = -1;
        if (r8 == r1) goto L_0x03d3;
    L_0x03d1:
        r8 = r8 + -1;
    L_0x03d3:
        r0 = r20;
        r9 = r0.cp;
        r10 = 0;
        r11 = r2.continuationOp;
        r12 = r2.continuationPc;
        r6 = r20;
        pushProgState(r6, r7, r8, r9, r10, r11, r12);
        if (r7 == 0) goto L_0x040b;
    L_0x03e3:
        r9 = 52;
        r2 = getIndex(r4, r5);
        r1 = r5 + 2;
        r3 = getIndex(r4, r1);
        r6 = r1 + 4;
        r1 = 0;
    L_0x03f2:
        if (r1 >= r2) goto L_0x0400;
    L_0x03f4:
        r7 = r3 + r1;
        r8 = -1;
        r10 = 0;
        r0 = r20;
        r0.setParens(r7, r8, r10);
        r1 = r1 + 1;
        goto L_0x03f2;
    L_0x0400:
        r10 = r6 + 1;
        r3 = r4[r6];
        r19 = r5;
        r5 = r10;
        r10 = r19;
        goto L_0x0061;
    L_0x040b:
        r10 = r2.continuationPc;
        r9 = r2.continuationOp;
        r1 = 52;
        r0 = r20;
        pushBackTrackState(r0, r1, r5);
        popProgState(r20);
        r1 = r5 + 4;
        r2 = getOffset(r4, r1);
        r1 = r1 + r2;
        r5 = r1 + 1;
        r3 = r4[r1];
        goto L_0x0061;
    L_0x0426:
        r1 = 1;
        goto L_0x003a;
    L_0x0429:
        r1 = 0;
        goto L_0x003a;
    L_0x042c:
        r5 = r2 + 1;
        r3 = r4[r2];
        r18 = r1;
        goto L_0x0061;
    L_0x0434:
        r3 = r1;
        r1 = r18;
        goto L_0x0313;
    L_0x0439:
        r13 = r1;
        goto L_0x02c8;
    L_0x043c:
        r14 = r2;
        goto L_0x02c2;
    L_0x043f:
        r1 = r18;
        goto L_0x0206;
    L_0x0443:
        r1 = r18;
        r2 = r5;
        goto L_0x0055;
    L_0x0448:
        r18 = r8;
        goto L_0x0061;
    L_0x044c:
        r1 = r11;
        goto L_0x0037;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.executeREBytecode(org.mozilla.javascript.regexp.REGlobalData, java.lang.String, int):boolean");
    }

    private static boolean matchRegExp(REGlobalData rEGlobalData, RECompiled rECompiled, String str, int i, int i2, boolean z) {
        boolean z2;
        if (rECompiled.parenCount != 0) {
            rEGlobalData.parens = new long[rECompiled.parenCount];
        } else {
            rEGlobalData.parens = null;
        }
        rEGlobalData.backTrackStackTop = null;
        rEGlobalData.stateStackTop = null;
        if (z || (rECompiled.flags & 4) != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        rEGlobalData.multiline = z2;
        rEGlobalData.regexp = rECompiled;
        char c = rEGlobalData.regexp.anchorCh;
        int i3 = i;
        while (i3 <= i2) {
            if (c >= '\u0000') {
                while (i3 != i2) {
                    char charAt = str.charAt(i3);
                    if (charAt != c && ((rEGlobalData.regexp.flags & 2) == 0 || upcase(charAt) != upcase((char) c))) {
                        i3++;
                    }
                }
                return false;
            }
            rEGlobalData.cp = i3;
            rEGlobalData.skipped = i3 - i;
            for (i3 = 0; i3 < rECompiled.parenCount; i3++) {
                rEGlobalData.parens[i3] = -1;
            }
            z2 = executeREBytecode(rEGlobalData, str, i2);
            rEGlobalData.backTrackStackTop = null;
            rEGlobalData.stateStackTop = null;
            if (z2) {
                return true;
            }
            if (c != '￾' || rEGlobalData.multiline) {
                i3 = (rEGlobalData.skipped + i) + 1;
            } else {
                rEGlobalData.skipped = i2;
                return false;
            }
        }
        return false;
    }

    Object executeRegExp(Context context, Scriptable scriptable, RegExpImpl regExpImpl, String str, int[] iArr, int i) {
        REGlobalData rEGlobalData = new REGlobalData();
        int i2 = iArr[0];
        int length = str.length();
        if (i2 > length) {
            i2 = length;
        }
        if (matchRegExp(rEGlobalData, this.re, str, i2, length, regExpImpl.multiline)) {
            Object obj;
            Scriptable scriptable2;
            int i3 = rEGlobalData.cp;
            iArr[0] = i3;
            int i4 = i3 - (rEGlobalData.skipped + i2);
            int i5 = i3 - i4;
            if (i == 0) {
                obj = Boolean.TRUE;
                scriptable2 = null;
            } else {
                obj = context.newArray(scriptable, 0);
                scriptable2 = (Scriptable) obj;
                scriptable2.put(0, scriptable2, str.substring(i5, i5 + i4));
            }
            if (this.re.parenCount == 0) {
                regExpImpl.parens = null;
                regExpImpl.lastParen = SubString.emptySubString;
            } else {
                regExpImpl.parens = new SubString[this.re.parenCount];
                SubString subString = null;
                for (int i6 = 0; i6 < this.re.parenCount; i6++) {
                    int parensIndex = rEGlobalData.parensIndex(i6);
                    if (parensIndex != -1) {
                        subString = new SubString(str, parensIndex, rEGlobalData.parensLength(i6));
                        regExpImpl.parens[i6] = subString;
                        if (i != 0) {
                            scriptable2.put(i6 + 1, scriptable2, subString.toString());
                        }
                    } else if (i != 0) {
                        scriptable2.put(i6 + 1, scriptable2, Undefined.instance);
                    }
                }
                regExpImpl.lastParen = subString;
            }
            if (i != 0) {
                scriptable2.put("index", scriptable2, Integer.valueOf(rEGlobalData.skipped + i2));
                scriptable2.put("input", scriptable2, (Object) str);
            }
            if (regExpImpl.lastMatch == null) {
                regExpImpl.lastMatch = new SubString();
                regExpImpl.leftContext = new SubString();
                regExpImpl.rightContext = new SubString();
            }
            regExpImpl.lastMatch.str = str;
            regExpImpl.lastMatch.index = i5;
            regExpImpl.lastMatch.length = i4;
            regExpImpl.leftContext.str = str;
            if (context.getLanguageVersion() == 120) {
                regExpImpl.leftContext.index = i2;
                regExpImpl.leftContext.length = rEGlobalData.skipped;
            } else {
                regExpImpl.leftContext.index = 0;
                regExpImpl.leftContext.length = rEGlobalData.skipped + i2;
            }
            regExpImpl.rightContext.str = str;
            regExpImpl.rightContext.index = i3;
            regExpImpl.rightContext.length = length - i3;
            return obj;
        } else if (i != 2) {
            return null;
        } else {
            return Undefined.instance;
        }
    }

    int getFlags() {
        return this.re.flags;
    }

    private static void reportWarning(Context context, String str, String str2) {
        if (context.hasFeature(11)) {
            Context.reportWarning(ScriptRuntime.getMessage1(str, str2));
        }
    }

    private static void reportError(String str, String str2) {
        throw ScriptRuntime.constructError("SyntaxError", ScriptRuntime.getMessage1(str, str2));
    }

    protected int getMaxInstanceId() {
        return 5;
    }

    protected int findInstanceIdInfo(String str) {
        int i;
        String str2;
        int length = str.length();
        char charAt;
        if (length == 6) {
            charAt = str.charAt(0);
            if (charAt == 'g') {
                i = 3;
                str2 = "global";
            } else {
                if (charAt == 's') {
                    i = 2;
                    str2 = "source";
                }
                str2 = null;
                i = 0;
            }
        } else if (length == 9) {
            charAt = str.charAt(0);
            if (charAt == 'l') {
                i = 1;
                str2 = "lastIndex";
            } else {
                if (charAt == 'm') {
                    i = 5;
                    str2 = "multiline";
                }
                str2 = null;
                i = 0;
            }
        } else {
            if (length == 10) {
                i = 4;
                str2 = "ignoreCase";
            }
            str2 = null;
            i = 0;
        }
        if (!(str2 == null || str2 == str || str2.equals(str))) {
            i = 0;
        }
        if (i == 0) {
            return super.findInstanceIdInfo(str);
        }
        int i2;
        switch (i) {
            case 1:
                i2 = this.lastIndexAttr;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                i2 = 7;
                break;
            default:
                throw new IllegalStateException();
        }
        return IdScriptableObject.instanceIdInfo(i2, i);
    }

    protected String getInstanceIdName(int i) {
        switch (i) {
            case 1:
                return "lastIndex";
            case 2:
                return "source";
            case 3:
                return "global";
            case 4:
                return "ignoreCase";
            case 5:
                return "multiline";
            default:
                return super.getInstanceIdName(i);
        }
    }

    protected Object getInstanceIdValue(int i) {
        boolean z = true;
        switch (i) {
            case 1:
                return this.lastIndex;
            case 2:
                return new String(this.re.source);
            case 3:
                if ((this.re.flags & 1) == 0) {
                    z = false;
                }
                return ScriptRuntime.wrapBoolean(z);
            case 4:
                if ((this.re.flags & 2) == 0) {
                    z = false;
                }
                return ScriptRuntime.wrapBoolean(z);
            case 5:
                if ((this.re.flags & 4) == 0) {
                    z = false;
                }
                return ScriptRuntime.wrapBoolean(z);
            default:
                return super.getInstanceIdValue(i);
        }
    }

    protected void setInstanceIdValue(int i, Object obj) {
        switch (i) {
            case 1:
                this.lastIndex = obj;
                return;
            case 2:
            case 3:
            case 4:
            case 5:
                return;
            default:
                super.setInstanceIdValue(i, obj);
                return;
        }
    }

    protected void setInstanceIdAttributes(int i, int i2) {
        switch (i) {
            case 1:
                this.lastIndexAttr = i2;
                return;
            default:
                super.setInstanceIdAttributes(i, i2);
                return;
        }
    }

    protected void initPrototypeId(int i) {
        String str;
        int i2 = 0;
        switch (i) {
            case 1:
                i2 = 2;
                str = "compile";
                break;
            case 2:
                str = "toString";
                break;
            case 3:
                str = "toSource";
                break;
            case 4:
                str = "exec";
                i2 = 1;
                break;
            case 5:
                str = "test";
                i2 = 1;
                break;
            case 6:
                str = RequestParameters.PREFIX;
                i2 = 1;
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(REGEXP_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(REGEXP_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        switch (methodId) {
            case 1:
                return realThis(scriptable2, idFunctionObject).compile(context, scriptable, objArr);
            case 2:
            case 3:
                return realThis(scriptable2, idFunctionObject).toString();
            case 4:
                return realThis(scriptable2, idFunctionObject).execSub(context, scriptable, objArr, 1);
            case 5:
                return Boolean.TRUE.equals(realThis(scriptable2, idFunctionObject).execSub(context, scriptable, objArr, 0)) ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                return realThis(scriptable2, idFunctionObject).execSub(context, scriptable, objArr, 2);
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    private static NativeRegExp realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (scriptable instanceof NativeRegExp) {
            return (NativeRegExp) scriptable;
        }
        throw IdScriptableObject.incompatibleCallError(idFunctionObject);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int findPrototypeId(java.lang.String r8) {
        /*
        r7 = this;
        r5 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        r2 = 3;
        r0 = 0;
        r1 = 0;
        r3 = r8.length();
        switch(r3) {
            case 4: goto L_0x0019;
            case 5: goto L_0x000c;
            case 6: goto L_0x0033;
            case 7: goto L_0x003b;
            case 8: goto L_0x0043;
            default: goto L_0x000c;
        };
    L_0x000c:
        r2 = r1;
        r1 = r0;
    L_0x000e:
        if (r2 == 0) goto L_0x005c;
    L_0x0010:
        if (r2 == r8) goto L_0x005c;
    L_0x0012:
        r2 = r2.equals(r8);
        if (r2 != 0) goto L_0x005c;
    L_0x0018:
        return r0;
    L_0x0019:
        r2 = r8.charAt(r0);
        r3 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r2 != r3) goto L_0x0029;
    L_0x0021:
        r1 = "exec";
        r2 = 4;
        r6 = r1;
        r1 = r2;
        r2 = r6;
        goto L_0x000e;
    L_0x0029:
        if (r2 != r5) goto L_0x000c;
    L_0x002b:
        r1 = "test";
        r2 = 5;
        r6 = r1;
        r1 = r2;
        r2 = r6;
        goto L_0x000e;
    L_0x0033:
        r1 = "prefix";
        r2 = 6;
        r6 = r1;
        r1 = r2;
        r2 = r6;
        goto L_0x000e;
    L_0x003b:
        r1 = "compile";
        r2 = 1;
        r6 = r1;
        r1 = r2;
        r2 = r6;
        goto L_0x000e;
    L_0x0043:
        r3 = r8.charAt(r2);
        r4 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        if (r3 != r4) goto L_0x0052;
    L_0x004b:
        r1 = "toSource";
        r6 = r1;
        r1 = r2;
        r2 = r6;
        goto L_0x000e;
    L_0x0052:
        if (r3 != r5) goto L_0x000c;
    L_0x0054:
        r1 = "toString";
        r2 = 2;
        r6 = r1;
        r1 = r2;
        r2 = r6;
        goto L_0x000e;
    L_0x005c:
        r0 = r1;
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.findPrototypeId(java.lang.String):int");
    }
}
