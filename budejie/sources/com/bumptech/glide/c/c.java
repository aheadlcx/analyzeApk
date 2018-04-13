package com.bumptech.glide.c;

import com.alibaba.wireless.security.SecExceptionCode;

class c {
    protected int a;
    protected byte[] b;
    protected int c;
    protected int d;
    protected int[][] e;
    protected int[] f = new int[256];
    protected int[] g = new int[256];
    protected int[] h = new int[256];
    protected int[] i = new int[32];

    public c(byte[] bArr, int i, int i2) {
        this.b = bArr;
        this.c = i;
        this.d = i2;
        this.e = new int[256][];
        for (int i3 = 0; i3 < 256; i3++) {
            this.e[i3] = new int[4];
            int[] iArr = this.e[i3];
            int i4 = (i3 << 12) / 256;
            iArr[2] = i4;
            iArr[1] = i4;
            iArr[0] = i4;
            this.h[i3] = 256;
            this.g[i3] = 0;
        }
    }

    public byte[] a() {
        int i;
        byte[] bArr = new byte[768];
        int[] iArr = new int[256];
        for (i = 0; i < 256; i++) {
            iArr[this.e[i][3]] = i;
        }
        int i2 = 0;
        for (i = 0; i < 256; i++) {
            int i3 = iArr[i];
            int i4 = i2 + 1;
            bArr[i2] = (byte) this.e[i3][0];
            int i5 = i4 + 1;
            bArr[i4] = (byte) this.e[i3][1];
            i2 = i5 + 1;
            bArr[i5] = (byte) this.e[i3][2];
        }
        return bArr;
    }

