package org.mozilla.javascript.regexp;

import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.TopLevel.Builtins;
import org.mozilla.javascript.Undefined;

class NativeRegExpCtor extends BaseFunction {
    private static final int DOLLAR_ID_BASE = 12;
    private static final int Id_AMPERSAND = 6;
    private static final int Id_BACK_QUOTE = 10;
    private static final int Id_DOLLAR_1 = 13;
    private static final int Id_DOLLAR_2 = 14;
    private static final int Id_DOLLAR_3 = 15;
    private static final int Id_DOLLAR_4 = 16;
    private static final int Id_DOLLAR_5 = 17;
    private static final int Id_DOLLAR_6 = 18;
    private static final int Id_DOLLAR_7 = 19;
    private static final int Id_DOLLAR_8 = 20;
    private static final int Id_DOLLAR_9 = 21;
    private static final int Id_PLUS = 8;
    private static final int Id_QUOTE = 12;
    private static final int Id_STAR = 2;
    private static final int Id_UNDERSCORE = 4;
    private static final int Id_input = 3;
    private static final int Id_lastMatch = 5;
    private static final int Id_lastParen = 7;
    private static final int Id_leftContext = 9;
    private static final int Id_multiline = 1;
    private static final int Id_rightContext = 11;
    private static final int MAX_INSTANCE_ID = 21;
    static final long serialVersionUID = -5733330028285400526L;
    private int inputAttr = 4;
    private int multilineAttr = 4;
    private int starAttr = 4;
    private int underscoreAttr = 4;

    NativeRegExpCtor() {
    }

    public String getFunctionName() {
        return "RegExp";
    }

    public int getLength() {
        return 2;
    }

    public int getArity() {
        return 2;
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (objArr.length > 0 && (objArr[0] instanceof NativeRegExp) && (objArr.length == 1 || objArr[1] == Undefined.instance)) {
            return objArr[0];
        }
        return construct(context, scriptable, objArr);
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        Scriptable nativeRegExp = new NativeRegExp();
        nativeRegExp.compile(context, scriptable, objArr);
        ScriptRuntime.setBuiltinProtoAndParent(nativeRegExp, scriptable, Builtins.RegExp);
        return nativeRegExp;
    }

    private static RegExpImpl getImpl() {
        return (RegExpImpl) ScriptRuntime.getRegExpProxy(Context.getCurrentContext());
    }

