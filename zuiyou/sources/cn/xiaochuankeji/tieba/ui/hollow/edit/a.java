package cn.xiaochuankeji.tieba.ui.hollow.edit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.hollow.data.EmotionDataBean;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.List;

public class a extends Adapter {
    private List<EmotionDataBean> a;
    private RecyclerView b;
    private Context c;

    class a extends ViewHolder {
        final /* synthetic */ a a;
        private WebImageView b;
        private View c;

        a(a aVar, View view) {
            this.a = aVar;
            super(view);
            this.b = (WebImageView) view.findViewById(R.id.emotion_item_image_view);
            this.c = view.findViewById(R.id.emotion_item_root);
        }

        void a(EmotionDataBean emotionDataBean) {
            this.b.setImageURI(cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/png/id/", emotionDataBean.imageId, ""));
            a(false);
        }

        void a(boolean z) {
            this.c.setBackgroundResource(z ? R.drawable.bg_emotion_item_selected : R.drawable.bg_emotion_item_normal);
        }
    }

    a(Context context, RecyclerView recyclerView) {
        this.b = recyclerView;
        this.c = context;
    }

    void a(List<EmotionDataBean> list) {
        this.a = list;
        notifyDataSetChanged();
        if (list != null) {
            this.b.scrollToPosition(list.size() / 2);
        }
    }

    long a(int i) {
        return this.a == null ? -1 : ((EmotionDataBean) this.a.get(i)).id;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(this, LayoutInflater.from(this.c).inflate(R.layout.emotion_list_item, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        ((a) viewHolder).a((EmotionDataBean) this.a.get(i));
        viewHolder.itemView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                this.b.b.smoothScrollToPosition(viewHolder.getAdapterPosition());
            }
        });
    }

    public int getItemCount() {
        return this.a == null ? 0 : this.a.size();
    }

    public int getItemViewType(int i) {
        return 0;
    }
}
