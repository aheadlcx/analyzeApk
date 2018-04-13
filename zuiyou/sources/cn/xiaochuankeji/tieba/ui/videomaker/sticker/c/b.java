package cn.xiaochuankeji.tieba.ui.videomaker.sticker.c;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;

final class b extends ScheduledThreadPoolExecutor {

    private static final class a {
        private static final b a = new b();
    }

    static b a() {
        return a.a;
    }

    private b() {
        super(2, new DiscardPolicy());
    }
}
