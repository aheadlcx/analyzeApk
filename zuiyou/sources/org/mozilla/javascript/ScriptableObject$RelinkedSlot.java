package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectOutputStream;

class ScriptableObject$RelinkedSlot extends ScriptableObject$Slot {
    final ScriptableObject$Slot slot;

    ScriptableObject$RelinkedSlot(ScriptableObject$Slot scriptableObject$Slot) {
        super(scriptableObject$Slot.name, scriptableObject$Slot.indexOrHash, scriptableObject$Slot.attributes);
        this.slot = ScriptableObject.access$100(scriptableObject$Slot);
    }

    boolean setValue(Object obj, Scriptable scriptable, Scriptable scriptable2) {
        return this.slot.setValue(obj, scriptable, scriptable2);
    }

    Object getValue(Scriptable scriptable) {
        return this.slot.getValue(scriptable);
    }

    ScriptableObject getPropertyDescriptor(Context context, Scriptable scriptable) {
        return this.slot.getPropertyDescriptor(context, scriptable);
    }

    int getAttributes() {
        return this.slot.getAttributes();
    }

    void setAttributes(int i) {
        this.slot.setAttributes(i);
    }

    void markDeleted() {
        super.markDeleted();
        this.slot.markDeleted();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.slot);
    }
}
