package cn.xiaochuankeji.tieba.ui.tag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.tag.a.c;
import cn.xiaochuankeji.tieba.ui.tag.a.d;
import cn.xiaochuankeji.tieba.ui.tag.a.e;
import com.alibaba.fastjson.JSONArray;
import java.util.ArrayList;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class NavigatorTagActivity extends h implements c {
    private static final a g = null;
    private ItemTouchHelper d;
    private c e;
    private RecyclerView f;

    static {
        w();
    }

    private static void w() {
        b bVar = new b("NavigatorTagActivity.java", NavigatorTagActivity.class);
        g = bVar.a("method-execution", bVar.a("1", "onCreate", "cn.xiaochuankeji.tieba.ui.tag.NavigatorTagActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 61);
    }

    public static void a(Activity activity, int i) {
        activity.startActivityForResult(new Intent(activity, NavigatorTagActivity.class), i);
    }

    static final void a(NavigatorTagActivity navigatorTagActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
    }

    public void onCreate(@Nullable Bundle bundle) {
        org.aspectj.lang.a a = b.a(g, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new b(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_nav_tag;
    }

    protected void i_() {
        this.a.getOptionText().setClickable(false);
        this.a.getOptionText().setTextColor(c.a.d.a.a.a().a((int) R.color.CT_6));
        this.e = new c(this, cn.xiaochuankeji.tieba.background.a.q().a());
        this.f = (RecyclerView) findViewById(R.id.recycler_view);
        this.f.setHasFixedSize(true);
        this.f.setAdapter(this.e);
        this.f.setLayoutManager(new GridLayoutManager(this, 4));
        this.f.addItemDecoration(new e(cn.xiaochuankeji.tieba.ui.utils.e.a(0.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(13.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(15.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(0.0f)));
        this.d = new ItemTouchHelper(new d(this, this.e));
        this.d.attachToRecyclerView(this.f);
        this.e.a(new c.b(this) {
            final /* synthetic */ NavigatorTagActivity a;

            {
                this.a = r1;
            }

            public void a(View view) {
                int childAdapterPosition = this.a.f.getChildAdapterPosition(view);
                if (this.a.e.a != null && childAdapterPosition < this.a.e.a.size() && childAdapterPosition >= 0) {
                    cn.xiaochuankeji.tieba.background.a.q().a(((NavigatorTag) this.a.e.a.get(childAdapterPosition)).id);
                }
                this.a.v();
            }

            public void b(View view) {
                int childAdapterPosition = this.a.f.getChildAdapterPosition(view);
                if (this.a.e.a == null || childAdapterPosition >= this.a.e.a.size() || childAdapterPosition < 0 || ((NavigatorTag) this.a.e.a.get(childAdapterPosition)).frozen != 1) {
                    this.a.a(this.a.f.getChildViewHolder(view));
                    this.a.a.getOptionText().setTextColor(c.a.d.a.a.a().a((int) R.color.CM));
                    this.a.a.getOptionText().setClickable(true);
                }
            }

            public void a() {
            }
        });
    }

    private void k() {
        JSONArray jSONArray = new JSONArray();
        cn.xiaochuankeji.tieba.background.a.q().a(this.e.a);
        ArrayList arrayList = this.e.a;
        for (int i = 0; i < arrayList.size(); i++) {
            jSONArray.add(Long.valueOf(((NavigatorTag) arrayList.get(i)).id));
        }
        if (jSONArray.size() > 0) {
            new cn.xiaochuankeji.tieba.api.tag.a().a(jSONArray).g();
        }
    }

    public void a(ViewHolder viewHolder) {
        this.d.startDrag(viewHolder);
    }

    public void s() {
        v();
    }

    private void v() {
        boolean z = false;
        if (this.e.a.size() == cn.xiaochuankeji.tieba.background.a.q().a().size()) {
            for (int i = 0; i < this.e.a.size(); i++) {
                if (((NavigatorTag) this.e.a.get(i)).id != ((NavigatorTag) cn.xiaochuankeji.tieba.background.a.q().a().get(i)).id) {
                    z = true;
                    break;
                }
            }
        } else {
            z = true;
        }
        if (z) {
            k();
        }
        c(z);
        if (j()) {
            cn.xiaochuankeji.tieba.background.a.q().a(this.e.a);
        }
        finish();
    }

    public void onBackPressed() {
        if (j()) {
            cn.xiaochuankeji.tieba.background.a.q().a(this.e.a);
        }
        super.onBackPressed();
    }

    public boolean j() {
        boolean z = false;
        for (int i = 0; i < this.e.a.size(); i++) {
            if (((NavigatorTag) this.e.a.get(i)).isNew) {
                z = true;
                ((NavigatorTag) this.e.a.get(i)).isNew = false;
            }
        }
        return z;
    }

    public void c(boolean z) {
        Intent intent = new Intent();
        intent.putExtra("key_nav_tag_chage", z);
        setResult(-1, intent);
    }
}
