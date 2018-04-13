package cn.xiaochuankeji.tieba.ui.videomaker.sticker.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a;
import cn.xiaochuankeji.tieba.ui.widget.bigImage.e;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.c;

public class b extends Adapter<StickerHolder> {
    private ArrayList<a> a = new ArrayList();

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((StickerHolder) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public StickerHolder a(ViewGroup viewGroup, int i) {
        return new StickerHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_sticker_image, viewGroup, false));
    }

    public void a(StickerHolder stickerHolder, final int i) {
        final a aVar = (a) this.a.get(i);
        stickerHolder.image.setImageURI(Uri.parse(aVar.g));
        final File file = new File(cn.xiaochuankeji.tieba.background.a.e().I(), String.valueOf(aVar.e.id));
        if (file.exists() && file.length() != 0) {
            aVar.c = 100;
        } else if (aVar.c == -1) {
            aVar.c = 0;
        }
        if (aVar.c == -1) {
            stickerHolder.state.setVisibility(8);
            stickerHolder.progres.clearAnimation();
            stickerHolder.progres.setVisibility(8);
        } else if (aVar.c == 0) {
            stickerHolder.state.setVisibility(0);
            stickerHolder.progres.clearAnimation();
            stickerHolder.progres.setVisibility(4);
        } else if (aVar.c > 0 && aVar.c < 100) {
            stickerHolder.state.setVisibility(4);
            stickerHolder.progres.setVisibility(0);
            if (stickerHolder.progres.getAnimation() == null) {
                Animation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
                rotateAnimation.setDuration(500);
                rotateAnimation.setRepeatMode(1);
                rotateAnimation.setRepeatCount(-1);
                stickerHolder.progres.startAnimation(rotateAnimation);
            }
        } else if (aVar.c == 100) {
            stickerHolder.state.setVisibility(4);
            stickerHolder.progres.clearAnimation();
            stickerHolder.progres.setVisibility(4);
        }
        com.jakewharton.a.b.a.a(stickerHolder.itemView).c(150, TimeUnit.MILLISECONDS).a(new rx.b.b<Void>(this) {
            final /* synthetic */ b d;

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
                if (aVar.c == 100) {
                    cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.b bVar = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.b(11);
                    bVar.b = file.getAbsolutePath();
                    bVar.d = aVar.s;
                    if (aVar.t != null) {
                        bVar.e = aVar.t.id;
                        bVar.f = aVar.t.cid;
                    }
                    c.a().d(bVar);
                } else if (aVar.c == 0) {
                    aVar.c = 1;
                    this.d.notifyItemChanged(i);
                    String a = cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/png/id/", (long) aVar.e.id, null);
                    e.a(BaseApplication.getAppContext()).a(a.hashCode(), Uri.parse(a), new cn.xiaochuankeji.tieba.ui.widget.bigImage.a.b(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void a(int i) {
                            super.a(i);
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
                                        aVar.c = 100;
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
                                    aVar.c = 0;
                                    this.a.a.d.notifyItemChanged(i);
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    public int getItemCount() {
        return this.a.size();
    }

    public void a(Collection<a> collection) {
        this.a.clear();
        this.a.addAll(collection);
        notifyDataSetChanged();
    }

    public void b(Collection<a> collection) {
        this.a.addAll(collection);
        notifyItemRangeInserted(this.a.size() - collection.size(), collection.size());
    }
}
