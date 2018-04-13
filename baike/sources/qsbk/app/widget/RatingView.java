package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import qsbk.app.R;
import qsbk.app.utils.DebugUtil;

public class RatingView extends LinearLayout {
    private static final String a = RatingView.class.getSimpleName();
    private ImageView[] b;

    public RatingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new ImageView[5];
        a(context);
    }

    public RatingView(Context context) {
        this(context, null);
    }

    private void a(Context context) {
        DebugUtil.debug(a, "initView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.rating, this);
        this.b[0] = (ImageView) inflate.findViewById(R.id.rating_1);
        this.b[1] = (ImageView) inflate.findViewById(R.id.rating_2);
        this.b[2] = (ImageView) inflate.findViewById(R.id.rating_3);
        this.b[3] = (ImageView) inflate.findViewById(R.id.rating_4);
        this.b[4] = (ImageView) inflate.findViewById(R.id.rating_5);
    }

    public void setRating(int i) {
        int i2;
        if (i > 10) {
            i = 10;
        }
        for (i2 = 0; i2 < 5; i2++) {
            DebugUtil.debug(a, "setRating i:" + i2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.b[i2]);
            this.b[i2].getDrawable().setLevel(3);
        }
        i2 = 0;
        while (i > 1) {
            this.b[i2].getDrawable().setLevel(1);
            i2++;
            i -= 2;
            if (i <= 0) {
                return;
            }
        }
        if (i == 1) {
            this.b[i2].getDrawable().setLevel(2);
        }
    }
}
