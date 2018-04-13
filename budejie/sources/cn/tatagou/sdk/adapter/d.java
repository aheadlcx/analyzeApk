package cn.tatagou.sdk.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.e.a.b;
import cn.tatagou.sdk.fragment.BaseFragment;
import cn.tatagou.sdk.pojo.Item;
import cn.tatagou.sdk.pojo.Special;
import cn.tatagou.sdk.util.m;
import cn.tatagou.sdk.util.p;
import java.util.List;

public class d extends a<Special> {
    private static final String e = d.class.getSimpleName();
    private BaseFragment f;
    private String g;

    class a {
        ImageView a;
        TextView b;
        final /* synthetic */ d c;

        a(d dVar) {
            this.c = dVar;
        }
    }

    public int getCount() {
        if (this.c == null) {
            return 0;
        }
        if (this.c.size() == 4) {
            return 4;
        }
        if (this.c.size() >= 5 && this.c.size() <= 7) {
            return 5;
        }
        if (this.c.size() >= 8 && this.c.size() <= 9) {
            return 8;
        }
        if (this.c.size() >= 10) {
            return 10;
        }
        return 0;
    }

    public d(Activity activity, List<Special> list, String str, BaseFragment baseFragment) {
        super(activity, (List) list);
        this.f = baseFragment;
        this.g = str;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.b).inflate(R.layout.ttg_columns_item, viewGroup, false);
            aVar.a = (ImageView) view.findViewById(R.id.iv_icon);
            aVar.b = (TextView) view.findViewById(R.id.tv_title);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        final Special special = (Special) this.c.get(i);
        if (special != null) {
            aVar.b.setText(special.getTitle());
            if (this.f == null) {
                p.showNetImg(this.b, special.getCoverImg(), aVar.a);
            } else {
                p.showNetImg(this.f, special.getCoverImg(), aVar.a);
            }
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ d b;

                public void onClick(View view) {
                    if ("BANNER".equals(special.getIsBanner()) || "ITEMS".equals(special.getIsBanner())) {
                        m.openSpecialList(this.b.b, special.getId(), special.getMarker(), this.b.g, special.getTitle(), null);
                    } else if ("URL".equals(special.getIsBanner()) || "TBURL".equals(special.getIsBanner())) {
                        m.openUrlH5(this.b.b, special);
                    } else if ("ITEM".equals(special.getIsBanner())) {
                        Item item = special.getItem();
                        if (item != null) {
                            b.spStatEvent(special.getId(), special.getMarker(), null);
                            m.openGoodsDetails(this.b.b, item, special.getId(), this.b.g, "HOME", item.getTaobaoType());
                        }
                    }
                }
            });
        }
        return view;
    }
}
