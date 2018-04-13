package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.ServiceOptionsOrBuilder;
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

public final class DescriptorProtos$ServiceOptions extends ExtendableMessage<DescriptorProtos$ServiceOptions> implements ServiceOptionsOrBuilder {
    private static final DescriptorProtos$ServiceOptions DEFAULT_INSTANCE = new DescriptorProtos$ServiceOptions();
    public static final int DEPRECATED_FIELD_NUMBER = 33;
    @Deprecated
    public static final Parser<DescriptorProtos$ServiceOptions> PARSER = new DescriptorProtos$ServiceOptions$1();
    public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private boolean deprecated_;
    private byte memoizedIsInitialized;
    private List<DescriptorProtos$UninterpretedOption> uninterpretedOption_;

    private DescriptorProtos$ServiceOptions(ExtendableBuilder<DescriptorProtos$ServiceOptions, ?> extendableBuilder) {
        super(extendableBuilder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$ServiceOptions() {
        this.memoizedIsInitialized = (byte) -1;
        this.deprecated_ = false;
        this.uninterpretedOption_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DescriptorProtos$ServiceOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    case 264:
                        this.bitField0_ |= 1;
                        this.deprecated_ = codedInputStream.readBool();
                        break;
                    case 7994:
                        if ((i & 2) != 2) {
                            this.uninterpretedOption_ = new ArrayList();
                            i |= 2;
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
                if ((i & 2) == 2) {
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        if ((i & 2) == 2) {
            this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
        }
        this.unknownFields = newBuilder.build();
        makeExtensionsImmutable();
    }

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.access$21600();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$21700().ensureFieldAccessorsInitialized(DescriptorProtos$ServiceOptions.class, DescriptorProtos$ServiceOptions$Builder.class);
    }

    public boolean hasDeprecated() {
        return (this.bitField0_ & 1) == 1;
    }

    public boolean getDeprecated() {
        return this.deprecated_;
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
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeBool(33, this.deprecated_);
        }
        for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
            codedOutputStream.writeMessage(999, (MessageLite) this.uninterpretedOption_.get(i));
        }
        newExtensionWriter.writeUntil(SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING, codedOutputStream);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = 0;
        int i2 = this.memoizedSize;
        if (i2 != -1) {
            return i2;
        }
        if ((this.bitField0_ & 1) == 1) {
            i2 = CodedOutputStream.computeBoolSize(33, this.deprecated_) + 0;
        } else {
            i2 = 0;
        }
        int i3 = i2;
        while (i < this.uninterpretedOption_.size()) {
            i++;
            i3 = CodedOutputStream.computeMessageSize(999, (MessageLite) this.uninterpretedOption_.get(i)) + i3;
        }
        i2 = (extensionsSerializedSize() + i3) + this.unknownFields.getSerializedSize();
        this.memoizedSize = i2;
        return i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DescriptorProtos$ServiceOptions)) {
            return super.equals(obj);
        }
        DescriptorProtos$ServiceOptions descriptorProtos$ServiceOptions = (DescriptorProtos$ServiceOptions) obj;
        boolean z = hasDeprecated() == descriptorProtos$ServiceOptions.hasDeprecated();
        if (hasDeprecated()) {
            z = z && getDeprecated() == descriptorProtos$ServiceOptions.getDeprecated();
        }
        if (z && getUninterpretedOptionList().equals(descriptorProtos$ServiceOptions.getUninterpretedOptionList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.unknownFields.equals(descriptorProtos$ServiceOptions.unknownFields)) {
            z = true;
        } else {
            z = false;
        }
        if (z && getExtensionFields().equals(descriptorProtos$ServiceOptions.getExtensionFields())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (hasDeprecated()) {
            hashCode = (((hashCode * 37) + 33) * 53) + Internal.hashBoolean(getDeprecated());
        }
        if (getUninterpretedOptionCount() > 0) {
            hashCode = (((hashCode * 37) + 999) * 53) + getUninterpretedOptionList().hashCode();
        }
        hashCode = (hashFields(hashCode, getExtensionFields()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$ServiceOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ServiceOptions) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$ServiceOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ServiceOptions) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$ServiceOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ServiceOptions) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$ServiceOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ServiceOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$ServiceOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ServiceOptions) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$ServiceOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ServiceOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$ServiceOptions parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$ServiceOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$ServiceOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$ServiceOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$ServiceOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$ServiceOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$ServiceOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$ServiceOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$ServiceOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$ServiceOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$ServiceOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$ServiceOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$ServiceOptions$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$ServiceOptions$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$ServiceOptions$Builder newBuilder(DescriptorProtos$ServiceOptions descriptorProtos$ServiceOptions) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$ServiceOptions);
    }

    public DescriptorProtos$ServiceOptions$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$ServiceOptions$Builder(null);
        }
        return new DescriptorProtos$ServiceOptions$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$ServiceOptions$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$ServiceOptions$Builder(builderParent, null);
    }

    public static DescriptorProtos$ServiceOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$ServiceOptions> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$ServiceOptions> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$ServiceOptions getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
