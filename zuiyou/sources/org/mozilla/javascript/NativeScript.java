package org.mozilla.javascript;

class NativeScript extends BaseFunction {
    private static final int Id_compile = 3;
    private static final int Id_constructor = 1;
    private static final int Id_exec = 4;
    private static final int Id_toString = 2;
    private static final int MAX_PROTOTYPE_ID = 4;
    private static final Object SCRIPT_TAG = "Script";
    static final long serialVersionUID = -6795101161980121700L;
    private Script script;

    static void init(Scriptable scriptable, boolean z) {
        new NativeScript(null).exportAsJSClass(4, scriptable, z);
    }

    private NativeScript(Script script) {
        this.script = script;
    }

    public String getClassName() {
        return "Script";
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (this.script != null) {
            return this.script.exec(context, scriptable);
        }
        return Undefined.instance;
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        throw Context.reportRuntimeError0("msg.script.is.not.constructor");
    }

    public int getLength() {
        return 0;
    }

    public int getArity() {
        return 0;
    }

    String decompile(int i, int i2) {
        if (this.script instanceof NativeFunction) {
            return ((NativeFunction) this.script).decompile(i, i2);
        }
        return super.decompile(i, i2);
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
                str = "compile";
                break;
            case 4:
                str = "exec";
                i2 = 0;
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(SCRIPT_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(SCRIPT_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        Object nativeScript;
        switch (methodId) {
            case 1:
                nativeScript = new NativeScript(compile(context, objArr.length == 0 ? "" : ScriptRuntime.toString(objArr[0])));
                ScriptRuntime.setObjectProtoAndParent(nativeScript, scriptable);
                return nativeScript;
            case 2:
                Script script = realThis(scriptable2, idFunctionObject).script;
                if (script == null) {
                    return "";
                }
                return context.decompileScript(script, 0);
            case 3:
                nativeScript = realThis(scriptable2, idFunctionObject);
                nativeScript.script = compile(context, ScriptRuntime.toString(objArr, 0));
                return nativeScript;
            case 4:
                throw Context.reportRuntimeError1("msg.cant.call.indirect", "exec");
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    private static NativeScript realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (scriptable instanceof NativeScript) {
            return (NativeScript) scriptable;
        }
        throw IdScriptableObject.incompatibleCallError(idFunctionObject);
    }

    private static Script compile(Context context, String str) {
        int[] iArr = new int[]{0};
        String sourcePositionFromStack = Context.getSourcePositionFromStack(iArr);
        if (sourcePositionFromStack == null) {
            sourcePositionFromStack = "<Script object>";
            iArr[0] = 1;
        }
        return context.compileString(str, null, DefaultErrorReporter.forEval(context.getErrorReporter()), sourcePositionFromStack, iArr[0], null);
    }

    protected int findPrototypeId(String str) {
        int i;
        String str2;
        switch (str.length()) {
            case 4:
                i = 4;
                str2 = "exec";
                break;
            case 7:
                i = 3;
                str2 = "compile";
                break;
            case 8:
                i = 2;
                str2 = "toString";
                break;
            case 11:
                i = 1;
                str2 = "constructor";
                break;
            default:
                str2 = null;
                i = 0;
                break;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}
