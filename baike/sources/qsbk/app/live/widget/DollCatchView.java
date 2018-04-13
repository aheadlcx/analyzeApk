package qsbk.app.live.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.Locale;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.DeviceUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.ui.LiveBaseActivity;

public class DollCatchView extends FrameLayout implements OnClickListener, OnTouchListener {
    public static int INTERVAL_SEND_MESSAGE = 200;
    private Runnable A;
    private boolean B;
    private LiveBaseActivity a;
    private CountDownTimer b;
    private boolean c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;
    private View i;
    private ImageView j;
    private LinearLayout k;
    private User l;
    private LinearLayout m;
    private SimpleDraweeView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private FrameLayout t;
    private FrameLayout u;
    private MediaPlayer v;
    private MediaPlayer w;
    private MediaPlayer x;
    private Handler y;
    private int z;

    public DollCatchView(Context context) {
        this(context, null);
    }

    public DollCatchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DollCatchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = true;
        this.y = new Handler();
        this.A = new p(this);
        this.B = true;
        a();
    }

    private void a() {
        this.a = (LiveBaseActivity) getContext();
        View inflate = View.inflate(getContext(), R.layout.live_doll_catch_view, this);
        this.d = inflate.findViewById(R.id.btn_doll_up);
        this.e = inflate.findViewById(R.id.btn_doll_down);
        this.f = inflate.findViewById(R.id.btn_doll_left);
        this.g = inflate.findViewById(R.id.btn_doll_right);
        this.h = inflate.findViewById(R.id.btn_doll_ok);
        this.d.setOnTouchListener(this);
        this.e.setOnTouchListener(this);
        this.f.setOnTouchListener(this);
        this.g.setOnTouchListener(this);
        this.h.setOnTouchListener(this);
        this.i = inflate.findViewById(R.id.btn_doll_request);
        this.j = (ImageView) inflate.findViewById(R.id.doll_history);
        this.k = (LinearLayout) inflate.findViewById(R.id.ll_my_diamond);
        this.i.setOnTouchListener(this);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.m = (LinearLayout) inflate.findViewById(R.id.ll_user);
        this.m.setOnClickListener(this);
        this.n = (SimpleDraweeView) inflate.findViewById(R.id.doll_user_avatar);
        this.o = (TextView) inflate.findViewById(R.id.doll_user_name);
        this.p = (TextView) inflate.findViewById(R.id.doll_my_diamond);
        this.q = (TextView) inflate.findViewById(R.id.tv_doll_request);
        this.r = (TextView) inflate.findViewById(R.id.doll_catch_price);
        this.s = (TextView) inflate.findViewById(R.id.live_doll_countdown);
        this.t = (FrameLayout) inflate.findViewById(R.id.doll_catching);
        this.u = (FrameLayout) inflate.findViewById(R.id.doll_preparing);
        this.u.setOnClickListener(this);
        this.v = MediaPlayer.create(this.a, R.raw.doll_insert_coin);
        if (this.v != null) {
            this.v.setLooping(false);
        }
        this.w = MediaPlayer.create(this.a, R.raw.doll_btn_direction);
        if (this.w != null) {
            this.w.setLooping(true);
        }
        this.x = MediaPlayer.create(this.a, R.raw.doll_btn_ok);
        if (this.x != null) {
            this.x.setLooping(false);
        }
        setRequestEnable(false);
    }

    public void updateBalance(long j) {
        this.p.setText(String.valueOf(j));
    }

    public void setPrice(int i) {
        this.r.setText(String.format(Locale.getDefault(), "x%d", new Object[]{Integer.valueOf(i)}));
    }

    public void setRequestEnable(boolean z) {
        if (z) {
            this.q.setText("开始抓娃娃");
            this.q.setTextSize(22.0f);
            this.y.removeCallbacksAndMessages(null);
        } else {
            this.q.setText("其他玩家游戏中");
            this.q.setTextSize(16.0f);
        }
        this.i.setEnabled(z);
        LayoutParams layoutParams = (LayoutParams) this.q.getLayoutParams();
        layoutParams.topMargin = WindowUtils.dp2Px(16);
        this.q.setLayoutParams(layoutParams);
        layoutParams = (LayoutParams) this.r.getLayoutParams();
        layoutParams.topMargin = WindowUtils.dp2Px(45);
        this.r.setLayoutParams(layoutParams);
    }

    public void setDollConnecting() {
        this.i.setEnabled(false);
        this.q.setText("正在连接...");
    }

    public void setDollMaintaining() {
        this.i.setEnabled(false);
        this.q.setText("娃娃机维护中");
        this.q.setTextSize(18.0f);
        setDollPrepare();
    }

    public void doDollCountDown(int i) {
        if (this.b != null) {
            this.b.cancel();
        }
        this.b = new q(this, (long) ((i * 1000) + 500), 500);
        this.b.start();
        this.s.setVisibility(0);
    }

    public void setDollCatching(boolean z) {
        this.B = z;
        if (z) {
            this.t.setVisibility(0);
            this.u.setVisibility(8);
        } else {
            if (this.w != null) {
                this.w.setLooping(false);
                this.w.pause();
            }
            this.y.removeCallbacksAndMessages(null);
        }
        this.d.setClickable(z);
        this.e.setClickable(z);
        this.f.setClickable(z);
        this.g.setClickable(z);
        this.h.setClickable(z);
    }

    public void setDollPrepare() {
        this.t.setVisibility(8);
        this.u.setVisibility(0);
    }

    public void playCoinEffect() {
        if (this.v != null) {
            this.v.seekTo(0);
            this.v.start();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_doll_request) {
            if (DeviceUtils.getSystemSDKInt() >= 16) {
                a(1);
                if (this.x != null) {
                    this.x.seekTo(0);
                    this.x.start();
                    return;
                }
                return;
            }
            ToastUtil.Long("您的系统版本过低，不支持娃娃游戏");
        } else if (id == R.id.doll_history) {
            new DollHistoryDialog(this.a).show();
        } else if (id == R.id.ll_my_diamond) {
            AppUtils.getInstance().getUserInfoProvider().toPay(this.a, 103);
        } else if (id == R.id.ll_user && this.a != null && this.l != null) {
            this.a.onAnimAvatarClick(this.l);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int id = view.getId();
        LayoutParams layoutParams;
        switch (motionEvent.getAction()) {
            case 0:
                if (id != R.id.btn_doll_up) {
                    if (id != R.id.btn_doll_down) {
                        if (id != R.id.btn_doll_left) {
                            if (id != R.id.btn_doll_right) {
                                if (id != R.id.btn_doll_ok) {
                                    if (id == R.id.btn_doll_request) {
                                        layoutParams = (LayoutParams) this.q.getLayoutParams();
                                        layoutParams.topMargin = WindowUtils.dp2Px(28);
                                        this.q.setLayoutParams(layoutParams);
                                        layoutParams = (LayoutParams) this.r.getLayoutParams();
                                        layoutParams.topMargin = WindowUtils.dp2Px(57);
                                        this.r.setLayoutParams(layoutParams);
                                        break;
                                    }
                                }
                                b(9);
                                this.s.setVisibility(8);
                                if (this.x != null && this.B) {
                                    this.x.seekTo(0);
                                    this.x.start();
                                }
                                setDollCatching(false);
                                this.a.showDollCatchingTips();
                                if (this.b != null) {
                                    this.b.cancel();
                                    break;
                                }
                            }
                            b(3);
                            break;
                        }
                        b(7);
                        break;
                    }
                    b(5);
                    break;
                }
                b(1);
                break;
                break;
            case 1:
            case 3:
                if (id != R.id.btn_doll_up) {
                    if (id != R.id.btn_doll_down) {
                        if (id != R.id.btn_doll_left) {
                            if (id != R.id.btn_doll_right) {
                                if (id != R.id.btn_doll_ok && id == R.id.btn_doll_request) {
                                    layoutParams = (LayoutParams) this.q.getLayoutParams();
                                    layoutParams.topMargin = WindowUtils.dp2Px(16);
                                    this.q.setLayoutParams(layoutParams);
                                    layoutParams = (LayoutParams) this.r.getLayoutParams();
                                    layoutParams.topMargin = WindowUtils.dp2Px(45);
                                    this.r.setLayoutParams(layoutParams);
                                    break;
                                }
                            }
                            b(4);
                            break;
                        }
                        b(8);
                        break;
                    }
                    b(6);
                    break;
                }
                b(2);
                break;
        }
        return false;
    }

    private void a(int i) {
        this.a.sendLiveMessageAndRefreshUI(LiveMessage.createDollMessage(AppUtils.getInstance().getUserInfoProvider().getUserId(), i));
    }

    private void b(int i) {
        if (!this.c) {
            switch (i) {
                case 1:
                    i = 7;
                    break;
                case 2:
                    i = 8;
                    break;
                case 3:
                    i = 1;
                    break;
                case 4:
                    i = 2;
                    break;
                case 5:
                    i = 3;
                    break;
                case 6:
                    i = 4;
                    break;
                case 7:
                    i = 5;
                    break;
                case 8:
                    i = 6;
                    break;
            }
        }
        this.a.sendLiveMessageAndRefreshUI(LiveMessage.createDollActionMessage(AppUtils.getInstance().getUserInfoProvider().getUserId(), i));
        if (i >= 9) {
            if (this.w != null) {
                this.w.setLooping(false);
                this.w.pause();
            }
            this.y.removeCallbacksAndMessages(null);
        } else if (i % 2 == 1) {
            if (this.w != null && this.B) {
                this.w.setLooping(true);
                this.w.start();
            }
            this.z = i;
            this.y.postDelayed(this.A, (long) INTERVAL_SEND_MESSAGE);
        } else {
            if (this.w != null) {
                this.w.setLooping(false);
                this.w.pause();
            }
            this.y.removeCallbacksAndMessages(null);
        }
    }

    public void setMajorView(boolean z) {
        this.c = z;
    }

    public void release() {
        if (this.w != null) {
            this.w.release();
            this.w = null;
        }
        if (this.x != null) {
            this.x.release();
            this.x = null;
        }
        if (this.v != null) {
            this.v.release();
            this.v = null;
        }
        this.y.removeCallbacksAndMessages(null);
    }

    public void showDollUserInfo(User user) {
        this.l = user;
        this.m.setVisibility(0);
        AppUtils.getInstance().getImageProvider().loadAvatar(this.n, user.headurl);
        this.o.setText(user.name + "游戏中");
    }

    public void hideDollUserInfo() {
        this.l = null;
        this.m.setVisibility(8);
    }
}
