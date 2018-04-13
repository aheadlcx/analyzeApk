package org.mozilla.javascript;

public class IdFunctionObject extends BaseFunction {
    static final long serialVersionUID = -5332312783643935019L;
    private int arity;
    private String functionName;
    private final IdFunctionCall idcall;
    private final int methodId;
    private final Object tag;
    private boolean useCallAsConstructor;

    public IdFunctionObject(IdFunctionCall idFunctionCall, Object obj, int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
        this.idcall = idFunctionCall;
        this.tag = obj;
        this.methodId = i;
        this.arity = i2;
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
    }

    public IdFunctionObject(IdFunctionCall idFunctionCall, Object obj, int i, String str, int i2, Scriptable scriptable) {
        super(scriptable, null);
        if (i2 < 0) {
            throw new IllegalArgumentException();
        } else if (str == null) {
            throw new IllegalArgumentException();
        } else {
            this.idcall = idFunctionCall;
            this.tag = obj;
            this.methodId = i;
            this.arity = i2;
            this.functionName = str;
        }
    }

    public void initFunction(String str, Scriptable scriptable) {
        if (str == null) {
            throw new IllegalArgumentException();
        } else if (scriptable == null) {
            throw new IllegalArgumentException();
        } else {
            this.functionName = str;
            setParentScope(scriptable);
        }
    }

    public final boolean hasTag(Object obj) {
        if (obj == null) {
            return this.tag == null;
        } else {
            return obj.equals(this.tag);
        }
    }

    public final int methodId() {
        return this.methodId;
    }

    public final void markAsConstructor(Scriptable scriptable) {
        this.useCallAsConstructor = true;
        setImmunePrototypeProperty(scriptable);
    }

    public final void addAsProperty(Scriptable scriptable) {
        ScriptableObject.defineProperty(scriptable, this.functionName, this, 2);
    }

    public void exportAsScopeProperty() {
        addAsProperty(getParentScope());
    }

    public Scriptable getPrototype() {
        Scriptable prototype = super.getPrototype();
        if (prototype != null) {
            return prototype;
        }
        prototype = getFunctionPrototype(getParentScope());
        setPrototype(prototype);
        return prototype;
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return this.idcall.execIdCall(this, context, scriptable, scriptable2, objArr);
    }

    public Scriptable createObject(Context context, Scriptable scriptable) {
        if (this.useCallAsConstructor) {
            return null;
        }
        throw ScriptRuntime.typeError1("msg.not.ctor", this.functionName);
    }

    String decompile(int i, int i2) {
        Object obj;
        StringBuilder stringBuilder = new StringBuilder();
        if ((i2 & 1) != 0) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj == null) {
            stringBuilder.append("function ");
            stringBuilder.append(getFunctionName());
            stringBuilder.append("() { ");
        }
        stringBuilder.append("[native code for ");
        if (this.idcall instanceof Scriptable) {
            stringBuilder.append(((Scriptable) this.idcall).getClassName());
            stringBuilder.append('.');
        }
        stringBuilder.append(getFunctionName());
        stringBuilder.append(", arity=");
        stringBuilder.append(getArity());
        stringBuilder.append(obj != null ? "]\n" : "] }\n");
        return stringBuilder.toString();
    }

    public int getArity() {
        return this.arity;
    }

    public int getLength() {
        return getArity();
    }

    public String getFunctionName() {
        return this.functionName == null ? "" : this.functionName;
    }

    public final RuntimeException unknown() {
        return new IllegalArgumentException("BAD FUNCTION ID=" + this.methodId + " MASTER=" + this.idcall);
    }
}
