package com.sensetime.stmobile.model;

public class STMobileFaceInfo {
    public STPoint[] extraFacePoints;
    public int extraFacePointsCount;
    public STPoint[] eyeballCenter;
    public int eyeballCenterPointsCount;
    public STPoint[] eyeballContour;
    public int eyeballContourPointsCount;
    public STMobile106 face106;
    public long faceAction;

    public STMobile106 getFace() {
        return this.face106;
    }

    public void setFace(STMobile106 sTMobile106) {
        this.face106 = sTMobile106;
    }

    public long getFaceAction() {
        return this.faceAction;
    }

    public void setFaceAction(int i) {
        this.faceAction = (long) i;
    }

    public int getExtraFacePointsCount() {
        return this.extraFacePointsCount;
    }

    public STPoint[] getExtraFacePoints() {
        return this.extraFacePoints;
    }

    public int getEyeballCenterPointsCount() {
        return this.eyeballCenterPointsCount;
    }

    public STPoint[] getEyeballCenter() {
        return this.eyeballCenter;
    }

    public int getEyeballContourPointsCount() {
        return this.eyeballContourPointsCount;
    }

    public STPoint[] getEyeballContour() {
        return this.eyeballContour;
    }
}
