package cn.v6.sixrooms.room.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.room.bean.OnHeadlineBean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class DialogHeadLineAdapter extends BaseAdapter {
    private Context a;
    private List<OnHeadlineBean> b;

    public static class HeadLineSecondViewHolder {
        TextView a;
        SimpleDraweeView b;
        TextView c;
        TextView d;
        View e;
    }

    public static class HeadLineViewHolder {
        TextView a;
        SimpleDraweeView b;
        ImageView c;
        TextView d;
        View e;
    }

    public DialogHeadLineAdapter(Context context, List<OnHeadlineBean> list) {
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
        OnHeadlineBean onHeadlineBean;
        switch (getItemViewType(i)) {
            case 0:
                HeadLineViewHolder headLineViewHolder;
                if (view != null) {
                    headLineViewHolder = (HeadLineViewHolder) view.getTag();
                } else {
                    view = View.inflate(this.a, R.layout.dialog_head_line_item, null);
                    headLineViewHolder = new HeadLineViewHolder();
                    headLineViewHolder.c = (ImageView) view.findViewById(R.id.iv_rank);
                    headLineViewHolder.b = (SimpleDraweeView) view.findViewById(R.id.iv_identity);
                    headLineViewHolder.a = (TextView) view.findViewById(R.id.tv_name);
                    headLineViewHolder.d = (TextView) view.findViewById(R.id.tv_num);
                    headLineViewHolder.e = view.findViewById(R.id.division);
                    view.setTag(headLineViewHolder);
                }
                if (this.a.getResources().getConfiguration().orientation == 2) {
                    headLineViewHolder.a.setMaxEms(5);
                } else if (this.a.getResources().getConfiguration().orientation == 1) {
                    headLineViewHolder.a.setMaxEms(20);
                }
                headLineViewHolder.e.setVisibility(0);
                int i2 = R.drawable.v6_ic_pop_rank_1;
                switch (i) {
                    case 0:
                        i2 = R.drawable.v6_ic_pop_rank_1;
                        headLineViewHolder.e.setVisibility(8);
                        break;
                    case 1:
                        i2 = R.drawable.v6_ic_pop_rank_2;
                        break;
                    case 2:
                        i2 = R.drawable.v6_ic_pop_rank_3;
                        break;
                }
                headLineViewHolder.c.setBackgroundResource(i2);
                onHeadlineBean = (OnHeadlineBean) this.b.get(i);
                headLineViewHolder.b.setImageURI(Uri.parse(onHeadlineBean.getPic()));
                headLineViewHolder.a.setText(onHeadlineBean.getAlias());
                headLineViewHolder.d.setText(onHeadlineBean.getNum());
                break;
            case 1:
                HeadLineSecondViewHolder headLineSecondViewHolder;
                if (view != null) {
                    headLineSecondViewHolder = (HeadLineSecondViewHolder) view.getTag();
                } else {
                    view = View.inflate(this.a, R.layout.dialog_head_line_second_item, null);
                    headLineSecondViewHolder = new HeadLineSecondViewHolder();
                    headLineSecondViewHolder.c = (TextView) view.findViewById(R.id.tv_rank);
                    headLineSecondViewHolder.b = (SimpleDraweeView) view.findViewById(R.id.iv_identity);
                    headLineSecondViewHolder.a = (TextView) view.findViewById(R.id.tv_name);
                    headLineSecondViewHolder.d = (TextView) view.findViewById(R.id.tv_num);
                    headLineSecondViewHolder.e = view.findViewById(R.id.division);
                    view.setTag(headLineSecondViewHolder);
                }
                if (this.a.getResources().getConfiguration().orientation == 2) {
                    headLineSecondViewHolder.a.setMaxEms(5);
                } else if (this.a.getResources().getConfiguration().orientation == 1) {
                    headLineSecondViewHolder.a.setMaxEms(20);
                }
                headLineSecondViewHolder.e.setVisibility(0);
                headLineSecondViewHolder.c.setText((i + 1));
                onHeadlineBean = (OnHeadlineBean) this.b.get(i);
                headLineSecondViewHolder.b.setImageURI(Uri.parse(onHeadlineBean.getPic()));
                headLineSecondViewHolder.a.setText(onHeadlineBean.getAlias());
                headLineSecondViewHolder.d.setText(onHeadlineBean.getNum());
                break;
        }
        return view;
    }

    public int getItemViewType(int i) {
        if (i <= 2) {
            return 0;
        }
        return 1;
    }

    public int getViewTypeCount() {
        return 2;
    }
}
