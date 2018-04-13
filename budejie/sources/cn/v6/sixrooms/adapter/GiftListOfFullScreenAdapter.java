package cn.v6.sixrooms.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.GiftListItemBean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class GiftListOfFullScreenAdapter extends BaseAdapter {
    private Context a;
    private List<GiftListItemBean> b;

    public static class GiftViewHolder {
        TextView a;
        SimpleDraweeView b;
        TextView c;
        View d;
    }

    public GiftListOfFullScreenAdapter(Context context, List<GiftListItemBean> list) {
        this.a = context;
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
            view = View.inflate(this.a, R.layout.pop_rank_gift_item, null);
            giftViewHolder = new GiftViewHolder();
            giftViewHolder.a = (TextView) view.findViewById(R.id.tv_name);
            giftViewHolder.b = (SimpleDraweeView) view.findViewById(R.id.iv_gift);
            giftViewHolder.c = (TextView) view.findViewById(R.id.tv_num);
            giftViewHolder.d = view.findViewById(R.id.division);
            view.setTag(giftViewHolder);
        }
        if (i == 0) {
            giftViewHolder.d.setVisibility(8);
        } else {
            giftViewHolder.d.setVisibility(0);
        }
        GiftListItemBean giftListItemBean = (GiftListItemBean) this.b.get(i);
        giftViewHolder.a.setText(giftListItemBean.getUname());
        giftViewHolder.b.setImageURI(Uri.parse(giftListItemBean.getPicUrl()));
        giftViewHolder.c.setText(giftListItemBean.getNum());
        return view;
    }
}
