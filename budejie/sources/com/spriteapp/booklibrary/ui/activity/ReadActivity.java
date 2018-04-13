package com.spriteapp.booklibrary.ui.activity;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.AlertDialog;
import android.util.Property;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.spriteapp.booklibrary.a.f;
import com.spriteapp.booklibrary.b.a;
import com.spriteapp.booklibrary.base.Base;
import com.spriteapp.booklibrary.config.HuaXiSDK;
import com.spriteapp.booklibrary.d.b;
import com.spriteapp.booklibrary.d.c;
import com.spriteapp.booklibrary.d.d;
import com.spriteapp.booklibrary.d.e;
import com.spriteapp.booklibrary.enumeration.ApiCodeEnum;
import com.spriteapp.booklibrary.enumeration.AutoSubEnum;
import com.spriteapp.booklibrary.enumeration.BookEnum;
import com.spriteapp.booklibrary.enumeration.ChapterEnum;
import com.spriteapp.booklibrary.enumeration.PageStyleEnum;
import com.spriteapp.booklibrary.enumeration.PayResultEnum;
import com.spriteapp.booklibrary.enumeration.UpdaterPayEnum;
import com.spriteapp.booklibrary.listener.DialogListener;
import com.spriteapp.booklibrary.listener.ReadDialogListener;
import com.spriteapp.booklibrary.model.AddBookModel;
import com.spriteapp.booklibrary.model.UpdateProgressModel;
import com.spriteapp.booklibrary.model.response.BookChapterResponse;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.model.response.SubscriberContent;
import com.spriteapp.booklibrary.ui.adapter.ChapterAdapter;
import com.spriteapp.booklibrary.ui.presenter.SubscriberContentPresenter;
import com.spriteapp.booklibrary.ui.view.SubscriberContentView;
import com.spriteapp.booklibrary.util.ActivityUtil;
import com.spriteapp.booklibrary.util.BookUtil;
import com.spriteapp.booklibrary.util.CollectionUtil;
import com.spriteapp.booklibrary.util.DialogUtil;
import com.spriteapp.booklibrary.util.NetworkUtil;
import com.spriteapp.booklibrary.util.ScreenUtil;
import com.spriteapp.booklibrary.util.SharedPreferencesUtil;
import com.spriteapp.booklibrary.util.StringUtil;
import com.spriteapp.booklibrary.util.TimeUtil;
import com.spriteapp.booklibrary.util.ToastUtil;
import com.spriteapp.booklibrary.widget.ReadBottomLayout;
import com.spriteapp.booklibrary.widget.ReadProgressLayout;
import com.spriteapp.booklibrary.widget.ReadProgressLayout.PositionCallback;
import com.spriteapp.booklibrary.widget.ReadRightTitleLayout;
import com.spriteapp.booklibrary.widget.ReadRightTitleLayout.ReadTitleListener;
import com.spriteapp.booklibrary.widget.TextSizeLayout;
import com.spriteapp.booklibrary.widget.readview.BaseReadView;
import com.spriteapp.booklibrary.widget.readview.BaseReadView.TouchPageListener;
import com.spriteapp.booklibrary.widget.readview.OnReadStateChangeListener;
import com.spriteapp.booklibrary.widget.readview.OverlappedWidget;
import com.spriteapp.booklibrary.widget.readview.PageWidget;
import de.greenrobot.event.EventBus;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadActivity extends TitleActivity implements SubscriberContentView {
    private static final int ANIMATION_TIME = 300;
    public static final String BOOK_DETAIL_TAG = "BookDetailTag";
    public static final String IS_FROM_NATIVE_STORE = "is_from_native_store";
    private static final String TAG = "ReadActivity";
    private boolean hasInitWidget;
    private boolean hasNotifyAdapter;
    private boolean isChangeTextSize;
    private boolean isClickPayChapter;
    private boolean isDismiss = true;
    private boolean isFromNativeStore;
    private boolean isNight;
    FrameLayout mBookContainer;
    private b mBookDb;
    private int mBookId;
    private int mBottomHeight;
    private ReadBottomLayout mBottomLayout;
    private ChapterAdapter mChapterAdapter;
    private c mChapterDb;
    private LinearLayout mChapterLayout;
    private List<BookChapterResponse> mChapterList;
    private ListView mChapterListView;
    private d mContentDb;
    private Context mContext;
    private int mCurrentChapter;
    private SimpleDateFormat mDateFormat;
    private ReadDialogListener mDialogListener = new ReadDialogListener() {
        public void clickCancelPay() {
            ReadActivity.this.toggleReadBar();
        }

        public void clickPayChapter() {
            int chapter_pay_type;
            ReadActivity.this.isClickPayChapter = true;
            if (ReadActivity.this.mQueryContent != null) {
                chapter_pay_type = ReadActivity.this.mQueryContent.getChapter_pay_type();
            } else {
                chapter_pay_type = 0;
            }
            if (chapter_pay_type == ChapterEnum.TO_PAY_PAGE.getCode()) {
                ActivityUtil.toWebViewActivity(ReadActivity.this.mContext, "http://s.hxdrive.net/user_recharge", true);
                return;
            }
            ReadActivity.this.showAutoSubDialog();
            ReadActivity.this.startRead = false;
            ReadActivity.this.mPresenter.getContent(ReadActivity.this.mBookId, ReadActivity.this.mCurrentChapter, AutoSubEnum.AUTO_SUB.getValue());
        }

        public void clickSetting() {
            ActivityUtil.toSettingActivity(ReadActivity.this.mContext);
        }
    };
    private View mDismissView;
    private DrawerLayout mDrawerLayout;
    private int mLoadChapterId;
    private LinearLayout mModeLayout;
    private BookDetailResponse mNewBookDetail;
    private BookDetailResponse mOldBookDetail;
    private AlertDialog mPayChapterDialog;
    private PositionCallback mPositionCallback = new PositionCallback() {
        public void sendPosition(int i) {
            if (ReadActivity.this.mWidget != null) {
                ReadActivity.this.mWidget.setSelectPage(i);
            }
        }
    };
    private SubscriberContentPresenter mPresenter;
    private a mProgressCallback = new a() {
        public void sendProgress(float f) {
            ReadActivity.this.mReadProgressLayout.setPercent(f);
        }
    };
    private LinearLayout mProgressLayout;
    private SubscriberContent mQueryContent;
    private OnReadStateChangeListener mReadListener = new OnReadStateChangeListener() {
        public void onChapterChanged(int i) {
            boolean z = true;
            ReadActivity.this.isClickPayChapter = false;
            ReadActivity.this.updateCurrentProgress(i);
            ReadActivity.this.mCurrentChapter = i;
            ReadActivity.this.mChapterDb.a(ReadActivity.this.mBookId, ReadActivity.this.mCurrentChapter);
            if (ReadActivity.this.mPayChapterDialog != null && ReadActivity.this.mPayChapterDialog.isShowing()) {
                ReadActivity.this.mPayChapterDialog.dismiss();
            }
            ReadActivity.this.mPayChapterDialog = null;
            if (ReadActivity.this.isChapterExists(i) && ReadActivity.this.mQueryContent.getChapter_need_buy() == ChapterEnum.NEED_BUY.getCode()) {
                ReadActivity.this.mContentDb.a(ReadActivity.this.mBookId, ReadActivity.this.mCurrentChapter, ChapterEnum.AUTO_BUY.getCode());
                boolean z2 = ReadActivity.this.mQueryContent.getAuto_sub() == AutoSubEnum.AUTO_SUB.getValue() && ReadActivity.this.mQueryContent.getChapter_pay_type() == ChapterEnum.AUTO_BUY.getCode();
                if (z2) {
                    ReadActivity.this.startRead = false;
                    ReadActivity.this.mPresenter.getContent(ReadActivity.this.mBookId, ReadActivity.this.mQueryContent.getChapter_id());
                } else {
                    ReadActivity.this.showPayChapterDialog();
                }
                ReadRightTitleLayout access$2900 = ReadActivity.this.mRightTitleLayout;
                if (z2) {
                    z = false;
                }
                access$2900.setBuyImageState(z);
            } else {
                ReadActivity.this.mRightTitleLayout.setBuyImageState(false);
            }
            ReadActivity.this.mReadProgressLayout.setTitle(ReadActivity.this.getCurrentChapterTitle(i));
            if (ReadActivity.this.mWidget != null) {
                ReadActivity.this.mReadProgressLayout.setCount(ReadActivity.this.mWidget.getChapterTotalPage());
                ReadActivity.this.mReadProgressLayout.setProgress(ReadActivity.this.mWidget.getCurrentProgress());
            }
            if (!CollectionUtil.isEmpty(ReadActivity.this.mChapterList)) {
                int size = ReadActivity.this.mChapterList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (((BookChapterResponse) ReadActivity.this.mChapterList.get(i2)).getChapter_id() == i) {
                        ((BookChapterResponse) ReadActivity.this.mChapterList.get(i2)).setChapterReadState(ChapterEnum.HAS_READ.getCode());
                        int i3 = i2 + 1;
                        if (i3 < ReadActivity.this.mChapterList.size()) {
                            ReadActivity.this.mLoadChapterId = ((BookChapterResponse) ReadActivity.this.mChapterList.get(i3)).getChapter_id();
                            if (!ReadActivity.this.isCurrentChapterExists(ReadActivity.this.mLoadChapterId)) {
                                ReadActivity.this.mPresenter.getContent(ReadActivity.this.mBookId, ReadActivity.this.mLoadChapterId, SharedPreferencesUtil.getInstance().getInt("hua_xi_book_auto_sub"), false);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                }
            }
        }

        public void onPageChanged(int i, int i2) {
            if (!ReadActivity.this.isChangeTextSize && !ReadActivity.this.isDismiss) {
                ReadActivity.this.hideReadBar();
            }
        }

        public void onLoadChapterFailure(int i) {
            ReadActivity.this.isClickPayChapter = false;
            if (!ReadActivity.this.isDismiss) {
                ReadActivity.this.hideReadBar();
            }
            ReadActivity.this.mCurrentChapter = i;
            ReadActivity.this.mChapterDb.a(ReadActivity.this.mBookId, ReadActivity.this.mCurrentChapter);
            ReadActivity.this.updateReadState();
            ReadActivity.this.mLoadChapterId = ReadActivity.this.mCurrentChapter;
            ReadActivity.this.startRead = false;
            ReadActivity.this.mPresenter.getContent(ReadActivity.this.mBookId, ReadActivity.this.mCurrentChapter);
        }

        public void onCenterClick() {
            ReadActivity.this.toggleReadBar();
        }

        public void onFlip() {
        }
    };
    private int mReadProgressHeight;
    private ReadProgressLayout mReadProgressLayout;
    private ReadReceiver mReadReceiver;
    private e mRecentBookDb;
    private ReadRightTitleLayout mRightTitleLayout;
    private View mShowView;
    private int mTempHeight;
    private LinearLayout mTextLayout;
    private com.spriteapp.booklibrary.b.b mTextSizeCallback = new com.spriteapp.booklibrary.b.b() {
        public void sendTextSize(int i) {
            if (ReadActivity.this.mWidget != null) {
                ReadActivity.this.mWidget.setFontSize(ScreenUtil.dpToPxInt((float) i));
                ReadActivity.this.mWidget.setChapterTotalPage();
                ReadActivity.this.mReadProgressLayout.setCount(ReadActivity.this.mWidget.getChapterTotalPage());
            }
        }
    };
    private TextSizeLayout mTextSizeLayout;
    private int mTitleHeight;
    private ReadTitleListener mTitleListener = new ReadTitleListener() {
        public void clickBuy() {
            ReadActivity.this.toggleReadBar();
            ReadActivity.this.showPayChapterDialog();
        }

        public void clickAddShelf() {
            ReadActivity.this.addToShelf();
        }

        public void clickHome() {
            ActivityUtil.toCommonActivity(ReadActivity.this.mContext, HomeActivity.class);
            ReadActivity.this.finish();
        }

        public void clickReward() {
        }

        public void clickMore() {
            BookDetailResponse access$1900 = ReadActivity.this.mNewBookDetail != null ? ReadActivity.this.mNewBookDetail : ReadActivity.this.mOldBookDetail != null ? ReadActivity.this.mOldBookDetail : null;
            if (access$1900 != null) {
                HuaXiSDK.getInstance().showShareDialog(ReadActivity.this.mContext, access$1900, ReadActivity.this.isNight);
            }
        }
    };
    private TouchPageListener mTouchPageListener = new TouchPageListener() {
        public void touchPage() {
            ReadActivity.this.isChangeTextSize = false;
        }
    };
    private BaseReadView mWidget;
    private boolean startRead = false;

    class ReadReceiver extends BroadcastReceiver {
        ReadReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ReadActivity.this.mWidget == null) {
                return;
            }
            if ("android.intent.action.BATTERY_CHANGED".equals(action)) {
                ReadActivity.this.mWidget.setBattery(100 - intent.getIntExtra(HistoryOpenHelper.COLUMN_LEVEL, 0));
            } else if ("android.intent.action.TIME_TICK".equals(action)) {
                ReadActivity.this.mWidget.setTime(ReadActivity.this.mDateFormat.format(new Date()));
            }
        }
    }

    public void initData() {
        EventBus.getDefault().register(this);
        this.mRightTitleLayout = new ReadRightTitleLayout(this);
        this.mRightLayout.addView(this.mRightTitleLayout);
        this.mRightTitleLayout.setTitleListener(this.mTitleListener);
        this.mContext = this;
        Intent intent = getIntent();
        BookDetailResponse bookDetailResponse = (BookDetailResponse) intent.getSerializableExtra(BOOK_DETAIL_TAG);
        this.isFromNativeStore = intent.getBooleanExtra(IS_FROM_NATIVE_STORE, false);
        this.mRightTitleLayout.setHomeViewState(this.isFromNativeStore);
        DialogUtil.setDialogListener(this.mDialogListener);
        this.mPresenter = new SubscriberContentPresenter();
        this.mPresenter.attachView((SubscriberContentView) this);
        this.mBookDb = new b(this);
        this.mChapterDb = new c(this);
        this.mContentDb = new d(this);
        this.mRecentBookDb = new e(this);
        if (bookDetailResponse != null) {
            this.mBookId = bookDetailResponse.getBook_id();
            this.mOldBookDetail = this.mBookDb.c(this.mBookId);
            this.mRightTitleLayout.setAddShelfViewState(BookUtil.isBookAddShelf(this.mOldBookDetail));
            this.mChapterList = this.mChapterDb.a(this.mBookId);
            int b = com.spriteapp.booklibrary.e.c.a().b(String.valueOf(this.mBookId), 0);
            this.mCurrentChapter = bookDetailResponse.getChapter_id();
            if (this.mCurrentChapter == 0) {
                this.mCurrentChapter = b;
            } else if (this.mCurrentChapter != b) {
                com.spriteapp.booklibrary.e.c.a().a(String.valueOf(this.mBookId), this.mCurrentChapter, 0, 0);
            }
            initChapterAdapter();
            openChapter();
            if (this.mOldBookDetail == null) {
                this.mPresenter.getBookDetail(this.mBookId);
            } else {
                this.mRecentBookDb.a(this.mOldBookDetail);
                if (TimeUtil.getTimeInterval((long) this.mOldBookDetail.getLast_update_book_datetime()) >= 3) {
                    this.mPresenter.getBookDetail(this.mBookId, false);
                } else {
                    judgeChapterNeedLoad();
                }
            }
            this.mTextSizeLayout.setPosition(SharedPreferencesUtil.getInstance().getInt("hua_xi_read_text_size_position"));
            setBatteryState();
        }
    }

    private void setBatteryState() {
        this.mDateFormat = new SimpleDateFormat("HH:mm");
        this.mReadReceiver = new ReadReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        intentFilter.addAction("android.intent.action.TIME_TICK");
        registerReceiver(this.mReadReceiver, intentFilter);
    }

    private void judgeChapterNeedLoad() {
        if (CollectionUtil.isEmpty(this.mChapterList) || this.mOldBookDetail == null) {
            this.mPresenter.getChapter(this.mBookId);
        } else if (this.mOldBookDetail.getBook_finish_flag() != BookEnum.BOOK_FINISH_TAG.getValue() && TimeUtil.getTimeInterval((long) this.mOldBookDetail.getLast_update_chapter_datetime()) >= 6) {
            this.mPresenter.getChapter(this.mBookId, false);
        }
    }

    private void openChapter() {
        if (!isCurrentChapterExists(this.mCurrentChapter) && this.mCurrentChapter != 0) {
            this.mPresenter.getContent(this.mBookId, this.mCurrentChapter);
            this.mLoadChapterId = this.mCurrentChapter;
        } else if (!CollectionUtil.isEmpty(this.mChapterList)) {
            if (this.mWidget == null) {
                BaseReadView overlappedWidget;
                if (SharedPreferencesUtil.getInstance().getInt("hua_xi_page_change_style") == PageStyleEnum.DEFAULT_STYLE.getValue()) {
                    overlappedWidget = new OverlappedWidget(this, String.valueOf(this.mBookId), this.mChapterList, this.mReadListener);
                } else {
                    overlappedWidget = new PageWidget(this, String.valueOf(this.mBookId), this.mChapterList, this.mReadListener);
                }
                this.mWidget = overlappedWidget;
                this.mBookContainer.addView(this.mWidget);
                this.isNight = SharedPreferencesUtil.getInstance().getBoolean("hua_xi_is_night_mode");
                if (this.mWidget.getPageFactory() != null) {
                    setMode();
                    this.mWidget.setProgressCallback(this.mProgressCallback);
                    this.mWidget.setTouchPageListener(this.mTouchPageListener);
                } else {
                    return;
                }
            }
            if (!this.startRead) {
                this.startRead = true;
                if (this.mWidget.isPrepared) {
                    this.mWidget.jumpToChapter(this.mCurrentChapter);
                    return;
                }
                this.mWidget.init();
                this.hasInitWidget = true;
                dismissDialog();
            }
        }
    }

    private void showPayChapterDialog() {
        if (this.mPayChapterDialog != null && this.mPayChapterDialog.isShowing()) {
            return;
        }
        if (this.mPayChapterDialog != null) {
            this.mPayChapterDialog.show();
            return;
        }
        String string;
        String string2;
        String string3;
        String format;
        int chapter_price = this.mQueryContent.getChapter_price();
        if (this.mQueryContent.getChapter_pay_type() == ChapterEnum.AUTO_BUY.getCode()) {
            string = getResources().getString(f.book_reader_pay_chapter);
            string2 = getResources().getString(f.book_reader_pay_prompt);
            string3 = getResources().getString(f.book_reader_cancel_text);
            format = String.format(getResources().getString(f.book_reader_buy_text), new Object[]{String.valueOf(chapter_price)});
        } else {
            string = getResources().getString(f.book_reader_recharge_title);
            string2 = getResources().getString(f.book_reader_recharge_message);
            string3 = getResources().getString(f.book_reader_recharge_negative_text);
            format = getResources().getString(f.book_reader_recharge_positive_text);
        }
        this.mPayChapterDialog = DialogUtil.getPayChapterDialog(this.mContext, string, string2, string3, format);
        this.mPayChapterDialog.show();
    }

    private void initChapterAdapter() {
        if (this.mChapterList == null) {
            this.mChapterList = new ArrayList();
        }
        this.mChapterAdapter = new ChapterAdapter(this, this.mChapterList);
        this.mChapterListView.setAdapter(this.mChapterAdapter);
        this.mChapterListView.setSelectionFromTop(BookUtil.getCurrentChapterPosition(this.mChapterList, this.mCurrentChapter), ScreenUtil.getScreenHeight() / 2);
    }

    public void configViews() {
        this.mTitleLayout.measure(0, 0);
        this.mTitleHeight = this.mTitleLayout.getMeasuredHeight();
        this.mBottomLayout.measure(0, 0);
        this.mBottomHeight = this.mBottomLayout.getMeasuredHeight();
        this.mTempHeight = this.mBottomHeight;
        this.mReadProgressLayout.measure(0, 0);
        this.mReadProgressHeight = this.mReadProgressLayout.getMeasuredHeight();
        this.mTitleLayout.setVisibility(8);
        getWindow().setFlags(1024, 1024);
        this.mBookContainer.setBackgroundColor(getResources().getColor(SharedPreferencesUtil.getInstance().getBoolean("hua_xi_is_night_mode") ? com.spriteapp.booklibrary.a.a.book_reader_read_night_background : com.spriteapp.booklibrary.a.a.book_reader_read_day_background));
    }

    public void findViewId() {
        super.findViewId();
        this.mBookContainer = (FrameLayout) findViewById(com.spriteapp.booklibrary.a.d.book_reader_read_container);
        this.mBottomLayout = (ReadBottomLayout) findViewById(com.spriteapp.booklibrary.a.d.book_reader_bottom_layout);
        this.mChapterListView = (ListView) findViewById(com.spriteapp.booklibrary.a.d.book_reader_catalog_list_view);
        this.mChapterLayout = (LinearLayout) findViewById(com.spriteapp.booklibrary.a.d.book_reader_chapter_layout);
        this.mProgressLayout = (LinearLayout) findViewById(com.spriteapp.booklibrary.a.d.book_reader_progress_layout);
        this.mModeLayout = (LinearLayout) findViewById(com.spriteapp.booklibrary.a.d.book_reader_mode_layout);
        this.mTextLayout = (LinearLayout) findViewById(com.spriteapp.booklibrary.a.d.book_reader_text_layout);
        this.mDrawerLayout = (DrawerLayout) findViewById(com.spriteapp.booklibrary.a.d.book_reader_drawer_layout);
        this.mReadProgressLayout = (ReadProgressLayout) findViewById(com.spriteapp.booklibrary.a.d.book_reader_read_progress_layout);
        this.mTextSizeLayout = (TextSizeLayout) findViewById(com.spriteapp.booklibrary.a.d.book_reader_text_size_layout);
        this.mShowView = this.mBottomLayout;
        this.mDismissView = this.mBottomLayout;
        this.mTextSizeLayout.setCallBack(this.mTextSizeCallback);
        this.mReadProgressLayout.setCallback(this.mPositionCallback);
        setListener();
    }

    private boolean isCurrentChapterExists(int i) {
        SubscriberContent a = this.mContentDb.a(this.mBookId, i);
        if (a != null) {
            a.setAuto_sub(SharedPreferencesUtil.getInstance().getInt("hua_xi_book_auto_sub"));
        }
        return a != null;
    }

    private boolean isChapterExists(int i) {
        this.mQueryContent = this.mContentDb.a(this.mBookId, i);
        if (this.mQueryContent != null) {
            this.mQueryContent.setAuto_sub(SharedPreferencesUtil.getInstance().getInt("hua_xi_book_auto_sub"));
        }
        return this.mQueryContent != null;
    }

    private void setListener() {
        this.mChapterLayout.setOnClickListener(this);
        this.mProgressLayout.setOnClickListener(this);
        this.mModeLayout.setOnClickListener(this);
        this.mTextLayout.setOnClickListener(this);
        this.mChapterListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ReadActivity.this.mCurrentChapter = ((BookChapterResponse) ReadActivity.this.mChapterList.get(i)).getChapter_id();
                ReadActivity.this.mChapterAdapter.setCurrentChapter(ReadActivity.this.mCurrentChapter);
                ReadActivity.this.mChapterDb.a(ReadActivity.this.mBookId, ReadActivity.this.mCurrentChapter);
                ((BookChapterResponse) ReadActivity.this.mChapterList.get(i)).setChapterReadState(ChapterEnum.HAS_READ.getCode());
                ReadActivity.this.mChapterAdapter.notifyDataSetChanged();
                ReadActivity.this.startRead = false;
                ReadActivity.this.openChapter();
                ReadActivity.this.mDrawerLayout.closeDrawer(ReadActivity.this.mChapterListView);
            }
        });
        this.mDrawerLayout.addDrawerListener(new DrawerListener() {
            public void onDrawerSlide(View view, float f) {
                if (!ReadActivity.this.isDismiss) {
                    ReadActivity.this.hideReadBar();
                }
                if (!ReadActivity.this.hasNotifyAdapter) {
                    ReadActivity.this.mCurrentChapter = com.spriteapp.booklibrary.e.c.a().b(String.valueOf(ReadActivity.this.mBookId), ReadActivity.this.mCurrentChapter);
                    if (ReadActivity.this.mCurrentChapter != ReadActivity.this.mChapterAdapter.getCurrentChapter()) {
                        ReadActivity.this.mChapterAdapter.setCurrentChapter(ReadActivity.this.mCurrentChapter);
                        ReadActivity.this.mChapterDb.a(ReadActivity.this.mBookId, ReadActivity.this.mCurrentChapter);
                        ReadActivity.this.updateReadState();
                        ReadActivity.this.mChapterAdapter.notifyDataSetChanged();
                    }
                    ReadActivity.this.hasNotifyAdapter = true;
                }
            }

            public void onDrawerOpened(View view) {
            }

            public void onDrawerClosed(View view) {
                ReadActivity.this.hasNotifyAdapter = false;
            }

            public void onDrawerStateChanged(int i) {
            }
        });
    }

    public void addContentView() {
        this.mContainerLayout.addView(getLayoutInflater().inflate(com.spriteapp.booklibrary.a.e.book_reader_activity_read, null), -1, -1);
    }

    public void onError(Throwable th) {
    }

    public void onClick(View view) {
        if (view == this.mChapterLayout) {
            this.mShowView = this.mBottomLayout;
            this.mDismissView = this.mBottomLayout;
            this.mDrawerLayout.openDrawer(this.mChapterListView);
        } else if (view == this.mProgressLayout) {
            if (this.mWidget != null) {
                this.mReadProgressLayout.setProgress(this.mWidget.getCurrentProgress());
            }
            this.mReadProgressLayout.setVisibility(0);
            this.mShowView = this.mReadProgressLayout;
            this.mDismissView = this.mBottomLayout;
            doBottomAnimation(this.mShowView, true, true);
            doBottomAnimation(this.mDismissView, false);
            this.mDismissView = this.mReadProgressLayout;
        } else if (view == this.mModeLayout) {
            changeMode();
        } else if (view == this.mTextLayout) {
            this.mTextSizeLayout.setVisibility(0);
            this.mShowView = this.mTextSizeLayout;
            this.mDismissView = this.mBottomLayout;
            doBottomAnimation(this.mDismissView, false);
            doBottomAnimation(this.mShowView, true);
            this.mDismissView = this.mTextSizeLayout;
        } else if (view == this.mLeftLayout) {
            showAddShelfPrompt();
        }
    }

    private void showAddShelfPrompt() {
        if (this.mBookDb == null) {
            finish();
            return;
        }
        BookDetailResponse c = this.mBookDb.c(this.mBookId);
        if (c == null || BookUtil.isBookAddShelf(c)) {
            finish();
        } else {
            DialogUtil.showCommonDialog(this, getResources().getString(f.book_reader_sure_to_add_shelf), new DialogListener() {
                public void clickCancel() {
                    ReadActivity.this.finish();
                }

                public void clickSure() {
                    ReadActivity.this.addToShelf();
                    ReadActivity.this.finish();
                }
            });
        }
    }

    public void onBackPressed() {
        if (this.mOldBookDetail == null && this.mNewBookDetail == null) {
            super.onBackPressed();
        } else {
            showAddShelfPrompt();
        }
    }

    private void changeMode() {
        if (this.mWidget != null) {
            this.isNight = !this.isNight;
            SharedPreferencesUtil.getInstance().putBoolean("hua_xi_is_night_mode", this.isNight);
            setMode();
        }
    }

    private void setMode() {
        this.mTextSizeLayout.changeMode(this.isNight);
        this.mReadProgressLayout.changeMode(this.isNight);
        this.mBottomLayout.changeMode(this.isNight);
        this.mRightTitleLayout.changeMode(this.isNight);
        this.mWidget.setTheme(this.isNight ? 1 : 0);
        this.mWidget.setTextColor(getResources().getColor(this.isNight ? com.spriteapp.booklibrary.a.a.book_reader_reader_text_night_color : com.spriteapp.booklibrary.a.a.book_reader_reader_text_color), getResources().getColor(this.isNight ? com.spriteapp.booklibrary.a.a.book_reader_read_title_night_color : com.spriteapp.booklibrary.a.a.book_reader_read_title_color));
        this.mBackImageView.setImageResource(this.isNight ? com.spriteapp.booklibrary.a.c.book_reader_title_back_night_selector : com.spriteapp.booklibrary.a.c.book_reader_title_back_selector);
        this.mTitleLayout.setBackgroundResource(this.isNight ? com.spriteapp.booklibrary.a.a.book_reader_read_bottom_night_background : com.spriteapp.booklibrary.a.a.book_reader_white);
        this.mLineView.setBackgroundResource(this.isNight ? com.spriteapp.booklibrary.a.a.book_reader_read_bottom_night_background : com.spriteapp.booklibrary.a.a.book_reader_divide_line_color);
        this.mChapterListView.setBackgroundResource(this.isNight ? com.spriteapp.booklibrary.a.a.book_reader_chapter_night_background : com.spriteapp.booklibrary.a.a.book_reader_white);
        this.mChapterListView.setSelector(this.isNight ? com.spriteapp.booklibrary.a.c.book_reader_common_press_night_selector : com.spriteapp.booklibrary.a.c.book_reader_common_press_selector);
        if (this.mChapterAdapter != null) {
            this.mChapterAdapter.setNight(this.isNight);
            this.mChapterAdapter.notifyDataSetChanged();
        }
    }

    public void setData(Base<SubscriberContent> base) {
        String message = base.getMessage();
        int code = base.getCode();
        SubscriberContent subscriberContent = (SubscriberContent) base.getData();
        if (subscriberContent == null) {
            ToastUtil.showSingleToast(message);
            return;
        }
        message = subscriberContent.getChapter_content_key();
        String chapter_content = subscriberContent.getChapter_content();
        if (StringUtil.isEmpty(message)) {
            ToastUtil.showSingleToast("key为空" + subscriberContent.getChapter_id());
        } else if (StringUtil.isEmpty(chapter_content)) {
            ToastUtil.showSingleToast("content为空" + subscriberContent.getChapter_id());
        } else {
            judgeNeedUpdatePayPage(subscriberContent);
            if (code == ApiCodeEnum.SUCCESS.getValue()) {
                subscriberContent.setChapter_need_buy(ChapterEnum.DO_NOT_NEED_BUY.getCode());
                if (this.isClickPayChapter) {
                    ToastUtil.showSingleToast(f.book_reader_buy_successful_text);
                    this.isClickPayChapter = false;
                }
            } else {
                if (code == ChapterEnum.BALANCE_SHORT.getCode()) {
                    subscriberContent.setChapter_pay_type(ChapterEnum.TO_PAY_PAGE.getCode());
                } else if (code == ChapterEnum.UN_SUBSCRIBER.getCode()) {
                    subscriberContent.setChapter_pay_type(ChapterEnum.AUTO_BUY.getCode());
                }
                subscriberContent.setChapter_need_buy(ChapterEnum.NEED_BUY.getCode());
            }
            if (isCurrentChapterExists(subscriberContent.getChapter_id())) {
                this.mContentDb.a(this.mBookId, subscriberContent.getChapter_id(), subscriberContent);
            } else {
                this.mContentDb.a(subscriberContent);
            }
            if (NetworkUtil.isAvailable(this.mContext)) {
                updateCurrentProgress(this.mCurrentChapter);
            }
            openChapter();
        }
    }

    private void judgeNeedUpdatePayPage(SubscriberContent subscriberContent) {
        if (subscriberContent != null) {
            int used_real_point = subscriberContent.getUsed_real_point();
            int used_false_point = subscriberContent.getUsed_false_point();
            if (used_real_point > 0 || used_false_point > 0) {
                EventBus.getDefault().post(UpdaterPayEnum.UPDATE_PAY_RESULT);
            }
        }
    }

    private void addToShelf() {
        AddBookModel addBookModel = new AddBookModel();
        addBookModel.setBookId(this.mBookId);
        addBookModel.setChapterId(this.mCurrentChapter);
        EventBus.getDefault().post(addBookModel);
    }

    private void updateReadState() {
        ((BookChapterResponse) this.mChapterList.get(BookUtil.getCurrentChapterPosition(this.mChapterList, this.mCurrentChapter))).setChapterReadState(ChapterEnum.HAS_READ.getCode());
    }

    private void updateCurrentProgress(int i) {
        if (!CollectionUtil.isEmpty(this.mChapterList)) {
            UpdateProgressModel updateProgressModel = new UpdateProgressModel();
            updateProgressModel.setBookId(this.mBookId);
            updateProgressModel.setChapterId(i);
            updateProgressModel.setChapterIndex(getChapterIndex(i));
            updateProgressModel.setChapterTotal(this.mChapterList.size());
            EventBus.getDefault().post(updateProgressModel);
        }
    }

    private int getChapterIndex(int i) {
        for (int i2 = 0; i2 < this.mChapterList.size(); i2++) {
            if (((BookChapterResponse) this.mChapterList.get(i2)).getChapter_id() == i) {
                return i2 + 1;
            }
        }
        return 0;
    }

    public void onEventMainThread(PayResultEnum payResultEnum) {
        if (payResultEnum == PayResultEnum.SUCCESS) {
            this.startRead = false;
            showAutoSubDialog();
            this.mPresenter.getContent(this.mBookId, this.mCurrentChapter, AutoSubEnum.AUTO_SUB.getValue());
        }
    }

    public void showAutoSubDialog() {
        if (SharedPreferencesUtil.getInstance().getInt("hua_xi_book_auto_sub") != AutoSubEnum.AUTO_SUB.getValue() && !SharedPreferencesUtil.getInstance().getBoolean("hua_xi_has_show_auto_buy_prompt_dialog")) {
            DialogUtil.getAutoSubDialog(this).show();
            SharedPreferencesUtil.getInstance().putBoolean("hua_xi_has_show_auto_buy_prompt_dialog", true);
        }
    }

    private String getCurrentChapterTitle(int i) {
        String str = "";
        if (CollectionUtil.isEmpty(this.mChapterList)) {
            return str;
        }
        String chapter_title;
        for (BookChapterResponse bookChapterResponse : this.mChapterList) {
            if (bookChapterResponse.getChapter_id() == i) {
                chapter_title = bookChapterResponse.getChapter_title();
                break;
            }
        }
        chapter_title = str;
        return chapter_title;
    }

    public void showNetWorkProgress() {
        showDialog();
    }

    public void disMissProgress() {
        if (this.hasInitWidget) {
            dismissDialog();
        }
    }

    public Context getMyContext() {
        return this;
    }

    private synchronized void hideReadBar() {
        dismissView(this.mTitleLayout, this.mDismissView);
        hideStatusBar();
    }

    private synchronized void showReadBar() {
        if (this.mTitleLayout.getVisibility() == 8) {
            this.mTitleLayout.setVisibility(0);
        }
        if (this.mBottomLayout.getVisibility() == 8) {
            this.mBottomLayout.setVisibility(0);
        }
        showView(this.mTitleLayout, this.mBottomLayout);
    }

    private synchronized void toggleReadBar() {
        if (this.isDismiss) {
            showReadBar();
        } else {
            hideReadBar();
        }
    }

    private void dismissView(View view, View view2) {
        boolean z = true;
        this.isDismiss = true;
        if (view != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) (-this.mTitleHeight)});
            ofFloat.setDuration(300);
            ofFloat.start();
        }
        if (view2 != null) {
            if (view2 != this.mReadProgressLayout) {
                z = false;
            }
            doBottomAnimation(view2, false, z);
        }
        this.mShowView = this.mBottomLayout;
    }

    private void showView(View view, View view2) {
        this.isDismiss = false;
        if (view != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) (-this.mTitleHeight), 0.0f});
            ofFloat.setDuration(300);
            ofFloat.start();
        }
        if (view2 != null) {
            doBottomAnimation(view2, true);
        }
        this.mDismissView = this.mBottomLayout;
    }

    private void doBottomAnimation(View view, boolean z) {
        doBottomAnimation(view, z, false);
    }

    private void doBottomAnimation(View view, boolean z, boolean z2) {
        float f;
        float f2 = 0.0f;
        boolean z3 = z && view == this.mTextSizeLayout;
        this.isChangeTextSize = z3;
        this.mBottomHeight = z2 ? this.mReadProgressHeight : this.mTempHeight;
        Property property = View.TRANSLATION_Y;
        float[] fArr = new float[2];
        if (z) {
            f = (float) this.mBottomHeight;
        } else {
            f = 0.0f;
        }
        fArr[0] = f;
        if (!z) {
            f2 = (float) this.mBottomHeight;
        }
        fArr[1] = f2;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, property, fArr);
        ofFloat.setDuration(300);
        ofFloat.start();
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        try {
            unregisterReceiver(this.mReadReceiver);
        } catch (Exception e) {
        }
        if (this.mPresenter != null) {
            this.mPresenter.detachView();
        }
    }

    public void setChapter(List<BookChapterResponse> list) {
        if (CollectionUtil.isEmpty(list)) {
            ToastUtil.showSingleToast("章节列表为空" + this.mBookId);
            return;
        }
        if (this.mChapterList == null) {
            this.mChapterList = new ArrayList();
        }
        this.mChapterList.clear();
        this.mChapterList.addAll(list);
        saveChapterToDb();
        this.mChapterAdapter.notifyDataSetChanged();
        if (!(this.mOldBookDetail == null && this.mNewBookDetail == null)) {
            this.mBookDb.b(this.mBookId);
        }
        if (this.mCurrentChapter == 0) {
            this.mCurrentChapter = ((BookChapterResponse) this.mChapterList.get(0)).getChapter_id();
            this.mChapterDb.a(this.mBookId, this.mCurrentChapter);
            ((BookChapterResponse) this.mChapterList.get(0)).setChapterReadState(ChapterEnum.HAS_READ.getCode());
            com.spriteapp.booklibrary.e.c.a = this.mCurrentChapter;
        }
        if (this.mWidget == null) {
            this.mPresenter.getContent(this.mBookId, this.mCurrentChapter);
        }
    }

    private void saveChapterToDb() {
        Observable.just(Integer.valueOf(1)).map(new Function<Integer, Object>() {
            public Integer apply(Integer num) {
                ReadActivity.this.mChapterDb.a(ReadActivity.this.mChapterList, ReadActivity.this.mBookId);
                return null;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Object>() {
            Disposable disposable;

            public void onComplete() {
                this.disposable.dispose();
            }

            public void onSubscribe(@NonNull Disposable disposable) {
                this.disposable = disposable;
            }

            public void onNext(@NonNull Object obj) {
            }

            public void onError(Throwable th) {
                this.disposable.dispose();
            }
        });
    }

    public void toChannelLogin() {
        if (this.mCurrentChapter == this.mLoadChapterId) {
            if (this.mWidget != null) {
                this.mWidget.setCurrentChapter();
            }
            HuaXiSDK.getInstance().toLoginPage(this.mContext);
        }
    }

    public void setBookDetail(BookDetailResponse bookDetailResponse) {
        this.mNewBookDetail = bookDetailResponse;
        judgeChapterNeedLoad();
        if (this.mOldBookDetail != null) {
            this.mBookDb.a(bookDetailResponse, this.mOldBookDetail.getBook_add_shelf());
            return;
        }
        this.mBookDb.a(bookDetailResponse, BookEnum.NOT_ADD_SHELF);
        this.mRecentBookDb.a(bookDetailResponse);
    }
}
