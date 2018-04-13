package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.SourceCodeInfo.Location;

class DescriptorProtos$SourceCodeInfo$Location$1 extends AbstractParser<Location> {
    DescriptorProtos$SourceCodeInfo$Location$1() {
    }

    public Location parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return new Location(codedInputStream, extensionRegistryLite, null);
    }
}
