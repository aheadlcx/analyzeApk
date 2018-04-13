package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.EnumValueDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.EnumValueOptionsOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;

public final class DescriptorProtos$EnumValueDescriptorProto$Builder extends Builder<DescriptorProtos$EnumValueDescriptorProto$Builder> implements EnumValueDescriptorProtoOrBuilder {
    private int bitField0_;
    private Object name_;
    private int number_;
    private SingleFieldBuilderV3<DescriptorProtos$EnumValueOptions, DescriptorProtos$EnumValueOptions$Builder, EnumValueOptionsOrBuilder> optionsBuilder_;
    private DescriptorProtos$EnumValueOptions options_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$EnumValueDescriptorProto.class, DescriptorProtos$EnumValueDescriptorProto$Builder.class);
    }

    private DescriptorProtos$EnumValueDescriptorProto$Builder() {
        this.name_ = "";
        this.options_ = null;
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$EnumValueDescriptorProto$Builder(BuilderParent builderParent) {
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

    public DescriptorProtos$EnumValueDescriptorProto$Builder clear() {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        this.number_ = 0;
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
        return DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_descriptor;
    }

    public DescriptorProtos$EnumValueDescriptorProto getDefaultInstanceForType() {
        return DescriptorProtos$EnumValueDescriptorProto.getDefaultInstance();
    }

    public DescriptorProtos$EnumValueDescriptorProto build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$EnumValueDescriptorProto buildPartial() {
        int i;
        int i2 = 1;
        DescriptorProtos$EnumValueDescriptorProto descriptorProtos$EnumValueDescriptorProto = new DescriptorProtos$EnumValueDescriptorProto(this, null);
        int i3 = this.bitField0_;
        if ((i3 & 1) != 1) {
            i2 = 0;
        }
        DescriptorProtos$EnumValueDescriptorProto.access$11002(descriptorProtos$EnumValueDescriptorProto, this.name_);
        if ((i3 & 2) == 2) {
            i2 |= 2;
        }
        DescriptorProtos$EnumValueDescriptorProto.access$11102(descriptorProtos$EnumValueDescriptorProto, this.number_);
        if ((i3 & 4) == 4) {
            i = i2 | 4;
        } else {
            i = i2;
        }
        if (this.optionsBuilder_ == null) {
            DescriptorProtos$EnumValueDescriptorProto.access$11202(descriptorProtos$EnumValueDescriptorProto, this.options_);
        } else {
            DescriptorProtos$EnumValueDescriptorProto.access$11202(descriptorProtos$EnumValueDescriptorProto, (DescriptorProtos$EnumValueOptions) this.optionsBuilder_.build());
        }
        DescriptorProtos$EnumValueDescriptorProto.access$11302(descriptorProtos$EnumValueDescriptorProto, i);
        onBuilt();
        return descriptorProtos$EnumValueDescriptorProto;
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder clone() {
        return (DescriptorProtos$EnumValueDescriptorProto$Builder) super.clone();
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$EnumValueDescriptorProto$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$EnumValueDescriptorProto$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$EnumValueDescriptorProto$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$EnumValueDescriptorProto$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$EnumValueDescriptorProto$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$EnumValueDescriptorProto) {
            return mergeFrom((DescriptorProtos$EnumValueDescriptorProto) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder mergeFrom(DescriptorProtos$EnumValueDescriptorProto descriptorProtos$EnumValueDescriptorProto) {
        if (descriptorProtos$EnumValueDescriptorProto != DescriptorProtos$EnumValueDescriptorProto.getDefaultInstance()) {
            if (descriptorProtos$EnumValueDescriptorProto.hasName()) {
                this.bitField0_ |= 1;
                this.name_ = DescriptorProtos$EnumValueDescriptorProto.access$11000(descriptorProtos$EnumValueDescriptorProto);
                onChanged();
            }
            if (descriptorProtos$EnumValueDescriptorProto.hasNumber()) {
                setNumber(descriptorProtos$EnumValueDescriptorProto.getNumber());
            }
            if (descriptorProtos$EnumValueDescriptorProto.hasOptions()) {
                mergeOptions(descriptorProtos$EnumValueDescriptorProto.getOptions());
            }
            mergeUnknownFields(descriptorProtos$EnumValueDescriptorProto.unknownFields);
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

    public DescriptorProtos$EnumValueDescriptorProto$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        DescriptorProtos$EnumValueDescriptorProto descriptorProtos$EnumValueDescriptorProto;
        DescriptorProtos$EnumValueDescriptorProto descriptorProtos$EnumValueDescriptorProto2;
        try {
            descriptorProtos$EnumValueDescriptorProto2 = (DescriptorProtos$EnumValueDescriptorProto) DescriptorProtos$EnumValueDescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$EnumValueDescriptorProto2 != null) {
                mergeFrom(descriptorProtos$EnumValueDescriptorProto2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$EnumValueDescriptorProto2 = (DescriptorProtos$EnumValueDescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$EnumValueDescriptorProto = descriptorProtos$EnumValueDescriptorProto2;
            th = th3;
            if (descriptorProtos$EnumValueDescriptorProto != null) {
                mergeFrom(descriptorProtos$EnumValueDescriptorProto);
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

    public DescriptorProtos$EnumValueDescriptorProto$Builder setName(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder clearName() {
        this.bitField0_ &= -2;
        this.name_ = DescriptorProtos$EnumValueDescriptorProto.getDefaultInstance().getName();
        onChanged();
        return this;
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder setNameBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasNumber() {
        return (this.bitField0_ & 2) == 2;
    }

    public int getNumber() {
        return this.number_;
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder setNumber(int i) {
        this.bitField0_ |= 2;
        this.number_ = i;
        onChanged();
        return this;
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder clearNumber() {
        this.bitField0_ &= -3;
        this.number_ = 0;
        onChanged();
        return this;
    }

    public boolean hasOptions() {
        return (this.bitField0_ & 4) == 4;
    }

    public DescriptorProtos$EnumValueOptions getOptions() {
        if (this.optionsBuilder_ == null) {
            return this.options_ == null ? DescriptorProtos$EnumValueOptions.getDefaultInstance() : this.options_;
        } else {
            return (DescriptorProtos$EnumValueOptions) this.optionsBuilder_.getMessage();
        }
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder setOptions(DescriptorProtos$EnumValueOptions descriptorProtos$EnumValueOptions) {
        if (this.optionsBuilder_ != null) {
            this.optionsBuilder_.setMessage(descriptorProtos$EnumValueOptions);
        } else if (descriptorProtos$EnumValueOptions == null) {
            throw new NullPointerException();
        } else {
            this.options_ = descriptorProtos$EnumValueOptions;
            onChanged();
        }
        this.bitField0_ |= 4;
        return this;
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder setOptions(DescriptorProtos$EnumValueOptions$Builder descriptorProtos$EnumValueOptions$Builder) {
        if (this.optionsBuilder_ == null) {
            this.options_ = descriptorProtos$EnumValueOptions$Builder.build();
            onChanged();
        } else {
            this.optionsBuilder_.setMessage(descriptorProtos$EnumValueOptions$Builder.build());
        }
        this.bitField0_ |= 4;
        return this;
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder mergeOptions(DescriptorProtos$EnumValueOptions descriptorProtos$EnumValueOptions) {
        if (this.optionsBuilder_ == null) {
            if ((this.bitField0_ & 4) != 4 || this.options_ == null || this.options_ == DescriptorProtos$EnumValueOptions.getDefaultInstance()) {
                this.options_ = descriptorProtos$EnumValueOptions;
            } else {
                this.options_ = DescriptorProtos$EnumValueOptions.newBuilder(this.options_).mergeFrom(descriptorProtos$EnumValueOptions).buildPartial();
            }
            onChanged();
        } else {
            this.optionsBuilder_.mergeFrom(descriptorProtos$EnumValueOptions);
        }
        this.bitField0_ |= 4;
        return this;
    }

    public DescriptorProtos$EnumValueDescriptorProto$Builder clearOptions() {
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
            onChanged();
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -5;
        return this;
    }

    public DescriptorProtos$EnumValueOptions$Builder getOptionsBuilder() {
        this.bitField0_ |= 4;
        onChanged();
        return (DescriptorProtos$EnumValueOptions$Builder) getOptionsFieldBuilder().getBuilder();
    }

    public EnumValueOptionsOrBuilder getOptionsOrBuilder() {
        if (this.optionsBuilder_ != null) {
            return (EnumValueOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder();
        }
        return this.options_ == null ? DescriptorProtos$EnumValueOptions.getDefaultInstance() : this.options_;
    }

    private SingleFieldBuilderV3<DescriptorProtos$EnumValueOptions, DescriptorProtos$EnumValueOptions$Builder, EnumValueOptionsOrBuilder> getOptionsFieldBuilder() {
        if (this.optionsBuilder_ == null) {
            this.optionsBuilder_ = new SingleFieldBuilderV3(getOptions(), getParentForChildren(), isClean());
            this.options_ = null;
        }
        return this.optionsBuilder_;
    }

    public final DescriptorProtos$EnumValueDescriptorProto$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$EnumValueDescriptorProto$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$EnumValueDescriptorProto$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$EnumValueDescriptorProto$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
