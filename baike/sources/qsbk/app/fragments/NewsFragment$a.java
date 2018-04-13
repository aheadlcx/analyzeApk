package qsbk.app.fragments;

import android.text.TextUtils;
import com.alipay.android.phone.mrpc.core.Headers;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MainActivity;
import qsbk.app.cache.FileCache;
import qsbk.app.cache.MemoryCache;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.Md5;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

class NewsFragment$a implements OnAsyncLoadListener {
    final /* synthetic */ NewsFragment a;
    private String b = "";

    public NewsFragment$a(NewsFragment newsFragment, String str) {
        this.a = newsFragment;
        NewsFragment.b(newsFragment, str);
    }

    public void onPrepareListener() {
        NewsFragment.a(this.a, true);
        StringBuilder stringBuilder = new StringBuilder(NewsFragment.c(this.a));
        if (NewsFragment.c(this.a).contains("?")) {
            stringBuilder.append("&page=").append(NewsFragment.d(this.a));
        } else {
            stringBuilder.append("?page=").append(NewsFragment.d(this.a));
        }
        if (UIHelper.isNightTheme()) {
            stringBuilder.append("&theme=night");
        }
        stringBuilder.append("&count=").append(30);
        this.b = stringBuilder.indexOf("?") == -1 ? NewsFragment.c(this.a) : stringBuilder.toString();
    }

    public String onStartListener() {
        String str = "";
        try {
            str = MemoryCache.findOrCreateMemoryCache().get(Md5.MD5(this.b));
            if (TextUtils.isEmpty(str)) {
                DebugUtil.debug("NewsFragment", "没有预加载，获取网络数据");
                str = HttpClient.getIntentce().get(this.b);
            }
        } catch (QiushibaikeException e) {
        } catch (Exception e2) {
        }
        DebugUtil.debug("NewsFragment", "loadContent:" + str);
        return str;
    }

    public void onFinishListener(String str) {
        if (TextUtils.isEmpty(str)) {
            if (NewsFragment.e(this.a) != null) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "加载失败，请稍后再试！", Integer.valueOf(0)).show();
            }
            if (Headers.REFRESH.equals(NewsFragment.g(this.a))) {
                NewsFragment.b(this.a).refreshDone(false);
            } else if ("load_more".equals(NewsFragment.g(this.a))) {
                NewsFragment.b(this.a).loadMoreDone(false);
            }
        } else {
            if (NewsFragment.d(this.a) == 1) {
                FileCache.cacheTextToDisk(NewsFragment.e(this.a), NewsFragment.f(this.a), str);
            }
            NewsFragment.a(this.a, str);
            ListViewHelper.saveLastUpdateTime(NewsFragment.e(this.a), NewsFragment.f(this.a));
            if (NewsFragment.e(this.a) instanceof MainActivity) {
                ((MainActivity) NewsFragment.e(this.a)).requestHideSmallTips(this.a);
            }
            if (Headers.REFRESH.equals(NewsFragment.g(this.a))) {
                this.a.scrollToTop();
            }
        }
        NewsFragment.a(this.a, false);
    }
}
