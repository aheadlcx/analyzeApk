package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.FileDescriptorSetOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.BuilderParent;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$FileDescriptorSet extends GeneratedMessageV3 implements FileDescriptorSetOrBuilder {
    private static final DescriptorProtos$FileDescriptorSet DEFAULT_INSTANCE = new DescriptorProtos$FileDescriptorSet();
    public static final int FILE_FIELD_NUMBER = 1;
    @Deprecated
    public static final Parser<DescriptorProtos$FileDescriptorSet> PARSER = new DescriptorProtos$FileDescriptorSet$1();
    private static final long serialVersionUID = 0;
    private List<DescriptorProtos$FileDescriptorProto> file_;
    private byte memoizedIsInitialized;

    private DescriptorProtos$FileDescriptorSet(Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$FileDescriptorSet() {
        this.memoizedIsInitialized = (byte) -1;
        this.file_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DescriptorProtos$FileDescriptorSet(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Object obj = null;
        this();
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        int i = 0;
        while (obj == null) {
            try {
                int readTag = codedInputStream.readTag();
                switch (readTag) {
                    case 0:
                        obj = 1;
                        break;
                    case 10:
                        if ((i & 1) != 1) {
                            this.file_ = new ArrayList();
                            i |= 1;
                        }
                        this.file_.add(codedInputStream.readMessage(DescriptorProtos$FileDescriptorProto.PARSER, extensionRegistryLite));
                        break;
                    default:
                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e2) {
                throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
            } catch (Throwable th) {
                if ((i & 1) == 1) {
                    this.file_ = Collections.unmodifiableList(this.file_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        if ((i & 1) == 1) {
            this.file_ = Collections.unmodifiableList(this.file_);
        }
        this.unknownFields = newBuilder.build();
        makeExtensionsImmutable();
    }

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.access$000();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$100().ensureFieldAccessorsInitialized(DescriptorProtos$FileDescriptorSet.class, DescriptorProtos$FileDescriptorSet$Builder.class);
    }

    public List<DescriptorProtos$FileDescriptorProto> getFileList() {
        return this.file_;
    }

    public List<? extends FileDescriptorProtoOrBuilder> getFileOrBuilderList() {
        return this.file_;
    }

    public int getFileCount() {
        return this.file_.size();
    }

    public DescriptorProtos$FileDescriptorProto getFile(int i) {
        return (DescriptorProtos$FileDescriptorProto) this.file_.get(i);
    }

    public FileDescriptorProtoOrBuilder getFileOrBuilder(int i) {
        return (FileDescriptorProtoOrBuilder) this.file_.get(i);
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == (byte) 1) {
            return true;
        }
        if (b == (byte) 0) {
            return false;
        }
        int i = 0;
        while (i < getFileCount()) {
            if (getFile(i).isInitialized()) {
                i++;
            } else {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.file_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.file_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (i = 0; i < this.file_.size(); i++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.file_.get(i));
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DescriptorProtos$FileDescriptorSet)) {
            return super.equals(obj);
        }
        boolean z;
        DescriptorProtos$FileDescriptorSet descriptorProtos$FileDescriptorSet = (DescriptorProtos$FileDescriptorSet) obj;
        if (getFileList().equals(descriptorProtos$FileDescriptorSet.getFileList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.unknownFields.equals(descriptorProtos$FileDescriptorSet.unknownFields)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (getFileCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getFileList().hashCode();
        }
        hashCode = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$FileDescriptorSet parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileDescriptorSet) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$FileDescriptorSet parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileDescriptorSet) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$FileDescriptorSet parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileDescriptorSet) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$FileDescriptorSet parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileDescriptorSet) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$FileDescriptorSet parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileDescriptorSet) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$FileDescriptorSet parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileDescriptorSet) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$FileDescriptorSet parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$FileDescriptorSet) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$FileDescriptorSet parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$FileDescriptorSet) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$FileDescriptorSet parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$FileDescriptorSet) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$FileDescriptorSet parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$FileDescriptorSet) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$FileDescriptorSet parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$FileDescriptorSet) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$FileDescriptorSet parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$FileDescriptorSet) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$FileDescriptorSet$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$FileDescriptorSet$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$FileDescriptorSet$Builder newBuilder(DescriptorProtos$FileDescriptorSet descriptorProtos$FileDescriptorSet) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$FileDescriptorSet);
    }

    public DescriptorProtos$FileDescriptorSet$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$FileDescriptorSet$Builder(null);
        }
        return new DescriptorProtos$FileDescriptorSet$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$FileDescriptorSet$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$FileDescriptorSet$Builder(builderParent, null);
    }

    public static DescriptorProtos$FileDescriptorSet getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$FileDescriptorSet> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$FileDescriptorSet> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$FileDescriptorSet getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
