package cn.v6.sixrooms.room.gift;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.event.EventManager;
import cn.v6.sixrooms.event.GiftBoxEvent;
import cn.v6.sixrooms.room.gift.GiftBoxDialog.WantGift;
import cn.v6.sixrooms.room.gift.GiftBoxListViewAdapter.ViewHolder;
import cn.v6.sixrooms.room.gift.GiftTypes.WrapGiftType;
import cn.v6.sixrooms.utils.DisPlayUtil;
import java.util.List;

public class GiftBoxPageHelp {
    private static final String TAG = GiftBoxPageHelp.class.getSimpleName();
    private View contentView;
    private int giftNumColumns;
    private boolean isLandscape;
    public GiftBoxListViewAdapter mAdapter;
    private Context mContext;
    private int mCurrentRoomType;
    private GiftBoxEvent mGiftBoxEvent = new GiftBoxEvent();
    public final List<Gift> mGiftList;
    public GridView mGridView;
    private Handler mHandler = new Handler();
    private boolean mIsSuperGirlRoom;
    public int mViewPagePosition;
    private WrapGiftType mWrapGiftType;

    public GiftBoxPageHelp(Context context, int i, boolean z, int i2, WrapGiftType wrapGiftType) {
        this.mContext = context;
        this.mWrapGiftType = wrapGiftType;
        this.mViewPagePosition = i2;
        this.mCurrentRoomType = i;
        this.mIsSuperGirlRoom = z;
        this.mGiftList = wrapGiftType.getTypeGiftList();
        if (DisPlayUtil.isLandscape(this.mContext)) {
            this.isLandscape = true;
            this.giftNumColumns = 3;
            return;
        }
        this.isLandscape = false;
        this.giftNumColumns = 5;
    }

    public View getView() {
        if (this.contentView != null) {
            return this.contentView;
        }
        this.contentView = View.inflate(this.mContext, R.layout.page_item_giftbox, null);
        this.contentView.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            public boolean onPreDraw() {
                int measuredHeight = GiftBoxPageHelp.this.contentView.getMeasuredHeight();
                int i = -1;
                if (GiftBoxPageHelp.this.mIsSuperGirlRoom) {
                    i = GiftBoxPageHelp.this.contentView.getMeasuredWidth() / GiftBoxPageHelp.this.giftNumColumns;
                }
                if (GiftBoxPageHelp.this.mAdapter != null) {
                    GiftBoxPageHelp.this.mAdapter.setViewSize(measuredHeight, i);
                }
                GiftBoxPageHelp.this.contentView.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
        this.contentView.setTag(R.id.gv_page_item_giftbox, this);
        init();
        return this.contentView;
    }

    private void init() {
        this.mGridView = (GridView) this.contentView.findViewById(R.id.gv_page_item_giftbox);
        this.mGridView.setNumColumns(this.giftNumColumns);
        this.mGridView.setVerticalScrollBarEnabled(false);
        this.mAdapter = new GiftBoxListViewAdapter(this.mContext, this.mGiftList, this.giftNumColumns, this.mIsSuperGirlRoom);
        this.mGridView.setAdapter(this.mAdapter);
        this.mGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                SelectGiftInfo selectGiftInfo;
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                if (viewHolder.gift.isSelected()) {
                    selectGiftInfo = null;
                } else {
                    Drawable drawable;
                    viewHolder.gift.setSelected(true);
                    RelativeLayout relativeLayout = viewHolder.bg;
                    if (GiftBoxPageHelp.this.mIsSuperGirlRoom) {
                        drawable = GiftBoxPageHelp.this.mContext.getResources().getDrawable(R.drawable.giftbox_gift_supergirl_select_bg);
                    } else {
                        drawable = GiftBoxPageHelp.this.mContext.getResources().getDrawable(R.drawable.giftbox_gift_select_bg);
                    }
                    relativeLayout.setBackgroundDrawable(drawable);
                    viewHolder.bgTag.setVisibility(0);
                    SelectGiftInfo selectGiftInfo2 = new SelectGiftInfo();
                    selectGiftInfo2.selectedGiftId = viewHolder.gift.getId();
                    selectGiftInfo2.gift = viewHolder.gift;
                    selectGiftInfo2.selectedViewHelp = GiftBoxPageHelp.this;
                    selectGiftInfo2.view = view;
                    selectGiftInfo = selectGiftInfo2;
                }
                GiftBoxSelectEvent giftBoxSelectEvent = new GiftBoxSelectEvent();
                giftBoxSelectEvent.selectGiftInfo = selectGiftInfo;
                EventManager.getDefault().nodifyObservers(giftBoxSelectEvent, "");
            }
        });
        if (1 == this.mCurrentRoomType || 2 == this.mCurrentRoomType || 3 == this.mCurrentRoomType) {
            this.mGridView.setOnScrollListener(new OnScrollListener() {
                public void onScrollStateChanged(AbsListView absListView, int i) {
                    if (i == 0) {
                        GiftBoxPageHelp.this.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                GiftBoxPageHelp.this.mGiftBoxEvent.isUpdateChatList = true;
                                EventManager.getDefault().nodifyObservers(GiftBoxPageHelp.this.mGiftBoxEvent, "1");
                            }
                        }, 500);
                        return;
                    }
                    GiftBoxPageHelp.this.mGiftBoxEvent.isUpdateChatList = false;
                    GiftBoxPageHelp.this.mHandler.removeCallbacksAndMessages(null);
                    EventManager.getDefault().nodifyObservers(GiftBoxPageHelp.this.mGiftBoxEvent, "1");
                }

                public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                }
            });
        }
    }

    public void recoveryGiftState(String str) {
        if (!TextUtils.isEmpty(str) && this.mGiftList != null) {
            for (Gift gift : this.mGiftList) {
                if (str.equals(gift.getId())) {
                    gift.setSelected(false);
                    if (this.mAdapter != null) {
                        this.mAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }

    public void setSelectGift(final WantGift wantGift) {
        if (wantGift != null) {
            if (this.mAdapter != null) {
                this.mAdapter.setSelectGift(this, wantGift);
            }
            if (this.mGridView != null) {
                this.mGridView.post(new Runnable() {
                    public void run() {
                        GiftBoxPageHelp.this.mGridView.setSelection(wantGift.giftPos);
                    }
                });
            }
        }
    }
}
