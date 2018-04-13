package qsbk.app.im.group.vote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.im.group.vote.bean.DayActivityInfo;

public class DayActivityLevelAdapter extends BaseAdapter {
    private static final String a = DayActivityLevelAdapter.class.getSimpleName();
    private Context b;
    private ArrayList<DayActivityInfo> c = new ArrayList();

    class a {
        ImageView a;
        final /* synthetic */ DayActivityLevelAdapter b;

        a(DayActivityLevelAdapter dayActivityLevelAdapter) {
            this.b = dayActivityLevelAdapter;
        }
    }

    public DayActivityLevelAdapter(Context context) {
        this.b = context;
    }

    public DayActivityLevelAdapter(Context context, ArrayList<DayActivityInfo> arrayList) {
        this.b = context;
        this.c = arrayList;
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

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.image_card_item_layout, null, false);
            a aVar2 = new a(this);
            aVar2.a = (ImageView) view.findViewById(R.id.iv_img);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setImageLevel(((DayActivityInfo) this.c.get(i)).mActivities);
        return view;
    }
}
