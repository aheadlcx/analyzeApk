package org.mozilla.javascript.regexp;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.RegExpProxy;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;

public class RegExpImpl implements RegExpProxy {
    protected String input;
    protected SubString lastMatch;
    protected SubString lastParen;
    protected SubString leftContext;
    protected boolean multiline;
    protected SubString[] parens;
    protected SubString rightContext;

    public boolean isRegExp(Scriptable scriptable) {
        return scriptable instanceof NativeRegExp;
    }

    public Object compileRegExp(Context context, String str, String str2) {
        return NativeRegExp.compileRE(context, str, str2, false);
    }

    public Scriptable wrapRegExp(Context context, Scriptable scriptable, Object obj) {
        return new NativeRegExp(scriptable, (RECompiled) obj);
    }

    public Object action(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, int i) {
        GlobData globData = new GlobData();
        globData.mode = i;
        globData.str = ScriptRuntime.toString(scriptable2);
        Object matchOrReplace;
        switch (i) {
            case 1:
                matchOrReplace = matchOrReplace(context, scriptable, scriptable2, objArr, this, globData, createRegExp(context, scriptable, objArr, 1, false));
                if (globData.arrayobj == null) {
                    return matchOrReplace;
                }
                return globData.arrayobj;
            case 2:
                String str;
                Function function;
                String str2;
                SubString subString;
                Object obj = ((objArr.length <= 0 || !(objArr[0] instanceof NativeRegExp)) && objArr.length <= 2) ? null : 1;
                NativeRegExp nativeRegExp = null;
                if (obj != null) {
                    nativeRegExp = createRegExp(context, scriptable, objArr, 2, true);
                    str = null;
                } else {
                    str = ScriptRuntime.toString(objArr.length < 1 ? Undefined.instance : objArr[0]);
                }
                matchOrReplace = objArr.length < 2 ? Undefined.instance : objArr[1];
                if (matchOrReplace instanceof Function) {
                    function = (Function) matchOrReplace;
                    str2 = null;
                } else {
                    str2 = ScriptRuntime.toString(matchOrReplace);
                    function = null;
                }
                globData.lambda = function;
                globData.repstr = str2;
                globData.dollar = str2 == null ? -1 : str2.indexOf(36);
                globData.charBuf = null;
                globData.leftIndex = 0;
                if (obj != null) {
                    matchOrReplace = matchOrReplace(context, scriptable, scriptable2, objArr, this, globData, nativeRegExp);
                } else {
                    String str3 = globData.str;
                    int indexOf = str3.indexOf(str);
                    if (indexOf >= 0) {
                        int length = str.length();
                        this.lastParen = null;
                        this.leftContext = new SubString(str3, 0, indexOf);
                        this.lastMatch = new SubString(str3, indexOf, length);
                        this.rightContext = new SubString(str3, indexOf + length, (str3.length() - indexOf) - length);
                        matchOrReplace = Boolean.TRUE;
                    } else {
                        matchOrReplace = Boolean.FALSE;
                    }
                }
                if (globData.charBuf == null) {
                    if (globData.global || r1 == null || !r1.equals(Boolean.TRUE)) {
                        return globData.str;
                    }
                    subString = this.leftContext;
                    replace_glob(globData, context, scriptable, this, subString.index, subString.length);
                }
                subString = this.rightContext;
                globData.charBuf.append(subString.str, subString.index, subString.length + subString.index);
                return globData.charBuf.toString();
            case 3:
                return matchOrReplace(context, scriptable, scriptable2, objArr, this, globData, createRegExp(context, scriptable, objArr, 1, false));
            default:
                throw Kit.codeBug();
        }
    }

