package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.OneofDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.OneofOptionsOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.BuilderParent;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class DescriptorProtos$OneofDescriptorProto extends GeneratedMessageV3 implements OneofDescriptorProtoOrBuilder {
    private static final DescriptorProtos$OneofDescriptorProto DEFAULT_INSTANCE = new DescriptorProtos$OneofDescriptorProto();
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int OPTIONS_FIELD_NUMBER = 2;
    @Deprecated
    public static final Parser<DescriptorProtos$OneofDescriptorProto> PARSER = new DescriptorProtos$OneofDescriptorProto$1();
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private DescriptorProtos$OneofOptions options_;

    private DescriptorProtos$OneofDescriptorProto(Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$OneofDescriptorProto() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DescriptorProtos$OneofDescriptorProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    case 18:
                        DescriptorProtos$OneofOptions$Builder toBuilder;
                        if ((this.bitField0_ & 2) == 2) {
                            toBuilder = this.options_.toBuilder();
                        } else {
                            toBuilder = null;
                        }
                        this.options_ = (DescriptorProtos$OneofOptions) codedInputStream.readMessage(DescriptorProtos$OneofOptions.PARSER, extensionRegistryLite);
                        if (toBuilder != null) {
                            toBuilder.mergeFrom(this.options_);
                            this.options_ = toBuilder.buildPartial();
                        }
                        this.bitField0_ |= 2;
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
        return DescriptorProtos.access$8600();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$8700().ensureFieldAccessorsInitialized(DescriptorProtos$OneofDescriptorProto.class, DescriptorProtos$OneofDescriptorProto$Builder.class);
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

    public boolean hasOptions() {
        return (this.bitField0_ & 2) == 2;
    }

    public DescriptorProtos$OneofOptions getOptions() {
        return this.options_ == null ? DescriptorProtos$OneofOptions.getDefaultInstance() : this.options_;
    }

    public OneofOptionsOrBuilder getOptionsOrBuilder() {
        return this.options_ == null ? DescriptorProtos$OneofOptions.getDefaultInstance() : this.options_;
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
            codedOutputStream.writeMessage(2, getOptions());
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
            i += CodedOutputStream.computeMessageSize(2, getOptions());
        }
        i += this.unknownFields.getSerializedSize();
        this.memoizedSize = i;
        return i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DescriptorProtos$OneofDescriptorProto)) {
            return super.equals(obj);
        }
        DescriptorProtos$OneofDescriptorProto descriptorProtos$OneofDescriptorProto = (DescriptorProtos$OneofDescriptorProto) obj;
        boolean z = hasName() == descriptorProtos$OneofDescriptorProto.hasName();
        if (hasName()) {
            z = z && getName().equals(descriptorProtos$OneofDescriptorProto.getName());
        }
        if (z && hasOptions() == descriptorProtos$OneofDescriptorProto.hasOptions()) {
            z = true;
        } else {
            z = false;
        }
        if (hasOptions()) {
            if (z && getOptions().equals(descriptorProtos$OneofDescriptorProto.getOptions())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && this.unknownFields.equals(descriptorProtos$OneofDescriptorProto.unknownFields)) {
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
        if (hasOptions()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getOptions().hashCode();
        }
        hashCode = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$OneofDescriptorProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$OneofDescriptorProto) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$OneofDescriptorProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$OneofDescriptorProto) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$OneofDescriptorProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$OneofDescriptorProto) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$OneofDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$OneofDescriptorProto) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$OneofDescriptorProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$OneofDescriptorProto) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$OneofDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$OneofDescriptorProto) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$OneofDescriptorProto parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$OneofDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$OneofDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$OneofDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$OneofDescriptorProto parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$OneofDescriptorProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$OneofDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$OneofDescriptorProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$OneofDescriptorProto parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$OneofDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$OneofDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$OneofDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$OneofDescriptorProto$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$OneofDescriptorProto$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$OneofDescriptorProto$Builder newBuilder(DescriptorProtos$OneofDescriptorProto descriptorProtos$OneofDescriptorProto) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$OneofDescriptorProto);
    }

    public DescriptorProtos$OneofDescriptorProto$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$OneofDescriptorProto$Builder(null);
        }
        return new DescriptorProtos$OneofDescriptorProto$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$OneofDescriptorProto$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$OneofDescriptorProto$Builder(builderParent, null);
    }

    public static DescriptorProtos$OneofDescriptorProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$OneofDescriptorProto> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$OneofDescriptorProto> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$OneofDescriptorProto getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
