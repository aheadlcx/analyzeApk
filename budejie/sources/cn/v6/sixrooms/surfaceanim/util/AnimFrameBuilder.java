package cn.v6.sixrooms.surfaceanim.util;

import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimSceneFrame;
import cn.v6.sixrooms.surfaceanim.animinterface.IRoomParameter;
import cn.v6.sixrooms.surfaceanim.annotation.SceneFrameOwner;
import cn.v6.sixrooms.surfaceanim.flybanner.FlyBannerSceneFrame;
import cn.v6.sixrooms.surfaceanim.flybanner.notification.NotificationSceneFrame;
import cn.v6.sixrooms.surfaceanim.flyframe.FlySceneFrame;
import cn.v6.sixrooms.surfaceanim.giftframe.GiftSceneFrame;
import cn.v6.sixrooms.surfaceanim.smaillfly.SmaillFlySceneFrame;
import cn.v6.sixrooms.surfaceanim.specialenterframe.SpecialenterSceneFrame;

public class AnimFrameBuilder {
    public static AnimSceneFrame createAnimFrame(AnimScene animScene, IRoomParameter iRoomParameter) {
        switch (a.a[animScene.getFrameType().ordinal()]) {
            case 1:
                return new GiftSceneFrame(iRoomParameter);
            case 2:
                return new FlySceneFrame(iRoomParameter);
            case 3:
                return new SpecialenterSceneFrame(iRoomParameter);
            case 4:
                return new FlyBannerSceneFrame(iRoomParameter);
            case 5:
                return new SmaillFlySceneFrame(iRoomParameter);
            case 6:
                return new NotificationSceneFrame(iRoomParameter);
            default:
                return null;
        }
    }

    public static int getAnimFrameKey(Class<? extends AnimSceneFrame> cls) {
        return cls.hashCode();
    }

    public static int getAnimFrameKey(AnimScene animScene) {
        switch (a.a[animScene.getFrameType().ordinal()]) {
            case 1:
                return GiftSceneFrame.class.hashCode();
            case 2:
                return FlySceneFrame.class.hashCode();
            case 3:
                return SpecialenterSceneFrame.class.hashCode();
            case 4:
                return FlyBannerSceneFrame.class.hashCode();
            case 5:
                return SmaillFlySceneFrame.class.hashCode();
            case 6:
                return NotificationSceneFrame.class.hashCode();
            default:
                try {
                    return Class.forName(((SceneFrameOwner) animScene.getClass().getAnnotation(SceneFrameOwner.class)).value()).hashCode();
                } catch (Exception e) {
                    e.hashCode();
                    return -1;
                }
        }
    }
}
