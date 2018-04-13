package cn.xiaochuankeji.tieba.push.proto;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.GeneratedMessageV3.BuilderParent;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class MsgWrapper {
    private static final Descriptor a = ((Descriptor) a().getMessageTypes().get(0));
    private static final FieldAccessorTable b = new FieldAccessorTable(a, new String[]{"User", "Synct", "Syncv", "PrevSyncv", "FromMid", "MessageType", "MessageID", "MessageSt"});
    private static FileDescriptor c;

    public interface SyncProtoItemOrBuilder extends MessageOrBuilder {
        long getFromMid();

        long getMessageID();

        ByteString getMessageSt();

        int getMessageType();

        long getPrevSyncv();

        int getSynct();

        long getSyncv();

        String getUser();

        ByteString getUserBytes();

        boolean hasFromMid();

        boolean hasMessageID();

        boolean hasMessageSt();

        boolean hasMessageType();

        boolean hasPrevSyncv();

        boolean hasSynct();

        boolean hasSyncv();

        boolean hasUser();
    }

    public static final class SyncProtoItem extends GeneratedMessageV3 implements SyncProtoItemOrBuilder {
        private static final SyncProtoItem DEFAULT_INSTANCE = new SyncProtoItem();
        public static final int FROMMID_FIELD_NUMBER = 5;
        public static final int MESSAGEID_FIELD_NUMBER = 101;
        public static final int MESSAGEST_FIELD_NUMBER = 102;
        public static final int MESSAGETYPE_FIELD_NUMBER = 100;
        @Deprecated
        public static final Parser<SyncProtoItem> PARSER = new AbstractParser<SyncProtoItem>() {
            public /* synthetic */ Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return a(codedInputStream, extensionRegistryLite);
            }

            public SyncProtoItem a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new SyncProtoItem(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PREVSYNCV_FIELD_NUMBER = 4;
        public static final int SYNCT_FIELD_NUMBER = 2;
        public static final int SYNCV_FIELD_NUMBER = 3;
        public static final int USER_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private long fromMid_;
        private byte memoizedIsInitialized;
        private long messageID_;
        private ByteString messageSt_;
        private int messageType_;
        private long prevSyncv_;
        private int synct_;
        private long syncv_;
        private volatile Object user_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements SyncProtoItemOrBuilder {
            private int bitField0_;
            private long fromMid_;
            private long messageID_;
            private ByteString messageSt_;
            private int messageType_;
            private long prevSyncv_;
            private int synct_;
            private long syncv_;
            private Object user_;

            public static final Descriptor getDescriptor() {
                return MsgWrapper.a;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return MsgWrapper.b.ensureFieldAccessorsInitialized(SyncProtoItem.class, Builder.class);
            }

            private Builder() {
                this.user_ = "";
                this.messageSt_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.user_ = "";
                this.messageSt_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (!SyncProtoItem.alwaysUseFieldBuilders) {
                }
            }

            public Builder clear() {
                super.clear();
                this.user_ = "";
                this.bitField0_ &= -2;
                this.synct_ = 0;
                this.bitField0_ &= -3;
                this.syncv_ = 0;
                this.bitField0_ &= -5;
                this.prevSyncv_ = 0;
                this.bitField0_ &= -9;
                this.fromMid_ = 0;
                this.bitField0_ &= -17;
                this.messageType_ = 0;
                this.bitField0_ &= -33;
                this.messageID_ = 0;
                this.bitField0_ &= -65;
                this.messageSt_ = ByteString.EMPTY;
                this.bitField0_ &= -129;
                return this;
            }

            public Descriptor getDescriptorForType() {
                return MsgWrapper.a;
            }

            public SyncProtoItem getDefaultInstanceForType() {
                return SyncProtoItem.getDefaultInstance();
            }

            public SyncProtoItem build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public SyncProtoItem buildPartial() {
                int i = 1;
                SyncProtoItem syncProtoItem = new SyncProtoItem((com.google.protobuf.GeneratedMessageV3.Builder) this);
                int i2 = this.bitField0_;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                syncProtoItem.user_ = this.user_;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                syncProtoItem.synct_ = this.synct_;
                if ((i2 & 4) == 4) {
                    i |= 4;
                }
                syncProtoItem.syncv_ = this.syncv_;
                if ((i2 & 8) == 8) {
                    i |= 8;
                }
                syncProtoItem.prevSyncv_ = this.prevSyncv_;
                if ((i2 & 16) == 16) {
                    i |= 16;
                }
                syncProtoItem.fromMid_ = this.fromMid_;
                if ((i2 & 32) == 32) {
                    i |= 32;
                }
                syncProtoItem.messageType_ = this.messageType_;
                if ((i2 & 64) == 64) {
                    i |= 64;
                }
                syncProtoItem.messageID_ = this.messageID_;
                if ((i2 & 128) == 128) {
                    i |= 128;
                }
                syncProtoItem.messageSt_ = this.messageSt_;
                syncProtoItem.bitField0_ = i;
                onBuilt();
                return syncProtoItem;
            }

            public Builder clone() {
                return (Builder) super.clone();
            }

            public Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            public Builder clearField(FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            public Builder clearOneof(OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            public Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            public Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof SyncProtoItem) {
                    return mergeFrom((SyncProtoItem) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(SyncProtoItem syncProtoItem) {
                if (syncProtoItem != SyncProtoItem.getDefaultInstance()) {
                    if (syncProtoItem.hasUser()) {
                        this.bitField0_ |= 1;
                        this.user_ = syncProtoItem.user_;
                        onChanged();
                    }
                    if (syncProtoItem.hasSynct()) {
                        setSynct(syncProtoItem.getSynct());
                    }
                    if (syncProtoItem.hasSyncv()) {
                        setSyncv(syncProtoItem.getSyncv());
                    }
                    if (syncProtoItem.hasPrevSyncv()) {
                        setPrevSyncv(syncProtoItem.getPrevSyncv());
                    }
                    if (syncProtoItem.hasFromMid()) {
                        setFromMid(syncProtoItem.getFromMid());
                    }
                    if (syncProtoItem.hasMessageType()) {
                        setMessageType(syncProtoItem.getMessageType());
                    }
                    if (syncProtoItem.hasMessageID()) {
                        setMessageID(syncProtoItem.getMessageID());
                    }
                    if (syncProtoItem.hasMessageSt()) {
                        setMessageSt(syncProtoItem.getMessageSt());
                    }
                    mergeUnknownFields(syncProtoItem.unknownFields);
                    onChanged();
                }
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                SyncProtoItem syncProtoItem;
                Throwable th;
                SyncProtoItem syncProtoItem2;
                try {
                    syncProtoItem = (SyncProtoItem) SyncProtoItem.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (syncProtoItem != null) {
                        mergeFrom(syncProtoItem);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    syncProtoItem = (SyncProtoItem) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException.unwrapIOException();
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    syncProtoItem2 = syncProtoItem;
                    th = th3;
                    if (syncProtoItem2 != null) {
                        mergeFrom(syncProtoItem2);
                    }
                    throw th;
                }
            }

            public boolean hasUser() {
                return (this.bitField0_ & 1) == 1;
            }

            public String getUser() {
                Object obj = this.user_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.user_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getUserBytes() {
                Object obj = this.user_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.user_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setUser(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.user_ = str;
                onChanged();
                return this;
            }

            public Builder clearUser() {
                this.bitField0_ &= -2;
                this.user_ = SyncProtoItem.getDefaultInstance().getUser();
                onChanged();
                return this;
            }

            public Builder setUserBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.user_ = byteString;
                onChanged();
                return this;
            }

            public boolean hasSynct() {
                return (this.bitField0_ & 2) == 2;
            }

            public int getSynct() {
                return this.synct_;
            }

            public Builder setSynct(int i) {
                this.bitField0_ |= 2;
                this.synct_ = i;
                onChanged();
                return this;
            }

            public Builder clearSynct() {
                this.bitField0_ &= -3;
                this.synct_ = 0;
                onChanged();
                return this;
            }

            public boolean hasSyncv() {
                return (this.bitField0_ & 4) == 4;
            }

            public long getSyncv() {
                return this.syncv_;
            }

            public Builder setSyncv(long j) {
                this.bitField0_ |= 4;
                this.syncv_ = j;
                onChanged();
                return this;
            }

            public Builder clearSyncv() {
                this.bitField0_ &= -5;
                this.syncv_ = 0;
                onChanged();
                return this;
            }

            public boolean hasPrevSyncv() {
                return (this.bitField0_ & 8) == 8;
            }

            public long getPrevSyncv() {
                return this.prevSyncv_;
            }

            public Builder setPrevSyncv(long j) {
                this.bitField0_ |= 8;
                this.prevSyncv_ = j;
                onChanged();
                return this;
            }

            public Builder clearPrevSyncv() {
                this.bitField0_ &= -9;
                this.prevSyncv_ = 0;
                onChanged();
                return this;
            }

            public boolean hasFromMid() {
                return (this.bitField0_ & 16) == 16;
            }

            public long getFromMid() {
                return this.fromMid_;
            }

            public Builder setFromMid(long j) {
                this.bitField0_ |= 16;
                this.fromMid_ = j;
                onChanged();
                return this;
            }

            public Builder clearFromMid() {
                this.bitField0_ &= -17;
                this.fromMid_ = 0;
                onChanged();
                return this;
            }

            public boolean hasMessageType() {
                return (this.bitField0_ & 32) == 32;
            }

            public int getMessageType() {
                return this.messageType_;
            }

            public Builder setMessageType(int i) {
                this.bitField0_ |= 32;
                this.messageType_ = i;
                onChanged();
                return this;
            }

            public Builder clearMessageType() {
                this.bitField0_ &= -33;
                this.messageType_ = 0;
                onChanged();
                return this;
            }

            public boolean hasMessageID() {
                return (this.bitField0_ & 64) == 64;
            }

            public long getMessageID() {
                return this.messageID_;
            }

            public Builder setMessageID(long j) {
                this.bitField0_ |= 64;
                this.messageID_ = j;
                onChanged();
                return this;
            }

            public Builder clearMessageID() {
                this.bitField0_ &= -65;
                this.messageID_ = 0;
                onChanged();
                return this;
            }

            public boolean hasMessageSt() {
                return (this.bitField0_ & 128) == 128;
            }

            public ByteString getMessageSt() {
                return this.messageSt_;
            }

            public Builder setMessageSt(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 128;
                this.messageSt_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearMessageSt() {
                this.bitField0_ &= -129;
                this.messageSt_ = SyncProtoItem.getDefaultInstance().getMessageSt();
                onChanged();
                return this;
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        private SyncProtoItem(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private SyncProtoItem() {
            this.memoizedIsInitialized = (byte) -1;
            this.user_ = "";
            this.synct_ = 0;
            this.syncv_ = 0;
            this.prevSyncv_ = 0;
            this.fromMid_ = 0;
            this.messageType_ = 0;
            this.messageID_ = 0;
            this.messageSt_ = ByteString.EMPTY;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private SyncProtoItem(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            Object obj = null;
            while (obj == null) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            obj = 1;
                            break;
                        case 10:
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 1;
                            this.user_ = readBytes;
                            break;
                        case 16:
                            this.bitField0_ |= 2;
                            this.synct_ = codedInputStream.readInt32();
                            break;
                        case 25:
                            this.bitField0_ |= 4;
                            this.syncv_ = codedInputStream.readFixed64();
                            break;
                        case 33:
                            this.bitField0_ |= 8;
                            this.prevSyncv_ = codedInputStream.readFixed64();
                            break;
                        case 40:
                            this.bitField0_ |= 16;
                            this.fromMid_ = codedInputStream.readInt64();
                            break;
                        case 800:
                            this.bitField0_ |= 32;
                            this.messageType_ = codedInputStream.readInt32();
                            break;
                        case 809:
                            this.bitField0_ |= 64;
                            this.messageID_ = codedInputStream.readFixed64();
                            break;
                        case 818:
                            this.bitField0_ |= 128;
                            this.messageSt_ = codedInputStream.readBytes();
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return MsgWrapper.a;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return MsgWrapper.b.ensureFieldAccessorsInitialized(SyncProtoItem.class, Builder.class);
        }

        public boolean hasUser() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getUser() {
            Object obj = this.user_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.user_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getUserBytes() {
            Object obj = this.user_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.user_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasSynct() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getSynct() {
            return this.synct_;
        }

        public boolean hasSyncv() {
            return (this.bitField0_ & 4) == 4;
        }

        public long getSyncv() {
            return this.syncv_;
        }

        public boolean hasPrevSyncv() {
            return (this.bitField0_ & 8) == 8;
        }

        public long getPrevSyncv() {
            return this.prevSyncv_;
        }

        public boolean hasFromMid() {
            return (this.bitField0_ & 16) == 16;
        }

        public long getFromMid() {
            return this.fromMid_;
        }

        public boolean hasMessageType() {
            return (this.bitField0_ & 32) == 32;
        }

        public int getMessageType() {
            return this.messageType_;
        }

        public boolean hasMessageID() {
            return (this.bitField0_ & 64) == 64;
        }

        public long getMessageID() {
            return this.messageID_;
        }

        public boolean hasMessageSt() {
            return (this.bitField0_ & 128) == 128;
        }

        public ByteString getMessageSt() {
            return this.messageSt_;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.user_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.synct_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeFixed64(3, this.syncv_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeFixed64(4, this.prevSyncv_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeInt64(5, this.fromMid_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeInt32(100, this.messageType_);
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeFixed64(101, this.messageID_);
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeBytes(102, this.messageSt_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.bitField0_ & 1) == 1) {
                i = 0 + GeneratedMessageV3.computeStringSize(1, this.user_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i += CodedOutputStream.computeInt32Size(2, this.synct_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i += CodedOutputStream.computeFixed64Size(3, this.syncv_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i += CodedOutputStream.computeFixed64Size(4, this.prevSyncv_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i += CodedOutputStream.computeInt64Size(5, this.fromMid_);
            }
            if ((this.bitField0_ & 32) == 32) {
                i += CodedOutputStream.computeInt32Size(100, this.messageType_);
            }
            if ((this.bitField0_ & 64) == 64) {
                i += CodedOutputStream.computeFixed64Size(101, this.messageID_);
            }
            if ((this.bitField0_ & 128) == 128) {
                i += CodedOutputStream.computeBytesSize(102, this.messageSt_);
            }
            i += this.unknownFields.getSerializedSize();
            this.memoizedSize = i;
            return i;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof SyncProtoItem)) {
                return super.equals(obj);
            }
            SyncProtoItem syncProtoItem = (SyncProtoItem) obj;
            boolean z = hasUser() == syncProtoItem.hasUser();
            if (hasUser()) {
                z = z && getUser().equals(syncProtoItem.getUser());
            }
            if (z && hasSynct() == syncProtoItem.hasSynct()) {
                z = true;
            } else {
                z = false;
            }
            if (hasSynct()) {
                if (z && getSynct() == syncProtoItem.getSynct()) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasSyncv() == syncProtoItem.hasSyncv()) {
                z = true;
            } else {
                z = false;
            }
            if (hasSyncv()) {
                if (z && getSyncv() == syncProtoItem.getSyncv()) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasPrevSyncv() == syncProtoItem.hasPrevSyncv()) {
                z = true;
            } else {
                z = false;
            }
            if (hasPrevSyncv()) {
                if (z && getPrevSyncv() == syncProtoItem.getPrevSyncv()) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasFromMid() == syncProtoItem.hasFromMid()) {
                z = true;
            } else {
                z = false;
            }
            if (hasFromMid()) {
                if (z && getFromMid() == syncProtoItem.getFromMid()) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasMessageType() == syncProtoItem.hasMessageType()) {
                z = true;
            } else {
                z = false;
            }
            if (hasMessageType()) {
                if (z && getMessageType() == syncProtoItem.getMessageType()) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasMessageID() == syncProtoItem.hasMessageID()) {
                z = true;
            } else {
                z = false;
            }
            if (hasMessageID()) {
                if (z && getMessageID() == syncProtoItem.getMessageID()) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasMessageSt() == syncProtoItem.hasMessageSt()) {
                z = true;
            } else {
                z = false;
            }
            if (hasMessageSt()) {
                if (z && getMessageSt().equals(syncProtoItem.getMessageSt())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && this.unknownFields.equals(syncProtoItem.unknownFields)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasUser()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getUser().hashCode();
            }
            if (hasSynct()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getSynct();
            }
            if (hasSyncv()) {
                hashCode = (((hashCode * 37) + 3) * 53) + Internal.hashLong(getSyncv());
            }
            if (hasPrevSyncv()) {
                hashCode = (((hashCode * 37) + 4) * 53) + Internal.hashLong(getPrevSyncv());
            }
            if (hasFromMid()) {
                hashCode = (((hashCode * 37) + 5) * 53) + Internal.hashLong(getFromMid());
            }
            if (hasMessageType()) {
                hashCode = (((hashCode * 37) + 100) * 53) + getMessageType();
            }
            if (hasMessageID()) {
                hashCode = (((hashCode * 37) + 101) * 53) + Internal.hashLong(getMessageID());
            }
            if (hasMessageSt()) {
                hashCode = (((hashCode * 37) + 102) * 53) + getMessageSt().hashCode();
            }
            hashCode = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static SyncProtoItem parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SyncProtoItem) PARSER.parseFrom(byteBuffer);
        }

        public static SyncProtoItem parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SyncProtoItem) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static SyncProtoItem parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SyncProtoItem) PARSER.parseFrom(byteString);
        }

        public static SyncProtoItem parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SyncProtoItem) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static SyncProtoItem parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SyncProtoItem) PARSER.parseFrom(bArr);
        }

        public static SyncProtoItem parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SyncProtoItem) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static SyncProtoItem parseFrom(InputStream inputStream) throws IOException {
            return (SyncProtoItem) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static SyncProtoItem parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SyncProtoItem) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static SyncProtoItem parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SyncProtoItem) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static SyncProtoItem parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SyncProtoItem) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static SyncProtoItem parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SyncProtoItem) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static SyncProtoItem parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SyncProtoItem) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(SyncProtoItem syncProtoItem) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(syncProtoItem);
        }

        public Builder toBuilder() {
            if (this == DEFAULT_INSTANCE) {
                return new Builder();
            }
            return new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static SyncProtoItem getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SyncProtoItem> parser() {
            return PARSER;
        }

        public Parser<SyncProtoItem> getParserForType() {
            return PARSER;
        }

        public SyncProtoItem getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static FileDescriptor a() {
        return c;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0011msg_wrapper.proto\u0012!cn.xiaochuankeji.tieba.push.proto\"\u0001\n\rSyncProtoItem\u0012\f\n\u0004User\u0018\u0001 \u0001(\t\u0012\r\n\u0005Synct\u0018\u0002 \u0001(\u0005\u0012\r\n\u0005Syncv\u0018\u0003 \u0001(\u0006\u0012\u0011\n\tPrevSyncv\u0018\u0004 \u0001(\u0006\u0012\u000f\n\u0007FromMid\u0018\u0005 \u0001(\u0003\u0012\u0013\n\u000bMessageType\u0018d \u0001(\u0005\u0012\u0011\n\tMessageID\u0018e \u0001(\u0006\u0012\u0011\n\tMessageSt\u0018f \u0001(\f"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                MsgWrapper.c = fileDescriptor;
                return null;
            }
        });
    }
}
