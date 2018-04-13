package cn.v6.sixrooms.room.utils;

import cn.v6.sixrooms.surfaceanim.specialenterframe.FortuneUtils;
import cn.v6.sixrooms.utils.DrawableResourceUtils;
import cn.v6.sixrooms.utils.LogUtils;
import com.facebook.drawee.view.SimpleDraweeView;

public class WealthRankImageUtils {
    public static final int MAX_WEALTH_LEVEL = 27;

    public static void setWealthImageView(String str, int i, SimpleDraweeView simpleDraweeView, boolean z) {
        if (z) {
            String customWealthRankImg = DrawableResourceUtils.getCustomWealthRankImg(str);
            LogUtils.d("WealthRankImageUtils", "wealthRankImg: " + customWealthRankImg);
            simpleDraweeView.setImageURI(customWealthRankImg);
            return;
        }
        int fortuneLevelUrl = FortuneUtils.getFortuneLevelUrl(String.valueOf(i));
        if (fortuneLevelUrl == -1) {
            simpleDraweeView.setVisibility(4);
        } else {
            simpleDraweeView.setImageResource(fortuneLevelUrl);
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
        String customWealthRankImg = DrawableResourceUtils.getCustomWealthRankImg(str);
        LogUtils.d("WealthRankImageUtils", "customNextWealthRankImg: " + customWealthRankImg);
        simpleDraweeView.setImageURI(customWealthRankImg);
    }

    public static void setNextWealthImageView(String str, int i, SimpleDraweeView simpleDraweeView) {
        int i2 = i + 1;
        if (i2 < 27) {
            i2 = FortuneUtils.getFortuneLevelUrl(String.valueOf(i2));
            if (i2 == -1) {
                simpleDraweeView.setVisibility(4);
                return;
            } else {
                simpleDraweeView.setImageResource(i2);
                return;
            }
        }
        String customNextWealthRankImg = DrawableResourceUtils.getCustomNextWealthRankImg(str);
        LogUtils.d("WealthRankImageUtils", "customNextWealthRankImg: " + customNextWealthRankImg);
        simpleDraweeView.setImageURI(customNextWealthRankImg);
    }
}
