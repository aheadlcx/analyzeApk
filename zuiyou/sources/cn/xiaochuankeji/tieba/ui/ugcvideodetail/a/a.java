package cn.xiaochuankeji.tieba.ui.ugcvideodetail.a;

import android.os.Parcelable;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class a implements Parcelable {
    cn.xiaochuankeji.tieba.api.ugcvideo.a a = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
    a b;
    b c = new b(this);

    public interface a {
        void a();

        void a(long j, int i);

        void a(UgcVideoInfoBean ugcVideoInfoBean, Moment moment);

        void a(boolean z, int i, UgcVideoInfoBean ugcVideoInfoBean);

        void b();

        void c();
    }

    public class b {
        public UgcVideoInfoBean a;
        public UgcVideoInfoBean b;
        public ArrayList<UgcVideoInfoBean> c = new ArrayList();
        public int d;
        public String e;
        public boolean f = false;
        public int g = 0;
        final /* synthetic */ a h;

        public b(a aVar) {
            this.h = aVar;
        }
    }

    protected abstract void a(boolean z);

    public abstract void b();

    public b a() {
        return this.c;
    }

    public void c() {
    }

    public void d() {
    }

    public void a(UgcVideoInfoBean ugcVideoInfoBean) {
        this.c.c.add(0, ugcVideoInfoBean);
        this.c.g = 1;
        if (this.b != null) {
            this.b.b();
        }
    }

    public void b(UgcVideoInfoBean ugcVideoInfoBean) {
        if (ugcVideoInfoBean.id == this.c.b.id) {
            this.c.b = null;
            if (this.b != null) {
                this.b.a(true, -1, ugcVideoInfoBean);
                return;
            }
            return;
        }
        int i = 0;
        while (i < this.c.c.size()) {
            if (((UgcVideoInfoBean) this.c.c.get(i)).id == ugcVideoInfoBean.id) {
                break;
            }
            i++;
        }
        i = -1;
        if (-1 != i) {
            this.c.c.remove(i);
            if (this.b != null) {
                this.b.a(false, i, ugcVideoInfoBean);
            }
        }
    }

    protected void a(List<UgcVideoInfoBean> list) {
        if (list != null && list.size() > 0) {
            Collection arrayList = new ArrayList();
            for (UgcVideoInfoBean ugcVideoInfoBean : list) {
                Object obj;
                Iterator it = this.c.c.iterator();
                while (it.hasNext()) {
                    if (((UgcVideoInfoBean) it.next()).id == ugcVideoInfoBean.id) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    arrayList.add(ugcVideoInfoBean);
                }
            }
            if (arrayList.size() > 0) {
                this.c.c.addAll(arrayList);
            }
        }
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void e() {
        this.b = null;
    }
}
