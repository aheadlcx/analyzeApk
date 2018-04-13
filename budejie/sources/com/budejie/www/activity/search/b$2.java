package com.budejie.www.activity.search;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.util.aa;

class b$2 implements OnItemClickListener {
    final /* synthetic */ b a;

    b$2(b bVar) {
        this.a = bVar;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        try {
            PlateBean plateBean = (PlateBean) this.a.c.getItemAtPosition(i);
            Intent intent = new Intent(this.a.getActivity(), CommonLabelActivity.class);
            intent.putExtra("theme_name", plateBean.theme_name);
            intent.putExtra("theme_id", plateBean.theme_id);
            intent.putExtra("colum_set", plateBean.colum_set);
            this.a.getActivity().startActivityForResult(intent, 0);
        } catch (Exception e) {
            if (!TextUtils.isEmpty(e.getMessage())) {
                aa.e("SearchLabelPageFragment", e.getMessage());
            }
        }
    }
}
