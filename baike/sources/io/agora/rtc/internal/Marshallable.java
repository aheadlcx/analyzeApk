package io.agora.rtc.internal;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

class Marshallable {
    public static final int PROTO_PACKET_SIZE = 8192;
    private ByteBuffer mBuffer = ByteBuffer.allocate(8192);

    public Marshallable() {
        this.mBuffer.order(ByteOrder.LITTLE_ENDIAN);
        this.mBuffer.position(2);
    }

    public byte[] marshall() {
        short position = (short) this.mBuffer.position();
        this.mBuffer.putShort(0, position);
        byte[] bArr = new byte[position];
        this.mBuffer.position(0);
        this.mBuffer.get(bArr);
        return bArr;
    }

    public void marshall(ByteBuffer byteBuffer) {
        this.mBuffer = byteBuffer;
    }

    public void unmarshall(byte[] bArr) {
        this.mBuffer = ByteBuffer.wrap(bArr);
        this.mBuffer.order(ByteOrder.LITTLE_ENDIAN);
        popShort();
    }

    public void unmarshall(ByteBuffer byteBuffer) {
        this.mBuffer = byteBuffer;
    }

    public ByteBuffer getBuffer() {
        return this.mBuffer;
    }

    public void pushBool(Boolean bool) {
        byte b = (byte) 0;
        if (bool.booleanValue()) {
            b = (byte) 1;
        }
        this.mBuffer.put(b);
    }

    public Boolean popBool() {
        boolean z = true;
        if (this.mBuffer.get() != (byte) 1) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public void pushByte(byte b) {
        this.mBuffer.put(b);
    }

    public byte popByte() {
        return this.mBuffer.get();
    }

    public void pushBytes(byte[] bArr) {
        this.mBuffer.putShort((short) bArr.length);
        this.mBuffer.put(bArr);
    }

    public byte[] popBytes() {
        short s = this.mBuffer.getShort();
        byte[] bArr = new byte[0];
        if (s <= (short) 0) {
            return bArr;
        }
        bArr = new byte[s];
        this.mBuffer.get(bArr);
        return bArr;
    }

    public void pushBytes32(byte[] bArr) {
        this.mBuffer.putInt(bArr.length);
        this.mBuffer.put(bArr);
    }

    public byte[] popBytes32() {
        int i = this.mBuffer.getInt();
        if (i <= 0) {
            return null;
        }
        byte[] bArr = new byte[i];
        this.mBuffer.get(bArr);
        return bArr;
    }

    public byte[] popAll() {
        byte[] bArr = new byte[this.mBuffer.remaining()];
        this.mBuffer.get(bArr);
        return bArr;
    }

    public void pushShort(short s) {
        this.mBuffer.putShort(s);
    }

    public short popShort() {
        return this.mBuffer.getShort();
    }

    public void pushInt(int i) {
        this.mBuffer.putInt(i);
    }

    public void pushDouble(double d) {
        this.mBuffer.putDouble(d);
    }

    public int popInt() {
        return this.mBuffer.getInt();
    }

    public void pushInt64(long j) {
        this.mBuffer.putLong(j);
    }

    public long popInt64() {
        return this.mBuffer.getLong();
    }

    public void pushString16(String str) {
        if (str == null) {
            this.mBuffer.putShort((short) 0);
            return;
        }
        this.mBuffer.putShort((short) str.getBytes().length);
        if (str.getBytes().length > 0) {
            this.mBuffer.put(str.getBytes());
        }
    }

    public String popString16() {
        short s = this.mBuffer.getShort();
        if (s > (short) 0) {
            byte[] bArr = new byte[s];
            this.mBuffer.get(bArr);
            try {
                return new String(bArr, "ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String popString16UTF8() {
        short s = this.mBuffer.getShort();
        if (s > (short) 0) {
            byte[] bArr = new byte[s];
            this.mBuffer.get(bArr);
            try {
                return new String(bArr, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public void pushStringArray(ArrayList<String> arrayList) {
        if (arrayList == null) {
            pushInt(0);
            return;
        }
        int size = arrayList.size();
        pushShort((short) size);
        for (int i = 0; i < size; i++) {
            pushBytes(((String) arrayList.get(i)).getBytes());
        }
    }

    public void pushIntArray(int[] iArr) {
        int i = 0;
        if (iArr == null) {
            pushInt(0);
            return;
        }
        int length = iArr.length;
        pushInt(length);
        while (i < length) {
            pushInt(iArr[i]);
            i++;
        }
    }

    public void pushIntArray(Integer[] numArr) {
        int i = 0;
        if (numArr == null) {
            pushInt(0);
            return;
        }
        int length = numArr.length;
        pushInt(length);
        while (i < length) {
            pushInt(numArr[i].intValue());
            i++;
        }
    }

    public int[] popIntArray() {
        int popInt = popInt();
        int[] iArr = new int[popInt];
        for (int i = 0; i < popInt; i++) {
            iArr[i] = popInt();
        }
        return iArr;
    }

    public void pushShortArray(short[] sArr) {
        int i = 0;
        if (sArr == null) {
            pushInt(0);
            return;
        }
        int length = sArr.length;
        pushInt(length);
        while (i < length) {
            pushShort(sArr[i]);
            i++;
        }
    }

    public short[] popShortArray() {
        int popInt = popInt();
        short[] sArr = new short[popInt];
        for (int i = 0; i < popInt; i++) {
            sArr[i] = popShort();
        }
        return sArr;
    }

    public void clear() {
        this.mBuffer.position(10);
    }
}
