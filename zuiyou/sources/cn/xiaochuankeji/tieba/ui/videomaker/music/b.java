package cn.xiaochuankeji.tieba.ui.videomaker.music;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicJson;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.marshalchen.ultimaterecyclerview.d;
import com.marshalchen.ultimaterecyclerview.e;
import java.util.ArrayList;
import rx.j;

public class b extends e<d> {
    private final Context a;
    private final Handler l;
    private final LayoutInflater m;
    private RecyclerView n;
    private ArrayList<UgcVideoMusicJson> o;
    private long p;
    private boolean q;
    private a r;
    private UgcVideoMusicJson s;
    private cn.xiaochuankeji.tieba.api.ugcvideo.a t = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
    private RotateAnimation u;

    public interface a {
        void a(UgcVideoMusicJson ugcVideoMusicJson);

        void a(String str, long j);
    }

    class b extends d {
        final /* synthetic */ b a;
        private WebImageView b;
        private ImageView c;
        private View d;
        private TextView e;
        private TextView f;
        private TextView g;
        private FrameLayout h;
        private View i;
        private View j;
        private View o;
        private UgcVideoMusicJson p;
        private int q;

        public b(final b bVar, View view) {
            this.a = bVar;
            super(view);
            view.findViewById(R.id.vMusicContent).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    this.b.a.r.a(this.b.p.url, this.b.p.id);
                }
            });
            this.b = (WebImageView) view.findViewById(R.id.wivCover);
            this.e = (TextView) view.findViewById(R.id.tvMusicName);
            this.f = (TextView) view.findViewById(R.id.tvSingers);
            this.g = (TextView) view.findViewById(R.id.tvDur);
            this.h = (FrameLayout) view.findViewById(R.id.flConfirm);
            this.h.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    this.b.a.r.a(this.b.p);
                }
            });
            this.c = (ImageView) view.findViewById(R.id.ivPlayPause);
            this.d = view.findViewById(R.id.ivLoading);
            this.i = view.findViewById(R.id.vPictureFlag);
            this.j = view.findViewById(R.id.vBottomItemSpace);
            this.o = view.findViewById(R.id.ivFavor);
            this.o.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    int i = 1;
                    cn.xiaochuankeji.tieba.api.ugcvideo.a b = this.b.a.t;
                    long j = this.b.p.id;
                    if (this.b.p.favor == 1) {
                        i = 0;
                    }
                    b.a(j, i).a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void onNext(Object obj) {
                            a((EmptyJson) obj);
                        }

                        public void onCompleted() {
                        }

                        public void onError(Throwable th) {
                            g.a(th.getMessage());
                        }

                        public void a(EmptyJson emptyJson) {
                            int i;
                            boolean z = true;
                            UgcVideoMusicJson a = this.a.b.p;
                            if (1 == this.a.b.p.favor) {
                                i = 0;
                            } else {
                                i = 1;
                            }
                            a.favor = i;
                            View b = this.a.b.o;
                            if (1 != this.a.b.p.favor) {
                                z = false;
                            }
                            b.setSelected(z);
                            SelectVideoMusicActivity.d.put(Long.valueOf(this.a.b.p.id), Integer.valueOf(this.a.b.p.favor));
                        }
                    });
                }
            });
        }

        public void a(UgcVideoMusicJson ugcVideoMusicJson, final int i) {
            boolean z = true;
            this.p = ugcVideoMusicJson;
            this.q = i;
            this.e.setText(ugcVideoMusicJson.musicName);
            long j = this.p.img != null ? (long) this.p.img.id : 0;
            if (0 != j) {
                this.b.setImageURI(cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", j, "/sz/280"));
            } else {
                this.b.setImageResource(R.drawable.img_default_music_cover);
            }
            ArrayList arrayList = ugcVideoMusicJson.singers;
            if (arrayList != null && arrayList.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder((String) arrayList.get(0));
                for (int i2 = 1; i2 < arrayList.size(); i2++) {
                    stringBuilder.append("&" + ((String) arrayList.get(i2)));
                }
                this.f.setText(stringBuilder.toString());
            }
            this.g.setText(cn.xiaochuankeji.tieba.ui.utils.d.a((long) (ugcVideoMusicJson.dur * 1000)));
            if (this.a.p == this.p.id) {
                if (this.a.q) {
                    this.c.setVisibility(8);
                    a(true);
                } else {
                    this.c.setVisibility(0);
                    this.c.setSelected(true);
                    a(false);
                    this.a.l.post(new Runnable(this) {
                        final /* synthetic */ b b;

                        public void run() {
                            this.b.a.n.scrollToPosition(i);
                        }
                    });
                }
                this.h.setVisibility(0);
                ((LayoutParams) this.i.getLayoutParams()).leftMargin = cn.xiaochuankeji.tieba.ui.utils.e.a(35.0f);
            } else {
                a(false);
                this.c.setVisibility(0);
                this.c.setSelected(false);
                this.h.setVisibility(8);
                ((LayoutParams) this.i.getLayoutParams()).leftMargin = cn.xiaochuankeji.tieba.ui.utils.e.a(17.0f);
            }
            if (this.a.s == null) {
                if (i == this.a.o.size() - 1) {
                    this.j.setVisibility(0);
                } else {
                    this.j.setVisibility(8);
                }
            } else if (i == this.a.o.size()) {
                this.j.setVisibility(0);
            } else {
                this.j.setVisibility(8);
            }
            Integer num = (Integer) SelectVideoMusicActivity.d.get(Long.valueOf(ugcVideoMusicJson.id));
            if (num != null) {
                View view = this.o;
                if (num.intValue() != 1) {
                    z = false;
                }
                view.setSelected(z);
                return;
            }
            View view2 = this.o;
            if (ugcVideoMusicJson.favor != 1) {
                z = false;
            }
            view2.setSelected(z);
        }

        public void a(boolean z) {
            if (z) {
                this.d.startAnimation(this.a.u);
                this.d.setVisibility(0);
                return;
            }
            this.d.clearAnimation();
            this.d.setVisibility(8);
        }
    }

    class c extends d {
        final /* synthetic */ b a;
        private View b;
        private TextView c;

        public c(final b bVar, View view) {
            this.a = bVar;
            super(view);
            this.b = view.findViewById(R.id.vCancel);
            this.c = (TextView) view.findViewById(R.id.tvMusicName);
            this.c.post(new Runnable(this) {
                final /* synthetic */ c b;

                public void run() {
                    this.b.c.setMaxWidth((cn.xiaochuankeji.tieba.ui.utils.e.b() - this.b.b.getMeasuredWidth()) - cn.xiaochuankeji.tieba.ui.utils.e.a(104.0f));
                }
            });
        }

        public void a(UgcVideoMusicJson ugcVideoMusicJson) {
            this.c.setText(ugcVideoMusicJson.musicName);
            this.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.a.s = null;
                    this.a.a.notifyDataSetChanged();
                    Context e = this.a.e();
                    if (e != null) {
                        ((Activity) e).setResult(-1, new Intent());
                        ((Activity) e).finish();
                    }
                }
            });
        }
    }

    public /* synthetic */ ViewHolder b(ViewGroup viewGroup) {
        return a(viewGroup);
    }

    public /* synthetic */ ViewHolder c(View view) {
        return e(view);
    }

    public /* synthetic */ ViewHolder d(View view) {
        return a(view);
    }

    public /* synthetic */ ViewHolder g(View view) {
        return b(view);
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((d) viewHolder, i);
    }

    public b(Context context, ArrayList<UgcVideoMusicJson> arrayList, a aVar, long j, UgcVideoMusicJson ugcVideoMusicJson) {
        this.a = context;
        this.l = new Handler();
        this.m = LayoutInflater.from(this.a);
        this.o = arrayList;
        this.r = aVar;
        this.s = ugcVideoMusicJson;
        this.p = j;
        b();
    }

    private void b() {
        this.u = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.u.setInterpolator(new LinearInterpolator());
        this.u.setDuration(1000);
        this.u.setRepeatCount(-1);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.n = recyclerView;
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.l.removeCallbacksAndMessages(null);
        this.n = null;
    }

    public d a(View view) {
        return new d(view);
    }

    public d a(ViewGroup viewGroup) {
        return new b(this, this.m.inflate(R.layout.view_item_ugcvideo_music, viewGroup, false));
    }

    public d b(View view) {
        return new c(this, this.m.inflate(R.layout.view_item_selected_music, null));
    }

    public int a() {
        if (this.s != null) {
            return this.o.size() + 1;
        }
        return this.o.size();
    }

    public int getItemViewType(int i) {
        if (this.s == null) {
            return super.getItemViewType(i);
        }
        if (i == 0) {
            return 5;
        }
        return super.getItemViewType(i);
    }

    public void a(d dVar, int i) {
        if (getItemViewType(i) == 0) {
            if (dVar instanceof b) {
                int i2;
                b bVar = (b) dVar;
                if (this.s != null) {
                    i2 = i - 1;
                } else {
                    i2 = i;
                }
                bVar.a((UgcVideoMusicJson) this.o.get(i2), i);
            }
        } else if (getItemViewType(i) == 5 && (dVar instanceof c)) {
            ((c) dVar).a(this.s);
        }
    }

    public d e(View view) {
        return null;
    }

    public void a(long j, boolean z) {
        this.p = j;
        this.q = z;
        notifyDataSetChanged();
    }
}
