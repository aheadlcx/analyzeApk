package cn.v6.sixrooms.surfaceanim.giftframe.giftscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimSceneType;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.SuperFireworksTextUtils;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneType;
import cn.v6.sixrooms.surfaceanim.util.FrameType;
import cn.v6.sixrooms.surfaceanim.util.SceneType;

public abstract class GiftScene extends AnimScene {
    protected SceneType mSceneType;

    public static class GiftSceneParameter extends SceneParameter {
        private String a;
        private String b;
        private String c;
        private int d;
        private long e;
        private int f;
        private int g;
        private int h;

        public void setNumEndFrame(int i) {
            this.h = i;
        }

        public void setNumStartFrame(int i) {
            this.g = i;
        }

        public int getNumStartFrame() {
            return this.g;
        }

        public int getNumEndFrame() {
            return this.h;
        }

        public void setStep(int i) {
            this.f = i;
        }

        public int getStep() {
            return this.f;
        }

        public void setPrice(long j) {
            this.e = j;
        }

        public long getPrice() {
            return this.e;
        }

        public void setIconUrl(String str) {
            this.a = str;
        }

        public void setFromUserName(String str) {
            if (str != null && str.length() > 6) {
                str = str.substring(0, 6) + "...";
            }
            this.b = str;
        }

        public void setGiftName(String str) {
            this.c = new StringBuilder(SuperFireworksTextUtils.s1).append(str).toString();
        }

        public void setGiftNum(int i) {
            this.d = i;
        }

        public String getIconUrl() {
            return this.a;
        }

        public String getText1() {
            return this.b;
        }

        public String getText2() {
            return this.c;
        }

        public int getGiftNum() {
            return this.d;
        }
    }

    public GiftScene(GiftSceneParameter giftSceneParameter) {
        super(giftSceneParameter);
    }

    public IAnimSceneType getSceneType() {
        return new AnimSceneType(this.mSceneType.ordinal());
    }

    public FrameType getFrameType() {
        return FrameType.GIFT_FRAME;
    }

    protected void addBitmap(int i) {
        AnimSceneResManager.getInstance().addBitmap(getSceneType(), i, true);
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
