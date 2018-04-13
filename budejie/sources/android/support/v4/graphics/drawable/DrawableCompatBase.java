package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class DrawableCompatBase {
    DrawableCompatBase() {
    }

    public static void setTint(Drawable drawable, int i) {
        if (drawable instanceof TintAwareDrawable) {
            ((TintAwareDrawable) drawable).setTint(i);
        }
    }

    public static void setTintList(Drawable drawable, ColorStateList colorStateList) {
        if (drawable instanceof TintAwareDrawable) {
            ((TintAwareDrawable) drawable).setTintList(colorStateList);
        }
    }

    public static void setTintMode(Drawable drawable, Mode mode) {
        if (drawable instanceof TintAwareDrawable) {
            ((TintAwareDrawable) drawable).setTintMode(mode);
        }
    }

    public static Drawable wrapForTinting(Drawable drawable) {
        if (drawable instanceof TintAwareDrawable) {
            return drawable;
        }
        return new DrawableWrapperDonut(drawable);
    }

    public static void inflate(Drawable drawable, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) throws IOException, XmlPullParserException {
        drawable.inflate(resources, xmlPullParser, attributeSet);
    }
}
