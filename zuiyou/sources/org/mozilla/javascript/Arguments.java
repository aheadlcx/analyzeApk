package org.mozilla.javascript;

final class Arguments extends IdScriptableObject {
    private static final String FTAG = "Arguments";
    private static final int Id_callee = 1;
    private static final int Id_caller = 3;
    private static final int Id_length = 2;
    private static final int MAX_INSTANCE_ID = 3;
    static final long serialVersionUID = 4275508002492040609L;
    private NativeCall activation;
    private Object[] args;
    private int calleeAttr = 2;
    private Object calleeObj;
    private int callerAttr = 2;
    private Object callerObj;
    private int lengthAttr = 2;
    private Object lengthObj;

    public Arguments(NativeCall nativeCall) {
        this.activation = nativeCall;
        Scriptable parentScope = nativeCall.getParentScope();
        setParentScope(parentScope);
        setPrototype(ScriptableObject.getObjectPrototype(parentScope));
        this.args = nativeCall.originalArgs;
        this.lengthObj = Integer.valueOf(this.args.length);
        NativeFunction nativeFunction = nativeCall.function;
        this.calleeObj = nativeFunction;
        int languageVersion = nativeFunction.getLanguageVersion();
        if (languageVersion > 130 || languageVersion == 0) {
            this.callerObj = NOT_FOUND;
        } else {
            this.callerObj = null;
        }
    }

    public String getClassName() {
        return FTAG;
    }

    private Object arg(int i) {
        if (i < 0 || this.args.length <= i) {
            return NOT_FOUND;
        }
        return this.args[i];
    }

    private void putIntoActivation(int i, Object obj) {
        this.activation.put(this.activation.function.getParamOrVarName(i), this.activation, obj);
    }

    private Object getFromActivation(int i) {
        return this.activation.get(this.activation.function.getParamOrVarName(i), this.activation);
    }

    private void replaceArg(int i, Object obj) {
        if (sharedWithActivation(i)) {
            putIntoActivation(i, obj);
        }
        synchronized (this) {
            if (this.args == this.activation.originalArgs) {
                this.args = (Object[]) this.args.clone();
            }
            this.args[i] = obj;
        }
    }

    private void removeArg(int i) {
        synchronized (this) {
            if (this.args[i] != NOT_FOUND) {
                if (this.args == this.activation.originalArgs) {
                    this.args = (Object[]) this.args.clone();
                }
                this.args[i] = NOT_FOUND;
            }
        }
    }

    public boolean has(int i, Scriptable scriptable) {
        if (arg(i) != NOT_FOUND) {
            return true;
        }
        return super.has(i, scriptable);
    }

    public Object get(int i, Scriptable scriptable) {
        Object arg = arg(i);
        if (arg == NOT_FOUND) {
            return super.get(i, scriptable);
        }
        if (sharedWithActivation(i)) {
            return getFromActivation(i);
        }
        return arg;
    }

