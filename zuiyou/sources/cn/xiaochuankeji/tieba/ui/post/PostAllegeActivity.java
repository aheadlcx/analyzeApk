package cn.xiaochuankeji.tieba.ui.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.data.post.PostAndCommentsRequest;
import cn.xiaochuankeji.tieba.background.data.post.PostAndCommentsRequest.OnQueryPostFinishListener;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.post.postdetail.VoiceDetailController;
import cn.xiaochuankeji.tieba.ui.post.postitem.e;
import cn.xiaochuankeji.tieba.ui.post.postitem.f;
import cn.xiaochuankeji.tieba.ui.publish.selecttopic.SelectTopicActivity;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.util.ArrayList;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;
import org.json.JSONObject;
import rx.j;

public class PostAllegeActivity extends h {
    private static final a t = null;
    private FrameLayout d;
    private EditText e;
    private Button f;
    private Button g;
    private Button h;
    private Button i;
    private LinearLayout j;
    private LinearLayout k;
    private LinearLayout l;
    private TextView m;
    private long n;
    private long o;
    private Topic p;
    private Post q;
    private e r;
    private VoiceDetailController s;

    static {
        w();
    }

    private static void w() {
        b bVar = new b("PostAllegeActivity.java", PostAllegeActivity.class);
        t = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.post.PostAllegeActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 175);
    }

    public static void a(Context context, long j, long j2) {
        Intent intent = new Intent(context, PostAllegeActivity.class);
        intent.putExtra("post_id", j);
        intent.putExtra("topic_id", j2);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_allege_post;
    }

    protected void i_() {
        this.d = (FrameLayout) findViewById(R.id.topic_summary);
        this.e = (EditText) findViewById(R.id.text_allege_content);
        this.f = (Button) findViewById(R.id.btn_submit_allege);
        this.h = (Button) findViewById(R.id.btn_modify_topic);
        this.i = (Button) findViewById(R.id.btn_allege_topic);
        this.g = (Button) findViewById(R.id.btn_submit_topic);
        this.l = (LinearLayout) findViewById(R.id.ll_buttons);
        this.j = (LinearLayout) findViewById(R.id.ll_allege_reason);
        this.k = (LinearLayout) findViewById(R.id.select_topic);
        this.m = (TextView) findViewById(R.id.textSelectTopic);
        this.l.setVisibility(8);
        k();
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PostAllegeActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String obj = this.a.e.getText().toString();
                if (TextUtils.isEmpty(obj.trim())) {
                    g.a("请填写申诉理由");
                    return;
                }
                com.izuiyou.a.a.b.b("content:" + String.format("{content:\"%s\"}", new Object[]{obj}));
                new cn.xiaochuankeji.tieba.background.b.b(this.a.o, this.a.n, "topic_complain", 0, obj, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onResponse(Object obj, Object obj2) {
                        a((JSONObject) obj, obj2);
                    }

                    public void a(JSONObject jSONObject, Object obj) {
                        com.izuiyou.a.a.b.b(jSONObject);
                        g.a("提交申诉成功");
                        cn.htjyb.c.a.a(this.a.a, this.a.a.e);
                        this.a.a.finish();
                    }
                }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onErrorResponse(XCError xCError, Object obj) {
                        g.a(xCError.getMessage());
                    }
                }).execute();
            }
        });
        this.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PostAllegeActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.p == null) {
                    g.a("请选择话题");
                    return;
                }
                new cn.xiaochuankeji.tieba.api.topic.b().c(this.a.n, this.a.p._topicID).a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onNext(Object obj) {
                        a((EmptyJson) obj);
                    }

                    public void onCompleted() {
                    }

                    public void onError(Throwable th) {
                        if (th instanceof ClientErrorException) {
                            g.a(th.getMessage());
                        } else {
                            g.a("网络错误");
                        }
                    }

                    public void a(EmptyJson emptyJson) {
                        g.a("修改成功");
                        this.a.a.finish();
                    }
                });
                this.a.j.setVisibility(8);
                this.a.l.setVisibility(0);
                this.a.k.setVisibility(8);
            }
        });
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PostAllegeActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.j.setVisibility(8);
                this.a.l.setVisibility(8);
                this.a.k.setVisibility(0);
            }
        });
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PostAllegeActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.j.setVisibility(0);
                this.a.l.setVisibility(8);
                this.a.k.setVisibility(8);
            }
        });
        this.m.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PostAllegeActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                SelectTopicActivity.a(this.a, 1, 0);
            }
        });
    }

    static final void a(PostAllegeActivity postAllegeActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        postAllegeActivity.n = postAllegeActivity.getIntent().getLongExtra("post_id", 0);
        postAllegeActivity.o = postAllegeActivity.getIntent().getLongExtra("topic_id", 0);
        super.onCreate(bundle);
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(t, this, this, bundle);
        c.c().a(new b(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (-1 == i2 && 1 == i) {
            this.p = new Topic(intent);
            j();
        }
    }

    public void onBackPressed() {
        if (this.l.getVisibility() == 0) {
            super.onBackPressed();
        } else if (this.q == null || this.q.status == -1) {
            this.j.setVisibility(8);
            this.l.setVisibility(0);
            this.k.setVisibility(8);
        } else {
            super.onBackPressed();
        }
    }

    protected void onPause() {
        super.onPause();
        cn.htjyb.c.a.a((Activity) this);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.r != null) {
            this.r.o();
        }
        if (this.s != null) {
            this.s.c();
        }
    }

    private void j() {
        if (this.p != null) {
            this.m.setText(this.p._topicName);
            this.m.setSelected(true);
        }
    }

    private void k() {
        v();
    }

    private void v() {
        if (this.n > 0) {
            PostAndCommentsRequest postAndCommentsRequest = new PostAndCommentsRequest(this.n);
            postAndCommentsRequest.registerOnQueryPostFinishListener(new OnQueryPostFinishListener(this) {
                final /* synthetic */ PostAllegeActivity a;

                {
                    this.a = r1;
                }

                public void onQueryPostFinish(boolean z, JSONObject jSONObject, ArrayList<Comment> arrayList, ArrayList<Comment> arrayList2, boolean z2, boolean z3, String str) {
                    if (jSONObject == null) {
                        g.a("帖子不存在");
                        this.a.finish();
                        return;
                    }
                    Post post = new Post(jSONObject);
                    PostDataBean postDataBeanFromJson = PostDataBean.getPostDataBeanFromJson(jSONObject);
                    if (postDataBeanFromJson.localPostType() == 2) {
                        this.a.a(postDataBeanFromJson, post);
                    } else {
                        this.a.a(post);
                    }
                    if (post.status != -1) {
                        this.a.j.setVisibility(0);
                        this.a.l.setVisibility(8);
                        this.a.k.setVisibility(8);
                        return;
                    }
                    this.a.j.setVisibility(8);
                    this.a.l.setVisibility(0);
                    this.a.k.setVisibility(8);
                }
            });
            postAndCommentsRequest.query();
        }
    }

    private void a(Post post) {
        if (post != null && post._ID >= 0) {
            this.q = post;
            int size = post._imgList.size();
            if (size == 0) {
                this.r = new cn.xiaochuankeji.tieba.ui.post.postitem.g(this);
            } else if (size == 1) {
                this.r = new cn.xiaochuankeji.tieba.ui.post.postitem.h(this);
            } else {
                this.r = new f(this, size);
            }
            if (post != null) {
                this.r.a(post, 0);
                ((FrameLayout) findViewById(R.id.topic_summary)).addView(this.r.i());
                this.r.j();
                this.r.n();
                return;
            }
            Post post2 = new Post(this.n);
        }
    }

    private void a(PostDataBean postDataBean, Post post) {
        if (postDataBean != null && post != null && postDataBean.getId() >= 0) {
            this.q = post;
            this.s = new VoiceDetailController(postDataBean, this);
            ((FrameLayout) findViewById(R.id.topic_summary)).addView(this.s.b());
        }
    }
}
