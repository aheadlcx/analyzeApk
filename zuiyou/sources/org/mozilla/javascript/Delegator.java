package org.mozilla.javascript;

public class Delegator implements Function {
    protected Scriptable obj = null;

    public Delegator(Scriptable scriptable) {
        this.obj = scriptable;
    }

    protected Delegator newInstance() {
        try {
            return (Delegator) getClass().newInstance();
        } catch (Throwable e) {
            throw Context.throwAsScriptRuntimeEx(e);
        }
    }

    public Scriptable getDelegee() {
        return this.obj;
    }

    public void setDelegee(Scriptable scriptable) {
        this.obj = scriptable;
    }

    public String getClassName() {
        return this.obj.getClassName();
    }

    public Object get(String str, Scriptable scriptable) {
        return this.obj.get(str, scriptable);
    }

    public Object get(int i, Scriptable scriptable) {
        return this.obj.get(i, scriptable);
    }

    public boolean has(String str, Scriptable scriptable) {
        return this.obj.has(str, scriptable);
    }

    public boolean has(int i, Scriptable scriptable) {
        return this.obj.has(i, scriptable);
    }

    public void put(String str, Scriptable scriptable, Object obj) {
        this.obj.put(str, scriptable, obj);
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        this.obj.put(i, scriptable, obj);
    }

    public void delete(String str) {
        this.obj.delete(str);
    }

    public void delete(int i) {
        this.obj.delete(i);
    }

    public Scriptable getPrototype() {
        return this.obj.getPrototype();
    }

    public void setPrototype(Scriptable scriptable) {
        this.obj.setPrototype(scriptable);
    }

    public Scriptable getParentScope() {
        return this.obj.getParentScope();
    }

    public void setParentScope(Scriptable scriptable) {
        this.obj.setParentScope(scriptable);
    }

    public Object[] getIds() {
        return this.obj.getIds();
    }

    public Object getDefaultValue(Class<?> cls) {
        return (cls == null || cls == ScriptRuntime.ScriptableClass || cls == ScriptRuntime.FunctionClass) ? this : this.obj.getDefaultValue(cls);
    }

    public boolean hasInstance(Scriptable scriptable) {
        return this.obj.hasInstance(scriptable);
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ((Function) this.obj).call(context, scriptable, scriptable2, objArr);
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        if (this.obj != null) {
            return ((Function) this.obj).construct(context, scriptable, objArr);
        }
        Scriptable nativeObject;
        Delegator newInstance = newInstance();
        if (objArr.length == 0) {
            nativeObject = new NativeObject();
        } else {
            nativeObject = ScriptRuntime.toObject(context, scriptable, objArr[0]);
        }
        newInstance.setDelegee(nativeObject);
        return newInstance;
    }
}
