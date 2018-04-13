package cn.xiaochuankeji.tieba.ui.post.b;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.ui.base.j;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseWhenSelectActivity;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import com.c.a.e;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class a extends j {
    private RecyclerView a;
    private ArrayList<LocalMedia> b;
    private b c;
    private a d;
    private e e;

    protected interface a {
        void a();

        void b();
    }

    class b extends Adapter<ViewHolder> {
        final /* synthetic */ a a;

        b(a aVar) {
            this.a = aVar;
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new c(this.a, LayoutInflater.from(this.a.e_()).inflate(R.layout.view_item_input_comment_thumb, null));
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            ((c) viewHolder).a((LocalMedia) this.a.b.get(i), i);
        }

        public int getItemCount() {
            return this.a.b.size();
        }
    }

    class c extends ViewHolder implements OnClickListener {
        final /* synthetic */ a a;
        private ImageView b;
        private TextView c;
        private ImageView d;
        private ImageView e;
        private LocalMedia f;
        private int g;

        public c(a aVar, View view) {
            this.a = aVar;
            super(view);
            this.b = (ImageView) view.findViewById(R.id.ivThumb);
            this.c = (TextView) view.findViewById(R.id.tvNumber);
            this.d = (ImageView) view.findViewById(R.id.ivVideoFlag);
            this.e = (ImageView) view.findViewById(R.id.ivDelete);
            this.e.setOnClickListener(this);
            this.b.setOnClickListener(this);
        }

        public void a(LocalMedia localMedia, int i) {
            this.g = i;
            this.f = localMedia;
            this.c.setText((i + 1) + "");
            if (1 == localMedia.type) {
                this.d.setVisibility(0);
            } else {
                this.d.setVisibility(8);
            }
            cn.xiaochuan.image.b.b.a(this.a.e_()).a(100, 100).b(true).a(1.0f).a(Uri.fromFile(new File(this.f.path))).a(cn.xiaochuankeji.tieba.ui.utils.e.a(4.0f)).a(new ColorDrawable(Color.parseColor("#808080"))).a(this.b);
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ivDelete:
                    this.a.b.remove(this.f);
                    this.a.e();
                    if (this.a.b.size() == 0 && this.a.d != null) {
                        this.a.d.a();
                    }
                    this.a.d.b();
                    return;
                case R.id.ivThumb:
                    a(this.g);
                    return;
                default:
                    return;
            }
        }

        private void a(int i) {
            cn.xiaochuankeji.tieba.background.picture.a f = cn.xiaochuankeji.tieba.background.a.f();
            ArrayList arrayList = new ArrayList();
            Iterator it = this.a.b.iterator();
            while (it.hasNext()) {
                Object a;
                LocalMedia localMedia = (LocalMedia) it.next();
                if (localMedia.type == 1) {
                    a = f.a(localMedia.path, Type.kVideo, 0);
                } else if (localMedia.path.substring(localMedia.path.length() - 3, localMedia.path.length()).equalsIgnoreCase("gif")) {
                    a = f.a(localMedia.path, Type.kGif, 0);
                } else {
                    a = f.a(localMedia.path, Type.kPostPicLarge, 0);
                }
                arrayList.add(a);
            }
            MediaBrowseWhenSelectActivity.a(this.a.e_(), arrayList, this.a.b, i);
        }
    }

    protected a(Context context, a aVar) {
        super(context);
        f_().setVisibility(8);
        this.d = aVar;
    }

    protected View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.view_comment_image_thumb, null);
    }

    protected void a(View view) {
        this.b = new ArrayList();
        this.a = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.a.setLayoutManager(new LinearLayoutManager(e_(), 0, false));
        this.a.addItemDecoration(new c());
        this.c = new b(this);
        this.a.setAdapter(this.c);
        try {
            if ((e_() instanceof cn.xiaochuankeji.tieba.ui.base.a) && ((cn.xiaochuankeji.tieba.ui.base.a) e_()).g()) {
                this.e = com.c.a.c.b((cn.xiaochuankeji.tieba.ui.base.a) e_());
            }
        } catch (Exception e) {
        }
    }

    public void a(ArrayList<LocalMedia> arrayList) {
        this.b.clear();
        this.b.addAll(arrayList);
        e();
        this.d.b();
    }

    public ArrayList<LocalMedia> c() {
        return this.b;
    }

    public void d() {
        if (this.b != null) {
            this.b.clear();
        }
    }

    private void e() {
        this.c.notifyDataSetChanged();
        if (this.b.size() == 0) {
            f_().setVisibility(8);
        } else {
            f_().setVisibility(0);
        }
        try {
            if (e_() == null || !(e_() instanceof cn.xiaochuankeji.tieba.ui.base.a) || !((cn.xiaochuankeji.tieba.ui.base.a) e_()).g()) {
                return;
            }
            if (this.b == null || this.b.size() <= 4) {
                if (this.e != null) {
                    this.e.b(true);
                }
            } else if (this.e != null) {
                this.e.b(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
