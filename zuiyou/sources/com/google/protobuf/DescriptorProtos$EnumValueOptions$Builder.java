package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite.Builder;
import com.google.protobuf.DescriptorProtos.EnumValueOptionsOrBuilder;
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

public final class DescriptorProtos$EnumValueOptions$Builder extends ExtendableBuilder<DescriptorProtos$EnumValueOptions, DescriptorProtos$EnumValueOptions$Builder> implements EnumValueOptionsOrBuilder {
    private int bitField0_;
    private boolean deprecated_;
    private RepeatedFieldBuilderV3<DescriptorProtos$UninterpretedOption, DescriptorProtos$UninterpretedOption$Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
    private List<DescriptorProtos$UninterpretedOption> uninterpretedOption_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$EnumValueOptions.class, DescriptorProtos$EnumValueOptions$Builder.class);
    }

    private DescriptorProtos$EnumValueOptions$Builder() {
        this.uninterpretedOption_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$EnumValueOptions$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.uninterpretedOption_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getUninterpretedOptionFieldBuilder();
        }
    }

    public DescriptorProtos$EnumValueOptions$Builder clear() {
        super.clear();
        this.deprecated_ = false;
        this.bitField0_ &= -2;
        if (this.uninterpretedOptionBuilder_ == null) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.bitField0_ &= -3;
        } else {
            this.uninterpretedOptionBuilder_.clear();
        }
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_descriptor;
    }

    public DescriptorProtos$EnumValueOptions getDefaultInstanceForType() {
        return DescriptorProtos$EnumValueOptions.getDefaultInstance();
    }

    public DescriptorProtos$EnumValueOptions build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$EnumValueOptions buildPartial() {
        int i = 1;
        DescriptorProtos$EnumValueOptions descriptorProtos$EnumValueOptions = new DescriptorProtos$EnumValueOptions(this, null);
        if ((this.bitField0_ & 1) != 1) {
            i = 0;
        }
        DescriptorProtos$EnumValueOptions.access$21202(descriptorProtos$EnumValueOptions, this.deprecated_);
        if (this.uninterpretedOptionBuilder_ == null) {
            if ((this.bitField0_ & 2) == 2) {
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                this.bitField0_ &= -3;
            }
            DescriptorProtos$EnumValueOptions.access$21302(descriptorProtos$EnumValueOptions, this.uninterpretedOption_);
        } else {
            DescriptorProtos$EnumValueOptions.access$21302(descriptorProtos$EnumValueOptions, this.uninterpretedOptionBuilder_.build());
        }
        DescriptorProtos$EnumValueOptions.access$21402(descriptorProtos$EnumValueOptions, i);
        onBuilt();
        return descriptorProtos$EnumValueOptions;
    }

    public DescriptorProtos$EnumValueOptions$Builder clone() {
        return (DescriptorProtos$EnumValueOptions$Builder) super.clone();
    }

    public DescriptorProtos$EnumValueOptions$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$EnumValueOptions$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$EnumValueOptions$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$EnumValueOptions$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$EnumValueOptions$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$EnumValueOptions$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$EnumValueOptions$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$EnumValueOptions$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$EnumValueOptions$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$EnumValueOptions$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public <Type> DescriptorProtos$EnumValueOptions$Builder setExtension(GeneratedExtension<DescriptorProtos$EnumValueOptions, Type> generatedExtension, Type type) {
        return (DescriptorProtos$EnumValueOptions$Builder) super.setExtension((GeneratedExtension) generatedExtension, (Object) type);
    }

    public <Type> DescriptorProtos$EnumValueOptions$Builder setExtension(GeneratedExtension<DescriptorProtos$EnumValueOptions, List<Type>> generatedExtension, int i, Type type) {
        return (DescriptorProtos$EnumValueOptions$Builder) super.setExtension((GeneratedExtension) generatedExtension, i, (Object) type);
    }

    public <Type> DescriptorProtos$EnumValueOptions$Builder addExtension(GeneratedExtension<DescriptorProtos$EnumValueOptions, List<Type>> generatedExtension, Type type) {
        return (DescriptorProtos$EnumValueOptions$Builder) super.addExtension((GeneratedExtension) generatedExtension, (Object) type);
    }

    public <Type> DescriptorProtos$EnumValueOptions$Builder clearExtension(GeneratedExtension<DescriptorProtos$EnumValueOptions, ?> generatedExtension) {
        return (DescriptorProtos$EnumValueOptions$Builder) super.clearExtension((GeneratedExtension) generatedExtension);
    }

    public DescriptorProtos$EnumValueOptions$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$EnumValueOptions) {
            return mergeFrom((DescriptorProtos$EnumValueOptions) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$EnumValueOptions$Builder mergeFrom(DescriptorProtos$EnumValueOptions descriptorProtos$EnumValueOptions) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (descriptorProtos$EnumValueOptions != DescriptorProtos$EnumValueOptions.getDefaultInstance()) {
            if (descriptorProtos$EnumValueOptions.hasDeprecated()) {
                setDeprecated(descriptorProtos$EnumValueOptions.getDeprecated());
            }
            if (this.uninterpretedOptionBuilder_ == null) {
                if (!DescriptorProtos$EnumValueOptions.access$21300(descriptorProtos$EnumValueOptions).isEmpty()) {
                    if (this.uninterpretedOption_.isEmpty()) {
                        this.uninterpretedOption_ = DescriptorProtos$EnumValueOptions.access$21300(descriptorProtos$EnumValueOptions);
                        this.bitField0_ &= -3;
                    } else {
                        ensureUninterpretedOptionIsMutable();
                        this.uninterpretedOption_.addAll(DescriptorProtos$EnumValueOptions.access$21300(descriptorProtos$EnumValueOptions));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$EnumValueOptions.access$21300(descriptorProtos$EnumValueOptions).isEmpty()) {
                if (this.uninterpretedOptionBuilder_.isEmpty()) {
                    this.uninterpretedOptionBuilder_.dispose();
                    this.uninterpretedOptionBuilder_ = null;
                    this.uninterpretedOption_ = DescriptorProtos$EnumValueOptions.access$21300(descriptorProtos$EnumValueOptions);
                    this.bitField0_ &= -3;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getUninterpretedOptionFieldBuilder();
                    }
                    this.uninterpretedOptionBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(DescriptorProtos$EnumValueOptions.access$21300(descriptorProtos$EnumValueOptions));
                }
            }
            mergeExtensionFields(descriptorProtos$EnumValueOptions);
            mergeUnknownFields(descriptorProtos$EnumValueOptions.unknownFields);
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

    public DescriptorProtos$EnumValueOptions$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        DescriptorProtos$EnumValueOptions descriptorProtos$EnumValueOptions;
        DescriptorProtos$EnumValueOptions descriptorProtos$EnumValueOptions2;
        try {
            descriptorProtos$EnumValueOptions2 = (DescriptorProtos$EnumValueOptions) DescriptorProtos$EnumValueOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$EnumValueOptions2 != null) {
                mergeFrom(descriptorProtos$EnumValueOptions2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$EnumValueOptions2 = (DescriptorProtos$EnumValueOptions) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$EnumValueOptions = descriptorProtos$EnumValueOptions2;
            th = th3;
            if (descriptorProtos$EnumValueOptions != null) {
                mergeFrom(descriptorProtos$EnumValueOptions);
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

    public DescriptorProtos$EnumValueOptions$Builder setDeprecated(boolean z) {
        this.bitField0_ |= 1;
        this.deprecated_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$EnumValueOptions$Builder clearDeprecated() {
        this.bitField0_ &= -2;
        this.deprecated_ = false;
        onChanged();
        return this;
    }

    private void ensureUninterpretedOptionIsMutable() {
        if ((this.bitField0_ & 2) != 2) {
            this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
            this.bitField0_ |= 2;
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

    public DescriptorProtos$EnumValueOptions$Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$EnumValueOptions$Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.set(i, descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.setMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$EnumValueOptions$Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$EnumValueOptions$Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$EnumValueOptions$Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addMessage(descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$EnumValueOptions$Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(i, descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$EnumValueOptions$Builder addAllUninterpretedOption(Iterable<? extends DescriptorProtos$UninterpretedOption> iterable) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            Builder.addAll(iterable, this.uninterpretedOption_);
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$EnumValueOptions$Builder clearUninterpretedOption() {
        if (this.uninterpretedOptionBuilder_ == null) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.bitField0_ &= -3;
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$EnumValueOptions$Builder removeUninterpretedOption(int i) {
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
            this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilderV3(this.uninterpretedOption_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
            this.uninterpretedOption_ = null;
        }
        return this.uninterpretedOptionBuilder_;
    }

    public final DescriptorProtos$EnumValueOptions$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$EnumValueOptions$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$EnumValueOptions$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$EnumValueOptions$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
