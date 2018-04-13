package cn.v6.sixrooms.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

public class ViewUtil {
    public static void resetViewSize(Context context, View[] viewArr, int[][] iArr) {
        for (int i = 0; i < viewArr.length; i++) {
            LayoutParams layoutParams = viewArr[i].getLayoutParams();
            if (iArr[i][0] != -1) {
                layoutParams.width = DisPlayUtil.getPix(context, iArr[i][0]);
            }
            if (iArr[i][1] != -1) {
                layoutParams.height = DisPlayUtil.getPix(context, iArr[i][1]);
            }
            if (layoutParams instanceof MarginLayoutParams) {
                if (iArr[i][2] != -1) {
                    ((MarginLayoutParams) layoutParams).leftMargin = DisPlayUtil.getPix(context, iArr[i][2]);
                }
                if (iArr[i][3] != -1) {
                    ((MarginLayoutParams) layoutParams).topMargin = DisPlayUtil.getPix(context, iArr[i][3]);
                }
                if (iArr[i][4] != -1) {
                    ((MarginLayoutParams) layoutParams).bottomMargin = DisPlayUtil.getPix(context, iArr[i][4]);
                }
                viewArr[i].setLayoutParams(layoutParams);
            }
        }
    }
}