    public void b() {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i3 < 256) {
            int[] iArr = this.e[i3];
            int i4 = iArr[1];
            int i5 = i3;
            for (int i6 = i3 + 1; i6 < 256; i6++) {
                int[] iArr2 = this.e[i6];
                if (iArr2[1] < i4) {
                    i4 = iArr2[1];
                    i5 = i6;
                }
            }
            int[] iArr3 = this.e[i5];
            if (i3 != i5) {
                i5 = iArr3[0];
                iArr3[0] = iArr[0];
                iArr[0] = i5;
                i5 = iArr3[1];
                iArr3[1] = iArr[1];
                iArr[1] = i5;
                i5 = iArr3[2];
                iArr3[2] = iArr[2];
                iArr[2] = i5;
                i5 = iArr3[3];
                iArr3[3] = iArr[3];
                iArr[3] = i5;
            }
            if (i4 != i2) {
                this.f[i2] = (i + i3) >> 1;
                for (i5 = i2 + 1; i5 < i4; i5++) {
                    this.f[i5] = i3;
                }
                i5 = i4;
                i4 = i3;
            } else {
                i4 = i;
                i5 = i2;
            }
            i3++;
            i = i4;
            i2 = i5;
        }
        this.f[i2] = (i + 255) >> 1;
        for (i4 = i2 + 1; i4 < 256; i4++) {
            this.f[i4] = 255;
        }
    }

    public void c() {
        int i;
        int i2;
        if (this.c < 1509) {
            this.d = 1;
        }
        this.a = ((this.d - 1) / 3) + 30;
        byte[] bArr = this.b;
        int i3 = this.c;
        int i4 = this.c / (this.d * 3);
        int i5 = i4 / 100;
        for (i = 0; i < 32; i++) {
            this.i[i] = (((1024 - (i * i)) * 256) / 1024) * 1024;
        }
        if (this.c < 1509) {
            i2 = 3;
        } else if (this.c % SecExceptionCode.SEC_ERROR_DYN_ENC_UNKNOWN_ERROR != 0) {
            i2 = 1497;
        } else if (this.c % 491 != 0) {
            i2 = 1473;
        } else if (this.c % 487 != 0) {
            i2 = 1461;
        } else {
            i2 = 1509;
        }
        int i6 = 0;
        int i7 = 32;
        int i8 = 2048;
        int i9 = 0;
        i = 1024;
        while (i9 < i4) {
            int i10 = (bArr[i6 + 0] & 255) << 4;
            int i11 = (bArr[i6 + 1] & 255) << 4;
            int i12 = (bArr[i6 + 2] & 255) << 4;
            int b = b(i10, i11, i12);
            b(i, b, i10, i11, i12);
            if (i7 != 0) {
                a(i7, b, i10, i11, i12);
            }
            int i13 = i6 + i2;
            if (i13 >= i3) {
                i10 = i13 - this.c;
            } else {
                i10 = i13;
            }
            int i14 = i9 + 1;
            if (i5 == 0) {
                i13 = 1;
            } else {
                i13 = i5;
            }
            if (i14 % i13 == 0) {
                i11 = i - (i / this.a);
                i12 = i8 - (i8 / 30);
                i = i12 >> 6;
                if (i <= 1) {
                    i = 0;
                }
                for (b = 0; b < i; b++) {
                    this.i[b] = ((((i * i) - (b * b)) * 256) / (i * i)) * i11;
                }
                i6 = i10;
                i5 = i13;
                i7 = i;
                i8 = i12;
                i9 = i14;
                i = i11;
            } else {
                i6 = i10;
                i5 = i13;
                i9 = i14;
            }
        }
    }

    public int a(int i, int i2, int i3) {
        int i4 = this.f[i2];
        int i5 = -1;
        int i6 = 1000;
        int i7 = i4 - 1;
        int i8 = i4;
        while (true) {
            if (i8 >= 256 && i7 < 0) {
                return i5;
            }
            int[] iArr;
            int i9;
            if (i8 < 256) {
                iArr = this.e[i8];
                i9 = iArr[1] - i2;
                if (i9 >= i6) {
                    i8 = i6;
                    i4 = 256;
                    i6 = i5;
                } else {
                    i4 = i8 + 1;
                    if (i9 < 0) {
                        i9 = -i9;
                    }
                    i8 = iArr[0] - i;
                    if (i8 < 0) {
                        i8 = -i8;
                    }
                    i8 += i9;
                    if (i8 < i6) {
                        i9 = iArr[2] - i3;
                        if (i9 < 0) {
                            i9 = -i9;
                        }
                        i8 += i9;
                        if (i8 < i6) {
                            i6 = iArr[3];
                        }
                    }
                    i8 = i6;
                    i6 = i5;
                }
            } else {
                i4 = i8;
                i8 = i6;
                i6 = i5;
            }
            if (i7 >= 0) {
                iArr = this.e[i7];
                i9 = i2 - iArr[1];
                if (i9 >= i8) {
                    i5 = i6;
                    i7 = -1;
                    i6 = i8;
                    i8 = i4;
                } else {
                    i7--;
                    if (i9 < 0) {
                        i9 = -i9;
                    }
                    i5 = iArr[0] - i;
                    if (i5 < 0) {
                        i5 = -i5;
                    }
                    i5 += i9;
                    if (i5 < i8) {
                        i9 = iArr[2] - i3;
                        if (i9 < 0) {
                            i9 = -i9;
                        }
                        i9 += i5;
                        if (i9 < i8) {
                            i5 = iArr[3];
                            i8 = i4;
                            i6 = i9;
                        }
                    }
                }
            }
            i5 = i6;
            i6 = i8;
            i8 = i4;
        }
    }

    public byte[] d() {
        c();
        e();
        b();
        return a();
    }

    public void e() {
        for (int i = 0; i < 256; i++) {
            int[] iArr = this.e[i];
            iArr[0] = iArr[0] >> 4;
            iArr = this.e[i];
            iArr[1] = iArr[1] >> 4;
            iArr = this.e[i];
            iArr[2] = iArr[2] >> 4;
            this.e[i][3] = i;
        }
    }

    protected void a(int i, int i2, int i3, int i4, int i5) {
        int i6;
        int i7 = i2 - i;
        if (i7 < -1) {
            i6 = -1;
        } else {
            i6 = i7;
        }
        i7 = i2 + i;
        if (i7 > 256) {
            i7 = 256;
        }
        int i8 = 1;
        int i9 = i2 - 1;
        int i10 = i2 + 1;
        while (true) {
            if (i10 < i7 || i9 > i6) {
                int i11 = i8 + 1;
                int i12 = this.i[i8];
                if (i10 < i7) {
                    i8 = i10 + 1;
                    int[] iArr = this.e[i10];
                    try {
                        iArr[0] = iArr[0] - (((iArr[0] - i3) * i12) / 262144);
                        iArr[1] = iArr[1] - (((iArr[1] - i4) * i12) / 262144);
                        iArr[2] = iArr[2] - (((iArr[2] - i5) * i12) / 262144);
                    } catch (Exception e) {
                    }
                } else {
                    i8 = i10;
                }
                if (i9 > i6) {
                    i10 = i9 - 1;
                    int[] iArr2 = this.e[i9];
                    try {
                        iArr2[0] = iArr2[0] - (((iArr2[0] - i3) * i12) / 262144);
                        iArr2[1] = iArr2[1] - (((iArr2[1] - i4) * i12) / 262144);
                        iArr2[2] = iArr2[2] - ((i12 * (iArr2[2] - i5)) / 262144);
                        i9 = i10;
                        i10 = i8;
                        i8 = i11;
                    } catch (Exception e2) {
                        i9 = i10;
                        i10 = i8;
                        i8 = i11;
                    }
                } else {
                    i10 = i8;
                    i8 = i11;
                }
            } else {
                return;
            }
        }
    }

    protected void b(int i, int i2, int i3, int i4, int i5) {
        int[] iArr = this.e[i2];
        iArr[0] = iArr[0] - (((iArr[0] - i3) * i) / 1024);
        iArr[1] = iArr[1] - (((iArr[1] - i4) * i) / 1024);
        iArr[2] = iArr[2] - (((iArr[2] - i5) * i) / 1024);
    }

    protected int b(int i, int i2, int i3) {
        int i4 = Integer.MAX_VALUE;
        int i5 = -1;
        int i6 = Integer.MAX_VALUE;
        int i7 = -1;
        int i8 = 0;
        while (i8 < 256) {
            int[] iArr = this.e[i8];
            int i9 = iArr[0] - i;
            if (i9 < 0) {
                i9 = -i9;
            }
            int i10 = iArr[1] - i2;
            if (i10 < 0) {
                i10 = -i10;
            }
            i10 += i9;
            i9 = iArr[2] - i3;
            if (i9 < 0) {
                i9 = -i9;
            }
            i10 += i9;
            if (i10 < i6) {
                i9 = i10;
                i6 = i8;
            } else {
                i9 = i6;
                i6 = i7;
            }
            i10 -= this.g[i8] >> 12;
            if (i10 < i4) {
                i7 = i8;
            } else {
                i10 = i4;
                i7 = i5;
            }
            i4 = this.h[i8] >> 10;
            int[] iArr2 = this.h;
            iArr2[i8] = iArr2[i8] - i4;
            iArr2 = this.g;
            iArr2[i8] = (i4 << 10) + iArr2[i8];
            i8++;
            i4 = i10;
            i5 = i7;
            i7 = i6;
            i6 = i9;
        }
        int[] iArr3 = this.h;
        iArr3[i7] = iArr3[i7] + 64;
        iArr3 = this.g;
        iArr3[i7] = iArr3[i7] - 65536;
        return i5;
    }
}
