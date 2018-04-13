package com.google.analytics.containertag.proto;

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

public interface Debug {

    public static final class DataLayerEventEvaluationInfo extends ExtendableMessageNano {
        public static final DataLayerEventEvaluationInfo[] EMPTY_ARRAY = new DataLayerEventEvaluationInfo[0];
        public ResolvedFunctionCall[] results = ResolvedFunctionCall.EMPTY_ARRAY;
        public RuleEvaluationStepInfo rulesEvaluation = null;

        public final DataLayerEventEvaluationInfo clear() {
            this.rulesEvaluation = null;
            this.results = ResolvedFunctionCall.EMPTY_ARRAY;
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
            r2 = r5 instanceof com.google.analytics.containertag.proto.Debug.DataLayerEventEvaluationInfo;
            if (r2 != 0) goto L_0x000b;
        L_0x0009:
            r0 = r1;
            goto L_0x0004;
        L_0x000b:
            r5 = (com.google.analytics.containertag.proto.Debug.DataLayerEventEvaluationInfo) r5;
            r2 = r4.rulesEvaluation;
            if (r2 != 0) goto L_0x0029;
        L_0x0011:
            r2 = r5.rulesEvaluation;
            if (r2 != 0) goto L_0x0027;
        L_0x0015:
            r2 = r4.results;
            r3 = r5.results;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x0027;
        L_0x001f:
            r2 = r4.unknownFieldData;
            if (r2 != 0) goto L_0x0034;
        L_0x0023:
            r2 = r5.unknownFieldData;
            if (r2 == 0) goto L_0x0004;
        L_0x0027:
            r0 = r1;
            goto L_0x0004;
        L_0x0029:
            r2 = r4.rulesEvaluation;
            r3 = r5.rulesEvaluation;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0027;
        L_0x0033:
            goto L_0x0015;
        L_0x0034:
            r2 = r4.unknownFieldData;
            r3 = r5.unknownFieldData;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0027;
        L_0x003e:
            goto L_0x0004;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.analytics.containertag.proto.Debug.DataLayerEventEvaluationInfo.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i;
            int i2 = 0;
            int hashCode = (this.rulesEvaluation == null ? 0 : this.rulesEvaluation.hashCode()) + 527;
            if (this.results == null) {
                i = hashCode * 31;
            } else {
                i = hashCode;
                for (hashCode = 0; hashCode < this.results.length; hashCode++) {
                    i = (this.results[hashCode] == null ? 0 : this.results[hashCode].hashCode()) + (i * 31);
                }
            }
            hashCode = i * 31;
            if (this.unknownFieldData != null) {
                i2 = this.unknownFieldData.hashCode();
            }
            return hashCode + i2;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.rulesEvaluation != null) {
                codedOutputByteBufferNano.writeMessage(1, this.rulesEvaluation);
            }
            if (this.results != null) {
                for (MessageNano writeMessage : this.results) {
                    codedOutputByteBufferNano.writeMessage(2, writeMessage);
                }
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int computeMessageSize;
            int i = 0;
            if (this.rulesEvaluation != null) {
                computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(1, this.rulesEvaluation) + 0;
            } else {
                computeMessageSize = 0;
            }
            if (this.results != null) {
                ResolvedFunctionCall[] resolvedFunctionCallArr = this.results;
                while (i < resolvedFunctionCallArr.length) {
                    computeMessageSize += CodedOutputByteBufferNano.computeMessageSize(2, resolvedFunctionCallArr[i]);
                    i++;
                }
            }
            computeMessageSize += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = computeMessageSize;
            return computeMessageSize;
        }

