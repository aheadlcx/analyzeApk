package cn.xiaochuankeji.tieba.ui.homepage.banner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.UgcEventJson;
import cn.xiaochuankeji.tieba.json.UgcEventListJson;
import cn.xiaochuankeji.tieba.ui.base.a;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import java.util.ArrayList;
import rx.j;

public class UgcEventListActivity extends a {
    private UltimateRecyclerView a;
    private a b;
    private ArrayList<UgcEventJson> c = new ArrayList();
    private cn.xiaochuankeji.tieba.api.ugcvideo.a d = new cn.xiaochuankeji.tieba.api.ugcvideo.a();

    public static void a(Context context) {
        context.startActivity(new Intent(context, UgcEventListActivity.class));
    }

    public boolean h() {
        return false;
    }

    protected int a() {
        return R.layout.activity_ugcevent_list;
    }

    protected void i_() {
        this.a = (UltimateRecyclerView) findViewById(R.id.ulRecyclerView);
        this.a.setLayoutManager(new LinearLayoutManager(this));
        this.b = new a(this, this.c);
        this.a.setAdapter(this.b);
        e();
        findViewById(R.id.ivBack).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UgcEventListActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
    }

    private void e() {
        this.d.b().a(rx.a.b.a.a()).b(new j<UgcEventListJson>(this) {
            final /* synthetic */ UgcEventListActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((UgcEventListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.a(th.getMessage());
            }

            public void a(UgcEventListJson ugcEventListJson) {
                if (ugcEventListJson.eventList.size() > 0) {
                    this.a.c.addAll(ugcEventListJson.eventList);
                    this.a.b.notifyDataSetChanged();
                    return;
                }
                g.a("暂时没有活动");
            }
        });
    }
}
