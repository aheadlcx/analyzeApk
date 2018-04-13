package cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.json.UgcEmoji;
import cn.xiaochuankeji.tieba.json.UgcEmojiListJson;
import cn.xiaochuankeji.tieba.ui.widget.overscroll.OverScrollLayout;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.LinkedList;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.e;
import rx.j;

public class b extends c implements cn.xiaochuankeji.tieba.ui.widget.overscroll.OverScrollLayout.a {
    OverScrollLayout a;
    RecyclerView b;
    private FragmentManager c;
    private cn.xiaochuankeji.tieba.ui.videomaker.sticker.a d;
    private View e;
    private cn.xiaochuankeji.tieba.ui.videomaker.sticker.adapter.a f = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.adapter.a();
    private cn.xiaochuankeji.tieba.api.ugcvideo.a g = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
    private cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.a h;
    private UgcEmojiListJson i;
    private UgcEmojiListJson j;
    private String k;

    public static class a {
        public String a;
        public String b;
    }

    public void onDestroyView() {
        this.a.c();
        super.onDestroyView();
    }

    private static b a(FragmentManager fragmentManager, cn.xiaochuankeji.tieba.ui.videomaker.sticker.a aVar, String str) {
        b bVar = new b();
        bVar.setArguments(new Bundle());
        bVar.c = fragmentManager;
        bVar.d = aVar;
        bVar.k = str;
        return bVar;
    }

    public static void a(@NonNull FragmentManager fragmentManager, @IdRes int i, String str, cn.xiaochuankeji.tieba.ui.videomaker.sticker.a aVar) {
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_bottom, R.anim.slide_in_bottom, R.anim.slide_out_bottom).replace(i, a(fragmentManager, aVar, str), "V_S_F_magic_emotion").addToBackStack("V_S_F_magic_emotion").commit();
        aVar.a();
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.view_video_magic_emotion_browser, viewGroup, false);
        this.a = (OverScrollLayout) inflate.findViewById(R.id.overScroll);
        this.a.setEnableStart(false);
        this.a.b();
        this.a.setOnOverScrollListener(this);
        this.e = inflate.findViewById(R.id.root);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.l();
            }
        });
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (f()) {
            this.b = this.a.getScrollView();
            LayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
            gridLayoutManager.setOrientation(1);
            this.b.setLayoutManager(gridLayoutManager);
            this.b.setAdapter(this.f);
            this.h = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.a(cn.xiaochuankeji.tieba.background.a.e().y() + "magicEmotion.dat");
            this.h.a().a(rx.a.b.a.a()).b(new j<String>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((String) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                }

                public void a(String str) {
                    if (TextUtils.isEmpty(str)) {
                        this.a.a.a();
                        this.a.a(0, false);
                        return;
                    }
                    this.a.i = (UgcEmojiListJson) JSON.parseObject(str, UgcEmojiListJson.class);
                    if (this.a.i.list.size() > 0) {
                        this.a.f.a(this.a.a(this.a.i.list), this.a.k);
                        this.a.a.a(this.a.i.more == 1);
                        this.a.a(0, true);
                        return;
                    }
                    this.a.a.a();
                    this.a.a(0, false);
                }
            });
        }
    }

    protected void e() {
    }

    public void onPause() {
        super.onPause();
        if (this.j != null) {
            this.h.a(JSON.toJSONString(this.j));
        } else if (this.i != null) {
            this.h.a(JSON.toJSONString(this.i));
        }
    }

    public void j() {
        a(0, false);
    }

    public void k() {
        a(this.i.offset, false);
    }

    public boolean i() {
        return this.i.more == 1;
    }

    private void a(final int i, final boolean z) {
        long j = 0;
        if (this.i != null) {
            j = this.i.version;
        }
        this.g.a(3, i, j).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<UgcEmojiListJson>(this) {
            final /* synthetic */ b c;

            public /* synthetic */ void onNext(Object obj) {
                a((UgcEmojiListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(UgcEmojiListJson ugcEmojiListJson) {
                this.c.a.b();
                if (z && i == 0) {
                    if (this.c.i.version != ugcEmojiListJson.version) {
                        if (this.c.i.more == 1) {
                            this.c.i = ugcEmojiListJson;
                            this.c.f.a(this.c.a(ugcEmojiListJson.list), this.c.k);
                        } else {
                            this.c.j = ugcEmojiListJson;
                        }
                    } else {
                        return;
                    }
                } else if (z || i != 0) {
                    this.c.i.list.addAll(ugcEmojiListJson.list);
                    this.c.i.more = ugcEmojiListJson.more;
                    this.c.i.cateid = ugcEmojiListJson.cateid;
                    this.c.i.offset = ugcEmojiListJson.offset;
                    this.c.f.a(this.c.a(ugcEmojiListJson.list));
                } else {
                    this.c.i = ugcEmojiListJson;
                    this.c.f.a(this.c.a(ugcEmojiListJson.list), this.c.k);
                }
                this.c.a.a(this.c.i());
            }
        });
    }

    private LinkedList<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> a(ArrayList<UgcEmoji> arrayList) {
        LinkedList<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> linkedList = new LinkedList();
        if (arrayList == null || arrayList.isEmpty()) {
            return linkedList;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            UgcEmoji ugcEmoji = (UgcEmoji) arrayList.get(i);
            cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a aVar = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a();
            aVar.o = "贴纸";
            aVar.p = String.valueOf(ugcEmoji.id);
            aVar.g = cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/png/id/", (long) ugcEmoji.img4preview.id, null);
            aVar.e = ugcEmoji.img;
            aVar.s = ugcEmoji.percent;
            aVar.t = ugcEmoji;
            linkedList.add(aVar);
        }
        return linkedList;
    }

    @l(a = ThreadMode.MAIN)
    public void addMagic(a aVar) {
        if (this.d != null) {
            this.d.a(new cn.xiaochuankeji.tieba.ui.videomaker.VideoRecordActivity.b(aVar.a, aVar.b));
        }
    }

    private void l() {
        if (this.c != null && this.c.popBackStackImmediate("V_S_F_magic_emotion", 1)) {
            if (this.d != null) {
                this.d.b();
            }
            this.c = null;
        }
    }
}
