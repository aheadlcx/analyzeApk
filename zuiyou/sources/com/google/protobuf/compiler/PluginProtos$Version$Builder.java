package com.google.protobuf.compiler;

import com.google.protobuf.AbstractMessage$Builder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.compiler.PluginProtos.VersionOrBuilder;
import java.io.IOException;

public final class PluginProtos$Version$Builder extends Builder<PluginProtos$Version$Builder> implements VersionOrBuilder {
    private int bitField0_;
    private int major_;
    private int minor_;
    private int patch_;
    private Object suffix_;

    public static final Descriptor getDescriptor() {
        return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return PluginProtos.internal_static_google_protobuf_compiler_Version_fieldAccessorTable.ensureFieldAccessorsInitialized(PluginProtos$Version.class, PluginProtos$Version$Builder.class);
    }

    private PluginProtos$Version$Builder() {
        this.suffix_ = "";
        maybeForceBuilderInitialization();
    }

    private PluginProtos$Version$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.suffix_ = "";
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (!PluginProtos$Version.access$400()) {
        }
    }

    public PluginProtos$Version$Builder clear() {
        super.clear();
        this.major_ = 0;
        this.bitField0_ &= -2;
        this.minor_ = 0;
        this.bitField0_ &= -3;
        this.patch_ = 0;
        this.bitField0_ &= -5;
        this.suffix_ = "";
        this.bitField0_ &= -9;
        return this;
    }

    public Descriptor getDescriptorForType() {
        return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor;
    }

    public PluginProtos$Version getDefaultInstanceForType() {
        return PluginProtos$Version.getDefaultInstance();
    }

    public PluginProtos$Version build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public PluginProtos$Version buildPartial() {
        int i = 1;
        PluginProtos$Version pluginProtos$Version = new PluginProtos$Version(this, null);
        int i2 = this.bitField0_;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        PluginProtos$Version.access$602(pluginProtos$Version, this.major_);
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        PluginProtos$Version.access$702(pluginProtos$Version, this.minor_);
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        PluginProtos$Version.access$802(pluginProtos$Version, this.patch_);
        if ((i2 & 8) == 8) {
            i |= 8;
        }
        PluginProtos$Version.access$902(pluginProtos$Version, this.suffix_);
        PluginProtos$Version.access$1002(pluginProtos$Version, i);
        onBuilt();
        return pluginProtos$Version;
    }

    public PluginProtos$Version$Builder clone() {
        return (PluginProtos$Version$Builder) super.clone();
    }

    public PluginProtos$Version$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (PluginProtos$Version$Builder) super.setField(fieldDescriptor, obj);
    }

    public PluginProtos$Version$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (PluginProtos$Version$Builder) super.clearField(fieldDescriptor);
    }

    public PluginProtos$Version$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (PluginProtos$Version$Builder) super.clearOneof(oneofDescriptor);
    }

    public PluginProtos$Version$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (PluginProtos$Version$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public PluginProtos$Version$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (PluginProtos$Version$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public PluginProtos$Version$Builder mergeFrom(Message message) {
        if (message instanceof PluginProtos$Version) {
            return mergeFrom((PluginProtos$Version) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public PluginProtos$Version$Builder mergeFrom(PluginProtos$Version pluginProtos$Version) {
        if (pluginProtos$Version != PluginProtos$Version.getDefaultInstance()) {
            if (pluginProtos$Version.hasMajor()) {
                setMajor(pluginProtos$Version.getMajor());
            }
            if (pluginProtos$Version.hasMinor()) {
                setMinor(pluginProtos$Version.getMinor());
            }
            if (pluginProtos$Version.hasPatch()) {
                setPatch(pluginProtos$Version.getPatch());
            }
            if (pluginProtos$Version.hasSuffix()) {
                this.bitField0_ |= 8;
                this.suffix_ = PluginProtos$Version.access$900(pluginProtos$Version);
                onChanged();
            }
            mergeUnknownFields(PluginProtos$Version.access$1100(pluginProtos$Version));
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        return true;
    }

    public PluginProtos$Version$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        PluginProtos$Version pluginProtos$Version;
        PluginProtos$Version pluginProtos$Version2;
        try {
            pluginProtos$Version2 = (PluginProtos$Version) PluginProtos$Version.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (pluginProtos$Version2 != null) {
                mergeFrom(pluginProtos$Version2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            pluginProtos$Version2 = (PluginProtos$Version) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            pluginProtos$Version = pluginProtos$Version2;
            th = th3;
            if (pluginProtos$Version != null) {
                mergeFrom(pluginProtos$Version);
            }
            throw th;
        }
    }

    public boolean hasMajor() {
        return (this.bitField0_ & 1) == 1;
    }

    public int getMajor() {
        return this.major_;
    }

    public PluginProtos$Version$Builder setMajor(int i) {
        this.bitField0_ |= 1;
        this.major_ = i;
        onChanged();
        return this;
    }

    public PluginProtos$Version$Builder clearMajor() {
        this.bitField0_ &= -2;
        this.major_ = 0;
        onChanged();
        return this;
    }

    public boolean hasMinor() {
        return (this.bitField0_ & 2) == 2;
    }

    public int getMinor() {
        return this.minor_;
    }

    public PluginProtos$Version$Builder setMinor(int i) {
        this.bitField0_ |= 2;
        this.minor_ = i;
        onChanged();
        return this;
    }

    public PluginProtos$Version$Builder clearMinor() {
        this.bitField0_ &= -3;
        this.minor_ = 0;
        onChanged();
        return this;
    }

    public boolean hasPatch() {
        return (this.bitField0_ & 4) == 4;
    }

    public int getPatch() {
        return this.patch_;
    }

    public PluginProtos$Version$Builder setPatch(int i) {
        this.bitField0_ |= 4;
        this.patch_ = i;
        onChanged();
        return this;
    }

    public PluginProtos$Version$Builder clearPatch() {
        this.bitField0_ &= -5;
        this.patch_ = 0;
        onChanged();
        return this;
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

    public PluginProtos$Version$Builder setSuffix(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 8;
        this.suffix_ = str;
        onChanged();
        return this;
    }

    public PluginProtos$Version$Builder clearSuffix() {
        this.bitField0_ &= -9;
        this.suffix_ = PluginProtos$Version.getDefaultInstance().getSuffix();
        onChanged();
        return this;
    }

    public PluginProtos$Version$Builder setSuffixBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 8;
        this.suffix_ = byteString;
        onChanged();
        return this;
    }

    public final PluginProtos$Version$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (PluginProtos$Version$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final PluginProtos$Version$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (PluginProtos$Version$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
