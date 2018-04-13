package qsbk.app.core.model;

import android.text.TextUtils;
import android.widget.FrameLayout.LayoutParams;
import java.io.Serializable;
import qsbk.app.core.utils.WindowUtils;

public class Activity implements Serializable {
    public String pic_url;
    public float pic_web_margin;
    public float pic_web_margintop;
    public float pic_web_ratio;
    public String pic_web_url;
    public float pic_web_width;
    public String position;
    public String redirect_url;

    public boolean isValid() {
        return (TextUtils.isEmpty(this.position) || (TextUtils.isEmpty(this.pic_web_url) && (TextUtils.isEmpty(this.pic_url) || TextUtils.isEmpty(this.redirect_url)))) ? false : true;
    }

    public boolean isWebActivity() {
        return !TextUtils.isEmpty(this.pic_web_url);
    }

    public LayoutParams getFrameLayoutParams(int i, int i2) {
        LayoutParams layoutParams = new LayoutParams(i / 4, i / 4);
        if ("top-right".equals(this.position)) {
            layoutParams.gravity = 53;
        } else if ("bottom-right".equals(this.position)) {
            layoutParams.gravity = 85;
        } else {
            this.position = "top-left";
            layoutParams.gravity = 51;
        }
        if (this.pic_web_width > 0.0f) {
            layoutParams.width = (int) (((float) i) * this.pic_web_width);
        }
        if (this.pic_web_ratio > 0.0f) {
            layoutParams.height = (int) (((float) layoutParams.width) * this.pic_web_ratio);
        }
        if (this.pic_web_margin > 0.0f) {
            if (this.position.contains("right")) {
                layoutParams.rightMargin = (int) (((float) i) * this.pic_web_margin);
            } else {
                layoutParams.leftMargin = (int) (((float) i) * this.pic_web_margin);
            }
        }
        if (this.pic_web_margintop < 0.0f) {
            layoutParams.topMargin = WindowUtils.dp2Px(104);
            layoutParams.bottomMargin = WindowUtils.dp2Px(60);
        } else if (this.position.contains("top")) {
            layoutParams.topMargin = (int) (((float) i2) * this.pic_web_margintop);
        } else {
            layoutParams.bottomMargin = (int) (((float) i2) * this.pic_web_margintop);
        }
        return layoutParams;
    }
}
