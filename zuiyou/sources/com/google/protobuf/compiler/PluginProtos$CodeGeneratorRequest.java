package com.google.protobuf.compiler;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.DescriptorProtos$FileDescriptorProto;
import com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.BuilderParent;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder;
import com.google.protobuf.compiler.PluginProtos.VersionOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class PluginProtos$CodeGeneratorRequest extends GeneratedMessageV3 implements CodeGeneratorRequestOrBuilder {
    public static final int COMPILER_VERSION_FIELD_NUMBER = 3;
    private static final PluginProtos$CodeGeneratorRequest DEFAULT_INSTANCE = new PluginProtos$CodeGeneratorRequest();
    public static final int FILE_TO_GENERATE_FIELD_NUMBER = 1;
    public static final int PARAMETER_FIELD_NUMBER = 2;
    @Deprecated
    public static final Parser<PluginProtos$CodeGeneratorRequest> PARSER = new PluginProtos$CodeGeneratorRequest$1();
    public static final int PROTO_FILE_FIELD_NUMBER = 15;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private PluginProtos$Version compilerVersion_;
    private LazyStringList fileToGenerate_;
    private byte memoizedIsInitialized;
    private volatile Object parameter_;
    private List<DescriptorProtos$FileDescriptorProto> protoFile_;

    private PluginProtos$CodeGeneratorRequest(Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private PluginProtos$CodeGeneratorRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.fileToGenerate_ = LazyStringArrayList.EMPTY;
        this.parameter_ = "";
        this.protoFile_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private PluginProtos$CodeGeneratorRequest(com.google.protobuf.CodedInputStream r10, com.google.protobuf.ExtensionRegistryLite r11) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
        r9 = this;
        r2 = 0;
        r7 = 4;
        r1 = 1;
        r9.<init>();
        r5 = com.google.protobuf.UnknownFieldSet.newBuilder();
        r3 = r2;
    L_0x000b:
        if (r2 != 0) goto L_0x0096;
    L_0x000d:
        r0 = r10.readTag();	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        switch(r0) {
            case 0: goto L_0x001f;
            case 10: goto L_0x0022;
            case 18: goto L_0x003c;
            case 26: goto L_0x004b;
            case 122: goto L_0x007a;
            default: goto L_0x0014;
        };	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
    L_0x0014:
        r0 = r9.parseUnknownField(r10, r5, r11, r0);	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        if (r0 != 0) goto L_0x0100;
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
        r4 = r10.readBytes();	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r0 = r3 & 1;
        if (r0 == r1) goto L_0x00fd;
    L_0x002a:
        r0 = new com.google.protobuf.LazyStringArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r9.fileToGenerate_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r0 = r3 | 1;
    L_0x0033:
        r3 = r9.fileToGenerate_;	 Catch:{ InvalidProtocolBufferException -> 0x00f4, IOException -> 0x00f0, all -> 0x00ec }
        r3.add(r4);	 Catch:{ InvalidProtocolBufferException -> 0x00f4, IOException -> 0x00f0, all -> 0x00ec }
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x001c;
    L_0x003c:
        r0 = r10.readBytes();	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r4 = r9.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r4 = r4 | 1;
        r9.bitField0_ = r4;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r9.parameter_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r0 = r2;
        r2 = r3;
        goto L_0x001c;
    L_0x004b:
        r0 = 0;
        r4 = r9.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r4 = r4 & 2;
        r6 = 2;
        if (r4 != r6) goto L_0x00fa;
    L_0x0053:
        r0 = r9.compilerVersion_;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r0 = r0.toBuilder();	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r4 = r0;
    L_0x005a:
        r0 = com.google.protobuf.compiler.PluginProtos$Version.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r0 = r10.readMessage(r0, r11);	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r0 = (com.google.protobuf.compiler.PluginProtos$Version) r0;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r9.compilerVersion_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        if (r4 == 0) goto L_0x0071;
    L_0x0066:
        r0 = r9.compilerVersion_;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r4.mergeFrom(r0);	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r0 = r4.buildPartial();	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r9.compilerVersion_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
    L_0x0071:
        r0 = r9.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r0 = r0 | 2;
        r9.bitField0_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r0 = r2;
        r2 = r3;
        goto L_0x001c;
    L_0x007a:
        r0 = r3 & 4;
        if (r0 == r7) goto L_0x00f8;
    L_0x007e:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r9.protoFile_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00b8, IOException -> 0x00e1 }
        r0 = r3 | 4;
    L_0x0087:
        r3 = r9.protoFile_;	 Catch:{ InvalidProtocolBufferException -> 0x00f4, IOException -> 0x00f0, all -> 0x00ec }
        r4 = com.google.protobuf.DescriptorProtos$FileDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x00f4, IOException -> 0x00f0, all -> 0x00ec }
        r4 = r10.readMessage(r4, r11);	 Catch:{ InvalidProtocolBufferException -> 0x00f4, IOException -> 0x00f0, all -> 0x00ec }
        r3.add(r4);	 Catch:{ InvalidProtocolBufferException -> 0x00f4, IOException -> 0x00f0, all -> 0x00ec }
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x001c;
    L_0x0096:
        r0 = r3 & 1;
        if (r0 != r1) goto L_0x00a2;
    L_0x009a:
        r0 = r9.fileToGenerate_;
        r0 = r0.getUnmodifiableView();
        r9.fileToGenerate_ = r0;
    L_0x00a2:
        r0 = r3 & 4;
        if (r0 != r7) goto L_0x00ae;
    L_0x00a6:
        r0 = r9.protoFile_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r9.protoFile_ = r0;
    L_0x00ae:
        r0 = r5.build();
        r9.unknownFields = r0;
        r9.makeExtensionsImmutable();
        return;
    L_0x00b8:
        r0 = move-exception;
    L_0x00b9:
        r0 = r0.setUnfinishedMessage(r9);	 Catch:{ all -> 0x00be }
        throw r0;	 Catch:{ all -> 0x00be }
    L_0x00be:
        r0 = move-exception;
    L_0x00bf:
        r2 = r3 & 1;
        if (r2 != r1) goto L_0x00cb;
    L_0x00c3:
        r1 = r9.fileToGenerate_;
        r1 = r1.getUnmodifiableView();
        r9.fileToGenerate_ = r1;
    L_0x00cb:
        r1 = r3 & 4;
        if (r1 != r7) goto L_0x00d7;
    L_0x00cf:
        r1 = r9.protoFile_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r9.protoFile_ = r1;
    L_0x00d7:
        r1 = r5.build();
        r9.unknownFields = r1;
        r9.makeExtensionsImmutable();
        throw r0;
    L_0x00e1:
        r0 = move-exception;
    L_0x00e2:
        r2 = new com.google.protobuf.InvalidProtocolBufferException;	 Catch:{ all -> 0x00be }
        r2.<init>(r0);	 Catch:{ all -> 0x00be }
        r0 = r2.setUnfinishedMessage(r9);	 Catch:{ all -> 0x00be }
        throw r0;	 Catch:{ all -> 0x00be }
    L_0x00ec:
        r2 = move-exception;
        r3 = r0;
        r0 = r2;
        goto L_0x00bf;
    L_0x00f0:
        r2 = move-exception;
        r3 = r0;
        r0 = r2;
        goto L_0x00e2;
    L_0x00f4:
        r2 = move-exception;
        r3 = r0;
        r0 = r2;
        goto L_0x00b9;
    L_0x00f8:
        r0 = r3;
        goto L_0x0087;
    L_0x00fa:
        r4 = r0;
        goto L_0x005a;
    L_0x00fd:
        r0 = r3;
        goto L_0x0033;
    L_0x0100:
        r0 = r2;
        r2 = r3;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.compiler.PluginProtos$CodeGeneratorRequest.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
    }

    public static final Descriptor getDescriptor() {
        return PluginProtos.access$1300();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return PluginProtos.access$1400().ensureFieldAccessorsInitialized(PluginProtos$CodeGeneratorRequest.class, PluginProtos$CodeGeneratorRequest$Builder.class);
    }

    public ProtocolStringList getFileToGenerateList() {
        return this.fileToGenerate_;
    }

    public int getFileToGenerateCount() {
        return this.fileToGenerate_.size();
    }

    public String getFileToGenerate(int i) {
        return (String) this.fileToGenerate_.get(i);
    }

    public ByteString getFileToGenerateBytes(int i) {
        return this.fileToGenerate_.getByteString(i);
    }

    public boolean hasParameter() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getParameter() {
        Object obj = this.parameter_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.parameter_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getParameterBytes() {
        Object obj = this.parameter_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.parameter_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public List<DescriptorProtos$FileDescriptorProto> getProtoFileList() {
        return this.protoFile_;
    }

    public List<? extends FileDescriptorProtoOrBuilder> getProtoFileOrBuilderList() {
        return this.protoFile_;
    }

    public int getProtoFileCount() {
        return this.protoFile_.size();
    }

    public DescriptorProtos$FileDescriptorProto getProtoFile(int i) {
        return (DescriptorProtos$FileDescriptorProto) this.protoFile_.get(i);
    }

    public FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int i) {
        return (FileDescriptorProtoOrBuilder) this.protoFile_.get(i);
    }

    public boolean hasCompilerVersion() {
        return (this.bitField0_ & 2) == 2;
    }

    public PluginProtos$Version getCompilerVersion() {
        return this.compilerVersion_ == null ? PluginProtos$Version.getDefaultInstance() : this.compilerVersion_;
    }

    public VersionOrBuilder getCompilerVersionOrBuilder() {
        return this.compilerVersion_ == null ? PluginProtos$Version.getDefaultInstance() : this.compilerVersion_;
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
        while (i < getProtoFileCount()) {
            if (getProtoFile(i).isInitialized()) {
                i++;
            } else {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        int i = 0;
        for (int i2 = 0; i2 < this.fileToGenerate_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.fileToGenerate_.getRaw(i2));
        }
        if ((this.bitField0_ & 1) == 1) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.parameter_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeMessage(3, getCompilerVersion());
        }
        while (i < this.protoFile_.size()) {
            codedOutputStream.writeMessage(15, (MessageLite) this.protoFile_.get(i));
            i++;
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = 0;
        int i2 = this.memoizedSize;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (i2 = 0; i2 < this.fileToGenerate_.size(); i2++) {
            i3 += computeStringSizeNoTag(this.fileToGenerate_.getRaw(i2));
        }
        i2 = (0 + i3) + (getFileToGenerateList().size() * 1);
        if ((this.bitField0_ & 1) == 1) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.parameter_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeMessageSize(3, getCompilerVersion());
        }
        i3 = i2;
        while (i < this.protoFile_.size()) {
            i++;
            i3 = CodedOutputStream.computeMessageSize(15, (MessageLite) this.protoFile_.get(i)) + i3;
        }
        i2 = this.unknownFields.getSerializedSize() + i3;
        this.memoizedSize = i2;
        return i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PluginProtos$CodeGeneratorRequest)) {
            return super.equals(obj);
        }
        boolean z;
        PluginProtos$CodeGeneratorRequest pluginProtos$CodeGeneratorRequest = (PluginProtos$CodeGeneratorRequest) obj;
        if ((getFileToGenerateList().equals(pluginProtos$CodeGeneratorRequest.getFileToGenerateList())) && hasParameter() == pluginProtos$CodeGeneratorRequest.hasParameter()) {
            z = true;
        } else {
            z = false;
        }
        if (hasParameter()) {
            if (z && getParameter().equals(pluginProtos$CodeGeneratorRequest.getParameter())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && getProtoFileList().equals(pluginProtos$CodeGeneratorRequest.getProtoFileList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && hasCompilerVersion() == pluginProtos$CodeGeneratorRequest.hasCompilerVersion()) {
            z = true;
        } else {
            z = false;
        }
        if (hasCompilerVersion()) {
            if (z && getCompilerVersion().equals(pluginProtos$CodeGeneratorRequest.getCompilerVersion())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && this.unknownFields.equals(pluginProtos$CodeGeneratorRequest.unknownFields)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (getFileToGenerateCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getFileToGenerateList().hashCode();
        }
        if (hasParameter()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getParameter().hashCode();
        }
        if (getProtoFileCount() > 0) {
            hashCode = (((hashCode * 37) + 15) * 53) + getProtoFileList().hashCode();
        }
        if (hasCompilerVersion()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getCompilerVersion().hashCode();
        }
        hashCode = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static PluginProtos$CodeGeneratorRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (PluginProtos$CodeGeneratorRequest) PARSER.parseFrom(byteBuffer);
    }

    public static PluginProtos$CodeGeneratorRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (PluginProtos$CodeGeneratorRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static PluginProtos$CodeGeneratorRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (PluginProtos$CodeGeneratorRequest) PARSER.parseFrom(byteString);
    }

    public static PluginProtos$CodeGeneratorRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (PluginProtos$CodeGeneratorRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static PluginProtos$CodeGeneratorRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (PluginProtos$CodeGeneratorRequest) PARSER.parseFrom(bArr);
    }

    public static PluginProtos$CodeGeneratorRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (PluginProtos$CodeGeneratorRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static PluginProtos$CodeGeneratorRequest parseFrom(InputStream inputStream) throws IOException {
        return (PluginProtos$CodeGeneratorRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static PluginProtos$CodeGeneratorRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PluginProtos$CodeGeneratorRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static PluginProtos$CodeGeneratorRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (PluginProtos$CodeGeneratorRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static PluginProtos$CodeGeneratorRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PluginProtos$CodeGeneratorRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static PluginProtos$CodeGeneratorRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (PluginProtos$CodeGeneratorRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static PluginProtos$CodeGeneratorRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PluginProtos$CodeGeneratorRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public PluginProtos$CodeGeneratorRequest$Builder newBuilderForType() {
        return newBuilder();
    }

    public static PluginProtos$CodeGeneratorRequest$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static PluginProtos$CodeGeneratorRequest$Builder newBuilder(PluginProtos$CodeGeneratorRequest pluginProtos$CodeGeneratorRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(pluginProtos$CodeGeneratorRequest);
    }

    public PluginProtos$CodeGeneratorRequest$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new PluginProtos$CodeGeneratorRequest$Builder(null);
        }
        return new PluginProtos$CodeGeneratorRequest$Builder(null).mergeFrom(this);
    }

    protected PluginProtos$CodeGeneratorRequest$Builder newBuilderForType(BuilderParent builderParent) {
        return new PluginProtos$CodeGeneratorRequest$Builder(builderParent, null);
    }

    public static PluginProtos$CodeGeneratorRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PluginProtos$CodeGeneratorRequest> parser() {
        return PARSER;
    }

    public Parser<PluginProtos$CodeGeneratorRequest> getParserForType() {
        return PARSER;
    }

    public PluginProtos$CodeGeneratorRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
