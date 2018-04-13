package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.SourceCodeInfoOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.BuilderParent;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$SourceCodeInfo extends GeneratedMessageV3 implements SourceCodeInfoOrBuilder {
    private static final DescriptorProtos$SourceCodeInfo DEFAULT_INSTANCE = new DescriptorProtos$SourceCodeInfo();
    public static final int LOCATION_FIELD_NUMBER = 1;
    @Deprecated
    public static final Parser<DescriptorProtos$SourceCodeInfo> PARSER = new DescriptorProtos$SourceCodeInfo$1();
    private static final long serialVersionUID = 0;
    private List<Location> location_;
    private byte memoizedIsInitialized;

    public static final class Location extends GeneratedMessageV3 implements DescriptorProtos$SourceCodeInfo$LocationOrBuilder {
        private static final Location DEFAULT_INSTANCE = new Location();
        public static final int LEADING_COMMENTS_FIELD_NUMBER = 3;
        public static final int LEADING_DETACHED_COMMENTS_FIELD_NUMBER = 6;
        @Deprecated
        public static final Parser<Location> PARSER = new DescriptorProtos$SourceCodeInfo$Location$1();
        public static final int PATH_FIELD_NUMBER = 1;
        public static final int SPAN_FIELD_NUMBER = 2;
        public static final int TRAILING_COMMENTS_FIELD_NUMBER = 4;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private volatile Object leadingComments_;
        private LazyStringList leadingDetachedComments_;
        private byte memoizedIsInitialized;
        private int pathMemoizedSerializedSize;
        private List<Integer> path_;
        private int spanMemoizedSerializedSize;
        private List<Integer> span_;
        private volatile Object trailingComments_;

        private Location(Builder<?> builder) {
            super(builder);
            this.pathMemoizedSerializedSize = -1;
            this.spanMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
        }

        private Location() {
            this.pathMemoizedSerializedSize = -1;
            this.spanMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.path_ = Collections.emptyList();
            this.span_ = Collections.emptyList();
            this.leadingComments_ = "";
            this.trailingComments_ = "";
            this.leadingDetachedComments_ = LazyStringArrayList.EMPTY;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Location(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            Object obj = null;
            this();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            int i = 0;
            while (obj == null) {
                try {
                    int readTag = codedInputStream.readTag();
                    ByteString readBytes;
                    switch (readTag) {
                        case 0:
                            obj = 1;
                            break;
                        case 8:
                            if ((i & 1) != 1) {
                                this.path_ = new ArrayList();
                                i |= 1;
                            }
                            this.path_.add(Integer.valueOf(codedInputStream.readInt32()));
                            break;
                        case 10:
                            readTag = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if ((i & 1) != 1 && codedInputStream.getBytesUntilLimit() > 0) {
                                this.path_ = new ArrayList();
                                i |= 1;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.path_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream.popLimit(readTag);
                            break;
                        case 16:
                            if ((i & 2) != 2) {
                                this.span_ = new ArrayList();
                                i |= 2;
                            }
                            this.span_.add(Integer.valueOf(codedInputStream.readInt32()));
                            break;
                        case 18:
                            readTag = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if ((i & 2) != 2 && codedInputStream.getBytesUntilLimit() > 0) {
                                this.span_ = new ArrayList();
                                i |= 2;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.span_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream.popLimit(readTag);
                            break;
                        case 26:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 1;
                            this.leadingComments_ = readBytes;
                            break;
                        case 34:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 2;
                            this.trailingComments_ = readBytes;
                            break;
                        case 50:
                            readBytes = codedInputStream.readBytes();
                            if ((i & 16) != 16) {
                                this.leadingDetachedComments_ = new LazyStringArrayList();
                                i |= 16;
                            }
                            this.leadingDetachedComments_.add(readBytes);
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if ((i & 1) == 1) {
                        this.path_ = Collections.unmodifiableList(this.path_);
                    }
                    if ((i & 2) == 2) {
                        this.span_ = Collections.unmodifiableList(this.span_);
                    }
                    if ((i & 16) == 16) {
                        this.leadingDetachedComments_ = this.leadingDetachedComments_.getUnmodifiableView();
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            if ((i & 1) == 1) {
                this.path_ = Collections.unmodifiableList(this.path_);
            }
            if ((i & 2) == 2) {
                this.span_ = Collections.unmodifiableList(this.span_);
            }
            if ((i & 16) == 16) {
                this.leadingDetachedComments_ = this.leadingDetachedComments_.getUnmodifiableView();
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.access$26000();
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.access$26100().ensureFieldAccessorsInitialized(Location.class, DescriptorProtos$SourceCodeInfo$Location$Builder.class);
        }

        public List<Integer> getPathList() {
            return this.path_;
        }

        public int getPathCount() {
            return this.path_.size();
        }

        public int getPath(int i) {
            return ((Integer) this.path_.get(i)).intValue();
        }

        public List<Integer> getSpanList() {
            return this.span_;
        }

        public int getSpanCount() {
            return this.span_.size();
        }

        public int getSpan(int i) {
            return ((Integer) this.span_.get(i)).intValue();
        }

        public boolean hasLeadingComments() {
            return (this.bitField0_ & 1) == 1;
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

        public boolean hasTrailingComments() {
            return (this.bitField0_ & 2) == 2;
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

        public ProtocolStringList getLeadingDetachedCommentsList() {
            return this.leadingDetachedComments_;
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

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i;
            int i2 = 0;
            getSerializedSize();
            if (getPathList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(10);
                codedOutputStream.writeUInt32NoTag(this.pathMemoizedSerializedSize);
            }
            for (i = 0; i < this.path_.size(); i++) {
                codedOutputStream.writeInt32NoTag(((Integer) this.path_.get(i)).intValue());
            }
            if (getSpanList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(18);
                codedOutputStream.writeUInt32NoTag(this.spanMemoizedSerializedSize);
            }
            for (i = 0; i < this.span_.size(); i++) {
                codedOutputStream.writeInt32NoTag(((Integer) this.span_.get(i)).intValue());
            }
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.leadingComments_);
            }
            if ((this.bitField0_ & 2) == 2) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.trailingComments_);
            }
            while (i2 < this.leadingDetachedComments_.size()) {
                GeneratedMessageV3.writeString(codedOutputStream, 6, this.leadingDetachedComments_.getRaw(i2));
                i2++;
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = 0;
            int i2 = this.memoizedSize;
            if (i2 != -1) {
                return i2;
            }
            int i3;
            int i4 = 0;
            for (i3 = 0; i3 < this.path_.size(); i3++) {
                i4 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.path_.get(i3)).intValue());
            }
            i2 = 0 + i4;
            if (getPathList().isEmpty()) {
                i3 = i2;
            } else {
                i3 = (i2 + 1) + CodedOutputStream.computeInt32SizeNoTag(i4);
            }
            this.pathMemoizedSerializedSize = i4;
            int i5 = 0;
            for (i4 = 0; i4 < this.span_.size(); i4++) {
                i5 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.span_.get(i4)).intValue());
            }
            i2 = i3 + i5;
            if (!getSpanList().isEmpty()) {
                i2 = (i2 + 1) + CodedOutputStream.computeInt32SizeNoTag(i5);
            }
            this.spanMemoizedSerializedSize = i5;
            if ((this.bitField0_ & 1) == 1) {
                i2 += GeneratedMessageV3.computeStringSize(3, this.leadingComments_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += GeneratedMessageV3.computeStringSize(4, this.trailingComments_);
            }
            i3 = 0;
            while (i < this.leadingDetachedComments_.size()) {
                i3 += computeStringSizeNoTag(this.leadingDetachedComments_.getRaw(i));
                i++;
            }
            i2 = ((i2 + i3) + (getLeadingDetachedCommentsList().size() * 1)) + this.unknownFields.getSerializedSize();
            this.memoizedSize = i2;
            return i2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Location)) {
                return super.equals(obj);
            }
            boolean z;
            Location location = (Location) obj;
            if ((getPathList().equals(location.getPathList())) && getSpanList().equals(location.getSpanList())) {
                z = true;
            } else {
                z = false;
            }
            if (z && hasLeadingComments() == location.hasLeadingComments()) {
                z = true;
            } else {
                z = false;
            }
            if (hasLeadingComments()) {
                if (z && getLeadingComments().equals(location.getLeadingComments())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasTrailingComments() == location.hasTrailingComments()) {
                z = true;
            } else {
                z = false;
            }
            if (hasTrailingComments()) {
                if (z && getTrailingComments().equals(location.getTrailingComments())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && getLeadingDetachedCommentsList().equals(location.getLeadingDetachedCommentsList())) {
                z = true;
            } else {
                z = false;
            }
            if (z && this.unknownFields.equals(location.unknownFields)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (getPathCount() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + getPathList().hashCode();
            }
            if (getSpanCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getSpanList().hashCode();
            }
            if (hasLeadingComments()) {
                hashCode = (((hashCode * 37) + 3) * 53) + getLeadingComments().hashCode();
            }
            if (hasTrailingComments()) {
                hashCode = (((hashCode * 37) + 4) * 53) + getTrailingComments().hashCode();
            }
            if (getLeadingDetachedCommentsCount() > 0) {
                hashCode = (((hashCode * 37) + 6) * 53) + getLeadingDetachedCommentsList().hashCode();
            }
            hashCode = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Location parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Location) PARSER.parseFrom(byteBuffer);
        }

        public static Location parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Location) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Location parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Location) PARSER.parseFrom(byteString);
        }

        public static Location parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Location) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Location parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Location) PARSER.parseFrom(bArr);
        }

        public static Location parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Location) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Location parseFrom(InputStream inputStream) throws IOException {
            return (Location) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Location parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Location) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Location parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Location) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Location parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Location) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Location parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Location) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Location parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Location) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public DescriptorProtos$SourceCodeInfo$Location$Builder newBuilderForType() {
            return newBuilder();
        }

        public static DescriptorProtos$SourceCodeInfo$Location$Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static DescriptorProtos$SourceCodeInfo$Location$Builder newBuilder(Location location) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(location);
        }

        public DescriptorProtos$SourceCodeInfo$Location$Builder toBuilder() {
            if (this == DEFAULT_INSTANCE) {
                return new DescriptorProtos$SourceCodeInfo$Location$Builder(null);
            }
            return new DescriptorProtos$SourceCodeInfo$Location$Builder(null).mergeFrom(this);
        }

        protected DescriptorProtos$SourceCodeInfo$Location$Builder newBuilderForType(BuilderParent builderParent) {
            return new DescriptorProtos$SourceCodeInfo$Location$Builder(builderParent, null);
        }

        public static Location getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Location> parser() {
            return PARSER;
        }

        public Parser<Location> getParserForType() {
            return PARSER;
        }

        public Location getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    private DescriptorProtos$SourceCodeInfo(Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$SourceCodeInfo() {
        this.memoizedIsInitialized = (byte) -1;
        this.location_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DescriptorProtos$SourceCodeInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Object obj = null;
        this();
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        int i = 0;
        while (obj == null) {
            try {
                int readTag = codedInputStream.readTag();
                switch (readTag) {
                    case 0:
                        obj = 1;
                        break;
                    case 10:
                        if ((i & 1) != 1) {
                            this.location_ = new ArrayList();
                            i |= 1;
                        }
                        this.location_.add(codedInputStream.readMessage(Location.PARSER, extensionRegistryLite));
                        break;
                    default:
                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e2) {
                throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
            } catch (Throwable th) {
                if ((i & 1) == 1) {
                    this.location_ = Collections.unmodifiableList(this.location_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        if ((i & 1) == 1) {
            this.location_ = Collections.unmodifiableList(this.location_);
        }
        this.unknownFields = newBuilder.build();
        makeExtensionsImmutable();
    }

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.access$25800();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$25900().ensureFieldAccessorsInitialized(DescriptorProtos$SourceCodeInfo.class, DescriptorProtos$SourceCodeInfo$Builder.class);
    }

    public List<Location> getLocationList() {
        return this.location_;
    }

    public List<? extends DescriptorProtos$SourceCodeInfo$LocationOrBuilder> getLocationOrBuilderList() {
        return this.location_;
    }

    public int getLocationCount() {
        return this.location_.size();
    }

    public Location getLocation(int i) {
        return (Location) this.location_.get(i);
    }

    public DescriptorProtos$SourceCodeInfo$LocationOrBuilder getLocationOrBuilder(int i) {
        return (DescriptorProtos$SourceCodeInfo$LocationOrBuilder) this.location_.get(i);
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == (byte) 1) {
            return true;
        }
        if (b == (byte) 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.location_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.location_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (i = 0; i < this.location_.size(); i++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.location_.get(i));
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DescriptorProtos$SourceCodeInfo)) {
            return super.equals(obj);
        }
        boolean z;
        DescriptorProtos$SourceCodeInfo descriptorProtos$SourceCodeInfo = (DescriptorProtos$SourceCodeInfo) obj;
        if (getLocationList().equals(descriptorProtos$SourceCodeInfo.getLocationList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.unknownFields.equals(descriptorProtos$SourceCodeInfo.unknownFields)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (getLocationCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getLocationList().hashCode();
        }
        hashCode = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$SourceCodeInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$SourceCodeInfo) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$SourceCodeInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$SourceCodeInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$SourceCodeInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$SourceCodeInfo) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$SourceCodeInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$SourceCodeInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$SourceCodeInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$SourceCodeInfo) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$SourceCodeInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$SourceCodeInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$SourceCodeInfo parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$SourceCodeInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$SourceCodeInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$SourceCodeInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$SourceCodeInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$SourceCodeInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$SourceCodeInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$SourceCodeInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$SourceCodeInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$SourceCodeInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$SourceCodeInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$SourceCodeInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$SourceCodeInfo$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$SourceCodeInfo$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$SourceCodeInfo$Builder newBuilder(DescriptorProtos$SourceCodeInfo descriptorProtos$SourceCodeInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$SourceCodeInfo);
    }

    public DescriptorProtos$SourceCodeInfo$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$SourceCodeInfo$Builder(null);
        }
        return new DescriptorProtos$SourceCodeInfo$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$SourceCodeInfo$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$SourceCodeInfo$Builder(builderParent, null);
    }

    public static DescriptorProtos$SourceCodeInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$SourceCodeInfo> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$SourceCodeInfo> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$SourceCodeInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
