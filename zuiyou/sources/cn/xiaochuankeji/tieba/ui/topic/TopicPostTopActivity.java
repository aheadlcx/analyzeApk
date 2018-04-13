package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.data.post.PostAndCommentsRequest;
import cn.xiaochuankeji.tieba.background.data.post.PostAndCommentsRequest.OnQueryPostFinishListener;
import cn.xiaochuankeji.tieba.background.topic.TopicDetail.TopPostInfo;
import cn.xiaochuankeji.tieba.background.upload.f;
import cn.xiaochuankeji.tieba.background.upload.j;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.topic.TopImageConfigJson;
import cn.xiaochuankeji.tieba.json.topic.TopImageConfigJson.ImageId;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.post.postitem.e;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.SelectPicturesActivity;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.SelectPicturesActivity.SelectEntranceType;
import cn.xiaochuankeji.tieba.ui.topic.b.b;
import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.aspectj.lang.a.a;
import org.json.JSONObject;
import rx.d;

public class TopicPostTopActivity extends h implements OnQueryPostFinishListener, b {
    private static final a x = null;
    LayoutManager d;
    ArrayList<String> e = new ArrayList();
    private SimpleDraweeView f;
    private RecyclerView g;
    private FrameLayout h;
    private ImageView i;
    private b j;
    private EditText k;
    private Handler l;
    private Uri m;
    private int n = -1;
    private cn.xiaochuankeji.tieba.api.topic.b o;
    private cn.xiaochuankeji.tieba.api.config.a p;
    private Post q;
    private long r;
    private long s;
    private String t;
    private List<ImageId> u;
    private PostAndCommentsRequest v;
    private j w;

    static {
        v();
    }

