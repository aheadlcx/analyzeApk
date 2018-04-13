package qsbk.app.ad.feedsad;

import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import qsbk.app.ConfigManager;
import qsbk.app.QsbkApp;
import qsbk.app.image.ImageSizeHelper;
import qsbk.app.utils.SharePreferenceUtils;

public class FeedsAdUtils {
    public static final int AD_APK_CLICK_TO_INSTALL = 3;
    public static final int AD_APK_CLICK_TO_START = 4;
    public static final int AD_APK_DOWNLOAD = 1;
    public static final int AD_APK_DOWNLOADING = 2;
    public static final int AD_TRY_AGAIN = 0;
    private static final String ASSOCIATIVE_MEMORY = "associative_memory";
    private static final String MAIN_CONDITION = "_main_condition";
    private static final int MODE = 0;
    private static final String SUB_VALUE = "_sub_value";

    public static boolean isOver(String str, int i) {
        if (containsMainCondition(str)) {
            return isGreaterMaxValue(i);
        }
        QsbkApp.mContext.getSharedPreferences(ASSOCIATIVE_MEMORY, 0).edit().putInt(SUB_VALUE, 0).apply();
        return false;
    }

    private static boolean containsMainCondition(String str) {
        if (QsbkApp.mContext.getSharedPreferences(ASSOCIATIVE_MEMORY, 0).getString(MAIN_CONDITION, "").equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }

    private static boolean isGreaterMaxValue(int i) {
        if (QsbkApp.mContext.getSharedPreferences(ASSOCIATIVE_MEMORY, 0).getInt(SUB_VALUE, 0) <= i) {
            return false;
        }
        return true;
    }

    public static void addMain_condition(String str) {
        SharedPreferences sharedPreferences = QsbkApp.mContext.getSharedPreferences(ASSOCIATIVE_MEMORY, 0);
        sharedPreferences.edit().putString(MAIN_CONDITION, str).apply();
        sharedPreferences.edit().putInt(SUB_VALUE, sharedPreferences.getInt(SUB_VALUE, 0) + 1).apply();
    }

    public static int getMaxFeedAdShowTime() {
        try {
            int parseInt = Integer.parseInt(SharePreferenceUtils.getSharePreferencesValue("feed_ad_max_time"));
            if (parseInt < 9) {
                return 9;
            }
            return parseInt;
        } catch (Exception e) {
            return 9999;
        }
    }

    public static void setMaxFeedAdShowTime(int i) {
        if (i < 9) {
            i = 9;
        }
        SharePreferenceUtils.setSharePreferencesValue("feed_ad_max_time", i);
    }

    public static void setImageViewLayoutParam(ImageView imageView, int i, int i2) {
        if (imageView != null) {
            int[] iArr = new int[2];
            caculateImageSize(iArr, i, i2);
            imageView.setLayoutParams(new LayoutParams(iArr[0], iArr[1]));
        }
    }

    private static void caculateImageSize(int[] iArr, int i, int i2) {
        if (iArr == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        int[] requestWidthAndMaxPixcel = ImageSizeHelper.getRequestWidthAndMaxPixcel();
        int i3 = requestWidthAndMaxPixcel[0];
        int i4 = requestWidthAndMaxPixcel[1];
        int i5 = (int) ((((float) i3) / ((float) i)) * ((float) i2));
        if (i4 == -1 || i5 <= i4) {
            i4 = i5;
        }
        iArr[0] = i3;
        iArr[1] = i4;
    }

    public static boolean needShowConfirm(String str) {
        return "36".equals(ConfigManager.getInstance().getChannel()) || !str.equals("wifi") || (str.equals("wifi") && QsbkApp.indexConfig.optBoolean("wifi_download_confirm"));
    }
}
