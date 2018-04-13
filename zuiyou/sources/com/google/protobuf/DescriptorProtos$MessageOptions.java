package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.MessageOptionsOrBuilder;
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

public final class DescriptorProtos$MessageOptions extends ExtendableMessage<DescriptorProtos$MessageOptions> implements MessageOptionsOrBuilder {
    private static final DescriptorProtos$MessageOptions DEFAULT_INSTANCE = new DescriptorProtos$MessageOptions();
    public static final int DEPRECATED_FIELD_NUMBER = 3;
    public static final int MAP_ENTRY_FIELD_NUMBER = 7;
    public static final int MESSAGE_SET_WIRE_FORMAT_FIELD_NUMBER = 1;
    public static final int NO_STANDARD_DESCRIPTOR_ACCESSOR_FIELD_NUMBER = 2;
    @Deprecated
    public static final Parser<DescriptorProtos$MessageOptions> PARSER = new DescriptorProtos$MessageOptions$1();
    public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private boolean deprecated_;
    private boolean mapEntry_;
    private byte memoizedIsInitialized;
    private boolean messageSetWireFormat_;
    private boolean noStandardDescriptorAccessor_;
    private List<DescriptorProtos$UninterpretedOption> uninterpretedOption_;

    private DescriptorProtos$MessageOptions(ExtendableBuilder<DescriptorProtos$MessageOptions, ?> extendableBuilder) {
        super(extendableBuilder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$MessageOptions() {
        this.memoizedIsInitialized = (byte) -1;
        this.messageSetWireFormat_ = false;
        this.noStandardDescriptorAccessor_ = false;
        this.deprecated_ = false;
        this.mapEntry_ = false;
        this.uninterpretedOption_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DescriptorProtos$MessageOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    case 8:
                        this.bitField0_ |= 1;
                        this.messageSetWireFormat_ = codedInputStream.readBool();
                        break;
                    case 16:
                        this.bitField0_ |= 2;
                        this.noStandardDescriptorAccessor_ = codedInputStream.readBool();
                        break;
                    case 24:
                        this.bitField0_ |= 4;
                        this.deprecated_ = codedInputStream.readBool();
                        break;
                    case 56:
                        this.bitField0_ |= 8;
                        this.mapEntry_ = codedInputStream.readBool();
                        break;
                    case 7994:
                        if ((i & 16) != 16) {
                            this.uninterpretedOption_ = new ArrayList();
                            i |= 16;
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
                if ((i & 16) == 16) {
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        if ((i & 16) == 16) {
            this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
        }
        this.unknownFields = newBuilder.build();
        makeExtensionsImmutable();
    }

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.access$16400();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$16500().ensureFieldAccessorsInitialized(DescriptorProtos$MessageOptions.class, DescriptorProtos$MessageOptions$Builder.class);
    }

    public boolean hasMessageSetWireFormat() {
        return (this.bitField0_ & 1) == 1;
    }

    public boolean getMessageSetWireFormat() {
        return this.messageSetWireFormat_;
    }

    public boolean hasNoStandardDescriptorAccessor() {
        return (this.bitField0_ & 2) == 2;
    }

    public boolean getNoStandardDescriptorAccessor() {
        return this.noStandardDescriptorAccessor_;
    }

    public boolean hasDeprecated() {
        return (this.bitField0_ & 4) == 4;
    }

    public boolean getDeprecated() {
        return this.deprecated_;
    }

    public boolean hasMapEntry() {
        return (this.bitField0_ & 8) == 8;
    }

    public boolean getMapEntry() {
        return this.mapEntry_;
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
            codedOutputStream.writeBool(1, this.messageSetWireFormat_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeBool(2, this.noStandardDescriptorAccessor_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeBool(3, this.deprecated_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeBool(7, this.mapEntry_);
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
            i2 = CodedOutputStream.computeBoolSize(1, this.messageSetWireFormat_) + 0;
        } else {
            i2 = 0;
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeBoolSize(2, this.noStandardDescriptorAccessor_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeBoolSize(3, this.deprecated_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeBoolSize(7, this.mapEntry_);
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
        if (!(obj instanceof DescriptorProtos$MessageOptions)) {
            return super.equals(obj);
        }
        DescriptorProtos$MessageOptions descriptorProtos$MessageOptions = (DescriptorProtos$MessageOptions) obj;
        boolean z = hasMessageSetWireFormat() == descriptorProtos$MessageOptions.hasMessageSetWireFormat();
        if (hasMessageSetWireFormat()) {
            z = z && getMessageSetWireFormat() == descriptorProtos$MessageOptions.getMessageSetWireFormat();
        }
        if (z && hasNoStandardDescriptorAccessor() == descriptorProtos$MessageOptions.hasNoStandardDescriptorAccessor()) {
            z = true;
        } else {
            z = false;
        }
        if (hasNoStandardDescriptorAccessor()) {
            if (z && getNoStandardDescriptorAccessor() == descriptorProtos$MessageOptions.getNoStandardDescriptorAccessor()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasDeprecated() == descriptorProtos$MessageOptions.hasDeprecated()) {
            z = true;
        } else {
            z = false;
        }
        if (hasDeprecated()) {
            if (z && getDeprecated() == descriptorProtos$MessageOptions.getDeprecated()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasMapEntry() == descriptorProtos$MessageOptions.hasMapEntry()) {
            z = true;
        } else {
            z = false;
        }
        if (hasMapEntry()) {
            if (z && getMapEntry() == descriptorProtos$MessageOptions.getMapEntry()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && getUninterpretedOptionList().equals(descriptorProtos$MessageOptions.getUninterpretedOptionList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.unknownFields.equals(descriptorProtos$MessageOptions.unknownFields)) {
            z = true;
        } else {
            z = false;
        }
        if (z && getExtensionFields().equals(descriptorProtos$MessageOptions.getExtensionFields())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (hasMessageSetWireFormat()) {
            hashCode = (((hashCode * 37) + 1) * 53) + Internal.hashBoolean(getMessageSetWireFormat());
        }
        if (hasNoStandardDescriptorAccessor()) {
            hashCode = (((hashCode * 37) + 2) * 53) + Internal.hashBoolean(getNoStandardDescriptorAccessor());
        }
        if (hasDeprecated()) {
            hashCode = (((hashCode * 37) + 3) * 53) + Internal.hashBoolean(getDeprecated());
        }
        if (hasMapEntry()) {
            hashCode = (((hashCode * 37) + 7) * 53) + Internal.hashBoolean(getMapEntry());
        }
        if (getUninterpretedOptionCount() > 0) {
            hashCode = (((hashCode * 37) + 999) * 53) + getUninterpretedOptionList().hashCode();
        }
        hashCode = (hashFields(hashCode, getExtensionFields()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$MessageOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MessageOptions) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$MessageOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MessageOptions) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$MessageOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MessageOptions) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$MessageOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MessageOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$MessageOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MessageOptions) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$MessageOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MessageOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$MessageOptions parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$MessageOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$MessageOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$MessageOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$MessageOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$MessageOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$MessageOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$MessageOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$MessageOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$MessageOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$MessageOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$MessageOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$MessageOptions$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$MessageOptions$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$MessageOptions$Builder newBuilder(DescriptorProtos$MessageOptions descriptorProtos$MessageOptions) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$MessageOptions);
    }

    public DescriptorProtos$MessageOptions$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$MessageOptions$Builder(null);
        }
        return new DescriptorProtos$MessageOptions$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$MessageOptions$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$MessageOptions$Builder(builderParent, null);
    }

    public static DescriptorProtos$MessageOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$MessageOptions> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$MessageOptions> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$MessageOptions getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
