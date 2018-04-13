package cn.v6.sixrooms.room.gift;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.event.EventManager;
import cn.v6.sixrooms.room.gift.GiftBoxDialog.WantGift;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.DisPlayUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class GiftBoxListViewAdapter extends BaseListAdapter<Gift> {
    private final boolean isLandscape = DisPlayUtil.isLandscape(this.mContext);
    private int itemWidth;
    private Context mContext;
    private GiftBoxPageHelp mGiftBoxPageHelp;
    private List<Gift> mGiftList;
    private int mGiftNumColumns;
    private boolean mIsSuperGirlRoom;
    private final float mScreenDensity = DensityUtil.getScreenDensity();
    private WantGift mWantGift;
    private int viewPageHeight;

    public class ViewHolder {
        public RelativeLayout bg;
        public ImageView bgTag;
        public Gift gift;
        public SimpleDraweeView giftImage;
        public TextView giftName;
        public TextView giftPrice;
    }

    public GiftBoxListViewAdapter(Context context, List<Gift> list, int i, boolean z) {
        super(context, list);
        this.mContext = context;
        this.mGiftList = list;
        this.mGiftNumColumns = i;
        this.mIsSuperGirlRoom = z;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Gift gift = (Gift) this.mGiftList.get(i);
        if (this.mWantGift != null && this.mWantGift.giftId.equals(gift.getId())) {
            gift.setSelected(true);
            this.mWantGift = null;
            SelectGiftInfo selectGiftInfo = new SelectGiftInfo();
            selectGiftInfo.selectedGiftId = gift.getId();
            selectGiftInfo.gift = gift;
            selectGiftInfo.selectedViewHelp = this.mGiftBoxPageHelp;
            GiftBoxSelectEvent giftBoxSelectEvent = new GiftBoxSelectEvent();
            giftBoxSelectEvent.selectGiftInfo = selectGiftInfo;
            EventManager.getDefault().nodifyObservers(giftBoxSelectEvent, "");
        }
        if (view == null) {
            view = getLayoutInflater().inflate(R.layout.abslist_item_giftbox, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.giftName = (TextView) view.findViewById(R.id.tv_gift_name_boxgift);
            viewHolder.giftPrice = (TextView) view.findViewById(R.id.tv_gift_price_boxgift);
            viewHolder.giftImage = (SimpleDraweeView) view.findViewById(R.id.iv_gift_image_boxgift);
            viewHolder.bg = (RelativeLayout) view.findViewById(R.id.rl_selected_bg_giftbox);
            viewHolder.bgTag = (ImageView) view.findViewById(R.id.iv_selected_giftbox);
            if (this.mIsSuperGirlRoom) {
                viewHolder.bgTag.setBackgroundResource(R.drawable.choose_gift_grad_bg_selected_supergirl_tag);
            }
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (gift != null) {
            viewHolder.giftName.setText(gift.getTitle());
            viewHolder.giftPrice.setText(gift.getPrice() + "币");
            if (gift.getType().equals("11")) {
                viewHolder.giftPrice.setText(gift.getNum() + "个");
            }
            String img = gift.getMpic() != null ? gift.getMpic().getImg() : "";
            if (TextUtils.isEmpty(img)) {
                viewHolder.giftImage.setVisibility(4);
            } else {
                viewHolder.giftImage.setVisibility(0);
                viewHolder.giftImage.setImageURI(img);
            }
        }
        if (gift.isSelected()) {
            Drawable drawable;
            RelativeLayout relativeLayout = viewHolder.bg;
            if (this.mIsSuperGirlRoom) {
                drawable = this.mContext.getResources().getDrawable(R.drawable.giftbox_gift_supergirl_select_bg);
            } else {
                drawable = this.mContext.getResources().getDrawable(R.drawable.giftbox_gift_select_bg);
            }
            relativeLayout.setBackgroundDrawable(drawable);
            viewHolder.bgTag.setVisibility(0);
        } else {
            viewHolder.bg.setBackgroundColor(0);
            viewHolder.bgTag.setVisibility(8);
        }
        viewHolder.gift = gift;
        if (this.isLandscape) {
            view.setLayoutParams(new LayoutParams(-1, this.viewPageHeight / 2));
        }
        setItemPadding(viewHolder);
        return view;
    }

    private void setItemPadding(ViewHolder viewHolder) {
        int dip2px;
        if (this.isLandscape) {
            if (this.mScreenDensity < 2.0f) {
                if (this.mIsSuperGirlRoom) {
                    dip2px = DensityUtil.dip2px(2.0f);
                } else {
                    int dip2px2 = DensityUtil.dip2px(3.0f);
                    ((RelativeLayout.LayoutParams) viewHolder.giftImage.getLayoutParams()).topMargin = dip2px2;
                    dip2px = dip2px2;
                }
            } else if (!this.mIsSuperGirlRoom) {
                dip2px = DensityUtil.dip2px(8.0f);
            }
            viewHolder.bg.setPadding(0, dip2px, 0, dip2px);
        }
        dip2px = DensityUtil.dip2px(7.0f);
        viewHolder.bg.setPadding(0, dip2px, 0, dip2px);
    }

    public void setSelectGift(GiftBoxPageHelp giftBoxPageHelp, WantGift wantGift) {
        this.mWantGift = wantGift;
        this.mGiftBoxPageHelp = giftBoxPageHelp;
        notifyDataSetChanged();
    }

    public void setViewSize(int i, int i2) {
        if (this.viewPageHeight != i || this.itemWidth != i2) {
            this.viewPageHeight = i;
            if (this.mIsSuperGirlRoom) {
                this.itemWidth = i2;
            }
            notifyDataSetChanged();
        }
    }
}
