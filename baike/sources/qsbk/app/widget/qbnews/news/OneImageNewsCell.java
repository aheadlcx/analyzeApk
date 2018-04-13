package qsbk.app.widget.qbnews.news;

import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.model.News;
import qsbk.app.utils.UIHelper;

@Deprecated
public class OneImageNewsCell extends NewsItemCell {
    private TextView g;
    private ImageView h;

    public void onCreate() {
        setCellView(R.layout.news_one_image_layout);
        super.onCreate();
    }

    protected void a() {
        this.g = (TextView) findViewById(R.id.news_title);
        this.h = (ImageView) findViewById(R.id.news_image);
        a(1.3333333333333333d);
        b(UIHelper.dip2px(getContext(), 70.0f));
        setImageLayoutParams(this.h);
        this.h.setBackgroundResource(UIHelper.getDefaultImageTileBackground());
        c();
    }

    public void onUpdate() {
        News news = (News) getItem();
        this.g.setText(news.title);
        if (news.participateCount > 0) {
            this.f.setVisibility(0);
            this.f.setText(c(news.participateCount));
        } else {
            this.f.setVisibility(8);
        }
        this.e.setText(news.source);
        this.d.setText(a(news.createdAt));
        String[] imageUrl = news.getImageUrl(1);
        if (imageUrl != null && imageUrl.length > 0) {
            displayImage(this.h, imageUrl[0], this.s);
        }
    }
}
