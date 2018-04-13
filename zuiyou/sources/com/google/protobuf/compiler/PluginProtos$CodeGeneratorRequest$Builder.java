package com.google.protobuf.compiler;

import com.google.protobuf.AbstractMessage$Builder;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.DescriptorProtos$FileDescriptorProto;
import com.google.protobuf.DescriptorProtos$FileDescriptorProto$Builder;
import com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder;
import com.google.protobuf.compiler.PluginProtos.VersionOrBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PluginProtos$CodeGeneratorRequest$Builder extends Builder<PluginProtos$CodeGeneratorRequest$Builder> implements CodeGeneratorRequestOrBuilder {
    private int bitField0_;
    private SingleFieldBuilderV3<PluginProtos$Version, PluginProtos$Version$Builder, VersionOrBuilder> compilerVersionBuilder_;
    private PluginProtos$Version compilerVersion_;
    private LazyStringList fileToGenerate_;
    private Object parameter_;
    private RepeatedFieldBuilderV3<DescriptorProtos$FileDescriptorProto, DescriptorProtos$FileDescriptorProto$Builder, FileDescriptorProtoOrBuilder> protoFileBuilder_;
    private List<DescriptorProtos$FileDescriptorProto> protoFile_;

    public static final Descriptor getDescriptor() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(PluginProtos$CodeGeneratorRequest.class, PluginProtos$CodeGeneratorRequest$Builder.class);
    }

    private PluginProtos$CodeGeneratorRequest$Builder() {
        this.fileToGenerate_ = LazyStringArrayList.EMPTY;
        this.parameter_ = "";
        this.protoFile_ = Collections.emptyList();
        this.compilerVersion_ = null;
        maybeForceBuilderInitialization();
    }

    private PluginProtos$CodeGeneratorRequest$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.fileToGenerate_ = LazyStringArrayList.EMPTY;
        this.parameter_ = "";
        this.protoFile_ = Collections.emptyList();
        this.compilerVersion_ = null;
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (PluginProtos$CodeGeneratorRequest.access$1700()) {
            getProtoFileFieldBuilder();
            getCompilerVersionFieldBuilder();
        }
    }

    public PluginProtos$CodeGeneratorRequest$Builder clear() {
        super.clear();
        this.fileToGenerate_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= -2;
        this.parameter_ = "";
        this.bitField0_ &= -3;
        if (this.protoFileBuilder_ == null) {
            this.protoFile_ = Collections.emptyList();
            this.bitField0_ &= -5;
        } else {
            this.protoFileBuilder_.clear();
        }
        if (this.compilerVersionBuilder_ == null) {
            this.compilerVersion_ = null;
        } else {
            this.compilerVersionBuilder_.clear();
        }
        this.bitField0_ &= -9;
        return this;
    }

    public Descriptor getDescriptorForType() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor;
    }

    public PluginProtos$CodeGeneratorRequest getDefaultInstanceForType() {
        return PluginProtos$CodeGeneratorRequest.getDefaultInstance();
    }

    public PluginProtos$CodeGeneratorRequest build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public PluginProtos$CodeGeneratorRequest buildPartial() {
        int i;
        int i2 = 1;
        PluginProtos$CodeGeneratorRequest pluginProtos$CodeGeneratorRequest = new PluginProtos$CodeGeneratorRequest(this, null);
        int i3 = this.bitField0_;
        if ((this.bitField0_ & 1) == 1) {
            this.fileToGenerate_ = this.fileToGenerate_.getUnmodifiableView();
            this.bitField0_ &= -2;
        }
        PluginProtos$CodeGeneratorRequest.access$1902(pluginProtos$CodeGeneratorRequest, this.fileToGenerate_);
        if ((i3 & 2) != 2) {
            i2 = 0;
        }
        PluginProtos$CodeGeneratorRequest.access$2002(pluginProtos$CodeGeneratorRequest, this.parameter_);
        if (this.protoFileBuilder_ == null) {
            if ((this.bitField0_ & 4) == 4) {
                this.protoFile_ = Collections.unmodifiableList(this.protoFile_);
                this.bitField0_ &= -5;
            }
            PluginProtos$CodeGeneratorRequest.access$2102(pluginProtos$CodeGeneratorRequest, this.protoFile_);
        } else {
            PluginProtos$CodeGeneratorRequest.access$2102(pluginProtos$CodeGeneratorRequest, this.protoFileBuilder_.build());
        }
        if ((i3 & 8) == 8) {
            i = i2 | 2;
        } else {
            i = i2;
        }
        if (this.compilerVersionBuilder_ == null) {
            PluginProtos$CodeGeneratorRequest.access$2202(pluginProtos$CodeGeneratorRequest, this.compilerVersion_);
        } else {
            PluginProtos$CodeGeneratorRequest.access$2202(pluginProtos$CodeGeneratorRequest, (PluginProtos$Version) this.compilerVersionBuilder_.build());
        }
        PluginProtos$CodeGeneratorRequest.access$2302(pluginProtos$CodeGeneratorRequest, i);
        onBuilt();
        return pluginProtos$CodeGeneratorRequest;
    }

    public PluginProtos$CodeGeneratorRequest$Builder clone() {
        return (PluginProtos$CodeGeneratorRequest$Builder) super.clone();
    }

    public PluginProtos$CodeGeneratorRequest$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (PluginProtos$CodeGeneratorRequest$Builder) super.setField(fieldDescriptor, obj);
    }

    public PluginProtos$CodeGeneratorRequest$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (PluginProtos$CodeGeneratorRequest$Builder) super.clearField(fieldDescriptor);
    }

    public PluginProtos$CodeGeneratorRequest$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (PluginProtos$CodeGeneratorRequest$Builder) super.clearOneof(oneofDescriptor);
    }

    public PluginProtos$CodeGeneratorRequest$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (PluginProtos$CodeGeneratorRequest$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public PluginProtos$CodeGeneratorRequest$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (PluginProtos$CodeGeneratorRequest$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public PluginProtos$CodeGeneratorRequest$Builder mergeFrom(Message message) {
        if (message instanceof PluginProtos$CodeGeneratorRequest) {
            return mergeFrom((PluginProtos$CodeGeneratorRequest) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder mergeFrom(PluginProtos$CodeGeneratorRequest pluginProtos$CodeGeneratorRequest) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (pluginProtos$CodeGeneratorRequest != PluginProtos$CodeGeneratorRequest.getDefaultInstance()) {
            if (!PluginProtos$CodeGeneratorRequest.access$1900(pluginProtos$CodeGeneratorRequest).isEmpty()) {
                if (this.fileToGenerate_.isEmpty()) {
                    this.fileToGenerate_ = PluginProtos$CodeGeneratorRequest.access$1900(pluginProtos$CodeGeneratorRequest);
                    this.bitField0_ &= -2;
                } else {
                    ensureFileToGenerateIsMutable();
                    this.fileToGenerate_.addAll(PluginProtos$CodeGeneratorRequest.access$1900(pluginProtos$CodeGeneratorRequest));
                }
                onChanged();
            }
            if (pluginProtos$CodeGeneratorRequest.hasParameter()) {
                this.bitField0_ |= 2;
                this.parameter_ = PluginProtos$CodeGeneratorRequest.access$2000(pluginProtos$CodeGeneratorRequest);
                onChanged();
            }
            if (this.protoFileBuilder_ == null) {
                if (!PluginProtos$CodeGeneratorRequest.access$2100(pluginProtos$CodeGeneratorRequest).isEmpty()) {
                    if (this.protoFile_.isEmpty()) {
                        this.protoFile_ = PluginProtos$CodeGeneratorRequest.access$2100(pluginProtos$CodeGeneratorRequest);
                        this.bitField0_ &= -5;
                    } else {
                        ensureProtoFileIsMutable();
                        this.protoFile_.addAll(PluginProtos$CodeGeneratorRequest.access$2100(pluginProtos$CodeGeneratorRequest));
                    }
                    onChanged();
                }
            } else if (!PluginProtos$CodeGeneratorRequest.access$2100(pluginProtos$CodeGeneratorRequest).isEmpty()) {
                if (this.protoFileBuilder_.isEmpty()) {
                    this.protoFileBuilder_.dispose();
                    this.protoFileBuilder_ = null;
                    this.protoFile_ = PluginProtos$CodeGeneratorRequest.access$2100(pluginProtos$CodeGeneratorRequest);
                    this.bitField0_ &= -5;
                    if (PluginProtos$CodeGeneratorRequest.access$2400()) {
                        repeatedFieldBuilderV3 = getProtoFileFieldBuilder();
                    }
                    this.protoFileBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.protoFileBuilder_.addAllMessages(PluginProtos$CodeGeneratorRequest.access$2100(pluginProtos$CodeGeneratorRequest));
                }
            }
            if (pluginProtos$CodeGeneratorRequest.hasCompilerVersion()) {
                mergeCompilerVersion(pluginProtos$CodeGeneratorRequest.getCompilerVersion());
            }
            mergeUnknownFields(PluginProtos$CodeGeneratorRequest.access$2500(pluginProtos$CodeGeneratorRequest));
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        for (int i = 0; i < getProtoFileCount(); i++) {
            if (!getProtoFile(i).isInitialized()) {
                return false;
            }
        }
        return true;
    }

    public PluginProtos$CodeGeneratorRequest$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        PluginProtos$CodeGeneratorRequest pluginProtos$CodeGeneratorRequest;
        PluginProtos$CodeGeneratorRequest pluginProtos$CodeGeneratorRequest2;
        try {
            pluginProtos$CodeGeneratorRequest2 = (PluginProtos$CodeGeneratorRequest) PluginProtos$CodeGeneratorRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (pluginProtos$CodeGeneratorRequest2 != null) {
                mergeFrom(pluginProtos$CodeGeneratorRequest2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            pluginProtos$CodeGeneratorRequest2 = (PluginProtos$CodeGeneratorRequest) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            pluginProtos$CodeGeneratorRequest = pluginProtos$CodeGeneratorRequest2;
            th = th3;
            if (pluginProtos$CodeGeneratorRequest != null) {
                mergeFrom(pluginProtos$CodeGeneratorRequest);
            }
            throw th;
        }
    }

    private void ensureFileToGenerateIsMutable() {
        if ((this.bitField0_ & 1) != 1) {
            this.fileToGenerate_ = new LazyStringArrayList(this.fileToGenerate_);
            this.bitField0_ |= 1;
        }
    }

    public ProtocolStringList getFileToGenerateList() {
        return this.fileToGenerate_.getUnmodifiableView();
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

    public PluginProtos$CodeGeneratorRequest$Builder setFileToGenerate(int i, String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        ensureFileToGenerateIsMutable();
        this.fileToGenerate_.set(i, str);
        onChanged();
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder addFileToGenerate(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        ensureFileToGenerateIsMutable();
        this.fileToGenerate_.add(str);
        onChanged();
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder addAllFileToGenerate(Iterable<String> iterable) {
        ensureFileToGenerateIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, this.fileToGenerate_);
        onChanged();
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder clearFileToGenerate() {
        this.fileToGenerate_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= -2;
        onChanged();
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder addFileToGenerateBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        ensureFileToGenerateIsMutable();
        this.fileToGenerate_.add(byteString);
        onChanged();
        return this;
    }

    public boolean hasParameter() {
        return (this.bitField0_ & 2) == 2;
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

    public PluginProtos$CodeGeneratorRequest$Builder setParameter(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 2;
        this.parameter_ = str;
        onChanged();
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder clearParameter() {
        this.bitField0_ &= -3;
        this.parameter_ = PluginProtos$CodeGeneratorRequest.getDefaultInstance().getParameter();
        onChanged();
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder setParameterBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 2;
        this.parameter_ = byteString;
        onChanged();
        return this;
    }

    private void ensureProtoFileIsMutable() {
        if ((this.bitField0_ & 4) != 4) {
            this.protoFile_ = new ArrayList(this.protoFile_);
            this.bitField0_ |= 4;
        }
    }

    public List<DescriptorProtos$FileDescriptorProto> getProtoFileList() {
        if (this.protoFileBuilder_ == null) {
            return Collections.unmodifiableList(this.protoFile_);
        }
        return this.protoFileBuilder_.getMessageList();
    }

    public int getProtoFileCount() {
        if (this.protoFileBuilder_ == null) {
            return this.protoFile_.size();
        }
        return this.protoFileBuilder_.getCount();
    }

    public DescriptorProtos$FileDescriptorProto getProtoFile(int i) {
        if (this.protoFileBuilder_ == null) {
            return (DescriptorProtos$FileDescriptorProto) this.protoFile_.get(i);
        }
        return (DescriptorProtos$FileDescriptorProto) this.protoFileBuilder_.getMessage(i);
    }

    public PluginProtos$CodeGeneratorRequest$Builder setProtoFile(int i, DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto) {
        if (this.protoFileBuilder_ != null) {
            this.protoFileBuilder_.setMessage(i, descriptorProtos$FileDescriptorProto);
        } else if (descriptorProtos$FileDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureProtoFileIsMutable();
            this.protoFile_.set(i, descriptorProtos$FileDescriptorProto);
            onChanged();
        }
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder setProtoFile(int i, DescriptorProtos$FileDescriptorProto$Builder descriptorProtos$FileDescriptorProto$Builder) {
        if (this.protoFileBuilder_ == null) {
            ensureProtoFileIsMutable();
            this.protoFile_.set(i, descriptorProtos$FileDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.protoFileBuilder_.setMessage(i, descriptorProtos$FileDescriptorProto$Builder.build());
        }
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder addProtoFile(DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto) {
        if (this.protoFileBuilder_ != null) {
            this.protoFileBuilder_.addMessage(descriptorProtos$FileDescriptorProto);
        } else if (descriptorProtos$FileDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureProtoFileIsMutable();
            this.protoFile_.add(descriptorProtos$FileDescriptorProto);
            onChanged();
        }
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder addProtoFile(int i, DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto) {
        if (this.protoFileBuilder_ != null) {
            this.protoFileBuilder_.addMessage(i, descriptorProtos$FileDescriptorProto);
        } else if (descriptorProtos$FileDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureProtoFileIsMutable();
            this.protoFile_.add(i, descriptorProtos$FileDescriptorProto);
            onChanged();
        }
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder addProtoFile(DescriptorProtos$FileDescriptorProto$Builder descriptorProtos$FileDescriptorProto$Builder) {
        if (this.protoFileBuilder_ == null) {
            ensureProtoFileIsMutable();
            this.protoFile_.add(descriptorProtos$FileDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.protoFileBuilder_.addMessage(descriptorProtos$FileDescriptorProto$Builder.build());
        }
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder addProtoFile(int i, DescriptorProtos$FileDescriptorProto$Builder descriptorProtos$FileDescriptorProto$Builder) {
        if (this.protoFileBuilder_ == null) {
            ensureProtoFileIsMutable();
            this.protoFile_.add(i, descriptorProtos$FileDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.protoFileBuilder_.addMessage(i, descriptorProtos$FileDescriptorProto$Builder.build());
        }
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder addAllProtoFile(Iterable<? extends DescriptorProtos$FileDescriptorProto> iterable) {
        if (this.protoFileBuilder_ == null) {
            ensureProtoFileIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.protoFile_);
            onChanged();
        } else {
            this.protoFileBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder clearProtoFile() {
        if (this.protoFileBuilder_ == null) {
            this.protoFile_ = Collections.emptyList();
            this.bitField0_ &= -5;
            onChanged();
        } else {
            this.protoFileBuilder_.clear();
        }
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder removeProtoFile(int i) {
        if (this.protoFileBuilder_ == null) {
            ensureProtoFileIsMutable();
            this.protoFile_.remove(i);
            onChanged();
        } else {
            this.protoFileBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder getProtoFileBuilder(int i) {
        return (DescriptorProtos$FileDescriptorProto$Builder) getProtoFileFieldBuilder().getBuilder(i);
    }

    public FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int i) {
        if (this.protoFileBuilder_ == null) {
            return (FileDescriptorProtoOrBuilder) this.protoFile_.get(i);
        }
        return (FileDescriptorProtoOrBuilder) this.protoFileBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends FileDescriptorProtoOrBuilder> getProtoFileOrBuilderList() {
        if (this.protoFileBuilder_ != null) {
            return this.protoFileBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.protoFile_);
    }

    public DescriptorProtos$FileDescriptorProto$Builder addProtoFileBuilder() {
        return (DescriptorProtos$FileDescriptorProto$Builder) getProtoFileFieldBuilder().addBuilder(DescriptorProtos$FileDescriptorProto.getDefaultInstance());
    }

    public DescriptorProtos$FileDescriptorProto$Builder addProtoFileBuilder(int i) {
        return (DescriptorProtos$FileDescriptorProto$Builder) getProtoFileFieldBuilder().addBuilder(i, DescriptorProtos$FileDescriptorProto.getDefaultInstance());
    }

    public List<DescriptorProtos$FileDescriptorProto$Builder> getProtoFileBuilderList() {
        return getProtoFileFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<DescriptorProtos$FileDescriptorProto, DescriptorProtos$FileDescriptorProto$Builder, FileDescriptorProtoOrBuilder> getProtoFileFieldBuilder() {
        if (this.protoFileBuilder_ == null) {
            this.protoFileBuilder_ = new RepeatedFieldBuilderV3(this.protoFile_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
            this.protoFile_ = null;
        }
        return this.protoFileBuilder_;
    }

    public boolean hasCompilerVersion() {
        return (this.bitField0_ & 8) == 8;
    }

    public PluginProtos$Version getCompilerVersion() {
        if (this.compilerVersionBuilder_ == null) {
            return this.compilerVersion_ == null ? PluginProtos$Version.getDefaultInstance() : this.compilerVersion_;
        } else {
            return (PluginProtos$Version) this.compilerVersionBuilder_.getMessage();
        }
    }

    public PluginProtos$CodeGeneratorRequest$Builder setCompilerVersion(PluginProtos$Version pluginProtos$Version) {
        if (this.compilerVersionBuilder_ != null) {
            this.compilerVersionBuilder_.setMessage(pluginProtos$Version);
        } else if (pluginProtos$Version == null) {
            throw new NullPointerException();
        } else {
            this.compilerVersion_ = pluginProtos$Version;
            onChanged();
        }
        this.bitField0_ |= 8;
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder setCompilerVersion(PluginProtos$Version$Builder pluginProtos$Version$Builder) {
        if (this.compilerVersionBuilder_ == null) {
            this.compilerVersion_ = pluginProtos$Version$Builder.build();
            onChanged();
        } else {
            this.compilerVersionBuilder_.setMessage(pluginProtos$Version$Builder.build());
        }
        this.bitField0_ |= 8;
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder mergeCompilerVersion(PluginProtos$Version pluginProtos$Version) {
        if (this.compilerVersionBuilder_ == null) {
            if ((this.bitField0_ & 8) != 8 || this.compilerVersion_ == null || this.compilerVersion_ == PluginProtos$Version.getDefaultInstance()) {
                this.compilerVersion_ = pluginProtos$Version;
            } else {
                this.compilerVersion_ = PluginProtos$Version.newBuilder(this.compilerVersion_).mergeFrom(pluginProtos$Version).buildPartial();
            }
            onChanged();
        } else {
            this.compilerVersionBuilder_.mergeFrom(pluginProtos$Version);
        }
        this.bitField0_ |= 8;
        return this;
    }

    public PluginProtos$CodeGeneratorRequest$Builder clearCompilerVersion() {
        if (this.compilerVersionBuilder_ == null) {
            this.compilerVersion_ = null;
            onChanged();
        } else {
            this.compilerVersionBuilder_.clear();
        }
        this.bitField0_ &= -9;
        return this;
    }

    public PluginProtos$Version$Builder getCompilerVersionBuilder() {
        this.bitField0_ |= 8;
        onChanged();
        return (PluginProtos$Version$Builder) getCompilerVersionFieldBuilder().getBuilder();
    }

    public VersionOrBuilder getCompilerVersionOrBuilder() {
        if (this.compilerVersionBuilder_ != null) {
            return (VersionOrBuilder) this.compilerVersionBuilder_.getMessageOrBuilder();
        }
        return this.compilerVersion_ == null ? PluginProtos$Version.getDefaultInstance() : this.compilerVersion_;
    }

    private SingleFieldBuilderV3<PluginProtos$Version, PluginProtos$Version$Builder, VersionOrBuilder> getCompilerVersionFieldBuilder() {
        if (this.compilerVersionBuilder_ == null) {
            this.compilerVersionBuilder_ = new SingleFieldBuilderV3(getCompilerVersion(), getParentForChildren(), isClean());
            this.compilerVersion_ = null;
        }
        return this.compilerVersionBuilder_;
    }

    public final PluginProtos$CodeGeneratorRequest$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (PluginProtos$CodeGeneratorRequest$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final PluginProtos$CodeGeneratorRequest$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (PluginProtos$CodeGeneratorRequest$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
