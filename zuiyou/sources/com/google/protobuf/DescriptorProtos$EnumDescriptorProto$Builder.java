package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.EnumDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.EnumOptionsOrBuilder;
import com.google.protobuf.DescriptorProtos.EnumValueDescriptorProtoOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$EnumDescriptorProto$Builder extends Builder<DescriptorProtos$EnumDescriptorProto$Builder> implements EnumDescriptorProtoOrBuilder {
    private int bitField0_;
    private Object name_;
    private SingleFieldBuilderV3<DescriptorProtos$EnumOptions, DescriptorProtos$EnumOptions$Builder, EnumOptionsOrBuilder> optionsBuilder_;
    private DescriptorProtos$EnumOptions options_;
    private RepeatedFieldBuilderV3<DescriptorProtos$EnumValueDescriptorProto, DescriptorProtos$EnumValueDescriptorProto$Builder, EnumValueDescriptorProtoOrBuilder> valueBuilder_;
    private List<DescriptorProtos$EnumValueDescriptorProto> value_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$EnumDescriptorProto.class, DescriptorProtos$EnumDescriptorProto$Builder.class);
    }

    private DescriptorProtos$EnumDescriptorProto$Builder() {
        this.name_ = "";
        this.value_ = Collections.emptyList();
        this.options_ = null;
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$EnumDescriptorProto$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.name_ = "";
        this.value_ = Collections.emptyList();
        this.options_ = null;
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getValueFieldBuilder();
            getOptionsFieldBuilder();
        }
    }

    public DescriptorProtos$EnumDescriptorProto$Builder clear() {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        if (this.valueBuilder_ == null) {
            this.value_ = Collections.emptyList();
            this.bitField0_ &= -3;
        } else {
            this.valueBuilder_.clear();
        }
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -5;
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_descriptor;
    }

    public DescriptorProtos$EnumDescriptorProto getDefaultInstanceForType() {
        return DescriptorProtos$EnumDescriptorProto.getDefaultInstance();
    }

    public DescriptorProtos$EnumDescriptorProto build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$EnumDescriptorProto buildPartial() {
        int i;
        int i2 = 1;
        DescriptorProtos$EnumDescriptorProto descriptorProtos$EnumDescriptorProto = new DescriptorProtos$EnumDescriptorProto(this, null);
        int i3 = this.bitField0_;
        if ((i3 & 1) != 1) {
            i2 = 0;
        }
        DescriptorProtos$EnumDescriptorProto.access$10002(descriptorProtos$EnumDescriptorProto, this.name_);
        if (this.valueBuilder_ == null) {
            if ((this.bitField0_ & 2) == 2) {
                this.value_ = Collections.unmodifiableList(this.value_);
                this.bitField0_ &= -3;
            }
            DescriptorProtos$EnumDescriptorProto.access$10102(descriptorProtos$EnumDescriptorProto, this.value_);
        } else {
            DescriptorProtos$EnumDescriptorProto.access$10102(descriptorProtos$EnumDescriptorProto, this.valueBuilder_.build());
        }
        if ((i3 & 4) == 4) {
            i = i2 | 2;
        } else {
            i = i2;
        }
        if (this.optionsBuilder_ == null) {
            DescriptorProtos$EnumDescriptorProto.access$10202(descriptorProtos$EnumDescriptorProto, this.options_);
        } else {
            DescriptorProtos$EnumDescriptorProto.access$10202(descriptorProtos$EnumDescriptorProto, (DescriptorProtos$EnumOptions) this.optionsBuilder_.build());
        }
        DescriptorProtos$EnumDescriptorProto.access$10302(descriptorProtos$EnumDescriptorProto, i);
        onBuilt();
        return descriptorProtos$EnumDescriptorProto;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder clone() {
        return (DescriptorProtos$EnumDescriptorProto$Builder) super.clone();
    }

    public DescriptorProtos$EnumDescriptorProto$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$EnumDescriptorProto$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$EnumDescriptorProto$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$EnumDescriptorProto$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$EnumDescriptorProto$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$EnumDescriptorProto$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$EnumDescriptorProto$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$EnumDescriptorProto$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$EnumDescriptorProto$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$EnumDescriptorProto$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$EnumDescriptorProto$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$EnumDescriptorProto) {
            return mergeFrom((DescriptorProtos$EnumDescriptorProto) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder mergeFrom(DescriptorProtos$EnumDescriptorProto descriptorProtos$EnumDescriptorProto) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (descriptorProtos$EnumDescriptorProto != DescriptorProtos$EnumDescriptorProto.getDefaultInstance()) {
            if (descriptorProtos$EnumDescriptorProto.hasName()) {
                this.bitField0_ |= 1;
                this.name_ = DescriptorProtos$EnumDescriptorProto.access$10000(descriptorProtos$EnumDescriptorProto);
                onChanged();
            }
            if (this.valueBuilder_ == null) {
                if (!DescriptorProtos$EnumDescriptorProto.access$10100(descriptorProtos$EnumDescriptorProto).isEmpty()) {
                    if (this.value_.isEmpty()) {
                        this.value_ = DescriptorProtos$EnumDescriptorProto.access$10100(descriptorProtos$EnumDescriptorProto);
                        this.bitField0_ &= -3;
                    } else {
                        ensureValueIsMutable();
                        this.value_.addAll(DescriptorProtos$EnumDescriptorProto.access$10100(descriptorProtos$EnumDescriptorProto));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$EnumDescriptorProto.access$10100(descriptorProtos$EnumDescriptorProto).isEmpty()) {
                if (this.valueBuilder_.isEmpty()) {
                    this.valueBuilder_.dispose();
                    this.valueBuilder_ = null;
                    this.value_ = DescriptorProtos$EnumDescriptorProto.access$10100(descriptorProtos$EnumDescriptorProto);
                    this.bitField0_ &= -3;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getValueFieldBuilder();
                    }
                    this.valueBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.valueBuilder_.addAllMessages(DescriptorProtos$EnumDescriptorProto.access$10100(descriptorProtos$EnumDescriptorProto));
                }
            }
            if (descriptorProtos$EnumDescriptorProto.hasOptions()) {
                mergeOptions(descriptorProtos$EnumDescriptorProto.getOptions());
            }
            mergeUnknownFields(descriptorProtos$EnumDescriptorProto.unknownFields);
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        for (int i = 0; i < getValueCount(); i++) {
            if (!getValue(i).isInitialized()) {
                return false;
            }
        }
        if (!hasOptions() || getOptions().isInitialized()) {
            return true;
        }
        return false;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        DescriptorProtos$EnumDescriptorProto descriptorProtos$EnumDescriptorProto;
        DescriptorProtos$EnumDescriptorProto descriptorProtos$EnumDescriptorProto2;
        try {
            descriptorProtos$EnumDescriptorProto2 = (DescriptorProtos$EnumDescriptorProto) DescriptorProtos$EnumDescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$EnumDescriptorProto2 != null) {
                mergeFrom(descriptorProtos$EnumDescriptorProto2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$EnumDescriptorProto2 = (DescriptorProtos$EnumDescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$EnumDescriptorProto = descriptorProtos$EnumDescriptorProto2;
            th = th3;
            if (descriptorProtos$EnumDescriptorProto != null) {
                mergeFrom(descriptorProtos$EnumDescriptorProto);
            }
            throw th;
        }
    }

    public boolean hasName() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.name_ = toStringUtf8;
        }
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

    public DescriptorProtos$EnumDescriptorProto$Builder setName(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder clearName() {
        this.bitField0_ &= -2;
        this.name_ = DescriptorProtos$EnumDescriptorProto.getDefaultInstance().getName();
        onChanged();
        return this;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder setNameBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = byteString;
        onChanged();
        return this;
    }

    private void ensureValueIsMutable() {
        if ((this.bitField0_ & 2) != 2) {
            this.value_ = new ArrayList(this.value_);
            this.bitField0_ |= 2;
        }
    }

    public List<DescriptorProtos$EnumValueDescriptorProto> getValueList() {
        if (this.valueBuilder_ == null) {
            return Collections.unmodifiableList(this.value_);
        }
        return this.valueBuilder_.getMessageList();
    }

    public int getValueCount() {
        if (this.valueBuilder_ == null) {
            return this.value_.size();
        }
        return this.valueBuilder_.getCount();
    }

    public DescriptorProtos$EnumValueDescriptorProto getValue(int i) {
        if (this.valueBuilder_ == null) {
            return (DescriptorProtos$EnumValueDescriptorProto) this.value_.get(i);
        }
        return (DescriptorProtos$EnumValueDescriptorProto) this.valueBuilder_.getMessage(i);
    }

    public DescriptorProtos$EnumDescriptorProto$Builder setValue(int i, DescriptorProtos$EnumValueDescriptorProto descriptorProtos$EnumValueDescriptorProto) {
        if (this.valueBuilder_ != null) {
            this.valueBuilder_.setMessage(i, descriptorProtos$EnumValueDescriptorProto);
        } else if (descriptorProtos$EnumValueDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureValueIsMutable();
            this.value_.set(i, descriptorProtos$EnumValueDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder setValue(int i, DescriptorProtos$EnumValueDescriptorProto$Builder descriptorProtos$EnumValueDescriptorProto$Builder) {
        if (this.valueBuilder_ == null) {
            ensureValueIsMutable();
            this.value_.set(i, descriptorProtos$EnumValueDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.valueBuilder_.setMessage(i, descriptorProtos$EnumValueDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder addValue(DescriptorProtos$EnumValueDescriptorProto descriptorProtos$EnumValueDescriptorProto) {
        if (this.valueBuilder_ != null) {
            this.valueBuilder_.addMessage(descriptorProtos$EnumValueDescriptorProto);
        } else if (descriptorProtos$EnumValueDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureValueIsMutable();
            this.value_.add(descriptorProtos$EnumValueDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder addValue(int i, DescriptorProtos$EnumValueDescriptorProto descriptorProtos$EnumValueDescriptorProto) {
        if (this.valueBuilder_ != null) {
            this.valueBuilder_.addMessage(i, descriptorProtos$EnumValueDescriptorProto);
        } else if (descriptorProtos$EnumValueDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureValueIsMutable();
            this.value_.add(i, descriptorProtos$EnumValueDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder addValue(DescriptorProtos$EnumValueDescriptorProto$Builder descriptorProtos$EnumValueDescriptorProto$Builder) {
        if (this.valueBuilder_ == null) {
            ensureValueIsMutable();
            this.value_.add(descriptorProtos$EnumValueDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.valueBuilder_.addMessage(descriptorProtos$EnumValueDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder addValue(int i, DescriptorProtos$EnumValueDescriptorProto$Builder descriptorProtos$EnumValueDescriptorProto$Builder) {
        if (this.valueBuilder_ == null) {
            ensureValueIsMutable();
            this.value_.add(i, descriptorProtos$EnumValueDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.valueBuilder_.addMessage(i, descriptorProtos$EnumValueDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder addAllValue(Iterable<? extends DescriptorProtos$EnumValueDescriptorProto> iterable) {
        if (this.valueBuilder_ == null) {
            ensureValueIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.value_);
            onChanged();
        } else {
            this.valueBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder clearValue() {
        if (this.valueBuilder_ == null) {
            this.value_ = Collections.emptyList();
            this.bitField0_ &= -3;
            onChanged();
        } else {
            this.valueBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder removeValue(int i) {
        if (this.valueBuilder_ == null) {
            ensureValueIsMutable();
            this.value_.remove(i);
            onChanged();
        } else {
            this.valueBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder getValueBuilder(int i) {
        return (DescriptorProtos$EnumValueDescriptorProto$Builder) getValueFieldBuilder().getBuilder(i);
    }

    public EnumValueDescriptorProtoOrBuilder getValueOrBuilder(int i) {
        if (this.valueBuilder_ == null) {
            return (EnumValueDescriptorProtoOrBuilder) this.value_.get(i);
        }
        return (EnumValueDescriptorProtoOrBuilder) this.valueBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends EnumValueDescriptorProtoOrBuilder> getValueOrBuilderList() {
        if (this.valueBuilder_ != null) {
            return this.valueBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.value_);
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder addValueBuilder() {
        return (DescriptorProtos$EnumValueDescriptorProto$Builder) getValueFieldBuilder().addBuilder(DescriptorProtos$EnumValueDescriptorProto.getDefaultInstance());
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder addValueBuilder(int i) {
        return (DescriptorProtos$EnumValueDescriptorProto$Builder) getValueFieldBuilder().addBuilder(i, DescriptorProtos$EnumValueDescriptorProto.getDefaultInstance());
    }

    public List<DescriptorProtos$EnumValueDescriptorProto$Builder> getValueBuilderList() {
        return getValueFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<DescriptorProtos$EnumValueDescriptorProto, DescriptorProtos$EnumValueDescriptorProto$Builder, EnumValueDescriptorProtoOrBuilder> getValueFieldBuilder() {
        if (this.valueBuilder_ == null) {
            this.valueBuilder_ = new RepeatedFieldBuilderV3(this.value_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
            this.value_ = null;
        }
        return this.valueBuilder_;
    }

    public boolean hasOptions() {
        return (this.bitField0_ & 4) == 4;
    }

    public DescriptorProtos$EnumOptions getOptions() {
        if (this.optionsBuilder_ == null) {
            return this.options_ == null ? DescriptorProtos$EnumOptions.getDefaultInstance() : this.options_;
        } else {
            return (DescriptorProtos$EnumOptions) this.optionsBuilder_.getMessage();
        }
    }

    public DescriptorProtos$EnumDescriptorProto$Builder setOptions(DescriptorProtos$EnumOptions descriptorProtos$EnumOptions) {
        if (this.optionsBuilder_ != null) {
            this.optionsBuilder_.setMessage(descriptorProtos$EnumOptions);
        } else if (descriptorProtos$EnumOptions == null) {
            throw new NullPointerException();
        } else {
            this.options_ = descriptorProtos$EnumOptions;
            onChanged();
        }
        this.bitField0_ |= 4;
        return this;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder setOptions(DescriptorProtos$EnumOptions$Builder descriptorProtos$EnumOptions$Builder) {
        if (this.optionsBuilder_ == null) {
            this.options_ = descriptorProtos$EnumOptions$Builder.build();
            onChanged();
        } else {
            this.optionsBuilder_.setMessage(descriptorProtos$EnumOptions$Builder.build());
        }
        this.bitField0_ |= 4;
        return this;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder mergeOptions(DescriptorProtos$EnumOptions descriptorProtos$EnumOptions) {
        if (this.optionsBuilder_ == null) {
            if ((this.bitField0_ & 4) != 4 || this.options_ == null || this.options_ == DescriptorProtos$EnumOptions.getDefaultInstance()) {
                this.options_ = descriptorProtos$EnumOptions;
            } else {
                this.options_ = DescriptorProtos$EnumOptions.newBuilder(this.options_).mergeFrom(descriptorProtos$EnumOptions).buildPartial();
            }
            onChanged();
        } else {
            this.optionsBuilder_.mergeFrom(descriptorProtos$EnumOptions);
        }
        this.bitField0_ |= 4;
        return this;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder clearOptions() {
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
            onChanged();
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -5;
        return this;
    }

    public DescriptorProtos$EnumOptions$Builder getOptionsBuilder() {
        this.bitField0_ |= 4;
        onChanged();
        return (DescriptorProtos$EnumOptions$Builder) getOptionsFieldBuilder().getBuilder();
    }

    public EnumOptionsOrBuilder getOptionsOrBuilder() {
        if (this.optionsBuilder_ != null) {
            return (EnumOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder();
        }
        return this.options_ == null ? DescriptorProtos$EnumOptions.getDefaultInstance() : this.options_;
    }

    private SingleFieldBuilderV3<DescriptorProtos$EnumOptions, DescriptorProtos$EnumOptions$Builder, EnumOptionsOrBuilder> getOptionsFieldBuilder() {
        if (this.optionsBuilder_ == null) {
            this.optionsBuilder_ = new SingleFieldBuilderV3(getOptions(), getParentForChildren(), isClean());
            this.options_ = null;
        }
        return this.optionsBuilder_;
    }

    public final DescriptorProtos$EnumDescriptorProto$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$EnumDescriptorProto$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$EnumDescriptorProto$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$EnumDescriptorProto$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
