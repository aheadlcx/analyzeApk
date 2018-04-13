package cn.xiaochuankeji.tieba.ui.homepage.feed.model;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.json.feed.FeedListJson;
import cn.xiaochuankeji.tieba.ui.recommend.data.RecPostDataBean;
import java.util.List;

public interface a {
    @WorkerThread
    void a(@Nullable FeedListJson feedListJson);

    void a(String str);

    void a(String str, boolean z, boolean z2, boolean z3, List<RecPostDataBean> list);

    void a(List<MemberInfoBean> list, String str);

    void a(boolean z, boolean z2, List<RecPostDataBean> list);
}
