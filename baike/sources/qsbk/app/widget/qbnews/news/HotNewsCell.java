package qsbk.app.widget.qbnews.news;

import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.model.News;
import qsbk.app.utils.UIHelper;

@Deprecated
public class HotNewsCell extends NewsItemCell {
    private ImageView g;
    private TextView h;
    private TextView i;

    public void onCreate() {
        setCellView(R.layout.news_hot_layout);
        super.onCreate();
    }

    protected void a() {
        this.g = (ImageView) findViewById(R.id.hot_news_image);
        a(2.0d);
        setImageLayoutParams(this.g);
        this.g.setBackgroundResource(UIHelper.getDefaultImageTileBackground());
        this.h = (TextView) findViewById(R.id.hot_news_title);
        this.i = (TextView) findViewById(R.id.hot_news_take_part_in);
    }

    public void onUpdate() {
        News news = (News) getItem();
        this.h.setText(news.title);
        if (news.participateCount > 0) {
            this.i.setVisibility(0);
            this.i.setText(c(news.participateCount));
        } else {
            this.i.setVisibility(8);
        }
        String[] imageUrl = news.getImageUrl(1);
        if (imageUrl != null && imageUrl.length > 0) {
            displayImage(this.g, imageUrl[0], this.s);
        }
    }

    public void onItemChange(Object obj) {
        super.onItemChange(obj);
    }
}
