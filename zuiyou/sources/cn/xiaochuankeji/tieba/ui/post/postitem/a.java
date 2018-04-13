package cn.xiaochuankeji.tieba.ui.post.postitem;

import android.app.Activity;
import cn.htjyb.b.a.d;
import cn.xiaochuankeji.tieba.background.favorite.Favorite;
import cn.xiaochuankeji.tieba.background.favorite.f;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.ui.my.favorite.CreateOrEditFavoriteActivity;
import cn.xiaochuankeji.tieba.ui.widget.b;
import org.json.JSONObject;

public class a extends b implements cn.xiaochuankeji.tieba.background.favorite.f.a {
    private f e;
    private long f;
    private a g;

    public interface a {
        void a(boolean z);
    }

    public a(Activity activity, a aVar) {
        super(activity);
        this.g = aVar;
    }

    public void a(long j) {
        this.f = j;
    }

    protected d a() {
        this.e = f.a();
        return this.e;
    }

    protected String a(int i) {
        return ((Favorite) this.e.itemAt(i)).getName();
    }

    protected String b(int i) {
        return ((Favorite) this.e.itemAt(i)).getPostCount() + "条内容";
    }

    protected void b() {
        CreateOrEditFavoriteActivity.a((Activity) this.a, -1);
    }

    protected void c(int i) {
        Favorite favorite = (Favorite) this.e.itemAt(i);
        long currentTimeMillis = System.currentTimeMillis();
        if (favorite == null) {
            cn.htjyb.ui.widget.a.a(this.a, (CharSequence) "请先创建收藏夹，再进行收藏", 0).show();
        } else {
            new cn.xiaochuankeji.tieba.background.favorite.a(currentTimeMillis, this.f, favorite.getId(), new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    Favorite favorite = new Favorite(jSONObject);
                    f.a().a(favorite.getId(), favorite.getPostCount());
                    this.a.f();
                    if (this.a.g != null) {
                        this.a.g.a(true);
                    }
                    this.a.e.b(this.a);
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    if (this.a.g != null) {
                        this.a.g.a(false);
                    }
                    this.a.e.b(this.a);
                }
            }).execute();
        }
    }

    public void k_() {
        g();
    }

    public void e() {
        super.e();
        this.e.a((cn.xiaochuankeji.tieba.background.favorite.f.a) this);
    }

    public void d() {
    }
}
