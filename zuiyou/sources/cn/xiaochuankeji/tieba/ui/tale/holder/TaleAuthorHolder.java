package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.tale.TaleComment;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class TaleAuthorHolder extends b {
    @BindView
    WebImageView avatar;
    @BindView
    AppCompatTextView name;

    public TaleAuthorHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(final TaleComment taleComment, int i) {
        this.name.setText(String.valueOf(taleComment.author.name));
        this.avatar.setWebImage(b.a(taleComment.author.id, taleComment.author.avatar));
        this.avatar.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TaleAuthorHolder b;

            public void onClick(View view) {
                MemberDetailActivity.a(this.b.itemView.getContext(), taleComment.author.id);
            }
        });
    }
}
