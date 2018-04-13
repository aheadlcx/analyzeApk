package com.budejie.www.activity.video.barrage.a;

import com.budejie.www.activity.video.barrage.danmaku.a.a;
import com.budejie.www.activity.video.barrage.danmaku.model.android.DanmakuContext;
import com.budejie.www.activity.video.barrage.danmaku.model.c;
import com.budejie.www.activity.video.barrage.danmaku.model.k;

public interface f {
    void a();

    void a(a aVar, DanmakuContext danmakuContext);

    void a(c cVar);

    void a(boolean z);

    void b();

    void b(boolean z);

    void c();

    void d();

    void e();

    long getCurrentTime();

    k getCurrentVisibleDanmakus();

    f$a getOnDanmakuClickListener();

    void setCallback(c.a aVar);

    void setDrawingThreadType(int i);

    void setOnDanmakuClickListener(f$a f_a);

    void setVisibility(int i);
}
