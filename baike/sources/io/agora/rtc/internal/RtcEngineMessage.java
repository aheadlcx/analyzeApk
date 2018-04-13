package io.agora.rtc.internal;

import io.agora.rtc.IRtcEngineEventHandler$LocalVideoStats;
import io.agora.rtc.IRtcEngineEventHandler$RemoteVideoStats;
import io.agora.rtc.video.VideoCompositingLayout;
import io.agora.rtc.video.VideoCompositingLayout.Region;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class RtcEngineMessage {
    public static short AGORA_UI_SERVER = (short) 0;

    public static class MediaAppContext extends Marshallable {
        MediaNetworkInfo networkInfo;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            if (this.networkInfo != null) {
                this.networkInfo.marshall((Marshallable) this);
            }
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
        }
    }

    public static class MediaNetworkInfo extends Marshallable {
        int asu;
        String bssid = "";
        ArrayList<String> dnsList = null;
        String gatewayIp4 = "";
        String gatewayIp6 = "";
        String localIp4 = "";
        String localIp6 = "";
        int networkSubtype;
        int networkType;
        int rssi;
        int signalLevel;
        String ssid = "";

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public void marshall(Marshallable marshallable) {
            marshallable.pushBytes(this.localIp4.getBytes());
            marshallable.pushBytes(this.gatewayIp4.getBytes());
            marshallable.pushBytes(this.localIp6.getBytes());
            marshallable.pushBytes(this.gatewayIp6.getBytes());
            marshallable.pushInt(this.networkType);
            marshallable.pushInt(this.networkSubtype);
            marshallable.pushInt(this.signalLevel);
            marshallable.pushInt(this.rssi);
            marshallable.pushInt(this.asu);
            if (this.ssid != null) {
                marshallable.pushBytes(this.ssid.getBytes());
            } else {
                marshallable.pushBytes("".getBytes());
            }
            if (this.bssid != null) {
                marshallable.pushBytes(this.bssid.getBytes());
            } else {
                marshallable.pushBytes("".getBytes());
            }
            if (this.dnsList != null) {
                marshallable.pushStringArray(this.dnsList);
            } else {
                marshallable.pushStringArray(new ArrayList());
            }
        }

        public byte[] marshall() {
            marshall((Marshallable) this);
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
        }
    }

    public static class MediaResSetupTime extends Marshallable {
        int elapsed;
        boolean firstSuccess;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.elapsed = popInt();
            this.firstSuccess = popBool().booleanValue();
        }
    }

    public static class PApiCallExecuted extends Marshallable {
        public String api;
        public int error;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.api = popString16UTF8();
            this.error = popInt();
        }
    }

    public static class PError extends Marshallable {
        public int err;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.err = popInt();
        }
    }

    public static class PFirstLocalAudioFrame extends Marshallable {
        public int elapsed;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            pushInt(this.elapsed);
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.elapsed = popInt();
        }
    }

    public static class PFirstLocalVideoFrame extends Marshallable {
        public int elapsed;
        public int height;
        public int width;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            pushInt(this.width);
            pushInt(this.height);
            pushInt(this.elapsed);
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.width = popInt();
            this.height = popInt();
            this.elapsed = popInt();
        }
    }

    public static class PFirstRemoteAudioFrame extends Marshallable {
        public int elapsed;
        public int uid;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            pushInt(this.uid);
            pushInt(this.elapsed);
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.uid = popInt();
            this.elapsed = popInt();
        }
    }

    public static class PFirstRemoteVideoDecoded extends Marshallable {
        public int elapsed;
        public int height;
        public int uid;
        public int width;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            pushInt(this.uid);
            pushInt(this.width);
            pushInt(this.height);
            pushInt(this.elapsed);
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.uid = popInt();
            this.width = popInt();
            this.height = popInt();
            this.elapsed = popInt();
        }
    }

    public static class PFirstRemoteVideoFrame extends Marshallable {
        public int elapsed;
        public int height;
        public int uid;
        public int width;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            pushInt(this.uid);
            pushInt(this.width);
            pushInt(this.height);
            pushInt(this.elapsed);
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.uid = popInt();
            this.width = popInt();
            this.height = popInt();
            this.elapsed = popInt();
        }
    }

    public static class PLocalVideoStat extends Marshallable {
        public IRtcEngineEventHandler$LocalVideoStats stats = new IRtcEngineEventHandler$LocalVideoStats();

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            pushInt(this.stats.sentBitrate);
            pushInt(this.stats.sentFrameRate);
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.stats.sentBitrate = popInt();
            this.stats.sentFrameRate = popInt();
        }
    }

    public static class PMediaEngineEvent extends Marshallable {
        int code;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.code = popInt();
        }
    }

    public static class PMediaReqConnectMedia2 extends Marshallable {
        public static final int uri = (66453504 | RtcEngineMessage.AGORA_UI_SERVER);
        String connInfo;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            pushBytes(this.connInfo.getBytes());
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
        }
    }

    public static class PMediaReqCreateChannel extends Marshallable {
        public static final int uri = (131072 | RtcEngineMessage.AGORA_UI_SERVER);
        String key;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            pushBytes(this.key.getBytes());
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
        }
    }

    public static class PMediaReqJoinMeida extends Marshallable {
        public static final int uri = (196608 | RtcEngineMessage.AGORA_UI_SERVER);
        int sid;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            pushInt(this.sid);
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
        }
    }

    public static class PMediaReqLeaveChannel extends Marshallable {
        public static final int uri = (393216 | RtcEngineMessage.AGORA_UI_SERVER);
        int sid;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            pushInt(this.sid);
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
        }
    }

    public static class PMediaReqLeaveLinkd extends Marshallable {
        public static final int uri = (262144 | RtcEngineMessage.AGORA_UI_SERVER);
        int sid;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            pushInt(this.sid);
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
        }
    }

    public static class PMediaReqUserData extends Marshallable {
        public static final int uri = (327680 | RtcEngineMessage.AGORA_UI_SERVER);
        String key;
        String mobileinfo;
        int uid;
        String username;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            pushBytes(this.key.getBytes());
            pushBytes(this.username.getBytes());
            pushBytes(this.mobileinfo.getBytes());
            pushInt(this.uid);
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
        }
    }

    public static class PMediaResAudioQuality extends Marshallable {
        short delay;
        short lost;
        int peer_uid;
        int quality;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.peer_uid = popInt();
            this.quality = popInt();
            this.delay = popShort();
            this.lost = popShort();
        }
    }

    public static class PMediaResJoinMedia extends Marshallable {
        public String channel;
        public int elapsed;
        public boolean firstSuccess;
        public int uid;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.channel = popString16UTF8();
            this.uid = popInt();
            this.elapsed = popInt();
            this.firstSuccess = popBool().booleanValue();
        }
    }

    public static class PMediaResLastmileQuality extends Marshallable {
        int quality;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.quality = popInt();
        }
    }

    public static class PMediaResNetworkQuality extends Marshallable {
        int rxQuality;
        int txQuality;
        int uid;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.uid = popInt();
            this.txQuality = popInt();
            this.rxQuality = popInt();
        }
    }

    public static class PMediaResRtcStats extends Marshallable {
        int cpuAppUsage;
        int cpuTotalUsage;
        int rxAudioKBitRate;
        int rxKBitRate;
        int rxVideoKBitRate;
        int totalDuration;
        int totalRxBytes;
        int totalTxBytes;
        int txAudioKBitRate;
        int txKBitRate;
        int txVideoKBitRate;
        int users;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.totalDuration = popInt();
            this.totalTxBytes = popInt();
            this.totalRxBytes = popInt();
            this.txKBitRate = popShort();
            this.rxKBitRate = popShort();
            this.txAudioKBitRate = popShort();
            this.rxAudioKBitRate = popShort();
            this.txVideoKBitRate = popShort();
            this.rxVideoKBitRate = popShort();
            this.cpuTotalUsage = popInt();
            this.cpuAppUsage = popInt();
            this.users = popInt();
        }
    }

    public static class PMediaResSpeakersReport extends Marshallable {
        int mixVolume;
        Speaker[] speakers;

        public static class Speaker {
            public int uid;
            public int volume;
        }

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            pushInt(this.mixVolume);
            int length = this.speakers.length;
            pushShort((short) length);
            for (int i = 0; i < length; i++) {
                pushInt(this.speakers[i].uid);
                pushInt(this.speakers[i].volume);
            }
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.mixVolume = popInt();
            short popShort = popShort();
            if (popShort > (short) 0) {
                this.speakers = new Speaker[popShort];
                for (short s = (short) 0; s < popShort; s++) {
                    this.speakers[s] = new Speaker();
                    this.speakers[s].uid = popInt();
                    this.speakers[s].volume = popInt();
                }
            }
        }
    }

    public static class PMediaResTransportQuality extends Marshallable {
        public int bitrate;
        public short delay;
        public boolean isAudio;
        public short lost;
        public int peer_uid;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.isAudio = popBool().booleanValue();
            this.peer_uid = popInt();
            this.bitrate = popInt();
            this.delay = popShort();
            this.lost = popShort();
        }
    }

    public static class PMediaResUserJoinedEvent extends Marshallable {
        int elapsed;
        int uid;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.uid = popInt();
            this.elapsed = popInt();
        }
    }

    public static class PMediaResUserOfflineEvent extends Marshallable {
        int reason;
        int uid;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.uid = popInt();
            this.reason = popInt();
        }
    }

    public static class PMediaResUserState extends Marshallable {
        boolean state;
        int uid;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.uid = popInt();
            this.state = popBool().booleanValue();
        }
    }

    public static class PRecordingServiceStatus extends Marshallable {
        public int status;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.status = popInt();
        }
    }

    public static class PRemoteVideoStat extends Marshallable {
        public IRtcEngineEventHandler$RemoteVideoStats stats = new IRtcEngineEventHandler$RemoteVideoStats();

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            pushInt(this.stats.uid);
            pushInt(this.stats.delay);
            pushInt(this.stats.width);
            pushInt(this.stats.height);
            pushInt(this.stats.receivedBitrate);
            pushInt(this.stats.receivedFrameRate);
            pushInt(this.stats.rxStreamType);
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.stats.uid = popInt();
            this.stats.delay = popInt();
            this.stats.width = popInt();
            this.stats.height = popInt();
            this.stats.receivedBitrate = popInt();
            this.stats.receivedFrameRate = popInt();
            this.stats.rxStreamType = popInt();
        }
    }

    public static class PStreamMessage extends Marshallable {
        byte[] payload;
        int streamId;
        int uid;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.uid = popInt();
            this.streamId = popInt();
            this.payload = popBytes();
        }
    }

    public static class PStreamMessageError extends Marshallable {
        int cached;
        int error;
        int missed;
        int streamId;
        int uid;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.uid = popInt();
            this.streamId = popInt();
            this.error = popInt();
            this.missed = popInt();
            this.cached = popInt();
        }
    }

    public static class PVideoCompositingLayout extends Marshallable {
        private static final short SERVER_TYPE = (short) 0;
        private static final short URI = (short) 20;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ byte[] marshall() {
            return super.marshall();
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        private void marshallRegion(Marshallable marshallable, Region region) {
            marshallable.pushInt(region.uid);
            marshallable.pushDouble(region.x);
            marshallable.pushDouble(region.y);
            marshallable.pushDouble(region.width);
            marshallable.pushDouble(region.height);
            marshallable.pushInt(region.zOrder);
            marshallable.pushDouble(region.alpha);
            marshallable.pushInt(region.renderMode);
        }

        private void marshall(Marshallable marshallable, VideoCompositingLayout videoCompositingLayout) {
            int i = 0;
            marshallable.pushShort((short) 0);
            marshallable.pushShort(URI);
            marshallable.pushInt(videoCompositingLayout.canvasWidth);
            marshallable.pushInt(videoCompositingLayout.canvasHeight);
            if (videoCompositingLayout.backgroundColor != null) {
                pushBytes(videoCompositingLayout.backgroundColor.getBytes());
            } else {
                pushBytes("".getBytes());
            }
            if (videoCompositingLayout.appData != null) {
                pushBytes(videoCompositingLayout.appData);
            } else {
                pushBytes("".getBytes());
            }
            pushShort((short) videoCompositingLayout.regions.length);
            while (i < videoCompositingLayout.regions.length) {
                marshallRegion(marshallable, videoCompositingLayout.regions[i]);
                i++;
            }
        }

        public byte[] marshall(VideoCompositingLayout videoCompositingLayout) {
            marshall(this, videoCompositingLayout);
            return super.marshall();
        }
    }

    public static class PVideoNetOptions extends Marshallable {
        short bitrate;
        short frameRate;
        short height;
        short width;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public void marshall(Marshallable marshallable) {
            marshallable.pushShort(this.width);
            marshallable.pushShort(this.height);
            marshallable.pushShort(this.frameRate);
            marshallable.pushShort(this.bitrate);
        }

        public byte[] marshall() {
            marshall((Marshallable) this);
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.width = popShort();
            this.height = popShort();
            this.frameRate = popShort();
            this.bitrate = popShort();
        }
    }

    public static class PVideoSizeChanged extends Marshallable {
        public int height;
        public int rotation;
        public int uid;
        public int width;

        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        public byte[] marshall() {
            pushInt(this.uid);
            pushInt(this.width);
            pushInt(this.height);
            pushInt(this.rotation);
            return super.marshall();
        }

        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.uid = popInt();
            this.width = popInt();
            this.height = popInt();
            this.rotation = popInt();
        }
    }
}
