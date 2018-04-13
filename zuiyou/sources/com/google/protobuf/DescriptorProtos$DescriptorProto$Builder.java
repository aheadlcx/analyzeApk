package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.DescriptorProto.ExtensionRange;
import com.google.protobuf.DescriptorProtos.DescriptorProto.ReservedRange;
import com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.EnumDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.FieldDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.MessageOptionsOrBuilder;
import com.google.protobuf.DescriptorProtos.OneofDescriptorProtoOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$DescriptorProto$Builder extends Builder<DescriptorProtos$DescriptorProto$Builder> implements DescriptorProtoOrBuilder {
    private int bitField0_;
    private RepeatedFieldBuilderV3<DescriptorProtos$EnumDescriptorProto, DescriptorProtos$EnumDescriptorProto$Builder, EnumDescriptorProtoOrBuilder> enumTypeBuilder_;
    private List<DescriptorProtos$EnumDescriptorProto> enumType_;
    private RepeatedFieldBuilderV3<DescriptorProtos$FieldDescriptorProto, DescriptorProtos$FieldDescriptorProto$Builder, FieldDescriptorProtoOrBuilder> extensionBuilder_;
    private RepeatedFieldBuilderV3<ExtensionRange, DescriptorProtos$DescriptorProto$ExtensionRange$Builder, DescriptorProtos$DescriptorProto$ExtensionRangeOrBuilder> extensionRangeBuilder_;
    private List<ExtensionRange> extensionRange_;
    private List<DescriptorProtos$FieldDescriptorProto> extension_;
    private RepeatedFieldBuilderV3<DescriptorProtos$FieldDescriptorProto, DescriptorProtos$FieldDescriptorProto$Builder, FieldDescriptorProtoOrBuilder> fieldBuilder_;
    private List<DescriptorProtos$FieldDescriptorProto> field_;
    private Object name_;
    private RepeatedFieldBuilderV3<DescriptorProtos$DescriptorProto, DescriptorProtos$DescriptorProto$Builder, DescriptorProtoOrBuilder> nestedTypeBuilder_;
    private List<DescriptorProtos$DescriptorProto> nestedType_;
    private RepeatedFieldBuilderV3<DescriptorProtos$OneofDescriptorProto, DescriptorProtos$OneofDescriptorProto$Builder, OneofDescriptorProtoOrBuilder> oneofDeclBuilder_;
    private List<DescriptorProtos$OneofDescriptorProto> oneofDecl_;
    private SingleFieldBuilderV3<DescriptorProtos$MessageOptions, DescriptorProtos$MessageOptions$Builder, MessageOptionsOrBuilder> optionsBuilder_;
    private DescriptorProtos$MessageOptions options_;
    private LazyStringList reservedName_;
    private RepeatedFieldBuilderV3<ReservedRange, DescriptorProtos$DescriptorProto$ReservedRange$Builder, DescriptorProtos$DescriptorProto$ReservedRangeOrBuilder> reservedRangeBuilder_;
    private List<ReservedRange> reservedRange_;

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProtos$DescriptorProto.class, DescriptorProtos$DescriptorProto$Builder.class);
    }

    private DescriptorProtos$DescriptorProto$Builder() {
        this.name_ = "";
        this.field_ = Collections.emptyList();
        this.extension_ = Collections.emptyList();
        this.nestedType_ = Collections.emptyList();
        this.enumType_ = Collections.emptyList();
        this.extensionRange_ = Collections.emptyList();
        this.oneofDecl_ = Collections.emptyList();
        this.options_ = null;
        this.reservedRange_ = Collections.emptyList();
        this.reservedName_ = LazyStringArrayList.EMPTY;
        maybeForceBuilderInitialization();
    }

    private DescriptorProtos$DescriptorProto$Builder(BuilderParent builderParent) {
        super(builderParent);
        this.name_ = "";
        this.field_ = Collections.emptyList();
        this.extension_ = Collections.emptyList();
        this.nestedType_ = Collections.emptyList();
        this.enumType_ = Collections.emptyList();
        this.extensionRange_ = Collections.emptyList();
        this.oneofDecl_ = Collections.emptyList();
        this.options_ = null;
        this.reservedRange_ = Collections.emptyList();
        this.reservedName_ = LazyStringArrayList.EMPTY;
        maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            getFieldFieldBuilder();
            getExtensionFieldBuilder();
            getNestedTypeFieldBuilder();
            getEnumTypeFieldBuilder();
            getExtensionRangeFieldBuilder();
            getOneofDeclFieldBuilder();
            getOptionsFieldBuilder();
            getReservedRangeFieldBuilder();
        }
    }

    public DescriptorProtos$DescriptorProto$Builder clear() {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        if (this.fieldBuilder_ == null) {
            this.field_ = Collections.emptyList();
            this.bitField0_ &= -3;
        } else {
            this.fieldBuilder_.clear();
        }
        if (this.extensionBuilder_ == null) {
            this.extension_ = Collections.emptyList();
            this.bitField0_ &= -5;
        } else {
            this.extensionBuilder_.clear();
        }
        if (this.nestedTypeBuilder_ == null) {
            this.nestedType_ = Collections.emptyList();
            this.bitField0_ &= -9;
        } else {
            this.nestedTypeBuilder_.clear();
        }
        if (this.enumTypeBuilder_ == null) {
            this.enumType_ = Collections.emptyList();
            this.bitField0_ &= -17;
        } else {
            this.enumTypeBuilder_.clear();
        }
        if (this.extensionRangeBuilder_ == null) {
            this.extensionRange_ = Collections.emptyList();
            this.bitField0_ &= -33;
        } else {
            this.extensionRangeBuilder_.clear();
        }
        if (this.oneofDeclBuilder_ == null) {
            this.oneofDecl_ = Collections.emptyList();
            this.bitField0_ &= -65;
        } else {
            this.oneofDeclBuilder_.clear();
        }
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -129;
        if (this.reservedRangeBuilder_ == null) {
            this.reservedRange_ = Collections.emptyList();
            this.bitField0_ &= -257;
        } else {
            this.reservedRangeBuilder_.clear();
        }
        this.reservedName_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= -513;
        return this;
    }

    public Descriptor getDescriptorForType() {
        return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor;
    }

    public DescriptorProtos$DescriptorProto getDefaultInstanceForType() {
        return DescriptorProtos$DescriptorProto.getDefaultInstance();
    }

    public DescriptorProtos$DescriptorProto build() {
        Object buildPartial = buildPartial();
        if (buildPartial.isInitialized()) {
            return buildPartial;
        }
        throw AbstractMessage$Builder.newUninitializedMessageException(buildPartial);
    }

    public DescriptorProtos$DescriptorProto buildPartial() {
        int i;
        int i2 = 1;
        DescriptorProtos$DescriptorProto descriptorProtos$DescriptorProto = new DescriptorProtos$DescriptorProto(this, null);
        int i3 = this.bitField0_;
        if ((i3 & 1) != 1) {
            i2 = 0;
        }
        DescriptorProtos$DescriptorProto.access$5002(descriptorProtos$DescriptorProto, this.name_);
        if (this.fieldBuilder_ == null) {
            if ((this.bitField0_ & 2) == 2) {
                this.field_ = Collections.unmodifiableList(this.field_);
                this.bitField0_ &= -3;
            }
            DescriptorProtos$DescriptorProto.access$5102(descriptorProtos$DescriptorProto, this.field_);
        } else {
            DescriptorProtos$DescriptorProto.access$5102(descriptorProtos$DescriptorProto, this.fieldBuilder_.build());
        }
        if (this.extensionBuilder_ == null) {
            if ((this.bitField0_ & 4) == 4) {
                this.extension_ = Collections.unmodifiableList(this.extension_);
                this.bitField0_ &= -5;
            }
            DescriptorProtos$DescriptorProto.access$5202(descriptorProtos$DescriptorProto, this.extension_);
        } else {
            DescriptorProtos$DescriptorProto.access$5202(descriptorProtos$DescriptorProto, this.extensionBuilder_.build());
        }
        if (this.nestedTypeBuilder_ == null) {
            if ((this.bitField0_ & 8) == 8) {
                this.nestedType_ = Collections.unmodifiableList(this.nestedType_);
                this.bitField0_ &= -9;
            }
            DescriptorProtos$DescriptorProto.access$5302(descriptorProtos$DescriptorProto, this.nestedType_);
        } else {
            DescriptorProtos$DescriptorProto.access$5302(descriptorProtos$DescriptorProto, this.nestedTypeBuilder_.build());
        }
        if (this.enumTypeBuilder_ == null) {
            if ((this.bitField0_ & 16) == 16) {
                this.enumType_ = Collections.unmodifiableList(this.enumType_);
                this.bitField0_ &= -17;
            }
            DescriptorProtos$DescriptorProto.access$5402(descriptorProtos$DescriptorProto, this.enumType_);
        } else {
            DescriptorProtos$DescriptorProto.access$5402(descriptorProtos$DescriptorProto, this.enumTypeBuilder_.build());
        }
        if (this.extensionRangeBuilder_ == null) {
            if ((this.bitField0_ & 32) == 32) {
                this.extensionRange_ = Collections.unmodifiableList(this.extensionRange_);
                this.bitField0_ &= -33;
            }
            DescriptorProtos$DescriptorProto.access$5502(descriptorProtos$DescriptorProto, this.extensionRange_);
        } else {
            DescriptorProtos$DescriptorProto.access$5502(descriptorProtos$DescriptorProto, this.extensionRangeBuilder_.build());
        }
        if (this.oneofDeclBuilder_ == null) {
            if ((this.bitField0_ & 64) == 64) {
                this.oneofDecl_ = Collections.unmodifiableList(this.oneofDecl_);
                this.bitField0_ &= -65;
            }
            DescriptorProtos$DescriptorProto.access$5602(descriptorProtos$DescriptorProto, this.oneofDecl_);
        } else {
            DescriptorProtos$DescriptorProto.access$5602(descriptorProtos$DescriptorProto, this.oneofDeclBuilder_.build());
        }
        if ((i3 & 128) == 128) {
            i = i2 | 2;
        } else {
            i = i2;
        }
        if (this.optionsBuilder_ == null) {
            DescriptorProtos$DescriptorProto.access$5702(descriptorProtos$DescriptorProto, this.options_);
        } else {
            DescriptorProtos$DescriptorProto.access$5702(descriptorProtos$DescriptorProto, (DescriptorProtos$MessageOptions) this.optionsBuilder_.build());
        }
        if (this.reservedRangeBuilder_ == null) {
            if ((this.bitField0_ & 256) == 256) {
                this.reservedRange_ = Collections.unmodifiableList(this.reservedRange_);
                this.bitField0_ &= -257;
            }
            DescriptorProtos$DescriptorProto.access$5802(descriptorProtos$DescriptorProto, this.reservedRange_);
        } else {
            DescriptorProtos$DescriptorProto.access$5802(descriptorProtos$DescriptorProto, this.reservedRangeBuilder_.build());
        }
        if ((this.bitField0_ & 512) == 512) {
            this.reservedName_ = this.reservedName_.getUnmodifiableView();
            this.bitField0_ &= -513;
        }
        DescriptorProtos$DescriptorProto.access$5902(descriptorProtos$DescriptorProto, this.reservedName_);
        DescriptorProtos$DescriptorProto.access$6002(descriptorProtos$DescriptorProto, i);
        onBuilt();
        return descriptorProtos$DescriptorProto;
    }

    public DescriptorProtos$DescriptorProto$Builder clone() {
        return (DescriptorProtos$DescriptorProto$Builder) super.clone();
    }

    public DescriptorProtos$DescriptorProto$Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$DescriptorProto$Builder) super.setField(fieldDescriptor, obj);
    }

    public DescriptorProtos$DescriptorProto$Builder clearField(FieldDescriptor fieldDescriptor) {
        return (DescriptorProtos$DescriptorProto$Builder) super.clearField(fieldDescriptor);
    }

    public DescriptorProtos$DescriptorProto$Builder clearOneof(OneofDescriptor oneofDescriptor) {
        return (DescriptorProtos$DescriptorProto$Builder) super.clearOneof(oneofDescriptor);
    }

    public DescriptorProtos$DescriptorProto$Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
        return (DescriptorProtos$DescriptorProto$Builder) super.setRepeatedField(fieldDescriptor, i, obj);
    }

    public DescriptorProtos$DescriptorProto$Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
        return (DescriptorProtos$DescriptorProto$Builder) super.addRepeatedField(fieldDescriptor, obj);
    }

    public DescriptorProtos$DescriptorProto$Builder mergeFrom(Message message) {
        if (message instanceof DescriptorProtos$DescriptorProto) {
            return mergeFrom((DescriptorProtos$DescriptorProto) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder mergeFrom(DescriptorProtos$DescriptorProto descriptorProtos$DescriptorProto) {
        RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
        if (descriptorProtos$DescriptorProto != DescriptorProtos$DescriptorProto.getDefaultInstance()) {
            if (descriptorProtos$DescriptorProto.hasName()) {
                this.bitField0_ |= 1;
                this.name_ = DescriptorProtos$DescriptorProto.access$5000(descriptorProtos$DescriptorProto);
                onChanged();
            }
            if (this.fieldBuilder_ == null) {
                if (!DescriptorProtos$DescriptorProto.access$5100(descriptorProtos$DescriptorProto).isEmpty()) {
                    if (this.field_.isEmpty()) {
                        this.field_ = DescriptorProtos$DescriptorProto.access$5100(descriptorProtos$DescriptorProto);
                        this.bitField0_ &= -3;
                    } else {
                        ensureFieldIsMutable();
                        this.field_.addAll(DescriptorProtos$DescriptorProto.access$5100(descriptorProtos$DescriptorProto));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$DescriptorProto.access$5100(descriptorProtos$DescriptorProto).isEmpty()) {
                if (this.fieldBuilder_.isEmpty()) {
                    this.fieldBuilder_.dispose();
                    this.fieldBuilder_ = null;
                    this.field_ = DescriptorProtos$DescriptorProto.access$5100(descriptorProtos$DescriptorProto);
                    this.bitField0_ &= -3;
                    this.fieldBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getFieldFieldBuilder() : null;
                } else {
                    this.fieldBuilder_.addAllMessages(DescriptorProtos$DescriptorProto.access$5100(descriptorProtos$DescriptorProto));
                }
            }
            if (this.extensionBuilder_ == null) {
                if (!DescriptorProtos$DescriptorProto.access$5200(descriptorProtos$DescriptorProto).isEmpty()) {
                    if (this.extension_.isEmpty()) {
                        this.extension_ = DescriptorProtos$DescriptorProto.access$5200(descriptorProtos$DescriptorProto);
                        this.bitField0_ &= -5;
                    } else {
                        ensureExtensionIsMutable();
                        this.extension_.addAll(DescriptorProtos$DescriptorProto.access$5200(descriptorProtos$DescriptorProto));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$DescriptorProto.access$5200(descriptorProtos$DescriptorProto).isEmpty()) {
                if (this.extensionBuilder_.isEmpty()) {
                    this.extensionBuilder_.dispose();
                    this.extensionBuilder_ = null;
                    this.extension_ = DescriptorProtos$DescriptorProto.access$5200(descriptorProtos$DescriptorProto);
                    this.bitField0_ &= -5;
                    this.extensionBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getExtensionFieldBuilder() : null;
                } else {
                    this.extensionBuilder_.addAllMessages(DescriptorProtos$DescriptorProto.access$5200(descriptorProtos$DescriptorProto));
                }
            }
            if (this.nestedTypeBuilder_ == null) {
                if (!DescriptorProtos$DescriptorProto.access$5300(descriptorProtos$DescriptorProto).isEmpty()) {
                    if (this.nestedType_.isEmpty()) {
                        this.nestedType_ = DescriptorProtos$DescriptorProto.access$5300(descriptorProtos$DescriptorProto);
                        this.bitField0_ &= -9;
                    } else {
                        ensureNestedTypeIsMutable();
                        this.nestedType_.addAll(DescriptorProtos$DescriptorProto.access$5300(descriptorProtos$DescriptorProto));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$DescriptorProto.access$5300(descriptorProtos$DescriptorProto).isEmpty()) {
                if (this.nestedTypeBuilder_.isEmpty()) {
                    this.nestedTypeBuilder_.dispose();
                    this.nestedTypeBuilder_ = null;
                    this.nestedType_ = DescriptorProtos$DescriptorProto.access$5300(descriptorProtos$DescriptorProto);
                    this.bitField0_ &= -9;
                    this.nestedTypeBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getNestedTypeFieldBuilder() : null;
                } else {
                    this.nestedTypeBuilder_.addAllMessages(DescriptorProtos$DescriptorProto.access$5300(descriptorProtos$DescriptorProto));
                }
            }
            if (this.enumTypeBuilder_ == null) {
                if (!DescriptorProtos$DescriptorProto.access$5400(descriptorProtos$DescriptorProto).isEmpty()) {
                    if (this.enumType_.isEmpty()) {
                        this.enumType_ = DescriptorProtos$DescriptorProto.access$5400(descriptorProtos$DescriptorProto);
                        this.bitField0_ &= -17;
                    } else {
                        ensureEnumTypeIsMutable();
                        this.enumType_.addAll(DescriptorProtos$DescriptorProto.access$5400(descriptorProtos$DescriptorProto));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$DescriptorProto.access$5400(descriptorProtos$DescriptorProto).isEmpty()) {
                if (this.enumTypeBuilder_.isEmpty()) {
                    this.enumTypeBuilder_.dispose();
                    this.enumTypeBuilder_ = null;
                    this.enumType_ = DescriptorProtos$DescriptorProto.access$5400(descriptorProtos$DescriptorProto);
                    this.bitField0_ &= -17;
                    this.enumTypeBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getEnumTypeFieldBuilder() : null;
                } else {
                    this.enumTypeBuilder_.addAllMessages(DescriptorProtos$DescriptorProto.access$5400(descriptorProtos$DescriptorProto));
                }
            }
            if (this.extensionRangeBuilder_ == null) {
                if (!DescriptorProtos$DescriptorProto.access$5500(descriptorProtos$DescriptorProto).isEmpty()) {
                    if (this.extensionRange_.isEmpty()) {
                        this.extensionRange_ = DescriptorProtos$DescriptorProto.access$5500(descriptorProtos$DescriptorProto);
                        this.bitField0_ &= -33;
                    } else {
                        ensureExtensionRangeIsMutable();
                        this.extensionRange_.addAll(DescriptorProtos$DescriptorProto.access$5500(descriptorProtos$DescriptorProto));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$DescriptorProto.access$5500(descriptorProtos$DescriptorProto).isEmpty()) {
                if (this.extensionRangeBuilder_.isEmpty()) {
                    this.extensionRangeBuilder_.dispose();
                    this.extensionRangeBuilder_ = null;
                    this.extensionRange_ = DescriptorProtos$DescriptorProto.access$5500(descriptorProtos$DescriptorProto);
                    this.bitField0_ &= -33;
                    this.extensionRangeBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getExtensionRangeFieldBuilder() : null;
                } else {
                    this.extensionRangeBuilder_.addAllMessages(DescriptorProtos$DescriptorProto.access$5500(descriptorProtos$DescriptorProto));
                }
            }
            if (this.oneofDeclBuilder_ == null) {
                if (!DescriptorProtos$DescriptorProto.access$5600(descriptorProtos$DescriptorProto).isEmpty()) {
                    if (this.oneofDecl_.isEmpty()) {
                        this.oneofDecl_ = DescriptorProtos$DescriptorProto.access$5600(descriptorProtos$DescriptorProto);
                        this.bitField0_ &= -65;
                    } else {
                        ensureOneofDeclIsMutable();
                        this.oneofDecl_.addAll(DescriptorProtos$DescriptorProto.access$5600(descriptorProtos$DescriptorProto));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$DescriptorProto.access$5600(descriptorProtos$DescriptorProto).isEmpty()) {
                if (this.oneofDeclBuilder_.isEmpty()) {
                    this.oneofDeclBuilder_.dispose();
                    this.oneofDeclBuilder_ = null;
                    this.oneofDecl_ = DescriptorProtos$DescriptorProto.access$5600(descriptorProtos$DescriptorProto);
                    this.bitField0_ &= -65;
                    this.oneofDeclBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getOneofDeclFieldBuilder() : null;
                } else {
                    this.oneofDeclBuilder_.addAllMessages(DescriptorProtos$DescriptorProto.access$5600(descriptorProtos$DescriptorProto));
                }
            }
            if (descriptorProtos$DescriptorProto.hasOptions()) {
                mergeOptions(descriptorProtos$DescriptorProto.getOptions());
            }
            if (this.reservedRangeBuilder_ == null) {
                if (!DescriptorProtos$DescriptorProto.access$5800(descriptorProtos$DescriptorProto).isEmpty()) {
                    if (this.reservedRange_.isEmpty()) {
                        this.reservedRange_ = DescriptorProtos$DescriptorProto.access$5800(descriptorProtos$DescriptorProto);
                        this.bitField0_ &= -257;
                    } else {
                        ensureReservedRangeIsMutable();
                        this.reservedRange_.addAll(DescriptorProtos$DescriptorProto.access$5800(descriptorProtos$DescriptorProto));
                    }
                    onChanged();
                }
            } else if (!DescriptorProtos$DescriptorProto.access$5800(descriptorProtos$DescriptorProto).isEmpty()) {
                if (this.reservedRangeBuilder_.isEmpty()) {
                    this.reservedRangeBuilder_.dispose();
                    this.reservedRangeBuilder_ = null;
                    this.reservedRange_ = DescriptorProtos$DescriptorProto.access$5800(descriptorProtos$DescriptorProto);
                    this.bitField0_ &= -257;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getReservedRangeFieldBuilder();
                    }
                    this.reservedRangeBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.reservedRangeBuilder_.addAllMessages(DescriptorProtos$DescriptorProto.access$5800(descriptorProtos$DescriptorProto));
                }
            }
            if (!DescriptorProtos$DescriptorProto.access$5900(descriptorProtos$DescriptorProto).isEmpty()) {
                if (this.reservedName_.isEmpty()) {
                    this.reservedName_ = DescriptorProtos$DescriptorProto.access$5900(descriptorProtos$DescriptorProto);
                    this.bitField0_ &= -513;
                } else {
                    ensureReservedNameIsMutable();
                    this.reservedName_.addAll(DescriptorProtos$DescriptorProto.access$5900(descriptorProtos$DescriptorProto));
                }
                onChanged();
            }
            mergeUnknownFields(descriptorProtos$DescriptorProto.unknownFields);
            onChanged();
        }
        return this;
    }

    public final boolean isInitialized() {
        int i;
        for (i = 0; i < getFieldCount(); i++) {
            if (!getField(i).isInitialized()) {
                return false;
            }
        }
        for (i = 0; i < getExtensionCount(); i++) {
            if (!getExtension(i).isInitialized()) {
                return false;
            }
        }
        for (i = 0; i < getNestedTypeCount(); i++) {
            if (!getNestedType(i).isInitialized()) {
                return false;
            }
        }
        for (i = 0; i < getEnumTypeCount(); i++) {
            if (!getEnumType(i).isInitialized()) {
                return false;
            }
        }
        for (i = 0; i < getExtensionRangeCount(); i++) {
            if (!getExtensionRange(i).isInitialized()) {
                return false;
            }
        }
        for (i = 0; i < getOneofDeclCount(); i++) {
            if (!getOneofDecl(i).isInitialized()) {
                return false;
            }
        }
        if (!hasOptions() || getOptions().isInitialized()) {
            return true;
        }
        return false;
    }

    public DescriptorProtos$DescriptorProto$Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        DescriptorProtos$DescriptorProto descriptorProtos$DescriptorProto;
        Throwable th;
        DescriptorProtos$DescriptorProto descriptorProtos$DescriptorProto2;
        try {
            descriptorProtos$DescriptorProto = (DescriptorProtos$DescriptorProto) DescriptorProtos$DescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (descriptorProtos$DescriptorProto != null) {
                mergeFrom(descriptorProtos$DescriptorProto);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            descriptorProtos$DescriptorProto = (DescriptorProtos$DescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException.unwrapIOException();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            descriptorProtos$DescriptorProto2 = descriptorProtos$DescriptorProto;
            th = th3;
            if (descriptorProtos$DescriptorProto2 != null) {
                mergeFrom(descriptorProtos$DescriptorProto2);
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

    public DescriptorProtos$DescriptorProto$Builder setName(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = str;
        onChanged();
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder clearName() {
        this.bitField0_ &= -2;
        this.name_ = DescriptorProtos$DescriptorProto.getDefaultInstance().getName();
        onChanged();
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder setNameBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.bitField0_ |= 1;
        this.name_ = byteString;
        onChanged();
        return this;
    }

    private void ensureFieldIsMutable() {
        if ((this.bitField0_ & 2) != 2) {
            this.field_ = new ArrayList(this.field_);
            this.bitField0_ |= 2;
        }
    }

    public List<DescriptorProtos$FieldDescriptorProto> getFieldList() {
        if (this.fieldBuilder_ == null) {
            return Collections.unmodifiableList(this.field_);
        }
        return this.fieldBuilder_.getMessageList();
    }

    public int getFieldCount() {
        if (this.fieldBuilder_ == null) {
            return this.field_.size();
        }
        return this.fieldBuilder_.getCount();
    }

    public DescriptorProtos$FieldDescriptorProto getField(int i) {
        if (this.fieldBuilder_ == null) {
            return (DescriptorProtos$FieldDescriptorProto) this.field_.get(i);
        }
        return (DescriptorProtos$FieldDescriptorProto) this.fieldBuilder_.getMessage(i);
    }

    public DescriptorProtos$DescriptorProto$Builder setField(int i, DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto) {
        if (this.fieldBuilder_ != null) {
            this.fieldBuilder_.setMessage(i, descriptorProtos$FieldDescriptorProto);
        } else if (descriptorProtos$FieldDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureFieldIsMutable();
            this.field_.set(i, descriptorProtos$FieldDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder setField(int i, DescriptorProtos$FieldDescriptorProto$Builder descriptorProtos$FieldDescriptorProto$Builder) {
        if (this.fieldBuilder_ == null) {
            ensureFieldIsMutable();
            this.field_.set(i, descriptorProtos$FieldDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.fieldBuilder_.setMessage(i, descriptorProtos$FieldDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addField(DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto) {
        if (this.fieldBuilder_ != null) {
            this.fieldBuilder_.addMessage(descriptorProtos$FieldDescriptorProto);
        } else if (descriptorProtos$FieldDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureFieldIsMutable();
            this.field_.add(descriptorProtos$FieldDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addField(int i, DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto) {
        if (this.fieldBuilder_ != null) {
            this.fieldBuilder_.addMessage(i, descriptorProtos$FieldDescriptorProto);
        } else if (descriptorProtos$FieldDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureFieldIsMutable();
            this.field_.add(i, descriptorProtos$FieldDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addField(DescriptorProtos$FieldDescriptorProto$Builder descriptorProtos$FieldDescriptorProto$Builder) {
        if (this.fieldBuilder_ == null) {
            ensureFieldIsMutable();
            this.field_.add(descriptorProtos$FieldDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.fieldBuilder_.addMessage(descriptorProtos$FieldDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addField(int i, DescriptorProtos$FieldDescriptorProto$Builder descriptorProtos$FieldDescriptorProto$Builder) {
        if (this.fieldBuilder_ == null) {
            ensureFieldIsMutable();
            this.field_.add(i, descriptorProtos$FieldDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.fieldBuilder_.addMessage(i, descriptorProtos$FieldDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addAllField(Iterable<? extends DescriptorProtos$FieldDescriptorProto> iterable) {
        if (this.fieldBuilder_ == null) {
            ensureFieldIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.field_);
            onChanged();
        } else {
            this.fieldBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder clearField() {
        if (this.fieldBuilder_ == null) {
            this.field_ = Collections.emptyList();
            this.bitField0_ &= -3;
            onChanged();
        } else {
            this.fieldBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder removeField(int i) {
        if (this.fieldBuilder_ == null) {
            ensureFieldIsMutable();
            this.field_.remove(i);
            onChanged();
        } else {
            this.fieldBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$FieldDescriptorProto$Builder getFieldBuilder(int i) {
        return (DescriptorProtos$FieldDescriptorProto$Builder) getFieldFieldBuilder().getBuilder(i);
    }

    public FieldDescriptorProtoOrBuilder getFieldOrBuilder(int i) {
        if (this.fieldBuilder_ == null) {
            return (FieldDescriptorProtoOrBuilder) this.field_.get(i);
        }
        return (FieldDescriptorProtoOrBuilder) this.fieldBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends FieldDescriptorProtoOrBuilder> getFieldOrBuilderList() {
        if (this.fieldBuilder_ != null) {
            return this.fieldBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.field_);
    }

    public DescriptorProtos$FieldDescriptorProto$Builder addFieldBuilder() {
        return (DescriptorProtos$FieldDescriptorProto$Builder) getFieldFieldBuilder().addBuilder(DescriptorProtos$FieldDescriptorProto.getDefaultInstance());
    }

    public DescriptorProtos$FieldDescriptorProto$Builder addFieldBuilder(int i) {
        return (DescriptorProtos$FieldDescriptorProto$Builder) getFieldFieldBuilder().addBuilder(i, DescriptorProtos$FieldDescriptorProto.getDefaultInstance());
    }

    public List<DescriptorProtos$FieldDescriptorProto$Builder> getFieldBuilderList() {
        return getFieldFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<DescriptorProtos$FieldDescriptorProto, DescriptorProtos$FieldDescriptorProto$Builder, FieldDescriptorProtoOrBuilder> getFieldFieldBuilder() {
        if (this.fieldBuilder_ == null) {
            this.fieldBuilder_ = new RepeatedFieldBuilderV3(this.field_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
            this.field_ = null;
        }
        return this.fieldBuilder_;
    }

    private void ensureExtensionIsMutable() {
        if ((this.bitField0_ & 4) != 4) {
            this.extension_ = new ArrayList(this.extension_);
            this.bitField0_ |= 4;
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

    public DescriptorProtos$DescriptorProto$Builder setExtension(int i, DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto) {
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

    public DescriptorProtos$DescriptorProto$Builder setExtension(int i, DescriptorProtos$FieldDescriptorProto$Builder descriptorProtos$FieldDescriptorProto$Builder) {
        if (this.extensionBuilder_ == null) {
            ensureExtensionIsMutable();
            this.extension_.set(i, descriptorProtos$FieldDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.extensionBuilder_.setMessage(i, descriptorProtos$FieldDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addExtension(DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto) {
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

    public DescriptorProtos$DescriptorProto$Builder addExtension(int i, DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto) {
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

    public DescriptorProtos$DescriptorProto$Builder addExtension(DescriptorProtos$FieldDescriptorProto$Builder descriptorProtos$FieldDescriptorProto$Builder) {
        if (this.extensionBuilder_ == null) {
            ensureExtensionIsMutable();
            this.extension_.add(descriptorProtos$FieldDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.extensionBuilder_.addMessage(descriptorProtos$FieldDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addExtension(int i, DescriptorProtos$FieldDescriptorProto$Builder descriptorProtos$FieldDescriptorProto$Builder) {
        if (this.extensionBuilder_ == null) {
            ensureExtensionIsMutable();
            this.extension_.add(i, descriptorProtos$FieldDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.extensionBuilder_.addMessage(i, descriptorProtos$FieldDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addAllExtension(Iterable<? extends DescriptorProtos$FieldDescriptorProto> iterable) {
        if (this.extensionBuilder_ == null) {
            ensureExtensionIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.extension_);
            onChanged();
        } else {
            this.extensionBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder clearExtension() {
        if (this.extensionBuilder_ == null) {
            this.extension_ = Collections.emptyList();
            this.bitField0_ &= -5;
            onChanged();
        } else {
            this.extensionBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder removeExtension(int i) {
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
            this.extensionBuilder_ = new RepeatedFieldBuilderV3(this.extension_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
            this.extension_ = null;
        }
        return this.extensionBuilder_;
    }

    private void ensureNestedTypeIsMutable() {
        if ((this.bitField0_ & 8) != 8) {
            this.nestedType_ = new ArrayList(this.nestedType_);
            this.bitField0_ |= 8;
        }
    }

    public List<DescriptorProtos$DescriptorProto> getNestedTypeList() {
        if (this.nestedTypeBuilder_ == null) {
            return Collections.unmodifiableList(this.nestedType_);
        }
        return this.nestedTypeBuilder_.getMessageList();
    }

    public int getNestedTypeCount() {
        if (this.nestedTypeBuilder_ == null) {
            return this.nestedType_.size();
        }
        return this.nestedTypeBuilder_.getCount();
    }

    public DescriptorProtos$DescriptorProto getNestedType(int i) {
        if (this.nestedTypeBuilder_ == null) {
            return (DescriptorProtos$DescriptorProto) this.nestedType_.get(i);
        }
        return (DescriptorProtos$DescriptorProto) this.nestedTypeBuilder_.getMessage(i);
    }

    public DescriptorProtos$DescriptorProto$Builder setNestedType(int i, DescriptorProtos$DescriptorProto descriptorProtos$DescriptorProto) {
        if (this.nestedTypeBuilder_ != null) {
            this.nestedTypeBuilder_.setMessage(i, descriptorProtos$DescriptorProto);
        } else if (descriptorProtos$DescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureNestedTypeIsMutable();
            this.nestedType_.set(i, descriptorProtos$DescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder setNestedType(int i, DescriptorProtos$DescriptorProto$Builder descriptorProtos$DescriptorProto$Builder) {
        if (this.nestedTypeBuilder_ == null) {
            ensureNestedTypeIsMutable();
            this.nestedType_.set(i, descriptorProtos$DescriptorProto$Builder.build());
            onChanged();
        } else {
            this.nestedTypeBuilder_.setMessage(i, descriptorProtos$DescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addNestedType(DescriptorProtos$DescriptorProto descriptorProtos$DescriptorProto) {
        if (this.nestedTypeBuilder_ != null) {
            this.nestedTypeBuilder_.addMessage(descriptorProtos$DescriptorProto);
        } else if (descriptorProtos$DescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureNestedTypeIsMutable();
            this.nestedType_.add(descriptorProtos$DescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addNestedType(int i, DescriptorProtos$DescriptorProto descriptorProtos$DescriptorProto) {
        if (this.nestedTypeBuilder_ != null) {
            this.nestedTypeBuilder_.addMessage(i, descriptorProtos$DescriptorProto);
        } else if (descriptorProtos$DescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureNestedTypeIsMutable();
            this.nestedType_.add(i, descriptorProtos$DescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addNestedType(DescriptorProtos$DescriptorProto$Builder descriptorProtos$DescriptorProto$Builder) {
        if (this.nestedTypeBuilder_ == null) {
            ensureNestedTypeIsMutable();
            this.nestedType_.add(descriptorProtos$DescriptorProto$Builder.build());
            onChanged();
        } else {
            this.nestedTypeBuilder_.addMessage(descriptorProtos$DescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addNestedType(int i, DescriptorProtos$DescriptorProto$Builder descriptorProtos$DescriptorProto$Builder) {
        if (this.nestedTypeBuilder_ == null) {
            ensureNestedTypeIsMutable();
            this.nestedType_.add(i, descriptorProtos$DescriptorProto$Builder.build());
            onChanged();
        } else {
            this.nestedTypeBuilder_.addMessage(i, descriptorProtos$DescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addAllNestedType(Iterable<? extends DescriptorProtos$DescriptorProto> iterable) {
        if (this.nestedTypeBuilder_ == null) {
            ensureNestedTypeIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.nestedType_);
            onChanged();
        } else {
            this.nestedTypeBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder clearNestedType() {
        if (this.nestedTypeBuilder_ == null) {
            this.nestedType_ = Collections.emptyList();
            this.bitField0_ &= -9;
            onChanged();
        } else {
            this.nestedTypeBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder removeNestedType(int i) {
        if (this.nestedTypeBuilder_ == null) {
            ensureNestedTypeIsMutable();
            this.nestedType_.remove(i);
            onChanged();
        } else {
            this.nestedTypeBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder getNestedTypeBuilder(int i) {
        return (DescriptorProtos$DescriptorProto$Builder) getNestedTypeFieldBuilder().getBuilder(i);
    }

    public DescriptorProtoOrBuilder getNestedTypeOrBuilder(int i) {
        if (this.nestedTypeBuilder_ == null) {
            return (DescriptorProtoOrBuilder) this.nestedType_.get(i);
        }
        return (DescriptorProtoOrBuilder) this.nestedTypeBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends DescriptorProtoOrBuilder> getNestedTypeOrBuilderList() {
        if (this.nestedTypeBuilder_ != null) {
            return this.nestedTypeBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.nestedType_);
    }

    public DescriptorProtos$DescriptorProto$Builder addNestedTypeBuilder() {
        return (DescriptorProtos$DescriptorProto$Builder) getNestedTypeFieldBuilder().addBuilder(DescriptorProtos$DescriptorProto.getDefaultInstance());
    }

    public DescriptorProtos$DescriptorProto$Builder addNestedTypeBuilder(int i) {
        return (DescriptorProtos$DescriptorProto$Builder) getNestedTypeFieldBuilder().addBuilder(i, DescriptorProtos$DescriptorProto.getDefaultInstance());
    }

    public List<DescriptorProtos$DescriptorProto$Builder> getNestedTypeBuilderList() {
        return getNestedTypeFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<DescriptorProtos$DescriptorProto, DescriptorProtos$DescriptorProto$Builder, DescriptorProtoOrBuilder> getNestedTypeFieldBuilder() {
        if (this.nestedTypeBuilder_ == null) {
            this.nestedTypeBuilder_ = new RepeatedFieldBuilderV3(this.nestedType_, (this.bitField0_ & 8) == 8, getParentForChildren(), isClean());
            this.nestedType_ = null;
        }
        return this.nestedTypeBuilder_;
    }

    private void ensureEnumTypeIsMutable() {
        if ((this.bitField0_ & 16) != 16) {
            this.enumType_ = new ArrayList(this.enumType_);
            this.bitField0_ |= 16;
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

    public DescriptorProtos$DescriptorProto$Builder setEnumType(int i, DescriptorProtos$EnumDescriptorProto descriptorProtos$EnumDescriptorProto) {
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

    public DescriptorProtos$DescriptorProto$Builder setEnumType(int i, DescriptorProtos$EnumDescriptorProto$Builder descriptorProtos$EnumDescriptorProto$Builder) {
        if (this.enumTypeBuilder_ == null) {
            ensureEnumTypeIsMutable();
            this.enumType_.set(i, descriptorProtos$EnumDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.enumTypeBuilder_.setMessage(i, descriptorProtos$EnumDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addEnumType(DescriptorProtos$EnumDescriptorProto descriptorProtos$EnumDescriptorProto) {
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

    public DescriptorProtos$DescriptorProto$Builder addEnumType(int i, DescriptorProtos$EnumDescriptorProto descriptorProtos$EnumDescriptorProto) {
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

    public DescriptorProtos$DescriptorProto$Builder addEnumType(DescriptorProtos$EnumDescriptorProto$Builder descriptorProtos$EnumDescriptorProto$Builder) {
        if (this.enumTypeBuilder_ == null) {
            ensureEnumTypeIsMutable();
            this.enumType_.add(descriptorProtos$EnumDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.enumTypeBuilder_.addMessage(descriptorProtos$EnumDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addEnumType(int i, DescriptorProtos$EnumDescriptorProto$Builder descriptorProtos$EnumDescriptorProto$Builder) {
        if (this.enumTypeBuilder_ == null) {
            ensureEnumTypeIsMutable();
            this.enumType_.add(i, descriptorProtos$EnumDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.enumTypeBuilder_.addMessage(i, descriptorProtos$EnumDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addAllEnumType(Iterable<? extends DescriptorProtos$EnumDescriptorProto> iterable) {
        if (this.enumTypeBuilder_ == null) {
            ensureEnumTypeIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.enumType_);
            onChanged();
        } else {
            this.enumTypeBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder clearEnumType() {
        if (this.enumTypeBuilder_ == null) {
            this.enumType_ = Collections.emptyList();
            this.bitField0_ &= -17;
            onChanged();
        } else {
            this.enumTypeBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder removeEnumType(int i) {
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
            this.enumTypeBuilder_ = new RepeatedFieldBuilderV3(this.enumType_, (this.bitField0_ & 16) == 16, getParentForChildren(), isClean());
            this.enumType_ = null;
        }
        return this.enumTypeBuilder_;
    }

    private void ensureExtensionRangeIsMutable() {
        if ((this.bitField0_ & 32) != 32) {
            this.extensionRange_ = new ArrayList(this.extensionRange_);
            this.bitField0_ |= 32;
        }
    }

    public List<ExtensionRange> getExtensionRangeList() {
        if (this.extensionRangeBuilder_ == null) {
            return Collections.unmodifiableList(this.extensionRange_);
        }
        return this.extensionRangeBuilder_.getMessageList();
    }

    public int getExtensionRangeCount() {
        if (this.extensionRangeBuilder_ == null) {
            return this.extensionRange_.size();
        }
        return this.extensionRangeBuilder_.getCount();
    }

    public ExtensionRange getExtensionRange(int i) {
        if (this.extensionRangeBuilder_ == null) {
            return (ExtensionRange) this.extensionRange_.get(i);
        }
        return (ExtensionRange) this.extensionRangeBuilder_.getMessage(i);
    }

    public DescriptorProtos$DescriptorProto$Builder setExtensionRange(int i, ExtensionRange extensionRange) {
        if (this.extensionRangeBuilder_ != null) {
            this.extensionRangeBuilder_.setMessage(i, extensionRange);
        } else if (extensionRange == null) {
            throw new NullPointerException();
        } else {
            ensureExtensionRangeIsMutable();
            this.extensionRange_.set(i, extensionRange);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder setExtensionRange(int i, DescriptorProtos$DescriptorProto$ExtensionRange$Builder descriptorProtos$DescriptorProto$ExtensionRange$Builder) {
        if (this.extensionRangeBuilder_ == null) {
            ensureExtensionRangeIsMutable();
            this.extensionRange_.set(i, descriptorProtos$DescriptorProto$ExtensionRange$Builder.build());
            onChanged();
        } else {
            this.extensionRangeBuilder_.setMessage(i, descriptorProtos$DescriptorProto$ExtensionRange$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addExtensionRange(ExtensionRange extensionRange) {
        if (this.extensionRangeBuilder_ != null) {
            this.extensionRangeBuilder_.addMessage(extensionRange);
        } else if (extensionRange == null) {
            throw new NullPointerException();
        } else {
            ensureExtensionRangeIsMutable();
            this.extensionRange_.add(extensionRange);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addExtensionRange(int i, ExtensionRange extensionRange) {
        if (this.extensionRangeBuilder_ != null) {
            this.extensionRangeBuilder_.addMessage(i, extensionRange);
        } else if (extensionRange == null) {
            throw new NullPointerException();
        } else {
            ensureExtensionRangeIsMutable();
            this.extensionRange_.add(i, extensionRange);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addExtensionRange(DescriptorProtos$DescriptorProto$ExtensionRange$Builder descriptorProtos$DescriptorProto$ExtensionRange$Builder) {
        if (this.extensionRangeBuilder_ == null) {
            ensureExtensionRangeIsMutable();
            this.extensionRange_.add(descriptorProtos$DescriptorProto$ExtensionRange$Builder.build());
            onChanged();
        } else {
            this.extensionRangeBuilder_.addMessage(descriptorProtos$DescriptorProto$ExtensionRange$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addExtensionRange(int i, DescriptorProtos$DescriptorProto$ExtensionRange$Builder descriptorProtos$DescriptorProto$ExtensionRange$Builder) {
        if (this.extensionRangeBuilder_ == null) {
            ensureExtensionRangeIsMutable();
            this.extensionRange_.add(i, descriptorProtos$DescriptorProto$ExtensionRange$Builder.build());
            onChanged();
        } else {
            this.extensionRangeBuilder_.addMessage(i, descriptorProtos$DescriptorProto$ExtensionRange$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addAllExtensionRange(Iterable<? extends ExtensionRange> iterable) {
        if (this.extensionRangeBuilder_ == null) {
            ensureExtensionRangeIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.extensionRange_);
            onChanged();
        } else {
            this.extensionRangeBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder clearExtensionRange() {
        if (this.extensionRangeBuilder_ == null) {
            this.extensionRange_ = Collections.emptyList();
            this.bitField0_ &= -33;
            onChanged();
        } else {
            this.extensionRangeBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder removeExtensionRange(int i) {
        if (this.extensionRangeBuilder_ == null) {
            ensureExtensionRangeIsMutable();
            this.extensionRange_.remove(i);
            onChanged();
        } else {
            this.extensionRangeBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder getExtensionRangeBuilder(int i) {
        return (DescriptorProtos$DescriptorProto$ExtensionRange$Builder) getExtensionRangeFieldBuilder().getBuilder(i);
    }

    public DescriptorProtos$DescriptorProto$ExtensionRangeOrBuilder getExtensionRangeOrBuilder(int i) {
        if (this.extensionRangeBuilder_ == null) {
            return (DescriptorProtos$DescriptorProto$ExtensionRangeOrBuilder) this.extensionRange_.get(i);
        }
        return (DescriptorProtos$DescriptorProto$ExtensionRangeOrBuilder) this.extensionRangeBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends DescriptorProtos$DescriptorProto$ExtensionRangeOrBuilder> getExtensionRangeOrBuilderList() {
        if (this.extensionRangeBuilder_ != null) {
            return this.extensionRangeBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.extensionRange_);
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder addExtensionRangeBuilder() {
        return (DescriptorProtos$DescriptorProto$ExtensionRange$Builder) getExtensionRangeFieldBuilder().addBuilder(ExtensionRange.getDefaultInstance());
    }

    public DescriptorProtos$DescriptorProto$ExtensionRange$Builder addExtensionRangeBuilder(int i) {
        return (DescriptorProtos$DescriptorProto$ExtensionRange$Builder) getExtensionRangeFieldBuilder().addBuilder(i, ExtensionRange.getDefaultInstance());
    }

    public List<DescriptorProtos$DescriptorProto$ExtensionRange$Builder> getExtensionRangeBuilderList() {
        return getExtensionRangeFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<ExtensionRange, DescriptorProtos$DescriptorProto$ExtensionRange$Builder, DescriptorProtos$DescriptorProto$ExtensionRangeOrBuilder> getExtensionRangeFieldBuilder() {
        if (this.extensionRangeBuilder_ == null) {
            this.extensionRangeBuilder_ = new RepeatedFieldBuilderV3(this.extensionRange_, (this.bitField0_ & 32) == 32, getParentForChildren(), isClean());
            this.extensionRange_ = null;
        }
        return this.extensionRangeBuilder_;
    }

    private void ensureOneofDeclIsMutable() {
        if ((this.bitField0_ & 64) != 64) {
            this.oneofDecl_ = new ArrayList(this.oneofDecl_);
            this.bitField0_ |= 64;
        }
    }

    public List<DescriptorProtos$OneofDescriptorProto> getOneofDeclList() {
        if (this.oneofDeclBuilder_ == null) {
            return Collections.unmodifiableList(this.oneofDecl_);
        }
        return this.oneofDeclBuilder_.getMessageList();
    }

    public int getOneofDeclCount() {
        if (this.oneofDeclBuilder_ == null) {
            return this.oneofDecl_.size();
        }
        return this.oneofDeclBuilder_.getCount();
    }

    public DescriptorProtos$OneofDescriptorProto getOneofDecl(int i) {
        if (this.oneofDeclBuilder_ == null) {
            return (DescriptorProtos$OneofDescriptorProto) this.oneofDecl_.get(i);
        }
        return (DescriptorProtos$OneofDescriptorProto) this.oneofDeclBuilder_.getMessage(i);
    }

    public DescriptorProtos$DescriptorProto$Builder setOneofDecl(int i, DescriptorProtos$OneofDescriptorProto descriptorProtos$OneofDescriptorProto) {
        if (this.oneofDeclBuilder_ != null) {
            this.oneofDeclBuilder_.setMessage(i, descriptorProtos$OneofDescriptorProto);
        } else if (descriptorProtos$OneofDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureOneofDeclIsMutable();
            this.oneofDecl_.set(i, descriptorProtos$OneofDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder setOneofDecl(int i, DescriptorProtos$OneofDescriptorProto$Builder descriptorProtos$OneofDescriptorProto$Builder) {
        if (this.oneofDeclBuilder_ == null) {
            ensureOneofDeclIsMutable();
            this.oneofDecl_.set(i, descriptorProtos$OneofDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.oneofDeclBuilder_.setMessage(i, descriptorProtos$OneofDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addOneofDecl(DescriptorProtos$OneofDescriptorProto descriptorProtos$OneofDescriptorProto) {
        if (this.oneofDeclBuilder_ != null) {
            this.oneofDeclBuilder_.addMessage(descriptorProtos$OneofDescriptorProto);
        } else if (descriptorProtos$OneofDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureOneofDeclIsMutable();
            this.oneofDecl_.add(descriptorProtos$OneofDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addOneofDecl(int i, DescriptorProtos$OneofDescriptorProto descriptorProtos$OneofDescriptorProto) {
        if (this.oneofDeclBuilder_ != null) {
            this.oneofDeclBuilder_.addMessage(i, descriptorProtos$OneofDescriptorProto);
        } else if (descriptorProtos$OneofDescriptorProto == null) {
            throw new NullPointerException();
        } else {
            ensureOneofDeclIsMutable();
            this.oneofDecl_.add(i, descriptorProtos$OneofDescriptorProto);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addOneofDecl(DescriptorProtos$OneofDescriptorProto$Builder descriptorProtos$OneofDescriptorProto$Builder) {
        if (this.oneofDeclBuilder_ == null) {
            ensureOneofDeclIsMutable();
            this.oneofDecl_.add(descriptorProtos$OneofDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.oneofDeclBuilder_.addMessage(descriptorProtos$OneofDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addOneofDecl(int i, DescriptorProtos$OneofDescriptorProto$Builder descriptorProtos$OneofDescriptorProto$Builder) {
        if (this.oneofDeclBuilder_ == null) {
            ensureOneofDeclIsMutable();
            this.oneofDecl_.add(i, descriptorProtos$OneofDescriptorProto$Builder.build());
            onChanged();
        } else {
            this.oneofDeclBuilder_.addMessage(i, descriptorProtos$OneofDescriptorProto$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addAllOneofDecl(Iterable<? extends DescriptorProtos$OneofDescriptorProto> iterable) {
        if (this.oneofDeclBuilder_ == null) {
            ensureOneofDeclIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.oneofDecl_);
            onChanged();
        } else {
            this.oneofDeclBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder clearOneofDecl() {
        if (this.oneofDeclBuilder_ == null) {
            this.oneofDecl_ = Collections.emptyList();
            this.bitField0_ &= -65;
            onChanged();
        } else {
            this.oneofDeclBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder removeOneofDecl(int i) {
        if (this.oneofDeclBuilder_ == null) {
            ensureOneofDeclIsMutable();
            this.oneofDecl_.remove(i);
            onChanged();
        } else {
            this.oneofDeclBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$OneofDescriptorProto$Builder getOneofDeclBuilder(int i) {
        return (DescriptorProtos$OneofDescriptorProto$Builder) getOneofDeclFieldBuilder().getBuilder(i);
    }

    public OneofDescriptorProtoOrBuilder getOneofDeclOrBuilder(int i) {
        if (this.oneofDeclBuilder_ == null) {
            return (OneofDescriptorProtoOrBuilder) this.oneofDecl_.get(i);
        }
        return (OneofDescriptorProtoOrBuilder) this.oneofDeclBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends OneofDescriptorProtoOrBuilder> getOneofDeclOrBuilderList() {
        if (this.oneofDeclBuilder_ != null) {
            return this.oneofDeclBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.oneofDecl_);
    }

    public DescriptorProtos$OneofDescriptorProto$Builder addOneofDeclBuilder() {
        return (DescriptorProtos$OneofDescriptorProto$Builder) getOneofDeclFieldBuilder().addBuilder(DescriptorProtos$OneofDescriptorProto.getDefaultInstance());
    }

    public DescriptorProtos$OneofDescriptorProto$Builder addOneofDeclBuilder(int i) {
        return (DescriptorProtos$OneofDescriptorProto$Builder) getOneofDeclFieldBuilder().addBuilder(i, DescriptorProtos$OneofDescriptorProto.getDefaultInstance());
    }

    public List<DescriptorProtos$OneofDescriptorProto$Builder> getOneofDeclBuilderList() {
        return getOneofDeclFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<DescriptorProtos$OneofDescriptorProto, DescriptorProtos$OneofDescriptorProto$Builder, OneofDescriptorProtoOrBuilder> getOneofDeclFieldBuilder() {
        if (this.oneofDeclBuilder_ == null) {
            this.oneofDeclBuilder_ = new RepeatedFieldBuilderV3(this.oneofDecl_, (this.bitField0_ & 64) == 64, getParentForChildren(), isClean());
            this.oneofDecl_ = null;
        }
        return this.oneofDeclBuilder_;
    }

    public boolean hasOptions() {
        return (this.bitField0_ & 128) == 128;
    }

    public DescriptorProtos$MessageOptions getOptions() {
        if (this.optionsBuilder_ == null) {
            return this.options_ == null ? DescriptorProtos$MessageOptions.getDefaultInstance() : this.options_;
        } else {
            return (DescriptorProtos$MessageOptions) this.optionsBuilder_.getMessage();
        }
    }

    public DescriptorProtos$DescriptorProto$Builder setOptions(DescriptorProtos$MessageOptions descriptorProtos$MessageOptions) {
        if (this.optionsBuilder_ != null) {
            this.optionsBuilder_.setMessage(descriptorProtos$MessageOptions);
        } else if (descriptorProtos$MessageOptions == null) {
            throw new NullPointerException();
        } else {
            this.options_ = descriptorProtos$MessageOptions;
            onChanged();
        }
        this.bitField0_ |= 128;
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder setOptions(DescriptorProtos$MessageOptions$Builder descriptorProtos$MessageOptions$Builder) {
        if (this.optionsBuilder_ == null) {
            this.options_ = descriptorProtos$MessageOptions$Builder.build();
            onChanged();
        } else {
            this.optionsBuilder_.setMessage(descriptorProtos$MessageOptions$Builder.build());
        }
        this.bitField0_ |= 128;
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder mergeOptions(DescriptorProtos$MessageOptions descriptorProtos$MessageOptions) {
        if (this.optionsBuilder_ == null) {
            if ((this.bitField0_ & 128) != 128 || this.options_ == null || this.options_ == DescriptorProtos$MessageOptions.getDefaultInstance()) {
                this.options_ = descriptorProtos$MessageOptions;
            } else {
                this.options_ = DescriptorProtos$MessageOptions.newBuilder(this.options_).mergeFrom(descriptorProtos$MessageOptions).buildPartial();
            }
            onChanged();
        } else {
            this.optionsBuilder_.mergeFrom(descriptorProtos$MessageOptions);
        }
        this.bitField0_ |= 128;
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder clearOptions() {
        if (this.optionsBuilder_ == null) {
            this.options_ = null;
            onChanged();
        } else {
            this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -129;
        return this;
    }

    public DescriptorProtos$MessageOptions$Builder getOptionsBuilder() {
        this.bitField0_ |= 128;
        onChanged();
        return (DescriptorProtos$MessageOptions$Builder) getOptionsFieldBuilder().getBuilder();
    }

    public MessageOptionsOrBuilder getOptionsOrBuilder() {
        if (this.optionsBuilder_ != null) {
            return (MessageOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder();
        }
        return this.options_ == null ? DescriptorProtos$MessageOptions.getDefaultInstance() : this.options_;
    }

    private SingleFieldBuilderV3<DescriptorProtos$MessageOptions, DescriptorProtos$MessageOptions$Builder, MessageOptionsOrBuilder> getOptionsFieldBuilder() {
        if (this.optionsBuilder_ == null) {
            this.optionsBuilder_ = new SingleFieldBuilderV3(getOptions(), getParentForChildren(), isClean());
            this.options_ = null;
        }
        return this.optionsBuilder_;
    }

    private void ensureReservedRangeIsMutable() {
        if ((this.bitField0_ & 256) != 256) {
            this.reservedRange_ = new ArrayList(this.reservedRange_);
            this.bitField0_ |= 256;
        }
    }

    public List<ReservedRange> getReservedRangeList() {
        if (this.reservedRangeBuilder_ == null) {
            return Collections.unmodifiableList(this.reservedRange_);
        }
        return this.reservedRangeBuilder_.getMessageList();
    }

    public int getReservedRangeCount() {
        if (this.reservedRangeBuilder_ == null) {
            return this.reservedRange_.size();
        }
        return this.reservedRangeBuilder_.getCount();
    }

    public ReservedRange getReservedRange(int i) {
        if (this.reservedRangeBuilder_ == null) {
            return (ReservedRange) this.reservedRange_.get(i);
        }
        return (ReservedRange) this.reservedRangeBuilder_.getMessage(i);
    }

    public DescriptorProtos$DescriptorProto$Builder setReservedRange(int i, ReservedRange reservedRange) {
        if (this.reservedRangeBuilder_ != null) {
            this.reservedRangeBuilder_.setMessage(i, reservedRange);
        } else if (reservedRange == null) {
            throw new NullPointerException();
        } else {
            ensureReservedRangeIsMutable();
            this.reservedRange_.set(i, reservedRange);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder setReservedRange(int i, DescriptorProtos$DescriptorProto$ReservedRange$Builder descriptorProtos$DescriptorProto$ReservedRange$Builder) {
        if (this.reservedRangeBuilder_ == null) {
            ensureReservedRangeIsMutable();
            this.reservedRange_.set(i, descriptorProtos$DescriptorProto$ReservedRange$Builder.build());
            onChanged();
        } else {
            this.reservedRangeBuilder_.setMessage(i, descriptorProtos$DescriptorProto$ReservedRange$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addReservedRange(ReservedRange reservedRange) {
        if (this.reservedRangeBuilder_ != null) {
            this.reservedRangeBuilder_.addMessage(reservedRange);
        } else if (reservedRange == null) {
            throw new NullPointerException();
        } else {
            ensureReservedRangeIsMutable();
            this.reservedRange_.add(reservedRange);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addReservedRange(int i, ReservedRange reservedRange) {
        if (this.reservedRangeBuilder_ != null) {
            this.reservedRangeBuilder_.addMessage(i, reservedRange);
        } else if (reservedRange == null) {
            throw new NullPointerException();
        } else {
            ensureReservedRangeIsMutable();
            this.reservedRange_.add(i, reservedRange);
            onChanged();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addReservedRange(DescriptorProtos$DescriptorProto$ReservedRange$Builder descriptorProtos$DescriptorProto$ReservedRange$Builder) {
        if (this.reservedRangeBuilder_ == null) {
            ensureReservedRangeIsMutable();
            this.reservedRange_.add(descriptorProtos$DescriptorProto$ReservedRange$Builder.build());
            onChanged();
        } else {
            this.reservedRangeBuilder_.addMessage(descriptorProtos$DescriptorProto$ReservedRange$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addReservedRange(int i, DescriptorProtos$DescriptorProto$ReservedRange$Builder descriptorProtos$DescriptorProto$ReservedRange$Builder) {
        if (this.reservedRangeBuilder_ == null) {
            ensureReservedRangeIsMutable();
            this.reservedRange_.add(i, descriptorProtos$DescriptorProto$ReservedRange$Builder.build());
            onChanged();
        } else {
            this.reservedRangeBuilder_.addMessage(i, descriptorProtos$DescriptorProto$ReservedRange$Builder.build());
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addAllReservedRange(Iterable<? extends ReservedRange> iterable) {
        if (this.reservedRangeBuilder_ == null) {
            ensureReservedRangeIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.reservedRange_);
            onChanged();
        } else {
            this.reservedRangeBuilder_.addAllMessages(iterable);
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder clearReservedRange() {
        if (this.reservedRangeBuilder_ == null) {
            this.reservedRange_ = Collections.emptyList();
            this.bitField0_ &= -257;
            onChanged();
        } else {
            this.reservedRangeBuilder_.clear();
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder removeReservedRange(int i) {
        if (this.reservedRangeBuilder_ == null) {
            ensureReservedRangeIsMutable();
            this.reservedRange_.remove(i);
            onChanged();
        } else {
            this.reservedRangeBuilder_.remove(i);
        }
        return this;
    }

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder getReservedRangeBuilder(int i) {
        return (DescriptorProtos$DescriptorProto$ReservedRange$Builder) getReservedRangeFieldBuilder().getBuilder(i);
    }

    public DescriptorProtos$DescriptorProto$ReservedRangeOrBuilder getReservedRangeOrBuilder(int i) {
        if (this.reservedRangeBuilder_ == null) {
            return (DescriptorProtos$DescriptorProto$ReservedRangeOrBuilder) this.reservedRange_.get(i);
        }
        return (DescriptorProtos$DescriptorProto$ReservedRangeOrBuilder) this.reservedRangeBuilder_.getMessageOrBuilder(i);
    }

    public List<? extends DescriptorProtos$DescriptorProto$ReservedRangeOrBuilder> getReservedRangeOrBuilderList() {
        if (this.reservedRangeBuilder_ != null) {
            return this.reservedRangeBuilder_.getMessageOrBuilderList();
        }
        return Collections.unmodifiableList(this.reservedRange_);
    }

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder addReservedRangeBuilder() {
        return (DescriptorProtos$DescriptorProto$ReservedRange$Builder) getReservedRangeFieldBuilder().addBuilder(ReservedRange.getDefaultInstance());
    }

    public DescriptorProtos$DescriptorProto$ReservedRange$Builder addReservedRangeBuilder(int i) {
        return (DescriptorProtos$DescriptorProto$ReservedRange$Builder) getReservedRangeFieldBuilder().addBuilder(i, ReservedRange.getDefaultInstance());
    }

    public List<DescriptorProtos$DescriptorProto$ReservedRange$Builder> getReservedRangeBuilderList() {
        return getReservedRangeFieldBuilder().getBuilderList();
    }

    private RepeatedFieldBuilderV3<ReservedRange, DescriptorProtos$DescriptorProto$ReservedRange$Builder, DescriptorProtos$DescriptorProto$ReservedRangeOrBuilder> getReservedRangeFieldBuilder() {
        if (this.reservedRangeBuilder_ == null) {
            this.reservedRangeBuilder_ = new RepeatedFieldBuilderV3(this.reservedRange_, (this.bitField0_ & 256) == 256, getParentForChildren(), isClean());
            this.reservedRange_ = null;
        }
        return this.reservedRangeBuilder_;
    }

    private void ensureReservedNameIsMutable() {
        if ((this.bitField0_ & 512) != 512) {
            this.reservedName_ = new LazyStringArrayList(this.reservedName_);
            this.bitField0_ |= 512;
        }
    }

    public ProtocolStringList getReservedNameList() {
        return this.reservedName_.getUnmodifiableView();
    }

    public int getReservedNameCount() {
        return this.reservedName_.size();
    }

    public String getReservedName(int i) {
        return (String) this.reservedName_.get(i);
    }

    public ByteString getReservedNameBytes(int i) {
        return this.reservedName_.getByteString(i);
    }

    public DescriptorProtos$DescriptorProto$Builder setReservedName(int i, String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        ensureReservedNameIsMutable();
        this.reservedName_.set(i, str);
        onChanged();
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addReservedName(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        ensureReservedNameIsMutable();
        this.reservedName_.add(str);
        onChanged();
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addAllReservedName(Iterable<String> iterable) {
        ensureReservedNameIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, this.reservedName_);
        onChanged();
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder clearReservedName() {
        this.reservedName_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= -513;
        onChanged();
        return this;
    }

    public DescriptorProtos$DescriptorProto$Builder addReservedNameBytes(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        ensureReservedNameIsMutable();
        this.reservedName_.add(byteString);
        onChanged();
        return this;
    }

    public final DescriptorProtos$DescriptorProto$Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$DescriptorProto$Builder) super.setUnknownFields(unknownFieldSet);
    }

    public final DescriptorProtos$DescriptorProto$Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        return (DescriptorProtos$DescriptorProto$Builder) super.mergeUnknownFields(unknownFieldSet);
    }
}
