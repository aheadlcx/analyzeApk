package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.budejie.www.R;
import com.budejie.www.activity.labelsubscription.LabelTextView;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;

public class a extends com.budejie.www.adapter.g.a<ListItemObject> implements com.budejie.www.activity.labelsubscription.LabelTextView.a {
    private LabelTextView e;

    public a(Context context, b<ListItemObject> bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.a, R.layout.post_label, viewGroup);
        this.e = (LabelTextView) inflate.findViewById(R.id.label_set);
        this.e.setOnLabelClickListener(this);
        return inflate;
    }

    public void c() {
        this.e.setLabelData((ListItemObject) this.c);
    }

    public void onClick(View view) {
    }

    public void a(ListItemObject listItemObject, int i) {
        this.b.c.a(listItemObject, i);
    }
}
