package com.google.protobuf.compiler;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;

class PluginProtos$CodeGeneratorResponse$1 extends AbstractParser<PluginProtos$CodeGeneratorResponse> {
    PluginProtos$CodeGeneratorResponse$1() {
    }

    public PluginProtos$CodeGeneratorResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return new PluginProtos$CodeGeneratorResponse(codedInputStream, extensionRegistryLite, null);
    }
}
