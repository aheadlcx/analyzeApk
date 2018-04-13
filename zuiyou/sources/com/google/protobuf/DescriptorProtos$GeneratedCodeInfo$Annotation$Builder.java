package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.GeneratedCodeInfo.Annotation;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$GeneratedCodeInfo$Annotation$Builder extends Builder<DescriptorProtos$GeneratedCodeInfo$Annotation$Builder> implements DescriptorProtos$GeneratedCodeInfo$AnnotationOrBuilder {
    private int begin_;
    private int bitField0_;
    private int end_;
    private List<Integer> path_;
    private Object sourceFile_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_GeneratedCodeInfo_Annotation_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_GeneratedCodeInfo_Annotation_fieldAccessorTable.ensureFieldAccessorsInitialized(Annotation.class, DescriptorProtos$GeneratedCodeInfo$Annotation$Builder.class);
    }

    private DescriptorProtos$GeneratedCodeInfo$Annotation$Builder() {
        this.path_ = Collections.emptyList();
        this.sourceFile_ = "";
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$GeneratedCodeInfo$Annotation$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.path_ = Collections.emptyList();
        this.sourceFile_ = "";
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (!GeneratedMessageV3.alwaysUseFieldBuilders) {
        }
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder clear() {
        super.clear();
        this.path_ = Collections.emptyList();
        this.bitField0_ &= -2;
        this.sourceFile_ = "";
        this.bitField0_ &= -3;
        this.begin_ = 0;
        this.bitField0_ &= -5;
        this.end_ = 0;
        this.bitField0_ &= -9;
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_GeneratedCodeInfo_Annotation_descriptor;
    }

    public Annotation getDefaultInstanceForType() {
        return Annotation.getDefaultInstance();
    }

    public Annotation build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public Annotation buildPartial() {
        int i = 1;
        Annotation annotation = new Annotation(this, null);
        int i2 = this.bitField0_;
        if ((this.bitField0_ & 1) == 1) {
            this.path_ = Collections.unmodifiableList(this.path_);
            this.bitField0_ &= -2;
        }
        Annotation.access$28402(annotation, this.path_);
        if ((i2 & 2) != 2) {
            i = 0;
        }
        Annotation.access$28502(annotation, this.sourceFile_);
        if ((i2 & 4) == 4) {
            i |= 2;
        }
        Annotation.access$28602(annotation, this.begin_);
        if ((i2 & 8) == 8) {
            i |= 4;
        }
        Annotation.access$28702(annotation, this.end_);
        Annotation.access$28802(annotation, i);
        onBuilt();
        return annotation;
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder clone() {
        return (DescriptorProtos$GeneratedCodeInfo$Annotation$Builder) super.clone();
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$GeneratedCodeInfo$Annotation$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$GeneratedCodeInfo$Annotation$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$GeneratedCodeInfo$Annotation$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$GeneratedCodeInfo$Annotation$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$GeneratedCodeInfo$Annotation$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder mergeFrom(Message message) {
        if (message instanceof Annotation) {
            return mergeFrom((Annotation) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder mergeFrom(Annotation annotation) {
        if (annotation != Annotation.getDefaultInstance()) {
            if (!Annotation.access$28400(annotation).isEmpty()) {
                if (this.path_.isEmpty()) {
                    this.path_ = Annotation.access$28400(annotation);
                    this.bitField0_ &= -2;
                } else {
                    ensurePathIsMutable();
                    this.path_.addAll(Annotation.access$28400(annotation));
                }
                onChanged();
            }
            if (annotation.hasSourceFile()) {
                this.bitField0_ |= 2;
                this.sourceFile_ = Annotation.access$28500(annotation);
                onChanged();
            }
            if (annotation.hasBegin()) {
                setBegin(annotation.getBegin());
            }
            if (annotation.hasEnd()) {
                setEnd(annotation.getEnd());
            }
            mergeUnknownFields(annotation.unknownFields);
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        return true;
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Annotation annotation;
        Throwable th;
        Annotation annotation2;
        try {
            annotation = (Annotation) Annotation.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (annotation != null) {
                mergeFrom(annotation);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            annotation = (Annotation) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            annotation2 = annotation;
            th = th3;
            if (annotation2 != null) {
                mergeFrom(annotation2);
            }
            throw th;
        }
    }

    private void ensurePathIsMutable() {
        if ((this.bitField0_ & 1) != 1) {
            this.path_ = new ArrayList(this.path_);
            this.bitField0_ |= 1;
        }
    }

    public List<Integer> getPathList() {
        return Collections.unmodifiableList(this.path_);
    }

    public int getPathCount() {
        return this.path_.size();
    }

    public int getPath(int i) {
        return ((Integer) this.path_.get(i)).intValue();
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder setPath(int i, int i2) {
        ensurePathIsMutable();
        this.path_.set(i, Integer.valueOf(i2));
        onChanged();
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder addPath(int i) {
        ensurePathIsMutable();
        this.path_.add(Integer.valueOf(i));
        onChanged();
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder addAllPath(Iterable<? extends Integer> iterable) {
        ensurePathIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, this.path_);
        onChanged();
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder clearPath() {
        this.path_ = Collections.emptyList();
        this.bitField0_ &= -2;
        onChanged();
        return this;
    }

    public boolean hasSourceFile() {
        return (this.bitField0_ & 2) == 2;
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

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder setSourceFile(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 2;
        this.sourceFile_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder clearSourceFile() {
        this.bitField0_ &= -3;
        this.sourceFile_ = Annotation.getDefaultInstance().getSourceFile();
        onChanged();
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder setSourceFileBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 2;
        this.sourceFile_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasBegin() {
        return (this.bitField0_ & 4) == 4;
    }

    public int getBegin() {
        return this.begin_;
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder setBegin(int i) {
        this.bitField0_ |= 4;
        this.begin_ = i;
        onChanged();
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder clearBegin() {
        this.bitField0_ &= -5;
        this.begin_ = 0;
        onChanged();
        return this;
    }

    public boolean hasEnd() {
        return (this.bitField0_ & 8) == 8;
    }

    public int getEnd() {
        return this.end_;
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder setEnd(int i) {
        this.bitField0_ |= 8;
        this.end_ = i;
        onChanged();
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder clearEnd() {
        this.bitField0_ &= -9;
        this.end_ = 0;
        onChanged();
        return this;
    }

    public final DescriptorProtos$GeneratedCodeInfo$Annotation$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$GeneratedCodeInfo$Annotation$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$GeneratedCodeInfo$Annotation$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$GeneratedCodeInfo$Annotation$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
