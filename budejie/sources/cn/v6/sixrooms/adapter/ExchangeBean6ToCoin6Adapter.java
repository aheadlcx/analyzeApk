package cn.v6.sixrooms.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.ui.phone.ExchangeBean6ToCoin6Activity;
import cn.v6.sixrooms.utils.DrawableResourceUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class ExchangeBean6ToCoin6Adapter extends BaseAdapter {
    private List<Map<String, String>> a;
    private ExchangeBean6ToCoin6Activity b;
    private DecimalFormat c = new DecimalFormat("###,###");

    private static class a {
        public ImageView a;
        public TextView b;
        public TextView c;
        public TextView d;

        private a() {
        }
    }

    public ExchangeBean6ToCoin6Adapter(List<Map<String, String>> list, ExchangeBean6ToCoin6Activity exchangeBean6ToCoin6Activity) {
        this.a = list;
        this.b = exchangeBean6ToCoin6Activity;
    }

    public void setList(List<Map<String, String>> list) {
        this.a = list;
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
        a aVar;
        if (view == null) {
            aVar = new a();
            view = View.inflate(this.b, R.layout.phone_activity_exchange_bean6_item, null);
            aVar.a = (ImageView) view.findViewById(R.id.image);
            aVar.b = (TextView) view.findViewById(R.id.get);
            aVar.c = (TextView) view.findViewById(R.id.pay);
            aVar.d = (TextView) view.findViewById(R.id.exchange);
        } else {
            aVar = (a) view.getTag();
        }
        int bean6ImageResourceByPosition = DrawableResourceUtils.getBean6ImageResourceByPosition(i);
        if (bean6ImageResourceByPosition != -1) {
            aVar.a.setImageResource(bean6ImageResourceByPosition);
        }
        String string = this.b.getString(R.string.exchange_item_howMuchYouWant);
        String format = this.c.format((long) Integer.parseInt((String) ((Map) this.a.get(i)).get("c")));
        aVar.b.setText(String.format(string, new Object[]{format}));
        string = this.b.getString(R.string.exchange_item_howMuchYouPay);
        format = this.c.format((long) Integer.parseInt((String) ((Map) this.a.get(i)).get(IXAdRequestInfo.WIDTH)));
        aVar.c.setText(String.format(string, new Object[]{format}));
        aVar.d.setOnClickListener(new a(this, i));
        view.setTag(aVar);
        return view;
    }
}
