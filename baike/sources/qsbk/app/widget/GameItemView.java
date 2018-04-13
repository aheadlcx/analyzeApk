package qsbk.app.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.UIHelper;

public class GameItemView extends RelativeLayout {
    TextView a;
    TextView b;
    ImageView c;
    TextView d;
    TextView e;
    RatingView f;
    TextView g;
    View h;

    public GameItemView(Context context) {
        this(context, null);
    }

    public GameItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GameItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(WindowUtils.dp2Px(80), 1073741824));
    }

    private void a() {
        if (this.a == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.game_view_item, this, true);
            this.a = (TextView) findViewById(R.id.game_download);
            this.b = (TextView) findViewById(R.id.play_num);
            this.c = (ImageView) findViewById(R.id.game_icon);
            this.d = (TextView) findViewById(R.id.game_name);
            this.e = (TextView) findViewById(R.id.game_gift);
            this.f = (RatingView) findViewById(R.id.ratting);
            this.g = (TextView) findViewById(R.id.game_description);
            this.h = findViewById(R.id.divider);
        }
    }

    public void setGameDownloadText(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.a.setText(str);
        }
    }

    public void setGameDownloadClickListener(OnClickListener onClickListener) {
        this.a.setOnClickListener(onClickListener);
    }

    public void setPlayNumText(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.b.setText(str);
        }
    }

    public void setIconUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            FrescoImageloader.displayImage(this.c, str, UIHelper.isNightTheme() ? R.drawable.found_item_avatar_night : R.drawable.found_item_avatar);
        }
    }

    public void setName(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.d.setText(str);
        }
    }

    public void isShowGift(boolean z) {
        if (z) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
    }

    public void setGiftDes(String str) {
        if (TextUtils.isEmpty(str)) {
            this.e.setText("礼");
        } else {
            this.e.setText(str);
        }
    }

    public void setRatingViewScore(int i) {
        this.f.setRating(i);
    }

    public void setDesText(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.g.setText(str);
        }
    }

    public void isShowDivider(boolean z) {
        if (z) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(8);
        }
    }
}
