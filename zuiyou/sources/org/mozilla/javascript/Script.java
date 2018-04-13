package org.mozilla.javascript;

public interface Script {
    Object exec(Context context, Scriptable scriptable);
}
