package cn.v6.sixrooms.surfaceanim.flybanner.utils.touch;

import android.graphics.Rect;

public class TouchEntity {
    private Rect a;
    private int b;
    private TouchParameter c;

    public static class TouchParameter {
        String a;
        String b;

        public String getRid() {
            return this.a;
        }

        public TouchParameter setRid(String str) {
            this.a = str;
            return this;
        }

        public String getUid() {
            return this.b;
        }

        public TouchParameter setUid(String str) {
            this.b = str;
            return this;
        }
    }

    public Rect getRect() {
        return this.a;
    }

    public void setRect(Rect rect) {
        this.a = rect;
    }

    public int getWhat() {
        return this.b;
    }

    public void setWhat(int i) {
        this.b = i;
    }

    public TouchParameter getTouchParameter() {
        return this.c;
    }

    public void setTouchParameter(TouchParameter touchParameter) {
        this.c = touchParameter;
    }
}
