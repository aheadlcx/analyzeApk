package cn.xiaochuankeji.tieba.ui.hollow.util;

import rx.d;

public interface IAudioRecorder {

    public interface a {
        void a(long j);

        void a(RecorderStatus recorderStatus);
    }

    public enum RecorderStatus {
        PREPARE,
        RECORDING,
        PAUSE,
        FILLED,
        FAILURE
    }

    void a();

    void a(long j);

    void a(a aVar);

    void b();

    void c();

    void d();

    d<String> e();
}
