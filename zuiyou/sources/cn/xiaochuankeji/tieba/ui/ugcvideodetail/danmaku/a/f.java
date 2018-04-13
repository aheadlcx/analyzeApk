package cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a;

import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuJson;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import java.util.List;

public interface f {

    public interface a {
        void a();

        void a(UgcVideoDanmakuJson ugcVideoDanmakuJson);

        void a(UgcVideoInfoBean ugcVideoInfoBean, long j, int i);

        void b();

        void c();

        void d();
    }

    public interface b {
        void a();

        void a(int i);

        void a(long j, int i);

        void a(List<UgcVideoDanmakuJson> list, boolean z, boolean z2);

        void a(boolean z);

        void a(boolean z, boolean z2);

        void b();
    }
}
