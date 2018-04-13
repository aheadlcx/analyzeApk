package cn.xiaochuankeji.tieba.ui.hollow.detail;

import android.annotation.SuppressLint;
import android.arch.lifecycle.q;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.base.l;
import cn.xiaochuankeji.tieba.ui.hollow.data.AudioDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.MsgDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.report.ReportPlayHollowJson;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer.PlayerStatus;
import cn.xiaochuankeji.tieba.ui.hollow.util.a;
import cn.xiaochuankeji.tieba.ui.hollow.widget.AudioPlayView;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.b;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.c;
import cn.xiaochuankeji.tieba.ui.widget.f;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.e.d;
import java.util.ArrayList;
import org.greenrobot.eventbus.ThreadMode;

public class FragmentMyReply extends l {
    private b a;
    private MyReplyModel b;
    private Unbinder c;
    private PlayerStatus d;
    private IAudioPlayer e;
    @BindView
    ImageView emptyImage;
    @BindView
    TextView emptyText;
    @BindView
    RelativeLayout emptyView;
    private String f;
    private AudioDataBean g;
    @BindView
    RecyclerView recyclerView;
    @BindView
    SmartRefreshLayout refreshLayout;

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.layout_my_hollow, null);
        this.c = ButterKnife.a(this, inflate);
        this.e = new a(getActivity());
        i();
        k();
        return inflate;
    }

    private void i() {
        this.a = new b(getActivity());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.a);
        this.recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 20);
        this.recyclerView.setAnimation(null);
        this.a.a(new a(this) {
            final /* synthetic */ FragmentMyReply a;

            {
                this.a = r1;
            }

            public void a(long j) {
                this.a.a(j);
            }
        });
        this.a.a(new b(this) {
            final /* synthetic */ FragmentMyReply a;

            {
                this.a = r1;
            }

            public void onClick(AudioDataBean audioDataBean, AudioPlayView audioPlayView) {
                if (audioDataBean != null) {
                    if (audioDataBean.a(this.a.g)) {
                        this.a.j();
                    } else {
                        this.a.a(audioDataBean, audioPlayView);
                    }
                }
            }
        });
    }

    private void a(final long j) {
        SDBottomSheet sDBottomSheet = new SDBottomSheet(getActivity(), new b(this) {
            final /* synthetic */ FragmentMyReply b;

            @SuppressLint({"SwitchIntDef"})
            public void a(int i) {
                switch (i) {
                    case 9:
                        this.b.b(j);
                        return;
                    default:
                        return;
                }
            }
        });
        ArrayList arrayList = new ArrayList();
        arrayList.add(new c(R.drawable.icon_option_delete, "删除", 9));
        sDBottomSheet.a(arrayList, null);
        sDBottomSheet.b();
    }

    private void b(final long j) {
        f.a("提示", "删除后不可恢复，确认删除？", getActivity(), new f.a(this) {
            final /* synthetic */ FragmentMyReply b;

            public void a(boolean z) {
                if (z) {
                    this.b.b.a(this.b.getActivity(), j, new b(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z) {
                            this.a.b.emptyView.setVisibility(z ? 0 : 8);
                        }
                    });
                }
            }
        });
    }

    private void a(final AudioDataBean audioDataBean, final AudioPlayView audioPlayView) {
        this.e.a(audioDataBean, new IAudioPlayer.a(this) {
            final /* synthetic */ FragmentMyReply c;

            public void a(PlayerStatus playerStatus) {
                this.c.d = playerStatus;
                switch (playerStatus) {
                    case LOADING:
                        audioPlayView.a();
                        return;
                    case PLAYING:
                        audioPlayView.b();
                        return;
                    case PAUSE:
                        audioPlayView.d();
                        return;
                    case END:
                        audioPlayView.c();
                        return;
                    default:
                        return;
                }
            }

            public void a(final long j) {
                this.c.getActivity().runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass7 b;

                    public void run() {
                        audioPlayView.a(j);
                    }
                });
                try {
                    MsgDataBean a = this.c.a.a(audioDataBean);
                    if (a != null) {
                        ReportPlayHollowJson reportPlayHollowJson = new ReportPlayHollowJson();
                        reportPlayHollowJson.ownerId = a.id;
                        reportPlayHollowJson.owner = "flow_xmsg";
                        reportPlayHollowJson.audioDuration = audioDataBean.dur;
                        reportPlayHollowJson.audioUri = audioDataBean.uri;
                        reportPlayHollowJson.deviceType = 0;
                        reportPlayHollowJson.playDur = audioDataBean.dur - j;
                        reportPlayHollowJson.version = cn.xiaochuankeji.tieba.background.utils.d.a.f;
                        cn.xiaochuankeji.tieba.ui.hollow.report.a.a().a(reportPlayHollowJson);
                    }
                } catch (Exception e) {
                    com.izuiyou.a.a.b.e(e);
                }
            }
        });
        this.g = audioDataBean;
        j();
    }

    private void j() {
        switch (this.d) {
            case LOADING:
            case PLAYING:
                this.e.b();
                return;
            case PAUSE:
                this.e.c();
                return;
            case END:
            case PREPARE:
                this.e.a();
                return;
            default:
                return;
        }
    }

    private void k() {
        this.refreshLayout.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ FragmentMyReply a;

            {
                this.a = r1;
            }

            public void a(final h hVar) {
                this.a.b.a(this.a.f, new a(this) {
                    final /* synthetic */ AnonymousClass8 b;

                    public void a(boolean z, String str) {
                        if (z) {
                            hVar.u();
                        } else {
                            hVar.t();
                        }
                        this.b.a.f = str;
                    }

                    public void a(Throwable th) {
                        if (hVar != null) {
                            hVar.u();
                        }
                        if (th instanceof ClientErrorException) {
                            g.a(th.getMessage());
                        }
                    }
                });
            }
        });
        this.refreshLayout.a(new d(this) {
            final /* synthetic */ FragmentMyReply a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
            }

            public void a_(h hVar) {
                this.a.l();
            }
        });
        this.emptyImage.setImageResource(R.drawable.ic_empty_chat);
        this.emptyText.setText("若不寻人聊，只能待佳音");
    }

    private void a(boolean z, String str) {
        this.refreshLayout.a(z);
        this.f = str;
    }

    private void l() {
        this.b.a(new a(this) {
            final /* synthetic */ FragmentMyReply a;

            {
                this.a = r1;
            }

            public void a(boolean z, String str) {
                this.a.refreshLayout.m();
                this.a.a(z, str);
            }

            public void a(Throwable th) {
                if (this.a.refreshLayout != null) {
                    this.a.refreshLayout.m();
                }
                g.a(th.getMessage());
            }
        }, new b(this) {
            final /* synthetic */ FragmentMyReply a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                this.a.emptyView.setVisibility(z ? 0 : 8);
            }
        });
    }

    protected void e() {
        this.b = (MyReplyModel) q.a((Fragment) this).a(MyReplyModel.class);
        this.b.a(this.a);
        this.refreshLayout.q();
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void onDeleteMsg(cn.xiaochuankeji.tieba.ui.hollow.recommend.d dVar) {
        this.a.a(dVar.a, new d(this) {
            final /* synthetic */ FragmentMyReply a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                this.a.emptyView.setVisibility(z ? 0 : 8);
            }
        });
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void onAddNewMsg(cn.xiaochuankeji.tieba.ui.hollow.recommend.b bVar) {
        this.a.a(bVar.a);
        if (bVar != null) {
            this.emptyView.setVisibility(8);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.e.e();
        this.c.a();
    }

    public void b(boolean z) {
        if (this.d != null && !z && this.d.equals(PlayerStatus.PLAYING)) {
            j();
        }
    }
}
