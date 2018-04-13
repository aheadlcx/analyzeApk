package qsbk.app.live.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import qsbk.app.live.R;

public class FamilyLevelView extends FrameLayout {
    TextView a;
    TextView b;
    FrameLayout c;

    public FamilyLevelView(Context context) {
        this(context, null);
    }

    public FamilyLevelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FamilyLevelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        View inflate = View.inflate(getContext(), R.layout.family_level_view, this);
        this.a = (TextView) inflate.findViewById(R.id.tv_level);
        this.b = (TextView) inflate.findViewById(R.id.tv_family_level_name);
        this.c = (FrameLayout) inflate.findViewById(R.id.bg_family_level);
    }

    public void setLevelAndName(int i, String str) {
        if (i > 0) {
            this.a.setText(i + "");
        } else {
            this.a.setText("");
        }
        this.b.setText(str);
        this.c.setBackgroundResource(getFamilyLevelBgResource(i));
    }

    public static int getFamilyLevelDrawableResource(int i) {
        if (i <= 9) {
            return R.drawable.bg_gift_family_level_1;
        }
        if (i >= 10 && i <= 39) {
            return R.drawable.bg_gift_family_level_2;
        }
        if (i >= 40 && i <= 69) {
            return R.drawable.bg_gift_family_level_3;
        }
        if (i < 70 || i > 98) {
            return R.drawable.bg_gift_family_level_5;
        }
        return R.drawable.bg_gift_family_level_4;
    }

    public static int getFamilyLevelGiftDrawableResource(int i) {
        if (i <= 9) {
            return R.drawable.bg_gift_family_level_alpha80_1;
        }
        if (i >= 10 && i <= 39) {
            return R.drawable.bg_gift_family_level_alpha80_2;
        }
        if (i >= 40 && i <= 69) {
            return R.drawable.bg_gift_family_level_alpha80_3;
        }
        if (i < 70 || i > 98) {
            return R.drawable.bg_gift_family_level_alpha80_5;
        }
        return R.drawable.bg_gift_family_level_alpha80_4;
    }

    public static int getFamilyLevelBgResource(int i) {
        if (i <= 9) {
            return R.drawable.family_level_bg_1;
        }
        if (i >= 10 && i <= 39) {
            return R.drawable.family_level_bg_2;
        }
        if (i >= 40 && i <= 69) {
            return R.drawable.family_level_bg_3;
        }
        if (i < 70 || i > 98) {
            return R.drawable.family_level_bg_5;
        }
        return R.drawable.family_level_bg_4;
    }
}
