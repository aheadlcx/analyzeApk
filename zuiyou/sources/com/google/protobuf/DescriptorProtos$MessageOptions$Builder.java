package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite.Builder;
import com.google.protobuf.DescriptorProtos.MessageOptionsOrBuilder;
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

public final class DescriptorProtos$MessageOptions$Builder extends ExtendableBuilder<DescriptorProtos$MessageOptions, DescriptorProtos$MessageOptions$Builder> implements MessageOptionsOrBuilder {
    private int bitField0_;
    private boolean deprecated_;
    private boolean mapEntry_;
    private boolean messageSetWireFormat_;
    private boolean noStandardDescriptorAccessor_;
    private RepeatedFieldBuilderV3<DescriptorProtos$UninterpretedOption, DescriptorProtos$UninterpretedOption$Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
    private List<DescriptorProtos$UninterpretedOption> uninterpretedOption_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_MessageOptions_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_MessageOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$MessageOptions.class, DescriptorProtos$MessageOptions$Builder.class);
    }

    private DescriptorProtos$MessageOptions$Builder() {
        this.uninterpretedOption_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$MessageOptions$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.uninterpretedOption_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getUninterpretedOptionFieldBuilder();
        }
    }

    public DescriptorProtos$MessageOptions$Builder clear() {
        super.clear();
        this.messageSetWireFormat_ = false;
        this.bitField0_ &= -2;
        this.noStandardDescriptorAccessor_ = false;
        this.bitField0_ &= -3;
        this.deprecated_ = false;
        this.bitField0_ &= -5;
        this.mapEntry_ = false;
        this.bitField0_ &= -9;
        if (this.uninterpretedOptionBuilder_ == null) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.bitField0_ &= -17;
        } else {
            this.uninterpretedOptionBuilder_.clear();
        }
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_MessageOptions_descriptor;
    }

    public DescriptorProtos$MessageOptions getDefaultInstanceForType() {
        return DescriptorProtos$MessageOptions.getDefaultInstance();
    }

    public DescriptorProtos$MessageOptions build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$MessageOptions buildPartial() {
        int i = 1;
        DescriptorProtos$MessageOptions descriptorProtos$MessageOptions = new DescriptorProtos$MessageOptions(this, null);
        int i2 = this.bitField0_;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        DescriptorProtos$MessageOptions.access$16902(descriptorProtos$MessageOptions, this.messageSetWireFormat_);
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        DescriptorProtos$MessageOptions.access$17002(descriptorProtos$MessageOptions, this.noStandardDescriptorAccessor_);
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        DescriptorProtos$MessageOptions.access$17102(descriptorProtos$MessageOptions, this.deprecated_);
        if ((i2 & 8) == 8) {
            i |= 8;
        }
        DescriptorProtos$MessageOptions.access$17202(descriptorProtos$MessageOptions, this.mapEntry_);
        if (this.uninterpretedOptionBuilder_ == null) {
            if ((this.bitField0_ & 16) == 16) {
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                this.bitField0_ &= -17;
            }
            DescriptorProtos$MessageOptions.access$17302(descriptorProtos$MessageOptions, this.uninterpretedOption_);
        } else {
            DescriptorProtos$MessageOptions.access$17302(descriptorProtos$MessageOptions, this.uninterpretedOptionBuilder_.build());
        }
        DescriptorProtos$MessageOptions.access$17402(descriptorProtos$MessageOptions, i);
        onBuilt();
        return descriptorProtos$MessageOptions;
    }

    public DescriptorProtos$MessageOptions$Builder clone() {
        return (DescriptorProtos$MessageOptions$Builder) super.clone();
    }

    public DescriptorProtos$MessageOptions$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$MessageOptions$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$MessageOptions$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$MessageOptions$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$MessageOptions$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$MessageOptions$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$MessageOptions$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$MessageOptions$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$MessageOptions$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$MessageOptions$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public <Type> DescriptorProtos$MessageOptions$Builder setExtension(GeneratedExtension<DescriptorProtos$MessageOptions, Type> generatedExtension, Type type) {
        return (DescriptorProtos$MessageOptions$Builder) super.setExtension((GeneratedExtension) generatedExtension, (Object) type);
    }

    public <Type> DescriptorProtos$MessageOptions$Builder setExtension(GeneratedExtension<DescriptorProtos$MessageOptions, List<Type>> generatedExtension, int i, Type type) {
        return (DescriptorProtos$MessageOptions$Builder) super.setExtension((GeneratedExtension) generatedExtension, i, (Object) type);
    }

    public <Type> DescriptorProtos$MessageOptions$Builder addExtension(GeneratedExtension<DescriptorProtos$MessageOptions, List<Type>> generatedExtension, Type type) {
        return (DescriptorProtos$MessageOptions$Builder) super.addExtension((GeneratedExtension) generatedExtension, (Object) type);
    }

    public <Type> DescriptorProtos$MessageOptions$Builder clearExtension(GeneratedExtension<DescriptorProtos$MessageOptions, ?> generatedExtension) {
        return (DescriptorProtos$MessageOptions$Builder) super.clearExtension((GeneratedExtension) generatedExtension);
    }

    public DescriptorProtos$MessageOptions$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$MessageOptions) {
            return mergeFrom((DescriptorProtos$MessageOptions) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$MessageOptions$Builder mergeFrom(DescriptorProtos$MessageOptions descriptorProtos$MessageOptions) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (descriptorProtos$MessageOptions != DescriptorProtos$MessageOptions.getDefaultInstance()) {
            if (descriptorProtos$MessageOptions.hasMessageSetWireFormat()) {
                setMessageSetWireFormat(descriptorProtos$MessageOptions.getMessageSetWireFormat());
            }
            if (descriptorProtos$MessageOptions.hasNoStandardDescriptorAccessor()) {
                setNoStandardDescriptorAccessor(descriptorProtos$MessageOptions.getNoStandardDescriptorAccessor());
            }
            if (descriptorProtos$MessageOptions.hasDeprecated()) {
                setDeprecated(descriptorProtos$MessageOptions.getDeprecated());
            }
            if (descriptorProtos$MessageOptions.hasMapEntry()) {
                setMapEntry(descriptorProtos$MessageOptions.getMapEntry());
            }
            if (this.uninterpretedOptionBuilder_ == null) {
                if (!DescriptorProtos$MessageOptions.access$17300(descriptorProtos$MessageOptions).isEmpty()) {
                    if (this.uninterpretedOption_.isEmpty()) {
                        this.uninterpretedOption_ = DescriptorProtos$MessageOptions.access$17300(descriptorProtos$MessageOptions);
                        this.bitField0_ &= -17;
                    } else {
                        ensureUninterpretedOptionIsMutable();
                        this.uninterpretedOption_.addAll(DescriptorProtos$MessageOptions.access$17300(descriptorProtos$MessageOptions));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$MessageOptions.access$17300(descriptorProtos$MessageOptions).isEmpty()) {
                if (this.uninterpretedOptionBuilder_.isEmpty()) {
                    this.uninterpretedOptionBuilder_.dispose();
                    this.uninterpretedOptionBuilder_ = null;
                    this.uninterpretedOption_ = DescriptorProtos$MessageOptions.access$17300(descriptorProtos$MessageOptions);
                    this.bitField0_ &= -17;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getUninterpretedOptionFieldBuilder();
                    }
                    this.uninterpretedOptionBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(DescriptorProtos$MessageOptions.access$17300(descriptorProtos$MessageOptions));
                }
            }
            mergeExtensionFields(descriptorProtos$MessageOptions);
            mergeUnknownFields(descriptorProtos$MessageOptions.unknownFields);
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

    public DescriptorProtos$MessageOptions$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        DescriptorProtos$MessageOptions descriptorProtos$MessageOptions;
        Throwable th;
        DescriptorProtos$MessageOptions descriptorProtos$MessageOptions2;
        try {
            descriptorProtos$MessageOptions = (DescriptorProtos$MessageOptions) DescriptorProtos$MessageOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$MessageOptions != null) {
                mergeFrom(descriptorProtos$MessageOptions);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$MessageOptions = (DescriptorProtos$MessageOptions) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$MessageOptions2 = descriptorProtos$MessageOptions;
            th = th3;
            if (descriptorProtos$MessageOptions2 != null) {
                mergeFrom(descriptorProtos$MessageOptions2);
            }
            throw th;
        }
    }

    public boolean hasMessageSetWireFormat() {
        return (this.bitField0_ & 1) == 1;
    }

    public boolean getMessageSetWireFormat() {
        return this.messageSetWireFormat_;
    }

    public DescriptorProtos$MessageOptions$Builder setMessageSetWireFormat(boolean z) {
        this.bitField0_ |= 1;
        this.messageSetWireFormat_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$MessageOptions$Builder clearMessageSetWireFormat() {
        this.bitField0_ &= -2;
        this.messageSetWireFormat_ = false;
        onChanged();
        return this;
    }

    public boolean hasNoStandardDescriptorAccessor() {
        return (this.bitField0_ & 2) == 2;
    }

    public boolean getNoStandardDescriptorAccessor() {
        return this.noStandardDescriptorAccessor_;
    }

    public DescriptorProtos$MessageOptions$Builder setNoStandardDescriptorAccessor(boolean z) {
        this.bitField0_ |= 2;
        this.noStandardDescriptorAccessor_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$MessageOptions$Builder clearNoStandardDescriptorAccessor() {
        this.bitField0_ &= -3;
        this.noStandardDescriptorAccessor_ = false;
        onChanged();
        return this;
    }

    public boolean hasDeprecated() {
        return (this.bitField0_ & 4) == 4;
    }

    public boolean getDeprecated() {
        return this.deprecated_;
    }

    public DescriptorProtos$MessageOptions$Builder setDeprecated(boolean z) {
        this.bitField0_ |= 4;
        this.deprecated_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$MessageOptions$Builder clearDeprecated() {
        this.bitField0_ &= -5;
        this.deprecated_ = false;
        onChanged();
        return this;
    }

    public boolean hasMapEntry() {
        return (this.bitField0_ & 8) == 8;
    }

    public boolean getMapEntry() {
        return this.mapEntry_;
    }

    public DescriptorProtos$MessageOptions$Builder setMapEntry(boolean z) {
        this.bitField0_ |= 8;
        this.mapEntry_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$MessageOptions$Builder clearMapEntry() {
        this.bitField0_ &= -9;
        this.mapEntry_ = false;
        onChanged();
        return this;
    }

    private void ensureUninterpretedOptionIsMutable() {
        if ((this.bitField0_ & 16) != 16) {
            this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
            this.bitField0_ |= 16;
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

    public DescriptorProtos$MessageOptions$Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$MessageOptions$Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.set(i, descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.setMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$MessageOptions$Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$MessageOptions$Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$MessageOptions$Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addMessage(descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$MessageOptions$Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(i, descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$MessageOptions$Builder addAllUninterpretedOption(Iterable<? extends DescriptorProtos$UninterpretedOption> iterable) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            Builder.addAll(iterable, this.uninterpretedOption_);
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$MessageOptions$Builder clearUninterpretedOption() {
        if (this.uninterpretedOptionBuilder_ == null) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.bitField0_ &= -17;
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$MessageOptions$Builder removeUninterpretedOption(int i) {
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
            this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilderV3(this.uninterpretedOption_, (this.bitField0_ & 16) == 16, getParentForChildren(), isClean());
            this.uninterpretedOption_ = null;
        }
        return this.uninterpretedOptionBuilder_;
    }

    public final DescriptorProtos$MessageOptions$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$MessageOptions$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$MessageOptions$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$MessageOptions$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
