package cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity;

import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;

public class b {
    public int a = 0;
    public String b;
    @DrawableRes
    public int c;
    @FloatRange(from = 0.0d, to = 1.0d)
    public float d;
    public long e;
    public long f;

    public b(int i) {
        this.a = i;
    }

    public String toString() {
        return "StickerEvent{event=" + this.a + ", source='" + this.b + '\'' + ", res=" + this.c + ", percent=" + this.d + '}';
    }
}
