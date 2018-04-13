package org.mozilla.javascript;

import java.util.Comparator;

class NativeArray$2 implements Comparator<Object> {
    NativeArray$2() {
    }

    public int compare(Object obj, Object obj2) {
        if (obj == Scriptable.NOT_FOUND) {
            if (obj2 == Scriptable.NOT_FOUND) {
                return 0;
            }
            return 1;
        } else if (obj2 == Scriptable.NOT_FOUND) {
            return -1;
        } else {
            if (obj == Undefined.instance) {
                if (obj2 != Undefined.instance) {
                    return 1;
                }
                return 0;
            } else if (obj2 == Undefined.instance) {
                return -1;
            } else {
                return ScriptRuntime.toString(obj).compareTo(ScriptRuntime.toString(obj2));
            }
        }
    }
}
