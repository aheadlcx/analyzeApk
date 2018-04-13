package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.GeneratedCodeInfoOrBuilder;
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

public final class DescriptorProtos$GeneratedCodeInfo extends GeneratedMessageV3 implements GeneratedCodeInfoOrBuilder {
    public static final int ANNOTATION_FIELD_NUMBER = 1;
    private static final DescriptorProtos$GeneratedCodeInfo DEFAULT_INSTANCE = new DescriptorProtos$GeneratedCodeInfo();
    @Deprecated
    public static final Parser<DescriptorProtos$GeneratedCodeInfo> PARSER = new DescriptorProtos$GeneratedCodeInfo$1();
    private static final long serialVersionUID = 0;
    private List<Annotation> annotation_;
    private byte memoizedIsInitialized;

    public static final class Annotation extends GeneratedMessageV3 implements DescriptorProtos$GeneratedCodeInfo$AnnotationOrBuilder {
        public static final int BEGIN_FIELD_NUMBER = 3;
        private static final Annotation DEFAULT_INSTANCE = new Annotation();
        public static final int END_FIELD_NUMBER = 4;
        @Deprecated
        public static final Parser<Annotation> PARSER = new DescriptorProtos$GeneratedCodeInfo$Annotation$1();
        public static final int PATH_FIELD_NUMBER = 1;
        public static final int SOURCE_FILE_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int begin_;
        private int bitField0_;
        private int end_;
        private byte memoizedIsInitialized;
        private int pathMemoizedSerializedSize;
        private List<Integer> path_;
        private volatile Object sourceFile_;

        private Annotation(Builder<?> builder) {
            super(builder);
            this.pathMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
        }

        private Annotation() {
            this.pathMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.path_ = Collections.emptyList();
            this.sourceFile_ = "";
            this.begin_ = 0;
            this.end_ = 0;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Annotation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        case 18:
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 1;
                            this.sourceFile_ = readBytes;
                            break;
                        case 24:
                            this.bitField0_ |= 2;
                            this.begin_ = codedInputStream.readInt32();
                            break;
                        case 32:
                            this.bitField0_ |= 4;
                            this.end_ = codedInputStream.readInt32();
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
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            if ((i & 1) == 1) {
                this.path_ = Collections.unmodifiableList(this.path_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.access$27900();
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.access$28000().ensureFieldAccessorsInitialized(Annotation.class, DescriptorProtos$GeneratedCodeInfo$Annotation$Builder.class);
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

        public boolean hasSourceFile() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getSourceFile() {
            Object obj = this.sourceFile_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.sourceFile_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getSourceFileBytes() {
            Object obj = this.sourceFile_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.sourceFile_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasBegin() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getBegin() {
            return this.begin_;
        }

        public boolean hasEnd() {
            return (this.bitField0_ & 4) == 4;
        }

        public int getEnd() {
            return this.end_;
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
            getSerializedSize();
            if (getPathList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(10);
                codedOutputStream.writeUInt32NoTag(this.pathMemoizedSerializedSize);
            }
            for (int i = 0; i < this.path_.size(); i++) {
                codedOutputStream.writeInt32NoTag(((Integer) this.path_.get(i)).intValue());
            }
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.sourceFile_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(3, this.begin_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeInt32(4, this.end_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.path_.size(); i3++) {
                i2 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.path_.get(i3)).intValue());
            }
            i = 0 + i2;
            if (!getPathList().isEmpty()) {
                i = (i + 1) + CodedOutputStream.computeInt32SizeNoTag(i2);
            }
            this.pathMemoizedSerializedSize = i2;
            if ((this.bitField0_ & 1) == 1) {
                i += GeneratedMessageV3.computeStringSize(2, this.sourceFile_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i += CodedOutputStream.computeInt32Size(3, this.begin_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i += CodedOutputStream.computeInt32Size(4, this.end_);
            }
            i += this.unknownFields.getSerializedSize();
            this.memoizedSize = i;
            return i;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Annotation)) {
                return super.equals(obj);
            }
            boolean z;
            Annotation annotation = (Annotation) obj;
            if ((getPathList().equals(annotation.getPathList())) && hasSourceFile() == annotation.hasSourceFile()) {
                z = true;
            } else {
                z = false;
            }
            if (hasSourceFile()) {
                if (z && getSourceFile().equals(annotation.getSourceFile())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasBegin() == annotation.hasBegin()) {
                z = true;
            } else {
                z = false;
            }
            if (hasBegin()) {
                if (z && getBegin() == annotation.getBegin()) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasEnd() == annotation.hasEnd()) {
                z = true;
            } else {
                z = false;
            }
            if (hasEnd()) {
                if (z && getEnd() == annotation.getEnd()) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && this.unknownFields.equals(annotation.unknownFields)) {
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
            if (hasSourceFile()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getSourceFile().hashCode();
            }
            if (hasBegin()) {
                hashCode = (((hashCode * 37) + 3) * 53) + getBegin();
            }
            if (hasEnd()) {
                hashCode = (((hashCode * 37) + 4) * 53) + getEnd();
            }
            hashCode = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Annotation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Annotation) PARSER.parseFrom(byteBuffer);
        }

        public static Annotation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Annotation) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Annotation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Annotation) PARSER.parseFrom(byteString);
        }

        public static Annotation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Annotation) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Annotation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Annotation) PARSER.parseFrom(bArr);
        }

