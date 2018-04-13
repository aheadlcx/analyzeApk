package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.view.ViewGroup;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.background.tale.TaleComment;
import cn.xiaochuankeji.tieba.widget.rich.RichTextBoard;

public class TaleBodyHolder extends b {
    @BindView
    RichTextBoard textBoard;

    public TaleBodyHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(TaleComment taleComment, int i) {
        this.textBoard.setTale(taleComment.detail);
    }
}
