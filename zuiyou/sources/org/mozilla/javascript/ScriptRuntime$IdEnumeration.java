package org.mozilla.javascript;

import java.io.Serializable;

class ScriptRuntime$IdEnumeration implements Serializable {
    private static final long serialVersionUID = 1;
    Object currentId;
    boolean enumNumbers;
    int enumType;
    Object[] ids;
    int index;
    Scriptable iterator;
    Scriptable obj;
    ObjToIntMap used;

    private ScriptRuntime$IdEnumeration() {
    }
}
