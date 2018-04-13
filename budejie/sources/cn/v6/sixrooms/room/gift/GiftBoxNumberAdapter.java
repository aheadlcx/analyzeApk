package cn.v6.sixrooms.room.gift;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.NumberBean;
import java.util.List;

public class GiftBoxNumberAdapter extends BaseAdapter {
    private int itemHeight = ((int) this.mContext.getResources().getDimension(R.dimen.gift_box_item_number_height));
    private int itemWidth = ((int) this.mContext.getResources().getDimension(R.dimen.gift_box_item_number_width));
    private Context mContext;
    private List<NumberBean> mNumbers;

    class ViewHolder {
        View lineView;
        TextView textView;

        ViewHolder() {
        }
    }

    public GiftBoxNumberAdapter(Context context, List<NumberBean> list) {
        this.mContext = context;
        this.mNumbers = list;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public Object getItem(int i) {
        if (i == getCount() - 1) {
            return null;
        }
        return this.mNumbers.get(i);
    }

    public int getCount() {
        return this.mNumbers.size() + 1;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(this.mContext, R.layout.gift_select_number_popwindow_view_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.id_gift_item_text);
            viewHolder.lineView = view.findViewById(R.id.iv_line_gift_number);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (i == getCount() - 1) {
            viewHolder.lineView.setVisibility(8);
            viewHolder.textView.setText("其它数量");
            viewHolder.textView.setTextColor(this.mContext.getResources().getColor(R.color.gift_box_text4));
        } else {
            CharSequence charSequence;
            viewHolder.lineView.setVisibility(0);
            NumberBean numberBean = (NumberBean) this.mNumbers.get(i);
            Object desc = numberBean.getDesc();
            if (TextUtils.isEmpty(desc)) {
                charSequence = numberBean.getNumber() + "个";
            } else {
                charSequence = desc + "(" + numberBean.getNumber() + "个)";
            }
            viewHolder.textView.setText(charSequence);
            if (numberBean.getNumber() <= 10) {
                viewHolder.textView.setTextColor(this.mContext.getResources().getColor(R.color.gift_box_text4));
            } else {
                viewHolder.textView.setTextColor(this.mContext.getResources().getColor(R.color.gift_box_text5));
            }
        }
        view.setLayoutParams(new LayoutParams(this.itemWidth, this.itemHeight));
        return view;
    }
}
