package qsbk.app.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;

public final class TileBackground {

    public enum BgImageType {
        ARTICLE,
        AD,
        SHARE
    }

    public static BitmapDrawable getBackgroud(Context context, BgImageType bgImageType) {
        BitmapDrawable bitmapDrawable;
        Resources resources = context.getResources();
        switch (bd.a[bgImageType.ordinal()]) {
            case 1:
                bitmapDrawable = (BitmapDrawable) resources.getDrawable(UIHelper.getDefaultImageTileBackground());
                break;
            case 2:
                bitmapDrawable = (BitmapDrawable) resources.getDrawable(UIHelper.getDefaultShareImageTileBackground());
                break;
            default:
                bitmapDrawable = (BitmapDrawable) resources.getDrawable(UIHelper.getDefaultAdImageTileBackground());
                break;
        }
        bitmapDrawable.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
        return bitmapDrawable;
    }
}
