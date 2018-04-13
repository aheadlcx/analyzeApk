package cn.xiaochuankeji.tieba.ui.my.ugcvideo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.videomaker.a.b;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import java.util.ArrayList;
import rx.j;

public class MyUgcVideoDraftActivity extends a {
    private UltimateRecyclerView a;
    private NavigationBar b;
    private e c;
    private ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.a.a> d = new ArrayList();

    public static void a(Context context) {
        context.startActivity(new Intent(context, MyUgcVideoDraftActivity.class));
    }

    protected int a() {
        return R.layout.activity_my_draft;
    }

    protected void i_() {
        this.a = (UltimateRecyclerView) findViewById(R.id.ultimateRecyclerView);
        this.a.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.a.a(R.layout.common_empty_view, UltimateRecyclerView.a, UltimateRecyclerView.a);
        this.c = new e(this, this.d);
        this.b = (NavigationBar) findViewById(R.id.navBar);
        this.a.setAdapter(this.c);
    }

    protected void j_() {
        super.j_();
        this.b.getBackView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyUgcVideoDraftActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        e();
    }

    private void e() {
        new b().a().a(rx.a.b.a.a()).b(new j<ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.a.a>>(this) {
            final /* synthetic */ MyUgcVideoDraftActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((ArrayList) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.a.a> arrayList) {
                if (arrayList.size() == 0) {
                    this.a.finish();
                    return;
                }
                this.a.d.clear();
                this.a.d.addAll(arrayList);
                this.a.c.notifyDataSetChanged();
            }
        });
    }
}
