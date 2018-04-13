package com.spriteapp.booklibrary.ui.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.a.f;
import com.spriteapp.booklibrary.config.HuaXiSDK;
import com.spriteapp.booklibrary.ui.fragment.BookshelfFragment;
import com.spriteapp.booklibrary.ui.fragment.BookstoreFragment;
import com.spriteapp.booklibrary.ui.fragment.DiscoverFragment;
import com.spriteapp.booklibrary.ui.fragment.MeFragment;
import com.spriteapp.booklibrary.util.ActivityUtil;
import com.spriteapp.booklibrary.util.AppUtil;
import com.spriteapp.booklibrary.util.CollectionUtil;
import com.spriteapp.booklibrary.util.ScreenUtil;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends TitleActivity implements OnClickListener {
    private static final int BOOKSHELF_POSITION = 0;
    private static final int BOOKSTORE_POSITION = 2;
    private static final int DISCOVER_POSITION = 1;
    private static final int ME_POSITION = 3;
    private static final String TAG = "HomeActivity";
    private static final int TOP_BAR_HEIGHT = 47;
    private ViewPagerAdapter mAdapter;
    LinearLayout mBookshelfLayout;
    LinearLayout mBookstoreLayout;
    private Context mContext;
    LinearLayout mDiscoverLayout;
    private List<Fragment> mFragmentList;
    ViewPager mHomeViewPager;
    LinearLayout mMeLayout;

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return (Fragment) HomeActivity.this.mFragmentList.get(i);
        }

        public int getCount() {
            return HomeActivity.this.mFragmentList.size();
        }
    }

    private class ViewPagerListener implements OnPageChangeListener {
        private ViewPagerListener() {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            switch (i) {
                case 0:
                    HomeActivity.this.addFreeTextView();
                    HomeActivity.this.setTitle(f.book_reader_bookshelf);
                    HomeActivity.this.setSelectView(0);
                    HomeActivity.this.visible(HomeActivity.this.mLeftLayout);
                    return;
                case 1:
                    HomeActivity.this.mRightLayout.removeAllViews();
                    HomeActivity.this.setTitle(f.book_reader_discover);
                    HomeActivity.this.setSelectView(1);
                    HomeActivity.this.gone(HomeActivity.this.mLeftLayout);
                    return;
                case 2:
                    HomeActivity.this.setTitle(f.book_reader_bookstore);
                    HomeActivity.this.addSearchView();
                    HomeActivity.this.setSelectView(2);
                    HomeActivity.this.gone(HomeActivity.this.mLeftLayout);
                    return;
                case 3:
                    HomeActivity.this.setTitle(f.book_reader_me);
                    HomeActivity.this.mRightLayout.removeAllViews();
                    HomeActivity.this.setSelectView(3);
                    HomeActivity.this.gone(HomeActivity.this.mLeftLayout);
                    return;
                default:
                    return;
            }
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    public void initData() {
        this.mContext = this;
        setTitle("书架");
        addFreeTextView();
        initFragment();
        setAdapter();
        setListener();
    }

    private void setListener() {
        this.mHomeViewPager.addOnPageChangeListener(new ViewPagerListener());
        this.mBookshelfLayout.setOnClickListener(this);
        this.mDiscoverLayout.setOnClickListener(this);
        this.mBookstoreLayout.setOnClickListener(this);
        this.mMeLayout.setOnClickListener(this);
    }

    private void setAdapter() {
        this.mHomeViewPager.setOffscreenPageLimit(3);
        this.mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        this.mHomeViewPager.setAdapter(this.mAdapter);
    }

    private void initFragment() {
        this.mFragmentList = new ArrayList();
        this.mFragmentList.add(new BookshelfFragment());
        this.mFragmentList.add(new DiscoverFragment());
        this.mFragmentList.add(new BookstoreFragment());
        this.mFragmentList.add(new MeFragment());
    }

    public void addContentView() {
        this.mContainerLayout.addView(getLayoutInflater().inflate(e.book_reader_activity_book, null), -1, -1);
    }

    public void findViewId() {
        super.findViewId();
        this.mHomeViewPager = (ViewPager) findViewById(d.book_reader_home_view_pager);
        this.mBookshelfLayout = (LinearLayout) findViewById(d.book_reader_bookshelf_layout);
        this.mDiscoverLayout = (LinearLayout) findViewById(d.book_reader_discover_layout);
        this.mBookstoreLayout = (LinearLayout) findViewById(d.book_reader_bookstore_layout);
        this.mMeLayout = (LinearLayout) findViewById(d.book_reader_me_layout);
        this.mBookshelfLayout.setSelected(true);
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view == this.mBookshelfLayout) {
            this.mHomeViewPager.setCurrentItem(0);
            setSelectView(0);
        } else if (view == this.mDiscoverLayout) {
            this.mHomeViewPager.setCurrentItem(1);
            setSelectView(1);
        } else if (view == this.mBookstoreLayout) {
            this.mHomeViewPager.setCurrentItem(2);
            setSelectView(2);
        } else if (view == this.mMeLayout) {
            this.mHomeViewPager.setCurrentItem(3);
            setSelectView(3);
        }
    }

    private void setSelectView(int i) {
        switch (i) {
            case 0:
                this.mBookshelfLayout.setSelected(true);
                setSelectFalse(this.mDiscoverLayout, this.mBookstoreLayout, this.mMeLayout);
                return;
            case 1:
                this.mDiscoverLayout.setSelected(true);
                setSelectFalse(this.mBookshelfLayout, this.mBookstoreLayout, this.mMeLayout);
                return;
            case 2:
                this.mBookstoreLayout.setSelected(true);
                setSelectFalse(this.mBookshelfLayout, this.mDiscoverLayout, this.mMeLayout);
                return;
            case 3:
                this.mMeLayout.setSelected(true);
                setSelectFalse(this.mBookshelfLayout, this.mDiscoverLayout, this.mBookstoreLayout);
                return;
            default:
                return;
        }
    }

    private void setViewpagerTopMargin(int i) {
        LayoutParams layoutParams = (LayoutParams) this.mHomeViewPager.getLayoutParams();
        layoutParams.topMargin = ScreenUtil.dpToPxInt((float) i);
        this.mHomeViewPager.setLayoutParams(layoutParams);
    }

    private void addSearchView() {
        this.mRightLayout.removeAllViews();
        View inflate = LayoutInflater.from(this).inflate(e.book_reader_book_store_search_layout, null);
        this.mRightLayout.addView(inflate);
        inflate.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivityUtil.toWebViewActivity(HomeActivity.this.mContext, "http://s.hxdrive.net/book_search");
            }
        });
    }

    private void addFreeTextView() {
        this.mRightLayout.removeAllViews();
        TextView textView = (TextView) LayoutInflater.from(this).inflate(e.book_reader_free_text_layout, null);
        int rightTitleColor = HuaXiSDK.getInstance().getConfig().getRightTitleColor();
        if (rightTitleColor != 0) {
            textView.setTextColor(rightTitleColor);
        }
        this.mRightLayout.addView(textView);
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (AppUtil.isLogin()) {
                    ActivityUtil.toWebViewActivity(HomeActivity.this.mContext, "http://s.hxdrive.net/user_signin");
                } else {
                    HuaXiSDK.getInstance().toLoginPage(HomeActivity.this.mContext);
                }
            }
        });
    }

    public void onBackPressed() {
        BookshelfFragment shelfFragment = getShelfFragment();
        if (shelfFragment == null || !shelfFragment.isDeleteBook()) {
            Fragment currentFragment = getCurrentFragment();
            if (currentFragment == null || (currentFragment instanceof BookshelfFragment)) {
                super.onBackPressed();
                return;
            }
            this.mHomeViewPager.setCurrentItem(0);
            setSelectView(0);
            return;
        }
        shelfFragment.setDeleteBook();
    }

    public Fragment getCurrentFragment() {
        if (CollectionUtil.isEmpty(this.mFragmentList)) {
            return null;
        }
        int currentItem = this.mHomeViewPager.getCurrentItem();
        if (currentItem < 0 || currentItem >= this.mFragmentList.size()) {
            return null;
        }
        return (Fragment) this.mFragmentList.get(currentItem);
    }

    public BookshelfFragment getShelfFragment() {
        Fragment currentFragment = getCurrentFragment();
        if (currentFragment == null || !(currentFragment instanceof BookshelfFragment)) {
            return null;
        }
        return (BookshelfFragment) currentFragment;
    }
}
