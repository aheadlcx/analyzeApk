package cn.v6.sixrooms.room.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.SubLiveListBean;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.utils.DateUtil;
import java.util.List;

public class SongQueueAdapter extends BaseAdapter {
    private BaseRoomActivity a;
    private List<SubLiveListBean> b;

    static class a {
        TextView a;
        TextView b;
        TextView c;
        TextView d;
        TextView e;
        View f;
        View g;

        a() {
        }
    }

    public SongQueueAdapter(BaseRoomActivity baseRoomActivity, List<SubLiveListBean> list) {
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
            view = LayoutInflater.from(this.a).inflate(R.layout.item_song_queue, viewGroup, false);
            aVar = new a();
            aVar.a = (TextView) view.findViewById(R.id.tv_music_name);
            aVar.b = (TextView) view.findViewById(R.id.tv_music_singer);
            aVar.c = (TextView) view.findViewById(R.id.tv_user_name);
            aVar.d = (TextView) view.findViewById(R.id.tv_song_time);
            aVar.e = (TextView) view.findViewById(R.id.tv_song_status);
            aVar.f = view.findViewById(R.id.music_line_buttom);
            aVar.g = view.findViewById(R.id.username_layout);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (this.a.getResources().getConfiguration().orientation == 2) {
            aVar.a.setMaxEms(5);
            aVar.b.setMaxEms(5);
            aVar.c.setMaxEms(5);
            aVar.d.setMaxEms(5);
        } else if (this.a.getResources().getConfiguration().orientation == 1) {
            aVar.a.setMaxEms(7);
            aVar.b.setMaxEms(5);
            aVar.c.setMaxEms(5);
            aVar.d.setMaxEms(5);
        }
        ((LayoutParams) aVar.g.getLayoutParams()).leftMargin = this.a.getResources().getDimensionPixelSize(R.dimen.song_name_margin_left);
        ((LayoutParams) aVar.e.getLayoutParams()).rightMargin = this.a.getResources().getDimensionPixelSize(R.dimen.song_status_margin_right);
        if (!TextUtils.isEmpty(((SubLiveListBean) this.b.get(i)).getMscName())) {
            aVar.a.setText(((SubLiveListBean) this.b.get(i)).getMscName());
        }
        if (TextUtils.isEmpty(((SubLiveListBean) this.b.get(i)).getMscFirst()) || ((SubLiveListBean) this.b.get(i)).getMscFirst().equals("")) {
            aVar.b.setText("原唱未知");
        } else {
            aVar.b.setText(((SubLiveListBean) this.b.get(i)).getMscFirst());
        }
        if (!TextUtils.isEmpty(((SubLiveListBean) this.b.get(i)).getSalias())) {
            aVar.c.setText(((SubLiveListBean) this.b.get(i)).getSalias());
        }
        if (((SubLiveListBean) this.b.get(i)).getSongTime() != 0) {
            aVar.d.setText(DateUtil.getDateDetailForFigure(((SubLiveListBean) this.b.get(i)).getSongTime()));
        }
        int status = ((SubLiveListBean) this.b.get(i)).getStatus();
        if (status == 0) {
            aVar.e.setText("排队中");
        } else if (status == 1) {
            aVar.e.setText("已同意");
        } else if (status == 2) {
            aVar.e.setText("已拒绝");
        }
        return view;
    }
}
