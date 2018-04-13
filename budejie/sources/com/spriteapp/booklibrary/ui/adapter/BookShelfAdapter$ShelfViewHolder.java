package com.spriteapp.booklibrary.ui.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.spriteapp.booklibrary.a.d;

class BookShelfAdapter$ShelfViewHolder extends ViewHolder {
    ImageView deleteImageView;
    ImageView logoImageView;
    RelativeLayout mShadowLayout;
    TextView progressTextView;
    TextView titleTextView;

    BookShelfAdapter$ShelfViewHolder(View view) {
        super(view);
        this.progressTextView = (TextView) view.findViewById(d.book_reader_progress_text_view);
        this.titleTextView = (TextView) view.findViewById(d.book_reader_title_text_view);
        this.logoImageView = (ImageView) view.findViewById(d.book_reader_logo_image_view);
        this.deleteImageView = (ImageView) view.findViewById(d.book_reader_delete_image_view);
        this.mShadowLayout = (RelativeLayout) view.findViewById(d.book_reader_image_layout);
    }
}
