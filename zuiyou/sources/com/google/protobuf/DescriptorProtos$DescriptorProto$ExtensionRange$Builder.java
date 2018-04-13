package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.DescriptorProto.ExtensionRange;
import com.google.protobuf.DescriptorProtos.ExtensionRangeOptionsOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;

public final class DescriptorProtos$DescriptorProto$ExtensionRange$Builder extends Builder<DescriptorProtos$DescriptorProto$ExtensionRange$Builder> implements DescriptorProtos$DescriptorProto$ExtensionRangeOrBuilder {
    private int bitField0_;
    private int end_;
    private SingleFieldBuilderV3<DescriptorProtos$ExtensionRangeOptions, DescriptorProtos$ExtensionRangeOptions$Builder, ExtensionRangeOptionsOrBuilder> optionsBuilder_;
    private DescriptorProtos$ExtensionRangeOptions options_;
    private int start_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable.ensureFieldAccessorsInitialized(ExtensionRange.class, DescriptorProtos$DescriptorProto$ExtensionRange$Builder.class);
    }

    private DescriptorProtos$DescriptorProto$ExtensionRange$Builder() {
        this.options_ = null;
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$DescriptorProto$ExtensionRange$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.options_ = null;
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getOptionsFieldBuilder();
        }
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder clear() {
        super.clear();
        this.start_ = 0;
        this.bitField0_ &= -2;
        this.end_ = 0;
        this.bitField0_ &= -3;
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -5;
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor;
    }

    public ExtensionRange getDefaultInstanceForType() {
        return ExtensionRange.getDefaultInstance();
    }

    public ExtensionRange build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public ExtensionRange buildPartial() {
        int i;
        int i2 = 1;
        ExtensionRange extensionRange = new ExtensionRange(this, null);
        int i3 = this.bitField0_;
        if ((i3 & 1) != 1) {
            i2 = 0;
        }
        ExtensionRange.access$3302(extensionRange, this.start_);
        if ((i3 & 2) == 2) {
            i2 |= 2;
        }
        ExtensionRange.access$3402(extensionRange, this.end_);
        if ((i3 & 4) == 4) {
            i = i2 | 4;
        } else {
            i = i2;
        }
        if (this.optionsBuilder_ == null) {
            ExtensionRange.access$3502(extensionRange, this.options_);
        } else {
            ExtensionRange.access$3502(extensionRange, (DescriptorProtos$ExtensionRangeOptions) this.optionsBuilder_.build());
        }
        ExtensionRange.access$3602(extensionRange, i);
        onBuilt();
        return extensionRange;
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder clone() {
        return (DescriptorProtos$DescriptorProto$ExtensionRange$Builder) super.clone();
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$DescriptorProto$ExtensionRange$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$DescriptorProto$ExtensionRange$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$DescriptorProto$ExtensionRange$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$DescriptorProto$ExtensionRange$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$DescriptorProto$ExtensionRange$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder mergeFrom(Message message) {
        if (message instanceof ExtensionRange) {
            return mergeFrom((ExtensionRange) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder mergeFrom(ExtensionRange extensionRange) {
        if (extensionRange != ExtensionRange.getDefaultInstance()) {
            if (extensionRange.hasStart()) {
                setStart(extensionRange.getStart());
            }
            if (extensionRange.hasEnd()) {
                setEnd(extensionRange.getEnd());
            }
            if (extensionRange.hasOptions()) {
                mergeOptions(extensionRange.getOptions());
            }
            mergeUnknownFields(extensionRange.unknownFields);
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        if (!hasOptions() || getOptions().isInitialized()) {
            return true;
        }
        return false;
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        ExtensionRange extensionRange;
        ExtensionRange extensionRange2;
        try {
            extensionRange2 = (ExtensionRange) ExtensionRange.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (extensionRange2 != null) {
                mergeFrom(extensionRange2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            extensionRange2 = (ExtensionRange) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            extensionRange = extensionRange2;
            th = th3;
            if (extensionRange != null) {
                mergeFrom(extensionRange);
            }
            throw th;
        }
    }

    public boolean hasStart() {
        return (this.bitField0_ & 1) == 1;
    }

    public int getStart() {
        return this.start_;
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder setStart(int i) {
        this.bitField0_ |= 1;
        this.start_ = i;
        onChanged();
        return this;
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder clearStart() {
        this.bitField0_ &= -2;
        this.start_ = 0;
        onChanged();
        return this;
    }

    public boolean hasEnd() {
        return (this.bitField0_ & 2) == 2;
    }

    public int getEnd() {
        return this.end_;
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder setEnd(int i) {
        this.bitField0_ |= 2;
        this.end_ = i;
        onChanged();
        return this;
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder clearEnd() {
        this.bitField0_ &= -3;
        this.end_ = 0;
        onChanged();
        return this;
    }

    public boolean hasOptions() {
        return (this.bitField0_ & 4) == 4;
    }

    public DescriptorProtos$ExtensionRangeOptions getOptions() {
        if (this.optionsBuilder_ == null) {
            return this.options_ == null ? DescriptorProtos$ExtensionRangeOptions.getDefaultInstance() : this.options_;
        } else {
            return (DescriptorProtos$ExtensionRangeOptions) this.optionsBuilder_.getMessage();
        }
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder setOptions(DescriptorProtos$ExtensionRangeOptions descriptorProtos$ExtensionRangeOptions) {
        if (this.optionsBuilder_ != null) {
            this.optionsBuilder_.setMessage(descriptorProtos$ExtensionRangeOptions);
        } else if (descriptorProtos$ExtensionRangeOptions == null) {
            throw new NullPointerException();
        } else {
            this.options_ = descriptorProtos$ExtensionRangeOptions;
            onChanged();
        }
        this.bitField0_ |= 4;
        return this;
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder setOptions(DescriptorProtos$ExtensionRangeOptions$Builder descriptorProtos$ExtensionRangeOptions$Builder) {
        if (this.optionsBuilder_ == null) {
            this.options_ = descriptorProtos$ExtensionRangeOptions$Builder.build();
            onChanged();
        } else {
            this.optionsBuilder_.setMessage(descriptorProtos$ExtensionRangeOptions$Builder.build());
        }
        this.bitField0_ |= 4;
        return this;
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder mergeOptions(DescriptorProtos$ExtensionRangeOptions descriptorProtos$ExtensionRangeOptions) {
        if (this.optionsBuilder_ == null) {
            if ((this.bitField0_ & 4) != 4 || this.options_ == null || this.options_ == DescriptorProtos$ExtensionRangeOptions.getDefaultInstance()) {
                this.options_ = descriptorProtos$ExtensionRangeOptions;
            } else {
                this.options_ = DescriptorProtos$ExtensionRangeOptions.newBuilder(this.options_).mergeFrom(descriptorProtos$ExtensionRangeOptions).buildPartial();
            }
            onChanged();
        } else {
            this.optionsBuilder_.mergeFrom(descriptorProtos$ExtensionRangeOptions);
        }
        this.bitField0_ |= 4;
        return this;
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder clearOptions() {
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
            onChanged();
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -5;
        return this;
    }

    public DescriptorProtos$ExtensionRangeOptions$Builder getOptionsBuilder() {
        this.bitField0_ |= 4;
        onChanged();
        return (DescriptorProtos$ExtensionRangeOptions$Builder) getOptionsFieldBuilder().getBuilder();
    }

    public ExtensionRangeOptionsOrBuilder getOptionsOrBuilder() {
        if (this.optionsBuilder_ != null) {
            return (ExtensionRangeOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder();
        }
        return this.options_ == null ? DescriptorProtos$ExtensionRangeOptions.getDefaultInstance() : this.options_;
    }

    private SingleFieldBuilderV3<DescriptorProtos$ExtensionRangeOptions, DescriptorProtos$ExtensionRangeOptions$Builder, ExtensionRangeOptionsOrBuilder> getOptionsFieldBuilder() {
        if (this.optionsBuilder_ == null) {
            this.optionsBuilder_ = new SingleFieldBuilderV3(getOptions(), getParentForChildren(), isClean());
            this.options_ = null;
        }
        return this.optionsBuilder_;
    }

    public final DescriptorProtos$DescriptorProto$ExtensionRange$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$DescriptorProto$ExtensionRange$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$DescriptorProto$ExtensionRange$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$DescriptorProto$ExtensionRange$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
