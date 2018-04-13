package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite.Builder;
import com.google.protobuf.DescriptorProtos.MethodOptions.IdempotencyLevel;
import com.google.protobuf.DescriptorProtos.MethodOptionsOrBuilder;
import com.google.protobuf.DescriptorProtos.UninterpretedOptionOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessage.GeneratedExtension;
import com.google.protobuf.GeneratedMessageV3.ExtendableBuilder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$MethodOptions$Builder extends ExtendableBuilder<DescriptorProtos$MethodOptions, DescriptorProtos$MethodOptions$Builder> implements MethodOptionsOrBuilder {
    private int bitField0_;
    private boolean deprecated_;
    private int idempotencyLevel_;
    private RepeatedFieldBuilderV3<DescriptorProtos$UninterpretedOption, DescriptorProtos$UninterpretedOption$Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
    private List<DescriptorProtos$UninterpretedOption> uninterpretedOption_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_MethodOptions_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_MethodOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$MethodOptions.class, DescriptorProtos$MethodOptions$Builder.class);
    }

    private DescriptorProtos$MethodOptions$Builder() {
        this.idempotencyLevel_ = 0;
        this.uninterpretedOption_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$MethodOptions$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.idempotencyLevel_ = 0;
        this.uninterpretedOption_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getUninterpretedOptionFieldBuilder();
        }
    }

    public DescriptorProtos$MethodOptions$Builder clear() {
        super.clear();
        this.deprecated_ = false;
        this.bitField0_ &= -2;
        this.idempotencyLevel_ = 0;
        this.bitField0_ &= -3;
        if (this.uninterpretedOptionBuilder_ == null) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.bitField0_ &= -5;
        } else {
            this.uninterpretedOptionBuilder_.clear();
        }
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_MethodOptions_descriptor;
    }

    public DescriptorProtos$MethodOptions getDefaultInstanceForType() {
        return DescriptorProtos$MethodOptions.getDefaultInstance();
    }

    public DescriptorProtos$MethodOptions build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$MethodOptions buildPartial() {
        int i = 1;
        DescriptorProtos$MethodOptions descriptorProtos$MethodOptions = new DescriptorProtos$MethodOptions(this, null);
        int i2 = this.bitField0_;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        DescriptorProtos$MethodOptions.access$23002(descriptorProtos$MethodOptions, this.deprecated_);
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        DescriptorProtos$MethodOptions.access$23102(descriptorProtos$MethodOptions, this.idempotencyLevel_);
        if (this.uninterpretedOptionBuilder_ == null) {
            if ((this.bitField0_ & 4) == 4) {
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                this.bitField0_ &= -5;
            }
            DescriptorProtos$MethodOptions.access$23202(descriptorProtos$MethodOptions, this.uninterpretedOption_);
        } else {
            DescriptorProtos$MethodOptions.access$23202(descriptorProtos$MethodOptions, this.uninterpretedOptionBuilder_.build());
        }
        DescriptorProtos$MethodOptions.access$23302(descriptorProtos$MethodOptions, i);
        onBuilt();
        return descriptorProtos$MethodOptions;
    }

    public DescriptorProtos$MethodOptions$Builder clone() {
        return (DescriptorProtos$MethodOptions$Builder) super.clone();
    }

    public DescriptorProtos$MethodOptions$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$MethodOptions$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$MethodOptions$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$MethodOptions$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$MethodOptions$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$MethodOptions$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$MethodOptions$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$MethodOptions$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$MethodOptions$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$MethodOptions$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public <Type> DescriptorProtos$MethodOptions$Builder setExtension(GeneratedExtension<DescriptorProtos$MethodOptions, Type> generatedExtension, Type type) {
        return (DescriptorProtos$MethodOptions$Builder) super.setExtension((GeneratedExtension) generatedExtension, (Object) type);
    }

    public <Type> DescriptorProtos$MethodOptions$Builder setExtension(GeneratedExtension<DescriptorProtos$MethodOptions, List<Type>> generatedExtension, int i, Type type) {
        return (DescriptorProtos$MethodOptions$Builder) super.setExtension((GeneratedExtension) generatedExtension, i, (Object) type);
    }

    public <Type> DescriptorProtos$MethodOptions$Builder addExtension(GeneratedExtension<DescriptorProtos$MethodOptions, List<Type>> generatedExtension, Type type) {
        return (DescriptorProtos$MethodOptions$Builder) super.addExtension((GeneratedExtension) generatedExtension, (Object) type);
    }

    public <Type> DescriptorProtos$MethodOptions$Builder clearExtension(GeneratedExtension<DescriptorProtos$MethodOptions, ?> generatedExtension) {
        return (DescriptorProtos$MethodOptions$Builder) super.clearExtension((GeneratedExtension) generatedExtension);
    }

    public DescriptorProtos$MethodOptions$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$MethodOptions) {
            return mergeFrom((DescriptorProtos$MethodOptions) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$MethodOptions$Builder mergeFrom(DescriptorProtos$MethodOptions descriptorProtos$MethodOptions) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (descriptorProtos$MethodOptions != DescriptorProtos$MethodOptions.getDefaultInstance()) {
            if (descriptorProtos$MethodOptions.hasDeprecated()) {
                setDeprecated(descriptorProtos$MethodOptions.getDeprecated());
            }
            if (descriptorProtos$MethodOptions.hasIdempotencyLevel()) {
                setIdempotencyLevel(descriptorProtos$MethodOptions.getIdempotencyLevel());
            }
            if (this.uninterpretedOptionBuilder_ == null) {
                if (!DescriptorProtos$MethodOptions.access$23200(descriptorProtos$MethodOptions).isEmpty()) {
                    if (this.uninterpretedOption_.isEmpty()) {
                        this.uninterpretedOption_ = DescriptorProtos$MethodOptions.access$23200(descriptorProtos$MethodOptions);
                        this.bitField0_ &= -5;
                    } else {
                        ensureUninterpretedOptionIsMutable();
                        this.uninterpretedOption_.addAll(DescriptorProtos$MethodOptions.access$23200(descriptorProtos$MethodOptions));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$MethodOptions.access$23200(descriptorProtos$MethodOptions).isEmpty()) {
                if (this.uninterpretedOptionBuilder_.isEmpty()) {
                    this.uninterpretedOptionBuilder_.dispose();
                    this.uninterpretedOptionBuilder_ = null;
                    this.uninterpretedOption_ = DescriptorProtos$MethodOptions.access$23200(descriptorProtos$MethodOptions);
                    this.bitField0_ &= -5;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getUninterpretedOptionFieldBuilder();
                    }
                    this.uninterpretedOptionBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(DescriptorProtos$MethodOptions.access$23200(descriptorProtos$MethodOptions));
                }
            }
            mergeExtensionFields(descriptorProtos$MethodOptions);
            mergeUnknownFields(descriptorProtos$MethodOptions.unknownFields);
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        for (int i = 0; i < getUninterpretedOptionCount(); i++) {
            if (!getUninterpretedOption(i).isInitialized()) {
                return false;
            }
        }
        if (extensionsAreInitialized()) {
            return true;
        }
        return false;
    }

    public DescriptorProtos$MethodOptions$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        DescriptorProtos$MethodOptions descriptorProtos$MethodOptions;
        Throwable th;
        DescriptorProtos$MethodOptions descriptorProtos$MethodOptions2;
        try {
            descriptorProtos$MethodOptions = (DescriptorProtos$MethodOptions) DescriptorProtos$MethodOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$MethodOptions != null) {
                mergeFrom(descriptorProtos$MethodOptions);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$MethodOptions = (DescriptorProtos$MethodOptions) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$MethodOptions2 = descriptorProtos$MethodOptions;
            th = th3;
            if (descriptorProtos$MethodOptions2 != null) {
                mergeFrom(descriptorProtos$MethodOptions2);
            }
            throw th;
        }
    }

    public boolean hasDeprecated() {
        return (this.bitField0_ & 1) == 1;
    }

    public boolean getDeprecated() {
        return this.deprecated_;
    }

    public DescriptorProtos$MethodOptions$Builder setDeprecated(boolean z) {
        this.bitField0_ |= 1;
        this.deprecated_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$MethodOptions$Builder clearDeprecated() {
        this.bitField0_ &= -2;
        this.deprecated_ = false;
        onChanged();
        return this;
    }

    public boolean hasIdempotencyLevel() {
        return (this.bitField0_ & 2) == 2;
    }

    public IdempotencyLevel getIdempotencyLevel() {
        IdempotencyLevel valueOf = IdempotencyLevel.valueOf(this.idempotencyLevel_);
        return valueOf == null ? IdempotencyLevel.IDEMPOTENCY_UNKNOWN : valueOf;
    }

    public DescriptorProtos$MethodOptions$Builder setIdempotencyLevel(IdempotencyLevel idempotencyLevel) {
        if (idempotencyLevel == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 2;
        this.idempotencyLevel_ = idempotencyLevel.getNumber();
        onChanged();
        return this;
    }

    public DescriptorProtos$MethodOptions$Builder clearIdempotencyLevel() {
        this.bitField0_ &= -3;
        this.idempotencyLevel_ = 0;
        onChanged();
        return this;
    }

    private void ensureUninterpretedOptionIsMutable() {
        if ((this.bitField0_ & 4) != 4) {
            this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
            this.bitField0_ |= 4;
        }
    }

    public List<DescriptorProtos$UninterpretedOption> getUninterpretedOptionList() {
        if (this.uninterpretedOptionBuilder_ == null) {
            return Collections.unmodifiableList(this.uninterpretedOption_);
        }
        return this.uninterpretedOptionBuilder_.getMessageList();
    }

    public int getUninterpretedOptionCount() {
        if (this.uninterpretedOptionBuilder_ == null) {
            return this.uninterpretedOption_.size();
        }
        return this.uninterpretedOptionBuilder_.getCount();
    }

    public DescriptorProtos$UninterpretedOption getUninterpretedOption(int i) {
        if (this.uninterpretedOptionBuilder_ == null) {
            return (DescriptorProtos$UninterpretedOption) this.uninterpretedOption_.get(i);
        }
        return (DescriptorProtos$UninterpretedOption) this.uninterpretedOptionBuilder_.getMessage(i);
    }

    public DescriptorProtos$MethodOptions$Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
        if (this.uninterpretedOptionBuilder_ != null) {
            this.uninterpretedOptionBuilder_.setMessage(i, descriptorProtos$UninterpretedOption);
        } else if (descriptorProtos$UninterpretedOption == null) {
            throw new NullPointerException();
        } else {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.set(i, descriptorProtos$UninterpretedOption);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$MethodOptions$Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.set(i, descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.setMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$MethodOptions$Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
        if (this.uninterpretedOptionBuilder_ != null) {
            this.uninterpretedOptionBuilder_.addMessage(descriptorProtos$UninterpretedOption);
        } else if (descriptorProtos$UninterpretedOption == null) {
            throw new NullPointerException();
        } else {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(descriptorProtos$UninterpretedOption);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$MethodOptions$Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
        if (this.uninterpretedOptionBuilder_ != null) {
            this.uninterpretedOptionBuilder_.addMessage(i, descriptorProtos$UninterpretedOption);
        } else if (descriptorProtos$UninterpretedOption == null) {
            throw new NullPointerException();
        } else {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(i, descriptorProtos$UninterpretedOption);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$MethodOptions$Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addMessage(descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$MethodOptions$Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(i, descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$MethodOptions$Builder addAllUninterpretedOption(Iterable<? extends DescriptorProtos$UninterpretedOption> iterable) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            Builder.addAll(iterable, this.uninterpretedOption_);
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$MethodOptions$Builder clearUninterpretedOption() {
        if (this.uninterpretedOptionBuilder_ == null) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.bitField0_ &= -5;
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$MethodOptions$Builder removeUninterpretedOption(int i) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.remove(i);
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder getUninterpretedOptionBuilder(int i) {
        return (DescriptorProtos$UninterpretedOption$Builder) getUninterpretedOptionFieldBuilder().getBuilder(i);
    }

    public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
        if (this.uninterpretedOptionBuilder_ == null) {
            return (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i);
        }
        return (UninterpretedOptionOrBuilder) this.uninterpretedOptionBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
        if (this.uninterpretedOptionBuilder_ != null) {
            return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.uninterpretedOption_);
    }

    public DescriptorProtos$UninterpretedOption$Builder addUninterpretedOptionBuilder() {
        return (DescriptorProtos$UninterpretedOption$Builder) getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos$UninterpretedOption.getDefaultInstance());
    }

    public DescriptorProtos$UninterpretedOption$Builder addUninterpretedOptionBuilder(int i) {
        return (DescriptorProtos$UninterpretedOption$Builder) getUninterpretedOptionFieldBuilder().addBuilder(i, DescriptorProtos$UninterpretedOption.getDefaultInstance());
    }

    public List<DescriptorProtos$UninterpretedOption$Builder> getUninterpretedOptionBuilderList() {
        return getUninterpretedOptionFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<DescriptorProtos$UninterpretedOption, DescriptorProtos$UninterpretedOption$Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
        if (this.uninterpretedOptionBuilder_ == null) {
            this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilderV3(this.uninterpretedOption_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
            this.uninterpretedOption_ = null;
        }
        return this.uninterpretedOptionBuilder_;
    }

    public final DescriptorProtos$MethodOptions$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$MethodOptions$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$MethodOptions$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$MethodOptions$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
