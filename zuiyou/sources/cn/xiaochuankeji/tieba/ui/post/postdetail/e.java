package cn.xiaochuankeji.tieba.ui.post.postdetail;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import cn.htjyb.b.a.b.b;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.post.j;
import cn.xiaochuankeji.tieba.background.post.r;
import cn.xiaochuankeji.tieba.background.review.d;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.newshare.CommentShareDataModel;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.post.PostQueryListView;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.c;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.f;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.json.JSONObject;

public class e implements OnItemLongClickListener, b, cn.htjyb.ui.a, cn.xiaochuankeji.tieba.background.review.b.a, cn.xiaochuankeji.tieba.background.review.b.b, cn.xiaochuankeji.tieba.ui.post.postdetail.d.a, d.b, SDBottomSheet.b {
    private QueryListView a;
    private Activity b;
    private Context c;
    private j d;
    private d e;
    private d f;
    private cn.xiaochuankeji.tieba.background.review.b g;
    private Post h;
    private Comment i;
    private long j;
    private a k;
    private int l;
    private int m = 0;
    private int n = 0;
    private b o = new b(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void a(boolean z, boolean z2, String str) {
            if (z) {
                this.a.f.notifyDataSetChanged();
                if (z2) {
                    this.a.d();
                    return;
                }
                return;
            }
            g.a(str);
        }
    };

    public interface a {
        void a(boolean z, Comment comment, int i, String str);

        void v();
    }

    public e(Activity activity, Post post, a aVar) {
        this.b = activity;
        this.c = activity;
        this.h = post;
        this.j = this.h._ID;
        this.k = aVar;
        k();
    }

    private void k() {
        l();
        m();
    }

    private void l() {
        this.d = new j(this.j);
        this.d.registerOnQueryFinishListener(this);
        this.e = new d(this.j);
        this.e.registerOnQueryFinishListener(this.o);
        this.f = new d(this.c, this.h, this.e, this.d);
        this.f.a((cn.xiaochuankeji.tieba.ui.post.postdetail.d.a) this);
        this.f.a((d.b) this);
        this.g = cn.xiaochuankeji.tieba.background.a.k();
    }

    private void m() {
        this.a = (QueryListView) ((Activity) this.c).findViewById(R.id.list);
    }

    public void a() {
        this.f.a(true);
        this.a.a(this.d, this.f);
        this.f.notifyDataSetChanged();
    }

    public void a(cn.htjyb.b.a.d dVar) {
        if (this.a instanceof PostQueryListView) {
            ((PostQueryListView) this.a).a(dVar);
        }
    }

    public void b() {
        this.a.m().setSelection(2);
    }

    public void d() {
        this.a.m().setSelection(2);
    }

    public void a(ArrayList<Comment> arrayList, boolean z) {
        this.d.setItems(arrayList);
        this.d.a(z);
        this.f.notifyDataSetChanged();
    }

    public void e() {
        this.e.a();
        this.f.a(false, null);
    }

    public void a(ArrayList<Comment> arrayList, long j, String str, boolean z, long j2) {
        this.e.a(arrayList, j, str, z, j2);
        this.a.a(this.e, this.f);
        this.f.a(false);
        this.f.notifyDataSetChanged();
        this.f.a(true, "查看全部评论 >");
    }

    public void a(String str, cn.xiaochuankeji.tieba.background.post.a aVar, String str2, ArrayList<Long> arrayList, ArrayList<Long> arrayList2) {
        this.g.a(this.j, str, aVar, arrayList, arrayList2, str2, this);
    }

    public void a(long j, String str, cn.xiaochuankeji.tieba.background.post.a aVar, String str2, ArrayList<Long> arrayList, ArrayList<Long> arrayList2) {
        this.g.a(this.j, j, str, aVar, arrayList, str2, arrayList2, this);
    }

    public void a(boolean z, String str, Comment comment, int i) {
        this.k.a(z, comment, i, str);
    }

    public void b(Comment comment, int i) {
        this.d.b(comment);
        this.h._commentCount = i;
        r.a().b();
    }

    public void a(boolean z, long j, String str) {
        if (z) {
            a(j);
        } else {
            g.a(str);
        }
    }

    private void a(long j) {
        b(j);
        int i = this.h._commentCount - 1;
        if (i < 0) {
            i = 0;
        }
        this.h._commentCount = i;
        this.f.notifyDataSetChanged();
    }

    private void b(long j) {
        int i = 0;
        for (int i2 = 0; i2 < this.e.itemCount(); i2++) {
            Comment comment = (Comment) this.e.itemAt(i2);
            if (comment._id == j) {
                this.e.a(comment);
                break;
            }
        }
        while (i < this.d.itemCount()) {
            comment = (Comment) this.d.itemAt(i);
            if (comment._id == j) {
                this.d.a(comment);
                return;
            }
            i++;
        }
    }

    public void c() {
        this.d.unregisterOnQueryFinishedListener(this);
        this.e.unregisterOnQueryFinishedListener(this.o);
        this.f.b();
    }

