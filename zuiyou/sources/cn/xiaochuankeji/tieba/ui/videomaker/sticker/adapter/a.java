package cn.xiaochuankeji.tieba.ui.videomaker.sticker.adapter;

import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.download.DownloadApi;
import cn.xiaochuankeji.tieba.network.c;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import okhttp3.ab;
import rx.b.b;
import rx.b.g;
import rx.j;

public class a extends Adapter<MagicEmotionHolder> {
    private ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> a = new ArrayList();
    private DownloadApi b = ((DownloadApi) c.a().a(DownloadApi.class));
    private int c = -1;

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((MagicEmotionHolder) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public MagicEmotionHolder a(ViewGroup viewGroup, int i) {
        return new MagicEmotionHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_sticker_image, viewGroup, false));
    }

    public void a(MagicEmotionHolder magicEmotionHolder, final int i) {
        int i2;
        final cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a aVar = (cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a) this.a.get(i);
        magicEmotionHolder.image.setImageURI(Uri.parse(aVar.g));
        final File file = new File(a(aVar.t.id));
        if (file.exists() && file.length() != 0) {
            aVar.c = 100;
        } else if (aVar.c == -1) {
            aVar.c = 0;
        }
        if (aVar.c == -1) {
            magicEmotionHolder.state.setVisibility(8);
            magicEmotionHolder.progres.clearAnimation();
            magicEmotionHolder.progres.setVisibility(8);
        } else if (aVar.c == 0) {
            magicEmotionHolder.state.setVisibility(0);
            magicEmotionHolder.progres.clearAnimation();
            magicEmotionHolder.progres.setVisibility(4);
        } else if (aVar.c > 0 && aVar.c < 100) {
            magicEmotionHolder.state.setVisibility(4);
            magicEmotionHolder.progres.setVisibility(0);
            if (magicEmotionHolder.progres.getAnimation() == null) {
                Animation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
                rotateAnimation.setDuration(500);
                rotateAnimation.setRepeatMode(1);
                rotateAnimation.setRepeatCount(-1);
                magicEmotionHolder.progres.startAnimation(rotateAnimation);
            }
        } else if (aVar.c == 100) {
            magicEmotionHolder.state.setVisibility(4);
            magicEmotionHolder.progres.clearAnimation();
            magicEmotionHolder.progres.setVisibility(4);
        }
        AppCompatImageView appCompatImageView = magicEmotionHolder.ivSelect;
        if (i == this.c) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        appCompatImageView.setVisibility(i2);
        com.jakewharton.a.b.a.a(magicEmotionHolder.itemView).c(150, TimeUnit.MILLISECONDS).a(new b<Void>(this) {
            final /* synthetic */ a d;

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
                String str = null;
                if (aVar.c == 100) {
                    if (this.d.c == i) {
                        this.d.c = -1;
                    } else {
                        this.d.c = i;
                    }
                    this.d.notifyDataSetChanged();
                    cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment.b.a aVar = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment.b.a();
                    aVar.a = -1 == this.d.c ? null : file.getPath();
                    if (-1 != this.d.c) {
                        str = aVar.t.magic_hint;
                    }
                    aVar.b = str;
                    org.greenrobot.eventbus.c.a().d(aVar);
                } else if (aVar.c == 0) {
                    aVar.c = 1;
                    this.d.notifyItemChanged(i);
                    this.d.b.download(aVar.t.magicUrl).b(rx.f.a.c()).d(new g<ab, String>(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ Object call(Object obj) {
                            return a((ab) obj);
                        }

                        public String a(ab abVar) {
                            if (cn.xiaochuankeji.tieba.api.download.a.a(file, abVar)) {
                                return file.getPath();
                            }
                            return null;
                        }
                    }).a(rx.a.b.a.a()).b(new j<String>(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void onNext(Object obj) {
                            a((String) obj);
                        }

                        public void onCompleted() {
                        }

                        public void onError(Throwable th) {
                            aVar.c = 0;
                            this.a.d.notifyItemChanged(i);
                        }

                        public void a(String str) {
                            aVar.c = 100;
                            this.a.d.notifyItemChanged(i);
                        }
                    });
                }
            }
        });
    }

    private String a(long j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cn.xiaochuankeji.tieba.background.a.e().J().getAbsolutePath());
        stringBuilder.append(File.separator);
        stringBuilder.append(j);
        stringBuilder.append(".zip");
        return stringBuilder.toString();
    }

    public int getItemCount() {
        return this.a.size();
    }

    public void a(LinkedList<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> linkedList, String str) {
        this.a.clear();
        this.a.addAll(linkedList);
        int i = 0;
        while (!TextUtils.isEmpty(str) && i < linkedList.size()) {
            if (a(((cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a) linkedList.get(i)).t.id).equals(str)) {
                this.c = i;
                break;
            }
            i++;
        }
        notifyDataSetChanged();
    }

    public void a(Collection<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> collection) {
        this.a.addAll(collection);
        notifyItemRangeInserted(this.a.size() - collection.size(), collection.size());
    }
}
