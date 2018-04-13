package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.UninterpretedOptionOrBuilder;
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

public final class DescriptorProtos$UninterpretedOption extends GeneratedMessageV3 implements UninterpretedOptionOrBuilder {
    public static final int AGGREGATE_VALUE_FIELD_NUMBER = 8;
    private static final DescriptorProtos$UninterpretedOption DEFAULT_INSTANCE = new DescriptorProtos$UninterpretedOption();
    public static final int DOUBLE_VALUE_FIELD_NUMBER = 6;
    public static final int IDENTIFIER_VALUE_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 2;
    public static final int NEGATIVE_INT_VALUE_FIELD_NUMBER = 5;
    @Deprecated
    public static final Parser<DescriptorProtos$UninterpretedOption> PARSER = new DescriptorProtos$UninterpretedOption$1();
    public static final int POSITIVE_INT_VALUE_FIELD_NUMBER = 4;
    public static final int STRING_VALUE_FIELD_NUMBER = 7;
    private static final long serialVersionUID = 0;
    private volatile Object aggregateValue_;
    private int bitField0_;
    private double doubleValue_;
    private volatile Object identifierValue_;
    private byte memoizedIsInitialized;
    private List<NamePart> name_;
    private long negativeIntValue_;
    private long positiveIntValue_;
    private ByteString stringValue_;

    public static final class NamePart extends GeneratedMessageV3 implements DescriptorProtos$UninterpretedOption$NamePartOrBuilder {
        private static final NamePart DEFAULT_INSTANCE = new NamePart();
        public static final int IS_EXTENSION_FIELD_NUMBER = 2;
        public static final int NAME_PART_FIELD_NUMBER = 1;
        @Deprecated
        public static final Parser<NamePart> PARSER = new DescriptorProtos$UninterpretedOption$NamePart$1();
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private boolean isExtension_;
        private byte memoizedIsInitialized;
        private volatile Object namePart_;

        private NamePart(Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private NamePart() {
            this.memoizedIsInitialized = (byte) -1;
            this.namePart_ = "";
            this.isExtension_ = false;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private NamePart(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            Object obj = null;
            while (obj == null) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            obj = 1;
                            break;
                        case 10:
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 1;
                            this.namePart_ = readBytes;
                            break;
                        case 16:
                            this.bitField0_ |= 2;
                            this.isExtension_ = codedInputStream.readBool();
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
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.access$23700();
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.access$23800().ensureFieldAccessorsInitialized(NamePart.class, DescriptorProtos$UninterpretedOption$NamePart$Builder.class);
        }

        public boolean hasNamePart() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getNamePart() {
            Object obj = this.namePart_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.namePart_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getNamePartBytes() {
            Object obj = this.namePart_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.namePart_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasIsExtension() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean getIsExtension() {
            return this.isExtension_;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            if (!hasNamePart()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (hasIsExtension()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            } else {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.namePart_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBool(2, this.isExtension_);
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
                i = 0 + GeneratedMessageV3.computeStringSize(1, this.namePart_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i += CodedOutputStream.computeBoolSize(2, this.isExtension_);
            }
            i += this.unknownFields.getSerializedSize();
            this.memoizedSize = i;
            return i;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof NamePart)) {
                return super.equals(obj);
            }
            NamePart namePart = (NamePart) obj;
            boolean z = hasNamePart() == namePart.hasNamePart();
            if (hasNamePart()) {
                z = z && getNamePart().equals(namePart.getNamePart());
            }
            if (z && hasIsExtension() == namePart.hasIsExtension()) {
                z = true;
            } else {
                z = false;
            }
            if (hasIsExtension()) {
                if (z && getIsExtension() == namePart.getIsExtension()) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && this.unknownFields.equals(namePart.unknownFields)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasNamePart()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getNamePart().hashCode();
            }
            if (hasIsExtension()) {
                hashCode = (((hashCode * 37) + 2) * 53) + Internal.hashBoolean(getIsExtension());
            }
            hashCode = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static NamePart parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NamePart) PARSER.parseFrom(byteBuffer);
        }

        public static NamePart parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NamePart) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static NamePart parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NamePart) PARSER.parseFrom(byteString);
        }

