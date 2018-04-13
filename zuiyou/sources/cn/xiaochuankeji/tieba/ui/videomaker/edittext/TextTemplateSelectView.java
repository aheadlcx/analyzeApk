package cn.xiaochuankeji.tieba.ui.videomaker.edittext;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.download.DownloadApi;
import cn.xiaochuankeji.tieba.json.UgcTypefaceJson;
import cn.xiaochuankeji.tieba.network.c;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.i;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import okhttp3.ab;
import rx.b.g;
import rx.j;

public class TextTemplateSelectView extends FrameLayout {
    private RecyclerView a;
    private LayoutInflater b;
    private cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.b c;
    private a d;
    private ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.b> e;
    private b f;
    private DownloadApi g;
    private cn.xiaochuankeji.tieba.api.ugcvideo.a h;

    public interface a {
        void a(cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.b bVar);
    }

    class TextTemplateHolder extends ViewHolder {
        final /* synthetic */ TextTemplateSelectView a;
        @Nullable
        @BindView
        WebImageView image;
        @Nullable
        @BindView
        AppCompatImageView ivSelect;
        @Nullable
        @BindView
        AppCompatImageView progres;
        @Nullable
        @BindView
        AppCompatImageView state;
        @Nullable
        @BindView
        AppCompatTextView tvNothing;

        public TextTemplateHolder(TextTemplateSelectView textTemplateSelectView, @NonNull View view) {
            this.a = textTemplateSelectView;
            super(view);
            ButterKnife.a(this, this.itemView);
        }

