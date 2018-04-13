package com.google.analytics.midtier.proto.containertag;

import com.google.tagmanager.protobuf.nano.CodedInputByteBufferNano;
import com.google.tagmanager.protobuf.nano.CodedOutputByteBufferNano;
import com.google.tagmanager.protobuf.nano.ExtendableMessageNano;
import com.google.tagmanager.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.tagmanager.protobuf.nano.MessageNano;
import com.google.tagmanager.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.ArrayList;

public interface TypeSystem {

    public static final class Value extends ExtendableMessageNano {
        public static final Value[] EMPTY_ARRAY = new Value[0];
        public boolean boolean_ = false;
        public boolean containsReferences = false;
        public int[] escaping = WireFormatNano.EMPTY_INT_ARRAY;
        public String functionId = "";
        public long integer = 0;
        public Value[] listItem = EMPTY_ARRAY;
        public String macroReference = "";
        public Value[] mapKey = EMPTY_ARRAY;
        public Value[] mapValue = EMPTY_ARRAY;
        public String string = "";
        public String tagReference = "";
        public Value[] templateToken = EMPTY_ARRAY;
        public int type = 1;

        public interface Escaping {
            public static final int CONVERT_JS_VALUE_TO_EXPRESSION = 16;
            public static final int ESCAPE_CSS_STRING = 10;
            public static final int ESCAPE_HTML = 1;
            public static final int ESCAPE_HTML_ATTRIBUTE = 3;
            public static final int ESCAPE_HTML_ATTRIBUTE_NOSPACE = 4;
            public static final int ESCAPE_HTML_RCDATA = 2;
            public static final int ESCAPE_JS_REGEX = 9;
            public static final int ESCAPE_JS_STRING = 7;
            public static final int ESCAPE_JS_VALUE = 8;
            public static final int ESCAPE_URI = 12;
            public static final int FILTER_CSS_VALUE = 11;
            public static final int FILTER_HTML_ATTRIBUTES = 6;
            public static final int FILTER_HTML_ELEMENT_NAME = 5;
            public static final int FILTER_NORMALIZE_URI = 14;
            public static final int NORMALIZE_URI = 13;
            public static final int NO_AUTOESCAPE = 15;
            public static final int TEXT = 17;
        }

        public interface Type {
            public static final int BOOLEAN = 8;
            public static final int FUNCTION_ID = 5;
            public static final int INTEGER = 6;
            public static final int LIST = 2;
            public static final int MACRO_REFERENCE = 4;
            public static final int MAP = 3;
            public static final int STRING = 1;
            public static final int TAG_REFERENCE = 9;
            public static final int TEMPLATE = 7;
        }

