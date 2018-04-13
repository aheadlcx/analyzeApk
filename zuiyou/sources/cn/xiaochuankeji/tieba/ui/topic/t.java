package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.topic.TopicDetail.TopPostInfo;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.List;
import rx.j;

public class t extends Adapter<b> {
    cn.xiaochuankeji.tieba.api.topic.b a = new cn.xiaochuankeji.tieba.api.topic.b();
    private final Context b;
    private List<TopPostInfo> c;
    private long d;
    private LayoutInflater e;
    private List<Post> f;
    private a g;

    public interface a {
        void a(long j);
    }

    public static class b extends ViewHolder {
        WebImageView a;
        TextView b;
        TextView c;
        TextView d;

        public b(View view) {
            super(view);
            this.a = (WebImageView) view.findViewById(R.id.iv_topic_top);
            this.b = (TextView) view.findViewById(R.id.tv_top_title);
            this.c = (TextView) view.findViewById(R.id.tv_edit_top);
            this.d = (TextView) view.findViewById(R.id.text_del_top);
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((b) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public t(Context context, long j, List<Post> list, a aVar) {
        this.b = context;
        this.d = j;
        this.e = LayoutInflater.from(context);
        this.f = list;
        this.g = aVar;
    }

    public void a(List<TopPostInfo> list) {
        this.c = list;
        notifyDataSetChanged();
    }

    public b a(ViewGroup viewGroup, int i) {
        return new b(this.e.inflate(R.layout.item_topic_top, viewGroup, false));
    }

    public void a(b bVar, final int i) {
        if (i >= 0 && i < this.c.size()) {
            final TopPostInfo topPostInfo = (TopPostInfo) this.c.get(i);
            bVar.a.setWebImage(cn.xiaochuankeji.tieba.background.f.b.b(topPostInfo.img_id));
            bVar.b.setText(topPostInfo.text);
            bVar.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ t c;

                public void onClick(View view) {
                    this.c.a.a(this.c.d, topPostInfo.pid).a(rx.a.b.a.a()).b(new j<Void>(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void onNext(Object obj) {
                            a((Void) obj);
                        }

                        public void onCompleted() {
                        }

                        public void onError(Throwable th) {
                            if (th instanceof ClientErrorException) {
                                g.a(th.getMessage());
                            }
                        }

                        public void a(Void voidR) {
                            this.a.c.g.a(((TopPostInfo) this.a.c.c.get(i)).pid);
                            this.a.c.c.remove(i);
                            this.a.c.notifyDataSetChanged();
                        }
                    });
                }
            });
            bVar.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ t c;

                public void onClick(View view) {
                    Object obj;
                    if (this.c.f != null) {
                        int i = 0;
                        obj = null;
                        while (i < this.c.f.size()) {
                            Post post = (Post) this.c.f.get(i);
                            if (post._ID == ((TopPostInfo) this.c.c.get(i)).pid) {
                                obj = 1;
                                TopicPostTopActivity.a(this.c.b, post, ((TopPostInfo) this.c.c.get(i)).img_id, ((TopPostInfo) this.c.c.get(i)).text);
                            }
                            i++;
                            obj = obj;
                        }
                    } else {
                        obj = null;
                    }
                    if (obj == null) {
                        TopicPostTopActivity.a(this.c.b, new Post(topPostInfo.pid), ((TopPostInfo) this.c.c.get(i)).img_id, ((TopPostInfo) this.c.c.get(i)).text);
                    }
                }
            });
        }
    }

    public int getItemCount() {
        return this.c.size();
    }
}
