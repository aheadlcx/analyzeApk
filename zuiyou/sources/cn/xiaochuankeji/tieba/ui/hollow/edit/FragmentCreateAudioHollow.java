package cn.xiaochuankeji.tieba.ui.hollow.edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.aop.permission.e;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.l;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer.PlayerStatus;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioRecorder;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioRecorder.RecorderStatus;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioRecorder.a;
import cn.xiaochuankeji.tieba.ui.hollow.util.b;
import cn.xiaochuankeji.tieba.ui.hollow.util.c;
import cn.xiaochuankeji.tieba.ui.hollow.widget.RecordAnimView;
import rx.j;

public class FragmentCreateAudioHollow extends l implements OnClickListener {
    private PlayerStatus a;
    private IAudioPlayer b;
    @BindView
    ImageView buttonMain;
    @BindView
    View buttonPlay;
    @BindView
    View buttonReset;
    private boolean c;
    private RecorderStatus d;
    private IAudioRecorder e;
    private long f;
    private b g;
    private Unbinder h;
    @BindView
    TextView hollowTime;
    @BindView
    RecordAnimView recordAnimView;
    @BindView
    TextView textPlay;
    @BindView
    View timeLayout;
    @BindView
    View welcomeLayout;

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_create_audio_hollow, null);
        this.h = ButterKnife.a(this, inflate);
        this.buttonReset.setOnClickListener(this);
        this.buttonPlay.setOnClickListener(this);
        this.buttonMain.setOnClickListener(this);
        return inflate;
    }

    protected void e() {
        this.e = new b();
        this.e.a(new a(this) {
            final /* synthetic */ FragmentCreateAudioHollow a;

            {
                this.a = r1;
            }

            public void a(RecorderStatus recorderStatus) {
                this.a.d = recorderStatus;
                switch (recorderStatus) {
                    case PREPARE:
                        this.a.k();
                        return;
                    case RECORDING:
                        this.a.l();
                        return;
                    case FILLED:
                        g.a("录制时长已满");
                        this.a.i();
                        return;
                    case PAUSE:
                        this.a.m();
                        return;
                    default:
                        return;
                }
            }

            public void a(long j) {
                this.a.hollowTime.setText(c.b(j));
                this.a.f = j;
            }
        });
        this.e.a(90);
        this.f = 0;
        this.hollowTime.setText(c.b(0));
        this.b = new cn.xiaochuankeji.tieba.ui.hollow.util.a(getActivity());
        this.c = true;
    }

    private void k() {
        e(false);
        d(false);
        this.hollowTime.setText(c.b(0));
        this.buttonMain.setImageResource(R.drawable.record);
        this.f = 0;
        if (this.g != null) {
            this.g.a(OptionType.INIT);
        }
    }

    private void l() {
        e(false);
        d(true);
        this.recordAnimView.a();
        if (this.g != null) {
            this.g.a(OptionType.RECORD_PLAYING);
        }
    }

    private void m() {
        e(true);
        this.recordAnimView.b();
        if (this.g != null) {
            this.g.a(OptionType.RECORD_NEXT);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hollow_edit_bt_reset:
                this.e.c();
                return;
            case R.id.hollow_edit_bt_main:
                n();
                return;
            case R.id.hollow_edit_bt_play:
                o();
                return;
            default:
                return;
        }
    }

    private void n() {
        switch (this.d) {
            case PREPARE:
                PermissionItem permissionItem = new PermissionItem("android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE");
                permissionItem.runIgnorePermission = false;
                permissionItem.settingText = "设置";
                permissionItem.deniedMessage = "开启以下权限才能正常发布语音内容";
                permissionItem.needGotoSetting = true;
                cn.xiaochuankeji.aop.permission.a.a(getActivity()).a(permissionItem, new e(this) {
                    final /* synthetic */ FragmentCreateAudioHollow a;

                    {
                        this.a = r1;
                    }

                    public void permissionGranted() {
                        this.a.e.a();
                        this.a.c = true;
                    }

                    public void permissionDenied() {
                        g.a("开启以下权限才能正常发布语音内容");
                    }
                });
                return;
            case RECORDING:
                this.e.b();
                return;
            case FILLED:
                g.a("录制时长已满");
                return;
            case PAUSE:
                this.e.a();
                this.c = true;
                return;
            default:
                return;
        }
    }

    private void o() {
        cn.xiaochuankeji.tieba.ui.widget.g.a(getActivity());
        if (this.c) {
            this.e.e().a(rx.a.b.a.a()).b(new j<String>(this) {
                final /* synthetic */ FragmentCreateAudioHollow a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((String) obj);
                }

                public void onCompleted() {
                    cn.xiaochuankeji.tieba.ui.widget.g.c(this.a.getActivity());
                }

                public void onError(Throwable th) {
                    g.a(th.getMessage());
                }

                public void a(String str) {
                    this.a.a(str);
                }
            });
            return;
        }
        cn.xiaochuankeji.tieba.ui.widget.g.c(getActivity());
        p();
    }

    private void a(String str) {
        this.b.a(str, this.f, new IAudioPlayer.a(this) {
            final /* synthetic */ FragmentCreateAudioHollow a;

            {
                this.a = r1;
            }

            public void a(PlayerStatus playerStatus) {
                this.a.a = playerStatus;
                switch (playerStatus) {
                    case PLAYING:
                        this.a.c(true);
                        this.a.recordAnimView.a();
                        if (this.a.g != null) {
                            this.a.g.a(OptionType.RECORD_PLAYING);
                            return;
                        }
                        return;
                    case PAUSE:
                        this.a.c(false);
                        this.a.recordAnimView.b();
                        if (this.a.g != null) {
                            this.a.g.a(OptionType.RECORD_NEXT);
                            return;
                        }
                        return;
                    case END:
                        this.a.c(false);
                        this.a.recordAnimView.b();
                        if (this.a.g != null) {
                            this.a.g.a(OptionType.RECORD_NEXT);
                        }
                        this.a.hollowTime.setText(c.b(this.a.f));
                        return;
                    default:
                        return;
                }
            }

            public void a(final long j) {
                if (this.a.getActivity() != null) {
                    this.a.getActivity().runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass4 b;

                        public void run() {
                            this.b.a.hollowTime.setText(c.b(j));
                        }
                    });
                }
            }
        });
        p();
    }

    private void p() {
        switch (this.a) {
            case PLAYING:
            case LOADING:
                this.b.b();
                return;
            case PAUSE:
                this.b.c();
                return;
            case END:
            case PREPARE:
                this.b.a();
                this.c = false;
                return;
            default:
                return;
        }
    }

    private void c(boolean z) {
        boolean z2;
        float f;
        boolean z3 = true;
        float f2 = 0.4f;
        this.textPlay.setText(z ? "暂停" : "试听");
        View view = this.buttonReset;
        if (z) {
            z2 = false;
        } else {
            z2 = true;
        }
        view.setEnabled(z2);
        ImageView imageView = this.buttonMain;
        if (z) {
            z3 = false;
        }
        imageView.setEnabled(z3);
        View view2 = this.buttonReset;
        if (z) {
            f = 0.4f;
        } else {
            f = 1.0f;
        }
        view2.setAlpha(f);
        imageView = this.buttonMain;
        if (!z) {
            f2 = 1.0f;
        }
        imageView.setAlpha(f2);
    }

    private void d(boolean z) {
        int i;
        int i2 = 0;
        View view = this.welcomeLayout;
        if (z) {
            i = 8;
        } else {
            i = 0;
        }
        view.setVisibility(i);
        View view2 = this.timeLayout;
        if (!z) {
            i2 = 8;
        }
        view2.setVisibility(i2);
    }

    private void e(boolean z) {
        int i = 0;
        this.buttonReset.setVisibility(z ? 0 : 4);
        View view = this.buttonPlay;
        if (!z) {
            i = 4;
        }
        view.setVisibility(i);
        this.buttonReset.setEnabled(z);
        this.buttonPlay.setEnabled(z);
        this.buttonMain.setImageResource(z ? R.drawable.record : R.drawable.record_pause);
    }

    public void b(boolean z) {
        if (z && this.g != null) {
            this.g.a(OptionType.INIT);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.h.a();
    }

    public void onPause() {
        super.onPause();
        if (this.d != null && this.d == RecorderStatus.RECORDING) {
            this.e.b();
        }
        if (this.a != null && this.a.equals(PlayerStatus.PLAYING)) {
            this.b.b();
        }
    }

    public void onStop() {
        super.onStop();
        this.b.d();
    }

    public void onDestroy() {
        super.onDestroy();
        this.e.d();
        this.b.e();
    }

    public void a(b bVar) {
        this.g = bVar;
    }

    public void i() {
        if (this.f < 5) {
            g.a("据说走心的树洞倾诉都大于5秒");
        } else {
            this.e.e().a(new rx.b.b<String>(this) {
                final /* synthetic */ FragmentCreateAudioHollow a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void call(Object obj) {
                    a((String) obj);
                }

                public void a(String str) {
                    EditHollowActivity.a(this.a.getActivity(), str, this.a.f);
                }
            }, new rx.b.b<Throwable>(this) {
                final /* synthetic */ FragmentCreateAudioHollow a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void call(Object obj) {
                    a((Throwable) obj);
                }

                public void a(Throwable th) {
                    g.a(th.getMessage());
                }
            });
        }
    }

    public boolean j() {
        if (this.f <= 0) {
            return false;
        }
        new cn.xiaochuankeji.tieba.ui.widget.b.a.a(getActivity(), "提示", "你要放弃发表吗？").b("继续编辑", null).a("放弃", new OnClickListener(this) {
            final /* synthetic */ FragmentCreateAudioHollow a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.getActivity() != null) {
                    this.a.getActivity().finish();
                }
            }
        }).a();
        return true;
    }
}
