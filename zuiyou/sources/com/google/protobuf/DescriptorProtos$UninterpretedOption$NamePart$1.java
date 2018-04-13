package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.UninterpretedOption.NamePart;

class DescriptorProtos$UninterpretedOption$NamePart$1 extends AbstractParser<NamePart> {
    DescriptorProtos$UninterpretedOption$NamePart$1() {
    }

    public NamePart parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return new NamePart(codedInputStream, extensionRegistryLite, null);
    }
}
