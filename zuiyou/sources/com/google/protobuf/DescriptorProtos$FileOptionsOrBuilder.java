package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.FileOptions.OptimizeMode;
import com.google.protobuf.DescriptorProtos.UninterpretedOptionOrBuilder;
import com.google.protobuf.GeneratedMessageV3.ExtendableMessageOrBuilder;
import java.util.List;

public interface DescriptorProtos$FileOptionsOrBuilder extends ExtendableMessageOrBuilder<DescriptorProtos$FileOptions> {
    boolean getCcEnableArenas();

    boolean getCcGenericServices();

    String getCsharpNamespace();

    ByteString getCsharpNamespaceBytes();

    boolean getDeprecated();

    String getGoPackage();

    ByteString getGoPackageBytes();

    @Deprecated
    boolean getJavaGenerateEqualsAndHash();

    boolean getJavaGenericServices();

    boolean getJavaMultipleFiles();

    String getJavaOuterClassname();

    ByteString getJavaOuterClassnameBytes();

    String getJavaPackage();

    ByteString getJavaPackageBytes();

    boolean getJavaStringCheckUtf8();

    String getObjcClassPrefix();

    ByteString getObjcClassPrefixBytes();

    OptimizeMode getOptimizeFor();

    String getPhpClassPrefix();

    ByteString getPhpClassPrefixBytes();

    boolean getPhpGenericServices();

    String getPhpNamespace();

    ByteString getPhpNamespaceBytes();

    boolean getPyGenericServices();

    String getSwiftPrefix();

    ByteString getSwiftPrefixBytes();

    DescriptorProtos$UninterpretedOption getUninterpretedOption(int i);

    int getUninterpretedOptionCount();

    List<DescriptorProtos$UninterpretedOption> getUninterpretedOptionList();

    UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i);

    List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList();

    boolean hasCcEnableArenas();

    boolean hasCcGenericServices();

    boolean hasCsharpNamespace();

    boolean hasDeprecated();

    boolean hasGoPackage();

    @Deprecated
    boolean hasJavaGenerateEqualsAndHash();

    boolean hasJavaGenericServices();

    boolean hasJavaMultipleFiles();

    boolean hasJavaOuterClassname();

    boolean hasJavaPackage();

    boolean hasJavaStringCheckUtf8();

    boolean hasObjcClassPrefix();

    boolean hasOptimizeFor();

    boolean hasPhpClassPrefix();

    boolean hasPhpGenericServices();

    boolean hasPhpNamespace();

    boolean hasPyGenericServices();

    boolean hasSwiftPrefix();
}
