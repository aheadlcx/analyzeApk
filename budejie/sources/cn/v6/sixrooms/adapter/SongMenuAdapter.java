package cn.v6.sixrooms.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.SubLiveListBean;
import cn.v6.sixrooms.room.BaseRoomActivity;
import java.util.List;

public class SongMenuAdapter extends BaseAdapter {
    private BaseRoomActivity a;
    private List<SubLiveListBean> b;

    static class a {
        TextView a;
        TextView b;
        TextView c;
        View d;

        a() {
        }
    }

    public SongMenuAdapter(BaseRoomActivity baseRoomActivity, List<SubLiveListBean> list) {
        this.a = baseRoomActivity;
        this.b = list;
    }

    public int getCount() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }

    public Object getItem(int i) {
        if (this.b == null) {
            return null;
        }
        return this.b.get(i);
    }

    public long getItemId(int i) {
        if (this.b == null) {
            return 0;
        }
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.a).inflate(R.layout.item_song_menu, null);
            aVar = new a();
            aVar.a = (TextView) view.findViewById(R.id.tv_song_name);
            aVar.b = (TextView) view.findViewById(R.id.tv_song_singer);
            aVar.c = (TextView) view.findViewById(R.id.tv_song_ok);
            aVar.d = view.findViewById(R.id.music_line_buttom);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (!TextUtils.isEmpty(((SubLiveListBean) this.b.get(i)).getMscName())) {
            aVar.a.setText(((SubLiveListBean) this.b.get(i)).getMscName());
        }
        if (TextUtils.isEmpty(((SubLiveListBean) this.b.get(i)).getMscFirst())) {
            aVar.b.setText("原唱未知");
        } else {
            aVar.b.setText(((SubLiveListBean) this.b.get(i)).getMscFirst());
        }
        aVar.c.setOnClickListener(new i(this, i));
        return view;
    }
}
