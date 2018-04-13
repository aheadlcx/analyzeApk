package com.google.protobuf.compiler;

import com.google.protobuf.AbstractMessage$Builder;
import com.google.protobuf.AbstractMessageLite;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PluginProtos$CodeGeneratorResponse$Builder extends Builder<PluginProtos$CodeGeneratorResponse$Builder> implements CodeGeneratorResponseOrBuilder {
    private int bitField0_;
    private Object error_;
    private RepeatedFieldBuilderV3<File, PluginProtos$CodeGeneratorResponse$File$Builder, PluginProtos$CodeGeneratorResponse$FileOrBuilder> fileBuilder_;
    private List<File> file_;

    public static final Descriptor getDescriptor() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(PluginProtos$CodeGeneratorResponse.class, PluginProtos$CodeGeneratorResponse$Builder.class);
    }

    private PluginProtos$CodeGeneratorResponse$Builder() {
        this.error_ = "";
        this.file_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private PluginProtos$CodeGeneratorResponse$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.error_ = "";
        this.file_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (PluginProtos$CodeGeneratorResponse.access$4300()) {
            getFileFieldBuilder();
        }
    }

    public PluginProtos$CodeGeneratorResponse$Builder clear() {
        super.clear();
        this.error_ = "";
        this.bitField0_ &= -2;
        if (this.fileBuilder_ == null) {
            this.file_ = Collections.emptyList();
            this.bitField0_ &= -3;
        } else {
            this.fileBuilder_.clear();
        }
        return this;
    }

    public Descriptor getDescriptorForType() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor;
    }

    public PluginProtos$CodeGeneratorResponse getDefaultInstanceForType() {
        return PluginProtos$CodeGeneratorResponse.getDefaultInstance();
    }

    public PluginProtos$CodeGeneratorResponse build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public PluginProtos$CodeGeneratorResponse buildPartial() {
        int i = 1;
        PluginProtos$CodeGeneratorResponse pluginProtos$CodeGeneratorResponse = new PluginProtos$CodeGeneratorResponse(this, null);
        if ((this.bitField0_ & 1) != 1) {
            i = 0;
        }
        PluginProtos$CodeGeneratorResponse.access$4502(pluginProtos$CodeGeneratorResponse, this.error_);
        if (this.fileBuilder_ == null) {
            if ((this.bitField0_ & 2) == 2) {
                this.file_ = Collections.unmodifiableList(this.file_);
                this.bitField0_ &= -3;
            }
            PluginProtos$CodeGeneratorResponse.access$4602(pluginProtos$CodeGeneratorResponse, this.file_);
        } else {
            PluginProtos$CodeGeneratorResponse.access$4602(pluginProtos$CodeGeneratorResponse, this.fileBuilder_.build());
        }
        PluginProtos$CodeGeneratorResponse.access$4702(pluginProtos$CodeGeneratorResponse, i);
        onBuilt();
        return pluginProtos$CodeGeneratorResponse;
    }

    public PluginProtos$CodeGeneratorResponse$Builder clone() {
        return (PluginProtos$CodeGeneratorResponse$Builder) super.clone();
    }

    public PluginProtos$CodeGeneratorResponse$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (PluginProtos$CodeGeneratorResponse$Builder) super.setField(fieldDescriptor, obj);
    }

    public PluginProtos$CodeGeneratorResponse$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (PluginProtos$CodeGeneratorResponse$Builder) super.clearField(fieldDescriptor);
    }

    public PluginProtos$CodeGeneratorResponse$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (PluginProtos$CodeGeneratorResponse$Builder) super.clearOneof(oneofDescriptor);
    }

    public PluginProtos$CodeGeneratorResponse$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (PluginProtos$CodeGeneratorResponse$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public PluginProtos$CodeGeneratorResponse$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (PluginProtos$CodeGeneratorResponse$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public PluginProtos$CodeGeneratorResponse$Builder mergeFrom(Message message) {
        if (message instanceof PluginProtos$CodeGeneratorResponse) {
            return mergeFrom((PluginProtos$CodeGeneratorResponse) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$Builder mergeFrom(PluginProtos$CodeGeneratorResponse pluginProtos$CodeGeneratorResponse) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (pluginProtos$CodeGeneratorResponse != PluginProtos$CodeGeneratorResponse.getDefaultInstance()) {
            if (pluginProtos$CodeGeneratorResponse.hasError()) {
                this.bitField0_ |= 1;
                this.error_ = PluginProtos$CodeGeneratorResponse.access$4500(pluginProtos$CodeGeneratorResponse);
                onChanged();
            }
            if (this.fileBuilder_ == null) {
                if (!PluginProtos$CodeGeneratorResponse.access$4600(pluginProtos$CodeGeneratorResponse).isEmpty()) {
                    if (this.file_.isEmpty()) {
                        this.file_ = PluginProtos$CodeGeneratorResponse.access$4600(pluginProtos$CodeGeneratorResponse);
                        this.bitField0_ &= -3;
                    } else {
                        ensureFileIsMutable();
                        this.file_.addAll(PluginProtos$CodeGeneratorResponse.access$4600(pluginProtos$CodeGeneratorResponse));
                    }
                    onChanged();
                }
            } else if (!PluginProtos$CodeGeneratorResponse.access$4600(pluginProtos$CodeGeneratorResponse).isEmpty()) {
                if (this.fileBuilder_.isEmpty()) {
                    this.fileBuilder_.dispose();
                    this.fileBuilder_ = null;
                    this.file_ = PluginProtos$CodeGeneratorResponse.access$4600(pluginProtos$CodeGeneratorResponse);
                    this.bitField0_ &= -3;
                    if (PluginProtos$CodeGeneratorResponse.access$4800()) {
                        repeatedFieldBuilderV3 = getFileFieldBuilder();
                    }
                    this.fileBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.fileBuilder_.addAllMessages(PluginProtos$CodeGeneratorResponse.access$4600(pluginProtos$CodeGeneratorResponse));
                }
            }
            mergeUnknownFields(PluginProtos$CodeGeneratorResponse.access$4900(pluginProtos$CodeGeneratorResponse));
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        return true;
    }

    public PluginProtos$CodeGeneratorResponse$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        PluginProtos$CodeGeneratorResponse pluginProtos$CodeGeneratorResponse;
        PluginProtos$CodeGeneratorResponse pluginProtos$CodeGeneratorResponse2;
        try {
            pluginProtos$CodeGeneratorResponse2 = (PluginProtos$CodeGeneratorResponse) PluginProtos$CodeGeneratorResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (pluginProtos$CodeGeneratorResponse2 != null) {
                mergeFrom(pluginProtos$CodeGeneratorResponse2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            pluginProtos$CodeGeneratorResponse2 = (PluginProtos$CodeGeneratorResponse) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            pluginProtos$CodeGeneratorResponse = pluginProtos$CodeGeneratorResponse2;
            th = th3;
            if (pluginProtos$CodeGeneratorResponse != null) {
                mergeFrom(pluginProtos$CodeGeneratorResponse);
            }
            throw th;
        }
    }

    public boolean hasError() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getError() {
        Object obj = this.error_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.error_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getErrorBytes() {
        Object obj = this.error_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.error_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public PluginProtos$CodeGeneratorResponse$Builder setError(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.error_ = str;
        onChanged();
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$Builder clearError() {
        this.bitField0_ &= -2;
        this.error_ = PluginProtos$CodeGeneratorResponse.getDefaultInstance().getError();
        onChanged();
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$Builder setErrorBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.error_ = byteString;
        onChanged();
        return this;
    }

    private void ensureFileIsMutable() {
        if ((this.bitField0_ & 2) != 2) {
            this.file_ = new ArrayList(this.file_);
            this.bitField0_ |= 2;
        }
    }

    public List<File> getFileList() {
        if (this.fileBuilder_ == null) {
            return Collections.unmodifiableList(this.file_);
        }
        return this.fileBuilder_.getMessageList();
    }

    public int getFileCount() {
        if (this.fileBuilder_ == null) {
            return this.file_.size();
        }
        return this.fileBuilder_.getCount();
    }

    public File getFile(int i) {
        if (this.fileBuilder_ == null) {
            return (File) this.file_.get(i);
        }
        return (File) this.fileBuilder_.getMessage(i);
    }

    public PluginProtos$CodeGeneratorResponse$Builder setFile(int i, File file) {
        if (this.fileBuilder_ != null) {
            this.fileBuilder_.setMessage(i, file);
        } else if (file == null) {
            throw new NullPointerException();
        } else {
            ensureFileIsMutable();
            this.file_.set(i, file);
            onChanged();
        }
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$Builder setFile(int i, PluginProtos$CodeGeneratorResponse$File$Builder pluginProtos$CodeGeneratorResponse$File$Builder) {
        if (this.fileBuilder_ == null) {
            ensureFileIsMutable();
            this.file_.set(i, pluginProtos$CodeGeneratorResponse$File$Builder.build());
            onChanged();
        } else {
            this.fileBuilder_.setMessage(i, pluginProtos$CodeGeneratorResponse$File$Builder.build());
        }
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$Builder addFile(File file) {
        if (this.fileBuilder_ != null) {
            this.fileBuilder_.addMessage(file);
        } else if (file == null) {
            throw new NullPointerException();
        } else {
            ensureFileIsMutable();
            this.file_.add(file);
            onChanged();
        }
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$Builder addFile(int i, File file) {
        if (this.fileBuilder_ != null) {
            this.fileBuilder_.addMessage(i, file);
        } else if (file == null) {
            throw new NullPointerException();
        } else {
            ensureFileIsMutable();
            this.file_.add(i, file);
            onChanged();
        }
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$Builder addFile(PluginProtos$CodeGeneratorResponse$File$Builder pluginProtos$CodeGeneratorResponse$File$Builder) {
        if (this.fileBuilder_ == null) {
            ensureFileIsMutable();
            this.file_.add(pluginProtos$CodeGeneratorResponse$File$Builder.build());
            onChanged();
        } else {
            this.fileBuilder_.addMessage(pluginProtos$CodeGeneratorResponse$File$Builder.build());
        }
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$Builder addFile(int i, PluginProtos$CodeGeneratorResponse$File$Builder pluginProtos$CodeGeneratorResponse$File$Builder) {
        if (this.fileBuilder_ == null) {
            ensureFileIsMutable();
            this.file_.add(i, pluginProtos$CodeGeneratorResponse$File$Builder.build());
            onChanged();
        } else {
            this.fileBuilder_.addMessage(i, pluginProtos$CodeGeneratorResponse$File$Builder.build());
        }
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$Builder addAllFile(Iterable<? extends File> iterable) {
        if (this.fileBuilder_ == null) {
            ensureFileIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.file_);
            onChanged();
        } else {
            this.fileBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$Builder clearFile() {
        if (this.fileBuilder_ == null) {
            this.file_ = Collections.emptyList();
            this.bitField0_ &= -3;
            onChanged();
        } else {
            this.fileBuilder_.clear();
        }
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$Builder removeFile(int i) {
        if (this.fileBuilder_ == null) {
            ensureFileIsMutable();
            this.file_.remove(i);
            onChanged();
        } else {
            this.fileBuilder_.remove(i);
        }
        return this;
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder getFileBuilder(int i) {
        return (PluginProtos$CodeGeneratorResponse$File$Builder) getFileFieldBuilder().getBuilder(i);
    }

    public PluginProtos$CodeGeneratorResponse$FileOrBuilder getFileOrBuilder(int i) {
        if (this.fileBuilder_ == null) {
            return (PluginProtos$CodeGeneratorResponse$FileOrBuilder) this.file_.get(i);
        }
        return (PluginProtos$CodeGeneratorResponse$FileOrBuilder) this.fileBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends PluginProtos$CodeGeneratorResponse$FileOrBuilder> getFileOrBuilderList() {
        if (this.fileBuilder_ != null) {
            return this.fileBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.file_);
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder addFileBuilder() {
        return (PluginProtos$CodeGeneratorResponse$File$Builder) getFileFieldBuilder().addBuilder(File.getDefaultInstance());
    }

    public PluginProtos$CodeGeneratorResponse$File$Builder addFileBuilder(int i) {
        return (PluginProtos$CodeGeneratorResponse$File$Builder) getFileFieldBuilder().addBuilder(i, File.getDefaultInstance());
    }

    public List<PluginProtos$CodeGeneratorResponse$File$Builder> getFileBuilderList() {
        return getFileFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<File, PluginProtos$CodeGeneratorResponse$File$Builder, PluginProtos$CodeGeneratorResponse$FileOrBuilder> getFileFieldBuilder() {
        if (this.fileBuilder_ == null) {
            this.fileBuilder_ = new RepeatedFieldBuilderV3(this.file_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
            this.file_ = null;
        }
        return this.fileBuilder_;
    }

    public final PluginProtos$CodeGeneratorResponse$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (PluginProtos$CodeGeneratorResponse$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final PluginProtos$CodeGeneratorResponse$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (PluginProtos$CodeGeneratorResponse$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
