package com.youth.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;
import com.youth.banner.view.BannerViewPager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Banner extends FrameLayout implements OnPageChangeListener {
    private Banner$BannerPagerAdapter adapter;
    private OnBannerClickListener bannerListener;
    private int bannerStyle;
    private TextView bannerTitle;
    private Context context;
    private int count;
    private int currentItem;
    private int delayTime;
    private DisplayMetrics dm;
    private ImageView emptyImage;
    private int gravity;
    private WeakHandler handler;
    private ImageLoaderInterface imageLoader;
    private List imageUrls;
    private List<View> imageViews;
    private LinearLayout indicator;
    private List<ImageView> indicatorImages;
    private LinearLayout indicatorInside;
    private int indicatorSize;
    private boolean isAutoPlay;
    private boolean isScroll;
    private int lastPosition;
    private OnBannerListener listener;
    private int mIndicatorHeight;
    private int mIndicatorMargin;
    private int mIndicatorSelectedResId;
    private int mIndicatorUnselectedResId;
    private int mIndicatorWidth;
    private OnPageChangeListener mOnPageChangeListener;
    private BannerScroller mScroller;
    private TextView numIndicator;
    private TextView numIndicatorInside;
    private int scaleType;
    private int scrollTime;
    public String tag;
    private final Runnable task;
    private int titleBackground;
    private boolean titleEnable;
    private int titleHeight;
    private int titleTextColor;
    private int titleTextSize;
    private LinearLayout titleView;
    private List<String> titles;
    private BannerViewPager viewPager;

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Banner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.tag = "banner";
        this.mIndicatorMargin = 5;
        this.bannerStyle = 1;
        this.delayTime = 2000;
        this.scrollTime = 800;
        this.isAutoPlay = true;
        this.isScroll = true;
        this.mIndicatorSelectedResId = R.drawable.gray_radius;
        this.mIndicatorUnselectedResId = R.drawable.white_radius;
        this.count = 0;
        this.gravity = -1;
        this.lastPosition = 1;
        this.scaleType = 1;
        this.titleEnable = true;
        this.imageUrls = new ArrayList();
        this.handler = new WeakHandler();
        this.task = new Banner$1(this);
        this.context = context;
        this.titles = new ArrayList();
        this.imageUrls = new ArrayList();
        this.imageViews = new ArrayList();
        this.indicatorImages = new ArrayList();
        this.dm = context.getResources().getDisplayMetrics();
        this.indicatorSize = this.dm.widthPixels / 80;
        initView(context, attributeSet);
    }

    private void handleTypedArray(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Banner);
            this.mIndicatorWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_indicator_width, this.indicatorSize);
            this.mIndicatorHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_indicator_height, this.indicatorSize);
            this.mIndicatorMargin = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_indicator_margin, 5);
            this.mIndicatorSelectedResId = obtainStyledAttributes.getResourceId(R.styleable.Banner_indicator_drawable_selected, R.drawable.gray_radius);
            this.mIndicatorUnselectedResId = obtainStyledAttributes.getResourceId(R.styleable.Banner_indicator_drawable_unselected, R.drawable.white_radius);
            this.scaleType = obtainStyledAttributes.getInt(R.styleable.Banner_image_scale_type, this.scaleType);
            this.delayTime = obtainStyledAttributes.getInt(R.styleable.Banner_delay_time, 2000);
            this.scrollTime = obtainStyledAttributes.getInt(R.styleable.Banner_scroll_time, 800);
            this.isAutoPlay = obtainStyledAttributes.getBoolean(R.styleable.Banner_is_auto_play, true);
            this.titleBackground = obtainStyledAttributes.getColor(R.styleable.Banner_title_background, -1);
            this.titleHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_title_height, -1);
            this.titleTextColor = obtainStyledAttributes.getColor(R.styleable.Banner_title_textcolor, -1);
            this.titleTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_title_textsize, -1);
            obtainStyledAttributes.recycle();
        }
    }

    private void initView(Context context, AttributeSet attributeSet) {
        this.imageViews.clear();
        View inflate = LayoutInflater.from(context).inflate(R.layout.banner, this, true);
        this.viewPager = (BannerViewPager) inflate.findViewById(R.id.bannerViewPager);
        this.titleView = (LinearLayout) inflate.findViewById(R.id.titleView);
        this.indicator = (LinearLayout) inflate.findViewById(R.id.circleIndicator);
        this.indicatorInside = (LinearLayout) inflate.findViewById(R.id.indicatorInside);
        this.bannerTitle = (TextView) inflate.findViewById(R.id.bannerTitle);
        this.numIndicator = (TextView) inflate.findViewById(R.id.numIndicator);
        this.numIndicatorInside = (TextView) inflate.findViewById(R.id.numIndicatorInside);
        this.emptyImage = (ImageView) inflate.findViewById(R.id.iv_empty);
        handleTypedArray(context, attributeSet);
        initViewPagerScroll();
    }

    private void initViewPagerScroll() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            this.mScroller = new BannerScroller(this.viewPager.getContext());
            this.mScroller.setDuration(this.scrollTime);
            declaredField.set(this.viewPager, this.mScroller);
        } catch (Exception e) {
            Log.e(this.tag, e.getMessage());
        }
    }

    public Banner isAutoPlay(boolean z) {
        this.isAutoPlay = z;
        return this;
    }

    public Banner setImageLoader(ImageLoaderInterface imageLoaderInterface) {
        this.imageLoader = imageLoaderInterface;
        return this;
    }

    public Banner setDelayTime(int i) {
        this.delayTime = i;
        return this;
    }

    public Banner setIndicatorGravity(int i) {
        switch (i) {
            case 5:
                this.gravity = 19;
                break;
            case 6:
                this.gravity = 17;
                break;
            case 7:
                this.gravity = 21;
                break;
        }
        return this;
    }

    public Banner setBannerAnimation(Class<? extends PageTransformer> cls) {
        try {
            setPageTransformer(true, (PageTransformer) cls.newInstance());
        } catch (Exception e) {
            Log.e(this.tag, "Please set the PageTransformer class");
        }
        return this;
    }

    public Banner setOffscreenPageLimit(int i) {
        if (this.viewPager != null) {
            this.viewPager.setOffscreenPageLimit(i);
        }
        return this;
    }

    public Banner setPageTransformer(boolean z, PageTransformer pageTransformer) {
        this.viewPager.setPageTransformer(z, pageTransformer);
        return this;
    }

    public Banner setBannerTitles(List<String> list) {
        if (this.titles != null) {
            this.titles.clear();
        }
        this.titles = list;
        return this;
    }

    public Banner setBannerStyle(int i) {
        this.bannerStyle = i;
        return this;
    }

    public Banner setTitleEnable(boolean z) {
        this.titleEnable = z;
        return this;
    }

    public Banner setViewPagerIsScroll(boolean z) {
        this.isScroll = z;
        return this;
    }

    public Banner setImages(List<?> list) {
        this.imageUrls.addAll(list);
        this.count = list.size();
        return this;
    }

    public void update(List<?> list, List<String> list2) {
        this.imageUrls.clear();
        this.titles.clear();
        this.imageUrls.addAll(list);
        this.titles.addAll(list2);
        this.count = this.imageUrls.size();
        start();
    }

    public void update(List<?> list) {
        this.imageUrls.clear();
        this.imageUrls.addAll(list);
        this.count = this.imageUrls.size();
        start();
    }

    public void updateBannerStyle(int i) {
        this.indicator.setVisibility(8);
        this.numIndicator.setVisibility(8);
        this.numIndicatorInside.setVisibility(8);
        this.indicatorInside.setVisibility(8);
        this.bannerTitle.setVisibility(8);
        this.titleView.setVisibility(8);
        this.bannerStyle = i;
        start();
    }

    public Banner start() {
        setBannerStyleUI();
        setImageList(this.imageUrls);
        setData();
        return this;
    }

    private void setTitleStyleUI() {
        if (this.titles.size() != this.imageUrls.size()) {
            throw new RuntimeException("[Banner] --> The number of titles and images is different");
        }
        if (this.titleBackground != -1) {
            this.titleView.setBackgroundColor(this.titleBackground);
        }
        if (this.titleHeight != -1) {
            this.titleView.setLayoutParams(new LayoutParams(-1, this.titleHeight));
        }
        if (this.titleTextColor != -1) {
            this.bannerTitle.setTextColor(this.titleTextColor);
        }
        if (this.titleTextSize != -1) {
            this.bannerTitle.setTextSize(0, (float) this.titleTextSize);
        }
        if (this.titles != null && this.titles.size() > 0) {
            this.bannerTitle.setText((CharSequence) this.titles.get(0));
            this.bannerTitle.setVisibility(0);
            this.titleView.setVisibility(0);
        }
    }

    private void setBannerStyleUI() {
        int i;
        if (this.count > 1) {
            i = 0;
        } else {
            i = 8;
        }
        switch (this.bannerStyle) {
            case 1:
                this.indicator.setVisibility(i);
                break;
            case 2:
                this.numIndicator.setVisibility(i);
                break;
            case 3:
                this.numIndicatorInside.setVisibility(i);
                break;
            case 4:
                this.indicator.setVisibility(i);
                break;
            case 5:
                this.indicatorInside.setVisibility(i);
                break;
        }
        if (this.titleEnable) {
            setTitleStyleUI();
        }
    }

    private void initImages() {
        this.imageViews.clear();
        if (this.bannerStyle == 1 || this.bannerStyle == 4 || this.bannerStyle == 5) {
            createIndicator();
        } else if (this.bannerStyle == 3) {
            this.numIndicatorInside.setText("1/" + this.count);
        } else if (this.bannerStyle == 2) {
            this.numIndicator.setText("1/" + this.count);
        }
    }

    private void setImageList(List<?> list) {
        if (list == null || list.size() <= 0) {
            Log.e(this.tag, "Please set the images data.");
            return;
        }
        initImages();
        for (int i = 0; i <= this.count + 1; i++) {
            Object obj;
            View view = null;
            if (this.imageLoader != null) {
                view = this.imageLoader.createImageView(this.context);
            }
            if (view == null) {
                view = new ImageView(this.context);
            }
            setScaleType(view);
            if (i == 0) {
                obj = list.get(this.count - 1);
            } else if (i == this.count + 1) {
                obj = list.get(0);
            } else {
                obj = list.get(i - 1);
            }
            this.imageViews.add(view);
            if (this.imageLoader != null) {
                this.imageLoader.displayImage(this.context, obj, view);
            } else {
                Log.e(this.tag, "Please set images loader.");
            }
        }
    }

    private void setScaleType(View view) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            switch (this.scaleType) {
                case 0:
                    imageView.setScaleType(ScaleType.CENTER);
                    return;
                case 1:
                    imageView.setScaleType(ScaleType.CENTER_CROP);
                    return;
                case 2:
                    imageView.setScaleType(ScaleType.CENTER_INSIDE);
                    return;
                case 3:
                    imageView.setScaleType(ScaleType.FIT_CENTER);
                    return;
                case 4:
                    imageView.setScaleType(ScaleType.FIT_END);
                    return;
                case 5:
                    imageView.setScaleType(ScaleType.FIT_START);
                    return;
                case 6:
                    imageView.setScaleType(ScaleType.FIT_XY);
                    return;
                case 7:
                    imageView.setScaleType(ScaleType.MATRIX);
                    return;
                default:
                    return;
            }
        }
    }

    public void showEmptyImage(boolean z) {
        if (z) {
            this.emptyImage.setImageResource(R.drawable.banner_empty);
            this.emptyImage.setVisibility(0);
            return;
        }
        this.emptyImage.setVisibility(8);
    }

    public void showLoadingImage() {
        this.emptyImage.setImageResource(R.drawable.icon_top_default_placeholde);
        this.emptyImage.setVisibility(0);
    }

    private void createIndicator() {
        this.indicatorImages.clear();
        this.indicator.removeAllViews();
        this.indicatorInside.removeAllViews();
        for (int i = 0; i < this.count; i++) {
            View imageView = new ImageView(this.context);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mIndicatorWidth, this.mIndicatorHeight);
            layoutParams.leftMargin = this.mIndicatorMargin;
            layoutParams.rightMargin = this.mIndicatorMargin;
            if (i == 0) {
                imageView.setImageResource(this.mIndicatorSelectedResId);
            } else {
                imageView.setImageResource(this.mIndicatorUnselectedResId);
            }
            this.indicatorImages.add(imageView);
            if (this.bannerStyle == 1 || this.bannerStyle == 4) {
                this.indicator.addView(imageView, layoutParams);
            } else if (this.bannerStyle == 5) {
                this.indicatorInside.addView(imageView, layoutParams);
            }
        }
    }

    private void setData() {
        this.currentItem = 1;
        if (this.adapter == null) {
            this.adapter = new Banner$BannerPagerAdapter(this);
            this.viewPager.addOnPageChangeListener(this);
        }
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.setFocusable(true);
        this.viewPager.setCurrentItem(1);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.indicator.getLayoutParams();
        if (this.gravity != -1) {
            layoutParams.gravity = this.gravity;
        }
        this.indicator.setLayoutParams(layoutParams);
        if (!this.isScroll || this.count <= 1) {
            this.viewPager.setScrollable(false);
        } else {
            this.viewPager.setScrollable(true);
        }
        if (this.isAutoPlay) {
            startAutoPlay();
        }
    }

    public void startAutoPlay() {
        this.handler.removeCallbacks(this.task);
        this.handler.postDelayed(this.task, (long) this.delayTime);
    }

    public void stopAutoPlay() {
        this.handler.removeCallbacks(this.task);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.isAutoPlay) {
            int action = motionEvent.getAction();
            if (action == 1 || action == 3 || action == 4) {
                startAutoPlay();
            } else if (action == 0) {
                stopAutoPlay();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public int toRealPosition(int i) {
        int i2 = (i - 1) % this.count;
        if (i2 < 0) {
            return i2 + this.count;
        }
        return i2;
    }

    public void onPageScrollStateChanged(int i) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrollStateChanged(i);
        }
        this.currentItem = this.viewPager.getCurrentItem();
        switch (i) {
            case 0:
                if (this.currentItem == 0) {
                    this.viewPager.setCurrentItem(this.count, false);
                    return;
                } else if (this.currentItem == this.count + 1) {
                    this.viewPager.setCurrentItem(1, false);
                    return;
                } else {
                    return;
                }
            case 1:
                if (this.currentItem == this.count + 1) {
                    this.viewPager.setCurrentItem(1, false);
                    return;
                } else if (this.currentItem == 0) {
                    this.viewPager.setCurrentItem(this.count, false);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageSelected(i);
        }
        if (this.bannerStyle == 1 || this.bannerStyle == 4 || this.bannerStyle == 5) {
            ((ImageView) this.indicatorImages.get(((this.lastPosition - 1) + this.count) % this.count)).setImageResource(this.mIndicatorUnselectedResId);
            ((ImageView) this.indicatorImages.get(((i - 1) + this.count) % this.count)).setImageResource(this.mIndicatorSelectedResId);
            this.lastPosition = i;
        }
        if (i == 0) {
            i = this.count;
        }
        if (i > this.count) {
            i = 1;
        }
        if (this.titles.size() >= i) {
            switch (this.bannerStyle) {
                case 1:
                    this.bannerTitle.setText((CharSequence) this.titles.get(i - 1));
                    return;
                case 2:
                    this.numIndicator.setText(i + "/" + this.count);
                    return;
                case 3:
                    this.numIndicatorInside.setText(i + "/" + this.count);
                    this.bannerTitle.setText((CharSequence) this.titles.get(i - 1));
                    return;
                case 4:
                    this.bannerTitle.setText((CharSequence) this.titles.get(i - 1));
                    return;
                case 5:
                    this.bannerTitle.setText((CharSequence) this.titles.get(i - 1));
                    return;
                default:
                    return;
            }
        }
    }

    @Deprecated
    public Banner setOnBannerClickListener(OnBannerClickListener onBannerClickListener) {
        this.bannerListener = onBannerClickListener;
        return this;
    }

    public Banner setOnBannerListener(OnBannerListener onBannerListener) {
        this.listener = onBannerListener;
        return this;
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }

    public void releaseBanner() {
        this.handler.removeCallbacksAndMessages(null);
        if (this.titles != null) {
            this.titles.clear();
        }
        if (this.imageUrls != null) {
            this.imageUrls.clear();
        }
        if (this.imageViews != null) {
            this.imageViews.clear();
        }
    }
}
