package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.FieldOptionsOrBuilder;
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

public final class DescriptorProtos$FieldOptions extends ExtendableMessage<DescriptorProtos$FieldOptions> implements FieldOptionsOrBuilder {
    public static final int CTYPE_FIELD_NUMBER = 1;
    private static final DescriptorProtos$FieldOptions DEFAULT_INSTANCE = new DescriptorProtos$FieldOptions();
    public static final int DEPRECATED_FIELD_NUMBER = 3;
    public static final int JSTYPE_FIELD_NUMBER = 6;
    public static final int LAZY_FIELD_NUMBER = 5;
    public static final int PACKED_FIELD_NUMBER = 2;
    @Deprecated
    public static final Parser<DescriptorProtos$FieldOptions> PARSER = new DescriptorProtos$FieldOptions$1();
    public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
    public static final int WEAK_FIELD_NUMBER = 10;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private int ctype_;
    private boolean deprecated_;
    private int jstype_;
    private boolean lazy_;
    private byte memoizedIsInitialized;
    private boolean packed_;
    private List<DescriptorProtos$UninterpretedOption> uninterpretedOption_;
    private boolean weak_;

    public enum CType implements ProtocolMessageEnum {
        STRING(0),
        CORD(1),
        STRING_PIECE(2);
        
        public static final int CORD_VALUE = 1;
        public static final int STRING_PIECE_VALUE = 2;
        public static final int STRING_VALUE = 0;
        private static final CType[] VALUES = null;
        private static final EnumLiteMap<CType> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new DescriptorProtos$FieldOptions$CType$1();
            VALUES = values();
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static CType valueOf(int i) {
            return forNumber(i);
        }

        public static CType forNumber(int i) {
            switch (i) {
                case 0:
                    return STRING;
                case 1:
                    return CORD;
                case 2:
                    return STRING_PIECE;
                default:
                    return null;
            }
        }

        public static EnumLiteMap<CType> internalGetValueMap() {
            return internalValueMap;
        }

