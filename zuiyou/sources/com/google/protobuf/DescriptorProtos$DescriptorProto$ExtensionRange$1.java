package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.DescriptorProto.ExtensionRange;

class DescriptorProtos$DescriptorProto$ExtensionRange$1 extends AbstractParser<ExtensionRange> {
    DescriptorProtos$DescriptorProto$ExtensionRange$1() {
    }

    public ExtensionRange parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return new ExtensionRange(codedInputStream, extensionRegistryLite, null);
    }
}
