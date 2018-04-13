package qsbk.app.abtest;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LogUtil;

public class ArticleActionStater {
    public static final String ACTION = "action";
    public static final String ACTION_COMMENT = "Comment";
    public static final String ACTION_DETAIL = "Detail";
    public static final String ACTION_VOTE_DOWN = "Vote_Down";
    public static final String ACTION_VOTE_UP = "Vote_Up";
    public static final String ART_TYPE = "art_type";
    public static final String KEY_STAT_ARTICLE_ACTION = "STAT_ARTICLE_ACTION";
    public static ArticleActionStater mStater = null;
    private LocalBroadcastManager a;
    private long b;

    private ArticleActionStater() {
        this.a = null;
        this.b = System.currentTimeMillis();
        this.a = LocalBroadcastManager.getInstance(QsbkApp.mContext);
    }

    public static ArticleActionStater getInstance() {
        if (mStater == null) {
            mStater = new ArticleActionStater();
        }
        return mStater;
    }

    public void statAction(String str, String str2) {
        LogUtil.d("stat action:" + str);
        if (!TextUtils.isEmpty(str2) && System.currentTimeMillis() - this.b >= 50) {
            LogUtil.d("stat action success:" + str + " type:" + str2);
            this.b = System.currentTimeMillis();
            Intent intent = new Intent(KEY_STAT_ARTICLE_ACTION);
            intent.putExtra("action", str);
            intent.putExtra(ART_TYPE, str2);
            this.a.sendBroadcast(intent);
        }
    }
}
