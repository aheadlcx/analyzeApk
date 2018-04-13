package cn.xiaochuankeji.tieba.ui.post;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import c.a.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.data.post.Post.PostVote;
import cn.xiaochuankeji.tieba.background.data.post.Post.PostVoteItem;
import cn.xiaochuankeji.tieba.ui.base.q;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.PictureView;
import java.util.ArrayList;
import java.util.List;

public class g extends BaseAdapter implements OnClickListener {
    private Context a;
    private PostVote b;
    private int c;
    private int d;

    static class a {
        private PictureView a;
        private PictureView b;
        private PictureView c;
        private PictureView d;
        private PictureView e;
        private PictureView f;
        private PictureView g;
        private PictureView h;
        private View i;
        private View j;
        private TextView k;
        private TextView l;

        a() {
        }

        public PictureView a(int i) {
            switch (i) {
                case 0:
                    return this.a;
                case 1:
                    return this.b;
                case 2:
                    return this.c;
                case 3:
                    return this.d;
                case 4:
                    return this.e;
                case 5:
                    return this.f;
                case 6:
                    return this.g;
                case 7:
                    return this.h;
                default:
                    return null;
            }
        }
    }

    public g(Context context, PostVote postVote, int i) {
        this.a = context;
        this.b = postVote;
        this.c = i;
        for (int size = this.b.getVoteItems().size() - 1; size > 0; size--) {
            if (((PostVoteItem) this.b.getVoteItems().get(size)).getPollCount() > 0) {
                this.d = size;
                return;
            }
        }
    }

    private boolean a() {
        return false;
    }

    private int b() {
        if (a()) {
            return 8;
        }
        return 6;
    }

    public int getCount() {
        int i = 0;
        int size = this.b.getVoteItems().size();
        int i2 = 0;
        while (i < size) {
            i2 += a(i);
            i++;
        }
        return size + i2;
    }

    private int a(int i) {
        int size = ((PostVoteItem) this.b.getVoteItems().get(i)).getMembers().size();
        if (size == 0) {
            return 0;
        }
        if (size % b() == 0) {
            return size / b();
        }
        return (size / b()) + 1;
    }

