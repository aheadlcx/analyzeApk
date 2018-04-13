package org.msgpack.core;

import org.msgpack.core.MessagePack.Code;
import org.msgpack.core.annotations.VisibleForTesting;
import org.msgpack.value.ValueType;

public enum MessageFormat {
    POSFIXINT(ValueType.INTEGER),
    FIXMAP(ValueType.MAP),
    FIXARRAY(ValueType.ARRAY),
    FIXSTR(ValueType.STRING),
    NIL(ValueType.NIL),
    NEVER_USED(null),
    BOOLEAN(ValueType.BOOLEAN),
    BIN8(ValueType.BINARY),
    BIN16(ValueType.BINARY),
    BIN32(ValueType.BINARY),
    EXT8(ValueType.EXTENSION),
    EXT16(ValueType.EXTENSION),
    EXT32(ValueType.EXTENSION),
    FLOAT32(ValueType.FLOAT),
    FLOAT64(ValueType.FLOAT),
    UINT8(ValueType.INTEGER),
    UINT16(ValueType.INTEGER),
    UINT32(ValueType.INTEGER),
    UINT64(ValueType.INTEGER),
    INT8(ValueType.INTEGER),
    INT16(ValueType.INTEGER),
    INT32(ValueType.INTEGER),
    INT64(ValueType.INTEGER),
    FIXEXT1(ValueType.EXTENSION),
    FIXEXT2(ValueType.EXTENSION),
    FIXEXT4(ValueType.EXTENSION),
    FIXEXT8(ValueType.EXTENSION),
    FIXEXT16(ValueType.EXTENSION),
    STR8(ValueType.STRING),
    STR16(ValueType.STRING),
    STR32(ValueType.STRING),
    ARRAY16(ValueType.ARRAY),
    ARRAY32(ValueType.ARRAY),
    MAP16(ValueType.MAP),
    MAP32(ValueType.MAP),
    NEGFIXINT(ValueType.INTEGER);
    
    private static final MessageFormat[] formatTable = null;
    private final ValueType valueType;

    static {
        formatTable = new MessageFormat[256];
        int i;
        while (i <= 255) {
            formatTable[i] = toMessageFormat((byte) i);
            i++;
        }
    }

    private MessageFormat(ValueType valueType) {
        this.valueType = valueType;
    }

    public ValueType getValueType() throws MessageFormatException {
        if (this != NEVER_USED) {
            return this.valueType;
        }
        throw new MessageFormatException("Cannot convert NEVER_USED to ValueType");
    }

    public static MessageFormat valueOf(byte b) {
        return formatTable[b & 255];
    }

    @VisibleForTesting
    static MessageFormat toMessageFormat(byte b) {
        if (Code.isPosFixInt(b)) {
            return POSFIXINT;
        }
        if (Code.isNegFixInt(b)) {
            return NEGFIXINT;
        }
        if (Code.isFixStr(b)) {
            return FIXSTR;
        }
        if (Code.isFixedArray(b)) {
            return FIXARRAY;
        }
        if (Code.isFixedMap(b)) {
            return FIXMAP;
        }
        switch (b) {
            case (byte) -64:
                return NIL;
            case (byte) -62:
            case (byte) -61:
                return BOOLEAN;
            case (byte) -60:
                return BIN8;
            case (byte) -59:
                return BIN16;
            case (byte) -58:
                return BIN32;
            case (byte) -57:
                return EXT8;
            case (byte) -56:
                return EXT16;
            case (byte) -55:
                return EXT32;
            case (byte) -54:
                return FLOAT32;
            case (byte) -53:
                return FLOAT64;
            case (byte) -52:
                return UINT8;
            case (byte) -51:
                return UINT16;
            case (byte) -50:
                return UINT32;
            case (byte) -49:
                return UINT64;
            case (byte) -48:
                return INT8;
            case (byte) -47:
                return INT16;
            case (byte) -46:
                return INT32;
            case (byte) -45:
                return INT64;
            case (byte) -44:
                return FIXEXT1;
            case (byte) -43:
                return FIXEXT2;
            case (byte) -42:
                return FIXEXT4;
            case (byte) -41:
                return FIXEXT8;
            case (byte) -40:
                return FIXEXT16;
            case (byte) -39:
                return STR8;
            case (byte) -38:
                return STR16;
            case (byte) -37:
                return STR32;
            case (byte) -36:
                return ARRAY16;
            case (byte) -35:
                return ARRAY32;
            case (byte) -34:
                return MAP16;
            case (byte) -33:
                return MAP32;
            default:
                return NEVER_USED;
        }
    }
}
