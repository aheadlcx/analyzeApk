package cn.v6.sixrooms.room.view;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.game.MiniGameAdapter;
import cn.v6.sixrooms.room.game.MiniGameBean;
import java.util.List;

public class MiniGameDialog extends Dialog implements OnClickListener {
    private ImageView a;
    private TextView b;
    private TextView c;
    private GridView d;
    private List<MiniGameBean> e;
    private MiniGameAdapter f;
    private String g;
    private String h = "";
    private BaseRoomActivity i;
    private OnItemClickListener j = new f(this);

    public void setmGameid(String str) {
        this.h = str;
        this.f.setmGameid(this.h);
        if ("".equals(this.h)) {
            this.c.setText("开始游戏");
            this.d.setOnItemClickListener(this.j);
        } else {
            for (int i = 0; i < this.e.size(); i++) {
                if (this.h.equals(((MiniGameBean) this.e.get(i)).getGameid())) {
                    this.f.setSeclection(i);
                    break;
                }
            }
            this.d.setOnItemClickListener(null);
            this.c.setText("结束游戏");
        }
        this.f.notifyDataSetChanged();
    }

    public MiniGameDialog(BaseRoomActivity baseRoomActivity, List<MiniGameBean> list, String str, String str2) {
        super(baseRoomActivity, R.style.OutClose_NoTitle_Dialog);
        this.i = baseRoomActivity;
        this.e = list;
        this.g = str;
        setContentView(R.layout.dialog_mini_game);
        this.a = (ImageView) findViewById(R.id.iv_close);
        this.b = (TextView) findViewById(R.id.game_desc);
        this.c = (TextView) findViewById(R.id.tv_start);
        this.d = (GridView) findViewById(R.id.game_grid);
        this.b.setText(this.g);
        this.f = new MiniGameAdapter(this.i, this.e);
        this.d.setAdapter(this.f);
        setmGameid(str2);
        this.a.setOnClickListener(this);
        this.c.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_close) {
            dismiss();
        } else if (id != R.id.tv_start) {
        } else {
            if ("".equals(this.h)) {
                this.i.sendOpenMiniGame(((MiniGameBean) this.e.get(this.f.getSeclection())).getGameid());
                dismiss();
                return;
            }
            this.i.sendCloseMiniGame(((MiniGameBean) this.e.get(this.f.getSeclection())).getGameid());
        }
    }
}
