package qsbk.app.live.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.facebook.drawee.view.SimpleDraweeView;
import qsbk.app.live.R;

public class GuessResultImageView extends SimpleDraweeView {
    public static final int STATUS_BIG = 2;
    public static final int STATUS_EMPTY = 0;
    public static final int STATUS_SMALL = 1;
    public int BIG_DRAWABLE_ID;
    public int EMPTY_DRAWABLE_ID;
    public int SMALL_DRAWABLE_ID;
    public int status;

    public GuessResultImageView(Context context) {
        this(context, null);
    }

    public GuessResultImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GuessResultImageView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public GuessResultImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    public void init() {
        this.EMPTY_DRAWABLE_ID = R.drawable.guess_result_empty;
        this.BIG_DRAWABLE_ID = R.drawable.guess_result_big;
        this.SMALL_DRAWABLE_ID = R.drawable.guess_result_small;
        this.status = 0;
    }

    public void setStatus(int i) {
        this.status = i;
        postInvalidate();
    }

    protected void onDraw(Canvas canvas) {
        if (this.status == 0) {
            setImageResource(this.EMPTY_DRAWABLE_ID);
        } else if (this.status == 2) {
            setImageResource(this.BIG_DRAWABLE_ID);
        } else if (this.status == 1) {
            setImageResource(this.SMALL_DRAWABLE_ID);
        } else {
            setImageResource(this.EMPTY_DRAWABLE_ID);
        }
        super.onDraw(canvas);
    }
}
