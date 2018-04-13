package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite.Builder;
import com.google.protobuf.DescriptorProtos.ExtensionRangeOptionsOrBuilder;
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

public final class DescriptorProtos$ExtensionRangeOptions$Builder extends ExtendableBuilder<DescriptorProtos$ExtensionRangeOptions, DescriptorProtos$ExtensionRangeOptions$Builder> implements ExtensionRangeOptionsOrBuilder {
    private int bitField0_;
    private RepeatedFieldBuilderV3<DescriptorProtos$UninterpretedOption, DescriptorProtos$UninterpretedOption$Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
    private List<DescriptorProtos$UninterpretedOption> uninterpretedOption_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_ExtensionRangeOptions_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_ExtensionRangeOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$ExtensionRangeOptions.class, DescriptorProtos$ExtensionRangeOptions$Builder.class);
    }

    private DescriptorProtos$ExtensionRangeOptions$Builder() {
        this.uninterpretedOption_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$ExtensionRangeOptions$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.uninterpretedOption_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getUninterpretedOptionFieldBuilder();
        }
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder clear() {
        super.clear();
        if (this.uninterpretedOptionBuilder_ == null) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.bitField0_ &= -2;
        } else {
            this.uninterpretedOptionBuilder_.clear();
        }
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_ExtensionRangeOptions_descriptor;
    }

    public DescriptorProtos$ExtensionRangeOptions getDefaultInstanceForType() {
        return DescriptorProtos$ExtensionRangeOptions.getDefaultInstance();
    }

    public DescriptorProtos$ExtensionRangeOptions build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$ExtensionRangeOptions buildPartial() {
        DescriptorProtos$ExtensionRangeOptions descriptorProtos$ExtensionRangeOptions = new DescriptorProtos$ExtensionRangeOptions(this, null);
        int i = this.bitField0_;
        if (this.uninterpretedOptionBuilder_ == null) {
            if ((this.bitField0_ & 1) == 1) {
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                this.bitField0_ &= -2;
            }
            DescriptorProtos$ExtensionRangeOptions.access$6702(descriptorProtos$ExtensionRangeOptions, this.uninterpretedOption_);
        } else {
            DescriptorProtos$ExtensionRangeOptions.access$6702(descriptorProtos$ExtensionRangeOptions, this.uninterpretedOptionBuilder_.build());
        }
        onBuilt();
        return descriptorProtos$ExtensionRangeOptions;
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder clone() {
        return (DescriptorProtos$ExtensionRangeOptions$Builder) super.clone();
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$ExtensionRangeOptions$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$ExtensionRangeOptions$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$ExtensionRangeOptions$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$ExtensionRangeOptions$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$ExtensionRangeOptions$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public <Type> DescriptorProtos$ExtensionRangeOptions$Builder setExtension(GeneratedExtension<DescriptorProtos$ExtensionRangeOptions, Type> generatedExtension, Type type) {
        return (DescriptorProtos$ExtensionRangeOptions$Builder) super.setExtension((GeneratedExtension) generatedExtension, (Object) type);
    }

    public <Type> DescriptorProtos$ExtensionRangeOptions$Builder setExtension(GeneratedExtension<DescriptorProtos$ExtensionRangeOptions, List<Type>> generatedExtension, int i, Type type) {
        return (DescriptorProtos$ExtensionRangeOptions$Builder) super.setExtension((GeneratedExtension) generatedExtension, i, (Object) type);
    }

    public <Type> DescriptorProtos$ExtensionRangeOptions$Builder addExtension(GeneratedExtension<DescriptorProtos$ExtensionRangeOptions, List<Type>> generatedExtension, Type type) {
        return (DescriptorProtos$ExtensionRangeOptions$Builder) super.addExtension((GeneratedExtension) generatedExtension, (Object) type);
    }

    public <Type> DescriptorProtos$ExtensionRangeOptions$Builder clearExtension(GeneratedExtension<DescriptorProtos$ExtensionRangeOptions, ?> generatedExtension) {
        return (DescriptorProtos$ExtensionRangeOptions$Builder) super.clearExtension((GeneratedExtension) generatedExtension);
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$ExtensionRangeOptions) {
            return mergeFrom((DescriptorProtos$ExtensionRangeOptions) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder mergeFrom(DescriptorProtos$ExtensionRangeOptions descriptorProtos$ExtensionRangeOptions) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (descriptorProtos$ExtensionRangeOptions != DescriptorProtos$ExtensionRangeOptions.getDefaultInstance()) {
            if (this.uninterpretedOptionBuilder_ == null) {
                if (!DescriptorProtos$ExtensionRangeOptions.access$6700(descriptorProtos$ExtensionRangeOptions).isEmpty()) {
                    if (this.uninterpretedOption_.isEmpty()) {
                        this.uninterpretedOption_ = DescriptorProtos$ExtensionRangeOptions.access$6700(descriptorProtos$ExtensionRangeOptions);
                        this.bitField0_ &= -2;
                    } else {
                        ensureUninterpretedOptionIsMutable();
                        this.uninterpretedOption_.addAll(DescriptorProtos$ExtensionRangeOptions.access$6700(descriptorProtos$ExtensionRangeOptions));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$ExtensionRangeOptions.access$6700(descriptorProtos$ExtensionRangeOptions).isEmpty()) {
                if (this.uninterpretedOptionBuilder_.isEmpty()) {
                    this.uninterpretedOptionBuilder_.dispose();
                    this.uninterpretedOptionBuilder_ = null;
                    this.uninterpretedOption_ = DescriptorProtos$ExtensionRangeOptions.access$6700(descriptorProtos$ExtensionRangeOptions);
                    this.bitField0_ &= -2;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getUninterpretedOptionFieldBuilder();
                    }
                    this.uninterpretedOptionBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(DescriptorProtos$ExtensionRangeOptions.access$6700(descriptorProtos$ExtensionRangeOptions));
                }
            }
            mergeExtensionFields(descriptorProtos$ExtensionRangeOptions);
            mergeUnknownFields(descriptorProtos$ExtensionRangeOptions.unknownFields);
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

    public DescriptorProtos$ExtensionRangeOptions$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        DescriptorProtos$ExtensionRangeOptions descriptorProtos$ExtensionRangeOptions;
        DescriptorProtos$ExtensionRangeOptions descriptorProtos$ExtensionRangeOptions2;
        try {
            descriptorProtos$ExtensionRangeOptions2 = (DescriptorProtos$ExtensionRangeOptions) DescriptorProtos$ExtensionRangeOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$ExtensionRangeOptions2 != null) {
                mergeFrom(descriptorProtos$ExtensionRangeOptions2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$ExtensionRangeOptions2 = (DescriptorProtos$ExtensionRangeOptions) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$ExtensionRangeOptions = descriptorProtos$ExtensionRangeOptions2;
            th = th3;
            if (descriptorProtos$ExtensionRangeOptions != null) {
                mergeFrom(descriptorProtos$ExtensionRangeOptions);
            }
            throw th;
        }
    }

    private void ensureUninterpretedOptionIsMutable() {
        if ((this.bitField0_ & 1) != 1) {
            this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
            this.bitField0_ |= 1;
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

    public DescriptorProtos$ExtensionRangeOptions$Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$ExtensionRangeOptions$Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.set(i, descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.setMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$ExtensionRangeOptions$Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$ExtensionRangeOptions$Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addMessage(descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(i, descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder addAllUninterpretedOption(Iterable<? extends DescriptorProtos$UninterpretedOption> iterable) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            Builder.addAll(iterable, this.uninterpretedOption_);
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder clearUninterpretedOption() {
        if (this.uninterpretedOptionBuilder_ == null) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.bitField0_ &= -2;
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder removeUninterpretedOption(int i) {
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
        boolean z = true;
        if (this.uninterpretedOptionBuilder_ == null) {
            List list = this.uninterpretedOption_;
            if ((this.bitField0_ & 1) != 1) {
                z = false;
            }
            this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilderV3(list, z, getParentForChildren(), isClean());
            this.uninterpretedOption_ = null;
        }
        return this.uninterpretedOptionBuilder_;
    }

    public final DescriptorProtos$ExtensionRangeOptions$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$ExtensionRangeOptions$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$ExtensionRangeOptions$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$ExtensionRangeOptions$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
