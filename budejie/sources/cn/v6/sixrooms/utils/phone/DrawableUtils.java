package cn.v6.sixrooms.utils.phone;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import cn.v6.sdk.sixrooms.coop.V6Coop;

public class DrawableUtils {
    public static void setSelector(View view, int i, int i2, int i3) {
        view.setBackgroundDrawable(createSelector(i, i2, i3));
    }

    public static Drawable createSelector(int i, int i2, int i3) {
        Drawable drawable;
        Drawable drawable2 = null;
        Drawable stateListDrawable = new StateListDrawable();
        if (i == -1) {
            drawable = null;
        } else {
            drawable = V6Coop.getInstance().getContext().getResources().getDrawable(i);
        }
        if (i2 != -1) {
            drawable2 = V6Coop.getInstance().getContext().getResources().getDrawable(i2);
        }
        stateListDrawable.addState(new int[]{16842919}, drawable2);
        stateListDrawable.addState(new int[]{16842910}, drawable);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }
}
