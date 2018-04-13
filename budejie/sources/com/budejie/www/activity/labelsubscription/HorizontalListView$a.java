package com.budejie.www.activity.labelsubscription;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import com.budejie.www.activity.labelsubscription.HorizontalListView.OnScrollStateChangedListener.ScrollState;

class HorizontalListView$a extends SimpleOnGestureListener {
    final /* synthetic */ HorizontalListView a;

    private HorizontalListView$a(HorizontalListView horizontalListView) {
        this.a = horizontalListView;
    }

    public boolean onDown(MotionEvent motionEvent) {
        return this.a.a(motionEvent);
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return this.a.a(motionEvent, motionEvent2, f, f2);
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        HorizontalListView.a(this.a, Boolean.valueOf(true));
        HorizontalListView.a(this.a, ScrollState.SCROLL_STATE_TOUCH_SCROLL);
        HorizontalListView.b(this.a);
        HorizontalListView horizontalListView = this.a;
        horizontalListView.d += (int) f;
        HorizontalListView.a(this.a, Math.round(f));
        this.a.requestLayout();
        return true;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        HorizontalListView.b(this.a);
        OnItemClickListener onItemClickListener = this.a.getOnItemClickListener();
        int a = HorizontalListView.a(this.a, (int) motionEvent.getX(), (int) motionEvent.getY());
        if (a >= 0 && !HorizontalListView.d(this.a)) {
            View childAt = this.a.getChildAt(a);
            int e = HorizontalListView.e(this.a) + a;
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(this.a, childAt, e, this.a.b.getItemId(e));
                return true;
            }
        }
        if (!(HorizontalListView.f(this.a) == null || HorizontalListView.d(this.a))) {
            HorizontalListView.f(this.a).onClick(this.a);
        }
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
        HorizontalListView.b(this.a);
        int a = HorizontalListView.a(this.a, (int) motionEvent.getX(), (int) motionEvent.getY());
        if (a >= 0 && !HorizontalListView.d(this.a)) {
            View childAt = this.a.getChildAt(a);
            OnItemLongClickListener onItemLongClickListener = this.a.getOnItemLongClickListener();
            if (onItemLongClickListener != null) {
                int e = HorizontalListView.e(this.a) + a;
                if (onItemLongClickListener.onItemLongClick(this.a, childAt, e, this.a.b.getItemId(e))) {
                    this.a.performHapticFeedback(0);
                }
            }
        }
    }
}
