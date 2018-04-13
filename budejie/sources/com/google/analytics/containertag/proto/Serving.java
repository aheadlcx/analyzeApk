package com.google.analytics.containertag.proto;

import com.budejie.www.R$styleable;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import com.google.tagmanager.protobuf.nano.CodedInputByteBufferNano;
import com.google.tagmanager.protobuf.nano.CodedOutputByteBufferNano;
import com.google.tagmanager.protobuf.nano.ExtendableMessageNano;
import com.google.tagmanager.protobuf.nano.Extension;
import com.google.tagmanager.protobuf.nano.Extension.TypeLiteral;
import com.google.tagmanager.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.tagmanager.protobuf.nano.MessageNano;
import com.google.tagmanager.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public interface Serving {

    public static final class CacheOption extends ExtendableMessageNano {
        public static final CacheOption[] EMPTY_ARRAY = new CacheOption[0];
        public int expirationSeconds = 0;
        public int gcacheExpirationSeconds = 0;
        public int level = 1;

        public interface CacheLevel {
            public static final int NO_CACHE = 1;
            public static final int PRIVATE = 2;
            public static final int PUBLIC = 3;
        }

        public final CacheOption clear() {
            this.level = 1;
            this.expirationSeconds = 0;
            this.gcacheExpirationSeconds = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CacheOption)) {
                return false;
            }
            CacheOption cacheOption = (CacheOption) obj;
            if (this.level == cacheOption.level && this.expirationSeconds == cacheOption.expirationSeconds && this.gcacheExpirationSeconds == cacheOption.gcacheExpirationSeconds) {
                if (this.unknownFieldData == null) {
                    if (cacheOption.unknownFieldData == null) {
                        return true;
                    }
                } else if (this.unknownFieldData.equals(cacheOption.unknownFieldData)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return (this.unknownFieldData == null ? 0 : this.unknownFieldData.hashCode()) + ((((((this.level + 527) * 31) + this.expirationSeconds) * 31) + this.gcacheExpirationSeconds) * 31);
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.level != 1) {
                codedOutputByteBufferNano.writeInt32(1, this.level);
            }
            if (this.expirationSeconds != 0) {
                codedOutputByteBufferNano.writeInt32(2, this.expirationSeconds);
            }
            if (this.gcacheExpirationSeconds != 0) {
                codedOutputByteBufferNano.writeInt32(3, this.gcacheExpirationSeconds);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int i = 0;
            if (this.level != 1) {
                i = 0 + CodedOutputByteBufferNano.computeInt32Size(1, this.level);
            }
            if (this.expirationSeconds != 0) {
                i += CodedOutputByteBufferNano.computeInt32Size(2, this.expirationSeconds);
            }
            if (this.gcacheExpirationSeconds != 0) {
                i += CodedOutputByteBufferNano.computeInt32Size(3, this.gcacheExpirationSeconds);
            }
            i += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i;
            return i;
        }

        public CacheOption mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                switch (readTag) {
                    case 0:
                        break;
                    case 8:
                        readTag = codedInputByteBufferNano.readInt32();
                        if (readTag != 1 && readTag != 2 && readTag != 3) {
                            this.level = 1;
                            break;
                        }
                        this.level = readTag;
                        continue;
                        break;
                    case 16:
                        this.expirationSeconds = codedInputByteBufferNano.readInt32();
                        continue;
                    case 24:
                        this.gcacheExpirationSeconds = codedInputByteBufferNano.readInt32();
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, codedInputByteBufferNano, readTag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static CacheOption parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (CacheOption) MessageNano.mergeFrom(new CacheOption(), bArr);
        }

        public static CacheOption parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new CacheOption().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class Container extends ExtendableMessageNano {
        public static final Container[] EMPTY_ARRAY = new Container[0];
        public String containerId = "";
        public Resource jsResource = null;
        public int state = 1;
        public String version = "";

        public final Container clear() {
            this.jsResource = null;
            this.containerId = "";
            this.state = 1;
            this.version = "";
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(java.lang.Object r5) {
            /*
            r4 = this;
            r0 = 1;
            r1 = 0;
            if (r5 != r4) goto L_0x0005;
        L_0x0004:
            return r0;
        L_0x0005:
            r2 = r5 instanceof com.google.analytics.containertag.proto.Serving.Container;
            if (r2 != 0) goto L_0x000b;
        L_0x0009:
            r0 = r1;
            goto L_0x0004;
        L_0x000b:
            r5 = (com.google.analytics.containertag.proto.Serving.Container) r5;
            r2 = r4.jsResource;
            if (r2 != 0) goto L_0x0035;
        L_0x0011:
            r2 = r5.jsResource;
            if (r2 != 0) goto L_0x0033;
        L_0x0015:
            r2 = r4.containerId;
            if (r2 != 0) goto L_0x0040;
        L_0x0019:
            r2 = r5.containerId;
            if (r2 != 0) goto L_0x0033;
        L_0x001d:
            r2 = r4.state;
            r3 = r5.state;
            if (r2 != r3) goto L_0x0033;
        L_0x0023:
            r2 = r4.version;
            if (r2 != 0) goto L_0x004b;
        L_0x0027:
            r2 = r5.version;
            if (r2 != 0) goto L_0x0033;
        L_0x002b:
            r2 = r4.unknownFieldData;
            if (r2 != 0) goto L_0x0056;
        L_0x002f:
            r2 = r5.unknownFieldData;
            if (r2 == 0) goto L_0x0004;
        L_0x0033:
            r0 = r1;
            goto L_0x0004;
        L_0x0035:
            r2 = r4.jsResource;
            r3 = r5.jsResource;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0033;
        L_0x003f:
            goto L_0x0015;
        L_0x0040:
            r2 = r4.containerId;
            r3 = r5.containerId;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0033;
        L_0x004a:
            goto L_0x001d;
        L_0x004b:
            r2 = r4.version;
            r3 = r5.version;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0033;
        L_0x0055:
            goto L_0x002b;
        L_0x0056:
            r2 = r4.unknownFieldData;
            r3 = r5.unknownFieldData;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0033;
        L_0x0060:
            goto L_0x0004;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.analytics.containertag.proto.Serving.Container.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.version == null ? 0 : this.version.hashCode()) + (((((this.containerId == null ? 0 : this.containerId.hashCode()) + (((this.jsResource == null ? 0 : this.jsResource.hashCode()) + 527) * 31)) * 31) + this.state) * 31)) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.jsResource != null) {
                codedOutputByteBufferNano.writeMessage(1, this.jsResource);
            }
            codedOutputByteBufferNano.writeString(3, this.containerId);
            codedOutputByteBufferNano.writeInt32(4, this.state);
            if (!this.version.equals("")) {
                codedOutputByteBufferNano.writeString(5, this.version);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int i = 0;
            if (this.jsResource != null) {
                i = 0 + CodedOutputByteBufferNano.computeMessageSize(1, this.jsResource);
            }
            i = (i + CodedOutputByteBufferNano.computeStringSize(3, this.containerId)) + CodedOutputByteBufferNano.computeInt32Size(4, this.state);
            if (!this.version.equals("")) {
                i += CodedOutputByteBufferNano.computeStringSize(5, this.version);
            }
            i += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i;
            return i;
        }

        public Container mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                switch (readTag) {
                    case 0:
                        break;
                    case 10:
                        this.jsResource = new Resource();
                        codedInputByteBufferNano.readMessage(this.jsResource);
                        continue;
                    case 26:
                        this.containerId = codedInputByteBufferNano.readString();
                        continue;
                    case 32:
                        readTag = codedInputByteBufferNano.readInt32();
                        if (readTag != 1 && readTag != 2) {
                            this.state = 1;
                            break;
                        }
                        this.state = readTag;
                        continue;
                    case 42:
                        this.version = codedInputByteBufferNano.readString();
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, codedInputByteBufferNano, readTag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static Container parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (Container) MessageNano.mergeFrom(new Container(), bArr);
        }

        public static Container parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new Container().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class FunctionCall extends ExtendableMessageNano {
        public static final FunctionCall[] EMPTY_ARRAY = new FunctionCall[0];
        public int function = 0;
        public boolean liveOnly = false;
        public int name = 0;
        public int[] property = WireFormatNano.EMPTY_INT_ARRAY;
        public boolean serverSide = false;

        public final FunctionCall clear() {
            this.property = WireFormatNano.EMPTY_INT_ARRAY;
            this.function = 0;
            this.name = 0;
            this.liveOnly = false;
            this.serverSide = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FunctionCall)) {
                return false;
            }
            FunctionCall functionCall = (FunctionCall) obj;
            if (Arrays.equals(this.property, functionCall.property) && this.function == functionCall.function && this.name == functionCall.name && this.liveOnly == functionCall.liveOnly && this.serverSide == functionCall.serverSide) {
                if (this.unknownFieldData == null) {
                    if (functionCall.unknownFieldData == null) {
                        return true;
                    }
                } else if (this.unknownFieldData.equals(functionCall.unknownFieldData)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i;
            int i2;
            int i3 = 1;
            int i4 = 0;
            if (this.property == null) {
                i = 527;
            } else {
                i = 17;
                for (int i5 : this.property) {
                    i = (i * 31) + i5;
                }
            }
            i = ((((i * 31) + this.function) * 31) + this.name) * 31;
            if (this.liveOnly) {
                i2 = 1;
            } else {
                i2 = 2;
            }
            i2 = (i2 + i) * 31;
            if (!this.serverSide) {
                i3 = 2;
            }
            i2 = (i2 + i3) * 31;
            if (this.unknownFieldData != null) {
                i4 = this.unknownFieldData.hashCode();
            }
            return i2 + i4;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.serverSide) {
                codedOutputByteBufferNano.writeBool(1, this.serverSide);
            }
            codedOutputByteBufferNano.writeInt32(2, this.function);
            if (this.property != null) {
                for (int writeInt32 : this.property) {
                    codedOutputByteBufferNano.writeInt32(3, writeInt32);
                }
            }
            if (this.name != 0) {
                codedOutputByteBufferNano.writeInt32(4, this.name);
            }
            if (this.liveOnly) {
                codedOutputByteBufferNano.writeBool(6, this.liveOnly);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int computeBoolSize;
            int i = 0;
            if (this.serverSide) {
                computeBoolSize = CodedOutputByteBufferNano.computeBoolSize(1, this.serverSide) + 0;
            } else {
                computeBoolSize = 0;
            }
            computeBoolSize += CodedOutputByteBufferNano.computeInt32Size(2, this.function);
            if (this.property != null && this.property.length > 0) {
                int[] iArr = this.property;
                int i2 = 0;
                while (i < iArr.length) {
                    i2 += CodedOutputByteBufferNano.computeInt32SizeNoTag(iArr[i]);
                    i++;
                }
                computeBoolSize = (computeBoolSize + i2) + (this.property.length * 1);
            }
            if (this.name != 0) {
                computeBoolSize += CodedOutputByteBufferNano.computeInt32Size(4, this.name);
            }
            if (this.liveOnly) {
                computeBoolSize += CodedOutputByteBufferNano.computeBoolSize(6, this.liveOnly);
            }
            computeBoolSize += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = computeBoolSize;
            return computeBoolSize;
        }

        public FunctionCall mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                switch (readTag) {
                    case 0:
                        break;
                    case 8:
                        this.serverSide = codedInputByteBufferNano.readBool();
                        continue;
                    case 16:
                        this.function = codedInputByteBufferNano.readInt32();
                        continue;
                    case 24:
                        int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 24);
                        readTag = this.property.length;
                        Object obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.property, 0, obj, 0, readTag);
                        this.property = obj;
                        while (readTag < this.property.length - 1) {
                            this.property[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.property[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    case 32:
                        this.name = codedInputByteBufferNano.readInt32();
                        continue;
                    case 48:
                        this.liveOnly = codedInputByteBufferNano.readBool();
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, codedInputByteBufferNano, readTag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static FunctionCall parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (FunctionCall) MessageNano.mergeFrom(new FunctionCall(), bArr);
        }

        public static FunctionCall parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new FunctionCall().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class GaExperimentRandom extends ExtendableMessageNano {
        public static final GaExperimentRandom[] EMPTY_ARRAY = new GaExperimentRandom[0];
        public String key = "";
        public long lifetimeInMilliseconds = 0;
        public long maxRandom = 2147483647L;
        public long minRandom = 0;
        public boolean retainOriginalValue = false;

        public final GaExperimentRandom clear() {
            this.key = "";
            this.minRandom = 0;
            this.maxRandom = 2147483647L;
            this.retainOriginalValue = false;
            this.lifetimeInMilliseconds = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(java.lang.Object r7) {
            /*
            r6 = this;
            r0 = 1;
            r1 = 0;
            if (r7 != r6) goto L_0x0005;
        L_0x0004:
            return r0;
        L_0x0005:
            r2 = r7 instanceof com.google.analytics.containertag.proto.Serving.GaExperimentRandom;
            if (r2 != 0) goto L_0x000b;
        L_0x0009:
            r0 = r1;
            goto L_0x0004;
        L_0x000b:
            r7 = (com.google.analytics.containertag.proto.Serving.GaExperimentRandom) r7;
            r2 = r6.key;
            if (r2 != 0) goto L_0x003d;
        L_0x0011:
            r2 = r7.key;
            if (r2 != 0) goto L_0x003b;
        L_0x0015:
            r2 = r6.minRandom;
            r4 = r7.minRandom;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 != 0) goto L_0x003b;
        L_0x001d:
            r2 = r6.maxRandom;
            r4 = r7.maxRandom;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 != 0) goto L_0x003b;
        L_0x0025:
            r2 = r6.retainOriginalValue;
            r3 = r7.retainOriginalValue;
            if (r2 != r3) goto L_0x003b;
        L_0x002b:
            r2 = r6.lifetimeInMilliseconds;
            r4 = r7.lifetimeInMilliseconds;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 != 0) goto L_0x003b;
        L_0x0033:
            r2 = r6.unknownFieldData;
            if (r2 != 0) goto L_0x0048;
        L_0x0037:
            r2 = r7.unknownFieldData;
            if (r2 == 0) goto L_0x0004;
        L_0x003b:
            r0 = r1;
            goto L_0x0004;
        L_0x003d:
            r2 = r6.key;
            r3 = r7.key;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x003b;
        L_0x0047:
            goto L_0x0015;
        L_0x0048:
            r2 = r6.unknownFieldData;
            r3 = r7.unknownFieldData;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x003b;
        L_0x0052:
            goto L_0x0004;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.analytics.containertag.proto.Serving.GaExperimentRandom.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((this.retainOriginalValue ? 1 : 2) + (((((((this.key == null ? 0 : this.key.hashCode()) + 527) * 31) + ((int) (this.minRandom ^ (this.minRandom >>> 32)))) * 31) + ((int) (this.maxRandom ^ (this.maxRandom >>> 32)))) * 31)) * 31) + ((int) (this.lifetimeInMilliseconds ^ (this.lifetimeInMilliseconds >>> 32)))) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.key.equals("")) {
                codedOutputByteBufferNano.writeString(1, this.key);
            }
            if (this.minRandom != 0) {
                codedOutputByteBufferNano.writeInt64(2, this.minRandom);
            }
            if (this.maxRandom != 2147483647L) {
                codedOutputByteBufferNano.writeInt64(3, this.maxRandom);
            }
            if (this.retainOriginalValue) {
                codedOutputByteBufferNano.writeBool(4, this.retainOriginalValue);
            }
            if (this.lifetimeInMilliseconds != 0) {
                codedOutputByteBufferNano.writeInt64(5, this.lifetimeInMilliseconds);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int i = 0;
            if (!this.key.equals("")) {
                i = 0 + CodedOutputByteBufferNano.computeStringSize(1, this.key);
            }
            if (this.minRandom != 0) {
                i += CodedOutputByteBufferNano.computeInt64Size(2, this.minRandom);
            }
            if (this.maxRandom != 2147483647L) {
                i += CodedOutputByteBufferNano.computeInt64Size(3, this.maxRandom);
            }
            if (this.retainOriginalValue) {
                i += CodedOutputByteBufferNano.computeBoolSize(4, this.retainOriginalValue);
            }
            if (this.lifetimeInMilliseconds != 0) {
                i += CodedOutputByteBufferNano.computeInt64Size(5, this.lifetimeInMilliseconds);
            }
            i += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i;
            return i;
        }

        public GaExperimentRandom mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                switch (readTag) {
                    case 0:
                        break;
                    case 10:
                        this.key = codedInputByteBufferNano.readString();
                        continue;
                    case 16:
                        this.minRandom = codedInputByteBufferNano.readInt64();
                        continue;
                    case 24:
                        this.maxRandom = codedInputByteBufferNano.readInt64();
                        continue;
                    case 32:
                        this.retainOriginalValue = codedInputByteBufferNano.readBool();
                        continue;
                    case 40:
                        this.lifetimeInMilliseconds = codedInputByteBufferNano.readInt64();
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, codedInputByteBufferNano, readTag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static GaExperimentRandom parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (GaExperimentRandom) MessageNano.mergeFrom(new GaExperimentRandom(), bArr);
        }

        public static GaExperimentRandom parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new GaExperimentRandom().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class GaExperimentSupplemental extends ExtendableMessageNano {
        public static final GaExperimentSupplemental[] EMPTY_ARRAY = new GaExperimentSupplemental[0];
        public GaExperimentRandom[] experimentRandom = GaExperimentRandom.EMPTY_ARRAY;
        public Value[] valueToClear = Value.EMPTY_ARRAY;
        public Value[] valueToPush = Value.EMPTY_ARRAY;

        public final GaExperimentSupplemental clear() {
            this.valueToPush = Value.EMPTY_ARRAY;
            this.valueToClear = Value.EMPTY_ARRAY;
            this.experimentRandom = GaExperimentRandom.EMPTY_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof GaExperimentSupplemental)) {
                return false;
            }
            GaExperimentSupplemental gaExperimentSupplemental = (GaExperimentSupplemental) obj;
            if (Arrays.equals(this.valueToPush, gaExperimentSupplemental.valueToPush) && Arrays.equals(this.valueToClear, gaExperimentSupplemental.valueToClear) && Arrays.equals(this.experimentRandom, gaExperimentSupplemental.experimentRandom)) {
                if (this.unknownFieldData == null) {
                    if (gaExperimentSupplemental.unknownFieldData == null) {
                        return true;
                    }
                } else if (this.unknownFieldData.equals(gaExperimentSupplemental.unknownFieldData)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i;
            int i2;
            int i3 = 0;
            if (this.valueToPush == null) {
                i = 527;
            } else {
                i = 17;
                for (i2 = 0; i2 < this.valueToPush.length; i2++) {
                    i = (this.valueToPush[i2] == null ? 0 : this.valueToPush[i2].hashCode()) + (i * 31);
                }
            }
            if (this.valueToClear == null) {
                i *= 31;
            } else {
                for (i2 = 0; i2 < this.valueToClear.length; i2++) {
                    i = (this.valueToClear[i2] == null ? 0 : this.valueToClear[i2].hashCode()) + (i * 31);
                }
            }
            if (this.experimentRandom == null) {
                i *= 31;
            } else {
                for (i2 = 0; i2 < this.experimentRandom.length; i2++) {
                    i = (this.experimentRandom[i2] == null ? 0 : this.experimentRandom[i2].hashCode()) + (i * 31);
                }
            }
            i2 = i * 31;
            if (this.unknownFieldData != null) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i2 + i3;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            int i = 0;
            if (this.valueToPush != null) {
                for (MessageNano writeMessage : this.valueToPush) {
                    codedOutputByteBufferNano.writeMessage(1, writeMessage);
                }
            }
            if (this.valueToClear != null) {
                for (MessageNano writeMessage2 : this.valueToClear) {
                    codedOutputByteBufferNano.writeMessage(2, writeMessage2);
                }
            }
            if (this.experimentRandom != null) {
                GaExperimentRandom[] gaExperimentRandomArr = this.experimentRandom;
                int length = gaExperimentRandomArr.length;
                while (i < length) {
                    codedOutputByteBufferNano.writeMessage(3, gaExperimentRandomArr[i]);
                    i++;
                }
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            Value[] valueArr;
            int i;
            int i2;
            int i3 = 0;
            if (this.valueToPush != null) {
                valueArr = this.valueToPush;
                i = 0;
                i2 = 0;
                while (i < valueArr.length) {
                    int computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(1, valueArr[i]) + i2;
                    i++;
                    i2 = computeMessageSize;
                }
            } else {
                i2 = 0;
            }
            if (this.valueToClear != null) {
                valueArr = this.valueToClear;
                i = 0;
                while (i < valueArr.length) {
                    computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(2, valueArr[i]) + i2;
                    i++;
                    i2 = computeMessageSize;
                }
            }
            if (this.experimentRandom != null) {
                GaExperimentRandom[] gaExperimentRandomArr = this.experimentRandom;
                while (i3 < gaExperimentRandomArr.length) {
                    i2 += CodedOutputByteBufferNano.computeMessageSize(3, gaExperimentRandomArr[i3]);
                    i3++;
                }
            }
            i2 += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i2;
            return i2;
        }

        public GaExperimentSupplemental mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                int repeatedFieldArrayLength;
                Object obj;
                switch (readTag) {
                    case 0:
                        break;
                    case 10:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                        if (this.valueToPush == null) {
                            readTag = 0;
                        } else {
                            readTag = this.valueToPush.length;
                        }
                        obj = new Value[(repeatedFieldArrayLength + readTag)];
                        if (this.valueToPush != null) {
                            System.arraycopy(this.valueToPush, 0, obj, 0, readTag);
                        }
                        this.valueToPush = obj;
                        while (readTag < this.valueToPush.length - 1) {
                            this.valueToPush[readTag] = new Value();
                            codedInputByteBufferNano.readMessage(this.valueToPush[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.valueToPush[readTag] = new Value();
                        codedInputByteBufferNano.readMessage(this.valueToPush[readTag]);
                        continue;
                    case 18:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 18);
                        if (this.valueToClear == null) {
                            readTag = 0;
                        } else {
                            readTag = this.valueToClear.length;
                        }
                        obj = new Value[(repeatedFieldArrayLength + readTag)];
                        if (this.valueToClear != null) {
                            System.arraycopy(this.valueToClear, 0, obj, 0, readTag);
                        }
                        this.valueToClear = obj;
                        while (readTag < this.valueToClear.length - 1) {
                            this.valueToClear[readTag] = new Value();
                            codedInputByteBufferNano.readMessage(this.valueToClear[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.valueToClear[readTag] = new Value();
                        codedInputByteBufferNano.readMessage(this.valueToClear[readTag]);
                        continue;
                    case 26:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 26);
                        if (this.experimentRandom == null) {
                            readTag = 0;
                        } else {
                            readTag = this.experimentRandom.length;
                        }
                        obj = new GaExperimentRandom[(repeatedFieldArrayLength + readTag)];
                        if (this.experimentRandom != null) {
                            System.arraycopy(this.experimentRandom, 0, obj, 0, readTag);
                        }
                        this.experimentRandom = obj;
                        while (readTag < this.experimentRandom.length - 1) {
                            this.experimentRandom[readTag] = new GaExperimentRandom();
                            codedInputByteBufferNano.readMessage(this.experimentRandom[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.experimentRandom[readTag] = new GaExperimentRandom();
                        codedInputByteBufferNano.readMessage(this.experimentRandom[readTag]);
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, codedInputByteBufferNano, readTag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static GaExperimentSupplemental parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (GaExperimentSupplemental) MessageNano.mergeFrom(new GaExperimentSupplemental(), bArr);
        }

        public static GaExperimentSupplemental parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new GaExperimentSupplemental().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class Property extends ExtendableMessageNano {
        public static final Property[] EMPTY_ARRAY = new Property[0];
        public int key = 0;
        public int value = 0;

        public final Property clear() {
            this.key = 0;
            this.value = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Property)) {
                return false;
            }
            Property property = (Property) obj;
            if (this.key == property.key && this.value == property.value) {
                if (this.unknownFieldData == null) {
                    if (property.unknownFieldData == null) {
                        return true;
                    }
                } else if (this.unknownFieldData.equals(property.unknownFieldData)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return (this.unknownFieldData == null ? 0 : this.unknownFieldData.hashCode()) + ((((this.key + 527) * 31) + this.value) * 31);
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeInt32(1, this.key);
            codedOutputByteBufferNano.writeInt32(2, this.value);
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int computeInt32Size = ((0 + CodedOutputByteBufferNano.computeInt32Size(1, this.key)) + CodedOutputByteBufferNano.computeInt32Size(2, this.value)) + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = computeInt32Size;
            return computeInt32Size;
        }

        public Property mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                switch (readTag) {
                    case 0:
                        break;
                    case 8:
                        this.key = codedInputByteBufferNano.readInt32();
                        continue;
                    case 16:
                        this.value = codedInputByteBufferNano.readInt32();
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, codedInputByteBufferNano, readTag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static Property parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (Property) MessageNano.mergeFrom(new Property(), bArr);
        }

        public static Property parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new Property().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class Resource extends ExtendableMessageNano {
        public static final Resource[] EMPTY_ARRAY = new Resource[0];
        private static final String TEMPLATE_VERSION_SET_DEFAULT = "0";
        public String[] key = WireFormatNano.EMPTY_STRING_ARRAY;
        public CacheOption liveJsCacheOption = null;
        public FunctionCall[] macro = FunctionCall.EMPTY_ARRAY;
        public String malwareScanAuthCode = "";
        public boolean oBSOLETEEnableAutoEventTracking = false;
        public FunctionCall[] predicate = FunctionCall.EMPTY_ARRAY;
        public String previewAuthCode = "";
        public Property[] property = Property.EMPTY_ARRAY;
        public float reportingSampleRate = 0.0f;
        public int resourceFormatVersion = 0;
        public Rule[] rule = Rule.EMPTY_ARRAY;
        public String[] supplemental = WireFormatNano.EMPTY_STRING_ARRAY;
        public FunctionCall[] tag = FunctionCall.EMPTY_ARRAY;
        public String templateVersionSet = "0";
        public String[] usageContext = WireFormatNano.EMPTY_STRING_ARRAY;
        public Value[] value = Value.EMPTY_ARRAY;
        public String version = "";

        public final Resource clear() {
            this.supplemental = WireFormatNano.EMPTY_STRING_ARRAY;
            this.key = WireFormatNano.EMPTY_STRING_ARRAY;
            this.value = Value.EMPTY_ARRAY;
            this.property = Property.EMPTY_ARRAY;
            this.macro = FunctionCall.EMPTY_ARRAY;
            this.tag = FunctionCall.EMPTY_ARRAY;
            this.predicate = FunctionCall.EMPTY_ARRAY;
            this.rule = Rule.EMPTY_ARRAY;
            this.previewAuthCode = "";
            this.malwareScanAuthCode = "";
            this.templateVersionSet = "0";
            this.version = "";
            this.liveJsCacheOption = null;
            this.reportingSampleRate = 0.0f;
            this.oBSOLETEEnableAutoEventTracking = false;
            this.usageContext = WireFormatNano.EMPTY_STRING_ARRAY;
            this.resourceFormatVersion = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(java.lang.Object r5) {
            /*
            r4 = this;
            r0 = 1;
            r1 = 0;
            if (r5 != r4) goto L_0x0005;
        L_0x0004:
            return r0;
        L_0x0005:
            r2 = r5 instanceof com.google.analytics.containertag.proto.Serving.Resource;
            if (r2 != 0) goto L_0x000b;
        L_0x0009:
            r0 = r1;
            goto L_0x0004;
        L_0x000b:
            r5 = (com.google.analytics.containertag.proto.Serving.Resource) r5;
            r2 = r4.supplemental;
            r3 = r5.supplemental;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x00ab;
        L_0x0017:
            r2 = r4.key;
            r3 = r5.key;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x00ab;
        L_0x0021:
            r2 = r4.value;
            r3 = r5.value;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x00ab;
        L_0x002b:
            r2 = r4.property;
            r3 = r5.property;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x00ab;
        L_0x0035:
            r2 = r4.macro;
            r3 = r5.macro;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x00ab;
        L_0x003f:
            r2 = r4.tag;
            r3 = r5.tag;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x00ab;
        L_0x0049:
            r2 = r4.predicate;
            r3 = r5.predicate;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x00ab;
        L_0x0053:
            r2 = r4.rule;
            r3 = r5.rule;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x00ab;
        L_0x005d:
            r2 = r4.previewAuthCode;
            if (r2 != 0) goto L_0x00ae;
        L_0x0061:
            r2 = r5.previewAuthCode;
            if (r2 != 0) goto L_0x00ab;
        L_0x0065:
            r2 = r4.malwareScanAuthCode;
            if (r2 != 0) goto L_0x00b9;
        L_0x0069:
            r2 = r5.malwareScanAuthCode;
            if (r2 != 0) goto L_0x00ab;
        L_0x006d:
            r2 = r4.templateVersionSet;
            if (r2 != 0) goto L_0x00c4;
        L_0x0071:
            r2 = r5.templateVersionSet;
            if (r2 != 0) goto L_0x00ab;
        L_0x0075:
            r2 = r4.version;
            if (r2 != 0) goto L_0x00cf;
        L_0x0079:
            r2 = r5.version;
            if (r2 != 0) goto L_0x00ab;
        L_0x007d:
            r2 = r4.liveJsCacheOption;
            if (r2 != 0) goto L_0x00da;
        L_0x0081:
            r2 = r5.liveJsCacheOption;
            if (r2 != 0) goto L_0x00ab;
        L_0x0085:
            r2 = r4.reportingSampleRate;
            r3 = r5.reportingSampleRate;
            r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
            if (r2 != 0) goto L_0x00ab;
        L_0x008d:
            r2 = r4.oBSOLETEEnableAutoEventTracking;
            r3 = r5.oBSOLETEEnableAutoEventTracking;
            if (r2 != r3) goto L_0x00ab;
        L_0x0093:
            r2 = r4.usageContext;
            r3 = r5.usageContext;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x00ab;
        L_0x009d:
            r2 = r4.resourceFormatVersion;
            r3 = r5.resourceFormatVersion;
            if (r2 != r3) goto L_0x00ab;
        L_0x00a3:
            r2 = r4.unknownFieldData;
            if (r2 != 0) goto L_0x00e5;
        L_0x00a7:
            r2 = r5.unknownFieldData;
            if (r2 == 0) goto L_0x0004;
        L_0x00ab:
            r0 = r1;
            goto L_0x0004;
        L_0x00ae:
            r2 = r4.previewAuthCode;
            r3 = r5.previewAuthCode;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x00ab;
        L_0x00b8:
            goto L_0x0065;
        L_0x00b9:
            r2 = r4.malwareScanAuthCode;
            r3 = r5.malwareScanAuthCode;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x00ab;
        L_0x00c3:
            goto L_0x006d;
        L_0x00c4:
            r2 = r4.templateVersionSet;
            r3 = r5.templateVersionSet;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x00ab;
        L_0x00ce:
            goto L_0x0075;
        L_0x00cf:
            r2 = r4.version;
            r3 = r5.version;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x00ab;
        L_0x00d9:
            goto L_0x007d;
        L_0x00da:
            r2 = r4.liveJsCacheOption;
            r3 = r5.liveJsCacheOption;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x00ab;
        L_0x00e4:
            goto L_0x0085;
        L_0x00e5:
            r2 = r4.unknownFieldData;
            r3 = r5.unknownFieldData;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x00ab;
        L_0x00ef:
            goto L_0x0004;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.analytics.containertag.proto.Serving.Resource.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i;
            int i2;
            int i3 = 0;
            if (this.supplemental == null) {
                i = 527;
            } else {
                i = 17;
                for (i2 = 0; i2 < this.supplemental.length; i2++) {
                    i = (this.supplemental[i2] == null ? 0 : this.supplemental[i2].hashCode()) + (i * 31);
                }
            }
            if (this.key == null) {
                i *= 31;
            } else {
                for (i2 = 0; i2 < this.key.length; i2++) {
                    i = (this.key[i2] == null ? 0 : this.key[i2].hashCode()) + (i * 31);
                }
            }
            if (this.value == null) {
                i *= 31;
            } else {
                for (i2 = 0; i2 < this.value.length; i2++) {
                    i = (this.value[i2] == null ? 0 : this.value[i2].hashCode()) + (i * 31);
                }
            }
            if (this.property == null) {
                i *= 31;
            } else {
                for (i2 = 0; i2 < this.property.length; i2++) {
                    i = (this.property[i2] == null ? 0 : this.property[i2].hashCode()) + (i * 31);
                }
            }
            if (this.macro == null) {
                i *= 31;
            } else {
                for (i2 = 0; i2 < this.macro.length; i2++) {
                    i = (this.macro[i2] == null ? 0 : this.macro[i2].hashCode()) + (i * 31);
                }
            }
            if (this.tag == null) {
                i *= 31;
            } else {
                for (i2 = 0; i2 < this.tag.length; i2++) {
                    i = (this.tag[i2] == null ? 0 : this.tag[i2].hashCode()) + (i * 31);
                }
            }
            if (this.predicate == null) {
                i *= 31;
            } else {
                for (i2 = 0; i2 < this.predicate.length; i2++) {
                    i = (this.predicate[i2] == null ? 0 : this.predicate[i2].hashCode()) + (i * 31);
                }
            }
            if (this.rule == null) {
                i *= 31;
            } else {
                for (i2 = 0; i2 < this.rule.length; i2++) {
                    i = (this.rule[i2] == null ? 0 : this.rule[i2].hashCode()) + (i * 31);
                }
            }
            i2 = (this.oBSOLETEEnableAutoEventTracking ? 1 : 2) + (((((this.liveJsCacheOption == null ? 0 : this.liveJsCacheOption.hashCode()) + (((this.version == null ? 0 : this.version.hashCode()) + (((this.templateVersionSet == null ? 0 : this.templateVersionSet.hashCode()) + (((this.malwareScanAuthCode == null ? 0 : this.malwareScanAuthCode.hashCode()) + (((this.previewAuthCode == null ? 0 : this.previewAuthCode.hashCode()) + (i * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + Float.floatToIntBits(this.reportingSampleRate)) * 31);
            if (this.usageContext == null) {
                i = i2 * 31;
            } else {
                i = i2;
                for (i2 = 0; i2 < this.usageContext.length; i2++) {
                    i = (this.usageContext[i2] == null ? 0 : this.usageContext[i2].hashCode()) + (i * 31);
                }
            }
            i2 = ((i * 31) + this.resourceFormatVersion) * 31;
            if (this.unknownFieldData != null) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i2 + i3;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            int i = 0;
            if (this.key != null) {
                for (String writeString : this.key) {
                    codedOutputByteBufferNano.writeString(1, writeString);
                }
            }
            if (this.value != null) {
                for (MessageNano writeMessage : this.value) {
                    codedOutputByteBufferNano.writeMessage(2, writeMessage);
                }
            }
            if (this.property != null) {
                for (MessageNano writeMessage2 : this.property) {
                    codedOutputByteBufferNano.writeMessage(3, writeMessage2);
                }
            }
            if (this.macro != null) {
                for (MessageNano writeMessage22 : this.macro) {
                    codedOutputByteBufferNano.writeMessage(4, writeMessage22);
                }
            }
            if (this.tag != null) {
                for (MessageNano writeMessage222 : this.tag) {
                    codedOutputByteBufferNano.writeMessage(5, writeMessage222);
                }
            }
            if (this.predicate != null) {
                for (MessageNano writeMessage2222 : this.predicate) {
                    codedOutputByteBufferNano.writeMessage(6, writeMessage2222);
                }
            }
            if (this.rule != null) {
                for (MessageNano writeMessage22222 : this.rule) {
                    codedOutputByteBufferNano.writeMessage(7, writeMessage22222);
                }
            }
            if (!this.previewAuthCode.equals("")) {
                codedOutputByteBufferNano.writeString(9, this.previewAuthCode);
            }
            if (!this.malwareScanAuthCode.equals("")) {
                codedOutputByteBufferNano.writeString(10, this.malwareScanAuthCode);
            }
            if (!this.templateVersionSet.equals("0")) {
                codedOutputByteBufferNano.writeString(12, this.templateVersionSet);
            }
            if (!this.version.equals("")) {
                codedOutputByteBufferNano.writeString(13, this.version);
            }
            if (this.liveJsCacheOption != null) {
                codedOutputByteBufferNano.writeMessage(14, this.liveJsCacheOption);
            }
            if (this.reportingSampleRate != 0.0f) {
                codedOutputByteBufferNano.writeFloat(15, this.reportingSampleRate);
            }
            if (this.usageContext != null) {
                for (String writeString2 : this.usageContext) {
                    codedOutputByteBufferNano.writeString(16, writeString2);
                }
            }
            if (this.resourceFormatVersion != 0) {
                codedOutputByteBufferNano.writeInt32(17, this.resourceFormatVersion);
            }
            if (this.oBSOLETEEnableAutoEventTracking) {
                codedOutputByteBufferNano.writeBool(18, this.oBSOLETEEnableAutoEventTracking);
            }
            if (this.supplemental != null) {
                String[] strArr = this.supplemental;
                int length = strArr.length;
                while (i < length) {
                    codedOutputByteBufferNano.writeString(19, strArr[i]);
                    i++;
                }
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int i;
            int i2;
            FunctionCall[] functionCallArr;
            int i3 = 0;
            if (this.key == null || this.key.length <= 0) {
                i = 0;
            } else {
                i2 = 0;
                for (String computeStringSizeNoTag : this.key) {
                    i2 += CodedOutputByteBufferNano.computeStringSizeNoTag(computeStringSizeNoTag);
                }
                i = (0 + i2) + (this.key.length * 1);
            }
            if (this.value != null) {
                Value[] valueArr = this.value;
                i2 = 0;
                while (i2 < valueArr.length) {
                    int computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(2, valueArr[i2]) + i;
                    i2++;
                    i = computeMessageSize;
                }
            }
            if (this.property != null) {
                Property[] propertyArr = this.property;
                i2 = 0;
                while (i2 < propertyArr.length) {
                    computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(3, propertyArr[i2]) + i;
                    i2++;
                    i = computeMessageSize;
                }
            }
            if (this.macro != null) {
                functionCallArr = this.macro;
                i2 = 0;
                while (i2 < functionCallArr.length) {
                    computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(4, functionCallArr[i2]) + i;
                    i2++;
                    i = computeMessageSize;
                }
            }
            if (this.tag != null) {
                functionCallArr = this.tag;
                i2 = 0;
                while (i2 < functionCallArr.length) {
                    computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(5, functionCallArr[i2]) + i;
                    i2++;
                    i = computeMessageSize;
                }
            }
            if (this.predicate != null) {
                functionCallArr = this.predicate;
                i2 = 0;
                while (i2 < functionCallArr.length) {
                    computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(6, functionCallArr[i2]) + i;
                    i2++;
                    i = computeMessageSize;
                }
            }
            if (this.rule != null) {
                Rule[] ruleArr = this.rule;
                i2 = 0;
                while (i2 < ruleArr.length) {
                    computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(7, ruleArr[i2]) + i;
                    i2++;
                    i = computeMessageSize;
                }
            }
            if (!this.previewAuthCode.equals("")) {
                i += CodedOutputByteBufferNano.computeStringSize(9, this.previewAuthCode);
            }
            if (!this.malwareScanAuthCode.equals("")) {
                i += CodedOutputByteBufferNano.computeStringSize(10, this.malwareScanAuthCode);
            }
            if (!this.templateVersionSet.equals("0")) {
                i += CodedOutputByteBufferNano.computeStringSize(12, this.templateVersionSet);
            }
            if (!this.version.equals("")) {
                i += CodedOutputByteBufferNano.computeStringSize(13, this.version);
            }
            if (this.liveJsCacheOption != null) {
                i += CodedOutputByteBufferNano.computeMessageSize(14, this.liveJsCacheOption);
            }
            if (this.reportingSampleRate != 0.0f) {
                i += CodedOutputByteBufferNano.computeFloatSize(15, this.reportingSampleRate);
            }
            if (this.usageContext != null && this.usageContext.length > 0) {
                computeMessageSize = 0;
                for (String computeStringSizeNoTag2 : this.usageContext) {
                    computeMessageSize += CodedOutputByteBufferNano.computeStringSizeNoTag(computeStringSizeNoTag2);
                }
                i = (i + computeMessageSize) + (this.usageContext.length * 2);
            }
            if (this.resourceFormatVersion != 0) {
                i += CodedOutputByteBufferNano.computeInt32Size(17, this.resourceFormatVersion);
            }
            if (this.oBSOLETEEnableAutoEventTracking) {
                i += CodedOutputByteBufferNano.computeBoolSize(18, this.oBSOLETEEnableAutoEventTracking);
            }
            if (this.supplemental != null && this.supplemental.length > 0) {
                String[] strArr = this.supplemental;
                i2 = 0;
                while (i3 < strArr.length) {
                    i2 += CodedOutputByteBufferNano.computeStringSizeNoTag(strArr[i3]);
                    i3++;
                }
                i = (i + i2) + (this.supplemental.length * 2);
            }
            i += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i;
            return i;
        }

        public Resource mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                int repeatedFieldArrayLength;
                Object obj;
                switch (readTag) {
                    case 0:
                        break;
                    case 10:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                        readTag = this.key.length;
                        obj = new String[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.key, 0, obj, 0, readTag);
                        this.key = obj;
                        while (readTag < this.key.length - 1) {
                            this.key[readTag] = codedInputByteBufferNano.readString();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.key[readTag] = codedInputByteBufferNano.readString();
                        continue;
                    case 18:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 18);
                        if (this.value == null) {
                            readTag = 0;
                        } else {
                            readTag = this.value.length;
                        }
                        obj = new Value[(repeatedFieldArrayLength + readTag)];
                        if (this.value != null) {
                            System.arraycopy(this.value, 0, obj, 0, readTag);
                        }
                        this.value = obj;
                        while (readTag < this.value.length - 1) {
                            this.value[readTag] = new Value();
                            codedInputByteBufferNano.readMessage(this.value[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.value[readTag] = new Value();
                        codedInputByteBufferNano.readMessage(this.value[readTag]);
                        continue;
                    case 26:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 26);
                        if (this.property == null) {
                            readTag = 0;
                        } else {
                            readTag = this.property.length;
                        }
                        obj = new Property[(repeatedFieldArrayLength + readTag)];
                        if (this.property != null) {
                            System.arraycopy(this.property, 0, obj, 0, readTag);
                        }
                        this.property = obj;
                        while (readTag < this.property.length - 1) {
                            this.property[readTag] = new Property();
                            codedInputByteBufferNano.readMessage(this.property[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.property[readTag] = new Property();
                        codedInputByteBufferNano.readMessage(this.property[readTag]);
                        continue;
                    case 34:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 34);
                        if (this.macro == null) {
                            readTag = 0;
                        } else {
                            readTag = this.macro.length;
                        }
                        obj = new FunctionCall[(repeatedFieldArrayLength + readTag)];
                        if (this.macro != null) {
                            System.arraycopy(this.macro, 0, obj, 0, readTag);
                        }
                        this.macro = obj;
                        while (readTag < this.macro.length - 1) {
                            this.macro[readTag] = new FunctionCall();
                            codedInputByteBufferNano.readMessage(this.macro[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.macro[readTag] = new FunctionCall();
                        codedInputByteBufferNano.readMessage(this.macro[readTag]);
                        continue;
                    case 42:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 42);
                        if (this.tag == null) {
                            readTag = 0;
                        } else {
                            readTag = this.tag.length;
                        }
                        obj = new FunctionCall[(repeatedFieldArrayLength + readTag)];
                        if (this.tag != null) {
                            System.arraycopy(this.tag, 0, obj, 0, readTag);
                        }
                        this.tag = obj;
                        while (readTag < this.tag.length - 1) {
                            this.tag[readTag] = new FunctionCall();
                            codedInputByteBufferNano.readMessage(this.tag[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.tag[readTag] = new FunctionCall();
                        codedInputByteBufferNano.readMessage(this.tag[readTag]);
                        continue;
                    case 50:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 50);
                        if (this.predicate == null) {
                            readTag = 0;
                        } else {
                            readTag = this.predicate.length;
                        }
                        obj = new FunctionCall[(repeatedFieldArrayLength + readTag)];
                        if (this.predicate != null) {
                            System.arraycopy(this.predicate, 0, obj, 0, readTag);
                        }
                        this.predicate = obj;
                        while (readTag < this.predicate.length - 1) {
                            this.predicate[readTag] = new FunctionCall();
                            codedInputByteBufferNano.readMessage(this.predicate[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.predicate[readTag] = new FunctionCall();
                        codedInputByteBufferNano.readMessage(this.predicate[readTag]);
                        continue;
                    case 58:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 58);
                        if (this.rule == null) {
                            readTag = 0;
                        } else {
                            readTag = this.rule.length;
                        }
                        obj = new Rule[(repeatedFieldArrayLength + readTag)];
                        if (this.rule != null) {
                            System.arraycopy(this.rule, 0, obj, 0, readTag);
                        }
                        this.rule = obj;
                        while (readTag < this.rule.length - 1) {
                            this.rule[readTag] = new Rule();
                            codedInputByteBufferNano.readMessage(this.rule[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.rule[readTag] = new Rule();
                        codedInputByteBufferNano.readMessage(this.rule[readTag]);
                        continue;
                    case 74:
                        this.previewAuthCode = codedInputByteBufferNano.readString();
                        continue;
                    case 82:
                        this.malwareScanAuthCode = codedInputByteBufferNano.readString();
                        continue;
                    case 98:
                        this.templateVersionSet = codedInputByteBufferNano.readString();
                        continue;
                    case 106:
                        this.version = codedInputByteBufferNano.readString();
                        continue;
                    case 114:
                        this.liveJsCacheOption = new CacheOption();
                        codedInputByteBufferNano.readMessage(this.liveJsCacheOption);
                        continue;
                    case 125:
                        this.reportingSampleRate = codedInputByteBufferNano.readFloat();
                        continue;
                    case 130:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 130);
                        readTag = this.usageContext.length;
                        obj = new String[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.usageContext, 0, obj, 0, readTag);
                        this.usageContext = obj;
                        while (readTag < this.usageContext.length - 1) {
                            this.usageContext[readTag] = codedInputByteBufferNano.readString();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.usageContext[readTag] = codedInputByteBufferNano.readString();
                        continue;
                    case R$styleable.Theme_Custom_new_item_shareFriend_text_color /*136*/:
                        this.resourceFormatVersion = codedInputByteBufferNano.readInt32();
                        continue;
                    case R$styleable.Theme_Custom_phone_verify_btn_bg /*144*/:
                        this.oBSOLETEEnableAutoEventTracking = codedInputByteBufferNano.readBool();
                        continue;
                    case 154:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 154);
                        readTag = this.supplemental.length;
                        obj = new String[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.supplemental, 0, obj, 0, readTag);
                        this.supplemental = obj;
                        while (readTag < this.supplemental.length - 1) {
                            this.supplemental[readTag] = codedInputByteBufferNano.readString();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.supplemental[readTag] = codedInputByteBufferNano.readString();
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, codedInputByteBufferNano, readTag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static Resource parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (Resource) MessageNano.mergeFrom(new Resource(), bArr);
        }

        public static Resource parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new Resource().mergeFrom(codedInputByteBufferNano);
        }
    }

    public interface ResourceState {
        public static final int LIVE = 2;
        public static final int PREVIEW = 1;
    }

    public interface ResourceType {
        public static final int CLEAR_CACHE = 6;
        public static final int GET_COOKIE = 5;
        public static final int JS_RESOURCE = 1;
        public static final int NS_RESOURCE = 2;
        public static final int PIXEL_COLLECTION = 3;
        public static final int RAW_PROTO = 7;
        public static final int SET_COOKIE = 4;
    }

    public static final class Rule extends ExtendableMessageNano {
        public static final Rule[] EMPTY_ARRAY = new Rule[0];
        public int[] addMacro = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] addMacroRuleName = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] addTag = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] addTagRuleName = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] negativePredicate = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] positivePredicate = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] removeMacro = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] removeMacroRuleName = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] removeTag = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] removeTagRuleName = WireFormatNano.EMPTY_INT_ARRAY;

        public final Rule clear() {
            this.positivePredicate = WireFormatNano.EMPTY_INT_ARRAY;
            this.negativePredicate = WireFormatNano.EMPTY_INT_ARRAY;
            this.addTag = WireFormatNano.EMPTY_INT_ARRAY;
            this.removeTag = WireFormatNano.EMPTY_INT_ARRAY;
            this.addTagRuleName = WireFormatNano.EMPTY_INT_ARRAY;
            this.removeTagRuleName = WireFormatNano.EMPTY_INT_ARRAY;
            this.addMacro = WireFormatNano.EMPTY_INT_ARRAY;
            this.removeMacro = WireFormatNano.EMPTY_INT_ARRAY;
            this.addMacroRuleName = WireFormatNano.EMPTY_INT_ARRAY;
            this.removeMacroRuleName = WireFormatNano.EMPTY_INT_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Rule)) {
                return false;
            }
            Rule rule = (Rule) obj;
            if (Arrays.equals(this.positivePredicate, rule.positivePredicate) && Arrays.equals(this.negativePredicate, rule.negativePredicate) && Arrays.equals(this.addTag, rule.addTag) && Arrays.equals(this.removeTag, rule.removeTag) && Arrays.equals(this.addTagRuleName, rule.addTagRuleName) && Arrays.equals(this.removeTagRuleName, rule.removeTagRuleName) && Arrays.equals(this.addMacro, rule.addMacro) && Arrays.equals(this.removeMacro, rule.removeMacro) && Arrays.equals(this.addMacroRuleName, rule.addMacroRuleName) && Arrays.equals(this.removeMacroRuleName, rule.removeMacroRuleName)) {
                if (this.unknownFieldData == null) {
                    if (rule.unknownFieldData == null) {
                        return true;
                    }
                } else if (this.unknownFieldData.equals(rule.unknownFieldData)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i;
            int i2 = 0;
            if (this.positivePredicate == null) {
                i = 527;
            } else {
                i = 17;
                for (int i3 : this.positivePredicate) {
                    i = (i * 31) + i3;
                }
            }
            if (this.negativePredicate == null) {
                i *= 31;
            } else {
                for (int i32 : this.negativePredicate) {
                    i = (i * 31) + i32;
                }
            }
            if (this.addTag == null) {
                i *= 31;
            } else {
                for (int i322 : this.addTag) {
                    i = (i * 31) + i322;
                }
            }
            if (this.removeTag == null) {
                i *= 31;
            } else {
                for (int i3222 : this.removeTag) {
                    i = (i * 31) + i3222;
                }
            }
            if (this.addTagRuleName == null) {
                i *= 31;
            } else {
                for (int i32222 : this.addTagRuleName) {
                    i = (i * 31) + i32222;
                }
            }
            if (this.removeTagRuleName == null) {
                i *= 31;
            } else {
                for (int i322222 : this.removeTagRuleName) {
                    i = (i * 31) + i322222;
                }
            }
            if (this.addMacro == null) {
                i *= 31;
            } else {
                for (int i3222222 : this.addMacro) {
                    i = (i * 31) + i3222222;
                }
            }
            if (this.removeMacro == null) {
                i *= 31;
            } else {
                for (int i32222222 : this.removeMacro) {
                    i = (i * 31) + i32222222;
                }
            }
            if (this.addMacroRuleName == null) {
                i *= 31;
            } else {
                for (int i322222222 : this.addMacroRuleName) {
                    i = (i * 31) + i322222222;
                }
            }
            if (this.removeMacroRuleName == null) {
                i *= 31;
            } else {
                for (int i3222222222 : this.removeMacroRuleName) {
                    i = (i * 31) + i3222222222;
                }
            }
            int i4 = i * 31;
            if (this.unknownFieldData != null) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i4 + i2;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            int i = 0;
            if (this.positivePredicate != null) {
                for (int writeInt32 : this.positivePredicate) {
                    codedOutputByteBufferNano.writeInt32(1, writeInt32);
                }
            }
            if (this.negativePredicate != null) {
                for (int writeInt322 : this.negativePredicate) {
                    codedOutputByteBufferNano.writeInt32(2, writeInt322);
                }
            }
            if (this.addTag != null) {
                for (int writeInt3222 : this.addTag) {
                    codedOutputByteBufferNano.writeInt32(3, writeInt3222);
                }
            }
            if (this.removeTag != null) {
                for (int writeInt32222 : this.removeTag) {
                    codedOutputByteBufferNano.writeInt32(4, writeInt32222);
                }
            }
            if (this.addTagRuleName != null) {
                for (int writeInt322222 : this.addTagRuleName) {
                    codedOutputByteBufferNano.writeInt32(5, writeInt322222);
                }
            }
            if (this.removeTagRuleName != null) {
                for (int writeInt3222222 : this.removeTagRuleName) {
                    codedOutputByteBufferNano.writeInt32(6, writeInt3222222);
                }
            }
            if (this.addMacro != null) {
                for (int writeInt32222222 : this.addMacro) {
                    codedOutputByteBufferNano.writeInt32(7, writeInt32222222);
                }
            }
            if (this.removeMacro != null) {
                for (int writeInt322222222 : this.removeMacro) {
                    codedOutputByteBufferNano.writeInt32(8, writeInt322222222);
                }
            }
            if (this.addMacroRuleName != null) {
                for (int writeInt3222222222 : this.addMacroRuleName) {
                    codedOutputByteBufferNano.writeInt32(9, writeInt3222222222);
                }
            }
            if (this.removeMacroRuleName != null) {
                int[] iArr = this.removeMacroRuleName;
                int length = iArr.length;
                while (i < length) {
                    codedOutputByteBufferNano.writeInt32(10, iArr[i]);
                    i++;
                }
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int i;
            int i2;
            int i3;
            int i4 = 0;
            if (this.positivePredicate == null || this.positivePredicate.length <= 0) {
                i = 0;
            } else {
                i2 = 0;
                for (int computeInt32SizeNoTag : this.positivePredicate) {
                    i2 += CodedOutputByteBufferNano.computeInt32SizeNoTag(computeInt32SizeNoTag);
                }
                i = (0 + i2) + (this.positivePredicate.length * 1);
            }
            if (this.negativePredicate != null && this.negativePredicate.length > 0) {
                i3 = 0;
                for (int computeInt32SizeNoTag2 : this.negativePredicate) {
                    i3 += CodedOutputByteBufferNano.computeInt32SizeNoTag(computeInt32SizeNoTag2);
                }
                i = (i + i3) + (this.negativePredicate.length * 1);
            }
            if (this.addTag != null && this.addTag.length > 0) {
                i3 = 0;
                for (int computeInt32SizeNoTag22 : this.addTag) {
                    i3 += CodedOutputByteBufferNano.computeInt32SizeNoTag(computeInt32SizeNoTag22);
                }
                i = (i + i3) + (this.addTag.length * 1);
            }
            if (this.removeTag != null && this.removeTag.length > 0) {
                i3 = 0;
                for (int computeInt32SizeNoTag222 : this.removeTag) {
                    i3 += CodedOutputByteBufferNano.computeInt32SizeNoTag(computeInt32SizeNoTag222);
                }
                i = (i + i3) + (this.removeTag.length * 1);
            }
            if (this.addTagRuleName != null && this.addTagRuleName.length > 0) {
                i3 = 0;
                for (int computeInt32SizeNoTag2222 : this.addTagRuleName) {
                    i3 += CodedOutputByteBufferNano.computeInt32SizeNoTag(computeInt32SizeNoTag2222);
                }
                i = (i + i3) + (this.addTagRuleName.length * 1);
            }
            if (this.removeTagRuleName != null && this.removeTagRuleName.length > 0) {
                i3 = 0;
                for (int computeInt32SizeNoTag22222 : this.removeTagRuleName) {
                    i3 += CodedOutputByteBufferNano.computeInt32SizeNoTag(computeInt32SizeNoTag22222);
                }
                i = (i + i3) + (this.removeTagRuleName.length * 1);
            }
            if (this.addMacro != null && this.addMacro.length > 0) {
                i3 = 0;
                for (int computeInt32SizeNoTag222222 : this.addMacro) {
                    i3 += CodedOutputByteBufferNano.computeInt32SizeNoTag(computeInt32SizeNoTag222222);
                }
                i = (i + i3) + (this.addMacro.length * 1);
            }
            if (this.removeMacro != null && this.removeMacro.length > 0) {
                i3 = 0;
                for (int computeInt32SizeNoTag2222222 : this.removeMacro) {
                    i3 += CodedOutputByteBufferNano.computeInt32SizeNoTag(computeInt32SizeNoTag2222222);
                }
                i = (i + i3) + (this.removeMacro.length * 1);
            }
            if (this.addMacroRuleName != null && this.addMacroRuleName.length > 0) {
                i3 = 0;
                for (int computeInt32SizeNoTag22222222 : this.addMacroRuleName) {
                    i3 += CodedOutputByteBufferNano.computeInt32SizeNoTag(computeInt32SizeNoTag22222222);
                }
                i = (i + i3) + (this.addMacroRuleName.length * 1);
            }
            if (this.removeMacroRuleName != null && this.removeMacroRuleName.length > 0) {
                int[] iArr = this.removeMacroRuleName;
                i2 = 0;
                while (i4 < iArr.length) {
                    i2 += CodedOutputByteBufferNano.computeInt32SizeNoTag(iArr[i4]);
                    i4++;
                }
                i = (i + i2) + (this.removeMacroRuleName.length * 1);
            }
            i += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i;
            return i;
        }

        public Rule mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                int repeatedFieldArrayLength;
                Object obj;
                switch (readTag) {
                    case 0:
                        break;
                    case 8:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 8);
                        readTag = this.positivePredicate.length;
                        obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.positivePredicate, 0, obj, 0, readTag);
                        this.positivePredicate = obj;
                        while (readTag < this.positivePredicate.length - 1) {
                            this.positivePredicate[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.positivePredicate[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    case 16:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 16);
                        readTag = this.negativePredicate.length;
                        obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.negativePredicate, 0, obj, 0, readTag);
                        this.negativePredicate = obj;
                        while (readTag < this.negativePredicate.length - 1) {
                            this.negativePredicate[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.negativePredicate[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    case 24:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 24);
                        readTag = this.addTag.length;
                        obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.addTag, 0, obj, 0, readTag);
                        this.addTag = obj;
                        while (readTag < this.addTag.length - 1) {
                            this.addTag[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.addTag[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    case 32:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 32);
                        readTag = this.removeTag.length;
                        obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.removeTag, 0, obj, 0, readTag);
                        this.removeTag = obj;
                        while (readTag < this.removeTag.length - 1) {
                            this.removeTag[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.removeTag[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    case 40:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 40);
                        readTag = this.addTagRuleName.length;
                        obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.addTagRuleName, 0, obj, 0, readTag);
                        this.addTagRuleName = obj;
                        while (readTag < this.addTagRuleName.length - 1) {
                            this.addTagRuleName[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.addTagRuleName[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    case 48:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 48);
                        readTag = this.removeTagRuleName.length;
                        obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.removeTagRuleName, 0, obj, 0, readTag);
                        this.removeTagRuleName = obj;
                        while (readTag < this.removeTagRuleName.length - 1) {
                            this.removeTagRuleName[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.removeTagRuleName[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    case 56:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 56);
                        readTag = this.addMacro.length;
                        obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.addMacro, 0, obj, 0, readTag);
                        this.addMacro = obj;
                        while (readTag < this.addMacro.length - 1) {
                            this.addMacro[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.addMacro[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    case 64:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 64);
                        readTag = this.removeMacro.length;
                        obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.removeMacro, 0, obj, 0, readTag);
                        this.removeMacro = obj;
                        while (readTag < this.removeMacro.length - 1) {
                            this.removeMacro[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.removeMacro[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    case 72:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 72);
                        readTag = this.addMacroRuleName.length;
                        obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.addMacroRuleName, 0, obj, 0, readTag);
                        this.addMacroRuleName = obj;
                        while (readTag < this.addMacroRuleName.length - 1) {
                            this.addMacroRuleName[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.addMacroRuleName[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    case 80:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 80);
                        readTag = this.removeMacroRuleName.length;
                        obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.removeMacroRuleName, 0, obj, 0, readTag);
                        this.removeMacroRuleName = obj;
                        while (readTag < this.removeMacroRuleName.length - 1) {
                            this.removeMacroRuleName[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.removeMacroRuleName[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, codedInputByteBufferNano, readTag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static Rule parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (Rule) MessageNano.mergeFrom(new Rule(), bArr);
        }

        public static Rule parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new Rule().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class ServingValue extends ExtendableMessageNano {
        public static final ServingValue[] EMPTY_ARRAY = new ServingValue[0];
        public static final Extension<ServingValue> ext = Extension.create(101, new TypeLiteral<ServingValue>() {
        });
        public int[] listItem = WireFormatNano.EMPTY_INT_ARRAY;
        public int macroNameReference = 0;
        public int macroReference = 0;
        public int[] mapKey = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] mapValue = WireFormatNano.EMPTY_INT_ARRAY;
        public int tagReference = 0;
        public int[] templateToken = WireFormatNano.EMPTY_INT_ARRAY;

        public final ServingValue clear() {
            this.listItem = WireFormatNano.EMPTY_INT_ARRAY;
            this.mapKey = WireFormatNano.EMPTY_INT_ARRAY;
            this.mapValue = WireFormatNano.EMPTY_INT_ARRAY;
            this.macroReference = 0;
            this.templateToken = WireFormatNano.EMPTY_INT_ARRAY;
            this.macroNameReference = 0;
            this.tagReference = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ServingValue)) {
                return false;
            }
            ServingValue servingValue = (ServingValue) obj;
            if (Arrays.equals(this.listItem, servingValue.listItem) && Arrays.equals(this.mapKey, servingValue.mapKey) && Arrays.equals(this.mapValue, servingValue.mapValue) && this.macroReference == servingValue.macroReference && Arrays.equals(this.templateToken, servingValue.templateToken) && this.macroNameReference == servingValue.macroNameReference && this.tagReference == servingValue.tagReference) {
                if (this.unknownFieldData == null) {
                    if (servingValue.unknownFieldData == null) {
                        return true;
                    }
                } else if (this.unknownFieldData.equals(servingValue.unknownFieldData)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i;
            int i2 = 0;
            if (this.listItem == null) {
                i = 527;
            } else {
                i = 17;
                for (int i3 : this.listItem) {
                    i = (i * 31) + i3;
                }
            }
            if (this.mapKey == null) {
                i *= 31;
            } else {
                for (int i32 : this.mapKey) {
                    i = (i * 31) + i32;
                }
            }
            if (this.mapValue == null) {
                i *= 31;
            } else {
                for (int i322 : this.mapValue) {
                    i = (i * 31) + i322;
                }
            }
            int i4 = (i * 31) + this.macroReference;
            if (this.templateToken == null) {
                i = i4 * 31;
            } else {
                i = i4;
                for (int i3222 : this.templateToken) {
                    i = (i * 31) + i3222;
                }
            }
            i4 = ((((i * 31) + this.macroNameReference) * 31) + this.tagReference) * 31;
            if (this.unknownFieldData != null) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i4 + i2;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            int i = 0;
            if (this.listItem != null) {
                for (int writeInt32 : this.listItem) {
                    codedOutputByteBufferNano.writeInt32(1, writeInt32);
                }
            }
            if (this.mapKey != null) {
                for (int writeInt322 : this.mapKey) {
                    codedOutputByteBufferNano.writeInt32(2, writeInt322);
                }
            }
            if (this.mapValue != null) {
                for (int writeInt3222 : this.mapValue) {
                    codedOutputByteBufferNano.writeInt32(3, writeInt3222);
                }
            }
            if (this.macroReference != 0) {
                codedOutputByteBufferNano.writeInt32(4, this.macroReference);
            }
            if (this.templateToken != null) {
                int[] iArr = this.templateToken;
                int length = iArr.length;
                while (i < length) {
                    codedOutputByteBufferNano.writeInt32(5, iArr[i]);
                    i++;
                }
            }
            if (this.macroNameReference != 0) {
                codedOutputByteBufferNano.writeInt32(6, this.macroNameReference);
            }
            if (this.tagReference != 0) {
                codedOutputByteBufferNano.writeInt32(7, this.tagReference);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int i;
            int i2;
            int i3;
            int i4 = 0;
            if (this.listItem == null || this.listItem.length <= 0) {
                i = 0;
            } else {
                i2 = 0;
                for (int computeInt32SizeNoTag : this.listItem) {
                    i2 += CodedOutputByteBufferNano.computeInt32SizeNoTag(computeInt32SizeNoTag);
                }
                i = (0 + i2) + (this.listItem.length * 1);
            }
            if (this.mapKey != null && this.mapKey.length > 0) {
                i3 = 0;
                for (int computeInt32SizeNoTag2 : this.mapKey) {
                    i3 += CodedOutputByteBufferNano.computeInt32SizeNoTag(computeInt32SizeNoTag2);
                }
                i = (i + i3) + (this.mapKey.length * 1);
            }
            if (this.mapValue != null && this.mapValue.length > 0) {
                i3 = 0;
                for (int computeInt32SizeNoTag22 : this.mapValue) {
                    i3 += CodedOutputByteBufferNano.computeInt32SizeNoTag(computeInt32SizeNoTag22);
                }
                i = (i + i3) + (this.mapValue.length * 1);
            }
            if (this.macroReference != 0) {
                i += CodedOutputByteBufferNano.computeInt32Size(4, this.macroReference);
            }
            if (this.templateToken != null && this.templateToken.length > 0) {
                int[] iArr = this.templateToken;
                i2 = 0;
                while (i4 < iArr.length) {
                    i2 += CodedOutputByteBufferNano.computeInt32SizeNoTag(iArr[i4]);
                    i4++;
                }
                i = (i + i2) + (this.templateToken.length * 1);
            }
            if (this.macroNameReference != 0) {
                i += CodedOutputByteBufferNano.computeInt32Size(6, this.macroNameReference);
            }
            if (this.tagReference != 0) {
                i += CodedOutputByteBufferNano.computeInt32Size(7, this.tagReference);
            }
            i += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i;
            return i;
        }

        public ServingValue mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                int repeatedFieldArrayLength;
                Object obj;
                switch (readTag) {
                    case 0:
                        break;
                    case 8:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 8);
                        readTag = this.listItem.length;
                        obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.listItem, 0, obj, 0, readTag);
                        this.listItem = obj;
                        while (readTag < this.listItem.length - 1) {
                            this.listItem[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.listItem[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    case 16:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 16);
                        readTag = this.mapKey.length;
                        obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.mapKey, 0, obj, 0, readTag);
                        this.mapKey = obj;
                        while (readTag < this.mapKey.length - 1) {
                            this.mapKey[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.mapKey[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    case 24:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 24);
                        readTag = this.mapValue.length;
                        obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.mapValue, 0, obj, 0, readTag);
                        this.mapValue = obj;
                        while (readTag < this.mapValue.length - 1) {
                            this.mapValue[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.mapValue[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    case 32:
                        this.macroReference = codedInputByteBufferNano.readInt32();
                        continue;
                    case 40:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 40);
                        readTag = this.templateToken.length;
                        obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.templateToken, 0, obj, 0, readTag);
                        this.templateToken = obj;
                        while (readTag < this.templateToken.length - 1) {
                            this.templateToken[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.templateToken[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    case 48:
                        this.macroNameReference = codedInputByteBufferNano.readInt32();
                        continue;
                    case 56:
                        this.tagReference = codedInputByteBufferNano.readInt32();
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, codedInputByteBufferNano, readTag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static ServingValue parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (ServingValue) MessageNano.mergeFrom(new ServingValue(), bArr);
        }

        public static ServingValue parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new ServingValue().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class Supplemental extends ExtendableMessageNano {
        public static final Supplemental[] EMPTY_ARRAY = new Supplemental[0];
        public GaExperimentSupplemental experimentSupplemental = null;
        public String name = "";
        public Value value = null;

        public final Supplemental clear() {
            this.name = "";
            this.value = null;
            this.experimentSupplemental = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(java.lang.Object r5) {
            /*
            r4 = this;
            r0 = 1;
            r1 = 0;
            if (r5 != r4) goto L_0x0005;
        L_0x0004:
            return r0;
        L_0x0005:
            r2 = r5 instanceof com.google.analytics.containertag.proto.Serving.Supplemental;
            if (r2 != 0) goto L_0x000b;
        L_0x0009:
            r0 = r1;
            goto L_0x0004;
        L_0x000b:
            r5 = (com.google.analytics.containertag.proto.Serving.Supplemental) r5;
            r2 = r4.name;
            if (r2 != 0) goto L_0x002f;
        L_0x0011:
            r2 = r5.name;
            if (r2 != 0) goto L_0x002d;
        L_0x0015:
            r2 = r4.value;
            if (r2 != 0) goto L_0x003a;
        L_0x0019:
            r2 = r5.value;
            if (r2 != 0) goto L_0x002d;
        L_0x001d:
            r2 = r4.experimentSupplemental;
            if (r2 != 0) goto L_0x0045;
        L_0x0021:
            r2 = r5.experimentSupplemental;
            if (r2 != 0) goto L_0x002d;
        L_0x0025:
            r2 = r4.unknownFieldData;
            if (r2 != 0) goto L_0x0050;
        L_0x0029:
            r2 = r5.unknownFieldData;
            if (r2 == 0) goto L_0x0004;
        L_0x002d:
            r0 = r1;
            goto L_0x0004;
        L_0x002f:
            r2 = r4.name;
            r3 = r5.name;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x002d;
        L_0x0039:
            goto L_0x0015;
        L_0x003a:
            r2 = r4.value;
            r3 = r5.value;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x002d;
        L_0x0044:
            goto L_0x001d;
        L_0x0045:
            r2 = r4.experimentSupplemental;
            r3 = r5.experimentSupplemental;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x002d;
        L_0x004f:
            goto L_0x0025;
        L_0x0050:
            r2 = r4.unknownFieldData;
            r3 = r5.unknownFieldData;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x002d;
        L_0x005a:
            goto L_0x0004;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.analytics.containertag.proto.Serving.Supplemental.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.experimentSupplemental == null ? 0 : this.experimentSupplemental.hashCode()) + (((this.value == null ? 0 : this.value.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + 527) * 31)) * 31)) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.name.equals("")) {
                codedOutputByteBufferNano.writeString(1, this.name);
            }
            if (this.value != null) {
                codedOutputByteBufferNano.writeMessage(2, this.value);
            }
            if (this.experimentSupplemental != null) {
                codedOutputByteBufferNano.writeMessage(3, this.experimentSupplemental);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int i = 0;
            if (!this.name.equals("")) {
                i = 0 + CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.value != null) {
                i += CodedOutputByteBufferNano.computeMessageSize(2, this.value);
            }
            if (this.experimentSupplemental != null) {
                i += CodedOutputByteBufferNano.computeMessageSize(3, this.experimentSupplemental);
            }
            i += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i;
            return i;
        }

        public Supplemental mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                switch (readTag) {
                    case 0:
                        break;
                    case 10:
                        this.name = codedInputByteBufferNano.readString();
                        continue;
                    case 18:
                        this.value = new Value();
                        codedInputByteBufferNano.readMessage(this.value);
                        continue;
                    case 26:
                        this.experimentSupplemental = new GaExperimentSupplemental();
                        codedInputByteBufferNano.readMessage(this.experimentSupplemental);
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, codedInputByteBufferNano, readTag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static Supplemental parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (Supplemental) MessageNano.mergeFrom(new Supplemental(), bArr);
        }

        public static Supplemental parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new Supplemental().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class SupplementedResource extends ExtendableMessageNano {
        public static final SupplementedResource[] EMPTY_ARRAY = new SupplementedResource[0];
        public String fingerprint = "";
        public Resource resource = null;
        public Supplemental[] supplemental = Supplemental.EMPTY_ARRAY;

        public final SupplementedResource clear() {
            this.supplemental = Supplemental.EMPTY_ARRAY;
            this.resource = null;
            this.fingerprint = "";
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(java.lang.Object r5) {
            /*
            r4 = this;
            r0 = 1;
            r1 = 0;
            if (r5 != r4) goto L_0x0005;
        L_0x0004:
            return r0;
        L_0x0005:
            r2 = r5 instanceof com.google.analytics.containertag.proto.Serving.SupplementedResource;
            if (r2 != 0) goto L_0x000b;
        L_0x0009:
            r0 = r1;
            goto L_0x0004;
        L_0x000b:
            r5 = (com.google.analytics.containertag.proto.Serving.SupplementedResource) r5;
            r2 = r4.supplemental;
            r3 = r5.supplemental;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x002f;
        L_0x0017:
            r2 = r4.resource;
            if (r2 != 0) goto L_0x0031;
        L_0x001b:
            r2 = r5.resource;
            if (r2 != 0) goto L_0x002f;
        L_0x001f:
            r2 = r4.fingerprint;
            if (r2 != 0) goto L_0x003c;
        L_0x0023:
            r2 = r5.fingerprint;
            if (r2 != 0) goto L_0x002f;
        L_0x0027:
            r2 = r4.unknownFieldData;
            if (r2 != 0) goto L_0x0047;
        L_0x002b:
            r2 = r5.unknownFieldData;
            if (r2 == 0) goto L_0x0004;
        L_0x002f:
            r0 = r1;
            goto L_0x0004;
        L_0x0031:
            r2 = r4.resource;
            r3 = r5.resource;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x002f;
        L_0x003b:
            goto L_0x001f;
        L_0x003c:
            r2 = r4.fingerprint;
            r3 = r5.fingerprint;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x002f;
        L_0x0046:
            goto L_0x0027;
        L_0x0047:
            r2 = r4.unknownFieldData;
            r3 = r5.unknownFieldData;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x002f;
        L_0x0051:
            goto L_0x0004;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.analytics.containertag.proto.Serving.SupplementedResource.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i;
            int i2;
            int i3 = 0;
            if (this.supplemental == null) {
                i = 527;
            } else {
                i = 17;
                for (i2 = 0; i2 < this.supplemental.length; i2++) {
                    i = (this.supplemental[i2] == null ? 0 : this.supplemental[i2].hashCode()) + (i * 31);
                }
            }
            i2 = ((this.fingerprint == null ? 0 : this.fingerprint.hashCode()) + (((this.resource == null ? 0 : this.resource.hashCode()) + (i * 31)) * 31)) * 31;
            if (this.unknownFieldData != null) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i2 + i3;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.supplemental != null) {
                for (MessageNano writeMessage : this.supplemental) {
                    codedOutputByteBufferNano.writeMessage(1, writeMessage);
                }
            }
            if (this.resource != null) {
                codedOutputByteBufferNano.writeMessage(2, this.resource);
            }
            if (!this.fingerprint.equals("")) {
                codedOutputByteBufferNano.writeString(3, this.fingerprint);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int i = 0;
            if (this.supplemental != null) {
                Supplemental[] supplementalArr = this.supplemental;
                int i2 = 0;
                while (i2 < supplementalArr.length) {
                    int computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(1, supplementalArr[i2]) + i;
                    i2++;
                    i = computeMessageSize;
                }
            }
            if (this.resource != null) {
                i += CodedOutputByteBufferNano.computeMessageSize(2, this.resource);
            }
            if (!this.fingerprint.equals("")) {
                i += CodedOutputByteBufferNano.computeStringSize(3, this.fingerprint);
            }
            i += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i;
            return i;
        }

        public SupplementedResource mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                switch (readTag) {
                    case 0:
                        break;
                    case 10:
                        int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                        if (this.supplemental == null) {
                            readTag = 0;
                        } else {
                            readTag = this.supplemental.length;
                        }
                        Object obj = new Supplemental[(repeatedFieldArrayLength + readTag)];
                        if (this.supplemental != null) {
                            System.arraycopy(this.supplemental, 0, obj, 0, readTag);
                        }
                        this.supplemental = obj;
                        while (readTag < this.supplemental.length - 1) {
                            this.supplemental[readTag] = new Supplemental();
                            codedInputByteBufferNano.readMessage(this.supplemental[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.supplemental[readTag] = new Supplemental();
                        codedInputByteBufferNano.readMessage(this.supplemental[readTag]);
                        continue;
                    case 18:
                        this.resource = new Resource();
                        codedInputByteBufferNano.readMessage(this.resource);
                        continue;
                    case 26:
                        this.fingerprint = codedInputByteBufferNano.readString();
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, codedInputByteBufferNano, readTag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static SupplementedResource parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (SupplementedResource) MessageNano.mergeFrom(new SupplementedResource(), bArr);
        }

        public static SupplementedResource parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new SupplementedResource().mergeFrom(codedInputByteBufferNano);
        }
    }
}
