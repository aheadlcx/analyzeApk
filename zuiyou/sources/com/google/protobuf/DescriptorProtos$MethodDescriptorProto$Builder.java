package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.MethodDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.MethodOptionsOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;

public final class DescriptorProtos$MethodDescriptorProto$Builder extends Builder<DescriptorProtos$MethodDescriptorProto$Builder> implements MethodDescriptorProtoOrBuilder {
    private int bitField0_;
    private boolean clientStreaming_;
    private Object inputType_;
    private Object name_;
    private SingleFieldBuilderV3<DescriptorProtos$MethodOptions, DescriptorProtos$MethodOptions$Builder, MethodOptionsOrBuilder> optionsBuilder_;
    private DescriptorProtos$MethodOptions options_;
    private Object outputType_;
    private boolean serverStreaming_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$MethodDescriptorProto.class, DescriptorProtos$MethodDescriptorProto$Builder.class);
    }

    private DescriptorProtos$MethodDescriptorProto$Builder() {
        this.name_ = "";
        this.inputType_ = "";
        this.outputType_ = "";
        this.options_ = null;
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$MethodDescriptorProto$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.name_ = "";
        this.inputType_ = "";
        this.outputType_ = "";
        this.options_ = null;
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getOptionsFieldBuilder();
        }
    }

    public DescriptorProtos$MethodDescriptorProto$Builder clear() {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        this.inputType_ = "";
        this.bitField0_ &= -3;
        this.outputType_ = "";
        this.bitField0_ &= -5;
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -9;
        this.clientStreaming_ = false;
        this.bitField0_ &= -17;
        this.serverStreaming_ = false;
        this.bitField0_ &= -33;
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_descriptor;
    }

    public DescriptorProtos$MethodDescriptorProto getDefaultInstanceForType() {
        return DescriptorProtos$MethodDescriptorProto.getDefaultInstance();
    }

    public DescriptorProtos$MethodDescriptorProto build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$MethodDescriptorProto buildPartial() {
        int i;
        int i2 = 1;
        DescriptorProtos$MethodDescriptorProto descriptorProtos$MethodDescriptorProto = new DescriptorProtos$MethodDescriptorProto(this, null);
        int i3 = this.bitField0_;
        if ((i3 & 1) != 1) {
            i2 = 0;
        }
        DescriptorProtos$MethodDescriptorProto.access$13002(descriptorProtos$MethodDescriptorProto, this.name_);
        if ((i3 & 2) == 2) {
            i2 |= 2;
        }
        DescriptorProtos$MethodDescriptorProto.access$13102(descriptorProtos$MethodDescriptorProto, this.inputType_);
        if ((i3 & 4) == 4) {
            i2 |= 4;
        }
        DescriptorProtos$MethodDescriptorProto.access$13202(descriptorProtos$MethodDescriptorProto, this.outputType_);
        if ((i3 & 8) == 8) {
            i = i2 | 8;
        } else {
            i = i2;
        }
        if (this.optionsBuilder_ == null) {
            DescriptorProtos$MethodDescriptorProto.access$13302(descriptorProtos$MethodDescriptorProto, this.options_);
        } else {
            DescriptorProtos$MethodDescriptorProto.access$13302(descriptorProtos$MethodDescriptorProto, (DescriptorProtos$MethodOptions) this.optionsBuilder_.build());
        }
        if ((i3 & 16) == 16) {
            i |= 16;
        }
        DescriptorProtos$MethodDescriptorProto.access$13402(descriptorProtos$MethodDescriptorProto, this.clientStreaming_);
        if ((i3 & 32) == 32) {
            i |= 32;
        }
        DescriptorProtos$MethodDescriptorProto.access$13502(descriptorProtos$MethodDescriptorProto, this.serverStreaming_);
        DescriptorProtos$MethodDescriptorProto.access$13602(descriptorProtos$MethodDescriptorProto, i);
        onBuilt();
        return descriptorProtos$MethodDescriptorProto;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder clone() {
        return (DescriptorProtos$MethodDescriptorProto$Builder) super.clone();
    }

    public DescriptorProtos$MethodDescriptorProto$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$MethodDescriptorProto$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$MethodDescriptorProto$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$MethodDescriptorProto$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$MethodDescriptorProto$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$MethodDescriptorProto$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$MethodDescriptorProto$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$MethodDescriptorProto$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$MethodDescriptorProto$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$MethodDescriptorProto$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$MethodDescriptorProto$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$MethodDescriptorProto) {
            return mergeFrom((DescriptorProtos$MethodDescriptorProto) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder mergeFrom(DescriptorProtos$MethodDescriptorProto descriptorProtos$MethodDescriptorProto) {
        if (descriptorProtos$MethodDescriptorProto != DescriptorProtos$MethodDescriptorProto.getDefaultInstance()) {
            if (descriptorProtos$MethodDescriptorProto.hasName()) {
                this.bitField0_ |= 1;
                this.name_ = DescriptorProtos$MethodDescriptorProto.access$13000(descriptorProtos$MethodDescriptorProto);
                onChanged();
            }
            if (descriptorProtos$MethodDescriptorProto.hasInputType()) {
                this.bitField0_ |= 2;
                this.inputType_ = DescriptorProtos$MethodDescriptorProto.access$13100(descriptorProtos$MethodDescriptorProto);
                onChanged();
            }
            if (descriptorProtos$MethodDescriptorProto.hasOutputType()) {
                this.bitField0_ |= 4;
                this.outputType_ = DescriptorProtos$MethodDescriptorProto.access$13200(descriptorProtos$MethodDescriptorProto);
                onChanged();
            }
            if (descriptorProtos$MethodDescriptorProto.hasOptions()) {
                mergeOptions(descriptorProtos$MethodDescriptorProto.getOptions());
            }
            if (descriptorProtos$MethodDescriptorProto.hasClientStreaming()) {
                setClientStreaming(descriptorProtos$MethodDescriptorProto.getClientStreaming());
            }
            if (descriptorProtos$MethodDescriptorProto.hasServerStreaming()) {
                setServerStreaming(descriptorProtos$MethodDescriptorProto.getServerStreaming());
            }
            mergeUnknownFields(descriptorProtos$MethodDescriptorProto.unknownFields);
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

    public DescriptorProtos$MethodDescriptorProto$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        DescriptorProtos$MethodDescriptorProto descriptorProtos$MethodDescriptorProto;
        Throwable th;
        DescriptorProtos$MethodDescriptorProto descriptorProtos$MethodDescriptorProto2;
        try {
            descriptorProtos$MethodDescriptorProto = (DescriptorProtos$MethodDescriptorProto) DescriptorProtos$MethodDescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$MethodDescriptorProto != null) {
                mergeFrom(descriptorProtos$MethodDescriptorProto);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$MethodDescriptorProto = (DescriptorProtos$MethodDescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$MethodDescriptorProto2 = descriptorProtos$MethodDescriptorProto;
            th = th3;
            if (descriptorProtos$MethodDescriptorProto2 != null) {
                mergeFrom(descriptorProtos$MethodDescriptorProto2);
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

    public DescriptorProtos$MethodDescriptorProto$Builder setName(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder clearName() {
        this.bitField0_ &= -2;
        this.name_ = DescriptorProtos$MethodDescriptorProto.getDefaultInstance().getName();
        onChanged();
        return this;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder setNameBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasInputType() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getInputType() {
        Object obj = this.inputType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.inputType_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getInputTypeBytes() {
        Object obj = this.inputType_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.inputType_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder setInputType(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 2;
        this.inputType_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder clearInputType() {
        this.bitField0_ &= -3;
        this.inputType_ = DescriptorProtos$MethodDescriptorProto.getDefaultInstance().getInputType();
        onChanged();
        return this;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder setInputTypeBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 2;
        this.inputType_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasOutputType() {
        return (this.bitField0_ & 4) == 4;
    }

    public String getOutputType() {
        Object obj = this.outputType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.outputType_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getOutputTypeBytes() {
        Object obj = this.outputType_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.outputType_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder setOutputType(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 4;
        this.outputType_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder clearOutputType() {
        this.bitField0_ &= -5;
        this.outputType_ = DescriptorProtos$MethodDescriptorProto.getDefaultInstance().getOutputType();
        onChanged();
        return this;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder setOutputTypeBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 4;
        this.outputType_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasOptions() {
        return (this.bitField0_ & 8) == 8;
    }

    public DescriptorProtos$MethodOptions getOptions() {
        if (this.optionsBuilder_ == null) {
            return this.options_ == null ? DescriptorProtos$MethodOptions.getDefaultInstance() : this.options_;
        } else {
            return (DescriptorProtos$MethodOptions) this.optionsBuilder_.getMessage();
        }
    }

    public DescriptorProtos$MethodDescriptorProto$Builder setOptions(DescriptorProtos$MethodOptions descriptorProtos$MethodOptions) {
        if (this.optionsBuilder_ != null) {
            this.optionsBuilder_.setMessage(descriptorProtos$MethodOptions);
        } else if (descriptorProtos$MethodOptions == null) {
            throw new NullPointerException();
        } else {
            this.options_ = descriptorProtos$MethodOptions;
            onChanged();
        }
        this.bitField0_ |= 8;
        return this;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder setOptions(DescriptorProtos$MethodOptions$Builder descriptorProtos$MethodOptions$Builder) {
        if (this.optionsBuilder_ == null) {
            this.options_ = descriptorProtos$MethodOptions$Builder.build();
            onChanged();
        } else {
            this.optionsBuilder_.setMessage(descriptorProtos$MethodOptions$Builder.build());
        }
        this.bitField0_ |= 8;
        return this;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder mergeOptions(DescriptorProtos$MethodOptions descriptorProtos$MethodOptions) {
        if (this.optionsBuilder_ == null) {
            if ((this.bitField0_ & 8) != 8 || this.options_ == null || this.options_ == DescriptorProtos$MethodOptions.getDefaultInstance()) {
                this.options_ = descriptorProtos$MethodOptions;
            } else {
                this.options_ = DescriptorProtos$MethodOptions.newBuilder(this.options_).mergeFrom(descriptorProtos$MethodOptions).buildPartial();
            }
            onChanged();
        } else {
            this.optionsBuilder_.mergeFrom(descriptorProtos$MethodOptions);
        }
        this.bitField0_ |= 8;
        return this;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder clearOptions() {
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
            onChanged();
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -9;
        return this;
    }

    public DescriptorProtos$MethodOptions$Builder getOptionsBuilder() {
        this.bitField0_ |= 8;
        onChanged();
        return (DescriptorProtos$MethodOptions$Builder) getOptionsFieldBuilder().getBuilder();
    }

    public MethodOptionsOrBuilder getOptionsOrBuilder() {
        if (this.optionsBuilder_ != null) {
            return (MethodOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder();
        }
        return this.options_ == null ? DescriptorProtos$MethodOptions.getDefaultInstance() : this.options_;
    }

    private SingleFieldBuilderV3<DescriptorProtos$MethodOptions, DescriptorProtos$MethodOptions$Builder, MethodOptionsOrBuilder> getOptionsFieldBuilder() {
        if (this.optionsBuilder_ == null) {
            this.optionsBuilder_ = new SingleFieldBuilderV3(getOptions(), getParentForChildren(), isClean());
            this.options_ = null;
        }
        return this.optionsBuilder_;
    }

    public boolean hasClientStreaming() {
        return (this.bitField0_ & 16) == 16;
    }

    public boolean getClientStreaming() {
        return this.clientStreaming_;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder setClientStreaming(boolean z) {
        this.bitField0_ |= 16;
        this.clientStreaming_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder clearClientStreaming() {
        this.bitField0_ &= -17;
        this.clientStreaming_ = false;
        onChanged();
        return this;
    }

    public boolean hasServerStreaming() {
        return (this.bitField0_ & 32) == 32;
    }

    public boolean getServerStreaming() {
        return this.serverStreaming_;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder setServerStreaming(boolean z) {
        this.bitField0_ |= 32;
        this.serverStreaming_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder clearServerStreaming() {
        this.bitField0_ &= -33;
        this.serverStreaming_ = false;
        onChanged();
        return this;
    }

    public final DescriptorProtos$MethodDescriptorProto$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$MethodDescriptorProto$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$MethodDescriptorProto$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$MethodDescriptorProto$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
