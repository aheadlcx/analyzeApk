package cn.v6.sixrooms.bean;

import cn.v6.sixrooms.animation.InterfaceAnimationDraw;

public class BeanAnimation {
    public int alpha = 255;
    public int[] alphaArray;
    public int[] alphaOffset;
    public int[][] alphaTime;
    public boolean bold = false;
    public String[] color;
    public float degrees = 0.0f;
    public int distanceShowInfo = -1;
    public int[] distanceShowTime;
    public int[][] drawableArray;
    public int drawableFps = 1;
    public int drawableId;
    public int[][] drawableTime;
    public String drawableUrl;
    public boolean fillAfter = true;
    public int[] fontSize;
    public boolean isCanClone = true;
    public boolean isGift = false;
    public boolean isSender = false;
    public boolean isSetLandOffset = false;
    public boolean isShowInLand = true;
    public float landScaleH = -1.0f;
    public float landScaleW = -1.0f;
    public InterfaceAnimationDraw mInterfaceAnimationDraw = null;
    public int offsetLandX;
    public int offsetLandY;
    public int runTime = -1;
    public float[] scaleDx;
    public float[] scaleDy;
    public float scaleH = 1.0f;
    public float[] scaleHInfo;
    public float[] scaleHOffset;
    public int[][] scaleHTime;
    public boolean scaleInCenter = true;
    public int[][] scaleOffsetTime;
    public float scaleW = 1.0f;
    public float[] scaleWInfo;
    public float[] scaleWOffset;
    public int[][] scaleWTime;
    public int[] scrollTime;
    public int[] scrollY;
    public int[] showTime;
    public String[] text;
    public int translateX;
    public int[] translateXInfo;
    public int[] translateXOffset;
    public int[][] translateXTime;
    public int translateY;
    public int[] translateYInfo;
    public int[] translateYOffset;
    public int[][] translateYTime;

    public BeanAnimation cloneEndStatus() {
        if (!this.isCanClone) {
            return null;
        }
        BeanAnimation beanAnimation = new BeanAnimation();
        beanAnimation.drawableId = this.drawableId;
        beanAnimation.offsetLandX = this.offsetLandX;
        beanAnimation.offsetLandY = this.offsetLandY;
        beanAnimation.text = this.text;
        beanAnimation.color = this.color;
        beanAnimation.isSender = this.isSender;
        beanAnimation.isGift = this.isGift;
        beanAnimation.bold = this.bold;
        beanAnimation.fontSize = this.fontSize;
        beanAnimation.drawableUrl = this.drawableUrl;
        if (this.alphaArray != null) {
            beanAnimation.alpha = this.alpha + this.alphaArray[this.alphaArray.length - 1];
        } else {
            beanAnimation.alpha = this.alpha;
        }
        if (this.translateXInfo != null) {
            beanAnimation.translateX = this.translateX + this.translateXInfo[this.translateXInfo.length - 1];
        } else {
            beanAnimation.translateX = this.translateX;
        }
        if (this.translateYInfo != null) {
            beanAnimation.translateY = this.translateY + this.translateYInfo[this.translateYInfo.length - 1];
        } else {
            beanAnimation.translateY = this.translateY;
        }
        if (this.scaleWInfo != null) {
            beanAnimation.scaleW = this.scaleW + this.scaleWInfo[this.scaleWInfo.length - 1];
        } else {
            beanAnimation.scaleW = this.scaleW;
        }
        if (this.scaleHInfo != null) {
            beanAnimation.scaleH = this.scaleW + this.scaleHInfo[this.scaleHInfo.length - 1];
        } else {
            beanAnimation.scaleH = this.scaleH;
        }
        beanAnimation.degrees = this.degrees;
        beanAnimation.isShowInLand = this.isShowInLand;
        return beanAnimation;
    }
}
