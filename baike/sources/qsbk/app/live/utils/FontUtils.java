package qsbk.app.live.utils;

import android.graphics.Typeface;
import java.lang.ref.SoftReference;
import qsbk.app.core.utils.AppUtils;

public class FontUtils {
    private static SoftReference<Typeface> a;
    private static SoftReference<Typeface> b;
    private static SoftReference<Typeface> c;
    private static SoftReference<Typeface> d;

    public static Typeface getBloggerSansFontBold() {
        if (a == null) {
            try {
                a = new SoftReference(Typeface.createFromAsset(AppUtils.getInstance().getAppContext().getAssets(), "fonts/Blogger Sans-Bold.ttf"));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return (Typeface) a.get();
    }

    public static Typeface getBloggerSansFont() {
        if (b == null) {
            try {
                b = new SoftReference(Typeface.createFromAsset(AppUtils.getInstance().getAppContext().getAssets(), "fonts/Blogger Sans.ttf"));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return (Typeface) b.get();
    }

    public static Typeface getBloggerSansFontLight() {
        if (c == null) {
            try {
                c = new SoftReference(Typeface.createFromAsset(AppUtils.getInstance().getAppContext().getAssets(), "fonts/Blogger Sans-Light.ttf"));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return (Typeface) c.get();
    }

    public static Typeface getBloggerSansFontBoldItalic() {
        if (d == null) {
            try {
                d = new SoftReference(Typeface.createFromAsset(AppUtils.getInstance().getAppContext().getAssets(), "fonts/Blogger Sans-Bold Italic.ttf"));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return (Typeface) d.get();
    }
}
