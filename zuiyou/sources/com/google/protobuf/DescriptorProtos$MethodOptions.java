package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.MethodOptionsOrBuilder;
import com.google.protobuf.DescriptorProtos.UninterpretedOptionOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.GeneratedMessageV3.BuilderParent;
import com.google.protobuf.GeneratedMessageV3.ExtendableBuilder;
import com.google.protobuf.GeneratedMessageV3.ExtendableMessage;
import com.google.protobuf.GeneratedMessageV3.ExtendableMessage.ExtensionWriter;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.UnknownFieldSet.Builder;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$MethodOptions extends ExtendableMessage<DescriptorProtos$MethodOptions> implements MethodOptionsOrBuilder {
    private static final DescriptorProtos$MethodOptions DEFAULT_INSTANCE = new DescriptorProtos$MethodOptions();
    public static final int DEPRECATED_FIELD_NUMBER = 33;
    public static final int IDEMPOTENCY_LEVEL_FIELD_NUMBER = 34;
    @Deprecated
    public static final Parser<DescriptorProtos$MethodOptions> PARSER = new DescriptorProtos$MethodOptions$1();
    public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private boolean deprecated_;
    private int idempotencyLevel_;
    private byte memoizedIsInitialized;
    private List<DescriptorProtos$UninterpretedOption> uninterpretedOption_;

    public enum IdempotencyLevel implements ProtocolMessageEnum {
        IDEMPOTENCY_UNKNOWN(0),
        NO_SIDE_EFFECTS(1),
        IDEMPOTENT(2);
        
        public static final int IDEMPOTENCY_UNKNOWN_VALUE = 0;
        public static final int IDEMPOTENT_VALUE = 2;
        public static final int NO_SIDE_EFFECTS_VALUE = 1;
        private static final IdempotencyLevel[] VALUES = null;
        private static final EnumLiteMap<IdempotencyLevel> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new DescriptorProtos$MethodOptions$IdempotencyLevel$1();
            VALUES = values();
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static IdempotencyLevel valueOf(int i) {
            return forNumber(i);
        }

        public static IdempotencyLevel forNumber(int i) {
            switch (i) {
                case 0:
                    return IDEMPOTENCY_UNKNOWN;
                case 1:
                    return NO_SIDE_EFFECTS;
                case 2:
                    return IDEMPOTENT;
                default:
                    return null;
            }
        }

        public static EnumLiteMap<IdempotencyLevel> internalGetValueMap() {
            return internalValueMap;
        }

        public final EnumValueDescriptor getValueDescriptor() {
            return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final EnumDescriptor getDescriptor() {
            return (EnumDescriptor) DescriptorProtos$MethodOptions.getDescriptor().getEnumTypes().get(0);
        }

        public static IdempotencyLevel valueOf(EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        private IdempotencyLevel(int i) {
            this.value = i;
        }
    }

    private DescriptorProtos$MethodOptions(ExtendableBuilder<DescriptorProtos$MethodOptions, ?> extendableBuilder) {
        super(extendableBuilder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$MethodOptions() {
        this.memoizedIsInitialized = (byte) -1;
        this.deprecated_ = false;
        this.idempotencyLevel_ = 0;
        this.uninterpretedOption_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DescriptorProtos$MethodOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    case 272:
                        readTag = codedInputStream.readEnum();
                        if (IdempotencyLevel.valueOf(readTag) != null) {
                            this.bitField0_ |= 2;
                            this.idempotencyLevel_ = readTag;
                            break;
                        }
                        newBuilder.mergeVarintField(34, readTag);
                        break;
                    case 7994:
                        if ((i & 4) != 4) {
                            this.uninterpretedOption_ = new ArrayList();
                            i |= 4;
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
                if ((i & 4) == 4) {
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        if ((i & 4) == 4) {
            this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
        }
        this.unknownFields = newBuilder.build();
        makeExtensionsImmutable();
    }

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.access$22500();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$22600().ensureFieldAccessorsInitialized(DescriptorProtos$MethodOptions.class, DescriptorProtos$MethodOptions$Builder.class);
    }

    public boolean hasDeprecated() {
        return (this.bitField0_ & 1) == 1;
    }

    public boolean getDeprecated() {
        return this.deprecated_;
    }

    public boolean hasIdempotencyLevel() {
        return (this.bitField0_ & 2) == 2;
    }

    public IdempotencyLevel getIdempotencyLevel() {
        IdempotencyLevel valueOf = IdempotencyLevel.valueOf(this.idempotencyLevel_);
        return valueOf == null ? IdempotencyLevel.IDEMPOTENCY_UNKNOWN : valueOf;
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
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeEnum(34, this.idempotencyLevel_);
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
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeEnumSize(34, this.idempotencyLevel_);
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
        if (!(obj instanceof DescriptorProtos$MethodOptions)) {
            return super.equals(obj);
        }
        DescriptorProtos$MethodOptions descriptorProtos$MethodOptions = (DescriptorProtos$MethodOptions) obj;
        boolean z = hasDeprecated() == descriptorProtos$MethodOptions.hasDeprecated();
        if (hasDeprecated()) {
            z = z && getDeprecated() == descriptorProtos$MethodOptions.getDeprecated();
        }
        if (z && hasIdempotencyLevel() == descriptorProtos$MethodOptions.hasIdempotencyLevel()) {
            z = true;
        } else {
            z = false;
        }
        if (hasIdempotencyLevel()) {
            if (z && this.idempotencyLevel_ == descriptorProtos$MethodOptions.idempotencyLevel_) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && getUninterpretedOptionList().equals(descriptorProtos$MethodOptions.getUninterpretedOptionList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.unknownFields.equals(descriptorProtos$MethodOptions.unknownFields)) {
            z = true;
        } else {
            z = false;
        }
        if (z && getExtensionFields().equals(descriptorProtos$MethodOptions.getExtensionFields())) {
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
        if (hasIdempotencyLevel()) {
            hashCode = (((hashCode * 37) + 34) * 53) + this.idempotencyLevel_;
        }
        if (getUninterpretedOptionCount() > 0) {
            hashCode = (((hashCode * 37) + 999) * 53) + getUninterpretedOptionList().hashCode();
        }
        hashCode = (hashFields(hashCode, getExtensionFields()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$MethodOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MethodOptions) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$MethodOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MethodOptions) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$MethodOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MethodOptions) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$MethodOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MethodOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$MethodOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MethodOptions) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$MethodOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$MethodOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$MethodOptions parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$MethodOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$MethodOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$MethodOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$MethodOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$MethodOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$MethodOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$MethodOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$MethodOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$MethodOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$MethodOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$MethodOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$MethodOptions$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$MethodOptions$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$MethodOptions$Builder newBuilder(DescriptorProtos$MethodOptions descriptorProtos$MethodOptions) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$MethodOptions);
    }

    public DescriptorProtos$MethodOptions$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$MethodOptions$Builder(null);
        }
        return new DescriptorProtos$MethodOptions$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$MethodOptions$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$MethodOptions$Builder(builderParent, null);
    }

    public static DescriptorProtos$MethodOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$MethodOptions> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$MethodOptions> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$MethodOptions getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
