package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.support.v7.widget.AppCompatTextView;
import android.view.ViewGroup;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.background.tale.TaleComment;

public class TaleTextHolder extends b {
    @BindView
    AppCompatTextView text;

    public TaleTextHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(TaleComment taleComment, int i) {
        this.text.setText(String.valueOf(taleComment.tale.b));
    }
}
