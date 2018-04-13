package com.google.protobuf;

import com.google.protobuf.Descriptors.FieldDescriptor;

public abstract class Extension<ContainingType extends MessageLite, Type> extends ExtensionLite<ContainingType, Type> {

    protected enum ExtensionType {
        IMMUTABLE,
        MUTABLE,
        PROTO1
    }

    public enum MessageType {
        PROTO1,
        PROTO2
    }

    protected abstract Object fromReflectionType(Object obj);

    public abstract FieldDescriptor getDescriptor();

    protected abstract ExtensionType getExtensionType();

    protected abstract Object singularFromReflectionType(Object obj);

    protected abstract Object singularToReflectionType(Object obj);

    protected abstract Object toReflectionType(Object obj);

    final boolean isLite() {
        return false;
    }

    public MessageType getMessageType() {
        return MessageType.PROTO2;
    }
}
