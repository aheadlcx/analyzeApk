package com.bdj.picture.edit.bean;

public enum BVType {
    IE_WATERMARK(1),
    IE_PIXELIZATION(2),
    IE_PASTER(3),
    IE_FILTER(4),
    IE_ROTATION(5);
    
    private int bvType;

    private BVType(int i) {
        this.bvType = i;
    }

    public int getBvType() {
        return this.bvType;
    }

    public void setBvType(int i) {
        this.bvType = i;
    }
}
