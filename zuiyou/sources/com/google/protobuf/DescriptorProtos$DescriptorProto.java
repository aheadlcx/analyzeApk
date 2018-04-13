package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.EnumDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.ExtensionRangeOptionsOrBuilder;
import com.google.protobuf.DescriptorProtos.FieldDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.MessageOptionsOrBuilder;
import com.google.protobuf.DescriptorProtos.OneofDescriptorProtoOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.BuilderParent;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$DescriptorProto extends GeneratedMessageV3 implements DescriptorProtoOrBuilder {
    private static final DescriptorProtos$DescriptorProto DEFAULT_INSTANCE = new DescriptorProtos$DescriptorProto();
    public static final int ENUM_TYPE_FIELD_NUMBER = 4;
    public static final int EXTENSION_FIELD_NUMBER = 6;
    public static final int EXTENSION_RANGE_FIELD_NUMBER = 5;
    public static final int FIELD_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int NESTED_TYPE_FIELD_NUMBER = 3;
    public static final int ONEOF_DECL_FIELD_NUMBER = 8;
    public static final int OPTIONS_FIELD_NUMBER = 7;
    @Deprecated
    public static final Parser<DescriptorProtos$DescriptorProto> PARSER = new DescriptorProtos$DescriptorProto$1();
    public static final int RESERVED_NAME_FIELD_NUMBER = 10;
    public static final int RESERVED_RANGE_FIELD_NUMBER = 9;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private List<DescriptorProtos$EnumDescriptorProto> enumType_;
    private List<ExtensionRange> extensionRange_;
    private List<DescriptorProtos$FieldDescriptorProto> extension_;
    private List<DescriptorProtos$FieldDescriptorProto> field_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private List<DescriptorProtos$DescriptorProto> nestedType_;
    private List<DescriptorProtos$OneofDescriptorProto> oneofDecl_;
    private DescriptorProtos$MessageOptions options_;
    private LazyStringList reservedName_;
    private List<ReservedRange> reservedRange_;

    public static final class ExtensionRange extends GeneratedMessageV3 implements DescriptorProtos$DescriptorProto$ExtensionRangeOrBuilder {
        private static final ExtensionRange DEFAULT_INSTANCE = new ExtensionRange();
        public static final int END_FIELD_NUMBER = 2;
        public static final int OPTIONS_FIELD_NUMBER = 3;
        @Deprecated
        public static final Parser<ExtensionRange> PARSER = new DescriptorProtos$DescriptorProto$ExtensionRange$1();
        public static final int START_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int end_;
        private byte memoizedIsInitialized;
        private DescriptorProtos$ExtensionRangeOptions options_;
        private int start_;

        private ExtensionRange(Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ExtensionRange() {
            this.memoizedIsInitialized = (byte) -1;
            this.start_ = 0;
            this.end_ = 0;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ExtensionRange(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            Object obj = null;
            while (obj == null) {
                try {
                    Object obj2;
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            readTag = 1;
                            break;
                        case 8:
                            this.bitField0_ |= 1;
                            this.start_ = codedInputStream.readInt32();
                            obj2 = obj;
                            break;
                        case 16:
                            this.bitField0_ |= 2;
                            this.end_ = codedInputStream.readInt32();
                            obj2 = obj;
                            break;
                        case 26:
                            DescriptorProtos$ExtensionRangeOptions$Builder toBuilder;
                            if ((this.bitField0_ & 4) == 4) {
                                toBuilder = this.options_.toBuilder();
                            } else {
                                toBuilder = null;
                            }
                            this.options_ = (DescriptorProtos$ExtensionRangeOptions) codedInputStream.readMessage(DescriptorProtos$ExtensionRangeOptions.PARSER, extensionRegistryLite);
                            if (toBuilder != null) {
                                toBuilder.mergeFrom(this.options_);
                                this.options_ = toBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                            obj2 = obj;
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                obj2 = 1;
                                break;
                            } else {
                                obj2 = obj;
                                break;
                            }
                    }
                    obj = obj2;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.access$2800();
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.access$2900().ensureFieldAccessorsInitialized(ExtensionRange.class, DescriptorProtos$DescriptorProto$ExtensionRange$Builder.class);
        }

        public boolean hasStart() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getStart() {
            return this.start_;
        }

        public boolean hasEnd() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getEnd() {
            return this.end_;
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 4) == 4;
        }

        public DescriptorProtos$ExtensionRangeOptions getOptions() {
            return this.options_ == null ? DescriptorProtos$ExtensionRangeOptions.getDefaultInstance() : this.options_;
        }

        public ExtensionRangeOptionsOrBuilder getOptionsOrBuilder() {
            return this.options_ == null ? DescriptorProtos$ExtensionRangeOptions.getDefaultInstance() : this.options_;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            if (!hasOptions() || getOptions().isInitialized()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.start_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.end_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeMessage(3, getOptions());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.bitField0_ & 1) == 1) {
                i = 0 + CodedOutputStream.computeInt32Size(1, this.start_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i += CodedOutputStream.computeInt32Size(2, this.end_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i += CodedOutputStream.computeMessageSize(3, getOptions());
            }
            i += this.unknownFields.getSerializedSize();
            this.memoizedSize = i;
            return i;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ExtensionRange)) {
                return super.equals(obj);
            }
            ExtensionRange extensionRange = (ExtensionRange) obj;
            boolean z = hasStart() == extensionRange.hasStart();
            if (hasStart()) {
                z = z && getStart() == extensionRange.getStart();
            }
            if (z && hasEnd() == extensionRange.hasEnd()) {
                z = true;
            } else {
                z = false;
            }
            if (hasEnd()) {
                if (z && getEnd() == extensionRange.getEnd()) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasOptions() == extensionRange.hasOptions()) {
                z = true;
            } else {
                z = false;
            }
            if (hasOptions()) {
                if (z && getOptions().equals(extensionRange.getOptions())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && this.unknownFields.equals(extensionRange.unknownFields)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasStart()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getStart();
            }
            if (hasEnd()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getEnd();
            }
            if (hasOptions()) {
                hashCode = (((hashCode * 37) + 3) * 53) + getOptions().hashCode();
            }
            hashCode = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ExtensionRange parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ExtensionRange) PARSER.parseFrom(byteBuffer);
        }

        public static ExtensionRange parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExtensionRange) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ExtensionRange parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ExtensionRange) PARSER.parseFrom(byteString);
        }

        public static ExtensionRange parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExtensionRange) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ExtensionRange parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ExtensionRange) PARSER.parseFrom(bArr);
        }

        public static ExtensionRange parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExtensionRange) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ExtensionRange parseFrom(InputStream inputStream) throws IOException {
            return (ExtensionRange) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ExtensionRange parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExtensionRange) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ExtensionRange parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ExtensionRange) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ExtensionRange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExtensionRange) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ExtensionRange parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ExtensionRange) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ExtensionRange parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExtensionRange) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public DescriptorProtos$DescriptorProto$ExtensionRange$Builder newBuilderForType() {
            return newBuilder();
        }

        public static DescriptorProtos$DescriptorProto$ExtensionRange$Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static DescriptorProtos$DescriptorProto$ExtensionRange$Builder newBuilder(ExtensionRange extensionRange) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(extensionRange);
        }

        public DescriptorProtos$DescriptorProto$ExtensionRange$Builder toBuilder() {
            if (this == DEFAULT_INSTANCE) {
                return new DescriptorProtos$DescriptorProto$ExtensionRange$Builder(null);
            }
            return new DescriptorProtos$DescriptorProto$ExtensionRange$Builder(null).mergeFrom(this);
        }

        protected DescriptorProtos$DescriptorProto$ExtensionRange$Builder newBuilderForType(BuilderParent builderParent) {
            return new DescriptorProtos$DescriptorProto$ExtensionRange$Builder(builderParent, null);
        }

        public static ExtensionRange getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ExtensionRange> parser() {
            return PARSER;
        }

        public Parser<ExtensionRange> getParserForType() {
            return PARSER;
        }

        public ExtensionRange getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class ReservedRange extends GeneratedMessageV3 implements DescriptorProtos$DescriptorProto$ReservedRangeOrBuilder {
        private static final ReservedRange DEFAULT_INSTANCE = new ReservedRange();
        public static final int END_FIELD_NUMBER = 2;
        @Deprecated
        public static final Parser<ReservedRange> PARSER = new DescriptorProtos$DescriptorProto$ReservedRange$1();
        public static final int START_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int end_;
        private byte memoizedIsInitialized;
        private int start_;

        private ReservedRange(Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ReservedRange() {
            this.memoizedIsInitialized = (byte) -1;
            this.start_ = 0;
            this.end_ = 0;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ReservedRange(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            Object obj = null;
            while (obj == null) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            obj = 1;
                            break;
                        case 8:
                            this.bitField0_ |= 1;
                            this.start_ = codedInputStream.readInt32();
                            break;
                        case 16:
                            this.bitField0_ |= 2;
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
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.access$3800();
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.access$3900().ensureFieldAccessorsInitialized(ReservedRange.class, DescriptorProtos$DescriptorProto$ReservedRange$Builder.class);
        }

        public boolean hasStart() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getStart() {
            return this.start_;
        }

        public boolean hasEnd() {
            return (this.bitField0_ & 2) == 2;
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
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.start_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.end_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.bitField0_ & 1) == 1) {
                i = 0 + CodedOutputStream.computeInt32Size(1, this.start_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i += CodedOutputStream.computeInt32Size(2, this.end_);
            }
            i += this.unknownFields.getSerializedSize();
            this.memoizedSize = i;
            return i;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ReservedRange)) {
                return super.equals(obj);
            }
            ReservedRange reservedRange = (ReservedRange) obj;
            boolean z = hasStart() == reservedRange.hasStart();
            if (hasStart()) {
                z = z && getStart() == reservedRange.getStart();
            }
            if (z && hasEnd() == reservedRange.hasEnd()) {
                z = true;
            } else {
                z = false;
            }
            if (hasEnd()) {
                if (z && getEnd() == reservedRange.getEnd()) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && this.unknownFields.equals(reservedRange.unknownFields)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasStart()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getStart();
            }
            if (hasEnd()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getEnd();
            }
            hashCode = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ReservedRange parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ReservedRange) PARSER.parseFrom(byteBuffer);
        }

        public static ReservedRange parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReservedRange) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ReservedRange parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ReservedRange) PARSER.parseFrom(byteString);
        }

        public static ReservedRange parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReservedRange) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ReservedRange parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ReservedRange) PARSER.parseFrom(bArr);
        }

        public static ReservedRange parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReservedRange) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ReservedRange parseFrom(InputStream inputStream) throws IOException {
            return (ReservedRange) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ReservedRange parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReservedRange) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ReservedRange parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ReservedRange) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ReservedRange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReservedRange) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ReservedRange parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ReservedRange) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ReservedRange parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReservedRange) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public DescriptorProtos$DescriptorProto$ReservedRange$Builder newBuilderForType() {
            return newBuilder();
        }

        public static DescriptorProtos$DescriptorProto$ReservedRange$Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static DescriptorProtos$DescriptorProto$ReservedRange$Builder newBuilder(ReservedRange reservedRange) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(reservedRange);
        }

        public DescriptorProtos$DescriptorProto$ReservedRange$Builder toBuilder() {
            if (this == DEFAULT_INSTANCE) {
                return new DescriptorProtos$DescriptorProto$ReservedRange$Builder(null);
            }
            return new DescriptorProtos$DescriptorProto$ReservedRange$Builder(null).mergeFrom(this);
        }

        protected DescriptorProtos$DescriptorProto$ReservedRange$Builder newBuilderForType(BuilderParent builderParent) {
            return new DescriptorProtos$DescriptorProto$ReservedRange$Builder(builderParent, null);
        }

        public static ReservedRange getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ReservedRange> parser() {
            return PARSER;
        }

        public Parser<ReservedRange> getParserForType() {
            return PARSER;
        }

        public ReservedRange getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    private DescriptorProtos$DescriptorProto(Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$DescriptorProto() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.field_ = Collections.emptyList();
        this.extension_ = Collections.emptyList();
        this.nestedType_ = Collections.emptyList();
        this.enumType_ = Collections.emptyList();
        this.extensionRange_ = Collections.emptyList();
        this.oneofDecl_ = Collections.emptyList();
        this.reservedRange_ = Collections.emptyList();
        this.reservedName_ = LazyStringArrayList.EMPTY;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DescriptorProtos$DescriptorProto(com.google.protobuf.CodedInputStream r13, com.google.protobuf.ExtensionRegistryLite r14) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
        r12 = this;
        r10 = 32;
        r9 = 16;
        r8 = 8;
        r7 = 4;
        r6 = 2;
        r12.<init>();
        r2 = 0;
        r4 = com.google.protobuf.UnknownFieldSet.newBuilder();
        r1 = 0;
    L_0x0011:
        if (r1 != 0) goto L_0x014f;
    L_0x0013:
        r0 = r13.readTag();	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        switch(r0) {
            case 0: goto L_0x0025;
            case 10: goto L_0x0028;
            case 18: goto L_0x0037;
            case 26: goto L_0x0053;
            case 34: goto L_0x006f;
            case 42: goto L_0x008b;
            case 50: goto L_0x00a8;
            case 58: goto L_0x00c5;
            case 66: goto L_0x00f4;
            case 74: goto L_0x0113;
            case 82: goto L_0x0132;
            default: goto L_0x001a;
        };	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
    L_0x001a:
        r0 = r12.parseUnknownField(r13, r4, r14, r0);	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        if (r0 != 0) goto L_0x0269;
    L_0x0020:
        r0 = 1;
        r1 = r2;
    L_0x0022:
        r2 = r1;
        r1 = r0;
        goto L_0x0011;
    L_0x0025:
        r0 = 1;
        r1 = r2;
        goto L_0x0022;
    L_0x0028:
        r0 = r13.readBytes();	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r3 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r3 = r3 | 1;
        r12.bitField0_ = r3;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r12.name_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = r1;
        r1 = r2;
        goto L_0x0022;
    L_0x0037:
        r0 = r2 & 2;
        if (r0 == r6) goto L_0x0266;
    L_0x003b:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r12.field_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = r2 | 2;
    L_0x0044:
        r2 = r12.field_;	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r3 = com.google.protobuf.DescriptorProtos$FieldDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r3 = r13.readMessage(r3, r14);	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0022;
    L_0x0053:
        r0 = r2 & 8;
        if (r0 == r8) goto L_0x0263;
    L_0x0057:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r12.nestedType_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = r2 | 8;
    L_0x0060:
        r2 = r12.nestedType_;	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r3 = PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r3 = r13.readMessage(r3, r14);	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0022;
    L_0x006f:
        r0 = r2 & 16;
        if (r0 == r9) goto L_0x0260;
    L_0x0073:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r12.enumType_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = r2 | 16;
    L_0x007c:
        r2 = r12.enumType_;	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r3 = com.google.protobuf.DescriptorProtos$EnumDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r3 = r13.readMessage(r3, r14);	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0022;
    L_0x008b:
        r0 = r2 & 32;
        if (r0 == r10) goto L_0x025d;
    L_0x008f:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r12.extensionRange_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = r2 | 32;
    L_0x0098:
        r2 = r12.extensionRange_;	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r3 = com.google.protobuf.DescriptorProtos.DescriptorProto.ExtensionRange.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r3 = r13.readMessage(r3, r14);	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0022;
    L_0x00a8:
        r0 = r2 & 4;
        if (r0 == r7) goto L_0x025a;
    L_0x00ac:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r12.extension_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = r2 | 4;
    L_0x00b5:
        r2 = r12.extension_;	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r3 = com.google.protobuf.DescriptorProtos$FieldDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r3 = r13.readMessage(r3, r14);	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0022;
    L_0x00c5:
        r0 = 0;
        r3 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r3 = r3 & 2;
        if (r3 != r6) goto L_0x0257;
    L_0x00cc:
        r0 = r12.options_;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = r0.toBuilder();	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r3 = r0;
    L_0x00d3:
        r0 = com.google.protobuf.DescriptorProtos$MessageOptions.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = r13.readMessage(r0, r14);	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = (com.google.protobuf.DescriptorProtos$MessageOptions) r0;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r12.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        if (r3 == 0) goto L_0x00ea;
    L_0x00df:
        r0 = r12.options_;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r3.mergeFrom(r0);	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = r3.buildPartial();	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r12.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
    L_0x00ea:
        r0 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = r0 | 2;
        r12.bitField0_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = r1;
        r1 = r2;
        goto L_0x0022;
    L_0x00f4:
        r0 = r2 & 64;
        r3 = 64;
        if (r0 == r3) goto L_0x0254;
    L_0x00fa:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r12.oneofDecl_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = r2 | 64;
    L_0x0103:
        r2 = r12.oneofDecl_;	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r3 = com.google.protobuf.DescriptorProtos$OneofDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r3 = r13.readMessage(r3, r14);	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0022;
    L_0x0113:
        r0 = r2 & 256;
        r3 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        if (r0 == r3) goto L_0x0251;
    L_0x0119:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r12.reservedRange_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = r2 | 256;
    L_0x0122:
        r2 = r12.reservedRange_;	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r3 = com.google.protobuf.DescriptorProtos.DescriptorProto.ReservedRange.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r3 = r13.readMessage(r3, r14);	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0022;
    L_0x0132:
        r3 = r13.readBytes();	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = r2 & 512;
        r5 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        if (r0 == r5) goto L_0x024e;
    L_0x013c:
        r0 = new com.google.protobuf.LazyStringArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r12.reservedName_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x01bf, IOException -> 0x0236 }
        r0 = r2 | 512;
    L_0x0145:
        r2 = r12.reservedName_;	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0249, IOException -> 0x0245, all -> 0x0241 }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0022;
    L_0x014f:
        r0 = r2 & 2;
        if (r0 != r6) goto L_0x015b;
    L_0x0153:
        r0 = r12.field_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r12.field_ = r0;
    L_0x015b:
        r0 = r2 & 8;
        if (r0 != r8) goto L_0x0167;
    L_0x015f:
        r0 = r12.nestedType_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r12.nestedType_ = r0;
    L_0x0167:
        r0 = r2 & 16;
        if (r0 != r9) goto L_0x0173;
    L_0x016b:
        r0 = r12.enumType_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r12.enumType_ = r0;
    L_0x0173:
        r0 = r2 & 32;
        if (r0 != r10) goto L_0x017f;
    L_0x0177:
        r0 = r12.extensionRange_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r12.extensionRange_ = r0;
    L_0x017f:
        r0 = r2 & 4;
        if (r0 != r7) goto L_0x018b;
    L_0x0183:
        r0 = r12.extension_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r12.extension_ = r0;
    L_0x018b:
        r0 = r2 & 64;
        r1 = 64;
        if (r0 != r1) goto L_0x0199;
    L_0x0191:
        r0 = r12.oneofDecl_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r12.oneofDecl_ = r0;
    L_0x0199:
        r0 = r2 & 256;
        r1 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        if (r0 != r1) goto L_0x01a7;
    L_0x019f:
        r0 = r12.reservedRange_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r12.reservedRange_ = r0;
    L_0x01a7:
        r0 = r2 & 512;
        r1 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        if (r0 != r1) goto L_0x01b5;
    L_0x01ad:
        r0 = r12.reservedName_;
        r0 = r0.getUnmodifiableView();
        r12.reservedName_ = r0;
    L_0x01b5:
        r0 = r4.build();
        r12.unknownFields = r0;
        r12.makeExtensionsImmutable();
        return;
    L_0x01bf:
        r0 = move-exception;
    L_0x01c0:
        r0 = r0.setUnfinishedMessage(r12);	 Catch:{ all -> 0x01c5 }
        throw r0;	 Catch:{ all -> 0x01c5 }
    L_0x01c5:
        r0 = move-exception;
    L_0x01c6:
        r1 = r2 & 2;
        if (r1 != r6) goto L_0x01d2;
    L_0x01ca:
        r1 = r12.field_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r12.field_ = r1;
    L_0x01d2:
        r1 = r2 & 8;
        if (r1 != r8) goto L_0x01de;
    L_0x01d6:
        r1 = r12.nestedType_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r12.nestedType_ = r1;
    L_0x01de:
        r1 = r2 & 16;
        if (r1 != r9) goto L_0x01ea;
    L_0x01e2:
        r1 = r12.enumType_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r12.enumType_ = r1;
    L_0x01ea:
        r1 = r2 & 32;
        if (r1 != r10) goto L_0x01f6;
    L_0x01ee:
        r1 = r12.extensionRange_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r12.extensionRange_ = r1;
    L_0x01f6:
        r1 = r2 & 4;
        if (r1 != r7) goto L_0x0202;
    L_0x01fa:
        r1 = r12.extension_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r12.extension_ = r1;
    L_0x0202:
        r1 = r2 & 64;
        r3 = 64;
        if (r1 != r3) goto L_0x0210;
    L_0x0208:
        r1 = r12.oneofDecl_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r12.oneofDecl_ = r1;
    L_0x0210:
        r1 = r2 & 256;
        r3 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        if (r1 != r3) goto L_0x021e;
    L_0x0216:
        r1 = r12.reservedRange_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r12.reservedRange_ = r1;
    L_0x021e:
        r1 = r2 & 512;
        r2 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        if (r1 != r2) goto L_0x022c;
    L_0x0224:
        r1 = r12.reservedName_;
        r1 = r1.getUnmodifiableView();
        r12.reservedName_ = r1;
    L_0x022c:
        r1 = r4.build();
        r12.unknownFields = r1;
        r12.makeExtensionsImmutable();
        throw r0;
    L_0x0236:
        r0 = move-exception;
    L_0x0237:
        r1 = new com.google.protobuf.InvalidProtocolBufferException;	 Catch:{ all -> 0x01c5 }
        r1.<init>(r0);	 Catch:{ all -> 0x01c5 }
        r0 = r1.setUnfinishedMessage(r12);	 Catch:{ all -> 0x01c5 }
        throw r0;	 Catch:{ all -> 0x01c5 }
    L_0x0241:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x01c6;
    L_0x0245:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x0237;
    L_0x0249:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x01c0;
    L_0x024e:
        r0 = r2;
        goto L_0x0145;
    L_0x0251:
        r0 = r2;
        goto L_0x0122;
    L_0x0254:
        r0 = r2;
        goto L_0x0103;
    L_0x0257:
        r3 = r0;
        goto L_0x00d3;
    L_0x025a:
        r0 = r2;
        goto L_0x00b5;
    L_0x025d:
        r0 = r2;
        goto L_0x0098;
    L_0x0260:
        r0 = r2;
        goto L_0x007c;
    L_0x0263:
        r0 = r2;
        goto L_0x0060;
    L_0x0266:
        r0 = r2;
        goto L_0x0044;
    L_0x0269:
        r0 = r1;
        r1 = r2;
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.DescriptorProtos$DescriptorProto.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
    }

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.access$2600();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$2700().ensureFieldAccessorsInitialized(DescriptorProtos$DescriptorProto.class, DescriptorProtos$DescriptorProto$Builder.class);
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

    public List<DescriptorProtos$FieldDescriptorProto> getFieldList() {
        return this.field_;
    }

    public List<? extends FieldDescriptorProtoOrBuilder> getFieldOrBuilderList() {
        return this.field_;
    }

    public int getFieldCount() {
        return this.field_.size();
    }

    public DescriptorProtos$FieldDescriptorProto getField(int i) {
        return (DescriptorProtos$FieldDescriptorProto) this.field_.get(i);
    }

    public FieldDescriptorProtoOrBuilder getFieldOrBuilder(int i) {
        return (FieldDescriptorProtoOrBuilder) this.field_.get(i);
    }

    public List<DescriptorProtos$FieldDescriptorProto> getExtensionList() {
        return this.extension_;
    }

    public List<? extends FieldDescriptorProtoOrBuilder> getExtensionOrBuilderList() {
        return this.extension_;
    }

    public int getExtensionCount() {
        return this.extension_.size();
    }

    public DescriptorProtos$FieldDescriptorProto getExtension(int i) {
        return (DescriptorProtos$FieldDescriptorProto) this.extension_.get(i);
    }

    public FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int i) {
        return (FieldDescriptorProtoOrBuilder) this.extension_.get(i);
    }

    public List<DescriptorProtos$DescriptorProto> getNestedTypeList() {
        return this.nestedType_;
    }

    public List<? extends DescriptorProtoOrBuilder> getNestedTypeOrBuilderList() {
        return this.nestedType_;
    }

    public int getNestedTypeCount() {
        return this.nestedType_.size();
    }

    public DescriptorProtos$DescriptorProto getNestedType(int i) {
        return (DescriptorProtos$DescriptorProto) this.nestedType_.get(i);
    }

    public DescriptorProtoOrBuilder getNestedTypeOrBuilder(int i) {
        return (DescriptorProtoOrBuilder) this.nestedType_.get(i);
    }

    public List<DescriptorProtos$EnumDescriptorProto> getEnumTypeList() {
        return this.enumType_;
    }

    public List<? extends EnumDescriptorProtoOrBuilder> getEnumTypeOrBuilderList() {
        return this.enumType_;
    }

    public int getEnumTypeCount() {
        return this.enumType_.size();
    }

    public DescriptorProtos$EnumDescriptorProto getEnumType(int i) {
        return (DescriptorProtos$EnumDescriptorProto) this.enumType_.get(i);
    }

    public EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int i) {
        return (EnumDescriptorProtoOrBuilder) this.enumType_.get(i);
    }

    public List<ExtensionRange> getExtensionRangeList() {
        return this.extensionRange_;
    }

    public List<? extends DescriptorProtos$DescriptorProto$ExtensionRangeOrBuilder> getExtensionRangeOrBuilderList() {
        return this.extensionRange_;
    }

    public int getExtensionRangeCount() {
        return this.extensionRange_.size();
    }

    public ExtensionRange getExtensionRange(int i) {
        return (ExtensionRange) this.extensionRange_.get(i);
    }

    public DescriptorProtos$DescriptorProto$ExtensionRangeOrBuilder getExtensionRangeOrBuilder(int i) {
        return (DescriptorProtos$DescriptorProto$ExtensionRangeOrBuilder) this.extensionRange_.get(i);
    }

    public List<DescriptorProtos$OneofDescriptorProto> getOneofDeclList() {
        return this.oneofDecl_;
    }

    public List<? extends OneofDescriptorProtoOrBuilder> getOneofDeclOrBuilderList() {
        return this.oneofDecl_;
    }

    public int getOneofDeclCount() {
        return this.oneofDecl_.size();
    }

    public DescriptorProtos$OneofDescriptorProto getOneofDecl(int i) {
        return (DescriptorProtos$OneofDescriptorProto) this.oneofDecl_.get(i);
    }

    public OneofDescriptorProtoOrBuilder getOneofDeclOrBuilder(int i) {
        return (OneofDescriptorProtoOrBuilder) this.oneofDecl_.get(i);
    }

    public boolean hasOptions() {
        return (this.bitField0_ & 2) == 2;
    }

    public DescriptorProtos$MessageOptions getOptions() {
        return this.options_ == null ? DescriptorProtos$MessageOptions.getDefaultInstance() : this.options_;
    }

    public MessageOptionsOrBuilder getOptionsOrBuilder() {
        return this.options_ == null ? DescriptorProtos$MessageOptions.getDefaultInstance() : this.options_;
    }

    public List<ReservedRange> getReservedRangeList() {
        return this.reservedRange_;
    }

    public List<? extends DescriptorProtos$DescriptorProto$ReservedRangeOrBuilder> getReservedRangeOrBuilderList() {
        return this.reservedRange_;
    }

    public int getReservedRangeCount() {
        return this.reservedRange_.size();
    }

    public ReservedRange getReservedRange(int i) {
        return (ReservedRange) this.reservedRange_.get(i);
    }

    public DescriptorProtos$DescriptorProto$ReservedRangeOrBuilder getReservedRangeOrBuilder(int i) {
        return (DescriptorProtos$DescriptorProto$ReservedRangeOrBuilder) this.reservedRange_.get(i);
    }

    public ProtocolStringList getReservedNameList() {
        return this.reservedName_;
    }

    public int getReservedNameCount() {
        return this.reservedName_.size();
    }

    public String getReservedName(int i) {
        return (String) this.reservedName_.get(i);
    }

    public ByteString getReservedNameBytes(int i) {
        return this.reservedName_.getByteString(i);
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == (byte) 1) {
            return true;
        }
        if (b == (byte) 0) {
            return false;
        }
        int i = 0;
        while (i < getFieldCount()) {
            if (getField(i).isInitialized()) {
                i++;
            } else {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        i = 0;
        while (i < getExtensionCount()) {
            if (getExtension(i).isInitialized()) {
                i++;
            } else {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        i = 0;
        while (i < getNestedTypeCount()) {
            if (getNestedType(i).isInitialized()) {
                i++;
            } else {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        i = 0;
        while (i < getEnumTypeCount()) {
            if (getEnumType(i).isInitialized()) {
                i++;
            } else {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        i = 0;
        while (i < getExtensionRangeCount()) {
            if (getExtensionRange(i).isInitialized()) {
                i++;
            } else {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        i = 0;
        while (i < getOneofDeclCount()) {
            if (getOneofDecl(i).isInitialized()) {
                i++;
            } else {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        if (!hasOptions() || getOptions().isInitialized()) {
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }
        this.memoizedIsInitialized = (byte) 0;
        return false;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        int i;
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        for (i = 0; i < this.field_.size(); i++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.field_.get(i));
        }
        for (i = 0; i < this.nestedType_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.nestedType_.get(i));
        }
        for (i = 0; i < this.enumType_.size(); i++) {
            codedOutputStream.writeMessage(4, (MessageLite) this.enumType_.get(i));
        }
        for (i = 0; i < this.extensionRange_.size(); i++) {
            codedOutputStream.writeMessage(5, (MessageLite) this.extensionRange_.get(i));
        }
        for (i = 0; i < this.extension_.size(); i++) {
            codedOutputStream.writeMessage(6, (MessageLite) this.extension_.get(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeMessage(7, getOptions());
        }
        for (i = 0; i < this.oneofDecl_.size(); i++) {
            codedOutputStream.writeMessage(8, (MessageLite) this.oneofDecl_.get(i));
        }
        for (i = 0; i < this.reservedRange_.size(); i++) {
            codedOutputStream.writeMessage(9, (MessageLite) this.reservedRange_.get(i));
        }
        while (i2 < this.reservedName_.size()) {
            GeneratedMessageV3.writeString(codedOutputStream, 10, this.reservedName_.getRaw(i2));
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
        if ((this.bitField0_ & 1) == 1) {
            i2 = GeneratedMessageV3.computeStringSize(1, this.name_) + 0;
        } else {
            i2 = 0;
        }
        int i4 = i2;
        for (i3 = 0; i3 < this.field_.size(); i3++) {
            i4 += CodedOutputStream.computeMessageSize(2, (MessageLite) this.field_.get(i3));
        }
        for (i3 = 0; i3 < this.nestedType_.size(); i3++) {
            i4 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.nestedType_.get(i3));
        }
        for (i3 = 0; i3 < this.enumType_.size(); i3++) {
            i4 += CodedOutputStream.computeMessageSize(4, (MessageLite) this.enumType_.get(i3));
        }
        for (i3 = 0; i3 < this.extensionRange_.size(); i3++) {
            i4 += CodedOutputStream.computeMessageSize(5, (MessageLite) this.extensionRange_.get(i3));
        }
        for (i3 = 0; i3 < this.extension_.size(); i3++) {
            i4 += CodedOutputStream.computeMessageSize(6, (MessageLite) this.extension_.get(i3));
        }
        if ((this.bitField0_ & 2) == 2) {
            i4 += CodedOutputStream.computeMessageSize(7, getOptions());
        }
        for (i3 = 0; i3 < this.oneofDecl_.size(); i3++) {
            i4 += CodedOutputStream.computeMessageSize(8, (MessageLite) this.oneofDecl_.get(i3));
        }
        for (i3 = 0; i3 < this.reservedRange_.size(); i3++) {
            i4 += CodedOutputStream.computeMessageSize(9, (MessageLite) this.reservedRange_.get(i3));
        }
        i2 = 0;
        while (i < this.reservedName_.size()) {
            i2 += computeStringSizeNoTag(this.reservedName_.getRaw(i));
            i++;
        }
        i2 = ((i2 + i4) + (getReservedNameList().size() * 1)) + this.unknownFields.getSerializedSize();
        this.memoizedSize = i2;
        return i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DescriptorProtos$DescriptorProto)) {
            return super.equals(obj);
        }
        DescriptorProtos$DescriptorProto descriptorProtos$DescriptorProto = (DescriptorProtos$DescriptorProto) obj;
        boolean z = hasName() == descriptorProtos$DescriptorProto.hasName();
        if (hasName()) {
            z = z && getName().equals(descriptorProtos$DescriptorProto.getName());
        }
        if (z && getFieldList().equals(descriptorProtos$DescriptorProto.getFieldList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getExtensionList().equals(descriptorProtos$DescriptorProto.getExtensionList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getNestedTypeList().equals(descriptorProtos$DescriptorProto.getNestedTypeList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getEnumTypeList().equals(descriptorProtos$DescriptorProto.getEnumTypeList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getExtensionRangeList().equals(descriptorProtos$DescriptorProto.getExtensionRangeList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getOneofDeclList().equals(descriptorProtos$DescriptorProto.getOneofDeclList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && hasOptions() == descriptorProtos$DescriptorProto.hasOptions()) {
            z = true;
        } else {
            z = false;
        }
        if (hasOptions()) {
            if (z && getOptions().equals(descriptorProtos$DescriptorProto.getOptions())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && getReservedRangeList().equals(descriptorProtos$DescriptorProto.getReservedRangeList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getReservedNameList().equals(descriptorProtos$DescriptorProto.getReservedNameList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.unknownFields.equals(descriptorProtos$DescriptorProto.unknownFields)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (hasName()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getName().hashCode();
        }
        if (getFieldCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getFieldList().hashCode();
        }
        if (getExtensionCount() > 0) {
            hashCode = (((hashCode * 37) + 6) * 53) + getExtensionList().hashCode();
        }
        if (getNestedTypeCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getNestedTypeList().hashCode();
        }
        if (getEnumTypeCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getEnumTypeList().hashCode();
        }
        if (getExtensionRangeCount() > 0) {
            hashCode = (((hashCode * 37) + 5) * 53) + getExtensionRangeList().hashCode();
        }
        if (getOneofDeclCount() > 0) {
            hashCode = (((hashCode * 37) + 8) * 53) + getOneofDeclList().hashCode();
        }
        if (hasOptions()) {
            hashCode = (((hashCode * 37) + 7) * 53) + getOptions().hashCode();
        }
        if (getReservedRangeCount() > 0) {
            hashCode = (((hashCode * 37) + 9) * 53) + getReservedRangeList().hashCode();
        }
        if (getReservedNameCount() > 0) {
            hashCode = (((hashCode * 37) + 10) * 53) + getReservedNameList().hashCode();
        }
        hashCode = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$DescriptorProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$DescriptorProto) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$DescriptorProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$DescriptorProto) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$DescriptorProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$DescriptorProto) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$DescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$DescriptorProto) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$DescriptorProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$DescriptorProto) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$DescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$DescriptorProto) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$DescriptorProto parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$DescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$DescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$DescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$DescriptorProto parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$DescriptorProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$DescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$DescriptorProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$DescriptorProto parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$DescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$DescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$DescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$DescriptorProto$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$DescriptorProto$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$DescriptorProto$Builder newBuilder(DescriptorProtos$DescriptorProto descriptorProtos$DescriptorProto) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$DescriptorProto);
    }

    public DescriptorProtos$DescriptorProto$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$DescriptorProto$Builder(null);
        }
        return new DescriptorProtos$DescriptorProto$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$DescriptorProto$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$DescriptorProto$Builder(builderParent, null);
    }

    public static DescriptorProtos$DescriptorProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$DescriptorProto> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$DescriptorProto> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$DescriptorProto getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
