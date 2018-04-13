package com.marshalchen.ultimaterecyclerview.layoutmanagers;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import com.marshalchen.ultimaterecyclerview.a.a;
import com.marshalchen.ultimaterecyclerview.e;

public class ClassicSpanGridLayoutManager extends GridLayoutManager {
    private final e a;
    private int b = -1;
    private SpanSizeLookup c = new SpanSizeLookup(this) {
        final /* synthetic */ ClassicSpanGridLayoutManager a;

        {
            this.a = r1;
        }

        public int getSpanGroupIndex(int i, int i2) {
            return super.getSpanGroupIndex(i, i2);
        }

        public int getSpanSize(int i) {
            return this.a.a(i);
        }
    };

    protected int a(int i) {
        if (this.b == 2) {
            if (this.a instanceof a) {
                int itemViewType = this.a.getItemViewType(i);
                if (itemViewType == 2 || itemViewType == 1 || i == 0) {
                    return getSpanCount();
                }
            }
        } else if (this.b == -1 && (this.a instanceof a)) {
            a aVar = (a) this.a;
            if (aVar.getItemViewType(i) == 2) {
                return getSpanCount();
            }
            if (aVar.getItemViewType(i) == 1) {
                return getSpanCount();
            }
            if (aVar.getItemViewType(i) == 0) {
                return 1;
            }
        }
        return 1;
    }

    public ClassicSpanGridLayoutManager(Context context, int i, a aVar) {
        super(context, i);
        this.a = aVar;
        setSpanSizeLookup(this.c);
    }

    public ClassicSpanGridLayoutManager(Context context, int i, int i2, int i3, a aVar) {
        super(context, i, i3, false);
        this.a = aVar;
        setSpanSizeLookup(this.c);
        if (i2 > 0) {
            this.b = i2;
        }
    }

    public ClassicSpanGridLayoutManager(Context context, int i, int i2, a aVar) {
        super(context, i);
        this.a = aVar;
        setSpanSizeLookup(this.c);
        this.b = i2;
    }
}
