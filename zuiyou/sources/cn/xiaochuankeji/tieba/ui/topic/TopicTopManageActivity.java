package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.topic.TopicDetail.TopPostInfo;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.topic.t.a;
import java.io.Serializable;
import java.util.List;
import org.aspectj.a.b.b;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class TopicTopManageActivity extends h implements a {
    private static final org.aspectj.lang.a.a i = null;
    private RecyclerView d;
    private t e;
    private List<TopPostInfo> f;
    private List<Post> g;
    private long h;

    static {
        j();
    }

    private static void j() {
        b bVar = new b("TopicTopManageActivity.java", TopicTopManageActivity.class);
        i = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.topic.TopicTopManageActivity", "android.os.Bundle", "savedInstance", "", "void"), 58);
    }

    public static void a(Context context, List<TopPostInfo> list, long j, List<Post> list2) {
        Intent intent = new Intent(context, TopicTopManageActivity.class);
        intent.putExtra("topic_top_list", (Serializable) list);
        intent.putExtra("topic_id", j);
        intent.putExtra("top_relate_post", (Serializable) list2);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_manage_top;
    }

    protected void i_() {
        this.d = (RecyclerView) findViewById(R.id.top_list);
        this.d.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.e = new t(this, this.h, this.g, this);
        this.d.setAdapter(this.e);
        this.e.a(this.f);
    }

    static final void a(TopicTopManageActivity topicTopManageActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        topicTopManageActivity.h = topicTopManageActivity.getIntent().getLongExtra("topic_id", 0);
        topicTopManageActivity.f = (List) topicTopManageActivity.getIntent().getSerializableExtra("topic_top_list");
        topicTopManageActivity.g = (List) topicTopManageActivity.getIntent().getSerializableExtra("top_relate_post");
        super.onCreate(bundle);
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(i, this, this, bundle);
        c.c().a(new u(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void a(long j) {
        EventPostTopRemoved eventPostTopRemoved = new EventPostTopRemoved();
        eventPostTopRemoved.postId = j;
        org.greenrobot.eventbus.c.a().d(eventPostTopRemoved);
    }

    @l(a = ThreadMode.MAIN)
    public void postAdd(TopPostInfo topPostInfo) {
        if (topPostInfo != null && topPostInfo.img_id > 0) {
            for (int i = 0; i < this.f.size(); i++) {
                if (((TopPostInfo) this.f.get(i)).pid == topPostInfo.pid) {
                    ((TopPostInfo) this.f.get(i)).img_id = topPostInfo.img_id;
                    ((TopPostInfo) this.f.get(i)).text = topPostInfo.text;
                    this.e.notifyItemChanged(i);
                    return;
                }
            }
        }
    }
}
