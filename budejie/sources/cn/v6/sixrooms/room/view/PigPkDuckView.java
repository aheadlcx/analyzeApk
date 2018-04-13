package cn.v6.sixrooms.room.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.room.game.PigPkYellowDuckBean;
import cn.v6.sixrooms.room.game.PigPkYellowDuckUser;
import cn.v6.sixrooms.view.interfaces.OnActivityListener;
import java.util.List;

public class PigPkDuckView extends RelativeLayout implements OnClickListener, OnActivityListener {
    private TextView a = ((TextView) findViewById(R.id.count_down));
    private ImageView b = ((ImageView) findViewById(R.id.pig_icon));
    private TextView c = ((TextView) findViewById(R.id.pig_name));
    private TextView d = ((TextView) findViewById(R.id.pig_num));
    private ImageView e = ((ImageView) findViewById(R.id.duck_icon));
    private TextView f = ((TextView) findViewById(R.id.duck_name));
    private TextView g = ((TextView) findViewById(R.id.duck_num));
    private String h;
    private PigPkDuckViewListener i;
    private Context j;
    private CountDownTimer k;
    private PigPkYellowDuckUser l;
    private PigPkYellowDuckUser m;

    public interface PigPkDuckViewListener {
        void onDuck();

        void onGameOver();

        void onPig();

        void onUserName(String str);
    }

    public PigPkDuckView(Context context, String str, PigPkYellowDuckBean pigPkYellowDuckBean, PigPkDuckViewListener pigPkDuckViewListener) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.phone_room_pig_pk_duck_page, this, true);
        this.h = str;
        this.j = context;
        this.i = pigPkDuckViewListener;
        initData(pigPkYellowDuckBean);
        this.b.setOnClickListener(this);
        this.e.setOnClickListener(this);
        countDownTime(Long.parseLong(pigPkYellowDuckBean.getEtm()), 0, this.a);
    }

    public void countDownTime(long j, int i, TextView textView) {
        this.k = new g(this, 1000 * j, i, textView);
        this.k.start();
    }

    public void initData(PigPkYellowDuckBean pigPkYellowDuckBean) {
        List prop = pigPkYellowDuckBean.getProp();
        this.l = (PigPkYellowDuckUser) prop.get(1);
        this.m = (PigPkYellowDuckUser) prop.get(0);
        if (this.l.getNum() != null) {
            this.d.setText(this.l.getNum() + "个");
        }
        if (this.m.getNum() != null) {
            this.g.setText(this.m.getNum() + "个");
        }
        if (this.m.getCaptain() != null) {
            this.f.setText(this.m.getCaptain().getAlias());
        } else {
            this.f.setText("暂无");
        }
        if (this.l.getCaptain() != null) {
            this.c.setText(this.l.getCaptain().getAlias());
        } else {
            this.c.setText("暂无");
        }
        if (!"1".equals(pigPkYellowDuckBean.getType())) {
            this.c.setClickable(false);
            this.f.setClickable(false);
        } else if (this.l.getCaptain() != null) {
            if (this.h.equals(this.l.getCaptain().getUid())) {
                this.c.setClickable(false);
                this.f.setClickable(true);
                this.f.setOnClickListener(this);
            } else {
                this.f.setClickable(false);
                this.c.setClickable(true);
                this.c.setOnClickListener(this);
            }
        }
        if ("0".equals(pigPkYellowDuckBean.getState())) {
            countDownTime(10, 1, null);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.duck_icon) {
            this.i.onDuck();
        } else if (id == R.id.pig_icon) {
            this.i.onPig();
        } else if (id == R.id.pig_name || id == R.id.duck_name) {
            String rid;
            if (this.h.equals(this.l.getCaptain().getUid())) {
                rid = this.m.getCaptain().getRid();
            } else {
                rid = this.l.getCaptain().getRid();
            }
            this.i.onUserName(rid);
        }
    }

    public void onActivityCreate() {
    }

    public void onActivityDestrory() {
        if (this.k != null) {
            this.k.cancel();
            this.k = null;
        }
    }
}