    private static void v() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("TopicPostTopActivity.java", TopicPostTopActivity.class);
        x = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.topic.TopicPostTopActivity", "android.os.Bundle", "savedInstance", "", "void"), 147);
    }

    public static void a(Context context, Post post, long j, String str) {
        Intent intent = new Intent(context, TopicPostTopActivity.class);
        intent.putExtra("post", post);
        intent.putExtra("image_id", j);
        intent.putExtra("top_text", str);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_topic_top;
    }

    protected void i_() {
        this.f = (SimpleDraweeView) findViewById(R.id.post_top_image);
        this.g = (RecyclerView) findViewById(R.id.image_list);
        this.i = (ImageView) findViewById(R.id.iv_action_image);
        this.h = (FrameLayout) findViewById(R.id.topic_summary);
        this.k = (EditText) findViewById(R.id.title_topic);
        if (!TextUtils.isEmpty(this.t)) {
            this.k.setText(this.t);
        }
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TopicPostTopActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                SelectPicturesActivity.a(this.a, this.a.e, SelectEntranceType.kTopicEditTop, 10);
            }
        });
        this.d = new LinearLayoutManager(this, 0, false);
        this.g.setLayoutManager(this.d);
        this.j = new b(this, this);
        this.g.setAdapter(this.j);
        this.g.setNestedScrollingEnabled(false);
        this.f.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ TopicPostTopActivity a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                this.a.a(this.a.m);
                return false;
            }
        });
        this.l = new Handler(Looper.getMainLooper());
        this.v = new PostAndCommentsRequest(this.q._ID);
        this.v.registerOnQueryPostFinishListener(this);
        j();
    }

    static final void a(TopicPostTopActivity topicPostTopActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        topicPostTopActivity.q = (Post) topicPostTopActivity.getIntent().getSerializableExtra("post");
        topicPostTopActivity.r = topicPostTopActivity.getIntent().getLongExtra("topic_id", 0);
        topicPostTopActivity.s = topicPostTopActivity.getIntent().getLongExtra("image_id", 0);
        topicPostTopActivity.t = topicPostTopActivity.getIntent().getStringExtra("top_text");
        topicPostTopActivity.o = new cn.xiaochuankeji.tieba.api.topic.b();
        topicPostTopActivity.p = new cn.xiaochuankeji.tieba.api.config.a();
        super.onCreate(bundle);
        topicPostTopActivity.k();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(x, this, this, bundle);
        c.c().a(new s(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    public boolean h() {
        return false;
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.w != null) {
            this.w.a();
        }
        this.v.unregisterOnQueryPostFinishListener();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Uri parse;
        if (i == 10) {
            if (-1 == i2) {
                ArrayList arrayList = (ArrayList) intent.getSerializableExtra("key_selected_local_media");
                this.e.clear();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    LocalMedia localMedia = (LocalMedia) it.next();
                    if (localMedia.type != 1 && cn.htjyb.c.a.b.c(localMedia.path)) {
                        this.e.add(localMedia.path);
                    }
                }
                for (int i3 = 0; i3 < this.e.size(); i3++) {
                    try {
                        parse = Uri.parse("file://" + ((String) this.e.get(i3)));
                        if (parse != null && parse.isAbsolute()) {
                            com.izuiyou.a.a.b.b("uri is absolute");
                            a(parse);
                            return;
                        }
                    } catch (Exception e) {
                    }
                }
            }
        } else if (i == 69) {
            if (-1 == i2) {
                parse = cn.xiaochuan.image.a.b.a(intent);
                com.izuiyou.a.a.b.b("path:" + parse.toString());
                this.m = parse;
                this.f.setImageURI(this.m);
                this.j.a(parse);
                this.l.postDelayed(new Runnable(this) {
                    final /* synthetic */ TopicPostTopActivity a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a(this.a.j.getItemCount() - 1, this.a.n);
                    }
                }, 500);
            }
        } else if (i == 70 && this.m != null && this.m.isAbsolute()) {
            com.izuiyou.a.a.b.b("path:" + this.m.toString());
            this.f.setImageURI(this.m);
            this.j.a(this.m);
            this.l.postDelayed(new Runnable(this) {
                final /* synthetic */ TopicPostTopActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a(this.a.j.getItemCount() - 1, this.a.n);
                }
            }, 500);
        }
    }

    public void s() {
        if (this.q._topic != null && this.q._topic._topicID != 0) {
            if (this.n < 0 && this.s > 0) {
                a(this.s);
            } else if (this.n < this.u.size()) {
                a(((ImageId) this.u.get(this.n)).bigImageId);
            } else {
                LocalMedia localMedia = new LocalMedia();
                localMedia.type = 2;
                localMedia.path = this.m.getPath();
                List arrayList = new ArrayList(1);
                arrayList.add(localMedia);
                this.w = new j();
                this.w.a(arrayList, "", null, new f(this) {
                    final /* synthetic */ TopicPostTopActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(List<Long> list, List<Long> list2, HashMap<String, LocalMedia> hashMap) {
                        if (list2.size() > 0) {
                            this.a.a(((Long) list2.get(0)).longValue());
                        }
                    }

                    public void a(String str) {
                        g.a("上传失败");
                    }
                });
            }
        }
    }

    public void a(int i, int i2) {
        View findViewByPosition = this.d.findViewByPosition(i2);
        if (findViewByPosition != null) {
            findViewByPosition.findViewById(R.id.image_container).setSelected(false);
        }
        findViewByPosition = this.d.findViewByPosition(i);
        if (findViewByPosition != null) {
            findViewByPosition.findViewById(R.id.image_container).setSelected(true);
        }
        Uri a = this.j.a(i);
        if (i < this.u.size()) {
            String b = cn.xiaochuankeji.tieba.background.f.b.b(((ImageId) this.u.get(i)).bigImageId).b();
            com.izuiyou.a.a.b.b("url:" + b);
            a = Uri.parse(b);
        }
        if (a != null) {
            this.f.setImageURI(a);
            this.m = a;
            this.n = i;
        }
    }

    private void a(Uri uri) {
        try {
            Uri fromFile = Uri.fromFile(new File(cn.xiaochuankeji.tieba.background.a.e().B(), new File(uri.getPath()).getName()));
            int i = getResources().getDisplayMetrics().widthPixels;
            if (uri != null) {
                try {
                    if (uri.isAbsolute()) {
                        cn.xiaochuan.image.a.b.a(this, uri, fromFile, i, "裁剪置顶封面", 3, 1);
                    }
                } catch (Exception e) {
                    if (uri == null) {
                        return;
                    }
                    if (uri.isAbsolute()) {
                        this.m = cn.xiaochuan.image.a.b.a(this, uri, fromFile, 70, 3, 1);
                    }
                }
            }
        } catch (Exception e2) {
            this.m = uri;
            this.f.setImageURI(this.m);
            this.j.a(this.m);
            this.l.postDelayed(new Runnable(this) {
                final /* synthetic */ TopicPostTopActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a(this.a.j.getItemCount() - 1, this.a.n);
                }
            }, 500);
        }
    }

    private void a(long j) {
        final long j2 = this.q._ID;
        long j3 = this.q == null ? this.r : this.q._topic._topicID;
        final String obj = this.k.getText().toString();
        j3 = j;
        this.o.a(j3, j2, obj, j).a(rx.a.b.a.a()).b(new rx.j<Void>(this) {
            final /* synthetic */ TopicPostTopActivity d;

            public /* synthetic */ void onNext(Object obj) {
                a((Void) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (th instanceof ClientErrorException) {
                    g.a(th.getMessage());
                }
            }

            public void a(Void voidR) {
                g.a("置顶成功");
                TopPostInfo topPostInfo = new TopPostInfo();
                topPostInfo.img_id = j3;
                topPostInfo.pid = j2;
                topPostInfo.text = obj;
                org.greenrobot.eventbus.c.a().d(topPostInfo);
                this.d.finish();
            }
        });
    }

    private void j() {
        e gVar;
        int size = this.q._imgList.size();
        if (size == 0) {
            gVar = new cn.xiaochuankeji.tieba.ui.post.postitem.g(this);
        } else if (size == 1) {
            gVar = new cn.xiaochuankeji.tieba.ui.post.postitem.h(this);
        } else {
            gVar = new cn.xiaochuankeji.tieba.ui.post.postitem.f(this, size);
        }
        if (!(this.q == null || this.q._topic == null || this.q._topic._topicID <= 0)) {
            gVar.a(this.q, 0);
            ((FrameLayout) findViewById(R.id.topic_summary)).addView(gVar.i());
            gVar.j();
        }
        if ((this.q._topic == null || this.q._topic._topicID == 0) && this.v != null) {
            this.v.query();
        }
    }

    private void k() {
        d.a(Boolean.valueOf(true)).d(new rx.b.g<Boolean, cn.xiaochuankeji.tieba.a.a>(this) {
            final /* synthetic */ TopicPostTopActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((Boolean) obj);
            }

            public cn.xiaochuankeji.tieba.a.a a(Boolean bool) {
                return cn.xiaochuankeji.tieba.a.b.a("top_image");
            }
        }).b(rx.f.a.c()).c(new rx.b.g<cn.xiaochuankeji.tieba.a.a, d<TopImageConfigJson>>(this) {
            final /* synthetic */ TopicPostTopActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((cn.xiaochuankeji.tieba.a.a) obj);
            }

            public d<TopImageConfigJson> a(cn.xiaochuankeji.tieba.a.a aVar) {
                if (aVar == null || TextUtils.isEmpty(aVar.e)) {
                    return this.a.p.a(0);
                }
                return d.a((TopImageConfigJson) JSON.parseObject(aVar.e, TopImageConfigJson.class));
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new rx.j<TopImageConfigJson>(this) {
            final /* synthetic */ TopicPostTopActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((TopImageConfigJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(TopImageConfigJson topImageConfigJson) {
                this.a.u = topImageConfigJson.imageIdList;
                List arrayList = new ArrayList();
                int b = this.a.f(this.a.s);
                com.izuiyou.a.a.b.b("imageIndex:" + b);
                if (b >= 0) {
                    this.a.n = b;
                }
                for (int i = 0; i < this.a.u.size(); i++) {
                    arrayList.add(Uri.parse(cn.xiaochuankeji.tieba.background.f.b.b(((ImageId) this.a.u.get(i)).smallImageId).b()));
                }
                this.a.j.a(arrayList, this.a.n);
                if (this.a.n >= 0) {
                    this.a.f.setImageURI(cn.xiaochuankeji.tieba.background.f.b.b(((ImageId) this.a.u.get(this.a.n)).bigImageId).b());
                } else if (this.a.s > 0) {
                    this.a.n = -1;
                    this.a.f.setImageURI(cn.xiaochuankeji.tieba.background.f.b.b(this.a.s).b());
                } else {
                    this.a.n = 0;
                    this.a.f.setImageURI(cn.xiaochuankeji.tieba.background.f.b.b(((ImageId) this.a.u.get(this.a.n)).bigImageId).b());
                }
            }
        });
    }

    private int f(long j) {
        for (int i = 0; i < this.u.size(); i++) {
            if (((ImageId) this.u.get(i)).bigImageId == j) {
                return i;
            }
        }
        return -1;
    }

    public void onQueryPostFinish(boolean z, JSONObject jSONObject, ArrayList<Comment> arrayList, ArrayList<Comment> arrayList2, boolean z2, boolean z3, String str) {
        if (jSONObject != null) {
            this.q = new Post(jSONObject);
            j();
        }
    }
}
