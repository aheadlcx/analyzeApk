package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.UninterpretedOptionOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessage.GeneratedExtension;
import com.google.protobuf.GeneratedMessageV3.BuilderParent;
import com.google.protobuf.GeneratedMessageV3.ExtendableBuilder;
import com.google.protobuf.GeneratedMessageV3.ExtendableMessage;
import com.google.protobuf.GeneratedMessageV3.ExtendableMessage.ExtensionWriter;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.Internal.EnumLiteMap;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$FileOptions extends ExtendableMessage<DescriptorProtos$FileOptions> implements DescriptorProtos$FileOptionsOrBuilder {
    public static final int CC_ENABLE_ARENAS_FIELD_NUMBER = 31;
    public static final int CC_GENERIC_SERVICES_FIELD_NUMBER = 16;
    public static final int CSHARP_NAMESPACE_FIELD_NUMBER = 37;
    private static final DescriptorProtos$FileOptions DEFAULT_INSTANCE = new DescriptorProtos$FileOptions();
    public static final int DEPRECATED_FIELD_NUMBER = 23;
    public static final int GO_PACKAGE_FIELD_NUMBER = 11;
    public static final int JAVA_GENERATE_EQUALS_AND_HASH_FIELD_NUMBER = 20;
    public static final int JAVA_GENERIC_SERVICES_FIELD_NUMBER = 17;
    public static final int JAVA_MULTIPLE_FILES_FIELD_NUMBER = 10;
    public static final int JAVA_OUTER_CLASSNAME_FIELD_NUMBER = 8;
    public static final int JAVA_PACKAGE_FIELD_NUMBER = 1;
    public static final int JAVA_STRING_CHECK_UTF8_FIELD_NUMBER = 27;
    public static final int OBJC_CLASS_PREFIX_FIELD_NUMBER = 36;
    public static final int OPTIMIZE_FOR_FIELD_NUMBER = 9;
    @Deprecated
    public static final Parser<DescriptorProtos$FileOptions> PARSER = new DescriptorProtos$FileOptions$1();
    public static final int PHP_CLASS_PREFIX_FIELD_NUMBER = 40;
    public static final int PHP_GENERIC_SERVICES_FIELD_NUMBER = 19;
    public static final int PHP_NAMESPACE_FIELD_NUMBER = 41;
    public static final int PY_GENERIC_SERVICES_FIELD_NUMBER = 18;
    public static final int SWIFT_PREFIX_FIELD_NUMBER = 39;
    public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private boolean ccEnableArenas_;
    private boolean ccGenericServices_;
    private volatile Object csharpNamespace_;
    private boolean deprecated_;
    private volatile Object goPackage_;
    private boolean javaGenerateEqualsAndHash_;
    private boolean javaGenericServices_;
    private boolean javaMultipleFiles_;
    private volatile Object javaOuterClassname_;
    private volatile Object javaPackage_;
    private boolean javaStringCheckUtf8_;
    private byte memoizedIsInitialized;
    private volatile Object objcClassPrefix_;
    private int optimizeFor_;
    private volatile Object phpClassPrefix_;
    private boolean phpGenericServices_;
    private volatile Object phpNamespace_;
    private boolean pyGenericServices_;
    private volatile Object swiftPrefix_;
    private List<DescriptorProtos$UninterpretedOption> uninterpretedOption_;

    public static final class Builder extends ExtendableBuilder<DescriptorProtos$FileOptions, Builder> implements DescriptorProtos$FileOptionsOrBuilder {
        private int bitField0_;
        private boolean ccEnableArenas_;
        private boolean ccGenericServices_;
        private Object csharpNamespace_;
        private boolean deprecated_;
        private Object goPackage_;
        private boolean javaGenerateEqualsAndHash_;
        private boolean javaGenericServices_;
        private boolean javaMultipleFiles_;
        private Object javaOuterClassname_;
        private Object javaPackage_;
        private boolean javaStringCheckUtf8_;
        private Object objcClassPrefix_;
        private int optimizeFor_;
        private Object phpClassPrefix_;
        private boolean phpGenericServices_;
        private Object phpNamespace_;
        private boolean pyGenericServices_;
        private Object swiftPrefix_;
        private RepeatedFieldBuilderV3<DescriptorProtos$UninterpretedOption, DescriptorProtos$UninterpretedOption$Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
        private List<DescriptorProtos$UninterpretedOption> uninterpretedOption_;

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.access$13800();
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.access$13900().ensureFieldAccessorsInitialized(DescriptorProtos$FileOptions.class, Builder.class);
        }

        private Builder() {
            this.javaPackage_ = "";
            this.javaOuterClassname_ = "";
            this.optimizeFor_ = 1;
            this.goPackage_ = "";
            this.objcClassPrefix_ = "";
            this.csharpNamespace_ = "";
            this.swiftPrefix_ = "";
            this.phpClassPrefix_ = "";
            this.phpNamespace_ = "";
            this.uninterpretedOption_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.javaPackage_ = "";
            this.javaOuterClassname_ = "";
            this.optimizeFor_ = 1;
            this.goPackage_ = "";
            this.objcClassPrefix_ = "";
            this.csharpNamespace_ = "";
            this.swiftPrefix_ = "";
            this.phpClassPrefix_ = "";
            this.phpNamespace_ = "";
            this.uninterpretedOption_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                getUninterpretedOptionFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.javaPackage_ = "";
            this.bitField0_ &= -2;
            this.javaOuterClassname_ = "";
            this.bitField0_ &= -3;
            this.javaMultipleFiles_ = false;
            this.bitField0_ &= -5;
            this.javaGenerateEqualsAndHash_ = false;
            this.bitField0_ &= -9;
            this.javaStringCheckUtf8_ = false;
            this.bitField0_ &= -17;
            this.optimizeFor_ = 1;
            this.bitField0_ &= -33;
            this.goPackage_ = "";
            this.bitField0_ &= -65;
            this.ccGenericServices_ = false;
            this.bitField0_ &= -129;
            this.javaGenericServices_ = false;
            this.bitField0_ &= -257;
            this.pyGenericServices_ = false;
            this.bitField0_ &= -513;
            this.phpGenericServices_ = false;
            this.bitField0_ &= -1025;
            this.deprecated_ = false;
            this.bitField0_ &= -2049;
            this.ccEnableArenas_ = false;
            this.bitField0_ &= -4097;
            this.objcClassPrefix_ = "";
            this.bitField0_ &= -8193;
            this.csharpNamespace_ = "";
            this.bitField0_ &= -16385;
            this.swiftPrefix_ = "";
            this.bitField0_ &= -32769;
            this.phpClassPrefix_ = "";
            this.bitField0_ &= -65537;
            this.phpNamespace_ = "";
            this.bitField0_ &= -131073;
            if (this.uninterpretedOptionBuilder_ == null) {
                this.uninterpretedOption_ = Collections.emptyList();
                this.bitField0_ &= -262145;
            } else {
                this.uninterpretedOptionBuilder_.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return DescriptorProtos.access$13800();
        }

        public DescriptorProtos$FileOptions getDefaultInstanceForType() {
            return DescriptorProtos$FileOptions.getDefaultInstance();
        }

        public DescriptorProtos$FileOptions build() {
            Object buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public DescriptorProtos$FileOptions buildPartial() {
            int i = 1;
            DescriptorProtos$FileOptions descriptorProtos$FileOptions = new DescriptorProtos$FileOptions((ExtendableBuilder) this);
            int i2 = this.bitField0_;
            if ((i2 & 1) != 1) {
                i = 0;
            }
            descriptorProtos$FileOptions.javaPackage_ = this.javaPackage_;
            if ((i2 & 2) == 2) {
                i |= 2;
            }
            descriptorProtos$FileOptions.javaOuterClassname_ = this.javaOuterClassname_;
            if ((i2 & 4) == 4) {
                i |= 4;
            }
            descriptorProtos$FileOptions.javaMultipleFiles_ = this.javaMultipleFiles_;
            if ((i2 & 8) == 8) {
                i |= 8;
            }
            descriptorProtos$FileOptions.javaGenerateEqualsAndHash_ = this.javaGenerateEqualsAndHash_;
            if ((i2 & 16) == 16) {
                i |= 16;
            }
            descriptorProtos$FileOptions.javaStringCheckUtf8_ = this.javaStringCheckUtf8_;
            if ((i2 & 32) == 32) {
                i |= 32;
            }
            descriptorProtos$FileOptions.optimizeFor_ = this.optimizeFor_;
            if ((i2 & 64) == 64) {
                i |= 64;
            }
            descriptorProtos$FileOptions.goPackage_ = this.goPackage_;
            if ((i2 & 128) == 128) {
                i |= 128;
            }
            descriptorProtos$FileOptions.ccGenericServices_ = this.ccGenericServices_;
            if ((i2 & 256) == 256) {
                i |= 256;
            }
            descriptorProtos$FileOptions.javaGenericServices_ = this.javaGenericServices_;
            if ((i2 & 512) == 512) {
                i |= 512;
            }
            descriptorProtos$FileOptions.pyGenericServices_ = this.pyGenericServices_;
            if ((i2 & 1024) == 1024) {
                i |= 1024;
            }
            descriptorProtos$FileOptions.phpGenericServices_ = this.phpGenericServices_;
            if ((i2 & 2048) == 2048) {
                i |= 2048;
            }
            descriptorProtos$FileOptions.deprecated_ = this.deprecated_;
            if ((i2 & 4096) == 4096) {
                i |= 4096;
            }
            descriptorProtos$FileOptions.ccEnableArenas_ = this.ccEnableArenas_;
            if ((i2 & 8192) == 8192) {
                i |= 8192;
            }
            descriptorProtos$FileOptions.objcClassPrefix_ = this.objcClassPrefix_;
            if ((i2 & 16384) == 16384) {
                i |= 16384;
            }
            descriptorProtos$FileOptions.csharpNamespace_ = this.csharpNamespace_;
            if ((i2 & 32768) == 32768) {
                i |= 32768;
            }
            descriptorProtos$FileOptions.swiftPrefix_ = this.swiftPrefix_;
            if ((i2 & 65536) == 65536) {
                i |= 65536;
            }
            descriptorProtos$FileOptions.phpClassPrefix_ = this.phpClassPrefix_;
            if ((i2 & 131072) == 131072) {
                i |= 131072;
            }
            descriptorProtos$FileOptions.phpNamespace_ = this.phpNamespace_;
            if (this.uninterpretedOptionBuilder_ == null) {
                if ((this.bitField0_ & 262144) == 262144) {
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    this.bitField0_ &= -262145;
                }
                descriptorProtos$FileOptions.uninterpretedOption_ = this.uninterpretedOption_;
            } else {
                descriptorProtos$FileOptions.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
            }
            descriptorProtos$FileOptions.bitField0_ = i;
            onBuilt();
            return descriptorProtos$FileOptions;
        }

        public Builder clone() {
            return (Builder) super.clone();
        }

        public Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        public Builder clearField(FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        public Builder clearOneof(OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        public Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        public Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        public <Type> Builder setExtension(GeneratedExtension<DescriptorProtos$FileOptions, Type> generatedExtension, Type type) {
            return (Builder) super.setExtension(generatedExtension, type);
        }

        public <Type> Builder setExtension(GeneratedExtension<DescriptorProtos$FileOptions, List<Type>> generatedExtension, int i, Type type) {
            return (Builder) super.setExtension(generatedExtension, i, type);
        }

        public <Type> Builder addExtension(GeneratedExtension<DescriptorProtos$FileOptions, List<Type>> generatedExtension, Type type) {
            return (Builder) super.addExtension(generatedExtension, type);
        }

        public <Type> Builder clearExtension(GeneratedExtension<DescriptorProtos$FileOptions, ?> generatedExtension) {
            return (Builder) super.clearExtension(generatedExtension);
        }

        public Builder mergeFrom(Message message) {
            if (message instanceof DescriptorProtos$FileOptions) {
                return mergeFrom((DescriptorProtos$FileOptions) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DescriptorProtos$FileOptions descriptorProtos$FileOptions) {
            RepeatedFieldBuilderV3 repeatedFieldBuilderV3 = null;
            if (descriptorProtos$FileOptions != DescriptorProtos$FileOptions.getDefaultInstance()) {
                if (descriptorProtos$FileOptions.hasJavaPackage()) {
                    this.bitField0_ |= 1;
                    this.javaPackage_ = descriptorProtos$FileOptions.javaPackage_;
                    onChanged();
                }
                if (descriptorProtos$FileOptions.hasJavaOuterClassname()) {
                    this.bitField0_ |= 2;
                    this.javaOuterClassname_ = descriptorProtos$FileOptions.javaOuterClassname_;
                    onChanged();
                }
                if (descriptorProtos$FileOptions.hasJavaMultipleFiles()) {
                    setJavaMultipleFiles(descriptorProtos$FileOptions.getJavaMultipleFiles());
                }
                if (descriptorProtos$FileOptions.hasJavaGenerateEqualsAndHash()) {
                    setJavaGenerateEqualsAndHash(descriptorProtos$FileOptions.getJavaGenerateEqualsAndHash());
                }
                if (descriptorProtos$FileOptions.hasJavaStringCheckUtf8()) {
                    setJavaStringCheckUtf8(descriptorProtos$FileOptions.getJavaStringCheckUtf8());
                }
                if (descriptorProtos$FileOptions.hasOptimizeFor()) {
                    setOptimizeFor(descriptorProtos$FileOptions.getOptimizeFor());
                }
                if (descriptorProtos$FileOptions.hasGoPackage()) {
                    this.bitField0_ |= 64;
                    this.goPackage_ = descriptorProtos$FileOptions.goPackage_;
                    onChanged();
                }
                if (descriptorProtos$FileOptions.hasCcGenericServices()) {
                    setCcGenericServices(descriptorProtos$FileOptions.getCcGenericServices());
                }
                if (descriptorProtos$FileOptions.hasJavaGenericServices()) {
                    setJavaGenericServices(descriptorProtos$FileOptions.getJavaGenericServices());
                }
                if (descriptorProtos$FileOptions.hasPyGenericServices()) {
                    setPyGenericServices(descriptorProtos$FileOptions.getPyGenericServices());
                }
                if (descriptorProtos$FileOptions.hasPhpGenericServices()) {
                    setPhpGenericServices(descriptorProtos$FileOptions.getPhpGenericServices());
                }
                if (descriptorProtos$FileOptions.hasDeprecated()) {
                    setDeprecated(descriptorProtos$FileOptions.getDeprecated());
                }
                if (descriptorProtos$FileOptions.hasCcEnableArenas()) {
                    setCcEnableArenas(descriptorProtos$FileOptions.getCcEnableArenas());
                }
                if (descriptorProtos$FileOptions.hasObjcClassPrefix()) {
                    this.bitField0_ |= 8192;
                    this.objcClassPrefix_ = descriptorProtos$FileOptions.objcClassPrefix_;
                    onChanged();
                }
                if (descriptorProtos$FileOptions.hasCsharpNamespace()) {
                    this.bitField0_ |= 16384;
                    this.csharpNamespace_ = descriptorProtos$FileOptions.csharpNamespace_;
                    onChanged();
                }
                if (descriptorProtos$FileOptions.hasSwiftPrefix()) {
                    this.bitField0_ |= 32768;
                    this.swiftPrefix_ = descriptorProtos$FileOptions.swiftPrefix_;
                    onChanged();
                }
                if (descriptorProtos$FileOptions.hasPhpClassPrefix()) {
                    this.bitField0_ |= 65536;
                    this.phpClassPrefix_ = descriptorProtos$FileOptions.phpClassPrefix_;
                    onChanged();
                }
                if (descriptorProtos$FileOptions.hasPhpNamespace()) {
                    this.bitField0_ |= 131072;
                    this.phpNamespace_ = descriptorProtos$FileOptions.phpNamespace_;
                    onChanged();
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!descriptorProtos$FileOptions.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = descriptorProtos$FileOptions.uninterpretedOption_;
                            this.bitField0_ &= -262145;
                        } else {
                            ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(descriptorProtos$FileOptions.uninterpretedOption_);
                        }
                        onChanged();
                    }
                } else if (!descriptorProtos$FileOptions.uninterpretedOption_.isEmpty()) {
                    if (this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = descriptorProtos$FileOptions.uninterpretedOption_;
                        this.bitField0_ &= -262145;
                        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getUninterpretedOptionFieldBuilder();
                        }
                        this.uninterpretedOptionBuilder_ = repeatedFieldBuilderV3;
                    } else {
                        this.uninterpretedOptionBuilder_.addAllMessages(descriptorProtos$FileOptions.uninterpretedOption_);
                    }
                }
                mergeExtensionFields(descriptorProtos$FileOptions);
                mergeUnknownFields(descriptorProtos$FileOptions.unknownFields);
                onChanged();
            }
            return this;
        }

        public final boolean isInitialized() {
            for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                if (!getUninterpretedOption(i).isInitialized()) {
                    return false;
                }
            }
            if (extensionsAreInitialized()) {
                return true;
            }
            return false;
        }

        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Throwable th;
            DescriptorProtos$FileOptions descriptorProtos$FileOptions;
            DescriptorProtos$FileOptions descriptorProtos$FileOptions2;
            try {
                descriptorProtos$FileOptions2 = (DescriptorProtos$FileOptions) DescriptorProtos$FileOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                if (descriptorProtos$FileOptions2 != null) {
                    mergeFrom(descriptorProtos$FileOptions2);
                }
                return this;
            } catch (InvalidProtocolBufferException e) {
                InvalidProtocolBufferException invalidProtocolBufferException = e;
                descriptorProtos$FileOptions2 = (DescriptorProtos$FileOptions) invalidProtocolBufferException.getUnfinishedMessage();
                throw invalidProtocolBufferException.unwrapIOException();
            } catch (Throwable th2) {
                Throwable th3 = th2;
                descriptorProtos$FileOptions = descriptorProtos$FileOptions2;
                th = th3;
                if (descriptorProtos$FileOptions != null) {
                    mergeFrom(descriptorProtos$FileOptions);
                }
                throw th;
            }
        }

        public boolean hasJavaPackage() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getJavaPackage() {
            Object obj = this.javaPackage_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.javaPackage_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getJavaPackageBytes() {
            Object obj = this.javaPackage_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.javaPackage_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setJavaPackage(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 1;
            this.javaPackage_ = str;
            onChanged();
            return this;
        }

        public Builder clearJavaPackage() {
            this.bitField0_ &= -2;
            this.javaPackage_ = DescriptorProtos$FileOptions.getDefaultInstance().getJavaPackage();
            onChanged();
            return this;
        }

        public Builder setJavaPackageBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 1;
            this.javaPackage_ = byteString;
            onChanged();
            return this;
        }

        public boolean hasJavaOuterClassname() {
            return (this.bitField0_ & 2) == 2;
        }

        public String getJavaOuterClassname() {
            Object obj = this.javaOuterClassname_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.javaOuterClassname_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getJavaOuterClassnameBytes() {
            Object obj = this.javaOuterClassname_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.javaOuterClassname_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setJavaOuterClassname(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 2;
            this.javaOuterClassname_ = str;
            onChanged();
            return this;
        }

        public Builder clearJavaOuterClassname() {
            this.bitField0_ &= -3;
            this.javaOuterClassname_ = DescriptorProtos$FileOptions.getDefaultInstance().getJavaOuterClassname();
            onChanged();
            return this;
        }

        public Builder setJavaOuterClassnameBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 2;
            this.javaOuterClassname_ = byteString;
            onChanged();
            return this;
        }

        public boolean hasJavaMultipleFiles() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean getJavaMultipleFiles() {
            return this.javaMultipleFiles_;
        }

        public Builder setJavaMultipleFiles(boolean z) {
            this.bitField0_ |= 4;
            this.javaMultipleFiles_ = z;
            onChanged();
            return this;
        }

        public Builder clearJavaMultipleFiles() {
            this.bitField0_ &= -5;
            this.javaMultipleFiles_ = false;
            onChanged();
            return this;
        }

        @Deprecated
        public boolean hasJavaGenerateEqualsAndHash() {
            return (this.bitField0_ & 8) == 8;
        }

        @Deprecated
        public boolean getJavaGenerateEqualsAndHash() {
            return this.javaGenerateEqualsAndHash_;
        }

        @Deprecated
        public Builder setJavaGenerateEqualsAndHash(boolean z) {
            this.bitField0_ |= 8;
            this.javaGenerateEqualsAndHash_ = z;
            onChanged();
            return this;
        }

        @Deprecated
        public Builder clearJavaGenerateEqualsAndHash() {
            this.bitField0_ &= -9;
            this.javaGenerateEqualsAndHash_ = false;
            onChanged();
            return this;
        }

        public boolean hasJavaStringCheckUtf8() {
            return (this.bitField0_ & 16) == 16;
        }

        public boolean getJavaStringCheckUtf8() {
            return this.javaStringCheckUtf8_;
        }

        public Builder setJavaStringCheckUtf8(boolean z) {
            this.bitField0_ |= 16;
            this.javaStringCheckUtf8_ = z;
            onChanged();
            return this;
        }

        public Builder clearJavaStringCheckUtf8() {
            this.bitField0_ &= -17;
            this.javaStringCheckUtf8_ = false;
            onChanged();
            return this;
        }

        public boolean hasOptimizeFor() {
            return (this.bitField0_ & 32) == 32;
        }

        public OptimizeMode getOptimizeFor() {
            OptimizeMode valueOf = OptimizeMode.valueOf(this.optimizeFor_);
            return valueOf == null ? OptimizeMode.SPEED : valueOf;
        }

        public Builder setOptimizeFor(OptimizeMode optimizeMode) {
            if (optimizeMode == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 32;
            this.optimizeFor_ = optimizeMode.getNumber();
            onChanged();
            return this;
        }

        public Builder clearOptimizeFor() {
            this.bitField0_ &= -33;
            this.optimizeFor_ = 1;
            onChanged();
            return this;
        }

        public boolean hasGoPackage() {
            return (this.bitField0_ & 64) == 64;
        }

        public String getGoPackage() {
            Object obj = this.goPackage_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.goPackage_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getGoPackageBytes() {
            Object obj = this.goPackage_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.goPackage_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setGoPackage(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 64;
            this.goPackage_ = str;
            onChanged();
            return this;
        }

        public Builder clearGoPackage() {
            this.bitField0_ &= -65;
            this.goPackage_ = DescriptorProtos$FileOptions.getDefaultInstance().getGoPackage();
            onChanged();
            return this;
        }

        public Builder setGoPackageBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 64;
            this.goPackage_ = byteString;
            onChanged();
            return this;
        }

        public boolean hasCcGenericServices() {
            return (this.bitField0_ & 128) == 128;
        }

        public boolean getCcGenericServices() {
            return this.ccGenericServices_;
        }

        public Builder setCcGenericServices(boolean z) {
            this.bitField0_ |= 128;
            this.ccGenericServices_ = z;
            onChanged();
            return this;
        }

        public Builder clearCcGenericServices() {
            this.bitField0_ &= -129;
            this.ccGenericServices_ = false;
            onChanged();
            return this;
        }

        public boolean hasJavaGenericServices() {
            return (this.bitField0_ & 256) == 256;
        }

        public boolean getJavaGenericServices() {
            return this.javaGenericServices_;
        }

        public Builder setJavaGenericServices(boolean z) {
            this.bitField0_ |= 256;
            this.javaGenericServices_ = z;
            onChanged();
            return this;
        }

        public Builder clearJavaGenericServices() {
            this.bitField0_ &= -257;
            this.javaGenericServices_ = false;
            onChanged();
            return this;
        }

        public boolean hasPyGenericServices() {
            return (this.bitField0_ & 512) == 512;
        }

        public boolean getPyGenericServices() {
            return this.pyGenericServices_;
        }

        public Builder setPyGenericServices(boolean z) {
            this.bitField0_ |= 512;
            this.pyGenericServices_ = z;
            onChanged();
            return this;
        }

        public Builder clearPyGenericServices() {
            this.bitField0_ &= -513;
            this.pyGenericServices_ = false;
            onChanged();
            return this;
        }

        public boolean hasPhpGenericServices() {
            return (this.bitField0_ & 1024) == 1024;
        }

        public boolean getPhpGenericServices() {
            return this.phpGenericServices_;
        }

        public Builder setPhpGenericServices(boolean z) {
            this.bitField0_ |= 1024;
            this.phpGenericServices_ = z;
            onChanged();
            return this;
        }

        public Builder clearPhpGenericServices() {
            this.bitField0_ &= -1025;
            this.phpGenericServices_ = false;
            onChanged();
            return this;
        }

        public boolean hasDeprecated() {
            return (this.bitField0_ & 2048) == 2048;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        public Builder setDeprecated(boolean z) {
            this.bitField0_ |= 2048;
            this.deprecated_ = z;
            onChanged();
            return this;
        }

        public Builder clearDeprecated() {
            this.bitField0_ &= -2049;
            this.deprecated_ = false;
            onChanged();
            return this;
        }

        public boolean hasCcEnableArenas() {
            return (this.bitField0_ & 4096) == 4096;
        }

        public boolean getCcEnableArenas() {
            return this.ccEnableArenas_;
        }

        public Builder setCcEnableArenas(boolean z) {
            this.bitField0_ |= 4096;
            this.ccEnableArenas_ = z;
            onChanged();
            return this;
        }

        public Builder clearCcEnableArenas() {
            this.bitField0_ &= -4097;
            this.ccEnableArenas_ = false;
            onChanged();
            return this;
        }

        public boolean hasObjcClassPrefix() {
            return (this.bitField0_ & 8192) == 8192;
        }

        public String getObjcClassPrefix() {
            Object obj = this.objcClassPrefix_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.objcClassPrefix_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getObjcClassPrefixBytes() {
            Object obj = this.objcClassPrefix_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.objcClassPrefix_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setObjcClassPrefix(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 8192;
            this.objcClassPrefix_ = str;
            onChanged();
            return this;
        }

        public Builder clearObjcClassPrefix() {
            this.bitField0_ &= -8193;
            this.objcClassPrefix_ = DescriptorProtos$FileOptions.getDefaultInstance().getObjcClassPrefix();
            onChanged();
            return this;
        }

        public Builder setObjcClassPrefixBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 8192;
            this.objcClassPrefix_ = byteString;
            onChanged();
            return this;
        }

        public boolean hasCsharpNamespace() {
            return (this.bitField0_ & 16384) == 16384;
        }

        public String getCsharpNamespace() {
            Object obj = this.csharpNamespace_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.csharpNamespace_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getCsharpNamespaceBytes() {
            Object obj = this.csharpNamespace_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.csharpNamespace_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setCsharpNamespace(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 16384;
            this.csharpNamespace_ = str;
            onChanged();
            return this;
        }

        public Builder clearCsharpNamespace() {
            this.bitField0_ &= -16385;
            this.csharpNamespace_ = DescriptorProtos$FileOptions.getDefaultInstance().getCsharpNamespace();
            onChanged();
            return this;
        }

        public Builder setCsharpNamespaceBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 16384;
            this.csharpNamespace_ = byteString;
            onChanged();
            return this;
        }

        public boolean hasSwiftPrefix() {
            return (this.bitField0_ & 32768) == 32768;
        }

        public String getSwiftPrefix() {
            Object obj = this.swiftPrefix_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.swiftPrefix_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getSwiftPrefixBytes() {
            Object obj = this.swiftPrefix_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.swiftPrefix_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setSwiftPrefix(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 32768;
            this.swiftPrefix_ = str;
            onChanged();
            return this;
        }

        public Builder clearSwiftPrefix() {
            this.bitField0_ &= -32769;
            this.swiftPrefix_ = DescriptorProtos$FileOptions.getDefaultInstance().getSwiftPrefix();
            onChanged();
            return this;
        }

        public Builder setSwiftPrefixBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 32768;
            this.swiftPrefix_ = byteString;
            onChanged();
            return this;
        }

        public boolean hasPhpClassPrefix() {
            return (this.bitField0_ & 65536) == 65536;
        }

        public String getPhpClassPrefix() {
            Object obj = this.phpClassPrefix_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.phpClassPrefix_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getPhpClassPrefixBytes() {
            Object obj = this.phpClassPrefix_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.phpClassPrefix_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setPhpClassPrefix(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 65536;
            this.phpClassPrefix_ = str;
            onChanged();
            return this;
        }

        public Builder clearPhpClassPrefix() {
            this.bitField0_ &= -65537;
            this.phpClassPrefix_ = DescriptorProtos$FileOptions.getDefaultInstance().getPhpClassPrefix();
            onChanged();
            return this;
        }

        public Builder setPhpClassPrefixBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 65536;
            this.phpClassPrefix_ = byteString;
            onChanged();
            return this;
        }

        public boolean hasPhpNamespace() {
            return (this.bitField0_ & 131072) == 131072;
        }

        public String getPhpNamespace() {
            Object obj = this.phpNamespace_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.phpNamespace_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getPhpNamespaceBytes() {
            Object obj = this.phpNamespace_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.phpNamespace_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setPhpNamespace(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 131072;
            this.phpNamespace_ = str;
            onChanged();
            return this;
        }

        public Builder clearPhpNamespace() {
            this.bitField0_ &= -131073;
            this.phpNamespace_ = DescriptorProtos$FileOptions.getDefaultInstance().getPhpNamespace();
            onChanged();
            return this;
        }

        public Builder setPhpNamespaceBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.bitField0_ |= 131072;
            this.phpNamespace_ = byteString;
            onChanged();
            return this;
        }

        private void ensureUninterpretedOptionIsMutable() {
            if ((this.bitField0_ & 262144) != 262144) {
                this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                this.bitField0_ |= 262144;
            }
        }

        public List<DescriptorProtos$UninterpretedOption> getUninterpretedOptionList() {
            if (this.uninterpretedOptionBuilder_ == null) {
                return Collections.unmodifiableList(this.uninterpretedOption_);
            }
            return this.uninterpretedOptionBuilder_.getMessageList();
        }

        public int getUninterpretedOptionCount() {
            if (this.uninterpretedOptionBuilder_ == null) {
                return this.uninterpretedOption_.size();
            }
            return this.uninterpretedOptionBuilder_.getCount();
        }

        public DescriptorProtos$UninterpretedOption getUninterpretedOption(int i) {
            if (this.uninterpretedOptionBuilder_ == null) {
                return (DescriptorProtos$UninterpretedOption) this.uninterpretedOption_.get(i);
            }
            return (DescriptorProtos$UninterpretedOption) this.uninterpretedOptionBuilder_.getMessage(i);
        }

        public Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
            if (this.uninterpretedOptionBuilder_ != null) {
                this.uninterpretedOptionBuilder_.setMessage(i, descriptorProtos$UninterpretedOption);
            } else if (descriptorProtos$UninterpretedOption == null) {
                throw new NullPointerException();
            } else {
                ensureUninterpretedOptionIsMutable();
                this.uninterpretedOption_.set(i, descriptorProtos$UninterpretedOption);
                onChanged();
            }
            return this;
        }

        public Builder setUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
            if (this.uninterpretedOptionBuilder_ == null) {
                ensureUninterpretedOptionIsMutable();
                this.uninterpretedOption_.set(i, descriptorProtos$UninterpretedOption$Builder.build());
                onChanged();
            } else {
                this.uninterpretedOptionBuilder_.setMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
            }
            return this;
        }

        public Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
            if (this.uninterpretedOptionBuilder_ != null) {
                this.uninterpretedOptionBuilder_.addMessage(descriptorProtos$UninterpretedOption);
            } else if (descriptorProtos$UninterpretedOption == null) {
                throw new NullPointerException();
            } else {
                ensureUninterpretedOptionIsMutable();
                this.uninterpretedOption_.add(descriptorProtos$UninterpretedOption);
                onChanged();
            }
            return this;
        }

        public Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption descriptorProtos$UninterpretedOption) {
            if (this.uninterpretedOptionBuilder_ != null) {
                this.uninterpretedOptionBuilder_.addMessage(i, descriptorProtos$UninterpretedOption);
            } else if (descriptorProtos$UninterpretedOption == null) {
                throw new NullPointerException();
            } else {
                ensureUninterpretedOptionIsMutable();
                this.uninterpretedOption_.add(i, descriptorProtos$UninterpretedOption);
                onChanged();
            }
            return this;
        }

        public Builder addUninterpretedOption(DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
            if (this.uninterpretedOptionBuilder_ == null) {
                ensureUninterpretedOptionIsMutable();
                this.uninterpretedOption_.add(descriptorProtos$UninterpretedOption$Builder.build());
                onChanged();
            } else {
                this.uninterpretedOptionBuilder_.addMessage(descriptorProtos$UninterpretedOption$Builder.build());
            }
            return this;
        }

        public Builder addUninterpretedOption(int i, DescriptorProtos$UninterpretedOption$Builder descriptorProtos$UninterpretedOption$Builder) {
            if (this.uninterpretedOptionBuilder_ == null) {
                ensureUninterpretedOptionIsMutable();
                this.uninterpretedOption_.add(i, descriptorProtos$UninterpretedOption$Builder.build());
                onChanged();
            } else {
                this.uninterpretedOptionBuilder_.addMessage(i, descriptorProtos$UninterpretedOption$Builder.build());
            }
            return this;
        }

        public Builder addAllUninterpretedOption(Iterable<? extends DescriptorProtos$UninterpretedOption> iterable) {
            if (this.uninterpretedOptionBuilder_ == null) {
                ensureUninterpretedOptionIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll((Iterable) iterable, this.uninterpretedOption_);
                onChanged();
            } else {
                this.uninterpretedOptionBuilder_.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearUninterpretedOption() {
            if (this.uninterpretedOptionBuilder_ == null) {
                this.uninterpretedOption_ = Collections.emptyList();
                this.bitField0_ &= -262145;
                onChanged();
            } else {
                this.uninterpretedOptionBuilder_.clear();
            }
            return this;
        }

        public Builder removeUninterpretedOption(int i) {
            if (this.uninterpretedOptionBuilder_ == null) {
                ensureUninterpretedOptionIsMutable();
                this.uninterpretedOption_.remove(i);
                onChanged();
            } else {
                this.uninterpretedOptionBuilder_.remove(i);
            }
            return this;
        }

        public DescriptorProtos$UninterpretedOption$Builder getUninterpretedOptionBuilder(int i) {
            return (DescriptorProtos$UninterpretedOption$Builder) getUninterpretedOptionFieldBuilder().getBuilder(i);
        }

        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
            if (this.uninterpretedOptionBuilder_ == null) {
                return (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i);
            }
            return (UninterpretedOptionOrBuilder) this.uninterpretedOptionBuilder_.getMessageOrBuilder(i);
        }

        public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
            if (this.uninterpretedOptionBuilder_ != null) {
                return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.uninterpretedOption_);
        }

        public DescriptorProtos$UninterpretedOption$Builder addUninterpretedOptionBuilder() {
            return (DescriptorProtos$UninterpretedOption$Builder) getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos$UninterpretedOption.getDefaultInstance());
        }

        public DescriptorProtos$UninterpretedOption$Builder addUninterpretedOptionBuilder(int i) {
            return (DescriptorProtos$UninterpretedOption$Builder) getUninterpretedOptionFieldBuilder().addBuilder(i, DescriptorProtos$UninterpretedOption.getDefaultInstance());
        }

        public List<DescriptorProtos$UninterpretedOption$Builder> getUninterpretedOptionBuilderList() {
            return getUninterpretedOptionFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<DescriptorProtos$UninterpretedOption, DescriptorProtos$UninterpretedOption$Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
            if (this.uninterpretedOptionBuilder_ == null) {
                this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilderV3(this.uninterpretedOption_, (this.bitField0_ & 262144) == 262144, getParentForChildren(), isClean());
                this.uninterpretedOption_ = null;
            }
            return this.uninterpretedOptionBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public enum OptimizeMode implements ProtocolMessageEnum {
        SPEED(1),
        CODE_SIZE(2),
        LITE_RUNTIME(3);
        
        public static final int CODE_SIZE_VALUE = 2;
        public static final int LITE_RUNTIME_VALUE = 3;
        public static final int SPEED_VALUE = 1;
        private static final OptimizeMode[] VALUES = null;
        private static final EnumLiteMap<OptimizeMode> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new DescriptorProtos$FileOptions$OptimizeMode$1();
            VALUES = values();
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static OptimizeMode valueOf(int i) {
            return forNumber(i);
        }

        public static OptimizeMode forNumber(int i) {
            switch (i) {
                case 1:
                    return SPEED;
                case 2:
                    return CODE_SIZE;
                case 3:
                    return LITE_RUNTIME;
                default:
                    return null;
            }
        }

        public static EnumLiteMap<OptimizeMode> internalGetValueMap() {
            return internalValueMap;
        }

        public final EnumValueDescriptor getValueDescriptor() {
            return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final EnumDescriptor getDescriptor() {
            return (EnumDescriptor) DescriptorProtos$FileOptions.getDescriptor().getEnumTypes().get(0);
        }

        public static OptimizeMode valueOf(EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        private OptimizeMode(int i) {
            this.value = i;
        }
    }

    private DescriptorProtos$FileOptions(ExtendableBuilder<DescriptorProtos$FileOptions, ?> extendableBuilder) {
        super(extendableBuilder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$FileOptions() {
        this.memoizedIsInitialized = (byte) -1;
        this.javaPackage_ = "";
        this.javaOuterClassname_ = "";
        this.javaMultipleFiles_ = false;
        this.javaGenerateEqualsAndHash_ = false;
        this.javaStringCheckUtf8_ = false;
        this.optimizeFor_ = 1;
        this.goPackage_ = "";
        this.ccGenericServices_ = false;
        this.javaGenericServices_ = false;
        this.pyGenericServices_ = false;
        this.phpGenericServices_ = false;
        this.deprecated_ = false;
        this.ccEnableArenas_ = false;
        this.objcClassPrefix_ = "";
        this.csharpNamespace_ = "";
        this.swiftPrefix_ = "";
        this.phpClassPrefix_ = "";
        this.phpNamespace_ = "";
        this.uninterpretedOption_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DescriptorProtos$FileOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Object obj = null;
        this();
        com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        int i = 0;
        while (obj == null) {
            try {
                int readTag = codedInputStream.readTag();
                ByteString readBytes;
                switch (readTag) {
                    case 0:
                        obj = 1;
                        break;
                    case 10:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 1;
                        this.javaPackage_ = readBytes;
                        break;
                    case 66:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 2;
                        this.javaOuterClassname_ = readBytes;
                        break;
                    case 72:
                        readTag = codedInputStream.readEnum();
                        if (OptimizeMode.valueOf(readTag) != null) {
                            this.bitField0_ |= 32;
                            this.optimizeFor_ = readTag;
                            break;
                        }
                        newBuilder.mergeVarintField(9, readTag);
                        break;
                    case 80:
                        this.bitField0_ |= 4;
                        this.javaMultipleFiles_ = codedInputStream.readBool();
                        break;
                    case 90:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 64;
                        this.goPackage_ = readBytes;
                        break;
                    case 128:
                        this.bitField0_ |= 128;
                        this.ccGenericServices_ = codedInputStream.readBool();
                        break;
                    case 136:
                        this.bitField0_ |= 256;
                        this.javaGenericServices_ = codedInputStream.readBool();
                        break;
                    case 144:
                        this.bitField0_ |= 512;
                        this.pyGenericServices_ = codedInputStream.readBool();
                        break;
                    case 152:
                        this.bitField0_ |= 1024;
                        this.phpGenericServices_ = codedInputStream.readBool();
                        break;
                    case 160:
                        this.bitField0_ |= 8;
                        this.javaGenerateEqualsAndHash_ = codedInputStream.readBool();
                        break;
                    case 184:
                        this.bitField0_ |= 2048;
                        this.deprecated_ = codedInputStream.readBool();
                        break;
                    case Opcodes.ADD_INT_LIT8 /*216*/:
                        this.bitField0_ |= 16;
                        this.javaStringCheckUtf8_ = codedInputStream.readBool();
                        break;
                    case 248:
                        this.bitField0_ |= 4096;
                        this.ccEnableArenas_ = codedInputStream.readBool();
                        break;
                    case 290:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 8192;
                        this.objcClassPrefix_ = readBytes;
                        break;
                    case 298:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 16384;
                        this.csharpNamespace_ = readBytes;
                        break;
                    case 314:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 32768;
                        this.swiftPrefix_ = readBytes;
                        break;
                    case 322:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 65536;
                        this.phpClassPrefix_ = readBytes;
                        break;
                    case 330:
                        readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 131072;
                        this.phpNamespace_ = readBytes;
                        break;
                    case 7994:
                        if ((i & 262144) != 262144) {
                            this.uninterpretedOption_ = new ArrayList();
                            i |= 262144;
                        }
                        this.uninterpretedOption_.add(codedInputStream.readMessage(DescriptorProtos$UninterpretedOption.PARSER, extensionRegistryLite));
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
                if ((i & 262144) == 262144) {
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        if ((i & 262144) == 262144) {
            this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
        }
        this.unknownFields = newBuilder.build();
        makeExtensionsImmutable();
    }

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.access$13800();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$13900().ensureFieldAccessorsInitialized(DescriptorProtos$FileOptions.class, Builder.class);
    }

    public boolean hasJavaPackage() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getJavaPackage() {
        Object obj = this.javaPackage_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.javaPackage_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getJavaPackageBytes() {
        Object obj = this.javaPackage_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.javaPackage_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasJavaOuterClassname() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getJavaOuterClassname() {
        Object obj = this.javaOuterClassname_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.javaOuterClassname_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getJavaOuterClassnameBytes() {
        Object obj = this.javaOuterClassname_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.javaOuterClassname_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasJavaMultipleFiles() {
        return (this.bitField0_ & 4) == 4;
    }

    public boolean getJavaMultipleFiles() {
        return this.javaMultipleFiles_;
    }

    @Deprecated
    public boolean hasJavaGenerateEqualsAndHash() {
        return (this.bitField0_ & 8) == 8;
    }

    @Deprecated
    public boolean getJavaGenerateEqualsAndHash() {
        return this.javaGenerateEqualsAndHash_;
    }

    public boolean hasJavaStringCheckUtf8() {
        return (this.bitField0_ & 16) == 16;
    }

    public boolean getJavaStringCheckUtf8() {
        return this.javaStringCheckUtf8_;
    }

    public boolean hasOptimizeFor() {
        return (this.bitField0_ & 32) == 32;
    }

    public OptimizeMode getOptimizeFor() {
        OptimizeMode valueOf = OptimizeMode.valueOf(this.optimizeFor_);
        return valueOf == null ? OptimizeMode.SPEED : valueOf;
    }

    public boolean hasGoPackage() {
        return (this.bitField0_ & 64) == 64;
    }

    public String getGoPackage() {
        Object obj = this.goPackage_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.goPackage_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getGoPackageBytes() {
        Object obj = this.goPackage_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.goPackage_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasCcGenericServices() {
        return (this.bitField0_ & 128) == 128;
    }

    public boolean getCcGenericServices() {
        return this.ccGenericServices_;
    }

    public boolean hasJavaGenericServices() {
        return (this.bitField0_ & 256) == 256;
    }

    public boolean getJavaGenericServices() {
        return this.javaGenericServices_;
    }

    public boolean hasPyGenericServices() {
        return (this.bitField0_ & 512) == 512;
    }

    public boolean getPyGenericServices() {
        return this.pyGenericServices_;
    }

    public boolean hasPhpGenericServices() {
        return (this.bitField0_ & 1024) == 1024;
    }

    public boolean getPhpGenericServices() {
        return this.phpGenericServices_;
    }

    public boolean hasDeprecated() {
        return (this.bitField0_ & 2048) == 2048;
    }

    public boolean getDeprecated() {
        return this.deprecated_;
    }

    public boolean hasCcEnableArenas() {
        return (this.bitField0_ & 4096) == 4096;
    }

    public boolean getCcEnableArenas() {
        return this.ccEnableArenas_;
    }

    public boolean hasObjcClassPrefix() {
        return (this.bitField0_ & 8192) == 8192;
    }

    public String getObjcClassPrefix() {
        Object obj = this.objcClassPrefix_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.objcClassPrefix_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getObjcClassPrefixBytes() {
        Object obj = this.objcClassPrefix_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.objcClassPrefix_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasCsharpNamespace() {
        return (this.bitField0_ & 16384) == 16384;
    }

    public String getCsharpNamespace() {
        Object obj = this.csharpNamespace_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.csharpNamespace_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getCsharpNamespaceBytes() {
        Object obj = this.csharpNamespace_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.csharpNamespace_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasSwiftPrefix() {
        return (this.bitField0_ & 32768) == 32768;
    }

    public String getSwiftPrefix() {
        Object obj = this.swiftPrefix_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.swiftPrefix_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getSwiftPrefixBytes() {
        Object obj = this.swiftPrefix_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.swiftPrefix_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasPhpClassPrefix() {
        return (this.bitField0_ & 65536) == 65536;
    }

    public String getPhpClassPrefix() {
        Object obj = this.phpClassPrefix_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.phpClassPrefix_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getPhpClassPrefixBytes() {
        Object obj = this.phpClassPrefix_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.phpClassPrefix_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasPhpNamespace() {
        return (this.bitField0_ & 131072) == 131072;
    }

    public String getPhpNamespace() {
        Object obj = this.phpNamespace_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.phpNamespace_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getPhpNamespaceBytes() {
        Object obj = this.phpNamespace_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.phpNamespace_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public List<DescriptorProtos$UninterpretedOption> getUninterpretedOptionList() {
        return this.uninterpretedOption_;
    }

    public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
        return this.uninterpretedOption_;
    }

    public int getUninterpretedOptionCount() {
        return this.uninterpretedOption_.size();
    }

    public DescriptorProtos$UninterpretedOption getUninterpretedOption(int i) {
        return (DescriptorProtos$UninterpretedOption) this.uninterpretedOption_.get(i);
    }

    public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
        return (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i);
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == (byte) 1) {
            return true;
        }
        if (b == (byte) 0) {
            return false;
        }
        int i = 0;
        while (i < getUninterpretedOptionCount()) {
            if (getUninterpretedOption(i).isInitialized()) {
                i++;
            } else {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        if (extensionsAreInitialized()) {
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }
        this.memoizedIsInitialized = (byte) 0;
        return false;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        ExtensionWriter newExtensionWriter = newExtensionWriter();
        if ((this.bitField0_ & 1) == 1) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.javaPackage_);
        }
        if ((this.bitField0_ & 2) == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.javaOuterClassname_);
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeEnum(9, this.optimizeFor_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeBool(10, this.javaMultipleFiles_);
        }
        if ((this.bitField0_ & 64) == 64) {
            GeneratedMessageV3.writeString(codedOutputStream, 11, this.goPackage_);
        }
        if ((this.bitField0_ & 128) == 128) {
            codedOutputStream.writeBool(16, this.ccGenericServices_);
        }
        if ((this.bitField0_ & 256) == 256) {
            codedOutputStream.writeBool(17, this.javaGenericServices_);
        }
        if ((this.bitField0_ & 512) == 512) {
            codedOutputStream.writeBool(18, this.pyGenericServices_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            codedOutputStream.writeBool(19, this.phpGenericServices_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeBool(20, this.javaGenerateEqualsAndHash_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            codedOutputStream.writeBool(23, this.deprecated_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeBool(27, this.javaStringCheckUtf8_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            codedOutputStream.writeBool(31, this.ccEnableArenas_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            GeneratedMessageV3.writeString(codedOutputStream, 36, this.objcClassPrefix_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            GeneratedMessageV3.writeString(codedOutputStream, 37, this.csharpNamespace_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            GeneratedMessageV3.writeString(codedOutputStream, 39, this.swiftPrefix_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            GeneratedMessageV3.writeString(codedOutputStream, 40, this.phpClassPrefix_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            GeneratedMessageV3.writeString(codedOutputStream, 41, this.phpNamespace_);
        }
        for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
            codedOutputStream.writeMessage(999, (MessageLite) this.uninterpretedOption_.get(i));
        }
        newExtensionWriter.writeUntil(SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING, codedOutputStream);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = 0;
        int i2 = this.memoizedSize;
        if (i2 != -1) {
            return i2;
        }
        if ((this.bitField0_ & 1) == 1) {
            i2 = GeneratedMessageV3.computeStringSize(1, this.javaPackage_) + 0;
        } else {
            i2 = 0;
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += GeneratedMessageV3.computeStringSize(8, this.javaOuterClassname_);
        }
        if ((this.bitField0_ & 32) == 32) {
            i2 += CodedOutputStream.computeEnumSize(9, this.optimizeFor_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeBoolSize(10, this.javaMultipleFiles_);
        }
        if ((this.bitField0_ & 64) == 64) {
            i2 += GeneratedMessageV3.computeStringSize(11, this.goPackage_);
        }
        if ((this.bitField0_ & 128) == 128) {
            i2 += CodedOutputStream.computeBoolSize(16, this.ccGenericServices_);
        }
        if ((this.bitField0_ & 256) == 256) {
            i2 += CodedOutputStream.computeBoolSize(17, this.javaGenericServices_);
        }
        if ((this.bitField0_ & 512) == 512) {
            i2 += CodedOutputStream.computeBoolSize(18, this.pyGenericServices_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            i2 += CodedOutputStream.computeBoolSize(19, this.phpGenericServices_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeBoolSize(20, this.javaGenerateEqualsAndHash_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            i2 += CodedOutputStream.computeBoolSize(23, this.deprecated_);
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeBoolSize(27, this.javaStringCheckUtf8_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            i2 += CodedOutputStream.computeBoolSize(31, this.ccEnableArenas_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            i2 += GeneratedMessageV3.computeStringSize(36, this.objcClassPrefix_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            i2 += GeneratedMessageV3.computeStringSize(37, this.csharpNamespace_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            i2 += GeneratedMessageV3.computeStringSize(39, this.swiftPrefix_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            i2 += GeneratedMessageV3.computeStringSize(40, this.phpClassPrefix_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            i2 += GeneratedMessageV3.computeStringSize(41, this.phpNamespace_);
        }
        int i3 = i2;
        while (i < this.uninterpretedOption_.size()) {
            i++;
            i3 = CodedOutputStream.computeMessageSize(999, (MessageLite) this.uninterpretedOption_.get(i)) + i3;
        }
        i2 = (extensionsSerializedSize() + i3) + this.unknownFields.getSerializedSize();
        this.memoizedSize = i2;
        return i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DescriptorProtos$FileOptions)) {
            return super.equals(obj);
        }
        DescriptorProtos$FileOptions descriptorProtos$FileOptions = (DescriptorProtos$FileOptions) obj;
        boolean z = hasJavaPackage() == descriptorProtos$FileOptions.hasJavaPackage();
        if (hasJavaPackage()) {
            z = z && getJavaPackage().equals(descriptorProtos$FileOptions.getJavaPackage());
        }
        if (z && hasJavaOuterClassname() == descriptorProtos$FileOptions.hasJavaOuterClassname()) {
            z = true;
        } else {
            z = false;
        }
        if (hasJavaOuterClassname()) {
            if (z && getJavaOuterClassname().equals(descriptorProtos$FileOptions.getJavaOuterClassname())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasJavaMultipleFiles() == descriptorProtos$FileOptions.hasJavaMultipleFiles()) {
            z = true;
        } else {
            z = false;
        }
        if (hasJavaMultipleFiles()) {
            if (z && getJavaMultipleFiles() == descriptorProtos$FileOptions.getJavaMultipleFiles()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasJavaGenerateEqualsAndHash() == descriptorProtos$FileOptions.hasJavaGenerateEqualsAndHash()) {
            z = true;
        } else {
            z = false;
        }
        if (hasJavaGenerateEqualsAndHash()) {
            if (z && getJavaGenerateEqualsAndHash() == descriptorProtos$FileOptions.getJavaGenerateEqualsAndHash()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasJavaStringCheckUtf8() == descriptorProtos$FileOptions.hasJavaStringCheckUtf8()) {
            z = true;
        } else {
            z = false;
        }
        if (hasJavaStringCheckUtf8()) {
            if (z && getJavaStringCheckUtf8() == descriptorProtos$FileOptions.getJavaStringCheckUtf8()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasOptimizeFor() == descriptorProtos$FileOptions.hasOptimizeFor()) {
            z = true;
        } else {
            z = false;
        }
        if (hasOptimizeFor()) {
            if (z && this.optimizeFor_ == descriptorProtos$FileOptions.optimizeFor_) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasGoPackage() == descriptorProtos$FileOptions.hasGoPackage()) {
            z = true;
        } else {
            z = false;
        }
        if (hasGoPackage()) {
            if (z && getGoPackage().equals(descriptorProtos$FileOptions.getGoPackage())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasCcGenericServices() == descriptorProtos$FileOptions.hasCcGenericServices()) {
            z = true;
        } else {
            z = false;
        }
        if (hasCcGenericServices()) {
            if (z && getCcGenericServices() == descriptorProtos$FileOptions.getCcGenericServices()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasJavaGenericServices() == descriptorProtos$FileOptions.hasJavaGenericServices()) {
            z = true;
        } else {
            z = false;
        }
        if (hasJavaGenericServices()) {
            if (z && getJavaGenericServices() == descriptorProtos$FileOptions.getJavaGenericServices()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasPyGenericServices() == descriptorProtos$FileOptions.hasPyGenericServices()) {
            z = true;
        } else {
            z = false;
        }
        if (hasPyGenericServices()) {
            if (z && getPyGenericServices() == descriptorProtos$FileOptions.getPyGenericServices()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasPhpGenericServices() == descriptorProtos$FileOptions.hasPhpGenericServices()) {
            z = true;
        } else {
            z = false;
        }
        if (hasPhpGenericServices()) {
            if (z && getPhpGenericServices() == descriptorProtos$FileOptions.getPhpGenericServices()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasDeprecated() == descriptorProtos$FileOptions.hasDeprecated()) {
            z = true;
        } else {
            z = false;
        }
        if (hasDeprecated()) {
            if (z && getDeprecated() == descriptorProtos$FileOptions.getDeprecated()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasCcEnableArenas() == descriptorProtos$FileOptions.hasCcEnableArenas()) {
            z = true;
        } else {
            z = false;
        }
        if (hasCcEnableArenas()) {
            if (z && getCcEnableArenas() == descriptorProtos$FileOptions.getCcEnableArenas()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasObjcClassPrefix() == descriptorProtos$FileOptions.hasObjcClassPrefix()) {
            z = true;
        } else {
            z = false;
        }
        if (hasObjcClassPrefix()) {
            if (z && getObjcClassPrefix().equals(descriptorProtos$FileOptions.getObjcClassPrefix())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasCsharpNamespace() == descriptorProtos$FileOptions.hasCsharpNamespace()) {
            z = true;
        } else {
            z = false;
        }
        if (hasCsharpNamespace()) {
            if (z && getCsharpNamespace().equals(descriptorProtos$FileOptions.getCsharpNamespace())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasSwiftPrefix() == descriptorProtos$FileOptions.hasSwiftPrefix()) {
            z = true;
        } else {
            z = false;
        }
        if (hasSwiftPrefix()) {
            if (z && getSwiftPrefix().equals(descriptorProtos$FileOptions.getSwiftPrefix())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasPhpClassPrefix() == descriptorProtos$FileOptions.hasPhpClassPrefix()) {
            z = true;
        } else {
            z = false;
        }
        if (hasPhpClassPrefix()) {
            if (z && getPhpClassPrefix().equals(descriptorProtos$FileOptions.getPhpClassPrefix())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasPhpNamespace() == descriptorProtos$FileOptions.hasPhpNamespace()) {
            z = true;
        } else {
            z = false;
        }
        if (hasPhpNamespace()) {
            if (z && getPhpNamespace().equals(descriptorProtos$FileOptions.getPhpNamespace())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && getUninterpretedOptionList().equals(descriptorProtos$FileOptions.getUninterpretedOptionList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.unknownFields.equals(descriptorProtos$FileOptions.unknownFields)) {
            z = true;
        } else {
            z = false;
        }
        if (z && getExtensionFields().equals(descriptorProtos$FileOptions.getExtensionFields())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (hasJavaPackage()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getJavaPackage().hashCode();
        }
        if (hasJavaOuterClassname()) {
            hashCode = (((hashCode * 37) + 8) * 53) + getJavaOuterClassname().hashCode();
        }
        if (hasJavaMultipleFiles()) {
            hashCode = (((hashCode * 37) + 10) * 53) + Internal.hashBoolean(getJavaMultipleFiles());
        }
        if (hasJavaGenerateEqualsAndHash()) {
            hashCode = (((hashCode * 37) + 20) * 53) + Internal.hashBoolean(getJavaGenerateEqualsAndHash());
        }
        if (hasJavaStringCheckUtf8()) {
            hashCode = (((hashCode * 37) + 27) * 53) + Internal.hashBoolean(getJavaStringCheckUtf8());
        }
        if (hasOptimizeFor()) {
            hashCode = (((hashCode * 37) + 9) * 53) + this.optimizeFor_;
        }
        if (hasGoPackage()) {
            hashCode = (((hashCode * 37) + 11) * 53) + getGoPackage().hashCode();
        }
        if (hasCcGenericServices()) {
            hashCode = (((hashCode * 37) + 16) * 53) + Internal.hashBoolean(getCcGenericServices());
        }
        if (hasJavaGenericServices()) {
            hashCode = (((hashCode * 37) + 17) * 53) + Internal.hashBoolean(getJavaGenericServices());
        }
        if (hasPyGenericServices()) {
            hashCode = (((hashCode * 37) + 18) * 53) + Internal.hashBoolean(getPyGenericServices());
        }
        if (hasPhpGenericServices()) {
            hashCode = (((hashCode * 37) + 19) * 53) + Internal.hashBoolean(getPhpGenericServices());
        }
        if (hasDeprecated()) {
            hashCode = (((hashCode * 37) + 23) * 53) + Internal.hashBoolean(getDeprecated());
        }
        if (hasCcEnableArenas()) {
            hashCode = (((hashCode * 37) + 31) * 53) + Internal.hashBoolean(getCcEnableArenas());
        }
        if (hasObjcClassPrefix()) {
            hashCode = (((hashCode * 37) + 36) * 53) + getObjcClassPrefix().hashCode();
        }
        if (hasCsharpNamespace()) {
            hashCode = (((hashCode * 37) + 37) * 53) + getCsharpNamespace().hashCode();
        }
        if (hasSwiftPrefix()) {
            hashCode = (((hashCode * 37) + 39) * 53) + getSwiftPrefix().hashCode();
        }
        if (hasPhpClassPrefix()) {
            hashCode = (((hashCode * 37) + 40) * 53) + getPhpClassPrefix().hashCode();
        }
        if (hasPhpNamespace()) {
            hashCode = (((hashCode * 37) + 41) * 53) + getPhpNamespace().hashCode();
        }
        if (getUninterpretedOptionCount() > 0) {
            hashCode = (((hashCode * 37) + 999) * 53) + getUninterpretedOptionList().hashCode();
        }
        hashCode = (hashFields(hashCode, getExtensionFields()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$FileOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileOptions) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$FileOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileOptions) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$FileOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileOptions) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$FileOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$FileOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileOptions) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$FileOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$FileOptions parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$FileOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$FileOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$FileOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$FileOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$FileOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$FileOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$FileOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$FileOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$FileOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$FileOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$FileOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DescriptorProtos$FileOptions descriptorProtos$FileOptions) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$FileOptions);
    }

    public Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    protected Builder newBuilderForType(BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static DescriptorProtos$FileOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$FileOptions> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$FileOptions> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$FileOptions getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
