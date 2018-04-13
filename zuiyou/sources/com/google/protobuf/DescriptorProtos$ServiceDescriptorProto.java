package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.MethodDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.ServiceDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.ServiceOptionsOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.BuilderParent;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$ServiceDescriptorProto extends GeneratedMessageV3 implements ServiceDescriptorProtoOrBuilder {
    private static final DescriptorProtos$ServiceDescriptorProto DEFAULT_INSTANCE = new DescriptorProtos$ServiceDescriptorProto();
    public static final int METHOD_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int OPTIONS_FIELD_NUMBER = 3;
    @Deprecated
    public static final Parser<DescriptorProtos$ServiceDescriptorProto> PARSER = new DescriptorProtos$ServiceDescriptorProto$1();
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private byte memoizedIsInitialized;
    private List<DescriptorProtos$MethodDescriptorProto> method_;
    private volatile Object name_;
    private DescriptorProtos$ServiceOptions options_;

    private DescriptorProtos$ServiceDescriptorProto(Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$ServiceDescriptorProto() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.method_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DescriptorProtos$ServiceDescriptorProto(com.google.protobuf.CodedInputStream r9, com.google.protobuf.ExtensionRegistryLite r10) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
        r8 = this;
        r1 = 1;
        r2 = 0;
        r6 = 2;
        r8.<init>();
        r5 = com.google.protobuf.UnknownFieldSet.newBuilder();
        r3 = r2;
    L_0x000b:
        if (r2 != 0) goto L_0x007b;
    L_0x000d:
        r0 = r9.readTag();	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        switch(r0) {
            case 0: goto L_0x001f;
            case 10: goto L_0x0022;
            case 18: goto L_0x0031;
            case 26: goto L_0x004d;
            default: goto L_0x0014;
        };	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
    L_0x0014:
        r0 = r8.parseUnknownField(r9, r5, r10, r0);	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        if (r0 != 0) goto L_0x00ca;
    L_0x001a:
        r0 = r1;
        r2 = r3;
    L_0x001c:
        r3 = r2;
        r2 = r0;
        goto L_0x000b;
    L_0x001f:
        r0 = r1;
        r2 = r3;
        goto L_0x001c;
    L_0x0022:
        r0 = r9.readBytes();	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r4 = r8.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r4 = r4 | 1;
        r8.bitField0_ = r4;	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r8.name_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r0 = r2;
        r2 = r3;
        goto L_0x001c;
    L_0x0031:
        r0 = r3 & 2;
        if (r0 == r6) goto L_0x00c7;
    L_0x0035:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r8.method_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r0 = r3 | 2;
    L_0x003e:
        r3 = r8.method_;	 Catch:{ InvalidProtocolBufferException -> 0x00c1, IOException -> 0x00bd, all -> 0x00b9 }
        r4 = com.google.protobuf.DescriptorProtos$MethodDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x00c1, IOException -> 0x00bd, all -> 0x00b9 }
        r4 = r9.readMessage(r4, r10);	 Catch:{ InvalidProtocolBufferException -> 0x00c1, IOException -> 0x00bd, all -> 0x00b9 }
        r3.add(r4);	 Catch:{ InvalidProtocolBufferException -> 0x00c1, IOException -> 0x00bd, all -> 0x00b9 }
        r7 = r2;
        r2 = r0;
        r0 = r7;
        goto L_0x001c;
    L_0x004d:
        r0 = 0;
        r4 = r8.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r4 = r4 & 2;
        if (r4 != r6) goto L_0x00c5;
    L_0x0054:
        r0 = r8.options_;	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r0 = r0.toBuilder();	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r4 = r0;
    L_0x005b:
        r0 = com.google.protobuf.DescriptorProtos$ServiceOptions.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r0 = r9.readMessage(r0, r10);	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r0 = (com.google.protobuf.DescriptorProtos$ServiceOptions) r0;	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r8.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        if (r4 == 0) goto L_0x0072;
    L_0x0067:
        r0 = r8.options_;	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r4.mergeFrom(r0);	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r0 = r4.buildPartial();	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r8.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
    L_0x0072:
        r0 = r8.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r0 = r0 | 2;
        r8.bitField0_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0091, IOException -> 0x00ae }
        r0 = r2;
        r2 = r3;
        goto L_0x001c;
    L_0x007b:
        r0 = r3 & 2;
        if (r0 != r6) goto L_0x0087;
    L_0x007f:
        r0 = r8.method_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r8.method_ = r0;
    L_0x0087:
        r0 = r5.build();
        r8.unknownFields = r0;
        r8.makeExtensionsImmutable();
        return;
    L_0x0091:
        r0 = move-exception;
    L_0x0092:
        r0 = r0.setUnfinishedMessage(r8);	 Catch:{ all -> 0x0097 }
        throw r0;	 Catch:{ all -> 0x0097 }
    L_0x0097:
        r0 = move-exception;
    L_0x0098:
        r1 = r3 & 2;
        if (r1 != r6) goto L_0x00a4;
    L_0x009c:
        r1 = r8.method_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r8.method_ = r1;
    L_0x00a4:
        r1 = r5.build();
        r8.unknownFields = r1;
        r8.makeExtensionsImmutable();
        throw r0;
    L_0x00ae:
        r0 = move-exception;
    L_0x00af:
        r1 = new com.google.protobuf.InvalidProtocolBufferException;	 Catch:{ all -> 0x0097 }
        r1.<init>(r0);	 Catch:{ all -> 0x0097 }
        r0 = r1.setUnfinishedMessage(r8);	 Catch:{ all -> 0x0097 }
        throw r0;	 Catch:{ all -> 0x0097 }
    L_0x00b9:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x0098;
    L_0x00bd:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x00af;
    L_0x00c1:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x0092;
    L_0x00c5:
        r4 = r0;
        goto L_0x005b;
    L_0x00c7:
        r0 = r3;
        goto L_0x003e;
    L_0x00ca:
        r0 = r2;
        r2 = r3;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.DescriptorProtos$ServiceDescriptorProto.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
    }

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.access$11500();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$11600().ensureFieldAccessorsInitialized(DescriptorProtos$ServiceDescriptorProto.class, DescriptorProtos$ServiceDescriptorProto$Builder.class);
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

    public List<DescriptorProtos$MethodDescriptorProto> getMethodList() {
        return this.method_;
    }

    public List<? extends MethodDescriptorProtoOrBuilder> getMethodOrBuilderList() {
        return this.method_;
    }

    public int getMethodCount() {
        return this.method_.size();
    }

    public DescriptorProtos$MethodDescriptorProto getMethod(int i) {
        return (DescriptorProtos$MethodDescriptorProto) this.method_.get(i);
    }

    public MethodDescriptorProtoOrBuilder getMethodOrBuilder(int i) {
        return (MethodDescriptorProtoOrBuilder) this.method_.get(i);
    }

    public boolean hasOptions() {
        return (this.bitField0_ & 2) == 2;
    }

    public DescriptorProtos$ServiceOptions getOptions() {
        return this.options_ == null ? DescriptorProtos$ServiceOptions.getDefaultInstance() : this.options_;
    }

    public ServiceOptionsOrBuilder getOptionsOrBuilder() {
        return this.options_ == null ? DescriptorProtos$ServiceOptions.getDefaultInstance() : this.options_;
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
        while (i < getMethodCount()) {
            if (getMethod(i).isInitialized()) {
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
        if ((this.bitField0_ & 1) == 1) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        for (int i = 0; i < this.method_.size(); i++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.method_.get(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeMessage(3, getOptions());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = 0;
        int i2 = this.memoizedSize;
        if (i2 != -1) {
            return i2;
        }
        if ((this.bitField0_ & 1) == 1) {
            i2 = GeneratedMessageV3.computeStringSize(1, this.name_) + 0;
        } else {
            i2 = 0;
        }
        int i3 = i2;
        while (i < this.method_.size()) {
            i++;
            i3 = CodedOutputStream.computeMessageSize(2, (MessageLite) this.method_.get(i)) + i3;
        }
        if ((this.bitField0_ & 2) == 2) {
            i3 += CodedOutputStream.computeMessageSize(3, getOptions());
        }
        i2 = this.unknownFields.getSerializedSize() + i3;
        this.memoizedSize = i2;
        return i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DescriptorProtos$ServiceDescriptorProto)) {
            return super.equals(obj);
        }
        DescriptorProtos$ServiceDescriptorProto descriptorProtos$ServiceDescriptorProto = (DescriptorProtos$ServiceDescriptorProto) obj;
        boolean z = hasName() == descriptorProtos$ServiceDescriptorProto.hasName();
        if (hasName()) {
            z = z && getName().equals(descriptorProtos$ServiceDescriptorProto.getName());
        }
        if (z && getMethodList().equals(descriptorProtos$ServiceDescriptorProto.getMethodList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && hasOptions() == descriptorProtos$ServiceDescriptorProto.hasOptions()) {
            z = true;
        } else {
            z = false;
        }
        if (hasOptions()) {
            if (z && getOptions().equals(descriptorProtos$ServiceDescriptorProto.getOptions())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && this.unknownFields.equals(descriptorProtos$ServiceDescriptorProto.unknownFields)) {
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
        if (getMethodCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getMethodList().hashCode();
        }
        if (hasOptions()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getOptions().hashCode();
        }
        hashCode = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$ServiceDescriptorProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ServiceDescriptorProto) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$ServiceDescriptorProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ServiceDescriptorProto) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$ServiceDescriptorProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ServiceDescriptorProto) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$ServiceDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ServiceDescriptorProto) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$ServiceDescriptorProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ServiceDescriptorProto) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$ServiceDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$ServiceDescriptorProto) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$ServiceDescriptorProto parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$ServiceDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$ServiceDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$ServiceDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$ServiceDescriptorProto parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$ServiceDescriptorProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$ServiceDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$ServiceDescriptorProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$ServiceDescriptorProto parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$ServiceDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$ServiceDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$ServiceDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$ServiceDescriptorProto$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$ServiceDescriptorProto$Builder newBuilder(DescriptorProtos$ServiceDescriptorProto descriptorProtos$ServiceDescriptorProto) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$ServiceDescriptorProto);
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$ServiceDescriptorProto$Builder(null);
        }
        return new DescriptorProtos$ServiceDescriptorProto$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$ServiceDescriptorProto$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$ServiceDescriptorProto$Builder(builderParent, null);
    }

    public static DescriptorProtos$ServiceDescriptorProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$ServiceDescriptorProto> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$ServiceDescriptorProto> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$ServiceDescriptorProto getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
