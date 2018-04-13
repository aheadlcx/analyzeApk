package cn.xiaochuankeji.tieba.ui.post.postdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import cn.htjyb.b.a.b.b;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.review.CommentPublisher;
import cn.xiaochuankeji.tieba.background.review.CommentPublisher.PublishType;
import cn.xiaochuankeji.tieba.background.review.c;
import cn.xiaochuankeji.tieba.background.upload.j;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.newshare.CommentShareDataModel;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.json.ForbidReplyJson;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.post.b.d;
import cn.xiaochuankeji.tieba.ui.post.postdetail.d.a;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.f;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class InnerCommentDetailActivity extends h implements OnClickListener, b, a, f.b, SDBottomSheet.b {
    private static final org.aspectj.lang.a.a B = null;
    private cn.xiaochuankeji.tieba.background.post.a A;
    private long d;
    private long e;
    private int f;
    private Post g;
    private SubcommentFilter h;
    private c i;
    private boolean j;
    private boolean k = false;
    private cn.xiaochuankeji.tieba.api.review.a l;
    private d m;
    private f n;
    private QueryListView o;
    private CommentPublisher p;
    private Comment q;
    private cn.xiaochuankeji.tieba.background.review.b r;
    private int s;
    private FrameLayout t;
    private j u;
    private long v = 0;
    private String w;
    private long x;
    private String y;
    private String z;

    public static class SubcommentFilter implements Serializable {
        public static final int QUERY_KEY_EQ = 1;
        public static final int QUERY_KEY_GE = 2;
        private final int queryKey;
        private final long srccommentId;
        private final long subcommentId;

        public SubcommentFilter(long j, long j2, int i) {
            this.subcommentId = j;
            this.srccommentId = j2;
            this.queryKey = i;
        }

        public String getQueryType() {
            String str = "equal";
            switch (this.queryKey) {
                case 1:
                    return "equal";
                case 2:
                    return "late";
                default:
                    return str;
            }
        }
    }

    static {
        y();
    }

    private static void y() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("InnerCommentDetailActivity.java", InnerCommentDetailActivity.class);
        B = bVar.a("method-execution", bVar.a("1", "onCreate", "cn.xiaochuankeji.tieba.ui.post.postdetail.InnerCommentDetailActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 181);
    }

    public static void a(Context context, Post post, long j, int i) {
        Intent intent = new Intent(context, InnerCommentDetailActivity.class);
        intent.putExtra("key_post", post);
        intent.putExtra("key_post_id", post._ID);
        intent.putExtra("key_rid", j);
        intent.putExtra(NotificationCompat.CATEGORY_STATUS, i);
        if (context instanceof PostDetailActivity) {
            intent.putExtra("key_show_is_from_post_detail", true);
        }
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        context.startActivity(intent);
    }

    public static void a(Context context, long j, long j2, int i) {
        a(context, j, j2, i, null);
    }

    public static void a(Context context, long j, long j2, int i, SubcommentFilter subcommentFilter) {
        a(context, j, j2, i, subcommentFilter, null);
    }

    public static void a(Context context, long j, long j2, int i, SubcommentFilter subcommentFilter, EntranceType entranceType) {
        Intent intent = new Intent(context, InnerCommentDetailActivity.class);
        intent.putExtra("key_post_id", j);
        intent.putExtra("key_rid", j2);
        intent.putExtra("key_from", a(entranceType));
        intent.putExtra(NotificationCompat.CATEGORY_STATUS, i);
        intent.putExtra("key_subcomment_filter", subcommentFilter);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        if (context instanceof PostDetailActivity) {
            intent.putExtra("key_show_is_from_post_detail", true);
        }
        context.startActivity(intent);
    }

    private static String a(EntranceType entranceType) {
        if (entranceType != null) {
            switch (entranceType) {
                case Chat:
                    return "chat";
                case Notify:
                    return "notify";
                case Push:
                    return "push";
            }
        }
        return null;
    }

    static final void a(InnerCommentDetailActivity innerCommentDetailActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        innerCommentDetailActivity.i.refresh();
        innerCommentDetailActivity.v = System.currentTimeMillis();
    }

    public void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(B, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new a(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_inner_comment;
    }

    protected boolean a(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        Serializable serializable = extras.getSerializable("key_post");
        if (serializable != null) {
            this.g = (Post) serializable;
        }
        this.e = extras.getLong("key_post_id");
        this.d = extras.getLong("key_rid");
        this.w = extras.getString("key_from");
        this.f = extras.getInt(NotificationCompat.CATEGORY_STATUS, 0);
        this.h = (SubcommentFilter) extras.getSerializable("key_subcomment_filter");
        this.j = extras.getBoolean("key_show_is_from_post_detail", false);
        w();
        this.n = new f(this, this);
        this.x = this.d;
        this.r = new cn.xiaochuankeji.tieba.background.review.b();
        this.l = new cn.xiaochuankeji.tieba.api.review.a();
        return true;
    }

    private void w() {
        boolean z = true;
        if (this.h == null) {
            long j = this.e;
            long j2 = this.d;
            if (this.g != null) {
                z = false;
            }
            this.i = new c(j, j2, z, this.f);
        } else {
            boolean z2;
            long j3 = this.e;
            long j4 = this.d;
            if (this.g == null) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.i = new c(j3, j4, z2, this.h.srccommentId, this.h.subcommentId, this.h.getQueryType());
        }
        this.i.b(this.w);
        this.i.registerOnQueryFinishListener(this);
    }

    protected void c() {
        super.c();
        this.t = (FrameLayout) findViewById(R.id.input_container);
        this.m = new d(this, new d.a(this) {
            final /* synthetic */ InnerCommentDetailActivity a;

            {
                this.a = r1;
            }

            public void a(long j, String str, String str2, cn.xiaochuankeji.tieba.background.post.a aVar, ArrayList<LocalMedia> arrayList) {
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this.a, "comment_detail", 4, 1112)) {
                    this.a.x = j;
                    this.a.z = str;
                    this.a.A = aVar;
                    this.a.y = str2;
                    if (arrayList.size() == 0) {
                        this.a.a(null, null);
                        return;
                    }
                    this.a.u = new j();
                    this.a.n.a((ArrayList) arrayList, this.a.u);
                }
            }

            public void a(ArrayList<LocalMedia> arrayList) {
                ArrayList arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((LocalMedia) it.next()).path);
                }
                if (arrayList == null || arrayList.size() == 0) {
                    cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.a(this.a, 1234);
                } else {
                    cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.c(this.a, 1234, (List) arrayList);
                }
            }
        });
        this.t.addView(this.m.f_());
    }

    protected void i_() {
        this.o = (QueryListView) findViewById(R.id.queryListView);
        this.o.f();
    }

    protected void j_() {
        super.j_();
    }

    private void a(ArrayList<Long> arrayList, ArrayList<Long> arrayList2) {
        if (!this.n.c()) {
            this.n.a();
            this.n.a("正在发评论", 10, 0);
        }
        this.x = 0 == this.x ? this.d : this.x;
        this.p = new CommentPublisher(PublishType.Reply, this.e, this.x);
        this.p.a(this.z, this.A, arrayList, this.y, arrayList2, new cn.xiaochuankeji.tieba.background.review.b.a(this) {
            final /* synthetic */ InnerCommentDetailActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z, String str, final Comment comment, int i) {
                if (z) {
                    this.a.n.a(new f.a(this) {
                        final /* synthetic */ AnonymousClass2 b;

                        public void a() {
                            if (!this.b.a.isFinishing()) {
                                g.a("评论发送成功");
                                this.b.a.m.h();
                                this.b.a.i.a(comment);
                            }
                        }
                    });
                    return;
                }
                this.a.n.b();
                g.a(str);
            }
        });
    }

    public void a(boolean z, boolean z2, String str) {
        if (z && z2) {
            if (this.g == null) {
                this.g = this.i.a();
            }
            BaseAdapter dVar = new d(this, this.g, this.i, null);
            if (this.j) {
                dVar.a();
            }
            if (this.h != null) {
                dVar.a(true, "查看全部回复 >");
            }
            this.o.a(this.i, dVar);
            dVar.a((a) this);
            String b = this.i.b();
            if (b != null) {
                this.a.getIvExtraOption().setSelected(b.equals("ct"));
            }
            if (this.i.itemAt(0) != null && ((Comment) this.i.itemAt(0)).hasForbidReply() && this.i.c()) {
                this.k = true;
                this.m.e();
                return;
            }
            return;
        }
        g.a(str);
        if (!TextUtils.isEmpty(str) && str.equals("评论不存在") && !isFinishing()) {
            finish();
        }
    }

    public void a(boolean z, ArrayList<Long> arrayList, ArrayList<Long> arrayList2, String str) {
        if (z) {
            a((ArrayList) arrayList, (ArrayList) arrayList2);
            return;
        }
        g.a(str);
        this.n.b();
    }

    public void a(Comment comment, int i) {
        if (i > 0 && !this.k) {
            this.m.a(comment._id, "回复 " + comment._writerName + ":");
        }
    }

    public void j() {
        this.h = null;
        w();
        this.i.refresh();
    }

    public void a(Comment comment) {
        this.q = comment;
        c(false);
    }

    private void c(boolean z) {
        Object obj = 1;
        SDBottomSheet sDBottomSheet = new SDBottomSheet(this, this);
        ArrayList arrayList = new ArrayList();
        Object obj2 = (this.q._commentContent == null || this.q._commentContent.trim().length() <= 0) ? null : 1;
        if (this.q._writerID != cn.xiaochuankeji.tieba.background.a.g().c()) {
            obj = null;
        }
        if (obj2 != null) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_copy, "复制文字", 6));
        }
        if (obj != null) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_delete, "删除", 9));
            if (z && this.i.c()) {
                if (this.k) {
                    arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_topic_block, "取消禁止", 15));
                } else {
                    arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_topic_block, "禁止评论", 14));
                }
            }
        } else {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_report, "举报", 12));
        }
        if (this.q.isInnerComment()) {
            sDBottomSheet.a(arrayList, null);
        } else {
            sDBottomSheet.a(sDBottomSheet.c(), arrayList);
        }
        sDBottomSheet.b();
    }

    public void a(int i) {
        if (i == 4 || i == 2 || i == 3 || i == 5 || i == 1) {
            b(i);
        } else if (i == 6) {
            cn.xiaochuankeji.tieba.ui.utils.d.a(this.q._commentContent);
            g.a("已复制");
        } else if (i == 9) {
            f.a("提示", "删除后不可恢复，确定删除？", this, new f.a(this) {
                final /* synthetic */ InnerCommentDetailActivity a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                    if (z) {
                        this.a.r.a(this.a.e, this.a.q._id, new cn.xiaochuankeji.tieba.background.review.b.b(this) {
                            final /* synthetic */ AnonymousClass3 a;

                            {
                                this.a = r1;
                            }

                            public void a(boolean z, long j, String str) {
                                if (z) {
                                    this.a.a.a(this.a.a.q._id);
                                } else {
                                    g.a(str);
                                }
                            }
                        });
                    }
                }
            });
        } else if (i == 12) {
            x();
        } else if (i == 14) {
            d(true);
        } else if (i == 15) {
            d(false);
        } else if (i == 18) {
            this.q.copyLink();
        }
    }

    private void d(final boolean z) {
        this.l.a(this.e, this.d, z).a(rx.a.b.a.a()).b(new rx.j<ForbidReplyJson>(this) {
            final /* synthetic */ InnerCommentDetailActivity b;

            public /* synthetic */ void onNext(Object obj) {
                a((ForbidReplyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.a(th.getMessage());
            }

            public void a(ForbidReplyJson forbidReplyJson) {
                this.b.k = z;
                if (this.b.k) {
                    this.b.m.e();
                } else {
                    this.b.m.f();
                }
                g.a(z ? "已禁止评论" : "已取消");
            }
        });
    }

    private void b(final int i) {
        ShareDataModel commentShareDataModel;
        if (this.g != null) {
            commentShareDataModel = new CommentShareDataModel(this.q, this.g, i);
        } else {
            commentShareDataModel = new CommentShareDataModel(this.q, this.e, i);
        }
        commentShareDataModel.prepareData(new ShareDataModel.a(this) {
            final /* synthetic */ InnerCommentDetailActivity c;

            public void a() {
                cn.xiaochuankeji.tieba.background.utils.share.b.a().a(this.c, commentShareDataModel);
                cn.xiaochuankeji.tieba.background.i.a.a(this.c.q._pid, this.c.q._id, "commentdetail", (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(i)), commentShareDataModel.getABTestId());
            }
        });
    }

    public void b(boolean z) {
        this.m.a(z);
    }

    public boolean k() {
        return this.c;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (-1 == i2 && 1234 == i) {
            this.m.a((ArrayList) cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.b(intent));
        }
    }

    private void a(long j) {
        this.i.a(j);
        ArrayList items = this.i.getItems();
        if (items == null || items.size() == 0) {
            onBackPressed();
        }
    }

    private void x() {
        LinkedHashMap o = cn.xiaochuankeji.tieba.background.utils.c.a.c().o();
        if (o.size() == 0) {
            c(0);
            return;
        }
        SDCheckSheet sDCheckSheet = new SDCheckSheet(this, new SDCheckSheet.a(this) {
            final /* synthetic */ InnerCommentDetailActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                long j = this.a.g._ID;
                long j2 = this.a.q._id;
                if (i == -123) {
                    CustomReportReasonActivity.a(this.a, j, j2, this.a.s, "review");
                } else {
                    this.a.c(i);
                }
            }
        });
        int i = 0;
        for (Entry entry : o.entrySet()) {
            int i2;
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            int parseInt = Integer.parseInt(str);
            int i3 = i + 1;
            String trim = str2.trim();
            if (trim.equals("其他")) {
                this.s = parseInt;
                i2 = -123;
            } else {
                i2 = parseInt;
            }
            if (i3 == o.size()) {
                sDCheckSheet.a(trim, i2, true);
            } else {
                sDCheckSheet.a(trim, i2, false);
            }
            i = i3;
        }
        sDCheckSheet.b();
    }

    private void c(int i) {
        new cn.xiaochuankeji.tieba.background.b.b(this.g._ID, this.q._id, "review", i, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ InnerCommentDetailActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                g.a("举报成功");
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ InnerCommentDetailActivity a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }

    public void onClick(View view) {
        view.getId();
    }

    public void s() {
        if (this.i.itemCount() != 0) {
            this.q = (Comment) this.i.itemAt(0);
            c(true);
        }
    }

    public void t() {
        if (this.i.itemCount() != 0) {
            v();
        }
    }

    public void v() {
        if (this.a.getIvExtraOption().isSelected()) {
            this.a.getIvExtraOption().setSelected(false);
            this.i.a("likes");
            this.i.refresh();
            return;
        }
        this.i.a("ct");
        this.a.getIvExtraOption().setSelected(true);
        this.i.refresh();
    }

    public void onBackPressed() {
        if (!this.n.c()) {
            super.onBackPressed();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.u != null) {
            this.u.a();
        }
        if (this.m != null) {
            this.m.g();
        }
        Map hashMap = new HashMap();
        hashMap.put("remain_time", Long.valueOf((System.currentTimeMillis() - this.v) / 1000));
        hashMap.put("max_sub", Integer.valueOf(this.o.getLastVisiblePosition() - 1));
        cn.xiaochuankeji.tieba.background.utils.monitor.a.b.a().a("view", "subreviews", this.e, this.d, "postdetail", hashMap);
    }
}
