package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug.MacroEvaluationInfo;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import com.google.tagmanager.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.tagmanager.protobuf.nano.MessageNano;

class DebugValueBuilder implements ValueBuilder {
    private Value value;

    private static class TypeMismatchException extends IllegalStateException {
        public TypeMismatchException(String str, int i) {
            super("Attempted operation: " + str + " on object of type: " + i);
        }
    }

    public DebugValueBuilder(Value value) {
        this.value = value;
    }

    private void validateType(int i, int i2, String str) {
        if (i != i2) {
            throw new TypeMismatchException(str, i2);
        }
    }

    public static Value copyImmutableValue(Value value) {
        MessageNano value2 = new Value();
        try {
            MessageNano.mergeFrom(value2, MessageNano.toByteArray(value));
        } catch (InvalidProtocolBufferNanoException e) {
            Log.e("Failed to copy runtime value into debug value");
        }
        return value2;
    }

    public ValueBuilder getListItem(int i) {
        validateType(2, this.value.type, "add new list item");
        return new DebugValueBuilder(this.value.listItem[i]);
    }

    public ValueBuilder getMapKey(int i) {
        validateType(3, this.value.type, "add new map key");
        return new DebugValueBuilder(this.value.mapKey[i]);
    }

    public ValueBuilder getMapValue(int i) {
        validateType(3, this.value.type, "add new map value");
        return new DebugValueBuilder(this.value.mapValue[i]);
    }

    public ValueBuilder getTemplateToken(int i) {
        validateType(7, this.value.type, "add template token");
        return new DebugValueBuilder(this.value.templateToken[i]);
    }

    public MacroEvaluationInfoBuilder createValueMacroEvaluationInfoExtension() {
        validateType(4, this.value.type, "set macro evaluation extension");
        MacroEvaluationInfo macroEvaluationInfo = new MacroEvaluationInfo();
        this.value.setExtension(MacroEvaluationInfo.macro, macroEvaluationInfo);
        return new DebugMacroEvaluationInfoBuilder(macroEvaluationInfo);
    }
}
