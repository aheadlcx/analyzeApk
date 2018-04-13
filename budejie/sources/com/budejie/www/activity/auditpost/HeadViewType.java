package com.budejie.www.activity.auditpost;

public enum HeadViewType {
    TEXT_VIEW(1),
    IMAGE_VIEW(2),
    GIF_VIEW(3),
    VOICE_VIEW(4),
    VIDEO_VIEW(5);
    
    private int type;

    private HeadViewType(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }
}
