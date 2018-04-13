package cn.xiaochuankeji.tieba.ui.videomaker.sticker.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MagicEmotionHolder extends ViewHolder {
    @Nullable
    @BindView
    ImageView image;
    @Nullable
    @BindView
    AppCompatImageView ivSelect;
    @Nullable
    @BindView
    AppCompatImageView progres;
    @Nullable
    @BindView
    RecyclerView resource;
    @Nullable
    @BindView
    AppCompatImageView state;

    public MagicEmotionHolder(@NonNull View view) {
        super(view);
        ButterKnife.a(this, this.itemView);
    }
}
