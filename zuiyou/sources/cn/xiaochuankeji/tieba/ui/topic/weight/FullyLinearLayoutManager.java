package cn.xiaochuankeji.tieba.ui.topic.weight;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import android.view.View.MeasureSpec;
import java.lang.reflect.Field;

public class FullyLinearLayoutManager extends LinearLayoutManager {
    private static boolean a = true;
    private static Field b = null;
    private final int[] c;
    private final RecyclerView d;
    private int e;
    private boolean f;
    private int g;
    private final Rect h;

    public FullyLinearLayoutManager(Context context) {
        super(context);
        this.c = new int[2];
        this.e = 100;
        this.g = 0;
        this.h = new Rect();
        this.d = null;
    }

    public FullyLinearLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
        this.c = new int[2];
        this.e = 100;
        this.g = 0;
        this.h = new Rect();
        this.d = null;
    }

    public FullyLinearLayoutManager(RecyclerView recyclerView) {
        super(recyclerView.getContext());
        this.c = new int[2];
        this.e = 100;
        this.g = 0;
        this.h = new Rect();
        this.d = recyclerView;
        this.g = ViewCompat.getOverScrollMode(recyclerView);
    }

    public FullyLinearLayoutManager(RecyclerView recyclerView, int i, boolean z) {
        super(recyclerView.getContext(), i, z);
        this.c = new int[2];
        this.e = 100;
        this.g = 0;
        this.h = new Rect();
        this.d = recyclerView;
        this.g = ViewCompat.getOverScrollMode(recyclerView);
    }

    public static int a() {
        return MeasureSpec.makeMeasureSpec(0, 0);
    }

    public void onMeasure(Recycler recycler, State state, int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        Object obj = mode != 0 ? 1 : null;
        Object obj2 = mode2 != 0 ? 1 : null;
        Object obj3 = mode == 1073741824 ? 1 : null;
        Object obj4 = mode2 == 1073741824 ? 1 : null;
        int a = a();
        if (obj3 == null || obj4 == null) {
            boolean z;
            int i3;
            if (getOrientation() == 1) {
                z = true;
            } else {
                z = false;
            }
            a(size, size2, z);
            int i4 = 0;
            int i5 = 0;
            recycler.clear();
            int itemCount = state.getItemCount();
            int itemCount2 = getItemCount();
            mode2 = 0;
            while (mode2 < itemCount2) {
                if (!z) {
                    if (!this.f) {
                        if (mode2 < itemCount) {
                            a(recycler, mode2, a, size2, this.c);
                        } else {
                            a(mode2);
                        }
                    }
                    mode = i4 + this.c[0];
                    if (mode2 == 0) {
                        i3 = this.c[1];
                    } else {
                        i3 = i5;
                    }
                    if (obj != null && mode >= size) {
                        mode2 = i3;
                        break;
                    }
                }
                if (!this.f) {
                    if (mode2 < itemCount) {
                        a(recycler, mode2, size, a, this.c);
                    } else {
                        a(mode2);
                    }
                }
                i3 = this.c[1] + i5;
                if (mode2 == 0) {
                    mode = this.c[0];
                } else {
                    mode = i4;
                }
                if (obj2 != null && i3 >= size2) {
                    mode2 = i3;
                    break;
                }
                mode2++;
                i5 = i3;
                i4 = mode;
            }
            mode2 = i5;
            mode = i4;
            if (obj3 != null) {
                mode = size;
            } else {
                i3 = (getPaddingLeft() + getPaddingRight()) + mode;
                if (obj != null) {
                    mode = Math.min(i3, size);
                } else {
                    mode = i3;
                }
            }
            if (obj4 != null) {
                i3 = size2;
            } else {
                i3 = (getPaddingTop() + getPaddingBottom()) + mode2;
                if (obj2 != null) {
                    i3 = Math.min(i3, size2);
                }
            }
            setMeasuredDimension(mode, i3);
            if (this.d != null && this.g == 1) {
                Object obj5 = ((!z || (obj2 != null && i3 >= size2)) && (z || (obj != null && mode >= size))) ? null : 1;
                View view = this.d;
                if (obj5 != null) {
                    i3 = 2;
                } else {
                    i3 = 0;
                }
                ViewCompat.setOverScrollMode(view, i3);
                return;
            }
            return;
        }
        super.onMeasure(recycler, state, i, i2);
    }

    private void a(int i) {
    }

    private void a(int i, int i2, boolean z) {
        if (this.c[0] != 0 || this.c[1] != 0) {
            return;
        }
        if (z) {
            this.c[0] = i;
            this.c[1] = this.e;
            return;
        }
        this.c[0] = this.e;
        this.c[1] = i2;
    }

    public void setOrientation(int i) {
        if (!(this.c == null || getOrientation() == i)) {
            this.c[0] = 0;
            this.c[1] = 0;
        }
        super.setOrientation(i);
    }

    private void a(Recycler recycler, int i, int i2, int i3, int[] iArr) {
        try {
            View viewForPosition = recycler.getViewForPosition(i);
            LayoutParams layoutParams = (LayoutParams) viewForPosition.getLayoutParams();
            int paddingLeft = getPaddingLeft() + getPaddingRight();
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int i4 = layoutParams.leftMargin + layoutParams.rightMargin;
            int i5 = layoutParams.topMargin + layoutParams.bottomMargin;
            a(layoutParams);
            calculateItemDecorationsForChild(viewForPosition, this.h);
            viewForPosition.measure(LayoutManager.getChildMeasureSpec(i2, (paddingLeft + i4) + (getRightDecorationWidth(viewForPosition) + getLeftDecorationWidth(viewForPosition)), layoutParams.width, canScrollHorizontally()), LayoutManager.getChildMeasureSpec(i3, (paddingTop + i5) + (getTopDecorationHeight(viewForPosition) + getBottomDecorationHeight(viewForPosition)), layoutParams.height, canScrollVertically()));
            iArr[0] = (getDecoratedMeasuredWidth(viewForPosition) + layoutParams.leftMargin) + layoutParams.rightMargin;
            iArr[1] = (getDecoratedMeasuredHeight(viewForPosition) + layoutParams.bottomMargin) + layoutParams.topMargin;
            a(layoutParams);
            recycler.recycleView(viewForPosition);
        } catch (IndexOutOfBoundsException e) {
        }
    }

    private static void a(LayoutParams layoutParams) {
        if (a) {
            try {
                if (b == null) {
                    b = LayoutParams.class.getDeclaredField("mInsetsDirty");
                    b.setAccessible(true);
                }
                b.set(layoutParams, Boolean.valueOf(true));
            } catch (NoSuchFieldException e) {
                b();
            } catch (IllegalAccessException e2) {
                b();
            }
        }
    }

    private static void b() {
        a = false;
    }
}
