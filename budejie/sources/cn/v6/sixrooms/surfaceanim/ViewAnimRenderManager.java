package cn.v6.sixrooms.surfaceanim;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.animinterface.IDrawControl;

public class ViewAnimRenderManager extends AnimRenderManager {
    private IDrawControl a;

    public ViewAnimRenderManager(IDrawControl iDrawControl) {
        this.a = iDrawControl;
        initRenderHandler();
    }

    protected void render() {
        super.render();
        this.a.statDraw();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.a.stopDraw();
    }

    protected void renderStop() {
        super.renderStop();
        this.a.stopDraw();
    }
}
