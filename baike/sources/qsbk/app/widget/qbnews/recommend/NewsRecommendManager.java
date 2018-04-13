package qsbk.app.widget.qbnews.recommend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import qsbk.app.model.News;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.TimeDelta;

@Deprecated
public class NewsRecommendManager {
    public static final int NEWS_DISTANCE_RECOMMEND_POSITION = 16;
    public static final int NEWS_FIRST_RECOMMEND_POSITION = 16;
    private static final Map<String, Integer> a = new HashMap();
    private static NewsRecommendManager b = null;
    private static TimeDelta c = new TimeDelta();
    private static List<News> d = new ArrayList();
    private static Map<Integer, Integer> e = new HashMap();

    private NewsRecommendManager() {
    }

    public static synchronized NewsRecommendManager getInstance() {
        NewsRecommendManager newsRecommendManager;
        synchronized (NewsRecommendManager.class) {
            if (b == null) {
                b = new NewsRecommendManager();
            }
            if (d.isEmpty()) {
                newsRecommendManager = b;
            } else {
                newsRecommendManager = b;
            }
        }
        return newsRecommendManager;
    }

    public static void setLastInsertPosition(String str, int i) {
        a.put(str, Integer.valueOf(i));
    }

    public static int getLastInsertPosition(String str) {
        return a.containsKey(str) ? ((Integer) a.get(str)).intValue() : -1;
    }

    static void a(News news) {
        if (news != null) {
            e.put(Integer.valueOf(news.hashCode()), Integer.valueOf((e.containsKey(Integer.valueOf(news.hashCode())) ? ((Integer) e.get(Integer.valueOf(news.hashCode()))).intValue() : 0) + 1));
        }
    }

    static void b(News news) {
        if (d != null && d.contains(news)) {
            d.remove(news);
        }
    }

    private static void a() {
    }

    public List<News> getRecommendNews(int i) {
        if (d.isEmpty() || c.getDelta() > ListViewHelper.DEFAULT_TIPS_TO_REFRESH_INTERVAL) {
            a();
        }
        Object arrayList = new ArrayList();
        Collections.shuffle(d);
        int i2 = 0;
        while (i2 < i && i2 < d.size()) {
            int intValue;
            int i3;
            News news = (News) d.get(i2);
            if (e.containsKey(Integer.valueOf(news.hashCode()))) {
                intValue = ((Integer) e.get(Integer.valueOf(news.hashCode()))).intValue();
            } else {
                intValue = 0;
            }
            if (3 > ((long) intValue)) {
                arrayList.add(news);
                i3 = i2;
            } else {
                d.remove(news);
                i3 = i2 - 1;
            }
            i2 = i3 + 1;
        }
        d.removeAll(arrayList);
        d.addAll(arrayList);
        return arrayList;
    }
}
