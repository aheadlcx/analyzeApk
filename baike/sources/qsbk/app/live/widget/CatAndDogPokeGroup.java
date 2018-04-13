package qsbk.app.live.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.live.R;

public class CatAndDogPokeGroup extends PokerGroup {
    private static String a = "Pokeggroup";

    public CatAndDogPokeGroup(Context context) {
        super(context);
    }

    public CatAndDogPokeGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CatAndDogPokeGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public static Drawable getPokerDrawable(int i) {
        int i2 = ((i % 4) * 13) + ((i / 4) + 1);
        int i3 = R.drawable.live_game_catanddog_poker_back_popbull;
        if (1 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_1;
        } else if (2 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_2;
        } else if (3 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_3;
        } else if (4 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_4;
        } else if (5 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_5;
        } else if (6 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_6;
        } else if (7 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_7;
        } else if (8 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_8;
        } else if (9 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_9;
        } else if (10 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_10;
        } else if (11 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_11;
        } else if (12 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_12;
        } else if (13 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_13;
        } else if (14 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_14;
        } else if (15 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_15;
        } else if (16 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_16;
        } else if (17 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_17;
        } else if (18 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_18;
        } else if (19 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_19;
        } else if (20 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_20;
        } else if (21 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_21;
        } else if (22 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_22;
        } else if (23 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_23;
        } else if (24 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_24;
        } else if (25 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_25;
        } else if (26 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_26;
        } else if (27 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_27;
        } else if (28 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_28;
        } else if (29 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_29;
        } else if (30 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_30;
        } else if (31 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_31;
        } else if (32 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_32;
        } else if (33 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_33;
        } else if (34 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_34;
        } else if (35 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_35;
        } else if (36 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_36;
        } else if (37 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_37;
        } else if (38 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_38;
        } else if (39 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_39;
        } else if (40 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_40;
        } else if (41 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_41;
        } else if (42 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_42;
        } else if (43 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_43;
        } else if (44 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_44;
        } else if (45 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_45;
        } else if (46 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_46;
        } else if (47 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_47;
        } else if (48 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_48;
        } else if (49 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_49;
        } else if (50 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_50;
        } else if (51 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_51;
        } else if (52 == i2) {
            i3 = R.drawable.live_game_catanddog_poker_52;
        } else {
            LogUtils.e(a, "wrong poker " + i);
        }
        return AppUtils.getInstance().getAppContext().getResources().getDrawable(i3);
    }
}
