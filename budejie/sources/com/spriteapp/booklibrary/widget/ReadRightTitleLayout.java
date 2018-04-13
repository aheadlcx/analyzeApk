package com.spriteapp.booklibrary.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.spriteapp.booklibrary.a.c;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.e;

public class ReadRightTitleLayout extends LinearLayout {
    private ImageView mAddShelfImageView;
    private ImageView mBuyImageView;
    private Context mContext;
    private ImageView mHomeImageView;
    private ImageView mMoreImageView;
    private ImageView mRewardImageView;
    private ReadTitleListener mTitleListener;
    private View mView;

    public interface ReadTitleListener {
        void clickAddShelf();

        void clickBuy();

        void clickHome();

        void clickMore();

        void clickReward();
    }

    public ReadRightTitleLayout(Context context) {
        this(context, null);
    }

    public ReadRightTitleLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ReadRightTitleLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        initView();
    }

    private void initView() {
        this.mView = LayoutInflater.from(this.mContext).inflate(e.book_reader_read_title_layout, null);
        addView(this.mView);
        this.mBuyImageView = (ImageView) this.mView.findViewById(d.book_reader_buy_image_view);
        this.mAddShelfImageView = (ImageView) this.mView.findViewById(d.book_reader_add_shelf_image_view);
        this.mHomeImageView = (ImageView) this.mView.findViewById(d.book_reader_home_image_view);
        this.mRewardImageView = (ImageView) this.mView.findViewById(d.book_reader_reward_image_view);
        this.mMoreImageView = (ImageView) this.mView.findViewById(d.book_reader_more_image_view);
        setListener();
    }

    private void setListener() {
        click(this.mBuyImageView);
        click(this.mAddShelfImageView);
        click(this.mRewardImageView);
        click(this.mMoreImageView);
        click(this.mHomeImageView);
    }

    private void click(final View view) {
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (ReadRightTitleLayout.this.mTitleListener != null) {
                    if (view == ReadRightTitleLayout.this.mBuyImageView) {
                        ReadRightTitleLayout.this.mTitleListener.clickBuy();
                    } else if (view == ReadRightTitleLayout.this.mAddShelfImageView) {
                        ReadRightTitleLayout.this.mTitleListener.clickAddShelf();
                    } else if (view == ReadRightTitleLayout.this.mRewardImageView) {
                        ReadRightTitleLayout.this.mTitleListener.clickReward();
                    } else if (view == ReadRightTitleLayout.this.mMoreImageView) {
                        ReadRightTitleLayout.this.mTitleListener.clickMore();
                    } else if (view == ReadRightTitleLayout.this.mHomeImageView) {
                        ReadRightTitleLayout.this.mTitleListener.clickHome();
                    }
                }
            }
        });
    }

    public void changeMode(boolean z) {
        this.mBuyImageView.setImageResource(z ? c.book_reader_buy_night_selector : c.book_reader_buy_day_selector);
        this.mAddShelfImageView.setImageResource(z ? c.book_reader_add_shelf_night_selector : c.book_reader_add_shelf_day_selector);
        this.mRewardImageView.setImageResource(z ? c.book_reader_reward_night_selector : c.book_reader_reward_day_selector);
        this.mMoreImageView.setImageResource(z ? c.book_reader_more_night_selector : c.book_reader_more_day_selector);
        this.mHomeImageView.setImageResource(z ? c.book_reader_home_night_selector : c.book_reader_home_day_selector);
    }

    public void setAddShelfViewState(boolean z) {
        if (this.mAddShelfImageView != null) {
            this.mAddShelfImageView.setVisibility(z ? 8 : 0);
        }
    }

    public void setTitleListener(ReadTitleListener readTitleListener) {
        this.mTitleListener = readTitleListener;
    }

    public void setBuyImageState(boolean z) {
        this.mBuyImageView.setVisibility(z ? 0 : 8);
    }

    public void setHomeViewState(boolean z) {
        this.mHomeImageView.setVisibility(z ? 0 : 8);
    }
}
