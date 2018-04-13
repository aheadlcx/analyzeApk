package cn.v6.sixrooms.surfaceanim.flybanner.notification;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.FrameType;

public class NotificationScene extends SpecialScene {
    public NotificationScene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    public FrameType getFrameType() {
        return FrameType.NOTIFICATION_FRAME;
    }

    protected int initMaxFrames() {
        return 160;
    }

    protected void initResources() {
        AnimSceneResManager.getInstance().addBitmap(getSceneType(), R.drawable.default_photo, true);
        GiftSceneUtil.getOriginIconBitmap(((NotificationSceneParameter) this.mSceneParameter).getPicuser(), null);
    }

    protected void initSceneElement() {
        addElement(new NotificationElement(this));
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
