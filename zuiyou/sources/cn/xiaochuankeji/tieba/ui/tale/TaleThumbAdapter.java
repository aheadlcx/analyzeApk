package cn.xiaochuankeji.tieba.ui.tale;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.tale.TaleAuthor;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.LinkedList;
import java.util.List;

public class TaleThumbAdapter extends Adapter<ThumbUserHolder> {
    private LinkedList<TaleAuthor> a = new LinkedList();

    static class ThumbUserHolder extends ViewHolder {
        @BindView
        WebImageView avatar;
        @BindView
        TextView count;
        @BindView
        TextView name;
        @BindView
        TextView sign;

        public ThumbUserHolder(View view) {
            super(view);
            ButterKnife.a(this, view);
        }
    }

    public class ThumbUserHolder_ViewBinding implements Unbinder {
        private ThumbUserHolder b;

        @UiThread
        public ThumbUserHolder_ViewBinding(ThumbUserHolder thumbUserHolder, View view) {
            this.b = thumbUserHolder;
            thumbUserHolder.avatar = (WebImageView) b.b(view, R.id.pv_avatar, "field 'avatar'", WebImageView.class);
            thumbUserHolder.name = (TextView) b.b(view, R.id.tv_name, "field 'name'", TextView.class);
            thumbUserHolder.sign = (TextView) b.b(view, R.id.label_sign, "field 'sign'", TextView.class);
            thumbUserHolder.count = (TextView) b.b(view, R.id.tvLikeCount, "field 'count'", TextView.class);
        }

        @CallSuper
        public void a() {
            ThumbUserHolder thumbUserHolder = this.b;
            if (thumbUserHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.b = null;
            thumbUserHolder.avatar = null;
            thumbUserHolder.name = null;
            thumbUserHolder.sign = null;
            thumbUserHolder.count = null;
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((ThumbUserHolder) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public ThumbUserHolder a(ViewGroup viewGroup, int i) {
        return new ThumbUserHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_member_preview, viewGroup, false));
    }

    public void a(ThumbUserHolder thumbUserHolder, int i) {
        TaleAuthor taleAuthor = (TaleAuthor) this.a.get(i);
        thumbUserHolder.count.setVisibility(8);
        thumbUserHolder.avatar.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(taleAuthor.id, taleAuthor.avatar));
        thumbUserHolder.name.setText(d.b(taleAuthor.name));
        c.a.b.a(thumbUserHolder.name, 0, 0, taleAuthor.gender == 2 ? R.drawable.personal_girls_s : R.drawable.personal_boy_s, 0);
        if (TextUtils.isEmpty(taleAuthor.sign)) {
            thumbUserHolder.sign.setVisibility(8);
            return;
        }
        thumbUserHolder.sign.setVisibility(0);
        thumbUserHolder.sign.setText(taleAuthor.sign);
    }

    public int getItemCount() {
        return this.a.size();
    }

    public void a(List<TaleAuthor> list) {
        this.a.clear();
        this.a.addAll(list);
        notifyDataSetChanged();
    }

    public void b(List<TaleAuthor> list) {
        int size = this.a.size();
        this.a.addAll(list);
        notifyItemRangeInserted(size, this.a.size() - size);
    }
}
