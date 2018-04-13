package in.srain.cube.views.ptr;

import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.AbsListView;

public abstract class PtrDefaultHandler implements PtrHandler {
    public static boolean canChildScrollUp(View view) {
        if (VERSION.SDK_INT >= 14) {
            return view.canScrollVertically(-1);
        }
        if (view instanceof AbsListView) {
            AbsListView absListView = (AbsListView) view;
            if (absListView.getChildCount() <= 0 || (absListView.getFirstVisiblePosition() <= 0 && absListView.getChildAt(0).getTop() >= absListView.getPaddingTop())) {
                return false;
            }
            return true;
        } else if (view.getScrollY() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean canChildScrollDown(View view) {
        if (!(view instanceof AbsListView)) {
            return ViewCompat.canScrollVertically(view, 1);
        }
        AbsListView absListView = (AbsListView) view;
        int count = absListView.getCount();
        int childCount = absListView.getChildCount();
        int firstVisiblePosition = absListView.getFirstVisiblePosition();
        if (childCount <= 0 || (firstVisiblePosition + childCount >= count && absListView.getChildAt(childCount - 1).getBottom() <= absListView.getBottom() - absListView.getPaddingBottom())) {
            return false;
        }
        return true;
    }

    public static boolean checkContentCanBePulledDown(PtrFrameLayout ptrFrameLayout, View view, View view2) {
        return !canChildScrollUp(view);
    }

    public static boolean checkContentCanBePulledUp(PtrFrameLayout ptrFrameLayout, View view, View view2) {
        return !canChildScrollDown(view);
    }

    public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
        return checkContentCanBePulledDown(ptrFrameLayout, view, view2);
    }
}
