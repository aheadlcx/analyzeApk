package com.google.protobuf;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Option extends GeneratedMessageV3 implements OptionOrBuilder {
    private static final Option DEFAULT_INSTANCE = new Option();
    public static final int NAME_FIELD_NUMBER = 1;
    private static final Parser<Option> PARSER = new AbstractParser<Option>() {
        public Option parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Option(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int VALUE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private Any value_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements OptionOrBuilder {
        private Object name_;
        private SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> valueBuilder_;
        private Any value_;

        public static final Descriptor getDescriptor() {
            return TypeProto.internal_static_google_protobuf_Option_descriptor;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return TypeProto.internal_static_google_protobuf_Option_fieldAccessorTable.ensureFieldAccessorsInitialized(Option.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.value_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.value_ = null;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (!GeneratedMessageV3.alwaysUseFieldBuilders) {
            }
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            if (this.valueBuilder_ == null) {
                this.value_ = null;
            } else {
                this.value_ = null;
                this.valueBuilder_ = null;
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return TypeProto.internal_static_google_protobuf_Option_descriptor;
        }

        public Option getDefaultInstanceForType() {
            return Option.getDefaultInstance();
        }

        public Option build() {
            Object buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
        }

        public Option buildPartial() {
            Option option = new Option((com.google.protobuf.GeneratedMessageV3.Builder) this);
            option.name_ = this.name_;
            if (this.valueBuilder_ == null) {
                option.value_ = this.value_;
            } else {
                option.value_ = (Any) this.valueBuilder_.build();
            }
            onBuilt();
            return option;
        }

        public Builder clone() {
            return (Builder) super.clone();
        }

        public Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        public Builder clearField(FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        public Builder clearOneof(OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        public Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        public Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        public Builder mergeFrom(Message message) {
            if (message instanceof Option) {
                return mergeFrom((Option) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Option option) {
            if (option != Option.getDefaultInstance()) {
                if (!option.getName().isEmpty()) {
                    this.name_ = option.name_;
                    onChanged();
                }
                if (option.hasValue()) {
                    mergeValue(option.getValue());
                }
                mergeUnknownFields(option.unknownFields);
                onChanged();
            }
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Throwable th;
            Option option;
            Option option2;
            try {
                option2 = (Option) Option.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                if (option2 != null) {
                    mergeFrom(option2);
                }
                return this;
            } catch (InvalidProtocolBufferException e) {
                InvalidProtocolBufferException invalidProtocolBufferException = e;
                option2 = (Option) invalidProtocolBufferException.getUnfinishedMessage();
                throw invalidProtocolBufferException.unwrapIOException();
            } catch (Throwable th2) {
                Throwable th3 = th2;
                option = option2;
                th = th3;
                if (option != null) {
                    mergeFrom(option);
                }
                throw th;
            }
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String toStringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = toStringUtf8;
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

        public Builder setName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.name_ = str;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = Option.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public boolean hasValue() {
            return (this.valueBuilder_ == null && this.value_ == null) ? false : true;
        }

        public Any getValue() {
            if (this.valueBuilder_ == null) {
                return this.value_ == null ? Any.getDefaultInstance() : this.value_;
            } else {
                return (Any) this.valueBuilder_.getMessage();
            }
        }

        public Builder setValue(Any any) {
            if (this.valueBuilder_ != null) {
                this.valueBuilder_.setMessage(any);
            } else if (any == null) {
                throw new NullPointerException();
            } else {
                this.value_ = any;
                onChanged();
            }
            return this;
        }

        public Builder setValue(com.google.protobuf.Any.Builder builder) {
            if (this.valueBuilder_ == null) {
                this.value_ = builder.build();
                onChanged();
            } else {
                this.valueBuilder_.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeValue(Any any) {
            if (this.valueBuilder_ == null) {
                if (this.value_ != null) {
                    this.value_ = Any.newBuilder(this.value_).mergeFrom(any).buildPartial();
                } else {
                    this.value_ = any;
                }
                onChanged();
            } else {
                this.valueBuilder_.mergeFrom(any);
            }
            return this;
        }

        public Builder clearValue() {
            if (this.valueBuilder_ == null) {
                this.value_ = null;
                onChanged();
            } else {
                this.value_ = null;
                this.valueBuilder_ = null;
            }
            return this;
        }

        public com.google.protobuf.Any.Builder getValueBuilder() {
            onChanged();
            return (com.google.protobuf.Any.Builder) getValueFieldBuilder().getBuilder();
        }

        public AnyOrBuilder getValueOrBuilder() {
            if (this.valueBuilder_ != null) {
                return (AnyOrBuilder) this.valueBuilder_.getMessageOrBuilder();
            }
            return this.value_ == null ? Any.getDefaultInstance() : this.value_;
        }

        private SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> getValueFieldBuilder() {
            if (this.valueBuilder_ == null) {
                this.valueBuilder_ = new SingleFieldBuilderV3(getValue(), getParentForChildren(), isClean());
                this.value_ = null;
            }
            return this.valueBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private Option(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Option() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Option(com.google.protobuf.CodedInputStream r6, com.google.protobuf.ExtensionRegistryLite r7) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
        r5 = this;
        r1 = 1;
        r5.<init>();
        r4 = com.google.protobuf.UnknownFieldSet.newBuilder();
        r2 = 0;
    L_0x0009:
        if (r2 != 0) goto L_0x004c;
    L_0x000b:
        r0 = r6.readTag();	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
        switch(r0) {
            case 0: goto L_0x001b;
            case 10: goto L_0x001d;
            case 18: goto L_0x0025;
            default: goto L_0x0012;
        };	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
    L_0x0012:
        r0 = r5.parseUnknownFieldProto3(r6, r4, r7, r0);	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
        if (r0 != 0) goto L_0x0072;
    L_0x0018:
        r0 = r1;
    L_0x0019:
        r2 = r0;
        goto L_0x0009;
    L_0x001b:
        r0 = r1;
        goto L_0x0019;
    L_0x001d:
        r0 = r6.readStringRequireUtf8();	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
        r5.name_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
        r0 = r2;
        goto L_0x0019;
    L_0x0025:
        r0 = 0;
        r3 = r5.value_;	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
        if (r3 == 0) goto L_0x0074;
    L_0x002a:
        r0 = r5.value_;	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
        r0 = r0.toBuilder();	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
        r3 = r0;
    L_0x0031:
        r0 = com.google.protobuf.Any.parser();	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
        r0 = r6.readMessage(r0, r7);	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
        r0 = (com.google.protobuf.Any) r0;	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
        r5.value_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
        if (r3 == 0) goto L_0x0072;
    L_0x003f:
        r0 = r5.value_;	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
        r3.mergeFrom(r0);	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
        r0 = r3.buildPartial();	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
        r5.value_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0056, IOException -> 0x0067 }
        r0 = r2;
        goto L_0x0019;
    L_0x004c:
        r0 = r4.build();
        r5.unknownFields = r0;
        r5.makeExtensionsImmutable();
        return;
    L_0x0056:
        r0 = move-exception;
        r0 = r0.setUnfinishedMessage(r5);	 Catch:{ all -> 0x005c }
        throw r0;	 Catch:{ all -> 0x005c }
    L_0x005c:
        r0 = move-exception;
        r1 = r4.build();
        r5.unknownFields = r1;
        r5.makeExtensionsImmutable();
        throw r0;
    L_0x0067:
        r0 = move-exception;
        r1 = new com.google.protobuf.InvalidProtocolBufferException;	 Catch:{ all -> 0x005c }
        r1.<init>(r0);	 Catch:{ all -> 0x005c }
        r0 = r1.setUnfinishedMessage(r5);	 Catch:{ all -> 0x005c }
        throw r0;	 Catch:{ all -> 0x005c }
    L_0x0072:
        r0 = r2;
        goto L_0x0019;
    L_0x0074:
        r3 = r0;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Option.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
    }

    public static final Descriptor getDescriptor() {
        return TypeProto.internal_static_google_protobuf_Option_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return TypeProto.internal_static_google_protobuf_Option_fieldAccessorTable.ensureFieldAccessorsInitialized(Option.class, Builder.class);
    }

    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String toStringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = toStringUtf8;
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

    public boolean hasValue() {
        return this.value_ != null;
    }

    public Any getValue() {
        return this.value_ == null ? Any.getDefaultInstance() : this.value_;
    }

    public AnyOrBuilder getValueOrBuilder() {
        return getValue();
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == (byte) 1) {
            return true;
        }
        if (b == (byte) 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.value_ != null) {
            codedOutputStream.writeMessage(2, getValue());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (!getNameBytes().isEmpty()) {
            i = 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
        }
        if (this.value_ != null) {
            i += CodedOutputStream.computeMessageSize(2, getValue());
        }
        i += this.unknownFields.getSerializedSize();
        this.memoizedSize = i;
        return i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Option)) {
            return super.equals(obj);
        }
        boolean z;
        Option option = (Option) obj;
        if ((getName().equals(option.getName())) && hasValue() == option.hasValue()) {
            z = true;
        } else {
            z = false;
        }
        if (hasValue()) {
            if (z && getValue().equals(option.getValue())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && this.unknownFields.equals(option.unknownFields)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53) + getName().hashCode();
        if (hasValue()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getValue().hashCode();
        }
        hashCode = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static Option parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Option) PARSER.parseFrom(byteBuffer);
    }

    public static Option parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Option) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Option parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Option) PARSER.parseFrom(byteString);
    }

    public static Option parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Option) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Option parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Option) PARSER.parseFrom(bArr);
    }

    public static Option parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Option) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Option parseFrom(InputStream inputStream) throws IOException {
        return (Option) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Option parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Option) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Option parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Option) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Option parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Option) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Option parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Option) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Option parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Option) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Option option) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(option);
    }

    public Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    protected Builder newBuilderForType(BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static Option getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Option> parser() {
        return PARSER;
    }

    public Parser<Option> getParserForType() {
        return PARSER;
    }

    public Option getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