        public final Value clear() {
            this.type = 1;
            this.string = "";
            this.listItem = EMPTY_ARRAY;
            this.mapKey = EMPTY_ARRAY;
            this.mapValue = EMPTY_ARRAY;
            this.macroReference = "";
            this.functionId = "";
            this.integer = 0;
            this.boolean_ = false;
            this.templateToken = EMPTY_ARRAY;
            this.tagReference = "";
            this.escaping = WireFormatNano.EMPTY_INT_ARRAY;
            this.containsReferences = false;
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
            r2 = r7 instanceof com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
            if (r2 != 0) goto L_0x000b;
        L_0x0009:
            r0 = r1;
            goto L_0x0004;
        L_0x000b:
            r7 = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value) r7;
            r2 = r6.type;
            r3 = r7.type;
            if (r2 != r3) goto L_0x0081;
        L_0x0013:
            r2 = r6.string;
            if (r2 != 0) goto L_0x0083;
        L_0x0017:
            r2 = r7.string;
            if (r2 != 0) goto L_0x0081;
        L_0x001b:
            r2 = r6.listItem;
            r3 = r7.listItem;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x0081;
        L_0x0025:
            r2 = r6.mapKey;
            r3 = r7.mapKey;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x0081;
        L_0x002f:
            r2 = r6.mapValue;
            r3 = r7.mapValue;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x0081;
        L_0x0039:
            r2 = r6.macroReference;
            if (r2 != 0) goto L_0x008e;
        L_0x003d:
            r2 = r7.macroReference;
            if (r2 != 0) goto L_0x0081;
        L_0x0041:
            r2 = r6.functionId;
            if (r2 != 0) goto L_0x0099;
        L_0x0045:
            r2 = r7.functionId;
            if (r2 != 0) goto L_0x0081;
        L_0x0049:
            r2 = r6.integer;
            r4 = r7.integer;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 != 0) goto L_0x0081;
        L_0x0051:
            r2 = r6.boolean_;
            r3 = r7.boolean_;
            if (r2 != r3) goto L_0x0081;
        L_0x0057:
            r2 = r6.templateToken;
            r3 = r7.templateToken;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x0081;
        L_0x0061:
            r2 = r6.tagReference;
            if (r2 != 0) goto L_0x00a4;
        L_0x0065:
            r2 = r7.tagReference;
            if (r2 != 0) goto L_0x0081;
        L_0x0069:
            r2 = r6.escaping;
            r3 = r7.escaping;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x0081;
        L_0x0073:
            r2 = r6.containsReferences;
            r3 = r7.containsReferences;
            if (r2 != r3) goto L_0x0081;
        L_0x0079:
            r2 = r6.unknownFieldData;
            if (r2 != 0) goto L_0x00af;
        L_0x007d:
            r2 = r7.unknownFieldData;
            if (r2 == 0) goto L_0x0004;
        L_0x0081:
            r0 = r1;
            goto L_0x0004;
        L_0x0083:
            r2 = r6.string;
            r3 = r7.string;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0081;
        L_0x008d:
            goto L_0x001b;
        L_0x008e:
            r2 = r6.macroReference;
            r3 = r7.macroReference;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0081;
        L_0x0098:
            goto L_0x0041;
        L_0x0099:
            r2 = r6.functionId;
            r3 = r7.functionId;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0081;
        L_0x00a3:
            goto L_0x0049;
        L_0x00a4:
            r2 = r6.tagReference;
            r3 = r7.tagReference;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0081;
        L_0x00ae:
            goto L_0x0069;
        L_0x00af:
            r2 = r6.unknownFieldData;
            r3 = r7.unknownFieldData;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0081;
        L_0x00b9:
            goto L_0x0004;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.analytics.midtier.proto.containertag.TypeSystem.Value.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i;
            int i2 = 1;
            int i3 = 0;
            int hashCode = (this.string == null ? 0 : this.string.hashCode()) + ((this.type + 527) * 31);
            if (this.listItem == null) {
                i = hashCode * 31;
            } else {
                i = hashCode;
                for (hashCode = 0; hashCode < this.listItem.length; hashCode++) {
                    i = (this.listItem[hashCode] == null ? 0 : this.listItem[hashCode].hashCode()) + (i * 31);
                }
            }
            if (this.mapKey == null) {
                i *= 31;
            } else {
                for (hashCode = 0; hashCode < this.mapKey.length; hashCode++) {
                    i = (this.mapKey[hashCode] == null ? 0 : this.mapKey[hashCode].hashCode()) + (i * 31);
                }
            }
            if (this.mapValue == null) {
                i *= 31;
            } else {
                for (hashCode = 0; hashCode < this.mapValue.length; hashCode++) {
                    i = (this.mapValue[hashCode] == null ? 0 : this.mapValue[hashCode].hashCode()) + (i * 31);
                }
            }
            i = ((((this.functionId == null ? 0 : this.functionId.hashCode()) + (((this.macroReference == null ? 0 : this.macroReference.hashCode()) + (i * 31)) * 31)) * 31) + ((int) (this.integer ^ (this.integer >>> 32)))) * 31;
            if (this.boolean_) {
                hashCode = 1;
            } else {
                hashCode = 2;
            }
            hashCode += i;
            if (this.templateToken == null) {
                i = hashCode * 31;
            } else {
                i = hashCode;
                for (hashCode = 0; hashCode < this.templateToken.length; hashCode++) {
                    i = (this.templateToken[hashCode] == null ? 0 : this.templateToken[hashCode].hashCode()) + (i * 31);
                }
            }
            hashCode = (this.tagReference == null ? 0 : this.tagReference.hashCode()) + (i * 31);
            if (this.escaping == null) {
                i = hashCode * 31;
            } else {
                i = hashCode;
                for (int i4 : this.escaping) {
                    i = (i * 31) + i4;
                }
            }
            hashCode = i * 31;
            if (!this.containsReferences) {
                i2 = 2;
            }
            hashCode = (hashCode + i2) * 31;
            if (this.unknownFieldData != null) {
                i3 = this.unknownFieldData.hashCode();
            }
            return hashCode + i3;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            int i = 0;
            codedOutputByteBufferNano.writeInt32(1, this.type);
            if (!this.string.equals("")) {
                codedOutputByteBufferNano.writeString(2, this.string);
            }
            if (this.listItem != null) {
                for (MessageNano writeMessage : this.listItem) {
                    codedOutputByteBufferNano.writeMessage(3, writeMessage);
                }
            }
            if (this.mapKey != null) {
                for (MessageNano writeMessage2 : this.mapKey) {
                    codedOutputByteBufferNano.writeMessage(4, writeMessage2);
                }
            }
            if (this.mapValue != null) {
                for (MessageNano writeMessage22 : this.mapValue) {
                    codedOutputByteBufferNano.writeMessage(5, writeMessage22);
                }
            }
            if (!this.macroReference.equals("")) {
                codedOutputByteBufferNano.writeString(6, this.macroReference);
            }
            if (!this.functionId.equals("")) {
                codedOutputByteBufferNano.writeString(7, this.functionId);
            }
            if (this.integer != 0) {
                codedOutputByteBufferNano.writeInt64(8, this.integer);
            }
            if (this.containsReferences) {
                codedOutputByteBufferNano.writeBool(9, this.containsReferences);
            }
            if (this.escaping != null && this.escaping.length > 0) {
                for (int writeInt32 : this.escaping) {
                    codedOutputByteBufferNano.writeInt32(10, writeInt32);
                }
            }
            if (this.templateToken != null) {
                Value[] valueArr = this.templateToken;
                int length = valueArr.length;
                while (i < length) {
                    codedOutputByteBufferNano.writeMessage(11, valueArr[i]);
                    i++;
                }
            }
            if (this.boolean_) {
                codedOutputByteBufferNano.writeBool(12, this.boolean_);
            }
            if (!this.tagReference.equals("")) {
                codedOutputByteBufferNano.writeString(13, this.tagReference);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            Value[] valueArr;
            int i;
            int i2 = 0;
            int computeInt32Size = CodedOutputByteBufferNano.computeInt32Size(1, this.type) + 0;
            if (!this.string.equals("")) {
                computeInt32Size += CodedOutputByteBufferNano.computeStringSize(2, this.string);
            }
            if (this.listItem != null) {
                valueArr = this.listItem;
                i = 0;
                while (i < valueArr.length) {
                    int computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(3, valueArr[i]) + computeInt32Size;
                    i++;
                    computeInt32Size = computeMessageSize;
                }
            }
            if (this.mapKey != null) {
                valueArr = this.mapKey;
                i = 0;
                while (i < valueArr.length) {
                    computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(4, valueArr[i]) + computeInt32Size;
                    i++;
                    computeInt32Size = computeMessageSize;
                }
            }
            if (this.mapValue != null) {
                valueArr = this.mapValue;
                i = 0;
                while (i < valueArr.length) {
                    computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(5, valueArr[i]) + computeInt32Size;
                    i++;
                    computeInt32Size = computeMessageSize;
                }
            }
            if (!this.macroReference.equals("")) {
                computeInt32Size += CodedOutputByteBufferNano.computeStringSize(6, this.macroReference);
            }
            if (!this.functionId.equals("")) {
                computeInt32Size += CodedOutputByteBufferNano.computeStringSize(7, this.functionId);
            }
            if (this.integer != 0) {
                computeInt32Size += CodedOutputByteBufferNano.computeInt64Size(8, this.integer);
            }
            if (this.containsReferences) {
                computeInt32Size += CodedOutputByteBufferNano.computeBoolSize(9, this.containsReferences);
            }
            if (this.escaping != null && this.escaping.length > 0) {
                computeMessageSize = 0;
                for (int computeInt32SizeNoTag : this.escaping) {
                    computeMessageSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(computeInt32SizeNoTag);
                }
                computeInt32Size = (computeInt32Size + computeMessageSize) + (this.escaping.length * 1);
            }
            if (this.templateToken != null) {
                Value[] valueArr2 = this.templateToken;
                while (i2 < valueArr2.length) {
                    i = CodedOutputByteBufferNano.computeMessageSize(11, valueArr2[i2]) + computeInt32Size;
                    i2++;
                    computeInt32Size = i;
                }
            }
            if (this.boolean_) {
                computeInt32Size += CodedOutputByteBufferNano.computeBoolSize(12, this.boolean_);
            }
            if (!this.tagReference.equals("")) {
                computeInt32Size += CodedOutputByteBufferNano.computeStringSize(13, this.tagReference);
            }
            computeInt32Size += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = computeInt32Size;
            return computeInt32Size;
        }

