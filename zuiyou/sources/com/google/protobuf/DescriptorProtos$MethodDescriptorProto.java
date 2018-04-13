package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.MethodDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.MethodOptionsOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.BuilderParent;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class DescriptorProtos$MethodDescriptorProto extends GeneratedMessageV3 implements MethodDescriptorProtoOrBuilder {
    public static final int CLIENT_STREAMING_FIELD_NUMBER = 5;
    private static final DescriptorProtos$MethodDescriptorProto DEFAULT_INSTANCE = new DescriptorProtos$MethodDescriptorProto();
    public static final int INPUT_TYPE_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int OPTIONS_FIELD_NUMBER = 4;
    public static final int OUTPUT_TYPE_FIELD_NUMBER = 3;
    @Deprecated
    public static final Parser<DescriptorProtos$MethodDescriptorProto> PARSER = new DescriptorProtos$MethodDescriptorProto$1();
    public static final int SERVER_STREAMING_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private boolean clientStreaming_;
    private volatile Object inputType_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private DescriptorProtos$MethodOptions options_;
    private volatile Object outputType_;
    private boolean serverStreaming_;

    private DescriptorProtos$MethodDescriptorProto(Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$MethodDescriptorProto() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.inputType_ = "";
        this.outputType_ = "";
        this.clientStreaming_ = false;
        this.serverStreaming_ = false;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DescriptorProtos$MethodDescriptorProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        continue;
                    case 10:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 1;
                        this.name_ = readBytes;
                        obj2 = obj;
                        continue;
                    case 18:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 2;
                        this.inputType_ = readBytes;
                        obj2 = obj;
                        continue;
                    case 26:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 4;
                        this.outputType_ = readBytes;
                        obj2 = obj;
                        continue;
                    case 34:
                        DescriptorProtos$MethodOptions$Builder toBuilder;
                        if ((this.bitField0_ & 8) == 8) {
                            toBuilder = this.options_.toBuilder();
                        } else {
                            toBuilder = null;
                        }
                        this.options_ = (DescriptorProtos$MethodOptions) codedInputStream.readMessage(DescriptorProtos$MethodOptions.PARSER, extensionRegistryLite);
                        if (toBuilder != null) {
                            toBuilder.mergeFrom(this.options_);
                            this.options_ = toBuilder.buildPartial();
                        }
                        this.bitField0_ |= 8;
                        obj2 = obj;
                        continue;
                    case 40:
                        this.bitField0_ |= 16;
                        this.clientStreaming_ = codedInputStream.readBool();
                        obj2 = obj;
                        continue;
                    case 48:
                        this.bitField0_ |= 32;
                        this.serverStreaming_ = codedInputStream.readBool();
                        break;
                    default:
                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            obj2 = 1;
                            continue;
                        }
                        break;
                }
                obj2 = obj;
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
        return DescriptorProtos.access$12500();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$12600().ensureFieldAccessorsInitialized(DescriptorProtos$MethodDescriptorProto.class, DescriptorProtos$MethodDescriptorProto$Builder.class);
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

    public boolean hasInputType() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getInputType() {
        Object obj = this.inputType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.inputType_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getInputTypeBytes() {
        Object obj = this.inputType_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.inputType_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasOutputType() {
        return (this.bitField0_ & 4) == 4;
    }

    public String getOutputType() {
        Object obj = this.outputType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.outputType_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getOutputTypeBytes() {
        Object obj = this.outputType_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.outputType_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasOptions() {
        return (this.bitField0_ & 8) == 8;
    }

    public DescriptorProtos$MethodOptions getOptions() {
        return this.options_ == null ? DescriptorProtos$MethodOptions.getDefaultInstance() : this.options_;
    }

    public MethodOptionsOrBuilder getOptionsOrBuilder() {
        return this.options_ == null ? DescriptorProtos$MethodOptions.getDefaultInstance() : this.options_;
    }

    public boolean hasClientStreaming() {
        return (this.bitField0_ & 16) == 16;
    }

    public boolean getClientStreaming() {
        return this.clientStreaming_;
    }

    public boolean hasServerStreaming() {
        return (this.bitField0_ & 32) == 32;
    }

    public boolean getServerStreaming() {
        return this.serverStreaming_;
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
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.inputType_);
        }
        if ((this.bitField0_ & 4) == 4) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.outputType_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeMessage(4, getOptions());
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeBool(5, this.clientStreaming_);
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeBool(6, this.serverStreaming_);
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
            i += GeneratedMessageV3.computeStringSize(2, this.inputType_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i += GeneratedMessageV3.computeStringSize(3, this.outputType_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i += CodedOutputStream.computeMessageSize(4, getOptions());
        }
        if ((this.bitField0_ & 16) == 16) {
            i += CodedOutputStream.computeBoolSize(5, this.clientStreaming_);
        }
        if ((this.bitField0_ & 32) == 32) {
            i += CodedOutputStream.computeBoolSize(6, this.serverStreaming_);
        }
        i += this.unknownFields.getSerializedSize();
        this.memoizedSize = i;
        return i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DescriptorProtos$MethodDescriptorProto)) {
            return super.equals(obj);
        }
        DescriptorProtos$MethodDescriptorProto descriptorProtos$MethodDescriptorProto = (DescriptorProtos$MethodDescriptorProto) obj;
        boolean z = hasName() == descriptorProtos$MethodDescriptorProto.hasName();
        if (hasName()) {
            z = z && getName().equals(descriptorProtos$MethodDescriptorProto.getName());
        }
        if (z && hasInputType() == descriptorProtos$MethodDescriptorProto.hasInputType()) {
            z = true;
        } else {
            z = false;
        }
        if (hasInputType()) {
            if (z && getInputType().equals(descriptorProtos$MethodDescriptorProto.getInputType())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasOutputType() == descriptorProtos$MethodDescriptorProto.hasOutputType()) {
            z = true;
        } else {
            z = false;
        }
        if (hasOutputType()) {
            if (z && getOutputType().equals(descriptorProtos$MethodDescriptorProto.getOutputType())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasOptions() == descriptorProtos$MethodDescriptorProto.hasOptions()) {
            z = true;
        } else {
            z = false;
        }
        if (hasOptions()) {
            if (z && getOptions().equals(descriptorProtos$MethodDescriptorProto.getOptions())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasClientStreaming() == descriptorProtos$MethodDescriptorProto.hasClientStreaming()) {
            z = true;
        } else {
            z = false;
        }
        if (hasClientStreaming()) {
            if (z && getClientStreaming() == descriptorProtos$MethodDescriptorProto.getClientStreaming()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasServerStreaming() == descriptorProtos$MethodDescriptorProto.hasServerStreaming()) {
            z = true;
        } else {
            z = false;
        }
        if (hasServerStreaming()) {
            if (z && getServerStreaming() == descriptorProtos$MethodDescriptorProto.getServerStreaming()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && this.unknownFields.equals(descriptorProtos$MethodDescriptorProto.unknownFields)) {
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
        if (hasInputType()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getInputType().hashCode();
        }
        if (hasOutputType()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getOutputType().hashCode();
        }
        if (hasOptions()) {
            hashCode = (((hashCode * 37) + 4) * 53) + getOptions().hashCode();
        }
        if (hasClientStreaming()) {
            hashCode = (((hashCode * 37) + 5) * 53) + Internal.hashBoolean(getClientStreaming());
        }
        if (hasServerStreaming()) {
            hashCode = (((hashCode * 37) + 6) * 53) + Internal.hashBoolean(getServerStreaming());
        }
        hashCode = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$MethodDescriptorProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MethodDescriptorProto) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$MethodDescriptorProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MethodDescriptorProto) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$MethodDescriptorProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MethodDescriptorProto) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$MethodDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MethodDescriptorProto) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$MethodDescriptorProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MethodDescriptorProto) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$MethodDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MethodDescriptorProto) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$MethodDescriptorProto parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$MethodDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$MethodDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$MethodDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$MethodDescriptorProto parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$MethodDescriptorProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$MethodDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$MethodDescriptorProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$MethodDescriptorProto parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$MethodDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$MethodDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$MethodDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$MethodDescriptorProto$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$MethodDescriptorProto$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$MethodDescriptorProto$Builder newBuilder(DescriptorProtos$MethodDescriptorProto descriptorProtos$MethodDescriptorProto) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$MethodDescriptorProto);
    }

    public DescriptorProtos$MethodDescriptorProto$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$MethodDescriptorProto$Builder(null);
        }
        return new DescriptorProtos$MethodDescriptorProto$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$MethodDescriptorProto$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$MethodDescriptorProto$Builder(builderParent, null);
    }

    public static DescriptorProtos$MethodDescriptorProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$MethodDescriptorProto> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$MethodDescriptorProto> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$MethodDescriptorProto getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
