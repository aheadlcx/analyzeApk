package android.support.v7.widget;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultItemAnimator extends SimpleItemAnimator {
    private static TimeInterpolator i;
    ArrayList<ArrayList<ViewHolder>> a = new ArrayList();
    ArrayList<ArrayList<b>> b = new ArrayList();
    ArrayList<ArrayList<a>> c = new ArrayList();
    ArrayList<ViewHolder> d = new ArrayList();
    ArrayList<ViewHolder> e = new ArrayList();
    ArrayList<ViewHolder> f = new ArrayList();
    ArrayList<ViewHolder> g = new ArrayList();
    private ArrayList<ViewHolder> j = new ArrayList();
    private ArrayList<ViewHolder> k = new ArrayList();
    private ArrayList<b> l = new ArrayList();
    private ArrayList<a> m = new ArrayList();

    private static class a {
        public int fromX;
        public int fromY;
        public ViewHolder newHolder;
        public ViewHolder oldHolder;
        public int toX;
        public int toY;

        private a(ViewHolder viewHolder, ViewHolder viewHolder2) {
            this.oldHolder = viewHolder;
            this.newHolder = viewHolder2;
        }

        a(ViewHolder viewHolder, ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
            this(viewHolder, viewHolder2);
            this.fromX = i;
            this.fromY = i2;
            this.toX = i3;
            this.toY = i4;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.oldHolder + ", newHolder=" + this.newHolder + ", fromX=" + this.fromX + ", fromY=" + this.fromY + ", toX=" + this.toX + ", toY=" + this.toY + '}';
        }
    }

    private static class b {
        public int fromX;
        public int fromY;
        public ViewHolder holder;
        public int toX;
        public int toY;

        b(ViewHolder viewHolder, int i, int i2, int i3, int i4) {
            this.holder = viewHolder;
            this.fromX = i;
            this.fromY = i2;
            this.toX = i3;
            this.toY = i4;
        }
    }

    public void runPendingAnimations() {
        int i;
        int i2;
        int i3;
        int i4 = !this.j.isEmpty() ? 1 : 0;
        if (this.l.isEmpty()) {
            i = 0;
        } else {
            i = 1;
        }
        if (this.m.isEmpty()) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        if (this.k.isEmpty()) {
            i3 = 0;
        } else {
            i3 = 1;
        }
        if (i4 != 0 || i != 0 || i3 != 0 || i2 != 0) {
            ArrayList arrayList;
            Runnable acVar;
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                c((ViewHolder) it.next());
            }
            this.j.clear();
            if (i != 0) {
                arrayList = new ArrayList();
                arrayList.addAll(this.l);
                this.b.add(arrayList);
                this.l.clear();
                acVar = new ac(this, arrayList);
                if (i4 != 0) {
                    ViewCompat.postOnAnimationDelayed(((b) arrayList.get(0)).holder.itemView, acVar, getRemoveDuration());
                } else {
                    acVar.run();
                }
            }
            if (i2 != 0) {
                arrayList = new ArrayList();
                arrayList.addAll(this.m);
                this.c.add(arrayList);
                this.m.clear();
                acVar = new ad(this, arrayList);
                if (i4 != 0) {
                    ViewCompat.postOnAnimationDelayed(((a) arrayList.get(0)).oldHolder.itemView, acVar, getRemoveDuration());
                } else {
                    acVar.run();
                }
            }
            if (i3 != 0) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.k);
                this.a.add(arrayList2);
                this.k.clear();
                Runnable aeVar = new ae(this, arrayList2);
                if (i4 == 0 && i == 0 && i2 == 0) {
                    aeVar.run();
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
                ViewCompat.postOnAnimationDelayed(((ViewHolder) arrayList2.get(0)).itemView, aeVar, removeDuration + Math.max(moveDuration, changeDuration));
            }
        }
    }

    public boolean animateRemove(ViewHolder viewHolder) {
        d(viewHolder);
        this.j.add(viewHolder);
        return true;
    }

    private void c(ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        ViewPropertyAnimator animate = view.animate();
        this.f.add(viewHolder);
        animate.setDuration(getRemoveDuration()).alpha(0.0f).setListener(new af(this, viewHolder, animate, view)).start();
    }

    public boolean animateAdd(ViewHolder viewHolder) {
        d(viewHolder);
        viewHolder.itemView.setAlpha(0.0f);
        this.k.add(viewHolder);
        return true;
    }

    void a(ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        ViewPropertyAnimator animate = view.animate();
        this.d.add(viewHolder);
        animate.alpha(1.0f).setDuration(getAddDuration()).setListener(new ag(this, viewHolder, view, animate)).start();
    }

    public boolean animateMove(ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        View view = viewHolder.itemView;
        int translationX = i + ((int) viewHolder.itemView.getTranslationX());
        int translationY = i2 + ((int) viewHolder.itemView.getTranslationY());
        d(viewHolder);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            dispatchMoveFinished(viewHolder);
            return false;
        }
        if (i5 != 0) {
            view.setTranslationX((float) (-i5));
        }
        if (i6 != 0) {
            view.setTranslationY((float) (-i6));
        }
        this.l.add(new b(viewHolder, translationX, translationY, i3, i4));
        return true;
    }

    void a(ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        View view = viewHolder.itemView;
        int i5 = i3 - i;
        int i6 = i4 - i2;
        if (i5 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i6 != 0) {
            view.animate().translationY(0.0f);
        }
        ViewPropertyAnimator animate = view.animate();
        this.e.add(viewHolder);
        animate.setDuration(getMoveDuration()).setListener(new ah(this, viewHolder, i5, view, i6, animate)).start();
    }

    public boolean animateChange(ViewHolder viewHolder, ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        if (viewHolder == viewHolder2) {
            return animateMove(viewHolder, i, i2, i3, i4);
        }
        float translationX = viewHolder.itemView.getTranslationX();
        float translationY = viewHolder.itemView.getTranslationY();
        float alpha = viewHolder.itemView.getAlpha();
        d(viewHolder);
        int i5 = (int) (((float) (i3 - i)) - translationX);
        int i6 = (int) (((float) (i4 - i2)) - translationY);
        viewHolder.itemView.setTranslationX(translationX);
        viewHolder.itemView.setTranslationY(translationY);
        viewHolder.itemView.setAlpha(alpha);
        if (viewHolder2 != null) {
            d(viewHolder2);
            viewHolder2.itemView.setTranslationX((float) (-i5));
            viewHolder2.itemView.setTranslationY((float) (-i6));
            viewHolder2.itemView.setAlpha(0.0f);
        }
        this.m.add(new a(viewHolder, viewHolder2, i, i2, i3, i4));
        return true;
    }

    void a(a aVar) {
        View view = null;
        ViewHolder viewHolder = aVar.oldHolder;
        View view2 = viewHolder == null ? null : viewHolder.itemView;
        ViewHolder viewHolder2 = aVar.newHolder;
        if (viewHolder2 != null) {
            view = viewHolder2.itemView;
        }
        if (view2 != null) {
            ViewPropertyAnimator duration = view2.animate().setDuration(getChangeDuration());
            this.g.add(aVar.oldHolder);
            duration.translationX((float) (aVar.toX - aVar.fromX));
            duration.translationY((float) (aVar.toY - aVar.fromY));
            duration.alpha(0.0f).setListener(new ai(this, aVar, duration, view2)).start();
        }
        if (view != null) {
            ViewPropertyAnimator animate = view.animate();
            this.g.add(aVar.newHolder);
            animate.translationX(0.0f).translationY(0.0f).setDuration(getChangeDuration()).alpha(1.0f).setListener(new aj(this, aVar, animate, view)).start();
        }
    }

    private void a(List<a> list, ViewHolder viewHolder) {
        for (int size = list.size() - 1; size >= 0; size--) {
            a aVar = (a) list.get(size);
            if (a(aVar, viewHolder) && aVar.oldHolder == null && aVar.newHolder == null) {
                list.remove(aVar);
            }
        }
    }

    private void b(a aVar) {
        if (aVar.oldHolder != null) {
            a(aVar, aVar.oldHolder);
        }
        if (aVar.newHolder != null) {
            a(aVar, aVar.newHolder);
        }
    }

    private boolean a(a aVar, ViewHolder viewHolder) {
        boolean z = false;
        if (aVar.newHolder == viewHolder) {
            aVar.newHolder = null;
        } else if (aVar.oldHolder != viewHolder) {
            return false;
        } else {
            aVar.oldHolder = null;
            z = true;
        }
        viewHolder.itemView.setAlpha(1.0f);
        viewHolder.itemView.setTranslationX(0.0f);
        viewHolder.itemView.setTranslationY(0.0f);
        dispatchChangeFinished(viewHolder, z);
        return true;
    }

    public void endAnimation(ViewHolder viewHolder) {
        int size;
        View view = viewHolder.itemView;
        view.animate().cancel();
        for (size = this.l.size() - 1; size >= 0; size--) {
            if (((b) this.l.get(size)).holder == viewHolder) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                dispatchMoveFinished(viewHolder);
                this.l.remove(size);
            }
        }
        a(this.m, viewHolder);
        if (this.j.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchRemoveFinished(viewHolder);
        }
        if (this.k.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAddFinished(viewHolder);
        }
        for (size = this.c.size() - 1; size >= 0; size--) {
            List list = (ArrayList) this.c.get(size);
            a(list, viewHolder);
            if (list.isEmpty()) {
                this.c.remove(size);
            }
        }
        for (int size2 = this.b.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.b.get(size2);
            int size3 = arrayList.size() - 1;
            while (size3 >= 0) {
                if (((b) arrayList.get(size3)).holder == viewHolder) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    dispatchMoveFinished(viewHolder);
                    arrayList.remove(size3);
                    if (arrayList.isEmpty()) {
                        this.b.remove(size2);
                    }
                } else {
                    size3--;
                }
            }
        }
        for (size = this.a.size() - 1; size >= 0; size--) {
            arrayList = (ArrayList) this.a.get(size);
            if (arrayList.remove(viewHolder)) {
                view.setAlpha(1.0f);
                dispatchAddFinished(viewHolder);
                if (arrayList.isEmpty()) {
                    this.a.remove(size);
                }
            }
        }
        if (this.f.remove(viewHolder)) {
        }
        if (this.d.remove(viewHolder)) {
        }
        if (this.g.remove(viewHolder)) {
        }
        if (this.e.remove(viewHolder)) {
            a();
        } else {
            a();
        }
    }

    private void d(ViewHolder viewHolder) {
        if (i == null) {
            i = new ValueAnimator().getInterpolator();
        }
        viewHolder.itemView.animate().setInterpolator(i);
        endAnimation(viewHolder);
    }

    public boolean isRunning() {
        return (this.k.isEmpty() && this.m.isEmpty() && this.l.isEmpty() && this.j.isEmpty() && this.e.isEmpty() && this.f.isEmpty() && this.d.isEmpty() && this.g.isEmpty() && this.b.isEmpty() && this.a.isEmpty() && this.c.isEmpty()) ? false : true;
    }

    void a() {
        if (!isRunning()) {
            dispatchAnimationsFinished();
        }
    }

    public void endAnimations() {
        int size;
        for (size = this.l.size() - 1; size >= 0; size--) {
            b bVar = (b) this.l.get(size);
            View view = bVar.holder.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            dispatchMoveFinished(bVar.holder);
            this.l.remove(size);
        }
        for (size = this.j.size() - 1; size >= 0; size--) {
            dispatchRemoveFinished((ViewHolder) this.j.get(size));
            this.j.remove(size);
        }
        for (size = this.k.size() - 1; size >= 0; size--) {
            ViewHolder viewHolder = (ViewHolder) this.k.get(size);
            viewHolder.itemView.setAlpha(1.0f);
            dispatchAddFinished(viewHolder);
            this.k.remove(size);
        }
        for (size = this.m.size() - 1; size >= 0; size--) {
            b((a) this.m.get(size));
        }
        this.m.clear();
        if (isRunning()) {
            int size2;
            ArrayList arrayList;
            int size3;
            for (size2 = this.b.size() - 1; size2 >= 0; size2--) {
                arrayList = (ArrayList) this.b.get(size2);
                for (size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    b bVar2 = (b) arrayList.get(size3);
                    View view2 = bVar2.holder.itemView;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    dispatchMoveFinished(bVar2.holder);
                    arrayList.remove(size3);
                    if (arrayList.isEmpty()) {
                        this.b.remove(arrayList);
                    }
                }
            }
            for (size2 = this.a.size() - 1; size2 >= 0; size2--) {
                arrayList = (ArrayList) this.a.get(size2);
                for (size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    ViewHolder viewHolder2 = (ViewHolder) arrayList.get(size3);
                    viewHolder2.itemView.setAlpha(1.0f);
                    dispatchAddFinished(viewHolder2);
                    arrayList.remove(size3);
                    if (arrayList.isEmpty()) {
                        this.a.remove(arrayList);
                    }
                }
            }
            for (size2 = this.c.size() - 1; size2 >= 0; size2--) {
                arrayList = (ArrayList) this.c.get(size2);
                for (size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    b((a) arrayList.get(size3));
                    if (arrayList.isEmpty()) {
                        this.c.remove(arrayList);
                    }
                }
            }
            a(this.f);
            a(this.e);
            a(this.d);
            a(this.g);
            dispatchAnimationsFinished();
        }
    }

    void a(List<ViewHolder> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ((ViewHolder) list.get(size)).itemView.animate().cancel();
        }
    }

    public boolean canReuseUpdatedViewHolder(@NonNull ViewHolder viewHolder, @NonNull List<Object> list) {
        return !list.isEmpty() || super.canReuseUpdatedViewHolder(viewHolder, list);
    }
}
