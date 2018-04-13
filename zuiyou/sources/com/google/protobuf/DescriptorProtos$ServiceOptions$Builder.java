package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite.Builder;
import com.google.protobuf.DescriptorProtos.ServiceOptionsOrBuilder;
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

public final class DescriptorProtos$ServiceOptions$Builder extends ExtendableBuilder<DescriptorProtos$ServiceOptions, DescriptorProtos$ServiceOptions$Builder> implements ServiceOptionsOrBuilder {
    private int bitField0_;
    private boolean deprecated_;
    private RepeatedFieldBuilderV3<DescriptorProtos$UninterpretedOption, DescriptorProtos$UninterpretedOption$Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
    private List<DescriptorProtos$UninterpretedOption> uninterpretedOption_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$ServiceOptions.class, DescriptorProtos$ServiceOptions$Builder.class);
    }

    private DescriptorProtos$ServiceOptions$Builder() {
        this.uninterpretedOption_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$ServiceOptions$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.uninterpretedOption_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getUninterpretedOptionFieldBuilder();
        }
    }

    public DescriptorProtos$ServiceOptions$Builder clear() {
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
        return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_descriptor;
    }

    public DescriptorProtos$ServiceOptions getDefaultInstanceForType() {
        return DescriptorProtos$ServiceOptions.getDefaultInstance();
    }

    public DescriptorProtos$ServiceOptions build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$ServiceOptions buildPartial() {
        int i = 1;
        DescriptorProtos$ServiceOptions descriptorProtos$ServiceOptions = new DescriptorProtos$ServiceOptions(this, null);
        if ((this.bitField0_ & 1) != 1) {
            i = 0;
        }
        DescriptorProtos$ServiceOptions.access$22102(descriptorProtos$ServiceOptions, this.deprecated_);
        if (this.uninterpretedOptionBuilder_ == null) {
            if ((this.bitField0_ & 2) == 2) {
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                this.bitField0_ &= -3;
            }
            DescriptorProtos$ServiceOptions.access$22202(descriptorProtos$ServiceOptions, this.uninterpretedOption_);
        } else {
            DescriptorProtos$ServiceOptions.access$22202(descriptorProtos$ServiceOptions, this.uninterpretedOptionBuilder_.build());
        }
        DescriptorProtos$ServiceOptions.access$22302(descriptorProtos$ServiceOptions, i);
        onBuilt();
        return descriptorProtos$ServiceOptions;
    }

    public DescriptorProtos$ServiceOptions$Builder clone() {
        return (DescriptorProtos$ServiceOptions$Builder) super.clone();
    }

    public DescriptorProtos$ServiceOptions$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$ServiceOptions$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$ServiceOptions$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$ServiceOptions$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$ServiceOptions$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$ServiceOptions$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$ServiceOptions$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$ServiceOptions$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$ServiceOptions$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$ServiceOptions$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public <Type> DescriptorProtos$ServiceOptions$Builder setExtension(GeneratedExtension<DescriptorProtos$ServiceOptions, Type> generatedExtension, Type type) {
        return (DescriptorProtos$ServiceOptions$Builder) super.setExtension((GeneratedExtension) generatedExtension, (Object) type);
    }

    public <Type> DescriptorProtos$ServiceOptions$Builder setExtension(GeneratedExtension<DescriptorProtos$ServiceOptions, List<Type>> generatedExtension, int i, Type type) {
        return (DescriptorProtos$ServiceOptions$Builder) super.setExtension((GeneratedExtension) generatedExtension, i, (Object) type);
    }

    public <Type> DescriptorProtos$ServiceOptions$Builder addExtension(GeneratedExtension<DescriptorProtos$ServiceOptions, List<Type>> generatedExtension, Type type) {
        return (DescriptorProtos$ServiceOptions$Builder) super.addExtension((GeneratedExtension) generatedExtension, (Object) type);
    }

    public <Type> DescriptorProtos$ServiceOptions$Builder clearExtension(GeneratedExtension<DescriptorProtos$ServiceOptions, ?> generatedExtension) {
        return (DescriptorProtos$ServiceOptions$Builder) super.clearExtension((GeneratedExtension) generatedExtension);
    }

    public DescriptorProtos$ServiceOptions$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$ServiceOptions) {
            return mergeFrom((DescriptorProtos$ServiceOptions) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$ServiceOptions$Builder mergeFrom(DescriptorProtos$ServiceOptions descriptorProtos$ServiceOptions) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (descriptorProtos$ServiceOptions != DescriptorProtos$ServiceOptions.getDefaultInstance()) {
            if (descriptorProtos$ServiceOptions.hasDeprecated()) {
                setDeprecated(descriptorProtos$ServiceOptions.getDeprecated());
            }
            if (this.uninterpretedOptionBuilder_ == null) {
                if (!DescriptorProtos$ServiceOptions.access$22200(descriptorProtos$ServiceOptions).isEmpty()) {
                    if (this.uninterpretedOption_.isEmpty()) {
                        this.uninterpretedOption_ = DescriptorProtos$ServiceOptions.access$22200(descriptorProtos$ServiceOptions);
                        this.bitField0_ &= -3;
                    } else {
                        ensureUninterpretedOptionIsMutable();
                        this.uninterpretedOption_.addAll(DescriptorProtos$ServiceOptions.access$22200(descriptorProtos$ServiceOptions));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$ServiceOptions.access$22200(descriptorProtos$ServiceOptions).isEmpty()) {
                if (this.uninterpretedOptionBuilder_.isEmpty()) {
                    this.uninterpretedOptionBuilder_.dispose();
                    this.uninterpretedOptionBuilder_ = null;
                    this.uninterpretedOption_ = DescriptorProtos$ServiceOptions.access$22200(descriptorProtos$ServiceOptions);
                    this.bitField0_ &= -3;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getUninterpretedOptionFieldBuilder();
                    }
                    this.uninterpretedOptionBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(DescriptorProtos$ServiceOptions.access$22200(descriptorProtos$ServiceOptions));
                }
            }
            mergeExtensionFields(descriptorProtos$ServiceOptions);
            mergeUnknownFields(descriptorProtos$ServiceOptions.unknownFields);
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

    public DescriptorProtos$ServiceOptions$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        DescriptorProtos$ServiceOptions descriptorProtos$ServiceOptions;
        DescriptorProtos$ServiceOptions descriptorProtos$ServiceOptions2;
        try {
            descriptorProtos$ServiceOptions2 = (DescriptorProtos$ServiceOptions) DescriptorProtos$ServiceOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$ServiceOptions2 != null) {
                mergeFrom(descriptorProtos$ServiceOptions2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$ServiceOptions2 = (DescriptorProtos$ServiceOptions) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$ServiceOptions = descriptorProtos$ServiceOptions2;
            th = th3;
            if (descriptorProtos$ServiceOptions != null) {
                mergeFrom(descriptorProtos$ServiceOptions);
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

    public DescriptorProtos$ServiceOptions$Builder setDeprecated(boolean z) {
        this.bitField0_ |= 1;
        this.deprecated_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$ServiceOptions$Builder clearDeprecated() {
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

    public DescriptorProtos$ServiceOptions$Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$ServiceOptions$Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.set(i, descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.setMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$ServiceOptions$Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$ServiceOptions$Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$ServiceOptions$Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addMessage(descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$ServiceOptions$Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(i, descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$ServiceOptions$Builder addAllUninterpretedOption(Iterable<? extends DescriptorProtos$UninterpretedOption> iterable) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            Builder.addAll(iterable, this.uninterpretedOption_);
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$ServiceOptions$Builder clearUninterpretedOption() {
        if (this.uninterpretedOptionBuilder_ == null) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.bitField0_ &= -3;
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$ServiceOptions$Builder removeUninterpretedOption(int i) {
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

    public final DescriptorProtos$ServiceOptions$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$ServiceOptions$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$ServiceOptions$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$ServiceOptions$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
