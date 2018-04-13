package org.mozilla.javascript;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import java.io.Serializable;
import java.lang.reflect.Method;

final class NativeError extends IdScriptableObject {
    private static final int ConstructorId_captureStackTrace = -1;
    public static final int DEFAULT_STACK_LIMIT = -1;
    private static final Method ERROR_DELEGATE_GET_STACK;
    private static final Method ERROR_DELEGATE_SET_STACK;
    private static final Object ERROR_TAG = MNSConstants.ERROR_TAG;
    private static final int Id_constructor = 1;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    private static final int MAX_PROTOTYPE_ID = 3;
    private static final String STACK_HIDE_KEY = "_stackHide";
    static final long serialVersionUID = -5338413581437645187L;
    private RhinoException stackProvider;

    private static final class ProtoProps implements Serializable {
        static final Method GET_PREPARE_STACK;
        static final Method GET_STACK_LIMIT;
        static final String KEY = "_ErrorPrototypeProps";
        static final Method SET_PREPARE_STACK;
        static final Method SET_STACK_LIMIT;
        private static final long serialVersionUID = 1907180507775337939L;
        private Function prepareStackTrace;
        private int stackTraceLimit;

        private ProtoProps() {
            this.stackTraceLimit = -1;
        }

        static {
            try {
                GET_STACK_LIMIT = ProtoProps.class.getMethod("getStackTraceLimit", new Class[]{Scriptable.class});
                SET_STACK_LIMIT = ProtoProps.class.getMethod("setStackTraceLimit", new Class[]{Scriptable.class, Object.class});
                GET_PREPARE_STACK = ProtoProps.class.getMethod("getPrepareStackTrace", new Class[]{Scriptable.class});
                SET_PREPARE_STACK = ProtoProps.class.getMethod("setPrepareStackTrace", new Class[]{Scriptable.class, Object.class});
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public Object getStackTraceLimit(Scriptable scriptable) {
            if (this.stackTraceLimit >= 0) {
                return Integer.valueOf(this.stackTraceLimit);
            }
            return Double.valueOf(Double.POSITIVE_INFINITY);
        }

        public int getStackTraceLimit() {
            return this.stackTraceLimit;
        }

        public void setStackTraceLimit(Scriptable scriptable, Object obj) {
            double toNumber = Context.toNumber(obj);
            if (Double.isNaN(toNumber) || Double.isInfinite(toNumber)) {
                this.stackTraceLimit = -1;
            } else {
                this.stackTraceLimit = (int) toNumber;
            }
        }

        public Object getPrepareStackTrace(Scriptable scriptable) {
            Object prepareStackTrace = getPrepareStackTrace();
            return prepareStackTrace == null ? Undefined.instance : prepareStackTrace;
        }

        public Function getPrepareStackTrace() {
            return this.prepareStackTrace;
        }

        public void setPrepareStackTrace(Scriptable scriptable, Object obj) {
            if (obj == null || Undefined.instance.equals(obj)) {
                this.prepareStackTrace = null;
            } else if (obj instanceof Function) {
                this.prepareStackTrace = (Function) obj;
            }
        }
    }

    NativeError() {
    }

    static {
        try {
            ERROR_DELEGATE_GET_STACK = NativeError.class.getMethod("getStackDelegated", new Class[]{Scriptable.class});
            ERROR_DELEGATE_SET_STACK = NativeError.class.getMethod("setStackDelegated", new Class[]{Scriptable.class, Object.class});
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static void init(Scriptable scriptable, boolean z) {
        Scriptable nativeError = new NativeError();
        ScriptableObject.putProperty(nativeError, "name", MNSConstants.ERROR_TAG);
        ScriptableObject.putProperty(nativeError, "message", "");
        ScriptableObject.putProperty(nativeError, "fileName", "");
        ScriptableObject.putProperty(nativeError, "lineNumber", Integer.valueOf(0));
        nativeError.setAttributes("name", 2);
        nativeError.setAttributes("message", 2);
        nativeError.exportAsJSClass(3, scriptable, z);
        NativeCallSite.init(nativeError, z);
    }

    static NativeError make(Context context, Scriptable scriptable, IdFunctionObject idFunctionObject, Object[] objArr) {
        Scriptable scriptable2 = (Scriptable) idFunctionObject.get("prototype", idFunctionObject);
        Object nativeError = new NativeError();
        nativeError.setPrototype(scriptable2);
        nativeError.setParentScope(scriptable);
        int length = objArr.length;
        if (length >= 1) {
            if (objArr[0] != Undefined.instance) {
                ScriptableObject.putProperty(nativeError, "message", ScriptRuntime.toString(objArr[0]));
            }
            if (length >= 2) {
                ScriptableObject.putProperty(nativeError, "fileName", objArr[1]);
                if (length >= 3) {
                    ScriptableObject.putProperty(nativeError, "lineNumber", Integer.valueOf(ScriptRuntime.toInt32(objArr[2])));
                }
            }
        }
        return nativeError;
    }

    protected void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        addIdFunctionProperty(idFunctionObject, ERROR_TAG, -1, "captureStackTrace", 2);
        ProtoProps protoProps = new ProtoProps();
        associateValue("_ErrorPrototypeProps", protoProps);
        idFunctionObject.defineProperty("stackTraceLimit", protoProps, ProtoProps.GET_STACK_LIMIT, ProtoProps.SET_STACK_LIMIT, 0);
        idFunctionObject.defineProperty("prepareStackTrace", protoProps, ProtoProps.GET_PREPARE_STACK, ProtoProps.SET_PREPARE_STACK, 0);
        super.fillConstructorProperties(idFunctionObject);
    }

    public String getClassName() {
        return MNSConstants.ERROR_TAG;
    }

    public String toString() {
        Object js_toString = js_toString(this);
        return js_toString instanceof String ? (String) js_toString : super.toString();
    }

    protected void initPrototypeId(int i) {
        String str;
        int i2 = 0;
        switch (i) {
            case 1:
                i2 = 1;
                str = "constructor";
                break;
            case 2:
                str = "toString";
                break;
            case 3:
                str = "toSource";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(ERROR_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(ERROR_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        switch (methodId) {
            case -1:
                js_captureStackTrace(context, scriptable2, objArr);
                return Undefined.instance;
            case 1:
                return make(context, scriptable, idFunctionObject, objArr);
            case 2:
                return js_toString(scriptable2);
            case 3:
                return js_toSource(context, scriptable, scriptable2);
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    public void setStackProvider(RhinoException rhinoException) {
        if (this.stackProvider == null) {
            this.stackProvider = rhinoException;
            defineProperty("stack", this, ERROR_DELEGATE_GET_STACK, ERROR_DELEGATE_SET_STACK, 2);
        }
    }

    public Object getStackDelegated(Scriptable scriptable) {
        if (this.stackProvider == null) {
            return NOT_FOUND;
        }
        Object formatStackTrace;
        int i = -1;
        Function function = null;
        ProtoProps protoProps = (ProtoProps) ((NativeError) getPrototype()).getAssociatedValue("_ErrorPrototypeProps");
        if (protoProps != null) {
            i = protoProps.getStackTraceLimit();
            function = protoProps.getPrepareStackTrace();
        }
        ScriptStackElement[] scriptStack = this.stackProvider.getScriptStack(i, (String) getAssociatedValue(STACK_HIDE_KEY));
        if (function == null) {
            formatStackTrace = RhinoException.formatStackTrace(scriptStack, this.stackProvider.details());
        } else {
            formatStackTrace = callPrepareStack(function, scriptStack);
        }
        setStackDelegated(scriptable, formatStackTrace);
        return formatStackTrace;
    }

    public void setStackDelegated(Scriptable scriptable, Object obj) {
        scriptable.delete("stack");
        this.stackProvider = null;
        scriptable.put("stack", scriptable, obj);
    }

    private Object callPrepareStack(Function function, ScriptStackElement[] scriptStackElementArr) {
        Context currentContext = Context.getCurrentContext();
        Object[] objArr = new Object[scriptStackElementArr.length];
        for (int i = 0; i < scriptStackElementArr.length; i++) {
            NativeCallSite nativeCallSite = (NativeCallSite) currentContext.newObject(this, "CallSite");
            nativeCallSite.setElement(scriptStackElementArr[i]);
            objArr[i] = nativeCallSite;
        }
        Scriptable newArray = currentContext.newArray(this, objArr);
        return function.call(currentContext, function, this, new Object[]{this, newArray});
    }

    private static Object js_toString(Scriptable scriptable) {
        Object property = ScriptableObject.getProperty(scriptable, "name");
        if (property == NOT_FOUND || property == Undefined.instance) {
            property = MNSConstants.ERROR_TAG;
        } else {
            property = ScriptRuntime.toString(property);
        }
        Object property2 = ScriptableObject.getProperty(scriptable, "message");
        if (property2 == NOT_FOUND || property2 == Undefined.instance) {
            property2 = "";
        } else {
            property2 = ScriptRuntime.toString(property2);
        }
        if (property.toString().length() == 0) {
            return property2;
        }
        if (property2.toString().length() == 0) {
            return property;
        }
        return ((String) property) + ": " + ((String) property2);
    }

    private static String js_toSource(Context context, Scriptable scriptable, Scriptable scriptable2) {
        Object property = ScriptableObject.getProperty(scriptable2, "name");
        Object property2 = ScriptableObject.getProperty(scriptable2, "message");
        Object property3 = ScriptableObject.getProperty(scriptable2, "fileName");
        Object property4 = ScriptableObject.getProperty(scriptable2, "lineNumber");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(new ");
        if (property == NOT_FOUND) {
            property = Undefined.instance;
        }
        stringBuilder.append(ScriptRuntime.toString(property));
        stringBuilder.append("(");
        if (!(property2 == NOT_FOUND && property3 == NOT_FOUND && property4 == NOT_FOUND)) {
            if (property2 == NOT_FOUND) {
                property = "";
            } else {
                property = property2;
            }
            stringBuilder.append(ScriptRuntime.uneval(context, scriptable, property));
            if (!(property3 == NOT_FOUND && property4 == NOT_FOUND)) {
                stringBuilder.append(", ");
                if (property3 == NOT_FOUND) {
                    property = "";
                } else {
                    property = property3;
                }
                stringBuilder.append(ScriptRuntime.uneval(context, scriptable, property));
                if (property4 != NOT_FOUND) {
                    int toInt32 = ScriptRuntime.toInt32(property4);
                    if (toInt32 != 0) {
                        stringBuilder.append(", ");
                        stringBuilder.append(ScriptRuntime.toString((double) toInt32));
                    }
                }
            }
        }
        stringBuilder.append("))");
        return stringBuilder.toString();
    }

    private static void js_captureStackTrace(Context context, Scriptable scriptable, Object[] objArr) {
        ScriptableObject scriptableObject = (ScriptableObject) ScriptRuntime.toObjectOrNull(context, objArr[0], scriptable);
        Scriptable scriptable2 = null;
        if (objArr.length > 1) {
            scriptable2 = (Function) ScriptRuntime.toObjectOrNull(context, objArr[1], scriptable);
        }
        NativeError nativeError = (NativeError) context.newObject(scriptable, MNSConstants.ERROR_TAG);
        nativeError.setStackProvider(new EvaluatorException("[object Object]"));
        if (scriptable2 != null) {
            Object obj = scriptable2.get("name", scriptable2);
            if (!(obj == null || Undefined.instance.equals(obj))) {
                nativeError.associateValue(STACK_HIDE_KEY, Context.toString(obj));
            }
        }
        scriptableObject.defineProperty("stack", nativeError, ERROR_DELEGATE_GET_STACK, ERROR_DELEGATE_SET_STACK, 0);
    }

    protected int findPrototypeId(String str) {
        int i;
        String str2;
        int length = str.length();
        if (length == 8) {
            char charAt = str.charAt(3);
            if (charAt == 'o') {
                i = 3;
                str2 = "toSource";
            } else {
                if (charAt == 't') {
                    i = 2;
                    str2 = "toString";
                }
                str2 = null;
                i = 0;
            }
        } else {
            if (length == 11) {
                i = 1;
                str2 = "constructor";
            }
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}
