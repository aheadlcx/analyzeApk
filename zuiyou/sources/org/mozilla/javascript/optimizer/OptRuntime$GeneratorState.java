package org.mozilla.javascript.optimizer;

import org.mozilla.javascript.Scriptable;

public class OptRuntime$GeneratorState {
    static final String CLASS_NAME = "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState";
    static final String resumptionPoint_NAME = "resumptionPoint";
    static final String resumptionPoint_TYPE = "I";
    static final String thisObj_NAME = "thisObj";
    static final String thisObj_TYPE = "Lorg/mozilla/javascript/Scriptable;";
    Object[] localsState;
    int maxLocals;
    int maxStack;
    public int resumptionPoint;
    Object[] stackState;
    public Scriptable thisObj;

    OptRuntime$GeneratorState(Scriptable scriptable, int i, int i2) {
        this.thisObj = scriptable;
        this.maxLocals = i;
        this.maxStack = i2;
    }
}
