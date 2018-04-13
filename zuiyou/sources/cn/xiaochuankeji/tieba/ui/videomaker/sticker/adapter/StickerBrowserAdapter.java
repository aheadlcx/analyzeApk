package cn.xiaochuankeji.tieba.ui.videomaker.sticker.adapter;

import android.net.Uri;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.c;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.b;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment.StickerBrowserFragment;
import com.facebook.drawee.view.SimpleDraweeView;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class StickerBrowserAdapter extends Adapter<StickerViewHolder> {
    private ArrayList<a> a = new ArrayList();
    private ArrayList<a> b = new ArrayList();
    private StickerBrowserFragment c;
    private Snackbar d;
    private OnClickListener e = new OnClickListener(this) {
        final /* synthetic */ StickerBrowserAdapter a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            c.a().a(this.a.b);
            this.a.a.removeAll(this.a.b);
            this.a.notifyDataSetChanged();
            if (this.a.a.size() == 0 && this.a.c != null) {
                this.a.c.close();
            }
            org.greenrobot.eventbus.c.a().d(new b(2));
        }
    };

    static class StickerViewHolder extends ViewHolder {
        @BindView
        SimpleDraweeView image;
        @BindView
        AppCompatImageView selected;

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
            stickerViewHolder.image = (SimpleDraweeView) butterknife.a.b.b(view, R.id.image, "field 'image'", SimpleDraweeView.class);
            stickerViewHolder.selected = (AppCompatImageView) butterknife.a.b.b(view, R.id.selected, "field 'selected'", AppCompatImageView.class);
        }

        @CallSuper
        public void a() {
            StickerViewHolder stickerViewHolder = this.b;
            if (stickerViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.b = null;
            stickerViewHolder.image = null;
            stickerViewHolder.selected = null;
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((StickerViewHolder) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public StickerViewHolder a(ViewGroup viewGroup, int i) {
        return new StickerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_sticker_list, viewGroup, false));
    }

    public void a(StickerViewHolder stickerViewHolder, final int i) {
        final a aVar = (a) this.a.get(i);
        if (!TextUtils.isEmpty(aVar.d)) {
            stickerViewHolder.image.setScaleType(ScaleType.CENTER_CROP);
            File file = new File(aVar.d);
            if (!file.exists()) {
                File file2 = new File(aVar.f);
                if (file2.exists() && file2.length() > 0 && String.valueOf(aVar.q).equalsIgnoreCase(cn.htjyb.c.a.b.b(file2))) {
                    cn.htjyb.c.a.b.a(file2, file);
                }
            }
            stickerViewHolder.selected.setVisibility(this.b.contains(aVar) ? 0 : 8);
            if (file.exists()) {
                stickerViewHolder.image.setImageURI(Uri.fromFile(file));
            } else {
                aVar.c = 0;
                aVar.d = null;
                aVar.f = null;
                c.a().a(aVar);
            }
        }
        com.jakewharton.a.b.a.a(stickerViewHolder.itemView).c(150, TimeUnit.MILLISECONDS).a(new rx.b.b<Void>(this) {
            final /* synthetic */ StickerBrowserAdapter c;

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
                if (aVar.d != null) {
                    if (this.c.b.contains(aVar)) {
                        this.c.b.remove(aVar);
                    } else {
                        this.c.b.add(aVar);
                    }
                    this.c.notifyItemChanged(i);
                    this.c.a();
                }
            }
        });
    }

    public int getItemCount() {
        return this.a.size();
    }

    public void a(StickerBrowserFragment stickerBrowserFragment) {
        this.c = stickerBrowserFragment;
    }

    public void a(Collection<a> collection) {
        this.a.clear();
        this.a.addAll(collection);
        notifyDataSetChanged();
    }

    public void a() {
        if (this.c != null) {
            if (this.d == null) {
                this.d = Snackbar.make(this.c.getView(), (CharSequence) "", -2).setActionTextColor(-1);
            }
            if (this.b.size() > 0) {
                this.d.setAction("删除(" + this.b.size() + ")", this.e);
                this.d.show();
                return;
            }
            this.d.dismiss();
        }
    }
}
