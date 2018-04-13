package qsbk.app.widget.qbnews.news;

import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Locale;
import qsbk.app.R;
import qsbk.app.activity.NewsWebActivity;
import qsbk.app.model.News;
import qsbk.app.utils.NumberUtils;
import qsbk.app.utils.StringUtils;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.qbnews.BaseNewsCell;

@Deprecated
public abstract class NewsItemCell extends BaseNewsCell {
    private static final SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    protected TextView d;
    protected TextView e;
    protected TextView f;

    public void onCreate() {
        this.s = TileBackground.getBackgroud(getContext(), BgImageType.ARTICLE);
        a();
    }

    protected void c() {
        this.d = (TextView) findViewById(R.id.news_time);
        this.e = (TextView) findViewById(R.id.news_come_from);
        this.f = (TextView) findViewById(R.id.news_take_part_in);
        this.f.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.news_part_in_bg_night : R.drawable.news_part_in_bg);
    }

    protected String c(int i) {
        return NumberUtils.format1(i) + "参与";
    }

    protected String a(String str) {
        return str;
    }

    public void onClick() {
        News news = (News) getItem();
        if (!StringUtils.isEmpty(news.webLink)) {
            NewsWebActivity.launch(getContext(), news);
        }
    }
}
