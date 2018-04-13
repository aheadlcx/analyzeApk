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
public class OneImageNewsRecommendCell extends NewsItemCell {
    private ImageView g;
    private TextView h;
    private TextView i;
    private ImageView j;
    private TextView k;
    private View l;
    private OnClickListener m = new a(this);

    public void onCreate() {
        setCellView(R.layout.news_one_image_recommend);
        super.onCreate();
    }

    protected void a() {
        this.g = (ImageView) findViewById(R.id.news_user_icon);
        this.g.setBackgroundResource(UIHelper.getDefaultAvatar());
        displayImage(this.g, null, UIHelper.getDrawable(UIHelper.isNightTheme() ? R.drawable.news_icon_night : R.drawable.news_icon));
        this.h = (TextView) findViewById(R.id.news_user_name);
        this.i = (TextView) findViewById(R.id.news_description);
        this.j = (ImageView) findViewById(R.id.news_image);
        this.l = findViewById(R.id.content_layout);
        a(1.0d);
        a(UIHelper.getScreenWidth(getContext()) - UIHelper.dip2px(getContext(), 32.0f));
        setImageLayoutParams(this.j);
        this.j.setBackgroundResource(UIHelper.getDefaultImageTileBackground());
        this.k = (TextView) findViewById(R.id.news_more);
        this.k.setText("查看更多糗闻");
        this.k.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.live_arrow_night : R.drawable.live_arrow), null, null, null);
        if (UIHelper.isNightTheme()) {
            this.l.setBackgroundColor(UIHelper.getColor(R.color.transparent));
        }
    }

    public void onUpdate() {
        News news = (News) getItem();
        this.i.setText(TextUtils.isEmpty(news.title) ? news.content : news.title);
        if (news.isOneImageNews()) {
            displayImage(this.j, news.covers[0], this.s);
        }
        this.k.setOnClickListener(new b(this));
        getCellView().setOnClickListener(this.m);
        NewsRecommendManager.a(news);
    }

    public void onClick() {
        super.onClick();
        NewsRecommendManager.b((News) getItem());
    }
}
