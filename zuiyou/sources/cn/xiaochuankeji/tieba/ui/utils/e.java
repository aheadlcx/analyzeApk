package cn.xiaochuankeji.tieba.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.media.ExifInterface;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.widget.SDGuideDialog;
import com.izuiyou.a.a.b;
import java.lang.reflect.Field;

public class e {
    private static float a;
    private static float b;
    private static int c;
    private static int d;
    private static Field e;
    private static boolean f = a.a().getBoolean("key_is_first_support", true);

    public static void a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        a = displayMetrics.density;
        b = displayMetrics.scaledDensity;
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (i >= i2) {
            c = i2;
            d = i;
            return;
        }
        c = i;
        d = i2;
    }

    public static float a() {
        return a;
    }

    public static int a(float f) {
        return Math.round(a * f);
    }

    public static int b() {
        return c;
    }

    public static int b(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int c() {
        return d;
    }

    public static int c(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static Pair<Boolean, String> a(String str, TextPaint textPaint, int i, int i2, String str2) {
        StaticLayout staticLayout = new StaticLayout(str, textPaint, i, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        if (staticLayout.getLineCount() <= i2) {
            return new Pair(Boolean.FALSE, str);
        }
        int offsetForHorizontal;
        int i3 = i2 - 1;
        float measureText = textPaint.measureText(str2);
        int lineEnd = staticLayout.getLineEnd(i3) - 1;
        if (staticLayout.getLineWidth(i3) + measureText > ((float) i)) {
            offsetForHorizontal = staticLayout.getOffsetForHorizontal(i3, ((float) i) - measureText) - 1;
        } else {
            offsetForHorizontal = lineEnd;
        }
        return new Pair(Boolean.TRUE, str.substring(0, offsetForHorizontal) + str2);
    }

    public static Rect a(Rect rect, float f) {
        Rect rect2 = new Rect();
        int centerX = rect.centerX();
        int centerY = rect.centerY();
        int width = (int) ((((float) rect.width()) * f) / 2.0f);
        int height = (int) ((((float) rect.height()) * f) / 2.0f);
        rect2.set(centerX - width, centerY - height, centerX + width, centerY + height);
        return rect2;
    }

    public static void a(TextView textView, int i) {
        Drawable drawable = BaseApplication.getAppContext().getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }

    public static void a(TextView textView, float f) {
        if (e == null) {
            try {
                e = TextView.class.getDeclaredField("mShadowRadius");
                e.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        try {
            e.set(textView, Float.valueOf(f));
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    public static boolean a(char c) {
        return c == '\u0000' || c == '\t' || c == '\n' || c == '\r' || ((c >= ' ' && c <= '퟿') || ((c >= '' && c <= '�') || (c >= '\u0000' && c <= '￿')));
    }

    public static void d(Context context) {
        if (!((Activity) context).isFinishing()) {
            SDGuideDialog sDGuideDialog = new SDGuideDialog((Activity) context);
            sDGuideDialog.a(R.drawable.personal_guanfang_bg, 17);
            sDGuideDialog.b();
        }
    }

    public static void a(Throwable th) {
        if (th instanceof ClientErrorException) {
            g.b(((ClientErrorException) th).errMessage());
        } else {
            g.b("网络错误");
        }
        b.e(th);
    }

    public static void e(Context context) {
        if (f) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.hollow_feed_content_view, null);
            final cn.xiaochuankeji.tieba.ui.widget.b.a.a aVar = new cn.xiaochuankeji.tieba.ui.widget.b.a.a(context);
            inflate.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    aVar.b();
                }
            });
            aVar.a(inflate).a();
            a.a().edit().putBoolean("key_is_first_support", false).apply();
            f = false;
        }
    }

    public static String a(long j) {
        if (j < 10000) {
            return "" + j;
        }
        return "" + (j / 10000) + "." + ((j % 10000) / 1000) + ExifInterface.LONGITUDE_WEST;
    }
}
