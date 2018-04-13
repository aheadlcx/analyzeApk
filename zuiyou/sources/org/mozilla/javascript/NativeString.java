package org.mozilla.javascript;

import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.text.Collator;

final class NativeString extends IdScriptableObject {
    private static final int ConstructorId_charAt = -5;
    private static final int ConstructorId_charCodeAt = -6;
    private static final int ConstructorId_concat = -14;
    private static final int ConstructorId_equalsIgnoreCase = -30;
    private static final int ConstructorId_fromCharCode = -1;
    private static final int ConstructorId_indexOf = -7;
    private static final int ConstructorId_lastIndexOf = -8;
    private static final int ConstructorId_localeCompare = -34;
    private static final int ConstructorId_match = -31;
    private static final int ConstructorId_replace = -33;
    private static final int ConstructorId_search = -32;
    private static final int ConstructorId_slice = -15;
    private static final int ConstructorId_split = -9;
    private static final int ConstructorId_substr = -13;
    private static final int ConstructorId_substring = -10;
    private static final int ConstructorId_toLocaleLowerCase = -35;
    private static final int ConstructorId_toLowerCase = -11;
    private static final int ConstructorId_toUpperCase = -12;
    private static final int Id_anchor = 28;
    private static final int Id_big = 21;
    private static final int Id_blink = 22;
    private static final int Id_bold = 16;
    private static final int Id_charAt = 5;
    private static final int Id_charCodeAt = 6;
    private static final int Id_concat = 14;
    private static final int Id_constructor = 1;
    private static final int Id_equals = 29;
    private static final int Id_equalsIgnoreCase = 30;
    private static final int Id_fixed = 18;
    private static final int Id_fontcolor = 26;
    private static final int Id_fontsize = 25;
    private static final int Id_indexOf = 7;
    private static final int Id_italics = 17;
    private static final int Id_lastIndexOf = 8;
    private static final int Id_length = 1;
    private static final int Id_link = 27;
    private static final int Id_localeCompare = 34;
    private static final int Id_match = 31;
    private static final int Id_replace = 33;
    private static final int Id_search = 32;
    private static final int Id_slice = 15;
    private static final int Id_small = 20;
    private static final int Id_split = 9;
    private static final int Id_strike = 19;
    private static final int Id_sub = 24;
    private static final int Id_substr = 13;
    private static final int Id_substring = 10;
    private static final int Id_sup = 23;
    private static final int Id_toLocaleLowerCase = 35;
    private static final int Id_toLocaleUpperCase = 36;
    private static final int Id_toLowerCase = 11;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    private static final int Id_toUpperCase = 12;
    private static final int Id_trim = 37;
    private static final int Id_trimLeft = 38;
    private static final int Id_trimRight = 39;
    private static final int Id_valueOf = 4;
    private static final int MAX_INSTANCE_ID = 1;
    private static final int MAX_PROTOTYPE_ID = 39;
    private static final Object STRING_TAG = "String";
    static final long serialVersionUID = 920268368584188687L;
    private CharSequence string;

    static void init(Scriptable scriptable, boolean z) {
        new NativeString("").exportAsJSClass(39, scriptable, z);
    }

    NativeString(CharSequence charSequence) {
        this.string = charSequence;
    }

    public String getClassName() {
        return "String";
    }

    protected int getMaxInstanceId() {
        return 1;
    }

    protected int findInstanceIdInfo(String str) {
        if (str.equals("length")) {
            return IdScriptableObject.instanceIdInfo(7, 1);
        }
        return super.findInstanceIdInfo(str);
    }

    protected String getInstanceIdName(int i) {
        if (i == 1) {
            return "length";
        }
        return super.getInstanceIdName(i);
    }

    protected Object getInstanceIdValue(int i) {
        if (i == 1) {
            return ScriptRuntime.wrapInt(this.string.length());
        }
        return super.getInstanceIdValue(i);
    }

