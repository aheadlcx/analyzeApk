package com.google.protobuf.compiler;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface PluginProtos$CodeGeneratorResponse$FileOrBuilder extends MessageOrBuilder {
    String getContent();

    ByteString getContentBytes();

    String getInsertionPoint();

    ByteString getInsertionPointBytes();

    String getName();

    ByteString getNameBytes();

    boolean hasContent();

    boolean hasInsertionPoint();

    boolean hasName();
}
