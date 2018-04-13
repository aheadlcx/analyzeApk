package com.iflytek.cloud.record;

import android.media.AudioRecord;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.thirdparty.cb;

public class PcmRecorder extends Thread {
    public static final int RATE16K = 16000;
    public static final int READ_INTERVAL40MS = 40;
    private final short a;
    private byte[] b;
    private AudioRecord c;
    private PcmRecordListener d;
    private PcmRecordListener e;
    private volatile boolean f;
    private double g;
    private double h;
    private int i;
    private int j;
    private int k;
    private int l;

    public interface PcmRecordListener {
        void onError(SpeechError speechError);

        void onRecordBuffer(byte[] bArr, int i, int i2);

        void onRecordReleased();

        void onRecordStarted(boolean z);
    }

    public PcmRecorder(int i, int i2) {
        this(i, i2, 1);
    }

    public PcmRecorder(int i, int i2, int i3) {
        this.a = (short) 16;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = false;
        this.g = 0.0d;
        this.h = 0.0d;
        this.i = 16000;
        this.j = 40;
        this.k = 40;
        this.l = i3;
        this.i = i;
        this.j = i2;
        if (this.j < 40 || this.j > 100) {
            this.j = 40;
        }
        this.k = 10;
    }

    private double a(byte[] bArr, int i) {
        if (bArr == null || i <= 0) {
            return 0.0d;
        }
        double d = 0.0d;
        for (byte b : bArr) {
            d += (double) b;
        }
        d /= (double) bArr.length;
        double d2 = 0.0d;
        for (byte b2 : bArr) {
            d2 += Math.pow(((double) b2) - d, 2.0d);
        }
        return Math.sqrt(d2 / ((double) (bArr.length - 1)));
    }

    private int a() throws SpeechError {
        if (this.c == null || this.d == null) {
            return 0;
        }
        int read = this.c.read(this.b, 0, this.b.length);
        if (read > 0 && this.d != null) {
            this.d.onRecordBuffer(this.b, 0, read);
            return read;
        } else if (read >= 0) {
            return read;
        } else {
            cb.c("Record read data error: " + read);
            throw new SpeechError(20006);
        }
    }

    private void b() {
        synchronized (this) {
            try {
                if (this.c != null) {
                    cb.a("release record begin");
                    this.c.release();
                    this.c = null;
                    if (this.e != null) {
                        this.e.onRecordReleased();
                        this.e = null;
                    }
                    cb.a("release record over");
                }
            } catch (Exception e) {
                cb.c(e.toString());
            }
        }
    }

    protected void a(short s, int i, int i2) throws SpeechError {
        if (this.c != null) {
            b();
        }
        int i3 = (i * i2) / 1000;
        int i4 = (((i3 * 4) * 16) * s) / 8;
        int i5 = s == (short) 1 ? 2 : 3;
        int minBufferSize = AudioRecord.getMinBufferSize(i, i5, 2);
        if (i4 < minBufferSize) {
            i4 = minBufferSize;
        }
        this.c = new AudioRecord(this.l, i, i5, 2, i4);
        this.b = new byte[(((i3 * s) * 16) / 8)];
        cb.a("\nSampleRate:" + i + "\nChannel:" + i5 + "\nFormat:" + 2 + "\nFramePeriod:" + i3 + "\nBufferSize:" + i4 + "\nMinBufferSize:" + minBufferSize + "\nActualBufferSize:" + this.b.length + "\n");
        if (this.c.getState() != 1) {
            cb.a("create AudioRecord error");
            throw new SpeechError(20006);
        }
    }

    protected void finalize() throws Throwable {
        b();
        super.finalize();
    }

    public void run() {
        long currentTimeMillis;
        Object obj = 1;
        int i = 0;
        while (!this.f) {
            try {
                a((short) 1, this.i, this.j);
                break;
            } catch (Exception e) {
                i++;
                if (i < 10) {
                    sleep(40);
                } else {
                    throw new SpeechError(20006);
                }
            } catch (Exception e2) {
                i++;
                if (i < 10) {
                    sleep(40);
                } else {
                    throw new SpeechError(20006);
                }
            } catch (Throwable e3) {
                cb.a(e3);
                if (this.d != null) {
                    this.d.onError(new SpeechError(20006));
                }
            }
        }
        i = 0;
        while (!this.f) {
            this.c.startRecording();
            if (this.c.getRecordingState() != 3) {
                throw new SpeechError(20006);
            }
            if (this.d != null) {
                this.d.onRecordStarted(true);
            }
            currentTimeMillis = System.currentTimeMillis();
            while (!this.f) {
                int a = a();
                if (obj != null) {
                    this.g += (double) a;
                    this.h += a(this.b, this.b.length);
                    if (System.currentTimeMillis() - currentTimeMillis >= 1000) {
                        if (this.g != 0.0d || this.h == 0.0d) {
                            cb.c("cannot get record permission, get invalid audio data.");
                            throw new SpeechError(20006);
                        }
                        obj = null;
                    }
                }
                if (this.b.length > a) {
                    cb.b("current record read size is less than buffer size: " + a);
                    sleep((long) this.k);
                }
            }
            b();
        }
        if (this.d != null) {
            this.d.onRecordStarted(true);
        }
        currentTimeMillis = System.currentTimeMillis();
        while (!this.f) {
            int a2 = a();
            if (obj != null) {
                this.g += (double) a2;
                this.h += a(this.b, this.b.length);
                if (System.currentTimeMillis() - currentTimeMillis >= 1000) {
                    if (this.g != 0.0d) {
                    }
                    cb.c("cannot get record permission, get invalid audio data.");
                    throw new SpeechError(20006);
                }
            }
            if (this.b.length > a2) {
                cb.b("current record read size is less than buffer size: " + a2);
                sleep((long) this.k);
            }
        }
        b();
    }

    public void startRecording(PcmRecordListener pcmRecordListener) throws SpeechError {
        this.d = pcmRecordListener;
        setPriority(10);
        start();
    }

    public void stopRecord(boolean z) {
        this.f = true;
        if (this.e == null) {
            this.e = this.d;
        }
        this.d = null;
        if (z) {
            synchronized (this) {
                try {
                    cb.a("stopRecord...release");
                    if (this.c != null) {
                        if (3 == this.c.getRecordingState() && 1 == this.c.getState()) {
                            cb.a("stopRecord releaseRecording ing...");
                            this.c.release();
                            cb.a("stopRecord releaseRecording end...");
                            this.c = null;
                        }
                        if (this.e != null) {
                            this.e.onRecordReleased();
                            this.e = null;
                        }
                    }
                } catch (Exception e) {
                    cb.c(e.toString());
                }
            }
        }
        cb.a("stop record");
    }
}
