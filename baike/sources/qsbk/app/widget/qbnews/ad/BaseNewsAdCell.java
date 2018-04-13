package qsbk.app.widget.qbnews.ad;

import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.model.ImageSize;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.qbnews.BaseNewsCell;

public abstract class BaseNewsAdCell extends BaseNewsCell {
    protected NewsAdSign d = NewsAdSign.FIRST;
    protected TextView e;
    protected ImageView f;
    protected TextView g;
    protected TextView h;

    public void onCreate() {
        this.s = TileBackground.getBackgroud(getContext(), BgImageType.AD);
        if (this.d == NewsAdSign.FIRST) {
            setCellView(R.layout.news_first_ad_layout);
        } else {
            setCellView(R.layout.news_second_ad_layout);
        }
        a();
    }

    protected void a() {
        this.e = (TextView) findViewById(R.id.news_ad_title);
        this.f = (ImageView) findViewById(R.id.news_ad_image);
        if (this.d == NewsAdSign.FIRST) {
            a(3.0d);
        } else {
            a(1.3333333333333333d);
            b(UIHelper.dip2px(getContext(), 70.0f));
        }
        setImageLayoutParams(this.f);
        this.f.setBackgroundResource(UIHelper.getDefaultAdImageTileBackground());
        this.g = (TextView) findViewById(R.id.news_ad_source);
        this.h = (TextView) findViewById(R.id.news_ad_flag);
        this.h.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.news_ad_bt_bg_night : R.drawable.news_ad_bt_bg);
        this.h.setTextColor(UIHelper.isNightTheme() ? -12500147 : -5060112);
    }

    protected void a(NewsAdSign newsAdSign) {
        this.d = newsAdSign;
    }

    protected ImageSize b() {
        if (this.a == 0 && this.b == 0) {
            this.a = UIHelper.getScreenWidth(getContext()) - UIHelper.dip2px(getContext(), 32.0f);
            this.b = (int) (((double) this.a) / this.c);
        }
        return super.b();
    }
}
