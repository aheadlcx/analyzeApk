package org.mozilla.javascript;

public class BoundFunction extends BaseFunction {
    static final long serialVersionUID = 2118137342826470729L;
    private final Object[] boundArgs;
    private final Scriptable boundThis;
    private final int length;
    private final Callable targetFunction;

    public BoundFunction(Context context, Scriptable scriptable, Callable callable, Scriptable scriptable2, Object[] objArr) {
        this.targetFunction = callable;
        this.boundThis = scriptable2;
        this.boundArgs = objArr;
        if (callable instanceof BaseFunction) {
            this.length = Math.max(0, ((BaseFunction) callable).getLength() - objArr.length);
        } else {
            this.length = 0;
        }
        ScriptRuntime.setFunctionProtoAndParent(this, scriptable);
        BaseFunction typeErrorThrower = ScriptRuntime.typeErrorThrower(context);
        ScriptableObject nativeObject = new NativeObject();
        nativeObject.put("get", nativeObject, typeErrorThrower);
        nativeObject.put("set", nativeObject, typeErrorThrower);
        nativeObject.put("enumerable", nativeObject, Boolean.valueOf(false));
        nativeObject.put("configurable", nativeObject, Boolean.valueOf(false));
        nativeObject.preventExtensions();
        defineOwnProperty(context, "caller", nativeObject, false);
        defineOwnProperty(context, "arguments", nativeObject, false);
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return this.targetFunction.call(context, scriptable, this.boundThis != null ? this.boundThis : ScriptRuntime.getTopCallScope(context), concat(this.boundArgs, objArr));
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        if (this.targetFunction instanceof Function) {
            return ((Function) this.targetFunction).construct(context, scriptable, concat(this.boundArgs, objArr));
        }
        throw ScriptRuntime.typeError0("msg.not.ctor");
    }

    public boolean hasInstance(Scriptable scriptable) {
        if (this.targetFunction instanceof Function) {
            return ((Function) this.targetFunction).hasInstance(scriptable);
        }
        throw ScriptRuntime.typeError0("msg.not.ctor");
    }

    public int getLength() {
        return this.length;
    }

    private Object[] concat(Object[] objArr, Object[] objArr2) {
        Object obj = new Object[(objArr.length + objArr2.length)];
        System.arraycopy(objArr, 0, obj, 0, objArr.length);
        System.arraycopy(objArr2, 0, obj, objArr.length, objArr2.length);
        return obj;
    }
}
