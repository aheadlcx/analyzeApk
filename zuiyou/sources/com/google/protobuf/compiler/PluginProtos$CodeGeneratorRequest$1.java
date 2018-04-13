package com.google.protobuf.compiler;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;

class PluginProtos$CodeGeneratorRequest$1 extends AbstractParser<PluginProtos$CodeGeneratorRequest> {
    PluginProtos$CodeGeneratorRequest$1() {
    }

    public PluginProtos$CodeGeneratorRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return new PluginProtos$CodeGeneratorRequest(codedInputStream, extensionRegistryLite, null);
    }
}
