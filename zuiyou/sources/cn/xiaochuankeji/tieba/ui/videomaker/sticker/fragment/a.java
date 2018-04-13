package cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.json.UgcEmojiListJson;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.adapter.WebPStickerAdapter;
import cn.xiaochuankeji.tieba.ui.widget.overscroll.OverScrollLayout;
import com.alibaba.fastjson.JSON;
import rx.e;
import rx.j;

public class a extends c implements cn.xiaochuankeji.tieba.ui.widget.overscroll.OverScrollLayout.a {
    OverScrollLayout a;
    RecyclerView b;
    private WebPStickerAdapter c = new WebPStickerAdapter();
    private cn.xiaochuankeji.tieba.api.ugcvideo.a d = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
    private cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.a e;
    private UgcEmojiListJson f;
    private UgcEmojiListJson g;

    public static a i() {
        a aVar = new a();
        aVar.setArguments(new Bundle());
        return aVar;
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = new OverScrollLayout(BaseApplication.getAppContext());
        this.a.setEnableStart(false);
        this.a.b();
        this.a.setOnOverScrollListener(this);
        return this.a;
    }

    protected void e() {
        if (f()) {
            this.b = this.a.getScrollView();
            LayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
            gridLayoutManager.setOrientation(1);
            this.b.setLayoutManager(gridLayoutManager);
            this.b.setAdapter(this.c);
            this.e = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.a(cn.xiaochuankeji.tieba.background.a.e().y() + "gifs.dat");
            this.e.a().a(rx.a.b.a.a()).b(new j<String>(this) {
                final /* synthetic */ a a;

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
                    this.a.f = (UgcEmojiListJson) JSON.parseObject(str, UgcEmojiListJson.class);
                    if (this.a.f.list.size() > 0) {
                        this.a.c.a(this.a.f.list);
                        this.a.a.a(this.a.f.more == 1);
                        this.a.a(0, true);
                        return;
                    }
                    this.a.a.a();
                    this.a.a(0, false);
                }
            });
        }
    }

    public void onDestroyView() {
        this.a.c();
        super.onDestroyView();
    }

    public void onPause() {
        super.onPause();
        if (this.g != null) {
            this.e.a(JSON.toJSONString(this.g));
        } else if (this.f != null) {
            this.e.a(JSON.toJSONString(this.f));
        }
    }

    public void j() {
        a(0, false);
    }

    public void k() {
        a(this.f.offset, false);
    }

    public boolean l() {
        return this.f.more == 1;
    }

    private void a(final int i, final boolean z) {
        long j = 0;
        if (this.f != null) {
            j = this.f.version;
        }
        this.d.a(2, i, j).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<UgcEmojiListJson>(this) {
            final /* synthetic */ a c;

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
                    if (this.c.f.version != ugcEmojiListJson.version) {
                        if (this.c.f.more == 1) {
                            this.c.f = ugcEmojiListJson;
                            this.c.c.a(this.c.f.list);
                        } else {
                            this.c.g = ugcEmojiListJson;
                        }
                    } else {
                        return;
                    }
                } else if (z || i != 0) {
                    this.c.f.list.addAll(ugcEmojiListJson.list);
                    this.c.f.more = ugcEmojiListJson.more;
                    this.c.f.cateid = ugcEmojiListJson.cateid;
                    this.c.f.offset = ugcEmojiListJson.offset;
                    this.c.c.b(ugcEmojiListJson.list);
                    if (ugcEmojiListJson.list.size() > 0 && this.c.b != null) {
                        this.c.b.scrollBy(0, (this.c.b.getResources().getDisplayMetrics().widthPixels / 3) / 3);
                    }
                } else {
                    this.c.f = ugcEmojiListJson;
                    this.c.c.a(ugcEmojiListJson.list);
                }
                this.c.a.a(this.c.l());
            }
        });
    }
}
