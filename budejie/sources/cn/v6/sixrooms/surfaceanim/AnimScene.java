package cn.v6.sixrooms.surfaceanim;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimRender;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimSceneType;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.protocol.SceneBean;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneType;
import cn.v6.sixrooms.surfaceanim.util.FPSDetectionUtil;
import cn.v6.sixrooms.surfaceanim.util.FrameType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AnimScene implements IAnimRender {
    public static final int RENDER_BEGIN = 1;
    public static final int RENDER_END = 2;
    public static final int RENDER_PRE = 0;
    private List<AnimSceneElement> a = new ArrayList();
    private int b = 0;
    private FPSDetectionUtil c = new FPSDetectionUtil();
    protected boolean mIsSceneElementLoaded;
    protected int mOffsetX;
    protected int mOffsetY;
    protected SceneBean mSceneBean;
    protected SceneParameter mSceneParameter;

    public static class SceneParameter implements Serializable {
        private PointI a;
        private int b = 1;
        private int c = Integer.MAX_VALUE;
        private String d;

        public void setMaxFrameNum(int i) {
            this.c = i;
        }

        public int getMaxFrameNum() {
            return this.c;
        }

        public void setCurFrameNum(int i) {
            this.b = i;
        }

        public synchronized int getCurFrameNum() {
            return this.b;
        }

        public void setPoint(PointI pointI) {
            this.a = pointI;
        }

        public PointI getPoint() {
            return this.a;
        }

        public void plusOneFrame() {
            this.b++;
        }

        public void plusFrames(int i) {
            this.b += i;
        }

        public void reset() {
            this.b = 1;
        }

        public String getResPath() {
            return this.d;
        }

        public void setResPath(String str) {
            this.d = str;
        }
    }

    public abstract FrameType getFrameType();

    protected abstract int initMaxFrames();

    protected abstract void initResources();

    protected abstract void initSceneElement();

    protected abstract void releaseResources();

    public AnimScene(SceneParameter sceneParameter) {
        this.mSceneParameter = sceneParameter;
        getSceneParameter().setMaxFrameNum(initMaxFrames());
    }

    public void setSceneBean(SceneBean sceneBean) {
        this.mSceneBean = sceneBean;
    }

    public SceneBean getSceneBean() {
        return this.mSceneBean;
    }

    public void setElementInitialized(boolean z) {
        this.mIsSceneElementLoaded = z;
    }

    public boolean ismIsSceneElementLoaded() {
        return this.mIsSceneElementLoaded;
    }

    public IAnimSceneType getSceneType() {
        return new AnimSceneType(getClass().hashCode());
    }

    public void setFPS(int i) {
        this.c.setFPS(i);
    }

    public int getRenderStatus() {
        return this.b;
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    public boolean render(Canvas canvas) {
        if (this.mSceneParameter.getPoint() == null) {
            this.b = 0;
            return false;
        }
        this.c.dropAnchor();
        this.b = 1;
        for (int i = 0; i < this.c.getSkipFramesCount(); i++) {
            for (AnimSceneElement frameControl : this.a) {
                frameControl.frameControl(this.mSceneParameter.getCurFrameNum());
            }
            this.mSceneParameter.plusOneFrame();
        }
        for (AnimSceneElement frameControl2 : this.a) {
            frameControl2.draw(canvas);
        }
        if (this.mSceneParameter.getCurFrameNum() >= this.mSceneParameter.getMaxFrameNum()) {
            this.b = 2;
            this.c.reset();
            this.mSceneParameter.reset();
            return true;
        }
        this.mSceneParameter.plusOneFrame();
        return false;
    }

    public void setSceneParameter(SceneParameter sceneParameter) {
        this.mSceneParameter = sceneParameter;
    }

    public SceneParameter getSceneParameter() {
        return this.mSceneParameter;
    }

    public int addElement(AnimSceneElement animSceneElement) {
        if (this.a.add(animSceneElement)) {
            return this.a.size() - 1;
        }
        return -1;
    }

    public int addElement(int i, AnimSceneElement animSceneElement) {
        this.a.add(i, animSceneElement);
        return i;
    }

    public int addElement(int i, String str, AnimSceneElement animSceneElement) {
        animSceneElement.setElementName(str);
        return addElement(i, animSceneElement);
    }

    public int addElement(String str, AnimSceneElement animSceneElement) {
        animSceneElement.setElementName(str);
        return addElement(animSceneElement);
    }

    public AnimSceneElement getSceneElement(String str) {
        for (AnimSceneElement animSceneElement : this.a) {
            if (str.equals(animSceneElement.getElementName())) {
                return animSceneElement;
            }
        }
        return null;
    }

    public void clearSceneElements() {
        this.a.clear();
        this.mIsSceneElementLoaded = false;
    }

    public List<AnimSceneElement> getAnimSceneElements() {
        return this.a;
    }

    public void resetSceneParameter() {
        if (this.mSceneParameter == null) {
            this.mSceneParameter = new SceneParameter();
        } else {
            this.mSceneParameter.reset();
        }
    }
}
