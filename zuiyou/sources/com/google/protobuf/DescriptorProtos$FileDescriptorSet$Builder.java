package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.FileDescriptorSetOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$FileDescriptorSet$Builder extends Builder<DescriptorProtos$FileDescriptorSet$Builder> implements FileDescriptorSetOrBuilder {
    private int bitField0_;
    private RepeatedFieldBuilderV3<DescriptorProtos$FileDescriptorProto, DescriptorProtos$FileDescriptorProto$Builder, FileDescriptorProtoOrBuilder> fileBuilder_;
    private List<DescriptorProtos$FileDescriptorProto> file_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$FileDescriptorSet.class, DescriptorProtos$FileDescriptorSet$Builder.class);
    }

    private DescriptorProtos$FileDescriptorSet$Builder() {
        this.file_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$FileDescriptorSet$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.file_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getFileFieldBuilder();
        }
    }

    public DescriptorProtos$FileDescriptorSet$Builder clear() {
        super.clear();
        if (this.fileBuilder_ == null) {
            this.file_ = Collections.emptyList();
            this.bitField0_ &= -2;
        } else {
            this.fileBuilder_.clear();
        }
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_descriptor;
    }

    public DescriptorProtos$FileDescriptorSet getDefaultInstanceForType() {
        return DescriptorProtos$FileDescriptorSet.getDefaultInstance();
    }

    public DescriptorProtos$FileDescriptorSet build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$FileDescriptorSet buildPartial() {
        DescriptorProtos$FileDescriptorSet descriptorProtos$FileDescriptorSet = new DescriptorProtos$FileDescriptorSet(this, null);
        int i = this.bitField0_;
        if (this.fileBuilder_ == null) {
            if ((this.bitField0_ & 1) == 1) {
                this.file_ = Collections.unmodifiableList(this.file_);
                this.bitField0_ &= -2;
            }
            DescriptorProtos$FileDescriptorSet.access$502(descriptorProtos$FileDescriptorSet, this.file_);
        } else {
            DescriptorProtos$FileDescriptorSet.access$502(descriptorProtos$FileDescriptorSet, this.fileBuilder_.build());
        }
        onBuilt();
        return descriptorProtos$FileDescriptorSet;
    }

    public DescriptorProtos$FileDescriptorSet$Builder clone() {
        return (DescriptorProtos$FileDescriptorSet$Builder) super.clone();
    }

    public DescriptorProtos$FileDescriptorSet$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$FileDescriptorSet$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$FileDescriptorSet$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$FileDescriptorSet$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$FileDescriptorSet$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$FileDescriptorSet$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$FileDescriptorSet$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$FileDescriptorSet$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$FileDescriptorSet$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$FileDescriptorSet$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$FileDescriptorSet$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$FileDescriptorSet) {
            return mergeFrom((DescriptorProtos$FileDescriptorSet) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$FileDescriptorSet$Builder mergeFrom(DescriptorProtos$FileDescriptorSet descriptorProtos$FileDescriptorSet) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (descriptorProtos$FileDescriptorSet != DescriptorProtos$FileDescriptorSet.getDefaultInstance()) {
            if (this.fileBuilder_ == null) {
                if (!DescriptorProtos$FileDescriptorSet.access$500(descriptorProtos$FileDescriptorSet).isEmpty()) {
                    if (this.file_.isEmpty()) {
                        this.file_ = DescriptorProtos$FileDescriptorSet.access$500(descriptorProtos$FileDescriptorSet);
                        this.bitField0_ &= -2;
                    } else {
                        ensureFileIsMutable();
                        this.file_.addAll(DescriptorProtos$FileDescriptorSet.access$500(descriptorProtos$FileDescriptorSet));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$FileDescriptorSet.access$500(descriptorProtos$FileDescriptorSet).isEmpty()) {
                if (this.fileBuilder_.isEmpty()) {
                    this.fileBuilder_.dispose();
                    this.fileBuilder_ = null;
                    this.file_ = DescriptorProtos$FileDescriptorSet.access$500(descriptorProtos$FileDescriptorSet);
                    this.bitField0_ &= -2;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getFileFieldBuilder();
                    }
                    this.fileBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.fileBuilder_.addAllMessages(DescriptorProtos$FileDescriptorSet.access$500(descriptorProtos$FileDescriptorSet));
                }
            }
            mergeUnknownFields(descriptorProtos$FileDescriptorSet.unknownFields);
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        for (int i = 0; i < getFileCount(); i++) {
            if (!getFile(i).isInitialized()) {
                return false;
            }
        }
        return true;
    }

    public DescriptorProtos$FileDescriptorSet$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        DescriptorProtos$FileDescriptorSet descriptorProtos$FileDescriptorSet;
        DescriptorProtos$FileDescriptorSet descriptorProtos$FileDescriptorSet2;
        try {
            descriptorProtos$FileDescriptorSet2 = (DescriptorProtos$FileDescriptorSet) DescriptorProtos$FileDescriptorSet.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$FileDescriptorSet2 != null) {
                mergeFrom(descriptorProtos$FileDescriptorSet2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$FileDescriptorSet2 = (DescriptorProtos$FileDescriptorSet) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$FileDescriptorSet = descriptorProtos$FileDescriptorSet2;
            th = th3;
            if (descriptorProtos$FileDescriptorSet != null) {
                mergeFrom(descriptorProtos$FileDescriptorSet);
            }
            throw th;
        }
    }

    private void ensureFileIsMutable() {
        if ((this.bitField0_ & 1) != 1) {
            this.file_ = new ArrayList(this.file_);
            this.bitField0_ |= 1;
        }
    }

    public List<DescriptorProtos$FileDescriptorProto> getFileList() {
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

    public DescriptorProtos$FileDescriptorProto getFile(int i) {
        if (this.fileBuilder_ == null) {
            return (DescriptorProtos$FileDescriptorProto) this.file_.get(i);
        }
        return (DescriptorProtos$FileDescriptorProto) this.fileBuilder_.getMessage(i);
    }

    public DescriptorProtos$FileDescriptorSet$Builder setFile(int i, DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto) {
        if (this.fileBuilder_ != null) {
            this.fileBuilder_.setMessage(i, descriptorProtos$FileDescriptorProto);
        } else if (descriptorProtos$FileDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureFileIsMutable();
            this.file_.set(i, descriptorProtos$FileDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorSet$Builder setFile(int i, DescriptorProtos$FileDescriptorProto$Builder descriptorProtos$FileDescriptorProto$Builder) {
        if (this.fileBuilder_ == null) {
            ensureFileIsMutable();
            this.file_.set(i, descriptorProtos$FileDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.fileBuilder_.setMessage(i, descriptorProtos$FileDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorSet$Builder addFile(DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto) {
        if (this.fileBuilder_ != null) {
            this.fileBuilder_.addMessage(descriptorProtos$FileDescriptorProto);
        } else if (descriptorProtos$FileDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureFileIsMutable();
            this.file_.add(descriptorProtos$FileDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorSet$Builder addFile(int i, DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto) {
        if (this.fileBuilder_ != null) {
            this.fileBuilder_.addMessage(i, descriptorProtos$FileDescriptorProto);
        } else if (descriptorProtos$FileDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureFileIsMutable();
            this.file_.add(i, descriptorProtos$FileDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorSet$Builder addFile(DescriptorProtos$FileDescriptorProto$Builder descriptorProtos$FileDescriptorProto$Builder) {
        if (this.fileBuilder_ == null) {
            ensureFileIsMutable();
            this.file_.add(descriptorProtos$FileDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.fileBuilder_.addMessage(descriptorProtos$FileDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorSet$Builder addFile(int i, DescriptorProtos$FileDescriptorProto$Builder descriptorProtos$FileDescriptorProto$Builder) {
        if (this.fileBuilder_ == null) {
            ensureFileIsMutable();
            this.file_.add(i, descriptorProtos$FileDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.fileBuilder_.addMessage(i, descriptorProtos$FileDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorSet$Builder addAllFile(Iterable<? extends DescriptorProtos$FileDescriptorProto> iterable) {
        if (this.fileBuilder_ == null) {
            ensureFileIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.file_);
            onChanged();
        } else {
            this.fileBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorSet$Builder clearFile() {
        if (this.fileBuilder_ == null) {
            this.file_ = Collections.emptyList();
            this.bitField0_ &= -2;
            onChanged();
        } else {
            this.fileBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorSet$Builder removeFile(int i) {
        if (this.fileBuilder_ == null) {
            ensureFileIsMutable();
            this.file_.remove(i);
            onChanged();
        } else {
            this.fileBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder getFileBuilder(int i) {
        return (DescriptorProtos$FileDescriptorProto$Builder) getFileFieldBuilder().getBuilder(i);
    }

    public FileDescriptorProtoOrBuilder getFileOrBuilder(int i) {
        if (this.fileBuilder_ == null) {
            return (FileDescriptorProtoOrBuilder) this.file_.get(i);
        }
        return (FileDescriptorProtoOrBuilder) this.fileBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends FileDescriptorProtoOrBuilder> getFileOrBuilderList() {
        if (this.fileBuilder_ != null) {
            return this.fileBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.file_);
    }

    public DescriptorProtos$FileDescriptorProto$Builder addFileBuilder() {
        return (DescriptorProtos$FileDescriptorProto$Builder) getFileFieldBuilder().addBuilder(DescriptorProtos$FileDescriptorProto.getDefaultInstance());
    }

    public DescriptorProtos$FileDescriptorProto$Builder addFileBuilder(int i) {
        return (DescriptorProtos$FileDescriptorProto$Builder) getFileFieldBuilder().addBuilder(i, DescriptorProtos$FileDescriptorProto.getDefaultInstance());
    }

    public List<DescriptorProtos$FileDescriptorProto$Builder> getFileBuilderList() {
        return getFileFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<DescriptorProtos$FileDescriptorProto, DescriptorProtos$FileDescriptorProto$Builder, FileDescriptorProtoOrBuilder> getFileFieldBuilder() {
        boolean z = true;
        if (this.fileBuilder_ == null) {
            List list = this.file_;
            if ((this.bitField0_ & 1) != 1) {
                z = false;
            }
            this.fileBuilder_ = new RepeatedFieldBuilderV3(list, z, getParentForChildren(), isClean());
            this.file_ = null;
        }
        return this.fileBuilder_;
    }

    public final DescriptorProtos$FileDescriptorSet$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$FileDescriptorSet$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$FileDescriptorSet$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$FileDescriptorSet$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
