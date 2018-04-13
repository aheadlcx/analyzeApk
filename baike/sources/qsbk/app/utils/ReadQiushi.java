package qsbk.app.utils;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.xiaomi.mipush.sdk.Constants;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import qsbk.app.adapter.ArticleAdapter.ViewHolder;
import qsbk.app.model.Article;
import qsbk.app.widget.OnScrollListenerWrapper;
import qsbk.app.widget.PtrLayout;

public class ReadQiushi {
    public static final HashMap<String, Boolean> mNeedSendQiushi = new HashMap();
    public static final HashMap<String, Boolean> mReadQiushi = new HashMap();

    private static class a extends OnScrollListenerWrapper {
        a(OnScrollListener onScrollListener) {
            super(onScrollListener);
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            super.onScrollStateChanged(absListView, i);
            if (i == 0) {
                int childCount = absListView.getChildCount();
                int top = absListView.getTop();
                int bottom = absListView.getBottom();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = absListView.getChildAt(i2);
                    if (childAt != null) {
                        int top2 = childAt.getTop();
                        int bottom2 = childAt.getBottom();
                        int i3 = bottom2 - top2;
                        if ((top2 <= top && bottom2 >= bottom) || (top2 >= top && bottom2 <= bottom)) {
                            a(childAt);
                        } else if (top2 >= top && bottom2 >= bottom) {
                            if ((((float) i3) / 3.0f) - ((float) (bottom2 - bottom)) >= 0.0f) {
                                a(childAt);
                            }
                        } else if (top2 <= top && bottom2 <= bottom && (((float) i3) / 3.0f) - ((float) (top - top2)) >= 0.0f) {
                            a(childAt);
                        }
                    }
                }
            }
        }

        private void a(View view) {
            if (view != null) {
                Object tag = view.getTag();
                if (tag != null && (tag instanceof ViewHolder)) {
                    ReadQiushi.setRead(((ViewHolder) tag).article);
                }
            }
        }
    }

    private ReadQiushi() {
    }

    public static void setRead(Article article) {
        if (article != null) {
            String str = article.id;
            if (!mReadQiushi.containsKey(str)) {
                mReadQiushi.put(str, Boolean.TRUE);
                mNeedSendQiushi.put(str, Boolean.TRUE);
            }
        }
    }

    public static boolean setFirstArticleRead(List<Object> list) {
        if (list != null && list.size() > 0) {
            Object obj = list.get(0);
            if (obj instanceof Article) {
                setRead((Article) obj);
                return true;
            }
        }
        return false;
    }

    public static void trackListView(PtrLayout ptrLayout) {
        ptrLayout.setOnScrollListener(new a(ptrLayout.getOnScrollListener()));
    }

    public static String getString() {
        Set<String> keySet = mNeedSendQiushi.keySet();
        keySet.remove(null);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        for (String append : keySet) {
            stringBuffer.append(append);
            stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        }
        if (stringBuffer.length() > 1) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public static void init() {
        String sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("_read_qiushi_");
        if (sharePreferencesValue.length() > 2) {
            for (String str : sharePreferencesValue.substring(1, sharePreferencesValue.length() - 1).split(Constants.ACCEPT_TIME_SEPARATOR_SP)) {
                mNeedSendQiushi.put(str.trim(), Boolean.TRUE);
                mReadQiushi.put(str.trim(), Boolean.TRUE);
            }
        }
    }

    public static void markSend() {
        mNeedSendQiushi.clear();
    }

    public static void destroy() {
        Log.e(ReadQiushi.class.getSimpleName(), "onDestroy " + getString());
        save2Local();
        mNeedSendQiushi.clear();
        mReadQiushi.clear();
    }

    public static void save2Local() {
        SharePreferenceUtils.setSharePreferencesValue("_read_qiushi_", getString());
    }
}
