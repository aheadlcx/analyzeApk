package com.iflytek.cloud.record;

import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.record.PcmRecorder.PcmRecordListener;
import com.iflytek.cloud.thirdparty.cb;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class a extends PcmRecorder {
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private boolean d = false;
    private int e = 16000;
    private final short f = (short) 16;
    private int g = 40;
    private int h = 40;
    private byte[] i = null;
    private RandomAccessFile j = null;
    private String k = null;
    private PcmRecordListener l = null;

    public a(int i, int i2, int i3, String str) {
        super(i, i2, i3);
        this.e = i;
        this.g = i2;
        this.h = i2;
        this.k = str;
    }

    private int a() throws SpeechError {
        if (this.j == null || this.l == null) {
            return 0;
        }
        if (this.b >= this.a) {
            try {
                this.b = 0;
                this.a = this.j.read(this.i, this.b, this.i.length);
                if (this.a < 0) {
                    return -1;
                }
            } catch (IOException e) {
                throw new SpeechError(20006);
            }
        }
        if (this.a <= 0 || this.l == null) {
            return 0;
        }
        int i = this.a - this.b > this.c ? this.c : this.a - this.b;
        this.l.onRecordBuffer(this.i, this.b, i);
        this.b += i;
        return i;
    }

    private void b() {
        if (this.j != null) {
            cb.a("release record begin");
            try {
                this.j.close();
            } catch (Throwable e) {
                cb.a(e);
            }
            this.j = null;
            if (this.l != null) {
                this.l.onRecordReleased();
                this.l = null;
            }
            cb.a("release record over");
        }
        if (this.i != null) {
            this.i = null;
        }
    }

    protected void a(short s, int i, int i2) throws SpeechError {
        this.c = (16 * (((i * 40) / 1000) * s)) / 8;
        this.i = new byte[(this.c * 10)];
        try {
            this.j = new RandomAccessFile(this.k, "r");
        } catch (FileNotFoundException e) {
            throw new SpeechError(20006);
        }
    }

    protected void finalize() throws Throwable {
        b();
        super.finalize();
    }

    public void run() {
        try {
            a((short) 1, this.e, this.g);
            if (this.l != null) {
                this.l.onRecordStarted(true);
            }
            while (!this.d) {
                if (a() < 0) {
                    this.d = true;
                    break;
                }
                sleep((long) this.h);
            }
        } catch (Throwable e) {
            cb.a(e);
            if (this.l != null) {
                this.l.onError(new SpeechError(20006));
            }
        }
        b();
    }

    public void startRecording(PcmRecordListener pcmRecordListener) throws SpeechError {
        this.l = pcmRecordListener;
        setPriority(10);
        start();
    }

    public void stopRecord(boolean z) {
        this.d = true;
    }
}
