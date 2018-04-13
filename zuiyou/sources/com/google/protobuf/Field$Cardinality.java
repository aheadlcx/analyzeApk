package com.google.protobuf;

import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Internal.EnumLiteMap;

public enum Field$Cardinality implements ProtocolMessageEnum {
    CARDINALITY_UNKNOWN(0),
    CARDINALITY_OPTIONAL(1),
    CARDINALITY_REQUIRED(2),
    CARDINALITY_REPEATED(3),
    UNRECOGNIZED(-1);
    
    public static final int CARDINALITY_OPTIONAL_VALUE = 1;
    public static final int CARDINALITY_REPEATED_VALUE = 3;
    public static final int CARDINALITY_REQUIRED_VALUE = 2;
    public static final int CARDINALITY_UNKNOWN_VALUE = 0;
    private static final Field$Cardinality[] VALUES = null;
    private static final EnumLiteMap<Field$Cardinality> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new Field$Cardinality$1();
        VALUES = values();
    }

    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    @Deprecated
    public static Field$Cardinality valueOf(int i) {
        return forNumber(i);
    }

    public static Field$Cardinality forNumber(int i) {
        switch (i) {
            case 0:
                return CARDINALITY_UNKNOWN;
            case 1:
                return CARDINALITY_OPTIONAL;
            case 2:
                return CARDINALITY_REQUIRED;
            case 3:
                return CARDINALITY_REPEATED;
            default:
                return null;
        }
    }

    public static EnumLiteMap<Field$Cardinality> internalGetValueMap() {
        return internalValueMap;
    }

    public final EnumValueDescriptor getValueDescriptor() {
        return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
    }

    public final EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }

    public static final EnumDescriptor getDescriptor() {
        return (EnumDescriptor) Field.getDescriptor().getEnumTypes().get(1);
    }

    public static Field$Cardinality valueOf(EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() != getDescriptor()) {
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        } else if (enumValueDescriptor.getIndex() == -1) {
            return UNRECOGNIZED;
        } else {
            return VALUES[enumValueDescriptor.getIndex()];
        }
    }

    private Field$Cardinality(int i) {
        this.value = i;
    }
}
