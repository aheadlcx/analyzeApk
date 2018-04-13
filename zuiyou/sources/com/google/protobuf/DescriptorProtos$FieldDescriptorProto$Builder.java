package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Label;
import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type;
import com.google.protobuf.DescriptorProtos.FieldDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.FieldOptionsOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;

public final class DescriptorProtos$FieldDescriptorProto$Builder extends Builder<DescriptorProtos$FieldDescriptorProto$Builder> implements FieldDescriptorProtoOrBuilder {
    private int bitField0_;
    private Object defaultValue_;
    private Object extendee_;
    private Object jsonName_;
    private int label_;
    private Object name_;
    private int number_;
    private int oneofIndex_;
    private SingleFieldBuilderV3<DescriptorProtos$FieldOptions, DescriptorProtos$FieldOptions$Builder, FieldOptionsOrBuilder> optionsBuilder_;
    private DescriptorProtos$FieldOptions options_;
    private Object typeName_;
    private int type_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$FieldDescriptorProto.class, DescriptorProtos$FieldDescriptorProto$Builder.class);
    }

    private DescriptorProtos$FieldDescriptorProto$Builder() {
        this.name_ = "";
        this.label_ = 1;
        this.type_ = 1;
        this.typeName_ = "";
        this.extendee_ = "";
        this.defaultValue_ = "";
        this.jsonName_ = "";
        this.options_ = null;
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$FieldDescriptorProto$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.name_ = "";
        this.label_ = 1;
        this.type_ = 1;
        this.typeName_ = "";
        this.extendee_ = "";
        this.defaultValue_ = "";
        this.jsonName_ = "";
        this.options_ = null;
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getOptionsFieldBuilder();
        }
    }

    public DescriptorProtos$FieldDescriptorProto$Builder clear() {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        this.number_ = 0;
        this.bitField0_ &= -3;
        this.label_ = 1;
        this.bitField0_ &= -5;
        this.type_ = 1;
        this.bitField0_ &= -9;
        this.typeName_ = "";
        this.bitField0_ &= -17;
        this.extendee_ = "";
        this.bitField0_ &= -33;
        this.defaultValue_ = "";
        this.bitField0_ &= -65;
        this.oneofIndex_ = 0;
        this.bitField0_ &= -129;
        this.jsonName_ = "";
        this.bitField0_ &= -257;
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -513;
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_descriptor;
    }

    public DescriptorProtos$FieldDescriptorProto getDefaultInstanceForType() {
        return DescriptorProtos$FieldDescriptorProto.getDefaultInstance();
    }

    public DescriptorProtos$FieldDescriptorProto build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$FieldDescriptorProto buildPartial() {
        int i;
        int i2 = 1;
        DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto = new DescriptorProtos$FieldDescriptorProto(this, null);
        int i3 = this.bitField0_;
        if ((i3 & 1) != 1) {
            i2 = 0;
        }
        DescriptorProtos$FieldDescriptorProto.access$7402(descriptorProtos$FieldDescriptorProto, this.name_);
        if ((i3 & 2) == 2) {
            i2 |= 2;
        }
        DescriptorProtos$FieldDescriptorProto.access$7502(descriptorProtos$FieldDescriptorProto, this.number_);
        if ((i3 & 4) == 4) {
            i2 |= 4;
        }
        DescriptorProtos$FieldDescriptorProto.access$7602(descriptorProtos$FieldDescriptorProto, this.label_);
        if ((i3 & 8) == 8) {
            i2 |= 8;
        }
        DescriptorProtos$FieldDescriptorProto.access$7702(descriptorProtos$FieldDescriptorProto, this.type_);
        if ((i3 & 16) == 16) {
            i2 |= 16;
        }
        DescriptorProtos$FieldDescriptorProto.access$7802(descriptorProtos$FieldDescriptorProto, this.typeName_);
        if ((i3 & 32) == 32) {
            i2 |= 32;
        }
        DescriptorProtos$FieldDescriptorProto.access$7902(descriptorProtos$FieldDescriptorProto, this.extendee_);
        if ((i3 & 64) == 64) {
            i2 |= 64;
        }
        DescriptorProtos$FieldDescriptorProto.access$8002(descriptorProtos$FieldDescriptorProto, this.defaultValue_);
        if ((i3 & 128) == 128) {
            i2 |= 128;
        }
        DescriptorProtos$FieldDescriptorProto.access$8102(descriptorProtos$FieldDescriptorProto, this.oneofIndex_);
        if ((i3 & 256) == 256) {
            i2 |= 256;
        }
        DescriptorProtos$FieldDescriptorProto.access$8202(descriptorProtos$FieldDescriptorProto, this.jsonName_);
        if ((i3 & 512) == 512) {
            i = i2 | 512;
        } else {
            i = i2;
        }
        if (this.optionsBuilder_ == null) {
            DescriptorProtos$FieldDescriptorProto.access$8302(descriptorProtos$FieldDescriptorProto, this.options_);
        } else {
            DescriptorProtos$FieldDescriptorProto.access$8302(descriptorProtos$FieldDescriptorProto, (DescriptorProtos$FieldOptions) this.optionsBuilder_.build());
        }
        DescriptorProtos$FieldDescriptorProto.access$8402(descriptorProtos$FieldDescriptorProto, i);
        onBuilt();
        return descriptorProtos$FieldDescriptorProto;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder clone() {
        return (DescriptorProtos$FieldDescriptorProto$Builder) super.clone();
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$FieldDescriptorProto$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$FieldDescriptorProto$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$FieldDescriptorProto$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$FieldDescriptorProto$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$FieldDescriptorProto$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$FieldDescriptorProto$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$FieldDescriptorProto$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$FieldDescriptorProto$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$FieldDescriptorProto$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$FieldDescriptorProto) {
            return mergeFrom((DescriptorProtos$FieldDescriptorProto) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder mergeFrom(DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto) {
        if (descriptorProtos$FieldDescriptorProto != DescriptorProtos$FieldDescriptorProto.getDefaultInstance()) {
            if (descriptorProtos$FieldDescriptorProto.hasName()) {
                this.bitField0_ |= 1;
                this.name_ = DescriptorProtos$FieldDescriptorProto.access$7400(descriptorProtos$FieldDescriptorProto);
                onChanged();
            }
            if (descriptorProtos$FieldDescriptorProto.hasNumber()) {
                setNumber(descriptorProtos$FieldDescriptorProto.getNumber());
            }
            if (descriptorProtos$FieldDescriptorProto.hasLabel()) {
                setLabel(descriptorProtos$FieldDescriptorProto.getLabel());
            }
            if (descriptorProtos$FieldDescriptorProto.hasType()) {
                setType(descriptorProtos$FieldDescriptorProto.getType());
            }
            if (descriptorProtos$FieldDescriptorProto.hasTypeName()) {
                this.bitField0_ |= 16;
                this.typeName_ = DescriptorProtos$FieldDescriptorProto.access$7800(descriptorProtos$FieldDescriptorProto);
                onChanged();
            }
            if (descriptorProtos$FieldDescriptorProto.hasExtendee()) {
                this.bitField0_ |= 32;
                this.extendee_ = DescriptorProtos$FieldDescriptorProto.access$7900(descriptorProtos$FieldDescriptorProto);
                onChanged();
            }
            if (descriptorProtos$FieldDescriptorProto.hasDefaultValue()) {
                this.bitField0_ |= 64;
                this.defaultValue_ = DescriptorProtos$FieldDescriptorProto.access$8000(descriptorProtos$FieldDescriptorProto);
                onChanged();
            }
            if (descriptorProtos$FieldDescriptorProto.hasOneofIndex()) {
                setOneofIndex(descriptorProtos$FieldDescriptorProto.getOneofIndex());
            }
            if (descriptorProtos$FieldDescriptorProto.hasJsonName()) {
                this.bitField0_ |= 256;
                this.jsonName_ = DescriptorProtos$FieldDescriptorProto.access$8200(descriptorProtos$FieldDescriptorProto);
                onChanged();
            }
            if (descriptorProtos$FieldDescriptorProto.hasOptions()) {
                mergeOptions(descriptorProtos$FieldDescriptorProto.getOptions());
            }
            mergeUnknownFields(descriptorProtos$FieldDescriptorProto.unknownFields);
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

    public DescriptorProtos$FieldDescriptorProto$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto;
        Throwable th;
        DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto2;
        try {
            descriptorProtos$FieldDescriptorProto = (DescriptorProtos$FieldDescriptorProto) DescriptorProtos$FieldDescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$FieldDescriptorProto != null) {
                mergeFrom(descriptorProtos$FieldDescriptorProto);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$FieldDescriptorProto = (DescriptorProtos$FieldDescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$FieldDescriptorProto2 = descriptorProtos$FieldDescriptorProto;
            th = th3;
            if (descriptorProtos$FieldDescriptorProto2 != null) {
                mergeFrom(descriptorProtos$FieldDescriptorProto2);
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

    public DescriptorProtos$FieldDescriptorProto$Builder setName(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder clearName() {
        this.bitField0_ &= -2;
        this.name_ = DescriptorProtos$FieldDescriptorProto.getDefaultInstance().getName();
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setNameBytes(ByteString byteString) {
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

    public DescriptorProtos$FieldDescriptorProto$Builder setNumber(int i) {
        this.bitField0_ |= 2;
        this.number_ = i;
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder clearNumber() {
        this.bitField0_ &= -3;
        this.number_ = 0;
        onChanged();
        return this;
    }

    public boolean hasLabel() {
        return (this.bitField0_ & 4) == 4;
    }

    public Label getLabel() {
        Label valueOf = Label.valueOf(this.label_);
        return valueOf == null ? Label.LABEL_OPTIONAL : valueOf;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setLabel(Label label) {
        if (label == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 4;
        this.label_ = label.getNumber();
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder clearLabel() {
        this.bitField0_ &= -5;
        this.label_ = 1;
        onChanged();
        return this;
    }

    public boolean hasType() {
        return (this.bitField0_ & 8) == 8;
    }

    public Type getType() {
        Type valueOf = Type.valueOf(this.type_);
        return valueOf == null ? Type.TYPE_DOUBLE : valueOf;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setType(Type type) {
        if (type == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 8;
        this.type_ = type.getNumber();
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder clearType() {
        this.bitField0_ &= -9;
        this.type_ = 1;
        onChanged();
        return this;
    }

    public boolean hasTypeName() {
        return (this.bitField0_ & 16) == 16;
    }

    public String getTypeName() {
        Object obj = this.typeName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.typeName_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getTypeNameBytes() {
        Object obj = this.typeName_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.typeName_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setTypeName(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 16;
        this.typeName_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder clearTypeName() {
        this.bitField0_ &= -17;
        this.typeName_ = DescriptorProtos$FieldDescriptorProto.getDefaultInstance().getTypeName();
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setTypeNameBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 16;
        this.typeName_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasExtendee() {
        return (this.bitField0_ & 32) == 32;
    }

    public String getExtendee() {
        Object obj = this.extendee_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.extendee_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getExtendeeBytes() {
        Object obj = this.extendee_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.extendee_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setExtendee(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 32;
        this.extendee_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder clearExtendee() {
        this.bitField0_ &= -33;
        this.extendee_ = DescriptorProtos$FieldDescriptorProto.getDefaultInstance().getExtendee();
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setExtendeeBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 32;
        this.extendee_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasDefaultValue() {
        return (this.bitField0_ & 64) == 64;
    }

    public String getDefaultValue() {
        Object obj = this.defaultValue_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.defaultValue_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getDefaultValueBytes() {
        Object obj = this.defaultValue_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.defaultValue_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setDefaultValue(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 64;
        this.defaultValue_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder clearDefaultValue() {
        this.bitField0_ &= -65;
        this.defaultValue_ = DescriptorProtos$FieldDescriptorProto.getDefaultInstance().getDefaultValue();
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setDefaultValueBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 64;
        this.defaultValue_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasOneofIndex() {
        return (this.bitField0_ & 128) == 128;
    }

    public int getOneofIndex() {
        return this.oneofIndex_;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setOneofIndex(int i) {
        this.bitField0_ |= 128;
        this.oneofIndex_ = i;
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder clearOneofIndex() {
        this.bitField0_ &= -129;
        this.oneofIndex_ = 0;
        onChanged();
        return this;
    }

    public boolean hasJsonName() {
        return (this.bitField0_ & 256) == 256;
    }

    public String getJsonName() {
        Object obj = this.jsonName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.jsonName_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getJsonNameBytes() {
        Object obj = this.jsonName_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.jsonName_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setJsonName(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 256;
        this.jsonName_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder clearJsonName() {
        this.bitField0_ &= -257;
        this.jsonName_ = DescriptorProtos$FieldDescriptorProto.getDefaultInstance().getJsonName();
        onChanged();
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setJsonNameBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 256;
        this.jsonName_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasOptions() {
        return (this.bitField0_ & 512) == 512;
    }

    public DescriptorProtos$FieldOptions getOptions() {
        if (this.optionsBuilder_ == null) {
            return this.options_ == null ? DescriptorProtos$FieldOptions.getDefaultInstance() : this.options_;
        } else {
            return (DescriptorProtos$FieldOptions) this.optionsBuilder_.getMessage();
        }
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setOptions(DescriptorProtos$FieldOptions descriptorProtos$FieldOptions) {
        if (this.optionsBuilder_ != null) {
            this.optionsBuilder_.setMessage(descriptorProtos$FieldOptions);
        } else if (descriptorProtos$FieldOptions == null) {
            throw new NullPointerException();
        } else {
            this.options_ = descriptorProtos$FieldOptions;
            onChanged();
        }
        this.bitField0_ |= 512;
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder setOptions(DescriptorProtos$FieldOptions$Builder descriptorProtos$FieldOptions$Builder) {
        if (this.optionsBuilder_ == null) {
            this.options_ = descriptorProtos$FieldOptions$Builder.build();
            onChanged();
        } else {
            this.optionsBuilder_.setMessage(descriptorProtos$FieldOptions$Builder.build());
        }
        this.bitField0_ |= 512;
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder mergeOptions(DescriptorProtos$FieldOptions descriptorProtos$FieldOptions) {
        if (this.optionsBuilder_ == null) {
            if ((this.bitField0_ & 512) != 512 || this.options_ == null || this.options_ == DescriptorProtos$FieldOptions.getDefaultInstance()) {
                this.options_ = descriptorProtos$FieldOptions;
            } else {
                this.options_ = DescriptorProtos$FieldOptions.newBuilder(this.options_).mergeFrom(descriptorProtos$FieldOptions).buildPartial();
            }
            onChanged();
        } else {
            this.optionsBuilder_.mergeFrom(descriptorProtos$FieldOptions);
        }
        this.bitField0_ |= 512;
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder clearOptions() {
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
            onChanged();
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -513;
        return this;
    }

    public DescriptorProtos$FieldOptions$Builder getOptionsBuilder() {
        this.bitField0_ |= 512;
        onChanged();
        return (DescriptorProtos$FieldOptions$Builder) getOptionsFieldBuilder().getBuilder();
    }

    public FieldOptionsOrBuilder getOptionsOrBuilder() {
        if (this.optionsBuilder_ != null) {
            return (FieldOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder();
        }
        return this.options_ == null ? DescriptorProtos$FieldOptions.getDefaultInstance() : this.options_;
    }

    private SingleFieldBuilderV3<DescriptorProtos$FieldOptions, DescriptorProtos$FieldOptions$Builder, FieldOptionsOrBuilder> getOptionsFieldBuilder() {
        if (this.optionsBuilder_ == null) {
            this.optionsBuilder_ = new SingleFieldBuilderV3(getOptions(), getParentForChildren(), isClean());
            this.options_ = null;
        }
        return this.optionsBuilder_;
    }

    public final DescriptorProtos$FieldDescriptorProto$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$FieldDescriptorProto$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$FieldDescriptorProto$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$FieldDescriptorProto$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
