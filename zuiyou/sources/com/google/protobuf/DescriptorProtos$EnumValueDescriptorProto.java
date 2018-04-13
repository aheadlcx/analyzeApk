package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.EnumValueDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.EnumValueOptionsOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.BuilderParent;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class DescriptorProtos$EnumValueDescriptorProto extends GeneratedMessageV3 implements EnumValueDescriptorProtoOrBuilder {
    private static final DescriptorProtos$EnumValueDescriptorProto DEFAULT_INSTANCE = new DescriptorProtos$EnumValueDescriptorProto();
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int NUMBER_FIELD_NUMBER = 2;
    public static final int OPTIONS_FIELD_NUMBER = 3;
    @Deprecated
    public static final Parser<DescriptorProtos$EnumValueDescriptorProto> PARSER = new DescriptorProtos$EnumValueDescriptorProto$1();
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private int number_;
    private DescriptorProtos$EnumValueOptions options_;

    private DescriptorProtos$EnumValueDescriptorProto(Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$EnumValueDescriptorProto() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.number_ = 0;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DescriptorProtos$EnumValueDescriptorProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        Object obj = null;
        while (obj == null) {
            try {
                Object obj2;
                int readTag = codedInputStream.readTag();
                switch (readTag) {
                    case 0:
                        readTag = 1;
                        break;
                    case 10:
                        ByteString readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 1;
                        this.name_ = readBytes;
                        obj2 = obj;
                        break;
                    case 16:
                        this.bitField0_ |= 2;
                        this.number_ = codedInputStream.readInt32();
                        obj2 = obj;
                        break;
                    case 26:
                        DescriptorProtos$EnumValueOptions$Builder toBuilder;
                        if ((this.bitField0_ & 4) == 4) {
                            toBuilder = this.options_.toBuilder();
                        } else {
                            toBuilder = null;
                        }
                        this.options_ = (DescriptorProtos$EnumValueOptions) codedInputStream.readMessage(DescriptorProtos$EnumValueOptions.PARSER, extensionRegistryLite);
                        if (toBuilder != null) {
                            toBuilder.mergeFrom(this.options_);
                            this.options_ = toBuilder.buildPartial();
                        }
                        this.bitField0_ |= 4;
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
        return DescriptorProtos.access$10500();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$10600().ensureFieldAccessorsInitialized(DescriptorProtos$EnumValueDescriptorProto.class, DescriptorProtos$EnumValueDescriptorProto$Builder.class);
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

    public boolean hasOptions() {
        return (this.bitField0_ & 4) == 4;
    }

    public DescriptorProtos$EnumValueOptions getOptions() {
        return this.options_ == null ? DescriptorProtos$EnumValueOptions.getDefaultInstance() : this.options_;
    }

    public EnumValueOptionsOrBuilder getOptionsOrBuilder() {
        return this.options_ == null ? DescriptorProtos$EnumValueOptions.getDefaultInstance() : this.options_;
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
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(2, this.number_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeMessage(3, getOptions());
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
        if ((this.bitField0_ & 2) == 2) {
            i += CodedOutputStream.computeInt32Size(2, this.number_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i += CodedOutputStream.computeMessageSize(3, getOptions());
        }
        i += this.unknownFields.getSerializedSize();
        this.memoizedSize = i;
        return i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DescriptorProtos$EnumValueDescriptorProto)) {
            return super.equals(obj);
        }
        DescriptorProtos$EnumValueDescriptorProto descriptorProtos$EnumValueDescriptorProto = (DescriptorProtos$EnumValueDescriptorProto) obj;
        boolean z = hasName() == descriptorProtos$EnumValueDescriptorProto.hasName();
        if (hasName()) {
            z = z && getName().equals(descriptorProtos$EnumValueDescriptorProto.getName());
        }
        if (z && hasNumber() == descriptorProtos$EnumValueDescriptorProto.hasNumber()) {
            z = true;
        } else {
            z = false;
        }
        if (hasNumber()) {
            if (z && getNumber() == descriptorProtos$EnumValueDescriptorProto.getNumber()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasOptions() == descriptorProtos$EnumValueDescriptorProto.hasOptions()) {
            z = true;
        } else {
            z = false;
        }
        if (hasOptions()) {
            if (z && getOptions().equals(descriptorProtos$EnumValueDescriptorProto.getOptions())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && this.unknownFields.equals(descriptorProtos$EnumValueDescriptorProto.unknownFields)) {
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
            hashCode = (((hashCode * 37) + 2) * 53) + getNumber();
        }
        if (hasOptions()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getOptions().hashCode();
        }
        hashCode = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$EnumValueDescriptorProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$EnumValueDescriptorProto) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$EnumValueDescriptorProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$EnumValueDescriptorProto) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$EnumValueDescriptorProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$EnumValueDescriptorProto) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$EnumValueDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$EnumValueDescriptorProto) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$EnumValueDescriptorProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$EnumValueDescriptorProto) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$EnumValueDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$EnumValueDescriptorProto) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$EnumValueDescriptorProto parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$EnumValueDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$EnumValueDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$EnumValueDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$EnumValueDescriptorProto parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$EnumValueDescriptorProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$EnumValueDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$EnumValueDescriptorProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$EnumValueDescriptorProto parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$EnumValueDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$EnumValueDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$EnumValueDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$EnumValueDescriptorProto$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$EnumValueDescriptorProto$Builder newBuilder(DescriptorProtos$EnumValueDescriptorProto descriptorProtos$EnumValueDescriptorProto) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$EnumValueDescriptorProto);
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$EnumValueDescriptorProto$Builder(null);
        }
        return new DescriptorProtos$EnumValueDescriptorProto$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$EnumValueDescriptorProto$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$EnumValueDescriptorProto$Builder(builderParent, null);
    }

    public static DescriptorProtos$EnumValueDescriptorProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$EnumValueDescriptorProto> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$EnumValueDescriptorProto> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$EnumValueDescriptorProto getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
