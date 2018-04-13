package cn.xiaochuankeji.tieba.ui.post.postdetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.ui.comment.e;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class d extends BaseAdapter {
    public Post a;
    public Context b;
    private cn.htjyb.b.a.d<Comment> c;
    private cn.htjyb.b.a.d<Comment> d;
    private boolean e;
    private String f;
    private HashMap<Long, f> g;
    private boolean h;
    private boolean i;
    private a j;
    private boolean k = true;
    private List<e> l;
    private b m;

    public interface a {
        void a(Comment comment);

        void a(Comment comment, int i);

        void j();
    }

    public interface b {
        void a(boolean z);
    }

    public d(Context context, Post post, cn.htjyb.b.a.d<Comment> dVar, cn.htjyb.b.a.d<Comment> dVar2) {
        this.b = context;
        this.a = post;
        this.c = dVar;
        this.d = dVar2;
        this.g = new HashMap();
        this.h = this.b instanceof InnerCommentDetailActivity;
        this.l = new ArrayList();
    }

    public void a(boolean z, String str) {
        this.e = z;
        if (this.e) {
            this.k = false;
        }
        this.f = str;
        notifyDataSetChanged();
    }

    public void a() {
        this.i = true;
    }

    private boolean e() {
        boolean z;
        if (this.c == null || this.c.itemCount() <= 0) {
            z = false;
        } else {
            z = true;
        }
        boolean z2;
        if (this.d == null || this.d.itemCount() <= 0) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z || r3) {
            return true;
        }
        return false;
    }

    private boolean f() {
        return this.e && this.c.itemCount() > 0 && !this.c.hasMore();
    }

    public int getCount() {
        int g = g();
        if (f()) {
            return g + 1;
        }
        return g;
    }

    private int g() {
        if (this.h) {
            int itemCount = this.c.itemCount();
            if (itemCount == 0) {
                return 0;
            }
            if (this.i) {
                return itemCount;
            }
            return itemCount + 1;
        } else if (!e()) {
            return 1;
        } else {
            if (f()) {
                return this.c.itemCount();
            }
            if (this.k) {
                return this.d.itemCount() + 1;
            }
            return this.c.itemCount() + 1;
        }
    }

    public Object getItem(int i) {
        int itemViewType = getItemViewType(i);
        if (this.h) {
            if (!this.i) {
                if (itemViewType == 4) {
                    return null;
                }
                if (i == 0) {
                    i = 0;
                } else {
                    i--;
                }
            }
            return this.c.itemAt(i);
        } else if (f()) {
            return this.c.itemAt(i);
        } else {
            if (this.k) {
                return this.d.itemAt(i - 1);
            }
            return this.c.itemAt(i - 1);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getViewTypeCount() {
        return 7;
    }

    public int getItemViewType(int i) {
        if (this.h) {
            if (this.i) {
                return 1;
            }
            if (i == 1) {
                return 2;
            }
            if (f() && i == getCount() - 1) {
                return 4;
            }
            return 1;
        } else if (!e()) {
            return 0;
        } else {
            if (f()) {
                if (i == getCount() - 1) {
                    return 4;
                }
                return 1;
            } else if (i == 0) {
                return 6;
            } else {
                return 1;
            }
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
        TextView textView;
        int a;
        if (itemViewType == 0) {
            if (view != null) {
                return view;
            }
            view = LayoutInflater.from(this.b).inflate(R.layout.textview_empty_tip_view, viewGroup, false);
            textView = (TextView) view.findViewById(R.id.tvEmpty);
            textView.setText("别让卤煮寂寞太久噢~");
            c.a.b.a(textView, 0, c.a.d.a.a.a().d(R.drawable.ic_topic_empty_post), 0, 0);
            textView.setText("别让卤煮寂寞太久噢~");
            a = cn.xiaochuankeji.tieba.ui.utils.e.a(40.0f);
            textView.setCompoundDrawablePadding(cn.xiaochuankeji.tieba.ui.utils.e.a(20.0f));
            textView.setPadding(0, a, 0, 0);
            textView.setVisibility(0);
            return view;
        } else if (itemViewType == 2) {
            a = this.h ? R.layout.view_item_view_post : R.layout.view_item_more_hot_comment;
            if (view == null) {
                view = LayoutInflater.from(this.b).inflate(a, viewGroup, false);
                view.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ d a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.h) {
                            PostDetailActivity.a(this.a.b, this.a.a);
                        } else if (this.a.d.hasMore()) {
                            this.a.d.queryMore();
                        }
                    }
                });
            } else {
                r0 = view.getTag();
                if (!(Integer.class.isInstance(r0) && ((Integer) r0).intValue() == 2)) {
                    com.izuiyou.a.a.b.e("会跳转到这里吗,跳转到这里吗...");
                    view = LayoutInflater.from(this.b).inflate(a, viewGroup, false);
                }
            }
            textView = (TextView) view.findViewById(R.id.tvMoreGodReview);
            if (this.h) {
                textView.setText("查看原帖 >");
            } else if (this.d.hasMore()) {
                textView.setText("更多热门评论 ↓");
            } else {
                textView.setVisibility(8);
            }
            view.setTag(Integer.valueOf(2));
            return view;
        } else if (itemViewType == 4) {
            if (view == null) {
                view = LayoutInflater.from(this.b).inflate(R.layout.view_item_view_all, viewGroup, false);
                view.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ d a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.j != null) {
                            this.a.j.j();
                        }
                    }
                });
            } else {
                r0 = view.getTag();
                if (!(Integer.class.isInstance(r0) && ((Integer) r0).intValue() == 4)) {
                    com.izuiyou.a.a.b.e("会跳转到这里吗,跳转到这里吗...");
                    view = LayoutInflater.from(this.b).inflate(R.layout.view_item_view_all, viewGroup, false);
                }
            }
            ((TextView) view.findViewById(R.id.label_title)).setText(this.f);
            view.setTag(Integer.valueOf(4));
            return view;
        } else if (itemViewType == 5 || itemViewType == 6) {
            view = LayoutInflater.from(this.b).inflate(R.layout.comment_flag, viewGroup, false);
            textView = (TextView) view.findViewById(R.id.tvFlagTxt);
            TextView textView2 = (TextView) view.findViewById(R.id.tv_sort_way);
            View findViewById = view.findViewById(R.id.vGodFlagDivide);
            ImageView imageView = (ImageView) view.findViewById(R.id.indicator_filter);
            View findViewById2 = view.findViewById(R.id.ll_container);
            textView.setText("评论");
            if (this.k) {
                textView2.setText("最热");
                imageView.setSelected(false);
            } else {
                textView2.setText("最新");
                imageView.setSelected(true);
            }
            findViewById2.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.c();
                }
            });
            if (this.d == null || this.d.itemCount() < 1 || !((Comment) this.d.itemAt(0)).isGod()) {
                return view;
            }
            findViewById.setVisibility(0);
            return view;
        } else {
            e eVar;
            f fVar;
            if (view == null) {
                eVar = new e(this.b);
                view = eVar.f_();
                view.setTag(eVar);
                this.l.add(eVar);
            } else {
                eVar = (e) view.getTag();
            }
            eVar.c();
            Comment comment = (Comment) getItem(i);
            f fVar2 = (f) this.g.get(Long.valueOf(comment._id));
            if (fVar2 == null) {
                fVar = new f();
                this.g.put(Long.valueOf(comment._id), fVar);
            } else {
                fVar = fVar2;
            }
            eVar.a(comment, this.a, i, fVar, this.k);
            if (this.h) {
                eVar.b(i != 0);
            }
            eVar.a(this.j);
            return view;
        }
    }

    public void a(a aVar) {
        this.j = aVar;
    }

    public void b() {
        if (this.l != null && this.l.size() > 0) {
            for (e eVar : this.l) {
                if (eVar != null) {
                    eVar.c();
                }
            }
        }
    }

    public void c() {
        if (this.k) {
            this.k = false;
            if (!this.h) {
                ((PostDetailActivity) this.b).a(this.c, this.k);
            }
            if (this.m != null) {
                this.m.a(false);
            }
            this.c.refresh();
            h.a(this.b, "zy_event_postdetail_page", "切换到最新评论");
            return;
        }
        this.k = true;
        if (!this.h) {
            ((PostDetailActivity) this.b).a(this.d, this.k);
        }
        if (this.m != null) {
            this.m.a(true);
        }
        notifyDataSetChanged();
        if (this.b instanceof PostDetailActivity) {
            ((PostDetailActivity) this.b).c(true);
        }
    }

    public void a(boolean z) {
        this.k = z;
    }

    public boolean d() {
        return this.k;
    }

    public void a(b bVar) {
        this.m = bVar;
    }
}
