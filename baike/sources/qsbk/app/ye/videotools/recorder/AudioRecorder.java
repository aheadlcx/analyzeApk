package qsbk.app.ye.videotools.recorder;

import android.media.AudioRecord;

public class AudioRecorder {
    private AudioRecord a = null;
    private boolean b = false;
    private int c = 22050;
    private int d = 1;
    private int e = 0;
    private AudioBufferCallBack f = null;
    private Thread g = null;
    private boolean h = false;
    private Object i = new Object();

    public interface AudioBufferCallBack {
        void hasData(byte[] bArr, int i);
    }

    class a implements Runnable {
        final /* synthetic */ AudioRecorder a;

        a(AudioRecorder audioRecorder) {
            this.a = audioRecorder;
        }

        public void run() {
            byte[] bArr = new byte[this.a.e];
            while (this.a.b) {
                synchronized (this.a.i) {
                    int read;
                    if (this.a.a != null) {
                        read = this.a.a.read(bArr, 0, this.a.e);
                    } else {
                        read = 0;
                    }
                    if (read > 0 && this.a.f != null) {
                        this.a.f.hasData(bArr, read);
                    }
                }
                if (read > 0 && !this.a.h) {
                    long j = 0;
                    for (read = 0; read < bArr.length; read++) {
                        j += (long) (bArr[read] * bArr[read]);
                    }
                    if (j > 0) {
                        this.a.h = true;
                    }
                }
            }
        }
    }

    public static AudioRecorder createAudioRecorder(AudioBufferCallBack audioBufferCallBack) {
        int minBufferSize = AudioRecord.getMinBufferSize(22050, 16, 2);
        if (minBufferSize > 0) {
            return new AudioRecorder(audioBufferCallBack, 22050, 1, minBufferSize);
        }
        minBufferSize = AudioRecord.getMinBufferSize(16000, 16, 2);
        if (minBufferSize > 0) {
            return new AudioRecorder(audioBufferCallBack, 16000, 1, minBufferSize);
        }
        minBufferSize = AudioRecord.getMinBufferSize(32000, 16, 2);
        if (minBufferSize > 0) {
            return new AudioRecorder(audioBufferCallBack, 32000, 1, minBufferSize);
        }
        minBufferSize = AudioRecord.getMinBufferSize(44100, 16, 2);
        if (minBufferSize > 0) {
            return new AudioRecorder(audioBufferCallBack, 44100, 1, minBufferSize);
        }
        return null;
    }

    public static AudioRecorder createAudioRecorder(AudioBufferCallBack audioBufferCallBack, int i) {
        int minBufferSize = AudioRecord.getMinBufferSize(i, 16, 2);
        if (minBufferSize > 0) {
            return new AudioRecorder(audioBufferCallBack, i, 1, minBufferSize);
        }
        minBufferSize = AudioRecord.getMinBufferSize(22050, 16, 2);
        if (minBufferSize > 0) {
            return new AudioRecorder(audioBufferCallBack, 22050, 1, minBufferSize);
        }
        minBufferSize = AudioRecord.getMinBufferSize(16000, 16, 2);
        if (minBufferSize > 0) {
            return new AudioRecorder(audioBufferCallBack, 16000, 1, minBufferSize);
        }
        minBufferSize = AudioRecord.getMinBufferSize(32000, 16, 2);
        if (minBufferSize > 0) {
            return new AudioRecorder(audioBufferCallBack, 32000, 1, minBufferSize);
        }
        minBufferSize = AudioRecord.getMinBufferSize(44100, 16, 2);
        if (minBufferSize > 0) {
            return new AudioRecorder(audioBufferCallBack, 44100, 1, minBufferSize);
        }
        return null;
    }

    private AudioRecorder(AudioBufferCallBack audioBufferCallBack, int i, int i2, int i3) {
        this.f = audioBufferCallBack;
        this.c = i;
        this.d = i2;
        this.e = i3;
        if (this.e % 2048 != 0) {
            this.e = ((this.e / 2048) + 1) * 2048;
        }
        this.a = new AudioRecord(1, i, 16, 2, this.e);
        this.g = new Thread(new a(this));
    }

    public int getSampleRate() {
        return this.c;
    }

    public int getChannels() {
        return this.d;
    }

    public void start() {
        this.a.startRecording();
        this.b = true;
        this.g.start();
    }

    public void stop() {
        if (this.a != null) {
            this.b = false;
            synchronized (this.i) {
                try {
                    this.a.stop();
                } catch (IllegalStateException e) {
                }
                this.a.release();
                this.a = null;
                this.f = null;
            }
        }
    }

    public boolean isRecordEnable() {
        return this.h;
    }
}
