package cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuan.image.MimeType;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.i;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.c;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.adapter.CustomStickerAdapter;
import cn.xiaochuankeji.tieba.ui.widget.a.a;
import com.izuiyou.a.a.b;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.b.g;
import rx.d;
import rx.e;

public class CustomStickerFragment extends c {
    private CustomStickerAdapter a = new CustomStickerAdapter();
    @BindView
    View progress;
    @BindView
    RecyclerView recyclerView;

    public static CustomStickerFragment i() {
        CustomStickerFragment customStickerFragment = new CustomStickerFragment();
        customStickerFragment.setArguments(new Bundle());
        return customStickerFragment;
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_sticker_default, viewGroup, false);
        ButterKnife.a(this, inflate);
        return inflate;
    }

    protected void e() {
        if (f()) {
            this.a.a((c) this);
            this.recyclerView.setItemAnimator(new a());
            i.a(this.recyclerView);
            LayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
            gridLayoutManager.setOrientation(1);
            this.recyclerView.setLayoutManager(gridLayoutManager);
            this.recyclerView.setAdapter(this.a);
            this.a.a(0, cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a.a);
            this.a.a(1, cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a.b);
            j();
        }
    }

    public void onDetach() {
        super.onDetach();
        if (this.a != null) {
            this.a.a();
        }
    }

    private void j() {
        d.a(Boolean.valueOf(true)).d(new g<Boolean, List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a>>(this) {
            final /* synthetic */ CustomStickerFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((Boolean) obj);
            }

            public List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> a(Boolean bool) {
                return c.a().a("自定义");
            }
        }).a(new g<List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a>, Boolean>(this) {
            final /* synthetic */ CustomStickerFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((List) obj);
            }

            public Boolean a(List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> list) {
                return Boolean.valueOf(list != null);
            }
        }).d(new g<List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a>, List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a>>(this) {
            final /* synthetic */ CustomStickerFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((List) obj);
            }

            public List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> a(List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> list) {
                list.add(0, cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a.a);
                list.add(1, cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a.b);
                return list;
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a>>(this) {
            final /* synthetic */ CustomStickerFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((List) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> list) {
                this.a.a.a((Collection) list);
                this.a.progress.setVisibility(8);
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1841 && i2 == -1 && intent != null) {
            a((ArrayList) intent.getSerializableExtra("key_selected_local_media"));
        }
    }

    private void a(ArrayList<LocalMedia> arrayList) {
        d.a((Object) arrayList).a(new g<ArrayList<LocalMedia>, Boolean>(this) {
            final /* synthetic */ CustomStickerFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((ArrayList) obj);
            }

            public Boolean a(ArrayList<LocalMedia> arrayList) {
                boolean z = (arrayList == null || arrayList.isEmpty()) ? false : true;
                return Boolean.valueOf(z);
            }
        }).b(new g<ArrayList<LocalMedia>, Boolean>(this) {
            final /* synthetic */ CustomStickerFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((ArrayList) obj);
            }

            public Boolean a(ArrayList<LocalMedia> arrayList) {
                File file = new File(cn.xiaochuankeji.tieba.background.a.e().I(), ".nomedia");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return Boolean.valueOf(true);
            }
        }).d(new g<ArrayList<LocalMedia>, Boolean>(this) {
            final /* synthetic */ CustomStickerFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((ArrayList) obj);
            }

            public Boolean a(ArrayList<LocalMedia> arrayList) {
                List arrayList2 = new ArrayList();
                int i = this.a.getResources().getDisplayMetrics().widthPixels;
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    LocalMedia localMedia = (LocalMedia) it.next();
                    b.c(localMedia.path);
                    cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a aVar = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a();
                    File file = new File(localMedia.path);
                    if (file != null && file.exists() && file.isFile()) {
                        long lastModified = file.lastModified();
                        String str = String.valueOf(lastModified) + "_" + String.valueOf(file.length());
                        File file2 = new File(cn.xiaochuankeji.tieba.background.a.e().I(), str);
                        if (!file2.exists()) {
                            cn.htjyb.c.a.b.a(file, file2);
                            file2.setLastModified(lastModified);
                        }
                        aVar.p = String.valueOf(System.currentTimeMillis());
                        aVar.d = file2.getAbsolutePath();
                        aVar.f = file.getAbsolutePath();
                        aVar.r = file2.length();
                        aVar.h = localMedia.width;
                        aVar.i = localMedia.height;
                        aVar.s = ((float) localMedia.width) / ((float) i) > 0.6f ? 60.0f : -100.0f;
                        aVar.q = str;
                        String mimeType = (file.getName().endsWith("gif") || file.getName().endsWith("GIF")) ? MimeType.GIF.toString() : localMedia.mimeType;
                        aVar.l = mimeType;
                        aVar.o = "自定义";
                        aVar.c = -1;
                        arrayList2.add(aVar);
                    }
                }
                c.a().a(arrayList2);
                return Boolean.valueOf(true);
            }
        }).b(rx.f.a.c()).a(rx.f.a.c()).a(new e<Boolean>(this) {
            final /* synthetic */ CustomStickerFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Boolean) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(Boolean bool) {
                this.a.j();
            }
        });
    }

    @l(a = ThreadMode.MAIN)
    public void stickerReload(cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.b bVar) {
        if (bVar != null && 2 == bVar.a) {
            j();
        }
    }
}
