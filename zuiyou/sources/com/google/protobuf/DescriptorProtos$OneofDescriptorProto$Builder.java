package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.OneofDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.OneofOptionsOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;

public final class DescriptorProtos$OneofDescriptorProto$Builder extends Builder<DescriptorProtos$OneofDescriptorProto$Builder> implements OneofDescriptorProtoOrBuilder {
    private int bitField0_;
    private Object name_;
    private SingleFieldBuilderV3<DescriptorProtos$OneofOptions, DescriptorProtos$OneofOptions$Builder, OneofOptionsOrBuilder> optionsBuilder_;
    private DescriptorProtos$OneofOptions options_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_OneofDescriptorProto_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_OneofDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$OneofDescriptorProto.class, DescriptorProtos$OneofDescriptorProto$Builder.class);
    }

    private DescriptorProtos$OneofDescriptorProto$Builder() {
        this.name_ = "";
        this.options_ = null;
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$OneofDescriptorProto$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.name_ = "";
        this.options_ = null;
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getOptionsFieldBuilder();
        }
    }

    public DescriptorProtos$OneofDescriptorProto$Builder clear() {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -3;
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_OneofDescriptorProto_descriptor;
    }

    public DescriptorProtos$OneofDescriptorProto getDefaultInstanceForType() {
        return DescriptorProtos$OneofDescriptorProto.getDefaultInstance();
    }

    public DescriptorProtos$OneofDescriptorProto build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$OneofDescriptorProto buildPartial() {
        int i;
        int i2 = 1;
        DescriptorProtos$OneofDescriptorProto descriptorProtos$OneofDescriptorProto = new DescriptorProtos$OneofDescriptorProto(this, null);
        int i3 = this.bitField0_;
        if ((i3 & 1) != 1) {
            i2 = 0;
        }
        DescriptorProtos$OneofDescriptorProto.access$9102(descriptorProtos$OneofDescriptorProto, this.name_);
        if ((i3 & 2) == 2) {
            i = i2 | 2;
        } else {
            i = i2;
        }
        if (this.optionsBuilder_ == null) {
            DescriptorProtos$OneofDescriptorProto.access$9202(descriptorProtos$OneofDescriptorProto, this.options_);
        } else {
            DescriptorProtos$OneofDescriptorProto.access$9202(descriptorProtos$OneofDescriptorProto, (DescriptorProtos$OneofOptions) this.optionsBuilder_.build());
        }
        DescriptorProtos$OneofDescriptorProto.access$9302(descriptorProtos$OneofDescriptorProto, i);
        onBuilt();
        return descriptorProtos$OneofDescriptorProto;
    }

    public DescriptorProtos$OneofDescriptorProto$Builder clone() {
        return (DescriptorProtos$OneofDescriptorProto$Builder) super.clone();
    }

    public DescriptorProtos$OneofDescriptorProto$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$OneofDescriptorProto$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$OneofDescriptorProto$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$OneofDescriptorProto$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$OneofDescriptorProto$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$OneofDescriptorProto$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$OneofDescriptorProto$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$OneofDescriptorProto$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$OneofDescriptorProto$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$OneofDescriptorProto$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$OneofDescriptorProto$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$OneofDescriptorProto) {
            return mergeFrom((DescriptorProtos$OneofDescriptorProto) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$OneofDescriptorProto$Builder mergeFrom(DescriptorProtos$OneofDescriptorProto descriptorProtos$OneofDescriptorProto) {
        if (descriptorProtos$OneofDescriptorProto != DescriptorProtos$OneofDescriptorProto.getDefaultInstance()) {
            if (descriptorProtos$OneofDescriptorProto.hasName()) {
                this.bitField0_ |= 1;
                this.name_ = DescriptorProtos$OneofDescriptorProto.access$9100(descriptorProtos$OneofDescriptorProto);
                onChanged();
            }
            if (descriptorProtos$OneofDescriptorProto.hasOptions()) {
                mergeOptions(descriptorProtos$OneofDescriptorProto.getOptions());
            }
            mergeUnknownFields(descriptorProtos$OneofDescriptorProto.unknownFields);
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

    public DescriptorProtos$OneofDescriptorProto$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        DescriptorProtos$OneofDescriptorProto descriptorProtos$OneofDescriptorProto;
        Throwable th;
        DescriptorProtos$OneofDescriptorProto descriptorProtos$OneofDescriptorProto2;
        try {
            descriptorProtos$OneofDescriptorProto = (DescriptorProtos$OneofDescriptorProto) DescriptorProtos$OneofDescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$OneofDescriptorProto != null) {
                mergeFrom(descriptorProtos$OneofDescriptorProto);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$OneofDescriptorProto = (DescriptorProtos$OneofDescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$OneofDescriptorProto2 = descriptorProtos$OneofDescriptorProto;
            th = th3;
            if (descriptorProtos$OneofDescriptorProto2 != null) {
                mergeFrom(descriptorProtos$OneofDescriptorProto2);
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

    public DescriptorProtos$OneofDescriptorProto$Builder setName(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$OneofDescriptorProto$Builder clearName() {
        this.bitField0_ &= -2;
        this.name_ = DescriptorProtos$OneofDescriptorProto.getDefaultInstance().getName();
        onChanged();
        return this;
    }

    public DescriptorProtos$OneofDescriptorProto$Builder setNameBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasOptions() {
        return (this.bitField0_ & 2) == 2;
    }

    public DescriptorProtos$OneofOptions getOptions() {
        if (this.optionsBuilder_ == null) {
            return this.options_ == null ? DescriptorProtos$OneofOptions.getDefaultInstance() : this.options_;
        } else {
            return (DescriptorProtos$OneofOptions) this.optionsBuilder_.getMessage();
        }
    }

    public DescriptorProtos$OneofDescriptorProto$Builder setOptions(DescriptorProtos$OneofOptions descriptorProtos$OneofOptions) {
        if (this.optionsBuilder_ != null) {
            this.optionsBuilder_.setMessage(descriptorProtos$OneofOptions);
        } else if (descriptorProtos$OneofOptions == null) {
            throw new NullPointerException();
        } else {
            this.options_ = descriptorProtos$OneofOptions;
            onChanged();
        }
        this.bitField0_ |= 2;
        return this;
    }

    public DescriptorProtos$OneofDescriptorProto$Builder setOptions(DescriptorProtos$OneofOptions$Builder descriptorProtos$OneofOptions$Builder) {
        if (this.optionsBuilder_ == null) {
            this.options_ = descriptorProtos$OneofOptions$Builder.build();
            onChanged();
        } else {
            this.optionsBuilder_.setMessage(descriptorProtos$OneofOptions$Builder.build());
        }
        this.bitField0_ |= 2;
        return this;
    }

    public DescriptorProtos$OneofDescriptorProto$Builder mergeOptions(DescriptorProtos$OneofOptions descriptorProtos$OneofOptions) {
        if (this.optionsBuilder_ == null) {
            if ((this.bitField0_ & 2) != 2 || this.options_ == null || this.options_ == DescriptorProtos$OneofOptions.getDefaultInstance()) {
                this.options_ = descriptorProtos$OneofOptions;
            } else {
                this.options_ = DescriptorProtos$OneofOptions.newBuilder(this.options_).mergeFrom(descriptorProtos$OneofOptions).buildPartial();
            }
            onChanged();
        } else {
            this.optionsBuilder_.mergeFrom(descriptorProtos$OneofOptions);
        }
        this.bitField0_ |= 2;
        return this;
    }

    public DescriptorProtos$OneofDescriptorProto$Builder clearOptions() {
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
            onChanged();
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -3;
        return this;
    }

    public DescriptorProtos$OneofOptions$Builder getOptionsBuilder() {
        this.bitField0_ |= 2;
        onChanged();
        return (DescriptorProtos$OneofOptions$Builder) getOptionsFieldBuilder().getBuilder();
    }

    public OneofOptionsOrBuilder getOptionsOrBuilder() {
        if (this.optionsBuilder_ != null) {
            return (OneofOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder();
        }
        return this.options_ == null ? DescriptorProtos$OneofOptions.getDefaultInstance() : this.options_;
    }

    private SingleFieldBuilderV3<DescriptorProtos$OneofOptions, DescriptorProtos$OneofOptions$Builder, OneofOptionsOrBuilder> getOptionsFieldBuilder() {
        if (this.optionsBuilder_ == null) {
            this.optionsBuilder_ = new SingleFieldBuilderV3(getOptions(), getParentForChildren(), isClean());
            this.options_ = null;
        }
        return this.optionsBuilder_;
    }

    public final DescriptorProtos$OneofDescriptorProto$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$OneofDescriptorProto$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$OneofDescriptorProto$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$OneofDescriptorProto$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
