package android.support.graphics.drawable;

import android.content.res.Resources.Theme;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;

abstract class f extends Drawable implements TintAwareDrawable {
    Drawable c;

    f() {
    }

    public void setColorFilter(int i, Mode mode) {
        if (this.c != null) {
            this.c.setColorFilter(i, mode);
        } else {
            super.setColorFilter(i, mode);
        }
    }

    public ColorFilter getColorFilter() {
        if (this.c != null) {
            return DrawableCompat.getColorFilter(this.c);
        }
        return null;
    }

    protected boolean onLevelChange(int i) {
        if (this.c != null) {
            return this.c.setLevel(i);
        }
        return super.onLevelChange(i);
    }

    protected void onBoundsChange(Rect rect) {
        if (this.c != null) {
            this.c.setBounds(rect);
        } else {
            super.onBoundsChange(rect);
        }
    }

    public void setHotspot(float f, float f2) {
        if (this.c != null) {
            DrawableCompat.setHotspot(this.c, f, f2);
        }
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        if (this.c != null) {
            DrawableCompat.setHotspotBounds(this.c, i, i2, i3, i4);
        }
    }

    public void setFilterBitmap(boolean z) {
        if (this.c != null) {
            this.c.setFilterBitmap(z);
        }
    }

    public void jumpToCurrentState() {
        if (this.c != null) {
            DrawableCompat.jumpToCurrentState(this.c);
        }
    }

    public void applyTheme(Theme theme) {
        if (this.c != null) {
            DrawableCompat.applyTheme(this.c, theme);
        }
    }

    public void clearColorFilter() {
        if (this.c != null) {
            this.c.clearColorFilter();
        } else {
            super.clearColorFilter();
        }
    }

    public Drawable getCurrent() {
        if (this.c != null) {
            return this.c.getCurrent();
        }
        return super.getCurrent();
    }

    public int getMinimumWidth() {
        if (this.c != null) {
            return this.c.getMinimumWidth();
        }
        return super.getMinimumWidth();
    }

    public int getMinimumHeight() {
        if (this.c != null) {
            return this.c.getMinimumHeight();
        }
        return super.getMinimumHeight();
    }

    public boolean getPadding(Rect rect) {
        if (this.c != null) {
            return this.c.getPadding(rect);
        }
        return super.getPadding(rect);
    }

    public int[] getState() {
        if (this.c != null) {
            return this.c.getState();
        }
        return super.getState();
    }

    public Region getTransparentRegion() {
        if (this.c != null) {
            return this.c.getTransparentRegion();
        }
        return super.getTransparentRegion();
    }

    public void setChangingConfigurations(int i) {
        if (this.c != null) {
            this.c.setChangingConfigurations(i);
        } else {
            super.setChangingConfigurations(i);
        }
    }

    public boolean setState(int[] iArr) {
        if (this.c != null) {
            return this.c.setState(iArr);
        }
        return super.setState(iArr);
    }
}
