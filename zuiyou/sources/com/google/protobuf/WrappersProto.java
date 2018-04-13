package com.google.protobuf;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class WrappersProto {
    private static FileDescriptor descriptor;
    static final Descriptor internal_static_google_protobuf_BoolValue_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(6));
    static final FieldAccessorTable internal_static_google_protobuf_BoolValue_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_BoolValue_descriptor, new String[]{"Value"});
    static final Descriptor internal_static_google_protobuf_BytesValue_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(8));
    static final FieldAccessorTable internal_static_google_protobuf_BytesValue_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_BytesValue_descriptor, new String[]{"Value"});
    static final Descriptor internal_static_google_protobuf_DoubleValue_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_protobuf_DoubleValue_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_DoubleValue_descriptor, new String[]{"Value"});
    static final Descriptor internal_static_google_protobuf_FloatValue_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    static final FieldAccessorTable internal_static_google_protobuf_FloatValue_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_FloatValue_descriptor, new String[]{"Value"});
    static final Descriptor internal_static_google_protobuf_Int32Value_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(4));
    static final FieldAccessorTable internal_static_google_protobuf_Int32Value_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_Int32Value_descriptor, new String[]{"Value"});
    static final Descriptor internal_static_google_protobuf_Int64Value_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(2));
    static final FieldAccessorTable internal_static_google_protobuf_Int64Value_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_Int64Value_descriptor, new String[]{"Value"});
    static final Descriptor internal_static_google_protobuf_StringValue_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(7));
    static final FieldAccessorTable internal_static_google_protobuf_StringValue_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_StringValue_descriptor, new String[]{"Value"});
    static final Descriptor internal_static_google_protobuf_UInt32Value_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(5));
    static final FieldAccessorTable internal_static_google_protobuf_UInt32Value_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_UInt32Value_descriptor, new String[]{"Value"});
    static final Descriptor internal_static_google_protobuf_UInt64Value_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(3));
    static final FieldAccessorTable internal_static_google_protobuf_UInt64Value_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_UInt64Value_descriptor, new String[]{"Value"});

    private WrappersProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001egoogle/protobuf/wrappers.proto\u0012\u000fgoogle.protobuf\"\u001c\n\u000bDoubleValue\u0012\r\n\u0005value\u0018\u0001 \u0001(\u0001\"\u001b\n\nFloatValue\u0012\r\n\u0005value\u0018\u0001 \u0001(\u0002\"\u001b\n\nInt64Value\u0012\r\n\u0005value\u0018\u0001 \u0001(\u0003\"\u001c\n\u000bUInt64Value\u0012\r\n\u0005value\u0018\u0001 \u0001(\u0004\"\u001b\n\nInt32Value\u0012\r\n\u0005value\u0018\u0001 \u0001(\u0005\"\u001c\n\u000bUInt32Value\u0012\r\n\u0005value\u0018\u0001 \u0001(\r\"\u001a\n\tBoolValue\u0012\r\n\u0005value\u0018\u0001 \u0001(\b\"\u001c\n\u000bStringValue\u0012\r\n\u0005value\u0018\u0001 \u0001(\t\"\u001b\n\nBytesValue\u0012\r\n\u0005value\u0018\u0001 \u0001(\fB|\n\u0013com.google.protobufB\rWrappersProtoP\u0001Z*github.com/golang/protobuf/ptypes/wrappersø\u0001\u0001", "¢\u0002\u0003GPBª\u0002\u001eGoogle.Protobuf.WellKnownTypesb\u0006proto3"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                WrappersProto.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
