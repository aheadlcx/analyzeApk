package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.FieldDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.FieldOptionsOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.BuilderParent;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.Internal.EnumLiteMap;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class DescriptorProtos$FieldDescriptorProto extends GeneratedMessageV3 implements FieldDescriptorProtoOrBuilder {
    private static final DescriptorProtos$FieldDescriptorProto DEFAULT_INSTANCE = new DescriptorProtos$FieldDescriptorProto();
    public static final int DEFAULT_VALUE_FIELD_NUMBER = 7;
    public static final int EXTENDEE_FIELD_NUMBER = 2;
    public static final int JSON_NAME_FIELD_NUMBER = 10;
    public static final int LABEL_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int NUMBER_FIELD_NUMBER = 3;
    public static final int ONEOF_INDEX_FIELD_NUMBER = 9;
    public static final int OPTIONS_FIELD_NUMBER = 8;
    @Deprecated
    public static final Parser<DescriptorProtos$FieldDescriptorProto> PARSER = new DescriptorProtos$FieldDescriptorProto$1();
    public static final int TYPE_FIELD_NUMBER = 5;
    public static final int TYPE_NAME_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private volatile Object defaultValue_;
    private volatile Object extendee_;
    private volatile Object jsonName_;
    private int label_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private int number_;
    private int oneofIndex_;
    private DescriptorProtos$FieldOptions options_;
    private volatile Object typeName_;
    private int type_;

    public enum Label implements ProtocolMessageEnum {
        LABEL_OPTIONAL(1),
        LABEL_REQUIRED(2),
        LABEL_REPEATED(3);
        
        public static final int LABEL_OPTIONAL_VALUE = 1;
        public static final int LABEL_REPEATED_VALUE = 3;
        public static final int LABEL_REQUIRED_VALUE = 2;
        private static final Label[] VALUES = null;
        private static final EnumLiteMap<Label> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new DescriptorProtos$FieldDescriptorProto$Label$1();
            VALUES = values();
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Label valueOf(int i) {
            return forNumber(i);
        }

        public static Label forNumber(int i) {
            switch (i) {
                case 1:
                    return LABEL_OPTIONAL;
                case 2:
                    return LABEL_REQUIRED;
                case 3:
                    return LABEL_REPEATED;
                default:
                    return null;
            }
        }

        public static EnumLiteMap<Label> internalGetValueMap() {
            return internalValueMap;
        }

        public final EnumValueDescriptor getValueDescriptor() {
            return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final EnumDescriptor getDescriptor() {
            return (EnumDescriptor) DescriptorProtos$FieldDescriptorProto.getDescriptor().getEnumTypes().get(1);
        }

        public static Label valueOf(EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        private Label(int i) {
            this.value = i;
        }
    }

    public enum Type implements ProtocolMessageEnum {
        TYPE_DOUBLE(1),
        TYPE_FLOAT(2),
        TYPE_INT64(3),
        TYPE_UINT64(4),
        TYPE_INT32(5),
        TYPE_FIXED64(6),
        TYPE_FIXED32(7),
        TYPE_BOOL(8),
        TYPE_STRING(9),
        TYPE_GROUP(10),
        TYPE_MESSAGE(11),
        TYPE_BYTES(12),
        TYPE_UINT32(13),
        TYPE_ENUM(14),
        TYPE_SFIXED32(15),
        TYPE_SFIXED64(16),
        TYPE_SINT32(17),
        TYPE_SINT64(18);
        
        public static final int TYPE_BOOL_VALUE = 8;
        public static final int TYPE_BYTES_VALUE = 12;
        public static final int TYPE_DOUBLE_VALUE = 1;
        public static final int TYPE_ENUM_VALUE = 14;
        public static final int TYPE_FIXED32_VALUE = 7;
        public static final int TYPE_FIXED64_VALUE = 6;
        public static final int TYPE_FLOAT_VALUE = 2;
        public static final int TYPE_GROUP_VALUE = 10;
        public static final int TYPE_INT32_VALUE = 5;
        public static final int TYPE_INT64_VALUE = 3;
        public static final int TYPE_MESSAGE_VALUE = 11;
        public static final int TYPE_SFIXED32_VALUE = 15;
        public static final int TYPE_SFIXED64_VALUE = 16;
        public static final int TYPE_SINT32_VALUE = 17;
        public static final int TYPE_SINT64_VALUE = 18;
        public static final int TYPE_STRING_VALUE = 9;
        public static final int TYPE_UINT32_VALUE = 13;
        public static final int TYPE_UINT64_VALUE = 4;
        private static final Type[] VALUES = null;
        private static final EnumLiteMap<Type> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new DescriptorProtos$FieldDescriptorProto$Type$1();
            VALUES = values();
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Type valueOf(int i) {
            return forNumber(i);
        }

        public static Type forNumber(int i) {
            switch (i) {
                case 1:
                    return TYPE_DOUBLE;
                case 2:
                    return TYPE_FLOAT;
                case 3:
                    return TYPE_INT64;
                case 4:
                    return TYPE_UINT64;
                case 5:
                    return TYPE_INT32;
                case 6:
                    return TYPE_FIXED64;
                case 7:
                    return TYPE_FIXED32;
                case 8:
                    return TYPE_BOOL;
                case 9:
                    return TYPE_STRING;
                case 10:
                    return TYPE_GROUP;
                case 11:
                    return TYPE_MESSAGE;
                case 12:
                    return TYPE_BYTES;
                case 13:
                    return TYPE_UINT32;
                case 14:
                    return TYPE_ENUM;
                case 15:
                    return TYPE_SFIXED32;
                case 16:
                    return TYPE_SFIXED64;
                case 17:
                    return TYPE_SINT32;
                case 18:
                    return TYPE_SINT64;
                default:
                    return null;
            }
        }

        public static EnumLiteMap<Type> internalGetValueMap() {
            return internalValueMap;
        }

        public final EnumValueDescriptor getValueDescriptor() {
            return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final EnumDescriptor getDescriptor() {
            return (EnumDescriptor) DescriptorProtos$FieldDescriptorProto.getDescriptor().getEnumTypes().get(0);
        }

        public static Type valueOf(EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        private Type(int i) {
            this.value = i;
        }
    }

    private DescriptorProtos$FieldDescriptorProto(Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$FieldDescriptorProto() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.number_ = 0;
        this.label_ = 1;
        this.type_ = 1;
        this.typeName_ = "";
        this.extendee_ = "";
        this.defaultValue_ = "";
        this.oneofIndex_ = 0;
        this.jsonName_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DescriptorProtos$FieldDescriptorProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        Object obj = null;
        while (obj == null) {
            try {
                Object obj2;
                int readTag = codedInputStream.readTag();
                ByteString readBytes;
                switch (readTag) {
                    case 0:
                        readTag = 1;
                        break;
                    case 10:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 1;
                        this.name_ = readBytes;
                        obj2 = obj;
                        break;
                    case 18:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 32;
                        this.extendee_ = readBytes;
                        obj2 = obj;
                        break;
                    case 24:
                        this.bitField0_ |= 2;
                        this.number_ = codedInputStream.readInt32();
                        obj2 = obj;
                        break;
                    case 32:
                        readTag = codedInputStream.readEnum();
                        if (Label.valueOf(readTag) != null) {
                            this.bitField0_ |= 4;
                            this.label_ = readTag;
                            obj2 = obj;
                            break;
                        }
                        newBuilder.mergeVarintField(4, readTag);
                        obj2 = obj;
                        break;
                    case 40:
                        readTag = codedInputStream.readEnum();
                        if (Type.valueOf(readTag) != null) {
                            this.bitField0_ |= 8;
                            this.type_ = readTag;
                            obj2 = obj;
                            break;
                        }
                        newBuilder.mergeVarintField(5, readTag);
                        obj2 = obj;
                        break;
                    case 50:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 16;
                        this.typeName_ = readBytes;
                        obj2 = obj;
                        break;
                    case 58:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 64;
                        this.defaultValue_ = readBytes;
                        obj2 = obj;
                        break;
                    case 66:
                        DescriptorProtos$FieldOptions$Builder toBuilder;
                        if ((this.bitField0_ & 512) == 512) {
                            toBuilder = this.options_.toBuilder();
                        } else {
                            toBuilder = null;
                        }
                        this.options_ = (DescriptorProtos$FieldOptions) codedInputStream.readMessage(DescriptorProtos$FieldOptions.PARSER, extensionRegistryLite);
                        if (toBuilder != null) {
                            toBuilder.mergeFrom(this.options_);
                            this.options_ = toBuilder.buildPartial();
                        }
                        this.bitField0_ |= 512;
                        obj2 = obj;
                        break;
                    case 72:
                        this.bitField0_ |= 128;
                        this.oneofIndex_ = codedInputStream.readInt32();
                        obj2 = obj;
                        break;
                    case 82:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 256;
                        this.jsonName_ = readBytes;
                        obj2 = obj;
                        break;
                    default:
                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            obj2 = 1;
                            break;
                        } else {
                            obj2 = obj;
                            break;
                        }
                }
                obj = obj2;
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e2) {
                throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
            } catch (Throwable th) {
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        this.unknownFields = newBuilder.build();
        makeExtensionsImmutable();
    }

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.access$6900();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$7000().ensureFieldAccessorsInitialized(DescriptorProtos$FieldDescriptorProto.class, DescriptorProtos$FieldDescriptorProto$Builder.class);
    }

    public boolean hasName() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.name_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.name_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasNumber() {
        return (this.bitField0_ & 2) == 2;
    }

    public int getNumber() {
        return this.number_;
    }

    public boolean hasLabel() {
        return (this.bitField0_ & 4) == 4;
    }

    public Label getLabel() {
        Label valueOf = Label.valueOf(this.label_);
        return valueOf == null ? Label.LABEL_OPTIONAL : valueOf;
    }

    public boolean hasType() {
        return (this.bitField0_ & 8) == 8;
    }

    public Type getType() {
        Type valueOf = Type.valueOf(this.type_);
        return valueOf == null ? Type.TYPE_DOUBLE : valueOf;
    }

    public boolean hasTypeName() {
        return (this.bitField0_ & 16) == 16;
    }

    public String getTypeName() {
        Object obj = this.typeName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.typeName_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getTypeNameBytes() {
        Object obj = this.typeName_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.typeName_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasExtendee() {
        return (this.bitField0_ & 32) == 32;
    }

    public String getExtendee() {
        Object obj = this.extendee_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.extendee_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getExtendeeBytes() {
        Object obj = this.extendee_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.extendee_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasDefaultValue() {
        return (this.bitField0_ & 64) == 64;
    }

    public String getDefaultValue() {
        Object obj = this.defaultValue_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.defaultValue_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getDefaultValueBytes() {
        Object obj = this.defaultValue_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.defaultValue_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasOneofIndex() {
        return (this.bitField0_ & 128) == 128;
    }

    public int getOneofIndex() {
        return this.oneofIndex_;
    }

    public boolean hasJsonName() {
        return (this.bitField0_ & 256) == 256;
    }

    public String getJsonName() {
        Object obj = this.jsonName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.jsonName_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getJsonNameBytes() {
        Object obj = this.jsonName_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.jsonName_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasOptions() {
        return (this.bitField0_ & 512) == 512;
    }

    public DescriptorProtos$FieldOptions getOptions() {
        return this.options_ == null ? DescriptorProtos$FieldOptions.getDefaultInstance() : this.options_;
    }

    public FieldOptionsOrBuilder getOptionsOrBuilder() {
        return this.options_ == null ? DescriptorProtos$FieldOptions.getDefaultInstance() : this.options_;
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == (byte) 1) {
            return true;
        }
        if (b == (byte) 0) {
            return false;
        }
        if (!hasOptions() || getOptions().isInitialized()) {
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }
        this.memoizedIsInitialized = (byte) 0;
        return false;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if ((this.bitField0_ & 32) == 32) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.extendee_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(3, this.number_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeEnum(4, this.label_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeEnum(5, this.type_);
        }
        if ((this.bitField0_ & 16) == 16) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.typeName_);
        }
        if ((this.bitField0_ & 64) == 64) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.defaultValue_);
        }
        if ((this.bitField0_ & 512) == 512) {
            codedOutputStream.writeMessage(8, getOptions());
        }
        if ((this.bitField0_ & 128) == 128) {
            codedOutputStream.writeInt32(9, this.oneofIndex_);
        }
        if ((this.bitField0_ & 256) == 256) {
            GeneratedMessageV3.writeString(codedOutputStream, 10, this.jsonName_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if ((this.bitField0_ & 1) == 1) {
            i = 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
        }
        if ((this.bitField0_ & 32) == 32) {
            i += GeneratedMessageV3.computeStringSize(2, this.extendee_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i += CodedOutputStream.computeInt32Size(3, this.number_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i += CodedOutputStream.computeEnumSize(4, this.label_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i += CodedOutputStream.computeEnumSize(5, this.type_);
        }
        if ((this.bitField0_ & 16) == 16) {
            i += GeneratedMessageV3.computeStringSize(6, this.typeName_);
        }
        if ((this.bitField0_ & 64) == 64) {
            i += GeneratedMessageV3.computeStringSize(7, this.defaultValue_);
        }
        if ((this.bitField0_ & 512) == 512) {
            i += CodedOutputStream.computeMessageSize(8, getOptions());
        }
        if ((this.bitField0_ & 128) == 128) {
            i += CodedOutputStream.computeInt32Size(9, this.oneofIndex_);
        }
        if ((this.bitField0_ & 256) == 256) {
            i += GeneratedMessageV3.computeStringSize(10, this.jsonName_);
        }
        i += this.unknownFields.getSerializedSize();
        this.memoizedSize = i;
        return i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DescriptorProtos$FieldDescriptorProto)) {
            return super.equals(obj);
        }
        DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto = (DescriptorProtos$FieldDescriptorProto) obj;
        boolean z = hasName() == descriptorProtos$FieldDescriptorProto.hasName();
        if (hasName()) {
            z = z && getName().equals(descriptorProtos$FieldDescriptorProto.getName());
        }
        if (z && hasNumber() == descriptorProtos$FieldDescriptorProto.hasNumber()) {
            z = true;
        } else {
            z = false;
        }
        if (hasNumber()) {
            if (z && getNumber() == descriptorProtos$FieldDescriptorProto.getNumber()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasLabel() == descriptorProtos$FieldDescriptorProto.hasLabel()) {
            z = true;
        } else {
            z = false;
        }
        if (hasLabel()) {
            if (z && this.label_ == descriptorProtos$FieldDescriptorProto.label_) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasType() == descriptorProtos$FieldDescriptorProto.hasType()) {
            z = true;
        } else {
            z = false;
        }
        if (hasType()) {
            if (z && this.type_ == descriptorProtos$FieldDescriptorProto.type_) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasTypeName() == descriptorProtos$FieldDescriptorProto.hasTypeName()) {
            z = true;
        } else {
            z = false;
        }
        if (hasTypeName()) {
            if (z && getTypeName().equals(descriptorProtos$FieldDescriptorProto.getTypeName())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasExtendee() == descriptorProtos$FieldDescriptorProto.hasExtendee()) {
            z = true;
        } else {
            z = false;
        }
        if (hasExtendee()) {
            if (z && getExtendee().equals(descriptorProtos$FieldDescriptorProto.getExtendee())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasDefaultValue() == descriptorProtos$FieldDescriptorProto.hasDefaultValue()) {
            z = true;
        } else {
            z = false;
        }
        if (hasDefaultValue()) {
            if (z && getDefaultValue().equals(descriptorProtos$FieldDescriptorProto.getDefaultValue())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasOneofIndex() == descriptorProtos$FieldDescriptorProto.hasOneofIndex()) {
            z = true;
        } else {
            z = false;
        }
        if (hasOneofIndex()) {
            if (z && getOneofIndex() == descriptorProtos$FieldDescriptorProto.getOneofIndex()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasJsonName() == descriptorProtos$FieldDescriptorProto.hasJsonName()) {
            z = true;
        } else {
            z = false;
        }
        if (hasJsonName()) {
            if (z && getJsonName().equals(descriptorProtos$FieldDescriptorProto.getJsonName())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasOptions() == descriptorProtos$FieldDescriptorProto.hasOptions()) {
            z = true;
        } else {
            z = false;
        }
        if (hasOptions()) {
            if (z && getOptions().equals(descriptorProtos$FieldDescriptorProto.getOptions())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && this.unknownFields.equals(descriptorProtos$FieldDescriptorProto.unknownFields)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (hasName()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getName().hashCode();
        }
        if (hasNumber()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getNumber();
        }
        if (hasLabel()) {
            hashCode = (((hashCode * 37) + 4) * 53) + this.label_;
        }
        if (hasType()) {
            hashCode = (((hashCode * 37) + 5) * 53) + this.type_;
        }
        if (hasTypeName()) {
            hashCode = (((hashCode * 37) + 6) * 53) + getTypeName().hashCode();
        }
        if (hasExtendee()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getExtendee().hashCode();
        }
        if (hasDefaultValue()) {
            hashCode = (((hashCode * 37) + 7) * 53) + getDefaultValue().hashCode();
        }
        if (hasOneofIndex()) {
            hashCode = (((hashCode * 37) + 9) * 53) + getOneofIndex();
        }
        if (hasJsonName()) {
            hashCode = (((hashCode * 37) + 10) * 53) + getJsonName().hashCode();
        }
        if (hasOptions()) {
            hashCode = (((hashCode * 37) + 8) * 53) + getOptions().hashCode();
        }
        hashCode = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$FieldDescriptorProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FieldDescriptorProto) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$FieldDescriptorProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FieldDescriptorProto) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$FieldDescriptorProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FieldDescriptorProto) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$FieldDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FieldDescriptorProto) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$FieldDescriptorProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FieldDescriptorProto) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$FieldDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FieldDescriptorProto) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$FieldDescriptorProto parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$FieldDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$FieldDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$FieldDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$FieldDescriptorProto parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$FieldDescriptorProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$FieldDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$FieldDescriptorProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$FieldDescriptorProto parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$FieldDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$FieldDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$FieldDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$FieldDescriptorProto$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$FieldDescriptorProto$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$FieldDescriptorProto$Builder newBuilder(DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$FieldDescriptorProto);
    }

    public DescriptorProtos$FieldDescriptorProto$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$FieldDescriptorProto$Builder(null);
        }
        return new DescriptorProtos$FieldDescriptorProto$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$FieldDescriptorProto$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$FieldDescriptorProto$Builder(builderParent, null);
    }

    public static DescriptorProtos$FieldDescriptorProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$FieldDescriptorProto> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$FieldDescriptorProto> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$FieldDescriptorProto getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
