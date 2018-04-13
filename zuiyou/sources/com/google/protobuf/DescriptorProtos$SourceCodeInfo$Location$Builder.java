package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.SourceCodeInfo.Location;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$SourceCodeInfo$Location$Builder extends Builder<DescriptorProtos$SourceCodeInfo$Location$Builder> implements DescriptorProtos$SourceCodeInfo$LocationOrBuilder {
    private int bitField0_;
    private Object leadingComments_;
    private LazyStringList leadingDetachedComments_;
    private List<Integer> path_;
    private List<Integer> span_;
    private Object trailingComments_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable.ensureFieldAccessorsInitialized(Location.class, DescriptorProtos$SourceCodeInfo$Location$Builder.class);
    }

    private DescriptorProtos$SourceCodeInfo$Location$Builder() {
        this.path_ = Collections.emptyList();
        this.span_ = Collections.emptyList();
        this.leadingComments_ = "";
        this.trailingComments_ = "";
        this.leadingDetachedComments_ = LazyStringArrayList.EMPTY;
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$SourceCodeInfo$Location$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.path_ = Collections.emptyList();
        this.span_ = Collections.emptyList();
        this.leadingComments_ = "";
        this.trailingComments_ = "";
        this.leadingDetachedComments_ = LazyStringArrayList.EMPTY;
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (!GeneratedMessageV3.alwaysUseFieldBuilders) {
        }
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder clear() {
        super.clear();
        this.path_ = Collections.emptyList();
        this.bitField0_ &= -2;
        this.span_ = Collections.emptyList();
        this.bitField0_ &= -3;
        this.leadingComments_ = "";
        this.bitField0_ &= -5;
        this.trailingComments_ = "";
        this.bitField0_ &= -9;
        this.leadingDetachedComments_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= -17;
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_descriptor;
    }

    public Location getDefaultInstanceForType() {
        return Location.getDefaultInstance();
    }

    public Location build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public Location buildPartial() {
        int i = 1;
        Location location = new Location(this, null);
        int i2 = this.bitField0_;
        if ((this.bitField0_ & 1) == 1) {
            this.path_ = Collections.unmodifiableList(this.path_);
            this.bitField0_ &= -2;
        }
        Location.access$26502(location, this.path_);
        if ((this.bitField0_ & 2) == 2) {
            this.span_ = Collections.unmodifiableList(this.span_);
            this.bitField0_ &= -3;
        }
        Location.access$26602(location, this.span_);
        if ((i2 & 4) != 4) {
            i = 0;
        }
        Location.access$26702(location, this.leadingComments_);
        if ((i2 & 8) == 8) {
            i |= 2;
        }
        Location.access$26802(location, this.trailingComments_);
        if ((this.bitField0_ & 16) == 16) {
            this.leadingDetachedComments_ = this.leadingDetachedComments_.getUnmodifiableView();
            this.bitField0_ &= -17;
        }
        Location.access$26902(location, this.leadingDetachedComments_);
        Location.access$27002(location, i);
        onBuilt();
        return location;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder clone() {
        return (DescriptorProtos$SourceCodeInfo$Location$Builder) super.clone();
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$SourceCodeInfo$Location$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$SourceCodeInfo$Location$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$SourceCodeInfo$Location$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$SourceCodeInfo$Location$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$SourceCodeInfo$Location$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder mergeFrom(Message message) {
        if (message instanceof Location) {
            return mergeFrom((Location) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder mergeFrom(Location location) {
        if (location != Location.getDefaultInstance()) {
            if (!Location.access$26500(location).isEmpty()) {
                if (this.path_.isEmpty()) {
                    this.path_ = Location.access$26500(location);
                    this.bitField0_ &= -2;
                } else {
                    ensurePathIsMutable();
                    this.path_.addAll(Location.access$26500(location));
                }
                onChanged();
            }
            if (!Location.access$26600(location).isEmpty()) {
                if (this.span_.isEmpty()) {
                    this.span_ = Location.access$26600(location);
                    this.bitField0_ &= -3;
                } else {
                    ensureSpanIsMutable();
                    this.span_.addAll(Location.access$26600(location));
                }
                onChanged();
            }
            if (location.hasLeadingComments()) {
                this.bitField0_ |= 4;
                this.leadingComments_ = Location.access$26700(location);
                onChanged();
            }
            if (location.hasTrailingComments()) {
                this.bitField0_ |= 8;
                this.trailingComments_ = Location.access$26800(location);
                onChanged();
            }
            if (!Location.access$26900(location).isEmpty()) {
                if (this.leadingDetachedComments_.isEmpty()) {
                    this.leadingDetachedComments_ = Location.access$26900(location);
                    this.bitField0_ &= -17;
                } else {
                    ensureLeadingDetachedCommentsIsMutable();
                    this.leadingDetachedComments_.addAll(Location.access$26900(location));
                }
                onChanged();
            }
            mergeUnknownFields(location.unknownFields);
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        return true;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        Location location;
        Location location2;
        try {
            location2 = (Location) Location.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (location2 != null) {
                mergeFrom(location2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            location2 = (Location) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            location = location2;
            th = th3;
            if (location != null) {
                mergeFrom(location);
            }
            throw th;
        }
    }

    private void ensurePathIsMutable() {
        if ((this.bitField0_ & 1) != 1) {
            this.path_ = new ArrayList(this.path_);
            this.bitField0_ |= 1;
        }
    }

    public List<Integer> getPathList() {
        return Collections.unmodifiableList(this.path_);
    }

    public int getPathCount() {
        return this.path_.size();
    }

    public int getPath(int i) {
        return ((Integer) this.path_.get(i)).intValue();
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder setPath(int i, int i2) {
        ensurePathIsMutable();
        this.path_.set(i, Integer.valueOf(i2));
        onChanged();
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder addPath(int i) {
        ensurePathIsMutable();
        this.path_.add(Integer.valueOf(i));
        onChanged();
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder addAllPath(Iterable<? extends Integer> iterable) {
        ensurePathIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, this.path_);
        onChanged();
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder clearPath() {
        this.path_ = Collections.emptyList();
        this.bitField0_ &= -2;
        onChanged();
        return this;
    }

    private void ensureSpanIsMutable() {
        if ((this.bitField0_ & 2) != 2) {
            this.span_ = new ArrayList(this.span_);
            this.bitField0_ |= 2;
        }
    }

    public List<Integer> getSpanList() {
        return Collections.unmodifiableList(this.span_);
    }

    public int getSpanCount() {
        return this.span_.size();
    }

    public int getSpan(int i) {
        return ((Integer) this.span_.get(i)).intValue();
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder setSpan(int i, int i2) {
        ensureSpanIsMutable();
        this.span_.set(i, Integer.valueOf(i2));
        onChanged();
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder addSpan(int i) {
        ensureSpanIsMutable();
        this.span_.add(Integer.valueOf(i));
        onChanged();
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder addAllSpan(Iterable<? extends Integer> iterable) {
        ensureSpanIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, this.span_);
        onChanged();
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder clearSpan() {
        this.span_ = Collections.emptyList();
        this.bitField0_ &= -3;
        onChanged();
        return this;
    }

    public boolean hasLeadingComments() {
        return (this.bitField0_ & 4) == 4;
    }

    public String getLeadingComments() {
        Object obj = this.leadingComments_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.leadingComments_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getLeadingCommentsBytes() {
        Object obj = this.leadingComments_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.leadingComments_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder setLeadingComments(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 4;
        this.leadingComments_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder clearLeadingComments() {
        this.bitField0_ &= -5;
        this.leadingComments_ = Location.getDefaultInstance().getLeadingComments();
        onChanged();
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder setLeadingCommentsBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 4;
        this.leadingComments_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasTrailingComments() {
        return (this.bitField0_ & 8) == 8;
    }

    public String getTrailingComments() {
        Object obj = this.trailingComments_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.trailingComments_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getTrailingCommentsBytes() {
        Object obj = this.trailingComments_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.trailingComments_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder setTrailingComments(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 8;
        this.trailingComments_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder clearTrailingComments() {
        this.bitField0_ &= -9;
        this.trailingComments_ = Location.getDefaultInstance().getTrailingComments();
        onChanged();
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder setTrailingCommentsBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 8;
        this.trailingComments_ = byteString;
        onChanged();
        return this;
    }

    private void ensureLeadingDetachedCommentsIsMutable() {
        if ((this.bitField0_ & 16) != 16) {
            this.leadingDetachedComments_ = new LazyStringArrayList(this.leadingDetachedComments_);
            this.bitField0_ |= 16;
        }
    }

    public ProtocolStringList getLeadingDetachedCommentsList() {
        return this.leadingDetachedComments_.getUnmodifiableView();
    }

    public int getLeadingDetachedCommentsCount() {
        return this.leadingDetachedComments_.size();
    }

    public String getLeadingDetachedComments(int i) {
        return (String) this.leadingDetachedComments_.get(i);
    }

    public ByteString getLeadingDetachedCommentsBytes(int i) {
        return this.leadingDetachedComments_.getByteString(i);
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder setLeadingDetachedComments(int i, String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        ensureLeadingDetachedCommentsIsMutable();
        this.leadingDetachedComments_.set(i, str);
        onChanged();
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder addLeadingDetachedComments(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        ensureLeadingDetachedCommentsIsMutable();
        this.leadingDetachedComments_.add(str);
        onChanged();
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder addAllLeadingDetachedComments(Iterable<String> iterable) {
        ensureLeadingDetachedCommentsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, this.leadingDetachedComments_);
        onChanged();
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder clearLeadingDetachedComments() {
        this.leadingDetachedComments_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= -17;
        onChanged();
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Location$Builder addLeadingDetachedCommentsBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        ensureLeadingDetachedCommentsIsMutable();
        this.leadingDetachedComments_.add(byteString);
        onChanged();
        return this;
    }

    public final DescriptorProtos$SourceCodeInfo$Location$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$SourceCodeInfo$Location$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$SourceCodeInfo$Location$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$SourceCodeInfo$Location$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
