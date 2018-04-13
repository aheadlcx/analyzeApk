package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite.Builder;
import com.google.protobuf.DescriptorProtos.EnumOptionsOrBuilder;
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

public final class DescriptorProtos$EnumOptions$Builder extends ExtendableBuilder<DescriptorProtos$EnumOptions, DescriptorProtos$EnumOptions$Builder> implements EnumOptionsOrBuilder {
    private boolean allowAlias_;
    private int bitField0_;
    private boolean deprecated_;
    private RepeatedFieldBuilderV3<DescriptorProtos$UninterpretedOption, DescriptorProtos$UninterpretedOption$Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
    private List<DescriptorProtos$UninterpretedOption> uninterpretedOption_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_EnumOptions_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_EnumOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$EnumOptions.class, DescriptorProtos$EnumOptions$Builder.class);
    }

    private DescriptorProtos$EnumOptions$Builder() {
        this.uninterpretedOption_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$EnumOptions$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.uninterpretedOption_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getUninterpretedOptionFieldBuilder();
        }
    }

    public DescriptorProtos$EnumOptions$Builder clear() {
        super.clear();
        this.allowAlias_ = false;
        this.bitField0_ &= -2;
        this.deprecated_ = false;
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
        return DescriptorProtos.internal_static_google_protobuf_EnumOptions_descriptor;
    }

    public DescriptorProtos$EnumOptions getDefaultInstanceForType() {
        return DescriptorProtos$EnumOptions.getDefaultInstance();
    }

    public DescriptorProtos$EnumOptions build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$EnumOptions buildPartial() {
        int i = 1;
        DescriptorProtos$EnumOptions descriptorProtos$EnumOptions = new DescriptorProtos$EnumOptions(this, null);
        int i2 = this.bitField0_;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        DescriptorProtos$EnumOptions.access$20202(descriptorProtos$EnumOptions, this.allowAlias_);
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        DescriptorProtos$EnumOptions.access$20302(descriptorProtos$EnumOptions, this.deprecated_);
        if (this.uninterpretedOptionBuilder_ == null) {
            if ((this.bitField0_ & 4) == 4) {
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                this.bitField0_ &= -5;
            }
            DescriptorProtos$EnumOptions.access$20402(descriptorProtos$EnumOptions, this.uninterpretedOption_);
        } else {
            DescriptorProtos$EnumOptions.access$20402(descriptorProtos$EnumOptions, this.uninterpretedOptionBuilder_.build());
        }
        DescriptorProtos$EnumOptions.access$20502(descriptorProtos$EnumOptions, i);
        onBuilt();
        return descriptorProtos$EnumOptions;
    }

    public DescriptorProtos$EnumOptions$Builder clone() {
        return (DescriptorProtos$EnumOptions$Builder) super.clone();
    }

    public DescriptorProtos$EnumOptions$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$EnumOptions$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$EnumOptions$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$EnumOptions$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$EnumOptions$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$EnumOptions$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$EnumOptions$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$EnumOptions$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$EnumOptions$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$EnumOptions$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public <Type> DescriptorProtos$EnumOptions$Builder setExtension(GeneratedExtension<DescriptorProtos$EnumOptions, Type> generatedExtension, Type type) {
        return (DescriptorProtos$EnumOptions$Builder) super.setExtension((GeneratedExtension) generatedExtension, (Object) type);
    }

    public <Type> DescriptorProtos$EnumOptions$Builder setExtension(GeneratedExtension<DescriptorProtos$EnumOptions, List<Type>> generatedExtension, int i, Type type) {
        return (DescriptorProtos$EnumOptions$Builder) super.setExtension((GeneratedExtension) generatedExtension, i, (Object) type);
    }

    public <Type> DescriptorProtos$EnumOptions$Builder addExtension(GeneratedExtension<DescriptorProtos$EnumOptions, List<Type>> generatedExtension, Type type) {
        return (DescriptorProtos$EnumOptions$Builder) super.addExtension((GeneratedExtension) generatedExtension, (Object) type);
    }

    public <Type> DescriptorProtos$EnumOptions$Builder clearExtension(GeneratedExtension<DescriptorProtos$EnumOptions, ?> generatedExtension) {
        return (DescriptorProtos$EnumOptions$Builder) super.clearExtension((GeneratedExtension) generatedExtension);
    }

    public DescriptorProtos$EnumOptions$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$EnumOptions) {
            return mergeFrom((DescriptorProtos$EnumOptions) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$EnumOptions$Builder mergeFrom(DescriptorProtos$EnumOptions descriptorProtos$EnumOptions) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (descriptorProtos$EnumOptions != DescriptorProtos$EnumOptions.getDefaultInstance()) {
            if (descriptorProtos$EnumOptions.hasAllowAlias()) {
                setAllowAlias(descriptorProtos$EnumOptions.getAllowAlias());
            }
            if (descriptorProtos$EnumOptions.hasDeprecated()) {
                setDeprecated(descriptorProtos$EnumOptions.getDeprecated());
            }
            if (this.uninterpretedOptionBuilder_ == null) {
                if (!DescriptorProtos$EnumOptions.access$20400(descriptorProtos$EnumOptions).isEmpty()) {
                    if (this.uninterpretedOption_.isEmpty()) {
                        this.uninterpretedOption_ = DescriptorProtos$EnumOptions.access$20400(descriptorProtos$EnumOptions);
                        this.bitField0_ &= -5;
                    } else {
                        ensureUninterpretedOptionIsMutable();
                        this.uninterpretedOption_.addAll(DescriptorProtos$EnumOptions.access$20400(descriptorProtos$EnumOptions));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$EnumOptions.access$20400(descriptorProtos$EnumOptions).isEmpty()) {
                if (this.uninterpretedOptionBuilder_.isEmpty()) {
                    this.uninterpretedOptionBuilder_.dispose();
                    this.uninterpretedOptionBuilder_ = null;
                    this.uninterpretedOption_ = DescriptorProtos$EnumOptions.access$20400(descriptorProtos$EnumOptions);
                    this.bitField0_ &= -5;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getUninterpretedOptionFieldBuilder();
                    }
                    this.uninterpretedOptionBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(DescriptorProtos$EnumOptions.access$20400(descriptorProtos$EnumOptions));
                }
            }
            mergeExtensionFields(descriptorProtos$EnumOptions);
            mergeUnknownFields(descriptorProtos$EnumOptions.unknownFields);
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

    public DescriptorProtos$EnumOptions$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        DescriptorProtos$EnumOptions descriptorProtos$EnumOptions;
        DescriptorProtos$EnumOptions descriptorProtos$EnumOptions2;
        try {
            descriptorProtos$EnumOptions2 = (DescriptorProtos$EnumOptions) DescriptorProtos$EnumOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$EnumOptions2 != null) {
                mergeFrom(descriptorProtos$EnumOptions2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$EnumOptions2 = (DescriptorProtos$EnumOptions) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$EnumOptions = descriptorProtos$EnumOptions2;
            th = th3;
            if (descriptorProtos$EnumOptions != null) {
                mergeFrom(descriptorProtos$EnumOptions);
            }
            throw th;
        }
    }

    public boolean hasAllowAlias() {
        return (this.bitField0_ & 1) == 1;
    }

    public boolean getAllowAlias() {
        return this.allowAlias_;
    }

    public DescriptorProtos$EnumOptions$Builder setAllowAlias(boolean z) {
        this.bitField0_ |= 1;
        this.allowAlias_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$EnumOptions$Builder clearAllowAlias() {
        this.bitField0_ &= -2;
        this.allowAlias_ = false;
        onChanged();
        return this;
    }

    public boolean hasDeprecated() {
        return (this.bitField0_ & 2) == 2;
    }

    public boolean getDeprecated() {
        return this.deprecated_;
    }

    public DescriptorProtos$EnumOptions$Builder setDeprecated(boolean z) {
        this.bitField0_ |= 2;
        this.deprecated_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$EnumOptions$Builder clearDeprecated() {
        this.bitField0_ &= -3;
        this.deprecated_ = false;
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

    public DescriptorProtos$EnumOptions$Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$EnumOptions$Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.set(i, descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.setMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$EnumOptions$Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$EnumOptions$Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$EnumOptions$Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addMessage(descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$EnumOptions$Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(i, descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$EnumOptions$Builder addAllUninterpretedOption(Iterable<? extends DescriptorProtos$UninterpretedOption> iterable) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            Builder.addAll(iterable, this.uninterpretedOption_);
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$EnumOptions$Builder clearUninterpretedOption() {
        if (this.uninterpretedOptionBuilder_ == null) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.bitField0_ &= -5;
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$EnumOptions$Builder removeUninterpretedOption(int i) {
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

    public final DescriptorProtos$EnumOptions$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$EnumOptions$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$EnumOptions$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$EnumOptions$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
