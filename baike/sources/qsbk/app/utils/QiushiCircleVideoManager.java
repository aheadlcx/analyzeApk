package qsbk.app.utils;

import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.Constants;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.utils.NetworkUtils;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.CircleArticle;

public class QiushiCircleVideoManager {
    private static QiushiCircleVideoManager a;
    public static ArrayList<CircleArticle> circleVideos = new ArrayList();

    public static QiushiCircleVideoManager getInstance() {
        if (a == null) {
            a = new QiushiCircleVideoManager();
        }
        return a;
    }

    public static void add(CircleArticle circleArticle) {
        if (!circleVideos.contains(circleArticle)) {
            circleVideos.add(circleArticle);
        }
    }

    public static void remove(CircleArticle circleArticle) {
        circleVideos.remove(circleArticle);
    }

    public static void addList(ArrayList<CircleArticle> arrayList) {
        if (circleVideos.size() == 0) {
            circleVideos.addAll(arrayList);
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            add((CircleArticle) it.next());
        }
    }

    public static void removeList(ArrayList<CircleArticle> arrayList) {
        if (circleVideos.size() != 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                remove((CircleArticle) it.next());
            }
        }
    }

    public void load() {
        circleVideos.clear();
        if (NetworkUtils.getInstance().isWifiAvailable()) {
            a(10);
        } else {
            a(4);
        }
    }

    private void a(int i) {
        if (i >= 0) {
            new SimpleHttpTask(String.format(Constants.QIUSHI_CIRCLE_VIDEO_RECOMMEND, new Object[]{Integer.valueOf(i)}), new ao(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }
}