        public DataLayerEventEvaluationInfo mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                switch (readTag) {
                    case 0:
                        break;
                    case 10:
                        this.rulesEvaluation = new RuleEvaluationStepInfo();
                        codedInputByteBufferNano.readMessage(this.rulesEvaluation);
                        continue;
                    case 18:
                        int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 18);
                        if (this.results == null) {
                            readTag = 0;
                        } else {
                            readTag = this.results.length;
                        }
                        Object obj = new ResolvedFunctionCall[(repeatedFieldArrayLength + readTag)];
                        if (this.results != null) {
                            System.arraycopy(this.results, 0, obj, 0, readTag);
                        }
                        this.results = obj;
                        while (readTag < this.results.length - 1) {
                            this.results[readTag] = new ResolvedFunctionCall();
                            codedInputByteBufferNano.readMessage(this.results[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.results[readTag] = new ResolvedFunctionCall();
                        codedInputByteBufferNano.readMessage(this.results[readTag]);
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

        public static DataLayerEventEvaluationInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (DataLayerEventEvaluationInfo) MessageNano.mergeFrom(new DataLayerEventEvaluationInfo(), bArr);
        }

        public static DataLayerEventEvaluationInfo parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new DataLayerEventEvaluationInfo().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class DebugEvents extends ExtendableMessageNano {
        public static final DebugEvents[] EMPTY_ARRAY = new DebugEvents[0];
        public EventInfo[] event = EventInfo.EMPTY_ARRAY;

        public final DebugEvents clear() {
            this.event = EventInfo.EMPTY_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DebugEvents)) {
                return false;
            }
            DebugEvents debugEvents = (DebugEvents) obj;
            if (Arrays.equals(this.event, debugEvents.event)) {
                if (this.unknownFieldData == null) {
                    if (debugEvents.unknownFieldData == null) {
                        return true;
                    }
                } else if (this.unknownFieldData.equals(debugEvents.unknownFieldData)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i;
            int i2;
            int i3 = 0;
            if (this.event == null) {
                i = 527;
            } else {
                i = 17;
                for (i2 = 0; i2 < this.event.length; i2++) {
                    i = (this.event[i2] == null ? 0 : this.event[i2].hashCode()) + (i * 31);
                }
            }
            i2 = i * 31;
            if (this.unknownFieldData != null) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i2 + i3;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.event != null) {
                for (MessageNano writeMessage : this.event) {
                    codedOutputByteBufferNano.writeMessage(1, writeMessage);
                }
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int i = 0;
            if (this.event != null) {
                EventInfo[] eventInfoArr = this.event;
                int i2 = 0;
                while (i2 < eventInfoArr.length) {
                    int computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(1, eventInfoArr[i2]) + i;
                    i2++;
                    i = computeMessageSize;
                }
            }
            i += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i;
            return i;
        }

        public DebugEvents mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                switch (readTag) {
                    case 0:
                        break;
                    case 10:
                        int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                        if (this.event == null) {
                            readTag = 0;
                        } else {
                            readTag = this.event.length;
                        }
                        Object obj = new EventInfo[(repeatedFieldArrayLength + readTag)];
                        if (this.event != null) {
                            System.arraycopy(this.event, 0, obj, 0, readTag);
                        }
                        this.event = obj;
                        while (readTag < this.event.length - 1) {
                            this.event[readTag] = new EventInfo();
                            codedInputByteBufferNano.readMessage(this.event[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.event[readTag] = new EventInfo();
                        codedInputByteBufferNano.readMessage(this.event[readTag]);
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

        public static DebugEvents parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (DebugEvents) MessageNano.mergeFrom(new DebugEvents(), bArr);
        }

        public static DebugEvents parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new DebugEvents().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class EventInfo extends ExtendableMessageNano {
        public static final EventInfo[] EMPTY_ARRAY = new EventInfo[0];
        public String containerId = "";
        public String containerVersion = "";
        public DataLayerEventEvaluationInfo dataLayerEventResult = null;
        public int eventType = 1;
        public String key = "";
        public MacroEvaluationInfo macroResult = null;

        public interface EventType {
            public static final int DATA_LAYER_EVENT = 1;
            public static final int MACRO_REFERENCE = 2;
        }

        public final EventInfo clear() {
            this.eventType = 1;
            this.containerVersion = "";
            this.containerId = "";
            this.key = "";
            this.macroResult = null;
            this.dataLayerEventResult = null;
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
            r2 = r5 instanceof com.google.analytics.containertag.proto.Debug.EventInfo;
            if (r2 != 0) goto L_0x000b;
        L_0x0009:
            r0 = r1;
            goto L_0x0004;
        L_0x000b:
            r5 = (com.google.analytics.containertag.proto.Debug.EventInfo) r5;
            r2 = r4.eventType;
            r3 = r5.eventType;
            if (r2 != r3) goto L_0x0043;
        L_0x0013:
            r2 = r4.containerVersion;
            if (r2 != 0) goto L_0x0045;
        L_0x0017:
            r2 = r5.containerVersion;
            if (r2 != 0) goto L_0x0043;
        L_0x001b:
            r2 = r4.containerId;
            if (r2 != 0) goto L_0x0050;
        L_0x001f:
            r2 = r5.containerId;
            if (r2 != 0) goto L_0x0043;
        L_0x0023:
            r2 = r4.key;
            if (r2 != 0) goto L_0x005b;
        L_0x0027:
            r2 = r5.key;
            if (r2 != 0) goto L_0x0043;
        L_0x002b:
            r2 = r4.macroResult;
            if (r2 != 0) goto L_0x0066;
        L_0x002f:
            r2 = r5.macroResult;
            if (r2 != 0) goto L_0x0043;
        L_0x0033:
            r2 = r4.dataLayerEventResult;
            if (r2 != 0) goto L_0x0071;
        L_0x0037:
            r2 = r5.dataLayerEventResult;
            if (r2 != 0) goto L_0x0043;
        L_0x003b:
            r2 = r4.unknownFieldData;
            if (r2 != 0) goto L_0x007c;
        L_0x003f:
            r2 = r5.unknownFieldData;
            if (r2 == 0) goto L_0x0004;
        L_0x0043:
            r0 = r1;
            goto L_0x0004;
        L_0x0045:
            r2 = r4.containerVersion;
            r3 = r5.containerVersion;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0043;
        L_0x004f:
            goto L_0x001b;
        L_0x0050:
            r2 = r4.containerId;
            r3 = r5.containerId;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0043;
        L_0x005a:
            goto L_0x0023;
        L_0x005b:
            r2 = r4.key;
            r3 = r5.key;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0043;
        L_0x0065:
            goto L_0x002b;
        L_0x0066:
            r2 = r4.macroResult;
            r3 = r5.macroResult;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0043;
        L_0x0070:
            goto L_0x0033;
        L_0x0071:
            r2 = r4.dataLayerEventResult;
            r3 = r5.dataLayerEventResult;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0043;
        L_0x007b:
            goto L_0x003b;
        L_0x007c:
            r2 = r4.unknownFieldData;
            r3 = r5.unknownFieldData;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0043;
        L_0x0086:
            goto L_0x0004;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.analytics.containertag.proto.Debug.EventInfo.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.dataLayerEventResult == null ? 0 : this.dataLayerEventResult.hashCode()) + (((this.macroResult == null ? 0 : this.macroResult.hashCode()) + (((this.key == null ? 0 : this.key.hashCode()) + (((this.containerId == null ? 0 : this.containerId.hashCode()) + (((this.containerVersion == null ? 0 : this.containerVersion.hashCode()) + ((this.eventType + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.eventType != 1) {
                codedOutputByteBufferNano.writeInt32(1, this.eventType);
            }
            if (!this.containerVersion.equals("")) {
                codedOutputByteBufferNano.writeString(2, this.containerVersion);
            }
            if (!this.containerId.equals("")) {
                codedOutputByteBufferNano.writeString(3, this.containerId);
            }
            if (!this.key.equals("")) {
                codedOutputByteBufferNano.writeString(4, this.key);
            }
            if (this.macroResult != null) {
                codedOutputByteBufferNano.writeMessage(6, this.macroResult);
            }
            if (this.dataLayerEventResult != null) {
                codedOutputByteBufferNano.writeMessage(7, this.dataLayerEventResult);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int i = 0;
            if (this.eventType != 1) {
                i = 0 + CodedOutputByteBufferNano.computeInt32Size(1, this.eventType);
            }
            if (!this.containerVersion.equals("")) {
                i += CodedOutputByteBufferNano.computeStringSize(2, this.containerVersion);
            }
            if (!this.containerId.equals("")) {
                i += CodedOutputByteBufferNano.computeStringSize(3, this.containerId);
            }
            if (!this.key.equals("")) {
                i += CodedOutputByteBufferNano.computeStringSize(4, this.key);
            }
            if (this.macroResult != null) {
                i += CodedOutputByteBufferNano.computeMessageSize(6, this.macroResult);
            }
            if (this.dataLayerEventResult != null) {
                i += CodedOutputByteBufferNano.computeMessageSize(7, this.dataLayerEventResult);
            }
            i += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i;
            return i;
        }

        public EventInfo mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                switch (readTag) {
                    case 0:
                        break;
                    case 8:
                        readTag = codedInputByteBufferNano.readInt32();
                        if (readTag != 1 && readTag != 2) {
                            this.eventType = 1;
                            break;
                        }
                        this.eventType = readTag;
                        continue;
                        break;
                    case 18:
                        this.containerVersion = codedInputByteBufferNano.readString();
                        continue;
                    case 26:
                        this.containerId = codedInputByteBufferNano.readString();
                        continue;
                    case 34:
                        this.key = codedInputByteBufferNano.readString();
                        continue;
                    case 50:
                        this.macroResult = new MacroEvaluationInfo();
                        codedInputByteBufferNano.readMessage(this.macroResult);
                        continue;
                    case 58:
                        this.dataLayerEventResult = new DataLayerEventEvaluationInfo();
                        codedInputByteBufferNano.readMessage(this.dataLayerEventResult);
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

        public static EventInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (EventInfo) MessageNano.mergeFrom(new EventInfo(), bArr);
        }

        public static EventInfo parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new EventInfo().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class MacroEvaluationInfo extends ExtendableMessageNano {
        public static final MacroEvaluationInfo[] EMPTY_ARRAY = new MacroEvaluationInfo[0];
        public static final Extension<MacroEvaluationInfo> macro = Extension.create(47497405, new TypeLiteral<MacroEvaluationInfo>() {
        });
        public ResolvedFunctionCall result = null;
        public RuleEvaluationStepInfo rulesEvaluation = null;

        public final MacroEvaluationInfo clear() {
            this.rulesEvaluation = null;
            this.result = null;
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
            r2 = r5 instanceof com.google.analytics.containertag.proto.Debug.MacroEvaluationInfo;
            if (r2 != 0) goto L_0x000b;
        L_0x0009:
            r0 = r1;
            goto L_0x0004;
        L_0x000b:
            r5 = (com.google.analytics.containertag.proto.Debug.MacroEvaluationInfo) r5;
            r2 = r4.rulesEvaluation;
            if (r2 != 0) goto L_0x0027;
        L_0x0011:
            r2 = r5.rulesEvaluation;
            if (r2 != 0) goto L_0x0025;
        L_0x0015:
            r2 = r4.result;
            if (r2 != 0) goto L_0x0032;
        L_0x0019:
            r2 = r5.result;
            if (r2 != 0) goto L_0x0025;
        L_0x001d:
            r2 = r4.unknownFieldData;
            if (r2 != 0) goto L_0x003d;
        L_0x0021:
            r2 = r5.unknownFieldData;
            if (r2 == 0) goto L_0x0004;
        L_0x0025:
            r0 = r1;
            goto L_0x0004;
        L_0x0027:
            r2 = r4.rulesEvaluation;
            r3 = r5.rulesEvaluation;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0025;
        L_0x0031:
            goto L_0x0015;
        L_0x0032:
            r2 = r4.result;
            r3 = r5.result;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0025;
        L_0x003c:
            goto L_0x001d;
        L_0x003d:
            r2 = r4.unknownFieldData;
            r3 = r5.unknownFieldData;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0025;
        L_0x0047:
            goto L_0x0004;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.analytics.containertag.proto.Debug.MacroEvaluationInfo.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.result == null ? 0 : this.result.hashCode()) + (((this.rulesEvaluation == null ? 0 : this.rulesEvaluation.hashCode()) + 527) * 31)) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.rulesEvaluation != null) {
                codedOutputByteBufferNano.writeMessage(1, this.rulesEvaluation);
            }
            if (this.result != null) {
                codedOutputByteBufferNano.writeMessage(3, this.result);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int i = 0;
            if (this.rulesEvaluation != null) {
                i = 0 + CodedOutputByteBufferNano.computeMessageSize(1, this.rulesEvaluation);
            }
            if (this.result != null) {
                i += CodedOutputByteBufferNano.computeMessageSize(3, this.result);
            }
            i += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i;
            return i;
        }

        public MacroEvaluationInfo mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                switch (readTag) {
                    case 0:
                        break;
                    case 10:
                        this.rulesEvaluation = new RuleEvaluationStepInfo();
                        codedInputByteBufferNano.readMessage(this.rulesEvaluation);
                        continue;
                    case 26:
                        this.result = new ResolvedFunctionCall();
                        codedInputByteBufferNano.readMessage(this.result);
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

        public static MacroEvaluationInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (MacroEvaluationInfo) MessageNano.mergeFrom(new MacroEvaluationInfo(), bArr);
        }

        public static MacroEvaluationInfo parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new MacroEvaluationInfo().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class ResolvedFunctionCall extends ExtendableMessageNano {
        public static final ResolvedFunctionCall[] EMPTY_ARRAY = new ResolvedFunctionCall[0];
        public String associatedRuleName = "";
        public ResolvedProperty[] properties = ResolvedProperty.EMPTY_ARRAY;
        public Value result = null;

        public final ResolvedFunctionCall clear() {
            this.properties = ResolvedProperty.EMPTY_ARRAY;
            this.result = null;
            this.associatedRuleName = "";
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
            r2 = r5 instanceof com.google.analytics.containertag.proto.Debug.ResolvedFunctionCall;
            if (r2 != 0) goto L_0x000b;
        L_0x0009:
            r0 = r1;
            goto L_0x0004;
        L_0x000b:
            r5 = (com.google.analytics.containertag.proto.Debug.ResolvedFunctionCall) r5;
            r2 = r4.properties;
            r3 = r5.properties;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x002f;
        L_0x0017:
            r2 = r4.result;
            if (r2 != 0) goto L_0x0031;
        L_0x001b:
            r2 = r5.result;
            if (r2 != 0) goto L_0x002f;
        L_0x001f:
            r2 = r4.associatedRuleName;
            if (r2 != 0) goto L_0x003c;
        L_0x0023:
            r2 = r5.associatedRuleName;
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
            r2 = r4.result;
            r3 = r5.result;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x002f;
        L_0x003b:
            goto L_0x001f;
        L_0x003c:
            r2 = r4.associatedRuleName;
            r3 = r5.associatedRuleName;
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.analytics.containertag.proto.Debug.ResolvedFunctionCall.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i;
            int i2;
            int i3 = 0;
            if (this.properties == null) {
                i = 527;
            } else {
                i = 17;
                for (i2 = 0; i2 < this.properties.length; i2++) {
                    i = (this.properties[i2] == null ? 0 : this.properties[i2].hashCode()) + (i * 31);
                }
            }
            i2 = ((this.associatedRuleName == null ? 0 : this.associatedRuleName.hashCode()) + (((this.result == null ? 0 : this.result.hashCode()) + (i * 31)) * 31)) * 31;
            if (this.unknownFieldData != null) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i2 + i3;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.properties != null) {
                for (MessageNano writeMessage : this.properties) {
                    codedOutputByteBufferNano.writeMessage(1, writeMessage);
                }
            }
            if (this.result != null) {
                codedOutputByteBufferNano.writeMessage(2, this.result);
            }
            if (!this.associatedRuleName.equals("")) {
                codedOutputByteBufferNano.writeString(3, this.associatedRuleName);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int i = 0;
            if (this.properties != null) {
                ResolvedProperty[] resolvedPropertyArr = this.properties;
                int i2 = 0;
                while (i2 < resolvedPropertyArr.length) {
                    int computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(1, resolvedPropertyArr[i2]) + i;
                    i2++;
                    i = computeMessageSize;
                }
            }
            if (this.result != null) {
                i += CodedOutputByteBufferNano.computeMessageSize(2, this.result);
            }
            if (!this.associatedRuleName.equals("")) {
                i += CodedOutputByteBufferNano.computeStringSize(3, this.associatedRuleName);
            }
            i += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i;
            return i;
        }

        public ResolvedFunctionCall mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                switch (readTag) {
                    case 0:
                        break;
                    case 10:
                        int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                        if (this.properties == null) {
                            readTag = 0;
                        } else {
                            readTag = this.properties.length;
                        }
                        Object obj = new ResolvedProperty[(repeatedFieldArrayLength + readTag)];
                        if (this.properties != null) {
                            System.arraycopy(this.properties, 0, obj, 0, readTag);
                        }
                        this.properties = obj;
                        while (readTag < this.properties.length - 1) {
                            this.properties[readTag] = new ResolvedProperty();
                            codedInputByteBufferNano.readMessage(this.properties[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.properties[readTag] = new ResolvedProperty();
                        codedInputByteBufferNano.readMessage(this.properties[readTag]);
                        continue;
                    case 18:
                        this.result = new Value();
                        codedInputByteBufferNano.readMessage(this.result);
                        continue;
                    case 26:
                        this.associatedRuleName = codedInputByteBufferNano.readString();
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

        public static ResolvedFunctionCall parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (ResolvedFunctionCall) MessageNano.mergeFrom(new ResolvedFunctionCall(), bArr);
        }

        public static ResolvedFunctionCall parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new ResolvedFunctionCall().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class ResolvedProperty extends ExtendableMessageNano {
        public static final ResolvedProperty[] EMPTY_ARRAY = new ResolvedProperty[0];
        public String key = "";
        public Value value = null;

        public final ResolvedProperty clear() {
            this.key = "";
            this.value = null;
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
            r2 = r5 instanceof com.google.analytics.containertag.proto.Debug.ResolvedProperty;
            if (r2 != 0) goto L_0x000b;
        L_0x0009:
            r0 = r1;
            goto L_0x0004;
        L_0x000b:
            r5 = (com.google.analytics.containertag.proto.Debug.ResolvedProperty) r5;
            r2 = r4.key;
            if (r2 != 0) goto L_0x0027;
        L_0x0011:
            r2 = r5.key;
            if (r2 != 0) goto L_0x0025;
        L_0x0015:
            r2 = r4.value;
            if (r2 != 0) goto L_0x0032;
        L_0x0019:
            r2 = r5.value;
            if (r2 != 0) goto L_0x0025;
        L_0x001d:
            r2 = r4.unknownFieldData;
            if (r2 != 0) goto L_0x003d;
        L_0x0021:
            r2 = r5.unknownFieldData;
            if (r2 == 0) goto L_0x0004;
        L_0x0025:
            r0 = r1;
            goto L_0x0004;
        L_0x0027:
            r2 = r4.key;
            r3 = r5.key;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0025;
        L_0x0031:
            goto L_0x0015;
        L_0x0032:
            r2 = r4.value;
            r3 = r5.value;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0025;
        L_0x003c:
            goto L_0x001d;
        L_0x003d:
            r2 = r4.unknownFieldData;
            r3 = r5.unknownFieldData;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0025;
        L_0x0047:
            goto L_0x0004;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.analytics.containertag.proto.Debug.ResolvedProperty.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.value == null ? 0 : this.value.hashCode()) + (((this.key == null ? 0 : this.key.hashCode()) + 527) * 31)) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.key.equals("")) {
                codedOutputByteBufferNano.writeString(1, this.key);
            }
            if (this.value != null) {
                codedOutputByteBufferNano.writeMessage(2, this.value);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int i = 0;
            if (!this.key.equals("")) {
                i = 0 + CodedOutputByteBufferNano.computeStringSize(1, this.key);
            }
            if (this.value != null) {
                i += CodedOutputByteBufferNano.computeMessageSize(2, this.value);
            }
            i += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i;
            return i;
        }

        public ResolvedProperty mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                switch (readTag) {
                    case 0:
                        break;
                    case 10:
                        this.key = codedInputByteBufferNano.readString();
                        continue;
                    case 18:
                        this.value = new Value();
                        codedInputByteBufferNano.readMessage(this.value);
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

        public static ResolvedProperty parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (ResolvedProperty) MessageNano.mergeFrom(new ResolvedProperty(), bArr);
        }

        public static ResolvedProperty parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new ResolvedProperty().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class ResolvedRule extends ExtendableMessageNano {
        public static final ResolvedRule[] EMPTY_ARRAY = new ResolvedRule[0];
        public ResolvedFunctionCall[] addMacros = ResolvedFunctionCall.EMPTY_ARRAY;
        public ResolvedFunctionCall[] addTags = ResolvedFunctionCall.EMPTY_ARRAY;
        public ResolvedFunctionCall[] negativePredicates = ResolvedFunctionCall.EMPTY_ARRAY;
        public ResolvedFunctionCall[] positivePredicates = ResolvedFunctionCall.EMPTY_ARRAY;
        public ResolvedFunctionCall[] removeMacros = ResolvedFunctionCall.EMPTY_ARRAY;
        public ResolvedFunctionCall[] removeTags = ResolvedFunctionCall.EMPTY_ARRAY;
        public Value result = null;

        public final ResolvedRule clear() {
            this.positivePredicates = ResolvedFunctionCall.EMPTY_ARRAY;
            this.negativePredicates = ResolvedFunctionCall.EMPTY_ARRAY;
            this.addTags = ResolvedFunctionCall.EMPTY_ARRAY;
            this.removeTags = ResolvedFunctionCall.EMPTY_ARRAY;
            this.addMacros = ResolvedFunctionCall.EMPTY_ARRAY;
            this.removeMacros = ResolvedFunctionCall.EMPTY_ARRAY;
            this.result = null;
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
            r2 = r5 instanceof com.google.analytics.containertag.proto.Debug.ResolvedRule;
            if (r2 != 0) goto L_0x000b;
        L_0x0009:
            r0 = r1;
            goto L_0x0004;
        L_0x000b:
            r5 = (com.google.analytics.containertag.proto.Debug.ResolvedRule) r5;
            r2 = r4.positivePredicates;
            r3 = r5.positivePredicates;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x0059;
        L_0x0017:
            r2 = r4.negativePredicates;
            r3 = r5.negativePredicates;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x0059;
        L_0x0021:
            r2 = r4.addTags;
            r3 = r5.addTags;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x0059;
        L_0x002b:
            r2 = r4.removeTags;
            r3 = r5.removeTags;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x0059;
        L_0x0035:
            r2 = r4.addMacros;
            r3 = r5.addMacros;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x0059;
        L_0x003f:
            r2 = r4.removeMacros;
            r3 = r5.removeMacros;
            r2 = java.util.Arrays.equals(r2, r3);
            if (r2 == 0) goto L_0x0059;
        L_0x0049:
            r2 = r4.result;
            if (r2 != 0) goto L_0x005b;
        L_0x004d:
            r2 = r5.result;
            if (r2 != 0) goto L_0x0059;
        L_0x0051:
            r2 = r4.unknownFieldData;
            if (r2 != 0) goto L_0x0066;
        L_0x0055:
            r2 = r5.unknownFieldData;
            if (r2 == 0) goto L_0x0004;
        L_0x0059:
            r0 = r1;
            goto L_0x0004;
        L_0x005b:
            r2 = r4.result;
            r3 = r5.result;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0059;
        L_0x0065:
            goto L_0x0051;
        L_0x0066:
            r2 = r4.unknownFieldData;
            r3 = r5.unknownFieldData;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0059;
        L_0x0070:
            goto L_0x0004;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.analytics.containertag.proto.Debug.ResolvedRule.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i;
            int i2;
            int i3 = 0;
            if (this.positivePredicates == null) {
                i = 527;
            } else {
                i = 17;
                for (i2 = 0; i2 < this.positivePredicates.length; i2++) {
                    i = (this.positivePredicates[i2] == null ? 0 : this.positivePredicates[i2].hashCode()) + (i * 31);
                }
            }
            if (this.negativePredicates == null) {
                i *= 31;
            } else {
                for (i2 = 0; i2 < this.negativePredicates.length; i2++) {
                    i = (this.negativePredicates[i2] == null ? 0 : this.negativePredicates[i2].hashCode()) + (i * 31);
                }
            }
            if (this.addTags == null) {
                i *= 31;
            } else {
                for (i2 = 0; i2 < this.addTags.length; i2++) {
                    i = (this.addTags[i2] == null ? 0 : this.addTags[i2].hashCode()) + (i * 31);
                }
            }
            if (this.removeTags == null) {
                i *= 31;
            } else {
                for (i2 = 0; i2 < this.removeTags.length; i2++) {
                    i = (this.removeTags[i2] == null ? 0 : this.removeTags[i2].hashCode()) + (i * 31);
                }
            }
            if (this.addMacros == null) {
                i *= 31;
            } else {
                for (i2 = 0; i2 < this.addMacros.length; i2++) {
                    i = (this.addMacros[i2] == null ? 0 : this.addMacros[i2].hashCode()) + (i * 31);
                }
            }
            if (this.removeMacros == null) {
                i *= 31;
            } else {
                for (i2 = 0; i2 < this.removeMacros.length; i2++) {
                    i = (this.removeMacros[i2] == null ? 0 : this.removeMacros[i2].hashCode()) + (i * 31);
                }
            }
            i2 = ((this.result == null ? 0 : this.result.hashCode()) + (i * 31)) * 31;
            if (this.unknownFieldData != null) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i2 + i3;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            int i = 0;
            if (this.positivePredicates != null) {
                for (MessageNano writeMessage : this.positivePredicates) {
                    codedOutputByteBufferNano.writeMessage(1, writeMessage);
                }
            }
            if (this.negativePredicates != null) {
                for (MessageNano writeMessage2 : this.negativePredicates) {
                    codedOutputByteBufferNano.writeMessage(2, writeMessage2);
                }
            }
            if (this.addTags != null) {
                for (MessageNano writeMessage22 : this.addTags) {
                    codedOutputByteBufferNano.writeMessage(3, writeMessage22);
                }
            }
            if (this.removeTags != null) {
                for (MessageNano writeMessage222 : this.removeTags) {
                    codedOutputByteBufferNano.writeMessage(4, writeMessage222);
                }
            }
            if (this.addMacros != null) {
                for (MessageNano writeMessage2222 : this.addMacros) {
                    codedOutputByteBufferNano.writeMessage(5, writeMessage2222);
                }
            }
            if (this.removeMacros != null) {
                ResolvedFunctionCall[] resolvedFunctionCallArr = this.removeMacros;
                int length = resolvedFunctionCallArr.length;
                while (i < length) {
                    codedOutputByteBufferNano.writeMessage(6, resolvedFunctionCallArr[i]);
                    i++;
                }
            }
            if (this.result != null) {
                codedOutputByteBufferNano.writeMessage(7, this.result);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            ResolvedFunctionCall[] resolvedFunctionCallArr;
            int i;
            int i2;
            int i3 = 0;
            if (this.positivePredicates != null) {
                resolvedFunctionCallArr = this.positivePredicates;
                i = 0;
                i2 = 0;
                while (i < resolvedFunctionCallArr.length) {
                    int computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(1, resolvedFunctionCallArr[i]) + i2;
                    i++;
                    i2 = computeMessageSize;
                }
            } else {
                i2 = 0;
            }
            if (this.negativePredicates != null) {
                resolvedFunctionCallArr = this.negativePredicates;
                i = 0;
                while (i < resolvedFunctionCallArr.length) {
                    computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(2, resolvedFunctionCallArr[i]) + i2;
                    i++;
                    i2 = computeMessageSize;
                }
            }
            if (this.addTags != null) {
                resolvedFunctionCallArr = this.addTags;
                i = 0;
                while (i < resolvedFunctionCallArr.length) {
                    computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(3, resolvedFunctionCallArr[i]) + i2;
                    i++;
                    i2 = computeMessageSize;
                }
            }
            if (this.removeTags != null) {
                resolvedFunctionCallArr = this.removeTags;
                i = 0;
                while (i < resolvedFunctionCallArr.length) {
                    computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(4, resolvedFunctionCallArr[i]) + i2;
                    i++;
                    i2 = computeMessageSize;
                }
            }
            if (this.addMacros != null) {
                resolvedFunctionCallArr = this.addMacros;
                i = 0;
                while (i < resolvedFunctionCallArr.length) {
                    computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(5, resolvedFunctionCallArr[i]) + i2;
                    i++;
                    i2 = computeMessageSize;
                }
            }
            if (this.removeMacros != null) {
                ResolvedFunctionCall[] resolvedFunctionCallArr2 = this.removeMacros;
                while (i3 < resolvedFunctionCallArr2.length) {
                    i2 += CodedOutputByteBufferNano.computeMessageSize(6, resolvedFunctionCallArr2[i3]);
                    i3++;
                }
            }
            if (this.result != null) {
                i2 += CodedOutputByteBufferNano.computeMessageSize(7, this.result);
            }
            i2 += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i2;
            return i2;
        }

        public ResolvedRule mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                int repeatedFieldArrayLength;
                Object obj;
                switch (readTag) {
                    case 0:
                        break;
                    case 10:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                        if (this.positivePredicates == null) {
                            readTag = 0;
                        } else {
                            readTag = this.positivePredicates.length;
                        }
                        obj = new ResolvedFunctionCall[(repeatedFieldArrayLength + readTag)];
                        if (this.positivePredicates != null) {
                            System.arraycopy(this.positivePredicates, 0, obj, 0, readTag);
                        }
                        this.positivePredicates = obj;
                        while (readTag < this.positivePredicates.length - 1) {
                            this.positivePredicates[readTag] = new ResolvedFunctionCall();
                            codedInputByteBufferNano.readMessage(this.positivePredicates[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.positivePredicates[readTag] = new ResolvedFunctionCall();
                        codedInputByteBufferNano.readMessage(this.positivePredicates[readTag]);
                        continue;
                    case 18:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 18);
                        if (this.negativePredicates == null) {
                            readTag = 0;
                        } else {
                            readTag = this.negativePredicates.length;
                        }
                        obj = new ResolvedFunctionCall[(repeatedFieldArrayLength + readTag)];
                        if (this.negativePredicates != null) {
                            System.arraycopy(this.negativePredicates, 0, obj, 0, readTag);
                        }
                        this.negativePredicates = obj;
                        while (readTag < this.negativePredicates.length - 1) {
                            this.negativePredicates[readTag] = new ResolvedFunctionCall();
                            codedInputByteBufferNano.readMessage(this.negativePredicates[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.negativePredicates[readTag] = new ResolvedFunctionCall();
                        codedInputByteBufferNano.readMessage(this.negativePredicates[readTag]);
                        continue;
                    case 26:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 26);
                        if (this.addTags == null) {
                            readTag = 0;
                        } else {
                            readTag = this.addTags.length;
                        }
                        obj = new ResolvedFunctionCall[(repeatedFieldArrayLength + readTag)];
                        if (this.addTags != null) {
                            System.arraycopy(this.addTags, 0, obj, 0, readTag);
                        }
                        this.addTags = obj;
                        while (readTag < this.addTags.length - 1) {
                            this.addTags[readTag] = new ResolvedFunctionCall();
                            codedInputByteBufferNano.readMessage(this.addTags[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.addTags[readTag] = new ResolvedFunctionCall();
                        codedInputByteBufferNano.readMessage(this.addTags[readTag]);
                        continue;
                    case 34:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 34);
                        if (this.removeTags == null) {
                            readTag = 0;
                        } else {
                            readTag = this.removeTags.length;
                        }
                        obj = new ResolvedFunctionCall[(repeatedFieldArrayLength + readTag)];
                        if (this.removeTags != null) {
                            System.arraycopy(this.removeTags, 0, obj, 0, readTag);
                        }
                        this.removeTags = obj;
                        while (readTag < this.removeTags.length - 1) {
                            this.removeTags[readTag] = new ResolvedFunctionCall();
                            codedInputByteBufferNano.readMessage(this.removeTags[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.removeTags[readTag] = new ResolvedFunctionCall();
                        codedInputByteBufferNano.readMessage(this.removeTags[readTag]);
                        continue;
                    case 42:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 42);
                        if (this.addMacros == null) {
                            readTag = 0;
                        } else {
                            readTag = this.addMacros.length;
                        }
                        obj = new ResolvedFunctionCall[(repeatedFieldArrayLength + readTag)];
                        if (this.addMacros != null) {
                            System.arraycopy(this.addMacros, 0, obj, 0, readTag);
                        }
                        this.addMacros = obj;
                        while (readTag < this.addMacros.length - 1) {
                            this.addMacros[readTag] = new ResolvedFunctionCall();
                            codedInputByteBufferNano.readMessage(this.addMacros[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.addMacros[readTag] = new ResolvedFunctionCall();
                        codedInputByteBufferNano.readMessage(this.addMacros[readTag]);
                        continue;
                    case 50:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 50);
                        if (this.removeMacros == null) {
                            readTag = 0;
                        } else {
                            readTag = this.removeMacros.length;
                        }
                        obj = new ResolvedFunctionCall[(repeatedFieldArrayLength + readTag)];
                        if (this.removeMacros != null) {
                            System.arraycopy(this.removeMacros, 0, obj, 0, readTag);
                        }
                        this.removeMacros = obj;
                        while (readTag < this.removeMacros.length - 1) {
                            this.removeMacros[readTag] = new ResolvedFunctionCall();
                            codedInputByteBufferNano.readMessage(this.removeMacros[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.removeMacros[readTag] = new ResolvedFunctionCall();
                        codedInputByteBufferNano.readMessage(this.removeMacros[readTag]);
                        continue;
                    case 58:
                        this.result = new Value();
                        codedInputByteBufferNano.readMessage(this.result);
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

        public static ResolvedRule parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (ResolvedRule) MessageNano.mergeFrom(new ResolvedRule(), bArr);
        }

        public static ResolvedRule parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new ResolvedRule().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class RuleEvaluationStepInfo extends ExtendableMessageNano {
        public static final RuleEvaluationStepInfo[] EMPTY_ARRAY = new RuleEvaluationStepInfo[0];
        public ResolvedFunctionCall[] enabledFunctions = ResolvedFunctionCall.EMPTY_ARRAY;
        public ResolvedRule[] rules = ResolvedRule.EMPTY_ARRAY;

        public final RuleEvaluationStepInfo clear() {
            this.rules = ResolvedRule.EMPTY_ARRAY;
            this.enabledFunctions = ResolvedFunctionCall.EMPTY_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RuleEvaluationStepInfo)) {
                return false;
            }
            RuleEvaluationStepInfo ruleEvaluationStepInfo = (RuleEvaluationStepInfo) obj;
            if (Arrays.equals(this.rules, ruleEvaluationStepInfo.rules) && Arrays.equals(this.enabledFunctions, ruleEvaluationStepInfo.enabledFunctions)) {
                if (this.unknownFieldData == null) {
                    if (ruleEvaluationStepInfo.unknownFieldData == null) {
                        return true;
                    }
                } else if (this.unknownFieldData.equals(ruleEvaluationStepInfo.unknownFieldData)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i;
            int i2;
            int i3 = 0;
            if (this.rules == null) {
                i = 527;
            } else {
                i = 17;
                for (i2 = 0; i2 < this.rules.length; i2++) {
                    i = (this.rules[i2] == null ? 0 : this.rules[i2].hashCode()) + (i * 31);
                }
            }
            if (this.enabledFunctions == null) {
                i *= 31;
            } else {
                for (i2 = 0; i2 < this.enabledFunctions.length; i2++) {
                    i = (this.enabledFunctions[i2] == null ? 0 : this.enabledFunctions[i2].hashCode()) + (i * 31);
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
            if (this.rules != null) {
                for (MessageNano writeMessage : this.rules) {
                    codedOutputByteBufferNano.writeMessage(1, writeMessage);
                }
            }
            if (this.enabledFunctions != null) {
                ResolvedFunctionCall[] resolvedFunctionCallArr = this.enabledFunctions;
                int length = resolvedFunctionCallArr.length;
                while (i < length) {
                    codedOutputByteBufferNano.writeMessage(2, resolvedFunctionCallArr[i]);
                    i++;
                }
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, codedOutputByteBufferNano);
        }

        public int getSerializedSize() {
            int i;
            int i2 = 0;
            if (this.rules != null) {
                ResolvedRule[] resolvedRuleArr = this.rules;
                int i3 = 0;
                i = 0;
                while (i3 < resolvedRuleArr.length) {
                    int computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(1, resolvedRuleArr[i3]) + i;
                    i3++;
                    i = computeMessageSize;
                }
            } else {
                i = 0;
            }
            if (this.enabledFunctions != null) {
                ResolvedFunctionCall[] resolvedFunctionCallArr = this.enabledFunctions;
                while (i2 < resolvedFunctionCallArr.length) {
                    i += CodedOutputByteBufferNano.computeMessageSize(2, resolvedFunctionCallArr[i2]);
                    i2++;
                }
            }
            i += WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = i;
            return i;
        }

        public RuleEvaluationStepInfo mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                int repeatedFieldArrayLength;
                Object obj;
                switch (readTag) {
                    case 0:
                        break;
                    case 10:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                        if (this.rules == null) {
                            readTag = 0;
                        } else {
                            readTag = this.rules.length;
                        }
                        obj = new ResolvedRule[(repeatedFieldArrayLength + readTag)];
                        if (this.rules != null) {
                            System.arraycopy(this.rules, 0, obj, 0, readTag);
                        }
                        this.rules = obj;
                        while (readTag < this.rules.length - 1) {
                            this.rules[readTag] = new ResolvedRule();
                            codedInputByteBufferNano.readMessage(this.rules[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.rules[readTag] = new ResolvedRule();
                        codedInputByteBufferNano.readMessage(this.rules[readTag]);
                        continue;
                    case 18:
                        repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 18);
                        if (this.enabledFunctions == null) {
                            readTag = 0;
                        } else {
                            readTag = this.enabledFunctions.length;
                        }
                        obj = new ResolvedFunctionCall[(repeatedFieldArrayLength + readTag)];
                        if (this.enabledFunctions != null) {
                            System.arraycopy(this.enabledFunctions, 0, obj, 0, readTag);
                        }
                        this.enabledFunctions = obj;
                        while (readTag < this.enabledFunctions.length - 1) {
                            this.enabledFunctions[readTag] = new ResolvedFunctionCall();
                            codedInputByteBufferNano.readMessage(this.enabledFunctions[readTag]);
                            codedInputByteBufferNano.readTag();
                            readTag++;
                        }
                        this.enabledFunctions[readTag] = new ResolvedFunctionCall();
                        codedInputByteBufferNano.readMessage(this.enabledFunctions[readTag]);
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

        public static RuleEvaluationStepInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (RuleEvaluationStepInfo) MessageNano.mergeFrom(new RuleEvaluationStepInfo(), bArr);
        }

        public static RuleEvaluationStepInfo parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new RuleEvaluationStepInfo().mergeFrom(codedInputByteBufferNano);
        }
    }
}