        public void a(final cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.b bVar) {
            if (bVar == null) {
                this.tvNothing.setVisibility(0);
                this.image.setVisibility(8);
                this.state.setVisibility(8);
                this.progres.clearAnimation();
                this.progres.setVisibility(8);
                this.itemView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ TextTemplateHolder a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.a.c != null) {
                            this.a.a.c = null;
                            this.a.a.f.notifyDataSetChanged();
                            this.a.a.d.a(this.a.a.c);
                        }
                    }
                });
            } else {
                this.tvNothing.setVisibility(8);
                this.image.setVisibility(0);
                this.image.setImageResource(bVar.b);
                if (bVar.h == 0) {
                    bVar.m = 2;
                } else if (3 != bVar.m) {
                    bVar.m = a.a(bVar.h) == null ? 1 : 2;
                }
                if (1 == bVar.m) {
                    this.state.setVisibility(0);
                    this.progres.clearAnimation();
                    this.progres.setVisibility(4);
                } else if (2 == bVar.m) {
                    this.state.setVisibility(8);
                    this.progres.clearAnimation();
                    this.progres.setVisibility(8);
                } else if (3 == bVar.m) {
                    this.state.setVisibility(4);
                    this.progres.setVisibility(0);
                    if (this.progres.getAnimation() == null) {
                        Animation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
                        rotateAnimation.setDuration(500);
                        rotateAnimation.setRepeatMode(1);
                        rotateAnimation.setRepeatCount(-1);
                        this.progres.startAnimation(rotateAnimation);
                    }
                }
                this.itemView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ TextTemplateHolder b;

                    public void onClick(View view) {
                        if (this.b.a.c != null && this.b.a.c.a == bVar.a) {
                            return;
                        }
                        if (1 == bVar.m) {
                            this.b.a.a(bVar.h);
                        } else if (2 == bVar.m) {
                            this.b.a.c = bVar;
                            this.b.a.f.notifyDataSetChanged();
                            this.b.a.d.a(this.b.a.c);
                        }
                    }
                });
            }
            this.ivSelect.setVisibility(bVar == this.a.c ? 0 : 8);
        }
    }

    public class TextTemplateHolder_ViewBinding implements Unbinder {
        private TextTemplateHolder b;

        @UiThread
        public TextTemplateHolder_ViewBinding(TextTemplateHolder textTemplateHolder, View view) {
            this.b = textTemplateHolder;
            textTemplateHolder.image = (WebImageView) butterknife.a.b.a(view, R.id.image, "field 'image'", WebImageView.class);
            textTemplateHolder.state = (AppCompatImageView) butterknife.a.b.a(view, R.id.state, "field 'state'", AppCompatImageView.class);
            textTemplateHolder.progres = (AppCompatImageView) butterknife.a.b.a(view, R.id.progres, "field 'progres'", AppCompatImageView.class);
            textTemplateHolder.ivSelect = (AppCompatImageView) butterknife.a.b.a(view, R.id.ivSelect, "field 'ivSelect'", AppCompatImageView.class);
            textTemplateHolder.tvNothing = (AppCompatTextView) butterknife.a.b.a(view, R.id.tvNothing, "field 'tvNothing'", AppCompatTextView.class);
        }

        @CallSuper
        public void a() {
            TextTemplateHolder textTemplateHolder = this.b;
            if (textTemplateHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.b = null;
            textTemplateHolder.image = null;
            textTemplateHolder.state = null;
            textTemplateHolder.progres = null;
            textTemplateHolder.ivSelect = null;
            textTemplateHolder.tvNothing = null;
        }
    }

    class b extends Adapter<TextTemplateHolder> {
        final /* synthetic */ TextTemplateSelectView a;

        b(TextTemplateSelectView textTemplateSelectView) {
            this.a = textTemplateSelectView;
        }

        public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
            a((TextTemplateHolder) viewHolder, i);
        }

        public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return a(viewGroup, i);
        }

        public TextTemplateHolder a(ViewGroup viewGroup, int i) {
            return new TextTemplateHolder(this.a, this.a.b.inflate(R.layout.text_template_cell, viewGroup, false));
        }

        public void a(TextTemplateHolder textTemplateHolder, int i) {
            if (this.a.e.size() == i) {
                textTemplateHolder.a(null);
            } else {
                textTemplateHolder.a((cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.b) this.a.e.get(i));
            }
        }

        public int getItemCount() {
            return this.a.e.size() + 1;
        }
    }

    public TextTemplateSelectView(@NonNull Context context) {
        this(context, null);
    }

    public TextTemplateSelectView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextTemplateSelectView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        this.c = null;
        this.h = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
        c();
    }

    private void c() {
        this.b = LayoutInflater.from(getContext());
        this.b.inflate(R.layout.view_text_template_select, this);
        this.a = (RecyclerView) findViewById(R.id.recyclerView);
        this.a.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.a.addItemDecoration(new i(e.a(10.0f)));
        this.e = e.a();
        this.f = new b(this);
        this.a.setAdapter(this.f);
        this.g = (DownloadApi) c.a().a(DownloadApi.class);
    }

    public void a() {
        this.a.scrollToPosition(0);
    }

    private void a(final int i) {
        this.h.a(i).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new j<UgcTypefaceJson>(this) {
            final /* synthetic */ TextTemplateSelectView b;

            public /* synthetic */ void onNext(Object obj) {
                a((UgcTypefaceJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(UgcTypefaceJson ugcTypefaceJson) {
                this.b.a(ugcTypefaceJson.typefaceUrl, i);
            }
        });
    }

    private void a(String str, final int i) {
        a(i, 3);
        this.f.notifyDataSetChanged();
        this.g.download(str).b(rx.f.a.c()).d(new g<ab, String>(this) {
            final /* synthetic */ TextTemplateSelectView b;

            public /* synthetic */ Object call(Object obj) {
                return a((ab) obj);
            }

            public String a(ab abVar) {
                return this.b.a(this.b.b(i), abVar);
            }
        }).a(rx.a.b.a.a()).b(new j<String>(this) {
            final /* synthetic */ TextTemplateSelectView b;

            public /* synthetic */ void onNext(Object obj) {
                a((String) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.b.a(i, 1);
                this.b.f.notifyDataSetChanged();
            }

            public void a(String str) {
                if (TextUtils.isEmpty(str)) {
                    this.b.a(i, 1);
                    this.b.f.notifyDataSetChanged();
                    return;
                }
                a.a(i, str);
                this.b.a(i, 2);
                this.b.f.notifyDataSetChanged();
            }
        });
    }

    private String a(File file, ab abVar) {
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        InputStream byteStream;
        try {
            fileOutputStream = new FileOutputStream(file, false);
            try {
                byteStream = abVar.byteStream();
                try {
                    byte[] bArr = new byte[16384];
                    while (true) {
                        int read = byteStream.read(bArr);
                        if (-1 == read) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.getFD().sync();
                    fileOutputStream.close();
                    fileOutputStream = null;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (byteStream == null) {
                            return null;
                        }
                        try {
                            byteStream.close();
                            return null;
                        } catch (IOException e32) {
                            e32.printStackTrace();
                            return null;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (byteStream != null) {
                            try {
                                byteStream.close();
                            } catch (IOException e42) {
                                e42.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                byteStream = null;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (byteStream == null) {
                    return null;
                }
                byteStream.close();
                return null;
            } catch (Throwable th3) {
                th = th3;
                byteStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (byteStream != null) {
                    byteStream.close();
                }
                throw th;
            }
            try {
                byteStream.close();
                byteStream = null;
                String a = cn.htjyb.c.a.b.a(file.getPath(), file.getParent());
                if (null != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                if (null == null) {
                    return a;
                }
                try {
                    byteStream.close();
                    return a;
                } catch (IOException e422) {
                    e422.printStackTrace();
                    return a;
                }
            } catch (Exception e7) {
                e = e7;
                fileOutputStream = null;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (byteStream == null) {
                    return null;
                }
                byteStream.close();
                return null;
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (byteStream != null) {
                    byteStream.close();
                }
                throw th;
            }
        } catch (Exception e8) {
            e = e8;
            byteStream = null;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (byteStream == null) {
                return null;
            }
            byteStream.close();
            return null;
        } catch (Throwable th5) {
            th = th5;
            byteStream = null;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (byteStream != null) {
                byteStream.close();
            }
            throw th;
        }
    }

    private void a(int i, int i2) {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.b bVar = (cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.b) it.next();
            if (bVar.h == i) {
                bVar.m = i2;
            }
        }
    }

    public void b() {
        this.f.notifyDataSetChanged();
    }

    public void setCurrent(cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.b bVar) {
        this.c = bVar;
        this.f.notifyDataSetChanged();
    }

    private File b(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cn.xiaochuankeji.tieba.background.a.e().K().getAbsolutePath());
        stringBuilder.append(File.separator);
        stringBuilder.append(i);
        stringBuilder.append(".zip");
        return new File(stringBuilder.toString());
    }

    public void setOnTemplateSelectListener(a aVar) {
        this.d = aVar;
    }
}
