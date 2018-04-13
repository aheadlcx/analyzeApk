package com.kubility.demo;

import android.media.AudioRecord;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MP3Recorder {
    private static MP3Recorder a = new MP3Recorder();
    private a b;
    private boolean c = false;
    private boolean d = false;
    private Handler e;
    private AudioRecord f;
    private int g;

    public static native void close();

    public static native int encode(short[] sArr, short[] sArr2, int i, byte[] bArr);

    public static native int flush(byte[] bArr);

    public static native void init(int i, int i2, int i3, int i4, int i5);

    static {
        System.loadLibrary("mp3lame");
    }

    private MP3Recorder() {
    }

    public static MP3Recorder a() {
        if (a == null) {
            a = new MP3Recorder();
        }
        return a;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void b() {
        if (!this.c) {
            if (this.b == null) {
                try {
                    throw new Exception("missing the init() method");
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            this.c = true;
            new Thread(this) {
                final /* synthetic */ MP3Recorder a;

                {
                    this.a = r1;
                }

                public void run() {
                    Process.setThreadPriority(-19);
                    this.a.g = AudioRecord.getMinBufferSize(this.a.b.c(), 16, this.a.b.b());
                    if (this.a.g >= 0) {
                        this.a.f = new AudioRecord(1, this.a.b.c(), 16, this.a.b.b(), this.a.g * 2);
                        short[] sArr = new short[(((this.a.b.c() * 2) * 1) * 5)];
                        byte[] bArr = new byte[((int) (7200.0d + (((double) (sArr.length * 2)) * 1.25d)))];
                        try {
                            FileOutputStream fileOutputStream;
                            if (this.a.b.d()) {
                                fileOutputStream = new FileOutputStream(this.a.b.a(), true);
                            } else {
                                fileOutputStream = new FileOutputStream(this.a.b.a());
                            }
                            MP3Recorder.a(this.a.b.c(), 1, this.a.b.c(), 32);
                            this.a.d = false;
                            try {
                                if (this.a.f != null) {
                                    this.a.f.startRecording();
                                    try {
                                        int flush;
                                        if (this.a.e != null) {
                                            this.a.e.sendEmptyMessage(1);
                                        }
                                        boolean z = false;
                                        while (this.a.c) {
                                            if (!this.a.d) {
                                                if (z) {
                                                    if (this.a.e != null) {
                                                        this.a.e.sendEmptyMessage(4);
                                                    }
                                                    z = false;
                                                }
                                                int read = this.a.f.read(sArr, 0, this.a.g);
                                                this.a.a(sArr, read);
                                                if (read < 0) {
                                                    if (this.a.e != null) {
                                                        this.a.e.sendEmptyMessage(-4);
                                                    }
                                                } else if (read != 0) {
                                                    read = MP3Recorder.encode(sArr, sArr, read, bArr);
                                                    if (read < 0) {
                                                        if (this.a.e != null) {
                                                            this.a.e.sendEmptyMessage(-5);
                                                            throw new Exception("audio coding error!");
                                                        }
                                                    } else if (read != 0) {
                                                        fileOutputStream.write(bArr, 0, read);
                                                    } else {
                                                        continue;
                                                    }
                                                } else {
                                                    continue;
                                                }
                                                flush = MP3Recorder.flush(bArr);
                                                if (flush < 0 && this.a.e != null) {
                                                    this.a.e.sendEmptyMessage(-5);
                                                }
                                                if (flush != 0) {
                                                    fileOutputStream.write(bArr, 0, flush);
                                                }
                                                MP3Recorder.close();
                                                this.a.c = false;
                                                if (this.a.e == null) {
                                                    this.a.e.sendEmptyMessage(2);
                                                    return;
                                                }
                                                return;
                                            } else if (!z) {
                                                if (this.a.e != null) {
                                                    this.a.e.sendEmptyMessage(3);
                                                }
                                                z = true;
                                            }
                                        }
                                        flush = MP3Recorder.flush(bArr);
                                        this.a.e.sendEmptyMessage(-5);
                                        if (flush != 0) {
                                            fileOutputStream.write(bArr, 0, flush);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        MP3Recorder.close();
                                        this.a.c = false;
                                        if (this.a.e == null) {
                                            this.a.e.sendEmptyMessage(2);
                                            return;
                                        }
                                        return;
                                    } finally {
                                        this.a.f.stop();
                                        this.a.f.release();
                                    }
                                    MP3Recorder.close();
                                    this.a.c = false;
                                    if (this.a.e == null) {
                                        this.a.e.sendEmptyMessage(2);
                                        return;
                                    }
                                    return;
                                }
                                throw new Exception("start the recording is called after init().");
                            } catch (IllegalStateException e2) {
                                if (this.a.e != null) {
                                    this.a.e.sendEmptyMessage(-3);
                                }
                                MP3Recorder.close();
                                this.a.c = false;
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            } catch (Throwable th) {
                                MP3Recorder.close();
                                this.a.c = false;
                            }
                        } catch (FileNotFoundException e4) {
                            if (this.a.e != null) {
                                this.a.e.sendEmptyMessage(-2);
                            }
                        }
                    } else if (this.a.e != null) {
                        try {
                            throw new Exception("missing the init() method");
                        } catch (Exception e5) {
                            e5.printStackTrace();
                            this.a.e.sendEmptyMessage(-1);
                        }
                    }
                }
            }.start();
        }
    }

    private void a(short[] sArr, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < sArr.length) {
            i3 += sArr[i2] * sArr[i2];
            i2++;
        }
        i2 = Math.abs(((int) (((float) i3) / ((float) i))) / 10000) >> 1;
        Message obtainMessage = this.e.obtainMessage();
        obtainMessage.what = 5;
        obtainMessage.obj = Integer.valueOf(i2);
        this.e.sendMessage(obtainMessage);
    }

    public void c() {
        this.c = false;
    }

    public void a(Handler handler) {
        this.e = handler;
    }

    public static void a(int i, int i2, int i3, int i4) {
        init(i, i2, i3, i4, 7);
    }
}
