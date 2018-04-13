package cn.v6.sixrooms.utils;

import android.net.Uri;
import cn.v6.sixrooms.surfaceanim.specialenterframe.FortuneUtils;
import com.facebook.drawee.view.SimpleDraweeView;

public class ImageViewUtils {
    public static final int MAX_WEALTH_LEVEL = 27;

    public static void setWealthImageView(String str, int i, SimpleDraweeView simpleDraweeView, boolean z) {
        String fortuneLevelUrl;
        if (z) {
            fortuneLevelUrl = DrawableResourceUtils.getFortuneLevelUrl(str, i, z);
            LogUtils.d("ImageViewUtils", "fortuneLevelUrl: " + fortuneLevelUrl);
            simpleDraweeView.setImageURI(Uri.parse(fortuneLevelUrl));
        } else if (i <= 27) {
            int fortuneLevelUrl2 = FortuneUtils.getFortuneLevelUrl(String.valueOf(i));
            if (fortuneLevelUrl2 == -1) {
                simpleDraweeView.setVisibility(4);
            } else {
                simpleDraweeView.setImageResource(fortuneLevelUrl2);
            }
        } else {
            fortuneLevelUrl = DrawableResourceUtils.getFortuneLevelUrl(str, i, z);
            LogUtils.d("ImageViewUtils", "fortuneLevelUrl: " + fortuneLevelUrl);
            simpleDraweeView.setImageURI(Uri.parse(fortuneLevelUrl));
        }
    }

    public static void setWealthImageView(String str, int i, SimpleDraweeView simpleDraweeView) {
        if (i < 27) {
            int fortuneLevelUrl = FortuneUtils.getFortuneLevelUrl(String.valueOf(i));
            if (fortuneLevelUrl == -1) {
                simpleDraweeView.setVisibility(4);
                return;
            } else {
                simpleDraweeView.setImageResource(fortuneLevelUrl);
                return;
            }
        }
        String fortuneLevelUrl2 = DrawableResourceUtils.getFortuneLevelUrl(str, i);
        LogUtils.d("ImageViewUtils", "fortuneLevelUrl: " + fortuneLevelUrl2);
        simpleDraweeView.setImageURI(Uri.parse(fortuneLevelUrl2));
    }
}
