package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.tale.TaleComment;

public abstract class b extends ViewHolder {
    protected int a;
    protected cn.xiaochuankeji.tieba.ui.tale.b b;

    public abstract void a(TaleComment taleComment, int i);

    public b(View view) {
        super(view);
        ButterKnife.a(this, view);
    }

    public b(ViewGroup viewGroup, @LayoutRes int i) {
        this(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
    }

    public static b a(ViewGroup viewGroup, int i, cn.xiaochuankeji.tieba.ui.tale.b bVar) {
        b taleAuthorHolder;
        switch (i) {
            case R.layout.board_author:
                taleAuthorHolder = new TaleAuthorHolder(viewGroup, i);
                break;
            case R.layout.board_text:
                taleAuthorHolder = new TaleTextHolder(viewGroup, i);
                break;
            case R.layout.board_unsupport:
                taleAuthorHolder = new e(viewGroup, i);
                break;
            case R.layout.item_tale_detail_body:
                taleAuthorHolder = new TaleBodyHolder(viewGroup, i);
                break;
            case R.layout.item_tale_detail_comment_empty:
                taleAuthorHolder = new a(viewGroup, i);
                break;
            case R.layout.item_tale_detail_comment_text:
                taleAuthorHolder = new TaleCommentTextHolder(viewGroup, i);
                break;
            case R.layout.item_tale_detail_long_image:
                taleAuthorHolder = new TaleImageHolder(viewGroup, i);
                break;
            case R.layout.item_tale_detail_more:
                taleAuthorHolder = new c(viewGroup, i);
                break;
            case R.layout.item_tale_detail_social:
                taleAuthorHolder = new TaleSocialHolder(viewGroup, i);
                break;
            case R.layout.item_tale_detail_tip:
                taleAuthorHolder = new d(viewGroup, i);
                break;
            case R.layout.item_tale_detail_web_image:
                taleAuthorHolder = new TaleWebImageHolder(viewGroup, i);
                break;
            case R.layout.item_tale_list_header:
                taleAuthorHolder = new TaleHeaderHolder(viewGroup, i);
                break;
            default:
                taleAuthorHolder = new TaleCommentHolder(viewGroup, i);
                break;
        }
        taleAuthorHolder.a(bVar);
        return taleAuthorHolder;
    }

    private void a(cn.xiaochuankeji.tieba.ui.tale.b bVar) {
        this.b = bVar;
    }

    public void a(int i) {
        this.a = i;
    }

    public void b() {
    }

    public void a() {
    }
}
