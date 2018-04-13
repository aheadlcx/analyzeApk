package com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public abstract class FlexibleDividerDecoration extends ItemDecoration {
    private static final int[] h = new int[]{16843284};
    protected DividerType a = DividerType.DRAWABLE;
    protected f b;
    protected d c;
    protected b d;
    protected c e;
    protected e f;
    protected boolean g;
    private Paint i;

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
            this.i = new Paint();
            a(aVar);
        } else {
            this.a = DividerType.DRAWABLE;
            if (aVar.e == null) {
                TypedArray obtainStyledAttributes = aVar.b.obtainStyledAttributes(h);
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

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
        int childCount;
        if (this.g) {
            childCount = recyclerView.getChildCount();
        } else {
            childCount = recyclerView.getChildCount() - 1;
        }
        int i = -1;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
            if (childAdapterPosition >= i) {
                if (ViewCompat.getAlpha(childAt) < 1.0f) {
                    i = childAdapterPosition;
                } else if (this.b.a(childAdapterPosition, recyclerView)) {
                    i = childAdapterPosition;
                } else {
                    Rect a = a(childAdapterPosition, recyclerView, childAt);
                    switch (this.a) {
                        case DRAWABLE:
                            Drawable a2 = this.e.a(childAdapterPosition, recyclerView);
                            a2.setBounds(a);
                            a2.draw(canvas);
                            i = childAdapterPosition;
                            continue;
                        case PAINT:
                            this.i = this.c.a(childAdapterPosition, recyclerView);
                            canvas.drawLine((float) a.left, (float) a.top, (float) a.right, (float) a.bottom, this.i);
                            i = childAdapterPosition;
                            continue;
                        case COLOR:
                            this.i.setColor(this.d.a(childAdapterPosition, recyclerView));
                            this.i.setStrokeWidth((float) this.f.a(childAdapterPosition, recyclerView));
                            canvas.drawLine((float) a.left, (float) a.top, (float) a.right, (float) a.bottom, this.i);
                            break;
                    }
                    i = childAdapterPosition;
                }
            }
        }
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        a(rect, recyclerView.getChildAdapterPosition(view), recyclerView);
    }
}
