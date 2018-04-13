package qsbk.app.live.widget;

import com.opensource.svgaplayer.SVGADynamicEntity;

class ja implements Runnable {
    final /* synthetic */ SVGADynamicEntity a;
    final /* synthetic */ iz b;

    ja(iz izVar, SVGADynamicEntity sVGADynamicEntity) {
        this.b = izVar;
        this.a = sVGADynamicEntity;
    }

    public void run() {
        this.b.c.mHandler.post(new jb(this, SuperUserEnterAnimLayout.getCircleBitmap(SuperUserEnterAnimLayout.getbitmap(this.b.a.getUserAvatar()))));
    }
}
