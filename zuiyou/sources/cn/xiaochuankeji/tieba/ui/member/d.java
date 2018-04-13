package cn.xiaochuankeji.tieba.ui.member;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.json.MomentListJson;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.my.ugcvideo.a;
import java.util.ArrayList;
import java.util.Iterator;
import rx.j;

public class d extends f {
    private a a;
    private ArrayList<Moment> b;
    private ArrayList<UgcVideoInfoBean> c;
    private cn.xiaochuankeji.tieba.api.ugcvideo.a d = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
    private long e;
    private long f = 0;

    public static d a(long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("s_key_uid", j);
        d dVar = new d();
        dVar.setArguments(bundle);
        return dVar;
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
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.b(false);
            }
        });
        this.a.a(true);
        this.a.c();
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
        this.d.b(this.e, this.f).a(rx.a.b.a.a()).b(new j<MomentListJson>(this) {
            final /* synthetic */ d b;

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
                boolean z = true;
                if (this.b.getActivity() != null && this.b.isAdded()) {
                    if (momentListJson.momentList.size() > 0) {
                        if (z) {
                            this.b.a.d();
                        }
                        this.b.f = momentListJson.offset;
                        this.b.b.addAll(momentListJson.momentList);
                        this.b.b();
                        a b = this.b.a;
                        if (momentListJson.more != 1) {
                            z = false;
                        }
                        b.c(z);
                    } else if (z) {
                        this.b.a.b(true);
                    }
                }
            }
        });
    }

    private void b() {
        this.c.clear();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            this.c.add((UgcVideoInfoBean) ((Moment) it.next()).ugcVideos.get(0));
        }
    }
}
