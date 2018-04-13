package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

class ScriptableObject$Slot implements Serializable {
    private static final long serialVersionUID = -6090581677123995491L;
    private volatile short attributes;
    int indexOrHash;
    String name;
    transient ScriptableObject$Slot next;
    volatile transient ScriptableObject$Slot orderedNext;
    volatile Object value;
    volatile transient boolean wasDeleted;

    ScriptableObject$Slot(String str, int i, int i2) {
        this.name = str;
        this.indexOrHash = i;
        this.attributes = (short) i2;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        if (this.name != null) {
            this.indexOrHash = this.name.hashCode();
        }
    }

    boolean setValue(Object obj, Scriptable scriptable, Scriptable scriptable2) {
        if ((this.attributes & 1) != 0) {
            return true;
        }
        if (scriptable != scriptable2) {
            return false;
        }
        this.value = obj;
        return true;
    }

    Object getValue(Scriptable scriptable) {
        return this.value;
    }

    int getAttributes() {
        return this.attributes;
    }

    synchronized void setAttributes(int i) {
        ScriptableObject.checkValidAttributes(i);
        this.attributes = (short) i;
    }

    void markDeleted() {
        this.wasDeleted = true;
        this.value = null;
        this.name = null;
    }

    ScriptableObject getPropertyDescriptor(Context context, Scriptable scriptable) {
        return ScriptableObject.buildDataDescriptor(scriptable, this.value, this.attributes);
    }
}
