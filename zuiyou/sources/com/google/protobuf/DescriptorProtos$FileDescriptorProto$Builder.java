package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.EnumDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.FieldDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.ServiceDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.SourceCodeInfoOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$FileDescriptorProto$Builder extends Builder<DescriptorProtos$FileDescriptorProto$Builder> implements FileDescriptorProtoOrBuilder {
    private int bitField0_;
    private LazyStringList dependency_;
    private RepeatedFieldBuilderV3<DescriptorProtos$EnumDescriptorProto, DescriptorProtos$EnumDescriptorProto$Builder, EnumDescriptorProtoOrBuilder> enumTypeBuilder_;
    private List<DescriptorProtos$EnumDescriptorProto> enumType_;
    private RepeatedFieldBuilderV3<DescriptorProtos$FieldDescriptorProto, DescriptorProtos$FieldDescriptorProto$Builder, FieldDescriptorProtoOrBuilder> extensionBuilder_;
    private List<DescriptorProtos$FieldDescriptorProto> extension_;
    private RepeatedFieldBuilderV3<DescriptorProtos$DescriptorProto, DescriptorProtos$DescriptorProto$Builder, DescriptorProtoOrBuilder> messageTypeBuilder_;
    private List<DescriptorProtos$DescriptorProto> messageType_;
    private Object name_;
    private SingleFieldBuilderV3<DescriptorProtos$FileOptions, DescriptorProtos$FileOptions.Builder, DescriptorProtos$FileOptionsOrBuilder> optionsBuilder_;
    private DescriptorProtos$FileOptions options_;
    private Object package_;
    private List<Integer> publicDependency_;
    private RepeatedFieldBuilderV3<DescriptorProtos$ServiceDescriptorProto, DescriptorProtos$ServiceDescriptorProto$Builder, ServiceDescriptorProtoOrBuilder> serviceBuilder_;
    private List<DescriptorProtos$ServiceDescriptorProto> service_;
    private SingleFieldBuilderV3<DescriptorProtos$SourceCodeInfo, DescriptorProtos$SourceCodeInfo$Builder, SourceCodeInfoOrBuilder> sourceCodeInfoBuilder_;
    private DescriptorProtos$SourceCodeInfo sourceCodeInfo_;
    private Object syntax_;
    private List<Integer> weakDependency_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$FileDescriptorProto.class, DescriptorProtos$FileDescriptorProto$Builder.class);
    }

    private DescriptorProtos$FileDescriptorProto$Builder() {
        this.name_ = "";
        this.package_ = "";
        this.dependency_ = LazyStringArrayList.EMPTY;
        this.publicDependency_ = Collections.emptyList();
        this.weakDependency_ = Collections.emptyList();
        this.messageType_ = Collections.emptyList();
        this.enumType_ = Collections.emptyList();
        this.service_ = Collections.emptyList();
        this.extension_ = Collections.emptyList();
        this.options_ = null;
        this.sourceCodeInfo_ = null;
        this.syntax_ = "";
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$FileDescriptorProto$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.name_ = "";
        this.package_ = "";
        this.dependency_ = LazyStringArrayList.EMPTY;
        this.publicDependency_ = Collections.emptyList();
        this.weakDependency_ = Collections.emptyList();
        this.messageType_ = Collections.emptyList();
        this.enumType_ = Collections.emptyList();
        this.service_ = Collections.emptyList();
        this.extension_ = Collections.emptyList();
        this.options_ = null;
        this.sourceCodeInfo_ = null;
        this.syntax_ = "";
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getMessageTypeFieldBuilder();
            getEnumTypeFieldBuilder();
            getServiceFieldBuilder();
            getExtensionFieldBuilder();
            getOptionsFieldBuilder();
            getSourceCodeInfoFieldBuilder();
        }
    }

    public DescriptorProtos$FileDescriptorProto$Builder clear() {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        this.package_ = "";
        this.bitField0_ &= -3;
        this.dependency_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= -5;
        this.publicDependency_ = Collections.emptyList();
        this.bitField0_ &= -9;
        this.weakDependency_ = Collections.emptyList();
        this.bitField0_ &= -17;
        if (this.messageTypeBuilder_ == null) {
            this.messageType_ = Collections.emptyList();
            this.bitField0_ &= -33;
        } else {
            this.messageTypeBuilder_.clear();
        }
        if (this.enumTypeBuilder_ == null) {
            this.enumType_ = Collections.emptyList();
            this.bitField0_ &= -65;
        } else {
            this.enumTypeBuilder_.clear();
        }
        if (this.serviceBuilder_ == null) {
            this.service_ = Collections.emptyList();
            this.bitField0_ &= -129;
        } else {
            this.serviceBuilder_.clear();
        }
        if (this.extensionBuilder_ == null) {
            this.extension_ = Collections.emptyList();
            this.bitField0_ &= -257;
        } else {
            this.extensionBuilder_.clear();
        }
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -513;
        if (this.sourceCodeInfoBuilder_ == null) {
            this.sourceCodeInfo_ = null;
        } else {
            this.sourceCodeInfoBuilder_.clear();
        }
        this.bitField0_ &= -1025;
        this.syntax_ = "";
        this.bitField0_ &= -2049;
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_descriptor;
    }

    public DescriptorProtos$FileDescriptorProto getDefaultInstanceForType() {
        return DescriptorProtos$FileDescriptorProto.getDefaultInstance();
    }

    public DescriptorProtos$FileDescriptorProto build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$FileDescriptorProto buildPartial() {
        int i;
        int i2 = 1;
        DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto = new DescriptorProtos$FileDescriptorProto(this, null);
        int i3 = this.bitField0_;
        if ((i3 & 1) != 1) {
            i2 = 0;
        }
        DescriptorProtos$FileDescriptorProto.access$1202(descriptorProtos$FileDescriptorProto, this.name_);
        if ((i3 & 2) == 2) {
            i2 |= 2;
        }
        DescriptorProtos$FileDescriptorProto.access$1302(descriptorProtos$FileDescriptorProto, this.package_);
        if ((this.bitField0_ & 4) == 4) {
            this.dependency_ = this.dependency_.getUnmodifiableView();
            this.bitField0_ &= -5;
        }
        DescriptorProtos$FileDescriptorProto.access$1402(descriptorProtos$FileDescriptorProto, this.dependency_);
        if ((this.bitField0_ & 8) == 8) {
            this.publicDependency_ = Collections.unmodifiableList(this.publicDependency_);
            this.bitField0_ &= -9;
        }
        DescriptorProtos$FileDescriptorProto.access$1502(descriptorProtos$FileDescriptorProto, this.publicDependency_);
        if ((this.bitField0_ & 16) == 16) {
            this.weakDependency_ = Collections.unmodifiableList(this.weakDependency_);
            this.bitField0_ &= -17;
        }
        DescriptorProtos$FileDescriptorProto.access$1602(descriptorProtos$FileDescriptorProto, this.weakDependency_);
        if (this.messageTypeBuilder_ == null) {
            if ((this.bitField0_ & 32) == 32) {
                this.messageType_ = Collections.unmodifiableList(this.messageType_);
                this.bitField0_ &= -33;
            }
            DescriptorProtos$FileDescriptorProto.access$1702(descriptorProtos$FileDescriptorProto, this.messageType_);
        } else {
            DescriptorProtos$FileDescriptorProto.access$1702(descriptorProtos$FileDescriptorProto, this.messageTypeBuilder_.build());
        }
        if (this.enumTypeBuilder_ == null) {
            if ((this.bitField0_ & 64) == 64) {
                this.enumType_ = Collections.unmodifiableList(this.enumType_);
                this.bitField0_ &= -65;
            }
            DescriptorProtos$FileDescriptorProto.access$1802(descriptorProtos$FileDescriptorProto, this.enumType_);
        } else {
            DescriptorProtos$FileDescriptorProto.access$1802(descriptorProtos$FileDescriptorProto, this.enumTypeBuilder_.build());
        }
        if (this.serviceBuilder_ == null) {
            if ((this.bitField0_ & 128) == 128) {
                this.service_ = Collections.unmodifiableList(this.service_);
                this.bitField0_ &= -129;
            }
            DescriptorProtos$FileDescriptorProto.access$1902(descriptorProtos$FileDescriptorProto, this.service_);
        } else {
            DescriptorProtos$FileDescriptorProto.access$1902(descriptorProtos$FileDescriptorProto, this.serviceBuilder_.build());
        }
        if (this.extensionBuilder_ == null) {
            if ((this.bitField0_ & 256) == 256) {
                this.extension_ = Collections.unmodifiableList(this.extension_);
                this.bitField0_ &= -257;
            }
            DescriptorProtos$FileDescriptorProto.access$2002(descriptorProtos$FileDescriptorProto, this.extension_);
        } else {
            DescriptorProtos$FileDescriptorProto.access$2002(descriptorProtos$FileDescriptorProto, this.extensionBuilder_.build());
        }
        if ((i3 & 512) == 512) {
            i = i2 | 4;
        } else {
            i = i2;
        }
        if (this.optionsBuilder_ == null) {
            DescriptorProtos$FileDescriptorProto.access$2102(descriptorProtos$FileDescriptorProto, this.options_);
        } else {
            DescriptorProtos$FileDescriptorProto.access$2102(descriptorProtos$FileDescriptorProto, (DescriptorProtos$FileOptions) this.optionsBuilder_.build());
        }
        if ((i3 & 1024) == 1024) {
            i |= 8;
        }
        if (this.sourceCodeInfoBuilder_ == null) {
            DescriptorProtos$FileDescriptorProto.access$2202(descriptorProtos$FileDescriptorProto, this.sourceCodeInfo_);
        } else {
            DescriptorProtos$FileDescriptorProto.access$2202(descriptorProtos$FileDescriptorProto, (DescriptorProtos$SourceCodeInfo) this.sourceCodeInfoBuilder_.build());
        }
        if ((i3 & 2048) == 2048) {
            i |= 16;
        }
        DescriptorProtos$FileDescriptorProto.access$2302(descriptorProtos$FileDescriptorProto, this.syntax_);
        DescriptorProtos$FileDescriptorProto.access$2402(descriptorProtos$FileDescriptorProto, i);
        onBuilt();
        return descriptorProtos$FileDescriptorProto;
    }

    public DescriptorProtos$FileDescriptorProto$Builder clone() {
        return (DescriptorProtos$FileDescriptorProto$Builder) super.clone();
    }

    public DescriptorProtos$FileDescriptorProto$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$FileDescriptorProto$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$FileDescriptorProto$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$FileDescriptorProto$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$FileDescriptorProto$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$FileDescriptorProto$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$FileDescriptorProto$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$FileDescriptorProto$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$FileDescriptorProto$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$FileDescriptorProto$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$FileDescriptorProto$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$FileDescriptorProto) {
            return mergeFrom((DescriptorProtos$FileDescriptorProto) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder mergeFrom(DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (descriptorProtos$FileDescriptorProto != DescriptorProtos$FileDescriptorProto.getDefaultInstance()) {
            if (descriptorProtos$FileDescriptorProto.hasName()) {
                this.bitField0_ |= 1;
                this.name_ = DescriptorProtos$FileDescriptorProto.access$1200(descriptorProtos$FileDescriptorProto);
                onChanged();
            }
            if (descriptorProtos$FileDescriptorProto.hasPackage()) {
                this.bitField0_ |= 2;
                this.package_ = DescriptorProtos$FileDescriptorProto.access$1300(descriptorProtos$FileDescriptorProto);
                onChanged();
            }
            if (!DescriptorProtos$FileDescriptorProto.access$1400(descriptorProtos$FileDescriptorProto).isEmpty()) {
                if (this.dependency_.isEmpty()) {
                    this.dependency_ = DescriptorProtos$FileDescriptorProto.access$1400(descriptorProtos$FileDescriptorProto);
                    this.bitField0_ &= -5;
                } else {
                    ensureDependencyIsMutable();
                    this.dependency_.addAll(DescriptorProtos$FileDescriptorProto.access$1400(descriptorProtos$FileDescriptorProto));
                }
                onChanged();
            }
            if (!DescriptorProtos$FileDescriptorProto.access$1500(descriptorProtos$FileDescriptorProto).isEmpty()) {
                if (this.publicDependency_.isEmpty()) {
                    this.publicDependency_ = DescriptorProtos$FileDescriptorProto.access$1500(descriptorProtos$FileDescriptorProto);
                    this.bitField0_ &= -9;
                } else {
                    ensurePublicDependencyIsMutable();
                    this.publicDependency_.addAll(DescriptorProtos$FileDescriptorProto.access$1500(descriptorProtos$FileDescriptorProto));
                }
                onChanged();
            }
            if (!DescriptorProtos$FileDescriptorProto.access$1600(descriptorProtos$FileDescriptorProto).isEmpty()) {
                if (this.weakDependency_.isEmpty()) {
                    this.weakDependency_ = DescriptorProtos$FileDescriptorProto.access$1600(descriptorProtos$FileDescriptorProto);
                    this.bitField0_ &= -17;
                } else {
                    ensureWeakDependencyIsMutable();
                    this.weakDependency_.addAll(DescriptorProtos$FileDescriptorProto.access$1600(descriptorProtos$FileDescriptorProto));
                }
                onChanged();
            }
            if (this.messageTypeBuilder_ == null) {
                if (!DescriptorProtos$FileDescriptorProto.access$1700(descriptorProtos$FileDescriptorProto).isEmpty()) {
                    if (this.messageType_.isEmpty()) {
                        this.messageType_ = DescriptorProtos$FileDescriptorProto.access$1700(descriptorProtos$FileDescriptorProto);
                        this.bitField0_ &= -33;
                    } else {
                        ensureMessageTypeIsMutable();
                        this.messageType_.addAll(DescriptorProtos$FileDescriptorProto.access$1700(descriptorProtos$FileDescriptorProto));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$FileDescriptorProto.access$1700(descriptorProtos$FileDescriptorProto).isEmpty()) {
                if (this.messageTypeBuilder_.isEmpty()) {
                    this.messageTypeBuilder_.dispose();
                    this.messageTypeBuilder_ = null;
                    this.messageType_ = DescriptorProtos$FileDescriptorProto.access$1700(descriptorProtos$FileDescriptorProto);
                    this.bitField0_ &= -33;
                    this.messageTypeBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getMessageTypeFieldBuilder() : null;
                } else {
                    this.messageTypeBuilder_.addAllMessages(DescriptorProtos$FileDescriptorProto.access$1700(descriptorProtos$FileDescriptorProto));
                }
            }
            if (this.enumTypeBuilder_ == null) {
                if (!DescriptorProtos$FileDescriptorProto.access$1800(descriptorProtos$FileDescriptorProto).isEmpty()) {
                    if (this.enumType_.isEmpty()) {
                        this.enumType_ = DescriptorProtos$FileDescriptorProto.access$1800(descriptorProtos$FileDescriptorProto);
                        this.bitField0_ &= -65;
                    } else {
                        ensureEnumTypeIsMutable();
                        this.enumType_.addAll(DescriptorProtos$FileDescriptorProto.access$1800(descriptorProtos$FileDescriptorProto));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$FileDescriptorProto.access$1800(descriptorProtos$FileDescriptorProto).isEmpty()) {
                if (this.enumTypeBuilder_.isEmpty()) {
                    this.enumTypeBuilder_.dispose();
                    this.enumTypeBuilder_ = null;
                    this.enumType_ = DescriptorProtos$FileDescriptorProto.access$1800(descriptorProtos$FileDescriptorProto);
                    this.bitField0_ &= -65;
                    this.enumTypeBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getEnumTypeFieldBuilder() : null;
                } else {
                    this.enumTypeBuilder_.addAllMessages(DescriptorProtos$FileDescriptorProto.access$1800(descriptorProtos$FileDescriptorProto));
                }
            }
            if (this.serviceBuilder_ == null) {
                if (!DescriptorProtos$FileDescriptorProto.access$1900(descriptorProtos$FileDescriptorProto).isEmpty()) {
                    if (this.service_.isEmpty()) {
                        this.service_ = DescriptorProtos$FileDescriptorProto.access$1900(descriptorProtos$FileDescriptorProto);
                        this.bitField0_ &= -129;
                    } else {
                        ensureServiceIsMutable();
                        this.service_.addAll(DescriptorProtos$FileDescriptorProto.access$1900(descriptorProtos$FileDescriptorProto));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$FileDescriptorProto.access$1900(descriptorProtos$FileDescriptorProto).isEmpty()) {
                if (this.serviceBuilder_.isEmpty()) {
                    this.serviceBuilder_.dispose();
                    this.serviceBuilder_ = null;
                    this.service_ = DescriptorProtos$FileDescriptorProto.access$1900(descriptorProtos$FileDescriptorProto);
                    this.bitField0_ &= -129;
                    this.serviceBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getServiceFieldBuilder() : null;
                } else {
                    this.serviceBuilder_.addAllMessages(DescriptorProtos$FileDescriptorProto.access$1900(descriptorProtos$FileDescriptorProto));
                }
            }
            if (this.extensionBuilder_ == null) {
                if (!DescriptorProtos$FileDescriptorProto.access$2000(descriptorProtos$FileDescriptorProto).isEmpty()) {
                    if (this.extension_.isEmpty()) {
                        this.extension_ = DescriptorProtos$FileDescriptorProto.access$2000(descriptorProtos$FileDescriptorProto);
                        this.bitField0_ &= -257;
                    } else {
                        ensureExtensionIsMutable();
                        this.extension_.addAll(DescriptorProtos$FileDescriptorProto.access$2000(descriptorProtos$FileDescriptorProto));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$FileDescriptorProto.access$2000(descriptorProtos$FileDescriptorProto).isEmpty()) {
                if (this.extensionBuilder_.isEmpty()) {
                    this.extensionBuilder_.dispose();
                    this.extensionBuilder_ = null;
                    this.extension_ = DescriptorProtos$FileDescriptorProto.access$2000(descriptorProtos$FileDescriptorProto);
                    this.bitField0_ &= -257;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getExtensionFieldBuilder();
                    }
                    this.extensionBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.extensionBuilder_.addAllMessages(DescriptorProtos$FileDescriptorProto.access$2000(descriptorProtos$FileDescriptorProto));
                }
            }
            if (descriptorProtos$FileDescriptorProto.hasOptions()) {
                mergeOptions(descriptorProtos$FileDescriptorProto.getOptions());
            }
            if (descriptorProtos$FileDescriptorProto.hasSourceCodeInfo()) {
                mergeSourceCodeInfo(descriptorProtos$FileDescriptorProto.getSourceCodeInfo());
            }
            if (descriptorProtos$FileDescriptorProto.hasSyntax()) {
                this.bitField0_ |= 2048;
                this.syntax_ = DescriptorProtos$FileDescriptorProto.access$2300(descriptorProtos$FileDescriptorProto);
                onChanged();
            }
            mergeUnknownFields(descriptorProtos$FileDescriptorProto.unknownFields);
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        int i;
        for (i = 0; i < getMessageTypeCount(); i++) {
            if (!getMessageType(i).isInitialized()) {
                return false;
            }
        }
        for (i = 0; i < getEnumTypeCount(); i++) {
            if (!getEnumType(i).isInitialized()) {
                return false;
            }
        }
        for (i = 0; i < getServiceCount(); i++) {
            if (!getService(i).isInitialized()) {
                return false;
            }
        }
        for (i = 0; i < getExtensionCount(); i++) {
            if (!getExtension(i).isInitialized()) {
                return false;
            }
        }
        if (!hasOptions() || getOptions().isInitialized()) {
            return true;
        }
        return false;
    }

    public DescriptorProtos$FileDescriptorProto$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Throwable th;
        DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto;
        DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto2;
        try {
            descriptorProtos$FileDescriptorProto2 = (DescriptorProtos$FileDescriptorProto) DescriptorProtos$FileDescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$FileDescriptorProto2 != null) {
                mergeFrom(descriptorProtos$FileDescriptorProto2);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$FileDescriptorProto2 = (DescriptorProtos$FileDescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$FileDescriptorProto = descriptorProtos$FileDescriptorProto2;
            th = th3;
            if (descriptorProtos$FileDescriptorProto != null) {
                mergeFrom(descriptorProtos$FileDescriptorProto);
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

    public DescriptorProtos$FileDescriptorProto$Builder setName(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder clearName() {
        this.bitField0_ &= -2;
        this.name_ = DescriptorProtos$FileDescriptorProto.getDefaultInstance().getName();
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder setNameBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = byteString;
        onChanged();
        return this;
    }

    public boolean hasPackage() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getPackage() {
        Object obj = this.package_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.package_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getPackageBytes() {
        Object obj = this.package_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.package_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public DescriptorProtos$FileDescriptorProto$Builder setPackage(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 2;
        this.package_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder clearPackage() {
        this.bitField0_ &= -3;
        this.package_ = DescriptorProtos$FileDescriptorProto.getDefaultInstance().getPackage();
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder setPackageBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 2;
        this.package_ = byteString;
        onChanged();
        return this;
    }

    private void ensureDependencyIsMutable() {
        if ((this.bitField0_ & 4) != 4) {
            this.dependency_ = new LazyStringArrayList(this.dependency_);
            this.bitField0_ |= 4;
        }
    }

    public ProtocolStringList getDependencyList() {
        return this.dependency_.getUnmodifiableView();
    }

    public int getDependencyCount() {
        return this.dependency_.size();
    }

    public String getDependency(int i) {
        return (String) this.dependency_.get(i);
    }

    public ByteString getDependencyBytes(int i) {
        return this.dependency_.getByteString(i);
    }

    public DescriptorProtos$FileDescriptorProto$Builder setDependency(int i, String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        ensureDependencyIsMutable();
        this.dependency_.set(i, str);
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addDependency(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        ensureDependencyIsMutable();
        this.dependency_.add(str);
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addAllDependency(Iterable<String> iterable) {
        ensureDependencyIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, this.dependency_);
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder clearDependency() {
        this.dependency_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= -5;
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addDependencyBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        ensureDependencyIsMutable();
        this.dependency_.add(byteString);
        onChanged();
        return this;
    }

    private void ensurePublicDependencyIsMutable() {
        if ((this.bitField0_ & 8) != 8) {
            this.publicDependency_ = new ArrayList(this.publicDependency_);
            this.bitField0_ |= 8;
        }
    }

    public List<Integer> getPublicDependencyList() {
        return Collections.unmodifiableList(this.publicDependency_);
    }

    public int getPublicDependencyCount() {
        return this.publicDependency_.size();
    }

    public int getPublicDependency(int i) {
        return ((Integer) this.publicDependency_.get(i)).intValue();
    }

    public DescriptorProtos$FileDescriptorProto$Builder setPublicDependency(int i, int i2) {
        ensurePublicDependencyIsMutable();
        this.publicDependency_.set(i, Integer.valueOf(i2));
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addPublicDependency(int i) {
        ensurePublicDependencyIsMutable();
        this.publicDependency_.add(Integer.valueOf(i));
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addAllPublicDependency(Iterable<? extends Integer> iterable) {
        ensurePublicDependencyIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, this.publicDependency_);
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder clearPublicDependency() {
        this.publicDependency_ = Collections.emptyList();
        this.bitField0_ &= -9;
        onChanged();
        return this;
    }

    private void ensureWeakDependencyIsMutable() {
        if ((this.bitField0_ & 16) != 16) {
            this.weakDependency_ = new ArrayList(this.weakDependency_);
            this.bitField0_ |= 16;
        }
    }

    public List<Integer> getWeakDependencyList() {
        return Collections.unmodifiableList(this.weakDependency_);
    }

    public int getWeakDependencyCount() {
        return this.weakDependency_.size();
    }

    public int getWeakDependency(int i) {
        return ((Integer) this.weakDependency_.get(i)).intValue();
    }

    public DescriptorProtos$FileDescriptorProto$Builder setWeakDependency(int i, int i2) {
        ensureWeakDependencyIsMutable();
        this.weakDependency_.set(i, Integer.valueOf(i2));
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addWeakDependency(int i) {
        ensureWeakDependencyIsMutable();
        this.weakDependency_.add(Integer.valueOf(i));
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addAllWeakDependency(Iterable<? extends Integer> iterable) {
        ensureWeakDependencyIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, this.weakDependency_);
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder clearWeakDependency() {
        this.weakDependency_ = Collections.emptyList();
        this.bitField0_ &= -17;
        onChanged();
        return this;
    }

    private void ensureMessageTypeIsMutable() {
        if ((this.bitField0_ & 32) != 32) {
            this.messageType_ = new ArrayList(this.messageType_);
            this.bitField0_ |= 32;
        }
    }

    public List<DescriptorProtos$DescriptorProto> getMessageTypeList() {
        if (this.messageTypeBuilder_ == null) {
            return Collections.unmodifiableList(this.messageType_);
        }
        return this.messageTypeBuilder_.getMessageList();
    }

    public int getMessageTypeCount() {
        if (this.messageTypeBuilder_ == null) {
            return this.messageType_.size();
        }
        return this.messageTypeBuilder_.getCount();
    }

    public DescriptorProtos$DescriptorProto getMessageType(int i) {
        if (this.messageTypeBuilder_ == null) {
            return (DescriptorProtos$DescriptorProto) this.messageType_.get(i);
        }
        return (DescriptorProtos$DescriptorProto) this.messageTypeBuilder_.getMessage(i);
    }

    public DescriptorProtos$FileDescriptorProto$Builder setMessageType(int i, DescriptorProtos$DescriptorProto descriptorProtos$DescriptorProto) {
        if (this.messageTypeBuilder_ != null) {
            this.messageTypeBuilder_.setMessage(i, descriptorProtos$DescriptorProto);
        } else if (descriptorProtos$DescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureMessageTypeIsMutable();
            this.messageType_.set(i, descriptorProtos$DescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder setMessageType(int i, DescriptorProtos$DescriptorProto$Builder descriptorProtos$DescriptorProto$Builder) {
        if (this.messageTypeBuilder_ == null) {
            ensureMessageTypeIsMutable();
            this.messageType_.set(i, descriptorProtos$DescriptorProto$Builder.build());
            onChanged();
        } else {
            this.messageTypeBuilder_.setMessage(i, descriptorProtos$DescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addMessageType(DescriptorProtos$DescriptorProto descriptorProtos$DescriptorProto) {
        if (this.messageTypeBuilder_ != null) {
            this.messageTypeBuilder_.addMessage(descriptorProtos$DescriptorProto);
        } else if (descriptorProtos$DescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureMessageTypeIsMutable();
            this.messageType_.add(descriptorProtos$DescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addMessageType(int i, DescriptorProtos$DescriptorProto descriptorProtos$DescriptorProto) {
        if (this.messageTypeBuilder_ != null) {
            this.messageTypeBuilder_.addMessage(i, descriptorProtos$DescriptorProto);
        } else if (descriptorProtos$DescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureMessageTypeIsMutable();
            this.messageType_.add(i, descriptorProtos$DescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addMessageType(DescriptorProtos$DescriptorProto$Builder descriptorProtos$DescriptorProto$Builder) {
        if (this.messageTypeBuilder_ == null) {
            ensureMessageTypeIsMutable();
            this.messageType_.add(descriptorProtos$DescriptorProto$Builder.build());
            onChanged();
        } else {
            this.messageTypeBuilder_.addMessage(descriptorProtos$DescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addMessageType(int i, DescriptorProtos$DescriptorProto$Builder descriptorProtos$DescriptorProto$Builder) {
        if (this.messageTypeBuilder_ == null) {
            ensureMessageTypeIsMutable();
            this.messageType_.add(i, descriptorProtos$DescriptorProto$Builder.build());
            onChanged();
        } else {
            this.messageTypeBuilder_.addMessage(i, descriptorProtos$DescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addAllMessageType(Iterable<? extends DescriptorProtos$DescriptorProto> iterable) {
        if (this.messageTypeBuilder_ == null) {
            ensureMessageTypeIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.messageType_);
            onChanged();
        } else {
            this.messageTypeBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder clearMessageType() {
        if (this.messageTypeBuilder_ == null) {
            this.messageType_ = Collections.emptyList();
            this.bitField0_ &= -33;
            onChanged();
        } else {
            this.messageTypeBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder removeMessageType(int i) {
        if (this.messageTypeBuilder_ == null) {
            ensureMessageTypeIsMutable();
            this.messageType_.remove(i);
            onChanged();
        } else {
            this.messageTypeBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder getMessageTypeBuilder(int i) {
        return (DescriptorProtos$DescriptorProto$Builder) getMessageTypeFieldBuilder().getBuilder(i);
    }

    public DescriptorProtoOrBuilder getMessageTypeOrBuilder(int i) {
        if (this.messageTypeBuilder_ == null) {
            return (DescriptorProtoOrBuilder) this.messageType_.get(i);
        }
        return (DescriptorProtoOrBuilder) this.messageTypeBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends DescriptorProtoOrBuilder> getMessageTypeOrBuilderList() {
        if (this.messageTypeBuilder_ != null) {
            return this.messageTypeBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.messageType_);
    }

    public DescriptorProtos$DescriptorProto$Builder addMessageTypeBuilder() {
        return (DescriptorProtos$DescriptorProto$Builder) getMessageTypeFieldBuilder().addBuilder(DescriptorProtos$DescriptorProto.getDefaultInstance());
    }

    public DescriptorProtos$DescriptorProto$Builder addMessageTypeBuilder(int i) {
        return (DescriptorProtos$DescriptorProto$Builder) getMessageTypeFieldBuilder().addBuilder(i, DescriptorProtos$DescriptorProto.getDefaultInstance());
    }

    public List<DescriptorProtos$DescriptorProto$Builder> getMessageTypeBuilderList() {
        return getMessageTypeFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<DescriptorProtos$DescriptorProto, DescriptorProtos$DescriptorProto$Builder, DescriptorProtoOrBuilder> getMessageTypeFieldBuilder() {
        if (this.messageTypeBuilder_ == null) {
            this.messageTypeBuilder_ = new RepeatedFieldBuilderV3(this.messageType_, (this.bitField0_ & 32) == 32, getParentForChildren(), isClean());
            this.messageType_ = null;
        }
        return this.messageTypeBuilder_;
    }

    private void ensureEnumTypeIsMutable() {
        if ((this.bitField0_ & 64) != 64) {
            this.enumType_ = new ArrayList(this.enumType_);
            this.bitField0_ |= 64;
        }
    }

    public List<DescriptorProtos$EnumDescriptorProto> getEnumTypeList() {
        if (this.enumTypeBuilder_ == null) {
            return Collections.unmodifiableList(this.enumType_);
        }
        return this.enumTypeBuilder_.getMessageList();
    }

    public int getEnumTypeCount() {
        if (this.enumTypeBuilder_ == null) {
            return this.enumType_.size();
        }
        return this.enumTypeBuilder_.getCount();
    }

    public DescriptorProtos$EnumDescriptorProto getEnumType(int i) {
        if (this.enumTypeBuilder_ == null) {
            return (DescriptorProtos$EnumDescriptorProto) this.enumType_.get(i);
        }
        return (DescriptorProtos$EnumDescriptorProto) this.enumTypeBuilder_.getMessage(i);
    }

    public DescriptorProtos$FileDescriptorProto$Builder setEnumType(int i, DescriptorProtos$EnumDescriptorProto descriptorProtos$EnumDescriptorProto) {
        if (this.enumTypeBuilder_ != null) {
            this.enumTypeBuilder_.setMessage(i, descriptorProtos$EnumDescriptorProto);
        } else if (descriptorProtos$EnumDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureEnumTypeIsMutable();
            this.enumType_.set(i, descriptorProtos$EnumDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder setEnumType(int i, DescriptorProtos$EnumDescriptorProto$Builder descriptorProtos$EnumDescriptorProto$Builder) {
        if (this.enumTypeBuilder_ == null) {
            ensureEnumTypeIsMutable();
            this.enumType_.set(i, descriptorProtos$EnumDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.enumTypeBuilder_.setMessage(i, descriptorProtos$EnumDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addEnumType(DescriptorProtos$EnumDescriptorProto descriptorProtos$EnumDescriptorProto) {
        if (this.enumTypeBuilder_ != null) {
            this.enumTypeBuilder_.addMessage(descriptorProtos$EnumDescriptorProto);
        } else if (descriptorProtos$EnumDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureEnumTypeIsMutable();
            this.enumType_.add(descriptorProtos$EnumDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addEnumType(int i, DescriptorProtos$EnumDescriptorProto descriptorProtos$EnumDescriptorProto) {
        if (this.enumTypeBuilder_ != null) {
            this.enumTypeBuilder_.addMessage(i, descriptorProtos$EnumDescriptorProto);
        } else if (descriptorProtos$EnumDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureEnumTypeIsMutable();
            this.enumType_.add(i, descriptorProtos$EnumDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addEnumType(DescriptorProtos$EnumDescriptorProto$Builder descriptorProtos$EnumDescriptorProto$Builder) {
        if (this.enumTypeBuilder_ == null) {
            ensureEnumTypeIsMutable();
            this.enumType_.add(descriptorProtos$EnumDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.enumTypeBuilder_.addMessage(descriptorProtos$EnumDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addEnumType(int i, DescriptorProtos$EnumDescriptorProto$Builder descriptorProtos$EnumDescriptorProto$Builder) {
        if (this.enumTypeBuilder_ == null) {
            ensureEnumTypeIsMutable();
            this.enumType_.add(i, descriptorProtos$EnumDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.enumTypeBuilder_.addMessage(i, descriptorProtos$EnumDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addAllEnumType(Iterable<? extends DescriptorProtos$EnumDescriptorProto> iterable) {
        if (this.enumTypeBuilder_ == null) {
            ensureEnumTypeIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.enumType_);
            onChanged();
        } else {
            this.enumTypeBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder clearEnumType() {
        if (this.enumTypeBuilder_ == null) {
            this.enumType_ = Collections.emptyList();
            this.bitField0_ &= -65;
            onChanged();
        } else {
            this.enumTypeBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder removeEnumType(int i) {
        if (this.enumTypeBuilder_ == null) {
            ensureEnumTypeIsMutable();
            this.enumType_.remove(i);
            onChanged();
        } else {
            this.enumTypeBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$EnumDescriptorProto$Builder getEnumTypeBuilder(int i) {
        return (DescriptorProtos$EnumDescriptorProto$Builder) getEnumTypeFieldBuilder().getBuilder(i);
    }

    public EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int i) {
        if (this.enumTypeBuilder_ == null) {
            return (EnumDescriptorProtoOrBuilder) this.enumType_.get(i);
        }
        return (EnumDescriptorProtoOrBuilder) this.enumTypeBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends EnumDescriptorProtoOrBuilder> getEnumTypeOrBuilderList() {
        if (this.enumTypeBuilder_ != null) {
            return this.enumTypeBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.enumType_);
    }

    public DescriptorProtos$EnumDescriptorProto$Builder addEnumTypeBuilder() {
        return (DescriptorProtos$EnumDescriptorProto$Builder) getEnumTypeFieldBuilder().addBuilder(DescriptorProtos$EnumDescriptorProto.getDefaultInstance());
    }

    public DescriptorProtos$EnumDescriptorProto$Builder addEnumTypeBuilder(int i) {
        return (DescriptorProtos$EnumDescriptorProto$Builder) getEnumTypeFieldBuilder().addBuilder(i, DescriptorProtos$EnumDescriptorProto.getDefaultInstance());
    }

    public List<DescriptorProtos$EnumDescriptorProto$Builder> getEnumTypeBuilderList() {
        return getEnumTypeFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<DescriptorProtos$EnumDescriptorProto, DescriptorProtos$EnumDescriptorProto$Builder, EnumDescriptorProtoOrBuilder> getEnumTypeFieldBuilder() {
        if (this.enumTypeBuilder_ == null) {
            this.enumTypeBuilder_ = new RepeatedFieldBuilderV3(this.enumType_, (this.bitField0_ & 64) == 64, getParentForChildren(), isClean());
            this.enumType_ = null;
        }
        return this.enumTypeBuilder_;
    }

    private void ensureServiceIsMutable() {
        if ((this.bitField0_ & 128) != 128) {
            this.service_ = new ArrayList(this.service_);
            this.bitField0_ |= 128;
        }
    }

    public List<DescriptorProtos$ServiceDescriptorProto> getServiceList() {
        if (this.serviceBuilder_ == null) {
            return Collections.unmodifiableList(this.service_);
        }
        return this.serviceBuilder_.getMessageList();
    }

    public int getServiceCount() {
        if (this.serviceBuilder_ == null) {
            return this.service_.size();
        }
        return this.serviceBuilder_.getCount();
    }

    public DescriptorProtos$ServiceDescriptorProto getService(int i) {
        if (this.serviceBuilder_ == null) {
            return (DescriptorProtos$ServiceDescriptorProto) this.service_.get(i);
        }
        return (DescriptorProtos$ServiceDescriptorProto) this.serviceBuilder_.getMessage(i);
    }

    public DescriptorProtos$FileDescriptorProto$Builder setService(int i, DescriptorProtos$ServiceDescriptorProto descriptorProtos$ServiceDescriptorProto) {
        if (this.serviceBuilder_ != null) {
            this.serviceBuilder_.setMessage(i, descriptorProtos$ServiceDescriptorProto);
        } else if (descriptorProtos$ServiceDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureServiceIsMutable();
            this.service_.set(i, descriptorProtos$ServiceDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder setService(int i, DescriptorProtos$ServiceDescriptorProto$Builder descriptorProtos$ServiceDescriptorProto$Builder) {
        if (this.serviceBuilder_ == null) {
            ensureServiceIsMutable();
            this.service_.set(i, descriptorProtos$ServiceDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.serviceBuilder_.setMessage(i, descriptorProtos$ServiceDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addService(DescriptorProtos$ServiceDescriptorProto descriptorProtos$ServiceDescriptorProto) {
        if (this.serviceBuilder_ != null) {
            this.serviceBuilder_.addMessage(descriptorProtos$ServiceDescriptorProto);
        } else if (descriptorProtos$ServiceDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureServiceIsMutable();
            this.service_.add(descriptorProtos$ServiceDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addService(int i, DescriptorProtos$ServiceDescriptorProto descriptorProtos$ServiceDescriptorProto) {
        if (this.serviceBuilder_ != null) {
            this.serviceBuilder_.addMessage(i, descriptorProtos$ServiceDescriptorProto);
        } else if (descriptorProtos$ServiceDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureServiceIsMutable();
            this.service_.add(i, descriptorProtos$ServiceDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addService(DescriptorProtos$ServiceDescriptorProto$Builder descriptorProtos$ServiceDescriptorProto$Builder) {
        if (this.serviceBuilder_ == null) {
            ensureServiceIsMutable();
            this.service_.add(descriptorProtos$ServiceDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.serviceBuilder_.addMessage(descriptorProtos$ServiceDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addService(int i, DescriptorProtos$ServiceDescriptorProto$Builder descriptorProtos$ServiceDescriptorProto$Builder) {
        if (this.serviceBuilder_ == null) {
            ensureServiceIsMutable();
            this.service_.add(i, descriptorProtos$ServiceDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.serviceBuilder_.addMessage(i, descriptorProtos$ServiceDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addAllService(Iterable<? extends DescriptorProtos$ServiceDescriptorProto> iterable) {
        if (this.serviceBuilder_ == null) {
            ensureServiceIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.service_);
            onChanged();
        } else {
            this.serviceBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder clearService() {
        if (this.serviceBuilder_ == null) {
            this.service_ = Collections.emptyList();
            this.bitField0_ &= -129;
            onChanged();
        } else {
            this.serviceBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder removeService(int i) {
        if (this.serviceBuilder_ == null) {
            ensureServiceIsMutable();
            this.service_.remove(i);
            onChanged();
        } else {
            this.serviceBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder getServiceBuilder(int i) {
        return (DescriptorProtos$ServiceDescriptorProto$Builder) getServiceFieldBuilder().getBuilder(i);
    }

    public ServiceDescriptorProtoOrBuilder getServiceOrBuilder(int i) {
        if (this.serviceBuilder_ == null) {
            return (ServiceDescriptorProtoOrBuilder) this.service_.get(i);
        }
        return (ServiceDescriptorProtoOrBuilder) this.serviceBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends ServiceDescriptorProtoOrBuilder> getServiceOrBuilderList() {
        if (this.serviceBuilder_ != null) {
            return this.serviceBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.service_);
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder addServiceBuilder() {
        return (DescriptorProtos$ServiceDescriptorProto$Builder) getServiceFieldBuilder().addBuilder(DescriptorProtos$ServiceDescriptorProto.getDefaultInstance());
    }

    public DescriptorProtos$ServiceDescriptorProto$Builder addServiceBuilder(int i) {
        return (DescriptorProtos$ServiceDescriptorProto$Builder) getServiceFieldBuilder().addBuilder(i, DescriptorProtos$ServiceDescriptorProto.getDefaultInstance());
    }

    public List<DescriptorProtos$ServiceDescriptorProto$Builder> getServiceBuilderList() {
        return getServiceFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<DescriptorProtos$ServiceDescriptorProto, DescriptorProtos$ServiceDescriptorProto$Builder, ServiceDescriptorProtoOrBuilder> getServiceFieldBuilder() {
        if (this.serviceBuilder_ == null) {
            this.serviceBuilder_ = new RepeatedFieldBuilderV3(this.service_, (this.bitField0_ & 128) == 128, getParentForChildren(), isClean());
            this.service_ = null;
        }
        return this.serviceBuilder_;
    }

    private void ensureExtensionIsMutable() {
        if ((this.bitField0_ & 256) != 256) {
            this.extension_ = new ArrayList(this.extension_);
            this.bitField0_ |= 256;
        }
    }

    public List<DescriptorProtos$FieldDescriptorProto> getExtensionList() {
        if (this.extensionBuilder_ == null) {
            return Collections.unmodifiableList(this.extension_);
        }
        return this.extensionBuilder_.getMessageList();
    }

    public int getExtensionCount() {
        if (this.extensionBuilder_ == null) {
            return this.extension_.size();
        }
        return this.extensionBuilder_.getCount();
    }

    public DescriptorProtos$FieldDescriptorProto getExtension(int i) {
        if (this.extensionBuilder_ == null) {
            return (DescriptorProtos$FieldDescriptorProto) this.extension_.get(i);
        }
        return (DescriptorProtos$FieldDescriptorProto) this.extensionBuilder_.getMessage(i);
    }

    public DescriptorProtos$FileDescriptorProto$Builder setExtension(int i, DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto) {
        if (this.extensionBuilder_ != null) {
            this.extensionBuilder_.setMessage(i, descriptorProtos$FieldDescriptorProto);
        } else if (descriptorProtos$FieldDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureExtensionIsMutable();
            this.extension_.set(i, descriptorProtos$FieldDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder setExtension(int i, DescriptorProtos$FieldDescriptorProto$Builder descriptorProtos$FieldDescriptorProto$Builder) {
        if (this.extensionBuilder_ == null) {
            ensureExtensionIsMutable();
            this.extension_.set(i, descriptorProtos$FieldDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.extensionBuilder_.setMessage(i, descriptorProtos$FieldDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addExtension(DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto) {
        if (this.extensionBuilder_ != null) {
            this.extensionBuilder_.addMessage(descriptorProtos$FieldDescriptorProto);
        } else if (descriptorProtos$FieldDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureExtensionIsMutable();
            this.extension_.add(descriptorProtos$FieldDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addExtension(int i, DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto) {
        if (this.extensionBuilder_ != null) {
            this.extensionBuilder_.addMessage(i, descriptorProtos$FieldDescriptorProto);
        } else if (descriptorProtos$FieldDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureExtensionIsMutable();
            this.extension_.add(i, descriptorProtos$FieldDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addExtension(DescriptorProtos$FieldDescriptorProto$Builder descriptorProtos$FieldDescriptorProto$Builder) {
        if (this.extensionBuilder_ == null) {
            ensureExtensionIsMutable();
            this.extension_.add(descriptorProtos$FieldDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.extensionBuilder_.addMessage(descriptorProtos$FieldDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addExtension(int i, DescriptorProtos$FieldDescriptorProto$Builder descriptorProtos$FieldDescriptorProto$Builder) {
        if (this.extensionBuilder_ == null) {
            ensureExtensionIsMutable();
            this.extension_.add(i, descriptorProtos$FieldDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.extensionBuilder_.addMessage(i, descriptorProtos$FieldDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder addAllExtension(Iterable<? extends DescriptorProtos$FieldDescriptorProto> iterable) {
        if (this.extensionBuilder_ == null) {
            ensureExtensionIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.extension_);
            onChanged();
        } else {
            this.extensionBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder clearExtension() {
        if (this.extensionBuilder_ == null) {
            this.extension_ = Collections.emptyList();
            this.bitField0_ &= -257;
            onChanged();
        } else {
            this.extensionBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder removeExtension(int i) {
        if (this.extensionBuilder_ == null) {
            ensureExtensionIsMutable();
            this.extension_.remove(i);
            onChanged();
        } else {
            this.extensionBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder getExtensionBuilder(int i) {
        return (DescriptorProtos$FieldDescriptorProto$Builder) getExtensionFieldBuilder().getBuilder(i);
    }

    public FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int i) {
        if (this.extensionBuilder_ == null) {
            return (FieldDescriptorProtoOrBuilder) this.extension_.get(i);
        }
        return (FieldDescriptorProtoOrBuilder) this.extensionBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends FieldDescriptorProtoOrBuilder> getExtensionOrBuilderList() {
        if (this.extensionBuilder_ != null) {
            return this.extensionBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.extension_);
    }

    public DescriptorProtos$FieldDescriptorProto$Builder addExtensionBuilder() {
        return (DescriptorProtos$FieldDescriptorProto$Builder) getExtensionFieldBuilder().addBuilder(DescriptorProtos$FieldDescriptorProto.getDefaultInstance());
    }

    public DescriptorProtos$FieldDescriptorProto$Builder addExtensionBuilder(int i) {
        return (DescriptorProtos$FieldDescriptorProto$Builder) getExtensionFieldBuilder().addBuilder(i, DescriptorProtos$FieldDescriptorProto.getDefaultInstance());
    }

    public List<DescriptorProtos$FieldDescriptorProto$Builder> getExtensionBuilderList() {
        return getExtensionFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<DescriptorProtos$FieldDescriptorProto, DescriptorProtos$FieldDescriptorProto$Builder, FieldDescriptorProtoOrBuilder> getExtensionFieldBuilder() {
        if (this.extensionBuilder_ == null) {
            this.extensionBuilder_ = new RepeatedFieldBuilderV3(this.extension_, (this.bitField0_ & 256) == 256, getParentForChildren(), isClean());
            this.extension_ = null;
        }
        return this.extensionBuilder_;
    }

    public boolean hasOptions() {
        return (this.bitField0_ & 512) == 512;
    }

    public DescriptorProtos$FileOptions getOptions() {
        if (this.optionsBuilder_ == null) {
            return this.options_ == null ? DescriptorProtos$FileOptions.getDefaultInstance() : this.options_;
        } else {
            return (DescriptorProtos$FileOptions) this.optionsBuilder_.getMessage();
        }
    }

    public DescriptorProtos$FileDescriptorProto$Builder setOptions(DescriptorProtos$FileOptions descriptorProtos$FileOptions) {
        if (this.optionsBuilder_ != null) {
            this.optionsBuilder_.setMessage(descriptorProtos$FileOptions);
        } else if (descriptorProtos$FileOptions == null) {
            throw new NullPointerException();
        } else {
            this.options_ = descriptorProtos$FileOptions;
            onChanged();
        }
        this.bitField0_ |= 512;
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder setOptions(DescriptorProtos$FileOptions.Builder builder) {
        if (this.optionsBuilder_ == null) {
            this.options_ = builder.build();
            onChanged();
        } else {
            this.optionsBuilder_.setMessage(builder.build());
        }
        this.bitField0_ |= 512;
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder mergeOptions(DescriptorProtos$FileOptions descriptorProtos$FileOptions) {
        if (this.optionsBuilder_ == null) {
            if ((this.bitField0_ & 512) != 512 || this.options_ == null || this.options_ == DescriptorProtos$FileOptions.getDefaultInstance()) {
                this.options_ = descriptorProtos$FileOptions;
            } else {
                this.options_ = DescriptorProtos$FileOptions.newBuilder(this.options_).mergeFrom(descriptorProtos$FileOptions).buildPartial();
            }
            onChanged();
        } else {
            this.optionsBuilder_.mergeFrom(descriptorProtos$FileOptions);
        }
        this.bitField0_ |= 512;
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder clearOptions() {
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
            onChanged();
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -513;
        return this;
    }

    public DescriptorProtos$FileOptions.Builder getOptionsBuilder() {
        this.bitField0_ |= 512;
        onChanged();
        return (DescriptorProtos$FileOptions.Builder) getOptionsFieldBuilder().getBuilder();
    }

    public DescriptorProtos$FileOptionsOrBuilder getOptionsOrBuilder() {
        if (this.optionsBuilder_ != null) {
            return (DescriptorProtos$FileOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder();
        }
        return this.options_ == null ? DescriptorProtos$FileOptions.getDefaultInstance() : this.options_;
    }

    private SingleFieldBuilderV3<DescriptorProtos$FileOptions, DescriptorProtos$FileOptions.Builder, DescriptorProtos$FileOptionsOrBuilder> getOptionsFieldBuilder() {
        if (this.optionsBuilder_ == null) {
            this.optionsBuilder_ = new SingleFieldBuilderV3(getOptions(), getParentForChildren(), isClean());
            this.options_ = null;
        }
        return this.optionsBuilder_;
    }

    public boolean hasSourceCodeInfo() {
        return (this.bitField0_ & 1024) == 1024;
    }

    public DescriptorProtos$SourceCodeInfo getSourceCodeInfo() {
        if (this.sourceCodeInfoBuilder_ == null) {
            return this.sourceCodeInfo_ == null ? DescriptorProtos$SourceCodeInfo.getDefaultInstance() : this.sourceCodeInfo_;
        } else {
            return (DescriptorProtos$SourceCodeInfo) this.sourceCodeInfoBuilder_.getMessage();
        }
    }

    public DescriptorProtos$FileDescriptorProto$Builder setSourceCodeInfo(DescriptorProtos$SourceCodeInfo descriptorProtos$SourceCodeInfo) {
        if (this.sourceCodeInfoBuilder_ != null) {
            this.sourceCodeInfoBuilder_.setMessage(descriptorProtos$SourceCodeInfo);
        } else if (descriptorProtos$SourceCodeInfo == null) {
            throw new NullPointerException();
        } else {
            this.sourceCodeInfo_ = descriptorProtos$SourceCodeInfo;
            onChanged();
        }
        this.bitField0_ |= 1024;
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder setSourceCodeInfo(DescriptorProtos$SourceCodeInfo$Builder descriptorProtos$SourceCodeInfo$Builder) {
        if (this.sourceCodeInfoBuilder_ == null) {
            this.sourceCodeInfo_ = descriptorProtos$SourceCodeInfo$Builder.build();
            onChanged();
        } else {
            this.sourceCodeInfoBuilder_.setMessage(descriptorProtos$SourceCodeInfo$Builder.build());
        }
        this.bitField0_ |= 1024;
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder mergeSourceCodeInfo(DescriptorProtos$SourceCodeInfo descriptorProtos$SourceCodeInfo) {
        if (this.sourceCodeInfoBuilder_ == null) {
            if ((this.bitField0_ & 1024) != 1024 || this.sourceCodeInfo_ == null || this.sourceCodeInfo_ == DescriptorProtos$SourceCodeInfo.getDefaultInstance()) {
                this.sourceCodeInfo_ = descriptorProtos$SourceCodeInfo;
            } else {
                this.sourceCodeInfo_ = DescriptorProtos$SourceCodeInfo.newBuilder(this.sourceCodeInfo_).mergeFrom(descriptorProtos$SourceCodeInfo).buildPartial();
            }
            onChanged();
        } else {
            this.sourceCodeInfoBuilder_.mergeFrom(descriptorProtos$SourceCodeInfo);
        }
        this.bitField0_ |= 1024;
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder clearSourceCodeInfo() {
        if (this.sourceCodeInfoBuilder_ == null) {
            this.sourceCodeInfo_ = null;
            onChanged();
        } else {
            this.sourceCodeInfoBuilder_.clear();
        }
        this.bitField0_ &= -1025;
        return this;
    }

    public DescriptorProtos$SourceCodeInfo$Builder getSourceCodeInfoBuilder() {
        this.bitField0_ |= 1024;
        onChanged();
        return (DescriptorProtos$SourceCodeInfo$Builder) getSourceCodeInfoFieldBuilder().getBuilder();
    }

    public SourceCodeInfoOrBuilder getSourceCodeInfoOrBuilder() {
        if (this.sourceCodeInfoBuilder_ != null) {
            return (SourceCodeInfoOrBuilder) this.sourceCodeInfoBuilder_.getMessageOrBuilder();
        }
        return this.sourceCodeInfo_ == null ? DescriptorProtos$SourceCodeInfo.getDefaultInstance() : this.sourceCodeInfo_;
    }

    private SingleFieldBuilderV3<DescriptorProtos$SourceCodeInfo, DescriptorProtos$SourceCodeInfo$Builder, SourceCodeInfoOrBuilder> getSourceCodeInfoFieldBuilder() {
        if (this.sourceCodeInfoBuilder_ == null) {
            this.sourceCodeInfoBuilder_ = new SingleFieldBuilderV3(getSourceCodeInfo(), getParentForChildren(), isClean());
            this.sourceCodeInfo_ = null;
        }
        return this.sourceCodeInfoBuilder_;
    }

    public boolean hasSyntax() {
        return (this.bitField0_ & 2048) == 2048;
    }

    public String getSyntax() {
        Object obj = this.syntax_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.syntax_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getSyntaxBytes() {
        Object obj = this.syntax_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.syntax_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public DescriptorProtos$FileDescriptorProto$Builder setSyntax(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 2048;
        this.syntax_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder clearSyntax() {
        this.bitField0_ &= -2049;
        this.syntax_ = DescriptorProtos$FileDescriptorProto.getDefaultInstance().getSyntax();
        onChanged();
        return this;
    }

    public DescriptorProtos$FileDescriptorProto$Builder setSyntaxBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 2048;
        this.syntax_ = byteString;
        onChanged();
        return this;
    }

    public final DescriptorProtos$FileDescriptorProto$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$FileDescriptorProto$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$FileDescriptorProto$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$FileDescriptorProto$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
