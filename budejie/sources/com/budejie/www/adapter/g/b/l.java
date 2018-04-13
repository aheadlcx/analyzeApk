package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.budejie.www.R;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;

public class l extends a<ListItemObject> {
    public l(Context context, b bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        return View.inflate(this.a, R.layout.new_new_list_item_link, viewGroup);
    }

    public void c() {
    }

    public void onClick(View view) {
    }
}
