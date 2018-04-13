package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.DescriptorProto.ReservedRange;

class DescriptorProtos$DescriptorProto$ReservedRange$1 extends AbstractParser<ReservedRange> {
    DescriptorProtos$DescriptorProto$ReservedRange$1() {
    }

    public ReservedRange parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return new ReservedRange(codedInputStream, extensionRegistryLite, null);
    }
}
