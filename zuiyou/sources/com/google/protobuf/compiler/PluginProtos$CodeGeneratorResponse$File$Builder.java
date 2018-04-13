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
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import java.io.IOException;

public final class PluginProtos$CodeGeneratorResponse$File$Builder extends Builder<PluginProtos$CodeGeneratorResponse$File$Builder> implements PluginProtos$CodeGeneratorResponse$FileOrBuilder {
    private int bitField0_;
    private Object content_;
    private Object insertionPoint_;
    private Object name_;

    public static final Descriptor getDescriptor() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable.ensureFieldAccessorsInitialized(File.class, PluginProtos$CodeGeneratorResponse$File$Builder.class);
    }

    private PluginProtos$CodeGeneratorResponse$File$Builder() {
        this.name_ = "";
        this.insertionPoint_ = "";
        this.content_ = "";
        maybeForceBuilderInitialization();
    }

    private PluginProtos$CodeGeneratorResponse$File$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.name_ = "";
        this.insertionPoint_ = "";
        this.content_ = "";
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (!File.access$3300()) {
        }
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder clear() {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        this.insertionPoint_ = "";
        this.bitField0_ &= -3;
        this.content_ = "";
        this.bitField0_ &= -5;
        return this;
    }

    public Descriptor getDescriptorForType() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor;
    }

    public File getDefaultInstanceForType() {
        return File.getDefaultInstance();
    }

    public File build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public File buildPartial() {
        int i = 1;
        File file = new File(this, null);
        int i2 = this.bitField0_;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        File.access$3502(file, this.name_);
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        File.access$3602(file, this.insertionPoint_);
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        File.access$3702(file, this.content_);
        File.access$3802(file, i);
        onBuilt();
        return file;
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder clone() {
        return (PluginProtos$CodeGeneratorResponse$File$Builder) super.clone();
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (PluginProtos$CodeGeneratorResponse$File$Builder) super.setField(fieldDescriptor, obj);
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (PluginProtos$CodeGeneratorResponse$File$Builder) super.clearField(fieldDescriptor);
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (PluginProtos$CodeGeneratorResponse$File$Builder) super.clearOneof(oneofDescriptor);
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (PluginProtos$CodeGeneratorResponse$File$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (PluginProtos$CodeGeneratorResponse$File$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder mergeFrom(Message message) {
        if (message instanceof File) {
            return mergeFrom((File) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder mergeFrom(File file) {
        if (file != File.getDefaultInstance()) {
            if (file.hasName()) {
                this.bitField0_ |= 1;
                this.name_ = File.access$3500(file);
                onChanged();
            }
            if (file.hasInsertionPoint()) {
                this.bitField0_ |= 2;
                this.insertionPoint_ = File.access$3600(file);
                onChanged();
            }
            if (file.hasContent()) {
                this.bitField0_ |= 4;
                this.content_ = File.access$3700(file);
                onChanged();
            }
            mergeUnknownFields(File.access$3900(file));
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        return true;
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        File file;
        File file2;
        try {
            file2 = (File) File.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (file2 != null) {
                mergeFrom(file2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            file2 = (File) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            file = file2;
            th = th3;
            if (file != null) {
                mergeFrom(file);
            }
            throw th;
        }
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

    public PluginProtos$CodeGeneratorResponse$File$Builder setName(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = str;
        onChanged();
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder clearName() {
        this.bitField0_ &= -2;
        this.name_ = File.getDefaultInstance().getName();
        onChanged();
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder setNameBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasInsertionPoint() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getInsertionPoint() {
        Object obj = this.insertionPoint_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.insertionPoint_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getInsertionPointBytes() {
        Object obj = this.insertionPoint_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.insertionPoint_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder setInsertionPoint(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 2;
        this.insertionPoint_ = str;
        onChanged();
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder clearInsertionPoint() {
        this.bitField0_ &= -3;
        this.insertionPoint_ = File.getDefaultInstance().getInsertionPoint();
        onChanged();
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder setInsertionPointBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 2;
        this.insertionPoint_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasContent() {
        return (this.bitField0_ & 4) == 4;
    }

    public String getContent() {
        Object obj = this.content_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.content_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getContentBytes() {
        Object obj = this.content_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.content_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder setContent(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 4;
        this.content_ = str;
        onChanged();
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder clearContent() {
        this.bitField0_ &= -5;
        this.content_ = File.getDefaultInstance().getContent();
        onChanged();
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder setContentBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 4;
        this.content_ = byteString;
        onChanged();
        return this;
    }

    public final PluginProtos$CodeGeneratorResponse$File$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (PluginProtos$CodeGeneratorResponse$File$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final PluginProtos$CodeGeneratorResponse$File$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (PluginProtos$CodeGeneratorResponse$File$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
