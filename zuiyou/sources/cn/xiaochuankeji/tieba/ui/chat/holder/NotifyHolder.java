package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class NotifyHolder extends ViewHolder {
    @BindView
    public WebImageView avatar;
    @BindView
    public TextView brief;
    @BindView
    public ImageView brief_type;
    @BindView
    public TextView danmaku;
    @BindView
    public View divider;
    @BindView
    public TextView hug;
    @BindView
    public LinearLayout icon_group;
    @BindView
    public View layout_right;
    @BindView
    public TextView likes;
    @BindView
    public TextView main;
    @BindView
    public ImageView more;
    @BindView
    public TextView review;
    @BindView
    public TextView share;
    @BindView
    public WebImageView thumb;
    @BindView
    public TextView ugc;
    @BindView
    public WebImageView ugc_tag;
    @BindView
    public TextView vote;

    public NotifyHolder(@NonNull View view) {
        super(view);
        ButterKnife.a(this, this.itemView);
    }
}