    protected void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -1, "fromCharCode", 1);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -5, "charAt", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -6, "charCodeAt", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -7, "indexOf", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -8, "lastIndexOf", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -9, "split", 3);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -10, "substring", 3);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -11, "toLowerCase", 1);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -12, "toUpperCase", 1);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -13, "substr", 3);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -14, "concat", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -15, "slice", 3);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, ConstructorId_equalsIgnoreCase, "equalsIgnoreCase", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, ConstructorId_match, "match", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, ConstructorId_search, "search", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, ConstructorId_replace, "replace", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, ConstructorId_localeCompare, "localeCompare", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, ConstructorId_toLocaleLowerCase, "toLocaleLowerCase", 1);
        super.fillConstructorProperties(idFunctionObject);
    }

    protected void initPrototypeId(int i) {
        String str;
        int i2 = 1;
        switch (i) {
            case 1:
                str = "constructor";
                break;
            case 2:
                str = "toString";
                i2 = 0;
                break;
            case 3:
                str = "toSource";
                i2 = 0;
                break;
            case 4:
                str = "valueOf";
                i2 = 0;
                break;
            case 5:
                str = "charAt";
                break;
            case 6:
                str = "charCodeAt";
                break;
            case 7:
                str = "indexOf";
                break;
            case 8:
                str = "lastIndexOf";
                break;
            case 9:
                str = "split";
                i2 = 2;
                break;
            case 10:
                str = "substring";
                i2 = 2;
                break;
            case 11:
                str = "toLowerCase";
                i2 = 0;
                break;
            case 12:
                str = "toUpperCase";
                i2 = 0;
                break;
            case 13:
                str = "substr";
                i2 = 2;
                break;
            case 14:
                str = "concat";
                break;
            case 15:
                str = "slice";
                i2 = 2;
                break;
            case 16:
                str = "bold";
                i2 = 0;
                break;
            case 17:
                str = "italics";
                i2 = 0;
                break;
            case 18:
                str = "fixed";
                i2 = 0;
                break;
            case 19:
                str = "strike";
                i2 = 0;
                break;
            case 20:
                str = "small";
                i2 = 0;
                break;
            case 21:
                str = "big";
                i2 = 0;
                break;
            case 22:
                str = "blink";
                i2 = 0;
                break;
            case 23:
                str = "sup";
                i2 = 0;
                break;
            case 24:
                str = "sub";
                i2 = 0;
                break;
            case 25:
                str = "fontsize";
                i2 = 0;
                break;
            case 26:
                str = "fontcolor";
                i2 = 0;
                break;
            case 27:
                str = "link";
                i2 = 0;
                break;
            case 28:
                str = "anchor";
                i2 = 0;
                break;
            case 29:
                str = "equals";
                break;
            case 30:
                str = "equalsIgnoreCase";
                break;
            case 31:
                str = "match";
                break;
            case 32:
                str = "search";
                break;
            case 33:
                str = "replace";
                i2 = 2;
                break;
            case 34:
                str = "localeCompare";
                break;
            case 35:
                str = "toLocaleLowerCase";
                i2 = 0;
                break;
            case 36:
                str = "toLocaleUpperCase";
                i2 = 0;
                break;
            case 37:
                str = "trim";
                i2 = 0;
                break;
            case 38:
                str = "trimLeft";
                i2 = 0;
                break;
            case 39:
                str = "trimRight";
                i2 = 0;
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(STRING_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(STRING_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        Object[] objArr2 = objArr;
        Scriptable scriptable3 = scriptable2;
        while (true) {
            int i;
            int length;
            Object toCharSequence;
            String scriptRuntime;
            char[] toCharArray;
            switch (methodId) {
                case ConstructorId_toLocaleLowerCase /*-35*/:
                case ConstructorId_localeCompare /*-34*/:
                case ConstructorId_replace /*-33*/:
                case ConstructorId_search /*-32*/:
                case ConstructorId_match /*-31*/:
                case ConstructorId_equalsIgnoreCase /*-30*/:
                case -15:
                case -14:
                case -13:
                case -12:
                case -11:
                case -10:
                case -9:
                case -8:
                case -7:
                case -6:
                case -5:
                    Scriptable scriptable4;
                    if (objArr2.length > 0) {
                        Scriptable toObject = ScriptRuntime.toObject(context, scriptable, ScriptRuntime.toCharSequence(objArr2[0]));
                        Object[] objArr3 = new Object[(objArr2.length - 1)];
                        for (i = 0; i < objArr3.length; i++) {
                            objArr3[i] = objArr2[i + 1];
                        }
                        objArr2 = objArr3;
                        scriptable4 = toObject;
                    } else {
                        scriptable4 = ScriptRuntime.toObject(context, scriptable, ScriptRuntime.toCharSequence(scriptable3));
                    }
                    methodId = -methodId;
                    scriptable3 = scriptable4;
                case -1:
                    length = objArr2.length;
                    if (length < 1) {
                        return "";
                    }
                    StringBuilder stringBuilder = new StringBuilder(length);
                    for (i = 0; i != length; i++) {
                        stringBuilder.append(ScriptRuntime.toUint16(objArr2[i]));
                    }
                    return stringBuilder.toString();
                case 1:
                    toCharSequence = objArr2.length >= 1 ? ScriptRuntime.toCharSequence(objArr2[0]) : "";
                    if (scriptable3 == null) {
                        return new NativeString(toCharSequence);
                    }
                    if (toCharSequence instanceof String) {
                        return toCharSequence;
                    }
                    return toCharSequence.toString();
                case 2:
                case 4:
                    toCharSequence = realThis(scriptable3, idFunctionObject).string;
                    if (toCharSequence instanceof String) {
                        return toCharSequence;
                    }
                    return toCharSequence.toString();
                case 3:
                    return "(new String(\"" + ScriptRuntime.escapeString(realThis(scriptable3, idFunctionObject).string.toString()) + "\"))";
                case 5:
                case 6:
                    CharSequence toCharSequence2 = ScriptRuntime.toCharSequence(scriptable3);
                    double toInteger = ScriptRuntime.toInteger(objArr2, 0);
                    if (toInteger >= 0.0d && toInteger < ((double) toCharSequence2.length())) {
                        char charAt = toCharSequence2.charAt((int) toInteger);
                        if (methodId == 5) {
                            return String.valueOf(charAt);
                        }
                        return ScriptRuntime.wrapInt(charAt);
                    } else if (methodId == 5) {
                        return "";
                    } else {
                        return ScriptRuntime.NaNobj;
                    }
                case 7:
                    return ScriptRuntime.wrapInt(js_indexOf(ScriptRuntime.toString(scriptable3), objArr2));
                case 8:
                    return ScriptRuntime.wrapInt(js_lastIndexOf(ScriptRuntime.toString(scriptable3), objArr2));
                case 9:
                    return ScriptRuntime.checkRegExpProxy(context).js_split(context, scriptable, ScriptRuntime.toString(scriptable3), objArr2);
                case 10:
                    return js_substring(context, ScriptRuntime.toCharSequence(scriptable3), objArr2);
                case 11:
                    return ScriptRuntime.toString(scriptable3).toLowerCase(ScriptRuntime.ROOT_LOCALE);
                case 12:
                    return ScriptRuntime.toString(scriptable3).toUpperCase(ScriptRuntime.ROOT_LOCALE);
                case 13:
                    return js_substr(ScriptRuntime.toCharSequence(scriptable3), objArr2);
                case 14:
                    return js_concat(ScriptRuntime.toString(scriptable3), objArr2);
                case 15:
                    return js_slice(ScriptRuntime.toCharSequence(scriptable3), objArr2);
                case 16:
                    return tagify(scriptable3, "b", null, null);
                case 17:
                    return tagify(scriptable3, "i", null, null);
                case 18:
                    return tagify(scriptable3, PushConstants.PUSH_NOTIFICATION_CREATE_TIMES_TAMP, null, null);
                case 19:
                    return tagify(scriptable3, "strike", null, null);
                case 20:
                    return tagify(scriptable3, "small", null, null);
                case 21:
                    return tagify(scriptable3, "big", null, null);
                case 22:
                    return tagify(scriptable3, "blink", null, null);
                case 23:
                    return tagify(scriptable3, "sup", null, null);
                case 24:
                    return tagify(scriptable3, "sub", null, null);
                case 25:
                    return tagify(scriptable3, "font", "size", objArr2);
                case 26:
                    return tagify(scriptable3, "font", "color", objArr2);
                case 27:
                    return tagify(scriptable3, "a", "href", objArr2);
                case 28:
                    return tagify(scriptable3, "a", "name", objArr2);
                case 29:
                case 30:
                    String scriptRuntime2 = ScriptRuntime.toString(scriptable3);
                    scriptRuntime = ScriptRuntime.toString(objArr2, 0);
                    return ScriptRuntime.wrapBoolean(methodId == 29 ? scriptRuntime2.equals(scriptRuntime) : scriptRuntime2.equalsIgnoreCase(scriptRuntime));
                case 31:
                case 32:
                case 33:
                    if (methodId == 31) {
                        methodId = 1;
                    } else if (methodId == 32) {
                        methodId = 3;
                    } else {
                        methodId = 2;
                    }
                    return ScriptRuntime.checkRegExpProxy(context).action(context, scriptable, scriptable3, objArr2, methodId);
                case 34:
                    Collator instance = Collator.getInstance(context.getLocale());
                    instance.setStrength(3);
                    instance.setDecomposition(1);
                    return ScriptRuntime.wrapNumber((double) instance.compare(ScriptRuntime.toString(scriptable3), ScriptRuntime.toString(objArr2, 0)));
                case 35:
                    return ScriptRuntime.toString(scriptable3).toLowerCase(context.getLocale());
                case 36:
                    return ScriptRuntime.toString(scriptable3).toUpperCase(context.getLocale());
                case 37:
                    String scriptRuntime3 = ScriptRuntime.toString(scriptable3);
                    char[] toCharArray2 = scriptRuntime3.toCharArray();
                    i = 0;
                    while (i < toCharArray2.length && ScriptRuntime.isJSWhitespaceOrLineTerminator(toCharArray2[i])) {
                        i++;
                    }
                    length = toCharArray2.length;
                    while (length > i && ScriptRuntime.isJSWhitespaceOrLineTerminator(toCharArray2[length - 1])) {
                        length--;
                    }
                    return scriptRuntime3.substring(i, length);
                case 38:
                    scriptRuntime = ScriptRuntime.toString(scriptable3);
                    toCharArray = scriptRuntime.toCharArray();
                    i = 0;
                    while (i < toCharArray.length && ScriptRuntime.isJSWhitespaceOrLineTerminator(toCharArray[i])) {
                        i++;
                    }
                    return scriptRuntime.substring(i, toCharArray.length);
                case 39:
                    scriptRuntime = ScriptRuntime.toString(scriptable3);
                    toCharArray = scriptRuntime.toCharArray();
                    i = toCharArray.length;
                    while (i > 0 && ScriptRuntime.isJSWhitespaceOrLineTerminator(toCharArray[i - 1])) {
                        i--;
                    }
                    return scriptRuntime.substring(0, i);
                default:
                    throw new IllegalArgumentException(String.valueOf(methodId));
            }
        }
    }

    private static NativeString realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (scriptable instanceof NativeString) {
            return (NativeString) scriptable;
        }
        throw IdScriptableObject.incompatibleCallError(idFunctionObject);
    }

    private static String tagify(Object obj, String str, String str2, Object[] objArr) {
        String scriptRuntime = ScriptRuntime.toString(obj);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('<');
        stringBuilder.append(str);
        if (str2 != null) {
            stringBuilder.append(' ');
            stringBuilder.append(str2);
            stringBuilder.append("=\"");
            stringBuilder.append(ScriptRuntime.toString(objArr, 0));
            stringBuilder.append('\"');
        }
        stringBuilder.append('>');
        stringBuilder.append(scriptRuntime);
        stringBuilder.append("</");
        stringBuilder.append(str);
        stringBuilder.append('>');
        return stringBuilder.toString();
    }

    public CharSequence toCharSequence() {
        return this.string;
    }

    public String toString() {
        return this.string instanceof String ? (String) this.string : this.string.toString();
    }

    public Object get(int i, Scriptable scriptable) {
        if (i < 0 || i >= this.string.length()) {
            return super.get(i, scriptable);
        }
        return String.valueOf(this.string.charAt(i));
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        if (i < 0 || i >= this.string.length()) {
            super.put(i, scriptable, obj);
        }
    }

    private static int js_indexOf(String str, Object[] objArr) {
        double d = 0.0d;
        String scriptRuntime = ScriptRuntime.toString(objArr, 0);
        double toInteger = ScriptRuntime.toInteger(objArr, 1);
        if (toInteger > ((double) str.length())) {
            return -1;
        }
        if (toInteger >= 0.0d) {
            d = toInteger;
        }
        return str.indexOf(scriptRuntime, (int) d);
    }

    private static int js_lastIndexOf(String str, Object[] objArr) {
        double d = 0.0d;
        String scriptRuntime = ScriptRuntime.toString(objArr, 0);
        double toNumber = ScriptRuntime.toNumber(objArr, 1);
        if (toNumber != toNumber || toNumber > ((double) str.length())) {
            d = (double) str.length();
        } else if (toNumber >= 0.0d) {
            d = toNumber;
        }
        return str.lastIndexOf(scriptRuntime, (int) d);
    }

    private static CharSequence js_substring(Context context, CharSequence charSequence, Object[] objArr) {
        double d = 0.0d;
        int length = charSequence.length();
        double toInteger = ScriptRuntime.toInteger(objArr, 0);
        if (toInteger < 0.0d) {
            toInteger = 0.0d;
        } else if (toInteger > ((double) length)) {
            toInteger = (double) length;
        }
        if (objArr.length <= 1 || objArr[1] == Undefined.instance) {
            d = toInteger;
            toInteger = (double) length;
        } else {
            double toInteger2 = ScriptRuntime.toInteger(objArr[1]);
            if (toInteger2 >= 0.0d) {
                d = toInteger2 > ((double) length) ? (double) length : toInteger2;
            }
            if (d >= toInteger) {
                double d2 = d;
                d = toInteger;
                toInteger = d2;
            } else if (context.getLanguageVersion() == 120) {
                d = toInteger;
            }
        }
        return charSequence.subSequence((int) d, (int) toInteger);
    }

    int getLength() {
        return this.string.length();
    }

    private static CharSequence js_substr(CharSequence charSequence, Object[] objArr) {
        double d = 0.0d;
        if (objArr.length < 1) {
            return charSequence;
        }
        double toInteger = ScriptRuntime.toInteger(objArr[0]);
        int length = charSequence.length();
        if (toInteger < 0.0d) {
            toInteger += (double) length;
            if (toInteger < 0.0d) {
                toInteger = 0.0d;
            }
        } else if (toInteger > ((double) length)) {
            toInteger = (double) length;
        }
        if (objArr.length == 1) {
            d = (double) length;
        } else {
            double toInteger2 = ScriptRuntime.toInteger(objArr[1]);
            if (toInteger2 >= 0.0d) {
                d = toInteger2;
            }
            d += toInteger;
            if (d > ((double) length)) {
                d = (double) length;
            }
        }
        return charSequence.subSequence((int) toInteger, (int) d);
    }

    private static String js_concat(String str, Object[] objArr) {
        int i = 0;
        int length = objArr.length;
        if (length == 0) {
            return str;
        }
        if (length == 1) {
            return str.concat(ScriptRuntime.toString(objArr[0]));
        }
        String[] strArr = new String[length];
        int length2 = str.length();
        for (int i2 = 0; i2 != length; i2++) {
            String scriptRuntime = ScriptRuntime.toString(objArr[i2]);
            strArr[i2] = scriptRuntime;
            length2 += scriptRuntime.length();
        }
        StringBuilder stringBuilder = new StringBuilder(length2);
        stringBuilder.append(str);
        while (i != length) {
            stringBuilder.append(strArr[i]);
            i++;
        }
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.CharSequence js_slice(java.lang.CharSequence r8, java.lang.Object[] r9) {
        /*
        r7 = 1;
        r2 = 0;
        r0 = r9.length;
        if (r0 >= r7) goto L_0x0028;
    L_0x0006:
        r0 = r2;
    L_0x0007:
        r6 = r8.length();
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 >= 0) goto L_0x0030;
    L_0x000f:
        r4 = (double) r6;
        r0 = r0 + r4;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 >= 0) goto L_0x0016;
    L_0x0015:
        r0 = r2;
    L_0x0016:
        r4 = r9.length;
        r5 = 2;
        if (r4 < r5) goto L_0x0020;
    L_0x001a:
        r4 = r9[r7];
        r5 = org.mozilla.javascript.Undefined.instance;
        if (r4 != r5) goto L_0x0037;
    L_0x0020:
        r2 = (double) r6;
    L_0x0021:
        r0 = (int) r0;
        r1 = (int) r2;
        r0 = r8.subSequence(r0, r1);
        return r0;
    L_0x0028:
        r0 = 0;
        r0 = r9[r0];
        r0 = org.mozilla.javascript.ScriptRuntime.toInteger(r0);
        goto L_0x0007;
    L_0x0030:
        r4 = (double) r6;
        r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x0016;
    L_0x0035:
        r0 = (double) r6;
        goto L_0x0016;
    L_0x0037:
        r4 = r9[r7];
        r4 = org.mozilla.javascript.ScriptRuntime.toInteger(r4);
        r7 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r7 >= 0) goto L_0x004d;
    L_0x0041:
        r6 = (double) r6;
        r4 = r4 + r6;
        r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r6 >= 0) goto L_0x0054;
    L_0x0047:
        r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1));
        if (r4 >= 0) goto L_0x0021;
    L_0x004b:
        r2 = r0;
        goto L_0x0021;
    L_0x004d:
        r2 = (double) r6;
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x0054;
    L_0x0052:
        r2 = (double) r6;
        goto L_0x0047;
    L_0x0054:
        r2 = r4;
        goto L_0x0047;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeString.js_slice(java.lang.CharSequence, java.lang.Object[]):java.lang.CharSequence");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int findPrototypeId(java.lang.String r9) {
        /*
        r8 = this;
        r6 = 98;
        r2 = 4;
        r3 = 2;
        r4 = 1;
        r0 = 0;
        r1 = 0;
        r5 = r9.length();
        switch(r5) {
            case 3: goto L_0x001b;
            case 4: goto L_0x0060;
            case 5: goto L_0x0089;
            case 6: goto L_0x00d0;
            case 7: goto L_0x0120;
            case 8: goto L_0x0150;
            case 9: goto L_0x017f;
            case 10: goto L_0x01ad;
            case 11: goto L_0x01b6;
            case 12: goto L_0x000e;
            case 13: goto L_0x01e6;
            case 14: goto L_0x000e;
            case 15: goto L_0x000e;
            case 16: goto L_0x01f0;
            case 17: goto L_0x01fa;
            default: goto L_0x000e;
        };
    L_0x000e:
        r2 = r1;
        r1 = r0;
    L_0x0010:
        if (r2 == 0) goto L_0x021c;
    L_0x0012:
        if (r2 == r9) goto L_0x021c;
    L_0x0014:
        r2 = r2.equals(r9);
        if (r2 != 0) goto L_0x021c;
    L_0x001a:
        return r0;
    L_0x001b:
        r2 = r9.charAt(r3);
        if (r2 != r6) goto L_0x0034;
    L_0x0021:
        r2 = r9.charAt(r0);
        r3 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r2 != r3) goto L_0x000e;
    L_0x0029:
        r2 = r9.charAt(r4);
        r3 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        if (r2 != r3) goto L_0x000e;
    L_0x0031:
        r0 = 24;
        goto L_0x001a;
    L_0x0034:
        r3 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        if (r2 != r3) goto L_0x0049;
    L_0x0038:
        r2 = r9.charAt(r0);
        if (r2 != r6) goto L_0x000e;
    L_0x003e:
        r2 = r9.charAt(r4);
        r3 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        if (r2 != r3) goto L_0x000e;
    L_0x0046:
        r0 = 21;
        goto L_0x001a;
    L_0x0049:
        r3 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        if (r2 != r3) goto L_0x000e;
    L_0x004d:
        r2 = r9.charAt(r0);
        r3 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r2 != r3) goto L_0x000e;
    L_0x0055:
        r2 = r9.charAt(r4);
        r3 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        if (r2 != r3) goto L_0x000e;
    L_0x005d:
        r0 = 23;
        goto L_0x001a;
    L_0x0060:
        r2 = r9.charAt(r0);
        if (r2 != r6) goto L_0x006f;
    L_0x0066:
        r1 = "bold";
        r2 = 16;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x006f:
        r3 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        if (r2 != r3) goto L_0x007c;
    L_0x0073:
        r1 = "link";
        r2 = 27;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x007c:
        r3 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        if (r2 != r3) goto L_0x000e;
    L_0x0080:
        r1 = "trim";
        r2 = 37;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0089:
        r2 = r9.charAt(r2);
        switch(r2) {
            case 100: goto L_0x0094;
            case 101: goto L_0x009e;
            case 104: goto L_0x00a8;
            case 107: goto L_0x00b2;
            case 108: goto L_0x00bc;
            case 116: goto L_0x00c6;
            default: goto L_0x0090;
        };
    L_0x0090:
        r2 = r1;
        r1 = r0;
        goto L_0x0010;
    L_0x0094:
        r1 = "fixed";
        r2 = 18;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x009e:
        r1 = "slice";
        r2 = 15;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x00a8:
        r1 = "match";
        r2 = 31;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x00b2:
        r1 = "blink";
        r2 = 22;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x00bc:
        r1 = "small";
        r2 = 20;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x00c6:
        r1 = "split";
        r2 = 9;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x00d0:
        r2 = r9.charAt(r4);
        switch(r2) {
            case 101: goto L_0x00db;
            case 104: goto L_0x00e5;
            case 110: goto L_0x00ee;
            case 111: goto L_0x00f8;
            case 113: goto L_0x0102;
            case 116: goto L_0x010c;
            case 117: goto L_0x0116;
            default: goto L_0x00d7;
        };
    L_0x00d7:
        r2 = r1;
        r1 = r0;
        goto L_0x0010;
    L_0x00db:
        r1 = "search";
        r2 = 32;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x00e5:
        r1 = "charAt";
        r2 = 5;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x00ee:
        r1 = "anchor";
        r2 = 28;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x00f8:
        r1 = "concat";
        r2 = 14;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0102:
        r1 = "equals";
        r2 = 29;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x010c:
        r1 = "strike";
        r2 = 19;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0116:
        r1 = "substr";
        r2 = 13;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0120:
        r3 = r9.charAt(r4);
        switch(r3) {
            case 97: goto L_0x012b;
            case 101: goto L_0x0133;
            case 110: goto L_0x013d;
            case 116: goto L_0x0146;
            default: goto L_0x0127;
        };
    L_0x0127:
        r2 = r1;
        r1 = r0;
        goto L_0x0010;
    L_0x012b:
        r1 = "valueOf";
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0133:
        r1 = "replace";
        r2 = 33;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x013d:
        r1 = "indexOf";
        r2 = 7;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0146:
        r1 = "italics";
        r2 = 17;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0150:
        r2 = r9.charAt(r2);
        switch(r2) {
            case 76: goto L_0x015b;
            case 114: goto L_0x0165;
            case 115: goto L_0x016c;
            case 117: goto L_0x0176;
            default: goto L_0x0157;
        };
    L_0x0157:
        r2 = r1;
        r1 = r0;
        goto L_0x0010;
    L_0x015b:
        r1 = "trimLeft";
        r2 = 38;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0165:
        r1 = "toString";
        r2 = r1;
        r1 = r3;
        goto L_0x0010;
    L_0x016c:
        r1 = "fontsize";
        r2 = 25;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0176:
        r1 = "toSource";
        r2 = 3;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x017f:
        r2 = r9.charAt(r0);
        r3 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r2 != r3) goto L_0x0191;
    L_0x0187:
        r1 = "fontcolor";
        r2 = 26;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0191:
        r3 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r2 != r3) goto L_0x019f;
    L_0x0195:
        r1 = "substring";
        r2 = 10;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x019f:
        r3 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        if (r2 != r3) goto L_0x000e;
    L_0x01a3:
        r1 = "trimRight";
        r2 = 39;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x01ad:
        r1 = "charCodeAt";
        r2 = 6;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x01b6:
        r2 = r9.charAt(r3);
        switch(r2) {
            case 76: goto L_0x01c1;
            case 85: goto L_0x01cb;
            case 110: goto L_0x01d5;
            case 115: goto L_0x01dc;
            default: goto L_0x01bd;
        };
    L_0x01bd:
        r2 = r1;
        r1 = r0;
        goto L_0x0010;
    L_0x01c1:
        r1 = "toLowerCase";
        r2 = 11;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x01cb:
        r1 = "toUpperCase";
        r2 = 12;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x01d5:
        r1 = "constructor";
        r2 = r1;
        r1 = r4;
        goto L_0x0010;
    L_0x01dc:
        r1 = "lastIndexOf";
        r2 = 8;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x01e6:
        r1 = "localeCompare";
        r2 = 34;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x01f0:
        r1 = "equalsIgnoreCase";
        r2 = 30;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x01fa:
        r2 = 8;
        r2 = r9.charAt(r2);
        r3 = 76;
        if (r2 != r3) goto L_0x020e;
    L_0x0204:
        r1 = "toLocaleLowerCase";
        r2 = 35;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x020e:
        r3 = 85;
        if (r2 != r3) goto L_0x000e;
    L_0x0212:
        r1 = "toLocaleUpperCase";
        r2 = 36;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x021c:
        r0 = r1;
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeString.findPrototypeId(java.lang.String):int");
    }
}
