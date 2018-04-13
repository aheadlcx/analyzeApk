package cn.v6.sixrooms.surfaceanim.protocol;

import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.Serializable;

public class PointF implements Serializable {
    private boolean a;
    public float x;
    public float y;

    public PointF(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public PointF(float f, float f2, boolean z) {
        this.x = f;
        this.y = f2;
        this.a = z;
    }

    public void setTransform(boolean z) {
        this.a = z;
    }

    public boolean isTransform() {
        return this.a;
    }

    public PointF transformNewScalePoint() {
        return new PointF(getScaleX(), getScaleY(), true);
    }

    public PointF transformScalePoint() {
        if (!this.a) {
            this.x = getScaleX();
            this.y = getScaleY();
            this.a = true;
        }
        return this;
    }

    public float getScaleX() {
        if (this.a) {
            return this.x;
        }
        return AnimSceneResManager.getInstance().getScalePx(this.x);
    }

    public float getScaleY() {
        if (this.a) {
            return this.y;
        }
        return AnimSceneResManager.getInstance().getScalePx(this.y);
    }

    public void setX(float f) {
        this.x = f;
    }

    public float getX() {
        return this.x;
    }

    public void setY(float f) {
        this.y = f;
    }

    public float getY() {
        return this.y;
    }

    public void set(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public final void negate() {
        this.x = -this.x;
        this.y = -this.y;
    }

    public final void offset(float f, float f2) {
        this.x += f;
        this.y += f2;
    }

    public final boolean equals(float f, float f2) {
        return this.x == f && this.y == f2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PointF pointF = (PointF) obj;
        if (this.x != pointF.x) {
            return false;
        }
        if (this.y != pointF.y) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) ((this.x * 31.0f) + this.y);
    }

    public String toString() {
        return "PointI(" + this.x + ", " + this.y + ")";
    }
}
