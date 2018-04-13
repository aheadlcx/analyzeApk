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

public final class Api extends GeneratedMessageV3 implements ApiOrBuilder {
    private static final Api DEFAULT_INSTANCE = new Api();
    public static final int METHODS_FIELD_NUMBER = 2;
    public static final int MIXINS_FIELD_NUMBER = 6;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int OPTIONS_FIELD_NUMBER = 3;
    private static final Parser<Api> PARSER = new AbstractParser<Api>() {
        public Api parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Api(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SOURCE_CONTEXT_FIELD_NUMBER = 5;
    public static final int SYNTAX_FIELD_NUMBER = 7;
    public static final int VERSION_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private byte memoizedIsInitialized;
    private List<Method> methods_;
    private List<Mixin> mixins_;
    private volatile Object name_;
    private List<Option> options_;
    private SourceContext sourceContext_;
    private int syntax_;
    private volatile Object version_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ApiOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> methodsBuilder_;
        private List<Method> methods_;
        private RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> mixinsBuilder_;
        private List<Mixin> mixins_;
        private Object name_;
        private RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> optionsBuilder_;
        private List<Option> options_;
        private SingleFieldBuilderV3<SourceContext, com.google.protobuf.SourceContext.Builder, SourceContextOrBuilder> sourceContextBuilder_;
        private SourceContext sourceContext_;
        private int syntax_;
        private Object version_;

        public static final Descriptor getDescriptor() {
            return ApiProto.internal_static_google_protobuf_Api_descriptor;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return ApiProto.internal_static_google_protobuf_Api_fieldAccessorTable.ensureFieldAccessorsInitialized(Api.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.methods_ = Collections.emptyList();
            this.options_ = Collections.emptyList();
            this.version_ = "";
            this.sourceContext_ = null;
            this.mixins_ = Collections.emptyList();
            this.syntax_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.methods_ = Collections.emptyList();
            this.options_ = Collections.emptyList();
            this.version_ = "";
            this.sourceContext_ = null;
            this.mixins_ = Collections.emptyList();
            this.syntax_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                getMethodsFieldBuilder();
                getOptionsFieldBuilder();
                getMixinsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            if (this.methodsBuilder_ == null) {
                this.methods_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                this.methodsBuilder_.clear();
            }
            if (this.optionsBuilder_ == null) {
                this.options_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                this.optionsBuilder_.clear();
            }
            this.version_ = "";
            if (this.sourceContextBuilder_ == null) {
                this.sourceContext_ = null;
            } else {
                this.sourceContext_ = null;
                this.sourceContextBuilder_ = null;
            }
            if (this.mixinsBuilder_ == null) {
                this.mixins_ = Collections.emptyList();
                this.bitField0_ &= -33;
            } else {
                this.mixinsBuilder_.clear();
            }
            this.syntax_ = 0;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ApiProto.internal_static_google_protobuf_Api_descriptor;
        }

        public Api getDefaultInstanceForType() {
            return Api.getDefaultInstance();
        }

        public Api build() {
            Object buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
        }

        public Api buildPartial() {
            Api api = new Api((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            api.name_ = this.name_;
            if (this.methodsBuilder_ == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.methods_ = Collections.unmodifiableList(this.methods_);
                    this.bitField0_ &= -3;
                }
                api.methods_ = this.methods_;
            } else {
                api.methods_ = this.methodsBuilder_.build();
            }
            if (this.optionsBuilder_ == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.options_ = Collections.unmodifiableList(this.options_);
                    this.bitField0_ &= -5;
                }
                api.options_ = this.options_;
            } else {
                api.options_ = this.optionsBuilder_.build();
            }
            api.version_ = this.version_;
            if (this.sourceContextBuilder_ == null) {
                api.sourceContext_ = this.sourceContext_;
            } else {
                api.sourceContext_ = (SourceContext) this.sourceContextBuilder_.build();
            }
            if (this.mixinsBuilder_ == null) {
                if ((this.bitField0_ & 32) == 32) {
                    this.mixins_ = Collections.unmodifiableList(this.mixins_);
                    this.bitField0_ &= -33;
                }
                api.mixins_ = this.mixins_;
            } else {
                api.mixins_ = this.mixinsBuilder_.build();
            }
            api.syntax_ = this.syntax_;
            api.bitField0_ = 0;
            onBuilt();
            return api;
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
            if (message instanceof Api) {
                return mergeFrom((Api) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Api api) {
            RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
            if (api != Api.getDefaultInstance()) {
                if (!api.getName().isEmpty()) {
                    this.name_ = api.name_;
                    onChanged();
                }
                if (this.methodsBuilder_ == null) {
                    if (!api.methods_.isEmpty()) {
                        if (this.methods_.isEmpty()) {
                            this.methods_ = api.methods_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureMethodsIsMutable();
                            this.methods_.addAll(api.methods_);
                        }
                        onChanged();
                    }
                } else if (!api.methods_.isEmpty()) {
                    if (this.methodsBuilder_.isEmpty()) {
                        this.methodsBuilder_.dispose();
                        this.methodsBuilder_ = null;
                        this.methods_ = api.methods_;
                        this.bitField0_ &= -3;
                        this.methodsBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getMethodsFieldBuilder() : null;
                    } else {
                        this.methodsBuilder_.addAllMessages(api.methods_);
                    }
                }
                if (this.optionsBuilder_ == null) {
                    if (!api.options_.isEmpty()) {
                        if (this.options_.isEmpty()) {
                            this.options_ = api.options_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureOptionsIsMutable();
                            this.options_.addAll(api.options_);
                        }
                        onChanged();
                    }
                } else if (!api.options_.isEmpty()) {
                    if (this.optionsBuilder_.isEmpty()) {
                        this.optionsBuilder_.dispose();
                        this.optionsBuilder_ = null;
                        this.options_ = api.options_;
                        this.bitField0_ &= -5;
                        this.optionsBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getOptionsFieldBuilder() : null;
                    } else {
                        this.optionsBuilder_.addAllMessages(api.options_);
                    }
                }
                if (!api.getVersion().isEmpty()) {
                    this.version_ = api.version_;
                    onChanged();
                }
                if (api.hasSourceContext()) {
                    mergeSourceContext(api.getSourceContext());
                }
                if (this.mixinsBuilder_ == null) {
                    if (!api.mixins_.isEmpty()) {
                        if (this.mixins_.isEmpty()) {
                            this.mixins_ = api.mixins_;
                            this.bitField0_ &= -33;
                        } else {
                            ensureMixinsIsMutable();
                            this.mixins_.addAll(api.mixins_);
                        }
                        onChanged();
                    }
                } else if (!api.mixins_.isEmpty()) {
                    if (this.mixinsBuilder_.isEmpty()) {
                        this.mixinsBuilder_.dispose();
                        this.mixinsBuilder_ = null;
                        this.mixins_ = api.mixins_;
                        this.bitField0_ &= -33;
                        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getMixinsFieldBuilder();
                        }
                        this.mixinsBuilder_ = repeatedFieldBuilderV3;
                    } else {
                        this.mixinsBuilder_.addAllMessages(api.mixins_);
                    }
                }
                if (api.syntax_ != 0) {
                    setSyntaxValue(api.getSyntaxValue());
                }
                mergeUnknownFields(api.unknownFields);
                onChanged();
            }
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Throwable th;
            Api api;
            Api api2;
            try {
                api2 = (Api) Api.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                if (api2 != null) {
                    mergeFrom(api2);
                }
                return this;
            } catch (InvalidProtocolBufferException e) {
                InvalidProtocolBufferException invalidProtocolBufferException = e;
                api2 = (Api) invalidProtocolBufferException.getUnfinishedMessage();
                throw invalidProtocolBufferException.unwrapIOException();
            } catch (Throwable th2) {
                Throwable th3 = th2;
                api = api2;
                th = th3;
                if (api != null) {
                    mergeFrom(api);
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
            this.name_ = Api.getDefaultInstance().getName();
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

        private void ensureMethodsIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.methods_ = new ArrayList(this.methods_);
                this.bitField0_ |= 2;
            }
        }

        public List<Method> getMethodsList() {
            if (this.methodsBuilder_ == null) {
                return Collections.unmodifiableList(this.methods_);
            }
            return this.methodsBuilder_.getMessageList();
        }

        public int getMethodsCount() {
            if (this.methodsBuilder_ == null) {
                return this.methods_.size();
            }
            return this.methodsBuilder_.getCount();
        }

        public Method getMethods(int i) {
            if (this.methodsBuilder_ == null) {
                return (Method) this.methods_.get(i);
            }
            return (Method) this.methodsBuilder_.getMessage(i);
        }

        public Builder setMethods(int i, Method method) {
            if (this.methodsBuilder_ != null) {
                this.methodsBuilder_.setMessage(i, method);
            } else if (method == null) {
                throw new NullPointerException();
            } else {
                ensureMethodsIsMutable();
                this.methods_.set(i, method);
                onChanged();
            }
            return this;
        }

        public Builder setMethods(int i, com.google.protobuf.Method.Builder builder) {
            if (this.methodsBuilder_ == null) {
                ensureMethodsIsMutable();
                this.methods_.set(i, builder.build());
                onChanged();
            } else {
                this.methodsBuilder_.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addMethods(Method method) {
            if (this.methodsBuilder_ != null) {
                this.methodsBuilder_.addMessage(method);
            } else if (method == null) {
                throw new NullPointerException();
            } else {
                ensureMethodsIsMutable();
                this.methods_.add(method);
                onChanged();
            }
            return this;
        }

        public Builder addMethods(int i, Method method) {
            if (this.methodsBuilder_ != null) {
                this.methodsBuilder_.addMessage(i, method);
            } else if (method == null) {
                throw new NullPointerException();
            } else {
                ensureMethodsIsMutable();
                this.methods_.add(i, method);
                onChanged();
            }
            return this;
        }

        public Builder addMethods(com.google.protobuf.Method.Builder builder) {
            if (this.methodsBuilder_ == null) {
                ensureMethodsIsMutable();
                this.methods_.add(builder.build());
                onChanged();
            } else {
                this.methodsBuilder_.addMessage(builder.build());
            }
            return this;
        }

        public Builder addMethods(int i, com.google.protobuf.Method.Builder builder) {
            if (this.methodsBuilder_ == null) {
                ensureMethodsIsMutable();
                this.methods_.add(i, builder.build());
                onChanged();
            } else {
                this.methodsBuilder_.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllMethods(Iterable<? extends Method> iterable) {
            if (this.methodsBuilder_ == null) {
                ensureMethodsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.methods_);
                onChanged();
            } else {
                this.methodsBuilder_.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearMethods() {
            if (this.methodsBuilder_ == null) {
                this.methods_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                this.methodsBuilder_.clear();
            }
            return this;
        }

        public Builder removeMethods(int i) {
            if (this.methodsBuilder_ == null) {
                ensureMethodsIsMutable();
                this.methods_.remove(i);
                onChanged();
            } else {
                this.methodsBuilder_.remove(i);
            }
            return this;
        }

        public com.google.protobuf.Method.Builder getMethodsBuilder(int i) {
            return (com.google.protobuf.Method.Builder) getMethodsFieldBuilder().getBuilder(i);
        }

        public MethodOrBuilder getMethodsOrBuilder(int i) {
            if (this.methodsBuilder_ == null) {
                return (MethodOrBuilder) this.methods_.get(i);
            }
            return (MethodOrBuilder) this.methodsBuilder_.getMessageOrBuilder(i);
        }

        public List<? extends MethodOrBuilder> getMethodsOrBuilderList() {
            if (this.methodsBuilder_ != null) {
                return this.methodsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.methods_);
        }

        public com.google.protobuf.Method.Builder addMethodsBuilder() {
            return (com.google.protobuf.Method.Builder) getMethodsFieldBuilder().addBuilder(Method.getDefaultInstance());
        }

        public com.google.protobuf.Method.Builder addMethodsBuilder(int i) {
            return (com.google.protobuf.Method.Builder) getMethodsFieldBuilder().addBuilder(i, Method.getDefaultInstance());
        }

        public List<com.google.protobuf.Method.Builder> getMethodsBuilderList() {
            return getMethodsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> getMethodsFieldBuilder() {
            if (this.methodsBuilder_ == null) {
                this.methodsBuilder_ = new RepeatedFieldBuilderV3(this.methods_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.methods_ = null;
            }
            return this.methodsBuilder_;
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

        public String getVersion() {
            Object obj = this.version_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String toStringUtf8 = ((ByteString) obj).toStringUtf8();
            this.version_ = toStringUtf8;
            return toStringUtf8;
        }

        public ByteString getVersionBytes() {
            Object obj = this.version_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.version_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setVersion(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.version_ = str;
            onChanged();
            return this;
        }

        public Builder clearVersion() {
            this.version_ = Api.getDefaultInstance().getVersion();
            onChanged();
            return this;
        }

        public Builder setVersionBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.version_ = byteString;
            onChanged();
            return this;
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

        private void ensureMixinsIsMutable() {
            if ((this.bitField0_ & 32) != 32) {
                this.mixins_ = new ArrayList(this.mixins_);
                this.bitField0_ |= 32;
            }
        }

        public List<Mixin> getMixinsList() {
            if (this.mixinsBuilder_ == null) {
                return Collections.unmodifiableList(this.mixins_);
            }
            return this.mixinsBuilder_.getMessageList();
        }

        public int getMixinsCount() {
            if (this.mixinsBuilder_ == null) {
                return this.mixins_.size();
            }
            return this.mixinsBuilder_.getCount();
        }

        public Mixin getMixins(int i) {
            if (this.mixinsBuilder_ == null) {
                return (Mixin) this.mixins_.get(i);
            }
            return (Mixin) this.mixinsBuilder_.getMessage(i);
        }

        public Builder setMixins(int i, Mixin mixin) {
            if (this.mixinsBuilder_ != null) {
                this.mixinsBuilder_.setMessage(i, mixin);
            } else if (mixin == null) {
                throw new NullPointerException();
            } else {
                ensureMixinsIsMutable();
                this.mixins_.set(i, mixin);
                onChanged();
            }
            return this;
        }

        public Builder setMixins(int i, com.google.protobuf.Mixin.Builder builder) {
            if (this.mixinsBuilder_ == null) {
                ensureMixinsIsMutable();
                this.mixins_.set(i, builder.build());
                onChanged();
            } else {
                this.mixinsBuilder_.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addMixins(Mixin mixin) {
            if (this.mixinsBuilder_ != null) {
                this.mixinsBuilder_.addMessage(mixin);
            } else if (mixin == null) {
                throw new NullPointerException();
            } else {
                ensureMixinsIsMutable();
                this.mixins_.add(mixin);
                onChanged();
            }
            return this;
        }

        public Builder addMixins(int i, Mixin mixin) {
            if (this.mixinsBuilder_ != null) {
                this.mixinsBuilder_.addMessage(i, mixin);
            } else if (mixin == null) {
                throw new NullPointerException();
            } else {
                ensureMixinsIsMutable();
                this.mixins_.add(i, mixin);
                onChanged();
            }
            return this;
        }

        public Builder addMixins(com.google.protobuf.Mixin.Builder builder) {
            if (this.mixinsBuilder_ == null) {
                ensureMixinsIsMutable();
                this.mixins_.add(builder.build());
                onChanged();
            } else {
                this.mixinsBuilder_.addMessage(builder.build());
            }
            return this;
        }

        public Builder addMixins(int i, com.google.protobuf.Mixin.Builder builder) {
            if (this.mixinsBuilder_ == null) {
                ensureMixinsIsMutable();
                this.mixins_.add(i, builder.build());
                onChanged();
            } else {
                this.mixinsBuilder_.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllMixins(Iterable<? extends Mixin> iterable) {
            if (this.mixinsBuilder_ == null) {
                ensureMixinsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.mixins_);
                onChanged();
            } else {
                this.mixinsBuilder_.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearMixins() {
            if (this.mixinsBuilder_ == null) {
                this.mixins_ = Collections.emptyList();
                this.bitField0_ &= -33;
                onChanged();
            } else {
                this.mixinsBuilder_.clear();
            }
            return this;
        }

        public Builder removeMixins(int i) {
            if (this.mixinsBuilder_ == null) {
                ensureMixinsIsMutable();
                this.mixins_.remove(i);
                onChanged();
            } else {
                this.mixinsBuilder_.remove(i);
            }
            return this;
        }

        public com.google.protobuf.Mixin.Builder getMixinsBuilder(int i) {
            return (com.google.protobuf.Mixin.Builder) getMixinsFieldBuilder().getBuilder(i);
        }

        public MixinOrBuilder getMixinsOrBuilder(int i) {
            if (this.mixinsBuilder_ == null) {
                return (MixinOrBuilder) this.mixins_.get(i);
            }
            return (MixinOrBuilder) this.mixinsBuilder_.getMessageOrBuilder(i);
        }

        public List<? extends MixinOrBuilder> getMixinsOrBuilderList() {
            if (this.mixinsBuilder_ != null) {
                return this.mixinsBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.mixins_);
        }

        public com.google.protobuf.Mixin.Builder addMixinsBuilder() {
            return (com.google.protobuf.Mixin.Builder) getMixinsFieldBuilder().addBuilder(Mixin.getDefaultInstance());
        }

        public com.google.protobuf.Mixin.Builder addMixinsBuilder(int i) {
            return (com.google.protobuf.Mixin.Builder) getMixinsFieldBuilder().addBuilder(i, Mixin.getDefaultInstance());
        }

        public List<com.google.protobuf.Mixin.Builder> getMixinsBuilderList() {
            return getMixinsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> getMixinsFieldBuilder() {
            if (this.mixinsBuilder_ == null) {
                this.mixinsBuilder_ = new RepeatedFieldBuilderV3(this.mixins_, (this.bitField0_ & 32) == 32, getParentForChildren(), isClean());
                this.mixins_ = null;
            }
            return this.mixinsBuilder_;
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

    private Api(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Api() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.methods_ = Collections.emptyList();
        this.options_ = Collections.emptyList();
        this.version_ = "";
        this.mixins_ = Collections.emptyList();
        this.syntax_ = 0;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Api(com.google.protobuf.CodedInputStream r11, com.google.protobuf.ExtensionRegistryLite r12) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
        r10 = this;
        r1 = 1;
        r2 = 0;
        r8 = 32;
        r7 = 4;
        r6 = 2;
        r10.<init>();
        r5 = com.google.protobuf.UnknownFieldSet.newBuilder();
        r3 = r2;
    L_0x000e:
        if (r2 != 0) goto L_0x00c4;
    L_0x0010:
        r0 = r11.readTag();	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        switch(r0) {
            case 0: goto L_0x0022;
            case 10: goto L_0x0025;
            case 18: goto L_0x002e;
            case 26: goto L_0x004c;
            case 34: goto L_0x006a;
            case 42: goto L_0x0073;
            case 50: goto L_0x009b;
            case 56: goto L_0x00ba;
            default: goto L_0x0017;
        };	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
    L_0x0017:
        r0 = r10.parseUnknownFieldProto3(r11, r5, r12, r0);	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        if (r0 != 0) goto L_0x0141;
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
        r0 = r11.readStringRequireUtf8();	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r10.name_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r0 = r2;
        r2 = r3;
        goto L_0x001f;
    L_0x002e:
        r0 = r3 & 2;
        if (r0 == r6) goto L_0x014b;
    L_0x0032:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r10.methods_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r0 = r3 | 2;
    L_0x003b:
        r3 = r10.methods_;	 Catch:{ InvalidProtocolBufferException -> 0x013a, IOException -> 0x0136, all -> 0x0132 }
        r4 = com.google.protobuf.Method.parser();	 Catch:{ InvalidProtocolBufferException -> 0x013a, IOException -> 0x0136, all -> 0x0132 }
        r4 = r11.readMessage(r4, r12);	 Catch:{ InvalidProtocolBufferException -> 0x013a, IOException -> 0x0136, all -> 0x0132 }
        r3.add(r4);	 Catch:{ InvalidProtocolBufferException -> 0x013a, IOException -> 0x0136, all -> 0x0132 }
        r9 = r2;
        r2 = r0;
        r0 = r9;
        goto L_0x001f;
    L_0x004c:
        r0 = r3 & 4;
        if (r0 == r7) goto L_0x0148;
    L_0x0050:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r10.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r0 = r3 | 4;
    L_0x0059:
        r3 = r10.options_;	 Catch:{ InvalidProtocolBufferException -> 0x013a, IOException -> 0x0136, all -> 0x0132 }
        r4 = com.google.protobuf.Option.parser();	 Catch:{ InvalidProtocolBufferException -> 0x013a, IOException -> 0x0136, all -> 0x0132 }
        r4 = r11.readMessage(r4, r12);	 Catch:{ InvalidProtocolBufferException -> 0x013a, IOException -> 0x0136, all -> 0x0132 }
        r3.add(r4);	 Catch:{ InvalidProtocolBufferException -> 0x013a, IOException -> 0x0136, all -> 0x0132 }
        r9 = r2;
        r2 = r0;
        r0 = r9;
        goto L_0x001f;
    L_0x006a:
        r0 = r11.readStringRequireUtf8();	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r10.version_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r0 = r2;
        r2 = r3;
        goto L_0x001f;
    L_0x0073:
        r0 = 0;
        r4 = r10.sourceContext_;	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        if (r4 == 0) goto L_0x0145;
    L_0x0078:
        r0 = r10.sourceContext_;	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r0 = r0.toBuilder();	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r4 = r0;
    L_0x007f:
        r0 = com.google.protobuf.SourceContext.parser();	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r0 = r11.readMessage(r0, r12);	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r0 = (com.google.protobuf.SourceContext) r0;	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r10.sourceContext_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        if (r4 == 0) goto L_0x0141;
    L_0x008d:
        r0 = r10.sourceContext_;	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r4.mergeFrom(r0);	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r0 = r4.buildPartial();	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r10.sourceContext_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r0 = r2;
        r2 = r3;
        goto L_0x001f;
    L_0x009b:
        r0 = r3 & 32;
        if (r0 == r8) goto L_0x013e;
    L_0x009f:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r10.mixins_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r0 = r3 | 32;
    L_0x00a8:
        r3 = r10.mixins_;	 Catch:{ InvalidProtocolBufferException -> 0x013a, IOException -> 0x0136, all -> 0x0132 }
        r4 = com.google.protobuf.Mixin.parser();	 Catch:{ InvalidProtocolBufferException -> 0x013a, IOException -> 0x0136, all -> 0x0132 }
        r4 = r11.readMessage(r4, r12);	 Catch:{ InvalidProtocolBufferException -> 0x013a, IOException -> 0x0136, all -> 0x0132 }
        r3.add(r4);	 Catch:{ InvalidProtocolBufferException -> 0x013a, IOException -> 0x0136, all -> 0x0132 }
        r9 = r2;
        r2 = r0;
        r0 = r9;
        goto L_0x001f;
    L_0x00ba:
        r0 = r11.readEnum();	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r10.syntax_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00f2, IOException -> 0x0127 }
        r0 = r2;
        r2 = r3;
        goto L_0x001f;
    L_0x00c4:
        r0 = r3 & 2;
        if (r0 != r6) goto L_0x00d0;
    L_0x00c8:
        r0 = r10.methods_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r10.methods_ = r0;
    L_0x00d0:
        r0 = r3 & 4;
        if (r0 != r7) goto L_0x00dc;
    L_0x00d4:
        r0 = r10.options_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r10.options_ = r0;
    L_0x00dc:
        r0 = r3 & 32;
        if (r0 != r8) goto L_0x00e8;
    L_0x00e0:
        r0 = r10.mixins_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r10.mixins_ = r0;
    L_0x00e8:
        r0 = r5.build();
        r10.unknownFields = r0;
        r10.makeExtensionsImmutable();
        return;
    L_0x00f2:
        r0 = move-exception;
    L_0x00f3:
        r0 = r0.setUnfinishedMessage(r10);	 Catch:{ all -> 0x00f8 }
        throw r0;	 Catch:{ all -> 0x00f8 }
    L_0x00f8:
        r0 = move-exception;
    L_0x00f9:
        r1 = r3 & 2;
        if (r1 != r6) goto L_0x0105;
    L_0x00fd:
        r1 = r10.methods_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r10.methods_ = r1;
    L_0x0105:
        r1 = r3 & 4;
        if (r1 != r7) goto L_0x0111;
    L_0x0109:
        r1 = r10.options_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r10.options_ = r1;
    L_0x0111:
        r1 = r3 & 32;
        if (r1 != r8) goto L_0x011d;
    L_0x0115:
        r1 = r10.mixins_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r10.mixins_ = r1;
    L_0x011d:
        r1 = r5.build();
        r10.unknownFields = r1;
        r10.makeExtensionsImmutable();
        throw r0;
    L_0x0127:
        r0 = move-exception;
    L_0x0128:
        r1 = new com.google.protobuf.InvalidProtocolBufferException;	 Catch:{ all -> 0x00f8 }
        r1.<init>(r0);	 Catch:{ all -> 0x00f8 }
        r0 = r1.setUnfinishedMessage(r10);	 Catch:{ all -> 0x00f8 }
        throw r0;	 Catch:{ all -> 0x00f8 }
    L_0x0132:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x00f9;
    L_0x0136:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x0128;
    L_0x013a:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x00f3;
    L_0x013e:
        r0 = r3;
        goto L_0x00a8;
    L_0x0141:
        r0 = r2;
        r2 = r3;
        goto L_0x001f;
    L_0x0145:
        r4 = r0;
        goto L_0x007f;
    L_0x0148:
        r0 = r3;
        goto L_0x0059;
    L_0x014b:
        r0 = r3;
        goto L_0x003b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Api.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
    }

    public static final Descriptor getDescriptor() {
        return ApiProto.internal_static_google_protobuf_Api_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return ApiProto.internal_static_google_protobuf_Api_fieldAccessorTable.ensureFieldAccessorsInitialized(Api.class, Builder.class);
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

    public List<Method> getMethodsList() {
        return this.methods_;
    }

    public List<? extends MethodOrBuilder> getMethodsOrBuilderList() {
        return this.methods_;
    }

    public int getMethodsCount() {
        return this.methods_.size();
    }

    public Method getMethods(int i) {
        return (Method) this.methods_.get(i);
    }

    public MethodOrBuilder getMethodsOrBuilder(int i) {
        return (MethodOrBuilder) this.methods_.get(i);
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

    public String getVersion() {
        Object obj = this.version_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String toStringUtf8 = ((ByteString) obj).toStringUtf8();
        this.version_ = toStringUtf8;
        return toStringUtf8;
    }

    public ByteString getVersionBytes() {
        Object obj = this.version_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.version_ = copyFromUtf8;
        return copyFromUtf8;
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

    public List<Mixin> getMixinsList() {
        return this.mixins_;
    }

    public List<? extends MixinOrBuilder> getMixinsOrBuilderList() {
        return this.mixins_;
    }

    public int getMixinsCount() {
        return this.mixins_.size();
    }

    public Mixin getMixins(int i) {
        return (Mixin) this.mixins_.get(i);
    }

    public MixinOrBuilder getMixinsOrBuilder(int i) {
        return (MixinOrBuilder) this.mixins_.get(i);
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
        int i;
        int i2 = 0;
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        for (i = 0; i < this.methods_.size(); i++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.methods_.get(i));
        }
        for (i = 0; i < this.options_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.options_.get(i));
        }
        if (!getVersionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.version_);
        }
        if (this.sourceContext_ != null) {
            codedOutputStream.writeMessage(5, getSourceContext());
        }
        while (i2 < this.mixins_.size()) {
            codedOutputStream.writeMessage(6, (MessageLite) this.mixins_.get(i2));
            i2++;
        }
        if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
            codedOutputStream.writeEnum(7, this.syntax_);
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
        for (i3 = 0; i3 < this.methods_.size(); i3++) {
            i4 += CodedOutputStream.computeMessageSize(2, (MessageLite) this.methods_.get(i3));
        }
        for (i3 = 0; i3 < this.options_.size(); i3++) {
            i4 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.options_.get(i3));
        }
        if (!getVersionBytes().isEmpty()) {
            i4 += GeneratedMessageV3.computeStringSize(4, this.version_);
        }
        if (this.sourceContext_ != null) {
            i4 += CodedOutputStream.computeMessageSize(5, getSourceContext());
        }
        while (i < this.mixins_.size()) {
            i4 += CodedOutputStream.computeMessageSize(6, (MessageLite) this.mixins_.get(i));
            i++;
        }
        if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
            i4 += CodedOutputStream.computeEnumSize(7, this.syntax_);
        }
        i2 = this.unknownFields.getSerializedSize() + i4;
        this.memoizedSize = i2;
        return i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Api)) {
            return super.equals(obj);
        }
        boolean z;
        Api api = (Api) obj;
        if ((getName().equals(api.getName())) && getMethodsList().equals(api.getMethodsList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getOptionsList().equals(api.getOptionsList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getVersion().equals(api.getVersion())) {
            z = true;
        } else {
            z = false;
        }
        if (z && hasSourceContext() == api.hasSourceContext()) {
            z = true;
        } else {
            z = false;
        }
        if (hasSourceContext()) {
            if (z && getSourceContext().equals(api.getSourceContext())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && getMixinsList().equals(api.getMixinsList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.syntax_ == api.syntax_) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.unknownFields.equals(api.unknownFields)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53) + getName().hashCode();
        if (getMethodsCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getMethodsList().hashCode();
        }
        if (getOptionsCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getOptionsList().hashCode();
        }
        hashCode = (((hashCode * 37) + 4) * 53) + getVersion().hashCode();
        if (hasSourceContext()) {
            hashCode = (((hashCode * 37) + 5) * 53) + getSourceContext().hashCode();
        }
        if (getMixinsCount() > 0) {
            hashCode = (((hashCode * 37) + 6) * 53) + getMixinsList().hashCode();
        }
        hashCode = (((((hashCode * 37) + 7) * 53) + this.syntax_) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static Api parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Api) PARSER.parseFrom(byteBuffer);
    }

    public static Api parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Api) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Api parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Api) PARSER.parseFrom(byteString);
    }

    public static Api parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Api) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Api parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Api) PARSER.parseFrom(bArr);
    }

    public static Api parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Api) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Api parseFrom(InputStream inputStream) throws IOException {
        return (Api) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Api parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Api) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Api parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Api) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Api parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Api) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Api parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Api) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Api parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Api) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Api api) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(api);
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

    public static Api getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Api> parser() {
        return PARSER;
    }

    public Parser<Api> getParserForType() {
        return PARSER;
    }

    public Api getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
