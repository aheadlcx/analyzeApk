package cn.v6.sixrooms.hall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.base.SixRoomsUtils;
import java.util.List;

final class af extends BaseAdapter {
    private List<String> a;
    private Context b;

    interface a {
        void a(int i);
    }

    private class b {
        final /* synthetic */ af a;
        private TextView b;
        private ImageView c;

        private b(af afVar) {
            this.a = afVar;
        }
    }

    af(Context context, List<String> list) {
        this.b = context;
        this.a = list;
    }

    public final int getCount() {
        return this.a == null ? 0 : this.a.size();
    }

    public final Object getItem(int i) {
        return this.a.get(i);
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            bVar = new b();
            view = LayoutInflater.from(this.b).inflate(R.layout.phone_item_hosts_type, viewGroup, false);
            bVar.b = (TextView) view.findViewById(R.id.hostsTypeTv);
            bVar.c = (ImageView) view.findViewById(R.id.typeIv);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        String str = (String) this.a.get(i);
        bVar.b.setText(SixRoomsUtils.parseTypeToTitle(str));
        bVar.c.setImageResource(SixRoomsUtils.parseTypeToDrawableId(str));
        return view;
    }
}
