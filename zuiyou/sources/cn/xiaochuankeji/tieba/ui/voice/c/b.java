package cn.xiaochuankeji.tieba.ui.voice.c;

import android.media.AudioRecord;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import cn.xiaochuankeji.tieba.common.medialib.SoundEffect;
import cn.xiaochuankeji.tieba.common.medialib.f;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class b {
    private final cn.xiaochuankeji.tieba.common.medialib.a a;
    private final AtomicBoolean b = new AtomicBoolean(false);
    private final AtomicBoolean c = new AtomicBoolean(false);
    private final SoundEffect d = new SoundEffect();
    private final HandlerThread e;
    private HandlerThread f;
    private final Handler g;
    private Handler h;
    private final Handler i;
    private cn.xiaochuankeji.tieba.common.medialib.b.b j;
    private AudioRecord k;
    private float l;
    private ByteBuffer m;
    private String n;
    private final AtomicBoolean o = new AtomicBoolean(false);
    private volatile int p;
    private Object q;
    private a r;

    public interface a {
        void a(int i);

        void a(long j);
    }

    public b(cn.xiaochuankeji.tieba.common.medialib.a aVar) {
        this.a = aVar;
        this.d.initialize();
        this.e = new HandlerThread("AudioRecordThread");
        this.e.start();
        this.g = new Handler(this.e.getLooper());
        this.i = new Handler(Looper.getMainLooper(), new Callback(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                if (message.what == 100 && this.a.j != null) {
                    this.a.j.a((Throwable) message.obj);
                }
                if (message.what == 200 && this.a.r != null) {
                    this.a.r.a(this.a.p);
                }
                return true;
            }
        });
        c();
    }

    private void c() {
        this.q = new Object();
        this.f = new HandlerThread("AudioVolumeThread");
        this.f.start();
        this.h = new Handler(this.f.getLooper());
    }

    public void a(cn.xiaochuankeji.tieba.common.medialib.b.b bVar) {
        this.j = bVar;
    }

    private void d() {
        if (Thread.currentThread() != this.e) {
            throw new IllegalStateException("Wrong thread");
        }
    }

    public boolean a() {
        return this.c.get();
    }

    public void a(final String str, final float f, final cn.xiaochuankeji.tieba.common.medialib.b.a aVar) {
        if (!this.b.get() && !this.c.get()) {
            this.b.set(true);
            this.o.set(false);
            this.g.post(new Runnable(this) {
                final /* synthetic */ b d;

                public void run() {
                    int a = this.d.a(str, f);
                    if (a >= 0) {
                        this.d.c.set(true);
                        this.d.a(a, aVar);
                    }
                    this.d.b.set(false);
                }
            });
            this.h.post(new Runnable(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void run() {
                    Object obj = 1;
                    do {
                        synchronized (this.a.q) {
                            if (this.a.c.get()) {
                                if (obj != null) {
                                    if (this.a.r != null) {
                                        this.a.r.a(System.currentTimeMillis());
                                    }
                                    obj = null;
                                }
                                this.a.i.sendEmptyMessage(200);
                            }
                            try {
                                this.a.q.wait(60);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } while (!this.a.o.get());
                }
            });
        }
    }

    private int a(String str, float f) {
        int i;
        int i2 = 2;
        d();
        Process.setThreadPriority(-19);
        this.n = str;
        this.l = f;
        if (this.a.b == 1) {
            i = 16;
        } else if (this.a.b == 2) {
            i = 12;
        } else {
            i = 1;
        }
        if (this.a.c == 1) {
            i2 = 3;
        } else if (this.a.c != 2) {
            i2 = 1;
        }
        int minBufferSize = AudioRecord.getMinBufferSize(this.a.a, i, i2);
        this.k = new AudioRecord(5, this.a.a, i, i2, minBufferSize);
        try {
            this.k.startRecording();
            if (this.k.getRecordingState() != 3) {
                a(new SecurityException("no audio record permission"));
                return -1;
            }
            e();
            return minBufferSize;
        } catch (Throwable e) {
            a(e);
            return -1;
        }
    }

    private void a(Throwable th) {
        this.i.sendMessage(this.i.obtainMessage(100, th));
    }

    public void a(int i, cn.xiaochuankeji.tieba.common.medialib.b.a aVar) {
        Object obj = 1;
        d();
        FileOutputStream fileOutputStream = null;
        this.m = ByteBuffer.allocateDirect(i);
        while (true) {
            FileOutputStream fileOutputStream2;
            Object obj2;
            int read = this.k.read(this.m, this.m.capacity());
            if (read > 0) {
                if (obj != null) {
                    long a = aVar.a();
                    if (a > 0) {
                        a(a, SystemClock.elapsedRealtime() - 50);
                    }
                    try {
                        fileOutputStream2 = new FileOutputStream(this.n, true);
                        obj2 = null;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        fileOutputStream2 = fileOutputStream;
                        obj2 = null;
                    }
                } else {
                    fileOutputStream2 = fileOutputStream;
                    obj2 = obj;
                }
                a(this.m, i);
                this.d.putSamples(this.m, this.l, 1.0f / this.l, read, this.a.c, this.a.b, this.a.a);
                int i2 = read;
                do {
                    try {
                        i2 = this.d.receiveSamples(this.m, this.m.capacity(), this.a.c, this.a.b);
                        if (i2 > 0) {
                            fileOutputStream2.write(this.m.array(), this.m.arrayOffset(), i2);
                            continue;
                        } else {
                            continue;
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        continue;
                    }
                } while (i2 > 0);
            } else {
                fileOutputStream2 = fileOutputStream;
                obj2 = obj;
            }
            if (this.o.get()) {
                try {
                    fileOutputStream2.close();
                    return;
                } catch (IOException e22) {
                    e22.printStackTrace();
                    return;
                }
            }
            fileOutputStream = fileOutputStream2;
            obj = obj2;
        }
    }

    public void a(final int i) {
        if (this.c.get()) {
            Log.d("VoiceRecorder", "stop");
            this.o.set(true);
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            this.g.post(new Runnable(this) {
                final /* synthetic */ b c;

                public void run() {
                    this.c.c(i);
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("VoiceRecorder", "stop done");
            synchronized (this.q) {
                this.q.notify();
            }
        }
    }

    public void b(final int i) {
        Log.d("VoiceRecorder", "forceStop");
        this.o.set(true);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.g.post(new Runnable(this) {
            final /* synthetic */ b c;

            public void run() {
                this.c.c(i);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("VoiceRecorder", "stop done");
        synchronized (this.q) {
            this.q.notify();
        }
    }

    private void c(int i) {
        d();
        Log.d("VoiceRecorder", "stopOnAudioRecordThread");
        try {
            if (this.k != null) {
                this.k.stop();
                this.k.release();
                this.k = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        d(i);
        this.n = null;
        this.c.set(false);
    }

    public void b() {
        this.d.release();
        this.g.removeCallbacksAndMessages(null);
        this.e.getLooper().quit();
    }

    private void e() {
        FileOutputStream fileOutputStream;
        IOException e;
        Throwable th;
        try {
            fileOutputStream = new FileOutputStream(this.n);
            try {
                fileOutputStream.write(new byte[44]);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            fileOutputStream = null;
            e22.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    private void a(long j, long j2) {
        RandomAccessFile randomAccessFile;
        IOException e;
        Throwable th;
        int i = 0;
        long j3 = j2 - j;
        if (j3 > 0) {
            try {
                randomAccessFile = new RandomAccessFile(this.n, "rw");
                try {
                    int a = (int) f.a((long) (((float) j3) / this.l), this.a.a, this.a.b);
                    byte[] bArr = new byte[(this.a.b * 1024)];
                    while (i < a) {
                        int min = Math.min(a - i, bArr.length);
                        randomAccessFile.write(bArr, 0, min);
                        i += min;
                    }
                    randomAccessFile.close();
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e2 = e3;
                    try {
                        e2.printStackTrace();
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e5) {
                e22 = e5;
                randomAccessFile = null;
                e22.printStackTrace();
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Throwable th3) {
                th = th3;
                randomAccessFile = null;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        }
    }

    private void d(int i) {
        IOException e;
        Throwable th;
        RandomAccessFile randomAccessFile = null;
        RandomAccessFile randomAccessFile2;
        try {
            randomAccessFile2 = new RandomAccessFile(this.n, "rw");
            try {
                int length = (int) randomAccessFile2.length();
                int i2 = length - 44;
                int a = (int) f.a((long) i, this.a.a, this.a.b);
                if (a > i2) {
                    randomAccessFile2.seek((long) length);
                    byte[] bArr = new byte[(this.a.b * 1024)];
                    while (i2 < a) {
                        int min = Math.min(a - i2, bArr.length);
                        randomAccessFile2.write(bArr, 0, min);
                        i2 += min;
                    }
                    i2 = (int) randomAccessFile2.length();
                } else {
                    i2 = a + 44;
                }
                byte[] a2 = a((long) (i2 - 44), (long) ((i2 - 44) + 36), (long) this.a.a, this.a.b, (long) ((this.a.c * this.a.a) * this.a.b), (byte) (this.a.c * 8));
                randomAccessFile2.seek(0);
                randomAccessFile2.write(a2);
                randomAccessFile2.close();
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                randomAccessFile = randomAccessFile2;
                try {
                    e2.printStackTrace();
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    randomAccessFile2 = randomAccessFile;
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
                throw th;
            }
        } catch (IOException e5) {
            e22 = e5;
            e22.printStackTrace();
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        } catch (Throwable th4) {
            th = th4;
            randomAccessFile2 = null;
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
            throw th;
        }
    }

    private byte[] a(long j, long j2, long j3, int i, long j4, byte b) {
        return new byte[]{(byte) 82, (byte) 73, (byte) 70, (byte) 70, (byte) ((int) (255 & j2)), (byte) ((int) ((j2 >> 8) & 255)), (byte) ((int) ((j2 >> 16) & 255)), (byte) ((int) ((j2 >> 24) & 255)), (byte) 87, (byte) 65, (byte) 86, (byte) 69, (byte) 102, (byte) 109, (byte) 116, (byte) 32, (byte) 16, (byte) 0, (byte) 0, (byte) 0, (byte) 1, (byte) 0, (byte) i, (byte) 0, (byte) ((int) (255 & j3)), (byte) ((int) ((j3 >> 8) & 255)), (byte) ((int) ((j3 >> 16) & 255)), (byte) ((int) ((j3 >> 24) & 255)), (byte) ((int) (255 & j4)), (byte) ((int) ((j4 >> 8) & 255)), (byte) ((int) ((j4 >> 16) & 255)), (byte) ((int) ((j4 >> 24) & 255)), (byte) ((b / 8) * i), (byte) 0, b, (byte) 0, (byte) 100, (byte) 97, (byte) 116, (byte) 97, (byte) ((int) (255 & j)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 24) & 255))};
    }

    private void a(ByteBuffer byteBuffer, int i) {
        int i2 = i / 2;
        short[] sArr = new short[i2];
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(sArr);
        double d = 0.0d;
        for (int i3 = 0; i3 < i2; i3++) {
            d += (double) (sArr[i3] * sArr[i3]);
        }
        if (i2 > 0) {
            this.p = (int) Math.sqrt(d / ((double) i2));
        }
    }

    public void a(a aVar) {
        this.r = aVar;
    }
}