    private boolean sharedWithActivation(int i) {
        NativeFunction nativeFunction = this.activation.function;
        int paramCount = nativeFunction.getParamCount();
        if (i >= paramCount) {
            return false;
        }
        if (i < paramCount - 1) {
            String paramOrVarName = nativeFunction.getParamOrVarName(i);
            for (int i2 = i + 1; i2 < paramCount; i2++) {
                if (paramOrVarName.equals(nativeFunction.getParamOrVarName(i2))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        if (arg(i) == NOT_FOUND) {
            super.put(i, scriptable, obj);
        } else {
            replaceArg(i, obj);
        }
    }

    public void delete(int i) {
        if (i >= 0 && i < this.args.length) {
            removeArg(i);
        }
        super.delete(i);
    }

    protected int getMaxInstanceId() {
        return 3;
    }

    protected int findInstanceIdInfo(String str) {
        int i;
        String str2;
        int i2;
        if (str.length() == 6) {
            char charAt = str.charAt(5);
            if (charAt == 'e') {
                i = 1;
                str2 = "callee";
            } else if (charAt == 'h') {
                i = 2;
                str2 = "length";
            } else if (charAt == 'r') {
                i = 3;
                str2 = "caller";
            }
            if (!(str2 == null || str2 == str || str2.equals(str))) {
                i = 0;
            }
            if (i == 0) {
                return super.findInstanceIdInfo(str);
            }
            switch (i) {
                case 1:
                    i2 = this.calleeAttr;
                    break;
                case 2:
                    i2 = this.lengthAttr;
                    break;
                case 3:
                    i2 = this.callerAttr;
                    break;
                default:
                    throw new IllegalStateException();
            }
            return IdScriptableObject.instanceIdInfo(i2, i);
        }
        str2 = null;
        i = 0;
        i = 0;
        if (i == 0) {
            return super.findInstanceIdInfo(str);
        }
        switch (i) {
            case 1:
                i2 = this.calleeAttr;
                break;
            case 2:
                i2 = this.lengthAttr;
                break;
            case 3:
                i2 = this.callerAttr;
                break;
            default:
                throw new IllegalStateException();
        }
        return IdScriptableObject.instanceIdInfo(i2, i);
    }

    protected String getInstanceIdName(int i) {
        switch (i) {
            case 1:
                return "callee";
            case 2:
                return "length";
            case 3:
                return "caller";
            default:
                return null;
        }
    }

    protected Object getInstanceIdValue(int i) {
        switch (i) {
            case 1:
                return this.calleeObj;
            case 2:
                return this.lengthObj;
            case 3:
                UniqueTag uniqueTag = this.callerObj;
                if (uniqueTag == UniqueTag.NULL_VALUE) {
                    return null;
                }
                if (uniqueTag != null) {
                    return uniqueTag;
                }
                Scriptable scriptable = this.activation.parentActivationCall;
                if (scriptable != null) {
                    return scriptable.get("arguments", scriptable);
                }
                return uniqueTag;
            default:
                return super.getInstanceIdValue(i);
        }
    }

    protected void setInstanceIdValue(int i, Object obj) {
        switch (i) {
            case 1:
                this.calleeObj = obj;
                return;
            case 2:
                this.lengthObj = obj;
                return;
            case 3:
                if (obj == null) {
                    obj = UniqueTag.NULL_VALUE;
                }
                this.callerObj = obj;
                return;
            default:
                super.setInstanceIdValue(i, obj);
                return;
        }
    }

    protected void setInstanceIdAttributes(int i, int i2) {
        switch (i) {
            case 1:
                this.calleeAttr = i2;
                return;
            case 2:
                this.lengthAttr = i2;
                return;
            case 3:
                this.callerAttr = i2;
                return;
            default:
                super.setInstanceIdAttributes(i, i2);
                return;
        }
    }

    Object[] getIds(boolean z) {
        int i = 0;
        Object ids = super.getIds(z);
        if (this.args.length != 0) {
            int intValue;
            boolean[] zArr = new boolean[this.args.length];
            int length = this.args.length;
            for (int i2 = 0; i2 != ids.length; i2++) {
                Object obj = ids[i2];
                if (obj instanceof Integer) {
                    intValue = ((Integer) obj).intValue();
                    if (intValue >= 0 && intValue < this.args.length && !zArr[intValue]) {
                        zArr[intValue] = true;
                        length--;
                    }
                }
            }
            if (!z) {
                intValue = 0;
                while (intValue < zArr.length) {
                    if (!zArr[intValue] && super.has(intValue, this)) {
                        zArr[intValue] = true;
                        length--;
                    }
                    intValue++;
                }
            }
            if (length != 0) {
                Object obj2 = new Object[(ids.length + length)];
                System.arraycopy(ids, 0, obj2, length, ids.length);
                intValue = 0;
                while (i != this.args.length) {
                    if (zArr == null || !zArr[i]) {
                        obj2[intValue] = Integer.valueOf(i);
                        intValue++;
                    }
                    i++;
                }
                if (intValue != length) {
                    Kit.codeBug();
                }
                return obj2;
            }
        }
        return ids;
    }

    protected ScriptableObject getOwnPropertyDescriptor(Context context, Object obj) {
        double toNumber = ScriptRuntime.toNumber(obj);
        int i = (int) toNumber;
        if (toNumber != ((double) i)) {
            return super.getOwnPropertyDescriptor(context, obj);
        }
        Object arg = arg(i);
        if (arg == NOT_FOUND) {
            return super.getOwnPropertyDescriptor(context, obj);
        }
        if (sharedWithActivation(i)) {
            arg = getFromActivation(i);
        }
        if (super.has(i, this)) {
            Scriptable ownPropertyDescriptor = super.getOwnPropertyDescriptor(context, obj);
            ownPropertyDescriptor.put("value", ownPropertyDescriptor, arg);
            return ownPropertyDescriptor;
        }
        Scriptable scriptable;
        ownPropertyDescriptor = getParentScope();
        if (ownPropertyDescriptor != null) {
            scriptable = ownPropertyDescriptor;
        }
        return buildDataDescriptor(scriptable, arg, 0);
    }

    protected void defineOwnProperty(Context context, Object obj, ScriptableObject scriptableObject, boolean z) {
        super.defineOwnProperty(context, obj, scriptableObject, z);
        double toNumber = ScriptRuntime.toNumber(obj);
        int i = (int) toNumber;
        if (toNumber != ((double) i) || arg(i) == NOT_FOUND) {
            return;
        }
        if (isAccessorDescriptor(scriptableObject)) {
            removeArg(i);
            return;
        }
        Object property = getProperty(scriptableObject, "value");
        if (property != NOT_FOUND) {
            replaceArg(i, property);
            if (isFalse(getProperty(scriptableObject, "writable"))) {
                removeArg(i);
            }
        }
    }
}
