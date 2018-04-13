package cn.v6.sixrooms.adapter;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.room.BaseRoomActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class GuardListOfFullScreenAdapter extends BaseAdapter {
    private BaseRoomActivity a;
    private List<UserInfoBean> b;
    private int c = 0;

    public static class GiftViewHolder {
        TextView a;
        ImageView b;
        ImageView c;
        ImageView d;
        View e;
    }

    public int getmState() {
        return this.c;
    }

    public void setmState(int i) {
        this.c = i;
    }

    public GuardListOfFullScreenAdapter(BaseRoomActivity baseRoomActivity, List<UserInfoBean> list) {
        this.a = baseRoomActivity;
        this.b = list;
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        GiftViewHolder giftViewHolder;
        if (view != null) {
            giftViewHolder = (GiftViewHolder) view.getTag();
        } else {
            view = View.inflate(this.a, R.layout.pop_rank_guard_item, null);
            GiftViewHolder giftViewHolder2 = new GiftViewHolder();
            giftViewHolder2.a = (TextView) view.findViewById(R.id.tv_name);
            giftViewHolder2.b = (ImageView) view.findViewById(R.id.iv_head);
            giftViewHolder2.c = (ImageView) view.findViewById(R.id.offline_head);
            giftViewHolder2.d = (ImageView) view.findViewById(R.id.iv_guard_type);
            giftViewHolder2.e = view.findViewById(R.id.division);
            view.setTag(giftViewHolder2);
            giftViewHolder = giftViewHolder2;
        }
        if (i == 0) {
            giftViewHolder.e.setVisibility(8);
        } else {
            giftViewHolder.e.setVisibility(0);
        }
        UserInfoBean userInfoBean = (UserInfoBean) this.b.get(i);
        ((SimpleDraweeView) giftViewHolder.b).setImageURI(Uri.parse(userInfoBean.getUserpic()));
        if (this.c == 0) {
            giftViewHolder.a.setText(this.a.isSuperGirlRoom().booleanValue() ? userInfoBean.getAlias() : userInfoBean.getUname());
            if (this.a.isSuperGirlRoom().booleanValue()) {
                giftViewHolder.c.setVisibility(8);
                giftViewHolder.d.setVisibility(0);
                giftViewHolder.d.setImageResource(this.a.getResources().getIdentifier("vfan_" + userInfoBean.getProp().substring(2), "drawable", this.a.getPackageName()));
            } else {
                int parseInt;
                giftViewHolder.c.setVisibility(0);
                giftViewHolder.d.setVisibility(0);
                try {
                    parseInt = Integer.parseInt(userInfoBean.getFlag());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    parseInt = -1;
                }
                if (parseInt == 0) {
                    giftViewHolder.a.setTextColor(this.a.getResources().getColor(R.color.pop_guard_text_offline));
                    giftViewHolder.c.setVisibility(0);
                } else {
                    giftViewHolder.a.setTextColor(this.a.getResources().getColor(R.color.white));
                    giftViewHolder.c.setVisibility(8);
                }
                String badge = userInfoBean.getBadge();
                if (badge.contains("7569")) {
                    if (parseInt == 0) {
                        giftViewHolder.d.setImageResource(R.drawable.pop_guard_silver_offline);
                    } else if (parseInt == 1) {
                        giftViewHolder.d.setImageResource(R.drawable.pop_guard_silver_online);
                    }
                } else if (badge.contains("7570")) {
                    if (parseInt == 0) {
                        giftViewHolder.d.setImageResource(R.drawable.pop_guard_gold_offline);
                    } else if (parseInt == 1) {
                        giftViewHolder.d.setImageResource(R.drawable.pop_guard_gold_online);
                    }
                }
            }
        } else {
            giftViewHolder.a.setText(userInfoBean.getUname());
            giftViewHolder.c.setVisibility(8);
            giftViewHolder.d.setVisibility(8);
        }
        return view;
    }
}
