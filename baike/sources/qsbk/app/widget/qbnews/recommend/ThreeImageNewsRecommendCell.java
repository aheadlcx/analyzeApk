package qsbk.app.widget.qbnews.recommend;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.model.News;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.qbnews.news.NewsItemCell;

@Deprecated
public class ThreeImageNewsRecommendCell extends NewsItemCell {
    private ImageView g;
    private TextView h;
    private TextView i;
    private ImageView j;
    private ImageView k;
    private ImageView l;
    private TextView m;
    private View n;
    private OnClickListener o = new c(this);

    public void onCreate() {
        setCellView(R.layout.news_three_image_recommend);
        super.onCreate();
    }

    protected void a() {
        this.g = (ImageView) findViewById(R.id.news_user_icon);
        this.g.setBackgroundResource(UIHelper.getDefaultAvatar());
        displayImage(this.g, null, UIHelper.getDrawable(UIHelper.isNightTheme() ? R.drawable.news_icon_night : R.drawable.news_icon));
        this.h = (TextView) findViewById(R.id.news_user_name);
        this.i = (TextView) findViewById(R.id.news_description);
        this.j = (ImageView) findViewById(R.id.news_image_first);
        this.k = (ImageView) findViewById(R.id.news_image_second);
        this.l = (ImageView) findViewById(R.id.news_image_third);
        this.n = findViewById(R.id.content_layout);
        a(1.0d);
        a((UIHelper.getScreenWidth(getContext()) - UIHelper.dip2px(getContext(), 42.0f)) / 3);
        setImageLayoutParams(this.j);
        setImageLayoutParams(this.k);
        setImageLayoutParams(this.l);
        this.j.setBackgroundResource(UIHelper.getDefaultImageTileBackground());
        this.k.setBackgroundResource(UIHelper.getDefaultImageTileBackground());
        this.l.setBackgroundResource(UIHelper.getDefaultImageTileBackground());
        this.m = (TextView) findViewById(R.id.news_more);
        this.m.setText("查看更多糗闻");
        this.m.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.live_arrow_night : R.drawable.live_arrow), null, null, null);
        if (UIHelper.isNightTheme()) {
            this.n.setBackgroundColor(UIHelper.getColor(R.color.transparent));
        }
    }

    public void onUpdate() {
        News news = (News) getItem();
        this.i.setText(TextUtils.isEmpty(news.title) ? news.content : news.title);
        if (news.covers != null) {
            if (news.covers.length > 2) {
                displayImage(this.l, news.covers[2], this.s);
            } else {
                this.l.setVisibility(8);
            }
            if (news.covers.length > 1) {
                displayImage(this.k, news.covers[1], this.s);
            } else {
                this.k.setVisibility(8);
            }
            if (news.covers.length > 0) {
                displayImage(this.j, news.covers[0], this.s);
            } else {
                this.j.setVisibility(8);
            }
        }
        this.m.setOnClickListener(new d(this));
        getCellView().setOnClickListener(this.o);
        NewsRecommendManager.a(news);
    }

    public void onClick() {
        super.onClick();
        NewsRecommendManager.b((News) getItem());
    }
}
