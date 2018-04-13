package org.mozilla.javascript.xmlimpl;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;

class XMLCtor extends IdFunctionObject {
    private static final int Id_defaultSettings = 1;
    private static final int Id_ignoreComments = 1;
    private static final int Id_ignoreProcessingInstructions = 2;
    private static final int Id_ignoreWhitespace = 3;
    private static final int Id_prettyIndent = 4;
    private static final int Id_prettyPrinting = 5;
    private static final int Id_setSettings = 3;
    private static final int Id_settings = 2;
    private static final int MAX_FUNCTION_ID = 3;
    private static final int MAX_INSTANCE_ID = 5;
    private static final Object XMLCTOR_TAG = "XMLCtor";
    static final long serialVersionUID = -8708195078359817341L;
    private XmlProcessor options;

    XMLCtor(XML xml, Object obj, int i, int i2) {
        super(xml, obj, i, i2);
        this.options = xml.getProcessor();
        activatePrototypeMap(3);
    }

    private void writeSetting(Scriptable scriptable) {
        for (int i = 1; i <= 5; i++) {
            int maxInstanceId = super.getMaxInstanceId() + i;
            ScriptableObject.putProperty(scriptable, getInstanceIdName(maxInstanceId), getInstanceIdValue(maxInstanceId));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readSettings(org.mozilla.javascript.Scriptable r5) {
        /*
        r4 = this;
        r0 = 1;
    L_0x0001:
        r1 = 5;
        if (r0 > r1) goto L_0x002e;
    L_0x0004:
        r1 = super.getMaxInstanceId();
        r1 = r1 + r0;
        r2 = r4.getInstanceIdName(r1);
        r2 = org.mozilla.javascript.ScriptableObject.getProperty(r5, r2);
        r3 = org.mozilla.javascript.Scriptable.NOT_FOUND;
        if (r2 != r3) goto L_0x0018;
    L_0x0015:
        r0 = r0 + 1;
        goto L_0x0001;
    L_0x0018:
        switch(r0) {
            case 1: goto L_0x0021;
            case 2: goto L_0x0021;
            case 3: goto L_0x0021;
            case 4: goto L_0x0029;
            case 5: goto L_0x0021;
            default: goto L_0x001b;
        };
    L_0x001b:
        r0 = new java.lang.IllegalStateException;
        r0.<init>();
        throw r0;
    L_0x0021:
        r3 = r2 instanceof java.lang.Boolean;
        if (r3 == 0) goto L_0x0015;
    L_0x0025:
        r4.setInstanceIdValue(r1, r2);
        goto L_0x0015;
    L_0x0029:
        r3 = r2 instanceof java.lang.Number;
        if (r3 != 0) goto L_0x0025;
    L_0x002d:
        goto L_0x0015;
    L_0x002e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xmlimpl.XMLCtor.readSettings(org.mozilla.javascript.Scriptable):void");
    }

    protected int getMaxInstanceId() {
        return super.getMaxInstanceId() + 5;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int findInstanceIdInfo(java.lang.String r6) {
        /*
        r5 = this;
        r0 = 0;
        r1 = 0;
        r2 = r6.length();
        switch(r2) {
            case 12: goto L_0x001c;
            case 14: goto L_0x0024;
            case 16: goto L_0x0040;
            case 28: goto L_0x0048;
            default: goto L_0x0009;
        };
    L_0x0009:
        r2 = r1;
        r1 = r0;
    L_0x000b:
        if (r2 == 0) goto L_0x0064;
    L_0x000d:
        if (r2 == r6) goto L_0x0064;
    L_0x000f:
        r2 = r2.equals(r6);
        if (r2 != 0) goto L_0x0064;
    L_0x0015:
        if (r0 != 0) goto L_0x0050;
    L_0x0017:
        r0 = super.findInstanceIdInfo(r6);
    L_0x001b:
        return r0;
    L_0x001c:
        r1 = "prettyIndent";
        r2 = 4;
        r4 = r1;
        r1 = r2;
        r2 = r4;
        goto L_0x000b;
    L_0x0024:
        r2 = r6.charAt(r0);
        r3 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        if (r2 != r3) goto L_0x0034;
    L_0x002c:
        r1 = "ignoreComments";
        r2 = 1;
        r4 = r1;
        r1 = r2;
        r2 = r4;
        goto L_0x000b;
    L_0x0034:
        r3 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        if (r2 != r3) goto L_0x0009;
    L_0x0038:
        r1 = "prettyPrinting";
        r2 = 5;
        r4 = r1;
        r1 = r2;
        r2 = r4;
        goto L_0x000b;
    L_0x0040:
        r1 = "ignoreWhitespace";
        r2 = 3;
        r4 = r1;
        r1 = r2;
        r2 = r4;
        goto L_0x000b;
    L_0x0048:
        r1 = "ignoreProcessingInstructions";
        r2 = 2;
        r4 = r1;
        r1 = r2;
        r2 = r4;
        goto L_0x000b;
    L_0x0050:
        switch(r0) {
            case 1: goto L_0x0059;
            case 2: goto L_0x0059;
            case 3: goto L_0x0059;
            case 4: goto L_0x0059;
            case 5: goto L_0x0059;
            default: goto L_0x0053;
        };
    L_0x0053:
        r0 = new java.lang.IllegalStateException;
        r0.<init>();
        throw r0;
    L_0x0059:
        r1 = 6;
        r2 = super.getMaxInstanceId();
        r0 = r0 + r2;
        r0 = org.mozilla.javascript.IdScriptableObject.instanceIdInfo(r1, r0);
        goto L_0x001b;
    L_0x0064:
        r0 = r1;
        goto L_0x0015;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xmlimpl.XMLCtor.findInstanceIdInfo(java.lang.String):int");
    }

    protected String getInstanceIdName(int i) {
        switch (i - super.getMaxInstanceId()) {
            case 1:
                return "ignoreComments";
            case 2:
                return "ignoreProcessingInstructions";
            case 3:
                return "ignoreWhitespace";
            case 4:
                return "prettyIndent";
            case 5:
                return "prettyPrinting";
            default:
                return super.getInstanceIdName(i);
        }
    }

    protected Object getInstanceIdValue(int i) {
        switch (i - super.getMaxInstanceId()) {
            case 1:
                return ScriptRuntime.wrapBoolean(this.options.isIgnoreComments());
            case 2:
                return ScriptRuntime.wrapBoolean(this.options.isIgnoreProcessingInstructions());
            case 3:
                return ScriptRuntime.wrapBoolean(this.options.isIgnoreWhitespace());
            case 4:
                return ScriptRuntime.wrapInt(this.options.getPrettyIndent());
            case 5:
                return ScriptRuntime.wrapBoolean(this.options.isPrettyPrinting());
            default:
                return super.getInstanceIdValue(i);
        }
    }

    protected void setInstanceIdValue(int i, Object obj) {
        switch (i - super.getMaxInstanceId()) {
            case 1:
                this.options.setIgnoreComments(ScriptRuntime.toBoolean(obj));
                return;
            case 2:
                this.options.setIgnoreProcessingInstructions(ScriptRuntime.toBoolean(obj));
                return;
            case 3:
                this.options.setIgnoreWhitespace(ScriptRuntime.toBoolean(obj));
                return;
            case 4:
                this.options.setPrettyIndent(ScriptRuntime.toInt32(obj));
                return;
            case 5:
                this.options.setPrettyPrinting(ScriptRuntime.toBoolean(obj));
                return;
            default:
                super.setInstanceIdValue(i, obj);
                return;
        }
    }

    protected int findPrototypeId(String str) {
        int i;
        String str2;
        int length = str.length();
        if (length == 8) {
            i = 2;
            str2 = "settings";
        } else if (length == 11) {
            i = 3;
            str2 = "setSettings";
        } else if (length == 15) {
            i = 1;
            str2 = "defaultSettings";
        } else {
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }

    protected void initPrototypeId(int i) {
        String str;
        int i2 = 0;
        switch (i) {
            case 1:
                str = "defaultSettings";
                break;
            case 2:
                str = "settings";
                break;
            case 3:
                i2 = 1;
                str = "setSettings";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(XMLCTOR_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(XMLCTOR_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        Object newObject;
        switch (methodId) {
            case 1:
                this.options.setDefault();
                newObject = context.newObject(scriptable);
                writeSetting(newObject);
                return newObject;
            case 2:
                newObject = context.newObject(scriptable);
                writeSetting(newObject);
                return newObject;
            case 3:
                if (objArr.length == 0 || objArr[0] == null || objArr[0] == Undefined.instance) {
                    this.options.setDefault();
                } else if (objArr[0] instanceof Scriptable) {
                    readSettings((Scriptable) objArr[0]);
                }
                return Undefined.instance;
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    public boolean hasInstance(Scriptable scriptable) {
        return (scriptable instanceof XML) || (scriptable instanceof XMLList);
    }
}
