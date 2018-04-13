package cn.xiaochuankeji.tieba.ui.widget.overscroll.a;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class b implements a {
    protected final RecyclerView b;
    protected final a c;
    protected boolean d = false;

    protected interface a {
        boolean a();

        boolean b();
    }

    protected class b implements a {
        final /* synthetic */ b a;

        protected b(b bVar) {
            this.a = bVar;
        }

        public boolean a() {
            return !this.a.b.canScrollHorizontally(-1);
        }

        public boolean b() {
            return !this.a.b.canScrollHorizontally(1);
        }
    }

    protected class c implements a {
        final /* synthetic */ b a;

        protected c(b bVar) {
            this.a = bVar;
        }

        public boolean a() {
            return !this.a.b.canScrollVertically(-1);
        }

        public boolean b() {
            return !this.a.b.canScrollVertically(1);
        }
    }

    public b(RecyclerView recyclerView) {
        this.b = recyclerView;
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if ((layoutManager instanceof LinearLayoutManager) || (layoutManager instanceof StaggeredGridLayoutManager)) {
            int orientation;
            if (layoutManager instanceof LinearLayoutManager) {
                orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            } else {
                orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            }
            if (orientation == 0) {
                this.c = new b(this);
                return;
            } else {
                this.c = new c(this);
                return;
            }
        }
        throw new IllegalArgumentException("Recycler views with custom layout managers are not supported by this adapter out of the box.Try implementing and providing an explicit 'impl' parameter to the other c'tors, or otherwise create a custom adapter subclass of your own.");
    }

    public View c() {
        return this.b;
    }

    public boolean a() {
        return !this.d && this.c.a();
    }

    public boolean b() {
        return !this.d && this.c.b();
    }
}
