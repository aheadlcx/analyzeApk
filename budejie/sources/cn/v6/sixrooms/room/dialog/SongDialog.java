package cn.v6.sixrooms.room.dialog;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.SongMenuAdapter;
import cn.v6.sixrooms.bean.SubLiveListBean;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.adapter.SongQueueAdapter;
import java.util.ArrayList;
import java.util.List;

public class SongDialog extends BaseDialog implements OnClickListener {
    List<SubLiveListBean> a = new ArrayList();
    List<SubLiveListBean> b = new ArrayList();
    private SongOnClick c;
    private ListView d = ((ListView) findViewById(R.id.playlist));
    private ListView e = ((ListView) findViewById(R.id.already_play));
    private RelativeLayout f = ((RelativeLayout) findViewById(R.id.playlist_btn));
    private RelativeLayout g = ((RelativeLayout) findViewById(R.id.already_btn));
    private RelativeLayout h = ((RelativeLayout) findViewById(R.id.song_btn));
    private View i = findViewById(R.id.playlist_line);
    private View j = findViewById(R.id.already_line);
    private SongMenuAdapter k;
    private SongQueueAdapter l;
    private RelativeLayout m = ((RelativeLayout) findViewById(R.id.playlist_layout));
    private RelativeLayout n = ((RelativeLayout) findViewById(R.id.already_play_layout));
    private RelativeLayout o = ((RelativeLayout) findViewById(R.id.song_title));
    private View p = findViewById(R.id.song_line);
    private LinearLayout q = ((LinearLayout) findViewById(R.id.default_menu_layout));
    private TextView r = ((TextView) findViewById(R.id.tv_song_menu_ok));
    private LinearLayout s = ((LinearLayout) findViewById(R.id.default_queue_layout));
    private TextView t = ((TextView) findViewById(R.id.tv_song_queue_ok));
    private TextView u = ((TextView) findViewById(R.id.song_title_content));

    public interface SongOnClick {
        void clickMenuList();

        void clickSongList();

        void close();
    }

    protected View getDialogContentView() {
        return View.inflate(this.mBaseRoomActivity, R.layout.song_menu_dialog_layout, null);
    }

    public void setOnSongOnClick(SongOnClick songOnClick) {
        this.c = songOnClick;
    }

    public SongDialog(BaseRoomActivity baseRoomActivity) {
        super(baseRoomActivity);
        this.g.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.f.setSelected(true);
        this.m.setVisibility(0);
        this.n.setVisibility(8);
        this.k = new SongMenuAdapter(this.mBaseRoomActivity, this.a);
        this.d.setAdapter(this.k);
        this.l = new SongQueueAdapter(this.mBaseRoomActivity, this.b);
        this.e.setAdapter(this.l);
    }

    public void setTitle(String str) {
        this.u.setText(str);
    }

    public void updataMenuList(List<SubLiveListBean> list) {
        if (list == null || list.size() <= 0) {
            this.a.clear();
            this.q.setVisibility(0);
        } else {
            this.q.setVisibility(8);
            this.a.clear();
            this.a.addAll(list);
        }
        this.k.notifyDataSetChanged();
    }

    public void upDataQueueList(List<SubLiveListBean> list) {
        if (list == null || list.size() <= 0 || ((SubLiveListBean) list.get(0)).getItemnum() == 0) {
            this.b.clear();
            this.s.setVisibility(0);
        } else {
            this.s.setVisibility(8);
            this.b.clear();
            this.b.addAll(list);
        }
        this.l.notifyDataSetChanged();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.playlist_btn) {
            this.g.setSelected(false);
            this.f.setSelected(true);
            this.h.setSelected(false);
            this.i.setVisibility(0);
            this.j.setVisibility(4);
            this.o.setVisibility(0);
            this.p.setVisibility(0);
            this.m.setVisibility(0);
            this.n.setVisibility(8);
            if (this.c != null) {
                this.c.clickMenuList();
            }
        } else if (id == R.id.already_btn) {
            this.g.setSelected(true);
            this.f.setSelected(false);
            this.h.setSelected(false);
            this.i.setVisibility(4);
            this.j.setVisibility(0);
            this.m.setVisibility(8);
            this.n.setVisibility(0);
            this.o.setVisibility(8);
            this.p.setVisibility(8);
            if (this.c != null) {
                this.c.clickSongList();
            }
        } else if (id == R.id.song_btn) {
            if (this.c != null) {
                this.c.close();
            }
        } else if (id == R.id.tv_song_menu_ok) {
            if (this.c != null) {
                this.c.close();
            }
        } else if (id == R.id.tv_song_queue_ok && this.c != null) {
            this.c.close();
        }
    }
}
