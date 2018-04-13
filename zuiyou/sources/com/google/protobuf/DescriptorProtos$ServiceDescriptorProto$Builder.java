package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.MethodDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.ServiceDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.ServiceOptionsOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$ServiceDescriptorProto$Builder extends Builder<DescriptorProtos$ServiceDescriptorProto$Builder> implements ServiceDescriptorProtoOrBuilder {
    private int bitField0_;
    private RepeatedFieldBuilderV3<DescriptorProtos$MethodDescriptorProto, DescriptorProtos$MethodDescriptorProto$Builder, MethodDescriptorProtoOrBuilder> methodBuilder_;
    private List<DescriptorProtos$MethodDescriptorProto> method_;
    private Object name_;
    private SingleFieldBuilderV3<DescriptorProtos$ServiceOptions, DescriptorProtos$ServiceOptions$Builder, ServiceOptionsOrBuilder> optionsBuilder_;
    private DescriptorProtos$ServiceOptions options_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$ServiceDescriptorProto.class, DescriptorProtos$ServiceDescriptorProto$Builder.class);
    }

    private DescriptorProtos$ServiceDescriptorProto$Builder() {
        this.name_ = "";
        this.method_ = Collections.emptyList();
        this.options_ = null;
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$ServiceDescriptorProto$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.name_ = "";
        this.method_ = Collections.emptyList();
        this.options_ = null;
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getMethodFieldBuilder();
            getOptionsFieldBuilder();
        }
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder clear() {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        if (this.methodBuilder_ == null) {
            this.method_ = Collections.emptyList();
            this.bitField0_ &= -3;
        } else {
            this.methodBuilder_.clear();
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
        return DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_descriptor;
    }

    public DescriptorProtos$ServiceDescriptorProto getDefaultInstanceForType() {
        return DescriptorProtos$ServiceDescriptorProto.getDefaultInstance();
    }

    public DescriptorProtos$ServiceDescriptorProto build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$ServiceDescriptorProto buildPartial() {
        int i;
        int i2 = 1;
        DescriptorProtos$ServiceDescriptorProto descriptorProtos$ServiceDescriptorProto = new DescriptorProtos$ServiceDescriptorProto(this, null);
        int i3 = this.bitField0_;
        if ((i3 & 1) != 1) {
            i2 = 0;
        }
        DescriptorProtos$ServiceDescriptorProto.access$12002(descriptorProtos$ServiceDescriptorProto, this.name_);
        if (this.methodBuilder_ == null) {
            if ((this.bitField0_ & 2) == 2) {
                this.method_ = Collections.unmodifiableList(this.method_);
                this.bitField0_ &= -3;
            }
            DescriptorProtos$ServiceDescriptorProto.access$12102(descriptorProtos$ServiceDescriptorProto, this.method_);
        } else {
            DescriptorProtos$ServiceDescriptorProto.access$12102(descriptorProtos$ServiceDescriptorProto, this.methodBuilder_.build());
        }
        if ((i3 & 4) == 4) {
            i = i2 | 2;
        } else {
            i = i2;
        }
        if (this.optionsBuilder_ == null) {
            DescriptorProtos$ServiceDescriptorProto.access$12202(descriptorProtos$ServiceDescriptorProto, this.options_);
        } else {
            DescriptorProtos$ServiceDescriptorProto.access$12202(descriptorProtos$ServiceDescriptorProto, (DescriptorProtos$ServiceOptions) this.optionsBuilder_.build());
        }
        DescriptorProtos$ServiceDescriptorProto.access$12302(descriptorProtos$ServiceDescriptorProto, i);
        onBuilt();
        return descriptorProtos$ServiceDescriptorProto;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder clone() {
        return (DescriptorProtos$ServiceDescriptorProto$Builder) super.clone();
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$ServiceDescriptorProto$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$ServiceDescriptorProto$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$ServiceDescriptorProto$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$ServiceDescriptorProto$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$ServiceDescriptorProto$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$ServiceDescriptorProto) {
            return mergeFrom((DescriptorProtos$ServiceDescriptorProto) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder mergeFrom(DescriptorProtos$ServiceDescriptorProto descriptorProtos$ServiceDescriptorProto) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (descriptorProtos$ServiceDescriptorProto != DescriptorProtos$ServiceDescriptorProto.getDefaultInstance()) {
            if (descriptorProtos$ServiceDescriptorProto.hasName()) {
                this.bitField0_ |= 1;
                this.name_ = DescriptorProtos$ServiceDescriptorProto.access$12000(descriptorProtos$ServiceDescriptorProto);
                onChanged();
            }
            if (this.methodBuilder_ == null) {
                if (!DescriptorProtos$ServiceDescriptorProto.access$12100(descriptorProtos$ServiceDescriptorProto).isEmpty()) {
                    if (this.method_.isEmpty()) {
                        this.method_ = DescriptorProtos$ServiceDescriptorProto.access$12100(descriptorProtos$ServiceDescriptorProto);
                        this.bitField0_ &= -3;
                    } else {
                        ensureMethodIsMutable();
                        this.method_.addAll(DescriptorProtos$ServiceDescriptorProto.access$12100(descriptorProtos$ServiceDescriptorProto));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$ServiceDescriptorProto.access$12100(descriptorProtos$ServiceDescriptorProto).isEmpty()) {
                if (this.methodBuilder_.isEmpty()) {
                    this.methodBuilder_.dispose();
                    this.methodBuilder_ = null;
                    this.method_ = DescriptorProtos$ServiceDescriptorProto.access$12100(descriptorProtos$ServiceDescriptorProto);
                    this.bitField0_ &= -3;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getMethodFieldBuilder();
                    }
                    this.methodBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.methodBuilder_.addAllMessages(DescriptorProtos$ServiceDescriptorProto.access$12100(descriptorProtos$ServiceDescriptorProto));
                }
            }
            if (descriptorProtos$ServiceDescriptorProto.hasOptions()) {
                mergeOptions(descriptorProtos$ServiceDescriptorProto.getOptions());
            }
            mergeUnknownFields(descriptorProtos$ServiceDescriptorProto.unknownFields);
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        for (int i = 0; i < getMethodCount(); i++) {
            if (!getMethod(i).isInitialized()) {
                return false;
            }
        }
        if (!hasOptions() || getOptions().isInitialized()) {
            return true;
        }
        return false;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        DescriptorProtos$ServiceDescriptorProto descriptorProtos$ServiceDescriptorProto;
        DescriptorProtos$ServiceDescriptorProto descriptorProtos$ServiceDescriptorProto2;
        try {
            descriptorProtos$ServiceDescriptorProto2 = (DescriptorProtos$ServiceDescriptorProto) DescriptorProtos$ServiceDescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$ServiceDescriptorProto2 != null) {
                mergeFrom(descriptorProtos$ServiceDescriptorProto2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$ServiceDescriptorProto2 = (DescriptorProtos$ServiceDescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$ServiceDescriptorProto = descriptorProtos$ServiceDescriptorProto2;
            th = th3;
            if (descriptorProtos$ServiceDescriptorProto != null) {
                mergeFrom(descriptorProtos$ServiceDescriptorProto);
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

    public DescriptorProtos$ServiceDescriptorProto$Builder setName(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder clearName() {
        this.bitField0_ &= -2;
        this.name_ = DescriptorProtos$ServiceDescriptorProto.getDefaultInstance().getName();
        onChanged();
        return this;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder setNameBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = byteString;
        onChanged();
        return this;
    }

    private void ensureMethodIsMutable() {
        if ((this.bitField0_ & 2) != 2) {
            this.method_ = new ArrayList(this.method_);
            this.bitField0_ |= 2;
        }
    }

    public List<DescriptorProtos$MethodDescriptorProto> getMethodList() {
        if (this.methodBuilder_ == null) {
            return Collections.unmodifiableList(this.method_);
        }
        return this.methodBuilder_.getMessageList();
    }

    public int getMethodCount() {
        if (this.methodBuilder_ == null) {
            return this.method_.size();
        }
        return this.methodBuilder_.getCount();
    }

    public DescriptorProtos$MethodDescriptorProto getMethod(int i) {
        if (this.methodBuilder_ == null) {
            return (DescriptorProtos$MethodDescriptorProto) this.method_.get(i);
        }
        return (DescriptorProtos$MethodDescriptorProto) this.methodBuilder_.getMessage(i);
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder setMethod(int i, DescriptorProtos$MethodDescriptorProto descriptorProtos$MethodDescriptorProto) {
        if (this.methodBuilder_ != null) {
            this.methodBuilder_.setMessage(i, descriptorProtos$MethodDescriptorProto);
        } else if (descriptorProtos$MethodDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureMethodIsMutable();
            this.method_.set(i, descriptorProtos$MethodDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder setMethod(int i, DescriptorProtos$MethodDescriptorProto$Builder descriptorProtos$MethodDescriptorProto$Builder) {
        if (this.methodBuilder_ == null) {
            ensureMethodIsMutable();
            this.method_.set(i, descriptorProtos$MethodDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.methodBuilder_.setMessage(i, descriptorProtos$MethodDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder addMethod(DescriptorProtos$MethodDescriptorProto descriptorProtos$MethodDescriptorProto) {
        if (this.methodBuilder_ != null) {
            this.methodBuilder_.addMessage(descriptorProtos$MethodDescriptorProto);
        } else if (descriptorProtos$MethodDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureMethodIsMutable();
            this.method_.add(descriptorProtos$MethodDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder addMethod(int i, DescriptorProtos$MethodDescriptorProto descriptorProtos$MethodDescriptorProto) {
        if (this.methodBuilder_ != null) {
            this.methodBuilder_.addMessage(i, descriptorProtos$MethodDescriptorProto);
        } else if (descriptorProtos$MethodDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureMethodIsMutable();
            this.method_.add(i, descriptorProtos$MethodDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder addMethod(DescriptorProtos$MethodDescriptorProto$Builder descriptorProtos$MethodDescriptorProto$Builder) {
        if (this.methodBuilder_ == null) {
            ensureMethodIsMutable();
            this.method_.add(descriptorProtos$MethodDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.methodBuilder_.addMessage(descriptorProtos$MethodDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder addMethod(int i, DescriptorProtos$MethodDescriptorProto$Builder descriptorProtos$MethodDescriptorProto$Builder) {
        if (this.methodBuilder_ == null) {
            ensureMethodIsMutable();
            this.method_.add(i, descriptorProtos$MethodDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.methodBuilder_.addMessage(i, descriptorProtos$MethodDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder addAllMethod(Iterable<? extends DescriptorProtos$MethodDescriptorProto> iterable) {
        if (this.methodBuilder_ == null) {
            ensureMethodIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.method_);
            onChanged();
        } else {
            this.methodBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder clearMethod() {
        if (this.methodBuilder_ == null) {
            this.method_ = Collections.emptyList();
            this.bitField0_ &= -3;
            onChanged();
        } else {
            this.methodBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder removeMethod(int i) {
        if (this.methodBuilder_ == null) {
            ensureMethodIsMutable();
            this.method_.remove(i);
            onChanged();
        } else {
            this.methodBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$MethodDescriptorProto$Builder getMethodBuilder(int i) {
        return (DescriptorProtos$MethodDescriptorProto$Builder) getMethodFieldBuilder().getBuilder(i);
    }

    public MethodDescriptorProtoOrBuilder getMethodOrBuilder(int i) {
        if (this.methodBuilder_ == null) {
            return (MethodDescriptorProtoOrBuilder) this.method_.get(i);
        }
        return (MethodDescriptorProtoOrBuilder) this.methodBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends MethodDescriptorProtoOrBuilder> getMethodOrBuilderList() {
        if (this.methodBuilder_ != null) {
            return this.methodBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.method_);
    }

    public DescriptorProtos$MethodDescriptorProto$Builder addMethodBuilder() {
        return (DescriptorProtos$MethodDescriptorProto$Builder) getMethodFieldBuilder().addBuilder(DescriptorProtos$MethodDescriptorProto.getDefaultInstance());
    }

    public DescriptorProtos$MethodDescriptorProto$Builder addMethodBuilder(int i) {
        return (DescriptorProtos$MethodDescriptorProto$Builder) getMethodFieldBuilder().addBuilder(i, DescriptorProtos$MethodDescriptorProto.getDefaultInstance());
    }

    public List<DescriptorProtos$MethodDescriptorProto$Builder> getMethodBuilderList() {
        return getMethodFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<DescriptorProtos$MethodDescriptorProto, DescriptorProtos$MethodDescriptorProto$Builder, MethodDescriptorProtoOrBuilder> getMethodFieldBuilder() {
        if (this.methodBuilder_ == null) {
            this.methodBuilder_ = new RepeatedFieldBuilderV3(this.method_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
            this.method_ = null;
        }
        return this.methodBuilder_;
    }

    public boolean hasOptions() {
        return (this.bitField0_ & 4) == 4;
    }

    public DescriptorProtos$ServiceOptions getOptions() {
        if (this.optionsBuilder_ == null) {
            return this.options_ == null ? DescriptorProtos$ServiceOptions.getDefaultInstance() : this.options_;
        } else {
            return (DescriptorProtos$ServiceOptions) this.optionsBuilder_.getMessage();
        }
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder setOptions(DescriptorProtos$ServiceOptions descriptorProtos$ServiceOptions) {
        if (this.optionsBuilder_ != null) {
            this.optionsBuilder_.setMessage(descriptorProtos$ServiceOptions);
        } else if (descriptorProtos$ServiceOptions == null) {
            throw new NullPointerException();
        } else {
            this.options_ = descriptorProtos$ServiceOptions;
            onChanged();
        }
        this.bitField0_ |= 4;
        return this;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder setOptions(DescriptorProtos$ServiceOptions$Builder descriptorProtos$ServiceOptions$Builder) {
        if (this.optionsBuilder_ == null) {
            this.options_ = descriptorProtos$ServiceOptions$Builder.build();
            onChanged();
        } else {
            this.optionsBuilder_.setMessage(descriptorProtos$ServiceOptions$Builder.build());
        }
        this.bitField0_ |= 4;
        return this;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder mergeOptions(DescriptorProtos$ServiceOptions descriptorProtos$ServiceOptions) {
        if (this.optionsBuilder_ == null) {
            if ((this.bitField0_ & 4) != 4 || this.options_ == null || this.options_ == DescriptorProtos$ServiceOptions.getDefaultInstance()) {
                this.options_ = descriptorProtos$ServiceOptions;
            } else {
                this.options_ = DescriptorProtos$ServiceOptions.newBuilder(this.options_).mergeFrom(descriptorProtos$ServiceOptions).buildPartial();
            }
            onChanged();
        } else {
            this.optionsBuilder_.mergeFrom(descriptorProtos$ServiceOptions);
        }
        this.bitField0_ |= 4;
        return this;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder clearOptions() {
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
            onChanged();
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -5;
        return this;
    }

    public DescriptorProtos$ServiceOptions$Builder getOptionsBuilder() {
        this.bitField0_ |= 4;
        onChanged();
        return (DescriptorProtos$ServiceOptions$Builder) getOptionsFieldBuilder().getBuilder();
    }

    public ServiceOptionsOrBuilder getOptionsOrBuilder() {
        if (this.optionsBuilder_ != null) {
            return (ServiceOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder();
        }
        return this.options_ == null ? DescriptorProtos$ServiceOptions.getDefaultInstance() : this.options_;
    }

    private SingleFieldBuilderV3<DescriptorProtos$ServiceOptions, DescriptorProtos$ServiceOptions$Builder, ServiceOptionsOrBuilder> getOptionsFieldBuilder() {
        if (this.optionsBuilder_ == null) {
            this.optionsBuilder_ = new SingleFieldBuilderV3(getOptions(), getParentForChildren(), isClean());
            this.options_ = null;
        }
        return this.optionsBuilder_;
    }

    public final DescriptorProtos$ServiceDescriptorProto$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$ServiceDescriptorProto$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$ServiceDescriptorProto$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$ServiceDescriptorProto$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
