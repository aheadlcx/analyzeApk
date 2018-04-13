package cn.tatagou.sdk.util;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.android.TtgConfig;
import cn.v6.sixrooms.constants.CommonInts;
import com.ali.auth.third.core.model.SystemMessageConstants;

public class q {
    public static String showErrorHint(Context context, int i) {
        return showErrorHint(context, null, null, null, i, true);
    }

    public static String showErrorHint(Context context, LinearLayout linearLayout, TextView textView, TextView textView2, int i, boolean z) {
        int i2;
        CharSequence charSequence;
        String string = context.getResources().getString(z ? R.string.ttg_toast_no_data : R.string.ttg_no_data);
        Object obj;
        switch (i) {
            case 200:
            case 304:
                if (linearLayout != null) {
                    linearLayout.setVisibility(8);
                }
                obj = string;
                i2 = 0;
                break;
            case 10000:
            case 10001:
            case CommonInts.GT_ERROR /*10002*/:
                obj = context.getResources().getString(R.string.ttg_net_bad);
                i2 = 1;
                break;
            case SystemMessageConstants.TAOBAO_CANCEL_CODE /*10004*/:
                obj = context.getResources().getString(R.string.ttg_phone_data);
                i2 = 1;
                break;
            case SystemMessageConstants.TAOBAO_ERROR_CODE /*10005*/:
                obj = context.getResources().getString(R.string.ttg_bc_fail);
                i2 = 1;
                break;
            default:
                charSequence = string;
                i2 = 1;
                break;
        }
        if (i2 != 0) {
            if (z) {
                l.showToastCenter(context, charSequence);
                if (linearLayout != null) {
                    linearLayout.setVisibility(8);
                }
            } else {
                if (linearLayout != null) {
                    linearLayout.setVisibility(0);
                }
                if (textView != null && textView2 == null) {
                    textView.setText(charSequence);
                } else if (textView != null) {
                    textView.setText(R.string.ttg_load_fail);
                    textView2.setVisibility(0);
                    textView2.setText(charSequence);
                }
            }
        }
        return charSequence;
    }

    public static void onResetThemeColor(View view) {
        if (view != null) {
            view.setBackgroundColor(TtgConfig.getInstance().getThemeColor());
        }
    }

    public static void onResetTextViewThemeColor(TextView textView) {
        if (textView != null) {
            textView.setTextColor(TtgConfig.getInstance().getThemeColor());
        }
    }

    public static void onResetShapeThemeColor(View view, int i, int i2, int i3) {
        if (view != null) {
            try {
                GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground();
                gradientDrawable.setStroke(i, i2);
                gradientDrawable.setColor(i3);
            } catch (Throwable e) {
                Log.e("TTG", "onResetShapeThemeColor: " + e.getMessage(), e);
            }
        }
    }
}
