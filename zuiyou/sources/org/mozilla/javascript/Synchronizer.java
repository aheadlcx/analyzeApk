package org.mozilla.javascript;

public class Synchronizer extends Delegator {
    private Object syncObject;

    public Synchronizer(Scriptable scriptable) {
        super(scriptable);
    }

    public Synchronizer(Scriptable scriptable, Object obj) {
        super(scriptable);
        this.syncObject = obj;
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object obj;
        if (this.syncObject != null) {
            obj = this.syncObject;
        } else {
            Scriptable scriptable3 = scriptable2;
        }
        Object unwrap;
        if (obj instanceof Wrapper) {
            unwrap = ((Wrapper) obj).unwrap();
        } else {
            unwrap = obj;
        }
        synchronized (r1) {
            obj = ((Function) this.obj).call(context, scriptable, scriptable2, objArr);
        }
        return obj;
    }
}
