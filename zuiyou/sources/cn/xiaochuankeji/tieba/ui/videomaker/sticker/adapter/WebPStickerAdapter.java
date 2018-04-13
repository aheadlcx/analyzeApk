package cn.xiaochuankeji.tieba.ui.videomaker.sticker.adapter;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.json.UgcEmoji;
import com.facebook.drawee.a.a.c;
import com.facebook.drawee.a.a.e;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class WebPStickerAdapter extends Adapter<StickerViewHolder> {
    private ArrayList<UgcEmoji> a = new ArrayList();

    static class StickerViewHolder extends ViewHolder {
        @BindView
        SimpleDraweeView image;
        @BindView
        AppCompatImageView progres;
        @BindView
        AppCompatImageView state;

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
            stickerViewHolder.image = (SimpleDraweeView) b.b(view, R.id.image, "field 'image'", SimpleDraweeView.class);
            stickerViewHolder.state = (AppCompatImageView) b.b(view, R.id.state, "field 'state'", AppCompatImageView.class);
            stickerViewHolder.progres = (AppCompatImageView) b.b(view, R.id.progres, "field 'progres'", AppCompatImageView.class);
        }

        @CallSuper
        public void a() {
            StickerViewHolder stickerViewHolder = this.b;
            if (stickerViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.b = null;
            stickerViewHolder.image = null;
            stickerViewHolder.state = null;
            stickerViewHolder.progres = null;
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((StickerViewHolder) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public StickerViewHolder a(ViewGroup viewGroup, int i) {
        return new StickerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_sticker_gif, viewGroup, false));
    }

    public void a(StickerViewHolder stickerViewHolder, final int i) {
        final UgcEmoji ugcEmoji = (UgcEmoji) this.a.get(i);
        if (ugcEmoji.img != null || ugcEmoji.img4preview != null) {
            final File file = new File(a.e().I(), String.valueOf(ugcEmoji.img.id));
            com.facebook.drawee.d.a controller = stickerViewHolder.image.getController();
            Uri parse = Uri.parse(cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/png/id/", (long) ugcEmoji.img4preview.id, "/sz/src"));
            if (!file.exists() || file.length() == 0) {
                if (ugcEmoji.status == -1 || ugcEmoji.status == 100) {
                    ugcEmoji.status = 0;
                }
                stickerViewHolder.image.setController(((e) ((e) c.a().a(parse).a(controller)).b(false)).k());
            } else {
                ugcEmoji.status = 100;
                String a = cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/png/id/", (long) ugcEmoji.img4small.id, null);
                try {
                    File a2 = cn.xiaochuankeji.tieba.common.c.a.a(ImageRequest.a(parse));
                    if (a2.exists() || a2.length() > 0) {
                        Drawable createFromPath = Drawable.createFromPath(a2.getAbsolutePath());
                        com.facebook.drawee.generic.a aVar = (com.facebook.drawee.generic.a) stickerViewHolder.image.getHierarchy();
                        aVar.b(createFromPath);
                        aVar.c(createFromPath);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                stickerViewHolder.image.setController(((e) ((e) c.a().a(Uri.parse(a)).a(controller)).b(true)).k());
            }
            if (ugcEmoji.status == -1) {
                stickerViewHolder.state.setVisibility(8);
                stickerViewHolder.progres.clearAnimation();
                stickerViewHolder.progres.setVisibility(8);
            } else if (ugcEmoji.status == 0) {
                stickerViewHolder.state.setVisibility(0);
                stickerViewHolder.progres.clearAnimation();
                stickerViewHolder.progres.setVisibility(8);
            } else if (ugcEmoji.status > 0 && ugcEmoji.status < 100) {
                stickerViewHolder.state.setVisibility(8);
                stickerViewHolder.progres.setVisibility(0);
                if (stickerViewHolder.progres.getAnimation() == null) {
                    Animation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
                    rotateAnimation.setDuration(500);
                    rotateAnimation.setRepeatMode(1);
                    rotateAnimation.setRepeatCount(-1);
                    stickerViewHolder.progres.startAnimation(rotateAnimation);
                }
            } else if (ugcEmoji.status == 100) {
                stickerViewHolder.state.setVisibility(8);
                stickerViewHolder.progres.clearAnimation();
                stickerViewHolder.progres.setVisibility(8);
            }
            com.jakewharton.a.b.a.a(stickerViewHolder.itemView).c(150, TimeUnit.MILLISECONDS).a(new rx.b.b<Void>(this) {
                final /* synthetic */ WebPStickerAdapter d;

                public /* synthetic */ void call(Object obj) {
                    a((Void) obj);
                }

                public void a(Void voidR) {
                    if (ugcEmoji.status == 100) {
                        cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.b bVar = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.b(19);
                        bVar.b = file.getAbsolutePath();
                        bVar.d = ugcEmoji.percent;
                        bVar.e = ugcEmoji.id;
                        bVar.f = ugcEmoji.cid;
                        org.greenrobot.eventbus.c.a().d(bVar);
                    } else if (ugcEmoji.status == 0) {
                        ugcEmoji.status = 1;
                        this.d.notifyItemChanged(i);
                        String a = cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/png/id/", (long) ugcEmoji.img.id, null);
                        cn.xiaochuankeji.tieba.ui.widget.bigImage.e.a(BaseApplication.getAppContext()).a(a.hashCode(), Uri.parse(a), new cn.xiaochuankeji.tieba.ui.widget.bigImage.a.b(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void a(int i) {
                                super.a(i);
                                if (i > 0) {
                                    ugcEmoji.status = i;
                                }
                            }

                            public void a(File file) {
                                super.a(file);
                                c(file);
                            }

                            private void c(File file) {
                                try {
                                    cn.htjyb.c.a.b.a(file, file);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    rx.a.b.a.a().a().a(new rx.b.a(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void call() {
                                            ugcEmoji.status = 100;
                                            this.a.a.d.notifyItemChanged(i);
                                        }
                                    });
                                }
                            }

                            public void a(Throwable th) {
                                rx.a.b.a.a().a().a(new rx.b.a(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void call() {
                                        ugcEmoji.status = 0;
                                        this.a.a.d.notifyItemChanged(i);
                                    }
                                });
                            }
                        });
                    }
                }
            });
        }
    }

    public int getItemCount() {
        return this.a.size();
    }

    public void a(Collection<UgcEmoji> collection) {
        this.a.clear();
        this.a.addAll(collection);
        notifyDataSetChanged();
    }

    public void b(Collection<UgcEmoji> collection) {
        this.a.addAll(collection);
        notifyItemRangeInserted(this.a.size() - collection.size(), collection.size());
    }
}