    protected int getMaxInstanceId() {
        return super.getMaxInstanceId() + 21;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int findInstanceIdInfo(java.lang.String r9) {
        /*
        r8 = this;
        r0 = 5;
        r1 = 4;
        r4 = 1;
        r6 = 36;
        r2 = 0;
        r3 = 0;
        r5 = r9.length();
        switch(r5) {
            case 2: goto L_0x0021;
            case 3: goto L_0x000e;
            case 4: goto L_0x000e;
            case 5: goto L_0x00b0;
            case 6: goto L_0x000e;
            case 7: goto L_0x000e;
            case 8: goto L_0x000e;
            case 9: goto L_0x00b9;
            case 10: goto L_0x000e;
            case 11: goto L_0x00e0;
            case 12: goto L_0x00ea;
            default: goto L_0x000e;
        };
    L_0x000e:
        r1 = r2;
    L_0x000f:
        if (r3 == 0) goto L_0x001a;
    L_0x0011:
        if (r3 == r9) goto L_0x001a;
    L_0x0013:
        r3 = r3.equals(r9);
        if (r3 != 0) goto L_0x001a;
    L_0x0019:
        r1 = r2;
    L_0x001a:
        if (r1 != 0) goto L_0x00f4;
    L_0x001c:
        r0 = super.findInstanceIdInfo(r9);
    L_0x0020:
        return r0;
    L_0x0021:
        r4 = r9.charAt(r4);
        switch(r4) {
            case 38: goto L_0x002a;
            case 39: goto L_0x0032;
            case 42: goto L_0x003b;
            case 43: goto L_0x0043;
            case 49: goto L_0x004c;
            case 50: goto L_0x0055;
            case 51: goto L_0x005e;
            case 52: goto L_0x0067;
            case 53: goto L_0x0070;
            case 54: goto L_0x0079;
            case 55: goto L_0x0082;
            case 56: goto L_0x008b;
            case 57: goto L_0x0094;
            case 95: goto L_0x009e;
            case 96: goto L_0x00a6;
            default: goto L_0x0028;
        };
    L_0x0028:
        r1 = r2;
        goto L_0x000f;
    L_0x002a:
        r1 = r9.charAt(r2);
        if (r1 != r6) goto L_0x000e;
    L_0x0030:
        r1 = 6;
        goto L_0x001a;
    L_0x0032:
        r1 = r9.charAt(r2);
        if (r1 != r6) goto L_0x000e;
    L_0x0038:
        r1 = 12;
        goto L_0x001a;
    L_0x003b:
        r1 = r9.charAt(r2);
        if (r1 != r6) goto L_0x000e;
    L_0x0041:
        r1 = 2;
        goto L_0x001a;
    L_0x0043:
        r1 = r9.charAt(r2);
        if (r1 != r6) goto L_0x000e;
    L_0x0049:
        r1 = 8;
        goto L_0x001a;
    L_0x004c:
        r1 = r9.charAt(r2);
        if (r1 != r6) goto L_0x000e;
    L_0x0052:
        r1 = 13;
        goto L_0x001a;
    L_0x0055:
        r1 = r9.charAt(r2);
        if (r1 != r6) goto L_0x000e;
    L_0x005b:
        r1 = 14;
        goto L_0x001a;
    L_0x005e:
        r1 = r9.charAt(r2);
        if (r1 != r6) goto L_0x000e;
    L_0x0064:
        r1 = 15;
        goto L_0x001a;
    L_0x0067:
        r1 = r9.charAt(r2);
        if (r1 != r6) goto L_0x000e;
    L_0x006d:
        r1 = 16;
        goto L_0x001a;
    L_0x0070:
        r1 = r9.charAt(r2);
        if (r1 != r6) goto L_0x000e;
    L_0x0076:
        r1 = 17;
        goto L_0x001a;
    L_0x0079:
        r1 = r9.charAt(r2);
        if (r1 != r6) goto L_0x000e;
    L_0x007f:
        r1 = 18;
        goto L_0x001a;
    L_0x0082:
        r1 = r9.charAt(r2);
        if (r1 != r6) goto L_0x000e;
    L_0x0088:
        r1 = 19;
        goto L_0x001a;
    L_0x008b:
        r1 = r9.charAt(r2);
        if (r1 != r6) goto L_0x000e;
    L_0x0091:
        r1 = 20;
        goto L_0x001a;
    L_0x0094:
        r1 = r9.charAt(r2);
        if (r1 != r6) goto L_0x000e;
    L_0x009a:
        r1 = 21;
        goto L_0x001a;
    L_0x009e:
        r4 = r9.charAt(r2);
        if (r4 != r6) goto L_0x000e;
    L_0x00a4:
        goto L_0x001a;
    L_0x00a6:
        r1 = r9.charAt(r2);
        if (r1 != r6) goto L_0x000e;
    L_0x00ac:
        r1 = 10;
        goto L_0x001a;
    L_0x00b0:
        r1 = "input";
        r3 = 3;
        r7 = r1;
        r1 = r3;
        r3 = r7;
        goto L_0x000f;
    L_0x00b9:
        r1 = r9.charAt(r1);
        r5 = 77;
        if (r1 != r5) goto L_0x00c8;
    L_0x00c1:
        r1 = "lastMatch";
        r3 = r1;
        r1 = r0;
        goto L_0x000f;
    L_0x00c8:
        r5 = 80;
        if (r1 != r5) goto L_0x00d5;
    L_0x00cc:
        r1 = "lastParen";
        r3 = 7;
        r7 = r1;
        r1 = r3;
        r3 = r7;
        goto L_0x000f;
    L_0x00d5:
        r5 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        if (r1 != r5) goto L_0x000e;
    L_0x00d9:
        r1 = "multiline";
        r3 = r1;
        r1 = r4;
        goto L_0x000f;
    L_0x00e0:
        r1 = "leftContext";
        r3 = 9;
        r7 = r1;
        r1 = r3;
        r3 = r7;
        goto L_0x000f;
    L_0x00ea:
        r1 = "rightContext";
        r3 = 11;
        r7 = r1;
        r1 = r3;
        r3 = r7;
        goto L_0x000f;
    L_0x00f4:
        switch(r1) {
            case 1: goto L_0x0102;
            case 2: goto L_0x0105;
            case 3: goto L_0x0108;
            case 4: goto L_0x010b;
            default: goto L_0x00f7;
        };
    L_0x00f7:
        r2 = super.getMaxInstanceId();
        r1 = r1 + r2;
        r0 = org.mozilla.javascript.IdScriptableObject.instanceIdInfo(r0, r1);
        goto L_0x0020;
    L_0x0102:
        r0 = r8.multilineAttr;
        goto L_0x00f7;
    L_0x0105:
        r0 = r8.starAttr;
        goto L_0x00f7;
    L_0x0108:
        r0 = r8.inputAttr;
        goto L_0x00f7;
    L_0x010b:
        r0 = r8.underscoreAttr;
        goto L_0x00f7;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExpCtor.findInstanceIdInfo(java.lang.String):int");
    }

    protected String getInstanceIdName(int i) {
        int maxInstanceId = i - super.getMaxInstanceId();
        if (1 > maxInstanceId || maxInstanceId > 21) {
            return super.getInstanceIdName(i);
        }
        switch (maxInstanceId) {
            case 1:
                return "multiline";
            case 2:
                return "$*";
            case 3:
                return "input";
            case 4:
                return "$_";
            case 5:
                return "lastMatch";
            case 6:
                return "$&";
            case 7:
                return "lastParen";
            case 8:
                return "$+";
            case 9:
                return "leftContext";
            case 10:
                return "$`";
            case 11:
                return "rightContext";
            case 12:
                return "$'";
            default:
                maxInstanceId = (maxInstanceId - 12) - 1;
                return new String(new char[]{'$', (char) (maxInstanceId + 49)});
        }
    }

    protected Object getInstanceIdValue(int i) {
        int maxInstanceId = i - super.getMaxInstanceId();
        if (1 > maxInstanceId || maxInstanceId > 21) {
            return super.getInstanceIdValue(i);
        }
        Object obj;
        RegExpImpl impl = getImpl();
        switch (maxInstanceId) {
            case 1:
            case 2:
                return ScriptRuntime.wrapBoolean(impl.multiline);
            case 3:
            case 4:
                obj = impl.input;
                break;
            case 5:
            case 6:
                obj = impl.lastMatch;
                break;
            case 7:
            case 8:
                obj = impl.lastParen;
                break;
            case 9:
            case 10:
                obj = impl.leftContext;
                break;
            case 11:
            case 12:
                obj = impl.rightContext;
                break;
            default:
                obj = impl.getParenSubString((maxInstanceId - 12) - 1);
                break;
        }
        return obj == null ? "" : obj.toString();
    }

    protected void setInstanceIdValue(int i, Object obj) {
        int maxInstanceId = i - super.getMaxInstanceId();
        switch (maxInstanceId) {
            case 1:
            case 2:
                getImpl().multiline = ScriptRuntime.toBoolean(obj);
                return;
            case 3:
            case 4:
                getImpl().input = ScriptRuntime.toString(obj);
                return;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                return;
            default:
                maxInstanceId = (maxInstanceId - 12) - 1;
                if (maxInstanceId < 0 || maxInstanceId > 8) {
                    super.setInstanceIdValue(i, obj);
                    return;
                }
                return;
        }
    }

    protected void setInstanceIdAttributes(int i, int i2) {
        int maxInstanceId = i - super.getMaxInstanceId();
        switch (maxInstanceId) {
            case 1:
                this.multilineAttr = i2;
                return;
            case 2:
                this.starAttr = i2;
                return;
            case 3:
                this.inputAttr = i2;
                return;
            case 4:
                this.underscoreAttr = i2;
                return;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                return;
            default:
                maxInstanceId = (maxInstanceId - 12) - 1;
                if (maxInstanceId < 0 || maxInstanceId > 8) {
                    super.setInstanceIdAttributes(i, i2);
                    return;
                }
                return;
        }
    }
}
