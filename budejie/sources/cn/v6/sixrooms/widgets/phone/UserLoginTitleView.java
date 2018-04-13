package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;

public class UserLoginTitleView extends FrameLayout {
    public static final int STATE_DEFAULT = 0;
    public static final int STATE_LOGIN = 1;
    public static final int STATE_REGISTER = 2;
    private Context a;
    private OnClickListener b;
    private LayoutInflater c;
    private int d = 0;
    private TextView e;
    private TextView f;
    private View g;

    public interface OnClickListener {
        void back();
    }

    public UserLoginTitleView(Context context, int i, OnClickListener onClickListener) {
        super(context);
        this.a = context;
        this.d = i;
        this.b = onClickListener;
        this.c = LayoutInflater.from(this.a);
        this.c.inflate(R.layout.title_user_login, this);
        this.g = findViewById(R.id.id_iv_title_back);
        this.e = (TextView) findViewById(R.id.id_tv_title_user_login_login);
        this.f = (TextView) findViewById(R.id.id_tv_register_username);
        this.g.setOnClickListener(new ay(this));
        a();
    }

    private void a() {
        switch (this.d) {
            case 0:
                this.e.setVisibility(8);
                this.f.setVisibility(8);
                return;
            case 1:
                this.e.setVisibility(0);
                this.f.setVisibility(8);
                return;
            case 2:
                this.e.setVisibility(8);
                this.f.setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void setTitleState(int i) {
        this.d = i;
        a();
    }
}
