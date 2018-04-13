package com.spriteapp.booklibrary.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.spriteapp.booklibrary.a.a;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.enumeration.ChapterEnum;
import com.spriteapp.booklibrary.model.response.BookChapterResponse;
import com.spriteapp.booklibrary.util.CollectionUtil;
import java.util.List;

public class ChapterAdapter extends BaseAdapter {
    private boolean isNight;
    private List<BookChapterResponse> mCatalogList;
    private Context mContext;
    private int mCurrentChapter;
    private LayoutInflater mInflater;

    public ChapterAdapter(Context context, List<BookChapterResponse> list) {
        this.mContext = context;
        this.mCatalogList = list;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setCurrentChapter(int i) {
        this.mCurrentChapter = i;
    }

    public int getCurrentChapter() {
        return this.mCurrentChapter;
    }

    public int getCount() {
        return CollectionUtil.isEmpty(this.mCatalogList) ? 0 : this.mCatalogList.size();
    }

    public Object getItem(int i) {
        return this.mCatalogList.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ChapterAdapter$ViewHolder chapterAdapter$ViewHolder;
        int i2 = 1;
        int i3 = 0;
        if (view == null) {
            chapterAdapter$ViewHolder = new ChapterAdapter$ViewHolder(this, null);
            view = this.mInflater.inflate(e.book_reader_item_chapter_list, null);
            chapterAdapter$ViewHolder.chapterTextView = (TextView) view.findViewById(d.book_reader_chapter_text_view);
            chapterAdapter$ViewHolder.freeChapterTextView = (TextView) view.findViewById(d.book_reader_free_chapter_text_view);
            chapterAdapter$ViewHolder.lineView = view.findViewById(d.book_reader_line_view);
            view.setTag(chapterAdapter$ViewHolder);
        } else {
            chapterAdapter$ViewHolder = (ChapterAdapter$ViewHolder) view.getTag();
        }
        BookChapterResponse bookChapterResponse = (BookChapterResponse) this.mCatalogList.get(i);
        if (bookChapterResponse != null) {
            int i4;
            int i5;
            int i6 = bookChapterResponse.getChapterReadState() == ChapterEnum.HAS_READ.getCode() ? 1 : 0;
            Object chapter_title = bookChapterResponse.getChapter_title();
            if (!TextUtils.isEmpty(chapter_title)) {
                chapterAdapter$ViewHolder.chapterTextView.setText(chapter_title.trim());
            }
            if (bookChapterResponse.getChapter_id() == this.mCurrentChapter) {
                i4 = 1;
            } else {
                i4 = 0;
            }
            if (i4 != 0) {
                chapterAdapter$ViewHolder.chapterTextView.setTextColor(this.mContext.getResources().getColor(this.isNight ? a.book_reader_main_night_color : a.book_reader_main_color));
            } else {
                TextView textView = chapterAdapter$ViewHolder.chapterTextView;
                Resources resources = this.mContext.getResources();
                i6 = this.isNight ? i6 != 0 ? a.book_reader_chapter_item_night_color : a.book_reader_chapter_not_read_night_color : i6 != 0 ? a.book_reader_common_text_color : a.book_reader_chapter_not_read_day_color;
                textView.setTextColor(resources.getColor(i6));
            }
            if (ChapterEnum.CHAPTER_IS_VIP.getCode() != bookChapterResponse.getChapter_is_vip()) {
                i2 = 0;
            }
            TextView textView2 = chapterAdapter$ViewHolder.freeChapterTextView;
            if (i2 != 0) {
                i3 = 8;
            }
            textView2.setVisibility(i3);
            if (i4 != 0) {
                chapterAdapter$ViewHolder.freeChapterTextView.setTextColor(this.mContext.getResources().getColor(this.isNight ? a.book_reader_chapter_free_text_night_color : a.book_reader_chapter_free_text_day_color));
            } else {
                chapterAdapter$ViewHolder.freeChapterTextView.setTextColor(this.mContext.getResources().getColor(this.isNight ? a.book_reader_chapter_not_read_night_color : a.book_reader_chapter_not_read_day_color));
            }
            View view2 = chapterAdapter$ViewHolder.lineView;
            if (this.isNight) {
                i5 = a.book_reader_divide_line_night_color;
            } else {
                i5 = a.book_reader_divide_line_color;
            }
            view2.setBackgroundResource(i5);
        }
        return view;
    }

    public void setNight(boolean z) {
        this.isNight = z;
    }
}
