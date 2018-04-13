package qsbk.app.utils;

import android.support.v4.view.MotionEventCompat;
import com.umeng.commonsdk.proguard.ar;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Base64 {
    public static final int DECODE = 0;
    public static final int DONT_BREAK_LINES = 8;
    public static final int ENCODE = 1;
    public static final int GZIP = 2;
    public static final int NO_OPTIONS = 0;
    public static final int ORDERED = 32;
    public static final int URL_SAFE = 16;
    private static final byte[] a = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    private static final byte[] b = new byte[]{(byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -5, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 62, (byte) -9, (byte) -9, (byte) -9, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -9, (byte) -9, (byte) -9, (byte) -1, (byte) -9, (byte) -9, (byte) -9, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, ar.m, ar.n, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) -9, (byte) -9, (byte) -9, (byte) -9};
    private static final byte[] c = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
    private static final byte[] d = new byte[]{(byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -5, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 62, (byte) -9, (byte) -9, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -9, (byte) -9, (byte) -9, (byte) -1, (byte) -9, (byte) -9, (byte) -9, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, ar.m, ar.n, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 63, (byte) -9, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) -9, (byte) -9, (byte) -9, (byte) -9};
    private static final byte[] e = new byte[]{(byte) 45, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 95, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122};
    private static final byte[] f = new byte[]{(byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -5, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 0, (byte) -9, (byte) -9, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) -9, (byte) -9, (byte) -9, (byte) -1, (byte) -9, (byte) -9, (byte) -9, (byte) 11, (byte) 12, (byte) 13, (byte) 14, ar.m, ar.n, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 37, (byte) -9, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) 62, (byte) 63, (byte) -9, (byte) -9, (byte) -9, (byte) -9};

    public static class InputStream extends FilterInputStream {
        protected byte[] a;
        private boolean b;
        private int c;
        private byte[] d;
        private int e;
        private int f;
        private int g;
        private boolean h;
        private int i;
        private byte[] j;

        public InputStream(java.io.InputStream inputStream) {
            this(inputStream, 0);
        }

        public InputStream(java.io.InputStream inputStream, int i) {
            boolean z = true;
            super(inputStream);
            this.h = (i & 8) != 8;
            if ((i & 1) != 1) {
                z = false;
            }
            this.b = z;
            this.e = this.b ? 4 : 3;
            this.d = new byte[this.e];
            this.c = -1;
            this.g = 0;
            this.i = i;
            this.a = Base64.c(i);
            this.j = Base64.d(i);
        }

        public int read() throws IOException {
            byte[] bArr;
            if (this.c < 0) {
                int read;
                if (this.b) {
                    bArr = new byte[3];
                    int i = 0;
                    for (int i2 = 0; i2 < 3; i2++) {
                        try {
                            read = this.in.read();
                            if (read >= 0) {
                                bArr[i2] = (byte) read;
                                i++;
                            }
                        } catch (IOException e) {
                            if (i2 == 0) {
                                throw e;
                            }
                        }
                    }
                    if (i <= 0) {
                        return -1;
                    }
                    Base64.b(bArr, 0, i, this.d, 0, this.i);
                    this.c = 0;
                    this.f = 4;
                } else {
                    byte[] bArr2 = new byte[4];
                    int i3 = 0;
                    while (i3 < 4) {
                        do {
                            read = this.in.read();
                            if (read < 0) {
                                break;
                            }
                        } while (this.j[read & 127] <= (byte) -5);
                        if (read < 0) {
                            break;
                        }
                        bArr2[i3] = (byte) read;
                        i3++;
                    }
                    if (i3 == 4) {
                        this.f = Base64.b(bArr2, 0, this.d, 0, this.i);
                        this.c = 0;
                    } else if (i3 == 0) {
                        return -1;
                    } else {
                        throw new IOException("Improperly padded Base64 input.");
                    }
                }
            }
            if (this.c < 0) {
                throw new IOException("Error in Base64 code reading stream.");
            } else if (this.c >= this.f) {
                return -1;
            } else {
                if (this.b && this.h && this.g >= 76) {
                    this.g = 0;
                    return 10;
                }
                this.g++;
                bArr = this.d;
                int i4 = this.c;
                this.c = i4 + 1;
                byte b = bArr[i4];
                if (this.c >= this.e) {
                    this.c = -1;
                }
                return b & 255;
            }
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = 0;
            while (i3 < i2) {
                int read = read();
                if (read >= 0) {
                    bArr[i + i3] = (byte) read;
                    i3++;
                } else if (i3 == 0) {
                    return -1;
                } else {
                    return i3;
                }
            }
            return i3;
        }
    }

    public static class OutputStream extends FilterOutputStream {
        protected byte[] a;
        private boolean b;
        private int c;
        private byte[] d;
        private int e;
        private int f;
        private boolean g;
        private byte[] h;
        private boolean i;
        private int j;
        private byte[] k;

        public OutputStream(java.io.OutputStream outputStream) {
            this(outputStream, 1);
        }

        public OutputStream(java.io.OutputStream outputStream, int i) {
            int i2;
            boolean z = true;
            super(outputStream);
            this.g = (i & 8) != 8;
            if ((i & 1) != 1) {
                z = false;
            }
            this.b = z;
            if (this.b) {
                i2 = 3;
            } else {
                i2 = 4;
            }
            this.e = i2;
            this.d = new byte[this.e];
            this.c = 0;
            this.f = 0;
            this.i = false;
            this.h = new byte[4];
            this.j = i;
            this.a = Base64.c(i);
            this.k = Base64.d(i);
        }

        public void write(int i) throws IOException {
            if (this.i) {
                this.out.write(i);
            } else if (this.b) {
                r0 = this.d;
                r1 = this.c;
                this.c = r1 + 1;
                r0[r1] = (byte) i;
                if (this.c >= this.e) {
                    this.out.write(Base64.b(this.h, this.d, this.e, this.j));
                    this.f += 4;
                    if (this.g && this.f >= 76) {
                        this.out.write(10);
                        this.f = 0;
                    }
                    this.c = 0;
                }
            } else if (this.k[i & 127] > (byte) -5) {
                r0 = this.d;
                r1 = this.c;
                this.c = r1 + 1;
                r0[r1] = (byte) i;
                if (this.c >= this.e) {
                    this.out.write(this.h, 0, Base64.b(this.d, 0, this.h, 0, this.j));
                    this.c = 0;
                }
            } else if (this.k[i & 127] != (byte) -5) {
                throw new IOException("Invalid character in Base64 data.");
            }
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.i) {
                this.out.write(bArr, i, i2);
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                write(bArr[i + i3]);
            }
        }

        public void flushBase64() throws IOException {
            if (this.c <= 0) {
                return;
            }
            if (this.b) {
                this.out.write(Base64.b(this.h, this.d, this.c, this.j));
                this.c = 0;
                return;
            }
            throw new IOException("Base64 input not properly padded.");
        }

        public void close() throws IOException {
            flushBase64();
            super.close();
            this.d = null;
            this.out = null;
        }

        public void suspendEncoding() throws IOException {
            flushBase64();
            this.i = true;
        }

        public void resumeEncoding() {
            this.i = false;
        }
    }

    private Base64() {
    }

    private static final byte[] c(int i) {
        if ((i & 16) == 16) {
            return c;
        }
        if ((i & 32) == 32) {
            return e;
        }
        return a;
    }

    private static final byte[] d(int i) {
        if ((i & 16) == 16) {
            return d;
        }
        if ((i & 32) == 32) {
            return f;
        }
        return b;
    }

    public static final void main(String[] strArr) {
        if (strArr.length < 3) {
            a("Not enough arguments.");
            return;
        }
        String str = strArr[0];
        String str2 = strArr[1];
        String str3 = strArr[2];
        if (str.equals("-e")) {
            encodeFileToFile(str2, str3);
        } else if (str.equals("-d")) {
            decodeFileToFile(str2, str3);
        } else {
            a("Unknown flag: " + str);
        }
    }

    private static final void a(String str) {
        System.err.println(str);
        System.err.println("Usage: java Base64 -e|-d inputfile outputfile");
    }

    private static byte[] b(byte[] bArr, byte[] bArr2, int i, int i2) {
        b(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    private static byte[] b(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        int i5 = 0;
        byte[] c = c(i4);
        int i6 = (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0) | (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0);
        if (i2 > 2) {
            i5 = (bArr[i + 2] << 24) >>> 24;
        }
        i5 |= i6;
        switch (i2) {
            case 1:
                bArr2[i3] = c[i5 >>> 18];
                bArr2[i3 + 1] = c[(i5 >>> 12) & 63];
                bArr2[i3 + 2] = (byte) 61;
                bArr2[i3 + 3] = (byte) 61;
                break;
            case 2:
                bArr2[i3] = c[i5 >>> 18];
                bArr2[i3 + 1] = c[(i5 >>> 12) & 63];
                bArr2[i3 + 2] = c[(i5 >>> 6) & 63];
                bArr2[i3 + 3] = (byte) 61;
                break;
            case 3:
                bArr2[i3] = c[i5 >>> 18];
                bArr2[i3 + 1] = c[(i5 >>> 12) & 63];
                bArr2[i3 + 2] = c[(i5 >>> 6) & 63];
                bArr2[i3 + 3] = c[i5 & 63];
                break;
        }
        return bArr2;
    }

    public static String encodeObject(Serializable serializable) {
        return encodeObject(serializable, 0);
    }

    public static String encodeObject(Serializable serializable, int i) {
        ByteArrayOutputStream byteArrayOutputStream;
        java.io.OutputStream outputStream;
        GZIPOutputStream gZIPOutputStream;
        ObjectOutputStream objectOutputStream;
        IOException e;
        Throwable th;
        int i2 = i & 2;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                outputStream = new OutputStream(byteArrayOutputStream, i | 1);
                if (i2 == 2) {
                    try {
                        gZIPOutputStream = new GZIPOutputStream(outputStream);
                        try {
                            objectOutputStream = new ObjectOutputStream(gZIPOutputStream);
                        } catch (IOException e2) {
                            e = e2;
                            objectOutputStream = null;
                            try {
                                e.printStackTrace();
                                try {
                                    objectOutputStream.close();
                                } catch (Exception e3) {
                                }
                                try {
                                    gZIPOutputStream.close();
                                } catch (Exception e4) {
                                }
                                try {
                                    outputStream.close();
                                } catch (Exception e5) {
                                }
                                try {
                                    byteArrayOutputStream.close();
                                    return null;
                                } catch (Exception e6) {
                                    return null;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                try {
                                    objectOutputStream.close();
                                } catch (Exception e7) {
                                }
                                try {
                                    gZIPOutputStream.close();
                                } catch (Exception e8) {
                                }
                                try {
                                    outputStream.close();
                                } catch (Exception e9) {
                                }
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Exception e10) {
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            objectOutputStream = null;
                            th = th3;
                            objectOutputStream.close();
                            gZIPOutputStream.close();
                            outputStream.close();
                            byteArrayOutputStream.close();
                            throw th;
                        }
                    } catch (IOException e11) {
                        e = e11;
                        gZIPOutputStream = null;
                        objectOutputStream = null;
                        e.printStackTrace();
                        objectOutputStream.close();
                        gZIPOutputStream.close();
                        outputStream.close();
                        byteArrayOutputStream.close();
                        return null;
                    } catch (Throwable th32) {
                        gZIPOutputStream = null;
                        objectOutputStream = null;
                        th = th32;
                        objectOutputStream.close();
                        gZIPOutputStream.close();
                        outputStream.close();
                        byteArrayOutputStream.close();
                        throw th;
                    }
                }
                objectOutputStream = new ObjectOutputStream(outputStream);
                gZIPOutputStream = null;
                try {
                    objectOutputStream.writeObject(serializable);
                    try {
                        objectOutputStream.close();
                    } catch (Exception e12) {
                    }
                    try {
                        gZIPOutputStream.close();
                    } catch (Exception e13) {
                    }
                    try {
                        outputStream.close();
                    } catch (Exception e14) {
                    }
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e15) {
                    }
                    try {
                        return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                    } catch (UnsupportedEncodingException e16) {
                        return new String(byteArrayOutputStream.toByteArray());
                    }
                } catch (IOException e17) {
                    e = e17;
                    e.printStackTrace();
                    objectOutputStream.close();
                    gZIPOutputStream.close();
                    outputStream.close();
                    byteArrayOutputStream.close();
                    return null;
                }
            } catch (IOException e18) {
                e = e18;
                gZIPOutputStream = null;
                objectOutputStream = null;
                outputStream = null;
                e.printStackTrace();
                objectOutputStream.close();
                gZIPOutputStream.close();
                outputStream.close();
                byteArrayOutputStream.close();
                return null;
            } catch (Throwable th322) {
                gZIPOutputStream = null;
                objectOutputStream = null;
                outputStream = null;
                th = th322;
                objectOutputStream.close();
                gZIPOutputStream.close();
                outputStream.close();
                byteArrayOutputStream.close();
                throw th;
            }
        } catch (IOException e19) {
            e = e19;
            gZIPOutputStream = null;
            objectOutputStream = null;
            outputStream = null;
            byteArrayOutputStream = null;
            e.printStackTrace();
            objectOutputStream.close();
            gZIPOutputStream.close();
            outputStream.close();
            byteArrayOutputStream.close();
            return null;
        } catch (Throwable th3222) {
            gZIPOutputStream = null;
            objectOutputStream = null;
            outputStream = null;
            byteArrayOutputStream = null;
            th = th3222;
            objectOutputStream.close();
            gZIPOutputStream.close();
            outputStream.close();
            byteArrayOutputStream.close();
            throw th;
        }
    }

    public static String encodeBytes(byte[] bArr) {
        return encodeBytes(bArr, 0, bArr.length, 0);
    }

    public static String encodeBytes(byte[] bArr, int i) {
        return encodeBytes(bArr, 0, bArr.length, i);
    }

    public static String encodeBytes(byte[] bArr, int i, int i2) {
        return encodeBytes(bArr, i, i2, 0);
    }

    public static String encodeBytes(byte[] bArr, int i, int i2, int i3) {
        ByteArrayOutputStream byteArrayOutputStream;
        OutputStream outputStream;
        IOException e;
        Throwable th;
        int i4 = i3 & 8;
        if ((i3 & 2) == 2) {
            GZIPOutputStream gZIPOutputStream;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    outputStream = new OutputStream(byteArrayOutputStream, i3 | 1);
                    try {
                        gZIPOutputStream = new GZIPOutputStream(outputStream);
                        try {
                            gZIPOutputStream.write(bArr, i, i2);
                            gZIPOutputStream.close();
                            try {
                                gZIPOutputStream.close();
                            } catch (Exception e2) {
                            }
                            try {
                                outputStream.close();
                            } catch (Exception e3) {
                            }
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e4) {
                            }
                            try {
                                return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                            } catch (UnsupportedEncodingException e5) {
                                return new String(byteArrayOutputStream.toByteArray());
                            }
                        } catch (IOException e6) {
                            e = e6;
                            try {
                                e.printStackTrace();
                                try {
                                    gZIPOutputStream.close();
                                } catch (Exception e7) {
                                }
                                try {
                                    outputStream.close();
                                } catch (Exception e8) {
                                }
                                try {
                                    byteArrayOutputStream.close();
                                    return null;
                                } catch (Exception e9) {
                                    return null;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                try {
                                    gZIPOutputStream.close();
                                } catch (Exception e10) {
                                }
                                try {
                                    outputStream.close();
                                } catch (Exception e11) {
                                }
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Exception e12) {
                                }
                                throw th;
                            }
                        }
                    } catch (IOException e13) {
                        e = e13;
                        gZIPOutputStream = null;
                        e.printStackTrace();
                        gZIPOutputStream.close();
                        outputStream.close();
                        byteArrayOutputStream.close();
                        return null;
                    } catch (Throwable th3) {
                        gZIPOutputStream = null;
                        th = th3;
                        gZIPOutputStream.close();
                        outputStream.close();
                        byteArrayOutputStream.close();
                        throw th;
                    }
                } catch (IOException e14) {
                    e = e14;
                    outputStream = null;
                    gZIPOutputStream = null;
                    e.printStackTrace();
                    gZIPOutputStream.close();
                    outputStream.close();
                    byteArrayOutputStream.close();
                    return null;
                } catch (Throwable th32) {
                    outputStream = null;
                    gZIPOutputStream = null;
                    th = th32;
                    gZIPOutputStream.close();
                    outputStream.close();
                    byteArrayOutputStream.close();
                    throw th;
                }
            } catch (IOException e15) {
                e = e15;
                outputStream = null;
                gZIPOutputStream = null;
                byteArrayOutputStream = null;
                e.printStackTrace();
                gZIPOutputStream.close();
                outputStream.close();
                byteArrayOutputStream.close();
                return null;
            } catch (Throwable th322) {
                outputStream = null;
                gZIPOutputStream = null;
                byteArrayOutputStream = null;
                th = th322;
                gZIPOutputStream.close();
                outputStream.close();
                byteArrayOutputStream.close();
                throw th;
            }
        }
        int i5;
        int i6 = i4 == 0 ? 1 : 0;
        i4 = (i2 * 4) / 3;
        if (i2 % 3 > 0) {
            i5 = 4;
        } else {
            i5 = 0;
        }
        int i7 = i4 + i5;
        if (i6 != 0) {
            i5 = i4 / 76;
        } else {
            i5 = 0;
        }
        byte[] bArr2 = new byte[(i5 + i7)];
        int i8 = i2 - 2;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (i11 < i8) {
            b(bArr, i11 + i, 3, bArr2, i10, i3);
            i5 = i9 + 4;
            if (i6 != 0 && i5 == 76) {
                bArr2[i10 + 4] = (byte) 10;
                i10++;
                i5 = 0;
            }
            i10 += 4;
            i9 = i5;
            i11 += 3;
        }
        if (i11 < i2) {
            b(bArr, i11 + i, i2 - i11, bArr2, i10, i3);
            i10 += 4;
        }
        try {
            return new String(bArr2, 0, i10, "UTF-8");
        } catch (UnsupportedEncodingException e16) {
            return new String(bArr2, 0, i10);
        }
    }

    private static int b(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        byte[] d = d(i3);
        if (bArr[i + 2] == (byte) 61) {
            bArr2[i2] = (byte) ((((d[bArr[i + 1]] & 255) << 12) | ((d[bArr[i]] & 255) << 18)) >>> 16);
            return 1;
        } else if (bArr[i + 3] == (byte) 61) {
            int i4 = ((d[bArr[i + 2]] & 255) << 6) | (((d[bArr[i]] & 255) << 18) | ((d[bArr[i + 1]] & 255) << 12));
            bArr2[i2] = (byte) (i4 >>> 16);
            bArr2[i2 + 1] = (byte) (i4 >>> 8);
            return 2;
        } else {
            try {
                int i5 = ((((d[bArr[i]] & 255) << 18) | ((d[bArr[i + 1]] & 255) << 12)) | ((d[bArr[i + 2]] & 255) << 6)) | (d[bArr[i + 3]] & 255);
                bArr2[i2] = (byte) (i5 >> 16);
                bArr2[i2 + 1] = (byte) (i5 >> 8);
                bArr2[i2 + 2] = (byte) i5;
                return 3;
            } catch (Exception e) {
                System.out.println("" + bArr[i] + ": " + d[bArr[i]]);
                System.out.println("" + bArr[i + 1] + ": " + d[bArr[i + 1]]);
                System.out.println("" + bArr[i + 2] + ": " + d[bArr[i + 2]]);
                System.out.println("" + bArr[i + 3] + ": " + d[bArr[i + 3]]);
                return -1;
            }
        }
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        int i4;
        byte[] d = d(i3);
        Object obj = new byte[((i2 * 3) / 4)];
        byte[] bArr2 = new byte[4];
        int i5 = i;
        int i6 = 0;
        int i7 = 0;
        while (i5 < i + i2) {
            byte b = (byte) (bArr[i5] & 127);
            byte b2 = d[b];
            if (b2 >= (byte) -5) {
                if (b2 >= (byte) -1) {
                    i4 = i6 + 1;
                    bArr2[i6] = b;
                    if (i4 > 3) {
                        i4 = b(bArr2, 0, obj, i7, i3) + i7;
                        if (b == (byte) 61) {
                            break;
                        }
                        i6 = i4;
                        i4 = 0;
                    } else {
                        i6 = i7;
                    }
                } else {
                    i4 = i6;
                    i6 = i7;
                }
                i5++;
                i7 = i6;
                i6 = i4;
            } else {
                System.err.println("Bad Base64 input character at " + i5 + ": " + bArr[i5] + "(decimal)");
                return null;
            }
        }
        i4 = i7;
        Object obj2 = new byte[i4];
        System.arraycopy(obj, 0, obj2, 0, i4);
        return obj2;
    }

    public static byte[] decode(String str) {
        return decode(str, 0);
    }

    public static byte[] decode(String str, int i) {
        byte[] bytes;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayInputStream byteArrayInputStream2;
        Throwable th;
        GZIPInputStream gZIPInputStream = null;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            bytes = str.getBytes();
        }
        bytes = decode(bytes, 0, bytes.length, i);
        if (bytes != null && bytes.length >= 4 && 35615 == ((bytes[0] & 255) | ((bytes[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) {
            byte[] bArr = new byte[2048];
            ByteArrayOutputStream byteArrayOutputStream;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byteArrayInputStream = new ByteArrayInputStream(bytes);
                    try {
                        GZIPInputStream gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream);
                        while (true) {
                            try {
                                int read = gZIPInputStream2.read(bArr);
                                if (read < 0) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            } catch (IOException e2) {
                                gZIPInputStream = gZIPInputStream2;
                                byteArrayInputStream2 = byteArrayInputStream;
                            } catch (Throwable th2) {
                                th = th2;
                                gZIPInputStream = gZIPInputStream2;
                            }
                        }
                        bytes = byteArrayOutputStream.toByteArray();
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e3) {
                        }
                        try {
                            gZIPInputStream2.close();
                        } catch (Exception e4) {
                        }
                        try {
                            byteArrayInputStream.close();
                        } catch (Exception e5) {
                        }
                    } catch (IOException e6) {
                        byteArrayInputStream2 = byteArrayInputStream;
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e7) {
                        }
                        try {
                            gZIPInputStream.close();
                        } catch (Exception e8) {
                        }
                        try {
                            byteArrayInputStream2.close();
                        } catch (Exception e9) {
                        }
                        return bytes;
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e10) {
                        }
                        try {
                            gZIPInputStream.close();
                        } catch (Exception e11) {
                        }
                        try {
                            byteArrayInputStream.close();
                        } catch (Exception e12) {
                        }
                        throw th;
                    }
                } catch (IOException e13) {
                    byteArrayInputStream2 = null;
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    byteArrayInputStream2.close();
                    return bytes;
                } catch (Throwable th4) {
                    th = th4;
                    byteArrayInputStream = null;
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    throw th;
                }
            } catch (IOException e14) {
                byteArrayOutputStream = null;
                byteArrayInputStream2 = null;
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                byteArrayInputStream2.close();
                return bytes;
            } catch (Throwable th5) {
                th = th5;
                byteArrayOutputStream = null;
                byteArrayInputStream = null;
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                byteArrayInputStream.close();
                throw th;
            }
        }
        return bytes;
    }

    public static Object decodeToObject(String str) {
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;
        Object readObject;
        IOException e;
        Throwable th;
        ClassNotFoundException e2;
        java.io.InputStream inputStream;
        java.io.InputStream inputStream2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(decode(str));
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
                try {
                    readObject = objectInputStream.readObject();
                    try {
                        byteArrayInputStream.close();
                    } catch (Exception e3) {
                    }
                    try {
                        objectInputStream.close();
                    } catch (Exception e4) {
                    }
                } catch (IOException e5) {
                    e = e5;
                    try {
                        e.printStackTrace();
                        try {
                            byteArrayInputStream.close();
                        } catch (Exception e6) {
                        }
                        try {
                            objectInputStream.close();
                        } catch (Exception e7) {
                        }
                        return readObject;
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            byteArrayInputStream.close();
                        } catch (Exception e8) {
                        }
                        try {
                            objectInputStream.close();
                        } catch (Exception e9) {
                        }
                        throw th;
                    }
                } catch (ClassNotFoundException e10) {
                    e2 = e10;
                    e2.printStackTrace();
                    try {
                        byteArrayInputStream.close();
                    } catch (Exception e11) {
                    }
                    try {
                        objectInputStream.close();
                    } catch (Exception e12) {
                    }
                    return readObject;
                }
            } catch (IOException e13) {
                e = e13;
                inputStream = inputStream2;
                e.printStackTrace();
                byteArrayInputStream.close();
                objectInputStream.close();
                return readObject;
            } catch (ClassNotFoundException e14) {
                e2 = e14;
                inputStream = inputStream2;
                e2.printStackTrace();
                byteArrayInputStream.close();
                objectInputStream.close();
                return readObject;
            } catch (Throwable th3) {
                inputStream = inputStream2;
                th = th3;
                byteArrayInputStream.close();
                objectInputStream.close();
                throw th;
            }
        } catch (IOException e15) {
            e = e15;
            objectInputStream = inputStream2;
            byteArrayInputStream = inputStream2;
            e.printStackTrace();
            byteArrayInputStream.close();
            objectInputStream.close();
            return readObject;
        } catch (ClassNotFoundException e16) {
            e2 = e16;
            objectInputStream = inputStream2;
            byteArrayInputStream = inputStream2;
            e2.printStackTrace();
            byteArrayInputStream.close();
            objectInputStream.close();
            return readObject;
        } catch (Throwable th32) {
            objectInputStream = inputStream2;
            byteArrayInputStream = inputStream2;
            th = th32;
            byteArrayInputStream.close();
            objectInputStream.close();
            throw th;
        }
        return readObject;
    }

    public static boolean encodeToFile(byte[] bArr, String str) {
        Throwable th;
        boolean z = true;
        OutputStream outputStream = null;
        OutputStream outputStream2;
        try {
            outputStream2 = new OutputStream(new FileOutputStream(str), 1);
            try {
                outputStream2.write(bArr);
                try {
                    outputStream2.close();
                } catch (Exception e) {
                }
            } catch (IOException e2) {
                z = false;
                try {
                    outputStream2.close();
                } catch (Exception e3) {
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
                outputStream = outputStream2;
                try {
                    outputStream.close();
                } catch (Exception e4) {
                }
                throw th;
            }
        } catch (IOException e5) {
            outputStream2 = null;
            z = false;
            outputStream2.close();
            return z;
        } catch (Throwable th3) {
            th = th3;
            outputStream.close();
            throw th;
        }
        return z;
    }

    public static boolean decodeToFile(String str, String str2) {
        Throwable th;
        boolean z = false;
        OutputStream outputStream = null;
        OutputStream outputStream2;
        try {
            outputStream2 = new OutputStream(new FileOutputStream(str2), 0);
            try {
                outputStream2.write(str.getBytes("UTF-8"));
                z = true;
                try {
                    outputStream2.close();
                } catch (Exception e) {
                }
            } catch (IOException e2) {
                try {
                    outputStream2.close();
                } catch (Exception e3) {
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
                outputStream = outputStream2;
                try {
                    outputStream.close();
                } catch (Exception e4) {
                }
                throw th;
            }
        } catch (IOException e5) {
            outputStream2 = null;
            outputStream2.close();
            return z;
        } catch (Throwable th3) {
            th = th3;
            outputStream.close();
            throw th;
        }
        return z;
    }

    public static byte[] decodeFromFile(String str) {
        Throwable th;
        byte[] bArr = null;
        int i = 0;
        InputStream inputStream = null;
        try {
            File file = new File(str);
            if (file.length() > 2147483647L) {
                System.err.println("File is too big for this convenience method (" + file.length() + " bytes).");
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            } else {
                Object obj = new byte[((int) file.length())];
                inputStream = new InputStream(new BufferedInputStream(new FileInputStream(file)), 0);
                while (true) {
                    try {
                        int read = inputStream.read(obj, i, 4096);
                        if (read < 0) {
                            break;
                        }
                        i += read;
                    } catch (IOException e2) {
                    }
                }
                bArr = new byte[i];
                System.arraycopy(obj, 0, bArr, 0, i);
                try {
                    inputStream.close();
                } catch (Exception e3) {
                }
            }
        } catch (IOException e4) {
            inputStream = null;
            try {
                System.err.println("Error decoding from file " + str);
                try {
                    inputStream.close();
                } catch (Exception e5) {
                }
                return bArr;
            } catch (Throwable th2) {
                th = th2;
                try {
                    inputStream.close();
                } catch (Exception e6) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            inputStream = null;
            th = th4;
            inputStream.close();
            throw th;
        }
        return bArr;
    }

    public static String encodeFromFile(String str) {
        InputStream inputStream;
        Throwable th;
        InputStream inputStream2 = null;
        try {
            File file = new File(str);
            byte[] bArr = new byte[Math.max((int) (((double) file.length()) * 1.4d), 40)];
            InputStream inputStream3 = new InputStream(new BufferedInputStream(new FileInputStream(file)), 1);
            int i = 0;
            while (true) {
                try {
                    int read = inputStream3.read(bArr, i, 4096);
                    if (read >= 0) {
                        i = read + i;
                    } else {
                        String str2 = new String(bArr, 0, i, "UTF-8");
                        try {
                            inputStream3.close();
                            return str2;
                        } catch (Exception e) {
                            return str2;
                        }
                    }
                } catch (IOException e2) {
                    inputStream = inputStream3;
                } catch (Throwable th2) {
                    th = th2;
                    inputStream2 = inputStream3;
                }
            }
        } catch (IOException e3) {
            inputStream = null;
            try {
                System.err.println("Error encoding from file " + str);
                try {
                    inputStream.close();
                    return null;
                } catch (Exception e4) {
                    return null;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                inputStream2 = inputStream;
                th = th4;
                try {
                    inputStream2.close();
                } catch (Exception e5) {
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            inputStream2.close();
            throw th;
        }
    }

    public static boolean encodeFileToFile(String str, String str2) {
        java.io.InputStream inputStream;
        java.io.OutputStream bufferedOutputStream;
        IOException e;
        java.io.InputStream inputStream2;
        Throwable th;
        java.io.OutputStream outputStream = null;
        try {
            inputStream = new InputStream(new BufferedInputStream(new FileInputStream(str)), 1);
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
                try {
                    byte[] bArr = new byte[65536];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read >= 0) {
                            bufferedOutputStream.write(bArr, 0, read);
                        } else {
                            try {
                                break;
                            } catch (Exception e2) {
                            }
                        }
                    }
                    inputStream.close();
                    try {
                        bufferedOutputStream.close();
                        return true;
                    } catch (Exception e3) {
                        return true;
                    }
                } catch (IOException e4) {
                    e = e4;
                    inputStream2 = inputStream;
                    try {
                        e.printStackTrace();
                        try {
                            inputStream2.close();
                        } catch (Exception e5) {
                        }
                        try {
                            bufferedOutputStream.close();
                            return false;
                        } catch (Exception e6) {
                            return false;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = inputStream2;
                        outputStream = bufferedOutputStream;
                        try {
                            inputStream.close();
                        } catch (Exception e7) {
                        }
                        try {
                            outputStream.close();
                        } catch (Exception e8) {
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outputStream = bufferedOutputStream;
                    inputStream.close();
                    outputStream.close();
                    throw th;
                }
            } catch (IOException e9) {
                e = e9;
                bufferedOutputStream = null;
                inputStream2 = inputStream;
                e.printStackTrace();
                inputStream2.close();
                bufferedOutputStream.close();
                return false;
            } catch (Throwable th4) {
                th = th4;
                inputStream.close();
                outputStream.close();
                throw th;
            }
        } catch (IOException e10) {
            e = e10;
            bufferedOutputStream = null;
            e.printStackTrace();
            inputStream2.close();
            bufferedOutputStream.close();
            return false;
        } catch (Throwable th5) {
            th = th5;
            inputStream = null;
            inputStream.close();
            outputStream.close();
            throw th;
        }
    }

    public static boolean decodeFileToFile(String str, String str2) {
        java.io.InputStream inputStream;
        java.io.OutputStream bufferedOutputStream;
        IOException e;
        java.io.InputStream inputStream2;
        Throwable th;
        java.io.OutputStream outputStream = null;
        boolean z = false;
        try {
            inputStream = new InputStream(new BufferedInputStream(new FileInputStream(str)), 0);
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
                try {
                    byte[] bArr = new byte[65536];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read < 0) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                    z = true;
                    try {
                        inputStream.close();
                    } catch (Exception e2) {
                    }
                    try {
                        bufferedOutputStream.close();
                    } catch (Exception e3) {
                    }
                } catch (IOException e4) {
                    e = e4;
                    inputStream2 = inputStream;
                    try {
                        e.printStackTrace();
                        try {
                            inputStream2.close();
                        } catch (Exception e5) {
                        }
                        try {
                            bufferedOutputStream.close();
                        } catch (Exception e6) {
                        }
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = inputStream2;
                        outputStream = bufferedOutputStream;
                        try {
                            inputStream.close();
                        } catch (Exception e7) {
                        }
                        try {
                            outputStream.close();
                        } catch (Exception e8) {
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outputStream = bufferedOutputStream;
                    inputStream.close();
                    outputStream.close();
                    throw th;
                }
            } catch (IOException e9) {
                e = e9;
                bufferedOutputStream = null;
                inputStream2 = inputStream;
                e.printStackTrace();
                inputStream2.close();
                bufferedOutputStream.close();
                return z;
            } catch (Throwable th4) {
                th = th4;
                inputStream.close();
                outputStream.close();
                throw th;
            }
        } catch (IOException e10) {
            e = e10;
            bufferedOutputStream = null;
            e.printStackTrace();
            inputStream2.close();
            bufferedOutputStream.close();
            return z;
        } catch (Throwable th5) {
            th = th5;
            inputStream = null;
            inputStream.close();
            outputStream.close();
            throw th;
        }
        return z;
    }
}
