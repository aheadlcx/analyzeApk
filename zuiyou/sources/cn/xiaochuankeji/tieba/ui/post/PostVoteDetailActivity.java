package cn.xiaochuankeji.tieba.ui.post;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.h;
import com.tencent.wcdb.database.SQLiteDatabase;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class PostVoteDetailActivity extends h {
    private static final a g = null;
    private long d;
    private long e;
    private int f;

    static {
        j();
    }

    private static void j() {
        b bVar = new b("PostVoteDetailActivity.java", PostVoteDetailActivity.class);
        g = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.post.PostVoteDetailActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 33);
    }

    public static void a(Context context, long j, long j2, int i) {
        Intent intent = new Intent(context, PostVoteDetailActivity.class);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.putExtra("kPostIdKey", j);
        intent.putExtra("kVoteIdKey", j2);
        intent.putExtra("kPositionKey", i);
        context.startActivity(intent);
    }

    static final void a(PostVoteDetailActivity postVoteDetailActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        Bundle extras = postVoteDetailActivity.getIntent().getExtras();
        postVoteDetailActivity.d = extras.getLong("kPostIdKey");
        postVoteDetailActivity.e = extras.getLong("kVoteIdKey");
        postVoteDetailActivity.f = extras.getInt("kPositionKey");
        postVoteDetailActivity.a(h.a(postVoteDetailActivity.d, postVoteDetailActivity.e, postVoteDetailActivity.f));
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(g, this, this, bundle);
        c.c().a(new f(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_vote_detail;
    }

    protected void i_() {
    }
}
