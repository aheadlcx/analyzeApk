package cn.v6.sixrooms.room.gift;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import cn.v6.sixrooms.room.gift.GiftBoxDialog.WantGift;
import cn.v6.sixrooms.room.gift.GiftTypes.WrapGiftType;
import java.util.List;

public class GiftBoxPageAdapter extends PagerAdapter {
    private Context mContext;
    private int mCurrentRoomType;
    private List<WrapGiftType> mDisplayWrapTypeList;
    private SparseArray<View> mHelpList = new SparseArray();
    private boolean mIsSuperGirlRoom;
    private WantGift mWantGift;

    public GiftBoxPageAdapter(Context context, int i, boolean z, List<WrapGiftType> list) {
        this.mContext = context;
        this.mCurrentRoomType = i;
        this.mIsSuperGirlRoom = z;
        this.mDisplayWrapTypeList = list;
        int i2 = 0;
        for (WrapGiftType giftBoxPageHelp : this.mDisplayWrapTypeList) {
            GiftBoxPageHelp giftBoxPageHelp2 = new GiftBoxPageHelp(this.mContext, this.mCurrentRoomType, z, i2, giftBoxPageHelp);
            View view = giftBoxPageHelp2.getView();
            view.setTag(giftBoxPageHelp2);
            this.mHelpList.put(i2, view);
            i2++;
        }
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = (View) this.mHelpList.get(i);
        if (view != null) {
            GiftBoxPageHelp giftBoxPageHelp = (GiftBoxPageHelp) view.getTag();
            if (this.mWantGift != null && this.mWantGift.typePos == i) {
                giftBoxPageHelp.setSelectGift(this.mWantGift);
                this.mWantGift = null;
            }
        } else {
            GiftBoxPageHelp giftBoxPageHelp2 = new GiftBoxPageHelp(this.mContext, this.mCurrentRoomType, this.mIsSuperGirlRoom, i, (WrapGiftType) this.mDisplayWrapTypeList.get(i));
            View view2 = giftBoxPageHelp2.getView();
            view2.setTag(giftBoxPageHelp2);
            this.mHelpList.put(i, view2);
            view = view2;
        }
        viewGroup.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public int getCount() {
        return this.mDisplayWrapTypeList.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public CharSequence getPageTitle(int i) {
        WrapGiftType wrapGiftType = (WrapGiftType) this.mDisplayWrapTypeList.get(i);
        if (wrapGiftType != null) {
            return wrapGiftType.getTagName();
        }
        return "";
    }

    public int getItemPosition(Object obj) {
        return -2;
    }

    public void setSelectedGift(WantGift wantGift) {
        this.mWantGift = wantGift;
        notifyDataSetChanged();
    }
}
