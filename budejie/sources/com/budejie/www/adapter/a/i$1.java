package com.budejie.www.adapter.a;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.RecommendUser;

class i$1 implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ i b;

    i$1(i iVar, int i) {
        this.b = iVar;
        this.a = i;
    }

    public void onClick(View view) {
        i$a i_a = (i$a) view.getTag();
        i_a.b.toggle();
        i.a().put(Integer.valueOf(this.a), Boolean.valueOf(i_a.b.isChecked()));
        Log.d("RecommendAttentionAdapter", "position=" + this.a + "---holder.cb.isChecked()=" + i_a.b.isChecked());
        if (i_a.b.isChecked()) {
            i.a.put(((RecommendUser) i.a(this.b).get(this.a)).getUserid(), new Fans((RecommendUser) i.a(this.b).get(this.a)));
        } else {
            i.a.remove(((RecommendUser) i.a(this.b).get(this.a)).getUserid());
        }
    }
}
