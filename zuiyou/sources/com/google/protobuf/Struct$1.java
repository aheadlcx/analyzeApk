package com.google.protobuf;

class Struct$1 extends AbstractParser<Struct> {
    Struct$1() {
    }

    public Struct parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return new Struct(codedInputStream, extensionRegistryLite, null);
    }
}
