package master.flame.danmaku.a;

import master.flame.danmaku.danmaku.model.l;

public interface f {

    public interface a {
        boolean a(f fVar);

        boolean a(l lVar);

        boolean b(l lVar);
    }

    l getCurrentVisibleDanmakus();

    a getOnDanmakuClickListener();

    float getXOff();

    float getYOff();
}
