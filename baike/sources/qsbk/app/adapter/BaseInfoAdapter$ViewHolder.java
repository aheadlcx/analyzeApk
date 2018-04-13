package qsbk.app.adapter;

import android.view.View;
import android.widget.TextView;
import qsbk.app.R;

public class BaseInfoAdapter$ViewHolder {
    TextView a;
    TextView b;
    TextView c;
    TextView d;
    TextView e;
    TextView f;
    TextView g;

    static BaseInfoAdapter$ViewHolder a(View view) {
        Object tag = view.getTag();
        if (tag != null && (tag instanceof BaseInfoAdapter$ViewHolder)) {
            return (BaseInfoAdapter$ViewHolder) tag;
        }
        BaseInfoAdapter$ViewHolder baseInfoAdapter$ViewHolder = new BaseInfoAdapter$ViewHolder();
        baseInfoAdapter$ViewHolder.a = (TextView) view.findViewById(R.id.age);
        baseInfoAdapter$ViewHolder.b = (TextView) view.findViewById(R.id.astrology);
        baseInfoAdapter$ViewHolder.c = (TextView) view.findViewById(R.id.location);
        baseInfoAdapter$ViewHolder.d = (TextView) view.findViewById(R.id.hobby);
        baseInfoAdapter$ViewHolder.e = (TextView) view.findViewById(R.id.introduce);
        baseInfoAdapter$ViewHolder.f = (TextView) view.findViewById(R.id.mobile_brand);
        baseInfoAdapter$ViewHolder.g = (TextView) view.findViewById(R.id.create_time);
        return baseInfoAdapter$ViewHolder;
    }
}
