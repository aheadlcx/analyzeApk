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

public final class Type extends GeneratedMessageV3 implements TypeOrBuilder {
    private static final Type DEFAULT_INSTANCE = new Type();
    public static final int FIELDS_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int ONEOFS_FIELD_NUMBER = 3;
    public static final int OPTIONS_FIELD_NUMBER = 4;
    private static final Parser<Type> PARSER = new AbstractParser<Type>() {
        public Type parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Type(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SOURCE_CONTEXT_FIELD_NUMBER = 5;
    public static final int SYNTAX_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private List<Field> fields_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private LazyStringList oneofs_;
    private List<Option> options_;
    private SourceContext sourceContext_;
    private int syntax_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements TypeOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> fieldsBuilder_;
        private List<Field> fields_;
        private Object name_;
        private LazyStringList oneofs_;
        private RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> optionsBuilder_;
        private List<Option> options_;
        private SingleFieldBuilderV3<SourceContext, com.google.protobuf.SourceContext.Builder, SourceContextOrBuilder> sourceContextBuilder_;
        private SourceContext sourceContext_;
        private int syntax_;

        public static final Descriptor getDescriptor() {
            return TypeProto.internal_static_google_protobuf_Type_descriptor;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return TypeProto.internal_static_google_protobuf_Type_fieldAccessorTable.ensureFieldAccessorsInitialized(Type.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.fields_ = Collections.emptyList();
            this.oneofs_ = LazyStringArrayList.EMPTY;
            this.options_ = Collections.emptyList();
            this.sourceContext_ = null;
            this.syntax_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.fields_ = Collections.emptyList();
            this.oneofs_ = LazyStringArrayList.EMPTY;
            this.options_ = Collections.emptyList();
            this.sourceContext_ = null;
            this.syntax_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                getFieldsFieldBuilder();
                getOptionsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            if (this.fieldsBuilder_ == null) {
                this.fields_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                this.fieldsBuilder_.clear();
            }
            this.oneofs_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            if (this.optionsBuilder_ == null) {
                this.options_ = Collections.emptyList();
                this.bitField0_ &= -9;
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
            return TypeProto.internal_static_google_protobuf_Type_descriptor;
        }

        public Type getDefaultInstanceForType() {
            return Type.getDefaultInstance();
        }

        public Type build() {
            Object buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
        }

        public Type buildPartial() {
            Type type = new Type((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            type.name_ = this.name_;
            if (this.fieldsBuilder_ == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.fields_ = Collections.unmodifiableList(this.fields_);
                    this.bitField0_ &= -3;
                }
                type.fields_ = this.fields_;
            } else {
                type.fields_ = this.fieldsBuilder_.build();
            }
            if ((this.bitField0_ & 4) == 4) {
                this.oneofs_ = this.oneofs_.getUnmodifiableView();
                this.bitField0_ &= -5;
            }
            type.oneofs_ = this.oneofs_;
            if (this.optionsBuilder_ == null) {
                if ((this.bitField0_ & 8) == 8) {
                    this.options_ = Collections.unmodifiableList(this.options_);
                    this.bitField0_ &= -9;
                }
                type.options_ = this.options_;
            } else {
                type.options_ = this.optionsBuilder_.build();
            }
            if (this.sourceContextBuilder_ == null) {
                type.sourceContext_ = this.sourceContext_;
            } else {
                type.sourceContext_ = (SourceContext) this.sourceContextBuilder_.build();
            }
            type.syntax_ = this.syntax_;
            type.bitField0_ = 0;
            onBuilt();
            return type;
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
            if (message instanceof Type) {
                return mergeFrom((Type) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Type type) {
            RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
            if (type != Type.getDefaultInstance()) {
                if (!type.getName().isEmpty()) {
                    this.name_ = type.name_;
                    onChanged();
                }
                if (this.fieldsBuilder_ == null) {
                    if (!type.fields_.isEmpty()) {
                        if (this.fields_.isEmpty()) {
                            this.fields_ = type.fields_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureFieldsIsMutable();
                            this.fields_.addAll(type.fields_);
                        }
                        onChanged();
                    }
                } else if (!type.fields_.isEmpty()) {
                    if (this.fieldsBuilder_.isEmpty()) {
                        this.fieldsBuilder_.dispose();
                        this.fieldsBuilder_ = null;
                        this.fields_ = type.fields_;
                        this.bitField0_ &= -3;
                        this.fieldsBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getFieldsFieldBuilder() : null;
                    } else {
                        this.fieldsBuilder_.addAllMessages(type.fields_);
                    }
                }
                if (!type.oneofs_.isEmpty()) {
                    if (this.oneofs_.isEmpty()) {
                        this.oneofs_ = type.oneofs_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureOneofsIsMutable();
                        this.oneofs_.addAll(type.oneofs_);
                    }
                    onChanged();
                }
                if (this.optionsBuilder_ == null) {
                    if (!type.options_.isEmpty()) {
                        if (this.options_.isEmpty()) {
                            this.options_ = type.options_;
                            this.bitField0_ &= -9;
                        } else {
                            ensureOptionsIsMutable();
                            this.options_.addAll(type.options_);
                        }
                        onChanged();
                    }
                } else if (!type.options_.isEmpty()) {
                    if (this.optionsBuilder_.isEmpty()) {
                        this.optionsBuilder_.dispose();
                        this.optionsBuilder_ = null;
                        this.options_ = type.options_;
                        this.bitField0_ &= -9;
                        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getOptionsFieldBuilder();
                        }
                        this.optionsBuilder_ = repeatedFieldBuilderV3;
                    } else {
                        this.optionsBuilder_.addAllMessages(type.options_);
                    }
                }
                if (type.hasSourceContext()) {
                    mergeSourceContext(type.getSourceContext());
                }
                if (type.syntax_ != 0) {
                    setSyntaxValue(type.getSyntaxValue());
                }
                mergeUnknownFields(type.unknownFields);
                onChanged();
            }
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Throwable th;
            Type type;
            Type type2;
            try {
                type2 = (Type) Type.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                if (type2 != null) {
                    mergeFrom(type2);
                }
                return this;
            } catch (InvalidProtocolBufferException e) {
                InvalidProtocolBufferException invalidProtocolBufferException = e;
                type2 = (Type) invalidProtocolBufferException.getUnfinishedMessage();
                throw invalidProtocolBufferException.unwrapIOException();
            } catch (Throwable th2) {
                Throwable th3 = th2;
                type = type2;
                th = th3;
                if (type != null) {
                    mergeFrom(type);
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
            this.name_ = Type.getDefaultInstance().getName();
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

        private void ensureFieldsIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.fields_ = new ArrayList(this.fields_);
                this.bitField0_ |= 2;
            }
        }

        public List<Field> getFieldsList() {
            if (this.fieldsBuilder_ == null) {
                return Collections.unmodifiableList(this.fields_);
            }
            return this.fieldsBuilder_.getMessageList();
        }

        public int getFieldsCount() {
            if (this.fieldsBuilder_ == null) {
                return this.fields_.size();
            }
            return this.fieldsBuilder_.getCount();
        }

        public Field getFields(int i) {
            if (this.fieldsBuilder_ == null) {
                return (Field) this.fields_.get(i);
            }
            return (Field) this.fieldsBuilder_.getMessage(i);
        }

        public Builder setFields(int i, Field field) {
            if (this.fieldsBuilder_ != null) {
                this.fieldsBuilder_.setMessage(i, field);
            } else if (field == null) {
                throw new NullPointerException();
            } else {
                ensureFieldsIsMutable();
                this.fields_.set(i, field);
                onChanged();
            }
            return this;
        }

        public Builder setFields(int i, com.google.protobuf.Field.Builder builder) {
            if (this.fieldsBuilder_ == null) {
                ensureFieldsIsMutable();
                this.fields_.set(i, builder.build());
                onChanged();
            } else {
                this.fieldsBuilder_.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addFields(Field field) {
            if (this.fieldsBuilder_ != null) {
                this.fieldsBuilder_.addMessage(field);
            } else if (field == null) {
                throw new NullPointerException();
            } else {
                ensureFieldsIsMutable();
                this.fields_.add(field);
                onChanged();
            }
            return this;
        }

        public Builder addFields(int i, Field field) {
            if (this.fieldsBuilder_ != null) {
                this.fieldsBuilder_.addMessage(i, field);
            } else if (field == null) {
                throw new NullPointerException();
            } else {
                ensureFieldsIsMutable();
                this.fields_.add(i, field);
                onChanged();
            }
            return this;
        }

        public Builder addFields(com.google.protobuf.Field.Builder builder) {
            if (this.fieldsBuilder_ == null) {
                ensureFieldsIsMutable();
                this.fields_.add(builder.build());
                onChanged();
            } else {
                this.fieldsBuilder_.addMessage(builder.build());
            }
            return this;
        }

        public Builder addFields(int i, com.google.protobuf.Field.Builder builder) {
            if (this.fieldsBuilder_ == null) {
                ensureFieldsIsMutable();
                this.fields_.add(i, builder.build());
                onChanged();
            } else {
                this.fieldsBuilder_.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllFields(Iterable<? extends Field> iterable) {
            if (this.fieldsBuilder_ == null) {
                ensureFieldsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.fields_);
                onChanged();
            } else {
                this.fieldsBuilder_.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearFields() {
            if (this.fieldsBuilder_ == null) {
                this.fields_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                this.fieldsBuilder_.clear();
            }
            return this;
        }

        public Builder removeFields(int i) {
            if (this.fieldsBuilder_ == null) {
                ensureFieldsIsMutable();
                this.fields_.remove(i);
                onChanged();
            } else {
                this.fieldsBuilder_.remove(i);
            }
            return this;
        }

        public com.google.protobuf.Field.Builder getFieldsBuilder(int i) {
            return (com.google.protobuf.Field.Builder) getFieldsFieldBuilder().getBuilder(i);
        }

        public FieldOrBuilder getFieldsOrBuilder(int i) {
            if (this.fieldsBuilder_ == null) {
                return (FieldOrBuilder) this.fields_.get(i);
            }
            return (FieldOrBuilder) this.fieldsBuilder_.getMessageOrBuilder(i);
        }

        public List<? extends FieldOrBuilder> getFieldsOrBuilderList() {
            if (this.fieldsBuilder_ != null) {
                return this.fieldsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.fields_);
        }

        public com.google.protobuf.Field.Builder addFieldsBuilder() {
            return (com.google.protobuf.Field.Builder) getFieldsFieldBuilder().addBuilder(Field.getDefaultInstance());
        }

        public com.google.protobuf.Field.Builder addFieldsBuilder(int i) {
            return (com.google.protobuf.Field.Builder) getFieldsFieldBuilder().addBuilder(i, Field.getDefaultInstance());
        }

        public List<com.google.protobuf.Field.Builder> getFieldsBuilderList() {
            return getFieldsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> getFieldsFieldBuilder() {
            if (this.fieldsBuilder_ == null) {
                this.fieldsBuilder_ = new RepeatedFieldBuilderV3(this.fields_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.fields_ = null;
            }
            return this.fieldsBuilder_;
        }

        private void ensureOneofsIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.oneofs_ = new LazyStringArrayList(this.oneofs_);
                this.bitField0_ |= 4;
            }
        }

        public ProtocolStringList getOneofsList() {
            return this.oneofs_.getUnmodifiableView();
        }

        public int getOneofsCount() {
            return this.oneofs_.size();
        }

        public String getOneofs(int i) {
            return (String) this.oneofs_.get(i);
        }

        public ByteString getOneofsBytes(int i) {
            return this.oneofs_.getByteString(i);
        }

        public Builder setOneofs(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureOneofsIsMutable();
            this.oneofs_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addOneofs(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureOneofsIsMutable();
            this.oneofs_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllOneofs(Iterable<String> iterable) {
            ensureOneofsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.oneofs_);
            onChanged();
            return this;
        }

        public Builder clearOneofs() {
            this.oneofs_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            onChanged();
            return this;
        }

        public Builder addOneofsBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            ensureOneofsIsMutable();
            this.oneofs_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureOptionsIsMutable() {
            if ((this.bitField0_ & 8) != 8) {
                this.options_ = new ArrayList(this.options_);
                this.bitField0_ |= 8;
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
                this.bitField0_ &= -9;
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
                this.optionsBuilder_ = new RepeatedFieldBuilderV3(this.options_, (this.bitField0_ & 8) == 8, getParentForChildren(), isClean());
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

    private Type(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Type() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.fields_ = Collections.emptyList();
        this.oneofs_ = LazyStringArrayList.EMPTY;
        this.options_ = Collections.emptyList();
        this.syntax_ = 0;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Type(com.google.protobuf.CodedInputStream r11, com.google.protobuf.ExtensionRegistryLite r12) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
        r10 = this;
        r1 = 1;
        r2 = 0;
        r8 = 8;
        r7 = 4;
        r6 = 2;
        r10.<init>();
        r5 = com.google.protobuf.UnknownFieldSet.newBuilder();
        r3 = r2;
    L_0x000e:
        if (r2 != 0) goto L_0x00b7;
    L_0x0010:
        r0 = r11.readTag();	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        switch(r0) {
            case 0: goto L_0x0022;
            case 10: goto L_0x0025;
            case 18: goto L_0x002e;
            case 26: goto L_0x004c;
            case 34: goto L_0x0066;
            case 42: goto L_0x0084;
            case 48: goto L_0x00ad;
            default: goto L_0x0017;
        };	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
    L_0x0017:
        r0 = r10.parseUnknownFieldProto3(r11, r5, r12, r0);	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        if (r0 != 0) goto L_0x0131;
    L_0x001d:
        r0 = r1;
        r2 = r3;
    L_0x001f:
        r3 = r2;
        r2 = r0;
        goto L_0x000e;
    L_0x0022:
        r0 = r1;
        r2 = r3;
        goto L_0x001f;
    L_0x0025:
        r0 = r11.readStringRequireUtf8();	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r10.name_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r0 = r2;
        r2 = r3;
        goto L_0x001f;
    L_0x002e:
        r0 = r3 & 2;
        if (r0 == r6) goto L_0x013e;
    L_0x0032:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r10.fields_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r0 = r3 | 2;
    L_0x003b:
        r3 = r10.fields_;	 Catch:{ InvalidProtocolBufferException -> 0x012d, IOException -> 0x0129, all -> 0x0125 }
        r4 = com.google.protobuf.Field.parser();	 Catch:{ InvalidProtocolBufferException -> 0x012d, IOException -> 0x0129, all -> 0x0125 }
        r4 = r11.readMessage(r4, r12);	 Catch:{ InvalidProtocolBufferException -> 0x012d, IOException -> 0x0129, all -> 0x0125 }
        r3.add(r4);	 Catch:{ InvalidProtocolBufferException -> 0x012d, IOException -> 0x0129, all -> 0x0125 }
        r9 = r2;
        r2 = r0;
        r0 = r9;
        goto L_0x001f;
    L_0x004c:
        r4 = r11.readStringRequireUtf8();	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r0 = r3 & 4;
        if (r0 == r7) goto L_0x013b;
    L_0x0054:
        r0 = new com.google.protobuf.LazyStringArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r10.oneofs_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r0 = r3 | 4;
    L_0x005d:
        r3 = r10.oneofs_;	 Catch:{ InvalidProtocolBufferException -> 0x012d, IOException -> 0x0129, all -> 0x0125 }
        r3.add(r4);	 Catch:{ InvalidProtocolBufferException -> 0x012d, IOException -> 0x0129, all -> 0x0125 }
        r9 = r2;
        r2 = r0;
        r0 = r9;
        goto L_0x001f;
    L_0x0066:
        r0 = r3 & 8;
        if (r0 == r8) goto L_0x0138;
    L_0x006a:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r10.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r0 = r3 | 8;
    L_0x0073:
        r3 = r10.options_;	 Catch:{ InvalidProtocolBufferException -> 0x012d, IOException -> 0x0129, all -> 0x0125 }
        r4 = com.google.protobuf.Option.parser();	 Catch:{ InvalidProtocolBufferException -> 0x012d, IOException -> 0x0129, all -> 0x0125 }
        r4 = r11.readMessage(r4, r12);	 Catch:{ InvalidProtocolBufferException -> 0x012d, IOException -> 0x0129, all -> 0x0125 }
        r3.add(r4);	 Catch:{ InvalidProtocolBufferException -> 0x012d, IOException -> 0x0129, all -> 0x0125 }
        r9 = r2;
        r2 = r0;
        r0 = r9;
        goto L_0x001f;
    L_0x0084:
        r0 = 0;
        r4 = r10.sourceContext_;	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        if (r4 == 0) goto L_0x0135;
    L_0x0089:
        r0 = r10.sourceContext_;	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r0 = r0.toBuilder();	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r4 = r0;
    L_0x0090:
        r0 = com.google.protobuf.SourceContext.parser();	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r0 = r11.readMessage(r0, r12);	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r0 = (com.google.protobuf.SourceContext) r0;	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r10.sourceContext_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        if (r4 == 0) goto L_0x0131;
    L_0x009e:
        r0 = r10.sourceContext_;	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r4.mergeFrom(r0);	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r0 = r4.buildPartial();	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r10.sourceContext_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r0 = r2;
        r2 = r3;
        goto L_0x001f;
    L_0x00ad:
        r0 = r11.readEnum();	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r10.syntax_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00e5, IOException -> 0x011a }
        r0 = r2;
        r2 = r3;
        goto L_0x001f;
    L_0x00b7:
        r0 = r3 & 2;
        if (r0 != r6) goto L_0x00c3;
    L_0x00bb:
        r0 = r10.fields_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r10.fields_ = r0;
    L_0x00c3:
        r0 = r3 & 4;
        if (r0 != r7) goto L_0x00cf;
    L_0x00c7:
        r0 = r10.oneofs_;
        r0 = r0.getUnmodifiableView();
        r10.oneofs_ = r0;
    L_0x00cf:
        r0 = r3 & 8;
        if (r0 != r8) goto L_0x00db;
    L_0x00d3:
        r0 = r10.options_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r10.options_ = r0;
    L_0x00db:
        r0 = r5.build();
        r10.unknownFields = r0;
        r10.makeExtensionsImmutable();
        return;
    L_0x00e5:
        r0 = move-exception;
    L_0x00e6:
        r0 = r0.setUnfinishedMessage(r10);	 Catch:{ all -> 0x00eb }
        throw r0;	 Catch:{ all -> 0x00eb }
    L_0x00eb:
        r0 = move-exception;
    L_0x00ec:
        r1 = r3 & 2;
        if (r1 != r6) goto L_0x00f8;
    L_0x00f0:
        r1 = r10.fields_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r10.fields_ = r1;
    L_0x00f8:
        r1 = r3 & 4;
        if (r1 != r7) goto L_0x0104;
    L_0x00fc:
        r1 = r10.oneofs_;
        r1 = r1.getUnmodifiableView();
        r10.oneofs_ = r1;
    L_0x0104:
        r1 = r3 & 8;
        if (r1 != r8) goto L_0x0110;
    L_0x0108:
        r1 = r10.options_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r10.options_ = r1;
    L_0x0110:
        r1 = r5.build();
        r10.unknownFields = r1;
        r10.makeExtensionsImmutable();
        throw r0;
    L_0x011a:
        r0 = move-exception;
    L_0x011b:
        r1 = new com.google.protobuf.InvalidProtocolBufferException;	 Catch:{ all -> 0x00eb }
        r1.<init>(r0);	 Catch:{ all -> 0x00eb }
        r0 = r1.setUnfinishedMessage(r10);	 Catch:{ all -> 0x00eb }
        throw r0;	 Catch:{ all -> 0x00eb }
    L_0x0125:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x00ec;
    L_0x0129:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x011b;
    L_0x012d:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x00e6;
    L_0x0131:
        r0 = r2;
        r2 = r3;
        goto L_0x001f;
    L_0x0135:
        r4 = r0;
        goto L_0x0090;
    L_0x0138:
        r0 = r3;
        goto L_0x0073;
    L_0x013b:
        r0 = r3;
        goto L_0x005d;
    L_0x013e:
        r0 = r3;
        goto L_0x003b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Type.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
    }

    public static final Descriptor getDescriptor() {
        return TypeProto.internal_static_google_protobuf_Type_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return TypeProto.internal_static_google_protobuf_Type_fieldAccessorTable.ensureFieldAccessorsInitialized(Type.class, Builder.class);
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

    public List<Field> getFieldsList() {
        return this.fields_;
    }

    public List<? extends FieldOrBuilder> getFieldsOrBuilderList() {
        return this.fields_;
    }

    public int getFieldsCount() {
        return this.fields_.size();
    }

    public Field getFields(int i) {
        return (Field) this.fields_.get(i);
    }

    public FieldOrBuilder getFieldsOrBuilder(int i) {
        return (FieldOrBuilder) this.fields_.get(i);
    }

    public ProtocolStringList getOneofsList() {
        return this.oneofs_;
    }

    public int getOneofsCount() {
        return this.oneofs_.size();
    }

    public String getOneofs(int i) {
        return (String) this.oneofs_.get(i);
    }

    public ByteString getOneofsBytes(int i) {
        return this.oneofs_.getByteString(i);
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
        for (int i2 = 0; i2 < this.fields_.size(); i2++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.fields_.get(i2));
        }
        for (int i3 = 0; i3 < this.oneofs_.size(); i3++) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.oneofs_.getRaw(i3));
        }
        while (i < this.options_.size()) {
            codedOutputStream.writeMessage(4, (MessageLite) this.options_.get(i));
            i++;
        }
        if (this.sourceContext_ != null) {
            codedOutputStream.writeMessage(5, getSourceContext());
        }
        if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
            codedOutputStream.writeEnum(6, this.syntax_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = 0;
        int i2 = this.memoizedSize;
        if (i2 != -1) {
            return i2;
        }
        int i3;
        if (getNameBytes().isEmpty()) {
            i2 = 0;
        } else {
            i2 = GeneratedMessageV3.computeStringSize(1, this.name_) + 0;
        }
        int i4 = i2;
        for (i3 = 0; i3 < this.fields_.size(); i3++) {
            i4 += CodedOutputStream.computeMessageSize(2, (MessageLite) this.fields_.get(i3));
        }
        i3 = 0;
        for (i2 = 0; i2 < this.oneofs_.size(); i2++) {
            i3 += GeneratedMessageV3.computeStringSizeNoTag(this.oneofs_.getRaw(i2));
        }
        i3 = (i4 + i3) + (getOneofsList().size() * 1);
        while (i < this.options_.size()) {
            i++;
            i3 = CodedOutputStream.computeMessageSize(4, (MessageLite) this.options_.get(i)) + i3;
        }
        if (this.sourceContext_ != null) {
            i3 += CodedOutputStream.computeMessageSize(5, getSourceContext());
        }
        if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
            i3 += CodedOutputStream.computeEnumSize(6, this.syntax_);
        }
        i2 = this.unknownFields.getSerializedSize() + i3;
        this.memoizedSize = i2;
        return i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Type)) {
            return super.equals(obj);
        }
        boolean z;
        Type type = (Type) obj;
        if ((getName().equals(type.getName())) && getFieldsList().equals(type.getFieldsList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getOneofsList().equals(type.getOneofsList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getOptionsList().equals(type.getOptionsList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && hasSourceContext() == type.hasSourceContext()) {
            z = true;
        } else {
            z = false;
        }
        if (hasSourceContext()) {
            if (z && getSourceContext().equals(type.getSourceContext())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && this.syntax_ == type.syntax_) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.unknownFields.equals(type.unknownFields)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53) + getName().hashCode();
        if (getFieldsCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getFieldsList().hashCode();
        }
        if (getOneofsCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getOneofsList().hashCode();
        }
        if (getOptionsCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getOptionsList().hashCode();
        }
        if (hasSourceContext()) {
            hashCode = (((hashCode * 37) + 5) * 53) + getSourceContext().hashCode();
        }
        hashCode = (((((hashCode * 37) + 6) * 53) + this.syntax_) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static Type parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Type) PARSER.parseFrom(byteBuffer);
    }

    public static Type parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Type) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Type parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Type) PARSER.parseFrom(byteString);
    }

    public static Type parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Type) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Type parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Type) PARSER.parseFrom(bArr);
    }

    public static Type parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Type) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Type parseFrom(InputStream inputStream) throws IOException {
        return (Type) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Type parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Type) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Type parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Type) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Type parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Type) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Type parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Type) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Type parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Type) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Type type) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(type);
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

    public static Type getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Type> parser() {
        return PARSER;
    }

    public Parser<Type> getParserForType() {
        return PARSER;
    }

    public Type getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
