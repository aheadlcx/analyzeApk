package android.support.v7.widget.helper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.MotionEvent;

class b implements OnItemTouchListener {
    final /* synthetic */ ItemTouchHelper a;

    b(ItemTouchHelper itemTouchHelper) {
        this.a = itemTouchHelper;
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.a.u.onTouchEvent(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.a.k = motionEvent.getPointerId(0);
            this.a.c = motionEvent.getX();
            this.a.d = motionEvent.getY();
            this.a.c();
            if (this.a.b == null) {
                b b = this.a.b(motionEvent);
                if (b != null) {
                    ItemTouchHelper itemTouchHelper = this.a;
                    itemTouchHelper.c -= b.k;
                    itemTouchHelper = this.a;
                    itemTouchHelper.d -= b.l;
                    this.a.a(b.h, true);
                    if (this.a.a.remove(b.h.itemView)) {
                        this.a.l.clearView(this.a.p, b.h);
                    }
                    this.a.a(b.h, b.i);
                    this.a.a(motionEvent, this.a.n, 0);
                }
            }
        } else if (actionMasked == 3 || actionMasked == 1) {
            this.a.k = -1;
            this.a.a(null, 0);
        } else if (this.a.k != -1) {
            int findPointerIndex = motionEvent.findPointerIndex(this.a.k);
            if (findPointerIndex >= 0) {
                this.a.a(actionMasked, motionEvent, findPointerIndex);
            }
        }
        if (this.a.r != null) {
            this.a.r.addMovement(motionEvent);
        }
        if (this.a.b != null) {
            return true;
        }
        return false;
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        int i = 0;
        this.a.u.onTouchEvent(motionEvent);
        if (this.a.r != null) {
            this.a.r.addMovement(motionEvent);
        }
        if (this.a.k != -1) {
            int actionMasked = motionEvent.getActionMasked();
            int findPointerIndex = motionEvent.findPointerIndex(this.a.k);
            if (findPointerIndex >= 0) {
                this.a.a(actionMasked, motionEvent, findPointerIndex);
            }
            ViewHolder viewHolder = this.a.b;
            if (viewHolder != null) {
                switch (actionMasked) {
                    case 1:
                        break;
                    case 2:
                        if (findPointerIndex >= 0) {
                            this.a.a(motionEvent, this.a.n, findPointerIndex);
                            this.a.a(viewHolder);
                            this.a.p.removeCallbacks(this.a.q);
                            this.a.q.run();
                            this.a.p.invalidate();
                            return;
                        }
                        return;
                    case 3:
                        if (this.a.r != null) {
                            this.a.r.clear();
                            break;
                        }
                        break;
                    case 6:
                        actionMasked = motionEvent.getActionIndex();
                        if (motionEvent.getPointerId(actionMasked) == this.a.k) {
                            if (actionMasked == 0) {
                                i = 1;
                            }
                            this.a.k = motionEvent.getPointerId(i);
                            this.a.a(motionEvent, this.a.n, actionMasked);
                            return;
                        }
                        return;
                    default:
                        return;
                }
                this.a.a(null, 0);
                this.a.k = -1;
            }
        }
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            this.a.a(null, 0);
        }
    }
}
