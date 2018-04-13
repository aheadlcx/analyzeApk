package org.mozilla.javascript;

import java.io.Serializable;

public abstract class Ref implements Serializable {
    static final long serialVersionUID = 4044540354730911424L;

    public abstract Object get(Context context);

    @Deprecated
    public abstract Object set(Context context, Object obj);

    public boolean has(Context context) {
        return true;
    }

    public Object set(Context context, Scriptable scriptable, Object obj) {
        return set(context, obj);
    }

    public boolean delete(Context context) {
        return false;
    }
}
