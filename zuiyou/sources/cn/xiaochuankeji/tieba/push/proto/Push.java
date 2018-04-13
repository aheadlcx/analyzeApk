package cn.xiaochuankeji.tieba.push.proto;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
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
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat.FieldType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Map.Entry;

public final class Push {
    private static final Descriptor a = ((Descriptor) a().getMessageTypes().get(0));
    private static final FieldAccessorTable b = new FieldAccessorTable(a, new String[]{"Type", "ClientId", "MsgId", "AckMsgId", "Auth", "AppId", "InstallId", "ClientType", "ClientVer", "NetType", "ZipType", "BizType", "BizData", "ErrData", "Extra"});
    private static final Descriptor c = ((Descriptor) a.getNestedTypes().get(0));
    private static final FieldAccessorTable d = new FieldAccessorTable(c, new String[]{"Key", "Value"});
    private static FileDescriptor e;

    public interface PacketOrBuilder extends MessageOrBuilder {
        boolean containsExtra(String str);

        long getAckMsgId();

        String getAppId();

        ByteString getAppIdBytes();

        String getAuth();

        ByteString getAuthBytes();

        ByteString getBizData();

        int getBizType();

        String getClientId();

        ByteString getClientIdBytes();

        String getClientType();

        ByteString getClientTypeBytes();

        String getClientVer();

        ByteString getClientVerBytes();

        ByteString getErrData();

        @Deprecated
        Map<String, String> getExtra();

        int getExtraCount();

        Map<String, String> getExtraMap();

        String getExtraOrDefault(String str, String str2);

        String getExtraOrThrow(String str);

        String getInstallId();

        ByteString getInstallIdBytes();

        long getMsgId();

        String getNetType();

        ByteString getNetTypeBytes();

        PacketType getType();

        int getZipType();

        boolean hasAckMsgId();

        boolean hasAppId();

        boolean hasAuth();

        boolean hasBizData();

        boolean hasBizType();

        boolean hasClientId();

        boolean hasClientType();

        boolean hasClientVer();

        boolean hasErrData();

        boolean hasInstallId();

        boolean hasMsgId();

        boolean hasNetType();

        boolean hasType();

        boolean hasZipType();
    }

    public static final class Packet extends GeneratedMessageV3 implements PacketOrBuilder {
        public static final int ACKMSGID_FIELD_NUMBER = 4;
        public static final int APPID_FIELD_NUMBER = 1002;
        public static final int AUTH_FIELD_NUMBER = 1001;
        public static final int BIZDATA_FIELD_NUMBER = 1502;
        public static final int BIZTYPE_FIELD_NUMBER = 1501;
        public static final int CLIENTID_FIELD_NUMBER = 2;
        public static final int CLIENTTYPE_FIELD_NUMBER = 1004;
        public static final int CLIENTVER_FIELD_NUMBER = 1005;
        private static final Packet DEFAULT_INSTANCE = new Packet();
        public static final int ERRDATA_FIELD_NUMBER = 2000;
        public static final int EXTRA_FIELD_NUMBER = 9999;
        public static final int INSTALLID_FIELD_NUMBER = 1003;
        public static final int MSGID_FIELD_NUMBER = 3;
        public static final int NETTYPE_FIELD_NUMBER = 1006;
        @Deprecated
        public static final Parser<Packet> PARSER = new AbstractParser<Packet>() {
            public /* synthetic */ Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return a(codedInputStream, extensionRegistryLite);
            }

