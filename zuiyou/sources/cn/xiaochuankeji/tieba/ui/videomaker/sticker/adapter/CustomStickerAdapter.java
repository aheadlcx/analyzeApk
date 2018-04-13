package cn.xiaochuankeji.tieba.ui.videomaker.sticker.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuan.image.MimeType;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.i;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.SelectPicturesActivity;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.SelectPicturesActivity.SelectEntranceType;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment.StickerBrowserFragment;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment.c;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class CustomStickerAdapter extends Adapter<StickerViewHolder> {
    private ArrayList<a> a = new ArrayList();
    private c b;
    private cn.xiaochuan.image.b.a c = new cn.xiaochuan.image.b.a();

    static class StickerViewHolder extends ViewHolder {
        @BindView
        AppCompatImageView image;

        public StickerViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.a(this, this.itemView);
        }
    }

    public class StickerViewHolder_ViewBinding implements Unbinder {
        private StickerViewHolder b;

        @UiThread
        public StickerViewHolder_ViewBinding(StickerViewHolder stickerViewHolder, View view) {
            this.b = stickerViewHolder;
            stickerViewHolder.image = (AppCompatImageView) b.b(view, R.id.image, "field 'image'", AppCompatImageView.class);
        }

        @CallSuper
        public void a() {
            StickerViewHolder stickerViewHolder = this.b;
            if (stickerViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.b = null;
            stickerViewHolder.image = null;
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((StickerViewHolder) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public void a(@NonNull c cVar) {
        this.b = cVar;
    }

    public StickerViewHolder a(ViewGroup viewGroup, int i) {
        return new StickerViewHolder(LayoutInflater.from(BaseApplication.getAppContext()).inflate(R.layout.view_item_sticker_custom, viewGroup, false));
    }

    public void a(StickerViewHolder stickerViewHolder, int i) {
        final a aVar = (a) this.a.get(i);
        if (aVar == a.a || aVar == a.b) {
            stickerViewHolder.image.setImageResource(aVar == a.a ? R.drawable.ic_sticker_add : R.drawable.ic_sticker_delete);
            stickerViewHolder.itemView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ CustomStickerAdapter b;

                public void onClick(View view) {
                    if (this.b.b == null) {
                        return;
                    }
                    if (aVar == a.a) {
                        Intent intent = new Intent(this.b.b.getActivity(), SelectPicturesActivity.class);
                        intent.putExtra("kKeyPictureSelectType", SelectEntranceType.kSticker);
                        intent.putExtra("kKeyEnableSwipeBack", false);
                        this.b.b.startActivityForResult(intent, 1841);
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putInt("S_L_F_MODE", aVar == a.a ? 2 : 3);
                    StickerBrowserFragment.a(this.b.b.getChildFragmentManager(), bundle);
                }
            });
        } else if (!TextUtils.isEmpty(aVar.d)) {
            final File file = new File(aVar.d);
            if (!file.exists()) {
                File file2 = new File(aVar.f);
                long lastModified = file2.lastModified();
                String str = String.valueOf(lastModified) + "_" + String.valueOf(file2.length());
                if (file2.exists() && file2.length() > 0 && String.valueOf(aVar.q).equalsIgnoreCase(str)) {
                    cn.htjyb.c.a.b.a(file2, file);
                    file.setLastModified(lastModified);
                }
            }
            if (file.exists()) {
                final boolean equalsIgnoreCase = MimeType.GIF.toString().equalsIgnoreCase(aVar.l);
                Context appContext = BaseApplication.getAppContext();
                int a = (int) i.a(appContext.getResources(), 72.0f);
                this.c.a(appContext, a, a, stickerViewHolder.image, Uri.fromFile(file));
                stickerViewHolder.itemView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ CustomStickerAdapter c;

                    public void onClick(View view) {
                        cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.b bVar = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.b(equalsIgnoreCase ? 17 : 11);
                        bVar.b = file.getAbsolutePath();
                        bVar.d = 60.0f;
                        org.greenrobot.eventbus.c.a().d(bVar);
                    }
                });
                return;
            }
            aVar.c = 0;
            aVar.d = null;
            aVar.f = null;
            cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.c.a().a(aVar);
        }
    }

    public int getItemCount() {
        return this.a.size();
    }

    public void a(Collection<a> collection) {
        this.a.clear();
        this.a.addAll(collection);
        notifyDataSetChanged();
    }

    public void a(int i, @NonNull a aVar) {
        this.a.add(i, aVar);
        notifyItemInserted(i);
    }

    public void a() {
        this.c = null;
        this.b = null;
        this.a.clear();
    }
}
