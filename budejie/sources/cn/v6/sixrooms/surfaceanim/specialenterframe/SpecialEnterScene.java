package cn.v6.sixrooms.surfaceanim.specialenterframe;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.FrameType;

public class SpecialEnterScene extends AnimScene {

    public static class SpecialEnterParameter extends SceneParameter {
        private String a;
        private String b;
        private String c;

        public String getRich() {
            return this.a;
        }

        public void setRich(String str) {
            this.a = str;
        }

        public String getRichUrl() {
            return this.c;
        }

        public void setRichUrl(String str) {
            this.c = str;
        }

        public String getContent() {
            return this.b;
        }

        public void setContent(String str) {
            this.b = str;
        }
    }

    public SpecialEnterScene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    protected int initMaxFrames() {
        return 60;
    }

    protected void initResources() {
        AnimSceneResManager.getInstance().addBitmap(getSceneType(), R.drawable.badge_default, true);
        AnimSceneResManager.getInstance().addBitmap(getSceneType(), R.drawable.special_enter_bg, true);
    }

    protected void releaseResources() {
        AnimSceneResManager.getInstance().clearSceneBitmaps(getSceneType());
    }

    public FrameType getFrameType() {
        return FrameType.SPECIALENTER_FRAME;
    }

    protected void initSceneElement() {
        addElement(new SpecialenterBgElement(this));
    }

    public boolean render(Canvas canvas) {
        if (this.mSceneParameter.getPoint() != null) {
            PointI point = this.mSceneParameter.getPoint();
            point.y += this.mOffsetY;
            point = this.mSceneParameter.getPoint();
            point.x += this.mOffsetX;
        }
        boolean render = super.render(canvas);
        if (this.mSceneParameter.getPoint() != null) {
            PointI point2 = this.mSceneParameter.getPoint();
            point2.y -= this.mOffsetY;
            point2 = this.mSceneParameter.getPoint();
            point2.x -= this.mOffsetX;
        }
        return render;
    }
}
