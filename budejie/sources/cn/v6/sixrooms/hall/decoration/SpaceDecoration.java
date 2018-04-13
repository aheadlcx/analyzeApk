package cn.v6.sixrooms.hall.decoration;

import android.graphics.Rect;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager.LayoutParams;
import android.view.View;

public class SpaceDecoration extends ItemDecoration {
    private int a;
    private boolean b = true;
    private boolean c = true;
    private boolean d = false;

    public int getHalfSpace() {
        return this.a;
    }

    public void setHalfSpace(int i) {
        this.a = i;
    }

    public SpaceDecoration(int i) {
        this.a = i / 2;
    }

    public void setPaddingEdgeSide(boolean z) {
        this.b = z;
    }

    public void setPaddingStart(boolean z) {
        this.c = z;
    }

    public void setPaddingHeaderFooter(boolean z) {
        this.d = z;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        int orientation;
        int spanCount;
        int i = 0;
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        int i2;
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
            i = ((LayoutParams) view.getLayoutParams()).getSpanIndex();
            i2 = orientation;
            orientation = spanCount;
            spanCount = i2;
        } else if (layoutManager instanceof GridLayoutManager) {
            orientation = ((GridLayoutManager) layoutManager).getOrientation();
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            i = ((GridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();
            i2 = orientation;
            orientation = spanCount;
            spanCount = i2;
        } else if (layoutManager instanceof LinearLayoutManager) {
            spanCount = ((LinearLayoutManager) layoutManager).getOrientation();
            orientation = 1;
        } else {
            spanCount = 0;
            orientation = 0;
        }
        if (childAdapterPosition >= 0 && childAdapterPosition < recyclerView.getAdapter().getItemCount() + 0) {
            Object obj;
            if (i == 0 && orientation > 1) {
                obj = GravityCompat.START;
            } else if (i == orientation - 1 && orientation > 1) {
                obj = GravityCompat.END;
            } else if (orientation == 1) {
                obj = 7;
            } else {
                obj = 17;
            }
            if (spanCount == 1) {
                switch (obj) {
                    case 7:
                        if (this.b) {
                            rect.left = this.a * 2;
                            rect.right = this.a * 2;
                            break;
                        }
                        break;
                    case 17:
                        rect.left = this.a;
                        rect.right = this.a;
                        break;
                    case GravityCompat.START /*8388611*/:
                        if (this.b) {
                            rect.left = this.a * 2;
                        }
                        rect.right = this.a;
                        break;
                    case GravityCompat.END /*8388613*/:
                        rect.left = this.a;
                        if (this.b) {
                            rect.right = this.a * 2;
                            break;
                        }
                        break;
                }
                if (childAdapterPosition + 0 < orientation && this.c) {
                    rect.top = this.a * 2;
                }
                rect.bottom = this.a * 2;
                return;
            }
            switch (obj) {
                case 7:
                    if (this.b) {
                        rect.left = this.a * 2;
                        rect.right = this.a * 2;
                        break;
                    }
                    break;
                case 17:
                    rect.bottom = this.a;
                    rect.top = this.a;
                    break;
                case GravityCompat.START /*8388611*/:
                    if (this.b) {
                        rect.bottom = this.a * 2;
                    }
                    rect.top = this.a;
                    break;
                case GravityCompat.END /*8388613*/:
                    rect.bottom = this.a;
                    if (this.b) {
                        rect.top = this.a * 2;
                        break;
                    }
                    break;
            }
            if (childAdapterPosition + 0 < orientation && this.c) {
                rect.left = this.a * 2;
            }
            rect.right = this.a * 2;
        } else if (!this.d) {
        } else {
            if (spanCount == 1) {
                if (this.b) {
                    rect.left = this.a * 2;
                    rect.right = this.a * 2;
                }
                rect.top = this.a * 2;
                return;
            }
            if (this.b) {
                rect.top = this.a * 2;
                rect.bottom = this.a * 2;
            }
            rect.left = this.a * 2;
        }
    }
}
