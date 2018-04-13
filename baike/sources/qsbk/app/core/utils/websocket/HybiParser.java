package qsbk.app.core.utils.websocket;

import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class HybiParser {
    private static final List<Integer> n = Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10)});
    private static final List<Integer> o = Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2)});
    private WebSocketClient a;
    private boolean b = true;
    private int c;
    private boolean d;
    private boolean e;
    private int f;
    private int g;
    private int h;
    private int i;
    private byte[] j = new byte[0];
    private byte[] k = new byte[0];
    private boolean l = false;
    private ByteArrayOutputStream m = new ByteArrayOutputStream();

    public static class HappyDataInputStream extends DataInputStream {
        public HappyDataInputStream(InputStream inputStream) {
            super(inputStream);
        }

        public byte[] readBytes(int i) throws IOException {
            byte[] bArr = new byte[i];
            readFully(bArr);
            return bArr;
        }
    }

    public static class ProtocolError extends IOException {
        public ProtocolError(String str) {
            super(str);
        }
    }

    public HybiParser(WebSocketClient webSocketClient) {
        this.a = webSocketClient;
    }

    private static byte[] a(byte[] bArr, byte[] bArr2, int i) {
        if (bArr2.length != 0) {
            for (int i2 = 0; i2 < bArr.length - i; i2++) {
                bArr[i + i2] = (byte) (bArr[i + i2] ^ bArr2[i2 % 4]);
            }
        }
        return bArr;
    }

    public void start(HappyDataInputStream happyDataInputStream) throws IOException {
        while (happyDataInputStream.available() != -1) {
            switch (this.c) {
                case 0:
                    a(happyDataInputStream.readByte());
                    break;
                case 1:
                    b(happyDataInputStream.readByte());
                    break;
                case 2:
                    a(happyDataInputStream.readBytes(this.g));
                    break;
                case 3:
                    this.j = happyDataInputStream.readBytes(4);
                    this.c = 4;
                    break;
                case 4:
                    this.k = happyDataInputStream.readBytes(this.h);
                    a();
                    this.c = 0;
                    break;
                default:
                    break;
            }
        }
        this.a.getListener().onDisconnect(0, "EOF");
    }

    private void a(byte b) throws ProtocolError {
        int i = (b & 64) == 64 ? 1 : 0;
        int i2;
        if ((b & 32) == 32) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i3;
        if ((b & 16) == 16) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        if (i == 0 && r3 == 0 && r0 == 0) {
            this.d = (b & 128) == 128;
            this.f = b & 15;
            this.j = new byte[0];
            this.k = new byte[0];
            if (!n.contains(Integer.valueOf(this.f))) {
                throw new ProtocolError("Bad opcode");
            } else if (o.contains(Integer.valueOf(this.f)) || this.d) {
                this.c = 1;
                return;
            } else {
                throw new ProtocolError("Expected non-final packet");
            }
        }
        throw new ProtocolError("RSV not zero");
    }

    private void b(byte b) {
        this.e = (b & 128) == 128;
        this.h = b & 127;
        if (this.h < 0 || this.h > 125) {
            this.g = this.h == 126 ? 2 : 8;
            this.c = 2;
            return;
        }
        this.c = this.e ? 3 : 4;
    }

    private void a(byte[] bArr) throws ProtocolError {
        this.h = c(bArr);
        this.c = this.e ? 3 : 4;
    }

    public byte[] frame(String str) {
        return a(str, 1, -1);
    }

    public byte[] frame(byte[] bArr) {
        return a(bArr, 2, -1);
    }

    private byte[] a(byte[] bArr, int i, int i2) {
        return a((Object) bArr, i, i2);
    }

    private byte[] a(String str, int i, int i2) {
        return a((Object) str, i, i2);
    }

    private byte[] a(Object obj, int i, int i2) {
        if (this.l) {
            return null;
        }
        if (obj instanceof String) {
            obj = a((String) obj);
        } else {
            byte[] bArr = (byte[]) obj;
        }
        int i3 = i2 > 0 ? 2 : 0;
        int length = obj.length + i3;
        int i4 = length <= 125 ? 2 : length <= SupportMenu.USER_MASK ? 4 : 10;
        int i5 = i4 + (this.b ? 4 : 0);
        int i6 = this.b ? 128 : 0;
        byte[] bArr2 = new byte[(length + i5)];
        bArr2[0] = (byte) (((byte) i) | -128);
        if (length <= 125) {
            bArr2[1] = (byte) (i6 | length);
        } else if (length <= SupportMenu.USER_MASK) {
            bArr2[1] = (byte) (i6 | 126);
            bArr2[2] = (byte) ((int) Math.floor((double) (length / 256)));
            bArr2[3] = (byte) (length & 255);
        } else {
            bArr2[1] = (byte) (i6 | 127);
            bArr2[2] = (byte) (((int) Math.floor(((double) length) / Math.pow(2.0d, 56.0d))) & 255);
            bArr2[3] = (byte) (((int) Math.floor(((double) length) / Math.pow(2.0d, 48.0d))) & 255);
            bArr2[4] = (byte) (((int) Math.floor(((double) length) / Math.pow(2.0d, 40.0d))) & 255);
            bArr2[5] = (byte) (((int) Math.floor(((double) length) / Math.pow(2.0d, 32.0d))) & 255);
            bArr2[6] = (byte) (((int) Math.floor(((double) length) / Math.pow(2.0d, 24.0d))) & 255);
            bArr2[7] = (byte) (((int) Math.floor(((double) length) / Math.pow(2.0d, 16.0d))) & 255);
            bArr2[8] = (byte) (((int) Math.floor(((double) length) / Math.pow(2.0d, 8.0d))) & 255);
            bArr2[9] = (byte) (length & 255);
        }
        if (i2 > 0) {
            bArr2[i5] = (byte) (((int) Math.floor((double) (i2 / 256))) & 255);
            bArr2[i5 + 1] = (byte) (i2 & 255);
        }
        System.arraycopy(obj, 0, bArr2, i3 + i5, obj.length);
        if (this.b) {
            byte[] bArr3 = new byte[]{(byte) ((int) Math.floor(Math.random() * 256.0d)), (byte) ((int) Math.floor(Math.random() * 256.0d)), (byte) ((int) Math.floor(Math.random() * 256.0d)), (byte) ((int) Math.floor(Math.random() * 256.0d))};
            System.arraycopy(bArr3, 0, bArr2, i4, bArr3.length);
            a(bArr2, bArr3, i5);
        }
        return bArr2;
    }

    public void ping(String str) {
        this.a.send(a(str, 9, -1));
    }

    public void close(int i, String str) {
        if (!this.l) {
            this.a.a(a(str, 8, i));
            this.l = true;
        }
    }

    private void a() throws IOException {
        int i = 0;
        byte[] a = a(this.k, this.j, 0);
        int i2 = this.f;
        if (i2 == 0) {
            if (this.i == 0) {
                throw new ProtocolError("Mode was not set.");
            }
            this.m.write(a);
            if (this.d) {
                byte[] toByteArray = this.m.toByteArray();
                if (this.i == 1) {
                    this.a.getListener().onMessage(b(toByteArray));
                } else {
                    this.a.getListener().onMessage(toByteArray);
                }
                b();
            }
        } else if (i2 == 1) {
            if (this.d) {
                this.a.getListener().onMessage(b(a));
                return;
            }
            this.i = 1;
            this.m.write(a);
        } else if (i2 == 2) {
            if (this.d) {
                this.a.getListener().onMessage(a);
                return;
            }
            this.i = 2;
            this.m.write(a);
        } else if (i2 == 8) {
            if (a.length >= 2) {
                i = ((a[0] + 1) * 256) + a[1];
            }
            String b = a.length > 2 ? b(a(a, 2)) : null;
            Log.d("HybiParser", "Got close op! " + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + b);
            this.a.getListener().onDisconnect(i, b);
        } else if (i2 == 9) {
            if (a.length > 125) {
                throw new ProtocolError("Ping payload too large");
            }
            Log.d("HybiParser", "Sending pong!!");
            this.a.a(a(a, 10, -1));
        } else if (i2 == 10) {
            Log.d("HybiParser", "Got pong! " + b(a));
        }
    }

    private void b() {
        this.i = 0;
        this.m.reset();
    }

    private String b(byte[] bArr) {
        try {
            return new String(bArr, "UTF-8");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] a(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private int c(byte[] bArr) throws ProtocolError {
        long b = b(bArr, 0, bArr.length);
        if (b >= 0 && b <= 2147483647L) {
            return (int) b;
        }
        throw new ProtocolError("Bad integer: " + b);
    }

    private byte[] a(byte[] bArr, int i) {
        return Arrays.copyOfRange(bArr, i, bArr.length);
    }

    private static long b(byte[] bArr, int i, int i2) {
        if (bArr.length < i2) {
            throw new IllegalArgumentException("length must be less than or equal to b.length");
        }
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j += (long) ((bArr[i3 + i] & 255) << (((i2 - 1) - i3) * 8));
        }
        return j;
    }
}
