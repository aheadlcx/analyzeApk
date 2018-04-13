package com.c.c;

import com.c.a.c;
import com.handmark.pulltorefresh.library.R;

public class a {
    private int a = 1;
    private com.c.b.a b;

    public a(com.c.b.a aVar) {
        this.b = aVar;
    }

    public void a(c cVar) {
        if (this.a == 1 || this.a == 5) {
            cVar.a(R.id.rl_loading).setVisibility(8);
            cVar.a(R.id.tv_end).setVisibility(8);
            cVar.a(R.id.tv_error).setVisibility(8);
        } else if (this.a == 3) {
            cVar.a(R.id.rl_loading).setVisibility(8);
            cVar.a(R.id.tv_end).setVisibility(0);
            cVar.a(R.id.tv_error).setVisibility(8);
        } else if (this.a == 4) {
            cVar.a(R.id.rl_loading).setVisibility(8);
            cVar.a(R.id.tv_end).setVisibility(8);
            cVar.a(R.id.tv_error).setVisibility(0);
        } else {
            cVar.a(R.id.rl_loading).setVisibility(0);
            cVar.a(R.id.tv_end).setVisibility(8);
            cVar.a(R.id.tv_error).setVisibility(8);
        }
        cVar.a(R.id.tv_error).setOnClickListener(new b(this));
    }

    public void a(int i) {
        this.a = i;
    }

    public int a() {
        return this.a;
    }

    public void a(com.c.b.a aVar) {
        this.b = aVar;
    }
}
