package cn.xiaochuankeji.tieba.ui.videomaker.music;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicCategoryJson;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.marshalchen.ultimaterecyclerview.d;
import com.marshalchen.ultimaterecyclerview.e;
import java.util.ArrayList;

public class a extends e<d> {
    private Context a;
    private ArrayList<UgcVideoMusicCategoryJson> l;
    private int m;
    private LayoutInflater n = LayoutInflater.from(this.a);
    private b o;

    public interface b {
        void a(int i);
    }

    class a extends d {
        final /* synthetic */ a a;
        private WebImageView b;
        private TextView c;
        private View d;
        private int e;

        public a(final a aVar, View view) {
            this.a = aVar;
            super(view);
            this.b = (WebImageView) view.findViewById(R.id.wivThumb);
            this.c = (TextView) view.findViewById(R.id.tvCategoryName);
            this.d = view.findViewById(R.id.divider);
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    this.b.a.o.a(this.b.e);
                }
            });
        }

        public void a(UgcVideoMusicCategoryJson ugcVideoMusicCategoryJson, int i) {
            this.e = i;
            this.b.setImageURI(cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/png/id/", (long) ugcVideoMusicCategoryJson.img.id, "/sz/228"));
            this.c.setText(ugcVideoMusicCategoryJson.categoryName);
            if (i == this.a.m) {
                this.c.setSelected(true);
                this.d.setVisibility(0);
                return;
            }
            this.c.setSelected(false);
            this.d.setVisibility(8);
        }
    }

    public /* synthetic */ ViewHolder b(ViewGroup viewGroup) {
        return a(viewGroup);
    }

    public /* synthetic */ ViewHolder c(View view) {
        return b(view);
    }

    public /* synthetic */ ViewHolder d(View view) {
        return a(view);
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((d) viewHolder, i);
    }

    public a(Context context, ArrayList<UgcVideoMusicCategoryJson> arrayList) {
        this.a = context;
        this.l = arrayList;
    }

    public void b(int i) {
        this.m = i;
        notifyDataSetChanged();
    }

    public void a(b bVar) {
        this.o = bVar;
    }

    public d a(ViewGroup viewGroup) {
        return new a(this, this.n.inflate(R.layout.view_item_music_category, viewGroup, false));
    }

    public void a(d dVar, int i) {
        if (dVar instanceof a) {
            ((a) dVar).a((UgcVideoMusicCategoryJson) this.l.get(i), i);
        }
    }

    public int a() {
        return this.l.size();
    }

    public d a(View view) {
        return null;
    }

    public d b(View view) {
        return null;
    }
}