        public static NamePart parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NamePart) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static NamePart parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NamePart) PARSER.parseFrom(bArr);
        }

        public static NamePart parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NamePart) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static NamePart parseFrom(InputStream inputStream) throws IOException {
            return (NamePart) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static NamePart parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NamePart) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static NamePart parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NamePart) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static NamePart parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NamePart) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static NamePart parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NamePart) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static NamePart parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NamePart) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public DescriptorProtos$UninterpretedOption$NamePart$Builder newBuilderForType() {
            return newBuilder();
        }

        public static DescriptorProtos$UninterpretedOption$NamePart$Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static DescriptorProtos$UninterpretedOption$NamePart$Builder newBuilder(NamePart namePart) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(namePart);
        }

        public DescriptorProtos$UninterpretedOption$NamePart$Builder toBuilder() {
            if (this == DEFAULT_INSTANCE) {
                return new DescriptorProtos$UninterpretedOption$NamePart$Builder(null);
            }
            return new DescriptorProtos$UninterpretedOption$NamePart$Builder(null).mergeFrom(this);
        }

        protected DescriptorProtos$UninterpretedOption$NamePart$Builder newBuilderForType(BuilderParent builderParent) {
            return new DescriptorProtos$UninterpretedOption$NamePart$Builder(builderParent, null);
        }

        public static NamePart getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<NamePart> parser() {
            return PARSER;
        }

        public Parser<NamePart> getParserForType() {
            return PARSER;
        }

        public NamePart getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    private DescriptorProtos$UninterpretedOption(Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$UninterpretedOption() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = Collections.emptyList();
        this.identifierValue_ = "";
        this.positiveIntValue_ = 0;
        this.negativeIntValue_ = 0;
        this.doubleValue_ = 0.0d;
        this.stringValue_ = ByteString.EMPTY;
        this.aggregateValue_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DescriptorProtos$UninterpretedOption(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Object obj = null;
        this();
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        int i = 0;
        while (obj == null) {
            try {
                int readTag = codedInputStream.readTag();
                ByteString readBytes;
                switch (readTag) {
                    case 0:
                        obj = 1;
                        break;
                    case 18:
                        if ((i & 1) != 1) {
                            this.name_ = new ArrayList();
                            i |= 1;
                        }
                        this.name_.add(codedInputStream.readMessage(NamePart.PARSER, extensionRegistryLite));
                        break;
                    case 26:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 1;
                        this.identifierValue_ = readBytes;
                        break;
                    case 32:
                        this.bitField0_ |= 2;
                        this.positiveIntValue_ = codedInputStream.readUInt64();
                        break;
                    case 40:
                        this.bitField0_ |= 4;
                        this.negativeIntValue_ = codedInputStream.readInt64();
                        break;
                    case 49:
                        this.bitField0_ |= 8;
                        this.doubleValue_ = codedInputStream.readDouble();
                        break;
                    case 58:
                        this.bitField0_ |= 16;
                        this.stringValue_ = codedInputStream.readBytes();
                        break;
                    case 66:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 32;
                        this.aggregateValue_ = readBytes;
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
                    this.name_ = Collections.unmodifiableList(this.name_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        if ((i & 1) == 1) {
            this.name_ = Collections.unmodifiableList(this.name_);
        }
        this.unknownFields = newBuilder.build();
        makeExtensionsImmutable();
    }

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.access$23500();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$23600().ensureFieldAccessorsInitialized(DescriptorProtos$UninterpretedOption.class, DescriptorProtos$UninterpretedOption$Builder.class);
    }

    public List<NamePart> getNameList() {
        return this.name_;
    }

    public List<? extends DescriptorProtos$UninterpretedOption$NamePartOrBuilder> getNameOrBuilderList() {
        return this.name_;
    }

    public int getNameCount() {
        return this.name_.size();
    }

    public NamePart getName(int i) {
        return (NamePart) this.name_.get(i);
    }

    public DescriptorProtos$UninterpretedOption$NamePartOrBuilder getNameOrBuilder(int i) {
        return (DescriptorProtos$UninterpretedOption$NamePartOrBuilder) this.name_.get(i);
    }

    public boolean hasIdentifierValue() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getIdentifierValue() {
        Object obj = this.identifierValue_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.identifierValue_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getIdentifierValueBytes() {
        Object obj = this.identifierValue_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.identifierValue_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasPositiveIntValue() {
        return (this.bitField0_ & 2) == 2;
    }

    public long getPositiveIntValue() {
        return this.positiveIntValue_;
    }

    public boolean hasNegativeIntValue() {
        return (this.bitField0_ & 4) == 4;
    }

    public long getNegativeIntValue() {
        return this.negativeIntValue_;
    }

    public boolean hasDoubleValue() {
        return (this.bitField0_ & 8) == 8;
    }

    public double getDoubleValue() {
        return this.doubleValue_;
    }

    public boolean hasStringValue() {
        return (this.bitField0_ & 16) == 16;
    }

    public ByteString getStringValue() {
        return this.stringValue_;
    }

    public boolean hasAggregateValue() {
        return (this.bitField0_ & 32) == 32;
    }

    public String getAggregateValue() {
        Object obj = this.aggregateValue_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.aggregateValue_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getAggregateValueBytes() {
        Object obj = this.aggregateValue_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.aggregateValue_ = copyFromUtf8;
        return copyFromUtf8;
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
        while (i < getNameCount()) {
            if (getName(i).isInitialized()) {
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
        for (int i = 0; i < this.name_.size(); i++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.name_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.identifierValue_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeUInt64(4, this.positiveIntValue_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeInt64(5, this.negativeIntValue_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeDouble(6, this.doubleValue_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeBytes(7, this.stringValue_);
        }
        if ((this.bitField0_ & 32) == 32) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.aggregateValue_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (i = 0; i < this.name_.size(); i++) {
            i2 += CodedOutputStream.computeMessageSize(2, (MessageLite) this.name_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            i2 += GeneratedMessageV3.computeStringSize(3, this.identifierValue_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeUInt64Size(4, this.positiveIntValue_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeInt64Size(5, this.negativeIntValue_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeDoubleSize(6, this.doubleValue_);
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeBytesSize(7, this.stringValue_);
        }
        if ((this.bitField0_ & 32) == 32) {
            i2 += GeneratedMessageV3.computeStringSize(8, this.aggregateValue_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DescriptorProtos$UninterpretedOption)) {
            return super.equals(obj);
        }
        boolean z;
        DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption = (DescriptorProtos$UninterpretedOption) obj;
        if ((getNameList().equals(descriptorProtos$UninterpretedOption.getNameList())) && hasIdentifierValue() == descriptorProtos$UninterpretedOption.hasIdentifierValue()) {
            z = true;
        } else {
            z = false;
        }
        if (hasIdentifierValue()) {
            if (z && getIdentifierValue().equals(descriptorProtos$UninterpretedOption.getIdentifierValue())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasPositiveIntValue() == descriptorProtos$UninterpretedOption.hasPositiveIntValue()) {
            z = true;
        } else {
            z = false;
        }
        if (hasPositiveIntValue()) {
            if (z && getPositiveIntValue() == descriptorProtos$UninterpretedOption.getPositiveIntValue()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasNegativeIntValue() == descriptorProtos$UninterpretedOption.hasNegativeIntValue()) {
            z = true;
        } else {
            z = false;
        }
        if (hasNegativeIntValue()) {
            if (z && getNegativeIntValue() == descriptorProtos$UninterpretedOption.getNegativeIntValue()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasDoubleValue() == descriptorProtos$UninterpretedOption.hasDoubleValue()) {
            z = true;
        } else {
            z = false;
        }
        if (hasDoubleValue()) {
            if (z && Double.doubleToLongBits(getDoubleValue()) == Double.doubleToLongBits(descriptorProtos$UninterpretedOption.getDoubleValue())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasStringValue() == descriptorProtos$UninterpretedOption.hasStringValue()) {
            z = true;
        } else {
            z = false;
        }
        if (hasStringValue()) {
            if (z && getStringValue().equals(descriptorProtos$UninterpretedOption.getStringValue())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasAggregateValue() == descriptorProtos$UninterpretedOption.hasAggregateValue()) {
            z = true;
        } else {
            z = false;
        }
        if (hasAggregateValue()) {
            if (z && getAggregateValue().equals(descriptorProtos$UninterpretedOption.getAggregateValue())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && this.unknownFields.equals(descriptorProtos$UninterpretedOption.unknownFields)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (getNameCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getNameList().hashCode();
        }
        if (hasIdentifierValue()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getIdentifierValue().hashCode();
        }
        if (hasPositiveIntValue()) {
            hashCode = (((hashCode * 37) + 4) * 53) + Internal.hashLong(getPositiveIntValue());
        }
        if (hasNegativeIntValue()) {
            hashCode = (((hashCode * 37) + 5) * 53) + Internal.hashLong(getNegativeIntValue());
        }
        if (hasDoubleValue()) {
            hashCode = (((hashCode * 37) + 6) * 53) + Internal.hashLong(Double.doubleToLongBits(getDoubleValue()));
        }
        if (hasStringValue()) {
            hashCode = (((hashCode * 37) + 7) * 53) + getStringValue().hashCode();
        }
        if (hasAggregateValue()) {
            hashCode = (((hashCode * 37) + 8) * 53) + getAggregateValue().hashCode();
        }
        hashCode = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$UninterpretedOption parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$UninterpretedOption) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$UninterpretedOption parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$UninterpretedOption) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$UninterpretedOption parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$UninterpretedOption) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$UninterpretedOption parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$UninterpretedOption) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$UninterpretedOption parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$UninterpretedOption) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$UninterpretedOption parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$UninterpretedOption) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$UninterpretedOption parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$UninterpretedOption) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$UninterpretedOption parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$UninterpretedOption) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$UninterpretedOption parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$UninterpretedOption) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$UninterpretedOption parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$UninterpretedOption) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$UninterpretedOption parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$UninterpretedOption) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$UninterpretedOption parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$UninterpretedOption) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$UninterpretedOption$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$UninterpretedOption$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$UninterpretedOption$Builder newBuilder(DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$UninterpretedOption);
    }

    public DescriptorProtos$UninterpretedOption$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$UninterpretedOption$Builder(null);
        }
        return new DescriptorProtos$UninterpretedOption$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$UninterpretedOption$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$UninterpretedOption$Builder(builderParent, null);
    }

    public static DescriptorProtos$UninterpretedOption getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$UninterpretedOption> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$UninterpretedOption> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$UninterpretedOption getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
