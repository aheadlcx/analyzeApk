package org.mozilla.javascript.xml;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.NativeWith;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.Scriptable;

public abstract class XMLObject extends IdScriptableObject {
    static final long serialVersionUID = 8455156490438576500L;

    public abstract boolean delete(Context context, Object obj);

    public abstract NativeWith enterDotQuery(Scriptable scriptable);

    public abstract NativeWith enterWith(Scriptable scriptable);

    public abstract Object get(Context context, Object obj);

    public abstract Scriptable getExtraMethodSource(Context context);

    public abstract Object getFunctionProperty(Context context, int i);

    public abstract Object getFunctionProperty(Context context, String str);

    public abstract boolean has(Context context, Object obj);

    public abstract Ref memberRef(Context context, Object obj, int i);

    public abstract Ref memberRef(Context context, Object obj, Object obj2, int i);

    public abstract void put(Context context, Object obj, Object obj2);

    public XMLObject(Scriptable scriptable, Scriptable scriptable2) {
        super(scriptable, scriptable2);
    }

    public Object addValues(Context context, boolean z, Object obj) {
        return Scriptable.NOT_FOUND;
    }

    public String getTypeOf() {
        return avoidObjectDetection() ? "undefined" : "xml";
    }
}
