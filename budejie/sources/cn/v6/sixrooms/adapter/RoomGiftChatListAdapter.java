package cn.v6.sixrooms.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.utils.DensityUtil;
import java.util.List;

public class RoomGiftChatListAdapter extends RoomChatListBaseAdapter {
    public RoomGiftChatListAdapter(List<UserInfoBean> list, Context context) {
        super(list, context);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View textView;
        UserInfoBean userInfoBean = (UserInfoBean) this.allList.get(i);
        if (view == null) {
            textView = new TextView(this.mContext);
            textView.setLayoutParams(new LayoutParams(-1, DensityUtil.dip2px(35.0f)));
            ((TextView) textView).setGravity(16);
            ((TextView) textView).setSingleLine();
            ((TextView) textView).setEllipsize(TruncateAt.END);
            ((TextView) textView).setTextColor(Color.parseColor("#999999"));
            ((TextView) textView).setTextSize(12.0f);
            ((TextView) textView).setPadding(DensityUtil.dip2px(10.0f), 0, DensityUtil.dip2px(10.0f), 0);
            textView.setBackgroundResource(R.drawable.gift_select_nuber_list_item_background);
        } else {
            textView = view;
        }
        ((TextView) textView).setText(userInfoBean.getUname());
        return textView;
    }
}