    public Object getItem(int i) {
        int i2;
        ArrayList arrayList;
        int b;
        int size;
        if (getItemViewType(i) == 4) {
            i2 = i - 1;
            arrayList = new ArrayList();
            b = i2 * b();
            size = ((PostVoteItem) this.b.getVoteItems().get(0)).getMembers().size();
            i2 = (i2 + 1) * b();
            if (i2 > size) {
                i2 = size;
            }
            return ((PostVoteItem) this.b.getVoteItems().get(0)).getMembers().subList(b, i2);
        } else if (getItemViewType(i) == 5) {
            i2 = (i - 2) - a(0);
            arrayList = new ArrayList();
            b = i2 * b();
            size = ((PostVoteItem) this.b.getVoteItems().get(1)).getMembers().size();
            i2 = (i2 + 1) * b();
            if (i2 > size) {
                i2 = size;
            }
            return ((PostVoteItem) this.b.getVoteItems().get(1)).getMembers().subList(b, i2);
        } else if (getItemViewType(i) == 6) {
            i2 = ((i - 3) - a(0)) - a(1);
            arrayList = new ArrayList();
            b = i2 * b();
            size = ((PostVoteItem) this.b.getVoteItems().get(2)).getMembers().size();
            i2 = (i2 + 1) * b();
            if (i2 > size) {
                i2 = size;
            }
            return ((PostVoteItem) this.b.getVoteItems().get(2)).getMembers().subList(b, i2);
        } else if (getItemViewType(i) != 7) {
            return Integer.valueOf(getItemViewType(i));
        } else {
            i2 = (((i - 4) - a(0)) - a(1)) - a(2);
            arrayList = new ArrayList();
            b = i2 * b();
            size = ((PostVoteItem) this.b.getVoteItems().get(3)).getMembers().size();
            i2 = (i2 + 1) * b();
            if (i2 > size) {
                i2 = size;
            }
            return ((PostVoteItem) this.b.getVoteItems().get(3)).getMembers().subList(b, i2);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        int size = this.b.getVoteItems().size();
        if (i == 0) {
            return 0;
        }
        if (i > 0 && i < a(0) + 1) {
            return 4;
        }
        if (i == a(0) + 1) {
            return 1;
        }
        if (i > a(0) + 1 && i < (a(0) + 2) + a(1)) {
            return 5;
        }
        if (size > 2 && i == (a(0) + 2) + a(1)) {
            return 2;
        }
        if (size > 2 && i > (a(0) + 2) + a(1) && i < ((a(0) + 3) + a(1)) + a(2)) {
            return 6;
        }
        if (size <= 3 || i != ((a(0) + 3) + a(1)) + a(2)) {
            return 7;
        }
        return 3;
    }

    public int getViewTypeCount() {
        return 8;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        int i2 = 0;
        if (view == null) {
            View inflate;
            aVar = new a();
            if (getItemViewType(i) == 4 || getItemViewType(i) == 5 || getItemViewType(i) == 6 || getItemViewType(i) == 7) {
                if (a()) {
                    inflate = LayoutInflater.from(this.a).inflate(R.layout.view_item_big_post_vote_detail_avatar, viewGroup, false);
                } else {
                    inflate = LayoutInflater.from(this.a).inflate(R.layout.view_item_post_vote_detail_avatar, viewGroup, false);
                }
                aVar.a = (PictureView) inflate.findViewById(R.id.pv_avatar_1);
                aVar.b = (PictureView) inflate.findViewById(R.id.pv_avatar_2);
                aVar.c = (PictureView) inflate.findViewById(R.id.pv_avatar_3);
                aVar.d = (PictureView) inflate.findViewById(R.id.pv_avatar_4);
                aVar.e = (PictureView) inflate.findViewById(R.id.pv_avatar_5);
                aVar.f = (PictureView) inflate.findViewById(R.id.pv_avatar_6);
                if (a()) {
                    aVar.g = (PictureView) inflate.findViewById(R.id.pv_avatar_7);
                    aVar.h = (PictureView) inflate.findViewById(R.id.pv_avatar_8);
                }
            } else {
                inflate = LayoutInflater.from(this.a).inflate(R.layout.view_item_vote_detail_section, viewGroup, false);
                aVar.i = inflate.findViewById(R.id.section_header);
                aVar.j = inflate.findViewById(R.id.viewPrev);
                aVar.k = (TextView) inflate.findViewById(R.id.tvVoteItemName);
                aVar.l = (TextView) inflate.findViewById(R.id.tvVoteItemPercent);
            }
            inflate.setTag(aVar);
            view = inflate;
        } else {
            aVar = (a) view.getTag();
        }
        int size;
        if (getItemViewType(i) == 4 || getItemViewType(i) == 5 || getItemViewType(i) == 6 || getItemViewType(i) == 7) {
            List list = (List) getItem(i);
            for (int i3 = 0; i3 < list.size(); i3++) {
                PictureView a = aVar.a(i3);
                a.setData(((Member) list.get(i3)).getAvatarPicture());
                a.setVisibility(0);
                a.setTag(list.get(i3));
                a.setOnClickListener(this);
            }
            for (size = list.size(); size < b(); size++) {
                aVar.a(size).setVisibility(4);
            }
        } else {
            size = getItemViewType(i);
            if (size != 0) {
                if (size == 1) {
                    i2 = 1;
                } else if (size == 2) {
                    i2 = 2;
                } else if (size == 3) {
                    i2 = 3;
                } else {
                    i2 = -1;
                }
            }
            if (i2 != -1) {
                int i4;
                PostVoteItem postVoteItem = (PostVoteItem) this.b.getVoteItems().get(i2);
                if (c.e().c()) {
                    i4 = q.b[i2];
                } else {
                    i4 = q.a[i2];
                }
                aVar.j.setBackgroundColor(i4);
                aVar.k.setText(postVoteItem.getName());
                aVar.l.setText(postVoteItem.getPollCount() + "人");
            }
        }
        return view;
    }

    public void onClick(View view) {
        if ((view instanceof PictureView) && view.getTag() != null && Member.class.isInstance(view.getTag())) {
            MemberDetailActivity.a(this.a, ((Member) view.getTag()).getId());
        }
    }
}
