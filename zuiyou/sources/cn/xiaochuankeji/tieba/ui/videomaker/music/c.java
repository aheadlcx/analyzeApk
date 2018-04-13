package cn.xiaochuankeji.tieba.ui.videomaker.music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.aop.permission.e;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicHomeJson;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicJson;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.j;
import cn.xiaochuankeji.tieba.ui.videomaker.music.b.a;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView.b;
import java.io.Serializable;
import java.util.ArrayList;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class c extends f implements a {
    private UltimateRecyclerView a;
    private cn.xiaochuankeji.tieba.ui.utils.f b;
    private b c;
    private cn.xiaochuankeji.tieba.api.ugcvideo.a d = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
    private long e;
    private ArrayList<UgcVideoMusicJson> f = new ArrayList();
    private boolean g;
    private long h;
    private UgcVideoMusicJson i = null;

    public static c a(long j) {
        c cVar = new c();
        Bundle bundle = new Bundle();
        bundle.putLong("key_cid", j);
        cVar.setArguments(bundle);
        return cVar;
    }

    public static c a(long j, UgcVideoMusicJson ugcVideoMusicJson, ArrayList<UgcVideoMusicJson> arrayList, boolean z, long j2) {
        c cVar = new c();
        Bundle bundle = new Bundle();
        bundle.putLong("key_cid", j);
        if (ugcVideoMusicJson != null) {
            bundle.putSerializable("key_select_music_json", ugcVideoMusicJson);
        }
        bundle.putSerializable("key_init_music_json_list", arrayList);
        bundle.putBoolean("key_more", z);
        bundle.putLong("key_offset", j2);
        cVar.setArguments(bundle);
        return cVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.e = arguments.getLong("key_cid");
        this.g = arguments.getBoolean("key_more");
        this.h = arguments.getLong("key_offset");
        Serializable serializable = arguments.getSerializable("key_select_music_json");
        if (serializable != null) {
            this.i = (UgcVideoMusicJson) serializable;
        }
        serializable = arguments.getSerializable("key_init_music_json_list");
        if (serializable != null) {
            ArrayList arrayList = (ArrayList) serializable;
            if (arrayList != null && arrayList.size() > 0) {
                this.f.addAll(arrayList);
            }
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_music_list, null);
        this.a = (UltimateRecyclerView) inflate.findViewById(R.id.ultimateRecyclerView);
        this.a.g.setId(R.id.id_stickynavlayout_innerscrollview);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.c = new b(getContext(), this.f, this, ((SelectVideoMusicActivity) getActivity()).v(), this.i);
        this.a.setLayoutManager(new LinearLayoutManager(getContext()));
        this.a.setOnLoadMoreListener(new b(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                this.a.b(this.a.h);
            }
        });
        this.a.a(R.layout.common_empty_view, UltimateRecyclerView.a, UltimateRecyclerView.a);
        this.a.setLoadMoreView(new j(getContext()));
        this.a.setAdapter(this.c);
        if (this.f.size() == 0 || !this.g) {
            this.a.h();
        }
        this.b = new cn.xiaochuankeji.tieba.ui.utils.f(getContext(), new cn.xiaochuankeji.tieba.ui.utils.f.a(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.b(false);
            }
        });
        ((FrameLayout) view.findViewById(R.id.rootView)).addView(this.b, new LayoutParams(-1, -1));
    }

    public boolean b(boolean z) {
        if (z) {
            this.f.clear();
            this.c.notifyDataSetChanged();
        }
        if (this.f.size() != 0) {
            return false;
        }
        b(0);
        return true;
    }

    public void b() {
        this.c.notifyDataSetChanged();
    }

    private void b(long j) {
        this.d.c(this.e, j).a(rx.a.b.a.a()).b(new rx.j<UgcVideoMusicHomeJson>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((UgcVideoMusicHomeJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.a(th.getMessage());
                if (this.a.f.size() == 0) {
                    this.a.b.a(R.drawable.ic_network_error, "断网了，小右加载不出列表啦");
                }
            }

            public void a(UgcVideoMusicHomeJson ugcVideoMusicHomeJson) {
                boolean z = true;
                if (ugcVideoMusicHomeJson.musicList != null && ugcVideoMusicHomeJson.musicList.size() > 0) {
                    this.a.f.addAll(ugcVideoMusicHomeJson.musicList);
                }
                c cVar = this.a;
                if (ugcVideoMusicHomeJson.more != 1) {
                    z = false;
                }
                cVar.g = z;
                this.a.h = ugcVideoMusicHomeJson.offset;
                if (this.a.g) {
                    this.a.a.f();
                } else {
                    this.a.a.h();
                }
                this.a.c.notifyDataSetChanged();
                if (this.a.f.size() != 0) {
                    this.a.b.setVisibility(8);
                } else if (this.a.e == -1) {
                    this.a.b.b(R.drawable.img_search_music_empty, "还没有收藏音乐");
                } else {
                    this.a.b.b(R.drawable.img_exception_network_error_in_musicselect, "列表空空小右懵懵");
                }
            }
        });
    }

    @l(a = ThreadMode.MAIN)
    public void musicView(cn.xiaochuankeji.tieba.background.d.c cVar) {
        this.c.a(cVar.a(), cVar.b());
    }

    public void a(final String str, final long j) {
        cn.xiaochuankeji.aop.permission.a.a(getContext()).a(new PermissionItem("android.permission.WRITE_EXTERNAL_STORAGE").needGotoSetting(true).runIgnorePermission(false).deniedMessage("播放音乐需要本地读写权限").settingText("去设置"), new e(this) {
            final /* synthetic */ c c;

            public void permissionGranted() {
                ((SelectVideoMusicActivity) this.c.getActivity()).a(str, j);
            }

            public void permissionDenied() {
            }
        });
    }

    public void a(UgcVideoMusicJson ugcVideoMusicJson) {
        ((SelectVideoMusicActivity) getActivity()).a(ugcVideoMusicJson);
    }
}
