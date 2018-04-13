package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.DescriptorProto.ReservedRange;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;

public final class DescriptorProtos$DescriptorProto$ReservedRange$Builder extends Builder<DescriptorProtos$DescriptorProto$ReservedRange$Builder> implements DescriptorProtos$DescriptorProto$ReservedRangeOrBuilder {
    private int bitField0_;
    private int end_;
    private int start_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ReservedRange_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ReservedRange_fieldAccessorTable.ensureFieldAccessorsInitialized(ReservedRange.class, DescriptorProtos$DescriptorProto$ReservedRange$Builder.class);
    }

    private DescriptorProtos$DescriptorProto$ReservedRange$Builder() {
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$DescriptorProto$ReservedRange$Builder(BuilderParent builderParent) {
        super(builderParent);
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (!GeneratedMessageV3.alwaysUseFieldBuilders) {
        }
    }

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder clear() {
        super.clear();
        this.start_ = 0;
        this.bitField0_ &= -2;
        this.end_ = 0;
        this.bitField0_ &= -3;
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ReservedRange_descriptor;
    }

    public ReservedRange getDefaultInstanceForType() {
        return ReservedRange.getDefaultInstance();
    }

    public ReservedRange build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public ReservedRange buildPartial() {
        int i = 1;
        ReservedRange reservedRange = new ReservedRange(this, null);
        int i2 = this.bitField0_;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        ReservedRange.access$4302(reservedRange, this.start_);
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        ReservedRange.access$4402(reservedRange, this.end_);
        ReservedRange.access$4502(reservedRange, i);
        onBuilt();
        return reservedRange;
    }

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder clone() {
        return (DescriptorProtos$DescriptorProto$ReservedRange$Builder) super.clone();
    }

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$DescriptorProto$ReservedRange$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$DescriptorProto$ReservedRange$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$DescriptorProto$ReservedRange$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$DescriptorProto$ReservedRange$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$DescriptorProto$ReservedRange$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder mergeFrom(Message message) {
        if (message instanceof ReservedRange) {
            return mergeFrom((ReservedRange) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder mergeFrom(ReservedRange reservedRange) {
        if (reservedRange != ReservedRange.getDefaultInstance()) {
            if (reservedRange.hasStart()) {
                setStart(reservedRange.getStart());
            }
            if (reservedRange.hasEnd()) {
                setEnd(reservedRange.getEnd());
            }
            mergeUnknownFields(reservedRange.unknownFields);
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        return true;
    }

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        ReservedRange reservedRange;
        Throwable th;
        ReservedRange reservedRange2;
        try {
            reservedRange = (ReservedRange) ReservedRange.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (reservedRange != null) {
                mergeFrom(reservedRange);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            reservedRange = (ReservedRange) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            reservedRange2 = reservedRange;
            th = th3;
            if (reservedRange2 != null) {
                mergeFrom(reservedRange2);
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

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder setStart(int i) {
        this.bitField0_ |= 1;
        this.start_ = i;
        onChanged();
        return this;
    }

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder clearStart() {
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

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder setEnd(int i) {
        this.bitField0_ |= 2;
        this.end_ = i;
        onChanged();
        return this;
    }

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder clearEnd() {
        this.bitField0_ &= -3;
        this.end_ = 0;
        onChanged();
        return this;
    }

    public final DescriptorProtos$DescriptorProto$ReservedRange$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$DescriptorProto$ReservedRange$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$DescriptorProto$ReservedRange$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$DescriptorProto$ReservedRange$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