    private static NativeRegExp createRegExp(Context context, Scriptable scriptable, Object[] objArr, int i, boolean z) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        if (objArr.length == 0 || objArr[0] == Undefined.instance) {
            return new NativeRegExp(topLevelScope, NativeRegExp.compileRE(context, "", "", false));
        }
        if (objArr[0] instanceof NativeRegExp) {
            return (NativeRegExp) objArr[0];
        }
        String scriptRuntime;
        String scriptRuntime2 = ScriptRuntime.toString(objArr[0]);
        if (i < objArr.length) {
            objArr[0] = scriptRuntime2;
            scriptRuntime = ScriptRuntime.toString(objArr[i]);
        } else {
            scriptRuntime = null;
        }
        return new NativeRegExp(topLevelScope, NativeRegExp.compileRE(context, scriptRuntime2, scriptRuntime, z));
    }

    private static Object matchOrReplace(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RegExpImpl regExpImpl, GlobData globData, NativeRegExp nativeRegExp) {
        String str = globData.str;
        globData.global = (nativeRegExp.getFlags() & 1) != 0;
        int[] iArr = new int[]{0};
        Object executeRegExp;
        if (globData.mode == 3) {
            executeRegExp = nativeRegExp.executeRegExp(context, scriptable, regExpImpl, str, iArr, 0);
            if (executeRegExp == null || !executeRegExp.equals(Boolean.TRUE)) {
                return Integer.valueOf(-1);
            }
            return Integer.valueOf(regExpImpl.leftContext.length);
        } else if (globData.global) {
            nativeRegExp.lastIndex = Double.valueOf(0.0d);
            int i = 0;
            executeRegExp = null;
            while (iArr[0] <= str.length()) {
                Object executeRegExp2 = nativeRegExp.executeRegExp(context, scriptable, regExpImpl, str, iArr, 0);
                if (executeRegExp2 == null || !executeRegExp2.equals(Boolean.TRUE)) {
                    return executeRegExp2;
                }
                if (globData.mode == 1) {
                    match_glob(globData, context, scriptable, i, regExpImpl);
                } else {
                    if (globData.mode != 2) {
                        Kit.codeBug();
                    }
                    SubString subString = regExpImpl.lastMatch;
                    int i2 = globData.leftIndex;
                    int i3 = subString.index - i2;
                    globData.leftIndex = subString.length + subString.index;
                    replace_glob(globData, context, scriptable, regExpImpl, i2, i3);
                }
                if (regExpImpl.lastMatch.length == 0) {
                    if (iArr[0] == str.length()) {
                        return executeRegExp2;
                    }
                    iArr[0] = iArr[0] + 1;
                }
                i++;
                executeRegExp = executeRegExp2;
            }
            return executeRegExp;
        } else {
            return nativeRegExp.executeRegExp(context, scriptable, regExpImpl, str, iArr, globData.mode == 2 ? 0 : 1);
        }
    }

    public int find_split(Context context, Scriptable scriptable, String str, String str2, Scriptable scriptable2, int[] iArr, int[] iArr2, boolean[] zArr, String[][] strArr) {
        int i;
        int i2 = iArr[0];
        int length = str.length();
        int languageVersion = context.getLanguageVersion();
        NativeRegExp nativeRegExp = (NativeRegExp) scriptable2;
        while (true) {
            int i3 = iArr[0];
            iArr[0] = i2;
            if (nativeRegExp.executeRegExp(context, scriptable, this, str, iArr, 0) != Boolean.TRUE) {
                iArr[0] = i3;
                iArr2[0] = 1;
                zArr[0] = false;
                return length;
            }
            int i4;
            i2 = iArr[0];
            iArr[0] = i3;
            zArr[0] = true;
            iArr2[0] = this.lastMatch.length;
            if (iArr2[0] != 0 || i2 != iArr[0]) {
                i = i2 - iArr2[0];
            } else if (i2 == length) {
                break;
            } else {
                i2++;
            }
            i2 = this.parens != null ? 0 : this.parens.length;
            strArr[0] = new String[i2];
            for (i4 = 0; i4 < i2; i4++) {
                strArr[0][i4] = getParenSubString(i4).toString();
            }
            return i;
        }
        if (languageVersion == 120) {
            iArr2[0] = 1;
            i = i2;
        } else {
            i = -1;
        }
        if (this.parens != null) {
        }
        strArr[0] = new String[i2];
        for (i4 = 0; i4 < i2; i4++) {
            strArr[0][i4] = getParenSubString(i4).toString();
        }
        return i;
    }

    SubString getParenSubString(int i) {
        if (this.parens != null && i < this.parens.length) {
            SubString subString = this.parens[i];
            if (subString != null) {
                return subString;
            }
        }
        return SubString.emptySubString;
    }

    private static void match_glob(GlobData globData, Context context, Scriptable scriptable, int i, RegExpImpl regExpImpl) {
        if (globData.arrayobj == null) {
            globData.arrayobj = context.newArray(scriptable, 0);
        }
        globData.arrayobj.put(i, globData.arrayobj, regExpImpl.lastMatch.toString());
    }

    private static void replace_glob(GlobData globData, Context context, Scriptable scriptable, RegExpImpl regExpImpl, int i, int i2) {
        String scriptRuntime;
        int length;
        int i3 = 0;
        int length2;
        if (globData.lambda != null) {
            SubString[] subStringArr = regExpImpl.parens;
            length2 = subStringArr == null ? 0 : subStringArr.length;
            Object[] objArr = new Object[(length2 + 3)];
            objArr[0] = regExpImpl.lastMatch.toString();
            while (i3 < length2) {
                SubString subString = subStringArr[i3];
                if (subString != null) {
                    objArr[i3 + 1] = subString.toString();
                } else {
                    objArr[i3 + 1] = Undefined.instance;
                }
                i3++;
            }
            objArr[length2 + 1] = Integer.valueOf(regExpImpl.leftContext.length);
            objArr[length2 + 2] = globData.str;
            if (regExpImpl != ScriptRuntime.getRegExpProxy(context)) {
                Kit.codeBug();
            }
            RegExpProxy regExpImpl2 = new RegExpImpl();
            regExpImpl2.multiline = regExpImpl.multiline;
            regExpImpl2.input = regExpImpl.input;
            ScriptRuntime.setRegExpProxy(context, regExpImpl2);
            try {
                Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
                scriptRuntime = ScriptRuntime.toString(globData.lambda.call(context, topLevelScope, topLevelScope, objArr));
                length = scriptRuntime.length();
            } finally {
                ScriptRuntime.setRegExpProxy(context, regExpImpl);
            }
        } else {
            length = globData.repstr.length();
            if (globData.dollar >= 0) {
                int[] iArr = new int[1];
                length2 = globData.dollar;
                while (true) {
                    SubString interpretDollar = interpretDollar(context, regExpImpl, globData.repstr, length2, iArr);
                    if (interpretDollar != null) {
                        length += interpretDollar.length - iArr[0];
                        length2 += iArr[0];
                    } else {
                        length2++;
                    }
                    length2 = globData.repstr.indexOf(36, length2);
                    if (length2 < 0) {
                        break;
                    }
                }
            }
            scriptRuntime = null;
        }
        length = regExpImpl.rightContext.length + (i2 + length);
        StringBuilder stringBuilder = globData.charBuf;
        if (stringBuilder == null) {
            stringBuilder = new StringBuilder(length);
            globData.charBuf = stringBuilder;
        } else {
            stringBuilder.ensureCapacity(length + globData.charBuf.length());
        }
        stringBuilder.append(regExpImpl.leftContext.str, i, i + i2);
        if (globData.lambda != null) {
            stringBuilder.append(scriptRuntime);
        } else {
            do_replace(globData, context, regExpImpl);
        }
    }

    private static SubString interpretDollar(Context context, RegExpImpl regExpImpl, String str, int i, int[] iArr) {
        if (str.charAt(i) != '$') {
            Kit.codeBug();
        }
        int languageVersion = context.getLanguageVersion();
        if (languageVersion != 0 && languageVersion <= 140 && i > 0 && str.charAt(i - 1) == '\\') {
            return null;
        }
        int length = str.length();
        if (i + 1 >= length) {
            return null;
        }
        char charAt = str.charAt(i + 1);
        if (NativeRegExp.isDigit(charAt)) {
            int i2;
            if (languageVersion != 0 && languageVersion <= 140) {
                if (charAt != '0') {
                    languageVersion = i;
                    i2 = 0;
                    while (true) {
                        languageVersion++;
                        if (languageVersion >= length) {
                            break;
                        }
                        char charAt2 = str.charAt(languageVersion);
                        if (!NativeRegExp.isDigit(charAt2)) {
                            break;
                        }
                        int i3 = (charAt2 - 48) + (i2 * 10);
                        if (i3 < i2) {
                            break;
                        }
                        i2 = i3;
                    }
                } else {
                    return null;
                }
            }
            int length2 = regExpImpl.parens == null ? 0 : regExpImpl.parens.length;
            int i4 = charAt - 48;
            if (i4 > length2) {
                return null;
            }
            languageVersion = i + 2;
            if (i + 2 < length) {
                charAt = str.charAt(i + 2);
                if (NativeRegExp.isDigit(charAt)) {
                    i2 = (charAt - 48) + (i4 * 10);
                    if (i2 <= length2) {
                        languageVersion++;
                        if (i2 == 0) {
                            return null;
                        }
                    }
                }
            }
            i2 = i4;
            if (i2 == 0) {
                return null;
            }
            i2--;
            iArr[0] = languageVersion - i;
            return regExpImpl.getParenSubString(i2);
        }
        iArr[0] = 2;
        switch (charAt) {
            case '$':
                return new SubString("$");
            case '&':
                return regExpImpl.lastMatch;
            case '\'':
                return regExpImpl.rightContext;
            case '+':
                return regExpImpl.lastParen;
            case '`':
                if (languageVersion == 120) {
                    regExpImpl.leftContext.index = 0;
                    regExpImpl.leftContext.length = regExpImpl.lastMatch.index;
                }
                return regExpImpl.leftContext;
            default:
                return null;
        }
    }

    private static void do_replace(GlobData globData, Context context, RegExpImpl regExpImpl) {
        int i;
        StringBuilder stringBuilder = globData.charBuf;
        String str = globData.repstr;
        int i2 = globData.dollar;
        if (i2 != -1) {
            int[] iArr = new int[1];
            i = 0;
            do {
                int i3 = i2 - i;
                stringBuilder.append(str.substring(i, i2));
                SubString interpretDollar = interpretDollar(context, regExpImpl, str, i2, iArr);
                if (interpretDollar != null) {
                    i3 = interpretDollar.length;
                    if (i3 > 0) {
                        stringBuilder.append(interpretDollar.str, interpretDollar.index, interpretDollar.index + i3);
                    }
                    i = iArr[0] + i2;
                    i2 += iArr[0];
                } else {
                    i = i2;
                    i2++;
                }
                i2 = str.indexOf(36, i2);
            } while (i2 >= 0);
        } else {
            i = 0;
        }
        i2 = str.length();
        if (i2 > i) {
            stringBuilder.append(str.substring(i, i2));
        }
    }

    public Object js_split(Context context, Scriptable scriptable, String str, Object[] objArr) {
        Object obj;
        long length;
        Scriptable newArray = context.newArray(scriptable, 0);
        if (objArr.length <= 1 || objArr[1] == Undefined.instance) {
            obj = null;
        } else {
            obj = 1;
        }
        long j = 0;
        if (obj != null) {
            j = ScriptRuntime.toUint32(objArr[1]);
            if (j > ((long) str.length())) {
                length = (long) (str.length() + 1);
                if (objArr.length >= 1 || objArr[0] == Undefined.instance) {
                    newArray.put(0, newArray, (Object) str);
                    return newArray;
                }
                String str2 = null;
                int[] iArr = new int[1];
                Scriptable scriptable2 = null;
                RegExpProxy regExpProxy = null;
                if (objArr[0] instanceof Scriptable) {
                    regExpProxy = ScriptRuntime.getRegExpProxy(context);
                    if (regExpProxy != null) {
                        Scriptable scriptable3 = (Scriptable) objArr[0];
                        if (regExpProxy.isRegExp(scriptable3)) {
                            scriptable2 = scriptable3;
                        }
                    }
                }
                if (scriptable2 == null) {
                    str2 = ScriptRuntime.toString(objArr[0]);
                    iArr[0] = str2.length();
                }
                int[] iArr2 = new int[]{0};
                boolean[] zArr = new boolean[]{false};
                String[][] strArr = new String[][]{null};
                int languageVersion = context.getLanguageVersion();
                int i = 0;
                while (true) {
                    int find_split = find_split(context, scriptable, str, str2, languageVersion, regExpProxy, scriptable2, iArr2, iArr, zArr, strArr);
                    if (find_split < 0 || ((obj != null && ((long) i) >= r16) || find_split > str.length())) {
                        break;
                    }
                    Object obj2;
                    if (str.length() == 0) {
                        obj2 = str;
                    } else {
                        obj2 = str.substring(iArr2[0], find_split);
                    }
                    newArray.put(i, newArray, obj2);
                    int i2 = i + 1;
                    if (scriptable2 != null && zArr[0]) {
                        i = strArr[0].length;
                        int i3 = 0;
                        while (i3 < i && (obj == null || ((long) i2) < r16)) {
                            newArray.put(i2, newArray, strArr[0][i3]);
                            i3++;
                            i2++;
                        }
                        zArr[0] = false;
                    }
                    iArr2[0] = iArr[0] + find_split;
                    if (languageVersion < 130 && languageVersion != 0 && obj == null && iArr2[0] == str.length()) {
                        break;
                    }
                    i = i2;
                }
                return newArray;
            }
        }
        length = j;
        if (objArr.length >= 1) {
        }
        newArray.put(0, newArray, (Object) str);
        return newArray;
    }

    private static int find_split(Context context, Scriptable scriptable, String str, String str2, int i, RegExpProxy regExpProxy, Scriptable scriptable2, int[] iArr, int[] iArr2, boolean[] zArr, String[][] strArr) {
        int i2 = iArr[0];
        int length = str.length();
        if (i == 120 && scriptable2 == null && str2.length() == 1 && str2.charAt(0) == ' ') {
            if (i2 == 0) {
                while (i2 < length && Character.isWhitespace(str.charAt(i2))) {
                    i2++;
                }
                iArr[0] = i2;
            }
            if (i2 == length) {
                return -1;
            }
            while (i2 < length && !Character.isWhitespace(str.charAt(i2))) {
                i2++;
            }
            int i3 = i2;
            while (i3 < length && Character.isWhitespace(str.charAt(i3))) {
                i3++;
            }
            iArr2[0] = i3 - i2;
            return i2;
        } else if (i2 > length) {
            return -1;
        } else {
            if (scriptable2 != null) {
                return regExpProxy.find_split(context, scriptable, str, str2, scriptable2, iArr, iArr2, zArr, strArr);
            }
            if (i != 0 && i < 130 && length == 0) {
                return -1;
            }
            if (str2.length() == 0) {
                if (i != 120) {
                    return i2 == length ? -1 : i2 + 1;
                } else {
                    if (i2 != length) {
                        return i2 + 1;
                    }
                    iArr2[0] = 1;
                    return i2;
                }
            } else if (iArr[0] >= length) {
                return length;
            } else {
                i2 = str.indexOf(str2, iArr[0]);
                return i2 == -1 ? length : i2;
            }
        }
    }
}
