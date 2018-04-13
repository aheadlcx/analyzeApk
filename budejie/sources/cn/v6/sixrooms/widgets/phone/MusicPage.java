package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.SubLiveListBean;
import cn.v6.sixrooms.room.BaseRoomActivity;
import java.util.List;

public class MusicPage extends RelativeLayout implements OnClickListener {
    private ListView a;
    private LinearLayout b;
    private TextView c;
    private ImageView d;
    private BaseRoomActivity e;
    public EditText et_song_input;
    private List<SubLiveListBean> f;
    private CallBack g;
    public LinearLayout ll_music_page;
    public TextView titleName;

    public interface CallBack {
        void onMusicPageHideSoftInput();
    }

    public ListView getLv_song_menu() {
        return this.a;
    }

    public MusicPage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MusicPage(Context context, List<SubLiveListBean> list, CallBack callBack) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.phone_room_music_page, this, true);
        this.e = (BaseRoomActivity) context;
        this.f = list;
        this.g = callBack;
        this.ll_music_page = (LinearLayout) findViewById(R.id.ll_music_page);
        this.ll_music_page.setOnClickListener(this);
        this.d = (ImageView) findViewById(R.id.iv_title_close);
        this.et_song_input = (EditText) findViewById(R.id.et_song_input);
        this.c = (TextView) findViewById(R.id.tv_song_set);
        this.titleName = (TextView) findViewById(R.id.tv_music_title);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.a = (ListView) findViewById(R.id.lv_song_menu);
        this.b = (LinearLayout) findViewById(R.id.ll_song_collect);
        this.a.setVisibility(0);
        this.b.setVisibility(0);
    }

    public void updateLiveBeanData(List<SubLiveListBean> list) {
        this.f = list;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_title_close) {
            this.g.onMusicPageHideSoftInput();
            this.ll_music_page.setVisibility(8);
        } else if (id != R.id.tv_song_set) {
        } else {
            if (this.et_song_input.getText().toString().equals("") || this.et_song_input.getText().toString().length() <= 50) {
                String obj = this.et_song_input.getText().toString();
                String str = "";
                String str2 = "";
                if (this.f == null || this.f.size() <= 0) {
                    Toast.makeText(this.e, "当前没有可点歌曲", 1).show();
                    return;
                }
                this.e.sendSetSong(obj, str, str2, ((SubLiveListBean) this.f.get(0)).getUid());
                return;
            }
            Toast.makeText(this.e, "输入歌区名限制为1至50个字符", 1).show();
        }
    }
}
