package cn.v6.sixrooms.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import java.util.ArrayList;

public class PublicMenuAdapter extends BaseAdapter {
    private Context a;
    private ArrayList<String> b;
    private ArrayList<Drawable> c;
    private volatile boolean d;

    static class a {
        TextView a;
        ImageView b;

        a() {
        }
    }

    public PublicMenuAdapter(Context context) {
        this(context, false);
    }

    public PublicMenuAdapter(Context context, boolean z) {
        this.a = context;
        this.d = z;
        Resources resources = context.getResources();
        this.b = new ArrayList();
        this.b.add("公聊");
        this.b.add("私聊");
        this.b.add("查看资料");
        this.b.add("送礼物");
        this.b.add("禁言");
        this.b.add("解除禁言");
        this.b.add("踢出房间");
        this.c = new ArrayList();
        this.c.add(resources.getDrawable(R.drawable.activity_room_chatting_selector));
        this.c.add(resources.getDrawable(R.drawable.activity_room_private_chatting_selector));
        this.c.add(resources.getDrawable(R.drawable.activity_room_view_data_selector));
        this.c.add(resources.getDrawable(R.drawable.activity_room_gift_selector));
        this.c.add(resources.getDrawable(R.drawable.activity_room_gag_selector));
        this.c.add(resources.getDrawable(R.drawable.activity_room_remove_gag_selector));
        this.c.add(resources.getDrawable(R.drawable.activity_room_kick_out_selector));
    }

    public void setRoomManager(boolean z) {
        this.d = z;
    }

    public int getCount() {
        if (this.d) {
            return this.b.size();
        }
        return this.b.size() - 3;
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view != null) {
            aVar = (a) view.getTag();
        } else {
            aVar = new a();
            view = View.inflate(this.a, R.layout.public_menu_item, null);
            aVar.a = (TextView) view.findViewById(R.id.name);
            aVar.b = (ImageView) view.findViewById(R.id.pic);
            view.setTag(aVar);
        }
        aVar.a.setText((CharSequence) this.b.get(i));
        aVar.b.setImageDrawable((Drawable) this.c.get(i));
        return view;
    }
}
