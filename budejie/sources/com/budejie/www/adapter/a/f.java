package com.budejie.www.adapter.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.type.MySquareIcon;
import java.util.List;

public class f extends BaseAdapter {
    private List<MySquareIcon> a;
    private Context b;

    public void a(List<MySquareIcon> list) {
        this.a = list;
    }

    public f(Context context) {
        this.b = context;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = View.inflate(this.b, R.layout.my_square_more_icon_item, null);
        MySquareIcon mySquareIcon = (MySquareIcon) this.a.get(i);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.moreIconItem_layout);
        TextView textView = (TextView) inflate.findViewById(R.id.moreIconItem_name);
        ((AsyncImageView) inflate.findViewById(R.id.moreIconItem_icon)).setAsyncCacheImage(mySquareIcon.getIcon(), R.color.transparent);
        textView.setText(mySquareIcon.getName());
        linearLayout.setOnClickListener(new f$1(this, mySquareIcon));
        return inflate;
    }
}
