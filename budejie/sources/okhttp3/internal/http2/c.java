package okhttp3.internal.http2;

import java.io.IOException;
import okio.ByteString;

public final class c {
    static final ByteString a = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    static final String[] b = new String[64];
    static final String[] c = new String[256];
    private static final String[] d = new String[]{"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

    static {
        int i = 0;
        for (int i2 = 0; i2 < c.length; i2++) {
            c[i2] = okhttp3.internal.c.a("%8s", Integer.toBinaryString(i2)).replace(' ', '0');
        }
        b[0] = "";
        b[1] = "END_STREAM";
        int[] iArr = new int[]{1};
        b[8] = "PADDED";
        for (int i3 : iArr) {
            b[i3 | 8] = b[i3] + "|PADDED";
        }
        b[4] = "END_HEADERS";
        b[32] = "PRIORITY";
        b[36] = "END_HEADERS|PRIORITY";
        for (int i4 : new int[]{4, 32, 36}) {
            for (int i5 : iArr) {
                b[i5 | i4] = b[i5] + '|' + b[i4];
                b[(i5 | i4) | 8] = b[i5] + '|' + b[i4] + "|PADDED";
            }
        }
        while (i < b.length) {
            if (b[i] == null) {
                b[i] = c[i];
            }
            i++;
        }
    }

    private c() {
    }

    static IllegalArgumentException a(String str, Object... objArr) {
        throw new IllegalArgumentException(okhttp3.internal.c.a(str, objArr));
    }

    static IOException b(String str, Object... objArr) throws IOException {
        throw new IOException(okhttp3.internal.c.a(str, objArr));
    }

    static String a(boolean z, int i, int i2, byte b, byte b2) {
        String a = b < d.length ? d[b] : okhttp3.internal.c.a("0x%02x", Byte.valueOf(b));
        String a2 = a(b, b2);
        String str = "%s 0x%08x %5d %-13s %s";
        Object[] objArr = new Object[5];
        objArr[0] = z ? "<<" : ">>";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = Integer.valueOf(i2);
        objArr[3] = a;
        objArr[4] = a2;
        return okhttp3.internal.c.a(str, objArr);
    }

    static String a(byte b, byte b2) {
        if (b2 == (byte) 0) {
            return "";
        }
        switch (b) {
            case (byte) 2:
            case (byte) 3:
            case (byte) 7:
            case (byte) 8:
                return c[b2];
            case (byte) 4:
            case (byte) 6:
                return b2 == (byte) 1 ? "ACK" : c[b2];
            default:
                String str = b2 < b.length ? b[b2] : c[b2];
                if (b == (byte) 5 && (b2 & 4) != 0) {
                    return str.replace("HEADERS", "PUSH_PROMISE");
                }
                if (b != (byte) 0 || (b2 & 32) == 0) {
                    return str;
                }
                return str.replace("PRIORITY", "COMPRESSED");
        }
    }
}
