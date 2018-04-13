package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.UninterpretedOption.NamePart;
import com.google.protobuf.DescriptorProtos.UninterpretedOptionOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$UninterpretedOption$Builder extends Builder<DescriptorProtos$UninterpretedOption$Builder> implements UninterpretedOptionOrBuilder {
    private Object aggregateValue_;
    private int bitField0_;
    private double doubleValue_;
    private Object identifierValue_;
    private RepeatedFieldBuilderV3<NamePart, DescriptorProtos$UninterpretedOption$NamePart$Builder, DescriptorProtos$UninterpretedOption$NamePartOrBuilder> nameBuilder_;
    private List<NamePart> name_;
    private long negativeIntValue_;
    private long positiveIntValue_;
    private ByteString stringValue_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$UninterpretedOption.class, DescriptorProtos$UninterpretedOption$Builder.class);
    }

    private DescriptorProtos$UninterpretedOption$Builder() {
        this.name_ = Collections.emptyList();
        this.identifierValue_ = "";
        this.stringValue_ = ByteString.EMPTY;
        this.aggregateValue_ = "";
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$UninterpretedOption$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.name_ = Collections.emptyList();
        this.identifierValue_ = "";
        this.stringValue_ = ByteString.EMPTY;
        this.aggregateValue_ = "";
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getNameFieldBuilder();
        }
    }

    public DescriptorProtos$UninterpretedOption$Builder clear() {
        super.clear();
        if (this.nameBuilder_ == null) {
            this.name_ = Collections.emptyList();
            this.bitField0_ &= -2;
        } else {
            this.nameBuilder_.clear();
        }
        this.identifierValue_ = "";
        this.bitField0_ &= -3;
        this.positiveIntValue_ = 0;
        this.bitField0_ &= -5;
        this.negativeIntValue_ = 0;
        this.bitField0_ &= -9;
        this.doubleValue_ = 0.0d;
        this.bitField0_ &= -17;
        this.stringValue_ = ByteString.EMPTY;
        this.bitField0_ &= -33;
        this.aggregateValue_ = "";
        this.bitField0_ &= -65;
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor;
    }

    public DescriptorProtos$UninterpretedOption getDefaultInstanceForType() {
        return DescriptorProtos$UninterpretedOption.getDefaultInstance();
    }

    public DescriptorProtos$UninterpretedOption build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$UninterpretedOption buildPartial() {
        int i = 1;
        DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption = new DescriptorProtos$UninterpretedOption(this, null);
        int i2 = this.bitField0_;
        if (this.nameBuilder_ == null) {
            if ((this.bitField0_ & 1) == 1) {
                this.name_ = Collections.unmodifiableList(this.name_);
                this.bitField0_ &= -2;
            }
            DescriptorProtos$UninterpretedOption.access$24902(descriptorProtos$UninterpretedOption, this.name_);
        } else {
            DescriptorProtos$UninterpretedOption.access$24902(descriptorProtos$UninterpretedOption, this.nameBuilder_.build());
        }
        if ((i2 & 2) != 2) {
            i = 0;
        }
        DescriptorProtos$UninterpretedOption.access$25002(descriptorProtos$UninterpretedOption, this.identifierValue_);
        if ((i2 & 4) == 4) {
            i |= 2;
        }
        DescriptorProtos$UninterpretedOption.access$25102(descriptorProtos$UninterpretedOption, this.positiveIntValue_);
        if ((i2 & 8) == 8) {
            i |= 4;
        }
        DescriptorProtos$UninterpretedOption.access$25202(descriptorProtos$UninterpretedOption, this.negativeIntValue_);
        if ((i2 & 16) == 16) {
            i |= 8;
        }
        DescriptorProtos$UninterpretedOption.access$25302(descriptorProtos$UninterpretedOption, this.doubleValue_);
        if ((i2 & 32) == 32) {
            i |= 16;
        }
        DescriptorProtos$UninterpretedOption.access$25402(descriptorProtos$UninterpretedOption, this.stringValue_);
        if ((i2 & 64) == 64) {
            i |= 32;
        }
        DescriptorProtos$UninterpretedOption.access$25502(descriptorProtos$UninterpretedOption, this.aggregateValue_);
        DescriptorProtos$UninterpretedOption.access$25602(descriptorProtos$UninterpretedOption, i);
        onBuilt();
        return descriptorProtos$UninterpretedOption;
    }

    public DescriptorProtos$UninterpretedOption$Builder clone() {
        return (DescriptorProtos$UninterpretedOption$Builder) super.clone();
    }

    public DescriptorProtos$UninterpretedOption$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$UninterpretedOption$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$UninterpretedOption$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$UninterpretedOption$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$UninterpretedOption$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$UninterpretedOption$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$UninterpretedOption$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$UninterpretedOption$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$UninterpretedOption$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$UninterpretedOption$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$UninterpretedOption$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$UninterpretedOption) {
            return mergeFrom((DescriptorProtos$UninterpretedOption) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder mergeFrom(DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (descriptorProtos$UninterpretedOption != DescriptorProtos$UninterpretedOption.getDefaultInstance()) {
            if (this.nameBuilder_ == null) {
                if (!DescriptorProtos$UninterpretedOption.access$24900(descriptorProtos$UninterpretedOption).isEmpty()) {
                    if (this.name_.isEmpty()) {
                        this.name_ = DescriptorProtos$UninterpretedOption.access$24900(descriptorProtos$UninterpretedOption);
                        this.bitField0_ &= -2;
                    } else {
                        ensureNameIsMutable();
                        this.name_.addAll(DescriptorProtos$UninterpretedOption.access$24900(descriptorProtos$UninterpretedOption));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$UninterpretedOption.access$24900(descriptorProtos$UninterpretedOption).isEmpty()) {
                if (this.nameBuilder_.isEmpty()) {
                    this.nameBuilder_.dispose();
                    this.nameBuilder_ = null;
                    this.name_ = DescriptorProtos$UninterpretedOption.access$24900(descriptorProtos$UninterpretedOption);
                    this.bitField0_ &= -2;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getNameFieldBuilder();
                    }
                    this.nameBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.nameBuilder_.addAllMessages(DescriptorProtos$UninterpretedOption.access$24900(descriptorProtos$UninterpretedOption));
                }
            }
            if (descriptorProtos$UninterpretedOption.hasIdentifierValue()) {
                this.bitField0_ |= 2;
                this.identifierValue_ = DescriptorProtos$UninterpretedOption.access$25000(descriptorProtos$UninterpretedOption);
                onChanged();
            }
            if (descriptorProtos$UninterpretedOption.hasPositiveIntValue()) {
                setPositiveIntValue(descriptorProtos$UninterpretedOption.getPositiveIntValue());
            }
            if (descriptorProtos$UninterpretedOption.hasNegativeIntValue()) {
                setNegativeIntValue(descriptorProtos$UninterpretedOption.getNegativeIntValue());
            }
            if (descriptorProtos$UninterpretedOption.hasDoubleValue()) {
                setDoubleValue(descriptorProtos$UninterpretedOption.getDoubleValue());
            }
            if (descriptorProtos$UninterpretedOption.hasStringValue()) {
                setStringValue(descriptorProtos$UninterpretedOption.getStringValue());
            }
            if (descriptorProtos$UninterpretedOption.hasAggregateValue()) {
                this.bitField0_ |= 64;
                this.aggregateValue_ = DescriptorProtos$UninterpretedOption.access$25500(descriptorProtos$UninterpretedOption);
                onChanged();
            }
            mergeUnknownFields(descriptorProtos$UninterpretedOption.unknownFields);
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        for (int i = 0; i < getNameCount(); i++) {
            if (!getName(i).isInitialized()) {
                return false;
            }
        }
        return true;
    }

    public DescriptorProtos$UninterpretedOption$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption;
        DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption2;
        try {
            descriptorProtos$UninterpretedOption2 = (DescriptorProtos$UninterpretedOption) DescriptorProtos$UninterpretedOption.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$UninterpretedOption2 != null) {
                mergeFrom(descriptorProtos$UninterpretedOption2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$UninterpretedOption2 = (DescriptorProtos$UninterpretedOption) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$UninterpretedOption = descriptorProtos$UninterpretedOption2;
            th = th3;
            if (descriptorProtos$UninterpretedOption != null) {
                mergeFrom(descriptorProtos$UninterpretedOption);
            }
            throw th;
        }
    }

    private void ensureNameIsMutable() {
        if ((this.bitField0_ & 1) != 1) {
            this.name_ = new ArrayList(this.name_);
            this.bitField0_ |= 1;
        }
    }

    public List<NamePart> getNameList() {
        if (this.nameBuilder_ == null) {
            return Collections.unmodifiableList(this.name_);
        }
        return this.nameBuilder_.getMessageList();
    }

    public int getNameCount() {
        if (this.nameBuilder_ == null) {
            return this.name_.size();
        }
        return this.nameBuilder_.getCount();
    }

    public NamePart getName(int i) {
        if (this.nameBuilder_ == null) {
            return (NamePart) this.name_.get(i);
        }
        return (NamePart) this.nameBuilder_.getMessage(i);
    }

    public DescriptorProtos$UninterpretedOption$Builder setName(int i, NamePart namePart) {
        if (this.nameBuilder_ != null) {
            this.nameBuilder_.setMessage(i, namePart);
        } else if (namePart == null) {
            throw new NullPointerException();
        } else {
            ensureNameIsMutable();
            this.name_.set(i, namePart);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder setName(int i, DescriptorProtos$UninterpretedOption$NamePart$Builder descriptorProtos$UninterpretedOption$NamePart$Builder) {
        if (this.nameBuilder_ == null) {
            ensureNameIsMutable();
            this.name_.set(i, descriptorProtos$UninterpretedOption$NamePart$Builder.build());
            onChanged();
        } else {
            this.nameBuilder_.setMessage(i, descriptorProtos$UninterpretedOption$NamePart$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder addName(NamePart namePart) {
        if (this.nameBuilder_ != null) {
            this.nameBuilder_.addMessage(namePart);
        } else if (namePart == null) {
            throw new NullPointerException();
        } else {
            ensureNameIsMutable();
            this.name_.add(namePart);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder addName(int i, NamePart namePart) {
        if (this.nameBuilder_ != null) {
            this.nameBuilder_.addMessage(i, namePart);
        } else if (namePart == null) {
            throw new NullPointerException();
        } else {
            ensureNameIsMutable();
            this.name_.add(i, namePart);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder addName(DescriptorProtos$UninterpretedOption$NamePart$Builder descriptorProtos$UninterpretedOption$NamePart$Builder) {
        if (this.nameBuilder_ == null) {
            ensureNameIsMutable();
            this.name_.add(descriptorProtos$UninterpretedOption$NamePart$Builder.build());
            onChanged();
        } else {
            this.nameBuilder_.addMessage(descriptorProtos$UninterpretedOption$NamePart$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder addName(int i, DescriptorProtos$UninterpretedOption$NamePart$Builder descriptorProtos$UninterpretedOption$NamePart$Builder) {
        if (this.nameBuilder_ == null) {
            ensureNameIsMutable();
            this.name_.add(i, descriptorProtos$UninterpretedOption$NamePart$Builder.build());
            onChanged();
        } else {
            this.nameBuilder_.addMessage(i, descriptorProtos$UninterpretedOption$NamePart$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder addAllName(Iterable<? extends NamePart> iterable) {
        if (this.nameBuilder_ == null) {
            ensureNameIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.name_);
            onChanged();
        } else {
            this.nameBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder clearName() {
        if (this.nameBuilder_ == null) {
            this.name_ = Collections.emptyList();
            this.bitField0_ &= -2;
            onChanged();
        } else {
            this.nameBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder removeName(int i) {
        if (this.nameBuilder_ == null) {
            ensureNameIsMutable();
            this.name_.remove(i);
            onChanged();
        } else {
            this.nameBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder getNameBuilder(int i) {
        return (DescriptorProtos$UninterpretedOption$NamePart$Builder) getNameFieldBuilder().getBuilder(i);
    }

    public DescriptorProtos$UninterpretedOption$NamePartOrBuilder getNameOrBuilder(int i) {
        if (this.nameBuilder_ == null) {
            return (DescriptorProtos$UninterpretedOption$NamePartOrBuilder) this.name_.get(i);
        }
        return (DescriptorProtos$UninterpretedOption$NamePartOrBuilder) this.nameBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends DescriptorProtos$UninterpretedOption$NamePartOrBuilder> getNameOrBuilderList() {
        if (this.nameBuilder_ != null) {
            return this.nameBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.name_);
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder addNameBuilder() {
        return (DescriptorProtos$UninterpretedOption$NamePart$Builder) getNameFieldBuilder().addBuilder(NamePart.getDefaultInstance());
    }

    public DescriptorProtos$UninterpretedOption$NamePart$Builder addNameBuilder(int i) {
        return (DescriptorProtos$UninterpretedOption$NamePart$Builder) getNameFieldBuilder().addBuilder(i, NamePart.getDefaultInstance());
    }

    public List<DescriptorProtos$UninterpretedOption$NamePart$Builder> getNameBuilderList() {
        return getNameFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<NamePart, DescriptorProtos$UninterpretedOption$NamePart$Builder, DescriptorProtos$UninterpretedOption$NamePartOrBuilder> getNameFieldBuilder() {
        boolean z = true;
        if (this.nameBuilder_ == null) {
            List list = this.name_;
            if ((this.bitField0_ & 1) != 1) {
                z = false;
            }
            this.nameBuilder_ = new RepeatedFieldBuilderV3(list, z, getParentForChildren(), isClean());
            this.name_ = null;
        }
        return this.nameBuilder_;
    }

    public boolean hasIdentifierValue() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getIdentifierValue() {
        Object obj = this.identifierValue_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.identifierValue_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getIdentifierValueBytes() {
        Object obj = this.identifierValue_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.identifierValue_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public DescriptorProtos$UninterpretedOption$Builder setIdentifierValue(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 2;
        this.identifierValue_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder clearIdentifierValue() {
        this.bitField0_ &= -3;
        this.identifierValue_ = DescriptorProtos$UninterpretedOption.getDefaultInstance().getIdentifierValue();
        onChanged();
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder setIdentifierValueBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 2;
        this.identifierValue_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasPositiveIntValue() {
        return (this.bitField0_ & 4) == 4;
    }

    public long getPositiveIntValue() {
        return this.positiveIntValue_;
    }

    public DescriptorProtos$UninterpretedOption$Builder setPositiveIntValue(long j) {
        this.bitField0_ |= 4;
        this.positiveIntValue_ = j;
        onChanged();
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder clearPositiveIntValue() {
        this.bitField0_ &= -5;
        this.positiveIntValue_ = 0;
        onChanged();
        return this;
    }

    public boolean hasNegativeIntValue() {
        return (this.bitField0_ & 8) == 8;
    }

    public long getNegativeIntValue() {
        return this.negativeIntValue_;
    }

    public DescriptorProtos$UninterpretedOption$Builder setNegativeIntValue(long j) {
        this.bitField0_ |= 8;
        this.negativeIntValue_ = j;
        onChanged();
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder clearNegativeIntValue() {
        this.bitField0_ &= -9;
        this.negativeIntValue_ = 0;
        onChanged();
        return this;
    }

    public boolean hasDoubleValue() {
        return (this.bitField0_ & 16) == 16;
    }

    public double getDoubleValue() {
        return this.doubleValue_;
    }

    public DescriptorProtos$UninterpretedOption$Builder setDoubleValue(double d) {
        this.bitField0_ |= 16;
        this.doubleValue_ = d;
        onChanged();
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder clearDoubleValue() {
        this.bitField0_ &= -17;
        this.doubleValue_ = 0.0d;
        onChanged();
        return this;
    }

    public boolean hasStringValue() {
        return (this.bitField0_ & 32) == 32;
    }

    public ByteString getStringValue() {
        return this.stringValue_;
    }

    public DescriptorProtos$UninterpretedOption$Builder setStringValue(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 32;
        this.stringValue_ = byteString;
        onChanged();
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder clearStringValue() {
        this.bitField0_ &= -33;
        this.stringValue_ = DescriptorProtos$UninterpretedOption.getDefaultInstance().getStringValue();
        onChanged();
        return this;
    }

    public boolean hasAggregateValue() {
        return (this.bitField0_ & 64) == 64;
    }

    public String getAggregateValue() {
        Object obj = this.aggregateValue_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.aggregateValue_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getAggregateValueBytes() {
        Object obj = this.aggregateValue_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.aggregateValue_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public DescriptorProtos$UninterpretedOption$Builder setAggregateValue(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 64;
        this.aggregateValue_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder clearAggregateValue() {
        this.bitField0_ &= -65;
        this.aggregateValue_ = DescriptorProtos$UninterpretedOption.getDefaultInstance().getAggregateValue();
        onChanged();
        return this;
    }

    public DescriptorProtos$UninterpretedOption$Builder setAggregateValueBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 64;
        this.aggregateValue_ = byteString;
        onChanged();
        return this;
    }

    public final DescriptorProtos$UninterpretedOption$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$UninterpretedOption$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$UninterpretedOption$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$UninterpretedOption$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
