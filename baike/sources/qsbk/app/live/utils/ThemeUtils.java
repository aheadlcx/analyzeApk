package qsbk.app.live.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import qsbk.app.core.utils.GiftResSync;
import qsbk.app.live.R;

public class ThemeUtils {
    public static void setTheme(Context context) {
        if (isChristmas()) {
            setTheme(context, R.style.LiveRoomChristmas);
        } else if (isSpringFestival()) {
            setTheme(context, R.style.LiveRoomSpring);
        } else {
            setTheme(context, R.style.LiveRoomNormal);
        }
    }

    public static void setTheme(Context context, int i) {
        context.setTheme(i);
    }

    public static boolean isChristmas() {
        try {
            if (System.currentTimeMillis() <= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-12-25 23:59:59").getTime()) {
                return true;
            }
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getLoveThemeArrayResId() {
        if (isChristmas()) {
            return R.array.LiveRoomChristmas;
        }
        if (isSpringFestival()) {
            return R.array.LiveRoomSpring;
        }
        return R.array.LiveRoomNormal;
    }

    public static void decorateImageView(ImageView imageView, String str) {
        if (str != null) {
            Drawable createFromPath = Drawable.createFromPath(GiftResSync.getDownloadedGiftResPath(str));
            if (createFromPath != null) {
                imageView.setImageDrawable(createFromPath);
            }
        }
    }

    public static void decorateViewBackgroundColor(View view, String str) {
        try {
            view.setBackgroundColor(Color.parseColor(str));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void decorateViewBackgroundImage(View view, String str) {
        if (str != null) {
            Drawable createFromPath = Drawable.createFromPath(GiftResSync.getDownloadedGiftResPath(str));
            if (createFromPath != null) {
                view.setBackgroundDrawable(createFromPath);
            }
        }
    }

    public static boolean isSpringFestival() {
        try {
            if (System.currentTimeMillis() <= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-02-11 23:59:59").getTime()) {
                return true;
            }
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isBeforeMarch() {
        try {
            if (System.currentTimeMillis() <= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-02-28 23:59:59").getTime()) {
                return true;
            }
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