        public Value mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                int repeatedFieldArrayLength;
                Object obj;
                switch (readTag) {
                    case 0:
                        break;
                    case 8:
                        readTag = codedInputByteBufferNano.readInt32();
                        if (readTag != 1 && readTag != 2 && readTag != 3 && readTag != 4 && readTag != 5 && readTag != 6 && readTag != 7 && readTag != 8 && readTag != 9) {
                            this.type = 1;
                            break;
                        }
                        this.type = readTag;
                        continue;
                        break;
                    case 18:
                        this.string = codedInputByteBufferNano.readString();
                        continue;
                    case 26:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 26);
                        if (this.listItem == null) {
                            readTag = 0;
                        } else {
                            readTag = this.listItem.length;
                        }
                        obj = new Value[(repeatedFieldArrayLength + readTag)];
                        if (this.listItem != null) {
                            System.arraycopy(this.listItem, 0, obj, 0, readTag);
                        }
                        this.listItem = obj;
                        while (readTag < this.listItem.length - 1) {
                            this.listItem[readTag] = new Value();
                            codedInputByteBufferNano.readMessage(this.listItem[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.listItem[readTag] = new Value();
                        codedInputByteBufferNano.readMessage(this.listItem[readTag]);
                        continue;
                    case 34:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 34);
                        if (this.mapKey == null) {
                            readTag = 0;
                        } else {
                            readTag = this.mapKey.length;
                        }
                        obj = new Value[(repeatedFieldArrayLength + readTag)];
                        if (this.mapKey != null) {
                            System.arraycopy(this.mapKey, 0, obj, 0, readTag);
                        }
                        this.mapKey = obj;
                        while (readTag < this.mapKey.length - 1) {
                            this.mapKey[readTag] = new Value();
                            codedInputByteBufferNano.readMessage(this.mapKey[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.mapKey[readTag] = new Value();
                        codedInputByteBufferNano.readMessage(this.mapKey[readTag]);
                        continue;
                    case 42:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 42);
                        if (this.mapValue == null) {
                            readTag = 0;
                        } else {
                            readTag = this.mapValue.length;
                        }
                        obj = new Value[(repeatedFieldArrayLength + readTag)];
                        if (this.mapValue != null) {
                            System.arraycopy(this.mapValue, 0, obj, 0, readTag);
                        }
                        this.mapValue = obj;
                        while (readTag < this.mapValue.length - 1) {
                            this.mapValue[readTag] = new Value();
                            codedInputByteBufferNano.readMessage(this.mapValue[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.mapValue[readTag] = new Value();
                        codedInputByteBufferNano.readMessage(this.mapValue[readTag]);
                        continue;
                    case 50:
                        this.macroReference = codedInputByteBufferNano.readString();
                        continue;
                    case 58:
                        this.functionId = codedInputByteBufferNano.readString();
                        continue;
                    case 64:
                        this.integer = codedInputByteBufferNano.readInt64();
                        continue;
                    case 72:
                        this.containsReferences = codedInputByteBufferNano.readBool();
                        continue;
                    case 80:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 80);
                        readTag = this.escaping.length;
                        obj = new int[(repeatedFieldArrayLength + readTag)];
                        System.arraycopy(this.escaping, 0, obj, 0, readTag);
                        this.escaping = obj;
                        while (readTag < this.escaping.length - 1) {
                            this.escaping[readTag] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.escaping[readTag] = codedInputByteBufferNano.readInt32();
                        continue;
                    case 90:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 90);
                        if (this.templateToken == null) {
                            readTag = 0;
                        } else {
                            readTag = this.templateToken.length;
                        }
                        obj = new Value[(repeatedFieldArrayLength + readTag)];
                        if (this.templateToken != null) {
                            System.arraycopy(this.templateToken, 0, obj, 0, readTag);
                        }
                        this.templateToken = obj;
                        while (readTag < this.templateToken.length - 1) {
                            this.templateToken[readTag] = new Value();
                            codedInputByteBufferNano.readMessage(this.templateToken[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.templateToken[readTag] = new Value();
                        codedInputByteBufferNano.readMessage(this.templateToken[readTag]);
                        continue;
                    case 96:
                        this.boolean_ = codedInputByteBufferNano.readBool();
                        continue;
                    case 106:
                        this.tagReference = codedInputByteBufferNano.readString();
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

        public static Value parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (Value) MessageNano.mergeFrom(new Value(), bArr);
        }

        public static Value parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new Value().mergeFrom(codedInputByteBufferNano);
        }
    }
}
