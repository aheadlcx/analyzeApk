package com.budejie.www.activity.phonenumber;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.budejie.www.adapter.a.i;
import com.budejie.www.adapter.a.i$a;
import com.budejie.www.bean.RecommendUser;

class d$1 implements OnItemClickListener {
    final /* synthetic */ d a;

    d$1(d dVar) {
        this.a = dVar;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        i$a i_a = (i$a) view.getTag();
        i_a.b.toggle();
        i.a().put(Integer.valueOf(i), Boolean.valueOf(i_a.b.isChecked()));
        if (i_a.b.isChecked()) {
            this.a.a.add(((RecommendUser) d.a(this.a).get(i)).getUserid());
        } else {
            this.a.a.remove(i);
        }
    }
}
