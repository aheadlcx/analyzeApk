package qsbk.app.widget.qbnews.news;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.model.News;
import qsbk.app.utils.UIHelper;

@Deprecated
public class ThreeImageNewsCell extends NewsItemCell {
    private TextView g;
    private View h;
    private ImageView i;
    private ImageView j;
    private ImageView k;

    public void onCreate() {
        setCellView(R.layout.news_three_image_layout);
        super.onCreate();
    }

    protected void a() {
        this.g = (TextView) findViewById(R.id.news_title);
        this.h = findViewById(R.id.news_three_image);
        a(1.3333333333333333d);
        b(UIHelper.dip2px(getContext(), 84.0f));
        this.i = (ImageView) findViewById(R.id.news_image_first);
        this.j = (ImageView) findViewById(R.id.news_image_second);
        this.k = (ImageView) findViewById(R.id.news_image_third);
        setImageLayoutParams(this.i);
        setImageLayoutParams(this.j);
        setImageLayoutParams(this.k);
        this.i.setBackgroundResource(UIHelper.getDefaultImageTileBackground());
        this.j.setBackgroundResource(UIHelper.getDefaultImageTileBackground());
        this.k.setBackgroundResource(UIHelper.getDefaultImageTileBackground());
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
        String[] imageUrl = news.getImageUrl(3);
        if (imageUrl != null) {
            if (imageUrl.length > 0) {
                this.i.setVisibility(0);
                displayImage(this.i, imageUrl[0], this.s);
            } else {
                this.i.setVisibility(8);
            }
            if (imageUrl.length > 1) {
                this.j.setVisibility(0);
                displayImage(this.j, imageUrl[1], this.s);
            } else {
                this.i.setVisibility(8);
            }
            if (imageUrl.length > 2) {
                this.k.setVisibility(0);
                displayImage(this.k, imageUrl[2], this.s);
                return;
            }
            this.i.setVisibility(8);
        }
    }
}
