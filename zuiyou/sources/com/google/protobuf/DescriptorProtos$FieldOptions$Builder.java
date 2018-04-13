package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite.Builder;
import com.google.protobuf.DescriptorProtos.FieldOptions.CType;
import com.google.protobuf.DescriptorProtos.FieldOptions.JSType;
import com.google.protobuf.DescriptorProtos.FieldOptionsOrBuilder;
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

public final class DescriptorProtos$FieldOptions$Builder extends ExtendableBuilder<DescriptorProtos$FieldOptions, DescriptorProtos$FieldOptions$Builder> implements FieldOptionsOrBuilder {
    private int bitField0_;
    private int ctype_;
    private boolean deprecated_;
    private int jstype_;
    private boolean lazy_;
    private boolean packed_;
    private RepeatedFieldBuilderV3<DescriptorProtos$UninterpretedOption, DescriptorProtos$UninterpretedOption$Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
    private List<DescriptorProtos$UninterpretedOption> uninterpretedOption_;
    private boolean weak_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_FieldOptions_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_FieldOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$FieldOptions.class, DescriptorProtos$FieldOptions$Builder.class);
    }

    private DescriptorProtos$FieldOptions$Builder() {
        this.ctype_ = 0;
        this.jstype_ = 0;
        this.uninterpretedOption_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$FieldOptions$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.ctype_ = 0;
        this.jstype_ = 0;
        this.uninterpretedOption_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getUninterpretedOptionFieldBuilder();
        }
    }

    public DescriptorProtos$FieldOptions$Builder clear() {
        super.clear();
        this.ctype_ = 0;
        this.bitField0_ &= -2;
        this.packed_ = false;
        this.bitField0_ &= -3;
        this.jstype_ = 0;
        this.bitField0_ &= -5;
        this.lazy_ = false;
        this.bitField0_ &= -9;
        this.deprecated_ = false;
        this.bitField0_ &= -17;
        this.weak_ = false;
        this.bitField0_ &= -33;
        if (this.uninterpretedOptionBuilder_ == null) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.bitField0_ &= -65;
        } else {
            this.uninterpretedOptionBuilder_.clear();
        }
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_FieldOptions_descriptor;
    }

    public DescriptorProtos$FieldOptions getDefaultInstanceForType() {
        return DescriptorProtos$FieldOptions.getDefaultInstance();
    }

    public DescriptorProtos$FieldOptions build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$FieldOptions buildPartial() {
        int i = 1;
        DescriptorProtos$FieldOptions descriptorProtos$FieldOptions = new DescriptorProtos$FieldOptions(this, null);
        int i2 = this.bitField0_;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        DescriptorProtos$FieldOptions.access$18102(descriptorProtos$FieldOptions, this.ctype_);
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        DescriptorProtos$FieldOptions.access$18202(descriptorProtos$FieldOptions, this.packed_);
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        DescriptorProtos$FieldOptions.access$18302(descriptorProtos$FieldOptions, this.jstype_);
        if ((i2 & 8) == 8) {
            i |= 8;
        }
        DescriptorProtos$FieldOptions.access$18402(descriptorProtos$FieldOptions, this.lazy_);
        if ((i2 & 16) == 16) {
            i |= 16;
        }
        DescriptorProtos$FieldOptions.access$18502(descriptorProtos$FieldOptions, this.deprecated_);
        if ((i2 & 32) == 32) {
            i |= 32;
        }
        DescriptorProtos$FieldOptions.access$18602(descriptorProtos$FieldOptions, this.weak_);
        if (this.uninterpretedOptionBuilder_ == null) {
            if ((this.bitField0_ & 64) == 64) {
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                this.bitField0_ &= -65;
            }
            DescriptorProtos$FieldOptions.access$18702(descriptorProtos$FieldOptions, this.uninterpretedOption_);
        } else {
            DescriptorProtos$FieldOptions.access$18702(descriptorProtos$FieldOptions, this.uninterpretedOptionBuilder_.build());
        }
        DescriptorProtos$FieldOptions.access$18802(descriptorProtos$FieldOptions, i);
        onBuilt();
        return descriptorProtos$FieldOptions;
    }

    public DescriptorProtos$FieldOptions$Builder clone() {
        return (DescriptorProtos$FieldOptions$Builder) super.clone();
    }

    public DescriptorProtos$FieldOptions$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$FieldOptions$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$FieldOptions$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$FieldOptions$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$FieldOptions$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$FieldOptions$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$FieldOptions$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$FieldOptions$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$FieldOptions$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$FieldOptions$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public <Type> DescriptorProtos$FieldOptions$Builder setExtension(GeneratedExtension<DescriptorProtos$FieldOptions, Type> generatedExtension, Type type) {
        return (DescriptorProtos$FieldOptions$Builder) super.setExtension((GeneratedExtension) generatedExtension, (Object) type);
    }

    public <Type> DescriptorProtos$FieldOptions$Builder setExtension(GeneratedExtension<DescriptorProtos$FieldOptions, List<Type>> generatedExtension, int i, Type type) {
        return (DescriptorProtos$FieldOptions$Builder) super.setExtension((GeneratedExtension) generatedExtension, i, (Object) type);
    }

    public <Type> DescriptorProtos$FieldOptions$Builder addExtension(GeneratedExtension<DescriptorProtos$FieldOptions, List<Type>> generatedExtension, Type type) {
        return (DescriptorProtos$FieldOptions$Builder) super.addExtension((GeneratedExtension) generatedExtension, (Object) type);
    }

    public <Type> DescriptorProtos$FieldOptions$Builder clearExtension(GeneratedExtension<DescriptorProtos$FieldOptions, ?> generatedExtension) {
        return (DescriptorProtos$FieldOptions$Builder) super.clearExtension((GeneratedExtension) generatedExtension);
    }

    public DescriptorProtos$FieldOptions$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$FieldOptions) {
            return mergeFrom((DescriptorProtos$FieldOptions) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$FieldOptions$Builder mergeFrom(DescriptorProtos$FieldOptions descriptorProtos$FieldOptions) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (descriptorProtos$FieldOptions != DescriptorProtos$FieldOptions.getDefaultInstance()) {
            if (descriptorProtos$FieldOptions.hasCtype()) {
                setCtype(descriptorProtos$FieldOptions.getCtype());
            }
            if (descriptorProtos$FieldOptions.hasPacked()) {
                setPacked(descriptorProtos$FieldOptions.getPacked());
            }
            if (descriptorProtos$FieldOptions.hasJstype()) {
                setJstype(descriptorProtos$FieldOptions.getJstype());
            }
            if (descriptorProtos$FieldOptions.hasLazy()) {
                setLazy(descriptorProtos$FieldOptions.getLazy());
            }
            if (descriptorProtos$FieldOptions.hasDeprecated()) {
                setDeprecated(descriptorProtos$FieldOptions.getDeprecated());
            }
            if (descriptorProtos$FieldOptions.hasWeak()) {
                setWeak(descriptorProtos$FieldOptions.getWeak());
            }
            if (this.uninterpretedOptionBuilder_ == null) {
                if (!DescriptorProtos$FieldOptions.access$18700(descriptorProtos$FieldOptions).isEmpty()) {
                    if (this.uninterpretedOption_.isEmpty()) {
                        this.uninterpretedOption_ = DescriptorProtos$FieldOptions.access$18700(descriptorProtos$FieldOptions);
                        this.bitField0_ &= -65;
                    } else {
                        ensureUninterpretedOptionIsMutable();
                        this.uninterpretedOption_.addAll(DescriptorProtos$FieldOptions.access$18700(descriptorProtos$FieldOptions));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$FieldOptions.access$18700(descriptorProtos$FieldOptions).isEmpty()) {
                if (this.uninterpretedOptionBuilder_.isEmpty()) {
                    this.uninterpretedOptionBuilder_.dispose();
                    this.uninterpretedOptionBuilder_ = null;
                    this.uninterpretedOption_ = DescriptorProtos$FieldOptions.access$18700(descriptorProtos$FieldOptions);
                    this.bitField0_ &= -65;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getUninterpretedOptionFieldBuilder();
                    }
                    this.uninterpretedOptionBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(DescriptorProtos$FieldOptions.access$18700(descriptorProtos$FieldOptions));
                }
            }
            mergeExtensionFields(descriptorProtos$FieldOptions);
            mergeUnknownFields(descriptorProtos$FieldOptions.unknownFields);
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

    public DescriptorProtos$FieldOptions$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        DescriptorProtos$FieldOptions descriptorProtos$FieldOptions;
        Throwable th;
        DescriptorProtos$FieldOptions descriptorProtos$FieldOptions2;
        try {
            descriptorProtos$FieldOptions = (DescriptorProtos$FieldOptions) DescriptorProtos$FieldOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$FieldOptions != null) {
                mergeFrom(descriptorProtos$FieldOptions);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$FieldOptions = (DescriptorProtos$FieldOptions) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$FieldOptions2 = descriptorProtos$FieldOptions;
            th = th3;
            if (descriptorProtos$FieldOptions2 != null) {
                mergeFrom(descriptorProtos$FieldOptions2);
            }
            throw th;
        }
    }

    public boolean hasCtype() {
        return (this.bitField0_ & 1) == 1;
    }

    public CType getCtype() {
        CType valueOf = CType.valueOf(this.ctype_);
        return valueOf == null ? CType.STRING : valueOf;
    }

    public DescriptorProtos$FieldOptions$Builder setCtype(CType cType) {
        if (cType == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.ctype_ = cType.getNumber();
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldOptions$Builder clearCtype() {
        this.bitField0_ &= -2;
        this.ctype_ = 0;
        onChanged();
        return this;
    }

    public boolean hasPacked() {
        return (this.bitField0_ & 2) == 2;
    }

    public boolean getPacked() {
        return this.packed_;
    }

    public DescriptorProtos$FieldOptions$Builder setPacked(boolean z) {
        this.bitField0_ |= 2;
        this.packed_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldOptions$Builder clearPacked() {
        this.bitField0_ &= -3;
        this.packed_ = false;
        onChanged();
        return this;
    }

    public boolean hasJstype() {
        return (this.bitField0_ & 4) == 4;
    }

    public JSType getJstype() {
        JSType valueOf = JSType.valueOf(this.jstype_);
        return valueOf == null ? JSType.JS_NORMAL : valueOf;
    }

    public DescriptorProtos$FieldOptions$Builder setJstype(JSType jSType) {
        if (jSType == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 4;
        this.jstype_ = jSType.getNumber();
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldOptions$Builder clearJstype() {
        this.bitField0_ &= -5;
        this.jstype_ = 0;
        onChanged();
        return this;
    }

    public boolean hasLazy() {
        return (this.bitField0_ & 8) == 8;
    }

    public boolean getLazy() {
        return this.lazy_;
    }

    public DescriptorProtos$FieldOptions$Builder setLazy(boolean z) {
        this.bitField0_ |= 8;
        this.lazy_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldOptions$Builder clearLazy() {
        this.bitField0_ &= -9;
        this.lazy_ = false;
        onChanged();
        return this;
    }

    public boolean hasDeprecated() {
        return (this.bitField0_ & 16) == 16;
    }

    public boolean getDeprecated() {
        return this.deprecated_;
    }

    public DescriptorProtos$FieldOptions$Builder setDeprecated(boolean z) {
        this.bitField0_ |= 16;
        this.deprecated_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldOptions$Builder clearDeprecated() {
        this.bitField0_ &= -17;
        this.deprecated_ = false;
        onChanged();
        return this;
    }

    public boolean hasWeak() {
        return (this.bitField0_ & 32) == 32;
    }

    public boolean getWeak() {
        return this.weak_;
    }

    public DescriptorProtos$FieldOptions$Builder setWeak(boolean z) {
        this.bitField0_ |= 32;
        this.weak_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldOptions$Builder clearWeak() {
        this.bitField0_ &= -33;
        this.weak_ = false;
        onChanged();
        return this;
    }

    private void ensureUninterpretedOptionIsMutable() {
        if ((this.bitField0_ & 64) != 64) {
            this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
            this.bitField0_ |= 64;
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

    public DescriptorProtos$FieldOptions$Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$FieldOptions$Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.set(i, descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.setMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FieldOptions$Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$FieldOptions$Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
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

    public DescriptorProtos$FieldOptions$Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addMessage(descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FieldOptions$Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            this.uninterpretedOption_.add(i, descriptorProtos$UninterpretedOption$Builder.build());
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FieldOptions$Builder addAllUninterpretedOption(Iterable<? extends DescriptorProtos$UninterpretedOption> iterable) {
        if (this.uninterpretedOptionBuilder_ == null) {
            ensureUninterpretedOptionIsMutable();
            Builder.addAll(iterable, this.uninterpretedOption_);
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$FieldOptions$Builder clearUninterpretedOption() {
        if (this.uninterpretedOptionBuilder_ == null) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.bitField0_ &= -65;
            onChanged();
        } else {
            this.uninterpretedOptionBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$FieldOptions$Builder removeUninterpretedOption(int i) {
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
            this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilderV3(this.uninterpretedOption_, (this.bitField0_ & 64) == 64, getParentForChildren(), isClean());
            this.uninterpretedOption_ = null;
        }
        return this.uninterpretedOptionBuilder_;
    }

    public final DescriptorProtos$FieldOptions$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$FieldOptions$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$FieldOptions$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$FieldOptions$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
