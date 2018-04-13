package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.UninterpretedOption.NamePart;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;

public final class DescriptorProtos$UninterpretedOption$NamePart$Builder extends Builder<DescriptorProtos$UninterpretedOption$NamePart$Builder> implements DescriptorProtos$UninterpretedOption$NamePartOrBuilder {
    private int bitField0_;
    private boolean isExtension_;
    private Object namePart_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable.ensureFieldAccessorsInitialized(NamePart.class, DescriptorProtos$UninterpretedOption$NamePart$Builder.class);
    }

    private DescriptorProtos$UninterpretedOption$NamePart$Builder() {
        this.namePart_ = "";
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$UninterpretedOption$NamePart$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.namePart_ = "";
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (!GeneratedMessageV3.alwaysUseFieldBuilders) {
        }
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder clear() {
        super.clear();
        this.namePart_ = "";
        this.bitField0_ &= -2;
        this.isExtension_ = false;
        this.bitField0_ &= -3;
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor;
    }

    public NamePart getDefaultInstanceForType() {
        return NamePart.getDefaultInstance();
    }

    public NamePart build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public NamePart buildPartial() {
        int i = 1;
        NamePart namePart = new NamePart(this, null);
        int i2 = this.bitField0_;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        NamePart.access$24202(namePart, this.namePart_);
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        NamePart.access$24302(namePart, this.isExtension_);
        NamePart.access$24402(namePart, i);
        onBuilt();
        return namePart;
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder clone() {
        return (DescriptorProtos$UninterpretedOption$NamePart$Builder) super.clone();
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$UninterpretedOption$NamePart$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$UninterpretedOption$NamePart$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$UninterpretedOption$NamePart$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$UninterpretedOption$NamePart$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$UninterpretedOption$NamePart$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder mergeFrom(Message message) {
        if (message instanceof NamePart) {
            return mergeFrom((NamePart) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder mergeFrom(NamePart namePart) {
        if (namePart != NamePart.getDefaultInstance()) {
            if (namePart.hasNamePart()) {
                this.bitField0_ |= 1;
                this.namePart_ = NamePart.access$24200(namePart);
                onChanged();
            }
            if (namePart.hasIsExtension()) {
                setIsExtension(namePart.getIsExtension());
            }
            mergeUnknownFields(namePart.unknownFields);
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        if (hasNamePart() && hasIsExtension()) {
            return true;
        }
        return false;
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        NamePart namePart;
        Throwable th;
        NamePart namePart2;
        try {
            namePart = (NamePart) NamePart.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (namePart != null) {
                mergeFrom(namePart);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            namePart = (NamePart) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            namePart2 = namePart;
            th = th3;
            if (namePart2 != null) {
                mergeFrom(namePart2);
            }
            throw th;
        }
    }

    public boolean hasNamePart() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getNamePart() {
        Object obj = this.namePart_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.namePart_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getNamePartBytes() {
        Object obj = this.namePart_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.namePart_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder setNamePart(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.namePart_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder clearNamePart() {
        this.bitField0_ &= -2;
        this.namePart_ = NamePart.getDefaultInstance().getNamePart();
        onChanged();
        return this;
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder setNamePartBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.namePart_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasIsExtension() {
        return (this.bitField0_ & 2) == 2;
    }

    public boolean getIsExtension() {
        return this.isExtension_;
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder setIsExtension(boolean z) {
        this.bitField0_ |= 2;
        this.isExtension_ = z;
        onChanged();
        return this;
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder clearIsExtension() {
        this.bitField0_ &= -3;
        this.isExtension_ = false;
        onChanged();
        return this;
    }

    public final DescriptorProtos$UninterpretedOption$NamePart$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$UninterpretedOption$NamePart$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$UninterpretedOption$NamePart$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$UninterpretedOption$NamePart$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
