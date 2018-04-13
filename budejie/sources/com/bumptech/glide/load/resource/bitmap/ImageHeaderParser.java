package com.bumptech.glide.load.resource.bitmap;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ImageHeaderParser {
    private static final byte[] a;
    private static final int[] b = new int[]{0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};
    private final b c;

    public enum ImageType {
        GIF(true),
        JPEG(false),
        PNG_A(true),
        PNG(false),
        UNKNOWN(false);
        
        private final boolean hasAlpha;

        private ImageType(boolean z) {
            this.hasAlpha = z;
        }

        public boolean hasAlpha() {
            return this.hasAlpha;
        }
    }

    private static class a {
        private final ByteBuffer a;

        public a(byte[] bArr) {
            this.a = ByteBuffer.wrap(bArr);
            this.a.order(ByteOrder.BIG_ENDIAN);
        }

        public void a(ByteOrder byteOrder) {
            this.a.order(byteOrder);
        }

        public int a() {
            return this.a.array().length;
        }

        public int a(int i) {
            return this.a.getInt(i);
        }

        public short b(int i) {
            return this.a.getShort(i);
        }
    }

    private static class b {
        private final InputStream a;

        public b(InputStream inputStream) {
            this.a = inputStream;
        }

        public int a() throws IOException {
            return ((this.a.read() << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (this.a.read() & 255);
        }

        public short b() throws IOException {
            return (short) (this.a.read() & 255);
        }

        public long a(long j) throws IOException {
            if (j < 0) {
                return 0;
            }
            long j2 = j;
            while (j2 > 0) {
                long skip = this.a.skip(j2);
                if (skip > 0) {
                    j2 -= skip;
                } else if (this.a.read() == -1) {
                    break;
                } else {
                    j2--;
                }
            }
            return j - j2;
        }

        public int a(byte[] bArr) throws IOException {
            int length = bArr.length;
            while (length > 0) {
                int read = this.a.read(bArr, bArr.length - length, length);
                if (read == -1) {
                    break;
                }
                length -= read;
            }
            return bArr.length - length;
        }

        public int c() throws IOException {
            return this.a.read();
        }
    }

    static {
        byte[] bArr = new byte[0];
        try {
            bArr = "Exif\u0000\u0000".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        a = bArr;
    }

    public ImageHeaderParser(InputStream inputStream) {
        this.c = new b(inputStream);
    }

    public boolean a() throws IOException {
        return b().hasAlpha();
    }

    public ImageType b() throws IOException {
        int a = this.c.a();
        if (a == 65496) {
            return ImageType.JPEG;
        }
        a = ((a << 16) & SupportMenu.CATEGORY_MASK) | (this.c.a() & SupportMenu.USER_MASK);
        if (a == -1991225785) {
            this.c.a(21);
            return this.c.c() >= 3 ? ImageType.PNG_A : ImageType.PNG;
        } else if ((a >> 8) == 4671814) {
            return ImageType.GIF;
        } else {
            return ImageType.UNKNOWN;
        }
    }

    public int c() throws IOException {
        Object obj = null;
        if (!a(this.c.a())) {
            return -1;
        }
        byte[] d = d();
        Object obj2 = (d == null || d.length <= a.length) ? null : 1;
        if (obj2 != null) {
            for (int i = 0; i < a.length; i++) {
                if (d[i] != a[i]) {
                    break;
                }
            }
        }
        obj = obj2;
        if (obj != null) {
            return a(new a(d));
        }
        return -1;
    }

    private byte[] d() throws IOException {
        long a;
        int a2;
        do {
            short b;
            short b2 = this.c.b();
            if (b2 == (short) 255) {
                b = this.c.b();
                if (b == (short) 218) {
                    return null;
                }
                if (b != (short) 217) {
                    a2 = this.c.a() - 2;
                    if (b != (short) 225) {
                        a = this.c.a((long) a2);
                    } else {
                        byte[] bArr = new byte[a2];
                        int a3 = this.c.a(bArr);
                        if (a3 == a2) {
                            return bArr;
                        }
                        if (!Log.isLoggable("ImageHeaderParser", 3)) {
                            return null;
                        }
                        Log.d("ImageHeaderParser", "Unable to read segment data, type: " + b + ", length: " + a2 + ", actually read: " + a3);
                        return null;
                    }
                } else if (!Log.isLoggable("ImageHeaderParser", 3)) {
                    return null;
                } else {
                    Log.d("ImageHeaderParser", "Found MARKER_EOI in exif segment");
                    return null;
                }
            } else if (!Log.isLoggable("ImageHeaderParser", 3)) {
                return null;
            } else {
                Log.d("ImageHeaderParser", "Unknown segmentId=" + b2);
                return null;
            }
        } while (a == ((long) a2));
        if (!Log.isLoggable("ImageHeaderParser", 3)) {
            return null;
        }
        Log.d("ImageHeaderParser", "Unable to skip enough data, type: " + b + ", wanted to skip: " + a2 + ", but actually skipped: " + a);
        return null;
    }

    private static int a(a aVar) {
        ByteOrder byteOrder;
        int length = "Exif\u0000\u0000".length();
        short b = aVar.b(length);
        if (b == (short) 19789) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else if (b == (short) 18761) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else {
            if (Log.isLoggable("ImageHeaderParser", 3)) {
                Log.d("ImageHeaderParser", "Unknown endianness = " + b);
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        aVar.a(byteOrder);
        length += aVar.a(length + 4);
        short b2 = aVar.b(length);
        for (b = (short) 0; b < b2; b++) {
            int a = a(length, b);
            short b3 = aVar.b(a);
            if (b3 == (short) 274) {
                short b4 = aVar.b(a + 2);
                if (b4 >= (short) 1 && b4 <= (short) 12) {
                    int a2 = aVar.a(a + 4);
                    if (a2 >= 0) {
                        if (Log.isLoggable("ImageHeaderParser", 3)) {
                            Log.d("ImageHeaderParser", "Got tagIndex=" + b + " tagType=" + b3 + " formatCode=" + b4 + " componentCount=" + a2);
                        }
                        a2 += b[b4];
                        if (a2 <= 4) {
                            a += 8;
                            if (a < 0 || a > aVar.a()) {
                                if (Log.isLoggable("ImageHeaderParser", 3)) {
                                    Log.d("ImageHeaderParser", "Illegal tagValueOffset=" + a + " tagType=" + b3);
                                }
                            } else if (a2 >= 0 && a + a2 <= aVar.a()) {
                                return aVar.b(a);
                            } else {
                                if (Log.isLoggable("ImageHeaderParser", 3)) {
                                    Log.d("ImageHeaderParser", "Illegal number of bytes for TI tag data tagType=" + b3);
                                }
                            }
                        } else if (Log.isLoggable("ImageHeaderParser", 3)) {
                            Log.d("ImageHeaderParser", "Got byte count > 4, not orientation, continuing, formatCode=" + b4);
                        }
                    } else if (Log.isLoggable("ImageHeaderParser", 3)) {
                        Log.d("ImageHeaderParser", "Negative tiff component count");
                    }
                } else if (Log.isLoggable("ImageHeaderParser", 3)) {
                    Log.d("ImageHeaderParser", "Got invalid format code=" + b4);
                }
            }
        }
        return -1;
    }

    private static int a(int i, int i2) {
        return (i + 2) + (i2 * 12);
    }

    private static boolean a(int i) {
        return (i & 65496) == 65496 || i == 19789 || i == 18761;
    }
}
