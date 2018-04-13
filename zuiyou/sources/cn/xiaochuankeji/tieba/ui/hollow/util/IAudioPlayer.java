package cn.xiaochuankeji.tieba.ui.hollow.util;

import cn.xiaochuankeji.tieba.ui.hollow.data.AudioDataBean;

public interface IAudioPlayer {

    public interface a {
        void a(long j);

        void a(PlayerStatus playerStatus);
    }

    public enum PlayerStatus {
        PREPARE,
        LOADING,
        PLAYING,
        PAUSE,
        END
    }

    void a();

    void a(AudioDataBean audioDataBean, a aVar);

    void a(String str, long j, a aVar);

    void b();

    void c();

    void d();

    void e();
}
