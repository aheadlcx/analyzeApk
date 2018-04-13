package qsbk.app.live.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.SimpleDialog.Builder;
import qsbk.app.live.R;
import qsbk.app.live.model.FamilyMemberData;
import qsbk.app.live.ui.helper.LevelHelper;

public class FamilyAllMemberAdapter extends Adapter<ViewHolder> {
    protected List<FamilyMemberData> a = new ArrayList();
    protected Context b;
    protected long c;
    protected int d;
    private OnItemClickListener e;

    public static class ItemViewHolder extends ViewHolder {
        public SimpleDraweeView ivAvatar;
        public ImageView ivMore;
        public TextView tvContribute;
        public TextView tvLevel;
        public TextView tvUserName;

        public ItemViewHolder(View view) {
            super(view);
            this.ivAvatar = (SimpleDraweeView) view.findViewById(R.id.avatar);
            this.tvUserName = (TextView) view.findViewById(R.id.tv_username);
            this.tvContribute = (TextView) view.findViewById(R.id.tv_contribute);
            this.tvLevel = (TextView) view.findViewById(R.id.tv_user_lv);
            this.ivMore = (ImageView) view.findViewById(R.id.iv_more);
        }
    }

    public FamilyAllMemberAdapter(Context context, List<FamilyMemberData> list, long j) {
        this.b = context;
        this.a = list;
        this.c = j;
    }

    public void setIdentity(int i) {
        this.d = i;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new ItemViewHolder(LayoutInflater.from(this.b).inflate(R.layout.item_family_head, viewGroup, false));
        }
        return new ItemViewHolder(LayoutInflater.from(this.b).inflate(R.layout.item_family_member, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        if (i >= 0 && i < this.a.size()) {
            FamilyMemberData familyMemberData = (FamilyMemberData) this.a.get(i);
            if (familyMemberData != null) {
                AppUtils.getInstance().getImageProvider().loadAvatar(itemViewHolder.ivAvatar, familyMemberData.a);
                LevelHelper.setLevelText(itemViewHolder.tvLevel, familyMemberData.l);
                itemViewHolder.tvUserName.setText(familyMemberData.n);
                itemViewHolder.tvContribute.setText(this.b.getResources().getString(R.string.family_fame, new Object[]{familyMemberData.f + ""}));
                if (familyMemberData.u == AppUtils.getInstance().getUserInfoProvider().getUserId() && ((long) familyMemberData.s) == AppUtils.getInstance().getUserInfoProvider().getUserOrigin()) {
                    itemViewHolder.ivMore.setVisibility(0);
                    itemViewHolder.ivMore.setImageResource(R.drawable.live_gift_rank_me);
                } else if (this.d == 1) {
                    itemViewHolder.ivMore.setVisibility(0);
                    itemViewHolder.ivMore.setImageResource(R.drawable.ic_family_member_more);
                    itemViewHolder.ivMore.setOnClickListener(new g(this, itemViewHolder, familyMemberData));
                } else {
                    itemViewHolder.ivMore.setVisibility(8);
                }
                itemViewHolder.ivAvatar.setOnClickListener(new h(this, familyMemberData));
            }
            itemViewHolder.tvUserName.requestLayout();
        }
    }

    private void a(View view, FamilyMemberData familyMemberData) {
        View inflate = View.inflate(this.b, R.layout.popup_window_family_detail, null);
        PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.getContentView().setFocusableInTouchMode(true);
        popupWindow.getContentView().setFocusable(true);
        popupWindow.getContentView().setOnKeyListener(new i(this, popupWindow));
        ((TextView) inflate.findViewById(R.id.tv_quit)).setOnClickListener(new j(this, popupWindow, familyMemberData));
        a(popupWindow, view);
    }

    private void a(FamilyMemberData familyMemberData) {
        Builder kVar = new k(this, R.style.SimpleDialog, familyMemberData);
        kVar.message(this.b.getString(R.string.family_expel_confirm)).positiveAction(this.b.getString(R.string.setting_confirm)).negativeAction(this.b.getString(R.string.setting_cancel));
        AppUtils.showDialogFragment((FragmentActivity) this.b, kVar);
    }

    private void b(FamilyMemberData familyMemberData) {
        NetRequest.getInstance().post(UrlConstants.FAMILY_EXPEL, new l(this, familyMemberData));
    }

    private void a(PopupWindow popupWindow, View view) {
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAsDropDown(view, 0, 0);
        }
    }

    public int getItemCount() {
        return this.a.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.e = onItemClickListener;
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        return 1;
    }
}