        public static Annotation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Annotation) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Annotation parseFrom(InputStream inputStream) throws IOException {
            return (Annotation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Annotation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Annotation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Annotation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Annotation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Annotation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Annotation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Annotation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Annotation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Annotation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Annotation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder newBuilderForType() {
            return newBuilder();
        }

        public static DescriptorProtos$GeneratedCodeInfo$Annotation$Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static DescriptorProtos$GeneratedCodeInfo$Annotation$Builder newBuilder(Annotation annotation) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(annotation);
        }

        public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder toBuilder() {
            if (this == DEFAULT_INSTANCE) {
                return new DescriptorProtos$GeneratedCodeInfo$Annotation$Builder(null);
            }
            return new DescriptorProtos$GeneratedCodeInfo$Annotation$Builder(null).mergeFrom(this);
        }

        protected DescriptorProtos$GeneratedCodeInfo$Annotation$Builder newBuilderForType(BuilderParent builderParent) {
            return new DescriptorProtos$GeneratedCodeInfo$Annotation$Builder(builderParent, null);
        }

        public static Annotation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Annotation> parser() {
            return PARSER;
        }

        public Parser<Annotation> getParserForType() {
            return PARSER;
        }

        public Annotation getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    private DescriptorProtos$GeneratedCodeInfo(Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$GeneratedCodeInfo() {
        this.memoizedIsInitialized = (byte) -1;
        this.annotation_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DescriptorProtos$GeneratedCodeInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.annotation_ = new ArrayList();
                            i |= 1;
                        }
                        this.annotation_.add(codedInputStream.readMessage(Annotation.PARSER, extensionRegistryLite));
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
                    this.annotation_ = Collections.unmodifiableList(this.annotation_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        if ((i & 1) == 1) {
            this.annotation_ = Collections.unmodifiableList(this.annotation_);
        }
        this.unknownFields = newBuilder.build();
        makeExtensionsImmutable();
    }

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.access$27700();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$27800().ensureFieldAccessorsInitialized(DescriptorProtos$GeneratedCodeInfo.class, DescriptorProtos$GeneratedCodeInfo$Builder.class);
    }

    public List<Annotation> getAnnotationList() {
        return this.annotation_;
    }

    public List<? extends DescriptorProtos$GeneratedCodeInfo$AnnotationOrBuilder> getAnnotationOrBuilderList() {
        return this.annotation_;
    }

    public int getAnnotationCount() {
        return this.annotation_.size();
    }

    public Annotation getAnnotation(int i) {
        return (Annotation) this.annotation_.get(i);
    }

    public DescriptorProtos$GeneratedCodeInfo$AnnotationOrBuilder getAnnotationOrBuilder(int i) {
        return (DescriptorProtos$GeneratedCodeInfo$AnnotationOrBuilder) this.annotation_.get(i);
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
        for (int i = 0; i < this.annotation_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.annotation_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (i = 0; i < this.annotation_.size(); i++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.annotation_.get(i));
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DescriptorProtos$GeneratedCodeInfo)) {
            return super.equals(obj);
        }
        boolean z;
        DescriptorProtos$GeneratedCodeInfo descriptorProtos$GeneratedCodeInfo = (DescriptorProtos$GeneratedCodeInfo) obj;
        if (getAnnotationList().equals(descriptorProtos$GeneratedCodeInfo.getAnnotationList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.unknownFields.equals(descriptorProtos$GeneratedCodeInfo.unknownFields)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (getAnnotationCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getAnnotationList().hashCode();
        }
        hashCode = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$GeneratedCodeInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$GeneratedCodeInfo) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$GeneratedCodeInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$GeneratedCodeInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$GeneratedCodeInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$GeneratedCodeInfo) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$GeneratedCodeInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$GeneratedCodeInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$GeneratedCodeInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$GeneratedCodeInfo) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$GeneratedCodeInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$GeneratedCodeInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$GeneratedCodeInfo parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$GeneratedCodeInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$GeneratedCodeInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$GeneratedCodeInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$GeneratedCodeInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$GeneratedCodeInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$GeneratedCodeInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$GeneratedCodeInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$GeneratedCodeInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$GeneratedCodeInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$GeneratedCodeInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$GeneratedCodeInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$GeneratedCodeInfo$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$GeneratedCodeInfo$Builder newBuilder(DescriptorProtos$GeneratedCodeInfo descriptorProtos$GeneratedCodeInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$GeneratedCodeInfo);
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$GeneratedCodeInfo$Builder(null);
        }
        return new DescriptorProtos$GeneratedCodeInfo$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$GeneratedCodeInfo$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$GeneratedCodeInfo$Builder(builderParent, null);
    }

    public static DescriptorProtos$GeneratedCodeInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$GeneratedCodeInfo> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$GeneratedCodeInfo> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$GeneratedCodeInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
