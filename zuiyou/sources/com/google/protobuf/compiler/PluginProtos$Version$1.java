package com.google.protobuf.compiler;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;

class PluginProtos$Version$1 extends AbstractParser<PluginProtos$Version> {
    PluginProtos$Version$1() {
    }

    public PluginProtos$Version parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return new PluginProtos$Version(codedInputStream, extensionRegistryLite, null);
    }
}
