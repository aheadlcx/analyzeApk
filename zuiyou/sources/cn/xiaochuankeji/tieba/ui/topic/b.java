package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class b extends Adapter<a> {
    private List<Uri> a;
    private Context b;
    private int c = -1;
    private b d;
    private LayoutInflater e;

    public interface b {
        void a(int i, int i2);
    }

    public static class a extends ViewHolder {
        SimpleDraweeView a;

        public a(View view) {
            super(view);
            this.a = (SimpleDraweeView) view.findViewById(R.id.iv_gallery_summary_item);
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public b(Context context, b bVar) {
        this.b = context;
        this.d = bVar;
        this.e = LayoutInflater.from(context);
    }

    public void a(List<Uri> list, int i) {
        this.a = list;
        this.c = i;
        notifyDataSetChanged();
    }

    public void a(Uri uri) {
        this.a.add(uri);
        notifyItemInserted(this.a.size() - 1);
    }

    public a a(ViewGroup viewGroup, int i) {
        return new a(this.e.inflate(R.layout.gallery_item, viewGroup, false));
    }

    public void a(final a aVar, int i) {
        if (i >= 0 && i < this.a.size()) {
            aVar.a.setImageURI((Uri) this.a.get(i));
            if (i == 0 && this.c <= 0) {
                aVar.itemView.findViewById(R.id.image_container).setSelected(true);
                this.c = 0;
            } else if (this.c > 0 && i == this.c) {
                aVar.itemView.findViewById(R.id.image_container).setSelected(true);
            }
            aVar.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    aVar.a.setSelected(true);
                    int adapterPosition = aVar.getAdapterPosition();
                    this.b.d.a(adapterPosition, this.b.c);
                    this.b.c = adapterPosition;
                }
            });
        }
    }

    public int getItemCount() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public Uri a(int i) {
        if (i >= this.a.size() || i < 0) {
            return null;
        }
        return (Uri) this.a.get(i);
    }
}
