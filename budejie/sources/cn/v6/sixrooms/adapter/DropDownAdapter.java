package cn.v6.sixrooms.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import java.util.List;

public class DropDownAdapter extends BaseAdapter {
    private LayoutInflater a = null;
    private Context b;
    private List<String> c;
    private int d;
    private int e = 0;

    public DropDownAdapter(Context context, List<String> list, int i) {
        this.b = context;
        this.a = (LayoutInflater) context.getSystemService("layout_inflater");
        this.c = list;
        this.d = i;
    }

    public void setList(List<String> list) {
        this.c = list;
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void setPosition(int i) {
        this.e = i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        DropDownAdapter$a dropDownAdapter$a;
        if (view == null) {
            view = this.a.inflate(R.layout.phone_ranking_adapter_drop_down, null);
            DropDownAdapter$a dropDownAdapter$a2 = new DropDownAdapter$a(this, (byte) 0);
            dropDownAdapter$a2.a = (TextView) view.findViewById(R.id.item_drop_down_tv);
            view.setLayoutParams(new LayoutParams(-1, this.d));
            view.setTag(dropDownAdapter$a2);
            dropDownAdapter$a = dropDownAdapter$a2;
        } else {
            dropDownAdapter$a = (DropDownAdapter$a) view.getTag();
        }
        if (i == this.e) {
            view.setBackgroundResource(R.drawable.rooms_third_tab);
            dropDownAdapter$a.a.setTextColor(-1);
        } else {
            ColorStateList colorStateList;
            view.setBackgroundResource(R.color.write_transparent_half);
            try {
                colorStateList = this.b.getResources().getColorStateList(R.color.phone_ranking_adapter_item__color_selector);
            } catch (Exception e) {
                colorStateList = null;
            }
            if (colorStateList != null) {
                dropDownAdapter$a.a.setTextColor(colorStateList);
            }
        }
        dropDownAdapter$a.a.setText((CharSequence) this.c.get(i));
        return view;
    }
}
