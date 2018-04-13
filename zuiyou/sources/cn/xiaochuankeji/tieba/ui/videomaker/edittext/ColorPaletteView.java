package cn.xiaochuankeji.tieba.ui.videomaker.edittext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.util.ArrayList;

public class ColorPaletteView extends FrameLayout {
    private static final ArrayList<b> c = new ArrayList();
    private RecyclerView a;
    private d b;

    class a extends Adapter<c> {
        final /* synthetic */ ColorPaletteView a;

        a(ColorPaletteView colorPaletteView) {
            this.a = colorPaletteView;
        }

        public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
            a((c) viewHolder, i);
        }

        public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return a(viewGroup, i);
        }

        public c a(ViewGroup viewGroup, int i) {
            View view = new View(this.a.getContext());
            int a = e.a(22.0f);
            view.setLayoutParams(new LayoutParams(a, a));
            return new c(this.a, view);
        }

        public void a(c cVar, int i) {
            cVar.a((b) ColorPaletteView.c.get(i));
        }

        public int getItemCount() {
            return ColorPaletteView.c.size();
        }
    }

    static class b {
        int a;
        int b;

        public b(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    class c extends ViewHolder {
        final /* synthetic */ ColorPaletteView a;
        private b b;

        public c(ColorPaletteView colorPaletteView, View view) {
            this.a = colorPaletteView;
            super(view);
        }

        public void a(b bVar) {
            this.b = bVar;
            this.itemView.setBackgroundColor(bVar.a);
            this.itemView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.a.b != null) {
                        this.a.a.b.a(this.a.b.a, this.a.b.b);
                    }
                }
            });
        }
    }

    public interface d {
        void a(int i, int i2);
    }

    static {
        c.add(new b(-1, ViewCompat.MEASURED_STATE_MASK));
        c.add(new b(ViewCompat.MEASURED_STATE_MASK, -1));
        c.add(new b(-9441372, -1));
        c.add(new b(-398257, -1));
        c.add(new b(-1215927, -1));
        c.add(new b(-1283944, -1));
        c.add(new b(-9147914, -1));
        c.add(new b(-6292235, -1));
        c.add(new b(-602134, -1));
        c.add(new b(-1801363, -1));
        c.add(new b(-537734, -1));
        c.add(new b(-7286885, -1));
        c.add(new b(-3743257, -1));
        c.add(new b(-7297582, -1));
        c.add(new b(-4803889, -1));
        c.add(new b(-469805, -1));
        c.add(new b(-6381922, -1));
        c.add(new b(-12434878, -1));
    }

    public ColorPaletteView(Context context) {
        this(context, null);
    }

    public ColorPaletteView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorPaletteView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c();
    }

    private void c() {
        this.a = new RecyclerView(getContext());
        addView(this.a, new FrameLayout.LayoutParams(-1, -1));
        this.a.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.a.setAdapter(new a(this));
    }

    public void a() {
        this.a.scrollToPosition(0);
    }

    protected void dispatchDraw(Canvas canvas) {
        Path path = new Path();
        float a = (float) e.a(4.0f);
        path.addRoundRect(new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight()), a, a, Direction.CCW);
        canvas.clipPath(path, Op.REPLACE);
        canvas.save();
        super.dispatchDraw(canvas);
    }

    public void setColorSelectedListener(d dVar) {
        this.b = dVar;
    }
}
