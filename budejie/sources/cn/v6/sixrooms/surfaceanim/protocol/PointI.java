package cn.v6.sixrooms.surfaceanim.protocol;

import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.Serializable;

public class PointI implements Serializable {
    private boolean a;
    public int x;
    public int y;

    public PointI(int i, int i2) {
        this.x = i;
        this.y = i2;
    }

    public PointI(int i, int i2, boolean z) {
        this.x = i;
        this.y = i2;
        this.a = z;
    }

    public void setTransform(boolean z) {
        this.a = z;
    }

    public boolean isTransform() {
        return this.a;
    }

    public PointI transformNewScalePoint() {
        return new PointI(getScaleX(), getScaleY(), true);
    }

    public PointI transformScalePoint() {
        if (!this.a) {
            this.x = getScaleX();
            this.y = getScaleY();
            this.a = true;
        }
        return this;
    }

    public int getScaleX() {
        if (this.a) {
            return this.x;
        }
        return AnimSceneResManager.getInstance().getScalePx(this.x);
    }

    public int getScaleY() {
        if (this.a) {
            return this.y;
        }
        return AnimSceneResManager.getInstance().getScalePx(this.y);
    }

    public void setX(int i) {
        this.x = i;
    }

    public int getX() {
        return this.x;
    }

    public void setY(int i) {
        this.y = i;
    }

    public int getY() {
        return this.y;
    }

    public void set(int i, int i2) {
        this.x = i;
        this.y = i2;
    }

    public final void negate() {
        this.x = -this.x;
        this.y = -this.y;
    }

    public final void offset(int i, int i2) {
        this.x += i;
        this.y += i2;
    }

    public final boolean equals(int i, int i2) {
        return this.x == i && this.y == i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PointI pointI = (PointI) obj;
        if (this.x != pointI.x) {
            return false;
        }
        if (this.y != pointI.y) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.x * 31) + this.y;
    }

    public String toString() {
        return "PointI(" + this.x + ", " + this.y + ")";
    }
}
