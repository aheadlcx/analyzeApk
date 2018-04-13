package qsbk.app.live.ui.helper;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;
import qsbk.app.live.utils.FontUtils;

public class LevelHelper {
    public static void setLevelText(TextView textView, int i) {
        setLevelText(textView, i, true, FontUtils.getBloggerSansFontBoldItalic(), true);
    }

    public static void setLevelText(TextView textView, int i, boolean z) {
        setLevelText(textView, i, z, FontUtils.getBloggerSansFontBold());
    }

    public static void setLevelText(TextView textView, int i, boolean z, Typeface typeface) {
        setLevelText(textView, i, z, typeface, false);
    }

    public static void setLevelText(TextView textView, int i, boolean z, Typeface typeface, boolean z2) {
        if (z2) {
            CharSequence charSequence = i < 1 ? " 1" : i < 10 ? i + "  " : i + "";
            textView.setText(charSequence);
        } else {
            textView.setText(getLevelText(i, z));
        }
        if (z) {
            textView.setBackgroundResource(getDrawableByLevel(i));
        }
        textView.setTypeface(typeface);
        textView.setTextColor(getLevelColor(i));
    }

    public static String getLevelText(int i) {
        return getLevelText(i, true);
    }

    public static String getLevelText(int i, boolean z) {
        String str = "";
        StringBuilder append = new StringBuilder().append("LV.");
        if (i < 1) {
            i = 1;
        }
        return append.append(i).toString();
    }

    public static int getLevelColor(int i) {
        return i < 200 ? -1 : ContextCompat.getColor(AppUtils.getInstance().getAppContext(), R.color.FFEC7A);
    }

    public static int getDrawableByLevel(int i) {
        if (i < 1) {
            i = 1;
        }
        if (i >= 300) {
            return R.drawable.live_lv_300;
        }
        switch (i / 10) {
            case 0:
                return R.drawable.live_lv_1_9;
            case 1:
                return R.drawable.live_lv_10_19;
            case 2:
                return R.drawable.live_lv_20_29;
            case 3:
                return R.drawable.live_lv_30_39;
            case 4:
                return R.drawable.live_lv_40_49;
            case 5:
                return R.drawable.live_lv_50_59;
            case 6:
                return R.drawable.live_lv_60_69;
            case 7:
                return R.drawable.live_lv_70_79;
            case 8:
                return R.drawable.live_lv_80_89;
            case 9:
                return R.drawable.live_lv_90_99;
            case 10:
                return R.drawable.live_lv_100_109;
            case 11:
                return R.drawable.live_lv_110_119;
            case 12:
                return R.drawable.live_lv_120_129;
            case 13:
                return R.drawable.live_lv_130_139;
            case 14:
                return R.drawable.live_lv_140_149;
            case 15:
                return R.drawable.live_lv_150_159;
            case 16:
                return R.drawable.live_lv_160_169;
            case 17:
                return R.drawable.live_lv_170_179;
            case 18:
                return R.drawable.live_lv_180_189;
            case 19:
                return R.drawable.live_lv_190_199;
            case 20:
                return R.drawable.live_lv_200_209;
            case 21:
                return R.drawable.live_lv_210_219;
            case 22:
                return R.drawable.live_lv_220_229;
            case 23:
                return R.drawable.live_lv_230_239;
            case 24:
                return R.drawable.live_lv_240_249;
            case 25:
                return R.drawable.live_lv_250_259;
            case 26:
                return R.drawable.live_lv_260_269;
            case 27:
                return R.drawable.live_lv_270_279;
            case 28:
                return R.drawable.live_lv_280_289;
            case 29:
                return R.drawable.live_lv_290_299;
            default:
                return R.drawable.live_lv_1_9;
        }
    }
}
