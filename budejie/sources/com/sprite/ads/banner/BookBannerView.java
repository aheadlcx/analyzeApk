package com.sprite.ads.banner;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.i;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sprite.ads.R;
import com.sprite.ads.internal.a.d;
import com.sprite.ads.internal.bean.data.BookItem;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.nati.reporter.NoReporter;
import com.sprite.ads.third.book.BookAdData;
import com.sprite.ads.third.book.BookAdLoader;

public class BookBannerView extends LinearLayout implements OnClickListener {
    ImageView imgBookPic;
    BookItem mAdItem;
    BookAdLoader mAdLoader;
    Context mContext;
    BannerADListener mListener;
    BookAdData mNativeAdData;
    TextView txtBookAuthor;
    TextView txtBookIntro;
    TextView txtBookName;

    public BookBannerView(BookAdLoader bookAdLoader, BookAdData bookAdData, Context context, ViewGroup viewGroup, BannerADListener bannerADListener, BookItem bookItem) {
        super(context);
        this.mContext = context;
        this.mNativeAdData = bookAdData;
        this.mAdLoader = bookAdLoader;
        this.mListener = bannerADListener;
        this.mAdItem = bookItem;
        viewGroup.removeAllViews();
        viewGroup.addView(this, new LayoutParams(-2, -2));
        initView();
        initData();
    }

    private void initData() {
        if (!(this.mContext instanceof Activity) || ((Activity) this.mContext).isFinishing()) {
            this.imgBookPic.setImageURI(Uri.parse(""));
            ADLog.d("spritead", "花溪小说，Glide未能加载图片，当前上下文为：" + this.mContext.toString() + " 是否正在关闭：" + ((Activity) this.mContext).isFinishing());
        } else {
            i.b(this.mContext).a(this.mNativeAdData.getPic()).a(DiskCacheStrategy.SOURCE).a(this.imgBookPic);
        }
        this.txtBookName.setText(this.mNativeAdData.getTitle());
        this.txtBookAuthor.setText(this.mNativeAdData.getBookAuthor());
        this.txtBookIntro.setText(this.mNativeAdData.getDesc());
    }

    private void initView() {
        this.mListener.onADReceive(new NoReporter(), true);
        View.inflate(this.mContext, R.layout.ad_book_item, this);
        this.imgBookPic = (ImageView) findViewById(R.id.ad_book_pic);
        this.txtBookName = (TextView) findViewById(R.id.ad_book_name);
        this.txtBookAuthor = (TextView) findViewById(R.id.ad_book_author);
        this.txtBookIntro = (TextView) findViewById(R.id.ad_book_intro);
        findViewById(R.id.ad_book_container).setOnClickListener(this);
        ((TextView) findViewById(R.id.ad_book_change_text)).setOnClickListener(this);
        ((TextView) findViewById(R.id.ad_book_view_book_text)).setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ad_book_change_text) {
            BookAdData bookAdData = (BookAdData) this.mAdLoader.getNativeAdData();
            if (bookAdData != null) {
                this.mNativeAdData = bookAdData;
                initData();
            } else {
                this.mListener.onNoAD(0);
            }
            d.a("点击广告", "详情页|点击换一本");
        } else if (id == R.id.ad_book_view_book_text || id == R.id.ad_book_container) {
            d.a("点击广告", "详情页|点击立即阅读|" + this.mNativeAdData.book_id);
            this.mAdItem.setUrl("mod://BDJ_To_HuaXiReaderRead@bookId=" + this.mNativeAdData.book_id);
            this.mListener.onADSkip(this.mAdItem);
        }
    }
}
