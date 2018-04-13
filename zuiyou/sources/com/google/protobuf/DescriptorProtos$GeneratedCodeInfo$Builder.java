package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.GeneratedCodeInfo.Annotation;
import com.google.protobuf.DescriptorProtos.GeneratedCodeInfoOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$GeneratedCodeInfo$Builder extends Builder<DescriptorProtos$GeneratedCodeInfo$Builder> implements GeneratedCodeInfoOrBuilder {
    private RepeatedFieldBuilderV3<Annotation, DescriptorProtos$GeneratedCodeInfo$Annotation$Builder, DescriptorProtos$GeneratedCodeInfo$AnnotationOrBuilder> annotationBuilder_;
    private List<Annotation> annotation_;
    private int bitField0_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_GeneratedCodeInfo_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_GeneratedCodeInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$GeneratedCodeInfo.class, DescriptorProtos$GeneratedCodeInfo$Builder.class);
    }

    private DescriptorProtos$GeneratedCodeInfo$Builder() {
        this.annotation_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$GeneratedCodeInfo$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.annotation_ = Collections.emptyList();
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getAnnotationFieldBuilder();
        }
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder clear() {
        super.clear();
        if (this.annotationBuilder_ == null) {
            this.annotation_ = Collections.emptyList();
            this.bitField0_ &= -2;
        } else {
            this.annotationBuilder_.clear();
        }
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_GeneratedCodeInfo_descriptor;
    }

    public DescriptorProtos$GeneratedCodeInfo getDefaultInstanceForType() {
        return DescriptorProtos$GeneratedCodeInfo.getDefaultInstance();
    }

    public DescriptorProtos$GeneratedCodeInfo build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$GeneratedCodeInfo buildPartial() {
        DescriptorProtos$GeneratedCodeInfo descriptorProtos$GeneratedCodeInfo = new DescriptorProtos$GeneratedCodeInfo(this, null);
        int i = this.bitField0_;
        if (this.annotationBuilder_ == null) {
            if ((this.bitField0_ & 1) == 1) {
                this.annotation_ = Collections.unmodifiableList(this.annotation_);
                this.bitField0_ &= -2;
            }
            DescriptorProtos$GeneratedCodeInfo.access$29302(descriptorProtos$GeneratedCodeInfo, this.annotation_);
        } else {
            DescriptorProtos$GeneratedCodeInfo.access$29302(descriptorProtos$GeneratedCodeInfo, this.annotationBuilder_.build());
        }
        onBuilt();
        return descriptorProtos$GeneratedCodeInfo;
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder clone() {
        return (DescriptorProtos$GeneratedCodeInfo$Builder) super.clone();
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$GeneratedCodeInfo$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$GeneratedCodeInfo$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$GeneratedCodeInfo$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$GeneratedCodeInfo$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$GeneratedCodeInfo$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$GeneratedCodeInfo) {
            return mergeFrom((DescriptorProtos$GeneratedCodeInfo) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder mergeFrom(DescriptorProtos$GeneratedCodeInfo descriptorProtos$GeneratedCodeInfo) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (descriptorProtos$GeneratedCodeInfo != DescriptorProtos$GeneratedCodeInfo.getDefaultInstance()) {
            if (this.annotationBuilder_ == null) {
                if (!DescriptorProtos$GeneratedCodeInfo.access$29300(descriptorProtos$GeneratedCodeInfo).isEmpty()) {
                    if (this.annotation_.isEmpty()) {
                        this.annotation_ = DescriptorProtos$GeneratedCodeInfo.access$29300(descriptorProtos$GeneratedCodeInfo);
                        this.bitField0_ &= -2;
                    } else {
                        ensureAnnotationIsMutable();
                        this.annotation_.addAll(DescriptorProtos$GeneratedCodeInfo.access$29300(descriptorProtos$GeneratedCodeInfo));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$GeneratedCodeInfo.access$29300(descriptorProtos$GeneratedCodeInfo).isEmpty()) {
                if (this.annotationBuilder_.isEmpty()) {
                    this.annotationBuilder_.dispose();
                    this.annotationBuilder_ = null;
                    this.annotation_ = DescriptorProtos$GeneratedCodeInfo.access$29300(descriptorProtos$GeneratedCodeInfo);
                    this.bitField0_ &= -2;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getAnnotationFieldBuilder();
                    }
                    this.annotationBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.annotationBuilder_.addAllMessages(DescriptorProtos$GeneratedCodeInfo.access$29300(descriptorProtos$GeneratedCodeInfo));
                }
            }
            mergeUnknownFields(descriptorProtos$GeneratedCodeInfo.unknownFields);
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        return true;
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        DescriptorProtos$GeneratedCodeInfo descriptorProtos$GeneratedCodeInfo;
        Throwable th;
        DescriptorProtos$GeneratedCodeInfo descriptorProtos$GeneratedCodeInfo2;
        try {
            descriptorProtos$GeneratedCodeInfo = (DescriptorProtos$GeneratedCodeInfo) DescriptorProtos$GeneratedCodeInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$GeneratedCodeInfo != null) {
                mergeFrom(descriptorProtos$GeneratedCodeInfo);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$GeneratedCodeInfo = (DescriptorProtos$GeneratedCodeInfo) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$GeneratedCodeInfo2 = descriptorProtos$GeneratedCodeInfo;
            th = th3;
            if (descriptorProtos$GeneratedCodeInfo2 != null) {
                mergeFrom(descriptorProtos$GeneratedCodeInfo2);
            }
            throw th;
        }
    }

    private void ensureAnnotationIsMutable() {
        if ((this.bitField0_ & 1) != 1) {
            this.annotation_ = new ArrayList(this.annotation_);
            this.bitField0_ |= 1;
        }
    }

    public List<Annotation> getAnnotationList() {
        if (this.annotationBuilder_ == null) {
            return Collections.unmodifiableList(this.annotation_);
        }
        return this.annotationBuilder_.getMessageList();
    }

    public int getAnnotationCount() {
        if (this.annotationBuilder_ == null) {
            return this.annotation_.size();
        }
        return this.annotationBuilder_.getCount();
    }

    public Annotation getAnnotation(int i) {
        if (this.annotationBuilder_ == null) {
            return (Annotation) this.annotation_.get(i);
        }
        return (Annotation) this.annotationBuilder_.getMessage(i);
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder setAnnotation(int i, Annotation annotation) {
        if (this.annotationBuilder_ != null) {
            this.annotationBuilder_.setMessage(i, annotation);
        } else if (annotation == null) {
            throw new NullPointerException();
        } else {
            ensureAnnotationIsMutable();
            this.annotation_.set(i, annotation);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder setAnnotation(int i, DescriptorProtos$GeneratedCodeInfo$Annotation$Builder descriptorProtos$GeneratedCodeInfo$Annotation$Builder) {
        if (this.annotationBuilder_ == null) {
            ensureAnnotationIsMutable();
            this.annotation_.set(i, descriptorProtos$GeneratedCodeInfo$Annotation$Builder.build());
            onChanged();
        } else {
            this.annotationBuilder_.setMessage(i, descriptorProtos$GeneratedCodeInfo$Annotation$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder addAnnotation(Annotation annotation) {
        if (this.annotationBuilder_ != null) {
            this.annotationBuilder_.addMessage(annotation);
        } else if (annotation == null) {
            throw new NullPointerException();
        } else {
            ensureAnnotationIsMutable();
            this.annotation_.add(annotation);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder addAnnotation(int i, Annotation annotation) {
        if (this.annotationBuilder_ != null) {
            this.annotationBuilder_.addMessage(i, annotation);
        } else if (annotation == null) {
            throw new NullPointerException();
        } else {
            ensureAnnotationIsMutable();
            this.annotation_.add(i, annotation);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder addAnnotation(DescriptorProtos$GeneratedCodeInfo$Annotation$Builder descriptorProtos$GeneratedCodeInfo$Annotation$Builder) {
        if (this.annotationBuilder_ == null) {
            ensureAnnotationIsMutable();
            this.annotation_.add(descriptorProtos$GeneratedCodeInfo$Annotation$Builder.build());
            onChanged();
        } else {
            this.annotationBuilder_.addMessage(descriptorProtos$GeneratedCodeInfo$Annotation$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder addAnnotation(int i, DescriptorProtos$GeneratedCodeInfo$Annotation$Builder descriptorProtos$GeneratedCodeInfo$Annotation$Builder) {
        if (this.annotationBuilder_ == null) {
            ensureAnnotationIsMutable();
            this.annotation_.add(i, descriptorProtos$GeneratedCodeInfo$Annotation$Builder.build());
            onChanged();
        } else {
            this.annotationBuilder_.addMessage(i, descriptorProtos$GeneratedCodeInfo$Annotation$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder addAllAnnotation(Iterable<? extends Annotation> iterable) {
        if (this.annotationBuilder_ == null) {
            ensureAnnotationIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.annotation_);
            onChanged();
        } else {
            this.annotationBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder clearAnnotation() {
        if (this.annotationBuilder_ == null) {
            this.annotation_ = Collections.emptyList();
            this.bitField0_ &= -2;
            onChanged();
        } else {
            this.annotationBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Builder removeAnnotation(int i) {
        if (this.annotationBuilder_ == null) {
            ensureAnnotationIsMutable();
            this.annotation_.remove(i);
            onChanged();
        } else {
            this.annotationBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder getAnnotationBuilder(int i) {
        return (DescriptorProtos$GeneratedCodeInfo$Annotation$Builder) getAnnotationFieldBuilder().getBuilder(i);
    }

    public DescriptorProtos$GeneratedCodeInfo$AnnotationOrBuilder getAnnotationOrBuilder(int i) {
        if (this.annotationBuilder_ == null) {
            return (DescriptorProtos$GeneratedCodeInfo$AnnotationOrBuilder) this.annotation_.get(i);
        }
        return (DescriptorProtos$GeneratedCodeInfo$AnnotationOrBuilder) this.annotationBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends DescriptorProtos$GeneratedCodeInfo$AnnotationOrBuilder> getAnnotationOrBuilderList() {
        if (this.annotationBuilder_ != null) {
            return this.annotationBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.annotation_);
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder addAnnotationBuilder() {
        return (DescriptorProtos$GeneratedCodeInfo$Annotation$Builder) getAnnotationFieldBuilder().addBuilder(Annotation.getDefaultInstance());
    }

    public DescriptorProtos$GeneratedCodeInfo$Annotation$Builder addAnnotationBuilder(int i) {
        return (DescriptorProtos$GeneratedCodeInfo$Annotation$Builder) getAnnotationFieldBuilder().addBuilder(i, Annotation.getDefaultInstance());
    }

    public List<DescriptorProtos$GeneratedCodeInfo$Annotation$Builder> getAnnotationBuilderList() {
        return getAnnotationFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<Annotation, DescriptorProtos$GeneratedCodeInfo$Annotation$Builder, DescriptorProtos$GeneratedCodeInfo$AnnotationOrBuilder> getAnnotationFieldBuilder() {
        boolean z = true;
        if (this.annotationBuilder_ == null) {
            List list = this.annotation_;
            if ((this.bitField0_ & 1) != 1) {
                z = false;
            }
            this.annotationBuilder_ = new RepeatedFieldBuilderV3(list, z, getParentForChildren(), isClean());
            this.annotation_ = null;
        }
        return this.annotationBuilder_;
    }

    public final DescriptorProtos$GeneratedCodeInfo$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$GeneratedCodeInfo$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$GeneratedCodeInfo$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$GeneratedCodeInfo$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
