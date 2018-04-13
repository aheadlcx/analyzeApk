package cn.xiaochuankeji.tieba.ui.my;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.member.MemberCommentInfo;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.comment.c;
import cn.xiaochuankeji.tieba.ui.post.PostQueryListView;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.SDGuideDialog;
import cn.xiaochuankeji.tieba.ui.widget.g;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.Iterator;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;
import rx.j;

public class MyCommentActivity extends h implements OnClickListener {
    private static String d = "key_member_id";
    private static final a l = null;
    private QueryListView e;
    private ImageView f;
    private TextView g;
    private cn.xiaochuankeji.tieba.background.member.a h;
    private long i;
    private c j;
    private ImageView k;

    private static void v() {
        b bVar = new b("MyCommentActivity.java", MyCommentActivity.class);
        l = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.my.MyCommentActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 59);
    }

    static {
        v();
    }

    public static void a(Context context, long j, int i) {
        Intent intent = new Intent(context, MyCommentActivity.class);
        intent.putExtra(d, j);
        intent.putExtra("key_count", i);
        context.startActivity(intent);
    }

    static final void a(MyCommentActivity myCommentActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        myCommentActivity.e.h();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(l, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new b(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onResume() {
        super.onResume();
    }

    protected int a() {
        return R.layout.activity_my_comment;
    }

    protected boolean a(Bundle bundle) {
        this.i = getIntent().getExtras().getLong(d);
        if (0 == this.i) {
            return false;
        }
        this.h = new cn.xiaochuankeji.tieba.background.member.a(this.i);
        return true;
    }

    protected void i_() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_container);
        this.f = (ImageView) findViewById(R.id.ivBack);
        this.k = (ImageView) findViewById(R.id.btnHide);
        this.g = (TextView) findViewById(R.id.tvOptionText);
        this.g.setText("最新");
        c.a.b.a(this.g, 0, 0, R.drawable.ic_comment_new, 0);
        this.h.a("new");
        this.e = new PostQueryListView(this, this) {
            final /* synthetic */ MyCommentActivity d;

            public void a(boolean z, boolean z2, String str) {
                super.a(z, z2, str);
            }
        };
        this.e.f();
        this.j = new c(this, this.h);
        this.j.c = false;
        this.e.a(this.h, this.j);
        this.e.a("与人互动，心自徜徉", (int) R.drawable.ic_empty_comments, EmptyPaddingStyle.GoldenSection);
        frameLayout.addView(this.e);
        a(this.g);
    }

    protected void j_() {
        super.j_();
        this.h.registerOnQueryFinishListener(new cn.htjyb.b.a.b.b(this) {
            final /* synthetic */ MyCommentActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z, boolean z2, String str) {
                if (z && z2) {
                    this.a.e.m().setSelection(1);
                }
                Iterator it = this.a.h.getItems().iterator();
                while (it.hasNext()) {
                    MemberCommentInfo memberCommentInfo = (MemberCommentInfo) it.next();
                    if (!(memberCommentInfo == null || memberCommentInfo.comment == null)) {
                        memberCommentInfo.isSelected = memberCommentInfo.comment.isHide();
                    }
                }
                g.c(this.a);
            }
        });
        this.e.m().setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ MyCommentActivity a;

            {
                this.a = r1;
            }

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                com.izuiyou.a.a.b.e("长按事件");
                return true;
            }
        });
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.k.setOnClickListener(this);
        findViewById(R.id.ok).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                return;
            case R.id.tvOptionText:
                Object a = this.h.a();
                int i = (TextUtils.isEmpty(a) || !a.equals("hot")) ? 0 : 1;
                g.a((Activity) this);
                if (i != 0) {
                    this.g.setText("最新");
                    c.a.b.a(this.g, 0, 0, R.drawable.ic_comment_new, 0);
                    this.h.a("new");
                    this.h.refresh();
                    return;
                }
                this.g.setText("最热");
                c.a.b.a(this.g, 0, 0, R.drawable.ic_comment_hot, 0);
                this.h.a("hot");
                this.h.refresh();
                return;
            case R.id.btnHide:
            case R.id.cancel:
                j();
                return;
            case R.id.ok:
                k();
                return;
            default:
                return;
        }
    }

    private void j() {
        this.j.b = !this.j.b;
        this.j.c = this.j.b;
        if (this.j.b) {
            findViewById(R.id.fl_container).setVisibility(8);
            findViewById(R.id.fl_container_edit_mode).setVisibility(0);
        } else {
            this.e.m().d();
            findViewById(R.id.fl_container).setVisibility(0);
            findViewById(R.id.fl_container_edit_mode).setVisibility(8);
        }
        Iterator it = this.h.getItems().iterator();
        while (it.hasNext()) {
            MemberCommentInfo memberCommentInfo = (MemberCommentInfo) it.next();
            if (!(memberCommentInfo == null || memberCommentInfo.comment == null)) {
                memberCommentInfo.isSelected = memberCommentInfo.comment.isHide();
            }
        }
        this.j.notifyDataSetChanged();
    }

    private void k() {
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.h.getItems().iterator();
        while (it.hasNext()) {
            MemberCommentInfo memberCommentInfo = (MemberCommentInfo) it.next();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pid", Long.valueOf(memberCommentInfo.relativePost._ID));
            jSONObject.put("rid", Long.valueOf(memberCommentInfo.comment._id));
            jSONObject.put("hide", Integer.valueOf(memberCommentInfo.isSelected ? 1 : 0));
            jSONArray.add(jSONObject);
        }
        if (jSONArray.size() > 0) {
            g.a((Activity) this, true);
            new cn.xiaochuankeji.tieba.api.post.c().a(jSONArray).a(rx.a.b.a.a()).b(new j<Void>(this) {
                final /* synthetic */ MyCommentActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((Void) obj);
                }

                public void onCompleted() {
                    g.c(this.a);
                }

                public void onError(Throwable th) {
                    g.c(this.a);
                    cn.xiaochuankeji.tieba.background.utils.g.a(th.getMessage());
                }

                public void a(Void voidR) {
                    Iterator it = this.a.h.getItems().iterator();
                    while (it.hasNext()) {
                        MemberCommentInfo memberCommentInfo = (MemberCommentInfo) it.next();
                        if (!(memberCommentInfo == null || memberCommentInfo.comment == null)) {
                            int i;
                            Comment comment = memberCommentInfo.comment;
                            if (memberCommentInfo.isSelected) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            comment.hide = i;
                            memberCommentInfo.isSelected = false;
                        }
                    }
                    this.a.j();
                    g.c(this.a);
                }
            });
            return;
        }
        j();
    }

    private void a(View view) {
        if (cn.xiaochuankeji.tieba.background.a.a().getBoolean("first_enter_comment", true)) {
            SDGuideDialog sDGuideDialog = new SDGuideDialog(this);
            sDGuideDialog.a(null, R.drawable.tips_privacy, 53, 0, e.a(20.0f), false);
            sDGuideDialog.setOnDismissListener(new SDGuideDialog.c(this) {
                final /* synthetic */ MyCommentActivity a;

                {
                    this.a = r1;
                }

                public void a(SDGuideDialog sDGuideDialog) {
                }
            });
            sDGuideDialog.b();
            cn.xiaochuankeji.tieba.background.a.a().edit().putBoolean("first_enter_comment", false).apply();
        }
    }
}
