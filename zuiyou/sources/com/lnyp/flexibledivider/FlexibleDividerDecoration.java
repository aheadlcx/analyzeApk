package com.lnyp.flexibledivider;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public abstract class FlexibleDividerDecoration extends ItemDecoration {
    private static final int[] i = new int[]{16843284};
    protected DividerType a = DividerType.DRAWABLE;
    protected f b;
    protected d c;
    protected b d;
    protected c e;
    protected e f;
    protected boolean g;
    protected boolean h;
    private Paint j;

    public interface c {
        Drawable a(int i, RecyclerView recyclerView);
    }

    public interface e {
        int a(int i, RecyclerView recyclerView);
    }

    protected enum DividerType {
        DRAWABLE,
        PAINT,
        COLOR
    }

    public interface f {
        boolean a(int i, RecyclerView recyclerView);
    }

    public interface b {
        int a(int i, RecyclerView recyclerView);
    }

    public static class a<T extends a> {
        protected Resources a;
        private Context b;
        private d c;
        private b d;
        private c e;
        private e f;
        private f g = new f(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public boolean a(int i, RecyclerView recyclerView) {
                return false;
            }
        };
        private boolean h = false;
        private boolean i = false;

        public a(Context context) {
            this.b = context;
            this.a = context.getResources();
        }

        public T a(final int i) {
            return a(new b(this) {
                final /* synthetic */ a b;

                public int a(int i, RecyclerView recyclerView) {
                    return i;
                }
            });
        }

        public T a(b bVar) {
            this.d = bVar;
            return this;
        }

        public T b(final int i) {
            return a(new e(this) {
                final /* synthetic */ a b;

                public int a(int i, RecyclerView recyclerView) {
                    return i;
                }
            });
        }

        public T a(e eVar) {
            this.f = eVar;
            return this;
        }

        protected void a() {
            if (this.c == null) {
                return;
            }
            if (this.d != null) {
                throw new IllegalArgumentException("Use setColor method of Paint class to specify line color. Do not provider ColorProvider if you set PaintProvider.");
            } else if (this.f != null) {
                throw new IllegalArgumentException("Use setStrokeWidth method of Paint class to specify line size. Do not provider SizeProvider if you set PaintProvider.");
            }
        }
    }

    public interface d {
        Paint a(int i, RecyclerView recyclerView);
    }

    protected abstract Rect a(int i, RecyclerView recyclerView, View view);

    protected abstract void a(Rect rect, int i, RecyclerView recyclerView);

    protected FlexibleDividerDecoration(a aVar) {
        if (aVar.c != null) {
            this.a = DividerType.PAINT;
            this.c = aVar.c;
        } else if (aVar.d != null) {
            this.a = DividerType.COLOR;
            this.d = aVar.d;
            this.j = new Paint();
            a(aVar);
        } else {
            this.a = DividerType.DRAWABLE;
            if (aVar.e == null) {
                TypedArray obtainStyledAttributes = aVar.b.obtainStyledAttributes(i);
                final Drawable drawable = obtainStyledAttributes.getDrawable(0);
                obtainStyledAttributes.recycle();
                this.e = new c(this) {
                    final /* synthetic */ FlexibleDividerDecoration b;

                    public Drawable a(int i, RecyclerView recyclerView) {
                        return drawable;
                    }
                };
            } else {
                this.e = aVar.e;
            }
            this.f = aVar.f;
        }
        this.b = aVar.g;
        this.g = aVar.h;
        this.h = aVar.i;
    }

    private void a(a aVar) {
        this.f = aVar.f;
        if (this.f == null) {
            this.f = new e(this) {
                final /* synthetic */ FlexibleDividerDecoration a;

                {
                    this.a = r1;
                }

                public int a(int i, RecyclerView recyclerView) {
                    return 2;
                }
            };
        }
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, State state) {
        Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            int itemCount = adapter.getItemCount();
            int b = b(recyclerView);
            int childCount = recyclerView.getChildCount();
            int i = -1;
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = recyclerView.getChildAt(i2);
                int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
                if (childAdapterPosition >= i) {
                    if (!this.g && childAdapterPosition >= itemCount - b) {
                        i = childAdapterPosition;
                    } else if (a(childAdapterPosition, recyclerView)) {
                        i = childAdapterPosition;
                    } else {
                        i = b(childAdapterPosition, recyclerView);
                        if (this.b.a(i, recyclerView)) {
                            i = childAdapterPosition;
                        } else {
                            Rect a = a(i, recyclerView, childAt);
                            switch (this.a) {
                                case DRAWABLE:
                                    Drawable a2 = this.e.a(i, recyclerView);
                                    a2.setBounds(a);
                                    a2.draw(canvas);
                                    i = childAdapterPosition;
                                    continue;
                                case PAINT:
                                    this.j = this.c.a(i, recyclerView);
                                    canvas.drawLine((float) a.left, (float) a.top, (float) a.right, (float) a.bottom, this.j);
                                    i = childAdapterPosition;
                                    continue;
                                case COLOR:
                                    this.j.setColor(this.d.a(i, recyclerView));
                                    this.j.setStrokeWidth((float) this.f.a(i, recyclerView));
                                    canvas.drawLine((float) a.left, (float) a.top, (float) a.right, (float) a.bottom, this.j);
                                    break;
                            }
                            i = childAdapterPosition;
                        }
                    }
                }
            }
        }
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int itemCount = recyclerView.getAdapter().getItemCount();
        int b = b(recyclerView);
        if (this.g || childAdapterPosition < itemCount - b) {
            childAdapterPosition = b(childAdapterPosition, recyclerView);
            if (!this.b.a(childAdapterPosition, recyclerView)) {
                a(rect, childAdapterPosition, recyclerView);
            }
        }
    }

    protected boolean a(RecyclerView recyclerView) {
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).getReverseLayout();
        }
        return false;
    }

    private int b(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            int spanCount = gridLayoutManager.getSpanCount();
            int itemCount = recyclerView.getAdapter().getItemCount();
            for (int i = itemCount - 1; i >= 0; i--) {
                if (spanSizeLookup.getSpanIndex(i, spanCount) == 0) {
                    return itemCount - i;
                }
            }
        }
        return 1;
    }

    private boolean a(int i, RecyclerView recyclerView) {
        if (!(recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
            return false;
        }
        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        if (gridLayoutManager.getSpanSizeLookup().getSpanIndex(i, gridLayoutManager.getSpanCount()) > 0) {
            return true;
        }
        return false;
    }

    private int b(int i, RecyclerView recyclerView) {
        if (!(recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
            return i;
        }
        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        return gridLayoutManager.getSpanSizeLookup().getSpanGroupIndex(i, gridLayoutManager.getSpanCount());
    }
}
