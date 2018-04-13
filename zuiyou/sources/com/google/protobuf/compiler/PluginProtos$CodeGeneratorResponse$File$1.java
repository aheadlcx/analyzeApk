package com.google.protobuf.compiler;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;

class PluginProtos$CodeGeneratorResponse$File$1 extends AbstractParser<File> {
    PluginProtos$CodeGeneratorResponse$File$1() {
    }

    public File parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return new File(codedInputStream, extensionRegistryLite, null);
    }
}
