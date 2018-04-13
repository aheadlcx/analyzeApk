package b.a.a.a;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class a extends SimpleItemAnimator {
    protected ArrayList<ViewHolder> a = new ArrayList();
    protected ArrayList<ViewHolder> b = new ArrayList();
    protected Interpolator c = new DecelerateInterpolator();
    private ArrayList<ViewHolder> d = new ArrayList();
    private ArrayList<ViewHolder> e = new ArrayList();
    private ArrayList<d> f = new ArrayList();
    private ArrayList<a> g = new ArrayList();
    private ArrayList<ArrayList<ViewHolder>> h = new ArrayList();
    private ArrayList<ArrayList<d>> i = new ArrayList();
    private ArrayList<ArrayList<a>> j = new ArrayList();
    private ArrayList<ViewHolder> k = new ArrayList();
    private ArrayList<ViewHolder> l = new ArrayList();

    private static class e implements ViewPropertyAnimatorListener {
        private e() {
        }

        public void onAnimationStart(View view) {
        }

        public void onAnimationEnd(View view) {
        }

        public void onAnimationCancel(View view) {
        }
    }

    private static class a {
        public ViewHolder a;
        public ViewHolder b;
        public int c;
        public int d;
        public int e;
        public int f;

        private a(ViewHolder viewHolder, ViewHolder viewHolder2) {
            this.a = viewHolder;
            this.b = viewHolder2;
        }

        private a(ViewHolder viewHolder, ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
            this(viewHolder, viewHolder2);
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.a + ", newHolder=" + this.b + ", fromX=" + this.c + ", fromY=" + this.d + ", toX=" + this.e + ", toY=" + this.f + '}';
        }
    }

    protected class b extends e {
        ViewHolder a;
        final /* synthetic */ a b;

        public b(a aVar, ViewHolder viewHolder) {
            this.b = aVar;
            super();
            this.a = viewHolder;
        }

        public void onAnimationStart(View view) {
            this.b.dispatchAddStarting(this.a);
        }

        public void onAnimationCancel(View view) {
            b.a.a.b.a.a(view);
        }

        public void onAnimationEnd(View view) {
            b.a.a.b.a.a(view);
            this.b.dispatchAddFinished(this.a);
            this.b.a.remove(this.a);
            this.b.a();
        }
    }

    protected class c extends e {
        ViewHolder a;
        final /* synthetic */ a b;

        public c(a aVar, ViewHolder viewHolder) {
            this.b = aVar;
            super();
            this.a = viewHolder;
        }

        public void onAnimationStart(View view) {
            this.b.dispatchRemoveStarting(this.a);
        }

        public void onAnimationCancel(View view) {
            b.a.a.b.a.a(view);
        }

        public void onAnimationEnd(View view) {
            b.a.a.b.a.a(view);
            this.b.dispatchRemoveFinished(this.a);
            this.b.b.remove(this.a);
            this.b.a();
        }
    }

    private static class d {
        public ViewHolder a;
        public int b;
        public int c;
        public int d;
        public int e;

        private d(ViewHolder viewHolder, int i, int i2, int i3, int i4) {
            this.a = viewHolder;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }
    }

    protected abstract void a(ViewHolder viewHolder);

    protected abstract void c(ViewHolder viewHolder);

    public a() {
        setSupportsChangeAnimations(false);
    }

    public void runPendingAnimations() {
        int i;
        int i2;
        int i3;
        int i4 = !this.d.isEmpty() ? 1 : 0;
        if (this.f.isEmpty()) {
            i = 0;
        } else {
            i = 1;
        }
        if (this.g.isEmpty()) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        if (this.e.isEmpty()) {
            i3 = 0;
        } else {
            i3 = 1;
        }
        if (i4 != 0 || i != 0 || i3 != 0 || i2 != 0) {
            final ArrayList arrayList;
            Runnable anonymousClass1;
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                i((ViewHolder) it.next());
            }
            this.d.clear();
            if (i != 0) {
                arrayList = new ArrayList();
                arrayList.addAll(this.f);
                this.i.add(arrayList);
                this.f.clear();
                anonymousClass1 = new Runnable(this) {
                    final /* synthetic */ a b;

                    public void run() {
                        if (this.b.i.remove(arrayList)) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                d dVar = (d) it.next();
                                this.b.a(dVar.a, dVar.b, dVar.c, dVar.d, dVar.e);
                            }
                            arrayList.clear();
                        }
                    }
                };
                if (i4 != 0) {
                    ViewCompat.postOnAnimationDelayed(((d) arrayList.get(0)).a.itemView, anonymousClass1, getRemoveDuration());
                } else {
                    anonymousClass1.run();
                }
            }
            if (i2 != 0) {
                arrayList = new ArrayList();
                arrayList.addAll(this.g);
                this.j.add(arrayList);
                this.g.clear();
                anonymousClass1 = new Runnable(this) {
                    final /* synthetic */ a b;

                    public void run() {
                        if (this.b.j.remove(arrayList)) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                this.b.a((a) it.next());
                            }
                            arrayList.clear();
                        }
                    }
                };
                if (i4 != 0) {
                    ViewCompat.postOnAnimationDelayed(((a) arrayList.get(0)).a.itemView, anonymousClass1, getRemoveDuration());
                } else {
                    anonymousClass1.run();
                }
            }
            if (i3 != 0) {
                final ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.e);
                this.h.add(arrayList2);
                this.e.clear();
                Runnable anonymousClass3 = new Runnable(this) {
                    final /* synthetic */ a b;

                    public void run() {
                        if (this.b.h.remove(arrayList2)) {
                            Iterator it = arrayList2.iterator();
                            while (it.hasNext()) {
                                this.b.j((ViewHolder) it.next());
                            }
                            arrayList2.clear();
                        }
                    }
                };
                if (i4 == 0 && i == 0 && i2 == 0) {
                    anonymousClass3.run();
                    return;
                }
                long moveDuration;
                long changeDuration;
                long removeDuration = i4 != 0 ? getRemoveDuration() : 0;
                if (i != 0) {
                    moveDuration = getMoveDuration();
                } else {
                    moveDuration = 0;
                }
                if (i2 != 0) {
                    changeDuration = getChangeDuration();
                } else {
                    changeDuration = 0;
                }
                ViewCompat.postOnAnimationDelayed(((ViewHolder) arrayList2.get(0)).itemView, anonymousClass3, removeDuration + Math.max(moveDuration, changeDuration));
            }
        }
    }

    protected void d(ViewHolder viewHolder) {
    }

    protected void b(ViewHolder viewHolder) {
    }

    private void g(ViewHolder viewHolder) {
        b.a.a.b.a.a(viewHolder.itemView);
        if (viewHolder instanceof b.a.a.a.a.a) {
            ((b.a.a.a.a.a) viewHolder).b(viewHolder);
        } else {
            d(viewHolder);
        }
    }

    private void h(ViewHolder viewHolder) {
        b.a.a.b.a.a(viewHolder.itemView);
        if (viewHolder instanceof b.a.a.a.a.a) {
            ((b.a.a.a.a.a) viewHolder).a(viewHolder);
        } else {
            b(viewHolder);
        }
    }

    private void i(ViewHolder viewHolder) {
        if (viewHolder instanceof b.a.a.a.a.a) {
            ((b.a.a.a.a.a) viewHolder).b(viewHolder, new c(this, viewHolder));
        } else {
            a(viewHolder);
        }
        this.b.add(viewHolder);
    }

    private void j(ViewHolder viewHolder) {
        if (viewHolder instanceof b.a.a.a.a.a) {
            ((b.a.a.a.a.a) viewHolder).a(viewHolder, new b(this, viewHolder));
        } else {
            c(viewHolder);
        }
        this.a.add(viewHolder);
    }

    public boolean animateRemove(ViewHolder viewHolder) {
        endAnimation(viewHolder);
        g(viewHolder);
        this.d.add(viewHolder);
        return true;
    }

    protected long e(ViewHolder viewHolder) {
        return Math.abs((((long) viewHolder.getOldPosition()) * getRemoveDuration()) / 4);
    }

    public boolean animateAdd(ViewHolder viewHolder) {
        endAnimation(viewHolder);
        h(viewHolder);
        this.e.add(viewHolder);
        return true;
    }

    protected long f(ViewHolder viewHolder) {
        return Math.abs((((long) viewHolder.getAdapterPosition()) * getAddDuration()) / 4);
    }

    public boolean animateMove(ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        View view = viewHolder.itemView;
        int translationX = (int) (((float) i) + ViewCompat.getTranslationX(viewHolder.itemView));
        int translationY = (int) (((float) i2) + ViewCompat.getTranslationY(viewHolder.itemView));
        endAnimation(viewHolder);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            dispatchMoveFinished(viewHolder);
            return false;
        }
        if (i5 != 0) {
            ViewCompat.setTranslationX(view, (float) (-i5));
        }
        if (i6 != 0) {
            ViewCompat.setTranslationY(view, (float) (-i6));
        }
        this.f.add(new d(viewHolder, translationX, translationY, i3, i4));
        return true;
    }

    private void a(ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        View view = viewHolder.itemView;
        final int i5 = i3 - i;
        final int i6 = i4 - i2;
        if (i5 != 0) {
            ViewCompat.animate(view).translationX(0.0f);
        }
        if (i6 != 0) {
            ViewCompat.animate(view).translationY(0.0f);
        }
        this.k.add(viewHolder);
        final ViewPropertyAnimatorCompat animate = ViewCompat.animate(view);
        final ViewHolder viewHolder2 = viewHolder;
        animate.setDuration(getMoveDuration()).setListener(new e(this) {
            final /* synthetic */ a e;

            public void onAnimationStart(View view) {
                this.e.dispatchMoveStarting(viewHolder2);
            }

            public void onAnimationCancel(View view) {
                if (i5 != 0) {
                    ViewCompat.setTranslationX(view, 0.0f);
                }
                if (i6 != 0) {
                    ViewCompat.setTranslationY(view, 0.0f);
                }
            }

            public void onAnimationEnd(View view) {
                animate.setListener(null);
                this.e.dispatchMoveFinished(viewHolder2);
                this.e.k.remove(viewHolder2);
                this.e.a();
            }
        }).start();
    }

    public boolean animateChange(ViewHolder viewHolder, ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        float translationX = ViewCompat.getTranslationX(viewHolder.itemView);
        float translationY = ViewCompat.getTranslationY(viewHolder.itemView);
        float alpha = ViewCompat.getAlpha(viewHolder.itemView);
        endAnimation(viewHolder);
        int i5 = (int) (((float) (i3 - i)) - translationX);
        int i6 = (int) (((float) (i4 - i2)) - translationY);
        ViewCompat.setTranslationX(viewHolder.itemView, translationX);
        ViewCompat.setTranslationY(viewHolder.itemView, translationY);
        ViewCompat.setAlpha(viewHolder.itemView, alpha);
        if (!(viewHolder2 == null || viewHolder2.itemView == null)) {
            endAnimation(viewHolder2);
            ViewCompat.setTranslationX(viewHolder2.itemView, (float) (-i5));
            ViewCompat.setTranslationY(viewHolder2.itemView, (float) (-i6));
            ViewCompat.setAlpha(viewHolder2.itemView, 0.0f);
        }
        this.g.add(new a(viewHolder, viewHolder2, i, i2, i3, i4));
        return true;
    }

    private void a(final a aVar) {
        View view = null;
        ViewHolder viewHolder = aVar.a;
        View view2 = viewHolder == null ? null : viewHolder.itemView;
        ViewHolder viewHolder2 = aVar.b;
        if (viewHolder2 != null) {
            view = viewHolder2.itemView;
        }
        if (view2 != null) {
            this.l.add(aVar.a);
            final ViewPropertyAnimatorCompat duration = ViewCompat.animate(view2).setDuration(getChangeDuration());
            duration.translationX((float) (aVar.e - aVar.c));
            duration.translationY((float) (aVar.f - aVar.d));
            duration.alpha(0.0f).setListener(new e(this) {
                final /* synthetic */ a c;

                public void onAnimationStart(View view) {
                    this.c.dispatchChangeStarting(aVar.a, true);
                }

                public void onAnimationEnd(View view) {
                    duration.setListener(null);
                    ViewCompat.setAlpha(view, 1.0f);
                    ViewCompat.setTranslationX(view, 0.0f);
                    ViewCompat.setTranslationY(view, 0.0f);
                    this.c.dispatchChangeFinished(aVar.a, true);
                    this.c.l.remove(aVar.a);
                    this.c.a();
                }
            }).start();
        }
        if (view != null) {
            this.l.add(aVar.b);
            duration = ViewCompat.animate(view);
            duration.translationX(0.0f).translationY(0.0f).setDuration(getChangeDuration()).alpha(1.0f).setListener(new e(this) {
                final /* synthetic */ a d;

                public void onAnimationStart(View view) {
                    this.d.dispatchChangeStarting(aVar.b, false);
                }

                public void onAnimationEnd(View view) {
                    duration.setListener(null);
                    ViewCompat.setAlpha(view, 1.0f);
                    ViewCompat.setTranslationX(view, 0.0f);
                    ViewCompat.setTranslationY(view, 0.0f);
                    this.d.dispatchChangeFinished(aVar.b, false);
                    this.d.l.remove(aVar.b);
                    this.d.a();
                }
            }).start();
        }
    }

    private void a(List<a> list, ViewHolder viewHolder) {
        for (int size = list.size() - 1; size >= 0; size--) {
            a aVar = (a) list.get(size);
            if (a(aVar, viewHolder) && aVar.a == null && aVar.b == null) {
                list.remove(aVar);
            }
        }
    }

    private void b(a aVar) {
        if (aVar.a != null) {
            a(aVar, aVar.a);
        }
        if (aVar.b != null) {
            a(aVar, aVar.b);
        }
    }

    private boolean a(a aVar, ViewHolder viewHolder) {
        boolean z = false;
        if (aVar.b == viewHolder) {
            aVar.b = null;
        } else if (aVar.a != viewHolder) {
            return false;
        } else {
            aVar.a = null;
            z = true;
        }
        ViewCompat.setAlpha(viewHolder.itemView, 1.0f);
        ViewCompat.setTranslationX(viewHolder.itemView, 0.0f);
        ViewCompat.setTranslationY(viewHolder.itemView, 0.0f);
        dispatchChangeFinished(viewHolder, z);
        return true;
    }

    public void endAnimation(ViewHolder viewHolder) {
        int size;
        View view = viewHolder.itemView;
        ViewCompat.animate(view).cancel();
        for (size = this.f.size() - 1; size >= 0; size--) {
            if (((d) this.f.get(size)).a == viewHolder) {
                ViewCompat.setTranslationY(view, 0.0f);
                ViewCompat.setTranslationX(view, 0.0f);
                dispatchMoveFinished(viewHolder);
                this.f.remove(size);
            }
        }
        a(this.g, viewHolder);
        if (this.d.remove(viewHolder)) {
            b.a.a.b.a.a(viewHolder.itemView);
            dispatchRemoveFinished(viewHolder);
        }
        if (this.e.remove(viewHolder)) {
            b.a.a.b.a.a(viewHolder.itemView);
            dispatchAddFinished(viewHolder);
        }
        for (size = this.j.size() - 1; size >= 0; size--) {
            List list = (ArrayList) this.j.get(size);
            a(list, viewHolder);
            if (list.isEmpty()) {
                this.j.remove(size);
            }
        }
        for (int size2 = this.i.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.i.get(size2);
            int size3 = arrayList.size() - 1;
            while (size3 >= 0) {
                if (((d) arrayList.get(size3)).a == viewHolder) {
                    ViewCompat.setTranslationY(view, 0.0f);
                    ViewCompat.setTranslationX(view, 0.0f);
                    dispatchMoveFinished(viewHolder);
                    arrayList.remove(size3);
                    if (arrayList.isEmpty()) {
                        this.i.remove(size2);
                    }
                } else {
                    size3--;
                }
            }
        }
        for (size = this.h.size() - 1; size >= 0; size--) {
            arrayList = (ArrayList) this.h.get(size);
            if (arrayList.remove(viewHolder)) {
                b.a.a.b.a.a(viewHolder.itemView);
                dispatchAddFinished(viewHolder);
                if (arrayList.isEmpty()) {
                    this.h.remove(size);
                }
            }
        }
        if (this.b.remove(viewHolder)) {
        }
        if (this.a.remove(viewHolder)) {
        }
        if (this.l.remove(viewHolder)) {
        }
        if (this.k.remove(viewHolder)) {
            a();
        } else {
            a();
        }
    }

    public boolean isRunning() {
        if (this.e.isEmpty() && this.g.isEmpty() && this.f.isEmpty() && this.d.isEmpty() && this.k.isEmpty() && this.b.isEmpty() && this.a.isEmpty() && this.l.isEmpty() && this.i.isEmpty() && this.h.isEmpty() && this.j.isEmpty()) {
            return false;
        }
        return true;
    }

    private void a() {
        if (!isRunning()) {
            dispatchAnimationsFinished();
        }
    }

    public void endAnimations() {
        int size;
        for (size = this.f.size() - 1; size >= 0; size--) {
            d dVar = (d) this.f.get(size);
            View view = dVar.a.itemView;
            ViewCompat.setTranslationY(view, 0.0f);
            ViewCompat.setTranslationX(view, 0.0f);
            dispatchMoveFinished(dVar.a);
            this.f.remove(size);
        }
        for (size = this.d.size() - 1; size >= 0; size--) {
            dispatchRemoveFinished((ViewHolder) this.d.get(size));
            this.d.remove(size);
        }
        for (size = this.e.size() - 1; size >= 0; size--) {
            ViewHolder viewHolder = (ViewHolder) this.e.get(size);
            b.a.a.b.a.a(viewHolder.itemView);
            dispatchAddFinished(viewHolder);
            this.e.remove(size);
        }
        for (size = this.g.size() - 1; size >= 0; size--) {
            b((a) this.g.get(size));
        }
        this.g.clear();
        if (isRunning()) {
            int size2;
            ArrayList arrayList;
            int size3;
            for (size2 = this.i.size() - 1; size2 >= 0; size2--) {
                arrayList = (ArrayList) this.i.get(size2);
                for (size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    d dVar2 = (d) arrayList.get(size3);
                    View view2 = dVar2.a.itemView;
                    ViewCompat.setTranslationY(view2, 0.0f);
                    ViewCompat.setTranslationX(view2, 0.0f);
                    dispatchMoveFinished(dVar2.a);
                    arrayList.remove(size3);
                    if (arrayList.isEmpty()) {
                        this.i.remove(arrayList);
                    }
                }
            }
            for (size2 = this.h.size() - 1; size2 >= 0; size2--) {
                arrayList = (ArrayList) this.h.get(size2);
                for (size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    ViewHolder viewHolder2 = (ViewHolder) arrayList.get(size3);
                    ViewCompat.setAlpha(viewHolder2.itemView, 1.0f);
                    dispatchAddFinished(viewHolder2);
                    if (size3 < arrayList.size()) {
                        arrayList.remove(size3);
                    }
                    if (arrayList.isEmpty()) {
                        this.h.remove(arrayList);
                    }
                }
            }
            for (size2 = this.j.size() - 1; size2 >= 0; size2--) {
                arrayList = (ArrayList) this.j.get(size2);
                for (size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    b((a) arrayList.get(size3));
                    if (arrayList.isEmpty()) {
                        this.j.remove(arrayList);
                    }
                }
            }
            a(this.b);
            a(this.k);
            a(this.a);
            a(this.l);
            dispatchAnimationsFinished();
        }
    }

    void a(List<ViewHolder> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ViewCompat.animate(((ViewHolder) list.get(size)).itemView).cancel();
        }
    }
}
