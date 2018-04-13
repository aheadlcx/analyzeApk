package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite.Builder;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.JavaType;
import com.google.protobuf.Descriptors.FileDescriptor.Syntax;
import com.google.protobuf.Descriptors.OneofDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map.Entry;

public abstract class AbstractMessage$Builder<BuilderType extends AbstractMessage$Builder<BuilderType>> extends Builder implements Message.Builder {
    public BuilderType clone() {
        throw new UnsupportedOperationException("clone() should be implemented in subclasses.");
    }

    public boolean hasOneof(OneofDescriptor oneofDescriptor) {
        throw new UnsupportedOperationException("hasOneof() is not implemented.");
    }

    public FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor) {
        throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
    }

    public BuilderType clearOneof(OneofDescriptor oneofDescriptor) {
        throw new UnsupportedOperationException("clearOneof() is not implemented.");
    }

    public BuilderType clear() {
        for (Entry key : getAllFields().entrySet()) {
            clearField((FieldDescriptor) key.getKey());
        }
        return this;
    }

    public List<String> findInitializationErrors() {
        return MessageReflection.findMissingFields(this);
    }

    public String getInitializationErrorString() {
        return MessageReflection.delimitWithCommas(findInitializationErrors());
    }

    protected BuilderType internalMergeFrom(AbstractMessageLite abstractMessageLite) {
        return mergeFrom((Message) abstractMessageLite);
    }

    public BuilderType mergeFrom(Message message) {
        if (message.getDescriptorForType() != getDescriptorForType()) {
            throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
        }
        for (Entry entry : message.getAllFields().entrySet()) {
            FieldDescriptor fieldDescriptor = (FieldDescriptor) entry.getKey();
            if (fieldDescriptor.isRepeated()) {
                for (Object addRepeatedField : (List) entry.getValue()) {
                    addRepeatedField(fieldDescriptor, addRepeatedField);
                }
            } else if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                Message message2 = (Message) getField(fieldDescriptor);
                if (message2 == message2.getDefaultInstanceForType()) {
                    setField(fieldDescriptor, entry.getValue());
                } else {
                    setField(fieldDescriptor, message2.newBuilderForType().mergeFrom(message2).mergeFrom((Message) entry.getValue()).build());
                }
            } else {
                setField(fieldDescriptor, entry.getValue());
            }
        }
        mergeUnknownFields(message.getUnknownFields());
        return this;
    }

    public BuilderType mergeFrom(CodedInputStream codedInputStream) throws IOException {
        return mergeFrom(codedInputStream, ExtensionRegistry.getEmptyRegistry());
    }

    public BuilderType mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        boolean shouldDiscardUnknownFieldsProto3;
        UnknownFieldSet.Builder builder;
        if (getDescriptorForType().getFile().getSyntax() == Syntax.PROTO3) {
            shouldDiscardUnknownFieldsProto3 = codedInputStream.shouldDiscardUnknownFieldsProto3();
        } else {
            shouldDiscardUnknownFieldsProto3 = codedInputStream.shouldDiscardUnknownFields();
        }
        if (shouldDiscardUnknownFieldsProto3) {
            builder = null;
        } else {
            builder = UnknownFieldSet.newBuilder(getUnknownFields());
        }
        int readTag;
        do {
            readTag = codedInputStream.readTag();
            if (readTag == 0) {
                break;
            }
        } while (MessageReflection.mergeFieldFrom(codedInputStream, builder, extensionRegistryLite, getDescriptorForType(), new BuilderAdapter(this), readTag));
        if (builder != null) {
            setUnknownFields(builder.build());
        }
        return this;
    }

    public BuilderType mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
        setUnknownFields(UnknownFieldSet.newBuilder(getUnknownFields()).mergeFrom(unknownFieldSet).build());
        return this;
    }

    public Message.Builder getFieldBuilder(FieldDescriptor fieldDescriptor) {
        throw new UnsupportedOperationException("getFieldBuilder() called on an unsupported message type.");
    }

    public Message.Builder getRepeatedFieldBuilder(FieldDescriptor fieldDescriptor, int i) {
        throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on an unsupported message type.");
    }

    public String toString() {
        return TextFormat.printToString((MessageOrBuilder) this);
    }

    protected static UninitializedMessageException newUninitializedMessageException(Message message) {
        return new UninitializedMessageException(MessageReflection.findMissingFields(message));
    }

    void markClean() {
        throw new IllegalStateException("Should be overridden by subclasses.");
    }

    void dispose() {
        throw new IllegalStateException("Should be overridden by subclasses.");
    }

    public BuilderType mergeFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AbstractMessage$Builder) super.mergeFrom(byteString);
    }

    public BuilderType mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AbstractMessage$Builder) super.mergeFrom(byteString, extensionRegistryLite);
    }

    public BuilderType mergeFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AbstractMessage$Builder) super.mergeFrom(bArr);
    }

    public BuilderType mergeFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
        return (AbstractMessage$Builder) super.mergeFrom(bArr, i, i2);
    }

    public BuilderType mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AbstractMessage$Builder) super.mergeFrom(bArr, extensionRegistryLite);
    }

    public BuilderType mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AbstractMessage$Builder) super.mergeFrom(bArr, i, i2, extensionRegistryLite);
    }

    public BuilderType mergeFrom(InputStream inputStream) throws IOException {
        return (AbstractMessage$Builder) super.mergeFrom(inputStream);
    }

    public BuilderType mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AbstractMessage$Builder) super.mergeFrom(inputStream, extensionRegistryLite);
    }

    public boolean mergeDelimitedFrom(InputStream inputStream) throws IOException {
        return super.mergeDelimitedFrom(inputStream);
    }

    public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return super.mergeDelimitedFrom(inputStream, extensionRegistryLite);
    }
}
