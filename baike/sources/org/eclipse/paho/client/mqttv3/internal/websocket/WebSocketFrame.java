package org.eclipse.paho.client.mqttv3.internal.websocket;

import android.support.v4.internal.view.SupportMenu;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Random;

public class WebSocketFrame {
    public static final int frameLengthOverhead = 6;
    private byte a;
    private boolean b;
    private byte[] c;
    private boolean d = false;

    public byte getOpcode() {
        return this.a;
    }

    public boolean isFin() {
        return this.b;
    }

    public byte[] getPayload() {
        return this.c;
    }

    public boolean isCloseFlag() {
        return this.d;
    }

    public WebSocketFrame(byte b, boolean z, byte[] bArr) {
        this.a = b;
        this.b = z;
        this.c = bArr;
    }

    public WebSocketFrame(byte[] bArr) {
        int i;
        int i2 = 0;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        a(wrap.get());
        byte b = wrap.get();
        int i3 = (b & 128) != 0 ? 1 : 0;
        int i4 = (byte) (b & 127);
        if (i4 == 127) {
            i = 8;
        } else if (i4 == 126) {
            i = 2;
        } else {
            i = 0;
        }
        while (true) {
            i--;
            if (i <= 0) {
                break;
            }
            i4 |= (wrap.get() & 255) << (i * 8);
        }
        byte[] bArr2 = null;
        if (i3 != 0) {
            bArr2 = new byte[4];
            wrap.get(bArr2, 0, 4);
        }
        this.c = new byte[i4];
        wrap.get(this.c, 0, i4);
        if (i3 != 0) {
            while (i2 < this.c.length) {
                byte[] bArr3 = this.c;
                bArr3[i2] = (byte) (bArr3[i2] ^ bArr2[i2 % 4]);
                i2++;
            }
        }
    }

    private void a(byte b) {
        this.b = (b & 128) != 0;
        this.a = (byte) (b & 15);
    }

    public WebSocketFrame(InputStream inputStream) throws IOException {
        int i = 8;
        int i2 = 0;
        a((byte) inputStream.read());
        if (this.a == (byte) 2) {
            byte read = (byte) inputStream.read();
            boolean z = (read & 128) != 0;
            int i3 = (byte) (read & 127);
            if (i3 != 127) {
                if (i3 == 126) {
                    i = 2;
                } else {
                    i = 0;
                }
            }
            if (i > 0) {
                i3 = 0;
            }
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                i3 |= (((byte) inputStream.read()) & 255) << (i * 8);
            }
            byte[] bArr = null;
            if (z) {
                bArr = new byte[4];
                inputStream.read(bArr, 0, 4);
            }
            this.c = new byte[i3];
            int i4 = i3;
            int i5 = 0;
            while (i5 != i3) {
                int read2 = inputStream.read(this.c, i5, i4);
                i5 += read2;
                i4 -= read2;
            }
            if (z) {
                while (i2 < this.c.length) {
                    byte[] bArr2 = this.c;
                    bArr2[i2] = (byte) (bArr2[i2] ^ bArr[i2 % 4]);
                    i2++;
                }
            }
        } else if (this.a == (byte) 8) {
            this.d = true;
        } else {
            throw new IOException(new StringBuffer("Invalid Frame: Opcode: ").append(this.a).toString());
        }
    }

    public byte[] encodeFrame() {
        int length = this.c.length + 6;
        if (this.c.length > SupportMenu.USER_MASK) {
            length += 8;
        } else if (this.c.length >= 126) {
            length += 2;
        }
        ByteBuffer allocate = ByteBuffer.allocate(length);
        appendFinAndOpCode(allocate, this.a, this.b);
        byte[] generateMaskingKey = generateMaskingKey();
        appendLengthAndMask(allocate, this.c.length, generateMaskingKey);
        for (length = 0; length < this.c.length; length++) {
            byte[] bArr = this.c;
            byte b = (byte) (bArr[length] ^ generateMaskingKey[length % 4]);
            bArr[length] = b;
            allocate.put(b);
        }
        allocate.flip();
        return allocate.array();
    }

    public static void appendLengthAndMask(ByteBuffer byteBuffer, int i, byte[] bArr) {
        if (bArr != null) {
            a(byteBuffer, i, true);
            byteBuffer.put(bArr);
            return;
        }
        a(byteBuffer, i, false);
    }

    private static void a(ByteBuffer byteBuffer, int i, boolean z) {
        if (i < 0) {
            throw new IllegalArgumentException("Length cannot be negative");
        }
        int i2 = z ? -128 : 0;
        if (i > SupportMenu.USER_MASK) {
            byteBuffer.put((byte) (i2 | 127));
            byteBuffer.put((byte) 0);
            byteBuffer.put((byte) 0);
            byteBuffer.put((byte) 0);
            byteBuffer.put((byte) 0);
            byteBuffer.put((byte) ((i >> 24) & 255));
            byteBuffer.put((byte) ((i >> 16) & 255));
            byteBuffer.put((byte) ((i >> 8) & 255));
            byteBuffer.put((byte) (i & 255));
        } else if (i >= 126) {
            byteBuffer.put((byte) (i2 | 126));
            byteBuffer.put((byte) (i >> 8));
            byteBuffer.put((byte) (i & 255));
        } else {
            byteBuffer.put((byte) (i2 | i));
        }
    }

    public static void appendFinAndOpCode(ByteBuffer byteBuffer, byte b, boolean z) {
        int i = 0;
        if (z) {
            i = (byte) 128;
        }
        byteBuffer.put((byte) (i | (b & 15)));
    }

    public static byte[] generateMaskingKey() {
        Random random = new Random();
        int nextInt = random.nextInt(255);
        int nextInt2 = random.nextInt(255);
        int nextInt3 = random.nextInt(255);
        int nextInt4 = random.nextInt(255);
        return new byte[]{(byte) nextInt, (byte) nextInt2, (byte) nextInt3, (byte) nextInt4};
    }
}
