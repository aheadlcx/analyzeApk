package cn.xiaochuankeji.tieba.ui.my.ugcvideo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.d.i;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.json.MomentListJson;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.videomaker.a.a;
import cn.xiaochuankeji.tieba.ui.videomaker.a.b;
import java.util.ArrayList;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.j;

public class f extends cn.xiaochuankeji.tieba.ui.base.f {
    private a a;
    private ArrayList<Moment> b;
    private ArrayList<a> c;
    private cn.xiaochuankeji.tieba.api.ugcvideo.a d = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
    private long e;
    private long f = 0;
    private int g;
    private boolean h = false;
    private boolean i = false;

    public static f a(long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("s_key_uid", j);
        f fVar = new f();
        fVar.setArguments(bundle);
        return fVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = getArguments().getLong("s_key_uid");
        this.b = new ArrayList();
        this.c = new ArrayList();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.a = new a(getContext());
        this.a.a(new a.a(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.b(false);
            }
        });
        this.a.a(false);
        this.a.a((int) R.drawable.ic_topic_empty_post, "大神还没有发布过跟拍");
        return this.a.f_();
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.a.a(this.b);
        if (this.b.size() == 0) {
            b(true);
        }
    }

    public void onResume() {
        super.onResume();
        b();
    }

    private void b() {
        new b().a().a(rx.a.b.a.a()).b(new j<ArrayList<a>>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((ArrayList) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(ArrayList<a> arrayList) {
                this.a.c = arrayList;
                this.a.h = true;
                if (this.a.c.size() == 0) {
                    this.a.a.b((ArrayList) arrayList);
                    if (this.a.b.size() == 0) {
                        this.a.c();
                        return;
                    } else {
                        this.a.a.d();
                        return;
                    }
                }
                this.a.a.b(false);
                this.a.a.d();
                this.a.a.b((ArrayList) arrayList);
            }
        });
    }

    private void b(final boolean z) {
        this.d.b(this.e, this.f).a(rx.a.b.a.a()).b(new j<MomentListJson>(this) {
            final /* synthetic */ f b;

            public /* synthetic */ void onNext(Object obj) {
                a((MomentListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (this.b.getActivity() != null && this.b.isAdded()) {
                    this.b.i = true;
                    if (z && this.b.b.size() == 0 && this.b.c.size() == 0) {
                        this.b.c();
                    }
                }
            }

            public void a(MomentListJson momentListJson) {
                if (this.b.getActivity() != null && this.b.isAdded()) {
                    this.b.i = true;
                    if (z) {
                        this.b.g = momentListJson.total;
                        ((MyUgcVideoShowActivity) this.b.getActivity()).a(this.b.g);
                    }
                    if (momentListJson.momentList.size() > 0) {
                        if (z) {
                            this.b.a.d();
                        }
                        this.b.f = momentListJson.offset;
                        this.b.b.addAll(momentListJson.momentList);
                        this.b.a.b(false);
                        this.b.a.c(momentListJson.more == 1);
                    } else if (z && this.b.c.size() == 0) {
                        this.b.c();
                    }
                }
            }
        });
    }

    private boolean c() {
        if (!this.h || !this.i) {
            return false;
        }
        this.a.b(true);
        return true;
    }

    @l(a = ThreadMode.MAIN)
    public void synchWhenPlayFinish(UgcVideoActivity.b bVar) {
        if (isVisible() && bVar.c != null) {
            for (int i = 0; i < this.b.size(); i++) {
                if (((Moment) this.b.get(i)).id == bVar.c.id) {
                    if (bVar.c.ugcVideos.size() == 0 || ((UgcVideoInfoBean) bVar.c.ugcVideos.get(0)).id != bVar.c.id) {
                        this.b.remove(i);
                        this.g--;
                        ((MyUgcVideoShowActivity) getActivity()).a(this.g);
                        if (this.b.size() == 0) {
                            this.a.b(true);
                        }
                    } else {
                        this.b.remove(i);
                        this.b.add(i, bVar.c);
                    }
                    this.a.e();
                    return;
                }
            }
        }
    }

    @l(a = ThreadMode.MAIN)
    public void draftPublished(i iVar) {
        if (iVar != null) {
            this.b.clear();
            this.h = false;
            this.i = false;
            this.f = 0;
            b(true);
        }
    }
}
