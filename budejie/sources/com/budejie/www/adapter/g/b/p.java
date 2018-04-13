package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.budejie.www.R;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;

public class p extends a<ListItemObject> {
    public ViewGroup e;

    public p(Context context, b<ListItemObject> bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.a, R.layout.post_item_layout, viewGroup);
        this.e = (LinearLayout) inflate.findViewById(R.id.post_container);
        return inflate;
    }

    public void c() {
    }

    public void onClick(View view) {
    }
}
