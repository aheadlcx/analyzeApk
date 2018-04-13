package cn.xiaochuankeji.tieba.ui.topic.deleteList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import cn.htjyb.b.a.b.b;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.post.PostQueryListView;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import com.iflytek.aiui.AIUIConstant;

public class TopicDeletePostActivity extends h {
    private NavigationBar d;
    private ImageView e;
    private RelativeLayout f;
    private PostQueryListView g;
    private long h;
    private MemberInfoBean i;
    private a j;

    public static void a(Context context, MemberInfoBean memberInfoBean, long j) {
        Intent intent = new Intent(context, TopicDeletePostActivity.class);
        intent.putExtra(AIUIConstant.USER, memberInfoBean);
        intent.putExtra("topicId", j);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_delete_post_list;
    }

    protected void i_() {
        this.d = (NavigationBar) findViewById(R.id.navBar);
        this.e = (ImageView) findViewById(R.id.emptyView);
        if (this.i != null) {
            this.d.setTitle(this.i.getNickName() + "-删帖");
        }
        this.f = (RelativeLayout) findViewById(R.id.rootView);
        this.g = j();
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(3, R.id.navBar);
        this.f.addView(this.g, layoutParams);
        this.g.a(this.j);
        this.j.registerOnQueryFinishListener(new b(this) {
            final /* synthetic */ TopicDeletePostActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z, boolean z2, String str) {
                if (!z) {
                    g.a(str);
                }
            }
        });
        this.g.h();
    }

    protected boolean a(Bundle bundle) {
        this.h = getIntent().getLongExtra("topicId", 0);
        this.i = (MemberInfoBean) getIntent().getSerializableExtra(AIUIConstant.USER);
        this.j = new a(this.h, this.i.getId());
        return true;
    }

    private PostQueryListView j() {
        PostQueryListView postQueryListView = new PostQueryListView(this);
        postQueryListView.f();
        postQueryListView.m().setId(R.id.id_delete_post_list);
        postQueryListView.a("列表空空小右懵懵", (int) R.drawable.img_exception_ugcvideo_recommend_empty, EmptyPaddingStyle.GoldenSection);
        return postQueryListView;
    }
}
