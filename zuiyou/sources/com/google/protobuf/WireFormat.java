package com.google.protobuf;

import java.io.IOException;

public final class WireFormat {
    static final int FIXED32_SIZE = 4;
    static final int FIXED64_SIZE = 8;
    static final int MAX_VARINT32_SIZE = 5;
    static final int MAX_VARINT64_SIZE = 10;
    static final int MAX_VARINT_SIZE = 10;
    static final int MESSAGE_SET_ITEM = 1;
    static final int MESSAGE_SET_ITEM_END_TAG = makeTag(1, 4);
    static final int MESSAGE_SET_ITEM_TAG = makeTag(1, 3);
    static final int MESSAGE_SET_MESSAGE = 3;
    static final int MESSAGE_SET_MESSAGE_TAG = makeTag(3, 2);
    static final int MESSAGE_SET_TYPE_ID = 2;
    static final int MESSAGE_SET_TYPE_ID_TAG = makeTag(2, 0);
    static final int TAG_TYPE_BITS = 3;
    static final int TAG_TYPE_MASK = 7;
    public static final int WIRETYPE_END_GROUP = 4;
    public static final int WIRETYPE_FIXED32 = 5;
    public static final int WIRETYPE_FIXED64 = 1;
    public static final int WIRETYPE_LENGTH_DELIMITED = 2;
    public static final int WIRETYPE_START_GROUP = 3;
    public static final int WIRETYPE_VARINT = 0;

    public enum FieldType {
        DOUBLE(JavaType.DOUBLE, 1),
        FLOAT(JavaType.FLOAT, 5),
        INT64(JavaType.LONG, 0),
        UINT64(JavaType.LONG, 0),
        INT32(JavaType.INT, 0),
        FIXED64(JavaType.LONG, 1),
        FIXED32(JavaType.INT, 5),
        BOOL(JavaType.BOOLEAN, 0),
        STRING(JavaType.STRING, 2) {
            public boolean isPackable() {
                return false;
            }
        },
        GROUP(JavaType.MESSAGE, 3) {
            public boolean isPackable() {
                return false;
            }
        },
        MESSAGE(JavaType.MESSAGE, 2) {
            public boolean isPackable() {
                return false;
            }
        },
        BYTES(JavaType.BYTE_STRING, 2) {
            public boolean isPackable() {
                return false;
            }
        },
        UINT32(JavaType.INT, 0),
        ENUM(JavaType.ENUM, 0),
        SFIXED32(JavaType.INT, 5),
        SFIXED64(JavaType.LONG, 1),
        SINT32(JavaType.INT, 0),
        SINT64(JavaType.LONG, 0);
        
        private final JavaType javaType;
        private final int wireType;

        private FieldType(JavaType javaType, int i) {
            this.javaType = javaType;
            this.wireType = i;
        }

        public JavaType getJavaType() {
            return this.javaType;
        }

        public int getWireType() {
            return this.wireType;
        }

        public boolean isPackable() {
            return true;
        }
    }

    public enum JavaType {
        INT(Integer.valueOf(0)),
        LONG(Long.valueOf(0)),
        FLOAT(Float.valueOf(0.0f)),
        DOUBLE(Double.valueOf(0.0d)),
        BOOLEAN(Boolean.valueOf(false)),
        STRING(""),
        BYTE_STRING(ByteString.EMPTY),
        ENUM(null),
        MESSAGE(null);
        
        private final Object defaultDefault;

        private JavaType(Object obj) {
            this.defaultDefault = obj;
        }

        Object getDefaultDefault() {
            return this.defaultDefault;
        }
    }

    enum Utf8Validation {
        LOOSE {
            Object readString(CodedInputStream codedInputStream) throws IOException {
                return codedInputStream.readString();
            }
        },
        STRICT {
            Object readString(CodedInputStream codedInputStream) throws IOException {
                return codedInputStream.readStringRequireUtf8();
            }
        },
        LAZY {
            Object readString(CodedInputStream codedInputStream) throws IOException {
                return codedInputStream.readBytes();
            }
        };

        abstract Object readString(CodedInputStream codedInputStream) throws IOException;
    }

    private WireFormat() {
    }

    public static int getTagWireType(int i) {
        return i & 7;
    }

    public static int getTagFieldNumber(int i) {
        return i >>> 3;
    }

    static int makeTag(int i, int i2) {
        return (i << 3) | i2;
    }

    static Object readPrimitiveField(CodedInputStream codedInputStream, FieldType fieldType, Utf8Validation utf8Validation) throws IOException {
        switch (fieldType) {
            case DOUBLE:
                return Double.valueOf(codedInputStream.readDouble());
            case FLOAT:
                return Float.valueOf(codedInputStream.readFloat());
            case INT64:
                return Long.valueOf(codedInputStream.readInt64());
            case UINT64:
                return Long.valueOf(codedInputStream.readUInt64());
            case INT32:
                return Integer.valueOf(codedInputStream.readInt32());
            case FIXED64:
                return Long.valueOf(codedInputStream.readFixed64());
            case FIXED32:
                return Integer.valueOf(codedInputStream.readFixed32());
            case BOOL:
                return Boolean.valueOf(codedInputStream.readBool());
            case BYTES:
                return codedInputStream.readBytes();
            case UINT32:
                return Integer.valueOf(codedInputStream.readUInt32());
            case SFIXED32:
                return Integer.valueOf(codedInputStream.readSFixed32());
            case SFIXED64:
                return Long.valueOf(codedInputStream.readSFixed64());
            case SINT32:
                return Integer.valueOf(codedInputStream.readSInt32());
            case SINT64:
                return Long.valueOf(codedInputStream.readSInt64());
            case STRING:
                return utf8Validation.readString(codedInputStream);
            case GROUP:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            case MESSAGE:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            case ENUM:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }
}
