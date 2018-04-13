package cn.xiaochuankeji.tieba.ui.picker;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegionHolder extends ViewHolder {
    @BindView
    View divider;
    @BindView
    AppCompatImageView flag;
    @BindView
    AppCompatTextView name;

    RegionHolder(View view) {
        super(view);
        ButterKnife.a(this, view);
    }
}
