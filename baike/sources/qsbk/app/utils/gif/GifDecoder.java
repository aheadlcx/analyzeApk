package qsbk.app.utils.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.os.Environment;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class GifDecoder extends Thread {
    public static final int STATUS_FINISH = -1;
    public static final int STATUS_FORMAT_ERROR = 1;
    public static final int STATUS_OPEN_ERROR = 2;
    public static final int STATUS_PARSING = 0;
    private boolean A = false;
    private byte[] B = new byte[256];
    private int C = 0;
    private int D = 0;
    private int E = 0;
    private boolean F = false;
    private int G = 0;
    private int H;
    private short[] I;
    private byte[] J;
    private byte[] K;
    private byte[] L;
    private GifFrame M;
    private int N;
    private GifAction O = null;
    private byte[] P = null;
    private String Q = null;
    private boolean R = false;
    private InputStream a;
    private int b;
    private boolean c;
    private int d;
    private int e = 1;
    private int[] f;
    private int[] g;
    private int[] h;
    public int height;
    private int i;
    private int j;
    private int k;
    private int l;
    private boolean m;
    private boolean n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    public int width;
    private Bitmap x;
    private Bitmap y;
    private GifFrame z = null;

    public GifDecoder(GifAction gifAction) {
        this.O = gifAction;
    }

    public void setGifImage(byte[] bArr) {
        this.P = bArr;
    }

    public void setGifImage(InputStream inputStream) {
        this.a = inputStream;
    }

    public void setCacheImage(boolean z, Context context) {
        boolean z2 = true;
        this.R = z;
        try {
            if (this.R) {
                if (Environment.getExternalStorageState().equals("mounted")) {
                    this.Q = Environment.getExternalStorageDirectory().getPath() + File.separator + "gifView_tmp_dir" + File.separator + a();
                    if (b(this.Q)) {
                        z2 = false;
                    }
                }
                if (z2) {
                    this.Q = context.getFilesDir().getAbsolutePath() + File.separator + a();
                    if (!b(this.Q)) {
                        this.R = false;
                    }
                }
            }
        } catch (Exception e) {
            this.R = false;
        }
    }

    private void a(String str, boolean z) {
        try {
            a(str);
            if (z) {
                new File(str.toString()).delete();
            }
        } catch (Exception e) {
        }
    }

    private boolean a(String str) {
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        String[] list = file.list();
        int i = 0;
        boolean z = false;
        while (i < list.length) {
            File file2;
            boolean z2;
            if (str.endsWith(File.separator)) {
                file2 = new File(str + list[i]);
            } else {
                file2 = new File(str + File.separator + list[i]);
            }
            if (file2.isFile()) {
                file2.delete();
                z2 = z;
            } else if (file2.isDirectory()) {
                a(str + File.separator + list[i]);
                a(str + File.separator + list[i], true);
                z2 = true;
            } else {
                z2 = z;
            }
            i++;
            z = z2;
        }
        return z;
    }

    private synchronized String a() {
        return String.valueOf(System.currentTimeMillis());
    }

    private boolean b(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return true;
            }
            return file.mkdirs();
        } catch (Exception e) {
            return false;
        }
    }

    private void a(Bitmap bitmap, String str) {
        try {
            File file = new File(this.Q + File.separator + str + ".png");
            bitmap.compress(CompressFormat.PNG, 100, new FileOutputStream(this.Q + File.separator + a() + ".png"));
        } catch (Exception e) {
        }
    }

    public void run() {
        if (this.a != null) {
            d();
        } else if (this.P != null) {
            c();
        }
    }

    public void free() {
        GifFrame gifFrame = this.M;
        if (this.R) {
            a(this.Q, true);
        } else {
            while (gifFrame != null) {
                if (!(gifFrame.image == null || gifFrame.image.isRecycled())) {
                    gifFrame.image.recycle();
                }
                gifFrame.image = null;
                this.M = this.M.nextFrame;
                gifFrame = this.M;
            }
        }
        if (this.a != null) {
            try {
                this.a.close();
            } catch (Exception e) {
            }
            this.a = null;
        }
        this.P = null;
        this.b = 0;
        this.z = null;
    }

    public int getStatus() {
        return this.b;
    }

    public boolean parseOk() {
        return this.b == -1;
    }

    public int getDelay(int i) {
        this.G = -1;
        if (i >= 0 && i < this.N) {
            GifFrame frame = getFrame(i);
            if (frame != null) {
                this.G = frame.delay;
            }
        }
        return this.G;
    }

    public int[] getDelays() {
        GifFrame gifFrame = this.M;
        int[] iArr = new int[this.N];
        int i = 0;
        while (gifFrame != null && i < this.N) {
            iArr[i] = gifFrame.delay;
            gifFrame = gifFrame.nextFrame;
            i++;
        }
        return iArr;
    }

    public int getFrameCount() {
        return this.N;
    }

    public Bitmap getImage() {
        return getFrameImage(0);
    }

    public int getLoopCount() {
        return this.e;
    }

    private void b() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        int[] iArr = new int[(this.width * this.height)];
        if (this.E > 0) {
            if (this.E == 3) {
                i = this.N - 2;
                if (i > 0) {
                    this.y = getFrameImage(i - 1);
                } else {
                    this.y = null;
                }
            }
            if (!(this.y == null || this.y.isRecycled())) {
                this.y.getPixels(iArr, 0, this.width, 0, 0, this.width, this.height);
                if (this.E == 2) {
                    if (this.F) {
                        i = 0;
                    } else {
                        i = this.k;
                    }
                    for (i2 = 0; i2 < this.w; i2++) {
                        i3 = ((this.u + i2) * this.width) + this.t;
                        i4 = this.v + i3;
                        while (i3 < i4) {
                            iArr[i3] = i;
                            i3++;
                        }
                    }
                }
            }
        }
        i2 = 8;
        i3 = 1;
        i = 0;
        while (i5 < this.s) {
            if (this.n) {
                if (i >= this.s) {
                    i3++;
                    switch (i3) {
                        case 2:
                            i = 4;
                            break;
                        case 3:
                            i = 2;
                            i2 = 4;
                            break;
                        case 4:
                            i = 1;
                            i2 = 2;
                            break;
                    }
                }
                int i6 = i;
                i += i2;
                i4 = i6;
            } else {
                i4 = i5;
            }
            i4 += this.q;
            if (i4 < this.height) {
                int i7 = this.width * i4;
                int i8 = i7 + this.p;
                i4 = this.r + i8;
                if (this.width + i7 < i4) {
                    i4 = this.width + i7;
                }
                i7 = this.r * i5;
                int i9 = i8;
                while (i9 < i4) {
                    i8 = i7 + 1;
                    i7 = this.h[this.L[i7] & 255];
                    if (i7 != 0) {
                        iArr[i9] = i7;
                    }
                    i9++;
                    i7 = i8;
                }
            }
            i5++;
        }
        this.x = Bitmap.createBitmap(iArr, this.width, this.height, Config.ARGB_4444);
    }

    public Bitmap getFrameImage(int i) {
        GifFrame frame = getFrame(i);
        if (frame == null) {
            return null;
        }
        return frame.image;
    }

    public GifFrame getCurrentFrame() {
        return this.z;
    }

    public GifFrame getFrame(int i) {
        int i2 = 0;
        for (GifFrame gifFrame = this.M; gifFrame != null; gifFrame = gifFrame.nextFrame) {
            if (i2 == i) {
                return gifFrame;
            }
            i2++;
        }
        return null;
    }

    public void reset() {
        this.z = this.M;
    }

    public GifFrame next() {
        if (!this.A) {
            this.A = true;
            return this.M;
        } else if (this.z == null) {
            return null;
        } else {
            if (this.b != 0) {
                this.z = this.z.nextFrame;
                if (this.z == null) {
                    this.z = this.M;
                }
            } else if (this.z.nextFrame != null) {
                this.z = this.z.nextFrame;
            }
            return this.z;
        }
    }

    private int c() {
        this.a = new ByteArrayInputStream(this.P);
        this.P = null;
        return d();
    }

    private int d() {
        g();
        if (this.a != null) {
            l();
            if (!f()) {
                j();
                if (this.N < 0) {
                    this.b = 1;
                    this.O.parseOk(false, -1);
                } else {
                    this.b = -1;
                    this.O.parseOk(true, -1);
                }
            }
            try {
                this.a.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.b = 2;
            this.O.parseOk(false, -1);
        }
        return this.b;
    }

    private void e() {
        int i;
        int i2 = this.r * this.s;
        if (this.L == null || this.L.length < i2) {
            this.L = new byte[i2];
        }
        if (this.I == null) {
            this.I = new short[4096];
        }
        if (this.J == null) {
            this.J = new byte[4096];
        }
        if (this.K == null) {
            this.K = new byte[4097];
        }
        int h = h();
        int i3 = 1 << h;
        int i4 = i3 + 1;
        int i5 = i3 + 2;
        int i6 = h + 1;
        int i7 = (1 << i6) - 1;
        for (i = 0; i < i3; i++) {
            this.I[i] = (short) 0;
            this.J[i] = (byte) i;
        }
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = -1;
        i = 0;
        while (i12 < i2) {
            int i16;
            int i17;
            if (i9 == 0) {
                if (i14 >= i6) {
                    i16 = i11 & i7;
                    i11 >>= i6;
                    i14 -= i6;
                    if (i16 <= i5 && i16 != i4) {
                        if (i16 != i3) {
                            if (i15 != -1) {
                                int i18;
                                if (i16 == i5) {
                                    i18 = i9 + 1;
                                    this.K[i9] = (byte) i10;
                                    i10 = i15;
                                } else {
                                    i18 = i9;
                                    i10 = i16;
                                }
                                while (i10 > i3) {
                                    i9 = i18 + 1;
                                    this.K[i18] = this.J[i10];
                                    i10 = this.I[i10];
                                    i18 = i9;
                                }
                                i10 = this.J[i10] & 255;
                                if (i5 >= 4096) {
                                    break;
                                }
                                i9 = i18 + 1;
                                this.K[i18] = (byte) i10;
                                this.I[i5] = (short) i15;
                                this.J[i5] = (byte) i10;
                                i15 = i5 + 1;
                                if ((i15 & i7) == 0 && i15 < 4096) {
                                    i6++;
                                    i7 += i15;
                                }
                                i17 = i9;
                                i9 = i11;
                                i11 = i16;
                                i16 = i7;
                                i7 = i10;
                                i10 = i14;
                                i14 = i6;
                                i6 = i17;
                            } else {
                                i10 = i9 + 1;
                                this.K[i9] = this.J[i16];
                                i9 = i10;
                                i15 = i16;
                                i10 = i16;
                            }
                        } else {
                            i6 = h + 1;
                            i7 = (1 << i6) - 1;
                            i5 = i3 + 2;
                            i15 = -1;
                        }
                    } else {
                        break;
                    }
                }
                if (i13 == 0) {
                    i13 = i();
                    if (i13 <= 0) {
                        break;
                    }
                    i = 0;
                }
                i11 += (this.B[i] & 255) << i14;
                i14 += 8;
                i++;
                i13--;
            } else {
                i16 = i7;
                i7 = i10;
                i10 = i14;
                i14 = i6;
                i6 = i9;
                i9 = i11;
                i11 = i15;
                i15 = i5;
            }
            i5 = i6 - 1;
            i6 = i8 + 1;
            this.L[i8] = this.K[i5];
            i12++;
            i8 = i6;
            i6 = i14;
            i14 = i10;
            i10 = i7;
            i7 = i16;
            i17 = i11;
            i11 = i9;
            i9 = i5;
            i5 = i15;
            i15 = i17;
        }
        for (i = i8; i < i2; i++) {
            this.L[i] = (byte) 0;
        }
    }

    private boolean f() {
        return this.b != 0;
    }

    private void g() {
        this.b = 0;
        this.N = 0;
        this.M = null;
        this.f = null;
        this.g = null;
    }

    private int h() {
        int i = 0;
        try {
            i = this.a.read();
        } catch (Exception e) {
            this.b = 1;
        }
        return i;
    }

    private int i() {
        this.C = h();
        int i = 0;
        if (this.C > 0) {
            while (i < this.C) {
                try {
                    int read = this.a.read(this.B, i, this.C - i);
                    if (read == -1) {
                        break;
                    }
                    i += read;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (i < this.C) {
                this.b = 1;
            }
        }
        return i;
    }

    private int[] a(int i) {
        int read;
        int i2 = 0;
        int i3 = i * 3;
        int[] iArr = null;
        byte[] bArr = new byte[i3];
        try {
            read = this.a.read(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            read = 0;
        }
        if (read < i3) {
            this.b = 1;
        } else {
            iArr = new int[256];
            read = 0;
            while (i2 < i) {
                i3 = read + 1;
                int i4 = bArr[read] & 255;
                int i5 = i3 + 1;
                int i6 = bArr[i3] & 255;
                read = i5 + 1;
                i3 = i2 + 1;
                iArr[i2] = (((i4 << 16) | -16777216) | (i6 << 8)) | (bArr[i5] & 255);
                i2 = i3;
            }
        }
        return iArr;
    }

    private void j() {
        int i = 0;
        while (i == 0 && !f()) {
            switch (h()) {
                case 0:
                    break;
                case 33:
                    switch (h()) {
                        case 249:
                            k();
                            break;
                        case 255:
                            i();
                            String str = "";
                            for (int i2 = 0; i2 < 11; i2++) {
                                str = str + ((char) this.B[i2]);
                            }
                            if (!str.equals("NETSCAPE2.0")) {
                                r();
                                break;
                            } else {
                                o();
                                break;
                            }
                        default:
                            r();
                            break;
                    }
                case 44:
                    m();
                    break;
                case 59:
                    i = 1;
                    break;
                default:
                    this.b = 1;
                    break;
            }
        }
    }

    private void k() {
        boolean z = true;
        h();
        int h = h();
        this.D = (h & 28) >> 2;
        if (this.D == 0) {
            this.D = 1;
        }
        if ((h & 1) == 0) {
            z = false;
        }
        this.F = z;
        this.G = p() * 10;
        this.H = h();
        h();
    }

    private void l() {
        String str = "";
        for (int i = 0; i < 6; i++) {
            str = str + ((char) h());
        }
        if (str.startsWith("GIF")) {
            n();
            if (this.c && !f()) {
                this.f = a(this.d);
                this.j = this.f[this.i];
                return;
            }
            return;
        }
        this.b = 1;
    }

    private void m() {
        boolean z;
        int i = 0;
        this.p = p();
        this.q = p();
        this.r = p();
        this.s = p();
        int h = h();
        this.m = (h & 128) != 0;
        if ((h & 64) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.n = z;
        this.o = 2 << (h & 7);
        if (this.m) {
            this.g = a(this.o);
            this.h = this.g;
        } else {
            this.h = this.f;
            if (this.i == this.H) {
                this.j = 0;
            }
        }
        if (this.F) {
            int i2 = this.h[this.H];
            this.h[this.H] = 0;
            i = i2;
        }
        if (this.h == null) {
            this.b = 1;
        }
        if (!f()) {
            e();
            r();
            if (!f()) {
                this.N++;
                b();
                if (this.M == null) {
                    if (this.R) {
                        String a = a();
                        this.M = new GifFrame(this.Q + File.separator + a + ".png", this.G);
                        a(this.x, a);
                    } else {
                        this.M = new GifFrame(this.x, this.G);
                    }
                    this.z = this.M;
                } else {
                    GifFrame gifFrame = this.M;
                    while (gifFrame.nextFrame != null) {
                        gifFrame = gifFrame.nextFrame;
                    }
                    if (this.R) {
                        String a2 = a();
                        gifFrame.nextFrame = new GifFrame(this.Q + File.separator + a2 + ".png", this.G);
                        a(this.x, a2);
                    } else {
                        gifFrame.nextFrame = new GifFrame(this.x, this.G);
                    }
                }
                if (this.F) {
                    this.h[this.H] = i;
                }
                q();
                this.O.parseOk(true, this.N);
            }
        }
    }

    private void n() {
        this.width = p();
        this.height = p();
        int h = h();
        this.c = (h & 128) != 0;
        this.d = 2 << (h & 7);
        this.i = h();
        this.l = h();
    }

    private void o() {
        do {
            i();
            if (this.B[0] == (byte) 1) {
                this.e = (this.B[1] & 255) | ((this.B[2] & 255) << 8);
            }
            if (this.C <= 0) {
                return;
            }
        } while (!f());
    }

    private int p() {
        return h() | (h() << 8);
    }

    private void q() {
        this.E = this.D;
        this.t = this.p;
        this.u = this.q;
        this.v = this.r;
        this.w = this.s;
        this.y = this.x;
        this.k = this.j;
        this.D = 0;
        this.F = false;
        this.G = 0;
        this.g = null;
    }

    private void r() {
        do {
            i();
            if (this.C <= 0) {
                return;
            }
        } while (!f());
    }
}
