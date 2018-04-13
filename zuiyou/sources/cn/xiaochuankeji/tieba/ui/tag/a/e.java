package cn.xiaochuankeji.tieba.ui.tag.a;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class e extends ItemDecoration {
    int a;
    private int b;
    private int c;
    private int d;

    public e(int i, int i2, int i3, int i4) {
        this.c = i;
        this.d = i2;
        this.b = i3;
        this.a = i4;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int itemCount = recyclerView.getAdapter().getItemCount();
        if (layoutManager == null) {
            return;
        }
        if (layoutManager instanceof GridLayoutManager) {
            a(((GridLayoutManager) layoutManager).getOrientation(), ((GridLayoutManager) layoutManager).getSpanCount(), rect, childAdapterPosition, itemCount);
        } else if (layoutManager instanceof LinearLayoutManager) {
            a(((LinearLayoutManager) layoutManager).getOrientation(), rect, childAdapterPosition, itemCount);
        }
    }

    private void a(int i, int i2, Rect rect, int i3, int i4) {
        float f;
        float f2;
        float f3 = 0.0f;
        float f4 = ((float) ((this.c * (i2 - 1)) + (this.d * 2))) / ((float) i2);
        int i5 = i3 % i2;
        int i6 = i3 / i2;
        if (i == 1) {
            f = (float) this.c;
            if (this.d == 0) {
                f2 = (((float) i5) * f4) / ((float) (i2 - 1));
                f4 -= f2;
                if (i4 / i2 == i6) {
                    f = 0.0f;
                }
            } else {
                if (i3 < i2) {
                    f3 = (float) this.b;
                } else if (i4 / i2 == i6) {
                    f = (float) this.a;
                }
                f2 = ((((float) i5) * ((f4 - ((float) this.d)) - ((float) this.d))) / ((float) (i2 - 1))) + ((float) this.d);
                f4 -= f2;
            }
        } else {
            f = (float) this.c;
            float f5;
            float f6;
            if (this.d == 0) {
                f2 = (((float) i5) * f4) / ((float) (i2 - 1));
                f4 -= f2;
                if (i4 / i2 == i6) {
                    f = f4;
                    f4 = 0.0f;
                    f5 = f2;
                    f2 = 0.0f;
                    f3 = f5;
                } else {
                    f5 = f4;
                    f4 = f;
                    f = f5;
                    f6 = f2;
                    f2 = 0.0f;
                    f3 = f6;
                }
            } else {
                if (i3 < i2) {
                    f3 = (float) this.d;
                } else if (i4 / i2 == i6) {
                    f = (float) this.d;
                }
                f2 = ((((float) i5) * ((f4 - ((float) this.d)) - ((float) this.d))) / ((float) (i2 - 1))) + ((float) this.d);
                f5 = f4 - f2;
                f4 = f;
                f = f5;
                f6 = f2;
                f2 = f3;
                f3 = f6;
            }
        }
        rect.set((int) f2, (int) f3, (int) f4, (int) f);
    }

    private void a(int i, Rect rect, int i2, int i3) {
        if (i == 0) {
            if (i2 == 0) {
                rect.set(this.d, 0, this.c, 0);
            } else if (i2 == i3 - 1) {
                rect.set(0, 0, this.d, 0);
            } else {
                rect.set(0, 0, this.c, 0);
            }
        } else if (i2 == 0) {
            rect.set(0, this.d, 0, this.c);
        } else if (i2 == i3 - 1) {
            rect.set(0, 0, 0, this.d);
        } else {
            rect.set(0, 0, 0, this.c);
        }
    }
}
