package com.sensetime.stmobile;

import com.sensetime.stmobile.model.STImage;
import com.sensetime.stmobile.model.STMobile106;
import com.sensetime.stmobile.model.STMobileFaceInfo;
import com.sensetime.stmobile.model.STMobileHandInfo;

public class STHumanAction {
    public int faceCount;
    public STMobileFaceInfo[] faces;
    public int handCount;
    public STMobileHandInfo[] hands;
    public STImage image;
    public boolean imageResult;

    public STMobile106[] getMobileFaces() {
        if (this.faceCount == 0) {
            return null;
        }
        STMobile106[] sTMobile106Arr = new STMobile106[this.faceCount];
        for (int i = 0; i < this.faceCount; i++) {
            sTMobile106Arr[i] = this.faces[i].face106;
        }
        return sTMobile106Arr;
    }

    public boolean replaceMobile106(STMobile106[] sTMobile106Arr) {
        int i = 0;
        if (sTMobile106Arr == null || sTMobile106Arr.length == 0 || this.faces == null || this.faceCount != sTMobile106Arr.length) {
            return false;
        }
        while (i < sTMobile106Arr.length) {
            this.faces[i].face106 = sTMobile106Arr[i];
            i++;
        }
        return true;
    }

    public STMobileFaceInfo[] getFaceInfos() {
        if (this.faceCount == 0) {
            return null;
        }
        return this.faces;
    }

    public STMobileHandInfo[] getHandInfos() {
        if (this.handCount == 0) {
            return null;
        }
        return this.hands;
    }

    public STImage getImage() {
        if (this.imageResult) {
            return this.image;
        }
        return null;
    }
}
