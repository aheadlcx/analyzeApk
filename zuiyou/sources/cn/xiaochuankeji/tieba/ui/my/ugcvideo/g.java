package cn.xiaochuankeji.tieba.ui.my.ugcvideo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.ugcvideo.a;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.json.MomentListJson;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.post.LikedUsersActivity;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity.b;
import java.util.ArrayList;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.j;

public class g extends f {
    private a a;
    private ArrayList<Moment> b;
    private a c = new a();
    private long d;
    private long e = 0;
    private int f;

    public static g a(long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("s_key_uid", j);
        g gVar = new g();
        gVar.setArguments(bundle);
        return gVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.d = getArguments().getLong("s_key_uid");
        this.b = new ArrayList();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.a = new a(getContext());
        this.a.a(new a.a(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.b(false);
            }
        });
        this.a.a(false);
        this.a.a((int) R.drawable.ic_topic_empty_post, "大神还没有喜欢过跟拍");
        return this.a.f_();
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.a.a(this.b);
        if (this.b.size() == 0) {
            b(true);
        }
    }

    private void b(final boolean z) {
        this.c.a(this.d, this.e).a(rx.a.b.a.a()).b(new j<MomentListJson>(this) {
            final /* synthetic */ g b;

            public /* synthetic */ void onNext(Object obj) {
                a((MomentListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (this.b.getActivity() != null && this.b.isAdded()) {
                    this.b.a.b(this.b.b.size() == 0);
                }
            }

            public void a(MomentListJson momentListJson) {
                if (this.b.getActivity() != null && this.b.isAdded()) {
                    if (z) {
                        this.b.f = momentListJson.total;
                        ((MyUgcVideoShowActivity) this.b.getActivity()).b(this.b.f);
                    }
                    if (momentListJson.momentList.size() > 0) {
                        if (z) {
                            this.b.a.d();
                        }
                        this.b.e = momentListJson.offset;
                        this.b.b.addAll(momentListJson.momentList);
                        this.b.a.c(momentListJson.more == 1);
                    } else if (z) {
                        this.b.a.b(true);
                    }
                }
            }
        });
    }

    @l(a = ThreadMode.MAIN)
    public void onEventMainThread(b bVar) {
        if (isVisible() && bVar.c != null) {
            for (int i = 0; i < this.b.size(); i++) {
                if (((Moment) this.b.get(i)).id == bVar.c.id) {
                    if (bVar.c.ugcVideos.size() != 0 && ((UgcVideoInfoBean) bVar.c.ugcVideos.get(0)).id == bVar.c.id && ((UgcVideoInfoBean) bVar.c.ugcVideos.get(0)).liked == 1) {
                        this.b.remove(i);
                        this.b.add(i, bVar.c);
                    } else {
                        this.b.remove(i);
                        this.f--;
                        ((MyUgcVideoShowActivity) getActivity()).b(this.f);
                        if (this.b.size() == 0) {
                            this.a.b(true);
                        }
                    }
                    this.a.e();
                    return;
                }
            }
        }
    }

    @l(a = ThreadMode.MAIN)
    public void onEventMainThread(LikedUsersActivity.a aVar) {
        for (int i = 0; i < this.b.size(); i++) {
            if (((Moment) this.b.get(i)).id == aVar.a) {
                this.b.remove(i);
                this.f--;
                ((MyUgcVideoShowActivity) getActivity()).b(this.f);
                if (this.b.size() == 0) {
                    this.a.b(true);
                }
                this.a.e();
                return;
            }
        }
    }
}
