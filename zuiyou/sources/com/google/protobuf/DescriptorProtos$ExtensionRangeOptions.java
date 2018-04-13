package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.ExtensionRangeOptionsOrBuilder;
import com.google.protobuf.DescriptorProtos.UninterpretedOptionOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.GeneratedMessageV3.BuilderParent;
import com.google.protobuf.GeneratedMessageV3.ExtendableBuilder;
import com.google.protobuf.GeneratedMessageV3.ExtendableMessage;
import com.google.protobuf.GeneratedMessageV3.ExtendableMessage.ExtensionWriter;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.UnknownFieldSet.Builder;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$ExtensionRangeOptions extends ExtendableMessage<DescriptorProtos$ExtensionRangeOptions> implements ExtensionRangeOptionsOrBuilder {
    private static final DescriptorProtos$ExtensionRangeOptions DEFAULT_INSTANCE = new DescriptorProtos$ExtensionRangeOptions();
    @Deprecated
    public static final Parser<DescriptorProtos$ExtensionRangeOptions> PARSER = new DescriptorProtos$ExtensionRangeOptions$1();
    public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<DescriptorProtos$UninterpretedOption> uninterpretedOption_;

    private DescriptorProtos$ExtensionRangeOptions(ExtendableBuilder<DescriptorProtos$ExtensionRangeOptions, ?> extendableBuilder) {
        super(extendableBuilder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$ExtensionRangeOptions() {
        this.memoizedIsInitialized = (byte) -1;
        this.uninterpretedOption_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DescriptorProtos$ExtensionRangeOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Object obj = null;
        this();
        Builder newBuilder = UnknownFieldSet.newBuilder();
        int i = 0;
        while (obj == null) {
            try {
                int readTag = codedInputStream.readTag();
                switch (readTag) {
                    case 0:
                        obj = 1;
                        break;
                    case 7994:
                        if ((i & 1) != 1) {
                            this.uninterpretedOption_ = new ArrayList();
                            i |= 1;
                        }
                        this.uninterpretedOption_.add(codedInputStream.readMessage(DescriptorProtos$UninterpretedOption.PARSER, extensionRegistryLite));
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
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        if ((i & 1) == 1) {
            this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
        }
        this.unknownFields = newBuilder.build();
        makeExtensionsImmutable();
    }

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.access$6200();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$6300().ensureFieldAccessorsInitialized(DescriptorProtos$ExtensionRangeOptions.class, DescriptorProtos$ExtensionRangeOptions$Builder.class);
    }

    public List<DescriptorProtos$UninterpretedOption> getUninterpretedOptionList() {
        return this.uninterpretedOption_;
    }

    public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
        return this.uninterpretedOption_;
    }

    public int getUninterpretedOptionCount() {
        return this.uninterpretedOption_.size();
    }

    public DescriptorProtos$UninterpretedOption getUninterpretedOption(int i) {
        return (DescriptorProtos$UninterpretedOption) this.uninterpretedOption_.get(i);
    }

    public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
        return (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i);
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
        while (i < getUninterpretedOptionCount()) {
            if (getUninterpretedOption(i).isInitialized()) {
                i++;
            } else {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        if (extensionsAreInitialized()) {
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }
        this.memoizedIsInitialized = (byte) 0;
        return false;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        ExtensionWriter newExtensionWriter = newExtensionWriter();
        for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
            codedOutputStream.writeMessage(999, (MessageLite) this.uninterpretedOption_.get(i));
        }
        newExtensionWriter.writeUntil(SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING, codedOutputStream);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (i = 0; i < this.uninterpretedOption_.size(); i++) {
            i2 += CodedOutputStream.computeMessageSize(999, (MessageLite) this.uninterpretedOption_.get(i));
        }
        int extensionsSerializedSize = (extensionsSerializedSize() + i2) + this.unknownFields.getSerializedSize();
        this.memoizedSize = extensionsSerializedSize;
        return extensionsSerializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DescriptorProtos$ExtensionRangeOptions)) {
            return super.equals(obj);
        }
        boolean z;
        DescriptorProtos$ExtensionRangeOptions descriptorProtos$ExtensionRangeOptions = (DescriptorProtos$ExtensionRangeOptions) obj;
        if (getUninterpretedOptionList().equals(descriptorProtos$ExtensionRangeOptions.getUninterpretedOptionList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.unknownFields.equals(descriptorProtos$ExtensionRangeOptions.unknownFields)) {
            z = true;
        } else {
            z = false;
        }
        if (z && getExtensionFields().equals(descriptorProtos$ExtensionRangeOptions.getExtensionFields())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (getUninterpretedOptionCount() > 0) {
            hashCode = (((hashCode * 37) + 999) * 53) + getUninterpretedOptionList().hashCode();
        }
        hashCode = (hashFields(hashCode, getExtensionFields()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$ExtensionRangeOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ExtensionRangeOptions) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$ExtensionRangeOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ExtensionRangeOptions) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$ExtensionRangeOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ExtensionRangeOptions) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$ExtensionRangeOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ExtensionRangeOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$ExtensionRangeOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ExtensionRangeOptions) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$ExtensionRangeOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ExtensionRangeOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$ExtensionRangeOptions parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$ExtensionRangeOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$ExtensionRangeOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$ExtensionRangeOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$ExtensionRangeOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$ExtensionRangeOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$ExtensionRangeOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$ExtensionRangeOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$ExtensionRangeOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$ExtensionRangeOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$ExtensionRangeOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$ExtensionRangeOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$ExtensionRangeOptions$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$ExtensionRangeOptions$Builder newBuilder(DescriptorProtos$ExtensionRangeOptions descriptorProtos$ExtensionRangeOptions) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$ExtensionRangeOptions);
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$ExtensionRangeOptions$Builder(null);
        }
        return new DescriptorProtos$ExtensionRangeOptions$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$ExtensionRangeOptions$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$ExtensionRangeOptions$Builder(builderParent, null);
    }

    public static DescriptorProtos$ExtensionRangeOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$ExtensionRangeOptions> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$ExtensionRangeOptions> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$ExtensionRangeOptions getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