    public void f() {
        if (this.d != null && this.f != null) {
            this.d.clear();
            this.f.notifyDataSetChanged();
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        return false;
    }

    private void n() {
        Object obj = 1;
        SDBottomSheet sDBottomSheet = new SDBottomSheet(this.b, this);
        ArrayList arrayList = new ArrayList();
        Object obj2 = (this.i._commentContent == null || this.i._commentContent.trim().length() <= 0) ? null : 1;
        if (this.i._writerID != cn.xiaochuankeji.tieba.background.a.g().c()) {
            obj = null;
        }
        if (obj2 != null) {
            arrayList.add(new c(R.drawable.icon_option_copy, "复制文字", 6));
        }
        if (obj != null) {
            arrayList.add(new c(R.drawable.icon_option_delete, "删除", 9));
        } else {
            arrayList.add(new c(R.drawable.icon_option_report, "举报", 12));
        }
        if (this.i.isInnerComment()) {
            sDBottomSheet.a(arrayList, null);
        } else {
            sDBottomSheet.a(sDBottomSheet.c(), arrayList);
        }
        sDBottomSheet.b();
    }

    public void a(int i) {
        if (i == 4 || i == 2 || i == 3 || i == 5 || i == 1) {
            c(i);
        } else if (i == 6) {
            cn.xiaochuankeji.tieba.ui.utils.d.a(this.i._commentContent);
            g.a("已复制");
        } else if (i == 9) {
            f.a("提示", "删除评论后,下面的回复也会被删除,确定删除？", (Activity) this.c, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                    if (z) {
                        this.a.g.a(this.a.j, this.a.i._id, this.a);
                    }
                }
            });
        } else if (i == 12) {
            o();
        } else if (i == 18) {
            this.i.copyLink();
        }
    }

    private void c(final int i) {
        ShareDataModel commentShareDataModel;
        if (this.h != null) {
            commentShareDataModel = new CommentShareDataModel(this.i, this.h, i);
        } else {
            commentShareDataModel = new CommentShareDataModel(this.i, this.j, i);
        }
        commentShareDataModel.prepareData(new cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel.a(this) {
            final /* synthetic */ e c;

            public void a() {
                cn.xiaochuankeji.tieba.background.utils.share.b.a().a(this.c.b, commentShareDataModel);
                cn.xiaochuankeji.tieba.background.i.a.a(this.c.i._pid, this.c.i._id, "postdetail", (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(i)), commentShareDataModel.getABTestId());
            }
        });
    }

    private void o() {
        LinkedHashMap o = cn.xiaochuankeji.tieba.background.utils.c.a.c().o();
        if (o.size() == 0) {
            d(0);
            return;
        }
        SDCheckSheet sDCheckSheet = new SDCheckSheet(this.b, new cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet.a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(int i) {
                long j = this.a.h._ID;
                long j2 = this.a.i._id;
                if (i == -123) {
                    CustomReportReasonActivity.a(this.a.b, j, j2, this.a.l, "review");
                } else {
                    this.a.d(i);
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
                this.l = parseInt;
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

    private void d(int i) {
        new cn.xiaochuankeji.tieba.background.b.b(this.h._ID, this.i._id, "review", i, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ e a;

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
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }

    public void a(Comment comment, int i) {
        InnerCommentDetailActivity.a(this.b, this.h, comment._id, comment._status);
    }

    public void j() {
        if (this.k != null) {
            this.k.v();
        }
    }

    public void a(Comment comment) {
        this.i = comment;
        n();
    }

    public void a(boolean z, boolean z2, String str) {
        if (z) {
            this.f.notifyDataSetChanged();
        } else {
            g.a(str);
        }
    }

    public void a(View view) {
        if (this.f != null) {
            this.f.c();
        }
    }

    public void a(boolean z) {
        int lastVisiblePosition = this.a.getLastVisiblePosition();
        if (z) {
            if (this.m > lastVisiblePosition) {
                lastVisiblePosition = this.m;
            }
            this.m = lastVisiblePosition;
            return;
        }
        if (this.n > lastVisiblePosition) {
            lastVisiblePosition = this.n;
        }
        this.n = lastVisiblePosition;
    }

    public int g() {
        if (!this.f.d()) {
            int lastVisiblePosition = this.a.getLastVisiblePosition();
            if (this.m > lastVisiblePosition) {
                lastVisiblePosition = this.m;
            }
            this.m = lastVisiblePosition;
        }
        return this.m;
    }

    public int h() {
        if (this.f.d()) {
            int lastVisiblePosition = this.a.getLastVisiblePosition();
            if (this.n > lastVisiblePosition) {
                lastVisiblePosition = this.n;
            }
            this.n = lastVisiblePosition;
        }
        return this.n;
    }

    public void b(int i) {
        if (this.f.d()) {
            if (this.n > i) {
                i = this.n;
            }
            this.n = i;
            return;
        }
        if (this.m > i) {
            i = this.m;
        }
        this.m = i;
    }

    public boolean i() {
        if (this.f == null) {
            return true;
        }
        return this.f.d();
    }
}
