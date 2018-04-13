package com.google.protobuf.compiler;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.BuilderParent;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.compiler.PluginProtos.VersionOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class PluginProtos$Version extends GeneratedMessageV3 implements VersionOrBuilder {
    private static final PluginProtos$Version DEFAULT_INSTANCE = new PluginProtos$Version();
    public static final int MAJOR_FIELD_NUMBER = 1;
    public static final int MINOR_FIELD_NUMBER = 2;
    @Deprecated
    public static final Parser<PluginProtos$Version> PARSER = new PluginProtos$Version$1();
    public static final int PATCH_FIELD_NUMBER = 3;
    public static final int SUFFIX_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private int major_;
    private byte memoizedIsInitialized;
    private int minor_;
    private int patch_;
    private volatile Object suffix_;

    private PluginProtos$Version(Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private PluginProtos$Version() {
        this.memoizedIsInitialized = (byte) -1;
        this.major_ = 0;
        this.minor_ = 0;
        this.patch_ = 0;
        this.suffix_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private PluginProtos$Version(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.major_ = codedInputStream.readInt32();
                        break;
                    case 16:
                        this.bitField0_ |= 2;
                        this.minor_ = codedInputStream.readInt32();
                        break;
                    case 24:
                        this.bitField0_ |= 4;
                        this.patch_ = codedInputStream.readInt32();
                        break;
                    case 34:
                        ByteString readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 8;
                        this.suffix_ = readBytes;
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
        return PluginProtos.access$000();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return PluginProtos.access$100().ensureFieldAccessorsInitialized(PluginProtos$Version.class, PluginProtos$Version$Builder.class);
    }

    public boolean hasMajor() {
        return (this.bitField0_ & 1) == 1;
    }

    public int getMajor() {
        return this.major_;
    }

    public boolean hasMinor() {
        return (this.bitField0_ & 2) == 2;
    }

    public int getMinor() {
        return this.minor_;
    }

    public boolean hasPatch() {
        return (this.bitField0_ & 4) == 4;
    }

    public int getPatch() {
        return this.patch_;
    }

    public boolean hasSuffix() {
        return (this.bitField0_ & 8) == 8;
    }

    public String getSuffix() {
        Object obj = this.suffix_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.suffix_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getSuffixBytes() {
        Object obj = this.suffix_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.suffix_ = copyFromUtf8;
        return copyFromUtf8;
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
            codedOutputStream.writeInt32(1, this.major_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(2, this.minor_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeInt32(3, this.patch_);
        }
        if ((this.bitField0_ & 8) == 8) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.suffix_);
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
            i = 0 + CodedOutputStream.computeInt32Size(1, this.major_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i += CodedOutputStream.computeInt32Size(2, this.minor_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i += CodedOutputStream.computeInt32Size(3, this.patch_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i += GeneratedMessageV3.computeStringSize(4, this.suffix_);
        }
        i += this.unknownFields.getSerializedSize();
        this.memoizedSize = i;
        return i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PluginProtos$Version)) {
            return super.equals(obj);
        }
        PluginProtos$Version pluginProtos$Version = (PluginProtos$Version) obj;
        boolean z = hasMajor() == pluginProtos$Version.hasMajor();
        if (hasMajor()) {
            z = z && getMajor() == pluginProtos$Version.getMajor();
        }
        if (z && hasMinor() == pluginProtos$Version.hasMinor()) {
            z = true;
        } else {
            z = false;
        }
        if (hasMinor()) {
            if (z && getMinor() == pluginProtos$Version.getMinor()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasPatch() == pluginProtos$Version.hasPatch()) {
            z = true;
        } else {
            z = false;
        }
        if (hasPatch()) {
            if (z && getPatch() == pluginProtos$Version.getPatch()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasSuffix() == pluginProtos$Version.hasSuffix()) {
            z = true;
        } else {
            z = false;
        }
        if (hasSuffix()) {
            if (z && getSuffix().equals(pluginProtos$Version.getSuffix())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && this.unknownFields.equals(pluginProtos$Version.unknownFields)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (hasMajor()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getMajor();
        }
        if (hasMinor()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getMinor();
        }
        if (hasPatch()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getPatch();
        }
        if (hasSuffix()) {
            hashCode = (((hashCode * 37) + 4) * 53) + getSuffix().hashCode();
        }
        hashCode = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static PluginProtos$Version parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (PluginProtos$Version) PARSER.parseFrom(byteBuffer);
    }

    public static PluginProtos$Version parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (PluginProtos$Version) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static PluginProtos$Version parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (PluginProtos$Version) PARSER.parseFrom(byteString);
    }

    public static PluginProtos$Version parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (PluginProtos$Version) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static PluginProtos$Version parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (PluginProtos$Version) PARSER.parseFrom(bArr);
    }

    public static PluginProtos$Version parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (PluginProtos$Version) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static PluginProtos$Version parseFrom(InputStream inputStream) throws IOException {
        return (PluginProtos$Version) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static PluginProtos$Version parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PluginProtos$Version) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static PluginProtos$Version parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (PluginProtos$Version) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static PluginProtos$Version parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PluginProtos$Version) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static PluginProtos$Version parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (PluginProtos$Version) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static PluginProtos$Version parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PluginProtos$Version) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public PluginProtos$Version$Builder newBuilderForType() {
        return newBuilder();
    }

    public static PluginProtos$Version$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static PluginProtos$Version$Builder newBuilder(PluginProtos$Version pluginProtos$Version) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(pluginProtos$Version);
    }

    public PluginProtos$Version$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new PluginProtos$Version$Builder(null);
        }
        return new PluginProtos$Version$Builder(null).mergeFrom(this);
    }

    protected PluginProtos$Version$Builder newBuilderForType(BuilderParent builderParent) {
        return new PluginProtos$Version$Builder(builderParent, null);
    }

    public static PluginProtos$Version getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PluginProtos$Version> parser() {
        return PARSER;
    }

    public Parser<PluginProtos$Version> getParserForType() {
        return PARSER;
    }

    public PluginProtos$Version getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