            public Packet a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Packet(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TYPE_FIELD_NUMBER = 1;
        public static final int ZIPTYPE_FIELD_NUMBER = 1500;
        private static final long serialVersionUID = 0;
        private long ackMsgId_;
        private volatile Object appId_;
        private volatile Object auth_;
        private int bitField0_;
        private ByteString bizData_;
        private int bizType_;
        private volatile Object clientId_;
        private volatile Object clientType_;
        private volatile Object clientVer_;
        private ByteString errData_;
        private MapField<String, String> extra_;
        private volatile Object installId_;
        private byte memoizedIsInitialized;
        private long msgId_;
        private volatile Object netType_;
        private int type_;
        private int zipType_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements PacketOrBuilder {
            private long ackMsgId_;
            private Object appId_;
            private Object auth_;
            private int bitField0_;
            private ByteString bizData_;
            private int bizType_;
            private Object clientId_;
            private Object clientType_;
            private Object clientVer_;
            private ByteString errData_;
            private MapField<String, String> extra_;
            private Object installId_;
            private long msgId_;
            private Object netType_;
            private int type_;
            private int zipType_;

            public static final Descriptor getDescriptor() {
                return Push.a;
            }

            protected MapField internalGetMapField(int i) {
                switch (i) {
                    case 9999:
                        return internalGetExtra();
                    default:
                        throw new RuntimeException("Invalid map field number: " + i);
                }
            }

            protected MapField internalGetMutableMapField(int i) {
                switch (i) {
                    case 9999:
                        return internalGetMutableExtra();
                    default:
                        throw new RuntimeException("Invalid map field number: " + i);
                }
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return Push.b.ensureFieldAccessorsInitialized(Packet.class, Builder.class);
            }

            private Builder() {
                this.type_ = 1;
                this.clientId_ = "";
                this.auth_ = "";
                this.appId_ = "";
                this.installId_ = "";
                this.clientType_ = "";
                this.clientVer_ = "";
                this.netType_ = "";
                this.bizData_ = ByteString.EMPTY;
                this.errData_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.type_ = 1;
                this.clientId_ = "";
                this.auth_ = "";
                this.appId_ = "";
                this.installId_ = "";
                this.clientType_ = "";
                this.clientVer_ = "";
                this.netType_ = "";
                this.bizData_ = ByteString.EMPTY;
                this.errData_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (!Packet.alwaysUseFieldBuilders) {
                }
            }

            public Builder clear() {
                super.clear();
                this.type_ = 1;
                this.bitField0_ &= -2;
                this.clientId_ = "";
                this.bitField0_ &= -3;
                this.msgId_ = 0;
                this.bitField0_ &= -5;
                this.ackMsgId_ = 0;
                this.bitField0_ &= -9;
                this.auth_ = "";
                this.bitField0_ &= -17;
                this.appId_ = "";
                this.bitField0_ &= -33;
                this.installId_ = "";
                this.bitField0_ &= -65;
                this.clientType_ = "";
                this.bitField0_ &= -129;
                this.clientVer_ = "";
                this.bitField0_ &= -257;
                this.netType_ = "";
                this.bitField0_ &= -513;
                this.zipType_ = 0;
                this.bitField0_ &= -1025;
                this.bizType_ = 0;
                this.bitField0_ &= -2049;
                this.bizData_ = ByteString.EMPTY;
                this.bitField0_ &= -4097;
                this.errData_ = ByteString.EMPTY;
                this.bitField0_ &= -8193;
                internalGetMutableExtra().clear();
                return this;
            }

            public Descriptor getDescriptorForType() {
                return Push.a;
            }

            public Packet getDefaultInstanceForType() {
                return Packet.getDefaultInstance();
            }

            public Packet build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Packet buildPartial() {
                int i = 1;
                Packet packet = new Packet((com.google.protobuf.GeneratedMessageV3.Builder) this);
                int i2 = this.bitField0_;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                packet.type_ = this.type_;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                packet.clientId_ = this.clientId_;
                if ((i2 & 4) == 4) {
                    i |= 4;
                }
                packet.msgId_ = this.msgId_;
                if ((i2 & 8) == 8) {
                    i |= 8;
                }
                packet.ackMsgId_ = this.ackMsgId_;
                if ((i2 & 16) == 16) {
                    i |= 16;
                }
                packet.auth_ = this.auth_;
                if ((i2 & 32) == 32) {
                    i |= 32;
                }
                packet.appId_ = this.appId_;
                if ((i2 & 64) == 64) {
                    i |= 64;
                }
                packet.installId_ = this.installId_;
                if ((i2 & 128) == 128) {
                    i |= 128;
                }
                packet.clientType_ = this.clientType_;
                if ((i2 & 256) == 256) {
                    i |= 256;
                }
                packet.clientVer_ = this.clientVer_;
                if ((i2 & 512) == 512) {
                    i |= 512;
                }
                packet.netType_ = this.netType_;
                if ((i2 & 1024) == 1024) {
                    i |= 1024;
                }
                packet.zipType_ = this.zipType_;
                if ((i2 & 2048) == 2048) {
                    i |= 2048;
                }
                packet.bizType_ = this.bizType_;
                if ((i2 & 4096) == 4096) {
                    i |= 4096;
                }
                packet.bizData_ = this.bizData_;
                if ((i2 & 8192) == 8192) {
                    i |= 8192;
                }
                packet.errData_ = this.errData_;
                packet.extra_ = internalGetExtra();
                packet.extra_.makeImmutable();
                packet.bitField0_ = i;
                onBuilt();
                return packet;
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
                if (message instanceof Packet) {
                    return mergeFrom((Packet) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Packet packet) {
                if (packet != Packet.getDefaultInstance()) {
                    if (packet.hasType()) {
                        setType(packet.getType());
                    }
                    if (packet.hasClientId()) {
                        this.bitField0_ |= 2;
                        this.clientId_ = packet.clientId_;
                        onChanged();
                    }
                    if (packet.hasMsgId()) {
                        setMsgId(packet.getMsgId());
                    }
                    if (packet.hasAckMsgId()) {
                        setAckMsgId(packet.getAckMsgId());
                    }
                    if (packet.hasAuth()) {
                        this.bitField0_ |= 16;
                        this.auth_ = packet.auth_;
                        onChanged();
                    }
                    if (packet.hasAppId()) {
                        this.bitField0_ |= 32;
                        this.appId_ = packet.appId_;
                        onChanged();
                    }
                    if (packet.hasInstallId()) {
                        this.bitField0_ |= 64;
                        this.installId_ = packet.installId_;
                        onChanged();
                    }
                    if (packet.hasClientType()) {
                        this.bitField0_ |= 128;
                        this.clientType_ = packet.clientType_;
                        onChanged();
                    }
                    if (packet.hasClientVer()) {
                        this.bitField0_ |= 256;
                        this.clientVer_ = packet.clientVer_;
                        onChanged();
                    }
                    if (packet.hasNetType()) {
                        this.bitField0_ |= 512;
                        this.netType_ = packet.netType_;
                        onChanged();
                    }
                    if (packet.hasZipType()) {
                        setZipType(packet.getZipType());
                    }
                    if (packet.hasBizType()) {
                        setBizType(packet.getBizType());
                    }
                    if (packet.hasBizData()) {
                        setBizData(packet.getBizData());
                    }
                    if (packet.hasErrData()) {
                        setErrData(packet.getErrData());
                    }
                    internalGetMutableExtra().mergeFrom(packet.internalGetExtra());
                    mergeUnknownFields(packet.unknownFields);
                    onChanged();
                }
                return this;
            }

            public final boolean isInitialized() {
                if (hasType()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Packet packet;
                Throwable th;
                Packet packet2;
                try {
                    packet = (Packet) Packet.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (packet != null) {
                        mergeFrom(packet);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    packet = (Packet) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException.unwrapIOException();
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    packet2 = packet;
                    th = th3;
                    if (packet2 != null) {
                        mergeFrom(packet2);
                    }
                    throw th;
                }
            }

            public boolean hasType() {
                return (this.bitField0_ & 1) == 1;
            }

            public PacketType getType() {
                PacketType valueOf = PacketType.valueOf(this.type_);
                return valueOf == null ? PacketType.SYN : valueOf;
            }

            public Builder setType(PacketType packetType) {
                if (packetType == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.type_ = packetType.getNumber();
                onChanged();
                return this;
            }

            public Builder clearType() {
                this.bitField0_ &= -2;
                this.type_ = 1;
                onChanged();
                return this;
            }

            public boolean hasClientId() {
                return (this.bitField0_ & 2) == 2;
            }

            public String getClientId() {
                Object obj = this.clientId_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.clientId_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getClientIdBytes() {
                Object obj = this.clientId_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.clientId_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setClientId(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.clientId_ = str;
                onChanged();
                return this;
            }

            public Builder clearClientId() {
                this.bitField0_ &= -3;
                this.clientId_ = Packet.getDefaultInstance().getClientId();
                onChanged();
                return this;
            }

            public Builder setClientIdBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.clientId_ = byteString;
                onChanged();
                return this;
            }

            public boolean hasMsgId() {
                return (this.bitField0_ & 4) == 4;
            }

            public long getMsgId() {
                return this.msgId_;
            }

            public Builder setMsgId(long j) {
                this.bitField0_ |= 4;
                this.msgId_ = j;
                onChanged();
                return this;
            }

            public Builder clearMsgId() {
                this.bitField0_ &= -5;
                this.msgId_ = 0;
                onChanged();
                return this;
            }

            public boolean hasAckMsgId() {
                return (this.bitField0_ & 8) == 8;
            }

            public long getAckMsgId() {
                return this.ackMsgId_;
            }

            public Builder setAckMsgId(long j) {
                this.bitField0_ |= 8;
                this.ackMsgId_ = j;
                onChanged();
                return this;
            }

            public Builder clearAckMsgId() {
                this.bitField0_ &= -9;
                this.ackMsgId_ = 0;
                onChanged();
                return this;
            }

            public boolean hasAuth() {
                return (this.bitField0_ & 16) == 16;
            }

            public String getAuth() {
                Object obj = this.auth_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.auth_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getAuthBytes() {
                Object obj = this.auth_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.auth_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setAuth(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.auth_ = str;
                onChanged();
                return this;
            }

            public Builder clearAuth() {
                this.bitField0_ &= -17;
                this.auth_ = Packet.getDefaultInstance().getAuth();
                onChanged();
                return this;
            }

            public Builder setAuthBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.auth_ = byteString;
                onChanged();
                return this;
            }

            public boolean hasAppId() {
                return (this.bitField0_ & 32) == 32;
            }

            public String getAppId() {
                Object obj = this.appId_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.appId_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getAppIdBytes() {
                Object obj = this.appId_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.appId_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setAppId(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                this.appId_ = str;
                onChanged();
                return this;
            }

            public Builder clearAppId() {
                this.bitField0_ &= -33;
                this.appId_ = Packet.getDefaultInstance().getAppId();
                onChanged();
                return this;
            }

            public Builder setAppIdBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                this.appId_ = byteString;
                onChanged();
                return this;
            }

            public boolean hasInstallId() {
                return (this.bitField0_ & 64) == 64;
            }

            public String getInstallId() {
                Object obj = this.installId_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.installId_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getInstallIdBytes() {
                Object obj = this.installId_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.installId_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setInstallId(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.installId_ = str;
                onChanged();
                return this;
            }

            public Builder clearInstallId() {
                this.bitField0_ &= -65;
                this.installId_ = Packet.getDefaultInstance().getInstallId();
                onChanged();
                return this;
            }

            public Builder setInstallIdBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.installId_ = byteString;
                onChanged();
                return this;
            }

            public boolean hasClientType() {
                return (this.bitField0_ & 128) == 128;
            }

            public String getClientType() {
                Object obj = this.clientType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.clientType_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getClientTypeBytes() {
                Object obj = this.clientType_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.clientType_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setClientType(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 128;
                this.clientType_ = str;
                onChanged();
                return this;
            }

            public Builder clearClientType() {
                this.bitField0_ &= -129;
                this.clientType_ = Packet.getDefaultInstance().getClientType();
                onChanged();
                return this;
            }

            public Builder setClientTypeBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 128;
                this.clientType_ = byteString;
                onChanged();
                return this;
            }

            public boolean hasClientVer() {
                return (this.bitField0_ & 256) == 256;
            }

            public String getClientVer() {
                Object obj = this.clientVer_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.clientVer_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getClientVerBytes() {
                Object obj = this.clientVer_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.clientVer_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setClientVer(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 256;
                this.clientVer_ = str;
                onChanged();
                return this;
            }

            public Builder clearClientVer() {
                this.bitField0_ &= -257;
                this.clientVer_ = Packet.getDefaultInstance().getClientVer();
                onChanged();
                return this;
            }

            public Builder setClientVerBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 256;
                this.clientVer_ = byteString;
                onChanged();
                return this;
            }

            public boolean hasNetType() {
                return (this.bitField0_ & 512) == 512;
            }

            public String getNetType() {
                Object obj = this.netType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.netType_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getNetTypeBytes() {
                Object obj = this.netType_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.netType_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setNetType(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 512;
                this.netType_ = str;
                onChanged();
                return this;
            }

            public Builder clearNetType() {
                this.bitField0_ &= -513;
                this.netType_ = Packet.getDefaultInstance().getNetType();
                onChanged();
                return this;
            }

            public Builder setNetTypeBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 512;
                this.netType_ = byteString;
                onChanged();
                return this;
            }

            public boolean hasZipType() {
                return (this.bitField0_ & 1024) == 1024;
            }

            public int getZipType() {
                return this.zipType_;
            }

            public Builder setZipType(int i) {
                this.bitField0_ |= 1024;
                this.zipType_ = i;
                onChanged();
                return this;
            }

            public Builder clearZipType() {
                this.bitField0_ &= -1025;
                this.zipType_ = 0;
                onChanged();
                return this;
            }

            public boolean hasBizType() {
                return (this.bitField0_ & 2048) == 2048;
            }

            public int getBizType() {
                return this.bizType_;
            }

            public Builder setBizType(int i) {
                this.bitField0_ |= 2048;
                this.bizType_ = i;
                onChanged();
                return this;
            }

            public Builder clearBizType() {
                this.bitField0_ &= -2049;
                this.bizType_ = 0;
                onChanged();
                return this;
            }

            public boolean hasBizData() {
                return (this.bitField0_ & 4096) == 4096;
            }

            public ByteString getBizData() {
                return this.bizData_;
            }

            public Builder setBizData(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4096;
                this.bizData_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearBizData() {
                this.bitField0_ &= -4097;
                this.bizData_ = Packet.getDefaultInstance().getBizData();
                onChanged();
                return this;
            }

            public boolean hasErrData() {
                return (this.bitField0_ & 8192) == 8192;
            }

            public ByteString getErrData() {
                return this.errData_;
            }

            public Builder setErrData(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8192;
                this.errData_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearErrData() {
                this.bitField0_ &= -8193;
                this.errData_ = Packet.getDefaultInstance().getErrData();
                onChanged();
                return this;
            }

            private MapField<String, String> internalGetExtra() {
                if (this.extra_ == null) {
                    return MapField.emptyMapField(a.a);
                }
                return this.extra_;
            }

            private MapField<String, String> internalGetMutableExtra() {
                onChanged();
                if (this.extra_ == null) {
                    this.extra_ = MapField.newMapField(a.a);
                }
                if (!this.extra_.isMutable()) {
                    this.extra_ = this.extra_.copy();
                }
                return this.extra_;
            }

            public int getExtraCount() {
                return internalGetExtra().getMap().size();
            }

            public boolean containsExtra(String str) {
                if (str != null) {
                    return internalGetExtra().getMap().containsKey(str);
                }
                throw new NullPointerException();
            }

            @Deprecated
            public Map<String, String> getExtra() {
                return getExtraMap();
            }

            public Map<String, String> getExtraMap() {
                return internalGetExtra().getMap();
            }

            public String getExtraOrDefault(String str, String str2) {
                if (str == null) {
                    throw new NullPointerException();
                }
                Map map = internalGetExtra().getMap();
                return map.containsKey(str) ? (String) map.get(str) : str2;
            }

            public String getExtraOrThrow(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                Map map = internalGetExtra().getMap();
                if (map.containsKey(str)) {
                    return (String) map.get(str);
                }
                throw new IllegalArgumentException();
            }

            public Builder clearExtra() {
                internalGetMutableExtra().getMutableMap().clear();
                return this;
            }

            public Builder removeExtra(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                internalGetMutableExtra().getMutableMap().remove(str);
                return this;
            }

            @Deprecated
            public Map<String, String> getMutableExtra() {
                return internalGetMutableExtra().getMutableMap();
            }

            public Builder putExtra(String str, String str2) {
                if (str == null) {
                    throw new NullPointerException();
                } else if (str2 == null) {
                    throw new NullPointerException();
                } else {
                    internalGetMutableExtra().getMutableMap().put(str, str2);
                    return this;
                }
            }

            public Builder putAllExtra(Map<String, String> map) {
                internalGetMutableExtra().getMutableMap().putAll(map);
                return this;
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public enum PacketType implements ProtocolMessageEnum {
            SYN(1),
            SYNACK(2),
            RESET(3),
            REROUTE(4),
            HEART(5),
            ACK(6),
            MESSAGE(7),
            CLOSE(8),
            ERR(100),
            ECHO(101);
            
            public static final int ACK_VALUE = 6;
            public static final int CLOSE_VALUE = 8;
            public static final int ECHO_VALUE = 101;
            public static final int ERR_VALUE = 100;
            public static final int HEART_VALUE = 5;
            public static final int MESSAGE_VALUE = 7;
            public static final int REROUTE_VALUE = 4;
            public static final int RESET_VALUE = 3;
            public static final int SYNACK_VALUE = 2;
            public static final int SYN_VALUE = 1;
            private static final PacketType[] VALUES = null;
            private static final EnumLiteMap<PacketType> internalValueMap = null;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<PacketType>() {
                    public /* synthetic */ EnumLite findValueByNumber(int i) {
                        return a(i);
                    }

                    public PacketType a(int i) {
                        return PacketType.forNumber(i);
                    }
                };
                VALUES = values();
            }

            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static PacketType valueOf(int i) {
                return forNumber(i);
            }

            public static PacketType forNumber(int i) {
                switch (i) {
                    case 1:
                        return SYN;
                    case 2:
                        return SYNACK;
                    case 3:
                        return RESET;
                    case 4:
                        return REROUTE;
                    case 5:
                        return HEART;
                    case 6:
                        return ACK;
                    case 7:
                        return MESSAGE;
                    case 8:
                        return CLOSE;
                    case 100:
                        return ERR;
                    case 101:
                        return ECHO;
                    default:
                        return null;
                }
            }

            public static EnumLiteMap<PacketType> internalGetValueMap() {
                return internalValueMap;
            }

            public final EnumValueDescriptor getValueDescriptor() {
                return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
            }

            public final EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final EnumDescriptor getDescriptor() {
                return (EnumDescriptor) Packet.getDescriptor().getEnumTypes().get(0);
            }

            public static PacketType valueOf(EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            private PacketType(int i) {
                this.value = i;
            }
        }

        private static final class a {
            static final MapEntry<String, String> a = MapEntry.newDefaultInstance(Push.c, FieldType.STRING, "", FieldType.STRING, "");
        }

        private Packet(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Packet() {
            this.memoizedIsInitialized = (byte) -1;
            this.type_ = 1;
            this.clientId_ = "";
            this.msgId_ = 0;
            this.ackMsgId_ = 0;
            this.auth_ = "";
            this.appId_ = "";
            this.installId_ = "";
            this.clientType_ = "";
            this.clientVer_ = "";
            this.netType_ = "";
            this.zipType_ = 0;
            this.bizType_ = 0;
            this.bizData_ = ByteString.EMPTY;
            this.errData_ = ByteString.EMPTY;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Packet(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            Object obj = null;
            this();
            if (extensionRegistryLite == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            int i = 0;
            while (obj == null) {
                try {
                    int i2;
                    Object obj2;
                    Object obj3;
                    int readTag = codedInputStream.readTag();
                    ByteString readBytes;
                    switch (readTag) {
                        case 0:
                            i2 = i;
                            i = 1;
                            break;
                        case 8:
                            readTag = codedInputStream.readEnum();
                            if (PacketType.valueOf(readTag) != null) {
                                this.bitField0_ |= 1;
                                this.type_ = readTag;
                                obj2 = obj;
                                i2 = i;
                                obj3 = obj2;
                                break;
                            }
                            newBuilder.mergeVarintField(1, readTag);
                            obj2 = obj;
                            i2 = i;
                            obj3 = obj2;
                            break;
                        case 18:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 2;
                            this.clientId_ = readBytes;
                            obj2 = obj;
                            i2 = i;
                            obj3 = obj2;
                            break;
                        case 25:
                            this.bitField0_ |= 4;
                            this.msgId_ = codedInputStream.readFixed64();
                            obj2 = obj;
                            i2 = i;
                            obj3 = obj2;
                            break;
                        case 33:
                            this.bitField0_ |= 8;
                            this.ackMsgId_ = codedInputStream.readFixed64();
                            obj2 = obj;
                            i2 = i;
                            obj3 = obj2;
                            break;
                        case 8010:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 16;
                            this.auth_ = readBytes;
                            obj2 = obj;
                            i2 = i;
                            obj3 = obj2;
                            break;
                        case 8018:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 32;
                            this.appId_ = readBytes;
                            obj2 = obj;
                            i2 = i;
                            obj3 = obj2;
                            break;
                        case 8026:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 64;
                            this.installId_ = readBytes;
                            obj2 = obj;
                            i2 = i;
                            obj3 = obj2;
                            break;
                        case 8034:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 128;
                            this.clientType_ = readBytes;
                            obj2 = obj;
                            i2 = i;
                            obj3 = obj2;
                            break;
                        case 8042:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 256;
                            this.clientVer_ = readBytes;
                            obj2 = obj;
                            i2 = i;
                            obj3 = obj2;
                            break;
                        case 8050:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 512;
                            this.netType_ = readBytes;
                            obj2 = obj;
                            i2 = i;
                            obj3 = obj2;
                            break;
                        case 12000:
                            this.bitField0_ |= 1024;
                            this.zipType_ = codedInputStream.readInt32();
                            obj2 = obj;
                            i2 = i;
                            obj3 = obj2;
                            break;
                        case 12008:
                            this.bitField0_ |= 2048;
                            this.bizType_ = codedInputStream.readInt32();
                            obj2 = obj;
                            i2 = i;
                            obj3 = obj2;
                            break;
                        case 12018:
                            this.bitField0_ |= 4096;
                            this.bizData_ = codedInputStream.readBytes();
                            obj2 = obj;
                            i2 = i;
                            obj3 = obj2;
                            break;
                        case 16002:
                            this.bitField0_ |= 8192;
                            this.errData_ = codedInputStream.readBytes();
                            obj2 = obj;
                            i2 = i;
                            obj3 = obj2;
                            break;
                        case 79994:
                            if ((i & 16384) != 16384) {
                                this.extra_ = MapField.newMapField(a.a);
                                readTag = i | 16384;
                            } else {
                                readTag = i;
                            }
                            MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(a.a.getParserForType(), extensionRegistryLite);
                            this.extra_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
                            obj3 = obj;
                            i2 = readTag;
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                i2 = i;
                                obj3 = 1;
                                break;
                            }
                            obj2 = obj;
                            i2 = i;
                            obj3 = obj2;
                            break;
                    }
                    obj2 = obj3;
                    i = i2;
                    obj = obj2;
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
            return Push.a;
        }

        protected MapField internalGetMapField(int i) {
            switch (i) {
                case 9999:
                    return internalGetExtra();
                default:
                    throw new RuntimeException("Invalid map field number: " + i);
            }
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return Push.b.ensureFieldAccessorsInitialized(Packet.class, Builder.class);
        }

        public boolean hasType() {
            return (this.bitField0_ & 1) == 1;
        }

        public PacketType getType() {
            PacketType valueOf = PacketType.valueOf(this.type_);
            return valueOf == null ? PacketType.SYN : valueOf;
        }

        public boolean hasClientId() {
            return (this.bitField0_ & 2) == 2;
        }

        public String getClientId() {
            Object obj = this.clientId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.clientId_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getClientIdBytes() {
            Object obj = this.clientId_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.clientId_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasMsgId() {
            return (this.bitField0_ & 4) == 4;
        }

        public long getMsgId() {
            return this.msgId_;
        }

        public boolean hasAckMsgId() {
            return (this.bitField0_ & 8) == 8;
        }

        public long getAckMsgId() {
            return this.ackMsgId_;
        }

        public boolean hasAuth() {
            return (this.bitField0_ & 16) == 16;
        }

        public String getAuth() {
            Object obj = this.auth_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.auth_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getAuthBytes() {
            Object obj = this.auth_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.auth_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasAppId() {
            return (this.bitField0_ & 32) == 32;
        }

        public String getAppId() {
            Object obj = this.appId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.appId_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getAppIdBytes() {
            Object obj = this.appId_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.appId_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasInstallId() {
            return (this.bitField0_ & 64) == 64;
        }

        public String getInstallId() {
            Object obj = this.installId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.installId_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getInstallIdBytes() {
            Object obj = this.installId_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.installId_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasClientType() {
            return (this.bitField0_ & 128) == 128;
        }

        public String getClientType() {
            Object obj = this.clientType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.clientType_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getClientTypeBytes() {
            Object obj = this.clientType_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.clientType_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasClientVer() {
            return (this.bitField0_ & 256) == 256;
        }

        public String getClientVer() {
            Object obj = this.clientVer_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.clientVer_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getClientVerBytes() {
            Object obj = this.clientVer_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.clientVer_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasNetType() {
            return (this.bitField0_ & 512) == 512;
        }

        public String getNetType() {
            Object obj = this.netType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.netType_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getNetTypeBytes() {
            Object obj = this.netType_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.netType_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasZipType() {
            return (this.bitField0_ & 1024) == 1024;
        }

        public int getZipType() {
            return this.zipType_;
        }

        public boolean hasBizType() {
            return (this.bitField0_ & 2048) == 2048;
        }

        public int getBizType() {
            return this.bizType_;
        }

        public boolean hasBizData() {
            return (this.bitField0_ & 4096) == 4096;
        }

        public ByteString getBizData() {
            return this.bizData_;
        }

        public boolean hasErrData() {
            return (this.bitField0_ & 8192) == 8192;
        }

        public ByteString getErrData() {
            return this.errData_;
        }

        private MapField<String, String> internalGetExtra() {
            if (this.extra_ == null) {
                return MapField.emptyMapField(a.a);
            }
            return this.extra_;
        }

        public int getExtraCount() {
            return internalGetExtra().getMap().size();
        }

        public boolean containsExtra(String str) {
            if (str != null) {
                return internalGetExtra().getMap().containsKey(str);
            }
            throw new NullPointerException();
        }

        @Deprecated
        public Map<String, String> getExtra() {
            return getExtraMap();
        }

        public Map<String, String> getExtraMap() {
            return internalGetExtra().getMap();
        }

        public String getExtraOrDefault(String str, String str2) {
            if (str == null) {
                throw new NullPointerException();
            }
            Map map = internalGetExtra().getMap();
            return map.containsKey(str) ? (String) map.get(str) : str2;
        }

        public String getExtraOrThrow(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            Map map = internalGetExtra().getMap();
            if (map.containsKey(str)) {
                return (String) map.get(str);
            }
            throw new IllegalArgumentException();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            if (hasType()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeEnum(1, this.type_);
            }
            if ((this.bitField0_ & 2) == 2) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.clientId_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeFixed64(3, this.msgId_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeFixed64(4, this.ackMsgId_);
            }
            if ((this.bitField0_ & 16) == 16) {
                GeneratedMessageV3.writeString(codedOutputStream, 1001, this.auth_);
            }
            if ((this.bitField0_ & 32) == 32) {
                GeneratedMessageV3.writeString(codedOutputStream, 1002, this.appId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                GeneratedMessageV3.writeString(codedOutputStream, 1003, this.installId_);
            }
            if ((this.bitField0_ & 128) == 128) {
                GeneratedMessageV3.writeString(codedOutputStream, 1004, this.clientType_);
            }
            if ((this.bitField0_ & 256) == 256) {
                GeneratedMessageV3.writeString(codedOutputStream, CLIENTVER_FIELD_NUMBER, this.clientVer_);
            }
            if ((this.bitField0_ & 512) == 512) {
                GeneratedMessageV3.writeString(codedOutputStream, 1006, this.netType_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                codedOutputStream.writeInt32(ZIPTYPE_FIELD_NUMBER, this.zipType_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                codedOutputStream.writeInt32(BIZTYPE_FIELD_NUMBER, this.bizType_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                codedOutputStream.writeBytes(BIZDATA_FIELD_NUMBER, this.bizData_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                codedOutputStream.writeBytes(2000, this.errData_);
            }
            GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetExtra(), a.a, 9999);
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.bitField0_ & 1) == 1) {
                i = 0 + CodedOutputStream.computeEnumSize(1, this.type_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i += GeneratedMessageV3.computeStringSize(2, this.clientId_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i += CodedOutputStream.computeFixed64Size(3, this.msgId_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i += CodedOutputStream.computeFixed64Size(4, this.ackMsgId_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i += GeneratedMessageV3.computeStringSize(1001, this.auth_);
            }
            if ((this.bitField0_ & 32) == 32) {
                i += GeneratedMessageV3.computeStringSize(1002, this.appId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                i += GeneratedMessageV3.computeStringSize(1003, this.installId_);
            }
            if ((this.bitField0_ & 128) == 128) {
                i += GeneratedMessageV3.computeStringSize(1004, this.clientType_);
            }
            if ((this.bitField0_ & 256) == 256) {
                i += GeneratedMessageV3.computeStringSize(CLIENTVER_FIELD_NUMBER, this.clientVer_);
            }
            if ((this.bitField0_ & 512) == 512) {
                i += GeneratedMessageV3.computeStringSize(1006, this.netType_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                i += CodedOutputStream.computeInt32Size(ZIPTYPE_FIELD_NUMBER, this.zipType_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                i += CodedOutputStream.computeInt32Size(BIZTYPE_FIELD_NUMBER, this.bizType_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                i += CodedOutputStream.computeBytesSize(BIZDATA_FIELD_NUMBER, this.bizData_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                i += CodedOutputStream.computeBytesSize(2000, this.errData_);
            }
            int i2 = i;
            for (Entry entry : internalGetExtra().getMap().entrySet()) {
                i2 = CodedOutputStream.computeMessageSize(9999, a.a.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build()) + i2;
            }
            i = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = i;
            return i;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Packet)) {
                return super.equals(obj);
            }
            Packet packet = (Packet) obj;
            boolean z = hasType() == packet.hasType();
            if (hasType()) {
                z = z && this.type_ == packet.type_;
            }
            if (z && hasClientId() == packet.hasClientId()) {
                z = true;
            } else {
                z = false;
            }
            if (hasClientId()) {
                if (z && getClientId().equals(packet.getClientId())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasMsgId() == packet.hasMsgId()) {
                z = true;
            } else {
                z = false;
            }
            if (hasMsgId()) {
                if (z && getMsgId() == packet.getMsgId()) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasAckMsgId() == packet.hasAckMsgId()) {
                z = true;
            } else {
                z = false;
            }
            if (hasAckMsgId()) {
                if (z && getAckMsgId() == packet.getAckMsgId()) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasAuth() == packet.hasAuth()) {
                z = true;
            } else {
                z = false;
            }
            if (hasAuth()) {
                if (z && getAuth().equals(packet.getAuth())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasAppId() == packet.hasAppId()) {
                z = true;
            } else {
                z = false;
            }
            if (hasAppId()) {
                if (z && getAppId().equals(packet.getAppId())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasInstallId() == packet.hasInstallId()) {
                z = true;
            } else {
                z = false;
            }
            if (hasInstallId()) {
                if (z && getInstallId().equals(packet.getInstallId())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasClientType() == packet.hasClientType()) {
                z = true;
            } else {
                z = false;
            }
            if (hasClientType()) {
                if (z && getClientType().equals(packet.getClientType())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasClientVer() == packet.hasClientVer()) {
                z = true;
            } else {
                z = false;
            }
            if (hasClientVer()) {
                if (z && getClientVer().equals(packet.getClientVer())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasNetType() == packet.hasNetType()) {
                z = true;
            } else {
                z = false;
            }
            if (hasNetType()) {
                if (z && getNetType().equals(packet.getNetType())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasZipType() == packet.hasZipType()) {
                z = true;
            } else {
                z = false;
            }
            if (hasZipType()) {
                if (z && getZipType() == packet.getZipType()) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasBizType() == packet.hasBizType()) {
                z = true;
            } else {
                z = false;
            }
            if (hasBizType()) {
                if (z && getBizType() == packet.getBizType()) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasBizData() == packet.hasBizData()) {
                z = true;
            } else {
                z = false;
            }
            if (hasBizData()) {
                if (z && getBizData().equals(packet.getBizData())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && hasErrData() == packet.hasErrData()) {
                z = true;
            } else {
                z = false;
            }
            if (hasErrData()) {
                if (z && getErrData().equals(packet.getErrData())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && internalGetExtra().equals(packet.internalGetExtra())) {
                z = true;
            } else {
                z = false;
            }
            if (z && this.unknownFields.equals(packet.unknownFields)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasType()) {
                hashCode = (((hashCode * 37) + 1) * 53) + this.type_;
            }
            if (hasClientId()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getClientId().hashCode();
            }
            if (hasMsgId()) {
                hashCode = (((hashCode * 37) + 3) * 53) + Internal.hashLong(getMsgId());
            }
            if (hasAckMsgId()) {
                hashCode = (((hashCode * 37) + 4) * 53) + Internal.hashLong(getAckMsgId());
            }
            if (hasAuth()) {
                hashCode = (((hashCode * 37) + 1001) * 53) + getAuth().hashCode();
            }
            if (hasAppId()) {
                hashCode = (((hashCode * 37) + 1002) * 53) + getAppId().hashCode();
            }
            if (hasInstallId()) {
                hashCode = (((hashCode * 37) + 1003) * 53) + getInstallId().hashCode();
            }
            if (hasClientType()) {
                hashCode = (((hashCode * 37) + 1004) * 53) + getClientType().hashCode();
            }
            if (hasClientVer()) {
                hashCode = (((hashCode * 37) + CLIENTVER_FIELD_NUMBER) * 53) + getClientVer().hashCode();
            }
            if (hasNetType()) {
                hashCode = (((hashCode * 37) + 1006) * 53) + getNetType().hashCode();
            }
            if (hasZipType()) {
                hashCode = (((hashCode * 37) + ZIPTYPE_FIELD_NUMBER) * 53) + getZipType();
            }
            if (hasBizType()) {
                hashCode = (((hashCode * 37) + BIZTYPE_FIELD_NUMBER) * 53) + getBizType();
            }
            if (hasBizData()) {
                hashCode = (((hashCode * 37) + BIZDATA_FIELD_NUMBER) * 53) + getBizData().hashCode();
            }
            if (hasErrData()) {
                hashCode = (((hashCode * 37) + 2000) * 53) + getErrData().hashCode();
            }
            if (!internalGetExtra().getMap().isEmpty()) {
                hashCode = (((hashCode * 37) + 9999) * 53) + internalGetExtra().hashCode();
            }
            hashCode = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Packet parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Packet) PARSER.parseFrom(byteBuffer);
        }

        public static Packet parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Packet) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Packet parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Packet) PARSER.parseFrom(byteString);
        }

        public static Packet parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Packet) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Packet parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Packet) PARSER.parseFrom(bArr);
        }

        public static Packet parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Packet) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Packet parseFrom(InputStream inputStream) throws IOException {
            return (Packet) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Packet parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Packet) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Packet parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Packet) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Packet parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Packet) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Packet parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Packet) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Packet parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Packet) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Packet packet) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(packet);
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

        public static Packet getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Packet> parser() {
            return PARSER;
        }

        public Parser<Packet> getParserForType() {
            return PARSER;
        }

        public Packet getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static FileDescriptor a() {
        return e;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\npush.proto\u0012!cn.xiaochuankeji.tieba.push.proto\"£\u0004\n\u0006Packet\u0012B\n\u0004Type\u0018\u0001 \u0002(\u000e24.cn.xiaochuankeji.tieba.push.proto.Packet.PacketType\u0012\u0010\n\bClientId\u0018\u0002 \u0001(\t\u0012\r\n\u0005MsgId\u0018\u0003 \u0001(\u0006\u0012\u0010\n\bAckMsgId\u0018\u0004 \u0001(\u0006\u0012\r\n\u0004Auth\u0018é\u0007 \u0001(\t\u0012\u000e\n\u0005AppId\u0018ê\u0007 \u0001(\t\u0012\u0012\n\tInstallId\u0018ë\u0007 \u0001(\t\u0012\u0013\n\nClientType\u0018ì\u0007 \u0001(\t\u0012\u0012\n\tClientVer\u0018í\u0007 \u0001(\t\u0012\u0010\n\u0007NetType\u0018î\u0007 \u0001(\t\u0012\u0010\n\u0007ZipType\u0018Ü\u000b \u0001(\u0005\u0012\u0010\n\u0007BizType\u0018Ý\u000b \u0001(\u0005\u0012\u0010\n\u0007BizData\u0018Þ\u000b \u0001(\f\u0012\u0010\n\u0007ErrData\u0018Ð\u000f \u0001(\f\u0012D\n\u0005Extra\u0018N \u0003(\u000b24.cn.xiaochuankeji.tieba.push.proto.Packet.ExtraEntry\u001a,\n\nExtraEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001\"x\n\nPacketType\u0012\u0007\n\u0003SYN\u0010\u0001\u0012\n\n\u0006SYNACK\u0010\u0002\u0012\t\n\u0005RESET\u0010\u0003\u0012\u000b\n\u0007REROUTE\u0010\u0004\u0012\t\n\u0005HEART\u0010\u0005\u0012\u0007\n\u0003ACK\u0010\u0006\u0012\u000b\n\u0007MESSAGE\u0010\u0007\u0012\t\n\u0005CLOSE\u0010\b\u0012\u0007\n\u0003ERR\u0010d\u0012\b\n\u0004ECHO\u0010e"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                Push.e = fileDescriptor;
                return null;
            }
        });
    }
}
