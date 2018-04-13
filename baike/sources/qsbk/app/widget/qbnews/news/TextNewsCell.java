package qsbk.app.widget.qbnews.news;

import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.model.News;

@Deprecated
public class TextNewsCell extends NewsItemCell {
    private TextView g;

    public void onCreate() {
        setCellView(R.layout.news_text_layout);
        super.onCreate();
    }

    protected void a() {
        this.g = (TextView) findViewById(R.id.news_title);
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
    }
}
