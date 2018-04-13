package com.facebook.stetho.websocket;

import com.facebook.stetho.common.Utf8Charset;

class FrameHelper {
    FrameHelper() {
    }

    public static Frame createTextFrame(String str) {
        return createSimpleFrame((byte) 1, Utf8Charset.encodeUTF8(str));
    }

    public static Frame createBinaryFrame(byte[] bArr) {
        return createSimpleFrame((byte) 2, bArr);
    }

    public static Frame createCloseFrame(int i, String str) {
        Object encodeUTF8;
        int length;
        if (str != null) {
            encodeUTF8 = Utf8Charset.encodeUTF8(str);
            length = encodeUTF8.length + 2;
        } else {
            encodeUTF8 = null;
            length = 2;
        }
        Object obj = new byte[length];
        obj[0] = (byte) ((i >> 8) & 255);
        obj[1] = (byte) (i & 255);
        if (encodeUTF8 != null) {
            System.arraycopy(encodeUTF8, 0, obj, 2, encodeUTF8.length);
        }
        return createSimpleFrame((byte) 8, obj);
    }

    public static Frame createPingFrame(byte[] bArr, int i) {
        return createSimpleFrame((byte) 9, bArr, i);
    }

    public static Frame createPongFrame(byte[] bArr, int i) {
        return createSimpleFrame((byte) 10, bArr, i);
    }

    private static Frame createSimpleFrame(byte b, byte[] bArr) {
        return createSimpleFrame(b, bArr, bArr.length);
    }

    private static Frame createSimpleFrame(byte b, byte[] bArr, int i) {
        Frame frame = new Frame();
        frame.fin = true;
        frame.hasMask = false;
        frame.opcode = b;
        frame.payloadLen = (long) i;
        frame.payloadData = bArr;
        return frame;
    }
}
