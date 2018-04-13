package cn.v6.sixrooms.hall;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.PopupWindow;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.base.SixRoomsUtils;
import java.util.List;

final class am extends PopupWindow {
    private List<ProvinceNumBean> a;
    private LocationAdapter b;
    private ProvinceNumBean c;
    private BaseViewable d;

    am(@NonNull Context context, @NonNull List<ProvinceNumBean> list, BaseViewable baseViewable) {
        super(context);
        this.a = list;
        this.d = baseViewable;
        a(context);
    }

    private void a(Context context) {
        setWidth(-1);
        setHeight(-2);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable());
        setOutsideTouchable(true);
        for (ProvinceNumBean provinceNumBean : this.a) {
            if (provinceNumBean.isSelect()) {
                this.c = provinceNumBean;
                break;
            }
        }
        View recyclerView = new RecyclerView(context);
        recyclerView.setLayoutParams(SixRoomsUtils.paramsFrame(-1, -1));
        recyclerView.setBackgroundColor(context.getResources().getColor(R.color.white));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.b = new LocationAdapter(context, this.a, new an(this));
        recyclerView.setAdapter(this.b);
        setContentView(recyclerView);
    }
}
