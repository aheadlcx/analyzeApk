package cn.v6.sixrooms.pay.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.pay.bean.PayInfoInterface;
import java.util.List;

public class PaySelectAdapter extends BaseAdapter {
    private List<PayInfoInterface> a;
    private Context b;

    public PaySelectAdapter(Context context, Object obj) {
        this.b = context;
        this.a = (List) obj;
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
        PaySelectAdapter$a paySelectAdapter$a;
        if (view == null || !(view instanceof LinearLayout)) {
            view = View.inflate(this.b, R.layout.phone_pay_select_item, null);
            PaySelectAdapter$a paySelectAdapter$a2 = new PaySelectAdapter$a();
            paySelectAdapter$a2.a = (TextView) view.findViewById(R.id.tv_content);
            view.setTag(paySelectAdapter$a2);
            paySelectAdapter$a = paySelectAdapter$a2;
        } else {
            paySelectAdapter$a = (PaySelectAdapter$a) view.getTag();
        }
        paySelectAdapter$a.a.setText(((PayInfoInterface) this.a.get(i)).getContent());
        return view;
    }
}
