package com.spriteapp.booklibrary.recyclerView.viewholder;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.recyclerView.decorate.Visitable;
import com.spriteapp.booklibrary.util.ToastUtil;

public class TypeViewHolder extends BaseViewHolder<Visitable> {
    private Context mContext;
    private ImageView mGoodBookImageView;
    private ImageView mNewBookImageView;
    private ImageView mOnSellImageView;
    private ImageView mQualityImageView;

    public TypeViewHolder(View view, Context context) {
        super(view);
        this.mContext = context;
        this.mOnSellImageView = (ImageView) view.findViewById(d.book_reader_on_sell_image_view);
        this.mQualityImageView = (ImageView) view.findViewById(d.book_reader_quality_image_view);
        this.mGoodBookImageView = (ImageView) view.findViewById(d.book_reader_good_book_image_view);
        this.mNewBookImageView = (ImageView) view.findViewById(d.book_reader_new_book_image_view);
    }

    public void bindViewData(Visitable visitable) {
        this.mOnSellImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ToastUtil.showSingleToast("限时特价");
            }
        });
        this.mQualityImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ToastUtil.showSingleToast("优质原创");
            }
        });
        this.mGoodBookImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ToastUtil.showSingleToast("优质好书");
            }
        });
        this.mNewBookImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ToastUtil.showSingleToast("新书抢先");
            }
        });
    }
}
