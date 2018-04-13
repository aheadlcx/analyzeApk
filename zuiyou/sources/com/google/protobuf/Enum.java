package com.google.protobuf;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Enum extends GeneratedMessageV3 implements EnumOrBuilder {
    private static final Enum DEFAULT_INSTANCE = new Enum();
    public static final int ENUMVALUE_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int OPTIONS_FIELD_NUMBER = 3;
    private static final Parser<Enum> PARSER = new AbstractParser<Enum>() {
        public Enum parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Enum(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SOURCE_CONTEXT_FIELD_NUMBER = 4;
    public static final int SYNTAX_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private List<EnumValue> enumvalue_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private List<Option> options_;
    private SourceContext sourceContext_;
    private int syntax_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements EnumOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<EnumValue, com.google.protobuf.EnumValue.Builder, EnumValueOrBuilder> enumvalueBuilder_;
        private List<EnumValue> enumvalue_;
        private Object name_;
        private RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> optionsBuilder_;
        private List<Option> options_;
        private SingleFieldBuilderV3<SourceContext, com.google.protobuf.SourceContext.Builder, SourceContextOrBuilder> sourceContextBuilder_;
        private SourceContext sourceContext_;
        private int syntax_;

        public static final Descriptor getDescriptor() {
            return TypeProto.internal_static_google_protobuf_Enum_descriptor;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return TypeProto.internal_static_google_protobuf_Enum_fieldAccessorTable.ensureFieldAccessorsInitialized(Enum.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.enumvalue_ = Collections.emptyList();
            this.options_ = Collections.emptyList();
            this.sourceContext_ = null;
            this.syntax_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.enumvalue_ = Collections.emptyList();
            this.options_ = Collections.emptyList();
            this.sourceContext_ = null;
            this.syntax_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                getEnumvalueFieldBuilder();
                getOptionsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            if (this.enumvalueBuilder_ == null) {
                this.enumvalue_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                this.enumvalueBuilder_.clear();
            }
            if (this.optionsBuilder_ == null) {
                this.options_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                this.optionsBuilder_.clear();
            }
            if (this.sourceContextBuilder_ == null) {
                this.sourceContext_ = null;
            } else {
                this.sourceContext_ = null;
                this.sourceContextBuilder_ = null;
            }
            this.syntax_ = 0;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return TypeProto.internal_static_google_protobuf_Enum_descriptor;
        }

        public Enum getDefaultInstanceForType() {
            return Enum.getDefaultInstance();
        }

        public Enum build() {
            Object buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
        }

        public Enum buildPartial() {
            Enum enumR = new Enum((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            enumR.name_ = this.name_;
            if (this.enumvalueBuilder_ == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.enumvalue_ = Collections.unmodifiableList(this.enumvalue_);
                    this.bitField0_ &= -3;
                }
                enumR.enumvalue_ = this.enumvalue_;
            } else {
                enumR.enumvalue_ = this.enumvalueBuilder_.build();
            }
            if (this.optionsBuilder_ == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.options_ = Collections.unmodifiableList(this.options_);
                    this.bitField0_ &= -5;
                }
                enumR.options_ = this.options_;
            } else {
                enumR.options_ = this.optionsBuilder_.build();
            }
            if (this.sourceContextBuilder_ == null) {
                enumR.sourceContext_ = this.sourceContext_;
            } else {
                enumR.sourceContext_ = (SourceContext) this.sourceContextBuilder_.build();
            }
            enumR.syntax_ = this.syntax_;
            enumR.bitField0_ = 0;
            onBuilt();
            return enumR;
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
            if (message instanceof Enum) {
                return mergeFrom((Enum) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Enum enumR) {
            RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
            if (enumR != Enum.getDefaultInstance()) {
                if (!enumR.getName().isEmpty()) {
                    this.name_ = enumR.name_;
                    onChanged();
                }
                if (this.enumvalueBuilder_ == null) {
                    if (!enumR.enumvalue_.isEmpty()) {
                        if (this.enumvalue_.isEmpty()) {
                            this.enumvalue_ = enumR.enumvalue_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureEnumvalueIsMutable();
                            this.enumvalue_.addAll(enumR.enumvalue_);
                        }
                        onChanged();
                    }
                } else if (!enumR.enumvalue_.isEmpty()) {
                    if (this.enumvalueBuilder_.isEmpty()) {
                        this.enumvalueBuilder_.dispose();
                        this.enumvalueBuilder_ = null;
                        this.enumvalue_ = enumR.enumvalue_;
                        this.bitField0_ &= -3;
                        this.enumvalueBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getEnumvalueFieldBuilder() : null;
                    } else {
                        this.enumvalueBuilder_.addAllMessages(enumR.enumvalue_);
                    }
                }
                if (this.optionsBuilder_ == null) {
                    if (!enumR.options_.isEmpty()) {
                        if (this.options_.isEmpty()) {
                            this.options_ = enumR.options_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureOptionsIsMutable();
                            this.options_.addAll(enumR.options_);
                        }
                        onChanged();
                    }
                } else if (!enumR.options_.isEmpty()) {
                    if (this.optionsBuilder_.isEmpty()) {
                        this.optionsBuilder_.dispose();
                        this.optionsBuilder_ = null;
                        this.options_ = enumR.options_;
                        this.bitField0_ &= -5;
                        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getOptionsFieldBuilder();
                        }
                        this.optionsBuilder_ = repeatedFieldBuilderV3;
                    } else {
                        this.optionsBuilder_.addAllMessages(enumR.options_);
                    }
                }
                if (enumR.hasSourceContext()) {
                    mergeSourceContext(enumR.getSourceContext());
                }
                if (enumR.syntax_ != 0) {
                    setSyntaxValue(enumR.getSyntaxValue());
                }
                mergeUnknownFields(enumR.unknownFields);
                onChanged();
            }
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Throwable th;
            Enum enumR;
            Enum enumR2;
            try {
                enumR2 = (Enum) Enum.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                if (enumR2 != null) {
                    mergeFrom(enumR2);
                }
                return this;
            } catch (InvalidProtocolBufferException e) {
                InvalidProtocolBufferException invalidProtocolBufferException = e;
                enumR2 = (Enum) invalidProtocolBufferException.getUnfinishedMessage();
                throw invalidProtocolBufferException.unwrapIOException();
            } catch (Throwable th2) {
                Throwable th3 = th2;
                enumR = enumR2;
                th = th3;
                if (enumR != null) {
                    mergeFrom(enumR);
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
            this.name_ = Enum.getDefaultInstance().getName();
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

        private void ensureEnumvalueIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.enumvalue_ = new ArrayList(this.enumvalue_);
                this.bitField0_ |= 2;
            }
        }

        public List<EnumValue> getEnumvalueList() {
            if (this.enumvalueBuilder_ == null) {
                return Collections.unmodifiableList(this.enumvalue_);
            }
            return this.enumvalueBuilder_.getMessageList();
        }

        public int getEnumvalueCount() {
            if (this.enumvalueBuilder_ == null) {
                return this.enumvalue_.size();
            }
            return this.enumvalueBuilder_.getCount();
        }

        public EnumValue getEnumvalue(int i) {
            if (this.enumvalueBuilder_ == null) {
                return (EnumValue) this.enumvalue_.get(i);
            }
            return (EnumValue) this.enumvalueBuilder_.getMessage(i);
        }

        public Builder setEnumvalue(int i, EnumValue enumValue) {
            if (this.enumvalueBuilder_ != null) {
                this.enumvalueBuilder_.setMessage(i, enumValue);
            } else if (enumValue == null) {
                throw new NullPointerException();
            } else {
                ensureEnumvalueIsMutable();
                this.enumvalue_.set(i, enumValue);
                onChanged();
            }
            return this;
        }

        public Builder setEnumvalue(int i, com.google.protobuf.EnumValue.Builder builder) {
            if (this.enumvalueBuilder_ == null) {
                ensureEnumvalueIsMutable();
                this.enumvalue_.set(i, builder.build());
                onChanged();
            } else {
                this.enumvalueBuilder_.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addEnumvalue(EnumValue enumValue) {
            if (this.enumvalueBuilder_ != null) {
                this.enumvalueBuilder_.addMessage(enumValue);
            } else if (enumValue == null) {
                throw new NullPointerException();
            } else {
                ensureEnumvalueIsMutable();
                this.enumvalue_.add(enumValue);
                onChanged();
            }
            return this;
        }

        public Builder addEnumvalue(int i, EnumValue enumValue) {
            if (this.enumvalueBuilder_ != null) {
                this.enumvalueBuilder_.addMessage(i, enumValue);
            } else if (enumValue == null) {
                throw new NullPointerException();
            } else {
                ensureEnumvalueIsMutable();
                this.enumvalue_.add(i, enumValue);
                onChanged();
            }
            return this;
        }

        public Builder addEnumvalue(com.google.protobuf.EnumValue.Builder builder) {
            if (this.enumvalueBuilder_ == null) {
                ensureEnumvalueIsMutable();
                this.enumvalue_.add(builder.build());
                onChanged();
            } else {
                this.enumvalueBuilder_.addMessage(builder.build());
            }
            return this;
        }

        public Builder addEnumvalue(int i, com.google.protobuf.EnumValue.Builder builder) {
            if (this.enumvalueBuilder_ == null) {
                ensureEnumvalueIsMutable();
                this.enumvalue_.add(i, builder.build());
                onChanged();
            } else {
                this.enumvalueBuilder_.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllEnumvalue(Iterable<? extends EnumValue> iterable) {
            if (this.enumvalueBuilder_ == null) {
                ensureEnumvalueIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.enumvalue_);
                onChanged();
            } else {
                this.enumvalueBuilder_.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearEnumvalue() {
            if (this.enumvalueBuilder_ == null) {
                this.enumvalue_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                this.enumvalueBuilder_.clear();
            }
            return this;
        }

        public Builder removeEnumvalue(int i) {
            if (this.enumvalueBuilder_ == null) {
                ensureEnumvalueIsMutable();
                this.enumvalue_.remove(i);
                onChanged();
            } else {
                this.enumvalueBuilder_.remove(i);
            }
            return this;
        }

        public com.google.protobuf.EnumValue.Builder getEnumvalueBuilder(int i) {
            return (com.google.protobuf.EnumValue.Builder) getEnumvalueFieldBuilder().getBuilder(i);
        }

        public EnumValueOrBuilder getEnumvalueOrBuilder(int i) {
            if (this.enumvalueBuilder_ == null) {
                return (EnumValueOrBuilder) this.enumvalue_.get(i);
            }
            return (EnumValueOrBuilder) this.enumvalueBuilder_.getMessageOrBuilder(i);
        }

        public List<? extends EnumValueOrBuilder> getEnumvalueOrBuilderList() {
            if (this.enumvalueBuilder_ != null) {
                return this.enumvalueBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.enumvalue_);
        }

        public com.google.protobuf.EnumValue.Builder addEnumvalueBuilder() {
            return (com.google.protobuf.EnumValue.Builder) getEnumvalueFieldBuilder().addBuilder(EnumValue.getDefaultInstance());
        }

        public com.google.protobuf.EnumValue.Builder addEnumvalueBuilder(int i) {
            return (com.google.protobuf.EnumValue.Builder) getEnumvalueFieldBuilder().addBuilder(i, EnumValue.getDefaultInstance());
        }

        public List<com.google.protobuf.EnumValue.Builder> getEnumvalueBuilderList() {
            return getEnumvalueFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<EnumValue, com.google.protobuf.EnumValue.Builder, EnumValueOrBuilder> getEnumvalueFieldBuilder() {
            if (this.enumvalueBuilder_ == null) {
                this.enumvalueBuilder_ = new RepeatedFieldBuilderV3(this.enumvalue_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.enumvalue_ = null;
            }
            return this.enumvalueBuilder_;
        }

        private void ensureOptionsIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.options_ = new ArrayList(this.options_);
                this.bitField0_ |= 4;
            }
        }

        public List<Option> getOptionsList() {
            if (this.optionsBuilder_ == null) {
                return Collections.unmodifiableList(this.options_);
            }
            return this.optionsBuilder_.getMessageList();
        }

        public int getOptionsCount() {
            if (this.optionsBuilder_ == null) {
                return this.options_.size();
            }
            return this.optionsBuilder_.getCount();
        }

        public Option getOptions(int i) {
            if (this.optionsBuilder_ == null) {
                return (Option) this.options_.get(i);
            }
            return (Option) this.optionsBuilder_.getMessage(i);
        }

        public Builder setOptions(int i, Option option) {
            if (this.optionsBuilder_ != null) {
                this.optionsBuilder_.setMessage(i, option);
            } else if (option == null) {
                throw new NullPointerException();
            } else {
                ensureOptionsIsMutable();
                this.options_.set(i, option);
                onChanged();
            }
            return this;
        }

        public Builder setOptions(int i, com.google.protobuf.Option.Builder builder) {
            if (this.optionsBuilder_ == null) {
                ensureOptionsIsMutable();
                this.options_.set(i, builder.build());
                onChanged();
            } else {
                this.optionsBuilder_.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addOptions(Option option) {
            if (this.optionsBuilder_ != null) {
                this.optionsBuilder_.addMessage(option);
            } else if (option == null) {
                throw new NullPointerException();
            } else {
                ensureOptionsIsMutable();
                this.options_.add(option);
                onChanged();
            }
            return this;
        }

        public Builder addOptions(int i, Option option) {
            if (this.optionsBuilder_ != null) {
                this.optionsBuilder_.addMessage(i, option);
            } else if (option == null) {
                throw new NullPointerException();
            } else {
                ensureOptionsIsMutable();
                this.options_.add(i, option);
                onChanged();
            }
            return this;
        }

        public Builder addOptions(com.google.protobuf.Option.Builder builder) {
            if (this.optionsBuilder_ == null) {
                ensureOptionsIsMutable();
                this.options_.add(builder.build());
                onChanged();
            } else {
                this.optionsBuilder_.addMessage(builder.build());
            }
            return this;
        }

        public Builder addOptions(int i, com.google.protobuf.Option.Builder builder) {
            if (this.optionsBuilder_ == null) {
                ensureOptionsIsMutable();
                this.options_.add(i, builder.build());
                onChanged();
            } else {
                this.optionsBuilder_.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllOptions(Iterable<? extends Option> iterable) {
            if (this.optionsBuilder_ == null) {
                ensureOptionsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.options_);
                onChanged();
            } else {
                this.optionsBuilder_.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearOptions() {
            if (this.optionsBuilder_ == null) {
                this.options_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
            } else {
                this.optionsBuilder_.clear();
            }
            return this;
        }

        public Builder removeOptions(int i) {
            if (this.optionsBuilder_ == null) {
                ensureOptionsIsMutable();
                this.options_.remove(i);
                onChanged();
            } else {
                this.optionsBuilder_.remove(i);
            }
            return this;
        }

        public com.google.protobuf.Option.Builder getOptionsBuilder(int i) {
            return (com.google.protobuf.Option.Builder) getOptionsFieldBuilder().getBuilder(i);
        }

        public OptionOrBuilder getOptionsOrBuilder(int i) {
            if (this.optionsBuilder_ == null) {
                return (OptionOrBuilder) this.options_.get(i);
            }
            return (OptionOrBuilder) this.optionsBuilder_.getMessageOrBuilder(i);
        }

        public List<? extends OptionOrBuilder> getOptionsOrBuilderList() {
            if (this.optionsBuilder_ != null) {
                return this.optionsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.options_);
        }

        public com.google.protobuf.Option.Builder addOptionsBuilder() {
            return (com.google.protobuf.Option.Builder) getOptionsFieldBuilder().addBuilder(Option.getDefaultInstance());
        }

        public com.google.protobuf.Option.Builder addOptionsBuilder(int i) {
            return (com.google.protobuf.Option.Builder) getOptionsFieldBuilder().addBuilder(i, Option.getDefaultInstance());
        }

        public List<com.google.protobuf.Option.Builder> getOptionsBuilderList() {
            return getOptionsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> getOptionsFieldBuilder() {
            if (this.optionsBuilder_ == null) {
                this.optionsBuilder_ = new RepeatedFieldBuilderV3(this.options_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                this.options_ = null;
            }
            return this.optionsBuilder_;
        }

        public boolean hasSourceContext() {
            return (this.sourceContextBuilder_ == null && this.sourceContext_ == null) ? false : true;
        }

        public SourceContext getSourceContext() {
            if (this.sourceContextBuilder_ == null) {
                return this.sourceContext_ == null ? SourceContext.getDefaultInstance() : this.sourceContext_;
            } else {
                return (SourceContext) this.sourceContextBuilder_.getMessage();
            }
        }

        public Builder setSourceContext(SourceContext sourceContext) {
            if (this.sourceContextBuilder_ != null) {
                this.sourceContextBuilder_.setMessage(sourceContext);
            } else if (sourceContext == null) {
                throw new NullPointerException();
            } else {
                this.sourceContext_ = sourceContext;
                onChanged();
            }
            return this;
        }

        public Builder setSourceContext(com.google.protobuf.SourceContext.Builder builder) {
            if (this.sourceContextBuilder_ == null) {
                this.sourceContext_ = builder.build();
                onChanged();
            } else {
                this.sourceContextBuilder_.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSourceContext(SourceContext sourceContext) {
            if (this.sourceContextBuilder_ == null) {
                if (this.sourceContext_ != null) {
                    this.sourceContext_ = SourceContext.newBuilder(this.sourceContext_).mergeFrom(sourceContext).buildPartial();
                } else {
                    this.sourceContext_ = sourceContext;
                }
                onChanged();
            } else {
                this.sourceContextBuilder_.mergeFrom(sourceContext);
            }
            return this;
        }

        public Builder clearSourceContext() {
            if (this.sourceContextBuilder_ == null) {
                this.sourceContext_ = null;
                onChanged();
            } else {
                this.sourceContext_ = null;
                this.sourceContextBuilder_ = null;
            }
            return this;
        }

        public com.google.protobuf.SourceContext.Builder getSourceContextBuilder() {
            onChanged();
            return (com.google.protobuf.SourceContext.Builder) getSourceContextFieldBuilder().getBuilder();
        }

        public SourceContextOrBuilder getSourceContextOrBuilder() {
            if (this.sourceContextBuilder_ != null) {
                return (SourceContextOrBuilder) this.sourceContextBuilder_.getMessageOrBuilder();
            }
            return this.sourceContext_ == null ? SourceContext.getDefaultInstance() : this.sourceContext_;
        }

        private SingleFieldBuilderV3<SourceContext, com.google.protobuf.SourceContext.Builder, SourceContextOrBuilder> getSourceContextFieldBuilder() {
            if (this.sourceContextBuilder_ == null) {
                this.sourceContextBuilder_ = new SingleFieldBuilderV3(getSourceContext(), getParentForChildren(), isClean());
                this.sourceContext_ = null;
            }
            return this.sourceContextBuilder_;
        }

        public int getSyntaxValue() {
            return this.syntax_;
        }

        public Builder setSyntaxValue(int i) {
            this.syntax_ = i;
            onChanged();
            return this;
        }

        public Syntax getSyntax() {
            Syntax valueOf = Syntax.valueOf(this.syntax_);
            return valueOf == null ? Syntax.UNRECOGNIZED : valueOf;
        }

        public Builder setSyntax(Syntax syntax) {
            if (syntax == null) {
                throw new NullPointerException();
            }
            this.syntax_ = syntax.getNumber();
            onChanged();
            return this;
        }

        public Builder clearSyntax() {
            this.syntax_ = 0;
            onChanged();
            return this;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private Enum(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Enum() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.enumvalue_ = Collections.emptyList();
        this.options_ = Collections.emptyList();
        this.syntax_ = 0;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Enum(com.google.protobuf.CodedInputStream r10, com.google.protobuf.ExtensionRegistryLite r11) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
        r9 = this;
        r1 = 1;
        r2 = 0;
        r7 = 4;
        r6 = 2;
        r9.<init>();
        r5 = com.google.protobuf.UnknownFieldSet.newBuilder();
        r3 = r2;
    L_0x000c:
        if (r2 != 0) goto L_0x0099;
    L_0x000e:
        r0 = r10.readTag();	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        switch(r0) {
            case 0: goto L_0x0020;
            case 10: goto L_0x0023;
            case 18: goto L_0x002c;
            case 26: goto L_0x004a;
            case 34: goto L_0x0068;
            case 40: goto L_0x0090;
            default: goto L_0x0015;
        };	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
    L_0x0015:
        r0 = r9.parseUnknownFieldProto3(r10, r5, r11, r0);	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        if (r0 != 0) goto L_0x00fb;
    L_0x001b:
        r0 = r1;
        r2 = r3;
    L_0x001d:
        r3 = r2;
        r2 = r0;
        goto L_0x000c;
    L_0x0020:
        r0 = r1;
        r2 = r3;
        goto L_0x001d;
    L_0x0023:
        r0 = r10.readStringRequireUtf8();	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r9.name_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r0 = r2;
        r2 = r3;
        goto L_0x001d;
    L_0x002c:
        r0 = r3 & 2;
        if (r0 == r6) goto L_0x0105;
    L_0x0030:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r9.enumvalue_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r0 = r3 | 2;
    L_0x0039:
        r3 = r9.enumvalue_;	 Catch:{ InvalidProtocolBufferException -> 0x00f7, IOException -> 0x00f3, all -> 0x00ef }
        r4 = com.google.protobuf.EnumValue.parser();	 Catch:{ InvalidProtocolBufferException -> 0x00f7, IOException -> 0x00f3, all -> 0x00ef }
        r4 = r10.readMessage(r4, r11);	 Catch:{ InvalidProtocolBufferException -> 0x00f7, IOException -> 0x00f3, all -> 0x00ef }
        r3.add(r4);	 Catch:{ InvalidProtocolBufferException -> 0x00f7, IOException -> 0x00f3, all -> 0x00ef }
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x001d;
    L_0x004a:
        r0 = r3 & 4;
        if (r0 == r7) goto L_0x0102;
    L_0x004e:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r9.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r0 = r3 | 4;
    L_0x0057:
        r3 = r9.options_;	 Catch:{ InvalidProtocolBufferException -> 0x00f7, IOException -> 0x00f3, all -> 0x00ef }
        r4 = com.google.protobuf.Option.parser();	 Catch:{ InvalidProtocolBufferException -> 0x00f7, IOException -> 0x00f3, all -> 0x00ef }
        r4 = r10.readMessage(r4, r11);	 Catch:{ InvalidProtocolBufferException -> 0x00f7, IOException -> 0x00f3, all -> 0x00ef }
        r3.add(r4);	 Catch:{ InvalidProtocolBufferException -> 0x00f7, IOException -> 0x00f3, all -> 0x00ef }
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x001d;
    L_0x0068:
        r0 = 0;
        r4 = r9.sourceContext_;	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        if (r4 == 0) goto L_0x00ff;
    L_0x006d:
        r0 = r9.sourceContext_;	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r0 = r0.toBuilder();	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r4 = r0;
    L_0x0074:
        r0 = com.google.protobuf.SourceContext.parser();	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r0 = r10.readMessage(r0, r11);	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r0 = (com.google.protobuf.SourceContext) r0;	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r9.sourceContext_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        if (r4 == 0) goto L_0x00fb;
    L_0x0082:
        r0 = r9.sourceContext_;	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r4.mergeFrom(r0);	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r0 = r4.buildPartial();	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r9.sourceContext_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r0 = r2;
        r2 = r3;
        goto L_0x001d;
    L_0x0090:
        r0 = r10.readEnum();	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r9.syntax_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00bb, IOException -> 0x00e4 }
        r0 = r2;
        r2 = r3;
        goto L_0x001d;
    L_0x0099:
        r0 = r3 & 2;
        if (r0 != r6) goto L_0x00a5;
    L_0x009d:
        r0 = r9.enumvalue_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r9.enumvalue_ = r0;
    L_0x00a5:
        r0 = r3 & 4;
        if (r0 != r7) goto L_0x00b1;
    L_0x00a9:
        r0 = r9.options_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r9.options_ = r0;
    L_0x00b1:
        r0 = r5.build();
        r9.unknownFields = r0;
        r9.makeExtensionsImmutable();
        return;
    L_0x00bb:
        r0 = move-exception;
    L_0x00bc:
        r0 = r0.setUnfinishedMessage(r9);	 Catch:{ all -> 0x00c1 }
        throw r0;	 Catch:{ all -> 0x00c1 }
    L_0x00c1:
        r0 = move-exception;
    L_0x00c2:
        r1 = r3 & 2;
        if (r1 != r6) goto L_0x00ce;
    L_0x00c6:
        r1 = r9.enumvalue_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r9.enumvalue_ = r1;
    L_0x00ce:
        r1 = r3 & 4;
        if (r1 != r7) goto L_0x00da;
    L_0x00d2:
        r1 = r9.options_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r9.options_ = r1;
    L_0x00da:
        r1 = r5.build();
        r9.unknownFields = r1;
        r9.makeExtensionsImmutable();
        throw r0;
    L_0x00e4:
        r0 = move-exception;
    L_0x00e5:
        r1 = new com.google.protobuf.InvalidProtocolBufferException;	 Catch:{ all -> 0x00c1 }
        r1.<init>(r0);	 Catch:{ all -> 0x00c1 }
        r0 = r1.setUnfinishedMessage(r9);	 Catch:{ all -> 0x00c1 }
        throw r0;	 Catch:{ all -> 0x00c1 }
    L_0x00ef:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x00c2;
    L_0x00f3:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x00e5;
    L_0x00f7:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x00bc;
    L_0x00fb:
        r0 = r2;
        r2 = r3;
        goto L_0x001d;
    L_0x00ff:
        r4 = r0;
        goto L_0x0074;
    L_0x0102:
        r0 = r3;
        goto L_0x0057;
    L_0x0105:
        r0 = r3;
        goto L_0x0039;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Enum.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
    }

    public static final Descriptor getDescriptor() {
        return TypeProto.internal_static_google_protobuf_Enum_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return TypeProto.internal_static_google_protobuf_Enum_fieldAccessorTable.ensureFieldAccessorsInitialized(Enum.class, Builder.class);
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

    public List<EnumValue> getEnumvalueList() {
        return this.enumvalue_;
    }

    public List<? extends EnumValueOrBuilder> getEnumvalueOrBuilderList() {
        return this.enumvalue_;
    }

    public int getEnumvalueCount() {
        return this.enumvalue_.size();
    }

    public EnumValue getEnumvalue(int i) {
        return (EnumValue) this.enumvalue_.get(i);
    }

    public EnumValueOrBuilder getEnumvalueOrBuilder(int i) {
        return (EnumValueOrBuilder) this.enumvalue_.get(i);
    }

    public List<Option> getOptionsList() {
        return this.options_;
    }

    public List<? extends OptionOrBuilder> getOptionsOrBuilderList() {
        return this.options_;
    }

    public int getOptionsCount() {
        return this.options_.size();
    }

    public Option getOptions(int i) {
        return (Option) this.options_.get(i);
    }

    public OptionOrBuilder getOptionsOrBuilder(int i) {
        return (OptionOrBuilder) this.options_.get(i);
    }

    public boolean hasSourceContext() {
        return this.sourceContext_ != null;
    }

    public SourceContext getSourceContext() {
        return this.sourceContext_ == null ? SourceContext.getDefaultInstance() : this.sourceContext_;
    }

    public SourceContextOrBuilder getSourceContextOrBuilder() {
        return getSourceContext();
    }

    public int getSyntaxValue() {
        return this.syntax_;
    }

    public Syntax getSyntax() {
        Syntax valueOf = Syntax.valueOf(this.syntax_);
        return valueOf == null ? Syntax.UNRECOGNIZED : valueOf;
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
        int i = 0;
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        for (int i2 = 0; i2 < this.enumvalue_.size(); i2++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.enumvalue_.get(i2));
        }
        while (i < this.options_.size()) {
            codedOutputStream.writeMessage(3, (MessageLite) this.options_.get(i));
            i++;
        }
        if (this.sourceContext_ != null) {
            codedOutputStream.writeMessage(4, getSourceContext());
        }
        if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
            codedOutputStream.writeEnum(5, this.syntax_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = 0;
        int i2 = this.memoizedSize;
        if (i2 != -1) {
            return i2;
        }
        if (getNameBytes().isEmpty()) {
            i2 = 0;
        } else {
            i2 = GeneratedMessageV3.computeStringSize(1, this.name_) + 0;
        }
        int i3 = i2;
        for (int i4 = 0; i4 < this.enumvalue_.size(); i4++) {
            i3 += CodedOutputStream.computeMessageSize(2, (MessageLite) this.enumvalue_.get(i4));
        }
        while (i < this.options_.size()) {
            i3 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.options_.get(i));
            i++;
        }
        if (this.sourceContext_ != null) {
            i3 += CodedOutputStream.computeMessageSize(4, getSourceContext());
        }
        if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
            i3 += CodedOutputStream.computeEnumSize(5, this.syntax_);
        }
        i2 = this.unknownFields.getSerializedSize() + i3;
        this.memoizedSize = i2;
        return i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Enum)) {
            return super.equals(obj);
        }
        boolean z;
        Enum enumR = (Enum) obj;
        if ((getName().equals(enumR.getName())) && getEnumvalueList().equals(enumR.getEnumvalueList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getOptionsList().equals(enumR.getOptionsList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && hasSourceContext() == enumR.hasSourceContext()) {
            z = true;
        } else {
            z = false;
        }
        if (hasSourceContext()) {
            if (z && getSourceContext().equals(enumR.getSourceContext())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && this.syntax_ == enumR.syntax_) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.unknownFields.equals(enumR.unknownFields)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53) + getName().hashCode();
        if (getEnumvalueCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getEnumvalueList().hashCode();
        }
        if (getOptionsCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getOptionsList().hashCode();
        }
        if (hasSourceContext()) {
            hashCode = (((hashCode * 37) + 4) * 53) + getSourceContext().hashCode();
        }
        hashCode = (((((hashCode * 37) + 5) * 53) + this.syntax_) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static Enum parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Enum) PARSER.parseFrom(byteBuffer);
    }

    public static Enum parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Enum) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Enum parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Enum) PARSER.parseFrom(byteString);
    }

    public static Enum parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Enum) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Enum parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Enum) PARSER.parseFrom(bArr);
    }

    public static Enum parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Enum) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Enum parseFrom(InputStream inputStream) throws IOException {
        return (Enum) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Enum parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Enum) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Enum parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Enum) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Enum parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Enum) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Enum parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Enum) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Enum parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Enum) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Enum enumR) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(enumR);
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

    public static Enum getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Enum> parser() {
        return PARSER;
    }

    public Parser<Enum> getParserForType() {
        return PARSER;
    }

    public Enum getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
