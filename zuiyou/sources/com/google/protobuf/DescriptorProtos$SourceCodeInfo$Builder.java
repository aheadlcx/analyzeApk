package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.SourceCodeInfo.Location;
import com.google.protobuf.DescriptorProtos.SourceCodeInfoOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$SourceCodeInfo$Builder extends Builder<DescriptorProtos$SourceCodeInfo$Builder> implements SourceCodeInfoOrBuilder {
    private int bitField0_;
    private RepeatedFieldBuilderV3<Location, DescriptorProtos$SourceCodeInfo$Location$Builder, DescriptorProtos$SourceCodeInfo$LocationOrBuilder> locationBuilder_;
    private List<Location> location_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$SourceCodeInfo.class, DescriptorProtos$SourceCodeInfo$Builder.class);
    }

    private DescriptorProtos$SourceCodeInfo$Builder() {
        this.location_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$SourceCodeInfo$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.location_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getLocationFieldBuilder();
        }
    }

    public DescriptorProtos$SourceCodeInfo$Builder clear() {
        super.clear();
        if (this.locationBuilder_ == null) {
            this.location_ = Collections.emptyList();
            this.bitField0_ &= -2;
        } else {
            this.locationBuilder_.clear();
        }
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor;
    }

    public DescriptorProtos$SourceCodeInfo getDefaultInstanceForType() {
        return DescriptorProtos$SourceCodeInfo.getDefaultInstance();
    }

    public DescriptorProtos$SourceCodeInfo build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$SourceCodeInfo buildPartial() {
        DescriptorProtos$SourceCodeInfo descriptorProtos$SourceCodeInfo = new DescriptorProtos$SourceCodeInfo(this, null);
        int i = this.bitField0_;
        if (this.locationBuilder_ == null) {
            if ((this.bitField0_ & 1) == 1) {
                this.location_ = Collections.unmodifiableList(this.location_);
                this.bitField0_ &= -2;
            }
            DescriptorProtos$SourceCodeInfo.access$27502(descriptorProtos$SourceCodeInfo, this.location_);
        } else {
            DescriptorProtos$SourceCodeInfo.access$27502(descriptorProtos$SourceCodeInfo, this.locationBuilder_.build());
        }
        onBuilt();
        return descriptorProtos$SourceCodeInfo;
    }

    public DescriptorProtos$SourceCodeInfo$Builder clone() {
        return (DescriptorProtos$SourceCodeInfo$Builder) super.clone();
    }

    public DescriptorProtos$SourceCodeInfo$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$SourceCodeInfo$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$SourceCodeInfo$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$SourceCodeInfo$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$SourceCodeInfo$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$SourceCodeInfo$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$SourceCodeInfo$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$SourceCodeInfo$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$SourceCodeInfo$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$SourceCodeInfo$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$SourceCodeInfo$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$SourceCodeInfo) {
            return mergeFrom((DescriptorProtos$SourceCodeInfo) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Builder mergeFrom(DescriptorProtos$SourceCodeInfo descriptorProtos$SourceCodeInfo) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (descriptorProtos$SourceCodeInfo != DescriptorProtos$SourceCodeInfo.getDefaultInstance()) {
            if (this.locationBuilder_ == null) {
                if (!DescriptorProtos$SourceCodeInfo.access$27500(descriptorProtos$SourceCodeInfo).isEmpty()) {
                    if (this.location_.isEmpty()) {
                        this.location_ = DescriptorProtos$SourceCodeInfo.access$27500(descriptorProtos$SourceCodeInfo);
                        this.bitField0_ &= -2;
                    } else {
                        ensureLocationIsMutable();
                        this.location_.addAll(DescriptorProtos$SourceCodeInfo.access$27500(descriptorProtos$SourceCodeInfo));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$SourceCodeInfo.access$27500(descriptorProtos$SourceCodeInfo).isEmpty()) {
                if (this.locationBuilder_.isEmpty()) {
                    this.locationBuilder_.dispose();
                    this.locationBuilder_ = null;
                    this.location_ = DescriptorProtos$SourceCodeInfo.access$27500(descriptorProtos$SourceCodeInfo);
                    this.bitField0_ &= -2;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getLocationFieldBuilder();
                    }
                    this.locationBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.locationBuilder_.addAllMessages(DescriptorProtos$SourceCodeInfo.access$27500(descriptorProtos$SourceCodeInfo));
                }
            }
            mergeUnknownFields(descriptorProtos$SourceCodeInfo.unknownFields);
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        return true;
    }

    public DescriptorProtos$SourceCodeInfo$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        DescriptorProtos$SourceCodeInfo descriptorProtos$SourceCodeInfo;
        DescriptorProtos$SourceCodeInfo descriptorProtos$SourceCodeInfo2;
        try {
            descriptorProtos$SourceCodeInfo2 = (DescriptorProtos$SourceCodeInfo) DescriptorProtos$SourceCodeInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$SourceCodeInfo2 != null) {
                mergeFrom(descriptorProtos$SourceCodeInfo2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$SourceCodeInfo2 = (DescriptorProtos$SourceCodeInfo) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$SourceCodeInfo = descriptorProtos$SourceCodeInfo2;
            th = th3;
            if (descriptorProtos$SourceCodeInfo != null) {
                mergeFrom(descriptorProtos$SourceCodeInfo);
            }
            throw th;
        }
    }

    private void ensureLocationIsMutable() {
        if ((this.bitField0_ & 1) != 1) {
            this.location_ = new ArrayList(this.location_);
            this.bitField0_ |= 1;
        }
    }

    public List<Location> getLocationList() {
        if (this.locationBuilder_ == null) {
            return Collections.unmodifiableList(this.location_);
        }
        return this.locationBuilder_.getMessageList();
    }

    public int getLocationCount() {
        if (this.locationBuilder_ == null) {
            return this.location_.size();
        }
        return this.locationBuilder_.getCount();
    }

    public Location getLocation(int i) {
        if (this.locationBuilder_ == null) {
            return (Location) this.location_.get(i);
        }
        return (Location) this.locationBuilder_.getMessage(i);
    }

    public DescriptorProtos$SourceCodeInfo$Builder setLocation(int i, Location location) {
        if (this.locationBuilder_ != null) {
            this.locationBuilder_.setMessage(i, location);
        } else if (location == null) {
            throw new NullPointerException();
        } else {
            ensureLocationIsMutable();
            this.location_.set(i, location);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Builder setLocation(int i, DescriptorProtos$SourceCodeInfo$Location$Builder descriptorProtos$SourceCodeInfo$Location$Builder) {
        if (this.locationBuilder_ == null) {
            ensureLocationIsMutable();
            this.location_.set(i, descriptorProtos$SourceCodeInfo$Location$Builder.build());
            onChanged();
        } else {
            this.locationBuilder_.setMessage(i, descriptorProtos$SourceCodeInfo$Location$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Builder addLocation(Location location) {
        if (this.locationBuilder_ != null) {
            this.locationBuilder_.addMessage(location);
        } else if (location == null) {
            throw new NullPointerException();
        } else {
            ensureLocationIsMutable();
            this.location_.add(location);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Builder addLocation(int i, Location location) {
        if (this.locationBuilder_ != null) {
            this.locationBuilder_.addMessage(i, location);
        } else if (location == null) {
            throw new NullPointerException();
        } else {
            ensureLocationIsMutable();
            this.location_.add(i, location);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Builder addLocation(DescriptorProtos$SourceCodeInfo$Location$Builder descriptorProtos$SourceCodeInfo$Location$Builder) {
        if (this.locationBuilder_ == null) {
            ensureLocationIsMutable();
            this.location_.add(descriptorProtos$SourceCodeInfo$Location$Builder.build());
            onChanged();
        } else {
            this.locationBuilder_.addMessage(descriptorProtos$SourceCodeInfo$Location$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Builder addLocation(int i, DescriptorProtos$SourceCodeInfo$Location$Builder descriptorProtos$SourceCodeInfo$Location$Builder) {
        if (this.locationBuilder_ == null) {
            ensureLocationIsMutable();
            this.location_.add(i, descriptorProtos$SourceCodeInfo$Location$Builder.build());
            onChanged();
        } else {
            this.locationBuilder_.addMessage(i, descriptorProtos$SourceCodeInfo$Location$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Builder addAllLocation(Iterable<? extends Location> iterable) {
        if (this.locationBuilder_ == null) {
            ensureLocationIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.location_);
            onChanged();
        } else {
            this.locationBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Builder clearLocation() {
        if (this.locationBuilder_ == null) {
            this.location_ = Collections.emptyList();
            this.bitField0_ &= -2;
            onChanged();
        } else {
            this.locationBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Builder removeLocation(int i) {
        if (this.locationBuilder_ == null) {
            ensureLocationIsMutable();
            this.location_.remove(i);
            onChanged();
        } else {
            this.locationBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder getLocationBuilder(int i) {
        return (DescriptorProtos$SourceCodeInfo$Location$Builder) getLocationFieldBuilder().getBuilder(i);
    }

    public DescriptorProtos$SourceCodeInfo$LocationOrBuilder getLocationOrBuilder(int i) {
        if (this.locationBuilder_ == null) {
            return (DescriptorProtos$SourceCodeInfo$LocationOrBuilder) this.location_.get(i);
        }
        return (DescriptorProtos$SourceCodeInfo$LocationOrBuilder) this.locationBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends DescriptorProtos$SourceCodeInfo$LocationOrBuilder> getLocationOrBuilderList() {
        if (this.locationBuilder_ != null) {
            return this.locationBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.location_);
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder addLocationBuilder() {
        return (DescriptorProtos$SourceCodeInfo$Location$Builder) getLocationFieldBuilder().addBuilder(Location.getDefaultInstance());
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder addLocationBuilder(int i) {
        return (DescriptorProtos$SourceCodeInfo$Location$Builder) getLocationFieldBuilder().addBuilder(i, Location.getDefaultInstance());
    }

    public List<DescriptorProtos$SourceCodeInfo$Location$Builder> getLocationBuilderList() {
        return getLocationFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<Location, DescriptorProtos$SourceCodeInfo$Location$Builder, DescriptorProtos$SourceCodeInfo$LocationOrBuilder> getLocationFieldBuilder() {
        boolean z = true;
        if (this.locationBuilder_ == null) {
            List list = this.location_;
            if ((this.bitField0_ & 1) != 1) {
                z = false;
            }
            this.locationBuilder_ = new RepeatedFieldBuilderV3(list, z, getParentForChildren(), isClean());
            this.location_ = null;
        }
        return this.locationBuilder_;
    }

    public final DescriptorProtos$SourceCodeInfo$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$SourceCodeInfo$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$SourceCodeInfo$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$SourceCodeInfo$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
