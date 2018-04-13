package cn.xiaochuankeji.tieba.ui.debug;

import cn.xiaochuan.media.av.XPlayerView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.a;

public class XPlayerActivity extends a {
    XPlayerView a;

    protected int a() {
        return R.layout.test_player;
    }

    protected void i_() {
        this.a = (XPlayerView) findViewById(R.id.player);
        this.a.play("/mnt/sdcard/luoye.webm");
    }

    public void onDestroy() {
        super.onDestroy();
        this.a.destroySurface();
    }
}