        public final EnumValueDescriptor getValueDescriptor() {
            return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final EnumDescriptor getDescriptor() {
            return (EnumDescriptor) DescriptorProtos$FieldOptions.getDescriptor().getEnumTypes().get(0);
        }

        public static CType valueOf(EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        private CType(int i) {
            this.value = i;
        }
    }

    public enum JSType implements ProtocolMessageEnum {
        JS_NORMAL(0),
        JS_STRING(1),
        JS_NUMBER(2);
        
        public static final int JS_NORMAL_VALUE = 0;
        public static final int JS_NUMBER_VALUE = 2;
        public static final int JS_STRING_VALUE = 1;
        private static final JSType[] VALUES = null;
        private static final EnumLiteMap<JSType> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new DescriptorProtos$FieldOptions$JSType$1();
            VALUES = values();
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static JSType valueOf(int i) {
            return forNumber(i);
        }

        public static JSType forNumber(int i) {
            switch (i) {
                case 0:
                    return JS_NORMAL;
                case 1:
                    return JS_STRING;
                case 2:
                    return JS_NUMBER;
                default:
                    return null;
            }
        }

        public static EnumLiteMap<JSType> internalGetValueMap() {
            return internalValueMap;
        }

        public final EnumValueDescriptor getValueDescriptor() {
            return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final EnumDescriptor getDescriptor() {
            return (EnumDescriptor) DescriptorProtos$FieldOptions.getDescriptor().getEnumTypes().get(1);
        }

        public static JSType valueOf(EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        private JSType(int i) {
            this.value = i;
        }
    }

    private DescriptorProtos$FieldOptions(ExtendableBuilder<DescriptorProtos$FieldOptions, ?> extendableBuilder) {
        super(extendableBuilder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$FieldOptions() {
        this.memoizedIsInitialized = (byte) -1;
        this.ctype_ = 0;
        this.packed_ = false;
        this.jstype_ = 0;
        this.lazy_ = false;
        this.deprecated_ = false;
        this.weak_ = false;
        this.uninterpretedOption_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DescriptorProtos$FieldOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        readTag = codedInputStream.readEnum();
                        if (CType.valueOf(readTag) != null) {
                            this.bitField0_ |= 1;
                            this.ctype_ = readTag;
                            break;
                        }
                        newBuilder.mergeVarintField(1, readTag);
                        break;
                    case 16:
                        this.bitField0_ |= 2;
                        this.packed_ = codedInputStream.readBool();
                        break;
                    case 24:
                        this.bitField0_ |= 16;
                        this.deprecated_ = codedInputStream.readBool();
                        break;
                    case 40:
                        this.bitField0_ |= 8;
                        this.lazy_ = codedInputStream.readBool();
                        break;
                    case 48:
                        readTag = codedInputStream.readEnum();
                        if (JSType.valueOf(readTag) != null) {
                            this.bitField0_ |= 4;
                            this.jstype_ = readTag;
                            break;
                        }
                        newBuilder.mergeVarintField(6, readTag);
                        break;
                    case 80:
                        this.bitField0_ |= 32;
                        this.weak_ = codedInputStream.readBool();
                        break;
                    case 7994:
                        if ((i & 64) != 64) {
                            this.uninterpretedOption_ = new ArrayList();
                            i |= 64;
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
                if ((i & 64) == 64) {
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        if ((i & 64) == 64) {
            this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
        }
        this.unknownFields = newBuilder.build();
        makeExtensionsImmutable();
    }

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.access$17600();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$17700().ensureFieldAccessorsInitialized(DescriptorProtos$FieldOptions.class, DescriptorProtos$FieldOptions$Builder.class);
    }

    public boolean hasCtype() {
        return (this.bitField0_ & 1) == 1;
    }

    public CType getCtype() {
        CType valueOf = CType.valueOf(this.ctype_);
        return valueOf == null ? CType.STRING : valueOf;
    }

    public boolean hasPacked() {
        return (this.bitField0_ & 2) == 2;
    }

    public boolean getPacked() {
        return this.packed_;
    }

    public boolean hasJstype() {
        return (this.bitField0_ & 4) == 4;
    }

    public JSType getJstype() {
        JSType valueOf = JSType.valueOf(this.jstype_);
        return valueOf == null ? JSType.JS_NORMAL : valueOf;
    }

    public boolean hasLazy() {
        return (this.bitField0_ & 8) == 8;
    }

    public boolean getLazy() {
        return this.lazy_;
    }

    public boolean hasDeprecated() {
        return (this.bitField0_ & 16) == 16;
    }

    public boolean getDeprecated() {
        return this.deprecated_;
    }

    public boolean hasWeak() {
        return (this.bitField0_ & 32) == 32;
    }

    public boolean getWeak() {
        return this.weak_;
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
            codedOutputStream.writeEnum(1, this.ctype_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeBool(2, this.packed_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeBool(3, this.deprecated_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeBool(5, this.lazy_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeEnum(6, this.jstype_);
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeBool(10, this.weak_);
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
            i2 = CodedOutputStream.computeEnumSize(1, this.ctype_) + 0;
        } else {
            i2 = 0;
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeBoolSize(2, this.packed_);
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeBoolSize(3, this.deprecated_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeBoolSize(5, this.lazy_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeEnumSize(6, this.jstype_);
        }
        if ((this.bitField0_ & 32) == 32) {
            i2 += CodedOutputStream.computeBoolSize(10, this.weak_);
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
        if (!(obj instanceof DescriptorProtos$FieldOptions)) {
            return super.equals(obj);
        }
        DescriptorProtos$FieldOptions descriptorProtos$FieldOptions = (DescriptorProtos$FieldOptions) obj;
        boolean z = hasCtype() == descriptorProtos$FieldOptions.hasCtype();
        if (hasCtype()) {
            z = z && this.ctype_ == descriptorProtos$FieldOptions.ctype_;
        }
        if (z && hasPacked() == descriptorProtos$FieldOptions.hasPacked()) {
            z = true;
        } else {
            z = false;
        }
        if (hasPacked()) {
            if (z && getPacked() == descriptorProtos$FieldOptions.getPacked()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasJstype() == descriptorProtos$FieldOptions.hasJstype()) {
            z = true;
        } else {
            z = false;
        }
        if (hasJstype()) {
            if (z && this.jstype_ == descriptorProtos$FieldOptions.jstype_) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasLazy() == descriptorProtos$FieldOptions.hasLazy()) {
            z = true;
        } else {
            z = false;
        }
        if (hasLazy()) {
            if (z && getLazy() == descriptorProtos$FieldOptions.getLazy()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasDeprecated() == descriptorProtos$FieldOptions.hasDeprecated()) {
            z = true;
        } else {
            z = false;
        }
        if (hasDeprecated()) {
            if (z && getDeprecated() == descriptorProtos$FieldOptions.getDeprecated()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasWeak() == descriptorProtos$FieldOptions.hasWeak()) {
            z = true;
        } else {
            z = false;
        }
        if (hasWeak()) {
            if (z && getWeak() == descriptorProtos$FieldOptions.getWeak()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && getUninterpretedOptionList().equals(descriptorProtos$FieldOptions.getUninterpretedOptionList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.unknownFields.equals(descriptorProtos$FieldOptions.unknownFields)) {
            z = true;
        } else {
            z = false;
        }
        if (z && getExtensionFields().equals(descriptorProtos$FieldOptions.getExtensionFields())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (hasCtype()) {
            hashCode = (((hashCode * 37) + 1) * 53) + this.ctype_;
        }
        if (hasPacked()) {
            hashCode = (((hashCode * 37) + 2) * 53) + Internal.hashBoolean(getPacked());
        }
        if (hasJstype()) {
            hashCode = (((hashCode * 37) + 6) * 53) + this.jstype_;
        }
        if (hasLazy()) {
            hashCode = (((hashCode * 37) + 5) * 53) + Internal.hashBoolean(getLazy());
        }
        if (hasDeprecated()) {
            hashCode = (((hashCode * 37) + 3) * 53) + Internal.hashBoolean(getDeprecated());
        }
        if (hasWeak()) {
            hashCode = (((hashCode * 37) + 10) * 53) + Internal.hashBoolean(getWeak());
        }
        if (getUninterpretedOptionCount() > 0) {
            hashCode = (((hashCode * 37) + 999) * 53) + getUninterpretedOptionList().hashCode();
        }
        hashCode = (hashFields(hashCode, getExtensionFields()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$FieldOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FieldOptions) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$FieldOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FieldOptions) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$FieldOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FieldOptions) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$FieldOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FieldOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$FieldOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FieldOptions) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$FieldOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FieldOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$FieldOptions parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$FieldOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$FieldOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$FieldOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$FieldOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$FieldOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$FieldOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$FieldOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$FieldOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$FieldOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$FieldOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$FieldOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$FieldOptions$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$FieldOptions$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$FieldOptions$Builder newBuilder(DescriptorProtos$FieldOptions descriptorProtos$FieldOptions) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$FieldOptions);
    }

    public DescriptorProtos$FieldOptions$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$FieldOptions$Builder(null);
        }
        return new DescriptorProtos$FieldOptions$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$FieldOptions$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$FieldOptions$Builder(builderParent, null);
    }

    public static DescriptorProtos$FieldOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$FieldOptions> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$FieldOptions> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$FieldOptions getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
