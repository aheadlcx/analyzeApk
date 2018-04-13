package org.mozilla.javascript;

public interface Callable {
    Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr);
}
