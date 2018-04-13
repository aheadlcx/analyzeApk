package com.google.tagmanager.proto;

import com.google.analytics.containertag.proto.Serving.SupplementedResource;
import com.google.tagmanager.protobuf.nano.CodedInputByteBufferNano;
import com.google.tagmanager.protobuf.nano.CodedOutputByteBufferNano;
import com.google.tagmanager.protobuf.nano.ExtendableMessageNano;
import com.google.tagmanager.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.tagmanager.protobuf.nano.MessageNano;
import com.google.tagmanager.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.ArrayList;

public interface Resource {

    public static final class ResourceWithMetadata extends ExtendableMessageNano {
        public static final ResourceWithMetadata[] EMPTY_ARRAY = new ResourceWithMetadata[0];
        public com.google.analytics.containertag.proto.Serving.Resource resource = null;
        public SupplementedResource supplementedResource = null;
        public long timeStamp = 0;

        public final ResourceWithMetadata clear() {
            this.timeStamp = 0;
            this.resource = null;
            this.supplementedResource = null;
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
            r2 = r7 instanceof com.google.tagmanager.proto.Resource.ResourceWithMetadata;
            if (r2 != 0) goto L_0x000b;
        L_0x0009:
            r0 = r1;
            goto L_0x0004;
        L_0x000b:
            r7 = (com.google.tagmanager.proto.Resource.ResourceWithMetadata) r7;
            r2 = r6.timeStamp;
            r4 = r7.timeStamp;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 != 0) goto L_0x002d;
        L_0x0015:
            r2 = r6.resource;
            if (r2 != 0) goto L_0x002f;
        L_0x0019:
            r2 = r7.resource;
            if (r2 != 0) goto L_0x002d;
        L_0x001d:
            r2 = r6.supplementedResource;
            if (r2 != 0) goto L_0x003a;
        L_0x0021:
            r2 = r7.supplementedResource;
            if (r2 != 0) goto L_0x002d;
        L_0x0025:
            r2 = r6.unknownFieldData;
            if (r2 != 0) goto L_0x0045;
        L_0x0029:
            r2 = r7.unknownFieldData;
            if (r2 == 0) goto L_0x0004;
        L_0x002d:
            r0 = r1;
            goto L_0x0004;
        L_0x002f:
            r2 = r6.resource;
            r3 = r7.resource;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x002d;
        L_0x0039:
            goto L_0x001d;
        L_0x003a:
            r2 = r6.supplementedResource;
            r3 = r7.supplementedResource;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x002d;
        L_0x0044:
            goto L_0x0025;
        L_0x0045:
            r2 = r6.unknownFieldData;
            r3 = r7.unknownFieldData;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x002d;
        L_0x004f:
            goto L_0x0004;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.tagmanager.proto.Resource.ResourceWithMetadata.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.supplementedResource == null ? 0 : this.supplementedResource.hashCode()) + (((this.resource == null ? 0 : this.resource.hashCode()) + ((((int) (this.timeStamp ^ (this.timeStamp >>> 32))) + 527) * 31)) * 31)) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeInt64(1, this.timeStamp);
            if (this.resource != null) {
                codedOutputByteBufferNano.writeMessage(2, this.resource);
            }
            if (this.supplementedResource != null) {
                codedOutputByteBufferNano.writeMessage(3, this.supplementedResource);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int computeInt64Size = 0 + CodedOutputByteBufferNano.computeInt64Size(1, this.timeStamp);
            if (this.resource != null) {
                computeInt64Size += CodedOutputByteBufferNano.computeMessageSize(2, this.resource);
            }
            if (this.supplementedResource != null) {
                computeInt64Size += CodedOutputByteBufferNano.computeMessageSize(3, this.supplementedResource);
            }
            computeInt64Size += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = computeInt64Size;
            return computeInt64Size;
        }

        public ResourceWithMetadata mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                switch (readTag) {
                    case 0:
                        break;
                    case 8:
                        this.timeStamp = codedInputByteBufferNano.readInt64();
                        continue;
                    case 18:
                        this.resource = new com.google.analytics.containertag.proto.Serving.Resource();
                        codedInputByteBufferNano.readMessage(this.resource);
                        continue;
                    case 26:
                        this.supplementedResource = new SupplementedResource();
                        codedInputByteBufferNano.readMessage(this.supplementedResource);
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

        public static ResourceWithMetadata parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (ResourceWithMetadata) MessageNano.mergeFrom(new ResourceWithMetadata(), bArr);
        }

        public static ResourceWithMetadata parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new ResourceWithMetadata().mergeFrom(codedInputByteBufferNano);
        }
    }
}
